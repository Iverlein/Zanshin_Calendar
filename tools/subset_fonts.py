#!/usr/bin/env python3
# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at https://mozilla.org/MPL/2.0/.
"""Subset Shippori Mincho to the characters the app can show.

Shippori Mincho (SIL OFL 1.1) is ~9 MB per weight because of its kanji; the
app needs a few dozen. The subset is every character that appears in the
Kotlin sources of core/ and app/, plus printable ASCII. Rerun after adding
kanji to the sources.

Source fonts: https://github.com/google/fonts/tree/main/ofl/shipporimincho
Needs fontTools (pip install fonttools).

Usage: tools/subset_fonts.py ShipporiMincho-Medium.ttf ShipporiMincho-Bold.ttf
"""

import pathlib
import sys

from fontTools import subset

ROOT = pathlib.Path(__file__).resolve().parent.parent
OUT = ROOT / "app/src/main/res/font"


def characters():
    chars = {chr(c) for c in range(0x20, 0x7F)}
    for path in list((ROOT / "core/src/main").rglob("*.kt")) + list((ROOT / "app/src/main").rglob("*.kt")):
        if path.name == "Vsop87Earth.kt":
            continue
        chars.update(ch for ch in path.read_text(encoding="utf-8") if ord(ch) > 0x7F)
    return "".join(sorted(chars))


def main():
    text = characters()
    for source, target in zip(sys.argv[1:3], ("shippori_medium.ttf", "shippori_bold.ttf")):
        options = subset.Options()
        options.layout_features = ["*"]
        options.name_IDs = ["*"]
        options.notdef_outline = True
        font = subset.load_font(source, options)
        subsetter = subset.Subsetter(options)
        subsetter.populate(text=text)
        subsetter.subset(font)
        subset.save_font(font, str(OUT / target), options)
        print(f"{target}: {(OUT / target).stat().st_size} bytes, {len(text)} characters")


if __name__ == "__main__":
    main()
