# Plan of investigation

For whoever continues the source work. The settled findings are in the
topic files listed in [README.md](README.md); this file says what is still
to be found, where to look, how to read it, and when each task counts as
done. Tasks are in the order they should be taken: each one unblocks
something the app can build.

Written 2026-10-03, at the end of the session that found the *White Beryl*
passages and wrote the topic files.

## Status, 2026-10-04

| Task | State |
| --- | --- |
| 1. Doubled mansions | **Done.** Every doubled entry resolved on the KP scans; see [mansions.md](mansions.md), *Box by box* (box 6 also lists byi bzhin, found by the OCR). The corrections are made in `Electional.kt`, following the print (SPEC §5.10); box 8's last unread word is ནུཾ, Revatī (2026-10-04). |
| 2. KP inventory | **Done.** Every box placed and named from the OCR of all 120 folios and the book's two lists of contents (img. 8–11, 63–64); box 1 is the opening prose, box 4 the robe chart; boxes 50 and 51 are missing from this print but found in a second print (KP2, BDRC I3CN12074 img. 218–345); headings 13, 19, 23, 30, 31, 36 and 44 settled or corrected; the second half described section by section ([kun-phan-me-long.md](kun-phan-me-long.md)). |
| 3. Mansion verses | **Done**, by machine: all 28 verses (Abhijit included) in [mansion-verses.md](mansion-verses.md), Tibetan up to the birth line and the full lists in English; every disagreement between the two OCR readings settled, the last five on the scan (2026-10-04). Not read by eye syllable by syllable. |
| 4. Yogas | **Done** as far as the texts go: names and short readings checked on the scan, long readings from the two OCR readings (their five disagreements settled on the scan, 2026-10-04), KP as a third witness (sha 'khon differs: KP's own reading, for WB2 agrees with WB); WB2 (the Sakya Centre print) has the same ranking and avoidance verses, so questions 1 and 2 need a reader, not another copy. |
| 5. Lunar dates | **Done**: every […] filled from the scan (date 25 reads སྒབ, not identified; date 13's illness is as date 5); the other *bla gnas* systems (by hour, *lho gter*, weekday) written out ([lunar-dates.md](lunar-dates.md)). |
| 6. WB ch. 33 inventory | **Done**, from the OCR: [white-beryl-ch33.md](white-beryl-ch33.md). Two new calculable day readings found: the weekday's (pp. 308–312), now written out in [weekdays.md](weekdays.md) and built; weekday × mansion (pp. 331–333). |
| 7. Kyūreki gaps | **Done**: 2033 settled (暦文協, 2015-08-28, 閏11月, matches the app); 三箇の悪日 sourced to the 簠簋内伝 (1919 and 1800 printings label the columns by birth year; the 1632 edition does not); O-Bon on 15 August and the sekku's Gregorian days sourced (Wikipedia お盆, NAOJ 節句); all three in the app ([kyureki.md](kyureki.md)). |
| 8. Lunar-day animal, trigram, sme ba | **Done** (2026-10-04): the trigram is the day of one of WB's eight goddesses (vol. 1, pp. 449–450, read on the scan), its illness reading built; the date's sme ba and animal have no reading of their own in WB or KP, what was found is recorded with its reasons ([lunar-day-signs.md](lunar-day-signs.md); question 9). |
| 9. Weighing the day | **Done** (2026-10-04): KP's ranking ([weighing.md](weighing.md)) and WB's combinations of weekday and mansion, the 28 named ones, the ten element pairs and the fifteen special days ([combinations.md](combinations.md)), read on the scans and built (SPEC §5.12). Rāhu's detailed course by date built too ([rahu.md](rahu.md)). The second set of special days, after the *Rdo rje gtsug lag* (WB p. 337, table p. 342), read and built too. Rāhu's general course read too, KP's chart (img. 78) agreeing date by date, and built on the fourteen dates the detailed course leaves (2026-10-04). Left: the combination period, which needs the planets (SPEC §5.13). |
| 10. Facts on the pages, 2026-10-04 | **Done**: every hedge in the shown readings resolved or recorded. *shwa rags* is a dike against flash floods and *thag ser* read as *thog ser* (lightning and hail) ([lunar-dates.md](lunar-dates.md)); bzhi mdo's weapon-tormas confirmed by the Derge print ([karanas.md](karanas.md)); WB's seven classes of mansions read, correcting Henning's natures for three mansions ([mansions.md](mansions.md)); the yoga avoidance verse read as number words, 3/5/6/9 chu tshod ([yogas.md](yogas.md)); question 9 answered; weekday words looked up in 64 dictionaries ([weekdays.md](weekdays.md)); 歳下食 checked against a 1901 table ([kyureki.md](kyureki.md)). |
| 11. Weighing the 暦注, 2026-10-04 | **Done**: no source ranks the kinds of annotation; within the lower band, 受死日 and 十死日 stand alone and 歳下食 is lifted by a good day and heavier with a bad one (Wikipedia 暦注下段), built; 十死日's funerals corrected; the 宿曜経 combinations, koyomi8's 一粒万倍日 rule and the 協紀辨方書 grades found and not used ([kyureki.md](kyureki.md)). |
| 12. KP's other activity boxes, 2026-10-04/05 | **Done**: 44 boxes read on the scans with KP2 and two OCR readings, then boxes 50–53 split or joined (crafts and haircuts, military training and games, KP2's 50–51): 50 lists in `Electional.kt`; box 47 (averting rites, every entry a kind of rite) and the charts not built ([kp-activities.md](kp-activities.md)). |

