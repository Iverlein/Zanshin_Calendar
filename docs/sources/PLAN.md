# How to read the sources

For whoever continues the source work. The settled findings are in the
topic files listed in [README.md](README.md). What is still to be read, and
why the app needs it, is in the roadmap's *Readings*
([ROADMAP.md](../ROADMAP.md#rd1)), each reading with its source, pages,
the readings it needs, the blocks that wait on it and its topic file;
what is left open in built work is listed under it. This file says how to read: the rules, the tools, the
services and the dead ends. Running several readings at once, as many
as the machine bears, each by a subagent and a launcher that merges
and keeps the shared files, is [PROTOCOL.md](PROTOCOL.md).

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
  numbered on from the last one; new reading tasks into the roadmap's
  *Readings* (RD…, or O… for what is left open in built work).
- A reading session commits its topic files without asking, as every
  session commits its verified work (AGENTS.md); pushing waits for the
  owner.

## The knowledge pool

A reading session fills `docs/sources/`; nothing it learns is left in the
conversation or in a scratch directory (the owner, 2026-10-11).

- **The text itself, in Tibetan.** Every passage read for the app goes
  into its topic file in Tibetan script as it reads on the scan, with
  edition, page and image number, the English beside it, not instead of
  it: the whole passage the facts come from, not only the line used, so
  that the next reader starts from checked text. Where the scan was not
  read, the machine reading may stand, marked as such (README.md, *How
  the files quote*). A work under copyright (a modern author's, Dr.
  Chenagtsang's, KD's essays) is quoted no further than its facts need;
  the public-domain texts (WB, KP, the classical works) are given in
  full.
- **Everything else learned.** A chapter's place and outline, what a
  passage turns out to be, a count, a cross-reference, a page that proves
  another text's source: into the topic file it concerns, or
  [wb-contents.md](wb-contents.md) for WB's structure, the same session,
  marked etext-only or machine-read where it is. A search with no result
  goes under *Dead ends*.
- **Marking a reading done.** In the roadmap's Readings table, "— done
  YYYY-MM-DD" after its name, only once its topic file holds it, names it
  ("RD7") and carries the Tibetan it read. `SourcesTest` (core) fails if
  a done reading's topic file is missing, does not name it or holds no
  Tibetan, or if a topic file is missing from [README.md](README.md)'s
  index.
- **What stays out.** Scans, etexts and downloads stay in the scratch
  directory, and the unchecked machine transcriptions in `wb/`
  (gitignored): the repository keeps what was checked, and says where
  the rest can be fetched again.

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
- `archive_lend.py login | borrow | pages | view | giveback` — BDRC's
  restricted scans lent on archive.org (BS, MK, KD and others), under the
  owner's login from the keyring (`secret-run`, never printed): a
  one-hour browse loan, and each wanted leaf screenshot from
  archive.org's own reader in a headed Chromium. The loan's images are
  obfuscated and are not to be decrypted; the reader's page is what is
  read. Find a passage's leaf by the BDRC etext's character offset (the
  search result's `startChar`), then the printed number on the page;
  `hf_read.py` on the screenshots finds a phrase in a run of leaves.
  Return the loan and delete the cookie file afterwards. For a
  single-volume item `view`'s FILE argument is "." (an empty one finds no
  page image), and the playwright it runs must match the cached Chromium
  (1.62.0 for chromium-1234; RD9, 2026-10-12).
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
- `schedule.py machine|plan|prompts|stubs|slot` — the readings that can
  run now and how many at once the card and the memory bear, their
  prompts and topic-file stubs, and the GPU slot lock every model run
  goes through ([PROTOCOL.md](PROTOCOL.md)).
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
included, and is how NM and SY were found. Many searches in a row run
from one open search page, each in an `<iframe>` of the same origin,
read after about nine seconds (2026-10-10). A hit's own etext may be
**search only** (OS, MK, BS): `etextchunks` answers 403 and the viewer
shows a snippet of about 300 characters. Read the snippet, search its
last words for the next one, and read the page itself on the lent scan:
the viewer's "Page N" of a hit is the leaf, not the printed page (OS:
"Page 1358" = leaf 1358 = p. 1303). The verse quoted in a snippet is
often the running head, not the text (OS's «རྩིས་རིག་འོད་ཕྲེང་སོ་གཅིག་པ། རིགས་ལྡན་མཆོད་པའི་འོད་སྣང»).

**Page images** (IIIF): `https://iiifpres.bdrc.io/collection/wio:bdr:<MW id>`
lists the volume manifests; each canvas has a service `@id`, and
`<@id>/full/max/0/default.jpg` is the image. Open-access volumes answer
200; restricted ones list only about twenty preview images and answer 401
for the rest. Native sizes are small (WB 703×1018, KP 2550×677), so crop
a line or a box and enlarge it 3–6× before reading it. The image server
answers 502 now and then; retry after a few seconds.

**Checking the 1996 typesetting.** The 1996 edition is typeset from a
blockprint and has misreadings (ག set as ད in «འགྲེ», p. 330). The
sharpest witness is the Taikhang reproduction of a Lhasa print (MW30116,
8280 px, no etext). To find a page there, read a few of its images at
3000 px with `hf_read.py yigdzin` and look the lines up in the 1996
etext: on 2026-10-06 its vol. 2 img. 350, 390, 430 and 470 held the 1996
vol. 2 pp. 244, 272, 297–300 and 331–332 (etext page − 8), and p. 330
was on img. 468.

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
- **The mansion of conception** («མངལ་འཇུག་སྐར་མ», WB p. 338): BDRC's
  full-text search and the etexts of WB, KD, NM and SY (2026-10-06) find
  no definition; the term occurs only in WB, SY's prose of it and a
  Rebkong anthology copying SY.
- **The Derge print of WB for one letter** (WB2, 1208 px): too small;
  for the 1996 edition's slips use the Taikhang and Zhol prints.
- **KD's scans** are lent on archive.org only: read them with
  `archive_lend.py` (above), or look for the same work as an open BDRC
  print (Dharmaśrī: MW9140; Phug pa: MW1NLM5184).
- **BDRC Woodblock-Stacks on WB vol. 2** (2026-10-04): the scans are only
  703 px wide; read as served it gives noise (8% of syllables agree), at
  3× upscaled 76%, its flags almost all its own (dropped vowel signs, ལྷ
  for ཉ). BDRC's etext is the third witness to use.
- **WB's eclipse exceptions in other manuals** (p. 64, open question 18,
  2026-10-10): CN's eclipse section has none; KD vol. 2, etext
  pp. 106–108, reprints WB's chapter; the Kālacakra commentaries at hand
  (Phyogs las rnam rgyal's *mchan 'grel*, the Jo nang *rtsis* volume)
  say nothing of Mercury or Venus at an eclipse. JL and OS restate the
  lines; no text found names the places the Mercury and Venus clause
  compares.
- **The rule behind the model almanac's dated month entries** (WB vol. 1,
  pp. 154–171: the water demons' king on the 1st month's 21st and the
  9th's 20th, the earth lords' king on the 8th month's 10th, the castes
  rising and lying, the mantrin's months; RD2, 2026-10-11): the etext
  of both volumes has none of «ཆུ་བདུད་རྒྱལ་པོ», «དགྲར་ལྡང», «རྗེ་རིགས», «ཁ་རུལ», «ཟོར་འཕེན་ཟླ»,
  «ཁྱི་གསར་མི་གསོ» in such a rule outside the model almanac. Not yet searched:
  BDRC's full text, and the *Gtsug lag spor thang* and other works the
  heads may follow.
- **A reckoning for the dead in WB ch. 31** (RD11, 2026-10-11, the etext
  of vol. 2): «གཤིན་རྩིས» occurs in ch. 29 (pp. 142–143), in passing in ch. 31
  (p. 225), ch. 35 (p. 452), the colophon (p. 457) and the notes
  (pp. 499–540); ch. 31 holds no reckoning for the dead. It is ch. 29,
  the copper vessel (*zangs kyam*), with the notes' section 4.
- **A rule for the month's or the year's river** (RD4, 2026-10-11):
  «ཀླུང་རྟ» and «ཀླུངས་རྟ» in the etext of both volumes of WB give only the
  person's river-horse (vol. 2, pp. 12–25, etext pp. 20–33) and the column
  «ཟླ་ཀླུང» of ch. 13's tables (vol. 1, etext p. 115).
- **The wind-horse of each triad, and a wind-horse's tomb** (RD3,
  2026-10-11), needed for the courses of the year's nos. 4, 9, 16 and the
  nine black ones 2, 5, 8: the etexts of both volumes searched for «ཀླུང་རྟ»
  beside the triads give only the tiger, horse and dog's iron monkey
  (vol. 2, p. 479, img. 487, etext only). Not yet searched: vol. 1,
  ch. 20–21 read on the scan.
