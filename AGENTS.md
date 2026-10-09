# Zanshin Calendar — context for assistants

An offline Android app showing one day in the Tibetan (Phugpa) calendar or
the Japanese 旧暦, with sunrise, sunset and solar noon. The specification is
[docs/SPEC.md](docs/SPEC.md); read it before changing behaviour, and keep it
in step with the code.

## Layout

| Path | What |
| --- | --- |
| `core/` | Pure Kotlin/JVM engines: `tibetan/` (Janson's arithmetic), `kyureki/` (Tenpō rules), `astro/` (Meeus, VSOP87, ΔT), `rational/`, `time/`. No Android, no dependencies. |
| `core/src/test/resources/vectors/` | Test vectors, tab-separated, source cited in each header. Third-party tables: gitignored, kept only locally; tests that need a missing file are skipped (`vectors()` in `Vectors.kt`). |
| `cli/` | Desktop tool to print a day, a kyūreki sui, a Tibetan year or the election for a work. |
| `core/src/main/resources/texts/` | The catalog: English names of the terms, reading summaries and list wordings, one `texts_<language>.properties` per translation (SPEC §8.2). |
| `app/` | Compose UI. Package `zanshin.app`, application id and `R` namespace `io.github.iverlein.zanshin`; interface text in `res/values/strings.xml`, translations in `res/values-<lang>/` (Weblate, [docs/weblate.md](docs/weblate.md)), checked by `src/test/.../TranslationsTest.kt`. |
| `fastlane/metadata/android/en-US/` | F-Droid store listing: texts, icon, screenshots, `changelogs/<versionCode>.txt`. |
| `website/` | Hugo site in English (`/`) and Russian (`/ru/`), built and rsynced to the VPS by `.github/workflows/website.yml` on pushes to `main`. Each language reads its texts, screenshots and changelog from its `fastlane/` listing (`en-US`, `ru-RU`) and the version from `app/build.gradle.kts`; only the interface words (`i18n/`), the screenshot captions (`data/screenshots.toml`) and the layout live here. A new store language needs its `[languages]` entry in `hugo.toml`, an `i18n/` file and captions. |
| `docs/sources/` | The Tibetan texts behind the open T2 and T3 readings, one topic per file (personal mansions, yogas, karaṇas, lunar dates, mansions, the *kun phan me long*), quoted from the scans with editions, pages and open questions; `PLAN.md` there says how to read them (rules, tools, dead ends), and the work plan in `docs/ROADMAP.md` (T2) what is still to be read and built. Read both before building any of them. |
| `docs/fdroid/` | A copy of the fdroiddata recipe; the app is on F-Droid since 2026-10-02, and recipe changes go through an fdroiddata MR (SPEC §12). |
| `tools/` | Python generators: VSOP87 table, city list, font subsets (the app's, and the website's Cyrillic in `subset_web_fonts.py`), Henning and Gyurme Dorje vector extraction. Their outputs are committed, except the vectors. `release_apks.py` keeps a GitHub release per tag with F-Droid's APK attached (SPEC §12). |
| `tools/sources/` | Reading the written sources (docs/sources/): `bdrc.py` (BDRC etexts and scans), `scans.py` (crops, rows and stacks for reading by eye), `hf_read.py` (the main reader: BDRC's Yigdzin-1 OCR model on the GPU, MITRA as witness), `ocr_bench.py` (readers scored against the BDRC benchmark), `agy_choose.py` (agy picks between two readings at each flag), `mansion_verses.py` (builds docs/sources/mansion-verses.md), `gpu.py` (checks no model is left on the GPU), `agy_read.py` (agy, for single hard crops), `bdrc_ocr.py` (BDRC's local OCR), `disagree.py` (where two readings differ, with the scan lines to check), `score_reading.py` (a reader's error rate against a scan reading), `local_read.py` (general vision models via `llm-serve`; none reads Tibetan well), `ndl.py` (NDL catalogue). Downloads stay in a scratch directory. |
| `tools/emulator/` | Driving the app on the `zanshin-test` emulator: `emu.py` (preferences, language, taps by on-screen text, screenshots with their text), `tour.py` (a fixed tour to compare two builds), `russian_tour.py` (the language switch and a Russian read-through), `store_shots.py` (the five store screenshots in one language). |

## Commands

```bash
./gradlew :core:test                                   # engines against the vectors
./gradlew :app:testDebugUnitTest                       # translations: keys, placeholders, offered languages complete
./gradlew :cli:run --args="2026-09-28"                 # one day, as text
./gradlew :cli:run --args="--sui 2033"                 # kyūreki months from month 11
./gradlew :cli:run --args="--elect WEDDING 2026-10-01 --months 2 --all"  # the best days for a work
./gradlew :app:assembleDebug                           # APK in app/build/outputs/apk/debug/
adb -s <serial> install -r app/build/outputs/apk/debug/app-debug.apk
(cd website && hugo server)                            # the website at localhost:1313
```

The toolchain on MONOLITH (JDK 21 pin, SDK in `~/Android/Sdk`, the
`zanshin-test` emulator) is described in `~/RUNBOOK.md` §7. Always pass
`-s <serial>` to adb: the phone and the emulator are often attached together.

- **Test on the emulator only**, never on the owner's phone: tours, taps,
  changed dates, preferences and languages all go to `zanshin-test`.
- **Install every new build on the phone** when it is connected (`adb
  devices`), after each update, without being asked, so it always runs the
  latest build.

## Rules that are easy to break

- **No `INTERNET` permission, ever** (SPEC §2). Check the merged manifest after
  adding a library: `aapt2 dump permissions app/build/outputs/apk/debug/app-debug.apk`.
- **No text without a published source** (SPEC §8). Readings live in
  `core/.../texts/Texts.kt`, each with a `Source` and `License`, their text in
  `core/src/main/resources/texts/texts.properties`; `TextsTest` fails if an
  annotation lacks a source, `CatalogTest` if a text lacks its catalog entry. Never write a reading from memory, and never
  copy copyrighted wording: state its facts in your own English (F-Droid needs
  every asset licensed).
- **Name every Tibetan word so that it can be found** (SPEC §8.1):
  English name first, then its Tibetan script and Wylie in brackets,
  "the la, the life-spirit (བླ, bla)". Every word, not only titles:
  terms, spirits, deities, persons, publishers, words written in
  phonetics (Losar, tsok); everywhere: readings, notes, list wordings,
  source lines, rows, labels, balloons, diagrams, the store listing.
  Never script or Wylie alone; the bracket at the first mention in each
  text, the name alone after it; in code `Ewts.named(english, wylie)`.
  `CatalogTest` and `TranslationsTest` check it. Identify an abbreviated
  title from the source or a catalogue (BDRC, 84000), never from memory.
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
- **No text under a non-commercial licence** (CC BY-NC and the like): F-Droid
  would label the app *Non-Free Assets*.
- **The White Beryl counts above every other source, and no Bon text is a
  source** (SPEC §8.1). The Bon *snang srid me long* (`docs/sources/README.md`,
  SN) is a witness to WB only: read it to check WB, never build or cite
  what only it gives.
- **No personal data in the repository**: it is public. Examples and tests use
  Kyoto or Lhasa, never the owner's location.
- **Check a release build before tagging.** R8 renames classes; the catalog
  keys engine terms by their enum's class name, kept by
  `app/proguard-rules.pro`. A debug build never shows a breakage there: build
  `assembleRelease`, sign it with the debug key (zipalign, apksigner) and look
  at both pages and a sheet on the emulator for raw keys such as `lh1.SENBU`.
- **Releases** (SPEC §12): bump `versionCode` and `versionName` in
  `app/build.gradle.kts`, add `changelogs/<versionCode>.txt`, commit, tag
  `v<versionName>`, push the tag (GitHub releases and their APKs follow by themselves, SPEC §12). The store description must not name unbuilt features.
- Commit only when the owner asks; no assistant signature in commits.