## Next investigation

What is left on the Tibetan side, in order of use to the app; each item
says where to look and with which tool.

1. ~~The app's own corrections.~~ Made: `Electional.kt` follows the print,
   and box 6's khrums smad, in both halves, stays out (SPEC §5.10;
   question 6 is still for a reader).
2. ~~New calculable readings in KP's second part.~~ None is left: §2, §3
   and §5, the combinations and special days, are built from WB's own text
   ([combinations.md](combinations.md)), and §11 (img. 85, read
   2026-10-04) is the table of the personal mansions and weekdays the app
   already has from WB p. 330; 104 of its 108 cells agree, the other four
   are carver's slips ([personal-mansions.md](personal-mansions.md)). Its
   readings (img. 86) are WB's verse shortened, with nothing new.
3. **Read by eye what was read by machine**, where it feeds the app: the
   weekday verses (`Texts.WEEKDAY`), WB's combinations and special days
   (pp. 331–337) and the yogas' longer readings. The mansion verses are
   not quoted (the app's mansion lists are Henning's), so they wait until
   they are. The method that works: BDRC's etext of WB (IE0OPI51524892,
   page = image number) as a third witness beside Yigdzin-1 and MITRA,
   aligned with `disagree.py` against the settled text; its flags are
   checked on the scan. **Weekdays done** 2026-10-04 (94% agreement, 69
   flags; two readings corrected, [weekdays.md](weekdays.md)); the
   combinations and yogas checked the same day, nothing changed (the
   etext is patchy there; [combinations.md](combinations.md),
   [yogas.md](yogas.md)). (The open
   readings in the mansion verses and the […] in
   [lunar-dates.md](lunar-dates.md) were settled on the scan on
   2026-10-04.)
4. **WB2's personal-mansion verse** (question 4): not among WB2
   img. 468–473. On 2026-10-04: img. 474 (folio side 462) is still the
   mansion verses, and the etext has the weekday-and-mansion combinations
   by p. 478–481 (img. 476–479), so the verse is on img. 475 or 476. Those
   folios are served only at 1224 px (a larger `--width` returns no image),
   too small to read the verse with confidence. The app does not wait on
   it: the names NM, SY and WB print already agree (question 4).
