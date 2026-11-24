---
description: The app, the engines and the CLI — kanji gloss on tap, font rebuilds for new kanji, calendar changes need a vector, emulator-only testing and the tools in tools/emulator/, drawing cue glyphs, the painted practice-day frames
trigger: glob
globs: '**/app/**, **/core/**, **/cli/**, **/tools/emulator/**, **/art/**, **/tools/subset_fonts.py, **/tools/glyph_sheet.py, **/tools/practice_frames.py, **/tools/ru_plan.toml'
paths:
- app/**
- core/**
- cli/**
- art/**
- tools/emulator/**
- tools/subset_fonts.py
- tools/glyph_sheet.py
- tools/practice_frames.py
- tools/ru_plan.toml
---

# The app, the engines and the CLI

`app/` is Compose UI. Package `zanshin.app`, application id and `R` namespace `io.github.iverlein.zanshin`; interface text in `res/values/strings.xml`, translations in `res/values-<lang>/` (Weblate, [docs/weblate.md](docs/weblate.md)), checked by `src/test/.../TranslationsTest.kt`. `bell/`: the meditation timer's service, the mindfulness bell's alarms and its ring service with the bell's notice, the synthesised bells (SPEC §10.9) and the morning notice's alarm and notification (`MorningNotice`, SPEC §10.5), tested by `BellTest`. `wheel/`: the prayer wheel (SPEC §10.12): `WheelPlan` the turning and layout without Android, tested by `WheelTest`, `WheelPainter` one canvas painter for the screen and the wallpaper, `WheelWallpaper` the live wallpaper; a test that force-stops the app drops the wallpaper. Keeping the screen on, for the wheel alone and for a sitting, uses the window's flag: `view.keepScreenOn` stays set after a trip to the home screen. `widget/`: the home-screen widget (`DayWidget`, SPEC §10.6), plain RemoteViews drawn by its own alarm, `WidgetTest` for its times; every row's visibility is set on each update, since the launcher applies an update to the views it already holds.

`tools/emulator/` drives the app on the `zanshin-test` emulator: `emu.py` (preferences, also into the release build by root, language, taps by on-screen text, screenshots with their text, the two widgets placed on the home screen), `tour.py` (a fixed tour to compare two builds), `russian_tour.py` (the language switch and a Russian read-through), `store_shots.py` (the ten store screenshots in one language), `scales.py` (both pages and the screens at font scales 1.0 to 2.0, in landscape and at a tablet's 800 dp, SPEC §10.1).

## Rules

- **Russian text is edited by `docs/translation-style.md`**: a string of
  `values-ru/strings.xml` or `texts_ru.properties`, new or changed, is
  written by its rules and checked with `python3 tools/ru_check.py --family
  <prefix>` before commit; the app's Russian is being rewritten block by
  block (ROADMAP L5, `tools/ru_plan.toml`), so a change to a family whose
  block is open goes into that block's letter to the owner.
  `python3 tools/ru_rewrite.py apply CHANGES` writes a block's rewrites
  and reads each back; `ru_rewrite.py letter --block <n>` lists every
  unit changed since `main`, English, before and after, for the letter.
  No AI detector gates the app's Russian either (the owner, 2026-11-21,
  L5.D2): `ai-check` is for information only.
- **Test on the emulator only**, never on the owner's phone: tours, taps,
  changed dates, preferences and languages all go to `zanshin-test`. The
  emulator is one for all sessions: queue for `emulator-5554` by message and
  restore the snapshot's preferences and language when done.
- **Nothing is installed on the phone**: since 3.0 it takes the app from
  F-Droid only (the owner, 2026-10-31). Always pass `-s <serial>` to adb.
- **Check a layout change at font scale 2.0 and in landscape** (SPEC §10.1,
  ROADMAP A2): `tools/emulator/scales.py OUTDIR s20,land`, and `tab` for a
  page's columns, in both languages, every PNG looked at. A row of fixed
  height, a label column of fixed width or a canvas label in sp is where it
  breaks first: give rows a least height, measure label columns
  (`FactColumn`), frame a drawing in a sheet (`DiagramFrame`).
- **Every kanji must show its English on tap** (`GlossText`), without a
  dotted underline; a Tibetan term shows its English inline and its
  pronunciation on tap.
- **New kanji need a font rebuild.** Shippori Mincho is subset to the
  characters in the Kotlin sources, the text catalogs and the string
  resources: run `tools/subset_fonts.py` with the full fonts from
  google/fonts `ofl/shipporimincho/` after adding any (fontTools in a
  throwaway virtualenv; the host Python has none). Tibetan needs no
  rebuild: its script is generated from the Wylie at run time
  (`core/.../tibetan/Ewts.kt`) and the Noto Serif Tibetan subset keeps the
  whole Tibetan block (`subset_fonts.py --tibetan`).
- **Draw a glyph and look at it.** Cue glyphs (`ui/CueGlyphs.kt`, SPEC
  §10.7) are paths on a 24-unit grid, stroke 1.4, round caps, inside 2.5
  to 21.5. `tools/glyph_sheet.py --out X.png` renders the work glyphs
  beside their families' (`--set family|animal`, `--only A,B`, `--dark`),
  large and at 22 px; look at every new one at 22 px before building. A
  work gets its own glyph (`CueGlyphs.WORK`) only where its family's
  draws it badly; the In brief row keeps the families.
- **The practice days' and the festivals' frames are paintings** (SPEC
  §10.3, ROADMAP T6.1, T6.2), never redrawn in code. The sources are
  `art/practice/` and `art/festivals/` as they were painted, on a flat
  magenta key; `tools/practice_frames.py cut art/practice art/festivals`
  (`--only festival_` for one set) first scales a festival painting so its
  disc lies on the practice frames' and evens out an off-key background,
  then keys them out, takes the part each frame uses, clears the
  plain disc round the numeral to a faint glow and writes
  `res/drawable-nodpi/practice_*.webp`; `practice_frames.py sheet OUT.png`
  shows them on four page colours with the numeral in each. The window the
  numeral stands in is in two places, `LARGE` in the script and `frameOf`
  in `PracticeDay.kt`: change both. Any picture the app needs is painted
  by Gemini, never drawn in code (the owner, 2026-11-22; machine rule 15):
  the `paint` skill and the machine's one painting queue (`paint-queue add
  zanshin-<picture> … --out art/practice/ --reference <a frame of the set>`),
  by the brief of T6.1 for a frame; look at the sheet, then at every practice day on
  the emulator in every theme, and the owner accepts it.
- **Calendar changes need a vector**, not only a passing build. The 2033
  leap-month choice is a named constant in `Kyureki.kt`, citing the 暦文協's
  2015 recommendation of 閏11月; the general intercalation rule stays open
  (SPEC §7.1).
