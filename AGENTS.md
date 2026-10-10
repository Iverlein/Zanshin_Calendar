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
| `app/` | Compose UI. Package `zanshin.app`, application id and `R` namespace `io.github.iverlein.zanshin`; interface text in `res/values/strings.xml`, translations in `res/values-<lang>/` (Weblate, [docs/weblate.md](docs/weblate.md)), checked by `src/test/.../TranslationsTest.kt`. `bell/`: the meditation timer's service, the mindfulness bell's alarms and the synthesised bells (SPEC §10.9), tested by `BellTest`. |
| `fastlane/metadata/android/en-US/` | F-Droid store listing: texts, icon, screenshots, `changelogs/<versionCode>.txt`. |
| `website/` | Hugo site in English (`/`) and Russian (`/ru/`), built and rsynced to the VPS by `.github/workflows/website.yml` on pushes to `main`. Each language reads its texts, screenshots and changelog from its `fastlane/` listing (`en-US`, `ru-RU`) and the version from `app/build.gradle.kts`; only the interface words (`i18n/`), the screenshot captions (`data/screenshots.toml`) and the layout live here. A new store language needs its `[languages]` entry in `hugo.toml`, an `i18n/` file and captions. |
| `docs/sources/` | The Tibetan texts behind the open T2 and T3 readings, one topic per file (personal mansions, yogas, karaṇas, lunar dates, mansions, the *kun phan me long*), quoted from the scans with editions, pages and open questions; `PLAN.md` there says how to read them (rules, tools, dead ends), and the work plan in `docs/ROADMAP.md` (T2) what is still to be read and built. Read both before building any of them. |
| `docs/fdroid/` | A copy of the fdroiddata recipe; the app is on F-Droid since 2026-10-02, and recipe changes go through an fdroiddata MR (SPEC §12). |
| `tools/` | Python generators: VSOP87 table, city list, font subsets (the app's, and the website's Cyrillic in `subset_web_fonts.py`), Henning and Gyurme Dorje vector extraction. Their outputs are committed, except the vectors. `release_apks.py` keeps a GitHub release per tag with F-Droid's APK attached (SPEC §12). `tcg_vectors.py` compiles Edward Henning's Tibetan calendar software unchanged to make the planets' and Rāhu's vectors (henning-tcg-*.tsv). |
| `tools/sources/` | Reading the written sources (docs/sources/): `bdrc.py` (BDRC etexts and scans), `scans.py` (crops, rows and stacks for reading by eye), `hf_read.py` (the main reader: BDRC's Yigdzin-1 OCR model on the GPU, MITRA as witness), `ocr_bench.py` (readers scored against the BDRC benchmark), `agy_choose.py` (agy picks between two readings at each flag), `mansion_verses.py` (builds docs/sources/mansion-verses.md), `gpu.py` (checks no model is left on the GPU), `agy_read.py` (agy, for single hard crops), `bdrc_ocr.py` (BDRC's local OCR), `disagree.py` (where two readings differ, with the scan lines to check), `score_reading.py` (a reader's error rate against a scan reading), `local_read.py` (general vision models via `llm-serve`; none reads Tibetan well), `ndl.py` (NDL catalogue), `archive_lend.py` (BDRC's scans lent on archive.org, read in its reader under the owner's login). Downloads stay in a scratch directory. |
| `tools/emulator/` | Driving the app on the `zanshin-test` emulator: `emu.py` (preferences, language, taps by on-screen text, screenshots with their text), `tour.py` (a fixed tour to compare two builds), `russian_tour.py` (the language switch and a Russian read-through), `store_shots.py` (the seven store screenshots in one language). |

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

## One worktree per session

Several sessions often build at once, and a shared checkout mixes their
work: one git index (a plain `git commit` took another block's staged
files on 2026-10-09), one Gradle build directory, half-finished files in
each other's builds. So every session, from its first edit:

1. **Works in its own worktree**, never in the main checkout
   (`~/Documents/Git/Zanshin_Calendar`), which stays on `main` with no
   uncommitted changes:

   ```bash
   cd ~/Documents/Git/Zanshin_Calendar
   W=../Zanshin_Calendar-wt/<name>          # <name>: the block or task, e.g. block-10
   git worktree add -b work/<name> $W main
   cp local.properties $W/                  # gitignored: the SDK path
   mkdir -p $W/core/src/test/resources && cp -r core/src/test/resources/vectors $W/core/src/test/resources/
   ```

   The vectors are gitignored third-party tables: without the copy their
   tests are skipped, not run. Every build, test and edit happens in `$W`.
2. **Commits its work without asking** once it is verified (the tests, and
   the emulator, phone and release checks below where they apply) and,
   for a feature, its docs are current (*Docs on every feature merge*): in the
   repository's style, separate `core:` / `app:` / `docs:` commits with
   substantive bodies, no assistant signature.
3. **Checks for conflicts and merges**: `git rebase main` in `$W`; a
   conflict is resolved keeping both sides' work (another block's changes
   are never dropped or reverted), and after any rebase that touched files
   other blocks also changed, the tests run again in `$W`. Then, from the
   main checkout, `git merge --ff-only work/<name>`; if it refuses, main
   moved again: rebase and test once more.
