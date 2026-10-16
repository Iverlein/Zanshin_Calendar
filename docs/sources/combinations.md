# Combinations of weekday and mansion

Sigla and the rules for quoting are in [README.md](README.md). WB gives
three kinds of combination of the day's weekday and mansion, read
2026-10-04 for the weighing of the day (SPEC §5.12, [weighing.md](weighing.md)):
the 28 named combinations (*'phrod chen*), the ten element pairs (*khams kyi
sbyor ba*, the small combination), and the special days (*'grub sbyor* and
the others). Machine reading: Yigdzin-1 for pp. 333–337 (img. 341–345),
whose text is clean; the tables read on the scans (691 px), digit by digit,
and every number checked against the verses. Checked again on 2026-10-04
against BDRC's etext of the edition (pages 338–346): it leaves many
phrases out on these pages, and where it has them it disagrees only in
OCR fragments (དེ, ད་དུས, ད); no other word is offered, so the two verses
below stand as read.

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
Henning has *stag mo*. Checked again on 2026-10-08 (ROADMAP T2.17), the
heading enlarged on p. 149 (img. 159): its first stack has the same ར on
top as «བརྟན» in the same row, not the སྟ of *stag*, "tiger", which
Henning's *stag mo* has and the Russian calendar compared in
[tibetastromed.md](tibetastromed.md) reads (ROADMAP T2.17).

**The table written out** (O10e, 2026-10-14): WB vol. 1, pp. 148–149
(img. 158–159), every cell read on the Ser thang reprint of the same
typesetting (BDRC MW3CN5026, vol. 1, I3CN5028, img. 155–156, 2040 px;
image = page + 7) in strips at full size, and against BDRC's scan; the
machine readers give nothing of it (Yigdzin-1 reads a stray title line
on img. 158 and one number on img. 159). The page prints four blocks,
the headings under the cells; only the first has the column of weekdays,
headed «འཕྲོད།» (combination), each with its number (ཉི, Sunday, ༡ … སྤེན,
Saturday, ༠); in the other three the rows run in the same order, and
their labels are given here in brackets, not printed. Monday's label is
a blot in both scans, read [ཟླ] from its place and number. Every cell
agrees with the rule above: each weekday's row runs on by one over WB's
28, with ༢༡ printed for both gro bzhin and byi bzhin.

Block 1, p. 148 (img. 158; Ser thang img. 155):

| འཕྲོད། | ཀུན་དགའ། | དུས་དབྱིག | དུལ། | སྐྱེ་དགུ། | གཞོན། | བྱ་རོག |
| --- | --- | --- | --- | --- | --- | --- |
| ཉི ༡ | ༠ | ༡ | ༢ | ༣ | ༤ | ༥ |
| [ཟླ] ༢ | ༤ | ༥ | ༦ | ༧ | ༨ | ༩ |
| མིག ༣ | ༨ | ༩ | ༡༠ | ༡༡ | ༡༢ | ༡༣ |
| ལྷག ༤ | ༡༢ | ༡༣ | ༡༤ | ༡༥ | ༡༦ | ༡༧ |
| ཕུར ༥ | ༡༦ | ༡༧ | ༡༨ | ༡༩ | ༢༠ | ༢༡ |
| སངས ༦ | ༢༠ | ༢༡ | ༢༡ | ༢༢ | ༢༣ | ༢༤ |
| སྤེན ༠ | ༢༣ | ༢༤ | ༢༥ | ༢༦ | ༠ | ༡ |

Block 2, p. 148 (img. 158; Ser thang img. 155):

| འཕྲོད། | རྒྱལ་མཚན། | དཔལ་བེའུ། | རྡོ་རྗེ། | ཐོ་བ། | གདུགས། | གྲོགས། | ཡིད། |
| --- | --- | --- | --- | --- | --- | --- | --- |
| (ཉི ༡) | ༦ | ༧ | ༨ | ༩ | ༡༠ | ༡༡ | ༡༢ |
| ([ཟླ] ༢) | ༡༠ | ༡༡ | ༡༢ | ༡༣ | ༡༤ | ༡༥ | ༡༦ |
| (མིག ༣) | ༡༤ | ༡༥ | ༡༦ | ༡༧ | ༡༨ | ༡༩ | ༢༠ |
| (ལྷག ༤) | ༡༨ | ༡༩ | ༢༠ | ༢༡ | ༢༡ | ༢༢ | ༢༣ |
| (ཕུར ༥) | ༢༡ | ༢༢ | ༢༣ | ༢༤ | ༢༥ | ༢༦ | ༠ |
| (སངས ༦) | ༢༥ | ༢༦ | ༠ | ༡ | ༢ | ༣ | ༤ |
| (སྤེན ༠) | ༢ | ༣ | ༤ | ༥ | ༦ | ༧ | ༨ |

Block 3, p. 149 (img. 159; Ser thang img. 156):

| འཕྲོད། | འདོད། | མགལ་མེ། | རྩ་བཏོན། | འཆི་བདག | མདའ། | གྲུབ། | མདུང་། | བདུད་རྩི། |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| (ཉི ༡) | ༡༣ | ༡༤ | ༡༥ | ༡༦ | ༡༧ | ༡༨ | ༡༩ | ༢༠ |
| ([ཟླ] ༢) | ༡༧ | ༡༨ | ༡༩ | ༢༠ | ༢༡ | ༢༡ | ༢༢ | ༢༣ |
| (མིག ༣) | ༢༡ | ༢༡ | ༢༢ | ༢༣ | ༢༤ | ༢༥ | ༢༦ | ༠ |
| (ལྷག ༤) | ༢༤ | ༢༥ | ༢༦ | ༠ | ༡ | ༢ | ༣ | ༤ |
| (ཕུར ༥) | ༡ | ༢ | ༣ | ༤ | ༥ | ༦ | ༧ | ༨ |
| (སངས ༦) | ༥ | ༦ | ༧ | ༨ | ༩ | ༡༠ | ༡༡ | ༡༢ |
| (སྤེན ༠) | ༩ | ༡༠ | ༡༡ | ༡༢ | ༡༣ | ༡༤ | ༡༥ | ༡༦ |

Block 4, p. 149 (img. 159; Ser thang img. 156):

| འཕྲོད། | གཏུན་ཤིང་། | གླང་པོ། | རྟག་མྱོས། | ཟད་པ། | གཡོ། | བརྟན། | འཕེལ། |
| --- | --- | --- | --- | --- | --- | --- | --- |
| (ཉི ༡) | ༢༡ | ༢༡ | ༢༢ | ༢༣ | ༢༤ | ༢༥ | ༢༦ |
| ([ཟླ] ༢) | ༢༤ | ༢༥ | ༢༦ | ༠ | ༡ | ༢ | ༣ |
| (མིག ༣) | ༡ | ༢ | ༣ | ༤ | ༥ | ༦ | ༧ |
| (ལྷག ༤) | ༥ | ༦ | ༧ | ༨ | ༩ | ༡༠ | ༡༡ |
| (ཕུར ༥) | ༩ | ༡༠ | ༡༡ | ༡༢ | ༡༣ | ༡༤ | ༡༥ |
| (སངས ༦) | ༡༣ | ༡༤ | ༡༥ | ༡༦ | ༡༧ | ༡༨ | ༡༩ |
| (སྤེན ༠) | ༡༧ | ༡༨ | ༡༩ | ༢༠ | ༢༡ | ༢༡ | ༢༢ |

**The readings**, vol. 2, pp. 331–333 (img. 339–341): a verse on each
(p. 331–332), then the short readings (p. 333, *mdor bsdus*):

