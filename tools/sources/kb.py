# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at https://mozilla.org/MPL/2.0/.
"""The knowledge pool's index: the graph of sources, passages, topic files, claims, questions,
readings and blocks in docs/sources/ and docs/ROADMAP.md, built into SQLite and queried.

  kb.py build                       rebuild build/sources/kb.sqlite from the files (every query does)
  kb.py check                       what the files get wrong: unknown sigla, dangling links, readings
                                    without their files, questions named but never asked, passages
                                    without a reading state; exit 1 if anything is found
  kb.py coverage [SIGLUM]           each source's pages by the best state they were read in (scan,
                                    machine, etext) and the pages nothing has opened, by chapter
  kb.py passage ID                  where a passage is quoted (file, heading, line) and who cites it
  kb.py cites TARGET                 what cites a passage id, a file or a question (q167)
  kb.py questions [--open|--answered] [--source S] [--file F]
  kb.py next                        what to read next: readings whose needs are met, ranked by what
                                    waits on them; chapters with open questions and no reading
  kb.py search WORDS...             full-text search over the passages' and files' text (FTS5)
  kb.py orphans                     files nothing links to; passages no claim or question cites
  kb.py graph                       the files, readings, blocks and questions as Mermaid
  kb.py stats

Every command takes --json (machine output) and --root DIR (the repository, default the
current directory). The structure the index reads, both the topic files as they are and the
layered layout they migrate to, is docs/sources/STRUCTURE.md; the registry of sigla it
resolves citations against is docs/sources/sources.yaml. The write side is pool.py. The judgement
about what to read and what a finding means stays with the `readings` and `sources` skills
(.claude/skills/); this script makes no model call.

Passage ids: `<siglum>[.<volume>]:<locator>` with the locator `p<page>[-<page>]` for a
printed page, `i<image>[-<image>]` for a scan image, `box<n>` for a numbered table and
`f<folio>[ab]` for a folio; a lowercase letter after the pages (`wb.2:p331b`) tells
passages on the same pages apart. `wb.1:p351-379`, `wb.2:p331`, `kp:i66`, `kp:box12`,
`wbz:i1058-1064`.
"""
import argparse
import json
import os
import re
import sqlite3
import subprocess
import sys
from collections import defaultdict
from pathlib import Path

import yaml

HERE = Path(__file__).resolve().parent
sys.path.insert(0, str(HERE))
import schedule  # noqa: E402  the roadmap's Readings table, parsed once there

STATES = ("scan", "machine", "etext")       # from the best witness to the weakest
STATE_RANK = {s: i for i, s in enumerate(STATES)}
DASH = "[-–]"
PID = re.compile(r"^(?P<sig>[a-z][a-z0-9]*)(?:\.(?P<vol>\d+))?:(?P<loc>(?:p\d+(?:" + DASH + r"\d+)?[a-z]?|i\d+(?:" + DASH + r"\d+)?[a-z]?|box\d+|f\d+[ab]?))$")
HEADING = re.compile(r"^(#{1,6})\s+(.*?)\s*#*\s*$")
ANCHOR = re.compile(r'<a id="([^"]+)"></a>\s*')
FRONT = re.compile(r"\A---\n(.*?)\n---\n", re.S)
MDLINK = re.compile(r"\]\(([^)\s#]+\.md)(#[^)]*)?\)")
QUESTION_REF = re.compile(r"\b(?:open )?question (\d+)\b")
QUESTION_ITEM = re.compile(r"^(\d+)\. ", re.M)
ANSWERED = re.compile(r"^\d+\. ~~|\b(?:Answered|Settled|Dropped)\b")
SIGLA_IN_TEXT = r"(?P<sig>WB2|WBZ|WB|KP2|KP|NM|SY|BS|MK|KD|DZ|PL|JL|OS|CN|SN|ST|GT|MP|DM|KL|GC|RS|Rabten|Gyurme Dorje|Dorje)"
SIGLA_ALIAS = {"gyurme dorje": "gd", "dorje": "gd"}
# the legacy citation forms: "WB vol. 2, pp. 351–356 (img. 359–364)", "vol. 1, p. 381",
# "KP img. 66", "(p. 351, img. 361)", "pp. 345–346"
CITE = re.compile(
    r"(?:" + SIGLA_IN_TEXT + r"[,:]?\s*\(?\s*)?"
    r"(?:vol\.\s*(?P<vol>\d)\s*,\s*)?"
    r"(?:(?:pp?\.\s*(?P<p1>\d{1,3})(?:" + DASH + r"(?P<p2>\d{1,3}))?)"
    r"|(?:img\.\s*(?P<i1>\d{1,4})(?:" + DASH + r"(?P<i2>\d{1,4}))?)"
    r"|(?:box(?:es)?\s*(?P<b1>\d{1,3})))"
)
STATE_WORDS = (
    ("scan", re.compile(r"read on the scan|on the scan|by eye|read again on the|on the Ser thang|on the Gser thang|checked on the scan|read line by line", re.I)),
    ("machine", re.compile(r"Yigdzin-1|MITRA|OCR|machine reading|machine-read", re.I)),
    ("etext", re.compile(r"etext", re.I)),
)
READ_LINE = re.compile(r"^\*?Read:\s*(scan|machine|etext)\b", re.I)
BLOCK_ROW = re.compile(r'^\| <a id="block-(\d+)"></a>\d+ \| (.*?) \| (.*?) \| (.*?) \| (.*)\|\s*$')
ITEM_ANCHOR = re.compile(r'<a id="([a-z]+\d+(?:-\d+)?)"></a>([A-Z]+[\d.]+)')