- **«ཉི་མའི་གླང»**, no. 19's entry (RD3, 2026-10-11): in neither volume's
  etext, whose OCR of vol. 2, p. 186 garbles the line itself; BDRC's
  full-text search not tried.
- **`bdrc.py scans` on 2026-10-11** failed with 502 from the IIIF
  manifest (`iiifpres.bdrc.io/vo:…/manifest`) while the image list
  (`iiifpres.bdrc.io/il/v:bdr:<I…>`) and the images answered: fetch
  `https://iiif.bdrc.io/bdr:<I…>::<filename>/full/max/0/default.jpg`
  directly, the filename from the image list (`I1KG129060316.jpg` for
  vol. 1 img. 316).
- **The owner's own Firefox** must never be driven by automation: a
  session on 2026-09-29 left about a hundred test preferences in the
  profile. Use a separate browser profile.
- **A rank or weight of the earth lords among the day's factors**
  (RD6, 2026-10-12): not in WB's notes vol. 2 pp. 474–541 (etext: «ས་བདག»
  with «སྟོབས», «ནུས་པ», «ཆེ་ཆུང», «དམིགས་བསལ», «གཙོ་བོ»), not in the three volumes
  of KD, not in KP img. 13–14. The notes list the earth lords and the results
  as two heads (pp. 534–535) and join them nowhere. Not searched: BDRC's
  full-text search in other manuals.
