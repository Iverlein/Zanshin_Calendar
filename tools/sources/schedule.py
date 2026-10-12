# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at https://mozilla.org/MPL/2.0/.
"""Which readings of docs/ROADMAP.md can run now, and how many at once the machine bears.

  schedule.py machine                 the card, the memory, the readers' costs, the slots
  schedule.py plan [--agents N] [--gpu RDn...] [--no-gpu RDn...] [--json]
                                      the readings to launch now, in order, with their roles
  schedule.py prompts DIR [plan options]
                                      one prompt file per launched reading, for the subagents
  schedule.py stubs [plan options]    the planned readings' sections in their topic files
                                      (new files made, README's index extended)
  schedule.py slot [-n N] -- CMD...   run CMD in a free GPU slot (waits for one), memory-capped

The rules are docs/sources/PROTOCOL.md; this is their arithmetic, run from the
repository root (a worktree). The costs below were measured on 2026-10-11
(RTX 4060 Laptop, 8 GB): Yigdzin-1 2.0 GB of the card and 2.5 GB of RAM for
a 1400 px page, MITRA 2.3 GB and 2.8 GB; a subagent's test run (Gradle) about
2 GB of RAM for a minute. The margins keep 1 GB of the card and 4 GB of RAM
free whatever the plan.

The Readings table: a row's needs before the first ";" must be done, those
after it are "best after" (RD15: RD11; RD3 for one part). A reading needs
the card if its source cell names images, scans, crops or boxes, and the
archive.org loan if it names lent scans; --gpu and --no-gpu overrule the
guess. O rows (left open in built work) are fillers: launched last, when
agents remain.
"""
import argparse
import fcntl
import json
import os
import re
import subprocess
import sys
from pathlib import Path

ROADMAP = Path("docs/ROADMAP.md")
SOURCES = Path("docs/sources")
RUNTIME = Path(os.environ.get("XDG_RUNTIME_DIR", "/tmp"))
SLOTS_FILE = RUNTIME / "zanshin-gpu-slots"

# Measured 2026-10-11 (MiB); the larger reader sizes a slot, so two readers of either kind fit it.
READER_VRAM = 2300
READER_RSS = 2800
READER_CAP = "6G"        # MemoryMax for one reader: twice its peak
AGENT_RSS = 2048         # a subagent's Gradle test run
VRAM_MARGIN = 1024
RAM_MARGIN = 4096
MAX_SLOTS = 4            # more readers than this only share the card's compute
GPU_WORDS = ("img.", "scan", "crop", "box")
LOAN_WORDS = ("lent", "archive.org")


# --- the roadmap ---------------------------------------------------------------------------

class Reading:
    def __init__(self, cells):
        self.id = re.search(r"(RD|O)\d+", cells[0]).group()
        self.name = re.sub(r"\s*— done .*", "", cells[1]).strip()
        self.done = bool(re.search(r"— done \d{4}-\d{2}-\d{2}", cells[1]))
        self.unplanned = bool(re.search(r"\bnot planned\b", cells[1], re.I))
        self.filler = self.id.startswith("O")
        if self.filler:
            self.source, needs, self.topic, self.prompt = cells[1], "", cells[2], ""
            self.name = cells[1][:70]
        else:
            self.source, needs, self.topic, self.prompt = cells[2], cells[3], cells[5], cells[6].strip(" |")
        hard, _, soft = needs.partition(";")
        self.needs = re.findall(r"RD\d+", hard)
        self.after = re.findall(r"RD\d+", soft)
        files = {}                      # a link names its file twice, as text and as target
        for m in re.finditer(r"([\w-]+\.md)( \(new\))?", self.topic):
            files[m.group(1)] = files.get(m.group(1), False) or bool(m.group(2))
        self.files = list(files.items())
        self.gpu = any(w in self.source for w in GPU_WORDS)
        self.loan = any(w in self.source for w in LOAN_WORDS)
        self.gates = 0

    def __repr__(self):
        return self.id


def readings(roadmap=ROADMAP):
    rows = [l for l in roadmap.read_text().splitlines() if re.match(r'\| <a id="(rd|o)\d+"', l)]
    out = [Reading(l.split(" | ")) for l in rows]
    by_id = {r.id: r for r in out}
    for r in out:                       # how many undone readings wait on this one, transitively
        seen, todo = set(), [r.id]
        while todo:
            x = todo.pop()
            for y in out:
                if x in y.needs and y.id not in seen and not y.done:
                    seen.add(y.id)
                    todo.append(y.id)
        r.gates = len(seen)
    return out, by_id


