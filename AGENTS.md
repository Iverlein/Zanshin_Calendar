# Zanshin Calendar — context for assistants

An offline Android app showing one day in the Tibetan (Phugpa) calendar or
the Japanese 旧暦, with sunrise, sunset and solar noon. The specification is
[docs/SPEC.md](docs/SPEC.md); read it before changing behaviour, and keep it
in step with the code.

## How the rules are laid out

This file is loaded into every session in full, so it holds the layout, the
commands, the session protocol and the bans no path can catch. Rules bound to
a part of the tree live in `.agents/rules/`, one file per area, loaded only
when a file under its paths is read or edited: Claude Code reads the
directory through the symlink `.claude/rules`, Antigravity reads it natively,
and each file's frontmatter carries both loaders' keys (`paths:`;
`trigger: glob` + `globs:`). An agent without such a loader opens the file by
hand before working there:

| Working in | Read | Holds |
| --- | --- | --- |
| `docs/sources/`, `tools/sources/`, the texts catalog, the `readings` and `sources` skills | `.agents/rules/sources.md` | Readings: published sources only, naming Tibetan words, the shape of a reading, the knowledge pool, WB above all and no Bon, the reading tools |
| `app/`, `core/`, `cli/`, `tools/emulator/`, `tools/subset_fonts.py`, `tools/glyph_sheet.py` | `.agents/rules/app.md` | Kanji gloss, font rebuilds, calendar vectors, the emulator tools and emulator-only testing, drawing glyphs |
| `fastlane/`, `website/`, `docs/fdroid/`, `app/build.gradle.kts`, `app/proguard-rules.pro`, `README.md` | `.agents/rules/release.md` | Docs on every feature merge in full, screenshots, the website, the GitHub description, the release-build check, releases |

A new package, tool or script goes into the layout table below; a new check
goes into the rule file for its paths, or here when no path catches it.

## Layout

| Path | What |
| --- | --- |
| `core/` | Pure Kotlin/JVM engines: `tibetan/`, `kyureki/`, `astro/`, `rational/`, `time/`. No Android, no dependencies. Test vectors in `core/src/test/resources/vectors/` (third-party tables gitignored; tests that need a missing file are skipped). |
| `core/src/main/resources/texts/` | The catalog: names of the terms, reading summaries and list wordings, one `texts_<language>.properties` per translation (SPEC §8.2). |
| `cli/` | Desktop tool to print a day, a kyūreki sui, a Tibetan year or the election for a work. |
| `app/` | Compose UI. Package `zanshin.app`, application id `io.github.iverlein.zanshin`; interface text in `res/values/strings.xml`, translations via Weblate ([docs/weblate.md](docs/weblate.md)); `bell/` the meditation timer and mindfulness bell (SPEC §10.9), and the morning notice on its alarms (§10.5); `widget/` the home-screen widget in two sizes (§10.6). |
| `fastlane/metadata/android/` | F-Droid store listing per language: texts, icon, screenshots, `changelogs/<versionCode>.txt`. |
| `website/` | Hugo site, English and Russian, deployed to the VPS by `.github/workflows/website.yml` on pushes to `main`; reads its texts from the `fastlane/` listings. |
| `docs/sources/` | The knowledge pool behind the readings: one topic per file, growing into the layers of `STRUCTURE.md` (`corpus/`, `claims/`, `questions/`, the registry `sources.yaml`, indexed by `tools/sources/kb.py`), `wb-contents.md`, `PLAN.md` (how to read and record), `PROTOCOL.md` (readings in parallel; the `readings` skill in `.claude/skills/`, and `sources`, the method of finding and recording facts in the pool); `docs/ROADMAP.md` *Readings* lists what is still to be read. |
| `docs/fdroid/` | A copy of the fdroiddata recipe; the app is on F-Droid since 2026-10-02 (SPEC §12). |
| `tools/` | Python generators (VSOP87 table, city list, font subsets, vector extraction, `release_apks.py`); `glyph_sheet.py` the cue glyphs as a contact sheet; `tools/sources/` the reading tools; `tools/emulator/` driving the app on the `zanshin-test` emulator. Each is described in its rule file. |

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
**Test on the emulator only**, never on the owner's phone; nothing is
installed on the phone either: since 3.0 it takes the app from F-Droid only.

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
   the emulator and release checks below where they apply) and,
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

A feature is not merged until every place that describes the app says what
it now does; this is part of step 2 above, not a release chore. In the same
branch: `docs/SPEC.md`, `docs/ROADMAP.md`, `README.md`, this file or the rule
file, the F-Droid listing in both languages, the screenshots, the website and
the GitHub description. What each needs — `.agents/rules/release.md`. Only
the changelog waits for the release.

## Rules that are easy to break

- **No `INTERNET` permission, ever** (SPEC §2). Check the merged manifest after
  adding a library: `aapt2 dump permissions app/build/outputs/apk/debug/app-debug.apk`.
- **No text without a published source** (SPEC §8): never a reading from
  memory, never copyrighted wording, nothing under a non-commercial licence.
  The White Beryl counts above every other source (on medicine, all but
  the Four Tantras), Mipham's word next, and no Bon text is a source. In full — `.agents/rules/sources.md`.
- **Name every Tibetan word so that it can be found** (SPEC §8.1): English
  name first, then script and Wylie in brackets at the first mention, in every
  text — `.agents/rules/sources.md`.
- **No personal data in the repository**: it is public. Examples and tests use
  Kyoto or Lhasa, never the owner's location.
- **Commit verified work without asking**, from the session's own worktree
  (*One worktree per session*); push only when the owner says; no assistant
  signature in commits.
