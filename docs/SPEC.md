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
| Tibetan: date, leap month, skipped/repeated day, weekday and planet, day element/gender/animal, year name and rabjung number, festivals; the almanac entries of §5.8 — lunar mansion, element pair, yoga, karaṇa, lunar-day cycles, hair-cutting day, monthly observances, personal days; the four aspects of the year and the pebbles of the day, month and year (§5.9); the lunar mansion's reading and the activity lists (§5.10) | Tsurphu version |
| Kyūreki: date, leap month, rokuyō, solar term of the day and current term, kanshi (干支), seasonal festivals; the 暦注 of §7.5 — 十二直, 二十八宿, 九星, 選日 and 暦注下段, 雑節, 恵方, personal bad days | 神吉日, 凶会日, 五墓日, 時下食 |
| Local sky: sunrise, sunset, true solar noon and the sun's altitude at noon; moon-phase glyph | |
| Screen with date navigation, home-screen widget, location setting; the morning notice, one a day with the day in brief (§10.5) | Other notifications |
| Meditation timer and mindfulness bell, a screen of their own (§10.9) | |
| Texts from published sources only (§8) | Translations beyond English |

Planned work, with its open questions, is in [ROADMAP.md](ROADMAP.md).
Nothing is planned that can neither be used nor change a weight or a
verdict (the owner, 2026-10-10).

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
app/    Android: Compose screen, home-screen widget (§10.6), settings
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
- "Today" depends on the page. On the 旧暦 page it is the civil date in the
  phone's time zone, from midnight. On the Tibetan page it is the Tibetan day
  running now, which begins at daybreak, the hare hour at 05:00 (§5.13), at
  the place whose hours the page shows: between midnight and 05:00 it is the
  date before, whose last hours are running, so the hours strip, the panel's
  dial and "now" hold the present moment. Janson's convention (Remark 6)
  of showing the coming day from midnight is not kept: on its page the hours
  had not begun (owner, 2026-10-15). The engine still maps a date to its JD;
  the UI labels Tibetan dates as "from dawn".

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
| Hair-cutting day | by lunar day, from Lama Zopa Rinpoche's translation (FPMT, 2008); FPMT's list, not the White Beryl's, which gives no haircut results by date (ROADMAP T2.15); the sheet opens on the result and whether the date goes well or badly for a haircut, sixteen good, fourteen to avoid (X10) |
| Washing the hair | the White Beryl's thirty hair dates (vol. 2, p. 404): what washing the hair on each date brings, a detail of the lunar date's reading; the good and bad dates that follow them are the list of KP box 37, whose worn bad line they fill (the 27th on neither side; `ElectionalTest`, [sources/hair-dates.md](sources/hair-dates.md)) |
| Monthly observances | 8th, 10th, 15th (Sojong), 25th, 30th (Sojong), after Edition Rabten |
| Personal day | luck, life or anti weekday for the animal of the birth year (Rabten's table), which are WB vol. 2, p. 330's la, life-force and slayer weekdays (*bla gza'*, *srog gza'*, *gshed gza'*), all 36 alike ([sources/personal-mansions.md](sources/personal-mansions.md)); the readings join Rabten's words to p. 330's (the la weekday's activities at its strong time, the life-force weekday virtuous, the slayer weekday avoided, and forceful rites quick when one's slayer weekday is strong and an enemy's la weekday weak; ROADMAP X8); needs a birth date (§10.5) |
| Personal mansions | whether the day's mansion is one of the six of the birth-year animal (bla, srog, dbang, skeg, bdud, gshed skar): the White Beryl, vol. 2, p. 330 (1996), its slips settled by the Sakya *nor bu'i me long* (p. 64) and Nam mkha' seng ge's *skar yig*, which print the same table ([sources/personal-mansions.md](sources/personal-mansions.md)); bla, srog and dbang skar lucky, the other three unlucky, as both texts call them; needs a birth date |
| Own days by the birth date | WB vol. 2, p. 338 (`ownDays`, ROADMAP T2.19): the birth weekday (the weekday of the birth date, p. 379); the weekday's place among the five of one's element, counted from the birth year's life force (*srog*), which p. 330 says is reckoned as the clan's element is: own (*rang gza'*, WB's *bla* and *dbang gza'* too, not the birth animal's *bla gza'*), mother, friend, child, enemy by the elements' relations, as the table of p. 346 gives them (`elementWeekday`), the weekdays' elements those of the *nag rtsis* (vol. 1, p. 257: Sun and Mars fire, Moon and Mercury water, Jupiter wood, Venus iron, Saturn earth); and the birth mansion, the almanac's mansion of the birth date (Phug pa Lhun grub rgya mtsho's coarse reckoning; no hour is asked). Birth and own weekday lucky with p. 338's works (battle array, contests of skill, pleading a case, trade, trials of strength, dice, horse races, archery, works that stir up strife); mother and friend lucky, anything good (p. 330); child no dot, middling (p. 330); enemy unlucky, every work avoided (p. 338); birth mansion lucky with p. 338's works. The mansion of conception (no source defines it) is not built ([sources/personal-mansions.md](sources/personal-mansions.md)); needs a birth date |
| The element's mansions | WB vol. 2, p. 330, with the tables pp. 345–346 (`Health.quarter`, ROADMAP H1): the day's mansion as a quarter of the element of the birth year's life force, counted as the weekdays are: the six eastern mansions from Kṛttikā (2–7) wood, the six southern (9–14) fire, the six western (16–21) iron, the six northern (22–26 and Aśvinī) water, the four between (Bharaṇī, Āśleṣā, Viśākhā and Abhijit) earth; own (good for anything), mother and friend (good), child (middling, no dot), enemy (no reading of its own, no dot; "every work avoided" is the enemy weekday's, p. 338). The tables print 21 both among the western six and among earth's four; WB vol. 1, p. 232 ends the western six with Śravaṇa and names Abhijit among the four, so a day in Śravaṇa is iron's and earth's fourth falls on no day. The great and small grave mansions of each element, all among earth's four (wood Viśākhā and Bharaṇī, fire Abhijit and Āśleṣā, earth Āśleṣā and Abhijit, iron Bharaṇī and Viśākhā, water Āśleṣā and Abhijit), are reckoned for the dead and have no reading for the living's day; shown on the Health screen (§10.10), not weighed; needs a birth date |
| The la | Its element the mother of the life force's (WB vol. 1, p. 253: water for wood, fire for earth); its place by the lunar date, the Phugpa list of WB vol. 2, p. 303, as the lunar date's reading gives it; its course within any day (p. 304): both feet at daybreak, the neck at sunrise, the hollow of the nape early in the morning, the lips late in the morning, the middle of the chest at noon, the navel early in the afternoon, the heart late in the afternoon, the back towards evening, both feet at sunset, spread above the navel from dusk to daybreak, the verse's times and not the clock's; where it stands, bloodletting, moxibustion and other treatment are avoided ([sources/health.md](sources/health.md), RD10) |
| The day's illness | What falling ill on the day means, as Illness rows of three readings: the lunar date's, dates 1–8 with lines of their own and every later date the same as one of them in a round of eight (WB vol. 2, pp. 297–302; the 9th as the 1st, the 30th as the 6th), the 8th, 15th, 22nd and 30th marked as the four perilous days (p. 302); the weekday's (pp. 308–312); the link's, how long the illness runs (pp. 351–356). Doubtful words are left out (the date's «ཀླུ་གྲ», the rites of the 1st's last line) ([sources/health.md](sources/health.md)) |

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
(`YearOfLifeTest`). Shown in the Reckonings, a year at a time (§10.11).

| Sign | Rule |
| --- | --- |
| Age | the Tibetan count: 1 in the year of birth, one more at each Losar |
| Natal mewa | the year's sme ba, one less each year round the nine; "the present rabjung's fire hare" (1687) has the 7 red (p. 255): the 九星 year star of the Tibetan year |
| Mewa of the year of age (*babs sme*) | the natal one in the middle of the square, then east, one place a year, on to the north-east in a male birth year, to the south-east in a female one, "whether the person is male or female" (p. 256) |
| Trigram of the year of age (*babs spar*) | "a man from li towards khon, a woman from kham towards khen; one trigram for each year" (p. 257); the Moonbeams' leap at each ten is not WB's |
| Progressed sign (*log men*) | a man from the tiger of the element his birth year's element feeds, forward through the sixty; a woman from the monkey of the element that feeds hers, backward (p. 387) |
| Stages of the energy (*dar gud*, growth and decline; in the code `Sector`) | for each aspect's element, the twelve stages of its energy, likened to a life (ch. 20, pp. 234–235), from the breath-taking (earth and water at the snake, fire the pig, iron the tiger, wood the monkey) to the tomb; the present year's animal gives the sector (p. 258); six good, six bad |
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
decline (p. 409). Tones: the predictive pebbles and stages by their colour, the
obstacles unlucky (the own year and the seventh at their mother year
mixed, as WB reads them good, and the progressed sign on a tomb mixed,
"slightly bad"), the progressed sign's gains lucky and its
doors and fives unlucky; mewa and trigram no tone.

**What to do** (the owner, 2026-10-16; ROADMAP Y1). The year sheet is
read for action, as WB itself reads it: «དེ་ལྟར་རྡེལ་ནག་ནན་གྱིས་བཅོས།», "so
treat the black pebbles in earnest", the rites fitted to what matters
most, to one's means, the place and the time (pp. 415–416). So:

- **What matters most** opens the pebbles: for a man the vitality, for a
  child the body, for a woman the destiny, "held the most precious and
  reckoned" (ch. 20, p. 242; p. 416, where people of rank or learning
  hold the luck). WB gives no age for a child; one counts as a child
  below the 18th year, the first in which WB's nine-multiples call a man
  "in his prime" and a woman "young" (`Care`); with no gender and from
  the 18th, all three are named. That aspect's predictive pebble is marked
  "matters most for you".
- **Each aspect's predictive pebble** follows, the year's verdict on it,
  with the stage its element's energy stands in under it, then the grid
  of the twenty-four they are counted from.
- **The stages are named as the energy's**: "Vitality · energy: death
  stage", never a bare "death"; the reading says the element's energy is
  in its death stage and what WB glosses it as, "prosperity wanes".
- **Every remedy WB gives is a *Remedy* row**, one row for what the
  person does and the rites alike, since the calendar's reader, a
  ngakpa, is practitioner and ritualist at once. Where WB gives a remedy
  once for a group it is one row for the group (`Reading.alsoRows`): the
  own year's and the seventh's general rites (pp. 388–390), the tombs'
  (p. 415), and the nine-multiples' cure, which p. 414 gives for every
  kind, the single count too («དྲུག་པ་དགུ་མིག་བཅོས་ཐབས་བསྟན།»). A bad stage's
  remedy is its aspect's decline remedy (p. 409) and then the stage's own
  rite, where p. 409 sends it, «གུད་བཞིའི་གཏོ་ཆོག་ནད་རྩིས་བཞིན།», to the illness
  reckoning's twelve stages (ch. 25, pp. 440–442), whose two spirits stand
  in *Spirits that harm*. The verse's images stay, after the ruling; the
  harmful spirits and the illnesses are rows. Where WB names no remedy
  (one white and one black on the destiny, the progressed sign's three
  meetings, the black undertakers), none is written.

**With the relatives** (§10.5; ROADMAP P2, `Kin.kt`, `KinReckoning`;
`Reckonings.kin` takes the mother, father, spouses and children entered
under the person, and anyone of the household further out). The **natal
trigram** (*skyes spar*) is the mother's trigram of her year of age in the
year of the birth, a woman's circuit from kham towards khen, one a year:
"take the child's year from the mother's, add one, and turn the mother's
trigram that many times" (the Moonbeams, f. 9a; WB ch. 22, p. 261, "the
trigram one is born on is the mother's luck", the notes, vol. 2, p. 476).
It is a row among the signs, without tone, reading WB's births on the
eight trigrams (ch. 22, pp. 286–288: the forefathers' fault, the nature of
the one born on it, the gods and the spirits that harm); with no mother's
birth year, a note says where to enter it. The **obstacles** join the
year's, each unlucky, its row naming the relative (WB ch. 24): the
progressed sign on another's birth year, from either side (p. 388): the
husband's on the wife's, the wife's on the husband's, the son's on the
father's, the father's on the son's (a son is a male child), and the
person's on the year a parent died; husband and wife on one trigram of
the year (*bza' shug*, p. 390), and two others of the household on one,
the lesser fault of the bridge's end (*zam thug*, the notes, p. 492); the
secret obstacle (*gsang keg*), the trigram of the year on the natal
trigram (p. 390), its rites by whether the mother lives; and both spouses'
mewa of the year on the two-black (p. 409). A relative's own trigram,
progressed sign and mewa of the year need their birth year (their gender
for the first two) and that they live; a spouse or father who has died
brings none of them. Tested in `KinTest` and `ReckoningsTest`.

**Not calculated**, for want of data the app does not ask for or of a
clear rule ([sources/year-of-life.md](sources/year-of-life.md)): the
secret obstacle's second clause, the son's on li and the daughter's on
kham (open question 91, whose trigram «མ་སྤར» is); the forefathers' tomb
obstacle (*dur keg*, question 90, whose signs at death); the progressed
sign on the "white year" (question 92); the dead's obstacle of seven
generations, which WB rejects; the household's pebble round, the
household's trigrams piled and its mewa gathered by colour (questions 96
and 158: who counts, and how many make a pile); the births by the natal trigram
against the life force and the parents' trigrams at the birth (ch. 22,
pp. 294, 326), which are the birth reckoning's (§5.9.2); the
four counted signs of the progressed sign (lifeline, peg, sky and earth
extension); the clan's nine-multiple (no clan is asked); the
readings of the mewa's nine-multiple by kind of person, which WB gives as
what "some hold"; the tomb days of the month (the 6th, 18th and 30th),
which are days, not years, and have no reading; and the Chinese pebble
divination that ends the chapter (pp. 416–452), a casting, not a
reckoning.

