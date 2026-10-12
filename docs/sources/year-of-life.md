# The year of age: the elemental divination of a person's year

What the app reckons for a person's year (SPEC §5.9.1, ROADMAP T4): the
mewa, trigram and progressed sign of the year of age, the twenty-four
decisive pebbles and the predictive ones, the sectors of growth and
decline, and the year's obstacles, each with its reading.

**Texts.** WB, Beijing 1996, vol. 1 (BDRC MW2CZ8040, scans I1KG12906, open;
**printed page = image − 10**, the etext's `[pN]` = image N): chapter 21,
*nag rtsis kyi skor 'go dang sgang sgril* (pp. 252–258, img. 262–268), and
chapter 24, *keg rtsis dang rgya nag rdel skor* (pp. 380–452, img.
390–462, the obstacle years to p. 416). The *Moonbeams* (DZ) in KD vol. 3 (etext
VEIE0OPI2F246A26_I4717; printed page = etext − 20): pp. 493, 496 and 507.

**How read (2026-10-09).** Every page with Yigdzin-1 (`hf_read.py
yigdzin`), MITRA as witness and `disagree.py` over them; the rules below
were read again on the scans (crops at 2.4–4×), the readings from the two
machine readings with their disagreements checked on the scan where they
touch what the app says. The two agree on 91–97% of the syllables of
each page; every flag in the passages the app uses was MITRA's slip
(ཕ for བ, a dropped or doubled line), and the two that touch a word the
app quotes, བཙོན "prison" (img. 391 l. 14, img. 397 l. 24), read
བཙོན on the scan. The readings are restated in our own words in
the catalog (`reading.YearPebble…`, `reading.YearPredictive…`,
`reading.YearSector…`, `reading.LogMen…`, `reading.Harsh…`,
`reading.NineMultiple…`, `reading.MewaObstacle…`, `reading.YearTrigram…`,
`reading.YearMewa…`). Gyurme Dorje's *Tibetan Elemental Divination
Paintings* (2001; archive.org OCR and page images) was the map: its
chapters follow WB with folio references, and its Table 2.11 and chart 6.2
are the test vectors (`YearOfLifeTest`).

## The signs (chapter 21)

**The mewa's elements and the natal mewa** (p. 255, img. 265, on the scan):

> དཀར་གསུམ་ལྕགས་ལ་ནག་མཐིང་ཆུ། །ལྗང་གུ་ཤིང་ལ་དམར་པོ་མེ། །… ད་ལྟའི་རབ་ཉིའི་མེ་ཡོས་འདི། །སྨེ་ཁྲིད་དང་པོར་རྒྱུ་བ་ལ། །ཡར་བཞི་སྲོག་ལ་མར་བཞི་དབང་། །… དབང་སྨེ་གཅིག་དཀར་ཀླུང་རྟའི་སྨེ། །གཉིས་ནག་སྐྱེས་སྨེ་བདུན་དམར་ཏེ།

The three whites are iron, black and blue water, green wood, the reds
fire (yellow, earth, unsaid). The present rabjung's fire hare (1687)
leads the mewa with: destiny 1 white, luck 2 black, *natal mewa 7 red*.
One less each year gives the 九星 year star of the Tibetan year
(`YearOfLife.yearSmeBa`); Dorje's captions to the 180-year charts give the
same in all three cycles from 1864. The four aspects' own mewa ("four up
for vitality, four down for destiny…") are not built.

**The mewa of each year of age** (p. 256, img. 266, on the scan):

> སྨེ་བ་ཤ་སྟག་བར་གྱི་རྩིས། །དེ་ཕྱིར་བར་རྩིས་གཙོར་བཏོན་པའི། །ལག་ལེན་སྒང་སྒྲིལ་བ་ཡི། །སྐོར་ལུགས་དབུས་ནས་ཤར་དུ་ཐོན། །གང་ཟག་ཕོ་མོ་ཇི་ལྟར་རུང་། །ལོ་ཕོ་དབུས་ནས་ཤར་ཐོན་ཏེ། །བྱང་ཤར་མཚམས་ཕྱིན་ཤར་ལྷོ་ནས། །དབུས་སུ་འགྲོ་འོ་མོ་ལོ་ནི། །དབུས་ནས་ཤར་ཐོན་ཤར་ལྷོར་འགྲོ། །བྱང་ཤར་ནང་དུ་འཇུག་པར་བྱ། །ལོ་རེས་སྨེ་བ་རེ་ལ་འགྲོ།

The mewa are the intermediate reckoning; the circuit goes out from the
middle to the east, *whatever the person, man or woman*: in a male year
on to the north-east and back to the middle from the south-east, in a
female year to the south-east and in from the north-east; one mewa a
year. So the birth year's gender, not the person's, turns it; every cell
of Dorje's Table 2.11 agrees.

**The trigrams** (p. 256, img. 266; p. 257, img. 267, on the scan): their
natures, *li me, khon sa, dwa lcags, khen gnam, kham chu, gin ri, zin
shing, zon rlung*; then, of the circuits, WB rejects several («…བཅུ་བྱ་གཅོད་
འདུལ་འགྱེད་པ་སོགས། །མི་འདྲའི་འཁྲུལ་པ་འགའ་ཡོད་ཀྱང་། །… དགག་བྱའི་གནས།») and gives its own:

> སྤར་ཁ་རུས་པ་ནང་རྩིས་ཏེ། །དེ་ཕྱིར་བཅུད་ཕྱུང་ནང་སྐོར་འགོ་ནི། །གང་ཟག་སྐྱེས་པ་ལི་ནས་ཁོན། །བུད་མེད་ཁམ་ནས་ཁེན་ཕྱོགས་བསྐོར། །ལོ་རེ་སྤར་ཁ་རེ་སྦྱར་བརྩི།

A man from li towards khon, a woman from kham towards khen, one trigram
for each year. The *Moonbeams* (p. 493) adds «བཅུ་ཁ་མ་ལོངས་རེ་རེ་སྟེ། །བཅུ་ཁ་
ལོངས་ནས་ཟུར་ལ་མཆོང་།», a leap to the corner at each ten, and Dorje's worked
example (a mother's 34th year at li) fits neither count; WB's own words have none and it lists counting by
tens among the circuits it rejects, so the app counts one a year (chart
6.2's man of 23 at zin agrees either way). For the pebbles the trigram
counts as one element: the *Moonbeams*, p. 493, «མཁའ་རི་རླུང་ཡང་སར་བྱས་པས། །
མདོ་དོན་འབྱུང་བ་ལྔ་རུ་འགྱུར།», sky, mountain and wind are also made earth.

**The natal trigram** is not in WB's chapter 21. Dorje (p. 108, from the
*Moonbeams* f. 12a) makes it the mother's trigram of the year of the
birth, found from the mother's age; WB's chapter 22 and the *Moonbeams*
(f. 9a in Dharmaśrī's own print) say the same, read on the scans in
*The birth reckoning and the natal trigram (RD13)* below; it is not
built.

**The sectors of growth and decline** (p. 258, img. 268; Yigdzin-1, clean):

> དར་གུད་བཅུ་གཉིས་ནི། དབུགས་ལེན་མངལ་གནས་ལུས་རྫོགས་དང་། །བཙས་དང་ཁྲུས་བྱེད་གོས་གྱོན་པ། །ལས་བྱེད་དར་བ་གུད་པ་དང་། །ན་བ་ཤི་བ་དུར་ཞུགས་རྣམས། །སྲོག་ལུས་དབང་ཐང་ཀླུང་རྟ་བཞི། །ས་ཆུ་ཡིན་ན་དབྱར་ར་སྦྲུལ། །མེ་ཡིན་དགུན་ཟླ་ར་བ་ཕག །ལྕགས་ལ་དཔྱིད་ར་སྟག་ནས་བཟུང་། །ཤིང་རྣམས་སྟོན་ར་སྤྲེའུ་ནས། །དབུགས་ལེན་ཡིན་ནོ་… །མངལ་གནས་ལུས་སོགས་གཡས་སྐོར་ཕྱིན། །གནམ་ལོའི་སྟེང་སླེབ་ལོ་ཡིན་ཏེ།

Then the six good (flourishing: a great treasure found; working:
long-lasting; dressing: able to sustain; bathing: wishes fulfilled; body
complete: happiness; birth: great wealth) and the six bad (breath-taking:
fortune spent; womb: falling into misfortune; decline: separation;
illness: ruin and disputes; death: prosperity lost; tomb: the line cut);
the tombs of wood the sheep, fire the dog, iron the ox, earth and water
the dragon; entering the tomb the great penalty, the seventh the lesser.
The ranks for the pebbles are the *Moonbeams*' (p. 496): «དར་བ་ལས་བྱེད་བཟང་པོའི་
རབ། ཁྲུས་བྱེད་གོས་གྱོན་བཟང་པོའི་འབྲིང་། །ལུས་རྫོགས་བཙས་པ་བཟང་པོའི་མཐའ། །དབུགས་ལེན་མངལ་གནས་
ངན་པའི་རབ། །གུད་པ་ན་བ་ངན་པའི་འབྲིང་། །ཤི་བ་དུར་ཞུགས་ངན་པའི་ཐ།», with their pebbles on
p. 507 (three, two, one white; one of each, one black, two black). Chart
6.2's sector column (illness ×, birth ○, work ○○○) agrees.

## The obstacle years (chapter 24)

**The pebbles** (p. 380, img. 390): WB's own forty-seven, of which the
heart: «གནམ་ལོ་ལོག་མེན་སྤར་ཁ་དང་། །སྨེ་བ་དར་གུད་བརྩི་དུས་རྡེལ། །མི་འགྱུར་རྩ་བའི་རྡེལ་
དྲུག་ཅེས། །… སྲོག་ལུས་དབང་ཐང་བཞི་འཐབ་པས། །གཅོད་འདྲལ་རྡེའུ་ཉེར་བཞི»: the present year,
the progressed sign, the trigram, the mewa, the sectors and the time of
reckoning, six basic pebbles, each fought against vitality, body, destiny
(and luck): the twenty-four decisive pebbles. Mother three white, friend
two, son white and black, enemy two black, earth or water meeting itself
one white, wood, iron or fire one black (as SPEC §5.9 already had).

**The predictive pebble** (p. 381): «ཁ་སྦྲང་རྡེལ་འགྲེམ་ལུགས། །མི་མཐུན་མང་ཡང་དཀར་
ནག་གི། །གསུམ་གཉིས་ལ་སོགས་མི་འདྲེན་པར། །ངོ་ཐེག་དཀར་ནག་བགྲངས་པ་ཡི། །གང་མང་ཁ་དམར་རྡེལ་
བོར་བ།»: though the ways differ, count the white and black that are there,
not the threes and twos, and lay it on whichever are more. Dorje's chart
6.2 lays its predictive pebbles otherwise (vitality ○× with 8 white to 5
black); no rule we can find gives his column, and WB's words are
followed.

**Readings of the pebbles** (pp. 380–384, img. 390–394): for each of
vitality, body, destiny and luck (and the la, after Kun 'byung A lo,
not built), what three, two or one white, two or one black, or white and
black foretell; all four white and all four black. Then (pp. 384–385,
img. 394–395) the predictive readings: each aspect white, each black, all
white («སྐྱིད་པའི་ལ་བརྒལ»), all black. Then pebbles of colour and of houses
(pp. 385–387), not built.

**The progressed sign** (p. 387, img. 397):

> དེ་ནས་ལོག་མེན་ཕྱི་གཟའ་སྟེ། །… བརྩི་བྱའི་ལོ་ཁམས་གང་ཡིན་གྱི། །སྐྱེས་པ་བུ་འདོད་སྟག་ཐོག་ནས། །དུ་ལོན་ལོ་གྲངས་ཐུར་དུ་འདྲེན། །བུད་མེད་མ་ཡི་སྤྲེལ་ཐོག་ནས། །གྱེན་དུ་ལོ་གྲངས་བརྩིས་པ་ཡིས།

A man from the tiger of the son of his year's element, counting down; a
woman from the monkey of its mother, counting up. Down is forward through
the sixty: chart 6.2's man of a fire dragon year is at the iron mouse in
his 23rd. The same convention fixes "up" and "down" elsewhere: the
nine-multiples' "up from the mouse" reach the dragon at nine only
counting backward. Then the doors and fives: «ཁྱི་འབྲུག་གཉིས་ནི་གནམ་སྒོ་ལྔ། །
ཕག་སྦྲུལ་གཉིས་ནི་ས་སྒོ་ལྔ། །ཕོ་གནམ་ཁྱི་ལ་ས་སྒོ་ནི། །ཕག་ལྔར་བརྗོད་དོ་མོ་ཡི་ཡང་། །གནམ་སྒོ་
འབྲུག་ལ་ས་སྒོ་སྦྲུལ།» and ox and sheep the ruins, bird and monkey the
separations, mouse and horse the lineage-cuttings, tiger and hare the
gains, each with its reading; and what the element governs (p. 388, img.
398, on the scan): «ཤིང་གིས་ཕ་ཁུ་མེས་གསུམ་བརྩི། །མེ་ཡིས་བུ་ཚ་མ་སྨད་བརྩི། །ས་ཡིས་མ་ཕྱི་
ཡུལ་མཁར་བརྩི། །ལྕགས་ཀྱིས་ནོར་གྲོགས་ལྷ་སྲུང་བརྩི། །ཆུ་ཡིས་གཉན་ལམ་མཐའ་བཞི་བརྩི།». Water
counts the *gnyan*, roads and the four borders: the print has གཉན, not
Dorje's "in-laws" (*gnyen*). The four counted signs (*yar lnga* lifeline,
*mar lnga* peg, *yar brgyad* sky extension, *mar brgyad* earth extension)
are not built: WB says each is "fought" with one of the subject's aspects
but not with which of its own, and Dorje's chart 6.3 does not settle it.

**The harsh years** (pp. 387–390, img. 397–400): the own animal's year
(13 son: illness and enemies, body and destiny; 25 friend: reviving
breath, ruin, vitality for men, destiny for women; 37 enemy: the demon's
cutting, destiny; 49 mother: glory, luck; 61 own: separation, vitality;
73 as 13); the seventh (7 slayer, 19 glory, 31 separation, 43 enemy of
prosperity, 55 enemy of reviving breath, with the general remedies); the
triad; the spouses' shared trigram, the ancestors' tombs and the secret
obstacle (not built: other people's signs); the four slayers («ཡར་བཞི་ན་རེས་
མར་བཞི་འཆི།», the fourth up illness, the fourth down death); the
progressed sign on one's own year, on its seventh, on the enemy of the
vitality (p. 388).

**The trigram of the year of age** (pp. 391–396, img. 401–406): for each
trigram what is bound, the harmful spirits, dreams, illnesses, goods and
foods to avoid, persons to beware, the dangerous time, the directions,
the texts, rites and ransom; then by the year's animal. The app gives the
first part in brief.

**The mewa of the year of age by the natal mewa** (pp. 405–408, img.
415–418): born under 2 black (on 3, 4, 5, 7, 6, 1 or 8, 9), 3 blue (2, 4,
5, 7, 6 or 8, 1 or 9), 4 green (2, 3, 4, 5, 7, 1 or 8, 6 or 9), 5 yellow
(2, 3, 4, 5, 7, the whites or 9), 7 red (2, 3, 4, 5, 7, the whites or 9),
the three whites (2, 3, 4, 5, 7, the whites, 9), 9 red (2, 3, 4, 5, 7,
the whites, 9): forty-six readings; the other pairs have none. Then the
household readings (p. 408, not built) and the mewa's four small
obstacles (pp. 408–409): on the year's mewa *khang keg*, on the natal *mal
keg* (with readings by colour), on the two-black *yul keg*, and «སྐྱེས་སྨེའི་
དགྲ་རུ་བབས་སྨེ་འམ། །… མེ་ལྕགས་ནང་ཕྲད་གྱུར་ཀྱང་ངན། །རྒྱལ་སྒོ་འགགས་པ»; then the mewa's sky
and earth doors (not built).

**The nine-multiples** (pp. 409–411, img. 419–421): of five kinds, the
first, single count, «ཕོ་བསྐོར་བྱི་བ་… གྱེན་དུ་བརྩི་བར་བྱ་བ་སྟེ། །དགུ་གཅིག་དང་པོ་
འབྲུག་ལ་སྡོད།» for men (9 dragon li, 18 sheep khon … 81 dragon li) and
«མོ་བསྐོར་… བྱ་ནས་གྱེན་དུ་འདྲེན་པ་སྟེ། །དགུ་གཅིག་དང་པོ་གླང་ལ་སྡོད།» for women (9 ox
kham, 18 dragon khen … 81 ox kham), each age with its reading, and the
"great disgrace" ages (men 9, 36, 63; women 18, 45, 54). The other four
kinds and the tomb signs (pp. 411–425) are not built.

## The rest of the obstacle years (pp. 409–416, img. 419–426)

Read 2026-10-09 with Yigdzin-1 and MITRA (img. 422–425 compared; every
flag MITRA's), built the same day.

**Aspects in decline** (p. 409): «སྲོག་གུད་དོ་ཤི་དོ་ཆད་འོང་། … ལུས་གུད་མྱ་ངན་རྨ་དང་
ནི། … དབང་གུད་ནོར་གོད་འོག་རྟ་འཆི། … ཀླུང་གུད་མ་ཉེས་ཁ་ཡོགས་འོང་།»: each aspect "in
decline", read here as in one of the six bad sectors, with its harm and
remedy. **The mewa's doors** (p. 409): «སྨེ་བའི་ས་སྒོ་གནམ་སྒོ་ནི། །གཉིས་ནག་ས་སྒོ་
དྲུག་དཀར་གནམ། །སྤྱི་དང་ཁྱད་པར་ཕོ་ཡི་ཡིན། །མོ་གནམ་གཅིག་དཀར་ས་སྒོ་ནི། །སྨེ་བ་བཞི་ལྡང་
བབས་ན་ཡིན།»; WB gives the doors' readings with the progressed sign (p. 387),
which the app quotes for them.

**The nine-multiples** (pp. 409–415): of five kinds, the single (built in
block 13), the combined, the trigram's, the mewa's and the clan's. The
meeting of a nine-multiple with the tomb (p. 410): «ཕོ་ཡི་བྱ་སྤྲེལ་ང་བཞི་གིན། །
གཤེད་བཞི་ཕྱི་ཕག་དོན་གཉིས་ཟོན། །སྟག་ཡོས་བཅོ་བརྒྱད་ཁོན་ལ་བབས། །རྟ་སྦྲུལ་སོ་དྲུག་ཁེན་ལ་
བབས། །མོ་ཡི་རྟ་སྦྲུལ་བཅོ་བརྒྱད་ཁེན། །སྟག་ཡོས་སོ་དྲུག་ཁོན་ལ་བབས། །གཤེད་བཞི་ཕྱི་ཕག་ང་བཞི་
ཟོན། །བྱ་སྤྲེལ་དོན་གཉིས་གིན་ལ་བབས། །དེ་རྣམས་དུར་དང་དགུ་མིག་འདོམ།» (Dorje, p. 115, the
same). The combined (p. 412): «ས་ཆུ་ཤིང་ཕོ་བྱི་བ་ནས། །ཤིང་རྣམས་མེ་མོ་ཡོས་བུ་དང་། །
མེ་རྣམས་ལྕགས་ཕོ་རྟ་ནས་བརྩི། །ལྕགས་རྣམས་ཆུ་མོ་བྱ་ཐོག་ནས། །གྱེན་ལ་བརྩི་བྱའི་ལོ་གྲངས་
བགྲངས། །དུར་ཐོག་ཕྱིན་ན་སྦྲགས་མར་ཚུད། །ལོ་དགུ་ཉེར་གཅིག་སོ་གསུམ་དང་། །ཞེ་ལྔ་ང་བདུན་རེ་
དགུ་དང་། །གྱ་གཅིག་སླེབ་དེའི་འབྱུང་བ་རྣམས། །ཡོད་ཚད་དུར་ཚུད་དགུ་མིག་འབབ།», for each of
vitality, body, destiny, luck, la and clan; the app reads the four
aspects. Each key, counted up nine, does reach its element's tomb
(`YearOfLifeTest`). Its readings by element and by first to fifth, the
sixth and seventh like the first and second (pp. 414–415: «སྦྲགས་མ་བྱི་ཕག་
གཤེད་བཞི་ཡི། །དགུ་མིག་དང་པོ་བདུད་ཀྱིས་འདེབས།» … «དྲུག་བདུན་དང་པོ་གཉིས་པར་མཚུངས།», then
wood, fire and iron). The trigram's (p. 413): «ཕོ་ལི་མོ་ཁམ་སྟེང་ཕྱིན་ཡིན»; the
mewa's: «སྐྱེས་སྨེ་དབུས་ཚུད་སྟེང་། །བབས་པའི་ཕོ་མོ་གཉིས་ལའོ།», its readings by kind of
person given as «ཞེས་པར་འདོད་པ་འགའ་ཞིག་ཡོད», what some hold: not built. The
clan's needs a clan: not built. The general remedies (p. 414): lamps,
food offerings, clay votive images, the Uṣṇīṣa and water tormas, nine of
each, an arrow for a man and a spindle for a woman, nine stones and nine
prostrations.

**The tombs** (p. 412): of the eighty tomb houses, «དབང་ཐང་གཅིག་པ་རང་དུར་དང་། །
དབང་ཐང་དགྲ་ནི་གཤེད་དུར་འདོད། །དེ་ཡང་རང་གཤེད་དུར་ལོ་ཆེ། །དེ་ཡི་བདུན་ཟུར་ཆུང་དུར་འདོད། །
ཕྱི་གཟར་བབས་ཀྱང་ཅུང་ཟད་ངན། … དཔེར་ན་དབང་ཐང་ཤིང་། །སྟག་ཡོས་རང་དུར་ཆེ་བ་ལུག །ཆུང་བ་
ཤིང་གླང་གཤེད་ཀྱི་དུར། །ཆེ་བ་ལྕགས་ལུག་ཆུང་ལྕགས་གླང་།»: the tomb year of one's own
destiny element and of its slayer, great on the tomb animal and small on
its seventh; the progressed sign (*phyi gza'*, p. 387) on one "slightly
bad". «དུར་མི་བཞི་ནི་ཆུ་འབྲུག་དང་། །ཤིང་ལུག་མེ་ཁྱི་ལྕགས་གླང་ཡིན། །དེ་དག་རང་ཁམས་དུར་ཤར་
ངན།»: the four black undertakers. Dorje (p. 117) has the tomb years at fixed
ages (21, 51, 45, 15) from twenty starting points; WB's own example is the
destiny's element and its slayer, which the app follows. The tomb days
(«ཕོ་ཚེས་དྲུག་དང་བཅོ་བརྒྱད་དང་། །སྟོང་ལ་ལུག་འོང་», the 6th, 18th and 30th) and the
great-disgrace tombs of the first, middle and last months have no reading
and are days, not years: not built.

**The tomb sign** (p. 415): «དུར་མིག་སྲོག་དང་བབས་སྤར་སྤྲད། །སྲོག་ཤིང་ཁོན་དང་མེ་སྲོག་
ཁེན། །ལྕགས་པོ་གིན་ལ་ས་ཆུ་ཟོན། །སྐོར་སྤར་བབས་ན་དུར་མིག་ཚུད།», with its remedies.

**What ends the chapter** (p. 416, img. 426): the obstacle years close
with WB's colophon line; then the Chinese pebble divination (*rgya nag
rdel*, pp. 416–452), a casting of pebbles, not a reckoning.

## The birth reckoning and the natal trigram (RD13)

WB vol. 1, ch. 22, *the reckoning of lives and karma* (ཚེ་རབས་ལས་རྩིས, tshe
rabs las rtsis), pp. 259–350 (img. 269–360; printed page = image − 10,
checked on img. 269 = p. 259 and img. 360 = p. 350); the notes' section 1
(vol. 2, I1KG12907, pp. 474–489, img. 482–497, image = page + 8); the
*Moonbeams* (DZ) in Dharmaśrī's *Gsung 'bum*, vol. 5 (I2533, open,
4800 px), with KD vol. 3's etext (VEIE0OPI2F246A26_I4717) to find the
folios. Read 2026-10-12.

**How read.** Yigdzin-1 over every page of the chapter and of the notes'
section; MITRA as witness over the quoted pages; the quoted passages read
again on the scans at 2.6–8× (img. 269, 270, 271, 272, 281–282, 304,
336, 357; vol. 2 img. 482, 484; DZ img. 021, 027). The *Moonbeams* was read
on the open scan alone: its type is clean and large, so the archive.org
loan of KD vol. 3 was not taken. The outline of the rest of the chapter
is from BDRC's etext (IE0OPI51524892, which garbles this chapter badly)
and Yigdzin-1, **not read on the scan**. Yigdzin-1 drops lines here as on
the verse pages of vol. 2: on img. 357 it left out five lines of the
children of the gods, glory, life, spirits and māras, which MITRA and the
scan give (below), on img. 271 and 336 it ran lines together, and of the
notes' img. 482 it read only the title (MITRA read the page). MITRA agreed
on 89–96% of the syllables of the quoted pages but 72% on img. 336, where
it reads khon for the print's khen five times (checked at 5×). Every flag
of `disagree.py` inside a quoted passage was read on the scan; the quotes
follow the scan.

### The natal trigram: the mother's trigram at the birth

**WB** (p. 261, img. 271, read on the scan), the five unchanging luck
pebbles (མི་འགྱུར་ཀླུང་རྡེལ, mi 'gyur klung rdel):

> མི་འགྱུར་ཀླུང་གི་རྡེལ་ལྔ་ནི། །བརྩི་བྱའི་ཕ་ལ་གང་ཡིན་དེ། །བརྩིས་ལ་ཕ་ཀླུངས་གང་ཡིན་དང་། །མ་ལོ་བརྩིས་པའི་མ་ཀླུངས་གསུམ། །མ་ཡི་ལོག་མིན་གར་བབས་དང་། །སྐྱེས་དུས་བབས་པའི་སྤར་སྨེ་ཡིན། །བུ་མོ་ཡིན་ན་ཕ་ལོའི་ཚབ། །མ་ཡི་ལོས་བྱ་མ་ཀླུངས་བཞི། །ཟླ་བ་གང་ལ་སྐྱེས་པའི་རྡེལ། །མི་འགྱུར་ཀླུང་གི་རྡེལ་ལྔ་ཡིན།

The five unchanging luck pebbles: reckon what belongs to the father of
the one reckoned, whatever the father's luck (ཕ་ཀླུང, pha klung) is;
then three mother's luck pebbles (མ་ཀླུང, ma klung), reckoned from the
mother's year: where the mother's progressed sign (ལོག་མིན, log min; the
*phyi gza'* of ch. 24) has fallen, and the trigram (སྤར་ཁ, spar kha) and the
mewa (སྨེ་བ, sme ba) that fell
at the time of the birth. For a daughter the mother's year takes the
father's place: four mother's luck pebbles. And the pebble of the month
of the birth. These are the five. (The print has «ལོག་མིན» here, with the
gi gu, checked at 8×; ch. 24 has «ལོག་མེན».)

**The notes** (vol. 2, p. 474, img. 482, read on the scan) say it in
prose: «མི་འགྱུར་ཀླུང་རྡེལ་ལྔ་ནི། བུ་ཡིན་ན་ཕ་ལོ་དང༌། སྐྱེས་དུས་ཀྱི་མ་ཡི་ལོག་མིན་སྤར་ཁ་སྨེ་བ་གསུམ་དང༌། སྐྱེས་པའི་ཟླ་བ་དང་བཅས་པ་ལྔ་ཡིན། བུ་མོ་ཡིན་ན་ཕ་ལོའི་ཚབ་ཏུ་མ་ལོ་བཞག་པས་མ་ཀླུང་བཞི་སྐྱེས་ཟླ་དང་ལྔའོ།»:
for a son, the father's year; the mother's progressed sign, trigram and
mewa at the time of the birth, three; and the month of the birth: five.
For a daughter the mother's year is put in place of the father's: four
mother's luck pebbles and the birth month, five. And what the chapter's
readings of "the trigram one is born on" mean (p. 476, img. 484, read on
the scan):

> སྤར་ཁ་གང་སྐྱེས་བཤད་པ་ནི་མ་ཀླུང་། སྨེ་བ་ནི་ལུས་སྨེ་སྟེ་སྐྱེས་སྨེར་གྲགས་པ་དེའི་འབྲས་བཤད་གཞུང་བཞིན་ནོ།

The trigram one is born on, where the text reads it, is the mother's
luck; the mewa is the body mewa (ལུས་སྨེ, lus sme), the one known as the
natal mewa (སྐྱེས་སྨེ, skyes sme), whose readings are as the text gives
them. So the natal trigram (སྐྱེས་སྤར, skyes spar) is the mother's, while
the natal mewa of the readings is the child's own year's mewa (ch. 21,
p. 255, above), not the mother's.

**The *Moonbeams*** (DZ, I2533 img. 021, f. 9a, lines 5–6, read on the
scan; KD vol. 3, p. 494, etext p. 514) gives the count:

> བྱིས་པའི་སྐྱེས་སྤར་མ་ཀླུང་སྟེ། །དེ་ཉིད་བདེ་བླག་འཚོལ་འདོད་ན། །མ་ལོའི་སྟེང་ནས་བུ་ཡི་ལོས། །འཕྲི་ལྷག་སྟེང་དུ་གཅིག་བསྣན་པའི། །གྲངས་དེར་མ་ཡི་སྤར་ཁ་བསྐོར། །མ་དེ་གར་བབས་སྤར་ཁ་དེ། །བུ་དེ་སྐྱེས་པའི་སྤར་ཁ་ཡིན།

The child's natal trigram is the mother's luck. To find it easily: take
the child's year from the mother's year, add one to what is left, turn
the mother's trigram that many times; the trigram the mother lands on is
the trigram the child is born on. The difference of the two years plus
one is the mother's year of age in the year of the birth, so the natal
trigram is the mother's trigram of her year of age (the woman's circuit
from kham towards khen, ch. 21, p. 257, above) in the child's birth year,
as Dorje has it. The lines just before (f. 8b–9a by the KD etext, p. 493)
are the circuit with «བཅུ་ཁ་མ་ལོངས་རེ་རེ་སྟེ། །བཅུ་ཁ་ལོངས་ནས་ཟུར་ལ་མཆོང་།», one a
year until each ten and then a leap to the corner; WB counts one a year
(above). Dorje's "f. 12a" is not this print's folio: here f. 12a (img.
027, read on the scan) holds the five nine-multiples («རྐྱང་སྦྲགས་སྤར་སྨེ་རུས»,
single, combined, trigram, mewa and clan), and the natal trigram is on
f. 9a.

**What else ch. 22 asks for at the birth** (p. 270, img. 270, read on the
scan): some reckon the clan's element from the natal trigram, and WB
rejects it: «འགའ་ཞིག་སྐྱེས་སྤར་ཁམས་བརྩི་བའི། །ལུགས་ལ་བཞེད་པར་ཡོད་ན་ཡང་། །རྣམ་པར་དག་པའི་རིང་ལུགས་མིན།»,
"though some hold to the way of reckoning the element by the natal
trigram, it is not the pure tradition"; the clan's element is asked (the
notes, p. 474, reckonings.md). The father's trigram at the birth is used
too: the notes ask first for «ཕ་མའི་བབ་སྤར», the trigrams the father and
the mother have fallen on (p. 474, img. 482, read on the scan), and
p. 326 reads the two together (below).

**What the reckoning needs, for whom** (from pp. 259–262 and the notes,
pp. 474–478, img. 482–486; the notes' pp. 475–478 by Yigdzin-1, read on
the scan where quoted):

| Input | Used for |
| --- | --- |
| The child's birth year, month, day and hour | the four precious pebbles; the rise and decline of the life force, body, power and luck on each (p. 261) |
| The child's gender | a daughter's luck pebbles (p. 261) |
| The mother's birth year | her progressed sign, trigram and mewa in the birth year: the mother's luck; the natal trigram |
| The father's birth year | the father's luck (p. 261); his trigram at the birth (p. 326); *dpal byur*, his life force and luck against the child's (notes, p. 476) |
| The father's and the grandfather's birth years | the line of births (རྒྱ་མ, rgya ma): their rise and decline counted onto the child's year (notes, pp. 476–477, Yigdzin-1) |
| The clan's element | the special pebble (p. 261); the line's "door closed" (notes, p. 477) |
| The household's years (གཟའ་ཚང, gza' tshang) | the line of births and its faults counted over all its members, the eldest and youngest above all (notes, p. 477, Yigdzin-1) |
| The moment of reckoning | one of the two indispensable pebbles, in the way of the master Khyung (p. 261) |

### The pebbles of the birth (pp. 259–262)

**The count** (p. 259, img. 269, read on the scan): others expand it to
about 15,890; WB keeps 641 root pebbles (རྩ་རྡེལ, rtsa rdel) and 2,537
identifying sign pebbles, 3,178 in all:

> རྡེལ་གྲངས་གཞན་ལྟར་སྤྲོས་ཆེ་ན། །ཁྲི་དང་ལྔ་སྟོང་བརྒྱད་བརྒྱ་དང་། །དགུ་བཅུ་ཙམ་དུ་འབྱོར་ནའང་། །དགོས་མེད་སྤྲོས་པ་དོར་བ་ཡི། །རྩ་རྡེལ་དྲུག་བརྒྱ་ཞེ་གཅིག་དང་། །བརྩི་དུས་མི་ལུས་ཁྱད་པར་གྱི། །ངོས་འཛིན་མཚན་མའི་རྡེའུ་ནི། །ཉིས་སྟོང་ལྔ་བརྒྱ་སོ་བདུན་ཏེ། །བསྡོམས་པས་སུམ་སྟོང་ཆིག་བརྒྱ་དང་། །བདུན་ཅུ་རྩ་བརྒྱད་གཏན་ཕབ་པར།

The page then names the five great clans (རུས་ཆེན, rus chen) by element,
*keg* wood, *ji* fire, *kungs* earth, *shang* iron, *'u* water, with
their family names (pp. 259–260, Yigdzin-1, not checked name by name).

**The root of 136** (p. 260, img. 270, read on the scan), after the
*Precious Lamp* (རིན་ཅེན་གསལ་སྒྲོན, rin chen gsal sgron):

> དེ་ཡང་རིན་ཅེན་གསལ་སྒྲོན་ལས། །བརྒྱ་རྡེལ་རིམ་པ་འདི་ལྟར་བཤད། །གཅེས་རྡེལ་བཞི་ལ་སྒོས་རྡེལ་གཅིག །མེད་དུ་མི་རུང་རྡེལ་གཉིས་ཏེ། །མི་འགྱུར་ཀླུང་གི་རྡེལ་ལྔ་ཡིན། །རྒྱ་འདྲེའི་རྡེལ་གྲངས་བརྒྱ་ཕྲག་གཅིག །བཏང་གཅོད་རྡེལ་ནི་ཉི་ཤུ་བཞི། །སྤྱིར་བསྡུས་བརྒྱ་དང་སུམ་ཅུ་དྲུག །ངེས་པར་གཏན་ཕབ་བརྩི་བ་སྟེ།། གཅེས་རྡེལ་བཞི་རུ་གྲགས་པ་ནི། །གནམ་ལོ་རྒྱལ་པོའི་རྡེལ་གྱིས་གཅིག །ཟླ་བ་བློན་པོའི་རྡེལ་དང་གཉིས། །ཞག་པོ་དམག་མིའི་རྡེལ་གྱིས་གསུམ། །དུས་ཚོད་མཚོན་ཆའི་རྡེ་འུས་བཞི། །འདི་རྣམས་གཅེས་རྡེལ་བཞི་རུ་གྲགས།།

Four precious pebbles (གཅེས་རྡེལ, gces rdel), one special pebble (སྒོས་རྡེལ,
sgos rdel), two indispensable ones (མེད་དུ་མི་རུང་རྡེལ, med du mi rung
rdel), five unchanging luck pebbles, a hundred, and twenty-four decisive
pebbles (བཏང་གཅོད, btang gcod): 136. The four precious ones are the birth
year as king, the month as minister, the day as soldier and the hour as
weapon. (p. 261 counts the decisive pebbles as twenty, below.)

**The colours** (p. 260, img. 270; Yigdzin-1, the lines checked on the
scan in part): «དེ་ལ་གནད་ཀྱི་རྡེའུ་ནི། །སྟག་ནས་སྦྲུལ་བར་ནག་པོར་འཕེལ། །རྟ་ནས་བྱ་བར་དཀར་པོར་འཕེལ། །ཁྱི་ལ་དཀར་གཅིག་ནག་པོ་གཅིག །ཕག་ལ་དཀར་གཉིས་ནག་པོ་གཅིག །བྱི་བ་ནག་གཉིས་དཀར་པོ་གཅིག །གླང་ལ་དཀར་ནག་གཉིས་རེ་སྟེ། །ལོ་ཟླ་ཞག་དུས་ཀུན་ལ་སྦྱར། །ཆུ་ནས་ལྕགས་བར་གཅིག་ནས་ལྔ། །ཟིན་ནས་ལི་བར་ནག་པོར་འཕེལ། །ཁོན་ནས་ཁེན་བར་དཀར་པོར་འཕེལ། །ཁམ་ལ་དཀར་གཉིས་ནག་པོ་གཅིག །གིན་ལ་ནག་གཉིས་དཀར་པོ་གཅིག །སྨེ་བ་དཀར་གསུམ་དཀར་པོར་འཕེལ། །ནག་ཐོན་ལྗང་གུ་ནག་པོར་འཕེལ། །ལྔ་སེར་དཀར་ནག་ཕྱེད་མ་སྟེ། །བདུན་དམར་དཀར་གཉིས་ནག་པོ་གཅིག །དགུ་དམར་ནག་གཉིས་དཀར་པོ་གཅིག །ཆད་ཆེན་ལ་ནི་ནག་པོ་གསུམ། །ཆད་ཆུང་ལ་ཡང་ནག་པོ་གཉིས། །མ་ལོ་དཀར་གསུམ་དབང་ལོ་གཉིས། །བུ་ལོ་དཀར་གཅིག་གྲོགས་ལོ་ཕྱེད། །དགྲ་ལོ་ནག་པོ་གསུམ་དུ་བཞག །ཁ་ཡན་ལ་ནི་དཀར་པོ་གསུམ། །ཁོང་ནོང་ལོ་ལ་དཀར་པོ་གཉིས། །སེ་ཞིག་ལ་ནི་དཀར་པོ་གཅིག །ཁ་རལ་དཀར་ནག་ཕྱེད་མ་སྟེ། །འདུན་ཁུར་ལ་ནི་ནག་པོ་གསུམ། །མི་འཁྲུལ་གནད་ཀྱི་རྡེལ་ཡིན་ནོ།»
The key pebbles for each sign of the birth: of the year, month, day and
hour animals, black growing from the tiger to the snake and white from
the horse to the bird, the dog one white and one black, the pig two white
and one black, the mouse two black and one white, the ox two of each; of
the trigrams (li ལི, khon ཁོན, dwa དྭ, khen ཁེན, kham ཁམ, gin གིན, zin ཟིན,
zon ཟོན, ch. 21 above), black growing from zin to li and white from khon to khen,
kham two white and one black, gin two black and one white; of the mewa,
white growing over the whites, black over black, blue and green, the
five yellow half and half, the seven red two white and one black, the
nine red two black and one white; of the special pebble, the great
penalty three black, the small two black, the mother's year three white,
the power's two, the son's one, the friend's half, the enemy's three
black; of the power's year, *kha yan* (ཁ་ཡན) three white, *khong nong*
(ཁོང་ནོང) two, *se zhig* (སེ་ཞིག) one, *kha ral* (ཁ་རལ) half, *'dun khur*
(འདུན་ཁུར) three black. Then an easier
way with pebbles of five colours, the identifying pebbles.

**The special pebble** (p. 261, img. 271, read on the scan): the birth
year set against the clan's element, as its great penalty (ཆད་ཆེན, chad
chen), small penalty (ཆད་ཆུང, chad chung), power year, mother, son,
friend or enemy year. For each clan:

| Clan, element | Great penalty | Small penalty | Power | Mother | Son | Friend | Enemy |
| --- | --- | --- | --- | --- | --- | --- | --- |
| *kungs*, earth | dragon | dog | ox, sheep | horse, snake | bird, monkey | mouse, pig | tiger, hare |
| *ji*, "the minister", fire | dog | dragon | horse, snake | tiger, hare | ox, sheep | bird, monkey | mouse, pig |
| *keg*, "the queen", wood | sheep | ox | tiger, hare | mouse, pig | horse, snake | dog, dragon | bird, monkey |
| *'u*, "the thief", water | dragon | dog | mouse, pig | bird, monkey | tiger, hare | horse, snake | ox, sheep |
| *shang*, "the slanderer", iron | ox | sheep | bird, monkey | dog, dragon | mouse, pig | tiger, hare | horse, snake |

The great penalty is the element's tomb animal (ch. 21, above), the small
its seventh.

**The two indispensable pebbles** (p. 261, read on the scan): the power
year, *kha yan*, *khong nong*, *se zhig*, *kha ral* or *'dun khur*, by how the year's power stands to its
life force (the same element, the life force's mother, its son, friend
or enemy); and the pebble of the time: some put it at the hour of the
birth, but in the way of the master Khyung (ཁྱུང, khyung) it is the time of reckoning
(«ཁ་ཅིག་འདི་ཡང་སྐྱེས་དུས་ལ། །བཞེད་ཀྱང་མཁས་དབང་ཁྱུང་གི་ལུགས། །བརྩི་བའི་དུས་སྦྱར་རྡེའུ་གཅིག»).

**The hundred, the rise and decline and the decisive twenty** (p. 261,
read on the scan): the five unchanging luck pebbles are each fought by
the life force, body, power and luck, each divided into own, mother,
son, friend and enemy: twenty-five pebbles of each, a hundred. Then four
of rise and decline (དར་གུད, dar gud): the life force's on the birth
year, the body's on the month, the power's on the day, the luck's on the
hour; the six good stages white, the six bad black; the year for the
first part of life, the month for the next (the print's word, «ཚེ་སྨང»
with a mark under it, is not clear at 8×), the day and hour for the last:
«བཟང་དྲུག་དཀར་པོ་ངན་དྲུག་ནག །གནམ་ལོས་ཚེ་ཡི་སྟོད་ཀྱི་ས། །ཟླ་བ་ཚེ་སྨང་ཞག་དུས་ཀྱིས། །ཚེ་སྨད་སྐྱིད་སྡུག་འབྲས་བུ་བརྟག»
Then twenty decisive pebbles: the white and black of the life force,
body, power and luck counted, a pebble laid on the greater, a red mark
(ཁ་དམར, kha dmar) on each. The new school (གསར་མ, gsar ma) of reckoners
puts the life force first, the old (རྙིང་མ, rnying ma) the power, "in
application there is no difference".

**The breakdown of 3,773** (p. 262, img. 272, read on the scan line by
line at 3.2×; the doubt of reckonings.md):

> །གསལ་སྒྲོན་རྩ་བའི་རྡེལ་བབས་ཏེ། །ལེ་ལག་འཐོར་བ་རྣམས་བསྡུས་ཤིང་། །རྒྱུད་ཁུངས་གཞན་གྱི་དགོངས་པ་སོགས། །གཅིག་བསྡུས་ཚེ་རབས་ལས་རྩིས་ལ། །བཀླགས་པས་གོ་ཤེས་བསྟན་བཅོས་འདིར། །སྤུག་སོགས་མཁས་དབང་གཞན་ལུགས་ལྟར། །སྤྲོས་ཏེ་རྡེལ་གྲངས་མང་འདོད་ན། །ཉི་ཁྲི་ཉིས་སྟོང་སུམ་བརྒྱ་དང་། །བརྒྱད་ཅུ་ལ་སོགས་འབྱུང་ན་ཡང་། །ལྷག་པོ་སྤྲོས་པ་མ་ཡིན་པའི། །འབྲས་བུ་དངོས་ཀྱི་རྡེའུ་དང་། །བརྩི་དུས་མི་ལུས་ཁྱད་པར་གྱི། །ངོས་འཛིན་མཚན་མའི་རྡེའུ་རྣམས། །ཡོངས་བསྡོམས་རྡེའུ་སུམ་སྟོང་དང་། །བདུན་བརྒྱ་དོན་གསུམ་གཏན་ཕབ་པར། །རྡེལ་བབས་བལྟས་ལ་ཁ་དམར་གདགས། །གཅེས་རྡེལ་མི་འབྱུང་ཀླུང་རྡེལ་དགུ། །སྒོས་རྡེལ་མེད་མི་རུང་བ་རེ། །ཟླ་ཞག་དུས་དབང་རྡེའུ་བཞི། །སྲོག་ལུས་དབང་ཀླུང་དང་བཅས་པར། །རང་མ་བུ་གྲོགས་དགྲ་བཅས་པའི། །རྡེའུ་བརྒྱ་དང་ཉི་ཤུ་དང་། །དར་གུད་ཞག་མལ་རྡེའུ་བཞི། །ངོ་བོ་བརྒྱ་དང་ལྔ་བཅུ་ཐམ། །གཅོད་འདྲལ་བཟང་ངན་ངོས་འཛིན་རེ། །ལྷ་དཔལ་ཚེ་བདུད་རྡེའུ་བརྒྱད། །དཔལ་བྱུར་གཉིས་ལ་རྡེའུ་བཞི། །དེ་ཡན་བརྩི་དུས་མི་ལུས་པའི། །མཚན་མའི་རྡེའུ་སུམ་བརྒྱ་གཅིག །རྒྱ་མར་དངོས་རྡེལ་ང་བཞི་དང་། །མཚན་མའི་རྡེའུ་བརྒྱད་བརྒྱ་དང་། །བཅུ་གཉིས་གཟའ་བཞི་མ་ཀླུངས་ལ། །དངོས་རྡེལ་གོ་བཞི་ངོས་འཛིན་གྱི། །མཚན་མའི་རྡེའུ་ཆིག་སྟོང་དང་། །ཆིག་བརྒྱ་གྱ་གཅིག་ལོ་ངན་ལ། །རྩ་བའི་རྡེའུ་ཉི་ཤུ་ལྔར་རྡེའུ་གཅིག །མཚན་མའི་བཅུ་དང་ངན་ཀླུངས་ཀྱི། །སྒོ་ལྔར་དངོས་རྡེལ་དྲུག་དང་ནི། །ངོས་འཛིན་སོ་བརྒྱད་བརྒྱ་རྡེལ་གྱི། །ངོས་འཛིན་བཅོ་ལྔ་དར་གུད་ཀྱི། །འབྲས་བུ་ངོས་འཛིན་ཞེ་བརྒྱད་དང་། །ཀླུང་གི་ངོས་འཛིན་མཚན་ཅན་ལྔ། །འབྲས་བུ་དངོས་རྡེལ་བཅོ་བརྒྱད་དང་། །ངོས་འཛིན་བརྒྱ་དང་བཞི་བཅུ་གཉིས། །མིག་ཚགས་ངོས་འཛིན་ལྔ་བཅུ་དྲུག །འདོམ་དཔྱད་ཡ་རབས་བརྒྱུད་སོགས་པའི། །དངོས་རྡེལ་བཅུ་དྲུག་ངོས་འཛིན་གྱི། །མཚན་ཅན་སུམ་ཅུ་སོ་གཉིས་དང་། །གསང་བ་ཡང་ཏིག་ཉི་ཤུ་གཅིག །མཚན་ཅན་བརྒྱ་དང་བཅོ་བརྒྱད་དང་། །བུས་པ་བཙས་རྩིས་དངོས་རྡེའུ། །བརྒྱ་དང་རེ་དགུ་ངོས་འཛིན་རྡེལ། །ལྔ་བརྒྱ་དོན་ལྔ་རྣམས་བོར་བའི། །དེ་ཡང་ཁ་དམར་རིམ་པ་དང་། །རྡེལ་ཕྲན་ངོ་ཐོག་བཅས་པ་རྣམས། །རིམ་པར་བཀོད་ལ་ཐོག་མར་ནི།

Gathering the root pebbles of the *Clear Lamp* (གསལ་སྒྲོན, gsal sgron), the
scattered answers and the views of other tantras into this reckoning of
lives: others, Phug pa (སྤུག, spug) among them, expand it to 22,380 and
more; WB fixes 3,773 result pebbles and identifying pebbles, laid out as
follows (the parts, in the order of the readings that follow):

| Part | Result pebbles | Identifying |
| --- | --- | --- |
| The essence (ངོ་བོ, ngo bo), 150 in all: precious and luck nine; the special and the indispensable, one each; month, day, hour and power, four; the 120 of life force, body, power and luck by own, mother, son, friend and enemy; the four of rise and decline | 150 | — |
| The good and bad of the cutting and tearing (གཅོད་འདྲལ, gcod 'dral); the gods, glory, life and māras (ལྷ་དཔལ་ཚེ་བདུད, lha dpal tshe bdud), eight; glory and misfortune (དཔལ་བྱུར, dpal byur), four | — | 301 "up to here" |
| The line of births (རྒྱ་མ, rgya ma) | 54 | 812 |
| The four planets (གཟའ་བཞི, gza' bzhi) and the mother's luck | 94 | 1,181 |
| The bad years: one to each of the 25 roots | 25 | 10 |
| The five doors of bad luck | 6 | 38 |
| The hundred pebbles' identifying | — | 15 |
| The results of rise and decline | — | 48 |
| The luck's | — | 5 |
| The results | 18 | 142 |
| The *mig tshags* (མིག་ཚགས, mig tshags; not identified) | — | 56 |
| The examination of the *'dom* (འདོམ་དཔྱད, 'dom dpyad; not identified), the noble line (ཡ་རབས་བརྒྱུད, ya rabs brgyud) and the like | 16 | 32 |
| The secret *yang tig* (གསང་བ་ཡང་ཏིག, gsang ba yang tig) | 21 | 118 |
| The child's birth reckoning (བུས་པ་བཙས་རྩིས, bus pa btsas rtsis) | 169 | 575 |

As read, the result pebbles make 553 and the identifying ones 3,333,
3,886 in all, 113 more than the 3,773 the page states; and p. 259 (above)
gives 3,178. The word after «གཅེས་རྡེལ» reads «མི་འབྱུང» on the scan at 6×,
but the nine it counts are the four precious and the five *unchanging*
(«མི་འགྱུར») luck pebbles. The numbers are read as the print writes
them: «དོན་གསུམ» 73, «དོན་ལྔ» 75, «གོ་བཞི» 94, «ང་བཞི» 54, «གྱ་གཅིག» 81.

Then the readings begin (p. 262, read on the scan), the four precious
pebbles first: the year as king, white, the king's word not blamed, a man
winning in the assembly, a woman hearing good talk; black, the king's
punishment, more suffering than joy, illness never ceasing; the month as
minister, the day as soldier and the hour as weapon likewise, each white
and black.

### Readings by the natal trigram

**By the paired-year element at the birth** (pp. 271–275, img. 281–285):
the thirty paired-year elements (ཁ་སྲོག, kha srog), from the wood mouse and
ox on, each with the part of the turtle (རུས་སྦལ, rus sbal) it comes from,
its body, the forefathers' fault, the illness, the present harm, and
then the birth "on" a sign of the mother. The first, read on the scan
(img. 281, the last lines, to img. 282, l. 3):

> ཁ་སྲོག་སུམ་ཅུ་ཐམ་པ་ལས། །ཤིང་ཕོ་བྱི་དང་ཤིང་མོ་གླང་། །རུས་སྦལ་དཔྱི་དང་བརླ་ལས་ཆད། །རྡོ་ལས་བཞུ་བའི་ལྕགས་ཀྱི་ལུས། །ཕ་མེས་གོང་མའི་རིང་ལ་ནི། །ལྕགས་རྫས་ཅན་ལ་སྙད་ཐོགས་ནས། །རོ་སྨད་བཤལ་དང་བརླ་དཔྱི་ན། །དྲེག་དང་གྲུམ་བུ་མདས་འཕོག་འཐེང་། །དམུ་འོར་ནད་ཀྱིས་ཤི་བ་འབྱུང་། །ཁྱིམ་ས་བྱེ་ཞིག་བྱེད་པ་དང་། །བུ་ཚ་ལྷག་ཆད་འོང་བ་དང་། །དྭ་བོར་ལུས་པའི་བརྒྱུད་ཡིན་ཏེ། །དེ་ལྟར་རང་འཐག་འདྲེ་ཅན་ནི། །ཁྱིམ་ན་ཡོད་པས་རུས་འདྲེ་གནོད། །དེ་ཡང་སྐྱེས་དུས་དགུ་མིག་གམ། །མ་དེ་ལྕགས་སམ་དྭ་ཡི་སྟེང་། །བཙས་ན་རྐང་ཞབས་སྐ་རགས་མན། །ནད་ལྡང་སྤར་སྨེ་གསོ་སྐྱེད་བཙས། །རྐང་མགྱོགས་དབང་པོ་མ་ཚང་འབྱུང་།

Of the thirty: the wood mouse and ox come from the turtle's hip and
thigh, a body of iron melted from stone. In the forefathers' time a
fault was done against something of iron; so diarrhoea of the lower
body, pain of the hips and thighs, gout, lameness, an arrow wound, death
by dropsy; the house falling to ruin, children dying young, a line left
orphaned; a spirit of one's own weaving in the house, so the clan's
spirit harms. And if at the birth it is a nine-multiple, or the mother is
on iron or on dwa, the child is weak in the feet and the loins; born on a
trigram or mewa that nourishes it, illness rises but it is quick of foot
[the reading of «ནད་ལྡང་… རྐང་མགྱོགས» is uncertain], with a sense faculty
lacking. Every entry closes the same way with the mother's sign at the
birth (Yigdzin-1, the lines checked in part on img. 282): «མ་དེ་ཆུའམ་ཁམ་
བཙས་ན», «སྤར་ཁ་ཟིན་ནམ་མ་ཤིང་ལ། །བཙས་ན», «ཁམ་མམ་མ་ཆུ་ལ་བཙས་ན», «ཁོན་ནམ་ཨ་མ་
ས་པར་བཙས», «མ་དེ་གཤེད་བཞི་ས་ཁམས་སམ། །སྤར་ཁ་ཟུར་བཞི་ལ་སྐྱེས་ན» (p. 275):
the mother's trigram is named with the element it carries (dwa iron,
zin wood, kham water, khon earth, li fire), and the "four slayers" and
"four corners" are the earth animals and the corner trigrams.

**The parents' trigrams at the birth** (p. 326, img. 336, read on the
scan):

