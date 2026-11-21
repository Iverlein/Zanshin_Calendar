#!/usr/bin/env python3
"""The Russian of the app, the store listing and the website, read against its English original.

Ported on 2026-11-21 from the translation engine of the Riding the Boar site (`tools/translate.py check`
and its Russian rule files there): the method is in docs/translation-style.md, the rule files are
tools/ru_rules/. The English is the original; every Russian text the site shows is aligned with it
unit by unit:

  fastlane/metadata/android/{en-US,ru-RU}/full_description.txt   one unit per line: a paragraph, a <b> heading, an <li>
  …/short_description.txt                                        and the 80-character limit
  …/changelogs/<versionCode>.txt                                 the current version's, the one the site shows
  website/i18n/{en,ru}.toml                                      key by key
  website/data/screenshots.toml                                  shot.en against shot.ru, caption and alt
  website/content/donate{,.ru}.md                                the title and description
  app/src/main/res/values{,-ru}/strings.xml                      the interface, key by key (plurals and arrays by item)
  core/src/main/resources/texts/texts{,_ru}.properties           the catalog: terms, wordings, readings, key by key

  python3 tools/ru_check.py                       # site, app and catalog: faults and notes; exit 1 on a fault
  python3 tools/ru_check.py site                  # one scope or several: site, app, catalog
  python3 tools/ru_check.py catalog --family reading.Mansion   # the keys of one family (prefix match)
  python3 tools/ru_check.py app catalog --summary # faults and notes counted per family and kind: the work list
  python3 tools/ru_check.py catalog --family reading.Yoga --text   # the Russian alone, for reading it through
  python3 tools/ru_check.py catalog --family reading.Yoga --pairs  # English and Russian side by side
  python3 tools/ru_check.py --plan                # the rewrite's blocks (tools/ru_plan.toml): families, units, words, done
  python3 tools/ru_check.py --block 76 --summary  # one block: what --family does for one family

A family is the key's first part (`brief`, `hours`; `Mansion`, `DreamVerdict`), and for the catalog's
`reading.` keys its first two (`reading.Mansion`): the unit a rewrite takes at once.

A fault: units that do not align (another number of lines, of items, of keys); a number of the original
missing from its unit; a term in its own script (Tibetan, kanji) missing; another set of HTML tags, links
or {{ … }} placeholders; straight or English quotation marks, a hyphen or an en dash for a dash, a dash
without its spaces, three dots, a double space, a space before punctuation; Latin and Cyrillic letters in
one word; a unit under 0.55 or over 2.2 of its original's length (long units only); an error of the rule
files (a missing «ё», a chat-bot trace, the English afterthought). A note: a unit outside 0.7–1.7 of its
original's length; words left in Latin script; the rule files' warnings. No script reads whether the
Russian says what the English says: that is the reading in docs/translation-style.md and the owner's.
"""
import argparse
import json
import re
import subprocess
import sys
import tomllib
import unicodedata
from pathlib import Path

REPO = Path(__file__).resolve().parent.parent
LISTING = REPO / "fastlane" / "metadata" / "android"
SITE = REPO / "website"
RULES = REPO / "tools" / "ru_rules"
PLAN = REPO / "tools" / "ru_plan.toml"
SHORT_LIMIT = 80
RATIO = (0.7, 1.7)
RATIO_HARD = (0.55, 2.2)
RATIO_MIN_CHARS = 60  # a short label translates to any length
NUMBER = re.compile(r"\d+(?:[.,]\d+)*")
TAG = re.compile(r"</?[a-z]+\b[^>]*>")
HREF = re.compile(r'href=\\?"([^"\\]+)')
PLACEHOLDER = re.compile(r"\{\{[^}]*\}\}|%\d+\$[sdf]|%[sd]|\{\d+\}")
APP_RES = REPO / "app" / "src" / "main" / "res"
CATALOG = REPO / "core" / "src" / "main" / "resources" / "texts"
# Names that stay in Latin script on both sides: products, licences, standards, the app itself.
LATIN_OK = set("""Zanshin Calendar Android F-Droid GPS OLED VSOP87 Wylie Figtree Shippori Mincho Onest PT Serif Mozilla
Public License SIL Open Font CC BY SA F Droid VSOP GeoNames QR Bitcoin Ethereum USDT Tron Gram GRAM TON Licence Spec Title Version
Fonts DUr OM AM HUM HRI PHAT SVAHA Om JST UTC NASA THL Wylie OK""".split())


