#!/usr/bin/env python3
"""The Russian side of the store listing and the website, read against its English original.

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

  python3 tools/ru_check.py            # faults and notes; exit 1 on a fault
  python3 tools/ru_check.py --text     # the Russian text alone, unit by unit, for reading it through

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
SHORT_LIMIT = 80
RATIO = (0.7, 1.7)
RATIO_HARD = (0.55, 2.2)
RATIO_MIN_CHARS = 60  # a short label translates to any length
NUMBER = re.compile(r"\d+(?:[.,]\d+)*")
TAG = re.compile(r"</?[a-z]+\b[^>]*>")
HREF = re.compile(r'href=\\?"([^"\\]+)')
PLACEHOLDER = re.compile(r"\{\{[^}]*\}\}")
# Names that stay in Latin script on both sides: products, licences, standards, the app itself.
LATIN_OK = set("""Zanshin Calendar Android F-Droid GPS OLED VSOP87 Wylie Figtree Shippori Mincho Onest PT Serif Mozilla
Public License SIL Open Font CC BY SA F Droid VSOP GeoNames QR Bitcoin Ethereum USDT Tron Gram GRAM TON Licence Spec Title Version
Fonts DUr""".split())


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


def pairs():
    """(label, english, russian) for every unit; a misalignment is a fault of its own."""
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
    if sorted(PLACEHOLDER.findall(en)) != sorted(PLACEHOLDER.findall(ru)):
        faults.append(f"{label}: other placeholders: {PLACEHOLDER.findall(ru)} against {PLACEHOLDER.findall(en)}")
    have = [re.sub(r"\D", "", n) for n in NUMBER.findall(plain(ru))]
    for n in (re.sub(r"\D", "", n) for n in NUMBER.findall(plain(en))):
        if n in have:
            have.remove(n)
        elif int(n) < 13:
            notes.append(f"{label}: the English «{n}» is not a digit here — a word, or dropped?")
        else:
            faults.append(f"{label}: the English number «{n}» is missing")
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
    text = "\n".join(plain(ru) for _, _, ru in units)
    labels = [label for label, _, _ in units]
    r = subprocess.run(["prose-scan", "--rules", str(RULES), "--json", "-"], input=text, capture_output=True, text=True)
    if r.returncode not in (0, 1):
        sys.exit(f"prose-scan failed: {r.stderr.strip()}")
    faults, notes = [], []
    for f in json.loads(r.stdout or "{}").get("-", []):
        where = labels[f["line"] - 1] if 0 < f["line"] <= len(labels) else f"line {f['line']}"
        (faults if f["level"] == "error" else notes).append(f"{where}: {f['rule']}: {f['message']}")
    return faults, notes


def main():
    ap = argparse.ArgumentParser(description=__doc__.split("\n")[0])
    ap.add_argument("--text", action="store_true", help="print the Russian unit by unit and stop")
    a = ap.parse_args()
    units, faults = pairs()
    if a.text:
        for label, _, ru in units:
            print(f"[{label}]\n{ru}\n")
        return 0
    notes = []
    for label, en, ru in units:
        f, n = compare(label, en, ru)
        faults += f
        notes += n
    sd = units[[l for l, _, _ in units].index("short_description")][2]
    if len(sd) > SHORT_LIMIT:
        faults.append(f"short_description: {len(sd)} characters, F-Droid takes {SHORT_LIMIT}")
    f, n = rule_files(units)
    faults += f
    notes += n
    for x in faults:
        print(f"FAULT  {x}")
    for x in notes:
        print(f"note   {x}")
    print(f"{len(units)} units: {len(faults)} fault(s), {len(notes)} note(s)")
    return 1 if faults else 0


if __name__ == "__main__":
    sys.exit(main())
