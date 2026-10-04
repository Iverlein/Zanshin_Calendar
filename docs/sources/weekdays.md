# Weekdays

Sigla (WB, NM, SY, KP, Rabten), the numbering of mansions and weekdays, and
the rules for quoting are in [README.md](README.md).

WB vol. 2, pp. 308–312 (scans img. 316–320): the seven planets as
weekdays, each a verse with a woodcut. Each verse gives whose *bla gza'*
the planet is, its caste, nature, colour and element; the activities good
on it; when it is strong; the activities to avoid; setting out and its
directions; a boy and a girl born on it; a death on it; rain and portents;
then falling ill, a theft, a remedy if obstructed (*sgrib na*) and an attack
in war and its counter, which are summarized only. A closing verse (p. 312)
names what each planet forbids even when strong, the days on which virtuous
work is avoided, and the planets' contests. The app's weekday reading
(`Texts.WEEKDAY`, SPEC §5.11) is built from this file.

**How it was read (2026-10-03/04).** Yigdzin-1 (`tools/sources/hf_read.py`)
as the base text, MITRA as witness; the two agree on 98% of syllables
(2,635 syllables, 42 places differ). Every difference was settled on the
scan lines stacked by `disagree.py`; the settled ones are listed under
*Readings settled on the scan*. Jupiter's verse begins བླ་མ་བྱང་སེམས on the
scan (p. 310, img. 318), without the planet's name, which the order of the
verses and the woodcut give. The verses below are the machine reading with
those corrections, not read by eye syllable by syllable; […] or a note
marks what stays unclear.

The lists keep to what can be identified; the words left out of the app's
lists are named under each weekday. The good list closes with བཟང་, the
bad with ངན་ or སྤང་; a few activities sit in both lists of one weekday
(Monday: wearing clothes), where the app keeps the one whose wording is
explicit.

## Sunday, the Sun (ཉི་མ), p. 308, img. 316

> ཉི་མ་པད་གཉེན་ལྷ་སྐྱེས་པ། །ལྷ་དང་སྐྱེས་པའི་གཟའ་ཡིན་པས། །རྒྱལ་པོའི་བླ་གཟའ་རྒྱལ་རིགས་ཡིན། ཞི་བ་དམར་སྐྱ་མེ་ཡི་ཁམས། །རྒྱལ་སར་བསྐོ་དང་ཕོ་བྲང་གདབ། །རྒྱལ་སྲིད་ལས་དང་མི་ཆེན་མཇལ། །དངོས་ལེན་སྦྱིན་སྲེག་སྨན་སྦྱོར་ཁྲུས། །དབང་བསྐུར་རབ་བྱུང་ཤིང་འཛུགས་མཚོན། །ཐབ་འཆའ་བ་དན་རྒྱལ་མཚན་འཛུགས། །འབྲུ་གདབ་རྐུན་ཇག་བཟློག་པ་བྱ། །བུ་བཙས་རྟ་རྒྱུག་རྟ་ཕྱུགས་འདུལ། །གཡང་ལོན་ལྟད་མོ་རྒྱལ་མིང་འདོགས། །ཆུ་རགས་རྟ་དྲེའུ་སྒ་རྒྱག་པ། །གསེར་ཤིང་པགས་པ་རུས་པའི་བཟོ། །འབྲོག་དགོན་འགྲོ་དང་མེ་ལས་བསངས། །ཆོས་འདོན་ལྷ་གསོལ་ཞུ་འབུལ་བ། །ཕྱག་བཙལ་སློབ་གཉེར་ནགས་ཁྲོད་འགྲོ། །སྟོན་མོ་ཡོ་ལང་དྲི་བཟང་ལས། །བཀྲ་ཤིས་དགེ་ལེགས་བྱ་བ་བཟང་། །ཟླ་སྨད་ཉིན་མོ་སེང་ཁྱིམ་སྟོབས། །མནའ་སྐྱེལ་གཤིན་ཆོས་ཤིད་རོ་འདོན། །བཙོན་བཟུང་མི་ཐར་ཚིག་ངན་སྨྲ། །ཞི་ལས་ནད་འདྲི་གཏར་སྲེག་བྱེད། །ལྷ་ཁང་རྨང་འདིང་སྐྲ་སེན་འབྲེག །གོས་དྲ་གྱོན་པ་སྤྲ་ཕྱར་འཚེམ། །ཁྱིམ་འཛིན་སྲི་མནན་གྲོང་ཁྱེར་བརྩིག །ཞིང་འདེབས་རྫིང་ཡུར་ས་ཁ་དབྱེ། །བག་མ་རབ་གནས་མེ་ཏོག་འཛུགས། །སྐྱས་བྱེད་གླང་འདུལ་བང་མཛོད་བརྩིག །ནོར་གཡང་ཕྱིར་གཏོང་བསྡུམ་བྱེད་པ། །ཁ་མཆུ་རྩོད་པ་སོགས་ལ་ངན། །ལམ་ཞུགས་སྤྱིར་ངན་ཁྱད་པར་དུ། །ལྷོ་བྱང་མཚམས་རྣམས་སྤང་བ་ཡིན། །ཁྱེའུ་ཞག་ཟླ་བདུན་སོས་ན། །བུ་ཕོ་ཚེ་རིང་མི་ཆེན་བྱམས། །བུ་མོ་སྐྱེས་ན་ཕྱུག་ན་ཡང་། །ཁྱིམ་གསོ་འདྲེན་ཞིང་སླར་བུ་མོ། །སྐྱེ་བས་ངན་པར་བཞེད་པ་ཡོད། །ཤི་ན་དགུ་འམ་བདུན་ལ་ཟློས། །སྒྲ་སོགས་ལྟས་ངན་ཆར་བ་འབྲིང་། །

