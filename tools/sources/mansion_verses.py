# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at https://mozilla.org/MPL/2.0/.
"""Build docs/sources/mansion-verses.md from the OCR of WB img. 321–336.

  mansion_verses.py WORKDIR OUT.md

WORKDIR (docs/sources/wb/verses/, kept locally) holds:
  yigdzin/<img>.txt  Yigdzin-1's reading of each page (hf_read.py)
  choices.json       the flags between Yigdzin-1 and MITRA with agy's
                     answers (disagree.py --json, then agy_choose.py)
  judged.json        those answers judged: take agy's "other" reading,
                     keep Yigdzin-1's, take MITRA's, or leave open ⟨A|B⟩
  fixes.json         [old, new] text replacements: MITRA's lines where
                     Yigdzin-1 dropped a verse head beside a woodcut
  cuts.json          per verse, the phrase after which the quotation stops
  en/NN.md           the English of each verse, with @@TIB@@ where the
                     quotation goes; en/00-head.md the file's opening
The pages are joined, the decisions applied, the text split into the 28
verses at the ༈ that opens each, and each verse's quotation cut and set
into its English. Rerun after changing any input.
"""
import json
import re
import sys
from pathlib import Path

SYL = re.compile(r'[ༀ-༊ཀ-྾࿐-࿚]+')  # a run of letters and marks (not tsheg, shad or space)


def decide(votes, judged):
    rule = {k: name for name in ('take_other', 'keep_main', 'take_witness', 'open') for k in judged.get(name, [])}
    for f in votes:
        r = rule.get(f"{f['page']}-{f['n']}")
        if r == 'take_other':
            f.update({'witness': f['agy'][5:].strip(), 'agy': 'B'})
        elif r == 'keep_main':
            f['agy'] = 'A'
        elif r == 'take_witness':
            f['agy'] = 'B'
        elif r == 'open':
            f['agy'] = 'unclear'
    return votes


def apply_votes(page, text, votes):
    for f in votes:
        if f['page'] != str(page):
            continue
        ans = f.get('agy', '')
        if ans.upper() == 'A':
            continue
        toks = [(m.group(), m.start(), m.end()) for m in SYL.finditer(text)]
        syl = [t[0] for t in toks]
        before, main, after = ([s for s in f[k].split('་') if s] for k in ('before', 'main', 'after'))
        want = before + main + after
        hit = next((i for i in range(len(syl) - len(want) + 1) if syl[i:i + len(want)] == want), None)
        if hit is None or not main:
            print('not placed:', page, f['n'], f['main'], f['witness'], ans, file=sys.stderr)
            continue
        a, b = toks[hit + len(before)][1], toks[hit + len(before) + len(main) - 1][2]
        new = f['witness'] if ans.upper() == 'B' else f"⟨{f['main']}|{f['witness']}⟩"
        text = text[:a] + new + text[b:]
    return text


def main(argv):
    if len(argv) != 2:
        sys.exit(__doc__)
    w, out = Path(argv[0]), Path(argv[1])
    votes = decide(json.loads((w / 'choices.json').read_text()), json.loads((w / 'judged.json').read_text()))
    txt = ''
    for p in range(321, 337):
        txt += f'\n⟦p{p}⟧\n' + apply_votes(p, (w / f'yigdzin/{p}.txt').read_text().replace('༷', ''), votes)
    for old, new in json.loads((w / 'fixes.json').read_text()):
        if txt.count(old) != 1:
            print('fix not applied (already settled by a vote?):', old[:40], file=sys.stderr)
        txt = txt.replace(old, new)
    verses = []
    for part in re.split(r'༈\s*', txt)[2:]:
        body = re.sub(r'\s*\n\s*', '', re.sub(r'⟦p\d+⟧', '', part)).strip()
        verses.append(re.sub(r'\s+', ' ', body))
    cuts = json.loads((w / 'cuts.json').read_text())
    parts = [(w / 'en/00-head.md').read_text().rstrip() + '\n']
    for i, v in enumerate(verses, 1):
        en = w / f'en/{i:02d}.md'
        if not en.exists():
            continue
        cut = cuts.get(str(i), '')
        k = v.find(cut) if cut else -1
        quote = v[:k + len(cut)].strip() if k >= 0 else v
        if k < 0:
            print('cut not found, whole verse quoted:', i, file=sys.stderr)
        parts.append('\n' + en.read_text().replace('@@TIB@@', '> ' + quote).rstrip() + '\n')
    out.write_text(''.join(parts))
    print(f'{len(verses)} verses, {len(parts) - 1} written -> {out}')


if __name__ == '__main__':
    main(sys.argv[1:])
