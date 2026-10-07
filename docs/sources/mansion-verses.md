# The mansion verses of the White Beryl

Sigla, numbering and quoting rules are in [README.md](README.md); the
abbreviations of the *kun phan me long* and the doubled mansions are in
[mansions.md](mansions.md).

WB vol. 2, pp. 313–328 (scans img. 321–336): after the heading "the good and
bad results of the 28 lunar mansions" (རྒྱུ་སྐར་ཉི་ཤུ་རྩ་བརྒྱད་ཀྱི། །བཟང་ངན་འབྲས་བུ་བརྗོད་པར་བྱ།),
one verse for each of the **28** mansions, from dbyu gu (Aśvinī) to nam
gru (Revatī), Abhijit (byi bzhin) included after gro bzhin, each beside a
woodcut of its deity.

Every verse runs in the same order: number of stars and shape; clan
(རུས or རིགས), deity, food, element; the mansion's kind (*mgron skar*, *dgra
skar* …) and whose *bla* it is; a long list of what it is good for, closed
by བཟང; a shorter list of what to avoid, closed by འཛེམ, སྤང or ངན; what a
birth on it means; what an illness begun on it means and its remedy; what
a death means; portents; rain; its minor star (*skar chung*, after ༧); and
the remedy "if obstructed" (*sgrib na*).