SCHEMA = """
create table source(siglum text primary key, title text, rank int, role text, locator text);
create table file(path text primary key, kind text, title text, words int, front text);
create table passage(id text primary key, siglum text, vol int, loc text, lo int, hi int, state text,
                     file text, heading text, line int, anchor text);
create table claim(id text primary key, file text, type text, title text, line int);
create table question(id int primary key, state text, file text, line int, text text);
create table reading(id text primary key, name text, done int, source text, topic text);
create table block(id int primary key, name text, built int);
create table link(src text, kind text, dst text, line int);
create index link_dst on link(dst);
create index link_src on link(src);
create virtual table fts using fts5(path, heading, body);
"""


def anchor_of(pid):
    return re.sub(r"[.:]", "-", pid)


def parse_pid(pid):
    m = PID.match(pid)
    if not m:
        return None
    sig, vol, loc = m.group("sig"), m.group("vol"), m.group("loc")
    nums = [int(x) for x in re.findall(r"\d+", loc)]
    lo, hi = nums[0], nums[-1]
    return dict(id=pid, siglum=sig, vol=int(vol) if vol else None, loc=loc, lo=lo, hi=hi)


def norm_dash(s):
    return s.replace("–", "-")


def pid_from_cite(m, default_sig, default_vol, sources):
    """A passage id from a legacy citation match, or None when it cannot be resolved."""
    sig = (m.group("sig") or default_sig or "").lower()
    sig = SIGLA_ALIAS.get(sig, sig)
    if sig not in sources:
        return None
    vol = m.group("vol") or default_vol
    src = sources[sig]
    if m.group("vol") and "volumes" in src and int(m.group("vol")) not in src["volumes"]:
        return None                                 # "vol. 7" of some other work
    if m.group("b1"):
        lo, hi, kind = m.group("b1"), None, "box"
    elif m.group("p1"):
        lo, hi = m.group("p1"), m.group("p2")
        if src.get("locator") in ("image", "folio", "box"):
            kind = {"image": "i", "folio": "f", "box": "box"}[src["locator"]]
        else:
            kind = "p"
    else:
        lo, hi = m.group("i1"), m.group("i2")
        kind = "i"
    loc = f"{kind}{lo}" + (f"-{hi}" if hi else "")
    if "volumes" in src:
        if kind == "p" and not vol:
            return None
        if kind == "p":
            return f"{sig}.{vol}:{loc}"
    return f"{sig}:{loc}"


