# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at https://mozilla.org/MPL/2.0/.
"""Where two machine readings of the same page disagree: the places to check on the scan.

  disagree.py MAIN.txt WITNESS.txt [--context N] [--scan IMG --sheet OUT.png]

MAIN is the better reading (agy's), WITNESS an independent one (BDRC OCR).
The two are aligned syllable by syllable; every stretch where they differ is
printed with N syllables of MAIN's text around it, numbered, and with the
MAIN line it falls on, so each can be looked up on the scan and the reading
in MAIN corrected or confirmed. On a test page every error of agy's fell on
such a stretch (docs/sources/PLAN.md, Tools); where both agree, both can
still be wrong, but far more rarely.

With --scan, the page image is cut into its text lines (local_read.lines)
and the lines that hold a flag are stacked into OUT.png, each labelled with
its line and flag numbers, so the checks are made on one sheet. MAIN's line
breaks follow the print (agy keeps them); the page header falls into the
first band and the page number into the last.

Long stretches where the witness is garbage (tables, small type) are listed
too, flagged "long", since they are where MAIN is least checked.
"""
import difflib
import re
import sys
from pathlib import Path


def tokens(text):
    """Syllables with the line of the text each one is on."""
    out = []
    for n, line in enumerate(text.splitlines(), 1):
        line = re.sub(r"[༵༷]", "", line)
        for s in re.sub(r"[།༎༏༐༑༔་\s]+", " ", line).split():
            if re.search(r"[ཀ-ྼ]", s):
                out.append((s, n))
    return out


def sheet(scan, flagged, out):
    sys.path.insert(0, str(Path(__file__).parent))
    from local_read import lines
    from PIL import Image, ImageDraw
    bands = lines(Image.open(scan))
    rows = []
    for line in sorted(flagged):
        band = bands[min(len(bands), max(1, line)) - 1]
        rows.append((f"{line}: " + ",".join(map(str, flagged[line])), band))
    margin, gap = 150, 8
    width = max(b.width for _, b in rows) + margin
    img = Image.new("L", (width, sum(b.height + gap for _, b in rows)), 255)
    draw = ImageDraw.Draw(img)
    y = 0
    for label, band in rows:
        img.paste(band, (margin, y))
        draw.text((6, y + band.height // 2 - 5), label, fill=0)
        y += band.height + gap
    img.save(out)
    print("# sheet:", out, img.size)


def main(argv):
    scan = out = None
    for opt in ("--scan", "--sheet"):
        if opt in argv:
            i = argv.index(opt)
            if opt == "--scan":
                scan = argv[i + 1]
            else:
                out = argv[i + 1]
            argv = argv[:i] + argv[i + 2:]
    context = 3
    if "--context" in argv:
        i = argv.index("--context")
        context = int(argv[i + 1])
        argv = argv[:i] + argv[i + 2:]
    if len(argv) != 2:
        sys.exit(__doc__)
    main_t = tokens(Path(argv[0]).read_text())
    wit_t = tokens(Path(argv[1]).read_text())
    a = [s for s, _ in main_t]
    b = [s for s, _ in wit_t]
    sm = difflib.SequenceMatcher(None, a, b, autojunk=False)
    k = 0
    flagged = {}
    agree = sum(bl.size for bl in sm.get_matching_blocks())
    print(f"# {argv[0]}: {len(a)} syllables, {agree} agreed by the witness ({agree / max(1, len(a)):.0%})")
    for tag, i1, i2, j1, j2 in sm.get_opcodes():
        if tag == "equal" or (tag == "insert"):
            continue
        k += 1
        before = "་".join(a[max(0, i1 - context):i1])
        after = "་".join(a[i2:i2 + context])
        line = main_t[i1][1] if i1 < len(main_t) else main_t[-1][1]
        size = max(i2 - i1, j2 - j1)
        flag = " long" if size > 6 else ""
        flagged.setdefault(line, []).append(k)
        print(f"{k:3d}. line {line}{flag}: {before} [{'་'.join(a[i1:i2])}] {after}   witness: {'་'.join(b[j1:j2]) or '∅'}")

    if scan and out and flagged:
        sheet(scan, flagged, out)


if __name__ == "__main__":
    main(sys.argv[1:])
