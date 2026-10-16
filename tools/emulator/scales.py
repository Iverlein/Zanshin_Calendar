#!/usr/bin/env python3
# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at https://mozilla.org/MPL/2.0/.
"""The app at font scales, in landscape and at a tablet's width, for SPEC §10.1 (ROADMAP A2).

Each mode sets the emulator's font scale, rotation and size, then walks both pages (top, then two scrolls), the
hours panel, the day in brief, a mansion's sheet with its ring, the election's month for weddings, the meditation
timer, the Health screen, the date picker and the 旧暦's mansion ring, a screenshot with its text at each step
(emu.Shots). Look at every PNG for clipped or overlapping text; the tool finds nothing by itself.

Modes: s10 s13 s15 s20 (portrait at font scale 1.0 … 2.0), land, land20 (landscape at 1.0 and 2.0), tab (a tablet's
800 dp, upright: wm size 1600x2560, density 320), tabland. The font scale, rotation and size are put back at the
end, and emu.restore() with them.

Traps it handles: in landscape and on the tablet the page stands in two columns that scroll apart, so a row is
looked for by scrolling its column; the launcher is upright, and after a Back to it the rotation falls back to
portrait, so the rotation is set again before every launch; the menu's groups remember being open, so a group is
tapped only when the row in it is not already in sight.

Usage: tools/emulator/scales.py OUTDIR MODE[,MODE…] [LANG,…]   (languages en and ru; both by default)
"""
import re
import sys
import time

import emu

SCALE = {"s10": "1.0", "s13": "1.3", "s15": "1.5", "s20": "2.0", "land": "1.0", "land20": "2.0", "tab": "1.0", "tabland": "1.0"}

LABELS = {
    "en": dict(hours="^Hours", brief="^In brief", mansion="Jyeṣṭhā", menu="Open menu", calendar="^CALENDAR$",
               practice="^PRACTICE$", election="^Choose a day$", work="^Weddings", meditation="^Meditation$",
               health="^Health$", shuku="^亢宿$"),
    "ru": dict(hours="^Часы", brief="^Вкратце", mansion="Джьештха", menu="Открыть меню", calendar="^КАЛЕНДАРЬ$",
               practice="^ПРАКТИКА$", election="^Выбрать день$", work="^Свадьбы", meditation="^Медитация$",
               health="^Здоровье$", shuku="^亢宿$"),
}

size = [1080, 2400]  # the screen in pixels as the mode turns it
two_columns = [False]


def setup(mode):
    tablet = mode.startswith("tab")
    w, h = (1600, 2560) if tablet else (1080, 2400)
    land = "land" in mode
    size[:] = [h, w] if land else [w, h]
    # 600 dp: the phone (420 dpi) in landscape is 914 dp wide, the tablet (320 dpi) 800 dp either way.
    two_columns[0] = land or tablet
    emu.sh(f"settings put system font_scale {SCALE[mode]}")
    emu.sh("settings put system accelerometer_rotation 0")
    if tablet:
        emu.sh("wm size 1600x2560")
        emu.sh("wm density 320")
    else:
        emu.sh("wm size reset")
        emu.sh("wm density reset")
    rotate()


def rotate():
    emu.sh(f"settings put system user_rotation {1 if size[0] > size[1] else 0}")
    time.sleep(1)


def reset():
    emu.sh("settings put system font_scale 1.0")
    emu.sh("settings put system user_rotation 0")
    emu.sh("wm size reset")
    emu.sh("wm density reset")


def start(wait=5):
    rotate()
    emu.start(wait)


