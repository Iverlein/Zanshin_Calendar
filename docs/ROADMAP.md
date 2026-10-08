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
order can differ from the rank: it is under *Blocks*. An item with no use on its own is ranked
with the one that gives it a use. "Later" is not ranked.

| # | Item | Size | Why | Needs |
| --- | --- | --- | --- | --- |
| 1 | T3 Element colours | S | A colour per element, once it has a place | A place for the day's element on the page (sourced: Berzin 3) |
| 2 | L4 Hosted Weblate | S | The repository is ready (docs/weblate.md); the project, its three components and the review rules are set up on Weblate with the owner's account; the translating itself is outside the code and open-ended | Not before 2026-12-28 (three months of development, for the Libre plan) |
| 3 | L3 Russian | S | Translated and passing `CatalogTest`, with the store listing and the language switch; what is left is the owner's read-through on the phone and the fixes it brings | — |
| 4 | T2 The Tibetan page's gaps | L | The page's readings are built; what is left is the hour of KP's rule 2 and answers that rest on unseen scans: a work plan of nine items below, T2.1–T2.3, T2.5–T2.8 and T2.4's display half built; then WB above all (T2.10–T2.18): WB's seasons (built), its almanac page, the burning dates (built), the eight classes' and nāgas' strikes, 11/6, hair by date, the la's place (built), Russian terms (built) | For T2.9, books only lent on archive.org |
| 5 | E Election: the best day for a work | L | The day's weighing (SPEC §5.12) read across days for one work, best first, with the hours of each day; the order of strength the texts give, nothing added to it; a reading of WB ch. 34's rising signs for the hours | E4 waits on a reading |
| 6 | T4 Element calculation, with the T5 settings | XL | Progressions that differ by gender, obstacle years and yearly sme ba, each needing a vector; the readings of every result need sources. Mo is not planned. The gender setting is small but nothing reads it before T4, so it ships with it | — |
| 7 | M1 Meditation Timer and Bell | M | Requested feature: meditation timer and randomized periodic bell (MindBell functionality) | Port audio/alarm logic from MindBell, build Compose UI |
| 8 | U The Tibetan page's UX | M | Five small items, the deciding factor named built (U2); left: the combination as one row, the page in weighed / yours / also-today sections, the brief grouped by voices, the hours on the In brief row; no side or tone changes | — |

## Blocks

The build order, by what each item waits on rather than by its size. A
block is built in one go and is done when each of its items meets its own
"Done when" below. **"Build the next block"** means the lowest-numbered
block not marked built; a built block keeps its number, marked built with
its date, so each prompt stays valid. A block blocked from outside (14)
moves up as soon as it is unblocked.

| # | Block | Waits on | Prompt |
| --- | --- | --- | --- |
| 1 | U1 + U3 | — | Build block 1: U1 and U3, one combination row and the Almanac in weighed, yours and also-today sections. |
| 2 | E6, the "For you" half | — | Build block 2: E6's "For you" half, every work to avoid on the person's enemy weekday and death mansion. |
| 3 | U4 + U5 | 1 | Build block 3: U4 and U5, the brief grouped by voices and the hours on the In brief row. |
| 4 | T3 Element colours | 1; the day element's place is the owner's choice | Build block 4: T3, a place for the day's element on the page and its colour. |
| 5 | T2.11 WB's almanac page | — | Build block 5: T2.11, read WB pp. 171–178 on the scan and inventory every entry against the app. |
| 6 | T2.15 Hair by date | 5 | Build block 6: T2.15, read WB p. 404's washing results and settle the haircut sheet against FPMT. |
| 7 | T2.14 The 11th month's 6th | 5; then the owner's decision | Build block 7: T2.14, read WB p. 226's nine bad days, then ask me to decide against Rabten's festival. |
| 8 | T2.13 The eight classes and the nāgas | 5 | Build block 8: T2.13, read WB pp. 226–235 and build the eight classes' and nāgas' strikes and turnings. |
| 9 | E1 + E2 + E3, with E6's election half | 2 | Build block 9: the election, E1 engine, E2 screen and E3 hours, with E6's election half. |
| 10 | E5 The 旧暦 election | 9 | Build block 10: E5, the 旧暦 election on E2's screen, unranked. |
| 11 | E4 The works' own rising signs | 9 | Build block 11: E4, read WB ch. 34's rising signs for each work and build them as the work's own hours. |
| 12 | M1 Meditation timer and bell | — | Build block 12: M1, the meditation timer and the periodic bell. |
| 13 | T4 + T5 Element calculation and gender | — | Build block 13: T4 with T5, the element calculation and the gender setting. |
| 14 | T2.4's reading half + T2.9 | The scans lent on archive.org (the owner's account) | Build block 14: T2.4's reading half and T2.9, from the borrowed scans. |
| 15 | L3 Russian read-through | The blocks before it that change wording | Build block 15: L3, fix what my Russian read-through on the phone found. |
| 16 | L4 Hosted Weblate | 15; not before 2026-12-28 | Build block 16: L4, set up Hosted Weblate as docs/weblate.md says. |

## Tibetan page

### T2. The Tibetan page's gaps: work plan

The page's readings are built (SPEC §5.8–5.13, the texts in
[sources/](sources/README.md)) and all fourteen questions for
a reader are answered ([open-questions.md](sources/open-questions.md)). Reviewed on
2026-10-06, what is left are places where the page contradicts itself,
shows a factor it does not weigh, or rests on a thin or machine-read
source. Numbers are over 2000–2049 (18,263 days) on the code of that day.
How to read the sources is in [sources/PLAN.md](sources/PLAN.md); every
reading task here ends with its facts in a topic file, with edition, page
and image number.

