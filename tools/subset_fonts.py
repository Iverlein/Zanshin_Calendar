#!/usr/bin/env python3
# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at https://mozilla.org/MPL/2.0/.
"""Subset the bundled fonts to the characters the app can show.

Shippori Mincho (SIL OFL 1.1) is ~9 MB per weight because of its kanji; the
app needs a few dozen. The subset is every character that appears in the
Kotlin sources of core/ and app/, plus printable ASCII. Rerun after adding
kanji to the sources.

Noto Serif Tibetan (SIL OFL 1.1) is a variable font of about 2 MB. Its
Tibetan is generated at run time from Wylie (core/.../tibetan/Ewts.kt), so it
never appears in the sources: the subset keeps the whole Tibetan block, with
every layout feature, since stacked letters need shaping. The weight axis is
pinned to one instance.

Source fonts: https://github.com/google/fonts/tree/main/ofl/shipporimincho
and https://github.com/google/fonts/tree/main/ofl/notoseriftibetan
Needs fontTools (pip install fonttools).

Usage: tools/subset_fonts.py ShipporiMincho-Medium.ttf ShipporiMincho-Bold.ttf
       tools/subset_fonts.py --tibetan "NotoSerifTibetan[wght].ttf"
"""

import argparse
import pathlib

from fontTools import subset
from fontTools.ttLib import TTFont
from fontTools.varLib import instancer

TIBETAN_BLOCK = "".join(chr(c) for c in range(0x0F00, 0x1000))
TIBETAN_WEIGHT = 500

ROOT = pathlib.Path(__file__).resolve().parent.parent
OUT = ROOT / "app/src/main/res/font"


def characters():
    chars = {chr(c) for c in range(0x20, 0x7F)}
    for path in list((ROOT / "core/src/main").rglob("*.kt")) + list((ROOT / "app/src/main").rglob("*.kt")):
        if path.name == "Vsop87Earth.kt":
            continue
        chars.update(ch for ch in path.read_text(encoding="utf-8") if ord(ch) > 0x7F)
    return "".join(sorted(chars))


def subset_to(font, text, target):
    options = subset.Options()
    options.layout_features = ["*"]
    options.name_IDs = ["*"]
    options.notdef_outline = True
    subsetter = subset.Subsetter(options)
    subsetter.populate(text=text)
    subsetter.subset(font)
    subset.save_font(font, str(OUT / target), options)
    print(f"{target}: {(OUT / target).stat().st_size} bytes, {len(text)} characters")


def main():
    parser = argparse.ArgumentParser(description=__doc__.split("\n")[0])
    parser.add_argument("mincho", nargs="*", help="Shippori Mincho Medium and Bold")
    parser.add_argument("--tibetan", help="Noto Serif Tibetan variable font")
    args = parser.parse_args()
    if args.mincho:
        text = characters()
        for source, target in zip(args.mincho, ("shippori_medium.ttf", "shippori_bold.ttf")):
            subset_to(TTFont(source), text, target)
    if args.tibetan:
        font = instancer.instantiateVariableFont(TTFont(args.tibetan), {"wght": TIBETAN_WEIGHT})
        subset_to(font, " " + TIBETAN_BLOCK, "noto_serif_tibetan.ttf")


if __name__ == "__main__":
    main()