- **Who:** the Sun, born of the gods: *bla gza'* of kings, royal caste; peaceful, pale red, fire.
- **Good:** enthronement, founding a palace, affairs of state, meeting great people, fire offerings, preparing medicine, bathing, empowerment, ordination, planting trees, making weapons such as arrows and lances, building a hearth, raising banners and victory banners, sowing, averting thieves and robbers, horse racing, breaking in horses and livestock, prosperity rites, spectacles, naming, building dams, saddling horses and mules, crafts in gold, wood, leather and bone, work with fire, smoke offerings, reciting scripture, worship of deities, presenting petitions, prostrations, study, going into the forest, feasts, making perfumes and incense, auspicious work.
- **Strong:** in the waning half, by day, and in Leo.
- **Avoid:** oaths, funeral rites, carrying out the dead, harsh words, pacifying rites, visiting the sick, bloodletting and moxibustion, laying temple foundations, cutting hair and nails, first wearing of new clothes, sewing tents, taking a new home, suppressing sri spirits, building towns, planting, digging ponds and canals, breaking ground, marriage, consecration, planting flowers, moving house, breaking in oxen, building storehouses, sending wealth out of the house, reconciliation, lawsuits and disputes, setting out.
- **Setting out:** bad in general; above all to the south, the north and the intermediate directions.
- **Born on it:** a boy who lives through seven days and seven months lives long and is loved by the great; a girl brings wealth but more girls follow, so some hold the birth bad.
- **Death, rain, portents:** repeated nine or seven times; portents of sound and the like bad; rain middling.
- **Not in the app's lists:** དངོས་ལེན (receiving the real thing?); བུ་བཙས (giving birth: the birth line says what it means); འབྲོག་དགོན་འགྲོ (going to herders' camps and monasteries?); ཡོ་ལང (household goods?).

## Monday, the Moon (ཟླ་བ), pp. 308–309, img. 316–317

> ཟླ་བ་བུད་མེད་ཀླུའི་བླ་གཟའ། །རྗེ་རིགས་དཀར་སེར་རུ་ཁམས་ཞི། །སྨན་སྦྱོར་ས་བོན་ཆང་བཙོ་ཁྲུས། །ཆུ་ལས་བཀྲ་ཤིས་བྲན་གཡོག་བསྟེན། །ཀླུ་སྨན་ཐབ་འཆའ་ཁྱིམ་གསར་འཛིན། །ལྷ་མཆོད་རྫིང་ཡུར་ས་ཁ་དབྱེ། །རབ་གནས་བག་མ་སྤོས་སྦྱོར་དྲི། །གླང་འདུལ་ལྟད་སྟོན་བསྡུམ་བྱ་བ། །སྐྲ་འཁྲུ་ཞི་བཟོ་སྐྲ་སེན་འབྲེག །བཅུད་ལེན་རྒྱན་འདོགས་ཕྱག་བྱ་བ། །གཉེན་སྦྱོར་གོས་དྲ་འོ་མའི་ལས། །རྟ་འདུལ་ཞིང་དང་ཤིང་འབྲས་དང་། །མེ་ཏོག་ཕྲེང་གྱོན་འབྲས་འབྲུའི་ལས། །གཏར་ཁ་མེ་བཙའ་བློན་མིང་འདོགས། །བླ་ལ་ཡོན་འབུལ་གཡང་འགུགས་པ། །བུ་རམ་ཤིང་དང་དངུལ་ཆུའི་ལས། །ཞི་དང་རྒྱས་པའི་ལས་རྣམས་དང་། །ལྷ་ཁང་རྨང་འདིང་རྟེན་བཞེངས་བཟང་། །ཀཌའི་ཁྱིམ་དང་མཚན་མོ་སྟོབས། །ནོར་ལེན་གཡང་ཅན་ཕྱིར་གཏོང་ཤིད། །སྲི་གནོན་རོ་འདོན་དབང་དྲག་ལས། །རབ་བྱུང་ཆོས་ཉན་ཁ་མཆུ་དམག །གོས་གྱོན་མཁར་ལས་དྲག་མཚོན་བཟོ། །ཆུ་རགས་སྦྱིན་སྲེག་རྔོན་སོགས་ངན། །ལམ་ཞུགས་སྤྱིར་ངན་ལྷོ་ནུབ་ངན། །མི་ཤི་ཟློས་མེད་བུ་སྐྱེས་བྱུག །ཤིན་ཏུ་བཙན་པར་བྱུང་བ་དང་། །བུ་མོ་མཛེས་དུངས་ཡིད་དུ་འོང་། །ཆར་བབས་སླར་ཡང་མང་དུ་འཕེལ། །ལྟོས་བྱུང་ཆུས་འཇིག་བློན་ལ་ངན། །

