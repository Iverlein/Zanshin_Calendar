# Combinations of weekday and mansion

Sigla and the rules for quoting are in [README.md](README.md). WB gives
three kinds of combination of the day's weekday and mansion, read
2026-10-04 for the weighing of the day (SPEC §5.12, [weighing.md](weighing.md)):
the 28 named combinations (*'phrod chen*), the ten element pairs (*khams kyi
sbyor ba*, the small combination), and the special days (*'grub sbyor* and
the others). Machine reading: Yigdzin-1 for pp. 333–337 (img. 341–345),
whose text is clean; the tables read on the scans (691 px), digit by digit,
and every number checked against the verses.

## The 28 named combinations

**The rule** is WB's table, vol. 1, pp. 148–149 (img. 158–159, read on the
scan): one row for each weekday, one column for each combination, the
number of the mansion it falls on. Sunday's row runs 0, 1, 2 … (kun dga' on
tha skar), Monday's from 4 (mgo), Tuesday's from 8 (skag), Wednesday's from
12 (me bzhi), Thursday's from 16 (lha mtshams), Friday's from 20 (chu smad),
Saturday's from 23 (mon gru): four mansions on for each weekday. The count
runs over 28 mansions: the table prints 21 twice, gro bzhin and byi bzhin
(Abhijit), in WB's own order of the 28, where byi bzhin follows gro bzhin
(the mansion verses, [mansion-verses.md](mansion-verses.md) 22–23; Henning
lists them the same way). Friday's row reads 20, 21, 21, 22, …: kun dga' on
chu smad, dus dbyig on gro bzhin, dul on byi bzhin, which never is the day's
mansion. `GreatCombinationTest` checks the first six columns of every row
and Sunday's last seven.

The headings of the table, in its order: ཀུན་དགའ། དུས་དབྱིག དུལ། སྐྱེ་དགུ། གཞོན།
བྱ་རོག རྒྱལ་མཚན། དཔལ་བེའུ། རྡོ་རྗེ། ཐོ་བ། གདུགས། གྲོགས། ཡིད། འདོད། མགལ་མེ།
རྩ་བཏོན། འཆི་བདག མདའ། གྲུབ། མདུང་། བདུད་རྩི། གཏུན་ཤིང་། གླང་པོ། རྟག་མྱོས།
ཟད་པ། གཡོ། བརྟན། འཕེལ།. The 24th is རྟག་མྱོས in WB's readings and table, where
Henning has *stag mo*.

**The readings**, vol. 2, pp. 331–333 (img. 339–341): a verse on each
(p. 331–332), then the short readings (p. 333, *mdor bsdus*):

