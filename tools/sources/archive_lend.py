# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at https://mozilla.org/MPL/2.0/.
"""Read pages of a lending-only archive.org item (BDRC's restricted scans) under the owner's login.

  archive_lend.py login JAR                 log in; ARCHIVE_USER and ARCHIVE_PW from the environment
  archive_lend.py borrow JAR ID             a one-hour browse loan of item ID
  archive_lend.py pages JAR ID FILE OUT     the volume file's page list (leaf, page, uri) as JSON
  archive_lend.py view JAR OUT ID FILE LEAF...
                                            each leaf as archive.org's own reader shows it,
                                            screenshot from a headed Chromium (needs playwright)
  archive_lend.py giveback JAR ID           return the loan

JAR is a Netscape cookie file kept in a scratch directory (mode 600): the session
and loan cookies, never printed, deleted after the work. The password comes from
the keyring through secret-run only, never from argv or a file:

  ARCHIVE_USER=<login> secret-run exec ARCHIVE_PW=title:Archive.org -- \\
      python3 -I tools/sources/archive_lend.py login /scratch/ia/cookies.txt

ID is the item (bdrc-W28845); FILE is one of its volume files, the item's name
for the first volume and NAME-1, NAME-2 … for the next (KD: bdrc-W28845 is vol. 1,
bdrc-W28845-1 vol. 2; MK: bdrc-W1PD152297-9 is vol. 10), each opening with
TBRC's title sheet that names the volume. The page list's page numbers are the
reader's, not always the printed ones: read the number on the page.

The images archive.org serves on a loan are obfuscated; this tool does not undo
that, and must not. It reads a page the way a borrower does, in the reader, and
the screenshot is what is read (crop and enlarge it, or run hf_read.py on it).
Run `view` with a Python that has playwright (a throwaway virtualenv:
`pip install playwright pillow && playwright install chromium`).
"""
import http.cookiejar
import json
import os
import sys
import time
import urllib.error
import urllib.parse
import urllib.request
from pathlib import Path

UA = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0 Safari/537.36"
LOANS = "https://archive.org/services/loans/loan/"


def opener(jar_path):
    jar = http.cookiejar.MozillaCookieJar(jar_path)
    if Path(jar_path).exists():
        jar.load(ignore_discard=True, ignore_expires=True)
    op = urllib.request.build_opener(urllib.request.HTTPCookieProcessor(jar))
    op.addheaders = [("User-Agent", UA)]
    return op, jar


def save(jar, jar_path):
    jar.save(ignore_discard=True, ignore_expires=True)
    os.chmod(jar_path, 0o600)


def post(op, url, data, referer=None):
    req = urllib.request.Request(url, data=urllib.parse.urlencode(data).encode(),
                                 headers={"Referer": referer} if referer else {})
    with op.open(req, timeout=60) as r:
        return r.status, r.read()


def login(jar_path):
    """Keeps only the two session cookies of xauthn's answer, which also carries S3 keys."""
    op, jar = opener(jar_path)
    try:
        _, body = post(op, "https://archive.org/services/xauthn/?op=login",
                       {"email": os.environ["ARCHIVE_USER"], "password": os.environ["ARCHIVE_PW"]})
    except urllib.error.HTTPError as e:
        print("login: HTTP", e.code)  # 502 now and then: try again
        return 1
    d = json.loads(body)
    cookies = d.get("values", {}).get("cookies", {}) if d.get("success") else {}
    for name in ("logged-in-user", "logged-in-sig"):
        if name not in cookies:
            print("login: refused")
            return 1
        value = cookies[name].split(";")[0].strip()  # the whole Set-Cookie string comes back
        if value.startswith(name + "="):
            value = value[len(name) + 1:]
        jar.set_cookie(http.cookiejar.Cookie(0, name, value, None, False, ".archive.org", True, True,
                                             "/", True, True, None, False, None, None, {}))
    save(jar, jar_path)
    print("login: ok")
    return 0


def borrow(jar_path, ident):
    op, jar = opener(jar_path)
    for action in ("browse_book", "create_token"):
        try:
            status, body = post(op, LOANS, {"action": action, "identifier": ident}, f"https://archive.org/details/{ident}")
        except urllib.error.HTTPError as e:
            print(action, "HTTP", e.code)
            return 1
        ok = json.loads(body).get("success")
        print(action, status, "success" if ok else "refused")
        if not ok:
            return 1
    save(jar, jar_path)
    return 0


def pages(jar_path, ident, sub, out):
    op, jar = opener(jar_path)
    with op.open(f"https://archive.org/metadata/{ident}", timeout=60) as r:
        meta = json.load(r)
    q = urllib.parse.urlencode({"id": ident, "itemPath": meta["dir"], "server": meta["server"], "format": "json",
                                "subPrefix": sub, "requestUri": f"/details/{ident}"})
    req = urllib.request.Request(f"https://{meta['server']}/BookReader/BookReaderJSIA.php?{q}",
                                 headers={"Referer": f"https://archive.org/details/{ident}"})
    with op.open(req, timeout=60) as r:
        data = json.load(r).get("data", {})
    rows = [{"leaf": p.get("leafNum"), "page": p.get("pageNum"), "uri": p.get("uri")}
            for spread in data.get("brOptions", {}).get("data", []) for p in spread]
    Path(out).write_text(json.dumps(rows, ensure_ascii=False, indent=0))
    print(sub, len(rows), "pages ->", out)
    return 0


def view(jar_path, out, ident, sub, *leaves):
    from playwright.sync_api import sync_playwright
    jar = http.cookiejar.MozillaCookieJar(jar_path)
    jar.load(ignore_discard=True, ignore_expires=True)
    out = Path(out)
    out.mkdir(parents=True, exist_ok=True)
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=False)
        ctx = browser.new_context(viewport={"width": 1400, "height": 2400}, device_scale_factor=3)
        ctx.add_cookies([{"name": c.name, "value": c.value, "domain": "." + c.domain.lstrip("."), "path": c.path or "/",
                          "secure": True} for c in jar if "archive.org" in c.domain])
        page = ctx.new_page()
        for leaf in leaves:
            page.goto(f"https://archive.org/details/{ident}/{sub}/page/n{leaf}/mode/1up",
                      wait_until="domcontentloaded", timeout=120000)
            img = None
            for _ in range(90):
                # the reader shows several pages at once: take the one whose container is this leaf
                el = page.locator(f".BRpagecontainer.pagediv{leaf} img.BRpageimage")
                if el.count() and el.first.evaluate("e => e.complete && e.naturalWidth > 0"):
                    img = el.first
                    img.scroll_into_view_if_needed()
                    break
                time.sleep(1)
            if not img:
                print(leaf, "no page image")
                continue
            time.sleep(2)
            target = out / f"{sub}_{int(leaf):04d}.png"
            img.screenshot(path=str(target), scale="device")
            print(leaf, "->", target.name)
        browser.close()
    return 0


def giveback(jar_path, ident):
    op, _ = opener(jar_path)
    try:
        status, body = post(op, LOANS, {"action": "return_loan", "identifier": ident}, f"https://archive.org/details/{ident}")
        print("return_loan", status, json.loads(body).get("success"))
    except urllib.error.HTTPError as e:
        print("return_loan HTTP", e.code)
    return 0


if __name__ == "__main__":
    cmd, *args = sys.argv[1:]
    sys.exit({"login": login, "borrow": borrow, "pages": pages, "view": view, "giveback": giveback}[cmd](*args))
