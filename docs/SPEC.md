# Zanshin Calendar — Specification

Status: draft of 2026-09-28. Replaces the "Syncretic Rekireki" design
document; what changed and why is in [Appendix A](#appendix-a-changes-from-the-syncretic-rekireki-draft).

## 1. What it is

An Android app that shows one day in two traditional calendars side by side:

- the **Tibetan calendar**, Phugpa version;
- the **Japanese old calendar** (旧暦 *kyūreki*, Tenpō rules) with rokuyō and
  the 24 solar terms;

plus sunrise, sunset and true solar noon at the owner's location. A home-screen
widget shows today; the app opens on today and moves to any other date.

The phone is the only target. There is no desktop or web edition; the JVM
command-line tool in §4 exists to develop and verify the engines, not to ship.

## 2. Principles

1. **Offline by construction.** The app manifest declares no `INTERNET`
   permission. Every value is computed on the device from formulas; nothing is
   downloaded, including ephemeris files.
2. **Every formula and every text has a published source**, named next to it in
   this spec or in the data file. Nothing written from memory ships.
3. **Test vectors are the contract.** An engine is done when it reproduces the
   published tables in §9, not when it compiles or looks plausible.
4. **Engines are pure and isolated.** Each engine is a function from a date
   (and, for the local sky, a location) to an immutable result. The Tibetan and
   kyūreki engines share only date primitives and the astronomy library; the
   UI is the only place both results meet.
5. **Exact where the tradition is exact.** The Tibetan calendar is defined in
   rational arithmetic and is computed in rationals, never floating point.
6. **MPL-2.0**, with the licence header in every source file (see `LICENSE`).

## 3. Scope

| In v1 | Later |
| --- | --- |
| Tibetan: date, leap month, skipped/repeated day, weekday and planet, day element/gender/animal, year name and rabjung number, festivals; the almanac entries of §5.8 — lunar mansion, element pair, yoga, karaṇa, lunar-day cycles, hair-cutting day, monthly observances, personal days; the four aspects of the year and the pebbles of the day, month and year (§5.9); the lunar mansion's reading and the activity lists (§5.10) | Tsurphu version; Rishi-star bathing week |
| Kyūreki: date, leap month, rokuyō, solar term of the day and current term, kanshi (干支), seasonal festivals; the 暦注 of §7.5 — 十二直, 二十八宿, 九星, 選日 and 暦注下段, 雑節, 恵方, personal bad days | 神吉日, 凶会日, 五墓日, 時下食; 七十二候 |
| Local sky: sunrise, sunset, true solar noon and the sun's altitude at noon; moon-phase glyph | Moon rise and set |
| Screen with date navigation, home-screen widget, location setting | Notifications |
| Meditation timer and mindfulness bell, a screen of their own (§10.9) | |
| Texts from published sources only (§8) | Translations beyond English |

Planned work, with its open questions, is in [ROADMAP.md](ROADMAP.md).

## 4. Architecture

Gradle multi-module project, Kotlin throughout.

```text
core/   pure Kotlin/JVM library, no Android dependency
          rational/   exact Rational over Long, overflow-checked
          jd/         Julian day number ↔ LocalDate, amod, floor-mod helpers
          tibetan/    Phugpa engine (§5)
          astro/      sun, moon, ΔT, rise/set (§6)
          kyureki/    Tenpō engine (§7)
          texts/      loads and validates the sourced texts (§8)
        src/test/resources/vectors/  test vectors, tab-separated (§9)
cli/    JVM entry point that prints a day as text — the console look of the
        original draft; used to inspect results without a phone
app/    Android: Compose screen, Glance widget, settings
```

- Result types are Kotlin `data class`es with `val` fields only.
- `core` has no runtime dependencies: the Kotlin standard library and
  `java.time` only. The texts of §8 will add a loader when sourcing starts.
- Versions are pinned in `gradle/libs.versions.toml` at milestone M0, at the
  latest stable releases of that day.
- `minSdk` 26 (`java.time` without desugaring). Application id
  `io.github.iverlein.zanshin`.
- Toolchain: JDK 21 through a Gradle toolchain (the system JDK is 27, newer
  than the Android Gradle Plugin is tested with), Android SDK command-line tools,
  Gradle wrapper committed to the repo. Installing these on the machine is a
  change to the host and goes into `~/RUNBOOK.md`.

Common commands:

```bash
./gradlew :core:test                     # engines against the test vectors
./gradlew :cli:run --args="2026-09-28"   # one day, as text
./gradlew :app:installDebug              # onto the phone over adb
```

### Date primitives

- The unit shared by everything is the **Julian day number** (JD): an integer
  naming a whole day, as Janson §2 defines it. Not the Julian *date*, which is
  a real-valued instant.
- `JD = LocalDate.toEpochDay() + 2440588`.
- `mod` always returns a result in `0 until n`; `amod` in `1..n`
  (`1 + (m − 1) mod n`). Kotlin's `%` is neither for negative operands, so it
  is not used on calendar quantities.
- "Today" is the civil date in the phone's time zone, mapped to its JD. The
  Tibetan day actually begins at dawn (~5:00 local mean time), so between
  midnight and dawn the screen already shows the day that starts at the coming
  dawn. That is Janson's convention (Remark 6) and is kept; the UI labels
  Tibetan dates as "from dawn".

## 5. Tibetan engine (Phugpa)

**Source:** Svante Janson, *Tibetan Calendar Mathematics*, 2007, revised 2014,
typo corrected 2022 — `www2.math.uu.se/~svantejs/papers/calendars/tibet.pdf`.
Section and equation numbers below are his. Cross-checks: Edward Henning's
computed calendars (kalacakra.org) and the Men-Tsee-Khang annual almanac.

### 5.1 Constants (epoch E806: month 3 of 806)

| Quantity | Value | Janson |
| --- | --- | --- |
| m₁, mean month in days | 167025/5656 (≈ 29.530587) | (7.2) |
| m₂ = m₁/30, mean lunar day | 11135/11312 | (7.3) |
| m₀, epoch offset in JD | 2015501 + 4783/5656 | (7.4) |
| s₁, mean sun per month, in revolutions | 65/804 | (7.6) |
| s₂ = s₁/30 | 13/4824 | (7.7) |
| s₀ | 743/804 | (7.8) |
| a₁, moon anomaly per month | 253/3528 | (7.12) |
| a₂, moon anomaly per lunar day | **1/28** (see 5.5) | (7.13) |
| a₀ | 475/3528 | (7.14) |
| β*, initial intercalation index | 61 | (5.4) |

Angles are in revolutions (full circles) and taken mod 1.

### 5.2 Months

For Tibetan year Y (numbered by the Gregorian year in which it starts) and
month M:

- M\* = 12(Y − 806) + M − 3  (5.2)
- ix = (2M\* + β\*) mod 65  (5.7)
- Month M of year Y has a **leap month** iff ix ∈ {48, 49}  (5.8). The leap
  month comes *before* the regular month of the same number.
- True month count n = ⌊(67M\* + β\*)/65⌋, plus 1 when ix ≥ 48 — except the
  leap month itself, which is never incremented  (5.9).

### 5.3 End of a lunar day

For lunar day d (1–30) in true month count n  (7.1)–(7.22):

- mean_date = n·m₁ + d·m₂ + m₀
- mean_sun = n·s₁ + d·s₂ + s₀
- anomaly_moon = n·a₁ + d·a₂ + a₀
- moon_equ = moon_tab(28 · (anomaly_moon mod 1)), with
  moon_tab(0..7) = 0 5 10 15 19 22 24 25, extended by moon_tab(14 − i) =
  moon_tab(i) and moon_tab(14 + i) = −moon_tab(i), interpolated linearly
- anomaly_sun = mean_sun − 1/4
- sun_equ = sun_tab(12 · (anomaly_sun mod 1)), with sun_tab(0..3) =
  0 6 10 11, extended by sun_tab(6 − i) = sun_tab(i) and sun_tab(6 + i) =
  −sun_tab(i), interpolated linearly
- **true_date = mean_date + moon_equ/60 − sun_equ/60**

All of it is `Rational`. Numerators stay far below `Long` range for the
supported years (see 5.7); multiplication is overflow-checked anyway.

### 5.4 Days (§6, §8)

- A calendar day takes the number of the lunar day that **ends** during it:
  JD = ⌊true_date(d, n)⌋  (8.1).
- A lunar day that begins and ends within one calendar day gives a **skipped**
  number.
- A calendar day in which no lunar day ends takes the number of the following
  day, so the number is **repeated**; the first of the pair is the leap day
  ("Extra" in almanacs).
- The first day of a month is 1 + JD(day 30 of the preceding month), which
  works whether day 1 or day 30 is skipped or repeated. **Losar** is
  1 + JD(day 30 of regular month 12 of the preceding year) — the year can open
  with leap month 1 (last time 2000).
- JD → Tibetan date: estimate n and d from the mean motion, then search the
  neighbouring lunar days (Janson §8; Dershowitz & Reingold,
  *Calendrical Calculations*, give a full implementation).

The two definitions of skipped and repeated days in the original draft
described the same case; the rules above are the correct ones.

### 5.5 Decision: a₂ = 1/28

Henning uses the exact a₂ = 3781/105840 (Janson 7.24); the Men-Tsee-Khang
almanac uses 1/28. The two produce different dates about once a decade. v1
follows the almanac. Janson names three JDs where the choice changes the
date — 2451951 (2001-02-10), 2453866 (2006-05-10), 2460999 (2025-11-19) —
and they are test vectors that pin this decision.

### 5.6 Derived values

| Value | Formula | Janson |
| --- | --- | --- |
| Weekday | (JD + 2) mod 7 → 0 Saturday/Saturn … 6 Friday/Venus | (9.1), Table 5 |
| Day element | ⌈(JD amod 10)/2⌉ → Wood, Fire, Earth, Iron, Water | (E.12), Table 14 |
| Day gender | male if JD odd | E.4 |
| Day animal | (JD + 2) amod 12 → Mouse, Ox, Tiger, Rabbit, Dragon, Snake, Horse, Sheep, Monkey, Bird, Dog, Pig | E.4, Table 3 |
| Year in Chinese cycle | (Y − 3) amod 60; element ⌈z/2⌉ with z = (Y − 3) amod 10 | (4.4) |
| Rabjung cycle and year | m = ⌈(Y − 1026)/60⌉, n = (Y − 1026) amod 60 | (4.2), (4.3) |

The day element and animal must agree with the kyūreki day kanshi for the same
JD (§7.4); a cross-engine test checks this for every day of a whole cycle.

### 5.7 Holidays

Fixed Tibetan dates, from Henning, *Kālacakra and the Tibetan Calendar*,
Appendix II (via Janson §11). If the date is skipped, the holiday falls on the
preceding day; if repeated, on the first of the pair; holidays are not held in
leap months, except Losar. Dates from Henning's Phugpa archive and Edition
Rabten's calendars: Losar (1/1), Chötrul Düchen (1/15), Kālacakra (3/15), Birth
of the Buddha (4/7), Saga Dawa Düchen (4/15), Zamling Chisang (5/15), Chökhor
Düchen (6/4), Entry into the womb (6/15), Lhabab Düchen (9/22), Gaden Ngamchö
(10/25), Thanksgiving to the Protectors (12/29). Henning
also marks festivals inside leap months; this app does not. Rabten's Sangpo
Chuzom, the Ten Good Omens on 11/6, is not kept: the White Beryl has no ten
good omens, and names the 7th of that month, the first of spring by its
chapter 31, the meeting of the nine bad, the first date of the great black
day (§5.11; owner's decision, 2026-10-09: the White Beryl above all).

### 5.8 Almanac entries (Janson §10, Appendix E)

Each calendar day also carries the entries of a Phugpa almanac, all checked
day by day against Henning's computed calendars (§9):

| Entry | Rule |
| --- | --- |
| Lunar mansion | ⌊27 × moon at daybreak⌋, the moon from (10.1)–(10.2); for the first of two equal dates, the moon at the end of the lunar day minus 1/27 |
| Element pair | the weekday's element (Table 5) with the mansion's (Indian system, Henning's list): ten pairs, four inauspicious |
| Yoga | ⌊27 × (moon at daybreak + true sun)⌋ (10.4)–(10.5); Tibetan names as printed in Phugpa almanacs |
| Karaṇa | the half lunar day in effect at daybreak: H = ⌊60 × (moon − sun)⌋ + 1, fixed for H = 1, 58, 59, 60 |
| Lunar-day animal, trigram, number | Janson (E.9)–(E.11); the first date's trigram by the month's animal and its sme ba by the season (1 white, 4 green, 7 in the first, middle and last months of the Chinese reckoning's seasons) as the White Beryl's almanac verse gives them, vol. 1, p. 178 (`AlmanacPageTest`, [sources/almanac-page.md](sources/almanac-page.md)) |
| Hair-cutting day | by lunar day, from Lama Zopa Rinpoche's translation (FPMT, 2008); FPMT's list, not the White Beryl's, which gives no haircut results by date (ROADMAP T2.15) |
| Washing the hair | the White Beryl's thirty hair dates (vol. 2, p. 404): what washing the hair on each date brings, a detail of the lunar date's reading; the good and bad dates that follow them are the list of KP box 37, whose worn bad line they fill (the 27th on neither side; `ElectionalTest`, [sources/hair-dates.md](sources/hair-dates.md)) |
| Monthly observances | 8th, 10th, 15th (Sojong), 25th, 30th (Sojong), after Edition Rabten |
| Personal day | luck, life or anti weekday for the animal of the birth year (Rabten's table); needs a birth date (§10.5) |
| Personal mansions | whether the day's mansion is one of the six of the birth-year animal (bla, srog, dbang, skeg, bdud, gshed skar): the White Beryl, vol. 2, p. 330 (1996), its slips settled by the Sakya *nor bu'i me long* (p. 64) and Nam mkha' seng ge's *skar yig*, which print the same table ([sources/personal-mansions.md](sources/personal-mansions.md)); bla, srog and dbang skar lucky, the other three unlucky, as both texts call them; needs a birth date |
| Own days by the birth date | WB vol. 2, p. 338 (`ownDays`, ROADMAP T2.19): the birth weekday (the weekday of the birth date, p. 379); the weekday's place among the five of one's element, counted from the birth year's life force (*srog*), which p. 330 says is reckoned as the clan's element is: own (*rang gza'*, WB's *bla* and *dbang gza'* too, not the birth animal's *bla gza'*), mother, friend, child, enemy by the elements' relations, as the table of p. 346 gives them (`elementWeekday`), the weekdays' elements those of the *nag rtsis* (vol. 1, p. 257: Sun and Mars fire, Moon and Mercury water, Jupiter wood, Venus iron, Saturn earth); and the birth mansion, the almanac's mansion of the birth date (Phug pa Lhun grub rgya mtsho's coarse reckoning; no hour is asked). Birth and own weekday lucky with p. 338's works (battle array, contests of skill, pleading a case, trade, trials of strength, dice, horse races, archery, works that stir up strife); mother and friend lucky, anything good (p. 330); child no dot, middling (p. 330); enemy unlucky, every work avoided (p. 338); birth mansion lucky with p. 338's works. The element's mansions (p. 330, tables p. 345) and the mansion of conception (no source defines it) are not built ([sources/personal-mansions.md](sources/personal-mansions.md)); needs a birth date |

**The times within the day** (`DayTimes`; the White Beryl, vol. 1,
pp. 177–178 and ch. 15, pp. 180–182; [sources/almanac-page.md](sources/almanac-page.md),
*The times within the day*), reckoned as WB's almanac reckons them, roughly,
from its own figures, not from the sky, in chu tshod of 24 minutes from
daybreak at 05:00 (§10.3); shown and not weighed, as WB only says to write
them (ROADMAP T2.20, the owner's rule of 2026-10-09):

- **Second mansion:** the moon's motion is the difference of the moon at
  daybreak on the day and on the next; the moon enters a mansion where
  that motion's share of the day brings it to the mansion's start. WB
  writes a second mansion when it comes in daytime, within WB's day
  length from daybreak: 30 chu tshod at the equinoctial middle terms,
  1;10 more or less each sign-month, 33;30 at the summer solstice and
  26;30 at the winter (vol. 1, pp. 180–182; `dayLength`, by the true
  sun). A fast moon can enter two.
- **Skipped yoga:** the same on the yoga's sum (moon at daybreak with the
  true sun); a yoga that begins and ends between two daybreaks is written
  with the day's own, with both times.
- **Viṣṭi's span:** the later half of the 4th, 11th, 18th and 25th, the
  earlier half of the 8th, 15th, 22nd and 29th, half the date's length
  between the ends of the date before and its own (Janson (7.22)), shown on
  every calendar day it touches; the earlier half often falls wholly in
  the day before.
- **The sun's terms:** the twelve sign entries (*khyim 'pho*), each Hor
  month's breath term (*dbugs thob*) and middle term (*sgang*), at the
  measures WB lists in mansions and chu tshod of the true sun (the signs
  from Aries at 0;0, the 3rd month's *sgang* at 0;36 and *dbugs thob* at
  26;28, each kind stepping by 2;15). A term falls where the true sun at
  the ends of two dates brackets its measure: the excess's chu tshod times
  14 (*yid*), with its chu srang divided by 6 (*ro*), are taken in chu
  tshod from the later end, the time given in whole chu tshod, half a
  one counted as one (p. 182, read with Ngag dbang bzang po,
  question 15). It is written on the calendar day it falls in, one
  before daybreak on the day before, as WB writes it in the date before.
  The day's sme ba keeps its own count (§5.11).

### 5.9 Four aspects and the pebbles

Each year of the sexagenary cycle has four elemental aspects (White Beryl
f. 156a/b, f. 158a; Moonbeams ff. 5b–6b), in `Forces.kt`:

| Aspect | Rule |
| --- | --- |
| Vitality (srog) | the element of the animal's direction: tiger, hare wood; snake, horse fire; monkey, bird iron; mouse, pig water; ox, dragon, sheep, dog earth |
| Body (lus) | from a key element by animal (water for tiger, hare, bird, monkey; wood for ox, sheep, horse, mouse; iron for dog, dragon, pig, snake): the year's element the same as the key gives iron, feeding it wood, fed by it water, overcoming it earth, overcome by it fire |
| Destiny (dbang thang) | the element of the year |
| Luck (klung rta) | tiger, horse, dog iron; mouse, dragon, monkey wood; bird, ox, snake water; pig, sheep, hare fire |

A contrast sets an aspect of the birth year against the same aspect of
another sign and names what the other element is to the person's: mother
(it feeds yours), friend (yours overcomes it), identity, son (yours feeds
it), enemy (it overcomes yours). Two readings use it:

- **The day, the month and the hours**, from the divination of health
  (White Beryl ff. 295b–299a; Moonbeams ff. 31b–32a), which sets the
  person's vitality and body against those of the present year, month, day
  and hour. The month's destiny element follows from the year's
  by the month's animal: tiger, hare, mouse and ox months take its son,
  dragon and snake its friend, horse and sheep its enemy, monkey and bird
  its mother, dog and pig the same element (Gyurme Dorje p. 90, Table 2.5).
  The month's animal is its Phugpa one (§5.2 names, the 3rd month a horse).
  The lunar date's element runs through the elements from the son of the
  month's, the 1st and 6th its son, the 5th and 10th the month's own; its
  animal is the lunar-day animal of §5.8 (from the tiger in male months,
  the monkey in female ones). A doubled date repeats both, a skipped one is
  passed over. The day has twelve two-hour hours, the hare hour first; an
  hour's element follows from the lunar date's by the hour's animal: hare,
  monkey and ox hours take its son, dragon, bird and tiger its friend,
  snake and dog its enemy, horse and pig its mother, sheep and mouse the
  same element (p. 90, Table 2.7; chart 8.1's hour of a wood dragon day is
  an earth bird). The hours are counted in clock time at the place, the hare
  hour from 05:00 to 07:00: "the first astrological period of the day begins
  at dawn. Nowadays, this is standardly taken as from 5 to 7 o'clock
  wristwatch-time, regardless of the time of year" (Berzin, *Details of
  Tibetan Astrology 1*). Gyurme Dorje's Table 2.4 gives the same periods in
  solar time; the app follows Berzin, whom it cites. The Tibetan day of a
  civil date runs from its 05:00 to 05:00 the next morning. The four
  aspects of a month, date or hour follow from its sign by the rules above.
- **The year**, from the divination of obstacle years: each of the four
  aspects of the birth year against the present year's. Pebbles: mother
three white, friend two white, identity one white for earth or water and one
black for wood, fire or iron, son one white and one black, enemy two black
(White Beryl ff. 248b–249a; Moonbeams f. 28a/b). The app shows them as the
schematic charts write them, ○ for white and × for black. The year's
balloon shows these four; the full reckoning of the year, with the other
basic signs, is the year of age below. The life-spirit (bla, the element
that feeds the vitality) is not one of the four aspects the charts
compare, so it is not shown.

The rules and the direction were read in Gyurme Dorje's edition, *Tibetan
Elemental Divination Paintings* (2001), used as a reading copy: pp. 64, 68,
90–91 and charts 6.2 (p. 228) and 8.1 (p. 296), which give every cell of a
subject born in a fire dragon year read in an earth tiger year (§9). Chart
8.1's day, the 15th of the 3rd month of 1998, comes out of the calendar
engine as the wood dragon it prints. The app cites the White Beryl and the
Moonbeams.

#### 5.9.1 The year of age

For a person with a birth date, the elemental divination of the year of
age (`YearOfLife.kt`, `YearReckoning`; [sources/year-of-life.md](sources/year-of-life.md)),
by the White Beryl, Beijing 1996, vol. 1: chapter 21 (pp. 255–258) for the
signs, chapter 24 (pp. 380–416) for the obstacle years, with Lochen
Dharmaśrī's *Moonbeams* (KD vol. 3, pp. 493, 496, 507) where WB does not
say. Tested against Gyurme Dorje's Table 2.11 and every cell of chart 6.2
(`YearOfLifeTest`).

| Sign | Rule |
| --- | --- |
| Age | the Tibetan count: 1 in the year of birth, one more at each Losar |
| Natal mewa | the year's sme ba, one less each year round the nine; "the present rabjung's fire hare" (1687) has the 7 red (p. 255): the 九星 year star of the Tibetan year |
| Mewa of the year of age (*babs sme*) | the natal one in the middle of the square, then east, one place a year, on to the north-east in a male birth year, to the south-east in a female one, "whether the person is male or female" (p. 256) |
| Trigram of the year of age (*babs spar*) | "a man from li towards khon, a woman from kham towards khen; one trigram for each year" (p. 257); the Moonbeams' leap at each ten is not WB's |
| Progressed sign (*log men*) | a man from the tiger of the element his birth year's element feeds, forward through the sixty; a woman from the monkey of the element that feeds hers, backward (p. 387) |
| Sectors of growth and decline | for each aspect's element, the twelve sectors from the breath-taking (earth and water at the snake, fire the pig, iron the tiger, wood the monkey) to the tomb; the present year's animal gives the sector (p. 258); six good, six bad |
| Elements | a mewa's: the whites iron, black and blue water, green wood, the reds fire, yellow earth (p. 255); a trigram's: li fire, khon earth, dwa iron, kham water, zin wood, and khen, gin and zon, "also made earth" (the Moonbeams, p. 493) |

The gender (§10.5) decides the trigram and the progressed sign; without it
they and their pebbles are left out, and the predictive pebbles with them.

**The twenty-four decisive pebbles** (p. 380): each of the four aspects of
the birth year against six basic signs, in WB's order: the present year,
the progressed sign (aspect against the same aspect), the trigram and the
mewa of the year of age (their one element against each aspect), the
aspect's sector (its pebbles by rank: flourishing and working three white,
bathing and dressing two, body complete and birth one; breath-taking and
the womb one of each, decline and illness one black, death and the tomb
two black, the Moonbeams, pp. 496 and 507), and the hour of reckoning, the
present two-hour period (§5.9, its sign against each aspect). For each
aspect the white and black are counted, "not the threes and twos but
those that are there", and the predictive pebble goes on whichever are
more (p. 381); even, none. Gyurme Dorje's chart 6.2 lays its predictive
pebbles otherwise; WB's rule is followed.

**Readings.** Each pebble cell reads WB's prediction for that many white or
black on that aspect (pp. 380–384); each predictive pebble, white or black,
its own, and all four alike theirs (pp. 384–385). The sectors read p. 258;
the progressed sign its animal's place, the sky and earth doors (a man's
dog and pig, a woman's dragon and snake), the five ruins (ox, sheep),
separations (bird, monkey), lineage-cuttings (mouse, horse) and gains
(tiger, hare), and what its element governs (p. 387); the trigram of the
year of age its passage (pp. 391–396); the mewa of the year of age WB's
reading by the natal mewa (pp. 405–408; a few pairs have none).

**The year's obstacles** (pp. 387–391, 408–411), each with its reading:
the year of one's own animal from the 13th, read by the relation of the
year's element to the birth year's (13 son, 25 friend, 37 enemy, 49
mother, 61 own); the seventh (7 enemy, 19 mother, 31 own, 43 son, 55
friend); the two others of one's triad; the fourth animal counted up
(backward, illness) and down (forward, death); the progressed sign on
one's birth sign, on its seventh, or its element the enemy of one's
vitality; the nine-multiples, the 9th to the 81st, a man's counted up
from the mouse, a woman's from the bird; the combined nine-multiple, the
9th, 21st … 81st, when every aspect reaches its tomb counting up from its
element's key (earth and water the wood mouse, wood the fire hare, fire
the iron horse, iron the water bird), read by element and by which of the
seven it is (pp. 412, 414–415); the nine-multiple that meets the tomb, by
gender and birth animal (p. 410); the trigram's nine-multiple (a man's
trigram back on li, a woman's on kham) and the mewa's (back in the
middle), with the general remedies (pp. 413–414); the four tomb years of
the birth year's element, the great on its tomb animal and the small on
the seventh, of its own element and of the element that slays it (wood:
wood sheep and wood ox, iron sheep and iron ox), the own great tomb as one
of the four black undertakers (water dragon, wood sheep, fire dog, iron
ox), and the progressed sign on a tomb year, "slightly bad" (p. 412); the
tomb sign, the trigram of the year on the tomb trigram of the vitality
(wood khon, fire khen, iron gin, earth and water zon; p. 415); and the
mewa's small obstacles: on the present year's mewa (house), on the natal
(bed), on the two-black (land), the enemy of the natal or fire against
iron (royal gate), and on the mewa's sky door (a man's six-white, a
woman's one-white) or earth door (two-black, four-green; p. 409), read as
the doors of p. 387. An aspect in one of the bad sectors also reads its
decline (p. 409). Tones: the predictive pebbles and sectors by their colour, the
obstacles unlucky (the own year and the seventh at their mother year
mixed, as WB reads them good, and the progressed sign on a tomb mixed,
"slightly bad"), the progressed sign's gains lucky and its
doors and fives unlucky; mewa and trigram no tone.

**Not calculated**, for want of data the app does not ask for or of a
clear rule ([sources/year-of-life.md](sources/year-of-life.md)): the natal
trigram, which is the mother's trigram in the year of the birth, and the
secret obstacle that needs it; the obstacles of spouses, parents and the
dead; the four counted signs of the progressed sign (lifeline, peg, sky
and earth extension); the clan's nine-multiple (no clan is asked); the
readings of the mewa's nine-multiple by kind of person, which WB gives as
what "some hold"; the tomb days of the month (the 6th, 18th and 30th),
which are days, not years, and have no reading; the household readings;
and the Chinese pebble divination that ends the chapter (pp. 416–452),
a casting, not a reckoning.

Supported range: 1900–2100 Gregorian, for both engines. In 1.0 the date picker
does not offer dates outside it (§10.2).

### 5.10 Electional lists: the mansion and the day's activities

Edward Henning, *Horary and electional astrology of the five components*
(kalacakra.org), in `Electional.kt`:

- **Lunar mansions**, after the White Beryl and the *Treasury of Jewels*:
  each mansion's kind of work, nature, planet and foods, and the activities
  the list of mansions names it good for.
- **Activities**: Henning's selection of thirteen from the activity lists
  of the *'bras rtsis bai dkar dgongs don kun phan me long*, which follows
  the White Beryl: offerings to deities, taking a new home, starting a
  journey, astrology and divination, making weapons, marriage, funerals,
  setting up supports and temples, and the four activities (destructive,
  controlling, pacifying, increasing) with accomplishing health and wealth.
  For each, the good and bad weekdays, lunar dates, mansions, day animals
  (the lunar date's, §5.8, as WB's notes count the *nyi ma*: open question 9,
  answered 2026-10-04) and trigrams. What a list does not name is neutral.
- **The White Beryl's mansion verses** (vol. 2, pp. 313–328,
  [sources/mansion-verses.md](sources/mansion-verses.md), the lists read
  on the scans of the 1996 edition): what each day mansion's verse names
  good and to avoid, as wording keys in the verse's order
  (`MansionVerses.kt`). Kṛttikā's «དྲ་གྱོད» is read «དྲ་གྱོན», new clothes,
  with the Zhol print. Left out: words the reading leaves in doubt, what
  a verse calls middling or acceptable, omens that are no act (a birth, a
  death), and Abhijit. A work qualified by a direction or a kind of person
  is shown and weighs for neither side; "for the most part" and "but for
  the special cases" are the verse's general rule and count. Two acts the
  other lists did not have are new: Bon rites, and pitching tents, which
  is not building. Henning's "installing a deity" is *rab gnas*,
  consecration: his nine mansions for it are all ones whose verse names
  consecration good and none whose verse avoids it, while the verses'
  temples and images go their own way. *Bcud len* in the weekday verses
  is taking elixirs, an act of its own.
- **The print's other boxes**, read on its scans in the same way
  ([sources/kp-activities.md](sources/kp-activities.md)): 50 more
  lists, from naming and new clothes to building, sowing, trade, medicine,
  haircuts, ordination and teaching, rain and councils. A box that says
  "otherwise as box N" takes N's lists in the kinds it names nothing in; a
  box that joins two activities (crafts and haircuts, military training and
  games) is split, an entry qualified for one half counting for that half;
  boxes 50–51 come from the second print, KP2. The box for averting rites,
  whose entries all name a kind of rite, and the charts are not built. The lists' readings cite the
  print first, the White Beryl and Henning beside it.
- **The White Beryl's chapter 34**, the important works one by one (vol. 2,
  pp. 378–428, [sources/white-beryl-ch34.md](sources/white-beryl-ch34.md),
  every list the app uses read on the scans), which the print's boxes
  digest in the same order (`WhiteBerylWorks.kt`). It is joined to each
  box: what it names plainly good is added to the box's good and taken
  from its bad, and the other way round; what the box names and the
  chapter does not keeps the box's reading. So the chapter settles the
  doubled khrums of the offerings box (Pūrvabhādrapadā good,
  Uttarabhādrapadā bad), puts back what the box's abbreviations and
  numerals lost (khrums stod and smad, ༤ for ༦), and reverses the day
  animals of war and dice, which the box gives the other way. Its works
  that have no box are lists of their own: shows, hunting and theft,
  taming horses, averting rites (which it gives plainly) and sorcery.
  Forecasts (a birth, a prisoner, the sick, a death) are not works and
  are not built, nor the wheels counted from the Sun's or the Moon's
  mansion, which are moments.

The lists are taken as printed, with these rules for what is doubtful:

- A name in parentheses, or with a qualifier ("Monday (S only)",
  "Mūla (bad for entombment)"), counts for neither side; so does a
  category the source calls "merely acceptable" or "neutral". The one
  exception is the mouse, "bad for divination" in the divination list
  itself.
- Where Henning names a mansion twice for one activity, the mansions follow
  the print he translated, the *kun phan me long* (BDRC W4CZ65561, the
  Mtho las dgon print, activity tables img. 21–65), read box by box in
  [sources/mansions.md](sources/mansions.md): its abbreviations of khrums
  stod and khrums smad had been read as chu smad, so most of his doubled
  Uttarāṣāḍhā are Uttarabhādrapadā. The print's own reading is kept also
  where Henning left a mansion out (Citrā bad for offerings; Śatabhiṣaj good
  for journeys; Pūrvaphalgunī and Śatabhiṣaj good for controlling
  activity). A mansion the print still names in two places for one activity
  (Mṛgaśiras as acceptable and bad for controlling activity) is left out,
  unless the White Beryl's chapter 34 decides it (Uttarabhādrapadā, in both
  halves of the offerings box, is bad there).
