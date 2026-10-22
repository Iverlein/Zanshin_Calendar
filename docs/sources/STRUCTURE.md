# The knowledge pool's structure

How `docs/sources/` is laid out so that it bears thousands of pages from
many sources, and the index that reads it. Decided by the owner on
2026-10-22: the pool will grow to several medicine and tantra sources, so
the structure changes now, before the topic files are too large to move.
Sigla, quoting and numbering are in [README.md](README.md); how to read is
[PLAN.md](PLAN.md); readings in parallel, [PROTOCOL.md](PROTOCOL.md).

## What breaks at scale, and the answer

The topic files mix two things: the corpus (passages in Tibetan with their
page and image) and the claims drawn from them. At ten thousand words a file
that works; at a hundred thousand neither a reader nor an agent can load the
file, a claim cites "marriage.md" rather than the passage, one
`open-questions.md` numbered past three hundred cannot be queried, and the
coverage of a source lives in README's *State* column as prose. The answer
is layers, each with a small unit and a stable id, Markdown in git as the
only truth, and an index built from the files that answers what a human or
an agent asks: what cites this passage, what is unread where the open
questions point, what rests on the etext only.

## The layers

| Layer | Unit | Where | Id |
| --- | --- | --- | --- |
| Sources | one entry per siglum: identifiers, volumes, page-to-image offsets, chapters, licence, rank, role | [sources.yaml](sources.yaml) | the siglum, lowercase: `wb`, `kp`, `st` |
| Corpus | one file per source chapter or section, in the text's own order; every passage in Tibetan with the English beside it, its reading state and its id | `corpus/<siglum>/<chapter>.md` | the passage id, below |
| Claims | one note per fact, rule, list, table, definition or remedy, short, citing passage ids, tagged with terms, naming what it feeds | `claims/<slug>.md` | the file's path, or `id:` in its frontmatter |
| Terms | one id per Tibetan term, with script, Wylie and English | the texts catalog (SPEC §8.2) and the claims' `terms:` | the Wylie, hyphenated: `bla-gnas`, `spar-kha` |
| Questions | one file per question to the texts, with its state, what it cites and what waits on it | `questions/q<nnnn>.md` | `q<n>`, numbered on from `open-questions.md` |
| Index | the graph of all of the above with the roadmap's readings and blocks, in SQLite with full text | `build/sources/kb.sqlite`, gitignored, rebuilt by every query | — |

The topic files as they stand are the first cut of the claims layer with
the corpus still inside them. They are read by the index as they are (*The
two shapes*) and move into the layers when next touched (*Migration*).

## Passage ids

`<siglum>[.<volume>]:<locator>`, the locator one of

| Form | Counts | Example |
| --- | --- | --- |
| `p<n>[-<m>]` | printed pages of the edition in `sources.yaml` | `wb.1:p351-379`, `wb.2:p331` |
| `i<n>[-<m>]` | scan images, for an edition without printed pages or when only the image is known | `wbz:i1058-1064`, `kp:i66` |
| `box<n>` | a numbered table | `kp:box12` |
| `f<n>[ab]` | a folio and its side | `pl:f12b` |

A lowercase letter after the pages (`wb.2:p331b`, `wb.2:p308-309b`) tells
passages on the same pages apart; `pool.py anchor` gives it to the second. The volume is given whenever the edition has volumes. Ranges
use a hyphen in ids; the en dash of prose (`pp. 351–379`) is accepted on
input. The anchor of a passage is its id with `.` and `:` made `-`:
`<a id="wb-2-p331"></a>`, so `[…](corpus/wb/ch33.md#wb-2-p331)` lands on
it on GitHub and in an editor.

## The shape of the files

Every file in a layer begins with YAML frontmatter; the index reads it, a
human skims it.

**Corpus** (`corpus/wb/ch23-marriage.md`):

```markdown
---
kind: corpus
source: wb
volume: 1
chapter: 23
pages: 351–379
read: machine          # the file's default reading state
---
# WB ch. 23, the marriage reckoning

## <a id="wb-1-p351"></a>wb.1:p351 The frame

*Read: scan, 2026-10-12.*

> ལེའུ་ཉེར་གསུམ་པ། བག་རྩིས་དང་ལམ་རྩིས་ལ་སོགས་པའི་སྐོར། …

The chapter's opening: …
```

