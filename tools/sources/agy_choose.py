# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at https://mozilla.org/MPL/2.0/.
"""Settle the flags of two OCR readings by asking agy to choose between them.

  agy_choose.py OUT.json SCANDIR FLAGS.json... [--per-call N] [--dry-run]

FLAGS.json files come from disagree.py --json (Yigdzin-1 as MAIN, a second
reader as witness). For each flag agy gets the page image SCANDIR/<page>.jpg
and the flag's context, "the line that reads … [A or B] …", and answers A,
B, another reading, or unclear: a constrained choice, which leaves far less
room to invent than a free transcription (agy wrote whole lines that are not
on the page when asked to transcribe, docs/sources/PLAN.md). Several pages
go into one call (--per-call, default 4) to spare agy's small weekly quota;
--dry-run prints the prompts instead. OUT.json gets every flag with agy's
answer, appended to on each run; flags already answered are skipped.

agy's answer is a third opinion, not the scan: where it says "other" or
"unclear", or where the choice matters to a finding, look at the crop.
"""
import json
import re
import subprocess
import sys
from pathlib import Path

MODEL = "Gemini 3.1 Pro (High)"  # --model works only before -p
FRAME = (
    "These are pages of a public-domain Tibetan treatise of 1685 (Sde srid Sangs rgyas rgya mtsho, White Beryl, "
    "chapter on astrology), in the typeset Beijing edition of 1996. Two OCR programs read them and disagree at the "
    "places listed below. For each item, open the page image named, find the line that matches the context (the "
    "syllables before and after), look at the printed syllable(s) in the brackets, and say which reading is printed. "
    "Answer one line per item and nothing else, in the form\n"
    "N: A\nN: B\nN: other <the printed syllables in Tibetan>\nN: unclear\n"
    "Judge only by the print; do not choose the more usual spelling if the print differs.\n\n"
)


def key(f):
    return f"{f['page']}-{f['n']}"


def prompt(flags, scandir):
    items = []
    for i, f in enumerate(flags, 1):
        img = (Path(scandir) / f"{f['page']}.jpg").resolve()
        items.append(f"{i}. image {img}: {f['before']} [A: {f['main'] or '(nothing)'} | B: {f['witness'] or '(nothing)'}] {f['after']}")
    return FRAME + "\n".join(items)


def ask(text):
    r = subprocess.run(["agy", "--model", MODEL, "-p", text, "--print-timeout", "20m"],
                       stdin=subprocess.DEVNULL, capture_output=True, text=True, timeout=1500)
    return r.stdout + r.stderr


def parse(answer, n):
    got = {}
    for m in re.finditer(r"^\s*(\d+)\s*[:.]\s*(A|B|other\s+.+|unclear)\s*$", answer, re.M | re.I):
        i = int(m.group(1))
        if 1 <= i <= n:
            got[i] = m.group(2).strip()
    return got


def main(argv):
    per, dry = 4, "--dry-run" in argv
    argv = [a for a in argv if a != "--dry-run"]
    if "--per-call" in argv:
        i = argv.index("--per-call")
        per = int(argv[i + 1])
        argv = argv[:i] + argv[i + 2:]
    if len(argv) < 3:
        sys.exit(__doc__)
    out, scandir = Path(argv[0]), argv[1]
    done = json.loads(out.read_text()) if out.exists() else []
    seen = {key(f) for f in done}
    flags = [f for p in argv[2:] for f in json.loads(Path(p).read_text()) if key(f) not in seen]
    pages = sorted({f["page"] for f in flags})
    for s in range(0, len(pages), per):
        batch = [f for f in flags if f["page"] in pages[s:s + per]]
        text = prompt(batch, scandir)
        if dry:
            print(text, "\n")
            continue
        answer = ask(text)
        got = parse(answer, len(batch))
        for i, f in enumerate(batch, 1):
            f["agy"] = got.get(i, "no answer")
            done.append(f)
        out.write_text(json.dumps(done, ensure_ascii=False, indent=1))
        print(f"pages {pages[s:s + per]}: {len(got)}/{len(batch)} answered", flush=True)
    for f in done:
        answer = f.get("agy", "").upper()
        pick = f["main"] if answer == "A" else f["witness"] if answer == "B" else ""
        print(f"{key(f):8} {f['before']} [{f['main']} | {f['witness']}] {f['after']}  →  {f.get('agy')} {pick}")


if __name__ == "__main__":
    main(sys.argv[1:])
