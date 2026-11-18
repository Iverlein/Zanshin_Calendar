---
name: question-sweep
description: Mark the knowledge pool's open questions that a later reading already settled. tools/sources/question_sweep.py lists the candidates in batches; one subagent per batch answers or narrows each question from the pool's own text; you merge. Use when asked to sweep, close or tidy the open questions, or "/question-sweep".
---

# Question sweep

Why: a reader who settles an old question usually corrects the passage and moves on, but
leaves the question file open. This sweep updates those files. It uses only text already in
the repository: no scans, no web, no model, no memory. When unsure, a question stays open.

Follow the steps in order. Do not skip one.

1. **Check the start.** In `~/Documents/Git/Zanshin_Calendar`: `git status` is clean and on
   `main`; `git worktree list`. Make your own worktree as AGENTS.md says:
   `git worktree add -b work/qsweep ../Zanshin_Calendar-wt/qsweep main`, and work there.

2. **Find the candidates.** In your worktree:
   `python3 tools/sources/question_sweep.py find <SCRATCH>/sweep --batches 5`
   (`<SCRATCH>` is your scratchpad directory). It writes `batch1.json` … `batch5.json` and
   `prompt1.txt` … `prompt5.txt` and prints the counts. Keep the counts for the report.

3. **Launch.** One background general-purpose subagent per batch, all in one message. Each
   gets this prompt: "Your full instructions are in `<SCRATCH>/sweep/prompt<i>.txt`; read it
   and do exactly that. Never sign commits; do not push or merge."
   Five at once is enough; use fewer batches if the owner asks for a small run.

4. **On each report**, one at a time:
   - Check two or three of its answers yourself: open the question file and the place it
     names, and see that the text there says what the answer says. If one is wrong, open
     that subagent's worktree, revert that question file (`git checkout main -- <file>`),
     commit, and say so in your report.
   - Merge: `python3 tools/sources/launcher.py merge qs<i>` from your worktree (it rebases
     `work/qs<i>`, runs the checks, and fast-forwards `main`).
   - Clean: from the main checkout, `git worktree remove --force ../Zanshin_Calendar-wt/qs<i>`
     and `git branch -d work/qs<i>`.

5. **End.** When all batches are merged: `./gradlew :core:test` in your worktree;
   `python3 tools/sources/kb.py check`. Remove your own worktree and branch the same way.
   `git worktree list` shows the main checkout alone; delete `<SCRATCH>/sweep`. Do not push.

6. **Report to the owner**: how many questions were candidates, how many answered, narrowed
   and left; any answer you reverted; the commits on `main`.

If you stop early: stop the subagents, remove every `qs<i>` worktree and branch that has no
commit (`git log --oneline main..work/qs<i>` is empty), and merge the ones that have commits
only after step 4's check.
