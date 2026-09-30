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
  (the 60-day cycle's) and trigrams. What a list does not name is neutral.

The lists are taken as printed, with these rules for what is doubtful:

- A name in parentheses, or with a qualifier ("Monday (S only)",
  "Mūla (bad for entombment)"), counts for neither side; so does a
  category the source calls "merely acceptable" or "neutral". The one
  exception is the mouse, "bad for divination" in the divination list
  itself.
- A mansion named more than once for one activity, in any of these places,
  is left out. Uttarāṣāḍhā in particular appears twice in several lists,
  once as both good and bad, and is probably confused there with
  Uttarabhādrapadā; the Tibetan original is not at hand to settle it.
- Rising signs are left out, since the page shows a day and not a moment;
  Abhijit, which the Phugpa calendar does not count among the day's
  mansions; "black" years, months and days, earth-lords and the demons,
  which the app does not calculate.
- Ranges are applied as stated: for pacifying, every mansion not named
  good, acceptable or neutral is bad; for health and wealth, waxing dates
  are good but for the 6th, 7th and 9th, waning dates bad.

A mansion's reading joins what the list of mansions names it good for with
the activity lists' good and bad; where they disagree (Rohiṇī is good for
marriage in the first, bad in the second) both stay, as elsewhere in the
app. The day in brief (§10.3) lists every activity with the factors that
name it good or bad; it weighs none against another.

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
   engine follows the resolution recommended by the Japan calendar society
   (閏11月 in 2033) as a named, single-place decision in code; the
   recommendation is confirmed from its published source during S3 (§8.3).

The 24 solar terms (節気) are the 15° points. Index them by longitude with
立春 = 315° as the first, the Japanese almanac order.

### 7.2 Rokuyō

(month + day) mod 6 → 0 大安 Taian, 1 赤口 Shakkō, 2 先勝 Senshō,
3 友引 Tomobiki, 4 先負 Senbu, 5 仏滅 Butsumetsu. A leap month uses its own
number. Consequence: the first day of month 1 is always 先勝.

### 7.3 Festivals

Kyūreki-dated observances: the five sekku (人日 1/7, 上巳 3/3, 端午 5/5,
七夕 7/7, 重陽 9/9), 十五夜 8/15, 十三夜 9/13, and 旧正月 1/1. Festivals
that most of Japan now keeps by the Gregorian calendar (for example O-Bon on
August 15) are shown on their Gregorian date and labelled as such.

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
| Personal (暦注下段) | 大禍日・狼藉日・滅門日, only in the solar month of one's birth-year branch; needs a birth date |
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
| 選日 and 暦注下段, 九星, 恵方, 庚申 | Japanese Wikipedia; Kotobank (精選版 日本国語大辞典, 日本大百科全書) for 寅の日, 巳の日, 甲子 |
| 九星気学 relations and school | Japanese Wikipedia 九星 (九星の関係) and 九星気学 |
| 縁日 as a group | Japanese Wikipedia 縁日 |
| Colours of the sme ba | Berzin, *Details of Tibetan Astrology 4* (Study Buddhism) |
| Four aspects, the pebbles of the day, month and year | The White Beryl (ff. 156a/b, 158a, 248b–254a, 295b–299a) with Lo chen Dharmaśrī's *Moonbeams* (ff. 5b–6b, 28a/b, 31b–32a), read in Gyurme Dorje's edition (2001) |
| Tibetan pronunciation | THL Simplified Phonetic Transcription of Standard Tibetan, Germano and Tournadre, 2003 (thlib.org; archived by the Wayback Machine at `thlib.org/global/php/essay_reader.php?url=/thl/phonetics/s/b1`–`b12`) |
| Tibetan spellings | The White Beryl, Sde srid Sangs rgyas rgya mtsho, Derge blocks reprinted Dehra Dun 1978 (BDRC W1KG12714) |
| Band names: 中段, 暦注下段, 選日 | Japanese Wikipedia 十二直, 暦注, 暦注下段, 選日; koyomi8.com 暦注の説明; こよみ博物館「暦注」 |
| 雑節, 節句, 十三夜 | NAOJ 暦Wiki |
| Hair-cutting days | *From the Sutra Chapter of Bodhisattva's Hair*, tr. Lama Zopa Rinpoche, FPMT 2008 |
| Element pairs, observances, festivals, personal days | Edition Rabten, *Tibetan Calendar 2026*; Henning's archive and symbolic details |
| Lunar mansions, activity lists | Henning, *Horary and electional astrology of the five components*, after the White Beryl, the *Treasury of Jewels* and the *'bras rtsis bai dkar dgongs don kun phan me long* |
| Tenth day | Jigme Lingpa, tr. Rigpa Translations 2013, Lotsawa House |