- **Who:** the *bla gza'* of women and nāgas; noble caste; white and yellow, water; peaceful.
- **Good:** preparing medicine, sowing, brewing beer, bathing, work with water, auspicious work, taking servants, medicine offerings to the nāgas, building a hearth, taking a new home, worship of deities, digging ponds and canals, breaking ground, consecration, marriage, making perfumes and incense, breaking in oxen, spectacles, reconciliation, washing the hair, cutting hair and nails, taking elixirs, putting on ornaments, prostrations, matchmaking, first wearing of new clothes, dairy work, breaking in horses, field work, planting trees, work with grain, bloodletting and moxibustion, offerings to the lama, prosperity rites, pacifying rites, increasing rites, laying temple foundations, making images.
- **Strong:** in Cancer (ཀཌ, MITRA ཀརྞ; the print's stack is small) and at night.
- **Avoid:** receiving wealth, sending wealth out of the house, funeral rites, suppressing sri spirits, carrying out the dead, rites of power, fierce rites, ordination, hearing the dharma, lawsuits, war, building, making weapons such as arrows and lances, building dams, fire offerings, hunting, setting out.
- **Setting out:** bad in general; bad to the south-west.
- **Born on it:** a boy rich and very strong; a girl beautiful and pleasing.
- **Death, rain, portents:** a death is not repeated; rain increases after it falls; portents: danger from water, bad for ministers.
- **Not in the app's lists:** ཞི་བཟོ (peaceful crafts?); མེ་ཏོག་ཕྲེང་གྱོན (wearing flower garlands); བློན་མིང་འདོགས (naming ministers?); བུ་རམ་ཤིང་དང་དངུལ་ཆུའི་ལས (work with sugar cane and mercury); གོས་གྱོན among the bad (wearing clothes), against གོས་དྲ among the good: kept only as good.

## Tuesday, Mars (མིག་དམར, here བཀྲ་ཤིས), p. 309, img. 317

> བཀྲ་ཤིས་ལྷ་མིན་སྐྱེས་སྤྱི་དང་། །དམག་དཔོན་བླ་གཟའ་རྒྱལ་རིགས་ཡིན། །དམར་ནག་དྲག་པོ་མེ་ཡི་ཁམས། །དམག་ལས་སྒྱུ་རྩལ་དམག་དཔོན་བསྐོ། །དྲག་ལས་དགྲ་འཇོམས་རྒྱལ་སར་འཇུག །ཞལ་ཆེ་ཁ་མཆུ་རྐུན་ཇག་འཕོང་། །བཟློག་པ་སྲི་མནན་རྟ་རྒྱུག་པ། །སྦ་རྒྱག་ཤྭ་རགས་བརྩིག་པ་དང་། །སྦྱིན་སྲེག་ཟོར་འཕེན་མཐུ་གཏད་བྱེད། །བུ་ལོན་སྙེག་དང་ཡུལ་གསར་འཛིན། །ཁྱི་གསོ་མེ་བཙའ་ཚེ་ལོ་རྩེ། །གསེར་དང་བྱུ་རུ་རལ་གྲིའི་ལས། །ཡུལ་མཁར་གཞོམ་པ་བྱི་བཤོར་བ། །དབང་དྲག་ལས་དང་དྲག་རིམ་བཟང་། །ལུག་སྡིག་ཁྱིམ་དང་མཚན་དགུག་སྟོབས། །རབ་བྱུང་རབ་གནས་བག་མ་ཁྲུས། །རྒྱན་གདགས་དགེ་ལས་དབང་བསྐུར་མོ། །དུར་ཤིད་གསང་གྲོས་བྲན་གཡོག་བསྟེན། །མཁར་ལས་ལྷ་ཁང་རྟེན་བཞེངས་རྩིས། །སྨན་སྦྱོར་བཟོ་དང་སྐྲ་སེན་འབྲེག །ནོར་གཏོང་གཏར་ཁ་གྲོང་ཁྱེར་བརྩིག །འདུ་འཚོག་སྟོན་མོ་ས་བོན་གདབ། །ལྷ་མཆོད་ཐབ་འཆའ་བང་མཛོད་འཛིན། །རྫིང་ཡུར་ཞིང་ལས་ས་ཁ་འབྱེད། །བཙོན་ཚུད་སྐྱེ་ཤིང་མེ་ཏོག་འཛུགས། །གསོན་དགེ་བརྟན་པ་བཀྲ་ཤིས་ལས། །ལྟད་མོ་བསྡུམ་བྱེད་གཡང་བསླན་ཚོང་། །དྲ་གྱོན་ཕྱ་ཐེར་སྦྲ་འཚེམ་སྐྱས། །ཞི་རྒྱས་དཀར་ཕྱོགས་ཕལ་ཆེར་ངན། །ལམ་ཞུགས་སྤྱིར་ངན་ཁྱད་པར་དུ། །ལྷོ་བྱང་མཚམས་རྣམས་ཤིན་ཏུ་ངན། །བུ་བཙས་གསོ་དཀའ་སྡིག་ལ་སྤྱོད། །དབྱེན་བྱེད་ང་རྒྱལ་ཁོང་ཁྲོ་ཆེ། །བུ་མོ་སྐྱེས་ན་མྱུར་དུ་འཆི། །ཆར་བབས་ཕྱིས་ཐན་ལྟས་བྱུང་ན། །མཚོན་འཇིགས་དམག་དཔོན་སྡིག་སྤྱོད་ངན། །

- **Who:** bkra shis, born of the asuras: *bla gza'* of generals; royal caste; red and black, fierce, fire.
- **Good:** war, martial skills, appointing generals, fierce rites, subduing enemies, enthronement, judging disputes, lawsuits, averting rites, suppressing sri spirits, horse racing, fire offerings, hurling zor, directing magical power against foes, collecting debts, taking new lands, raising dogs, moxibustion, work with gold, coral and swords, destroying lands and forts, rites of power.
- **Strong:** in Aries and Scorpio (ལུག་སྡིག་ཁྱིམ; the next word, མཚན་དགུག, is not clear).
- **Avoid:** ordination, consecration, marriage, bathing, putting on ornaments, virtuous work, empowerment, divination, burial, funeral rites, council, taking servants, building, building temples, making images, astrology, preparing medicine, crafts, cutting hair and nails, sending wealth out of the house, bloodletting, building towns, gatherings, feasts, sowing, worship of deities, building a hearth, building storehouses, digging ponds and canals, field work, breaking ground, planting trees, planting flowers, lasting work, auspicious work, spectacles, reconciliation, prosperity rites, trade, first wearing of new clothes, sewing tents, moving house, pacifying rites, increasing rites, setting out.
- **Setting out:** bad in general; very bad to the south, the north and the intermediate directions; peaceful, increasing and other white works mostly bad.
- **Born on it:** a boy hard to raise, given to evil, divisive, proud and quick to anger; a girl dies young.
- **Death, rain, portents:** a death repeated three times (p. 310, img. 318); rain then drought; portents: danger of weapons, bad for generals and evil-doers.
- **In the lists since 2026-10-04:** ཤྭ་རགས་བརྩིག་པ, building dikes against flash floods ([lunar-dates.md](lunar-dates.md)).
- **Not in the app's lists:** རྐུན་ཇག་འཕོང (*rkun jag* thieves and robbers, Tshig mdzod chen mo; *'phong* unclear); ས[ྦ]་རྒྱག (*sba* is cane, *sba rgyug* a cane or staff, Tshig gter rgya mtsho; making wickerwork?); ཚེ་ལོ་རྩེ (not identified), བྱི་བཤོར (*byi shor*, adultery, Jim Valby and Tshig gter rgya mtsho: not an act the lists can use); བཙོན་ཚུད, གསོན་དགེ, ཕྱ་ཐེར among the bad (imprisonment; virtue for the living?; not identified).

## Wednesday, Mercury (ལྷག་པ), p. 310, img. 318