- **An earth lord named in the good half of a KP box** (RD5, 2026-10-12):
  none in boxes 2–65 bar the *mdos* verse of box 56, which names rites, not
  lords. *gnam stong* in full is not in any box's list (only «གནམ» in box 23).
- **MITRA on whole KP folios** (RD5, 2026-10-12): loops on digits over the
  2550 px woodblock tables; no reading. Use Yigdzin-1 on whole folios and on
  the left and right 55 % halves enlarged 1.5×.
- **WB vol. 1 pp. 428–429 in BDRC's scan I1KG12906 and its etext** (RD12,
  2026-10-12): absent; img. 438–441 are pp. 430, 431, 430, 431. The text is in
  the Zhol print WBZ (I1KG1710 img. 565–568), machine-read only.
- **A worked numeric example of the fifteen starting points** (RD12,
  2026-10-12): none in ch. 25; the only example is p. 442's twelve stages.
- **Recovery or death counts for the mansions** (RD12, 2026-10-12): not in
  vol. 2's mansion verses (pp. 313–328); not searched elsewhere.
- **A weighing among the seventeen parts of the bride's departure** (RD15,
  2026-10-12): none in WB ch. 23 (pp. 365–378, read on the scan); each bad
  finding has its own remedy.
- **The meaning of «ཎི་ཤ»**, the match's sixth point (RD15, 2026-10-12): the
  etext of both volumes of WB has it on vol. 1, p. 351, in the notes
  (vol. 2, p. 525) and in ch. 30 (vol. 2, p. 152), and Yigdzin-1 on p. 359;
  none says what the word is.
