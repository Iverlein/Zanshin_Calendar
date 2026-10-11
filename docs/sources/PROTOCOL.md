# Reading in parallel: the protocol

How a bunch of readings from the roadmap's *Readings* table
([ROADMAP.md](../ROADMAP.md#rd1)) is run three at a time. One **unit** is
three readings, each read by its own subagent, all three at once; units
run one after another. The session that starts them is the **launcher**.
It does the bookkeeping that three hands cannot share; the subagents do
the reading. The rules of reading themselves are in [PLAN.md](PLAN.md)
and are not repeated here; this file says who does what, in what order,
and what is checked at each step.

## Why three, and why these three

The machine, not the texts, sets the size of a unit:

- **The GPU holds two readers.** The card has 8 GB, the desktop keeps
  about 1.7 GB of it, Yigdzin-1 takes about 2 GB with its activations and
  MITRA more. The readers neither queue nor lock the card: a third model
  fails to load or starves the other two. So a unit has **two GPU
  slots** and one reading that needs no scans, or that can read the
  etext first and take a slot when one frees.
- **One archive.org loan at a time**, under the owner's login
  (`archive_lend.py`): one subagent per unit may hold it.
- **agy's quota is one session's worth**: one subagent per unit has the
  agy turn, for single crops the readers and the eye leave unsettled.
- **Shared files.** Every reading touches `docs/ROADMAP.md`,
  `open-questions.md`, PLAN.md's *Dead ends* and [README.md](README.md)'s
  index; several readings write one topic file. Three subagents appending
  to the same place of one file make three conflicts, so those edits are
  the launcher's, and a shared topic file is given its sections before
  the unit starts (*Before a unit*, step 3).

**Choosing a unit.** Take the lowest-numbered readings not done whose
needs are done (the table's *Needs* column; "Do the next reading" in
ROADMAP.md), with these constraints: no reading in a unit needs another
of the same unit; at most two need the scans; at most one needs the
loan. A reading that gates many others (RD11 gates RD12–RD17) goes in
the first unit it fits.

**The units as the table stands on 2026-10-11** (RD1 and RD2 done):

| Unit | GPU slot A | GPU slot B | Third, no GPU at first | Shared topic file |
| --- | --- | --- | --- | --- |
| 1 | RD3 (WB vol. 2, pp. 175–198) | RD11 (the reckonings' outlines: from the etext, the scan for the counts) | RD7 (catalogues) | — |
| 2 | RD4 (WB vol. 2, pp. 199–221) | RD10 (WB on health) | RD8 (Dr. Chenagtsang's works) | health.md: RD10, RD8 |
| 3 | RD5 (KP's boxes, crops) | RD13 (WB ch. 22; the loan for KD vol. 3) | RD6 (WB's notes, KD, KP: etext search first) | — |
| 4 | RD12 (WB ch. 25) | RD14 (WB ch. 24) | RD15 (WB ch. 23; etext first, a slot when one frees) | — |
| 5 | RD9 (the classical texts) | RD16 (the dead) | RD17 (the other reckonings) | reckonings.md: RD16, RD17 |

The table is recomputed before each unit from the roadmap as it then
stands: a reading that turned out larger than planned, or a new reading
(O…, RD…) the last unit added, changes it.

## Before a unit: the launcher

1. **The checkout.** The main checkout is on `main`, clean; `git worktree
   list` shows which other sessions are at work, so their branches are
   not touched. The launcher's own edits go into a worktree of its own,
   `work/unit-<n>`, made as AGENTS.md says (*One worktree per session*).
2. **The unit.** Pick the three readings by the rule above. Check each
   one's *Needs* is marked done, and read each one's roadmap row and the
   item sections it is *For*, to give the subagent the right pages.
3. **The stubs.** For every topic file of the unit marked *(new)*, and
   for every existing topic file two readings of the unit share: create
   it or open it, and give each reading of the unit a section of its
   own, in the table's order, each a heading `## <subject> (RDn)` with
   one line saying what will stand there and a blank line after it, so
   that no two sections are within three lines of each other. A new file
   gets its row in README.md's index (*Roadmap* column the reading ids,
   *State* "stub"). Commit this in the launcher's worktree, `docs: topic
   file stubs for unit <n>`, merge it into `main` fast-forward, and start
   the subagents from that `main`. A subagent then writes inside its own
   section and nowhere else in that file.
4. **The machine.** `python3 tools/sources/gpu.py` lists no model.
   `~/ai/tibetan-ocr/venv` exists (RUNBOOK §7). The scans already on the
   machine are listed in `docs/sources/wb/README.md` (*Images done*) so
   that the subagents are told which pages need no fetch.
5. **The turns.** Assign slot A, slot B and "none" for the GPU, the loan
   to at most one, the agy turn to one. Write them into each prompt.
6. **Launch** the three subagents in the background, each with the
   prompt below filled in, and wait for all three reports. Nothing else
   is built meanwhile from this session, so the emulator and the build
   stay free for other sessions.

## The subagent's sequence

Each step ends with its check; the next step does not start before the
check passes. `$W` is the subagent's worktree; `$S` its scratch
directory, `~/ai/tibetan-ocr/scratch/rd<n>/`.

### 0. The worktree

```bash
cd ~/Documents/Git/Zanshin_Calendar
W=../Zanshin_Calendar-wt/rd<n>
git worktree add -b work/rd<n> $W main
cp local.properties $W/
mkdir -p ~/ai/tibetan-ocr/scratch/rd<n>
```

No vectors are needed: a reading runs `SourcesTest` only. **Check:**
`git -C $W log -1` shows the launcher's stub commit (or the `main` the
launcher named).

### 1. The rules and the row

Read, in this order: [README.md](README.md) (sigla, how the files
quote), [PLAN.md](PLAN.md) (*Before starting*, *The knowledge pool*,
*Tools*, *Workflow*, *Dead ends*), SPEC §8 (no text without a published
source, facts in our own words, no non-commercial licence), the
reading's row in the roadmap's *Readings* table and the item sections it
is *For*, the topic file as it stands, `wb-contents.md`'s rows for the
chapters it will open, and the questions in `open-questions.md` that
name its pages. Write down the page and image numbers to read (WB vol. 1:
image = page + 10; vol. 2: image = page + 8; check on the first scan).
**Check:** the list of pages, which are already machine-read
(`docs/sources/wb/README.md`, *Images done*), and the questions already
open on them.

### 2. The texts

- The etext of the volume, for searching: `bdrc.py etext $S/etext <IE
  id>` (the ids in README.md). The outline of a chapter, a count of
  pebbles, a search for a term: from the etext first, the scan after.
- The scans of the pages, into the shared cache so that the next unit
  finds them: `bdrc.py scans ~/ai/tibetan-ocr/scans/<image group>
  <image group> FIRST-LAST`; pages already there are not fetched again.
  KP and other works the same way, by their group. Lent scans (KD, BS,
  MK): only the subagent with the loan, by PLAN.md's *Tools*; the loan
  is returned and the cookie file deleted in the same step.
- The third subagent, with no GPU slot, does all of its etext and
  catalogue work here and asks the launcher for a slot (a message) only
  if a scan turns out to be needed.

**Check:** every page of the list is on disk, and the first and last
image carry the printed page numbers expected.

### 3. The readers (the GPU)

Only the subagents with a slot. Before each run:
`python3 tools/sources/gpu.py` lists at most one model process (the
other slot's); if it lists two, wait a minute and look again. Every run
is capped and stops its own model when killed:

```bash
systemd-run --user --scope -p MemoryMax=16G ~/ai/tibetan-ocr/venv/bin/python \
  tools/sources/hf_read.py yigdzin OUT IMG...
```

1. **Yigdzin-1 over all the pages** first: seconds a page, and the base
   text. OUT is `docs/sources/wb/yigdzin/` for WB vol. 2 (one file per
   image, as the folder has them) and `docs/sources/wb/vol1/yigdzin/`
   for vol. 1; other works under `$S`. Pages already read are skipped.
2. **MITRA as witness** over the pages that will be quoted, not over
   every page: 1.5–2 minutes a WB page. Two MITRA runs do not share the
   card: take the lock, `flock $XDG_RUNTIME_DIR/zanshin-mitra.lock
   systemd-run … hf_read.py mitra OUT IMG...`, and release it by ending.
   OUT is `docs/sources/wb/mitra/` or `wb/vol1/mitra/` likewise.
3. **Free the card** between the two and at the end: the reader exits by
   itself; `python3 tools/sources/gpu.py` must not name any process of
   this subagent's.

**Check:** a `.txt` per image in both folders for the quoted pages, one
for Yigdzin-1 for the rest, and `gpu.py` clean.

### 4. The scan

For each page to be quoted: `disagree.py yigdzin/N.txt mitra/N.txt
--scan IMG --sheet $S/dis-N.png`, then every flag read on the sheet and
the base text corrected, and the passage read once against the scan
itself (`scans.py stack` or `crop`, enlarged 3× or more; PLAN.md,
*Workflow*). Tables and lists are read digit by digit and entry by
entry. What the sheet and the eye leave unsettled, and only that, goes
to agy, one crop at a time (`agy_choose.py`, `agy_read.py`), by the
subagent that holds the agy turn; the others write the doubt down as a
doubt. **Check:** no flag of any quoted page left unread; each agy
answer judged against the scan, not taken.

### 5. The writing

Into the reading's own section of its topic file (the whole file, when
the file is its alone):

- each passage in Tibetan script as it reads on the scan, with edition,
  page and image number, and the English beside it, the whole passage
  the facts come from; where the scan was not read, the machine reading
  marked as such;
- the facts the app will need, in our own words, in the shape the item
  section asks for (a course, a list of works, a count);
- every Tibetan word named English first, then script and Wylie in
  brackets at its first mention (SPEC §8.1; "the la, the life-spirit
  (བླ, bla)"); bla is never "soul", sri never "vampire";
- a modern author's text (Dr. Chenagtsang, KD's essays) quoted no
  further than the facts need; the public-domain texts in full;
- under a heading *Open* at the end of the section: questions for a
  reader of the tradition, **unnumbered**, each starting "Question:"; and
  under *Searched and not found*: each search without a result, with
  where it was made. The launcher numbers the questions into
  `open-questions.md` and moves the dead ends into PLAN.md, so those two
  files are not edited by the subagent.

Besides the topic file: the chapter's row in `wb-contents.md` (what has
now been read of it), and the topic file's row in README.md's index
(*State*: what was read on the scan, with the date). Nothing else:
not the roadmap, not PLAN.md, not `open-questions.md`, not SPEC or
code. **Check**, against the list of pages from step 1: every page
appears with its image number; `grep -c '[ༀ-࿿]'` on the section is not
zero; no sentence about the app or its code in the section; no
Tibetan script or Wylie without its English; nothing written from
memory, nothing under a non-commercial licence, no personal data.

### 6. The tests and the commit

```bash
cd $W && ./gradlew :core:test --tests 'zanshin.core.SourcesTest'
python3 tools/sources/gpu.py
```

Both clean. Then one commit on `work/rd<n>` in the repository's style:
subject `docs: RD<n> read, <subject>`; body: the pages and images read,
with which readers and what was checked by eye, what was found, and
what stays open. No assistant signature of any kind (AGENTS.md).

### 7. The rebase, and stop

```bash
cd $W && git rebase main
```

A conflict is resolved keeping both sides (another subagent's section
or row is never dropped); after a rebase that touched a shared file,
`SourcesTest` runs again. Then the subagent **stops**: no merge into
`main`, no push, no edit in the main checkout, no removal of the
worktree. The launcher merges.

### 8. The report

The final message, for the launcher, in this order:

1. the branch, the commit hash, and whether `main` moved under it;
2. the pages and images read, and with what (Yigdzin-1 only, with
   MITRA, by eye, agy for N crops);
3. what was found, in a few lines, with the topic file's section named;
4. the questions, verbatim, for numbering;
5. the dead ends, verbatim;
6. what the row asked for and was not done, and why (pages missing on
   BDRC, a restricted volume, time);
7. the scratch directory, and whether the shared scan cache gained
   pages;
8. `gpu.py`'s last line.

A subagent that is blocked (a loan that will not open, a volume that
answers 401, two models on the card for more than ten minutes) says so
in its report rather than working around it with a new tool or a
reading from memory.

## After a unit: the launcher

1. **Merge in report order.** From the main checkout, `git merge
   --ff-only work/rd<n>` for each; a refusal means `main` moved: rebase
   in that subagent's worktree (keeping both sides), run `SourcesTest`
   there, merge again.
2. **The bookkeeping commit**, in `work/unit-<n>` rebased on the new
   `main`: each reading's row in the roadmap marked "— done YYYY-MM-DD"
   after its name only if its topic file now holds its text (step 5's
   checks, read, not trusted); a reading not finished keeps its row and
   gets a note of what is left, or a new O… row; the questions numbered
   on from the last one in `open-questions.md`, each with its topic file
   linked, and the "Question:" lines in the topic files replaced by the
   numbers; the dead ends appended to PLAN.md's *Dead ends* with their
   dates; `docs/sources/wb/README.md`'s *Images done* extended (local,
   not committed). Then `./gradlew :core:test` in full, and the commit
   `docs: unit <n> read (RD<a>, RD<b>, RD<c>)`, merged fast-forward.
3. **Clean up.** `git worktree remove --force` and `git branch -d` for
   the three subagent branches and the unit's own; the subagents'
   scratch directories deleted, the shared scan cache and the machine
   transcriptions under `docs/sources/wb/` kept; `gpu.py` clean; no
   browser left running.
4. **Report to the owner**: the three readings with their commits, what
   stays open, what the next unit will be. Pushing waits for the
   owner's word; a push carries every session's commits.
5. **The next unit** from step 1 of *Before a unit*, with the table
   recomputed.

## The prompt for a subagent

Filled in by the launcher; `<…>` are the places to fill.

```
Read RD<n> for Zanshin Calendar: <the row's Prompt column, verbatim>.

You are one of three readers working at once; follow
docs/sources/PROTOCOL.md, "The subagent's sequence", step by step, and
docs/sources/PLAN.md for how to read. Your worktree is
../Zanshin_Calendar-wt/rd<n>, branch work/rd<n>, from main at <hash>,
which holds your topic file's stub (section "<heading>" in <file>).
Pages: <source>, pp. <a>–<b>, img. <c>–<d>; already machine-read:
<images or "none">.
GPU: <slot A | slot B | none: etext and catalogue work; message the
launcher for a slot if a scan is needed>. The archive.org loan:
<yours | not yours>. agy: <your turn | not yours: write doubts down>.
Do not merge, push, edit the main checkout, number questions, edit
ROADMAP.md, PLAN.md or open-questions.md, or sign anything. Stop after
the rebase and report in the shape of step 8.
```