#### 5.9.2 The birth

WB vol. 1, ch. 22, the reckoning of lives and karma (*tshe rabs las
rtsis*), as far as it is built (ROADMAP R2, 2026-10-19;
[sources/year-of-life.md](sources/year-of-life.md), RD13): the parts read
on the scan that follow from the years alone. `BirthReckoning`
(core `tibetan/Birth.kt`):

- **The natal trigram**, the mother's trigram in the year of the birth
  (§5.9.1, `KinReckoning.natalTrigram`); without the mother's birth year
  nothing of the birth is reckoned.
- **The five children** (p. 294): the natal trigram's element (li fire,
  khon earth, dwa iron, kham water, zin wood; khen, gin and zon earth, by
  the *Moonbeams*) against the life force of the birth year: its mother,
  the parents' god (*pha ma lha*); its son, the children's glory (*bu
  tsha dpal*); the same, harmony that is life (*mthun pa tshe*); the
  element it overcomes, a spirit that can be survived (*thub pa 'dre*);
  its enemy, a demon that cannot (*mi thub bdud*), dying at fifteen for
  wood, twelve for iron, nine for fire, six for water and three for
  earth. WB's worked count is the test (`BirthTest`). The ages p. 294
  gives the other four are those of its example, the horse and snake
  years, and are not shown.
- **The parents' trigrams at the birth** (p. 326), the father's being his
  trigram of the year of the birth: a son with the father on zin and the
  mother on dwa, the father soon dies; a daughter with the father on gin
  and the mother on zin, the mother; on khen a son of a dog or dragon
  mother, or a child of a dog or dragon year, bad for the father; a son
  on li to a father on khen, the same; on khon a daughter of an ox or
  sheep mother, or an ox or sheep daughter, bad for the mother; on zon
  any child of a dog or dragon mother, or a dog or dragon son, no sister
  to call one, and on gin any child of an ox or sheep mother likewise; on
  gin an ox or sheep daughter, bad for her brothers; a daughter on the
  trigrams p. 326 lists for her year (the horse, snake, mouse and pig on
  kham; the tiger, hare, bird and monkey on dwa or zin; the ox and sheep
  on khon or gin; the dog and dragon on zon; any on khen) counts
  hearthstones (*skyed po bgrang*), setting up house again and again.
  Without the child's gender the readings for a son or a daughter are
  left out, without the father's year those that ask for his trigram.

**Not calculated** yet: the pebbles of the birth (the four precious, the
special pebble of the clan, which is asked, the indispensable two, the
five of unchanging luck, the hundred, the rise and decline, the decisive
twenty, pp. 259–262), whose colours and readings past p. 262 are not
read on the scan; the readings of the sixty years and of the mewa of the
birth; the line of births; the readings of p. 326 that need the order of
the siblings or end "and the like" (an earlier child on li and a later
on khen; mother and child on enemy trigrams); the five by the year
(p. 347), another count of the same five, not shown beside p. 294's.

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
  (`MansionVerses.kt`). Kṛttikā's «དྲ་གྱོད» is read «དྲ་གྱོན», cutting and
  wearing new clothes, with the Zhol print. Left out: words the reading leaves in doubt, what
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
- **The White Beryl's twelve day animals** (vol. 2, pp. 356–359,
  [sources/day-animals.md](sources/day-animals.md), the lists read on the
  scans): what each *nyi ma*'s verse names good and to avoid, with the
  avoidances of the six opposed pairs after them (tiger and monkey
  councils and gatherings, dog and dragon war and raids, pig and snake
  wood and digging, mouse and horse horse racing and slaughter, ox and
  sheep building a throne and raising silk flags), as wording keys in the
  verse's order (`DayAnimalVerses.kt`). Left out as with the mansion
  verses: words the reading leaves in doubt (the bird's and hare's pair
  among them), what a verse calls middling or only acceptable; a work
  qualified by a direction is shown and weighs for neither side. The
  earth lord's seat and remedy that close each verse are chapter 31's
  (§5.11) and are not built twice.
- **The print's other boxes**, read on its scans in the same way
  ([sources/kp-activities.md](sources/kp-activities.md)): 50 more
  lists, from naming and new clothes to building, sowing, trade, medicine,
  haircuts, ordination and teaching, rain and councils. A box that says
  "otherwise as box N" takes N's lists in the kinds it names nothing in; a
  box that joins two activities (crafts and haircuts, military training and
  games) is split, an entry qualified for one half counting for that half;
  boxes 50–51 come from the second print, KP2. The box for averting rites,
  whose entries all name a kind of rite, and the charts are not built. The lists' readings cite the
  print first, the White Beryl and Henning beside it. The boxes name the day's link
  of the twelve and the sme ba too (`Factors.links`, `Factors.smeBa`): «སྐྱེད» is the
  sense sources (skye + mched), bare «སྐྱེ» birth, and a bare numeral in the sme ba's
  place before the rising signs is the sme ba (ROADMAP O1). Box 53 is two
  columns, the good and the bad, with its links, the Tiger, Dragon and Ox
  days and the trigram zon in the good one. Every box was read again on
  the scan for O10 (2026-10-14); where it reads otherwise than the first
  reading, the scan's reading is built (ROADMAP T2.28): the brackets of
  boxes 28, 30 and 54 set weekdays aside, box 55's mansions are as both
  prints have them, and box 21's bad dates are bad for breaking in oxen
  only.