- **The Library of Congress catalogue** (catalog.loc.gov, loc.gov/books,
  id.loc.gov; RD7, 2026-10-12): a Cloudflare check and a 503; its records
  are read as copied into Open Library and VIAF.
- **WorldCat** (search.worldcat.org; RD7, 2026-10-12): a fielded search asks
  to sign in, a plain one to accept the terms first; not used.
- **84000 for "Chenagtsang"** (RD7, 2026-10-12): no result.
- **Dr. Chenagtsang's name in Tibetan script in a library's authority
  record** (RD7, 2026-10-12): none; VIAF's Tibetan heading is Wikidata's.
  The title pages of KL and GC print it.
- ***The Science of Mantra Healing by Interdependence* on BDRC** (RD7,
  2026-10-12): a title search for *rten 'brel sngags* finds only older texts.
- **The etexts of GC (IE0OPI430023E3), the 1996 poems (IE0OPI85F3D3D1) and
  RS (IE0OPI126D5130)** (RD7, 2026-10-12): `etextchunks` answers 403 (search
  only); RS's scans are lent on archive.org.
- **A list of his books on drnida.com/books** (RD7, 2026-10-12): the page
  holds no list.
- **A single verdict or score of the whole reckoning for the dead** (RD16,
  2026-10-12; WB vol. 2, pp. 44–51 on the scan, the etext of pp. 51–143 and
  of the notes pp. 499–522): none; each examination gives its own readings
  and rites.
- **The natal trigram on the *Moonbeams*' f. 12a** (Dorje, p. 108; RD13,
  2026-10-12) in Dharmaśrī's *Gsung 'bum* print (I2533, img. 027, read on
  the scan): that folio holds the five nine-multiples; the natal trigram
  is on f. 9a (img. 021). Dorje's folio number is of another print.
- **A worked example of a natal trigram** in WB ch. 22 or the notes'
  section 1 (RD13, 2026-10-12; etext and Yigdzin-1): none; the
  *Moonbeams*' rule (f. 9a) is the only count.
- **A table of the la, the life force or illness by date, month or hour in
  WB ch. 20–21** (RD10, 2026-10-12; vol. 1, pp. 218–258, every «བླ», «སྲོག»
  and «ནད» of the etext, img. 228–268): none.
- **KL's six moxa dates (1, 6, 15, 18, 22, 30) as a list in WB** (RD10,
  2026-10-12; «མེ་བཙའ» in the etext of both volumes): none.
- **A reading of a person's enemy or grave mansion in WB ch. 33** (RD10,
  2026-10-12; «དགྲ་སྐར», «དུར་སྐར» in vol. 2): «དགྲ་སྐར» there is a class of the
  mansions themselves; no reading of either for a day.
- **A rule for the eight-day round of the lunar dates' illness in WB ch. 33**
  (RD10, 2026-10-12): none; the matches are given one by one (the round is
  ch. 25's goddesses').
- **A source named in KL for its la tables or moxa dates** (RD8,
  2026-10-12; KL's whole etext): none in ch. 10 or ch. 13; only p. 3,
  note 2, and p. 24 (Mipham) for the anointing.
- **KL's moxa line and «བླ་རྩ་བསྡམ» in BDRC's full-text search** (RD8,
  2026-10-12; «ཚེས་གཅིག་དྲུག་དང་བཅོ་ལྔ», «བཅོ་བརྒྱད་ཉེར་གཉིས་སུམ་ཅུ»): KL only;
  «བླ་རྩ་བསྡམ», no result.
- **Mipham's *Treatise on Passion* in the etext of his Collected Works**
  (RD8, 2026-10-12; VEIE0OPI44A9BBFC_I1KG8551, poor OCR): no readable la
  passage between etext pp. 536 and 575; the scans are to be read instead.
- **GC's etext** (RD8, 2026-10-12): `etextchunks` answers 403; the viewer's
  search finds no snippet naming Mipham, Deumar, Rigdzin Gödem or a source
  for the la course (22 terms searched; the list in health.md).
- **KL's place words in RS** (RD8, 2026-10-12): none; RS's la courses are
  other systems.
- **The Final Tantra's la-pulse passage that GC quotes** (RD8, 2026-10-12):
  not located in MW3CN5036. Found by RD9 (2026-10-12) through BDRC's full-text
  search for «ཚེའི་རྟགས་བླ་ཡི་རྩ་ལ་བརྟག་པ»: ST p. 505 (img. 519), the end of
  the Final Tantra's ch. 1; GT img. 665.
- **A worked example of the household pebble round with its readings**
  (RD14, 2026-10-12): none in WB ch. 24 or the notes' section 2 beyond the
  notes' layout of five people (vol. 2, p. 490).
- **The mother's year in WB's secret circle** (RD14, 2026-10-12; vol. 1,
  p. 380): «མ་ལ་དྲིས» is the secret life force's mother element (the scan;
  the notes, vol. 2, p. 489), not the person's mother.
