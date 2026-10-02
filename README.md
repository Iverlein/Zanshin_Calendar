# Zanshin Calendar

An offline Android app that shows one day at a time in the Tibetan calendar
(Phugpa) or the old Japanese calendar (旧暦, Tenpō rules), with the almanac
readings of that day and sunrise, solar noon and sunset for your place.

<img src="fastlane/metadata/android/en-US/images/phoneScreenshots/1.png" width="220" alt="Tibetan day: Lhabab Düchen">
<img src="fastlane/metadata/android/en-US/images/phoneScreenshots/2.png" width="220" alt="Japanese day: 十三夜">
<img src="fastlane/metadata/android/en-US/images/phoneScreenshots/4.png" width="220" alt="Japanese almanac annotations">

- **Tibetan:** lunar day, month and year with skipped and doubled days and
  leap months; festivals and monthly observances; lunar mansion, yoga,
  karaṇa, element pair, lunar-day animal, trigram and number; hair-cutting
  days and personal days for a birth year.
- **旧暦:** month and day, 六曜, the current solar term, moon phase, 十二直,
  二十八宿, 九星, 選日 and 暦注下段, 雑節, 干支 and 恵方.
- Every Tibetan term and kanji shows its English on tap; every reading names
  its published source.
- Dates from 1900 to 2100. No internet permission: everything is computed on
  the phone.

## Build

JDK 21 and the Android SDK (platform 37) are needed.

```bash
./gradlew :core:test          # calendar engines
./gradlew :app:assembleDebug  # app/build/outputs/apk/debug/app-debug.apk
./gradlew :cli:run --args="2026-10-23"   # one day as text, on the desktop
```

The test vectors (published tables from Janson, Henning, NAOJ and
こよみのページ) are not in this repository; tests that need them are skipped.

The design, the formulas and their sources are in [docs/SPEC.md](docs/SPEC.md).

## Support

[Give me a tip](https://zanshin.fyi/donate/)

## Licence

Zanshin Calendar is free software under the [Mozilla Public License 2.0](LICENSE).

- Fonts: Figtree and Shippori Mincho, SIL Open Font License 1.1
  ([app/src/main/assets/licenses/](app/src/main/assets/licenses/)).
- City list: [GeoNames](https://www.geonames.org/), CC BY 4.0.
- Readings adapted from Japanese Wikipedia: CC BY-SA 4.0, marked in the app.
  All other readings are the project's own summaries of the cited sources.
