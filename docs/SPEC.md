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
(10/25), Sangpo Chuzom — the Ten Good Omens (11/6, checked on three occasions in
Rabten's 2025–26 calendars), Thanksgiving to the Protectors (12/29). Henning
also marks festivals inside leap months; this app does not.

### 5.8 Almanac entries (Janson §10, Appendix E)

Each calendar day also carries the entries of a Phugpa almanac, all checked
day by day against Henning's computed calendars (§9):

| Entry | Rule |
| --- | --- |
| Lunar mansion | ⌊27 × moon at daybreak⌋, the moon from (10.1)–(10.2); for the first of two equal dates, the moon at the end of the lunar day minus 1/27 |
| Element pair | the weekday's element (Table 5) with the mansion's (Indian system, Henning's list): ten pairs, four inauspicious |
| Yoga | ⌊27 × (moon at daybreak + true sun)⌋ (10.4)–(10.5); Tibetan names as printed in Phugpa almanacs |
| Karaṇa | the half lunar day in effect at daybreak: H = ⌊60 × (moon − sun)⌋ + 1, fixed for H = 1, 58, 59, 60 |
| Lunar-day animal, trigram, number | Janson (E.9)–(E.11) |
| Hair-cutting day | by lunar day, from Lama Zopa Rinpoche's translation (FPMT, 2008) |
| Monthly observances | 8th, 10th, 15th (Sojong), 25th, 30th (Sojong), after Edition Rabten |
| Personal day | luck, life or anti weekday for the animal of the birth year (Rabten's table); needs a birth date (§10.5) |
| Personal mansions | whether the day's mansion is one of the six of the birth-year animal (bla, srog, dbang, skeg, bdud, gshed skar): the White Beryl, vol. 2, p. 330 (1996), its slips settled by the Sakya *nor bu'i me long* (p. 64) and Nam mkha' seng ge's *skar yig*, which print the same table ([sources/personal-mansions.md](sources/personal-mansions.md)); bla, srog and dbang skar lucky, the other three unlucky, as both texts call them; needs a birth date |

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
schematic charts write them, ○ for white and × for black. Only this part of
the divination of obstacle years is calculated: the log-men year, trigram,
numeric square, sectors of growth and decline and the hour add pebbles of
their own, not shown. The life-spirit (bla, the element that feeds the
vitality) is not one of the four aspects the charts compare, so it is not
shown.

The rules and the direction were read in Gyurme Dorje's edition, *Tibetan
Elemental Divination Paintings* (2001), used as a reading copy: pp. 64, 68,
90–91 and charts 6.2 (p. 228) and 8.1 (p. 296), which give every cell of a
subject born in a fire dragon year read in an earth tiger year (§9). Chart
8.1's day, the 15th of the 3rd month of 1998, comes out of the calendar
engine as the wood dragon it prints. The app cites the White Beryl and the
Moonbeams.

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
  boxes 50–51 come from the second print, KP2. Averting rites, whose
  entries all name a kind of rite, and the charts are not built. The lists' readings cite the
  print first and Henning beside it.

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
  (Uttarabhādrapadā in both halves of the offerings box; Mṛgaśiras as
  acceptable and bad for controlling activity) is left out.
- Rising signs are left out, since the page shows a day and not a moment;
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
  perilous dates (8, 15, 22, 30) and where the soul (bla gnas) sits, after
  the Phugpa list for people. Two acts were identified from the
  dictionaries and WB's own usage: shwa rags (22nd and 27th), a dike
  against flash floods, counted with dams; thag ser (29th), read as thog
  ser, casting lightning and hail, counted with fierce rites; both
  readings name the print's word. The dot: lucky on a virtuous day,
  unlucky on the other two, mixed on the 8th and the 22nd, virtuous but
  perilous.
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
  day (`DaySmeBa`). It is an Almanac row after Rāhu's, opening the reading
  with the day's sme ba, the wood-mouse day it counts from and the moved
  square; no tone, not weighed (§5.12), as it holds for a direction, not
  for the day.

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
  §5.8, one voice); Rāhu, on the dates its detailed course or its course by
  month names (§5.13), the first of the *kun phan me long*'s seven; the
  weekday; the mansion; the special days of weekday and mansion, one
  voice; the lunar date; the karaṇa; the yoga; the day animal (the lunar
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
  reading (§5.10), each special day's, the date's verse with Henning's date
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
  brief lists them apart, under "For you".
- **The day in brief** names the tone and what decided it (the
  combination or the strongest factor), lists the voices that took
  that side, and gives each work with the voices that carry it, in rank
  order; works on which more voices agree come first. Its summary line is
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
- **Order on the page**: the Almanac section lists the day's readings in
  rank order (named combination, element pair, Rāhu, weekday, mansion,
  special days, lunar date, karaṇa, yoga), after the monthly observance and
  personal rows and before the hair-cutting day.

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
side) are 2,140 (12 %; 2,087 before the mansions' verses were built,
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
  course.
- **Jupiter's nectar periods** ([sources/nectar-periods.md](sources/nectar-periods.md)),
  the *kun phan me long* §10: each double hour is halved, each half ruled
  by a planet, counted from the weekday's own planet six on by day (from
  dawn) and five on by night (from sunset); Jupiter's halves are the
  nectar periods. On the twelve hours from the hare hour at 05:00 (§10.3)
  each half is a clock hour, and the Almanac section has a row with the
  day's nectar hours, whose reading lists what the activity tables name
  them good for. It takes no part in the weighing: it is a time within the
  day.
- **The combination period** (*tatkāla dus sbyor*,
  [sources/combination-period.md](sources/combination-period.md)), which the
  texts hold above every factor of the day: the sign rising in each hour.
  KP's table (§9, img. 79–80) gives the month's sign at daybreak and one
  sign more each hour (the 3rd month Aries … the 2nd Pisces); WB (vol. 2,
  pp. 371–376) gives for each sign what is good and bad while it rises and
  whether the period is to be accomplished or avoided. It is shown on the
  hours panel (§10.3) and in the day in brief's "By the hour" block, not
  weighed into the day: a day reading has no hours.
- **Not built**: the hour against the day's animal sign (KP's rule 2).

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

## 8. Texts

### 8.1 Policy

Every text the app shows, other than names and numbers computed in §5–§7,
names a published source; a test fails if any annotation lacks one. F-Droid
requires every asset to be legally licensed, so copyrighted wording is never
copied:

| Licence | Used for |
| --- | --- |
| Own English summary of the cited source, MPL-2.0 | copyrighted sources — Todan's こよみ博物館, NAOJ, Kotobank dictionaries, FPMT, Edition Rabten, Henning, Lotsawa House. Written as statements of fact and "good for / avoid" lists, not translations |
| CC BY-SA 4.0 | wording adapted from Japanese Wikipedia (選日, 暦注下段, 九星, 九星気学, 歳徳神, 庚申待) |

No text under a non-commercial or no-derivatives licence: F-Droid labels an
app containing one with the *Non-Free Assets* anti-feature.

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
| Tibetan pronunciation | THL Simplified Phonetic Transcription of Standard Tibetan, Germano and Tournadre, 2003 (thlib.org; archived by the Wayback Machine at `thlib.org/global/php/essay_reader.php?url=/thl/phonetics/s/b1`–`b12`) |
| Tibetan spellings | The White Beryl, Sde srid Sangs rgyas rgya mtsho, Derge blocks reprinted Dehra Dun 1978 (BDRC W1KG12714) |
| Band names: 中段, 暦注下段, 選日 | Japanese Wikipedia 十二直, 暦注, 暦注下段, 選日; koyomi8.com 暦注の説明; こよみ博物館「暦注」 |
| 雑節, 節句, 十三夜 | NAOJ 暦Wiki |
| O-Bon by the Gregorian date | Japanese Wikipedia お盆 |
| Hair-cutting days | *From the Sutra Chapter of Bodhisattva's Hair*, tr. Lama Zopa Rinpoche, FPMT 2008 |
| Element pairs, observances, festivals, personal days | Edition Rabten, *Tibetan Calendar 2026*; Henning's archive and symbolic details |
| Personal mansions | The White Beryl, vol. 2, p. 330 (BDRC MW2CZ8040), with the Sakya *'bras rtsis rab gsal nor bu'i me long*, p. 64 (BDRC MW29978_8B19DD) |
| Lunar mansions, activity lists | Henning, *Horary and electional astrology of the five components*, after the White Beryl, the *Treasury of Jewels* and the *'bras rtsis bai dkar dgongs don kun phan me long*; his doubled mansions read on that print (BDRC W4CZ65561); the White Beryl's seven classes of mansions, vol. 2, pp. 328–329 |
| Lunar dates, weekdays, yogas, karaṇas | The White Beryl, ch. 33, Beijing 1996, vol. 2, pp. 297–304, 308–312 and 347–351 (BDRC MW2CZ8040) |
| Trigram (the eight goddesses of the date) | The White Beryl, ch. 25, Beijing 1996, vol. 1, pp. 449–450 (BDRC MW2CZ8040) |
| Combinations of weekday and mansion, special days | The White Beryl, Beijing 1996, vol. 2, pp. 331–337, 341 and 342 (the *Rdo rje gtsug lag*'s special days), with the table in vol. 1, pp. 148–149 (BDRC MW2CZ8040) |
| Rāhu's course | The White Beryl, Beijing 1996, vol. 2, pp. 236–238 (BDRC MW2CZ8040); the *kun phan me long*'s chart of the general course, img. 78 (BDRC MW4CZ65561) |
| Earth lords of the date's animal | The White Beryl, Beijing 1996, vol. 2, pp. 224–226 (BDRC MW2CZ8040) |
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
| `henning-phugpa.tsv` | Every day of 2000, 2013, 2024–2027 (2,245 days): date, repetition, weekday, mansion, element pair, yoga, karaṇa, lunar-day cycles, Chinese mansion, festivals | Henning's Phugpa archive, extracted by `tools/extract_henning.py`; compared with a₂ = 3781/105840, which Henning uses |
| `naoj-2026-2027.tsv` | Solar terms, new moons and 雑節 with JST times, 2026–2027 | NAOJ 暦要項 |
| `koyomi8-2026-2027.tsv` | Every day of 2026–2027: 干支, 十二直, 二十八宿, 旧暦 date, 六曜, 九星, 選日 | こよみのページ (koyomi8.com), an independent computation |
| `crosscheck-new-moons.tsv`, `crosscheck-solar-terms.tsv` | New moons and 15° solar terms 1900–2100, UTC | Computed with PyEphem 4.2.1 — a cross-check, not a published table. Worst differences: 34 s and 36 s |
| `gyurme-dorje-forces.tsv` | Vitality, body, destiny and luck of all 60 years, and the relationship of destiny to vitality (kha-yan, khong-nong, …) | Gyurme Dorje (2001), charts to Plates 3–8, pp. 70–85, extracted from the archive.org OCR by `tools/extract_gyurme_dorje.py`. One body and three relationship rows are lost in the OCR; year 57 prints destiny wood where its own relationship row and every other year give the year's element, iron |
| *(in `ForcesTest`)* | Table 2.5, the destiny elements of the twelve months for each yearly element (p. 91); chart 8.1, the month, day and hour pebbles of the health divination (p. 296); Table 2.7, the hours' destiny elements (p. 91) | Gyurme Dorje (2001) |
| *(in `ForcesTest`)* | Chart 6.2: the four aspects of a fire dragon, an earth tiger and an iron mouse year, and all 20 elemental cells of the obstacle-year chart | Gyurme Dorje (2001), p. 228 |
| *(in `RekichuTest`)* | The 九星 leap positions 1905–2100; the 二十八宿 table for every day of 1900–2100 | Japanese Wikipedia 九星, 二十八宿 |

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
- **Every term is translated on tap.** Each Tibetan word and each kanji opens
  a balloon with its English (and reading); these carry no dotted underline.
  A Tibetan term shows in Tibetan script; its balloon gives the Wylie, how it
  is said and the English, and screen readers read the pronunciation (§10.3).
- **Readings on demand.** Annotations are listed as rows with a lucky/unlucky
  mark; tapping one opens a sheet with its reading, "good for" and "avoid",
  source and licence.
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
  (Janson Table 4), seasonal name; then the month's element and its
  vitality and body (§5.9), with a birth date set each with its pebbles
  and relation to yours, as in the year's balloon.
- **Repeated day:** each of the two days carries a small tag, "first of two" /
  "second of two".
- **Skipped day:** the day after the gap carries a note, "day 24 is
  omitted". A holiday moved back by a skipped date says "moved from day N".
- **Leap month:** "Leap 2nd month".
- **Year:** element and animal, "Fire Horse"; the royal year (2153)
  and rabjung cycle ("17th cycle, year 40") in the year's balloon.
- **Midnight to dawn:** "today" is the civil date, as §4 says; the
  date carries a small "from dawn".
- **Day line:** one short line — weekday, planet, day element and animal, e.g.
  "Monday · Moon · Iron Horse". Tapping it opens a balloon with the full
  details: weekday with its Tibetan name, planet, element, animal, gender.
- **In brief:** under the day line, one row of glyphs, however many works
  the day names: the families of the heaviest works good and of the
  heaviest to avoid, weighed as §5.12 says (§10.7), with the day's tone and, in a few words, what decided it
  ("a lucky day · by the combination"); after the glyphs, how many works
  the day names good and to avoid ("good 53", "avoid 36"), since the row
  cannot show the proportion and on 11 % of days the lists run against
  the tone (§5.12; decided by the owner, 2026-10-07); it opens the tone with its
  reason, the voices of that tone and each activity with the voices that
  carry it; nothing outweighed is shown. With a birth date set, a "For
  you" block lists the day's personal day and personal mansions (§5.8)
  with their dots, each over the factor it is ("Luck day" over "Sunday,
  for your birth year"), and one sentence that they are shown, not weighed
  (§5.12). After the works, a "By the hour" block (§5.13) gives the clock
  times of the combination periods to be accomplished and to be avoided,
  consecutive hours of one verdict joined ("09:00–13:00"), and the nectar
  periods, each time opening the hours panel at its hour, with one
  sentence that within its hour the combination period outweighs every
  factor of the day (WB vol. 2, p. 376) and that the nectar periods are not
  weighed (`DayHours`).
- **Almanac:** monthly observance, personal day, personal mansion (§5.8,
  on the days the mansion is one of one's six); the festival is the
  headline and opens its reading from there, so the Almanac does not
  repeat it. Then the day's readings in the rank of §5.12: named
  combination, element pair, Rāhu, weekday (§5.11), lunar mansion (§5.10,
  no dot: it has no tone of its own), special days, lunar date, karaṇa and
  yoga (§5.11); then the haircut, weighed as §5.12 weighs every work: its
  dot is the side the brief gives haircuts and its subtitle names the
  factor that decides ("avoid · by Tuesday"; KP box 52a names every weekday,
  so on every day of 2000–2049 it is the weekday). Its sheet says so,
  shows the days of the Tibetan month with the side the weighing gives
  haircuts on each, and gives FPMT's day for the date as one of the date's
  lists, marked outweighed on the days it is (decided by the owner,
  2026-10-06). The weekday, mansion, karaṇa and yoga rows carry their
  Tibetan names in their sheets, under the gloss ("Tibetan" with the term
  in script, tapping it for the Wylie and phonetics), so that no section
  repeats them (decided by the owner, 2026-10-07: the five components
  section, which gave the same four terms again, was folded in). After the
  Almanac and Your day, the lunar-day cycles as tappable terms, the date's
  animal (*nyi ma*) opening its earth lords and the
  trigram its goddess's reading (§5.11). Several special days are one row,
  their dots side by side, as they are one voice in the weighing; its
  sheet gives each reading in turn. Rāhu's courses by date and by month
  are one row in the same way. The day line's balloon names the 60-day
  cycle's animal the "day sign", the Lunar day section the date's animal
  and the date's sme ba, and the *bla mkhyen* row says its sme ba is
  counted from the solstice, so that the two animals and the two sme ba
  are not taken for one.
- **Tibetan script:** every Tibetan term (month, mansion, yoga, karaṇa,
  weekday, trigram) is written in Tibetan script, converted at run time from
  the Wylie of the sources (`Ewts.kt`), and sits on the baseline of its
  label. Its balloon adds the Wylie and the pronunciation in the THL
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
- **Your day:** with a birth date set, a section after the almanac sets the
  vitality and body of the birth year against those of the lunar date
  (§5.9), one row each: the aspect, its pebbles, the relation and both
  elements ("Body ××, enemy: the day's water to your fire"). A row opens
  the relation's reading, then how it was worked out: the lunar date's and
  month's signs, the year's, how the month's and date's elements are
  counted, the day's element, yours and the relation.
  The tone dot is lucky for white pebbles only, unlucky for black only,
  mixed for both.
- **Hours of the day:** a clock icon on the Almanac header opens the
  hours: a 24-hour dial, midnight at the top, with the twelve two-hour
  periods named by their animals. Its inner ring is the combination period
  (§5.13), each hour coloured by the White Beryl's verdict on its rising
  sign, with dots on Jupiter's nectar periods; with a birth date two outer
  rings show the pebbles of the hour's vitality and body against the birth
  year's (§5.9). On today's page a hand marks the present moment and the
  current hour is selected. Tapping an hour, or stepping with the arrows
  beside its sign, shows its rows: the combination period, a nectar period
  if one falls in it, and with a birth date vitality and body; each opens
  its reading as the day's rows do.
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
  as a full-height column: the two calendars at the top, settings below.
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
- **Birth date**, optional and stored on the device: enables the personal days
  (Tibetan luck/life/anti, Japanese 三箇の悪日) and the Tibetan pebbles.
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
  §10.4. Switching it on without a birth date asks for one; cancelling leaves
  it off.
- **Local sky line:** sunrise, sunset, true solar noon (the sun's transit, not
  12:00) and the sun's altitude then. Facts only.

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
- **Tibetan page.** Element pair: the two elements on its row, and in its
  sheet the table of the ten pairs, weekday down, mansion across. Lunar
  mansion: a small ring of 27 on its row, the full ring in its sheet. Haircut:
  scissors on its row; in its sheet the days of the Tibetan month, one cell
  per civil day (a doubled date twice, a skipped one not at all), each dot
  the weighed side for haircuts, the caption naming the day's civil date
  and deciding factor.
  Trigram: drawn as its three lines. Named combination: in its sheet the
  table of the 28, weekday down from Sunday, the 27 mansions across, each
  cell in its tone, today's marked. Nectar periods: the 24 hours as a ring,
  midnight at the top as on the hours panel (§10.3), the periods as arcs,
  small on the row and with the hours in the sheet. Lunar date: five marks
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
  compass is the date's.
- **Reading sheets** carry a band in the reading's tone across their top.
- **Font.** `tools/subset_fonts.py` takes the characters of the Kotlin
  sources, the catalogs and the string resources; a rebuild after new kanji
  in any of them.

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