Still open: the published source of the 2033 resolution (§7.1).

## 9. Test vectors

Stored as tab-separated files under `core/src/test/resources/vectors/`, one
file per source, each with the source citation in `#` header lines. `cli` and
`app` never read them. Files marked *pending* do not exist yet.

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
- **In brief:** under the day line, as on the 旧暦 page, a line counts the
  activities the lists of §5.10 name good, to avoid, and both; it opens the
  activities with the weekday, lunar date, mansion, day animal and trigram
  that name them.
- **Almanac:** festival, monthly observance, personal day, element pair,
  lunar mansion (§5.10) and hair-cutting day as reading rows; then the five
  components (mansion, yoga, karaṇa, weekday) and the lunar-day cycles as
  tappable terms.
- **Tibetan script:** every Tibetan term (month, mansion, yoga, karaṇa,
  weekday, trigram) is written in Tibetan script, converted at run time from
  the Wylie of the sources (`Ewts.kt`), and sits on the baseline of its
  label. Its balloon adds the Wylie and the pronunciation in the THL
  Simplified Phonetic Transcription of Standard Tibetan (Germano and
  Tournadre, 2003; `Thl.kt`: the general principle, special rules 1–13, the
  exceptions and the word boundaries, tested on the document's own 86
  examples). A spelling the converter cannot read stays in Wylie: the karaṇa
  vishti, a Sanskrit loan, until its Tibetan spelling is sourced. The
  spellings were checked against the OCR of the White Beryl (BDRC
  W1KG12714): 95 of 106 terms appear there verbatim; til brdung and mi
  'phrod follow its spelling rather than Henning's (til rdung, mi phrod);
  snron, snrubs and six yogas (rnam sel, tshe dang ldan pa, shin tu 'grams,
  yongs bsnun, mchog can, yongs 'joms) are not in the OCR and remain as the
  sources give them. The font is Noto Serif Tibetan (OFL), one weight,
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
- **Hours of the day:** a clock icon on the "Your day" header opens the
  hours (§5.9): a 24-hour dial, midnight at the top, with the twelve
  two-hour periods named by their animals; the outer ring coloured by the
  pebbles of the hour's vitality against the birth year's, the inner by
  those of the body. On today's page a hand marks the present moment and
  the current hour is selected. Tapping an hour, or stepping with the
  arrows beside its sign, shows its vitality and body rows, which open the
  reading and its workings as the day's rows do.
- **Sme ba in its colour:** the lunar day's number carries a swatch of the
  colour its box is printed in (Berzin, *Details of Tibetan Astrology 4*). No
  whole day is coloured.

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
- **In brief:** a line under the rokuyō (and under the day mark) counts the
  activities the day's annotations name as good and to avoid, and how many are
  named both ways; it opens the breakdown. The breakdown lists each activity
  with the annotations that name it, the annotations by tone, the day mark, and
  for the owner the 三箇の悪日 and the 九星気学 relation. It is a listing, never a
  verdict: no published rule says which annotation outranks another (下段 over
  中段, 二十八宿 over 十二直), so none is weighed, and where annotations disagree
  both sides are shown.
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

The recipe proposed to `fdroiddata` is kept in
`docs/fdroid/io.github.iverlein.zanshin.yml`. A release is: bump `versionCode`
and `versionName`, add `changelogs/<versionCode>.txt`, commit, tag
`v<versionName>`, push the tag; F-Droid's update checker picks up the tag.
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
