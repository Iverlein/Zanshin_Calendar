"""The launcher's mechanical steps (docs/sources/PROTOCOL.md, step 6).

  launcher.py resolve corpus|row|both FILE   a rebase conflict in FILE resolved keeping both sides
  launcher.py merge ID...                    each reader's branch rebased on main, the shared files
                                             resolved, kb.py check and SourcesTest run, main fast-forwarded
  launcher.py bookkeep SPEC.json             a reading's report filed: its questions as files, its row
                                             marked done, its topic files naming it, its dead ends in PLAN.md

`resolve corpus` merges the passages of both sides in page order (an `## Open`
or `## Searched` heading met twice has its items joined); `row` keeps our table
row and adds what theirs appended after the part both share; `both` keeps ours
then theirs. `merge` resolves README.md by main's copy and `pool.py index`,
corpus files by `corpus`, wb-contents.md by `row`, and stops on any other file.

The bookkeeping spec is JSON:

  {"rd": "RD36", "waits": "block-31", "date": "2026-10-23",
   "summary": "what the reading settled, for the row after «— done <date>:»",
   "topic": ["corpus/wb/ch29-grave.md"],   files that must name the reading
   "pages": "WB vol. 2, pp. 128–143 …",    the sentence naming it, if one is missing
   "files": ["corpus/wb/ch29-grave.md"],   files whose "- Question: …" lines become question files
   "titles": [...], "cites": [[...], ...],  one title and one list of passage ids per such line
   "questions": [{"title", "cites", "body"}, ...],   questions given in the report only
   "dead_heading": "…", "dead_ends": ["…", ...]}

Run from the repository root of a worktree (the launcher's `work/readings`).
"""
import argparse
import json
import os
import re
import subprocess
import sys
import tempfile
from pathlib import Path

CONF = re.compile(r"^<<<<<<< [^\n]*\n(.*?)^=======\n(.*?)^>>>>>>> [^\n]*\n", re.S | re.M)
NUMBERED = re.compile(r"^- Question \d+ \(\[q\d+\]\([^)]*\)\): ")
PASSAGE = re.compile(r"^## <a id=\"[^\"]+\"></a>\S+?:[pi](\d+)(?:-(\d+))?", re.M)
INTRO = "text's order, each under its id, with its `*Read:*` line.\n"
def main_checkout():
    """The main checkout: the parent of the repository's common git directory."""
    common = subprocess.run(["git", "rev-parse", "--path-format=absolute", "--git-common-dir"],
                            capture_output=True, text=True, check=True).stdout.strip()
    return Path(common).parent


def run(cmd, cwd=None, check=True):
    out = subprocess.run(cmd, cwd=cwd, capture_output=True, text=True)
    if check and out.returncode:
        sys.exit(f"{' '.join(cmd)}: {out.stdout}{out.stderr}")
    return out


# resolve

def _passages(block):
    return [p for p in re.split(r"(?=^## )", block, flags=re.M) if p.strip()]


def _key(p):
    m = PASSAGE.match(p)
    if not m:
        return (10**6, 0)                       # Open, Searched: at the end
    lo = int(m.group(1))
    return (lo, int(m.group(2) or lo))


def _corpus(m):
    out, seen = [], {}
    for p in sorted(_passages(m.group(1)) + _passages(m.group(2)), key=_key):
        head = p.splitlines()[0]
        if head in seen and not head.startswith("## <a"):
            i = seen[head]
            have = out[i].splitlines()
            numbered = {NUMBERED.sub("", l).lower() for l in have if NUMBERED.match(l)}
            add = [l for l in p.splitlines()[1:] if l not in have
                   and not (l.startswith("- Question: ") and l[12:].strip().lower() in numbered)]
            if add:
                out[i] = out[i].rstrip("\n") + "\n" + "\n".join(add).strip("\n") + "\n\n"
            continue
        seen[head] = len(out)
        out.append(p.rstrip("\n") + "\n\n")
    return "".join(out)


def _row(m):
    ours, theirs = m.group(1).rstrip("\n"), m.group(2).rstrip("\n")
    if "\n" in ours or "\n" in theirs:
        return m.group(1) + m.group(2)
    shared = os.path.commonprefix([ours, theirs])
    cut = shared.rfind("; ") + 2
    return ours.rstrip(" |") + "; " + theirs[cut:].rstrip(" |") + " |\n"


def resolve(mode, path):
    path = Path(path)
    fn = {"corpus": _corpus, "row": _row, "both": lambda m: m.group(1) + m.group(2)}[mode]
    text = CONF.sub(fn, path.read_text())
    if "<<<<<<<" in text:
        sys.exit(f"{path}: conflict markers left")
    path.write_text(text.rstrip("\n") + "\n")
    print(f"resolved {path}")


# merge

