# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at https://mozilla.org/MPL/2.0/.
"""Fetch texts and page images from the Buddhist Digital Resource Center.

  bdrc.py etext OUTDIR ID...              volume etexts, with [pN] page markers;
                                          ID is a volume (VE…) or a whole etext
                                          instance (IE…), whose volumes are fetched
  bdrc.py volumes MW_ID                   list a work's volumes (image groups)
  bdrc.py scans OUTDIR VOLUME [FIRST-LAST] [--width N]
                                          page images of one volume, numbered as
                                          BDRC labels them ("img. N", which can
                                          differ from the canvas's place)

The IE id is on the work's library.bdrc.io page ("Open in Etext Viewer"), and
so is its MW id. VOLUME is an image-group id (I…) or a number
in the volumes listing. No browser needed (docs/sources/PLAN.md, Tools).
Open-access volumes answer every image; restricted ones only about twenty.
"""
import json
import re
import sys
import time
import urllib.parse
import urllib.request
from pathlib import Path

REFS = "https://ldspdi.bdrc.io/etextrefs/bdr:{}"
ETEXT = "https://ldspdi.bdrc.io/osearch/etextchunks?cstart={}&cend={}&id={}"
COLLECTION = "https://iiifpres.bdrc.io/collection/wio:bdr:{}"
MANIFEST = "https://iiifpres.bdrc.io/vo:bdr:{}/manifest"
WINDOW = 20000


def get(url, tries=3):
    for attempt in range(tries):
        try:
            with urllib.request.urlopen(url, timeout=90) as r:
                return r.read()
        except Exception as e:  # a window past the end can answer 500 once
            if attempt == tries - 1:
                raise
            print(f"  retry {url}: {e}", file=sys.stderr)
            time.sleep(2)


def etext(ve, out):
    """Walk a volume in 20 000-character windows until two come back empty."""
    chunks, pages, start, empty = {}, {}, 0, 0
    while empty < 2:
        try:
            data = json.loads(get(ETEXT.format(start, start + WINDOW, urllib.parse.quote("bdr:" + ve))))
        except Exception:
            data = []
        got = False
        for hit in data:
            inner = hit.get("innerHits") or {}
            for h in (inner.get("chunks") or {}).get("hits", []):
                s = h["sourceAsMap"]
                chunks[s["cstart"]] = s.get("text_bo") or s.get("text_en") or ""
                got = True
            for h in (inner.get("etext_pages") or {}).get("hits", []):
                s = h["sourceAsMap"]
                pages[s["cstart"]] = s.get("pnum")
        empty = 0 if got else empty + 1
        start += WINDOW
    marks = sorted(pages.items())
    text = []
    for cs in sorted(chunks):
        t = chunks[cs]
        for off, n in reversed([(p - cs, n) for p, n in marks if cs <= p < cs + len(t)]):
            t = t[:off] + f"\n[p{n}]\n" + t[off:]
        text.append(t)
    Path(out).write_text("".join(text))
    print(ve, len(chunks), "chunks", len(pages), "pages", "->", out)


def etext_volumes(ie):
    """The VE ids of an etext instance. Any id that is neither VE nor IE is refused:
    the chunks API answers an unknown id with hits from everywhere, without end."""
    if ie.startswith("VE"):
        return [ie]
    if not ie.startswith("IE"):
        sys.exit(f"{ie}: give a VE… or IE… id")
    return [v["@id"] for v in json.loads(get(REFS.format(ie)))["volumes"]]


def volumes(mw):
    coll = json.loads(get(COLLECTION.format(mw)))
    result = []
    for m in coll.get("manifests", []):
        group = m["@id"].split("bdr:")[1].split("/")[0]
        label = m.get("label")
        label = label.get("@value") if isinstance(label, dict) else label
        result.append((group, label))
    return result


def image_number(canvas, index):
    """BDRC's own number of a canvas, "img. 321": the one the topic files cite. It is
    not the canvas's place in the manifest, since a volume's first images (the
    scanning targets) are left out, so canvas 1 is often img. 3."""
    labels = canvas.get("label") or []
    for label in labels if isinstance(labels, list) else [labels]:
        value = label.get("@value", "") if isinstance(label, dict) else str(label)
        m = re.fullmatch(r"img\. (\d+)", value)
        if m:
            return int(m.group(1))
    return index


def scans(out, volume, first=1, last=None, width=None):
    """Save images first..last of a volume, by BDRC's image numbers, as img<NNN>.jpg (full size, or --width)."""
    manifest = json.loads(get(MANIFEST.format(volume)))
    canvases = {image_number(c, i): c for i, c in enumerate(manifest["sequences"][0]["canvases"], 1)}
    last = min(last or max(canvases), max(canvases))
    size = f"{width}," if width else "max"
    Path(out).mkdir(parents=True, exist_ok=True)
    for n in range(first, last + 1):
        target = Path(out) / f"img{n:03d}.jpg"
        if target.exists() or n not in canvases:
            continue
        service = canvases[n]["images"][0]["resource"]["service"]["@id"]
        try:
            target.write_bytes(get(f"{service}/full/{size}/0/default.jpg"))
            print(n, target)
        except Exception as e:
            print(n, "not served:", e, file=sys.stderr)


def main(argv):
    if len(argv) >= 3 and argv[0] == "etext":
        for given in argv[2:]:
            for ve in etext_volumes(given):
                etext(ve, Path(argv[1]) / f"{ve}.txt")
    elif len(argv) == 2 and argv[0] == "volumes":
        for i, (group, label) in enumerate(volumes(argv[1]), 1):
            print(i, group, label)
    elif len(argv) >= 3 and argv[0] == "scans":
        width = None
        if "--width" in argv:
            i = argv.index("--width")
            width = int(argv[i + 1])
            argv = argv[:i] + argv[i + 2:]
        out, volume = argv[1], argv[2]
        first, last = 1, None
        if len(argv) > 3:
            a, _, b = argv[3].partition("-")
            first, last = int(a), int(b or a)
        scans(out, volume, first, last, width)
    else:
        sys.exit(__doc__)


if __name__ == "__main__":
    main(sys.argv[1:])
