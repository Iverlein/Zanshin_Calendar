# Zanshin Calendar — Roadmap

Planned work that is not yet built. [SPEC.md](SPEC.md) describes what the app
does; this file describes what it is meant to do next. When an item is built,
its settled design moves into the SPEC and the entry here is removed.

The rules of the SPEC apply to everything below: no text without a published
source (§8), nothing the app cannot calculate, no `INTERNET` permission (§2).

## 1.1 — 旧暦 page

### R1. Activity vocabulary

Prerequisite for R3 and R4. The "good for" and "avoid" lists in `Texts.kt` are
free strings, and the same activity appears under several wordings
("weddings", "marriage", "taking a bride"; "building", "any building work").
Each string gets an `Activity` (wedding, travel, moving house, building,
earthworks, business, medicine, sowing, …); the source wording stays in the
reading sheet.

- Merge only wordings that name the same act. When in doubt, keep them apart.
- Done when: every list entry maps to an `Activity`, and a test fails on an
  unmapped string.

### R2. 九星気学 option

A switch in settings, off by default, that adds a personal 九星 row to the
middle band.

- Uses the birth date already stored for the personal days; switching it on
  without one asks for it.
- 本命星 is `Rekichu.yearStar()` of the birth year, reckoned from 立春. The day
  and month stars are already computed.
- The row gives the relation of the 本命星 to the day star by their elements:
  相生, 比和 or 相剋, with its tone.
- Source: needed before any wording is written. Japanese Wikipedia 九星気学
  (CC BY-SA) is the first candidate. The sheet states that 気学 is a separate
  school, not part of the historical almanac.
- Done when: the relation for every pair of stars is in a test with its
  source, and the owner has confirmed the row on the phone.

### R3. Day summary

A summary of the day built only from what the sources state or what can be
counted. **No verdict, score or weighting the tradition does not give.**

What it may contain:

- **The two days the almanac itself flags.** 受死日 is printed as ● in the
  lower band, hence 黒日, and the source says no other annotation need be read
  on it. 天赦日 alone carries the note 万よし. Source: Japanese Wikipedia
  暦注下段.
- **The day's annotations grouped by their tone**, each by name.
- **Activities** (R1): which annotations name an activity as good and which
  as to be avoided. Where they disagree, both sides are shown with their
  sources; the conflict is not resolved.
- **Personal part**, when a birth date is set: the 三箇の悪日 and the R2
  relation.

Placement: one line under the rokuyō in the header — the ● or 万よし mark when
the day has one, then "good for" and "avoid" as R4 glyphs; a tap opens the
breakdown. The same line is the candidate content for the widget (SPEC §10.6).

Open: is there a published rule for which annotation outranks which (下段 over
中段, 二十八宿 over 十二直)? Without one, R3 stays a listing.

### R4. Visual cues

- **Activity glyphs** beside "good for" and "avoid", drawn in the stroke style
  of `ui/Icons.kt` and licensed with the app (MPL-2.0). The 119 strings in
  `Texts.kt` fall into about twenty families, one glyph each:

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
    trigram, direction and colour (the data of `Texts.KYUSEI`). With R2 on,
    the 本命星 is marked too.
  - 六曜: a strip of six with today's step.
- **Trigrams** (☰☱☲☳☴☵☶☷) drawn as vectors, not taken from a font.
- **Tone on the sheet** as a coloured band, not only the row's dot.
- Every diagram has a content description for screen readers. New kanji in
  diagrams (direction and trigram names) need the font rebuild
  (`tools/subset_fonts.py`).
- Mock up on the design canvas before building.

### R5. Marking good and bad days

What tradition gives:

| Mark | Tradition | Source |
| --- | --- | --- |
| ● | 受死日, printed as a black dot in the lower band, hence 黒日 | Japanese Wikipedia 暦注下段; koyomi8.com |
| 万よし | note on 天赦日 only | Japanese Wikipedia 暦注下段 |

No colouring of whole days by luck is recorded for the historical almanac.
In the 具注暦 the red writing (朱書き) marks the 二十八宿 and 七曜 in the upper
margin, not luck. On modern Japanese calendars a red date means a Sunday or
holiday, so a red day for "bad" would be misread.

Plan:

- The date picker shows ● under 受死日 and a mark for 天赦日, so both can be
  found when choosing a day.
- The day header shows the same marks (R3).
- The lucky/unlucky colours of the rows stay as they are: an app convention,
  and not presented as traditional.
- No background or font colour for a whole day, since that would need a
  weighting the tradition does not give (R3).

### R6. Home of the deity days

1.0.1 lists 寅の日, 巳の日, 己巳, 甲子 and 庚申 among the 選日, as koyomi8 does for
庚申 and 己巳. They are festival days of deities (Kotobank; Wikipedia 庚申待)
rather than 選日 proper. Move them if a source names a better home.

## Tibetan page

### T1. Tibetan script for every term

Each term shown as the Japanese page shows kanji: Tibetan script, then its
reading, then English, all in the tap balloon. Tibetan terms must read as
easily as the romaji on the 旧暦 page, so the reading is two lines: Wylie
(exact spelling, as the sources print it) and a phonetic spelling (how it is
said), e.g. *sa ga* · Saga.

- Font: the phone ships `NotoSerifTibetan-VF.ttf` (Android 15, checked
  2026-09-29), but not every Android build does. Bundle Noto Serif Tibetan
  (OFL), subset like Shippori Mincho; keep the layout features, since
  stacked letters need shaping.
- Script from the Wylie already in the sources, converted by a tool in
  `tools/` and checked by hand against the source's own script where it
  prints one; the output is committed, as with the other generated tables.
