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
birth, found from the mother's age (WB's chapter 22, the natal horoscope,
is not read); it is not built.

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

## Open

- The natal trigram needs the mother's birth year: whether the app should
  ask for it is the owner's call.
- The four counted signs of the progressed sign: which of the counted
  sign's aspects WB means.
- The Chinese pebble divination (pp. 416–452) is read only far enough to
  see that it is a casting.