- Rising signs are not factors of the day, since a day has no rising
  sign: the White Beryl's chapter 34 gives them as the works' own hours
  (§5.13), and the print's boxes are not read for them; left out are
  Abhijit, which the Phugpa calendar does not count among the day's
  mansions; "black" years, months and days, earth-lords and the demons,
  which the app does not calculate.
- Ranges are applied as stated: for pacifying, every mansion not named
  good, acceptable or neutral is bad; for health and wealth, waxing dates
  are good but for the 6th, 7th and 9th, waning dates bad.

Henning's list of natures joins two systems for two mansions (Śatabhiṣaj
"very stable, ephemeral", Uttarabhādrapadā "quick change, permanent") and
follows the Indian classes for Revatī; the White Beryl's own seven classes
(vol. 2, pp. 328–329, [sources/mansions.md](sources/mansions.md)) put
Śatabhiṣaj among the very stable, Uttarabhādrapadā among the quick and
good, Revatī among the unstable and changing, and those three readings add
WB's class after Henning's line.
A mansion's reading joins its White Beryl verse's lists, first, with what
the list of mansions names it good for and the activity lists' good and
bad, and cites the White Beryl and the print beside Henning; where they
disagree (Rohiṇī is good for marriage in Henning's list, to avoid in its
verse and the print) both stay, as elsewhere in the app, and the mansion
says nothing on that work. The day in brief (§10.3) weighs the factors as §5.12 says.

### 5.11 The White Beryl's readings: lunar date, weekday, yoga, karaṇa, trigram

Chapter 33 of the White Beryl (Sde srid Sangs rgyas rgya mtsho, 1685;
Beijing 1996 edition, vol. 2, BDRC MW2CZ8040) gives a reading for each of
the day's components. The texts, quoted from the scans, are in
[sources/](sources/README.md); the app states their facts in its own
English (§8.1), each reading citing its pages:

