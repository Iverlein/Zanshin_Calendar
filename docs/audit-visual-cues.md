# Audit of the visual cues, 2026-10-06

The glyphs and diagrams of SPEC §10.7 were settled on 2026-10-03 and
released in 1.1, when `Activity` had 112 entries. It now has 180: the White
Beryl's lunar dates, karaṇas and weekdays and the *kun phan me long*'s other
activity boxes added 68, all Tibetan, each put into one of the 27 existing
families. This audit asks whether the cues still do their work, and plans
what to draw next.

**Basis.** The working tree on 2026-10-06 at 14:47 (a560b81 plus the
uncommitted weighing of the Tibetan day, in which a lucky day no longer
lifts the date's or the weekday's prohibitions). The numbers come from
`DaySummary.of` for both calendars over every day from 2000-01-01 to
2049-12-31 (18,263 days), dumped by a throwaway test that is not part of the
repository; outweighed activities are left out, as the page leaves them
out. The screens are the debug build on `zanshin-test`, 2026-10-06, Lhasa
and Kyoto, built just before that change to the weighing.

## 1. The In brief line

### What it shows

One glyph per family named good, one per family named to avoid, in the
enum's order; a family in the mixed colour when one of its activities is
named both ways (SPEC §10.7).

| | 旧暦 page | Tibetan page |
| --- | --- | --- |
| Activities named a day (mean / max) | 15 / 32 | 84 / 95 |
| Glyphs on the line (mean / 90th pct / max) | 13 / 18 / 26 | 33 / 37 / 41 |
| Families shown on ≥ 95 % of days | 0 | 21 of 27 |
| Days a family is on both lines | 85 % (mostly disputes: the 旧暦 lists, by design) | 100 % (no disputes: different members of the family) |
| Most activities behind one glyph on one line (median / max) | 3 / 6 | 7 / 12 |
| Share of the notes that are post-1.1 activities | 0 % | 57 % |

**C1. The 旧暦 line works.** 2026-10-06 shows 7 good and 9 to avoid on two
short rows; every family is absent on at least a tenth of the days, so a
glyph's presence says something. Kept as it is (owner, 2026-10-06).

**C2. The Tibetan line has stopped saying anything.** It shows nearly every
family every day, and every day some family on both lines, drawn green
and red at once though nothing is disputed: RITE on both lines on 99 % of
days, TRADE on 95 %, AGREEMENT on 86 %. On the emulator 2026-10-06 drew 23
good glyphs in three rows; with the current weighing the same day names 40
works good and 44 to avoid. Two causes:

- The Tibetan factors name far more works (84 a day against 15), and
  the families are wide: RITE gathers 12 activities, PRAYER 13, FIELD 10.
- A family's members go opposite ways by their nature. Pacifying is good
  when destroying is to be avoided; AGREEMENT now holds lawsuits beside
  reconciliation.

Regrouping the families alone (§2) makes this worse, not better: a first
regrouping into 32 families draws 40 glyphs a day instead of 33.

**C3. Single activities, by contrast, carry the day.** The common works are
named on nearly every day, always one way (the weighing decides each),
and their side changes from day to day:

| Activity | good | avoid | not named |
| --- | --- | --- | --- |
| journey | 45 % | 55 % | 0 % |
| wedding | 27 % | 73 % | 0 % |
| building | 56 % | 44 % | 0 % |
| moving house | 76 % | 20 % | 4 % |
| trading | 71 % | 26 % | 3 % |
| haircuts | 47 % | 53 % | 0 % |
| funerals | 27 % | 72 % | 1 % |
| taking up office | 66 % | 34 % | 0 % |
| study | 77 % | 23 % | 0 % |
| new clothes | 55 % | 45 % | 0 % |
| lawsuits | 32 % | 65 % | 3 % |
| medical treatment | 56 % | 5 % | 40 % |

## 2. The families: glyphs that no longer fit

The post-1.1 activities were placed in the nearest family, and several now
sit under a picture of something else. Seen in the breakdown on
2026-10-06: "care of horses", "saddling horses and mules" and "raising dogs"
under the sprouting field; "destructive activity" and "prosperity rites"
under one glyph.

| Family (glyph) | Members that do not fit | Note |
| --- | --- | --- |
| FIELD (sprout) | taming animals, cattle work, horses, saddling, raising dogs, dairy | 6 of its 10 members are animals |
| RITE (vajra) | pacifying, increasing, controlling, destroying, cursing, averting, suppressing *sri* | the four actions point opposite ways (C2); at 22 dp the vajra reads as a chain of rings |
| PRAYER (mala) | ordination, vows, serving the teacher, brahmins; offerings, smoke offerings | 13 members: practice, vows and offerings in one |
| HOUSEHOLD (broom) | bathing, servants, brewing, perfume, parents, giving a child | none of its 6 Tibetan members is about clearing house |
| HAIRCUT (scissors) | washing hair | |
| AGREEMENT (sealed page) | lawsuits, judging cases; meeting the great, petitions, statecraft | quarrels and audiences beside contracts |
| CONDUCT (figure) | friendship | the rest (lasting and moving work) fits |
| CELEBRATION (cups) | good fortune | games, feasts, spectacles, horse racing fit |