- **The hand reckoning, a casting (མོ, mo) "like Lu's", in WB's main text**
  (RD17, 2026-10-12; «ལག་རྩིས», «ཡིད་ལ་དང་པོར་གང་དྲན», «གང་དྲན» in the etext of
  both volumes): only in the notes, vol. 2, p. 538.
- **A breakdown of WB ch. 27's 301 root pebbles** (RD17, 2026-10-12; p. 3
  on the scan, the chapter by Yigdzin-1): none; the thirteen circles make
  65, the small reckonings close with their own 75.
- **A link or sme ba in KP box 50's bad half or box 60's halves** (O1,
  2026-10-12; KP2 img. 278, KP img. 62): none, bar box 60's mention of the
  links as a class.
- **The word inside KP box 5's bracket** (O1, 2026-10-12; KP img. 25, KP2
  img. 244): not legible in either print at 6×.
- **Box 60's last line and box 59's «སྐྱེད» in KP2** (O1, 2026-10-12; img.
  285): worn, not read.
- **The bird names *bya gzhug*, *bya mjug*, *bya zer*, *bya sbo* in WB's
  list of the mansions' other names** (O2, 2026-10-12; vol. 1, p. 19, etext
  p. 29, etext only): absent; the list gives Śravaṇa, Dhaniṣṭhā and
  Śatabhiṣaj other names, none of them birds.
- **«བྱ་གཞུག» and «བྱ་མཇུག» elsewhere in WB** (O2, 2026-10-12; the etext of
  both volumes): only vol. 1, p. 93, and vol. 2, pp. 382, 390, 401, 416; the
  notes (vol. 2, pp. 474–541) have neither.
- **A commentary on ch. 34's four lines, or *bya gzhug* with a number, on
  BDRC** (O2, 2026-10-12; full-text search «བྱ་གཞུག», «བྱ་ཟེར་བྱ་སྦོ»,
  «བྱ་སྦོ་བྱ་གཞུག»): nearly all hits are «གདུལ་བྱ་གཞུག»; one treasure text's
  prosperity rite (MW1KG2118_4EBF3C) names *bya sbo* and *bya gzhug* between
  *phul* and *sgrog*, without numbers.
- **Ch. 34's three verses in WB's notes** (O2, 2026-10-12; vol. 2,
  pp. 474–541, etext): «ཚེས་གཅིག་དྲུག», «བཅོ་བརྒྱད་དགུ», «ཚེ་ནོར», «ཡིག་རྩིས»
  do not occur; the «བག་མ» passages (pp. 525–532) concern the couple's years,
  clans and ancestors.
- **A second open-access Four Tantras on BDRC** (RD9, 2026-10-12): the
  Lhasa 1982 print (MW16778, I4839), the Sde dge typeset (MW2DB4628,
  I1PD28098), the Grathang print (MW29627, I1PD464) and the Final Tantra
  alone (MW8LS68177) answer 401 beyond their preview images; the Dharamsala
  2008 Root and Explanatory Tantras (MW1KG2121, I1KG2143) and a Mongolian
  manuscript of the first two (MW1NLM116) answer; the Drathang woodblock
  with notes (MW4CZ321183, I4CZ327131) is whole and was used.