# --- the machine -----------------------------------------------------------------------------

def nvidia(query, what):
    try:
        out = subprocess.run(["nvidia-smi", f"--query-{what}={query}", "--format=csv,noheader,nounits"],
                             capture_output=True, text=True, check=True).stdout
    except (OSError, subprocess.CalledProcessError):
        return []
    return [[c.strip() for c in l.split(",")] for l in out.splitlines() if l.strip()]


def machine():
    gpu = nvidia("memory.total,memory.used", "gpu")
    total, used = (int(gpu[0][0]), int(gpu[0][1])) if gpu else (0, 0)
    apps = nvidia("pid,process_name,used_memory", "compute-apps")
    readers = [(int(p), n, int(m)) for p, n, m in apps if "python" in n]
    reader_vram = sum(m for _, _, m in readers)
    mem = {}
    for l in Path("/proc/meminfo").read_text().splitlines():
        k, v = l.split(":")
        mem[k] = int(v.split()[0]) // 1024
    avail = mem["MemAvailable"]
    # what the card can take: its free memory plus what readers already hold, less the margin
    by_vram = max(0, (total - used + reader_vram - VRAM_MARGIN) // READER_VRAM) if total else 0
    by_ram = max(0, (avail + len(readers) * READER_RSS - RAM_MARGIN) // READER_RSS)
    slots = min(by_vram, by_ram, MAX_SLOTS)
    agents = slots + max(0, (avail + len(readers) * READER_RSS - RAM_MARGIN - slots * READER_RSS) // AGENT_RSS)
    return dict(vram_total=total, vram_used=used, readers=readers, ram_total=mem["MemTotal"], ram_avail=avail,
                cpus=os.cpu_count(), slots=int(slots), slots_by_vram=int(by_vram), slots_by_ram=int(by_ram),
                agents=int(max(agents, 1)))


def print_machine(m):
    print(f"card: {m['vram_total']} MiB, {m['vram_used']} MiB used"
          + (f", readers on it: {', '.join(f'{p} {n} {v} MiB' for p, n, v in m['readers'])}" if m["readers"] else ""))
    print(f"RAM: {m['ram_total']} MiB, {m['ram_avail']} MiB available; {m['cpus']} CPUs")
    print(f"a reader: {READER_VRAM} MiB of the card, {READER_RSS} MiB of RAM, capped at {READER_CAP};"
          f" margins {VRAM_MARGIN} MiB and {RAM_MARGIN} MiB")
    print(f"GPU slots: {m['slots']} (by the card {m['slots_by_vram']}, by RAM {m['slots_by_ram']}, at most {MAX_SLOTS})")
    print(f"subagents at once: {m['agents']} (the slots, and {AGENT_RSS} MiB of RAM for each one more)")


# --- the plan --------------------------------------------------------------------------------

def plan(args):
    all_, by_id = readings()
    m = machine()
    agents = args.agents or m["agents"]
    for r in all_:
        if r.id in args.gpu:
            r.gpu = True
        if r.id in args.no_gpu:
            r.gpu = False
    done = {r.id for r in all_ if r.done}
    waiting, ready = [], []
    for r in all_:
        if r.done or r.unplanned:
            continue
        missing = [n for n in r.needs if n not in done]
        (waiting if missing else ready).append((r, missing))
    # priority: what gates most first, then the lowest number; fillers last
    ready.sort(key=lambda x: (x[0].filler, -x[0].gates, int(re.search(r"\d+", x[0].id).group())))
    launch, held, loan_taken, agy_taken, slots_left = [], [], False, False, m["slots"]
    # first the card's slots, then whatever else fits, then more card readings (they queue on the locks)
    order = [x for x in ready if x[0].gpu and not x[0].filler] + [x for x in ready if not x[0].gpu and not x[0].filler] \
        + [x for x in ready if x[0].filler]
    seen = set()
    for r, _ in order:
        if r.id in seen:
            continue
        seen.add(r.id)
        if len(launch) >= agents:
            held.append((r, "no subagent left: the cap is %d" % agents))
            continue
        if r.loan and loan_taken:
            held.append((r, "the loan is taken"))
            continue
        role = []
        if r.gpu:
            role.append("card" if slots_left > 0 else "card, queued")
            slots_left -= 1
        else:
            role.append("etext")
        if r.loan:
            role.append("loan")
            loan_taken = True
        if not agy_taken and not r.filler:
            role.append("agy")
            agy_taken = True
        later = [n for n in r.after if n not in done]
        if later:
            role.append("best after " + ", ".join(later))
        launch.append((r, role))
    return m, launch, held, waiting, done


def prompt_for(r, head, files_sections):
    sections = "; ".join(f'section "{h}" in {f}' for f, h in files_sections.get(r.id, []))
    role = ", ".join(r.role)
    pages = r.source if not r.filler else r.topic
    text = f"""Read {r.id} for Zanshin Calendar: {(r.prompt or r.name).rstrip(".")}.

You are one of several readers working at once; follow docs/sources/PROTOCOL.md,
"The subagent's sequence", step by step, and docs/sources/PLAN.md for how to read.
Your worktree is ../Zanshin_Calendar-wt/{r.id.lower()}, branch work/{r.id.lower()}, from main at {head}.
Pages: {pages}.
Your topic file: {r.topic}{'; ' + sections if sections else ''}.
Resources: {role}. A model runs only through `python3 tools/sources/schedule.py slot -- ...`,
which waits for a free GPU slot. The archive.org loan is {'yours' if r.loan else 'not yours'};
agy is {'your turn, for single crops the readers and the eye leave unsettled' if 'agy' in r.role else 'not your turn: write doubts down as doubts'}.
Do not merge, push, edit the main checkout, number questions, edit ROADMAP.md, PLAN.md
or open-questions.md, or sign anything. Stop after the rebase and report in the shape of step 8.
"""
    return text


def head_hash():
    return subprocess.run(["git", "rev-parse", "--short", "HEAD"], capture_output=True, text=True).stdout.strip()


def cmd_plan(args):
    m, launch, held, waiting, done = plan(args)
    for r, role in launch:
        r.role = role
    if args.json:
        print(json.dumps(dict(machine=m, launch=[dict(id=r.id, name=r.name, role=role, gpu=r.gpu, loan=r.loan,
                                                       files=[f for f, _ in r.files], gates=r.gates) for r, role in launch],
                              held=[dict(id=r.id, why=w) for r, w in held],
                              waiting=[dict(id=r.id, needs=n) for r, n in waiting]), indent=1, ensure_ascii=False))
        return
    print_machine(m)
    print(f"\ndone: {', '.join(sorted(done, key=lambda s: (not s.startswith("RD"), int(re.search(r"\d+", s).group())))) or 'none'}")
    print(f"\nlaunch now ({len(launch)}):")
    for r, role in launch:
        print(f"  {r.id:5} {r.name[:60]:60} gates {r.gates}  [{', '.join(role)}]  -> {', '.join(f for f, _ in r.files)}")
    if held:
        print("\nready but held:")
        for r, why in held:
            print(f"  {r.id:5} {r.name[:60]:60} {why}")
    if waiting:
        print("\nwaiting on:")
        for r, needs in waiting:
            print(f"  {r.id:5} {r.name[:60]:60} needs {', '.join(needs)}")
    SLOTS_FILE.write_text(str(m["slots"]))
    print(f"\nslots written to {SLOTS_FILE}")


def sections_for(launch):
    """Topic files shared by two launched readings, or new: each reading's section heading in them."""
    users = {}
    for r, _ in launch:
        for f, new in r.files:
            users.setdefault(f, []).append((r, new))
    out = {}
    for f, rs in users.items():
        if len(rs) > 1 or any(new for _, new in rs):
            for r, _ in rs:
                out.setdefault(r.id, []).append((f, f"{r.name} ({r.id})"))
    return out


def cmd_prompts(args):
    m, launch, _, _, _ = plan(args)
    for r, role in launch:
        r.role = role
    head = head_hash()
    sections = sections_for(launch)
    out = Path(args.dir)
    out.mkdir(parents=True, exist_ok=True)
    for r, _ in launch:
        (out / f"{r.id.lower()}.txt").write_text(prompt_for(r, head, sections))
        print(f"{out / (r.id.lower() + '.txt')}")


def index_row(text, row):
    """The index with the row added at the end of its table, the first
    table whose rows link a topic file."""
    lines = text.splitlines(keepends=True)
    first = next((i for i, l in enumerate(lines) if l.startswith("| [")), None)
    if first is None:
        return text.rstrip("\n") + "\n" + row
    end = first
    while end < len(lines) and lines[end].startswith("|"):
        end += 1
    return "".join(lines[:end] + [row] + lines[end:])


def cmd_stubs(args):
    m, launch, _, _, _ = plan(args)
    sections = sections_for(launch)
    index = SOURCES / "README.md"
    index_text = index.read_text()
    files = {}
    for rid, lst in sections.items():
        for f, h in lst:
            files.setdefault(f, []).append((rid, h))
    for f, heads in files.items():
        path = SOURCES / f
        if not path.exists():
            title = f[:-3].replace("-", " ").capitalize()
            path.write_text(f"# {title}\n\nSigla, numbering and quoting rules are in [README.md](README.md).\n")
            ids = ", ".join(rid for rid, _ in heads)
            row = f"| [{f}]({f}) | {title}: the sections below, one per reading | {ids} | Stub: nothing read yet |\n"
            index_text = index_row(index_text, row)
            print(f"made {path}")
        text = path.read_text()
        for rid, h in heads:
            if f"({rid})" in text:
                continue
            text = text.rstrip("\n") + f"\n\n\n## {h}\n\nNothing read yet: {rid}'s passages, with the English beside them, go here.\n"
            print(f"{f}: section for {rid}")
        path.write_text(text)
    index.write_text(index_text)
    if not files:
        print("no shared or new topic file among the launched readings: no stubs needed")


def cmd_slot(args):
    n = args.n or (int(SLOTS_FILE.read_text()) if SLOTS_FILE.exists() else 2)
    n = max(n, 1)
    cmd = ["systemd-run", "--user", "--scope", "--quiet", "-p", f"MemoryMax={READER_CAP}"] + args.rest
    locks = [RUNTIME / f"zanshin-gpu-slot-{i}.lock" for i in range(n)]
    handles = [open(l, "w") for l in locks]
    held = None
    while held is None:
        for i, h in enumerate(handles):       # the first free slot, without waiting
            try:
                fcntl.flock(h, fcntl.LOCK_EX | fcntl.LOCK_NB)
                held = i
                break
            except BlockingIOError:
                continue
        if held is None:                      # none free: wait on the first (any would do)
            fcntl.flock(handles[0], fcntl.LOCK_EX)
            held = 0
    print(f"# GPU slot {held + 1} of {n}", file=sys.stderr, flush=True)
    try:
        return subprocess.call(cmd)
    finally:
        fcntl.flock(handles[held], fcntl.LOCK_UN)


def main(argv):
    p = argparse.ArgumentParser(description=__doc__.split("\n\n")[0])
    sub = p.add_subparsers(dest="cmd", required=True)
    sub.add_parser("machine")

    def plan_options(sp):
        sp.add_argument("--agents", type=int, help="subagents at once (default: what the machine bears)")
        sp.add_argument("--gpu", nargs="*", default=[], help="readings that need the card, overruling the guess")
        sp.add_argument("--no-gpu", nargs="*", default=[], help="readings that do not")
        sp.add_argument("--json", action="store_true")
    plan_options(sub.add_parser("plan"))
    sp = sub.add_parser("prompts")
    sp.add_argument("dir")
    plan_options(sp)
    plan_options(sub.add_parser("stubs"))
    sp = sub.add_parser("slot")
    sp.add_argument("-n", type=int, help="the number of slots (default: the last plan's)")
    sp.add_argument("rest", nargs=argparse.REMAINDER, help="-- CMD...")
    args = p.parse_args(argv)
    if args.cmd == "machine":
        print_machine(machine())
    elif args.cmd == "plan":
        cmd_plan(args)
    elif args.cmd == "prompts":
        cmd_prompts(args)
    elif args.cmd == "stubs":
        cmd_stubs(args)
    elif args.cmd == "slot":
        args.rest = args.rest[1:] if args.rest[:1] == ["--"] else args.rest
        return cmd_slot(args)


if __name__ == "__main__":
    sys.exit(main(sys.argv[1:]))
