# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at https://mozilla.org/MPL/2.0/.
"""Transcribe page images with a local vision model served by llm-serve.

  local_read.py PRESET OUTDIR IMG... [--lines] [--prompt TEXT] [--max-tokens N]

PRESET names an llm-serve preset (llm-serve --list); this script carries no
launch flags of its own, as MONOLITH's automation asks of every consumer
(~/automation/ai/README.md). If nothing serves on the port, it starts
`llm-serve PRESET` and stops it when done; a server it did not start is left
alone, and if that server holds another preset the script stops with a
message rather than taking the port. SIGTERM and SIGHUP stop it cleanly
too, and at the end it checks that no model is left on the GPU (gpu.py). LLM_ENDPOINT overrides the address,
LLM_PRESETS the preset registry (for trying a model before it is added).

--lines cuts a page into text lines first (by the horizontal ink profile,
which suits typeset pages) and sends one line per request, as line-level
OCR models such as Yigdzin-1 expect. Without it the whole page is sent.
Output: OUTDIR/<image stem>.txt. Score against a passage read on the scan
with score_reading.py.
"""
import base64
import io
import json
import os
import shutil
import subprocess
import sys
import time
import urllib.error
import urllib.request
from pathlib import Path

from PIL import Image

sys.path.insert(0, str(Path(__file__).parent))
import gpu  # noqa: E402

ENDPOINT = os.environ.get("LLM_ENDPOINT", "http://127.0.0.1:8080").rstrip("/")
DEFAULT_PROMPT = ("Transcribe all the Tibetan text in this image into Tibetan Unicode exactly as printed, "
                  "keeping shad and tsheg. Do not correct or complete anything. Output only the text.")


def llm_serve(*args):
    r = subprocess.run(["llm-serve", *args], capture_output=True, text=True, timeout=60)
    return r.stdout.strip() if r.returncode == 0 else ""


def healthy():
    try:
        with urllib.request.urlopen(ENDPOINT + "/health", timeout=5) as r:
            return r.status == 200
    except Exception:
        return False


class Lease:
    """Start llm-serve PRESET if the port is free; stop it on exit only if we started it."""

    def __init__(self, preset):
        self.preset, self.proc = preset, None

    def __enter__(self):
        if not shutil.which("llm-serve"):
            sys.exit("llm-serve not found: no local models on this machine")
        running = llm_serve("--preset")
        if running:
            if running != self.preset:
                sys.exit(f"port is held by llm-serve {running}; free it (llm-serve --stop) or ask its owner")
            return self
        if healthy():
            sys.exit(f"{ENDPOINT} answers, but llm-serve does not know that server; leaving it alone")
        log = open(Path(os.environ.get("TMPDIR", "/tmp")) / f"llm-serve-{self.preset}.log", "w")
        self.proc = subprocess.Popen(["llm-serve", self.preset], stdin=subprocess.DEVNULL, stdout=log, stderr=subprocess.STDOUT)
        for _ in range(300):
            if healthy():
                return self
            if self.proc.poll() is not None:
                self.stop()
                sys.exit(f"llm-serve {self.preset} exited with {self.proc.returncode}; see {log.name}")
            time.sleep(1)
        self.stop()
        sys.exit(f"llm-serve {self.preset} did not come up in 300 s; see {log.name}")

    def stop(self):
        """Stop the server and wait until it is really gone."""
        llm_serve("--stop")
        for _ in range(60):
            if not llm_serve("--preset") and not healthy():
                return
            time.sleep(1)
        print(f"# llm-serve {self.preset} still up 60 s after --stop; check llm-serve --status", flush=True)

    def __exit__(self, *exc):
        if self.proc:
            self.stop()
        elif llm_serve("--preset"):
            print(f"# llm-serve {self.preset} left running: it was up before this run", flush=True)
            return
        gpu.report()


def data_url(im):
    buf = io.BytesIO()
    im.convert("RGB").save(buf, format="PNG")
    return "data:image/png;base64," + base64.b64encode(buf.getvalue()).decode()


