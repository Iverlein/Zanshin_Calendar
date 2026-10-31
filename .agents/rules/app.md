---
description: The app, the engines and the CLI — kanji gloss on tap, font rebuilds for new kanji, calendar changes need a vector, emulator-only testing and the tools in tools/emulator/, drawing cue glyphs
trigger: glob
globs: '**/app/**, **/core/**, **/cli/**, **/tools/emulator/**, **/tools/subset_fonts.py, **/tools/glyph_sheet.py'
paths:
- app/**
- core/**
- cli/**
- tools/emulator/**
- tools/subset_fonts.py
- tools/glyph_sheet.py
---

# The app, the engines and the CLI

`app/` is Compose UI. Package `zanshin.app`, application id and `R` namespace `io.github.iverlein.zanshin`; interface text in `res/values/strings.xml`, translations in `res/values-<lang>/` (Weblate, [docs/weblate.md](docs/weblate.md)), checked by `src/test/.../TranslationsTest.kt`. `bell/`: the meditation timer's service, the mindfulness bell's alarms and its ring service with the bell's notice, the synthesised bells (SPEC §10.9) and the morning notice's alarm and notification (`MorningNotice`, SPEC §10.5), tested by `BellTest`. `widget/`: the home-screen widget (`DayWidget`, SPEC §10.6), plain RemoteViews drawn by its own alarm, `WidgetTest` for its times; every row's visibility is set on each update, since the launcher applies an update to the views it already holds.

`tools/emulator/` drives the app on the `zanshin-test` emulator: `emu.py` (preferences, also into the release build by root, language, taps by on-screen text, screenshots with their text, the two widgets placed on the home screen), `tour.py` (a fixed tour to compare two builds), `russian_tour.py` (the language switch and a Russian read-through), `store_shots.py` (the nine store screenshots in one language), `scales.py` (both pages and the screens at font scales 1.0 to 2.0, in landscape and at a tablet's 800 dp, SPEC §10.1).

## Rules

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
- **Calendar changes need a vector**, not only a passing build. The 2033
  leap-month choice is a named constant in `Kyureki.kt`, citing the 暦文協's
  2015 recommendation of 閏11月; the general intercalation rule stays open
  (SPEC §7.1).