Each item is done when its code has tests, SPEC is changed with it, both
pages and the sheets it touches are checked on the emulator in English and
Russian, and the release build is checked before the next tag (SPEC §12).

#### T2.1 The haircut row, weighed — built 2026-10-06 (SPEC §10.3)

#### T2.2 Rāhu's compass — built 2026-10-06 (SPEC §10.7)

#### T2.3 The person's own days — built 2026-10-06 (SPEC §5.12, §10.3)

The brief's "For you" block, shown and not weighed; WB pp. 337–338 and
345–346 read into [personal-mansions.md](sources/personal-mansions.md). WB
calls a person's own days "of particular importance" but ranks them
against nothing, so the weighing stays as it is. Open question 14,
answered the same evening and checked on the scans of four works: WB's
tables by element are by the clan's element, which the app does not
have, or, "applied the same way", by the birth year's life force, which
it has; the birth weekday is the weekday of birth, and the birth mansion
the mansion the moon passes through on the date of birth (Phug pa Lhun
grub rgya mtsho). All three follow from the birth date. The mansion of
conception has no definition in any source found and is not to be built.
Showing the rest in "For you" is the owner's decision.

#### T2.4 The hours above the day — S built 2026-10-07 (SPEC §10.3), then M

- **Gap.** The texts hold the combination period above every factor of
  the day (KP rule 5, WB vol. 2, p. 376), and KP's rule 2 puts the hour
  (*dus tshod*), "a sharp weapon", above the day's animal sign. The hours
  panel shows the combination period and the nectar periods, but the
  brief, which gives the day's verdict on each work, does not say that an
  hour can overrule it. The hour of rule 2 is not built (SPEC §5.13).
- **Work, S, built 2026-10-07.** The brief gets a "By the hour" block: today's hours whose
  combination period is to be accomplished or avoided, and the nectar
  hours, each opening the hours panel, with one sentence that within its
  hour the combination period outweighs the day's weighing.
- **Work, M (reading).** What makes an hour good or bad against the *nyi
  ma* in rule 2: KP img. 13–14 with WB p. 376 ([weighing.md](sources/weighing.md)),
  then the table it refers to. Built on the hours panel if it is
  calculable.

#### T2.5 The mansion's own lists — built 2026-10-07 (SPEC §5.10, §5.12)

