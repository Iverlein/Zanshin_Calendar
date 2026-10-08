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
| 1 | T3 Element colours | S | A colour per element, once it has a place | A place for the day's element on the page (sourced: Berzin 3) |
| 2 | L4 Hosted Weblate | S | The repository is ready (docs/weblate.md); the project, its three components and the review rules are set up on Weblate with the owner's account; the translating itself is outside the code and open-ended | Not before 2026-12-28 (three months of development, for the Libre plan) |
| 3 | L3 Russian | S | Translated and passing `CatalogTest`, with the store listing and the language switch; what is left is the owner's read-through on the phone and the fixes it brings | — |
| 4 | T2 The Tibetan page's gaps | L | The page's readings are built; what is left is the hour of KP's rule 2 and answers that rest on unseen scans: a work plan of nine items below, T2.1–T2.3, T2.5–T2.8 and T2.4's display half built; then WB above all (T2.10–T2.18): WB's seasons, its almanac page, the burning dates, the eight classes' and nāgas' strikes, 11/6, hair by date, the soul's sides, Russian terms | For T2.9, books only lent on archive.org; T2.13 and T2.14 wait on T2.10 |
| 5 | T4 Element calculation, with the T5 settings | XL | Progressions that differ by gender, obstacle years and yearly sme ba, each needing a vector; the readings of every result need sources. Mo is not planned. The gender setting is small but nothing reads it before T4, so it ships with it | — |
| 6 | M1 Meditation Timer and Bell | M | Requested feature: meditation timer and randomized periodic bell (MindBell functionality) | Port audio/alarm logic from MindBell, build Compose UI |

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

#### T2.10 WB's months and seasons — S reading, then S code; first

- **Gap.** WB's model almanac (vol. 1, pp. 156–173) gives each Hor month
  two seasons: the Chinese reckoning's (the 11th month the first of
  spring and the tiger, the 1st the last of spring and the dragon) and the
  Kālacakra one (the 4th early summer). The app's month animals are WB's.
  Its season names (`TibetanMonth.N.season`, the 1st "early spring") are
  the Kālacakra ones, and `Texts.RAHU_MONTH` takes WB's "first month of
  spring" for month 1 and its autumn for months 7–9, while the passage
  (vol. 2, p. 238) sits in the chapter of the Chinese reckoning, where
  spring would be months 11, 12 and 1 and autumn 5–7. Every season-keyed
  passage below (T2.13, T2.14) waits on the same answer.
- **Work.** Read the twelve month lines of pp. 156–173 on the scan into a
  months section of tibetastromed.md (or a `months.md`); for each
  season-keyed WB passage the app uses or will use, settle which system it
  counts by, from its own words and context (a named animal, the chapter).
  Move `RAHU_MONTH` if WB's seasons say so; give the month balloon both
  season names, each labelled.
- **Done when.** The months are read with page and image; `RAHU_MONTH` is
  keyed by WB's season with a test pinning one month of each season; SPEC
  §5.13 and §10.3 say which season system each reading uses.

#### T2.11 WB's almanac page — S reading

- **Gap.** WB vol. 1, pp. 173–180 says what a Phugpa almanac writes for
  each day, in order (tibetastromed.md, *WB's almanac page*). The app has
  never been checked against WB's own list: some entries are built (the
  combinations, special days, yoga, Viṣṭi, trigram, sme ba, earth lords),
  some are not (the burning dates, the twelve links, the dates' stages,
  the strikes of the nāgas and the eight classes, the sky doors, *gnyan
  pa*, *snag tsha*).
- **Work.** Read pp. 173–180 on the scan; inventory every entry with its
  rule's page (as white-beryl-ch33.md does for chapter 33), marking each
  built, planned below or not planned with the reason. Cross-check the
  special-day table (p. 341) against the verse of p. 179, a second WB
  witness, and re-read the Tuesday *bdud nyi* (the table's Āśleṣā; the
  calendar has Maghā). Search for the calendar's "lucky days" and "days of
  good and evil" (tibetastromed.md); build them only if WB has them.
- **Done when.** The inventory is in a topic file, each entry settled;
  any new reading task is an item here.

#### T2.12 The burning dates (*bsreg tshes*) — M

