#!/usr/bin/env python3
# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at https://mozilla.org/MPL/2.0/.
"""Subset the website's Cyrillic fonts to the Russian alphabet.

Figtree and the app's Shippori Mincho subset have no Cyrillic, so the Russian
pages of website/ take it from two more fonts, both SIL OFL 1.1: Onest beside
Figtree for text, PT Serif beside Shippori Mincho for headings. The site loads
them with a unicode-range, so a page without Cyrillic never downloads them.
Onest keeps its weight axis from 400 to 600, the weights the site uses.

PT Serif's licence reserves the names "PT Sans", "PT Serif" and "ParaType",
and under the OFL a subset is a Modified Version, which may not carry them: the
subset is renamed Zanshin Serif Cyrillic. Onest reserves no name.

Source fonts: https://github.com/google/fonts/tree/main/ofl/onest
and https://github.com/google/fonts/tree/main/ofl/ptserif
Needs fontTools and brotli (pip install fonttools brotli).

Usage: tools/subset_web_fonts.py "Onest[wght].ttf" PT_Serif-Web-Regular.ttf
"""

import argparse
import pathlib

from fontTools import subset
from fontTools.ttLib import TTFont
from fontTools.varLib import instancer

ROOT = pathlib.Path(__file__).resolve().parent.parent
OUT = ROOT / "website/static/fonts"

# The Russian alphabet with Ё, Ukrainian Ґ, and №; keep in step with the
# unicode-range in website/assets/css/site.css.
CYRILLIC = [*range(0x0400, 0x0460), 0x0490, 0x0491, 0x2116]


def rename(font, family):
    """Replace the family, full, PostScript and unique names; copyright and licence stay."""
    postscript = family.replace(" ", "") + "-Regular"
    names = {1: family, 3: postscript, 4: f"{family} Regular", 6: postscript, 16: family}
    for record in list(font["name"].names):
        if record.nameID in names:
            record.string = names[record.nameID]
        elif record.nameID == 17:
            record.string = "Regular"
    return font


def subset_to(font, target):
    options = subset.Options()
    options.flavor = "woff2"
    options.layout_features = ["*"]
    options.name_IDs = ["*"]
    options.notdef_outline = True
    subsetter = subset.Subsetter(options)
    subsetter.populate(unicodes=CYRILLIC)
    subsetter.subset(font)
    subset.save_font(font, str(OUT / target), options)
    print(f"{target}: {(OUT / target).stat().st_size} bytes")


def main():
    parser = argparse.ArgumentParser(description=__doc__.split("\n")[0])
    parser.add_argument("onest", help="Onest variable font")
    parser.add_argument("ptserif", help="PT Serif Regular")
    args = parser.parse_args()
    onest = instancer.instantiateVariableFont(TTFont(args.onest), {"wght": (400, 600)})
    subset_to(onest, "onest-cyrillic.woff2")
    subset_to(rename(TTFont(args.ptserif), "Zanshin Serif Cyrillic"), "zanshin-serif-cyrillic.woff2")


if __name__ == "__main__":
    main()
