---
name: sources
description: Work with the knowledge pool in docs/sources/ — find the passage behind a fact before writing any text or code, record what a session learns from a source as passages, claims and questions in the layers of STRUCTURE.md, and migrate a topic file when it is next touched. Use when a reading, a build or a question needs the sources, when asked "where does this come from", to record a finding, or to split a topic file.
---

# The knowledge pool: find, record, migrate

Two scripts do the mechanical work; this skill decides. `tools/sources/kb.py`
reads the pool (the index, rebuilt on every call; `--json` for parsing);
`tools/sources/pool.py` writes it (validated, repeatable, nothing written on an
error). The layout, the ids and the frontmatter are docs/sources/STRUCTURE.md;
the rules of quoting, README.md; how to read a scan, PLAN.md. Run both scripts
from the repository root of the session's worktree (AGENTS.md: never the main
checkout).

## 1. Find, before writing

Nothing is written from memory (SPEC §8). Before a reading, a list wording, a
row or a line of code that states a fact of the texts:

1. `kb.py search <words>` for the words, English or Tibetan; `kb.py passage
   <id>` for the pages, which shows where they are quoted, in what state, and
   who cites them; `kb.py cites <file|id|qN>` for the other direction.
2. Judge the state. **scan** may be built on; **machine** may be built on
   when the words that matter are not in doubt, else the scan is read first
   (PLAN.md, *Workflow*); **etext** alone is never built on: it is read on the
   scan, or the fact waits. `kb.py coverage <siglum>` says what has never
   been opened.
3. Rank the witnesses by sources.yaml's `rank` and the rule file: WB first,
   the Four Tantras above it on medicine, Mipham next, a Bon text a witness
   only. A disagreement between sources is a claim of `type: conflict`, not a
   silent choice.
4. A fact nobody has quoted is not found: say so, and either read it now
   (the readings skill) or write a question (step 3).

## 2. Record what was learned

Every fact a session learns from a source goes into the pool in that session
(PLAN.md, *The knowledge pool*). The unit decides the command:

- **A passage** (the text itself, Tibetan with the English beside it): `pool.py
  passage <id> --title … --state scan|machine|etext [--date …] --tibetan FILE
  --english FILE`. The id is the pages as the edition prints them
  (`wb.2:p331`, a range `wb.1:p351-379`, a letter when the same pages hold
  two passages, `wb.2:p331b`; images `kp:i66`, boxes `kp:box12`). The state
  is what was really done, not what was meant: scan only when every quoted
  syllable was read on the page image. The corpus file is chosen by the
  registry's chapters; `pool.py stub <siglum> <chapter>` makes it when it is
  missing. A passage is written whole, not only the line used.
- **A claim** (one fact, rule, list, table, definition or remedy in our own
  words): `pool.py claim <slug> --title … --type … --cites <ids> [--terms …]
  [--feeds …] [--open …] --body FILE`. One fact per claim; `cites` names the
  passages it rests on, never a file; `feeds` names the SPEC section, block or
  item it serves; `terms` the Tibetan terms in Wylie, hyphenated. The script
  warns when a cited passage is quoted nowhere or rests on the etext only:
  then the passage comes first, or the claim says so in its body.
- **A question** for a reader of the tradition (a doubt about the text, not
  about the app): `pool.py question --title … --cites <ids> [--waits RDn|
  block-n] --body FILE`. It is numbered on from the last question anywhere
  (`pool.py next-question`). A subagent reading under the readings skill does
  not number: it reports its questions verbatim and the launcher runs this.
- **An answer**: `pool.py answer qN --date … --body FILE [--cites …]`. A
  question of the old `open-questions.md` is answered in its item by hand,
  in the file's own style.
- **A search with no result** is a result: PLAN.md, *Dead ends*, by hand.

After writing: `kb.py check` clean (an error is fixed, never silenced); the
new files are committed with the work they belong to, in the repository's
style, no signature.

## 3. Migrate when touching an old topic file

A topic file of the old shape (no frontmatter) is not rewritten on sight. When
a session edits one for another reason, it takes the step that fits the time:

1. **Anchor**: `pool.py anchor <file>` shows the ids the index infers for its
   passage headings and what it cannot resolve; `--write` applies them.
   Check every inferred volume against the heading's chapter before writing:
   the inference is right for the files checked, not proven for all.
2. **Split**: `pool.py split <file>` (dry run), then `--write`: each anchored
   passage section moves into the corpus file of its chapter, in page order,
   with `*Read:*` inferred and marked *confirm*; a pointer stays in the topic
   file. Then, by judgement: confirm or correct each `*Read:*` line against
   the section's own words; cut what the topic file says in its own words
   into claims (step 2), each citing its passage; move its *Open* questions
   into question files and its *Searched and not found* into PLAN.md; repoint
   the roadmap rows and other files that cite the topic file by name to the
   passage ids (`kb.py cites <file>` lists them). What remains of the topic
   file is a short overview or nothing.
3. `kb.py check`, `./gradlew :core:test --tests 'zanshin.core.SourcesTest'`,
   commit `docs: <file> split into the layers`.

## What this skill never does

Write a reading from memory; copy a modern translation's wording; cite a file
where a passage id exists; mark a passage scan that was read by machine; edit
`build/sources/kb.sqlite` (a build artifact); edit the main checkout.
