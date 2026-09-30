#!/usr/bin/env python3
# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at https://mozilla.org/MPL/2.0/.
"""Switch the app to Russian through its own menu, then walk its screens.

Checks the language switch (Menu › Language › Русский sets the per-app locale)
and prints every screen's text for a read-through of the translation. Runs on
today's date. Afterwards emu.restore() clears the language again.

Usage: tools/emulator/russian_tour.py OUTDIR
"""
import sys
import time

from emu import PKG, Shots, back, prefs, sh, start, tap, top, up

shot = Shots(sys.argv[1])

prefs("TIBETAN")
start()
tap(r"[Mm]enu")
shot("menu-en")
tap(r"^Language")
shot("language-dialog")
tap(r"^Русский")
time.sleep(3)
shot("after-switch")

# The Tibetan page, its balloons, sheets, the day in brief and the hours
top()
tap(r"месяц ·")
shot("month-balloon")
back()
tap(r"^год:")
shot("year-balloon")
back()
for i in range(1, 5):
    up()
    shot(f"tib-{i}")
top()
if tap(r"^Вкратце"):
    shot("brief")
    back()
top()
up()
up()
if tap(r"^Жизненная сила"):
    shot("pebble-sheet")
    back()
top()
up()
if tap(r"^Часы дня"):
    shot("hours")
    back()
top()
up()
if tap(r"^Стрижка"):
    shot("haircut-sheet")
    back()

# The 旧暦 page, the day in brief, a reading, the menu and About
prefs("KYUREKI")
start()
shot("kyu-0")
for i in range(1, 6):
    up()
    shot(f"kyu-{i}")
top()
if tap(r"^Вкратце"):
    shot("brief")
    up()
    shot("brief-2")
    back()
top()
if tap(r"(先勝|友引|先負|仏滅|大安|赤口)"):
    shot("rokuyo-sheet")
    back()
top()
tap(r"[Мм]еню")
shot("menu-ru")
if tap(r"^О приложении"):
    shot("about-0")
    up()
    shot("about-1")
    up()
    shot("about-2")
    back()
print(sh(f"cmd locale get-app-locales {PKG}"))