- **Lunar date** (pp. 297–304, [lunar-dates.md](sources/lunar-dates.md)):
  the good and bad activities of each of the thirty dates, a birth and a
  death on it, its place in the five-fold cycle (dga' ba, bzang po, rgyal
  ba virtuous; stong pa, rdzogs pa not, with their remedies), the four
  perilous dates (8, 15, 22, 30) and where the la, the life-spirit, sits
  (bla gnas), after the Phugpa list for people and for horses and other
  livestock, without a side (WB's man's left and woman's right belong to
  its Kālacakra list). *bla* is "the la (bla), the life-spirit", «ла
  (bla), жизненный дух», after Berzin's glossary; never "soul". Two acts
  were identified from the dictionaries and WB's own usage: shwa rags
  (22nd and 27th), a dike against flash floods, counted with dams; thag
  ser (29th), read as thog ser, casting lightning and hail, counted with
  fierce rites; both readings name the print's word. The dot: lucky on a
  virtuous day, unlucky on the other two, mixed on the 8th and the 22nd,
  virtuous but perilous.
- **Weekday** (pp. 308–312, [weekdays.md](sources/weekdays.md)): the
  verse on the day's planet: whose *bla gza'* it is, its caste, nature and
  element, the activities good and bad on it, when it is strong, setting
  out and its directions, a birth and a death on it, and what the closing
  verse forbids on it even when it is strong. Words the reading cannot
  identify stay out of the lists. The dot: Mars and Saturn unlucky, the
  closing verse avoiding virtuous work on them; the Sun mixed, named there
  too though its own verse calls it peaceful; the other four lucky.