> ལྷག་པ་གནོད་སྦྱིན་མ་ནིང་གཟའ། །རྒྱལ་པོའི་བླ་གཟའ་དམངས་ཀྱི་རིགས། །ཞི་བ་སྔོ་ལྗང་ཆུ་ཡི་ཁམས། །སྙན་ངག་སྒྲ་སློབ་བསྟན་བཅོས་རྩོམ། །སྔགས་བཟླ་རིན་ཅེན་ས་ལས་ཚོང་། །སྡོམ་ལེན་རབ་གནས་ཡིག་རྩིས་སློབ། །དབང་བསྐུར་ཆོས་སློབ་ཆུ་ཡི་ལས། །བསྡུམས་དང་འབྲེལ་སྙེག་ཁྱིམ་གསར་འཛིན། །རྒྱན་འདོགས་དྲ་གྱོན་དོན་གཉེར་བ། །གཡང་ལོན་འཁོར་བསྟེན་ཞི་བའི་ལས། །སྐྲ་འཁྲུ་ཡོན་འབུལ་བྱ་བ་དང་། །མཁར་ལས་རྫིང་ཡུར་ཁྲོན་པ་འདྲུ། །ལྷ་ཁང་རྟེན་བཞེངས་བང་མཛོད་འཛིན། །འཕོ་སྐྱས་ཞིང་འདེབས་ས་ཁ་དབྱེ། །བཟོ་དང་སྐྱེ་ཤིང་མེ་ཏོག་འཛུགས། །ཕྱུགས་འདུལ་མོ་རྩིས་གཤིན་ལས་བྱ། །རྟ་དྲེལ་སྒ་རྒྱག་རང་དོན་སྒྲུབ། །གཏར་སྲེག་གཉེན་དང་སྐྲ་སེན་འབྲེག །རྒྱལ་པོའི་ཁྲུས་དང་མིག་འཕྲུལ་སྟོན། །ནང་ལེན་དཀར་ཕྱོགས་དགེ་ལས་བཟང་། །ལམ་ཞུགས་སྤྱིར་བཟང་ཤར་བྱང་དང་། །མཚམས་སུ་ཕྱིན་ན་ངན་པ་ཡིན། །དཀར་པོའི་སྟོད་དང་ནག་པོའི་སྨད། །འཁྲིག་དང་བུ་མོའི་ཁྱིམ་སྟོབས་ཆེ། །ཁྱད་པར་རྒྱས་པའི་ལས་ལ་ཤིས། །རབ་བྱུང་ཆང་བཅོ་དུར་སྦོ་མདོས། །ནོར་གཏོང་ཐབ་འཆའ་ཆུ་རགས་རྩིག །ཆོས་ཉན་སྨན་སྦྱར་བག་མ་ལེན། །དབང་ལས་ཀུན་དང་བཟློག་རིམ་བྱེད། །དགྲ་དང་རྩོད་དང་ཞལ་ཆེ་གཅོད། །དམག་ཇག་རྐུན་མ་དྲག་ལས་ངན། །དུར་འདེབས་བཀག་པར་བཤད་ཀྱང་ཡོད། །བུ་སྐྱེས་ངན་ཅིང་མ་ལ་རྩུབ། །ནད་བུ་ཅན་དུ་འོང་བ་དང་། །བུ་མོ་བཙས་ན་ཕ་མ་དབུལ། །མོ་རང་ཟས་ནོར་དབང་ཐང་ཆེ། །མི་ཤི་ལྔར་ངན་བཙོན་མི་ཐར། །ཆར་བབས་ཕྱིས་ཞོད་ལྟས་བྱུང་ན། །རླུང་འཇིགས་ཆུ་འགྲམ་གྲོང་ཁྱེར་དང་། །སྐྱེས་པའི་ཚོགས་ལ་ངན་པར་བྱེད། །

- **Who:** the yakṣa, a neuter planet; *bla gza'* of kings; commoner caste; peaceful, blue and green, water.
- **Good:** poetry and grammar, writing treatises, reciting mantras, work with the precious substances, earthworks, trade, taking vows, consecration, learning writing and astrology, empowerment, studying the dharma, work with water, reconciliation, seeking connections, taking a new home, putting on ornaments, first wearing of new clothes, lawsuits, prosperity rites, taking a retinue, pacifying rites, washing the hair, presenting offerings, building, digging ponds, canals and wells, building temples, making images, building storehouses, moving house, planting, breaking ground, crafts, planting trees, planting flowers, breaking in livestock, astrology and divination, funeral rites, saddling horses and mules, bloodletting and moxibustion, matchmaking, cutting hair and nails, bathing, magic shows, virtuous work, increasing rites, setting out.
- **Strong:** at the start of the waxing and the end of the waning half, in Gemini and Virgo; increasing rites especially auspicious.
- **Avoid:** ordination, brewing beer, sending wealth out of the house, building a hearth, building dams, hearing the dharma, preparing medicine, taking a bride, rites of power, averting rites, disputes, judging disputes, war, robbery, fierce rites.
- **Setting out:** good in general; bad to the east, the north and the intermediate directions (ཤར་བྱང་དང་མཚམས).
- **Born on it:** a boy bad, harsh to his mother, sickly; a girl makes her parents poor, but has food, wealth and power herself.
- **Death, rain, portents:** a death bad five times over; a prisoner is not freed; rain then clearing; portents: danger of wind, bad for riverside towns and assemblies of men.
- **Not in the app's lists:** རང་དོན་སྒྲུབ (accomplishing one's own aims); ནང་ལེན (taking in); དུར་སྦོ and མདོས among the bad (not identified; MITRA དུར་སྤོ).

## Thursday, Jupiter (ཕུར་བུ), pp. 310–311, img. 318–319

