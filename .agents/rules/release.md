---
description: Store listing, website, releases — docs on every feature merge in full (SPEC, ROADMAP, README, listing, screenshots, website, GitHub description), the release-build check for R8, how a release is tagged
trigger: glob
globs: '**/fastlane/**, **/website/**, **/docs/fdroid/**, **/app/build.gradle.kts, **/app/proguard-rules.pro, **/README.md, **/docs/SPEC.md, **/docs/ROADMAP.md'
paths:
- fastlane/**
- website/**
- docs/fdroid/**
- app/build.gradle.kts
- app/proguard-rules.pro
- README.md
- docs/SPEC.md
- docs/ROADMAP.md
---

# Store listing, website and releases

`fastlane/metadata/android/en-US/` is the F-Droid store listing: texts, icon, screenshots, `changelogs/<versionCode>.txt`. `website/` is a Hugo site in English (`/`) and Russian (`/ru/`), built and rsynced to the VPS by `.github/workflows/website.yml` on pushes to `main`. Each language reads its texts, screenshots and changelog from its `fastlane/` listing (`en-US`, `ru-RU`) and the version from `app/build.gradle.kts`; only the interface words (`i18n/`), the screenshot captions (`data/screenshots.toml`) and the layout live here. A new store language needs its `[languages]` entry in `hugo.toml`, an `i18n/` file and captions. `docs/fdroid/` is a copy of the fdroiddata recipe; the app is on F-Droid since 2026-10-02, and recipe changes go through an fdroiddata MR (SPEC §12).

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
   script; the rule file for its paths (or `AGENTS.md`), for a new check.
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

## Rules

- **Check a release build before tagging.** R8 renames classes; the catalog
  keys engine terms by their enum's class name, kept by
  `app/proguard-rules.pro`. A debug build never shows a breakage there: build
  `assembleRelease`, sign it with the debug key (zipalign, apksigner) and look
  at both pages and a sheet on the emulator for raw keys such as `lh1.SENBU`.
  The release build is not debuggable, so `run-as` cannot write its
  preferences: `emu.prefs_release(...)` copies them in by root, and
  `emu.language(tag, emu.RELEASE_PKG)` and `emu.start(package=emu.RELEASE_PKG)`
  drive it; uninstall it from the emulator afterwards.
- **Releases** (SPEC §12): bump `versionCode` and `versionName` in
  `app/build.gradle.kts`, add `changelogs/<versionCode>.txt`, commit, tag
  `v<versionName>`, push the tag (GitHub releases and their APKs follow by themselves, SPEC §12). The store description is kept current on every feature merge (*Docs on every feature merge*) and must not name unbuilt features.
- Commit verified work without asking, from the session's own worktree
  (*One worktree per session*); push only when the owner says; no
  assistant signature in commits.
