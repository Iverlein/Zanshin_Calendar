# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at https://mozilla.org/MPL/2.0/.
"""Transcribe page images with agy (Antigravity CLI, Gemini), one call per page.

  agy_read.py OUTDIR IMG...          typeset pages (the 1996 White Beryl)
  agy_read.py --prompt FILE OUTDIR IMG...
                                     any other instruction; {path} in FILE
                                     is replaced by the image's path

Writes OUTDIR/<image stem>.txt and skips images already done, so several
runs over disjoint lists can go in parallel (one page takes 5–15 minutes).
When the model refuses a whole page or times out, the page is retried as
two overlapping halves, <stem>a.txt and <stem>b.txt.

What agy is good for, measured 2026-10-03 (see docs/sources/README.md):
typeset Tibetan comes out nearly clean, far better than the BDRC OCR;
woodblock tables do not — on abbreviated mansion lists it wrote the
standard order of the mansions instead of the print. Its text is a witness
to be checked on the scan, never a quotation by itself.
"""
import subprocess
import sys
from pathlib import Path

from PIL import Image

MODEL = "Gemini 3.1 Pro (High)"  # --model works only before -p
TYPESET = (
    "This is a page of a public-domain Tibetan treatise of 1685 (Sde srid Sangs rgyas rgya mtsho, "
    "White Beryl, chapter on astrology), in the typeset Beijing edition of 1996, needed for a "
    "philological comparison against an OCR text. Transcribe the Tibetan text of {path} into Tibetan "
    "Unicode exactly as printed: keep shad, tsheg, the bracketed numbers and the line breaks. Do not "
    "correct spelling and do not fill anything in from memory; mark a syllable you cannot read as […]. "
    "Print only the transcription."
)
REFUSALS = ("unable to provide", "I can't", "I cannot", "I apologize", "Error: timeout")


def agy(prompt, cwd):
    r = subprocess.run(
        ["agy", "--model", MODEL, "-p", prompt, "--print-timeout", "20m"],
        stdin=subprocess.DEVNULL, capture_output=True, text=True, cwd=cwd, timeout=1500,
    )
    return r.stdout + r.stderr


def refused(text):
    return any(s in text for s in REFUSALS) or len(text.strip()) < 200


def page(src, out, template):
    stem = src.stem
    if (out / f"{stem}.txt").exists() or (out / f"{stem}b.txt").exists():
        return
    text = agy(template.format(path=src.resolve()), out)
    if not refused(text):
        (out / f"{stem}.txt").write_text(text)
        print(stem, "whole", flush=True)
        return
    im = Image.open(src)
    w, h = im.size
    for part, box in (("a", (0, 0, w, h * 55 // 100)), ("b", (0, h * 45 // 100, w, h))):
        half = out / f"{stem}{part}.png"
        im.crop(box).save(half)
        text = agy(template.format(path=half.resolve()), out)
        (out / f"{stem}{part}.txt").write_text(text)
        print(stem, part, "refused" if refused(text) else "ok", flush=True)


def main(argv):
    template = TYPESET
    if argv[:1] == ["--prompt"]:
        template = Path(argv[1]).read_text()
        argv = argv[2:]
    if len(argv) < 2:
        sys.exit(__doc__)
    out = Path(argv[0])
    out.mkdir(parents=True, exist_ok=True)
    for img in argv[1:]:
        page(Path(img), out, template)


if __name__ == "__main__":
    main(sys.argv[1:])