- **Gap.** WB's burning dates, a weekday meeting one of two dates (vol. 1,
  p. 179: Sunday the 12th and 27th, Monday the 11th and 26th … Saturday the
  7th and 22nd), are named among the things to avoid on the karaṇa pages
  (p. 351: bloodletting, moxibustion and virtuous work do not succeed) and
  in chapter 34 (pp. 404, 414, 426, 428). The app does not have them.
- **Work.** Read p. 179, p. 351 and the chapter 34 lines on the scan; build
  `BurningDate` (weekday, date) with its reading and lists; place it in
  the weighing where WB's words put it (with the special days, unless the
  reading says otherwise, SPEC §5.12); a doubled or skipped date follows
  the lunar date.
- **Done when.** A vector from WB's verse (and the calendar's 2026 days as
  a cross-check) passes; the row, its sheet and the brief show it in
  English and Russian; SPEC §5.11–5.12; days against their tone measured
  on 2000–2049.

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
- **Work.** After T2.10: read pp. 226–235 on the scan into a topic file,
  section by section; build the eight classes' and the nāgas' courses as
  dated rows like Rāhu's (strike or turning, the hour and direction, the
  works WB names for each), weighed in the tier WB's words give them;
  list the other sections as built or not planned.
- **Done when.** Each course has a test vector from the text; SPEC §5.13;
  checked on the emulator in both languages.

#### T2.14 The 11th month's 6th: Ten Good Omens or nine bad — owner

- **Gap.** The app shows 11/6 as Sangpo Chuzom, the Ten Good Omens, after
  Rabten. WB has no ten good omens; it names «ངན་པ་དགུ་འཛོམ», nine bad
  things meeting, on the 7th of the first spring month (vol. 2, p. 226), in
  a list of bad days by month after Rāhu's sisters; the calendar gives the
  nine bad on 11/6. If T2.10 finds the first spring month to be the 11th,
  WB's day is 11/7.
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

#### T2.16 The soul's place: the sides and the animals — S

- **Gap.** WB gives the left and right of men and women for the Kālacakra
  list (p. 303), and beside each Phugpa date the place of "horses and the
  like" (pp. 303–304). The app gives the person's place only, without a
  side.
- **Work.** Read pp. 303–304 on the scan: whether the sides hold for the
  Phugpa list; add the animals' place to the lunar date's reading.
- **Done when.** The thirty readings carry what WB gives; `CatalogTest`
  passes; Russian added.

#### T2.17 Russian terms by WB's words — S

- **Gap.** The catalog gives one Russian word to different WB terms:
  «свершение» for the yoga Siddhi, the element pair *dngos grub* and the
  named combination *grub* (and in two special days' names), «нектар» and
  «юность» each twice, and «жжение» (the element pair *sreg pa*) beside
  «сочетание жжения» (*gtan spang*'s other name, *bsreg sbyor*, p. 337),
  which T2.12's burning dates would make a third. The calendar reads
  *rtag myos* as a tiger (*stag*).
- **Work.** Distinct Russian words where WB's terms differ, and one word
  for "burning" across *sreg pa*, *bsreg sbyor* and *bsreg tshes* only if
  WB means the same by it; check *rtag myos* on WB's table (vol. 1,
  pp. 148–149).
- **Done when.** `CatalogTest` and `TranslationsTest` pass; the Russian
  read-through (L3) sees the new words.

#### T2.18 Election by activity — not planned until T2.10–T2.13

The calendar's "choice of time" lists a month's good and bad days for one
of 25 works, ranked. The app weighs every work on every day already
(SPEC §5.12); a month view for one work is a display of that, worth
deciding after the WB voices above are in, since they change the sides.

#### Order

1. T2.1, T2.2 and T2.3 are built.
2. T2.4, the display half: built 2026-10-07.
3. T2.7 and T2.8 built 2026-10-07.
4. T2.5 built 2026-10-07; T2.6 built 2026-10-08.
5. The reading half of T2.4, and T2.9, as the scans allow.
6. WB above all: T2.10 first (the season-keyed items wait on it), then
   T2.11 (it may add items), then the small ones T2.16, T2.17 and T2.15,
   then T2.12, T2.14 (the owner's decision after its reading) and T2.13.

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
  vol. 1, p. 403: T4 material), its weekday groups by birth year, and one
  column only of the special days' table (WB gives both).

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