WB's 28 mansion verses (pp. 313–328) are read: every good and avoid list
on the scan, Kṛttikā's «དྲ་གྱོན» (new clothes, where the 1996 print sets
«དྲ་གྱོད») from the Zhol print ([mansion-verses.md](sources/mansion-verses.md)).
Each day mansion's reading now joins its verse's lists, first, to
Henning's list and KP's boxes: 313 new wordings and two new activities
(Bon rites, pitching tents). Henning's "installing a deity" is *rab
gnas*, consecration: his nine mansions for it are all ones whose verse
names consecration good, none whose verse avoids it, while the verses'
temples and images go their own way (Hasta avoids them). *Bcud len*,
which no verse names, is its own act, taking elixirs, no longer the
Japanese pages' taking medicine. The *dar gud* (pp.
329–330) is read and not built: WB says that reckoning lacks a
scriptural source. Measured again on 2000–2049: the tones do not change
(the mansion has none); the days' works grow from 1,530,335 to
1,627,039 (101,811 added; 5,107 gone, silent where a verse contradicts
the mansion's other lists or moved with "installing a deity"), 11,245
change side, and the mansion decides 288,365
instead of 172,008; days against their tone 2,087 to 2,140.
`DaySummaryTest` keeps 1 November 2026 (Ārdrā: killing good) and 28
January 2026 (Kṛttikā: war and raids to avoid).

#### T2.6 WB chapter 34, the works one by one — built 2026-10-08 (SPEC §5.10)

Inventoried, compared and read on the scans
([white-beryl-ch34.md](sources/white-beryl-ch34.md)): the chapter's 65
works are KP's boxes in the same order, so the boxes are its digest. It
is joined to each box (what it names plainly good or bad is added, and
decides where the box has the other side), and five works without a box
are lists of their own (shows, hunting and theft, taming horses,
averting rites, sorcery). Its entries stand at their factors' ranks, as
the works' particular cases; the chapter says nothing of a rank of its
own. Measured on 2000–2049: no day's tone changes, 23,616 works are
added, 9,741 change side, days against their tone 2,140 to 2,085.
`ElectionalTest` keeps the decisions (the offerings' khrums, servants'
4th and 14th, war's animals, rain's mansions). Left open: three entries
whose verse reads two ways, and the name «བྱ་གཞུག» (white-beryl-ch34.md,
*Left out*).

#### T2.7 The day's tone against its lists — built 2026-10-07 (SPEC §5.12, §10.3, §10.7)

The owner chose the counts beside the In brief row ("good 61", "avoid 21",
one above the other). Measured again on the code of that day: 2,087 days
of 18,263 in 2000–2049 run against their tone, and the combination
decides the tone on 9,672; `DaySummaryTest` keeps both.

#### T2.8 Rows that repeat — built 2026-10-07 (SPEC §10.3)

The owner chose to fold the five components into the Almanac rows: the
weekday's, mansion's, karaṇa's and yoga's sheets give the Tibetan name in
script (tap for Wylie and phonetics), the section is gone, and the day
line keeps its facts.

#### T2.9 Answers that rest on unseen scans — owner

- **Gap.** Question 11 (the day's sme ba runs up from the first wood-mouse
  day after the winter solstice) rests on BS and MK, and the reading of
  *dmigs bsal* in part on KD; all three were read in BDRC's etext only,
  their scans being lent on archive.org (`bdrc-W25151` and others,
  [sources/README.md](sources/README.md)). Of question 1, WB's 18th bad
  yoga and its line with «རང་སྐྱེས» have no reading in any source found; the
  app does not use the ranking.
- **Work.** Borrowed with the owner's archive.org account: check the BS
  and MK passages and KD vol. 1, pp. 498–499 on the page, and record them
  as read. Question 1's remainder waits for a reader of the tradition.

#### WB above all: T2.10–T2.18

The owner's rule (2026-10-08): **the White Beryl counts above every other
source.** Where Rabten, FPMT, Henning's lists or a calendar disagree with
it, WB decides; where WB is silent they may stand, marked as theirs. The
items below come from comparing a Russian WB-based calendar with the app
over 2026 ([tibetastromed.md](sources/tibetastromed.md)) and from WB's
etext, searched for each thing that calendar shows; every WB passage they
name is found in the etext and **not yet read on the scan**, so each item
starts with that reading (PLAN.md). The comparison found nothing that
moves the app's arithmetic: its dates, month animals, trigram, sme ba and
mansions are WB's and Henning's, and the calendar's mansions are wrong on
38 days of 2026.

The Bon *snang srid me long* (SN in [sources/README.md](sources/README.md)),
where the calendar takes most of what WB lacks, is a **witness to WB
only** (the owner, 2026-10-08): its etext is searchable, so each item reads
it beside WB (the burning dates for T2.12, the nāgas and eight classes for
T2.13, the hair-washing results for T2.15, the people's and horses' la
places for T2.16) to confirm a reading or raise a question, but nothing
that only SN gives is built or cited, and no Bon text is a `Source`.

#### T2.10 WB's months and seasons — built 2026-10-08 (SPEC §5.13, §10.3)

WB's model almanac gives each Hor month a Kālacakra season and a Chinese
one (vol. 1, pp. 154–171, read on the scan:
[tibetastromed.md](sources/tibetastromed.md), *WB's months and
seasons*). Its chapter 31, where every season-keyed passage the app uses
or plans stands, counts by the Chinese reckoning, as two of its own
passages show (pp. 206, 228): spring is months 11, 12 and 1, autumn 5–7.
`Texts.RAHU_MONTH` moved from months 1, 2 and 7–9 to 11, 12 and 5–7
(`SeasonReckoning`, `SeasonReckoningTest`); the month balloon shows both
seasons, each labelled. T2.13 and T2.14 take their months from the same
answer.

#### T2.11 WB's almanac page — S reading

- **Gap.** WB vol. 1, pp. 171–178 (img. 181–188) says what a Phugpa almanac writes for
  each day, in order (tibetastromed.md, *WB's almanac page*). The app has
  never been checked against WB's own list: some entries are built (the
  combinations, special days, yoga, Viṣṭi, trigram, sme ba, earth lords),
  some are not (the burning dates, the twelve links, the dates' stages,
  the strikes of the nāgas and the eight classes, the sky doors, *gnyan
  pa*, *snag tsha*).
- **Work.** Read pp. 171–178 on the scan; inventory every entry with its
  rule's page (as white-beryl-ch33.md does for chapter 33), marking each
  built, planned below or not planned with the reason. Cross-check the
  special-day table (p. 341) against the verse of p. 179, a second WB
  witness, and re-read the Tuesday *bdud nyi* (the table's Āśleṣā; the
  calendar has Maghā). Search for the calendar's "lucky days" and "days of
  good and evil" (tibetastromed.md); build them only if WB has them.
- **Done when.** The inventory is in a topic file, each entry settled;
  any new reading task is an item here.

#### T2.12 The burning dates (*bsreg tshes*) — built 2026-10-08 (SPEC §5.11, §5.12)

- **Gap.** WB's burning dates, a weekday meeting one of two dates (vol. 1,
  p. 177, img. 187: Sunday the 12th and 27th, Monday the 11th and 26th …
  Saturday the 7th and 22nd), are named among the things to avoid after
  the karaṇas (vol. 2, p. 351: bloodletting, moxibustion and virtuous work
  do not succeed) and in chapter 34 (pp. 404, 414, 426). The app did not
  have them.
- **Built.** Read on the scans into [burning-dates.md](sources/burning-dates.md);
  `BurningDate` by the day's weekday and its own date, with WB's reading,
  lists and remedy, a member of the special days' voice (WB's almanac
  writes it with them, p. 177), in the special-days row and its sheet in
  English and Russian; `BurningDateTest` against WB's verse (the vector)
  and its number words. Over 2000–2049: 1,226 burning days, the day's tone
  changed on 11, days whose lists run against their tone 2,082 (2,085
  without). The calendar's 2026 days have the same pairs.
- **Left.** WB's marked case, the burning date that begins before nightfall
  on the day before (p. 177), needs WB's length of daylight by the Sun's
  sign (vol. 1, ch. 8): not built; worth it only with that reckoning for
  other uses. *rgyun sreg* (p. 351) is unidentified.

#### T2.13 The strikes of the eight classes and the nāgas — L

