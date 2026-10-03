# Zanshin Calendar — Roadmap

Planned work that is not yet built. [SPEC.md](SPEC.md) describes what the app
does; this file describes what it is meant to do next. When an item is built,
its settled design moves into the SPEC and the entry here is removed.

The rules of the SPEC apply to everything below: no text without a published
source (§8), nothing the app cannot calculate, no `INTERNET` permission (§2).

## By effort

The items below, easiest first. Sizes: **S** a sitting, one or two files;
**M** a few days, new code with its tests; **L** a week or more, touching
most screens or needing a generator in `tools/`; **XL** several weeks,
mostly research or design before any code. The rank counts the work of
building an item, not its prerequisites; "Needs" gives those, so the build
order can differ from the rank. An item with no use on its own is ranked
with the one that gives it a use. "Later" is not ranked.

| # | Item | Size | Why | Needs |
| --- | --- | --- | --- | --- |
| 1 | T3 Element colours, personal mansions | S | A colour per element and a table per animal, once each has what it needs | A place for the element colour; a source for the mansions |
| 2 | L4 Hosted Weblate | S | The repository is ready (docs/weblate.md); the project, its three components and the review rules are set up on Weblate with the owner's account; the translating itself is outside the code and open-ended | Not before 2026-12-28 (three months of development, for the Libre plan) |
| 3 | L3 Russian | S | Translated and passing `CatalogTest`, with the store listing and the language switch; what is left is the owner's read-through on the phone and the fixes it brings | — |
| 4 | T2 Tibetan readings | M | Mansions and the activity lists are built; yogas, karaṇas, lunar days and further activities each need a source first | Sources |
| 5 | R4 Visual cues | XL | About twenty activity glyphs, a dozen diagrams and the trigrams, each drawn, described for screen readers and mocked up first; a font rebuild | — |
| 6 | T4 Element calculation, with the T5 settings | XL | Progressions that differ by gender, obstacle years and yearly sme ba, each needing a vector; the readings of every result need sources. Mo is not planned. The gender setting is small but nothing reads it before T4, so it ships with it | — |
| 7 | M1 Meditation Timer and Bell | M | Requested feature: meditation timer and randomized periodic bell (MindBell functionality) | Port audio/alarm logic from MindBell, build Compose UI |

## 1.1 — 旧暦 page

### R4. Visual cues

- **Activity glyphs** on the "In brief" line, in place of its counts, and in
  its breakdown (SPEC §10.4), drawn in the stroke style of `ui/Icons.kt` and
  licensed with the app (MPL-2.0). The `Activity` entries (SPEC §8.2) fall
  into about twenty families, one glyph each; a family field on `Activity`
  keeps the grouping testable:

  | Glyph | Covers, for example |
  | --- | --- |
  | wedding | weddings, marriage, taking a bride, betrothal gifts, marriage talks |
  | journey | travel, setting out, long journeys, coming home |
  | sea | boarding ships, sea travel, sea voyages |
  | moving house | moving house, setting up a branch family, retiring |
  | building | building, raising pillars, raising the ridgepole, roofing, gates, walls, storehouses, house repairs, driving nails |
  | earth | moving earth, digging, earthworks, breaking ground, laying foundations, filling holes |
  | well | digging wells, wells |
  | field | sowing, planting, grafting, harvesting, cutting grass, felling trees, opening rice bales |
  | shop | opening a shop or business, buying, buying land, receiving money, money talks |
  | agreement | contracts, agreements, promises, negotiations, consultations, disputes |
  | beginning | beginnings, new ventures, starting school or lessons, entrance exams, taking up office, announcements |
  | medicine | taking or starting medicine, acupuncture, visiting the sick |
  | funeral | funerals, burial, interments, memorial services, mourning, building graves |
  | shrine | shrine rites, prayer, shrines and altars, devotion, making wishes |
  | clothes | sewing, new clothes, first wearing of new clothes |
  | name | naming, naming a child |
  | household | clearing out, throwing things away, putting things in order, an unclean house |
  | blade | blades, bloodshed, hunting |
  | fire | fire |
  | everything | everything |

  Times of day from the rokuyō ("the morning", "noon", "the afternoon",
  "morning and evening") are not activities: they get a day arc with the good
  hours filled, not a glyph.