- **The word «བླ» in Mipham's *Treatise on Passion*** (RD9, 2026-10-12;
  Yigdzin-1 over I1KG8551 img. 536–580): only in other words (བླ་རེ, བླ་མ,
  བླང); the treatise speaks of the drop of bliss, not the la.
- **A source Deumar names for the Tsuglag Rigje system** (RD9, 2026-10-12;
  DM pp. 589–594 on the scan): none.
- **«དུས་སྲི» and «སྲི་བཟློག» in WB** (O6, 2026-10-12; the etext of both
  volumes, IE0OPI51524892): the *dus sri* of Ramble's rite and "turning
  back the sri" are in neither volume.
- **A gloss on WB's Mercury and Venus clause at a solar eclipse** (O4,
  2026-10-12; vol. 1, p. 64): BDRC full-text search for «ཆུ་ཚོད་འབྱུང་མེ་ཟླ་ཚུན»,
  «ལྷན་ཅིག་གནས་ཚེ་བཟུང», «ལྷག་པ་པ་སངས་གཉིས» and fourteen variants (listed in
  month-entries.md, O4's *Searched and not found*) finds WB's prints, KD,
  the Gser thang edition and a verse copy in Bstan 'dzin chos kyi rgyal
  mtshan's works (MW3JT13322, no gloss); the etexts of KD, the Sakya *rtsis
  gzhung*, JL, Bu ston, *Making the Learned Glad*, Kongtrul's *Treasury
  Pervading All Knowledge*, the annotated *Abridged Kālacakra Tantra* and
  Blo gros dpal give only the *Seven Special Qualities*' restatement. Sde
  srid's *Removing the Tarnish from the Beryl* has no etext on BDRC.
- **«མོད་འགྱུར» as a compound, and a gloss on the 9th hair date** (O3,
  2026-10-12): no headword in the 64 dictionaries of Steinert's collection
  (*mod*, *mod pa*, *mod po* only); BDRC full-text search: «ན་ཆུང་མོད» and
  «དགུ་ནི་ན་ཆུང» only in WB (MW2CZ8040, MW3CN5026) and a modern novel, «ན་ཆུང་མོང» and
  «ན་ཆུང་མོད་པར» nothing, «ན་ཆུང་མོས» one unrelated poem, «མོད་འགྱུར» 28 hits, the
  first six read, none another almanac's hair dates. WB2's line (I1KG12740
  img. 555) is blotted and cannot be read at 1226 px.
- **A sharper scan of WB vol. 2's 1996 edition** (O8, 2026-10-12): the
  Gser thang reprint of the same typesetting (MW3CN5026, vol. 2, I3CN5029,
  1905 px; image = page + 6 at pp. 223–226) shows the 1996 letters at nearly
  three times BDRC's 703 px; the next reading of WB vol. 2 starts there. The
  Taikhang reproduction (MW30116, I5878) img. 324–326 fetched (8280 px), not
  read; where p. 225 falls in it not established.
- **WBZ's IIIF manifest** (O8, 2026-10-12): `bdrc.py scans I1KG1710` failed
  with 502 on the manifest, and the image list
  (`iiifpres.bdrc.io/il/v:bdr:I1KG1710`) with 502 too; the images answered
  at `https://iiif.bdrc.io/bdr:I1KG1710::I1KG1710NNNN.jpg/full/max/0/default.jpg`
  (NNNN the four-digit image number).
- **A sharper scan of WB vol. 2, pp. 308–312** (O7, 2026-10-13): the Gser
  thang reprint (I3CN5029, img. 314–318, 1905 px, image = page + 6 as at
  pp. 223–226) is a bilevel image; it shows the 1996 letters larger but
  fills the small boxes with ink, so it does not settle Tuesday's vowel,
  Wednesday's «དུར་?ོ» or Tuesday's «[རླིད]». It does settle Monday's
  «དྲོ», Cancer's ཊ and Saturday's «བནྡེ».
- **WB vol. 1, pp. 173–174 on the Gser thang reprint** (O10, 2026-10-13):
  the reprint (MW3CN5026, I3CN5028, 2040 px) does not hold them: its
  img. 172–173 are model-almanac boxes and img. 174 is p. 175 (image =
  page − 1 at pp. 175–182; image = page + 7 at pp. 22 and 156), so the
  model almanac is laid out differently there and pp. 173–174 are read on
  BDRC's 703-px scan only.
- **The Gser thang reprint keeps no one offset** (O10, 2026-10-14): in
  vol. 1 (I3CN5028) image = page − 5 at pp. 253, 276 and 355–364, page − 1
  at pp. 185–189 and page + 7 at pp. 31–65 (img. 252 is not p. 234); in
  vol. 2 (I3CN5029) image = page + 6 at pp. 152, 174, 211–212, 223, 484 and
  523–525. Check the printed page number on each image fetched. The
  reprint is blank in places on vol. 2, p. 223 (img. 229).
- **Mipham's *Treatise on Passion*, img. 547** (O10, 2026-10-14): one folio
  side in a single band at 2428 px; the line on the mansions to avoid is
  not readable by eye at the scales tried and stays machine text.
- **KP's small numerals and KP2's photograph** (O10, 2026-10-14): box 33's
  second good date (img. 42) stays ༢༡ or ༣༡ at 4×; the numerals under box
  16's three «ཟླ་ཚེས» (img. 33) are too small at 5× (WB's work 14 gives
  them); KP2's boxes 50 and 51 (img. 278–279) are too dim to read syllable
  by syllable and stand as the machine reading; KP2 img. 264, box 34's
  numeral against WB's «བཞི་ལྗང», was not read.
