# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at https://mozilla.org/MPL/2.0/.
"""Score machine readings of a Tibetan page against a reading checked on the scan.

  score_reading.py TRUTH.txt READING.txt...

TRUTH holds a passage read by eye on the scan (it may be part of the page);
each READING is a whole-page transcription. The passage is located in the
reading, and the syllable error rate over it is printed (substitutions,
insertions and deletions of tsheg-separated syllables, divided by the length
of the passage), with the differing syllables, so that a reader's habits show.
Punctuation (shad) and spaces are ignored. Used on 2026-10-03 to choose
between agy models and local OCR for the White Beryl (docs/sources/PLAN.md).
"""
import difflib
import re
import sys
from pathlib import Path


def syllables(text):
    text = re.sub(r"[\u0f35\u0f37]", "", text)  # under-marks some OCR models add; not in the prints
    text = re.sub(r"[།༎༏༐༑༔་\s]+", " ", text)
    return [s for s in text.split() if re.search(r"[ཀ-ྼ]", s)]


def locate(truth, reading):
    """The stretch of the reading that matches the passage: from its first to its last matched syllable."""
    sm = difflib.SequenceMatcher(None, truth, reading, autojunk=False)
    blocks = [b for b in sm.get_matching_blocks() if b.size >= 2]
    if not blocks:
        return []
    return reading[blocks[0].b - blocks[0].a: blocks[-1].b + blocks[-1].size + (len(truth) - blocks[-1].a - blocks[-1].size)]


def errors(truth, part):
    sm = difflib.SequenceMatcher(None, truth, part, autojunk=False)
    count, diffs = 0, []
    for tag, i1, i2, j1, j2 in sm.get_opcodes():
        if tag != "equal":
            count += max(i2 - i1, j2 - j1)
            diffs.append(f"{'་'.join(truth[i1:i2]) or '∅'} → {'་'.join(part[j1:j2]) or '∅'}")
    return count, diffs


def main(argv):
    if len(argv) < 2:
        sys.exit(__doc__)
    truth = syllables(Path(argv[0]).read_text())
    for path in argv[1:]:
        part = locate(truth, syllables(Path(path).read_text()))
        n, diffs = errors(truth, part)
        print(f"{path}: {n}/{len(truth)} syllables wrong, error rate {n / len(truth):.1%}")
        for d in diffs[:40]:
            print("   ", d)


if __name__ == "__main__":
    main(sys.argv[1:])
