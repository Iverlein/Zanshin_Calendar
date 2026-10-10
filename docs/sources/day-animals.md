# The twelve day animals in the White Beryl

Sigla and the rules for quoting are in [README.md](README.md). The *nyi ma*
is the lunar date's animal (open question 9, [lunar-day-signs.md](lunar-day-signs.md)).

WB vol. 2, pp. 356–359 (scans img. 364–367), after the twelve links' days
and before the hours of the Chinese reckoning ([weighing.md](weighing.md),
*The hour of rule 2*): «ཉི་མ་བཅུ་གཉིས་སོ་སོ་ཡི། །བཟང་ངན་བྱེ་བྲག་བརྗོད་པར་བྱ།», "the good and bad
of each of the twelve day animals is told", one verse for each animal
from the tiger, beside its woodcut; then six opposed pairs and what each
avoids. Every verse runs in the same order: what is to be avoided
(closed by སྤང, འཛེམ or ངན), what is good (closed by བཟང or དགེ), often with
the direction for lawsuits (*don gnyer*) between them; then the earth
lord's seat in the house on that day and what it harms («X ལ་གནས … ངན»),
and the remedy (closed by ཐུབ or ཞི).

**How it was read** (2026-10-10, ROADMAP T2.24). Yigdzin-1 and MITRA
(`tools/sources/hf_read.py`) on the 1996 scans, BDRC's etext
(IE0OPI51524892, page = image) as third witness; every place where they
differ and every word of the lists read on the scan at 2–5×. Yigdzin-1
dropped one line beside the dragon's woodcut, MITRA the whole monkey
verse. The Tibetan below is Yigdzin-1's with those places settled; the
earth lord's seats and remedies were not read again on the scan.

**The seats are chapter 31's.** The twelve seats (the beer jar, the
granary, the door, the door bolt, the mill, the rope, the saddle, the
trough, knife and axe, the plates, the threshold, the hoe) are those of
pp. 223–226 ([earth-lords.md](earth-lords.md)), there for the
reckoning of the dead; here with shorter remedies. The app shows them as
built from ch. 31 and does not build them again.