5. **Questions for a reader** ([open-questions.md](open-questions.md)):
   1 (ranking), 5 (SY's Mouse gshed gza'; KP now sides with WB), 6 (box
   6), 8 (sha 'khon: KP's change), 10 ('Od 'bar ma's *chu gri bkar*). A
   third WB print would not settle 1 or 8: WB2 has the same words.
   Question 2 was answered on 2026-10-04 (number words: the yogas'
   avoided chu tshod) and question 9 too (the *nyi ma* is the lunar date's
   animal).
6. **WB ch. 33 inventory** ([white-beryl-ch33.md](white-beryl-ch33.md))
   was made from BDRC's OCR; recheck its section headings with
   `hf_read.py yigdzin` when a section is taken up.
7. ~~The earth lords of each animal day~~ Read and built 2026-10-04
   ([earth-lords.md](earth-lords.md), SPEC §5.11); the day's earth lord's
   part of the house and the hearth god's place read and built too, with
   KP's chart (img. 103) as second witness, which also has the mouse day's
   སྲང. Left from that chapter: the *bla mkhyen*, which needs the sme ba of
   the sixty-day count (the year's section of WB ch. 31 to be read first),
   and the day by the clan's element, which needs the person's clan.

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
  numbered on from the last one.
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

## Tasks

The briefs as they were set; what each found is in the table at the top
and in its topic file.

### 1. Settle the doubled mansions of the activity lists (T2)

**Why:** the app leaves out every mansion Henning names twice for one
activity (SPEC §5.10). The cause is now known: KP abbreviates mansion
names, and Henning seems to have read the abbreviations of khrums stod and
khrums smad as chu smad. See [mansions.md](mansions.md).

**Where:** KP, BDRC W4CZ65561, images 21–67. The affected activities and
mansions are listed in mansions.md: offerings to deities, taking a new home,
setting out, making weapons, marriage, funerals, controlling activity
(and Dhaniṣṭhā twice, Mṛgaśiras good and qualified).

**How:**

1. Inventory the boxes (task 2) to find the box of each affected activity.
2. In each box, transcribe the mansion list of the good half and the bad
   half, syllable by syllable, from crops enlarged 3×.
3. Align each list with Henning's list for the same box (in
   `core/.../tibetan/Electional.kt`, in his order). Where both have the
   same number of entries, each abbreviation's expansion follows from its
   place. Build a key of abbreviations from the boxes where the alignment
   is unambiguous (e.g. ཁྲོད, ཁྲིད/ཁྲུད, ཆུད, ཤུད, གྲོཞི, མོནྲུ, མོན་དྲེ), and
   apply it to the rest.
4. Where the 2550 px image does not show a vowel sign, look for a clearer
   copy: search BDRC for other prints of KP (titles *'bras rtsis bai dkar
   dgongs don*, *kun phan me long*), and Henning's pages on kalacakra.org
   for the edition he used.

**Done when** each doubled entry in mansions.md is resolved to one mansion
with its image number, or recorded as unreadable on every copy found. Then
the app's lists can be corrected (a separate, code task).

### 2. Inventory the *kun phan me long* tables (T2)

**Why:** KP holds many more activities than Henning's thirteen; the
inventory says which can be added, and gives task 1 its boxes.

**Where:** KP images 21–67, which the thumbnails show as boxed tables;
images 1–20 and 68–120 have not been looked at.

**How:** for each image, read each box's heading (the activity, at the
left of the box under its number) and note what its halves list
(weekdays, dates, mansions, animals, trigrams, signs, *dus sbyor*). Write
the inventory as a table in [kun-phan-me-long.md](kun-phan-me-long.md):
image, box, heading in Tibetan, English, factors listed. Headings read so
far are in that file.

**Done when** every box of images 21–67 has a row, and images 1–20 and
68–120 have a one-line description each.

### 3. Write out the remaining mansion verses (T2)

**Why:** the app's mansion readings come from Henning; WB's verses give the
Tibetan and more (births, illness, minor stars, remedies), and settle any
doubt in his list.

**Where:** WB vol. 2, pp. 313–328 (img. 321–336). The etext has every
verse, but lost the opening lines of about eleven (Rohiṇī, Puṣya,
Āśleṣā, Pūrvaphalgunī, Uttaraphalgunī, Citrā and Viśākhā among them),
and garbles a syllable or two in most lines.

**How:** follow the Uttarāṣāḍhā entry in mansions.md as the pattern:
the Tibetan of the good and avoid lists as read on the scan, then the
gist in English (good, avoid, birth). Split the etext at each verse's
*skar chung* line (`༧ སྐར་ཆུང`), which ends it.

**Done when** all 28 verses (Abhijit included) are in mansions.md.

### 4. Check the yoga readings and the ranking (T2)

**Where:** WB pp. 347–349 (img. 355–357) for the long readings; NM and
SY (etexts) for their yoga sections, which may give the ranking in other
words.

