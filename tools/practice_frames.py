#!/usr/bin/env python3
# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at https://mozilla.org/MPL/2.0/.
"""The painted frames of the practice days (SPEC §10.3, ROADMAP T6.1) into the app.

The paintings are kept in art/practice/ as they came, in lossless WebP, each
with an alpha channel or on a flat key colour (magenta by default). The script
keys the background out (removing the key's spill from the soft edges), takes
the part of the painting its frame uses, clears the plain disc round the
numeral to a faint glow, and writes
app/src/main/res/drawable-nodpi/practice_<name>.webp; ``sheet`` renders a
contact sheet of the results on four page colours with the numeral in each.

    python3 tools/practice_frames.py cut art/practice [--key ff00ff]
    python3 tools/practice_frames.py sheet <out.png> [--src <dir of webp>]
"""

from __future__ import annotations

import argparse
import sys
from pathlib import Path

import numpy as np
from PIL import Image, ImageChops, ImageDraw, ImageFilter, ImageFont

ROOT = Path(__file__).resolve().parent.parent
OUT = ROOT / "app/src/main/res/drawable-nodpi"
MINCHO = ROOT / "app/src/main/res/font"  # Shippori Mincho, the numeral's face

SCALE = 3  # the pictures are written at 3× their size in dp
# name: (the part of the painting taken, in its pixels; width and height in dp; the numeral's window in dp).
# The six large paintings share one geometry (1024 × 896, the inner disc x 251–772, y 221–664): the window is the
# disc less a margin, so that a two-digit numeral's cap box stands in it with room round it.
LARGE = ((96, 14, 928, 873), 250, 258, (54, 72, 196, 186))
FRAMES = {
    "8": LARGE,
    "10": LARGE,
    "15": LARGE,
    "25": LARGE,
    "29": LARGE,
    "30": LARGE,
    # The small days' wreath (966 × 976 of its painting, the disc x 135–884, y 155–906): a window as large a part of
    # its disc as the large frames', so the numeral stands as large and the wreath round it is narrower.
    "small": ((27, 23, 993, 999), 170, 172, (26, 41, 144, 137)),
}
PAGES = {"Ink": (0x11, 0x13, 0x11), "Night": (0x00, 0x00, 0x00), "Maroon": (0x3A, 0x11, 0x17), "Paper": (0xF5, 0xF1, 0xE8)}
GLOW = 0.14  # how much of the painted disc is kept round the numeral, as a glow


def key_out(img: Image.Image, key: tuple[int, int, int]) -> Image.Image:
    """The picture with its key colour cut away, the key's spill taken out of the soft edges."""
    img = img.convert("RGBA")
    if img.getextrema()[3][0] < 250:  # a real alpha channel: keep it
        return img
    rgb = np.asarray(img, dtype=np.float32)[..., :3]
    k = np.array(key, dtype=np.float32)
    # How far a pixel is from the key decides its cover: the key and its JPEG noise go, a mixed edge pixel stays part.
    d = np.abs(rgb - k).max(axis=2)
    a = np.clip((d - 40) / 110, 0, 1)
    # Unmix the key: the pixel is a·colour + (1−a)·key.
    safe = np.maximum(a, 1e-3)[..., None]
    colour = np.clip((rgb - (1 - a)[..., None] * k) / safe, 0, 255)
    out = Image.fromarray(np.dstack([colour, a * 255]).round().astype(np.uint8), "RGBA")
    # A pixel ring of left-over fringe goes with a one-pixel erosion of the alpha.
    out.putalpha(out.getchannel("A").filter(ImageFilter.MinFilter(3)))
    return out


def fit(img: Image.Image, crop: tuple[int, int, int, int] | None, w: int, h: int) -> Image.Image:
    """The part [crop] of [img] (its painted part when None) scaled to [w]×[h]."""
    if crop is None:
        crop = img.getchannel("A").point(lambda v: 255 if v > 24 else 0).getbbox() or (0, 0, *img.size)
    return img.crop(crop).resize((w, h), Image.LANCZOS)


RAYS = 720


def disc_radii(img: Image.Image, window: tuple[int, int, int, int]) -> np.ndarray:
    """The plain disc round the numeral's window, as the length of rays from the window's centre.

    Each ray stops at the first contour it meets, the first pixel darker than the disc at the centre: the rim's gold
    line, the seat's edge. A ray may slip through a gap in a line; [cut] takes the median of the paintings that share
    one geometry, and the smallest radius among neighbouring rays, so that such a ray is pulled back.
    """
    x0, y0, x1, y1 = (v * SCALE for v in window)
    cx, cy = (x0 + x1) / 2, (y0 + y1) / 2
    light = np.asarray(img.convert("L").filter(ImageFilter.GaussianBlur(1.5)), dtype=np.float32)
    h, w = light.shape
    base = light[int(cy), int(cx)]
    radii = np.zeros(RAYS)
    for i in range(RAYS):
        th = 2 * np.pi * i / RAYS
        dx, dy = np.cos(th), np.sin(th)
        r = 1.0
        while True:
            x, y = int(cx + dx * r), int(cy + dy * r)
            if not (0 <= x < w and 0 <= y < h) or light[y, x] < base - 25:
                break
            r += 1
        radii[i] = r
    return radii


