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

- Question: The birth reckoning's count: p. 259 (img. 269) fixes 641 root
  and 2,537 identifying pebbles, 3,178 in all, and p. 262 (img. 272)
  3,773, whose parts as read make 553 result and 3,333 identifying
  pebbles, 3,886. Are these two counts of different scopes, or slips?
- Question: WB p. 262 (img. 272) prints «གཅེས་རྡེལ་མི་འབྱུང་ཀླུང་རྡེལ་དགུ»; the
  nine are the four precious and five unchanging luck pebbles. Is
  «མི་འབྱུང» a slip for «མི་འགྱུར»?
- Question: The decisive pebbles are 24 on p. 260 (img. 270) and 20 on
  p. 261 (img. 271). Which belongs to WB's reckoning?
- Question: WB p. 261 (img. 271) gives the life force's rise and decline
  to the upper part of life by the year, and the month's to «ཚེ་སྨང» (the
  print, with a mark under it). Is the month the middle of life?
- Question: The mother's trigram at the birth: is her trigram counted one
  a year (WB ch. 21, p. 257) or with the *Moonbeams*' leap to the corner
  at each ten (DZ f. 8b–9a)? The natal trigram differs once the mother
  has passed ten.
- Question: The father's luck pebble: WB p. 261 "whatever the father's
  luck is", the notes p. 474 the father's year, and the *Moonbeams* (KD
  vol. 3, p. 501, etext only) the father's progressed sign («ཕ་ཡི་ལོག་མེན་གར་
  བབས»), and it records a view of five unchanging luck pebbles with the
  mother's trigram twice (p. 502, «ཕ་ཀླུང་མ་ཀླུང་བབས་སྤར་དང༌། །རང་ཀླུང་མ་ཡི་སྤར་ཁ»).
  Which is WB's?
- Question: WB p. 294 (img. 304) prints «མོ་ལ་ཆུ་ཡི་བདུད་དུ་སོང» for the fire
  horse and snake born on kham, where the parallel lines have «ལྕགས་ལ་མེ་ཡི་
  བདུད» and «ས་ལ་ཤིང་གི་བདུད». Is «མོ» a slip for «མེ», or does the line hold
  for a woman only?
- Question: «སྐྱེད་པོ་བགྲང» (p. 326, img. 336), said of daughters born on
  certain trigrams: "count husbands", that is, marry more than once?
- For RD11's open question 23: on the scan of vol. 2, p. 474 (img. 482,
  read at 8×) the first «དུས་ཁམས» is printed so, but the second reads
  **«རུས་ཁམས»** («གཞན་ཡང་རུས་ཁམས་དང་ཕ་མེས་བརྒྱུད་པའི་ལོ»): the clan's element, as
  the five great clans that follow imply.

### Searched and not found

- **The natal trigram on the *Moonbeams*' f. 12a** (Dorje, p. 108) in
  Dharmaśrī's *Gsung 'bum* print (I2533, img. 027, read on the scan): that
  folio holds the five nine-multiples; the natal trigram is on f. 9a
  (img. 021). Dorje's folio number is of another print.
- **A worked example of a natal trigram** in WB ch. 22 or the notes'
  section 1 (etext and Yigdzin-1): none; the *Moonbeams*' rule (f. 9a) is
  the only count.

## Open

- The natal trigram needs the mother's birth year, and the father's
  trigram at the birth (WB p. 326) the father's: whether the app should
  ask for them is the owner's call (RD13 above).
- The four counted signs of the progressed sign: which of the counted
  sign's aspects WB means.
- The Chinese pebble divination (pp. 416–452) is read only far enough to
  see that it is a casting.
