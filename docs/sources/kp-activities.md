# The kun phan me long's other activity boxes

Sigla, the numbering of mansions and weekdays and the rules for quoting are
in [README.md](README.md); the boxes and their images are inventoried in
[kun-phan-me-long.md](kun-phan-me-long.md), the mansion abbreviations in
[mansions.md](mansions.md).

Henning translated thirteen of KP's activity boxes, which the app has had
since 1.0 (SPEC §5.10). The other boxes were read on 2026-10-04 and 05 and
are in `Electional.kt`, 50 lists in all. Each was read on the scan of KP (BDRC I4CZ65599, 2550 px,
enlarged two to four times where the print is small), with the Yigdzin-1
and MITRA readings of the folio (`tools/sources/hf_read.py`) and the same
box in KP2 (I3CN12074) beside it. Where the three disagree the scan decides,
and where the scan does not, the entry is left out. The transcription is
kept as data beside the scans on MONOLITH (`~/ai/tibetan-ocr/bench/kpact/`,
`kpdata.py`, with `gen.py`, which writes the Kotlin lists and the box
sections below from it).

**The rules** are those of SPEC §5.10, applied box by box:

- An entry the box names with a qualifier ("good for gates", "bad for
  roofing", "middling", "bad for funeral feasts"), or in brackets, counts
  for neither side. So does one named in both halves. Emphasis alone
  ("best", "very bad") does not qualify.
- What the app does not calculate is left out: rising signs, the yogas
  and *dus sbyor*, the nectar periods, the
  Chinese almanac's days (*pi ling*, *ki kang*, *se byi* …), Rāhu, the earth
  lords, Abhijit.
- Where a box says "otherwise as box N", it takes box N's lists in the kinds
  (weekdays, dates, mansions, day animals, trigrams) it names nothing in.
- A word or digit that cannot be read with confidence is left out and noted.

**The khrums and chu abbreviations.** ཆོད is chu stod (19), ཆུཾད chu smad
(20), ཁྲོད and ཁྲོུཾད khrums stod (24), ཁྲུཾད and ཁྲིད khrums smad (25), as in
[mansions.md](mansions.md). Where a box names all 27 mansions between its
halves, the one left over settles a doubtful abbreviation (box 3).

**What the app shows.** Each box is an activity in the lists of the weekday,
the lunar date, the mansion, the day animal and the trigram; most reuse a
wording the app already had (naming, sowing, consecration …), the rest add
sixteen wordings and two activities (care of horses, bringing rain).

## The boxes

Weekdays as Sun … Sat; mansions by their Tibetan names (nag pa = Citrā);
"days" are the day animals (the lunar date's, SPEC §5.8).

**The Tibetan of each box** (O10f, 2026-10-14) stands under its image line,
the good half and the bad half as the print has them, with the lists below
as its English. **Image numbers there are BDRC's own labels**, two more
than the "KP img." line above them. Read on KP (I4CZ65599, 2550 px) at 2.8×
in five overlapping strips a folio, with Yigdzin-1's readings of the half
folios (left and right 55 %, enlarged 1.5×) as the base, and every doubtful
word, numeral and bracket at 3× to 6×, against KP2 where KP is unclear. The
print's contractions are kept (ནབསོ nabs so, བྲཉེ bra nye, ལྷཾས lha mtshams,
བྱིཞི byi bzhin, that is Abhijit, གྲོཞི gro bzhin, ནུཾ nam gru, མོནྲེ, མོནྲུ
mon gre, mon gru, སག sa ga, ཐརྐ tha skar, མྱེ me bzhi, ཧལྱི hal khyi). "/" is a
line of the print, […] a word not legible, … a word left out unread, and
"(machine)" what was taken from Yigdzin-1 without being settled on the scan.
Where the scan reads otherwise than the lists, a bullet **The scan (O10f)**
says so; the lists above each bullet are as built before it, and the
bullets' readings were built on 2026-10-15 (ROADMAP T2.28, `Electional.kt`),
as was the table of *What the table should now carry* below.

### 2. Naming — མིང་འདོགས་པ་ལ་བཟང་བ

KP img. 21, KP2 img. 240.

The box on the scan, KP BDRC img. 23 (O10f):

- Good half: «༄། །གཟའ་ཉི་རྒྱལ་མིང། ཟླ་ཟློན་པོ། མིག་དམག་དཔོན། ལྷག་སྐྱེས་པ། ཕུར་བུ་དགེ་སློང་བྲམ་ཟེ། / སངས་བུད་མེད་རྐང་བཞི་རིན་པོ་ཆེ། སྤེན་ཡུལ་དང་མཁར། སྐར་མ་བྲཉེ་མགོ་རྒྱལ་ / ནབསོ་དབོ་ཆོད་ཆུཾད་ཁྲོུཾད་ནུཾ་ཐརྐ་སྨིན་རུང་ཙམ། སུ་སྦྱོར་འཁྲིག་ཉ་གཞུ་བུམ་པ་»
- Bad half: «༄། །སྐར་མ་གྲེ་ནག་ / སྐག་སྣྲོན་སྣྲུབས་ / དུས་སྦྱོར་སྲང་སྡིག་»

- **Good:** bra nye, mgo, rgyal, nabs so, dbo, chu stod, chu smad, khrums smad, nam gru, tha skar
- **Bad:** gre, nag pa, skag, snron, snrubs
- **Neither side:** every weekday, each for one kind of name (the Sun for a king's, the Moon a minister's, Mars a general's, Mercury a man's, Jupiter a monk's or brahmin's, Venus a woman's, a beast's or a jewel's, Saturn a land's or fort's); smin drug, only somewhat (KP རུང་ཙམ, KP2 ཅུང་ཙམ)
- **Not calculated:** rising signs
- The two chu and the khrums: ཆོད ཆུཾད in KP, read 19, 20 in the list's own order; KP2 has ཁྲུཾད (25) where KP's reading wavers.
- **The scan (O10f):** The good half's khrums reads ཁྲོུཾད (khrums stod) on KP at 2.8×, KP2 ཁྲུཾད (khrums smad): the waver noted above stays.

### 3. Cutting new clothes — གོས་གསར་དྲ་བ་ལ་བཟང་བ

KP img. 21, KP2 img. 240.

The box on the scan, KP BDRC img. 23 (O10f):

- Good half: «༄། །གཟའ་ཟླ་ལྷག་ཕུར་སངས་སྐར་མ་སྣར་ནབསོ་རྒྱལ་ / མྱེ་ས་རི་ས་ག་ལྷཾས་མོནྲེ་ནག་ཁྲུཾད་ཆུཾད་གྲོཞི་ཐརྐ་ / ཉི་མ་ཁྱི་དུས་སྦྱོར་སེངྒེ་བུ་མོ་ཉ་གླང་འཁྲིག»
- Bad half: «༄། །གཟའ་ཉི་སྤེན་མིག་ཚེས་༨ སྐར་མ་བྲཉེ་སྨིན་ / མགོ་ལག་སྐག་མཆུ་དབོ་སྣྲོན་སྣྲུབས་ཆོད་ / མོནྲུ་ནུཾ་ཁྲོུཾད་གྲེ་བྱིཞི་ཉི་མ་སྤྲེལ་དུས་སྦྱོར་ཀརྚ་སྡིག་ཆུ་སྲིན་»

- **Good:** Mon Wed Thu Fri; snar ma, nabs so, rgyal, me bzhi, sa ri, sa ga, lha mtshams, mon gre, nag pa, khrums smad, chu smad, gro bzhin, tha skar; days Dog
- **Bad:** Sun Sat Tue; dates 8; bra nye, smin drug, mgo, lag, skag, mchu, dbo, snron, snrubs, chu stod, mon gru, nam gru, khrums stod, gre; days Monkey
- **Not calculated:** rising signs; Abhijit (bad)
- The two halves name every mansion once; that fixes the bad ཁྲ as khrums stod (24), the one otherwise missing, as the scan shows.

### 5. Putting on new clothes — གོས་གསར་གྱོན་པ་ལ་བཟང་བ

KP img. 23, KP2 img. 242.

The box on the scan, KP BDRC img. 25 (O10f):

- Good half: «༄། །གཟའ་ལྷག་ཕུར་སངས་༼ཟླ༽ སྐར་མ་མྱེ་…མཆོག་སྣར་ / ནབསོ་རྒྱལ་ས་རི་ནག་ས་ག་གྲོཞི་ལྷཾས་ཆུཾད་མོནྲེ་ཁྲུཾད་ / ནུཾ་ཐརྐ་རྣཾས་སྐྱེད་སྲིད་༼[…]་སྲེད་༽ དུས་སྦྱོར་འཁྲིག་སེངྒེ་ [interlinear, machine: ལང་ཚོ་སྦྱོར་] གཞུ་གླང་»
- Bad half: «༄། །གཟའ་ཉི་མིག་སྤེན་ཚེས་༨ སྐར་མ་བྲཉེ་སྨིན་མགོ་ / ལག་སྐག་མཆུ་གྲེ་སྣྲོན་སྣྲུབས་ཆོད་དབོ་ཁྲོད་བྱིཞི་མོནྲུ་མྲིག་ / འདུ་མིང་རེག་ཚོར་ལེན་སྐྱེ་ཉི་མ་སྤྲེལ་དུས་སྦྱོར་ཀརྚ་སྡིག་འཕུང་བྱེད།»

- **Good:** Wed Thu Fri; me bzhi, snar ma, nabs so, rgyal, sa ri, nag pa, sa ga, gro bzhin, lha mtshams, chu smad, mon gre, khrums smad, nam gru, tha skar
- **Bad:** Sun Tue Sat; dates 8; bra nye, smin drug, mgo, lag, skag, mchu, gre, snron, snrubs, chu stod, dbo, khrums stod, mon gru; days Monkey
- **Neither side:** Monday (in brackets)
- **Not calculated:** rising signs; the twelve links (རྣམ་ཤེས, སྐྱེ, སྲིད, སྲེད good; མ་རིག, འདུ་བྱེད, མིང, རེག, ཚོར, ལེན, སྐྱེ bad); Abhijit (bad)
- me bzhi is named 'best' (མྱེ་མཆོག). The halves name every mansion once, chu smad and khrums smad good, chu stod and khrums stod bad.

### 7. Building walls and forts — མཁར་ལས་བརྩིག་བཟོ་ལ་བཟང་བ

KP img. 24, KP2 img. 243.

The box on the scan, KP BDRC img. 26 (O10f):

- Good half: «༄། །གཟའ་ལྷག་སངས་ཕུར་ཤིང་བཟོ་བ་བཟང་། ཐོག་འབུབས་ངན། ཚེས་༣ ༧ ༡༡ ༡༣ ༡༥ ༡༧ ༡༤ ༢༣ ༢༥ སྐར་མ་ཆོད་ / ནབསོ་མགོ་སྣྲུབས་སྒོ་འཛུགས་བཟང་། མྱེ་ཐོག་འབུབས་ངན། དབོ་གྲེ་ཁྲོུཾད་ཆུཾད་མོནྲེ་ནུཾ་སྣར་འདུ་བྱེད་ / མ་རིག་སྐྱེ་ཉི་མ་སྤྲེལ་ལྡུམ་ར་བཟང་། སྟག་གླང་ལུག་གིན་ཟིན་དུས་སྦྱོར་གླང་སེངྒེ་གཞུ་བུམ་པ་བུ་མོ་»
- Bad half: «༄། །གཟའ་ཉི་རྒྱལ་མཁར་བཟང་། ཟླ་ལྷ་ཁང་བཟང་། སྤེན་ས་ལས་བཟང་། མིག་ཚེས་༡༠ ༢༠ ༣༠ ༡༨ ༢༢ ༦ ༡༦ ༢༦ སྐར་མ་བྲཉེ་ / རྒྱལ་རྨང་འདིང་བཟང་། སྐག་ཁྲུཾད་ལྷཾས་སྨིན་ས་རི་སྦྲ་ཁྱིམ་འབུབས་བཟང་། མཆུ་གྲོཞི་མོནྲུ་ས་ག་སྣྲོན་ཐརྐ་ནག་ཉི་མ་རྟ་ཐོག་ཕུབ་ངན། བྱི་སྒོ་ / འཛུགས་བཟང་། ཡོས་འབྲུག་ཁྱི་ཕག་སྦྲུལ་ཁོན་ཁཾ་ཁེན་༩ དུས་སྦྱོར་ཀརྚ་ཆུ་སྲིན་སྲང་ལུག་སྡིག་འབྲུག་[རྔེ]་སོགས་ཁང་གནས་ས་བདག་ནང་དུ་སྤངས།»

- **Good:** Wed Fri; dates 3, 7, 11, 13, 15, 17, 14, 23, 25; chu stod, nabs so, mgo, dbo, gre, khrums stod, chu smad, mon gre, nam gru, snar ma; days Tiger, Ox, Sheep; trigrams gin, zin
- **Bad:** Tue; dates 10, 20, 30, 18, 22, 6, 16, 26; bra nye, skag, khrums smad, lha mtshams, smin drug, mchu, gro bzhin, mon gru, sa ga, snron, tha skar, nag pa; days Hare, Dragon, Dog, Pig, Snake; trigrams khon, kham, khen
- **Neither side:** Thursday (good for woodwork, bad for roofing); snrubs (good for setting up gates); me bzhi (bad for roofing); the Monkey day (good for gardens); among the bad, the Sun (good for royal forts), the Moon (for temples), Saturn (for earthwork), rgyal (for laying foundations), sa ri (for roofing tents and houses), the Horse day (bad for roofing), the Mouse day (good for doorways)
- **Not calculated:** rising signs; the twelve links (འདུ་བྱེད, མ་རིག, སྐྱེ good); the earth lords
- Lag is not named. The 14th among the good dates is printed ༡༤ in both prints, out of their order. The khrums are ཁྲོུཾད (good) and ཁྲུཾད (bad) in both prints.
- **The scan (O10f):** The bad half ends with the Dragon day's earth lord and the earth lords of the house, «ཁང་གནས་ས་བདག», to be kept out.

### 9. Setting up hearth and pillars — ཐབ་ཀ་བཅའ་བ་ལ་བཟང་བ

KP img. 25, KP2 img. 244.

The box on the scan, KP BDRC img. 27 (O10f):

- Good half: «༄། །གཟའ་ཉི་ཟླ་ཕུར་སངས་སྤེན་ཚེས་༢ ༧ ༨ ༡༢ / ༡༣ ༡༧ ༡༨ ༢༢ ༢༣ ༢༧ སྐར་མ་སྨིན་མགོ་རྒྱལ་ / གྲེ་མྱེ་དབོ་ཁྲོུཾད་ཐརྐ་ཆུཾད་ཐབ་འབྲིང་། དུས་སྦྱོར་ / བུམ་པ་སེངྒེ་ཉ་»
- Bad half: «༄། །གཟའ་མིག་ལྷག། གོང་དུ་མ་འཁོད་ཚེས་རྣམས་བཏང་སྙོམས་ཡིན། / སྐར་མ་ལག་མཆུ་སྣྲུབས་ཆོད་སྐག་ཁྲུཾད་མོནྲེ་ནུཾ་ / བྱིཞི་ལྷཾས་ནབསོ་ཆུཾད་སྒྱེད་བུ་བཟང་། བྲཉེ་སྒྱེད་བུ་དང་ཀ་བ་འཛུགས་ / པ་བཟང་། ཉི་མ་ཁྱི་གླང་མགར་ཐབ་བཟང་། འབྲུག་རྗེ་[…]་གནས་དུས་འཛེམ།»

- **Good:** Sun Mon Thu Fri Sat; dates 2, 7, 8, 12, 13, 17, 18, 22, 23, 27; smin drug, mgo, rgyal, gre, me bzhi, dbo, khrums stod, tha skar
- **Bad:** Tue Wed; lag, mchu, snrubs, chu stod, skag, khrums smad, mon gre, nam gru
- **Neither side:** chu smad (middling for the hearth); among the bad, lha mtshams, nabs so and chu smad (good for the hearth stones), bra nye (good for the hearth stones and pillars), the Dog and Ox days (good for a smith's hearth)
- **Not calculated:** rising signs; Abhijit; the Dragon day's earth lord
- The box adds that dates it does not name are neutral. KP2's reading drops the 23rd; KP has it.

### 10. Feasts, using vessels and household goods — སྟོན་མོ་ལ་སོགས་ཡོ་ལང་བྱེད་པ་ལ་བཟང་བ

KP img. 26, KP2 img. 245.

The box on the scan, KP BDRC img. 28 (O10f):

- Good half: «༄། །གཟའ་སངས་ཕུར་ཉི་ཟླ་གསོན་སྟོན་མཆོག་ཤིད་སྟོན་ངན། / ལྷག་རུང་ཙམ། སྐར་མ་དབོ་སྣྲུབས་ནུཾ་ནབསོ་བྱིཞི་ / མོནྲེ་ས་རི་མྱེ་མགོ་སྨིན་ཤིད་སྟོན་ངན། / མ་རིག་རྣམ་ཤེས་ཚོར་བ་སྲེད་པ་ / ཉི་མ་ཁྱི་ཕག་[…]་སེར་ཆོས་སྟོན་བཟང་།»
- Bad half: «༄། །གཟའ་མིག་སྤེན་ཚེས་༩ ༡༩ ༢༩ ༡ ༡༣ ༡༠ ༡༡ ༢༠ ༢༡ ༣༠ / སྐར་མ་སྣར་རྒྱལ་ས་ག་མཆུ་གྲེ་བྲཉེ་ཁྲུཾད་ཆོད་ཤིད་སྟོན་བཟང་། ལྷཾས་ཆོས་ / སྟོན་བཟང་། གྲོཞི་སྐྱིད་སྟོན་བཟང་། ཉི་མ་བྱ་ཡོས་ཀིང་ཟོར་ནག་དབུལ་ཧལྱི་ / པི་ལིང་གནམ་ཞག་ནག་ཐེ་སེ་ཙང་ཀུན་ཧང་ཕན་སེ་བྱི་སེ་ཤར་ཀུངས་མྱུལ་ / ས་རྒྱལ་འབྲུག་ལྷ་ལྷ་ལྔ་བར་གནམ་དྲ་ཆེན་རཱ་ཧུ་རྒྱུ་དུས་གཟའ་རྒོད་སོགས།»

- **Good:** Fri Thu Sun; dbo, snrubs, nam gru, nabs so, mon gre, sa ri, me bzhi, mgo; days Dog
- **Bad:** Tue Sat; dates 9, 19, 29, 1, 13, 10, 11, 20, 21, 30; snar ma, rgyal, sa ga, mchu, gre, bra nye, khrums smad; days Bird, Hare
- **Neither side:** the Moon, after which the weekdays are called best for feasts of the living and bad for funeral feasts; Wednesday (acceptable); smin drug (bad for funeral feasts); the Pig day (good for feasts of the dharma); among the bad, chu stod (good for funeral feasts), lha mtshams (good for feasts of the dharma), gro bzhin (good for happy feasts)
- **Not calculated:** Abhijit; the twelve links (མ་རིག, རྣམ་ཤེས, ཚོར་བ, སྲེད་པ); the Chinese almanac's days (pi ling, gnam zhag nag, the se …), Rāhu's course and the like
- The bad dates include ༡༣, as printed.

### 11. Preparing food, brewing beer — ཟས་སྦྱོར་ཆང་བཙོ་བ་ལ་བཟང་བ

KP img. 26, KP2 img. 245. Otherwise as box 10.

The box on the scan, KP BDRC img. 28 (O10f):

- Good half: «༄། །གཟའ་ཟླ་ཕུར་སངས་ཚེས་༤ / སྐར་མ་བྱིཞི་ཁྲུཾད་ཉི་མ་ལུག»
- Bad half: «༄། །གཟའ་ལྷག་ཚེས་༢༩ དེ་ལས་གཞན་ / མ་སྟོན་མོ་ཡོ་ལང་དང་མཚུངས་སོ། །»

- **Good:** Mon Thu Fri; dates 4; khrums smad; days Sheep
- **Bad:** Wed; dates 29
- **Not calculated:** Abhijit (good)
- «དེ་ལས་གཞན་མ་སྟོན་མོ་ཡོ་ལང་དང་མཚུངས་སོ»: otherwise as box 10, whose lists it takes where it names nothing of its own.

### 15. Trade and measuring grain — ཚོང་དང་འབྲུའི་འཇལ་ལེན་ལ་བཟང་བ

KP img. 30, KP2 img. 249.

The box on the scan, KP BDRC img. 32 (O10f):

- Good half: «༄། །གཟའ་ལྷག་ཕུར་སངས་༼ཉི་ཟླ༽ཚེས་༡ ༢ ༣ […] ༣༠ འདི་རྣམས་ལ་འཚོང་བ་ངན། སྐར་མ་མོནྲུ་ཐརྐ་ནུཾ་ / མགོ་ནབསོ་སྣར་ཁྲོུཾད་སྣྲུབས་ཆུཾད་ས་རི་གྲོཞི་བྱིཞི་སྐག་གྲེ་ཁྲུཾད་…གཞིས་དང་ཕྱུགས་ཉོ་བཟང་། མཆུ་རྟ་ཕྱུགས་ཉོ་བཟང་། ཉི་མ་ལུག་ / གཏོང་བཟང་། བྱ་བྱི་དུས་སྦྱོར་སྲང་སྡིག་བུ་མོ་གླང་འཁྲིག་སེངྒེ་དགེ་བ་ཆ[བས]་བ་དངོས་གྲུབ་སྦྱོར་བར་…ཞིང་ཉོ་བཟང་།»
- Bad half: «༄། །གཟའ་མིག་སྤེན་ཚེས་༤ ༥ ༦ ༧ ༨ ༩ ༡༠ ༡༡ [blot] སྐར་མ་མྱེ་རྒྱལ་མོནྲེ་ས་ག་ལག་དུས་སྦྱོར་ལུག་གིན་༩ ཉི་མ་གླང་སྦྲུལ་རང་རང་ཉི་མར་ངན། [interlinear under the blot and the mansions, not read: «…བཟང་།», «ཞིང་ཉོ་…།», «…ས་ཉེ་…།»]»

- **Good:** Wed Thu Fri; mon gru, tha skar, nam gru, mgo, nabs so, snar ma, khrums stod, snrubs, chu smad, sa ri, gro bzhin, skag, gre; days Bird, Mouse
- **Bad:** Tue Sat; dates 4, 5, 6, 7, 8, 9, 10, 11; me bzhi, rgyal, mon gre, sa ga, lag; trigrams gin
- **Neither side:** the Sun and the Moon (in brackets); all the good dates, from the 1st to the 30th (partly illegible), of which the box says «འདི་རྣམས་ལ་འཚོང་བ་ངན» (these are bad for selling); khrums smad (good for buying estates and livestock); mchu (for buying horses and livestock); the Sheep day (for giving livestock away); among the bad, the Ox and Snake days ('each bad on its own day')
- **Not calculated:** rising signs; the yogas dge ba and chabs (?) and the grub sbyor (good for buying fields); the sme ba nine; Abhijit (good); a verse on the grain measure counted from the Sun's mansion (box 14)
- The bad dates run on after the 11th under a blot and an interlinear note in both prints; only 4–11 are legible. The good list's first khrums is ཁྲོད in KP2 (24), its last ཁྲུཾད (25).

### 15b. Giving and taking loans — ཞར་བྱུང་བུ་ལོན་གཏོང་ལེན་ལ་བཟང་བ

KP img. 31, KP2 img. 250.

The box on the scan, KP BDRC img. 33 (O10f):

- Good half: «༄། །གཟའ་མིག་ལྷག / སྐར་མ་སྣར་ལག / སྐག་ནབསོ་སྣྲོན་སྙེགས་ལ་ / བཟང་། ཉི་མ་སྦྲུལ་»
- Bad half: «༄། །གཟའ་སངས་ / ཚེས་༡༥ སྐར་མ་ས་རི་ས་ག / ལྷཾས་སྣྲུབས་གྲོཞི་གནམ་ / [སྟོང]་ཀི་ཀང་»

- **Good:** Tue Wed; snar ma, lag, skag, nabs so; days Snake
- **Bad:** Fri; dates 15; sa ri, sa ga, lha mtshams, snrubs, gro bzhin
- **Neither side:** snron (good for collecting debts)
- **Not calculated:** the Chinese almanac's days (gnam stong, ki kang …)
- A sub-box of 15 under its own heading, 'by the way'.

### 16a. Receiving wealth — ནོར་ཅ་གཏོང་ལེན་ལ་བཟང་བ (ལེན)

KP img. 31, KP2 img. 250.

The box on the scan, KP BDRC img. 33 (O10f):

- Good half: «༄། །གཟའ་ཉི་སྤེན་མིག་ལྷག་གཏོང་ངན་ལེན་བཟང་། ཕུར་…ཕྱུགས་གཏོང་ངན། ཚེས་༣ ༤ / ༥ ༡༤ ༡༥ ༢༡ ༢༥ སྐར་མ་སྣར་རྒྱལ་མཆུ་གྲེ་སྣྲུབས་ཆོད་མགོ་ / མོནྲུ་ཁྲུཾད་ནུཾ་སྨིན་ནབསོ་སྣྲོན་ཁྲོད་བྲཉེ་འདི་རྣམས་གཏོང་ངན་ལེན་བཟང་། / ལག་ཕྱུགས་…ལ་མིན་པ་བཟང་། མ་རིག་སྲིད་པ་ཚོར་བ་༩ …ཚུར་ལེན་བཟང་།»
- Bad half: «༄། །གཟའ་ཟླ་སངས་ཚེས་༡ ༦ ༧ ༨ ༩ ༡༠ ༡༡ ༡༢ ༡༣ ༡༤ / ༡༦ ༡༧ ༡༨ ༢༠ ༢༦ སྐར་མ་མྱེ་དབོ་ནག་མོནྲེ་ཉི་མ་སྦྲུལ་དབུལ་ / ཉི་ནག་ཀི་ཀང་སེ་བྱི་ཧང་ཕན་ཟླ་ཀླུང་ཟླ་ཚེས་ཟླ་ཚེས་ཟླ་ཚེས་…འདི་[…]། [small numerals under the three ཟླ་ཚེས, not read with confidence: the first two look like ༡༡ ༢༧ and ༡༢ ༢༥, the third ༡ and a worn digit]»
- The giving half, 16b, is the same box.

- **Good:** Sun Sat Tue Wed; dates 3, 4, 5, 15, 21, 25; snar ma, rgyal, mchu, gre, snrubs, chu stod, mgo, mon gru, khrums smad, nam gru, smin drug, nabs so, snron, khrums stod, bra nye
- **Bad:** Mon Fri; dates 1, 6, 7, 8, 9, 10, 11, 12, 13, 16, 17, 18, 20, 26; me bzhi, dbo, nag pa, mon gre; days Snake
- **Neither side:** the 14th (in both halves); lag (good, but not for livestock)
- **Not calculated:** the twelve links (མ་རིག, སྲིད་པ, ཚོར་བ, 'good for taking in'); the Chinese almanac's days (dbul, nyi nag, ki kang, se byi, hang phan, zla klung) and dates of particular months
- Box 16 is split in two: its good weekdays and mansions are «གཏོང་ངན་ལེན་བཟང» (bad for giving, good for taking), the mansions explicitly «འདི་རྣམས» (these). The bad half and the plain good dates hold for both.

### 16b. Giving wealth away — ནོར་ཅ་གཏོང་ལེན་ལ་བཟང་བ (གཏོང)

KP img. 31, KP2 img. 250.

- **Good:** dates 3, 4, 5, 15, 21, 25
- **Bad:** Sun Sat Tue Wed Mon Fri; dates 1, 6, 7, 8, 9, 10, 11, 12, 13, 16, 17, 18, 20, 26; snar ma, rgyal, mchu, gre, snrubs, chu stod, mgo, mon gru, khrums smad, nam gru, smin drug, nabs so, snron, khrums stod, bra nye, me bzhi, dbo, nag pa, mon gre; days Snake
- **Neither side:** Thursday (bad for giving livestock); the 14th (in both halves); lag (good, but not for livestock)
- The giving half of box 16; see 16a.

### 17. Auspicious and virtuous rites — བཀྲ་ཤིས་དགེ་ལེགས་བྱེད་པ་ལ་བཟང་བ

KP img. 32, KP2 img. 251.

The box on the scan, KP BDRC img. 34 (O10f):

- Verse: «། །གཡང་སྐར་སོ་སོའི་ངོས་འཛིན་ནི། །སྨིན་དྲུག་བུད་མེད་སྣར་མ་གོས། །བོད་ཕྱུགས་མགོ་ལག་སྐྱེས་པ་དང་། །ནབས་སོ་རྒྱལ་ནི་མིའི་གཡང་སྐར། །རྒྱལ་ནི་རྟ་བོང་ལུག་གི་འང་ / གཡང་། །གྲེ་ནི་བོད་བུའི་དབོ་ལུག་གི། །མེ་བཞི་བྲ་ཉེ་གཉིས་པོར། །ཕག་[བགྲ]་ནག་ས་རི་དང་། །ས་ག་ས་ཞིང་ལྷ་མཚམས་ནི། །[བྲས]་ནོར་སྤྱིའི་ཡིན་སྣྲུབས་རྟ་ཡི། །ཆུ་སྟོད་ཆུ་སྨད་ནས་ / ཀྱི་གཡང་། །བྱི་བཞིན་བྲན་སྐར་མོན་དྲེ་དང་། །མོན་གྲུ་རྟ་བོང་ནམ་གྲུ་གཡུ་ཡི། །གཡང་གི་སྐར་མ་འདི་དག་ལ། །རང་རང་གཏོང་ངན་ལེན་འགུགས་བཟང་། །»
- Good half: «༄། །གཟའ་ཉི་ཟླ་ལྷག་སངས་ཕུར་སྐར་མ་ / ཐརྐ་སྨིན་མགོ་དབོ་མྱེ་ནག་ས་རི་སྣྲོན་ཆོད་གྲོཞི་ / ནུཾ་ཆུཾད་མོནྲུ་དུས་སྦྱོར་གླང་སེངྒེ་གཞུ་བུམ་པ་བུ་མོ་སེལ་བ་ / མཛའོ་ཏི་ལ་བརྡུང་མི་སྡུག་མིང་༨ བདུད་རྩི་ཐུན་མཚམས།»
- Bad half: «༄། །གཟའ་མིག་སྤེན་སྐར་མ་བྲཉེ་ལག་སྐག་སྣྲུབས་ / བྱིཞི་དུས་སྦྱོར་ཀརྚ་སྡིག་ཆུ་སྲིན་སྲང་ལུག་ལྷ་ལྔ་གནས་ཕྱོགས་ / བིཥྚི་ཀི་ཀང་རཱ་ཧུ་པི་ལིང་དབུལ་ཧལྱི་ལོ་ཟླ་ཚེས་ / སོགས་ནག་པའི་དུས་ཕུང་ཟོར་སོགས།»

- **Good:** Sun Mon Wed Fri Thu; tha skar, smin drug, mgo, dbo, me bzhi, nag pa, sa ri, snron, chu stod, gro bzhin, nam gru, chu smad, mon gru
- **Bad:** Tue Sat; bra nye, lag, skag, snrubs
- **Not calculated:** rising signs; the yogas sel ba and mdza' bo and the other good times of the third line (the nectar periods); Abhijit (bad); the karaṇa Viṣṭi, ki kang, Rāhu, pi ling, the Dog year, the five gods' places and the like (bad)
- Before the table, a verse names the prosperity mansion (གཡང་སྐར) of each kind of wealth (smin drug of women, snar ma of clothes, mgo and lag of men, nabs so and rgyal of people …): on them giving one's own kind away is bad, calling it in good. The two chu of the good list are ཆོད and ཆུཾད (19, 20).
- **The scan (O10f):** The *Not calculated* list's "the Dog year" is «ཧལྱི་ལོ་ཟླ་ཚེས», the *hal khyi* of year, month and date (as the RD5 section reads it).

### 18. Planting trees and flowers — སྐྱེ་ཤིང་མེ་ཏོག་འདེབས་པ་ལ་བཟང་བ

KP img. 33, KP2 img. 252.

The box on the scan, KP BDRC img. 35 (O10f):

- Good half: «༄། །གཟའ་ཟླ་ལྷག་ཕུར་སྤེན་༼སངས༽ སྐར་མ་མགོ་ / ལག་རྒྱལ་ཁྲུཾད་ཆོད་ནུཾ་མོནྲུ་སྣྲོན་ས་རི་སྐག་ཆུཾད་ཁྲོུཾད་ཉི་མ་ / བྱ་སྤྲེལ་ལི་ཟིན་དུས་སྦྱོར་གླང་འཁྲིག་ཉ་བུམ་པ་ / ཆུ་སྲིན།»
- Bad half: «༄། །གཟའ་མིག ཉི་ཤིང་[…]་བཟང་སྟེ་མེ་ཏོག་ / འདེབས་པ་ངན། སྐར་མ་བྲཉེ་སྨིན་མཆུ་མྱེ་ / དབོ་སག་ཁོན་དུས་སྦྱོར་ལུག་སེངྒེ་ / སྡིག་བུ་མོ་སྲང་སེ་བདུད་རྒྱུ་དུས་ཉི་ནག»

- **Good:** Mon Wed Thu Sat; mgo, lag, rgyal, chu stod, nam gru, mon gru, snron, sa ri, skag, chu smad, khrums stod; days Bird, Monkey; trigrams li, zin
- **Bad:** Tue; bra nye, smin drug, mchu, me bzhi, dbo, sa ga
- **Neither side:** Friday (in brackets); the Sun (good for planting trees, bad for flowers); khrums smad, named good (ཁྲུཾད) and bad (ཁྲིད, as in the new-home box)
- **Not calculated:** rising signs; the Chinese almanac's days (se bdud, nyi nag)
- **The scan (O10f) reads otherwise:** the bad half names no khrums. The word read ཁྲིད (khrums smad) on 2026-10-04 is «ཁོན», the trigram khon, in KP and KP2 (BDRC img. 35, KP2 img. 254, at 6×). So khrums smad (ཁྲུཾད) is named only in the good half and counts good, not neither; and the trigram khon is bad.

### 19. Making peace between enemies — འགྲས་སྡུམས་བྱེད་པ་ལ་བཟང་བ

KP img. 33, KP2 img. 252.

The box on the scan, KP BDRC img. 35 (O10f):

- Good half: «༄། །གཟའ་ཟླ་ལྷག་ཕུར་སངས་མགྲོན་འགྲོ་ངན། / ཚེས་༨ སྐར་མ་ས་རི་ཁྲོུཾད་དབོ་ཆུཾད་གྲོཞི་ / བྱིཞི་མྱེ་ལྷཾས་ཐརྐ་མོནྲུ་རྒྱལ་ཉི་མ་ཁྱི་གླང་དུས་སྦྱོར་ / ལུག་སེངྒེ་བུ་མོ་ཉ་བདུད་རྩི་ཐུན་མཚམས་སོ། །»
- Bad half: «༄། །གཟའ་ཉི་མིག་སྤེན་ཚེས་༤ སྐར་མ་མགོ་ / ལག་སྐག་མཆུ་ནག་ཆོད་སྣྲོན་སག་ནུཾ་ཁྲུཾད་ / ཉི་མ་རྟ་དུས་སྦྱོར་ཀརྚ་ཆུ་སྲིན་སྡིག་བུམ་སྟོང་སེ་བྱི་ / ཞག་ནག་འཕུང་བྱེད།»

- **Good:** Mon Wed Thu; dates 8; sa ri, khrums stod, dbo, chu smad, gro bzhin, me bzhi, lha mtshams, tha skar, mon gru, rgyal; days Dog, Ox
- **Bad:** Sun Tue Sat; dates 4; mgo, lag, skag, mchu, nag pa, chu stod, snron, sa ga, nam gru, khrums smad; days Horse
- **Neither side:** Friday (bad for going as a guest)
- **Not calculated:** rising signs; the nectar periods; Abhijit (good); zhag nag and 'phung byed (bad)
- KP2 heads the box འགྲས.

### 20. Virtuous acts for the living — གསོན་དགེའི་ལས་ལ་བཟང་བ

KP img. 34, KP2 img. 253.

The box on the scan, KP BDRC img. 36 (O10f):

- Good half: «༄། །གཟའ་ལྷག་ཕུར་སངས་༼ཉི་ཟླ༽ ཚེས་༡ ༡༨ སྐར་མ་སྣར་མགོ་ / ནབསོ་རྒྱལ་མོནྲུ་དབོ་ལག་མྱེ་ནག་ས་རི་སྣྲོན་བྱིཞི་མོནྲེ་ཁྲོུཾད་གྲེ་ / ནུཾ་ལྷཾས་སྣྲུབས་མཆུ་སྐྱེད་མ་རིག་རྣཾས་ཚོར་སྲིད་སྲེད་ཉི་མ་འབྲུག་དུས་སྦྱོར་ / གླང་སེངྒེ་བུམ་པ་གཞུ་བུ་མོ་ཁེན་འཕེལ་འགྱུར་སྦྱོར་སོགས།»
- Bad half: «༄། །གཟའ་མིག་སྤེན་སྐར་མ་བྲཉེ་སྐག་ཁྲིད་གྲོཞི་ / ངན། རྒ་ཤི་ཉི་མ་བྱ་དུས་སྦྱོར་ལུག་ཆུ་སྲིན་སྲང་སྡིག་ / བིཥྚི་ཀི་ཀང་པི་ལིང་ཉི་ནག་ཕུང་ཟོར་ཧལྱི་དྲ་ / ཆེན་རཱ་ཧུ་དབུལ་ལག་ལོ་ཟླ་ཞག་ནག་སོགས།»

- **Good:** Wed Thu Fri; dates 1, 18; snar ma, mgo, nabs so, rgyal, mon gru, dbo, lag, me bzhi, nag pa, sa ri, snron, mon gre, khrums stod, gre, nam gru, lha mtshams, snrubs, mchu; days Dragon; trigrams khen
- **Bad:** Tue Sat; bra nye, skag, khrums smad, gro bzhin; days Bird
- **Neither side:** the Sun and the Moon (in brackets)
- **Not calculated:** Abhijit (good); the twelve links (སྐྱེ, མ་རིག, རྣམ་ཤེས, ཚོར, སྲིད, སྲེད good; རྒ་ཤི bad); rising signs; the 'phel 'gyur combination and the like (good); Viṣṭi, ki kang, pi ling, nyi nag, phung zor and the like (bad)
- KP2 brackets lha mtshams; KP does not, and the app follows KP. The bad khrums is ཁྲིད, khrums smad, as in box 18.
- **The scan (O10f):** The bad half's ཁྲིད is seen on the scan; box 18's is not a khrums (see there).

### 21. Manuring, opening the soil, breaking in oxen — ས་ལུད་ཁ་འབྱེད་གླང་འདུལ་ལ་བཟང་བ

KP img. 34, KP2 img. 253.

The box on the scan, KP BDRC img. 36 (O10f):

- Good half: «༄། །གཟའ་ཟླ་ལྷག་སངས་སྤེན་ཕུར་ས་དབྱེ་འབྲིང་། ཚེས་༡ / ༦ ༡༦ ༡༤ ༢༥ ༢༦ སྐར་མ་ནབསོ་མོནྲུ་[…][interlinear: ས་ལུད་འབྲིང་།] / སྣར་ནུཾ་ས་ག་མོནྲེ་ཆོད་མགོ་མྱེ་ས་རི་ / རྒྱལ་བྱིཞི་གྲོཞི་གླང་འདུལ་བཟང་། ཐརྐ་ས་དབྱེ་ངན། གིན་དུས་སྦྱོར་སྲང་གཞུ་བུམ་ཉ་»
- Bad half: «༄། །གཟའ་ཉི་མིག་ཚེས་༡༡ ༢༢ ༢༤ གླང་འདུལ་བ་ལ་ངན། སྐར་མ་ / སྐག་བྲཉེ་སྨིན་དབོ་མཆུ་གྲེ་ལྷཾས་ཉི་མ་ཡོས་སྦྲུལ་ཕག་བྱི་ / ལམ་ལྡོ་པི་ལིང་དབུལ་ཉི་ལུས་དུར་ཞུགས་དང་ལྔ་སེར་འཆར་ཕྱོགས། / ཕོ་ཉ་ཟས་འཚོལ་ཞག་ནག་ས་ཡི་ལྷ་མོ་བསྟན་མ་གནས་ཕྱོགས།»

- **Good:** Mon Wed Fri Sat; dates 1, 4, 14, 19, 25; nabs so, mon gru, snar ma, nam gru, sa ga, mon gre, chu stod, mgo, me bzhi, sa ri, rgyal; trigrams gin
- **Bad:** Sun Tue; dates 11, 22; skag, bra nye, smin drug, dbo, mchu, gre, lha mtshams; days Hare, Snake, Pig
- **Neither side:** Thursday (middling for opening the soil); one mansion after mon gru, not legible (middling for manuring); gro bzhin (good for breaking in oxen); tha skar (bad for opening the soil); the 29th, good and also bad for breaking in oxen
- **Not calculated:** rising signs; Abhijit (good); the Chinese almanac's days and the earth goddess's place (bad)
- **The scan (O10f) reads otherwise:** the good dates are «༡ ༦ ༡༦ ༡༤ ༢༥ ༢༦», 1, 6, 16, 14, 25, 26 (the 6 is box 16's 6 of the run 6 7 8 9 10, BDRC img. 33; the 4 the open hook with a flat head of box 22's bad 4, 8, 14, 28, img. 38, not the looped head of box 16's 9), not 1, 4, 14, 19, 25; and the bad «༡༡ ༢༢ ༢༤», 11, 22, 24, stand before «གླང་འདུལ་བ་ལ་ངན» (bad for breaking in oxen). No 29 is in the box. Whether «bad for breaking in oxen» holds for the three dates alone or for the whole line is not settled. The «བྱི» closing the bad half's second line runs on into «ལམ་ལྡོ»: the mouse road, not a Mouse day. WB's own work (vol. 2, p. 390, img. 398, ch. 34 no. 9, read on the scan) has «ཚེས་གཅིག་དྲུག་དང་བཅུ་གཅིག་དང་། །བཅུ་དྲུག་བཅུ་དགུ་ཉི་ཤུ་ལྔ། །ཉེར་དྲུག་རྣམས་ལ་བཟང་པོ་ཡིན། །བཅུ་གཅིག་ཉེར་གཉིས་ཉེར་དགུ་ལ། །གླང་འདུལ་བྱེད་པར་ངན་པ་ཡིན།», 19 and 29 where KP's carving has the shape of its own 4 (box 16's ༡༤, not its ༩): the box and the chapter differ in these two digits, and in WB too the three bad dates are qualified, «bad for breaking in oxen».

### 22. Sowing the fields — ཞིང་ལས་ས་བོན་འདེབས་པ་ལ་བཟང་བ

KP img. 36, KP2 img. 257.

The box on the scan, KP BDRC img. 38 (O10f):

- Good half: «༄། །གཟའ་སྤེན་ཟླ་ལྷག་ཕུར་སངས་ཚེས་༢ ༡༠ ༡༢ ༡༥ ༡༦ / ༡༧ ༢༨ ༢༡ ༡༤ ༢༢ ༢༣ ༢༤ ༢༥ ༢༦ ༢༧ ༣༠ སྐར་མ་སྣར་ནབསོ་ནུཾ་མགོ་ / མཆུ་མྱེ་ནག་སྣྲོན་མོནྲེ་མོནྲུ་ས་རི་ལག་གྲོཞི་སྣྲུབས་ཆོད་ཆུཾད་ལྷཾས་ / ཁྲུཾད་ཞིང་རྨོ་ངན། སོན་འདེབས་བཟང་། ཚོར་བ་ཉི་མ་ལུག་ཁྱི་གླང་ / [ཁྱིམ་སྐྱེས] དུས་སྦྱོར་ཉ་བུམ་གླང་སྲང་ཆུ་སྲིན་[བྱིཞི]།»
- Bad half: «༄། །གཟའ་ཉི་མིག་ཚེས་༤ ༨ ༡༤ ༢༨ / ༢༩ སྐར་མ་བྲཉེ་སྨིན་རྒྱལ་སག་ / དབོ་ཁྲོུཾད་ཉི་མ་ཕག་ཡོས་འབྲུག་ / སྦྲུལ་དུས་སྦྱོར་ལུག་ཀརྚ་སྡིག་ཉི་ནག་ཐེ་སེ་ / [ཁྱིམ་…]་གྲུམ་བུ་ས་རྒྱལ་འབྲུག་ལྷ་འཕུང་བྱེད།»

- **Good:** Sat Mon Wed Thu Fri; dates 2, 10, 12, 15, 16, 17, 21, 22, 23, 24, 25, 26, 27, 30; snar ma, nabs so, nam gru, mgo, mchu, me bzhi, nag pa, snron, mon gre, mon gru, sa ri, lag, gro bzhin, snrubs, chu stod, chu smad, lha mtshams; days Sheep, Dog, Ox
- **Bad:** Sun Tue; dates 4, 8, 14, 29; bra nye, smin drug, rgyal, sa ga, dbo, khrums stod; days Pig, Hare, Dragon, Snake
- **Neither side:** the 28th, printed in both halves; one good date read ༡༤ or ༡༩ (the shapes of 4 and 9 cannot be told apart there); khrums smad (bad for ploughing, good for sowing)
- **Not calculated:** Abhijit (good); the link ཚོར་བ; rising signs; srung bu, the earth lord, the Dragon god and the like
- The good dates run in two lines and are read on the scan at three times the size; the readings by machine disagree on most of them.
- **The scan (O10f):** The doubtful good date reads ༡༤ by the shape of the bad half's own 4 and 14 on the same folio (the open hook with a flat head): then the 14th, like the 28th, is printed in both halves and counts for neither side, not bad.

### 23. Giving gifts and dowries — སྐྱས་བྱེད་པ་བཟང་བ

KP img. 36, KP2 img. 257.

The box on the scan, KP BDRC img. 38 (O10f):

- Good half: «༄། །གཟའ་ལྷག་ཕུར་ / ༼ཟླ༽ སྐར་མ་ཁྲོུཾད་ནུཾ་ / དབོ་སྣྲུབས་ཆུཾད་༦»
- Bad half: «༄། །གཟའ་སངས་ཉི་མིག་སྤེན་སྐར་མ་ཐརྐ་ནག་མགོ་མྱེ་རྒྱལ་སྣར་མོནྲེ་སྨིན་ལག ས་རི་ / ས་ག་མཆུ་ཆོད་སྐག་ཤིན་ཏུ་ངན། ཉི་མ་ཡོས་ཁྱི་ལུག་སྦྲུལ་སྟག་ཁོན་སེ་ཤར་གནམ་པི་ལིང་ / ཙང་ཀུན་ཕོ་ཉ་ཧལྱི་ཉི་ནག་གཟའ་བདུན་ཟས་འཚོལ་ལྔ་དགུའི་ཕྱོགས་བཅས།»

- **Good:** Wed Thu; khrums stod, nam gru, dbo, snrubs, chu smad
- **Bad:** Fri Sun Tue Sat; tha skar, nag pa, mgo, me bzhi, rgyal, snar ma, mon gre, smin drug, lag, sa ri, sa ga, mchu, chu stod, skag; days Hare, Dog, Sheep, Snake, Tiger; trigrams khon
- **Neither side:** the Moon (in brackets)
- **Not calculated:** the Chinese almanac's days (se shar, gnam stong, pi ling, tsang kun …) and the Seven Planets' food-seeking (bad)
- The bad mansions end «ཤིན་ཏུ་ངན» (very bad), an emphasis, not a restriction.

### 24. Sewing tents and felt — སྦྲ་དང་ཕྱ་ཐེར་འཚེམ་པར་བཟང་བ

KP img. 37, KP2 img. 258.

The box on the scan, KP BDRC img. 39 (O10f):

- Good half: «༄། །གཟའ་ཕུར་༼ཟླ་ལྷག་སངས༽ སྐར་མ་ས་རི་ / ནབསོ་སྣར་ཁྲོུཾད་ཆོད་དབོ་སྨིན་…འཚེམ་བ་འབྲིང་། ཉི་མ་ / སྟག་སྦྲུལ་དུས་སྦྱོར་སེངྒེ་གཞུ་བུམ་པ་ཉ་གླང་»
- Bad half: «༄། །གཟའ་ཉི་ལྷག་སྤེན་ཚེས་༢༩ སྐར་མ་ཆུཾད་ / གྲེ་མཆུ་སྐག་ལག་ནུཾ་མོནྲེ་ཁྲུཾད་སྣྲུབས་ནག་མྱེ་…འཚེམ་ / བ་བཟང་འགེབས་བ་ངན། ཉི་མ་སྤྲེལ་དུས་སྦྱོར་ཆུ་སྲིན་སྡིག»

- **Good:** Thu; sa ri, nabs so, snar ma, khrums stod, chu stod, dbo; days Tiger, Snake
- **Bad:** Sun Sat; dates 29; chu smad, gre, mchu, skag, lag, nam gru, mon gre, khrums smad, snrubs, nag pa; days Monkey
- **Neither side:** the Moon, Mercury and Venus (bracketed among the good; Mercury also named bad); smin drug (middling for sewing); me bzhi (good for sewing, bad for pitching)
- **Not calculated:** rising signs

### 25. Building storehouses — བང་མཛོད་བཅའ་བར་བཟང་བ

KP img. 37, KP2 img. 258.

The box on the scan, KP BDRC img. 39 (O10f):

- Good half: «༄། །གཟའ་ལྷག་སྤེན་༼ཟླ་ཕུར་སངས༽ སྐར་མ་ནབསོ་ / སྣར་རྒྱལ་ས་རི་གྲོཞི་བྱིཞི་སྣྲུབས་…འདི་རྣམས་མཆོག། སག་ / ཆོད་མོནྲུ་ཆུཾད་འདི་རྣམས་ཁ་ཕྱེ་ངན་ཉིམ་ལུག་ཁྱི་དུས་སྦྱོར་གླང་བུ་ཉ»
- Bad half: «༄། །གཟའ་ཉི་མིག་སྐར་མ་བྲཉེ་སྨིན་མཆུ་ / གྲེ་…འདི་རྣམས་ཁ་ཕྱེ་བཟང་། ས་རི་ནག་…ཁ་ཕྱེ་ངན། ཉི་མ་ཕག / སྦྲུལ་བྱ་གླང་ཁ་ཕྱེ་ངན། དུས་སྦྱོར་ཀརྚ་སྡིག་དབུལ་ལག་སོགས་ངན།»

- **Good:** Wed Sat; nabs so, snar ma, rgyal, gro bzhin, snrubs; days Mouse, Sheep, Dog
- **Bad:** Sun Tue; days Pig, Snake, Bird
- **Neither side:** the Moon, Thursday and Friday (in brackets); sa ri (good, and bad for opening the storehouse); sa ga, chu stod, mon gru, chu smad (named good, but «འདི་རྣམས་ཁ་དབྱེ་ངན», bad for opening it); bra nye, smin drug, mchu, gre (named bad, but good for opening it); nag (bad for opening it); the Ox day (bad for opening it)
- **Not calculated:** Abhijit (best); rising signs; dbul and the like (bad)
- The first good mansions are «འདི་རྣམས་མཆོག» (these are the best), an emphasis.
- **The scan (O10f):** KP's good half has the Sheep and Dog days only («ཉིམ་ལུག་ཁྱི»); KP2 (img. 260) has «ཉི་མ་བྱི་ལུག་ཁྱི», with the Mouse.

### 26. Raising victory banners and flags — རྒྱལ་མཚན་བ་དན་འཕྱར་བ་ལ་བཟང་བ

KP img. 38, KP2 img. 259.

The box on the scan, KP BDRC img. 40 (O10f):

- Good half: «༄། །གཟའ་ཉི་ཕུར་སྤེན་༼ཟླ་ལྷག་སངས༽ སྐར་མ་མགོ་ / དབོ་ཁྲོུཾད་ཐརྐ་ཆུཾད་རྒྱལ་དུས་སྦྱོར་གླང་སེངྒེ་གཞུ་ / འཁྲིག བླ་ནག་སོགས་མེད་ཅིང་བདུད་རྩི་ཐུན་མཚམས་སོགས།»
- Bad half: «༄། །གཟའ་ལྷག སྐར་མ་སྨིན་ལག་གྲེ་མཆུ་ / སྣྲུབས་བྲཉེ་ཉི་མ་གླང་ལུག་དུས་སྦྱོར་ཆུ་སྲིན་ཀརྚ་སྡིག / སྲང་ཉི་ནག་བིཥྚི་འཕུང་བྱེད་ནག་མོ་རྒྱུ་དུས་སོགས་འཛེམ།»

- **Good:** Sun Thu Sat; mgo, dbo, khrums stod, tha skar, chu smad, rgyal
- **Bad:** smin drug, lag, gre, mchu, snrubs, bra nye; days Ox, Sheep
- **Neither side:** the Moon and Venus (in brackets among the good); Mercury (in brackets among the good, and bad)
- **Not calculated:** rising signs; the nectar periods (good: 'without bla nag and the like'); nyi nag, Viṣṭi, 'phung byed, nag mo and the like (bad)

### 27. Making springs, wells and canals — ཆུ་མིག་ཁྲོན་པ་ཡུར་བ་བཟོ་བ་ལ་བཟང་བ

KP img. 38, KP2 img. 259.

The box on the scan, KP BDRC img. 40 (O10f):

- Good half: «༄། །གཟའ་ཟླ་ལྷག་སངས་སྤེན་༼ཕུར༽ ཚེས་༢ ༧ / ༡༢ ༡༧ ༢༢ ༢༧ སྐར་མ་ཁྲུཾད་ཐརྐ་ལག་གྲོཞི་བྱིཞི་ / སྣྲོན་ཆུཾད་ས་རི་སྐག་དབོ་ཉི་མ་ལུག་དུས་སྦྱོར་འཁྲིག / བུམ་ཆུ་སྲིན་གཞུ་གླང་ཀརྚ་[ཞིབ་ཏུ་ཡིན་ན་བཻ་དཀར་ཆུ་བརྟག་བུམ་པའི་འཁོར་ལོས་བརྩི་བྱ།] (small print, machine, seen at 2×)»
- Bad half: «༄། །གཟའ་ཉི་མིག་ཚེས་༣ ༡༣ ༡༨ ༢༣ ༢༨ ༨ / སྐར་མ་སྨིན་མཆུ་གྲེ་མྱེ་ནག་མོནྲུ་ཁྲོུཾད་ཁཾ་༣ / ཉི་མ་ཡོས་འབྲུག་སྦྲུལ་ཕག་དུས་སྦྱོར་ལུག་སེངྒེ་ཉི་ནག་བྱི་ལམ་»

- **Good:** Mon Wed Fri Sat; dates 2, 7, 12, 17, 22, 27; khrums smad, tha skar, lag, gro bzhin, snron, chu smad, sa ri, skag, dbo; days Sheep
- **Bad:** Sun Tue; dates 3, 13, 18, 23, 28, 8; smin drug, mchu, gre, me bzhi, nag pa, mon gru, khrums stod; days Hare, Dragon, Snake, Pig; trigrams kham
- **Neither side:** Thursday (in brackets)
- **Not calculated:** Abhijit (good); rising signs; the sme ba three (bad); nyi nag, byi lam (bad)
- The good dates are every fifth from the 2nd, the bad every fifth from the 3rd.

### 28. Feeding up horses — རྟ་རྒྱགས་པ་ལ་བཟང་བ

KP img. 39, KP2 img. 260.

The box on the scan, KP BDRC img. 41 (O10f):

- Good half: «༄། །གཟའ་སངས་མིག་ཉི་༼ཟླ་ལྷག་ཕུར༽ སྐར་མ་སྨིན་ / མགོ་རྒྱལ་ནབསོ་མྱེ་སྣར་ནག་གྲོཞི་བྱིཞི་ / ཉི་མ་ཡོས་དུས་སྦྱོར་གླང་སེངྒེ་གཞུ་གིན»
- Bad half: «༄། །གཟའ་སྤེན་ཚེས་༡༡ ༢༢ ༢༩ སྐར་མ་ས་རི་ / མོནྲུ་མཆུ་གྲེ་ཁྲོུཾད་ཆོད་ཉི་མ་བྱི་རྟ་དུས་སྦྱོར་ / ཀརྚ་བུམ་ཧལྱི་བ་ཕུད་བྱེད་རཱ་ཧུའི་གདོང་རང་རྟ་སེ་བ་བུམ་སྟོང་སེ་ལོ (the third word machine)»

- **Good:** Fri Tue Sun Wed Thu; smin drug, mgo, rgyal, nabs so, me bzhi, snar ma, nag pa, gro bzhin; days Hare; trigrams gin
- **Bad:** Sat; dates 11, 22, 29; sa ri, mon gru, mchu, gre, khrums stod, chu stod; days Mouse, Horse
- **Neither side:** the Moon (in brackets)
- **Not calculated:** Abhijit (good); rising signs; the karaṇa, ha li, Rāhu's face and the like (bad)
- The third bad date is ༢༩ on the scan (KP's machine reading had 24).
- **The scan (O10f) reads otherwise:** the arched bracket spans «ཟླ་ལྷག་ཕུར» in KP and KP2 (BDRC img. 41, KP2 img. 262, at 4×): the Moon, Mercury and Jupiter are bracketed, so Wednesday and Thursday count for neither side, not good.

### 29. Treating horses, mules and donkeys — རྟ་དྲེའུ་བོང་བཅོས་བྱེད་པ་ལ་བཟང་བ

KP img. 39, KP2 img. 260. Otherwise as box 28.

The box on the scan, KP BDRC img. 41 (O10f):

- Good half: «༄། །གཟའ་ཟླ་ལྷག སྐར་མ་ནབསོ་ཆུཾད་མོནྲུ་ས་རི་ / མགོ་ནུཾ་སྐག་…བྱ་གཅོད་དམིགས་བསལ་ལེགས། མྱེ་གྲེ་མཆུ་ / [འབྲེག]་པ་བཟང་། བྲཉེ་དབོ་…[བྲེས་གཞན་…]་བཟང་།»
- Bad half: «༄། །གཟའ་ཉི་སྤེན་མིག་མེ་རྒྱག་བཟང་། སྐར་མ་ / མཆུ་གྲེ་ལྷཾས་གྲོཞི་བྱིཞི་…[…]་ངན། གཞན་ / རྣམས་བཟང་ངན་རྒྱགས་པ་དང་མཚུངས།»

- **Good:** Mon Wed; nabs so, chu smad, mon gru, sa ri
- **Bad:** —
- **Neither side:** mgo, nam gru, skag (castrating especially good); bra nye and dbo (good for other work); the Sun, Saturn and Mars (named bad, but good for cauterizing); me bzhi, mchu, gre, lha mtshams, gro bzhin (named bad for particular work)
- **Not calculated:** Abhijit
- «གཞན་རྣམས་བཟང་ངན་རྒྱགས་པ་དང་མཚུངས»: the rest as box 28, whose lists it takes where it names nothing plainly.

### 30. Saddling — སྒ་རྒྱག་ལ་བཟང་བ

KP img. 39, KP2 img. 260. Otherwise as box 28.

The box on the scan, KP BDRC img. 41 (O10f):

- Good half: «༄། །གཟའ་མིག་ལྷག་ཕུར་༼ཉི་སངས༽ སྐར་མ་སྨིན་རྒྱལ་ / ནབསོ་ལྷཾས་གྲོཞི་བྱིཞི་མགོ་སྣར་མོནྲུ་ཆུཾད་དབོ་མྱེ་ས་རི»
- Bad half: «༄། །གཟའ་སྤེན་སྐར་མ་སྐག་མཆུ་གྲེ་མོནྲེ་ / [ཁྱིམ]་སོགས་བཟང་ངན་གཞན་རྟ་རྒྱགས་དང་མཚུངས།»

- **Good:** Tue Wed Thu Fri; smin drug, rgyal, nabs so, lha mtshams, gro bzhin, mgo, snar ma, mon gru, chu smad, dbo, me bzhi, sa ri
- **Bad:** Sat; skag, mchu, gre, mon gre
- **Neither side:** the Sun (in brackets)
- **Not calculated:** Abhijit (good)
- The rest as box 28 («རྟ་རྒྱགས་དང་མཚུངས»).
- **The scan (O10f) reads otherwise:** the bracket spans «ཉི་སངས» in KP and KP2 (BDRC img. 41, KP2 img. 262): Friday is bracketed with the Sun and counts for neither side, not good.

### 31. Keeping dogs — ཁྱི་བསྟེན་པར་བཟང་བ

KP img. 40, KP2 img. 261. Otherwise as box 16a.

The box on the scan, KP BDRC img. 42 (O10f):

- Good half: «༄། །གཟའ་སྤེན་མིག སྐར་མ་སྐག་…ཁྱད་པར་བླ་སྐར་ཡིན་པས་བཟང་། / མཆུ་ཐརྐ་ཁྲུཾད་…གཞན་མ་གོང་གི་ནོར་ལེན་དང་མཚུངས་སོ། །»
- Bad half: «༄། །གཟའ་ལྷག་ཕུར་སངས་མིག་ཉི་གཟའ་འདི་ / རྣམས་འབྲིང་། ཉི་མ་ཁྱི་ཁེན་༥ སེ་བ་བླ་མཁྱེན་ / ཧང་ཕན་ཕྱོགས་ཧལྱི་ཟས་འཚོལ་དང་གནས་ཕྱོགས་ཉི་[…]།»

- **Good:** Sat Tue; skag, mchu, tha skar
- **Bad:** days Dog; trigrams khen
- **Neither side:** Wednesday, Thursday, Friday, Mars (?) and the Sun, which the bad half calls «འདབྲིང» (middling); one khrums of the good half (ཁྲོད in KP, ཁྲུཾད by the readings of both prints)
- **Not calculated:** the sme ba five, se ba, bla mkhyen; hang phan, ha li and the like (bad)
- skag is good above all, 'being its bla skar' (the dog's). «གཞན་མ་གོང་གི་ནོར་ལེན་དང་མཚུངས་སོ»: the rest as receiving wealth (box 16).

### 32. Calling prosperity, bon rites — གཡང་ལེན་བོན་ཆོག་ལ་བཟང་བ

KP img. 40, KP2 img. 261.

The box on the scan, KP BDRC img. 42 (O10f):

- Good half: «༄། །གཟའ་ཉི་ཟླ་ལྷག་སྤེན་༼ཕུར་སངས༽ ཚེས་༡༥ སྐར་མ་སྣར་ནབསོ་སྨིན་རྒྱལ་ / མགོ་དབོ་ས་རི་མོནྲུ་མོནྲེ་སྣྲོན་སྣྲུབས་ཆོད་ནུཾ་ཆུཾད་གྲེ་ནག་མྱེ་ལྷཾས་ཐརྐ་ / ལག་གྲོཞི་མིག་ཡང་། མཆུ་བོན་ཆོག་ངན། ཉི་མ་སྟག་༦ ༩ འཕེལ་འགྱུར་སྦྱོར་བཀྲ་ཤིས་ཉི་མ།»
- Bad half: «༄། །གཟའ་མིག སྐར་མ་ས་ག་ / སྐག་ཁྲུཾད་བྲཉེ་ཉི་མ་ / ཡོས་ཕག་འཕུད་བྱེད་»

- **Good:** Sun Mon Wed Sat; dates 15; snar ma, nabs so, smin drug, rgyal, mgo, dbo, sa ri, mon gru, mon gre, snron, snrubs, chu stod, nam gru, chu smad, gre, nag pa, me bzhi, lha mtshams, tha skar; days Tiger
- **Bad:** Tue; sa ga, skag, khrums smad, bra nye; days Hare, Pig
- **Neither side:** Thursday and Friday (in brackets); lag and gro bzhin (with a word not read); mchu (bad for Bon rites)
- **Not calculated:** the sme ba six and nine; the 'phel 'gyur combination and the auspicious days (good); 'phud byed (bad)

### 33. Learning writing and astrology — ཡི་གེ་རྩིས་རིག་པ་བསླབ་པར་བཟང་བ

KP img. 40, KP2 img. 261.

The box on the scan, KP BDRC img. 42 (O10f):

- Good half: «༄། །གཟའ་ཕུར་ལྷག་སངས་ཉི་ཚེས་༡༨ [༢༡] སྐར་མ་སྨིན་ནབསོ་མཆུ་མྱེ་རྒྱལ་ / ཁྲུཾད་ས་རི་མགོ་ས་ག་མོནྲུ་ཁྲོུཾད་ལྷཾས་ལག ནག་རི་མོ་དང་བཟོ་སློབ་པ་མཆོག་ཏུ་བཟང་། འདུ་ཉི་མ་ / ཁྱི་དུས་སྦྱོར་བུ་ཉ་གླང་སེངྒེ་བུ་མོ་སྲང་གཞུ་འཁྲིག་རྒྱུ་རྩལ་བསླབ་པར་མཆོག་ཏུ་བཟང་།»
- Bad half: «༄། །གཟའ་སྤེན་མིག་ཚེས་ / ༡༦ སྐར་མ་བྲཉེ་སྐག་ཉི་མ་ཕག / གླང་དུས་སྦྱོར་སྡིག་འཕུད་བྱེད།»

- **Good:** Thu Wed Fri Sun; dates 18; smin drug, nabs so, mchu, me bzhi, rgyal, khrums smad, sa ri, mgo, sa ga, mon gru, khrums stod, lha mtshams, lag; days Dog
- **Bad:** Sat Tue; dates 16; bra nye, skag; days Pig, Ox
- **Neither side:** nag (best for learning drawing and crafts)
- **Not calculated:** rising signs (best for learning skills); 'phud byed (bad)
- A second good date after the 18th is not legible (the readings by machine give 2, 21 or 31).
- **The scan (O10f):** The good half ends its mansions with «འདུ», formation ('du byed), the link, plain: not in the table of 2026-10-10 below, which counts box 33 among those naming none.

### 34. Compounding medicine — སྨན་སྦྱོར་བྱེད་པར་བཟང་བ

KP img. 41, KP2 img. 262.

The box on the scan, KP BDRC img. 43 (O10f):

- Good half: «༄། །གཟའ་ཟླ་ཉི་ཕུར་སངས་སྐར་མ་སྨིན་མགོ་རྒྱལ་མྱེ་སྣར་ / ས་རི་གྲོཞི་མོནྲེ་མོནྲུ་ཁྲོུཾད་ཁྲུཾད་ནུཾ་ཆུཾད་ནག་ནབསོ་ལྷཾས་བྱིཞི་མིང་རྣཾས་ / ཤེས་ལེན་ཉི་མ་བྱ་ཁྱི་༩ དུས་སྦྱོར་བུམ་…མཆོག འཁྲིག་གཞུ་ཉ་ / བདུད་རྩི་ཐུན་མཚམས་གཟའ་སྐར་ཆུ་ཕྲད་སོགས།»
- Bad half: «༄། །གཟའ་སྤེན་མིག་ལྷག སྐར་མ་ / བྲཉེ་སྐག་སྣྲོན་གྲེ་ས་ག་ཐརྐ་ཉི་མ་སྟག་ / སྦྲུལ་ལུག་དུས་སྦྱོར་ཆུ་སྲིན་སྡིག་ཀརྚ་དབུལ་ / འཕུང་བྱེད་མི་ཕྲོད་རིགས་ལྡན་དུས།»

- **Good:** Mon Sun Thu Fri; smin drug, mgo, rgyal, me bzhi, snar ma, sa ri, gro bzhin, mon gre, mon gru, khrums stod, khrums smad, nam gru, chu smad, nag pa, nabs so, lha mtshams; days Bird, Dog
- **Bad:** Sat Tue Wed; bra nye, skag, snron, gre, sa ga, tha skar; days Tiger, Snake, Sheep
- **Not calculated:** Abhijit (good); the links མིང, རྣམ་ཤེས, ལེན (good); the sme ba four; rising signs (Virgo 'best'); the nectar periods and the meeting of planet and mansion in water (good); dbul and the like (bad)
- Before box 36 (img. 42) a verse reckons from the Sun's mansion where the Moon's falls on the physician, the spatula, the medicine bowl or the patient.

### 35. Bloodletting, moxibustion, treatment — གཏར་མེ་དཔྱད་བཅོས་བྱེད་པར་བཟང་བ

KP img. 41, KP2 img. 262.

The box on the scan, KP BDRC img. 43 (O10f):

- Good half: «༄། །གཟའ་ཟླ་ལྷག་ཕུར་སངས་ཚེས་༡༠ / ༡༨ སྐར་མ་ལྷཾས་ནུཾ་གྲོཞི་བྱིཞི་མཆུ་སྣར་ / ནབསོ་རྒྱལ་ས་རི་སྣྲོན་ཉི་མ་བྱ་ཁྱི་ / དུས་སྦྱོར་གླང་འཁྲིག་བུམ་ཉ་༼ཀརྚ་སྲང༽ / ༼སེངྒེ་གཞུ༽»
- Bad half: «༄། །གཟའ་སྤེན་ཉི་མིག་མེ་བཙའ་བཟང་། ཚེས་༩ ༨ ༢༩ ༣༠ སྐར་ / མ་ལག་ཁྲུཾད་གྲེ་ཐརྐ་བྲཉེ་[ལི]་ཉི་མ་འབྲུག་སྦྲུལ་ལུག / ༢ ༧ དུས་སྦྱོར་སྡིག་ཆུ་སྲིན་བུཾ་ལུག་བིཥྚི་གདོང་འཕུང་བྱེད་ / འཆི་སྦྱོར་སྲོག་ཚེས་རིགས་ལྡན་འཆར་སྐབས་ཤིན་ཏུ་ངན། གཞན་ཡང་མི་རྟའི་བླ་ / གནས་གཅེས་པར་འཛིན་དགོས་པ་འོག་ནས་གསལ།»

- **Good:** Mon Wed Thu Fri; dates 10, 18; lha mtshams, nam gru, gro bzhin, mchu, snar ma, nabs so, rgyal, sa ri, snron; days Bird, Dog
- **Bad:** dates 9, 8, 29, 30; lag, gre, tha skar, bra nye; days Dragon, Snake, Sheep
- **Neither side:** Saturn, the Sun and Mars («མེ་བཙའ་བཟང», good for moxibustion); one khrums of the bad half, and one name after bra nye, not read
- **Not calculated:** Abhijit (good); rising signs (Cancer and Libra in brackets); the sme ba two and seven; Viṣṭi; the 'chi sbyor, the srog dates and the rising of rigs ldan (very bad); the bla gnas of people and horses (box 36)
- **The scan (O10f):** The bad half's khrums reads ཁྲུཾད at 5×, khrums smad: bad. The word after bra nye is read «ལི» (the trigram li) by Yigdzin-1 and is not settled on the scan. The rising signs in the arched brackets are Cancer and Libra, Leo and Sagittarius.

### 37. Bathing and washing the hair — ཁྲུས་དང་སྐྲ་འཁྲུ་བྱེད་པར་བཟང་བ

KP img. 46, KP2 img. 267.

The box on the scan, KP BDRC img. 48 (O10f):

- Good half: «༄། །གཟའ་ཟླ་ལྷག་ཕུར་སངས་ཉི་…ཁྲུས་བཟང་སྐྲ་འཁྲུ་ངན། ཚེས་༣ ༤ ༥ ༦ ༨ ༡༠ ༡༡ / ༡༣ ༡༥ ༡༦ ༡༨ ༡༩ ༢༢ ༢༣ ༢༦ སྐར་མ་སྨིན་སྣར་མགོ་ལག་ཐརྐ་ཆོད་ས་རི་ / ཆུཾད་ཁྲུཾད་ནུཾ་ནག་མོནྲེ་བྱིཞི་གྲོཞི་སྐྲ་བཞར་རྒྱལ་རྟ་ཟླའི་ཚེས་ཉེར་ལྔ་ / མགོ་…ཟླའི་ཚེས་བརྒྱད་སྡིག་པ་འདག སྐྱེ་མཆེད་སྲིད་པ་སྔོ་? འདུ་བྱེད་སྐྱེ་བ་མ་རིག / རྣམ་ཤེས་དུས་སྦྱོར་ཉ་བུམ་པ་ཆུ་སྲིན་བུ་མོ།»
- Bad half: «༄། །གཟའ་མིག་སྤེན་ཚེས་༡ ༢ ༧ ༩ / ༡༢ ༡༤ ༡༧ ༢༠ ༢༡ ༢༤ ༢༥ ༢༨ ༢༩ ༣༠ སྐར་ / མ་རྒྱལ་མཆུ་ས་ག་ཁྲོུཾད་གྲེ་བྲཉེ་ཡུག / མ་སྐྲ་ཁྲུས་བཟང་། [«ཡུག་མ» not understood] མིང་ཚོར་སྲེད་ཉི་མ་ / ཁྱི་སྟག་ལི་ཁེན་༡ ༦ དུས་སྦྱོར་སྲང་ཀརྚ་»

- **Good:** Mon Wed Thu Fri; dates 3, 4, 5, 6, 8, 10, 11, 13, 15, 16, 18, 19, 22, 23, 26; smin drug, snar ma, mgo, lag, tha skar, chu stod, sa ri, chu smad, khrums smad, nam gru, nag pa, mon gre
- **Bad:** Tue Sat; dates 1, 2, 7, 9, 12, 14, 17, 20, 21, 24, 25, 28, 29, 30; rgyal, mchu, sa ga, khrums stod, gre, bra nye
- **Neither side:** the Sun (good for bathing, bad for washing the hair); gro bzhin (good for shaving the head); rgyal on the 25th of the Horse month and mgo on the 8th (named for particular merits)
- **Not calculated:** Abhijit (good); the twelve links; rising signs
- The two halves divide the thirty dates between them; the bad half's second line is worn. The complement of the good dates was taken for it until WB's p. 404 was read (2026-10-09, [hair-dates.md](hair-dates.md)): the good half is WB's good list date for date, and WB's bad list names the 27th on neither side, so the box's bad dates are WB's, without the 27th. A table below gives what bathing on each date brings, after another tradition.
- **The scan (O10f):** The bad half's last line names the Dog and Tiger days and the trigrams li and khen, plain, with the sme ba 1 and 6: bad. The app has none of them for this box. «ཡུག་མ» before «སྐྲ་ཁྲུས་བཟང» is not understood.

### 39. Enthronement — རྒྱལ་སར་འཇུག་པ་ལ་བཟང་བ

KP img. 47, KP2 img. 268.

The box on the scan, KP BDRC img. 49 (O10f):

- Good half: «༄། །གཟའ་ཉི་མིག་ཕུར་༼ཟླ་ལྷག་སངས༽ ཚེས་[༡ ༢ ༨ ༩ ༡༠ / ༡༡ ༡༢ ༢༡ ༢༢ ༡༥ ༡༨] སྐར་མ་སྣར་མགོ་རྒྱལ་ལྷཾས་སྣྲོན་ཆུཾད་ནབསོ་ / ཆོད་མོནྲུ་དབོ་ཁྲུཾད་ནུཾ་སྨིན་ས་རི་མྱེ་རྣམ་ཤེས་སྐྱེ་མཆེད་སྐྱེ་ / ཉི་མ་རྟ་འབྲུག་སྟག་ཡོས་ཁེན་དུས་སྦྱོར་སེངྒེ་གཞུ་རྟ་ / བུམ་པ་ས་ཆུ་འཕྲོད་པ་བདུད་རྩི་ཐུན་མཚམས་སོ། །»
- Bad half: «༄། །གཟའ་སྤེན་ཚེས་[༦ ༡༦ ༢༦ ༩ ༢༠ ༢༡ ༢༢ ༢༣ ༢༤ ༢༥ / ༢༧ ༢༨ ༢༩ ༣༠] སྐར་མ་བྲཉེ་ལག་སྐག་མཆུ་ནག་གྲོཞི་ / བྱིཞི་སྣྲུབས་ས་ག་མོནྲེ་རྒ་ཤི་མ་རིག་ཉི་མ་སྦྲུལ་སྤྲེལ་ / དུས་སྦྱོར་ཆུ་སྲིན་སྡིག་སེ་བྱི་པི་ལིང་ཙང་ཀུན་ཐེ་སེ་ / སེ་བ་བླ་མཁྱེན་འཕུང་བྱེད་ནག་མོ་རྒྱུ་དུས།»

- **Good:** Sun Tue Thu; snar ma, mgo, rgyal, lha mtshams, snron, chu smad, nabs so, chu stod, mon gru, dbo, khrums smad, nam gru, smin drug, sa ri, me bzhi; days Horse, Dragon, Tiger, Hare; trigrams khen
- **Bad:** Sat; bra nye, lag, skag, mchu, nag pa, gro bzhin, snrubs, sa ga, mon gre; days Snake, Monkey
- **Neither side:** the Moon, Mercury and Venus (in brackets)
- **Not calculated:** the dates of both halves (crowded in two lines and only partly legible: the good begin 1, 2, 8, 9, 10, the bad 6, 16, 26); the links; rising signs; the earth and water combination and the nectar periods (good); se ba, bla mkhyen, 'phung byed, nag mo and the like (bad)
- The dates are left out until read with confidence on a better scan.
- **The scan (O10f):** The dates in brackets above are read at 1.7× and are crowded; not settled digit by digit.

### 41. Taking attendants and servants — འཁོར་གཡོག་བསྟེན་པར་བཟང་བ

KP img. 48, KP2 img. 269.

The box on the scan, KP BDRC img. 50 (O10f):

- Good half: «༄། །གཟའ་ཟླ་ལྷག་སངས་༼ཉི་ཕུར༽ ཚེས་ / ༨ ༥ ༡༤ ༢༩ སྐར་མ་ཁྲུཾད་ནབསོ་ཁྲོུཾད་ / རྒྱལ་མཆུ་དབོ་སྣྲུབས་ཆོད་ཆུཾད་བྲཉེ་ལག་[…] / ས་བསྟེན་ཚོར་བ་ཉི་མ་རྟ་ཕྱི་བང་བྲིས་པ་[…]་བཟང་། / དུས་སྦྱོར་ལུག་གཞུ་ཆུ་སྲིན་སེངྒེ་གླང་»
- Bad half: «༄། །གཟའ་མིག་སྤེན་ཚེས་༡༡ ༡༢ ༡༣ ༤ ༧ ༩ ༡༠ [༡༣] ༡༨ ༢༡ / ༢༢ ༢༣ ༢༥ ༢༦ ༢༧ ༢༨ ༢༩ ༣༠ སྐར་མ་གྲེ་སྣྲོན་ཉི་མ་སྦྲུལ་ / ཁེན་དུས་སྦྱོར་ཀརྚ་སྲང་འཕུང་བྱེད་ཞིབ་རྩིས་བྱེད་ན་སྨེ་བ་ཙཀྲ་དང་། སྦྲུལ་འཁོར་ / ལ་བསྟུན་དགོས་ཀྱང་ཀུན་གྱི་སྤྱོད་ཡུལ་དུ་མ་གྱུར་པས་འདིར་མི་གསལ།»

- **Good:** Mon Wed Fri; dates 8, 5, 14; khrums smad, nabs so, khrums stod, rgyal, mchu, dbo, snrubs, chu stod, chu smad, bra nye; days Horse
- **Bad:** Tue Sat; dates 11, 12, 13, 4, 7, 9, 10, 18, 21, 22, 23, 25, 26, 27, 28, 30; gre, snron; days Snake; trigrams khen
- **Neither side:** the Sun and Thursday (in brackets); the 29th (in both halves); lag (with a qualifier not read)
- **Not calculated:** rising signs; 'phung byed; a closing note that the sme ba chart should also be weighed
- One more bad date in the first line, read 13 or 15, is left out.
- **The scan (O10f):** The good half's fourth line reads «ས་བསྟེན་ཚོར་བ»: the link feeling (tshor ba) may be plain there, or under the qualifier before it; not settled. The bad half's closing note: an exact reckoning would follow the sme ba wheel and the snake wheel (སྦྲུལ་འཁོར, sbrul 'khor), not given because not in common use.

### 43. Putting on ornaments — རྒྱན་འདོགས་པར་བཟང་བ

KP img. 50, KP2 img. 271.

The box on the scan, KP BDRC img. 52 (O10f):

- Good half: «༄། །གཟའ་ཟླ་སངས་ལྷག་ཕུར་ཉི་སྐར་མ་སྨིན་མགོ་གྲོཞི་རྒྱལ་ནབསོ་མཆུ་ / གྲེ་ཆོད་ཆུཾད་སྣྲོན་སྣྲུབས་ལག་མྱེ་མོནྲེ་ལྷཾས་ཁྲུཾད་དབོ་ནག་འདུ་བྱེད་ཉི་མ་རྟ་སྤྲེལ་ / གིན་དུས་སྦྱོར་གླང་འཁྲིག་བུ་མོ་གཞུ་ཉ་སེངྒེ་ས་ཆུ་འཕྲད་སོགས་བཟང་།»
- Bad half: «༄། །གཟའ་མིག / སྤེན་སྐར་མ་བྲཉེ་ / ཐརྐ་སྐག་མོནྲུ»

- **Good:** Mon Fri Wed Thu Sun; smin drug, mgo, gro bzhin, rgyal, nabs so, mchu, gre, chu stod, chu smad, snron, snrubs, lag, me bzhi, mon gre, lha mtshams, khrums smad, dbo, nag pa; days Horse, Monkey; trigrams gin
- **Bad:** Tue Sat; bra nye, tha skar, skag, mon gru
- **Not calculated:** Abhijit (good); the link འདུ་བྱེད; rising signs; the earth and water combination (good)

### 44. Suppressing the sri spirits — སྲི་གནོན་བྱ་བར་བཟང་བ

KP img. 50, KP2 img. 271.

The box on the scan, KP BDRC img. 52 (O10f):

- Good half: «༄། །གཟའ་མིག་སྤེན་ཉི་ཕུར་༼ལྷག་སངས༽ ཚེས་[…] / […] སྐར་མ་མགོ་ལག་སྐག་སྣྲོན་སྣྲུབས་ས་ག་ / ས་རི་ཁྲུཾད་མྱེ་ཐརྐ་མཆུ་མཆོག་ཏུ་བཟང་། ཉི་མ་སྟག་འབྲུག་སྤྲེལ་ / ཟོན་དུས་སྦྱོར་སེངྒེ་ཆུ་སྲིན་གླང་གཞུ་སྡིག་ཀརྚ་༣ དུས་མཚན་[…]་སོགས།»
- Bad half: «༄། །གཟའ་ཟླ་ཚེས་༡ སྐར་མ་རྒྱལ་སྨིན་ / མོནྲེ་ལྷཾས་གྲོཞི་ཉི་མ་ཡོས་སྦྲུལ་ཕག་བྱ་ / བྱི་གླང་ཁོན་ཀླུ་གཟའ་ཟས་འཚོལ་ཀི་ཀང་ / ས་འོག་རིམ་དགུའི་ས་བདག་བརྟག།»

- **Good:** Tue Sat Sun Thu; mgo, lag, skag, snron, snrubs, sa ga, sa ri, khrums smad, me bzhi, tha skar, mchu; days Tiger, Dragon, Monkey; trigrams zon
- **Bad:** Mon; dates 1; rgyal, smin drug, mon gre, lha mtshams, gro bzhin; days Hare, Snake, Pig, Bird, Mouse, Ox; trigrams khon
- **Neither side:** Wednesday and Friday (in brackets)
- **Not calculated:** the good dates (two crowded lines, not read with confidence); Abhijit (good); rising signs; the Nāga planet's food-seeking and ki kang (bad); the earth lords of the nine levels
- mchu is 'best of all'.

### 48. Fire offerings — སྦྱིན་སྲེག་བྱེད་པ་ལ་བཟང་བ

KP img. 54, KP2 img. 275.

The box on the scan, KP BDRC img. 56 (O10f):

- Good half: «༄། །གཟའ་ཉི་མིག་ཕུར་༼སྤེན༽ ཚེས་༣ ༡༣ ༢༣ ༡༨ / ༢༨ ༢༩ སྐར་མ་སྨིན་ནབསོ་མཆུ་ས་རི་ཁྲོུཾད་གྲེ་ཆོད་ / སག་མགོ་འདྲེ་འདུལ་བ་བཟང་། རེག་ཉི་མ་སྟག་བྱ་འབྲུག་འདྲེ་ / འདུལ་བཟང་། ལི་ཟོན་[…]་འདུལ་བཟང་། དུས་སྦྱོར་གླང་སེངྒེ་ལུག་བྱིས་ཅན»
- Bad half: «༄། །གཟའ་ཟླ་ལྷག་སངས་ཚེས་༡༢ ༢༢ ༢ ༡༧ ༢༧ / སྐར་མ་ལག་སྐག་བྲཉེ་ཁྲུཾད་སྣྲུབས་ཉི་མ་སྦྲུལ་ཁམས་ / འདུལ་དང་གཉན་འདུལ་ངན། ༥ ༧ ༢ ༤ དུས་སྦྱོར་ཆུ་སྲིན་ཉ་[…]་མོ་རྒྱུ་དུས་ / ཟིན་ཕུང་གཞན་ཡང་ས་བདག་རྒྱུ་དུས་སོགས་ངན།»

- **Good:** Sun Tue Thu; dates 3, 13, 23, 18, 28, 29; smin drug, nabs so, mchu, sa ri, khrums stod, gre, chu stod, lag
- **Bad:** Mon Wed Fri; dates 12, 22, 2, 17, 27; lag, skag, bra nye, khrums smad, snrubs; days Snake
- **Neither side:** Saturn (in brackets); mgo (good for subduing spirits); lag, named in both halves; among the bad, the dates 5, 7, 2, 8 with a qualifier and the Tiger, Bird and Dragon days (bad for subduing spirits)
- **Not calculated:** the links; rising signs; the sme ba; nag mo and the like (bad)
- **The scan (O10f) reads otherwise:** the good half's eighth mansion is «སག», sa ga (written so as in box 12), not lag (BDRC img. 56 at 3.5×). So sa ga is good, and lag, named only in the bad half, is bad, not neither.

### 49. Consecration — རབ་གནས་བྱེད་པར་བཟང་བ

KP img. 54, KP2 img. 275.

The box on the scan, KP BDRC img. 56 (O10f):

- Good half: «༄། །གཟའ་ཕུར་ཟླ་ལྷག་སངས་ཚེས་༡ ༩ ༡༠ ༡༡ ༡༢ ༡༣ ༡༥ / སྐར་མ་སྨིན་ལག་ཁྲོུཾད་ལྷཾས་ནབསོ་མགོ་རྒྱལ་སྣར་ཁྲུཾད་ / [གྲོཞི]་དབོ་སྣར་བྱིཞི་ཆོད་སྣྲུབས་མོནྲེ་ཆུཾད་སྐག་[…] / སྣྲོན་ས་རི་རྣམ་ཤེས་ཉི་མ་སྟག་འབྲུག་པ། དུས་སྦྱོར་སེངྒེ་འཁྲིག་སྡིག་བུཾ་ / ༼ཉ་གླང་བུ༽ བདུད་རྩི་ཐུན་མཚམས། དགུན་ཉི་ལྡོག་ནས་ཡར་ངོའི་ཚེས་རྣམས་བཟང་། (line 3 machine where bracketed; its second «སྣར» doubtful)»
- Bad half: «༄། །གཟའ་ཉི་མིག་སྤེན་ཚེས་༢ ༣ ༥ ༦ ༧ ༨ ༩ [༡༩] / [༡༤ ༡༦] ༡༩ ༢༠ ༢༡ ༢༢ ༢༣ ༢༤ ༢༥ ༢༦ ༢༧ ༢༨ ༢༩ ༣༠ སྐར་མ་ / ནག་སྐག་མཆུ་གྲེ་གྲོཞི་བྲཉེ་སྐྱེ་མཆེད་༦ དུས་སྦྱོར་ཆུ་སྲིན་ / སྲང་ལུག་ཀརྚ་འཆི་སྦྱོར་ལོ་ཟླ་ཞག་ནག་ཉི་ནག་ཀླུ་གཟའ་ནག་ / མོ་ཚེས་གཉན་རྒྱུ་སོགས་བིཥྚི་འཕུང་བྱེད་དབུལ་པི་ལིང་བར་གནམ་ཧལྱི་གཅེར་བུ།»

- **Good:** Thu Mon Wed Fri; dates 1, 10, 11, 12, 13, 15; smin drug, lag, khrums stod, lha mtshams, nabs so, mgo, rgyal, snar ma, khrums smad, dbo, snrubs, gro bzhin, mon gre, chu smad, chu stod, skag; days Tiger, Dragon
- **Bad:** Sun Tue Sat; dates 2, 3, 5, 6, 7, 8, 14, 16, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30; nag pa, skag, mchu, gre, gro bzhin, bra nye
- **Neither side:** the 9th (in both halves); skag and gro bzhin, named in both halves; sa ri (in the good half, with the link རྣམ་ཤེས)
- **Not calculated:** Abhijit (good); the links; rising signs; the nectar periods; the waxing dates from the winter solstice (good); Viṣṭi, 'phung byed and the like (bad)
- One bad date after the 9th is unclear and left out.
- **The scan (O10f):** The good half's fourth line begins «སྣྲོན», snron, plain: good, which the app does not have.

### 54. Composing treatises, learning poetics and grammar — བསྟན་བཅོས་རྩོམ་དང་སྙན་ངག་སྒྲ་སློབ་པར་བཟང་བ

KP img. 57, KP2 img. 280.

The box on the scan, KP BDRC img. 59 (O10f):

- Good half: «༄། །གཟའ་ཕུར་ལྷག་༼སངས་ཉི་ཟླ༽ ཚེས་༢ ༣ ༥ ༡༠ ༡༡ / ༢༣ མཆོག་ཏུ་བཟང་། སྐར་མ་སྨིན་ནབསོ་རྒྱལ་ལྷཾས་མྱེ་ས་རི་ / མོནྲུ་དབོ་ཁྲོུཾད་ཁྲུཾད་ཐརྐ་སྣར་ནུཾ་ཆོད་སྣྲུབས་ལག་མགོ་ / འདུ་བྱེད་ཉི་མ་ཁྱི་༥ དུས་སྦྱོར་ཉ་གླང་འཁྲིག་སེངྒེ་ཕྱོགས་གཙང་སོགས་སུ།»
- Bad half: «༄། །གཟའ་སྤེན་མིག་ཚེས་༤ ༧ ༡༢ ༩ ༡༥ ༡ རིག་ / བྱེད་སྤྱིའི་དུས་གཉན་ངན། སྐར་མ་བྲཉེ་སྐག་མཆུ་ཉི་མ་གླང་ / ཕག ལོ་ཟླ་ཞག་ནག་བིཥྚི་ཐེ་སེ་སེ་ལོ་དམས་ཤིང་ཚེས་གཉན་ / ས་རྒྱལ་ཕུང་ཟོར་འཕུང་བྱེད་སྒྲའི་དུས་གཉན་ལོགས་སུ་གསལ།»

- **Good:** Thu Wed Sun Mon; dates 2, 3, 5, 10, 11, 23; smin drug, snar ma, rgyal, lha mtshams, me bzhi, sa ri, mon gru, dbo, khrums stod, khrums smad, tha skar, nabs so, nam gru, chu stod, snrubs, lag, mgo; days Dog
- **Bad:** Sat Tue; dates 4, 7, 12, 9, 15; bra nye, skag, mchu; days Ox, Pig
- **Neither side:** Friday (in brackets); the 23rd, named 'best' at the end of the good dates
- **Not calculated:** the link འདུ་བྱེད; rising signs; the sme ba five; a clean place (good); Viṣṭi, the se lo and the like, the earth lord, phung zor (bad)
- One more bad date, read 1 with the word rig (rig byed), is left out.
- **The scan (O10f) reads otherwise:** the arched bracket spans «སངས་ཉི་ཟླ» in KP and KP2 (BDRC img. 59, KP2 img. 282, at 4×): the Sun and the Moon are bracketed with Friday and count for neither side, not good.

### 55. Bringing rain — ཆར་འབེབས་བྱེད་པར་བཟང་བ

KP img. 57, KP2 img. 280.

The box on the scan, KP BDRC img. 59 (O10f):

- Good half: «༄། །གཟའ་ཟླ་ལྷག་སངས་༼སྤེན༽ སྐར་མ་བྲཉེ་མགོ་ / ལག་ནབསོ་གྲེ་དབོ་ནག་སག་ལྷཾས་ཆོད་མོནྲེ་གྲོཞི་ / ཆུཾད་མོནྲུ་ཁྲོུཾད་ཉི་མ་བྱི་ཕག་བྱ་སྤྲེལ་ཁམ་༣ ༢ ༤ / དུས་སྦྱོར་ཀརྚ་བུཾ་ཉ་ཆུ་སྲིན་བདུད་རྩི་ཐུན་མཚམས། རྗེ་བརྒྱད་ཐེབས། ཀླུ་ཐེབས་སོགས་[…]།»
- Bad half: «༄། །གཟའ་ཕུར་ཉི་མིག སྐར་མ་སྨིན་རྒྱལ་མྱེ་ / སྣྲོན་སྣྲུབས་མཆུ་ནུཾ་སྐག་ཉི་མ་རྟ་འབྲུག་གླང་ / ལུག་སྦྲུལ་ཀླུ་སྨན་འདེབས་པ་བཟང་། ཁྱི་ལི་༩ ༧ དུས་སྦྱོར་སྡིག / ལུག་སྲང་འཕུང་བྱེད་ཟིན་ཕུང་དབུལ་ཉི་ནག་ཀླུ་བཟློག་པ།»

- **Good:** Mon Wed Fri; bra nye, mgo, lag, snar ma, gre, dbo, nag pa, sa ri, lha mtshams, chu stod, mon gre, gro bzhin, snron, snrubs, mchu, nam gru, chu smad, mon gru, khrums stod; days Mouse, Pig, Bird, Monkey; trigrams kham
- **Bad:** Thu Sun Tue; smin drug, rgyal, me bzhi, khrums smad, skag; days Horse, Dragon, Ox, Sheep, Snake, Dog; trigrams li
- **Neither side:** Saturn (in brackets)
- **Not calculated:** rising signs; the sme ba; the nectar periods (good); the eight classes' and the nāgas' strokes, 'phung byed and the like (bad)
- The Snake day is bad for rain but good for nāga offerings, which this box does not count.
- **The scan (O10f) reads otherwise**, KP and KP2 alike (BDRC img. 59, KP2 img. 282): the good mansions are bra nye, mgo, lag, nabs so, gre, dbo, nag pa, sa ga (སག), lha mtshams, chu stod, mon gre, gro bzhin, chu smad, mon gru, khrums stod; the bad smin drug, rgyal, me bzhi, snron, snrubs, mchu, nam gru (ནུཾ), skag. The lists above have snar ma and sa ri good (the print has nabs so and sa ga there), snron, snrubs, mchu and nam gru good (the print has them bad), and khrums smad bad (not named).

### 56. Thread-cross and torma rites — མདོས་གཏོར་བྲབ་པ་ལ་བཟང་བ

KP img. 58, KP2 img. 281.

The box on the scan, KP BDRC img. 60 (O10f):

- Good half: «༄། །གཟའ་ཉི་ཕུར་སྤེན་མིག་ཚེས་༡༨ ༢༥ / ༢༨ ༢༧ སྐར་མ་གྲེ་ལག་མགོ་མཆུ་སྣྲུབས / ནག་ཆོད་ནབསོ་དབོ་ཐརྐ་སྣྲོན་དྲག་མདོས། / བྲཉེ་སྐྱས་འདེབས། ཉི་མ་འབྲུག་ཁོན་ས་སྒོ་བསྡམ་པ། / [ཁྲོུཾད?]…གནམ་སྒོ་དང་རྒྱལ་མདོས། ཟོན་དུས་སྦྱོར་སྡིག་ཆུ་སྲིན་ལུག་སེངྒེ / ཉ་ཀརྚ་༢ བདུད་དང་མ་གཤིན། ༣ ༤ ཀླུ་མདོས་བཟང་། ༥ རྒྱལ / ༧ གཟའ་བཙན་དམུ་མདོས་བཟང་། ཟིན་ཕུང་པིཥྚི་རཱ་ཧུ་ཀླུ་གཟའ་ནག་མོ / མིག་དམར་རཱ་ཧུའི་ཐུན་མཚམས། སྡེ་བརྒྱད་རྒྱུ་ཐེབས་དུས་ཕྱོགས་བཅས། ། (the qualifier between lines 3–5 «…ཁེན», machine)»
- Bad half: «༄། །གཟའ་སངས་ཟླ་ལྷག་ཚེས་༢༦ ༡༢ ༡༤ སྐར་མ་ / གྲོཞི་བྱིཞི་མཆུ་བོན་ཆོག་ངན། སྣར་ལྷཾས་མོནྲུ་མོནྲེ་ནུཾ་ / ཉི་མ་ཕག་བོན་ཆོག་ངན། སྟག་དུས་སྦྱོར་གླང་སྲང་པི་ལིང་སེ་བྱ་ / ཀུངས་མྱུལ་གནམ་ཟས་འཚོལ་ཕག་མོ་རྒྱུ་དམག་དཔོན་ཙང་ཀུན་གནས་ / ཕྱོགས་དབང་མོ་ཁྲག་སྒོར་ཙ་མུནྡི་ཁྱབ་འཇུག་རྒྱུ་དུས་མཐོང་བ་རྒྱུ་དུས་ནུབ་ / བྱང་དུ། ཤ་བ་རུ་རྒྱས་ངན། དཔལ་མོ་རྒྱུ་དུས་མདོས་རྣམས་དམིགས་གསལ་ལ༔ / ཞི་ལྗང་གནས་ཕྱོགས་འཛེམ།»

- **Good:** Sun Thu Sat Tue; dates 18, 25, 28, 27; gre, lag, mgo, snrubs, gro bzhin
- **Bad:** Fri Mon Wed; dates 26, 12, 14; snar ma, lha mtshams, mon gru, mon gre, nam gru
- **Neither side:** mchu (good, but bad for Bon rites); nag, chu stod, nabs so, dbo, tha skar, snron (good for fierce mdos); bra nye (for planting skyas); the Dragon day and the trigrams khon, khen (good for particular mdos); the Pig day (bad for Bon rites); the dates 2, 3, 4, 5, 7 with the spirits they suit
- **Not calculated:** rising signs; the spirits' and Rāhu's times and places
- The box names a kind of mdos for most of its entries; only the plain ones are counted.
- **The scan (O10f):** gro bzhin stands plain in the good half and in the bad half with byi bzhin and mchu as «བོན་ཆོག་ངན» (bad for Bon rites): like mchu, it counts for neither side. The bad half names the Tiger day plain after the Pig day's qualifier: bad, which the app does not have.

### 57. Honouring and petitioning — བསྙེན་བཀུར་ཞུ་གསོལ་བྱ་བ་ལ་བཟང་བ

KP img. 59, KP2 img. 282.

The box on the scan, KP BDRC img. 61 (O10f):

- Good half: «༄། །གཟའ་སངས་ཉི་ལྷག་༼ཕུར༽ སྐར་མ་ཐརྐ་ནབསོ / མོནྲུ་ནུཾ་ས་རི་མགོ་ལག་རྒྱལ་སྐག་མཆུ་མོནྲེ་ཆོད་སྣྲོན / ཉི་མ་ཁྱི་ཡོས་ཁེན་༥ དུས་སྦྱོར་བུ་གླང་སེངྒེ་སྡིག་ཞི་མདོ་བྲིས་པ»
- Bad half: «༄། །གཟའ་སྤེན་མིག་ཚེས་༤ ༨ ༩ / ༢༨ སྐར་མ་ཆུཾད་ཁྲོུཾད་སག་སྐྱེ་བ་སྲིད་པ / མ་རིག་མིང་གཟུགས་དབུལ་ཉི་ཕུང་ཟོར»

- **Good:** Fri Sun Wed; tha skar, nabs so, mon gru, nam gru, sa ri, mgo, lag, rgyal, skag, mchu, mon gre, chu stod, snron; days Dog, Hare; trigrams khen
- **Bad:** Sat Tue; dates 4, 8, 9, 28; chu smad, khrums stod, sa ga
- **Neither side:** Thursday (in brackets)
- **Not calculated:** the links; rising signs; zhi mdo; dbul, phung zor (bad)
- **The scan (O10f):** A small numeral ༥ follows the trigram khen in the good half: the sme ba 5, good, not in the table of 2026-10-10.

### 58. Disputes and lawsuits — ཁ་མཆུ་རྩོད་པ་ལ་བཟང་བ

KP img. 59, KP2 img. 282.

The box on the scan, KP BDRC img. 61 (O10f):

- Good half: «༄། །གཟའ་སངས་མིག སྐར་མ་ཐརྐ / ཆུཾད་ནབསོ་མོནྲུ་ནུཾ་མགོ་མཆུ / དུས་སྦྱོར་བུ་མོ་ཞི་མདོ་དགེ་བ»
- Bad half: «༄། །གཟའ་སྤེན་ཉི་ཟླ་ཕུར་ལྷག་ཚེས་༤ ༨ ༩ ༢༨ / སྐར་མ་ས་རི་ལག་རྒྱལ་སྐག་སྣྲོན་ལྷཾས་སྣྲུབས་ནག་སྲིད་པ / རྒ་ཤི་མ་རིག་མིང་གཟུགས་ཉི་མ་རྟ་ཕུང་ཟོར་དབུལ་ལག»

- **Good:** Fri Tue; tha skar, chu smad, nabs so, mon gru, nam gru, mgo, mchu
- **Bad:** Sat Sun Mon Thu Wed; dates 4, 8, 9, 28; sa ri, lag, rgyal, skag, snron, lha mtshams, snrubs, nag pa; days Horse
- **Not calculated:** the links; rising signs; zhi mdo, dge ba (good); phung zor, dbul and the like (bad)

### 59. Judging cases — ཞལ་ལྕེ་གཅོད་པ་ལ་བཟང་བ

KP img. 60, KP2 img. 283.

The box on the scan, KP BDRC img. 62 (O10f):

- Good half: «༄། །གཟའ་སངས་ཉི་མིག་༼ཕུར༽ སྐར་མ / ཐརྐ་ནབསོ་མོནྲུ་ནུཾ་ས་རི་ལག་རྒྱལ་ཆུཾད་སྐྱེད / རེག་པ་སྐྱེ་བ་ཉི་མ་གླང་ཁྱི་དུས་སྦྱོར་བུ་མོ་ཞི་མདོ»
- Bad half: «༄། །གཟའ་སྤེན་ཟླ་ལྷག་ཚེས་༤ ༨ ༩ ༡༨ སྐར / མ་མགོ་སྐག་ནག་སྣྲོན་ཁྲོུཾད་སྣྲུབས་མ་རིག་མིང་ཉི་མ / རྟ་ཕུང་ཟོར་དབུལ་ལག་ལ་སོགས་གདུག་རྩུབ་ཆེ་བའི་ས་བདག་སྤངས།»

- **Good:** Fri Sun Tue; tha skar, nabs so, mon gru, nam gru, sa ri, lag, rgyal, chu smad; days Ox, Dog
- **Bad:** Sat Mon Wed; dates 4, 8, 9, 18; mgo, skag, nag pa, snron, khrums stod, snrubs; days Horse
- **Neither side:** Thursday (in brackets)
- **Not calculated:** the links; rising signs; zhi mdo (good); phung zor, dbul and the harsh earth lords (bad)

### 60. Councils — མདུན་གྲོས་བྱེད་པར་བཟང་བ

KP img. 60, KP2 img. 283.

The box on the scan, KP BDRC img. 62 (O10f):

- Good half: «༄། །གཟའ་ཕུར་སངས་ཉི་རྒྱལ་གྲོས་བཟང་། ༼ཟླ༽ / སྐར་མ་སྣར་མགོ་སྐག་མོནྲུ་མྱེ་ནག་དབོ་མོནྲེ / ཁྲོུཾད་ཁྲུཾད་ཆུཾད་ནུཾ་ཉི་མ་རྟ་དུས་སྦྱོར་སེངྒེ་བུ་མོ / གཞུ་བུམ་པ་སྲང་བདུད་རྩི་ཐུན་མཚམས།»
- Bad half: «༄། །གཟའ་མིག་སྤེན་ཚེས་༡༢ སྐར་མ་ལྷཾས་བྱིཞི / སྣྲུབས་ཉི་མ་སྟག་སྤྲེལ་བྱ་ཁྱི་[…]་དུས་སྦྱོར་ལུག་ཀརྚ / སྲང་སྡིག་ཆུ་སྲིན་༼འཁྲིག༽ ཉི་ནུབ་རཱ་ཧུ་དྲ་ཆེན་པི་ལིང་ཧལྱི / ཀི་ཀང་དབུལ་ལོ་ཟླ་ཞག་ནག་ཕུང་ཟོར་སྦྱོར་བྱེད་རྟེན་འབྲེལ་སོགས་དོན་བསྟུན།»

- **Good:** Thu Fri; snar ma, mgo, skag, mon gru, me bzhi, nag pa, dbo, mon gre, khrums stod, khrums smad, chu smad, nam gru; days Horse
- **Bad:** Tue Sat; dates 12; lha mtshams, snrubs; days Tiger, Monkey, Bird, Dog
- **Neither side:** the Sun (good for royal councils); the Moon (in brackets)
- **Not calculated:** Abhijit (bad); rising signs; the nectar periods (good); ki kang, dbul, phung zor and the like (bad)

### 50. Ordination, teaching, maṇḍalas, study, empowerment, practice — རབ་བྱུང་འཆད་ཉན་དཀྱིལ་འཁོར་འདྲི་བ་དང་བསློབ་གཉེར་དབང་བསྐུར་སྒྲུབ་མཆོད་ལ་བཟང་བ

KP2 img. 276 (not in KP).

The box on the photograph, KP2 img. 278 (O10f), **machine reading**: Yigdzin-1 over the halves of the folio, compared with the photograph at 1.1× to 1.6× but not checked syllable by syllable:

- Good half: «༄། །གཟའ་ཉི་ཕུར་ཟླ་༼…རབ་བྱུང་ངན།༽ སངས་༼…༽ / རབ་བྱུང་འཆད་ཉན་ལ་བཟང་། ཚེས་༣ ༥ ༡༠ ༡༣ ༢༣ ༡༡ སྐར་མ་ས་རི་ས་ག་སྣར་ / མགོ་ལག་ནབསོ་རྒྱལ་མཆུ་སྨིན་མྱེ་ནག་མོནྲེ་མོནྲུ་ཆོད་༼ཁྲུཾད༽ / ལྷཾས་ཆུཾད་སྣྲོན་ནུཾ་ཐརྐ་བྲཉེ་ཁྲོུཾད་དབང་བཟང་། དབོ་འཆད་ཉན་བཟང་། རྣམ་ / ཤེས་ཚོར་བ་ཉི་མ་འབྲུག་ཕག་གིན་༥ དུས་སྦྱོར་ལུག་གླང་ཆུ་སྲིན་བུཾ་སེངྒེ་ / …དཀྱིལ་འཁོར་འདྲི་བར་ཐིག་ཞིབ་རྒྱས་བཻ་དཀར་ལྟར།»
- Bad half: «༄། །གཟའ་མིག་སྤེན་ཚེས་༡ ༢༢ […] ༡༥ སྐར་མ་སྐག་གྲོཞི་ / ཉི་མ་སྟག་ཕག་བསློབ་གཉེར་ངན། ལུག་གླང་དུས་སྦྱོར་འཁྲིག་གཞུ་བུམ་ / ཟིན་ཕུང་ཉི་ནག་བིཥྚི་འཕུང་བྱེད་ཀླུ་གཟའ་ལོ་ཟླ་ནག / ཚེས་གཉན་སོགས་»

- **Good:** Sun Thu; dates 3, 5, 10, 13, 23, 11; sa ri, sa ga, snar ma, mgo, lag, nabs so, rgyal, mchu, smin drug, me bzhi, nag pa, mon gre, mon gru, chu stod, khrums smad; days Dragon; trigrams gin
- **Bad:** Tue Sat; dates 1, 22, 15; days Sheep, Ox
- **Neither side:** the Moon and Mercury (in brackets: bad for ordination); Venus (in brackets: good for ordination and teaching); lha mtshams, chu smad, snron, nam gru, tha skar, bra nye, khrums stod (good for empowerment); dbo (good for teaching); skag and gro bzhin (bad for study); the Tiger day (bad for study); the Pig day (good, and bad for study)
- **Not calculated:** the links རྣམ་ཤེས, ཚོར་བ; rising signs; zin phung, nyi nag, Viṣṭi, 'phung byed, the Nāga planet and the like (bad); for drawing maṇḍalas the lines are laid out as the White Beryl teaches
- KP2 only. Two or three bad dates beside 1, 22 and 15 (read 4, 8, 9 by machine) are not clear on the photograph and are left out.

### 51. Dikes and protection against water — ཤྭ་རག་ཆུ་བསྲུང་སོགས་ལ་བཟང་བ

KP2 img. 277 (not in KP).

The box on the photograph, KP2 img. 279 (O10f), **machine reading**: Yigdzin-1 over the halves of the folio, compared with the photograph at 1.1× to 1.6× but not checked syllable by syllable:

- Good half: «༄། །གཟའ་ཉི་སངས་མིག་༼ཕུར་སྤེན༽ ཚེས་ / ༡༢ ༢༠ ༢༣ ༢༤ སྐར་མ་སྨིན་རྒྱལ་ / གྲེ་དབོ་མྱེ་ས་རི་སག་སྐག་ཁྲོུཾད་ / མོནྲེ་ཉི་མ་ཁྱི་དུས་སྦྱོར་ཀརྚ་ལུག་སྲང་བུ་མོ་སེངྒེ་སྒྲ་གཅན་རྒྱུ་ / ཐེབས་དང་བསྟུན། ཕྱོགས་གཙང་དུ་ལོ་བཟང་ནས་ཐོག་མར་བརྩི་བ་བཟང་།»
- Bad half: «༄། །གཟའ་ཟླ་ལྷག་ཚེས་༣ ༡༧ / ༢༢ ༢༧ སྐར་མ་ཆུཾད་ཁྲུཾད་ཆོད་ལག / མཆུ་ནུཾ་ནག་སྣྲོན་སྣྲུབས་སྐར་མ་འདི་ནི་ཤིན་ཏུ / ངན། དུས་སྦྱོར་བུམ་པ་གཞུ་…འཕུང་བྱེད་ / བསྟན་མ་བྱི་ལམ་སྔོ་རྒྱུ་སོགས།»

- **Good:** Sun Fri Tue; smin drug, rgyal, gre, dbo, me bzhi, sa ri, sa ga, skag, khrums stod, mon gre; days Dog
- **Bad:** Mon Wed; chu smad, khrums smad, chu stod, lag, mchu, nam gru, nag pa, snron, snrubs
- **Neither side:** Thursday and Saturn (in brackets)
- **Not calculated:** the dates of both halves (not read with confidence on the photograph); Abhijit (bad); rising signs; Rāhu's course, bstan ma, byi lam and the like
- KP2 only. The bad mansions end «དེ་ནི་ཤིན་ཏུ» (very bad), an emphasis. The heading's ཤྭ་རག is the shwa rags of the lunar dates (a dike against flash floods), and the box is listed under that wording.

### 52a. Cutting hair and nails — བཟོ་དང་སྐྲ་སེན་བྲེག་པར་བཟང་བ (སྐྲ་སེན)

KP img. 55, KP2 img. 278.

The box on the scan, KP BDRC img. 57 (O10f):

- Good half: «༄། །གཟའ་སངས་ཟླ་དངུལ་ཆུའི་ལས་རུང་། ལྷག་ / དངུལ་ལས་སྐྲ་སེན་བྲེག་པ་བཟང་། སྐར་མ་སྨིན་མགོ་ནག / མཆུ་སག་ལྷཾས་ཁྲུཾད་བཟོ་སྐྲ་སེན་བཟང་། ཐརྐ་ལག / མྱེ་ནུཾ་བྱིཞི་སྐྲ་བཞར་བཟང་། ཁྲོུཾད་སྐྲ་བཀྲུ་ངན་བཟོ་བཟང་། མིང་ལེན་པ་ / མ་རིག་ཉི་མ་ཁྱི་གླང་ཁམ་ལྕགས་བཟོ་བཟང་། ལི་ལྕགས་དང་རྫ་ཞུན་བཟང་། / དུས་སྦྱོར་ལུག་གླང་འཁྲིག་སེངྒེ་བུ་མོ་ཉ་༼བུམ་པ་གཞུ༽»
- Bad half: «༄། །གཟའ་ཉི་གསེར་ཤིང་པགས་རུས་བཟོ་ / བ་བཟང་། མིག་བྱུ་རུ་རལ་གྲི་གསེར་བཟོ་བ་བཟང་། ཕུར་རིན་པོ་ཆེ་དང་ / ཤིང་བཟོ་བ་བཟང་། སྤེན་སྤྱིར་ངན་ཀྱང་ལྕགས་སོགས་གཞིག་པའི་ལས་བཟང་། / སྐར་མ་སྣར་སྣྲོན་ཆུཾད་དབོ་མོནྲུ་བྲཉེ་རེག་པ་རྒ་ཤི་ / ཉི་མ་སྤྲེལ་འབྲུག་དུས་སྦྱོར་ཆུ་སྲིན་ཀརྚ་སྡིག་སྲང་ཚེས་གཉན་རྒྱུ་ / དུས།»

- **Good:** Fri Mon Wed; smin drug, mgo, nag pa, mchu, sa ga, lha mtshams, khrums smad, tha skar, lag, me bzhi, nam gru
- **Bad:** Sun Tue Thu Sat; snar ma, snron, chu smad, dbo, mon gru, bra nye; days Monkey, Dragon
- **Neither side:** khrums stod (good for a craft, word not read)
- **Not calculated:** Abhijit (good for shaving); the links; rising signs; the earth lords' days (bad)
- Box 52 joins crafts and cutting hair and nails, split here in two. The Moon and Mercury are good «for silver work and cutting hair and nails» (the Moon's mercury work only acceptable); smin drug, mgo, nag pa, mchu, sa ga, lha mtshams and khrums smad «for crafts and hair»; tha skar, lag, me bzhi and nam gru for shaving the head. Each bad weekday is good for one craft (the Sun gold, wood, leather and bone; Mars coral, swords and gold; Jupiter jewels; Saturn breaking up iron), so it stays bad for the hair. The White Beryl's weekday verses avoid cutting hair on the same four days.
- **The scan (O10f) reads otherwise:** the Moon is only «དངུལ་ཆུའི་ལས་རུང» (acceptable for mercury work); silver work and cutting hair and nails are Mercury's alone («ལྷག་དངུལ་ལས་སྐྲ་སེན་བྲེག་པ་བཟང»). The box does not name the Moon for hair and nails; WB's own work 50 (vol. 2, p. 420, img. 428, [white-beryl-ch34.md](white-beryl-ch34.md)) does («ཟླ་བ(༢)བུད་ཤིང་དངུལ་ཆུའི་ལས། །སྐྲ་དང་སེན་མོ་འབྲེག་པ་བཟང»), so Monday stays good for 52a by WB. khrums stod is «སྐྲ་བཀྲུ་ངན་བཟོ་བཟང» (bad for washing the hair, good for crafts). Jupiter is good for jewels and woodwork.

### 52b. Crafts — བཟོ་དང་སྐྲ་སེན་བྲེག་པར་བཟང་བ (བཟོ)

KP img. 55, KP2 img. 278.

- **Good:** Fri; smin drug, mgo, nag pa, mchu, sa ga, lha mtshams, khrums smad; days Dog, Ox; trigrams kham, li
- **Bad:** snar ma, snron, chu smad, dbo, mon gru, bra nye; days Monkey, Dragon
- **Neither side:** the Moon and Mercury (for silver work only); the Sun, Mars, Jupiter and Saturn (bad, but each good for one craft); tha skar and lag (good for woodwork)
- **Not calculated:** the links; rising signs
- The crafts half of box 52 (see 52a). The Dog and Ox days and the trigram kham are good for ironwork, li for iron and pottery: kept good, since ironwork is a craft.

### 53a. Military training — དམག་རྩལ་གཤོམ་དང་ཆོ་ལོ་སོགས་བྱེད་པར་བཟང་བ (དམག་རྩལ)

KP img. 56, KP2 img. 279.

The box on the scan, KP BDRC img. 58 (O10f):

- Good half: «༄། །གཟའ་སྤེན་མིག་སངས་…རྩེད་མོ་བཟང་། ཚེས་ / [༨ ༡༡ ༡༨ ༡༥] ལྷོ་ནུབ་ངན། [༡ … ༧ … ༢༥] …ཤར་ངན། / [༣ ༩ ༡༠] ལྷོ་ངན། སྐར་མ་ཐརྐ་མགོ་ལག / སྐག་ཆོད་བྱིཞི་མྱེ་ལྷཾས་དབོ་མོནྲུ་སྣྲོན་སྣྲུབས་ཁྲོུཾད་ས་ག་མཆུ་ / འདུ་བྱེད་རྣམ་ཤེས་རེག་པ་ལེན་པ་ཉི་མ་སྟག་འབྲུག་གླང་ཟོན་དུས་སྦྱོར་ / གཞུ་ཀརྚ་ལུག་སེངྒེ་འདིར་ཞིབ་རྩིས་བྱེད་ན་འཁོར་ལོའི་རྣམ་གྲངས་བརྩི་… / …ཚུལ་སོགས་ཆེས་ཟབ་ཇི་སྙེད་མཆིས་ཀྱང་ཀུན་སྤྱོད་མ་ཡིན་པས་འདིར་མི་གསལ།»
- Bad half: «༄། །གཟའ་ཉི་ཕུར་ཟླ་ལྷག་ཚེས་[༤ ༡༤ ༢༡ ༢༠] / ༢༨ ༣༠ འདི་སོགས་དམག་གི་གནམ་སྒོ། སྐར་མ་ནབསོ་ནག་ནུཾ་གྲེ་ / བྲཉེ་སྨིན་སྣར་གྲོཞི་དགྲ་ལ་རྒོལ་བ་བཟང་བའོ། །སྲེད་པ་སྲིད་པ་ཉི་མ་ / སྦྲུལ་ཡོས་ལུག་ཁྱི་སྤྲེལ་རྩེད་མོ་བཟང་། ལི་རྩེད་མོ་བཟང་། [དྭ་ཁམ]་ / དུས་སྦྱོར་བུ་མོ་ཉ་སྡིག་བུམ་གནམ་འཕུང་བྱེད་ཐེ་སེ་སེ་བདུད་ཉི་ནག་ཚེས་གཉན་… / པི་ལིང་བུམ་སྟོང་ཝང་གིང་བྱི་ལམ་ཧལྱི་དུས་མཚན་ཐེབས་ཕྱོགས་ཙང་ཀུན་ལྷ་དགུ་… / རང་རྟ་ཟིན་ཕུང་གདོང་ལ་ཟླ་རླུང་སེ་བྱི་གཉན་ཁྲ་ཀིང་སེ་ལོ་སེ་ཤར་བདུད་ཐུན་མཚམས།»

- **Good:** tha skar, mgo, lag, skag, chu stod, me bzhi, lha mtshams, dbo, mon gru, snron, snrubs, khrums stod, sa ga, mchu
- **Bad:** Sun Thu Mon Wed; days Tiger, Dragon, Ox; trigrams zon
- **Neither side:** the dates, each tied to a direction (bad to the south-west, the east, the south …, the gates of war); nabs so, nag pa, nam gru, gre, bra nye, smin drug, snar ma, gro bzhin (bad, but good for attacking an enemy)
- **Not calculated:** Abhijit (good); the links; rising signs; the wheel's numbers, pi ling, the earth lords and the like (bad)
- Box 53 joins military training with dice and games, split here in two: the plain mansions and the bad weekdays and days hold for both.

### 53b. Dice and games — དམག་རྩལ་གཤོམ་དང་ཆོ་ལོ་སོགས་བྱེད་པར་བཟང་བ (ཆོ་ལོ)

KP img. 56, KP2 img. 279.

- **Good:** Sat Tue Fri; tha skar, mgo, lag, skag, chu stod, me bzhi, lha mtshams, dbo, mon gru, snron, snrubs, khrums stod, sa ga, mchu; days Snake, Hare, Sheep, Dog, Monkey; trigrams li
- **Bad:** Sun Thu Mon Wed; days Tiger, Dragon, Ox; trigrams zon
- **Neither side:** as 53a
- **Not calculated:** as 53a
- Saturn, Mars and Venus, the five day animals and the trigram li are «རྩེད་མོ་བཟང» (good for games).

## Boxes not built

- **4, 14, 36** and the plough woodcut (img. 35) are charts counted from the
  Sun's mansion or by the hour, not lists for the day; **13** is a few lines
  of prose on a child's first outing.
- **47** (averting rites, img. 53): nearly every entry names the kind of
  rite it suits (gentle, fierce, *zor*), and together the kinds cover every
  weekday, so nothing counts for the rite as such.
- Their Tibetan is not written out (O10f, 2026-10-14): they hold no list
  the app uses.

## Split and joined boxes (2026-10-05)

Four boxes that qualify most of their entries were built as well, read on
the scans the same way:

- **50** and **51** are in KP2 only (img. 276–277). Box 50 joins ordination,
  teaching, drawing maṇḍalas, study, empowerment and practice under one
  heading; its plain entries hold for all of them and are one list, while
  what it names for one of them alone counts for neither side. Box 51 is
  the *shwa rag* of the lunar dates, a dike against flash floods, and is
  listed under that wording; its dates are not clear on the photograph.
- **52** joins crafts with cutting hair and nails, and **53** military
  training with dice and games. Each is split into two lists: an entry the
  box qualifies for one half ("good for silver work and cutting hair",
  "good for games") counts for that half, and the plain entries for both.

## The sme ba and the links (read 2026-10-10)

The boxes' sme ba numbers and links, left out on 2026-10-04 as "not
calculated", were read again on KP (2550 px) and, where KP is unclear,
KP2, for the date's sme ba and the day's link (SPEC §5.11, ROADMAP
T2.25). The digits were told apart by the dates printed in the same
boxes (box 16's bad 6, 7, 8, 9, 10; box 41's 28, 29, 30): 8 is the open
angle, 9 the rounded hook. The half a number or link stands in gives its
side; a box's "otherwise as box N" carries the links like any other kind
(11 as 10, 31 as 16a).

| Box | Good | Bad |
| --- | --- | --- |
| 5 putting on new clothes | links rnam shes, srid pa, sred pa | links ma rig, 'du byed, ming, reg pa, tshor ba, len pa |
| 7 walls and forts | links 'du byed, ma rig | sme ba 9 |
| 10 feasts, 11 food and brewing | links ma rig, rnam shes, tshor ba, sred pa | |
| 15 trade | | sme ba 9 |
| 16a receiving wealth, 31 dogs | links ma rig, srid pa, tshor ba ("good for taking in"); 16a sme ba 9 | 31 sme ba 5 |
| 17 auspicious work | link ming, sme ba 8 | |
| 20 virtuous acts for the living | links ma rig, rnam shes, tshor ba, srid pa, sred pa | link rga shi |
| 22 sowing | link tshor ba | |
| 23 gifts and dowries | sme ba 6 | |
| 27 springs, wells, canals | | sme ba 3 |
| 32 prosperity, Bon rites | sme ba 6, 9 | |
| 34 medicine | links ming, rnam shes, len pa; sme ba 9 | |
| 35 bloodletting | | sme ba 2, 7 |
| 37 bathing | links skye mched, srid pa, 'du byed, skye ba, ma rig, rnam shes | links ming, tshor ba, sred pa; sme ba 1, 6 |
| 38 divination | links srid pa, rnam shes, tshor ba | link ma rig |
| 39 enthronement | links rnam shes, skye mched, skye ba | links rga shi, ma rig |
| 43 ornaments | link 'du byed | |
| 44 suppressing *sri* | sme ba 3 | |
| 45 funerals | | sme ba 3, 5, 9 |
| 46 supports, temples | links 'du byed, skye mched, ming, rnam shes; sme ba 5 | |
| 49 consecration | | link skye mched, sme ba 6 |
| 52a hair and nails | | links reg pa, rga shi |
| 52b crafts | links ming, len pa, ma rig (with ironwork) | links reg pa, rga shi |
| 54 composing, poetics | link 'du byed, sme ba 5 | |
| 57 honouring | | links skye ba, srid pa, ma rig, ming |
| 58 lawsuits | | links srid pa, rga shi, ma rig, ming |
| 59 judging | links reg pa, skye ba | links ma rig, ming |

Left out: "skye" alone, which may be birth or the sense sources (boxes 5
and 7, and box 20's first good link); box 46's ma rig, «good for building
stupas», a qualifier; box 55's numbers (KP and KP2 unclear); box 53's
links, whose half the scan does not settle; box 50 (KP2 only); box 48 and
60 name the links or sme ba without saying which. Boxes 2, 3, 6, 8, 9, 12,
18, 19, 24–26, 33, 40–42, 61–65 name none. `ElectionalTest` holds the
table.

## The earth lords and the almanac's days in the boxes (RD5)

Read 2026-10-12 for ROADMAP RD5: which of the earth lords (*sa bdag*) that
RD3 and RD4 identify in WB ([year-month-earth-lords.md](year-month-earth-lords.md))
the *kun phan me long*'s boxes name, and for which works. KP is BDRC I4CZ65599,
KP2 I3CN12074. **Image numbers in this section are BDRC's own labels, the
file names of the scans; the sections above count two fewer** (box 2 is on
KP img. 23, which they call 21; KP2 likewise two more than they say). KP2 is
the same text in a second print, one folio often a line or two ahead.

**How it was read.** Yigdzin-1 over every folio of KP from img. 23 to 66
(boxes 2 to 65) and of KP2 from img. 240 to 282; again over the left 55 % and
the right 55 % of each KP folio that carries a name, enlarged 1.5×, which
finds lines the whole folio loses (box 39's whole line of names, img. 49, is
missing from the folio reading and present in the halves and in KP2). The
names agree in the three readings. On the scan itself, at 2× to 4×, the names
were seen in boxes 10, 13, 16, 17, 19, 20, 23, 25, 26, 39 (img. 28, 31, 33–36,
38–40, 49) and, less surely, 42 (img. 51); the running words around them in every box, and every name in
the other boxes, are the machine reading, marked "(machine)" below. MITRA was
tried on whole KP folios and looped on digits (2,400 to 4,100 characters of
«༡༠ ༡༠ …» for a folio of 900), so it is no witness here. agy was not used.
The small numerals under the three «ཟླ་ཚེས» of box 16 are too small to be
read at the scan's size and are left out.

The whole text of each box, read on the scan (O10f, 2026-10-14), now
stands in its section above; the runs below are kept as RD5 read them.

### How a box names them

Each box is two halves side by side, the good half first and the bad half
(printed «ངན་པ», or «ངན» at the end of its first line) second. The earth
lords and the Chinese almanac's days stand in **the bad half**, in its last
line or two, as a run of names without a verb, and the box's rule on
"what is not calculated" (above) is this run. The one box that says
anything about them in its good half is box 56 (below); box 13 is prose
without halves, and box 53's closing prose says their reckoning is not given. No box says what an earth lord does, how
it moves or how it is mended: **the box gives the name and the side, nothing
more.** What a name means is in WB; the identifications follow.

### Who the names are

| KP | Wylie | In RD3 / RD4 and the other topic files |
| --- | --- | --- |
| ཐེ་སེ | the se | the king Tese, the year's no. 1 (p. 177), and Grumbu of the *the se* spirits, the month's no. 2; KP does not say which |
| ཐེ་ཁྱིམ | the khyim | the great queen Tekhyim, year no. 2 |
| ཧང་ཕན (ཧང་བན) | hang phan | Hangphen the yellow, the watchman who leads the *hal khyi*, year no. 7 (the consort Harphen or Hangne, no. 3, is another) |
| ཙང་ཀུན | tsang kun | the minister Tsangkun, year no. 5 (and the three Tsangkun, nos. 20–22; the month's no. 41) |
| སེ་བྱི | se byi | Sebyi, the treasure guardian, year no. 8 |
| སེ་ཤར | se shar | Seshar, the bodyguard, year no. 9; the hearth god Seshar of the month (no. 48, scattered no. 6) |
| སེ་བ་བླ་མཁྱེན | se ba bla mkhyen | the life-spirit knower of the *se*, year no. 6 |
| རང་རྟ, ཏེ་སོ, ཀུངས་མྱུལ | rang rta, te so, kungs myul | Seba Rangta no. 10, Teso no. 4, Kungnyul no. 14 of the year |
| པི་ལིང | pi ling | Pilang, year no. 25; Piling the poisonous, month no. 30 (and no. 35) |
| ཟླ་ཀླུང | zla klung | the month's river (*zla klungs*), month no. 3 |
| ཟིན་ཕུང | zin phung | Zinphung the black, year no. 24, month no. 31 |
| ཧལ་ཁྱི | hal khyi | the year's black *hal khyi* (no. 16) and the month's |
| སེ་བདུད | se bdud | the *se* demon, scattered no. 2 of the month |
| ཚེས་གཉན | tshes gnyan | the fierce date of nine corpses, scattered no. 1 of the month |
| འཕུང་བྱེད, ཀླུ་གཟའ་ནག་མོ, གཅེར་བུ | 'phung byed, klu gza' nag mo, gcer bu | the earth's destroyer, the nāga planet and the naked stump-hand, the three of the five further ones of the hidden twelve (RD4) |
| ས་ཡི་ལྷ་མོ་བསྟན་མ | sa yi lha mo bstan ma | the earth goddess Tenma, month no. 20 |
| བུམ་སྟོང, བྱི་ལམ | bum stong, byi lam | the empty vase and the mouse road, among the month's circling lords |
| ཀི་ཀང, ཉི་ནག, གནམ་སྟོང | ki kang, nyi nag, gnam stong | not earth lords of RD3/RD4: *ki kang* is WB's no. 16 of the lords that move by date ([earth-lord-courses.md](earth-lord-courses.md)), *nyi nag* the great black day ([great-black-day.md](great-black-day.md)), *gnam stong* the date of each month so named (same file) |
| དབུལ་ལག, ཕུང་ཟོར | dbul lag, phung zor | not in RD3/RD4; *dbul lag* is read as one name (see box 25), and WB's own «དབུལ་ལག་འཆར» of the burning dates ([burning-dates.md](burning-dates.md)) holds it |

Of the ten names of the roadmap row, «ཟླ་ཀླུང» is named only in box 16
(box 53 has «ཟླ་རླུང»), and «གནམ་སྟོང» only in the short form «གནམ» (box 23,
img. 38; box 10's «གནམ་ཞག་ནག» is another name). The full word is on KP
img. 47, in the verse of box 36, as the day on which the soles of the feet
hold the *bla gnas*: not as an earth lord.

### The boxes, one by one

"Bad half" below is the half printed «ངན». The Tibetan is the run of names
as read, with the neighbouring words that make it a sentence; the English is
only which names, in the print's order.

- **10 Feasts, vessels** (KP img. 28, KP2 247), names seen on the scan.
  «ཉི་མ་བྱ་ཡོས་ ཀིང་ཟོར་ནག་ དབུལ་ ཧལ་ཁྱི་ ལོ་ཟླ་… པི་ལིང་ གནམ་ཞག་ནག་ ཐེ་སེ་ ཙང་ཀུན་ ཧང་ཕན་ སེ་བྱི་ སེ་ཤར་ ཀུངས་མྱུལ་ ས་རྒྱལ་ འབྲུག་ལྷ་ ལྷ་ལྔ་ བར་གནམ་ དྲ་ཆེན་ རཱ་ཧུ་རྒྱུ་དུས་ གཟའ་རྒོད་སོགས»
  Bad half, after the Bird and Hare days: *dbul*, the *hal khyi* of year and
  month, *pi ling*, *gnam zhag nag*, the se, tsang kun, hang phan, se byi,
  se shar, kungs myul, the earth king, the dragon god, the five gods, Rāhu's
  course and the planet Lhagö (*gza' rgod*) and the like. The words before
  «ཀིང་ཟོར» are machine reading.
- **12 Setting out** (Henning's; img. 29, KP2 248), names machine.
  «དུས་སྦྱོར་ ཀརྚ་ སེངྒེ་ སྲང་ བུམ་པ་ སྡིག་ ལུག་ གླང་ འཁྲིག་ འཇིག་ཉི་ ཕུང་ཟོར་ པི་ལིང་ སའི་འཕུང་བྱེད་ ཉི་ནབ། འགྲོ་མིའི་བདུད་གཅོད་སོགས།»
  *phung zor*, *pi ling* and the earth's destroyer among what the traveller
  avoids; the rest of the line not settled.
- **13 A child's first outing** (img. 31, KP2 250), the whole box read on
  the scan at 2× (O10f, 2026-10-14; the first line's middle less surely):
  «༄། ༼༡༣༽ །ཞར་བྱུང་[ཕྲུའུ]་སྒོ་འདོན་ལ། །གོང་གི་སྐྱེད་དམིགས་གསལ་ལ་ཉི་མ་བྱུང་བ་གྲོང་གི་ཡར་ངོ་བཟང་། །གསུམ་གསུམ་པའི་གནམ་སྒོ་མི་དགེ། / [སྐྱེར]་ཁའི་བདུད་གཉན་འདྲེ་ལྔ་བདུད་བཅད། ཐེ་སེ་ཧང་བུ་སེ་ཞིག་ལོ་ཟླའི་ས་བདག་ངན་པ་སྤྱི་དང་། ལོ་ཟླ་ཞག་དུས་ལ་ཕྱོགས་སུ་རྒྱུ་བའི་ཧལ་ཁྱི་…… / གཅིག་དཀར་རཱ་བརྒྱད་གདོང་ཐུག །ཁྱད་པར་ཆུང་སྲི་བུ་ཟན་བཅས་ལ་འཛེམ། །»
  By the way, for taking a child out of the door: beside the particulars
  above, the waxing half of the month is good, and the sky doors of the
  threes are not virtuous; then the demons, *gnyan* and five *'dre* of the
  first outing (the first word read སྐྱེར here, སྤེར in the RD5 reading),
  the se, *hang bu* (hang phan), *se zhig*, the bad earth lords of year
  and month in general, and the *hal khyi* that goes by direction in year,
  month, day and hour (a dotted gap in the print follows), met head on;
  above all the *chung sri* and the child-eaters (*bu zan*) are avoided.
  «སྐྱེར་ཁ» and «འདྲེ་ལྔ» are not identified.
- **15b/16 Taking and giving wealth** (img. 33, KP2 252), names seen on the
  scan. Bad half of box 16: «… ཉི་མ་སྦྲུལ་ དབུལ་ / ཉི་ནག་ ཀི་ཀང་ སེ་བྱི་ ཧང་ཕན་ ཟླ་ཀླུང་ ཟླ་ཚེས་ ཟླ་ཚེས་ ཟླ་ཚེས་ [small numerals] འདི་…»
  The Snake day (སྦྲུལ on the scan, O10f; read སྤྲུལ, the Monkey, before), *dbul*, *nyi nag*, *ki kang*, se byi, hang phan, the
  month's river, then three «ཟླ་ཚེས» (month and date) each with its small
  numerals: the dates of the months on which they stand, not read. The
  last word is cut by the print's worn edge (not read).
- **17 Auspicious rites** (img. 34, KP2 253), names seen on the scan.
  «བིཥྚི་ ཀི་ཀང་ རཱ་ཧུ་ པི་ལིང་ དབུལ་ ཧལ་ཁྱི་ ལོ་ཟླ་ཚེས་ … སོགས་ནག་པའི་དུས་ ཕུང་ཟོར་སོགས།»
  Viṣṭi, ki kang, Rāhu, pi ling, dbul, the hal khyi, the date of the year
  and the month, the black times and phung zor.
- **18 Planting** (img. 35, KP2 254): «སྡིག་ བུ་མོ་ སྲང་ སེ་བདུད་རྒྱུ་དུས་ ཉི་ནག་» the *se* demon's
  times and *nyi nag*.
- **19 Peace between enemies** (img. 35, KP2 254), names seen on the scan.
  «ཉི་མ་རྟ་ དུས་སྦྱོར་ ཀརྚ་ ཆུ་སྲིན་ སྡིག་ བུམ་སྟོང་ སེ་བྱི་» the Horse day,
  *bum stong*, *se byi*.
- **20 Virtuous acts for the living** (img. 36, KP2 255), names seen on the
  scan. «བིཥྚི་ ཀི་ཀང་ པི་ལིང་ ཉི་ནག་ ཕུང་ཟོར་ ཧལ་ཁྱི་ དྲ་[ཆེན] རཱ་ཧུ་ དབུལ་ལག་ ལོ་ཟླ་ཞག་ནག་སོགས།»
- **21 Manuring, breaking oxen** (img. 36, KP2 255): «… ཕོ་ཉ་ ཟས་འཚོལ་ ཞག་ནག་ ས་ཡི་ལྷ་མོ་བསྟན་མ་གནས་ཕྱོགས། ལམ་སྔོ་ པི་ལིང་ དབུལ་ ཉི་ལུས་ དུར་ཞུགས་དང་ ལྔ་སེར་འཆར་ཕྱོགས།»
  Tenma's place, the blue mouse road (*lam sngo*, read in KP as «ལམ་ལྡོ»,
  KP2 «ལམ་སྔོ»), *pi ling*, *dbul*.
- **22 Sowing** (img. 38, KP2 259): «… ཉི་ནག་ ཐེ་སེ་ཁྱིམ་ … གྲུམ་བུ་ ས་རྒྱལ་ འབྲུག་ལྷ་ འཕུང་བྱེད» *nyi nag*,
  the se and the khyim, Grumbu, the earth king, the dragon god, the
  destroyer (machine).
- **23 Gifts, dowries** (img. 38, KP2 259), names seen on the scan.
  «ཉི་མ་ཡོས་ཁྱི་ལུག་སྦྲུལ་སྟག་ ཁོན་ སེ་ཤར་ གནམ་ པི་ལིང་ … ཙང་ཀུན་ ཕོ་ཉ་ ཧལ་ཁྱི་ ཉི་ནག་ གཟའ་བདུན་ཟས་འཚོལ་ ལྔ་དགུའི་ཕྱོགས་བཅས།»
  Hare, Dog, Sheep, Snake, Tiger days and the trigram *khon*, then se shar,
  *gnam* (short for *gnam stong*), pi ling, tsang kun, the messenger (*pho
  nya*), the hal khyi, nyi nag, the seven planets' food-seeking and the
  places of the five and the nine, all in the bad half («ཤིན་ཏུ་ངན» stands
  in its first line).
- **25 Storehouses** (img. 39, KP2 260), seen on the scan.
  «དུ་སྦྱོར་ ཀརྚ་ སྡིག་ དབུལ་ལག་ སོགས་ངན།» *dbul lag* and the like are bad;
  the two syllables are one name, as in boxes 20, 59 and 62.
- **26 Banners** (img. 40, KP2 261), seen on the scan.
  «… སྲང་ ཉི་ནག་ བིཥྚི་ འཕུང་བྱེད་ ནག་མོ་ རྒྱུ་དུས་སོགས་འཛེམ།» *nyi nag*, Viṣṭi,
  the destroyer, the black one's times: to be avoided.
- **27 Springs and wells** (img. 40, KP2 261): «ཉི་མ་ཡོས་འབྲུག་སྦྲུལ་ཕག་ དུ་སྦྱོར་ ལུག་ སེངྒེ་ ཉི་ནག་ བྱི་ལམ་…» *nyi nag*, the mouse road.
- **28 Feeding horses** (img. 41, KP2 262): «ཀརྚ་ བུམ་ ཧལྱི་བ་ ཕུད་བྱེད་ རཱ་ཧུའི་གདོང་ རང་རྟ་ སེ་བ་ བུམ་སྟོང་ སེ་ལོ་» the
  *hal khyi*, the destroyer, Rāhu's face, *rang rta* (Seba Rangta), *se ba*,
  *bum stong*, *se lo*.
- **31 Keeping dogs** (img. 42, KP2 263): «ངན་པ། ཧང་ཕན་ཕྱོགས་ ཧལ་ཁྱི་ ཟས་འཚོལ་དང་ གནས་ཕྱོགས…» hang phan's quarter, the
  *hal khyi*, the food-seeking and the place (machine).
- **34 Medicine** (img. 43, KP2 264): «… ཀརྚ་ དབུལ་ བདུད་རྩི་ཐུན་མཚམས་…» *dbul* bad.
- **39 Enthronement** (img. 49, KP2 270), names seen on the scan.
  «དུས་སྦྱོར་ ཆུ་སྲིན་ སྡིག་ སེ་བྱི་ པི་ལིང་ ཙང་ཀུན་ ཐེ་སེ་ … སེ་བ་བླ་མཁྱེན་ འཕུད་བྱེད་ ནག་མོ་རྒྱུ་དུས་»
  se byi, pi ling, tsang kun, the se, the *se ba bla mkhyen*, the destroyer
  and the black one's times.
- **42 Giving and taking a bride** (Henning's; img. 51, KP2 272), seen at 2×,
  the longest run. «… བར་གནམ་ལོ་ཟླ་ ཉི་ནག་ གཡུ་མཛོད་ བིཥྚི་ འཕུང་བྱེད་ ལྷ་ལྔ་དགུ་ པི་ལིང་ ཀི་ཀང་ ས་ཡི་ལྷ་མོ་བསྟན་མ་ གནམ་སྦྱོར་ གཟའ་བདུན་ ཕོ་ཉ་ … ཀླུ་གཟའ་ དབུལ་ སེ་བྱི་ ཧང་ཕན་ ཧང་ཕན་སེར་པོ་ རང་རྟ་ ཏེ་སོ་ ཀུངས་མྱུལ་ … ཐེ་ཁྱིམ་ ཧལ་ཁྱི་ ཝང་ཕན་ སེ་བ་ ཕུང་ཟོར་ བག་མའི་ལོ་ལག་ཙ་ཀྲ་སོགས་ ཀྱི་ ལྷ་དྲུག་གནས་ཕྱོགས།»
  It names the year and month, the mid-sky, *nyi nag*, the turquoise
  treasure (*gyu mdzod*), Viṣṭi, the destroyer, the five and the nine,
  *pi ling*, *ki kang*, Tenma, the nāga planet, *dbul*, se byi, hang phan and
  Hangphen the yellow, Rangta, Teso, Kungnyul, the khyim, the hal khyi, the
  *se ba*, *phung zor*, and the six gods' places of «the bride's year, the
  wheel of the hands» («བག་མའི་ལོ་ལག་ཙ་ཀྲ», machine; KP2 prints the last word differently, not settled), as what the bride's
  business must mind. The box ends «གལ་ཆེ་ཞིབ་རྩིས་རྟོགས་དཀའི་རིགས་འདིར་མི་གསལ»:
  the exact reckoning of the important, hard-to-grasp kinds is not given here.
  This is the passage the bride's departure (ROADMAP P3) needs.
- **44 Suppressing *sri*** (img. 52, KP2 273): «… ས་འོག་རིམ་དགུའི་ས་བདག་བརྟག།» the earth lords of the nine levels
  under the earth are to be looked into (RD3's nine).
- **45 Funerals** (img. 53, KP2 274): «ཉི་མ་སྟག་སྤྲེལ་ཁྱི་ ལོ་ཟླ་ཞག་དུས་ཀྱི་ས་བདག་ རྒྱུ་དུས་ཕལ་ཆེར་འཛེམ།»
  the earth lords of the year, month, date and hour, and their times of
  moving, mostly to be avoided.
- **46 Temples, stupas** (Henning's; img. 54, KP2 275): «བིཥྚི་ བར་ ཧལྱི་ ལོ་ཟླ་ཞག་ནག་སོགས། པི་ལིང་ ཕོ་ཉ་ དབུལ་ འཕུང་བྱེད་ … ཀི་ཀང་»
- **47 Averting** (img. 55, KP2 276): «… འཇོམས་དང་ གདུག་ཅན་རྣམས་ གནས་ཕྱོགས་དང་ རྒྱུ་དུས་ གདོང་» the harsh
  earth lords (*sa bdag gdug can*) and the directions and times of the
  *hal khyi*; the good half names one rite for them.
- **48 Fire offering** (img. 56, KP2 277): «ཟིན་ཕུང་ གཞན་ཡང་ ས་བདག་ རྒྱུ་དུས་སོགས་ངན།»
- **49 Consecration** (img. 56, KP2 277): «… ཉི་ནག་ ཀླུ་གཟའ་ནག་མོ་ ཚེས་གཉན་རྒྱུ་སོགས་ བིཥྚི་ འཕུང་བྱེད་ དབྱུལ་པིང་ བར་ག་ནཾ་ ཧལྱི་ གཅེར་བུ།»
- **52 Crafts, hair** (img. 57, KP2 280): «… སྲང་ ཚེས་གཉན་ རྒྱུ་…»
- **53 Military training, games** (img. 58, KP2 281), the prose that ends the
  box, machine: «གནམ་འཕུང་བྱེད་ ཐེ་སེ་ སེ་བདུད་ ཉི་ནག་ ཚེས་གཉན་ … འདིར་ཞིབ་རྩིས་བྱེད་ན་འཁོར་ལོའི་རྣམ་གྲངས་བརྩི་ པི་ལིང་ བུམ་སྟོང་ ཝང་གིང་ བྱི་ལམ་ ཧལྱི་ དུས་མཚན་ཐེབས་ ཕྱོགས་ ཙང་ཀུན་ ལྷ་དགུ་ཚུལ་སོགས་ ཆེས་ཟབ་ ཇི་སྙེད་མཆིས་ཀྱང་ ཀུན་སྤེལ་མ་ཡིན་པས་ འདིར་མི་གསལ། རང་རྟ་ ཟིན་ཕུང་ གདོང་ལ་ ཟླ་རླུང་ སེ་བྱི་ གཉན་ཁྲ་ ཀིང་ སེ་ལོ་ སེ་ཤར་ བདུད་ ཐུན་མཚམས་»
  A list of the names whose exact reckoning, «the wheel's counts (the
  circling of the heavens, the numbers of the wheel)», the box says it does
  not give: it is too deep and not a thing of common use. It contains the
  month's river as «ཟླ་རླུང» (KP2 «ཟླ་རླུང་», the same; read «ཟླ་ཀླུང» in box
  16), the only other place the month's river occurs.
- **54 Composing treatises** (img. 59, KP2 282): «ལོ་ཟླ་ཞག་ནག་ བིཥྚི་ ཐེ་སེ་ སེ་ལོ་ དམས་ཤིང་ ཚེས་གཉན་ … ས་རྒྱལ་ ཕུང་ཟོར་ འཕུང་བྱེད་ སྒྲའི་དུས་གཉན་ལོགས་སུ་གསལ།»
  and at its foot «ཟིན་ཕུང་ དབུལ་ ཉི་ནག་ ཀླུ་བཟློག་པ» (box 55, rain), the
  last for the nāga rite's reversal.
- **56 Thread-cross and torma rites** (img. 60), bad half: «པི་ལིང་ སེ་བྱ་ ཀུངས་མྱུལ་ གནམ་ཟས་འཚོལ་ ཕག་མོ་རྒྱུ་ དམག་དཔོན་ ཙང་ཀུན་ གནས་…»;
  good half, in a verse of the kinds of *mdos* for the directions and
  times: «གཟའ་བཙན་དམུ་མདོས་བཟང་ ཟིན་ཕུང་ པིཥྚི་ རཱ་ཧུ་ ཀླུ་གཟའ་ནག་མོ་ ཞི་ལྗང་གནས་ཕྱོགས་འཛེམ།» — a rite
  for each kind of lord, and *zin phung*, Viṣṭi, Rāhu, the black nāga
  planet and the place of the green-and-blue peace avoided (machine).
- **57 to 60 Honouring, lawsuits, judging, councils** (img. 61–62), machine:
  57 «དབུལ་ཉི་ ཕུང་ཟོར་», 58 «ཉི་མ་རྟ་ ཕུང་ཟོར་ དབུལ་ལག་», 59 «རྟ་ ཕུང་ཟོར་ དབུལ་ལག་ ལ་སོགས་ གདུག་རྩུབ་ཆེ་བའི་ས་བདག་སྤངས།»
  (the harsh earth lords are left aside), 60 «ཀི་ཀང་ དབུལ་ ལོ་ཟླ་ཞག་ནག་ ཕུང་ཟོར་» with «རཱ་ཧུ་ དྲ་ཆེན་ པི་ལིང་ ཧལ་ཁྱི»
  before it.
- **61 to 64** (Henning's, img. 63–64), machine: 61 and 62 name «ཀིང་ ཧལྱི་ … པིང་ … གཟའ་རྒོད་ ཟས་འཚོལ་»
  and «ས་བདག་ དུག་པ་ཅན་རྣམས་ འཛོམ»; 63 «ཐེ་སེ་ ཟིན་ཕུང་ བིཥྚི་ ཧལྱི་ དབུལ་ པི་ལིང་ ཕར་ ལོ་ཟླ་ཞག་ནག་ འབྲུག་ལྷ་ གནས་ཕྱོགས།»

### What the boxes give, in short

Of the roadmap's ten names the boxes name *se byi* in boxes 10, 16, 19,
39, 42, 53; *hang phan* in 10, 16, 31, 42; *zla klung* only in 16 (and 53 as
«ཟླ་རླུང»); *se shar* in 10, 23, 53 (KP's «ས་ཤར» there, KP2's «སེ་ཤར»);
*the se* in 10, 13, 22, 39, 53, 54; *pi ling* in 10, 12, 17, 20, 21, 23, 39,
42, 46, 53, 56, 60, 63; *tsang kun* in 10, 23, 39, 53, 56; *dbul* in 10, 16,
17, 21, 34, 42, 46, 55, 57 to 60 and 63 or 64 (box 25 has «དབུལ་ལག»);
*nyi nag* in 16, 18, 20, 22, 23, 26, 27, 42, 49, 53, 55; *gnam stong* in 23
(as «གནམ»). All stand in the bad half, as a name to be avoided, a place or
a time to be avoided: **never as one work for which a lord is good or
bad.** The boxes from 56 on are machine reading, and the box a line
belongs to may be one off. The works are the boxes' own: a lord named in box 10 is bad
for feasts, in box 39 for an enthronement, in box 42 for the marriage.

### Open

- Open question 43 ([open-questions.md](open-questions.md)): the numerals under box 16's three «ཟླ་ཚེས».
- Open question 44: «ཀིང་ཟོར་ནག» and «གནམ་ཞག་ནག» in box 10.
- Open question 45: «དབུལ་ལག» in boxes 20, 25, 59 and 62. Answered 2026-10-18: one earth lord (below, *Three of the open words*).
- Not done: the second part of KP (img. 87–118, the earth lords of the year,
  month, day and hour as charts, and the remedies) is not read; it is the
  place that may say what each lord of these runs does.

### Searched and not found

- A box that names a lord in its good half: none of the boxes read, bar the
  *mdos* verse of box 56, which names the kinds of rite and not the lords.
- *gnam stong* in full in any box's list of names: only the short «གནམ»
  (box 23) and the verse of box 36 (img. 47; KP2 was not read that far).
- MITRA on whole KP folios (2550 px woodblock tables): loops on digits, no
  reading.


## KP's links left out: boxes 5, 7, 20, 48, 50, 53, 55 and 60 (O1)

Read 2026-10-12 for ROADMAP O1: the twelve links of dependent origination
(རྟེན་འབྲེལ, rten 'brel) and the sme ba, the nine numbers of the magic square
(སྨེ་བ, sme ba), that the table of 2026-10-10 above left out. **Image numbers
here are BDRC's own labels, the file names of the scans, as in the RD5
section; the box sections and the table above count two fewer** (box 5 is
KP img. 25 here, "23" above). KP is BDRC I4CZ65599 (2550 px), KP2 I3CN12074.

**How it was read.** Yigdzin-1 over the four quarters of each folio (the left
and right 55 %, the top and bottom 53 %, enlarged 1.5×) of KP img. 25, 26, 36,
56, 58, 59, 61, 62 and KP2 img. 244, 245, 255, 277–282; then every link and
numeral below on the scan itself, cropped and enlarged 3× to 6×, in KP and
again in KP2 (KP2 img. 283–285 fetched for box 60). The numerals were told
apart by the dates printed in the same print: box 37's «༣ ༤ ༥ ༦ ༨» (KP
img. 48, the dates 3, 4, 5, 6, 8) and box 48's «༡༨ ༢༨ ༢༩» (img. 56): 8 is the
open angle, 9 the rounded hook with a curled head, 4 a closed curve with a
tail, 3 the hook with a second stroke. MITRA was not used (it loops on KP's
digits, RD5); agy was not used. Where a quotation below is the machine
reading and not the scan, it says so.

The twelve links, named once: ignorance (མ་རིག་པ, ma rig pa), formation
(འདུ་བྱེད, 'du byed), consciousness (རྣམ་ཤེས, rnam shes), name and form
(མིང་གཟུགས, ming gzugs), the six sense sources (སྐྱེ་མཆེད, skye mched), contact
(རེག་པ, reg pa), feeling (ཚོར་བ, tshor ba), craving (སྲེད་པ, sred pa), grasping
(ལེན་པ, len pa), becoming (སྲིད་པ, srid pa), birth (སྐྱེ་བ, skye ba), ageing and
death (རྒ་ཤི, rga shi).

### "skye" and "skyed": birth and the sense sources

The early boxes write each link by its first syllable (ignorance «མ་རིག» or
«མརིག», naming «མིང», contact «རེག», feeling «ཚོར», grasping «ལེན», becoming
«སྲིད», craving «སྲེད») and consciousness contracted to «རྣཾས», as the mansions
are contracted (chu smad «ཆུཾད»: the first syllable and the last letter of the
second). On the scan the "skye" of 2026-10-10 is two different words:

- **«སྐྱེད», with a final ད**: box 5's good half, box 20's good half, and box
  59's good half (where the 2026-10-10 table did not count it). By the
  contraction it is skye + mched, **the six sense sources**; birth, skye ba,
  has no final letter to add.
- **«སྐྱེ», bare**: box 5's bad half and box 7's good half: **birth**.

Three things on the page bear this out. Box 5 has both words, «སྐྱེད» in its
good half and «སྐྱེ» in its bad, and its halves otherwise name every link once
and none twice (rnam shes, the sense sources, becoming, craving good; ignorance,
formation, name, contact, feeling, grasping, birth bad; ageing and death not
named): one box does not set the same link on both sides. Box 5's bad half
runs in the order of the chain, ignorance, formation, name, contact, feeling,
grasping and then «སྐྱེ», which is birth's place (eleventh), not the sense
sources' (fifth). And where the later boxes have room they write both in full
and keep them apart: box 37 (img. 48) «སྐྱེ་མཆེད་ སྲིད་པ་ … འདུ་བྱེད་ སྐྱེ་བ་ མ་རིག་ རྣམ་ཤེས་»
and box 39 (img. 49) «རྣམ་ཤེས་ སྐྱེ་མཆེད་ སྐྱེ་», box 59 (img. 62) «རེག་པ་ སྐྱེ་བ་».
KP2 has the same letters in the same places (img. 244, 245, 255).

### The boxes

- **5 Putting on new clothes** (KP img. 25, KP2 img. 244). Good half, line 3:
  «ཐརྐ་ རྣཾས་ སྐྱེད་ སྲིད་ ༼[…] སྲེད་༽ དུས་སྦྱོར་ འཁྲིག་ སེངྒེ་ …»
  — tha skar; consciousness, the sense sources, becoming, then a bracket
  holding one unread word and craving; the rising signs. Bad half, line 3,
  after ignorance at the end of line 2 («མྲིག», machine):
  «འདུ་མིང་རེག་ཚོར་ལེན་སྐྱེ་ ཉི་མ་ སྤྲེལ་ དུས་སྦྱོར་ ཀརྚ་ སྡིག་ འཕུང་བྱེད་»
  — formation, name, contact, feeling, grasping, birth; the Monkey day; the
  rising signs; the destroyer (འཕུང་བྱེད, 'phung byed). KP2 the same, «སྐྱེད» and bare
  «སྐྱེ» seen; its bracket after «སྲིད» holds one letter, read «ག» by machine.
  The bracket is the print's arch, the one that sets Monday aside in line 1
  («སངས་ ༼ཟླ༽»): in KP it spans the unread word and «སྲེད», so craving may
  stand in brackets, which counts for neither side.
- **7 Walls and forts** (KP img. 26, KP2 img. 245). Good half, end of line 2
  and line 3: «… སྣར་ འདུ་བྱེད་ / མ་རིག་ སྐྱེ་ ཉི་མ་ སྤྲེལ་ ལྡུམ་ར་བཟང་། …»
  — formation, ignorance, birth; then the Monkey day, good for gardens. Bad
  half, line 3: «… ༩ དུས་སྦྱོར་ ཀརྚ་ …», the sme ba 9 already in the table.
  KP2 the same, bare «སྐྱེ».
- **20 Virtuous acts for the living** (KP img. 36, KP2 img. 255). Good half,
  line 3: «སྣྲུབས་ མཆུ་ སྐྱེད་ མ་རིག་ རྣཾས་ ཚོར་ སྲིད་ སྲེད་ ཉི་མ་ འབྲུག་ …»
  — the mansions snrubs and mchu, then the sense sources, ignorance,
  consciousness, feeling, becoming, craving; the Dragon day. Bad half,
  line 3: «རྒ་ཤི་ ཉི་མ་ བྱ་ …» — ageing and death, the Bird day. KP2
  the same, «སྐྱེད» seen.
- **48 Fire offerings** (KP img. 56, KP2 img. 277). Good half, lines 3–4:
  «… ས་ག་ མགོ་ འདྲེ་འདུལ་བ་བཟང་། རེག་ ཉི་མ་ སྟག་ བྱ་ འབྲུག་ འདྲེ་ / འདུལ་བཟང་། …»
  — sa ga; mgo, good for subduing spirits (འདྲེ་འདུལ, 'dre 'dul); **contact**, the
  Tiger, Bird and Dragon days: good for subduing spirits. Contact stands in
  the good half inside a run the box qualifies, so it counts for neither
  side. (The Tiger, Bird and Dragon days are in the good half too, not the
  bad as the box section above says; they count for neither side either way.)
  Bad half, end of line 2 and line 3:
  «… ཉི་མ་ སྦྲུལ་ ཁམས་ / འདུལ་དང་གཉན་འདུལ་ངན། ༥ ༧ ༢ ༤ དུས་སྦྱོར་ ཆུ་སྲིན་ ཉ་ … / ཟིན་ཕུང་ གཞན་ཡང་ས་བདག་རྒྱུ་དུས་སོགས་ངན།»
  — the Snake day and kham (printed «ཁམས»), bad for subduing and for
  subduing the *gnyan* spirits (གཉན, gnyan); then, after the shad, four
  numerals, **5, 7, 2, 4**, plain; the rising signs; Zinphung the black (ཟིན་ཕུང, zin phung) and the
  earth lords' (ས་བདག, sa bdag) times, bad. The fourth numeral is box 37's
  4, not this box's 8 (the open angle of «༡༨», «༢༨»); Yigdzin-1 reads 4 in
  KP and KP2. The four stand where boxes 7 and 55 put the sme ba, between the
  day animals and trigrams and the rising signs; the box's dates are in its
  first line («ཚེས་ ༡༢ ༢༢ ༢ ༡༧ ༢༧»). KP2 the same.
- **50 Ordination, teaching, maṇḍalas** (KP2 img. 278 only; "276" above).
  Good half, end of line 5 and line 6:
  «… དབོ་ འཆད་ཉན་བཟང་། རྣམ་ / ཤེས་ ཚོར་བ་ ཉི་མ་ འབྲུག་ ཕག་ གིན་ ༥ དུས་སྦྱོར་ …»
  — dbo, good for teaching; then, a new run after the shad, plain:
  **consciousness, feeling**, the Dragon and Pig days, the trigram gin, the
  numeral **5**, the rising signs. The bad half (lines 1–5) names no link
  and no numeral before its rising signs. The whole box is on this one image.
- **53 Military training and games** (KP img. 58, KP2 img. 281). The box is
  two columns from top to bottom: the column rule runs through all seven
  lines in both prints, and each column has its own run of rising signs
  (line 5 ends «… ཟོན་ དུས་སྦྱོར་» on the left and begins «དུས་སྦྱོར་ བུ་མོ་ …»
  on the right). The left column is the good half under the heading, the
  right the bad half under «ངན་པ».
  Good column, line 5: «འདུ་བྱེད་ རྣམ་ཤེས་ རེག་པ་ ལེན་པ་ ཉི་མ་ སྟག་ འབྲུག་ གླང་ ཟོན་ དུས་སྦྱོར་ / གཞུ་ ཀརྚ་ ལུག་ སེངྒེ་ འདིར་ཞིབ་རྩིས་བྱེད་ན་འཁོར་ལོའི་རྣམ་གྲངས་བརྩི …»
  — **formation, consciousness, contact, grasping**, the Tiger, Dragon and Ox
  days, the trigram zon, the rising signs; plain, so good for both halves.
  Bad column, lines 3–4: «བྲ་ཉེ་ སྨིན་ སྣར་ གྲོ་བཞི་ དགྲ་ལ་རྒོལ་བ་བཟང་བའོ། །སྲེད་པ་ སྲིད་པ་ ཉི་མ་ / སྦྲུལ་ ཡོས་ ལུག་ ཁྱི་ སྤྲེལ་ རྩེད་མོ་བཟང་། ལི་ རྩེད་མོ་བཟང་། …»
  — the mansions good for attacking an enemy; then **craving, becoming**, the
  Snake, Hare, Sheep, Dog and Monkey days: good for games; li, good for
  games. Craving and becoming are in the run the box qualifies for games, as
  the five day animals are. KP2 the same in both columns.
  Found in passing: the Tiger, Dragon and Ox days and the trigram zon, which
  the box section above and the app count as bad for both halves, stand in
  the good column, plain, in both prints; the bad column names no plain day
  animal or trigram.
- **55 Bringing rain** (KP img. 59, KP2 img. 282). Good half, end of line 3:
  «ཉི་མ་ བྱི་ ཕག་ བྱ་ སྤྲེལ་ ཁམ་ ༣ ༢ ༤ / དུས་སྦྱོར་ ཀརྚ་ བུཾ་ ཉ་ ཆུ་སྲིན་ བདུད་རྩི་ཐུན་མཚམས། …»
  — the Mouse, Pig, Bird and Monkey days, kham, **3, 2, 4**; the rising signs
  and the nectar periods (the second line machine). Bad half, line 3:
  «… ཀླུ་སྨན་འདེབས་པ་བཟང་། ཁྱི་ ལི་ ༩ ༧ དུས་སྦྱོར་ སྡིག་ …» — good for
  offerings to the nāgas (ཀླུ, klu) and the *sman* goddesses (སྨན, sman) (machine); then the Dog day, li, **9, 7**,
  the rising signs. KP is the clearer for 9 and 7 (the curled hook and the
  seven); KP2 is the clearer for 3 and 2 (the second stroke of the 3); both
  have the same 4. Yigdzin-1 reads 3, 2, 4 and 9, 7 in both prints. These
  stand in the sme ba's place, as in box 48.
- **59 Judging cases** (KP img. 62, KP2 img. 285). Good half, end of line 2
  and line 3: «… ལག་ རྒྱལ་ ཆུཾད་ སྐྱེད་ / རེག་པ་ སྐྱེ་བ་ ཉི་མ་ གླང་ ཁྱི་ དུས་སྦྱོར་ བུ་མོ་ ཞི་མདོ་ …»
  — after the mansions, **the sense sources**, then contact and birth (in
  the table), the Ox and Dog days. KP2's «སྐྱེད» is worn: not read.
- **60 Councils** (KP img. 62, KP2 img. 285). Bad half, last line:
  «… ཀི་ཀང་ དབུལ་ ལོ་ཟླ་ཞག་ནག་ ཕུང་ཟོར་ སྦྱོར་བྱེད་ རྟེན་འབྲེལ་སོགས་དོན་བསྟུན།»
  — the almanac's days *ki kang* (ཀི་ཀང, ki kang) and *dbul* (དབུལ, dbul), the
  black days of year, month and date, *phung zor* (ཕུང་ཟོར, phung zor);
  "the combinations (སྦྱོར་བྱེད, sbyor byed), the links and the like: according to the
  matter". Read on KP at 3×, the last four words less surely; KP2's line is
  not legible. The box names the links only as a class, and no number: there
  is nothing to place on either side.

### What the table should now carry

| Box | Good | Bad | Neither |
| --- | --- | --- | --- |
| 5 | links rnam shes, skye mched, srid pa; sred pa unless the bracket holds it | links ma rig, 'du byed, ming, reg pa, tshor ba, len pa, skye ba | sred pa if bracketed (open) |
| 7 | links 'du byed, ma rig, skye ba | sme ba 9 (as now) | |
| 20 | links skye mched, ma rig, rnam shes, tshor ba, srid pa, sred pa | link rga shi (as now) | |
| 48 | | sme ba 5, 7, 2, 4 | link reg pa (qualified) |
| 50 | links rnam shes, tshor ba; sme ba 5 | | |
| 53a military training | links 'du byed, rnam shes, reg pa, len pa | | links sred pa, srid pa (good for games only) |
| 53b dice and games | links 'du byed, rnam shes, reg pa, len pa, sred pa, srid pa | | |
| 55 | sme ba 3, 2, 4 | sme ba 9, 7 | |
| 59 | adds link skye mched to reg pa, skye ba | (as now) | |
| 60 | | | the links named as a class only |

Box 48's and 55's numerals are the sme ba by their place; box 50's 5 by the
same place. The 53 split follows the rule of *Split and joined boxes*: an
entry qualified for one half counts for that half alone. Apart from the
links: box 53's Tiger, Dragon and Ox days and the trigram zon belong among
the good of both halves, not the bad.

### Open

- Open question 103 ([open-questions.md](open-questions.md)).
- Open question 104 ([open-questions.md](open-questions.md)).
- Open question 105 ([open-questions.md](open-questions.md)).

### Searched and not found

- A link or sme ba in box 50's bad half (KP2 img. 278) or in box 60's halves
  (KP img. 62): none, bar box 60's mention of the links as a class.
- The word inside box 5's bracket (KP img. 25, KP2 img. 244): not legible in
  either print at 6×.
- Box 60's last line and box 59's «སྐྱེད» in KP2 (img. 285): worn, not read.

## Three of the open words, 2026-10-18 (open questions 45, 155, 157)

- **«དབུལ་ལག» is one earth lord.** WB gives it a reading and a woodcut in
  ch. 33 (vol. 2, p. 362, img. 370, read on the scan at 2.5×): «༈ དབུལ་ལག་མཁར་ལས་འགོ་འཛུགས་དང་། །བག་མ་བུ་འབོགས་སྐྱེད་པུ་འཛུགས། །བཤད་གསར་རྟེན་བཞེངས་རབ་ཏུ་གནས། །ལྟད་སྟོན་ཐོག་འབུབས་དར་འཕྱར་བ།»
  (then, from the etext, «ནོར་རྫས་དབུགས་ཆེན་དབུགས་མེད་རྣམས། །ཕྱིར་གཏོང་རོ་འདོན་ཞིང་འདེབས་དང་། །བང་མཛོད་ཁ་དབྱེ་ལ་སོགས་པ། །གསོན་གཤིན་ལས་རྣམས་ཕལ་ཆེར་འཛེམ།»):
  beginning a fort, marriage, giving out a son, setting up a hearth, new
  teaching, making images, consecration, shows, roofing, raising flags,
  sending wealth out, carrying out a corpse, sowing and opening a
  treasury are mostly avoided. Ch. 34 names it beside the *phung zor* and
  the *hal khyi* (a dozen places in pp. 388–426, etext), and
  the model almanac writes it with an hour («དམག་དབུལ་ཉི་ཕྱེད», vol. 1, p. 156,
  etext). The day it falls is not found: «དབུལ» is not in ch. 31's courses
  (etext searched).
- **Box 37's «ཡུག་མ» is the widow.** KP's «བྲཉེ་ཡུག་མ་སྐྲ་ཁྲུས་བཟང» digests WB's work
  34, «བྲ་ཉེ(༡)ཡུགས་སྐྲ་བཀྲུ་བ་བཟང་།» (vol. 2, p. 404, read on the scan in
  [hair-dates.md](hair-dates.md)): Bharaṇī, bad for bathing and the hair,
  is good for washing the hair at the end of widowhood. *Yug za ma*, *yugs
  sa mo* is a widow (Dan Martin), *yugs sa* the mourning for a dead spouse
  (Jim Valby; Ives Waldo).
- **Box 21's dates are the blocks'.** KP2 img. 255 carves the good half's
  dates after «ཚེས་༡» in the same five cursive shapes as KP img. 36, so
  both prints have them; whether the second and fourth are KP's 4 or its 9
  is not settled by eye.

## On the prints and in BDRC's search, 2026-10-18 night (open question 44)

- **«ཀིང་ཟོར་ནག» and «གནམ་ཞག་ནག».** BDRC's search finds each once outside KP,
  in an anthology of Bhutanese almanac texts (MW26041, Thimphu 1981,
  etext): «…ཉི་མ་བྱ་ཡོས་ཀིང་ཟོར་ནག་དབུལ …པེ་ལིད་གནམ་ཞག་ནག …», two separate names
  in a list of days, as KP prints them; which Chinese-almanac days they
  are is not given there.

## Settled, 2026-10-18 late night (open questions 44 and 154)

- **44, «ཀིང་ཟོར་ནག» and «གནམ་ཞག་ནག» are contractions of WB's list.** Box 10
  is WB's work 11, feasts and vessels (ch. 34, vol. 2, p. 391, img. 399,
  read on the scan), whose list of what to avoid runs «…ཉི་མ་ཁྱི་ཕག་བཟང་བ་ཡིན། །བྱ་དང་ཡོས་ཀྱི་ཉི་མ་དང་། །འཆི་སྦྱོར་ཀི་ཀང་ཕུང་པོའི་ཟོར། །ནག་ཆུང་གཟའ་བདུན་ཟས་འཚོལ་བ། །དབུལ་ལག་ཧལ་ཁྱི་པི་ལིང་འཕར། །གནམ་ཁྱི་ཟས་འཚོལ་རྒྱུ་དུས་དང་། །ཞག་ནག་ཐེ་སེ་བློན་ཙང་ཀུན། །ཧང་ཕན་སེ་བྱི་སེ་ཤར་དང་། །ཀུངས་མྱུལ་ས་རྒྱལ་འབྲུག་ལྷའི་ཚོགས། །ལྷ་ལྔ་བར་གནས་དྲ་ཆེན་དང་། །རཱ་ཧུ་རྒྱུ་བའི་དུས་སོགས་ལ། །སྟོན་མོ་[བྱ]་བ་ཤིན་ཏུ་འཛེམ།»
  (the bracketed syllable from the etext). KP keeps WB's order name for
  name: «ཉི་མ་བྱ་ཡོས» the Bird and Hare days, «ཀིང་ཟོར་ནག» the *ki kang*, the
  *phung po'i zor* and the *nag chung*, «དབུལ» the *dbul lag*, «ཧལ་ཁྱི»,
  «པི་ལིང», «གནམ་ཞག་ནག» the sky dog (*gnam khyi*) and the black day (*zhag
  nag*), then the se, tsang kun and the rest as WB. They are not Chinese
  names of their own; each part is an earth lord or day already listed in
  RD3, RD4 and the courses. The Bhutanese anthology (MW26041) copies KP.
- **154, the feasts' qualifier is the Sun's and the Moon's.** The same
  work opens (img. 399, l. 1–2, read on the scan) «༡༡ སྟོན་མོ་ལ་སོགས་ཡོ་ལང་བྱེད། །ཉི(༡)ཟླ(༢)གསོན་སྟོན་ཡོ་ལང་མཆོག །ཤིད་སྟོན་བྱ་བ་ཤིས་པ་མིན། །པ་སངས(༦)ཕུར་བུ(༥)གཉིས་ལ་བཟང་། །ལྷག་པ(༤)རུང་ཙམ་མིག(༣)སྤེན(༠)ངན།»:
  Sunday and Monday are best for feasts of the living and for vessels,
  not auspicious for funeral feasts; Friday and Thursday are good without
  the qualifier, Wednesday passable, Tuesday and Saturday bad. KP's box
  joins the four weekdays in one phrase; WB keeps them apart.

## Open (O10f, 2026-10-14)

- Open questions 153–155 ([open-questions.md](open-questions.md)).

### Searched and not found

- The second good date of box 33 (img. 42): ༢༡ or ༣༡ at 4×, neither settled.
- The small numerals under box 16's three «ཟླ་ཚེས» (img. 33): too small at 5×.
- Box 50 and 51 on KP2's photograph (img. 278, 279) syllable by syllable:
  too dim at the photograph's size; written out as the machine reading.

## Box 21's bad dates against WB's work 9, 2026-10-19 (open question 153)

- **The three dates are bad for breaking in oxen alone, as in WB.** Box
  21 is a digest of WB's work 9, opening the soil and breaking in oxen
  (vol. 2, p. 390, img. 398, read on the scan for O2,
  [white-beryl-ch34.md](white-beryl-ch34.md)), whose dates are:
  «ཚེས་གཅིག་དྲུག་དང་བཅུ་གཅིག་དང་། །བཅུ་དྲུག་བཅུ་དགུ་ཉི་ཤུ་ལྔ། །ཉེར་དྲུག་རྣམས་ལ་བཟང་པོ་ཡིན། །བཅུ་གཅིག་ཉེར་གཉིས་ཉེར་དགུ་ལ། །གླང་འདུལ་བྱེད་པར་ངན་པ་ཡིན།»,
  "the 1st, 6th, 11th, 16th, 19th, 25th and 26th are good; on the 11th,
  22nd and 29th breaking in oxen is bad". WB's own line qualifies the
  three, and the 11th stands in the good list too, so it cannot be bad
  for the whole work; KP's «ཚེས་༡༡ ༢༢ ༢༤ གླང་འདུལ་བ་ལ་ངན» is the same line, with its
  ༢༤ where WB has 29, as in the box's good dates (question 157), where
  the shape read as 4 stands where WB has 9.
- **Box 21's doubtful shape stands where WB has its 9s (open question
  157).** Read again on img. 36 at 3× (2026-10-19): the good half's
  dates after «ཚེས་༡» run ༦ ༡༦ ༡[?] ༢༥ ༢༦ and the bad half's ༡༡ ༢༢ ༢[?], the
  same cursive shape in both doubtful places. WB's work 9 has the good
  1, 6, 11, 16, 19, 25, 26 and the bad 11, 22, 29: the box follows that
  order date for date (the good 11 left out), and the doubtful shape
  falls on WB's 19 and 29 alone. Read as 4, the box would add a 14 and a
  24 that WB has nowhere in the work; read as 9, it is WB's list. Which
  numeral the carver meant is still not settled against KP's 4s and 9s
  elsewhere.
