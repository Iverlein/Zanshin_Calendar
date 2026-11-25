#!/usr/bin/env python3
"""Apply a block's Russian rewrites and write the owner's letter (ROADMAP L5).

    ru_rewrite.py apply CHANGES        write the new Russian into strings.xml and texts_ru.properties
    ru_rewrite.py edit EDITS [--out CHANGES]
                                       substring edits on the present Russian, written as a CHANGES file
                                       (to stdout, or --out) for apply
    ru_rewrite.py letter [--base REV] [--block N] [--out FILE]
                                       every Russian unit changed since REV (default main),
                                       grouped by family: the English, before, after
    ru_rewrite.py compose N=REV... [--base REV]
                                       write into this tree every Russian unit as block N's REV
                                       has it, for blocks rewritten in branches side by side

CHANGES is plain text, one unit per paragraph: a header line «app <key>» or
«catalog <key>» (a plural item as «key#few»), then the new Russian on one
line; a line starting with «#» is a comment. Each unit is read back after
writing and must parse to the text given.

EDITS is the same shape with two lines under each header, the part of the
present Russian to replace and its replacement: «catalog <key>», «old»,
«new». Several edits of one unit apply in order; each old part must stand in
the unit exactly once.

compose takes, for each unit, the text of the REV of the block that owns it
(tools/ru_plan.toml), when that block changed it since its fork from --base; a unit its
owner left alone takes the change the other blocks made (a block renaming a
term in other blocks' units, as CatalogTest's naming check asks). Units the
other blocks changed in different ways are listed and keep the first.
"""
import argparse
import html
import re
import subprocess
import sys
import tempfile
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent))
import ru_check  # noqa: E402

REPO = ru_check.REPO
RU_XML = ru_check.APP_RES / "values-ru" / "strings.xml"
EN_XML = ru_check.APP_RES / "values" / "strings.xml"
RU_PROPS = ru_check.CATALOG / "texts_ru.properties"
EN_PROPS = ru_check.CATALOG / "texts.properties"


def parse_changes(path):
    units, head = [], None
    for raw in Path(path).read_text().splitlines():
        line = raw.rstrip()
        if not line or line.startswith("#"):
            head = None if not line else head
            continue
        if head is None:
            scope, _, key = line.partition(" ")
            if scope not in ("app", "catalog") or not key:
                sys.exit(f"bad header: {line!r}")
            head = (scope, key.strip())
        else:
            units.append((*head, line))
            head = None
    return units


def xml_escape(text):
    text = html.escape(text, quote=False)
    return text.replace("'", "\\'").replace('"', '\\"').replace("\n", "\\n")


def set_app(xml, key, text):
    name, _, q = key.partition("#")
    body = xml_escape(text)
    if not q:
        pat = re.compile(rf'(<string name="{re.escape(name)}"[^>]*>)(.*?)(</string>)', re.S)
    else:
        block = re.search(rf'<(plurals|string-array) name="{re.escape(name)}"[^>]*>.*?</\1>', xml, re.S)
        if not block:
            sys.exit(f"no plurals or array {name}")
        if q.isdigit():
            items = list(re.finditer(r"(<item[^>]*>)(.*?)(</item>)", block.group(0), re.S))
            m = items[int(q)]
        else:
            m = re.search(rf'(<item quantity="{q}">)(.*?)(</item>)', block.group(0), re.S)
        if not m:
            sys.exit(f"no item {key}")
        inner = block.group(0)[: m.start(2)] + body + block.group(0)[m.end(2):]
        return xml[: block.start()] + inner + xml[block.end():]
    m = pat.search(xml)
    if not m:
        sys.exit(f"no string {name}")
    return xml[: m.start(2)] + body + xml[m.end(2):]


def set_catalog(lines, key, text):
    value = text.replace("\n", "\\n")
    for i, line in enumerate(lines):
        if line.partition("=")[0].strip() == key and not line.lstrip().startswith(("#", "!")):
            lines[i] = f"{key}={value}"
            return
    sys.exit(f"no catalog key {key}")



def edit(path, out):
    lines = [l.rstrip() for l in Path(path).read_text().splitlines()]
    app, cat = ru_check.android_strings(RU_XML), ru_check.properties(RU_PROPS)
    texts, order, i = {}, [], 0
    while i < len(lines):
        line = lines[i]
        if not line or line.startswith("#"):
            i += 1
            continue
        scope, _, key = line.partition(" ")
        key = key.strip()
        if scope not in ("app", "catalog") or not key:
            sys.exit(f"bad edit at line {i + 1}: {line!r}")
        if i + 2 >= len(lines):
            sys.exit(f"edit without its two lines: {line!r}")
        old, new = lines[i + 1], lines[i + 2]
        source = app if scope == "app" else cat
        if (scope, key) not in texts:
            if key not in source:
                sys.exit(f"no {scope} key {key}")
            texts[(scope, key)] = source[key]
            order.append((scope, key))
        n = texts[(scope, key)].count(old)
        if n != 1:
            sys.exit(f"{scope} {key}: «{old}» stands {n} times, not once")
        texts[(scope, key)] = texts[(scope, key)].replace(old, new)
        i += 3
    body = "\n\n".join(f"{s} {k}\n{texts[(s, k)]}" for s, k in order) + "\n"
    if out:
        Path(out).write_text(body)
        print(f"{len(order)} unit(s) → {out}")
    else:
        sys.stdout.write(body)
    return 0