> བླ་མ་བྱང་སེམས་བློན་པོ་དང་། །བརྗེ་སྐྱེས་པའི་བླ་གཟའ་ཡིན། །སྐུ་མདོག་སེར་པོ་བྲམ་ཟེའི་རིགས། །ཞི་དྲག་འདྲེས་མ་རླུང་གི་ཁམས། །རབ་བྱུང་དབང་བསྐུར་རབ་གནས་སྡོམ། །བག་མ་དྲ་གྱོན་ས་བོན་གདབ། །དགེ་ཕྱོགས་དཀྱིལ་འཁོར་སྦྱིན་སྲེག་བྱ། །རིག་བསླབ་འཆད་ཉན་རྒྱལ་སར་འཇུག །གཏར་ཁ་རྒྱག་དང་དབང་གི་ལས། །མེ་བཙའ་སྨན་སྦྱོར་མོ་རྩིས་བྱ། །མཁར་ལས་ཐབ་འཆའ་ཁྱིམ་གསར་འཛིན། །ཆང་བཅོ་སྔགས་བཟླ་རྒྱན་འདོགས་ཚོང་། །སྲི་མནན་ལྷ་ཁང་རྟེན་བཞེངས་བྱ། །སྤྲ་ཕྱར་འཚེམ་དང་བ་དན་འཛུགས། །ཤིང་ལས་འཕོ་སྐྱས་རྟ་གླང་འདུལ། །རྟ་དྲེལ་སྒ་རྒྱག་འཁོར་བསྟེན་བསྡུམས། །སྟོན་མོ་མེ་ཏོག་སྐྱེ་ཤིང་འཛུགས། །སྐྲ་འཁྲུ་རིན་ཅེན་ལྔ་ཡི་ལས། །བཟློག་པ་མཐུ་གཏད་ཧོམ་སྤར་བ། །ཕ་མའི་དོན་དང་རྔ་ཡབ་ལས། །འདུན་གྲོས་བརྟན་པའི་ལས་བྱ་དང་། །གཤེགས་གསོལ་བཀྲ་ཤིས་བྱ་བར་བཟང་། །ལམ་ཞུགས་སྤྱིར་བཟང་ནུབ་ཕྱོགས་སྤང་། །བསྐྲད་ལས་ཉ་དང་གཞུ་ཁྱིམ་སྟོབས། །ཐོག་འབུབས་དུར་འདེབས་མནའ་སྐྱེལ་དམག །ནད་ཐེབས་ཕྱུགས་གཏོང་སྐྲ་སེན་འབྲེག །མདོས་གཏོར་ཟོར་ཁ་འཕེན་པ་དང་། །བཙོན་ཚུད་བཟོ་ཡི་བྱ་བ་ངན། །གཤིན་ལས་ངན་ཀྱང་ཤིད་སྟོན་བཟང་། །མི་ཤི་ཤུལ་ལ་ངན་པ་ཙམ། །བུ་ཕོ་སྐྱེས་ན་བཞིན་ངན་ཅིང་། །བློ་གསལ་མཁས་ཤིང་ཆོས་ཀྱང་བྱེད། །བུ་མོ་ཚེ་རིང་གྲོགས་ཀྱང་བཟང་། །ལྟས་བྱུང་ཐོག་འཇིགས་དགེ་སློང་དང་། །རིག་བྱེད་མཁན་ལ་ངན་པར་བྱེད། །

- **Who:** *bla gza'* of lamas, bodhisattvas, ministers and the བརྗེ་སྐྱེས (the twice-born?); yellow, brahmin caste; mixed peaceful and fierce, wind. The verse does not name the planet: the woodcut and the order do.
- **Good:** ordination, empowerment, consecration, taking vows, marriage, first wearing of new clothes, sowing, virtuous work, maṇḍala rites, fire offerings, learning the sciences, teaching and hearing the dharma, enthronement, bloodletting and moxibustion, rites of power, preparing medicine, astrology and divination, building, building a hearth, taking a new home, brewing beer, reciting mantras, putting on ornaments, trade, suppressing sri spirits, building temples, making images, sewing tents, raising banners and victory banners, woodwork, moving house, breaking in livestock, saddling horses and mules, taking a retinue, reconciliation, feasts, planting flowers, planting trees, washing the hair, work with the precious substances, averting rites, directing magical power against foes, the parents' affairs, council, lasting work, auspicious work, setting out.
- **Strong:** in Pisces and Sagittarius (ཉ་དང་གཞུ); the line opens with བསྐྲད་ལས, expelling rites, probably what succeeds then.
- **Avoid:** roofing, burial, oaths, war, sending livestock away, cutting hair and nails, hurling mdos, gtor ma and zor, crafts, funeral rites.
- **Setting out:** good in general; avoid the west.
- **Born on it:** a boy plain of face but clear-minded, learned and religious; a girl long-lived, with good friends.
- **Death, rain, portents:** a death only a little bad for those left; funeral rites bad but the funeral feast (ཤིད་སྟོན) good; portents: danger of lightning, bad for monks and Vedic scholars.
- **Not in the app's lists:** རྔ་ཡབ་ལས (work with yak-tail whisks?); གཤེགས་གསོལ (rites for the departed?); ཧོམ་སྤར་བ (hom offerings: kept under fire offerings); ནད་ཐེབས, བཙོན་ཚུད among the bad (falling ill; imprisonment: not activities).

## Friday, Venus (པ་སངས), p. 311, img. 319