- **The rest of the page:**
  - 六曜: the day arc above, so 先勝 and 先負 read at a glance.
  - Solar term and 雑節: a ring of the 24 terms with the current one and the
    season's 土用 and 彼岸 spans marked.
  - 干支: line glyphs for the twelve animals and the five elements.
  - 恵方: a compass rose with the year's bearing (the degrees are already in
    `Ehou`).
  - Month and year stars: on the same 3×3 board as the day star.
- **The Tibetan page** gets the same treatment in a later release, using the
  same glyph set.
- **Diagrams in the reading sheets:**
  - 十二直: a dial of twelve with today's station marked.
  - 二十八宿: a ring of 28 in its four quadrants of seven.
  - 九星: the 3×3 board (後天定位盤) with today's star, each cell carrying its
    trigram, direction and colour (the data of `Texts.KYUSEI`). With the nine-star reading on,
    the 本命星 is marked too.
  - 六曜: a strip of six with today's step.
- **Trigrams** (☰☱☲☳☴☵☶☷) drawn as vectors, not taken from a font.
- **Tone on the sheet** as a coloured band, not only the row's dot.
- Every diagram has a content description for screen readers. New kanji in
  diagrams (direction and trigram names) need the font rebuild
  (`tools/subset_fonts.py`).
- Mock up on the design canvas before building.

## Tibetan page

### T2. Meaning of the components and day details

The lunar mansion's reading and Henning's thirteen activity lists are built
(SPEC §5.10), with the Tibetan day in brief. What is left waits on sources:

- Yogas, karaṇas, lunar days: a source is still needed for each. Henning's
  destructive list names Viṣṭi among what is good for it, which a karaṇa
  reading could carry once karaṇas have one.
- More activities: Henning gives a selection and meant to add others; the
  full lists are in the *'bras rtsis bai dkar dgongs don kun phan me long*.
- The doubtful mansion entries (SPEC §5.10), Uttarāṣāḍhā above all, can be
  settled from the Tibetan text of that list.

### T3. Element colours and personal mansions

The sme ba shows in the colour of its box (SPEC §10.3), and the four aspects
with the pebbles of the year, month, day and hours are built (SPEC §5.9).
What is left waits on a source or on design:

- **Element colours:** wood green, fire red, earth yellow, iron white, water
  black or blue (Berzin 3). The day's element appears only inside the day
  line, so it first needs a place of its own on the page.
- **Personal mansions:** the app has the life, soul and deadly weekday (srog,
  bla, gshed) of the birth animal. Berzin names six personal mansions (srog,
  bla, dbang, skeg, btub, gshed skar); the table per animal still needs a
  source. The *White Beryl* OCR has no hit for these names, and a full-text
  search of the Rinchen Terdzö (rtz.tsadra.org, 2026-09-29) found none either,
  nor any of the karaṇa vishti or of the eight names the White Beryl OCR does
  not confirm (SPEC §10.3). The table likely comes from almanac practice (the
  yearly Men-Tsee-Khang lo tho) or a calculation manual.
- Whole days stay uncoloured, as on the 旧暦 page (SPEC §10.4). The element
  pair already marks four of its ten pairs as inauspicious.

### T4. Divination