class Index:
    def __init__(self, root):
        self.root = Path(root)
        self.sources_dir = self.root / "docs" / "sources"
        self.sources = yaml.safe_load((self.sources_dir / "sources.yaml").read_text())
        self.rows = defaultdict(list)
        self.problems = []
        self.warnings = []
        self.passages = {}
        self.fts = []
        self.reading_vol = {}                       # RDn -> the volume its source cell names

    def problem(self, where, what):
        self.problems.append((where, what))

    def warn(self, where, what):
        """A legacy topic file's gap: reported, not failed. The layered layout (frontmatter) makes it an error."""
        self.warnings.append((where, what))

    # --- the files ------------------------------------------------------------------------

    def read_all(self):
        for s, d in self.sources.items():
            self.rows["source"].append((s, d.get("title"), d.get("rank"), d.get("role"), d.get("locator", "page")))
        self.read_roadmap()                         # first: a heading naming a reading takes its volume
        ignored = self.gitignored()
        for path in sorted(self.sources_dir.rglob("*.md")):
            rel = path.relative_to(self.root).as_posix()
            if any(part.startswith(".") for part in path.relative_to(self.sources_dir).parts) or rel in ignored:
                continue
            self.read_file(path, rel)
        self.read_questions()
        self.read_readme_sigla()

    def gitignored(self):
        """The pool's files git ignores (docs/sources/wb/, the machine transcriptions kept
        locally): not part of the pool, not indexed."""
        try:
            out = subprocess.run(["git", "-C", str(self.root), "ls-files", "--others", "--ignored", "--exclude-standard", "--", "docs/sources"],
                                 capture_output=True, text=True, check=True).stdout
        except (subprocess.CalledProcessError, FileNotFoundError):
            return set()
        return set(out.split())

    def read_file(self, path, rel):
        text = path.read_text()
        front, body = {}, text
        m = FRONT.match(text)
        if m:
            try:
                front = yaml.safe_load(m.group(1)) or {}
            except yaml.YAMLError as e:
                self.problem(rel, f"frontmatter does not parse: {e}")
            body = text[m.end():]
        kind = front.get("kind") or ("topic" if path.parent == self.sources_dir else "unknown")
        if kind == "unknown":
            kind = {"corpus": "corpus", "claims": "claim", "questions": "question"}.get(path.parent.name, "topic")
        title = next((HEADING.match(l).group(2) for l in body.splitlines() if HEADING.match(l)), path.stem)
        self.rows["file"].append((rel, kind, title, len(body.split()), json.dumps(front, default=str) if front else None))
        if kind == "question" and front.get("id"):
            qid = int(str(front["id"]).lstrip("q"))
            self.rows["question"].append((qid, front.get("state", "open"), rel, 1, title))
            for c in front.get("cites", []) or []:
                self.rows["link"].append((f"q{qid}", "cites", str(c), 1))
            for w in front.get("waits", []) or []:
                self.rows["link"].append((f"q{qid}", "waits", str(w), 1))
        if kind == "claim":
            cid = front.get("id") or rel
            self.rows["claim"].append((cid, rel, front.get("type"), title, 1))
            for c in front.get("cites", []) or []:
                self.rows["link"].append((cid, "cites", str(c), 1))
            for t in front.get("terms", []) or []:
                self.rows["link"].append((cid, "term", str(t), 1))
            for f in front.get("feeds", []) or []:
                self.rows["link"].append((cid, "feeds", str(f), 1))
            for q in front.get("open", []) or []:
                self.rows["link"].append((cid, "opens", str(q), 1))
        self.read_links(rel, body)
        self.read_passages(rel, body, front, kind)

    def read_links(self, rel, body):
        fenced = False
        for n, line in enumerate(body.splitlines(), 1):
            if line.startswith("```"):
                fenced = not fenced
                continue
            if fenced:
                continue
            line = re.sub(r"`[^`]*`", "", line)         # an example in a code span is not a link
            for m in MDLINK.finditer(line):
                target = m.group(1)
                dst = (self.root / Path(rel).parent / target).resolve()
                try:
                    dst_rel = dst.relative_to(self.root).as_posix()
                except ValueError:
                    dst_rel = target
                if not dst.exists():
                    self.problem(f"{rel}:{n}", f"link to a missing file: {target}")
                self.rows["link"].append((rel, "file", dst_rel, n))
            for m in QUESTION_REF.finditer(line):
                if rel != "docs/sources/open-questions.md":
                    self.rows["link"].append((rel, "question", f"q{m.group(1)}", n))

    def read_passages(self, rel, body, front, kind):
        """Passages: a heading carrying a passage id (the layered layout), or a legacy heading
        with pages and images in brackets. The reading state is the `*Read: …*` line under the
        heading, else the frontmatter's, else the words of the section's first paragraph, else
        the enclosing section's. A legacy file without frontmatter cites WB by default when it
        names WB at all; a bare page or image elsewhere is not a passage."""
        lines = body.splitlines()
        default_sig = (front.get("source") or ("wb" if re.search(r"\bWB\b", body) else "")).lower()
        default_vol = str(front["volume"]) if front.get("volume") else None
        file_state = front.get("read")
        sections = []                               # [level, heading, line, paragraph lines, volume context]
        vol_at = {0: default_vol}                   # level -> the volume named by that section's head
        explicit = False                            # the current section's head or first lines named one
        for n, line in enumerate(lines, 1):
            h = HEADING.match(line)
            vm = re.search(r"\bvol\.\s*([12])\b", line)
            if h:
                level = len(h.group(1))
                for deeper in [k for k in vol_at if k >= level]:
                    del vol_at[deeper]
                parent = vol_at[max(vol_at)]
                own = self.heading_volume(line, default_sig) if not default_vol else None
                vol_at[level] = own or parent
                explicit = bool(own)
                sections.append([level, h.group(2), n, [], vol_at[level]])
            elif sections and len(sections[-1][3]) < 40 and line.strip():
                sections[-1][3].append(line)
                if vm and not default_vol and not explicit and len(sections[-1][3]) <= 12:
                    vol_at[sections[-1][0]] = sections[-1][4] = vm.group(1)
                    explicit = True
        states = {}                                 # level -> state, the enclosing sections' states
        for level, heading, n, para, vol_ctx in sections:
            text = heading + "\n" + "\n".join(para)
            a = ANCHOR.search(heading)
            heading_clean = ANCHOR.sub("", heading)
            self.fts.append((rel, heading_clean, "\n".join(para)))
            state = None
            for l in para[:2]:
                rm = READ_LINE.match(l.strip().strip("*"))
                if rm:
                    state = rm.group(1).lower()
            if state is None:
                for s, rx in STATE_WORDS:
                    if rx.search(text):
                        state = s
                        break
            for deeper in [k for k in states if k >= level]:
                del states[deeper]
            inherited = file_state or next((states[k] for k in sorted(states, reverse=True)), None)
            if state:
                states[level] = state
            state = state or inherited
            pid = None
            first = heading_clean.split()[0] if heading_clean.split() else ""
            if PID.match(norm_dash(first)):
                pid = norm_dash(first)
            else:
                cm = CITE.search(heading_clean)
                if cm and (cm.group("p1") or cm.group("i1") or cm.group("b1")) and kind in ("topic", "corpus") and (cm.group("sig") or default_sig):
                    pid = pid_from_cite(cm, default_sig, vol_ctx, self.sources)
                    if pid is None and cm.group("p1") and not cm.group("sig"):
                        self.problem(f"{rel}:{n}", f"a page without a volume, not resolved: {heading_clean[:60]}")
                    if pid:
                        pid = norm_dash(pid)
            if pid:
                p = parse_pid(pid)
                if p["siglum"] not in self.sources:
                    self.problem(f"{rel}:{n}", f"unknown siglum in passage id {pid}")
                    continue
                if state is None:
                    (self.problem if front else self.warn)(f"{rel}:{n}", f"passage {pid} has no reading state (scan, machine or etext)")
                anchor = a.group(1) if a else anchor_of(pid)
                key = pid
                k = 2
                while key in self.passages:            # the same pages quoted in two places
                    key = f"{pid}#{k}"
                    k += 1
                self.passages[key] = (pid, p, state, rel, heading_clean, n, anchor)
            # inline citations with a siglum or a volume, as links from the file
            for cm in CITE.finditer(text):
                if not (cm.group("sig") or cm.group("vol")):
                    continue
                cid = pid_from_cite(cm, default_sig, vol_ctx, self.sources)
                if cid:
                    self.rows["link"].append((rel, "cites", norm_dash(cid), n))

    def heading_volume(self, heading, sig):
        """The volume a legacy heading names: "vol. N" outright, else the chapter it names
        looked up in the registry, else the volume of the reading (RDn) it is marked with."""
        vm = re.search(r"\bvol\.\s*([12])\b", heading)
        if vm:
            return vm.group(1)
        sm = re.search(SIGLA_IN_TEXT, heading)
        if sm and SIGLA_ALIAS.get(sm.group("sig").lower(), sm.group("sig").lower()) != sig:
            return None                             # another work's chapter or page
        chapters = {int(ch): str(vol) for vol, v in (self.sources.get(sig, {}).get("volumes") or {}).items() for ch in (v.get("chapters") or {})}
        cm = re.search(r"\b(?:chapter|ch\.)\s*(\d+)\b", heading, re.I)
        if cm and int(cm.group(1)) in chapters:
            return chapters[int(cm.group(1))]
        rm = re.search(r"\b(RD\d+)\b", heading)
        if rm and rm.group(1) in self.reading_vol:
            return self.reading_vol[rm.group(1)]
        return None

    def read_roadmap(self):
        roadmap = self.root / "docs" / "ROADMAP.md"
        out, _ = schedule.readings(roadmap)
        for r in out:
            self.rows["reading"].append((r.id, r.name, int(r.done), r.source, r.topic))
            vm = re.search(r"\bvol\.\s*([12])\b", r.source)
            if vm and ("WB" in r.source or not re.search(SIGLA_IN_TEXT, r.source)):
                self.reading_vol[r.id] = vm.group(1)
            files = {}                              # path-aware: corpus/wb/ch23.md names a file in a subdirectory
            for m in re.finditer(r"([\w/-]+\.md)( \(new\))?", r.topic):
                f = m.group(1).removeprefix("sources/")     # the roadmap links from docs/
                files[f] = files.get(f, False) or bool(m.group(2))
            for f, new in files.items():
                p = self.sources_dir / f
                if not p.exists() and not new and not r.done:
                    self.problem(f"ROADMAP {r.id}", f"topic file {f} does not exist and is not (new)")
                self.rows["link"].append((r.id, "topic", f"docs/sources/{f}", 0))
            for d in r.needs:
                self.rows["link"].append((r.id, "needs", d, 0))
            for d in r.after:
                self.rows["link"].append((r.id, "after", d, 0))
            for cm in CITE.finditer(r.source):
                if cm.group("sig") or cm.group("vol"):
                    cid = pid_from_cite(cm, "wb", None, self.sources)
                    if cid:
                        self.rows["link"].append((r.id, "cites", norm_dash(cid), 0))
        text = roadmap.read_text()
        for n, line in enumerate(text.splitlines(), 1):
            bm = BLOCK_ROW.match(line)
            if bm:
                bid, name, readings, builds = int(bm.group(1)), bm.group(2), bm.group(3), bm.group(4)
                self.rows["block"].append((bid, re.sub(r"\s*—\s*built.*", "", name), int("— built" in name)))
                for rid in re.findall(r"\b(RD\d+|O\d+)\b", readings):
                    self.rows["link"].append((f"block-{bid}", "needs", rid, n))
                for other in re.findall(r"#block-(\d+)", builds):
                    self.rows["link"].append((f"block-{bid}", "after", f"block-{other}", n))
            for m in QUESTION_REF.finditer(line):
                self.rows["link"].append(("docs/ROADMAP.md", "question", f"q{m.group(1)}", n))

    def read_questions(self):
        path = self.sources_dir / "open-questions.md"
        if not path.exists():
            return
        text = path.read_text()
        starts = [(m.start(), int(m.group(1))) for m in QUESTION_ITEM.finditer(text)]
        for i, (pos, num) in enumerate(starts):
            end = starts[i + 1][0] if i + 1 < len(starts) else len(text)
            item = text[pos:end]
            line = text.count("\n", 0, pos) + 1
            state = "answered" if ANSWERED.search(item[:600]) else "open"
            head = re.sub(r"\s+", " ", item.split("\n\n")[0])[:300]
            self.rows["question"].append((num, state, "docs/sources/open-questions.md", line, head))
            for cm in CITE.finditer(item[:400]):
                if cm.group("sig") or cm.group("vol"):
                    cid = pid_from_cite(cm, "wb", None, self.sources)
                    if cid:
                        self.rows["link"].append((f"q{num}", "cites", norm_dash(cid), line))
            for lm in MDLINK.finditer(item):
                self.rows["link"].append((f"q{num}", "file", f"docs/sources/{lm.group(1)}", line))
            for rid in re.findall(r"\b(RD\d+)\b", item):
                self.rows["link"].append((f"q{num}", "waits", rid, line))

    def read_readme_sigla(self):
        readme = (self.sources_dir / "README.md").read_text()
        table = re.search(r"^## Editions.*?(?=^## |\Z)", readme, re.S | re.M)
        listed = set(s.lower() for s in re.findall(r"^\| \*\*(\w+)\*\*", table.group(0), re.M)) if table else set()
        listed |= set(s.lower() for s in re.findall(r"\(\*\*(\w+)\*\*\)", table.group(0))) if table else set()
        registry = set(self.sources)
        for s in sorted(listed - registry):
            self.problem("README.md Editions", f"siglum {s.upper()} is not in sources.yaml")
        for s in sorted(registry - listed):
            self.problem("sources.yaml", f"siglum {s.upper()} is not in README.md's Editions table")

    # --- the database ---------------------------------------------------------------------

    def write(self, db_path):
        db_path.parent.mkdir(parents=True, exist_ok=True)
        if db_path.exists():
            db_path.unlink()
        db = sqlite3.connect(db_path)
        db.executescript(SCHEMA)
        for key, (pid, p, state, rel, heading, n, anchor) in self.passages.items():
            self.rows["passage"].append((key, p["siglum"], p["vol"], p["loc"], p["lo"], p["hi"], state, rel, heading, n, anchor))
        for table, rows in self.rows.items():
            if not rows:
                continue
            width = len(rows[0])
            db.executemany(f"insert or ignore into {table} values ({','.join('?' * width)})", rows)
        db.executemany("insert into fts values (?,?,?)", self.fts)
        db.commit()
        return db


