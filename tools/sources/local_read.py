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
message rather than taking the port. LLM_ENDPOINT overrides the address,
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
import urllib.request
from pathlib import Path

from PIL import Image

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
                sys.exit(f"llm-serve {self.preset} exited with {self.proc.returncode}; see {log.name}")
            time.sleep(1)
        sys.exit(f"llm-serve {self.preset} did not come up in 300 s; see {log.name}")

    def __exit__(self, *exc):
        if self.proc:
            llm_serve("--stop")


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


def lines(page, core=0.2, min_height=8):
    """Text lines of a typeset page. Tibetan vowel signs bridge the white
    between lines, so lines are found by their dense cores (the head line and
    letter bodies, where the ink profile passes a fifth of its maximum), and
    each line runs from the emptiest row above its core to the one below."""
    g = page.convert("L")
    w, h = g.size
    px = g.load()
    ink = [sum(1 for x in range(0, w, 2) if px[x, y] < 128) for y in range(h)]
    smooth = [sum(ink[max(0, y - 2):y + 3]) for y in range(h)]
    threshold = max(smooth) * core
    cores, start = [], None
    for y, v in enumerate(smooth + [0]):
        if v > threshold and start is None:
            start = y
        elif v <= threshold and start is not None:
            if y - start >= min_height:
                cores.append((start, y))
            start = None
    cuts = [0]
    for (a0, a1), (b0, b1) in zip(cores, cores[1:]):
        cuts.append(min(range(a1, b0 + 1), key=lambda y: smooth[y]))
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
    with Lease(preset):
        for img in imgs:
            page = Image.open(img)
            t0 = time.time()
            if by_line:
                text = "\n".join(ask(line, prompt, 160).strip() for line in lines(page))
            else:
                text = ask(page, prompt, max_tokens)
            (out / f"{Path(img).stem}.txt").write_text(text)
            print(img, f"{time.time() - t0:.0f} s", flush=True)


if __name__ == "__main__":
    main(sys.argv[1:])