> མདོར་བསྡུས་ཉུང་ངུར་བརྗོད་པའི་ལུགས། །ཀུན་དགའ་ལ་ནི་ནོར་རྙེད་འགྱུར། །དུས་ཀྱི་དབྱིག་ལ་འཇིགས་པ་ཆེ། །དུལ་བ་ལ་ནི་ནོར་མང་ཕྱུག །སྐྱེ་དགུ་གནས་སུ་བུད་མེད་སྐྱིད། །གཞོན་ལ་བྱ་བ་ཐམས་ཅད་འགྲུབ། །བྱ་རོག་ཁ་འཐབ་འཇིགས་ཤིང་བཀྲེས། །རྒྱལ་མཚན་གོས་སོགས་ཕུན་སུམ་ཚོགས། །དཔལ་བེའུ་ལ་ནི་རིན་ཅེན་འདུ། །རྡོ་རྗེ་ཐོག་དང་དེ་བཞིན་འཇིགས། །ཐོ་བས་འཆི་བའི་འཇིགས་པ་འབྱུང་། །གདུགས་ལ་དགྲ་བོ་གཞོམ་པར་འགྱུར། །གྲོགས་པོ་ལ་ནི་གྲོགས་དང་ཕྲད། །ཡིད་ལ་དཔལ་ནི་ཐོབ་པའོ། །འདོད་པས་བྱ་བ་ཡིད་སྨོན་འགྲུབ། །མགལ་མེ་ལ་ནི་འཐབ་རྩོད་འབྱུང་། །རྩ་བཏོན་ལ་ནི་ཚེ་ཟད་འགྱུར། །འཆི་བདག་ལ་ནི་འཆི་བ་འོང་། །མདའ་ལ་སྣ་དང་ཡན་ལག་ཉམས། །གྲུབ་པ་བརྩམས་པའི་བྱ་བ་འགྲུབ། །མདུང་ལ་ནད་ཀྱི་འཇིགས་པ་འབྱུང་། །བདུད་རྩི་ལ་ནི་དགྲ་འཕུང་ངོ་། །གཏུན་ལ་གཏུན་ཤིང་རིག་པར་བྱ། །གླང་པོས་དོན་ཀུན་འགྲུབ་པ་སྟེ། །རྟག་མྱོས་ལ་ནི་བཞོན་པ་རྙེད། །ཟད་ལ་ནོར་དང་སྐྱེ་བོ་འཛད། །གཡོ་ལ་རྒྱལ་པོའི་བྱ་བ་འགྲུབ། །བརྟན་ལ་སྲིད་སྡེ་འཕུང་བར་བྱེད། །འཕེལ་བ་ལ་ནི་ནོར་འཕེལ་ལོ།།

(Yigdzin reads བྲོ་བས for ཐོ་བས, the table's heading and the verse's ཐོ་བས.)
The app takes each combination's tone from its short reading: lucky for
kun dga', dul, skye dgu, gzhon, rgyal mtshan, dpal be'u, gdugs, grogs, yid,
'dod, grub, bdud rtsi, glang po, rtag myos, g.yo and 'phel; unlucky for dus
dbyig, bya rog, rdo rje, tho ba, mgal me, rtsa bton, 'chi bdag, mda', mdung
(fear of illness; its verse is milder), gtun shing, zad pa and brtan (the
state ruined). The reading of each gives the short reading and the gist of
its verse; the verses name fortunes, not activities, so they carry no
lists. The verses close: "the results set out in full, as the great paṇḍita
of Kashmir taught" (དེ་དག་རྒྱས་པར་ཕྱེ་བའི་འབྲས། །ཁ་ཆེ་པཎ་ཆེན་གསུང་བཞིན་བཀོད།).

## The ten element pairs

WB p. 333 (img. 341, l. 10–28): when planet and mansion meet, the
combination of their elements: earth and earth *dngos grub sbyor*, water
and water *bdud rtsi sbyor*, earth and water *lang tsho sbyor*, fire and
fire *'phel 'gyur sbyor*, wind and wind *phun tshogs sbyor*, fire and wind
*stobs ldan sbyor*, earth and wind *mi 'phrod sbyor*, water and wind *mi
mthun sbyor*, earth and fire *sreg pa'i sbyor*, fire and water *'chi ba'i
sbyor*, each with what it is good for (the lists of `Texts.ELEMENT_PAIR`).
It ends with how much they weigh:

> འདིར་ནི་བཟང་གསུམ་གསོ་ཐུབ་གསུམ། །ངན་གསུམ་ཐ་ཆད་གཅིག་དང་བཅུས། །བྱ་བ་ཀུན་ལ་བཟང་ངན་བྱེད། །དེ་ཕྱིར་འཕྲོད་འདི་གཟུང་བར་བྱོས། །གཟའ་སྐར་སོ་སོར་བཟང་ན་ཡང་། །འཕྲོད་ངན་དེ་ཡི་འབྲས་བུར་འགྱུར། །གཟའ་སྐར་དེ་ལས་ལྡོག་པའི་ཚེ། །འཕྲོད་བཟང་གྱུར་ན་དེ་འབྲས་འགྲུབ།།

