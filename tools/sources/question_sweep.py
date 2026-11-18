#!/usr/bin/env python3
"""Find open questions that a later text in the pool may already settle, and mark one narrowed.

The mechanical half of the `question-sweep` skill (.claude/skills/question-sweep/SKILL.md);
the skill's readers judge each candidate. No model is called here.

  question_sweep.py find OUTDIR [--batches N]
      Every question file in docs/sources/questions/ whose state is open or narrowed, with
      each place in docs/**/*.md (question files and README.md left out) that names it
      (q123 or q0123) near a word like "answered", "settled", "confirmed", "reads",
      "dead end". Writes OUTDIR/batch<i>.json (question number -> file, state, asked,
      mentions[{at, snip}]) and OUTDIR/prompt<i>.txt, one reader's instructions per batch,
      and prints the counts.

  question_sweep.py narrow QID --date YYYY-MM-DD --source NAME --body FILE
      Sets `state: narrowed` in the question's frontmatter (an open question only) and
      appends "**Narrowed DATE (sweep from NAME).** <body>". Answering is `pool.py answer`.

Run from the repository root (or a worktree's root).
"""
import argparse
import glob
import json
import os
import re
import sys

QDIR = 'docs/sources/questions'
HINT = re.compile(r'answer|settl|confirm|dead end|resolv|reads? |is «|not «|closed', re.I)
QREF = re.compile(r'\bq0*(\d{2,4})\b')

PROMPT = """Question sweep, batch {i} of {n}, for Zanshin Calendar's knowledge pool.

Read first: docs/sources/STRUCTURE.md and .claude/skills/sources/SKILL.md.

The job: some questions in docs/sources/questions/ are still marked open or narrowed,
but a later reading already settled them. Find those and update their files. Use only
the text already in the repository. Do not look at scans, do not search the web, do not
run a model, do not answer from memory. If you are not sure, leave the question as it is.

Setup: from ~/Documents/Git/Zanshin_Calendar run
  git worktree add -b work/qs{i} ../Zanshin_Calendar-wt/qs{i} main
and work only inside ../Zanshin_Calendar-wt/qs{i}.

Your list: {batch}
It maps a question number to its file, state, asked date and "mentions": the places
(file:line) where a later text names the question, with a snippet.

For each question in the list:
1. Read the question file. Read each mention in its file at that line, with the lines
   around it. If needed: python3 tools/sources/kb.py passage <id> and
   python3 tools/sources/kb.py cites q<n>.
2. Decide one of three:
   ANSWERED - a later text plainly gives what the question asks (the syllable read on
     a print, the figure confirmed, the source found, or "a dead end" stated as final).
     Write 2-5 sentences to a temporary file: the answer quoted as the pool has it
     (Tibetan in «» exactly as written) and where it is (file, section or passage id,
     the reading's name and date). Then run:
       python3 tools/sources/pool.py answer q<n> --date <date of that text> --body <file> [--cites <passage ids>]
   NARROWED - the question is open, and a later text narrows it without settling it.
     Write 1-3 sentences to a temporary file, then run:
       python3 tools/sources/question_sweep.py narrow q<n> --date <date of that text> --source <reading name> --body <file>
   LEAVE - anything else. A mention that only repeats or carries the question is LEAVE.
   If the question repeats another question that is answered, answer it by pointing to
   that one. If the other is not answered, LEAVE.
3. After about 20 questions: python3 tools/sources/kb.py check, then commit:
     git commit -am "docs: questions answered or narrowed from the pool, sweep batch {i}"
   Never add a signature or a Co-Authored-By line.

At the end:
- ./gradlew :core:test --tests 'zanshin.core.SourcesTest' -q and fix what it reports.
- git rebase main. If docs/sources/README.md conflicts, run in order:
    git checkout --ours docs/sources/README.md
    python3 tools/sources/pool.py index
    git add docs/sources/README.md
    git rebase --continue
  (During a rebase "--ours" is main's side.)
- Stop. Do not merge, do not push. Edit nothing but question files and the README index.

Report: the branch and its last commit; answered (q number + one line each); narrowed
(q number + one line each); how many were left; anything odd.
"""


