#!/usr/bin/env python3
# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at https://mozilla.org/MPL/2.0/.
"""The seven store screenshots, in one language, on the emulator.

1. Tibetan day: Saga Dawa Düchen, 2027-06-18, a lucky day, in Lhasa
2. 旧暦 day: 2026-10-23 (十三夜, 霜降 begins), in Kyoto
3. The Saga Dawa Düchen reading sheet, with its source, opened from the heading
4. The 旧暦 almanac bands, from 中段 down
5. The menu over the 旧暦 page
6. The meditation timer: a zazen of 20, 10 and 30 minutes, set from its preset
7. The year of age of a man born 1976-06-01 (a fire dragon year, chart 6.2's
   subject) in 2026, opened from the year's balloon on 2026-10-23 in Lhasa

Shots 1 to 6 set no birth date, so no personal rows appear. The status bar is put in demo
mode (09:00, full battery, no signal icons). This image ignores demo mode's
"hide notifications", and every clock change posts a "Clock change"
notification, so the shade is cleared after each change. Demo mode is left
before the shade is opened, and entered afresh for every screenshot: the shade,
taps and swipes all bring the satellite icon back. The clock is changed
to the shown dates; afterwards automatic time, the status bar and emu.restore()
are put back.
Review the PNGs before copying them to
fastlane/metadata/android/<locale>/images/phoneScreenshots/.

Usage: tools/emulator/store_shots.py en|ru OUTDIR
"""
import re
import sys
import time
from pathlib import Path

from emu import BIRTH_1976_06_01, KYOTO, LHASA, adb, nodes, prefs, language, restore, sh, start, up

LABELS = {
    "en": {"festival": r"^Saga Dawa Düchen$", "menu": r"[Mm]enu", "meditation": r"^Meditation$",
           "year": r"^Fire Horse year$", "year_of_age": r"year of age", "person": "Tenzin"},
    "ru": {"festival": r"^Сага Дава Дючен$", "menu": r"меню", "meditation": r"^Медитация$",
           "year": r"Огонь-Лошадь$", "year_of_age": r"год жизни", "person": "Тензин"},
}

# The timer's presets for shot 6, named in the listing's language; the zazen is the plan set.
ZAZEN = "10;1200,600,1800;WOOD;2;3"
PRESETS = {
    "en": f"Zazen\t{ZAZEN}\nMorning\t30;1500;WOOD;2;1",
    "ru": f"Дзадзэн\t{ZAZEN}\nУтро\t30;1500;WOOD;2;1",
}

lang, out = sys.argv[1], Path(sys.argv[2])
labels = LABELS[lang]
out.mkdir(parents=True, exist_ok=True)


def demo(command, **extras):
    args = ["am", "broadcast", "-a", "com.android.systemui.demo", "-e", "command", command]
    for k, v in extras.items():
        args += ["-e", k, v]
    adb("shell", *args)


def shot(n):
    # Taps and swipes bring the satellite icon back, and only a fresh entry
    # into demo mode hides it again.
    demo("exit")
    demo_bar()
    time.sleep(1.5)
    (out / f"{n}.png").write_bytes(adb("exec-out", "screencap", "-p", capture=True))
    print(f"== {n}")
    print("\n".join(f"  {t}\t{d}" for t, d, _, _ in nodes() if t or d))


def find(pattern, desc=False, last=False):
    hits = [(x, y) for t, d, x, y in nodes() if re.search(pattern, d if desc else t)]
    if not hits:
        sys.exit(f"not found: {pattern}")
    return hits[-1] if last else hits[0]


def tap_at(xy):
    adb("shell", "input", "tap", str(xy[0]), str(xy[1]))
    time.sleep(1.5)


def clean_status_bar():
    demo("exit")
    sh("cmd statusbar expand-notifications")
    time.sleep(1.5)
    for _, d, x, y in nodes():
        if d == "Clear all notifications.":
            tap_at((x, y))
    sh("cmd statusbar collapse")
    time.sleep(1)


def demo_bar():
    demo("enter")
    demo("clock", hhmm="0900")
    demo("battery", level="100", plugged="false")
    demo("network", wifi="hide", mobile="hide", airplane="hide", satellite="hide")
    demo("notifications", visible="false")
    demo("status", location="hide", alarm="hide", sync="hide", bluetooth="hide", volume="hide", mute="hide", zen="hide")


def day(mmddhhmm, year, calendar, place, strings=None, people=None):
    prefs(calendar, birth=None, kigaku=False, place=place, strings=strings, people=people, person=0)
    sh(f"date {mmddhhmm}{year}.00")
    clean_status_bar()
    start(7)


# Setting the clock needs a root adbd; the emulator image allows it.
adb("root")
adb("wait-for-device")
time.sleep(2)
sh("settings put global auto_time 0")
sh("settings put global sysui_demo_allowed 1")
language(lang)
try:
    day("06180900", 2027, "TIBETAN", LHASA)
    shot(1)
    # The festival's heading opens its reading.
    tap_at(find(labels["festival"]))
    shot(3)

    day("10230900", 2026, "KYUREKI", KYOTO)
    shot(2)
    # Scroll slowly, without a fling, until 中段 sits under the top bar.
    for _ in range(4):
        hits = [y for t, _, _, y in nodes() if t.startswith("中段")]
        if hits and hits[0] < 1600:
            adb("shell", "input", "swipe", "540", str(hits[0]), "540", "330", "3000")
            break
        up()
    shot(4)

    adb("shell", "input", "swipe", "540", "700", "540", "2000", "150")
    adb("shell", "input", "swipe", "540", "700", "540", "2000", "150")
    tap_at(find(labels["menu"], desc=True))
    shot(5)

    day("10230900", 2026, "KYUREKI", KYOTO, strings={"sit_plan": ZAZEN, "sit_presets": PRESETS[lang]})
    tap_at(find(labels["menu"], desc=True))
    tap_at(find(labels["meditation"]))
    shot(6)

    day("10230900", 2026, "TIBETAN", LHASA, people=[(labels["person"], BIRTH_1976_06_01, "m")])
    tap_at(find(labels["year"]))
    tap_at(find(labels["year_of_age"]))
    shot(7)
finally:
    demo("exit")
    sh("settings put global auto_time 1")
    restore()
