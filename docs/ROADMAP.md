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
| 1 | T3 The hour's pebbles, element colours, personal mansions | S | The hour is one more row once the page knows the time; a colour per element and a table per animal, once each has what it needs | A time of day and the hours panel (a round clock); a place for the element colour; a source for the mansions |
| 2 | L4 Hosted Weblate | M | Project setup, three components and the review rules; the translating itself is outside the code and open-ended | L3, which brings the switch |
| 3 | L3 Russian, with the L2 switch | L | Every key and reading translated and checked against the original sources, the store listing, and a full read-through on the phone. The switch itself is small (`locales_config.xml` and a start-up wrapper for Android 8–12) but has nothing to offer before a translation exists, so it ships with this one | — |
| 4 | T2 Tibetan readings | L | Own-English summaries of Henning for mansions and activities; yogas, karaṇas and lunar days still lack a source | Sources |
| 5 | R4 Visual cues | XL | About twenty activity glyphs, a dozen diagrams and the trigrams, each drawn, described for screen readers and mocked up first; a font rebuild | — |
| 6 | T4 Element calculation, with the T5 settings | XL | Progressions that differ by gender, obstacle years and yearly sme ba, each needing a vector; the readings of every result need sources. Mo is not planned. The gender setting is small but nothing reads it before T4, so it ships with it | — |

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

The five components (weekday, lunar day, mansion, yoga, karaṇa) and the
lunar-day cycles get reading sheets with "good for" and "avoid", mapped to
the same `Activity` entries as the 旧暦 readings (SPEC §8.2).

- Mansions: Henning, *Horary and electional astrology of the five
  components* (kalacakra.org/calendar/tibast03.htm), after the White Beryl
  and the Treasury of Jewels: each mansion's nature, activities, food and
  planet.
- Activities: the same page lists, per activity, the good and bad weekdays,
  mansions, solar-day animals and trigrams (from the *'bras rtsis bai dkar
  dgongs don kun phan me long*). This is the Tibetan counterpart of the 旧暦
  "good for / avoid" lists.
- Yogas, karaṇas, lunar days: a source is still needed for each.
- Copyrighted sources, so own English summaries (SPEC §8).

### T3. The hour's pebbles, element colours and personal mansions

The sme ba shows in the colour of its box (SPEC §10.3), and the four aspects
with the pebbles of the day, month and year are built (SPEC §5.9). What is
left waits on a source or on design:

- **The hour's pebbles.** The divination of health reads the hour too
  (chart 8.1): the two-hour periods from the hare at daybreak, each hour's
  element from the day's by its animal (hare, monkey, ox son; dragon, bird,
  tiger friend; snake, dog enemy; horse, pig mother; sheep, mouse identity;
  Gyurme Dorje p. 90, Table 2.7). The page shows a day, not a moment, so it
  needs the current time and the hours' boundaries (from sunrise, or fixed
  clock hours; to be settled from the source) before it has a place.
  The owner's design: an icon opens an hours panel, a round clock with each
  two-hour period coloured by its pebbles and the workings on tap, as the
  day's rows have them.

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

## Localisation

English stays the source language. The order: Russian, translated by the
project; then Japanese; then German, French, Spanish, Portuguese and
Chinese (Simplified and Traditional, served separately), all through Hosted
Weblate.

The text is out of the code: interface text in `strings.xml`, the engines'
and readings' text in the catalog, dates and ordinals by the app's language
(SPEC §8.2, §10.1). A language is added as files, not code.

### L2. Language switch

Built together with L3: with English alone there is nothing to switch to.

- Android 13 and later: `res/xml/locales_config.xml` and
  `android:localeConfig`, so the system offers a per-app language in
  Settings › Apps. No code, no dependency.
- Android 8–12 (minSdk 26): a language entry in the menu's settings, next to
  the Personal section (T5), applied with a small locale wrapper at start-up
  rather than a new AppCompat dependency.
- Default: follow the system language, English when it is not supported.

### L3. Russian, by the project

- `values-ru/strings.xml` (with `locale_tag` = ru and Russian date
  patterns), `texts_ru.properties` and a store listing in
  `fastlane/metadata/android/ru-RU/`. ICU writes Russian ordinals as bare
  numbers, so the `month` pattern carries the ending ("%1$s-й месяц").
- Readings are translated from the English summaries, then checked against
  the original source, since an error in an "avoid" list misleads.
- Established Russian terminology where a source gives one: Study Buddhism
  publishes Berzin's astrology articles in Russian.
- Licences: the MPL-2.0 summaries translate freely; wording adapted from
  Japanese Wikipedia stays CC BY-SA 4.0 in translation, with its
  attribution. The reading sheet adds "translation of the English summary".
- Done when every key is translated, `CatalogTest` passes for `ru`, and the
  owner has read the app in Russian on the phone.

### L4. Hosted Weblate

For Japanese first, then German, French, Spanish, Portuguese, Simplified
Chinese and Traditional Chinese.

- A project on Hosted Weblate (gratis for libre projects), one component for
  `strings.xml`, one for the reading catalog, one for the store listing.
- Reviews switched on for the reading catalog: a translated reading ships
  only once a reviewer has approved it, for the same reason as in L3.
- Weblate commits through a pull request, never straight to `main`.
- Chinese as two separate languages, each translated and reviewed on its
  own, not converted from one to the other: `values-b+zh+Hans` and
  `values-b+zh+Hant` in the app, `zh-CN` and `zh-TW` in the store listing.
- Japanese goes right after Russian, as the first Weblate language,
  translated there by the owner together with invited friends; the owner
  and those of them who read Japanese well are its reviewers. Its
  readings of the 旧暦 page can be written from the Japanese sources
  directly, but Todan's copyrighted wording still may not be copied;
  Wikipedia's CC BY-SA wording may, with attribution. The Weblate glossary
  holds the terms the app already uses (十二直, 選日, …).
- The gratis Libre plan keeps a project Public: any signed-in user can
  contribute to any language, including Japanese (Weblate docs, *Access
  control*). Quality rests on reviews, which can be set per language, and on
  reviewing each Weblate pull request before merging. Whether unapproved
  strings are left out of Android `strings.xml`, which has no state field,
  is untested; until it is, the pull request is the gate.
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