def apply(path):
    units = parse_changes(path)
    xml = RU_XML.read_text()
    props = RU_PROPS.read_text().split("\n")
    for scope, key, text in units:
        if scope == "app":
            xml = set_app(xml, key, text)
        else:
            set_catalog(props, key, text)
    RU_XML.write_text(xml)
    RU_PROPS.write_text("\n".join(props))
    app, cat = ru_check.android_strings(RU_XML), ru_check.properties(RU_PROPS)
    bad = [(s, k) for s, k, t in units if (app if s == "app" else cat).get(k) != t]
    for s, k in bad:
        print(f"read back differs: {s} {k}", file=sys.stderr)
    print(f"{len(units)} unit(s) written, {len(bad)} differ")
    return 1 if bad else 0


def at_rev(rev, path, parse):
    rel = path.relative_to(REPO)
    blob = subprocess.run(["git", "-C", str(REPO), "show", f"{rev}:{rel}"],
                          capture_output=True, text=True, check=True).stdout
    with tempfile.NamedTemporaryFile("w", suffix=path.suffix, delete=False) as f:
        f.write(blob)
    try:
        return parse(Path(f.name))
    finally:
        Path(f.name).unlink()


def letter(rev, block, out):
    plan = ru_check.blocks() if block else None
    sections = {}
    for scope, ru_path, en_path, parse in (
        ("app", RU_XML, EN_XML, ru_check.android_strings),
        ("catalog", RU_PROPS, EN_PROPS, ru_check.properties),
    ):
        old, new, en = at_rev(rev, ru_path, parse), parse(ru_path), parse(en_path)
        for key in sorted(new, key=lambda k: list(new).index(k)):
            if old.get(key) == new[key]:
                continue
            fam = ru_check.family(scope, key)
            if plan and str(ru_check.block_of(scope, fam, plan)) != str(block):
                continue
            base = key.split("#")[0] + ("#other" if "#" in key and key not in en else "")
            sections.setdefault((scope, fam), []).append(
                (key, en.get(key, en.get(base, "")), old.get(key, "(new)"), new[key]))
    n = sum(len(v) for v in sections.values())
    lines = [f"Changed Russian units since {rev}: {n}, in {len(sections)} families.", ""]
    for (scope, fam), units in sections.items():
        lines += [f"## {scope} · {fam} ({len(units)})", ""]
        for key, en, old, new in units:
            lines += [f"**{key}**", f"EN: {en}", f"Было: {old}", f"Стало: {new}", ""]
    text = "\n".join(lines)
    if out:
        Path(out).write_text(text)
        print(f"{n} unit(s) in {len(sections)} families → {out}")
    else:
        print(text)
    return 0


def compose(pairs, base):
    plan = ru_check.blocks()
    revs = {}
    for pair in pairs:
        n, _, rev = pair.partition("=")
        if not n.isdigit() or not rev:
            sys.exit(f"not N=REV: {pair!r}")
        revs[int(n)] = rev
    xml, props = RU_XML.read_text(), RU_PROPS.read_text().split("\n")
    written, split = 0, []
    for scope, ru_path, parse in (("app", RU_XML, ru_check.android_strings),
                                  ("catalog", RU_PROPS, ru_check.properties)):
        old = at_rev(base, ru_path, parse)
        # a block's change is what its REV has against its own fork point, so a branch behind
        # --base does not undo what --base gained since
        texts, forks = {}, {}
        for n, rev in revs.items():
            fork = subprocess.run(["git", "-C", str(REPO), "merge-base", base, rev],
                                  capture_output=True, text=True, check=True).stdout.strip()
            texts[n], forks[n] = at_rev(rev, ru_path, parse), at_rev(fork, ru_path, parse)
        mine = lambda n, key: texts[n].get(key) != forks[n].get(key)
        for key, was in old.items():
            owner = ru_check.block_of(scope, ru_check.family(scope, key), plan)
            if owner in texts and mine(owner, key):
                text = texts[owner][key]
            else:
                changed = list(dict.fromkeys(texts[n][key] for n in texts if mine(n, key)))
                if not changed:
                    continue
                if len(changed) > 1:
                    split.append(f"{scope} {key}")
                text = changed[0]
            if scope == "app":
                xml = set_app(xml, key, text)
            else:
                set_catalog(props, key, text)
            written += 1
    RU_XML.write_text(xml)
    RU_PROPS.write_text("\n".join(props))
    for x in split:
        print(f"changed differently outside its block, the first kept: {x}", file=sys.stderr)
    print(f"{written} unit(s) composed from {len(revs)} block(s)")
    return 1 if split else 0


def main():
    ap = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    sub = ap.add_subparsers(dest="cmd", required=True)
    a = sub.add_parser("apply")
    a.add_argument("changes")
    e = sub.add_parser("edit")
    e.add_argument("edits")
    e.add_argument("--out")
    l = sub.add_parser("letter")
    l.add_argument("--base", default="main")
    l.add_argument("--block", type=int)
    l.add_argument("--out")
    c = sub.add_parser("compose")
    c.add_argument("pairs", nargs="+", metavar="N=REV")
    c.add_argument("--base", default="main")
    args = ap.parse_args()
    if args.cmd == "apply":
        return apply(args.changes)
    if args.cmd == "edit":
        return edit(args.edits, args.out)
    if args.cmd == "compose":
        return compose(args.pairs, args.base)
    return letter(args.base, args.block, args.out)


if __name__ == "__main__":
    sys.exit(main())
