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
| `cli/` | Desktop tool to print a day, a kyūreki sui or a Tibetan year. |
| `app/` | Compose UI. Package `zanshin.app`, application id `io.github.iverlein.zanshin`. |
| `fastlane/metadata/android/en-US/` | F-Droid store listing: texts, icon, screenshots, `changelogs/<versionCode>.txt`. |
| `docs/fdroid/` | The recipe proposed to fdroiddata (SPEC §12). |
| `tools/` | Python generators: VSOP87 table, city list, font subset, Henning vector extraction. Their outputs are committed. |

## Commands

```bash
./gradlew :core:test                                   # engines against the vectors
./gradlew :cli:run --args="2026-09-28"                 # one day, as text
./gradlew :cli:run --args="--sui 2033"                 # kyūreki months from month 11
./gradlew :app:assembleDebug                           # APK in app/build/outputs/apk/debug/
adb -s <serial> install -r app/build/outputs/apk/debug/app-debug.apk
```

The toolchain on MONOLITH (JDK 21 pin, SDK in `~/Android/Sdk`, the
`zanshin-test` emulator) is described in `~/RUNBOOK.md` §7. Always pass
`-s <serial>` to adb: the phone and the emulator are often attached together.

## Rules that are easy to break

- **No `INTERNET` permission, ever** (SPEC §2). Check the merged manifest after
  adding a library: `aapt2 dump permissions app/build/outputs/apk/debug/app-debug.apk`.
- **No text without a published source** (SPEC §8). Readings live in
  `core/.../texts/Texts.kt`, each with a `Source` and `License`; `TextsTest`
  fails if an annotation lacks one. Never write a reading from memory, and never
  copy copyrighted wording: state its facts in your own English (F-Droid needs
  every asset licensed).
- **Every Tibetan term and kanji must show its English on tap** (`GlossText`),
  without a dotted underline.
- **New kanji need a font rebuild.** Shippori Mincho is subset to the
  characters in the sources: run `tools/subset_fonts.py` with the full fonts
  from google/fonts `ofl/shipporimincho/` after adding any.
- **Calendar changes need a vector**, not only a passing build. The 2033
  leap-month choice is a named constant in `Kyureki.kt`, pending confirmation
  from its published source (SPEC §7.1, S3).
- **No text under a non-commercial licence** (CC BY-NC and the like): F-Droid
  would label the app *Non-Free Assets*.
- **No personal data in the repository**: it is public. Examples and tests use
  Kyoto or Lhasa, never the owner's location.
- **Releases** (SPEC §12): bump `versionCode` and `versionName` in
  `app/build.gradle.kts`, add `changelogs/<versionCode>.txt`, commit, tag
  `v<versionName>`. The store description must not name unbuilt features.
- Commit only when the owner asks; no assistant signature in commits.
