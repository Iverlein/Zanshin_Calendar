---
name: readings
description: Run the open readings of docs/ROADMAP.md's Readings table in parallel, as many as the machine bears: plan with tools/sources/schedule.py, stub the shared topic files, launch one subagent per reading, merge and bookkeep each report, replan until nothing is left. Use when asked to run, launch or continue the readings, or "do the readings".
---

# Readings in parallel

You are the launcher of docs/sources/PROTOCOL.md; its *The launcher's
loop* is the full text, this is the order of calls. Read PROTOCOL.md
once before the first plan.

1. **Worktree.** Main checkout clean on `main`; `git worktree list`.
   Make `work/readings` as AGENTS.md says and work there.
2. **Plan.** `python3 tools/sources/kb.py next` first: the readings whose
   needs are met, ranked by what waits on them, and the chapters with
   open questions and no reading planned; a chapter worth a reading gets
   its RD row in the roadmap (topic file `corpus/<siglum>/<chapter>.md`)
   before the plan. Then `python3 tools/sources/schedule.py plan`. **Three
   readings at once is the floor** (the owner, 2026-10-22): when it
   launches fewer, make rows for what `kb.py next` names until three are
   ready, unless `schedule.py machine` says the machine bears fewer; never one reading alone while another could run. Check what it
   assumed (card, loan) against the rows; rerun with `--gpu`,
   `--no-gpu`, `--agents` if needed. It writes the slot count for
   `schedule.py slot`.
3. **Stubs.** `python3 tools/sources/schedule.py stubs`; fix a poor
   title; `./gradlew :core:test --tests 'zanshin.core.SourcesTest'`
   and `python3 tools/sources/kb.py check`;
   commit `docs: topic file stubs for <ids>`; fast-forward into `main`.
4. **Prompts.** `python3 tools/sources/schedule.py prompts DIR` (DIR in
   the scratch directory); read each; add what the row lacks.
5. **Launch** each prompt as a background general-purpose subagent, one
   per reading, all at once. Do nothing else that builds or uses the
   emulator while they run.
6. **Each report**: merge `--ff-only work/rd<n>` from the main checkout
   (rebase in its worktree if refused); in `work/readings`: mark the
   row done only after reading the topic file's section, make each of
   its questions a file with `python3 tools/sources/pool.py question`
   (the sources skill; never into open-questions.md any more), append
   its dead ends to PLAN.md,
   put each change for a build it reports into ROADMAP's T2.28 (an
   owner's decision named there),
   extend docs/sources/wb/README.md's *Images done*; `./gradlew
   :core:test` and `python3 tools/sources/kb.py check`; commit `docs: RD<n> done, <what it settled>`;
   fast-forward; remove the subagent's worktree and branch and its
   scratch; then `schedule.py plan` again and launch what it frees
   (steps 3–5 for those).
7. **End** when the plan launches nothing and no subagent runs:
   `python3 tools/sources/gpu.py` clean, loan returned, no browser
   left; report the readings done with their commits and what stays
   open. Do not push.

Never: sign a commit, push, read from memory, edit the main checkout,
or mark a reading done on a subagent's word alone.