- **Gap.** WB vol. 2, pp. 226–235 run through dated courses beside the
  earth lords and Rāhu: the bad days by month, *dra chen*, Rāhu's days by
  month, the earth lords' turning, *gnyan*, and, named in WB's almanac
  list, the strikes (*thebs*) and turnings (*bzlog*) of the eight classes
  (p. 232, section 28: season-month, date, hour, direction and class) and
  of the nāgas (p. 234, section 31: on a strike nāga offerings and
  rain-making good, on a turning not; p. 364). None is built. The
  calendar's own eight classes and "protectors" are not WB's and are not
  to be copied.
- **Work.** With T2.10's months (spring 11, 12, 1; autumn 5–7): read
  pp. 226–235 on the scan into a topic file, section by section; build
  the eight classes' and the nāgas' courses as dated rows like Rāhu's
  (strike or turning, the hour and direction, the works WB names for
  each), weighed in the tier WB's words give them;
  list the other sections as built or not planned.
- **Done when.** Each course has a test vector from the text; SPEC §5.13;
  checked on the emulator in both languages.

#### T2.14 The 11th month's 6th: Ten Good Omens or nine bad — owner

- **Gap.** The app shows 11/6 as Sangpo Chuzom, the Ten Good Omens, after
  Rabten. WB has no ten good omens; it names «ངན་པ་དགུ་འཛོམ», nine bad
  things meeting, on the 7th of the first spring month (vol. 2, p. 226), in
  a list of bad days by month after Rāhu's sisters; the calendar gives the
  nine bad on 11/6. T2.10 found ch. 31's first spring month to be the
  11th, so WB's day is 11/7.
- **Work.** Read p. 226 and the list that follows on the scan; with T2.10's
  month, give the WB day and its list. Then the owner decides (a choice
  for AskUserQuestion): WB's nine bad on its day, Rabten's festival
  removed (recommended: WB above all), or both, Rabten's marked as his.
- **Done when.** The festival table and its sheet follow the decision;
  SPEC §5.7.

#### T2.15 Hair: cutting and washing by date — S reading, then S

- **Gap.** WB has no list of haircut results by date: haircuts are named in
  the weekday and mansion verses and chapter 34's work 50, which the app
  already weighs. The haircut sheet shows FPMT's results by date. WB's
  chapter 34 work 34 gives "bathing and washing the hair, with the dates'
  results" (p. 404), and the calendar's washing list matches FPMT's
  *haircut* list on several dates (20, 22, 24–25, 29, 30).
- **Work.** Read p. 404's dates' results on the scan and set them against
  FPMT's list. If FPMT's is WB's washing list, the sheet says so and the
  results move to washing the hair; if not, the sheet keeps FPMT's list
  marked as FPMT's and not WB's, and WB's washing results join the lunar
  date's reading.
- **Done when.** The sheet and the reading follow; SPEC §10.3.

#### T2.16 The la's place: the sides and the animals — built 2026-10-08 (SPEC §5.11)

- **Gap.** WB gives the left and right of men and women for the Kālacakra
  list (p. 303), and beside each Phugpa date the place of "horses and the
  like" (pp. 303–304). The app gave the person's place only, and called
  the *bla* "the soul".
- **Built.** Read on the scans ([lunar-dates.md](sources/lunar-dates.md),
  *Where the la resides*): the sides open the Kālacakra list and the
  Phugpa list names none, so the app keeps the place without a side; the
  thirty readings give the place in horses and other livestock beside the
  person's, two of its words (dates 3 and 5) marked unidentified. *bla* is
  now "the la (bla), the life-spirit", «ла (bla), жизненный дух», after
  Berzin's glossary, in the readings, the la mansion (*bla skar*) and
  Gaṇḍa's long reading (WB's «བླ་ཚེ»). `CatalogTest` checks every date in
  both languages.

#### T2.17 Russian terms by WB's words — built 2026-10-08 (SPEC §8.2)

- **Gap.** The catalog gave one Russian word to different WB terms:
  «свершение» for the yoga Siddhi, the element pair *dngos grub* and the
  named combination *grub*, «юность» for *lang tsho* and *gzhon*; and
  several to one: *'phel* was «рост», «возрастание», «приумножение» and
  «продвижение», the burning «жжение», «сочетание жжения» and «сжигающая
  дата», *mi 'phrod* «недостаток» beside «несовместимость». The calendar
  reads *rtag myos* as a tiger (*stag*).