**How the text was read** (2026-10-03). Two OCR models (BDRC's Yigdzin-1
and MITRA, `tools/sources/hf_read.py`) read every page; they disagree at
one to nine places a page. Each disagreement was put to agy as a choice
between the two readings (`agy_choose.py`), and then judged: a reading
that matches the same formula elsewhere in these verses (སྦྲ་ཕྱར "pitching
tents", ལྟས་བྱུང "portents arise", ཕུར་བུ་ལྷར་འཛིན …), a known term (ཆོམ་པོ
"robber", སཱ་ལ "śāla tree") or a reading by eye on the scan wins over agy's
choice, which proved wrong where such a check exists in about one case in
five. Where Yigdzin-1 dropped the lines beside a woodcut (the heads of
skag, sa ga and byi bzhin), MITRA's reading is given. So the Tibetan below
is a reading by machine with every disagreement settled, not a reading by
eye: where both models make the same mistake (both read བཙན for the
print's བཙོན once on p. 349) it can still hide. The five readings left open on
2026-10-03 were read on enlarged scan crops on 2026-10-04 and settled
(lag's བསྣུམས, as printed, is probably a slip for བསྡུམས); none stays open.
The English gives the lists in full and the rest in brief.


**The lists read by eye** (2026-10-07, ROADMAP T2.5). The good and avoid
lists of all 28 verses were read on the 1996 scans (img. 321–336, at
twice their served size, doubtful places enlarged further) against the
Tibetan below, and agree with it wherever the scan is clear; the verse heads,
births, illnesses and omens were not read again. Three places stay
doubtful in the print, and the Zhol print (WBZ, BDRC I1KG1710, img.
1066–1067) settles one: Kṛttikā's «དྲ་གྱོད» is «དྲ་གྱོན», new clothes, a
slip of the 1996 typesetting. Bharaṇī's «རྟ་ཕྱུགས་འོ» ends in a syllable
neither print shows clearly, and its «ཞལ་ཆེ་དམག་དྲངས་ཁ་དབྱེ» has six
syllables where the metre wants seven; neither changes a list.

**What the app takes from them** (SPEC §5.10): each verse's lists as
wording keys (`core/.../texts/MansionVerses.kt`), joined to the mansion's
reading before Henning's list and the *kun phan me long*'s boxes. Left
out: words marked (?) below, what a verse calls middling (*'bring*) or
acceptable (*rung*), omens that are no act (a birth, a death, a girl
born), and Abhijit's verse, since the Phugpa calendar does not count it
among the day's mansions. A work qualified by a direction or a kind of
person (lawsuits in the south and west, funerals of the low-born) is
shown and weighs for neither side. "For the most part" (*phal cher*) and
"but for the special cases" (*dmigs bsal ma gtogs*) are taken as the
verse's general rule, which they are: the special cases are what
chapter 34 gives work by work (ROADMAP T2.6).

## 1. dbyu gu (Aśvinī)

p. 313 (img. 321):

> དབྱུ་གུ་སྐར་གསུམ་རྟ་མགོའི་མགུལ། །རུས་ནི་རྟ་འཇིག་ལྷ་དྲི་ཟ། །ཟས་ཤ་སྦྲང་རྩི་རླུང་གི་ཁམས། །དགྲ་སྐར་མི་བརྟན་རྐུན་པོའི་བླ། །ཆོས་འཆད་རབ་བྱུང་སྡོམ་པ་འབེབས། །ལྷ་གཡང་དགེ་ལེགས་དྲ་གྱོན་ཚོང་། །སྲི་མནན་དམག་དང་རོ་འདོན་རྩོད། །ཐབ་འཆའ་བ་དན་རྒྱལ་མཚན་འཛུགས། །གཤིན་ལས་དུར་སྤོ་སྐྲ་སེན་འབྲེག །སྐྲ་འཁྲུ་རྫིང་ཡུར་ཁྲོན་པ་འདྲུ། །དོན་གཉེར་བོན་ཆོག་ཕྱུགས་འདུལ་བསྡུམས། །མཆོད་བཟོ་ཕྱིར་འཇལ་ཁྱི་བསྟེན་ལམ། །དགྲ་འདུལ་རྐུན་ཇག་ཡུལ་དབྱུང་བ། །བཟློག་པ་བྱོལ་ཟོར་ཡུགས་ས་བོར། །དགྲ་སྒྲུབ་བན་རྫོང་གཏམ་ཁུངས་བཅད། །དམིགས་བསལ་མ་གཏོགས་ལས་བཞི་དང་། །རིམ་གྲོ་ལྟད་མོ་མིང་འདོགས་བཟང་། །བཙོན་ཚུད་ཞག་པོ་གསུམ་ན་ཐར། །ས་དབྱེ་འདུ་ལོང་ཆེན་པོའི་ལས། །རྒྱན་འདོགས་སྨན་སྦྱར་གསོན་ལས་གཉེན། །མཁར་ལས་གཏར་སྲེག་ཚེ་འགུགས་སྐྱས། །རྩིག་རྨང་འདིང་དང་བག་མ་དང་། །བུ་སྲིང་ཕྱིར་གཏོང་ལ་སོགས་ངན། །

Good and avoid lists read on the scan, 2026-10-07.

Three stars, like a horse's head and neck; clan Rta 'jig (the first letter
of རུས is worn), deity the gandharvas; food meat and honey; wind; an enemy
mansion, unstable, the *bla* of thieves.

- **Good:** teaching the dharma, ordination, giving vows, prosperity rites,
  virtuous acts, new clothes, trade, suppressing *sri*, war, carrying out a
  corpse, disputes, setting up a hearth, raising banners and victory
  standards, funeral rites, moving a grave, cutting hair and nails, washing
  the hair, digging ponds, canals and wells, lawsuits, Bon rites, taming
  livestock, reconciling, making offerings, paying back, keeping dogs,
  setting out, subduing enemies, driving thieves and robbers from the
  land, averting rites, the *byol zor*, ending mourning, rites against
  enemies, sending off a monk (?), stopping rumours; the four activities
  but for the special cases; services, shows, naming. Who is imprisoned
  goes free in three days.
- **Avoid:** breaking ground, great gatherings, putting on ornaments,
  preparing medicine, works for the living, marriage alliances, building,
  bloodletting and moxibustion, summoning long life, dowries, laying
  foundations, brides, sending away a son or a sister.
- **Born on it:** lives sixty to eighty years, eloquent, handsome,
  virtuous, of a physician's line, skilled in song, dance and music;
  restless in mind and body.
- Illness from travel and water work, cured by a ransom on a dough horse;
  a death is bad for the neighbours; portents strife and great winds,
  epidemics, bad for mantrins and Bonpos; rain brings drought; minor star
  *drag po stobs ldan*, very good for fierce rites and carrying out a
  corpse; if obstructed, hold a horse skull above oneself.

## 2. bra nye (Bharaṇī)

p. 314 (img. 322):

> བྲ་ཉེ་སྐར་གསུམ་མོ་མཚན་དབྱིབས། །རུས་ནི་ཝརྒ་ལྷ་གཤིན་རྗེ། །ཁ་ཟས་ཏིལ་འབྲས་མེ་ཡི་ཁམས། །དམེ་སྐར་འཕུང་བྱེད་ར་ཡི་བླ། །གནག་མཚོན་འཁོར་བསྟེན་རྟ་ཕྱུགས་འོ། །ལིང་འདྲི་དགྲ་མནན་ཡུགས་སྐྲ་འཁྲུ། །ཟོར་འཕེན་འདྲེ་བསྐྲད་ར་ཕྱུགས་ལེན། །རང་འཐག་སྐྱེད་འཛུགས་ས་གཞི་འཚོང་། །སྲི་མནན་རྐུན་མ་སྔགས་བཟླ་དང་། །རིགས་ངན་ཤིད་བྱེད་དབང་དྲག་ལས། །ཡུགས་ས་ཕོ་མོ་གསོ་བ་དང་། །ལིངས་བྱེད་བྲེས་འཆོས་གཏད་སེལ་འཛུགས། །རོ་བསྲེག་དབང་བསྐུར་མིང་འདོགས་བཟང་། །ལྷ་གསོལ་མཁར་ལས་ས་བོན་གདབ། །ལྷ་ཁང་རྟེན་བཞེངས་རྒྱལ་སར་བསྐོ། །དགེ་བཤེས་ཆེ་འདོན་ཆོ་ག་དང་། །དྲ་གྱོན་མོ་རྩིས་བང་མཛོད་བརྩིག །ཕྱིར་འཇལ་དུར་འདེབས་བཟློག་རིམ་བྱ། །ཞལ་ཆེ་དམག་དྲངས་ཁ་དབྱེ། །ར་འཇལ་ཚོང་བྱེད་གཉེན་སྦྱོར་ལམ། །མི་ཤི་གསུམ་ཟློས་མོད་ལ་སྦ། །ཞི་རྒྱས་ལས་དང་རབ་གནས་བྱ། །གཡང་བསླན་སྐྱེ་ཤིང་འཛུགས་པ་དང་། །སྨན་སྦྱོར་གཏར་སྲེག་སྦྱིན་སྲེག་གཏད། །རྒྱལ་མཚན་བ་དན་འཕྱར་འཛུགས་དང་། །དོན་གཉེར་ཚེ་ནོར་སྒྲུབ་པ་སོགས། །དག་ལས་དཀར་ཕྱོགས་བྱ་བ་འཛེམ། །བག་མ་གཏོང་ལེན་བུ་སྐྱེས་ངན། །

Good and avoid lists read on the scan, 2026-10-07.

Three stars, shaped like the female organ; clan Varga (?), deity Yama;
food sesame and rice; fire; a *dme* mansion, a destroyer, the *bla* of
goats.

- **Good:** cattle (?) and weapons, taking attendants, horses and livestock
  (the last syllable, «འོ», is unclear in both the 1996 print and WBZ
  img. 1066),
  drawing the effigy (*ling*), suppressing enemies, washing the hair,
  hurling the *zor*, expelling demons, acquiring goats, setting up a
  hand-mill (?), planting, selling land, suppressing *sri*, (catching)
  thieves, reciting mantras, funerals of the low-born, power and fierce
  rites, supporting widows and widowers, hunting, setting up or removing a
  *gtad*, cremation, empowerment, naming.
- **Avoid:** offerings to the gods, building, sowing, temples, making
  images, enthronement, great rites for a spiritual friend, new clothes,
  divination, building storehouses, paying back, burial, averting rites,
  judging cases, leading an army, opening (storehouses), paying in goats,
  trade, marriage alliances, journeys; pacifying and increasing rites,
  consecration, prosperity rites, planting trees, preparing medicine,
  bloodletting and moxibustion, fire offerings, *gtad*, raising banners,
  lawsuits, rites for life and wealth, all white (virtuous) work; giving or
  taking a bride.
- **Born on it:** a boy is bad. Whoever is born is steady, healthy and
  truthful but of little compassion, lives thirty-six or at most
  ninety-five years, skilled in crafts and hard-working, and for want of
  compassion is reborn in hell.
- If someone dies, three deaths follow: bury at once. Illness from going to
  a house of mourning, cured by a ransom on a dough ox with white mustard
  in mouth and ears; portents head and eye disease, phlegm disease, grain
  scarce, towns burnt, bad for China; rain falls again; minor star *phung
  rgyab can*, for subduing enemies and disputes over land, middling for
  funeral beer and gardens; if obstructed, raise ox and sheep skulls in
  one's own direction.

## 3. smin drug (Kṛttikā)

p. 314 (img. 322–323):

> སྨིན་དྲུག་སྐར་དྲུག་སྤུ་གྲིའི་དབྱིབས། །རྗེ་རིགས་མེ་ལྷ་ཟས་ནི་ཞོ། །མེ་ཁམས་ཟློས་སྐར་བུད་མེད་བླ། །བཏང་སྙོམས་ཅན་ལ་དགེ་ཆོས་འགྲུབ། །ཆོས་སློབ་ལྷ་ཁང་མཆོད་རྟེན་གསོ། །རབ་གནས་ལྷ་གཡང་མངའ་གསོལ་བྱེད། །སྦྱིན་སྲེག་ཡོན་འབུལ་སྨན་སྦྱོར་རྩིས། །རྒྱན་འདོགས་སྐྱིད་སྟོན་རྒྱལ་སར་བསྐོ། །ཐབ་འཆའ་ཚུར་ལེན་སྦྲ་ཁྱིམ་འབུབས། །གཉེན་ཕྲད་རྟ་རྒྱུག་སྒ་རྒྱག་པ། །ལྟད་མོ་སྐྲ་འཁྲུ་བཙོན་བཟུང་བ། །རིན་ཅེན་བཟོ་དང་བུད་མེད་བསྟེན། །ཆུ་རགས་དཀར་ཕྱོགས་བྱ་བ་བཟང་། །མཁར་ལས་ཕར་འཇལ་ལམ་ཞུགས་ཤིད། །རྒྱལ་མཚན་བ་དན་བང་མཛོད་དབྱེ། །རྫིང་ཡུར་ཞིང་འདེབས་ས་ཁང་དབྱེ། །དྲ་གྱོད་དམག་ཇག་དྲག་ལས་སྐྱེས། །གཤིན་ལས་ཤིད་སྟོན་སྲི་གནོན་དང་། །བག་མ་ཁྱོར་ངན་སྐྱེད་ཁ་འདྲེན། །བུ་འབོགས་མེ་ཏོག་ཤིང་འཛུགས་འཛེམ། །བུ་སྐྱེས་སྙན་གྲགས་ལོངས་སྤྱོད་འཕེལ། །

Good and avoid lists read on the scan, 2026-10-07.

Six stars, shaped like a razor; clan the royal caste, deity Agni; food
curd; fire; a "repeating" mansion (*zlos skar*), the *bla* of women; a
neutral one, on which virtuous dharma succeeds.

- **Good:** studying the dharma, restoring temples and stupas,
  consecration, prosperity rites, enthronement, fire offerings, giving
  offerings, preparing medicine, astrology, putting on ornaments, feasts of
  joy, appointing to office, setting up a hearth, taking in, pitching a
  tent, meeting relatives, horse races, saddling, shows, washing the hair,
  seizing prisoners, jewellery work, keeping women, dams, all white work.
- **Avoid:** building, paying out, setting out, funerals, raising
  banners, opening storehouses, ponds and canals, planting fields, opening
  a cellar, new clothes (the 1996 print sets «དྲ་གྱོད», *dra
  gyod*, as if new clothes and disputes; the Zhol print, WBZ img. 1067,
  line 2, has «དྲ་གྱོན», new clothes, as every other verse), war and raids, fierce rites, gifts,
  funeral rites, funeral feasts, suppressing *sri*; a bride is bad for her
  husband; opening a garden (?), giving a child away, planting flowers and
  trees.
- **Born on it:** a boy gains fame and wealth, learned and brave, bad in
  youth, good in age and long-lived; a girl is bad.
- Illness from impurity; ransom on a dough goat with curd; if someone dies,
  six deaths follow: bury at once; portents drought, head and eye disease,
  towns burnt; rain comes late; minor star *mtheb chung zur phud can*, all
  good at sunrise; if obstructed, show a monkey's skull from one's own
  direction.

## 4. snar ma (Rohiṇī)

p. 315 (img. 323):

> སྣར་མ་སྐར་ལྔ་ཤིང་རྟའི་དབྱིབས། །རུས་ནི་གོའུ་ཏ་མ་ལ། །ས་ཁམས་གཡང་གི་སྐར་མ་སྟེ། །ཟས་ནི་ཞོ་སྦྲང་རབ་ཏུ་བརྟན། །ཡུལ་མཁར་གནས་གཞི་བཟུང་རྒྱལ་ས། །རབ་བྱུང་འཆད་ཉན་རབ་གནས་བྱ། །སློབ་གཉེར་ལྷ་གསོལ་ཚེ་གཡང་སྐྱོབ། །ཕྱུགས་སྤྱར་སྨན་སྦྱོར་ནོར་ཕྱུགས་ལེན། །འདུན་གྲོས་མཁར་བརྩིག་བྲེས་འཆོས་པ། །ཉོ་ཚོང་གཏར་སྲེག་དགྲ་ལ་རྒོལ། །ས་བོན་རྟ་འདུལ་སྒ་རྒྱག་རྒྱུག །བུ་ལོན་སྙེག་དང་ཕོ་མོ་བཙས། །ཀུན་ར་བརྩིག་དང་ཞི་རྒྱས་ལས། །དྲ་གྱོན་སྐྲ་འཁྲུ་བང་མཛོད་བཅའ། །དོན་གཉེར་ཤར་བཟང་ས་ཁ་དབྱེ། །སྦྲ་ཁྱིམ་ཕྱ་ཐེར་འཚེམ་འབུབས་བཟང་། །ལམ་ཞུགས་སྟོན་མོ་ཕྱུགས་ནོར་འཇལ། །དམག་ཇག་འཕོ་སྐྱས་རུས་བུ་སྦོ། །མི་ཤི་ནོར་ངན་ཕྱུགས་གཡང་སྐལ། །གཉེན་བྱེད་བཟློག་པ་སྐྲ་སེན་འབྲེག །བུ་སྲིང་རྫོང་དང་གཤིན་ལས་འཛེམ། །བག་མ་གཏོང་ལེན་གཉིས་ཀ་ངན། །བུ་སྐྱེས་ཚེ་རིང་གཟུགས་བཟང་མཛེས། །

Good and avoid lists read on the scan, 2026-10-07.

Five stars, shaped like a cart; clan Gautama; earth; a mansion of
prosperity (*g.yang*); food curd and honey; very stable.

- **Good:** taking land, a fort or a dwelling, enthronement, ordination,
  teaching, consecration, study, offerings to the gods, protecting life
  and prosperity, (cross-)breeding livestock, preparing medicine, taking in
  wealth and livestock, councils, building forts, repairing measures (?),
  buying and trading, bloodletting and moxibustion, attacking enemies,
  sowing, breaking horses, saddling and racing, claiming debts, a birth of
  either sex, building a pleasure garden (*kun ra*), pacifying and
  increasing rites, new clothes, washing the hair, building storehouses,
  lawsuits (good in the east), breaking ground, sewing and pitching tents
  and felt.
- **Avoid:** setting out, feasts, paying out livestock and goods, war and
  raids, moving house, gifts, burying bones, a death (bad for wealth),
  sharing out the livestock's prosperity, marriage, averting rites,
  cutting hair and nails, sending off a son or sister, funeral rites;
  giving and taking a bride are both bad.
- **Born on it:** a boy is long-lived, handsome, rich and religious, without
  guile, brave, prosperous, victorious over enemies.
- Illness from the hearth (*thab gzhob*), ransom on a dough dog with
  butter; a death of either sex is bad for wealth; portents a peaceful
  realm, good harvests, strife calmed; late rain, no grain; minor star *lha
  mo g.yang skyong byed*, outward empowerment and prosperity rites good; if
  obstructed, show a dog's skull above oneself.

## 5. mgo (Mṛgaśiras)

p. 315 (img. 323–324):

> མགོ་ནི་སྐར་གསུམ་རི་དྭགས་མགོ། །རུས་ནི་བྷ་ར་དྷྭ་ཛ་དང་། །ལྷ་ནི་ཟླ་བ་ཟས་སུ་ནི། །རི་དྭགས་ཤ་ཟ་རླུང་གི་ཁམས། །མྱུར་བཟང་དམེ་སྐར་བཙད་པོའི་བླ། །ཆོས་འཆད་རབ་བྱུང་རབ་གནས་གཉེན། །ཁྱིམ་གསར་སྒོ་འཛུགས་ལྷ་གསོལ་གཡང་། །གསོན་ཆོས་རྒྱན་སྤྲོད་ཤིང་བཟོ་ཁྲོམ། །ཞིང་ལས་གསོན་ལས་རྒྱལ་སར་བསྐོ། །སྨན་སྦྱོར་བློ་གྲོས་གཞི་འདིང་བ། །མཁར་ལས་ལྷ་ཁང་རྟེན་བཞེངས་ཁྲུས། །དམག་ཇག་དགྲ་འདུལ་མོ་རྩིས་བྱ། །དྲག་ཅིང་རྩུབ་པའི་ལས་རྣམས་དང་། །ཐབ་འཆའ་འདུ་ལོང་རྒྱལ་མཚན་འཛུགས། །ནོར་བླང་དྲག་ལས་འདྲེ་འདུལ་བ། །བཟློག་པ་སྲི་མནན་ས་ཁ་དབྱེ། །སྐྲ་སེན་འབྲེག་དང་རྟ་རྒྱུག་བཟོ། །རྟ་བོང་དྲེའུ་སྒ་རྒྱག་བཅོས། །སྟོན་མོ་ཚོང་དང་མིང་འདོགས་ལྟད། །ཕྱུགས་འདུལ་རྩེད་མོ་འདོད་དོན་སྒྲུབ། །དྲག་རྒྱས་བཟང་ལ་དབང་ཞི་རུང་། །དགེ་ལེགས་མེ་ཏོག་ཤིང་འཛུགས་བཟང་། །མི་ནོར་ཕྱིར་གཏོང་ལམ་ཞུགས་ཤིང་། །གཤིན་ལས་དུར་འདེབས་ཁ་སྨྲས་དང་། །འཇམ་ལས་ཞལ་ཆེ་འཕོ་སྐྱས་བྱེད། །དྲ་གྱོན་ཤི་བ་མགོ་འཆི་ངན། །བག་མ་ཚུར་བཟང་ཕར་གཏོང་ངན། །

Good and avoid lists read on the scan, 2026-10-07.

Three stars, like a deer's head; clan Bharadvāja, deity the Moon; food
venison; wind; quick and good, a *dme* mansion, the *bla* of a *btsad po*.

- **Good:** teaching the dharma, ordination, consecration, marriage
  alliances, a new house, setting up a door, offerings to the gods,
  prosperity rites, dharma for the living, giving ornaments, carpentry,
  markets, field work, works for the living, enthronement, preparing
  medicine, counsel, laying foundations, building, temples, making images,
  bathing, war and raids, subduing enemies, divination, fierce and harsh
  work, setting up a hearth, gatherings, raising victory banners, taking
  wealth, fierce rites, subduing demons, averting rites, suppressing *sri*,
  breaking ground, cutting hair and nails, racehorses, saddling and
  treating horses, donkeys and mules, feasts, trade, naming, shows, taming
  livestock, games, gaining what one wishes; good for fierce and increasing
  rites, acceptable for power and pacifying rites; virtuous acts, planting
  flowers and trees.
- **Avoid:** sending away people or wealth, setting out, funerals, funeral
  rites, burial, quarrels, gentle work, judging cases, moving house, gifts,
  new clothes; a death means the head of the house dies; a bride: taking
  one in is good, sending one away bad.
- **Born on it:** a boy lives 28, 62 or 90 years, religious, keeping his
  vows, truthful, brave, clear-minded and rich; a girl is called
  sharp-tongued.
- Illness from enmity and mourning, ransom on a dough dog with venison;
  portents strife and great winds, many epidemics, bad for mantrins,
  Bonpos and India; heavy rain. Between mgo and lag lies a minor star
  (most tantras place it there); minor star *ma nam*, comes in the
  morning, a death on it is bad; if obstructed, raise a fox's skull above
  oneself.

## 6. lag (Ārdrā)

p. 316 (img. 324):

> ལག་ནི་སྐར་གཅིག་ཐིག་ལེའི་དབྱིབས། །རིགས་ནི་དམངས་པོ་ཟས་ཁྲག་ཆུ། །ལྷ་ནི་གཏུམ་དྲག་བཤན་པའི་བླ། །ཆུ་ཁམས་མགྲོན་སྐར་འབྲས་བུ་ངན། །གཡང་ལོན་བཟོ་ཡིག་ཞལ་ཆེ་གཅོད། །རབ་བྱུང་རབ་གནས་དགེ་ལས་སྒྲུབ། །རྒྱན་སྤྲོད་སྐྲ་འཁྲུ་ས་བོན་གདབ། །རོ་ལམ་བཏོད་པ་འཇལ་ལེན་བྱེད། །དམག་ཇག་དྲག་ལས་གསོད་གཅོད་རྒྱུ། །འཐབ་རྩོད་དགྲ་ཡི་སྲི་སྒྲུབ་པ། །ཚེ་འགུགས་བོན་ཆོག་སྲི་མནན་བྱ། །མོ་འདེབས་བཟློག་པ་རྫིང་ཡུར་འཆོས། །མཐོ་ལ་ཞུ་འབུལ་ཐུག་འགྱེད་པ། །མེ་ཏོག་སྐྱེ་ཤིང་འཛུགས་པ་བཟང་། །ལྷ་གསོལ་བ་དང་གཤིན་ལས་རིགས། །གཉེན་དང་ཕྱུགས་འཇལ་དོན་གཉེར་སྐྱས། །དྲ་གྱོན་ལམ་ཞུགས་རྒྱལ་ས་བསྣུམས། །ལྷ་ཁང་རྟེན་བཞེངས་བ་དན་འཛུགས། །གཏར་ཁ་མེ་བཙའ་སྦྲ་ཕྱར་འཚེམ། །ཐབ་འཆའ་ཆུ་རགས་རྒྱག་པ་དང་། །ཞི་རྒྱས་དབང་དང་བཀྲ་ཤིས་ལས། །སྦྱིན་སྲེག་བྱ་སོགས་ཕལ་ཆེར་ངན། །བག་མ་བྱས་ན་ཁྱོ་ལ་ངན། །

Good and avoid lists read on the scan, 2026-10-07.

One star, shaped like a drop; caste the commoners, food blood and water;
deity the Fierce One (Rudra), the *bla* of butchers; water; a guest mansion
(*mgron skar*), bad in its results.

- **Good:** prosperity rites, crafts and writing, judging cases,
  ordination, consecration, virtuous acts, giving ornaments, washing the
  hair, sowing, clearing a corpse road (?), measuring out and taking in,
  war and raids, fierce rites, killing and cutting, fights and disputes,
  *sri* rites against enemies, summoning life, Bon rites, suppressing
  *sri*, divination, averting rites, repairing ponds and canals,
  petitioning the great, sending a *thug* (?), planting flowers and trees.
- **Avoid:** offerings to the gods, funeral rites, marriage alliances,
  measuring livestock, lawsuits, gifts, new clothes, setting out,
  enthronement, reconciling, temples, making images, raising banners,
  bloodletting and moxibustion, sewing tents and felt, setting up a hearth,
  building dams, pacifying, increasing, power and auspicious rites, fire
  offerings: mostly bad; a bride is bad for her husband.
- **Born on it:** lives 35 or 65 years by robbery; fierce, unwavering,
  clumsy, poor, proud, fond of evil; a boy is very strong, a girl quick at
  wool work.
- Illness from going among crowds, in the hands, ransom on a dough dog with
  blood in its mouth; portents rain, easy births, no disease, medicines and
  game increase; bad for the harvest: the rain is late. Minor star *ze'u*,
  comes at noon, everything good; if obstructed, raise a cow's skull above
  oneself.

## 7. nabs so (Punarvasu)

p. 316 (img. 324–325):

> ནབས་སོ་སྐར་གཉིས་ཁྲི་རྐང་དབྱིབས། །ཉི་མའི་ལྷ་དེ་རུས་དབང་བྱེད། །ཟས་འཁུར་རླུང་ཁམས་གཡང་སྐར་ཏེ། །མྱུར་ཞིང་འགྱུར་བ་ལུག་གི་བླ། །རབ་བྱུང་རབ་གནས་ཆོས་འཆད་དབང་། །ལྷ་གསོལ་གཡང་འགུགས་སྦྱིན་སྲེག་བྱ། །སྒྲུབ་མཆོད་སློབ་གཉེར་དཀྱིལ་འཁོར་བྲི། །ས་བོན་ཐོག་འབུབས་རྩིག་རྨང་འདིང་། །སྐྱེད་འཛུགས་ཐུག་འགྱེད་བུ་ལོན་འཇལ། །ཁ་སྨྲས་ཚུར་འགུགས་འཐབ་མོ་ཤིང་། །དོན་གཉེར་རྒྱན་སྤྲོད་རྒྱལ་སར་བསྐོ། །སྨན་སྦྱོར་དྲ་གྱོན་མོ་རྩིས་བྱ། །མཁར་ལས་ཁྱིམ་འཛིན་གྲོང་ཁྱེར་བརྩིག །ལྷ་ཁང་རྟེན་བཞེངས་བཟློག་པ་ཟོར། །འཁོར་བསྟེན་ཕྲ་མོའི་ལས་རིགས་བརྩམ། །ཕྱུགས་འཇལ་གཤིན་ལས་བང་མཛོད་བཅའ། །ཞིང་འདེབས་ས་དབྱེ་སྦྲ་ཕྱར་འཚེམ། །ལམ་ཞུགས་ཚོང་དང་ལུག་ནང་ལེན། །རྟ་བོང་སྒ་རྒྱག་གཏར་སྲེག་བཅོས། །ཞུ་བ་གསོལ་དང་མིང་འདོགས་པ། །དབང་དང་རྒྱས་པ་ཞི་བའི་ལས། །སྟོན་མོ་གསོན་པོའི་བྱ་བ་བཟང་། །བུ་འབོགས་རོ་འདོན་འབྲིང་དུ་བྱེད། །དམག་ཇག་ཕར་རྒོལ་ལུག་འཇལ་དྲག །ནོར་གཏོང་ས་འཛིན་ལྷ་ཁང་བཤིག །ཐབ་ཁ་འཛུགས་དང་སྡིག་ལས་འཛེམ། །བག་མ་ཕར་ངན་ཚུར་ལེན་བཟང་། །

Good and avoid lists read on the scan, 2026-10-07.

Two stars, shaped like the leg of a bed; deity "the god of the Sun", clan
Dbang byed; food pastry (*'khur*); wind; a mansion of prosperity, quick and
changing, the *bla* of sheep.

- **Good:** ordination, consecration, teaching, empowerment, offerings to
  the gods, summoning prosperity, fire offerings, practice and offering,
  study, drawing maṇḍalas, sowing, roofing, laying foundations, planting
  gardens, sending a *thug* (?), paying debts, quarrels (?), calling back,
  fights, lawsuits, giving ornaments, enthronement, preparing medicine, new
  clothes, divination, building, taking a house, building towns, temples,
  making images, averting rites, the *zor*, taking attendants, beginning
  small tasks, measuring livestock, funeral rites, building storehouses,
  planting fields, breaking ground, sewing tents, setting out, trade,
  taking sheep in, saddling and treating horses and donkeys, bloodletting
  and moxibustion, petitioning, naming, power, increasing and pacifying
  rites, feasts, works for the living. Middling: giving a child away,
  carrying out a corpse.
- **Avoid:** war and raids, attacking (?), paying out sheep, fierce rites,
  giving wealth, taking land, pulling down temples, setting up a hearth,
  sinful acts; a bride: sending one away is bad, taking one in good.
- **Born on it:** lives 50 to 60 years, has fields and wealth, or is
  religious and law-abiding, dull by nature, steady, healthy and content.
- Illness from a guest's misfortune, ransom on a dough pig with a rice
  *gtor ma*; a death: bury at once; portents strife, bad for trade; rain
  soon falls again; minor star *'od chung rgyal mo can*, judging cases at
  noon is bad; if obstructed, raise a skull above oneself.

## 8. rgyal (Puṣya)

p. 317 (img. 325):

> རྒྱལ་ནི་སྐར་གསུམ་རིལ་བའི་དབྱིབས། །རུས་ནི་རྩྭ་ཤིང་གྱོན་པའི་རིགས། །ཕུར་བུ་ལྷར་འཛིན་སྦྲང་ཡོས་ཟས། །མེ་ཁམས་འགྲུབ་སྐར་ལུག་གི་བླ། །མི་བརྟན་འགྱུར་ལ་རབ་བྱུང་དབང་། །རབ་གནས་སྒྲུབ་མཆོད་ཡུལ་གསར་འཛིན། །ཆོས་འཆད་མངའ་གསོལ་ཡོན་འབུལ་བ། །རྒྱན་སྤྲོད་དྲ་གྱོན་རྒྱལ་སར་བསྐོ། །ལྷ་གཡང་བཟློག་པ་དཀྱིལ་འཁོར་འདྲི། །སྨན་སྦྱོར་མོ་རྩིས་འཁོར་བསྟེན་གཏར། །ལམ་ཞུགས་ནོར་ཕྱུགས་ཚུར་ལེན་པ། །ལྷ་ཁང་རྟེན་བཞེངས་རྟ་གླང་འདུལ། །སྒ་རྒྱག་ཐབ་བཅའ་མཛོད་འཛིན་ལྟད། །ཞི་རྒྱས་དབང་དང་བཀྲ་ཤིས་ལས། །མྱུར་སྒྲུབ་གོང་མར་ཞུ་ནོར་འབུལ། །མིང་འདོགས་ཆུ་རགས་རྒྱལ་མཚན་འཛུགས། །གསོན་གཤིན་དོན་གཉེར་ཤིང་འཛུགས་བཟང་། །ཚོང་དང་ཁ་མཆུ་ཕར་འཇལ་སྐྱས། །རྔོན་རྐུ་ཞིང་འདེབས་མཁར་གྱི་ལས། །གཡང་ཅན་མི་ཕྱུགས་ལུག་ཕྱིར་གཏོང་། །སྟོན་མོ་སྲི་མནན་སྐྲ་འཁྲུ་འཛེམ། །ཤི་བ་ཟློས་མེད་བཟང་བ་ཡིན། །བག་མ་ཕར་ངན་ཚུར་ལེན་བཟང་། །

Good and avoid lists read on the scan, 2026-10-07.

Three stars, shaped like a pellet; clan "those who wear grass and wood",
deity Jupiter; food honey and molasses; fire; an accomplishing mansion
(*'grub skar*), the *bla* of sheep; unstable and changing.

- **Good:** ordination, empowerment, consecration, practice and offering,
  taking a new land, teaching, enthronement, giving offerings, giving
  ornaments, new clothes, appointing to office, prosperity rites, averting
  rites, drawing maṇḍalas, preparing medicine, divination, taking
  attendants, bloodletting, setting out, taking in wealth and livestock,
  temples, making images, breaking horses and oxen, saddling, setting up a
  hearth, taking over a treasury, shows, pacifying, increasing, power and
  auspicious rites, quick accomplishment, presenting wealth to the great,
  naming, building dams, raising victory banners, works for the living and
  the dead, lawsuits, planting trees.
- **Avoid:** trade, disputes, paying out, gifts, hunting and theft,
  planting fields, building, sending away people, livestock or sheep that
  carry the house's prosperity, feasts, suppressing *sri*, washing the
  hair. A death does not repeat: good. A bride: sending one away is bad,
  taking one in good.
- **Born on it:** a boy of great prosperity, lives 80 years, religious,
  generous, hard-working, handsome, eloquent, skilled with his hands,
  restless, learned, rich and fortunate.
- Illness from boils (?) and water work, in the lower body, ransom on a
  dough goat with a turtle; portents phlegm disease; rain brings drought;
  minor star *'grul bo*, comes at midnight, everything bad on it; if
  obstructed, raise a goat's head above oneself.

## 9. skag (Āśleṣā)

p. 317–318 (img. 325–326; the first three lines, beside the woodcut at
the foot of img. 325, are MITRA's reading: Yigdzin-1 dropped them):

> སྐག་ནི་སྐར་དྲུག་གདེངས་ཀའི་དབྱིབས། །རིགས་ནི་སྨན་རྒྱུད་ལྷ་ལྟོ་འགྲོ། །ཁ་ཟས་སྦྲུལ་ཤ་མགྲོན་སྐར་ཏེ། །ཆུ་ཁམས་འབྲས་ངན་ཁྱི་གཅན་བླ༑ །དམག་ཇག་གསོད་གཅོད་གྲོང་ཁྱེར་འཇོམས། །ཞུ་གསོལ་སྲི་མནན་གསེབ་གཏོང་ཚོང་། །གཡོ་བསླུས་སྒབ་ཆོག་བུ་ལོན་འཇལ། །རྫིང་ཡུར་ཆུ་རགས་སྐྱེ་ཤིང་འཛུགས། །མི་ཤི་གཤིན་ཆོས་གྲོས་ཐག་གཅོད། །དྲག་ལས་ཆུ་བོ་བསྒྱུར་རྣམས་བཟང་། །ལྷ་གསོལ་རབ་གནས་སྨན་སྦྱོར་སྐྱས། །དྲ་གྱོན་རྒྱན་སྤྲོད་རྒྱལ་སར་བསྐོ། །ལྷ་ཁང་རྟེན་བཞེངས་མོ་རྩིས་བྱ། །མཁར་ལས་དོན་གཉེར་ཁྱིམ་གསར་འཛིན། །ལམ་ཞུགས་ཕྱུགས་འདུལ་འབྲེལ་སྙེག་པ། །ཐབ་བཅའ་སྦྲ་ཕྱར་གྲོང་ཁྱེར་བརྩིག །ཞི་རྒྱས་དབང་གི་ལས་རྣམས་དང་། །བཀྲ་ཤིས་དགེ་ལེགས་དགེ་བའི་ལས། །རྟ་བོང་སྒ་བཅོས་ར་ཕྱིར་གཏོང་། །རྐུན་མ་ལྟད་མོ་ཐགས་ཁྲི་འཛུགས། །རབ་བྱུང་འཆད་ཉན་དཀྱིལ་འཁོར་འདྲི། །སློབ་གཉེར་བྱ་དང་དབང་བསྐུར་བ། །དོན་གཉེར་ཚེ་ནོར་སྒྲུབ་པ་དང་། །ཁ་མཆུ་དགེ་ཆོས་བརྟན་ལས་སྤང་། །ཁྲི་གདན་ཕྱི་ལ་བཏང་ན་འཆི། །རོ་འདོན་ངན་ལ་ཤིད་སྟོན་རུང་། །བག་མ་གཏོང་ལེན་གཉིས་ཀ་འཛེམ། །

Good and avoid lists read on the scan, 2026-10-07.

Six stars, shaped like a serpent's hood; caste "of the healers' line",
deity the serpents; food snake meat; a guest mansion (*mgron skar*); water;
bad in its results; the *bla* of dogs and beasts of prey.

- **Good:** war and raids, killing and cutting, destroying towns,
  petitioning, suppressing *sri*, sending spies (?), trade, deceit and
  cheating, the *sgab* rite (?), paying debts, ponds, canals and dams,
  planting trees, a death, funeral rites, settling deliberations, fierce
  rites, turning a river.
- **Avoid:** offerings to the gods, consecration, preparing medicine,
  gifts, new clothes, giving ornaments, enthronement, temples, making
  images, divination, building, lawsuits, a new house, setting out, taming
  livestock, pressing claims, setting up a hearth, raising tents, building
  towns, pacifying, increasing and power rites, auspicious and virtuous
  acts, saddling and treating horses and donkeys, sending goats away,
  thieves (?), shows, setting up a loom, ordination, teaching, drawing
  maṇḍalas, study, empowerment, rites for life and wealth, disputes,
  virtuous dharma, lasting work. A throne or seat sent out means death;
  carrying out a corpse is bad, a funeral feast acceptable; giving and
  taking a bride are both to be avoided.
- **Born on it:** a boy lives 53 years, dull, has few daughters and a short
  time with a beautiful wife; angry, fond of sin and lust, greedy, slow to
  give, busy with bad deeds.
- Illness from travel and bad food, ransom on a dough pig with a frog, in
  the hearth; portents bad for Hor and Tibet; at an eclipse rain, easy
  births, no disease, medicine and mantra increase; the rain is late. Minor
  star *she mang 'dab skar*, everything bad at midnight; if obstructed,
  raise a mouse's skull above oneself.

## 10. mchu (Maghā)

p. 318 (img. 326):

> མཆུ་ནི་སྐར་དྲུག་ཆུ་བོ་འདྲ། །ལྷ་ནི་སྤེན་པ་སེར་སྐྱའི་རིགས། །ཟས་ནི་ཏིལ་ཐུག་མེ་ཡི་ཁམས། །བརྟན་པའི་དོན་གྲུབ་གཏུམ་དྲག་ཅན། །དགྲ་སྐར་ཕྱུགས་དང་ཇག་པའི་བླ། །རབ་བྱུང་དགེ་སྒྲུབ་འཆད་ཉན་དབང་། །སློབ་གཉེར་སྒྲུབ་མཆོད་ལྷ་རྟེན་བཞེངས། །མོ་རྩིས་ཡིག་སློབ་དཀྱིལ་འཁོར་འདྲི། །མཐུ་བཟློག་སྲི་མནན་སྦྱིན་སྲེག་ཟོར། །ཕར་རྒོལ་དམག་ཇག་དབང་དྲག་ལས། །གཏར་བསྲེག་རྒྱས་ལས་སྨྲ་ལབ་འཛུགས། །ཁྱིམ་གསར་ས་བོན་འཁོར་གཡོག་བསྟེན། །ས་འཛིན་རྒྱན་སྤྲོད་བང་མཛོད་དབྱེ། །ནོར་ལེན་གཡང་ལོན་རྟ་ཕྱུགས་ཉོ། །བཟོ་དང་ཞུ་བ་སྐྲ་སེན་འབྲེག །དཀར་ཕྱོགས་ལས་འགོ་ཁྱི་བསྟེན་བཟང་། །སྐྱས་བྱ་རབ་གནས་རྒྱལ་སར་བསྐོ། །མཁར་ལས་ལམ་ཞུགས་དོན་གཉེར་བ། །ལྷ་གསོལ་སྟོན་མོ་ཐབ་བཅའ་བ། །སྐྲ་འཁྲུ་བསྡུམ་བྱ་དྲ་གྱོན་ཤིད། །ཆུ་རགས་རྫིང་ཁྲོན་ས་ཁ་དབྱེ། །རྟ་བོང་སྒ་རྒྱག་འདུལ་བཅོས་བྱེད། །དགེ་བཤེས་ཆེ་འདོན་བྱ་བ་དང་། །ནོར་རྫས་ཕྱིར་གཏོང་སྦྲ་ཕྱར་འཚེམ། །བོན་ཆོག་མེ་ཏོག་ཤིང་འཛུགས་པ། །བ་དན་རྒྱལ་མཚན་འཛུགས་སོགས་འཛེམ། །བག་མ་གཏོང་ལེན་བྱར་མི་རུང་། །

Good and avoid lists read on the scan, 2026-10-07.

Six stars, like a river; deity Saturn, clan Kapila (*ser skya*); food
sesame broth; fire; stable, accomplishing, fierce; an enemy mansion, the
*bla* of livestock and of robbers.

- **Good:** ordination, virtuous practice, teaching, empowerment, study,
  practice and offering, making images, divination, learning to write,
  drawing maṇḍalas, averting magic, suppressing *sri*, fire offerings, the
  *zor*, attacking, war and raids, power and fierce rites, bloodletting and
  moxibustion, increasing rites, beginning to speak (?), a new house,
  sowing, taking attendants, taking land, giving ornaments, opening
  storehouses, taking wealth, prosperity rites, buying horses and
  livestock, crafts, petitioning, cutting hair and nails, beginning white
  work, keeping dogs.
- **Avoid:** gifts, consecration, enthronement, building, setting out,
  lawsuits, offerings to the gods, feasts, setting up a hearth, washing the
  hair, reconciling, new clothes, funerals, dams, ponds and wells, breaking
  ground, saddling, breaking and treating horses and donkeys, great rites
  for a spiritual friend, sending away wealth and goods, sewing tents, Bon
  rites, planting flowers and trees, raising banners; a bride must be
  neither given nor taken.
- **Born on it:** who outlives nine years lives to 95; a boy is bad for
  others, a girl dies; fond of lies, with many attendants and wealth,
  skilled in crafts, disciplined, diligent in virtue.
- Illness from carving stone and rock, ransom on a dough pig with sesame;
  a death brings seven more, bad for the people of the land; an earthquake
  portends harm to servants; at an eclipse human disease and epidemics,
  phlegm disease, grain and wealth scarce, towns burnt; rain soon falls
  again; minor star *sri rta rkang gnyis*, bad for everything; if
  obstructed, show a snake's skull above oneself.

## 11. gre (Pūrvaphalgunī)

p. 319 (img. 327):

> གྲེ་ནི་སྐར་གཉིས་ལྷ་ཁྱབ་འཇུག །དབྱིབས་ནི་མི་རྐང་ལྟ་བུ་སྟེ། །རུས་གཞོན་རྩི་མོའི་ཁྱིམ་ཐབ་རྒྱུད། །ཟས་སུ་འབྲས་དང་ཏིལ་ཚིག་མ། །མེ་ཁམས་དམེ་སྐར་ཇག་པའི་བླ། །བརྟན་པའི་དོན་གྲུབ་སྐར་མ་སྟེ། །གཉེན་སྦྱོར་ནོར་བླང་ཚེ་གཡང་མདོས། །གཉེན་ཕྲད་ཚུར་འགུགས་བང་མཛོད་དབྱེ། །སྲུང་བསྟེན་གཞིས་ཚོང་ཕྱུགས་ནོར་ལེན། །ལྷ་གསོལ་དབང་བསྐུར་ས་འཛིན་མཁར། །དབང་ལས་རྒྱན་སྤྲོད་མོ་རྩིས་བྱ། །ལུག་འཇལ་ཐུག་འགྱེད་ལྷོ་ནུབ་བགྲོད། །བརྟན་ལས་ཐབ་བཅའ་ཆུ་རགས་རྒྱག །རབ་བྱུང་འཆད་ཉན་དཀྱིལ་འཁོར་འབྲི། །སྦྱིན་སྲེག་བྱ་བ་ལ་སོགས་བཟང་། །རབ་གནས་གཤིན་ལས་སྟོན་མོ་ཤིད། །དགྲ་འདུལ་དྲག་ལས་རྟ་ཉོ་ལམ། །བུ་ལོན་འཇལ་ཇག་སྨན་བཅོས་མཚོན། །གཏར་སྲེག་བོང་བུ་ནོར་རྫས་གཏོང་། །རྟ་བོང་སྒ་བཅོས་ས་ཁ་དབྱེ། །དྲ་གྱོན་སྐྲ་འཁྲུ་བང་མཛོད་བཅའ། །རྒྱལ་མཚན་བ་དན་འཛུགས་པ་དང་། །རྫིང་ཡུལ་ཕྱུགས་འདུལ་མིང་འདོགས་པ། །སྦྲ་ཕྱར་འཚེམ་དང་འབུབས་པའི་ལས། །བག་མ་གཏོང་ལེན་འཁོར་བསྟེན་ངན། །

Good and avoid lists read on the scan, 2026-10-07.

Two stars, deity Viṣṇu, shaped like a man's legs; clan of the *gzhon rtsi
mo* house (?); food rice and roasted sesame; fire; a *dme* mansion, the
*bla* of robbers; stable and accomplishing.

- **Good:** marriage alliances, taking wealth, *mdos* rites for life and
  prosperity, meeting relatives, calling back, opening storehouses, keeping
  guards, trading estates, taking livestock and wealth, offerings to the
  gods, empowerment, taking land, forts, power rites, giving ornaments,
  divination, paying in sheep, sending a *thug* (?), travelling south-west,
  lasting work, setting up a hearth, building dams, ordination, teaching,
  drawing maṇḍalas, fire offerings.
- **Avoid:** consecration, funeral rites, feasts, funerals, subduing
  enemies, fierce rites, buying horses, setting out, paying debts, raids,
  medicine, weapons, bloodletting and moxibustion, donkeys, giving away
  goods, saddling and treating horses and donkeys, breaking ground, new
  clothes, washing the hair, building storehouses, raising banners, ponds,
  taming livestock, naming, sewing and pitching tents; giving or taking a
  bride, taking attendants.
- **Born on it:** who outlives eight years lives about 55; of bad nature
  till death, guileful, lustful, very stingy, skilled in crafts, fond of
  clothes, ornaments and talk; a boy sharp-tongued.
- Illness, often, from felling trees or digging, ransom on a dough monkey
  with fruit, freed in a day; a death middling; portents bad for India and
  the east; at an eclipse head and eye disease; rain soon falls again;
  minor star *tsho gza' the'u rkang*, everything bad at noon; if
  obstructed, show a skull from one's own direction.

## 12. dbo (Uttaraphalgunī)

p. 319–320 (img. 327–328):

> དབོ་ནི་སྐར་གཉིས་ཁྲི་ཡི་དབྱིབས། །རུས་ནི་མ་ཧེ་ཁ་ཟས་ཁྲེ། །ལྷ་ནི་གཤིན་རྗེ་རླུང་གི་ཁམས། །རབ་ཏུ་བརྟན་པ་ལུག་གི་བླ། །དགྲ་སྐར་ཞེས་བྱ་འདི་ཉིད་ལ། །ལྷ་གཡང་ཡུལ་གསར་གནས་གཞི་བཟུང་། །ཚེ་སྒྲུབ་རབ་གནས་ཟོར་འཕེན་དང་། །འཆད་ཉན་ལྷ་ཁང་རྟེན་བཞེངས་བཟློག །སྐྲ་འཁྲུ་རྒྱན་སྤྲོད་རྒྱལ་སར་བསྐོ། །གཉེན་སྦྱོར་མོ་རྩིས་སྦྲ་ཕྱར་འཚེམ། །ཆུ་རགས་མཁར་ལས་ཐབ་ཁ་བཅའ། །སྒྱེད་འཛུགས་བུ་འབོགས་འཕོ་སྐྱས་བྱ། །སྟོན་མོ་མིང་འདོགས་རྒྱལ་མཚན་འཛུགས། །རྟ་བོང་སྒ་རྒྱག་བྲེས་བརྩིག་པ། །རྩུབ་ལས་གསོད་གཅོད་དབང་དྲག་ལས། །དགེ་ཞིང་བཀྲ་ཤིས་འཁོར་བསྟེན་པ། །བརྟག་ཅིང་བརྟན་པའི་ལས་ཀྱི་རིགས། །བག་མ་གཏོང་ལེན་ཞི་བའི་ལས། །ནོར་རྫས་ཕྱིར་གཏོང་འདུན་གྲོས་བྱ། །དོན་གཉེར་དྲ་གྱོན་སྦྲ་ཁྱིམ་འབུབས། །རྫིང་ཡུར་ཁྲོན་འདྲུ་དམག་དང་བསྡུམས། །རང་དོན་རྟ་ཕྱུགས་འདུལ་བ་བཟང་། །ལམ་ཞུགས་སྤྱིར་ངན་ལྷོ་བྱང་དགེ །ཞིང་ལས་ས་བོན་ས་ཁ་དབྱེ། །མེ་ཏོག་ཤིང་འཛུགས་བུ་སྲིང་རྫོང་། །འབྲི་གནག་ར་ལུག་ཕྱིར་གཏོང་དང་། །བཟོ་དང་སྐྲ་སེན་འབྲེག་སོགས་འཛེམ། །

Good and avoid lists read on the scan, 2026-10-07.

Two stars, shaped like a throne; clan Buffalo, food millet; deity Yama;
wind; very stable, the *bla* of sheep; called an enemy mansion.

- **Good:** prosperity rites, a new land, taking a dwelling, rites for long
  life, consecration, hurling the *zor*, teaching, temples, making images,
  averting rites, washing the hair, giving ornaments, enthronement,
  marriage alliances, divination, sewing tents, dams, building, setting up
  a hearth, planting gardens, giving a child away, moving house, gifts,
  feasts, naming, raising victory banners, saddling horses and donkeys,
  building, harsh work, killing and cutting, power and fierce rites,
  virtuous and auspicious acts, taking attendants, considered and lasting
  work, **giving and taking a bride**, pacifying rites, sending away goods,
  councils, lawsuits, new clothes, pitching tents, digging ponds, canals
  and wells, war and reconciliation, breaking horses and livestock for
  oneself. Setting out is bad in general, good to the south and north.
- **Avoid:** field work, sowing, breaking ground, planting flowers and
  trees, sending off a son or sister, sending away female yaks, cattle,
  goats and sheep, crafts, cutting hair and nails.
- **Born on it:** lives a hundred years, very rich, law-abiding, generous,
  truthful, fond of travel and of fasting vows, somewhat foolish; dies of
  poison.
- Illness from digging (?), ransom on a dough pig with greens; a death
  does not repeat, funeral rites middling; portents bad trade; at an
  eclipse strife, great winds, epidemics, bad for mantrins and Bonpos; rain
  later, heavy; minor star *mtsho gza' lham bu*, give no food away at dusk,
  everything bad around noon; if obstructed, raise a bear's skull above
  oneself.

## 13. ma bzhi (Hasta)

p. 320 (img. 328):

> མ་བཞི་སྐར་ལྔ་ལག་པའི་དབྱིབས། །རུས་ནི་འོད་སྲུངས་ལྷ་མེ་ལྷ། །བྱ་ཤ་སྲེ་དའི་ཁ་ཟས་ཟ། །ཤིང་གི་སྐར་མ་རླུང་གི་ཁམས། །མི་བརྟན་འགྱུར་བ་ལུག་གི་བླ། །མཆོད་པ་རབ་བྱུང་རབ་གནས་དབང་། །འཆད་ཉན་སྒྲུབ་མཆོད་དཀྱིལ་འཁོར་བྲི། །སློབ་གཉེར་ལྷ་གཡང་སྡོམ་པ་འབོགས། །མཁར་ལས་ཁྱིམ་གསར་རྒྱན་སྤྲོད་བཟོ། །དྲ་གྱོན་སྨན་སྦྱོར་རྒྱལ་སར་བསྐོ། །དུར་འདེབས་མོ་རྩིས་ས་བོན་གདབ། །ཞིང་ལས་ཐབ་བཅའ་གྲོང་ཁྱེར་བརྩིག །གཉེན་ཆོས་དགའ་སྟོན་འདུན་གྲོས་བྱ། །སྣ་ལེན་གཤིན་ལས་རྒོད་ཕྱུགས་འདུལ། །བསྡུམས་དང་ཤྭ་རགས་ས་ཁ་དབྱེ། །དགེ་ལེགས་འབུལ་བ་གནག་ལུག་འཛུགས། །རྟ་བོང་སྒ་རྒྱག་ཟེ་རྔོག་འབྲེག །དོན་གཉེར་བ་དང་མྱུར་སྒྲུབ་ལས། །ཕར་རྒོལ་དམག་ཇག་སྦྲ་ཕྱར་འཚེམ། །ཕྱུགས་བསྲེལ་མནན་པ་སྐྲ་སེན་འབྲེག །ཡུལ་བཟུང་ཡིག་བསླབ་དམིགས་བསལ་ཡིན། །དབང་དྲག་ཞི་རྒྱས་ལས་ཕལ་ཆེར། །འདི་ཉིད་ལ་ནི་བཟང་བར་བཤད། །ཕྱིར་འཇལ་སྦྲ་ཁྱིམ་རྐུན་མ་ལམ། །ཤིད་རོ་བསྲེག་འདོན་རྫིང་ཁྲོན་འདྲུ། །ལྷ་ཁང་རྟེན་བཞེངས་ལྟད་མོ་སྐྱས། །འབྲི་གནག་ལུག་གཏོང་ཕྱུགས་ནང་ལེན། །གཉའ་སྲོ་བ་དང་ཐོག་འབུབས་པ། །མེ་ཏོག་ཤིང་འཛུགས་ཚོང་བྱེད་སྤང་། །མི་ཤི་ཟློས་འབྱུང་བག་མ་ངན། །

Good and avoid lists read on the scan, 2026-10-07.

Five stars, shaped like a hand; clan Kāśyapa, deity Agni; food bird meat
and *sre da* (?); a wood mansion (?), wind; unstable and changing, the *bla*
of sheep.

- **Good:** offerings, ordination, consecration, empowerment, teaching,
  practice and offering, drawing maṇḍalas, study, prosperity rites, giving
  vows, building, a new house, giving ornaments, crafts, new clothes,
  preparing medicine, enthronement, burial, divination, sowing, field work,
  setting up a hearth, building towns, rites for relatives, feasts of joy,
  councils, receiving guests, funeral rites, breaking wild livestock,
  reconciling, *shwa rags*, breaking ground, virtuous acts, offering,
  setting up cattle and sheep, saddling horses and donkeys, trimming manes,
  lawsuits, quick accomplishment, attacking, war and raids, sewing tents,
  crossbreeding livestock (?), suppressing, cutting hair and nails; taking
  land and learning to write are special cases; most power, fierce,
  pacifying and increasing rites are called good on it.
- **Avoid:** paying out, (pitching) tents, thieves, setting out, funerals,
  cremation, carrying out a corpse, digging ponds and wells, temples,
  making images, shows, gifts, sending away female yaks, cattle and sheep,
  taking livestock in, yoking (?), roofing, planting flowers and trees,
  trade. A death repeats; a bride is bad.
- **Born on it:** a boy, bad for his mother, lives 80 years, handsome,
  eloquent, skilled with his hands, bold, strong, restless, rich, given to
  offerings and gifts, very guileful, or a thief and a drinker.
- Illness from fright, ransom on a dough buffalo with a lotus root;
  portents bad for scribes and potters; at an eclipse much strife, great
  winds, epidemics, bad for mantrins and Bonpos; the rain is late. Minor
  star *lu gu sna*, good for piercing (animals') noses and taking in goats;
  if obstructed, show a buffalo's skull from one's own direction.

## 14. nag pa (Citrā)

p. 320–321 (img. 328–329):

> ནག་པ་སྐར་གཅིག་པད་སྙིང་དབྱིབས། །རིགས་ནི་སཱ་ལའི་ཤིང་གྲོང་རྒྱུད། །ལྷ་ནི་ཉི་མ་ཟས་ཤིང་ཏོག །ཟློས་སྐར་རླུང་ཁམས་གནག་དང་རའི། །བླ་སྐར་མྱུར་ལ་བཟང་བ་དེ། །རབ་བྱུང་རྟེན་བཞེངས་དཀྱིལ་འཁོར་བྲི། །དགེ་ལེགས་ཆོས་ཉན་མྱུར་ལས་སྒྲུབ། །སྒྲུབ་མཆོད་དབང་བསྐུར་བཟློག་རིམ་བྱ། །ཚེ་གཡང་རྒྱན་སྤྲོད་སྐྲ་སེན་འབྲེག །འདུན་གྲོས་དྲ་གྱོན་ནོར་ཕྱུགས་ལེན། །སྨན་སྦྱོར་སྐྲ་འཁྲུ་རི་མོ་བཟོ། །ཞིང་ལས་ས་བོན་ཁང་གསར་བཟུང་། །གཡོས་བྱ་རྟ་རྒྱུག་མདོས་གཏོར་འཕང་། །ཤིད་དང་ཕྱུགས་འདུལ་སྦྲ་ཕྱར་འཚེམ། །ར་གནག་ཚུར་ལེན་དབང་རྒྱས་ལས། །གསོན་ལས་དཀར་ཕྱོགས་བྱ་བ་བཟང་། །རབ་གནས་ལྷ་གསོལ་རྒྱལ་སར་བསྐོ། །དགེ་བཤེས་ཆོ་འདོན་བྱ་བ་དང་། །ཞུ་བ་ར་ཕྱུགས་ཕར་འཇལ་ལམ། །དོན་གཉེར་མོ་རྩིས་སྦྲ་ཁྱིམ་འབུབས། །ངན་ཞིང་གཞིས་ཀ་ཡིན་ན་དགེ །དུར་འདེབས་དུར་ཁ་འབྱེད་དང་སྐྱས། །རྐུ་ཇག་རྫིང་ཡུར་བང་མཛོད་འབྱེད། །རྒྱལ་སྲིད་བྱ་བ་ཞུ་བསྙེན་བཀུར། །དགྲ་འཐབ་གཤིན་ལས་ཞལ་ཆེ་གཅོད། །ཞི་བ་དྲག་པོའི་ལས་ཕལ་ཆེར། །ཆུ་རགས་ལྟད་མོ་མིང་འདོགས་ངན། །བག་མ་བྱས་ན་བུ་མོ་འཆི། །

Good and avoid lists read on the scan, 2026-10-07.

One star, like the heart of a lotus; caste of the *sna la* tree village
line (?), deity the Sun; food fruit; a "repeating" mansion; wind; the *bla*
of cattle and goats; quick and good.

- **Good:** ordination, making images, drawing maṇḍalas, virtuous acts,
  listening to the dharma, quick works, practice and offering,
  empowerment, averting rites, life and prosperity rites, giving ornaments,
  cutting hair and nails, councils, new clothes, taking in wealth and
  livestock, preparing medicine, washing the hair, painting, field work,
  sowing, taking a new house, cooking (?), horse races, throwing *mdos* and
  *gtor ma*, funerals, taming livestock, sewing tents, taking in goats and
  cattle, power and increasing rites, works for the living, white work.
- **Avoid:** consecration, offerings to the gods, enthronement, long-life
  rites for a spiritual friend, petitioning, paying out goats and
  livestock, setting out, lawsuits, divination, pitching tents (bad, but
  good on one's own estate), burial, opening a grave, gifts, theft and
  raids, opening ponds, canals and storehouses, affairs of state,
  petitioning and honouring, fighting enemies, funeral rites, judging
  cases, most pacifying and fierce rites, dams, shows, naming. A bride: a
  daughter dies.
- **Born on it:** lives 28 years; given to song, dance and music, generous,
  handsome, eloquent, sensual, stubborn (?), truthful but harsh, lustful,
  hard-natured, wearing many clothes and garlands.
- Illness from defiled food, ransom on a dough tiger with sweet rice; few
  deaths repeat: bury at once; portents bad harvests, much strife, bad for
  scribes, potters and the like; rain soon falls again. Minor star *bag ma
  sna rum*: building livestock pens and prosperity rites, bad for brides;
  if obstructed, show a tiger's skull above oneself.

## 15. sa ri (Svātī)

p. 321 (img. 329):

> ས་རི་སྐར་གཅིག་ནོར་བུའི་དབྱིབས། །རུས་ནི་རིན་ཅེན་ལྷ་རླུང་ལྷ། །ཕྱེ་དང་ཏིལ་ཐུག་བལ་སྲན་ཟ། །རླུང་ཁམས་དགྲ་སྐར་བརྗེའི་འབྲུ། །གནག་བླ་མི་བརྟན་འགྱུར་བ་སྟེ། །རབ་བྱུང་རབ་གནས་བྱ་བ་དང་། །དགེ་ལེགས་དཀྱིལ་འཁོར་སློབ་གཉེར་དབང་། །སྨན་སྦྱོར་ལྷ་སྒྲུབ་འཆད་ཉན་བྱ། །ལྷ་གཡང་ཞལ་གསོ་རྟེན་བཞེངས་དང་། །ཞིང་ལས་འབྲུ་འཐོར་ས་ཁ་དབྱེ། །ཚེ་འགུགས་བཟློག་པ་སྲི་མནན་བསྡུམས། །དྲ་གྱོན་རྒྱལ་ས་བང་མཛོད་འཛིན། །ཞལ་ཆེ་ཆུ་བརྒལ་སྦྲ་ཁྱིམ་འབུབས། །གཉེན་སྦྱོར་ལམ་ཞུགས་རྟ་གླང་འདུལ། །སྟོན་མོ་བག་ཁྲུས་སྦྱིན་སྲེག་བྱེད། །རྫིང་ཡུར་ཁྱིམ་གསར་སྦྲ་ཕྱར་འཚེམ། །རྟ་བོང་དྲེའུ་སྒ་རྒྱག་བཅོས། །གཏར་བསྲེག་ཆུ་རགས་ལྡུམ་ར་གདབ། །ཡུལ་མཁར་ཚུར་ལེན་ཤེ་བླང་སོགས། །རྒྱས་དབང་ཞི་བ་ཡིས་མཚོན་པའི། །གསོན་གཤིན་ལས་རྣམས་ཕལ་ཆེར་བཟང་། །མཁར་ལས་ས་ཞིང་ཡུལ་མཁར་གཏོང་། །ཕར་འཇལ་དོན་གཉེར་འབྲུ་བང་འབྱེད། །ལྟད་མོ་རྟ་རྒྱུག་དུར་འདེབས་སྐྱས། །རྐུ་ཇག་དྲག་ལས་ངན་པས་འཛེམ། །

Good and avoid lists read on the scan, 2026-10-07.

One star, shaped like a jewel; clan "the precious", deity Vāyu; food flour,
sesame broth and beans; wind; an enemy mansion, the *bla* of cattle;
unstable and changing.

- **Good:** ordination, consecration, virtuous acts, maṇḍalas, study,
  empowerment, preparing medicine, deity practice, teaching, prosperity
  rites, restoring and making images, field work, scattering grain,
  breaking ground, summoning life, averting rites, suppressing *sri*,
  reconciling, new clothes, enthronement, taking over a storehouse,
  judging cases, crossing water, pitching tents, marriage alliances,
  setting out, breaking horses and oxen, feasts, the bride's bath, fire
  offerings, ponds and canals, a new house, sewing tents, saddling and
  treating horses, donkeys and mules, bloodletting and moxibustion, dams,
  laying out gardens, taking in land and forts; most works for the living
  and the dead of the increasing, power and pacifying kind.
- **Avoid:** building, giving away land, fields or forts, paying out,
  lawsuits, opening grain stores, shows, horse races, burial, gifts, theft
  and raids, fierce rites.
- **Born on it:** lives 20 to 60 years, an astrologer who finds wealth,
  quick to anger, guileful, troubled in mind, rich, beautiful, loving to
  those above and hating those below, jealous and stingy, gentle by nature,
  fond of councils, disciplined; a boy powerful, a girl strong-willed.
- Illness from going to a ruined place, ransom on a dough buffalo with
  mushrooms; a death does not repeat: bury at once; earthquakes, threats
  and eclipses bad; portents strife grows, bad for Phu ral (?); light rain.
  Minor star *srin mo me khyer*, middling in summer, at midnight in autumn;
  no household tasks; if obstructed, a skull of the impure (?) placed on
  oneself calms the harm.

## 16. sa ga (Viśākhā)

p. 322 (img. 330; the first line, beside the woodcut at the top of the
page, is MITRA's reading: Yigdzin-1 dropped it):

> ས་ག་སྐར་བཞི་ར་མགོའི་དབྱིབས། །དུས་མཚན་དབང་པོ་མེ་ལྷ་ལྷ། །ཏིལ་དང་མེ་ཏོག་རྒྱ་སྲན་ཟས། །འགྲུབ་སྐར་མེ་ཁམས་ཞིང་གི་བླ། །བཏང་སྙོམས་ཅན་ལ་དཀྱིལ་འཁོར་བྲི། །རབ་བྱུང་རབ་གནས་དབང་བསྐུར་སྒྲུབ། །སྦྱིན་སྲེག་འཆད་ཉན་སློབ་གཉེར་བྱ། །གཉེན་སྦྱོར་དྲ་གྱོན་བུ་ལོན་འཇལ། །བཟོ་དང་ཁྱིམ་གསར་བང་མཛོད་འཛིན། །སྲི་མནན་རྟ་གླང་འདུལ་བ་དང་། །ཞིང་ལས་ས་བོན་སྐྲ་སེན་འབྲེག །ཆད་བརྩིག་དོན་གཉེར་དབང་དྲག་ལས། །ཆུ་རགས་དགྲ་འདུལ་ལས་རྣམས་བཟང་། །དུར་གདབ་ཞིང་འཚོང་བང་མཛོད་དབྱེ། །ཁྱིམ་འབུབས་མཁར་ལས་མགྲོན་འགྲོ་ལམ། །ལྷག་ཡང་ཞུ་གསོལ་ཤིད་དང་ཚོང་། །རྒྱལ་ས་ལྷ་ཁང་རྟེན་བཞེངས་པ། །སྨན་སྦྱར་ཞི་དང་རྒྱས་པའི་ལས། །འབྲུ་འཇལ་ཁ་མཆུ་བུན་གཏོང་སྐྱས། །སྟོན་མོ་སྐྲ་འཁྲུ་མོ་རྩིས་བྱ། །བསྡུམས་དང་མེ་ཏོག་ཤིང་འཛུགས་ངན། །གསོན་པོའི་ལས་དང་ཞིང་ཉོ་འབྲིང་། །བག་མ་ལེན་བཟང་གཏོང་བ་ངན། །

Good and avoid lists read on the scan, 2026-10-07.

Four stars, shaped like a goat's head; *dus mtshan* (?), deity Indra and
Agni; food sesame, flowers and peas; an accomplishing mansion; fire; the
*bla* of fields; neutral.

- **Good:** drawing maṇḍalas, ordination, consecration, empowerment,
  practice, fire offerings, teaching, study, marriage alliances, new
  clothes, paying debts, crafts, a new house, taking over a storehouse,
  suppressing *sri*, breaking horses and oxen, field work, sowing, cutting
  hair and nails, building, lawsuits, power and fierce rites, dams,
  subduing enemies.
- **Avoid:** burial, selling fields, opening storehouses, pitching a house
  (tent?), building, guests going, setting out, and also petitioning,
  funerals, trade, enthronement, temples, making images, preparing
  medicine, pacifying and increasing rites, measuring grain, disputes,
  lending, gifts, feasts, washing the hair, divination, reconciling,
  planting flowers and trees. Middling: works for the living, buying
  fields. A bride: taking one in is good, sending one away bad.
- **Born on it:** lives 70 years, splendid and glorious, with attendants
  and wealth, subdues enemies, wise, women gather round; bad in youth, good
  in age.
- Illness from travelling at night, ransom on a dough tiger with molasses;
  a death: bury at once; portents head disease and phlegm, fires, robbers
  die; rain falls again. Minor star *'bru mo thang skyon*, everything good;
  if obstructed, raise a tiger's skull at one's own place.

## 17. lha mtshams (Anurādhā)

p. 322–323 (img. 330–331):

> ལྷ་མཚམས་སྐར་བཞི་གླང་པོའི་དབྱིབས། །རིགས་དམངས་ལྷ་ཉི་ཟས་ཆང་དང་། །སྲན་ཆུང་སྦྲང་རྩི་ས་ཡི་ཁམས། །མྱུར་བཟང་ཟློས་སྐར་བརྗེའི་བླ། །རབ་བྱུང་རབ་གནས་ལྷ་གཡང་སྨན། །དགེ་ལས་ཆོས་སྟོན་མཆོད་རྟེན་གསོ། །དྲ་གྱོན་རྒྱན་སྤྲོད་རྒྱལ་སར་བསྐོ། །སྐྲ་འཁྲུ་གཏར་སྲེག་རི་མོ་བཟོ། །དམག་ཇག་ཕར་རྒོལ་གཏད་དྲག་ལས། །དབང་དང་རྒྱས་པའི་ལས་སྤྱི་དང་། །ཚེ་སྣུབ་ཞིང་ལས་ས་བོན་གདབ། །ལམ་ཞུགས་རྩེད་འཇོ་མྱུར་སྒྲུབ་ལས། །རྟ་བོང་དྲེའུ་སྒ་རྒྱག་པ། །ཞལ་ཆེ་གཤེ་སྡུད་སྐྲ་སེན་འབྲེག །ཐག་རིང་བགྲོད་དང་བལ་གཡང་བསློན། །རྙེད་ཚུར་ལེན་དང་ཕྱ་རི་དྭགས། །གཤོར་ལ་དོན་གཉེར་ལྷོ་དང་ནུབ། །ཡུལ་བཟུང་སློབ་གཉེར་ཚུར་ལེན་བཟང་། །ཁྱིམ་འབུབས་གཤིན་ལས་ཕྱིར་འཇལ་དང་། །གྲོས་འདུན་མཁར་ལས་ཁྱིམ་གསར་འཛིན། །ཞི་ལས་སྟོན་མོ་ས་ཁ་དབྱེ། །རྟ་བོང་དྲེལ་འདུལ་བཅས་བྱེད་པ། །བཟློག་པ་སྲི་མནན་གྲོང་ཁྱེར་བརྩིག །ཞིང་རྨོ་བུ་འབོགས་ཐབ་འཆའ་ངན། །བག་མ་ཁྱོ་དང་འབྲལ་བས་འཛེམ། །

Good and avoid lists read on the scan, 2026-10-07.

Four stars, shaped like an elephant; caste the commoners, deity the Sun
(?); food beer, peas and honey; earth; quick and good, a "repeating"
mansion, the *bla* of *brje* (?).

- **Good:** ordination, consecration, prosperity rites, medicine, virtuous
  acts, teaching the dharma, restoring stupas, new clothes, giving
  ornaments, enthronement, washing the hair, bloodletting and moxibustion,
  painting, war and raids, attacking, *gtad* and fierce rites, power and
  increasing rites in general, field work, sowing, setting out, games,
  quick works, saddling horses, donkeys and mules, judging cases, cutting
  hair and nails, travelling far, prosperity rites for wool, taking in
  gains, game (?), lawsuits in the south and west, taking land, study,
  taking in.
- **Avoid:** pitching a tent, funeral rites, paying out, councils,
  building, taking a new house, pacifying rites, feasts, breaking ground,
  breaking horses, donkeys and mules, averting rites, suppressing *sri*,
  building towns, ploughing, giving a child away, setting up a hearth. A
  bride parts from her husband: avoid.
- **Born on it:** a boy lives 28 years, law-abiding, eloquent, handsome,
  rich, tamed, religious, wise; a girl is bad.
- Illness from coming between fighters, ransom on a dough black antelope
  with peas; a death repeats five times; portents a peaceful realm, good
  harvests, no strife; rain soon. Minor star *bum ldan*: avoid everything;
  if obstructed, a skull placed on oneself calms all harm.

## 18. snron (Jyeṣṭhā)

p. 323 (img. 331):

> སྣྲོན་ནི་སྐར་གསུམ་ཐེམ་སྐས་དབྱིབས། །ལྷ་དབང་པོ་དང་རུས་ལེགས་བྱེད། །ཁ་ཟས་འབྲས་དམར་འབྲས་ཐུག་ཟ། །ས་ཁམས་མགྲོན་སྐར་ར་ཡི་བླ། །གཏམ་དྲག་ངན་ལ་གཡུལ་གཤོམ་དང་། །མནན་གཏད་འཇོམས་འཕྲོག་དྲག་པོའི་ལས། །ཕུར་སྦུབ་གཡུལ་འགྱེད་བསད་པའི་ལས། །རྐུན་ཇག་ངན་འཐབ་ཞུ་བ་གསོལ། །རབ་བྱུང་རབ་གནས་ཚེ་གཡང་འགུགས། །ལྷ་གསོལ་བཀྲ་ཤིས་རྒྱལ་སར་བསྐོ། །དགེ་ལེགས་སློབ་གཉེར་ས་བོན་གདབ། །རྒྱན་སྤྲོད་ནོར་བླང་བུ་ལོན་སྙེག །སྐྱེ་ཤིང་ཟམ་འཛུགས་ཆུ་རྐ་ལེན། །གཏར་སྲེག་རྟ་ཕྱུགས་ཁྱུ་ལ་གཏོང་། །ལྷོ་དང་ནུབ་ལ་དོན་གཉེར་བ། །གྲོང་ཁྱེར་གཞོམ་དང་སྲི་མནན་པ། །མངོན་སྤྱོད་ཐུག་འགྱེད་གསོན་ལས་བཟང་། །མཁར་ལས་དྲ་གྱོན་ལམ་ཞུགས་བཟོ། །སྨན་སྦྱར་མོ་རྩིས་རྟེན་བཞེངས་འདུམས། །གཤིན་ལས་བྲན་བསྟེན་ར་ཕྱུགས་གཏོང་། །མིང་འདོགས་ཆུ་རགས་སྐྱོར་བཤས་བྱེད། །ཤི་ཆོས་དོན་གཉེར་བུ་མོ་སྐྱེས། །ཞི་རྒྱས་དབང་གི་ལས་རྣམས་དང་། །བག་མ་གཏོང་ལེན་གཉིས་ཀ་ངན། །

Good and avoid lists read on the scan, 2026-10-07.

Three stars, shaped like a ladder; deity Indra, clan Legs byed; food red
rice and rice broth; earth; a guest mansion, the *bla* of goats.

- **Good:** harsh speech, bad work, arraying for battle, suppressing,
  *gtad*, destroying, robbing, fierce rites, burying the *phur pa*,
  battle, killing, theft and raids, bad fights, petitioning, ordination,
  consecration, summoning life and prosperity, offerings to the gods,
  auspicious acts, enthronement, virtuous acts, study, sowing, giving
  ornaments, taking wealth, claiming debts, planting trees, building
  bridges, drawing water channels, bloodletting and moxibustion, sending
  horses and livestock to the herd, lawsuits in the south and west,
  destroying towns, suppressing *sri*, sorcery, sending a *thug* (?),
  works for the living.
- **Avoid:** building, new clothes, setting out, crafts, preparing
  medicine, divination, making images, reconciling, funeral rites, taking
  servants, giving away goats, naming, dams, death rites, lawsuits, the
  birth of a girl, pacifying, increasing and power rites; giving and
  taking a bride are both bad.
- **Born on it:** lives 35 years, of little compassion, poor, of few
  wants, busy with bad deeds; offerings and gifts increase, life is short;
  who takes up the dharma soon breaks his vows.
- Illness from quarrels at night, ransom on a dough monkey with Chinese
  peas; a death middling: bury after the rites; portents good harvests,
  disease and strife calmed; rain later and little. Minor star *chen ma*:
  bad for torn clothes, close the door of prosperity; if obstructed, show
  a monkey's skull above oneself.

## 19. snrubs (Mūla)

p. 323–324 (img. 331–332):

> སྣྲུབས་ནི་སྐར་དགུ་སྡིག་པའི་དབྱིབས། །ལྷ་ནི་རྔོན་པ་ཅན་དང་ནི། །རུས་ནི་ཀ་ཏྱ་ཡ་ནའོ། །འོ་མ་ནག་རྩ་སྡོང་འབྲས་ཟ། །དམེ་སྐར་ཆུ་ཁམས་དམངས་པོའི་བླ། །འབྲས་བུ་ངན་ལ་དམག་འདྲེན་མཚོན། །བསད་དྲག་ངན་ཐབས་གྲོང་ཁྱེར་འཇོམས། །རབ་གནས་ཚེ་གཡང་རྟེན་བཞེངས་ཚོང་། །ལྷ་གསོལ་ཞལ་གསོ་སྐྱས་བྱ་བ། །མཁར་ལས་སྟོན་མོ་ས་བོན་གདབ། །གཤིན་ལས་མོ་རྩིས་སྲི་མནན་བཟློག །ཞི་ལས་སྒོ་འཛུགས་བང་བ་བརྩིག །རྒྱན་སྤྲོད་གཏམ་གཏོང་ཐུག་འགྱེད་པ། །ནོར་རྫས་ཚུར་ལེན་ལས་ཕྲ་མོ། །དབང་དྲག་ལ་སོགས་གསོན་པོའི་ལས། །མདོས་དང་གཏོར་མ་བྲན་ལས་བཟང་། །ཕར་འཇལ་ལམ་ཞུགས་རྒྱལ་སར་འཇུག །དོན་གཉེར་དྲ་གྱོན་ཤ་མར་གཏོང་། །སྦྲ་ཕྱར་ཐབ་བཅའ་རྒྱལ་མཚན་འཛུགས། །དུར་འདེབས་སྡུད་ཁ་ཟ་འོག་འཇུག །མིང་འདོགས་ཆུ་རགས་ཁྱིམ་གསར་འཛིན། །སྦྱིན་སྲེག་བྱེས་བགྲོད་རྒྱལ་པོའི་ལས། །ཞི་བ་རྒྱས་པའི་ལས་སྤྱི་དང་། །འདུན་གྲོས་བཀྲ་ཤིས་དགེ་ལེགས་ལས། །བག་མ་གཏོང་ལེན་ངན་པས་སྤང་། །

Good and avoid lists read on the scan, 2026-10-07.

Nine stars, shaped like a scorpion; deity "the one with hunters", clan
Kātyāyana; food milk, black roots and fruit; a *dme* mansion; water; the
*bla* of commoners; bad in its results.

- **Good:** leading an army, weapons, killing, fierce rites, bad means,
  destroying towns; consecration, life and prosperity rites, making
  images, trade, offerings to the gods, restoring, gifts, building, feasts,
  sowing, funeral rites, divination, suppressing *sri*, averting rites,
  pacifying rites, setting up a door, building storehouses, giving
  ornaments, sending messages, sending a *thug* (?), taking in goods,
  small tasks, power, fierce and other works for the living, *mdos* and
  *gtor ma*, servants' work.
- **Avoid:** paying out, setting out, enthronement, lawsuits, new clothes,
  giving meat and butter, raising (?), setting up a hearth, raising victory
  banners, burial, naming, dams, taking a new house, fire offerings,
  travelling abroad, royal affairs, pacifying and increasing rites in
  general, councils, auspicious and virtuous acts; giving and taking a
  bride: bad, abandon.
- **Born on it:** lives 35 or 63 years, fortunate, master of house, wealth
  and grain; a girl is hard to raise; proud and busy with bad deeds.
- Illness from impurity, ransom on a dough peacock with sesame; a death is
  bad for wealth: bury at once; portents easy births, no disease, medicine,
  mantra and trade increase, bad for traders and boatmen; rain later.
  Minor star *khrus mo bu skyes*: sending off a son or sister is bad; if
  obstructed, raise a peacock's skull above oneself.

## 20. chu stod (Pūrvāṣāḍhā)

p. 324 (img. 332):

> ཆུ་སྟོད་སྐར་བཞི་ཀ་ཏྱའི་རིགས། །ཆུ་ལྷ་ལྷར་འཛིན་མཆོད་རྟེན་དབྱིབས། །ནྱ་གྲོ་ཏ་ནི་ཟས་སུ་ཟ། །ཆུ་ཁམས་གཡང་སྐར་བརྟན་དོན་འགྲུབ། །གཏུམ་དྲག་ཤ་མར་ནས་ཀྱི་བླ། །རབ་བྱུང་རབ་གནས་ལྷ་གཡང་དབང་། །གཉེན་སྦྱོར་རྒྱལ་ས་སྐྱེད་ཚལ་བཟོ། །ཞལ་གསོ་འཁོར་དང་བུད་མེད་བསྟེན། །ཞུ་གསོལ་ས་བོན་ནོར་ཕྱུགས་ལེན། །རྒྱན་སྤྲོད་སྐྲ་འཁྲུ་ས་ཁ་དབྱེ། །ལྟད་མོ་རྟ་ཕྱུགས་ཞིང་ཁང་ཉོ། །བཟློག་རིམ་སྦྱིན་སྲེག་བང་མཛོད་བཅའ། །མཁར་ལས་རྒྱལ་སར་བསྐོ་བ་དང་། །ཚེ་སྒྲུབ་མིང་འདོགས་དྲག་ལས་ཤིད། །ལམ་ཞུགས་སྦྲ་ཕྱར་འཚེམ་དང་ཚོང་། །བཀྲ་ཤིས་དོན་གཉེར་ལྷོ་དང་ནུབ། །རྨོས་འཛུགས་ངོ་བསྲོ་དགྲ་དང་འཐབ། །གཉེར་སར་བསྐོ་སོགས་གསོན་ལས་བཟང་། །འབྲུ་ནོར་ཕྱིར་གཏོང་བང་ཁ་དབྱེ། །དྲ་གྱོན་གཤིན་ལས་ཐབ་ཁ་འཆའ། །འཕོ་སྐྱས་སྟོན་མོ་རྟ་རྒྱུག་པ། །ཞི་རྒྱས་ལས་དང་ཆུ་རགས་བརྩིག །བག་མ་མི་རུང་ངན་པས་སྤང་། །

Good and avoid lists read on the scan, 2026-10-07.

Four stars, shaped like a stupa; clan Kātya, deity the water god; food the
fig (*nya gro dha*); water; a mansion of prosperity, stable and
accomplishing; fierce; the *bla* of meat, butter and barley.

- **Good:** ordination, consecration, prosperity rites, empowerment,
  marriage alliances, enthronement, making gardens, restoring, taking
  attendants and women, petitioning, sowing, taking in wealth and
  livestock, giving ornaments, washing the hair, breaking ground, shows,
  buying horses, livestock, fields and houses, averting rites, fire
  offerings, building storehouses, building, long-life rites, naming,
  fierce rites, funerals, setting out, sewing tents, trade, auspicious
  acts, lawsuits in the south and west, ploughing and planting, fighting
  enemies, appointing stewards, works for the living.
- **Avoid:** sending away grain and wealth, opening storehouses, new
  clothes, funeral rites, setting up a hearth, moving house, gifts, feasts,
  horse races, pacifying and increasing rites, building dams; a bride is
  not allowed: abandon.
- **Born on it:** lives 80 years, religious and learned, skilled in crafts
  and every task; proud and with many attendants, but long-lived, and
  reaches the higher realms; a girl turns out bad.
- Illness from misfortune and mourning, ransom on a dough makara; a death
  is bad for wealth: carry it out; portents rain, easy births, no disease,
  medicine and mantra increase, somewhat bad for smiths; rain falls again.
  Minor star *khye'u rgyas byed*, every task good; if obstructed, hold a
  whole makara figure above oneself.

## 21. chu smad (Uttarāṣāḍhā)

p. 324 (img. 332; also read by eye on the scan, [mansions.md](mansions.md)):

> ཆུ་སྨད་སྐར་བཞི་བྲེ་ལྟ་བུ། །རིགས་ནི་རྟག་གུ་ལྷ་ཐམས་ཅད། །ཁ་ཟས་སྦྲང་ཡོས་ས་ཡི་ཁམས། །འགྲུབ་སྐར་རབ་བརྟན་འབྲུ་ཡི་བླ། །རྒྱན་འདོགས་རབ་གནས་འཆད་ཉན་དབང་། །བསྙེན་སྒྲུབ་སྡོམ་ལེན་བྲན་གཡོག་བསྟེན། །འབྲུ་ནོར་ཚུར་ལེན་སྐྱེད་པོ་འཛུགས། །རྟེན་བཞེངས་དྲ་གྱོན་རྒྱལ་སར་འཇུག །མཁར་ལས་ཡུལ་གསར་གནས་གཞི་བཟུང་། །ལྷ་གཡང་ཚེ་སྒྲུབ་མོ་རྩིས་བྱ། །སྐྱེ་ཤིང་མེ་ཏོག་སྐྲ་འཁྲུ་ཁྲུས། །འདུན་གྲོས་བཀྲ་ཤིས་བྱ་བ་དང་། །གཤིན་ལས་ས་བོན་བང་མཛོད་བཅའ། །ཟློག་རིམ་སྦྲ་ཕྱར་འཕོ་སྐྱས་བསྡུམས། །རྟ་བོང་དྲེའུ་སྒ་རྒྱག་བཅོས། །རྒྱལ་མཚན་བ་དན་རྫིང་ཡུར་འཆོས། །སྨན་སྦྱོར་བག་མ་གཏོང་ལེན་བྱ། །དབང་དང་ཞི་རྒྱས་ལས་སྤྱི་དང་། །མིང་འདོགས་གསོན་པོའི་བྱ་བ་བཟང་། །ཕྱུགས་བཤུག་འབྲུ་འཇལ་མར་གཏོང་བ། །རོ་བསྲེག་ལམ་ཞུགས་བང་མཛོད་དབྱེ། །སྐྲ་སེན་ཆུ་རགས་དུར་སྦ་བཟོ། །དྲག་ལས་མཛོ་མོ་ཕྱི་ལ་གཏོང་། །སྦྲ་ཁྱིམ་འབུབས་དང་དོན་གཉེར་འཛེམ། །

Good and avoid lists read on the scan, 2026-10-07.

Four stars, shaped like a measure; clan Rtag gu, deity "all the gods";
food honey and molasses; earth element; an "accomplishing" mansion,
very stable, the *bla* of grain.

- **Good:** ornaments, consecration, teaching and listening, empowerment,
  retreat practice, taking vows, taking servants, taking in grain and
  wealth, planting gardens, making images, new clothes, enthronement,
  building, a new land or dwelling, prosperity and long-life rites,
  divination, planting trees and flowers, washing the hair, bathing,
  councils, auspicious acts, funeral rites, sowing, building storehouses,
  averting rites, treating horses, donkeys and mules, raising banners,
  repairing ponds and canals, preparing medicine, **giving and taking a
  bride**, power, pacifying and increasing rites in general, naming,
  everything for the living.
- **Avoid:** slaughtering livestock, measuring out grain, giving butter
  away, **cremation**, **setting out on a journey**, opening storehouses,
  cutting hair and nails, building dams, making tombs and hiding places,
  fierce rites, sending a female dzo away, pitching a tent, lawsuits.
- Born on it: lives 85 years.

## 22. gro bzhin (Śravaṇa)

p. 325 (img. 333):

> གྲོ་བཞིན་སྐར་གསུམ་བྲེ་ལྟ་བུ། །རུས་ནི་མོ་དགལ་ལྷ་ཁྱབ་འཇུག །ཁ་ཟས་མར་གསར་བྱ་ཤ་ཟ། །ས་ཁམས་མགྲོན་སྐར་བོན་པོའི་བླ། །མྱུར་ཞིང་འགྱུར་ལ་བློ་གྲོས་གཞི། །རྫིང་ཡུར་ཆུ་ལས་སོ་ནམ་ཚོང་། །སྐྱིད་སྟོན་དགེ་ལེགས་ཚེ་གཡང་གཉེན། །དྲ་གྱོན་རྒྱན་སྤྲོད་ཁྱིམ་གསར་འཛིན། །རྟེན་བཞེངས་བཟློག་རིམ་མོ་རྩིས་བྱ། །སྨན་སྦྱོར་གཏར་སྲེག་ཕར་རྒོལ་བསྡུམས། །ལྷ་གསོལ་འབྲུ་གདབ་ས་ཁ་དབྱེ། །བཟའ་ཆེད་ནོར་བཙལ་བང་མཛོད་དབྱེ། །ལམ་ཞུགས་དོན་གཉེར་རྟ་གླང་འདུལ། །བང་བརྩིག་ནོར་འཇལ་ལྟད་མོ་དང་། །མི་གཡང་འགུགས་རྩེད་མྱུར་ལས་བཟང་། །རབ་བྱུང་རབ་གནས་རྒྱལ་ས་བསྐོ། །གསོན་ཆོས་བྱ་དང་བོན་ཆོག་བྱ། །གཤིན་ལས་དམག་ཇག་བུ་ལོན་འཇལ། །རྟ་བོང་བྱ་གཅོད་ཡོན་སྤྲོད་པ། །སྲི་མནན་སྟོན་མོ་ཤིང་ལས་སྤང་། །བག་མ་ལེན་ལ་ཤིས་པ་ཡིན། །ཞི་དྲག་ལས་རུང་དབང་རྒྱས་ངན། །

Good and avoid lists read on the scan, 2026-10-07.

Three stars, like a measure; clan Maudgalya, deity Viṣṇu; food fresh
butter and bird meat; earth; a guest mansion, the *bla* of Bonpos; quick
and changing, the ground of intelligence.

- **Good:** ponds, canals and water works, farming, trade, feasts of joy,
  virtuous acts, life and prosperity rites, marriage alliances, new
  clothes, giving ornaments, taking a new house, making images, averting
  rites, divination, preparing medicine, bloodletting and moxibustion,
  attacking, reconciling, offerings to the gods, sowing grain, breaking
  ground, seeking wealth for food, opening storehouses, setting out,
  lawsuits, breaking horses and oxen, building storehouses, paying out
  wealth, shows, summoning human prosperity, games, quick works.
- **Avoid:** ordination, consecration, enthronement, dharma for the
  living, Bon rites, funeral rites, war and raids, paying debts, horses and
  donkeys and gelding them, giving offerings, suppressing *sri*, feasts,
  woodwork. **Taking a bride** is auspicious; pacifying and fierce rites
  acceptable, power and increasing rites bad.
- **Born on it:** lives 62 years, broadly learned, good-hearted, loved by
  the king, brave, seldom ill, victorious over enemies.
- Illness from a crowded place, ransom on a dough buffalo with blood in its
  mouth; portents rain, easy births, medicine and mantra increase; rain
  soon. Minor star *rde'u mgo dmar*, most works for the living and the
  dead good; if obstructed, raise an ox's skull above oneself.

## 23. byi bzhin (Abhijit)

p. 325 (img. 333; the first line, beside the woodcut, is MITRA's reading:
Yigdzin-1 dropped it):

> བྱི་བཞིན་སྐར་གསུམ་དབྱིབས་གླང་མགོ། །རུས་ནི་སོ་མ་ལྷ་ཚངས་པ། །གཤིན་སྐར་རླུང་ཟ་ས་ཡི་ཁམས། །མྱུར་ལ་འགྱུར་བའི་སྐར་མ་སྟེ། །རབ་གནས་སྐྲ་བཞར་སེལ་བྱེད་པ། །དགྲ་ལྷ་གསོལ་དང་དགྲ་ལ་རྒོལ། །ཕྱིར་འཇལ་གསོན་དགེ་གཤིན་ལས་དང་། །ཆང་བཙོ་མཁར་ལས་ས་བོན་འདེབས། །སྲི་མནན་པ་དང་མོ་འདེབས་བཟང་། །འདུན་གྲོས་དོན་གཉེར་རྒྱལ་སར་བསྐོ། །དྲ་གྱོན་མཐུ་འགྱེད་གསེབ་གཏོང་བ། །བཀྲ་ཤིས་དགེ་ལེགས་བྱ་བའི་རིགས། །གཤིན་དང་ཚེ་ནོར་སྒྲུབ་པ་སོགས། །དམིགས་བསལ་མ་གཏོགས་ཞི་རྒྱས་དབང་། །དྲག་པོའི་ལས་རྣམས་ཕལ་ཆེར་ངན། །འབྲས་བུ་བྱིངས་ནི་གྲོ་བཞིན་མཚུངས། །བག་མ་གཏོང་ལེན་ཤྭ་རགས་སྤང་། །

Good and avoid lists read on the scan, 2026-10-07.

Three stars, shaped like an ox's head; clan Soma, deity Brahmā; a
mansion of the dead (*gshin skar*), feeding on wind (?); earth; quick and
changing.

- **Good:** consecration, shaving the hair, offerings to the *dgra lha*,
  attacking enemies, paying back, virtuous acts for the living, funeral
  rites, brewing beer, building, sowing, suppressing *sri*, divination.
- **Avoid:** councils, lawsuits, enthronement, new clothes, sorcery,
  sending spies, auspicious and virtuous acts, rites for the dead and for
  life and wealth; but for the special cases, most pacifying, increasing,
  power and fierce rites are bad. Its results are on the whole those of
  gro bzhin. Giving and taking a bride and *shwa rags*: abandon.
- **Born on it:** lives 60 years, learned and wise, of little craving,
  good-hearted, subdues enemies, rich and powerful.
- The verse has no lines on illness, death, portents or rain. Minor star
  *lha lcam skar gcig*: hiding a death (?), song and dance good; if
  obstructed, show a deer's skull above oneself.

## 24. mon dre (Dhaniṣṭhā)

p. 326 (img. 334):

> མོན་དྲེ་སྐར་བཞི་བྱ་ལྟ་བུ། །རུས་ནི་གནས་འཇོག་ལྷ་ནོར་ལྷ། །ཁ་ཟས་རྒྱ་སྲན་བྱི་བླའི་ཤ། །ཆུ་ཁམས་མགྲོན་སྐར་རྟ་བོང་བླ། །མྱུར་ཞིང་འགྱུར་ལ་རབ་གནས་དབང་། །རབ་བྱུང་གཡང་འགུགས་སྨན་སྦྱོར་ཚོང་། །རྟེན་བཞེངས་མཁར་ལས་ཁྱིམ་གསར་འཛིན། །དྲ་གྱོན་རྒྱན་སྤྲོད་རྟ་གླང་འདུལ། །བྲིས་བརྩིག་ཞིང་འདེབས་ས་ཁ་དབྱེ། །ལམ་ཞུགས་དོན་གཉེར་ཞུ་བ་གསོལ། །དབང་དང་དྲག་པོའི་ལས་སྤྱི་དང་། །གསོན་ལས་དམག་ཇག་ཆོས་སྟོན་འདྲེན། །ལྷ་གསོལ་འདུན་མ་ཆད་པ་བརྩིག །སྐྲ་འཁྲུ་རྟ་བཅོས་བྱེད་སོགས་བཟང་། །གཤིན་ལས་ནོར་རྫས་རྟ་ཕྱུགས་གཏོང་། །བཟློག་པ་སྲི་མནན་རྒྱལ་སར་འཇུག །རྟ་བོང་དྲེའུ་སྒ་རྒྱག་རྒྱུག །ཞི་རྒྱས་འཕོ་སྐྱས་ཐབ་འཆའ་གཉེན། །བག་མ་གཏོང་ལེན་ངན་པས་འཛེམ། །

Good and avoid lists read on the scan, 2026-10-07.

Four stars, like a bird; clan Vasiṣṭha, deity the Vasus (*nor lha*); food
Chinese peas and meat; water; a guest mansion, the *bla* of horses and
donkeys; quick and changing.

- **Good:** consecration, empowerment, ordination, summoning prosperity,
  preparing medicine, trade, making images, building, taking a new house,
  new clothes, giving ornaments, breaking horses and oxen, drawing and
  building (?), planting fields, breaking ground, setting out, lawsuits,
  petitioning, power and fierce rites in general, works for the living,
  war and raids, inviting a teacher of the dharma, offerings to the gods,
  councils, building dykes (?), washing the hair, treating horses.
- **Avoid:** funeral rites, sending away wealth, goods, horses and
  livestock, averting rites, suppressing *sri*, enthronement, saddling and
  racing horses, donkeys and mules, pacifying and increasing rites, moving
  house, gifts, setting up a hearth, marriage alliances; giving and taking
  a bride: bad, avoid.
- **Born on it:** lives 25 or 60 years, good-hearted, skilled in medicine,
  sons and wealth increase; angry and hard-working, bad-natured,
  coarse-minded but generous, skilled in song and dance, foolish, dies by
  water; a girl is beautiful.
- Minor star *rgyal nam*, everything good. Illness from travel, ransom on a
  dough human figure with greens; a death repeats once: bury at once;
  portents a peaceful realm, no disease, strife calmed, grain increases,
  bad for horses and donkeys; light rain; if obstructed, raise a horse's
  skull above oneself.

## 25. mon gru (Śatabhiṣaj)

p. 326–327 (img. 334–335):

> མོན་གྲུ་མི་རེང་ལྟ་བུའམ། །མེ་ཏོག་ཕུང་དབྱིབས་ལྷ་ཆུ་ལྷ། །རུས་ནི་སྐར་མའི་བུ་ཡི་བརྒྱུད། །ཁ་ཟས་ར་ཤ་ས་ཡི་ཁམས། །གཡང་སྐར་རབ་བརྟན་རྟ་བོང་བླ། །ཆོས་འཆད་རབ་གནས་རབ་བྱུང་དབང་། །སྨན་སྦྱོར་ས་བོན་རྒྱལ་སར་བསྐོ། །གཞན་གྱི་སློབ་དཔོན་གནས་གཞི་བཟུང་། །རྟེན་བཞེངས་མཛོད་བཅའ་ས་ཁ་དབྱེ། །རྟ་བཅོས་རྟ་འདུལ་རྟ་ཕྱུགས་ཉོ། །གཤིན་ཆོས་ལམ་ཞུགས་ཚུར་ལན་བསྡུམས། །ཉོ་ཚོང་ཡུལ་བཟུང་སྐྱེ་ཤིང་འཛུགས། །དགེ་ལེགས་འདུན་གྲོས་གསོན་བརྟན་ལས། །དོན་གཉེར་སྒྲུབ་མཆོད་གཡང་ལོན་ལྷ། །དམག་ཇག་ས་འཛིན་ལྟད་མོ་སོགས། །དམིགས་བསལ་མ་གཏོགས་ཞི་རྒྱས་དབང་། །ལས་སྤྱི་བཟང་ལ་དྲག་ལས་རུང་། །རྒྱན་སྤྲོད་དྲ་གྱོན་རྫིང་ཡུར་འདྲུ། །བཟློག་པ་བཟོ་དང་སྐྲ་སེན་འབྲེག །རྟ་བོང་ནོར་གཏོང་སྣོད་ཁ་དབྱེ། །མཁར་ལས་བྱ་དང་རྐུན་མ་ཤིད། །རྟ་རྒྱུག་སྦྲ་ཕྱར་འཚེམ་ལ་སོགས། །བག་མ་གཏོང་ལེན་གཉིས་ཀ་ངན། །

Good and avoid lists read on the scan, 2026-10-07.

Like a *mi reng* (?) or shaped like a heap of flowers; deity the water god;
clan "the line of the star's son"; food goat meat; earth; a mansion of
prosperity, very stable, the *bla* of horses and donkeys.

- **Good:** teaching, consecration, ordination, empowerment, preparing
  medicine, sowing, enthronement, being another's teacher, taking a
  dwelling, making images, building a treasury, breaking ground, treating
  and breaking horses, buying horses and livestock, funeral rites, setting
  out, taking in (?), reconciling, buying and trading, taking land,
  planting trees, virtuous acts, councils, lasting works for the living,
  lawsuits, practice and offering, prosperity rites, war and raids, taking
  land, shows; but for the special cases pacifying, increasing and power
  rites good in general, fierce rites acceptable.
- **Avoid:** giving ornaments, new clothes, digging ponds and canals,
  averting rites, crafts, cutting hair and nails, giving away horses,
  donkeys and wealth, opening vessels, building, thieves, funerals, horse
  races, sewing tents; giving and taking a bride are both bad.
- **Born on it:** dies young, angry and wild, rich, handsome, generous,
  hard-working, rash in speech, healthy, brave.
- Illness from a widow's food, ransom on a dough human figure with rice; a
  death is bad for the community; portents a peaceful realm, grain
  increases, disease and strife calmed; rain falls again, bad for creatures
  in water. Minor star *lha mo g.yang skar*: offerings to the gods,
  prosperity rites and taking in good; if obstructed, show an owl's skull
  above oneself.

## 26. khrums stod (Pūrvabhādrapadā)

p. 327 (img. 335):

> ཁྲུམས་སྟོད་སྐར་གཉིས་ཤིང་རྟའི་དབྱིབས། །སྐྱེ་བོ་ལས་ཀྱི་རྒྱུད་ཡིན་ལ། །ལྷ་ནི་འབའ་ཞིག་འཚོ་བ་སྟེ། །ཁ་ཟས་ལུག་གི་ཤ་ཁྲག་སྤྱོད། །གཤིན་སྐར་མེ་ཁམས་བརྗེའི་བླ། །བརྟན་པའི་དོན་གྲུབ་སྐར་མ་སྟེ། །དབང་བསྐུར་རབ་གནས་ཚེ་འགུགས་གཉེན། །ལྷ་གསོལ་སྦྱིན་སྲེག་མཁར་ལས་བསྡུམས། །སྨན་སྦྱོར་གསོན་ལས་འདུན་གྲོས་སྐྱས། །ཕྱུགས་མ་ཉོ་དང་རྟ་ཕྱུགས་འདུལ། །ནོར་ལེན་ཐབ་བཅའ་རྒྱལ་མཚན་འཛུགས། །ཤིང་འཛུགས་བྲན་བསྟེན་བཟོ་རིག་སློབ། །གཤིན་ལས་སེལ་འཛུགས་གཞི་ཡི་ཚོང་། །དགེ་བའི་ལས་དང་ཁྱི་བསྟེན་པ། །ཤྭ་རགས་བརྩིག་སོགས་ཆུ་བོ་བསྲུང་། །ས་བཟུང་སྦྲ་ཕྱར་མིང་འདོགས་བཟང་། །ནོར་འཇལ་ཞལ་ཆེ་ཁ་སྨྲས་ལམ། །དྲ་གྱོན་སྐྲ་འཁྲུ་རྫིང་ཡུར་འདྲུ། །དོན་གཉེར་ཐག་རིང་ས་ཁ་དབྱེ། །སོ་ནམ་མགོ་འཛུགས་ཤིང་རྐུ་ཇག །རྨོས་འབྱེད་ཞིང་ལས་རྟ་རྒྱུག་པ། །བག་མ་གཏོང་ལེན་ངན་ལས་སྤང་།།དབང་དྲག་ལས་རུང་ཞི་རྒྱས་ངན། །

Good and avoid lists read on the scan, 2026-10-07.

Two stars, shaped like a cart; of the line of men's deeds (?), deity "the
only harmful one" (འཚེ་བ; MITRA reads འཚོ་བ, "the living one"); food mutton and blood; a mansion of the
dead; fire; the *bla* of *brje* (?); stable and accomplishing.

- **Good:** empowerment, consecration, summoning life, marriage alliances,
  offerings to the gods, fire offerings, building, reconciling, preparing
  medicine, works for the living, councils, gifts, buying female
  livestock, breaking horses and livestock, taking wealth, setting up a
  hearth, raising victory banners, planting trees, taking servants,
  learning crafts, funeral rites, setting up a *sel*, trading estates,
  virtuous acts, keeping dogs, building *shwa rags* and the like to guard
  against rivers, taking land, pitching tents, naming.
- **Avoid:** paying out wealth, judging cases, quarrels, setting out, new
  clothes, washing the hair, digging ponds and canals, lawsuits, far
  travel, breaking ground, beginning farming, theft and raids, ploughing,
  field work, horse races; giving and taking a bride: bad, abandon. Power
  and fierce rites acceptable, pacifying and increasing bad.
- **Born on it:** lives 95 (?) years, bad-natured and foolish, fond of
  theft, killing, war, raids and guile; loving to his kin and rich, hostile
  to outsiders, harmful to others, unable to give.
- Illness and its remedy as for mon gru; a death: send it off quickly;
  portents epidemics and eye disease, grain scarce, wealth burnt, bad for
  butchers, much phlegm disease; light rain. Minor star *lu khu gnyis
  ldan*: walls, journeys and forts all bad; if obstructed, show above
  oneself the skull of one whose line died out.

## 27. khrums smad (Uttarabhādrapadā)

p. 327–328 (img. 335–336):

> ཁྲུམས་སྨད་སྐར་གཉིས་རྣ་བའི་དབྱིབས། །ལྷ་ནི་སྤྲུལ་གཟའ་རུས་ནོར་རྒྱས། །ཁ་ཟས་བལ་སྲན་ཆུ་ཡི་ཁམས། །གཤིན་ཀར་མྱུར་བཟང་དྲག་ཤུལ་ཆེ། །རྒྱན་སྤྲོད་ལམ་སྨན་རི་མོ་བཟོ། །རབ་གནས་ཕྱུགས་སྤེལ་འཇལ་ལེན་བྱེད། །རྒྱལ་ས་འཆད་ཉན་དབང་སྡོམ་ལེན། །དྲ་གྱོན་འདུན་མ་འབྲུ་ནོར་སྡུད། །རྫིང་ཡུར་ཞིང་ལས་ས་བོན་གདབ། །རྟེན་བཞེངས་བཟློག་པ་སྲི་མནན་བྱ། །གཤིན་ལས་བྲན་བསྟེན་སྐྲ་འཁྲུ་ཤིད། །དབང་དྲག་མྱུར་ལས་དམག་བརྐུ་ཇག །ཆང་བཅོ་ཉོ་ཚོང་ས་ཁ་དབྱེ། །སྐྱེ་ཤིང་མེ་ཏོག་འཛུགས་པ་བཟང་། །ལྷ་གསོལ་གཡང་འགུགས་འདུ་ལོང་བྱེད། །མཁར་ལས་ཁྱིམ་འཛིན་གྲོང་ཁྱེར་བརྩིག །གཏར་སྲེག་ཐབ་བཅའ་སྦྲ་ཕྱར་འཚེམ། །གསོན་ལས་དོན་གཉེར་སྟོན་མོ་བསྡུམས། །མཐོང་འཛུགས་ཆུ་རགས་སྐྲ་སེན་འབྲེག །ལྟད་མོ་བུ་མོ་བཙས་དང་ཤིད། །ཞི་རྒྱས་བག་མ་དུག་འདེབས་ངན། །

Good and avoid lists read on the scan, 2026-10-07.

Two stars, shaped like an ear; deity *sprul gza'* (Ahirbudhnya?), clan Nor
rgyas; food beans; water; a mansion of the dead (?), quick and good, very
fierce.

- **Good:** giving ornaments, **setting out**, preparing medicine,
  painting, consecration, breeding livestock, measuring out and taking in,
  enthronement, teaching, empowerment and taking vows, new clothes,
  councils, gathering grain and wealth, ponds and canals, field work,
  sowing, making images, averting rites, suppressing *sri*, funeral rites,
  taking servants, washing the hair, funerals, power and fierce rites,
  quick works, war, theft and raids, brewing beer, buying and trading,
  breaking ground, planting trees and flowers.
- **Avoid:** offerings to the gods, summoning prosperity, gatherings,
  building, taking a house, building towns, bloodletting and moxibustion,
  setting up a hearth, sewing tents, works for the living, lawsuits,
  feasts, reconciling, setting up a *mthong* (?), dams, cutting hair and
  nails, shows, the birth of a girl, funerals, pacifying and increasing
  rites, **a bride**, laying poison.
- **Born on it:** a boy lives 50 or 60 years, skilled in song, dance and
  learning, given to dharma and generosity, rich, fond of giving, honest,
  disciplined, wise, compassionate, gathers attendants, victorious.
- Illness from drinking stale beer, ransom on a dough human figure with
  greens; minor star *skar drug*, no work in the morning; portents rain,
  easy births, no disease, medicine increases, somewhat bad for craftsmen;
  rain soon; if obstructed, show a snake's skull above oneself.

Journeys good and a bride bad, against Uttarāṣāḍhā's journeys bad and a
bride good: the same split the *kun phan me long* shows once its
abbreviations are read ([mansions.md](mansions.md)).

## 28. nam gru (Revatī)

p. 328 (img. 336):

> ནམ་གྲུ་སྐར་མ་སུམ་ཅུ་གཉིས། །གྲུ་དབྱིབས་རུས་ནི་སྐྱེད་པའི་བུ༑ །ལྷ་ནི་ཉི་མ་ཕྱེ་ཤ་ཟ། །ཆུ་ཁམས་འགྲུབ་སྐར་མི་བརྟན་འགྱུར། །རབ་བྱུང་རབ་གནས་ཆོས་འཆད་དབང་། །ལྷ་སྒྲུབ་སྡོམ་འབོགས་གོས་གསར་གྱོན། །མཁར་ལས་ཞལ་ཆེ་རྒྱལ་སར་བསྐོ། །ལྷ་གཡང་སྨན་སྦྱོར་དགེ་ལེགས་ཚོང་། །སྟོན་མོ་ཕྱུགས་འགྱེད་ས་བོན་གདབ། །ཁྱིམ་འཛིན་སྐྲ་འཁྲུ་ས་ཁ་འབྱེད། །གཤིན་ལས་རོ་དྲང་ཤིད་དང་སྐྱས། །གཏར་སྲེག་མིང་འདོགས་སྐྲ་སེན་འབྲེག །ཞུ་འབུལ་འདུན་མ་རང་དོན་སྒྲུབ། །ཕྱུགས་འདུལ་ཁ་སྤྲས་རྐུ་བ་དང་། །རྟ་དང་བོང་བུའི་བྱ་གཅོད་པ། །གསོན་ལས་སྐྱེ་ཤིང་འཛུགས་པ་སོགས། །དབང་ཞིའི་ལས་བཟང་རྒྱས་པ་རུང་། །གོས་དྲ་ལམ་ཞུགས་བུ་འབོགས་འགྱེད། །དམག་དྲག་འཕྲོག་འཇོམས་བརྟན་ལས་དང་། །བཟློག་རིམ་ཐབ་འཆའ་སྦྲ་ཕྱར་འཚེམ། །བསྡུམས་བྱ་ཆུ་རགས་བུ་མོ་འབོགས། །ཐུག་འགྱེད་བྱ་དང་ཡུལ་འདོན་དང་། །གསེབ་སྤྲོད་འབྲུ་ནོར་ཕྱིར་གཏོང་ངན། །དོན་གཉེར་བྱེས་ངན་རང་ཡུལ་བཟང་། །བག་མ་བྱས་ན་བུ་མི་སྐྱེ། །

Good and avoid lists read on the scan, 2026-10-07.

Thirty-two stars, shaped like a boat; clan "son of the nourisher", deity
the Sun; food flour and meat; water; an accomplishing mansion, unstable
and changing.

- **Good:** ordination, consecration, teaching, empowerment, deity
  practice, giving vows, wearing new clothes, building, judging cases,
  enthronement, prosperity rites, preparing medicine, virtuous acts, trade,
  feasts, distributing livestock, sowing, taking a house, washing the hair,
  breaking ground, funeral rites, laying out a corpse, funerals, gifts,
  bloodletting and moxibustion, naming, cutting hair and nails,
  petitioning, councils, one's own affairs, taming livestock, theft (?),
  gelding horses and donkeys, works for the living, planting trees; power
  and pacifying rites good, increasing acceptable.
- **Avoid:** cutting clothes, setting out, giving a child away, war, fierce
  work, robbing and destroying, lasting work, averting rites, setting up a
  hearth, sewing tents, reconciling, dams, giving a daughter away, sending
  a *thug* (?), expelling, informing (?), sending away grain and wealth.
  Lawsuits abroad bad, at home good. A bride: no son is born.
- **Born on it:** lives 28 or 90 years, very wise, busy with grain, wealth
  and riches, very good to others, of sound senses, not brave.
- Minor star *las sna dro lod*, no work at midnight and noon. Illness from
  a woman's death, ransom on a dough human figure with molasses thrown at a
  crossroads, freed in three days; a death is bad for astrologers;
  portents rain, easy births, medicine and mantra increase, bad for
  astrologers; light rain; if obstructed, show from one's own direction the
  skull of a woman whose line died out.

The section closes (p. 328–329): these are the particular results of each
mansion in brief; the minor stars' names differ much between sources, and
WB follows the *Dur mdzod*; where the bad cannot be avoided, read the
*Heart Sūtra* and the protective dhāraṇīs, make water offerings and offer
to the Three Jewels. Then come the mansions grouped by kind (unstable and
changing, very stable …), with what each kind is good for.

## The mansions' rise and decline (*dar gud*)

p. 329–330 (img. 337–338), read on the scan 2026-10-07:

> ད་ནི་སྐར་མའི་དར་གུད་བསྟན། །གང་གིས་ཉ་བ་དར་བ་ཡིན། །སྟོང་ལ་བྱུང་བ་གྱོད་པའོ། །གཞན་མ་རྣམས་ནི་གུད་པ་ཡིན། །སྨིན་དྲུག་ལ་སོགས་ཤར་སྐར་ཉ། །ཤར་སྐར་དར་ལ་ལྷོ་བྱང་གུད། །ནུབ་སྐར་གྱོད་དེ་དེ་ལྟར་བརྩི། །གཞན་ཡང་ཟླ་བའི་སྲོག་དང་ནི། །ལས་ཀྱི་སྐར་མ་འཐབ་པ་ཡི། །ཟླ་བ་མར་གྱུར་དར་བ་དང་། །བུར་གྱུར་གྱོད་ཅིང་གྲོགས་ཀྱང་དར། །དགྲ་གྱུར་གུད་ལ་རང་ཤར་ཞུད། །དར་བ་བཟང་ཞིང་གུད་པ་ངན། །གྱོད་ཞུད་གཉིས་པོ་འབྲིང་དུ་འདོད། །བརྩི་བའི་ལུགས་ཀུང་ཡོད་མོད་ཏུང་། །ནུས་སྟོབས་ལྡན་པའི་ལུང་ཁུང་དབེན།།

(The last line as printed: ཀུང, ཏུང and ཁུང for ཀྱང, ཀྱང and ཁུངས. The
quarters are counted from Kṛttikā, "Kṛttikā and the following, the
eastern mansions"; the line ends in a clear «ཉ» whose sense here is not
clear.)

A mansion is "rising" (*dar ba*) when the full moon falls on it,
*gyod pa* when the new moon does, "declining" (*gud pa*) otherwise; or by
quarter, the eastern mansions rising, the southern and northern
declining, the western *gyod*; or by the element of the moon's life
force against the mansion's, mother and friend rising, son *gyod*, enemy
declining, the same *zhud*. Rising is good, declining bad, *gyod* and
*zhud* middling. The verse ends: "there is that way of reckoning too,
but it lacks a scriptural source of force".

**For the app** (SPEC §5.12): the White Beryl gives the *dar gud* and
sets it aside in the same breath, so it does not change the mansion's
voice, which keeps no tone of its own.
