# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at https://mozilla.org/MPL/2.0/.
"""Leaving no model behind on the GPU when a reader finishes.

  gpu.py          list what still holds the GPU (exit 1 if a model does)

Used by local_read.py and hf_read.py: exit_on_signals() turns SIGTERM and
SIGHUP into a normal exit, so a killed run still stops the llm-serve server
it started (a server left up holds 2–6 GB of the 8 GB card and blocks the
next reader); report() runs at the end and names any model process still
on the GPU. Desktop processes (the compositor, browsers) are not models
and are left out.
"""
import os
import signal
import subprocess
import sys

MODELS = ("llama-server", "python", "vllm", "ollama")


def exit_on_signals():
    def handler(sig, frame):
        raise SystemExit(128 + sig)
    for s in (signal.SIGTERM, signal.SIGHUP):
        signal.signal(s, handler)


def leftovers(exclude=()):
    """Model processes on the GPU: (pid, name, MiB), except the pids in exclude."""
    try:
        out = subprocess.run(["nvidia-smi", "--query-compute-apps=pid,process_name,used_memory",
                              "--format=csv,noheader,nounits"], capture_output=True, text=True, timeout=30).stdout
    except (OSError, subprocess.TimeoutExpired):
        return []
    found = []
    for line in out.splitlines():
        pid, name, mem = (f.strip() for f in line.split(","))
        if int(pid) not in exclude and any(m in os.path.basename(name) for m in MODELS):
            found.append((int(pid), name, int(mem)))
    return found


def report(exclude=()):
    left = leftovers(exclude)
    if not left:
        print("# GPU: no model left running", flush=True)
    for pid, name, mem in left:
        print(f"# GPU: still held by pid {pid} {name} ({mem} MiB); "
              f"stop it if it is not another job's (llm-serve --stop for llama-server)", flush=True)
    return not left


if __name__ == "__main__":
    sys.exit(0 if report() else 1)