- **Russian names of the 暦注** (K1, 2026-10-15): cyberleninka's search
  API, synologia.ru (the articles «Эр ши ба сю», «Цзе ци», «Шэ», «Цзянь
  чу», «Тун шу», «Хуан ли» and others are "not yet written") and WaRoDai
  (БЯРС) have no Russian name for 暦注, its bands, 十二直, 選日, 雑節, 社日
  or the named 下段 days; Фельдман's «Японский календарь» (*НАА*, 1970,
  № 4), Цыбульский's *Лунно-солнечный календарь стран Восточной Азии*
  (1987) and *Календарные обычаи и обряды народов Восточной Азии* (1989)
  have no full text online; abirus.ru's copy of Вяткин's index times out
  (coollib.com book 523632 has the volume with its page marks).
- **The solar terms' sources** (K6-R, 2026-10-15): the NAOJ 暦Wiki page
  「二十四節気」 (…/C6F3BDBDBBCDC0E1B5A4.html) and koyomi8.com/sub/24sekki.html
  answer 404; the pages are 「季節/二十四節気とは？」 and koyomi8.com/24doc.html.
  The 暦林問答集 has no section on 節分, 彼岸, 八十八夜, 入梅, 半夏生 or 二百十日
  (contents, pid 14488380 img. 3–4, 36–37). 呉澄's 月令七十二候集解 is in the
  NDL only as modern reprints, none digitised. NDL IIIF full-size image
  URLs answer 307: fetch with `curl -L`. The NDL catalogue answers 429 when
  several readers search at once (`ndl.py` now retries and prints pids).
- **The 暦林問答集** (K3-R, 2026-10-15): the 文化8 (1811) printing (NDL
  record 000007481577) is not digitised; the NDL Lab full-text API answers
  "This PID is not allowed" for pid 14488380; no table of the 三箇悪日 and
  no birth-year rule in §61 or §64; no section on the 六曜, the day's 九星,
  大明日, 節徳日, 鬼宿日, 受死日, 十死日, 天火日, 地火日, 一粒万倍日,
  不成就日, 三隣亡, 十方暮, 八専, 犯土 or the 縁日 (contents, GR img. 3–4,
  36–37).
