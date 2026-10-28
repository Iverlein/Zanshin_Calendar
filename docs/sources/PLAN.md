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

## Dead ends

Searched and found wanting, so not to be repeated:

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
  author. `archive_lend.py view` needs playwright 1.62.0 for the cached
  chromium-1234; the system Python's 1.64.0 asks for another download.
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
