# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at https://mozilla.org/MPL/2.0/.
"""Finding a passage: in the etexts, in another print's images, in the dictionaries.

  locate.py find PHRASE ETEXT... [--ctx N]
      every place PHRASE occurs in bdrc.py etexts, ignoring tsheg, shad,
      spaces and line breaks (the OCR's own breaks), with the [pN] page
      marker before it and N characters of context (default 150)
  locate.py map ETEXT OCR...
      which etext pages each OCR file (one image of another print, read by
      hf_read.py) holds: its 4-syllable shingles looked up in the etext,
      pages with three or more hits, with their counts
  locate.py zhol PAGE...
      WB vol. 1 printed pages on the Zhol print (I1KG1710): the images, the
      lines, the folio and side of each image, from docs/sources/wb/zhol-map.tsv
      (docs/sources/wb/zhol-map.md says how it was made and checked)
  locate.py dict REPO TERM... [--prefix]
      headwords equal to TERM (Wylie, as the dictionaries key them) in
      Steinert's 64 dictionaries; REPO is a clone of
      github.com/christiansteinert/tibetan-dictionary (git clone --depth 1,
      4.5 GB, into a scratch directory); --prefix matches the start

An etext's page marker is BDRC's: for WB's 1996 edition it is the image
number (vol. 1: printed page = image − 10; vol. 2: image − 8), for WBZ the
image number. A print's image is mapped with `map` against the etext of the
edition whose page is wanted. In an OCR file each line is a line of the
folio, so a hit's character offset over the line's length places the word
for a crop (scans.py crop) to be read by eye.
"""
import argparse
import collections
import glob
import os
import re

SKIP = set("་། \n\t༌༔/")  # "/" is a line break in BDRC etexts
PAGE = re.compile(r"\[p(\d+)\]")


def squeeze(text):
    """The text without separators, and each kept character's index in the original."""
    kept = [(i, ch) for i, ch in enumerate(text) if ch not in SKIP]
    return "".join(ch for _, ch in kept), [i for i, _ in kept]


def syllables(text):
    return [s for s in re.split(r"[་།\s༌]+", text) if s]


def find(args):
    needle, _ = squeeze(args.phrase)
    for path in args.etexts:
        text = open(path, encoding="utf-8").read()
        flat, index = squeeze(text)
        pages = [(m.start(), m.group(1)) for m in PAGE.finditer(text)]
        for m in re.finditer(re.escape(needle), flat):
            at = index[m.start()]
            page = "?"
            for pos, number in pages:
                if pos > at:
                    break
                page = number
            context = text[max(0, at - args.ctx): at + args.ctx + len(args.phrase)]
            print(f"{os.path.basename(path)} p{page}: {context.replace(chr(10), ' / ')}\n")


def map_pages(args):
    text = open(args.etext, encoding="utf-8").read()
    shingles = collections.defaultdict(set)
    for m in re.finditer(r"\[p(\d+)\](.*?)(?=\[p\d+\]|\Z)", text, re.S):
        s = syllables(m.group(2))
        for i in range(len(s) - 3):
            shingles[tuple(s[i:i + 4])].add(int(m.group(1)))
    for path in args.ocr:
        s = syllables(open(path, encoding="utf-8").read())
        hits = collections.Counter()
        for i in range(len(s) - 3):
            pages = shingles.get(tuple(s[i:i + 4]))
            if pages and len(pages) < 3:
                hits.update(pages)
        print(os.path.basename(path), sorted((p, n) for p, n in hits.items() if n >= 3))


def lookup(args):
    files = sorted(glob.glob(os.path.join(args.repo, "_input", "dictionaries", "public", "*")))
    seen = set()
    for path in files:
        name = os.path.basename(path)
        if name.endswith("_Scan"):
            continue
        for line in open(path, encoding="utf-8", errors="replace"):
            key = line.split("|", 1)[0]
            if any(key.startswith(t) if args.prefix else key == t for t in args.terms):
                entry = f"{name} :: {line.strip()}"
                if entry not in seen:
                    seen.add(entry)
                    print(entry)


ZHOL_MAP = os.path.join(os.path.dirname(os.path.abspath(__file__)), "..", "..", "docs", "sources", "wb", "zhol-map.tsv")


def folio(img):
    """The Zhol folio and side of a vol. 1 image: odd images are the recto, even the verso (checked img. 20-21, 103-105, 197, 251-256, 382-384)."""
    return f"f. {(img - 1) // 2}{'a' if img % 2 else 'b'}"


def zhol(args):
    rows = {}
    for ln in open(ZHOL_MAP, encoding="utf-8"):
        if ln.startswith("#") or ln.startswith("page"):
            continue
        c = ln.rstrip("\n").split("\t")
        rows[int(c[0])] = c
    for p in args.pages:
        if p not in rows:
            print(p, "not in the map (vol. 1 pp. 1-472)")
            continue
        _, a, la, b, lb, basis = rows[p]
        a, b = int(a), int(b)
        where = f"img. {a}" + (f" l. {la}" if la else "") + (f" - img. {b}" + (f" l. {lb}" if lb else "") if b != a else "")
        note = "  (interpolated between neighbours: open the range)" if basis == "interp" else "  (line number can be one off)"
        print(f"p. {p}: {where}  [{folio(a)}..{folio(b)}]{note}")


def main():
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    sub = parser.add_subparsers(dest="cmd", required=True)
    f = sub.add_parser("find")
    f.add_argument("phrase")
    f.add_argument("etexts", nargs="+")
    f.add_argument("--ctx", type=int, default=150)
    f.set_defaults(run=find)
    m = sub.add_parser("map")
    m.add_argument("etext")
    m.add_argument("ocr", nargs="+")
    m.set_defaults(run=map_pages)
    z = sub.add_parser("zhol")
    z.add_argument("pages", nargs="+", type=int)
    z.set_defaults(run=zhol)
    d = sub.add_parser("dict")
    d.add_argument("repo")
    d.add_argument("terms", nargs="+")
    d.add_argument("--prefix", action="store_true")
    d.set_defaults(run=lookup)
    args = parser.parse_args()
    args.run(args)


if __name__ == "__main__":
    main()