> །ཕོ་ཐོག་མོ་དང་མོ་ཐོག་ཕོ། །བཙས་ན་ག་ལོག་ནམ་ཕུགས་སུ། །རབས་ཆད་ངན་ཅིང་གཞན་ཡང་ནི། །ཕ་ཟིན་མ་དྭར་བུ་བཙས་ན། །ཕ་དེ་མྱུར་དུ་འཆི་བ་ཡིན། །ཕ་གིན་མ་ཟིན་བུ་མོ་བཙས། །དེ་ཚེ་མ་དེ་མྱུར་དུ་འཆི། །མ་ནི་ཁྱི་འབྲུག་ཁེན་ཐོག་ཏུ། །བུ་སྐྱེས་ཕར་ངན་མ་ལོ་དེ། །གང་རུང་ཁེན་ཐོག་ཁྱི་འབྲུག་སྐྱེས། །ཕར་ངན་ཕ་ཁེན་བུ་ལི་ལ། །བཙས་ན་ཕར་ངན་སྔོན་དུ་བུ། །མ་ལིར་སྐྱེས་རྗེས་མ་ཁེན་བཙས། །སྔོན་མ་དྭ་ལ་རྗེས་མ་ཟིན། །ལ་སོགས་དགྲ་བདུད་བཙས་ན་ཡང་། །ཕར་ངན་མ་གླང་ལུག་ལོ་པར། །ཁོན་ཐོག་བུ་མོ་གང་སྐྱེས་ཀྱང་། །མར་ངན་མ་ལོ་གང་ཡིན་ཡང་། །ཁོན་ཐོག་བུ་མོ་གླང་ལུག་སྐྱེས། །མར་ངན་མ་དེ་ཁྱི་འབྲུག་ལོ། །ཟོན་ཐོག་བུ་ལོ་གང་སྐྱེས་ཀྱང་། །སྲིང་མོར་འབོད་རྒྱུ་མེད་པའོ། །མ་ལོ་གང་ཡིན་ཟོན་གྱི་ཐོག །ཁྱི་འབྲུག་བུ་སྐྱེས་གོང་དང་འདྲ། །མ་ནི་གླང་ལུག་གིན་གྱི་ཐོག །བུ་གང་བཙས་པ་གོང་དང་འདྲ། །མ་ནི་གིན་ཐོག་བུ་མོ་ནི། །གླང་ལུག་ལོ་སྐྱེས་མིང་པོར་ངན། །བུ་མོ་རྟ་སྦྲུལ་བྱི་ཕག་བཞི། །ཁམ་ལ་སྐྱེས་དང་སྟག་ཡོས་བྱ། །སྤྲེལ་བཞི་དྭ་ཟིན་ལ་སྐྱེས་པ། །གླང་ལུག་ཁོན་གིན་ཁྱི་འབྲུག་ཟོན། །ཁེན་སྐྱེས་བུ་མོ་སྐྱེད་པོ་བགྲང་། །མནའ་ཟའི་ཀླུང་ངན་ཉེར་དྲུག་ཅེས། །འདི་ལྟར་བརྩི་བའི་ལུགས་ཀྱང་ཡོད། །མ་བུ་གཉིས་ནི་ལི་ཁེན་སོགས། །སྤར་ཁ་དགྲ་བདུད་བསྣོལ་མར་སྐྱེས(༡)།

