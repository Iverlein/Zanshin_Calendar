# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at https://mozilla.org/MPL/2.0/.
"""Drive Zanshin Calendar on the zanshin-test emulator: preferences, language,
taps by on-screen text, screenshots with the screen's text beside them.

Test values only (AGENTS.md: no personal data): Kyoto, zone Asia/Tokyo, and a
birth date of 1976-06-01, a fire dragon year, the subject of Gyurme Dorje's
charts 6.2 and 8.1. The app must be a debug build, so that run-as can write
its preferences. The emulator is booted with
  ~/Android/Sdk/emulator/emulator -avd zanshin-test -no-snapshot-save -no-boot-anim
and each boot returns to its snapshot; restore() puts back the snapshot's
own preferences after a run.
"""
import html
import re
import subprocess
import tempfile
import time
from pathlib import Path

SERIAL = "emulator-5554"
PKG = "io.github.iverlein.zanshin"
BIRTH_1976_06_01 = 2343  # epoch days
KYOTO_LAT = 4630125598962940169  # 35.0211 as Double raw bits, as the app stores it
KYOTO_LON = 4638980428775913266  # 135.7538


def adb(*args, capture=False):
    r = subprocess.run(["adb", "-s", SERIAL, *args], check=True, capture_output=True)
    return r.stdout if capture else None


def sh(command):
    return adb("shell", command, capture=True).decode(errors="replace")


def prefs(calendar="TIBETAN", birth=BIRTH_1976_06_01, kigaku=True):
    """Write the app's preferences; the app is stopped first. birth=None leaves it unset."""
    birth_line = f'<long name="birth" value="{birth}" />' if birth is not None else ""
    xml = f"""<?xml version='1.0' encoding='utf-8' standalone='yes' ?>
<map>
    <string name="calendar">{calendar}</string>
    <string name="zone">Asia/Tokyo</string>
    {birth_line}
    <long name="lon" value="{KYOTO_LON}" />
    <string name="label">Kyoto · 35.02°N 135.75°E</string>
    <boolean name="kigaku" value="{'true' if kigaku else 'false'}" />
    <long name="lat" value="{KYOTO_LAT}" />
</map>
"""
    with tempfile.NamedTemporaryFile("w", suffix=".xml", encoding="utf-8", delete=False) as f:
        f.write(xml)
    adb("shell", "am", "force-stop", PKG)
    adb("push", f.name, "/data/local/tmp/z.xml")
    Path(f.name).unlink()
    sh(f"run-as {PKG} sh -c 'cat /data/local/tmp/z.xml > shared_prefs/zanshin.xml'")


def language(tag):
    """The app's own language (per-app locale): "ru", "en", or "" to follow the system."""
    sh(f'cmd locale set-app-locales {PKG} --locales "{tag}"')


def restore():
    """The snapshot's preferences and language, as they were before a run."""
    prefs("KYUREKI", birth=20706)
    language("")


def start(wait=6):
    adb("shell", "am", "start", "-n", f"{PKG}/zanshin.app.MainActivity")
    time.sleep(wait)


def nodes():
    """The screen's nodes as (text, content description, centre x, centre y)."""
    sh("uiautomator dump /data/local/tmp/ui.xml")
    xml = sh("cat /data/local/tmp/ui.xml")
    out = []
    for m in re.finditer(r'<node [^>]*?text="([^"]*)"[^>]*?content-desc="([^"]*)"[^>]*?bounds="\[(\d+),(\d+)\]\[(\d+),(\d+)\]"', xml):
        t, d, x1, y1, x2, y2 = m.groups()
        out.append((html.unescape(t), html.unescape(d), (int(x1) + int(x2)) // 2, (int(y1) + int(y2)) // 2))
    return out


class Shots:
    """Numbered screenshots in a folder, each with the screen's text beside it, also printed."""

    def __init__(self, folder):
        self.folder = Path(folder)
        self.folder.mkdir(parents=True, exist_ok=True)
        self.n = 0

    def __call__(self, name):
        self.n += 1
        time.sleep(1.2)
        stem = self.folder / f"{self.n:02d}-{name}"
        stem.with_suffix(".png").write_bytes(adb("exec-out", "screencap", "-p", capture=True))
        texts = [f"{t}\t{d}" for t, d, _, _ in nodes() if t or d]
        stem.with_suffix(".txt").write_text("\n".join(texts) + "\n", encoding="utf-8")
        print(f"== {self.n:02d} {name}")
        print("\n".join(f"  {line}" for line in texts))


def tap(pattern, desc=None, timeout=8):
    """
    Tap the first node whose text or description matches; desc=True matches the
    description only. Waits up to [timeout] seconds for it to appear: a cold
    start after an install takes longer than start()'s wait.
    """
    deadline = time.monotonic() + timeout
    while True:
        for t, d, x, y in nodes():
            hit = re.search(pattern, d) if desc else (re.search(pattern, t) or (desc is None and re.search(pattern, d)))
            if hit:
                adb("shell", "input", "tap", str(x), str(y))
                time.sleep(1.2)
                return True
        if time.monotonic() > deadline:
            print(f"not found: {pattern}")
            return False
        time.sleep(1)


def back():
    """Back: closes a sheet or balloon; with none open it leaves the app."""
    adb("shell", "input", "keyevent", "KEYCODE_BACK")
    time.sleep(1)


def up():
    adb("shell", "input", "swipe", "540", "1900", "540", "900", "2000")
    time.sleep(0.8)


def top():
    for _ in range(6):
        adb("shell", "input", "swipe", "540", "700", "540", "2000", "150")