A passage is a heading whose first word is its id, with the anchor before
it. The line `*Read: scan|machine|etext, <date>.*` under the heading is its
reading state; without it the file's `read:` holds. The states, from the
best witness down: **scan** (read by eye on the page image), **machine**
(an OCR reading, Yigdzin-1 or MITRA, not checked on the scan), **etext**
(BDRC's etext only). The Tibetan and the English follow as README's
*How the files quote* says. The file runs in the text's order, so the
coverage of a chapter is the union of its passages.

**Claim** (`claims/bride-priest.md`):

```markdown
---
kind: claim
type: rule               # definition | list | table | rule | remedy | conflict | note
terms: [sna-bon, gto]
cites: [wb.1:p365-377, kp:box12]
feeds: ["SPEC §10.11", block-31]
open: [q7]               # questions this claim waits on
---
# The priest of the bride's going

The *sna bon* leads the bride's party …
```

A claim is short, one fact, in our own words (SPEC §8), and cites passage
ids, never a file. Its `type: conflict` names two claims or passages that
disagree and which source stands (the rule of rank in
`.agents/rules/sources.md`). `feeds:` names the SPEC section, roadmap item
or block the claim serves, so that a change in the text can be followed to
the app.

**Question** (`questions/q0258.md`):

```markdown
---
kind: question
id: q258
state: open              # open | answered | dropped
asked: 2026-10-22
answered:
cites: [wb.1:p378a]
waits: [RD30]            # the reading or block that will settle it
---
# Is the first verse's remedy the nose rite?

The question, as a reader of the rtsis tradition would need it; the answer
below it when it comes, dated, with its passage.
```

Numbering continues from `open-questions.md`, which keeps questions 1–257
as they are until each is moved.

## The index

`tools/sources/kb.py`, run from the repository root (a worktree), rebuilds
`build/sources/kb.sqlite` from the files on every call and answers:

| Command | Answers |
| --- | --- |
| `check` | what the files get wrong: a siglum not in the registry, a link to a missing file, a reading without its topic file, a question named but never asked, a passage without a reading state (an error in a layered file, a warning in a legacy one), the registry and README's Editions table out of step; exit 1 on an error |
| `coverage [SIGLUM]` | each source's pages by the best state they were read in, chapter by chapter, with the gaps |
| `passage ID` | where a passage is quoted (file, heading, line, anchor) and who cites it |
| `cites TARGET` | what cites a passage, a file or a question |
| `questions [--open] [--source S] [--file F]` | the questions, filtered |
| `next` | the readings whose needs are met, ranked by what waits on them (blocks, readings, open questions on their pages); the chapters with open questions and no reading planned |
| `search WORDS` | full text over the passages' and files' text |
| `orphans` | files nothing links to; passages no claim, question or reading cites |
| `graph` | readings, blocks and topic files as Mermaid |
| `stats` | counts |

`--json` on any of them is for an agent; `--root DIR` points at another
checkout. The database is a build artifact: never committed, never edited.
`check` runs in the `readings` skill's bookkeeping beside `SourcesTest`.
For a human the same folder opens in Foam (VS Code) or Obsidian: the
relative links and the anchors give backlinks and the graph without any
change to the files.

## The method: two scripts, two skills

Rule 11 of the machine (`~/AGENTS.md`): every step with one right answer is
a script, every step that needs judgement a skill that drives it.

| Step | Script (mechanical) | Skill (judgement) |
| --- | --- | --- |
| Find the passage behind a fact | `kb.py search`, `passage`, `cites`, `coverage` | `sources`: which witness counts, whether the state may be built on, what is missing |
| Record a passage, a claim, a question, an answer | `pool.py passage`, `claim`, `question`, `answer`, `stub` | `sources`: the honest state, one fact per claim, what is a question |
| Lead the reading | `kb.py next`, `schedule.py plan`, `prompts`, `stubs` | `readings`: which chapter deserves a row, how many readers, what each prompt lacks |
| Bookkeep a reading | `pool.py question` for each reported question, `kb.py check`, `SourcesTest` | `readings`: whether the topic file holds what the row asked |
| Migrate a topic file | `pool.py anchor`, `split`, `index` | `sources`: confirm each inferred state and volume, cut the claims, repoint the citers |

`pool.py` validates against the registry and the index and writes nothing on
an error; `kb.py check` is the gate after every write. README's *The layers*
table is generated by `pool.py index` from the frontmatter and never edited
by hand.

## The two shapes

The index reads a legacy topic file by its headings: a heading with
`(p. 351, img. 361)` or `(pp. 365–377, img. 375–387)` is a passage of the
file's default source (WB when the file names WB), and its volume is taken,
in this order, from `vol. N` in the heading, the chapter the heading names
(`chapter 23`, `ch. 23`, looked up in the registry), the reading the heading
is marked with (`RD13`, whose row names the volume), or the enclosing
section. A citation in prose counts when it names a siglum or a volume
(`WB vol. 2, p. 331`, `KP img. 66`, `KP box 12`); a bare `p. 349` does not.
The reading state comes from the words of the section's first lines (*read
on the scan*, *by eye*; *Yigdzin-1*, *MITRA*, *OCR*; *etext*), else from the
enclosing section. These are inferences, and `check` warns where they fail;
a layered file states all of it in its frontmatter and is held to it.

## Migration

- **A new reading is written in the layers from now**: the roadmap's
  Readings row names `corpus/<siglum>/<chapter>.md` as its topic file;
  `schedule.py stubs` or `pool.py stub` makes the file with its frontmatter;
  the reader writes passages with `pool.py passage`, its facts with
  `pool.py claim`, and reports its questions for the launcher's
  `pool.py question`. The `readings` and `sources` skills and PROTOCOL.md say
  the same; `open-questions.md` is not extended any more.
- **An old topic file is split when next touched** for another reason, not
  on sight (the rule of `rules/tools.md`): its passages into the corpus
  file of their chapter, each with its id and state; what it says in its own
  words into claims; its *Open* and *Searched and not found* into questions
  and PLAN.md's dead ends. A reading or question that cited the file by
  name is repointed to the passage id. Until then the file is read as a
  legacy file. A first, smaller step is allowed: anchor its passages with
  ids (`pool.py anchor FILE --write`) and leave the rest; `pool.py split`
  moves the anchored sections into their corpus files when the time comes.
- **The registry grows with every source**: a siglum is used nowhere before
  it is in `sources.yaml` and README's Editions table; `check` fails
  otherwise.
- **What moves with the files**: README's Files table and *State* column
  become a generated view of the frontmatter once the topic files are
  split; `SourcesTest`'s "every topic file is in the index" then covers the
  layers. Both are ROADMAP C1.
