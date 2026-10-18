# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at https://mozilla.org/MPL/2.0/.
"""BDRC's full-text search, exact phrases, from a headed Chromium.

  bdrc_search.py OUT.json PHRASE...

For each phrase (searched in quotes, as an exact string) the hit count, the
etext volume and character offset of each hit's link (feed them to
`bdrc.py etext` or read the window around the offset), and the results
page's text with its snippets, into OUT.json; a summary on stdout.

The search page is a single-page app: six phrases at a time run in
same-origin iframes of one open page and are read after 25 s. The browser
is a headed Chromium of class playwright-work, which sway parks on
workspace 11 beside the Playwright MCP browser (rule 9 of ~/AGENTS.md).
Run it with a Python that has playwright, e.g. a throwaway virtualenv
(`pip install playwright==1.62.0`, matching the cached Chromium).
Search-only etexts answer 403 to the etext API: their snippet is all there
is without the lent scans (docs/sources/PLAN.md).
"""
import json
import sys
import time

from playwright.sync_api import sync_playwright

PAGE = "https://library.bdrc.io/osearch/search?q=x&uilang=en"
JS = """async (qs) => {
  const fs = qs.map(q => { const f = document.createElement('iframe');
    f.style.width = '1200px'; f.style.height = '900px';
    f.src = '/osearch/search?q=' + encodeURIComponent('"' + q + '"') + '&uilang=en';
    document.body.appendChild(f); return [q, f]; });
  await new Promise(r => setTimeout(r, 25000));
  const res = {};
  for (const [q, f] of fs) {
    const d = f.contentDocument, txt = d.body.innerText;
    const n = (txt.match(/(\\d+) hits? for/) || [])[1] || '0';
    const links = [...new Set([...d.querySelectorAll('a')].map(a => a.href)
      .filter(h => h.includes('openEtext'))
      .map(h => { const u = new URL(h); return u.searchParams.get('openEtext') + ' ' + u.searchParams.get('startChar'); }))];
    const i = txt.indexOf('Search Results');
    res[q] = {hits: n, links, text: txt.slice(i, i + 8000)};
    f.remove();
  }
  return res;
}"""


def main():
    if len(sys.argv) < 3:
        sys.exit(__doc__)
    out, phrases = sys.argv[1], sys.argv[2:]
    res = {}
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=False, args=["--ozone-platform=x11", "--class=playwright-work"])
        page = browser.new_page()
        page.goto(PAGE, timeout=120000)
        time.sleep(6)
        for i in range(0, len(phrases), 6):
            res.update(page.evaluate(JS, phrases[i:i + 6]))
        browser.close()
    with open(out, "w") as f:
        json.dump(res, f, ensure_ascii=False, indent=1)
    for q, r in res.items():
        print(q, r["hits"], *r["links"][:8], sep="\n  ")


if __name__ == "__main__":
    main()