A girl born after a boy or a boy after a girl, alternating: in the end
the line is cut, which is bad. A son born when the father is on zin and
the mother on dwa: the father soon dies. A daughter born when the father
is on gin and the mother on zin: the mother soon dies. A son born when a
mother of a dog or dragon year is on khen is bad for the father; and
whatever the mother's year, a child of a dog or dragon year born on khen
is bad for the father. Father on khen and the son born on li: bad for the
father. An earlier child born on li and a later on khen, an earlier on
dwa and a later on zin, and the like, enemies and māras: bad for the
father too. Whatever daughter is born to a mother of an ox or sheep year
on khon is bad for the mother; and whatever the mother's year, a
daughter of an ox or sheep year born on khon is bad for the mother. A
mother of a dog or dragon year on zon: whatever child is born, there
will be no sister to call one; whatever the mother's year, on zon, a son
of a dog or dragon year the same. A mother of an ox or sheep year on gin:
any child the same. A mother on gin and a daughter of an ox or sheep
year: bad for the brothers. Daughters of the horse, snake, mouse or pig
born on kham, of the tiger, hare, bird or monkey born on dwa or zin, of
the ox or sheep on khon or gin, of the dog or dragon on zon, and any born
on khen, "count husbands" (སྐྱེད་པོ་བགྲང, read as marrying more than once):
some reckon these as the twenty-six bad lucks of the oath-breaker
(མནའ་ཟ, mna' za). Mother and child on li and khen and the like, trigrams
of enemy and māra crossed: bad for the father. The page goes on (Yigdzin-1)
with the parents' and the child's years and trigrams, its entries marked
with the 1996 editors' numbers ༡–༡༨.

**The children of the gods, glory, life, spirits and māras** (p. 347,
img. 357, read on the scan; Yigdzin-1 drops five of these lines):

> བཙས་པ་གསོ་བ་འདི་བཞིན་ཏེ། །གསོ་བ་དཀའ་བར་བསྟན་པ་དང་། །སྲུང་སྡོམ་ཕན་བྱ་བསྟན་པ་དང་། །གོས་དང་མིང་གི་གང་མཐུན་དང་། །གཏའ་བསྡམ་ལྷ་ལ་གདགས་པ་དང་། །སྲི་མནན་གླུད་གཏོར་བཏང་བའོ། །དང་པོ་གསོ་བ་དཀའ་སླ་ནི། །ལོ་ཟླ་སྤར་སྨེ་གཟའ་སྐར་དང་། །ཞག་དུས་ཚེས་གྲངས་དག་གིས་བཟུང་། །ལོ་ནི་འདི་བཞིན་ཤེས་པར་བྱ། །ལྷ་ཡི་བུ་ནི་མཐོ་ཞིང་ལྡོག །དཔལ་གྱི་བུ་ནི་བཟང་པོ་ཡིན། །ཚེ་ཡི་བུ་ནི་མཐུན་པ་ཡིན། །འདྲེ་ཡི་བུ་ནི་སོས་པར་དཀའ། །བདུད་ཀྱི་བུ་ནི་གནོད་པ་ཆེ། །ལྷ་ཡི་བུ་ཞེས་བྱ་བ་ནི། །བུ་ཡི་སྲོག་ལུས་དབང་གསུམ་གྱིས། །ཕ་མ་དག་ལ་སྐྱོབ་པ་ན། །ལྷ་ཡི་བུ་སྟེ་གསོ་བ་དཀའ། །བུ་ཡི་སྲོག་ལུས་དབང་གསུམ་པོ། །ཕ་མས་སྐྱོབ་ན་དཔལ་བུ་བཟང་། །བུ་ཡི་སྲོག་ལུས་དབང་གསུམ་དང་། །ཕ་མའི་སྲོག་ལུས་གཅིག་པ་ན། །ཚེ་ཡི་བུ་ཞེས་བྱ་སྟེ་ལེགས། །ཕ་མའི་སྲོག་ལུས་དབང་གསུམ་གྱིས། །བྱིས་པ་དག་ལ་གནོད་པ་ན། །འདྲེ་ཡི་བུ་སྟེ་གསོ་བ་དཀའ། །བུ་ཚའི་སྲོག་ལུས་དབང་གསུམ་གྱིས། །ཕ་མ་དག་ལ་གནོད་པ་ན། །བདུད་ཀྱི་བུ་ཞེས་བྱ་བ་སྟེ། །བུ་ནི་ཕ་ལ་གནོད་པ་དང་། །སྤར་ཁ་ཁེན་ལ་བཙས་པ་ན། །སྲིན་པོ་ཕ་བདུད་གཅོད་ཅེས་བྱ། །བུ་མོ་མ་ལ་གནོད་པ་དང་། །སྤར་ཁ་ཁོན་ལ་བཙས་པ་ན། །སྲིན་མོ་མ་ཚེ་འཕྲོག་ཅེས་བྱ། །ཕ་མ་དག་ལ་གནོད་འགྱུར་ཡང་། །གསོ་བ་སླ་བར་འབྱུང་བ་ཡིན། །གཞན་ཡང་འདི་བཞིན་བརྗོད་པ་ཡོད། །ཕ་མའི་སྲོག་དང་བུ་སྲོག་སྤྲད། །མཐུན་པར་བཙས་པ་ཚེ་ཡི་བུ། །ཕ་བས་བུ་ཚེ་རིང་བ་ཡོད། །བུ་གྱུར་བཙས་པ་དཔལ་གྱི་བུ། །ཕ་དང་མེས་པས་བུ་ཚ་དར། །མར་གྱུར་བཙས་པ་ལྷ་ཡི་བུ། །ཕ་དང་མ་ལ་བུ་ཡིས་སྐྱོབ། །གྲོགས་གྱུར་བཙས་པ་འདྲེ་ཡི་བུ། །ཆུང་བའི་དུས་ན་གསོ་བ་དཀའ། །དགྲར་གྱུར་བཙས་པ་བདུད་ཀྱི་བུ། །ཕ་མར་ངན་པས་དྭ་པོར་ལུས།

Raising the newborn: that it is hard to raise; protections, vows and
help; clothes and names that suit; a pledge bound to a god; pressing the
harmful spirits of the dead (སྲི, sri) down and giving ransom tormas.
First, hard or easy to raise, judged by the year, month, trigram, mewa,
planet, mansion, day, hour and date. Of the year: a child of the gods
(ལྷ, lha) stands high and turns back; a child of glory (དཔལ, dpal) is good;
a child of life (ཚེ, tshe) is in harmony; a child of spirits (འདྲེ, 'dre)
is hard to keep alive; a child of māras (བདུད, bdud) does great harm.
When the child's life force, body and power protect the parents' (stand
as their mother), it is a child of the gods, hard to raise; when the
parents' protect the child's, a child of glory, good; when the two are
of one element, a child of life, good; when the parents' harm the
child's, a child of spirits, hard to raise; when the child's harm the
parents', a child of māras. A son harmful to the father, born on the
trigram **khen**: "the rākṣasa (སྲིན་པོ, srin po) cutting the father's
māra"; a daughter harmful to the mother, born on **khon**: "the rākṣasī
(སྲིན་མོ, srin mo) stealing the mother's life"; these harm the parents
but are easy to raise. Another view, by the life force alone: the child's
the same as the parents', a child of life, outliving the father; their
son, a child of glory, the line flourishing; their mother, a child of
the gods, protecting father and mother; their friend, a child of spirits,
hard to raise while small; their enemy, a child of māras, bad for the
parents, left an orphan. The months likewise; the page goes on to the
trigram, mewa, planet and mansion.

The notes (p. 476, img. 484, read on the scan) count the same five from
the natal trigram and mewa: «བརྩི་བྱའི་སྲོག་ལུས་དབང་ཀླུང་བཞིས་མ་ཀླུང་སྤར་ཁ་དང་། སྐྱེས་སྨེའི་ཁམས་ལ་འཐབས་པས་མ་དང་ཕྲད་ན་ཕ་མ་ལྷ། བུ་དང་འཕྲད་ན་བུ་ཚ་དཔལ། རང་དང་འཕྲད་ན་མཐུན་པ་ཚེ།»
… the four of the one reckoned fought against the element of the mother's
luck trigram and of the natal mewa: meeting its mother, the parents'
god; its son, the children's glory; itself, harmony, life; its friend,
a spirit that can be survived; its enemy, a māra that cannot (the last
two by Yigdzin-1).

**The five by the natal trigram, with a worked count** (p. 294, img. 304,
read on the scan):

> གཞན་ཡང་ལྷ་དཔལ་ཚེ་བདུད་ནི། །ཕ་མ་ལྷ་ལ་བུ་ཚ་དཔལ། །མཐུན་པ་ཚེ་ལ་ཐུབ་པ་འདྲེ། །མི་ཐུབ་པ་ནི་བདུད་དུ་འགྱུར། །དེ་ཡི་ཚིག་ཀྱང་འདི་ཡིན་ཏེ། །དཔེར་ན་རྟ་སྦྲུལ་ལོ་པ་གཉིས། །ཟིན་ལ་བཙས་པ་ཕ་མ་ལྷ། །ཚེ་ཡི་ཉི་མ་ཤར་བ་སྟེ། །ལོ་ནི་བདུན་ཅུ་རྩ་གསུམ་བརྒལ། །མཚམས་བཞིར་བཙས་ན་བུ་ཚ་དཔལ། །སྤྲིན་བྲལ་ཉི་མ་དྲུག་ཅུ་གཅིག །ལི་ལ་བཙས་པ་མཐུན་པ་ཚེ། །ཉི་མ་རླུང་བསྐྱོད་ཚེ་སྐལ་འབྲིང་། །ལོ་ནི་བཞི་བཅུ་ཞེ་དགུ་འདའ། །དྭ་ལ་བཙས་པ་ཐུབ་པ་འདྲེ། །ཉི་མ་སྤྲིན་བསྒྲིབས་སུམ་ཅུ་བདུན། །ཁམ་ལ་བཙས་ན་མི་ཐུབ་བདུད། །ཚེ་ཡི་སྐལ་ཆད་ཉི་མ་ནུབ། །བཙས་མ་ཐག་ཏུ་འཆི་བར་བཤད། །དེ་ཡང་ཤིང་གི་སྟག་ཡོས་གཉིས། །དྭ་ལ་བཙས་པ་ཇི་བཞིན་དུ། །ལྕགས་ཀྱི་བདུད་ལ་སོང་བ་ན། །ལོ་ནི་བཅོ་ལྔ་དག་ན་འཆི། །ལྕགས་ཁམས་བྱ་སྤྲེལ་ལི་ལ་བཙས། །ལྕགས་ལ་མེ་ཡི་བདུད་དུ་སོང་། །ལོ་ནི་བཅུ་གཉིས་དག་ན་འཆི། །མེ་ཡི་རྟ་སྦྲུལ་ཁམ་ལ་བཙས། །མོ་ལ་ཆུ་ཡི་བདུད་དུ་སོང་། །ལོ་ནི་དགུ་ལ་འཆི་བ་ཡིན། །ཆུ་ཁམས་བྱི་ཕག་མཚམས་བཞིར་སྐྱེས། །ལོ་ནི་དྲུག་ལ་འཆི་བ་དང་། །གཤེད་བཞི་ཟིན་གྱི་ཐོག་ཏུ་བཙས། །ས་ལ་ཤིང་གི་བདུད་དུ་སོང་། །ལོ་གསུམ་དག་ན་འཆི་བ་སྟེ།

The gods, glory, life and māras: the parents' god, the children's glory,
harmony that is life, a spirit that can be survived, and one that cannot,
a māra. In words: take the two of the horse and snake years (their life
force fire). Born on zin (wood, fire's mother): the parents' god, the sun
of life risen, passing seventy-three. Born on the four corners (earth,
fire's son; the *Moonbeams* makes sky, mountain and wind earth, above):
the children's glory, a sun without clouds, sixty-one. Born on li (fire,
the same): harmony, life, a sun moved by the wind, a middling share,
passing forty-nine. Born on dwa (iron, the element fire overcomes, its
friend): a spirit that can be survived, a clouded sun, thirty-seven. Born
on kham (water, fire's enemy): the māra that cannot be survived, life's
share cut, the sun set, said to die as soon as born. So: the wood tiger
and hare born on dwa go to the iron māra and die at fifteen; iron bird
and monkey born on li, the fire māra, at twelve; fire horse and snake
born on kham, the water māra, at nine (the print reads «མོ་ལ», "for a
woman" or a slip for «མེ་ལ», "for fire", checked at 8×); water mouse and
pig born on the four corners at six; the earth animals (the four slayers,
གཤེད་བཞི, gshed bzhi) born on zin, the wood māra, at three. The page goes
on (Yigdzin-1) to what each of the five means for the body, and to the
illness of each māra: iron the lungs, fire fever and the heart, water
the kidneys, earth the spleen, wood the liver.

**The natal trigram elsewhere in the chapter** (etext and Yigdzin-1, not
read on the scan): the line of births with the parents on a trigram,
«ཕ་མ་ལི་ཐོག་བབས་པའི་དུས། །ཁྱི་ལོ་ལོང་བ་སྐྱེས་པ», a dog-year child born
blind when the parents are on li, and so for each trigram (p. 307,
img. 317); whom to marry when the line is hard to continue, by the trigram
one is born on («ལིར་སྐྱེས་སྲིད་དཀོན་རྫ་མོའམ», p. 307; again p. 347,
«ལི་ལ་སྐྱེས་པ་སྲིད་མེད་ན»); births of the year animals on each trigram,
good and bad for father and mother (pp. 322–327); the trigrams of the
four directions and corners (pp. 326, 334); the mother on li or kham and
the line cut (p. 346); easy or hard to raise by the trigram (p. 348,
«སྤར་ཁའི་གསོ་དཀའ་སླ་ནི། །བྱ་སྤྲེལ་ལི་ལ…»). The *Moonbeams* names the
obstacle that falls when the year of age's trigram reaches the natal one
(KD vol. 3, p. 508, etext only): «རང་སྐྱེས་སྤར་ཁའི་སྟེང་སླེབས་པ། །གསང་ཀེག་…»,
the secret obstacle (གསང་ཀེག, gsang keg) of ch. 24 (RD14).

### The rest of the chapter, outlined (etext and Yigdzin-1)

By printed page (image = page + 10); not read on the scan:

- pp. 259–262 (img. 269–272): the opening verses, the count, the five great clans and
  their family names, the key pebbles and their colours, the 136, the
  special and indispensable pebbles, the unchanging luck, the hundred,
  rise and decline, the decisive pebbles, the 3,773 (above).
- pp. 262–263 (img. 272–273): the four precious pebbles' readings.
- pp. 263–270 (img. 273–280): the twelve years in brief and the sixty in full: each
  year's character, lifespan, number of obstacles, children's share,
  illnesses, the earth lords and spirits that harm («ཚེ་ཚད་བདུན་ཅུ་དོན་ལྔ»,
  «བར་ཆད་དྲུག», «བུ་སྐལ་ལྔ་འམ་ཡང་ན་གསུམ»…).
- pp. 271–275 (img. 281–285): the five bodies of the elements in brief, then the thirty
  paired-year elements with the mother's sign at the birth (above).
- pp. 275–280 (img. 285–290): the power's five, *kha yan*, *khong nong*, *se zhig*,
  *'dun khur*, *kha ral*, each with its readings and inner kinds.
- pp. 281–282 (img. 291–292): one luck: the triads' readings («བྱི་འབྲུག་སྤྲེལ་གསུམ་ལོ་པ་ནི»),
  the cutting and tearing among the child's own four.
- pp. 283–286 (img. 293–296): the first, middle and last (ར་འབྲིང་ཐ་མ, ra 'bring tha ma)
  of year, month, day and hour, the special pebble's readings by clan (great and small
  penalty …).
- pp. 286–293 (img. 296–303): births on each mewa, from one white to nine red, with the
  former life and what the child was («གཉིས་ནག་འདི་ལ་གང་སྐྱེས་པ», «གསུམ་མཐིང་…
  བསེན་མོ་ཀླུ་སྲིན་གཉེན་གྱི་བུ», «བཞི་ལྗང་… ཀླུ་མོ་དཀར་མོས་བྱིན་པའི་བུ»), with
  birthmarks.
- pp. 294–295 (img. 304–305): gods, glory, life and māras by the trigram and mewa; glory
  and misfortune, the father's and mother's life force against the
  child's (*dpal byur*).
- pp. 295–305 (img. 305–315): the line of births (*rgya ma*): the father's and
  grandfather's rise and decline counted onto the child's year, the
  "crowding", "staying" and "lameness" of the line, its remedies
  (earth-lord rites, thread-crosses, མདོས, mdos), the fifteen gods born with one (སྲོག་ལྷ, dgra
  lha …, p. 305).
- pp. 306–309 (img. 316–319): *rgyu 'them* (རྒྱུ་འཐེམས), the parents' trigrams; the goat-horn
  (ར་རུ, ra ru) and rhinoceros-horn (བསེ་རུ, bse ru) kinds of year and trigram; brides by
  trigram; the planets' hundred kinds (p. 309).
- pp. 309–318 (img. 319–328): the planets of the line (གཟའ, gza'), with the crowding and
  its cures; births of the triads under each planet («བྱ་གླང་སྦྲུལ་བཙས་
  མིག་དམར་ཁར»), the bad year counted for the household.
- pp. 319–327 (img. 329–337): the parents' years and trigrams at the birth, children
  bad for father or mother, bodies matched (ལུས་སྤྲད, lus sprad), the trigram's
  enemies and māras (p. 326 above), the mother of a hare year and the
  like.
- pp. 328–337 (img. 338–347): the hundred pebbles' readings by each aspect: the life
  force's, body's, power's and luck's rise and decline, white and black,
  the four great treasures, births on the good and bad six.
- pp. 338–342 (img. 348–352): the secret *yang tig*: children of nature, of lineage,
  of life and so on, numbered by the 1996 editors (༡)–(༣༤).
- pp. 343–347 (img. 353–357): the birth pebbles (བཙས་རྡེའུ, btsas rde'u), the four ways, the number
  of children, a child's share of power, whom to marry, the children of
  the gods to māras (above).
- pp. 347–350 (img. 357–360): raising a child hard to raise: by the trigram, mewa,
  planet, mansion, day, hour and date; protections, vows, names and
  clothes, a pledge bound to a god, pressing down the harmful spirits
  of the dead and ransom; the chapter's close.

### Open

Open questions 69–76 ([open-questions.md](open-questions.md)), in order: the
two counts of pp. 259 and 262; «མི་འབྱུང»; 24 or 20 decisive pebbles; «ཚེ་སྨང»;
the mother's trigram one a year or with the leap at ten; the father's luck
pebble; «མོ་ལ་ཆུ་ཡི་བདུད»; «སྐྱེད་པོ་བགྲང». Question 23 narrows: on the scan of
vol. 2, p. 474 (img. 482, at 8×) the first «དུས་ཁམས» is printed so, the
second reads «རུས་ཁམས» («གཞན་ཡང་རུས་ཁམས་དང་ཕ་མེས་བརྒྱུད་པའི་ལོ»), the clan's element.

### Searched and not found

- **The natal trigram on the *Moonbeams*' f. 12a** (Dorje, p. 108) in
  Dharmaśrī's *Gsung 'bum* print (I2533, img. 027, read on the scan): that
  folio holds the five nine-multiples; the natal trigram is on f. 9a
  (img. 021). Dorje's folio number is of another print.
- **A worked example of a natal trigram** in WB ch. 22 or the notes'
  section 1 (etext and Yigdzin-1): none; the *Moonbeams*' rule (f. 9a) is
  the only count.

## The obstacles that need other people (RD14)

WB vol. 1, ch. 24, the obstacle reckoning (ཀེག་རྩིས, keg rtsis), the parts
that ask for someone else's signs: the household, the spouse, the parents,
the dead; with ch. 22's births on the eight trigrams (pp. 286–288, which
are the ROADMAP's "ff. 180b–182a"), and the notes' section 2 (vol. 2,
I1KG12907, pp. 489–495, img. 497–503, image = page + 8). The parts of
ch. 24 that need only the person are above (*The obstacle years*, *The
rest of the obstacle years*, read 2026-10-09) and are not repeated here.
Read 2026-10-12.

**How read.** Yigdzin-1 over img. 391–426 (vol. 1) and 498–503 (vol. 2);
MITRA as witness over img. 296–298, 390, 395–398, 400, 406, 409–410,
418–419. The two agree on 93–94% of the syllables of these pages; the flags
inside the quoted passages were read on the scans at 2.3–5× (img. 296–298,
390, 395–396, 398, 400, 406, 409–410, 417–419; vol. 2 img. 497–500). Most
were MITRA's (ལྟ for ལྷ, ནུབ for རྩུབ, བརྒྱད for བརྒྱུད, a looped line); where
Yigdzin-1 was wrong the quote follows the scan (img. 400: «ཚོར་བ», «ཁ་སྤྲོད»;
vol. 2 img. 497: «ཁྱི», «གླང»). Passages marked "Yigdzin-1" were not read on the
scan. BDRC's etext (IE0OPI51524892) was the third witness and the map.

### The secret circle asks for no one's dates (p. 380)

reckonings.md had "for the secret circle the mother's year" from the
etext's «གསང་སྲོག་མ་ལ་དྲིས». Read whole on the scan (p. 380, img. 390, the
last eleven lines), the line means the mother *element* of the secret
year's life force, not the person's mother:

> གཞན་ཡང་ལོ་ཁྱིམ་ཕྱི་སྐོར་ལ། །ཕྱི་ནང་གསང་བ་རྣམ་པ་གསུམ། །གནམ་ལོ་ཟླ་ཞག་དུས་ཚོད་དང་། །བརྩི་བྱ་འཐབ་པ་ཕྱི་སྐོར་དང་། །ལོག་མེན་དང་འཐབ་ནང་གི་སྐོར། །གསང་བའི་སྐོར་འགོ་བརྩི་བ་ནི། །ཕྱི་སྲོག་དགྲར་དྲི་ཕོ་ཡི་ཕོ། །མོ་ཡི་མོ་ལོ་དག་ལ་བཟུང་། །བྱི་ཕག་ཐ་ཆུང་བཞི་འཛིན་དང་། །ཐ་ཆུང་སྟག་ཡོས་འཛིན་པའི་ཚེ། །ཁོ་རང་གང་ཉེའི་ལོ་ལ་དྲིས། །དེ་ཡི་ཕྱི་དབང་དགྲ་དང་མཐུན། །ལུས་ནི་ཕྱི་ལུས་སྐྱོབ་པ་དང་། །དབང་བུ་ཀླུང་གྲོགས་བླ་རྡེལ་ནི། །གསང་སྲོག་མ་ལ་དྲིས་པ་སྟེ། །དེ་བཞིན་རང་གི་ལོ་བཙལ་ལ། །སོ་སོར་འཐབ་པའི་གསང་སྐོར་ཞེས། །སྤྲོས་པ་ཁ་ཅིག་གཞན་གྱི་ལུགས། །སྙིང་རྒྱུད་སྤྲོས་བཤད་འདོད་པ་ན། །ཕྱི་སྐོར་གནམ་ལོ་ནང་སྐོར་ནི། །ལོག་མེན་གསང་བ་རང་ནས་བསྐོར།

Of the year-house (ལོ་ཁྱིམ, lo khyim) there are three circles, outer, inner
and secret: the outer fights the one reckoned with the year, month, day
and hour; the inner with the progressed sign (ལོག་མེན, log men); the secret
starts from a year looked for as the enemy of the outer life force, a
male year for a male and a female for a female. Where the mouse and pig
would take the four "smallest" (the earth animals) and these the tiger or
hare, one asks the year nearest to him. Its power is the enemy; its body
what protects the outer body; its power the son, its luck the friend, and
the la pebble (བླ་རྡེལ, bla rdel) is looked for at the mother of the secret
life force. This, "the secret circle each fought separately", is "the
elaboration of some, another's way". WB's own: the outer circle from the
current year, the inner from the progressed sign, the secret from one's
own year. The notes (vol. 2, p. 489, img. 497, the last two lines, read
on the scan) give the others' secret year by example:

> སྐབས་གཉིས་པ་ཀེག་རྩིས་ལ། གཞན་བཞེད་ཀྱི་གསང་ལོ་ནི། རྟ་ལོ་པའི་སྲོག་དགྲ་བྱི་བའི་སྲོག་ཆུ་ལྟ་བུ། བྱི་ལོའི་ཁྱི། ཕག་ལོའི་གླང་། ཁྱི་འབྲུག་སྟག གླང་ལུག་ཡོས་སམ། དེའི་དབང་གསང་སྲོག་དགྲ། ལུས་མ། དབང་བུ། ཀླུང་གྲོགས། བླ་སྲོག་གི་མའོ། །

The secret year of the others' view: for one of a horse year (life force
fire) the mouse, whose life force water is fire's enemy; for a mouse year
the dog; for a pig year the ox; for the dog and dragon the tiger; for the
ox and sheep the hare. Its power is the secret life force's enemy, its
body the mother, power the son, luck the friend, la the life force's
mother. So the secret circle is reckoned from the person's own year: **no
relative's date is needed**, and it is a view WB reports, not its own.

### The household's pebbles (pp. 385–387; the notes, pp. 490–491)

**The verse** (p. 385, img. 395, from the 16th line; p. 386, img. 396,
whole; p. 387, img. 397, l. 1; read on the scan):

> །གཞན་ཡང་ཁྱིམ་གཅིག་བཟའ་མི་གྲངས། །མང་ཚོགས་ཁ་དམར་གཅེས་པ་ཡི། །རྒྱ་ནག་རྡེལ་སྐོར་ཞེས་བྱ་བ། །བཟའ་ཚང་ཁྱིམ་གཅིག་མི་དུ་ཡོད། །སོ་སོའི་སྲོག་ལུས་དབང་ཀླུང་གི། །བཏང་གཅོད་ཁ་དམར་རྡེལ་རེ་དང་། །སྨེ་བ་ཁམས་རྡེལ་ལྔ་བོར་ལ། །ཕོ་མོ་རིམ་པ་རྒན་རིམ་བཞིན། །མ་ནོར་རྡེལ་རྣམས་ལེགས་པར་བཀྲམ། །གོང་མའི་ལུས་དང་འོག་མ་ཡི། །ཁམས་ནས་རིམ་པས་བླང་བར་བྱ། །ལྷག་མ་མ་རྫོགས་བར་དུ་ལེན། །རྡེལ་ཀུན་གསེགས་རྡར་རིས་སུ་བླང་། །དེ་ལ་རྐྱང་པའི་རྡེལ་སྐོར་ཟེར། །ལྷ་ས་རབ་འབྲིང་ཐ་གསུམ་མོ།། ལྷ་སར་རྡེལ་ནག་བབས་གྱུར་ན། །འཁོན་དང་མི་གཙང་བ་ལ་འཛེམ། །བསངས་དང་ཁྲུས་ཀྱི་ཆོ་ག་བྱ། །རྡེལ་དམར་བྱུང་བར་གྱུར་པ་ན། །ཐབ་གཞོབ་མི་གཙང་བ་ལ་འཛེམ། །སྔོ་དམར་གཉིས་པོ་བྱུང་གྱུར་ན། །ཀླུ་བཙན་རུ་བསྡུམ་བྱ་བ་ཡིན། །རྡེལ་ལྗང་བྱུང་བར་གྱུར་པ་ན། །འཐབ་དང་འཁོན་ལ་འཛེམ་པར་བྱ། །ཀླུ་བཙན་མཚོ་སྨན་ཆོ་གས་བཅོས། །རྡེལ་སེར་བྱུང་ན་ནད་ཡམས་ངོ། །གཙུག་ཏོར་སྟོང་བཟློག་རྒྱལ་མདོས་བྱ། །རྡེལ་དཀར་བྱུང་བར་གྱུར་པ་ན། །བཟང་ཞིང་བཀྲ་ཤིས་རྡེལ་ཡིན་ནོ། །གཏང་རག་ལྷ་གསོལ་ལྷ་ཆོག་བྱ། །རྡེལ་མཇུག་ལྷ་སར་བོར་བར་བྱ།། དེ་འོག་བར་པ་ཐ་མ་གསུམ། །མི་ས་རབ་འབྲིང་ཐ་གསུམ་ཡིན། །དེ་རུ་རྡེལ་དཀར་བྱུང་བ་ན། །བསམ་འགྲུབ་གཡུང་དྲུང་བདེ་ཞིང་སྐྱིད། །མི་རྣམས་ཀུན་ལ་ཤི་ཆད་མེད། །རྡེལ་ནག་བྱུང་བར་གྱུར་པ་ན། །གློ་བུར་ཡེ་འདྲོག་འོང་བའི་དུས། །ཚེ་གཟུངས་ཆོ་ག་བྱེད་པར་བཤད། །རྡེལ་དེ་སྔོན་པོ་བྱུང་བ་ན། །འབྲས་དང་ཤུ་ཐོར་འོང་བའི་ངོ། །ས་གཉན་རྡོ་གཉན་བརྐོ་མི་ཉན། །ས་བདག་ཀླུ་ཡི་ཆོ་ག་བྱེད། །རྡེལ་དེ་ལྗང་གུ་བྱུང་བ་ན། །ཀླུ་བརྒྱད་ཀླུ་བསེན་དགུ་མདོས་བྱ། །རྡེལ་དམར་བྱུང་བར་གྱུར་པ་ན། །དམར་ངོ་གྲི་ཁ་མཚོན་བསྒྱུར་དང་། །ནག་པོ་མགོ་གསུམ་དགྲ་བྲུབ་གྱིས། །རྡེལ་སེར་བྱུང་ན་རིམས་ནད་ངོ། །བདུད་མདོས་མ་ཊཾ་ཁྲག་མདོས་ཐོང་།། སྔ་མ་གཉིས་ཀྱི་རྡེལ་མཇུག་ལ། །དཀར་པོ་བཟང་རབ་གཏམ་སྙན་ཐོས། །ནག་པོ་ནད་ཡམས་ངོ་ཡང་ཡིན། །སེར་པོ་བྱུང་ན་འགོ་ནད་འོང་། །རྡེལ་སྔོན་བྱུང་བར་གྱུར་པ་ན། །ཀླུ་ཡི་ནད་དང་གཉན་ནད་འོང་། །དམར་བྱུང་གྲི་ངོ་མཚོན་ངོ་ཡོད།། རྡེལ་མཇུག་འགོ་མའི་གོང་མའི་འོག །ཁྱིམ་ས་རབ་འབྲིང་ཐ་གསུམ་ཟེར། །དེ་ལ་རྡེལ་དཀར་བབས་པ་ན། །བཀྲ་ཤིས་རྡེལ་ནི་ཡིན་པས་བཟང་། །ནག་ན་ཁྱིམ་སར་ན་ཆགས་ཆེ། །བདུད་ཀྱི་བླ་ཁྲོམ་འཕྲུལ་གསུམ་བྱ། །ཁྱིམ་ནང་ས་དཀར་ཕྱོགས་བཞིར་གཏོར། །ཁང་པའི་ཕྱོགས་བཞིར་གཡུང་དྲུང་བྲི། །སྐྱེད་པུར་ཁུ་དཀར་གསོལ་བ་དང་། །སྔོ་ལྗང་རྡེལ་དེ་བབས་པ་ན། །ཀླུ་བརྒྱད་ས་བདག་གཏོ་བསྒྱུར་དང་། །ཀླུ་འབུམ་ཀླུ་ཡི་སྤང་སྐོང་འདོན། །རྡེལ་སེར་རིམས་ནད་ངོ་རུ་ཤར། །བློ་བཟློག་མ་མོའི་ཆོ་ག་བྱ། །དམར་པོས་ཐབ་གཞོབ་དམར་གྱི་ངོ། །དམར་གཏོར་ཁྲོ་རོལ་ཟོར་མདོས་བྱ། །ཁྱིམ་སའི་རྡེལ་རོའི་ཁ་དམར་ནི། །དཀར་པོ་གཉིས་ཀྱིས་བཀྲ་ཤིས་ཐོབ། །དཀར་ནག་གཉིས་བྱུང་རྒྱ་འདྲའི་ངོ། །ནག་པོ་གཅིག་བྱུང་ན་ཚའི་ངོ། །ནག་གཉིས་ལ་ནི་ཤི་ཆད་ངོ། །ཕུགས་ནས་རོ་འོང་ངེས་པ་ཡིན། །བཟའ་ཚང་སྤར་ཁའི་རིམ་གྲོ་སོགས། །ལུས་སྲོག་དབང་ཀླུང་བཞི་པོ་ལ། །གསོ་བསྐྱེད་རིམ་གྲོ་འབོགས་པ་ཡིན། །ལོ་འགྲོས་ཆུང་ངུ་ཞིབ་ཏུ་བརྩི། །དཀར་ནག་གསུམ་དུ་བྱུང་བ་ན། །མར་གྱི་དཀྱིལ་ནས་རུས་པ་བྱུང་། །སོ་ཡི་རྒྱལ་པོ་མཆེ་བ་ཆག །སྔོ་དམར་སེར་གསུམ་བྱུང་བ་ན། །ཕུག་མའི་འོག་ནས་ཆུ་དོང་བྱུང་། །སྙིང་དུ་བསམས་པས་གློ་བར་སོང་། །ལྗང་གུ་བྱུང་ན་མི་ཁའི་ངོ། །དེ་རྣམས་རྡེལ་རོའི་ཁ་དམར་ཡིན། །རྡེལ་རོ་བྱུང་ན་ངན་པར་བཤད།། དང་པོ་རྐྱང་པའི་རྡེལ་སྐོར་ནི། །དེ་ལ་ལྷ་ས་ཟེར་བ་ཡིན། །དེ་ནས་གཉིས་པ་བསྐོར་བ་ནི། །དེ་ལ་མི་ས་ཟེར་བ་ཡིན། །དེ་ནས་གསུམ་པ་བསྐོར་བ་ནི། །དེ་ལ་ཁྱིམ་ས་ཟེར་བ་ཡིན། །ནོར་ས་རབ་འབྲིང་ཐ་མ་གསུམ། །ལྷ་ས་མི་སར་རེ་རེ་སྟེ། །ཁྱིམ་ས་དག་ལ་གཉིས་གཉིས་ཏེ། །ཕྱོགས་ནི་བཀྲམ་ནས་བླང་བར་བྱ། །ཁྱིམ་སའི་འོག་མ་དགྲ་ས་ལ། །རྡེལ་དཀར་ཤར་ན་དགྲ་མགོ་མཐོ། །དགྲ་བཟློག་དགྲ་མནན་དགྲ་བྲུབ་བྱ། །ནག་དམར་ལྗང་སེར་སྔོན་པོ་བྱུང་། །དགྲ་མགོ་རེ་བས་ཕུར་ཏེ་བཟང་།། གཞན་ཡང་འབྲས་བུ་འདི་བཞིན་བཤད། །ལྷ་ས་དང་པོར་རྡེལ་དཀར་བྱུང་། །ཁུག་པའི་རྡེལ་གྱི་ལྷག་ཀྱང་དཀར། །ཡང་ཁུག་རྡེལ་ལྷག་རྣམས་དཀར་ན། །ལྷ་ནད་འོང་བའི་ངོ་ཡང་ཡིན། །ཡང་ན་ལྷ་ས་དང་པོ་ནག །ཁུག་རྡེལ་དཀར་ལ་ཡང་ཁུག་ནག །ལྷ་སྲུང་བདུད་བབས་ཤིན་ཏུ་ངན། །རི་མགོར་དར་འཕེན་འཛུགས་པར་བྱ། །ཡུལ་ལྷ་གསོལ་དང་དཔངས་བསྟོད་བྱ།། མི་སར་རྡེལ་དཀར་བྱུང་བ་ན། །ནང་མི་ཁམས་བདེ་ཁྱིམ་ས་ལ། །རྡེལ་དཀར་ཁྱིམ་ལ་གོད་ཁ་མེད། །འབྲུ་ནོར་འཕེལ་ལ་དགྲ་སའི་ཐོག །རྡེལ་དཀར་བྱུང་ན་ཁ་མཆུའི་ངོ།།

Besides the person's own four, the Chinese pebble round (རྒྱ་ནག་རྡེལ་སྐོར,
rgya nag rdel skor) is reckoned for **all the members of one household**
(བཟའ་ཚང, bza' tshang; the members, བཟའ་མི, bza' mi), "the precious verdicts
where many gather". For each member, the decisive red-marked pebble of
each of his life force, body, power and luck, and five pebbles of the
mewa's element; laid out men and women in order of age; then taken up in
turn, the upper one's body with the lower one's element, until all are
taken, in a zigzag. The first row is the plain round (རྐྱང་པ, rkyang pa):
the god's place (ལྷ་ས, lha sa), best, middle and last. Then the readings
by the colour of the pebble that falls there: in the god's place black,
keep from grudges and impurity, do purifying smoke and washing; red, keep
the hearth clean; blue and red, a rite to bring the nāgas and *btsan*
together; green, avoid fights and grudges, rites of the nāgas, *btsan*
and lake goddesses; yellow, an epidemic, the Uṣṇīṣa and the king's
ransom; white, good, an auspicious pebble, thanksgiving and offering to
the gods; the tail pebble is laid in the god's place. Below, middle and
last, the people's place (མི་ས, mi sa): white, wishes fulfilled, no death
among them; black, sudden misfortune, longevity rites; blue, swellings
and dropsy, do not dig earth or stone, rites of the earth lords and
nāgas; green, the eight nāgas' rites; red, blades and weapons, the black
three-headed rite against enemies; yellow, epidemics, the māra and blood
rites. On the tail of the first two: white, best, good news; black,
sickness; yellow, contagion; blue, nāga and *gnyan* illness; red, blades.
Below these the house's place (ཁྱིམ་ས, khyim sa): white, auspicious; black,
much illness in the house, with rites of the māras' la, white earth
scattered in the four quarters, swastikas drawn on the four sides; blue
and green, the eight nāgas and the earth lords; yellow, epidemic; red,
the hearth. The leftover pebble (རྡེལ་རོ, rdel ro) of the house's place: two
white, good fortune; white and black, mixed; one black, illness; two
black, a death, "a corpse comes in the end"; then remedies for the
household's trigrams and for each one's four, the small year's course
counted closely, and the leftover's further signs by colour. Then the
names: the first, plain round is the god's place, the second turn the
people's, the third the house's, with the wealth place (ནོར་ས, nor sa) best,
middle and last, one each in the god's and people's place and two each
in the house's; the lowest, under the house's, is the enemy's place
(དགྲ་ས, dgra sa): white there, the enemy's head high, with rites to turn him
back; black, red, green, yellow or blue, the enemy's head brought low,
good. Other results: a white first in the god's place with the folded
rows' leftovers white, a disease of the gods; the god's place black, the
folded white and the refolded black, a māra has come down on the
guardian gods; in the people's place white, the household well.

**The notes** (vol. 2, p. 490, img. 498, l. 7–11, read on the scan; the
rest of pp. 490–491 Yigdzin-1 and the etext) make it a procedure:

> རྒྱ་ནག་རྡེལ་སྐོར་ནི་བཟའ་ཚང་སྤྱི་ལ་བརྩི་སྟེ་… ཁྱིམ་དེའི་མི་ངོ་དུ་ཡོད་ལ་བརྩི་དགོས། དེ་ཡང་མི་རེ་རེ་བཞིན་གཅོད་འདྲལ་ཉེར་བཞི་བཞག་ནས་བཏང་གཅོད་རྡེའུ་བཞིར་བསྡུས་ལ་དེ་སྟེང་མི་རང་རང་གི་བབ་སྨེའི་… ཐོག་མར་ཕོ་ངོ་དུ་ཡོད་རྒན་རིམ་ནས་ཆུང་ཤོས་བར་… འགོད། དེ་ནས་མོ་དུ་ཡོད་ཀྱང་དེ་ལྟར་བསྒྲིག ལོ་མཉམ་ན་སྐྱེ་སྔ་བ་སྔོན་མ་བྱ། མ་ཤེས་ན་གང་ཆེ་བའམ་རྒན་པ་སྔ་མར་བྱའོ། །

The Chinese pebble round is reckoned for the household as a whole, for
as many people as the house holds. For each, the twenty-four pebbles of
cutting and tearing are laid and gathered into four decisive pebbles,
with each one's mewa of the year above them; men first, eldest to
youngest, then the women the same; of two of one year the one born first
goes first, and if that is not known, the bigger or older. The pebbles
are then taken, the eldest man's body first, then the youngest woman's,
in a zigzag, into one row (the plain round); folded back under itself
while the rows are equal (the fold, ཁུག་པ, khug pa), a pebble left over set
at the side (the leftover); folded again (the refold, ཡང་ཁུག, yang khug);
the first, middle and last rows are the god's, people's, house's and
wealth's places, the lowest the enemy's. A worked layout of five people
follows (p. 490, a table of numbered pebbles). The notes add that some
reckoners count the people gathered in the house that year, or only the
year's four fought against the person, and that WB does not; and that the
leftovers are found by dividing the count by three.

So the household round needs, for every member, the **birth year** (for
the four aspects and the mewa of the year of age), the **gender**, and the
**order of birth** where two share a year; it needs no date of death.

### The progressed sign on another's year (p. 388)

(p. 388, img. 398, l. 10–15, read on the scan):

> ལོག་མེན་རང་ལོའི་ཐོག་ཏུ་ཕྱིན། །རང་གི་ལུས་ཀྱིས་རང་མི་ཐེག །མི་ཐེག་པ་ཡི་ཁུར་པོ་འབབ། །ཆུང་མའི་ལོ་ཐོག་ཁྱོ་བོ་ཕྱིན། །ཧོམ་དུ་ཆུད་དེ་ཆུང་མར་ངན། །ཁྱོ་བོའི་ཐོག་ཏུ་ཆུང་མ་ཕྱིན། །བདུད་ཞུ་གོན་ཞེས་ཁྱོ་ལ་ངན། །ཕ་ཡི་ལོ་ཐོག་བུ་ཕྱིན་ན། །དོ་མ་ཁྲིད་དེ་ཕ་ལ་ངན། །བུ་ལོའི་སྟེང་དུ་ཕས་ཕྱིན་ན། །ཕ་བུའི་རོ་འཛིན་བུ་ལ་ངན། །ལོ་དཀར་ཐོག་ཏུ་ཕྱིན་པ་ན། །ཉེ་བར་མྱ་ངན་འོང་སྟེ་ངན། །བདུན་ཟུར་ཐོག་ཏུ་ཕྱིན་པ་ན། །སྲོག་གཅོད་མདུན་སར་སླེབ་སྟེ་ངན། །ཕ་མ་ཤི་བའི་ལོ་ཐོག་སླེབ། །གཤིན་གྱི་སྲིད་པར་ཕྱིན་ཏེ་ངན།

The progressed sign on one's own year: one's body cannot bear oneself, a
burden too heavy falls. The **husband's** progressed sign on the
**wife's** birth year: "gone into the trap", bad for the wife. The wife's
on the husband's: "wearing the māra's hat", bad for the husband. The
**son's** on the **father's** year: "leading the ghost", bad for the
father. The father's on the son's: "father holding son's corpse", bad for
the son. On the white year (ལོ་དཀར, lo dkar; not identified), sorrow comes
near. On its seventh, the life-cutter arrives in front. On the year in
which **father or mother died**: "gone into the existence of the dead",
bad. No remedy is named here beyond the progressed sign's general ones
(p. 387, above). Needs: the spouse's and the father's (or son's) birth
years, and the year of each parent's death.

### The spouses' shared trigram, the ancestors' tombs, the dead and the secret obstacle (p. 390)

(p. 390, img. 400, l. 8 to the end of the page, read on the scan):

> བཟའ་མི་སྤར་ཁ་གཅིག་སྟེང་ཚོགས། །བཟའ་ཤུག་དག་གི་རྩུབ་པ་སྟེ། །སྐྱིད་སྡུག་རྣམ་གཉིས་འབྱུང་དུས་ཡིན། །རིམ་གྲོ་སྤར་སྨེ་བཞིན་དུ་བྱ། །ཆགས་ཅན་བྱེས་སུ་བྲོས་ན་ཕན། །ཕྱི་ནང་བར་གསུམ་རོ་འདོད་ཡིན། །རོ་སྙིང་ཕྱུང་ཞིང་ཁྱི་རོ་འདྲུད། །ཁྱད་པར་རྐྱ་མདའ་དབྱེ་བསྲུང་བྱ། །གཤིན་ཁྲལ་གཞལ་ཞིང་རོ་དབྱུང་གི། །གཏོ་ཆོག་བྱས་ན་ཉེས་པ་ཞི།། ཕ་མེས་ཤི་བའི་སྲི་དུས་ལ། །སྤར་སྨེ་ཕྱི་གཟའ་ལོ་གྲངས་སླེབས། །སྲི་ངན་ཕ་མེས་དུར་ཀེག་སྟེ། །དུར་ན་བརྒྱགས་ཆད་གཤིན་ཀུན་འཐབ། །གཤིན་ལག་གསོན་ལ་གཡབ་མོ་འདེབས། །དུར་མཚུན་གསོལ་ཞིང་དུར་ཁ་ནི། །རྒྱ་ཡིས་བཏབ་སྟེ་ཕ་དུར་དང་། །མ་དུར་རུས་པ་གསར་དུ་འདུར། །ཕལ་ཆེན་བཀླག་ཅིང་དུར་ས་སྤོ། །ཡང་ན་སྤོ་ཚུལ་དུར་སར་ཁྲོམ། །བྲི་ཞིང་སཱཙྪ་ཁང་བུ་ལ། །གསང་བཏོད་མར་མེ་འབུལ་བར་བྱ། །གསོན་ཀེག་གཤིན་ཀེག་འདམ་པས་ན། །གསོན་གཤིན་ལག་སྦྲེལ་བཅད་ན་བཟང་། །སྔར་འདས་གཤིན་པོ་རྟིང་མ་ཡི། །ཤི་བའི་ལོ་ཡི་ཕྱི་ནང་གསང་། །ཤི་ལོར་བྱས་ནས་གསོན་ཀེག་བཞིན། །རྡེལ་བོར་ནག་ན་ཤི་ཆད་དང་། །ནོར་གོད་ཡ་ག་བླ་འཁྱམ་ཞེས། །བརྗོད་པ་འཁྲུལ་ཏེ་རྙིང་རྒྱུད་ལྟར། །སྤྲོས་བཤད་རྒྱུད་མཐུན་བརྩི་འདོད་ན། །བརྩི་བྱ་དེར་ལྟོས་བདུན་བརྒྱུད་ཀྱི། །ཤི་བའི་གནམ་ལོས་གཤིན་ལོ་བྱས། །དོ་གཅིག་གནམ་ལོ་དང་འཐབས་པའི། །རྡེའུ་ནག་དང་ར་འབད་དང་། །དགུ་དུར་རང་ཀེག་བདུན་ཟུར་མང་། །གཤིན་པོ་ཀེག་ཆེ་གསོན་ལ་རྩུབ། །ཞེ་བདུན་སྙིང་པོར་འདི་མི་དགོས། །ཅི་ཕྱིར་ཕ་མེས་ཁུ་ཕོ་བོའི། །སྲི་དུས་དོག་བཤད་གོང་ལྟར་གཉན། །དགོས་དོན་སྙིང་པོ་དག་དོར་ནས། །ཡན་བཤད་ཚིག་སྦྱོར་བྱེད་རྣམས་འཁྲུལ། །རུས་བརྒྱུད་ཕྱི་མ་དུར་སོང་གི། །གནམ་ལོའི་ཕྱི་ནང་གསང་བ་དང་། །གཤིན་པོའི་སྲོག་ལུས་དབང་ཀླུང་བླ། །འཐབས་པའི་རྡེལ་ནག་གང་བབས་ཀྱིས། །ཤི་ཆད་ནད་གོད་ཡ་ག་བླ། །འཁྱམས་པའི་འབྲས་བུ་བརྗོད་པ་སོགས། །སྤྲོས་པ་ལུགས་གཞན་འདོད་པ་ཡོད། །གསང་ཀེག་རྐུན་པོ་རང་སྐྱེས་ཀྱི། །སྤར་སྟེང་སླེབ་དང་མ་སྤར་ལི། །བུ་ལ་གསང་ཀེག་མ་སྤར་ཁམ། །བུ་མོར་གསང་ཀེག་ཚོར་བ་དཀའ། །དངངས་སྐྲག་དལ་ཡམས་ནད་སྙིང་ངམ། །བརྔན་གྲོགས་མཐོ་དམོད་ཁ་མཆུ་དང་། །ཉེ་ཁག་ཡ་རབས་ཡིད་ཚང་ཤོར། །མ་རབས་གྱོད་འོང་ངན་པ་ཡི། །ཡས་བདུད་གཡོས་ཤིང་མས་སྲིད་ལྡང་། །བཙན་རྒྱལ་ཆོ་འཕྲུལ་འགྱེད་པའི་དུས། །ཤིན་ཏུ་སྣད་པས་ལུས་ནད་དང་། །སེམས་ནད་གང་རུང་འབྱུང་བར་ངེས། །མུན་སྲོད་སྒོ་སྦུག་དོག་འཛུལ་རྨི། །ཡང་སྲིད་མ་ཡི་མངལ་འཇུག་བརྡ། །ནད་པའི་སྔས་སུ་འགྲིམ་འགྲུལ་དང་། །མྱ་ངན་མི་བྱ་མནའ་བཀར་སྤང་། །ཚེ་དཔག་མེད་མདོ་འབུམ་བཀླག་བྱ། །རུ་ཤན་ཁ་སྤྲོད་བདུད་མདོས་དང་། །མ་ཡོད་མ་མེད་དང་སྦྱར་བའི། །མ་ཀླུངས་གཏོ་ཆོག་རྒྱས་པར་བྱ།

(«རྐྱ་མདའ» as the scan shows it; the etext has «རྒྱ་མདའ»; neither
identified.)

**The spouses' obstacle** (བཟའ་ཤུག, bza' shug). When members of the
household have their trigram of the year of age on **one trigram
together**, it is the harsh year of spouses: a time when both joy and
sorrow come; the rites as for the trigram and mewa; it helps if the one
"with attachment" goes away from home; the outer, inner and middle "want
a corpse": take out a corpse's heart, drag a dog's corpse, guard
especially the *rkya mda'* (not identified), pay the tax of the dead and
do the rite of taking out the corpse. The notes (vol. 2, p. 492, img. 500,
read on the scan): «བཟའ་མི་སྤར་ཁ་གཅིག་ལ་ཚོགས་པ་ནི་ཁྱོ་ཤུགས་ལས་གཞན་རབ་བྱུང་སོགས་ལ་མེད་ཀྱང་། ཁྱིམ་གཅིག་ལ་ཟམ་ཐུག་གི་ཉེས་པ་ཙམ་ཡོད་དོ།»: household members gathered on one
trigram: though for others than husband and wife (monks and the like)
there is no *bza' shug*, for those of one house there is the fault of
the bridge's end (ཟམ་ཐུག, zam thug). So the spouses' obstacle needs **both
spouses' birth years and genders** (each one's trigram of the year of
age, ch. 21, p. 257), and the lesser fault the other housemates'.

**The ancestors' tomb obstacle** (དུར་ཀེག, dur keg). At the time of the
harmful spirits of the dead (སྲི, sri) of the forefathers' death, when the
trigram, mewa or progressed sign (*phyi gza'*) of the year reaches "that
number of years", the evil *sri* is the forefathers' tomb obstacle: in the
tomb provisions are cut, all the dead fight, the hand of the dead
beckons to the living. Remedies: offer to the tomb, seal the tomb's mouth
with a seal, rebury the bones of the father's and mother's tombs anew,
read the *Avataṃsaka* (ཕལ་ཆེན, phal chen), move the tomb site or draw a
picture of moving it, offer lamps at a *tsha tsha* house. The living
person's obstacle and the dead's join, so cut the joined hands of the
living and the dead. The notes (p. 492, img. 500, read on the scan) gloss
it «ཕ་མེས་ཤི་དུས་ཀྱི་སྤར་སྨེ་ལོག་མིན་ཡིན་ནོ།», "the trigram, mewa and progressed
sign of the forefathers' time of death". Read so, the obstacle falls
when one's trigram, mewa or progressed sign of the year is the one of a
forefather's death year (whose, below). Needs: **the year of death** of
the father, mother and forefathers, and their birth years if their own
signs at death are meant.

**The obstacle of the dead** (གཤིན་ཀེག, gshin keg). Some reckon a dead person
who died earlier like a living one, his death year standing for his
birth year, with the outer, inner and secret circles, and read black
pebbles as death, loss and the la wandering: WB calls this an error after
the old tantras. Those who want the full elaboration take, for seven
generations back of the one reckoned, each one's death year as the dead
year, fought against the current year, with black pebbles, the *ra 'bad*
(not identified), nine-multiples, tombs, own obstacles and sevenths: "the
dead's obstacle great, harsh for the living". WB: "this is not needed in
the forty-seven of the heart", for the fathers', forefathers', uncles'
and elder brothers' *sri* times are already counted dangerous above;
dropping the point and making up sentences is an error. Another view
fights the outer, inner and secret circles of the current year against
the life force, body, power, luck and la of the last of the line who went
to the tomb: "other ways that elaborate". So **WB's own reckoning does not
need the death years of seven generations**; it reports that view and
rejects it.

**The secret obstacle** (གསང་ཀེག, gsang keg), "the thief": when the trigram
of the year of age reaches **one's own natal trigram**; and, for a son,
when the **mother's trigram** (མ་སྤར, ma spar) is on li, for a daughter on
kham. It is hard to notice: fright, sudden epidemics, heart illness;
disputes with friends and the high; the close and noble lose heart; the
low bring lawsuits; the māra above stirs, the *srid* below rises; the
*btsan* and kings show their power: illness of body or mind is certain.
Dreams of entering a dark door at dusk, signs of entering a mother's
womb. Do not go to a sick person's pillow, do not mourn, keep from oaths;
read the *Aparimitāyus* sūtra a hundred thousand times; the *ru shan kha
sprod* (not identified) and the māra's thread-cross (མདོས, mdos); and do
the mother's luck rite in full, according to whether the mother is living
or dead. The notes (p. 492, img. 500, read on the scan):

> གསང་ཀེག་ནི་རང་སྐྱེས་སྤར་སྟེང་སླེབས་པ་དང་། བརྩི་བྱ་ལི་ལ་བབ་དུས་བུ་ལ་གསང་ཀེག་དང་། ཁམ་ལ་བབ་དུས་བུ་མོར་ཀེག་ཡིན་པས། མ་ད་ལྟ་ཡོད་ན་མ་ཀླུང་དང་བསྟུན་པའི་སྤར་སྨེ་སོགས་བཅོས། མ་མེད་ན་མ་ཡི་དགེ་རྩ་དང་མིང་བྱང་ལ་བརྟེན་པའི་ཀླུང་ཆོག་བྱ་ཞེས་སོ། །

The secret obstacle: the year's trigram reaching one's natal trigram;
and when **the one reckoned** falls on li it is a son's secret obstacle,
on kham a daughter's. If the mother is alive, the remedy is the trigram
and mewa rites suited to the mother's luck; if not, a luck rite with
merit made for her and her name card. The notes read WB's «མ་སྤར་ལི» as the
person's own trigram of the year on li, not the mother's (question
below). Either way the secret obstacle needs **the mother's birth year**
(the natal trigram, RD13 above: her trigram of her year of age in the
child's birth year) and, for the remedy, **whether the mother is
living**.

### The household's trigrams piled up (pp. 396–400)

After each trigram's own readings (pp. 391–396, above) come the general
ones (p. 396, img. 406, l. 11, read on the scan):
«དྭ་ནི་སྤར་ཁ་སྤྱི་འགྲེལ་ལ། །ཁྱིམ་གཅིག་བཟའ་གྲངས་མང་ཡོད་དེ། །ལིར་(༡)སྤུངས་སྤྱི་གང་གནམ་རྡིབ་ཡིན།», now the general
reading of the trigrams: where one house has many members, (1) piled
(སྤུངས, spungs) on li, "the sky falls". These are **the household's
trigrams of the year of age**, many on one trigram, then pairs and sets
of trigrams; the 1996 editors number the cases ༡–༢༥. From Yigdzin-1, the
lines named read on the scan:

- piled on each trigram (p. 396, img. 406, the scan; pp. 396–397,
  img. 406–407): li, the sky falls, bad for a daughter in her prime,
  epidemics; khon, the earth's centre torn, bad for old women and for
  cattle and sheep; dwa, the māra's army stirs, bad for a middle son;
  khen, the ridge torn, bad for old men; kham, the ocean stirred, bad for
  a small boy; gin, bad for the eldest son; zin, bad for the youngest
  daughter; zon, bad for the eldest daughter; each with its remedy, and
  "for all piling, scattering the members is precious";
- the four māra doors, the four "spear-points" of the corners, east and
  west three, khen and khon together, li and kham, dwa and zin, li and
  khen, khon and kham (p. 397, img. 407, Yigdzin-1);
- by the members' places (gin and zon, khon and zon … to ༢༤): which
  members are endangered ("the old or the young", "the man in his prime",
  "a woman near"), with ransoms and rites (p. 398, img. 408, Yigdzin-1);
  among the remedies «བཟའ་མི་ལོ་མཐུན་བསྡེབས་བྱས་ལ། །སོ་སོར་སྐྱེད་པོ་ཟན་གསུམ་འཛུགས།», "join the members of
  harmonious years and set up three dough figures each";
- the "five heart-holds" (p. 399, img. 409, l. 6–11, Yigdzin-1): many of
  one's own year on one's trigram with a friend's year among them (li,
  many horse and snake with one or two bird or monkey; zin, tiger and
  hare with an earth animal; kham, mouse and pig with horse or snake; dwa,
  bird and monkey with tiger or hare; khon, the earth animals with mouse
  or pig): mother and son years, middling; enemy and slayer years, a
  gathering of bad conditions.

**By the members' natal trigrams** (p. 399, img. 409, the last two lines,
to p. 400, img. 410, l. 3, read on the scan):

> གཞན་ཡང་འདི་ལྟར་བརྗོད་ཀྱང་ཡོད། །བཟའ་མི་ཁོན་ཁམ་སྐྱེས་པ་དེ། །གིན་གྱི་སྟེང་དུ་ཕྱིན་པ་ནི། །ཕོ་དེ་བདུད་ཀྱི་རྒྱ་རུ་ཆུག །བདུན་ཟུར་ལོ་པས་བདུད་བྱས་མིར། །བདུད་ཀྱི་ཞལ་བསྒྱུར་ཉ་རྒྱ་བཤུ། །བདུད་ནག་བཅུ་གསུམ་མདོས་བསྐང་བྱ། །དབང་བསྐུར་ལྷ་གསོལ་རྣམ་གྲངས་འགབ། །དྭ་ཁམ་ཁེན་གསུམ་ཁྲེམས་ཀྱང་ཆུག །མོ་དེ་གཤིན་རྗེའི་ཁྲོམ་ལ་བཏབ། །བཟའ་མི་དྭ་ཁམ་བུད་མེད་ནི། །ཟོན་ཐོག་ལ་ཕྱིན་ཁྲོམ་བཏབ་ཀྱི། །ན་བ་མི་འཆི་མི་ན་འཆི། །ཁྲུས་དང་མིང་བསྒྱུར་ཚེ་དབང་ཞུ།

"Another view: members **born on** khon and kham who come onto gin (with
the year's trigram): the man has gone into the māra's net; a person of
his seventh year acts as the māra; the māra's mask, a fish-net, the black
māra's thirteen thread-crosses, empowerments and offerings to the gods.
When dwa, kham and khen are in a row (ཁྲེམས, khrems), the woman is cast
into the Lord of Death's market. A woman member born on dwa or kham who
comes onto zon is cast into the market: the sick one does not die, the
one not sick dies; washing, a change of name, a longevity empowerment."
The notes (p. 492, img. 500, read on the scan):
«བཟའ་མི་གཉིས་ལས་གཅིག་ཁོན་གཅིག་ཁམ་ལ་སྐྱེས་ཤིང་གཉིས་ཀའི་བབ་སྤར་གིན་ལ་འདོམས་ན་ཞེས་སོགས་སོ།», of two members one born on
khon and one on kham, both their trigrams of the year meeting on gin; and
of «ལི་ཟིན་ཟོན་གསུམ་ཁྲོམ», that the first two named are the members' **natal
trigrams** (སྐྱེས་སྤར, skyes spar). So these readings need **two spouses' or
housemates' natal trigrams** (each one's mother's birth year, RD13) and
their trigrams of the year of age. The page goes on (p. 400, img. 410,
Yigdzin-1, the editors' numbers) with piling that is good: zin eightfold
good, gin fivefold, khen eightfold; mother-and-son trigrams on the four
"seats".

### The household's mewa (pp. 407–409)

(p. 407, img. 417, the last eight lines, read on the scan; p. 408,
img. 418, to l. 17, Yigdzin-1, l. 13–17 read on the scan):

> ད་ནི་སྤྱི་ཡི་ཁ་དམར་ལ། །ཁྱིམ་ནང་གཅིག་དཀར་མང་ཚོགས་ན། །ཤི་འོས་མེད་ཀྱང་རོ་འོང་དུས། …། །ཁྱིམ་ནང་དཀར་གསུམ་མང་ཚོགས་ན། །ཕོ་ལྷ་གནམ་དུ་ཡར་དུས་དེ། …། །ནག་པོ་མང་ཚོགས་ཡ་བདུད་གཡོས། …། །གསུམ་མཐིང་མང་ཚོགས་ས་བདག་འགྲས། …། །ལྔ་སེར་མང་ཚོགས་རྒྱལ་འགོང་གནོད། …། །བདུན་དམར་མང་ཚོགས་དགྲ་སྲི་ལྡང་། …། །དགུ་དམར་མང་ཚོགས་ཚེ་གཡང་ཉམས། …། །ཁྱིམ་ནང་སྲོག་ལ་དགྲ་དར་མང་། …། །ཡང་ཅིག་སོ་སོའི་གཉིས་ནག་བསྟན། །གཅིག་དཀར་བུ་ཆུང་(བྱིས་པའི་)གཉིས་ནག་ཡིན། །གཉིས་ནག་ཀུན་ཡིན་སྐྱེས་དར་གནད། །གསུམ་མཐིང་བྱིས་པ་(ཤར་པོའི་གཉིས་ནག)བཞི་ལྗང་ཡུགས་ས(འི་གཉིས་ནག་ཡིན།)། །ལྔ་སེར་བརྗེ་དྲུག་དཀར་བོན། །བདུན་དམར་བུད་མེད་གཉིས་ནག་ལ། །བརྒྱད་དཀར་ཞང་བློན་གྲ་མའི་ཡིན། །དགུ་དམར་བཙད་པོ(རྒན་རྒོན།)ཡིན་ཏེ་གནད། །དེར་བབས་ངན་ཏེ་རིམ་གྲོ་བསྐྱེད།།

The general verdicts on the mewa: when in one house **many members' mewa
of the year** are the same, by colour: many one white, a corpse comes
though no one is due to die, gods' illness, epidemics; many of the three
whites, the male god goes up to the sky; many black, the māra above
stirs, illness, harm to servants, horses, dogs and cattle, separation;
many three blue, earth lords offended; many five yellow, kings and *'gong
po*, bile illness; many seven red, enemies and *sri*; many nine red, life
and fortune spent; many with the enemy of their life force or power on
red mewa, bad for livestock; many black with one white, many white with
one black, each with its image; each one's mewa rite. Then **each kind of
person's own "two black"** (the mewa that counts as that person's two
black): one white for small boys (children); two black for all, the crux
for men in their prime; three blue for children; four green for widows
(ཡུགས་ས, yugs sa); five yellow and six white (the line not clear, below);
seven red for women; eight white for ministers and monks; nine red for
the elders: to fall there is bad. Then the mewa's four small obstacles
(read above); and (p. 409, img. 419, l. 1–3, read on the scan)
«བཟའ་མི་གཉིས་ཀ་གཉིས་ནག་ཕྱིན། །རོ་འཛིན་དཔུང་པ་མཆེ་བ་བརྗེས། །གཉིས་ནག་རྒྱབ་འདུག་སྔར་འདས་རྐྱེན། །མདུན་ཡོད་མ་འོང་ལོ་ཚིགས་བོར། །ཕོ་ལ་ལོགས་བབས་མོ་ཟུར་ཉེན།»: when **both spouses'** mewa of the
year come onto two black, "holding the corpse, shoulder and fang
exchanged"; two black behind is the cause of a past death, in front of one
not yet come: years to beware; for the man on the side, for the woman at
the corner, danger. Needs: **every household member's birth year and
gender** (the mewa of the year of age, ch. 21, p. 256) and, for the "two
black" of each kind, his or her **age and station** (child, man in his
prime, widow, woman, monk or minister, elder).

### The births on the eight trigrams: WB ff. 180b–182a (pp. 286–288)

The ROADMAP's "WB ff. 180b–182a" (from P2, after Dorje) are the Zhol
print's folios: in WBZ (I1KG1710, one side a scan) img. 363 is marked
f. 181 in its left margin and img. 365 f. 182 (Tibetan and Chinese, read
on the scan), so ff. 180b–182a are img. 362–365. Their text, matched in
both etexts, is ch. 22's **births on each of the eight trigrams**
(ནང་རུས་སྤར་ཆེན་སྡེ་བརྒྱད, nang rus spar chen sde brgyad), in the 1996 edition
p. 286, l. 19 to p. 288, l. 4 (img. 296–298); the mewa births follow. RD13
outlined these pages from the etext ("births on each mewa",
pp. 286–293) but did not read them; they are not the pages RD13 quoted
(pp. 271–272, 294, 326, 347). Read here on the scan (img. 296 l. 19 to
img. 298 l. 4, at 2.5–4.5×):

> ནང་རུས་སྤར་ཆེན་སྡེ་བརྒྱད་ལས། །ལི་སྟེང་འདི་རུ་གང་སྐྱེས་པ། །དཀྱེལ་ཆེ་ཕྲ་མ་དག་ལ་དགའ། །ཕ་དང་གྱོས་པོའི་གདན་ས་འཕྲོག །མ་ཉེས་ཁ་ཡོག་རྟག་ཏུ་འོང་། །མགོ་དང་དབང་པོ་སྐྱོན་ཅན་འོང་། །ཕ་ཡིན་དམར་པོ་ཁྲག་ངོ་ཡོད། །གྲི་དང་ཐབ་གཞོབ་ས་ཡིན་ཏེ། །ཁྱེད་ཀྱི་ཕ་དང་མེས་རང་ནས། །གྲི་ཁ་ཆེན་པོ་བྱེ་གྱུར་ཏེ། །དེ་ཡང་མགོ་གྲི་མང་བ་ཡིན། །མོ་ཡིན་ཟླ་རྡིབ་བྱིས་འཇུར་འོང་། །ཐབ་གཞོབ་འཁོན་གྱིས་རྐྱེན་བྱས་ནས། །ལྷ་ཀླུ་འཕངས་པས་གཉན་ནད་དང་། །བུ་ཚ་སྐྱོན་ཆེན་འོང་ཉེན་ཡོད། །ཕ་མེས་ཤིན་ཏུ་དར་བའི་ཚེ། །བཞི་དང་ལྔར་ནི་གྱེས་པ་སྟེ། །ཡུལ་ལམ་དུར་ས་རྩུབ་མོ་ནི། །ལྷོ་དང་ནུབ་ཏུ་ཁ་བལྟས་ཡོད། །ལྷ་ནི་རྒྱལ་པོ་བཙན་བདུད་འགོ། །དེ་བཞིན་གནོད་བྱེད་འགོང་པོས་བྱེད། །ས་བདག་གནོད་ན་བཟློག་པར་དཀའ། །ཁོན་སྟེང་འདི་རུ་གང་སྐྱེས་པ། །དུར་འདྲེ་དུར་སྐྱོན་ས་ཞེས་བྱ། །ཕ་མེས་དུར་གདབ་ཉེས་པ་ནི། །དེ་ཡི་སྐྱོན་གྱིས་མི་བརྒྱུད་ལ། །གླེན་ལྐུགས་ཀླུ་དང་འགྲས་པ་དང་། །ཞ་འཐེང་འཐིབས་གྲིར་ཤི་བའང་། །ཕ་མེས་ཉིས་བརྒྱུད་གང་སྟོངས་ཆེ། །བློ་བརྟན་གཞན་གྱིས་བཅོས་པར་དཀའ། །དམོད་མོ་སྐྱེན་ཞིང་སྡུག་བཞིན་ནག །རྟག་ཏུ་ཞེ་འཁོན་ན་ཚ་མོད། །བུ་ཚ་གསོ་དཀའ་བྱེ་ཡུགས་བྱེད། །ཁ་འཇམ་གྱོག་ཆེ་ཁྲོ་ཞིང་གཏུམ། །རོ་བག་མི་གཙང་བ་ལ་གཟབ། །ཚེ་སྟོད་ནོར་ཆེ་ཚེ་སྨད་དབུལ། །ཡུལ་དང་དུར་ནི་སེར་སྐྱ་བ། །མདུན་ན་ལམ་གྲོག་གིས་ཚོད་ཡོད། །ལྷ་རུ་ནག་སྨུག་ཀླུ་སྨན་འགོ །གདོན་དུ་ས་བདག་ཀླུ་མོ་གནོད། །དྭ་སྟེང་འདི་རུ་གང་སྐྱེས་པ། །དམེ་གྲི་དྭ་ཡི་ས་ཞེས་བྱ། །ཕ་མེས་དམེ་གྲི་འཁོར་བ་ཡིས། །ངན་ཐབས་བྱས་ནས་བསད་པ་ཡོད། །དཱའུ་གོ་གསོས་བྱས་པ་དང་། །དམེ་ཅན་དང་དུ་བླངས་པའོ། །གཡུ་ཁྲབ་ཕྱི་ནས་འོང་བ་ཡི། །འདྲེ་གནོད་བྱེས་སུ་མི་ཤི་ཡོད། །ཕ་མེས་གསུམ་མམ་བཞི་བརྒྱུད་ཡིན། །ཡུལ་དུར་སེར་རམ་ནག་པ་ནི། །ནུབ་བམ་བྱང་དུ་བལྟ་བ་ཡོད། །ནོར་རྫས་ཅུང་ཞིག་སྲབ་པ་སྟེ། །སེམས་དཀར་སྤྲོ་ཐུང་ཁོང་ཁྲོ་འཕེལ། །གཡོ་སྤོགས་ཆུང་ལ་དྲག་རྩལ་ཆེ། །སྙིང་སྟོབས་ཆེ་ཞིང་ཡོན་ཏན་འཕྲོད། །ཕོ་མོ་འདྲ་བར་ཁྱིམ་སོ་འདྲེན། །ཞང་ཉེའི་ཕྱོགས་སུ་མནའ་ཟ་ལ། །ས་དང་ནོར་ནི་གང་སྟོངས་ཆེ། །ཛ་མ་གྲིར་འཆི་གཟེར་ནད་མོད། །བཙན་འགོ་གྲེ་བོ་གནོད་པ་ཡིན། །ཁེན་སྟེང་འདི་རུ་གང་སྐྱེས་པ། །ཞ་འཐེང་གང་སྟོངས་ས་ཞེས་བྱ། །ཕ་མེས་ཞ་འཐེང་སྒུར་པོ་དང་། །གླེན་ལྐུགས་ཅན་དང་དལ་རྒྱབ་ཡོད། །དགྲ་སྣ་ཅུང་ཟད་འབྱུང་བ་དང་། །ཡུལ་ལ་འཕོ་འཕྱུགས་འོང་བ་དང་། །སྐྱིད་སྡུག་གང་སྟོངས་ཆེ་བ་ཡིན། །ཕ་མེས་གཉིས་སམ་བཞི་བརྒྱུད་དེ། །མི་ཡི་གང་སྟོངས་ཆུང་བ་འོང་། །ཡུལ་དུར་དམར་སེར་ནུབ་བྱང་བལྟ། །ང་རྒྱལ་ཆེ་ལ་ལྷ་འཕང་མཐོ། །འབད་ནས་བྱས་ཀྱང་མགུ་བ་ཉུང་། །ཐབས་མཁས་སྐྱིད་སྡུག་རྐྱེན་ལ་བརྟག །ཕ་དང་ཁུ་པོ་དག་ལ་གཟན། །འགོང་པོ་འགོ་ཞིང་རྒྱལ་པོ་གནོད། །ཁམ་སྟེང་འདི་རུ་གང་སྐྱེས་པས། །བཙོག་མནོལ་སྐྱས་ནག་ས་ཞེས་བྱ། །ཕ་མེས་མི་ལག་པར་ཤོར་རམ། །ཡང་ན་ཚུར་བླངས་ནལ་བྱས་སམ། །བུ་སྲིང་མ་སྤུ་རབས་ཆད་ཀྱི། །རྫས་ནག་སྐྱས་ཀྱིས་སྙད་བྱས་ནས། །ཕ་མིང་དམའ་ལ་བུ་སྲིང་མཐོ། །མནའ་མ་བྱེ་བྲལ་བྱས་འདུས་མང་། །ཕ་མེས་ལྔའམ་བཞི་བརྒྱུད་དེ། །ཕོ་བོ་བརྒྱུད་དར་ཡུལ་དུར་ནི། །དཀར་ནག་བྱང་ངམ་ཤར་བལྟས་ཡོད། །སྡུག་བསྔལ་མོད་ལ་བྱིན་ལ་བྱེད། །ཞེ་ནག་སྣ་ཚོ་མཐུན་པར་དཀའ། །ནང་དུ་ཉོག་ཆེ་ཕྱི་རུ་འཇམ། །མཐོ་སེམས་ཆེ་ལ་རང་ཚགས་དམ། །བུ་ཚ་ནལ་བུའི་ངོ་ཡང་ཡོད། །ལྷ་རུ་ཀླུ་བཙན་དཀར་མ་དང་། །མ་མོ་འགོ་ཞིང་བསེན་མོ་གནོད། །གིན་སྟེང་འདི་རུ་གང་སྐྱེས་པ། །ས་བདག་ཐབ་གཞོབ་ས་ཞེས་བྱ། །ཕ་མེས་མཛེ་ནད་གཉན་ནད་ཀྱིས། །ཤིའམ་གཞན་གྱིས་བཙོག་བྱས་ཤིང་། །ཐབ་གཞོབ་རྐྱེན་གྱིས་གྲིར་ཤི་མང་། །ཡང་ན་བན་བོན་ཡོན་ཏན་ཅན། །འོང་སྟེ་གཉིས་སམ་གསུམ་བརྒྱུད་ཡིན། །ཡུལ་དུར་བྱང་ངམ་ཤར་བལྟས་ཡོད། །བློ་བརྟན་གཞན་གྱིས་བཅོས་པར་དཀའ། །ཤ་ལྕི་ཁ་ཉུང་ཚིག་བརྟན་ལ། །ཕ་གཅིག་བུ་གཅིག་རྨང་པོར་འོང་། །ཕྲ་ལ་ཞིབ་ཅིང་ཆེ་ལ་རྩིང་། །ལྷ་རུ་དགྲ་ལྷ་དྲག་པོ་དང་། །གཤིན་རྗེ་འགོ་སྟེ་ས་བདག་དང་། །ས་ཐེལ་གནོད་པ་ཡིན་ཞེས་བྱ། །ཟིན་སྟེང་འདི་རུ་གང་སྐྱེས་པས། །རབས་ཆད་རྨང་འདྲེའི་ས་ཞེས་བྱ། །ཕ་མེས་རབས་ཆད་སྐྱོན་ཆེན་དང་། །དམེ་དང་དྭ་འུ་གོ་གསོས་དང་། །ཤིང་གཉན་བཅད་པས་འགག་གྲི་འམ། །ཤིང་གྲི་མང་སྟེ་ཆིག་བརྒྱུད་དམ། །ཡང་ན་གསུམ་བརྒྱུད་འོང་བ་ཡིན། །མི་དམངས་དྲག་པོ་འབྱུང་བའི་ས། །ཡུལ་དུར་ལྗང་སྔོན་ཤར་ལྷོར་བལྟ། །མདོག་སྔོ་ཁྲོམ་གལ་ཆེ་བ་ལ། །ཐབས་མཁས་ཟལ་ཆེ་ཡིད་སྣང་སླུ། །བུ་དང་ནོར་གྱི་དབང་ཐང་ཆེ། །ཁ་རྒོད་ལག་འཐོར་ནང་དུ་གྱོང་། །ལྷ་རུ་ཀླུ་བདུད་ཡུལ་བདག་འགོ། །གདོན་དུ་རྨང་འདྲེ་གནོད་པའོ། །ཟོན་སྟེང་འདི་རུ་གང་སྐྱེས་པ། །རྦད་འདྲེ་ཀླུ་གཉན་ས་ཞེས་བྱ། །ཕ་མེས་སློབ་དཔོན་དང་འགྲས་སམ། །ཡང་ན་རྦད་འདྲེ་གཏོང་བ་ཡོད། །ཀླུ་དང་ས་བདག་གནོད་པ་ཡིས། །སྨྱོ་འབོག་ལྕེབ་གྲིར་ཤི་བ་དང་། །མི་འཁྱར་ཡང་ན་བྱེས་ཤི་ཡོད། །ཕ་མེས་གཉིས་སམ་བཞི་ཡིན་ཀྱང་། །ད་ལྟ་ཤིན་ཏུ་རྨང་བ་ཡིན། །ཡུལ་དུར་ཤར་ལྷོའམ་ལྷོ་ཕྱོགས་སུ། །ཕུ་བལྟས་ཡོད་དེ་ཡུལ་ལྷ་དང་། །འཕྱ་འདོད་ཁྲོ་ལ་དགའ་བ་སྟེ། །མཐོ་གནོན་མཉམ་གཉིས་འགྲན་སེམས་ཆེ། །སེམས་ལས་ངོ་སོ་གཉིས་ཀ་ཆེ། །ཐ་མ་གློ་བུར་ནད་ཀྱིས་འཆི། །སྨན་འགོ་རྒྱལ་པོ་འགོང་པོ་གནོད། །དེ་ནི་སྤར་ཁའི་སྐྱེས་ཚད་ཡིན།།

(«གྱོས་པོ», father-in-law, is Yigdzin-1's; the scan's letter is not clear
at 4.5×.)

Of the eight great trigrams of the inner clan, what each brings to the
child born on it (the natal trigram, the mother's trigram at the birth,
RD13): the forefathers' fault and how many generations they were, the
child's character and lot, the direction the home and the tomb face, the
god that goes with it and the spirit that harms:

| Born on | Called | The forefathers | The child | Home and tomb | God with; harm from |
| --- | --- | --- | --- | --- | --- |
| li | the place of blades and the hearth | flourished greatly, then split into four or five; from the father and grandfather "a great blade", many deaths by the blade | loves gossip and slander; takes the seat of father and father-in-law; blamed without fault; faults of the head and senses; a man has red signs of blood; a woman a fallen moon (miscarriage), a child's death; *gnyan* illness from hearth grudges; great faults in the children | facing south and west | kings, *btsan*, māras; *'gong po*; the earth lords' harm hard to turn back |
| khon | the place of the tomb spirit and the tomb's fault | a fault in laying a tomb; two generations, great loss | the line foolish and mute, at odds with the nāgas, hunchbacked, lame, dying by the blade; curses come fast; grudges, illness; children hard to raise, separations; soft speech, much pride, angry and fierce; rich early, poor late; careful with corpses and impurity | yellowish | black-brown nāga goddesses; earth lords and nāginīs |
| dwa | the place of the *dme* blade | a *dme* blade among them: a killing by foul means; an orphan raised, one with *dme* taken in; three or four generations | wealth a little thin; white-minded, short of patience, anger grows; little guile, great vigour, strong courage, gains in learning; man and woman alike keep the house; an oath broken on the mother's brothers' side; land and wealth lost; *dza ma* (not identified) dies by the blade; stabbing pains | yellow or black, facing west or north | *btsan*; the dead of violence (*gre bo*) |
| khen | the place of the lame and of loss | hunchbacks, the lame, the foolish and mute; two or four generations | a few enemies; moves of home; much loss of joy, little of people; proud, high-placed gods; little satisfied though he strives; clever, takes the conditions; feeds on father and uncle | red-yellow, facing north-west | *'gong po*; kings |
| kham | the place of impurity, pollution and black gifts | one fell into others' hands, or one was taken in in incest; brother and sister's line cut, black gifts; the father's name low, the brothers' high; brides separated; five or four generations | quick to suffer; black-hearted, hard to agree with; troubled within, smooth without; proud and tight; signs of children of incest | white-black, facing north or east | nāgas, *btsan*, white goddesses, *ma mo*; *bsen mo* |
| gin | the place of the earth lords and the hearth | died of leprosy or *gnyan* illness, or defiled by others; many deaths by the blade through the hearth; or a learned *ban bon*; two or three generations | stubborn; heavy-bodied, few words, firm in speech; one father, one son, a thin line; fine in small things, coarse in great | facing north or east | the warrior god and the Lord of Death; earth lords and *sa thel* |
| zin | the place of the line cut and the foundation spirit | a great fault of the line cut; *dme*, orphans raised; a *gnyan* tree felled: deaths by the blade, many by wood; one or three generations | a place of fierce common folk; much needs; skilled and crafty; great power of children and wealth; reckless, mean at home | green-blue, facing south-east | nāgas, māras, local lords; the foundation spirit (རྨང་འདྲེ, rmang 'dre) |
| zon | the place of the sorcery spirit and the nāga-*gnyan* | at odds with a master, or sent sorcery spirits; through nāgas and earth lords madness, epilepsy, deaths by the blade or in water, wanderers or deaths abroad; two or four generations, now very thin | loves scorn and anger; wants to equal the high, rivalry; at the end sudden illness | south-east or south | the medicine goddess; kings and *'gong po* |

"That is the trigrams' measure of birth." These are **readings by the
natal trigram**, in the shape of the mewa births that follow, and need
**the mother's birth year** only.

### The nine-multiples that need the parents (pp. 412–413; the notes, pp. 493–494)

Yigdzin-1 and the etext, **not read on the scan**:

- The drum-sickle nine-multiple of birth luck (རྔ་ཟོར་བཙས་ཀླུང་དགུ་མིག, rnga zor
  btsas klung dgu mig, p. 412, img. 422, l. 20, to p. 413, img. 423, l. 1):
  «རྔ་ཟོར་བཙས་ཀླུང་དགུ་མིག་ནི། །གནམ་ལོའི་མདུན་དུ་ལྔ་བགྲངས་པའི། །མཐུན་གསུམ་དེ་ལ་བརྟག་པ་དེ།» … «མདུན་ལྔའི་ཕ་མ་གཉིས་ལ་བརྩི།»:
  the triad counted five ahead of the current year; for one of a wood
  clan, a birth in the wood-female tiger, hare … snake year is bad, and so
  for each element with its years and readings; "the five ahead is
  counted for both father and mother". The notes (vol. 2, p. 494,
  img. 502): «རྔ་ཟོར་བཙས་ཀླུང་དགུ་མིག་ནི་རུས་ཤིང་ཁམས་པ་ལ་རང་ཁམས་དང་མཐུན་པའི་ན་ཤི་དུར་ལོ་པའི་བུ་ཚ་བཙས་ན་ངན་ཏེ་… རུས་མིན་ཀྱང་ཕ་མའི་སྲོག་ལའང་བརྩི་བ་ཡོད་འདྲའོ། །མ་ཡི་རུས་ལས་ཀྱང་དེ་ལྟར་བརྩིའོ། །དེ་ཡང་གནམ་ལོའི་མར་ལྔ་མཐུན་གསུམ་མམ་རྔ་ཟོར་དགུ་མིག་ལ་ཕ་མ་གང་རུང་ཚུད་དུས་བུས་པ་སྐྱེས།»:
  for a wood clan, a child born in the year of illness, death or tomb of
  the element is bad; it seems to be counted also from the parents' life
  force, not only the clan; from the mother's clan too; it is when either
  parent falls in the current year's triad five back, the drum-sickle,
  that a child is born. Needs: **the parents' birth years and clans** and
  the child's birth year.
- The nine-multiple of the "birth disgrace" (སྐྱེས་མཚང, skyes mtshang): the
  notes (vol. 2, p. 493, img. 501, l. 9)
  «སྐྱེས་མཚང་སྣད་ཀྱི་དགུ་མིག་དགུ་ལྔ་ཚུན་མ་དང་དགུ་དྲུག་ཕྱིན་ཕ་ཡི་ལོ་ལས་བརྩི།», read here as "up to nine fives
  (45) from the mother's, from nine sixes (54) on from the father's year"
  (question below); ch. 24's ages of disgrace (p. 410, img. 420) are
  counted from the person alone, above.

### What the obstacles need, for whom

| Obstacle (WB vol. 1) | Whose dates | What is reckoned from them | Reading and remedy |
| --- | --- | --- | --- |
| The secret circle (p. 380) | none: the person's year | a year whose life force is the enemy of the person's | another's view, reported; not WB's forty-seven |
| The household's pebbles (pp. 385–387; notes pp. 490–491) | every member of the house: birth year, gender, order of birth | each one's four decisive pebbles and mewa of the year, laid in rows | the god's, people's, house's, wealth's and enemy's places by colour, with rites |
| The progressed sign on another's year (p. 388) | spouse's birth year; father's (or son's) birth year; each parent's year of death | the person's progressed sign of the year against those years | named images, bad for wife, husband, father or son, or "gone into the existence of the dead" |
| The spouses' obstacle, *bza' shug* (p. 390; notes p. 492) | both spouses' (and housemates') birth years and genders | each one's trigram of the year of age, on one trigram | joy and sorrow both; trigram and mewa rites; "the one with attachment" goes abroad; the corpse rites |
| The forefathers' tomb obstacle, *dur keg* (p. 390; notes p. 492) | the year of death of father, mother, forefathers (and their birth years, if their own signs at death are meant) | the trigram, mewa and progressed sign of the death, met by the person's of the year | the dead fight and beckon; rebury, seal, move the tomb, the *Avataṃsaka*, lamps |
| The obstacle of the dead, *gshin keg* (p. 390) | seven generations' years of death | the dead year's circles | a view WB rejects; not needed |
| The secret obstacle, *gsang keg* (p. 390; notes p. 492) | the mother's birth year; whether she is living | the natal trigram, met by the trigram of the year; the son's li, the daughter's kham | hidden illness of body or mind; the *Aparimitāyus* sūtra; the mother's luck rite by her living or not |
| The household's trigrams piled (pp. 396–400) | every member's birth year and gender; for the "another view", their mothers' birth years | each one's trigram of the year; natal trigrams | ༡–༢༥: who is endangered, ransoms, rites; the good pilings |
| The household's mewa (pp. 407–409) | every member's birth year, gender, age and station | each one's mewa of the year | by colour; each kind of person's "two black"; both spouses on two black |
| The births on the trigrams, ff. 180b–182a (pp. 286–288) | the mother's birth year | the natal trigram | the forefathers' fault and generations, the child's character, home and tomb, god and harm |
| The drum-sickle of birth luck (pp. 412–413) | both parents' birth years and clans | the parents in the current year's triad five back | a child born then; Yigdzin-1 only |

