# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at https://mozilla.org/MPL/2.0/.
"""Where two machine readings of the same page disagree: the places to check on the scan.

  disagree.py MAIN.txt WITNESS.txt [--context N] [--scan IMG --sheet OUT.png]
              [--scan IMG --crops DIR --json FLAGS.json]

MAIN is the better reading (Yigdzin-1's, hf_read.py), WITNESS a second one
(MITRA, or BDRC's app OCR). The two are aligned syllable by syllable; every
stretch where they differ is printed with N syllables of MAIN's text around
it, numbered, and with the MAIN line it falls on, so each can be looked up
on the scan and the reading in MAIN corrected or confirmed. Where both
agree, both can still be wrong: on the benchmark pages MITRA flags 43% of
Yigdzin's errors, BDRC Woodblock-Stacks 58%, the two together 68%
(docs/sources/PLAN.md, Tools).

With --scan, the page image is cut into its text lines (local_read.lines)
and the lines that hold a flag are stacked into OUT.png, each labelled with
its line and flag numbers, so the checks are made on one sheet. MAIN's line
breaks follow the print (the OCR models keep them); the page header falls into the
first band and the page number into the last.

Long stretches where the witness is garbage (tables, small type) are listed
too, flagged "long", since they are where MAIN is least checked; text the
witness has and MAIN lacks is flagged "missing" (Yigdzin-1 drops lines
beside woodcuts at the foot of a page: WB img. 325, a whole verse head). Stretches
that differ only in joining (a ༈ or ༧ run onto the next syllable, a tsheg
written or left out) are not flags.

With --crops and --json, every flag's scan line with its neighbours is saved as DIR/<page>-<n>.png, and the flags
are written as JSON: page, number, line, both readings, context and crop.
agy_choose.py puts them to agy as a choice between the two readings.
"""
import json
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


def sheet(scan, flagged, out, main_lines):
    sys.path.insert(0, str(Path(__file__).parent))
    from local_read import lines
    from PIL import Image, ImageDraw
    bands = lines(Image.open(scan))
    # Where the scan's lines and MAIN's do not pair one to one (a page number,
    # a heading, a woodcut), show each flagged line with its neighbours.
    spread = 0 if len(bands) == main_lines else 1
    rows, shown = [], set()
    for line in sorted(flagged):
        for n in range(line - spread, line + spread + 1):
            i = min(len(bands), max(1, n))
            if i in shown:
                continue
            shown.add(i)
            label = f"{line}: " + ",".join(map(str, flagged[line])) if n == line else f"({i})"
            rows.append((label, bands[i - 1]))
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


def joined(syllables):
    return re.sub(r"[༄-༔༠-༩་]", "", "".join(syllables))


def crops(scan, flags, outdir, main_lines):
    sys.path.insert(0, str(Path(__file__).parent))
    from local_read import lines
    from PIL import Image
    bands = lines(Image.open(scan))
    spread = 1  # a woodcut can shift the pairing even where the counts agree
    outdir = Path(outdir)
    outdir.mkdir(parents=True, exist_ok=True)
    for f in flags:
        picked = [bands[min(len(bands), max(1, n)) - 1] for n in range(f["line"] - spread, f["line"] + spread + 1)]
        width = max(b.width for b in picked)
        img = Image.new("L", (width, sum(b.height for b in picked)), 255)
        y = 0
        for b in picked:
            img.paste(b, (0, y))
            y += b.height
        img = img.resize((int(img.width * 2.5), int(img.height * 2.5)), Image.LANCZOS)
        path = outdir / f"{f['page']}-{f['n']}.png"
        img.save(path)
        f["crop"] = str(path.resolve())


def main(argv):
    scan = out = cropdir = jsonout = None
    for opt in ("--scan", "--sheet", "--crops", "--json"):
        if opt in argv:
            i = argv.index(opt)
            value = argv[i + 1]
            if opt == "--scan":
                scan = value
            elif opt == "--sheet":
                out = value
            elif opt == "--crops":
                cropdir = value
            else:
                jsonout = value
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
    flags = []
    agree = sum(bl.size for bl in sm.get_matching_blocks())
    print(f"# {argv[0]}: {len(a)} syllables, {agree} agreed by the witness ({agree / max(1, len(a)):.0%})")
    for tag, i1, i2, j1, j2 in sm.get_opcodes():
        if tag == "equal":
            continue
        if joined(a[i1:i2]) == joined(b[j1:j2]):
            continue
        k += 1
        before = "་".join(a[max(0, i1 - context):i1])
        after = "་".join(a[i2:i2 + context])
        line = main_t[i1][1] if i1 < len(main_t) else main_t[-1][1]
        size = max(i2 - i1, j2 - j1)
        # MAIN lacks what the witness has: Yigdzin-1 can drop whole lines,
        # e.g. a verse head beside a woodcut at the foot of a page.
        flag = " missing" if tag == "insert" else ""
        flag += " long" if size > 6 else ""
        flagged.setdefault(line, []).append(k)
        print(f"{k:3d}. line {line}{flag}: {before} [{'་'.join(a[i1:i2])}] {after}   witness: {'་'.join(b[j1:j2]) or '∅'}")
        flags.append({"page": Path(argv[0]).stem, "n": k, "line": line, "main": "་".join(a[i1:i2]),
                      "witness": "་".join(b[j1:j2]), "before": before, "after": after})

    if scan and out and flagged:
        sheet(scan, flagged, out, max(n for _, n in main_t))
    if scan and cropdir and flags:
        crops(scan, flags, cropdir, max(n for _, n in main_t))
    if jsonout:
        Path(jsonout).write_text(json.dumps(flags, ensure_ascii=False, indent=1))


if __name__ == "__main__":
    main(sys.argv[1:])
