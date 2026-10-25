#!/usr/bin/env python3
# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at https://mozilla.org/MPL/2.0/.
"""Render the cue glyphs of app/.../ui/CueGlyphs.kt as a contact sheet, to look at while drawing them.

Each glyph is drawn the way CueIcon draws it (24-unit grid, stroke 1.4, round caps, dots of radius 1.1),
once large and once at the size a phone shows it. A work's own glyph (CueGlyphs.WORK) stands beside
its family's, so that the two can be compared.

    tools/glyph_sheet.py                       # every work glyph beside its family's -> glyph_sheet.png
    tools/glyph_sheet.py --set family          # the family glyphs
    tools/glyph_sheet.py --only HUNTING,GAMES  # just these works
    tools/glyph_sheet.py --out /tmp/x.png --dark
"""

import argparse
import html
import re
import subprocess
import sys
import tempfile
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent
GLYPHS = ROOT / "app/src/main/kotlin/zanshin/app/ui/CueGlyphs.kt"
ACTIVITIES = ROOT / "core/src/main/kotlin/zanshin/core/texts/Activities.kt"
TEXTS = ROOT / "core/src/main/resources/texts/texts.properties"

LINE = re.compile(r"^\s*(?:(\w+)\.(\w+)|\"(\w+)\") to (.+?),?$")
CONST = re.compile(r'^\s*private (?:const )?val (\w+) = (.+)$')
GLYPH = re.compile(r'^CueGlyph\((.+?)(?:, dots = listOf\((.*)\))?\)$')
OFFSET = re.compile(r"Offset\(([-\d.]+)f, ([-\d.]+)f\)")


class Source:
    """The glyph entries of CueGlyphs.kt, each a line `Kind.NAME to <expression>`, and its private vals."""

    def __init__(self, text: str):
        self.consts = {m.group(1): m.group(2) for m in map(CONST.match, text.splitlines()) if m}
        self.entries: dict[str, dict[str, str]] = {}
        for line in text.splitlines():
            m = LINE.match(line)
            if m:
                kind = m.group(1) or "ELEMENTS"
                self.entries.setdefault(kind, {})[m.group(2) or m.group(3)] = m.group(4)

    def string(self, expr: str) -> str:
        return "".join(self.consts[t][1:-1] if t in self.consts else t[1:-1] for t in (p.strip() for p in expr.split("+")))

    def glyph(self, expr: str):
        expr = expr.strip()
        if expr in self.consts:
            return self.glyph(self.consts[expr])
        m = re.match(r'^(\w+)\.getValue\((?:\w+\.(\w+)|"(\w+)")\)$', expr)
        if m:
            kind = {"ANIMAL": "Animal", "FAMILY": "ActivityFamily"}.get(m.group(1), m.group(1))
            return self.glyph(self.entries[kind][m.group(2) or m.group(3)])
        m = GLYPH.match(expr)
        if not m:
            raise ValueError(f"cannot read glyph expression: {expr}")
        dots = [(float(x), float(y)) for x, y in OFFSET.findall(m.group(2) or "")]
        return self.string(m.group(1)), dots

    def glyphs(self, kind: str):
        return {name: self.glyph(expr) for name, expr in self.entries.get(kind, {}).items()}


def families() -> dict[str, str]:
    """Each activity's family, from the exhaustive `when` of Activities.kt."""
    body = ACTIVITIES.read_text().split("val Activity.family", 1)[1].split("\n    }", 1)[0]
    out = {}
    for lhs, fam in re.findall(r"((?:[A-Z_]+,\s*)*[A-Z_]+)\s*->\s*ActivityFamily\.(\w+)", body):
        for a in re.split(r",\s*", lhs.strip()):
            out[a] = fam
    return out


def names() -> dict[str, str]:
    out = {}
    for line in TEXTS.read_text().splitlines():
        if line.startswith("Activity.") or line.startswith("ActivityFamily."):
            key, _, value = line.partition("=")
            out[key] = value
    return out


def cell(x: float, y: float, glyph, size: float, ink: str) -> str:
    path, dots = glyph
    k = size / 24
    parts = [f'<g transform="translate({x},{y}) scale({k})">',
             f'<path d="{path}" fill="none" stroke="{ink}" stroke-width="1.4" stroke-linecap="round" stroke-linejoin="round"/>']
    parts += [f'<circle cx="{cx}" cy="{cy}" r="1.1" fill="{ink}"/>' for cx, cy in dots]
    parts.append("</g>")
    return "".join(parts)


def main() -> int:
    ap = argparse.ArgumentParser(description=__doc__.split("\n")[0])
    ap.add_argument("--set", choices=["work", "family", "animal"], default="work")
    ap.add_argument("--only", help="comma-separated keys to draw")
    ap.add_argument("--out", default="glyph_sheet.png")
    ap.add_argument("--dark", action="store_true", help="light ink on a dark page")
    ap.add_argument("--columns", type=int, default=4)
    args = ap.parse_args()

    source = Source(GLYPHS.read_text())
    fam_glyphs = source.glyphs("ActivityFamily")
    label = names()
    rows = []
    if args.set == "work":
        fam_of = families()
        for key, g in source.glyphs("Activity").items():
            rows.append((key, label.get(f"Activity.{key}", key), [fam_glyphs[fam_of[key]], g]))
    elif args.set == "family":
        rows = [(k, label.get(f"ActivityFamily.{k}", k), [g]) for k, g in fam_glyphs.items()]
    else:
        rows = [(k, k.lower(), [g]) for k, g in source.glyphs("Animal").items()]
    if args.only:
        wanted = set(args.only.split(","))
        rows = [r for r in rows if r[0] in wanted]
    if not rows:
        print("no glyphs to draw", file=sys.stderr)
        return 1

    bg, ink, faint = ("#1c1b19", "#e8e2d6", "#8a857b") if args.dark else ("#fbf8f2", "#2b2824", "#9a948a")
    big, small, pad = 96, 22, 14
    col_w = pad + 2 * (big + pad) + small + 3 * pad
    row_h = big + 44
    cols = min(args.columns, len(rows))
    height = ((len(rows) + cols - 1) // cols) * row_h + pad
    svg = [f'<svg xmlns="http://www.w3.org/2000/svg" width="{cols * col_w}" height="{height}">',
           f'<rect width="100%" height="100%" fill="{bg}"/>']
    for i, (key, name, gs) in enumerate(rows):
        x0, y0 = (i % cols) * col_w + pad, (i // cols) * row_h + pad
        for j, g in enumerate(gs):
            colour = faint if len(gs) > 1 and j == 0 else ink
            svg.append(f'<rect x="{x0 + j * (big + pad)}" y="{y0}" width="{big}" height="{big}" fill="none" stroke="{faint}" stroke-opacity=".25"/>')
            svg.append(cell(x0 + j * (big + pad), y0, g, big, colour))
        svg.append(cell(x0 + len(gs) * (big + pad), y0 + big - small, gs[-1], small, ink))
        svg.append(f'<text x="{x0}" y="{y0 + big + 18}" font-family="sans-serif" font-size="13" fill="{ink}">{html.escape(name[:38])}</text>')
    svg.append("</svg>")
    with tempfile.NamedTemporaryFile("w", suffix=".svg", delete=False) as f:
        f.write("".join(svg))
    subprocess.run(["rsvg-convert", "-o", args.out, f.name], check=True)
    Path(f.name).unlink()
    print(args.out)
    return 0


if __name__ == "__main__":
    sys.exit(main())
