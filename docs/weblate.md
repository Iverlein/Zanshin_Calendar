# Translations on Hosted Weblate

How the project is set up on [Hosted Weblate](https://hosted.weblate.org)
(ROADMAP L4) and how a translation gets into a release. The rules behind it
are in SPEC §8.2; the settings below follow the Weblate documentation for
2026.10.

## Project

| Setting | Value | Why |
| --- | --- | --- |
| Plan | Libre (gratis for libre projects) | The project is public: any signed-in user can contribute |
| Enable reviews | on | A reading reaches the app only once a reviewer has approved it (SPEC §8.1) |
| Translation quality filter | Only include approved translations | Reviews alone do not keep unapproved strings out of commits; this filter does |
| Enable suggestions | on | Contributors unsure of a wording can suggest instead |
| Adding new translation | Contact maintainers | A language starts only with someone to review it |
| Translation license | MPL-2.0 for the interface and the store listing; the catalog is open, below | |

The Libre plan keeps the project public, so reviews are the gate: per
language, the reviewers are those who read it well (the owner and invited
friends for Japanese). A language whose reviews are turned off in its own
workflow commits every string, reviewed or not; keep reviews on everywhere.

## Components

All three read `main` of https://github.com/Iverlein/Zanshin_Calendar and
push through a GitHub pull request from Weblate's fork, never to `main`
directly (version control: *GitHub pull request*).

| | Interface | Catalog | Store listing |
| --- | --- | --- | --- |
| Slug | `interface` | `catalog` | `store-listing` |
| File format | Android String Resource | Java Properties (UTF-8) | App store metadata files |
| File mask | `app/src/main/res/values-*/strings.xml` | `core/src/main/resources/texts/texts_*.properties` | `fastlane/metadata/android/*` |
| Monolingual base file | `app/src/main/res/values/strings.xml` | `core/src/main/resources/texts/texts.properties` | `fastlane/metadata/android/en-US` |
| Template for new translations | empty | empty | `fastlane/metadata/android/en-US` |
| Language code style | Android style (`ja`, `zh-rTW`) | POSIX (`ja`, `zh_Hant`) | Google Play metadata style (`ja-JP`) |
| Translation flags | — | `ignore-java-format, placeholders:r"\{\d\}"` | — |
| Key filter | — | — | `^(?!changelogs/.*$).+$` |

- **Catalog flags.** Weblate treats `.properties` as Java `MessageFormat`,
  which would flag every single apostrophe; `Catalog.kt` only replaces
  `{0}`, `{1}`, so the MessageFormat check is off and the placeholder check
  keeps the `{n}` in place.
- **Store listing.** Changelogs stay English and are filtered out. Set
  `max-length:50` on `title.txt` and `max-length:80` on
  `short_description.txt` (bulk edit): F-Droid's limits.
- **Chinese** is two languages, Simplified and Traditional, each translated
  and reviewed on its own. The maintainer creates their files in the
  repository (`values-zh-rCN` and `values-zh-rTW` with `locale_tag`
  `zh-Hans` and `zh-Hant`; `texts_zh_Hans.properties`,
  `texts_zh_Hant.properties`; `zh-CN`, `zh-TW`), and Weblate discovers them.
- **Glossary**: the project's glossary holds the terms the app already
  uses, the kanji and Tibetan terms with their English, so every language
  names them the same way.
- **Webhook**: the GitHub repository notifies
  `https://hosted.weblate.org/hooks/github/` on push, so Weblate follows
  `main`.

## The catalog's licence

Weblate takes one licence per component, and the catalog mixes the app's own
summaries (MPL-2.0) with wording adapted from Japanese Wikipedia (CC BY-SA
4.0, whose translations stay CC BY-SA). Settled by the owner on 2026-09-30:
the catalog is split. The readings whose `License` is `CC_BY_SA` move to a
file family of their own (`texts/wikipedia.properties` and its
translations), which `Catalog.kt` reads beside `texts*.properties`, and get
a fourth component under CC BY-SA 4.0; the rest of the catalog stays
MPL-2.0. The split is made when the project is set up.

## Eligibility and timing

The Libre plan asks for a libre licence, a README that says translations
are on Weblate, and at least three months of active development; a trial
project not approved within 14 days is removed. Development here began on
2026-09-28, so the trial starts no earlier than 2026-12-28, with the README
line added then.

## Existing translations

Russian is translated by the project and already complete. Weblate imports it
as *translated*, not *approved*; with the quality filter above, Weblate would
leave unapproved strings out of the files it writes. Right after import, bulk
edit every Russian string of the three components from *Translated* to
*Approved* (query `state:translated`), and check the first Weblate pull
request for removed Russian lines.

## Pull requests from Weblate

Before merging one:

- `./gradlew :core:test :app:testDebugUnitTest` passes. `CatalogTest` and
  `TranslationsTest` check that a translation has only English keys, the
  same placeholders and a `locale_tag` in its own language, and that every
  language offered in the menu is complete.
- A partial language is fine: missing strings show in English. It is
  offered in the menu (`AppLanguage.TAGS` and `res/xml/locales_config.xml`)
  only once complete and read through on a phone.
- Readings are checked against their sources, as in SPEC §8.1; a Weblate
  approval is the translator's side of that, the merge is the maintainer's.
