# Reading in parallel: the protocol

How the open readings of the roadmap's *Readings* table
([ROADMAP.md](../ROADMAP.md#rd1)) are run several at a time, as many as
the machine bears. One session, the **launcher**, picks the readings
that can start, gives each to a subagent of its own, and does the
bookkeeping that many hands cannot share; the subagents read. The rules
of reading are [PLAN.md](PLAN.md)'s and are not repeated here. The
arithmetic of this file is `tools/sources/schedule.py`; the launcher's
steps are also the `/readings` skill (`.claude/skills/readings/`).

## What bounds the number

Readings are independent of the build, so the only bounds are the
machine's, each with a margin, and a few things that exist once:

| Resource | Cost of one reader, measured 2026-10-11 | Margin kept free | Bound |
| --- | --- | --- | --- |
| The card (RTX 4060 Laptop, 8 GB; the desktop holds about 2 GB) | Yigdzin-1 2.0 GB, MITRA 2.3 GB | 1 GB | GPU slots = (free − margin) ÷ 2.3 GB, at most 4 |
| RAM (31 GB) | a reader 2.5–2.8 GB; a subagent's Gradle test run about 2 GB | 4 GB | slots again, and one more subagent per 2 GB left |
| The archive.org loan (BDRC's lent scans, the owner's login) | — | — | one subagent at a time |
| agy's quota | — | — | one subagent at a time, for single crops |
| The launcher's own context | each report a few thousand tokens | — | the subagent cap, `--agents N`, when the machine's number is more than the launcher can merge in a sitting |

A reader shares the card's compute with the others, so two readers do
not read twice as fast; the gain of parallel slots is that one
subagent's reading by eye and writing overlap another's model runs. A
slot is held only while a model runs, never for a whole session.

`schedule.py machine` prints the live numbers and what they give. On
2026-10-11, with 12.7 GB of RAM free, that was 2 slots and 3 subagents;
with the memory hogs closed it is 2 slots and more subagents.

## Which readings, in what order

`schedule.py plan` reads the table and sorts:

1. **Available**: not done, every need before the first ";" of its
   *Needs* cell done. Needs after the ";" ("RD3 for the sky dog of
   brides", "RD8 beside") are *best after*: the reading starts, and its
   prompt names them, so the subagent reads what they produced if it is
   there and marks what it could not use.
2. **Priority**: the reading that gates the most undone readings first
   (RD11 gates six), then the lowest number. O rows (*Left open in built
   work*) are fillers, launched last, when subagents remain.
3. **Resources**: a reading needs the card if its source cell names
   images, scans, crops or boxes, and the loan if it names lent scans;
   `--gpu` and `--no-gpu` overrule the guess, and the plan prints what
   it assumed. The slots are filled first with card readings, then the
   remaining subagents with etext and catalogue readings, then more card
   readings, which queue on the slot locks (they read the etext and the
   pages already machine-read while they wait). The loan goes to the
   highest-priority reading that needs it; the agy turn to the first
   launched.
4. **Held**: the rest, each with its reason (the cap, the loan).

The plan is recomputed each time a subagent reports, from the table as
it then stands: a reading that finished frees the ones it gated, and the
next report launches them. So the process is a pool, not fixed waves.

## The launcher's loop

1. **The checkout.** The main checkout is on `main`, clean; `git
   worktree list` shows the other sessions' worktrees, which are not
   touched. The launcher's own edits go into its worktree,
   `work/readings`, made as AGENTS.md says (*One worktree per session*).
2. **The plan.** In its worktree, rebased on `main`: `python3
   tools/sources/schedule.py plan`, and look at what it assumed for the
   card and the loan; rerun with `--gpu`/`--no-gpu`/`--agents` if the
   table's words misled it. The plan writes the slot count to
   `$XDG_RUNTIME_DIR/zanshin-gpu-slots`, which `schedule.py slot` reads.
3. **The stubs.** `schedule.py stubs` gives every new topic file of the
   plan, and every existing file two launched readings share, a section
   per reading, `## <reading> (RDn)`, far enough apart that two
   subagents' writing never meets in one hunk, and a new file its row in
   README.md's index; a new file under `corpus/` gets the frontmatter of
   [STRUCTURE.md](STRUCTURE.md), and the reading's row names it so
   (`corpus/<siglum>/<chapter>.md`). Correct a new file's title if the file name made a
   poor one. Commit, `docs: topic file stubs for <RDa, RDb, …>`, merge
   fast-forward into `main`.
4. **The prompts.** `schedule.py prompts DIR` writes one prompt per
   launched reading, from the row's *Prompt* cell, its pages, its topic
   file and section, the `main` it starts from and its resources. Read
   each once: a prompt that needs a detail the row lacks (an image
   group id, a page that moved) gets it added by hand.
5. **Launch** every planned reading as a background subagent with its
   prompt. Then wait; nothing is built from this session meanwhile, so
   the emulator and the build stay free for other sessions.
6. **On each report**, in the order they arrive:
   - **Merge**: from the main checkout, `git merge --ff-only work/rd<n>`.
     A refusal means `main` moved: rebase in that subagent's worktree
     keeping both sides, run `SourcesTest` there, merge again.
   - **Bookkeep**, in `work/readings` rebased on the new `main`: the
     reading's row marked "— done YYYY-MM-DD" after its name only if its
     topic file now holds its text (the subagent's checks of step 5 are
     read, not trusted); a reading not finished keeps its row with a
     note of what is left, or a new O… row; its questions numbered on
     from the last one into `open-questions.md`, each with its topic
     file linked, and the "Question:" lines in the topic file replaced
     by the numbers: since 2026-10-22 each question becomes a file,
     `python3 tools/sources/pool.py question --title … --cites … --waits …`,
     numbered on from the last question anywhere, and `open-questions.md`
     is not extended; its dead ends appended to PLAN.md's *Dead ends*
     with the date; every change for a build it reports (a list entry
     or a reading the scan reads otherwise) added to the roadmap's
     corrections item, [T2.28](../ROADMAP.md#t2-28), with its row and
     topic file, so that a block builds it, and one that waits on the
     owner named as a decision there;
     `docs/sources/wb/README.md`'s *Images done* extended
     (local, not committed). `./gradlew :core:test` in full; commit
     `docs: RD<n> done, <what it settled>`; merge fast-forward.
   - **Clean**: `git worktree remove --force` and `git branch -d` for
     that subagent; its scratch directory deleted, the shared scan cache
     and the transcriptions under `docs/sources/wb/` kept.
   - **Replan**: `schedule.py plan` again; what became available, within
     the subagents now free, is stubbed, prompted and launched as above.
7. **The end**, when the plan launches nothing and no subagent runs:
   `gpu.py` clean, no browser left running, the loan returned, and the
   report to the owner: the readings done with their commits, what
   stays open, what waits on what. Pushing waits for the owner's word;
   a push carries every session's commits.

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
`git -C $W log -1` shows the `main` the prompt names (the stub commit).

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
- The scans of the pages, into the shared cache so that the next reading
  finds them: `bdrc.py scans ~/ai/tibetan-ocr/scans/<image group>
  <image group> FIRST-LAST`; pages already there are not fetched again.
  KP and other works the same way, by their group. Lent scans (KD, BS,
  MK): only the subagent with the loan, by PLAN.md's *Tools*; the loan
  is returned and the cookie file deleted in the same step.
- A reading launched for the etext does all of its etext and catalogue
  work here; if a scan turns out to be needed after all, it takes a
  slot in step 3 like any other: the slots are locks, not assignments.

**Check:** every page of the list is on disk, and the first and last
image carry the printed page numbers expected.

### 3. The readers (the GPU)

Every model run goes through the slot lock, which waits for a free slot
and caps the run's memory at twice a reader's peak:

```bash
python3 tools/sources/schedule.py slot -- ~/ai/tibetan-ocr/venv/bin/python \
  tools/sources/hf_read.py yigdzin OUT IMG...
```

1. **Yigdzin-1 over all the pages** first: seconds a page, and the base
   text. OUT is `docs/sources/wb/yigdzin/` for WB vol. 2 (one file per
   image, as the folder has them) and `docs/sources/wb/vol1/yigdzin/`
   for vol. 1; other works under `$S`. Pages already read are skipped.
2. **MITRA as witness** over the pages that will be quoted, not over
   every page: 1.5–2 minutes a WB page, through the same `slot`. OUT is
   `docs/sources/wb/mitra/` or `wb/vol1/mitra/` likewise.
3. **Free the card** between the two and at the end: the reader exits by
   itself and the slot with it; `python3 tools/sources/gpu.py` must not
   name any process of this subagent's.

A slot that does not come free within twenty minutes is reported to the
launcher (a message), not worked around. **Check:** a `.txt` per image
in both folders for the quoted pages, one for Yigdzin-1 for the rest,
and `gpu.py` clean.

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
the file is its alone). **A topic file under `corpus/`** is a corpus file of
[STRUCTURE.md](STRUCTURE.md): there each passage is written with
`python3 tools/sources/pool.py passage <id> --title … --state … --tibetan F
--english F` (its id the printed pages, its state what was really done), the
facts in our own words as claims with `pool.py claim`, and the points
below apply to the claims' bodies; the `sources` skill
(`.claude/skills/sources/SKILL.md`) is the method. In a topic file of the
old shape:

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
(*State*: what was read on the scan, with the date; a stub's "nothing
read yet" replaced). Nothing else: not the roadmap, not PLAN.md, not
`open-questions.md`, not SPEC or code. **Check**, against the list of
pages from step 1: every page appears with its image number;
`grep -c '[ༀ-࿿]'` on the section is not zero; no sentence about the
app or its code in the section; no Tibetan script or Wylie without its
English; nothing written from memory, nothing under a non-commercial
licence, no personal data.

### 6. The tests and the commit

```bash
cd $W && ./gradlew :core:test --tests 'zanshin.core.SourcesTest'
cd $W && python3 tools/sources/kb.py check
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
   BDRC, a restricted volume, a *best after* reading not yet there,
   time);
7. the scratch directory, and whether the shared scan cache gained
   pages;
8. `gpu.py`'s last line.

A subagent that is blocked (a loan that will not open, a volume that
answers 401, no slot for twenty minutes) says so in its report rather
than working around it with a new tool or a reading from memory.

## The prompt for a subagent

`schedule.py prompts DIR` writes it; this is its shape, for a prompt
written by hand:

```text
Read RD<n> for Zanshin Calendar: <the row's Prompt column, verbatim>.

You are one of several readers working at once; follow
docs/sources/PROTOCOL.md, "The subagent's sequence", step by step, and
docs/sources/PLAN.md for how to read. Your worktree is
../Zanshin_Calendar-wt/rd<n>, branch work/rd<n>, from main at <hash>.
Pages: <the row's source cell>.
Your topic file: <file>; section "<heading>" in <file>.
[when the topic file is under corpus/:] Your topic file is a corpus file:
each passage with `pool.py passage`, the facts as claims (the sources skill).
Resources: <card | etext>, <loan>, <agy>, <best after RDm>. A model
runs only through `python3 tools/sources/schedule.py slot -- ...`, which
waits for a free GPU slot. The archive.org loan is <yours | not yours>;
agy is <your turn | not your turn: write doubts down as doubts>.
Do not merge, push, edit the main checkout, number questions, edit
ROADMAP.md, PLAN.md or open-questions.md, or sign anything. Stop after
the rebase and report in the shape of step 8.
```