def versioncode():
    m = re.search(r"versionCode\s*=\s*(\d+)", (REPO / "app" / "build.gradle.kts").read_text())
    return m.group(1)


def lines(path):
    return [l.strip() for l in path.read_text().splitlines() if l.strip() and l.strip() not in ("<ul>", "</ul>")]


def frontmatter(path):
    m = re.match(r"---\n(.*?)\n---", path.read_text(), re.S)
    out = {}
    for l in m.group(1).splitlines():
        k, _, v = l.partition(":")
        out[k.strip()] = v.strip().strip('"')
    return out


def site_pairs():
    """(label, english, russian) for every unit of the site; a misalignment is a fault of its own."""
    out, faults = [], []
    en, ru = LISTING / "en-US", LISTING / "ru-RU"
    le, lr = lines(en / "full_description.txt"), lines(ru / "full_description.txt")
    if len(le) != len(lr):
        faults.append(f"full_description: {len(lr)} units, the English has {len(le)}")
    for i, (a, b) in enumerate(zip(le, lr), 1):
        kind = lambda s: "li" if s.startswith("<li>") else "b" if s.startswith("<b>") else "a" if s.startswith("<a ") else "p"
        if kind(a) != kind(b):
            faults.append(f"full_description line {i}: a {kind(b)} against the English {kind(a)} — the units moved")
        out.append((f"listing {i}", a, b))
    out.append(("short_description", (en / "short_description.txt").read_text().strip(),
                (ru / "short_description.txt").read_text().strip()))
    vc = versioncode()
    out.append((f"changelog {vc}", (en / "changelogs" / f"{vc}.txt").read_text().strip(),
                (ru / "changelogs" / f"{vc}.txt").read_text().strip()))
    ie = tomllib.loads((SITE / "i18n" / "en.toml").read_text())
    ir = tomllib.loads((SITE / "i18n" / "ru.toml").read_text())
    for k in sorted(set(ie) ^ set(ir)):
        faults.append(f"i18n: key «{k}» in {'en only' if k in ie else 'ru only'}")
    out += [(f"i18n {k}", ie[k], ir[k]) for k in ie if k in ir]
    shots = tomllib.loads((SITE / "data" / "screenshots.toml").read_text())["shot"]
    for s in shots:
        for field in ("caption", "alt"):
            out.append((f"shot {s['file']} {field}", s["en"][field], s["ru"][field]))
    de, dr = frontmatter(SITE / "content" / "donate.md"), frontmatter(SITE / "content" / "donate.ru.md")
    for field in ("title", "description"):
        out.append((f"donate {field}", de[field], dr[field]))
    return out, faults


def android_strings(path):
    """key → text of an Android string resource; plurals and arrays give one key per item."""
    import xml.etree.ElementTree as ET
    def text(e):
        t = (e.text or "") + "".join(ET.tostring(c, encoding="unicode") for c in e)
        return t.replace("\\'", "'").replace('\\"', '"').replace("\\n", "\n").replace("\\@", "@").replace("\\?", "?")
    out = {}
    for e in ET.parse(path).getroot():
        if e.get("translatable") == "false":
            continue
        name = e.get("name")
        if e.tag == "string":
            out[name] = text(e)
        elif e.tag == "plurals":
            for i in e:
                out[f"{name}#{i.get('quantity')}"] = text(i)
        elif e.tag == "string-array":
            for k, i in enumerate(e):
                out[f"{name}#{k}"] = text(i)
    return out


def properties(path):
    """key → text of a UTF-8 properties file of one line per entry, as Catalog.kt reads it."""
    out = {}
    for line in path.read_text().splitlines():
        if not line.strip() or line.lstrip().startswith(("#", "!")):
            continue
        k, _, v = line.partition("=")
        out[k.strip()] = v.replace("\\n", "\n").replace("\\:", ":").replace("\\=", "=")
    return out


def family(scope, key):
    if scope == "catalog" and key.startswith("reading."):
        return ".".join(key.split(".")[:2])
    if scope == "catalog":
        return key.split(".")[0]
    return key.split("_")[0].split("#")[0]