> མདོར་བསྡུས་ཉུང་ངུར་བརྗོད་པའི་ལུགས། །ཀུན་དགའ་ལ་ནི་ནོར་རྙེད་འགྱུར། །དུས་ཀྱི་དབྱིག་ལ་འཇིགས་པ་ཆེ། །དུལ་བ་ལ་ནི་ནོར་མང་ཕྱུག །སྐྱེ་དགུ་གནས་སུ་བུད་མེད་སྐྱིད། །གཞོན་ལ་བྱ་བ་ཐམས་ཅད་འགྲུབ། །བྱ་རོག་ཁ་འཐབ་འཇིགས་ཤིང་བཀྲེས། །རྒྱལ་མཚན་གོས་སོགས་ཕུན་སུམ་ཚོགས། །དཔལ་བེའུ་ལ་ནི་རིན་ཅེན་འདུ། །རྡོ་རྗེ་ཐོག་དང་དེ་བཞིན་འཇིགས། །ཐོ་བས་འཆི་བའི་འཇིགས་པ་འབྱུང་། །གདུགས་ལ་དགྲ་བོ་གཞོམ་པར་འགྱུར། །གྲོགས་པོ་ལ་ནི་གྲོགས་དང་ཕྲད། །ཡིད་ལ་དཔལ་ནི་ཐོབ་པའོ། །འདོད་པས་བྱ་བ་ཡིད་སྨོན་འགྲུབ། །མགལ་མེ་ལ་ནི་འཐབ་རྩོད་འབྱུང་། །རྩ་བཏོན་ལ་ནི་ཚེ་ཟད་འགྱུར། །འཆི་བདག་ལ་ནི་འཆི་བ་འོང་། །མདའ་ལ་སྣ་དང་ཡན་ལག་ཉམས། །གྲུབ་པ་བརྩམས་པའི་བྱ་བ་འགྲུབ། །མདུང་ལ་ནད་ཀྱི་འཇིགས་པ་འབྱུང་། །བདུད་རྩི་ལ་ནི་དགྲ་འཕུང་ངོ་། །གཏུན་ལ་གཏུན་ཤིང་རིག་པར་བྱ། །གླང་པོས་དོན་ཀུན་འགྲུབ་པ་སྟེ། །རྟག་མྱོས་ལ་ནི་བཞོན་པ་རྙེད། །ཟད་ལ་ནོར་དང་སྐྱེ་བོ་འཛད། །གཡོ་ལ་རྒྱལ་པོའི་བྱ་བ་འགྲུབ། །བརྟན་ལ་སྲིད་སྡེ་འཕུང་བར་བྱེད། །འཕེལ་བ་ལ་ནི་ནོར་འཕེལ་ལོ།།

(Yigdzin reads བྲོ་བས for ཐོ་བས, the table's heading and the verse's ཐོ་བས.)
In *'chi bdag*'s verse the scan reads «དགྲ་ཀུན», all enemies, where Yigdzin-1
read «རྐུན», thieves; the reading said "enemies and thieves" until X5
(2026-10-16). The line «དགྲ་ཀུན་བླ་མའི་ཤིད་བྱེད་པ», like *tho ba*'s «གནམ་ལྕགས་གདོང་དང་སེང་གེའི་བུ»,
*brtan pa*'s «ས་དང་ལམ་དང་བདུད་མི་གཡོ» and *dus dbyig*'s doubtful «ཐོད་རྒལ་དགྲ་ལ་མེ་ཆུ་ཆེ»,
has no meaning the verse gives, so the readings leave it out (SPEC §8.1, rule 7).
The app takes each combination's tone from its short reading: lucky for
kun dga', dul, skye dgu, gzhon, rgyal mtshan, dpal be'u, gdugs, grogs, yid,
'dod, grub, bdud rtsi, glang po, rtag myos, g.yo and 'phel; unlucky for dus
dbyig, bya rog, rdo rje, tho ba, mgal me, rtsa bton, 'chi bdag, mda', mdung
(fear of illness; its verse is milder), gtun shing, zad pa and brtan (the
state ruined). The reading of each gives the short reading and the gist of
its verse; the verses name fortunes, not activities, so they carry no
lists. The verses close: "the results set out in full, as the great paṇḍita
of Kashmir taught" (དེ་དག་རྒྱས་པར་ཕྱེ་བའི་འབྲས། །ཁ་ཆེ་པཎ་ཆེན་གསུང་བཞིན་བཀོད།).

## The text of pp. 331–333

(O10, 2026-10-13.) WB vol. 2, pp. 331–333 (img. 339–341): the verse on each
of the 28 named combinations, their short readings, and the ten element
pairs with the verse on their weight. Base text Yigdzin-1 on BDRC's scan,
MITRA as witness for pp. 331–332 (`disagree.py`: 2 and 4 flags, each read
on the scan); every line then read against the Ser thang reprint of the
same typesetting (BDRC MW3CN5026, vol. 2, I3CN5029, img. 337–339, 1905 px)
in strips, the doubtful stacks at 2–5×. The scan corrected the readers in
«ངེས་པར་འཚོར» (bya rog), «རྩ་བཏོན», «རིགས་བརྒྱུད» (brtan pa), «དགྲ་ཀུན» ('chi bdag;
Yigdzin-1 རྐུན) and «ཐོ་བས» (p. 333). Read as printed: «ངུ་འབོད» (tho ba),
«དན་འོང» (rtag myos) and «དན་བསམ» (brtan pa), for དོན. Doubtful: dus dbyig's
«མེ་ཆུ་ཆེ», so in both readers, where the reprint's vowel looks like ི and
the stack after it is thick; and in rtag myos the first letter of «བཟང»
is broken on the reprint.

p. 331 (img. 339; Ser thang img. 337):