> དཀར་པོ་སྟག་གཟིག་རྒྱལ་བློན་དང་། །བུད་མེད་བླ་གཟའ་བྲམ་ཟེའི་རིགས། །ཞི་བ་ས་ཁམས་སྣ་ཚོགས་མདོག །ལྷ་གསོལ་ཆོས་འཆད་རབ་བྱུང་གནས། །དཀར་ཕྱོགས་རིམ་གྲོ་ལྷ་རྟེན་བཞེངས། །ཆང་བཅོ་གྲོགས་བཙལ་ས་བོན་གདབ། །གཉེན་བསྒྲིག་མཛའ་བོ་བག་མ་ཚོང་། །རྒྱན་འདོགས་དྲི་སྦྱོར་སོ་ནམ་ལས། །ལྷ་ཁང་ཁང་གསར་རྨང་གཞི་འདིང་། །དྲ་གྱོན་མཁར་ལས་རྫིང་ཡུར་འདྲུ། །ལྕགས་སོགས་རིན་ཅེན་ལྔ་ཡི་བཟོ། །གཏར་ཁ་རྒྱག་དང་རྒྱས་པའི་ལས། །མེ་བཙའ་ཁྱིམ་འཛིན་ལྟད་མོ་དང་། །འཁོར་བསྟེན་གཤིན་གྱི་བྱ་བ་སྤྱི། །རྟ་རྒྱུག་རྩེད་མོ་ཐབ་ཁ་བཅའ། །སྟོན་མོ་སྐྲ་འཁྲུ་སྐྲ་སེན་འབྲེག །ས་དབྱེ་རྟ་དྲེལ་སྒ་རྒྱག་པ། །འདུན་གྲོས་བརྟན་དང་བཀྲ་ཤིས་ལས། །མོ་རྩིས་རིག་སློབ་སྨན་ཚོང་བསྡུམས། །རྒྱལ་སྲིད་བྱ་བ་ཞལ་ཆེ་གཅོད། །རྙེད་བཀུར་ཞུ་གསོལ་ཕྱག་མཆོད་དབུལ། །ཆུ་རགས་བུད་མེད་བགྲོད་བཙོན་བཟང་། །ལམ་ཞུགས་སྤྱིར་བཟང་ལྷོ་བྱང་དང་། །ནུབ་ཀྱི་ཕྱོགས་སུ་ཕྱིན་ན་ངན། །མར་ངོ་གླང་སྲང་དབང་ལས་འགྲུབ། །གཤགས་འཁོན་འཕོ་སྐྱས་བུ་ལོན་འཇལ། །དུར་སྔ་མགྲོན་འགྲོ་མདོས་གཏོར་འཕེན། །ནད་ཐེབས་རྐུན་ཇག་འཁོན་པར་ས། །དྲག་ལས་ལ་སོགས་ངན་པ་ཡིན། །ཧོམ་སྤར་ངན་ཞེས་བཀག་ཀྱང་ཡོད། །བུ་སྐྱེས་ཚེ་རིང་དབང་རྣོ་ཕྱུག །བུ་མོ་ནད་འོང་དཔལ་ཀྱང་ཆེ། །མི་ཤི་བདུན་ཟློས་ཆར་བབས་ལེགས། །ལྟས་བྱུང་འབྲུ་འཕེལ་སློབ་དཔོན་དང་། །བུད་མེད་ལ་ངན་

- **Who:** the white one (དཀར་པོ): *bla gza'* of the Tajiks (སྟག་གཟིག), kings, ministers and women; brahmin caste; peaceful, earth, many-coloured.
- **Good:** worship of deities, teaching the dharma, ordination, consecration, virtuous work, making images, brewing beer, seeking friends, sowing, matchmaking, marriage, trade, putting on ornaments, making perfumes and incense, farming, building temples, building new houses, laying foundations, first wearing of new clothes, building, digging ponds and canals, crafts in iron and the five precious substances, bloodletting, increasing rites, moxibustion, taking a new home, spectacles, taking a retinue, funeral rites, horse racing, games, building a hearth, feasts, washing the hair, cutting hair and nails, breaking ground, saddling horses and mules, council, lasting work, auspicious work, astrology and divination, learning the sciences, reconciliation, affairs of state, judging disputes, presenting petitions, prostrations, presenting offerings, building dams, consorting with women, rites of power, setting out.
- **Strong:** in the waning half and in Taurus and Libra (གླང་སྲང); rites of power succeed.
- **Avoid:** feuds, moving house, paying debts, hurling mdos, gtor ma and zor, robbery, fierce rites, sending livestock away.
- **Setting out:** good in general; bad to the south, the north and the west.
- **Born on it:** a boy long-lived, sharp and rich; a girl sickly but of great glory.
- **Death, rain, portents:** a death repeated seven times; good rain; portents: grain increases, bad for teachers and women.
- **Not in the app's lists:** རིམ་གྲོ (services); སྨན་ཚོང (selling medicine?); རྙེད་བཀུར (gain and honour); དུར་སྔ་མགྲོན་འགྲོ (not identified; MITRA དུར་སབ), ནད་ཐེབས, འཁོན་པར་ས among the bad; ཧོམ་སྤར (hom offerings), "forbidden by some": in the summary, not in the lists.

## Saturday, Saturn (སྤེན་པ), p. 312, img. 320

> སྤི༷ན་པ༷་དབུལ་པོ་མ་ནིང་བྲན། །བྱིས་པའི་བླ་གཟའ་གདོལ་པའི་རིགས། །དྲག་གཟའ་སེར་སྐྱ་ས་ཡི་ཁམས། །ཁང་བཟུང་ཞལ་བསྲོ་ནོར་སྒྲུབ་པ༑ །ཚེ་གཡང་སྒྱེད་འཛུགས་ནོར་ཕྱུགས་ལེན། །དམག་འདྲེན་ཞིང་ལས་གྲོང་ཁྱེར་འགའ་ཞིག་ཏུ། གདབ། །མེ་ཏོག་སྐྱེ་ཤིང་འཛུགས་པ་དང་། །ལྕགས་ལས་རྫིང་ཁྲོན་ས་ཁ་དབྱེ། །སྲི་གནོན་རྒྱལ་མཚན་བང་མཛོད་འཛིན། །ཡུལ་མཁར་མིང་འདོགས་བུད་མེད་བགྲོད། །མདའ་མཚོན་ནག་ལས་ཁྱི་གསར་གསོ། །རྩིས་དང་རྐུན་ཇག་ས་བོན་བཟང་། །མཚན་མོ་ཟླ་བའི་མར་ངོ་དང་། །ཆུ་སྲིན་བུམ་ཁྱིམ་བསད་ལས་འགྲུབ། །རབ་བྱུང་རབ་གནས་ཁྲིམས་འཆལ་འཆོས། །གསོན་དགེ་འདུན་གྲོས་བཀྲ་ཤིས་བརྟན། །དྲ་གྱོན་ཞི་བཟོ་སྐྲ་སེན་འབྲེག །རྒྱས་དང་དབང་ལས་གཏར་ཁ་རྒྱག །མེ་བཙའ་སྐྲ་འཁྲུ་སྨན་སྦྱར་དང་། །ཚོང་བྱེད་ཟློག་པ་ཕྲུ་ཟློག་བག །ནོར་ཕྱུགས་ཕྱིར་གཏོང་སྦྲ་ཕྱར་འཚེམ། །བཙོན་བཟུང་ལོངས་སྤྱོད་ཐམས་ཅད་འཆོར། །གཤིན་ལས་སྐྱས་དང་རྩེད་མོ་བྱ། །ལྷ་ཁང་རྟེན་བཞེངས་ཞིང་ཁང་ཚོང་། །རྟ་དྲེལ་སྒ་རྒྱག་སྟོན་མོ་བསྡུམས། །ལྷ་གསོལ་རྒྱན་འདོགས་རྒྱལ་ས་བཟུང་། །རྒྱལ་པོ་བསྙེན་བཀུར་ཕྱག་མཆོད་འབུལ། །རྒྱལ་སྲིད་བྱ་བ་ཞལ་ཆེ་གཅོད། །དགྲ་འཐབ་འཁོར་བསྟེན་ལྟད་མོ་ང༷ན། །ལམ་ཞུགས་སྤྱིར་ངན་ཁྱད་པར་དུ། །མཚམས་ཤར་ནུབ་རྣམས་སྤང་བྱ་ཡིན། །སྐྱེས་པ་གཟུགས་བཟང་བསོད་ནམས་ཆུང་། །བུ་འབྲིང་བུ་མོ་ཆེ་ཡང་ཐུང་། །ཤི་ན་ཟློས་མེད་ཆར་བབས་ངན། །