- **Built.** *dngos grub* достижение (yoga and pair), *grub* свершение;
  *lang tsho* юность, *gzhon* юноша; *'phel* and the pair's *'phel 'gyur*
  возрастание, приумножение left to the increasing rites (*rgyas*); *mi
  'phrod* несовместимость. WB spells the burning *sreg* and *bsreg* alike
  (p. 177 «སྲེག་སྦྱོར», ch. 34 «སྲེག་ཚེས», [burning-dates.md](sources/burning-dates.md)) and
  names both the earth–fire pair and *gtan spang* a burning combination,
  so all three are сожжение: «сожжение», «сочетание сожжения», «дата
  сожжения». «нектар», «ваджра» and «радость» each stand for one WB word
  (*bdud rtsi*, *rdo rje*, *dga' ba*) in two reckonings and stay. *rtag
  myos* is WB's on the table's heading (vol. 1, p. 149, img. 159, its ར
  beside «བརྟན»'s) and in its short reading (vol. 2, p. 333): «вечно
  ликующий» stays ([combinations.md](sources/combinations.md)).
  `CatalogTest` fails when two WB terms share a Russian name or one WB
  word loses its Russian one.
- **English, the same rule** (the owner, 2026-10-08: follow WB). English
  had the same two collisions, *'khon 'dzin* and *mi mthun* both
  "discord", and its pair names followed the Sanskrit while their
  readings named the Tibetan. Now *dngos grub* attainment, *gzhon* young
  one, *'khon 'dzin* (Vaidhṛti) enmity; *'phel* and *'phel 'gyur* growth
  ("increasing" stays the increasing rites'); *phun tshogs* perfection,
  *mi 'phrod* incompatibility, *mi mthun* discord in the reading too,
  *dga' ba* joy for the yoga Harṣaṇa as for Nandā. `CatalogTest` checks
  both languages. The Russian read-through (L3) sees the new words.

#### T2.18 Election by activity — planned as E

The calendar's "choice of time" lists a month's good and bad days for one
of 25 works, ranked. The app weighs every work on every day already
(SPEC §5.12); the election reads that weighing across days, so the WB
voices still to come (T2.11, T2.13) change its results without changing
it. The plan is item E below.

#### T2.19 The birth weekday, the birth mansion and the element's weekdays — built 2026-10-08 (SPEC §5.8, §5.12, §10.3)

- **Gap.** WB p. 338 names the birth weekday, one's own weekday (the *bla*
  weekday, one with it: p. 346, p. 312) and the mother's, friend's and
  enemy weekdays of one's element, and the birth mansion; the app builds
  p. 330's days by the birth animal only. Open question 14 settled what
  each is: the element is the birth year's life force ("applied the same
  way", the tables pp. 345–346), the birth weekday the weekday of birth
  (p. 379), the birth mansion the mansion of the birth date (Phug pa Lhun
  grub rgya mtsho). The mansion of conception stays unbuilt (no source
  defines it).
- **Work.** Compute them from the birth date; show each in "For you" with
  p. 338's works (the birth and own weekday: contests, trade, pleading a
  case, races, archery; the mother's and friend's good; the enemy's every
  work avoided, E6; the birth mansion: offerings, serving the lama,
  giving, a new house, planting trees). Where one of them and a p. 330
  day fall on the same weekday, one row. Not weighed (T2.3; what WB says
  of a person's days against the day is under E, *The person's days and
  the hour against the day*).
- **Done when.** Vectors for the test birth date (1976-06-01);
  `CatalogTest`; SPEC §5.8, §10.3; Russian.
- **Built.** `ownDays` (core `tibetan/`): the birth weekday, the
  weekday's place among the five of the life force's element
  (`elementWeekday`, the weekdays' elements of the *nag rtsis*, vol. 1,
  p. 257) and the birth mansion; readings `Texts.OWN_DAY` with p. 338's
  works, the mother's and friend's "anything" (p. 330), the child's
  middling (no dot), the enemy's "every work" to avoid. Every weekday
  holds one of the five, so with a birth date every day has a row. The
  roles of the weekday, with the birth animal's personal day, are one
  Almanac row, "Your weekday" with each one's dot, its sheet giving each
  reading in turn, and one entry in the brief's "For you" (`sharedTone`);
  the birth mansion its own row. `OwnDayTest` checks the table of p. 346
  cell by cell, that each animal's anti day (*gshed gza'*, p. 330) is an
  enemy weekday of its life force, and the test birth date (a Tuesday,
  Fire Dragon, life force earth, birth mansion Ārdrā, 12 returns in
  2026); `DaySummaryTest`, that nothing is weighed and one weekday is one
  entry. Checked on the emulator in English (8 October 2026: "Anti day ·
  Enemy weekday") and Russian (1 November 2026: «День удачи · День недели
  матери» and the birth mansion), and with no birth date (no row).
- **Left.** The element's mansions by the life force (p. 330, tables
  p. 345: own, mother, friend, child, enemy quarters and the grave
  mansions), which would put a second row on every day; the mansion of
  conception (no source defines it); E6's exception for the enemy weekday
  and the death mansion.

#### Order

