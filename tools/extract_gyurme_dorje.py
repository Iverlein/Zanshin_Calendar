#!/usr/bin/env python3
# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at https://mozilla.org/MPL/2.0/.
"""Turn Gyurme Dorje's sixty-year charts into a test-vector file.

Source: Gyurme Dorje, Tibetan Elemental Divination Paintings (London: John
  Eskenazi, 2001), tabular charts to Plates 3-8, fig. 2, pp. 70-85, after the
  White Beryl (f. 156a/b, f. 158a) and the Moonbeams (ff. 5b-6b). Read from the
  OCR text of the archive.org copy (the "_djvu.txt" file).

Usage: tools/extract_gyurme_dorje.py book_djvu.txt > core/src/test/resources/vectors/gyurme-dorje-forces.tsv

Each year of the charts lists its body element (a named form, e.g. 'molten
iron cauldron'), then its vitality (the animal's head), destiny (clothing),
luck (belt) and the element of the luck (mat). The years run in order from
wood mouse, so the n-th head belongs to year n. A body whose line the OCR
lost is left empty. The last column is the chart's relationship of the
destiny element with the vitality element (kha-yan, khong-nong, se-zhig,
kha-ral, 'dun-khur).

One correction, stated in the output: year 57, iron monkey, prints its
destiny as "wood, symbolised by green clothing" (p. 85, checked on the page
image), but its relationship row reads kha-yan, destiny the same as its iron
vitality, and the destiny of every other year is the element of the year.
"""

import re
import sys

# Some pages prefix the aspect's name ("vitality element (srog) earth, ..."); "tire" is the OCR's fire.
ELEMENT = re.compile(r"^(?:[a-z ]+ \([^)]*\) )?(wood|fire|tire|earth|iron|water)[,;.] symbolised by (.*)", re.IGNORECASE)
PARTS = {"head": "vitality", "clothing": "destiny", "belt": "luck", "mat": "mat"}
# Head, clothing and belt are drawn in the element's colour: a second reading of each.
COLOUR = {"green": "wood", "red": "fire", "yellow": "earth", "white": "iron", "blue": "water"}

lines = open(sys.argv[1], encoding="utf-8").read().splitlines()
# The charts sit between the section on the four aspects and the next chapter.
start = next(i for i, l in enumerate(lines) if l.startswith("Four elemental aspects of the sexagenary cycle"))
end = next(i for i, l in enumerate(lines) if i > start and l.startswith("Subjects of divination and corresponding elements"))

RELATION = re.compile(r"(kha-yan|khong-nong|se-zhig|kha-ral|'dun-khur)\s*$")
CORRECTIONS = {57: {"destiny": "iron"}}

entries = []  # (part, element) in page order; ("relation", term) for the relationship rows
for line in lines[start:end]:
    if m := RELATION.search(line.replace("\u2018", "'").strip()):
        entries.append(("relation", m.group(1)))
    elif m := ELEMENT.match(line.strip()):
        what = m.group(2).lower()
        part = next((p for key, p in PARTS.items() if re.search(rf"\b{key}\b", what)), "body")
        element = m.group(1).lower().replace("tire", "fire")
        colour = what.split()[0]
        if part in ("vitality", "destiny", "luck") and COLOUR.get(colour) != element:
            sys.exit(f"{element} drawn in {colour}: {line.strip()}")
        entries.append((part, element))

years = []
for i, (part, element) in enumerate(entries):
    if part != "vitality":
        continue
    body = entries[i - 1][1] if i > 0 and entries[i - 1][0] == "body" else ""
    following = dict(entries[i + 1:i + 3])
    nxt = next((j for j in range(i + 1, len(entries)) if entries[j][0] == "vitality"), len(entries))
    relation = next((e for p, e in entries[i + 1:nxt] if p == "relation"), "")
    n = len(years) + 1
    row = {"destiny": following.get("destiny", ""), "luck": following.get("luck", "")} | CORRECTIONS.get(n, {})
    years.append((element, body, row["destiny"], row["luck"], relation))

if len(years) != 60:
    sys.exit(f"expected 60 years, found {len(years)}")

print("# Four elemental aspects of each year of the sexagenary cycle, year 1 = wood mouse.")
print("# Source: Gyurme Dorje, Tibetan Elemental Divination Paintings (London: John Eskenazi, 2001),")
print("# charts to Plates 3-8, fig. 2, pp. 70-85, after the White Beryl and the Moonbeams;")
print("# extracted from the archive.org OCR by tools/extract_gyurme_dorje.py. Empty: lost in the OCR.")
print("# Correction: year 57 (iron monkey) prints destiny wood (green clothing); its relationship row reads")
print("# kha-yan, destiny the same as its iron vitality, so destiny is iron here.")
print("# year\tvitality\tbody\tdestiny\tluck\tdestiny_with_vitality")
for n, row in enumerate(years, start=1):
    print("\t".join([str(n), *row]))