def ask(im, prompt, max_tokens=2048):
    body = {
        "messages": [{"role": "user", "content": [
            {"type": "image_url", "image_url": {"url": data_url(im)}},
            {"type": "text", "text": prompt},
        ]}],
        "temperature": 0,
        "repeat_penalty": 1.15,  # small OCR models loop on a line they cannot read
        "max_tokens": max_tokens,
    }
    req = urllib.request.Request(ENDPOINT + "/v1/chat/completions", json.dumps(body).encode(),
                                 {"Content-Type": "application/json"})
    with urllib.request.urlopen(req, timeout=900) as r:
        return json.load(r)["choices"][0]["message"]["content"]


def lines(page, core=0.2, min_height=8, column=(0.55, 0.95)):
    """Text lines of a typeset page. Tibetan vowel signs bridge the white
    between lines, so lines are found by their dense cores (the head line and
    letter bodies, where the ink profile passes a fifth of its maximum), and
    each line runs from the emptiest row above its core to the one below.
    Cores are taken from two profiles: one over a column at the right of the
    page, because the White Beryl's woodcuts stand at the left and a profile
    across them merges the text lines beside a figure into one; and one over
    the whole width, for the short last lines of a verse that do not reach
    that column (a whole-width core is kept only where no column core is)."""
    g = page.convert("L")
    w, h = g.size
    px = g.load()

    def profile(x0, x1):
        ink = [sum(1 for x in range(x0, x1, 2) if px[x, y] < 128) for y in range(h)]
        return [sum(ink[max(0, y - 2):y + 3]) for y in range(h)]

    def find(smooth):
        threshold = max(smooth) * core
        found, start = [], None
        for y, v in enumerate(smooth + [0]):
            if v > threshold and start is None:
                start = y
            elif v <= threshold and start is not None:
                if y - start >= min_height:
                    found.append((start, y))
                start = None
        return found

    full = profile(0, w)
    in_column = find(profile(int(w * column[0]), int(w * column[1])))
    cores = in_column + [c for c in find(full)
                         if not any(c[0] < b and a < c[1] for a, b in in_column)]
    cores.sort()
    cuts = [0]
    for (a0, a1), (b0, b1) in zip(cores, cores[1:]):
        cuts.append(min(range(a1, max(a1, b0) + 1), key=lambda y: full[y]))
    cuts.append(h)
    return [g.crop((0, cuts[i], w, cuts[i + 1])) for i in range(len(cores))]


def main(argv):
    by_line = "--lines" in argv
    argv = [a for a in argv if a != "--lines"]
    prompt = DEFAULT_PROMPT
    if "--prompt" in argv:
        i = argv.index("--prompt")
        prompt = argv[i + 1]
        argv = argv[:i] + argv[i + 2:]
    max_tokens = 2048  # a reasoning model needs more: it thinks before it transcribes
    if "--max-tokens" in argv:
        i = argv.index("--max-tokens")
        max_tokens = int(argv[i + 1])
        argv = argv[:i] + argv[i + 2:]
    if len(argv) < 3:
        sys.exit(__doc__)
    preset, out, imgs = argv[0], Path(argv[1]), argv[2:]
    out.mkdir(parents=True, exist_ok=True)
    gpu.exit_on_signals()  # a killed run still stops the server it started
    with Lease(preset):
        for img in imgs:
            page = Image.open(img)
            t0 = time.time()
            try:
                if by_line:
                    text = "\n".join(ask(line, prompt, 160).strip() for line in lines(page))
                else:
                    text = ask(page, prompt, max_tokens)
            except urllib.error.HTTPError as e:
                # One page the server cannot answer (its chat parser rejects the output,
                # the image overflows the context) should not end the run.
                print(img, f"failed: HTTP {e.code} {e.read()[:200]!r}", flush=True)
                continue
            (out / f"{Path(img).stem}.txt").write_text(text)
            print(img, f"{time.time() - t0:.0f} s", flush=True)


if __name__ == "__main__":
    main(sys.argv[1:])
