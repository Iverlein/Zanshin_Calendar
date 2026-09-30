#!/usr/bin/env python3
# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at https://mozilla.org/MPL/2.0/.
"""A fixed tour of the app's screens on the emulator, for comparing two builds.

Run it on one build, install the other, run it again into another folder, and
compare the .txt files (the screens' text) and the .png screenshots below the
status bar; scroll offsets can differ between runs. The clock is set to
2026-10-23 10:00 so both runs show the same day; afterwards restore automatic
time (settings put global auto_time 1) and emu.restore().

Usage: tools/emulator/tour.py OUTDIR
"""
import sys

from emu import Shots, adb, back, nodes, prefs, sh, start, tap, top, up

shot = Shots(sys.argv[1])


def begin():
    sh("date 102310002026.00")
    start(7)


# 旧暦 page, scrolled through
prefs("KYUREKI")
begin()
shot("kyureki-0")
for i in range(1, 6):
    up()
    shot(f"kyureki-{i}")
top()
if tap(r"^In brief"):
    shot("summary")
    up()
    shot("summary-2")
    back()
top()
if tap(r"month"):
    shot("month-balloon")
    back()
top()
up()
if tap(r"(先勝|友引|先負|仏滅|大安|赤口)"):
    shot("rokuyo-sheet")
    back()

# Tibetan page
prefs("TIBETAN")
begin()
shot("tibetan-0")
for i in range(1, 5):
    up()
    shot(f"tibetan-{i}")
top()
if tap(r"year$"):
    shot("year-balloon")
    back()
if tap(r"· Moon ·|· Sun ·|· Mars ·|· Mercury ·|· Jupiter ·|· Venus ·|· Saturn ·"):
    shot("day-balloon")
    back()
up()
up()
if tap(r"^Vitality"):
    shot("pebble-sheet")
    back()

# Menu, about, location, date picker
top()
if tap(r"[Mm]enu", desc=True):
    shot("menu")
    if tap(r"^About"):
        shot("about-0")
        up()
        shot("about-1")
        up()
        shot("about-2")
        back()
tap(r"[Mm]enu", desc=True)
if tap(r"^(Location|Kyoto)"):
    shot("location")
    back()
back()
if tap(r"Oct 2026"):
    shot("date-picker")
    back()

# No birth date: the Tibetan page without personal rows
prefs("TIBETAN", birth=None, kigaku=False)
begin()
up()
shot("tibetan-nobirth")
print(f"{shot.n} screens in {sys.argv[1]}")