### Open

- Question: the forefathers' tomb obstacle (WB vol. 1, p. 390): «ཕ་མེས་ཤི་བའི་སྲི་དུས་ལ། །སྤར་སྨེ་ཕྱི་གཟའ་ལོ་གྲངས་སླེབས།». The notes (vol. 2, p. 492) gloss it «ཕ་མེས་ཤི་དུས་ཀྱི་སྤར་སྨེ་ལོག་མིན», "the trigram, mewa and progressed sign of the forefathers' time of death". Are these the forefather's own (his trigram and progressed sign in his year of death, which need his birth year) or those the death year gives the person reckoned, and is "reaching the number of years" the person's trigram, mewa or progressed sign coming onto them?
- Question: the secret obstacle (WB vol. 1, p. 390): «གསང་ཀེག་རྐུན་པོ་རང་སྐྱེས་ཀྱི། །སྤར་སྟེང་སླེབ་དང་མ་སྤར་ལི། །བུ་ལ་གསང་ཀེག་མ་སྤར་ཁམ། །བུ་མོར་གསང་ཀེག». The notes (vol. 2, p. 492) read «བརྩི་བྱ་ལི་ལ་བབ་དུས་བུ་ལ་གསང་ཀེག», the one reckoned on li. Is «མ་སྤར» the mother's trigram (of the current year, or at the birth?) or, as the notes take it, the one reckoned's trigram, and what does «མ» then mean?
- Question: WB vol. 1, p. 388: «ལོ་དཀར་ཐོག་ཏུ་ཕྱིན་པ་ན། །ཉེ་བར་མྱ་ངན་འོང» — what is the "white year" (ལོ་དཀར) the progressed sign reaches?
- Question: WB vol. 1, p. 390: what are «རྐྱ་མདའ» (so on the scan; the etext «རྒྱ་མདའ») to be guarded at the spouses' obstacle, «ར་འབད» among the dead's pebbles, and «རུ་ཤན་ཁ་སྤྲོད» at the secret obstacle?
- Question: WB vol. 1, p. 408: «ལྔ་སེར་བརྗེ་དྲུག་དཀར་བོན» in the list of each kind of person's "two black": whose "two black" are the five yellow and the six white («བརྗེ», «བོན»)?
- Question: the notes, vol. 2, p. 493: «སྐྱེས་མཚང་སྣད་ཀྱི་དགུ་མིག་དགུ་ལྔ་ཚུན་མ་དང་དགུ་དྲུག་ཕྱིན་ཕ་ཡི་ལོ་ལས་བརྩི» — is «མ» the mother's year (counted from the mother's year up to 45, from the father's from 54), and is it the parents' age that is counted for the child?
- Question: the household round (WB vol. 1, p. 385; the notes, vol. 2, p. 490): is the household the people living in the house that year (as "some reckoners" count) or the family wherever it lives, and does a member who died during the year still count?
- Doubt: «གྱོས་པོ» (p. 286, img. 296) rests on Yigdzin-1; the scan's letter is not clear.
- Doubt: the drum-sickle of birth luck (pp. 412–413) and the notes' pp. 493–494 rest on Yigdzin-1 and the etext; not read on the scan.

### Searched and not found

- **A worked example of the household pebble round with its readings**
  beyond the notes' layout of five people (vol. 2, p. 490): none in ch. 24
  or the notes' section 2 (etext and Yigdzin-1).
- **The mother's year in the secret circle** (WB vol. 1, p. 380): the
  «མ་ལ་དྲིས» is the secret life force's mother element (the scan; the notes,
  vol. 2, p. 489), not the person's mother.

## Open

- The natal trigram needs the mother's birth year, and the father's
  trigram at the birth (WB p. 326) the father's: whether the app should
  ask for them is the owner's call (RD13 above).
- The four counted signs of the progressed sign: which of the counted
  sign's aspects WB means.
- The Chinese pebble divination (pp. 416–452) is read only far enough to
  see that it is a casting.