> ཀུན་དགའ་ལ་སོགས་འདིར་བརྗོད་བྱ། །གཟའ་སྐར་ཀུན་དགར་འཕྲོད་པ་ན། །ཚེ་དང་བཀྲག་དང་གཟི་བརྗིད་རྒྱས། །དཔལ་དང་འབྱོར་པ་བསོད་ནམས་འབར། །ལས་དང་བྱ་བ་བསམ་པ་འགྲུབ། །ནོར་བུ་ཚེ་ཡི་དངོས་གྲུབ་རྙེད།།གཟའ་སྐར་དུས་དབྱིག་འཕྲོད་པ་ན། །ནམ་མཁའ་མདངས་ཉམས་མེ་ཏོག་ཡལ། །རྩི་ཤིང་ཆུ་རྣམས་སྐམས་པར་བྱེད། །འཇིགས་པའི་དགྲ་འོང་གསོད་པ་འབྱུང་། །ཐོད་རྒལ་དགྲ་ལ་མེ་ཆུ་ཆེ། །གཟའ་སྐར་དུལ་བར་ཕྲད་པ་ན། །བསམ་འགྲུབ་བློ་དང་བདེ་བར་ལྡན། །དཔག་བསམ་འབྲས་བུ་ཚེ་ཡི་དཔལ། །བདེ་སྐྱིད་ནོར་དང་ཆོས་སུ་ལྡན། །ནོར་བུ་ནོར་བདག་ཚེ་རིང་སྐྱིད། །སྐྱེ་དགུ་སྨོན་པའི་བྱ་བ་འགྲུབ། །བློ་གྲོས་མོས་བསམ་རབ་རྒྱས་ཤིང་། །བཀྲ་ཤིས་མི་ནོར་དོན་དུ་བཟང་། །ཐ་དད་སྨོན་པའི་བུ་སྨད་སྐྱིད།།གཞོན་ལ་སྟག་གི་རི་མོ་བཀྲ། །སྔར་ཟོས་རྒྱལ་བ་ཚེ་ཡི་ཞག །ཅུང་ཟད་སྲོག་ཤིང་མེ་ཏོག་གཏེར། །བུམ་པར་ཚེ་ཡི་ཞག་འཚོ་ཞིང་། །དགྲ་བཅོམ་གཡུལ་ལས་རྒྱལ་བ་ཡིན། །བྱ་རོག་མི་རྟག་ཚེ་ཡི་གཏེར། །སྲོག་གི་མདངས་ཀྱང་ངེས་པར་འཚོར། །བཟའ་བཏུང་བཅུད་ཀྱི་འབྲས་བུ་མེད། །ལྷ་མིའི་བདེ་བ་སྡུག་བསྔལ་བྱེད། །རྒྱལ་མཚན་ནོར་གོས་ཕུན་སུམ་ཚོགས། །བཀྲག་དང་གཟི་མདངས་རི་མོ་བཀྲ། །ཡིད་འོང་དཔལ་ལྡན་བཀྲ་ཤིས་སྐྱིད། །མི་ནོར་དགེ་བའི་རོལ་མོ་བཟང་། །དཔལ་བེའུ་དབང་ཕྱུག་བུ་དང་ལྡན། །དཔལ་ཆེན་དར་བ་རྟིང་ནས་བཟང་། །བདེ་སྐྱིད་ལྷ་མོའི་ལས་བཟང་ཞིང་། །དཔལ་ལྡན་ཅན་གྱིས་སྲོག་བཅོམ་འགྱུར། །རྡོ་རྗེ་དྲག་པོ་ཐོག་སེར་འབེབས། །རང་ཉིད་མི་བདེ་གཞན་རྣམས་འཕུང་། །མི་དགེ་སྣ་ཚོགས་ལས་བསྐྱེད་ཅིང་། །དགྲ་ཇག་ནད་རིམས་འཇིགས་པ་འབྱུང་། །ཐོ་བས་ཞེ་སྡང་སྲོག་གཅོད་བྱེད། །གནམ་ལྕགས་གདོང་དང་སེང་གེའི་བུ། །ངུ་འབོད་རྩོད་དང་མི་ཤེས་པ། །རྐང་བཞིའི་སྲོག་ལ་ངེས་པར་སྡང་།།གདུགས་ལ་སེང་གེའི་བུ་སྐྱིད་ཅིང་། །རྩོད་པའི་མེ་ཏོག་ཀུན་སྟེར་བྱེད། །དབང་བཞིའི་དཔའ་བ་དཔུང་དང་ལྡན། །ཞེ་སྡང་རྩོད་པའི་དགྲ་བཅོམ་འགྲུབ།།གྲོགས་ནི་དཔལ་དང་དྲིན་དུ་བསམ། །འདོད་པ་དབང་དང་ཐབས་རྟེན་པ། །བསམ་འགྲུབ་བཀྲག་ལྡན་ཚེ་ཡི་གཏེར། །དཔལ་དང་འདོད་པའི་དོན་རྣམས་འགྲུབ།།ཡིད་ལ་ནོར་བུ་ཚེ་ཡི་གཏེར། །རིན་ཅེན་དཔལ་དང་དབང་ཐོབ་ཅིང་། །རྒྱལ་བའི་བུ་དང་ལེགས་པའི་ལམ། །དཔལ་དང་བསམ་པའི་དོན་འགྲུབ་བོ།།འདོད་པ་གཉེན་གྱི་ལམ་ཡིན་ཞིང་། །བསགས་པའི་ནོར་བུ་ཡིད་འོང་འདི། །འཇིག་རྟེན་ཤིས་དང་རྒྱལ་བ་སྟེ། །བསམས་པ་ཙམ་གྱིས་འདོད་པ་འགྲུབ།།

p. 332 (img. 340; Ser thang img. 338):

> མགལ་མེ་འཇིགས་སྐྲག་འཐབ་པའི་ས། །མི་ཁའི་རྫས་དང་གཏམ་སྣོལ་ཡིན། །ཟས་ཀྱི་རོ་འདོད་དམར་ལམ་འབར། །རིགས་ངན་གཉེན་དང་འཐབ་པའི་ས། །རྩ་བཏོན་སྟོན་པའི་དགྲ་བཅོམ་སྟེ། །བཀྲ་ཤིས་དོན་བསམ་འཕུང་བར་འགྱུར། །ཆོས་ནོར་དབང་བསྐུར་རབ་གནས་འཕམ། །འཕགས་པའི་ནོར་ཡང་ཆུད་ཟོས་སོ།།འཆི་བདག་མཁའ་ཡི་གཅན་གཟན་ཏེ། །དགྲ་ཀུན་བླ་མའི་ཤིད་བྱེད་པ། །འཇིགས་པའི་ས་དང་ལམ་ལ་འཁྲིད།།མདའ་ལ་སྣ་དང་དབང་པོ་ཉམས། །བཀྲག་དང་ཕུང་པོ་བསམ་པར་བྱེད། །རིག་བྱེད་ཚེ་ཡི་བར་གཅོད་ཅིང་། །ལས་དང་བཀྲ་ཤིས་ཉམས་པ་ཡིན།།གྲུབ་པ་གྲུབ་མཆོག་འབར་བ་སྟེ། །ལེགས་པའི་གཏམ་དང་ཡོ་བྱད་ལྡན། །དགའ་མཆོག་བློ་གྲོས་དཔའ་གསལ་ཞིང་། །ཡིད་བསམ་བཀྲ་ཤིས་དཔལ་གྲུབ་བོ།།མདུང་ནི་རབ་རྒྱས་ཞི་བས་ཏེ། །ཐོག་མཐའ་བར་དང་ལེགས་བསམ་པ། །རྒྱལ་མོ་རབས་ཆད་སྐུ་བཀྲུ་བ། །ས་ཡི་ལས་དང་པདྨའི་མདངས། །བྱང་ཕྱོགས་སྒྲ་ལྡན་བུ་ཚ་སྟེ།།བདུད་རྩི་ཕལ་ཆེར་ཡིད་འོང་ཞིང་། །གསོ་བ་གསོ་བྱེད་མཆོག་ཡིན་ཏེ། །གསོ་བ་དགྲ་དང་འཇིགས་པའི་གཤེད། །དགྲ་ཟད་དཔལ་དང་ནོར་ཡང་ཐོབ།།གཏུན་ཤིང་བདུད་ཀྱི་བུ་ཡིན་ཏེ། །ཚེ་ཟད་ནོར་ཟད་ལྷ་ཡང་ཟད། །བཀྲ་ཤིས་ཟད་དང་མགོ་རྣམས་འཆག །གཏུན་ཤིང་མ་རིག་མགོ་འཆག་གོ །གླང་པོ་གླང་ཆེན་ཤུགས་ལས་མགྱོགས། །འགྲོ་འོང་འདུག་མལ་ཀུན་བདེ་ཞིང་། །གཏམ་དང་གྲགས་པ་ལས་གྲུབ་པ། །གླང་པོ་ཀུན་གྱིས་སྨོན་པ་འགྲུབ།།རྟག་མྱོས་རིན་ཅེན་ཚེ་སྐྱིད་ཅིང་། །རྒྱ་མཚོའི་པདྨོ་ཀུན་འཕེལ་བས། །ལས་བཟང་འགྲུབ་པའི་དན་འོང་ཞིང་། །བཞོན་པ་མི་ནོར་དཔལ་དང་ལྡན།།ཟད་པ་ཟད་ཆེན་ཚེ་ཟད་ཡིན། །ནོར་ཟད་གཏམ་ཟད་ཤུགས་ཟད་པ། །རོ་ཟད་མཁར་ཟད་རྫས་ཟད་པ། །ལས་དང་བསོད་ནམས་འབྲས་བུ་ཟད།།གཡོ་ནི་མི་ཡི་བླ་མ་བྱེད། །རྒྱལ་ས་རྒྱལ་ཕྲན་ས་ཡི་ལས། །དྲག་ཤུལ་སྨོན་པའི་ལས་འགྲུབ་ཅིང་། །རྒྱལ་ཆེན་བཞི་ཡང་ཕྱུག་པར་འགྱུར།།བརྟན་པ་དགྲ་ནི་རྒྱལ་བས་ཏེ། །འཁོར་དང་རིགས་བརྒྱུད་བཀྲ་མི་ཤིས། །ས་དང་ལམ་དང་བདུད་མི་གཡོ། །དན་བསམ་སྲིད་སྡེ་འཕུང་བར་འགྱུར།འཕེལ་བ་བརྟན་པའི་མཆོག་འཕེལ་ཞིང་། །ལྷ་ཡི་དབང་པོ་བློ་ཡང་འཕེལ། །བློ་གྲོས་རྒྱས་ཤིང་བསམ་འཕེལ་བྱེད། །བཀྲག་དང་གཟི་བརྗིད་ཆེར་འཕེལ་ཞིང་། །འཕེལ་བ་ཀུན་ལ་འཕེལ་བས་བཟང་། །དེ་དག་རྒྱས་པར་ཕྱེ་བའི་འབྲས། །ཁ་ཆེ་པཎ་ཆེན་གསུང་བཞིན་བཀོད།།