def clear_disc(img: Image.Image, window: tuple[int, int, int, int], radii: np.ndarray) -> Image.Image:
    """[img] with the disc of [radii] turned to a faint glow of its own colour, its edge feathered."""
    k = 9
    padded = np.concatenate([radii[-k:], radii, radii[:k]])
    radii = np.array([padded[i:i + 2 * k + 1].min() for i in range(RAYS)]) - SCALE
    x0, y0, x1, y1 = (v * SCALE for v in window)
    cx, cy = (x0 + x1) / 2, (y0 + y1) / 2
    mask = Image.new("L", img.size, 0)
    points = [(cx + np.cos(2 * np.pi * i / RAYS) * r, cy + np.sin(2 * np.pi * i / RAYS) * r) for i, r in enumerate(radii)]
    ImageDraw.Draw(mask).polygon(points, fill=255)
    mask = mask.filter(ImageFilter.GaussianBlur(SCALE))
    cap = mask.point(lambda m: round(255 - m * (1 - GLOW)))
    img = img.copy()
    img.putalpha(ImageChops.darker(img.getchannel("A"), cap))
    return img


def cut(src: Path, key: tuple[int, int, int]) -> None:
    OUT.mkdir(parents=True, exist_ok=True)
    fitted = {}
    for name, (crop, w, h, window) in FRAMES.items():
        path = next((src / f"practice_{name}{ext}" for ext in (".png", ".webp") if (src / f"practice_{name}{ext}").exists()), None)
        if path is None:
            print(f"missing practice_{name} in {src}", file=sys.stderr)
            continue
        fitted[name] = fit(key_out(Image.open(path), key), crop, w * SCALE, h * SCALE)
    # The paintings of one geometry share one disc: the median of their rays.
    shapes: dict[tuple, list] = {}
    for name, img in fitted.items():
        shapes.setdefault(FRAMES[name], []).append(disc_radii(img, FRAMES[name][3]))
    for name, img in fitted.items():
        window = FRAMES[name][3]
        img = clear_disc(img, window, np.median(shapes[FRAMES[name]], axis=0))
        dest = OUT / f"practice_{name}.webp"
        img.save(dest, "WEBP", quality=88, method=6, alpha_quality=100)
        print(f"{dest.relative_to(ROOT)}  {dest.stat().st_size // 1024} KB")


def numeral_font(size: int) -> ImageFont.FreeTypeFont:
    return ImageFont.truetype(str(MINCHO / "shippori_medium.ttf"), size)


def sheet(dest: Path, src: Path) -> None:
    """The frames in rows, one row per page colour, with the day's numeral in each window."""
    names = list(FRAMES)
    cell_w, cell_h = max(f[1] for f in FRAMES.values()) * SCALE + 40, max(f[2] for f in FRAMES.values()) * SCALE + 40
    out = Image.new("RGB", (cell_w * len(names), cell_h * len(PAGES)))
    for row, (page, bg) in enumerate(PAGES.items()):
        text = (0xEC, 0xE8, 0xDF) if page != "Paper" else (0x1F, 0x1D, 0x19)
        for col, name in enumerate(names):
            _, w, h, (x0, y0, x1, y1) = FRAMES[name]
            if not (src / f"practice_{name}.webp").exists():
                continue
            cell = Image.new("RGBA", (cell_w, cell_h), (*bg, 255))
            img = Image.open(src / f"practice_{name}.webp").convert("RGBA")
            ox, oy = (cell_w - img.width) // 2, (cell_h - img.height) // 2
            cell.alpha_composite(img, (ox, oy))
            label = {"small": "9", "30": "30"}.get(name, name)
            d = ImageDraw.Draw(cell)
            # The numeral as the app sets it: its cap box fills the window's height, its baseline on the window's foot.
            size = round((y1 - y0) * SCALE / 0.72)  # 156 sp in the app, its cap box 0.72 of it
            d.text((ox + (x0 + x1) / 2 * SCALE, oy + y1 * SCALE), label, font=numeral_font(size), fill=text, anchor="ms")
            out.paste(cell.convert("RGB"), (col * cell_w, row * cell_h))
    out.save(dest)
    print(dest)


def main() -> None:
    p = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    sub = p.add_subparsers(dest="cmd", required=True)
    c = sub.add_parser("cut", help="key, fit and write the frames into the app")
    c.add_argument("src", type=Path)
    c.add_argument("--key", default="ff00ff", help="the flat background colour, hex")
    s = sub.add_parser("sheet", help="a contact sheet on the four page colours")
    s.add_argument("dest", type=Path)
    s.add_argument("--src", type=Path, default=OUT)
    a = p.parse_args()
    if a.cmd == "cut":
        cut(a.src, tuple(int(a.key[i:i + 2], 16) for i in (0, 2, 4)))
    else:
        sheet(a.dest, a.src)


if __name__ == "__main__":
    main()
