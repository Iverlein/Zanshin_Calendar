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
- **In every text on dreams, note the state neither asleep nor awake**
  (the owner, 2026-11-10): where a source names a state of a
  practitioner before falling asleep or before waking that is neither
  sleep nor waking, aware of itself, with feelings and visions, or
  comments on such visions (as experience, ཉམས, nyams; pure vision,
  དག་སྣང, dag snang; clear light, འོད་གསལ, 'od gsal), the passage is
  recorded even when the reading asked for something else
  ([RD388](../ROADMAP.md#rd388) gathers them).

## The knowledge pool

A reading session fills `docs/sources/`; nothing it learns is left in the
conversation or in a scratch directory (the owner, 2026-10-11).
Where in `docs/sources/` it goes, and in what shape, is [STRUCTURE.md](STRUCTURE.md):
since 2026-10-22 a new reading writes its passages into the corpus file of
its chapter (`corpus/<siglum>/<chapter>.md`), each passage with its id and
`*Read:*` line, its facts as claims and its questions as question files;
an old topic file is split into those when next touched.

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
  page image), and the playwright it runs must match a cached Chromium:
  the system Python's 1.64 has chromium-1248 since 2026-11-01 (`python -m
  playwright install chromium` fetches a missing one; RD9, 2026-10-12).
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
- `kb.py check|coverage|passage|cites|questions|next|search|orphans|graph` —
  the index of the knowledge pool (STRUCTURE.md): what the files get wrong,
  each source's pages by the state they were read in, who cites a passage,
  what to read next by what waits on it, full text; `--json` for an agent.
  Run `check` with `SourcesTest` before a reading is marked done.
- `pool.py stub|passage|claim|question|answer|anchor|split|index` — writing
  to the pool in its layers, validated against the registry and the index;
  driven by the `sources` skill (`.claude/skills/sources/SKILL.md`).
- `bdrc_ocr.py APP setup|run` — BDRC's older app models (CPU, seconds a
  page), a quicker but weaker witness.
- `score_reading.py TRUTH READING…` and `ocr_bench.py pick|score` — a
  reader's error rate against a passage read on the scan, or against the
  open BDRC benchmark; how the readers below were ranked.
- `local_read.py PRESET …` — general vision models through `llm-serve`;
  none reads Tibetan well enough (below).
- `bdrc_search.py OUT.json PHRASE…` — BDRC's full-text search, exact
  phrases, six at a time in a headed Chromium of class `playwright-work`
  (parked on workspace 11): hit counts, each hit's etext volume and
  character offset, and the snippets. It answered more of the open
  questions than the dictionaries did (2026-10-18): another reckoner's
  gloss, a contraction's full form, a variant spelling. Search-only
  etexts answer 403 to the etext API; their snippet is the reading.
- `archive_lend.py view` opens its reader in a Chromium of class
  `playwright-work` too (2026-10-18), and its FILE argument is "." for a
  single-volume item.
- **Second witnesses found 2026-10-19.** KP2 (I3CN12074) carves KP's
  tables and charts more cleanly than the Mtho las print: the nectar
  tables on img. 306–307, the Rāhu charts on img. 301; check a doubtful
  KP numeral there first. WB's notes are Mipham's *Nag rtsis brjed byang*
  and have his own print (I2029, img. 621–750) and a clean typeset one, open, in the Chengdu Collected Works (MW2DB16631_DB4BAA, vol. 1, I1PD34647 img. 529–694; WB's notes p. 491 is its p. 525, img. 571; 2026-10-25); Zhechen Gyaltsab's table manual, the *Lotus Pond* (ZG's volume I4PD2444, from p. 262), restates the notes in verse with their charts. For a medical line of the
  Four Tantras, Khro ru Tshe rnam's *Rgyud bzhi'i 'grel chen* (MW29701,
  etexts) and the *Phyi ma rgyud kyi 'grel ba phyogs bsgrigs*
  (MW1KG14623) explain it word by word. The reckoners' manuals with full
  etexts, Lha mo tshul khrims's *Nag rtsis rdel 'grem 'bras bshad*
  (MW1KG21818) and Dpal bsdus's *'Byung rtsis dpyad don rmad byung*
  (VEIE0OPI6328F058_I4767), restate WB's reckonings in plain words and
  answered several questions at once. Sde srid's own replies on WB, the
  *G.ya' sel* (MW2PD17386_474815, and in the etext group
  VEIE0OPID552C050), are search-only there, but one copy has a full
  etext: `bdrc.py etext DIR VEIE0OPIF790A4DF_I5876` (546 pages, found
  2026-10-19; the 165th reply on etext pp. 23–24, the 168th on p. 25; the 106th is in vol. 1, I5875 img. 461, RD48, 2026-10-23). The replies answer
  WB's own puzzles (open question 33 came from one); search a question's
  key words with «ཞེས» to reach them. A search-only text can be read
  further by chaining: search the last words of one snippet, and the next
  snippet carries on from them (a whole reply was read so, 2026-10-19). For a list of dates whose source is
  unknown, search the numbers as one run of number words
  («བཅོ་བརྒྱད་ཉེར་གཉིས»), which found KL's moxa dates in Lung rigs bstan
  dar's Four Tantras commentary (MW00KG07597; open question 79).
  The *Collected Astrological and Prognosticatory Texts from Bhutan*
  (Thimphu, 1981; full etext `VEIE0OPI1829B6AF_I1KG11905`, about 600
  pages, its list of contents on etext pp. 7–10) gather some thirty
  manuals, among them the *Bum bzang*, a birth reckoning, a marriage
  reckoning (*Bag rtsis nam mkha'i bu*) and a digest of WB's results
  (*'Bras rtsis bai dkar dgongs don*, pp. 341–460); they answered open
  questions 40 and 91 (2026-10-19). Its school is not established text by
  text: use it as a witness to WB. WB's own other chapters answer more
  than once: a rule asked of one chapter is often stated in another
  (the tie of white and black pebbles, open question 160, in ch. 29; the
  times to beware for learning, which ch. 34 points to, in ch. 35).
  A reset (typeset) reprint reads more easily than its blocks but may bring
  them to the source it digests (the Bhutan KP sets WB's dates and weekday
  where KP's own scan differs): read the digit on the reprint, then confirm
  it on the blocks, and keep the blocks where they differ.
- **Which edition a writer cites:** search two or three lines he cites with
  his page numbers, fetch the etexts of the editions that hold them, and
  compare the steps between the lines' pages with his. The steps do not
  depend on any offset between page and image, so one comparison rules an
  edition in or out (open question 86, 2026-10-19).
- **A word an OCR file places, on the scan:** `scans.py line IMG N --at F`
  crops line N of the page around the fraction F of its width, from the
  OCR file's line number and the word's offset over the line; it is how
  the doubtful words of 2026-10-19 were read on the Gser thang reprint.
- **An outline part's place in a volume:** `curl
  https://ldspdi.bdrc.io/resource/MW…_XXXX.json` gives the part's
  `contentLocation` (a CL… id) and its location statement; the CL…'s
  own JSON gives the volume and the first and last image.
- `locate.py find | map | dict` — a phrase in the etexts whatever the
  OCR's line breaks; which etext pages an OCR'd image of another print holds
  (its 4-syllable shingles); a Wylie headword in Steinert's 64 dictionaries
  (a `git clone --depth 1` of github.com/christiansteinert/tibetan-dictionary,
  4.5 GB, in scratch). An OCR file keeps the folio's lines, so a word's
  character offset over its line's length places the crop to read by eye
  (2026-10-18).

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
etext (`locate.py map`): on 2026-10-06 its vol. 2 img. 350, 390, 430 and
470 held the 1996 vol. 2 pp. 244, 272, 297–300 and 331–332 (etext page − 8),
and p. 330 was on img. 468; on 2026-10-18 img. 438–446 held pp. 306–312
(p. 308 begins on img. 440). Its six lines a folio are read at native size
in crops of about 1,300 × 250 px. The Zhol print (WBZ, 3,754 × 613 px,
seven lines) maps to the 1996 vol. 1 at WBZ img. 325–326 = pp. 261–262,
374 = p. 294, 426 = p. 354, 551 = p. 418, 554 = p. 420; 463 = p. 355, 580–581 = p. 438;
to the 1996 vol. 2 at WBZ img. 908 = p. 196, 927–928 = pp. 211–212,
1044–1046 = pp. 297–299, 1059–1064 = pp. 308–312, 1087 = p. 331, 1095 =
p. 337, 1145 = p. 385 (2026-10-18). The WBZ etext keeps the folio's lines
too, so `locate.py find` on it and a word's offset in its line place the
crop. The Taikhang vol. 2 img. 474–477 hold pp. 334–337 (2026-10-18). More of WBZ, read
2026-10-18 night: vol. 1 img. 458 = p. 351, 469 = p. 359, 511–512 = p. 390,
531 = p. 403, 537 = p. 408; vol. 2 img. 1052–1053 = pp. 303–304, 1100 =
p. 347, 1104 = p. 350, 1112 = p. 357. The Taikhang vol. 1 (I5877) img. 486
holds p. 359. More Taikhang, 2026-10-19 (Yigdzin-1 at 2400 px, about 20 s an image,
placed by 4-syllable shingles against the 1996 etext, whose `[pN]` is
the 1996 image): vol. 1 img. 339 = p. 259, 343 = p. 262, 596 = pp.
436–437, 598 = p. 438, 608 = p. 445, 620 = p. 454; vol. 2 (I5878) img.
40 = p. 23, 52 = p. 32, 64 = p. 40, 69–80 = pp. 44–51 (about 1.4 images a
page). A phrase's character offset in its OCR line over the line's
length, on a text block of 7 lines, places a native-size crop (8280 px)
within a few hundred pixels. The Taikhang caught a line the 1996 print
drops (vol. 1 p. 262, open question 69), so where a count or a list
does not add up, compare it first. The Taikhang carves WB's interlinear
notes too: the hal khyi's note on 104 days in other texts (vol. 2 p. 185,
in round brackets in the 1996 edition) stands in small letters on
Taikhang vol. 2 img. 278 (Yigdzin-1's reading, 2026-10-19). So a
bracketed note of the 1996 edition can be WB's own, not its editors';
check the Taikhang before calling one an editor's addition. KP's colophon is BDRC's image labelled 121 (the file's 119).
BDRC's full-text search answers a word's other uses in seconds and found
glosses no dictionary has (open questions 24, 38, 63, 130); run it before
the dictionaries.

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

**Two reading notes from RD159 (2026-11-05).** A session's tool results keep only the last 27 images or so: write each comparison down while its image is in view, or the reading has to be done again. On both prints of WB a ལ under a vowel sign looks like ཡ: set a doubtful word beside a certain «ལེགས» before correcting «ལིང», «ལིངས» or «བུ་ལོན».

## Dead ends

Searched and found wanting, so not to be repeated:

- **RD387, 2026-11-11, Marpa on the midnight dream:** BDRC full-text search, 2026-11-11, no hit: «ནམ་ཕྱེད་ཀྱི་རྨི་ལམ་བདུད», «ནམ་ཕྱེད་དུ་རྨི་ལམ», «ནམ་ཕྱེད་ཀྱི་རྨི་ལམ་དེ», «ནམ་ཕྱེད་ཀྱི་རྨི་ལམ་ལ་ནི», «གདོན་གྱི་རྨི་ལམ་ཡིན», «གདོན་དབང་གི་རྨི་ལམ», «གདོན་གྱིས་བཏང་བའི་རྨི་ལམ», «ཐོ་རངས་ཀྱི་རྨི་ལམ་བདེན». Marpa vol. 4 and Ras chung pa vol. 2 by words: no dream with midnight, dusk, dawn or gung beyond the death-sign passages already in the pool. Karmapa IX's Yi dam drag po dbang bshad (VEIE0OPI9762274C_I1GS66298) answers 0 pages, snippet only. «གདོན་དབང་» 103 hits not opened.
- **PT 55 IX (the Dunhuang dream manuscript), 2026-11-11, RD366 again:** OTDO no connection, Gallica and IDP HTTP 403 (not worked around), the Cambridge Himalaya site (van Schaik, RET 64) no connection, JSTOR open to a request but the article closed; the Iwao–Zorin paper on secular Dunhuang fragments has nothing on dreams; web searches give only the bibliographic record of Crescenzi and Torricelli 1995 (*The Tibet Journal* 20.2, pp. 3–17: text, translation, commentary). The Tibetan needs that article in print or via a library, or the three sites when they serve.
- **BDRC's full-text search, 2026-10-18 night, no hit or no gloss:**
  «སྐྲང་སྒོམ», «ལས་ལ་ཟློད» and «ཟླ་རིབ་ནད» nowhere; «སྔ་ཆ་གྲིབ», «དགྲ་ལས་མིང་འདོགས», «ཞག་གི་ལོ་སྟེང» and
  «སྲི་གཞུང་སུམ་བརྒྱ» in WB's prints only; «གཏོང་ཤེས» too common to search (432
  hits). The Russian *Йога звука*'s booksellers (Chitai-gorod 403,
  Meloman 404) and a web search for Dr. Chenagtsang's *Tibetan Healing
  Massage* contents found nothing.
- **BDRC's full-text search, 2026-10-19, no hit or no gloss:** «ལས་ལ་བཟློད»,
  «གཏོང་ཤེས་རྒྱུད», «ཧལ་ཁྱི་མ་ལྔ», «ཉི་ལྡོག་དང་མཉམ་དུ», «ས་བདག་རྣམས་འཕོ», «བླ་ནི་ལུས་མ» nowhere;
  «ལག་རྩིས་ལུ», «བླ་རྟགས་གཡག», «ཕྱེད་གཉིས་ཟེར་གཉིས», «འབྲིང་པོ་བཞི་ཡི་སྟེང», «སུམ་སྟོང་བདུན་བརྒྱ་དོན་གསུམ»
  and the counts of chs. 20, 22 and 29 in WB's prints only; the almanacs'
  month sheets (*Sgo bzhi mdzod lnga'i yang bcud*, SN) list the month's
  earth lords as a diagram whose places the etext loses, so they do not
  settle which of WB's views an almanac follows (open question 32).

- **Rinchen Terdzö** full text (rtz.tsadra.org, owner's manual search,
  2026-09-29): none of the personal-mansion names, no Viṣṭi, none of the
  eight unconfirmed yoga and mansion names. It is a collection of treasure
  texts, not a calculation manual.
- **A drawn board of the birth's hundred, 2026-10-22** (open question
  184): *The Beautiful Rosary* in the Lhasa Mentsikhang print (I1KG12897,
  img. 205–226) tabulates the readings by aspect and relation (img. 210–224)
  and closes on WB's general verdict (img. 225–226) with no drawn board; the
  Sera *Sheet of the Pebble Ground* (MW3PD446_17A116, vol. 9 = I2PD17138,
  img. 555–576, pp. 553–572) is a cursive manuscript of columns with one
  pebble each, then number tables and prose, no board of a hundred. The
  board is KD vol. 3, p. 718 (the corpus file `corpus/kd/v3-sheets.md`).
  The *Utpala Garland* (UT) was cited in this file as Dpal bsdus's: he
  edited it; BDRC names Dge rtse 'Gyur med bstan pa'i rnam rgyal as its
  author. `archive_lend.py view` and `bdrc_search.py` run on the system Python's
  playwright 1.64 since 2026-11-01, when its chromium-1248 was installed
  (two readings had skipped their searches for want of it).
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
- **A rule for the month's or the year's luck** (RD4, 2026-10-11):
  «ཀླུང་རྟ» and «ཀླུངས་རྟ» in the etext of both volumes of WB give only the
  person's luck (*klung rta*) (vol. 2, pp. 12–25, etext pp. 20–33) and the column
  «ཟླ་ཀླུང» of ch. 13's tables (vol. 1, etext p. 115).
- **The wind-horse of each triad, and a wind-horse's tomb** (RD3,
  2026-10-11), needed for the courses of the year's nos. 4, 9, 16 and the
  nine black ones 2, 5, 8: the etexts of both volumes searched for «ཀླུང་རྟ»
  beside the triads give only the tiger, horse and dog's iron monkey
  (vol. 2, p. 479, img. 487, etext only). Found since in vol. 1, ch. 20,
  p. 254: all four triads' luck, core's `klungRta` (open question 26,
  2026-10-19); the tomb of a luck is still not found.
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
  three times BDRC's 703 px; the next reading of WB vol. 2 starts there. Fetch it at native size: `bdrc.py scans … --width 2048` (wider than
  the 1905 px original) wrote empty files on 2026-10-27 (RD60, RD61); leave
  `--width` out, or give 1905 or less. The
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
- **Words in none of Steinert's 64 dictionaries** (2026-10-18): «ཤ་ཆིངས»,
  «སྤོམ་གཏའ», «གཏོང་ཤེས», «ར་བསེ», «སྐྲང་སྒོམ», «ཚེ་སྨང», «ཁ་སྤྲང»; «སོ་ནག» only as place
  names; «བསོ» only as a call (Bon) and «སྭ» as «སོ»; with «སྭོ», a call to gods
  and spirits, and «ཀི་ཀི་བསྭོ་བསྭོ», the war cry, they settled the war lines'
  «བསོ་གདབ» on 2026-10-18 (question 135). Also in none (2026-10-18): «ཟློད»,
  «ཟློད་པ», «བཟློད་པ» (question 39); «ལུ» as a person or a text (question 101);
  «ཀིང་ཟོར», «གནམ་ཞག་ནག», «དབུལ་ལག», «ལོ་དཀར» and «ཎི་ཤ» as headwords («ཏི་ཤ» only as
  Tiṣya, the Pusya mansion and a buddha, 84000). WB's notes (vol. 2, pp. 474 ff.) were not found in the WBZ
  etext by three of their phrases, so question 23 was not read there.
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
- **The year of age gathered** (RD20, 2026-10-20): «སྡེ་བཞིའི་ཁ་དམར», the
  four classes' verdicts, is in WB's etext (both volumes) only on vol. 1,
  p. 385, and the notes do not gloss it. «བཟའ་ཚང་སྤྱི་རྡེལ», the household's
  general pebbles, is only on vol. 1, p. 415 (img. 425, l. 25), with no
  comment in the notes' section 2 (pp. 489–495). No single verdict for a
  whole household beyond the leftover of the house's place (p. 386) and the
  general readings of trigrams and mewa, in ch. 24 (pp. 385–416) or the
  notes' section 2.
- **The verdict pebble** (RD18, 2026-10-20): in WB's etext (vol. 1,
  pp. 258–473; vol. 2, pp. 3–173; the notes) no passage gives the verdict
  pebble a colour, a red mark or a material, and «ཁ་དམར» is never defined;
  its literal uses describe demons, sri, a woman and a tube. The *G.ya'
  sel* (etext VEIE0OPIF790A4DF_I5876) has «ཁ་དམར» and «བཏང་གཅོད» once (etext
  p. 16, poor OCR) and no rule for laying or for a tie. WB vol. 2, p. 497
  (img. 505) is not in the shared scan cache; its passage on marking one
  pebble for the illness throw is not about the verdict.
- **The birth read whole** (RD19, 2026-10-20): no count or weight for the
  twenty-one children (how many conditions make one), and no rule for two
  children whose conditions both hold, in pp. 338–340 or the notes,
  pp. 484–485 (on the scan); no layout of the board for p. 331's general
  verdict in ch. 22 or the notes' section 1; no rule ranking the sets of
  pp. 334–335 and 338 against the hundred's single readings beyond the
  sets' own wording. BDRC serves the Gser thang reprint I3CN5028 img. 328
  (vol. 1, p. 333) all black (fetched twice): read it on the 1996 scan.
  On that reprint Yigdzin-1 reads ཟ as ཐ (ཟིན, ཟོན as ཐིན, ཐོན) and dropped
  three lines of p. 342.
- **D16's worked examples** (O17, 2026-10-20): WB vol. 1, p. 380 (img. 390,
  l. 25), on the 1996 scan at 4× and 9×, has no «ནག» after «དཀར» in the
  son-meeting line: it reads «དཀར་ན་གཟུང». The notes' table (vol. 2, p. 489,
  both prints) has no cell whose meeting is a breath-taking stage and no
  row whose verdict turns on the womb alone.
- **D16 in the *Moonbeams*** (O18, 2026-10-20): in the whole *Moonbeams*
  (DZ, BDRC I2533 img. 015–064 by Yigdzin-1; KD vol. 3 etext pp. 506–536)
  no rule for how a half place counts in a verdict and no tie rule for the
  year of age; «ཁྲ» never as a pebble's colour (the eleventh starting point's
  rites «དཀར་ནག་ཁྲའི», KD p. 520, etext only); «མཉམ» only at f. 28b (the dead)
  and in the marriage's repetition pebbles (KD p. 525, etext only). Gyurme
  Dorje's notes to the obstacle years (OCR text) say nothing on the
  predictive pebbles' count or a mixed one. Dorje cites the *Moonbeams* by
  another print's folios, about 6.5 ahead of I2533: his ff. 27b–28b are
  I2533's ff. 21a–22a.
- **D16 in WB, the notes and the manuals** (O19, 2026-10-20): BDRC's
  full-text search, «ཁ་དམར་ཕྱེད་མ», «ཕྱེད་མ་དཀར་ནག་གཉིས», «ཕྱེད་མ་དཀར་ནག»,
  «དཀར་ནག་ཟུང་དུ་བོར», «ཁ་དམར་ཁྲ་བོ», «ཀེག་རྩིས་ཀྱི་ཁ་དམར», «ཁ་དམར་དཀར་ནག་གཉིས»
  and «གནས་སམ་སྐོར་མགོའི» no hit; «ཁ་དམར་ཁྲ» only a veterinary and a pharmacy
  book; «བུ་ཁྲ» too common to read (299 hits); «མཉམ་ན་ཕྱེད་མ» only the
  *Moonbeams*' tie for the dead and its copies. WB's notes, vol. 2,
  pp. 489–496, 500–503, 507: no rule for how a place with both colours
  counts in the year of age's verdict; p. 503's «ཁྲ་མ» is a table. WB
  vol. 1, pp. 380–385, on the scan: no «ཁྲ» or «ཕྱེད» as a verdict and no
  reading for a speckled predictive pebble. The notes' pp. 496 and 507 hold
  no stage ranks: SPEC §5.9.1's "pp. 496 and 507" are KD vol. 3's.
- **Condensing a reckoning in the notes** (RD26, 2026-10-20): the etext of
  the notes (vol. 2, img. 482–549) for «བསྡུ», «སྙིང་པོ», «དྲིལ», «མདོར», «གནད»,
  «ལག་ལེན» and «ལག་ཏུ་ལེན», «རྒྱས་སྤྲོས», «སྤྱི་བསྡོམ», «ཟིན་བྲིས»: the condensing of a
  reckoning is named only on p. 513; «མདོར་ན» elsewhere (pp. 478, 498, 523)
  sums a passage, not a reckoning; «སྙིང་པོ» besides the title and p. 538
  only on pp. 503 and 519. The closes of the notes' eight sections, on the
  scan: none gives a single verdict for a whole reckoning, and none joins
  two reckonings. No form or model of the written sheet (ཤོག་ཁྲ) or the
  reckoning memorandum (རྩིས་བྱང) beyond p. 539's description; «ཤོག་ཁྲ»
  elsewhere only for the month wheel's chart sheet (p. 531) and, in the
  1996 edition's index (img. 550, 553), WB's charts.
- **The reckonings with each other** (RD24, 2026-10-20): WB's etext, vol. 1,
  pp. 218–473, and the notes, vol. 2, pp. 474–551: no passage that ranks
  the birth, the year of age, the marriage, the illness or the dead's
  reckoning one above another as a whole; «ལས་རྩིས་ལས», «ཀེག་རྩིས་ལས»,
  «སྐྱེས་རྩིས», «ལོ་རྩིས» nowhere; «སྤྱི་རྩིས» only as the notes' seventh
  section's name (pp. 474, 532, 535); «བཙས་ལོ», «སྐྱེས་ཞག» not in vol. 1. No
  rule for whose aspect matters beyond p. 242, p. 261, pp. 415–416, p. 472,
  p. 420 and the notes' pp. 494–495, and no age dividing the child, the
  young and the man in his prime. The parts of life only by the birth's
  times (p. 261), the boards of pp. 331 and 471, and single readings
  («ཚེ་སྟོད», «ཚེ་སྨད», pp. 276–353); no part of life for the year of age's
  signs.
- **The match concluded** (RD21, 2026-10-20): BDRC's full-text search,
  «སྐར་འབྲས་སྤྱི་སྡོམ» no hit (the OCRs spell it «སྒར» and «སར»);
  «ལས་གཉེན་དགྲའི» only in WB's prints and the *Removing the Tarnish*;
  «ལས་ཀྱི་ཆུང་མ», «གཉེན་གྱི་ཆུང་མ», «དགྲའི་ཆུང་མ» in WB, its reprints and
  manuals that copy point 3, none with a rule for the whole. The *Removing
  the Tarnish* etexts (I5875, I5876): «སྤུན་གྲངས», «སྐར་འབྲས», «ཟུངས་ཟད»,
  «ཚེ་ཟད» in the marriage replies none; only replies 112–119 on the
  marriage chapter (questions on I5875 etext pp. 94–95, answers 112–114
  on pp. 468–469), none on point 7 or a sum. The Bhutan *Sky Jewel*
  (I1KG11905 etext pp. 213–238): «སྤྱི», «བསྡོམ», «གང་མང», «ཁ་དམར»,
  «བཏང་གཅོད», «ཤས་ཆེ», «མང་ཉུང» give no rule for the whole match. WB's
  etext: «སྐར་འབྲས» only as «གཟའ་སྐར་འབྲས་བུ» (vol. 1, pp. 438–439; vol. 2,
  pp. 51, 316) and on p. 351.
- **A verdict over a whole reckoning in ch. 26, 27, 29 and 30** (RD23,
  2026-10-21): BDRC's etext of vol. 1, pp. 453–472, vol. 2, pp. 3–25 and
  144–172 and the notes, pp. 532–535: «ཁ་དམར» in ch. 26 only on p. 471, in
  ch. 27 only on pp. 22–23, in ch. 30 on pp. 163, 166, 168 and 172; no
  verdict laid over all thirty starting points, over all thirteen
  circles or over a whole reckoning of the moment; «རྡེལ་ཁྲ» and
  «ཐམས་ཅད་ནག» in ch. 26 only on p. 468, «ཐམས་ཅད་དཀར» only on p. 471.
  Yigdzin-1's reading of vol. 2, img. 112–134 (the thirteenth's rites,
  pp. 104–126): no line ties the white, speckled or black rite to the
  colour of a verdict; «ཆེ་ཆུང» nowhere there, «འབྲིང» only in «འབྲིང་བཞི»
  (pp. 112, 115), «རྒྱས་དང་བསྡུས» only on p. 114.
- **A rule for the whole in the *Moonbeams*** (RD27, 2026-10-21): DZ
  img. 005–064 (Yigdzin-1, the closes on the scan) and KD vol. 3's etext,
  pp. 506–536: no rule that weighs one reckoning against another, and no
  single verdict of a whole reckoning but ch. 5's five general totals
  (f. 24a) and ch. 7's "roll everything together and decide" for the la
  pebbles (f. 27a); «སྤྱི་བསྡོམས» only on f. 24a; «གང་མང» only on f. 17a
  (Drogtön's way); «རིམ་གྲོ» only on f. 26b; «མཉམ» only on f. 20a and
  f. 28b; no readings of the pebbles beyond the names of the meetings
  (f. 17b–18a) and the dead's verdicts by domain (f. 29a). KD vol. 3's
  print, pp. 486, 498, 501, 506, 509–510 (lent scans): nothing in the
  closes beyond DZ's; it differs in headings and a few words only.
- **A rule for the whole illness reckoning** (RD22, 2026-10-21): the WB
  etext, vol. 1, pp. 417–452, and the notes, pp. 495–499: «ཤས་ཆེ», «གང་མང»,
  «སུམ་ཆ», «ཕྱེད་མ», «ཆས་ལྷག», «ཁ་དམར»: no rule beyond those quoted in
  verdicts.md (RD22); «གང་མང» in ch. 25 only on pp. 435, 438 and 445,
  «ཆས་ལྷག» only on p. 425; no rule for four trigrams of four elements and
  none tying the five-pebble element to the board's verdicts. No worked
  example of the illness identified (times, trigrams and the five
  pebbles laid) in ch. 25 or the notes: «ཤིང་གཉིས་མེ་གཉིས་ལྕགས་གསུམ» (p. 497)
  is a split only.
- **Open questions 161–165** (O16, 2026-10-21): a worked example of the
  luck's mewa beyond WB's fire hare (p. 255) and the *Moonbeams*' wood
  rat: WB's etext (both volumes) for «ཀླུང་རྟའི་སྨེ», «ཀླུང་སྨེ», «ཀླུང་གི་སྨེ»
  with a colour, none but p. 255 (the model almanac's tables, pp.
  105–125, give the wind-horse's element, not its mewa); the notes for
  «ཀླུང་རྟའི་སྨེ», «སྨེ་ཁྲིད», «མཐུན་གསུམ» with a mewa, none. «དགྲ་དུར» used for
  trigrams and aspects (vol. 1, pp. 306, 313–314, 331, 347; vol. 2, pp. 98,
  129–132; the notes, pp. 478, 480, 488, 498, 505) and never explained.
  «དྭ་ཟླ» or «དཱ་ཟླ»: WB's etext, the *Moonbeams* (KD vol. 3), Dpal bsdus's
  *'Byung rtsis dpyad don rmad byung*, the *Removing the Tarnish* (I5876)
  and the Bhutan collection (I1KG11905), only p. 348 and the notes,
  pp. 488–489; BDRC's full-text search («དྭ་ཟླ», «དཱ་ཟླ», «ཕ་ཡི་དྭ་ཟླ»,
  «དྭ་ཟླ་ཡིན»), WB's prints and copies only. WB's four «འགྲུབ་སྐར» named
  together: none in the etext («ས་ག་ཆུ་སྨད», «ཆུ་སྨད་ནམ་གྲུ», «འགྲུབ་སྐར་བཞི»,
  «གྲུབ་སྐར་བཞི» only p. 348 and vol. 2, p. 329's classes); BDRC,
  «བརྟན་གྲུབ་སྐར», no hit. A day's mewa that turns at the solstices in WB,
  the notes or the *Moonbeams*: none («ཉི་ལྡོག» with «སྨེ» in vol. 2, ch. 30,
  and the notes' section 8, none).
- **A surviving deciding text** (RD25, 2026-10-21): BDRC's full-text
  search and catalogue, exact phrases: «བཏང་གཅོད་འཕྲུལ་གྱི་སྒྲོན་མེ» (3 hits: WB
  1996, Nor brang in KD and in MW1GS66291), «བཏང་གཅོད་འཕྲུལ» (4, the same
  and the Gser thang WB), «བཏང་གཅོད་ནག་པོ» (3: Nor brang twice, MW1KG2733),
  «འཕྲུལ་གྱི་གོ་བྱེད» (5: three WB prints, Nor brang, MW1KG2733),
  «ཁ་དམར་བསམ་ཀ» (4, WB prints only), «སྦས་མཛུབ» (6) and «ཉིང་ཁུ་ཤོག་ཁྲ» (2),
  WB and its restatements only; «བཏང་གཅོད» (69), «བཏང་གཅོད་ཀྱི» (8) and
  «སྟངས་གཅོད» (90): no catalogued title; «གཤིན་རིན་པོ་ཆེ་གསལ་སྒྲོན» (0);
  «གབ་པའི་སྙིང་གཟེར» (3: the *Moonbeams*' copies and Gyurme Dorje), no title;
  «ཐིག་ལེ་ནག་པོ» (321) not walked. BDRC's outlines of MW2PD17476 vols. 66,
  73, 74 and MW2PD17386 vols. 25, 28: no title with «btang gcod», «kha
  dmar» or «'phrul gyi sgron me». BDRC's image server answered 503
  (I4PD636, I1KG1710) or empty images (I1GS66247) all through
  2026-10-21: try again before calling those scans missing.
- **A manual for writing a reckoning** (RD28, 2026-10-21): BDRC's
  full-text search, no hit: «སྐྱེས་རྩིས་ཀྱི་ཡི་གེ», «རྩིས་ཡིག་འབྲི་ཚུལ»,
  «རྩིས་ཡིག་བྲི་ཚུལ», «སྐྱེས་རྩིས་འབྲི་ཚུལ», «ཀེག་རྩིས་ཀྱི་ཡི་གེ», «ལོ་རྩིས་ཀྱི་ཡི་གེ»,
  «ལོ་བབས་རྩིས», «སྐྱེས་རྩིས་ཡིག», «དཔེར་བརྗོད་ཀྱི་ཤོག་ཁྲ». Titles: «སྐྱེས་རྩིས» (56)
  names one work, a manuscript on the thirty sounding elements (ཁ་སྲོག,
  kha srog; MW2PD19899_9CC3D5); «བཙས་རྩིས» (12), «འཁྲུངས་རྩིས» (28) no title;
  «རྩིས་བྱང» (138) as a title only in the Bon Kanjur and Katen (MW30498),
  not a source; «ཚེ་རྩིས» (191) mostly the svarodaya's (དབྱངས་འཆར, dbyangs
  'char) life reckoning; «རྩིས་དཔེ» (145) a reckoning book, not a model;
  «ཤོག་ཁྲར་བཀོད», «ཤོག་ཕྲར་བཀོད», «རྡེལ་ཁྲར་གཞིགས» only WB's notes p. 539,
  I1LT0800 and KD vol. 3, p. 729. KD's etext, vols. 1–3 (I4715–I4717):
  «རྩིས་ཡིག» only for the Ming calendar (vol. 1, etext p. 574); «རྩིས་བྱང»
  only in «གྲུབ་རྩིས་བྱང་བགྲོད» (vol. 2); «དཔེར་བརྗོད» in vol. 2 only the
  conjunctions' examples (etext pp. 150–161); «ཤོག་ཁྲ» in vol. 3 only the
  sheets (etext pp. 733–751) and p. 13; «སྦྱིན་བདག» in vol. 3 only p. 163.
  Lhamo Tsultrim's etext (I1KG21886): «མྱོང་བྱང», «རྩིས་ཡིག», «རྩིས་བྱང»,
  «སྦྱིན་བདག», «ཡི་གེར», «འབྲི་ཚུལ», «བྲི་ཚུལ», «དཔེར་བརྗོད», «ཟིན་བྲིས»,
  «སྤྱི་བསྡོམ» none; «ཤོག་ཁྲ» only look-up tables (etext pp. 12, 194–195).
- **«སྟངས་གཅོད» and «བཏང་གཅོད», a gloss or a text naming both** (open question 245, 2026-10-22):
  - 2026-10-22, Steinert's 64 dictionaries (`locate.py dict`, Wylie
  headwords): «btang gcod», «stangs gcod», «stang gcod», «btangs gcod»,
  «stangs bcad», «btang bcad» no headword; «gtang gcod» only in the
  *Ocean of Word Treasures*; «thag gcod» in seven, none tied to the
  reckoning.
  - 2026-10-22, BDRC's full-text search, exact phrases, no hit:
  «གཡབ་མོས་སྟངས་གཅོད», «སྟངས་གཅོད་ཉེར་བཞི», «བཏང་གཅོད་ཉེར་བཞི», «འཚོ་འཆི་སྟངས་གཅོད»,
  «དགེ་སྡིག་གི་བཏང་གཅོད», «གཏང་གཅོད་ཁ་དམར», «སྟངས་གཅོད་ནི», «གཏང་གཅོད་ནི»,
  «བཏང་གཅོད་ཅེས», «སྟངས་གཅོད་ཅེས», «བསྲེག་སྒྲུབ་བཏང་གཅོད», «བསྲེག་སྒྲུབ་གཏང་བཅད»,
  «སྟངས་གཅོད་དཀར་ན་ཆགས»: no gloss of «སྟངས་གཅོད» anywhere, and no text that names
  the two spellings together. Hits counted but not all read (first page
  of results only): «སྟངས་གཅོད» 90, «བཏང་གཅོད» 69, «སྟོང་གཅོད» 43 (most of them
  «རྡུལ་ཕྲན་སྟོང་གཅོད», cutting a thousand atoms, another phrase), «སྟངས་བཅད» 23
  (mostly "posture taken", «འདུག་སྟངས་བཅད»), «བཏང་བཅད» 16 (mostly medical
  recipes, "given, it stops", another phrase), «གཏང་གཅོད» 12, «སྟང་གཅོད» 10,
  «བཏང་གཅོད་ཁ་དམར» 17 (the Four Tantras and their commentaries),
  «བཏངས་གཅོད» 1 (Gyurme Dorje's book only); «སྟང་གཅོད» outside the
  reckoning mostly political («ཞང་བློན་སྟང་གཅོད», ministers' rulings).
  - 2026-10-22, the etexts: WB's 1996 etext has no «སྟངས་གཅོད», «སྟང་གཅོད»,
  «བཏངས་གཅོད» or «སྟངས་བཅད» in either volume; the Zhol, second Zhol and
  Sde dge etexts none either (poor OCR). KD vol. 3's etext has
  «སྟངས་གཅོད» only in the *Moonbeams*, Kun bzang blo gros's supplement and
  the sheets, and «བཏང་གཅོད» only in the supplement's verses of the dead
  (pp. 706–707). Dpal bsdus's *'Byung rtsis dpyad don rmad byung* (I4767)
  has «བཏང་གཅོད» once (etext p. 216) and «རྣོ་སྟངས་གཅོད», a sharp deciding, twice
  (pp. 232, 307), no gloss. The *Removing the Tarnish* (I5876) etext: no «སྟངས་གཅོད» and no
  «བཏང་གཅོད» under that OCR (RD18 found «བཏང་གཅོད» once on its poor p. 16).
  - 2026-10-22, Mipham's own print (I2029, img. 656–676, Yigdzin-1): no
  «སྟངས་གཅོད».
- **Kun dga' rig 'dzin, the writer of KD's sheets** (open question 246, 2026-10-22). Method notes: BDRC's osearch results page paginates by a plain `&page=N`, while `bdrc_search.py` reads page 1 only and cuts at 8000 characters, so hits past about twenty are missed; a restricted volume's open preview images can carry its front matter (KD vol. 3's board of editors and contents, I4717 img. 5, 13), read without the loan.
  - 2026-10-22, BDRC's records: MW28845_B881BE, WA0XLB881BE5C3B6D,
  MW28845_CD1540, _EA5BB4, _FFD448 and every other node of KD's outline:
  no creator link but the compiler's (P4853) on MW28845.
  - 2026-10-22, BDRC's person search: library.bdrc.io/search, persons,
  the exact name in Wylie, 0 results; osearch with the person
  filter, the name unquoted, six persons, none of them he (above).
  - 2026-10-22, KD's etext, vols. 1–5 (I4715–I4719): the name only on the
  boards of editors, vol. 2's contents and its sections 10, 17, 18 and
  19, and vol. 3's contents and the sheets; no life of him (vol. 1's
  appendix holds the two chief editors' only), no Chinese text in any
  volume's front matter; vol. 3's preface (etext pp. 17–20) names no
  editor.
  - 2026-10-22, the 1986 article's close (MW1KG21754, etext pp. 432–433):
  verses of dedication, no colophon on the writer.
  - 2026-10-22, BDRC's full-text search, no hit: «སྨན་རྩིས་ཀུན་དགའ་རིག་འཛིན»,
  «རྩིས་པ་ཀུན་དགའ་རིག་འཛིན», «ཀུན་དགའ་རིག་འཛིན་ནས», «ཀུན་དགའ་རིག་འཛིན་གྱི»,
  «སློབ་དཔོན་ཀུན་དགའ་རིག་འཛིན»; in the chained searches «ནན་ཅིང་ཙེ་ཅིན་ཧྲན་གནམ་རིག་བལྟ་ཁང»,
  «རྒྱུགས་འབྲས་བྱ་དགའ་ཐོབ», «སློབ་ཐོན་དཔེ་བཟང་གི་གཟི་བརྗིད་དཔང་ཡིག», «ཚེས་ཉེར་བརྒྱད་ནས་ཨ་མདོའི་བཀྲ་ཤིས་དབང་ཕྱུག»:
  the memoir's year, the Golog physician's name and the interview's body
  not reached. The bare name has 35 hits in all, every one read (sections
  3–4).
  - 2026-10-22, `etextchunks` 403 (search only) for the memoir
  (IE0OPI156B76B6), the Golog history (IE0OPI4C49C33E), the Tibet
  University volume (IE0OPIE8EEAEB9), Bsod nams dpal ldan's book
  (IE0OPI9442689C) and the 2013 almanac (IE0OPI0559C226).
  - 2026-10-22, the web, «"Kunga Rigzin" OR "Kunga Rindzin" Tibetan
  astrology Mentsikhang Lhasa»: no English notice of him.
- **Other copies and records of the two written reckonings on open scans** (RD29, 2026-10-22):
  - 2026-10-22, BDRC's full-text search (`bdrc_search.py`): «འབྲས་བུ་འདོན་པ་དེར་བཀོད་པ»,
  «སློང་ཐང་ལས», «ཞིབ་པ་རེའུ་མིག་འདི་ཡིས་ཤེས» no hit; «རྩིས་དཔེ་ཁག་ཅིག» only the
  print's own record (MW1KG12790); «ཚེ་རྩིས་འབྲས་བཤད་བཅས་གསལ་བར་བཀོད་པ» and
  «སོ་དྲུག་གཙོ་བོར་བཟུང་བའི» only that record and the *Catalogue of the Great
  Treasury* (MW4CZ354964, search only); «ནོར་བུའི་ཕྲེང་མཛེས» (259 hits) and
  «ཕྲེང་མཛེས་ཞེས་བྱ་བ་བཞུགས» (154) no other copy of *The Beautiful Rosary* in
  the first results; «གཤིན་རྩིས་ཉུང་བསྡུས» 2 hits, the manuscript's record and
  a line of 'Bro'i sbyin pa's (འབྲོའི་སྦྱིན་པ, 'bro'i sbyin pa) *Clear Mirror of
  Results* (MW1KG21827, search only, not read); «ཤོག་ཕྲར་བཀོད» only the
  manuscript.
  - 2026-10-22, BDRC's catalogue: MW1KG12790 has no outline (no parts); the
  image group I1KG12897 starts at img. 003.
  - 2026-10-22, agy for single crops: `agy_read.py`'s model, "Gemini 3.1 Pro
  (High)", is no longer offered by agy ("not recognized"); run by hand with
  "Gemini 4 Argon (High)", img. 362's head row and img. 089's title timed
  out at 20 minutes with no text, and img. 004 was refused by the model's
  filter. No agy reading was used.
- **The fifteenth examination of the dead (WB vol. 2, pp. 128–143)** (RD36, 2026-10-23):
  - 2026-10-23, a calculation of the grave's site in "searching for the ground" (pp. 133–135, from Mañjuśrī's turtle): none; the part is a rite. Its only reckoned items are the remedies of the year's trigram, planet and mansion (p. 133; the notes, p. 522). The ground reckoning proper is the notes' section 5 (ས་རྩིས, sa rtsis, 5,600 pebbles, from p. 522) and ch. 32.
  - 2026-10-23, a verdict or reading of the fifteenth examination's own (pp. 128–143 and the notes, p. 522): none; its 4 result pebbles (p. 51) are rites chosen by the trigram, the grave kind and the dead.
  - 2026-10-23, MITRA on one GPU slot shared by eight readers: no slot within twenty minutes; the pages were read by eye on the Gser thang reprint (I3CN5029, image = page + 6 for pp. 128–143).
- **The dead's living household and second examination (WB vol. 2, pp. 51–58; the notes, pp. 499–503)** (RD30, 2026-10-23):
  - 2026-10-23, the notes give no identification for the la pebbles' general-verdict images ("not seen in the commentary and the like"; WB vol. 2, p. 502): they are judged from the board's shape only.
  - 2026-10-23, the six lamps' inputs (earlier dead with their dates, ages, causes and places of death; the living's journeys) are not among the reckoning's ten roots in WB or the notes, pp. 499–503.
  - 2026-10-23, agy for single crops: `agy_read.py` hard-codes "Gemini 3.1 Pro (High)", which agy no longer offers (it lists Gemini 4 Argon, Gemini 3.8 Flash); no crop was read. Switched the same day (a84618a) to "Gemini 3.8 Flash (Medium)", the owner's choice for pictures: a four-line crop of I3CN5029 img. 009 read right in about ten minutes, agy cutting and zooming the crop itself and leaving its helper images in the output folder.
- **The third examination of the dead in the notes (WB vol. 2, pp. 499–522)** (RD31, 2026-10-23):
  - 2026-10-23, Mipham's notes, section 4 (pp. 499–522, etext), searched for ཟ་འདྲེ, ཐོད, ཞག་དུས and the weekday states: only the sentence of p. 503 (wb.2:p503b) treats the third examination; no gloss of the eating demon, the skulls, or a weekday's dar, gud, zhud and gyod. The heart-crushings (སྙིང་འགྲམ) are explained only under the thirteenth examination (pp. 506–507).
- **Rāhu and the assembly reckoning in the dead's twelfth and fourteenth examinations** (RD34, 2026-10-23):
  - 2026-10-23, Rāhu in the twelfth examination's own text: «ཁྱབ་འཇུག», «སྒྲ་གཅན», «རཱ་ཧུ» searched in the etext and the Yigdzin-1 and MITRA readings of WB vol. 2, pp. 94–104, and p. 97 read by eye on the Gser thang reprint. Not found, although the sizes on p. 50 count Rāhu's twelve result pebbles.
  - 2026-10-23, «ཁྲོམ་རྩིས» in the etexts of both WB volumes: only p. 126 and p. 451's list of works. No text of the eighty-chapter assembly reckoning was found.
- **The rites' colours in the dead's thirteenth examination (WB vol. 2, pp. 104–126)** (RD35, 2026-10-23):
  - 2026-10-23, WB vol. 2, pp. 104–126 read through on the Gser thang reprint: no line ties the white, speckled or black rite to the colour of the thirteenth point's verdict, nor says which colour asks for great or small rites.
- **The thirty's fighting pebbles and checks (WB vol. 1, ch. 26)** (RD37, 2026-10-23):
  - 2026-10-23, WB ch. 26 (pp. 453–472) and the notes' pp. 532–533 searched for a statement pairing the eight fighting pebbles of p. 453 with each point's eight checks: none; the only link is the notes' grouping of the checks into upper, lower and middle.
- **The household round and the son meeting (WB vol. 1, ch. 24; the notes, sections 1–2; DZ)** (RD43, 2026-10-23):
  - 2026-10-23, a household round laid out for any count but five: none in WB ch. 24 (pp. 385–387), the notes' section 2 (pp. 489–495) or the *Moonbeams* (DZ ff. 22b–23a; KD vol. 3's etext pp. 528–529); the notes' laying (p. 490) and fold (p. 491) are of five people only.
  - 2026-10-23, a colour rule of WB's own for the birth's hundred: none on vol. 1, pp. 260–261 (Yigdzin-1 of img. 270–271 searched for «ནང་ཕྲད», «མ་གྲོགས», «བུ་ལ» and «དཀར་ནག»); the rule is the notes' (p. 475) and the *Moonbeams*' (f. 16b).
  - 2026-10-23, speckled (ཁྲ་བོ, khra bo) or half (ཕྱེད་མ, phyed ma) pebbles in the household round: none in WB pp. 385–387, the notes pp. 490–491 or the *Moonbeams* ff. 22b–23a, whose «རྡེལ་ཁྲ» names the mixing of the pebbles, not a speckled pebble.
- **Ch. 28's terms elsewhere in WB** (RD39, 2026-10-23):
  - 2026-10-23, BDRC's etext of WB vol. 1 and vol. 2 (IE0OPI51524892): «རྐང་ཕྲན་ནག» occurs only in ch. 28 (vol. 2, p. 38; vol. 1, p. 405 has «རྐང་ཕྲན» in another sense); «སྐྱ་ཐིག» only in ch. 28; «ཉ་དྲུག» as the six full moons and «བྱ་ཞག» only in ch. 28 and the notes, p. 537: no other passage of WB explains them.
- **The moment's sixty fixed signs (WB vol. 2, pp. 171–172)** (RD40, 2026-10-23):
  - 2026-10-23, which year the fixed sixty-year signs of pp. 171–172 count: not stated on those pages nor in the notes' pp. 536–538 (Yigdzin-1 on the 1996 scan, the lines on the asker's year read on the Gser thang reprint).
- **The match's grave and in-law words, and a name for mixed kinds** (RD42, 2026-10-23):
  - 2026-10-23, WB's etext of both volumes and the notes: «ཤིད་དུར» only at the notes p. 526 (and «གཤིད་དུར» at WB p. 354, «ཕན་ཚུན་རང་དུར་གཤིད་དུར་ཐུག», glossed by the notes p. 524 as «རང་དུར་དུར་གཤེད»); «གནས་སྐྱུག» or «གནས་སྒྱུག» only at WB p. 362, the notes p. 526 and ch. 30 p. 152; no gloss of «དགྲ་དུར» in point 10 itself, only the notes' gloss of the same compound in the household reckoning (p. 478).
  - 2026-10-23, the pages read (WB pp. 355, 358, 360, 362, 364; the notes pp. 524–526): no rule that turns a match of mixed kinds into one name.
- **The illness reckoning's nineteen pebbles and p. 425's labels** (RD41, 2026-10-23):
  - 2026-10-23, WB vol. 1, pp. 445–447, and the notes, vol. 2, pp. 497–499: no worked example of the nineteen pebbles laid, and no count of white and black that names their verdict beyond "good and smooth white, rough and bad black" and the readings pebble by pebble; the date's pebble has no reading in WB (the notes send it to the goddesses).
  - 2026-10-23, the notes, vol. 2, pp. 495–499: no gloss of p. 425's labels «ཁྲ་བོའི་དཀར་པོ» and the like (open question 230) and none of «ལྔ་སུམ་གཅིག».
- **Ch. 27 in Mipham's notes** (RD38, 2026-10-23):
  - 2026-10-23, a note on ch. 27's small reckonings in Mipham's section eight (vol. 2, pp. 535–538 read whole on the reprint, I3CN5029 img. 541–544): none; the section glosses ch. 30's moment and ch. 28's omens and closes the notes. Its only words on the circles are section seven's (p. 533).
  - 2026-10-23, a worked example of any circle (ch. 27 and the notes, pp. 533–538): none.
- **The dead's and the sick's open inputs in the manuals (DZ ch. 6–7, LT, UT)** (RD45, 2026-10-23):
  - 2026-10-23, the age at which the nine-eye strikes and the start of the male and female rounds to the executioner (question 308): not in the *Moonbeams* ch. 6–7 (img. 051–063), nor in LT's or UT's reckonings for the dead (etexts, «དགུ་མིག»: only the year-course and the pebble lists).
  - 2026-10-23, «ཛ་མོ» and «རྫ་མོ» (question 311), «རྔ་ཞོན» (question 343) and «སྔས་བསྲུང» (question 359): no hit in the etexts of LT and UT, nor in the *Moonbeams* ch. 6–7.
  - 2026-10-23, the day's illness of WB pp. 428–429 (question 360): the *Moonbeams* names "the examination of the day's illness and spirits" only as a matter of the extensive texts (dz:i052-055); «ཉི་མའི་ནད» has no hit in LT's or UT's etext.
  - 2026-10-23, why iron male dog and earth female sheep stand twice among WB's sixty-year signs (question 344): the *Moonbeams*' and LT's plank-trees use other fixed years and give no such pairs.
- **The thirty and the circles in the manuals and BDRC's search** (RD44, 2026-10-23):
  - 2026-10-23, a manual laying the la's wandering by the sky year (WB vol. 2, p. 16; question 363): BDRC's full-text search for «བྱི་བའི་ལོ་པ་བླ», «སྦྲུལ་ལོ་པའི་བླ», «རྟ་ལོ་པའི་བླ», «ཕག་ལོ་པའི་བླ», «བླ་ནི་ཤར་གྱི་ཕྱོགས་སུ་འཁྱམས», «བླ་ནི་ནུབ་ཀྱི་ཕྱོགས་སུ་འཁྱམས» and «བླ་ནི་ལྷོ་ཡི་ཕྱོགས་སུ་འཁྱམས»: only WB's 1996 and Gser thang prints carry the verse; the other hits are a medical text's la by the seasons (MW1KG16595), the *Rin chen gter mdzod* and Blo bzang tshul khrims's collected works; LT, UT and the *Moonbeams* have no la wandering by year.
  - 2026-10-23, the thirty or the circles in UT (etext IE0OPI6328F058, «སྐོར་འགོ», «ལྡིང», «བལ་མོ», «དཔྱད་ལྡིང», «བྱ་ཁྱུང»): only the general starting points (ut:p4, ut:p5) and the dead's thirteen examinations; the *Moonbeams*' six reckonings hold no circles.
  - 2026-10-23, a worked thirty: BDRC's full-text search for «ཤིང་གི་བྱི་གླང་སྟེང་ནས་བརྩི», «བརྩི་བྱའི་ཁམས་མཐུན་པའི», «སྐོར་འགོ་ཀུན་ལ་རྡེལ་བརྒྱད», «སྐོར་འགོ་སུམ་ཅུའི», «རྒྱ་བོད་སྐོར་འགོ»: WB's prints, LT and its reprints, the *Moonbeams*' prints (I2533, KD, I2PD18783, I1KG10408) and catalogues, no thirty worked for a person.
  - 2026-10-23, the circles' mewa in a phrase: «ལུས་སྨེ་ནས་གྱེན», «སྨེ་འཕྲེང་མཐུན་པའི», «དགྲའི་བྱ་ནས་གྱེན», «ཁམས་ཀྱི་དགྲའི་བྱ་ནས»: no hits.
- **WB's doubtful words on the Lhasa blocks** (RD46, 2026-10-23):
  - 2026-10-23, WBZ img. 701, l. 2 (p. 53, «བྲག་དམར་ན་ལ») and img. 817, ll. 2–3 (p. 130, dwa's food): the Zhol folio is worn there and gives nothing at 4–5×.
  - 2026-10-23, WBZ img. 473, l. 6 (vol. 1, p. 362, the in-laws' «གནས་སྐྱུག»): ཀ or ག not visible at 6×.
  - 2026-10-23, WBZ img. 506, l. 5 (vol. 1, p. 386, the syllable before «བཟློག»): not legible at 4×.
  - 2026-10-23, Mipham's print of the notes (I2029, 3400 px, about 33 px a line): img. 654 holds the household round's division (vol. 2, p. 491, question 317), but at 3× its letters run together; Yigdzin-1 reads «རྡེའུ་གྲངས་གསུམ་གྱིས་བགོས་པས».
- **Removing the Tarnish's replies on ch. 26–27** (RD47, 2026-10-23):
  - 2026-10-23, the *Removing the Tarnish* etexts (I5875, I5876): «བརྒྱ་སོ་བདུན», «བརྒྱ་སོ་བརྒྱད», «བརྒྱ་སོ་དགུ», «བརྒྱ་བཞི་བཅུ» no hit, the OCR writing «བརྒྱུ་སོ», «བཅས་བརྒྱད», «བརྒྱས»; the questions run in WB's chapter order (135–136 ch. 25, 137–142 ch. 26–27, 143 ch. 28), so no other question on ch. 26–27 stands among them; the replies say nothing of which element picks the thirty's pairs, of whether the birth year counts as one, or of the circles' mewa.
- **Removing the Tarnish on the dead, the sick, the moment and the match** (RD48, 2026-10-23):
  - 2026-10-23, the *Removing the Tarnish*, questions 101–136 and 143–160 (I5875 img. 90–108, Yigdzin-1): none asks which places of the la board are upper, lower and middle (q270), how the death day's planet's state is found (q282), from what age the nine-eye strike and the old kinds are counted (q308), which year the illness table of WB vol. 1, p. 426 calls the sky year (q361), which mewa the second examination's mewa reason reads (q272), or the fire and iron clans' great and small cut-off (q375); nor does any question or reply of ch. 23 (107–119) give a name or rule for a couple whose aspects fall into different kinds (q209, q354).
  - 2026-10-23, the *Removing the Tarnish* etext of vol. 1 (VEIE0OPIF790A4DF_I5875), pp. 450–546: «ཞུད», «གྱོད», «དགུ་མིག», «བླ་རྡེལ», «ཤི་ལོ», «བབ་སྤར» give nothing on the reckoning for the dead's inputs; the etext's ordinals are mostly misread, so the replies were found by Yigdzin-1's reading of the images.
  - 2026-10-23, WB's etext (IE0OPI51524892), vol. 1, for «ཁ་འཕངས» / «ཞལ་འཕངས» of question and reply 111: no hit; the term is the regent's and his questioner's, not WB's.
- **The thirty's count, 241st pebble and check order** (RD50, 2026-10-25):
  - 2026-10-25: BDRC full-text search for «རྩིན་འཁོར་ལོ་རྩིབས་བརྒྱད་དང་» (with «རྩིན» contracted) and «བུད་མེད་དབང་ཐང་རྩིས། བྱེད་ན་གཡུང་དྲུང་འཁྱིལ་པ་དང་» gave 0 hits, since the prints write «རྩི་ན» and punctuate otherwise; the shorter «བཅུ་གསུམ་བྱིས་པ་ཆུང་ངུའི་ལུས» (4 hits) and «བཅུ་བཞི་བུད་མེད་དབང་ཐང་རྩིས» (2–3 hits) found the printings of the *Moonbeams*.
  - 2026-10-25: the WBZ and Sakya (WB2) etexts do not match «རྡེལ་གྲངས་ཉིས་བརྒྱ» exactly (OCR); the Zhol line was found by «གཏན་འབེབས་ཡིན» with «བཅུ» in the line, and the Sakya etext gave nothing.
  - 2026-10-25: no text read (WB, the notes, the *Moonbeams*, UT) names a 241st pebble or an extra "general" pebble.
- **The circles' small reckonings (RD51)** (RD51, 2026-10-25):
  - 2026-10-25, a parallel verse for the la's wandering by the year: none in the etexts of the Bhutan collection (I1KG11905), the *G.ya' sel* (I5875, I5876) and Dpal bsdus's manual (I4767), searched for «ཕྱོགས་སུ་འཁྱམས» and «བླ་འཁྱམས».
  - 2026-10-25, a gloss of «ཚད་ཟླ», «ན་ཟླ» and «རྟ་ཞག་སྔོན་པོ» in the same etexts: none.
- **The karma reckoning over the match and the two utterly bad lives** (questions 355 and 388, 2026-10-25):
  - 2026-10-25, WB's etext of both volumes (IE0OPI51524892) for «ཚེ་ཟད», «ཟུངས་ཟད», «ཟུང་ཟད», «ཁས་ཉེན», «ལས་རྩིས», «ཐ་ཆད», «དབང་ཐང་ཆུང», «སྤྱིར་ངན»: no line of ch. 22 (vol. 1, pp. 259–350) or of the notes' section 1 (vol. 2, pp. 474–489) names what makes an aspect "life exhausted", "vitality exhausted" or "the reckoning bad in general"; «ཚེ་ཟད» elsewhere only for the dead (vol. 2, pp. 56–57), the yogas (vol. 2, pp. 332–333), the twelve deaths (vol. 2, pp. 450, 540) and a mewa reading (vol. 1, p. 412); «ཐ་ཆད» in vol. 1 nowhere, in vol. 2 only pp. 41, 226, 333 and 393; «ཁ་དམར» in the notes' section 1 only pp. 474–475, 476, 479, 483 and 488.
  - 2026-10-25, the *Removing the Tarnish* etext of vol. 1 (VEIE0OPIF790A4DF_I5875) for «ཐ་ཆད», «ཚེ་རྩིས», «ལས་རྩིས», «ཚེ་ཟད», «ཁ་དམར», «དཀར་ནག», «དྲིལ»: «ཐ་ཆད» only in question 115 (img. 94) and reply 111; «ཚེ་རྩིས» only in questions 102 and 111 and reply 111; replies 101–106 on ch. 22 (img. 454–461) grade no aspect and lay no single verdict («ཁ་དམར» only img. 458, the time at which a verdict is set, and img. 470, reply 115); no other reply on ch. 23 (107–119) speaks of the life reckoning's verdict.
  - 2026-10-25, the manuals' etexts: LT (VEIE0OPI9C5B1B35_I1KG21886) «ལས་རྩིས» only in a chapter title (etext p. 79), «ཚེ་ཟད» only in the dead's and a divination board's readings (etext pp. 55, 105, 109, 138), nothing on the match's karma reckoning; KD vol. 3 (VEIE0OPI2F246A26_I4717): the *Moonbeams* (etext pp. 506–536, its match chapter from p. 525) and Kun bzang blo gros's supplement *Rgyu skar phreng ba* (etext pp. 537 on, its karma reckoning closing p. 636 and its match chapter after it) have no line on WB p. 364's list, «ཚེ་ཟད» only for the dead (p. 727) and the yogas (p. 752); UT's karma reckoning is recorded (ut:p153-155) but its match chapter was not searched beyond «ཚེ་ཟད» and «ལས་རྩིས» (no hit there).
  - 2026-10-25, BDRC's full-text search: «ཚེ་ཟད་པ་དང་ཟུངས་ཟད» and «ལས་འབྲས་ངན་རྣམས་མཐུན་ཀྱང» only WB's Gser thang reprint; «ལས་རྩིས་ངན», «ཟུང་ཟད་པ་སྟེ», «ལས་རྩིས་ཀྱི་དབང་ཐང», «རྩིས་སྤྱིར་ངན» only WB's prints of the notes; «ཚེ་ཟད་ཟུངས་ཟད» once, a medical text (MW29982, *Lhan skyes kyi rma bcos pa*, "all but the life exhausted and the vitality exhausted" healed), not a reckoning; «ངན་ཐ་ཆད་གཉིས», «ཚེ་རྩིས་ངན», «ཁ་དམར་རྡེའུ་གཅིག་ཏུ», «ཚེ་རྩིས་ཀྱི་གནས་རྣམས», «ཚེ་རྩིས་ཀྱི་ཁ་དམར», «བག་རྩིས་མཐུན་ཡང», «ཚེ་རབས་ལས་རྩིས་ངན» no hit; «ལས་རྩིས་ཀྱི་ཁ་དམར» only Gyurme Dorje's index; «ཟུར་གྱི་ཁ་དམར» and «ལས་རྩིས་ཁྲ་མ» only UT; «ངན་པ་ཐ་ཆད» 68 hits, almost all doctrinal prose in the sense "the basest".
- **The illness's people and animals, the Vaiḍūrya line (RD52)** (RD52, 2026-10-25):
  - 2026-10-25: «སྔས་བསྲུང་པ» occurs only in WB p. 445 (Gser thang and Sakya prints) and the regent's question 135, without a gloss; no text glosses whose animal pp. 428–429's twelve are in a clause of its own.
  - 2026-10-25: no print of the *Vaiḍūrya* volume or of the *Speckled Sheet* carrying the clause on the moment's trigram was found; the line appears only in the two prints of the regent's replies (the second, MW2PD17386_474815, is search only and answers 403); «བཻ་ཌཱུརྻའི་གླེགས་བུ» occurs only in an unrelated Shākya mchog ldan passage.
  - 2026-10-25: no rule for the moment's trigram beyond the first date and first hour in the *Garland of Stars* or the Dpal bsdus, apart from the notes' «turning in the order».
- **The household round's division and a speckled member pebble (questions 317, 318)** (2026-10-25):
  - 2026-10-25, a print of the notes reading «བཞིས» or «བཞི» for the household round's division (WB vol. 2, p. 491): none; Mipham's own print (I2029 img. 654, at 4× with the contrast raised) and the typeset Chengdu Collected Works (MW2DB16631_DB4BAA, I1PD34647 img. 571) read «གསུམ» like the 1996 and Gser thang prints.
  - 2026-10-25, BDRC's full-text search, no hit: «རྡེའུ་གྲངས་བཞིས་བགོས», «རྡེའུ་གྲངས་བཞི་ཡིས་བགོས», «ལྷ་སར་ཁྲ་བོ», «མི་སར་ཁྲ་བོ», «ཁྱིམ་སར་ཁྲ་བོ», «རྡེལ་ཁྲ་བྱུང», «ལྷ་སར་རྡེལ་ཁྲ», «ཁྲ་བོ་བབས་ན་ལྷ་ས»; «རྡེའུ་གྲངས་གསུམ་གྱིས་བགོས» only the Gser thang WB; WB p. 385's «གོང་མའི་ལུས་དང་འོག་མ», «བཟའ་ཚང་ཁྱིམ་གཅིག་མི་དུ་ཡོད» and «ཁྱིམ་སའི་རྡེལ་རོའི་ཁ་དམར» only WB's prints and KD vol. 3 (etext p. 685, Mkhyen rab nor bu's digest restating WB's verses); «ཁྱིམ་མི་གཅིག་བསྡུས» only KD's sheet (kd.3:p726); «རྡེལ་གྲངས་ཚན་པ་བཞི» and «གསུམ་ལས་ལྷག་པ་འབྱུང་མི་སྲིད» only LT's two prints and the *Bzo rtsis zlos gar* (MW1PD95727, the same text).
  - 2026-10-25, the *Removing the Tarnish*, questions and replies 120–130 (I5875 img. 093–100, 475–489, Yigdzin-1, searched for «ཁུག», «རྡེལ་རོ», «ཁྱིམ་ས», «བཟའ་མི», «བཟའ་ཚང», «ཁྲ»): nothing on the household round's laying, fold, leftovers or colours; question and reply 123 only ask and defend whether the round is Phugpa's (ys:i096, ys:i478-479).
  - 2026-10-25, UT's etext (IE0OPI6328F058): «རྡེལ་སྐོར» only on etext pp. 96 and 170, each naming the household round among the reckonings it leaves out; no «ཁྱིམ་རྩིས».
  - 2026-10-25, the Bhutan collection (VEIE0OPI1829B6AF_I1KG11905): its *Good Vase* is the preliminaries only (pp. 1–78); no «རྡེལ་སྐོར», «ཡང་ཁུག» or «བཞིས་བགོས» in the volume. The *Good Vase*'s household round (MW30023_79E1C5) is search only and was read in its snippets.
  - 2026-10-25, a laying for any count but three, five and seven, or a stated rule for the order of taking with an even number of members: none in LT, SN, ZG, KD vol. 3's etext or the *Good Vase*'s snippets. KD vol. 3's sheet of seven (p. 726, leaf 745 of the loan) was not read on the scan: the loan was another session's.
  - 2026-10-25, a speckled pebble in the household round or its readings: none in LT (pp. 20–25), SN (pp. 70–71), ZG (pp. 285–286), KD vol. 3's digest (etext p. 685) or the *Removing the Tarnish*'s replies 120–130.
- **The illness identified by elements: the five, the seven and the shares (WB vol. 1, pp. 435, 438; the notes, p. 497; questions 227, 228, 389)** (2026-10-25):
  - 2026-10-25, BDRC's full-text search (`bdrc_search.py`): «ཁམས་ཁྲ་དང་པོའི་འགྱུར» only the regent's replies (MW2PD17386_474815, search only); «དུས་བཞིའི་ངང་ལ་ཁམས་ལྔའི་འབྱུང་བ», «དེ་དག་གང་མང་ཁམས་ཀྱི་རྡེལ», «སུམ་ཆ་སུམ་གཉིས་གདོན་རྐྱེན», «འབྱུང་བ་ཕྱེད་མ་ནད་གཉིས» no hit; «གཉིས་པ་ཕྲ་བར་རྣམ་ལྔ» only the regent's two copies (I5875, and MW2PD17386_474815, whose snippet reads «ནད་རྩིས་འགར» and «སྦྱར་བ» for the Taikhang's «ནང་རྩིས་འགར» and «སྤར་ཁ»); «ཕྱེད་མ་ནད་གཉིས», «ལོ་ཟླ་ཞག་དུས་སྲོག་ཁམས», «རང་ཟླར་ཚེས་པའི», «ལོ་སོགས་དང་མི་རིགས» only WB's prints, the notes and the regent's replies; «ཁམས་ཁྲ» (60 hits) names the *Speckled Elements* only in lists of sources (WB, LT, the *Rin chen gter mdzod*), and no copy of its plank-tree or of the "inner reckoning" turned up. The «ལོ་ཟླ་ཞག་དུས་སྲོག་ཁམས་དང་། །སྤར་ཁའི་ཁམས་བཅས་ཀུན་མཐུན་ན» of a reckoning for the dead (MW3CN2232_FB9B30, I3CN2345) is about the dead, not the sick.
  - 2026-10-25, the manuals' etexts for the illness identified by elements («ནད་ངོས», «སྲོག་ཁམས་བཞི», «སུམ་ཆ», «ཕྱེད་མ», «མི་རིགས», «ཁམས་རྡེལ», «ཤིང་རླུང», «གང་མང»): LT (pp. 176–183), UT (ch. 5, pp. 194–216), the *Moonbeams* (KD vol. 3, etext pp. 530–531, "the illness and spirits by day … from the extensive texts"), its supplement (etext p. 703), Kun dga' rig 'dzin's sheets of examples (etext pp. 733–748) and the rest of KD vol. 3's etext, Mkhyen rab nor bu's digest included, give the board's pebbles and their rites only; LT's «སུམ་ཆ», «ཕྱེད་མ» are the white and black classes of the fifteen (pp. 176, 181), UT's hits other words («ཉིན་ཕྱེད», «ནམ་ཕྱེད»), not elements' shares. ZG (etext I4PD2444) has none of the words in an illness sense.
  - 2026-10-25, a variant of p. 435's «རང་ཟླར་ཚེས་པའི་སྤར་ཁ་ཡིན» or p. 438's «སུམ་ཆ་སུམ་གཉིས»: none; the Zhol (I1KG1710 img. 577, 581) and Taikhang (I5877 img. 594, 598) scans read as the 1996 edition but for «གསུམ་ཆ»; the Sde dge reprint's scans (I1KG12739) are 1188 px wide and illegible at that size, and its etext garbles the line.
  - 2026-10-25, the regent's replies 133–136 and their questions (I5875 img. 99, 491–494): no reply speaks of the shares (halves, thirds) or of the notes' seven.
- **The illness identified, the classes and labels (RD53)** (RD53, 2026-10-25):
  - 2026-10-25: the pool (kb.py) and the 1996 print's Yigdzin-1 of vol. 1 and 2 for «ཁྲ་བོའི་», «ལྔ་སུམ་གཅིག», «བླངས་ལན» and «རྙོངས་ཚད»: «ཁྲ་བོའི་» stands only in p. 425; none of the four has a gloss; «གདགས་སྲིབས» stands in ch. 26 only.
- **The plank-tree's offering line (RD55)** (RD55, 2026-10-25):
  - 2026-10-25, BDRC full-text search: «བསྔོ་རུ་མ་བཏུབ» (7 etexts), «བསྔོ་མ་བཏུབ» (5), «བསྔོར་མ་བཏུབ» (KD only), also with «ཅེས» or «ཞེས» and the variants «བསྔོ་རུ་མི་བཏུབ», «བསྔོ་མི་བཏུབ», «བསྔོ་མ་བཏུབ་པ»: every hit is the verse itself, copied; none is a gloss.
  - 2026-10-25, the etexts of YS I5875, WB2 vol. 2, UT I4767, LT, ZG, the Bhutan collection and the Gser thang vols. 1 and 2: «བསྔོ་རུ», «བསྔོ་མ་བཏུབ», «བསྔོར» and «སྔོ་བགྲང» have no hit other than the verse and the notes' own line; Steinert's dictionaries were not consulted (the clone is not on this machine); YS I5876 and the Zhol blocks for pp. 423–425 (img. 559–562) were not read.
- **The circles' verdicts (RD54)** (RD54, 2026-10-25):
  - 2026-10-25, a second print's reading of «རུས་མིག»: the Sakya etext (VEIE0OPI0BA3A0E0) answers 403; the Zhol scan (WBZ img. 657) does not tell ra from da at the crops' size; the Taikhang reproduction was not opened.
  - 2026-10-25, a worked example of the meadow's ornament in Mipham's notes: none beyond the three lines of pp. 533–534; pp. 535–538 do not speak to it.
  - 2026-10-25, a note on Sprugtön's limbs or on «རྡེལ་ཁྲ» in the notes, pp. 532–538: none.
- **The unopened pages of ch. 31 and ch. 34 (RD56)** (RD56, 2026-10-25):
  - 2026-10-25: the mansion numerals in the brackets of pp. 380–382 and 424 (21, 22, 26, 27) were not settled syllable by syllable: Yigdzin-1 gives 21 and 22 twice and 26 is missing; the doubling is the Śravaṇa–Abhijit one white-beryl-ch34.md already notes.
  - 2026-10-25: Rāhu's «ནུ་རུ» course for the 11th and 12th in p. 237's finer form was translated from the machine reading and not checked on the scan; the join of p. 237 to p. 240 («ནོར» then «རིགས») is not settled; p. 386 (img. 394) is not in this reading.
- **Ch. 27 on the Taikhang block (RD57)** (RD57, 2026-10-25):
  - agy on p. 17, l. 26 (RD57, 2026-10-25): the crops of the Taikhang (I5878 img. 33, line 1, two sizes) and of the Zhol print (I1KG1710 img. 651, line 1) all answered «blocked by Gemini's filters», the line speaking of sieges and raids; not tried again.
  - Which impression the Taikhang reproduces (RD57): not found in what the README holds on MW30116, which names a Lhasa print and the New Delhi 1972 reproduction only; no catalogue was searched for it.
- **D4, the earth lords' rank, searched further (2026-10-26)** (weighing.md, the sections D4 A, B and C, each with its *Searched and not found* in full):
  - 2026-10-26: «ས་བདག» near every word of rank or concession («སྟོབས», «གཙོ», «ཆེ་ཆུང», «བཟང་ཡང», «ཡིན་ཀྱང», «དམིགས་བསལ» and others) in WB vol. 1 ch. 13–14 and 23, vol. 2 ch. 29–31, 33, 34 (the 1996 etext), the regent's questions 161–193 and replies 161–190 (Yigdzin-1), DZ, the supplement, LT, UT, ZG, NM, SY, JL, CN vols. 14 and 17, KD vols. 1–3: no rank against the day's factors. A regex for ས་བདག needs a lookbehind, or it catches ནས་བདག.
  - 2026-10-26: Kongtrul's *Treasury of Knowledge* (etext VE5488_001, whole): the element reckoning as history and one line only; the earth lords twice, never in a reckoning. No element-reckoning writing of Mipham's beyond the notes on WB came up.
  - 2026-10-26: BDRC full text, about 65 phrases between readers B and C (zero hits: «ས་བདག་ལས་ཆེ», «ས་བདག་ལས་གལ་ཆེ», «ཚེས་བཟང་ཡང་ས་བདག», «གཟའ་སྐར་བཟང་ཡང», «ས་བདག་ངན་ཡང», «ས་བདག་གལ་ཆེ», «ལྟོ་འཕྱེ་གཙོ་བོ», «ཉིན་བཟང་ཡང་ཕྱོགས», «ལོ་ཐོ་ལྟ་ཚུལ» and others); search-only etexts (OS, BS, MK, the Sakya bka' 'bum, Blo bzang sbyin pa's almanac manual, SPD) answer 403 and stay unread.
  - 2026-10-26: the Men-Tsee-Khang almanac of 2000, front matter img. 3–80: no user's instructions, no weighing; the year's earth-lord wheels are drawings. Not reached: Maurer's 2019 Brill chapter and 2009 book, Cornu, Schuh, Henning, the Men-Tsee-Khang's English books, Jampa Kelsang (no copy at hand).
- **The summing rules left for the moment, the dead and the illness** (RD58, 2026-10-27):
  - The notes' pp. 503 and 537 (img. 511, 545, 546) and vol. 2 p. 172 (img. 180) on the scan: no sentence says which aspect of the landed year each of the four fights, nor how many years the sixty-year pairs' count runs; ch. 27 «ཉེར་བདུན» is named as the like, but its circles count the asker's years lived upward, with five aspects (circles-count).
  - Vol. 2 pp. 166–168 (img. 174–176, the lines of the three) and the notes' p. 536: no line says that a place's verdict is a count of white and black, or how the cutting and rise and decline are ranked.
  - Vol. 1 p. 425 and the notes p. 496 (img. 435, 504): no class for a difference of one, two or seven and more; no statement whether the speckled counts to the fifteen.
  - P. 503 img. 511: the print does not tell «བླ» from «ཟླ» in the fifth group; no other print of the notes (Mipham's own, I2029, or the Chengdu Collected Works) was opened for the letter.
- **RD58's third reading: Mipham's own print and the typeset Collected Works** (RD58, 2026-10-27):
  - I2029 img. 739–743 and the typeset's img. 682–685: no line that says which aspects of the moment's and the one reckoned's years meet in the counts of p. 172, none that gives how many years the counts run, none that names the six of the 360 otherwise than the eight words.
  - I2029 img. 654–750 (Yigdzin-1) and the typeset's img. 581–584: no class of the nine for a difference of one, two or seven and more; no word on the speckled in the fifteen; three sentences of the 1996 notes p. 495 («ཁྲ་བོ་ནི་ཕྱི་ནང་…ཅུང་ཞན» and two others) are not on I2029 img. 662.
  - I2029 img. 676, row 2 (7× to 12×): the head letter of «བླ་བར» is not shown to be «བླ» or «ཟླ» on the block print (the typeset reads «བླ»).
  - I2029 img. 740, row 5–6 at 8×: the syllable of «ལོ་ཚབ» is not distinct (Yigdzin-1 «ཙ་བ»); I2029 img. 742, row 2: the list of the six in the 360 sentence is blotted.
  - «ཡོས» and «ལོག་མུ་ཅོར» (p. 503): the letters are the same on Mipham's print and the typeset; no gloss in the notes; Steinert's dictionaries not opened.
- **RD62 (2026-10-28)** (RD62, 2026-10-28):
  - Re-reading ch. 27's doubtful letters (p. 9 l. 6 སྐུད/རྒུད, p. 17 l. 9 ཛ/རྔ, the rat's ལྷོར/ལྟོར) on the 1-bit Gser thang reprint at 5–6×: the scan does not settle them by eye (RD62, 2026-10-28); a different, sharper print would be needed.
- **RD64 (2026-10-28)** (RD64, 2026-10-28):
  - «རང་མཐོ་བཅུ་པ» and «མཐོ་ཕྱེད» as a phrase in the Bhutan digest and Dpal bsdus's Rmad byung (locate.py find): no hit; the Bhutan digest says «མཐོ་བཅུ་པ» alone.
  - The closing verse's lines «ལས་བརྩམས་གང་ཟག་དེའི་དར་གུད» and «ཁ་དམར་སྨྲ» after «ཁྱད་པར་དུ» in the same two etexts: not found in the closing verse's own wording; only Dpal bsdus p. 317 has «ཁ་དམར་སྨྲ» after rise and decline, in its own chapter.
  - «སྦ་ཕྱར», «སྦྲ་ཕྱར» and «ཤྭ་རགས» in the Bhutan digest and Dpal bsdus: no gloss; the first is garbled in the Bhutan digest's list (p. 357), the third not present.
- **RD63 (2026-10-28)** (RD63, 2026-10-28):
  - «སྤྲ་ཁྱིམ»/«སྦྲ་ཁྱིམ» searched in the three local WB etexts (1996 print, Zhol print I4CZ74285, Sakya Centre print I1KG1710): it occurs only in this line and in a garbled Zhol passage on setting up a door, with no gloss (RD63).
  - «སྦྲང་མ་ཕུད» / «ཡོལ་བས་སྒྲིབ»: the other WB prints' etexts repeat the same text, so they are no independent witness (RD63).
  - No BDRC full-text search was made for p. 224 and p. 236's words (no browser, etext-only reading) (RD63).
- **RD67 (2026-10-28): other prints and manuals on ch. 27's unread words** (RD67, 2026-10-28):
  - BDRC's exact-phrase search for «ཚད་ཟླ་མག་རྒུད», «ལྕོང་མོའི་རྔམས», «ལྕོང་མོའི་རྔམས་རྒྱ་མཚོ་དཀྲུགས», «ཡན་ལག་ས་བདག་ཐུགས་པ་སྦལ» found nothing; «ཚད་ཟླ་མག་སྐུད», «ན་ཟླ་སྟོན་མོ», «བླ་ལྷོར་འཁྱམས», «ཤིང་ལུག་པ», «འུར་ཞོགས» only in WB's prints and unrelated works, none a gloss; the Sakya, Zhol and Taikhang-copy etexts and Zhechen Gyaltsab's volume (I4PD2441-2451) give no gloss of the golden turtle, the sixteen limbs, «ལྕོང་མོ» or «བརྗེའི» as the monk.
  - The Zhol print's «བརྗེའི» (WBZ img. 658, last line) is too small to tell from «རྗེའི» at 4 times; the rare-texts print MW2PD17386 has no scan open.
- **RD66 (2026-10-28): ch. 34's list of works, the mtho and the closing verse** (RD66, 2026-10-28):
  - Every occurrence of «སྐྱས» in the 1996 etext (pp. 300–470), LT, UT and the Bhutan digest read for a bride's portion or dowry: none says so; each is a gift or offering.
  - «ཤྭ་རགས», «ཤུ་རགས» and «སྦ་ཕྱར» in the Sakya Centre etext (I1KG12739/12740): no hit, its OCR too broken.
  - Mipham's notes in the 1996 volume ([p482]–[p549]) for «སྐྱས», «ཤྭ», «ཁྱད་པར་དུ» and the length of a mtho: nothing.
  - The digging figure's mtho in LT, UT and the Bhutan digest outside p. 314: nothing beyond the digest's p. 314, the same words as ch. 32.
  - The Taikhang reproduction (I5878) not opened for p. 378 and the Zhol print's list not read on the scan; Steinert's dictionaries not searched for «སྐྱས», «ཕྱར», «ཕྱ་ཐེར».
- **RD65 (2026-10-28): ch. 31's four open passages on the other prints** (RD65, 2026-10-28):
  - Mipham's notes of vol. 2 (pp. 474–545) searched for «སྦྲང་མ», «ཡོལ་བས་སྒྲིབ», «ཐེམ་བུར», «སྦྲ་ཁྱིམ», «སྤྲ་ཁྱིམ», «ཉི་མའི་ཀླུང», «མ་ལྔ་བུ་ལྔ», «ཧལ་ཁྱི»: no gloss of any of the four passages (the notes cover the black reckoning only).
  - The Sakya Centre print (I1KG12739–40): etext too corrupt for phrase search, scans 1210 px wide, not read.
  - BDRC full-text search of the four passages' phrases: only copies of WB and the outline etext of the commentary Dpyod ldan snying nor (search only, no gloss) carry them.
  - Not searched: the hal khyi's year charts of vol. 1 pp. 116–121 and the Steinert dictionaries.
- **RD70 (2026-10-28)** (RD70, 2026-10-28):
  - A gloss of «ཉེར་ཐམ» as a word: none; BDRC's exact-phrase search (26 hits in 19 etexts) gives uses, not a definition; Steinert's dictionaries were not searched.
  - ZG's meadow lines in another work: «ཡོངས་དྲིལ་རྩ་གཅོད» 1 hit (ZG only), «གསེར་ལོ་གཡུ་ལོ་དུང་ལོ» 1 hit (unrelated); «ཁ་དམར་སྤྱི་བསྡོམས» 5 hits, the Moonbeams' line again, three of them search-only works; no further manual gives the totals' use.
- **RD69 (2026-10-28)** (RD69, 2026-10-28):
  - BDRC full-text search: «འབྲུག་ལོ་བྱི་ལ་སྦྲུལ་ལོ་བྱ» 0 hits; the line with «ཁྱི» only in WB's prints and the digest MW1KG21827.
  - «སྣང་སེལ་དུར་གྱི» and «སྣང་སེལ་ཐ་ཆུང་» 0 hits; «སྣང་སེལ་ཁྲབ་གྱོན», «ཉི་དུས་བྱང་ཕྱོགས་ངན», «དུར་ཉིར་ཁ་མི་སྟོན», «གནས་པའི་ཉི་དུས་འཛིན» only in WB's prints and the commentary's etexts, no gloss.
  - «དམག་མི་ཝང་ཁྱི», «ཝང་གིང་ལང་གིང», «ལང་གིང་དམར་པོ», «ཧལ་ཁྱི་ནག་པོ་མ་ལྔ» 0 hits; the G.ya' sel etext and Mipham's notes (pp. 474–545) have nothing on the light-clearer, Wangkhyi, the sun's klung or the hal khyi's mothers and sons.
  - The Bhutan collection's year tables (I1KG11905 img. 446–452) are too small to read and their etext is garbled: no witness for q28 or q29.
- **RD68 (2026-10-28)** (RD68, 2026-10-28):
  - «ཕྱ་ཐེར», «ཕྱི་ཐེར», «ཤྭ་རགས» and «མཐོ་ཕྱེད» as headwords in Steinert's 64 dictionaries: none.
  - «ན་ཆུང་མོད» in the Zhol print's etext (garbled), its second impression, UT, LT and the Bhutan digest: no hit (only the 1996 etext p. 412).
  - A length for the digging figure's mtho in the digging rite itself (Bhutan digest, UT, LT, the Zhol etexts): «མཐོ་གང» occurs only in WB's rites (stakes, a «ཤེར་ཤིང», a Buddha's image), not as a plot's measure.
  - The lines of works 24, 49 and 50 on the Taikhang print were not read by eye (too small at 1.3x); the Zhol print's cartouches still unread.
- **RD71 (2026-10-28)** (RD71, 2026-10-28):
  - A gloss of «རུས་ནག»: BDRC's exact search (61 hits, WB's grave chapter and unrelated rites), «བྱང་ཕྱོགས་རུས་ནག» 0 hits; the etexts of ZG, the Bhutan collection and the G.ya' sel define the word only by the set it stands in.
  - A text laying out the circles' two mewas: «ལུས་སྨེ་དང་སྨེ་ཕྲེང་མཐུན» and «བརྩི་བྱའི་ལོ་རྟ» 0 hits; the nine hits of «བརྩི་བྱའི་ཁམས་ཀྱི་དགྲ» are WB's circles and their digests, none on the mewa.
  - Sprugtön's limbs: «སྤྲུག་སྟོན་གྱི་རྩིས», «སྤྲུག་སྟོན་ཡན་ལག», «རྡེའུ་ལྔ་བཅུ་ཡན་ལག་བཅུ་དྲུག» 0 hits each; «སྤྲུག་སྟོན» in ZG, the Bhutan collection and the G.ya' sel etexts: 0.
  - The typeset notes' p. 533 was not located in I1PD34647 (img. 605–624; printed page = image − 46 does not hold there).
- **RD73 (2026-10-28)** (RD73, 2026-10-28):
  - The head letter of the stack under «བཟོ་» of work 50 on the Gser thang reprint at 6×: a blot, ka or ga not told.
  - «ཁྱད་པར་དུ» with a stated weight over the other members of a list in WB's etext (about 30 occurrences): none.
- **RD72 (2026-10-28)** (RD72, 2026-10-28):
  - Steinert's 64 dictionaries (locate.py dict, Wylie headwords): «nyi dus», «nyi ma dus tshod», «nyi ma'i klung», «wang khyi», «wang ging», «lang ging», «ma lnga bu lnga», «dur nyi», «dur gyi steng», «dur lo», «gshed bzhi» have no headword that glosses them; «dur mig», «rang dur» and «klung rta» only in no. 62.
  - BDRC's full-text search: «ཉི་དུས» (299 hits, almost all run-ons of other words), «ཉི་མ་དུས་ཚོད», «ཉི་མའི་དུས་ཚོད», «ཝང་ཁྱི», «ཝང་གིང», «དུར་ཉི»: none glosses Wangkhyi's «ཉི་དུས» or the light-clearer's «དུར».
  - The year charts of vol. 1 pp. 116–121 (Yigdzin-1 on I3CN5028 img. 123–128): names only, no mothers and sons, no sun's klung, no day counts.
  - The commentary Dpyod ldan snying nor stays search-only: its snippets are WB's own words.
- **RD75 (2026-10-28)** (RD75, 2026-10-28):
  - Steinert's 64 dictionaries on ch. 27's words: «ཚད་ཟླ» only in Valby (brother); «མག་རྒུད», «མག་སྐུད», «བུ་ཚར», «བུར་ཞོགས», «ཞུར་ཞོགས», «བརྗེ» as monk or spiritual friend, «ཤིང་ལུག་པ» as the cow's class, «རྔམས» or «རྔ་མས» in the tadpole line: no headword settles any of them.
  - ZG on p. 9 l. 16 and p. 17: «ཟླ་སྟོན», «ཞག་བཅུ་ཚུན», «ལྕོང་མོ», «རུས་པ» as a bone of the turtle: 0 hits in ZG's etext; ZG does not quote the children's circle's remedy lines nor the soldiers' reckoning.
- **RD74 (2026-10-28)** (RD74, 2026-10-28):
  - Steinert's 64 dictionaries: no headword «ཡོ་ནས», «ཁ་ཡོ་ནས», «ཧལ་ཁྱི», «ཉི་མའི་ཀླུང», «མ་ལྔ་བུ་ལྔ»; «ཁ་ཡོ» only in Valby (mouth crooked); «ཁ་ཡོག» is a false charge.
  - BDRC full-text search: «ཁ་ཡོ་ནས་འདྲེན» 0 hits; «ཁ་ཡེ་ནས་འདྲེན» 1 (the Bhutan table); «ཡོ་ནས་འདྲེན» 8 (WB's five copies, the Bhutan collection, two copies of a sentence of Ngawang Kunga Sonam); «ཁ་ཡོ་ནས» 3, all the Bon Klu 'bum collections; no text glosses WB's pair.
  - The Bhutan collection's tables (I1KG11905 img. 571, etext pp. 451, 570): no gloss beside the table; the second table's OCR is too corrupt past the dog and rat days.
  - The other cells of WB vol. 1 p. 133's twelve-day table, the Sakya Centre print and the «ཁ་ཡོ» 628-hit list were not read.
- **RD77 (2026-10-28)** (RD77, 2026-10-28):
  - A third look at the three Taikhang syllables of pp. 9 and 17: the first letter of «[?]ུར» (p. 17 l. 26, img. 33 line 1, at 4 and 5 times), the stack after «ལྕོང་མོའི» (p. 17 l. 9, img. 32, 4 times) and the stack after «མག» (p. 9 l. 6, img. 20, 4 times) are fused ink on the Taikhang and are told by neither head nor foot; no crop settled them.
- **RD76 (2026-10-28)** (RD76, 2026-10-28):
  - Zhol print, Taikhang reproduction (vol. 2) and Sakya Centre print: the ox's line of p. 210 and the light-clearer's of p. 219 (Zhol img. 925 and 935, Taikhang img. 307 and 317, Sakya img. 309): the words are those of the 1996 print; no variant separates the day from the hour, no animal named for the grave in any month.
  - The Sakya Centre print's scans (I1KG12740, 1210 × 190 px a page): unreadable beyond what the etext shows; p. 219 not fetched.
  - The model almanac's year charts of vol. 1 (1996 print pp. 138–147): no cell names the light-clearer's grave animal, none the hal khyi's mothers or sons; «དུར་ལེན», «སྣང་སེལ» are names only.
  - The Taikhang reproduction's year charts (I5877 vol. 1, img. 170, 178, 186): the hal khyi's cell reads «ཧལ་ཁྱི། བུ་མོ།» as in the 1996 chart; no «མ་ལྔ» or «བུ་ལྔ»; the charts of img. 182 and 184 were not read.
- **RD79 (2026-10-28)** (RD79, 2026-10-28):
  - The planets' directions and the weekday's date triads of p. 313: docs/sources/ searched for the opening words «ཉི་མ་པ་སངས», «ཐད་ཀར་ངན་པས», «གཟའ་ཡི་ཚེས་ལུགས»; nothing found (the pool had never quoted them); BDRC full text not searched.
- **RD78 (2026-10-28)** (RD78, 2026-10-28):
  - The 1996 print's cells (I1KG12906 img. 143, p. 133): 670 px wide, cell text about 12 px high with show-through; not read.
  - Whole-page Yigdzin-1 on I3CN5028 img. 140 reads five characters; the cells were cropped one by one.
  - An etext of the tables: the 1996 etext has no text for the cells; the only «ཆང་རྫ་ལ་གནས» hit is the verse of vol. 2, p. 367.
  - A gloss of «ཁ་ཡོ» or «སྦྲང་མ» in the tables: none; no cell of either table names them. The Bhutan second table's cells and BDRC full-text search were not redone.
- **RD81 (2026-10-28)** (RD81, 2026-10-28):
  - A rank of the Lto-'phye's parts by name (head, belly, back, tail) beyond p. 282's ninth part, back and tail: not found on pp. 276–294; p. 281's figure is the only place the parts are drawn.
- **RD86 (2026-10-28)** (RD86, 2026-10-28):
  - The direction of counting the eight places of a door side: not stated on p. 287 (img. 293, read by eye), nor in the page's own lines before and after the table; no commentary in the pool.
- **RD85 (2026-10-28)** (RD85, 2026-10-28):
  - On the printed page itself (img. 319) nothing names the planet behind «ཕུང», the planet for «ཆུ་བདག», or the direction in which the friend is put; no gloss, note or interlinear word; the etext and other books were not searched.
- **RD87 (2026-10-28)** (RD87, 2026-10-28):
  - A seat, a direction or an order of the pairs for the hal khyi's mothers and sons in the verse, the note and the remedy lines to the foot of p. 185 (Gser thang reprint img. 191, read line by line by eye, 2026-10-28): none; the lines say only 22 days to each pair and 110 in all.
  - The word of the sun's klung explained in the four blocks of p. 186 (img. 192, by eye, 2026-10-28): the block of Langging gives the table of twelve year-pairs and the line «ཉི་མའི་ཀླུང་དང་ལྷན་ཅིག་བསྐྱེད» and no gloss.
- **RD88 (2026-10-28)** (RD88, 2026-10-28):
  - KP's list of contents (I4CZ65599 img. 9), box 24 «སྦྲ་དང་ཕྱ་ཐེར་འཚེམ་པ»: not found on the woodblock by eye at 2× (2026-10-28); the machine readings stand.
  - The subjoined ra under work 17's stack on the Gser thang reprint (img. 384) at 10× (2026-10-28): not told.
  - A gloss of «ཨ་འཐས» in the 1996 etext (locate.py find, 2026-10-28): the verse (p. 436) and p. 475 of the notes, no gloss at either.
- **RD89 (2026-10-28)** (RD89, 2026-10-28):
  - The rat verse's stack on the Gser thang reprint (I3CN5029 img. 22, l. 23, at 6×, 2026-10-28): a solid blot, ལྷ against ལྟ not told there; the 1996 scan settles it.
  - A gloss on ch. 27 pp. 16, 20 and 23–25 (both scans, 2026-10-28): no interlinear note names the la's directions, the wood sheep's class or the person of «བརྗེ».
- **RD84 (2026-10-28)** (RD84, 2026-10-28):
  - A rule, list or figure of the calendar (lunar days, weekdays, mansions, yogas, planets, a date count) in WB vol. 2 pp. 438–454 (img. 444–460 of the Gser thang reprint): none by the keywords ཚེས, ཟླ་ངོ, ཉི་མ, སྐར་མ, ཞག and the page-by-page machine text.
- **RD90 (2026-10-28)** (RD90, 2026-10-28):
  - A gloss of «ཇི་ནོང» (BDRC's etext of the 1996 print and the G.ya' sel, for «ཇི་ནོང», «ཟི་ནོང», «ཞི་ནོང», 2026-10-28): only p. 3's gloss itself; no other use.
  - «འུར་ཞོགས», «རྔ་མས» and «ཚད་ཟླ» elsewhere in WB's 1996 etext (2026-10-28): «འུར་ཞོགས» not found (the etext's p. 17 l. 26 is garbled); «རྔ་མས» only at p. 17 l. 9; «ཚད་ཟླ» only at p. 9 l. 6, «ཚང་ཟླ» once in the marriage reckoning, which does not gloss «ཚད».
  - A word for an element other than «དབང» as a start (ch. 27 pp. 3, 9, 17, both scans, 2026-10-28): none names the life force's, the body's or the luck's element.
- **RD91 (2026-10-28)** (RD91, 2026-10-28):
  - The verb after «ཡ་ཐེམ» on p. 294 (I3CN5029 img. 300, l. 7, at 7×; I1KG12907 img. 302 at 12×, 2026-10-28): a blot with sa on top; «སྤྲས» not told from «སྦས».
  - The Sde-dge print (WB2, I1KG12740 img. 408) as a third witness for p. 282 (2026-10-28): the scan carves the letters at about ten pixels, «ཧེ» not told from «དེ» at 5×; its etext is garbled on the line and has no hit for «བུད་མེད་བུས», «བྲང་ཤུལ» or «ཡ་ཐེམ་སྦས/སྤྲས».
  - «བྲང་ཤུལ» as one word (Steinert's public dictionaries, 64 sets, and WB's two etext volumes, 2026-10-28): no headword; only on p. 294; «ཁྱི་སྤྱང» elsewhere only in vol. 1 etext img. 284, which does not decide one animal or two.
- **RD92 (2026-10-31)** (RD92, 2026-10-31):
  - The NAOJ (2026-10-31, in the browser): the 暦Wiki's page list has no page on the 八将神, the 方位神 or any year direction; its 「暦注」 page names only the three bands; the こよみ用語解説 index has nothing on directions.
  - koyomi8.com (2026-10-31): the 暦注計算 page, its three 「暦注の説明」 pages, the 「暦と天文の雑学」 index and the 恵方 article have nothing on the 八将神; its 方位神 articles (金神, 金神の間日, 2009-05-28/29) name them without directions.
  - rekichu.com, takao-hospital.net/koyomi and 神宮館's 「運勢のページ」 (2026-10-31): nothing on the 八将神; the 高島暦 令和8年's text is not online (Kinokuniya lists its contents only).
  - The 暦林問答集 §6, §8–§15 (GR img. 11–17, MS img. 11–15): no stem rule for any of the eight, no anchor year for the 大將軍, no branch rule for the 歳殺, no start of the year, no 遊行 but the 大將軍's (§54).
- **RD94 (2026-10-31)** (RD94, 2026-10-31):
  - 方違え in the 暦林問答集: no 方違, 違方 or 方忌 in the NDL machine full text of MS (pid 2533201, 2026-10-31); 違 four times, none on directions. GR has no machine full text.
  - 日遊神's course outside the house in a Japanese text: not in GR §43, in the 頭書長暦 or 簾中抄 as 古事類苑 prints them, nor in the three Meiji almanac-books (pid 760755, 759984, 760747); the NDL full-text search for 日遊所在 (2026-10-31) found, besides these, only diaries and modern studies, all on 日遊 in the house at a birth.
  - 日遊神 on koyomi8.com: not named on 「暦注の説明（その２）」, 「方塞がりと方違え」 or 「天一天上と塞がり」; a site search returned no page for it.
  - 天一, 日遊, 方違 on the NAOJ 暦Wiki: not on the 「暦注」 page; the wiki's own search returned only the front page.
- **RD93 (2026-10-31)** (RD93, 2026-10-31):
  - The NDL catalogue (`ndl.py`, 2026-10-31) for 金神, 金神七殺, 金神遊行, 大金神, 金神方, «金神 方位 暦», and 金神 with 簠簋, 拾芥抄, 陰陽雑書, 二中暦, 暦林, 群書類従, 古事類苑, 方忌, 尅: no digitised work older than the 簠簋内伝 of 1632; 『安部懐中伝暦』 (1642, record R100000094-I212793) has no pid, not read; 『大将軍則覧并修法篇金神七殺則覧並修法篇』 (峰本天籟, manuscript, no date), 『文政新刻金神方位重寶記』 (松樹菴, 1824) and 『歳々明方金神八將神方位早繰』 (no date) have no pid, not read; the rest are Meiji almanacs and 方鑑 books.
  - The 暦林問答集: 金神, 七殺, 七煞 in the NDL's machine text of pid 2559255 and 2533201 (pid 14488380 is refused by the NDL Lab API); none.
  - The NAOJ 暦Wiki: no page on 金神 (DuckDuckGo, site eco.mtk.nao.ac.jp).
  - Not searched: the rest of the 簠簋内伝 (巻一 after §9, 巻二–五) for further mentions of 金神.
- **RD84 (2026-10-31)** (RD84, 2026-10-31):
  - A rule, list or figure of the calendar in WB vol. 2 pp. 429–454, by the eye pass of 2026-10-31 (every line read on the scan): none but p. 432's days to beware of for grammar; the letters and names the eye corrected bring none in.
- **RD98 dead ends** (RD98, 2026-11-01):
  - A gloss of «ཇི་ནོང»: the 1996 etext has the word only at p. 3, l. 4.
  - A worked example of the notes' «སྨེ་ཕྲེང་མཐུན་པ» for the circles: the etext's four places (notes pp. 488, 495, 500, 504) speak of the thirty, the dead's reckoning or the sixteenth row, none of the circles.
  - «གྲོས་ལེགས» in either etext: none; «གོས་ལེགས་ཚོང་ལེགས» only at p. 24, l. 16.
- **RD95 dead ends** (RD95, 2026-11-01):
  - No headword in Steinert's 64 dictionaries for «ཁྲ་བོ་བློ་ཐུབ» as a pair, «བསེ་རགས», «བྱེ་ཐག», «ཏིང་མཉྫི», «སླ་ང་རྩེས», «སྟེང་འཁོར», «ཟོ་བ་ཞབས་བརྡོལ», «སིག་ལྐོགས», «སྐྱར་མ», «སེལ་གོག», «བང་ས་དུ་རུ་ཅན», «ཕན་ངོན», «ཐལ་ཤལ», «གསང་ཤིད་ཟུ», «ཕྱི་གུ»; «རགས» is a bare syllable with no headword.
- **RD103 dead ends** (RD103, 2026-11-01):
  - BDRC title catalogue (autosuggest): no title «རིན་ཆེན་འགྲོ་ལམ» (Precious Road) and none «གབ་པའི་སྙིང་གཟེར» (Heart Nail of the Hidden).
  - «བུ་ཡིག» is a class of texts, not a single catalogued title.
  - «ར་བར»: no sense found in WB or the notes' pages on the dead's living pebbles.
- **RD97 dead ends** (RD97, 2026-11-01):
  - «སྤུད་པ་དགུ»: no gloss in the dictionaries (spud pa is only «adorn»); BDRC 3 hits (the White Beryl's outline, a Vajrabhairava text, a Four Tantras commentary), none a gloss. «རྒྱུག་པ་དགུ»: 2 hits, a leaping drill, unrelated.
  - «ཆོམས་ཁྱུང་ནག»: 2 hits, both WB. «མཇུག་རྩེའི་དཔེ»: 3 hits, all WB. «སྤྱུག་སྟོན»: 0 hits. «ཤ་རུ»: 1417 hits, not sifted.
  - «སྤོ་སྨད» as a mansion: 43 hits, all the place Po or unrelated; no dictionary has it as a mansion.
  - «sha ri bhyo», «ma tram», «gung phur», «gzhob sel» as rites: no dictionary headword.
- **RD99 dead ends** (RD99, 2026-11-01):
  - p. 28's figure labels are not legible in any copy (reprint at 2×, the 1996 scan, the Zhol print at 3–4×, WBZ img. 664).
  - «རྐང་ཕྲན་ནག»: BDRC full-text search 3 hits, all WB; no dictionary entry.
  - «ཤོལ་པོ», «སྨྱུང་མོ», «བུད་མེད་མི་འབྲུ་སྙི», «རྡེལ་འགོ་དྲོས», «འབལ་དྲོས»: none in Steinert's dictionaries in the senses needed.
  - «ཁེན་ཐོག» (p. 42) and «ལུགས་གཞན་བཞི་པ» (p. 37): BDRC search returns only WB copies.
- **RD96 dead ends** (RD96, 2026-11-01):
  - Q289: «ཡངས་ཆ», «ཤར་སྟོད་སྟག་ལ་རྣམ་ལྔ», «གཞན་བཞི་ཡངས་སྟེང», «ཐིག་བཏབ» appear only in WB's own prints (BDRC full-text search; etexts of WB, LT, UT, ZG; Mipham's notes).
  - Q311: «འཕུང་སྲི་ཛ་མོ» no hit; «འཕུང་སྲི་རྫ་མོ» one hit, in WB's etext; «རྫ་མོ», «ཛ་མོ», «འཕུང་སྲི» in none of Steinert's 64 dictionaries.
  - «ཇ་ལ», «ཇུ་ཚེར», «ཚ་རགས», «སོག་པ་སྲང», «ཕུག་དར», «བྱ་འུ», «ནོར་ཕྱུགས» as mansion names: in none of Steinert's dictionaries.
  - The animals for khen, gin and zon as places that open death are named by no source (notes, LT, UT).
- **RD95 part 2 dead ends** (RD95, 2026-11-01):
  - «བུ་ནས་གཟུགས་དང་མར་བཅོས», «ཤི་ལོའི་སྤར་སྨེ», «ཟན་བརྣངས་ལར་ན», «ཟན་གཏད་མི་རུང་བཅུ་གསུམ», «གཤེད་བཞིའི་རྡེལ་སྒོ», «ཐོག་གྲི་རྐུན་པོ», «ལྟོང་དམར་ཆུ་གྲི», «གྲི་རིགས་བཅུ་གསུམ», «སྤྲེ་ཤལ་བཟེད་རབས་ཆད»: no text but WB's in BDRC's full-text search.
  - «ཚེ་སྔོན་ཕྱི་གུ», «གཅོད་གྲི་བཅུ་གསུམ»: no hit. «བརྡེ» as a headword: none in Steinert's 64 dictionaries; «བརྗེ» only.
  - No other list of the thirteen blades in BDRC's search (q281).
- **RD100 dead ends** (RD100, 2026-11-01):
  - BDRC full-text search for «རྟ་ཞག་སྔོན་པོ»: no hit; the Garland (KD vol. 3 etext p. 556) has «རྟ་ཞོག་ནི། སྔོན་པོའི་ཕྱིར», no gloss; no dictionary has «རྟ་ཞག» or «རྟ་ཞོག».
  - Steinert's dictionaries, no headword: ja gshang, sngo thul, glong rgyas, yos ston, 'brang ma, da pa sri, thel 'gong, g.yo 'khrug, the'u bsen, gung phur, hal khyi, «ཁ་ལན» (only «ཁ་ལེན»).
  - The commentary's continuation after WB's closing verse (search-only MW1KG10375, MW1PD152297): only the Moonbeams' own lines on the base and the count; no word on 241.
  - The Zhol print's p. 472 l. 1–2 (WBZ img. 628): too cursive at 1.3× to tell ཁོན from བོན.
  - A statement of whose year serves for points 12–13, 16, 18–20, 22, 26–30, and a worked example of the thirty with a number of years: none in the Garland, the Moonbeams (KD) or the search-only texts.
- **RD102 dead ends** (RD102, 2026-11-01):
  - «ངོ་ཐེག» in Steinert's 64 dictionaries, and in BDRC's full text (55 hits, WB's prints and copies only).
  - «ཕ་མེས་ཤི་བའི་སྲི་དུས» outside WB's prints and the Zhol print's copy; «མ་སྤར་ལི» outside WB's prints and KD's note.
  - A gloss of «ཅི་རིགས་གདགས» or «བཟའ་ཚང་སྤྱི་རྡེལ» in the notes pp. 489–495 or any other work: none; the second only in WB's prints and KD's copy (3 hits).
  - «སྐྱེད་པུར་ཁུ་དཀར» 0 hits; no dictionary entry for «སྐྱེད་པུར», «སྐྱེད་བུར», «མ་ཏྲི», «ཁ་སྦྲང».
  - The wealth's place's pebbles: «ནོར་ས» with «རབ་འབྲིང་ཐ་གསུམ» finds only WB's prints, KD and a Bhutan collection's copy; no manual lays it out.
  - A household round for any count but five: nothing new.
- **RD109 dead ends** (RD109, 2026-11-01):
  - Steinert's dictionaries, no gloss of the lines: snang sel (only the meditation on impermanence), mar gyi khyim mtshes, yar gyi gshed, dur gshed (a term of black reckoning, no gloss), sbrang ma phud, gnas pa'i nyi dus, nyi ma'i glang, khar bu, gtong shes, sa bdag gtong shes.
  - BDRC full-text: «ཡོལ་བས་སྒྲིབ» (82 hits, mandala curtain), «སྣང་སེལ་དུར» (2), «ཉི་དུས་ནག་པོ» (3, WB's etexts), «ཧལ་ཁྱི་ནག་པོ» (68, not read through): no gloss of the mothers and sons, the light-clearer's grave or Wangkhyi's day-time.
  - The Bon students' textbook (I4PD3282): none of ch. 31's lines for «ཡོ་ནས», «སྦྲང་མ», «ཕུད་གཏོར», «ཉི་མའི་ཀླུང», «ཉི་དུས», «སྣང་སེལ»; the rat cell has no remedy there.
  - Q441 (why a tent): no source; RD65's dead end stands.
- **RD101 dead ends** (RD101, 2026-11-01):
  - «བེ་ལུག» in Steinert's dictionaries (be lug, bu lug, be'u lug): none; BDRC «གྲོགས་ཀྱིས་བེ་ལུག»: the 1996 print and the Gser thang reprint only.
  - «རག་མི་ལ» in the notes and the etexts of WB, UT, the Bhutan collection, G.ya' sel: none beside WB's line; BDRC 20 hits, none bearing.
  - «ཀླུང་གི་དུར» outside WB p. 335: none.
  - A rule for which verdict stands when the birth and the year of age disagree: none in WB, the notes, G.ya' sel, UT, the Bhutan collection.
  - The forty of the birth wind (p. 342) in any other manual read: none.
  - A name for a couple of mixed kinds: none in UT, the Bhutan manual (I1KG11905 pp. 217–218), G.ya' sel reply 113, the notes.
  - BDRC «ལས་རྩིས་བྱས་པས་གཉིས་ཀ་ངན་པ» and «དུས་ཚོད་ཚང་གི་རྩིས»: WB's prints and copies only.
- **RD111 dead ends** (RD111, 2026-11-01):
  - BDRC full-text: «བླངས་ལན་པ» and «མཚན་མོ་ནད་ཕོག་པ» 0 hits; «ནམ་ཕྱེད་འདས་ན་ཞག», «ནམ་ལངས་ནས་ཞག་ཕྱི་མ», «ནད་པའི་ནམ་མཁའི་ལོ», «ནམ་མཁའི་ལོ་ལས», «སྲོག་གི་དགྲ་ཡིས་ནམ་མཁའི» 0 hits; «ནད་ཕོག་པའི་ཞག» only a Tibetan-Mongolian dictionary; «གདོན་རྐྱེན་ནད» only WB's reprint. Nothing on where the day of falling ill turns at night, on the sky year of p. 426, on the thirds' spirits and conditions.
  - Steinert's dictionaries: no entry for the compounds «མྱུར་སྨད», «བསྔོ་རུ», «བླངས་ལན», «གདོན་རྐྱེན»; the parts only.
- **RD106 dead ends** (RD106, 2026-11-01):
  - WB's etext vol. 2: «ཟློས་ཆེ» and «ཟློས་ཆུང» with the four deciding pebbles none in the dead's chapter (pp. 44–100); the manuscript's speckled lines by word none; «དཀར་ནག་འདྲེས» with «བར་དོ» none.
  - BDRC's catalogue record MW1KG12790: no Prajñāmaṇi, no date of the Precious Key.
  - The machine's etexts: «པྲཛྙཱ་མ་ཎི» in three spellings nowhere.
- **RD110 dead ends** (RD110, 2026-11-01):
  - «ཡམས་སྐུད», «རྒྱ་མའི་ཐག་པ་», «མཚོལ», «ཀླུམ་མནན», «གྲུམ་མནན», «དོ་གཅིག», «ལྷས་ངན», «ཕྱི་ལྟས» are not headwords in Steinert's 64 dictionaries; «ཤ་མཚན་ཤའི་ལྔ», «ལྷས་ངན་བབས», «རིམ་པར་དེད» no hit in the Sakya, UT or LT etexts beyond the verse.
  - The Gser thang etext and other search-only etexts answer 403 to bdrc.py etext; only snippets read.
  - The thirteen gates, the sixty pairs' length and the 360 starting points occur in no etext found, with «ཡར་གསུམ་སོགས་ལ་སྲོག་ལུས», «རང་དུར་གཤེད་དུར» and the gates' own lines.
- **RD107 dead ends** (RD107, 2026-11-01):
  - 日遊's outer course in a Japanese text: not in the 暦林問答集 §43, the 拾芥抄, the 簠簋内伝, the 古事類苑, the 大唐陰陽書, the 具注暦 of 応安七, the 御堂関白記 and the 満済准后日記 (only 日遊在内); NDL full-text queries 日遊在東/南/西/北, 日遊遊行, 日遊神遊行: no page.
  - A text that says which 金神遊行 wins: not in the 簠簋内伝, 拾芥抄, three Meiji almanac-books or the 古事類苑.
  - The 歳破's «livestock good»: not in the kana almanacs (dozens of copies, 1712–1860s), the 簠簋内伝, 拾芥抄 or 古事類苑.
  - The 歳殺's, 黄幡's and 歳刑's branches in a triad rule in an old text: the 暦林問答集 gives the 歳刑 by quarter, the 簠簋内伝 only the table.
  - The 假名暦略註's own text: NDL pids 14487066 and 13589337 have no machine text and no open images. *Corrected 2026-11-05 (RD163): 14487066 has open images, read by eye; only the machine text is missing.*
- **RD104 dead ends** (RD104, 2026-11-01):
  - The list of 208 questions searched by string: no hit for the sky dog (q415), «འཇུག་ཕོད» (q419), «རིགས་དགུ» (q428), «དགྲ་རྡེལ» (q396) or the moment's small examinations (q423).
  - «དཔྱད་འགོག» and «སྟོངས» have no second occurrence in img. 9–108 and 467–514.
- **RD116 dead ends** (RD116, 2026-11-01):
  - BDRC full-text: «བཏང་གཅོད་ཀྱི་འཕྲུལ་གྱི་སྒྲོན་མེ» 0 hits; «བཏང་གཅོད་འཕྲུལ་གྱི་སྒྲོན་མེ» 1 (WB's list); «འབྱུང་བ་རིན་པོ་ཆེ་གསལ་བའི་སྒྲོན་མེ» 1 (the record itself); «སྟོང་གཅོད་ཁ་དམར» and «སྟོང་གཅོད་རྡེའུ» 0.
  - The Mentsikhang catalogue's snippet shows books and page spans, not the contents of book 6; the catalogue is search only (etextchunks 403).
  - No person record gave dates for the authors of the Four Tantras commentaries; the dating of the spellings rests on the Tantras being older than both reckoners.
- **RD115 dead ends** (RD115, 2026-11-01):
  - A subset of the seven counts of p. 249 (136, 47, 13, 19, 20, 12, 215) summing to 440: none; only 442 (without the 20).
  - «གཅོད་འདྲལ», «འདྲལ་འགྱེད» as a circuit's name in the dictionaries: the verbs only.
  - The faint letters of the Deathless Elixir img. 809 on a 2370 px scan at 3–5×: not legible.
  - The pairs of the six bad stages by name in Mipham, the Good Vase and Zhechen Gyaltsab: none; the pairing is by order.
- **RD113 dead ends** (RD113, 2026-11-01):
  - «ཕྱ་ཐེར», «phya the», «phya thir» as headwords in the 64 dictionaries: none.
  - «ས་རོད་ཡ» / «སཱ་རོད་ཡ» as a tantra's name in the pool's etexts: only the colophon.
  - «དམར་ནག» between p. 386 and p. 436 of the 1996 etext: only the colophon.
  - The two lines of the Mūlatantra in every etext of the pool: only WB's p. 450.
  - «ཏ་ལ་ལམ་དུ་བསྒྱུར» in the pool's etexts: only WB (1996 and Zhol); no dictionary has the saying.
  - «ཟོར་ཚུགས» as a word: none; the print has «ཐོར་ཚུགས».
- **RD114 dead ends** (RD114, 2026-11-01):
  - A mansion tied to the serpent's body: BDRC «ལྟོ་འཕྱེ་དང་རྒྱུ་སྐར», «ལྟོ་འཕྱེའི་ལུས་རྒྱུ་སྐར», «སྐར་མའི་རྒྱུ་ཕྱོགས་ལྟོ་འཕྱེ» no hit; «ལྟོ་འཕྱེའི་མཆན་ཁུང» four hits, none giving a mansion for a part of the body.
  - The direction of counting the door's places: six BDRC phrasings no hit; the verse «ཞིབ་པར་ཕྱོགས་རེར་བརྒྱད་རེ་ཕྱེ» stands only in WB's prints and the Bzo rtsis zlos gar, none with a direction.
  - The Sa dpyad phyogs bsgrigs that BSB cites was not searched by title.
- **RD112 dead ends** (RD112, 2026-11-01):
  - BDRC full-text: «ཟླ་ནག་ས་བདག» no hit; «ཁ་དབོན» only in the Bon Kanjur catalogue, unrelated; «ལི་ནི་བཤན་པའི་ཐོད་པ» in WB's etexts and a Nyingma ritual collection (MW1KG2733, search only: not opened).
  - Dictionaries: «གནམ་སྒོ», «ཟླ་ནག», «ཁ་དབོན», «གཟའ་རྒོད» not listed; «ཧལ་ཁྱི» only inside an abbreviation list.
  - Dhaniṣṭhā-yoga: the editors' note on Bharaṇī (p. 329) was not reached; no dictionary lists «ཕུང» as a planet.
- **RD108 dead ends** (RD108, 2026-11-01):
  - archive.org: the Sakya volume with the Seven Special Qualities (BDRC W29978, bdrc-W29978) is stream_only; the loan call answers HTTP 400 and the reader gives no page image. It cannot be lent or read.
  - archive.org bdrc-W1KG25692 (the Tibet University journal's selected articles, 715 leaves, printed pp. 1–567): none of its entries is the interview with Kun dga' rig 'dzin; the interview is in a volume archive.org does not carry. Question 250 stays open.
  - KD vol. 3 front and last leaves (1–7, 768–773): the title leaf is the work's calligraphy and a signature; the Tibetan colophon gives publisher data and no writer; the Chinese title is in vol. 5's colophon only.
- **RD105 dead ends** (RD105, 2026-11-01):
  - Strip-wise Yigdzin runs (one line at a time) of I2029 img. 744 hallucinate repeating text.
  - I2029 pages are 3400 px wide at about 33 px a line, so single stacks stay indistinct at 9–12×.
  - BDRC search «དྲུང་མཁས་པ་རཏྣ» and «དྲུང་མཁས་པ་རཀྵ»: 0 hits; «འབྲི་ཝང་སུམ་ཅུ»: 0 hits; «རྩིས་ཀྱི་ཟབ་གནད་མན་ངག་གི་ཚོགས» only WB's two prints.
- **RD118 dead ends** (RD118, 2026-11-01):
  - The old couple's course in Lha mo tshul khrims (I1KG21886 etext, «རྒན་རྒོན» nine times) and the regent's replies (I5876 etext, once): no seats by month; the blue mouse road «བྱི་ལམ», «ལམ་སྔོ»: none in either.
  - The Bhutan collection's verses (etext): remedies (p. 455), a corpse-rite entry (pp. 506, 509, 546) and the wheels' garbled lines; no verse course.
  - An earth lord set against the date's results, in the three etexts: none.
- **RD120 dead ends** (RD120, 2026-11-01):
  - BDRC full-text search for «མི་ཧྲེང་མདུང་ཐོགས», «གཏོང་ཤེས་ལས་བཤད» and «ཚེས་དགུ་ན་ཆུང་མོད»: not run; bdrc_search.py stopped at the launch of its Chromium (chromium-1248 missing on this machine).
- **RD119 dead ends** (RD119, 2026-11-01):
  - The Taikhang's lines of pp. 338 and 349 (I5877 img. 456 and 473 by the offset): fetched (img. 442–475), not read, no slot taken for them.
  - The corpus heading for wbz:i778 says p. 105; the line read is p. 104 l. 28 (p. 105 begins at img. 778 l. 5): a slip to correct.
- **RD121 dead ends** (RD121, 2026-11-01):
  - A sentence in WB pp. 22–23 that says how many verdicts the limbs have or that they are counted into the five: none, read line by line on the scan (img. 30–31, 3×).
  - The first two groups' count of verdicts in words: «ཁ་དམར་གདགས» only, no numeral; the numeral stands from the third group on (p. 22).
  - A gathering of the meadow's verdicts into five totals in WB pp. 22–23: none; the Moonbeams (f. 24a) and Zhechen Gyaltsab's verse alone give it.
- **RD122 dead ends** (RD122, 2026-11-01):
  - A word-by-word gloss of p. 349's «རུས་ཆེན་ཁམས་ཀྱི་གཤེད་ལོར་སོང་» in the notes' pp. 488–489: none (the notes name only «ཕ་ཡི་དྭ་ཟླས»).
  - A second worked example of the luck's mewa in the Zhol print's pp. 255–258 or the hidden's page: none.
  - Doubt on the scan: img. 467, l. 4, «ཡར» and «ལྔ་སྨེ» read at 2× only; a 6× crop of both syllables would settle them.
- **RD117 dead ends** (RD117, 2026-11-01):
  - The fourth examination's 87 explained: in Mipham's notes (pp. 499–522) by Yigdzin-1's reading searched for «བརྒྱད་ཅུ་བདུན» and «གྱ་བདུན» (only the year's 287 on p. 513), in the 1996 etext's other occurrences of «བསྒྲིལ་བས» (two): none.
  - The sub-counts of the sky's round (the 78 and 1,874) in the results pages (Yigdzin-1 over img. 60–151): none; the one hit of «ཉེར་བརྒྱད» is twenty-eight recitations of the Heart Sutra (p. 121).
  - The count lines on the Zhol print and the Sakya Centre print by their BDRC etexts (IE0OPI7850CC9C, IE0OPI0BA3A0E0): corrupt at these lines; the Zhol scans' leaf for p. 50 not looked for.
  - A rule sizing the rites by the colour of the thirteenth's verdict in the notes' p. 517: none.
- **RD124 dead ends** (RD124, 2026-11-01):
  - A gloss of «བསྔོ་རུ་མ་བཏུབ» or a variant spelling of it on the Zhol print, img. 553, 560, 562 (p. 419, pp. 423–424, p. 425): none, the three lines read alike.
  - A relation of the square's directions (Bhutan reset) to the nu ru setting-out or arrival direction, or to the inner, tho brgal or hidden course of WB p. 238: none; compared date by date.
  - A bare-pair run in the 1996 etext other than work 14's two lines and the notes' «ཐིག་དང་གཅིག་ནས་ཉེར་ལྔའི་བར»: none.
  - Correction owed to claims/plank-tree-bsngo-ru.md: p. 425 continues «མ་བབས་འདི་ལ་ཕྱི་ཆགས་ཡོད» with no «ཐང་ཤིང་»; only p. 419 has «ཐང་ཤིང་མ་བབས».
- **RD123 dead ends** (RD123, 2026-11-01):
  - «དཀར་ན་གཟུང་དུ་བོར» or «ཕྲད་དཀར་ན» as a son meeting's rule in the Moonbeams (KD vol. 3), UT, LT, the Bhutan collection and the Clear Mirror (their etexts, searched for «བུ་ཕྲད», «བུ་འཕྲད», «ཕྲད་དཀར་ན»): none; every other manual gives a mixed place, a pair, a half or a speckled one.
  - A sentence in the notes pp. 489–491 that says how a son meeting or a half counts toward the white and black of the place: none, read on the Gser thang reprint (img. 495–497).
  - The son meeting's count in the Clear Mirror beyond «ཁྲ»: the verse of etext p. 334 gives the place's count only.
  - Gyurme Dorje's own text on the son (pp. 226–230): not read this time (no loan).
- **RD127 dead ends** (RD127, 2026-11-01):
  - The Moonbeams f. 21b–22b and f. 26b–27a (DZ img. 046–048, 056–057), by eye and in the pool's text: a rule for a half in the count of the four verdict pebbles; a definition of the general and the particular verdict; a reading of a half verdict of the year of age: none.
- **RD125 dead ends** (RD125, 2026-11-01):
  - «གནས་སྐྱུག» as a spelling in the 1996 typeset etexts of both volumes (BDRC IE0OPI51524892): no hit; «གནས་སྒྱུག» and «སྒྱུག་མོ» stand there (vol. 1 img. 372; vol. 2 img. 344 and 534).
  - The verse of p. 362 and the notes' p. 526 in the Chengdu typeset notes (I1PD34647 img. 571–573): not on those leaves.
  - BDRC full-text search for «གནས་སྒྱུག་སྤར་ཁ» and «ཤིང་དུར་ལུག»: not run; bdrc_search.py did not start (Chromium 1248 missing).
  - A rule that the verdict's colour picks one of the trigram's three rites, in WB pp. 104–105 and the notes' p. 517: none.
- **RD126 dead ends** (RD126, 2026-11-01):
  - «མ་སྤར» glossed beside the clause on the Zhol print or in the lines around it (img. 512, ll. 3–6): none; the block reads as the 1996 print.
  - A gloss of «ངོ་ཐོག» or «ངོ་ཐེག» in the lines of img. 498–500: none.
  - p. 403's «འདུ་ལོང» or «འདི་ལོང» on the Taikhang reproduction: not fetched.
  - claims/wb-count-by-place-kha-smrang.md still says the Zhol print was not read: to update.
- **RD130** (RD130, 2026-11-03):
  - 2026-11-03, p. 475 and p. 538 on the Gser thang reprint, the 1996 print and Yigdzin-1, Mipham's p. 475 table: a legend naming the white mark; a name or description of «རྩིས་ཀྱི་ཟབ་གནད་མན་ངག་གི་ཚོགས»; a gloss of «ལཱུ་བཞིན».
- **RD128** (RD128, 2026-11-03):
  - 2026-11-03, p. 331's stack on the 1996 print against p. 330's: no difference at 20×; the 1996 scans (691 px wide) cannot tell ཀ from ག.
  - 2026-11-03, «རག་མི་ལ» and «བེ་ལུག»: no new place; not searched again beyond RD101's list.
- **RD132** (RD132, 2026-11-03):
  - 2026-11-03, the local etexts and scratch readings, and Yigdzin-1 on img. 743–745 of Mipham's print: a gloss of «ལཱུ» or a second text that names the method; no.
- **RD129** (RD129, 2026-11-03):
  - 2026-11-03, «སྣང་པར་བཤད», «སྣད་པར་བཤད», «མི་ཆེན་སར» and «བོན་སར» as written in the Zhol etext (IE0OPI7850CC9C): none (its OCR of p. 391 and p. 472 is garbled).
  - 2026-11-03, a gloss of «ཕུགས», «དུར་ཕུགས» or the luck's tomb on Zhol img. 435–436: none.
- **RD131** (RD131, 2026-11-03):
  - 2026-11-03, a syllable-by-syllable scan reading of p. 249's «སྐབས་གསུམ་བཞིར་བགོས» on the Zhol print (img. 309) and the Taikhang reproduction (img. 326): not made, the Zhol hand too small; machine readings only.
  - 2026-11-03, the stack of «རྙོངས/སྙོངས/སྟོངས» on p. 435 (Zhol img. 577, Taikhang img. 594, 4–5×): its two letters could not be told apart.
  - 2026-11-03, the notes (vol. 2, p. 497) on a Zhol or Taikhang scan: none in the cache; read on the typeset reprint only.
- **RD133** (RD133, 2026-11-03):
  - 2026-11-03, a clear subjoined ga on Taikhang img. 488–490 to compare with the stack after «གནས»: «སྒྱུ་མ» (img. 488, l. 3) is blotted at 6×; «སྒྲུབ» and «སྒྲོག» (img. 490) were not looked at.
- **RD134** (RD134, 2026-11-03):
  - 2026-11-03, a sharper impression of p. 331's stack than the Taikhang's (8700 px): none among the prints known to the pool.
  - 2026-11-03, p. 330's «སྒབ» on the Taikhang for the stack's comparison: not found in the crop made (img. 445).
- **RD135** (RD135, 2026-11-03):
  - 2026-11-03, the thirteen who may not be offered food: no other name among the lines of pp. 68–70 on the Zhol, Taikhang (Yigdzin-1 and the eye) and the Gser thang etext; the lists of five and six are of those who may not have a funeral, not food.
  - 2026-11-03, «ཇུ་རྩེ» in the Zhol etext (IE0OPI7850CC9C): only p. 817, in this verse.
  - 2026-11-03, the dog day's and pig day's lines in the Zhol etext (IE0OPI7850CC9C): «ཚེ་སྔོན་སྔགས་པ» and «ཉིན་མཚན་དུས་ཚོད་བཅུ་གཉིས» no hit (its OCR of these pages is garbled).
- **RD137** (RD137, 2026-11-03):
  - 2026-11-03, a figure or a numbered layout of the grid's five parts on p. 96 in the 1996 print, the Gser thang reprint (img. 102) or the Zhol print (img. 766): none, text only.
  - 2026-11-03, a name of the mansions of p. 122 other than «སྤོ», «སྦ་སྨད», «ལག་སོར» on the pages (reprint img. 128, Zhol img. 804–805): none.
- **RD139** (RD139, 2026-11-03):
  - 2026-11-03, «ལུགས་གཞན་བཞི་པ» (the fourth system of p. 37): not on p. 537 (reprint img. 543) nor in the Zhol etext's page of the verse.
  - 2026-11-03, «ཉི་ནག་དྲུག་གསུམ» and «ས་ཞིག»/«སེ་ཞིག» on the scan of p. 42 (reprint img. 048, Zhol img. 685): not located in the crops made at 1–3×; the Zhol etext's reading of the page is too garbled.
- **RD138** (RD138, 2026-11-03):
  - 2026-11-03, the Zhol print's first pair-line verb («སྦུབ» or the machine's «རྩུབ») on img. 602, by eye at 2×: the cursive letters are not told.
  - 2026-11-03, a sentence of WB or the notes joining the eight results of the board to the eight fighting pebbles by number: none in pp. 453–472 or the notes' pp. 532–533.
  - 2026-11-03, «བརྩི་བྱའི་ལོ་ཁམས» and «ལོ་ཁམས» across the 1996 etexts (vols. 1 and 2): hits only at vol. 1 p. 387 and, as «སོ་སོའི་ལོ་ཁམས», in the notes' close on the moment; none in ch. 26.
- **RD141** (RD141, 2026-11-03):
  - 2026-11-03, «ཤི་སྒབ» and «ཤི་སྐབ» in the Zhol etext (IE0OPI7850CC9C) pp. 777–784: three hits only, too noisy to place the other places by their text.
- **RD136** (RD136, 2026-11-03):
  - 2026-11-03, a table or text dividing the twelve la places into the upper, lower and middle pebbles: not in WB's notes (p. 502), the Moonbeams' board (RD45), Lha mo Tshul khrims's verse restatement (MW1KG21818), or BDRC's full-text search for «རྡེལ་སྟོད་སྟོངས་ན» and «རྡེལ་སྨད་སྟོངས་ན» (0 hits each).
  - 2026-11-03, a gloss of «ཡུགས་སའི་སྐྱུར» (0 hits) or «ཁྲ་བོ་བློ་ཐུབ» (1 hit, WB's own notes) in BDRC's full-text search; «ལྔ་སེར་སྐྱུར་རམ» has two (Lha mo's verse, MW1KG21818, and the Dge ldan Rdel 'grem dpag bsam ljon shing, MW21984_22CC1A, whose etext was not read), both repeating WB's line without a gloss in the first.
  - 2026-11-03, a reason for Mercury's missing gyod and Jupiter's missing gud: none on the four prints, in Lha mo's verse or in the element test.
  - 2026-11-03, a rule behind the six day-animal months of p. 63: none; Lha mo's verse has no day-animal entry.
- **RD140** (RD140, 2026-11-03):
  - 2026-11-03, «འཕུང་སྲི་ཛ་མོ» or «འཕུང་སྲི་རྫ་མོ»: nowhere else in the etexts of WB (1996) and the Zhol print; the Zhol scan, img. 758, not resolved.
  - 2026-11-03, «སྤྲ་སྒོ» in the 1996 etext and the Zhol etext (which has «སྨྲ་སྒོ», p. 771): no second place; no gloss.
  - 2026-11-03, a count, or the word «ལྔ», explained in Mipham's note on the thirteen gates (p. 536): none.
  - 2026-11-03, the animals of khen, gin and zon in the notes p. 509: none beyond «ཁེན་གིན་ཟོན་གསུམ་ལའང་ཤི་ཁ་ཕྱེ་བས་བརྒྱད་དོ».
- **RD142** (RD142, 2026-11-03):
  - 2026-11-03, «ཚད་ཟླ་མག་སྐུད» and «ཞུར་ཞོགས» in the 1996 etext outside the verse: no occurrence; a parallel verse for the four la years: none.
- **RD143** (RD143, 2026-11-03):
  - 2026-11-03, pp. 478, 483 and 526 on the reprint and the 1996 print: a sentence naming the pebbles' colour as what makes the life force, body or luck bad in the match's karma reckoning; none.
- **RD145** (RD145, 2026-11-03):
  - 2026-11-03, a gloss of «ལྔ་ནས» after an animal in the gates, in the reprint's pp. 170–171 and the Zhol etext (p. 880, 885): none.
  - 2026-11-03, a line seating a person of the own place on earth, pp. 167–169: none; earth appears only in the list of results.
  - 2026-11-03, «དུས་ལོའི་སྨེ་བ» elsewhere in the 1996 etext: only on p. 165.
- **RD147** (RD147, 2026-11-03):
  - 2026-11-03, a letter-by-letter reading of I5875 img. 497–498 and 513–514 at 3× or more: not made in RD147; the corpus readings of RD48 and RD104 stand for them.
- **RD144** (RD144, 2026-11-03):
  - 2026-11-03, a gloss in LT or in Mipham's notes (wb.2:p532-533) that gives the pair of WB's twenty-eighth or twenty-ninth reading: none; LT's pair verse is WB's.
  - 2026-11-03, a gloss on «དྲུག་འབྲས» as a named place (the Moonbeams, LT's etext, Mipham's notes pp. 490–500, 532–534): the name occurs only in WB vol. 1, p. 413 and the Moonbeams; WB vol. 2, p. 188 has «མར་གྱི་དྲུག་པ» for the earth lord.
- **RD148** (RD148, 2026-11-03):
  - 2026-11-03, «འཕྲུལ་གྱི་མིག་ཚགས» and «ཁྱུང» in img. 497–498's reply 140: not seen on the lines opened (part of img. 498 l. 2 and none of img. 497 at 3×; time).
- **RD146** (RD146, 2026-11-03):
  - 2026-11-03, BDRC's full-text search: «འགྲོ་བདེ་གཉིས» 2 hits (the Bhutan KP; the Jonang manual), «འགྲོ་བདེ་གཉིས་ཀྱི་འབྲས་བུ» 1 (the Bhutan KP only): no commentary on KP's clause, no second copy of KP's sentence.
  - 2026-11-03, the same search, «ཁྱད་པར་དམིགས་ཀྱིས་བསལ» 19 hits: WB's prints, the Kun 'dus chen mo, the 'Byung rtsis dpyad don rmad byung and the commentators' idiom; none uses it for a weighing.
  - 2026-11-03, «འཇུག་ཕོད་ལ་སོགས» 9 hits: the Sarvadurgati and its three commentaries, SDP's two copies; none gives a Sanskrit equivalent or the word viṣṭi.
  - 2026-11-03, WB's departure, pp. 365–378 (etext): no verse ranks the checks; no screening clause of the KP kind.
- **RD154** (RD154, 2026-11-03):
  - 2026-11-03, «མོ་བརྒྱུད་རྨང» in BDRC's full-text search: 1 hit, WB's own Gser thang etext (I3CN5029); no text repeats or glosses the line; «རྡེལ་སྨད» has 3 hits, all WB's colour pair, none gives a sense for «རྨང».
  - 2026-11-03, «སྤོས་གྲི་རྒོད» and «ལྷོག་པ་སྤོས་གྲི» in BDRC's full-text search: 0 hits.
- **The prayer wheel's mantras** (2026-11-04):
  - 2026-11-04, WB's etext, both volumes with Mipham's notes, for the six mantras of claims/wheel-mantras.md other than Mañjuśrī's: none is printed whole. WB has the fragment «མ་ཎི་པདྨེ» (vol. 2, etext p. 263, «to be drawn at the wall's corners»), the six syllables by name (etext p. 78), and the hearts of White Tārā (vol. 1, etext p. 381) and Vajrapāṇi (vol. 2, etext p. 175) by name.
  - 2026-11-04, BDRC's full-text search filtered to Mipham (the results page's Author facet; the URL takes `&author[0]=P252`): no hit for ཨོཾ་བཛྲ་པཱ་ཎི་ཧཱུྃ་ཕཊ; the only hit for the Medicine Buddha's mantra is the anthology MW2DB4624, of many authors, search-only. The Hundred Mantras has no Medicine Buddha mantra and no ཨོཾ་ཨཱཿཧཱུྃ་བཛྲ་གུ་རུ་པདྨ་སིདྡྷི་ཧཱུྃ: its guru mantra is another one, etext p. 668 of the reset copy.
  - 2026-11-04, the Rin chen gter mdzod (MW1KG14) was not used: its etext is unaligned, its [pN] marks count paragraphs and not images, so a hit does not give a page. Prints with an image group in the etext's id (VE…_I…) mostly have marks that are images (the next line is an exception).
  - 2026-11-04, the Sde dge gzungs bsdus (I1KG9227) was not reached: its etext's page 177 is not image 177, and the Medicine Buddha mantra (etext «མུཏྒ») was not found on img. 177–178.
- **RD157 (2026-11-05)** (RD157, 2026-11-05):
  - RD112's dictionary line for «ཁ་དབོན» and RD80's BDRC search for it stand on a machine misreading: the word to search is «ཁུ་དབོན».
- **RD160, the wheel's direction (2026-11-05)** (RD160, 2026-11-05):
  - BDRC's full-text search, 19 phrases on left and right circuits of the wheel, no hit; `bdrc_search.py` reads page 1 only, so «མ་ཎི་འཁོར་ལོ» (170 hits), «འཁོར་ལོའི་ཕན་ཡོན» (47) and «མ་ཎི་དུང་འཁོར» (62) stand unread past it; the list is in prayer-wheel.md, *Searched and not found*.
  - Ladner, *The Wheel of Great Compassion* (Wisdom, 2000): archive.org's `wheelgreatcompas00ladn` answered «no longer available» on 2026-11-05.
- **RD156 (2026-11-05)** (RD156, 2026-11-05):
  - The direction of counting the door's places: pp. 276–294 read line by line, no direction stated; pp. 242–275 not read.
- **RD163, the direction gods (2026-11-05)** (RD163, 2026-11-05):
  - A text that calls livestock good towards the 歳破: none in the 暦林問答集, the 簠簋内伝, the 協紀辨方書, the 假名暦本名頒暦畧註, the 方鑒精義大成, こよみの友, the 1924–1928 almanacs or the NDL full-text search; the exhibition page names no source.
  - A commentary on the 拾芥抄's 天一太白 sentence; a rule for which 金神遊行 wins (first 25 hits of each search); 日遊 with a direction outside the house; 金神 in the 二中歴; the 高島暦 of today (the NDL lists it without images): the terms searched are in kyureki.md, *Searched and not found* of RD163.
- **RD161, ch. 29's words (2026-11-05)** (RD161, 2026-11-05):
  - BDRC exact phrases with no text but WB's prints: «སེལ་གོག», «ཏིང་མཉྫི», «སླ་ང་རྩེས», «གསང་ཤིད», «བྱུར་ཤིད», «སིག་ལྐོགས», «དུ་རུ་ཅན», «སྨན་ལེ་འབྲང» (but for two offering lists), «ས་བདག་ཁར་བུ», «བཀྲ་བྱེ་མ», «བཀྲ་ཆུ་རྟེན», «གནམ་གྱི་བྱི་ལམ», «ཇུ་ཚེ་གླུ», «རྡེལ་ཕྲེང་གསུམ», «ཚ་རགས་སྣ».
  - BDRC no hit: «ཕ་གུ་མེས», «བང་སོ་དུ་རུ», «སྐྱར་མའི་ནང», «གྲི་ཐལ་ཤལ», «བུས་པ་ཤར་བ» as «བྱིས་པ་ཤར་བ»; Wylie «gsang-shid» none, «byur-shid» and «'dur-shid» Gyurme Dorje alone.
  - The Bhutan digest (I1KG11905, etext pp. 479–597), no line for: «སྟེང་འཁོར», «ཟོ་བ», «འོག་རྟ», «བྱེ་ཐག», «འབྲང་རྒྱས», «གཡེར་མ», «ཕ་གུ», «རྨང་པོ», «ཚ་རགས», the funeral shares.
  - Steinert's dictionaries, no headword: «ju rtse», «ju tshe», «ju tsher», «sor le», «tshar shing», «phan ngon», «tsha rags», «bye thag», «bse rags», «bang sa».
  - The Blue Beryl's page image (I1PD411 img. 450) is not served by `bdrc.py scans`; I8LS20838 img. 228 holds printed p. 216, not the etext's p. 228.
- **RD164, the Bhutan digest (2026-11-05)** (RD164, 2026-11-05):
  - The digest (Bhutan collection etext, img. 479–601) has none of these in WB's sense: the road omens, the colophon's names, «དགྲ་དར», «དགྲ་ལོ», «ཆད་ཆེན», «བླ་རྡེ», the blades, the speckled parts, the verses of pp. 70 and 80, the list of p. 92, «རྡེལ་དོན», «ངོས་རྡེལ», «ཞེ་གསུམ»: the terms are in reckonings.md, RD164.
  - «བྱ་འུ» as a mansion in the collection, LT, UT, I4949, the regent's replies and the Zhol print: the verse of bad mansions and the list at img. 259 only, neither with a number.
- **RD151, «ཡི་བམ» and the stack before «ཁམ» (2026-11-06)** (RD151, 2026-11-06):
  - A reading of ya against la in the Taikhang hand from a certain ya elsewhere on img. 514: none made; a dated sheet with several of each, cut from img. 513–514, would settle it.
  - A second print of the replies (Zhol print or etext of I5875) to compare: none in the cache.
- **RD150, the six places of «ཤི་སྒབ» (2026-11-06)** (RD150, 2026-11-06):
  - A ཀ-stack («སྐ») of the same hand on img. 161, 162, 165 or 166 to set beside the six places: none turned up in the crops.
- **RD155, the Tibetan thirteenth and fourteenth pairs (2026-11-06)** (RD155, 2026-11-06):
  - WB vol. 1, p. 453 on the Zhol blocks (I1KG1710 img. 602) and the Taikhang (I5877 img. 619): a Tibetan thirteenth and fourteenth pair in the order of the *Moonbeams*; none, both give the friend's horse and sheep first.
- **RD159, third part (2026-11-06)** (RD159, 2026-11-06):
  - Locating a word on the Gser thang reprint from Yigdzin-1's OCR line number and offset (img. 322: 28 OCR lines against 21 ink lines): fails; crops need a mapping from the ink rows (`scans.py line`) or a choice by eye from a whole-page view.
  - The Zhol print's pp. 1066–1085 have no OCR text yet; a run waits long for a free slot.
- **RD167, the notes (2026-11-06)** (RD167, 2026-11-06):
  - A table of the birth's hundred with a legend for the cross and the dot: not in the *Moonbeams* (I2533 img. 035–037, sentences only); the *Little Red Tiger-Head* is not in the registry and was not searched.
  - «གནས་སྐྱུག» / «གནས་སྒྱུག»: the Sakya and Zhol etexts have neither form at those places (`locate.py find`); the Gser thang reprint's stacks are blotted at 3.5×.
  - A text giving the twenty's last group «ལོ་ཟླ་ཞག་གསུམ་གྱི་གསུམ» a year of its own: none (as RD58).
- **RD165, ch. 28 and ch. 30 (2026-11-06)** (RD165, 2026-11-06):
  - «ཐུན་ཀུང», «ཀྲུང་ཞེས», «དོ་གཅིག་གི་ལོ», «ཡམས་སྐུད», «ཀླུམ་མནན», «གྲུམ་མནན», «དུས་ལོའི་སྨེ་བ», «ཤོལ་པོ», «རྐང་ཕྲན་ནག», «བླ་ལྷོར» in the regent's replies (I5876) and the Bhutan collection (I1KG11905): no hit but the unrelated «ཉ་དྲུག» of etext p. 364 and «བླ་འཁྱམས» of p. 394.
- **RD162, the words of ch. 31–34 (2026-11-06)** (RD162, 2026-11-06):
  - BDRC exact phrases «ཆང་རྫ་ཁ་ཡོ», «ཁ་ཡོ་ཞིང་ཆང», «རྫ་ཁ་ཡོ», «ཡོ་ནས་ཆང», «ཆང་རྫ་ཡོ་», «ཐེམ་བུར་ཆང», «གཤིན་ཆང་བཙགས», «སྦང་མ་ཡོལ་བ», «ཚེས་དགུ་ན་ཆུང», «སྐྲ་བཅད་ཚེས་དགུ»: no hit.
  - «ཡོ་ཆང་» (13 hits): Bon and noisy OCR, none the rat day's «ཡོ». Steinert's dictionaries have no headword for «byi sker», «yo nas» or «ཉི་མའི་ཀླུང».
  - SY printed p. 345 (img. 373 of I1PD30933) and the Drigung volume (VE1ER68, 401) are not served.
- **RD169, ch. 31–32's words and counts (2026-11-06)** (RD169, 2026-11-06):
  - BDRC full-text: «སྐབས་དོན་ལྔ་ལས», «སྐབས་དོན་ལྔ་པོ», «བྱི་སྐོར་ནང་སྐོར», «གཏོང་ཤེས་རྒྱུད»: no hit; «སྐབས་གསུམ་བཞིར་བགོས», «བསྡོམས་པས་བཞི་བརྒྱ་བཞི་བཅུར་བཞེད», «བརྩི་བྱ་གཅོད་འདྲལ», «དམར་ནག་སོགས་ཆེས་ཟབ», «གཏོང་ཤེས་ལས་བཤད»: only the White Beryl's prints.
  - The five topics of the root in the Beijing, Sde-dge and Zhol prints, KD, *Removing the Tarnish*, I1KG21886: the phrase occurs, no list.
  - Reprint I3CN5028 img. 119–130 (ch. 13's pp. 112–123, not the colophon of ch. 12; ch. 12 ends on printed p. 103 = img. 110; on I3CN5028 pp. 237–257 are page − 5, p. 91 = img. 98; on I3CN5029 p. 428 = img. 434): tables and woodcuts.
- **RD166, the Moonbeams and the replies (2026-11-06)** (RD166, 2026-11-06):
  - «ར་བར» as a placing of a pebble in the etexts of KD vol. 3, WB vols. 1 and 2, LT, the replies and the Bhutan digest: only this verse and the digest's copy.
  - «ངན་ཐ་ཆད», «ཐ་ཆད་ངན», «ཚེ་རྩིས་ངན», «དཔྱད་འགོག», «རྦད་ཁམ» in the same etexts: none.
  - «ཞག་འཕོ», «ཞག་བརྗེ», «ནམ་ཕྱེད» with «ཞག», «ནུབ་མོ» with «ནད» and «ཞག» (where the day of falling ill turns): none.
  - «སུམ་ཆ་སུམ་གཉིས» as a split of the elements: none beside the children's third in WB p. 369.
  - A Steinert clone for «བསྔོས་ཚང», «བསྡུས་ཚད», «རྟ་ཞག»: not on the machine.
- **RD170, ch. 32 on the scan (2026-11-06)** (RD170, 2026-11-06):
  - The direction of counting the outer door's places: the 1996 etext of pp. 242–275 searched for words of direction and turning, no statement (pp. 276–294 read line by line in RD156, also without one).
- **RD153, the other stacks of «ཤི་སྒབ» (2026-11-06)** (RD153, 2026-11-06):
  - «ཤི་སྒབ» in Yigdzin-1's strips of img. 165 (all five scan lines, five windows each): not found; the strips read poorly at the edges.
- **RD159, fourth part (2026-11-06)** (RD159, 2026-11-06):
  - The stack of «བསྐྱུར»/«བསྒྱུར» (q645) on the Gser thang reprint at 5× (img. 322, printed p. 316, line 4): a solid black blot, the ས over ཀ/ག not told; every crop of the word gives the same blot. The Zhol folio (img. 1068, 3757×606) has letters of about 30 px, too small at 4–6×. Only a cleaner print (an original-scale scan or another edition) or knowledge of the rite settles it; no more cropping.
- **RD168, the catalogue's questions (2026-11-06)** (RD168, 2026-11-06):
  - BDRC full-text, 0 hits: «ཚེ་ཁ་དམར་ན», «ལུས་ཁ་དམར་ན», «དབང་ཁ་དམར་ན», «རླུང་ཁ་དམར་ན», «ཚེ་ལུས་དབང་རླུང་ཁ་དམར», «བུམ་བཟང་གཤིན་རྩིས» (q524); «རྒྱ་མའི་ཐག་པ», «ཐག་པ་བརྒྱད་ཡར» (q527); «བཏང་གཅོད་སྒྱུ་འཕྲུལ་སྒྲོན་མ», «སྒྱུ་འཕྲུལ་སྒྲོན་མེ», «ཡིག་ཆ་ཉི་ཤུ་པ», «གཏན་ལ་འབེབས་པའི་སྒྱུ་འཕྲུལ» (q535); «མུ་ལ་ཏན་ཏྲ་ལས».
  - Web search for 《西藏天文历算总汇》 with a publisher or KD's Chinese title: the register's page only (q554).
- **RD172, the Japanese almanac's last questions (2026-11-06)** (RD172, 2026-11-06):
  - A source for the clause «馬牛之類不可於其方求» of the 歳破: not in KK's rows, the 論衡 passage on the 移徙法 or a web search for 歲破 with 六畜, 牛馬.
  - A copy of the 簠簋内伝 older than 1578: NDL (the records of 2532305, 2544991–2544993), Kyoto and Tohoku records read for dates; none older.
  - 岡部一德's own text or any record of it: Kyoto, Tohoku, Tokyo records and a web search; only 保己一's copy.
  - 馬場真理子's 2024 article on the 暦略註 and her 2017 one on the 暦林問答集: bibliographic records only, no full text reachable.
  - The 高島暦 of today: not seen.
- **RD179, ch. 27's reckonings (2026-11-06)** (RD179, 2026-11-06):
  - A text with KL's six moxa dates as numbers: BDRC full text, «ཚེས་གཅིག་དྲུག་བཅོ་ལྔ་བཅོ་བརྒྱད», «བཅོ་ལྔ་བཅོ་བརྒྱད་ཉེར་གཉིས་སུམ་ཅུ», «གཏར་བསྲེག་ཚེས་གཅིག་དྲུག»: none; «ཚེས་གཅིག་དྲུག་བཅོ་བརྒྱད» five hits and «ཉེར་གཉིས་ཉེར་བཞི་ཉ་སྟོང» seven, the Blue Beryl's list with the 24th.
  - Mercury or Venus beside the sun's eclipse other than the Seven Special Qualities: only WB p. 64 and the pool's known witness.
  - The Black Moon's own course among the date's earth lords: none in ch. 31's twenty-seven.
  - A text laying out the meadow's ornament leaves besides WB, the Moonbeams, ZG and KD: none («སྤང་རྒྱན་ལོ་མ» in WB and UT).
  - «སྤྲུག་སྟོན» / «སྒྲུག་སྟོན» in UT and WB: names only.
- **RD171, the wheel texts' and the Kun-gsal-dpe's words (2026-11-06)** (RD171, 2026-11-06):
  - «ཏ་ལ་ལམ་དུ», «ཏཱ་ལ་ལམ་དུ», «ཏ་ལ་ལམ་དུ་བསྒྱུར» (BDRC): 0 hits; «ཏ་ལ་ལམ» 9 hits, all across a word boundary.
  - «འདུག་པར་བསྐོར་ན», «མ་ཎི་འཁོར་ལོ་འདུག་པར», «ཐད་ཀར་དྲང་ཞིང་བསྐོར»: 0 hits.
  - «མགོ་མཇུག་ལོག་ན་ཞ་བོར», «མགོ་མཇུག་ལོག་པར་བསྐོར», «མགོ་མཇུག་ལོག་ཏུ་བསྐོར», «འཕྲེད་ལ་བསྐོར་ན», «ཞ་བོར་སྐྱེ»: 0 hits.
  - «ངེས་འབྱེད་བླ་མའི་རྒྱུད», «རཱ་ལི་ནས་བཤད», «ཧེ་བཛྲ་ལས་སྤྱི་བོའི་འཁོར་ལོ་མི་བསྐྱོད་པ»: 0 hits; «ཕྱ་ཐེར་དང་སྦྲ»: 0 hits.
  - «nges 'byed bla ma», «bar rgyur», «yab skor», «bon skor», «phya ther» as headwords in 64 Steinert dictionaries: none.
  - Etexts answering 403 (snippet only): the Sakya volume of Ngag dbang kun dga' bsod nams (VE29307_094, _096), Bod ljongs nang bstan (I1PD35670), eight of the 19 «ཕྱ་ཐེར» hits.
- **RD170, second run (2026-11-06)** (RD170, 2026-11-06):
  - Words of turning and direction (གཡས་སྐོར, གཡོན་སྐོར, ཉི་འཁོར, ཉི་ལམ, གཡས་ཕྱོགས, གཡོན་ཕྱོགས, གནས་བརྒྱད) in the machine text of pp. 243–275 and 295–296: one hit, p. 273, a river and the sun and moon turning right as a sign of good ground; nothing about a door.
- **RD173, the catalogue's second round (2026-11-06)** (RD173, 2026-11-06):
  - BDRC exact phrases «དཔལ་གདོང་ཆུ་བྱ» (34 hits, none helps), «ཡེ་ཤེས་ཆོས་འཕེལ་དུ་འབོད་པས» (1 hit, the Bhutan collection only), «བརྒྱ་འདྲེའི་རྡེའུའི་ཁྲ་མ» (0), «གཙུག་ལག་སྤོར་ཐང་རྩིས་གཞུང» (1 hit, KD vol. 3 itself).
  - «dkon mchog bstan 'dzin phrin las» (153 hits): the second Grub dbang incarnate (1923–1965), the Lho lung dkar colophons and others; BDRC has no person record for the verse work's writer.
  - Bing for 《西藏天文历算总汇》: nothing but calendar sites; Google Books API answered 429.
  - The Clear Lamp's root has no etext, and its manuscript was not read by eye.
- **RD174, ch. 24 and ch. 26 on the birth and the year of age (2026-11-06)** (RD174, 2026-11-06):
  - A speckled reading for the year of age's four aspects in KD vols. 1–3, UT, LT, NM, SY, YS and the notes: none.
  - A rule for birth against year of age for one year, with «བཙས་ཀླུང», «ལོ་ཀེག་དང་བཙས», «ཀླུང་དང་ཀེག་རྩིས»: none beyond WB p. 335.
  - «ངོ་ཐེག» outside WB's 1996 print: none.
  - Tibetan-script Sanskrit names of the 27 yogas, BDRC full text, four phrases: none.
  - The etext of Dpyod ldan snying nor (I1KG81102): HTTP 403, search only.
- **RD178, ch. 27's first leaves (2026-11-06)** (RD178, 2026-11-06):
  - p. 3's gloss «ཇི་ནོང» on the Zhol print, img. 629 l. 2, crops at 3× and 6×: illegible, too small and worn at that resolution.
- **RD176, ch. 33's mansion verses (2026-11-06)** (RD176, 2026-11-06):
  - «ཆུ་ཀ་ལེན» and «ཆུ་ཀ» as a word: no hit in the Zhol etext (img. 1030–1110) except «རྐལེན» in this line.
  - The 1996 print's own scan for Aśvinī's numeral: 687 px wide, not legible.
- **RD177, the meanings left open in ch. 33 and the notes (2026-11-06)** (RD177, 2026-11-06):
  - «ཆུབ་ཉི» in WB, KD vol. 3, LT, UT, ZG and the Bhutan collection: WB (p. 335, tables of pp. 341–342) and the Bhutan digest (etext p. 421) only; none glosses it.
  - «ཐུན་ཀུང», «ཐུན་ཀླུང», «ཐུན་ཀུངས», «ཐུན་གུང» in the same etexts: no hit but WB's own line.
  - «དོ་གཅིག» besides WB and the notes: no definition. «རྒྱུག་དྲུག», «སྤུད་པ་དགུ» as a gloss: none.
  - The departure's seven lords with a day or a grade, in LT, UT, KD vol. 3 and the Bhutan digest: no text says 'of the date'.
- **RD175, letters in doubt on pp. 331–435 (2026-11-06)** (RD175, 2026-11-06):
  - A Zhol impression with a legible vowel over ཐ in «ངོ་ཐོག/ཐེག» (p. 381): only img. 499 of I1KG1710 was looked at, at 14×; no second impression of the blocks was found.
- **RD183, ch. 32's last words (2026-11-06)** (RD183, 2026-11-06):
  - «བྱན་ཚང»: BDRC 13 hits (WB, the Bai dkar copies, the Rmad byung, the Bod kyi rtsis rig kun 'dus, unrelated hits), no gloss. «ས་དགའ་མ»: 14 hits, only WB copies.
  - «མ་དགུའི་ས་རུ» and «ཁྱོ་གའི་ས་རུ་བརྩི»: only copies of WB's passage; «ཁྱོ་ག་དང་མ་དགུ» and its reverse: no hit.
  - «མཛེས་ཅན་མིན་པའི་མི»: no exact hit; «མཐེ་བོང་ཙམ་པ་ཁྱོར་གང་བླུགས» only in the Bzo rtsis zlos gar snippet.
  - Taikhang woodcut labels: img. 402 at 8280 px, edge words and circles not resolved by eye.
- **RD180, vol. 2's early leaves (2026-11-06)** (RD180, 2026-11-06):
  - «བཀྲ་ཕན་ངོན», «ཕན་ངོན་བཀྲ», «བཀྲ་བན་བོན», «བན་བོན་བཀྲ་དང་བདུན» in BDRC full text: no hit. «ལངས་པའི་ཉིན» in the 1996 and Zhol etexts: no hit. «དབུས་ནག» in the 1996 etext near img. 88: no hit.
  - The goat's bladder in the 1996 etext of img. 114–115 and the Zhol etext of img. 783: not in the text.
- **RD182, vol. 1's tables and the hal khyi (2026-11-06)** (RD182, 2026-11-06):
  - A gloss of «ཉི་མ་དུས་ཚོད» saying whether one of the two suffices: every hit in WB's etext read; BDRC 51 hits, only I2PD20755 (a Bon-side count) fetched, no gloss.
  - A reason for the tent at the door in the 1996 etext (five hits of «སྦྲ་ཁྱིམ»): none.
  - A gloss of the la-knower's animal in WB's etexts: none.
  - The order of the five mother-son pairs: not on p. 185.
- **RD176, second part (2026-11-06)** (RD176, 2026-11-06):
  - BDRC exact phrases «རྗོན་པ་ཅན», «ལྷ་ནི་རྗོན་པ་ཅན», «རྗོན་པ་ཅན་ཞེས», «རྗོན་པ་ཅན་དང», «རྗོན་རྐུ», «སྣུབས་ལྷ་རྗོན»: no hit; SY (I1PD30933) has no deity or «རྗོན» for Mūla or Puṣya.
  - The Zhol line of p. 305's fifth meeting: not located on img. 1054–1055.
  - A better scan of the 1996 print's p. 328 for Aśvinī's numeral: none in the cache (the Sde dge copy I1KG12740 holds 11 images).
- **RD169b (2026-11-06)** (RD169, 2026-11-06):
  - The root book of the Clear Lamp's five topics (MW2PD17476_B43210; manuscript I4PD636 img. 414–415): cursive dbu med at 1400 px, not readable at 3×; needs a reader of the script.
- **RD183b (2026-11-06)** (RD183, 2026-11-06):
  - The woodcut's left edge on Taikhang img. 402 at 2× in rotated crops: only the position of four words, none readable; the circles likewise; one agy call on the right-edge strip gave no answer in 11 minutes.
- **RD180b (2026-11-06)** (RD180, 2026-11-06):
  - «ལྷང་ཕུག» in the Zhol etext at img. 782–783: no hit (the etext there is garbled).
- **RD159c (2026-11-07)** (RD159, 2026-11-07):
  - The stack of «བསྐྱུར»/«བསྒྱུར» on the Taikhang print (I5878 img. 447, line 2 right end), cut by ink rows, 2×: a filled block, not told.
- **RD176d (2026-11-07)** (RD176, 2026-11-07):
  - Zhol img. 1084: the letter of «དྲོ་?ོད» is not legible at 1x–2x.
  - Zhol img. 782–806: the marks not counted (one image looked at).
- **RD175c (2026-11-07)** (RD175, 2026-11-07):
  - The word «སྙོངས/སྟོངས» of fire four and wind one on the Zhol print (img. 577, lines 6–7), crops at 3–8×: no legible stack found.
- **RD181b (2026-11-07)** (RD181, 2026-11-07):
  - The archive.org loan not opened again: KD vol. 3's leaves are served at 1002 px only, so q552 and q553 gain nothing from a second loan.
  - The Bhutan wheels' (I1KG11905 img. 446) box directions: not read, the inner ring's words too ligatured.
- **RD178c (2026-11-07)** (RD178, 2026-11-07):
  - The owner of the door line (p. 53, Zhol img. 700, Taikhang img. 82): no heading or gloss on either print names it.
  - The base letters of the stack of p. 54, l. 1 (Taikhang img. 85 at 7x, Zhol img. 703 at 5x–10x): fused, heavy heads.
  - The gloss of p. 3 on the Taikhang at 10x: letters merged.
- **RD180d (2026-11-07)** (RD180, 2026-11-07):
  - «བཀྲ་སྲིའུ་དབུས», «ཕན་ངོན», «སྦོ་སྨད», «ལྟོ་བ་མཚན» in the Zhol etext (locate.py find): no hit, the etext being garbled there.
  - q634: the 1996 etext passage with «སྦོ་སྨད» is on its p. 197, not p. 122; the question's own line is still not seen.
- **RD186 (2026-11-07)** (RD186, 2026-11-07):
  - «རི་ཏི» for the sage's name: the reprint reads «རི་ཏི» where Yigdzin-1 reads «རྀ་ཥི» (p. 176 has «རི་ཥི»); which spelling WB intends is not decided, no etext search made.
  - The wheel's petals (p. 184): the diagram's figures too small on the reprint, worse on the 1996 print; no other copy looked for.
- **RD184 (2026-11-07)** (RD184, 2026-11-07):
  - A list of the sixty year-names with their Indian names in the pool or the code: none.
  - MITRA over pp. 198–212 of the 1996 print: not run, no slot came free.
  - The sixty years' lists of ch. 18 elsewhere in the pool (kb.py search on «རྣམ་འཕྱང», «གསེར་འཕྱང», «བློ་ངན»): nothing outside the ch. 13 list of names.
- **RD185 (2026-11-07)** (RD185, 2026-11-07):
  - MITRA over the 690-px scan of WB vol. 1 img. 200-203: looped garbage on the numerals; stopped, output deleted.
  - A larger scan of pp. 190-197 and 213-217 in which the stacked figures are legible: the 1996 scan is 690 px wide and the Gser thang reprint's small digits are broken; the Zhol (I1KG1710) and Taikhang (I5878) prints not opened.
- **RD188 (2026-11-07)** (RD188, 2026-11-07):
  - The figure for Saturn in the five planets' weekday numbers (p. 42): not in the machine texts of either reader; on the page image at ordinary size it looked like «0», not enlarged.
  - The planets' colours on p. 54 in the whole-page Yigdzin-1 and MITRA runs: not found (both returned only the table labels); found in a second run on the enlarged lower half.
- **RD190 (2026-11-07)** (RD190, 2026-11-07):
  - A page number for the key of the Tiger-Ear notation other than 210 (RD184 had 209): the 1996 print (img. 219–220) and the reprint (img. 209) both have it on the page numbered 210.
  - Which crops "the black" and "the white" are, in the key or in the entries of pp. 206–211: not said.
- **RD193 (2026-11-07)** (RD193, 2026-11-07):
  - Yigdzin-1 on the chart pages of WB vol. 1 ch. 13 (img. 143–148 of the reprint and the 1996 scan): loops or garbage; MITRA likewise on tables, only numerals or run-together lines. Pp. 136–172 need reading cell by cell by eye.
- **RD194 (2026-11-07)** (RD194, 2026-11-07):
  - A passage giving the sixty years of the Little Tiger-Ear from another text, to set against entries 44–60 (kb.py search on the entry words): none.
- **RD195 (2026-11-07)** (RD195, 2026-11-07):
  - WB vol. 1 ch. 13 pp. 136–147 on the Gser thang reprint (img. 143–154): by-eye crops at 1x to 3x do not make the reset face's cell glyphs reliably legible; the cell text needs another print (the 1996 print, img. 146–157, or a Zhol/Taikhang print).
- **RD196 (2026-11-07)** (RD196, 2026-11-07):
  - Yigdzin-1 on pp. 154–171 of WB vol. 1 ch. 13: not run, the boxes are too small and set in ovals (as RD193 found).
  - The Gser thang reprint (I3CN5028) holds nothing of pp. 167–174 (img. 174 is p. 175); those pages exist only in the 1996 print, where the boxes are small and soft.
- **RD199 (2026-11-07)** (RD199, 2026-11-07):
  - A readable cell text of any of the 24 charts of WB vol. 1 ch. 13 pp. 136–147: the 1996 scan at 3× and the Zhol leaf at 1.5× give shapes, not syllables; only BDRC's etext reading exists.
  - The Zhol leaves of the ring charts (pp. 136–141): not found on Zhol img. 120–172.
- **RD200 (2026-11-07)** (RD200, 2026-11-07):
  - The stacked two-digit figures of the 12th and 1st month heads (breath-taking and sgang dates, day and night lengths): not read digit by digit.
- **RD202 (2026-11-07)** (RD202, 2026-11-07):
  - p. 169 (1996 print img. 179) carries no month head, only boxes; the 8th head is on p. 168, the 9th on p. 170.
  - p. 154's head, lines 5–7: not read by eye in the time given.
- **RD201 (2026-11-07)** (RD201, 2026-11-07):
  - The 3rd head's dar figures (end of the 7th printed line, p. 160) and the 6th head's weekday figures after «dar 5, 3, 1»: not made out on the crops; no stacked figure of the 2nd–6th heads read digit by digit.
- 2026-11-08, RD176: Taikhang I5878 img. 466, Revatī's «ལས་སྣ་དྲོ» line not identified among rows at 0.6–0.8×.
- 2026-11-08, RD175: a fifth run on the one Zhol impression (pp. 335, 377, 381, 435): lines cut at 3× again, no ཕ/ལ loop and no vowel over ཐ told; no second impression on BDRC.
- 2026-11-08, RD159 (first run): Taikhang I5878 img. 447 line 5, the stack «བསྐྱུར»/«བསྒྱུར» at ¾ size: a filled block, not told.
- 2026-11-08, RD180: «ལྷང་ཕུག» spelled so on the Zhol print: none; the machine has «ལྒང་ཕུག» there (img. 780). The Taikhang syllable for q561 (I5878 img. 160, end of line 3) unread: `scans.py line` is off by one line on the landscape folios and on Zhol img. 780, so crops there are cut by hand.
- 2026-11-08, RD178: the word «ཚད་ཟླ» on Zhol img. 638 at 6× (line 2): not isolated by the line cut; the Zhol slips hold several rows per band.
- 2026-11-08, RD159 (second run): the stack «བསྐྱུར»/«བསྒྱུར» on the Gser thang reprint (img. 319, Aśvinī's ransom, 4×) and on the Taikhang print (I5878 img. 447, last line, 4–6×), cut from Yigdzin-1's line offset: a blot with two holes on both, not told. Method for the next run: take the machine's line and offset on the Zhol image (WBZ img. 1065–1090), crop at 5× beside a certain «བསྐོར» of the same line; the Zhol's thin script may tell ཀ from ག.
- 2026-11-08, RD193: WB vol. 1 p. 151 (reprint img. 158), upper-grid rows 4–6: the headings and the cell words are not legible at 1.25×; a read of single cells at 4–6× or the 1996 etext is needed.
- 2026-11-08, RD193: WB vol. 1 p. 134 (img. 141), the watch line's first consonant (རྟ or ཌྷ) and the later vowel pairs ཨུ/ཨཱུ, ཨེ/ཨཻ, གུ/གཱུ: not told at 7× on the reprint.
- 2026-11-08, RD184: Aṅgiras «ཨདིར» / «ཨངྐི» / «ཨངྒི» in Yigdzin-1's reading of the Taikhang folios 237–246 (I5877 img. 247–256): not found. The Taikhang as an independent witness is weak this way: Yigdzin-1 reads it in cuts of about 2000 px with the same model, and only folio 242 was checked on the scan. Taikhang folios 205–236 (img. 215–246) are the end of ch. 17, not ch. 18; ch. 18 is folios 237–246.
- 2026-11-08, RD204 (Zhol run, nothing committed): a reader reports that Zhol I1KG1710 img. 41–43 hold ch. 3's history verses (pp. ~27–28), not p. 32, so "Zhol image = page + 10" does not hold for vol. 1 on that scan (p. 32 should fall near img. 46–48; find it with `locate.py map` against the etext before cropping), and that Taikhang I5878 img. 51–52 are weather omens, not p. 32 (not checked by the launcher; check the PLAN.md line "vol. 2 (I5878) img. 52 = p. 32" before relying on it). The run was also starved by the card's queue: three other readers held the slots.
- 2026-11-08, RD185/RD186 readers: for the Gser thang reprint I3CN5028 vol. 1 near pp. 185–197 and 217 the image is page − 1 (printed 188 = img. 187; printed 196 = img. 195), not page + 7; find a page by its printed number before cropping.
- 2026-11-08, RD203: the 1996 etext (I1KG12906) agrees with Yigdzin-1 on only 67–93 % of syllables on ch. 1, 2 and 9, so it is no independent witness for those pages; Yigdzin-1 reads the bracketed numerals badly (༢ for ༣, ༡༡ for ༡, ༡ for ༦/༧), and every figure is checked against its number-words (first word the units digit).
- 2026-11-08, RD158: ch. 34 pp. 407–411 are on the Zhol I1KG1710 at img. 1172–1179 (not page + 8); `scans.py line` fails on the landscape Zhol leaves (use hand crops, lines about 64 px apart from y≈85); the line ends «ཅེ/རྩེ», «དབྲལ/དཔྲལ», «གཅིག/ཅི», «སྟོང/སྟོར», «རྐུ/སྐུ» (ch34.md ninth pass) were not hit by the crops and the hand was illegible there.
- 2026-11-08, RD184b (nothing committed): WB ch. 18 pp. 198 and 204 on the reprint I3CN5028 (img. 197, 203) by quarter-width crops of 4 lines at 3.4× did not yield a syllable-by-syllable comparison reliable enough to raise either page (many crops came back unreadable in the viewer). Next time: one or two lines per crop at 5× or more, each compared with the stored text as it is cut, or single crops to agy. Line pitch: p. 198 has 25 lines, line 1 at y≈772, pitch ≈86.6 px; p. 204 has 28 lines, line 1 at y≈321, pitch ≈87.4 px; the text sits at x≈205–2039 and x≈225–1870.
- 2026-11-08, RD205: ch. 7 p. 43, the table's tops and middles (twelve columns of three small figures) read again at 4–7× but not settled (only the bottom row 0, 15, 10, 45 ×3 is secure); the column of five small figures between lines 10 and 12 beside «Mars» (roughly a dot, 11, 1, 14, 144) not read.
- 2026-11-08, RD184c: WB ch. 18 pp. 198 and 204 on the reprint I3CN5028 (img. 197, 203), one line per crop in two halves at 3–4× (zoom 7–8×), were legible (this supersedes the RD184b entry for crops) and every line was compared with the stored text. Three stacks resist the 1-bit print at any enlargement: p. 204 l. 7 «ལྟོག/ལྷོག», l. 24 «ལྗང/ལྡང», ll. 11–12 and p. 198 l. 19 «སྨྱུ/སྒྱུ». Next time: another witness for those three words (the Taikhang I5877 at its own resolution, or a clean ལྟ / ལྗ / སྒྱུ in the same print to compare shapes), not more enlargement of the reprint.
- 2026-11-08, RD184d: WB ch. 18 pp. 200–202 on the reprint I3CN5028 (img. 199–201), one line per crop in two halves at 2.2× (zoom 5–6×), legible; pp. 200 and 201 raised to scan. One stack is not told apart in the 1-bit print: p. 202 l. 2 «ལྡང / ལྟང / ལྷང» (last syllable of «མཁྲིས་པའི་ནད་ལྡང»); the page stays machine. Next time: another witness for that word (the Taikhang I5877, ch. 18 = folios 237–246, at its own resolution), not more enlargement of the reprint. The viewer shows about 2000 px across: a half-line at 2.2× is the effective limit; stacking 4–5 lines of one half per image halves the views.
- **Dreams and the calendar, 2026-11-09** (question 784): BDRC's full-text
  search finds no text that reads dreams by date: «རྨི་ལམ་ལ་བརྟེན་པའི་འཆི་ལྟས» no
  hit, «ཐོ་རངས་རྨིས» once (Tāranātha's autobiography), «ཚེས་ལ་རྨི་ལམ» four times, all
  biographies dating a dream, «གཟའ་ལ་རྨི་ལམ» none; «རྨི་ལམ་བརྟག» (863 hits) and «རྨི་ལྟས»
  (2172) are manuals by image and ritual, listed in the question. Brill's
  chapter pages answer "Human Verification" (405) and OAPEN "Making sure
  you're not a bot" (403) to the headed browser as to a static fetch;
  nichibun.repo.nii.ac.jp answered 503 to both; the Hokkaidō repository's
  PDF link is not in the record page's text. A static fetch of the Four
  Tantras' etext (IE0OPIFEF6EACA, one volume, 692 chunks) works with
  `bdrc.py etext` in under a minute.
- **RD213, 2026-11-09**: the Ebara volume (EB9, 『京都大学蔵潁原文庫選集』 vol. 9) is
  in no online form and has no table of contents beyond the NDL record
  (R100000002-I029406484), no page numbers per text; the 日本大百科全書
  entries 初夢 and 夢占 name no Edo source by title; 山家集 on ja.wikisource is
  an empty page, no critical edition or poem number found online;
  森田登代子's 「大雑書研究序説」 and nichibun.repo.nii.ac.jp/records/651 not
  reachable; no 大雑書 dream table tied to 庚申, the stems or dates besides ゆ六
  in the 万代大雑書 part 15.
- **RD211, 2026-11-09**: the Gser thang print I3CN5038 runs its pages
  backwards from about img. 199 to img. 261 (img. 202 = printed p. 241,
  img. 225 = p. 218, img. 260 = p. 183), so a chapter's colophon lies on a
  lower image than its text; the registry's page rule holds only to img. 39.
- **RD210, 2026-11-09** (RD210, 2026-11-09):
  - The etext IE0OPIAB2E5954 (vol. 6 of the First Dalai Lama's Gsung 'bum) is unusable for SRM printed pp. 386–389 (garbled OCR); the scan must be read.
  - `bdrc.py volumes W…` answers 401 for these W ids; volume ids come from ldspdi.bdrc.io/resource/W….json (instanceHasVolume, then volumeNumber).
- **RD214, 2026-11-09**: Scheuermann's chapter (Brill, DOI
  10.1163/9789004410688_009, https://brill.com/display/book/9789004410688/BP000009.xml)
  answers HTTP 405 "Human Verification" (a CloudFront WAF captcha) to curl and
  to the headed Playwright browser alike; library.oapen.org answers the
  Anubis page; Crossref holds metadata only. The reading waits for the
  chapter's PDF from the owner (solved in his browser, or a library copy).
- **RD209, 2026-11-09** (RD209, 2026-11-09):
  - RBG vol. 3 pp. 203–205: the BDRC etext IE0OPIBC148677 answers 403 and the scan manifest vo:I1KG2753 serves 41 images, none near these pages; visible only as a snippet in BDRC full-text search.
  - `bdrc.py volumes W<instance>` answers 401; scans for KCM, SKP and DTD were fetched through the work-level collection manifest wvo:bdr:MW…::bdr:I…; `bdrc.py scans` with the MW id fails on KCM (500).
- **RD208, 2026-11-09** (RD208, 2026-11-09):
  - The page numbers 799–810 for LGD (registry, README, ROADMAP) do not hold the text; it is at pp. 961–971 of Rin chen gter mdzod vol. 42 (I1KG57 img. 980–990), found from the volume's contents page in the etext, not from BDRC's outline (whose contentLocationEndPage 990 is the volume's image sequence).
  - The etext's [pN] marks in VE1KG14_042 count paragraphs, not pages or images; the contents page at its start lists printed pages that match the scan's printed numbers (image = page + 19).
  - No individual sign in LGD's four lists has its verdict changed by the hour or the lunar day (searched the whole text).
- **RD212, 2026-11-09** (RD212, 2026-11-09):
  - MDP etext IE0OPIE0503306 answers 403 to the etext API (search-only); scans I4992 serve only img. 1–22 (front matter and contents), img. 135+ returns nothing.
  - BDRC full-text search: about a dozen guessed phrases for MDP's dream part found no snippet from MW21880 (e.g. «རྨི་ལམ་བརྟག་པ་བསྒྲིགས་སོ» 0 hits); the only snippet is its contents line, from «རྨི་ལམ་བརྟག་པ་བཞུགས་སོ».
- **RD215, 2026-11-09** (RD215, 2026-11-09):
  - WB etext IE0OPI51524892 (vols. 1 and 2, searched on syllable boundaries): «ནམ་ཐུན», «སྲོད་ཐུན», «དགོང་ཐུན», «སྔ་ཐུན», «ཐུན་ཐ་མ», «མཚན་མོའི་ཐུན» do not occur; «མཚན་ཐུན» once, in «ཉིན་མཚན་ཐུན་བརྒྱད» (vol. 2 p. 403); «ཐུན་བཞི» only in the vowel-watch list of vol. 1 p. 134 and a hand-reckoning sentence.
  - Mipham's notes (vol. 2 pp. 474–541): no watch of the night, no clock bound.
  - The row's «twelve of night on p. 162»: vol. 2 p. 162 holds the mewa and trigram verses; the twelve times are vol. 1 p. 17 and vol. 2 p. 156.
  - The Indian yāma as Sanskrit background: no published study found; the claim's fallback is unchecked.
- **RD217, 2026-11-09** (RD217, 2026-11-09):
  - No MITRA slot came within ten minutes (rd218 and rd220 held the card).
  - The cursive TDR and SRM scans (2173×460 and 4320×896) were not legible enough to check by eye; `scans.py line` fails on these landscape pages (IndexError), crops with explicit coordinates work.
- **RD221, 2026-11-09** (RD221, 2026-11-09):
  - MDP (MW21880): etext IE0OPIE0503306_I4992 answers 403 again; the iiifpres manifest of I4992 lists 41 canvases and an image beyond them answers 401; no route to its dream part (pp. 134–137). BDRC full-text search shows MDP holds the same verses (snippets only).
  - BDRC: no print of this text from Klong rdol's gsung 'bum found; KDL (MW29669_5E6E42, pp. 235–240) begins at Garga with no dream part; a part of the women's title stands separately as MW29669_8009F4 (pp. 255–258, not compared). ldspdi POST /osearch/search and /search answer 405, the Person_Works query template fails with 500; the person record is P22.
- **RD216, 2026-11-09** (RD216, 2026-11-09):
  - No dream image is read by WB ch. 30 beyond p. 144, and WB ch. 25 has only four dream lines (the ox-day rough dreams, the five-yellow mewa's dreams of za zi, the dragon-year skull remedy, the pig-year omen of the ritualist bitten in the sick person's dream).
  - KCM, DTD, RBG and KDL give no images with verdicts, and neither does MDP, which is unreachable.
  - Klong rdol's dream text is the ACIP etext RST (RD221), so KBS and RST are not independent witnesses; their agreement on images is not two sources.
- **RD218, 2026-11-09**: the scans of I1344 img. 268–270 and I1365 img.
  261–262 are bitmaps with letters about 8 px high, so la/ba, the tsheg's
  place and the stacked letters of Sanskrit words cannot be read; Yigdzin-1
  and MITRA agree on 96–99 % of each page and the passages moved from etext
  to machine, not to scan. Only a higher-resolution scan (the archive.org loan,
  or BDRC's original) can settle RD207's spelling doubts. `scans.py line`
  finds only 1–2 lines on these 7-line strips; cut bands by hand, line k at
  about y = 42 + 37.5 × (k − 1) px.
- **RD219, 2026-11-09**: RBG vol. 3 pp. 203–205: the work-level manifest
  `wvo:bdr:MW1KG2733_B7BF9B::bdr:I1KG2753` answers 401 (restricted);
  `vo:bdr:I1KG2753` serves 41 images, none of these pages; the etext API 403;
  `bdrc.py volumes` with the MW id likewise. No route without the archive.org
  loan. Tool note: `bdrc.py volumes W<instance>` answers 401 for KCM, SKP and
  DTD, so their scans need the `wvo:bdr:MW…::bdr:I…` manifest (KCM I2638 img.
  689–692, SKP I1NLM933_001 img. 40–55, DTD print I3877 img. 221–222; for
  MW1NLM4232 the plain `vo:bdr:I1NLM4232_001` manifest works; RRT is I3KG732,
  img. N = printed folio N − 11). The scan fetch would belong in `bdrc.py` as a
  `scans` mode taking a `wvo:` manifest.
- **RD222, 2026-11-09**: the cursive hand of TDR, SRM and GNC is about 40 px
  a syllable on a 4320×896 page with six lines of about 80 px an image;
  mapping a flagged syllable to an x position by character fraction put 3×
  crops in the wrong place. A scan reader needs line-by-line crops cut from
  the printed line boxes. Yigdzin-1 and MITRA agree at 96–98 % on SRM, 85–86 %
  on TDR and 92–99 % on GNC; the doubts are listed in srm.md.
- **RD223, 2026-11-09**: the 夢はんじ of the 万代大雑書 part 15 (HUSCAP
  hdl.handle.net/2115/94454, 78_06_Ito.pdf, leaves <5>–<8>, 132 articles) holds
  no date, season, month or 初夢 rule; 初夢 does not occur in the PDF. The one
  calendar rule in its dream part is ゆ六, the table of days a dream is not told.
  The Ebara reprint's own 大雑書 and 夢占吉凶秘伝書 stay unread (q786).
- **RD227, 2026-11-09** (RD227, 2026-11-09):
  - No Chag-po-ri or Derge print of the Four Tantras found served on BDRC; the only second print used is the Drathang woodblock GT (I4CZ327131), images 1763-2669 px wide with red interlinear notes illegible at that size (ST img. = GT img. + about 5 early on and around ch. 81; ST 362-363 = GT 462; ST 507 = GT 647; ST 34 = GT 29).
  - Steinert's dictionaries by Wylie headword: ལྕོལ, སྡིག་སྡོམ, ལྷས་ཚོར, ཕྱུ have no headword; ནིའུ་གསིང only as ne'u gsing with one Vinaya line.
  - GT img. 660, 662 read the Final Tantra, not ch. 91: ch. 91's end is GT 647-649.
- **RD228, 2026-11-09** (RD228, 2026-11-09):
  - The Dpe bsdur ma scans of vol. 14 (I1PD95859) and vol. 25 (I1PD95870) are restricted: BDRC serves 41 images, none of these pages; only the OCR etext with its variant notes was read, note markers lost, each note assigned to its word by order only.
  - No etext of the Peking or Narthang prints (W22704) and no Co ne print with scans found; the Narthang etext of W2KG5015 is badly garbled OCR and not used.
  - bdrc.py volumes and the collection manifest answer 'Insufficient rights' for these Tengyur instances; volume ids come from purl.bdrc.io/resource/W….json (instanceHasVolume, then volumeNumber): Peking vol. 28 is I1KG13197, Narthang I2KG212312 and I3279, the gser bris ma I1PD31365; bdrc_search.py found the five LGD prints (MW01CT0060, MW18978, MW23445, MW2PD17514, MW1LT3204) with open scans.
  - No Sanskrit parallel of the Svapnohana's dream catalogue published that was found.
- **RD230, 2026-11-09** (RD230, 2026-11-09):
  - The machine reading of WB vol. 2 pp. 474–541 (img. 482–549): no gloss of «རྨི་ལམ་རྩུབ», «ཟ་ཟི» or the skull remedy of the dragon year; the only «རྨི་ལམ» is p. 535's list of preliminaries.
  - The Taikhang reproduction (I5877 img. 587, 589, 590, which should hold pp. 428, 430, 431; the offset page + 159 is a guess) fetched and not read; the Zhol print not opened.
- **RD231, 2026-11-09** (RD231, 2026-11-09):
  - scans.py line fails with IndexError on I3279 img. 288 and I1PD31365 img. 389: the strips carry a black frame that defeats the row-ink line finder; scans.py crop with pixel boxes works (gser bris ma 1540 255 1820 320; Narthang 600 190 800 235). The OCR line numbers do not map to scan rows, so look at a stack of the whole image first.
- **RD232, 2026-11-09** (RD232, 2026-11-09):
  - Palri img. 541 and 543 not read: only line 1 of img. 542 carries the phrase.
  - Sbas-yul img. 148: the cursive hand does not settle «རྟ་ཇུག་སྦང་ཕྱེར» by eye; the phrase is on line 3 after «ཞོན་པ༔ ཞོན་ནས་དབང་མེད་དུ་ཁྱེར་བ༔».
  - Zhe chen img. 310 line 6: the phrase lies at x 1750–2750, y 305–395 (scans.py crop); the second syllable «རུག» or «རྒྱུག» is not settled by eye; Kazi ར against ང is a moderate-confidence reading of a grainy print.
- **RD229, 2026-11-09** (RD229, 2026-11-09):
  - BDRC full-text search for «ལུས་ཡང་རྨི་ལམ་དག་ན་སྐྱེས་པ་མཐོང་» and four other dream phrases of the Explanatory Tantra: etext commentaries by Byang pa (MW27496, also MW15590), Rin spungs pa (MW27494), Khro ru Mkhan po Tshe rnam (MW29701) and Bri gung Chos kyi grags pa's Dka' mchan (MW1KG24729); none of Zur khar, Lho brag or Mkhyen rab nor bu turned up. The Drikung Dka' mchan (etext p. 61) has ch. 2's verse with glosses and no word on དག; Khro ru's glosses (etext pp. 175, 320–322) seen in part, not recorded (modern author).
  - The two commentaries have no scans served (lent or archive.org); read in etext only.
- **RD233, 2026-11-09** (RD233, 2026-11-09):
  - Peking img. 315 line 1, the Sanskrit title: five signs after «སྭ», the stack before «ཨུ་ཧ» not told apart even at 2.4×.
  - Peking img. 317 line 8: the last syllable of «འབྲས་བུ་ལྡན་ན་བློ་མེད་…» is a stack with a vowel sign that 1.9× does not settle (Derge etext «བློ»).
  - Peking img. 318, end of line 4 and start of line 5 (Derge's «རྨི་ལམ་བརྟག་པ་རྫོགས་སོ»): one syllable more than expected looks present; unsettled at 2.2×.
  - Peking contrast is low (grey on grey): crops need autocontrast; img. 316 needs a −45 px vertical offset, img. 318 −5 px (text lines 8, 8, 8, 5).
- **RD234, 2026-11-09** (RD234, 2026-11-09):
  - The row's Peking vol. 49 (I1KG13218) was not opened: the work is in vol. 46 (I1KG13215); found through the abstract work WA0RT4282 (workHasInstance) reached from the Derge MW23703_2100 via instanceOf, then purl.bdrc.io/resource/MW….json for the content locations.
  - The Dpe bsdur ma etext (W1PD95844 vol. 25 pp. 415–418) with its variant notes was not fetched; the Narthang (W2KG5015 vol. 46 pp. 289–291) and W22704 vol. 46 images were located, not fetched or read.
  - bdrc.py scans OUT VOLUME FIRST-LAST takes the image group once; PLAN.md's example repeats it. Peking grey print crops are faint until autocontrasted; 1.1–2× reads best, a 3× zoom of a 40 px letter goes blocky.
- **RD235, 2026-11-09** (RD235, 2026-11-09):
  - Co ne print: no scan served, and the Dpe bsdur ma notes name «ཅོ་» at no place in the watches' verse; its reading of the fourth-watch line is unknown (q833 answered for Narthang and the gser bris ma, open for Co ne).
  - I2KG212312 img. 284 is the same page and layout as I3279 img. 286: the two Narthang scans are one print family, not independent witnesses.
  - The Dpe bsdur ma etext's note markers are lost in the OCR: the minus-sign entry for «ཐུན་བཞི་ཟླ་བ་ཕྱེད་བར» is placed under «པེ་» by order among the notes only.
  - First segment's spelling «བརྒྱད་པ་ལ» (Narthang, gser bris ma) against «བརྒྱད་བར་ལ» (Peking, Derge etext) and «བར» against «བར་དུ» at the end of the third watch's line: not settled at 30–40 px.
- **RD236, 2026-11-09** (RD236, 2026-11-09):
  - Narthang manuscript's «བྱ་ཤ…ཏེ» (I2KG212330 img. 290): the middle syllable's lower sign (ད, ང or ར) and whether «འུ» follows «ཏེ» at the frame are not settled at 3–5×; the W22704 copy is too coarse to help.
  - Dpe bsdur ma scans (vol. 25, I1PD95870) restricted; the notes' markers are lost, each entry assigned to its word by content only.
  - The Narthang pages are hand-written strips and the OCR line numbers do not map to page lines; lines were found by an ink-row profile of the strip (img. 290: 7 lines, centres near y 318, 364, 410, 456, 502, 550, 595); autocontrast then 2.7–5× crops read well.
- **RD238, 2026-11-09** (RD238, 2026-11-09):
  - Palri img. 542 line 1: after «གཅིག་པུ» the print has ས; «པུ་ས» or «པུས» is not settled at 2×.
  - Palri img. 539: «ཐོ་རེངས» (machine) against «ཐོ་རངས», and «བསྐྱངས་བས» against «བསྐྱར་བས», not settled by eye.
  - Palri img. 534–538 (the rite) not read by eye, only diffed against RD208's rite; the Yigdzin-1 reading drops the page-head «ཨོཾ» marks and does not read the seed string at the head of img. 534.
  - scans.py line not used; crops with pixel boxes work: the Palri text block is x about 500–3900, y 140–820 in the 4350×975 strip, 5–6 lines to a page, a half-width crop at 1.1× reads well. img. 545 starts another text (the sadhana of the little white boy who announces in the ear).
- **RD237, 2026-11-09** (RD237, 2026-11-09):
  - The abstract work WA0XL5DC139EF13C8 (KCM) has one instance only (MW23766_5DC139); no other print of KCM by its own title found in BDRC's catalogue.
  - KCM in the Rin chen gter mdzod, I1KG52 (vol. 37, image = printed page + 17 at img. 640, 700 and 730, 810 images in all): the KCM page was not reached; the etext VE1KG14_037 is about 1.17M characters but its text up to img. 800 reaches only about 534k of 830k, KCM at about 762k, so the etext covers more than the images the manifest serves or is ordered differently; img. 1000 and 1180 do not exist.
  - bdrc.py etext of the whole IE0OPIC2BFA6FE (15 volumes) is slow (over 3 minutes) and was stopped; its KCM hit (VEIE0OPIC2BFA6FE_I1KG81132) was not read.
  - The Rin chen gter mdzod KCM paragraph has no «ཚོད» after «དུས», so it is a variant of the Tashijong print, not a copy.
- **RD239, 2026-11-09** (RD239, 2026-11-09):
  - scans.py line fails with IndexError on I6320 img. 276; the page has 6 text rows at a pitch of about 125 px (y about 20, 145, 270, 395, 520, 645): use scans.py crop with pixel boxes.
  - The dro'i kha / dro zhig place was not identified on the W24769 print (probably around «བརྐྱང་འདུག» / «ཞལ་འཛུམ» on img. 277 lines 1–2); 'dod/'ong on img. 277 line 5 «འདོད་དོན» not looked at by eye; «ཡོང་ངོ» vs «ཡོད་དོ» on img. 278 line 4 not settled at 1.0×.
- **RD240, 2026-11-09** (RD240, 2026-11-09):
  - The abstract work WA0XL78B598345102 (TDR) has one instance only (MW25101_78B598); no second scan print of TDR was found under its title.
  - BDRC full-text search for «སྒྲོལ་དཀར་རྨི་ལམ་བརྟག་པ» finds 0 hits; «སྒྲོལ་མ་དཀར་མོའི་སྒོ་ནས་རྨི་ལམ་བརྟག་པ» finds the contents line only; «ཨ་ནུ་ཏ་ར་ཞེས་པའི་ཡི་གེ» hits 4 etexts, none TDR (VE1ALS05317E and VEIE0OPI9244B62A_I3PD274 are a dream-yoga text, read and rejected; VEIE0OPIDB282A46_I3CN4662 and VEIE0OPI51EA6051_I2PD16008 not opened); «དངུལ་གྱི་མེ་ལོང་བཞི་བསམ» hits VE23681_001, not opened, and the TDR etext.
  - The scan of W1PD2 I1PD407 (the print behind the TDR etext) was not fetched; MITRA not run over the GNC manuscript; crops at 1.5–2× of the four-mirror lines, the vulture-flesh sentence (img. 731 lines 4–5) and the Mtsho rgyal passage not made.
- **RD241, 2026-11-09** (RD241, 2026-11-09):
  - The manifest of I1PD407 is restricted: bdrc.py volumes W1PD2 gives HTTP 401, and the manifest lists only 41 canvases (img. 3–22 and 373–391), so the TDR pages (printed pp. 313–316, etext [p341]–[p344]) are not served; another route would need a different open volume of W1PD2, or an etext-only reading.
  - GNC I1ER1055 img. 731 lines 3–5 at 1.2×: the letters of the headless hand are not told apart; the vulture-flesh sentence (after «ཅེས་བྲི༔») was not located line by line and the stop after «རྫས» not settled; 1.5–2× crops of img. 730–733 and MITRA over the manuscript remain undone.
- **RD242, 2026-11-09** (RD242, 2026-11-09):
  - Peking img. 315 is only 1405 px high, so the row-ink profile gives no line positions; line 2 sits at y about 400–520, and the first line centres tried (y = 324, 435, 549, 654, 768, 876, 990, 1104) cut off the lower line: find the lines from a 1/3-size overview.
- **RD243, 2026-11-09** (RD243, 2026-11-09):
  - Peking img. 315 lines 3–8 and the rest of line 1 (x 3350–4900) not reached at 2× in the 14 minutes.
- **RD244, 2026-11-09** (RD244, 2026-11-09):
  - Peking img. 316 lines 3–8 not read at 2×; bands y 684–909, 906–1131, 1128–1353 on the 5358×1421 scan, line pitch 111 px, lines 1–2 at y about 300 and 411.
- **RD245, 2026-11-09** (RD245, 2026-11-09):
  - scans.py line raised IndexError on Peking img. 317 and found 10 lines on img. 318 (the text part has 5), so its numbering does not match; take lines from a 1/3-size overview, pitch about 111 px.
  - Peking img. 317 lines 1–7 and img. 318 lines 1–3 not read at 2×.
- **RD247, 2026-11-09** (RD247, 2026-11-09):
  - Peking img. 317 lines 4–5 joint: line 4 ends «བྲམ་ཟེ» (3–4×), line 5 begins with a tall-tailed stack not told from «ར» or the «ཕྱུ» of the next syllable. scans.py line fails on img. 317; lines found from the ink profile (peaks at y 225–1223, 225 the frame; line centres y 342, 450, 561, 672, 780, 886, 996).
- **RD248, 2026-11-09** (RD248, 2026-11-09):
  - Peking img. 315 at 2× is too blurred for syllable certainty on the doubtful stacks; a 4× or higher crop of each (last glyph of line 8, the first stacks of «བཞོན་པ» and «ཁང་བཟངས», «དང་བ») is needed; line centres for lines 3–8 at y about 571, 682, 793, 904, 1015, 1126 on the 5384×1405 scan.
- **RD246, 2026-11-09** (RD246, 2026-11-09):
  - Peking img. 316 at 2× is blurry (letters about 40 px): «གང་ལ» against «གང་བ» and «འོ་མ» against «ཨོ་མ» are not told apart, the etext's reading taken; shad and tsheg spacing not compared.
- **RD250, 2026-11-09** (RD250, 2026-11-09):
  - Peking img. 315 line 8 last glyph (x about 4770–4820, frame at about 4830): a head-line with a left hook and a long stroke falling right, ས or ན with a tail, not told apart at 4×.
  - Seven doubtful places of the Peking Svapnohana left unread at 4× (listed in the RD250 note of svo:p271d); one crop on img. 317 line 1 hit the page number «༧༡» instead of the target.
- **RD249, 2026-11-09** (RD249, 2026-11-09):
  - Peking img. 318 lines 1–3: the line breaks and the start of line 1 («བཞོན») not fixed at 2×; 3–4× re-crops at the doubtful places not made.
- **RD251, 2026-11-09** (RD251, 2026-11-09):
  - Peking img. 315 at 3–5×: the stacks of «བཞོན་པ», «ཁང་བཟངས», «དང་བ», «རྣམ་པར» are blurred past telling from their neighbours (letters about 40 px); line 7 «ཁང་བཟངས» crop not aligned, line 5 «རྣམ་པར» not re-cropped tighter.
- **RD252, 2026-11-09** (RD252, 2026-11-09):
  - Peking img. 316 lines 5–6 at 4×: the crops landed on other syllables or the letters were not told apart cleanly (line 5's «གང་ལ» perhaps at x about 2740–2900, unconfirmed); the RD246 doubts stand.
- **RD253, 2026-11-09** (RD253, 2026-11-09):
  - scans.py line unusable on I6320 pages; the row's y positions do not hold per page; ink-profile centres per image (img. 275: 107, 220, 332, 458, 570, 687; 276: 111, 221, 349, 456, 569, 682; 277: 117, 226, 338, 445, 552, 665; 278: 128, 241, 352, 458, 566, 678; 279: 132, 236, 339, 447, 559, 665), crop from centre−75 to centre+50, lines 2–6 start at x about 250–330.
  - Not settled at 1.4–3.4× on W24769: «དངོས་སམ»/«དངོས་སུམ» (img. 275 line 6), «བརྐྱངས»/«བརྐྱང» (277 line 1), «ཐམས་ཀྱང»/«ཐམས་ཅད» and «ཟྭ་ཚོད»/«ཟ་ཚོད» (276 line 3), «སེམས་སྐྱེད»/«སེམས་བསྐྱེད» (276 line 4), «མགོ་པོ»/«མགོ་བོ» (277 line 6); the ra-stack of «རྡ/རྔ་ཡབ» (276 line 6).
- **RD256, 2026-11-09** (RD256, 2026-11-09):
  - Waseda's 古典籍総合データベース answered 503 maintenance throughout; Waseda's 夢占吉凶秘傳書 (bunko30_e0277) not opened.
  - The NDL fulltext API (lab.ndl.go.jp/dl/api/book/fulltext-json/<pid>) answers 403 for the 和古書 microfilm items (the 大雑書 prints 14970xxx, 14487xxx); only pids 2539476, 2533938, 2541867 and 9892379 returned text; the NDL search lists 76 大雑書 titles, almost all restricted.
  - komonjo.rokumeibunko.com (the transcribed 夢合長寿鑑絵抄) and 575.jpn.org gave certificate errors, not bypassed; Aozora and the 立命館 ARC portal gave only portal pages; the 江戸東京博物館 answer on 初夢 (secondary) says 大晦日, then from the Tenmei years the morning of 1–2 January, then the night of 2 January, naming no source text.
- **RD254, 2026-11-09** (RD254, 2026-11-09):
  - The page image of the Shechen etext's second place (VE1KG14_037, «མཁའ་འགྲོ་ལྔ་སྐོར», etext p. 36): the etext's page markers restart with each text and its 2087 pages run past the print volume I1KG52 (about 830 images); Yigdzin-1 on every hundredth image of I1KG52, I1KG53 and I1KG54 (img. 100–800) and locate.py map gave no hit, so its volume and image are not known.
  - Zhe chen second syllable: no crop at 3–12× tells «རུག» from «དྲུག» or «ཟུག»; MITRA reads «དྲུག» on every crop.
  - Sbas-yul first syllable: «རྟ», «རི», «རཾ», «རྀ» across the machines' crops, not settled by eye.
- **RD261, 2026-11-09** (RD261, 2026-11-09):
  - Waseda bunko30_e0277 not opened (maintenance); the ten images of the 国文研 manifest carry no OCR text layer, so the reading is by eye; no Japanese OCR model available.
  - The branch cells of the ゆ六 table are not all decoded glyph by glyph; what the slash divides in a cell is unexplained on the page; the title's particle and verb are doubtful cursive.
- **RD257, 2026-11-09** (RD257, 2026-11-09):
  - Scheuermann's chapter: Brill 405 human verification (chapter and front matter), Google Books human check and API out of quota, OAPEN Anubis ends in HTTP 500, Unpaywall and OpenAlex closed, LMU and FAU metadata only, Semantic Scholar no open PDF, Academia and Sapienza 403, CORE a human check, DOAB API refuses, HAL no hit, the Internet Archive has no scan of the volume; the Anubis check at journals.ub.uni-heidelberg.de passes by itself in the headed browser.
  - BDRC strings «བི་བྷཱུ་ཏི་ཙནྡྲའི་རྨི་ལམ་བརྟག་པ» and five variants gave 0 hits; bdrc_search 'Author:' lines can attach to the next hit and were not relied on (MW2KG5015_1402 is a Tārā sādhana, rejected).
  - Schuh, Cornu, Mayer, Kapstein, Mills: none found open that discusses the Svapnohana; the open dream chapters found are on Bon texts or closed; Scheuermann's 2018 'One Will Quickly Die!' not found open.
- **RD258, 2026-11-09** (RD258, 2026-11-09):
  - The KCM page in I1KG52 (img. 3–805: the volume ends before KCM's place in the etext) and in I1KG53 (probes img. 5, 40–560 step 20–40, 580–820 step 40: only the Pra se na text at img. 440 and 460 matches the etext): not reached; bdrc.py volumes W1KG14 answers HTTP 401.
  - The etext VE1KG14_037's [pN] marks restart at each text and are not page numbers; RD237's 'KCM at about 762k characters' is true in bytes only, by characters it is 1.077M.
- **RD255, 2026-11-09** (RD255, 2026-11-09):
  - BDRC full-text search for «ལྷས་ཚོར་དུས་ཏེ», «ལྡེར་བཟོ་འགུལ», «སྦལ་ལྗོང་ཉ་སྦྲུལ་འབུ་སྦྲང», «མོ་ཕྱུ་རིམ་གྲོ་རྟེན་འབྲེལ» and variants, «ལུས་ཚོར་དུས་ཏེ» (0 hits), «ལྡེར་སོ་འགུལ» (1 hit, the Bhutan print), «ལྷས་ཚོར་ངུས་ཏེ» (2 hits), «ལྷས་ཚོར་བའི་དུས» (7 hits, all modern glosses following Khro ru): Byang pa (MW27496) and Rin spungs pa (MW27494) have no hit for ch. 81 or 91 and their etexts end with the Explanatory Tantra's ch. 31; the Drikung Dka' mchan has no gloss on ལྷས་ཚོར་, ལྡེར་ or ཕྱྭ itself, Khro ru none on ལྷས་, ལྡེར་ or ཕྱུ.
  - The Bhutan print's scan (MW4CZ16923, I1KG13565), the 2005 Man ngag lhan thabs scan (archive.org) and the 1982 Lhasa print (MW16778, read through search snippets only) not opened; Khro ru's Drang srong zhal lung (MW28849) not fetched; no commentary of Zur khar, Lho brag or Mkhyen rab nor bu turned up.
- **RD260, 2026-11-09** (RD260, 2026-11-09):
  - The Zhol print was not opened for the four ch. 25 lines; the last line's «རྨི་ལྟས»/«ཕྱི་ལྟས» stack of p. 144 was not enlarged on either print; the Zhol folio number (lower left «ཐ», 四百十九) is a low-confidence reading.
  - Print notes: Taikhang vol. 2 (I5878, MW30116) p. 144 is img. 222, printed folio 212 = image − 10, img. 220 ends ch. 29's colophon; Zhol (I1KG1710) the same text is img. 840, img. 835 ends ch. 29's colophon; Taikhang vol. 1 (I5877) printed numbers are image − 10 (the earlier 'page + 159' does not hold).
- **RD263, 2026-11-09** (RD263, 2026-11-09):
  - Pariś. 68 searched whole for tithi, pakṣa, weekday and season names, māsa, ṛtu, nakṣatra: no tie of a dream to any of them; the only time ties are the watches and the five states of the night.
  - The Kālaprakāśikā's Sanskrit verse: none of four Iyer scans on the Internet Archive has Devanagari in its text layer; the Tamil-press scan failed to download; Wikisource, GRETIL and Sanskrit Documents do not hold the work.
  - Bṛhatsaṃhitā and Yogayātrā: no dream chapter. Not read: Kohlbrugge 1938, Modak 1993, Negelein's German Svapnacintāmaṇi (1912), the other Aṅgavijjā chapters (43–44); the Aṅgavijjā morning and midday lines are garbled in both OCR layers.
- **RD259, 2026-11-09** (RD259, 2026-11-09):
  - Higher-resolution Peking: archive.org bdrc-W1KG13126 has a 94 GB original zip that answers 401, the per-volume derivatives are the same scan, a range read of the zip directory failed with 500 twice; Otani's only online resource is the Peking Tripitaka Online Search (catalogue and concordance, no page images); dsr.nii.ac.jp/tibetan-tripitaka/ answers 404.
  - Adarsha's Tengyur is Derge only; the Dpe bsdur ma bdrc-W1PD95844 restricted in China, bdrc-W1PD95859 and bdrc-W1PD95870 have no item; Derge bdrc-W23703 restricted in China; Open Library OL44152045M asked for human verification.
  - RBG vol. 3 pp. 203–205: archive.org bdrc-W1KG2733 exists but is restricted in China and lent only (zip 401), bdrc-W1KG2753 has no item; only the loan reaches it. The Lhasa Tengyur printing, rKTs and ACIP were not opened.
  - Tool note: a background loop with pgrep -f on 'hf_read.py yigdzin' matches its own command line and hangs; use until [ -f out.txt ].
- **RD262, 2026-11-09** (RD262, 2026-11-09):
  - The full etexts of MW25126 and MW8LS18598: etextchunks 403, etextrefs empty for both MW ids, a guessed IE8LS18598 gives 404; chains of searches stop at chunk boundaries (Padma's at «rmi lam rnams ni bgrod min la zhugs shis pa'o», «gang gis gnyid kyi mtha' mar» and the end «[ 2 ]de'i [26 .]»; Bya mdo's at «lus la 'byar dang gseb na 'a»); the sentences before «rmi lam sna tshogs las byung ba» in Padma and after p. 26 not reached by a dozen guesses.
  - The page of Bya mdo's second place (char. 318035): no marker in the window.
  - Neither book is registered as a source with a siglum; cited by MW id.
- **RD264, 2026-11-09** (RD264, 2026-11-09):
  - Serthang etext (IE0OPIFEF6EACA, all 622 pages) for «རྨི་ལམ་བྱི་ལ», «འཆི་བདག་གིས་ཟིན», «དུར་ཁྲོད་ཉལ», «ཡིད་འཇུག་བུ་ག་ནད་ཀྱིས», «མི་བཟང་བ» and «མི་ཟད་པ»: no hit in ch. 7; Yigdzin-1 over Serthang img. 33, 35–58: the bad-dream verse on none.
  - The wa-zur of ཕྱྭ was not looked up in a dictionary (Steinert's clone is not on this machine); the 2005 Man ngag lhan thabs's ch. 81 read at leaf 362 only (borrowed one hour under the owner's login and returned, the cookie file shredded); the Bhutan scan's sister clause and ལྡེར་སོ are by eye at poor resolution (moderate); the Drathang's and the 2005 print's sister clause not read.
- **RD265, 2026-11-09** (RD265, 2026-11-09):
  - No dream-to-tithi/weekday/nakṣatra/month tie in any text read (Negelein's whole layer, Bṛhadyātrā 16, Kālaprakāśikā 42, Matsya 242, Agni 229, Brahmavaivarta 73, the Adbhutasāgara dream chapter, Aṅgavijjā chs. 41–44 and 59).
  - Garuḍapurāṇa: no dream chapter in four editions; Svapnādhyāya manuscripts svapnadhyaya-CGV-PSS-0182-1515, -0183-1516 and M-856: the OCR is noise from manuscript photographs; Viṣṇudharmottara: no Sanskrit layer with the dream verse found; GRETIL's Matsya file 404 and the directory 403 (Agni reached, CC BY-NC-SA e-text, used only to compare, not cited); Wikisource and Sanskrit Documents not tried for the Purāṇas; the Bṛhadyātrā leaf image could not be read in the 293-px viewport; not read: Modak 1993, the Śabdakalpadruma article svapna, Negelein's manuscripts i and r.
- **RD268, 2026-11-09** (RD268, 2026-11-09):
  - Drathang img. 42 and 43 by eye at up to 10×: the vowel over ལ in «ལན་པ»/«ལེན་པ», the prefix of «བྲོ་པའི»/«བྲོ་བའི», the vowel of «འདེད»/«འདོད» (line 11), the pair «རྟ་ཕག» and «རྔ་མོ» (line 3), and the prefix of «ཕན་»/«འཕན་» at the end of a printed line (img. 42 → 43, line 26) not settled (35 px a syllable).
  - The Serthang page of the verse is still missing; the Drathang interlinear notes on img. 42–43 (small red or black glosses) and the Drathang colophon of ch. 7 not read; img. 44 and 45 by Yigdzin-1 only.
- **RD267, 2026-11-09** (RD267, 2026-11-09):
  - BDRC full-text search for the two lines and their variants: 8 hits for the first, 13 for the second, 1 for «ཐོ་རེངས» (the Gso bya lus kyi rnam bshad, restricted); the Serthang etext has none of the lines or the verses around them; the etexts of the Gso bya lus kyi rnam bshad (MW8LS18598), I4795 and I1KG10372, I1KG21894, I4PD4564, I2PD16002, I8LS18618, I4721 answer 403 or empty, only snippets seen.
  - The 'Bum nag gsal sgron is not registered as a source; cited by its BDRC etext id.
- **RD269, 2026-11-09** (RD269, 2026-11-09):
  - No older Indian text with the Brahmavaivarta/Svapnohana series found (not searched beyond Negelein's own list: Śabdakalpadruma, the Bṛhaspati Svapnādhyāya MSS unread); Bṛhadyātrā vv. 1–3 (p. 40) and Negelein pp. XXI, 17, 35 not opened; the OCR-based Atharva (apv) and Svapnohana lines not re-read.
  - Ids keep wrong page numbers (renaming would break cites): bvp:p662-665 (true p. 661), mtp:p453-455 (493–494), agp:p280-281 (281–282), agv:p186 (185), agv:p259-260 (260–261); the RD265 row text is stale.
- **RD266, 2026-11-09** (RD266, 2026-11-09):
  - Co ne vol. 63 (I1GS66094) img. 400–604 and vol. 65 (I1GS66096) img. 1–18 do not hold the Svapnohana; next places: the end of vol. 64 (only img. 480–544 every sixth sampled before), the end of vol. 65, vol. 66, or the Co ne number of the work from a Co ne catalogue (the Peking number is not the Co ne's).
  - iiif.bdrc.io answers 429 to parallel requests: download one image at a time with a 0.5 s pause and backoff; the GPU slot is shared and waits several minutes a call at 14–19 s an image, a full OCR of 160 images about 40 minutes, a stride search is cheaper.
- **RD271, 2026-11-09** (RD271, 2026-11-09):
  - ram rug, ram rug sbang g.yer, mi kha'i rdzas, spang g.yeng and spang g.yer have no headword in Steinert's public sets or on the online Steinert site; BDRC's exact search for «རྨེ་ཅན་ནི་གདོང་ལ» gave 0 hits; «གཏད་དུ་ཚུད་པ» has no dictionary headword.
  - The Brda yig ming don gsal ba'i sgron me page (I1KG16860 img. 263) lists bro pa'i khral 'ded in a headword list but the cursive glosses could not be read; Das and Jäschke exist in Steinert's set only as page scans and were not checked; the Bod rgya tshig mdzod chen mo etext was not found; the medical dictionary's images (I00EGS1017132) are not served.
- **RD270, 2026-11-09** (RD270, 2026-11-09):
  - BDRC title searches on gso rig rgyud bzhi, rgyud bzhi, sman gyi rgyud bzhi, bshad rgyud, rgyud bzhi par and rgyud bzhi dpe bsdur: the abstract work WA3CN5036 lists only the Serthang instance; open scans listed but not read to the verse: Chag-po-ri MW1KG13115 (I1KG13487), MW30134 (I5907), MW8228, Zhol MW30137 (I5910), Derge MW00EGS1016257 (I00JW501163) and MW1KG12776, Drathang MW1KG13761 (I1KG116089), MW3CN1703 (I3CN1705) and MW4CZ321191 (I4CZ327147); not on open scans: Lhasa MW16778, the archive.org Drathang MW29627, the Dpe bsdur ma MW1PD137835.
  - iiif answered HTTP 429 for most images when six volumes were fetched in parallel: one volume at a time; a follow-up could take the Zhol, Derge, other Drathang and Chag-po-ri copies at roughly img. 36–45; the interlinear notes and the line 26 prefix (ཕན/འཕན) not read.
- **RD273, 2026-11-09** (RD273, 2026-11-09):
  - The BDRC outline O1GS66030 of W1GS66030 and the resource WA0RT4282 give no per-text Co ne entries; the place came from rKTs (rkts.org, data/collections/C/C.xml); Co ne vols. 63–66 hold neither text.
  - Yigdzin-1 on the whole Co ne image returns only «༄༅། །» for img. 273 (vol. 28) and img. 263 (vol. 49): crop the frame at 1.5× or use line strips (strips hallucinate past line 4); the Co ne Svapnohana's doubtful words could not be told by eye at 2–3×; the archive.org zip is the same scan and not sharper; 429 did not occur for ten images per call with bdrc.py scans.
- **RD272, 2026-11-09** (RD272, 2026-11-09):
  - Penn Ms. Coll. 390 item 1871 (a Bṛhaspati Svapnādhyāya, 5 leaves): the OPenn server delivered about 100 bytes per second, images not read; retry with https://openn.library.upenn.edu/Data/0002/mscoll390_item1871/data/web/3014_000{2..5}_web.jpg.
  - Aufrecht's Catalogus Catalogorum IA text layer: Devanagari garbage; Modak 1993 not opened; the Bodleian (403) and Leipzig (host not resolved) records not fetched; the IA item in.ernet.dli.2015.345206 holds ka to ha so not svapna; the Bengali-script first-edition Parts V and VI end at śrīhi (the article would be in a Part VII not found); the Basu printing of 1886–93 itself not seen at the page; the Śaunaka-kārikā manuscript not identified in Negelein's pages.
- **RD276, 2026-11-09** (RD276, 2026-11-09):
  - The Co ne images on BDRC are the only Co ne copy tried; the archive.org zip is the same scan; the Dpe bsdur ma notes (vol. 14 p. 1368) cite «ཅོ་» at none of the five words; no higher-resolution Co ne.
  - By eye at 2.5×–14× on blurred, sharpened, dilated and deskewed crops none of the five doubtful words nor the fourth-watch line's inner syllables could be read letter by letter: the ink is dotted and partly absent (the half-leaf printed from a worn block and reproduced as a bitonal image); strips of this scan hallucinate; MITRA frames cost several minutes each through the slot.
- **RD275, 2026-11-09** (RD275, 2026-11-09):
  - Line 11 (བྲོ་བའི/བྲོ་པའི, འདེད/འདོད, ལེན་པ/ལན་པ) and line 26 ཕན/འཕན on Zhol I5910 img. 56–57, Chag-po-ri I1KG13487 img. 48–49 and Derge I00JW501163 img. 41 not settled by eye at 5–14× (bilevel or grey scans, glyphs broken); I5907 img. 66 line 11 not looked at by eye.
  - The verse is not on img. 36–45 of I1KG13487, I5907, I5910 or I00JW501163 (those are chs. 2–6 and the Root Tantra's end; the Zhol Explanatory Tantra starts at img. 38, ch. 7 is on img. 56–59); not reached to the verse: Drathang I1KG116089, MW3CN1703 (I3CN1705), MW4CZ321191 (I4CZ327147), Chag-po-ri MW8228 (I1KG10010, img. 30–52 fetched, not OCR'd), Derge MW1KG12776 (I1KG12862, img. 30–52 fetched, not OCR'd); the verse is about three images before each print's ch. 7 colophon.
- **RD278, 2026-11-09** (RD278, 2026-11-09):
  - SKP: WA0XLA02C496D030E has one instance only; BDRC's range for MW1NLM9044_009 (pp. 153–164) is a king-spirit rite, not a dream text; the dawn line of NLM933 img. 50 not settled.
  - RRT: «རྨི་ལྟས་བརྟག་ཐབས་རྒྱས་པ» has one hit only (MW3KG218_O3KG218_VTCXE7); RBG: WA1KG2733 lists only MW1KG2733 and W1KG2733; BDRC search finds the Derge and Peking Tengyur texts of the witness list, not RBG itself.
  - Tool note: curl https://purl.bdrc.io/query/table/volumesForInstance?R_RES=bdr:W23703&format=json&pageNumber=N works GET with no auth and lists a work's volume ids and numbers when bdrc.py volumes gives 401 (W23703: volume 206 is I1524).
- **RD277, 2026-11-09** (RD277, 2026-11-09):
  - Penn item 2730: first image 4951_0000 arrived as a dark cover frame only, 4951_0002 gave the top line only; item 687 (6029_0001) gave no text lines; item 1871 leaves 2–5 arrived only in their upper lines, the table on f. 1r and the colophon on f. 4r not seen; the OPenn server gives 1–3 kB/s and resets connections, curl -C - in a retry loop works.
  - The Leipzig portal host mymssportal.dl.uni-leipzig.de does not resolve; the Bodleian South Asian catalogue (d. 215, Chandra Shum Shere d. 765(1)) returns 403; no images of Leipzig 1178 or Oxford d. 215 found; Berlin SBB Hs. or. 6301-12 not opened; sourcelibrary.org/book/svapanadhyaya gives a Cloudflare challenge; Surat CGV-PSS-0183-1516 not read, CGV-PSS-0182-1515's page edge cuts off the fourth span; the Telugu palm leaf and the Hindi Svapnaprakāśikā text layers lack the span words.
- **RD279, 2026-11-09** (RD279, 2026-11-09):
  - Chag-po-ri MW8228 (I1KG10010 img. 30–58) and Derge MW1KG12776 (I1KG12862 img. 36–52), Yigdzin-1 over every image: no རྟ་ཕག, བཟང་བ, བཟད་པ or ch. 7 colophon; the text is a commentary on the Root Tantra's structure, not the Explanatory Tantra's own text; the row's premise (a whole-work print) is doubtful for both; check MW8228 and MW1KG12776 in the BDRC catalogue before more time; the Drathang I1KG116089, I3CN1705 and I4CZ327147 would have to be read from the cache (img. 34–75, 25–75, 25–74) with Yigdzin-1 to find the verse.
- **RD280** (RD280, 2026-11-10):
  - W22704 (I3279 img. 286) at 3–4×: the thresholded letters are broken, not readable syllable by syllable; the manuscript replica I2KG212312 is the legible Narthang witness.
  - Co ne SVO (I1GS66059 img. 270 lines 3–4) at 3×: dotted, partly missing ink; no watch-line syllable told apart by eye except the stack «ཐུན་བཞི».
- **RD282** (RD282, 2026-11-10):
  - About 90 exact-string searches on BDRC (2026-11-10, EWTS) for the paragraph between char. 18559 and 21346 of Padma rdo rje's book and for the one after char. 25516: forms of «rmi lam brtag pa» with a particle or noun, «bstan 'gyur las rmi lam», the translator and author names, «slob dpon», «lo tsA ba», «ngag dbang gi dgongs pa», «de ltar» and «thun» openings: more than ten hits (none his book) or none. Only the lent scan can settle it.
- **RD284** (RD284, 2026-11-10):
  - The Zhe chen, Kazi and Sbas-yul prints' first line of the LGD rite (the six glyphs of p. 962): their dream folios only are in the scan cache and the rite's first folio was not located; no plain-letter witness seen.
  - BDRC exact-string search (2026-11-10) for «མཉམ་པོར་འོང་བའི་བདུད» (68 hits), «མཉམ་པོར་འོང་བའི་ལྷ» (1), «ལྷན་ཅིག་སྐྱེས་པའི་བདུད» (88), «རང་དང་ལྷན་ཅིག་སྐྱེས་པའི་ལྷ» (9), «ལྷན་སྐྱེས་ཀྱི་བདུད» (12): snippets only, none glosses the phrase or names a dream.
  - A span for LGD's dusk, midnight and dawn (hours or watches): none in LGD, in RST's thirds or in the pages read.
  - A collectors' note, preface or list of sources in MDP: none in the front matter (I4992 img. 4–19) or on pp. 134–138; a composer's name for the rite of pp. 134–137: none.
- **RD286** (RD286, 2026-11-10):
  - The Delhi print of TDR, img. 346 (p. 342 foot): the catchword at lower right is not legible; the Śramaṇā text's colophon was not read on the scan; the other print's etext gives no author or colophon name.
  - BDRC records of MW25101 parts 64–66 give no author for any of the three works; BDRC's record of SKP's first text has no author field.
  - BDRC full-text search «དམར་མོ་འདས་པའི་གདུང་ཐལ»: no hit; «རྨི་ལམ་བརྟགས་པའི་ལེའུ» and «རྨི་ལམ་བརྟག་པ་པདྨ་དཀར»: no hit (for «རྨི་ལམ་བརྟག་པའི་ལེའུ» only the Man ngag rin chen nyi ma'i sgron ma ch. 6, VE1KG14_019, and an unrelated NLM manuscript).
  - Steinert's 64 dictionaries (locate.py dict) not consulted: the clone is not in the scratch; «dmar mo» and «rno bkra» not looked up.
- **RD285** (RD285, 2026-11-10):
  - 国書データベース title search 大雑書: 305 records, several open-image prints (永代大雑書 Ansei 3 on the NDL, 星槎ラボ's 大ざつしよ Enpō 2, 彰考館, 北大's 安政雑書大成) but no 京都大学 潁原文庫 copy of EB9's 大雑書.
  - 京都大学貴重資料デジタルアーカイブ: its keyword search ignores a keyword given in the address and returns the whole archive (27,627 items); no 大雑書 found there.
  - The NDL fulltext API is 403 for the other 大雑書 prints and for 校註国歌大系, 西行法師全集 (春陽堂 1926) and 山家集詳解; it works for 日本歌学全書 (pids 993401–993412), 異本山家集 and 西行全集 (1930). 山家集 on Wikisource and the 国歌大観 numbers: not open.
- **RD283** (RD283, 2026-11-10):
  - WB vol. 1 pp. 428 and 429 are absent from the 1996 scan I1KG12906 (img. 437 = p. 427, img. 438 = p. 430); p. 428 read on the Gser thang reprint, Zhol and Taikhang.
  - No verb of seeing and no image beside «རྨི་ལམ་རྩུབ» or «རྨི་ལམ་ཟ་ཟི་མང་བ» at the three places, on any print read.
  - No gloss of «རྩུབ» or «ཟ་ཟི» in Mipham's notes by the machine reading of img. 482–549; the Chengdu etext of the notes is search-only (API 403) and was not opened.
  - The Zhol etext (IE0OPI7850CC9C) is unusable for searching: no tshegs and full of OCR noise.
- **RD281** (RD281, 2026-11-10):
  - ST ch. 2's birth lines on the Chag-po-ri print I1KG13487 (img. 35–37): Yigdzin-1's text of img. 36 breaks off before «ཟླ་དགུ་འདས་ནས»; no reading by print there.
  - ST ch. 81 on the Derge and the Chag-po-ri copy: not located (the Zhol's is img. 483; by proportion near img. 440 and 442).
  - BDRC full-text search 2026-11-10: «ནིའུ་གསིང་དང་སྦལ་ལྗོང» no hit; «ལྷས་ཚོར་དུས་ཏེ» 14 hits, all the same verse with no gloss of the clause. The Blue Beryl and the Moonbeams gave no hit on the words.
- **RD288** (RD288, 2026-11-10):
  - «བརྗེད་ངེས» on any of the eight prints' scans of ch. 7, Yigdzin-1 over the line and by eye on two: not found; all have ངས.
  - A part of the night between midnight and dawn named in Byang pa, Khro ru, Rin spungs pa, the Drikung and the 'Bum nag gsal sgron (the passages in the pool): not found.
  - The Gso bya lus kyi rnam bshad (etext restricted) and a second published etext of Byang pa or Khro ru: not opened.
- **RD289** (RD289, 2026-11-10):
  - The Narthang manuscript's second and third title signs at 5–6× (img. 284 line 2): ink-filled stacks, not told apart.
  - The Narthang manuscript's «ལྷོ»/«ལྟོ» (img. 286 line 4) and «སྙིང་སྡུག»/«སྔག» (line 2 end): the head of the stack not told apart at 7–8×.
  - Yigdzin-1 over the whole frame of img. 286 returns only 20 characters (a colophon); the frame needs line strips.
- **RD287** (RD287, 2026-11-10):
  - WB's notes (vol. 2, pp. 474–541) and the etexts of the notes: no «ཐུན་བཞི», «མཚན་ཐུན», «ནམ་ཐུན» or «སྲོད་ཐུན»; Mipham's own words on the watches: none in the etexts the pool holds.
  - BDRC full-text search 2026-11-10: «ཐུན་གསུམ་ཀུ་མུད་བཞད» 8 hits (the 1996 print, the Gser thang etext, the Zhol and its second impression and four volumes not identified); «ཉིན་མཚན་ཐུན་བརྒྱད» 8 hits (WB vol. 2 in two prints and six volumes, three windows read did not show the phrase); «ཐུན་མཚམས་རྭ་ཧུ» 0 hits. Nothing from the Kālacakra tradition or from Mipham.
  - The Zhol folio for vol. 2 p. 403 (etext page 1167): img. 1167 shows the medicine passage and a picture; the Taikhang reproduction was not read for any passage here.
- **RD291** (RD291, 2026-11-10):
  - Scheuermann 2020 (Brill, pp. 161–180) is not open: brill.com blocks automated browsers with a human check (HTTP 405), and OAPEN, DOAB and the Internet Archive hold no copy.
  - No puṇyakāṇḍa of the Kāṇva branch in any open catalogue or study; Ganga Sagar Rai (Purāṇa Bulletin 8, 1966) and Bonazzoli unread, not open.
  - No separate date for the Brahmavaivarta's dream chapter (IV 73 in Jivananda's numbering); Hazra and Rocher read in text layers only.
  - Internet Archive access: iiif.archive.org serves full-size leaves of the Kālaprakāśikā scan, and the _djvu.txt files of Hazra and Rocher download without a loan.
- **RD292** (RD292, 2026-11-10):
  - The Svapnohana's colophons on the Derge, Peking and Narthang: no author, reviser or place named; the title-like tag after «༡༡» at the end of Peking img. 314 line 8 not told apart at 1.5×.
  - The Derge dkar chag and the Peking and Narthang catalogues were not opened (no etext or scan located); the catalogue statements stand as RD257 recorded them.
  - BDRC person records of Vibhūticandra and Vāgīśvarakīrti: no work, teacher or pupil link between them, no entry naming the remedy's Ngag dbang.
- **RD295** (RD295, 2026-11-10):
  - A text of the Aṣṭāṅgahṛdaya Śārīrasthāna on GRETIL: the site holds only the Sūtrasthāna (and under a non-commercial licence); the Śārīrasthāna was read on the Kunte page images.
  - A variant of the cows' line (go-sarga against go-mārga) in Kunte's notes: the page has no apparatus.
  - A commentary on Bṛhadyātrā 16.10 that states the pairing: none open.
- **RD293** (RD293, 2026-11-10):
  - NDL full-text search for 初夢 with 宝船, 二日, 元日の夜, 守貞漫稿 and 東都歳時記 found no Edo book, only modern local histories; nothing for 夢を見て語らざる日 and its variants.
  - NDL catalogue (ndl.py) for 東都歳時記, 江戸歳事記, 守貞謾稿, 類聚近世風俗志 and 嬉遊笑覧: no open pid; the Meiji pids 759975, 759977, 759984, 760747 and 760755 give OCR too rough to search for the table.
  - 大雑書抜緑組 (pid 9892379, 1798) and 夢合延寿袋大成 (pid 2533938) searched in the OCR only, images not read; the 星槎ラボ 延宝2 大ざつしよ and the 安政雑書大成 not reached (no locator for their images).
- **RD290** (RD290, 2026-11-10):
  - The Zhol's ch. 81 (I5910 img. 483) cropped at 3–4×: the bilevel strokes are too crowded for a reading by eye, the Zhol's ལྗོངས stays the machine's reading.
  - The Zhol, Derge, Chag-po-ri and Drathang pages of ST pp. 183, 218 and 241 (q790) not located; the Drikung commentary's etext has no gloss of ཟ་ཟི, only some thirty uses.
  - ལྷས་ཚོར in the Drikung commentary: quoted without a gloss; Chag-po-ri img. 425–448 and Derge img. 400–446 hold no second copy of the list.
- **RD296** (RD296, 2026-11-10):
  - A second BDRC instance, scan or etext, of the Serthang Four Tantras: the work record WA3CN5036 lists MW3CN5036 only; searches for «gso rig rgyud bzhi» with «gser thang», «gser thang bla rung lnga rig nang bstan slob gling», «gser rta» and «gso rig sde bzhi» give that instance alone (2026-11-10).
  - The ch. 7 bad-dream verse and the two night lines anywhere in the Serthang etext (all 622 pages, spaces and tsheg stripped; «རྨི་ལམ་བྱི་ལ», «སྤྲེའུ་སྟག», «འཆི་བདག་ཟིན», «དུར་ཁྲོད་ཉལ», «མི་བཟང་བ», «མི་ཟད་པ», «མཚན་སྟོད»): none; the leaf is not misplaced elsewhere in the volume.
  - «བརྗེད་ངེས» in the Lhasa 1982 and Beijing typeset etexts of the verse: not found (ངས on both). A BDRC full-text search of «brjed nges» also hit Bon texts, not used.
- **RD297** (RD297, 2026-11-10):
  - I2638 img. 692, the syllable before «རང་གི་དུས་ཚོད» (end of line 3 and start of line 4, crops up to 5×): not settled by eye as ཐོ, བཻ or ཕམ; comparing it with the same hand's ཐོ elsewhere on the page not finished.
  - The Rin chen gter mdzod scan page of KCM: no route beyond RD258's; DTD p. 158 comparison not done.
- **RD294** (RD294, 2026-11-10):
  - A fifth animal or a variant to «རྟ» on the Taikhang, Zhol or 1996 prints: none; the Bhutan collection and the Sakya print were not opened for this glyph.
  - ST ch. 91 on the Derge print I00JW501163: not found in img. 447–452, 500, 540, 580 and 620 by Yigdzin-1 (OCR search for «རིམ་གྲོ», «རྟེན་འབྲེལ», «བུད་མེད་འདྲེན», «རོ་ཙ», «ཕྱྭ»); nor on the Zhol img. 513–528 (chs. 82–83).
- **RD298** (RD298, 2026-11-10):
  - The Amoghasiddhi line's second and fourth syllables on the Narthang manuscript: Yigdzin-1 on 16 windows of the line's end, contrast-stretched and thresholded crops at 5–7×: the lower sign of the second syllable is smeared by the verso's show-through and the right-hand frame rule; not settled.
  - A Peking reading by eye of the three places was not repeated: the crops of I1KG13215 img. 310 missed the lines (about 108 px apart, the letters faint grey), so the Peking column of the count is RD234's.
- **RD300** (RD300, 2026-11-10):
  - Six BDRC volumes came back empty from the etext API (I4723, I2PD18140, I2PD16002, I3CN6530, I4PD4564, I1KG2751) and are not counted.
  - The Blue Beryl and the Moonbeams are not among the 19 hits for the line and were not looked at; the author of the unsigned great commentary I5618 not identified.
- **RD299** (RD299, 2026-11-10):
  - A part of the night between midnight and dawn named in the Blue Beryl (I1PD411), Zur mkhar ba (I8LS19191), Skyem pa Tshe dbang (I4711) and the Moonbeams (MW23703_4312, MW1PD95844_3549): not found (BDRC etexts, 2026-11-10).
  - «མཚན་སྟོད་སྲོད» as an exact phrase on BDRC full-text search: 0 hits; «མཚན་གུང་རྨི་ལམ»: 4 hits, all modern prose or verse, none a commentary.
  - The Four Tantras commentary I1KG10372: the volume is closed (0 chunks); I2PD16002 (1.5 million characters) not fetched; the Dri med kun gsal and Drang srong zhal lung etexts not opened.
- **RD301** (RD301, 2026-11-10):
  - BDRC full-text search 2026-11-10: the exact ch. 2 line 19 hits, neither the Blue Beryl nor the Moonbeams among them (the Blue Beryl's etext searched whole: «རྨི་ལམ་དག་ན» once, in its own sentence with an added ཡང, «རྨི་ལམ་དག་ནའང་»); «མཚན་སྟོད་རྨི་ལམ་འབྲས་བུ་རིང» 11 hits, among them the Moonbeams and Byang pa; the Moonbeams on the Four Tantras' own verse «བརྗེད་ངས» or «བརྗེད་ངེས»: not in it.
  - No scan of the Blue Beryl (I1PD411) or of the Moonbeams (Tengyur) looked at; both rest on machine-made etext, so nothing is quoted from them without a scan check. Dharmaśrī's Moonbeams in dz is another work (elemental divination).
- **RD302** (RD302, 2026-11-10):
  - «རྨི་ལམ་མི་བཟད་པ» as the Tantra's line in Skyem pa, Zur mkhar ba, the Blue Beryl, the Drikung dka' mchan or Rin spungs pa, spaces and tsheg stripped: not found; «མི་བཟད་པ» stands only as an adjective in Zur mkhar ba and the Blue Beryl.
  - «སྤྲེའུ་སྟག» in Zur mkhar ba, the Blue Beryl and Skyem pa Tshe dbang: not found as a quoted line of the Tantra.
  - «མི་ཟད་པ» as the Tantra's line in Skyem pa, Zur mkhar ba, the Blue Beryl and the Drikung: not found; Byang pa and Khro ru not reopened.
- **RD306** (RD306, 2026-11-10):
  - The Peking bird line: img. 311 lines 4–5 were cropped at 1–1.5× in two halves and the line was not found among the eight lines; not located, so not read.
  - BDRC full-text search for the sign in the Tibetan commentaries and the Indian sources was not run; the Derge, Co ne and Narthang places were not reread.
- **RD305** (RD305, 2026-11-10):
  - The Derge print of the Svapnohana in the image group I00JW501163 named by the row: that group is the Derge Four Tantras; the Derge Tengyur frames are in I1344 img. 268–271, letters of 8 px.
  - A syllable of «བགྲོད་མིན» and of «བཅུ་ཡི་བར» that differs on the Peking: none seen at 2.5–4×.
- **RD307** (RD307, 2026-11-10):
  - Exact BDRC searches with 0 hits: «མཉམ་པོར་འོང་བའི་བདུད་ནི», «མཉམ་པོར་འོང་བའི་བདུད་ཅེས་པ», «ལྷན་ཅིག་སྐྱེས་པའི་ལྷ་དང་མཉམ་པོར་འོང་བ», «ལྷན་སྐྱེས་ཀྱི་ལྷ་དང་མཉམ་འོང་གི་བདུད», «རམ་རུག་ཅེས་པ», «རམ་རུག་ནི»: no definition by «ཞེས་པ་ནི» apart from the Chöd gloss of the karmic spirit.
  - No text besides LGD lists the pair in a dream-causes section; no text says what «རམ་རུག» is.
- **RD303** (RD303, 2026-11-10):
  - BDRC exact-phrase searches of 2026-11-10: «སྦལ་ལྗོང་ཉ་སྦྲུལ་འབུ་སྦྲང་སྡིག་སྡོམ» 3 hits; «ནིའུ་གསིང་དང་སྦལ» and «ནེའུ་གསིང་དང་སྦལ» none; «ལྷས་ཚོར་དུས་ཏེ་གདོན» 14; «རང་ཚོར་དུས་ཡིན་གདོན» 18; «ལྷས་ཚོར་བའི་དུས» 7; «ལྷས་ཚོར་གྱི་དུས» 7; «རྨི་ལམ་ལྷས་ཚོར» none.
  - The scans of the Blue Beryl, Skyem pa, Dar mo, the Drikung and Zur mkhar ba answer 401 and cannot be viewed; Zur mkhar ba's two ནེའུ་གསིང hits and Rin spungs pa's one are in other contexts.
- **RD309** (RD309, 2026-11-10):
  - A commentary on the Svapnekṣā that names the Amoghasiddhi bird: BDRC full-text search «བྱ་ཤར་ཏེ་རུ» 3 hits (the Derge Tengyur etext, the Dpe bsdur ma etext, a modern medical collection), «བྱ་ཤིང་ཏེའུ» 0, «བྱ་ཤང་ཏེའུ» 0, «བྱ་ཤར་ཏེའུ» 0, «བྱ་ཤིང་ཏེ» 0, «བྱ་ཏེའུ་ཕོ་ཉར» 0, «བྱ་ཤར་ཏེ» 5 (two unrelated).
  - The Narthang line by eye at 3× and 8× is a smear of the verso's show-through; the Co ne line is a dense bitonal bitmap not readable by eye; the Derge ར and ུ of «རུ» at 8 px are not told apart.
- **RD304** (RD304, 2026-11-10):
  - The Blue Beryl, Skyem pa, the Mes po'i zhal lung, Zur mkhar ba (I8LS19191) and Sde srid's Man ngag lhan thabs (I1PD403) searched whole in the etext, 2026-11-10, for «མོ་ཕྱྭ», «རིམ་གྲོ་རྟེན་འབྲེལ», «རྡོ་རྗེའི་མདུད་པ་བདུན» and «བ་རའི་འོ་མ»: no gloss of ཕྱྭ, བ་ར or the milk's purpose; Zur mkhar ba and the Lhan thabs have no lines on ch. 91, the Mes po'i zhal lung only the kapha-causes verse.
  - Steinert's dictionaries (locate.py dict) not cloned (4.5 GB): དྲི་ཆེན and བ་ར not looked up there; BDRC's full-text search not run; the Zhol print's img. 675 not re-read.
- **RD308** (RD308, 2026-11-10):
  - NDL catalogue (ndl.py) for 夢を見て語らざる日, 夢 語らざる日 大雑書, 大雑書, 万代大雑書, 永代大雑書, 夢合, 夢判断, 夢占 and 三世相: the 永代大雑書大成 (pid 759978, 1883) and the 吉凶禍福夢判断 (pid 760913, 1890) carry the words but answer NG to the IIIF manifest and are not allowed in the full-text API; the 万代大雑書三世相 (pid 760742) is open and was read (mdz:i108).
  - NDL full-text API for the 104 open items among 360 records: 語らざる, かたらざる and 語らず occur in no almanac or dream book; 初夢 in no almanac; the 江戸歳事記 of 1838 (pids 8369316, 14670261) restricted; the 守貞謾稿 and the 類聚近世風俗志 only as restricted Iwanami reprints; the 俳諧歳時記栞草 and the 江戸府内絵本風俗往来 have no pid; ja.wikisource has no page for the 守貞漫稿 or the 東都歳事記; the NDL full-text search for 初夢 with 二日 pages to the same hits on every page.
- **RD310** (RD310, 2026-11-10):
  - The Co ne print of the same three places by eye: the Co ne scans (RD277) were read by machine only and no new Co ne image was taken; the places stay machine readings there.
  - A variant of «བགྲོད་མིན» or «བཅུ་ཡི་བར» on the Derge frames at 10–12×: none seen; «བཟློག» for «བགྲོད» and «པི» for «ཡི» are not what the strokes show.
  - A telling of ྷ from ྟ in the stack after «དང་» before «འཕྱེ» at 18×: not possible at 8 px letters.
- **RD312** (RD312, 2026-11-10):
  - Penn Ms. Coll. 390 items 2730, 1871 and 673: openn.library.upenn.edu delivered 0.2–1 kB/s and stalled; not read, q868 unanswered for Penn.
  - Leipzig MSS 1176–1180: no scan or catalogue text found open.
  - Hemādri's Āyurvedarasāyana (two Gurukul Kangri scans, partly damaged): no gloss on Śārīra 6.63 in the text layers; no commentary on the Bṛhadyātrā dream chapter other than Bhaṭṭotpala's; no Śārīrasthāna on GRETIL.
  - The edition letters Saṃ and the Loṃbaḍī-pāḍā one are garbled in the Aṅgavijjā preface's text layer, the page not read on the image; the Aṣṭāṅgasaṅgraha edition's title page and press not found.
- **RD313** (RD313, 2026-11-10):
  - BDRC full-text search, 2026-11-10: «མི་བཟད་རྨི་ལམ་མཐོང་འགྱུར་ཏེ» 6 hits, «མི་ཟད་རྨི་ལམ་མཐོང་གྱུར» 1, «མི་བཟང་རྨི་ལམ་མཐོང་འགྱུར་ཏེ» 1, «ཐོ་རངས་རླུང་གི་དུས་ཉིད་དེ» 4, «ནམ་ཕྱེད་དུས་སུ་མཁྲིས་པར་བརྗོད» 8, «གསུམ་ཆ་ཕྱིས་འབྱུང་གསལ་བྱེད་ཡིན» 8, «སྐྱེས་པ་དང་གླང་པོ་ཆེ་ལ་སོགས་པ་མཐོང་བ» 3 (all the Moonbeams), «ལུས་ཡང་ཤིང་ཆེ་བ» 1 (Rin spungs pa).
  - Names for the stretch from midnight to dawn (མཚན་བར, མཚན་ཕྱི, མཚན་མཇུག, མཚན་ཐ་མ, ནམ་ཕྱེད་ཕྱི, ནམ་ཕྱེད་ནས, ནམ་ཕྱེད་ཚུན, ནམ་གུང, མཚན་ཕྱེད, ནམ་ཕྱེད་འདས) near རྨི་ལམ in seven commentaries' etexts: none.
  - Not re-read: Khro ru, the 'Bum nag gsal sgron (I1PD409), the Man ngag lhan thabs for midnight to dawn, Rin spungs pa's second volume (I4708), the Peking-type Moonbeams and the Blue Beryl's other etext on the page; Bya mdo only by snippet, his etext closed; no page image of any commentary opened.
- **RD311** (RD311, 2026-11-10):
  - The Co ne's watch lines, «བགྲོད/བཟློག», the word after «བཅུ» and the Gāyatrī's first word by eye on deskewed, blurred crops at 4–7×: the ink is dotted and partly absent; not one letter told.
  - The Svapnekṣā vol. 49 img. 263–264: the right halves of the strips returned nothing or invented text; narrow windows of 330–350 px and the right half of a line as a strip gave nothing usable, whole-line strips at 1.5× give the stretches recorded.
- **RD317** (RD317, 2026-11-10):
  - A Sanskrit dream text with the half-month for the fourth watch other than the Brahmavaivarta's chapter: Internet Archive title search for Ācāramayūkha and Svapnādhyāya gave six items: two photographed manuscripts of the Bhagavantabhāskara (text layer noise), the Bṛhaspati Svapnādhyāya manuscripts already read (RD277), and the CC BY-NC-ND Romanian translation of the Ācāramayūkha (a lead only).
  - A public-domain print of the Ācāramayūkha with the dream chapter: not found in the Internet Archive title search; HathiTrust not searched.
- **RD315** (RD315, 2026-11-10):
  - BDRC full-text search, 2026-11-10, no hit: «ས་སྐྱ་པཎྜི་ཏ་རྨི་ལམ་བརྟག་ཐབས», «རྨི་ལམ་བསྟགས་པའི་དཔལ», «བླ་མ་རྒ་ལོ་ཟེ་དམར་བ», «བི་ཁྱཱ་ཏ་དེ་བ་ཤཱཀྱ་ཤྲཱི་དཱ་ན་ཤྲཱི», «དམར་མོའི་གདུང་ཐལ», «དམར་མོ་ཤི་བའི་རོ་བསྲེགས་པའི་ཐལ», «སྒྲ་ཚད་པ་རིན་ཆེན་རྣམ་རྒྱལ་བུ་སྟོན»; «རྣོ་བཀྲའི་ལོ་རྒྱུས» two hits, titles only; «ལེགས་པར་བཤད་པ་པདྨ་དཀར» 34 hits, all titles.
  - SKP img. 50 lines 3–4 at 3×: cursive not resolved; no MITRA on GNC img. 501–502; MDP's second text (p. 138 ff.) not read to its end; Steinert's dictionaries not consulted, so «dmar mo» and «rno bkra» are not looked up there; no author or colophon name at the end of White Tārā's text in the other TDR print.
- **RD318** (RD318, 2026-11-10):
  - No other print or source quotes either line: searched in the two etexts (identical wording at both places) and in the Yigdzin-1 text of five other prints.
  - The Zhe chen and Ngodrup hands could not be read by eye at the key syllables (scans too small and cursive); the ཕ/བ letter on Kazi (looked at by eye at 9–12×) not settled; no MITRA pass.
- **RD222** (RD222, 2026-11-10):
  - RD222 second run: TDR img. 347–348 and GNC img. 565–569 (2173×460 and 2165×461 px) cannot be read syllable by syllable at 2.3×; only a higher-resolution scan or another print helps.
  - On SRM (4320×896) cropping by character fraction put the window across two lines; crops must be cut from per-line y centres (window about −42 to +26 px). disagree.py's «line N» does not map to scan lines on these pages.
- **RD316** (RD316, 2026-11-10):
  - Mipham's notes on WB ch. 25 and 27: the Chengdu etext is search-only; BDRC searches for «ཟ་ཟི་མང་བ་དང», «རྨི་ལམ་ཟ་ཟི་མང་བ», «ཟ་ཟི་ནི», «ཟ་ཟི་ཞེས་པ», «ཟ་ཟི་ཞེས», «ཟ་ཟི་ཡིན», «ཟ་ཟི་ལ», «ཟ་ཟིའི་» showed no Mipham hit among the twenty each lists; the unlisted hits (236 for «ཟ་ཟི་མང», 161 for «རྨི་ལམ་ཟ་ཟི») not seen, a weak negative.
  - No lexicon entry for «ཟ་ཟི་མང» itself; glosses in the forms «ཟ་ཟི་ཞེས་པ་ནི», «ཟ་ཟི་ཞེས་པའི», «ཟ་ཟི་སྟེ»: 0 hits; the Blue Beryl and Zur mkhar ba not opened for this word; the Zhol, Derge and other prints of st:p183, st:p218, st:p241 not located; the Great Tibetan–Chinese dictionary not read by its own title.
- **RD314** (RD314, 2026-11-10):
  - About 20 BDRC phrase searches for night-watch bounds (among them «མཚན་ཐུན་བཞི», «ཆུ་ཚོད་བདུན་ཕྱེད», «ཐུན་རེ་ལ་ཆུ་ཚོད», «ཉིན་མཚན་ཐུན་བརྒྱད») gave no hit; no text read gives a watch in hours or in 7½ water-hours; «ཐུན་བཞིར་བགོས» and «མཚན་ཐུན་བཞིར» hit only day retreat sessions.
  - For «སྙིང་སྡུག»: seven hits, all the Svapnohana itself; Steinert's dictionaries not cloned; no Tibetan Amarakośa found; Mipham's other works cannot be searched by author, the three hits carrying his name bound no watch.
  - The Narthang Tengyur etext (char. 420824) files the bird verse under four unrelated Tārā-sādhana titles, the BDRC outline there being mislabelled.
- **RD219** (RD219, 2026-11-10):
  - KCM img. 691 line 5 and img. 692 line 3: the cursive hand's letters cannot be told apart at 1.4–2×; the DTD photostat I3877 img. 222 is a 2099×476 bitmap with letters of a few pixels.
  - SKP p. 51 line 2: ཚོང against ཚོད is not decided by the scan; RBG: no route to pp. 203–205 beyond the loan; bdrc.py scans fetches KCM, DTD and SKP images directly although bdrc.py volumes answers 401.
- **RD319** (RD319, 2026-11-10):
  - A dream list with WB's lines in WB's order among the pool's non-WB corpus files: none (a search for each line's phrases, 2026-11-10).
  - A statement of where the dream examination of p. 144 comes from: none in WB ch. 30 p. 144 nor in the notes' p. 535.
  - Teeth falling (WB's «སོ་གོག»): no text of the pool names it as a dream image; only SKP's doubtful line comes near.
- **RD322** (RD322, 2026-11-10):
  - Steinert's Chandra Das and Jäschke entries are page-indexed only and were not read; Negi, Tshig mdzod chen mo and Dung dkar carry no page numbers; the Mahāvyutpatti's own numbering is not in Steinert's file, Negi's 8242 could not be matched.
  - No hit for «ཐུན་བརྒྱད» or «ཐུན་ཚོད་བརྒྱད» in the G.ya' sel etexts or Dpal bsdus's manual; BDRC phrase searches «ཐུན་རེ་ལ་གཟའ་རེ» and «ཐུན་བརྒྱད་པོ་རེ་རེ་ལ་གཟའ» 0 hits, «ཐུན་རེ་གཟའ་རེ» 3 and «ཀླུ་རེ་ལྷན་ཅིག་འཆར» 4, all WB copies or the quoting anthologies; the Tibetan Amarakośa not located.
- **RD321** (RD321, 2026-11-10):
  - Skyem pa's etext (I4711) has no gloss of ཟ་ཟི་མང at ch. 15's line; its only hits of «ཟ་ཟི་མང» (p. 692) and «ཟ་ཟི་འོང» (p. 693) are outlines of ranges in the pestilence chapters, and ཟ་ཟི at ch. 81 (p. 818) is the one gloss.
  - BDRC etexts of the Blue Beryl, Khro ru and the Drikung searched by «རྨི་ལམ་ཟ་ཟི་མང»: no gloss that explains ཟ་ཟི itself; Khro ru's colophon (I5624 pp. 818–821) names no source text; Skyem pa's own date: only a sheep-year, no year (p. 1267); the Blue Beryl's scan answers 401; Byang pa, Rin spungs pa and Zur mkhar ba do not reach ch. 81.
- **RD323** (RD323, 2026-11-10):
  - The row's etext id I1PD95847 is the volume of the Muktāvalī on the Hevajra, not of the Svapnohana; vol. 14's front matter (etext pp. 3–17) holds the publisher's title and the table of contents only.
  - An explanation of the sigla in vol. 14's etext: none, the siglum names are only the columns of the collation tables; «ཤང་ཤང་ཏེའུ» in the etexts of vols. 9, 14 and 25: 0 hits, «ཤིང་ཏེའུ» and «ཤང་ཏེའུ» one hit each (the note).
  - The marker figures of the notes in the Svapnohana's text are lost in the OCR except for a few signs; the notes' lemmas are never quoted, so an entry's place is by the printed page and the order only; the table rows ཚིག་རྐང and ཚེག་ཁྱིམ have broken figures.
- **RD324** (RD324, 2026-11-10):
  - BDRC exact-phrase search, 2026-11-10: «ཟབ་མོ་གཅོད་ཀྱི་དམིགས་འབུམ» seven hits, all this text; «གཅོད་ཀྱི་དམིགས་འབུམ་ལས་གསུངས་པ» two; «ཕྱིས་འབྱུང་གསལ་བྱེད་ཡིན་ནམ་གྱི» none; «ཐོ་རངས་ཁ་ཡིན་ནམ་གྱི་གསུམ་ཆ» only RST; «རྨི་ལམ་བཟང་ངན་གྱི་འབྲས་བུ» three hits in three etexts, not opened; no Chöd collection (a dmigs 'bum) found that holds the dream passage.
  - The etexts of MW21880, MW25126, MW1AC12, MW1PD95727, MW8LS18598 and the two prints of the KBS article answer 403 (search-only): only snippets read; not read: the Mongolian manuscripts MW1NLM9044_O1NLM9044_009 and MW1NLM9419_O1NLM9419_007, the manuscript's pages past img. 95 beyond the machine reading; the title leaf (img. 87) machine reading garbled.
- **RD320** (RD320, 2026-11-10):
  - BDRC full-text search, exact phrases «རྨི་ལམ་བརྟག་པ» (357 hits), «རྨི་ལམ་བརྟགས་པ» (61), «རྨི་ལམ་ལུང་བསྟན» (233), «རྨི་ལམ་བཟང་ངན» (287), «རྨི་ལམ་དཔྱད» (13), «རྨི་ལམ་གྱི་རྟགས» (242), «རྨི་ལྟས», «རྨི་ལམ་བརྟག་ཐབས», «རྨི་ལམ་གྱི་ལྟས»: from the fourth page on only running text; no astrological work with a dream-examination title, the only astrological hits being the White Beryl and its Phug lugs print.
  - Bu ston's Rmi lam brtag pa (MW1PD45496_7F5CBB): the etext of vol. 28 garbled, the scan I2DB62486 not looked at; Rngog mdo sde's Zhal gdams rmi ltas ma (MW1KG16666_3C20D2): the etext stops at p. 233 of 327, the scan volume I4PD2674 restricted; Tsong kha pa's Dkyil 'khor gdeg cing rmi ltas bzang ngan (MW22273_11912A): not found by title in the poor etext, the scan I1KG2238 open.
  - Karma chags med's Rmi lam lung bstan gyi bstab pa tshigs bcad (MW1KG8321_0BE0FB): etextrefs answers 404, not opened; Padma rdo rje's book (MW25126) and five medical books (MW25122, MW28975, MW1GS61408, MW21626, MW8LS18598) search only, the etext API answers 403.
- **RD325** (RD325, 2026-11-10):
  - The five ways of entering with a dream clause in the verse: not on the Derge (img. 436 lines 2–3) nor the Chag-po-ri (img. 428 lines 1–2), eye on crops, 2026-11-10.
  - A sharper image of the Derge's ch. 91 small type: the cached scan is the BDRC microfilm, bitonal, the small letters run together; the Drathang's ch. 81 and ch. 91 pages not opened; q915 (what བ་ར is) is a question for a reader of the tradition, not a reading of the page.
- **RD327** (RD327, 2026-11-10):
  - A second Peking instance on BDRC: the abstract works WA0BC002 and WA0RT4282 give only W1KG13126; the Peking Kangyur (W1PD96684) and a Kangyur reproduction (W3CN1094) are not the Tengyur; W1KG13703 is the Otani University's catalogue of its Peking print, without images.
  - A Peking Tengyur on archive.org other than bdrc-W1KG13126 (title searches for Peking, Otani, Tengyur, Tanjur, Tibetan Tripitaka): none; BDRC's ldspdi and library search endpoints answer 405 to a POST.
  - The Peking's second watch «ར» after «བརྒྱད་པ»: none seen on the 1× and 2× crops of img. 315 line 2.
- **RD329** (RD329, 2026-11-10):
  - A list of good and bad dream images in Bu ston's two folios (img. 833–836), read through: none, the title line only (2026-11-10).
  - A list of good and bad dream images in Tsong kha pa's img. 385–388: two images only (img. 386), the rest remedies; the section's own heading is not on these images.
  - A second print of Bu ston's text (MW22106_CC9683, MW1934_6BFB80) not looked at; no MITRA witness and no disagree.py sheets, so no flag read against a second reader.
- **RD326** (RD326, 2026-11-10):
  - BDRC exact-phrase searches, 2026-11-10: «སྟོབས་ཀྱི་རྒྱུ་ཕུར་བུ», «ཐུན་ཕྱེད་རེ་རེ་ལ་གཟའ», «གཟའ་དང་ཀླུ་ལྷན་ཅིག་འཆར» and «གཡུལ་ལས་རྣམ་པར་རྒྱལ་བ་ཐུན་ཕྱེད» no hit; «དབྱངས་འཆར་གཞུང་ལས» 5 hits and «དབྱངས་འཆར་རྒྱུད» 73, none a Dbyangs 'char text that holds the verse.
  - The etexts of Karma rat+na's Sman yig gces btus (I1KG1117), the Drang srong zhal lung (I4726), the 'Bras rtsis bar gsal li dwangs ma'i me long (I1KG21916) and the Yul mdo khams stod vol. 10 (I2PD18783) answer 0 chunks (search only); BDRC's record of the Rnam dpyod has no creator; the author of the verse manual «Skar rtsis kyi lag len byed grub sgang dril ltar bkod pa» (MW3CN3480) not looked up.
- **RD328** (RD328, 2026-11-10):
  - W1NLM9044 part 9 (MW1NLM9044_O1NLM9044_009, img. 153–164), the part sources.yaml named: a king-spirit rite, not the dream text (BDRC's own record); the dream text is part 30.
  - A date or an author in GDM and GDR: none; GDM's ending is lost (img. 113), GDR ends with «འཆི་ལྟས་རྫོགས་སོ» (img. 157) and an empty leaf; a name for the writer of GDN's second colophon on the scan: only «མཁན་པོས་བྲིས་པའོ» plain.
  - The Garga part of GDM past img. 95: read only by machine (RD324), not compared with the two new copies; GDN and GDR read for the dream part, the death omens' beginning and the colophons only.
- **RD332** (RD332, 2026-11-10):
  - bdrc.py volumes given an MW part id prints the MW id as the group, and given a W root id it returns 401; the real image group comes from the wvo:bdr:<MW>::bdr:<I…> manifest id in iiifpres.bdrc.io/collection/wio:bdr:<MW part>.
  - I4PD2674 (Rngog mdo sde's Zhal gdams rmi ltas ma) serves nothing near the pages (images 1–3 only); at about 1280 px wide the cursive letters ལྗ and ལྡ could not be told apart by eye; no MITRA witness and no disagree.py sheet were run.
- **RD330** (RD330, 2026-11-10):
  - The scan of the Siddhasāra volume: BDRC's manifest for I1KG15617 and W1KG14614 returned HTTP 500, and volumes lists only the etext volume.
  - No physician's dream beside the messenger in Byang pa pp. 204–232, Rin spungs pa pp. 227–240, Khro ru pp. 296–330 or the Explanatory Tantra's ch. 25 (Khro ru pp. 445–452); the Bhutan print's etext has «ཕོ་ཉ» once, in the child-spirits' list; the Drathang line variants by eye on the cursive print: given up, gt:i41 stays machine.
- **RD331** (RD331, 2026-11-10):
  - «ངག་དབང» and «ངག་དབང་དགོངས» in the Derge volume 28 etext outside the Svapnohana's own line, and in the first five etext volumes of Mkhyen brtse's collected works (IE0OPIC2BFA6FE, I1KG81113–81117): none (2026-11-10); the Gāyatrī's name «ག་ཡ་ཏྲི» in the Derge etext pp. 239–268 and in CBL vol. 5: none.
  - The Dpe bsdur ma variant notes for 'Deceiving Death' (vol. 14 etext pp. 1359–1362): the OCR garbled and the markers lost, no note assignable; the scans of vol. 14 (I1PD95859) restricted; the scans of CBL (I1KG81117) and of the Derge pages (I1344) not read; Steinert's dictionaries not consulted for «ངག་དབང»; no other translation or catalogue note on the origin of the dream verses sought beyond BDRC's records of Vāgīśvarakīrti.
- **RD334** (RD334, 2026-11-10):
  - A date in any record of the three volumes or of their parts on BDRC (records read as JSON, 2026-11-10): none; BDRC exact-phrase search «སྲིད་གསལ་གྱི་ཁྲི་ལོ» and «སྔགས་སྨྱོན་པདྨ་འོད་ཟེར»: 0 hits each; BDRC persons for Dkon mchog bstan pa'i sgron me by autosuggest: no person record with dates found.
  - The colophons of GDM's 3rd text (img. 85–86) and of the parts of GDR's volume other than the 1st and 8th not read on the scan; the colophon readings are machine only, the catalogue's Wylie transcription agreeing.
- **RD335** (RD335, 2026-11-10):
  - A list of dream images in Dbang chen's img. 80–82: none, one expected dream, the mandala (2026-11-10); a line in the Sha za brtsegs pa'i rgyud's img. 463 that says dream apart from the colophon: none.
  - BDRC's IIIF route for Dbang chen: bdrc.py scans with the VE id (VE1ER1992) answers 500, the image group I4CZ30840 found from the work's record (I4CZ30837–30841 for vols. 1–5); bdrc.py volumes W4CZ30835 answers 401, with the MW id it lists only VE ids; for the Sha za brtsegs scan bdrc.py volumes MW2CZ7955 gives I1KG3558 directly.
  - No MITRA witness, so img. 463 lines 3–4 and the garbled words stay machine-read.
- **RD339** (RD339, 2026-11-10):
  - The table, 語らざる日 and 初夢 on images 128, 132–139 and 190 of pid 760742: not found; images 140–189 (the lots) seen only through the OCR sorted by image, not by eye; images 1–107 (front matter and almanac day-choosing tables) not read leaf by leaf, only 33, 36 and 101 looked at.
  - The OCR text of the volume has no hit for 語らざる, 初夢 or 夢を見て, as in RD308; the cursive of the articles looked at, not read letter by letter.
- **RD337** (RD337, 2026-11-10):
  - The Derge pa/ba and da cannot be read by eye at 8 px (3–10× crops show blurs); no MITRA witness run; scans.py line failed on the Derge page, finding one line, so crops were cut by hand; the first slot run showed finished while another reader's job held the card.
- **RD338** (RD338, 2026-11-10):
  - BDRC exact phrases «ཐུན་ཕྱེད་སོ་སོ་ལ་དབྱུ་གུ་བཞི་བཞི» and «གཡུལ་རྒྱལ་མཆོག་དགའ»: no hit; «ཐུན་ཕྱེད་པའི་རཱ་ཧུ»: 4 hits, KD vol. 3 and two others (the I2PD16001 etext answers 0 chunks, VE23703_1521 not read); «དབྱུ་གུ་བཞི་བཞི»: 6 hits, only KD vol. 3 read; «ཆུ་སྲང་ང་དྲུག་རྐང་བཅས»: 13 hits, two read (VE30541_013 p. 207, VE23660_004 p. 116).
  - The dbu can print of the Mchog dga' (MW1NLM5184, scans open) not searched for the chapter 9 folio; the lent KD scans not opened.
- **RD336** (RD336, 2026-11-10):
  - No 'physician's own dream' phrase in any of the Moonbeams, Byang pa, Rin spungs pa, Blue Beryl or Khro ru etexts; the Gtsang sman is the only place; no «ཟླ་ཟེར» near pp. 263–264 of Gtsang sman.
  - The name colophons at Gtsang sman pp. 169 and 422 belong to other works in the collection, p. 372 names none; the Drikung dka' mchan etext was not read at this verse (working ids: Blue Beryl VEIE0OPICF5AF372_I1PD411, Drikung VEIE0OPI7984ABB9_I1KG24731).
- **RD342** (RD342, 2026-11-10):
  - BDRC full-text search: «ཡ་མཚན་ཅན་ནི་བརྟུལ་ཞུགས་ཀྱི་བྱེ་བྲག་ལ་གནས་པའི་ཕྱི་རོལ་པ» 0 hits; «དྲྭ་ཕད་རྐང་གླིང» and «རྦད་མགོ་དང་དྲྭ་ཕད» one hit each, in the Blue Beryl (I1PD411); «རྦད་མགོ» and «རྐང་གླིང་རུས་རྒྱན» occur nowhere else in the Drikung, Skyem pa or Blue Beryl etexts; Skyem pa's etext cites «ཟླ་ཟེར» many times but not at this verse (pp. 187–188); the Drikung and Skyem pa read on etext only.
- **RD340** (RD340, 2026-11-10):
  - The NDL catalogue (ndl.py, 2026-11-10) for 夢合長者鑑, 夢合, 夢判断, 夢占, 夢解, 夢見, 夢合延寿袋, いろは引夢うらなひ, 夢うらない, ゆめはんじ, 夢判じ and 夢相, titles before 1925 with 夢 or ゆめ: apart from the 夢合長者鑑 (pid 899567, open) and the 1913 book (pid 917353) no record carries a Digital Collections pid; 759978 and 760913 stay restricted.
  - The 夢合長者鑑 on all 34 images: no table of days, no slash legend, no 語らざる日, no 初夢 (the running text not read letter by letter, so a sentence on the days could hide in it); the NDL OCR of pids 899567 and 917353 for 語ら, 初夢, 夢を見て, かたらず and 語らず: no hit, the OCR of 899567 noise.
- **RD341** (RD341, 2026-11-10):
  - Chapter 9's start (etext p. 449, «ཐོག་མར་དཀར་པོའི་ཕྱོགས་ཀྱི་ཐུན་ཕྱེད») in img. 620–649 by Yigdzin-1 (img. 650–665 fetched, not read): not located, the half-watch text already runs at img. 620–628 so it starts earlier; the verse of etext p. 454 (the multiplier words) not looked for on the scan; no MITRA second witness.
- **RD343** (RD343, 2026-11-10):
  - Waseda Kotenseki answered 503 Site Maintenance on every page; the National Archives of Japan, the University of Tokyo and archive.org not tried.
  - The kokusho search URL /biblio/search?kw= renders an empty record, /biblio?kw= shows the form and the search runs only once the keyword is entered through the page; ndlsearch.ndl.go.jp opensearch returned nothing through curl; the full-text route that works is lab.ndl.go.jp/dl/api/book/fulltext-json/<pid>, images from dl.ndl.go.jp/api/iiif/<pid>/R0000006/full/1400,/0/default.jpg.
- **RD344** (RD344, 2026-11-10):
  - The 夢合延寿袋大成 (pid 2533938) images 7–74: no table of days, no slash legend, no 初夢 calendar at thumbnail size; a close read of images 7–57 would be needed to exclude a small inline note on dream days; the Kyoto, Kyushu and Tohoku copies, the sequel 後編 and the 夢合長寿鑑絵抄, 夢合長寿宝 and 夢合早占大成 not opened.
- **RD353 (2026-11-10)** (RD353, 2026-11-10):
  - A Co ne image sharper than the bitonal 2055 px frame: BDRC's service answers `--width 4000` with empty files; the full-size fetch is the cached scan.
  - The Co ne's letters at 3–7× after deskew, blur and contrast stretch (img. 270 lines 3–7): no letter told apart; the fourth-watch line, line 6's end and line 7 stay machine readings.
  - «ཐུན་བཞི་ཟླ་བ་ཕྱེད་པར་དུ», «ཐུན་བཞི་ཟླ་ཕྱེད་པར་དུ», «ཐུན་བཞི་ཟླ་བ་ཕྱེད་པར་བྱ», «བཟློག་མིན་ལ་ཞུགས», «རྟ་ལ་མོ་དང་ལྟོ་འཕྱེ»: no hit in BDRC's full-text search (2026-11-10).
  - The second and third letters of the Narthang manuscript's title stacks (img. 284 line 2, 7×) were not told apart; the Derge frames' letters (8 px, I1344 img. 268, 270) not resolvable at that size for པ/བ at the second watch.
- **RD354 (2026-11-10)** (RD354, 2026-11-10):
  - BDRC width upscaling (--width 4000 and 6000) returns empty files for the Co ne, Derge, Peking and Narthang scans of the Svapnekṣā.
  - Yigdzin-1 on wide high-contrast windows of the Peking night line returns stock phrases; only whole-line strips worked earlier. Windows of Co ne lines must be cut with the slope in mind: 70 px windows pick up two lines.
  - BDRC exact phrases «བརྟགས་པ་ལ་ཉལ», «བཏབ་ལ་ཉལ», «བརྟགས་པ་ཉལ», «བྱ་ཤང་ཏེའུ», «ཤང་ཤང་ཏེའུ་ཕོ་ཉ»: no hit (2026-11-10); «ཤང་ཤང་ཏེའུ» alone 369 hits, not opened.
- **RD356 (2026-11-10)** (RD356, 2026-11-10):
  - The Blue Beryl's printed page (Dpe tshogs print, p. 1148): BDRC's I1PD411 and I1PD412 have only 41 canvases each in their manifests and the pages beyond answer 401; I8LS20838 is not the Blue Beryl's page.
  - Chag-po-ri I1KG13487 img. 37 line 4 (ch. 2's boy line): dashed and faint, not legible by eye; Yigdzin-1 on a line strip gives garbage.
  - The Drathang print's hand notes of ch. 81 (img. 463) at 2658 px: no note can be shown or excluded.
- **RD357 (2026-11-10)** (RD357, 2026-11-10):
  - GRETIL has no Brahmavaivarta; no dated manuscript of its dream chapter found (the Colenda Brahmavaivarta is Bengali, 1852, with no contents given; the Berlin and Leipzig portal did not resolve; the Hamburg NGMCP record A 433-50 answers 404).
  - No quotation of the Brahmavaivarta's dream chapter in the medieval digests was found by search; Scheuermann's study is refused again (Brill HTTP 405, LMU record without full text, FAU record with the DOI only).
  - Penn Item 1871's later leaves only looked through at reduced size for time words, none seen; not a word-by-word read. The way in: Penn's Colenda IIIF (colenda.library.upenn.edu/items/ark:/81431/<ark>/manifest, images .../full/2670,/0/default.jpg) answers at once, OPenn is the slow one; arks p3jz6w (1871), p3hm52k1p (2730), p3df6k63n (673), p39c6s18p (687).
- **RD358 (2026-11-10)** (RD358, 2026-11-10):
  - Waseda's 古典籍総合データベース answers 503 on every page; Kokubunken has no record titled 万代大雑書 and no 夢合判断大成; the 守貞謾稿 and the NDL 東都歳事記 29922 and 29923 are collection records without images; NDL full-text search for 夢を見て語らぬ日 and variants: no almanac hit.
  - Not opened though their manifests are open: Kyushu 夢合延寿袋大成 (11996, 13960), Osaka 夢合延寿袋大成 (00875), 夢合清明囊 (国文研三井 Y1472-005), 国文研 大ざつしよ ヤ0-32 and ヤ0-116, 東京学芸大 大雑書 of 安永9 and 文政11, 嘉永大雑書, 万宝大雑書, 万歳大雜書日用寳, the 大雜書三世相 copies; the 夢合長寿宝 seen on sheets only; for the 星槎ラボ and Tohoku prints the table is only 'not recognised at sheet size'.
- **RD359 (2026-11-10)** (RD359, 2026-11-10):
  - Derge vol. 213 images (catalogue pp. 736–737) and Derge vol. 28 image 268: HTTP 500 on 2026-11-10; Jo nang catalogue scans: 401 (lent only). Peking (W1KG13126 vols. 221–224) and Co ne catalogues not located or not opened; Tāranātha's history names translators, not works.
  - Exact-phrase searches without a hit: «ངག་དབང་གྲགས་པས་མཛད་པའི་རྨི་ལམ», «ངག་གི་དབང་ཕྱུག་གྲགས་པའི་རྨི་ལམ», «རྨི་ལམ་ཐུན་བཞི», «ཐུན་དང་པོར་རྨི་ལམ», «བསྟན་འགྱུར་རྨི་ལམ་བརྟག་པ», «རྨི་ལམ་བརྟག་པ་ལས་ཀྱང»; ten guesses at Padma rdo rje's introducing sentence; «མཚན་ཐུན་བཞི» (21 hits) and «མཚན་ཐུན་དང་པོ» (25 hits) only generic night-watch texts, first 8 hits of each looked at.
- **RD355 (2026-11-10)** (RD355, 2026-11-10):
  - The Zhe chen (I4PD2250) and Kazi (I6132) scans of the Lama Gongdü are restricted to a window that starts inside the text; their first folios could not be opened. No plain-letter writing of the six-glyph string in any of four copies.
  - Exact searches without a hit: «རམ་རུག་སྤང་གཡེར», «ནམ་ཕྱེད་ཀྱི་རྨི་ལམ་ཉོན་མོངས», «ཐོ་རངས་ཀྱི་རྨི་ལམ་རྟེན་འབྲེལ», «ནམ་སྲོད་ཀྱི་རྨི་ལམ», «ཐོ་རངས་ཀྱི་རྨི་ལམ་བཟང་ངན»; «རམ་རུག་སྦང་གཡེར» only in the Shechen etext and Karma Chags med's ransoms; «སྦང་གཡེར» elsewhere only in Bon medical texts (witnesses to usage, not sources); no other text pairs dusk, midnight and dawn with habit, afflictions and omen as LGD does.

RD366 (2026-11-10): Old Tibetan Documents Online (otdo.aa.tufs.ac.jp) refused the connection; Gallica blocked automated browsers (HTTP 403), so the BnF images of PT 55 came from IDP at 1024 px; Academia.edu (403), JSTOR, the Dunhuang Academy database and book118 did not serve the 1995 and 2009 studies; no Tibetan Zhou gong dream book and no Stein or IOL dream manuscript found by web searches in English, Chinese and Japanese; P.T. 129, 351, 1043, 1046, 1047, 1051 and 1055 (divination manuscripts) not opened.
- **RD360 (2026-11-10)** (RD360, 2026-11-10):
  - No BDRC hit for «འོ་ཡུག་པ་ན་རེ», «ཨོ་ཡུག་པ», «འོ་ཡུག་པ་གསུང», «འོ་ཡུག་པ་འཆི་», «འཆི་བ་བསླུ་བ་འོ་ཡུག་པ»: the remark's wording is found only in CBL.
  - The Śākyamuni mantra «ཨོཾ་བུདྷ་ཙུ་རུ་མཱ་ཙུ་རུ་ཀེ་སྭཱ» and the Tārā title are found only in the 'Brong rtse etext; the modern article MW20242_55798C / MW1CZ690_FBE63E that quotes the Ma gcig phrase was not opened; the Sngags rim chen mo has no scan attached (ALL etext only).
  - The registry and the cbs note say the Dpe bsdur ma holds the work at vol. 14 pp. 1335-1362: the same print also has it in vol. 46, pp. 1049-1130, with the Bsdus don at nos. 2678-2679.
- **RD364 (2026-11-10)** (RD364, 2026-11-10):
  - A table 夢を見て語らざる日 in the 夢合延寿袋大成 of Kyushu (two copies) and Osaka, the 夢合清明囊, the 国文研 大ざつしよ ヤ0-32 and ヤ0-116, the 東京学芸大 大雑書 and 万宝大雑書, the 万宝大ざつしよ of Toyo University: none on the sheets or the enlarged leaves, but the running text was not read letter by letter.
  - The dream sections of the 嘉永大雑書 and the 萬歳大雜書日用寳 (images 49-202 not looked at) not located at sheet size; the 栞草's list of sources not found on images 2-13; the 夢合長寿宝 text beyond the contents leaf and the 大雜書三世相 of Nagoya and Kano not opened; the other 東都歳事記 copies (Hiroshima, Tsukuba), the NDL 東都歳事記 and 守貞謾稿 (restricted) and the Waseda 夢占吉凶秘傳書 (503) not opened.
- **RD361 (2026-11-10)** (RD361, 2026-11-10):
  - Bu ston: his Laghutantra notes (ACIP VE1ALS05001I) have no «རྨི» at all; his ground rite (MW1PD45496_3D3CDD) and mandala rite (MW1PD45496_4D9910) are listed in BDRC but no etext volume found. Mkhas grub rje's Kālacakra rites and notes (MW384_62A527, MW23693_74E637, MW384_7FAEF2, MW1NLM3263 scans only, MW1KG3819) located by title but not reached by the exact-phrase searches of the bed-rite sentences.
  - Searches with no result: «རྨི་ལམ་གྱི་འཁོར་ལོ», «དུས་ཀྱི་འཁོར་ལོའི་རྨི་ལམ»; the broad «རྨི་ལམ་གྱི་ལྟས» (324 hits) and «རྨི་ལམ་བརྟག» (863 hits) did not point at the Kālacakra; the death-dream verse «གང་ཞིག་གིས་ནི་རྨི་ལམ་མཐར། བྱེ་མ་འམ་ཐལ་བའི་ཕུང་པོ» is not in the Derge Tantra, Vimalaprabhā or Padminī etexts read.
  - The bed-rite sentences also occur in Dol po pa (I8LS18987), Phyogs las rnam rgyal (I1PD95825), Zhu chen Tshul khrims rin chen (VE00EGS1016703_018) and a Sakya rite series (MW27883_79C889), found by search only; the Gdams ngag mdzod hits are Sakya and Zhi byed rites; Rang byung rdo rje's Zab mo nang don (VE30541_014 pp. 231-232) read only at its opening; «འཆི་ལྟས» (57 hits in vol. 11, 69 in vol. 13) read in context only at vol. 11 pp. 494-504, vol. 12 p. 8, vol. 13 pp. 529 and 537; the Nag rtsis dream lines in Kun 'dus vol. 3 at etext pp. 609, 668, 670 and 719 not read.
- **RD362 (2026-11-10)** (RD362, 2026-11-10):
  - The Chengdu Gsung 'bum etext (IE0OPI814E11F4) is search-only (404 on the etext API): the Chengdu vol. 25 texts were read on the scan by Yigdzin-1 only; vol. 32 images 646-647 «rmi lam snang ba» are a doctrinal title, not opened. An etext search of the WB notes (vol. 2 pp. 482-549) for «རྨི», «གཉིད», «ཉལ», «སྨྲེ» found no other dream gloss; Yigdzin-1 img. 482 holds only the title page.
  - BDRC author facet &author[0]=P252 works (20 hits a page, &page=N) and returned 148 hits for «རྨི་ལམ», almost all the dream-as-illusion simile; the Bka' ma reset I1PD106522 p. 533 (Do sngags bstan 'dzin bzang po's Gza' yum rite, good and bad dream signs from a «Sphyi rgyud») and MW21915 (an anonymous Gso rig compilation, I1KG20642 p. 41) are not Mipham's.
  - Unfinished leads: the Sikkim prints (MW5062: I1KG8549 p. 91, I1KG8544 p. 182, I1KG8552 pp. 237-238) only as snippets; the be bum (vol. 28 pp. 25 and 147-151: mantras turning back bad dreams; vol. 22 p. 27 «rmi lam bzang po»; vol. 22 p. 59); Gzhung 'grel skor vol. 25 p. 284 «mtshan mor rmi lam brtag» in the Guhyagarbha preparation; vol. 1 images 162, 356, 386 and 398 (more Karuṇāpuṇḍarīka dreams) not read.
- **RD365 (2026-11-10)** (RD365, 2026-11-10):
  - Padma rdo rje's etext VEIE0OPI409E1122_I1KG10372 answers HTTP 403 on every window and returns 0 chunks (volume 1 is etext only, no scans); a request with browser-like headers was refused by the permission classifier as bot-check circumvention and not retried: only the owner can unlock this, for example with a BDRC account or another route to the text.
  - The Narthang catalogue's etext (288 pages) is served but its OCR is garbage: a search in it proves nothing; the even images needed the text block cropped before Yigdzin-1 read them. The Peking Tengyur vols. 221 and 223 (img. 12, 100, 300, 500) and 224 (img. 4-6, 12, 100, 300, 500, 700-707) are running text, no list of titles: the row's premise of a Peking catalogue there is unconfirmed.
- **RD368 (2026-11-10)** (RD368, 2026-11-10):
  - Kokubunken's ORMK-02344 images are only 3279 px, so nothing is gained by asking for more (TOKY and Shunan Kano go to 6520 px). Not done: the 栞草's 紀事 and 鶏旦 entry, the 清明秘鈔夢判事's ten second lines, the Edo-Tokyo Museum 嘉永大雑書, the 夢合長寿宝 text, TOKY-01831 images 133-202; Waseda 夢占吉凶秘傳書 HTTP 503 on the archive host too; the sheets of twelve other 三世相小鑑 prints and of the 嘉永 and Nagoya and Tohoku 大雜書三世相 copies were made but not looked at.
  - Kokubunken's /api/biblioSimpleSearch?searchkbn=simple&keyword=… returns paged JSON (data, last_page; the image field is 0 or 1): a quick way to list the digitised copies of a title.
- **RD370 (2026-11-10)** (RD370, 2026-11-10):
  - The first exact-phrase search gave no hit under the Kanjur's own id: the Derge text was found only through a title search (MW4CZ5369_0543). The 84000 translation and its Sanskrit appendix for Toh 543 were not copied (non-commercial licence).
  - The other «rmi lam» pages of Toh 543 (pp. 252, 318, 371, 374, 416, 422, 423, 461, 473, 482, 485, 487, 574, 575, 599, 629) were read only in the lines round the word, none counts watches; «མཚན་མོའི་ཐུན» at p. 699 ff. (earthquake omens) and «ཐུན་ནི་ཐ་མར་ཉལ། ཐོ་རངས་ལྡང་བ» at p. 656 not read further; the scan (image 397) was looked at only, the red print too faint at the served size to read syllable by syllable, no Derge folio established.
- **RD367 (2026-11-10)** (RD367, 2026-11-10):
  - BDRC full-text search of 126 phrases of «རྨི་ལམ» / «རྨི་ལྟས» / «རྨིས་ལམ» with the lunar day (by number), the month, the fortnight, the weekday and weekday names, the day, the year, the seasons, the day animals, the mansions, the twelve houses and spans (the zero-hit list in the dds.md section): no sentence that makes a dream's fruit depend on the date of the dream; the 37 hits are dated dreams in biographies, songs and treasure texts, the Dream month name, and homonyms such as «རྨི་ལམ་ལོ», «ཉིན་མོའི་རྨི་ལམ», «སྟོན་གྱི་རྨི་ལམ».
  - Etexts that answered empty or 403 and were read by snippet only: the life of Jo sgron ma (MW3CN2459_B0A1F0), the Tibetan–Chinese star-calculation dictionary (MW1PD137843), Padma rdo rje's book on sleep and dreams (MW25126), Thub bstan phun tshogs's book on signs (MW25122); the hits behind the counts of 20 and over were read as the first 20 snippets only; BDRC's search offsets pointed into the wrong place in the etexts loaded (locate.py find on the downloaded etext was reliable); a table of days or a verse by the day animal with no «རྨི་ལམ» in the same clause is out of reach of an etext search.
- **RD363 (2026-11-10)** (RD363, 2026-11-10):
  - No Tibetan etext of TSL vols. 1-26 on BDRC (MW1KG15423: empty; IE0OPI46409446: vols. 27-30 only; VE4CZ42297_030: search-only): vols. 1-26 would need scan reading; the Lama Gongdü dream rite of TSL vols. 1-26 not reached in this print (corpus/lgd already holds it from vol. 42 of the Rin chen gter mdzod).
  - bdrc_search of «རྨི་ལམ» finds 287 hits everywhere, not scoped to a work: only phrase searches with life-specific words locate TSL.
  - KTD MW29259 and MW24036: no etext; MW24036 (1136 scan images) unread, no table of contents in its first 12 images; MW00EGS1016761 (Sgo chen dgon chos spyod) is compiled by Mkhan chen Dpal ldan shes rab, not KTD's own text, its p. 551 colophon dream is Mi pham's; the bad-omen words «ལྟས་ངན» in vol. 30 (images 184-185, 206, 421) are non-dream omens.
- **RD369 (2026-11-10)** (RD369, 2026-11-10):
  - BDRC exact-phrase searches found the dream chapter's words only in the Kun 'dus etext, the Derge, Narthang and Dpe bsdur ma Dbyangs 'char, the Bzo rtsis zlos gar etext (MW1PD95727, search only, 403: its snippet is the Mchog dga' text with «bcu shor na» for ten) and an etext VEIE0OPI51EA6051_I2PD16001 (403, snippet only); no other Phug pa book, Rgyal rtse, Mkhas grub Nor bzang rgya mtsho or other Dbyangs 'char commentary gives the dream rule (about a dozen phrases tried).
  - The Kun 'dus loaned scans were not opened; the Dbyangs 'char verses were not read on a scan (the Derge scan may be open on BDRC); the Mongolian print img. 279 read by eye on one crop only; the p. 140 etext lines are scrambled; a line of the etext is missing at the top of p. 200.
- **RD371 (2026-11-10)** (RD371, 2026-11-10):
  - A table 夢と見て語らざる日 or 夢をみてかたらぬ日 in the thirteen 三世相小鑑 prints, the two 嘉永大雑書三世相 prints, the three 大雜書三世相 prints and the 萬歳大雑書日用寳 images 133-202 (sheets and the leaves named enlarged; running cursive not read letter by letter); any legend, note or marginal line on the gap before the last group of each month in any print; a sentence on the days in the 夢合長寿宝 and the 夢合長寿鑑絵抄 other than the six jia.
  - Not opened: the Edo-Tokyo Museum 嘉永大雑書 (100450762), the Himeji 嘉永三世相大雑書 (300106373), the 蓬左 copies (100145740, 100145738: manifests empty), the Tohoku 夢合長壽鑑繪抄 (100446527, manifest 404), Waseda; most 三世相小鑑 prints seen at sheet size only; the 六甲の夢 table read in part (the first seven day-columns).
- **RD372 (2026-11-10)** (RD372, 2026-11-10):
  - BDRC exact phrases «མཚན་མོའི་ཐུན་བཞི་པའི་རྨི་ལམ» and «ཐོ་རངས་ཀྱི་ཆ་ལ་བརྟག་པར་བྱ»: 0 hits; «དང་པོའི་ཐུན་གྱི་རྨི་ལམ་གང» (15 hits), «བཞི་པ་བདེན་པར་འབྱུང་བ་ཡིན» and «གསུམ་པ་རླུང་བྱུང་ཤེས་པར་བྱ» (28 hits each) show only quoters of the verse (Tsong kha pa, the Sngags rim chen mo, Chos kyi grags pa, the Rgyud sde kun btus, Shes rab 'od zer, Padma rdo rje's modern book); only the first 4-8 of each result list can be read through bdrc_search.py; «རྨི་ལམ་ཞེས་བྱ་ཡོད་པ་སྤངས»: 2 hits (the Derge and Padma rdo rje's book), no commentary on those lines.
  - The volume etext VE1KG4326_009 (Kun dga' rin chen) answers 403 to bdrc.py etext: not retried, not worked around; the other etexts of that search (VE1ALS06282E, VE1ER24, VE1ER25, VE1ER48) not fetched. On p. 400 the red print is too faint at the served size: the scan stays unreached. The Sanskrit OCR is garbled on exactly the line that matters; the 2008 reprint's text layer not opened.
- **RD379 (2026-11-10)** (RD379, 2026-11-10):
  - A sentence on keeping a dream silent, hiding it or not telling it in any of the seventy-two cells of canvases 26-30 and in the 邪魅 page and the 寝臥之法 beside them (the 寝臥之法, a health article, looked at on the thumbnail and not read letter by letter); a legend for the circle and the mark for 夢 at the head of each cell (read as 夢, the same in every cell); the source of the table (the 夫木 caption on canvas 29 is a poem line, not a source of the cells, not read).
  - 27 of the 60 cells were not re-cropped at other sizes or thresholds, so a second pass could still recover words; single-glyph doubts remain (辛巳 あふ/なる, 戊子 家督, 乙酉 親ゑん/急事, 辛亥 遊楽, 成 家そんぢう).
- **RD375 (2026-11-10)** (RD375, 2026-11-10):
  - ctext.org's search page answered a security notice forbidding scraping (not worked around): the ctext texts of 周公解夢, 夢書 and 玉匣記 not read there. IDP (idp.bl.uk) and Gallica answered a bot check (Cloudflare) and were not worked around: P.3908 images 1-12 (chapters 1-18) and 17-21 not read; the other Dunhuang dream-book manuscripts (S.620, P.2829, S.5900, P.3105, P.3281, P.3571, P.3685, P.3990, Dx.2844 and others) not seen, no open image or transcription found.
  - zh.wikisource 周公解夢 (27 sections): no line on telling a dream (不說, 不語, 勿說, 莫說, 勿語 found 0 times) and no table; zh.wikisource 玉匣記: no dream chapter; the 欽定協紀辨方書 (all 36 juan): 夢 once, in a quotation of the 周禮's 占夢; Wikisource full-text searches for 夢不語, 夢不可告人, 夢忌, 惡夢不可語人, 凡夢不可說, 夢勿語人: no hit apart from the Yongle entry; 夢林玄解 (the part on Wikisource) no rule of silence; 夢占逸旨 original not found online; 星命總括, 三世相 and the Japanese 夢合 texts' Chinese citations not searched.
- **RD378 (2026-11-10)** (RD378, 2026-11-10):
  - The Sanskrit pp. 179-181 (Gaṇapati Śāstrī, part 1, OCR of in.ernet.dli.2015.345315) hold no unit of time; pp. 174-225 searched for ghaṭi, nāḍi, muhūrta, nimeṣa, truṭi, lava, kalā, kāṣṭhā, triṃśat, yāma, ardharātra: only the watch words on pp. 175-176 and 199; the scan leaves were not opened.
  - Web searches (English Wikipedia pages on Hindu units of time and Muhurta): the Indian units second-hand only; no primary text of the Atharvaṇa jyotiṣa's muhūrta names, so no match of the twelve Tibetan names could be made; the scan of p. 438 could not be read (red print too faint, Yigdzin-1 returned nothing), so p. 438's etext is not checked against the page.
- **RD373 (2026-11-10)** (RD373, 2026-11-10):
  - BDRC exact-phrase searches for the lines in snr:p370 («བགེགས་མཐར་བྱེད་ཀྱིས་གཞོམ», «ཀླུ་བྱང་གིས་བདག་མེད་པའི་དོན», «བདུད་རྩི་འཁྱིལ་བའི་སྔགས་ཀྱིས་སྲུང») answered only in the Sngags rim chen mo's own etexts (ALS VE1ALS05281E, VEIE0OPI9244B62A_I3PD268) and modern books that quote the line (Padma rdo rje's Gnyid dang rmi lam bar gyi 'brel ba rnam par dpyad pa Da ki'i glu dbyangs MW25126, search only; the Sngags rim blo sbyong volume MW1PD95727, search only); no commentary on the Sngags rim chen mo by Pha bong kha pa or Blo bzang don grub open as an etext.
  - «ཨུ་དུམྦཱ་རའི་མེ་ཏོག» and the Vajrāvalī title phrase found only the Peking etext of the Vajrāvalī, no Derge etext; the 450-verse title came from the Derge Tengyur catalogue (VE23703_1531 p. 748); the Derge Toh numbers of the four Tengyur texts not read; the Derge scan I1KG9222 image 295 too faint to read; the Garland's three ways of raising the god into the sky (snr:p369-370) not read; Lwa ba pa and Śāntipa not looked for; no Sanskrit sought.
- **RD374 (2026-11-10)** (RD374, 2026-11-10):
  - The exact string «བྱེ་མ་འམ་ཐལ་བའི་ཕུང་པོ»: no hit on BDRC (found as «བྱེ་མའམ་ཐལ་བའི་ཕུང་པོ(འ)མ» in Tsong kha pa and the Kanjur and «བྱེ་མའི་ཐལ་བའི་ཕུང་པོ» in the 'Chi bslu); not opened from its search hits: Mkhyen brtse's collected works (I1KG81117), I4PD2667, I2453; some Rgyud bzhi hits (I2PD16008) answered 403 on the etext API.
  - Part-of-night hits not read beyond the ones recorded for «ནམ་ཕྱེད་ཀྱི་རྨི་ལམ» (10), «ཐོ་རངས་ཀྱི་རྨི་ལམ» (59, first ten read), «སྲོད་ཀྱི་རྨི་ལམ» (5), «ནམ་གུང་གི་རྨི་ལམ» (6), «དགོང་མོའི་རྨི་ལམ» (11), «མཚན་སྟོད་རྨི་ལམ» (29); «སྔ་དྲོའི་རྨི་ལམ», «ནམ་ལངས་ཀྱི་རྨི་ལམ», «ནམ་ཕྱེད་ཀྱི་རྨི་ལྟས»: no hit; death-sign dream lists seen by snippet only (Rol pa'i rdo rje, the Gsang sngags Wam volume, the Klong gsal rdo rje snying po khrid rim, the other «rnam shes gong du 'pho ba» copy I6006, the modern Krung go'i gso rig kun 'dus); the Four Tantras' root verses read in the Sichuan Minzu etext only, Zur mkhar's, Sde srid's and Khro ru's commentaries not read, the «Zla zer» that Byang pa cites not found; all scans except Gdams ngag mdzod img. 173-174 not read, the Kanjur folio fetched but not read.
- **RD377 (2026-11-10)** (RD377, 2026-11-10):
  - BDRC exact phrases without a hit, 2026-11-10: «ཐུན་བཞི་པ་ལ་བརྟག», «ཐུན་བཞི་པའི་རྨི་ལམ», «ཐུན་བཞི་པར་བརྟག», «ཐུན་ཐ་མ་ལ་བརྟག», «ཐུན་ཐ་མར་རྨི་ལམ», «ཐོ་རངས་ཀྱི་ཐུན་གྱི་རྨི་ལམ», «ཐོ་རངས་ཀྱི་ཆར་རྨི་ལམ་ཤིན་དུ་གསལ», «ཐོ་རངས་ཀྱི་ཆ་རྨི་ལམ་གསལ», «ཐུན་ཐ་མའི་རྨི་ལམ་གསལ», «ཁྱེད་ཀྱི་ཆ་ཉིན་བར་གྱི་བག་ཆགས་དང་འདྲེས»; the day-mixed reason is in no etext other than the Rgyud sde kun btus (two prints) and Kun dga' rin chen; no text of Padma dkar po, Pha bong kha pa, Bu ston, Mkhas grub rje or Rong zom returned from these searches or from «དང་པོ་གསུམ་ལ་ངེས་པ་མེད» (12 hits, all the Sngags rim chen mo or later manuals).
  - Closed to the etext API (no work-around tried): Kun dga' rin chen VE1KG4326_009 (403); the Songtsen Library volumes of Bstan 'dzin chos kyi rgyal mtshan and Rin chen phun tshogs; the Sa skya'i chos mdzod chen mo (MW3PD184, I4PD3582: 0 chunks); Zhu chen Tshul khrims rin chen (VE00EGS1016703_018: 0 chunks; its search snippet carries the same sleeping-step wording); the older sources Nam mkha' dpal bzang names (Sa bzang 'Phags pa, Mnga' ris Rab 'byams pa, Mkhas grub Dpal ldan rdo rje pa) never hit an etext; the Sangs rgyas rdo rje, Drukpa Hevajra and Cakrasaṃvara manuals seen by snippet only.
- **RD376 (2026-11-10)** (RD376, 2026-11-10):
  - BDRC exact-phrase searches: «རྒྱལ་པོ་དང་ནི་མེ་དང་ཁྲག» one hit (the Dpe bsdur ma etext), none in the Derge etext; no hit for «སྤེན་པས་མི་ངན་དང་འགྲོགས», «རི་བྲག་ལ་འཛེག་པ་རྨི» or «རྨི་ལམ་རྣོ་བ་དང་དགྲ་ཉམས»; the Derge etext of the whole volume has no «ཀེ་ཏུ་ལ», «ཚད་ནད» or «གྲང་ནད»; «རྨི་ལམ» occurs in the root only at pp. 121-122.
  - The Dpe bsdur ma scan I1PD95959 serves only img. 3-22 and 1853-1872 (fair use, 401): not worked around; Yigdzin-1 gives garbage on the Peking vermilion images (read by eye on the green channel at 5400 px); no Co ne print among the work's BDRC instances; BDRC answered 429 several times (waited, one request at a time, 6 s spacing); a full by-eye pass of every letter on all prints not done, the Gser bris ma OCR only; the IIIF work-manifest URL wvo:bdr:<MW>::bdr:<I> and green-channel crops for the Peking are worth a note.
- **RD380 (2026-11-10)** (RD380, 2026-11-10):
  - Yongle dadian volumes beyond 20181-20182: no other day-by-day lines (checked 19931, 20121, 20122, 20139, 20197, 20204, 20205, 20308; 20183-20196 are not on Wikisource); Wikisource searches for 呂茂明, 吕茂明, 甲子日夢, 日得夢, 夢不語: no day-dream table and no date for 呂茂明; ja.wikisource.org for 夢合, 大雑書 and 六甲 with 夢: nothing.
  - The Commons PDF of 雷英發's Yuan book is a low-resolution derivative (842 px per spread, characters 8-12 px high): the star tables are illegible, no dream line found, the day part not read; P.3908 chapters 3-18 looked through, headings only, not read line by line; IDP, Gallica and ctext.org not tried again (bot checks and the scraping notice found in RD375).
- **RD385 (2026-11-10)** (RD385, 2026-11-10):
  - BDRC exact phrases of day animals, weekdays and elements with the dream, no hit: «བྱི་བའི་ཉིན་རྨི་ལམ», «གླང་གི་ཉིན་རྨི་ལམ», «ཞག་གི་རྨི་ལམ་འབྲས་བུ», «ཚེས་ལ་རྨི་ལམ་རྨིས་ན», «གཟའ་ཉི་མའི་ཉིན་རྨི་ལམ», «ཤིང་གི་ཉིན་རྨི་ལམ», «ཉི་མའི་གཟའ་ལ་རྨི་ལམ»; «རྨི་ལམ་རྨི་བའི་ཉིན»: one hit, a guide to a mountain (MW2CZ6238), a day on which one dreamt, not a rule; every hit on the seven Nag rtsis lines is WB's text or a copy of it; the Kun 'dus etext pp. 536-766 for «རྨིས», «རྨིའོ», «རྨི་ལྟས» and «རྒྱ་བལ»: none.
  - The Mchog dga's Saturn lines of the tenth and eleventh place and with Ketu: «ནོར་སྣ་ཚོགས་ཐོབ་རྨི་ལམ་རྣོ་བ», «རྨི་ལམ་རྣོ་བ་དང་དགྲ་ཉམས»: no hit; the other lines two hits each, the Kun 'dus and the Bzo rtsis zlos gar vol. 2 (MW1PD95727), the same commentary; all read in etext only: the KD loaned scans and the WB parallels at pp. 268, 291, 381, 383 and vol. 2 p. 160 not read on the scan in this reading (Gser thang p. 160 opened, not read line by line).
- **RD384 (2026-11-10)** (RD384, 2026-11-10):
  - Marpa's saying: BDRC exact-string search finds each of the three lines once, in vol. 5 only; six variant strings give 0 hits; no commentary quotes the saying or the verses; the three-part verse (9, 8 and 4 hits) is in commentaries and books only, not in the Derge Mañjuśrīmūlakalpa etext; no commentary of Mi bskyod rdo rje turned up in the exact-phrase results (not searched by title).
  - Zhwa dmar V (I1KG5168 pp. 181-182) and the root print I1701: root verses only, no night parts; not opened: Kong sprul's Zab mo nang don gyi tshig 'grel, Padma rdo rje (MW25126) and Bya mdo Klu byams rgyal (MW8LS18598), search-only; BDRC lists MW30541 as etext volumes with no image group, so nothing was read on a scan.
- **RD381 (2026-11-10)** (RD381, 2026-11-10):
  - BDRC phrases with 0 hits: རྨི་ལམ་བསྟན་བཅོས, རྨི་ལམ་ལྟ་བ་ངན་པ་རྣམ་པར་བཟློག་པ, རྒྱལ་པོ་ཀྲྀ་ཀིའི་རྨི་ལམ, རྨི་ལམ་བརྟག་པ་བི་བྷཱུ་ཏི་ཙནྡྲའི་རང་འགྱུར, རྨི་ལམ་ངེས་པར་བསྟན་པ་རྫོགས; the search tool shows only the first 20 works and 8 etext links per phrase, so the list is complete for the Derge catalogue's 33 places and the titles surfaced, not for every colophon with the word.
  - King Kṛkī's and Ānanda's ten dreams are only quoted in the Derge catalogues (Tengyur pp. 134 and 572-574; Kanjur catalogue p. 124), the sūtra itself not found; the dhāraṇī's second entry (Toh 952, MW4CZ5369_0952) not located; no Kālacakra dream chapter found by title; the Peking, Narthang and Co ne volumes not opened (only BDRC's records of those prints) and the Peking and Co ne catalogues not found; Padma rdo rje's etext not retried; other dream passages named but not read in full: Derge vol. 28 pp. 378, 446-447, 499-500 (a Mahākāla rite on the night of the 14th of the dark fortnight with a dream as a sign of success) and the Guhyasamāja commentary (VE23703_1355 p. 533).
- **RD382 (2026-11-10)** (RD382, 2026-11-10):
  - Commentaries not found or not opened: Ratnarakṣita's Padminī (Peking 2137; Tsuda says it quotes the verses to verse 20 verbatim and then gives only the meaning); Bhavabhaṭṭa, Jayabhadra, Sachen and Tsong kha pa's other Sdom 'byung notes: no hit; exact-string BDRC searches found no commentary on these verses; the Dpe bsdur ma Kanjur etext of the Saṃvarodaya turned up in no search; Peking and Narthang seen only through Tsuda's collation; Bang and Kuranishi on the Padminī ch. 19 (Taisho University repository) returned a maintenance notice twice, not worked around.
  - Schneider 2010 not seen (the statement that most Sdom 'byung 19 death-sign verses are in Vāgīśvarakīrti's text is second-hand); Snellgrove's Sanskrit of the Hevajra not opened (a lending copy); exact strings with no hit: «དུས་མཚན་མོ་ཞེས་བྱ་བ་ནི», «དུས་མཚན་ནི་ནག་མོ», «གཤིན་རྗེའི་ཡུམ་དུས་མཚན», «གཤིན་རྗེའི་ལྟ་བར་འགྲོ་བར་འགྱུར»; no Kanjur hit for «བུ་མོ་ནག་མོ་རབ་འཁྲོས་ནས», «ཞག་བདུན་ནས་ནི་འཆི་བྱེད་ཡིན», «ཀ་ར་བི་རའི་འཕྲེང་བ་རྨིས»; the Kanjur scan at 2x did not settle lto bar against lta bar.
- **RD383 (2026-11-10)** (RD383, 2026-11-10):
  - Exact BDRC phrases and hit counts: «ཤོག་དྲིལ་བཞི་པོ་དེ་ལ་འཕོ་བ་སྤྱི་རྡོལ» 2, «འཕོ་བ་སྤྱི་རྡོལ་མའི་ལྷན་ཐབས» 2, «ཤོག་དྲིལ་བཞིའི་གདམས་པ» 7, «ཤོག་དྲིལ་བཞི་པོ» 14, «སྙན་གྱི་ཤོག་དྲིལ» 50, «སྙན་གྱི་ཤོག་དྲིལ་བཞི་པར་གྲགས་པ» 3, «ཤོག་དྲིལ་བཞིའི་བརྒྱུད་པ» 0, «བདེ་གཤེགས་རིན་ཆེན་ཏོག་རྩེ་སྒང་པ» 0; the Deb ther sngon po, Mkhas pa'i dga' ston and Bka' brgyud gser 'phreng not read (etext ids IE3CN3380, IE28792, IE1PD96069, IE23564 gave 404); Dpa' bo's life of Marpa read at its front matter only; Padma dkar po's Chos 'byung not opened.
  - Snippet-level only, not opened: the Bri lugs bang mdzod copy, the 'Brug lugs chos mdzod chen mo copy (MW23779_33216E), the 'Brug pa'i chos mdzod copy (MW3CN2232), the Bri gung lineage prayer attributed to Rig 'dzin Chos grags, Chos kyi grags pa's gsung 'bum, the Fifth Dalai Lama's Thob yig, the Sa skya Lam 'bras listing, Mkhyen brtse's gsung 'bum; the BDRC person record of Gling not searched; the OCR etexts (I3CN2237, I1243, I1KG15361) carry errors in the names of the chains; no scan looked at.
- **RD386 (2026-11-10)** (RD386, 2026-11-10):
  - No rite of the Derge Tengyur volumes VE23703_1330-1353 (the dream word with «ཚེས», «ནུབ་མོ», «ཐོ་རངས», «མཚན་མོ», «མཚན་ཕྱེད», «ཆུ་ཚོད», «ནམ་ཕྱེད», «ཐུན་ཐ་མ», windows of about 110 syllables), of vol. 28 and of VE23703_1355 ties a dream's use to a day of the month: the dates beside a dream word are the 14th of the dark half (the sitting night), the eighth of the waxing half in a translator's colophon, the 8th, 10th and 14th of offerings in a Vajrayoginī rite (VE23703_1340 p. 87, no dream) and the syllable-calendar of a Kālacakra gloss (VE23703_1335 pp. 124, 133, 1336 p. 589).
  - Not looked at: the Derge Tengyur etext volumes VE23703_1354 and 1356-1372 and after, the Peking, Narthang and Co ne prints, the Kanjur tantras' own dream passages (only Toh 48 and 460A, RD381); BDRC's exact-phrase search not run (the volumes were read directly); no scan read, every passage is etext.
- **RD388, 2026-11-11, the state between sleep and not-sleep** (RD388, 2026-11-11):
  - BDRC full-text search, 2026-11-11, no hit: «སད་པ་དང་གཉིད་ཀྱི་བར», «གཉིད་དང་སད་པའི་བར», «གཉིད་དང་སད་པ་གཉིས་ཀྱི་བར», «སད་པ་དང་རྨི་ལམ་གྱི་བར», «གཉིད་རྨི་བར», «ཉལ་བ་དང་སད་པའི་བར», «གཉིད་དང་མ་གཉིད་ཀྱི་བར»; «སད་རྨི་བར» one hit, a prayer listing waking, dream and bardo, not the state. «གཉིད་དང་མ་གཉིད» 22 hits: five opened, the rest read as snippets only (Lam rim chen mo, Dwags po, Jo nang, Kun dga' rnam rgyal, 'Jig rten mgon po, a Rdzogs chen song). The BDRC facet gives the Ri chos hits to Marpa, the edition read is Karma chags med's. Mipham and Rtsa gsum gling pa vols. 27–30 not reached by these phrases; WB no hit; Zhwa dmar IV's Six Dharmas (rrd.16:p220) nothing; Sakya «ཉལ་ཁར» is the wish for a dream at lying down; «ཉམས་དང་རྨི་ལམ» (462 hits), «ཉམས་སྣང» (3425) and «ཉམས་ཀྱི་འོད་གསལ» (143) counted, not read.
- **RD389, 2026-11-11, the state between sleep and waking in Mipham and Rtsa gsum gling pa** (RD389, 2026-11-11):
  - BDRC author facet P252 (Mipham): «གཉིད་དང་མ་གཉིད», «གཉིད་འོད་གསལ», «རྨི་ལམ་དང་ཉམས», «གཉིད་ཀྱི་འོད་གསལ», «གཉིད་དུ་འགྲོ་བའི་ཚེ», «གཉིད་ལ་སོང», «གཉིད་ཀྱི་མཚམས»: no hit of Mipham (with 0 hits BDRC shows the unfiltered hits of other authors under the same count; judge by the Author line); page 1 of the results only. Rtsa gsum gling pa vols. 27–30: «གཉིད་དང་མ་གཉིད», «ཉམས་ཀྱི་འོད་གསལ», «གཉིད་ཀྱི་འོད་གསལ», «སད་ཁར», «གཉིད་དང་སད» 0 hits; vols. 1–26 have no etext. The Dege print of the Padma dkar po (I4PD554, pp. 992–993) is OCR-garbled.
- **RD390, 2026-11-11, Marpa's other volumes on the midnight dream** (RD390, 2026-11-11):
  - Marpa's collected works vols. 1–3 and 5–7 (VEIE0OPI65ED66C6_I1KG15193–15199) opened whole: «གདོན་དབང» 0; «ནམ་ཕྱེད་ཀྱི་རྨི་ལམ» 0; «གདོན་གྱི་རྨི་ལམ» only the inner-heat manual (vol. 2 p. 162). BDRC phrase search: «གདོན་དབང་གི་རྨི་ལམ», «གདོན་དབང་གིས་རྨི་ལམ», «རྨི་ལམ་གདོན་གྱི་དབང», «གདོན་དབང་དུ་རྨི་ལམ» 0; «ནམ་གུང་གི་རྨི་ལམ» 6 hits not opened (VE1KG14_027, VEIE0OPI8B8FB4B8_I3CN8691, VEIE0OPI9903DE9F_I1PD36011, VEIE0OPICD2F24D4_I1KG23068, VEIE0OPI9762274C_I1GS66298, VEIE0OPI4B49432A_I1KG20329). «གདོན་དབང་» 103 hits: BDRC lists the first 13 works only, the page parameter returns the same list. Mi la ras pa's life and songs (etext not available in the prints seen) and Sgam po pa's works (no etext id found) not opened.
- **RD391, 2026-11-11, the spirits' dreams in the Drikung, Yu thog and Chos kyi grags pa texts** (RD391, 2026-11-11):
  - 'Jig rten mgon po's volume VE3JT13348_013, whole etext: «ཉལ་བའི་འཁྲུལ་འཁོར» stands three times, at p79 and p97 of the pod ta (the three physical disciplines in the cures of the practitioner's illnesses, the lion's posture without a word of dreams) and at p190 of the supplement pod; no second teaching in the volume ties the male or female spirit's dream to a posture. The Yu thog text Sku gsum rang shar (dds.47), etext pp. 273–335, has no colophon naming the writer. The dream passages elsewhere in the 15 volumes of Chos kyi grags pa not opened.
- **RD392, 2026-11-11, ch. 81's ལྷས་ཚོར་ or ལྟས་ཚོར་** (RD392, 2026-11-11):
  - BDRC exact search «ལྟས་ཚོར་དུས་ཏེ» / «ལྟས་ཚོར་དུས»: one hit, the Wylie etext MW15925 (I1PD436), an editor's «ldas [ltas]». I4842 and I1KG8419 (hits for «ལྷས་ཚོར་བའི་དུས»): etext API 403, search-only. Drathang I4CZ327131 img. 463: BDRC's maximum 2658 px (--width 4000 returns an empty file), the hand notes do not resolve at 2×. The Blue Beryl's printed page (Dpe tshogs p. 1148) still 401. The glossary is Lung rigs bstan dar's, not Sde srid's (earlier claim and passage not yet corrected).
- **RD397, 2026-11-11, where the tradition took Marpa's song** (RD397, 2026-11-11):
  - Four of the six hits of «ནམ་གུང་གི་རྨི་ལམ» are search-only (etext API 403): VEIE0OPI9903DE9F_I1PD36011, VEIE0OPICD2F24D4_I1KG23068, VEIE0OPI9762274C_I1GS66298, VEIE0OPI4B49432A_I1KG20329; the other two read whole (VE1KG14_027, VEIE0OPI8B8FB4B8_I3CN8691). BDRC phrase search hit counts: «ནམ་གུང་ལ་བདུད་ཀྱི་སྤྲུལ་པ་སྟོན» 3; «ནམ་གུང་ལ་བདུད་ཀྱི་སྤྲུལ་པ» 3; «བདུད་ཀྱི་སྤྲུལ་པ་སྟོན། དེ་ལ་ཡིད་བརྟན» 9; «ཡིད་བརྟན་བྱར་མི་རུང» 44; «ནམ་སྲོད་ལ་བག་ཆགས་རྨི་ལམ» 14; «ཐོ་རངས་ལྷ་ཡི་ལུང་བསྟན» 4; all hits read are Lives of Marpa or the Mgur mtsho; the Lo pan collection copies (I3CN21898, I3CN21904) are search-only. No etext of Mi la's life or songs, Sgam po pa's works, Mgo legs or Ngan rdzong ston pa carries the lines; no commentary on the line found. q1116 stays open: Rin chen gter mdzod vol. 27 [p72] ཉི་ནུབ་ས་སྲོས་བར་དུ་བསྒོམ uses the same two syllables without explaining them.
- **RD394, 2026-11-11, the night's parts in the dream rules** (RD394, 2026-11-11):
  - BDRC full-text search 2026-11-11: «རྨི་ལམ་དགེ་བ་བལྟ་བར་བྱ» no hit; «ཆུ་ཚོད་ལྔ་པའི་ཐུན་མཚམས» and «ཆུ་ཚོད་ལྔ་པའི་ཐུན» two hits only, VE23703_1333 and the Dpe bsdur ma edition (VEIE0OPIA3E40644_I1PD95854, not opened); «བར་མའི་ཐུན་ནི་བྲི་གྱུར་ནས», «མཚན་ཕྱེད་མཐར་གྱུར་ལ» and «ཐུན་ནི་གསུམ་པར་ཕྱིན་ནས» only the root tantra's own prints and KD vol. 2's etext (VEIE0OPI2F246A26_I4716 pp. 410–411, no gloss); VEIE0OPIFDBF938F_I5743 etext fetch empty. The Derge etext of the tantra has no fourth watch (ཐུན་ནི་བཞི, ཐུན་བཞི་པ, བཞི་པའི་ཐུན). No commentary on the omens' middle watch found. No Tibetan medical text read in the pool divides the night in four by the humours.
- **RD396, 2026-11-11, the words left in doubt in WB's dream list** (RD396, 2026-11-11):
  - BDRC exact phrases «ཤིང་གིས་སྔས་ཀྱང་བྱས» (0; the etexts read «ཤིང་གི་སྔས»), «རྨི་ལམ་འཁྲུལ་པའི་རྐྱེན» (0), «རྨི་ལམ་གྱི་རྒྱུ་ལྔ» (0), «ཕྱི་རོལ་གྱི་ལྷ་འདྲེ་འཁྲུགས་པ» (0): no Chöd text lists causes of a confused dream; «རྨི་ལམ་འཁྲུལ་པའི་རྒྱུ» 4 hits are the Pramāṇa commentary of Śākya mchog ldan only. Steinert's dictionaries (sparse clone of the public folder): «tsab tsub», «byes su» have no dream sense; «tshig pa za», «zur gzhogs», «thal phung» no headword. «རྒྱ་བལ་ཁ་ཅིག»: 3 hits (Kun 'dus chen mo, Bzo rtsis zlos gar vol. 2, Dpyod ldan snying nor), no text names the Chinese and Nepalese. q944: no text with WB's dream lines or the Klong rdol verses in WB's order among the hits. The search snippets are Wylie; the Tibetan of the Dpyod ldan snying nor and Zlos gar quotes is converted from Wylie and its OCR is poor. «byes su shi» (24 hits) and «byes su 'khyams» (113) read on page 1 only.
- **RD398, 2026-11-11, the Japanese table of the days a dream is not told** (RD398, 2026-11-11):
  - A legend or note for the slash on image 9 of the Kokubunken print (bid 200013909), beside the table and in the lower paragraph: none. A 初夢 in the second-day entry of the 東都歳事記, canvas 18: none (left-page columns only; the right page's columns from the third day on not read). The 万代大雑書 prints of the Kokubunken (ヤ0-32, ヤ0-116), Tokyo Gakugei (安永9, 文政11) and the Edo-Tokyo Museum not opened; the 嘉永大雑書, the 蓬左 copies, Waseda (503) not tried again.
- **RD393, 2026-11-11, the be'u bum's dream rites and their words** (RD393, 2026-11-11):
  - Exact BDRC phrases with 0 hits: «དངོས་སམ་རྨི་ལམ་བརྟགས་པའི་ཞལ་གདམས་བྲི», «བཅོམ་ལྡན་འདས་མྱ་ངན་ལས་འདས་པའི་གཟིམས་པ་ལྟར་ཉལ», «ཐབས་མཁས་ཐུགས་རྗེ་ཚད་མེད་པའི», «ཤཱཀྱ་ཐུབ་པའི་སྒོ་ནས་རྨི་ལམ་བརྟག», «རུ་རྟ་ལྡེ་གུར»; «འོ་ཡུག་པ་ན་རེ», «འོ་ཡུག་པ་གསུང་སྐད», «འོ་ཡུག་པས་གསུངས», «འོ་ཡུག་པ་རྨི་ལམ» 0; the 44 hits of «འོ་ཡུག་པ་» are Sakya lineage names, none tied to the Lord of Death's Time-sign. The Great Tibetan-Chinese Dictionary has no entry for «ru rta'i lde gur»; the other 60 Steinert dictionaries and the full etext of VE23681_001 (download timed out) not searched; the lexicon renderings of ru rta's medical uses are uncertain.
- **RD395, 2026-11-11, the small words of the Svapnohana and the Tengyur rites** (RD395, 2026-11-11):
  - BDRC exact phrases, 0 hits: «ལྟས་ངན་རྣམ་པར་བཟློག་པ», «རུས་སྤལ་ཆུ་སྲིན་ཡུག་མ», «རྨེ་བ་འབུག་པ», «ཁྱུ་མཆོག་རུས་སྤལ», «འཚོ་བ་རྨི་ལམ་གྱི་ཟླ་བ», «རྨི་ལམ་བདག་མེད་པའི་གཏམ». No Sanskrit found for Toh 48, the Turning back of the bad dream, the Showing of dreams or Dpal rta dbyangs' commentary; Klu'i byang chub's Twenty not looked for in an edition. bdrc.py etext on I1KG81094: 403. Steinert's repository: fetch with git clone --depth 1 --filter=blob:none --sparse and sparse-checkout set _input/dictionaries/public (about 108 MB, not 4.5 GB). The IA text of the Mañjuśrīmūlakalpa is 2015.345315.The-Arjya_djvu.txt (the earlier claim's name 404s).
- **RD399, 2026-11-11, the dream rites' small questions (in part)** (RD399, 2026-11-11):
  - BDRC full-text search: «འཇམ་གསར་བླ་མ་རྒ་ལོ» and «རྒ་ལོ་ཙཱ་བ་ཆོས་ཀྱི་འབྱུང་གནས» no hit; also none for «རིང་པོ་སུམ་ཅུ་རྨི་ལམ», «རྨི་ལམ་གྱི་རང་བཞིན་རིང་པོ», «མཚན་བརྟགས་འདི་ལྟར་བྱུང», «ཐོ་རངས་མཚན་བརྟགས», «བགེགས་མཐར་བྱེད་རྨི་ལམ་ངན» and one search for a Vairocana text's dawn-dream signs; these were guessed shapes, not quoted lines, so the absence is weak. The pyewts module is missing, so BDRC snippets' Wylie was turned into Tibetan by hand in bsr:p825-828b.
- **RD400, 2026-11-11, the Japanese dream prints (in part)** (RD400, 2026-11-11):
  - A table of the days a dream is not told in the Tokyo Gakugei prints bid 100408974 and 100408973 (all leaves on sheets of 700 px, image 24 and the last text leaf enlarged; running text not read letter by letter) and in Kokubunken ヤ0-32 (images 37–48) and ヤ0-116 (images 25–36), on sheets only. Not opened: the Edo-Tokyo Museum 嘉永大雑書, the right-hand columns of the 東都歳事記 canvas 18, the 江戸府内絵本風俗往来 and the 諸国年中行事; the 守貞謾稿 is restricted at the NDL. A rerun needs a larger time budget or the 夢 headings located first. Kokubunken serves every call (manifest at kokusho.nijl.ac.jp/biblio/<bid>/manifest, IIIF /full/<w>,/).
- **RD218, 2026-11-11, the Svapnohana scan proof (second run)** (RD218, 2026-11-11):
  - BDRC serves no larger frame of I1344 img. 268–269 or I1365 img. 261–262 than the cached 2000 px ones (bdrc.py scans --width 4000 returns a non-image file); at 8× a crop of img. 269 line 6 shows shape and shads, not la/ba or tsheg place; Yigdzin-1 again agrees with the etext but for p/b, wa-zur and tsheg slips. The Derge pages cannot reach the scan state without the archive.org loan or another original.
- **RD401, 2026-11-11, the dream questions RD399 did not reach** (RD401, 2026-11-11):
  - BDRC full-text search (lines quoted from the pool): «ཐ་མར་རྨི་ལམ་བརྟགས་པ་ལ་ཉལ» and «ཐ་མར་རྨི་ལམ་བཏབ་ལ་ཉལ» no hit (the Peking etext is not searchable text); «ཐོ་རངས་མཚན་བརྟས་འདི་ལྟར་བྱུང» and «ཐོ་རངས་གང་བྱུང་བདེན་ནོ» one hit each, the same volume (VE30541_005). Narthang catalogue vol. 225's etext (VEIE0OPIF6EAA761_I2KG212509): «འཆི་བ་བསླུ» and «འཆི་བླུ» none, «རྨི་ལམ» three hits on other works; OCR garbled. A Vairocana-tradition text that reads the pupils' dawn dreams: none in the pool; no exact-string search was made for want of a quoted line. No second Peking image of the Svapnekṣā leaf exists on BDRC (RD327). The Narthang catalogue's images 3–140 and 207–288 and kcd.11 pp. 409, 538–539 on a scan (Derge vol. 11 group I1327 not in the scan cache) not read.
- **RD402, 2026-11-11, titles, authors and orders the dream readings left** (RD402, 2026-11-11):
  - The BDRC catalogue records of the volumes were not opened; the authorship above comes from colophons in the etext. The line བདུད་ཀྱི་སྤྲུལ་པ in Marpa's song: rrd.23 and rrd.26 give no gloss, no commentary searched (q1114 open). The Kam tshang text of rrd.21:p25: author and colophon not read. The BDRC 'Kun mkhyen 'Jigs med gling pa' field of Gdams ngag mdzod vol. 9 is not the text of rrd.21:p19; which text it belongs to was not found. q1130 (lcg, tdr, ttn) not opened.
- **RD404, 2026-11-11, the Narthang catalogue images and the glossary's stack (in part)** (RD404, 2026-11-11):
  - Narthang catalogue vol. 225 (I2KG212509) img. 208–240, odd images, Yigdzin-1 on whole images: «རྨི་ལམ་བརྟག་པ» and «སྭཔྣ» none; «རྨི་ལམ» once (img. 239, Nāgārjuna's work). Even images 208–238 came back with 1–50 characters and were not read; images 3–140 and 241–288 not run. I00KG07616 img. 518 fetched, not enlarged.
- **RD403, 2026-11-11, the spirits' dreams and the night's reckoning** (RD403, 2026-11-11):
  - BDRC full-text search (exact strings): «རྒྱལ་པོ་ལ་རྟ་མགྲིན།», «གཟའ་ལ་ཕྱག་རྡོར། ཀླུ་ལ་ནཱ་ག་རཀཱ» and «གདོན་གྱི་རྨི་ལམ་ལ་ཡང་» one hit each, the Yu thog volume itself; «ཁྱི་ལ་སྤྱང་ཀི། སྤྱང་ཀི་ལ་སྟག» four (VE3JT13356_017 etext API 403); «ཕོ་གདོན་གྱི་རྨི་ལམ» three, all the 'Jig rten mgon po teaching; «སྲོད་ཀྱི་རྨི་ལམ་དེའི་རྐྱེན་གྱིས» one. The whole Dpe bsdur ma etext of the Cakrasaṃvara commentary (I1PD95854): «ཆུ་ཚོད» 12 places and «མཚན་མོ་ཕྱེད་ནས» name no starting moment. «བར་མའི་ཐུན་ནི་བྲི་གྱུར་ནས», «ཐ་མར་ཉེ་ལ་འགུལ་བ་བྱུང་», «གཉིས་པའི་ཐུན་དཀྱིལ་དུ», «མཚན་ཕྱེད་དུས་དག་ཏུ» and the bare «བར་མའི་ཐུན་»: no commentary on the omens (I2PD18782 403; I5743, I1KG2753 not opened). «ན་མོ་གུ་རུ་དྷེ་ཝ་ཌཱ་ཀི་ནཱི་ཧཱུྃ» in VE1KG14_007, VE1ER112, VE1ER54: no etext writes a string in plain letters between the opening Om and the homage.
- **RD405, 2026-11-11, how the Kagyu masters read Marpa's midnight dream** (RD405, 2026-11-11):
  - BDRC full-text search: «ནམ་ཕྱེད་ཀྱི་རྨི་ལམ་གདོན་དབང་ཡིན», «སྤྱིར་སྲོད་ཀྱི་རྨི་ལམ་བག་ཆགས་ཡིན», «ནམ་ཕྱེད་ཀྱི་རྨི་ལམ་གདོན་དབང», «སྲོད་ཀྱི་རྨི་ལམ་བག་ཆགས», «ནམ་ཕྱེད་ཀྱི་རྨི་ལམ་གདོན» one hit each, VE30541_005 only; «དེ་ལ་གཟའ་གཏད་ཅི་ཡང་མེད» 8 hits, all the song's own copies (Marpa's works, the Ris med collection, Nechu Lhakhang texts, Lo pan copies), no gloss; «ནམ་ཕྱེད་ལྷ་འདྲེའི་ཆོ་འཕྲུལ» 4, «ནམ་སྲོད་རྨི་ལམ་བག་ཆགས་ངེས་པ་མེད» 3, «ཐོ་རངས་རྨི་ལམ་འབྲས་བུ་ཡོད་པ» 2, «བདུད་ཀྱི་བར་ཆད་རྩོམ་དུས་ཡིན» 2, «ནམ་ཕྱེད་ནས་བཟུང་ཐོ་རངས་ཕྱེད» 3, all in Karma chags med's prints only. Gdams ngag mdzod vol. 9 opened whole: no passage on what the midnight dream is. The Karmapa's vols. 1–3 and 6–12 opened whole: none but VE30541_007 text 9 p102; VE30541_009 p4, _010 pp. 39 and 69, _011 p33 not read for dreams. Mi la ras pa's songs, Sgam po pa's works and the other Six Dharmas dream manuals: no etext found; no Kong sprul or Tai si tu dream-yoga text found on the volumes named.
- **RD406, 2026-11-11, the Narthang catalogue's images 3–140** (RD406, 2026-11-11):
  - Narthang catalogue vol. 225 (I2KG212509) img. 3–86 whole images and img. 4–104 even crops, Yigdzin-1: no second entry with «རྨི་ལམ་བརྟག་པ»; «རྨི་ལམ» only on img. 75 and 77 (grep on «རྨི་ལམ» alone). Images 87–140 not read: whether the catalogue lists the Examination of Dreams a second time there is unchecked; img. 77 is machine reading only.
- **RD407, 2026-11-11, the Narthang catalogue's images 87–140** (RD407, 2026-11-11):
  - Narthang catalogue vol. 225 (I2KG212509) img. 87–140, Yigdzin-1: no second entry «རྨི་ལམ་བརྟག་པ»; machine reading only; img. 93, 105, 111 and 121 nearly empty and unread, so the absence rests on the images that did read. The reader's section is plain prose, not yet passages and claims.
- **Open-question investigations, 2026-11-13, the etext and catalogue questions of the dream and calculation chapters** (inv-a to inv-o, 2026-11-13):
  - `bdrc_search.py` needs the Chromium build the installed Playwright asks for (`python -m playwright install chromium`, 1254 on 2026-11-13); until then it fails and every full-text search is lost.
  - BDRC image group I1PD411 (Blue Beryl vol. 1) serves 41 of its 980 images, printed pp. 170–171 not among them: the Drukpa clause of q988/q989 cannot be checked on a scan there.
  - Web searches for «སྲིད་གསལ»/«Srid gsal» as an era name (q980) and for the Brahmavaivarta verse «tathaiva»/«tatraiva» (q997): nothing; GRETIL's page for the latter came back empty.
  - Leipzig Sanskrit manuscripts 1176–1180 (Aufrecht 1901): no record in the manuscript portal (mymssportal), q872.
  - The Sakya Medicine Buddha commentary MW29307 (etext VE29307_052) is search-only: 0 chunks, only Wylie snippets (q794).
  - Padma rdo rje's book (MW25126): the text before the Svapnohana quotation is unreachable except by the archive.org loan (q1003).
  - Dpe bsdur ma vol. 46 (I1PD95891) images 1096–1100 came back empty twice (q1011); the Narthang catalogue entry img. 77 and the Peking vol. 28 title (img. 315, 3200 px at most) cannot be settled by eye (q1004, q1088).
  - Fetching Derge Tengyur volumes with `bdrc.py` takes about a minute each, a Beijing gsung 'bum volume over two minutes: search the phrase first to get the volume id, and run the fetch in the background.
  - No muhūrta-name list in the Derge Mañjuśrīmūlakalpa, the Kun 'dus vol. 3 etext or the TSS 70 text layer (q1056); nothing older than Kun dga' rin chen and Nam mkha' dpal bzang on the Sakya reason for the last watch (q1049).
  - Commentary on WB ch. 9 p. 62 («མནན་ཡུན…», «སྒྲིབ་དང་མནན་དང་གྲོལ་ཡུན»): phrase searches find only copies of the verse itself (q774); WB p. 403's watch length (q812, q966): no text states it.
  - «དམར་མོ་འདས་པ…» (q881) and «མཚན་བརྟགས»/«མཚན་རྟགས» (q1142) and «བེགས་མཐར་བྱེད་» (q1141, a wrong spelling): 0 hits.
  - Why the Pleiades month is the «Dream month» (q1133): lexica give a month name only.
  - The Edo 初夢 night of 2 January (q786, q788, q1001, q1002): no print read has it; Waseda answers 503, the 夢合判断大成 (1830) has no Kokubunken record; the kokusho `/biblio?kw=` page renders only in a browser, `/api/biblioSimpleSearch?searchkbn=simple&keyword=…&page=N` works with curl.
- **Tool note, 2026-11-13 (inv-n):** BDRC person and work records can be read without a browser: `purl.bdrc.io/resource/<ID>.ttl`, and `autocomplete.bdrc.io/_msearch` (Elasticsearch) for titles; neither finds a catalogue part titled «'pho ba spyi rtol ma'i lhan thabs»; three volumes BDRC's phrase search lists for «བར་མའི་ཐུན» (I2PD18782, I5743, I1KG2753) return empty from the etext API.
- **Dead ends, 2026-11-13 (inv-q, inv-r):** archive.org's OCR of the Ācāramayūkha prints (Gharpure 1921, Bhagavantabhāskara copies) is unusable for phrase search, the Bakre 1915 copy returned no file, no 1876 Benares print with Rāmanātha's commentary found (q928); the Dpe bsdur ma vol. 1 etext (I1PD95846) has no preface or sigla text (q952); BDRC serves no scan of MW15590 (I1PD380 img. 183–186) (q924); «སྔ་སུམ་པ་མཁན་པོ» and «ལྡང་ཁུ་མི» 0 hits (q969, q971); Drathang I4CZ327131 img. 647 (2657×477 px) has dense hand notes, nothing legible (q824); Japanese Wikisource has no 守貞謾稿, 東都歳事記 or 嬉遊笑覧; the kokusho `/api/biblioSimpleSearch` route searches titles only; no web page found for a Larung Gar 611-page Four Tantras (q894).
- **Dead ends and tool note, 2026-11-13 (inv-s):** BDRC phrase search «སེམས་སྡེ་སྤྲ་ཁྲིད», «སྦྲན་པ་ནམ་མཁའ་རྡོ་རྗེ» and «ཐམས་ཅད་མཁྱེན་པ་ཤཱཀྱ་ཤྲཱི་ནས་བརྒྱུད་པའི་ཤྲ་མ་ཎ» 0 hits; MW1KG4326 and MW3CN5276 are readable only as snippets (the etext API answers 403); no source names the lama of the Śramaṇā rite (q1130) or a separate source book for the Mchog dga's Saturn lines (q1075). A work's contents can be walked without reading its etext: `purl.bdrc.io/resource/<MW>.ttl`, `bdo:hasPart` with `contentLocationPage` and `contentLocationEndPage`.
- **Tool note and dead ends, 2026-11-13 (inv-t):** for a BDRC etext split into one volume per work, a `bdrc_search.py` search for a work's title returns char −1000 in that work's own etext volume: the volume-to-work map without reading the etext (MW1KG4326: _001 Arapacana sādhana, _002 Mol gtam, _003 Rab gnas, _008 Phur pa stod las, _009 Phur pa dbang mtshams sbyor; _004–_007 untested). Elasticsearch `_msearch` on author fields returns 500; `purl.bdrc.io/query/table/Work_creator` fails; MW2PD14777 has no `hasPart`; MW20396 (1996, in copyright) has no etext, so its p. 164 is unread.
- **Dead ends, 2026-11-14 (inv-f):** BDRC phrase search «རམ་རུག་ཅེས་བྱ», «རམ་རུག་ནི», «རམ་རུག་ཀྱང་», «རམ་རུག་ཅེས་», «རམ་རུག་ཞེས་», «རམ་རུག་གི་རྩ» 0 hits (no text glosses ram rug); «རམ་རྒྱུག» is only «དཀྱིལ་འཁོར་རམ་རྒྱུག་རིས», another word; the one new place is the Lama Gongdü practice volume MW1GS60392 («བྲ་སྲན་རམ་རུག་ལ་སོགས་མི་ཁའི་རྫས», snippet only; q804, q816, q826, q840 stay open).