p. 333 (img. 341; Ser thang img. 339):

> མདོར་བསྡུས་ཉུང་ངུར་བརྗོད་པའི་ལུགས། །ཀུན་དགའ་ལ་ནི་ནོར་རྙེད་འགྱུར། །དུས་ཀྱི་དབྱིག་ལ་འཇིགས་པ་ཆེ། །དུལ་བ་ལ་ནི་ནོར་མང་ཕྱུག །སྐྱེ་དགུ་གནས་སུ་བུད་མེད་སྐྱིད། །གཞོན་ལ་བྱ་བ་ཐམས་ཅད་འགྲུབ། །བྱ་རོག་ཁ་འཐབ་འཇིགས་ཤིང་བཀྲེས། །རྒྱལ་མཚན་གོས་སོགས་ཕུན་སུམ་ཚོགས། །དཔལ་བེའུ་ལ་ནི་རིན་ཅེན་འདུ། །རྡོ་རྗེ་ཐོག་དང་དེ་བཞིན་འཇིགས། །ཐོ་བས་འཆི་བའི་འཇིགས་པ་འབྱུང་། །གདུགས་ལ་དགྲ་བོ་གཞོམ་པར་འགྱུར། །གྲོགས་པོ་ལ་ནི་གྲོགས་དང་ཕྲད། །ཡིད་ལ་དཔལ་ནི་ཐོབ་པའོ། །འདོད་པས་བྱ་བ་ཡིད་སྨོན་འགྲུབ། །མགལ་མེ་ལ་ནི་འཐབ་རྩོད་འབྱུང་། །རྩ་བཏོན་ལ་ནི་ཚེ་ཟད་འགྱུར། །འཆི་བདག་ལ་ནི་འཆི་བ་འོང་། །མདའ་ལ་སྣ་དང་ཡན་ལག་ཉམས། །གྲུབ་པ་བརྩམས་པའི་བྱ་བ་འགྲུབ། །མདུང་ལ་ནད་ཀྱི་འཇིགས་པ་འབྱུང་། །བདུད་རྩི་ལ་ནི་དགྲ་འཕུང་ངོ་། །གཏུན་ལ་གཏུན་ཤིང་རིག་པར་བྱ། །གླང་པོས་དོན་ཀུན་འགྲུབ་པ་སྟེ། །རྟག་མྱོས་ལ་ནི་བཞོན་པ་རྙེད། །ཟད་ལ་ནོར་དང་སྐྱེ་བོ་འཛད། །གཡོ་ལ་རྒྱལ་པོའི་བྱ་བ་འགྲུབ། །བརྟན་ལ་སྲིད་སྡེ་འཕུང་བར་བྱེད། །འཕེལ་བ་ལ་ནི་ནོར་འཕེལ་ལོ།།དེ་ནས་གཟའ་སྐར་གཉིས་ཕྲད་པར། །ཁམས་ཀྱི་སྦྱོར་བ་འདི་ལྟ་སྟེ། །ས་གཉིས་ཕྲད་པ་དངོས་གྲུབ་སྦྱོར། །དངོས་གྲུབ་སྦྱོར་བས་ཅི་བསམ་འགྲུབ། །རྟེན་བཞེངས་མཁར་བརྩིག་ཞིང་ཉོ་ཞིང་། །རྒྱས་པའི་ལས་ལ་འབད་པ་དང་། །འདུན་མ་བྱེད་སོགས་བརྟན་ལས་བཟང་། །ཆུ་གཉིས་ཕྲད་པ་བདུད་རྩི་སྦྱོར། །བདུད་རྩི་སྦྱོར་བས་ཚེ་སྟོབས་འཕེལ། །གཉེན་ཚོང་འཕེལ་དང་ཞིང་ལས་བཟང་། །སྨན་སྦྱོར་ཁྲུས་བྱ་བག་མ་བླང་། །ཚེ་དབང་ཚེ་སྒྲུབ་ཚེ་གཟུངས་སོགས། །ཚེ་བསྐྱེད་ཆོ་ག་མཆོག་ཏུ་ཤིས། །ས་ཆུ་ཕྲད་པ་ལང་ཚོ་སྦྱོར། །ལང་ཚོ་སྦྱོར་བས་ཤིན་ཏུ་སྐྱིད། །གོས་གྱོན་རྒྱན་འདོགས་རོལ་རྩེད་དང་། །དགའ་སྟོན་བྱ་ཞིང་ཆེ་སར་འདོན། །མེ་གཉིས་ཕྲད་པ་འཕེལ་འགྱུར་སྦྱོར། །འཕེལ་འགྱུར་སྦྱོར་བས་ཟས་གོས་རྙེད། །ཚོང་བྱ་འབྲུ་གདབ་སྦྱིན་པ་གཏོང་། །ནོར་རྒྱུན་མ་སྒྲུབ་དགེ་ལས་བྱ། །ནོར་ལྷ་མཆོད་ཅིང་དངོས་གྲུབ་དབང་། །རླུང་གཉིས་ཕྲད་པ་ཕུན་ཚོགས་སྦྱོར། །ཕུན་ཚོགས་སྦྱོར་བས་བསམ་མྱུར་འགྲུབ། །འགྲུལ་བྱ་དོན་གཉེར་ཁ་མཆུ་བསྡུམས། །གཡོ་བའི་ལས་དང་དྲག་པོའི་ལས། །གཞན་ཡང་མྱུར་བའི་ལས་རྣམས་འགྲུབ། །མེ་རླུང་ཕྲད་པ་སྟོབས་ལྡན་སྦྱོར། །སྟོབས་ལྡན་སྦྱོར་བས་བཀྲ་ཤིས་བྱེད། །རིམ་གྲོ་ལྷ་གསོལ་ཆོས་སྤྱོད་བྱ། །ཞི་རྒྱས་དབང་གི་ལས་རྣམས་སྒྲུབ། །ས་རླུང་ཕྲད་པ་མི་འཕྲོད་སྦྱོར། །མི་འཕྲོད་སྦྱོར་བས་ཟས་ནོར་འཛད། །ཇག་རྐུན་འཇོམས་འཕྲོག་བྱེད་པ་སོགས། །མྱ་ངན་འོང་ལ་དོན་མི་འགྲུབ། །ནོར་གྱི་སྤེལ་ཚོགས་བསྒྲུབ་ཀྱང་ཟེར། །ཆུ་རླུང་ཕྲད་པ་མི་མཐུན་སྦྱོར། །མི་མཐུན་སྦྱོར་བས་གཉེན་བཤེས་འབྱེད། །དབྱེན་དང་ཕྲ་མ་འཇུག་པ་དང་། །དགྲ་བོ་ཕྱུང་བའི་ལས་རྣམས་བྱ། །ས་མེ་ཕྲད་པ་སྲེག་པའི་སྦྱོར། །སྲེག་པའི་སྦྱོར་བས་སྡུག་བསྔལ་བསྐྱེད། །དམག་འདེགས་དགྲ་བོ་གཞོམ་པ་དང་། །དྲག་ལས་དང་ནི་རྩོད་པ་དང་། །གཞན་ཡང་ཕ་རོལ་སྡུག་བསྔལ་བའི། །ལས་རྣམས་འགྲུབ་པར་བྱ་བ་ཡིན། །མེ་ཆུ་ཕྲད་པ་འཆི་བའི་སྦྱོར། །འཆི་བའི་སྦྱོར་བས་སྲོག་འཕྲོག་བྱེད། །ཕ་རོལ་གསོད་དང་མཐུ་གཏད་དང་། །དུག་སྦྱོར་ལ་སོགས་རིག་པས་བསྒྲུབ། །འདིར་ནི་བཟང་གསུམ་གསོ་ཐུབ་གསུམ། །ངན་གསུམ་ཐ་ཆད་གཅིག་དང་བཅུས། །བྱ་བ་ཀུན་ལ་བཟང་ངན་བྱེད། །དེ་ཕྱིར་འཕྲོད་འདི་གཟུང་བར་བྱོས། །གཟའ་སྐར་སོ་སོར་བཟང་ན་ཡང་། །འཕྲོད་ངན་དེ་ཡི་འབྲས་བུར་འགྱུར། །གཟའ་སྐར་དེ་ལས་ལྡོག་པའི་ཚེ། །འཕྲོད་བཟང་གྱུར་ན་དེ་འབྲས་འགྲུབ།།

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