**How:** read the long readings on the scan, replacing the OCR-based
table in [yogas.md](yogas.md). Search NM and SY for the yoga names
(སེལ་བ, རབ་སྟོངས, ཀུན་བརྡུངས, ཤ་འཁོན …) and for a ranking passage; a second
witness can answer open questions 1 and 2.

**Done when** the long readings are scan-checked and questions 1–2 are
answered or marked as needing a reader.

### 5. Complete the lunar dates (T2)

**Where:** WB pp. 297–304 (img. 305–312).

**How:** fill the […] in [lunar-dates.md](lunar-dates.md) from
enlarged crops; transcribe the illness and remedy lines (now summarized);
read date 4 of the *bla gnas* list; write out the other *bla gnas*
systems (Kālacakra syllables, the *lho gter* list with hours, the
weekday list), pp. 303–304.

**Done when** the table has no […] that a crop at 6× can resolve.

### 6. Inventory the rest of WB chapter 33

**Why:** the chapter holds more than the app has used, and none of it is
written down yet.

**Where:** WB vol. 2, pp. 305–312 (the planets and weekdays, img.
313–320); pp. 329–332 (the mansions' strength, *dar gud*, p. 329; the
personal mansions, p. 330; the weekday-and-mansion combinations *kun
dga'* … with their readings, pp. 331–332; img. 337–340); pp. 343–346 (tables of the karaṇas and of the *bla skar*
by element, img. 351–354); pp. 351– (img. 359 on: the bad days such as
*ma rig pa'i nyi ma*).

**How:** one line per section in a new `white-beryl-ch33.md`: pages,
images, what it gives, whether the app could use it.

### 7. The kyūreki gaps

Not Tibetan, but the same rule applies. Each needs a published source;
facts only, never copied wording (SPEC §8).

- **The 2033 leap month** (SPEC §7.1, S3): `Kyureki.kt` names the choice
  of 閏11月 as a constant pending its source. Leads, not yet checked: a
  statement by the 日本カレンダー暦文化振興協会, and the 国立天文台's
  position on the 2033年問題.
- **The personal reading of 三箇の悪日** (SPEC §7.5): `Rekichu.personalDays`
  shows 大禍日・狼藉日・滅門日 only in the solar month of one's birth-year
  branch; no source is cited for that rule. Look in almanacs that print
  the 暦注下段 (e.g. 高島暦, 神宮館) and their commentaries.
- **Gregorian-dated festivals** (SPEC §7.3, e.g. O-Bon on 15 August): the
  SPEC promises them, the code does not have them. Find a source listing
  which festivals Japan keeps by the Gregorian date.

Write the findings into a new `kyureki.md` in this folder. The owner's
review dossier for an expert (built locally, not in the repository) asks
the same questions; answers from the expert go here too.

### ~~8. Add Tibetan script for transcribed names~~

**Done** (2026-10-04): The UI was updated to show the Tibetan script alongside the transcript for these terms using `Ewts.toTibetan()`. The collected mappings are preserved below for reference.

| Place | Tibetan term | Transcript |
| --- | --- | --- |
| `GreatCombination.KUN_DGA` | ཀུན་དགའ་ | kun dga' |
| `GreatCombination.DUS_DBYIG` | དུས་དབྱིག་ | dus dbyig |
| `GreatCombination.DUL` | དུལ་བ་ | dul ba |
| `GreatCombination.SKYE_DGU` | སྐྱེ་དགུ་ | skye dgu |
| `GreatCombination.GZHON` | གཞོན་ | gzhon |
| `GreatCombination.BYA_ROG` | བྱ་རོག་ | bya rog |
| `GreatCombination.RGYAL_MTSHAN` | རྒྱལ་མཚན་ | rgyal mtshan |
| `GreatCombination.DPAL_BEU` | དཔལ་བེའུ་ | dpal be'u |
| `GreatCombination.RDO_RJE` | རྡོ་རྗེ་ | rdo rje |
| `GreatCombination.THO_BA` | ཐོ་བ་ | tho ba |
| `GreatCombination.GDUGS` | གདུགས་ | gdugs |
| `GreatCombination.GROGS` | གྲོགས་ | grogs |
| `GreatCombination.YID` | ཡིད་ | yid |
| `GreatCombination.DOD` | འདོད་ | 'dod |
| `GreatCombination.MGAL_ME` | མགལ་མེ་ | mgal me |
| `GreatCombination.RTSA_BTON` | རྩ་བཏོན་ | rtsa bton |
| `GreatCombination.CHI_BDAG` | འཆི་བདག་ | 'chi bdag |
| `GreatCombination.MDA` | མདའ་ | mda' |
| `GreatCombination.GRUB` | གྲུབ་ | grub |
| `GreatCombination.MDUNG` | མདུང་ | mdung |
| `GreatCombination.BDUD_RTSI` | བདུད་རྩི་ | bdud rtsi |
| `GreatCombination.GTUN_SHING` | གཏུན་ཤིང་ | gtun shing |
| `GreatCombination.GLANG_PO` | གླང་པོ་ | glang po |
| `GreatCombination.RTAG_MYOS` | རྟག་མྱོས་ | rtag myos |
| `GreatCombination.ZAD_PA` | ཟད་པ་ | zad pa |
| `GreatCombination.GYO` | གཡོ་ | g.yo |
| `GreatCombination.BRTAN` | བརྟན་ | brtan |
| `GreatCombination.PHEL` | འཕེལ་ | 'phel |
| `CombinationDay.GRUB_SBYOR` | འགྲུབ་སྦྱོར་ | 'grub sbyor |
| `CombinationDay.ZUNG_SBYOR` | ཟུང་སྦྱོར་ | zung sbyor |
| `CombinationDay.BDUD_RGYAL` | བདུད་རྒྱལ་ | bdud rgyal |
| `CombinationDay.GRUB_NYI` | གྲུབ་ཉི་ | grub nyi |
| `CombinationDay.BKRA_SHIS_NYI` | བཀྲ་ཤིས་ཉི་མ་ | bkra shis nyi ma |
| `CombinationDay.PHEL_NYI` | འཕེལ་ཉི་ | 'phel nyi |
| `CombinationDay.CHUB_NYI` | ཆུབ་ཉི་ | chub nyi |
| `CombinationDay.MTHUN_NYI` | མཐུན་ཉི་ | mthun nyi |
| `CombinationDay.SBYOR_NYI` | སྦྱོར་ཉི་ | sbyor nyi |
| `CombinationDay.BDUD_NYI` | བདུད་ཀྱི་ཉི་མ་ | bdud kyi nyi ma |
| `CombinationDay.CHI_SBYOR` | འཆི་སྦྱོར་ | 'chi sbyor |
| `CombinationDay.MI_PHROD_NYI` | མི་འཕྲོད་ཉི་མ་ | mi 'phrod nyi ma |
| `CombinationDay.MI_MTHUN_NYI` | མི་མཐུན་ཉི་མ་ | mi mthun nyi ma |
| `CombinationDay.JIG_NYI` | འཇིག་པའི་ཉི་མ་ | 'jig pa'i nyi ma |
| `CombinationDay.GTAN_SPANG` | གཏན་སྤང་ | gtan spang |
| `Trigram.LI` (goddess) | འོད་འབར་མ་ | 'od 'bar ma |
| `Trigram.KHON` (goddess) | བསྟན་མ་ | bstan ma |
| `Trigram.DWA` (goddess) | དཀར་གསལ་མ་ | dkar gsal ma |
| `Trigram.KHEN` (goddess) | མདངས་ལྡན་མ་ | mdangs ldan ma |
| `Trigram.KHAM` (goddess) | ཆར་འབེབས་མ་ | char 'bebs ma |
| `Trigram.GIN` (goddess) | གཡོ་མེད་མ་ | g.yo med ma |
| `Trigram.ZIN` (goddess) | འོད་འཆང་མ་ | 'od 'chang ma |
| `Trigram.ZON` (goddess) | སྐྱོབ་བྱེད་མ་ | skyob byed ma |

## Dead ends

Searched and found wanting, so not to be repeated:

- **Rinchen Terdzö** full text (rtz.tsadra.org, owner's manual search,
  2026-09-29): none of the personal-mansion names, no Viṣṭi, none of the
  eight unconfirmed yoga and mansion names. It is a collection of treasure
  texts, not a calculation manual.
- **The Derge reprint of WB** (BDRC MW1KG12714): its OCR garbles the verse
  passages; use the 1996 edition.
- **Tsadra wikis** (rtz, rywiki): behind a Cloudflare check that an
  automated browser passes at most once. A person has to click through.
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
