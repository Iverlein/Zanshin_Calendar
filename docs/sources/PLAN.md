# How to read the sources

For whoever continues the source work. The settled findings are in the
topic files listed in [README.md](README.md). What is still to be read, and
why the app needs it, is in the work plan for the Tibetan page's gaps
([ROADMAP.md](../ROADMAP.md), T2), each reading task with its source,
pages and done-when. This file says how to read: the rules, the tools, the
services and the dead ends.

**Tools and their limits** (all in `tools/sources/`, see *Tools* below):
Yigdzin-1 is the main reader but **drops lines beside woodcuts** (WB
img. 325, 330, 333: verse heads) and leans to common spellings; MITRA is
the witness and catches those drops (`disagree.py` flags them "missing");
both fail on BDRC's 1224-pixel microfilm scans (WB2), where MITRA invents
text. agy, asked to choose between two readings (`agy_choose.py`), was
wrong in about one case in five where a formula or the scan could check
it; judge its answers, do not take them. `gpu.py` and the readers stop
every model they start, even when killed.

## Before starting

- Read [README.md](README.md) (how the files quote) and SPEC §8 (no text
  without a published source; facts in our own words; nothing under a
  non-commercial licence).
- Never write a reading from memory. A task is done only when its facts are
  read on a page image and written into the topic file with edition, page
  and image number.
- A negative result is a result: write down what was searched and found
  absent (see *Dead ends* below), so that nobody searches it again.
- New questions about the texts go into [open-questions.md](open-questions.md),
  numbered on from the last one; new reading tasks into the work plan
  (ROADMAP T2).
- Nothing here is committed without the owner's word (AGENTS.md).

## Tools

All of these worked on 2026-10-03. The scripts are in `tools/sources/`
(each prints its usage when run without arguments); keep downloads in a
scratch directory, not in the repository:

- `bdrc.py etext DIR IE…` — the etexts below, with page markers;
  `bdrc.py volumes MW…` and `bdrc.py scans DIR I… 21-67` — page images.
- `scans.py crop | row | stack` — enlarged crops, one region across many
  images (heading cells, or one word wherever it occurs), stacked folios.
- `agy_read.py DIR IMG…` — transcription by agy, for single hard crops
  (see *Reading with agy* below).
- `ndl.py WORDS` — NDL catalogue search, for printed Japanese sources.
- `hf_read.py yigdzin|mitra OUT IMG…` — **the main reader**: BDRC's
  Yigdzin-1 OCR model on the local GPU (`hf_read.py setup VENV` makes its
  environment); MITRA, the runner-up, as witness.
- `disagree.py MAIN WITNESS --scan IMG --sheet OUT` — where two readings
  differ (and what MAIN dropped), with those scan lines stacked for
  checking by eye; `--crops DIR --json FLAGS` for the next step.
- `agy_choose.py OUT SCANDIR FLAGS…` — each flag put to agy as a choice
  between the two readings; judge the answers before using them.
- `mansion_verses.py WORKDIR OUT` — builds [mansion-verses.md](mansion-verses.md)
  from the readings, the judged choices and the English (WORKDIR is
  `wb/verses/`, local).
- `gpu.py` — what still holds the GPU; the readers call it when they end.
- `bdrc_ocr.py APP setup|run` — BDRC's older app models (CPU, seconds a
  page), a quicker but weaker witness.
- `score_reading.py TRUTH READING…` and `ocr_bench.py pick|score` — a
  reader's error rate against a passage read on the scan, or against the
  open BDRC benchmark; how the readers below were ranked.
- `local_read.py PRESET …` — general vision models through `llm-serve`;
  none reads Tibetan well enough (below).

### Which reader, 2026-10-03

Thirteen readers were scored on 15 pages of the open BDRC Tibetan OCR
benchmark (blockprint, digital font, metal type; Uchen) and WB p. 357 read
by eye. Mean character error rate:

| Reader | blockprint | typeset | WB p. 357 | all |
| --- | --- | --- | --- | --- |
| Yigdzin-1 (`BDRC/tibetan-ocr`) | 1.1% | 0.1–0.6% | 0.1% | **0.6%** |
| MITRA (`buddhist-nlp/bdrc-mitra-ocr-qwen35-0.8b`) | 1.5% | 0.8–1.5% | 0.1% | 1.2% |
| BDRC app, Woodblock-Stacks / Woodblock | 6–9% | 1.4–10% | 5–6% | 5–7% |
| PechaBridge, dots.mocr, PaddleOCR-VL, Qwen3.5-9B, Qwen3.6-35B, BDRC Modern | 34–67% | 1–37% | 0.5–17% | 19–31% |
| Tesseract `bod`, BDRC `bod_uchen` | 69–77% | 6–24% | 5–15% | ~35% |

agy (Gemini 3.1 Pro) made 2% syllable errors on p. 357, Yigdzin-1 0.5%
(one syllable, which MITRA misread the same way). Over the 31 WB pages agy
has transcribed, the two agree on 76–96% of syllables on most pages; where
they part most (p. 310, 29%), agy wrote fluent lines that are not on the
page and Yigdzin-1 read the scan correctly. **agy's transcriptions in
`wb/agy/` are therefore not a reliable base; read the pages again with
Yigdzin-1.** BDRC's own leaderboard
(huggingface.co/spaces/BDRC/tibetan-ocr-leaderboard, 46 systems) agrees:
the same two models first, Gemini 3.1 Pro at 0.33 median CER.

**Workflow:**