- **Who:** poor, neuter, a servant: *bla gza'* of children; caṇḍāla caste; fierce, grey-yellow, earth.
- **Good:** taking a new home, gaining wealth, long-life and prosperity rites, acquiring goods and livestock, leading an army, field work, building towns, planting flowers, planting trees, ironwork, digging ponds and wells, breaking ground, suppressing sri spirits, raising banners and victory banners, building storehouses, building, naming, consorting with women, making weapons such as arrows and lances, black rites, raising dogs, astrology, robbery, sowing, killing others.
- **Strong:** at night, in the waning half, in Capricorn and Aquarius (ཆུ་སྲིན་བུམ); killing succeeds.
- **Avoid:** ordination, consecration, restoring broken vows, virtuous work, council, auspicious work, lasting work, first wearing of new clothes, cutting hair and nails, increasing rites, rites of power, bloodletting and moxibustion, washing the hair, preparing medicine, trade, averting rites, marriage, sending wealth out of the house, sewing tents, funeral rites, moving house, games, building temples, making images, trading land and houses, saddling horses and mules, feasts, reconciliation, worship of deities, putting on ornaments, enthronement, serving the king, prostrations, presenting offerings, affairs of state, judging disputes, fighting enemies, taking a retinue, spectacles, setting out.
- **Setting out:** bad in general; above all to the intermediate directions, the east and the west.
- **Born on it:** handsome but of little merit; a boy middling, a girl short-lived (the print's ཆེ or ཚེ, MITRA ཚེ).
- **Death, rain, portents:** a death not repeated; rain bad; a prisoner loses all he owns.
- **In the lists since 2026-10-04:** ཞལ་བསྲོ, laying foundations: the dictionaries give *zhal bsro* two senses, the opening of the eyes of a consecrated image and the laying of a foundation (Tshig mdzod chen mo, Brda dkrol gser gyi me long); beside ཁང་བཟུང (taking a house), and with consecration among Saturday's bad, it is the second.
- **Not in the app's lists:** གྲོང་ཁྱེར(འགའ་ཞིག་ཏུ)གདབ: the bracket is the editors' "in some copies"; read as founding towns; ཞི་བཟོ, ཕྲུ་ཟློག (MITRA རློག) among the bad (not identified).

## The closing verse, p. 312, img. 320

> ཉི་མ་གནམ་སྒོ་དར་ཡང་ཤིད་མི་བྱ། །ཟླ་བ་ཀླུ་སྒོ་དར་ཡང་ཧོམ་མི་སྤར། །དམག་དཔོན་གྲི་གཟའ་དར་ཡང་གྲི་མི་འདུལ། །ལྷག་པ་རབས་ཆད་དར་ཡང་དུར་མི་གདབ། །ཕུར་བུ་ཤིད་གཟའ་དར་ཡང་ཟོར་མི་འཕངས། །པ་སངས་ཕྱུགས་གཟའ་དར་ཡང་ཕྱུགས་མི་སྐྱེལ། །སྤེན་པ་ས་གཟའ་དར་ཡང་ནོར་མི་གཏོང་། །ཉི་མ་མིག་དམར་སྤེན་པ་གསུམ། །སྒྲ་གཅན་གདོང་མཇུག་བིཥྚི་དང་། །ཤ་འཁོན་ཡོངས་བསྣུན་ཀུན་བརྡུང་ལ། །གཞན་རྣམས་བཟང་སྟེ་དགེ་ལས་སྤང་། །རང་གཟའ་བླ་གཟའ་ཤར་བ་ལ། །གཤགས་འདེབས་རྩལ་སྤྲོད་སྟོབས་འགྱེད་དང་། །རྟ་རྒྱུག་ཤོ་རྒྱན་ཚོང་བྱ་བ། །གཡུལ་འགྱེད་འཁྲུག་སོགས་ལས་ཀུན་བཟང་། །སྤྱིར་བཏང་ཉི་ཟླ་ཕུར་བུ་གསུམ། །གང་འཕྱི་བ་དེ་རྒྱལ་བ་དང་། །དམག་དཔོན་པ་སངས་སྤེན་པ་གསུམ། །གང་སྔ་བ་དེ་རྒྱལ་ཞེས་ཀྱང་། །དྲག་གཟའ་དར་ན་རྒོལ་བ་བཟང་། །ཞི་དར་དྲག་གུད་བཏང་སྙོམས་ཏེ། །གཞན་ཡང་ཤིན་ཏུ་གཅེས་པ་ཡི། །མན་ངག་ཁ་ཤས་ཐེམ་དུ་སྦས། །གཟའ་བདུན་གང་ལ་རེས་ཡོད་ཀྱང་། །གྲོགས་ཕྱོགས་དོན་གྲུབ་ནོར་དང་ཕྲད། །མ་ཕྱོགས་གཏམ་སྙན་ཐོས་པའི་ས། །རང་ཕྱོགས་ཁ་མཆུ་མྱ་ངན་འོང་། །བུ་ཕྱོགས་བྱེ་བྲལ་སྟོར་བརླག་འོང་། །དགྲ་ཕྱོགས་རྩོད་དང་འཐབ་པའི་རྒྱུ། །

- **Even when strong** (*dar yang*): no funeral rites on the Sun, the door of
  heaven; no hom offering on the Moon, the door of the nāgas; Mars, the
  general, the planet of knives: no taming with a knife; Mercury, which ends
  the lineage: no grave dug; Jupiter, the planet of funerals: no *zor*
  hurled; Venus, the planet of livestock: no livestock sent away; Saturn,
  the planet of earth: no wealth given away.
- **Virtuous work avoided** on the Sun, Mars and Saturn, on Rāhu's head and
  tail, on Viṣṭi and on the yogas sha 'khon, yongs bsnun and kun brdung,
  "even if the rest is good". The app's weekday dot follows this line
  (`Texts.weekdayTone`): Mars and Saturn unlucky, the Sun mixed, since its
  own verse calls it peaceful.
- On one's own planet and *bla gza'*: duels, contests of skill and strength,
  horse races, dice, trade and battle all good. Of the Sun, Moon and Jupiter
  the later wins, of Mars, Venus and Saturn the earlier; when a fierce
  planet is strong, attacking is good. The planets' quarters (friend,
  mother, own, child, enemy) bring gain, good news, quarrels, separation
  and strife. None of this is a day reading; the app does not use it.

