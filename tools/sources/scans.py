# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at https://mozilla.org/MPL/2.0/.
"""Crops and contact sheets for reading page scans by eye.

  scans.py crop IMG X0 Y0 X1 Y1 [--scale S] [-o OUT]
      one region, in the image's own pixels, enlarged (default 2×), greyscale
  scans.py row OUT X0 Y0 X1 Y1 IMG... [--scale S]
      the same region of several images side by side: the heading cells of
      a run of folios, or one word wherever it occurs, to compare glyphs
  scans.py stack OUT IMG... [--box X0 Y0 X1 Y1] [--width W]
      whole images (or one region of each) one under another, labelled with
      their file names: a few folios per view
  scans.py line IMG N [--at F] [--half W] [--scale S] [-o OUT]
      the N-th text line of a page (1-based, found by the rows' ink), around
      the fraction F of its width (default the whole line): where an OCR
      file puts a word (its line number and its offset over the line's
      length), to read it on the scan; prints the number of lines found

Small woodblock scans (KP folios are 2550 px wide) need crops enlarged 3–6×
before a vowel sign can be told apart; see docs/sources/mansions.md for the
ཆུཾད/ཆོད and ཁྲུཾད/ཁྲོད cases. Needs Pillow.
"""
import argparse
from pathlib import Path

from PIL import Image, ImageDraw


def grey(path):
    return Image.open(path).convert("L")


def enlarge(im, scale):
    return im.resize((int(im.width * scale), int(im.height * scale)), Image.LANCZOS)


def crop(args):
    out = args.o or f"{Path(args.img).stem}_crop.png"
    enlarge(grey(args.img).crop(tuple(args.box)), args.scale).save(out)
    print(out)


def row(args):
    tiles = [enlarge(grey(p).crop(tuple(args.box)), args.scale) for p in args.imgs]
    gap = 20
    sheet = Image.new("L", (sum(t.width for t in tiles) + gap * len(tiles), max(t.height for t in tiles)), 255)
    x = 0
    for t in tiles:
        sheet.paste(t, (x, 0))
        x += t.width + gap
    sheet.save(args.out)
    print(args.out, sheet.size)


def stack(args):
    tiles = []
    for p in args.imgs:
        im = grey(p)
        if args.box:
            im = im.crop(tuple(args.box))
        im = im.resize((args.width, int(im.height * args.width / im.width)), Image.LANCZOS)
        tiles.append((Path(p).stem, im))
    margin, gap = 110, 20
    sheet = Image.new("L", (args.width + margin, sum(t.height + gap for _, t in tiles)), 255)
    draw = ImageDraw.Draw(sheet)
    y = 0
    for name, t in tiles:
        sheet.paste(t, (margin, y))
        draw.text((8, y + t.height // 2), name, fill=0)
        y += t.height + gap
    sheet.save(args.out)
    print(args.out, sheet.size)


def text_lines(im, min_ink=0.01, min_height=15):
    """The (top, bottom) rows of each text line: runs of rows with ink on more than min_ink of the width."""
    w, h = im.size
    px = im.load()
    rows = [sum(1 for x in range(0, w, 2) if px[x, y] < 128) * 2 > w * min_ink for y in range(h)]
    lines, top = [], None
    for y, inked in enumerate(rows + [False]):
        if inked and top is None:
            top = y
        elif not inked and top is not None:
            if y - top > min_height:
                lines.append((top, y))
            top = None
    return lines


def line(args):
    im = grey(args.img)
    lines = text_lines(im)
    top, bottom = lines[args.n - 1]
    px = im.load()
    cols = [x for x in range(im.width) if any(px[x, y] < 128 for y in range(top, bottom, 3))]
    x0, x1 = cols[0], cols[-1]
    if args.at is not None:
        mid = int(x0 + args.at * (x1 - x0))
        x0, x1 = max(0, mid - args.half), min(im.width, mid + args.half)
    out = args.o or f"{Path(args.img).stem}_line{args.n}.png"
    enlarge(im.crop((x0, max(0, top - 25), x1, min(im.height, bottom + 25))), args.scale).save(out)
    print(out, len(lines), "lines")


def main():
    ap = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    sub = ap.add_subparsers(dest="cmd", required=True)
    c = sub.add_parser("crop")
    c.add_argument("img")
    c.add_argument("box", nargs=4, type=int)
    c.add_argument("--scale", type=float, default=2)
    c.add_argument("-o")
    c.set_defaults(fn=crop)
    r = sub.add_parser("row")
    r.add_argument("out")
    r.add_argument("box", nargs=4, type=int)
    r.add_argument("imgs", nargs="+")
    r.add_argument("--scale", type=float, default=1.4)
    r.set_defaults(fn=row)
    s = sub.add_parser("stack")
    s.add_argument("out")
    s.add_argument("imgs", nargs="+")
    s.add_argument("--box", nargs=4, type=int)
    s.add_argument("--width", type=int, default=2000)
    s.set_defaults(fn=stack)
    l = sub.add_parser("line")
    l.add_argument("img")
    l.add_argument("n", type=int)
    l.add_argument("--at", type=float)
    l.add_argument("--half", type=int, default=350)
    l.add_argument("--scale", type=float, default=1)
    l.add_argument("-o")
    l.set_defaults(fn=line)
    args = ap.parse_args()
    args.fn(args)


if __name__ == "__main__":
    main()
