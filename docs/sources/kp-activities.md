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
- What the app does not calculate is left out: rising signs, the twelve
  links, the sme ba, the yogas and *dus sbyor*, the nectar periods, the
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

### 2. Naming — མིང་འདོགས་པ་ལ་བཟང་བ

KP img. 21, KP2 img. 240.

- **Good:** bra nye, mgo, rgyal, nabs so, dbo, chu stod, chu smad, khrums smad, nam gru, tha skar
- **Bad:** gre, nag pa, skag, snron, snrubs
- **Neither side:** every weekday, each for one kind of name (the Sun for a king's, the Moon a minister's, Mars a general's, Mercury a man's, Jupiter a monk's or brahmin's, Venus a woman's, a beast's or a jewel's, Saturn a land's or fort's); smin drug, only somewhat (KP རུང་ཙམ, KP2 ཅུང་ཙམ)
- **Not calculated:** rising signs
- The two chu and the khrums: ཆོད ཆུཾད in KP, read 19, 20 in the list's own order; KP2 has ཁྲུཾད (25) where KP's reading wavers.

### 3. Cutting new clothes — གོས་གསར་དྲ་བ་ལ་བཟང་བ

KP img. 21, KP2 img. 240.

- **Good:** Mon Wed Thu Fri; snar ma, nabs so, rgyal, me bzhi, sa ri, sa ga, lha mtshams, mon gre, nag pa, khrums smad, chu smad, gro bzhin, tha skar; days Dog
- **Bad:** Sun Sat Tue; dates 8; bra nye, smin drug, mgo, lag, skag, mchu, dbo, snron, snrubs, chu stod, mon gru, nam gru, khrums stod, gre; days Monkey
- **Not calculated:** rising signs; Abhijit (bad)
- The two halves name every mansion once; that fixes the bad ཁྲ as khrums stod (24), the one otherwise missing, as the scan shows.

### 5. Putting on new clothes — གོས་གསར་གྱོན་པ་ལ་བཟང་བ

KP img. 23, KP2 img. 242.

- **Good:** Wed Thu Fri; me bzhi, snar ma, nabs so, rgyal, sa ri, nag pa, sa ga, gro bzhin, lha mtshams, chu smad, mon gre, khrums smad, nam gru, tha skar
- **Bad:** Sun Tue Sat; dates 8; bra nye, smin drug, mgo, lag, skag, mchu, gre, snron, snrubs, chu stod, dbo, khrums stod, mon gru; days Monkey
- **Neither side:** Monday (in brackets)
- **Not calculated:** rising signs; the twelve links (རྣམ་ཤེས, སྐྱེ, སྲིད, སྲེད good; མ་རིག, འདུ་བྱེད, མིང, རེག, ཚོར, ལེན, སྐྱེ bad); Abhijit (bad)
- me bzhi is named 'best' (མྱེ་མཆོག). The halves name every mansion once, chu smad and khrums smad good, chu stod and khrums stod bad.

### 7. Building walls and forts — མཁར་ལས་བརྩིག་བཟོ་ལ་བཟང་བ

KP img. 24, KP2 img. 243.

- **Good:** Wed Fri; dates 3, 7, 11, 13, 15, 17, 14, 23, 25; chu stod, nabs so, mgo, dbo, gre, khrums stod, chu smad, mon gre, nam gru, snar ma; days Tiger, Ox, Sheep; trigrams gin, zin
- **Bad:** Tue; dates 10, 20, 30, 18, 22, 6, 16, 26; bra nye, skag, khrums smad, lha mtshams, smin drug, mchu, gro bzhin, mon gru, sa ga, snron, tha skar, nag pa; days Hare, Dragon, Dog, Pig, Snake; trigrams khon, kham, khen
- **Neither side:** Thursday (good for woodwork, bad for roofing); snrubs (good for setting up gates); me bzhi (bad for roofing); the Monkey day (good for gardens); among the bad, the Sun (good for royal forts), the Moon (for temples), Saturn (for earthwork), rgyal (for laying foundations), sa ri (for roofing tents and houses), the Horse day (bad for roofing), the Mouse day (good for doorways)
- **Not calculated:** rising signs; the twelve links (འདུ་བྱེད, མ་རིག, སྐྱེ good); the earth lords
- Lag is not named. The 14th among the good dates is printed ༡༤ in both prints, out of their order. The khrums are ཁྲོུཾད (good) and ཁྲུཾད (bad) in both prints.

### 9. Setting up hearth and pillars — ཐབ་ཀ་བཅའ་བ་ལ་བཟང་བ

KP img. 25, KP2 img. 244.

- **Good:** Sun Mon Thu Fri Sat; dates 2, 7, 8, 12, 13, 17, 18, 22, 23, 27; smin drug, mgo, rgyal, gre, me bzhi, dbo, khrums stod, tha skar
- **Bad:** Tue Wed; lag, mchu, snrubs, chu stod, skag, khrums smad, mon gre, nam gru
- **Neither side:** chu smad (middling for the hearth); among the bad, lha mtshams, nabs so and chu smad (good for the hearth stones), bra nye (good for the hearth stones and pillars), the Dog and Ox days (good for a smith's hearth)
- **Not calculated:** rising signs; Abhijit; the Dragon day's earth lord
- The box adds that dates it does not name are neutral. KP2's reading drops the 23rd; KP has it.

### 10. Feasts, using vessels and household goods — སྟོན་མོ་ལ་སོགས་ཡོ་ལང་བྱེད་པ་ལ་བཟང་བ

KP img. 26, KP2 img. 245.

- **Good:** Fri Thu Sun; dbo, snrubs, nam gru, nabs so, mon gre, sa ri, me bzhi, mgo; days Dog
- **Bad:** Tue Sat; dates 9, 19, 29, 1, 13, 10, 11, 20, 21, 30; snar ma, rgyal, sa ga, mchu, gre, bra nye, khrums smad; days Bird, Hare
- **Neither side:** the Moon, after which the weekdays are called best for feasts of the living and bad for funeral feasts; Wednesday (acceptable); smin drug (bad for funeral feasts); the Pig day (good for feasts of the dharma); among the bad, chu stod (good for funeral feasts), lha mtshams (good for feasts of the dharma), gro bzhin (good for happy feasts)
- **Not calculated:** Abhijit; the twelve links (མ་རིག, རྣམ་ཤེས, ཚོར་བ, སྲེད་པ); the Chinese almanac's days (pi ling, gnam zhag nag, the se …), Rāhu's course and the like
- The bad dates include ༡༣, as printed.

### 11. Preparing food, brewing beer — ཟས་སྦྱོར་ཆང་བཙོ་བ་ལ་བཟང་བ

KP img. 26, KP2 img. 245. Otherwise as box 10.

- **Good:** Mon Thu Fri; dates 4; khrums smad; days Sheep
- **Bad:** Wed; dates 29
- **Not calculated:** Abhijit (good)
- «དེ་ལས་གཞན་མ་སྟོན་མོ་ཡོ་ལང་དང་མཚུངས་སོ»: otherwise as box 10, whose lists it takes where it names nothing of its own.

### 15. Trade and measuring grain — ཚོང་དང་འབྲུའི་འཇལ་ལེན་ལ་བཟང་བ

KP img. 30, KP2 img. 249.

- **Good:** Wed Thu Fri; mon gru, tha skar, nam gru, mgo, nabs so, snar ma, khrums stod, snrubs, chu smad, sa ri, gro bzhin, skag, gre; days Bird, Mouse
- **Bad:** Tue Sat; dates 4, 5, 6, 7, 8, 9, 10, 11; me bzhi, rgyal, mon gre, sa ga, lag; trigrams gin
- **Neither side:** the Sun and the Moon (in brackets); all the good dates, from the 1st to the 30th (partly illegible), of which the box says «འདི་རྣམས་ལ་འཚོང་བ་ངན» (these are bad for selling); khrums smad (good for buying estates and livestock); mchu (for buying horses and livestock); the Sheep day (for giving livestock away); among the bad, the Ox and Snake days ('each bad on its own day')
- **Not calculated:** rising signs; the yogas dge ba and chabs (?) and the grub sbyor (good for buying fields); the sme ba nine; Abhijit (good); a verse on the grain measure counted from the Sun's mansion (box 14)
- The bad dates run on after the 11th under a blot and an interlinear note in both prints; only 4–11 are legible. The good list's first khrums is ཁྲོད in KP2 (24), its last ཁྲུཾད (25).

### 15b. Giving and taking loans — ཞར་བྱུང་བུ་ལོན་གཏོང་ལེན་ལ་བཟང་བ

KP img. 31, KP2 img. 250.

- **Good:** Tue Wed; snar ma, lag, skag, nabs so; days Snake
- **Bad:** Fri; dates 15; sa ri, sa ga, lha mtshams, snrubs, gro bzhin
- **Neither side:** snron (good for collecting debts)
- **Not calculated:** the Chinese almanac's days (gnam stong, ki kang …)
- A sub-box of 15 under its own heading, 'by the way'.

### 16a. Receiving wealth — ནོར་ཅ་གཏོང་ལེན་ལ་བཟང་བ (ལེན)

KP img. 31, KP2 img. 250.

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

- **Good:** Sun Mon Wed Fri Thu; tha skar, smin drug, mgo, dbo, me bzhi, nag pa, sa ri, snron, chu stod, gro bzhin, nam gru, chu smad, mon gru
- **Bad:** Tue Sat; bra nye, lag, skag, snrubs
- **Not calculated:** rising signs; the yogas sel ba and mdza' bo and the other good times of the third line (the nectar periods); Abhijit (bad); the karaṇa Viṣṭi, ki kang, Rāhu, pi ling, the Dog year, the five gods' places and the like (bad)
- Before the table, a verse names the prosperity mansion (གཡང་སྐར) of each kind of wealth (smin drug of women, snar ma of clothes, mgo and lag of men, nabs so and rgyal of people …): on them giving one's own kind away is bad, calling it in good. The two chu of the good list are ཆོད and ཆུཾད (19, 20).

### 18. Planting trees and flowers — སྐྱེ་ཤིང་མེ་ཏོག་འདེབས་པ་ལ་བཟང་བ

KP img. 33, KP2 img. 252.

- **Good:** Mon Wed Thu Sat; mgo, lag, rgyal, chu stod, nam gru, mon gru, snron, sa ri, skag, chu smad, khrums stod; days Bird, Monkey; trigrams li, zin
- **Bad:** Tue; bra nye, smin drug, mchu, me bzhi, dbo, sa ga
- **Neither side:** Friday (in brackets); the Sun (good for planting trees, bad for flowers); khrums smad, named good (ཁྲུཾད) and bad (ཁྲིད, as in the new-home box)
- **Not calculated:** rising signs; the Chinese almanac's days (se bdud, nyi nag)

### 19. Making peace between enemies — འགྲས་སྡུམས་བྱེད་པ་ལ་བཟང་བ

KP img. 33, KP2 img. 252.

- **Good:** Mon Wed Thu; dates 8; sa ri, khrums stod, dbo, chu smad, gro bzhin, me bzhi, lha mtshams, tha skar, mon gru, rgyal; days Dog, Ox
- **Bad:** Sun Tue Sat; dates 4; mgo, lag, skag, mchu, nag pa, chu stod, snron, sa ga, nam gru, khrums smad; days Horse
- **Neither side:** Friday (bad for going as a guest)
- **Not calculated:** rising signs; the nectar periods; Abhijit (good); zhag nag and 'phung byed (bad)
- KP2 heads the box འགྲས.

### 20. Virtuous acts for the living — གསོན་དགེའི་ལས་ལ་བཟང་བ

KP img. 34, KP2 img. 253.

- **Good:** Wed Thu Fri; dates 1, 18; snar ma, mgo, nabs so, rgyal, mon gru, dbo, lag, me bzhi, nag pa, sa ri, snron, mon gre, khrums stod, gre, nam gru, lha mtshams, snrubs, mchu; days Dragon; trigrams khen
- **Bad:** Tue Sat; bra nye, skag, khrums smad, gro bzhin; days Bird
- **Neither side:** the Sun and the Moon (in brackets)
- **Not calculated:** Abhijit (good); the twelve links (སྐྱེ, མ་རིག, རྣམ་ཤེས, ཚོར, སྲིད, སྲེད good; རྒ་ཤི bad); rising signs; the 'phel 'gyur combination and the like (good); Viṣṭi, ki kang, pi ling, nyi nag, phung zor and the like (bad)
- KP2 brackets lha mtshams; KP does not, and the app follows KP. The bad khrums is ཁྲིད, khrums smad, as in box 18.

### 21. Manuring, opening the soil, breaking in oxen — ས་ལུད་ཁ་འབྱེད་གླང་འདུལ་ལ་བཟང་བ

KP img. 34, KP2 img. 253.

- **Good:** Mon Wed Fri Sat; dates 1, 4, 14, 19, 25; nabs so, mon gru, snar ma, nam gru, sa ga, mon gre, chu stod, mgo, me bzhi, sa ri, rgyal; trigrams gin
- **Bad:** Sun Tue; dates 11, 22; skag, bra nye, smin drug, dbo, mchu, gre, lha mtshams; days Hare, Snake, Pig
- **Neither side:** Thursday (middling for opening the soil); one mansion after mon gru, not legible (middling for manuring); gro bzhin (good for breaking in oxen); tha skar (bad for opening the soil); the 29th, good and also bad for breaking in oxen
- **Not calculated:** rising signs; Abhijit (good); the Chinese almanac's days and the earth goddess's place (bad)

### 22. Sowing the fields — ཞིང་ལས་ས་བོན་འདེབས་པ་ལ་བཟང་བ

KP img. 36, KP2 img. 257.

- **Good:** Sat Mon Wed Thu Fri; dates 2, 10, 12, 15, 16, 17, 21, 22, 23, 24, 25, 26, 27, 30; snar ma, nabs so, nam gru, mgo, mchu, me bzhi, nag pa, snron, mon gre, mon gru, sa ri, lag, gro bzhin, snrubs, chu stod, chu smad, lha mtshams; days Sheep, Dog, Ox
- **Bad:** Sun Tue; dates 4, 8, 14, 29; bra nye, smin drug, rgyal, sa ga, dbo, khrums stod; days Pig, Hare, Dragon, Snake
- **Neither side:** the 28th, printed in both halves; one good date read ༡༤ or ༡༩ (the shapes of 4 and 9 cannot be told apart there); khrums smad (bad for ploughing, good for sowing)
- **Not calculated:** Abhijit (good); the link ཚོར་བ; rising signs; srung bu, the earth lord, the Dragon god and the like
- The good dates run in two lines and are read on the scan at three times the size; the readings by machine disagree on most of them.

### 23. Giving gifts and dowries — སྐྱས་བྱེད་པ་བཟང་བ

KP img. 36, KP2 img. 257.

- **Good:** Wed Thu; khrums stod, nam gru, dbo, snrubs, chu smad
- **Bad:** Fri Sun Tue Sat; tha skar, nag pa, mgo, me bzhi, rgyal, snar ma, mon gre, smin drug, lag, sa ri, sa ga, mchu, chu stod, skag; days Hare, Dog, Sheep, Snake, Tiger; trigrams khon
- **Neither side:** the Moon (in brackets)
- **Not calculated:** the Chinese almanac's days (se shar, gnam stong, pi ling, tsang kun …) and the Seven Planets' food-seeking (bad)
- The bad mansions end «ཤིན་ཏུ་ངན» (very bad), an emphasis, not a restriction.

### 24. Sewing tents and felt — སྦྲ་དང་ཕྱ་ཐེར་འཚེམ་པར་བཟང་བ

KP img. 37, KP2 img. 258.

- **Good:** Thu; sa ri, nabs so, snar ma, khrums stod, chu stod, dbo; days Tiger, Snake
- **Bad:** Sun Sat; dates 29; chu smad, gre, mchu, skag, lag, nam gru, mon gre, khrums smad, snrubs, nag pa; days Monkey
- **Neither side:** the Moon, Mercury and Venus (bracketed among the good; Mercury also named bad); smin drug (middling for sewing); me bzhi (good for sewing, bad for pitching)
- **Not calculated:** rising signs

### 25. Building storehouses — བང་མཛོད་བཅའ་བར་བཟང་བ

KP img. 37, KP2 img. 258.

- **Good:** Wed Sat; nabs so, snar ma, rgyal, gro bzhin, snrubs; days Mouse, Sheep, Dog
- **Bad:** Sun Tue; days Pig, Snake, Bird
- **Neither side:** the Moon, Thursday and Friday (in brackets); sa ri (good, and bad for opening the storehouse); sa ga, chu stod, mon gru, chu smad (named good, but «འདི་རྣམས་ཁ་དབྱེ་ངན», bad for opening it); bra nye, smin drug, mchu, gre (named bad, but good for opening it); nag (bad for opening it); the Ox day (bad for opening it)
- **Not calculated:** Abhijit (best); rising signs; dbul and the like (bad)
- The first good mansions are «འདི་རྣམས་མཆོག» (these are the best), an emphasis.

### 26. Raising victory banners and flags — རྒྱལ་མཚན་བ་དན་འཕྱར་བ་ལ་བཟང་བ

KP img. 38, KP2 img. 259.

- **Good:** Sun Thu Sat; mgo, dbo, khrums stod, tha skar, chu smad, rgyal
- **Bad:** smin drug, lag, gre, mchu, snrubs, bra nye; days Ox, Sheep
- **Neither side:** the Moon and Venus (in brackets among the good); Mercury (in brackets among the good, and bad)
- **Not calculated:** rising signs; the nectar periods (good: 'without bla nag and the like'); nyi nag, Viṣṭi, 'phung byed, nag mo and the like (bad)

### 27. Making springs, wells and canals — ཆུ་མིག་ཁྲོན་པ་ཡུར་བ་བཟོ་བ་ལ་བཟང་བ

KP img. 38, KP2 img. 259.

- **Good:** Mon Wed Fri Sat; dates 2, 7, 12, 17, 22, 27; khrums smad, tha skar, lag, gro bzhin, snron, chu smad, sa ri, skag, dbo; days Sheep
- **Bad:** Sun Tue; dates 3, 13, 18, 23, 28, 8; smin drug, mchu, gre, me bzhi, nag pa, mon gru, khrums stod; days Hare, Dragon, Snake, Pig; trigrams kham
- **Neither side:** Thursday (in brackets)
- **Not calculated:** Abhijit (good); rising signs; the sme ba three (bad); nyi nag, byi lam (bad)
- The good dates are every fifth from the 2nd, the bad every fifth from the 3rd.

### 28. Feeding up horses — རྟ་རྒྱགས་པ་ལ་བཟང་བ

KP img. 39, KP2 img. 260.

- **Good:** Fri Tue Sun Wed Thu; smin drug, mgo, rgyal, nabs so, me bzhi, snar ma, nag pa, gro bzhin; days Hare; trigrams gin
- **Bad:** Sat; dates 11, 22, 29; sa ri, mon gru, mchu, gre, khrums stod, chu stod; days Mouse, Horse
- **Neither side:** the Moon (in brackets)
- **Not calculated:** Abhijit (good); rising signs; the karaṇa, ha li, Rāhu's face and the like (bad)
- The third bad date is ༢༩ on the scan (KP's machine reading had 24).

### 29. Treating horses, mules and donkeys — རྟ་དྲེའུ་བོང་བཅོས་བྱེད་པ་ལ་བཟང་བ

KP img. 39, KP2 img. 260. Otherwise as box 28.

- **Good:** Mon Wed; nabs so, chu smad, mon gru, sa ri
- **Bad:** —
- **Neither side:** mgo, nam gru, skag (castrating especially good); bra nye and dbo (good for other work); the Sun, Saturn and Mars (named bad, but good for cauterizing); me bzhi, mchu, gre, lha mtshams, gro bzhin (named bad for particular work)
- **Not calculated:** Abhijit
- «གཞན་རྣམས་བཟང་ངན་རྒྱགས་པ་དང་མཚུངས»: the rest as box 28, whose lists it takes where it names nothing plainly.

### 30. Saddling — སྒ་རྒྱག་ལ་བཟང་བ

KP img. 39, KP2 img. 260. Otherwise as box 28.

- **Good:** Tue Wed Thu Fri; smin drug, rgyal, nabs so, lha mtshams, gro bzhin, mgo, snar ma, mon gru, chu smad, dbo, me bzhi, sa ri
- **Bad:** Sat; skag, mchu, gre, mon gre
- **Neither side:** the Sun (in brackets)
- **Not calculated:** Abhijit (good)
- The rest as box 28 («རྟ་རྒྱགས་དང་མཚུངས»).

### 31. Keeping dogs — ཁྱི་བསྟེན་པར་བཟང་བ

KP img. 40, KP2 img. 261. Otherwise as box 16a.

- **Good:** Sat Tue; skag, mchu, tha skar
- **Bad:** days Dog; trigrams khen
- **Neither side:** Wednesday, Thursday, Friday, Mars (?) and the Sun, which the bad half calls «འདབྲིང» (middling); one khrums of the good half (ཁྲོད in KP, ཁྲུཾད by the readings of both prints)
- **Not calculated:** the sme ba five, se ba, bla mkhyen; hang phan, ha li and the like (bad)
- skag is good above all, 'being its bla skar' (the dog's). «གཞན་མ་གོང་གི་ནོར་ལེན་དང་མཚུངས་སོ»: the rest as receiving wealth (box 16).

### 32. Calling prosperity, bon rites — གཡང་ལེན་བོན་ཆོག་ལ་བཟང་བ

KP img. 40, KP2 img. 261.

- **Good:** Sun Mon Wed Sat; dates 15; snar ma, nabs so, smin drug, rgyal, mgo, dbo, sa ri, mon gru, mon gre, snron, snrubs, chu stod, nam gru, chu smad, gre, nag pa, me bzhi, lha mtshams, tha skar; days Tiger
- **Bad:** Tue; sa ga, skag, khrums smad, bra nye; days Hare, Pig
- **Neither side:** Thursday and Friday (in brackets); lag and gro bzhin (with a word not read); mchu (bad for Bon rites)
- **Not calculated:** the sme ba six and nine; the 'phel 'gyur combination and the auspicious days (good); 'phud byed (bad)

### 33. Learning writing and astrology — ཡི་གེ་རྩིས་རིག་པ་བསླབ་པར་བཟང་བ

KP img. 40, KP2 img. 261.

- **Good:** Thu Wed Fri Sun; dates 18; smin drug, nabs so, mchu, me bzhi, rgyal, khrums smad, sa ri, mgo, sa ga, mon gru, khrums stod, lha mtshams, lag; days Dog
- **Bad:** Sat Tue; dates 16; bra nye, skag; days Pig, Ox
- **Neither side:** nag (best for learning drawing and crafts)
- **Not calculated:** rising signs (best for learning skills); 'phud byed (bad)
- A second good date after the 18th is not legible (the readings by machine give 2, 21 or 31).

### 34. Compounding medicine — སྨན་སྦྱོར་བྱེད་པར་བཟང་བ

KP img. 41, KP2 img. 262.

- **Good:** Mon Sun Thu Fri; smin drug, mgo, rgyal, me bzhi, snar ma, sa ri, gro bzhin, mon gre, mon gru, khrums stod, khrums smad, nam gru, chu smad, nag pa, nabs so, lha mtshams; days Bird, Dog
- **Bad:** Sat Tue Wed; bra nye, skag, snron, gre, sa ga, tha skar; days Tiger, Snake, Sheep
- **Not calculated:** Abhijit (good); the links མིང, རྣམ་ཤེས, ལེན (good); the sme ba four; rising signs (Virgo 'best'); the nectar periods and the meeting of planet and mansion in water (good); dbul and the like (bad)
- Before box 36 (img. 42) a verse reckons from the Sun's mansion where the Moon's falls on the physician, the spatula, the medicine bowl or the patient.

### 35. Bloodletting, moxibustion, treatment — གཏར་མེ་དཔྱད་བཅོས་བྱེད་པར་བཟང་བ

KP img. 41, KP2 img. 262.

- **Good:** Mon Wed Thu Fri; dates 10, 18; lha mtshams, nam gru, gro bzhin, mchu, snar ma, nabs so, rgyal, sa ri, snron; days Bird, Dog
- **Bad:** dates 9, 8, 29, 30; lag, gre, tha skar, bra nye; days Dragon, Snake, Sheep
- **Neither side:** Saturn, the Sun and Mars («མེ་བཙའ་བཟང», good for moxibustion); one khrums of the bad half, and one name after bra nye, not read
- **Not calculated:** Abhijit (good); rising signs (Cancer and Libra in brackets); the sme ba two and seven; Viṣṭi; the 'chi sbyor, the srog dates and the rising of rigs ldan (very bad); the bla gnas of people and horses (box 36)

### 37. Bathing and washing the hair — ཁྲུས་དང་སྐྲ་འཁྲུ་བྱེད་པར་བཟང་བ

KP img. 46, KP2 img. 267.

- **Good:** Mon Wed Thu Fri; dates 3, 4, 5, 6, 8, 10, 11, 13, 15, 16, 18, 19, 22, 23, 26; smin drug, snar ma, mgo, lag, tha skar, chu stod, sa ri, chu smad, khrums smad, nam gru, nag pa, mon gre
- **Bad:** Tue Sat; dates 1, 2, 7, 9, 12, 14, 17, 20, 21, 24, 25, 28, 29, 30; rgyal, mchu, sa ga, khrums stod, gre, bra nye
- **Neither side:** the Sun (good for bathing, bad for washing the hair); gro bzhin (good for shaving the head); rgyal on the 25th of the Horse month and mgo on the 8th (named for particular merits)
- **Not calculated:** Abhijit (good); the twelve links; rising signs
- The two halves divide the thirty dates between them; the bad half's second line is worn. The complement of the good dates was taken for it until WB's p. 404 was read (2026-10-09, [hair-dates.md](hair-dates.md)): the good half is WB's good list date for date, and WB's bad list names the 27th on neither side, so the box's bad dates are WB's, without the 27th. A table below gives what bathing on each date brings, after another tradition.

### 39. Enthronement — རྒྱལ་སར་འཇུག་པ་ལ་བཟང་བ

KP img. 47, KP2 img. 268.

- **Good:** Sun Tue Thu; snar ma, mgo, rgyal, lha mtshams, snron, chu smad, nabs so, chu stod, mon gru, dbo, khrums smad, nam gru, smin drug, sa ri, me bzhi; days Horse, Dragon, Tiger, Hare; trigrams khen
- **Bad:** Sat; bra nye, lag, skag, mchu, nag pa, gro bzhin, snrubs, sa ga, mon gre; days Snake, Monkey
- **Neither side:** the Moon, Mercury and Venus (in brackets)
- **Not calculated:** the dates of both halves (crowded in two lines and only partly legible: the good begin 1, 2, 8, 9, 10, the bad 6, 16, 26); the links; rising signs; the earth and water combination and the nectar periods (good); se ba, bla mkhyen, 'phung byed, nag mo and the like (bad)
- The dates are left out until read with confidence on a better scan.

### 41. Taking attendants and servants — འཁོར་གཡོག་བསྟེན་པར་བཟང་བ

KP img. 48, KP2 img. 269.

- **Good:** Mon Wed Fri; dates 8, 5, 14; khrums smad, nabs so, khrums stod, rgyal, mchu, dbo, snrubs, chu stod, chu smad, bra nye; days Horse
- **Bad:** Tue Sat; dates 11, 12, 13, 4, 7, 9, 10, 18, 21, 22, 23, 25, 26, 27, 28, 30; gre, snron; days Snake; trigrams khen
- **Neither side:** the Sun and Thursday (in brackets); the 29th (in both halves); lag (with a qualifier not read)
- **Not calculated:** rising signs; 'phung byed; a closing note that the sme ba chart should also be weighed
- One more bad date in the first line, read 13 or 15, is left out.

### 43. Putting on ornaments — རྒྱན་འདོགས་པར་བཟང་བ

KP img. 50, KP2 img. 271.

- **Good:** Mon Fri Wed Thu Sun; smin drug, mgo, gro bzhin, rgyal, nabs so, mchu, gre, chu stod, chu smad, snron, snrubs, lag, me bzhi, mon gre, lha mtshams, khrums smad, dbo, nag pa; days Horse, Monkey; trigrams gin
- **Bad:** Tue Sat; bra nye, tha skar, skag, mon gru
- **Not calculated:** Abhijit (good); the link འདུ་བྱེད; rising signs; the earth and water combination (good)

### 44. Suppressing the sri spirits — སྲི་གནོན་བྱ་བར་བཟང་བ

KP img. 50, KP2 img. 271.

- **Good:** Tue Sat Sun Thu; mgo, lag, skag, snron, snrubs, sa ga, sa ri, khrums smad, me bzhi, tha skar, mchu; days Tiger, Dragon, Monkey; trigrams zon
- **Bad:** Mon; dates 1; rgyal, smin drug, mon gre, lha mtshams, gro bzhin; days Hare, Snake, Pig, Bird, Mouse, Ox; trigrams khon
- **Neither side:** Wednesday and Friday (in brackets)
- **Not calculated:** the good dates (two crowded lines, not read with confidence); Abhijit (good); rising signs; the Nāga planet's food-seeking and ki kang (bad); the earth lords of the nine levels
- mchu is 'best of all'.

### 48. Fire offerings — སྦྱིན་སྲེག་བྱེད་པ་ལ་བཟང་བ

KP img. 54, KP2 img. 275.

- **Good:** Sun Tue Thu; dates 3, 13, 23, 18, 28, 29; smin drug, nabs so, mchu, sa ri, khrums stod, gre, chu stod, lag
- **Bad:** Mon Wed Fri; dates 12, 22, 2, 17, 27; lag, skag, bra nye, khrums smad, snrubs; days Snake
- **Neither side:** Saturn (in brackets); mgo (good for subduing spirits); lag, named in both halves; among the bad, the dates 5, 7, 2, 8 with a qualifier and the Tiger, Bird and Dragon days (bad for subduing spirits)
- **Not calculated:** the links; rising signs; the sme ba; nag mo and the like (bad)

### 49. Consecration — རབ་གནས་བྱེད་པར་བཟང་བ

KP img. 54, KP2 img. 275.

- **Good:** Thu Mon Wed Fri; dates 1, 10, 11, 12, 13, 15; smin drug, lag, khrums stod, lha mtshams, nabs so, mgo, rgyal, snar ma, khrums smad, dbo, snrubs, gro bzhin, mon gre, chu smad, chu stod, skag; days Tiger, Dragon
- **Bad:** Sun Tue Sat; dates 2, 3, 5, 6, 7, 8, 14, 16, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30; nag pa, skag, mchu, gre, gro bzhin, bra nye
- **Neither side:** the 9th (in both halves); skag and gro bzhin, named in both halves; sa ri (in the good half, with the link རྣམ་ཤེས)
- **Not calculated:** Abhijit (good); the links; rising signs; the nectar periods; the waxing dates from the winter solstice (good); Viṣṭi, 'phung byed and the like (bad)
- One bad date after the 9th is unclear and left out.

### 54. Composing treatises, learning poetics and grammar — བསྟན་བཅོས་རྩོམ་དང་སྙན་ངག་སྒྲ་སློབ་པར་བཟང་བ

KP img. 57, KP2 img. 280.

- **Good:** Thu Wed Sun Mon; dates 2, 3, 5, 10, 11, 23; smin drug, snar ma, rgyal, lha mtshams, me bzhi, sa ri, mon gru, dbo, khrums stod, khrums smad, tha skar, nabs so, nam gru, chu stod, snrubs, lag, mgo; days Dog
- **Bad:** Sat Tue; dates 4, 7, 12, 9, 15; bra nye, skag, mchu; days Ox, Pig
- **Neither side:** Friday (in brackets); the 23rd, named 'best' at the end of the good dates
- **Not calculated:** the link འདུ་བྱེད; rising signs; the sme ba five; a clean place (good); Viṣṭi, the se lo and the like, the earth lord, phung zor (bad)
- One more bad date, read 1 with the word rig (rig byed), is left out.

### 55. Bringing rain — ཆར་འབེབས་བྱེད་པར་བཟང་བ

KP img. 57, KP2 img. 280.

- **Good:** Mon Wed Fri; bra nye, mgo, lag, snar ma, gre, dbo, nag pa, sa ri, lha mtshams, chu stod, mon gre, gro bzhin, snron, snrubs, mchu, nam gru, chu smad, mon gru, khrums stod; days Mouse, Pig, Bird, Monkey; trigrams kham
- **Bad:** Thu Sun Tue; smin drug, rgyal, me bzhi, khrums smad, skag; days Horse, Dragon, Ox, Sheep, Snake, Dog; trigrams li
- **Neither side:** Saturn (in brackets)
- **Not calculated:** rising signs; the sme ba; the nectar periods (good); the eight classes' and the nāgas' strokes, 'phung byed and the like (bad)
- The Snake day is bad for rain but good for nāga offerings, which this box does not count.

### 56. Thread-cross and torma rites — མདོས་གཏོར་བྲབ་པ་ལ་བཟང་བ

KP img. 58, KP2 img. 281.

- **Good:** Sun Thu Sat Tue; dates 18, 25, 28, 27; gre, lag, mgo, snrubs, gro bzhin
- **Bad:** Fri Mon Wed; dates 26, 12, 14; snar ma, lha mtshams, mon gru, mon gre, nam gru
- **Neither side:** mchu (good, but bad for Bon rites); nag, chu stod, nabs so, dbo, tha skar, snron (good for fierce mdos); bra nye (for planting skyas); the Dragon day and the trigrams khon, khen (good for particular mdos); the Pig day (bad for Bon rites); the dates 2, 3, 4, 5, 7 with the spirits they suit
- **Not calculated:** rising signs; the spirits' and Rāhu's times and places
- The box names a kind of mdos for most of its entries; only the plain ones are counted.

### 57. Honouring and petitioning — བསྙེན་བཀུར་ཞུ་གསོལ་བྱ་བ་ལ་བཟང་བ

KP img. 59, KP2 img. 282.

- **Good:** Fri Sun Wed; tha skar, nabs so, mon gru, nam gru, sa ri, mgo, lag, rgyal, skag, mchu, mon gre, chu stod, snron; days Dog, Hare; trigrams khen
- **Bad:** Sat Tue; dates 4, 8, 9, 28; chu smad, khrums stod, sa ga
- **Neither side:** Thursday (in brackets)
- **Not calculated:** the links; rising signs; zhi mdo; dbul, phung zor (bad)

### 58. Disputes and lawsuits — ཁ་མཆུ་རྩོད་པ་ལ་བཟང་བ

KP img. 59, KP2 img. 282.

- **Good:** Fri Tue; tha skar, chu smad, nabs so, mon gru, nam gru, mgo, mchu
- **Bad:** Sat Sun Mon Thu Wed; dates 4, 8, 9, 28; sa ri, lag, rgyal, skag, snron, lha mtshams, snrubs, nag pa; days Horse
- **Not calculated:** the links; rising signs; zhi mdo, dge ba (good); phung zor, dbul and the like (bad)

### 59. Judging cases — ཞལ་ལྕེ་གཅོད་པ་ལ་བཟང་བ

KP img. 60, KP2 img. 283.

- **Good:** Fri Sun Tue; tha skar, nabs so, mon gru, nam gru, sa ri, lag, rgyal, chu smad; days Ox, Dog
- **Bad:** Sat Mon Wed; dates 4, 8, 9, 18; mgo, skag, nag pa, snron, khrums stod, snrubs; days Horse
- **Neither side:** Thursday (in brackets)
- **Not calculated:** the links; rising signs; zhi mdo (good); phung zor, dbul and the harsh earth lords (bad)

### 60. Councils — མདུན་གྲོས་བྱེད་པར་བཟང་བ

KP img. 60, KP2 img. 283.

- **Good:** Thu Fri; snar ma, mgo, skag, mon gru, me bzhi, nag pa, dbo, mon gre, khrums stod, khrums smad, chu smad, nam gru; days Horse
- **Bad:** Tue Sat; dates 12; lha mtshams, snrubs; days Tiger, Monkey, Bird, Dog
- **Neither side:** the Sun (good for royal councils); the Moon (in brackets)
- **Not calculated:** Abhijit (bad); rising signs; the nectar periods (good); ki kang, dbul, phung zor and the like (bad)

### 50. Ordination, teaching, maṇḍalas, study, empowerment, practice — རབ་བྱུང་འཆད་ཉན་དཀྱིལ་འཁོར་འདྲི་བ་དང་བསློབ་གཉེར་དབང་བསྐུར་སྒྲུབ་མཆོད་ལ་བཟང་བ

KP2 img. 276 (not in KP).

- **Good:** Sun Thu; dates 3, 5, 10, 13, 23, 11; sa ri, sa ga, snar ma, mgo, lag, nabs so, rgyal, mchu, smin drug, me bzhi, nag pa, mon gre, mon gru, chu stod, khrums smad; days Dragon; trigrams gin
- **Bad:** Tue Sat; dates 1, 22, 15; days Sheep, Ox
- **Neither side:** the Moon and Mercury (in brackets: bad for ordination); Venus (in brackets: good for ordination and teaching); lha mtshams, chu smad, snron, nam gru, tha skar, bra nye, khrums stod (good for empowerment); dbo (good for teaching); skag and gro bzhin (bad for study); the Tiger day (bad for study); the Pig day (good, and bad for study)
- **Not calculated:** the links རྣམ་ཤེས, ཚོར་བ; rising signs; zin phung, nyi nag, Viṣṭi, 'phung byed, the Nāga planet and the like (bad); for drawing maṇḍalas the lines are laid out as the White Beryl teaches
- KP2 only. Two or three bad dates beside 1, 22 and 15 (read 4, 8, 9 by machine) are not clear on the photograph and are left out.

### 51. Dikes and protection against water — ཤྭ་རག་ཆུ་བསྲུང་སོགས་ལ་བཟང་བ

KP2 img. 277 (not in KP).

- **Good:** Sun Fri Tue; smin drug, rgyal, gre, dbo, me bzhi, sa ri, sa ga, skag, khrums stod, mon gre; days Dog
- **Bad:** Mon Wed; chu smad, khrums smad, chu stod, lag, mchu, nam gru, nag pa, snron, snrubs
- **Neither side:** Thursday and Saturn (in brackets)
- **Not calculated:** the dates of both halves (not read with confidence on the photograph); Abhijit (bad); rising signs; Rāhu's course, bstan ma, byi lam and the like
- KP2 only. The bad mansions end «དེ་ནི་ཤིན་ཏུ» (very bad), an emphasis. The heading's ཤྭ་རག is the shwa rags of the lunar dates (a dike against flash floods), and the box is listed under that wording.

### 52a. Cutting hair and nails — བཟོ་དང་སྐྲ་སེན་བྲེག་པར་བཟང་བ (སྐྲ་སེན)

KP img. 55, KP2 img. 278.

- **Good:** Fri Mon Wed; smin drug, mgo, nag pa, mchu, sa ga, lha mtshams, khrums smad, tha skar, lag, me bzhi, nam gru
- **Bad:** Sun Tue Thu Sat; snar ma, snron, chu smad, dbo, mon gru, bra nye; days Monkey, Dragon
- **Neither side:** khrums stod (good for a craft, word not read)
- **Not calculated:** Abhijit (good for shaving); the links; rising signs; the earth lords' days (bad)
- Box 52 joins crafts and cutting hair and nails, split here in two. The Moon and Mercury are good «for silver work and cutting hair and nails» (the Moon's mercury work only acceptable); smin drug, mgo, nag pa, mchu, sa ga, lha mtshams and khrums smad «for crafts and hair»; tha skar, lag, me bzhi and nam gru for shaving the head. Each bad weekday is good for one craft (the Sun gold, wood, leather and bone; Mars coral, swords and gold; Jupiter jewels; Saturn breaking up iron), so it stays bad for the hair. The White Beryl's weekday verses avoid cutting hair on the same four days.

### 52b. Crafts — བཟོ་དང་སྐྲ་སེན་བྲེག་པར་བཟང་བ (བཟོ)

KP img. 55, KP2 img. 278.

- **Good:** Fri; smin drug, mgo, nag pa, mchu, sa ga, lha mtshams, khrums smad; days Dog, Ox; trigrams kham, li
- **Bad:** snar ma, snron, chu smad, dbo, mon gru, bra nye; days Monkey, Dragon
- **Neither side:** the Moon and Mercury (for silver work only); the Sun, Mars, Jupiter and Saturn (bad, but each good for one craft); tha skar and lag (good for woodwork)
- **Not calculated:** the links; rising signs
- The crafts half of box 52 (see 52a). The Dog and Ox days and the trigram kham are good for ironwork, li for iron and pottery: kept good, since ironwork is a craft.

### 53a. Military training — དམག་རྩལ་གཤོམ་དང་ཆོ་ལོ་སོགས་བྱེད་པར་བཟང་བ (དམག་རྩལ)

KP img. 56, KP2 img. 279.

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