def build(args):
    ix = Index(args.root)
    ix.read_all()
    db = ix.write(args.db)
    return ix, db


# --- the queries ------------------------------------------------------------------------------

def q(db, sql, *params):
    return [tuple(r) for r in db.execute(sql, params)]


def cmd_build(args):
    ix, db = build(args)
    out = {"passages": len(ix.passages), "problems": len(ix.problems), "warnings": len(ix.warnings), "db": str(args.db)}
    emit(args, out, lambda: print(f"{out['passages']} passages, {out['problems']} problems, {out['warnings']} warnings, {args.db}"))


def cmd_check(args):
    ix, db = build(args)
    problems = list(ix.problems)
    for (src, dst, line) in q(db, "select src, dst, line from link where kind='question'"):
        if not q(db, "select 1 from question where id=?", int(dst[1:])):
            problems.append((f"{src}:{line}", f"{dst} is named but never asked"))
    for (src, dst, line) in q(db, "select src, dst, line from link where kind in ('needs','after','waits','topic')"):
        if dst.startswith("RD") or dst.startswith("O"):
            if not q(db, "select 1 from reading where id=?", dst):
                problems.append((f"{src}:{line}", f"{dst} is not in the Readings table"))
        elif dst.startswith("block-"):
            if not q(db, "select 1 from block where id=?", int(dst[6:])):
                problems.append((f"{src}:{line}", f"{dst} is not in the Blocks table"))
        elif dst.endswith(".md") and not (ix.root / dst).exists():
            problems.append((f"{src}:{line}", f"{dst} does not exist"))
    for (src, dst, line) in q(db, "select src, dst, line from link where kind='cites'"):
        p = parse_pid(dst)
        if p is None:
            problems.append((f"{src}:{line}", f"malformed passage id {dst}"))
        elif p["siglum"] not in ix.sources:
            problems.append((f"{src}:{line}", f"unknown siglum in {dst}"))
    problems.sort()
    warnings = sorted(ix.warnings)
    emit(args, {"problems": [{"where": w, "what": x} for w, x in problems], "warnings": [{"where": w, "what": x} for w, x in warnings]},
         lambda: ([print(f"{w}: {x}") for w, x in problems], [print(f"warning: {w}: {x}") for w, x in warnings],
                  print(f"{len(problems)} problems, {len(warnings)} warnings in legacy files")))
    return 1 if problems else 0