def swipe(x=None):
    w, h = size
    x = w // 2 if x is None else x
    emu.adb("shell", "input", "swipe", str(x), str(h * 3 // 4), str(x), str(h // 4), "800")
    time.sleep(0.8)


def column_x(column):
    w, _ = size
    if column == "drawer":
        return 200
    if not two_columns[0]:
        return w // 2
    return w // 4 if column == "left" else w * 3 // 4


def visible(pattern):
    return any(re.search(pattern, t) or re.search(pattern, d) for t, d, _, _ in emu.nodes())


def find_tap(pattern, column="left"):
    """Scroll [column] ("left", "right" or "drawer") until [pattern] shows away from the bars, then tap it."""
    w, h = size
    x = column_x(column)
    for _ in range(6):
        for t, d, nx, ny in emu.nodes():
            if (re.search(pattern, t) or re.search(pattern, d)) and 200 < ny < h - 250:
                emu.adb("shell", "input", "tap", str(nx), str(ny))
                time.sleep(1.2)
                return True
        emu.adb("shell", "input", "swipe", str(x), str(h * 2 // 3), str(x), str(h // 3), "800")
        time.sleep(0.8)
    print(f"not found: {pattern}")
    return False


def menu_row(l, group, row):
    """A row of the menu, its group opened only when the row is not in sight."""
    emu.tap(l["menu"], desc=True)
    if not visible(l[row]):
        find_tap(l[group], "drawer")
    find_tap(l[row], "drawer")


def walk(mode, lang, out):
    l = LABELS[lang]
    people = [("Tenzin", emu.BIRTH_1976_06_01, "m"), ("Yuki", 9000, "f")]
    for calendar in ["TIBETAN", "KYUREKI"]:
        emu.prefs(calendar, people=people, person=0)
        emu.language(lang)
        shots = emu.Shots(f"{out}/{mode}-{lang}-{calendar.lower()}")
        start()
        shots("top")
        for column in ["left", "right"] if two_columns[0] else ["left"]:
            swipe(column_x(column))
            shots(f"{column}-scrolled")
            swipe(column_x(column))
            swipe(column_x(column))
            shots(f"{column}-further")

    emu.prefs("TIBETAN", people=people, person=0)
    shots = emu.Shots(f"{out}/{mode}-{lang}-screens")
    start()
    find_tap(l["hours"], "left")
    shots("hours")
    swipe()
    shots("hours-2")
    emu.back()
    find_tap(l["brief"], "left")
    shots("brief")
    swipe()
    shots("brief-2")
    emu.back()
    find_tap(l["mansion"], "right")
    shots("mansion")
    swipe()
    swipe()
    shots("mansion-2")
    emu.back()
    # The header's date button: the first figure in the top bar.
    top = [n for n in emu.nodes() if re.search(r"\d", n[0]) and n[3] < 400]
    if top:
        emu.adb("shell", "input", "tap", str(top[0][2]), str(top[0][3]))
        shots("picker")
        emu.back()

    menu_row(l, "calendar", "election")
    emu.tap(l["work"])
    # The family opens its works; the first is the work of the family's name.
    hits = [n for n in emu.nodes() if re.search(l["work"], n[0])]
    if len(hits) > 1:
        emu.adb("shell", "input", "tap", str(hits[1][2]), str(hits[1][3]))
    time.sleep(5)
    shots("election")
    swipe()
    shots("election-2")
    emu.back()
    emu.back()

    start(3)
    menu_row(l, "practice", "meditation")
    shots("meditation")
    swipe()
    shots("meditation-2")
    emu.back()

    start(3)
    menu_row(l, "calendar", "health")
    shots("health")
    emu.back()

    emu.prefs("KYUREKI", people=people, person=0)
    start()
    find_tap(l["shuku"], "right")
    shots("shuku")
    swipe()
    shots("shuku-2")
    emu.back()


def main():
    out, modes = sys.argv[1], sys.argv[2].split(",")
    langs = sys.argv[3].split(",") if len(sys.argv) > 3 else ["en", "ru"]
    try:
        for mode in modes:
            setup(mode)
            for lang in langs:
                walk(mode, lang, out)
    finally:
        reset()
        emu.restore()


if __name__ == "__main__":
    main()
