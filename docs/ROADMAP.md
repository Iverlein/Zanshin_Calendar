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

## Later

- **Fortune-telling page** (九星気学 in full): 本命星, 月命星, the year, month
  and day boards, lucky directions and the 五黄殺, 暗剣殺, 本命殺 and 的殺
  directions. All of these can be calculated from the boards; the readings need
  sources. Only calculated positions, never free interpretation.
- Kyūreki: 神吉日, 凶会日, 五墓日, 時下食; 七十二候 (SPEC §3).
- Tibetan: Tsurphu version; Rishi-star bathing week; Tibetan script, after
  checking font coverage on the phone (SPEC §3, §10.6).
- Moon rise and set; notifications; translations beyond English (SPEC §3).
- The widget: sizes and content (SPEC §10.6).
