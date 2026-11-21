# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at https://mozilla.org/MPL/2.0/.
"""Spell a White Beryl figure from its word-numerals and compare it with the printed glyphs.

  numwords.py spell «ཀླུ་ལག་འབྱུང་མེ»        the value the words spell
  numwords.py check FILE [FILE...]                 every «words(figure)» in the file: OK / NO / ??
  numwords.py key                                  the digit key

WB gives many figures twice: as glyphs in brackets and as a run of word-numerals
(sky 0, moon 1, ...). The run is read from its last word to its first, the last
word being the leftmost digit of the glyph figure: «ཀླུ་ལག་འབྱུང་མེ»
(naga 8, hand 2, element 5, fire 3) spells 3528. This is the check that settled
q1317-q1332 and the nine «victor» glosses of ch. 17 on 2026-11-21; it spells, it
does not judge: a NO is a lead (a blot misread, a slip of the print, or a key
entry still missing), settled by a second check (arithmetic, a repeat of the
figure, a printed divisor) as in those answers.

`check` takes the longest run of key words directly before each bracketed figure
and tries every suffix of it. OK: some suffix spells the figure. NO: none does;
the longest run's spelling is printed. ??: no key word stands before the figure
(a computed product, a figure across a page break, a figure without words).

The key is the pool's (docs/sources/claims/, the answers of q1317, q1319, q1326,
q1328, q1332). `yon` is 3 (four places of p. 196, ch. 3's «ཟླ་ཡོན ༣༡»); a place
where the scan shows 2 is a blot, not a second value.
"""
import re
import sys
from pathlib import Path

KEY = {}
for value, words in {
    0: 'མཁའ་ ཐིག་ སྟོང་ ཐིག་ལེ་ ནམ་མཁའ་',
    1: 'ཟླ་ གཟུགས་ བསེ་རུ་ ཟླ་བ་ ཤར་',
    2: 'མིག་ ལག་ ཟུང་ ལག་པ་',
    3: 'མེ་ ཡོན་ སྐྱེས་ ཡོན',
    4: 'ཆུ་ མཚོ་ རྒྱ་མཚོ་',
    5: 'འབྱུང་ མདའ་ དབང་ མདའ་',
    6: 'རོ་ དུས་ མཚམས་',
    7: 'རི་ ཐུབ་ དྲང་སྲོང་ གཟའ་',
    8: 'ཀླུ་ ནོར་',
    9: 'བུག་ རྩ་ སྲིན་',
    10: 'ཕྱོགས་',
    11: 'དྲག་',
    12: 'ཉི་ ཁྱིམ་',
    13: 'འདོད་',
    14: 'ཡིད་ མ་ནུ་',
    15: 'ཚེས་',
    16: 'མི་བདག་ རྒྱལ་པོ་',  # mi bdag: Tshig mdzod chen mo, Bod yig tshig gter (rd-an)
    18: 'སྐྱོན་',
    24: 'རྒྱལ་བ་',
    27: 'འཁོར་ལོ་ སྐར་མ་',
    32: 'སོ་',
}.items():
    for w in words.split():
        KEY[w.rstrip('་')] = value
# two-syllable keys are matched as one word; the longest first
MULTI = sorted((k for k in KEY if '་' in k), key=len, reverse=True)

DIGITS = str.maketrans('༠༡༢༣༤༥༦༧༨༩', '0123456789')
SYL = re.compile(r'[ཀ-྾]+')
FIG = re.compile(r'\(([༠-༩]+)\)')
PARTICLES = ('ས', 'ར', 'ི', 'འི', 'ཡི', 'ནས', 'ལ', 'དང', 'ན')


def words_of(text):
    """The key words of a run of syllables, each as (word, value); None for a syllable
    that is not one. Two-syllable keys are joined."""
    syl = SYL.findall(text)
    out = []
    i = 0
    while i < len(syl):
        if i + 1 < len(syl) and f'{syl[i]}་{syl[i + 1]}' in KEY:
            out.append((f'{syl[i]}་{syl[i + 1]}', KEY[f'{syl[i]}་{syl[i + 1]}']))
            i += 2
            continue
        s = syl[i]
        v = KEY.get(s)
        if v is None:
            for p in PARTICLES:
                if s.endswith(p) and s[:-len(p)] in KEY:
                    v = KEY[s[:-len(p)]]
                    break
        out.append((s, v))
        i += 1
    return out


def spell(words):
    """Digit string of a list of (word, value): last word first."""
    return ''.join(str(v) for _, v in reversed(words))


def trailing_run(text):
    run = []
    for w in reversed(words_of(text)):
        if w[1] is None:
            break
        run.append(w)
    run.reverse()
    return run


def check_text(text):
    results = []
    prev = 0
    for m in FIG.finditer(text):
        glyphs = m.group(1).translate(DIGITS)
        segment = text[max(prev, m.start() - 400):m.start()]
        prev = m.end()
        segment = re.split(r'[།\n]', segment)[-1]  # the run stands in the same clause
        run = trailing_run(segment)
        if not run:
            results.append(('??', m.group(1), '', ''))
            continue
        hit = None
        for k in range(1, len(run) + 1):
            if spell(run[-k:]) == glyphs:
                hit = k
        words = ' '.join(w for w, _ in run)
        if hit:
            results.append(('OK', m.group(1), words, spell(run[-hit:])))
        else:
            results.append(('NO', m.group(1), words, spell(run)))
    return results


def main(argv):
    if len(argv) >= 3 and argv[1] == 'spell':
        run = words_of(' '.join(argv[2:]))
        bad = [w for w, v in run if v is None]
        print(spell([w for w in run if w[1] is not None]), ('  (not in the key: ' + ' '.join(bad) + ')') if bad else '')
        return 0
    if len(argv) == 2 and argv[1] == 'key':
        for k, v in sorted(KEY.items(), key=lambda kv: (kv[1], kv[0])):
            print(v, k)
        return 0
    if len(argv) >= 3 and argv[1] == 'check':
        counts = {'OK': 0, 'NO': 0, '??': 0}
        for f in argv[2:]:
            for status, fig, words, spelled in check_text(Path(f).read_text(encoding='utf-8')):
                counts[status] += 1
                if status != 'OK':
                    print(f'{status}  ({fig})  {words}  ->  {spelled}')
        print(f'OK {counts["OK"]}, NO {counts["NO"]}, ?? {counts["??"]}')
        return 1 if counts['NO'] else 0
    print(__doc__)
    return 2


if __name__ == '__main__':
    sys.exit(main(sys.argv))