4. **Cleans up** once main holds its commits: `git worktree remove --force $W`
   (forced because of the copied, untracked `local.properties` and vectors;
   Gradle's build output goes with it) and `git branch -d work/<name>`,
   which refuses a branch that is not merged.

Pushing still waits for the owner's word. The emulator is one for all:
sessions queue for `emulator-5554` by message, and each restores the
snapshot's preferences and language when it is done.

## Docs on every feature merge

A feature is not merged until every place that describes the app says
what it now does; this is part of step 2 above, not a release chore.
On every merge of a feature, in the same branch:

1. **`docs/SPEC.md`**: the behaviour, in its section, and the menu or
   scope where they change.
2. **`docs/ROADMAP.md`**: the item and its block marked built, with
   what was checked.
3. **`README.md`**: the feature list, and the screenshots' alt text.
4. **`AGENTS.md`**: the layout table, for a new package, tool or
   script; these rules, for a new check.
5. **The F-Droid listing**, both languages,
   `fastlane/metadata/android/{en-US,ru-RU}/`: `full_description.txt`
   (each part a `<b>` block; a block with a list becomes a card on the
   website) and `short_description.txt` (80 characters at most). F-Droid
   shows them from the next release's build, the website at once.
6. **Screenshots**, both languages: retake them with
   `tools/emulator/store_shots.py en|ru OUTDIR` whenever a screen they
   show has changed, review every PNG, then copy them into
   `images/phoneScreenshots/`; a new screen gets a shot of its own in the
   script. Captions and alt text in `website/data/screenshots.toml`.
7. **The website** (`website/`): build it with `hugo`, and change the
   layout or CSS where the listing's new text does not fit; look at both
   languages, wide and at phone width, in a real browser. It deploys on
   the push.
8. **The GitHub description and topics**, through the API with the
   keyring's `Zanshin_Calendar_PAT`, never printed:
   `secret-run http T=title:Zanshin_Calendar_PAT -H 'Authorization: Bearer {T}' -X PATCH --data @about.json https://api.github.com/repos/Iverlein/Zanshin_Calendar`
   (topics: `PUT …/topics` with `{"names": […]}`).

Only the changelog waits for the release (*Releases* below).

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
  `v<versionName>`, push the tag (GitHub releases and their APKs follow by themselves, SPEC §12). The store description is kept current on every feature merge (*Docs on every feature merge*) and must not name unbuilt features.
- Commit verified work without asking, from the session's own worktree
  (*One worktree per session*); push only when the owner says; no
  assistant signature in commits.
