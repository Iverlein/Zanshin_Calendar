---
description: The app, the engines and the CLI — kanji gloss on tap, font rebuilds for new kanji, calendar changes need a vector, emulator-only testing and the tools in tools/emulator/
trigger: glob
globs: '**/app/**, **/core/**, **/cli/**, **/tools/emulator/**, **/tools/subset_fonts.py'
paths:
- app/**
- core/**
- cli/**
- tools/emulator/**
- tools/subset_fonts.py
---

# The app, the engines and the CLI

`app/` is Compose UI. Package `zanshin.app`, application id and `R` namespace `io.github.iverlein.zanshin`; interface text in `res/values/strings.xml`, translations in `res/values-<lang>/` (Weblate, [docs/weblate.md](docs/weblate.md)), checked by `src/test/.../TranslationsTest.kt`. `bell/`: the meditation timer's service, the mindfulness bell's alarms and its ring service with the bell's notice, and the synthesised bells (SPEC §10.9), tested by `BellTest`.

`tools/emulator/` drives the app on the `zanshin-test` emulator: `emu.py` (preferences, language, taps by on-screen text, screenshots with their text), `tour.py` (a fixed tour to compare two builds), `russian_tour.py` (the language switch and a Russian read-through), `store_shots.py` (the seven store screenshots in one language).

## Rules

- **Test on the emulator only**, never on the owner's phone: tours, taps,
  changed dates, preferences and languages all go to `zanshin-test`. The
  emulator is one for all sessions: queue for `emulator-5554` by message and
  restore the snapshot's preferences and language when done.
- **Install every new build on the phone** when it is connected (`adb
  devices`), after each update, without being asked, so it always runs the
  latest build. Always pass `-s <serial>`.
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
- **Calendar changes need a vector**, not only a passing build. The 2033
  leap-month choice is a named constant in `Kyureki.kt`, citing the 暦文協's
  2015 recommendation of 閏11月; the general intercalation rule stays open
  (SPEC §7.1).