**What the app takes** (SPEC §5.10): each verse's lists, and the pairs'
avoidances, as wording keys (`core/.../texts/DayAnimalVerses.kt`),
joined first to the day animal's lists, before the *kun phan me long*'s
boxes and Henning's list (`Texts.ELECTIONAL_ANIMAL`). WB counts above
them: an entry of theirs that names any of its works the other way from
the verse is left out (a hare day's marriage, a pig day's suppressing
the *sri*; also the dragon's and dog's "hunting and theft", where WB
avoids raids, and with it the hunting). Left out of the lists, as with
the mansion verses: words the reading leaves in doubt (below), what a
verse calls middling (*'bring*) or only acceptable (*rung tsam*). A work
qualified by a direction (lawsuits in the east and west) is shown and
weighs for neither side. Where the verse names a work both ways under
two words (the hare's works for the dead good, its funerals bad; the
dragon's seizing lands good, the pair's war and raids bad), the day
animal says nothing on it, as for any reading.

**Measured on 2000–2049** (18,263 days): the days' tones do not change
(the day animal has none); the days' works grow from 1,650,091 to
1,681,946 (31,983 added; 128 gone, where KP's entry the verse
contradicts carried another work, or the verse names a work both ways),
518 change side, and the day animal decides 49,784 works instead of
17,797; days against their tone 2,067 to 2,018. `DaySummaryTest` keeps
a day each list decides.

## Tiger

p. 356 (img. 364):

> སྟག་ལ་ལྷ་གསོལ་ཇག་ཆོམ་སྐྱས། །གཉེན་བྱེད་སྨན་སྦྱོར་དབང་བསྐུར་ཁྲུས། །གླུད་དང་བོན་ཆོག་མདོས་གཏོར་སྤང་། །གཡང་ལོན་མཁར་ལས་བ་གམ་གདགས། །དམག་དྲང་བསད་ལས་དྲག་ཤུལ་ལས། །དྲག་པོའི་མཚོན་བཟོ་མནན་པ་དང་། །དོན་གཉེར་ཤར་དང་ནུབ་གཉིས་དགེ །བྱང་འབྲིང་རྒྱལ་ས་རྟེན་བཞེངས་པ། །ཧོམ་སྤོར་སྦྱིན་སྲེག་རབ་གནས་བཟང་། །ལྷག་མོ་སྒོ་གནས་ཐོན་བུར་ངན། །སྒོ་བཏོན་ཡུལ་ལྷ་གནས་པའི་སར། །མདའ་ལ་ལྷམ་ཀྲད་བཏགས་ལ་འཛུགས།།

- **Avoid:** offerings to deities, robbery, gifts (*skyas*), marriage alliances, preparing medicine, empowerment, bathing (*khrus*), ransom rites (*glud*), Bon rites, thread-crosses and tormas (*mdos gtor*).
- **Good:** prosperity rites (*g.yang lon*), building forts, setting up battlements (*ba gam*), leading an army, killing, fierce and harsh work, making fierce weapons, suppressing (*mnan pa*); lawsuits (*don gnyer*) in the east and west, in the north middling; enthronement (*rgyal sa*), raising images (*rten bzhengs*), burnt offerings (*hom*), fire offerings, consecration.
- Seat: the door («སྒོ་གནས»), bad for a son going out; its first syllable, Yigdzin-1 ལྷག, MITRA ལྟག, is not clear on the scan.

## Hare

p. 356 (img. 364):

> ཡོས་ལ་དམག་མཚོན་མཁར་ལས་གཉེན། །ས་བརྐོ་རྡོ་རློག་རྫིང་ཡུར་འཆོས། །སྟོན་མོ་གཡང་སློན་རིམ་གྲོ་ཤིད། །ས་ཁ་འབྱེད་དང་གླང་འདུལ་བ། །འགྲམ་འདིང་ས་བོན་ཆུ་མིག་འདྲུ། །རྨོས་བྱ་རྟ་འདུལ་དྲག་ཤུལ་ལྟད། །ཆུ་རགས་སྐྱས་དང་ཡོ་ལང་སྤང་། །དོན་གཉེར་ལྷོར་ངན་གཞན་རུང་ཙམ། །གཤིན་ལས་སྤྱི་དང་གླང་ལུག་ལས། །ཞུ་གསོལ་རྒྱལ་ས་བཟུང་བ་བཟང་། །མནན་པ་བྱེད་པ་ངན་པ་དང་། །གཡག་ཤིང་ལ་གནས་བུ་མོར་ངན། །གཅོད་རུ་ཅིག་ལ་སྤྲང་པོའི་ཞྭ། །བཏགས་ལ་སྦྱིན་བདག་ན་བཟའ་ཡང་། །བོང་བུ་ལ་བཀལ་ཐུབ་པའོ། །

- **Avoid:** war and weapons, building forts, marriage alliances, digging, turning over stones (*rdo rlog*), repairing ponds and canals, feasts, calling prosperity (*g.yang slon*), ritual services (*rim gro*), funerals (*shid*), opening the ground, breaking in oxen, sowing, digging springs, ploughing, breaking in horses, dikes (*chu rags*), gifts; lawsuits in the south are bad, elsewhere only acceptable; suppressing is bad.
- **Good:** works for the dead in general, work with oxen and sheep, petitions (*zhu gsol*), taking the throne (*rgyal sa bzung*).
- Left out as doubtful: «འགྲམ་འདིང» (laying banks?), «དྲག་ཤུལ་ལྟད» (the last syllable ལྟད or ལྷད on the scan), «ཡོ་ལང». Seat: the door bolt (*g.yag shing*), bad for daughters.

## Dragon

p. 357 (img. 365):

> འབྲུག་ལ་གྲུ་གཟིངས་ཟམ་འཕྲང་འཆོས། །གཏར་ག་མེ་བཙའ་རང་འཐག་འཛུགས། །བརྐོ་རློག་བྱས་ན་ཀླུ་གཉན་གནོད། །ཆར་དབབ་རོ་འདོན་མྱ་ངན་སྐད། །ཕྱོགས་ནི་གང་དུ་ངན་པའོ། །རྒྱལ་ས་དགེ་ལས་ཐོག་སེར་འབེབས། །རྟེན་བཞེངས་དྲག་རིམ་སྲི་མནན་པ། །ལྷ་གསོལ་དྲག་ལས་དགྲ་འདྲེ་འདུལ། །རབ་བྱུང་སྒྲུབ་མཆོད་རབ་གནས་དབང་། །ཡུལ་འཕྲོག་རྔ་ལྤགས་འབུབས་པ་བཟང་། །རང་འཐག་ལ་གནས་ཞང་ཉེར་ངན། །ལྷོ་ཕྱེ་གནམ་ལ་གཏོར་བས་ཐུབ། །བག་ཟན་མི་ལུས་འབྲུག་མགོ་བཅོས། །ནས་བྲེ་གང་དང་ཟོར་བ་དང་། །མདའ་དར་སེར་པོ་དང་བཅས་པ། །དེ་དག་འབྲུག་གི་ཐོག་ཏུ་འཛུགས།།

- **Avoid:** repairing boats, bridges and narrow paths, bloodletting (*gtar ga*), moxibustion (*me btsa'*), setting up a water mill (*rang 'thag*), digging and turning over stones, which harms the nāgas and the *gnyan*, bringing rain, carrying out a corpse, mourning cries: bad in whatever direction.
- **Good:** enthronement, virtuous work, sending down lightning and hail (*thog ser 'bebs*), raising images, fierce ritual services, suppressing the *sri*, offerings to deities, fierce work, subduing enemies and demons, ordination, practice and offering, consecration, empowerment, seizing lands (*yul 'phrog*), stretching drum skins.
- Yigdzin-1 drops the line «གང་དུ་ངན་པའོ། །རྒྱལ་ས་དགེ་ལས་ཐོག་སེར» beside the woodcut; MITRA and the scan have it. Seat: the water mill, bad for the maternal uncle.

## Snake

p. 357 (img. 365):

> སྦྲུལ་ལ་ཤིང་ནགས་གཅོད་འདྲེན་གཤིན། །སྨན་དཔྱད་ཧོམ་སྤར་དྲག་ལས་སྐྱས། །ནོར་འཇལ་རྒྱལ་པོ་བནྡེ་བོན། །འཁོན་བྱེད་གཏར་སྲེག་ཚེའུ་ལུམ། །ཆུ་བསུབ་ཐབ་བཅའ་ས་ཁ་འབྱེད། །རྒྱལ་ས་བག་མ་གཏོང་ལེན་དང་། །མནན་པ་ཉོ་ཚོང་རིམ་གྲོ་འཛེམ། །མྱ་ངན་བྱས་ན་རོ་ཟླ་འོང་། །ནད་པ་ཕྱོགས་འདོན་སྐྱེལ་བསུ་བྱེད། །བུ་ལོན་ཕྱི་ལ་གཏོང་བ་དང་། །ཕྱུགས་གཏོང་ཀླུ་ཆོག་བྱ་བ་བཟང་། །དོན་གཉེར་ལྷོར་བཟང་གཞན་རྣམས་ངན། །ཐག་པ་ལ་གནས་རོ་ཁུར་ངན། །གྲིས་གཏུབས་བག་མ་བྱ་དགོས་ན། །བག་མའི་ཞྭ་ནང་ཁྱི་ལུད་བཅུག །རྩིས་མཁན་གདན་འོག་མནན་པས་ཐུབ།།

- **Avoid:** cutting and hauling forests, works for the dead, examining medicine (*sman dpyad*), burnt offerings, fierce work, gifts, paying out wealth (*nor 'jal*), making feuds, bloodletting and moxibustion, setting up a hearth, opening the ground, enthronement, giving and taking a bride, suppressing, buying and selling, ritual services; mourning brings a second corpse.
- **Good:** moving the sick to another place, seeing people off and welcoming them, lending, sending livestock out, nāga rites (*klu chog*); lawsuits good in the south, bad elsewhere.
- Left out as doubtful: «རྒྱལ་པོ་བནྡེ་བོན» after *nor 'jal* (paid to kings, monks and Bonpos?; the scan has བནྡེ, Yigdzin-1 བརྗེ), «ཚེའུ་ལུམ», «ཆུ་བསུབ». Seat: the rope, bad for the corpse-bearer.

## Horse

p. 357 (img. 365):

> རྟ་ལ་མཁར་ཁྱིམ་འབུབས་པ་དང་། །དྲག་པོའི་མཚོན་བཟོ་གཤིན་གྱི་ལས། །འཐབ་རྩོད་ཞལ་ཆེ་དོན་གཉེར་སྒྲེང་། །ཟེ་བ་འདྲེག་དང་རྟ་འདུལ་རྒྱུག །ཆར་བ་དབབ་དང་རྟའི་ཉོ་ཚོང་། །བག་མ་གཏོང་ལེན་ངན་པ་ཡིན། །རྩེ་ལྷ་གསོལ་དང་གསང་གྲོས་བྱེད། །སྟོབས་འགྲན་གྲོས་བཞི་ལྟད་མོ་རྒྱན། །གྲོགས་བཙལ་རྒྱལ་ས་མནའ་སྐར་བཟང་། །སྒར་གནས་བང་ཆེན་ལ་ངན་པས། །སྒ་མེད་ཞོན་ཅིང་ཡུལ་དཔོན་གྱི། །རྐང་རྗེས་ས་བླང་མལ་འོག་མནན།།

- **Avoid:** building forts and houses, making fierce weapons, works for the dead, fights and disputes, judging disputes (*zhal che*), lawsuits, cutting manes (*ze ba*), breaking in and racing horses, bringing rain, buying and selling horses, giving and taking a bride.
- **Good:** offerings to deities, secret counsel, trials of strength, spectacles, seeking friends, enthronement.
- Left out as doubtful: the syllable before *lha gsol* (Yigdzin-1 རྩེ, MITRA ཚེ), «གྲོས་བཞི», «རྒྱན», «མནའ་སྐར», and the last syllable of «དོན་གཉེར་སྒྲེང» (the etext གླེང), which does not change the lawsuits. Seat: the saddle (*sga*).

## Sheep

p. 357 (img. 365):

> ལུག་ལ་སྡོམ་ལེན་གཏར་སྲེག་དྲག །འདུལ་ཁྲིམས་བསྣོན་པ་དམག་འདྲེན་སྐྱས། །སྨན་སྦྱོར་དབྱུག་དཔྱད་དོན་གཉེར་བ། །ཆར་དབབ་ལ་སོགས་སྤང་བ་ཡིན། །ལྷ་གསོལ་མཁར་ལས་ལུག་རྫི་འཛུགས། །ཟས་སྦྱོར་ལུག་རྫི་བཙལ་བག་མ། །ཕྱུགས་གཏོང་འཕྲུ་རློག་ལས་སྦྱོར་བརྟན། །བང་མཛོད་བཅའ་བ་བཟང་བ་ཡིན། །གཞོང་པ་ལ་གནས་ཟན་བརྫིར་ངན། །སླ་ངར་བརྫི་ཞིང་སྨན་གྱི་ཕུད། །ལྷ་རྗེ་ལ་ངན་སྨན་ནད་པའི། །འཕོངས་འོག་བཏོན་པས་ཉེས་པ་ཞི། །

- **Avoid:** taking vows, bloodletting and moxibustion, fierce work, leading an army, gifts, preparing medicine, lawsuits, bringing rain and the like.
- **Good:** offerings to deities, building forts, seeking and appointing a shepherd, preparing food, a bride, sending livestock out, lasting work, building storehouses.
- Left out as doubtful: «འདུལ་ཁྲིམས་བསྣོན་པ» (MITRA བརྩོན), «དབྱུག་དཔྱད», «འཕྲུ་རློག». Seat: the trough.

## Monkey

p. 358 (img. 366):

> སྤྲེལ་ལ་གོས་གསར་དྲ་གྱོན་མཚོན། །སྦྲ་བཙེམ་སྐྱེས་པ་ཕོ་ཆས་གཞུག །བཟོ་རིགས་འདུན་གྲོས་ཇག་སོགས་སྤང་། །རྒྱན་སྤྲོད་བག་མ་སྐྱེ་ཤིང་འཛུགས། །ཆོ་ལོ་མིག་མངས་རྒྱན་ཚོམས་རྒྱག །ལྟད་མོ་དོན་གཉེར་ཤར་བྱང་བགྲོད། །ཆར་དབབ་མནན་པ་གཤིན་གྱི་ལས། །ལྡུམ་ར་སྐྱེད་དང་རོལ་རྩེད་བཟང་། །རྒྱལ་ས་ཆེ་འདོན་བྱ་བ་འཛེམ། །གྲིབ་གནས་མཁར་དང་ཤ་པར་ངན། །མེ་ཡིས་བསྲེག་ཚུལ་ལན་གསུམ་བྱ། །སྣམ་སྣར་ཁྱི་ལུད་སྐམ་པོ་བཏགས། །ནུབ་སྨད་ཕྱོགས་སུ་བསྒྱུར་བས་ཐུབ།།

- **Avoid:** new clothes, wearing them, weapons, sewing tents, crafts, councils (*'dun gros*), robbery and the like; enthronement and ceremonies of honour (*che 'don*).
- **Good:** giving ornaments, a bride, planting trees, dice (*cho lo*), board games (*mig mangs*), spectacles, lawsuits, going east and north, bringing rain, suppressing, works for the dead, making gardens, music and play.
- MITRA drops the whole verse. Left out as doubtful: «སྐྱེས་པ་ཕོ་ཆས་གཞུག» (dressing a man in male gear?), «རྒྱན་ཚོམས་རྒྱག». Seat: the knife and axe («གྲིབ་གནས»?).

## Bird

p. 358 (img. 366):

> བྱ་ལ་གསང་བགྲོས་སྟོན་མོ་དང་། །དྲག་པོའི་མཚོན་བཟོ་མནན་པ་རྒྱག །དགེ་རྩ་སྦྱིན་གཏོང་ལྟད་མོ་ངན། །ཚོང་བྱེད་ནད་པ་སྲིའུ་ཡི། །སྨན་དཔྱད་བྱས་ན་བདུད་རྩིར་འགྱུར། །ཧོམ་དང་སྦྱིན་སྲེག་བྱ་བ་དང་། །ཤིང་འཛུགས་ཆར་དབབ་དོན་གཉེར་བཟང་། །སྡེར་མ་ལ་གནས་གློ་བུར་འོང་། །ངད་སྡེར་བྱ་ཞིང་སྦར་ཟན་གཏང་། །སྒོམ་ཆེན་ཞྭ་ནང་དེ་ཕོ་བཅུག །བཅུ་གཉིས་ལོ་ཡི་འཁོར་ལོ་གདགས། །གྲལ་དཔོན་གཡས་ཟུར་བཞག་པས་ཐུབ།།

- **Avoid:** secret counsel, feasts, making fierce weapons, suppressing, roots of virtue (*dge rtsa*), giving, spectacles.
- **Good:** trade; medicine given to the sick and to small children (*sri'u*) turns to nectar; burnt offerings (*hom*, so on the scan, the etext ཆོས) and fire offerings, planting trees, bringing rain, lawsuits.
- Seat: the plates (*sder ma*): sudden misfortune.

## Dog

p. 358 (img. 366):

> ཁྱི་ལ་བག་མ་དོན་གཉེར་སྐྱས། །གསང་གྲོས་བྱི་བཤོར་སྐྱེད་པུ་འཛུགས། །སྐྲ་འཁྲུ་རྐུ་འཕྲོག་འཐབ་མཁར་ལས། །ཆར་དབབ་དྲག་པོའི་ལས་རྣམས་ངན། །མགོ་བཀྲུས་བྱས་ན་ཁ་སྨྲས་དང་། །སེམས་ལ་སྡུག་བསྔལ་འབྱུང་བས་སྤང་། །བཟོ་རིགས་བུ་འབོགས་ཡོ་ལང་རྟེན། །བུ་མོ་བརྫངས་དང་རི་དྭགས་རྔོན། །རི་ལྟད་བསེ་རགས་ཁ་བཅིངས་སྨན། །ཞལ་ཆེ་ཞུ་བ་གསོལ་གདབ་པ། །བང་མཛོད་བཅའ་བ་བཟང་བ་ཡིན། །མ་ཐེམ་ལ་གནས་མ་རེག་འགྲོ། །ཁྱི་གཅིག་ལ་ནི་སྤང་ཟན་བྱིན། །གཅིག་ལ་མི་ཁའི་རྫས་བཀལ་ལ། །གར་འགྲོའི་ཕྱོགས་སུ་ཁ་བསྟན་ཐུབ།།

- **Avoid:** a bride, lawsuits, gifts, secret counsel, elopement (*byi bshor*), washing the hair, theft and robbery, fights, building forts, bringing rain, fierce work; washing the head brings quarrels and sorrow.
- **Good:** crafts, sending a daughter away in marriage, hunting, binding the mouths of the *bse rag*, medicine, judging disputes, petitions, supplication (*gsol gdab*), building storehouses.
- Left out as doubtful: «སྐྱེད་པུ་འཛུགས», «བུ་འབོགས», «ཡོ་ལང་རྟེན», «རི་ལྟད». Seat: the threshold.

## Pig

p. 358 (img. 366):

> ཕག་ལ་དུར་གདབ་རྡོ་རློག་དང་། །བོན་ཆོག་མཁར་ལས་ས་ཁ་དབྱེ། །མནན་པ་རིག་ཆོས་བསླབ་བཀླག་པ། །རི་མོ་ཤིད་དང་ཁྲོན་འདྲུ་འཛེམ། །དགའ་སྟོན་བྱེད་དང་ཆོས་སྟོན་འདྲེན། །དབང་ལས་དབང་བསྐུར་བྱ་བ་དང་། །ཁྲིམས་ནོད་བཙུན་པ་སྲི་མནན་པ། །ཆར་པ་དབབ་པ་བཟང་པའོ། །འཇོར་ལ་གནས་པས་ས་མི་བརྐོ། །སྔ་མ་ཤ་རུས་བརྐོས་ན་ཐུབ། །སྤེན་མ་ཉག་མ་གཅིག་དང་ནི། །འཇག་མ་གསུམ་པོ་བུད་མེད་ཀྱི། །དོར་རྟ་ཚལ་པས་སྒྲིལ་བྱས་པ། །རྒྱ་གྲམ་ལམ་དུ་མནན་བྱས་ལ། །དེ་ཁར་ཐོ་བརྩིགས་ཕ་ཚན་གྱི། །ཞྭ་མོ་གཡོགས་པས་ཐུབ་པའོ། །

- **Avoid:** making a grave (*dur gdab*), turning over stones, Bon rites, building forts, opening the ground, suppressing, studying and reading the sciences, painting, funerals, digging wells.
- **Good:** celebrations (*dga' ston*, so on the scan at 5×), inviting a teacher of the dharma, power rites and empowerment, monks taking the precepts (*khrims nod btsun pa*), suppressing the *sri*, bringing rain.
- Seat: the hoe; do not dig.

## Mouse

pp. 358–359 (img. 366–367):

> བྱི་ཉིར་མོ་འདེབས་རྟ་རྒྱུག་པ། །དྲག་པོའི་མཚོན་བཟོ་མནན་པ་རྒྱག །མེ་བཙའ་རྐེད་མན་ཐག་རིངས་དོན། །དམར་ལས་བུ་སྲིང་རྫོང་བ་ངན། །སྒོ་འཛུགས་རྐུ་ཇག་བྱིས་པ་གསོ། །དོན་གཉེར་བྱི་བཤོར་ནལ་ཕྲུག་བླང་། །མཛོད་བཅའ་བུད་མེད་འཕྲོག་པ་དང་། །ཚོང་བྱ་ཆར་དབབ་བཟང་བ་ཡིན། །ཆང་རྫ་ལ་གནས་བྱིས་པར་ངན། །ཡོལ་བ་བཏང་བས་ཉེས་པ་ཞི། །བལ་སྐུད་ཀྱི་སྐེར་བཏགས་པས་ཐུབ། །

- **Avoid:** divination (*mo 'debs*), horse racing, making fierce weapons, suppressing, moxibustion, slaughter (*dmar las*), sending away a son or a sister.
- **Good:** setting up a door, theft and robbery, caring for children, lawsuits, elopement, taking in a child born out of wedlock (*nal phrug*), building storehouses, abducting women, trade, bringing rain.
- Left out as doubtful: «རྐེད་མན་ཐག་རིངས་དོན». Seat: the beer jar, bad for children.

## Ox

p. 359 (img. 367):

> གླང་ལ་ཁ་སྨྲས་ཚོང་རིག་སློབ། །རབ་བྱུང་འཆད་ཉན་མནན་པ་དང་། །བག་མ་ནས་བང་ཁ་འབྱེད་པ། །ཆར་པ་དབབ་པ་ངན་པ་ཡིན། །ཡུགས་ས་ཆུང་ལེན་བཟོ་དམག་འདྲེན། །མཚོན་བཟོ་ཞལ་ཆེ་མགར་ཐབ་བཅའ། །སེམས་ལས་ལུགས་ཀྱི་སྡུག་བསྔལ་བཅོས། །ཕྱུགས་ཉོ་མཁར་ལས་རྨང་འདིང་བཟང་། །བོང་བ་ལ་གནས་བྱན་མོར་མན། །གཤམ་ལ་བུག་པ་བྱས་པས་ཐུབ། །ས་སེར་བྲེ་གང་གསེབ་ཏུ་ནི། །རང་ཉོའི་དངོས་པོ་སྤུ་མིང་གཞུག །

- **Avoid:** quarrels (*kha smras*), trade, learning the sciences, ordination, teaching and listening, suppressing, a bride, opening the barley store, bringing rain.
- **Good:** crafts, leading an army, making weapons, judging disputes, setting up a forge, buying livestock, building forts, laying foundations.
- Left out as doubtful: «ཡུགས་ས་ཆུང་ལེན» (taking a widow as a young wife?), «སེམས་ལས་ལུགས་ཀྱི་སྡུག་བསྔལ་བཅོས». Seat: the granary (*bong ba*).

## The six opposed pairs

p. 359 (img. 367), after the ox:

> སྟག་སྤྲེལ་གཉིས་ལ་འདུན་ཁྲོམ་སྤང་། །བྱ་ཡོས་གཉིས་ལ་གཏད་སྟོན་སྤང་། །ཁྱི་འབྲུག་གཉིས་ལ་དམག་ཇག་སྤང་། །ཕག་སྦྲུལ་ཤིང་དང་ས་བརྐོ་སྤང་། །བྱི་རྟ་རྟ་རྒྱུག་དམར་བཤས་སྤང་། །གླང་ལུག་ཁྲི་བརྩིག་དར་འཕྱར་སྤང་། །ཉི་མའི་བཅོས་སྒྲིབ་རིམ་པ་རྣམས། །ལས་དམིགས་གཙོ་བོར་ཤེས་པ་དགོས། །

Tiger and monkey avoid councils and gatherings (*'dun khrom*); bird and
hare avoid «གཏད་སྟོན» (showing a curse object, *gtad*, or a feast for an
entrustment?), left out as doubtful; dog and dragon avoid war and raids
(*dmag jag*; the scan has ཁྱི, Yigdzin-1 བྱི); pig and snake avoid wood
(*shing*, taken as felling and woodwork) and digging; mouse and horse
avoid horse racing and slaughter (*dmar bshas*); ox and sheep avoid
building a throne (*khri brtsig*) and raising silk flags (*dar 'phyar*).
"The remedies and screens of the day animals are to be known with the
work aimed at first": the closing line, which leads into the hours.
