#!/usr/bin/env python3
# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at https://mozilla.org/MPL/2.0/.
"""Keep a GitHub release for every version tag, with F-Droid's APK attached.

For each tag v<versionName>: the release is created if it is missing, with the
store changelogs of that versionCode (English, then Russian) as its notes.
Once f-droid.org publishes that versionCode, its APK is attached as
zanshin-<versionName>.apk. The APK is F-Droid's own build and signature, so an
install from GitHub and one from F-Droid update each other; it is checked
against the sha256 in F-Droid's signed index before upload. F-Droid publishes
some days after a tag, so the attaching waits for a later run.

Runs in .github/workflows/release.yml (on a tag, daily, by hand) with gh
authenticated by GH_TOKEN; locally the same, from the repository root.

Usage: tools/release_apks.py [--dry-run]
"""
import hashlib
import json
import re
import subprocess
import sys
import tempfile
import urllib.request
from pathlib import Path

PACKAGE = "io.github.iverlein.zanshin"
API = f"https://f-droid.org/api/v1/packages/{PACKAGE}"
INDEX = "https://f-droid.org/repo/index-v2.json"
APK = "https://f-droid.org/repo/{package}_{code}.apk"
CHANGELOGS = Path("fastlane/metadata/android")
DRY = "--dry-run" in sys.argv


def run(*args, capture=True):
    r = subprocess.run(args, check=True, capture_output=capture, text=True)
    return r.stdout if capture else None


def act(*args):
    print("  " + " ".join(args[:4]) + (" …" if len(args) > 4 else ""))
    if not DRY:
        run(*args, capture=False)


def fetch(url):
    with urllib.request.urlopen(url, timeout=120) as r:
        return r.read()


def version_code(tag):
    """The versionCode in app/build.gradle.kts at that tag."""
    gradle = run("git", "show", f"{tag}:app/build.gradle.kts")
    return int(re.search(r"versionCode\s*=\s*(\d+)", gradle).group(1))


def notes(code):
    parts = []
    for listing, heading in (("en-US", None), ("ru-RU", "По-русски")):
        f = CHANGELOGS / listing / "changelogs" / f"{code}.txt"
        if f.exists():
            text = f.read_text(encoding="utf-8").strip()
            parts.append(f"**{heading}**\n\n{text}" if heading else text)
    parts.append(
        f"Install from [F-Droid](https://f-droid.org/packages/{PACKAGE}/). "
        "The APK here, when attached, is F-Droid's own build with F-Droid's "
        "signature, so it updates from F-Droid and F-Droid from it; it appears "
        "once F-Droid has published the version, some days after the tag."
    )
    return "\n\n".join(parts)


def releases():
    out = run("gh", "release", "list", "--limit", "200", "--json", "tagName")
    names = {r["tagName"] for r in json.loads(out)}
    return {n: {a["name"] for a in json.loads(run("gh", "release", "view", n, "--json", "assets"))["assets"]} for n in names}


def main():
    tags = sorted(
        (t for t in run("git", "tag", "--list", "v*").split() if re.fullmatch(r"v\d+(\.\d+)*", t)),
        key=lambda t: [int(x) for x in t[1:].split(".")],
    )
    existing = releases()
    published = {p["versionCode"] for p in json.loads(fetch(API))["packages"]}
    index = None
    for tag in tags:
        code, name = version_code(tag), tag[1:]
        print(f"{tag}: versionCode {code}, {'on F-Droid' if code in published else 'not yet on F-Droid'}")
        if tag not in existing:
            with tempfile.NamedTemporaryFile("w", suffix=".md", delete=False, encoding="utf-8") as f:
                f.write(notes(code))
            act("gh", "release", "create", tag, "--verify-tag", "--title", f"Zanshin Calendar {name}", "--notes-file", f.name)
            existing[tag] = set()
        asset = f"zanshin-{name}.apk"
        if code not in published or asset in existing[tag]:
            continue
        if index is None:
            index = json.loads(fetch(INDEX))["packages"][PACKAGE]["versions"]
        sha = next(v["file"]["sha256"] for v in index.values() if v["manifest"]["versionCode"] == code)
        data = fetch(APK.format(package=PACKAGE, code=code))
        if hashlib.sha256(data).hexdigest() != sha:
            sys.exit(f"{tag}: the downloaded APK does not match F-Droid's index")
        path = Path(tempfile.mkdtemp()) / asset
        path.write_bytes(data)
        act("gh", "release", "upload", tag, str(path))


if __name__ == "__main__":
    main()