1. Typeset pages (WB, NM, SY): `hf_read.py yigdzin` over the pages, a
   witness beside it (`hf_read.py mitra`, or `bdrc_ocr.py … Woodblock-Stacks`
   on the CPU), then `disagree.py` with Yigdzin-1 as MAIN; check each flag
   on the scan. On the benchmark pages Yigdzin-1 got 74 of 3761 syllables
   wrong; MITRA flagged 43% of those (flagging 2.4% of the text),
   Woodblock-Stacks 58% (10%), both together 68% (12%). On WB pages that
   means 1–8 flags a page with MITRA (img. 305–308: 99–100% agreement), but
   about 84 with Woodblock-Stacks, so MITRA is the witness to use when the
   GPU has the time (1.5–2 min a WB page against Yigdzin-1's 20 s). The two BDRC models
   share training data and some errors (p. 357: both read བཙན for the
   print's བཙོན), and Yigdzin-1 leans to the common spelling where the
   print has an unusual one, so a passage to be quoted is still read once
   against the scan.
2. Woodblock tables (KP): crop each half of a box (`scans.py crop`) and
   read the crops; a whole folio comes out with the lines of side-by-side
   boxes run together. The crops come out as clean lists that keep the
   print's abbreviations; errors are in fine strokes (ནུཾ read ཆུཾ), so
   check every entry that matters against the crop. On box 6 the model
   found an entry (བྱིཞི) the reading by eye had missed.
3. agy only for what both readers and the eye leave unsettled, one crop at
   a time: its weekly quota is shared and small.

### Reading with agy

agy (`Gemini 3.1 Pro (High)`) takes 5–15 minutes a page and invents
plausible text where it cannot read (see above). It does **not** read
woodblock tables: on KP's abbreviated mansion lists it wrote the standard
order of the 27 mansions instead of the print, and on box headings it got
about half right and invented box numbers. Use it for single hard crops,
not for bulk reading.

### The services themselves

**BDRC etexts**, no browser needed:

```
https://ldspdi.bdrc.io/etextrefs/bdr:<IE id>          → volume ids (VE…)
https://ldspdi.bdrc.io/osearch/etextchunks?cstart=N&cend=N+20000&id=bdr%3A<VE id>
```

The text is in `innerHits.chunks.hits[].sourceAsMap.text_bo`, page numbers
in `innerHits.etext_pages.hits[].sourceAsMap.pnum` with their `cstart`.
Walk the volume in 20 000-character windows until two come back empty; a
window can answer HTTP 500 once, so retry it. The IE ids of WB, NM and SY
are in the README.

**BDRC full-text search** is a single-page app, so read it in a browser:
`https://library.bdrc.io/osearch/search?q="<Tibetan>"&uilang=en`. It
searches the OCR of thousands of works, rtsis manuals and almanacs
included, and is how NM and SY were found.

**Page images** (IIIF): `https://iiifpres.bdrc.io/collection/wio:bdr:<MW id>`
lists the volume manifests; each canvas has a service `@id`, and
`<@id>/full/max/0/default.jpg` is the image. Open-access volumes answer
200; restricted ones list only about twenty preview images and answer 401
for the rest. Native sizes are small (WB 703×1018, KP 2550×677), so crop
a line or a box and enlarge it 3–6× before reading it.

**OCR of woodblock prints:** BDRC's own pipeline,
github.com/buda-base/tibetan-ocr-app (MIT), with its Woodblock model.
Running text comes out nearly clean; tables do not (columns interleave,
digits drop), and the worn dbu med manuscript gives noise. Setup:

```
git clone --depth 1 https://github.com/buda-base/tibetan-ocr-app.git && cd tibetan-ocr-app
curl -sLO https://github.com/buda-base/tibetan-ocr-app/releases/download/v0.1/bdrc_ocr_models_1.0.zip
unzip -q bdrc_ocr_models_1.0.zip -d OCRModels
python3 -m venv venv && venv/bin/pip install numpy onnxruntime opencv-python-headless pillow \
  pyctcdecode pyewts scipy thin-plate-spline tqdm pyyaml requests platformdirs PySide6-Essentials
sed -i 's/import_local_model(os.path.dirname(model_dir))/import_local_model(model_dir)/' cli.py
QT_QPA_PLATFORM=offscreen venv/bin/python cli.py --model OCRModels/Woodblock --folder IMGS --output OUT
```

Tesseract's `bod` model is no use on these prints.

## Dead ends

Searched and found wanting, so not to be repeated:

- **Rinchen Terdzö** full text (rtz.tsadra.org, owner's manual search,
  2026-09-29): none of the personal-mansion names, no Viṣṭi, none of the
  eight unconfirmed yoga and mansion names. It is a collection of treasure
  texts, not a calculation manual.
- **The Derge reprint of WB** (BDRC MW1KG12714): its OCR garbles the verse
  passages; use the 1996 edition.
- **Tsadra wikis** (rtz, rywiki): a static fetch gets HTTP 403. A headed
  Chromium with a fresh profile of its own, read over its debugging port,
  loaded five rywiki pages in a row on 2026-10-06 without a check; an
  automated browser in a shared profile has passed the check at most once.
- **KP W21970** (Kan su'u mi rigs dpe skrun khang 2000): restricted on
  BDRC and archive.org.
- **KP dbu med manuscript** (MW3CN12069): OCR gives noise; where the text
  sits in its 414 images is not known.
- **NM page images**: stream-only on archive.org; the etext is all there is.
- **Tesseract `bod`**: useless on woodblock prints.
- **BDRC Woodblock-Stacks on WB vol. 2** (2026-10-04): the scans are only
  703 px wide; read as served it gives noise (8% of syllables agree), at
  3× upscaled 76%, its flags almost all its own (dropped vowel signs, ལྷ
  for ཉ). BDRC's etext is the third witness to use.
- **The owner's own Firefox** must never be driven by automation: a
  session on 2026-09-29 left about a hundred test preferences in the
  profile. Use a separate browser profile.