Three good, three that can be restored, three bad and one worst: these ten
make every work good or bad, so hold to the combination. Even when the
planet and the mansion are each good, a bad combination becomes its result;
when it is the other way round, a good combination brings its result. This
puts the combination above the weekday and the mansion (SPEC §5.12).

## The special days

WB pp. 335–337 (img. 343–345), the verses; p. 341 (img. 349), their table.
After the pairs come days a weekday and a mansion make together, "many
names according to the learned, set out without loss" (pp. 337). The table
gives, for each weekday, the mansion of each; where its column is split, a
second tradition (ལུགས་གཉིས, མི་འདྲ་གཉིས), which the app counts too. Read on the
scan, by WB's numbers (Sunday … Saturday):

| Day | Table heading | Sun | Mon | Tue | Wed | Thu | Fri | Sat | Lucky |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| *'grub sbyor* | འགྲུབ་སྦྱོར | 12 | 21 | 0 | 16 | 7 | 26 | 3 | yes |
| *zung sbyor* | ཟུང་སྦྱོར | 9 | 15 | 5 | 18 | 11 | 3 | 19 | yes |
| *bdud rgyal* | བདུད་རྒྱལ | 18 | 21 (byi bzhin) | 25 | 2 | 6 | 10 | 14 | yes |
| *grub nyi* | གྲུབ་ཉི་ལུགས་གཉིས | 18, 19 | 22, 13 | 25 | 2 | 7, 6 | 10 | 15, 14 | yes |
| *bkra shis nyi ma* | བཀྲིས་ཉི་མ་ལུགས་གཉིས | 25 | 8 | 14, 10 | 17 | 11 | 2 | 3, 14 | yes |
| *'phel nyi* | འཕེལ་ཉི་མི་འདྲ་གཉིས | 3 | 14, 19 | 7, 6 | 16 | 25 | 4 | 24, 22 | yes |
| *chub nyi* | ཆུབ་ཉི | 9 | 15 | 5 | 18 | 24 | 3 | 19 | yes |
| *mthun nyi* | མཐུན་ཉི | 17 | 25 | 24 | 1 | 0 | 9 | 26 | yes |
| *sbyor nyi* | སྦྱོར་ཉི་མི་འདྲ་གཉིས | 4 | 17 | 2 | 19, 1 | 23, 22 | 0, 21 | 12, 17 | yes |
| *bdud kyi nyi ma* | བདུད་ཉི | 2 | 11 | 8 | 19 | 4 | 7 | 23 | no |
| *'chi sbyor* | འཆི་སྦྱོར | 16 | 2 | 23 | 0 | 4 | 3 | 12 | no |
| *mi 'phrod nyi ma* | མི་འཕྲོད་ཉི་ལུགས་གཉིས | 22, 21 | 15, 16 | 16 | 17, 18 | 21, 23 | 3 | 10 | no |
| *mi mthun nyi ma* | མི་མཐུན་ཉི་མི་འདྲ་གསུམ | 17, 23 | 25 | 24, 0 | 1 | 16, 15 | 9 | 26, 13 | no |
| *'jig pa'i nyi ma* | འཇིག་ཉི་མི་འདྲ་གསུམ | 15, 24 | 17, 3 | 26, 24, 23 | 12, 13 | 3 | 6, 10 | 1, 8 | no |
| *gtan spang* | བསྲེག་སྦྱོར་དང་གཏན་སྤང | 9 | 15 | 5 | 18 | 22 | 3 | 19 | no |