def overlaps(a_lo, a_hi, b_lo, b_hi):
    return a_lo <= b_hi and b_lo <= a_hi


def cmd_coverage(args):
    ix, db = build(args)
    out = []
    for sig, src in ix.sources.items():
        if args.siglum and sig != args.siglum.lower():
            continue
        vols = src.get("volumes") or {None: src}
        for vol, v in vols.items():
            rows = q(db, "select lo, hi, state, file, heading from passage where siglum=? and (vol is ? or ? is null) and loc like 'p%'", sig, vol, vol) \
                if vol is not None else q(db, "select lo, hi, state, file, heading from passage where siglum=?", sig)
            if not rows and not v.get("chapters"):
                continue
            best = {}
            for lo, hi, state, f, h in rows:
                for p in range(lo, hi + 1):
                    if state and (p not in best or STATE_RANK[state] < STATE_RANK[best[p]]):
                        best[p] = state
            chapters = v.get("chapters") or {}
            pages = v.get("pages")
            if chapters:
                bounds = sorted(chapters.items(), key=lambda kv: kv[1])
                spans = [(ch, start, (bounds[i + 1][1] - 1) if i + 1 < len(bounds) else (pages[1] if pages else start)) for i, (ch, start) in enumerate(bounds)]
            elif pages:
                spans = [(None, pages[0], pages[1])]
            else:
                spans = [(None, min(best), max(best))] if best else []
            for ch, lo, hi in spans:
                counts = {s: 0 for s in STATES}
                for p in range(lo, hi + 1):
                    if p in best:
                        counts[best[p]] += 1
                unread = hi - lo + 1 - sum(counts.values())
                gaps = runs([p for p in range(lo, hi + 1) if p not in best])
                out.append({"source": sig, "volume": vol, "chapter": ch, "pages": [lo, hi], **counts, "unread": unread, "gaps": gaps})

    def show():
        last = None
        for r in out:
            key = (r["source"], r["volume"])
            if key != last:
                print(f"\n{r['source'].upper()}" + (f" vol. {r['volume']}" if r["volume"] else ""))
                print("  ch.  pages      scan  mach  etxt  unread  gaps")
                last = key
            g = ", ".join(f"{a}–{b}" if a != b else str(a) for a, b in r["gaps"][:6]) + (" …" if len(r["gaps"]) > 6 else "")
            print(f"  {str(r['chapter'] or '-'):4} {r['pages'][0]:>3}–{r['pages'][1]:<4}  {r['scan']:4}  {r['machine']:4}  {r['etext']:4}  {r['unread']:6}  {g}")
    emit(args, out, show)