## Readings settled on the scan

| Img., line | Yigdzin | MITRA | Scan | Note |
| --- | --- | --- | --- | --- |
| 316, 5 | མཚོན | མཚན | མཚོན | weapons |
| 316, 10 | བཙོན | བཙན | བཙོན | prisoner (the same at 317/21, 318/16, 319/5, 9, 21, 27, 320/10) |
| 316, 10 | སྤྲ | སྨྲ | སྨྲ | harsh words spoken |
| 316, 15 | སྐྲ | སྒྲ | སྒྲ | portents of sound |
| 316, 18 | ལྷ | ལྟ | unclear | the thief's door faces south; not used |
| 317, 2 | ཀཌའི | ཀརྞའི | ཀ + small stack | Cancer, read from the sense |
| 317, 6 | ཀླུ | གླུ | unclear | in the illness lines; not used |
| 317, 14 | སྦ་རྒྱག | སྣ | ས[ྦ]་རྒྱུག | not saddling (སྒ་རྒྱག); left out |
| 317, 26; 319, 21, 26 | ལྷོ(ར) | ལྟོ(ར) | ལྷོ | south |
| 317, 27 | བྱེ | བྱི | བྱི | a mouse's skull (remedy) |
| 318, 12 | སྦོ | སྤོ | unclear | Wednesday's bad list; left out |
| 319, 2, 23; 320, 19 | སྤར | སྦར | — | hom offering, either spelling |
| 319, 22 | སྔ | སྦ | unclear | Friday's bad list; left out |
| 320, 1 | སྤི༷ན | སྤེན | སྤེན | Saturn |
| 320, 3 | སྒྱེད | སྐྱེད | unclear | long-life and prosperity rites either way |
| 320, 9, 15 | ཟློག | རློག | unclear | left out |
| 320, 12 | ལྷད | ལྟད | ལྟད | spectacles |
| 320, 14 | ཆེ | ཚེ | unclear | a girl short-lived |
| 320, 22 | སྒྲ | སྐྲ | སྒྲ | Rāhu (སྒྲ་གཅན) |
| 320, 22 | བསྡུ་ན | བསྣུན | བསྣུན | the yoga yongs bsnun |

## Words looked up, 2026-10-04

The words left out above were looked up in the 64 dictionaries of
Christian Steinert's collection (github.com/christiansteinert/tibetan-dictionary,
`_input/dictionaries/public`: Rangjung Yeshe, Jim Valby, Ives Waldo, Dan
Martin, the Tshig mdzod chen mo, Dung dkar, Dag tshig gsar bsgrigs, Brda
dkrol gser gyi me long, Bod yig tshig gter rgya mtsho and others), by
Wylie headword. What they settle:

| Word | Weekday | Found | In the lists |
| --- | --- | --- | --- |
| ཞལ་བསྲོ | Saturday, good | laying a foundation (or the consecration of an image) | yes, laying foundations |
| ཤྭ་རགས | Tuesday, good | a dike against flash floods ([lunar-dates.md](lunar-dates.md)) | yes, with dams |
| འབྲོག་དགོན་འགྲོ | Sunday, good | *'brog dgon*: solitary country without villages (Ives Waldo, Jim Valby) | no: no such activity; going into the forest is already listed |
| ཡོ་ལང | Sunday, good, after སྟོན་མོ | festivities, a feast (Sgom sde tshig mdzod chen mo: *ston mo, gsol ston*; Dag tshig gsar bsgrigs: merry-making) | no: feasts already listed |
| རྔ་ཡབ་ལས | Thursday, good | the yak-tail whisk (all) | no activity |
| གཤེགས་གསོལ | Thursday, good | asking the deity to depart at a rite's end (Rangjung Yeshe) | no activity |
| བྱི་བཤོར | Tuesday, good | *byi shor*, adultery (Jim Valby, Tshig gter rgya mtsho) | no |
| ཕྲུ་ཟློག | Saturday, bad | *phru* is turning the soil of a field (Dag tshig gsar bsgrigs; Tshig mdzod chen mo: *sa zhing, phru slog*), so ploughing | no: Saturday's good list has field work, and ploughing is not split from it |
| སྦ་རྒྱག | Tuesday, good | *sba* cane (*sba rgyug*, a cane or staff); the act is not attested | no |
| དུར་སྦོ | Wednesday, bad | not in the dictionaries; MITRA's དུར་སྤོ is "moving a grave", as in Aśvinī's verse | no |
| མདོས | Wednesday, bad | the thread-cross ransom rite (as in the lunar dates' *mdos* and *gtor ma*) | no: no activity of its own |

Not found in any of them: དངོས་ལེན, ཞི་བཟོ, ཚེ་ལོ་རྩེ, གསོན་དགེ, ཕྱ་ཐེར (beside
tents and sewing here and in Rohiṇī's verse, so probably a cloth or tent
item), སྨན་ཚོང, དུར་སྔ་མགྲོན་འགྲོ, མཚན་དགུག.

