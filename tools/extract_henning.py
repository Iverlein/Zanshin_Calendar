#!/usr/bin/env python3
# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at https://mozilla.org/MPL/2.0/.
"""Turn Edward Henning's computed Phugpa calendars into a test-vector file.

Source: Edward Henning, Traditional Tibetan calendar archive, Phugpa list,
  http://www.kalacakra.org/calendar/tiblist.htm
  one file per year: http://www.kalacakra.org/calendar/tdata/pl_YYYY.txt
Henning checked these computations against printed Tibetan almanacs.

Usage: tools/extract_henning.py pl_2000.txt pl_2026.txt ... > core/src/test/resources/vectors/henning-phugpa.tsv

One row per calendar day: Gregorian date, Tibetan year, month, leap flag,
lunar day, weekday, lunar mansion, element pair, yoga, karana, day animal,
trigram, number, Chinese mansion of the solar day, festival text. Omitted
lunar days produce no row; a repeated day produces two rows.
"""

import re
import sys
from datetime import datetime

DAY = re.compile(r"^(\d+): (\w+)\. ([^.]+)\. (\w+-\w+); (\d+ \w+ \d+)$")
MONTH = re.compile(r"^Tibetan Lunar Month: (\d+)(?: \((Intercalary|Delayed)\))? - ")
YEAR = re.compile(r"^New Year: (\d+),")
SECOND = re.compile(r"^\s+([^,]+), ([^,]+), (\w+), (\w+) (\d)$")
SOLAR = re.compile(r"^\s+Solar: [\w-]+\. (\w+) \d$")

rows = []
for path in sys.argv[1:]:
    year = month = leap = None
    current = None
    for raw in open(path, encoding="utf-8"):
        line = raw.rstrip("\n")
        if m := YEAR.match(line):
            year = int(m.group(1))
        elif m := MONTH.match(line):
            month, leap = int(m.group(1)), m.group(2) == "Intercalary"
        elif m := DAY.match(line):
            date = datetime.strptime(m.group(5), "%d %b %Y").date().isoformat()
            current = [date, year, month, int(leap), int(m.group(1)), m.group(2), m.group(3), m.group(4)]
            current += ["", "", "", "", "", "", ""]
            rows.append(current)
        elif current and (m := SECOND.match(line)) and current[8] == "":
            current[8:13] = [m.group(1), m.group(2), m.group(3), m.group(4), m.group(5)]
        elif current and (m := SOLAR.match(line)):
            current[13] = m.group(1)
        elif current and line.startswith("  ") and line.strip()[:1].isalpha() and not line.strip().startswith("Solar:"):
            current[14] = line.strip()

print("# Phugpa calendar days computed by Edward Henning, Traditional Tibetan calendar archive,")
print("# http://www.kalacakra.org/calendar/tiblist.htm — extracted by tools/extract_henning.py.")
print("# date\tyear\tmonth\tleap\tday\tweekday\tmansion\telements\tyoga\tkarana\tanimal\ttrigram\tnumber\tchinese_mansion\tfestival")
for r in sorted(rows, key=lambda r: r[0]):
    print("\t".join(str(x) for x in r))