Fits that stay: WEDDING (love, dowry), LEARNING (divination, astrology,
teaching and hearing dharma), MEDICINE (bloodletting, making medicine),
BLADE (war, killing, robbery, poison: one glyph for harm is right), SACRED
(consecration, banners), EARTH (dams), WELL (water work), BUILDING (hearth),
TRADE (trading, giving out, moving goods).

## 3. Rows without a graphic

On the Tibetan page, after 1.1 the Almanac gained rows that have no cue,
while the 旧暦 page has a diagram for every term of its kind.

| Row | Now | What would serve |
| --- | --- | --- |
| Named combination | text | its sheet: the 7 × 27 table of weekday by mansion with today's cell, as the element pair has its 4 × 4 grid |
| Nectar periods | the hours as text | a small 24-hour arc with the two periods, the hours dial of the panel at row size |
| Lunar date (Nanda … Pūrṇa) | text | a strip of the five classes, today's marked, as the 六曜 strip |
| Karaṇa | text | its sheet: the ring of 11, seven moving and four fixed |
| Yoga | text | its sheet: the ring of 27, as the mansion's |
| Rāhu | text | a compass with Rāhu's direction; the direction is in the reading's prose only, so this needs it as data first |
| Special days, personal days | text | none needed: the row's dot says enough |

The five components rows for karaṇa and yoga (`FactRow` without `lead`)
would take the same small rings.

## 4. Plan

Decided by the owner on 2026-10-06: the Tibetan line becomes **one row of
glyphs, however long the full list**, green for the best works by the sum
of the weights of the factors that name them, red for the worst, those
first to avoid; it is not a fixed set of works (a fixed row would repeat
the same glyphs daily). The 旧暦 line stays on families as it is.

### V1. One row on the Tibetan line — M

- **Score.** An activity's score is the sum of the weights of the factors
  standing on its side (the voices of `weigh`, a group such as the two
  combinations or the special days counting once). Outweighed factors
  add nothing, so nothing outweighed is shown. The score only picks the
  glyphs; it changes no verdict and no side.
- **Weights.** The sources give weights to three factors only: the date 1,
  the planet 4 and the mansion 8 (KP rule 1, open question 12), and they
  put the mansion above the weekday where the rank puts it below. The
  proposal is weights from the rank the weighing already uses: the
  combination 10, Rāhu 9, weekday 8, mansion 7, special days 6, date 5,
  karaṇa 4, yoga 3, day animal 2, trigram 1. Equal scores keep the
  breakdown's order (more factors first, then the stronger). SPEC §5.12
  says that these weights order the display and are not a source's.
- **The row.** Eight glyphs fit at 22 dp. Green first, best first; then
  red, worst first; four each, and a side with fewer than four leaves its
  places to the other. A glyph appears once: where a family's best work is
  green and another of its works red, it stands on the side where it
  scores higher and the next family takes the other place. Without that
  rule the row would show the same glyph green and red on 44 % of days
  (2000–2049); with the V2 families still on 18 %.
- **Behaviour, measured with these weights:** about 5 of the 8 glyphs
  change from one day to the next; the place at the cut is decided by
  the tie order on 71 % of days.
- **Code.** `DaySummary.bestGood` and `worstAvoid` (core), tested on the
  dates of `DaySummaryTest` and on the once-only rule over a year;
  `BriefRow` draws one `Row` on the Tibetan page and keeps the two lines
  on the 旧暦 page; the spoken text names the works the row stands for.
  The breakdown is unchanged and still lists everything.
- **SPEC.** §5.12 (the score), §10.3 and §10.7 (the row).

### V2. Families regrouped, and their glyphs — M

For the breakdown, where every row carries its family's glyph, and for the
row of V1, where finer families mean fewer clashes. Eleven new families,
38 in all; the mapping is checked against each activity's wording in
[sources/](sources/README.md) before it is fixed.

