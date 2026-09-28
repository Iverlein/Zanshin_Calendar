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
| Tibetan: date, leap month, skipped/repeated day, weekday and planet, day element/gender/animal, year name and rabjung number, fixed holidays | Tsurphu version; lunar mansion, yoga, karana; Tibetan script rendering |
| Kyūreki: date, leap month, rokuyō, solar term of the day and current term, day and year kanshi (干支), seasonal festivals | 一粒万倍日, 天赦日 and other kanshi-derived days; 七十二候 |
| Local sky: sunrise, sunset, true solar noon and the sun's altitude at noon | Moon rise/set and phase |
| Screen with date navigation, home-screen widget, location setting | Notifications |
| Texts from published sources only (§8) | Translations beyond English |

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
        src/main/resources/data/     sourced texts, JSON
        src/test/resources/vectors/  published test vectors, JSON (§9)
cli/    JVM entry point that prints a day as text — the console look of the
        original draft; used to inspect results without a phone
app/    Android: Compose screen, Glance widget, settings
```

- Result types are Kotlin `data class`es with `val` fields only.
- `core` has one runtime dependency, `kotlinx-serialization-json`, for §8.
  Everything else is the Kotlin standard library and `java.time`.
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
leap months, except Losar. v1 list: Losar (1/1), Chötrul Düchen (1/15), Saga
Dawa Düchen (4/15), Chökhor Düchen (6/4), Lhabab Düchen (9/22) — each entry
confirmed against the source during S1 (§8.3) before it ships.

Supported range: 1900–2100 Gregorian. Outside it the engine still computes but
the UI marks results as untested.

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

## 8. Texts

### 8.1 Policy

Every text the app shows, other than names and numbers computed in §5–§7,
comes from a published source and says which. An entry without a source is
rejected when the data loads, and a test fails if one exists. Machine-written
paraphrase is not a source. When no official source is found for a set, the
set is left out.

### 8.2 Schema

```json
{
  "id": "jp.rokuyo.3",
  "original": "友引",
  "lang": "ja",
  "en": "…",
  "translation": "published | own",
  "source": {
    "title": "…", "publisher": "…", "year": 2026,
    "locator": "page, section or URL",
    "retrieved": "2026-09-28"
  }
}
```

`translation: own` marks an English rendering made for this app from the cited
original; the UI shows the original next to it.

### 8.3 Sourcing tasks

| Task | Set | Candidate official source, to be confirmed |
| --- | --- | --- |
| S1 | Tibetan holidays | Henning, Appendix II; Men-Tsee-Khang almanac |
| S2 | Tibetan hair-cutting days (the 30-day list of the original draft) | Men-Tsee-Khang publications. No confirmed source → the set is left out |
| S3 | Rokuyō meanings; 24 solar terms; sekku; 2033 resolution | NAOJ 暦計算室 and its 暦Wiki; the Japan calendar society's announcement |
| S4 | Festival descriptions | National Diet Library, 「日本の暦」 online exhibition |

The texts in the original draft are not carried over: they were embellished
beyond any source, and several were wrong (§A).

## 9. Test vectors

Stored as JSON under `core/src/test/resources/vectors/`, one file per source,
each with the source citation in its header. `cli` and `app` never read them.

| File | Content | Source |
| --- | --- | --- |
| `tibetan-losar.json` | Losar dates 1927–2046 and year names (120 years) | Janson Table 1 |
| `tibetan-dates.json` | 2007-12-31 = day 23, month 11, Fire–Pig; 2014-01-08 = Wednesday, day 8, month 11, Water–Snake; 2022-02-13 = Sunday, day 12, month 12, Iron–Ox; the epoch dates of Remark 16. Janson's title page calls 2007-12-31 a Sunday, but it was a Monday and his (9.1) gives Monday, so that weekday is not a vector | Janson, title page and §7 |
| `tibetan-a2.json` | The three dates of §5.5 | Janson Remark 14 |
| `tibetan-almanac-YYYY.json` | Month-by-month skipped/repeated days and leap months for at least two years | Men-Tsee-Khang almanac or Henning's calendar archive |
| `naoj-YYYY.json` | New moons, 24 solar terms, sunrise/sunset for Tokyo | NAOJ 暦要項 / 暦計算室 |
| `kyureki-newyear.json` | 旧正月 dates, and leap months, over a span that includes 2033–34 | NAOJ 暦Wiki or a published almanac, confirmed in S3 |

## 10. User interface

- **Look:** the original draft's console aesthetic, carried into Compose.
  Monospace type, dark background by default, palette from the draft's xterm
  colours: moss `#87AF87`, ozone `#5F8787`, ivory `#D0D0D0`, amber `#D78700`.
  Two panels, Tibetan and 旧暦, stacked on a phone screen.
- **Navigation:** opens on today; swipe for ±1 day; a date picker for anything
  else.
- **Widget (Glance):** today's Tibetan date, kyūreki date and rokuyō; refreshes
  at local midnight.
- **Location:** entered by hand, or taken once from the device's coarse
  location on request. Stored only on the device. It affects the local sky
  panel only — neither calendar depends on it.
- **Local sky panel:** sunrise, sunset, true solar noon (the sun's transit, not
  12:00) and the sun's altitude then. Facts only; the draft's "Yin/Yang" notes
  are gone.
- **Tibetan terms** in English and Wylie for v1; Tibetan script is later
  (§3), after checking font coverage on the phone.

## 11. Milestones and done criteria

| | Milestone | Done when |
| --- | --- | --- |
| M0 | Toolchain: JDK 21, Android SDK, Gradle wrapper, empty modules, CI-less build | `./gradlew build` passes; `~/RUNBOOK.md` updated for the host changes |
| M1 | Tibetan engine | All Tibetan vectors pass; `cli` prints today |
| M2 | Astronomy library and kyūreki engine | NAOJ and kyūreki vectors pass; the cross-engine kanshi test passes |
| M3 | Texts | S1–S4 settled; every loaded entry carries a source |
| M4 | App and widget | Installed on the phone over adb; screenshot taken with `adb exec-out screencap -p`; owner confirms the screen and widget on the device |

Each milestone is committed only when asked.

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