def merge(ids):
    MAIN = main_checkout()
    for rid in ids:
        w = MAIN.parent / "Zanshin_Calendar-wt" / rid.lower()
        if run(["git", "rebase", "main"], cwd=w, check=False).returncode:
            while True:
                for f in run(["git", "diff", "--name-only", "--diff-filter=U"], cwd=w).stdout.split():
                    if f == "docs/sources/README.md":
                        (w / f).write_text(run(["git", "show", f"main:{f}"], cwd=w).stdout)
                    elif f.startswith("docs/sources/corpus/"):
                        resolve("corpus", w / f)
                    elif f == "docs/sources/wb-contents.md":
                        resolve("row", w / f)
                    else:
                        sys.exit(f"{rid}: {f} is not a file this tool resolves; resolve it by hand")
                run(["python3", "tools/sources/pool.py", "index"], cwd=w)
                run(["git", "add", "-A", "docs"], cwd=w)
                env = dict(os.environ, GIT_EDITOR="true")
                cont = subprocess.run(["git", "rebase", "--continue"], cwd=w, env=env, capture_output=True, text=True)
                if cont.returncode == 0:
                    break
                if not run(["git", "diff", "--name-only", "--diff-filter=U"], cwd=w).stdout.strip():
                    sys.exit(f"{rid}: the rebase stopped: {cont.stdout}{cont.stderr}")
        print(run(["python3", "tools/sources/kb.py", "check"], cwd=w).stdout.strip().splitlines()[-1])
        t = run(["./gradlew", "-q", ":core:test", "--tests", "zanshin.core.SourcesTest"], cwd=w, check=False)
        if t.returncode:
            sys.exit(f"{rid}: SourcesTest fails\n{t.stdout[-2000:]}{t.stderr[-2000:]}")
        run(["git", "merge", "-q", "--ff-only", f"work/{rid.lower()}"], cwd=MAIN)
        print(f"{rid} merged: {run(['git', 'log', '--oneline', '-1'], cwd=MAIN).stdout.strip()}")


# bookkeep

def _question(title, cites, waits, body, date):
    with tempfile.NamedTemporaryFile("w", suffix=".md", delete=False) as f:
        f.write(body.strip()[0].upper() + body.strip()[1:] + "\n")
    out = run(["python3", "tools/sources/pool.py", "question", "--title", title, "--cites", ",".join(cites),
               "--waits", waits, "--body", f.name, "--date", date])
    os.unlink(f.name)
    n = int(re.search(r"q(\d+)", out.stdout).group(1))
    print(f"q{n}: {title}")
    return n


def bookkeep(spec_path):
    spec = json.loads(Path(spec_path).read_text())
    rid, date, waits = spec["rd"], spec.get("date", "2026-10-23"), spec["waits"]
    src = Path("docs/sources")
    for q in spec.get("questions", []):
        _question(q["title"], q["cites"], waits, q["body"], date)
    lines = [(f, l) for f in spec.get("files", []) for l in (src / f).read_text().splitlines()
             if l.startswith("- Question: ")]
    if len(lines) != len(spec.get("titles", [])):
        sys.exit(f"{len(lines)} questions in the files, {len(spec.get('titles', []))} titles")
    for (f, line), title, cites in zip(lines, spec.get("titles", []), spec.get("cites", [])):
        body = line[len("- Question: "):]
        n = _question(title, cites, waits, body, date)
        up = "../" * (len(Path(f).parts) - 1)
        p = src / f
        p.write_text(p.read_text().replace(line, f"- Question {n} ([q{n:04d}]({up}questions/q{n:04d}.md)): {body}", 1))
    road = Path("docs/ROADMAP.md")
    t = road.read_text()
    m = re.search(rf'(\| <a id="{rid.lower()}"></a>{rid} \| )([^|]*?)( \|)', t)
    if not m or "— done" in m.group(2):
        sys.exit(f"row {rid} not found or already done")
    road.write_text(t[:m.start(2)] + f"{m.group(2)} — done {date}: {spec['summary']}" + t[m.end(2):])
    print(f"{rid} marked done")
    for f in spec.get("topic", []):
        p = src / f
        t = p.read_text()
        if not re.search(rf"\b{rid}\b", t):
            if INTRO not in t:
                sys.exit(f"{f}: no intro to name {rid} after")
            p.write_text(t.replace(INTRO, INTRO + f"\nRead by {rid} ({date}): {spec['pages']}.\n", 1))
            print(f"{f}: names {rid}")
    if spec.get("dead_ends"):
        plan = src / "PLAN.md"
        t = plan.read_text().rstrip("\n")
        t += f"\n- **{spec['dead_heading']}** ({rid}, {date}):\n" + "".join(f"  - {d}\n" for d in spec["dead_ends"])
        plan.write_text(t)
        print(f"{len(spec['dead_ends'])} dead ends appended")
    run(["python3", "tools/sources/pool.py", "index"])


def main():
    ap = argparse.ArgumentParser(description=__doc__.split("\n")[0])
    sub = ap.add_subparsers(dest="cmd", required=True)
    r = sub.add_parser("resolve"); r.add_argument("mode", choices=["corpus", "row", "both"]); r.add_argument("file")
    m = sub.add_parser("merge"); m.add_argument("ids", nargs="+")
    b = sub.add_parser("bookkeep"); b.add_argument("spec")
    a = ap.parse_args()
    if a.cmd == "resolve":
        resolve(a.mode, a.file)
    elif a.cmd == "merge":
        merge(a.ids)
    else:
        bookkeep(a.spec)


if __name__ == "__main__":
    main()