1. T2.1, T2.2 and T2.3 are built.
2. T2.4, the display half: built 2026-10-07.
3. T2.7 and T2.8 built 2026-10-07.
4. T2.5 built 2026-10-07; T2.6 built 2026-10-08.
5. The reading half of T2.4, and T2.9, as the scans allow.
6. WB above all: T2.10 built 2026-10-08; then
   T2.11 (it may add items), then the small ones T2.16 (built 2026-10-08), T2.17 (built 2026-10-08) and T2.15,
   then T2.12 (built 2026-10-08), T2.14 (the owner's decision after its reading) and T2.13.
7. T2.19 built 2026-10-08; with it E6's "For you" half has its enemy weekday.

#### Not planned

- The day by the clan's element (*rus chen*, WB ch. 31, p. 225): it needs
  a Tibetan patrilineal clan, which the app's readers do not have, and WB
  confines it to reckoning for the dead.
- KP box 47 (averting rites, every entry a kind of rite) and KP's charts
  (SPEC §5.10).
- Rāhu's course by the hour on the hours panel: the text names times of
  day, not clock hours (SPEC §5.13).
- WB's yoga ranking verse (question 1): the yoga's dot comes from its
  other verses (SPEC §5.11).
- From the calendar compared in [tibetastromed.md](sources/tibetastromed.md),
  what WB does not support: its "old style" (the month animals from the
  tiger; WB's model almanac gives the 1st month the dragon), its
  "protectors of the teaching" (no such course in WB), the house god's
  10-day cycle (WB's *khyim lha* moves by season, p. 204), the sky
  medicine by the day's trigram (WB's *gnam sman* is by one's own year,
  vol. 1, p. 401: T4 material), its weekday groups by birth year, and one
  column only of the special days' table (WB gives both).
- Anything resting on Bon texts alone (the owner, 2026-10-08): SN's six-day
  cycle for setting out, its eight classes by date, its six gods and six
  black days (*gdags kyi lha drug dang nag drug*), Bon festivals, and
  Khyung sprul's *dpyad gsum dag rtsis*, a reformed calendar arithmetic of
  its own (the calendar's «Че сум так ци»).

### T3. Element colours

The sme ba shows in the colour of its box (SPEC §10.3), the four aspects
with the pebbles of the year, month, day and hours are built (SPEC §5.9),
and so are the personal mansions (SPEC §5.8). What is left waits on design:

- **Element colours:** wood green, fire red, earth yellow, iron white, water
  black or blue (Berzin 3). The day's element appears only inside the day
  line, so it first needs a place of its own on the page.
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

## UX

### U. The Tibetan day page: weight made visible

A review of the page against SPEC §5.12 (2026-10-08; the emulator, Kyoto,
8 October 2026, the test birth date). The weighed readings stand in the
Phugpa order of strength, as §5.12 sets it, but the page does not show
what weighs what: rows that are not weighed stand inside the weighed run,
an unweighed personal row opens the list, one voice is two rows with
opposite dots, and nothing names the factor that decided the day. The
brief's lists run long and say the same voices on line after line. No
item changes a side or a tone; each changes SPEC §5.12 (*Order on the
page*) and §10.3 with the code. U2 is built; U1 and U3 ship together; then U4 and U5.

#### U1 One row for the combination — S

- **Gap.** The named combination (*'phrod chen*) and the element pair are
  one voice (§5.12), yet two rows with their own dots: on 8 October 2026
  *Pest* is red and Wind–Fire green, so the combination has no tone, and
  the page does not say so. Rāhu's courses and the special days, each one
  voice, are already one row each.
- **Work.** One "Combination" row: both parts' dots side by side, the
  subtitle naming both and whether they agree ("Pest · Wind–Fire: the
  parts disagree, no tone"); its sheet gives each reading in turn, with
  the combination table and the element grid, as the special days' sheet
  does.

#### U2 The deciding factor named — built 2026-10-08 (SPEC §5.12, §10.3)

- **Gap.** The brief's tone said "the strongest factor that takes a side
  decides" without naming it (Thursday on 8 October 2026), while the
  haircut row named its own ("avoid · by Thursday"). Nothing in the
  Almanac linked a row to the brief's verdict.
- **Built.** The weighing keeps its deciding voice (`DayVerdict.factor`,
  `.deciding`). The In brief row says "a lucky day · by Thursday" («решает:
  Четверг»), or "by the combination"; the brief's sheet names it with its
  kind ("Thursday (weekday)"); that row in the Almanac, both rows where
  the combination decides, carries a "decides" mark outlined in its tone,
  and its sheet says so under the gloss. Over 2000–2049: the combination
  9,672 days, the weekday 7,708, the special days 568, the date 292, the
  karaṇa 23, the yoga none (`DaySummaryTest`). Checked on the emulator
  in English and Russian on a day of each kind, lucky and unlucky, with
  and without a birth date. U1 will put the mark on its one combination
  row.

#### U3 Three sections: weighed, yours, also today — S

- **Gap.** The *bla mkhyen* and the nectar periods stand between Rāhu and
  the weekday, inside the run of weighed voices, though neither is
  weighed. The personal day and personal mansions open the Almanac, so an
  unweighed row is the first verdict on the page (8 October 2026: "Hostile
  day" in red above a lucky day), while vitality and body sit apart in
  "Your day". The nectar periods are shown three times: this row, the
  brief's "By the hour" and the dots on the hours dial.
- **Work.** **Almanac**: the monthly observance, then the weighed voices
  in rank (the combination, Rāhu, weekday, mansion, special days, date,
  karaṇa, yoga), then the haircut. **Your day**: the personal day and
  mansions with vitality and body (and T2.19's days, E6's every-work days).
  **Also today**: the *bla mkhyen*. The nectar row goes; the brief and
  the dial keep the nectar periods. "Lunar day" stays as it is.

#### U4 The brief grouped by the voices that carry each work — S

- **Gap.** On 8 October 2026 the brief lists 65 good works, one per line,
  and from the 25th on the same "Thursday" or "Thursday · Maghā" repeats
  on each.
- **Work.** Works grouped by the set of voices standing on their side,
  each group headed by that set, its works as one wrapped run of glyphs
  and names; groups in the order of their weight (§5.12), as the works
  are now. Nothing outweighed is shown, as now; a work still opens its
  workings.

#### U5 The hours on the In brief row — S

- **Gap.** The combination period is the one factor a text puts above the
  day (WB p. 376, T2.4; under E, *The person's days and the hour against
  the day*), but the In brief row gives only the day's tone; its hours
  are one tap away, in the sheet's "By the hour". On an unlucky day the
  hours to be accomplished are what a reader can use.
- **Work.** The row carries the hours that run against the day's tone:
  on an unlucky day those to be accomplished ("unlucky day ·
  09:00–13:00 good"), on a lucky day those to be avoided; a tap on them
  opens the hours panel at that hour. Nothing is weighed anew.

Each is done when SPEC has it, `TranslationsTest` passes, and screenshots
on the emulator of a lucky and an unlucky day, with and without a birth
date, in English and Russian, show it.

## Election

### E. The best day for a work

The user picks a work (a wedding, a haircut, setting out) and a span of
days; the app lists the days the weighing makes good for it, best first,
each with the factor that decides it, and the hours within it. No rule of
weighing is new: each day is weighed exactly as its page weighs it (SPEC
§5.12), and the election only orders the days. What the day page says of
a work and what the election says of it can therefore never differ, and
every voice built later (T2.11, T2.13) reaches the election by itself.

**The order, and what in it is sourced.** The side a work takes on a day
is the texts' (§5.12). The good days are ordered by the order of strength
the texts give, as far as it goes, and by the app's convention only where
it stops:

1. Days on which the combination of weekday and mansion names the work
   good (its element pair's list): the combination is the result, "even
   when the planet and the mansion are each good" (WB vol. 2, p. 333).
2. Then by the strongest voice that names it good, in the Phugpa order of
   strength (KP rules 2–4, WB vol. 2, p. 376): Rāhu, the weekday, the
   mansion, the special days, the date, the karaṇa, the yoga, the day
   animal, the trigram. A work the weekday makes good ranks above one
   only the date makes good.
3. Within one rank, a day whose combination is lucky before one whose
   combination is unlucky or has no tone. The combination's tone decides
   no work it does not name (§5.12; the owner, 2026-10-06), but WB p. 333
   makes it the result of the day, so it may order days that already
   stand on one side.
4. Within that, the sum of the standing voices' weights, ten for the
   combination down to one for the trigram: the app's convention, the
   one the In brief row already uses (§5.12), and the screen says so.
5. Then the earlier day.

Days on which no voice names the work are blank, never "neutral, so
fine". Not used: KP's weights of one, four and eight (the Kashmiri
paṇḍita's, which WB p. 376 sets aside), a count of voices (open questions
12–13), and outweighed voices, which are neither shown nor counted (§5.12). The person's own days (T2.3) are marked on the
days they fall on and not weighed, as in the brief: no text places them
against the combination. The two WB avoids every work on take the day
away from the person who has a birth date set (E6, D3).

#### The person's days and the hour against the day

What WB says on whether a person's own days or an hour outweigh the day
(asked by the owner 2026-10-08; [personal-mansions.md](sources/personal-mansions.md),
*Works on one's own days*; [weighing.md](sources/weighing.md)):

- **A person's bad days are absolute.** On the enemy weekday of one's
  element "every work is to be avoided" (*las kun spang*); on the death
  mansion, the *gshed skar* (p. 330), "anything is bad" (p. 338). The
  line before them: where weekday, date and mansion are good, "something
  else can arise: from white bronze and good iron fly hot, burning
  sparks"; by its place, a good day can still go wrong for one person
  (the verse does not draw the link).
- **A person's good days only name works.** The birth and own weekday are
  good for contests, trade, pleading a case, races; the birth mansion for
  offerings, giving, a new house. Nothing says one lifts a bad day.
- **WB ranks a person's days against nothing**, neither the combination
  nor any single factor (open question 14). Tshul khrims rgyal mtshan's
  gloss, that they lead as the *dmigs bsal* over the general ("over a
  treatise's general rule the particular is strong"), is not WB's and is
  not built.
- **The hour:** only the combination period is placed above the day: the
  powers of all the factors are complete in it, "held highest of all"
  (p. 376). KP's rule 2, the hour above the *nyi ma*, sets the hour
  against the weakest of the seven only, not the day. D1 keeps both out
  of the day's place.

So a person's bad day takes the day away from that person (E6, D3); a
person's good day and a good hour add nothing to a day's place.

#### E1 The engine — M

- **Work.** `Election` in `core/.../texts/` beside `DaySummary`: for a
  work and a span, one `DaySummary.of(day)` per Tibetan day (a doubled
  date twice, a skipped one not at all, as `TibetanCalendar.monthOf`), the
  side from `sideOf`, the key above; each result keeps the day, its side,
  what decided it (combination or the strongest voice and its rank), the
  combination's tone, the weight and the standing voices. A span runs
  from the shown day up to twelve months, within the date picker's
  1900–2100. The works offered are those some list names, grouped by
  `ActivityFamily`.
- **Tests.** On 2000–2049, for every work, the election's side equals
  `DaySummary.sideOf` day by day; the key's order holds (no day decided
  by a weaker voice before one decided by a stronger); a ranked vector
  for October 2026 for haircuts, weddings and setting out.
- **Witness.** The same three works against the calendar's October 2026
  election ([tibetastromed.md](sources/tibetastromed.md): haircuts on all
  7 of its days, weddings 11 of 12, setting out 11 of 14), each
  difference named by the WB reading behind it, in tibetastromed.md. The
  calendar is a witness, not a source; nothing is copied from it.
- **Cost.** A year is about 380 day summaries: measured on the emulator,
  computed off the main thread and kept per work and span.

#### E2 The screen — M

- **Entry.** A menu item under the two calendars ("Choose a day"); and
  each work in the day in brief and in a reading's lists opens the
  election for that work from the shown day.
- **Picking a work.** The families with their glyphs (§10.7), each
  opening its works; a search over the works' names in the app's
  language.
- **The result.** The span (this month, three months, twelve months);
  the days as a grid, the haircut sheet's (`HaircutGrid`, generalised to
  any work, so one grid serves both): green, red, blank; under it the
  best days, each with its civil and Tibetan date, what decides it ("by
  the combination", "by Wednesday"), the voices standing and the "For
  you" mark. A day opens its workings, as a work's row in the brief
  does, and from there its day page. The days to avoid are red in the
  grid and say why on tap; they get no list of their own.
- **Done when.** English and Russian strings, `TranslationsTest` and
  `CatalogTest` pass; the haircut sheet unchanged to the eye.

#### E3 The hours of the chosen days — S

The combination period (§5.13) follows the month and the hour only (KP
§9: the month's sign at daybreak, one sign on each hour): every day of a
Tibetan month has the same periods at the same clock hours. It chooses
the hour, never the day. The screen gives the work's hours once per
Tibetan month in the span, not per day: the periods whose sign's reading
(WB vol. 2, pp. 371–376, `Texts.DUS_SBYOR`) names the work good, and
those that name it to avoid; and per listed day the nectar periods (by
weekday, §5.13) where their reading names the work. Hours do not move a
day's place (D1).

#### E4 The works' own rising signs — M reading, then S

- **Gap.** WB's chapter 34 gives most works their rising signs (naming,
  clothes, the new home, the hearth, shows, sewing, storehouses,
  banners …: [white-beryl-ch34.md](sources/white-beryl-ch34.md),
  *Inventory*), and KP's boxes carry them; [kp-activities.md](sources/kp-activities.md)
  lists them as not calculated. Since the combination period was built
  the app has the sign rising in each hour, so they are calculable.
- **Work.** Read each work's rising signs on the scans (I1KG12907, img.
  386–436), KP's boxes as witness, into white-beryl-ch34.md; build them as
  the work's own hours, which stand above the sign's general reading for
  that work as its particular case (WB p. 376: «དམིགས་བསལ་བྱུང་ན་དེ་ཉིད་གཙོ»).
  E3 and the hours panel show them.
- **Done when.** A vector from the text; SPEC §5.13. The same lists'
  twelve links (not calculated) and *sme ba* (calculated, §5.11, but
  given no rank by the texts) stay out.

#### E5 The 旧暦 page — S

- **Gap.** No source ranks one kind of 暦注 above another (SPEC §7.5,
  [kyureki.md](sources/kyureki.md)); the page lists both sides unweighed,
  only the lower band's own rules setting some aside.
- **Work.** The same screen on the 旧暦 page: the days on which some
  annotation names the work good and none, after the lower band's rules,
  names it to avoid, and the disputed days apart, in date order, with no
  "best" and a sentence that the almanac gives no order. The Qing
  協紀辨方書's six grades (kyureki.md) stay out: nothing ties them to the
  Japanese almanac.

#### Owner decisions — settled 2026-10-08

- **D1 An hour against the day: no.** KP rule 5 holds the combination
  period above everything, so by the letter a day to avoid becomes good
  in an hour whose sign names the work good. But every day of a month
  has every sign (E3), so offering such days would offer every day the
  combination's verdict forbids. The owner decided: the day's place is
  the day's weighing, and the hours say when within it; a day to avoid
  is not offered, not even "only at these hours".
- **D2 The 旧暦 election: yes.** E5 goes ahead, unranked, as the page
  itself is.

#### The election's order

E1, E2 and E3 ship together; E4 after its reading; E5 after E2, on its
screen. Each is
done when its code has tests, SPEC has it (a new §5.14 for the election,
§10 for its screen), it is checked on the emulator in English and
Russian, and the release build is checked before the tag (R8: the picker
names works through the catalog by their enum's class name). The store
listing and the website name the election only once it is released.

#### E6 The days WB avoids every work on, for you — S

- **Gap.** "For you" lists the person's days with their dots and weighs
  none (T2.3), so the enemy weekday and the death mansion read as one
  unlucky row among others, though WB avoids every work on them (p. 338;
  *The person's days and the hour against the day*, above). The death
  mansion is already among the six personal mansions; the enemy weekday
  by element is T2.19's (built 2026-10-08, `OwnDay.ENEMY_WEEKDAY`).
- **D3 — settled 2026-10-08: it needs a birth date and follows WB.** With
  no birth date set, nothing changes: the day's tone and lists are the
  weighing's (§5.12), the same for every reader. With a birth date set,
  on the person's enemy weekday and death mansion WB's words hold for
  that person: every work is to be avoided (p. 338).
- **Work.** On those days, with a birth date: "For you" says so in WB's
  words, naming the day (enemy weekday of one's element, death mansion);
  the In brief row says "every work to avoid, for you" beside the day's
  tone; the brief shows no good list, which has no power for the person
  (as outweighed voices are not shown, §5.12), and its avoid list stays;
  the election (E1–E2) does not offer the day for any work and says why
  on tap. The combination period is not placed against a person's days
  by any text, so "By the hour" stays as it is (the good list hidden and
  the hours kept: both agreed by the owner, 2026-10-08). SPEC §5.12
  states the exception: the person's days stay unweighed except these
  two, which WB makes absolute.
- **Done when.** A vector of such days for the test birth date
  (1976-06-01); `DaySummaryTest` unchanged with no birth date; the reading
  cites p. 338; SPEC §5.12, §10.3, §5.14; Russian. The "For you" and brief
  half needs no election; the election half ships with E1–E2.

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