def runs(pages):
    out = []
    for p in pages:
        if out and out[-1][1] == p - 1:
            out[-1][1] = p
        else:
            out.append([p, p])
    return out


def cmd_passage(args):
    ix, db = build(args)
    p = parse_pid(norm_dash(args.id))
    if not p:
        sys.exit(f"not a passage id: {args.id}")
    where = q(db, "select id, state, file, heading, line, anchor from passage where siglum=? and (vol=? or ? is null) and lo<=? and hi>=? order by lo, hi",
              p["siglum"], p["vol"], p["vol"], p["hi"], p["lo"])
    cites = [(s, k, d, l) for s, k, d, l in q(db, "select src, kind, dst, line from link where kind='cites'")
             if (pp := parse_pid(d)) and pp["siglum"] == p["siglum"] and (pp["vol"] == p["vol"] or p["vol"] is None) and overlaps(pp["lo"], pp["hi"], p["lo"], p["hi"])]
    out = {"id": p["id"], "quoted": [dict(zip(("id", "state", "file", "heading", "line", "anchor"), w)) for w in where],
           "cited_by": [dict(zip(("src", "kind", "dst", "line"), c)) for c in cites]}

    def show():
        print(f"{p['id']}: quoted in {len(where)} place(s), cited by {len(cites)}")
        for w in where:
            print(f"  {w[2]}:{w[4]}  [{w[1] or '?'}]  {w[3]}  (#{w[5]})")
        for s, k, d, l in cites:
            print(f"  ← {s}:{l} cites {d}")
    emit(args, out, show)


def cmd_cites(args):
    ix, db = build(args)
    t = args.target
    if t.endswith(".md") and not t.startswith("docs/"):
        t = f"docs/sources/{t}"
    rows = q(db, "select src, kind, line from link where dst=? order by src, line", t)
    if not rows and parse_pid(norm_dash(t)):
        return cmd_passage(argparse.Namespace(id=t, **vars(args)))
    emit(args, [dict(zip(("src", "kind", "line"), r)) for r in rows],
         lambda: [print(f"{s}:{l} ({k})") for s, k, l in rows] or print(f"{len(rows)} links to {t}"))