- Phonetics: the THL Simplified Phonetic Transcription of Standard Tibetan
  (Germano and Tournadre, 2003), a published rule set that works from the
  Wylie. The conversion lives in `tools/` with a test of the examples THL
  gives; names with an established spelling in the app's sources (festival
  names from Edition Rabten) keep that spelling.

### T2. Meaning of the components and day details

The five components (weekday, lunar day, mansion, yoga, karaṇa) and the
lunar-day cycles get reading sheets with "good for" and "avoid", on the
activity vocabulary of R1.

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

### T3. Good, bad and neutral days, and personal marks

What tradition gives:

| Mark | Tradition | Source |
| --- | --- | --- |
| ○ / × | white and black pebbles: one to three of them express the relation of a natal element to a transiting one (mother, child, friend, enemy, same) | Berzin, *Details of Tibetan Astrology 3* (Study Buddhism) |
| colours of the elements | wood green, fire red, earth yellow, iron white, water blue or black | Berzin 3 |
| colours of the sme ba | 1 white, 2 black, 3 navy blue, 4 green, 5 yellow, 6 white, 7 red, 8 white, 9 maroon; "when the magic-square is printed, the colour of each box is in accordance with this scheme" | Berzin, *Details of Tibetan Astrology 4* |

Plan:

- Personal weekdays: the app has the life, soul and deadly weekday (srog,
  bla, gshed) of the birth animal. Add the six personal mansions (srog, bla,
  dbang, skeg, btub, gshed skar), which Berzin names; the table per animal
  still needs a source.
- Show the relation of the day's element to the birth element with the ○/×
  pebbles, and the sme ba and elements in their colours. That is the
  traditional colouring; whole days stay uncoloured, as on the 旧暦 page (R5).
- The element pair already marks four of its ten pairs as inauspicious.

### T4. Divination

- **Element calculation ('byung rtsis)** is arithmetic: birth animal and
  element, sme ba and spar kha of year and day (the day's already computed,
  Janson E.9–E.11), progressed animal, element and sme ba for each year of
  age (Berzin 3, 4; they differ for men and women, so they need a gender
  setting, see T5), obstacle years (keg). The yearly sme ba appears to follow the
  same count as the 九星 year star (1 at a wood-rat year, counting down), so
  it can share code with R2 once a vector confirms it. The readings of each
  result need sources; the calculated positions do not.
- **Mo** (Mipham's dice: two throws of the ARAPACANA die, 36 outcomes; other
  systems use three dice or a mala) has no calculation beyond a random draw.
  Every reading is a text, and the English ones are copyrighted
  translations (Goldberg, *Mo*, Snow Lion 1990). Berzin notes it is done in
  a meditational context after a retreat. Not planned.

### T5. Personal settings

Birth date and gender sit together in one "Personal" section of the menu,
since every personal reading needs one or both: the personal days of both
calendars and R2 need the birth date; the progressions of T4 need both.

- Gender: not set, male or female; the tradition defines the progressions
  for these two only. While it is not set, the readings that need it are
  hidden rather than guessed.
- Both stay on the device only, as the birth date does now (SPEC §10.5).

## Localisation

English stays the source language. Russian comes first, translated by the
project; German, French, Spanish, Portuguese, Chinese and Japanese follow
through Hosted Weblate.

### L1. Text out of the code

Prerequisite for everything below, and best done before 1.1 adds more text.
Nothing on screen changes.

- UI text (section titles, "begins today", `Labels`) moves from Kotlin into
  `res/values/strings.xml`.
- `core/` is plain JVM, so its readings (`Texts.kt`) and the English glosses
  on the engine enums (`Choku.english` and the like) move into a catalog per
  language under `core/src/main/resources/`, keyed by enum name. A test fails
  when a language lacks a key the English catalog has, and every translated
  reading keeps its `Source`.
- Dates, month ordinals and numbers go through locale-aware formatters
  instead of hand-built strings ("Tue 29 Sep 2026", "8th month").
- Tibetan and Japanese terms, Wylie, phonetics and romaji are not
  translated; only glosses, readings and UI text are.

### L2. Language switch

- Android 13 and later: `res/xml/locales_config.xml` and
  `android:localeConfig`, so the system offers a per-app language in
  Settings › Apps. No code, no dependency.
- Android 8–12 (minSdk 26): a language entry in the menu's settings, next to
  the Personal section (T5), applied with a small locale wrapper at start-up
  rather than a new AppCompat dependency.
- Default: follow the system language, English when it is not supported.

### L3. Russian, by the project

- `values-ru/strings.xml`, the Russian catalog and a store listing in
  `fastlane/metadata/android/ru-RU/`.
- Readings are translated from the English summaries, then checked against
  the original source, since an error in an "avoid" list misleads.
- Established Russian terminology where a source gives one: Study Buddhism
  publishes Berzin's astrology articles in Russian.
- Licences: the MPL-2.0 summaries translate freely; wording adapted from
  Japanese Wikipedia stays CC BY-SA 4.0 in translation, with its
  attribution. The reading sheet adds "translation of the English summary".
- Done when every key is translated, `TextsTest` passes for `ru`, and the
  owner has read the app in Russian on the phone.

### L4. Hosted Weblate

For German, French, Spanish, Portuguese, Chinese and Japanese.

- A project on Hosted Weblate (gratis for libre projects), one component for
  `strings.xml`, one for the reading catalog, one for the store listing.
- Reviews switched on for the reading catalog: a translated reading ships
  only once a reviewer has approved it, for the same reason as in L3.
- Weblate commits through a pull request, never straight to `main`.
- Open: Chinese in Simplified, Traditional or both (`zh-CN`, `zh-TW`)?
- Japanese: the readings of the 旧暦 page can be written from the Japanese
  sources directly, but Todan's copyrighted wording still may not be copied;
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