Three good, three that sustain (*gso thub*), three bad and one worst: these
ten make every work good or bad, so hold to the combination. Even when the
planet and the mansion are each good, a bad combination becomes its result;
when it is the other way round, a good combination brings its result. This
puts the combination above the weekday and the mansion (SPEC §5.12).

**The grades, by the verse's order** (checked 2026-10-06 against BDRC's
etext IE0OPI51524892, which has the pairs in the same order): good,
*dngos grub*, *bdud rtsi*, *lang tsho*; sustaining, *'phel 'gyur* (food and
clothes found), *phun tshogs* (wishes quickly accomplished), *stobs ldan*
(good omens); bad, *mi 'phrod*, *mi mthun*, *sreg pa*; worst, *'chi ba*.
*Gso* is to nourish, to raise; the three sustaining verses name only good
results, so the app counts them lucky with the good three (`PairGrade`),
and each pair's reading names its grade.

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

Then a second set "from the *Rdo rje gtsug lag*", which WB closes with:
"of these, the individual results of planet and mansion are the main thing,
to be examined in detail" (དེ་ཡང་གཟའ་སྐར་སོ་སོ་ཡི། །སྒེར་གྱི་འབྲས་བུ་གཙོ་བས་ཞིབ།).

### The verses written out

(O10, 2026-10-13.) WB vol. 2, p. 334, last line, to p. 337, l. 25 (img.
342–345): the special days and the *Rdo rje gtsug lag*'s, to the close
quoted above. Read on the Ser thang reprint of the same typesetting
(BDRC MW3CN5026, vol. 2, I3CN5029, img. 340–343, 1905 px) line by line,
with Yigdzin-1's reading of BDRC's scan as base; the readers garble the
editors' bracketed numbers (Yigdzin-1 drops the brackets and often
doubles a digit), so every number was read on the reprint and checked
against the two tables above, which it matches cell for cell but one:
in the *Rdo rje gtsug lag*'s day of incompatibility Mercury is printed
«ལྷག་པར(༧)», where every other Mercury is (༤); the mansion that follows,
སྣྲུབས(༡༨), is the table's. Read as printed: «གྲོ་ཞུན(༢༡)», «ས་གའམ(༡༥)», «ལྷག(༤)སོག་པ(༡༨)»
(gtan spang's Wednesday, where the other verses write སྣྲུབས), «པ་བ་སངས»,
«ཕ་མིང». Doubtful: «ཧྲི» (Yigdzin-1 ཏྲི) in the Sun's demon day, and the last
syllable of the Venus demon day's line «…འོག་ཏུ་[སྦད]», whose final is not
clear on the reprint. The Moon's demon day names no number in the verse.

p. 334 (img. 342; Ser thang img. 340), the last line:

> །ཉི་མ(༡)མེ་བཞིའི(༡༢)ཁྲི་ལ་བཞུགས། །ཟླ་བས(༢)

p. 335 (img. 343; Ser thang img. 341):

> གྲོ་བཞིན(༢༡)ཁྱིམ་དུ་བསུ། །བཀྲ་ཤིས(༣)ཚངས་དབྱིག(༠)ཐོགས་ནས་བརྗོད། །ལྷག་པ(༤)ལག་སོར(༡༦)ལྷན་ཅིག་རྩེ། །ལྷ་ཡི་བླ་མ(༥)རྒྱལ(༧)སར་ཕེབས། །དཀར་པོས(༦)ནམ་གྲུའི(༢༦)ལག་ནས་བཟུང་། །སྤེན་པས(༠)སྣར་མ(༣)གྲོགས་སུ་ཁྲིད། །འདི་དག་འགྲུབ་པའི་སྦྱོར་བ་ཡིན། །བདུད་རྩི་སྦྱོར་བ་ཞེས་བྱ་སྟེ། །བྱ་བའི་ལས་རྣམས་ཅི་བསྒྲུབ་བཟང་།། གཞན་ཡང་ཟུང་སྦྱོར་ཞེས་བྱ་བ། །ཉི་མ(༡)མཆུ(༩)དང་དུས་གཅིག་འཛོམ། །ཟླ་བ(༢)ས་ག(༡༥)མིག་དམར(༣)ལག(༥)། །ལྷག་པ(༤)སྣྲུབས(༡༨)དང་ཕུར་བུ(༥)དབོ(༡༡)། །པ་སངས(༦)སྣར་མ(༣)སྤེན(༠)ཆུ་སྟོད(༡༩)། །འགྲུབ་སྦྱོར་ཟུང་སྦྱོར་ཤར་བ་ན། །རང་རང་དམིགས་བསལ་ལས་སྤང་དང་། །ངན་བྱེད་དྲག་པོའི་ལས་མ་གཏོགས། །ཞི་རྒྱས་དབང་གི་ལས་རྣམས་ཀུན། །དགེ་ཞིང་ཁྱད་པར་ནང་གསེས་ཀྱི། །དབྱེ་བའི་ཁྱད་པར་ལོགས་སུ་བསྟན། །ཉི(༡)སྣྲུབས(༡༨)ཟླ་བ(༢)བྱི་བཞིན(༢༡)དང་། །མིག་དམར(༣)ཁྲུམས་སྨད(༢༥)ལྷག(༤)སྨིན་དྲུག(༢)། །ཕུར་བུ(༥)ནབས་སོ(༦)པ་སངས(༦)གྲེ(༡༠)། །སྤེན་པ(༠)ས་རི(༡༤)ཕྲད་པ་ན། །བདུད་རྒྱལ་དགྲ་ལྷ་མཆོད་པ་དང་། །དགྲ་བྲུབ་དགྲ་མནན་དགྲ་ལ་རྒོལ། །རྩུབ་འགྱུར་དྲག་པོའི་ལས་རྣམས་བཟང་། །ཉི་མ(༡)སྣྲུབས(༡༨)དང་ཟླ(༢)མོན་དྲེ(༢༢)། །མིག་དམར(༣)ཁྲུམས་སྨད(༢༥)ལྷག(༤)སྨིན་དྲུག(༢)། །ཕུར་བུ(༥)རྒྱལ(༧)དང་པ་སངས(༦)གྲེ(༡༠)། །སྤེན་པ(༠)ས་ག(༡༥)ལུགས་གཅིག་ལ། །ཉི་མ(༡)ཆུ་སྟོད(༡༩)ཟླ་བ(༢)ནག(༡༣)། །ཕུར་བུ(༥)ནབས་སོ(༦)སྤེན(༠)ས་རི(༡༤)། །འཛོམ་པ་གྲུབ་ཉི་བདུན་ཞེས་བྱ། །གལ་ཆེའི་བྱ་བ་ཅི་ཡང་དང་། །དགེ་ཞིང་བཟང་བའི་ལས་རྣམས་འགྲུབ། །གནོད་བྱེད་སྡིག་པའི་ལས་རྣམས་སྤང་། །འདི་དང་བདུད་རྒྱལ་གཅིག་པ་དེ། །ཕར་རྒོལ་ལས་ལ་བཟང་ཞེས་བརྗོད། །ཉི་མ(༡)ཁྲུམས་སྨད(༢༥)ཟླ་བ(༢)སྐག(༨)། །མིག་དམར(༣)ས་རི(༡༤)ལྷག་པ(༤)སྣྲོན(༡༧)། །ཕུར(༥)དབོ(༡༡)པ་སངས(༦)སྨིན་དྲུག(༢)དང་། །སྤེན་པ(༠)སྣར་མ(༣)གཞན་ཡང་ནི། །མིག་དམར(༣)གྲེ(༡༠)དང་སྤེན(༠)ས་རི(༡༤)། །འདི་དག་བཀྲ་ཤིས་ཉི་མ་ཞེས། །སྐྱིད་ལ་དབང་བསྐུར་རབ་གནས་དང་། །ལྷ་གསོལ་དགེ་བའི་ལས་རྣམས་བཟང་། །ཉི་མ(༡)སྣར་མ(༣)ཟླ(༢)ས་རི(༡༤)། །མིག་དམར(༣)རྒྱལ(༧)དང་ལྷག(༤)ལྷ་མཚམས(༡༦)། །ཕུར་བུ(༥)ཁྲུམས་སྨད(༢༥)པ་སངས(༦)མགོ(༤)། །སྤེན་པ(༠)ཁྲུམས་སྟོད(༢༤)ཡང་ན་ནི། །ཟླ་བ(༢)ཆུ་སྟོད(༡༩)མིག་དམར(༣)ནབས(༦)། །སྤེན་པ(༠)མོན་དྲེ(༢༢)འཛོམ་པ་སྟེ། །འཕེལ་ཉི་བདུན་ཞེས་དགེ་ལས་དང་། །ཡིག་རྩིས་ཡོན་ཏན་སྤེལ་ལས་དང་། །ཆུ་འབེབས་ཁྲོན་འདྲུ་ཞིང་ལས་དང་། །ས་བོན་འདེབས་པ་ལ་སོགས་བཟང་། །གཞན་ཡང་ཕུར་བུ(༥)ཁྲུམས་སྟོད(༢༤)ལས། །ཟུང་སྦྱོར་དང་མཚུངས་ཆུབ་ཉི་ཞེས། །བཟང་ལས་ཕལ་ཆེར་ཐམས་ཅད་བཟང་། །ཉི་མ(༡)སྣྲོན(༡༧)དང་ཟླ(༢)ཁྲུམས་སྨད(༢༥)། །མིག་དམར(༣)ཁྲུམས་སྟོད(༢༤)ལྷག(༤)བྲ་ཉེ(༡)། །ཕུར(༥)དབྱུག(༠)པ་སངས(༦)མཆུ(༩)དང་འཛོམ། །སྤེན་པ(༠)ནམ་གྲུ(༢༦)མཐུན་ཉི་ཞེས། །བར་བསྡུམས་གཉེན་བཟོ་ཕན་བྱ་བཟང་། །ཉི་མ(༡)མགོ(༤)དང་ཟླ་བ(༢)སྣྲོན(༡༧)། །མིག་དམར(༣)སྨིན་དྲུག(༢)ལྷག(༤)ཆུ་སྟོད(༡༩)། །ཕུར་བུ(༥)སྒྲོག(༢༣)དང་པ་སངས(༦)དབྱུག(༠)། །སྤེན་པ(༠)མེ་བཞི(༡༢)སྦྱོར་ཉི་བདུན། །གཞན་ཡང་ལུགས་གཅིག་ལྷག(༤)

p. 336 (img. 344; Ser thang img. 342):

> བྲ་ཉེ(༡)། །ཕུར་བུ(༥)མོན་དྲེ(༢༢)སངས(༦)གྲོ་བཞིན(༢༡)། །སྤེན་པ(༠)སྣྲོན(༡༧)འཛོམ་སྦྱོར་ཉི་ཡིན། །ལྷ་གསོལ་གཉེན་སྦྱོར་ལྟད་སྟོན་དང་། །དགེ་ལས་འཕེལ་འདོད་རྒྱས་པའི་ལས། །བཟང་ལ་ཕུང་བྱེད་ངན་བྱེད་ངན།། བདུད་ཀྱི་ཉི་མ་སྤུན་བདུན་ནི། །ཉི་མ(༡)སྨིན་དྲུག(༢)གུད་དུ་འཛོམ། །ལྷ་གསོལ་བདུད་ཀྱི་ཉི་མ་ཡིན། །རྡོ་ལ་ཡི་གེ་ཧྲི་བྲིས་ལ། །མཆོད་རྟེན་འོག་ཏུ་མནན་པ་བྱ། །ཟླ་བ་ལྕགས་ཀྱི་བྱ་ཁྲ་ཞོན། །མོ་བཙུན་བདུད་ཀྱི་ཉི་མ་ཡིན། །མགར་བའི་འོག་ཇོ་ནག་པོ་ལ། །མཚལ་ཆེན་ལྕོག་ལའི་ཐིག་བཏབ་སྟེ། །ཡུགས་ས་མོ་ཡི་ཁྱིམ་དུ་བོར། །མིག་དམར་ལག་ན་སྟ་རེ་ཐོགས། །དམག་དཔོན་བདུད་ཀྱི་ཉི་མ་ཡིན། །ར་མགོ་དག་ལ་མནན་པ་བྱ། །ལྷག་པ་ལག་ན་བྲེ་སྟོང་ཐོགས། །ནས་འཇལ་བདུད་ཀྱི་ཉི་མ་ཡིན། །རྫ་ཆག་གཙོ་བོ་ཅིག་ལ་བྱིན། །ཕུར་བུ་མགོ་ཡི་གདན་ལ་བཞུགས། །བན་བོན་བདུད་ཀྱི་ཉི་མ་ཡིན། །མཉན་པའི་བུ་ལ་བྱང་བུ་གདགས། །པ་ཝ་སངས་དང་རྒྱལ་དུ་འཛོམ། །ཆེ་བཙན་བདུད་ཀྱི་ཉི་མ་ཡིན། །མས་སྐྱེད་རྟ་འཆག་འོག་ཏུ་[སྦད]། །སྤེན་པ་གསེར་གྱི་སྒྲོག་ཏུ་ཚུད། །རྟ་ཚོང་བདུད་ཀྱི་ཉི་མ་ཡིན། །རྟ་སྒྲོག་ཁྱི་ཡི་སྐེ་ལ་གདགས། །རང་རང་སོ་སོའི་བྱ་ལས་དང་། །དབང་པོའི་བྱ་བ་ཐམས་ཅད་དང་། །བག་མ་བླངས་ན་གྱོས་པོ་དང་། །ཁྱོ་འཆི་ཤིད་བྱས་རོ་བདག་དང་། །མནའ་མ་སྲིད་འཛིན་ལ་ངན་ཞིང་། །ལམ་དུ་ཞུགས་པར་མི་བྱ་འཛེམ། །མཐུ་གཏད་ངན་བྱེད་རིགས་རྣམས་བཟང་། །ཉི་མས་ལག་པའི་སོར་མོ་བསྲེགས། །ཟླ་བས་སྨིན་དྲུག་ཟིལ་གྱིས་མནན། །མིག་དམར་སྒྲོག་གི་སྨད་ཀྱིས་བཅིངས། །གཟའ་ཡི་ལག་པ་དབྱུག་པས་བཅག །ཕུར་བུ་མགོ་ཡི་སྟེང་དུ་བཏབ། །དཀར་པོས་བེའུའི་རྫི་བོ་བརྡུངས། །སྤེན་པའི་ཕུང་པོ་མེ་བཞིས་བསྲེགས། །འཆི་སྦྱོར་ཡིན་ཏེ་མཁར་ལས་དང་། །རབ་གནས་ཚེ་དབང་ཚེ་སྒྲུབ་དང་། །བག་མ་གཏོང་ལེན་ལམ་ཞུགས་མཁར། །ནད་པ་འཕོ་ལན་ལ་སོགས་པའི། །ལས་དང་བྱ་བ་ཕལ་ཆེར་འཛེམ།། ཉི་མ(༡)མོན་དྲེ(༢༢)ཟླ(༢)ས་ག(༡༥)། །མིག་དམར(༣)ལྷ་མཚམས(༡༦)ལྷག་པ(༤)སྣྲོན(༡༧)། །ཕུར་བུ(༥)གྲོ་བཞིན(༢༡)པ་སངས(༦)སྣར(༣)། །སྤེན་པ(༠)གྲེ(༡༠)རྣམས་ཡང་ན་ནི། །ཉི་མ(༡)གྲོ་བཞིན(༢༡)ཟླ(༢)ལྷ་མཚམས(༡༦)། །གཟའ་ལྷག(༤)དང་ནི་སྐར་མ་སྣྲུབས(༡༨)། །ཕུར་བུ(༥)མོན་གྲུ(༢༣)མི་འཕྲོད་ཉི། །འདི་ལ་དགྲ་འདུལ་དབྱེ་ལ་སོགས། །ངན་ལས་བཟང་ལ་བག་མ་བླངས། །སྨྱུག་མོ་ལ་ངན་ཤིད་བྱས་ན། །བག་མ་བུ་སྲིང་ལ་ངན་ནོ། །སྨན་དང་མཐུན་སྦྱོར་ཕན་ལས་སྤང་།། ཉི་མ(༡)སྣྲོན(༡༧)དང་ཟླ(༢)ཁྲུམས་སྨད(༢༥)། །མིག་དམར(༣)ཁྲུམས་སྟོད(༢༤)ལྷག(༤)བྲ་ཉེ(༡)། །ཕུར་བུ(༥)ལྷ་མཚམས(༡༦)པ་སངས(༦)མཆུ(༩)། །སྤེན་པ(༠)ནམ་གྲུ(༢༦)ཡང་ན་ནི། །ཉི་མ(༡)མོན་གྲུ(༢༣)མིག་དམར(༣)དབྱུག(༠)། །ཕུར་བུ(༥)ས་གའམ(༡༥)ལྷ་མཚམས(༡༦)ཕྲད། །དྲང་སྲོང་སྤེན་པ(༠)ནག་པ(༡༣)འཛོམ། །མི་མཐུན་ཉི་མ་ཞེས་བྱ་སྟེ། །དབྱེ་ལས་ཁང་སེལ་ངན་བྱེད་བཟང་། །བག་བྱས་རང་དང་ཕ་མིང་དང་། །སྨྱུག་མོར་ངན་ཞིང་ཤིད་བྱས་ན། །སྐྱེས་པ་གཞོན་ལ་ངན་པས་སྤང་། །གཞན་ཡང་བཟང་ལས་ཕལ་ཆེར་ངན། །ཉི་མ(༡)ས་ག(༡༥)ཟླ་བ(༢)སྣྲོན(༡༧)། །མིག་དམར(༣)ནམ་གྲུ(༢༦)ལྷག(༤)མེ་བཞི(༡༢)། །ཕུར(༥)སྣར(༣)པ་སངས(༦)ནབས་སོ(༦)དང་། །སྤེན་པ(༠)བྲ་ཉེ(༡)ཡང་ན་ནི། །ཉི་མ(༡)ཁྲུམས་སྟོད(༢༤)ཟླ་བ(༢)སྣར(༣)། །མིག་དམར(༣)ཁྲུམས་སྟོད(༢༤)ལྷག་པ(༤)ནག(༡༣)། །པ་བ་སངས(༦)དང་གྲེ(༡༠)རུ

p. 337 (img. 345; Ser thang img. 343), to l. 25:

> འཛོམ། །སྤེན(༠)སྐག(༨)གཞན་ཡང་མིག་དམར(༣)སྒྲོག(༢༣)། །དེ་དག་འཇིག་པའི་ཉི་མ་ཞེས། །བག་མ་སྙེ་བོ་རྟ་ལ་ངན། །ཤིད་བྱས་སྲིད་དང་ཚ་བོར་ངན། །ལམ་དུ་ཞུགས་པར་མི་བྱའོ། །སེལ་སོགས་ངན་བྱེད་ལས་ལ་ཤིས། །ཉི་མ(༡)རྟ་ཆེན(༩)ཟླ(༢)ས་ག(༡༥)། །མིག་དམར(༣)ལག(༥)དང་ལྷག(༤)སོག་པ(༡༨)། །ཕུར་བུ(༥)མོན་དྲེ(༢༢)པ་སངས(༦)སྣར(༣)། །སྤེན་པ(༠)ཆུ་སྟོད(༡༩)འཛོམ་པའི་ཚེ། །གཏན་སྤང་ཞེས་གྲགས་གལ་ཆེན་གྱི། །བྱ་བ་ཐམས་ཅད་སྤང་ཞེས་ཏེ། །འདི་ལ་བསྲེག་སྦྱོར་ཞེས་ཀྱང་བྱ། །གཏར་སྲེག་སྤང་བར་བྱ་བ་ཡིན། །ཕུར་བུ་མ་གཏོགས་ཟུང་སྦྱོར་དང་། །ཆ་མཐུན་འདྲ་བ་སྤང་བླང་གཅེས། །གཞན་ཡང་རྡོ་རྗེ་གཙུག་ལག་ནས། །བདུད་ཉི་རང་གཤེད་ལས་ལ་བརྩི། །མི་འཕྲོད་ཉི་མ་ལྟས་ངན་ནོ། །གྲུབ་པའི་ཉི་མར་བསྙེན་སྒྲུབ་བོ། །ཞེས་པ་གྲུབ་པའི་ཉི་མ་ནི། །ཉི་མ(༡)སྐར་མ་ཁྲུམས་སྟོད(༢༤)སྨད(༢༥)། །ནམ་གྲུ(༢༦)ཆུ་སྨད(༢༠)འཛོམ་པ་དང་། །ཟླ་བ(༢)གྲོ་ཞུན(༢༡)སྣར་མ(༣)དང་། །དམག་དཔོན(༣)ཁྲུམས་སྨད(༢༥)ནམ་གྲུ(༢༦)སྨིན(༢)། །ལྷག་པ(༤)སྨིན་དྲུག(༢)མོན་གྲུ(༢༣)འཛོམ། །ཕུར་བུ(༥)ནབས་སོ(༦)རྒྱལ(༧)དང་འཛོམ། །པ་སངས(༦)གྲོ་བཞིན(༢༡)ཐ་སྐར(༠)འཛོམ། །སྤེན་པ(༠)གྲོ་བཞིན(༢༡)འཛོམ་པའོ། །བདུད་ཀྱི་ཉི་མ(༡)ལྷ་མཚམས(༡༦)དང་། །བྲ་ཉེ(༡)ཟླ་བ(༢)རྒྱལ(༧)ཆུ་སྟོད(༡༩)། །ཆུ་སྨད(༢༠)ལག(༥)དང་མིག་དམར(༣)ལ། །ཆུ་སྨད(༢༠)མོན་དྲེ(༢༢)ས་གའོ(༡༥)། །ལྷག་པ(༤)ཐ་སྐར(༠)མགོ(༤)འཛོམ་པ། །ཕུར་བུ(༥)མགོ(༤)དང་ས་ག(༡༥)འཛོམ། །པ་སངས(༦)སྐག(༨)དང་ནམ་གྲུ(༢༦)འཛོམ། །སྤེན་པ(༠)དབོ(༡༡)དང་མེ་བཞིའོ(༡༢)། །མི་མཐུན་ཉི་མ(༡)མཆུ(༩)སྣྲོན(༡༧)སྣྲུབས(༡༨)།། མོན་གྲུ(༢༣)ཟླ་བ(༢)ས་ག(༡༥)དང་། །བྱི་བཞིན(༢༡)ཁྲུམས་སྨད(༢༥)མིག་དམར(༣)ལ། །བྲ་ཉེ(༡)ཐ་སྐར(༠)ཁྲུམས་སྟོད(༢༤)ལག(༥)། །ལྷག་པ(༤)བྲ་ཉེ(༡)ཕུར་བུ(༥)ལ། །ས་ག(༡༥)ལག(༥)དང་ལྷ་མཚམས(༡༦)འཛོམས། །པ་སངས(༦)མཆུ(༩)དང་སྤེན་པ(༠)ལ། །ནག་པ(༡༣)ནམ་གྲུ(༢༦)འཛོམ་པའོ། །འཇིག་ཉི་ཉི་མ(༡)ས་ག(༡༥)བྲེ(༡༩)། །ཁྲུམས་སྟོད(༢༤)ཟླ(༢)སྣར(༣)ཆུ་སྨད(༢༠)སྒྲོག(༢༣)། །མིག་དམར(༣)མོན་གྲུ(༢༣)ཁྲུམས་སྟོད(༢༤)དང་། །ལྷག་པ(༤)སྣར(༣)ནག(༡༣)སྣྲུབས(༡༨)ནམ་གྲུ(༢༦)། །ཕུར་བུ་སྣར་མ(༣)སྣྲུབས(༡༨)པ་སངས(༦)། །སྣར་མ(༣)ནབས་སོ(༦)གྲེ(༡༠)སྤེན་པར(༠)། །སྐག་དང་ཆུ་སྟོད(༡༩)ཆུ་སྨད(༢༠)དོ། །མི་འཕྲོད་ཉི་མ་ཉི་མ(༡)མཆུ(༩)། །གྲོ་བཞིན(༢༡)མོན་དྲེ(༢༢)ཟླ་བ(༢)ལ། །ལག(༥)དང་ས་ག(༡༥)ལྷ་མཚམས(༡༦)དང་། །དམག་དཔོན(༣)ལག(༥)དང་ལྷ་མཚམས(༡༦)སྣྲུབས(༡༨)། །ལྷག་པར(༧)སྣྲུབས(༡༨)དང་ཕུར་བུ(༥)ལ། །གྲོ་བཞིན(༢༡)མོན་གྲུ(༢༣)ནམ་གྲུའོ(༢༦)། །པ་སངས(༦)སྣར་མ(༣)སྤེན་པ(༠)ལ། །གྲེ(༡༠)དང་ཆུ་སྟོད(༡༩)ཆུ་སྨད(༢༠)དོ། །དེ་དག་གཟའ་སྐར་འཕྲོད་སྦྱོར་ལ། །འབྲས་བུ་བཟང་ངན་མི་འདྲ་བར། །མིང་གི་དབྱེ་བ་མང་གསུངས་པ། །མཁས་རྣམས་བཞེད་པའི་ལུགས་སོ་སོ། །མ་ཉམས་བཞིན་དུ་བཀོད་པ་དང་། །དེ་ནང་གཉན་པའི་སྤང་བླང་རྣམས། །ལེ་ཐོ་སྦྱོར་འགྲོལ་དུས་བཤད་པ། །རྣམས་ཀྱི་འབྲས་བུ་ཅུང་ཟད་གཅེས། །དེ་ཡང་གཟའ་སྐར་སོ་སོ་ཡི། །སྒེར་གྱི་འབྲས་བུ་གཙོ་བས་ཞིབ། །སྦྱོར་བ་དེ་དག་ཤར་དུས་དང་། །དྲག་ལས་ལ་སོགས་བསྟུན་དགོས་རྣམས། །རང་རང་ལུགས་ནས་བཤད་བཞིན་གཅེས།

## The *Rdo rje gtsug lag*'s special days

WB p. 337 (img. 345), the verse; p. 342 (img. 350), its table, under
three headings: རྡོ་རྗེ་གཙུག་ལག་གི་གྲུབ་ཉི་མི་འདྲ་བཞི, བདུད་ཉི་རྡོ་རྗེ་གཙུག་ལག་ལུགས,
མི་མཐུན་ཉི་མ་བཞི, and below them འཇིག་ཉི་བཞི and མི་འཕྲོད་ཉི་མ་གསུམ. Read
2026-10-04: the verse by Yigdzin-1, MITRA and BDRC's etext, checked on the
scan; the table on the scan at 3–6×, digit by digit (in this hand ༢ has
one hook and ༣ two, as in KP). The introduction:

> གཞན་ཡང་རྡོ་རྗེ་གཙུག་ལག་ནས། །བདུད་ཉི་རང་གཤེད་ལས་ལ་བརྩི། །མི་འཕྲོད་ཉི་མ་ལྟས་ངན་ནོ། །གྲུབ་པའི་ཉི་མར་བསྙེན་སྒྲུབ་བོ། །ཞེས་པ་གྲུབ་པའི་ཉི་མ་ནི། །

"Further, from the *Rdo rje gtsug lag*: the demon day is reckoned against
the work it is the bane of (*rang gshed*); the day of incompatibility is an
ill omen; on the day of accomplishment, approach and accomplishment (*bsnyen
sgrub*, a deity's retreat practice)." Yigdzin-1 reads ལྷས for ལྟས; MITRA
and the etext have ལྟས, and so does the scan. The verse then names, weekday
by weekday, the mansions of each day, with the editors' numbers; the table
gives the same mansions, one to a cell, filling a weekday's spare cells
with repeats. By WB's numbers (Sunday … Saturday):

| Day | Sun | Mon | Tue | Wed | Thu | Fri | Sat |
| --- | --- | --- | --- | --- | --- | --- | --- |
| *grub pa'i nyi ma* | 24, 25, 26, 20 | 21, 3 | 25, 26, 2 | 2, 23 | 6, 7 | 21, 0 | 21 |
| *bdud kyi nyi ma* | 16, 1 | 7, 19, 20, 5 | 20, 22, 15 | 0, 4 | 4, 15 | 8, 26 | 11, 12 |
| *mi mthun nyi ma* | 9, 17, 18, 23 | 15, 21 (byi bzhin), 25 | 1, 0, 24, 5 | 1 | 15, 5, 16 | 9 | 13, 26 |
| *'jig nyi* | 15, 19, 24 | 3, 20, 23 | 23, 24 | 3, 13, 18, 26 | 3, 18 | 3, 6, 10 | 8, 19, 20 |
| *mi 'phrod nyi ma* | 9, 21, 22 | 5, 15, 16 | 5, 16, 18 | 18 | 21, 23, 26 | 3 | 10, 19, 20 |

Every cell agrees with the verse's numbers and names. Where the OCR's
numbers differ, the scan has the table's: *mi mthun*'s Saturday is ནག་པ(༡༣),
Citrā, and *mi 'phrod*'s Sunday མོན་དྲེ(༢༢) and Thursday མོན་གྲུ(༢༣). Three
names are worth a note:

- **བྱི་བཞིན(༢༡)**, *mi mthun*'s Monday: Abhijit, which the editors number
  21 like gro bzhin; never the day's mansion, so it drops out. Gro bzhin is
  spelt out wherever the verse means it (གྲོ་བཞིན, once གྲོ་ཞུན).
- **བྲེ(༡༩)**, *'jig nyi*'s Sunday: chu stod, Pūrvāṣāḍhā, as in WB's
  verse of the personal mansions (p. 330, [personal-mansions.md](personal-mansions.md)).
- **སྒྲོག(༢༣)**, *'jig nyi*'s Monday: mon gru, Śatabhiṣaj, as in the first
  set's *'jig pa'i nyi ma* (Tuesday, སྒྲོག(༢༣)) and the *'chi sbyor* verse,
  where Mars is bound by *sgrog*.

**In the app** (SPEC §5.12): a day this table makes and WB's own table does
not is shown as that kind: WB's reading of the kind first, whose lists
and rows it keeps, then its own line where it has one, then that it is
the *Rdo rje gtsug lag*'s reckoning (ROADMAP X5; the day of
accomplishment adds dharma practice for *bsnyen sgrub*);
it counts among the special days. In 2026 it adds a special day to 92 days,
on some of them the only one.

## Open

- Open questions 146–147 ([open-questions.md](open-questions.md)).
