# Plan of investigation

For whoever continues the source work. The settled findings are in the
topic files listed in [README.md](README.md); this file says what is still
to be found, where to look, how to read it, and when each task counts as
done. Tasks are in the order they should be taken: each one unblocks
something the app can build.

Written 2026-10-03, at the end of the session that found the *White Beryl*
passages and wrote the topic files.

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

All of these worked on 2026-10-03.

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
- **The owner's own Firefox** must never be driven by automation: a
  session on 2026-09-29 left about a hundred test preferences in the
  profile. Use a separate browser profile.