- **Yoga** (pp. 347–349, [yogas.md](sources/yogas.md)): the short reading,
  the gist of the longer verse, and the avoidance verse (kun brdungs,
  yongs 'joms and sha 'khon avoided whole; six others from their start,
  for as many chu tshod of 24 minutes as the verse counts in number words:
  sel ba 3, zug rngu 5, skrangs pa and shin skrangs 6, kun 'joms and rdo
  rje 9). The White Beryl names most yogas
  otherwise than the almanacs; its name is shown under the reading. The
  dot: unlucky for the three avoided whole and the three whose short
  reading names a harm (rab stongs, gzer, 'bras), mixed for the other
  three avoided in part (sel ba, rma chen, rdo rje) and the one it calls
  middling (dga' ba), lucky for the rest. Its ranking verse is not used:
  how to read it is open ([open-questions.md](sources/open-questions.md) 1).
- **Karaṇa** (pp. 349–351, [karanas.md](sources/karanas.md)): what each
  verse names good, and for Viṣṭi what to avoid. Its name in the White
  Beryl is shown under the reading. The dot: Viṣṭi unlucky, the rest lucky.
- **Trigram** (vol. 1, pp. 449–450, [lunar-day-signs.md](sources/lunar-day-signs.md)):
  the date as the day of one of the eight goddesses the White Beryl counts
  through the month; its count by date and month gives the date's trigram
  under her name (*li* 'Od 'bar ma … *zon* Skyob byed ma, checked against
  every date by `TrigramGoddessTest`). Her day says what an illness that
  comes on then is traced to, the harming spirits, how it shows and the
  rites named. Tapping the trigram row opens it; an illness reading, with
  no lists and no dot. The date's sme ba has no reading of its own in the
  White Beryl or the *kun phan me long*: the readings found are of the
  person's progressed trigram and sme ba (T4) or of a sme ba counted from
  the solstice, so its row opens none. The date's animal is the *nyi ma*
  (open question 9): it carries the activity lists of §5.10 and, in WB's
  chapter 31 (vol. 2, pp. 223–226, [earth-lords.md](sources/earth-lords.md),
  with the *kun phan me long*'s chart, img. 103, as second witness), the
  earth lords of each animal day for the reckoning of the dead: where the
  earth lord sits in the house, what is bad there and its remedy, which
  part of the house it keeps to, where the hearth god is, and the earth
  lord who witnesses the day with its funeral rules. Tapping the
  animal's row opens them with the activity lists for a day of that animal;
  no tone, and not in the day in brief, which already counts those lists.
- **The *bla mkhyen* of the day** (vol. 2, p. 224, with the year's
  section p. 180 and the day's sme ba p. 192; [earth-lords.md](sources/earth-lords.md)):
  the astrologer spirit dwells in the direction of the day's seven-red,
  where the 7 stands when the day's sme ba is put in the middle of the
  square (south at the top: 4 9 2 / 3 5 7 / 8 1 6 for 5); the year's
  section names what is avoided towards it, whom it harms and the remedy.
  The day's sme ba here is not the date's: it is the sixty-day count that
  the White Beryl starts at the one-white on the winter solstice, with the
  rule Blo bzang sbyin pa's *Tsi na'i rtsis la 'jug pa* and a text in the
  *Mdo khams stod* collection give (open question 11): a wood-mouse day
  takes the sme ba of the stretch it falls in between the sun's longitudes
  270°, 330°, 30°, 90°, 150°, 210° (the mid-month terms, dated in Lhasa
  mean solar time, UT + 6:04:24), 1, 7, 4 counting up and 9, 3, 6
  counting down, and every other day counts on from the last wood-mouse
  day (`DaySmeBa`). It is the row of the "Also today" section, after the
  Almanac and Your day (§10.3), its sheet opening
  with the direction's compass and the moved square that finds it (§10.7),
  and the details giving the day's sme ba, the wood-mouse day it counts
  from and the seven-red's place; no tone, not weighed (§5.12), as it holds
  for a direction, not for the day.
- **The great black day** (*nyi ma nag chen*, vol. 2, p. 226;
  [great-black-day.md](sources/great-black-day.md)): the first of the
  earth lords that move by date, one date in each month by the season-month
  of the Chinese reckoning, as chapter 31 counts (§5.13): the 7th, 14th and
  21st of the 11th, 12th and 1st months, the 8th, 16th and 24th of the 2nd
  to 4th, the 9th, 18th and 27th of the 5th to 7th, the 10th, 20th and 30th
  of the 8th to 10th (`GreatBlackDay`, `GreatBlackDayTest`). On 11/7 Rāhu
  meets his sister: the meeting of the nine bad (*ngan pa dgu 'dzom*),
  "the worst of the bad"; the other dates name the sisters defiled and the
  bellies of blood, iron and water arising and bursting. On all twelve
  works for the dead and the living are bad, above all sending wealth out,
  empowerment and consecration, a bride, building, burial and trade, and no
  virtuous work is done; harmful means go along with it, so black rites are
  its good list. Keyed by the date as it stands, like Rāhu's course by month
  (a skipped date has none, a doubled one has it on both days, a leap month
  as its month: WB's almanac writes the earth lords for both, vol. 1,
  p. 178). A row in "Also today" after the *bla mkhyen*, titled the meeting
  of the nine bad on 11/7, with the date's event, WB's results and its
  remedies in the sheet; unlucky, and not weighed (§5.12): it is one of
  chapter 31's earth lords, which the verse that ranks the day's factors
  (vol. 2, p. 376) does not place. WB's other tradition (the 24th in three
  last months, the 9th in the first of winter) is not built.
- **The burning date** (*bsreg tshes*, vol. 1, p. 177, its reading vol. 2,
  p. 351; [burning-dates.md](sources/burning-dates.md)): the weekday
  meeting one of its two dates, Sunday the 12th and 27th, Monday the 11th
  and 26th, Tuesday the 10th and 25th, Wednesday the 3rd and 18th,
  Thursday the 6th and 21st, Friday the 2nd and 17th, Saturday the 7th and
  22nd, by the day's own weekday and date (`BurningDate`). Bloodletting,
  moxibustion and virtuous work fail, cremation is bad, fierce work
  favoured, with a remedy; chapter 34 adds funerals, supports and temples,
  and life and wealth (pp. 404, 414, 426). Its tone is bad. WB's marked
  case, a burning date that begins before nightfall on the day before
  (p. 177): where the date before a weekday's burning date ends before
  nightfall (WB's day length, §5.8), the burning date
  is a part of the special days' row on that day, with the time it
  begins and WB's hook (`DayTimes.burningFrom`, §5.8); shown and not
  weighed, as WB names no weight for the hook.

The lists' wordings map to activities like every other reading's (§8.2).
The day in brief (§10.3) weighs them with the lists of §5.10 as §5.12 says.

### 5.12 Weighing the day

Two texts say how the factors of a day weigh against each other, and the
app follows both:

- **The *kun phan me long*** (its opening prose, img. 13–14, read on the
  scan: [sources/weighing.md](sources/weighing.md)): Rāhu, the weekday, the
  mansion, the date, the karaṇa, the yoga and the day's animal sign (*nyi
  ma*), each stronger than the next; where the planet and the mansion
  disagree the planet leads, where the mansion and the date disagree the
  mansion; where good and bad are mixed among them all, the combination
  (*'phrod*) leads, then a particular case (*dmigs bsal*), without one the
  general grouping (*phyogs sdebs*), and where good and bad still differ
  the stronger; a combination period (*dus sbyor*) outweighs everything.
  The passage is a prose digest of the verse that closes the White Beryl's
  chapter 33 (vol. 2, p. 376), which gives the weights it opens with as
  the view of the paṇḍita of Kashmir and takes the Phugpa order of
  strength instead.
- **The White Beryl** (vol. 2, pp. 331–337, [sources/combinations.md](sources/combinations.md)):
  the combination of weekday and mansion makes every work good or bad, so
  that "even when the planet and the mansion are each good, a bad
  combination becomes its result" (p. 333); of the special days of weekday
  and mansion, the do's and don'ts "matter somewhat", while "the individual
  results of planet and mansion are the main thing" (p. 337).

Rāhu's course is by date and direction, the hour and the combination period
are times within the day (§5.13). The app weighs the day by **one rule**,
which answers two questions: what the day is (its tone) and what each work
is on it (its side, good or to avoid). The two answers come out of the same
voices, so that the tone and the lists cannot go separate ways.

- **The voices**, strongest first: the combination of weekday and mansion
  (*'phrod*: the named combination, *'phrod chen*, and the element pair,
  §5.8, one voice); Rāhu, on the dates its detailed course, its course by
  month or its course among the earth lords names (§5.13), the first of the *kun phan me long*'s seven; the
  weekday; the mansion; the special days of weekday and mansion with the
  burning date (§5.11), which WB's almanac writes with them (vol. 1,
  p. 177), one voice; the lunar date; the karaṇa; the yoga; the day animal (the lunar
  date's, as §5.10's lists). The trigram is not among the seven; it ranks
  last, so its lists (§5.10) decide only a work no other voice names.
- **A voice's tone** on the day: the named combination's is its short
  reading's (16 lucky, 12 unlucky); the element pair's is its grade in
  WB's verse (p. 333), «བཟང་གསུམ་གསོ་ཐུབ་གསུམ། །ངན་གསུམ་ཐ་ཆད་གཅིག», read in
  the verse's order: three good (earth–earth, water–water, earth–water),
  three that sustain (*gso thub*: fire–fire, wind–wind, fire–wind, whose
  verses promise food and clothes, quick success and good omens), both
  lucky; three bad (earth–wind, water–wind, earth–fire) and the worst
  (fire–water), unlucky. The combination has a tone when its two parts
  agree. The weekday's, date's, karaṇa's and yoga's are their dots (§5.11).
  The special days have one when all of the day's agree. Rāhu, the mansion
  and the day animal have none: Rāhu is reckoned by direction, and every
  mansion and animal has lists on both sides (the White Beryl's seven
  classes of mansions, vol. 2, pp. 328–329, are kinds of work, not good and
  bad). Nor does the mansions' rise and decline (*dar gud*, pp. 329–330)
  give one: a mansion rises when the full moon falls on it, by its
  quarter, or by its element against the moon's life force, rising good
  and declining bad, but the White Beryl closes the passage saying that
  this reckoning lacks a scriptural source of force
  ([sources/mansion-verses.md](sources/mansion-verses.md)).
- **A voice's side** on a work: what its lists name (the element pair's
  (§5.11), the weekday's verse with Henning's weekday list, the mansion's
  reading (§5.10), each special day's and the burning date's, the date's verse with Henning's date
  list and FPMT's hair-cutting day, the karaṇa's verse, the day animal's
  and the trigram's lists; Rāhu's courses; the named combination and the
  yoga carry none). A factor whose own lists name a work both ways, and a
  group whose members do, says nothing on it.
- **The combination is the result** (WB p. 333) where it speaks: on the
  day where its two parts agree, on a work where its element pair's list
  names it; every voice on the other side is outweighed, "even when the
  planet and the mansion are each good". Its tone on the day does not
  decide a work it does not name: a lucky combination makes the day lucky,
  but does not lift what the date, the weekday or the karaṇa forbid, nor
  an unlucky one forbid what they name good. Such a work is weighed by the
  voices that name it, as below. (From 2026-10-06 until the same evening
  the combination's tone decided every work, which emptied the avoid list
  on every day with a lucky combination and the good list on every day
  with an unlucky one.)
- **Otherwise the strongest voice that takes a side**: for a work, the
  strongest whose lists name it (the work's *dmigs bsal*, its particular
  case); for the day, the strongest that has a tone of its own (its
  *phyogs sdebs*, a factor's general place among the good or the bad).
  With two voices that disagree this is rule 2, the planet before the
  mansion, the mansion before the date. Voices are not counted: the White
  Beryl's verse, which the *kun phan me long* digests (vol. 2, p. 376),
  weighs what is still mixed by «མང་ཉུང་སྟོབས་ཀྱི་ཁྱད་པར», greater and lesser
  strength, and neither *dmigs bsal* nor *phyogs sdebs* is a count of
  sides ([sources/weighing.md](sources/weighing.md), open questions 12–13,
  answered 2026-10-06; until then the app took the side more voices took,
  which on 2000–2049 gave 1,533 days the other tone). The trigram, ranked
  last, decides only a work no other voice names. The special days
  stand in their own rank, below the weekday and the mansion (WB p. 337:
  their do's and don'ts "matter somewhat", "the individual results of
  planet and mansion are the main thing"). The person's own weekdays and
  mansions, which a later reader counts as a *dmigs bsal* above the rest,
  are not weighed: the White Beryl calls them "of particular importance"
  (*khyad par gces pa*, vol. 2, p. 338), and neither it nor any other text
  found places them against the combination
  ([sources/personal-mansions.md](sources/personal-mansions.md), open
  question 14, answered: the texts' own days are by the clan's element
  or the life force's, and the birth weekday and mansion). The day in
  brief lists them apart, under "For you", the roles of the day's
  weekday as one entry, lucky or unlucky where those that take a side
  agree and mixed where they do not (`sharedTone`; the child's weekday,
  middling, takes none). Two are the exception, which WB makes absolute
  (p. 338): on the enemy weekday of one's element "every work is to be
  avoided", and on the death mansion, the slayer mansion (*gshed skar*)
  of p. 330, "anything is bad". On them, for the person with a birth date
  set, the brief has no good list (`DaySummary.avoidAll`, whose `good`
  is then empty), and the election does not offer the day for any work
  (§5.14); the day's tone, its avoid list, `sideOf`, the Almanac rows and
  the hours stay the weighing's, the same for every reader (ROADMAP E6).
  For the test birth date, 1 June 1976 (earth, Dragon),
  October 2026 has five Thursdays and one Pūrvaphalgunī, the 9th
  (`DaySummaryTest`).
- **The day in brief** names the tone and what decided it: the
  combination, or the strongest factor that takes a side, by name
  ("by Thursday"; `DayVerdict.factor` and `.deciding`, the special days
  named together where they spoke as one). That factor's row in the
  Almanac carries a "decides" mark, and its sheet says so under the
  gloss; where the combination decides, its one row carries it. Over 2000–2049 the day is decided by the combination on 9,672
  days, the weekday on 7,708, the special days on 568, the date on 292
  and the karaṇa on 23; the yoga never decides, since the date or the
  karaṇa before it always has a tone (`DaySummaryTest`). The brief lists the voices that took
  that side, and gives the works grouped by the voices that carry them
  (ROADMAP U4, built 2026-10-09; `byVoices`): each group headed by its
  voices in rank order, its works one wrapped run of glyphs and names,
  each opening its workings; the groups in the works' order, those more
  voices agree on first, then those a stronger voice decides. Its summary line is
  one row (§10.7): a work's weight there is the sum of the weights of the
  voices standing on its side, ten for the combination down to one for the
  trigram, in the rank above. The weights are the app's, to order the line
  by the Phugpa order of strength; they change no side. The texts' only
  numbers, the date one, the planet four, the mansion eight, are the
  Kashmiri paṇḍita's, which the White Beryl sets aside for that order (vol.
  2, p. 376), so the app does not use them. Outweighed voices,
  and works that only outweighed voices name, are not shown: they have no
  power on the day, and their own readings still say what they say. On a festival or monthly observance the brief
  says that the tone is the day's for its works and does not weigh the
  festival's merit: the texts give no rule placing a festival in the
  weighing.
- **Order on the page** (ROADMAP U1 and U3, built 2026-10-09; U6, built
  2026-10-09): the page runs in the order of strength. First the hours
  (§5.13), the one factor the texts put above every factor of the day, as
  a "Hours" row above the brief; then the day in brief; then "Works", the
  verdicts on single works (the haircut), which are results of the
  weighing and not voices in it; then the Almanac section, which holds
  what is weighed, after the monthly observance: the voices in rank order
  (the combination, Rāhu, weekday, mansion, special days, lunar date,
  karaṇa, yoga), each voice one row, so that the named combination and
  the element pair are one "Combination" row with both dots, as the
  special days and Rāhu's courses are. The Almanac's heading names what
  decides the day ("by the combination", "by Tuesday"), and only that
  voice's dot is solid: the other rows' dots are outlines, so that the
  column of dots reads as each voice's own tone and not as a count of
  sides, which the White Beryl does not make (above). What is shown but not weighed stands apart: the person's
  own days and mansions in "Your day", with vitality and body; the
  *bla mkhyen*, the great black day and the earth lords' courses (§5.13) in "Also today". The nectar periods, times within the day
  (§5.13), are in the brief's "By the hour" and on the hours panel, not
  in the Almanac.

The weights the *kun phan me long* gives first (the date once, the planet
fourfold, the mansion eightfold) put the mansion above the planet, against
its own rule for disagreements; they say what a good factor is worth, and
are not used to decide: the White Beryl gives them as the paṇḍita of
Kashmir's and sets them aside for the Phugpa order of strength (vol. 2,
p. 376; [open-questions.md](sources/open-questions.md) 12). Where the special
days rank among the single factors is the app's reading of WB p. 337; the
texts do not place them against the date, karaṇa and yoga.

On 2000–2049 (18,263 days) the rule decides the tone by the combination on
9,672 days (53 %) and by the strongest factor on the rest. The days whose
lists run against their tone (more than twice as many works on the other
side) are 2,067 (11 %; 2,082 before Rāhu's course among the earth lords,
T2.13, put its lists in his tier; 2,085 before the burning date, T2.12;
2,140 before the White Beryl's chapter 34 was built,
T2.6, which changes no day's tone; 2,087 before the mansions' verses,
T2.5); 3,892 were under an earlier rule, which let the
special days decide and each work go to its strongest voice. A day's tone
and its prohibitions are separate questions, and a lucky day may still
have much to avoid, so the In brief row gives the two counts beside its
glyphs (§10.3). `DaySummaryTest` keeps these numbers.

### 5.13 Rāhu, the hours and the combination period

- **Rāhu's course** ([sources/rahu.md](sources/rahu.md)): the White Beryl
  (vol. 2, pp. 236–238) gives it by lunar date twice, a general course for
  all thirty dates (time and directions, no activities) and a detailed
  course for the sixteen dates on which Rāhu enters a direction or turns
  back (1, 4, 6, 8, 11, 12, 14, 15, 17, 18, 21, 22, 24, 25, 27, 29): when,
  where to, how many days it stays, and what that day is good and bad for.
  The app shows the detailed course on those dates as a reading row
  ("Rāhu's course") and weighs its lists (§5.12). On the other fourteen
  dates the same row gives the general course, when and from where to where
  Rāhu moves, which the *kun phan me long*'s chart (§7, img. 78) confirms
  date by date, with WB's rule for fierce work (go along with its course,
  never face it, p. 238); it names no activities and takes no part in the
  weighing. The row's compass (§10.7) draws the move the row's reading
  gives: the detailed course's on the eight dates it enters a direction
  and moves on (4, 8, 12, 15, 18, 22, 25, 29), which runs against the
  general course on the 12th (north to south, not south to north) and the
  18th (east to west, not south-west to north-east); the general course
  on the other dates, the turning-back ones included, whose detailed
  reading names no course, its caption naming which (`RahuCourse`, tested
  against the general course's table in rahu.md). Both readings add
  Rāhu's course over the hours of any day
  (p. 239): eight named times of day, each with the direction it moves
  from and to. They stay in the reading, not on the hours panel, since the
  text names times of day rather than clock hours. On the dates WB gives
  for its course by month (pp. 238–239: the first and middle month of
  spring, the three months of autumn) a second Rāhu row names the form it
  takes and fierce rites good, weighed in Rāhu's tier like the detailed
  course. Its seasons are the Chinese reckoning's (`SeasonReckoning`),
  as everywhere in WB's chapters on the elemental reckoning (20–32): the
  first month of spring is the 11th, the tiger month, the middle the
  12th, and autumn the 5th to the 7th
  ([sources/tibetastromed.md](sources/tibetastromed.md), *WB's months and
  seasons*).
- **Rāhu among the earth lords** (vol. 2, p. 232, section 27 of the
  earth lords that move by date, [sources/earth-lord-courses.md](sources/earth-lord-courses.md)):
  the dates of each season-month on which Rāhu seeks food, two in the first
  month of spring (the 11th and 28th) down to none in the last of winter
  (`RahuBySeason`); works great and small for the living and the dead are
  avoided, above all work with earth and with mud (the Zhol print's
  «ཁྱད་པར་ས་ལས་འདམ་ལས་ངན», which the 1996 edition misprints), hurling *zor* and
  fierce work auspicious. A third reading in the Rāhu row, weighed in his
  tier like the course by month; it takes the count of days whose lists run
  against their tone from 2,082 to 2,067 (§5.12).
- **The earth lords that move by date** (WB vol. 2, pp. 226–235,
  [sources/earth-lord-courses.md](sources/earth-lord-courses.md)), which
  WB's almanac writes on the day (vol. 1, pp. 173, 178): after the great
  black day (§5.11), the small black day, *pi ling 'phar ma*, *zin phung*,
  *phung po zor thogs*, *ki kang*, *hal khyi*, *gnam khyi*, *gnam sbyor*,
  *gza' rgod*, *dbul po*, *gza' bdun*, *ngam shing*, *bar khyi*, *ka khyung
  ki kang* and *dra chen* on their dates; the eight classes' and the nāgas'
  strikes and turnings, a strike naming good the works its turning names
  to avoid (p. 232; p. 234; again p. 364); the earth lords' turning; the
  *gnyan*'s moving times, with Spug ston's strikes and turnings of the
  *gnyan*, the only ones WB gives; the classes' own times; and the sky door
  of every date by the last figure of its number (the 1st, 11th and 21st
  the guests', … the 10th, 20th and 30th the general one), each door's own
  work avoided (pp. 368–369). Last, the black day (p. 235): the date's animal
  the four-slayer of the month's, three either side, as WB reckons the
  black months (vol. 1, p. 183), with the Paṇchen's black day by year as
  another view. Keyed by the Chinese reckoning's season-month,
  which is also the animal month from the tiger (the 11th), by the date and,
  for *zin phung* and *gnam khyi*, by the date's animal; a skipped date has
  none, a doubled one both days, a leap month as its month
  (`EarthLordCourses`). Where WB gives a course and then another way, or
  what "some say", the first is built; WB's model almanac (vol. 1,
  pp. 154–171), whose day boxes write the courses, settles the readings it
  can check and is the test's witness (`EarthLordCoursesTest`). Shown and
  not weighed: WB's order of strength (vol. 2, p. 376) ranks the seven
  factors and the combination period and gives the earth lords no place
  (§5.12). One row in "Also today" (§10.3).
- **Jupiter's nectar periods** ([sources/nectar-periods.md](sources/nectar-periods.md)),
  the *kun phan me long* §10: each double hour is halved, each half ruled
  by a planet, counted from the weekday's own planet six on by day (from
  dawn) and five on by night (from sunset); Jupiter's halves are the
  nectar periods. On the twelve hours from the hare hour at 05:00 (§10.3)
  each half is a clock hour, shown as dots on the hours panel's dial and in
  the day in brief's "By the hour"; the hour's nectar row on the panel
  opens the reading, which lists what the activity tables name them good
  for. It takes no part in the weighing: it is a time within the day.
- **The combination period** (*tatkāla dus sbyor*,
  [sources/combination-period.md](sources/combination-period.md)), which the
  texts hold above every factor of the day: the sign rising in each hour.
  KP's table (§9, img. 79–80) gives the month's sign at daybreak and one
  sign more each hour (the 3rd month Aries … the 2nd Pisces); WB (vol. 2,
  pp. 371–376) gives for each sign what is good and bad while it rises and
  whether the period is to be accomplished or avoided. It is shown on the
  hours panel (§10.3) and in the day in brief's "By the hour" block, not
  weighed into the day: a day reading has no hours.
- **The works' own hours** (ROADMAP E4,
  [sources/white-beryl-ch34.md](sources/white-beryl-ch34.md), *Rising
  signs*): WB's chapter 34 names, for 55 of the app's works, the signs
  rising good and bad for each, read on the scans with the lists' own
  rules (middling, acceptable, qualified or doubled: neither; "the others
  bad" as stated), in `WhiteBerylWorks.SIGNS` (`Factors.signs`). They are
  the work's particular case, which stands above the period's general
  reading for it (WB vol. 2, p. 376: «དམིགས་བསལ་བྱུང་ན་དེ་ཉིད་གཙོ»): an
  hour's period reading drops the wordings whose act (`Activities`) the
  chapter names on the other side under that sign (`Texts.period`; on
  the twelve signs, Gemini's empowerment, practice and study, against
  ordination and teaching), and the works the chapter names for the sign
  are a row of their own (`Texts.WORKS_SIGN`). Like the period they are
  times within the day and are not weighed into it (`WorksSignTest`).
- **The earth lords of the hour** (WB vol. 2, pp. 235–236,
  [sources/earth-lord-courses.md](sources/earth-lord-courses.md)): *g.yu
  mdzod sngon mo*, the god of the hours, and the earth lords *khang brtsegs*
  and *mtsho sngon*, each on the place of the hour that it is (in the tiger
  hour the tiger's, the upper east, and so on in order), with what WB names
  bad there and its remedies, one reading for the three; and the black
  hours, the date's animal against the hour's as the black days set the
  month's against the date's (on a mouse or horse day the bird and hare
  hours; `EarthLordCourses.blackHour`), every important work bad and fierce
  work striking home. The hour's *bla mkhyen*, on the *klung rta* of the
  hour's triad (vol. 1, p. 254: the tiger, horse and dog's the monkey, the
  pig, sheep and hare's the snake, the mouse, dragon and monkey's the
  tiger, the bird, ox and snake's the pig), with the year's avoidances and
  remedies (§5.11); and the hour's *sa rgyal*, on the four-slayer in front
  of the hour, the upward one, three animals on (vol. 1, p. 235 counts
  them up and down; the *'Bras rtsis rab gsal nor bu'i me long* puts it
  on the hare in the mouse hour), spectacles, corpse rites and forts avoided
  (`EarthLordCourses.hourBlaMkhyen`, `hourSaRgyal`). Each names its place
  by the animal and WB's direction for it (vol. 1, p. 254: tiger upper
  east … ox north-east). The *sa rgyal* the other way WB gives, *pi ling
  'phar ma*'s course, one hour and place in each season-month of the
  Chinese reckoning (the snake hour on the upper south in the first of
  spring … the dragon hour on the bird's place in the last of winter), as
  another view, in its hour only (`hourSaRgyalOther`). The hidden earth
  lords (*gab pa'i sa bdag*, p. 221), one to each animal, on the place of
  the hour's own animal (*gnyan khra* the mouse's … *phyug po* the pig's),
  a corpse not led their way. The black sky dog (p. 197, for year, month,
  day and hour by the new Chinese reckoning): its head on the hour's
  animal, its tail on the seventh, its twelve parts clockwise (*chos
  skor*) on through the animals, as a Gyalrong rtsis collection lays them
  out, each with what it forbids and what it does to a bride
  (`gnamKhyiPart`). On the hours panel (§10.3), not weighed.
- **Not built**: the hour against the day's animal sign (KP's rule 2).

### 5.14 The election: the best day for a work

The user picks a work and a span; the app gives the days the weighing
makes good for it, best first, and the hours within them
(`Election`, `ElectionSpan` in `core/.../texts/Election.kt`; ROADMAP E).
No rule of weighing is new: each day is weighed exactly as its page
weighs it (§5.12), one `DaySummary.of` per Tibetan day, so what the day
page and the election say of a work can never differ, and every voice
built later reaches the election by itself.

- **The works offered** are those some list of the day's voices names
  (§5.12: the element pairs, Rāhu's courses, the weekday's, mansion's,
  special days', burning date's, date's, karaṇa's, day animal's and
  trigram's lists), grouped by family (§10.7) in the summary line's
  order: 137 works in 35 families. "Everything" is left out, a list's
  word for the whole day and not a work one chooses.
- **The span** runs from the shown day to the end of the first, third or
  twelfth Tibetan month, counting the shown day's own as the first, one
  day per civil day (a doubled date twice, a skipped one not at all), and
  never past the end of 2100, the date picker's range. A span is weighed
  once, with the person's days where a birth date is set, and every
  work's election reads it.
- **A day's side** for the work is `DaySummary.sideOf`: good, to avoid,
  or blank where no voice names the work, never "neutral, so fine". What
  decided it is the voice that decided the work in the weighing
  (`ActivityNote.decider`): the combination where its element pair names
  the work, otherwise the strongest voice naming it.
- **The order of the good days**, the texts' as far as it goes and the
  app's only where it stops (`Election.ORDER`):
  1. the days on which the combination names the work good: it is the
     result, "even when the planet and the mansion are each good" (WB
     vol. 2, p. 333);
  2. then by the strongest voice naming the work good, in the Phugpa
     order of strength (KP rules 2–4, WB vol. 2, p. 376): Rāhu, the
     weekday, the mansion, the special days, the date, the karaṇa, the
     yoga, the day animal, the trigram;
  3. within one rank, a day whose combination is lucky before one whose
     combination is unlucky or has no tone (its two parts disagree):
     p. 333 makes it the day's result, so it may order days that already
     stand on one side, though it decides no work it does not name;
  4. then the sum of the standing voices' weights, ten for the
     combination down to one for the trigram, the app's convention of the
     In brief row (§5.12), which the screen says is the app's;
  5. then the earlier day.

  Not used: KP's weights of one, four and eight (the paṇḍita of
  Kashmir's, which WB p. 376 sets aside), a count of voices, and
  outweighed voices, which are neither shown nor counted. The days to
  avoid are red in the grid and given no list of their own.
- **The person's days** (§5.12) are marked on the days they fall on and
  not weighed. The two WB makes absolute (vol. 2, p. 338), the enemy
  weekday of one's element and the death mansion, take the day away from
  the person with a birth date set: it is not offered for any work, and
  says why on tap; its side stays the weighing's, the same for every
  reader (ROADMAP E6). For 1 June 1976, October 2026 loses the
  Thursdays 1, 8, 15, 22 and 29 and the 9th (`ElectionTest`).
- **The hours** (ROADMAP E3): the combination period (§5.13) follows the
  month and the hour only, so every day of a Tibetan month has the same
  periods at the same clock hours; the election gives them once per
  Tibetan month of the span: the periods that name the work good, and
  those that name it to avoid, a sign naming it both ways saying nothing
  (`Election.hours`): the work's own signs first (WB's chapter 34, the
  works' own hours of §5.13, its particular case), then the sign's reading
  (WB vol. 2, pp. 371–376) without what they decide the other way.
  Journeys, for one, are good while Capricorn, Virgo, Sagittarius or
  Pisces rises and bad in the other eight hours (p. 393, `ElectionTest`). On each
  good day it gives the nectar periods (§5.13) where their reading names
  the work. The hours choose the hour, never the day: a day to avoid is
  not offered "only at these hours", since every day of a month has every
  sign (ROADMAP E, D1).
- **Cost:** a twelve-month span is about 380 day summaries, weighed off
  the main thread and kept per start, length and birth date (`Spans` in
  `ElectionScreen.kt`); a work's election reads the kept span. On the
  `zanshin-test` emulator a twelve-month span was on screen within 4.3 s
  of the tap, uiautomator's polling included, with no frame skipped
  (2026-10-09).
- **Witness:** for October 2026 the election agrees with the
  tibetastromed.ru calendar's on haircuts on all 7 of the days it names,
  weddings on 11 of 12 and setting out on 11 of 14; each difference is a
  WB reading the site does not follow ([sources/tibetastromed.md](sources/tibetastromed.md),
  *The election, October 2026*). The calendar is a witness, not a
  source. `ElectionTest` checks on 2000–2049 that every work's side is the
  day page's and that the order holds, and keeps October 2026 ranked for
  haircuts, weddings and setting out.
- **The 旧暦's** is §7.6, unranked (ROADMAP E5).

## 6. Astronomy library

**Source:** Jean Meeus, *Astronomical Algorithms*, 2nd ed. (1998).

| Function | Method |
| --- | --- |
| ΔT = TT − UT | Espenak & Meeus polynomial expressions (NASA Five Millennium Canon), in code, no table download |
| Apparent solar longitude | VSOP87 series truncated as in Meeus Appendix III, plus nutation and aberration (ch. 22, 25, 32) |
| Instants of new moon | Meeus ch. 49 |
| Instant of a solar longitude λ | Newton iteration on the solar longitude |
| Sunrise, sunset, transit | Meeus ch. 15, standard altitude −0°50′ |

Accuracy targets, checked against the NAOJ 暦要項 values in the test vectors:
new moons and solar terms within 60 s; sunrise and sunset within 1 min.

Events that fall within ±5 min of midnight JST decide which day a month or term
lands on and cannot be trusted to a 60 s error budget. They must have a test
vector taken from NAOJ, and a test lists every such event in 1900–2100.

## 7. Kyūreki engine (Tenpō rules)

This is the Chinese-derived lunisolar calendar Japan used until 1872. Neither
it nor rokuyō is Shinto, which is why the original draft's "Shinto" label is
gone; the screen calls the panel 旧暦.

### 7.1 Rules

All instants in JST (UTC+9) — the civil zone, not a true solar meridian.

1. A month begins on the JST day containing the new moon.
2. The twelve **major terms** (中気) are the instants when the solar longitude
   is a multiple of 30°. A month is numbered by the major term it contains:
   雨水 330° → 1, 春分 0° → 2, 穀雨 → 3, 小満 → 4, 夏至 → 5, 大暑 → 6,
   処暑 → 7, 秋分 → 8, 霜降 → 9, 小雪 → 10, 冬至 → 11, 大寒 → 12.
3. A month with no major term is a **leap month** (閏月) and takes the number
   of the month *before* it — the opposite of the Tibetan placement.
4. The months containing 冬至, 春分, 夏至 and 秋分 are always 11, 2, 5 and 8.
   Where rules 2–3 leave a choice, this fixes it.
5. **2033 problem.** In 2033–34 the rules above contradict each other. The
   engine follows the resolution the 日本カレンダー暦文化振興協会 (暦文協)
   recommended at its general meeting of 28 August 2015: 閏11月, the
   lunation from 2033-12-22 (rekibunkyo.or.jp/year2033problem.html). The
   society left the general intercalation rule open, so the engine keeps
   the choice as a named, single-place decision in code; the NAOJ takes no
   side ([sources/kyureki.md](sources/kyureki.md)).

The 24 solar terms (節気) are the 15° points. Index them by longitude with
立春 = 315° as the first, the Japanese almanac order.

### 7.2 Rokuyō

(month + day) mod 6 → 0 大安 Taian, 1 赤口 Shakkō, 2 先勝 Senshō,
3 友引 Tomobiki, 4 先負 Senbu, 5 仏滅 Butsumetsu. A leap month uses its own
number. Consequence: the first day of month 1 is always 先勝.

### 7.3 Festivals

Kyūreki-dated observances: the five sekku (人日 1/7, 上巳 3/3, 端午 5/5,
七夕 7/7, 重陽 9/9), 十五夜 8/15, 十三夜 9/13, and 旧正月 1/1. Festivals
that most of Japan now keeps by the Gregorian calendar are shown on their
Gregorian date and labelled as such: O-Bon on 15 August, the middle of the
月遅れ days 13–16 August that most regions keep (Japanese Wikipedia お盆;
Tokyo keeps 15 July, Okinawa and Amami the kyūreki date). The sekku are kept
today on the Gregorian day of the same number (NAOJ 暦Wiki 節句), but the
page names them on their kyūreki day, the calendar it shows
([sources/kyureki.md](sources/kyureki.md)).

### 7.4 Kanshi

- Day: (JD + 49) mod 60, with 0 = 甲子. Check: JD 2451545 (2000-01-01) → 54 = 戊午.
- Year: (Y − 4) mod 60 for the kyūreki year Y, which begins at 旧正月, not on
  1 January.

### 7.5 暦注 (almanac annotations)

Rules as tabulated in Japanese Wikipedia (十二直, 二十八宿, 九星, 選日,
暦注下段) after 岡田芳朗・阿久根末忠『現代こよみ読み解き事典』 (1993); the
雑節 as defined by the NAOJ. "節月" is the solar month that begins at each odd
solar term, 立春 = 寅月.

The names of the bands follow the sources: 中段 is the 十二直 alone (Japanese
Wikipedia 十二直; koyomi8.com 暦注の説明 その１), 暦注下段 and 選日 are separate
lists (Wikipedia 暦注下段 and 選日; koyomi8.com その２ and その３), and
`Senjitsu.band` records which one each day belongs to. The festival days of
deities that fall by the sexagenary cycle form a third group, 縁日 (Japanese
Wikipedia 縁日): 甲子 of Daikokuten, 己巳 of Benzaiten, 庚申 of Taishakuten and
Shōmen Kongō, 寅の日 of Bishamonten and 巳の日, the day behind 初巳. koyomi8
also counts 庚申 and 己巳 among the 選日; the app lists each day once, under
縁日.

| Annotation | Rule |
| --- | --- |
| 十二直 | 建 on the day whose branch is the solar month's; the first day of a solar month repeats the previous day's |
| 二十八宿 | an unbroken 28-day cycle, 角 on JD ≡ 17 (mod 28) — the weekday × branch table |
| 九星 of the day | 陽遁 from the 甲子 nearest 冬至 (一白, counting up), 陰遁 from the 甲子 nearest 夏至 (九紫, counting down); a solstice on 甲午 or 癸巳 takes the later 甲子; a 240-day half ends with the 60-day 九星 leap, switching at its 甲午 from 七赤 or 三碧 |
| 九星 of the year and month | 11 − (Y mod 9) with 立春 as the year boundary; the 節の九星 table |
| 暦注下段 | 天赦日, 大明日, 天恩日, 母倉日, 節徳日, 鬼宿日, 受死日, 十死日, 帰忌日, 血忌日, 天火日, 地火日, 往亡日, 歳下食, 重日, 復日 |
| 選日 | 一粒万倍日, 不成就日 (by kyūreki month and day), 三隣亡, 十方暮, 八専 and its 間日, 大犯土, 小犯土 and their 間日, 天一天上 |
| 縁日 | 寅の日, 巳の日, 己巳, 甲子, 庚申 |
| Personal (暦注下段) | 大禍日・狼藉日・滅門日, only in the solar month of one's birth-year branch, as the 簠簋内伝 gives them (巻上「三箇悪日」, NDL pid 1911335, img. 20; koyomi8 その３ has the same table); needs a birth date |
| 雑節 | 節分, 彼岸 (equinox ± 3 days), 社日 (the 戊 day nearest the equinox; a tie goes to the one nearer the equinox instant), 八十八夜, 入梅 (80°), 半夏生 (100°), 土用 (from 297°, 27°, 117°, 207°) and its Ox days, 二百十日, 二百二十日 |
| 恵方 | by the stem of the year from 立春 |

**九星気学 (optional, personal).** The 本命星 is the year star of the birth date,
the year reckoned from 立春. Its relation to the day star follows the stars'
elements (Japanese Wikipedia 九星, 九星の関係): 相生 when one element feeds the
other (木生火, 火生土, 土生金, 金生水, 水生木), 比和 when both share one, 相剋 when
one overcomes the other (木剋土, 土剋水, 水剋火, 火剋金, 金剋木). The article reads
the table as it stands for a person's years, months and days, 相生 and 比和 as
good and 相剋 as bad; its ※ marks apply to directions only and are not used. The
table prints 九紫火星 under 六白金星's 金剋木; the row's heading and the 七赤金星
row give 三碧木星 and 四緑木星 there, which the app follows. All 81 pairs are a
test vector.

**The lower band's own rules** (Japanese Wikipedia 暦注下段, read 2026-10-04;
[sources/kyureki.md](sources/kyureki.md)). No source ranks one kind of
annotation above another; within the lower band three rules act:

- 受死日 and 十死日 were printed alone in the lower band («他のものと重複して
  記載されず»). On either day the day's other 下段 annotations are set aside:
  they stay on the page, marked so, and do not count in the day in brief. The
  two never fall on one day. The 選日 and 縁日 are separate lists and stay.
- 歳下食 is a light bad day: with another annotation naming the day good it
  need not be kept, and with another bad day it weighs more. The app takes the
  good and bad days of the lower band and the 選日 (not the 縁日); with a bad
  one it is heavier, else with a good one it is set aside. The 1901 table's
  condition (no other bad day) agrees on the bad side.
- 重日 and 復日 double what is done, good or bad; their tone is mixed.

Left out, because their full rules cannot be recovered or need more than a date:
神吉日 (almanacs apply undocumented exclusions), 凶会日 (conflicting tables),
五墓日 (needs the 納音 of the birth year) and the hourly 時下食.

### 7.6 The 旧暦 election: the days for a work

The user picks a work and a span; the app gives the days the 旧暦
annotations name good for it (`KyurekiElection`, `KyurekiSpan` in
`core/.../texts/KyurekiElection.kt`; ROADMAP E5). It reads each day's
brief (§10.4) as the day's breakdown lists it, so the two never differ,
and adds no rule: the almanac gives no order, and the election gives none.

- **The works offered** are those some list of the brief names: the
  rokuyō's, the 十二直's, the 二十八宿's, and those of the 暦注下段, the 選日
  and the 縁日, the personal 三箇の悪日 among them, grouped by family (§10.7)
  in the summary line's order. "Everything" is left out, as in the Tibetan
  election (§5.14).
- **The span** runs from the shown day to the end of the first, third or
  twelfth 旧暦 month, counting the shown day's own as the first, and never
  past the end of 2100. With a birth date set the person's 三箇の悪日 are
  among the day's annotations, as on the page.
- **A day's side** for the work: the annotations whose lists name it good,
  and those that name it to avoid, after the lower band's own rules have
  set some aside (§7.5). An annotation that names everything names the work
  too: 天赦日's 万よし, 十死日's and the 三箇の悪日's "everything to avoid".
  Named only good, the day is good; only to avoid, to avoid; both ways,
  disputed, with both sides shown; by none, blank, not neutral.
- **The days given:** those named good, then apart those disputed, each in
  date order, with the annotations on each side; the days to avoid have no
  list. No day is "best": no published rule ranks one kind of annotation
  above another (§7.5), and the Qing 協紀辨方書's six grades are not used,
  since nothing ties them to the Japanese almanac ([sources/kyureki.md](sources/kyureki.md)).
- **No hours:** the rokuyō's times of day name no work, and the hourly
  時下食 is left out (§7.5).
- **Vector:** weddings from 1 October 2026 to the end of the 8th month,
  each annotation by the rules of §7.5: good on 8/22, 8/23, 8/28 and 8/29;
  disputed on 8/21 (天赦日 against 仏滅), 8/24, 8/26 (不成就日 among the
  avoid side) and 8/30 (`KyurekiElectionTest`). On 2020–2039 every day's
  sides are its breakdown's, and with a 子-year birth date every work
  offered is named on some day. For a 辰-year birth, 大禍日 falls on the 辰
  month's 丑 days, which are its 十死日, so the lower band always sets it
  aside; the other two 三箇の悪日 make the day to avoid or disputed for
  every work.

## 8. Texts

### 8.1 Policy

Every text the app shows, other than names and numbers computed in §5–§7,
has a published source; a test fails if any annotation lacks one. **The
app names no source in its readings** (owner, 2026-10-09: the reader of
the day needs its readings, not their sources): no sheet ends in source
lines, and no reading, note, list wording, detail or interface string
names a source's title, author, publisher, edition, chapter or page, or
says what "the text" says. A reading states what its source says as
fact ("Of the ten, it is among the three good"); a view other than the
main one is "another view", named only where the view is itself what
the row shows (Pukton's dates, the Vajra Treatise's days, the Paṇchen
Mön'drowa's black day). About & sources (§10.5) lists the sources of all
readings with their licences, and its Documentation section points to
the repository, where each reading's `Source` (§8.2) names its own, down
to the page, and `docs/sources/` gives the passages; comments in the
catalog keep the pages too. `CatalogTest` fails on a reading that names
a source, `TranslationsTest` on an interface string other than About's.
F-Droid
requires every asset to be legally licensed, so copyrighted wording is never
copied:

| Licence | Used for |
| --- | --- |
| Own English summary of the cited source, MPL-2.0 | copyrighted sources — Todan's こよみ博物館, NAOJ, Kotobank dictionaries, FPMT, Edition Rabten, Henning, Lotsawa House. Written as statements of fact and "good for / avoid" lists, not translations |
| CC BY-SA 4.0 | wording adapted from Japanese Wikipedia (選日, 暦注下段, 九星, 九星気学, 歳徳神, 庚申待) |

No text under a non-commercial or no-derivatives licence: F-Droid labels an
app containing one with the *Non-Free Assets* anti-feature.

**The White Beryl counts above every other source** (decided by the owner,
2026-10-08): where another source disagrees with it, WB decides; where WB
is silent, the other may stand, named as its own. **Witnesses are not
sources:** a text read only to check a reading of WB, the Bon *snang srid
me long* among them ([sources/README.md](sources/README.md), SN), is never
an annotation's `Source`, and nothing that only it states is shown. No Bon
text is a source of the app.

**Every Tibetan word is named so that it can be found** (owner,
2026-10-08 for titles, 2026-10-09 for every word): its English name
first, then its Tibetan script and its Wylie in brackets, "the la, the
life-spirit (བླ, bla)", "Reciting the Names of Mañjuśrī (མཚན་བརྗོད,
mtshan brjod)". This holds for every Tibetan word the app shows, a
term or the name of a deity, spirit, person, print, publisher or text,
and wherever it shows it: in readings, notes, list wordings and source
lines, in the interface's rows, labels, balloons, sheets and diagrams,
and in the store listing. Neither the script nor the Wylie ever stands
without the other or without the name, and a Tibetan word written in
phonetics (Losar, tsok) is a Tibetan word too. The English name is the
word's meaning ("feast offering (ཚོགས, tshogs)"); a proper name, and a
class of spirits English has no word for, keeps the form English
writers give it, a loan English has taken (lama, torma, tsampa) or else
its pronunciation in THL phonetics (Pehar, Ömbarma, tsen); a month goes
by its Sanskrit name (Bhādrapada), as English writes it. In Russian the
name is Russian, a proper name in Russian transcription. Within one text
(a reading, a note, a string, a `Source`) the bracket follows the
first mention of a word, and later mentions give the name alone. A term
the app gives only in English (the earth lord, the thread-cross) shows
no Tibetan and needs no bracket; nor do Sanskrit words (nāga, karaṇa),
places English spells as its own (Tibet, Lhasa), the city list
(GeoNames) and organisations (Lotsawa House). For a text, a reading gives the
short title a text is known by, as its source gives it; the `Source`
gives the full title, with the Wylie as BDRC catalogues it, and, for a
canonical text, its Tohoku number and 84000 page; About & sources names
the White Beryl by its short title, *bai DUr dkar po*, and the `Source`
by its full one, *Phug lugs rtsis kyi legs bshad mkhas pa'i mgul rgyan
bai DUr dkar po'i do shal*. Several texts in one
bracket stand apart by semicolons. A Japanese book gets its English name,
then its kanji and Hepburn reading. A text named only by an abbreviation
is identified from the source itself where it gives the full title, else
from a catalogue that uses the same abbreviation (BDRC, 84000), and the
`Source` holding all of them links where they can be read.
`CatalogTest` fails on Tibetan script outside such a bracket, on a bracket
without a name before it, on a Wylie whose script is not the one the
converter makes of it, on a translation whose brackets are not English's, and on a known
Tibetan word left in Wylie alone (every Wylie of the engines' terms, and
every Wylie any bracket of the catalog, the interface or the store listing
gives); `TranslationsTest` checks the interface strings and the store
listing, changelogs included, the same way. Where the White Beryl's
name for a yoga or karaṇa differs from the almanacs', the sheet gives it
as "Also called"; other sheets give the term's Tibetan name as
"Tibetan". The
code writes a term as `Ewts.named(english, wylie)`, and the app sets the
Tibetan runs in its Tibetan font (`withTibetan`).

### 8.2 Catalog

`core/.../texts/Texts.kt` holds every reading's structure as Kotlin data: a
`Reading` with the keys of its "good for" and "avoid" wordings, a `Source`
(title, publisher, URL) and a `License`, and optionally further sources
(`also`) for parts of the summary under the same licence.

The text itself is in a catalog per language, `core/src/main/resources/texts/`:
`texts.properties` is English, the source language, and a translation is
`texts_<language>.properties` (`_ru`, `_zh_Hant`), UTF-8, read by `Catalog.kt`.
Keys: `<Enum>.<NAME>` for the English name of a term (`Choku.TATSU`), with
`.<field>` for a second one (`Weekday.SUNDAY.planet`, `Element.FIRE.inText`
for running text); `reading.<Enum>.<NAME>` for a reading's summary;
`wording.<key>` for each wording of the lists. Readings that share a sentence
keep it once, as a pattern whose `{0}` takes the entry (`reading.KyuSei`,
`reading.Haircut`). A translation may be partial, since Weblate commits
only reviewed strings ([weblate.md](weblate.md)): a key it lacks, or leaves
empty, falls back to English, in the catalog as in `strings.xml`.
`CatalogTest` and the app's `TranslationsTest` fail when a translation has a
key English lacks, other placeholders, or a `locale_tag` in another
language; a language offered in the menu (§10.5) must also be complete.
Tibetan and Japanese terms, Wylie, phonetics, romaji and the sources'
titles are not in the catalog: they are not translated. The catalog follows the
language the app's own resources resolved to (§10.1), not the phone's.

Russian (`texts_ru.properties`, `values-ru/strings.xml`) is translated by the
project from the English summaries and checked against the cited sources.
The terms of the elemental divination follow the Russian edition of Berzin's
*Details of Tibetan Astrology* on Study Buddhism: жизненная сила, тело,
могущество, конь ветра for the four aspects, мать, ребёнок, друг, враг and
совпадение for the relations, камни for the pebbles. A translated reading
keeps its source and licence; its licence label says it is a translation
(MPL-2.0 for the app's own summaries, CC BY-SA 4.0 with attribution for
wording adapted from Japanese Wikipedia).

In every language the names of the weekday-and-mansion reckonings (yogas,
karaṇas, the element pairs, the named combinations, the special days and
the burning date) follow the White Beryl's own words (§8.1), not the
Sanskrit behind them: two terms WB writes differently never share a name
(*dngos grub* attainment, достижение; *grub* accomplishment, свершение;
*lang tsho* youth, юность; *gzhon* young one, юноша; *'khon 'dzin*
enmity, раздор; *mi mthun* discord, разлад), and one word of WB's keeps
one name wherever it stands (*'phel* and the fire pair's *'phel 'gyur*
growth, возрастание, so "increasing" and приумножение stay the increasing
rites'; *sreg pa*, *bsreg sbyor* and *bsreg tshes* burning, сожжение,
which WB spells with and without its *b*; *mi 'phrod* incompatibility,
несовместимость; *dga' ba* joy, радость; *bdud rtsi* nectar, нектар).
`CatalogTest` checks both rules in each language.

The "good for" and "avoid" lists keep the source's wording. `Activities.kt`
maps every wording key to the act it names (`Activity`: "weddings", "marriage"
and "taking a bride" are one act), so annotations can be compared in any
language; only wordings that name the same act share an entry, and the
rokuyō's hours ("the morning", "noon") map to a `DayTime` instead. A test
fails on a wording with no entry and on an entry no wording uses.

### 8.3 Sources in use

| Set | Source |
| --- | --- |
| 六曜, 十二直, 二十八宿 | こよみ博物館, 株式会社トーダン (an almanac publisher) |
| 選日 and 暦注下段, 九星, 恵方, 庚申 | Japanese Wikipedia; Kotobank (精選版 日本国語大辞典, 日本大百科全書) for 寅の日, 巳の日, 甲子; 『独占易学全書』 (1901, NDL) for 歳下食, a second witness to Wikipedia's table |
| 九星気学 relations and school | Japanese Wikipedia 九星 (九星の関係) and 九星気学 |
| 縁日 as a group | Japanese Wikipedia 縁日 |
| Colours of the sme ba | Berzin, *Details of Tibetan Astrology 4* (Study Buddhism) |
| Four aspects, the pebbles of the day, month and year | The White Beryl (ff. 156a/b, 158a, 248b–254a, 295b–299a) with Lo chen Dharmaśrī's *Moonbeams* (ff. 5b–6b, 28a/b, 31b–32a), read in Gyurme Dorje's edition (2001) |
| The year of age: its signs, pebbles, sectors, obstacles and readings | The White Beryl, Beijing 1996, vol. 1, pp. 255–258 and 380–416 (BDRC MW2CZ8040), with the *Moonbeams* in the *Bod kyi rtsis rig kun 'dus chen mo*, vol. 3, pp. 493, 496 and 507 (BDRC MW28845); Gyurme Dorje's Table 2.11 and chart 6.2 as test vectors |
| Tibetan pronunciation | THL Simplified Phonetic Transcription of Standard Tibetan, Germano and Tournadre, 2003 (thlib.org; archived by the Wayback Machine at `thlib.org/global/php/essay_reader.php?url=/thl/phonetics/s/b1`–`b12`) |
| Tibetan spellings | The White Beryl, Sde srid Sangs rgyas rgya mtsho, Derge blocks reprinted Dehra Dun 1978 (BDRC W1KG12714) |
| Band names: 中段, 暦注下段, 選日 | Japanese Wikipedia 十二直, 暦注, 暦注下段, 選日; koyomi8.com 暦注の説明; こよみ博物館「暦注」 |
| 雑節, 節句, 十三夜 | NAOJ 暦Wiki |
| O-Bon by the Gregorian date | Japanese Wikipedia お盆 |
| Hair-cutting days | *From the Sutra Chapter of Bodhisattva's Hair*, tr. Lama Zopa Rinpoche, FPMT 2008 |
| Washing the hair by date | The White Beryl, vol. 2, p. 404 (BDRC MW2CZ8040) |
| Element pairs, observances, festivals, personal days | Edition Rabten, *Tibetan Calendar 2026*; Henning's archive and symbolic details |
| Personal mansions | The White Beryl, vol. 2, p. 330 (BDRC MW2CZ8040), with the Sakya *'bras rtsis rab gsal nor bu'i me long*, p. 64 (BDRC MW29978_8B19DD) |
| Own days by the birth date | The White Beryl, vol. 2, pp. 312, 330, 337–338 and 345–346 (BDRC MW2CZ8040); the birth mansion after Phug pa Lhun grub rgya mtsho's commentary on the *dbyangs 'char*, ch. 3 (BDRC MW1NLM5184) |
| Lunar mansions, activity lists | Henning, *Horary and electional astrology of the five components*, after the White Beryl, the *Treasury of Jewels* and the *'bras rtsis bai dkar dgongs don kun phan me long*; his doubled mansions read on that print (BDRC W4CZ65561); the White Beryl's seven classes of mansions, vol. 2, pp. 328–329, and its chapter 34, the works one by one, pp. 378–428 (BDRC MW2CZ8040) |
| Lunar dates, weekdays, yogas, karaṇas | The White Beryl, ch. 33, Beijing 1996, vol. 2, pp. 297–304, 308–312 and 347–351 (BDRC MW2CZ8040) |
| Trigram (the eight goddesses of the date) | The White Beryl, ch. 25, Beijing 1996, vol. 1, pp. 449–450 (BDRC MW2CZ8040) |
| Combinations of weekday and mansion, special days | The White Beryl, Beijing 1996, vol. 2, pp. 331–337, 341 and 342 (the *Rdo rje gtsug lag*'s special days), with the table in vol. 1, pp. 148–149, and the almanac verse of vol. 1, p. 177, a second witness to seven kinds of special day (BDRC MW2CZ8040) |
| Rāhu's course | The White Beryl, Beijing 1996, vol. 2, pp. 236–238 (BDRC MW2CZ8040); the *kun phan me long*'s chart of the general course, img. 78 (BDRC MW4CZ65561) |
| Earth lords of the date's animal | The White Beryl, Beijing 1996, vol. 2, pp. 224–226 (BDRC MW2CZ8040) |
| The great black day, the meeting of the nine bad | The White Beryl, Beijing 1996, vol. 2, p. 226 (BDRC MW2CZ8040) |
| The earth lords that move by date, Rāhu's among them | The White Beryl, Beijing 1996, vol. 2, pp. 226–235, with the results pp. 364 and 368–369, and the model almanac, vol. 1, pp. 154–171 (BDRC MW2CZ8040) |
| Weighing the day | *'Bras rtsis bai dkar dgongs don kun phan me long*, img. 13–14 (BDRC W4CZ65561), with the White Beryl, vol. 2, pp. 333 and 337 |
| Tenth day | Jigme Lingpa, tr. Rigpa Translations 2013, Lotsawa House |

The 2033 resolution: 暦文協, 2015-08-28 (§7.1). The birth-year rule of the
三箇の悪日: the 簠簋内伝 on the NDL's scan, with koyomi8 (§7.5).

## 9. Test vectors

Stored as tab-separated files under `core/src/test/resources/vectors/`, one
file per source, each with the source citation in `#` header lines. `cli` and
`app` never read them.

The vector files are third-party tables, so they are kept locally and listed in
`.gitignore`: the public repository does not carry them. A test whose file is
missing is skipped, not failed, so a fresh clone still builds and passes.

| File | Content | Source |
| --- | --- | --- |
| `tibetan-losar.tsv` | Losar dates 1927–2046 and year names (120 years) | Janson Table 1 |
| *(in `TibetanCalendarTest`)* | 2007-12-31 = day 23, month 11, Fire–Pig; 2014-01-08 = Wednesday, day 8, month 11, Water–Snake; 2022-02-13 = Sunday, day 12, month 12, Iron–Ox; the epoch dates of Remark 16. Janson's title page calls 2007-12-31 a Sunday, but it was a Monday and his (9.1) gives Monday, so that weekday is not a vector | Janson, title page and §7 |
| *(in `TibetanCalendarTest`)* | The three dates of §5.5 | Janson Remark 14 |
| `henning-phugpa.tsv` | Every day of 2000, 2013, 2024–2027 (2,245 days): date, repetition, weekday, mansion, element pair, yoga, karaṇa, lunar-day cycles, Chinese mansion, festivals, and the day's figures: the true weekday (the date's end), the moon at daybreak, the true sun and the yoga's sum, to the chu srang; `DayTimesTest` works WB's rules for the times within the day (§5.8) on them | Henning's Phugpa archive, extracted by `tools/extract_henning.py`; compared with a₂ = 3781/105840, which Henning uses |
| `naoj-2026-2027.tsv` | Solar terms, new moons and 雑節 with JST times, 2026–2027 | NAOJ 暦要項 |
| `koyomi8-2026-2027.tsv` | Every day of 2026–2027: 干支, 十二直, 二十八宿, 旧暦 date, 六曜, 九星, 選日 | こよみのページ (koyomi8.com), an independent computation |
| `crosscheck-new-moons.tsv`, `crosscheck-solar-terms.tsv` | New moons and 15° solar terms 1900–2100, UTC | Computed with PyEphem 4.2.1 — a cross-check, not a published table. Worst differences: 34 s and 36 s |
| `gyurme-dorje-forces.tsv` | Vitality, body, destiny and luck of all 60 years, and the relationship of destiny to vitality (kha-yan, khong-nong, …) | Gyurme Dorje (2001), charts to Plates 3–8, pp. 70–85, extracted from the archive.org OCR by `tools/extract_gyurme_dorje.py`. One body and three relationship rows are lost in the OCR; year 57 prints destiny wood where its own relationship row and every other year give the year's element, iron |
| *(in `ForcesTest`)* | Table 2.5, the destiny elements of the twelve months for each yearly element (p. 91); chart 8.1, the month, day and hour pebbles of the health divination (p. 296); Table 2.7, the hours' destiny elements (p. 91) | Gyurme Dorje (2001) |
| *(in `ForcesTest`)* | Chart 6.2: the four aspects of a fire dragon, an earth tiger and an iron mouse year, and all 20 elemental cells of the obstacle-year chart | Gyurme Dorje (2001), p. 228 |
| *(in `YearOfLifeTest`)* | The natal mewa of the captions to the 180-year charts; Table 2.11, the mewa of each age 1–90 for every natal mewa, male and female years (p. 103); chart 6.2, the 23rd year of a man born in a fire dragon year: progressed sign iron mouse, trigram zin, mewa 7 and all 24 decisive pebbles, the sectors and the hour included (p. 229) | Gyurme Dorje (2001); WB vol. 1, p. 255 (1687's mewa) |
| *(in `RekichuTest`)* | The 九星 leap positions 1905–2100; the 二十八宿 table for every day of 1900–2100 | Japanese Wikipedia 九星, 二十八宿 |
| *(in `KyurekiElectionTest`)* | Weddings, 1–10 October 2026 (旧暦 8/21–8/30): the days named good, the disputed days and their annotations | The rules of §7.5 (天赦日, 不成就日, the rokuyō), each checked in `RekichuTest` |
| *(in `ElectionTest`)* | October 2026, the good days ranked for haircuts, weddings and setting out, and the days to avoid that the site names; the days of 1 June 1976's enemy weekday and death mansion not offered | The app's own weighing (§5.14), witnessed by the tibetastromed.ru election (sources/tibetastromed.md) |

## 10. User interface

Settled in the design review of 2026-09-28. A clickable mockup of these
decisions, with calculated data for 20 Sep – 15 Nov 2026, is on the design
canvas "Zanshin Calendar — basic design".

### 10.1 Principles

- **UX comes first.** The console look of the original draft is dropped where
  it hurts reading or touch; nothing in the UI has to look like a terminal.
- **Dark theme only** in 1.0.
- **One calendar at a time.** The screen shows either the Tibetan or the 旧暦
  view, never both.
- **Details in balloons.** Secondary facts open in a balloon (popover) when the
  element they belong to is tapped, and close on tapping outside it.
- **Every term is translated.** Each kanji opens a balloon with its English
  and reading; these carry no dotted underline. A Tibetan term shows as its
  English name with its script and Wylie in brackets (§8.1), in rows and
  labels as in running text; tapping it opens a balloon with how it is
  said, and screen readers read the pronunciation (§10.3).
- **Readings on demand.** Annotations are listed as rows with a lucky/unlucky
  mark; tapping one opens a sheet with its reading, "good for" and "avoid";
  the sources are listed in About & sources, not on the sheet (§8.1).
- **Text out of the code.** Interface text is in `res/values/strings.xml`, the
  engines' and readings' text in the catalog (§8.2). Dates, ordinals and
  numbers follow the language the app's resources resolved to, named by the
  `locale_tag` string: date formats are translatable `DateTimeFormatter`
  patterns (`pattern_header_date`: "EEE d MMM yyyy"), ordinals come from ICU
  ("8th month"). A phone set to a language the app lacks shows English
  throughout, dates included. Coordinates stay in the international form
  (35.02°N 135.75°E), and a saved place keeps the label it was saved with.

### 10.2 Day screen, common to both calendars

- Opens on today, in the **calendar last viewed**.
- **Swipe left/right** moves one day.
- **Switching calendars:** two entries at the top of the side menu, and a tap
  on the calendar's name in the header switches directly.
- **Switching people:** once two or more people are saved (§10.5), the
  header holds a ring with the chosen person's initials (the person glyph
  for a name left blank or for no one), between the date and the calendar's
  name; a tap opens the list of people, and a tap there switches.
- **Tapping the Gregorian date** in the header opens the date picker, limited
  to 1900–2100. Under each day it shows the day number in the calendar being
  viewed, a dot on holidays and festivals and, in the 旧暦 view, the day marks
  of §10.4, with a legend whose kanji translate on tap.
- Festivals and holidays carry a line glyph drawn for this app.
- **Local sky line** at the bottom of both calendars (§10.5).

### 10.3 Tibetan view

- **Headline:** the day number, with the month under it. On a holiday, the
  holiday's name and glyph take the headline and the date moves under it.
- **Month:** number plus Tibetan name — "4th month · sa ga". Tapping it opens
  a balloon with the other names: Sanskrit lunar mansion, animal name
  (Janson Table 4), and its two seasons, each labelled, as WB's model
  almanac gives them (vol. 1, pp. 154–171): the Kālacakra one (the 1st
  month early spring) and the Chinese reckoning's (the 11th early spring,
  the 1st late spring); then the month's element and its
  vitality and body (§5.9), with a birth date set each with its pebbles
  and relation to yours, as in the year's balloon.
- **Repeated day:** each of the two days carries a small tag, "first of two" /
  "second of two".
- **Skipped day:** the day after the gap carries a note, "day 24 is
  omitted". A holiday moved back by a skipped date says "moved from day N".
- **Leap month:** "Leap 2nd month".
- **Year:** element and animal, "Fire Horse"; the royal year (2153)
  and rabjung cycle ("17th cycle, year 40") in the year's balloon. With a
  person chosen, the balloon ends in "Your 51st year of age →", which opens
  the **year sheet** (§5.9.1): the person's age and signs; the signs of
  the year (the mewa of the year of age with the natal one, and with a
  gender the trigram and the progressed sign with its place); the pebbles
  as a grid, a row for each basic sign and a column for each aspect as
  chart 6.2 lays them, the white and black counted under it, then each
  aspect's predictive pebble (and all four alike); the hour of reckoning
  named; the four sectors of growth and decline; and the year's
  obstacles, or a line saying there are none. Every row and cell opens
  its reading with its workings. Without a gender a note asks for it in
  People. The sheet changes once a year, so it stays behind the tap
  (the owner's rule that the page repeats nothing).
- **Midnight to dawn:** "today" is the civil date, as §4 says; the
  date carries a small "from dawn".
- **No day line** (decided by the owner, 2026-10-09; ROADMAP T3): the
  weekday and planet are the Almanac's weekday row, which carries the
  Tibetan name in its sheet, and the 60-day cycle's element and animal,
  weighed nowhere (§5.12), are the "Day sign" row of the Lunar day
  section.
- **Hours** (ROADMAP U6, built 2026-10-09): under the year line and above
  the brief, since the combination period outweighs every factor of the
  day while it lasts (§5.12, §5.13): a strip of the twelve two-hour
  periods from 05:00, each coloured by the White Beryl's verdict on the
  sign rising in it, the clock hour under every third and, on today's
  page, the present moment as a mark; under it, today, the present hour
  with its sign and verdict ("now · Horse hour 11:00–13:00 · Sagittarius
  rises · to be accomplished"), and the combination periods that run
  against the day's tone (ROADMAP U5, built 2026-10-09;
  `DaySummary.hoursAgainst`): on an unlucky day those to be accomplished
  ("good hours 09:00–11:00 13:00–17:00 …"), on a lucky day those to be
  avoided, consecutive hours joined; nothing is weighed anew, and on the
  person's enemy weekday or death mansion there are none, since WB places
  no hour above the person's day (§5.12). A tap on the strip or on a time
  opens the hours panel at that hour, a tap on the row at the present one;
  screen readers get the present hour and every run's times, with an
  action for each (`HoursRow`). Until U6 the hours against the tone stood
  on the In brief row, and the panel opened from a clock icon on the
  Almanac's heading.
- **In brief:** under the hours, one row of glyphs, however many works
  the day names: the families of the heaviest works good and of the
  heaviest to avoid, weighed as §5.12 says (§10.7), with the day's tone and, in a few words, what decided it
  ("a lucky day · by the combination", "an unlucky day · by Tuesday",
  the factor named as the weighing found it, §5.12); after the glyphs, how many works
  the day names good and to avoid ("good 53", "avoid 36"), since the row
  cannot show the proportion and on 11 % of days the lists run against
  the tone (§5.12; decided by the owner, 2026-10-07); it opens the tone with its
  reason and, under it, the rule in a sentence (ROADMAP U6): that each
  work goes to the strongest factor that names it, with how many works
  still stand against the tone ("so 42 works are still good today"), and
  that within its hour the combination period outweighs the day, neither
  said on the person's enemy weekday or death mansion; then the "By the
  hour" block (below), before the works, and the works grouped by the voices
  that carry them (§5.12), a work opening a balloon with its side and
  each voice with its kind ("weekday · Sunday") and "Choose a day for it",
  which opens the election for that work from the shown day (§10.8);
  nothing outweighed is shown. With a birth date set, a "For
  you" block lists the day's personal day, own days by the birth date and
  personal mansions (§5.8) with their dots, each over the factor it is
  ("Luck day · Mother weekday" over "Sunday", "Birth mansion" over
  "Ārdrā"), the roles of the weekday as one line, and one sentence that
  they are shown, not weighed (§5.12). On the person's enemy weekday or
  death mansion (§5.12) the row says "every work to avoid, for you" under
  the day's tone and gives only the count to avoid; the sheet says it
  under the tone, naming the day ("Enemy weekday, Thursday"), lists no
  good works, and its "For you" block gives WB's words for each (vol. 2,
  p. 338) and why the good list is gone. Before the works (ROADMAP U6;
  after them until then), a "By the hour" block (§5.13) gives the clock
  times of the combination periods to be accomplished and to be avoided,
  consecutive hours of one verdict joined ("09:00–13:00"), and the nectar
  periods, each time opening the hours panel at its hour, with one
  sentence that within its hour the combination period outweighs every
  factor of the day (WB vol. 2, p. 376) and that the nectar periods are not
  weighed (`DayHours`).
- **Works** (ROADMAP U6, built 2026-10-09): between the brief and the
  Almanac, the verdicts on single works, which are results of the
  weighing and not voices in it: the haircut, weighed as §5.12 weighs every work: its
  dot is the side the brief gives haircuts and its subtitle names the
  factor that decides ("avoid · by Tuesday"; KP box 52a names every weekday,
  so on every day of 2000–2049 it is the weekday). Its sheet says so,
  shows the days of the Tibetan month with the side the weighing gives
  haircuts on each, and gives FPMT's day for the date as one of the date's
  lists, marked outweighed on the days it is (decided by the owner,
  2026-10-06). It is FPMT's, not the White Beryl's: WB has no haircut
  results by date, and its thirty hair dates (p. 404) are for washing the
  hair, shown in the lunar date's sheet as a "Washing the hair" detail
  (ROADMAP T2.15, [sources/hair-dates.md](sources/hair-dates.md)). Until
  U6 the haircut was the Almanac's last row, the one row whose dot was a
  verdict among rows whose dots are the voices' tones.
- **Almanac:** what is weighed (§5.12, *Order on the page*). The monthly
  observance; the festival is the headline and opens its reading from
  there, so the Almanac does not repeat it. Then the day's voices in the
  rank of §5.12, one row each: the combination, Rāhu, weekday (§5.11),
  lunar mansion (§5.10, no dot: it has no tone of its own), special days,
  lunar date, karaṇa and yoga (§5.11), the one that decided the day's tone
  with a small "decides" mark outlined in its tone. The combination is
  one row, "Combination" (ROADMAP U1): the dots of the named combination
  and the element pair side by side, the two elements' glyphs, and a
  subtitle naming both parts and whether they agree ("Pestle ·
  Wind–Fire: the parts disagree, no tone", "Friend · Earth–Earth: both
  lucky"); its sheet says it is one voice and when it decides, then gives
  each reading in turn, the named combination with the table of the 28
  and the element pair with the grid of the ten. The section's heading
  names what decides the day ("Almanac · by the combination", "by
  Tuesday"; ROADMAP U6), and only the deciding voice's dot is solid: every
  other row's dot is an outline, "not deciding today" to a screen reader,
  and its sheet says under the gloss that its tone does not decide the
  day and that its lists still decide the works they name that no
  stronger factor names (`Annotation.outline`), so that the column of
  dots reads as the voices' own tones and not as a count of sides. The weekday, mansion, karaṇa and yoga rows carry their
  Tibetan names in their sheets, under the gloss ("Tibetan" with the term
  named as §8.1 writes it, tapping it for the phonetics), so that no section
  repeats them (decided by the owner, 2026-10-07: the five components
  section, which gave the same four terms again, was folded in). After the
  Almanac, Your day and Also today, the Lunar day section: first the
  day sign, the 60-day cycle's element and animal ("Earth Bird"), the
  element's glyph in its colour (ROADMAP T3: the hues of the sme ba
  boxes that stand for it, Berzin 4, iron white, water black or blue,
  wood green, earth yellow, fire red; water drawn blue and the green
  and red lightened, so that a thin glyph reads on the dark page), its
  balloon giving element, animal and gender; then the lunar-day cycles
  as tappable terms, the date's
  animal (*nyi ma*) opening its earth lords and the
  trigram its goddess's reading (§5.11). Several special days are one row,
  "Special days", their dots side by side, as they are one voice in the
  weighing; its sheet gives each reading in turn. The burning date is one
  of them, its subtitle naming the weekday and the date that make it. Rāhu's courses by date and by month
  are one row in the same way. The Lunar day section names the 60-day
  cycle's animal the "day sign", and the date's animal
  and the date's sme ba, and the *bla mkhyen* row says its sme ba is
  counted from the solstice, so that the two animals and the two sme ba
  are not taken for one. On the day a sun's term falls, a last row
  names it with its time ("Middle term of month 8 · 01:00", "Sun enters
  Libra · 19:24"), its sheet the term's reading, its Tibetan name and its
  measure (§5.8).
- **Times within the day** (§5.8): the mansion row's subtitle adds a
  second mansion that comes by daylight ("then Uttaraphalgunī from
  16:30"), the yoga row a skipped yoga ("and Vajra 09:01–04:06, skipped"),
  the karaṇa row Viṣṭi's span ("Viṣṭi 08:33–21:16", the span alone when
  the day's karaṇa is Viṣṭi), each with a detail in the sheet. A time
  before the day's daybreak at 05:00 reads "the day before", one from the
  next daybreak on "the next day". A burning date that begins in
  daylight joins the special days' row (§5.11).
- **Tibetan script:** every Tibetan term (month, mansion, yoga, karaṇa,
  weekday, trigram) is written as §8.1 names it, its English name, then its
  Tibetan script, converted at run time from the Wylie of the sources
  (`Ewts.kt`), and the Wylie, and sits on the baseline of its label. Its
  balloon adds the pronunciation in the THL
  Simplified Phonetic Transcription of Standard Tibetan (Germano and
  Tournadre, 2003; `Thl.kt`: the general principle, special rules 1–13, the
  exceptions and the word boundaries, tested on the document's own 86
  examples). The karaṇa Viṣṭi, a Sanskrit loan, is spelled as the White
  Beryl prints it, བིཥྚི (pp. 349–351), written in EWTS with an explicit stack
  (biSh+Ti); THL gives no rule for a Sanskrit stack, so it has no
  pronunciation. The spellings were checked against the OCR of the White
  Beryl (BDRC W1KG12714): 95 of 106 terms appear there verbatim; til brdung
  and mi 'phrod follow its spelling rather than Henning's (til rdung, mi
  phrod); snron, snrubs and six yogas (rnam sel, tshe dang ldan pa, shin tu
  'grams, yongs bsnun, mchog can, yongs 'joms) were not in the OCR and remain
  as the almanacs give them. Read on the scans, the White Beryl names five
  of those yogas otherwise (sel ba, tshe ldan, rab stongs, rma chen, dpa'
  bo) and does print yongs 'joms; the Sakya *nor bu'i me long* has yongs su
  bsnun beside it ([yogas.md](sources/yogas.md)). The font is Noto Serif Tibetan (OFL), one weight,
  subset to the Tibetan block with its shaping features.
- **Year balloon** also lists the year's four aspects: vitality, body,
  destiny and luck with their elements (§5.9); with a birth date set, each
  with its pebbles and relation to yours ("Fire · ○○○ mother"). The year's
  contrast lives there rather than on the page, where it would repeat for
  a whole year; the month's lives in the month's balloon for the same
  reason.
- **Your day:** with a birth date set, a section after the Almanac holds
  what the day is for the person, shown and not weighed (§5.12; ROADMAP
  U3). First the weekday's roles for the person (§5.8: the personal day of
  the birth animal, "for your birth year"; the birth weekday, "for your
  birth date"; the element's weekday, "for the life force of your birth
  year, earth"), one row with each one's dot where there are two or more,
  titled "Your weekday" with their names under it, its sheet giving each
  reading in turn; the personal mansions (on the days the mansion is one
  of one's six) and the birth mansion. Then the vitality and body of the
  birth year against those of the lunar date (§5.9), one row each: the aspect, its pebbles, the relation and both
  elements ("Body ××, enemy: the day's water to your fire"). A row opens
  the relation's reading, then how it was worked out: the lunar date's and
  month's signs, the year's, how the month's and date's elements are
  counted, the day's element, yours and the relation.
  The tone dot is lucky for white pebbles only, unlucky for black only,
  mixed for both.
- **Also today:** after Your day, what the day holds that the weighing
  does not count: the *bla mkhyen*'s direction (§5.11), with its compass,
  and on its dates the great black day (§5.11), its title the meeting of
  the nine bad on 11/7; then the earth lords' courses (§5.13), one row
  whose subtitle names each course on the day with its event ("The nāgas:
  turning", "The sky door: war's door"), the sheet giving each course's
  reading in turn.
  The nectar periods have no row on the page: the brief's "By the hour"
  and the hours panel give them (§5.13).
- **Hours of the day:** a clock icon on the Almanac header opens the
  hours: a 24-hour dial, midnight at the top, with the twelve two-hour
  periods named by their animals. Its inner ring is the combination period
  (§5.13), each hour coloured by the White Beryl's verdict on its rising
  sign, with dots on Jupiter's nectar periods; with a birth date two outer
  rings show the pebbles of the hour's vitality and body against the birth
  year's (§5.9). On today's page a hand marks the present moment and the
  current hour is selected. Tapping an hour, or stepping with the arrows
  beside its sign, shows its rows: the combination period, without the
  works its sign's own hours decide the other way, the works' own hours
  ("The works' own hours", the works WB's chapter 34 names good or to
  avoid while the sign rises, §5.13) where the chapter names any, a
  nectar period if one falls in it, Viṣṭi while its span lasts, the
  black hour on its hours, the earth lords of the hour on the hour's
  animal's place, the hour's *bla mkhyen* and *sa rgyal* on theirs, the
  *sa rgyal* the other way in its hour, the hidden earth lord and the sky
  dog (§5.13), and with a birth date vitality and body; each
  opens its reading as the day's rows do. The ring keeps the period's
  verdict; an unlucky arc inside the rings marks Viṣṭi's span (§5.8).
- **Sme ba in its colour:** the lunar day's number carries the square of the
  nine numbers, each box in the colour it is printed in and today's marked
  (Berzin, *Details of Tibetan Astrology 4*: colours, and the arrangement with
  9 at the top, south, and 1 at the bottom, north). No whole day is coloured.

### 10.4 旧暦 view

- **Date:** in English — the day number as the headline, "8th month" under it
  ("8th month, day 18" for screen readers). Tapping the month opens a balloon
  with its traditional name (葉月 Hazuki).
- **Rokuyō:** as prominent as the date, since Japanese wall
  calendars lead with it.
- **Leap month:** "Leap 6th month".
- **Day marks:** the two days the almanac itself marks (Japanese Wikipedia
  暦注下段) get a line under the rokuyō: 受死日, printed as a black dot in the
  lower band and hence 黒日, shows a filled dot and "black day — the worst of
  all"; 天赦日, the only day with the note 万よし, shows a ring and "heaven's
  pardon — good for all". The line opens the day's reading; the same marks
  appear in the date picker. On a day with both, the black day wins, since on
  it no other annotation need be read. The marks are drawn, not set in type.
  No whole day is coloured by luck: none is recorded for the historical
  almanac, whose red writing (朱書き) in the 具注暦 marks the 二十八宿 and 七曜,
  and on modern Japanese calendars a red date means a Sunday or holiday. The
  lucky and unlucky colours of the rows are an app convention.
- **In brief:** a line under the rokuyō (and under the day mark) shows the
  glyphs of the activity families the day's annotations name good and to
  avoid (§10.7), a family in the mixed colour when one of its activities is
  named both ways; screen readers hear the counts. It opens the breakdown. The breakdown lists each activity
  with the annotations that name it, the annotations by tone, the day mark, and
  for the owner the 三箇の悪日 and the 九星気学 relation, and the annotations
  set aside by the lower band's rules (§7.5) under the one that sets them
  aside. It is a listing, never a verdict: no published rule ranks one kind of
  annotation above another (下段 over 中段, 二十八宿 over 十二直), so the kinds
  are not weighed, and where annotations disagree both sides are shown. A row
  set aside says so ("set aside"), and its reading names what set it aside; a
  heavier 歳下食 names the bad days that make it so.
- **Solar term:** always shown — "秋分 · until 8 Oct"; highlighted
  on the day a new one begins.
- **Moon phase:** a phase glyph next to the date.
- **暦注:** in the order of a printed almanac — the middle band (中段, the
  十二直 alone), the 二十八宿, the lower band (暦注下段), the selected days
  (選日), the deity days (縁日), the 雑節, then 干支 of day and year, the
  solar month, month and year stars, the day star and the 恵方. The day star sits with the other 九星: it is
  a personal reading, not a day quality, and entered almanacs only after Meiji
  (Todan こよみ博物館「暦注」).
- **九星気学 row:** with the nine-star reading on, the relation of the owner's
  本命星 to the day star (§7.5) follows the day star, as 相生, 比和 or 相剋 with
  its tone and cycle ("metal feeds water"). Its reading says the reading belongs
  to 九星気学, gathered as 気学 in 1909, not to the historical almanac.

### 10.5 Menu, settings, location

- A **burger button, top left**, opens a menu that slides out from the left
  as a full-height column, scrolling when it does not fit, in groups under
  small capital labels, a line between groups: **Calendar** (the two
  calendars and Choose a day), **Practice** (Meditation, §10.9),
  **Settings** (location, people, nine-star reading, language), and
  About & sources alone at the foot. About & sources lists the
  calendars' methods, the readings' sources and licences, and, under
  Documentation, says that the sheets name no source and links the
  repository, where each reading's is kept (§8.1).
- **Location**, stored only on the device:
  - **"Use my location"**: one reading from the device's location service
    (`ACCESS_COARSE_LOCATION` and `ACCESS_FINE_LOCATION`, asked for when
    tapped, not at start-up). Fine location enables the GPS provider, the
    only offline source on phones without network location (common without
    Google services); if the user grants approximate location only, the
    fused and network providers are used.
  - **City search**, offline, over a bundled list of about 25,000 cities
    (GeoNames `cities15000`, CC BY 4.0; attribution in the About screen).
  - **Coordinates** typed by hand.
  - No map: a map needs network tiles, and the app has no `INTERNET` permission
    (§2).
- The location drives the local sky line only; neither calendar depends on it.
- **People**, optional and stored on the device: up to ten, each a name, a
  birth date and a gender (not set, male or female; the tradition defines
  the progressions for these two only, and while it is not set the
  readings that need it are hidden, §5.9.1). The birth date of the person chosen enables the personal
  days (Tibetan luck/life/anti, the own days of §5.8 and the personal
  mansions, Japanese 三箇の悪日), the Tibetan pebbles, the nine-star reading
  and the election's "For you"; with no one chosen ("No one") the pages
  read as without a birth date. The menu row names the person chosen and
  opens the list: a tap on a person chooses them, a pencil changes or
  deletes them, and "Add a person" (while fewer than ten are saved) asks for
  a name and a birth date, both needed, and the gender, which may stay not
  set, and chooses the new person. With
  no one saved the row goes straight to adding. A birth date saved before
  there were people reads as one person without a name, listed by the
  date, chosen. Stored as the `people` preference, one person per line
  (epoch day, tab, name, and with a gender a tab and `m` or `f`; a line
  saved before has none and reads as not set), and `person`, the index
  chosen or -1.
- **Language**: the phone's language or one the app is translated into
  (English, Russian), each listed by its own name. Android 13 and later keep
  the choice themselves as the per-app language (`LocaleManager`, with
  `res/xml/locales_config.xml` and `android:localeConfig`, so Settings ›
  Apps offers it too); before 13 it is stored with the other settings and
  applied by a locale wrapper when the activity starts (`AppLanguage.kt`),
  without an AppCompat dependency. The activity is recreated in the new
  language. By default the app follows the phone, English when the phone's
  language is not offered. A language is offered only once it is complete
  and has been read through on a phone; a partial one still shows, string
  by string, on a phone set to it.
- **Nine-star reading**, a switch, off by default: adds the 九星気学 row of
  §10.4. Switching it on with no one chosen asks for a person (the list, or
  adding one when none is saved); cancelling leaves it off.
- **Local sky line:** sunrise, sunset, true solar noon (the sun's transit, not
  12:00) and the sun's altitude then. Facts only.
- **Choose a day**, under the two calendars: the election (§10.8) of the
  calendar shown, from the shown day, its work not yet picked.

### 10.6 Deferred until the basic design is set

- The widget: sizes and content.

### 10.7 Visual cues

Settled 2026-10-03 on the design canvas "Zanshin Calendar — visual cues".
Glyphs and diagrams are drawn for this app (`ui/CueGlyphs.kt`,
`ui/Diagrams.kt`, MPL-2.0) in the stroke style of the festival glyphs: a
24-unit grid, stroke 1.4, round caps. Every diagram has a content description;
kanji drawn inside one are explained by its caption, which names the cell
last tapped, today's at first (§10.1: every kanji shows its English on tap).

- **Activity families.** Each `Activity` has an `ActivityFamily` (38, from
  everything, weddings and journeys to rites, haircuts and conduct), one glyph
  each; a test fails on a family no activity uses, another on a family
  without a glyph, and the `when` that maps them is exhaustive. Regrouped on
  2026-10-06 after the Tibetan lists had grown from 112 activities to 180,
  when 21 of 27 families stood on nearly every day: livestock (a horseshoe),
  disputes (facing arrows), office and the great (a throne), vows and
  ordination (an alms bowl), offerings (a butter lamp), bathing (a ewer),
  family and friends (two figures), and the four actions apart, each as the
  hearth of its fire offering: round for pacifying, square for increasing,
  semicircular for power, triangular for fierce rites (Gyurme Dorje,
  *Tibetan Elemental Divination Paintings*, 2001, glossary, *burnt
  offerings*, after Klong chen pa and Beyer, *The Cult of Tārā*, pp.
  264–275). The rites' vajra is drawn upright, as it read as a chain of rings
  at 22 dp. The breakdown puts each activity's family glyph before it.
- **In brief line.** On the 旧暦 page the families named good and to avoid,
  in the order the enum declares, on two lines. On the Tibetan page one row
  (`DaySummary.row`): the families of the heaviest works named good, a rule,
  then those of the heaviest to avoid (§5.12 gives the weights); four places
  each, a side with fewer families leaving its places to the other, eight
  at most and as many as fit the width. A family appears once: where it
  would stand on both sides, it keeps the side of its heavier work and the
  next family takes the other place. Equal weights keep the breakdown's
  order. At the row's end the two counts, how many works the day names
  good and to avoid, one above the other in their colours, so that they
  leave the glyphs their eight places on a 411 dp screen in English and
  Russian. Screen readers hear the counts and the works the row stands for.
  Measured over 2000–2049: four and four on 92 % of days, about six of the
  eight glyphs change from one day to the next.
- **Animals and elements.** The twelve animals are drawn as heads (whole
  bodies were indistinguishable at 20 dp), shared by the 干支 rows and the
  Tibetan year, day and lunar-day animal; the five elements (Tibetan iron
  drawn as metal) and the Indian wind serve the 干支 stems, the Tibetan year
  and day, the weekday and mansion elements and the element pair.
- **旧暦 page.** 六曜: a day arc, morning on the left, noon at the top, in the
  tones of the times its reading names (`rokuyoTimes`), drawn only when it
  names some; its sheet repeats the arc and shows the six days in turn. Solar
  term: a ring of the 24, 冬至 at the top, the current term filled, the four
  土用 (the 18° before each 立) and the two 彼岸 (about three days either side
  of each equinox) as inner arcs. 九星: the day, month (月) and year (年) stars
  on one board of nine; the day star's sheet shows the board with each box's
  colour, trigram and direction. 恵方: a compass of the 24 directions with the
  year's bearing. 十二直: a dial of the twelve with their tones. 二十八宿: a ring
  in four quadrants of seven, north at the top, from 角 in the east
  counterclockwise as in the sky.
- **Boards of nine.** Both calendars draw the Lo Shu square south at the
  top: 4 9 2 / 3 5 7 / 8 1 6, the 九星 on the fixed board (後天定位盤, Japanese
  Wikipedia 九星) and the sme ba as Berzin prints it; the trigram of each box
  is the same in both. North-up elsewhere (compass, rings): only the boards
  follow the board convention.
- **Tibetan page.** Element pair: the two elements on the combination's row, and in its
  sheet the table of the ten pairs, weekday down, mansion across. Lunar
  mansion: a small ring of 27 on its row, the full ring in its sheet. Haircut:
  scissors on its row; in its sheet the days of the Tibetan month, one cell
  per civil day (a doubled date twice, a skipped one not at all), each dot
  the weighed side for haircuts, the caption naming the day's civil date
  and deciding factor.
  Trigram: drawn as its three lines. Named combination: in its sheet the
  table of the 28, weekday down from Sunday, the 27 mansions across, each
  cell in its tone, today's marked. Lunar date: five marks
  for its class on the row; in the sheet the thirty dates in the columns
  of their five classes with their tones. Karaṇa: a ring of the eleven in
  the order a month runs through them, Kiṃstughna first, the seven moving
  ones under a line, the three fixed last; yoga: a ring of the 27; both
  small on the row and full in the sheet, each cell with its tone. Rāhu: a compass, north at the
  top as the 恵方's, small on the row and in the sheet with the eight
  directions and a caption naming the course and its directions; an arrow
  from the direction it comes from to the one it goes to, as the date's
  reading gives it (`RahuCourse.of`, §5.13); on the 14th four arrows into
  a pool in the middle (from the sky into the lake), on the 30th eight
  outward (every direction). Rāhu's course by month has none: the row's
  compass is the date's. *Bla mkhyen*: the same compass, small on the row
  and in the sheet, with one saffron arrow from the middle to the day's
  seven-red, or a ring in the middle when the day's sme ba is the 7 itself;
  under it the board moved so that the day's sme ba (of the sixty-day
  count, §5.11) stands in the middle, the 7's box outlined and a short
  arrow into it from the middle, captioned as the working. The date's sme
  ba row keeps its own small board, the date's number marked: the two
  counts differ, so the spirit is never marked on it.
- **Reading sheets** carry a band in the reading's tone across their top.
- **Font.** `tools/subset_fonts.py` takes the characters of the Kotlin
  sources, the catalogs and the string resources; a rebuild after new kanji
  in any of them.

### 10.8 The election screen

The best days for a work (§5.14, ROADMAP E2), a full screen with a back
arrow, in the calendar shown: the Tibetan election (§5.14), or on the
旧暦 page the 旧暦's (§7.6, ROADMAP E5), in the 旧暦's colour, whose
differences close this section.

- **Entry:** "Choose a day" in the menu (§10.5), from the shown day with
  no work picked; on the Tibetan page, a work's balloon in the brief
  ("Choose a day for it") and any wording of a reading's lists, which opens
  a balloon of the works it names, one "Choose a day: …" line each; both
  from the shown day with that work picked.
- **Picking a work:** the families with their glyphs (§10.7) in the
  summary line's order, each with its count of works and opening them;
  a search over the works' names in the app's language. "Change" beside
  the picked work returns here.
- **The span:** three chips, "This month", "Three months", "Twelve
  months" (§5.14); the shown day's month at first.
- **The months:** for each Tibetan month of the span its name, its days
  as a grid of six to a row (`WorkGrid`, the haircut sheet's grid
  generalised, §10.3), each cell the lunar date over the dot of the work's
  side, green, red or without colour where no voice names it, the shown day
  outlined; with a birth date set, the person's enemy weekday and death
  mansion drawn as a red ring. Tapping a cell names its civil date, lunar
  date and side with what decided it ("good · by the combination",
  "avoid · by Monday"), or that every work is to be avoided for the
  person, naming the day. Under the grid the work's hours in that month
  (§5.14): the combination periods good for it and those to avoid it,
  clock times joined into runs.
- **The best days:** "Best days, strongest first", each with its civil
  date as the header gives it, weekday and year ("Mon 19 Oct 2026", since
  a span runs into the next year), and its Tibetan date, what decides it ("by the combination", "by Wednesday")
  with the dot of the combination's tone where it has one, the voices
  standing, the person's own days on it with their dots under "For
  you", and its nectar periods where their reading names the work. A day
  opens a balloon with the work, each voice with its kind, and "Open the
  day", which shows its Tibetan page. The days to avoid have no list.
- **Notes:** the order's sentence (the texts' order of strength, then the
  app's sum of weights, saying which is which), the hours' sentence (the
  same each day of a month; they say when, never which day), and with a
  birth date set the ring's sentence (WB vol. 2, p. 338).
- Weighed off the main thread; "Weighing the days…" until the span is
  ready.
- **The 旧暦 election** (§7.6): entered from the menu on the 旧暦 page, from
  a work in the day's breakdown ("Choose a day for it" in its balloon) and
  from a reading's lists, as on the Tibetan page. The picker offers the
  旧暦's works; the months are 旧暦 months, each cell's dot green where the
  work is named good only, red to avoid only, the mixed colour both ways;
  a tap names the annotations on each side, each kanji with its English.
  Under the grids "Named good, in date order" and "Named both good and to
  avoid", each day with its civil and 旧暦 dates and its annotations, each
  kanji glossed on tap; a day opens a balloon with "Open the day", its
  旧暦 page. No hours. The note says that the almanac gives no order and
  why, and that an annotation naming everything names the work.
  "Reading the days…" until the span is ready.

### 10.9 Meditation timer and mindfulness bell

A screen of its own, apart from both calendars (ROADMAP M1), opened by
"Meditation" in the menu's Practice group and by a tap on the timer's
notification; a back arrow returns to the day. Mind Bell (Apache-2.0)
was the model for what it does, not its code: Mind Bell's one sound
names no source, which §8 does not allow, and the bells here are
synthesised on the device (`app/.../bell/BellSound.kt`), so the app
carries no sound file.

- **Timer:** a dial of the session's length that empties as the
  warm-up or a period runs, its time left in Mincho numerals, Begin and
  Stop. A session (`SessionPlan`) is a silent warm-up of any length,
  none included, then one to twelve periods of any length one after the
  other (as a zazen of 20, 10 and 30 minutes), each length up to 24
  hours, typed as minutes and seconds in a dialog. The session's bell
  strikes once when the warm-up ends; where one period gives way to the
  next a different sound, the wood block by default (or one of the
  bells), strikes one to three times; the bell strikes one to three
  times at the end, 5 s apart (the wood block's 0.9 s). The timer stops
  once the last strikes have faded. While it runs only the dial, "Period
  2 of 3" under its time, the session's time left and Stop show.
- **Presets:** the session set can be saved under a name, listed above
  the session's settings with its periods ("20 + 10 + 30 min"); a tap
  sets it, marked by a check while the settings match it, a cross
  deletes it, and a name already saved replaces that preset, the dialog
  saying so. Kept on the device with the settings.
- **Running with the screen off:** a foreground service (type
  `specialUse`) holds a partial wake lock for the sitting and rings its
  bells by the elapsed-realtime clock; its silent notification counts
  down and carries Stop. Notification permission is asked for at the
  first Begin on Android 13 and later; the timer runs without it.
- **Mindfulness bell:** off by default. Every 15 to 120 minutes within
  active hours (from and until, by half hours, both ends ringing) on the
  chosen weekdays; fixed bells fall on the interval counted from the
  start of the hours, random ones half to one and a half intervals after
  the last, the first of a day within one interval of the start
  (`BellPlan`). One exact alarm at a time (`USE_EXACT_ALARM`, before
  Android 13 `SCHEDULE_EXACT_ALARM`), set again after each ring, a
  reboot, an update and a change of clock or zone. It keeps quiet during
  a sitting and a call, and, unless switched off, while the phone is on
  silent, vibrate or do not disturb; a bell more than ten minutes late is
  skipped. The switch's subtitle names the next bell.
- **Sound:** a large bowl, a small bowl or a bell, one choice for the
  session's bell and the mindfulness bell (the wood block only between
  periods); a volume and Listen. Bells ring on the alarm stream, so a sitting is
  heard with the ringer silenced, and duck other sound while they ring.
- No network: the §2 rule holds; the permissions added are the
  foreground service's, the wake lock, notifications, exact alarms and
  boot.

## 11. Milestones and done criteria

| | Milestone | Done when |
| --- | --- | --- |
| M0 | Toolchain: JDK 21, Android SDK, Gradle wrapper, empty modules, CI-less build | `./gradlew build` passes; `~/RUNBOOK.md` updated for the host changes |
| M1 | Tibetan engine | All Tibetan vectors pass; `cli` prints today |
| M2 | Astronomy library and kyūreki engine | NAOJ and kyūreki vectors pass; the cross-engine kanshi test passes |
| M3 | Texts | S1–S4 settled; every loaded entry carries a source |
| M4 | App and widget | Installed on the phone over adb; screenshot taken with `adb exec-out screencap -p`; owner confirms the screen and widget on the device |

Each milestone is committed only when asked.

State on 2026-09-28: M0, M1, M2 done — every vector of §9 passes. M3 done for
the sets of §8.3; the 2033 source is still open. M4: the day screens with
almanac, menu, date picker, location and birth date are built and installed;
the widget is not built (its design is deferred, §10.6). Release 1.0 is
prepared for F-Droid (§12).

## 12. Distribution

F-Droid builds the app from the public source and signs it with its own key.
What its inclusion policy asks of this repository, and where it is met:

| Requirement | Where |
| --- | --- |
| Free licence, public source | `LICENSE` (MPL-2.0); every Kotlin and Python file carries the MPL header |
| No proprietary libraries or tracking | AndroidX and Compose only; no `INTERNET` permission |
| Every asset licensed | Fonts under OFL 1.1 (`app/src/main/assets/licenses/`), GeoNames CC BY 4.0, texts per §8.1, glyphs and icon drawn for the app |
| No Google dependency blob in the APK | `dependenciesInfo` off in `app/build.gradle.kts` |
| Store listing in the repository | `fastlane/metadata/android/en-US/`: title, short and full description, icon, screenshots, one changelog per `versionCode` |
| A tag per release | `v` + `versionName`, on the commit that sets it |
| Description matches the app | the listing mentions no feature that is not built (no widget until §10.6 is done) |

The app is on F-Droid since 2026-10-02:
<https://f-droid.org/packages/io.github.iverlein.zanshin/>. A copy of its
`fdroiddata` recipe is kept in `docs/fdroid/io.github.iverlein.zanshin.yml`;
the recipe itself changes only through a merge request to `fdroiddata`, and
the copy follows it. A release is: bump `versionCode`
and `versionName`, add `changelogs/<versionCode>.txt`, commit, tag
`v<versionName>`, push the tag; F-Droid's update checker picks up the tag.
F-Droid's bot adds the build to the recipe within about a day, and its
build cycle publishes it some days later. The tag also gets a GitHub
release with the changelogs as notes, and once f-droid.org has published
that version, F-Droid's own APK is attached to it, checked against
F-Droid's signed index (`tools/release_apks.py`, run by
`.github/workflows/release.yml` on each tag and daily). One signature
everywhere: a GitHub install and an F-Droid install update each other.
Builds are not reproducible yet, so F-Droid signs with its own key; switching
to the developer's key later forces users to reinstall.

## Appendix A: Changes from the "Syncretic Rekireki" draft

- **Platform.** Was a Python terminal program with an Android port "later";
  the phone is now the only target, so the engines are Kotlin.
- **Code removed from the spec.** The draft's code did not run: undefined
  names (`C_RESET`, `C_FG_MOSS`), an f-string syntax error, a non-existent
  `ephem.next_rising` call whose error was swallowed so sunrise always printed
  a made-up 06:00, and a dependency (`qreki`) that is not on PyPI.
- **Tibetan engine.** The draft hard-coded month 4, day 15 and derived
  element/animal from the day of the year. Its step constants
  (692043/707268 and 30 × 292207/291996) do not match the published
  arithmetic; §5.1 uses Janson's. Its skipped/repeated day definitions described
  the same case. Local sunrise no longer defines the Tibetan day.
- **Japanese engine.** Renamed from "Shinto" (§7). Added the 2033 problem,
  the major-term month rule and the 立春 term order. Solar altitude is taken at
  true solar noon, not at 12:00 civil time, and the invented interpretive
  notes are dropped.
- **Texts.** The draft's commentaries were unsourced and embellished; the
  Tanabata text had a crescent where the moon is at first quarter, and O-Bon
  was dated by the old calendar where most of Japan keeps it on 15 August.
  All texts are now sourced (§8).
- **Removed.** The Antigravity (`agy`) harness, the "Chronos-Craftsman
  Systems" author line, the "zero-dependency" claim, and the chat residue at
  the top and bottom of the document.