def questions(root):
    qs = {}
    for f in glob.glob(os.path.join(root, QDIR, 'q*.md')):
        t = open(f, encoding='utf-8').read()
        st = re.search(r'^state: *(\w+)', t, re.M)
        if not st or st.group(1) not in ('open', 'narrowed'):
            continue
        n = int(re.search(r'q0*(\d+)', os.path.basename(f)).group(1))
        asked = re.search(r"^asked: *'?([\d-]+)", t, re.M)
        qs[n] = {'file': os.path.relpath(f, root), 'state': st.group(1),
                 'asked': asked.group(1) if asked else None, 'mentions': []}
    return qs


def find(args):
    root = '.'
    qs = questions(root)
    for p in glob.glob(os.path.join(root, 'docs/**/*.md'), recursive=True):
        if '/questions/' in p or p.endswith('README.md'):
            continue
        for i, line in enumerate(open(p, encoding='utf-8', errors='ignore')):
            for m in QREF.finditer(line):
                n = int(m.group(1))
                if n not in qs:
                    continue
                snip = line[max(0, m.start() - 300):m.end() + 300].strip()
                if HINT.search(snip):
                    qs[n]['mentions'].append({'at': f'{os.path.relpath(p, root)}:{i + 1}', 'snip': snip})
    cand = {n: v for n, v in sorted(qs.items()) if v['mentions']}
    os.makedirs(args.outdir, exist_ok=True)
    keys = list(cand)
    n = max(1, args.batches)
    for i in range(n):
        part = {k: cand[k] for k in keys[i::n]}
        bpath = os.path.abspath(os.path.join(args.outdir, f'batch{i + 1}.json'))
        json.dump(part, open(bpath, 'w', encoding='utf-8'), ensure_ascii=False, indent=1)
        with open(os.path.join(args.outdir, f'prompt{i + 1}.txt'), 'w', encoding='utf-8') as fh:
            fh.write(PROMPT.format(i=i + 1, n=n, batch=bpath))
        print(f'batch {i + 1}: {len(part)} questions -> {bpath}')
    print(f'{len(qs)} open or narrowed; {len(cand)} named by a later text')


def narrow(args):
    m = re.fullmatch(r'q?0*(\d+)', args.qid)
    if not m:
        sys.exit(f'not a question id: {args.qid}')
    hits = [f for f in glob.glob(os.path.join(QDIR, 'q*.md'))
            if int(re.search(r'q0*(\d+)', os.path.basename(f)).group(1)) == int(m.group(1))]
    if len(hits) != 1:
        sys.exit(f'question file not found: {args.qid}')
    path = hits[0]
    text = open(path, encoding='utf-8').read()
    if not re.search(r'^state: *open\b', text, re.M):
        sys.exit(f'{path}: not open, left as it is')
    if not re.fullmatch(r'\d{4}-\d{2}-\d{2}', args.date):
        sys.exit('--date must be YYYY-MM-DD')
    body = open(args.body, encoding='utf-8').read().strip()
    if not body:
        sys.exit('--body is empty')
    text = re.sub(r'^state: *open\b', 'state: narrowed', text, count=1, flags=re.M)
    text = text.rstrip('\n') + f'\n\n**Narrowed {args.date} (sweep from {args.source}).** {body}\n'
    open(path, 'w', encoding='utf-8').write(text)
    print(f'{path}: narrowed')


def main():
    ap = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    sub = ap.add_subparsers(dest='cmd', required=True)
    f = sub.add_parser('find')
    f.add_argument('outdir')
    f.add_argument('--batches', type=int, default=5)
    f.set_defaults(fn=find)
    nw = sub.add_parser('narrow')
    nw.add_argument('qid')
    nw.add_argument('--date', required=True)
    nw.add_argument('--source', required=True)
    nw.add_argument('--body', required=True)
    nw.set_defaults(fn=narrow)
    args = ap.parse_args()
    args.fn(args)


if __name__ == '__main__':
    main()