21 is gro bzhin but where the verses say byi bzhin (Monday's *bdud rgyal*:
ཟླ་བ་བྱི་བཞིན), which never is the day's mansion. Every cell agrees with the
verses' editors' numbers and names; `CombinationDayTest` checks *'grub
sbyor* and *'chi sbyor* against the names in the verses (*'grub sbyor*:
ཉི་མ་མེ་བཞིའི་ཁྲི་ལ་བཞུགས … གྲོ་བཞིན་ཁྱིམ་དུ་བསུ། །བཀྲ་ཤིས་ཚངས་དབྱིག་ཐོགས་ནས་བརྗོད། །ལྷག་པ་ལག་སོར་ལྷན་ཅིག་རྩེ། …;
*'chi sbyor*: ཉི་མས་ལག་པའི་སོར་མོ་བསྲེགས། །ཟླ་བས་སྨིན་དྲུག་ཟིལ་གྱིས་མནན། …).

What each names, in the verses (the app's lists, `Texts.COMBINATION_DAY`):

- *'grub sbyor*, "also called the nectar combination": whatever is done is
  good (བྱ་བའི་ལས་རྣམས་ཅི་བསྒྲུབ་བཟང་).
- *'grub sbyor* and *zung sbyor*: all pacifying, increasing and controlling
  work is virtuous, except each one's exceptions and harmful fierce work.
- *bdud rgyal*: offerings to the war gods, burying and suppressing enemies,
  attacking: rough fierce work is good.
- *grub nyi bdun*: any important and virtuous work succeeds, harmful sinful
  work is avoided; where it falls with *bdud rgyal*, good for attacking.
- *bkra shis nyi ma*: happiness; empowerment, consecration, offerings to
  deities, virtuous work.
- *'phel nyi bdun*: virtuous work, learning writing and astrology, bringing
  water, digging wells, field work, sowing.
- *chub nyi* (Thursday with khrums stod, otherwise as *zung sbyor*): nearly
  all good work is good.
- *mthun nyi*: settling quarrels, making alliances, helpful work.
- *sbyor nyi bdun* and its other tradition (*'dzom sbyor nyi*): offerings to
  deities, alliances, spectacles, virtuous and increasing work; harmful,
  destructive work bad.
- *bdud kyi nyi ma spun bdun*, the seven demon days, each with its own
  picture (the Sun's on smin drug "the demon of deity offerings", the
  Moon's "the queen's", Mars "the general's", Mercury "the grain
  measurer's", Jupiter "the priests'", Venus "the nobles'", Saturn "the
  horse dealer's") and a rite to suppress it; their own work and all work of
  the powerful, taking a bride, funerals, daughters-in-law and householders
  bad; do not set out; black magic good.
- *'chi sbyor*, pictured as the planet striking its mansion: building,
  consecration, long-life rites, sending and taking a bride, setting out,
  moving the sick mostly avoided.
- *mi 'phrod nyi*: subduing enemies and causing division good; a bride, a
  funeral, medicine and harmony-making avoided.
- *mi mthun nyi*: separating and harmful work good; marriage, funerals and
  most good work bad.
- *'jig pa'i nyi ma*: bad for a bride, relatives, horses; funerals; do not
  set out; harmful work suits it.
- *gtan spang*, "also called the burning combination": every important
  work is avoided, and bloodletting and moxibustion. "Except on Thursday it
  is alike with *zung sbyor*; what to take up and avoid matters"
  (ཕུར་བུ་མ་གཏོགས་ཟུང་སྦྱོར་དང་། །ཆ་མཐུན་འདྲ་བ་སྤང་བླང་གཅེས།): the table has the same
  mansions in both columns but Thursday's.

Then a second set "from the *Rdo rje gtsug lag*" (p. 337: *grub pa'i nyi
ma*, *bdud kyi nyi ma*, *mi mthun*, *'jig nyi*, *mi 'phrod*, with other
mansions), not in the table and not used by the app. WB closes: "of these,
the individual results of planet and mansion are the main thing, to be
examined in detail" (དེ་ཡང་གཟའ་སྐར་སོ་སོ་ཡི། །སྒེར་གྱི་འབྲས་བུ་གཙོ་བས་ཞིབ།).