def cmd_questions(args):
    ix, db = build(args)
    rows = q(db, "select id, state, file, line, text from question order by id")
    if args.open:
        rows = [r for r in rows if r[1] == "open"]
    if args.answered:
        rows = [r for r in rows if r[1] != "open"]
    if args.source:
        sig = args.source.lower()
        ids = {int(s[1:]) for s, d in q(db, "select src, dst from link where kind='cites' and src like 'q%'") if (p := parse_pid(d)) and p["siglum"] == sig}
        rows = [r for r in rows if r[0] in ids]
    if args.file:
        f = args.file if args.file.startswith("docs/") else f"docs/sources/{args.file}"
        ids = {int(d[1:]) for s, d in q(db, "select src, dst from link where kind='question' and src=?", f)}
        ids |= {int(s[1:]) for s, d in q(db, "select src, dst from link where kind='file' and src like 'q%' and dst=?", f)}
        rows = [r for r in rows if r[0] in ids]
    emit(args, [dict(zip(("id", "state", "file", "line", "text"), r)) for r in rows],
         lambda: [print(f"q{i:<4}{'open' if s == 'open' else 'done':5} {t[:110]}") for i, s, f, l, t in rows] or print(f"{len(rows)} questions"))


def cmd_next(args):
    ix, db = build(args)
    readings, by_id = schedule.readings(ix.root / "docs" / "ROADMAP.md")
    open_q = [(int(s[1:]), d) for s, d in q(db, "select src, dst from link where kind='cites' and src like 'q%'")]
    open_ids = {r[0] for r in q(db, "select id from question where state='open'")}
    blocks_waiting = defaultdict(set)
    for s, d in q(db, "select src, dst from link where kind='needs' and src like 'block-%'"):
        blocks_waiting[d].add(s)
    built = {f"block-{b}" for (b,) in q(db, "select id from block where built=1")}
    ranked = []
    for r in readings:
        if r.done or r.unplanned:
            continue
        unmet = [d for d in r.needs if not by_id[d].done]
        cited = [parse_pid(d) for s, k, d, l in q(db, "select src, kind, dst, line from link where src=? and kind='cites'", r.id)]
        qs = sorted({qi for qi, d in open_q if qi in open_ids and (pp := parse_pid(d))
                     and any(c and c["siglum"] == pp["siglum"] and c["vol"] == pp["vol"] and overlaps(c["lo"], c["hi"], pp["lo"], pp["hi"]) for c in cited)})
        waiting = sorted(blocks_waiting.get(r.id, set()) - built)
        score = (0 if unmet else 1, len(waiting) + r.gates, len(qs))
        ranked.append((score, r, unmet, waiting, qs))
    ranked.sort(key=lambda x: x[0], reverse=True)
    # chapters nothing has opened or planned, with open questions pointing at them
    cov = []
    cited_by_plans = [parse_pid(d) for (d,) in q(db, "select dst from link where kind='cites' and (src like 'RD%' or src like 'O%')")]
    for sig, src in ix.sources.items():
        for vol, v in (src.get("volumes") or {}).items():
            chs = sorted((v.get("chapters") or {}).items(), key=lambda kv: kv[1])
            for i, (ch, start) in enumerate(chs):
                end = chs[i + 1][1] - 1 if i + 1 < len(chs) else (v.get("pages") or [0, start])[1]
                read = q(db, "select count(*) from passage where siglum=? and vol=? and lo<=? and hi>=?", sig, vol, end, start)[0][0]
                planned = any(c and c["siglum"] == sig and c["vol"] == vol and overlaps(c["lo"], c["hi"], start, end) for c in cited_by_plans)
                qs = sorted({qi for qi, d in open_q if qi in open_ids and (pp := parse_pid(d)) and pp["siglum"] == sig and pp["vol"] == vol and overlaps(pp["lo"], pp["hi"], start, end)})
                if qs and not planned:
                    cov.append({"source": sig, "volume": vol, "chapter": ch, "pages": [start, end], "passages": read, "open_questions": qs})
    cov.sort(key=lambda c: -len(c["open_questions"]))
    out = {"readings": [{"id": r.id, "name": r.name, "ready": not unmet, "unmet": unmet, "blocks_waiting": waiting, "gates": r.gates, "open_questions": qs} for _, r, unmet, waiting, qs in ranked],
           "chapters": cov}

    def show():
        print("Readings, the readiest first (needs met; blocks and readings waiting; open questions on its pages):")
        for _, r, unmet, waiting, qs in ranked:
            flag = "ready" if not unmet else "needs " + ",".join(unmet)
            print(f"  {r.id:5} {flag:18} waits {len(waiting) + r.gates:2}  q {len(qs):2}  {r.name[:70]}")
        if cov:
            print("\nChapters with open questions and no reading planned:")
            for c in cov:
                print(f"  {c['source'].upper()} vol. {c['volume']} ch. {c['chapter']:2} pp. {c['pages'][0]}–{c['pages'][1]}: {len(c['passages']) if isinstance(c['passages'], list) else c['passages']} passages quoted, questions {', '.join(str(x) for x in c['open_questions'][:8])}")
    emit(args, out, show)


def cmd_search(args):
    ix, db = build(args)
    query = " ".join(f'"{w}"' for w in args.words)
    rows = q(db, "select path, heading, snippet(fts, 2, '«', '»', '…', 12) from fts where fts match ? order by rank limit ?", query, args.limit)
    emit(args, [dict(zip(("file", "heading", "snippet"), r)) for r in rows],
         lambda: [print(f"{p}  {h[:50]}\n    {s}") for p, h, s in rows] or print(f"{len(rows)} hits"))