| New family | Members | Glyph (to draw) |
| --- | --- | --- |
| LIVESTOCK | taming animals, cattle work, horses, saddling, raising dogs, dairy, buying livestock | a saddle or a halter: not an animal head, which already stands for the twelve signs |
| Four actions, one family each | pacifying · increasing · controlling · destroying, with cursing, averting and suppressing *sri* under the action their wording names | the hearths of the four fire offerings: circle, square, half-moon, triangle. RITE keeps health and wealth, prosperity rites, fire offerings, empowerment and rainmaking |
| VOWS | ordination, vows, serving the teacher, brahmins | an alms bowl |
| OFFERING | offerings, smoke offerings | an offering bowl with incense smoke |
| BATHING | bathing, washing hair, perfume | a ewer |
| KIN | parents, giving a child, servants, friendship | two figures |
| DISPUTE | disputes, lawsuits, judging cases | two facing arrows |
| AUTHORITY | meeting the great, petitions, statecraft, taking up office | a parasol, the emblem of rank among the eight auspicious symbols |

Measured with a first mapping (one "wrathful" family in place of the four
actions, 32 families), the days a family stands on both of today's lines
fall: HAIRCUT from 32 % to 0, HOUSEHOLD from 45 % to 0, AGREEMENT from
86 % to 41 %, FIELD from 79 % to 35 %. That wrathful family is still on
both lines on 59 % of days, which is why the four actions are drawn apart
here.

- **Glyphs** in the R4 style (24-unit grid, stroke 1.4, round caps), and a
  redrawn RITE glyph that reads at 22 dp. Path data in `CueGlyphs.kt`.
- **Sources.** The four hearth shapes need a published source in SPEC
  §10.7 before they are drawn (for example Beer, *The Encyclopedia of
  Tibetan Symbols and Motifs*, 1999: to check); so does the parasol as a
  sign of rank.
- **Tests.** The existing test (every family used) keeps the enum honest.
  Two 旧暦 activities move, disputes to DISPUTE and taking up office to
  AUTHORITY, so the 旧暦 line shows those glyphs on some days (13.1 glyphs a
  day before and after); `DaySummaryTest`'s family list of 2026-10-23 is
  checked again.
- **No font rebuild:** no new kanji.

### V3. Diagrams for the Tibetan rows — M

From §3, in order of use: the named-combination table (sheet), the nectar
arc (row and sheet), the lunar-date strip (row), the karaṇa and yoga rings
(sheets, and small rings on the five components rows). Rāhu's compass waits
until its direction is data, read from the texts with a vector test.

### Order and checks

1. A mock-up canvas, as for R4: the Tibetan page of a day decided by the
   combination and of one decided by the count, each with its row; the
   breakdown with the new families; the new glyphs at 22 dp beside the
   old. Data from the engine, not by hand. Owner's approval.
2. V1 and V2 together (V1 works with today's families, but clashes on
   44 % of days against 18 %), then V3.
3. Each step on the emulator, both pages and a sheet; a release build
   before tagging (R8 renames the family enum's classes, SPEC §12).

### Settled

- **The weights** of V1 follow the rank. KP rule 1's numbers (the date
  one, the planet four, the mansion eight) are the Kashmiri paṇḍita's,
  which the White Beryl sets aside for the Phugpa order of strength (vol.
  2, p. 376; [sources/weighing.md](sources/weighing.md)), so they are not
  an alternative within the tradition the app follows.
- **The four hearths** are sourced: round for pacifying, square for
  increasing, semicircular for power, triangular for fierce rites (Gyurme
  Dorje, *Tibetan Elemental Divination Paintings*, 2001, glossary, *burnt
  offerings*, after Klong chen pa and Beyer, *The Cult of Tārā*, pp.
  264–275).
- **The parasol** found no source calling it a sign of rank; office and
  the great are drawn as a throne, a picture of the thing and no emblem.

## 5. Built, 2026-10-06

V1, V2 and V3 as above, with these differences from the plan:

- Livestock is a horseshoe (a saddle read as a table at 22 dp); offerings
  are a butter lamp (a bowl with smoke would have looked like the
  celebration cups); the hearths each hold a small flame.
- Bathing takes perfume; family and friends take servants; brewing stays
  with the household; averting rites stay with the rites, as their
  wording names no action; cursing and suppressing *sri* go to fierce
  rites. Prayer is now "prayer and practice".
- The row's once-only rule acts on what is shown: a family moves to its
  other side only when it would be shown on both, so a family whose
  heavier work missed the cut on one side can still stand on the other.
- The karaṇa and yoga rings sit on their Almanac rows, as the mansion's
  does; the five components rows carry no second copy.
- Rāhu's compass is left (ROADMAP V).

Measured again on the built code, 2000–2049: four and four glyphs on 92 %
of days, otherwise one side's spare places to the other; about six of the
eight glyphs change from one day to the next. The 旧暦 line draws 13.1
glyphs a day as before; the dispute glyph is on it on 17 % of days, the
throne on 3 %. SPEC §5.12 and §10.7 hold the design.
