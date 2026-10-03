# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at https://mozilla.org/MPL/2.0/.
"""Compare Tibetan OCR readers on pages with a known transcription.

  ocr_bench.py pick PARQUET OUTDIR [--per N] [--kinds blockprints,digital_fonts,...]
  ocr_bench.py score TRUTHDIR READINGDIR... [-v]

`pick` takes pages of the open BDRC Tibetan OCR benchmark
(huggingface.co/datasets/BDRC/tibetan-ocr-benchmark, CC0; download
data/test-00000-of-00001.parquet) and writes, for N pages of each
technology asked for, <id>.jpg and its ground truth <id>.gt.txt. Only
Uchen pages are taken: the sources read here are all dbu can. Needs pandas
and pyarrow.

`score` reads every <stem>.gt.txt in TRUTHDIR and, for each READINGDIR,
the reading <stem>.txt beside it, and prints on average, by kind of page
(the stem's prefix before the first '-') and with -v per page
- CER as the benchmark computes it (whitespace stripped, repeated tsheg
  folded, the placeholders K O B I S dropped; the benchmark also runs
  botok's normalisation, left out here), so its leaderboard
  (huggingface.co/spaces/BDRC/tibetan-ocr-leaderboard) is roughly
  comparable;
- the syllable error rate, the measure score_reading.py uses.
A missing reading counts as CER 1. A truth file may hold only part of its
page (a passage read by eye): the reading is then cut to the matching
stretch first, as in score_reading.py.
"""
import re
import statistics
import sys
from pathlib import Path

sys.path.insert(0, str(Path(__file__).parent))
from score_reading import errors, locate, syllables  # noqa: E402


def normal(text):
    text = re.sub(r"[༵༷]", "", text)
    text = re.sub(r"\s+", "", text)
    text = re.sub(r"་+", "་", text)
    return re.sub(r"[KOBIS]", "", text)


def levenshtein(a, b):
    if len(a) < len(b):
        a, b = b, a
    prev = list(range(len(b) + 1))
    for i, ca in enumerate(a, 1):
        cur = [i]
        for j, cb in enumerate(b, 1):
            cur.append(min(prev[j] + 1, cur[j - 1] + 1, prev[j - 1] + (ca != cb)))
        prev = cur
    return prev[-1]


def cer(truth, reading, partial):
    t = normal(truth)
    r = normal(reading)
    if partial:
        # Cut the reading to the passage the truth covers, by syllables.
        ts = syllables(truth)
        r = normal("་".join(locate(ts, syllables(reading))))
        t = normal("་".join(ts))
    return min(1.0, levenshtein(t, r) / max(1, len(t)))


def ser(truth, reading):
    ts = syllables(truth)
    part = locate(ts, syllables(reading))
    if not part:
        return 1.0
    return min(1.0, errors(ts, part)[0] / max(1, len(ts)))


def pick(parquet, outdir, per, kinds):
    import pandas as pd
    df = pd.read_parquet(parquet)
    out = Path(outdir)
    out.mkdir(parents=True, exist_ok=True)
    for kind in kinds:
        rows = df[(df.technology == kind) & (df.script == "Uchen")]
        rows = rows.sort_values("id").head(per)
        for _, row in rows.iterrows():
            stem = f"{kind[:5]}-{row['id']}"
            (out / f"{stem}.jpg").write_bytes(row["image"]["bytes"])
            (out / f"{stem}.gt.txt").write_text(row["transcription"])
            print(stem, row["legibility"], row["script_4"])


def score(truthdir, readers, verbose=False):
    truths = sorted(Path(truthdir).glob("*.gt.txt"))
    # A passage read by eye is marked by its name: <stem>.part.gt.txt
    print(f"{'reader':28} {'pages':>5} {'CER mean':>9} {'CER med':>8} {'syl err':>8}")
    for reader in readers:
        cers, sers, rows = [], [], []
        for t in truths:
            stem = t.name.removesuffix(".gt.txt").removesuffix(".part")
            partial = t.name.endswith(".part.gt.txt")
            reading = Path(reader) / f"{stem}.txt"
            truth = t.read_text()
            if reading.exists():
                text = reading.read_text()
                c, s = cer(truth, text, partial), ser(truth, text)
            else:
                c, s = 1.0, 1.0
            cers.append(c)
            sers.append(s)
            rows.append(f"    {stem:24} CER {c:6.1%}  syl {s:6.1%}")
        kinds = {}
        for t, c in zip(truths, cers):
            kinds.setdefault(t.name.split("-")[0], []).append(c)
        by_kind = "  ".join(f"{k} {statistics.mean(v):.1%}" for k, v in kinds.items())
        print(f"{Path(reader).name:28} {len(cers):5d} {statistics.mean(cers):9.1%} "
              f"{statistics.median(cers):8.1%} {statistics.mean(sers):8.1%}   CER by kind: {by_kind}")
        if verbose:
            for r in rows:
                print(r)


def main(argv):
    if len(argv) >= 3 and argv[0] == "pick":
        per, kinds = 5, ["blockprints", "digital_fonts"]
        if "--per" in argv:
            per = int(argv[argv.index("--per") + 1])
        if "--kinds" in argv:
            kinds = argv[argv.index("--kinds") + 1].split(",")
        pick(argv[1], argv[2], per, kinds)
    elif len(argv) >= 3 and argv[0] == "score":
        verbose = "-v" in argv
        argv = [a for a in argv if a != "-v"]
        score(argv[1], argv[2:], verbose)
    else:
        sys.exit(__doc__)


if __name__ == "__main__":
    main(sys.argv[1:])