- **The White Beryl's chapter 34**, the important works one by one (vol. 2,
  pp. 378–428, [sources/white-beryl-ch34.md](sources/white-beryl-ch34.md),
  every list the app uses read on the scans), which the print's boxes
  digest in the same order (`WhiteBerylWorks.kt`). It is joined to each
  box: what it names plainly good is added to the box's good and taken
  from its bad, and the other way round; what it reads otherwise than the
  box (the 19th for the box's 20th, the sme ba four for its nine) or calls
  middling is taken from both sides (`WhiteBerylWorks.NEITHER`); what the box names and the
  chapter does not keeps the box's reading. Dates the chapter names for
  one month only (work 14: the 27th of the 11th month, the 25th of the
  12th, the 23rd of the 1st, by the Hor months as chapter 31 numbers them, §5.13) are
  `Factors.monthDates`, joined to the date's lists in that month alone
  (`Texts.electionalDate`), where they outweigh the plain date on the
  other side (the 25th is good for goods, the 12th month's 25th bad).
  Work 14's good «ཉེར་གཅིག་ཉེར་ལྔའི་བར» is the run 21 to 25, as work 13
  writes it out, where the box has the 21st and the 25th alone (open
  question 156, the owner, 2026-10-18). So the chapter settles the
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
good, Revatī among the unstable and changing. Each mansion's reading opens
with its place among WB's classes, then Henning's nature and kind of work
(ROADMAP X6); for those three WB's class stands alone, with what the class
is good for, and Henning's line stays in the topic file; Bharaṇī, which
the print puts in no class, keeps Henning's line alone.
A mansion's reading joins its White Beryl verse's lists, first, with what
the list of mansions names it good for and the activity lists' good and
bad, and cites the White Beryl and the print beside Henning; where they
disagree (Rohiṇī is good for marriage in Henning's list, to avoid in its
verse and the print) both stay, as elsewhere in the app, and the mansion
says nothing on that work. The day in brief (§10.3) weighs the factors as §5.12 says.
The day animal's reading joins its White Beryl verse's lists first, then
the activity lists without each entry that names any of its works the
other way from the verse, since the White Beryl counts above the print
and Henning: so a hare day no longer favours marriage, nor a pig day
avoid suppressing the *sri*, and an entry joining two works goes whole
(the dragon's and dog's "hunting and theft", where the verse avoids
raids). Where the verse itself names a work both ways under two words
(the hare's works for the dead and its funerals) the day animal says
nothing on it.

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
  (open question 9): it carries WB's results of the twelve day animals
  with the activity lists (§5.10) and, in WB's
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
  weighed, as WB names no weight for the hook. Its sheet gives the ruling,
  the remedy as a row and *How it falls* with the shown day's pair,
  "today Saturday and the 7th" (`Texts.burningDate`, §8.1 rule 4).
- **The month's own entries** (WB's month heading, vol. 1, p. 176, with
  ch. 15–16 and vol. 2, pp. 212, 312, 359;
  [month-entries.md](sources/month-entries.md); `MonthEntries`, checked by
  `MonthEntriesTest`): the month is long when its days reach thirty,
  short with fewer, however many dates are omitted or doubled. The
  weekday that rises in it, roughly by the month's animal after the
  *Gtsug lag spor thang* (Saturn in the tiger, horse and dog months, Mars
  in the mouse, dragon and monkey, the Moon in the pig, sheep and hare,
  Jupiter in the bird, ox and snake) and finely from the element of the
  true weekday of its 1st, the lord: the planets of the lord's element and
  of its son rise, its mother's wane, its enemy's decline, its friend's
  move, as the model almanac's month lines print them; p. 312 lifts no
  avoidance for a rising planet, so the rise changes no weighing. The
  year's black months and the black third of this one (§5.13). The
  seasonal signs of ch. 16, by the mean sun of the true reckoning
  (Janson's (7.6)–(7.8)): the sage's seven days (*drang srong ri ti*),
  when the waters gain the eight qualities, from the date in which it
  reaches 10 mansions 43;30, and the pig's (*phag zhag*), when the rain
  turns to poison, from the date in which it reaches 5;46,30, their days
  counted from the calendar day of that date; the engine's dates are
  those of WB's 65-year table (pp. 188–189) or the date after, as its rule
  allows, for every year from 1990 to 2100. The comet WB's count marks
  (p. 186): the years since the rab byung began (1027, as the *Kun 'dus
  chen mo* restates it) times twelve with the months since the 3rd, the
  corrected month count (twice it with 31, over 65, added) with 3 added
  leaving nothing over 75. All shown and not weighed: WB gives them no
  weight.
- **The eclipses** (WB vol. 1, ch. 9, pp. 59, 62 and 65, with ch. 4's
  *byed rtsis*, pp. 31–32; [month-entries.md](sources/month-entries.md);
  `Eclipses` and `ByedRtsis`, checked by `EclipsesTest` and
  `ByedRtsisTest`). The sun is the abridged tantra's, the *byed rtsis*,
  as ch. 9 says: its months from *nag pa* of 1687, the corrected month
  with twice the months and 19 over 65 added; the month's weekday 1;31,50
  from 0;2,10 back, its anomaly 2;1 from 9;79 back, its sun
  2;10,58,2,10 from 26;54,24,3,9 back; each date 0;59,3,4,0 and
  0;4,21,5,9; the true reckoning's equations. A month's moon is the same
  in both reckonings: the *byed rtsis*'s month is the app's count less
  10 898. Rāhu's head is `Planets.rahu`'s, WB's own (question 16). The
  moon of the full moon is that sun at the end of the 15th with half a
  circle added; Rāhu seizes it within 57 chu tshod past the head, 50
  before the head or the tail, 45 past the tail. The sun at the end of
  the 30th is seized within 52 past the head or 5 before it, 7 past the
  tail or 40 before it. Against Meeus's eclipses of 2000–2049
  (`tools/eclipse_vectors.py`) every full moon it marks (79) has a lunar
  eclipse and every new moon (33) a solar one; it misses five umbral
  eclipses of magnitude under 0.1, those of July 2001 and July 2019, and
  faint penumbral ones. With the true reckoning's sun it would find 27
  of the 72 umbral eclipses and mark 31 full moons with none. The day
  of the 15th's or 30th's end shows the eclipse in the Almanac, neutral,
  with WB's merit (p. 62): deeds multiplied seventy million times at a
  lunar eclipse, a million million times at a solar one. It names no
  works, so it is shown and not weighed. Not built: the eclipse's time,
  size, length and results (sections 2–8 and 10), which name no works
  either, and p. 64's two exceptions for the sun, the two turnings and
  Mercury or Venus with it, which WB's readers make days on which the
  sun is hard to seize, not days without an eclipse, and which would
  remove real eclipses (open question 18).
- **What WB's month heading writes and the app leaves out** (the owner,
  2026-10-10: what can neither be used nor change the weights or the
  verdict is not shown): the *byed rtsis*'s own omitted and doubled dates
  and its five places of each date, both reckonings' month figures
  (*dhru ba*), the five planets' slow and fast motion at the full and new
  moon (p. 40's four motions), Rāhu's figure, and the Kar myang
  reckoning's Lord's meeting (*dbang phyug mjal phrad*, p. 36). Each was
  read and could be reckoned ([month-entries.md](sources/month-entries.md));
  none feeds any weighing.
- **The letters of the day** (WB vol. 1, p. 177, entries 3, 4, 12 and 17,
  with ch. 2, pp. 16–19, ch. 12, p. 97, and ch. 13's table, pp. 150–153;
  [day-letters.md](sources/day-letters.md); `DayLetters`, checked by
  `DayLettersTest` against the model almanac's day boxes and the table
  cell by cell). The Kālacakra's vowel of each date, the same every month
  (a i ṛ u ḷ, a e ar o al, ha ya ra va la, then the long ones back: lā vā
  rā yā hā, āl au ār ai ā, ḹ ū ṝ ī ā), joined to the consonant of the
  month's sign, five to a group and six rounds a month (the *ka* group in
  Capricorn and back in Aquarius, *ca* in Pisces and Aries, *ṭa* in Taurus
  and Gemini, *pa* in Cancer and Leo, *ta* in Virgo and Libra, sa ha ṣa
  śa kṣa in Scorpio and Sagittarius; Hor month *m* the sign *m* − 3 from
  Aries, a leap month its number's), with its element and sense object,
  space to earth with sound to smell in the waxing half, back in the
  waning. The *dbyangs 'char*'s, the same every month, its halves
  reversed: a i u e o with dha to ha on the 1st to 15th, ā ī ū e o with
  ka to da (without ṅa ña ṇa) on the 16th to 30th, earth to space with
  smell, taste, form, touch, sound. The date's stage with the five-fold
  cycle: child, youth, prime, old, ripe. The twelve links, counted in days
  from the day of each month's *sgang* (§5.8) to the day before the next,
  by WB's table: the row of the *sgang*'s month starts from its link (the
  5th month feeling, each later month the next, the 11th ignorance), runs
  forward twelve days and repeats the 3rd to 5th, then back over the
  second fifteen in the same way, the 31st column its link less seven, the
  32nd less five, and the 10th month's 28th column *rga shi* as both
  prints have it; with 28 days the 15th and 30th columns drop, with 29 the
  30th, with 31 and 32 the extra columns come in. A stretch of 33 days
  (once in 2000–2049, from 4 July 2009) has no link. The hundred feet: the
  quarter of its mansion the moon walks in at daybreak, with its syllable
  on ch. 12's wheel (Kṛttikā a i u e … Bharaṇī li lu le lo; Śravaṇa ju je
  jo kha, Abhijit's khi khu khe kho never the moon's), and the moon's
  mansion's fangs on the wheel of the hundred feet (pp. 96–97: 81 cells,
  the mansions round the outer ring, the consonants, the signs and the
  five classes within, the sixteen vowels in the corners): the right fang
  the diagonal toward the mansions before, the left toward those after,
  each with every cell it crosses, the face-on where the left meets the
  outer ring, as p. 98's Kṛttikā has them (right Bharaṇī; left a, Taurus,
  dga', bzang, Libra, ta; Viśākhā face-on); and the same over the mansions
  of the sun, the five planets and Rāhu (p. 178's «ལྔ་སྒྲ་ཉི་མ», question 17):
  the sun the almanac's true sun at the date's end, the planets' apparent
  places (*myur dag*) and Rāhu's head on the day's date as Edward Henning's
  software reckons them for the generalised Phugpa epoch (`Planets`, a
  digit-for-digit port of TCG 1.06's t3.c and t2.c, MIT licence; the
  general day is the Julian day number less 1,355,847, Henning's true
  month count the app's plus 22,338; `PlanetsTest` against his program on
  every day of 2000–2047). What the app does with them (ROADMAP T2.25,
  built 2026-10-10; the owner's rule that nothing is shown that can
  neither be used nor change a weight or verdict): the Kālacakra's and
  the *dbyangs 'char*'s letters and the date's stage are not shown, as WB
  reads them against the syllable of a person's name (vol. 2, p. 305),
  which the app does not ask for. The day's link is weighed with the
  trigram and the date's sme ba (§5.12) by its lists: WB's own reading of
  each link's day first (vol. 2, pp. 351–356, the days of the twelve
  links, ROADMAP T2.27, built 2026-10-15; `LinkVerses`), its works with
  washing the head, washing the body and new clothes, which every verse
  names one way or the other; then what the *kun phan me long*'s activity
  boxes name each link good or bad for, 22 boxes read on the scans
  ([kp-activities.md](sources/kp-activities.md)), without the entries the
  verse names the other way, as for the day animal (§5.10). The link's
  sheet gives WB's ruling, the four directions for one's aims as
  *Setting out*, the remedy and a birth on the day as rows (§8.1); the
  loss and its thief are left in the topic file
  ([day-letters.md](sources/day-letters.md)), the illness waits for the
  health screen (ROADMAP H1), and p. 356's links on the hours for open
  question 19. The date's sme ba is weighed by the boxes alone, sixteen
  naming its numbers. The hundred feet are
  read as ch. 12 reads them (pp. 97–99, on the Zhol print, WBZ img.
  133–136; `HundredFeet`): from each body's mansion the right fang ends
  on one mansion, which it strikes; the sun, Mars, Saturn and Rāhu are the
  four malefics (*drag gza' bzhi*, p. 95; the sun «སྡིག་གཟའ», p. 97). On a
  person's birth mansion one malefic's fang makes aims fail, two bring
  illness, three death; one or two benefics' (the moon's, Mercury's,
  Jupiter's, Venus's) leave food and drink short, three bring epidemics
  and loss of wealth. The six holders counted from it round the wheel's
  28 (the 10th place of work, the 16th, 18th, 19th, 23rd and 25th) have
  their results, and malefics on the birth mansion, the place of work and
  the 23rd at once bring death. A body's face-on on the moon's mansion
  of the day is the spear (*mdung sbyor*) for a benefic, the great spear
  (*mdung chen*) for a malefic: every work avoided, a bride above all.
  Shown and not weighed, as p. 376 does not rank them. Not built: p. 98's
  rule that a malefic's fang or face-on on the date, sign, consonants and
  vowels is avoided when one acts, sets out or stays, which on the date's
  class and the moon's sign alone falls on 64% of days in 2000–2049 and
  does not say whose letters; and the five harms of p. 97 (*sol ba*,
  *'khyud pa*, *du ba*, face-on, sight), read and recorded in
  [day-letters.md](sources/day-letters.md).

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
  last, decides only a work no other voice names; the date's sme ba and
  the day's link stand with it as one voice (ROADMAP T2.25), since no
  text orders the three, silent on a work they name both ways. The special days
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
  own days and mansions in "Your day", the vitality and body on the Health screen (§10.10); the
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
  to. It stands once in the catalog, as the *Through the day* row of
  Rāhu's family note (`reading.Rahu.through_day`, ROADMAP X7), which the
  general course shares, and not on the hours panel, since the
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
  WB's almanac writes on the day (vol. 1, pp. 173, 178): first the black
  month (*zla nag*, p. 212, the pure Phugpa view; vol. 1, p. 183): the
  months whose animals are the four-slayers of the year's, three either
  side of it, the year the Chinese reckoning's from the 11th month; of a
  first month of a season the upper third is black (the 1st–10th), of a
  middle month the middle (11th–20th, WB's notes, vol. 2, p. 484), of a
  last the lower (21st–30th), and the course falls on the days of that
  third, with p. 212's and p. 359's works and remedies. Then Kikang's
  black month (*ki kang zla nag*, p. 212): in each year one date in two
  season-months, with a time of day for some, and one work spoiled (a
  tiger year's 8th of the first months of summer and winter at dawn,
  funeral rites … an ox year's 22nd of the last months of autumn and
  spring at dusk, sending wealth out). After them the great
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
  black months (vol. 1, p. 183). Keyed by the Chinese reckoning's season-month,
  which is also the animal month from the tiger (the 11th), by the date and,
  for *zin phung* and *gnam khyi*, by the date's animal; a skipped date has
  none, a doubled one both days, a leap month as its month
  (`EarthLordCourses`). Only WB's own course of each earth lord is shown,
  one entry for each (the owner, 2026-10-11): where WB gives a course and
  then another way, one person's view or what "some say" (*ki kang*'s
  second and third courses, the great black day's and *nag chung*'s
  variants, the Paṇchen's black day by year, the eight classes' and the
  nāgas' added strikes, …), the others stay in
  `docs/sources/earth-lord-courses.md` and are not shown, in the courses
  or in the readings; an earth lord that does several things on a date
  (the *gnyan* move and strike, several classes have their time) is one
  entry, its lines in one reading and its lists merged. WB's model almanac (vol. 1,
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
  for, its *How it falls* row the rule and the weekday's clock hours. It takes no part in the weighing: it is a time within the day.
- **The combination period** (*tatkāla dus sbyor*,
  [sources/combination-period.md](sources/combination-period.md)), which the
  texts hold above every factor of the day: the sign rising in each hour.
  KP's table (§9, img. 81–82) gives the month's sign at daybreak and one
  sign more each hour (the 3rd month Aries … the 2nd Pisces); WB (vol. 2,
  pp. 371–376) gives for each sign what is good and bad while it rises and
  whether the period is to be accomplished or avoided. Capricorn, to be
  avoided, has a good list too, beside its woodcut on p. 374, which the
  etext drops (ROADMAP O10). It is shown on the
  hours panel (§10.3) and in the day in brief's "By the hour" block, not
  weighed into the day: a day reading has no hours. Within its hour it
  decides a work it names one way, the work's own hours first
  (`DaySummary.inHours`, `Election.periodSide`); the haircut row on today's
  page reads it, and so do the In brief row and its sheet for every work
  (`DaySummary.atHour`, §10.3). On the twelve signs it names 75 of the
  139 works one way or the other: setting out, building and the sacred
  supports in all twelve hours, haircuts and ten more works in ten, down
  to seven works in one.
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
  'phar ma*'s course by season-month, is not shown, nor named in the
  reading (the owner, 2026-10-11: no other view of an earth lord, §5.13).
  The hidden earth
  lords (*gab pa'i sa bdag*, p. 221), one to each animal, on the place of
  the hour's own animal (*gnyan khra* the mouse's … *phyug po* the pig's),
  a corpse not led their way. The black sky dog (p. 197, for year, month,
  day and hour by the new Chinese reckoning): its head on the hour's
  animal, its tail on the seventh, its twelve parts clockwise (*chos
  skor*) on through the animals, as a Gyalrong rtsis collection lays them
  out, each with what it forbids and what it does to a bride
  (`gnamKhyiPart`); its reading gives the head's and the tail's, the
  other ten parts' are in
  [sources/earth-lord-courses.md](sources/earth-lord-courses.md). On the
  hours panel (§10.3), not weighed. Each of these readings' *How it
  falls* row is the shown hour's line (§8.1, rule 4; `HourFall`): the
  place it sits on in this hour, the sky dog's head and tail, the date's
  two black hours; the hour's *bla mkhyen* points to the day's for its
  five texts, which stand once (ROADMAP X9).
- **The hours of the Chinese reckoning** (WB vol. 2, p. 359, read on the
  scan; [sources/weighing.md](sources/weighing.md), *The hour of rule 2*):
  after the twelve day animals' results WB names what each of the twelve
  hours is good for, from the hare hour: guarding curd and beer and binding
  the *'gong po*'s mouths; raising fortune and prosperity (*phywa g.yang*);
  the mantras that tame the earth lords; fierce averting recitation; taming
  madness and fainting fits; driving out adversity; fulfilment and
  offerings to the protectors; binding the *bse rag*'s mouths; taming the
  *gre mo*; the mantras that summon fortune; fierce mantras; mantras to
  slay enemies (`Texts.HOUR_WORKS`). The hour of one's own year, the birth
  year's animal's: fierce and harsh work, gatherings, confrontations and
  great displays avoided (`ChineseHours.ownYearHour`, `Texts.OWN_YEAR_HOUR`).
  The four rough times when the four māras move, «ཕྱེད་གཉིས་ཟེར་གཉིས»: in them
  nothing goes well but averting, killing and fierce work
  (`Texts.ROUGH_TIME`). WB does not say which they are; a ritual manual of
  the *Rin chen gter mdzod* (vol. 54) glosses the same pair, «གུང་གཉིས་དང་ཟེར་གཉིས»,
  as midnight and noon and the rays of the sun rising in the morning and
  gathering in the evening, and the *'Bras rtsis rab gsal nor bu'i me long*
  counts them, with the black hours, among the hours to avoid for a
  bride. They are moments: the app takes the sun's at the user's place,
  sunrise, the transit, sunset and the transit twelve hours on, those
  that fall within the Tibetan day from 05:00 to 05:00, and shows each in
  the hour it falls in (`ChineseHours.roughTimes`); without a place they
  are not shown. WB closes, «དུས་ཚོད་མཚོན་ཆ་རྣོ་བ་ཡིན», the hour is a sharp weapon.
- **The hour above the day's animal sign** (the *kun phan me long*'s rule
  2 from WB p. 376, «ཉི་མ་དུས་ཚོད་མི་འདྲ་ན། །དུས་ཚོད་མཚོན་ཆ་རྣོ་བས་གཙོ»): where the day's
  animal sign (*nyi ma*) and the hour differ, the hour leads. Within an
  hour, a work whose side the day animal decided (§5.12, its decider the
  day animal: no stronger voice names it) takes the side the hour's own
  readings give it: that hour's works, its black hour, a rough time
  falling in it and, with a birth date, one's own year's hour. A reading
  that avoids everything turns every such good work it does not name
  good; readings that name a work both ways turn nothing
  (`DaySummary.overruledInHour`). The earth lords of the hour are left out:
  they stand on places, and what they forbid is forbidden their way. The
  hour outweighs the *nyi ma* only, the weakest of the seven; the day's
  weighing and its lists do not change. On 5 January 2026 (a horse day)
  consultations and seeking friends, good by the day animal alone, are to be avoided in the
  rough times and the black hours; on 29 November 2026 fierce rites, which
  it avoids, are good in the ox and tiger hours (`ChineseHoursTest`).
  On the hours panel (§10.3).

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
gone; the screen calls the panel 旧暦. The rokuyō in their present form date
from an almanac of 1747 and rose to weight after 1873, when the luck
annotations were struck from the official almanac and they alone stayed
(Japanese Wikipedia 六曜; ROADMAP K4): the six's family note says so.

**What the page is for** (the owner, 2026-10-12 and 2026-10-14): an
almanac of use to a present-day practitioner of onmyōdō, Shinto or
Shingon, ideally to all three, on the old rules and readings — as the
Tibetan page is the White Beryl's almanac. The Shingon practitioner's
reckoning is much nearer the Tibetan page's than an onmyōji's or a Shinto
priest's, so that reader is served by the Tibetan page at least (the
owner, 2026-10-14); the 旧暦 page is shaped for the other two. Its
**frame** is the set of
annotations the traditional almanac printed in Japan today carries (the
神宮館高島暦 and its kind: 干支, 九星, 六曜, 十二直, 二十八宿, 下段, 選日,
the year's direction gods), because that is what a practitioner reads
today and because the annotations that ever existed are far more than any
living practice; the calendar the shrines hand out with the 神宮大麻, the
神宮暦, carries none of them on purpose. Within that frame the old texts
(the expert's advice: the 暦林問答集 before the 簠簋内伝) give the readings
and correct the digests; an annotation the almanac has dropped is not
revived from them (the expert, 2026-10-14; [sources/kyureki.md](sources/kyureki.md),
*Her second answer*). The rules below come from modern digests of the old
rules; which old text speaks first where they differ is ROADMAP K3.

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
fact ("Of the ten, it is among the three good"); no reading gives a view
other than its main one as "another view" (the owner, 2026-10-16: to a
Western reader an alternative reads as uncertainty, not as a call to
act), and where a tradition's own entries are what a row shows (Pukton's
dates, the Vajra Treatise's days) they are entries of their own. About & sources (§10.5) lists the sources of all
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
| MIT, Edward Henning's Tibetan calendar software (TCG 1.06), notice kept in `Planets.kt` and named in About | the five planets' and Rāhu's arithmetic, ported from his t2.c and t3.c (§5.11) |
| CC BY-SA 4.0 | wording adapted from Japanese Wikipedia (選日, 暦注下段, 九星, 九星気学, 歳徳神, 庚申待) |

No text under a non-commercial or no-derivatives licence: F-Droid labels an
app containing one with the *Non-Free Assets* anti-feature.

**The White Beryl counts above every other source** (decided by the owner,
2026-10-08): where another source disagrees with it, WB decides; where WB
is silent, the other may stand, named as its own. One text counts above
it on medicine (owner, 2026-10-15): the Four Tantras (རྒྱུད་བཞི, rgyud
bzhi). Where another source differs from WB on a medical point, what it
rests on is traced first; a difference that comes from the Four Tantras
is followed, any other loses to WB (the la's course by date, ROADMAP
D14). **Witnesses are not
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

**The shape of a reading** (the owner, 2026-10-11; ROADMAP X1). Every
reading written from 2026-10-11 on follows the rules below; the readings
built before them are brought to the same shape family by family (ROADMAP
X3–X10). The rules came from a reading of all 459 of the Tibetan page's
readings: each was one paragraph in its source's order, so the ruling
came last, facts the day cannot use stood in the main text, the strongest
voice had the thinnest text, and no two families said "good" or "avoid"
the same way.

1. **The reader's order, not the source's.** A sheet runs: the title and
   gloss (the app's), the paragraph, the Good-for and Avoid lists, the
   rows, then the app's workings (how the entry was counted). A reading
   gives its facts in that order whatever order its source gives them in.
2. **The paragraph is the ruling.** One to three sentences, 20 to 60
   words. The first sentence says what this is on the shown day and which
   way it cuts: "The 1st of the month is a virtuous day, the day of joy
   (དགའ་བ, dga' ba): pacifying rites, crafts, games and all white work go
   well." The sentences after
   it carry only what changes the ruling: the one prohibition the source
   ties to the entry itself ("Hold no funeral on it, even when the Sun is
   strong: it is the door of heaven"), a perilous day's warning, a
   condition ("except on Thursday"). The paragraph never lists works that
   the sheet's lists repeat under it, and never explains a mechanism.
3. **Every other fact is a row**, a label and a value, in this order and
   with these labels and no others: *Also called* · *Strong when* ·
   *Avoid its start* · *Setting out* · *Rank* · *Remedy* · *Spirits that
   harm* · *Illness* · *Rites* · *La today* · *A birth today* · *A death
   today* · *Livestock* · *Planet* · *Foods* · *Through the day* · *How
   it falls* (*Avoid its start*, a yoga's first chutsö, and a mansion's
   *Planet* and *Foods* joined with ROADMAP X6; *Through the day*,
   Rāhu's course over the hours of every day, with X7). A row is a phrase or one sentence;
   it holds names and brackets well, where a sentence does not. The
   omens (*A birth today*, *A death today*, *Livestock*, the planet's
   caste and colour under *Also called*, a mansion's *Planet* and *Foods*)
   stay: they are the source's content, kept as the last rows and never
   in the paragraph (D6). Each row is a catalog entry of its own,
   `<reading>.<row>` (§8.2), shown in the sheet's label column (§10.3);
   its value starts in lower case, as a phrase does, and carries no full
   stop.
4. ***How it falls* gives today's line only.** "The 8th, 18th and 28th
   of the spring months; today the 8th of the first month of spring."
   The whole table, every month's dates, the twelve places of the sky
   dog, the seven weekdays' burning dates, lives in the entry's topic
   file in `docs/sources/`, which About points to; a reading never
   writes a table out as prose.
5. **Boilerplate once.** A sentence that would be the same in every
   reading of a family is the family's note (`reading.<Family>`, filled
   with {0}) or a row's label, never a sentence repeated in each reading:
   the la's seat is a *La today* row, not thirty sentences; Rāhu's course
   through the hours stands once in Rāhu's note.
6. **One vocabulary.** A work in prose "goes well" or "goes badly"; the
   lists say "Good for" and "Avoid"; a whole entry is "to be avoided" or
   "avoided for its whole length"; a day is lucky, unlucky, mixed or
   neutral, as the tone labels say; a remedy is "Remedy:" in the
   imperative ("hoist white silk and burn white butter"), what the person
   does and the rites in one row, since the reader, a ngakpa, does both
   (the owner, 2026-10-16); an alternative tradition is not shown, an
   earth lord's included (§5.13), and no reading says "another view". The
   source's classes of work
   keep their names, since they are terms and not verdicts: white work,
   virtuous work, fierce rites, harsh work. Not used for a ruling:
   auspicious, succeeds, turns out well, shunned, given up, prosper.
7. **A verse's image is marked as an image and never opens.** The
   headword of a verse, "death in prison", "meeting a woman friend",
   "understand it as pounding", goes after the ruling as "its image is
   death in prison"; a phrase whose meaning the source does not give
   ("the mute one bursts") is dropped to the topic file, not shown bare.
   The year of age's pebble verses are omens and stay verses, each with
   its ruling line first and its remedy as a row.
8. **Nothing about the app in a reading.** No "the works below", "the
   lists", "the day in brief", "shown and not weighed", "with a birth date
   set". A reading states what its source says; what the app does with it
   is a note in `strings.xml` under the gloss (`brief_outweighed_note`,
   `brief_avoid_all_note`, `tib_earth_lords_note`, the mansion's
   `tib_mansion_lists_note` on how its lists are joined, the birth
   mansion's `tib_birth_mansion_note` and the pebbles' `pebble_note` on
   what is set against what, by which clock, and what the app leaves
   out, the hours panel's `hours_rough_note` and `hours_over_day_note`).
9. **No editor's notes.** "Not identified", "the print spells", "the two
   rendered word for word", "some say" go to the topic file in
   `docs/sources/`, with the Tibetan, and to ROADMAP's *Left open* table
   if they are questions; a reading shows only what is settled, and a
   recorded alternative stays in the topic file.
10. **Plain sentences.** One fact per sentence, 25 words at most, present
    tense, the day, the spirit or the work as the subject, a verb in
    every sentence: "Setting out goes badly, above all to the south" and
    not "Setting out is bad in general, above all to the south, the
    north and the intermediate directions". No chain of clauses on
    semicolons; a list of three or more things is a row.
11. **Names as §8.1 says, uniform.** Each word named at its first mention
    in each text, the paragraph, each row and the title being texts of
    their own, so the title's bracket does not spare the paragraph's
    (D7); and the same word bracketed the same way everywhere, one English
    name and one Wylie per word across the catalog, the interface and the
    listing. The words the sources themselves name two ways are listed
    with the test (the la weekday, *bla gza'*, both the birth animal's
    luck day and the element's own weekday).
12. **A family reads as one.** Before writing a reading, read its
    family's readings and the comment above them in the catalog, and
    match the first sentence's pattern and the rows present; a family
    whose source gives a fact for every member gives that row in every
    member. The effort goes where the weight is: the combination's
    readings are as full as the earth lords', not thinner.
13. **The Russian is the same shape.** Each key translated keeps the
    paragraph's bound and the rows' order; the row labels and the
    vocabulary of rule 6 have one Russian each, kept in the comment at
    the head of `texts_ru.properties`.

`CatalogTest` holds what of the rules a test can see (ROADMAP X2, built
2026-10-11): every row key one of the seventeen; the Russian with every
row the English has; no paragraph over 60 words (a Tibetan bracket adds
none, a {0} counts one) and none under 20, measured as the sheet shows
it, a family's note filled with the entry's own text; none of rule 8's and 9's words in a reading
("the lists", "the day in brief", "the works below", "not identified",
"word for word", "the print", and their Russian); and rule 11's
uniformity across the catalog, the interface strings and the store
listing (its changelogs, records of past releases, aside): a word whose
namings end in different words is named two ways, the namings that
differ from the most frequent being the odd ones; two words with one
name, or a word named by another word's longer name ("water tormas" for
the torma, where the water torma has its own word), are bracketed two
ways, a check run on English only, since a Russian genitive puts the
head before the name. *bla gza'* is the listed exception. The families
were brought to the shape one ROADMAP item at a time (X3–X10), each
allowed its breaks until its block was built; since the last, block 43
(2026-10-18), every reading holds the shape with no allowance. The 旧暦
page's readings, not in X's review, are outside the checks, but for the
24 solar terms (ROADMAP K6), written in the shape and held to it. The rest of the rules (the ruling
first, one vocabulary, an image marked as one, plain sentences, a family
read as one) the writer checks by hand.
The three examples below are the rule applied to readings as they stood;
the facts are the same, moved. All three stand in the catalog in this
shape since 2026-10-11, their Russian with them: the lesser black day's
*How it falls* row is the line the engine names for the shown day, the
season's three dates and the one that fell (§8.2). It has no *Another
view* row: the earth lords show none (§5.13).

*Lunar date 1*, before: "A day of joy (དགའ་བ, dga' ba), Nandā, one of the
virtuous days: good for pacifying rites, crafts, games and all white
work. A child born today: children follow one another, and wide
learning. A death today is bad. Livestock bought today increases a
thousandfold. The la, the life-spirit (བླ, bla), sits in the big toe
today, and in horses and other livestock in the sole of the foot: no
bloodletting, moxibustion or other treatment there." After: "The 1st of
the month is a virtuous day, the day of joy (དགའ་བ, dga' ba): pacifying
rites, crafts, games and all white work go well." (23 words, the Russian
20: «Первое число месяца — благой день, … идут в этот день хорошо».) *Also called* · Nandā. *La today* · the la,
the life-spirit (བླ, bla), sits in the big toe; in horses and other
livestock, in the sole of the foot; no bloodletting, moxibustion or other
treatment there (the row names the la, as rule 11 asks of each row; the
label is a short name, as the Lunar day section's labels are, §10.3).
*A birth today* · children follow one another, and wide learning. *A
death today* · bad. *Livestock* · bought today, increases a
thousandfold.

*Sunday*, before: 105 words opening "The Sun: the la weekday (བླ་གཟའ, bla
gza') of kings, of the royal caste; peaceful, pale red, of fire." After:
"The Sun's day. Setting out goes badly, above all to the south, the north
and the directions between. Hold no funeral on it, even when the Sun is
strong: it is the door of heaven. With Tuesday and Saturday it is a day
on which virtuous work is avoided." *Strong when* · in the waning half of
the month, by day, and in Leo. *Also called* · the la weekday (བླ་གཟའ, bla
gza') of kings; royal caste; peaceful; pale red; fire. *A birth today* · a
boy who lives through seven days and seven months lives long and is
loved by the great; a girl will be rich, but more girls follow her, so
some hold her birth bad. *A death today* · repeated nine or seven times.

*The lesser black day*, before: 150 words, 90 of them the dates of every
season. After: "The lesser black day, Nyima Nakchung (ཉི་མ་ནག་ཆུང, nyi ma
nag chung), the course of Jama Gunggyal (བྱ་མ་གུང་རྒྱལ, bya ma gung rgyal).
Avoid works for the dead, a bride, consecration, feasts and funeral
rites, and above all sending wealth out of the house." *How it falls* ·
the 8th, 18th and 28th of the spring months; today the 8th of the first
month of spring. *Remedy* · set dough figures of a tiger, a horse, a
sheep and a dog to the south, west, north and east with offerings; or
burn white butter, scatter white earth on the roof and red earth to the
four sides.

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
for running text, `Mansion.ASHVINI.sanskrit` and `MonthNames.8.sanskrit`
for the Sanskrit name of a month, mansion, yoga, karaṇa or lunar-date
class: its IAST in English, its Russian transcription in Russian, §8.1;
the engines keep the IAST as `iast`); `reading.<Enum>.<NAME>` for a reading's summary;
`<reading>.<row>` for each of its rows (§8.1, rule 3; ROADMAP X2), beside
the entry's own text (`reading.LunarDate.1.la`, `reading.Weekday.SUNDAY.strong`;
under a family's note, beside the entry the note's {0} takes,
`reading.NatalTrigram.LI.spirits`; for a group's shared rows, beside a key
the readings name in `Reading.alsoRows`, `reading.NineMultiple.remedy`),
`<row>` one of `also_called`, `strong`, `setting_out`, `rank`, `remedy`,
`spirits`, `illness`, `rites`, `la`, `birth`, `death`, `livestock`,
`avoid_start`, `planet`, `foods`, `through_day`, `falls`, each label a
term of its own (`ReadingRow.LA`, "La today"), and a row filled as its
summary is, {0} taking the same text. A course by date's *How it falls*
row has no catalog entry: it is the line the engine names for the shown
day (`EarthLordCourses.fall`, a `Fall`, ROADMAP X7), in one of four
forms, a `CourseFall` for a course with one date in each season-month
("the 8th, 18th and 28th of the spring months; today the 8th of the
first month of spring"), a `MonthFall` for one with its own dates in
the month ("the 18th and 28th of the last month of spring; today the
18th", "the 13th of the middle month of summer, which is today", "every
date of the last month of winter but the 27th; today the 15th"), an
`AnimalFall` for one by the date's animal ("the dragon and sheep days of
the first month of spring; today a day of the sheep") and an
`EveryMonthFall` for the sky doors ("the 2nd, 12th and 22nd of every
month; today the 12th"), with the hour, time of day or way it goes where
the source gives one (", at the snake hour", `CourseAt.*`), written from
`CourseFall.*`, `CourseAt.*`, `Animal.<NAME>.of` and
`DateOrdinal.<one|two|few|other>`; where one course does two things on
a date (the nyen moving and turning back) the row has a line for each,
under what it does (`CourseFall.event.*`). The great black day, Rāhu's
course among the earth lords and his course by month take the same
lines; a black month's line is its reading's own row, under its third
(`reading.EarthLord.ZLA_NAG.<UPPER|MIDDLE|LOWER>.falls`) or its year
(`…KI_KANG_ZLA_NAG.<animal>.falls`), and the eclipses', the seasonal
signs' and the comet's are the rule that marks them; `wording.<key>` for each wording of the lists. Readings that share a sentence
keep it once, as a pattern whose `{0}` takes the entry (`reading.KyuSei`,
`reading.Haircut.GOOD` and `.AVOID`, a good date's and one to avoid). A translation may be partial, since Weblate commits
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
совпадение for the relations, камни for the pebbles. One Russian word
stands for one term throughout (the read-through of 2026-10-10): лунное
созвездие for a Tibetan mansion and лунная стоянка only for the 旧暦's
宿, владыки земли for *sa bdag*, мева for *sme ba* with its numbers
named as in the year of life («семь красных»), Вишти for the karaṇa,
мытьё головы for washing the hair. The 旧暦's terms take the name the
published Russian Japanology and Sinology give them where one is found
(ROADMAP K1, the table in `docs/sources/kyureki.md`): 神無月 месяц без
богов, 閏月 вставной месяц (only a year is високосный), the 節気 as the
dictionary and the sinologists' tables name them (дожди для злаков, малая полнота,
окончание жары, малые и большие снега), 縁日 храмовые праздники, 人日の節句
Дзиндзицу; a term no work names keeps the project's. A translated reading
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

Clothes are two acts, as the White Beryl's chapter 34 and the print's boxes
keep them: making new clothes (cutting, sewing: གོས་དྲ, gos dra; 和洋裁,
裁衣) and their first wearing (གོས་གྱོན, gos gyon; 着初め). The verses'
compound «དྲ་གྱོན» (dra gyon), "cutting and wearing", and a bare "clothes"
name both. Sewing tents is a third, beside pitching them.

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
| Element pairs, observances, festivals, personal days | Edition Rabten, *Tibetan Calendar 2026*; Henning's archive and symbolic details; the personal days with the White Beryl, vol. 2, p. 330 (BDRC MW2CZ8040) |
| Personal mansions | The White Beryl, vol. 2, p. 330 (BDRC MW2CZ8040), with the Sakya *'bras rtsis rab gsal nor bu'i me long*, p. 64 (BDRC MW29978_8B19DD) |
| Own days by the birth date | The White Beryl, vol. 2, pp. 312, 330, 337–338 and 345–346 (BDRC MW2CZ8040); the birth mansion after Phug pa Lhun grub rgya mtsho's commentary on the *dbyangs 'char*, ch. 3 (BDRC MW1NLM5184) |
| Lunar mansions, activity lists | Henning, *Horary and electional astrology of the five components*, after the White Beryl, the *Treasury of Jewels* and the *'bras rtsis bai dkar dgongs don kun phan me long*; his doubled mansions read on that print (BDRC W4CZ65561); the White Beryl's seven classes of mansions, vol. 2, pp. 328–329, and its chapter 34, the works one by one, pp. 378–428 (BDRC MW2CZ8040) |
| Lunar dates, weekdays, yogas, karaṇas | The White Beryl, ch. 33, Beijing 1996, vol. 2, pp. 297–304, 308–312 and 347–351 (BDRC MW2CZ8040) |
| Trigram (the eight goddesses of the date) | The White Beryl, ch. 25, Beijing 1996, vol. 1, pp. 449–450 (BDRC MW2CZ8040) |
| Combinations of weekday and mansion, special days | The White Beryl, Beijing 1996, vol. 2, pp. 331–337, 341 and 342 (the *Rdo rje gtsug lag*'s special days), with the table in vol. 1, pp. 148–149, and the almanac verse of vol. 1, p. 177, a second witness to seven kinds of special day (BDRC MW2CZ8040) |
| Rāhu's course | The White Beryl, Beijing 1996, vol. 2, pp. 236–238 (BDRC MW2CZ8040); the *kun phan me long*'s chart of the general course, img. 78 (BDRC MW4CZ65561) |
| Earth lords of the date's animal | The White Beryl, Beijing 1996, vol. 2, pp. 224–226 (BDRC MW2CZ8040) |
| The great black day, the meeting of the nine bad | The White Beryl, Beijing 1996, vol. 2, p. 226 (BDRC MW2CZ8040) |
| The month heading's entries: long or short, the weekday's rise, the black months, the seasonal signs, the comet | The White Beryl, Beijing 1996, vol. 1, pp. 176, 183 and 186–189, and vol. 2, pp. 212, 312 and 359 (BDRC MW2CZ8040); the comet's years after the *Bod kyi rtsis rig kun 'dus chen mo*, vol. 2, p. 145 (BDRC MW28845) |
| The eclipses, by the *byed rtsis* | The White Beryl, Beijing 1996, vol. 1, ch. 4, pp. 31–32, and ch. 9, pp. 59, 62 and 65 (BDRC MW2CZ8040); checked against Jean Meeus, *Astronomical Algorithms*, 2nd ed., ch. 54 |
| The letters of the day: the Kālacakra's and the *dbyangs 'char*'s vowels and consonants, the stage, the twelve links, the hundred feet | The White Beryl, Beijing 1996, vol. 1, pp. 16–19, 97–98, 150–153 and 173–178, with the Zhol print (BDRC MW1KG1617) for the table and the model almanac (BDRC MW2CZ8040) |
| The earth lords that move by date, Rāhu's among them | The White Beryl, Beijing 1996, vol. 2, pp. 226–235, with the results pp. 364 and 368–369, and the model almanac, vol. 1, pp. 154–171 (BDRC MW2CZ8040) |
| The element's mansions and grave mansions | The White Beryl, Beijing 1996, vol. 2, p. 330 and the tables pp. 345–346, with vol. 1, p. 232 for Abhijit (BDRC MW2CZ8040) |
| The la's element, course and the illness of the day; the year of age on the seven-red | The White Beryl, Beijing 1996, vol. 1, pp. 253 and 403–404, vol. 2, pp. 297–304, 308–312 and 351–356 (BDRC MW2CZ8040) |
| The humours and the seasons (the Health screen's Medicine tab) | The Four Tantras (རྒྱུད་བཞི, rgyud bzhi), the Serthang print, Root Tantra ch. 3 and 6, Explanatory Tantra ch. 14 (BDRC MW3CN5036) |
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
| `meeus-eclipses.tsv` | Every lunar (penumbral or deeper) and solar eclipse of 2000–2049, the UT date of greatest eclipse, the umbral magnitude or gamma; `EclipsesTest` holds WB's eclipses to it | Meeus, *Astronomical Algorithms*, ch. 54, computed by `tools/eclipse_vectors.py` |
| *(in `ByedRtsisTest`)* | The *byed rtsis*'s month figures at its epoch and their monthly motions; its lag behind the true reckoning at the epoch, 0;13,13 of the weekday, the correction WB gives from the *Zhal lung* (vol. 1, p. 32) | WB vol. 1, ch. 4 |

## 10. User interface

Settled in the design review of 2026-09-28. A clickable mockup of these
decisions, with calculated data for 20 Sep – 15 Nov 2026, is on the design
canvas "Zanshin Calendar — basic design".

### 10.1 Principles

- **UX comes first.** The console look of the original draft is dropped where
  it hurts reading or touch; nothing in the UI has to look like a terminal.
- **Themes** (ROADMAP A1, built 2026-10-15): **Ink**, the dark palette of
  1.0 and the default for every install, so that no one's app changes
  colour on the update (D5); **Paper**, a warm off-white page with dark
  ink; **Night**, Ink on a black page for OLED screens; **Maroon**, the
  robe's maroon with saffron, dark; **Sepia**, a parchment page with
  less blue in it than Paper's and brown ink, light, for the evening;
  and **System**, Ink or Paper as the phone is set to dark or light
  (§10.5). Maroon carries an accent pair and a tone set of its own:
  vermilion and the bad tone sit beside maroon in hue, so on it the 旧暦
  accent and the bad tone are turned towards orange and lightened,
  saffron a brighter gold, each at least 25° of CIELAB hue from the page
  as well as apart from it in lightness. Every colour is a role of the theme (`ZanshinColors`
  in `ui/Theme.kt`: the page, the sheets, the raised and selected cells,
  lines, text, muted and faint text, the off state, the two accents, the
  three tones, the scrim, and the colour set on an accent fill), read
  through `Palette`, which holds the theme drawn now as a snapshot state,
  so that composables, Canvas drawing and plain functions alike redraw
  when it changes; only the theme and the printed colours (§10.7) write a
  colour value (`PaletteTest`). Each theme holds text and muted text at
  4.5:1 on the page, the sheets and the raised cells, the tones and the
  accents at 4.5:1 where they colour text, faint text and every dot and
  ring at 3:1 (WCAG 2.1, 1.4.3 and 1.4.11), and good, mixed and bad
  apart in lightness as well as hue. The window is drawn in the theme's
  page before Compose (`themes.xml`, a style per theme, Maroon's on
  Ink's, Sepia's on Paper's, System's by `values-night`), the status and navigation bar icons dark on a light
  page, and from Android 13 the splash of the next launch takes the
  stored theme (`setSplashScreenTheme`); on Android 12 the splash stays
  Ink. The bell's notification stays the system's. Material You's
  wallpaper colours are not offered: the tones and the printed colours
  are chosen against a known page.
- **Any font scale, any width** (ROADMAP A2, built 2026-10-16). The app
  follows the phone's font scale with nothing clipped or overlapping, checked
  at 1.0, 1.3, 1.5 and 2.0, in landscape and on a tablet of 800 dp, in
  English and Russian (`ui/Fit.kt`). No interface text is set below 12 sp.
  A label column is as wide as its widest label, 120 dp at least and at most
  40 % of the row, never narrower than its longest word (up to half the row), so a label wraps
  between words and never inside one. The header's date drops its year, then
  its weekday, where the whole date does not fit beside the calendar toggle
  (`pattern_header_date_no_year`, then `pattern_short_date`); where even the
  short date has no room, the date goes on a line of its own under the menu.
  The sky line moves the noon's altitude into its balloon ("Sun at noon")
  where the three times would not fit with it. The hours' short form is set
  smaller, never broken over two lines (§10.3), and a grid cell, a tab or a
  title row grows taller rather than clip its text; tabs that do not fit as
  equal segments take each its name's width and scroll sideways. From 600 dp
  (a phone in landscape, a tablet) a day page stands in two columns, each
  scrolling on its own: the headline, the hours and the brief on the left,
  the sections on the right, together at most 1040 dp wide and centred; a
  screen of its own (the election, meditation, health, the reckonings,
  location, about) is at most 640 dp wide and centred. A ring, compass or
  board in a reading sheet grows with the font scale, or with the room on a
  tablet, up to 360 dp or the sheet's width, its strokes and labels with it,
  so that a label keeps its place: its labels grow with the drawing and
  never past the font scale, its caption is read at the font scale. The
  hours' dial, which fills the sheet already, is drawn up to 360 dp with its
  names at that scale, and the combination table's 27 columns keep their
  figures' size on a phone. The tables and grids laid out by their text are
  not scaled. The dial and the boards keep their tap targets.
- **One calendar at a time.** The screen shows either the Tibetan or the 旧暦
  view, never both.
- **Details in balloons.** Secondary facts open in a balloon (popover) when the
  element they belong to is tapped, and close on tapping outside it.
- **Every term is translated.** Each kanji opens a balloon with its English
  and reading; these carry no dotted underline. A Tibetan term shows as its
  English name with its script and Wylie in brackets (§8.1), in rows and
  labels as in running text (the Lunar day section's row labels excepted,
  §10.3: short names, their brackets in what the row opens); tapping it opens a balloon with how it is
  said, and screen readers read the pronunciation (§10.3).
- **Readings on demand.** Annotations are listed as rows with a lucky/unlucky
  mark; tapping one opens a sheet with its reading, "good for" and "avoid",
  its rows and its workings (§10.3, *The sheet*); the sources are listed in
  About & sources, not on the sheet (§8.1).
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
- **Switching calendars:** two entries at the top of the side menu, and in
  the header a toggle of two segments, "Tibetan | 旧暦" (ROADMAP U15, built
  2026-10-11): the calendar shown filled in its accent, saffron or
  vermilion, the other outlined in its own; a tap on the other switches.
  Screen readers get the two as tabs, the shown one selected. Until U15 the
  header held a pill naming the calendar shown with a swap icon, which read
  either as "this is Tibetan" or as "go to Tibetan".
- **Switching people:** once two or more people are saved (§10.5), the
  header holds a ring with the chosen person's initials (the person glyph
  for a name left blank or for no one), between the date and the calendar's
  name; a tap opens the list of people, and a tap there switches.
- **Tapping the Gregorian date** in the header opens the date picker, limited
  to 1900–2100. Under each day it shows the day number in the calendar being
  viewed, a dot on holidays and festivals and, in the 旧暦 view, the day marks
  of §10.4, with a legend whose kanji translate on tap.
  **The jump** (ROADMAP U9, built 2026-10-11): the month's title, with a
  small chevron after it, opens the years 1900–2100 as a grid of four
  columns that scrolls, opened with the year shown in its third row; the
  year shown is filled in the calendar's accent and this year outlined in
  it. A year opens its twelve months, three to a row, marked the same way
  (this month outlined), and a month opens its days. In the months the
  chevrons move a year, in the days a month as before; in the years they
  are hidden, and a tap on the title there goes back to the days. Any
  month of the range is three taps from the days (title, year, month),
  with a scroll of the years between.
- Festivals and holidays carry a line glyph drawn for this app.
- **Local sky line** at the bottom of both calendars (§10.5).
- **First-run line** (ROADMAP U18, built 2026-10-11): under the In brief
  row, on the first three opens of each page, one line that can be put
  away: on the Tibetan page "Tap any row for its reading; the solid dot
  decides the day", on the 旧暦 page "Tap any row for its reading; every
  kanji shows its English on tap". An open is counted once a launch, when
  the page is first shown (turning the phone is not another); the line is
  gone after the third open or a tap on it, a counter per page
  (`first_run_tibetan`, `first_run_kyureki`). Interface text, not a
  reading (§8 does not apply).

### 10.3 Tibetan view

- **Headline:** the day number, with the month under it. On a holiday, the
  holiday's name and glyph take the headline and the date moves under it.
- **Month:** number plus Tibetan name — "4th month · sa ga". Tapping it opens
  a balloon with the other names: Sanskrit lunar mansion, animal name
  (Janson Table 4), and its two seasons, each labelled, as WB's model
  almanac gives them (vol. 1, pp. 154–171): the Kālacakra one (the 1st
  month early spring) and the Chinese reckoning's (the 11th early spring,
  the 1st late spring); then the month heading's own entries
  (§5.11, ROADMAP T2.21): its days, long or short; the planet rising
  roughly, and finely what rises, wanes, declines and moves; the year's
  two black months by animal, with this one's black dates when it is one;
  Kikang's black month when it falls in this month, with its date, time
  and work; the sage's and the pig's seven days from their dates when they
  begin in it; a comet when the count marks the month; then the month's
  element and its vitality and body (§5.9), with a birth date set each with its pebbles
  and relation to yours, as in the year's balloon.
- **Repeated day:** each of the two days carries a small tag, "first of two" /
  "second of two".
- **Skipped day:** the day after the gap carries a note, "day 24 is
  omitted". A holiday moved back by a skipped date says "moved from day N".
- **Leap month:** "Leap 2nd month".
- **Year:** element and animal, "Fire Horse"; the royal year (2153)
  and rabjung cycle ("17th cycle, year 40") in the year's balloon. With a
  person chosen, the balloon ends in "Your 51st year of age →", which opens
  the person's **year of age** (§5.9.1) in the Reckonings, at the year
  shown (§10.11); Back from it returns to the page. It changes once a
  year, so it stays behind the tap (the owner's rule that the page
  repeats nothing). Until R1 (2026-10-15) it was a sheet over the page.
- **Midnight to dawn:** "today" is still the day before until 05:00, as
  §4 says; the date carries a small "from dawn". A page left on today
  follows when the day turns, at 05:00 while the app is open or when it
  comes back; a day browsed to stays.
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
  `DaySummary.hoursAgainst`): on an unlucky day those to be accomplished,
  on a lucky day those to be avoided, consecutive hours joined, on one
  line in short form, the clock hours alone under the In brief line's
  words ("avoid 05–09 · 11–13 · 17–19 · 23–01", "good 09–11 · 13–17 …";
  ROADMAP U11, built 2026-10-11; `HoursShortForm`, which the morning
  notice and the widget share): every period begins on the hour,
  so nothing is lost, and the full times stand in the brief's "By the
  hour". Four runs fit at font scale 1.3 in both languages; the five some
  months give, or a larger font scale, are set smaller rather than broken
  (the size found by measuring again, since Android scales small text more
  than large). Until U11 the full
  times were listed under a longer label ("hours to avoid 05:00–09:00
  11:00–13:00 …"), two to four lines. Nothing is weighed anew, and on the
  person's enemy weekday or death mansion there are none, since WB places
  no hour above the person's day (§5.12). A tap on a time opens the hours
  panel at that hour, a tap anywhere else on the row, the strip included,
  at the present one (2.4.1: a tap on the strip had opened the hour under
  the finger, which read as a random hour);
  screen readers get the present hour and every run's full times, with an
  action for each (`HoursRow`). Until U6 the hours against the tone stood
  on the In brief row, and the panel opened from a clock icon on the
  Almanac's heading.
- **In brief:** under the hours, one row of glyphs, however many works
  the day names: the families of the heaviest works good and of the
  heaviest to avoid, weighed as §5.12 says (§10.7), with the day's tone and, in a few words, what decided it
  ("a lucky day · by the combination", "an unlucky day · by Tuesday",
  the factor named as the weighing found it, §5.12); over each side's glyphs, how many works
  the day names good and to avoid ("good 53", "avoid 36"; §10.7), since the row
  cannot show the proportion and on 11 % of days the lists run against
  the tone (§5.12; decided by the owner, 2026-10-07). On today's page the
  row and its sheet give the works as they stand in the present hour
  (ROADMAP U21; the owner, 2026-10-16), as the haircut row does
  (*Works*, below): every work the combination period of the sign now
  rising names one way takes the period's side, since within its hour
  it outweighs every factor of the day (§5.13), and a work the day names
  nowhere joins the lists; the rest keep the day's weighing, and the
  day's tone and what decided it stay the day's (`DaySummary.atHour`).
  The counts and glyphs follow, the period's works the heaviest, and
  both move on with the clock. Another day's row and sheet, and the
  person's day of every work to avoid, which no hour outweighs, are the
  day's weighing. The row opens the tone with its
  reason and, under it, the rule in a sentence (ROADMAP U6): that each
  work goes to the strongest factor that names it, with how many works
  still stand against the tone ("so 42 works are still good today"), and
  that within its hour the combination period outweighs the day, neither
  said on the person's enemy weekday or death mansion; on today's page
  that sentence names the hour ("Now, until 17:00, Pisces rises, and its
  combination period outweighs the day: it decides 46 works, 37 good and
  9 to avoid, marked “by the hour” below"), the count against the tone
  staying the day's; then the "By the
  hour" block (below), before the works, and the works in the 旧暦
  brief's row (ROADMAP U10, built 2026-10-11): one row each, the family's
  glyph, the work, and after it, right-aligned in muted 13 sp, the voices
  that carry it (§5.12), each tapped for its kind ("weekday"); a work
  opens a balloon with its side and each voice with its kind ("weekday ·
  Sunday") and "Choose a day for it", which opens the election for that
  work from the shown day (§10.8); nothing outweighed is shown. A work
  the present hour decides comes first on its side, its one voice "by
  the hour · Pisces", tapped for its kind ("combination period"), and
  its balloon gives the sign and when its period ends ("Pisces rising,
  until 17:00"); the day's voices it outweighs are not shown. Until
  U10 the works stood in groups under their voices, the voices in
  semi-bold, which took the eye from the works. With a birth date set, a "For
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
  factor of the day (WB vol. 2, p. 376), that an hour's own readings
  outweigh only the day's animal sign, whose works the hours panel names
  (§5.13), and that the nectar periods are not weighed (`DayHours`).
- **Works** (ROADMAP U6, built 2026-10-09): between the brief and the
  Almanac, the verdicts on single works, which are results of the
  weighing and not voices in it: the haircut, weighed as §5.12 weighs every work: its
  dot is the side the brief gives haircuts and its subtitle names the
  factor that decides ("avoid · by Tuesday"; KP box 52a names every weekday,
  so on every day of 2000–2049 it is the weekday). On today's page the
  row holds for the moment, not the day (the owner, 2026-10-15): within its
  hour the combination period outweighs every factor of the day (§5.13),
  and WB's chapter 34 names haircuts good while Aries, Taurus, Gemini, Leo,
  Virgo or Pisces rises and bad under Cancer, Libra, Scorpio and Capricorn,
  so the dot is the side in the hour now and the subtitle says until when
  and what decides ("good until 23:00 · by Pisces rising"); in the hours of
  Sagittarius and Aquarius, which name no haircuts, the day's weighing
  stands, its decider named as before ("… · by Thursday"). On another day, and on the
  person's day of every work to avoid (§5.12), which no hour outweighs, the
  row is the day's weighing (`DaySummary.inHours`, reading
  `Election.hours`). Its sheet says so, gives the good hours and the hours
  to avoid for haircuts on the day,
  shows the days of the Tibetan month with the side the weighing gives
  haircuts on each, and gives FPMT's day for the date as one of the date's
  lists, marked outweighed on the days it is, by the hour's decider on
  today's page (decided by the owner, 2026-10-06). It is FPMT's, not the White Beryl's: WB has no haircut
  results by date, and its thirty hair dates (p. 404) are for washing the
  hair, shown in the lunar date's sheet as a "Washing the hair" detail
  (ROADMAP T2.15, [sources/hair-dates.md](sources/hair-dates.md)). Until
  U6 the haircut was the Almanac's last row, the one row whose dot was a
  verdict among rows whose dots are the voices' tones.
- **Almanac:** what is weighed (§5.12, *Order on the page*). The monthly
  observance; the festival is the headline and opens its reading from
  there, so the Almanac does not repeat it. On the day of an eclipse
  (§5.11) a "Lunar eclipse" or "Solar eclipse" row follows, neutral, its
  subtitle WB's multiplied merit, opening its reading. Then the day's voices in the
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
  other row's dot is an outline (10 dp with a 2 dp ring, ROADMAP U12, built
  2026-10-11; 8 dp with a 1.5 dp ring until then, which hardly showed on
  the ink page), "not deciding today" to a screen reader,
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
  and red lightened, so that a thin glyph reads on the dark page; on a
  light theme, Paper or Sepia, a darker set of the same hues, iron a
  slate grey for white, each apart in lightness from the tones; each
  set holds 3:1 on every page and sheet of its themes), its
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
  are not taken for one. The section's labels are short names on one line,
  with no Tibetan bracket ("Date's animal", "Date's mewa", "Twelve
  links"; decided by the owner, 2026-10-10): each word is named as §8.1
  writes it where its row leads, the date's animal in its sheet's
  "Tibetan" line, the sme ba in its sheet's title, the link as its
  value, so that the label column holds no long terms. A plain value sits
  on its label's baseline. The date's sme ba opens its reading, how the
  almanac counts it, with what the activity lists name its number good
  or bad for; "Twelve links" the day's link as a Tibetan term, its sheet
  counting the day from the middle term ("day 4 of 31 from the middle
  term of month 8") under the title, then the link's own reading, its
  lists and its rows (§5.11), no row on a day beyond the table. Not shown (ROADMAP T2.25, the owner's rule that nothing
  is shown that can neither be used nor change a weight or verdict): the
  Kālacakra's and the *dbyangs 'char*'s letters, the moon's foot and the
  wheel's cells, and the sun's terms, which the links and the
  solstice-counted sme ba still count from.
- **The hundred feet** (§5.11, ROADMAP T2.25): in "Your day", with a
  birth date set, a row "Hundred feet (རྐང་བརྒྱ, rkang brgya)" on the
  days a fang is on the birth mansion or malefics hold it, the place of
  work and the 23rd at once; its second line names the bodies ("Sun"),
  its sheet the day's result as its reading (ROADMAP X8: the result first,
  the family's note naming the malefics and benefics, the wheel as the
  *How it falls* row), the birth mansion, the fangs on it and the holders
  struck, each with the bodies and what a malefic there brings ("Mars:
  epidemics and grave illness", `Holder.<NAME>.struck`). The holders alone
  raise no row: they are struck on 68% of days in 2020–2029, for two
  weeks at a time. In "Also today", the spear or great spear on its day,
  unlucky, its second line the bodies, its sheet the reading with every
  work and a bride to avoid, then what each body's spear brings a bride,
  one sentence per body (`Texts.spear`; never more than three bodies in
  1950–2100), the *Setting out* and *A birth today* rows, the face-on's
  bodies and the moon's mansion. Both shown, not weighed.
- **Short rows** (the owner, 2026-10-10: "do not bloat the screen"): a
  row's second line says only what it is; the rest is in its sheet. The
  *bla mkhyen* row gives the direction alone (its sme ba in the sheet),
  the earth lords' courses row the courses' names ("Namkyi Nakpo · The
  sky door"), what each does today in the sheet. A second line states the
  fact, never what its sheet holds (ROADMAP U13, built 2026-10-11): Rāhu's
  course by date gives the course as its compass draws it ("south-west →
  north-east", on the 14th "from the sky into the lake", on the 30th
  "every direction"; whether the course is the detailed or the general is
  in the compass's caption), a special day what makes it ("Sunday +
  Hasta", the Vajra Treatise's days too, whose reckoning their reading
  names), the burning date its weekday and date ("Tuesday + Lunar date
  3"). Until U13 they read "date 1: where Rāhu moves, and what the day is
  good for", "special day of weekday and mansion: Sunday + Hasta" and
  "burning date of weekday and date: …". The kind-and-gloss lines stay
  ("lunar mansion: the hand", "karaṇa: unpleasant"; on the 旧暦 page
  "naru — completion · twelve stations"): the gloss is the fact.
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
  of one's six) and the birth mansion, whose sheet says under the gloss
  that it is the almanac's mansion of the birth date, the hour of birth
  not asked (`tib_birth_mansion_note`); the hundred feet. Last a row
  "Health" that opens the Health screen on the day (§10.10), where the
  vitality and body of the date stand since ROADMAP H1 (2026-10-15).
- **Also today:** after Your day, what the day holds that the weighing
  does not count: the *bla mkhyen*'s direction (§5.11), with its compass,
  and on its dates the great black day (§5.11), its title the meeting of
  the nine bad on 11/7; then the earth lords' courses (§5.13), one row
  whose subtitle names each course on the day with its event ("The nāgas:
  turning", "The sky door: war's door"), the sheet giving each course's
  reading in turn; the black month and Kikang's among them, the black
  month's subtitle its black dates, Kikang's its work. Then, on their seven
  days, the sage's and the pig's seven days (§5.11), "day 3 of 7", the
  pig's unlucky.
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
  hidden earth lord and the sky
  dog (§5.13), and with a birth date vitality and body. Among them the
  hour's own readings (§5.13): "What the hour is for", a rough time with
  its moment ("the sun's rays gathering, 16:59") where one falls in the
  hour, "Your own year's hour" with a birth date, and "Against the day's
  animal sign" where the hour turns works the day animal alone decided
  ("To avoid in this hour: consultations"), its details giving the works
  on each side and its note (`hours_over_day_note`) how far the hour
  reaches; the rough time's note (`hours_rough_note`) says its moment is
  the sun's at the user's place. Each
  opens its reading as the day's rows do. The ring keeps the period's
  verdict; an unlucky arc inside the rings marks Viṣṭi's span (§5.8).
- **The sheet** (SPEC §8.1, *The shape of a reading*; ROADMAP X1, X2,
  built 2026-10-11): a row opens its reading in a bottom sheet, a band in
  its tone at the top, and the sheet runs in the reader's order: the title
  and the gloss with the tone, under them the decides note or the
  outweighed note (`brief_outweighed_note`), the entry's own note where
  it has one (the mansion's, how its lists are joined,
  `tib_mansion_lists_note`), and the "Tibetan" line, a
  diagram where the entry has one; then the paragraph, the ruling; the
  "Good for" and "Avoid" lists; the reading's rows in their fixed order,
  *Also called* · *Strong when* · *Avoid its start* · *Setting out* ·
  *Rank* · *Remedy* · *Spirits that harm* · *Illness* · *Rites* · *La
  today* · *A birth today* · *A death today* · *Livestock* · *Planet* ·
  *Foods* · *Through the day* · *How it falls*, each
  shown only where the catalog has it; and last the app's workings rows,
  how the entry was counted (the hair date, Viṣṭi's span, the pebbles'
  count). Rows and workings share one label column, as wide as the
  longest of the seventeen labels in the shown language on one line at the
  phone's font scale, never under 112 dp and at most half the
  sheet, where a longer workings label wraps; the "Tibetan" line keeps the
  same column. A sheet of several parts (the combination, the special
  days, Your weekday) gives each part's reading in this order in turn.
  Until X2 the workings were the only rows, in a column of 112 dp, and a
  reading's facts stood in its paragraph.
- **Sme ba in its colour:** the lunar day's number carries the square of the
  nine numbers, each box in the colour it is printed in and today's marked
  (Berzin, *Details of Tibetan Astrology 4*: colours, and the arrangement with
  9 at the top, south, and 1 at the bottom, north). No whole day is coloured.

### 10.4 旧暦 view

- **Date:** in English — the day number as the headline, "8th month" under it
  ("8th month, day 18" for screen readers). Tapping the month opens a balloon
  with its traditional name (葉月 Hazuki). Tapping the day number or the
  moon opens a balloon of facts (ROADMAP K6): the moon's age at noon JST
  as the printed almanac gives it beside the date (月齢, days since the
  last new moon at or before noon, `Kyureki.moonAge`), the phase, the
  month's length (大, 30 days, or 小, 29) and the Gregorian date.
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
  almanac, whose red writing (朱書き) in the 具注暦 is the weekday in the
  upper band, 密 on Sundays (作花一志, 曜日の起源と具注暦, of the 具注暦 of
  1210; ROADMAP K4),
  and on modern Japanese calendars a red date means a Sunday or holiday. The
  lucky and unlucky colours of the rows are an app convention.
- **In brief:** a line under the rokuyō (and under the day mark) shows the
  glyphs of the activity families the day's annotations name good and to
  avoid (§10.7), a family in the mixed colour when one of its activities is
  named both ways, each side under its word and count as on the Tibetan
  page ("good 10", "avoid 17"), eight glyphs at most (ROADMAP U16, built
  2026-10-11); screen readers hear the counts. It opens the breakdown. The breakdown lists each activity
  with the annotations that name it, the annotations by tone, the day mark, and
  for the owner the 三箇の悪日 and the 九星気学 relation, and the annotations
  set aside by the lower band's rules (§7.5) under the one that sets them
  aside. It is a listing, never a verdict: no published rule ranks one kind of
  annotation above another (下段 over 中段, 二十八宿 over 十二直), so the kinds
  are not weighed, and where annotations disagree both sides are shown. A row
  set aside says so ("set aside"), and its reading names what set it aside; a
  heavier 歳下食 names the bad days that make it so.
- **Solar term:** always shown — "秋分 · until 8 Oct"; highlighted
  on the day a new one begins. The box opens the term's sheet (ROADMAP
  K6, the owner's rule of 2026-10-14 that every row of this page opens a
  reading or a balloon of facts, as on the Tibetan page) with the three
  parts a Tibetan sheet has. *The reading* (`reading.SolarTerm.*`, in the
  shape of §8.1): what the term marks, glossed after the こよみ便覧 (1787)
  and the NAOJ, the solar month a 節 opens or the lunar month a 中 names,
  and what hangs on it (土用, 彼岸, 社日, the 雑節 in its span, the day
  stars' turn at the solstices, the year's turn at 立春); its *Also
  called* row the month's name (正月節). *The details*, computed: 節 or
  中, the Sun's longitude, the day it began and the day it ends, today's
  day of its span, the solar month it opens or falls in, the next term
  and its date. *The ring* (`TermRingDetail`): the 24 at sheet size, 冬至
  at the top, each named in kanji, the 節 on heavy lines (where the solar
  months begin) and in the text colour, the 中 muted, the term marked and
  today's place in it, the 土用 and 彼岸 arcs named; a tapped term is
  named below with what it does.
- **Moon phase:** a phase glyph next to the date.
- **暦注:** in the order of a printed almanac — the middle band (中段, the
  十二直 alone), the 二十八宿, the lower band (暦注下段), the selected days
  (選日), the deity days (縁日), the 雑節, then 干支 of day and year, the
  solar month, month and year stars, the day star and the 恵方. Each 干支
  row opens a balloon (ROADMAP K6): the stem with its element and
  polarity, the branch with its animal, the pair's place in the sixty;
  the solar month's balloon its branch, its first and last day and what
  is counted by it (the twelve stations, the month star, the three bad
  days). The month and year stars open their own 九星 sheets, the board
  set to that star, the gloss saying month or year. The day star sits with the other 九星: it is
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
  calendars, Choose a day and Health), **Reckonings** (the element
  reckoning, §10.11), **Practice** (Meditation, §10.9),
  **Settings** (location, people, nine-star reading, language, theme,
  morning notice), and
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
- **Relatives** (ROADMAP P1, built 2026-10-15): each saved person keeps
  their own tree of relatives, which does not count toward the ten (the
  owner, 2026-10-15: each of the ten is a user of the app with their own
  tree). The person's dialog has a Relatives row ("None entered", "3
  relatives") opening the tree: each relative indented under the one
  they are entered under, named from the person out ("Mother's father",
  «Отец матери»), with their name, birth, death and "in the household",
  a plus to enter a relative of theirs and a pencil to change them; at
  the foot, "Add a relative" of the person. A relative is a mother or a
  father (once each under anyone), a spouse, a child or a brother or
  sister, or, under the person only, a member of the household who is
  not kin. Each has a name that may stay blank, a gender (a mother's
  and a father's by the relation), the birth, living or died with the
  death, and whether they live in the person's household. A birth or a
  death is a year typed, with the Tibetan year it stands for beside it,
  or a day from the calendar and then, if known, the hour from the
  clock; a year alone counts as the Tibetan year that begins in it, a
  day as the Tibetan year it falls in. A birth year or a year of death
  is needed to save; years from 1800. Deleting a relative deletes those
  entered under them, as the dialog says. At most 60 relatives a person.
  No clan: only some families have one (the owner, 2026-10-15), so the
  reckonings that ask for it (ch. 22, 23, 29) go without. What the
  readings ask of each relative is in ROADMAP P1 (RD13–RD16); the year
  of age reads them since P2 (§5.9.1). Stored as the `relatives` preference, one relative per
  line, by tabs: the person's index, the relative's id, the id they are
  entered under (`-` for the person), the relation (`MOTHER`, `FATHER`,
  `SPOUSE`, `CHILD`, `SIBLING`, `HOUSEHOLD`), the name, the gender (`m`,
  `f` or empty), the birth (`1950`, `1948-11-02` or
  `1999-03-04T06:30`, or empty), the death (empty while living, `d`
  for a death not dated, `d` and the moment) and `h` in the household.
  A list saved before reads as people without relatives; a line whose
  line of descent no longer reaches the person is dropped with its
  branch (`RelativesTest`). When the relatives close, the person's
  dialog takes the focus off its name field, so the keyboard does not
  come back over Save.
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
- **Theme** (ROADMAP A1): a dialog of the themes of §10.1, System, Ink,
  Paper, Night, Maroon and Sepia, each with its name, a line on what it is and a swatch
  of its page with a line of its text and its good, mixed and bad dots
  (System's split, Ink and Paper); a tap applies the theme at once, the
  dialog staying so that they can be compared, and Done closes it. The
  menu row names the theme. Stored as the `theme` preference (the
  theme's name), Ink when unset; System follows the phone's dark setting
  as it changes.
- **Nine-star reading**, a switch, off by default: adds the 九星気学 row of
  §10.4. Switching it on with no one chosen asks for a person (the list, or
  adding one when none is saved); cancelling leaves it off.
- **Morning notice** (ROADMAP N1, built 2026-10-16), a switch, off by
  default, its subtitle the next notice ("Next notice on Saturday at
  06:00", from the bell's wordings), or while off "The day in brief,
  once a morning". While on, a row "Time of the notice" shows the time,
  06:00 at first, and opens the bell's time picker (§10.9), to the
  minute. Switching it on asks for the notification permission on
  Android 13 and later; without it the notice is posted unseen. At the
  time, by the phone's clock, every day, one silent notification for the
  calendar last viewed, on today's page as that calendar counts it
  (§4), for the person chosen, in the brief's own words (§10.7), the
  civil date as its subtext:
  - Tibetan: the day's tone and reason as the title ("A lucky day · by
    the combination"); then the counts of the In brief line ("good 79 ·
    avoid 14") and the hours against the tone in the hours row's short
    form (ROADMAP U11), under the words the row leaves to its heading,
    "hours to avoid 05–09 · 11–13 · 17–19 · 23–01" or "good hours …";
    on the person's enemy weekday or death mansion "Every work to
    avoid, for you" and the avoid count, with no hours (§5.12).
  - 旧暦: the 六曜 as the title ("大安 Taian — great peace"); the day
    mark where there is one ("受死日 black day — the worst of all"),
    then the counts.

  The channel ("Morning notice") is of low importance: in the shade and
  the status bar, no sound, no vibration, no heads-up. A tap opens the
  app on today's page of that calendar, from whatever screen was shown.
  The alarm is one exact alarm, set again after each notice and with
  the bell's (§10.9); a launch sets it again too, since a force stop
  clears an app's alarms, and a notice whose day has passed, after the
  clock was changed, is dropped. Stored as `notice`, `notice_time`
  (seconds of the day) and `notice_next` (the next notice, epoch
  milliseconds, for the subtitle).
- **Local sky line:** sunrise, true solar noon (the sun's transit, not
  12:00) with the sun's altitude then, and sunset, each with its icon, on
  one line of 44 dp pinned under the page (ROADMAP U8, built 2026-10-11).
  Facts only, read once a day, so the line keeps to a twentieth of the
  screen: the place is not on it but in the balloon a tap on the line
  opens, with "Change location" as its action. With no place set the line
  says "Set a location for sunrise and sunset" and opens the location
  screen. Until U8 the place stood on a second line, the two about 110 dp.
- **Choose a day**, under the two calendars: the election (§10.8) of the
  calendar shown, from the shown day, its work not yet picked.
- **Health**, under Choose a day: the Health screen (§10.10) on the
  shown day.
- **Element reckoning**, the Reckonings group's row: the Reckonings
  (§10.11) for the person chosen, at the shown day's Tibetan year.
- **People from a screen**: the way to People that Health's You tab and
  the Reckonings show without a person opens the same list and dialogs
  over that screen. (Until R1 they opened only over the day pages, so on
  Health the link did nothing.)
- **The groups fold** (ROADMAP H3, built 2026-10-15): each group's label
  is a header row with a chevron that turns, a button with its state
  ("open", "closed") for screen readers; a tap opens or closes its rows
  with an animation of the height. Every group is closed on a fresh
  install, and each keeps the state it was last left in, apart from the
  others, across the menu's closing and the app's restart (preferences
  `menu_calendar`, `menu_reckonings`, `menu_practice`, `menu_settings`). About & sources
  stands alone at the foot, always shown. A group added later starts
  closed.

### 10.6 The home-screen widget

Built 2026-10-16 (ROADMAP W1). Two widgets in the launcher's list, both
for the calendar last viewed, each a tap that opens the app on that
calendar's today (§4: the Tibetan day turns at 05:00 at the place, the
旧暦's at midnight on the phone's clock), and each reading the day as the
page does (`DayInfo`, `DaySummary`), nothing weighed anew:

- **Day** (2 × 2 cells): the civil date ("Fri 16 Oct") and the calendar's
  name in its accent, the calendar's day as a numeral beside its month,
  and on the Tibetan page the day's tone with its dot and its reason as
  the In brief row words them ("a lucky day", "by the combination"); on
  the 旧暦, which has no tone (§7.5), the In brief line's counts in their
  place ("good 7", "avoid 3"). On the person's enemy weekday or death
  mansion the brief's line "every work to avoid, for you" under the tone.
- **Day and hours** (4 × 2 cells): the same facts, the numeral beside the
  month and date, the tone and reason on one line; then on the Tibetan
  page the hours row's strip of the twelve two-hour periods from 05:00 in
  their combination periods' tones, the present period marked under its
  cell, the clock hour under every third, the present period in short
  form ("now 21–23 · Gemini rises · to be accomplished") and the hours
  against the day's tone in the hours row's short form ("avoid 05–09 ·
  11–13 · 17–19 · 23–01", `HoursShortForm`; none on the person's
  avoid-all day); on the 旧暦 the rokuyō with its English, the day mark
  where there is one ("受死日 black day — the worst of all") and the
  counts.

The widget takes the app's theme (§10.5): its page, text and tones are
the theme's, the strip's tones laid over the page as the hours row lays
them. System follows the phone's dark setting by itself from Android 12
(each colour given for day and night); before it, the setting at the
last update stands until the next. It takes the app's language. Its text
follows the font scale up to 1.15, at which both sizes' lines fit their
cells in Russian, the longer language; past it the page holds them. From
Android 12 each size has a compact variant the launcher picks where the
cell is short or narrow, as on a landscape home screen: the small one
without the date line and the reason, the 旧暦's counts on one line; the
wide one with the reason on its own line, without the clock hours and the
present period's line, the mark under the strip still showing it.

It is drawn anew at each two-hour period's start on the place's clock
(every odd hour from 05:00) and at midnight on the phone's, whichever
comes first (`WidgetPlan`), by one exact alarm that does not wake the
phone (a widget is seen only with the screen on, and a missed alarm fires
as the phone wakes; `USE_EXACT_ALARM` as the bell's, §10.9); when the app
is left, so that a change of calendar, person, place, theme or language
shows at once; on a change of the phone's language; and by the bell's
receiver after a reboot, an update and a change of clock or zone. With
no widget placed there is no alarm. Plain `RemoteViews` (`DayWidget`), no
widget library: Glance would bring WorkManager, DataStore and with them
permissions the app does not need, and the app keeps its own times
already. Screen readers get the widget's lines as one description.

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
- **In brief line.** Both pages alike (ROADMAP U16, built 2026-10-11): one
  row of glyphs, the families named good, then those to avoid, each side
  under its word and its count in its tone ("good 53", "avoid 36"; on the
  person's enemy weekday or death mansion the avoid side alone); four
  places a side, a side with fewer families leaving its places to the
  other, eight at most and as many as fit the width. The words and counts
  stand over their sides rather than before them, so that the eight
  glyphs keep the width at font scale 1.3 in Russian ("избегать 35").
  On the Tibetan page (`DaySummary.row`) the families of the heaviest
  works (§5.12 gives the weights); a family appears once: where it would
  stand on both sides, it keeps the side of its heavier work and the next
  family takes the other place; equal weights keep the breakdown's order.
  Measured over 2000–2049: four and four on 92 % of days, about six of the
  eight glyphs change from one day to the next. On the 旧暦 page
  (`DaySummary.familyRow`) the first families in the order the enum
  declares: a count, not a rank, since no weight enters the 旧暦 (§10.4);
  a family named both ways stands on both sides in the mixed colour, and
  the breakdown keeps every family. Until U16 the 旧暦 line gave every
  family on two lines with "good" and "avoid" before them, and the
  Tibetan row no word, a rule between its sides and the counts at its
  end. Screen readers hear the counts and, on the Tibetan page, the works
  the row stands for.
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
- **Printed colours.** The sme ba's colours as printed (Berzin 4), the
  nine stars' after their readings, and the elements' (T3, §10.3) are
  the only colours outside the theme, and keep their hues on every
  theme. A box or dot close to the page's own lightness, under 1.5:1
  against the sheet it is drawn on, gets a thin rim in the faint colour,
  a rule rather than a list, so that each theme rims its own: the black
  sme ba box on the dark themes, the white boxes (1, 6, 8) and the white
  stars' dots on Paper and Sepia, and on Sepia's parchment the yellow
  box (5) and the yellow star's dot too.
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
- **The months:** for each Tibetan month of the span its name and the
  civil span its days cover, "9th month · 11 Oct – 9 Nov" (the span's own
  days, so the first month starts on the shown day; the year at the end
  only where the span leaves the shown day's, "8 Jan – 6 Feb 2027"), its days
  as a grid of six to a row (`WorkGrid`, the haircut sheet's grid
  generalised, §10.3), each cell the lunar date with the civil day of the
  month small under it, the date picker's figures reversed (a doubled
  date's two cells each with their own civil day, a skipped date no cell),
  over the dot of the work's side, green, red or without colour where no
  voice names it, the shown day outlined; with a birth date set, the person's enemy weekday and death
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
  standing beside the deciding one on a line of their own only where
  there are some (`ElectionDay.beside`: never "by Friday" and "Friday"
  again under it), the person's own days on it with their dots under "For
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
  旧暦's works; the months are 旧暦 months, headed and celled as the Tibetan
  grid is, with the civil span and each cell's civil day, each cell's dot green where the
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
- **Keep the screen on:** a switch under the session's settings, off by
  default. When on, Begin keeps the screen on (the window's
  keep-screen-on flag, no permission), past the last bell and past Stop,
  until the meditation screen is left by its back arrow, Back or the
  menu; opening the screen again at rest does not hold it. A sitting
  under way when the screen is opened, from its notification, holds it
  at once.
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
  chosen weekdays; From and Until each move a half hour on − and +, and
  a tap on the time between them opens the Material time picker in the
  app's colours (ROADMAP U17, built 2026-10-11), 24-hour as the app's
  clock, its time rounded to the nearest half hour (a quarter past or to
  rounds up, from 23:45 it stays 23:30); a From set at or after Until
  moves Until half an hour after it, and an Until at or before From moves
  From half an hour before it (`BellHours`); fixed bells fall on the interval counted from the
  start of the hours, random ones half to one and a half intervals after
  the last, the first of a day within one interval of the start
  (`BellPlan`). One exact alarm at a time (`USE_EXACT_ALARM`, before
  Android 13 `SCHEDULE_EXACT_ALARM`), set again after each ring, a
  reboot, an update and a change of clock or zone. The morning notice
  (§10.5) has an exact alarm of its own under the same permission, and
  the same receiver sets both again and draws the widget anew (§10.6); the notice does not use the bell's ring
  service, since it makes no sound (`MorningNotice`, `NoticePlan`). It keeps quiet during
  a sitting; unless switched off, during a call (ringing or answered) and
  while another app plays sound longer than a notification's (music, a
  video, a game, directions, a voice chat, an alarm); and, unless switched
  off apart, while the phone is on silent, vibrate or do not disturb; a
  bell more than ten minutes late is skipped. The switch's subtitle names the next bell.
  Each bell rings in a short foreground service (`BellRingService`, type
  `specialUse`), started by the exact alarm: Android mutes an app's
  sound in the background (AudioHardening, seen on Android 17), and the
  service keeps the strike heard. Its notification is the bell's visual
  cue: it shows over the screen with the bell's message, without sound or
  vibration of its own, and is removed when the strike has faded (about
  8 s). The message is the user's own line, set under the days, by
  default Om mani padme hum in its Tibetan script alone,
  ཨོཾ་མ་ཎི་པདྨེ་ཧཱུྃ (the owner's choice, 2026-10-11: the user's own text,
  so §8.1's naming does not apply to it); Default sets it again, and an
  empty message shows the bell's name. Notification permission is asked
  for when the bell is switched on; without it the bell rings unseen.
- **Sound:** a large bowl, a small bowl or a bell, one choice for the
  session's bell and the mindfulness bell (the wood block only between
  periods); a volume and Listen. Bells ring on the alarm stream, so a sitting is
  heard with the ringer silenced, and duck other sound while they ring.
- No network: the §2 rule holds; the permissions added are the
  foreground service's, the wake lock, notifications, exact alarms and
  boot.

### 10.10 The Health screen

The Tibetan day's illness, the la and the person's element on a screen
of their own (ROADMAP H1, built 2026-10-15; [sources/health.md](sources/health.md)),
opened from the menu (§10.5) and from Your day's last row (§10.3) on
the day shown there. Its header has Back, the title and the date, and
the day before and after. Under it four tabs by subject, and a line:
the readings are the tradition's, not medical advice.

- **Today:** "Falling ill today", one row whose sheet gives in turn the
  date's, the weekday's and the link's Illness rows (§5.8, *The day's
  illness*) and the goddess of the day (§5.11); on the four perilous
  days its dot is unlucky and its line says so. "The la today": the
  la's place by the date, its sheet the course within the day (§5.8,
  *The la*).
- **You** (with a person chosen; else the way to People): the day's
  mansion as a quarter of the life force's element, with its reading;
  the grave mansion where the day's is one; the la's element; the
  vitality and body of the date against the birth year (§5.9; until
  H1 in Your day), each row as before: the aspect, its pebbles, the
  relation and both elements, its sheet the relation's reading and how
  it was worked out, the dot lucky for white pebbles only, unlucky for
  black only, mixed for both; in a year of age whose mewa is the
  seven-red, its reading by the colour alone (WB vol. 1, pp. 403–404:
  the full round avoids bloodletting and moxa that year, the short one
  says they will be done; born under the two-black, p. 405 in the year
  sheet makes moxa good and bloodletting bad, and WB says not which
  leads).
- **Treatment:** the medical works (`ActivityFamily.MEDICINE`) as the
  person's day page gives them (§5.12), each with the voices on its
  side and their readings; on the person's day of every work avoided
  that first, and no work good.
- **Medicine:** what the Four Tantras add and the White Beryl does not
  reckon, with the grey dot (`Tone.NEUTRAL`; the owner, 2026-10-11):
  the three humours, the season and the two times each one's illness
  rises, its people and its country and conduct (Root Tantra ch. 3 and
  6), and the six seasons' food and conduct (Explanatory Tantra
  ch. 14), read on the Serthang print (RD9). Neither names works, so
  neither enters the weighing. Which season a Tibetan month is in is
  not reckoned: no source read gives the seasons' months.

Not built: Dr. Nida Chenagtsang's own tables, which are WB's but for the
six moxa dates and the syllables (RD7–RD9), the la pulse (a sign read at
the pulse, not by date), WB's illness reckoning (H2) and the mansion of
conception.

### 10.11 The Reckonings

The White Beryl's element reckoning (*'byung rtsis*) for the person
chosen, a section of its own (ROADMAP R, R1 built 2026-10-15;
[sources/reckonings.md](sources/reckonings.md)), as Meditation has: the
Reckonings group of the menu (§10.5), and the year's balloon on the
Tibetan page (§10.3), which opens its year of age directly. Its pebbles
are counters laid by how two elements meet, white or black by a fixed
rule, not a throw of chance (WB vol. 1, p. 417; the notes, vol. 2,
p. 474): the screen's first line says so, naming the reckoning in
Tibetan, and no reckoning in it is cast, as Mo is not (T4).

- **The list**: Back, the title "Element reckoning" and the person's
  name; the line on the pebbles; under "Your reckonings" one row for
  each reckoning built, with the person's value for the year (today
  "Your 51st year · Fire Horse year"), opening its own view. With no one
  chosen, a line that the reckonings are made from a birth date and the
  way to People (§10.5). Built: the birth, its row "Natal trigram lake
  (དྭ, dwa) · the children's glory", or "Needs your mother's birth year";
  the year of age. WB's other reckonings, chapters 23–30, join them one
  by one as R2… (ROADMAP R), in the order of the inventory (RD11, RD17);
  `Reckoning` in the app lists them.
- **The birth** (§5.9.2): Back, the title "The birth" and the person's
  name; the person's birth year and its life force; under "The natal
  trigram" its row (as in the year of age) and the child it makes them;
  under "The parents' trigrams" each reading that holds, or a line that
  none does, with notes asking for the gender and the father's birth year
  where they are missing; a closing note on what of the reckoning is
  reckoned. Each row opens its reading, whose workings give the life
  force, the trigram and its element, a demon's age, both parents'
  trigrams and years and the one born. Without the mother's birth year,
  a line that the birth is reckoned from her trigram and the way to
  People.
- **The year of age** (§5.9.1): its header has Back, the title, the
  Tibetan year as its sign and civil span ("Fire Horse year ·
  2026–2027"), and the year before and after; the year before the
  person's birth year cannot be reached. Under it: the person's age and
  signs; the signs of the year (the mewa of the year of age with the
  natal one, and with a gender the trigram and the progressed sign with
  its place); under the pebbles, first what matters most for the person
  (§5.9.1, *What to do*), then for each aspect its predictive pebble, the
  one that matters most marked "matters most for you", with under it the
  stage its element's energy stands in ("Vitality · energy: death
  stage"); all four alike; then the pebbles as a grid, drawn as the
  charts draw them (○ white, × black), a row for each basic sign and a
  column for each aspect as chart 6.2 lays them, the white and black
  counted under it, and the hour of reckoning, the present one whatever
  the year, named; and the year's obstacles, or a line saying there are
  none. Every row and cell opens its reading with its
  workings. Without a gender a note asks for it in People. A year
  stepped to is reckoned from its place in the sixty-year cycle
  (`YearOfLife.yearSign`), the same as from any day in it
  (`ReckoningsTest`).
- **Back** leads the way it came: from a reckoning to the list, or, from the year of age,
  when the year's balloon opened it, to the page; from the list out of
  the section.

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
the widget was built later (§10.6, 2026-10-16). Release 1.0 is
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
| Description matches the app | the listing mentions no feature that is not built |

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