def keyed(scope, en, ru, prefix):
    out, faults = [], []
    for k in list(ru):
        base, _, q = k.partition("#")
        if q in ("zero", "two", "few", "many") and k not in en and f"{base}#other" in en:
            en = {**en, k: en[f"{base}#other"]}  # Russian has more plural forms than English
    for k in en:
        if prefix and not k.startswith(prefix):
            continue
        if k not in ru:
            faults.append(f"{scope} {k}: no Russian")
            continue
        if en[k] == ru[k] and not re.search(r"[A-Za-z]{2}", PLACEHOLDER.sub("", en[k]).replace("EEE", "").replace("MMM", "")
                                             .replace("yyyy", "").replace("LLLL", "")):
            continue  # a format or a symbol string: nothing to translate
        if k.startswith("pattern_"):
            continue  # a date pattern: its letters are the format's, not words
        out.append((f"{scope} {k}", en[k], ru[k]))
    for k in ru:
        if k not in en and (not prefix or k.startswith(prefix)):
            faults.append(f"{scope} {k}: Russian only, no English key")
    return out, faults


def pairs(scopes, prefix=None):
    units, faults = [], []
    if "site" in scopes:
        u, f = site_pairs()
        units += u
        faults += f
    if "app" in scopes:
        u, f = keyed("app", android_strings(APP_RES / "values" / "strings.xml"),
                     android_strings(APP_RES / "values-ru" / "strings.xml"), prefix)
        units += u
        faults += f
    if "catalog" in scopes:
        u, f = keyed("catalog", properties(CATALOG / "texts.properties"), properties(CATALOG / "texts_ru.properties"), prefix)
        units += u
        faults += f
    return units, faults


def plain(text):
    return re.sub(r"\s+", " ", PLACEHOLDER.sub(" ", TAG.sub(" ", text))).strip()


def other_scripts(text):
    return [w for w in re.findall(r"[^\W\d_]+", text)
            if any(not unicodedata.name(c, "").startswith(("LATIN", "CYRILLIC")) for c in w)]


def typography(label, text):
    out = []
    t = PLACEHOLDER.sub("X", HREF.sub("", TAG.sub(lambda m: "<>", text)))  # an address, a tag, a placeholder are not prose
    if '"' in t:
        out.append(f"{label}: a straight quotation mark — «…» outside, „…“ inside")
    if "”" in t or ("“" in t and "„" not in t) or "‘" in t:
        out.append(f"{label}: English quotation marks — «…» outside, „…“ inside")
    if t.count("«") != t.count("»"):
        out.append(f"{label}: {t.count('«')} «, {t.count('»')} » — a quotation mark is not closed")
    for m in re.finditer(r"\S+ [-–] \S+", t):
        out.append(f"{label}: a hyphen or an en dash for a dash: «{m.group(0)}»")
    for m in re.finditer(r"\S+—\S*|\S*—\S+", t):
        out.append(f"{label}: a dash without its spaces: «{m.group(0)}»")
    if "..." in t:
        out.append(f"{label}: three dots — the ellipsis is one character, «…»")
    if re.search(r"\S  +\S", t):
        out.append(f"{label}: a double space")
    for m in re.finditer(r"\S+ [,.;:!?»)](?!\S*\w)", t):
        out.append(f"{label}: a space before a punctuation mark: «{m.group(0)}»")
    return out