def cmd_orphans(args):
    ix, db = build(args)
    linked = {d for (d,) in q(db, "select distinct dst from link")}
    files = []
    for f, k, front in q(db, "select path, kind, front from file"):
        fm = json.loads(front) if front else {}
        if k == "claim":
            if (fm.get("id") or f) not in linked and not fm.get("feeds"):
                files.append(f)
        elif k == "question":
            if f"q{str(fm.get('id', '')).lstrip('q')}" not in linked:
                files.append(f)
        elif f not in linked and not f.endswith("README.md"):
            files.append(f)
    cited = set()
    for (d,) in q(db, "select distinct dst from link where kind='cites' and src not like 'docs/%'"):
        if p := parse_pid(d):
            cited.add((p["siglum"], p["vol"], p["lo"], p["hi"]))
    uncited = [(i, f, h) for i, s, v, lo, hi, f, h in q(db, "select id, siglum, vol, lo, hi, file, heading from passage")
               if not any(cs == s and cv == v and overlaps(clo, chi, lo, hi) for cs, cv, clo, chi in cited)]
    emit(args, {"files": files, "passages": [{"id": i, "file": f, "heading": h} for i, f, h in uncited]},
         lambda: (print(f"{len(files)} files nothing links to:"), [print(f"  {f}") for f in files],
                  print(f"{len(uncited)} passages no claim, question or reading cites (of {q(db, 'select count(*) from passage')[0][0]})")))


def cmd_graph(args):
    ix, db = build(args)
    lines = ["graph LR"]
    seen = set()

    def node(i, label, shape=("[", "]")):
        key = re.sub(r"[^A-Za-z0-9_]", "_", i)
        if key not in seen:
            seen.add(key)
            lines.append(f'  {key}{shape[0]}"{label}"{shape[1]}')
        return key
    for rid, name, done in q(db, "select id, name, done from reading"):
        node(rid, f"{rid}{' ✓' if done else ''}", ("(", ")"))
    for bid, name, built in q(db, "select id, name, built from block"):
        node(f"block-{bid}", f"block {bid}{' ✓' if built else ''}", ("{{", "}}"))
    for s, k, d in q(db, "select distinct src, kind, dst from link where kind in ('needs','topic','after')"):
        if d.endswith(".md"):
            dn = node(d, Path(d).name)
        else:
            dn = node(d, d)
        lines.append(f"  {node(s, s)} -->|{k}| {dn}")
    text = "\n".join(lines)
    emit(args, {"mermaid": text}, lambda: print(text))


def cmd_stats(args):
    ix, db = build(args)
    out = {t: q(db, f"select count(*) from {t}")[0][0] for t in ("source", "file", "passage", "claim", "question", "reading", "block", "link")}
    out["open_questions"] = q(db, "select count(*) from question where state='open'")[0][0]
    out["passages_by_state"] = dict(q(db, "select coalesce(state,'?'), count(*) from passage group by state"))
    out["words"] = q(db, "select sum(words) from file")[0][0]
    emit(args, out, lambda: [print(f"{k:18} {v}") for k, v in out.items()])


def emit(args, data, show):
    if args.json:
        json.dump(data, sys.stdout, ensure_ascii=False, indent=1)
        print()
    else:
        show()


def main(argv=None):
    ap = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    ap.add_argument("--root", default=".", help="the repository root (default: the current directory)")
    ap.add_argument("--db", help="the SQLite file (default: <root>/build/sources/kb.sqlite)")
    ap.add_argument("--json", action="store_true")
    sp = ap.add_subparsers(dest="cmd", required=True)
    sp.add_parser("build").set_defaults(fn=cmd_build)
    sp.add_parser("check").set_defaults(fn=cmd_check)
    p = sp.add_parser("coverage"); p.add_argument("siglum", nargs="?"); p.set_defaults(fn=cmd_coverage)
    p = sp.add_parser("passage"); p.add_argument("id"); p.set_defaults(fn=cmd_passage)
    p = sp.add_parser("cites"); p.add_argument("target"); p.set_defaults(fn=cmd_cites)
    p = sp.add_parser("questions"); p.add_argument("--open", action="store_true"); p.add_argument("--answered", action="store_true")
    p.add_argument("--source"); p.add_argument("--file"); p.set_defaults(fn=cmd_questions)
    sp.add_parser("next").set_defaults(fn=cmd_next)
    p = sp.add_parser("search"); p.add_argument("words", nargs="+"); p.add_argument("--limit", type=int, default=20); p.set_defaults(fn=cmd_search)
    sp.add_parser("orphans").set_defaults(fn=cmd_orphans)
    sp.add_parser("graph").set_defaults(fn=cmd_graph)
    sp.add_parser("stats").set_defaults(fn=cmd_stats)
    args = ap.parse_args(argv)
    args.root = Path(args.root).resolve()
    args.db = Path(args.db) if args.db else args.root / "build" / "sources" / "kb.sqlite"
    return args.fn(args) or 0


if __name__ == "__main__":
    sys.exit(main())
