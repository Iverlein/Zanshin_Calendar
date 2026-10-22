---
description: Readings and the written sources — published sources only, naming every Tibetan word, the shape of a reading (SPEC §8.1), the knowledge pool, the White Beryl above all and no Bon text, the tools in tools/sources/
trigger: glob
globs: '**/docs/sources/**, **/tools/sources/**, **/core/src/main/resources/texts/**, **/core/src/main/kotlin/zanshin/core/texts/**, **/.claude/skills/readings/**'
paths:
- docs/sources/**
- tools/sources/**
- core/src/main/resources/texts/**
- core/src/main/kotlin/zanshin/core/texts/**
- .claude/skills/readings/**
---

# Readings and the written sources

`docs/sources/` is the project's knowledge pool: the Tibetan texts behind the readings, one topic per file today and, since 2026-10-22, the layers of `STRUCTURE.md` (a corpus file per source chapter with passage ids, claims, question files, the registry `sources.yaml`; a new reading is written in them, an old topic file split when next touched) (personal mansions, yogas, karaṇas, lunar dates, mansions, the *kun phan me long*, …), in Tibetan script as read on the scans with the English beside, editions, pages and open questions; `wb-contents.md` maps WB's 35 chapters and what has been read of each; `PLAN.md` says how to read and record (rules, tools, the knowledge pool, dead ends), `PROTOCOL.md` how readings run in parallel (as many subagents as the machine bears, a launcher that merges and bookkeeps; the `/readings` skill in `.claude/skills/`), and `docs/ROADMAP.md`'s *Readings* what is still to be read, apart from what is to be built. Read both before building any of them.

`tools/sources/` holds the reading tools: `bdrc.py` (BDRC etexts and scans), `scans.py` (crops, rows, stacks and an OCR line's place on the page, for reading by eye), `hf_read.py` (the main reader: BDRC's Yigdzin-1 OCR model on the GPU, MITRA as witness), `ocr_bench.py` (readers scored against the BDRC benchmark), `agy_choose.py` (agy picks between two readings at each flag), `mansion_verses.py` (builds docs/sources/mansion-verses.md), `gpu.py` (checks no model is left on the GPU), `agy_read.py` (agy, for single hard crops), `bdrc_ocr.py` (BDRC's local OCR), `disagree.py` (where two readings differ, with the scan lines to check), `score_reading.py` (a reader's error rate against a scan reading), `local_read.py` (general vision models via `llm-serve`; none reads Tibetan well), `ndl.py` (NDL catalogue), `archive_lend.py` (BDRC's scans lent on archive.org, read in its reader under the owner's login), `locate.py` (a phrase in the etexts, another print's image mapped to the etext's pages, a word in Steinert's 64 dictionaries), `bdrc_search.py` (BDRC's full-text search, exact phrases, in a headed work browser), `schedule.py` (which readings can run now and how many at once the machine bears, their prompts and stubs, and the GPU slot lock), `kb.py` (the index of the pool, `docs/sources/STRUCTURE.md`: `check` what the files get wrong, `coverage` each source's pages by reading state, `passage`/`cites` who cites what, `next` what to read by what waits on it, `search`; `check` runs beside `SourcesTest`). Downloads stay in a scratch directory.

## Rules

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
- **Every reading in the shape of SPEC §8.1** (*The shape of a reading*,
  13 rules): the ruling first in a paragraph of 20–60 words, every other
  fact a row in the fixed order and labels, *How it falls* today's line
  only, boilerplate once per family, one vocabulary (goes well, goes
  badly, Good for, Avoid, Remedy:), a verse's image marked as an image,
  nothing about the app and no editor's note in a reading, plain
  sentences, names uniform, the family's pattern matched, the Russian
  the same shape. Read the rule and its three examples before writing
  any reading; the old readings are brought to it by ROADMAP X3–X10.
  A row is a catalog entry `<reading>.<row>` (SPEC §8.2), never a
  sentence of the paragraph. `CatalogTest` holds the shape: rows only
  of the sixteen (`ReadingRow`), the Russian with every row, no
  paragraph over 60 words, none of rule 8's and 9's words, and D7's
  one name and one bracket per Tibetan word across the catalog, the
  interface and the listing (`ReadingShape.kt`). Families not yet in
  the shape are allowed their breaks in its `ALLOWANCE`, one line per
  ROADMAP item with a count: a block of X3–X10 deletes its item's line
  once its families pass, a count may only fall, and nothing new is
  ever added to it; a new family is mapped to its item in `FAMILIES`.
- **Readings fill the knowledge pool** (docs/sources/PLAN.md, *The
  knowledge pool*): everything a session learns from a source goes into
  `docs/sources/` in that session and is committed, each passage in its
  verified Tibetan script with the English beside it, finds made in
  passing too (WB's structure in `wb-contents.md`), searches without a
  result under *Dead ends*. A reading is marked done in the roadmap only
  when its topic file holds it; `SourcesTest` checks that.
- **No text under a non-commercial licence** (CC BY-NC and the like): F-Droid
  would label the app *Non-Free Assets*.
- **The White Beryl counts above every other source but the Four Tantras on
  medicine, and no Bon text is a source** (SPEC §8.1). A source that differs
  from WB on a medical point is traced to what it rests on first: from the
  Four Tantras it is followed, from anything else WB stands. **Mipham
  comes next** (owner, 2026-10-20): his notes on WB (vol. 2, pp. 474–541)
  and his other writings decide where WB is silent or terse, above the
  *Moonbeams*, the manuals and modern authors such as Gyurme Dorje (SPEC §8). The Bon *snang srid me long* (`docs/sources/README.md`,
  SN) is a witness to WB only: read it to check WB, never build or cite
  what only it gives.