def compare(label, en, ru):
    faults, notes = [], []
    if not re.search(r"[а-яё]", ru, re.I) and re.search(r"[a-z]{3}", plain(en)):
        faults.append(f"{label}: not in Russian: «{ru}»")
    if sorted(re.sub(r"\s.*", ">", t) for t in TAG.findall(en)) != sorted(re.sub(r"\s.*", ">", t) for t in TAG.findall(ru)):
        faults.append(f"{label}: other HTML tags than the English")
    if HREF.findall(en) != HREF.findall(ru):
        notes.append(f"{label}: other links than the English: {HREF.findall(ru)} against {HREF.findall(en)}")
    if set(PLACEHOLDER.findall(en)) != set(PLACEHOLDER.findall(ru)):
        faults.append(f"{label}: other placeholders: {PLACEHOLDER.findall(ru)} against {PLACEHOLDER.findall(en)}")
    have = [re.sub(r"\D", "", n) for n in NUMBER.findall(plain(ru))]
    for n in (re.sub(r"\D", "", n) for n in NUMBER.findall(plain(en))):
        if n in have:
            have.remove(n)
        elif int(n) < 13:
            notes.append(f"{label}: the English «{n}» is not a digit here — a word, or dropped?")
        else:
            # A reading may spell a date as a word («двадцать четвёртого числа»); the site keeps digits.
            (notes if label.startswith(("app ", "catalog ")) else faults).append(f"{label}: the English number «{n}» is missing")
    if have:
        notes.append(f"{label}: number(s) the English does not state: {', '.join(have)}")
    for w in other_scripts(en):
        if w not in ru:
            faults.append(f"{label}: the term «{w}» in its own script is missing")
    faults += typography(label, ru)
    for w in re.findall(r"[^\W\d_]+", plain(ru)):
        if len({unicodedata.name(c, "")[:5] for c in w} & {"LATIN", "CYRIL"}) == 2:
            faults.append(f"{label}: Latin and Cyrillic letters in one word: «{w}»")
    pe, pr = plain(en), plain(ru)
    if len(pe) >= RATIO_MIN_CHARS:
        r = len(pr) / len(pe)
        if not RATIO[0] <= r <= RATIO[1]:
            hard = not RATIO_HARD[0] <= r <= RATIO_HARD[1]
            (faults if hard else notes).append(f"{label}: {len(pr)} characters against {len(pe)} (×{r:.2f})"
                                               " — something dropped or added" + ("" if hard else "?"))
    # Wylie inside the Tibetan brackets is the transliteration, Latin by design.
    bare = re.sub(r"\([^()]*[\u0F00-\u0FFF][^()]*\)", " ", pr)
    lat = [w for w in re.findall(r"[A-Za-z]+", bare) if w not in LATIN_OK and not re.fullmatch(r"[IVXLCDM]+", w)]
    if lat:
        notes.append(f"{label}: in Latin script: {', '.join(dict.fromkeys(lat))}")
    return faults, notes


def rule_files(units):
    """(faults, notes) of tools/ru_rules/ over every Russian unit, through the machine's prose-scan."""
    # A placeholder stands as a word, so a line that opens with one does not open with an arrow.
    text = "\n".join(re.sub(r"\s+", " ", PLACEHOLDER.sub("X", TAG.sub(" ", ru))).strip() for _, _, ru in units)
    labels = [label for label, _, _ in units]
    r = subprocess.run(["prose-scan", "--rules", str(RULES), "--json", "-"], input=text, capture_output=True, text=True)
    if r.returncode not in (0, 1):
        sys.exit(f"prose-scan failed: {r.stderr.strip()}")
    faults, notes = [], []
    for f in json.loads(r.stdout or "{}").get("-", []):
        where = labels[f["line"] - 1] if 0 < f["line"] <= len(labels) else f"line {f['line']}"
        (faults if f["level"] == "error" else notes).append(f"{where}: {f['rule']}: {f['message']}")
    return faults, notes


def blocks():
    return tomllib.loads(PLAN.read_text())["block"]


def block_of(scope, fam, plan):
    """The first block whose pattern takes the family, or None."""
    name = fam.removeprefix("reading.")
    for b in plan:
        if any(re.fullmatch(p, name) for p in b.get(scope, [])):
            return b["id"]
    return None


def show_plan(units):
    from collections import defaultdict
    plan = blocks()
    agg = defaultdict(lambda: [set(), 0, 0])
    lost = set()
    for label, _, ru in units:
        scope, key = label.split(" ", 1)
        fam = family(scope, key)
        b = block_of(scope, fam, plan)
        if b is None:
            lost.add(f"{scope} {fam}")
            continue
        agg[b][0].add(f"{scope} {fam}")
        agg[b][1] += 1
        agg[b][2] += len(plain(ru).split())
    total = [0, 0]
    for b in plan:
        fams, n, w = agg[b["id"]]
        total[0] += n
        total[1] += w
        print(f"{b['id']:3}  {b['done'] or 'open':10}  {len(fams):4} families  {n:5} units  {w:6} words  {b['title']}")
    print(f"     {'':10}  {'':14}  {total[0]:5} units  {total[1]:6} words")
    for x in sorted(lost):
        print(f"FAULT  {x}: in no block of {PLAN.name}")
    return 1 if lost else 0