- **Element calculation ('byung rtsis)** is arithmetic: birth animal and
  element, sme ba and spar kha of year and day (the day's already computed,
  Janson E.9–E.11), progressed animal, element and sme ba for each year of
  age (Berzin 3, 4; they differ for men and women, so they need a gender
  setting, see T5), obstacle years (keg). The yearly sme ba appears to follow the
  same count as the 九星 year star (1 at a wood-rat year, counting down), so
  it can share code with the 九星 year star (SPEC §7.5) once a vector confirms it. The readings of each
  result need sources; the calculated positions do not.
- **Mo** (Mipham's dice: two throws of the ARAPACANA die, 36 outcomes; other
  systems use three dice or a mala) has no calculation beyond a random draw.
  Every reading is a text, and the English ones are copyrighted
  translations (Goldberg, *Mo*, Snow Lion 1990). Berzin notes it is done in
  a meditational context after a retreat. Not planned.

### T5. Personal settings

Built together with T4: nothing else reads the gender.

Birth date and gender sit together in one "Personal" section of the menu,
since every personal reading needs one or both: the personal days of both
calendars and the nine-star reading need the birth date; the progressions of T4 need both.

- Gender: not set, male or female; the tradition defines the progressions
  for these two only. While it is not set, the readings that need it are
  hidden rather than guessed.
- Both stay on the device only, as the birth date does now (SPEC §10.5).

### M1. Meditation Timer and Periodic Bell

Porting the core functionality of the open-source MindBell app into Zanshin Calendar using modern Android architecture (idiomatic Kotlin, Jetpack Compose).

- **Meditation Timer**: A countdown UI built in Compose. Will use a Foreground Service with a persistent notification to ensure the timer finishes reliably without being killed by modern Android battery optimization.
- **Periodically Sounding Bell**: A background chime that rings at either fixed intervals or randomized intervals (matching MindBell's original functions, ringing within an active daytime window).
- **Audio**: Needs a bundled bell sound (e.g., OGG/MP3) with an F-Droid compatible free license (such as Apache 2.0 or CC0).
- **Implementation**: Avoids the deprecated MindBell background service patterns. Uses modern `AlarmManager.setExactAndAllowWhileIdle()` (or equivalent WorkManager scheduling) with a `BroadcastReceiver` to handle audio playback efficiently. Must maintain the strict `No INTERNET` policy.

## Localisation

English stays the source language. The order: Russian, translated by the
project; then Japanese; then German, French, Spanish, Portuguese and
Chinese (Simplified and Traditional, served separately), all through Hosted
Weblate.

The text is out of the code: interface text in `strings.xml`, the engines'
and readings' text in the catalog, dates and ordinals by the app's language
(SPEC §8.2, §10.1). A language is added as files, not code.

### L3. Russian, by the project

Built: the interface strings, the catalog and the ru-RU store listing, with
the language switch in the menu (SPEC §8.2, §10.5).

- Done when the owner has read the app in Russian on the phone and the
  fixes that brings are in.

### L4. Hosted Weblate

For Japanese first, then German, French, Spanish, Portuguese, Simplified
Chinese and Traditional Chinese.

The repository is ready: partial translations fall back to English string
by string, tests guard keys, placeholders, `locale_tag` and the
completeness of offered languages, and [weblate.md](weblate.md) gives every
setting of the project and its three components. What is left is on
Weblate and GitHub, with the owner's accounts:

- Push `main`; create the project on Hosted Weblate (Libre plan) and its
  components as in weblate.md; approve the imported Russian strings; add
  the GitHub webhook.
- Not before 2026-12-28: the Libre plan wants three months of active
  development, and development began on 2026-09-28 (weblate.md,
  *Eligibility*). The README then names Weblate.
- Split the catalog: the Wikipedia-adapted readings into a CC BY-SA
  component of their own (weblate.md, *The catalog's licence*).
- Fill the glossary with the kanji and Tibetan terms and their English.
- Japanese goes right after Russian, as the first Weblate language,
  translated there by the owner together with invited friends; the owner
  and those of them who read Japanese well are its reviewers. Its
  readings of the 旧暦 page can be written from the Japanese sources
  directly, but Todan's copyrighted wording still may not be copied;
  Wikipedia's CC BY-SA wording may, with attribution.
- The app itself gains no network access: translations arrive in the source
  and ship with a release.

## Later

- **Fortune-telling page** (九星気学 in full): 本命星, 月命星, the year, month
  and day boards, lucky directions and the 五黄殺, 暗剣殺, 本命殺 and 的殺
  directions. All of these can be calculated from the boards; the readings need
  sources. Only calculated positions, never free interpretation.
- Kyūreki: 神吉日, 凶会日, 五墓日, 時下食; 七十二候 (SPEC §3).
- Tibetan: Tsurphu version; Rishi-star bathing week (SPEC §3).
- Moon rise and set; notifications (SPEC §3).
- The widget: sizes and content (SPEC §10.6).
