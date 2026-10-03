# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at https://mozilla.org/MPL/2.0/.
"""Search the National Diet Library catalogue (NDL Search OpenSearch).

  ndl.py WORDS... [--count N]

Prints title, creator, date and link for each hit. The catalogue indexes the
OCR text of many digitised old books, so a term such as 三箇悪日 finds the
classical almanacs (簠簋内伝, 大雑書 …) that use it — the way to a printed,
public-domain source for a 暦注 (docs/sources/kyureki.md).
"""
import re
import sys
import urllib.parse
import urllib.request

URL = "https://ndlsearch.ndl.go.jp/api/opensearch?any={}&cnt={}"


def search(words, count=20):
    with urllib.request.urlopen(URL.format(urllib.parse.quote(" ".join(words)), count), timeout=60) as r:
        xml = r.read().decode()
    for item in re.findall(r"<item>(.*?)</item>", xml, re.S):
        def field(tag):
            m = re.search(rf"<{tag}[^>]*>([^<]*)<", item)
            return m.group(1) if m else ""
        yield field("title"), field("dc:creator"), field("dc:date"), field("link")


def main(argv):
    count = 20
    if "--count" in argv:
        i = argv.index("--count")
        count = int(argv[i + 1])
        argv = argv[:i] + argv[i + 2:]
    if not argv:
        sys.exit(__doc__)
    for row in search(argv, count):
        print(" | ".join(row))


if __name__ == "__main__":
    main(sys.argv[1:])