def kind(message):
    """The kind of a finding, for the summary: the rule file's name, or the check's first words."""
    m = re.match(r"(?:[^:]+: )?(Yo|ChatArtifacts|ChatVoice|ModelHand|Calques|Cliches|Connectors|Formulas|Inflation|"
                 r"Kanzelyarit|MixedScript|Translationese|Afterthought):", message.split(": ", 1)[1] if ": " in message else message)
    if m:
        return m.group(1)
    body = message.split(": ", 1)[1] if ": " in message else message
    for k, pat in (("ratio", r"characters against"), ("number", r"number|not a digit"), ("script term", r"own script"),
                   ("quotes", r"quotation"), ("dash", r"dash"), ("ellipsis", r"three dots"), ("spaces", r"space"),
                   ("Latin", r"Latin"), ("tags", r"tags|links"), ("placeholders", r"placeholder"), ("missing", r"no Russian|only")):
        if re.search(pat, body):
            return k
    return "other"


def main():
    ap = argparse.ArgumentParser(description=__doc__.split("\n")[0])
    ap.add_argument("scopes", nargs="*", choices=["site", "app", "catalog"], help="default: all three")
    ap.add_argument("--family", help="only the keys that start with this (app and catalog)")
    ap.add_argument("--plan", action="store_true", help="the rewrite's blocks, from tools/ru_plan.toml")
    ap.add_argument("--block", type=int, help="only the families of this block of tools/ru_plan.toml")
    ap.add_argument("--summary", action="store_true", help="count faults and notes per family and kind")
    ap.add_argument("--text", action="store_true", help="print the Russian unit by unit and stop")
    ap.add_argument("--pairs", action="store_true", help="print English and Russian unit by unit and stop")
    a = ap.parse_args()
    scopes = a.scopes or ["site", "app", "catalog"]
    if a.plan:
        units, _ = pairs(["app", "catalog"])
        return show_plan(units)
    if a.block:
        plan = blocks()
        scopes = [s for s in scopes if s != "site"]
        units, faults = pairs(scopes)
        units = [u for u in units if block_of(u[0].split()[0], family(*u[0].split(" ", 1)), plan) == a.block]
        faults = []
    else:
        units, faults = pairs(scopes, a.family)
    if a.text or a.pairs:
        for label, en, ru in units:
            print(f"[{label}]\n" + (f"EN {en}\nRU " if a.pairs else "") + f"{ru}\n")
        return 0
    notes = []
    for label, en, ru in units:
        f, n = compare(label, en, ru)
        faults += f
        notes += n
    if "site" in scopes:
        sd = units[[l for l, _, _ in units].index("short_description")][2]
        if len(sd) > SHORT_LIMIT:
            faults.append(f"short_description: {len(sd)} characters, F-Droid takes {SHORT_LIMIT}")
    f, n = rule_files(units)
    faults += f
    notes += n
    if a.summary:
        from collections import Counter, defaultdict
        fam = {label: (label.split()[0], family(label.split()[0], label.split()[1]) if " " in label else label)
               for label, _, _ in units}
        size = Counter(fam[l] for l, _, _ in units)
        rows = defaultdict(Counter)
        for level, items in (("F", faults), ("n", notes)):
            for x in items:
                label = x.split(":")[0]
                key = fam.get(label) or (label.split()[0], family(label.split()[0], label.split()[1]) if " " in label else label)
                rows[key][f"{level}:{kind(x)}"] += 1
        print(f"{'scope':8} {'family':30} {'units':>5} {'faults':>6} {'notes':>6}  kinds")
        for key in sorted(size, key=lambda k: (k[0], -sum(v for c, v in rows[k].items() if c[0] == "F"), k[1])):
            c = rows[key]
            fa = sum(v for k, v in c.items() if k[0] == "F")
            no = sum(v for k, v in c.items() if k[0] == "n")
            print(f"{key[0]:8} {key[1]:30} {size[key]:5} {fa:6} {no:6}  " + ", ".join(f"{k} {v}" for k, v in c.most_common(6)))
    else:
        for x in faults:
            print(f"FAULT  {x}")
        for x in notes:
            print(f"note   {x}")
    print(f"{len(units)} units: {len(faults)} fault(s), {len(notes)} note(s)")
    return 1 if faults else 0


if __name__ == "__main__":
    sys.exit(main())
