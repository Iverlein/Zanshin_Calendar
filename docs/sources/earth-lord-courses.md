# The earth lords that move by date

Sigla, numbering and quoting rules are in [README.md](README.md). WB vol. 2,
1996 edition, ch. 31, pp. 226–235 (scans I1KG12907, img. 234–243: in vol. 2
the printed page is the image number − 8), the section «ཚེས་ལ་རྒྱུ་བའི་ས་བདག»,
"the earth lords that move by date", which follows the reckoning for the dead
([earth-lords.md](earth-lords.md)) and ends where the earth lords of the hour
begin (p. 235, «གཡུ་མཛོད་སྔོན་མོ་དུས་ཀྱི་ལྷ»). ROADMAP T2.13, read and built
2026-10-09.

**How it was read.** Yigdzin-1 (`tools/sources/hf_read.py`) on the scans
as the base; BDRC's etext of the edition and MITRA as witnesses (MITRA agrees
with Yigdzin-1 on 85–98% of syllables a page, but reads img. 235 as noise,
13%; the etext drops whole lines on pp. 226–227 and 232–233). Every line that
carries a date, a month, an animal, an hour, a direction or a work was read
on the scan at 2.2–2.5×; the remedies are summarised from Yigdzin-1 and the
etext, not read syllable by syllable. Corrections of Yigdzin-1 made on the
scan: «དཔྱིད་ཐའི་ཉེར་གཅིག» (Yigdzin-1 རའི), «ཐ་ཆུང་གནམ་གང་ལྐུགས་པ་རྡོལ» (རྔ་ཆུང, ཞུགས),
«ཆེ་འདོན» (ཚེ), «གཤིན་བག» (ཕག), «ཁྱི་བརྒྱད་ཕག་གཅིག» (བྱི), «ཁྱི་ཟླའི་གནམ་སྟོང» (བྱི),
«རྟ་ཡི་ཟླ་བའི་ལྔ་བཅུ་གཅིག» (the etext drops ལྔ), «ཁྱི་ཟླའི་ཉི་ཤུ་གཉིས་དང་དགུ»,
«དགུན་རའི་བཅུ་གསུམ་བཅུ་དྲུག་བར» (པར), «ཆར་འབེབས་ས་དབྱེ་ཀླུ་ཆོག» (ཁྲུ).

**The months.** The chapter counts by the Chinese reckoning
([tibetastromed.md](tibetastromed.md), *WB's months and seasons*): the first
month of spring is Hor month 11, the tiger month, so "the first month of
spring" and "the tiger month" are the same month, and the season-months
0–11 are also the animal months from the tiger. "The day" (*nyi ma*) of a
course is the date's animal, as for the chapter's other earth lords
([lunar-day-signs.md](lunar-day-signs.md), question 9). «X གསུམ», "the three
Xs", is the Xth, the X+10th and the X+20th: Zadün's second course spells the
same dates out (p. 231, «དཔྱིད་གསུམ་ཉེར་བརྒྱད་བཅོ་བརྒྱད་བརྒྱད»), and the model
almanac writes the nāgas' turnings on the 6th, 16th and 26th of the middle
month of winter for the text's «དྲུག་གསུམ» (below).

**The model almanac as a witness.** WB's model almanac (vol. 1, pp. 154–171,
img. 164–181; its heading of each month read in the etext, its day boxes on
the scan) writes the courses in each day's box. Read for Hor month 11 (img.
164–165), 12 (img. 166) and 9 (img. 180–181), it agrees with the reading
below wherever it writes a course: in month 11 *dbul* on the 1st and 11th,
*bar*, *nag*, *gnam sbyor* and *hal* on the 8th, «ཀླུ་བཟློག» on the 10th,
«ཐེབས་ཡོས་དུས» and «ཀླུ་ཐེབས» on the 14th, *ki kang* and «ཐེབས» on the 15th,
«བཟློག» on the 17th and 19th, *ki kang* on the 27th, and the sky doors
boxed on every date (མགྲོན, ཚོང, བུ, དམག, གཉེན, མཁར, བག, དུར, ཤིད, སྤྱི) by the general
reckoning; in month 12 *ki kang* on the 2nd and 3rd; in month 9 «ཀླུ་བཟློག»
on the 2nd, 6th, 16th, 20th and 26th, «ཀླུ་ཐེབས» on the 7th and 17th, the
*btsan* on the 6th, 16th and 26th and the *gnod sbyin* on the 9th, 19th and
29th, and «ཐེབས» from the 22nd. These are the vectors of
`EarthLordCoursesTest`.

**Weight.** None is weighed. WB's order of strength (vol. 2, p. 376,
[weighing.md](weighing.md)) lists the earth lords (*sa bdag*) among the
results it has set out and then ranks only Rāhu, the weekday, the mansion,
the date, the karaṇa, the yoga and the *nyi ma*, with the combination
period over all; it gives the earth lords no place in that order, and the
app does not invent one (SPEC §5.12). They are shown in "Also today", as the
great black day is. The exception is section 27, a course of Rāhu, which
stands with his other courses in his tier.

## The sections

Numbers in brackets are the 1996 editors' notes, which number the earth
lords. "Built" means `EarthLordCourses` (core/.../tibetan/EarthLordCourses.kt)
with its reading in `Texts.earthLord`.

### 1. *Nyi ma nag chen*, the great black day (p. 226)

Rāhu's family, the meeting of the nine bad on the 7th of the first month of
spring, and the sisters' dates through the year. **Built by ROADMAP T2.14**
(block 7) as `GreatBlackDay`: [great-black-day.md](great-black-day.md). Its
"one person's view" (the 24th of the last months of spring, autumn and
winter, the 9th of the first of winter) is built here as another view
(`NAG_CHEN`), with the course's results.

### 2. *Nyi ma nag chung*, Jama Gunggyal's course (p. 226)

> ཉི་མ(༢)ནག་ཆུང་ཞེས་བྱ་བ། །བྱ་མ་གུང་རྒྱལ་རྒྱུ་བའི་དུས། །དཔྱིད་གསུམ་བརྒྱད་གསུམ་རིམ་པ་བཞིན། །དབྱར་གསུམ་ལྔ་གསུམ་སྟོན་གཅིག་གསུམ། །དགུན་གསུམ་བཞི་དང་བཅོ་བརྒྱད་དང་། །བཅུ་དྲུག་ལ་ནི་རིམ་པར་རྒྱུ།། ཡང་ན་མི་གཅིག་ལུགས་ཤིག་ལ། །དགུན་གསུམ་དགུ་གསུམ་རིམ་རྒྱུ་བཤད། །གཤིན་བག་རབ་གནས་སྟོན་མོ་ཤིད། །ཁྱད་པར་ནོར་རྫས་ཕྱིར་གཏོང་ངན།

Spring 8, 18, 28; summer 5, 15, 25; autumn 1, 11, 21; winter 4, 18, 16 (one
person's view: winter 9, 19, 29). Works for the dead, a bride,
consecration, feasts and funeral rites bad, above all sending wealth out.
Remedies: dough animals to the four sides, white butter, white and red
earth. **Built** (`NAG_CHUNG`), the one person's view as another view.

### Pi ling 'phar ma (p. 227)

> པི་ལིང་འཕར་མ(འདི་དོན་ས་རྒྱལ་ཏེ་རྗེ་བློན་བཅུ་གཅིག་ཡོད།)… དཔྱིད་རའི་ཉེར་བརྒྱད་ཕག་གི་དུས། །འབྲིང་པོའི་བཅོ་བརྒྱད་སྤྲེའུའི་དུས། །ཐ་ཆུང་ཚེས་བརྒྱད་སྦྲུལ་གྱི་དུས། །དབྱར་རའི་ཉེར་ལྔའི་ཕག་གི་དུས། །འབྲིང་པོའི་བཅོ་ལྔའི་སྤྲེའུའི་དུས། །ཐ་ཆུང་ཚེས་ལྔའི་སྦྲུལ་གྱི་དུས། །སྟོན་རའི་ཉེར་གཅིག་ཁྱི་ཡི་དུས། །སྟོན་འབྲིང་བཅུ་གཅིག་ལུག་གི་དུས། །སྟོན་ཐའི་ཚེས་གཅིག་འབྲུག་གི་དུས། །དགུན་རའི་ཉེར་དགུ་བྱ་ཡི་དུས། །འབྲིང་པོའི་བཅུ་དགུ་རྟ་དུས་ལ། །ཐ་ཆུང་ཚེས་དགུའི་ཡོས་དུས་ལ།

"In fact the *sa rgyal*, with eleven ministers." One date and hour a month.
Good: suppressing, *gtad*, hail, hurling *zor*, *sel*, setting up wind
wheels, *mdos* and *gtor*, counter-attack, fierce work; "done together with
him they strike hard". Bad: all works great and small, especially a bride,
moving house, building, enclosure walls, a birth, consecration, stupas,
works for the dead, enthronement, burial, funeral rites, spectacles and
feasts, maṇḍalas, banner poles; digging the ground kills. Remedies listed.
**Built** (`PI_LING`). The hours of p. 236's «དུས་ཚོད་ས་རྒྱལ» ("its moving
is the *pi ling 'phar ma*") belong to the earth lords of the hour (T2.20).

### 14. *Zin phung* (p. 227)

> ས་བདག་ཟིན་ཕུང(༡༤)ཞེས་བྱ་བ། …དཔྱིད་གསུམ་སྟག་སྤྲེའི་ཉི་མ་ལ། །ལྷོ་ནས་བྱང་གི་ཕྱོགས་སུ་རྒྱུ། …དབྱར་གསུམ་གླང་ལུག་ཉི་མ་ལ། །ནུབ་ནས་ཤར་གྱི་ཕྱོགས་སུ་རྒྱུ། …སྟོན་གསུམ་ཁྱི་འབྲུག་བྱང་ནས་ལྷོ། …དགུན་གསུམ་ཕག་སྦྲུལ་ཉི་མ་ལ། །ཤར་ནས་ནུབ་ཀྱི་ཕྱོགས་དེར་རྒྱུ། …དཔྱིད་ཟླ་བྱི་རྟ་བྱ་ཡོས་བཞིའི། །ཉི་མར་ཟིན་ཕུང་དབུས་ན་གནས། །དེར་ནི་བྱ་བ་མི་བྱའོ།

Father the ogre *bdud nyi*, mother the ogress *'jig nyi*, son the minister
*tshang kun*. By the day's animal in each season, with direction; an hour
for each month from spring («བྱ་ཉལ་ནམ་ཕྱེད་ཐོ་རེངས་དང་། །སྨྱུར་སྨད་ཉི་རྩེར་རྡེལ་འགོ་དྲོས། །ས་སྲོས་རྩེ་ཤར་སྔ་དྲོ་དང་། །ཉི་ཕྱེད་མཚན་ཕྱེད་སྲོད་ལ་རྒྱུ། །མར་བཞིའི་དུས་དེར་ལྡོག་པར་བྱེད།»,
read on the scan, with the Zhol print (WBZ etext, p. 946), which has
«ཉི་རྩེའི་རྡོལ་འགོ» where the 1996 edition prints «ཉི་རྩེར་རྡེལ་འགོ»: when the birds
go to roost, midnight, dawn, late afternoon, the first breaking of the sun
on the peaks, the warmth of the morning (དྲོས), nightfall, sunrise, morning,
noon, midnight, evening, twelve times for the twelve months; it turns back
at «དུས་ཚོད་མར་གྱི་བཞི་གཤེད», the four-slayer counted downward, the count by
which the black days give the bird and the hare to the mouse and horse
months (p. 235). In the reading); in spring on mouse,
horse, bird and hare days it sits in the middle and nothing is done. Each
season's avoidances (spring: building, *sa tstsha*, maṇḍalas, blocking
holes, roofing, seeking a grave; summer: earth and water work, ponds,
sowing, threshing, wells; autumn: earth work, «སྐྱེད་འཛུགས», walls, «གཡག་ཤིང་དགོག»
(the last two confirmed on the Zhol print, WBZ img. 945–946, but of no
settled sense: given word for word, "setting up growth" and "pulling down
the yak wood", in the reading and the avoid list); winter: earth work, stupas,
foundations) and the general ones (a birth turns back, council, a bride,
spectacles, building, a corpse, war); never face it. **Built** (`ZIN_PHUNG`).

### 15. *Phung po zor thogs* (p. 228)

> སྟག་ཡོས་ཟླ་བའི་ཚེས་བརྒྱད་ལ། །འབྲུག་དགུ་སྦྲུལ་གསུམ་རྟ་ཉེར་གསུམ། །ལུག་དགུ་སྤྲེལ་བདུན་བྱ་བཅུ་བདུན། །ཁྱི་བརྒྱད་ཕག་གཅིག་བྱི་བཅུ་དྲུག །གླང་གི་ཟླ་བ་ཚེས་དགུར་རྒྱུ།

By animal month. "Another way" by season-month (spring 18, 8, 9 …), which
[tibetastromed.md](tibetastromed.md) used to settle the reckoning, is built
as another view. Avoid list and remedies as in the app's reading. **Built** (`PHUNG_ZOR`).

### 16. *Ki kang* (p. 228)

> ཀི་ཀང(༡༦)འབྱུང་བའི་སྤྱི་བདུད་པོ། །གཟའ་རྒོད་དྲག་པོ་སྒྲ་གཅན་ནི། །སྟག་རྟ་ཁྱི་གསུམ་ཟླ་བ་ལ། །བཅོ་ལྔ་ཉི་ཤུ་བརྒྱད་ལ་རྒྱུ། །ཕག་ལུག་ཡོས་གསུམ་གཉིས་གསུམ་ལ། །བྱི་འབྲུག་སྤྲེལ་གསུམ་བཅོ་བརྒྱད་དང་། །ཉི་ཤུ་བརྒྱད་རྒྱུ་བྱ་གླང་སྦྲུལ། །བཅུ་དྲུག་ཉེར་དྲུག་དག་ལ་རྒྱུ།

«གཉིས་གསུམ» is the 2nd and the 3rd: the model almanac writes *ki kang* on both
in the hare month. Two other courses follow («ཡང་ཅིག་རྒྱུ་ཚུལ་མི་འདྲ་གཉིས»), the
second called "a discordant view"; the results after them (p. 229: white
work brings a king down, a bride, building, a feast, a birth; fierce and
harsh work good) are the app's reading. **Built** (`KI_KANG`), the second and third courses as other views (the
third, for two groups of months only: tiger, horse and dog 17 and 27; pig,
sheep and hare 2 and 22).

### 17. *Hal khyi nag po* (p. 229)

> སྟག་ཟླའི་ཚེས་བརྒྱད་བྱ་ཡི་དུས། །ཡོས་ཟླའི་བཅུ་དྲུག་རྟ་ཡི་དུས། །འབྲུག་ཟླའི་ཉེར་བཞིའི་སྟག་གི་དུས། །སྦྲུལ་ཟླའི་ཚེས་དགུའི་གླང་གི་དུས། །རྟ་ཟླའི་བཅོ་བརྒྱད་ཕག་གི་དུས། །ལུག་ཟླའི་ཉེར་བདུན་བྱ་ཡི་དུས། །སྤྲེལ་ཟླའི་ཚེས་བཅུའི་སྦྲུལ་གྱི་དུས། །བྱ་ཟླའི་ཉི་ཤུའི་འབྲུག་གི་དུས། །ཁྱི་ཟླའི་གནམ་སྟོང་སྟག་གི་དུས། །ཕག་ཟླའི་བཅུ་གཅིག་བྱ་ཡི་དུས། །བྱི་ཟླའི་ཉེར་གཉིས་སྤྲེའུའི་དུས། །གླང་ཟླའི་ཚེས་གསུམ་ཡོས་ཀྱི་དུས།

"Its food-seeking is also said to be at the seven-corner of the moving
date's day." **Built** (`HAL_KHYI`).

### 18. *Gnam khyi nag po* (p. 229)

By season-month, the day's animals and a time of day (the reading names
them). "One way, as told in the reckoning of brides"; a second way follows
(spring sheep days at dawn …), built as another view. Avoid as for *hal khyi*.
**Built** (`GNAM_KHYI`).

### 19. *Gnam sbyor* (p. 230)

> གནམ་སྦྱོར(༡༩)དཔྱིད་གསུམ་རིམ་པ་ལྟར། །བརྒྱད་བདུན་བཞི་དང་དབྱར་ཟླ་གསུམ། །བདུན་བདུན་ཉེར་དྲུག་སྟོན་ཟླ་གསུམ། །བཅུ་དགུ་བཅུ་དགུ་ཚེས་བདུན་ལ། །དགུན་གསུམ་བཅུ་དྲུག་བཅུ་དགུ་དང་། །ཉི་ཤུ་བཞི་ལ་སྦྱོར་བའོ། །བཟའ་གསར་མཉམ་སྦྱོར་བག་རོ་ངན།

*bza' gsar mnyam sbyor* read as a new couple joining. What "some" add (war,
spectacles, taking a child out) is in the reading, not in the lists.
**Built** (`GNAM_SBYOR`).

### 20. *Gza' rgod* (p. 230)

> འབྲུག་ལུག་ཟླ་བའི་བཅོ་བརྒྱད་ལ། །ཕག་སྦྲུལ་ཟླ་བའི་བཅུ་དྲུག་དང་། །ཉི་ཤུ་གཉིས་ལ་རྒྱུ་བ་ཡིན། །རྟ་ཟླའི་བཅུ་གསུམ་སྤྲེལ་ཟླ་ཡི། །དགུ་དང་ཉི་ཤུ་གཉིས་ལ་རྒྱུ།

**Built** (`GZA_RGOD`).

### 21. *Dbul po lag stong* (p. 230)

> སྟག་གི་ཟླ་བའི་གཅིག་བཅུ་གཅིག །ཡོས་ཟླའི་གཉིས་དང་ཉི་ཤུ་གཅིག །འབྲུག་གི་ཟླ་བའི་གསུམ་དང་བརྒྱད། །སྦྲུལ་གྱི་ཟླ་བའི་བཞི་དང་བརྒྱད། །རྟ་ཡི་ཟླ་བའི་ལྔ་བཅུ་གཅིག །ལུག་ཟླའི་དྲུག་དང་ཉི་ཤུ་གཉིས། །སྤྲེའུ་ཟླ་བའི་བདུན་བཅུ་དགུ། །བྱ་ཡི་ཟླ་བའི་བརྒྱད་དང་བཅུ། །ཁྱི་ཟླའི་ཉི་ཤུ་གཉིས་དང་དགུ། །ཕག་ཟླའི་ཚེས་གཅིག་བྱི་ཟླ་ཡི། །གཅིག་དང་བཅུ་དྲུག་གླང་ཟླ་བའི། །དགུ་དང་ཉེར་དགུ་དག་ལ་རྒྱུ།

One person's variant for four months (spring first 6 and 16, autumn last 9
and 21, winter first 19, middle 10) built as another view. **Built** (`DBUL_PO`).

### 22. *Gza' bdun* (p. 231)

> གཟའ་བདུན(༢༢)ཟས་འཚོལ་ཕོ་ཉ་འགྱེད། །དཔྱིད་གསུམ་བརྒྱད་གསུམ་དག་ལ་རྒྱུ། །དབྱར་གསུམ་ལྔ་གསུམ་དག་ལ་རྒྱུ། །སྟོན་གསུམ་གཅིག་གསུམ་དགུན་དགུ་གསུམ། །རིམ་པ་བཞིན་དུ་རྒྱུ་བ་ཡིན།

The second way gives the same dates in reverse order (28, 18, 8 …), built
as another view. "Its results are the same as *ki kang*'s." **Built** (`GZA_BDUN`).

### 23. *Ngam shing* (p. 231)

> དཔྱིད་ར་ཚེས་བཅུར་ཤར་ནས་ནུབ། །དཔྱིད་འབྲིང་ཉི་ཤུ་ཤར་ནས་ནུབ། །དབྱར་འབྲིང་གནམ་གང་ལྷོ་ནས་བྱང་། །དབྱར་ཐ་བཅུ་གཉིས་ཤར་ནས་ནུབ། །སྟོན་ར་བཅུ་ལ་ནུབ་ནས་ཤར། །སྟོན་འབྲིང་ཉི་ཤུར་ཤར་ནས་ནུབ། །དགུན་འབྲིང་གནམ་གང་ལྷོ་ནས་བྱང་། །དགུན་ཐ་བཅུ་གཉིས་ཤར་ནས་ནུབ།

Good for honouring spiritual friends, enthronement, a throne, new teaching,
escorting and welcoming abbots and teachers; «བག་མ་བུ་སྲིང་ལས་མི་རུང» (a bride,
and the works of son and sister, not allowed: only the bride is listed).
**Built** (`NGAM_SHING`).

### 24. *Bar khyi* (p. 231)

> བར་ཁྱི(༢༤)དཔྱིད་གསུམ་བརྒྱད་གསུམ་རིམ། །དབྱར་གསུམ་ལྔ་གསུམ་རིམ་པ་བཞིན། །སྟོན་གསུམ་གཅིག་གསུམ་རིམ་པར་རྒྱུ། །དགུན་གསུམ་དགུ་གསུམ་རིམ་པར་རྒྱུ།

The dates of *gza' bdun*. **Built** (`BAR_KHYI`).

### 25. *Ka khyung ki kang* (p. 232)

> སྟག་རྟ་ཁྱི་ཟླར་བཅུ་གཅིག་དང་། །ཉི་ཤུ་བདུན་ལ་རྒྱུ་བ་ཡིན། །ཕག་ལུག་ཡོས་ཀྱི་ཉི་ཤུ་བཞི། །བྱི་འབྲུག་སྤྲེའུའི་ཉི་ཤུ་བརྒྱད། །བྱ་གླང་སྦྲུལ་ཟླའི་ཉི་ཤུ་དྲུག

"Some say" its avoidances; "no good or bad results and no remedies are
given for it; yet avoid all but fierce work" — that verdict is the list.
**Built** (`KA_KHYUNG`).

### 26. *Dra chen* (p. 232)

> སྦྲུལ་ཟླའི་ཉི་ཤུ་ལྔ། །ལུག་གི་བཅོ་ལྔ་བྱ་ཟླ་ཡི། །གཅིག་དང་ཉེར་གཅིག་ཁྱིའི་ཉེར་གཅིག །ཕག་གི་ཚེས་པ་དགུ་ལ་རྒྱུ།

**Built** (`DRA_CHEN`).

### 27. Rāhu (p. 232)

> རཱ་ཧུ(༢༧)དཔྱིད་རའི་བཅུ་གཅིག་དང་། །ཉེར་བརྒྱད་འབྲིང་པོའི་ཚེས་གཉིས་ལ། །ཐ་ཆུང་བཅོ་བརྒྱད་དབྱར་ར་ཡི། །བཅུ་དྲུག་ཉེར་གཉིས་འབྲིང་པོ་ཡི། །གཅིག་དང་བཅུ་གསུམ་ཐ་ཆུང་གི །ཚེས་གཉིས་སྟོན་རའི་ཉེར་བརྒྱད་ལ། །འབྲིང་པོའི་བཅུ་དྲུག་ཐ་ཆུང་གི །ཚེས་གཅིག་དགུན་རའི་ཚེས་གཉིས་ལ། །འབྲིང་པོའི་བཅོ་བརྒྱད་ཐ་ཆུང་གི །ཚེས་ལ་ཟས་ཁ་འཚོལ་བ་མེད། །གསོན་གཤིན་བྱ་བ་ཆེ་ཆུང་འཛེམ། …ཟོར་འཕེན་དྲག་པོའི་ལས་ལ་ཤིས།

The last month of winter has no date. The 1996 edition's «ཁྱད་པ་རས་ལས་འདམས་ལས་ངན» is
«ཁྱད་པར་ས་ལས་འདམ་ལས་ངན» in the Zhol print (WBZ etext): above all, work with earth
and with mud is bad; built into the avoid list. **Built** as
`RahuBySeason` with `Texts.RAHU_SEASON`, a third reading in the Rāhu row,
weighed in Rāhu's tier like his course by month ([rahu.md](rahu.md)).

### 28. The eight classes' general strikes and turnings (pp. 232–233)

> སྡེ་བརྒྱད(༢༨)སྤྱི་ཡི་ཐེབས་བཟློག་བསྟན། །དཔྱིད་རའི་བཅུ་བཞིའི་ཡོས་བུའི་དུས། །བསྟན་མ་དང་བཅས་ནུབ་ནས་ཤར། །མྱུལ་ཐེབས་བཅོ་ལྔའི་ཚེས་ལའང་ཐེབས། །བཅུ་བདུན་བཅུ་དགུའི་ཉི་ཕྱེད་དང་། །སྤྲེལ་གླང་དུས་སུ་བཟློག་པར་འགྱུར། …ཐེབས་ལ་ཟོར་མདོས་གཏད་ཁྲོམ་རྦད། །དམག་ཇག་དྲག་ལས་ཕར་རྒོལ་བཟང་། །བཟློག་ལ་དེ་དག་སྤང་བར་བྱ།

Month by month, with the class, hour and direction (the app's reading gives
all twelve). Where the text adds "some say" (the last month of autumn, from
the 8th to the 30th) the added dates are built as another view. The mid-winter strike
«བཅུ་བདུན་ཉེར་གཉིས་ནས། །སྟོང་བར» is read as the 17th and the 22nd to the 30th,
the 20th, between them, being its turning. Results: p. 232 and again
p. 364 («སྡེ་བརྒྱད་ཐེབས་དུས་མདོས་གཏོར་དང་། །མཐུ་གཏད་ཐོག་སེར་མནན་སྲེག་ཟོར། །དམག་ཇག་ཕར་རྒོལ་བྱ་བ་བཟང༌། །བཟློག་དུས་དེ་རྣམས་སྤང་བར་བྱ།», from the etext): on a strike good,
on a turning to avoid. **Built** (`SDE_BRGYAD`).

### 29. The earth lords' turning (p. 233)

> ས་བདག་བཟློག་པའི(༢༩)དུས་བསྟན་པ། །དཔྱིད་རའི་བཅུ་བདུན་བཅུ་དགུ་ལ། །འབྲིང་པོའི་གཅིག་བརྒྱད་ཐ་ཆུང་གི། །གནམ་སྟོང་དབྱར་རའི་གཉིས་ལྔ་ལ། །འབྲིང་པོའི་གསུམ་དང་ཐ་ཆུང་གི །བཅུ་གཅིག་སྟོན་རའི་ཉི་ཤུ་ལྔ། །འབྲིང་གསུམ་ཐ་ཆུང་ཚེས་གཉིས་ལ། །དགུན་རའི་བཅོ་བརྒྱད་འབྲིང་ཉེར་བདུན། །ཐ་ཆུང་བཅུ་བཞིར་ས་བདག་བཟློག །དམག་དྲང་རོ་གཏོང་གཏད་སེར་དབབ། །ཆར་འབེབས་ས་དབྱེ་ཀླུ་ཆོག་རྣམས། །གང་བརྩམས་ཚུར་བཟློག

**Built** (`SA_BDAG_BZLOG`). Then "the view of Spug ston" (*spug ston bzhed
pa'i lugs*): for each month the dates of success (*grub*, when the earth
lords' remedies help), of vanishing (*yal*, when they turn back on oneself),
of not giving out wealth, and the direction of the *yas lam*. Read on the
scan; **built** (`SPUG_STON`). The last month of summer reads «གསུམ་དྲུག་ཉར་ཡལ»
in the 1996 edition and «གསུམ་དྲུག་ཉེར་ཡལ» in the Zhol print (WBZ img. 954,
read on the scan): taken as the autumn lines are written, success,
vanishing and wealth in turn without the words, the 3rd, the 26th
(«དྲུག་ཉེར», inverted for the metre) and the 28th. Each month's *yas lam*,
the way for the ransom offering, is in the reading: WB uses *yas* for the
ransom sent to the spirits (vol. 1: «གཉན་རྣམས་ཡས་ཀྱིས་བཀར», the *gnyan* appeased
with *yas*; «ཡས་ཀྱིས་བསྒྱུར་བ་ལུག་ར་ཁྱི», a sheep, goat and dog sent as *yas*;
«ཡས་ཀྱི་འཇུ་ཐག», the *yas* cord), so the *yas thags* laid toward it is the
ransom cord: south-west, north-west, north-east; south-east, north-west,
west; north-east, south-east, north-west; south, east, north-east.

### 30. The *gnyan*'s moving times, and their strikes and turnings (pp. 233–234)

> གཉན་གྱི་རྒྱུ་དུས(༣༠)དཔྱིད་གསུམ་ལ། །བརྒྱད་དྲུག་ཚེས་བཞི་རིམ་བཞིན་ཡིན། །དབྱར་གསུམ་བདུན་བཅུ་ཉེར་བདུན་རིམ། །སྟོན་གསུམ་དགུ་སྟོང་ཉེར་བདུན་ལ། །དགུན་གསུམ་བཅུ་དགུ་བཅུ་དང་གསུམ། །རིམ་བཞིན་རྒྱུ་དུས་ཅི་བརྩམས་ཀྱང་། །ཟློས་སྐྱོར་དགོས་པས་ངན་པ་སྟེ། །འོན་ཀྱང་གཉན་མཆོད་བཟང་བར་བསྟན།

Then Spug ston's strikes and turnings of the *gnyan* («སྤུག་སྟོན་བཞེད་པར་འདི་ལྟ་སྟེ། །དཔྱིད་ར་བདུན་ཐེབས་བཅུ་དགུར་བཟློག …»), the only such course WB gives for
them, "results compared by the reasoning above". Both **built** (`GNYAN`):
the moving times, and the strikes and turnings without lists of their own.
This is the *gnyan pa* that WB's almanac marks in its boxes (vol. 1,
p. 173, [almanac-page.md](almanac-page.md), entry 15).

### 31. The nāgas' strikes and turnings (p. 234)

> ནཱ་ག་ཀླུ་ཡི(༣༡)ཐེབས་བཟློག་བསྟན། །དཔྱིད་རའི་ལྔ་བཅུ་བཅོ་ལྔ་ལ། །ཀླུ་རྣམས་ཁྲིམས་སྲུང་དུས་ཏེ་བཟློག །བཅུ་བཞི་ལ་ནི་ཐེབས་པར་འགྱུར། …ཐེབས་དུས་ཀླུ་གཏོར་ཀླུ་བརྒྱད་དང་། །ཆར་འབེབས་གཏད་ཁྲོམ་ཀླུ་ཡི་ལས། །གང་ཡང་བསྣུན་ན་ཤིས་འགྱུར་ཞིང་། །བཟློག་དུས་དེ་དག་བྱར་མི་རུང་།

Month by month (the reading gives them). Where the text adds what "some
teachings" or "the authoritative texts" say (the 15th of the last month of
summer, the 5th of the middle of autumn, the 23rd of the last, the 15th of
the first of winter, the 15th and 21st of the middle), the added dates are
built as another view. The middle of winter's
«གཉིས་དང་དྲུག་གསུམ་ཉི་ཤུར་བཟློག» is the 2nd, the three 6s and the 20th, as the
model almanac writes it. In the last month of winter they sleep. The
results again on p. 364. **Built** (`KLU`).

### 32–39. The classes' own times (p. 234)

> ཁྱད་པར་ཕྱེ་བའི་ཐེབས་དུས་ནི། །ཀླུ་ནི་ར་བཞིའི་བདུན་གསུམ་གྱི། །ཉི་ཤར་ཟེར་རྒྱུ་ཐ་ཆུང་བཞིའི། །ཉེར་དགུའི་སྲོད་ལ་བདུད་རྒྱུའོ(༣༢)། །འབྲིང་བཞིའི་དྲུག་གསུམ་ཉི་ཤར་ཟེར། །བཙན་རྒྱུ(༣༣)ར་བཞིའི་བརྒྱད་གསུམ་གྱི། །སྐྱ་རེངས་ཤར་དུས་རྒྱལ་པོ་རྒྱུ། །འབྲིང་བཞིའི་དགུ་གསུམ་ཉིན་ནམ་ཕྱེད། །གནོད་སྦྱིན་རྒྱུ(༣༥)ལ་ར་བ་བཞིའི། །ཉེར་གཉིས་སྲོད་ལ་ལམ་མོ(༣༦)རྒྱུ། །ཐ་ཆུང་བཅུ་གསུམ་ཉི་སྨྱུར་ཁར། །གཤིན་རྗེ་རྒྱུ(༣༧)ལ་གཟའ(༣༨)སྲིན་པོ(༣༩)། །དུས་ཆེན་བིདྡྷིའི་ཚེས་ལ་རྒྱུ། །དེ་དུས་རང་རང་མཐུན་ལས་ཤིས།

**Built** (`CLASS_TIMES`), the planets and *srin po* too: «བིདྡྷི» is Viṣṭi,
whose place it takes in WB's table of the karaṇas (vol. 2, p. 343, etext:
«…ཁྱིམ་སྐྱེས་ཚོང་པ་བིདྡྷི་གདབ་པ…»), so their dates are the eight on which Viṣṭi
falls, the 4th, 8th, 11th, 15th, 18th, 22nd, 25th and 29th (vol. 1, p. 177,
[almanac-page.md](almanac-page.md), entry 9).
Then the key to all strikes and turnings: work done as it approaches
(«འགྱུ་གདོང») flourishes, ahead of it declines, after it has passed neither
helps nor harms; in the reading.

### 40–49. The sky doors (pp. 234–235)

> གནམ་སྒོ་བཅུ་ཡི་བརྩི་ལུགས་ནི། །གཅིག(༤༠)གསུམ་མགྲོན་པོ་གཉིས(༤༡)གསུམ་ཚོང་། །གསུམ(༤༢)གསུམ་བུ་ཆུང་བཞི(༤༣)གསུམ་དམག །ལྔ(༤༤)གསུམ་གཉེན་ལ་དྲུག(༤༥)གསུམ་མཁར། །བདུན(༤༦)གསུམ་བག་མ་བརྒྱད(༤༧)གསུམ་དུར། །དགུ(༤༨)གསུམ་ཤིད་ལ་བཅུ(༤༩)གསུམ་སྤྱིའི། །གནམ་སྒོ་ཡིན་ཏེ་དེ་དག་ལ། །རང་རང་ལས་ལ་འཛེམ་པ་གཅེས། །ཁྱད་པར་ཕྱེ་བའི་བརྩི་ཚུལ་ནི། །ར་བ་བཞི་ཡི་གསུམ་ནས་འདྲེན། །འབྲིང་བཞིའི་གཉིས་ནས་ཐ་བཞིའི་གཅིག །གཉིས་བོར་ས་ནས་གནམ་སྒོ་བྱུང་། །དེ་ཡི་སྒྲིབ་ཤིང་བཅོས་ཐབས་ནི། །འབྲས་བཤད་ལེའུའི་ནང་དུ་གསལ།

Every date's door by the last figure of its number. The "special
reckoning" starts the count at the 3rd in the first months, the 2nd in the
middle, the 1st in the last; the model almanac's first month of spring has
the guests' door on the 1st, so its almanac follows the general reckoning,
which is built (`GNAM_SGO`). Each door's result and remedy are in ch. 33,
pp. 368–369 (from the etext: «མགྲོན་གྱི་གནམ་སྒོར་དོན་གཉེར་དང་། །མགྲོན་བྱས་ཁྱིམ་དེ་གུད་པར་འགྱུར།», «ཚོང་གི་གནམ་སྒོར་ཚོང་བྱས་ན། །ཚོང་ཉེས་གོད་ཁ་རྒྱུན་མི་ཆད།» … «བཅུ་གསུམ་སྤྱི་ཡི་གནམ་སྒོ་ལ། །ལས་ཅི་བྱས་ཀྱང་ཉེས་ཤིང་འཕུང་»), read on the scan (img. 376–377) with their remedies, which each door's reading summarises in one clause; the dhāraṇīs they name are not identified by title.

### The black days (p. 235)

> ད་ནི་ཞག་ནག་བཤད་པར་བྱ། །གོང་དུ་ཟླ་ནག་བཤད་པ་ལྟར། །འདི་ནི་ཟླ་བ་ཉི་མ་སྤྲད། །དཔེར་ན་བྱི་རྟའི་ཟླ་བ་ལ། །བྱ་ཡོས་གཉིས་ཀྱི་ཉི་མ་ནག

The black days meet the month with the day as the black months meet the
year with the month. The black months' rule is WB's vol. 1, p. 183
(etext): «གཞན་ཡང་ཟླ་བ་ནག་པོ་ནི། །གང་ཤར་ཟླ་བའི་བཞི་གཤེད་ནག། བྱི་རྟ་གནམ་ཤར་བྱ་ཡོས་ནག། གླང་ལུག་ཤར་ཚེ་ཁྱི་འབྲུག་ནག། སྟག་སྤྲེལ་ཤར་ན་ཕག་སྦྲུལ་ནག།», the
four-slayer of the animal that rises, the animals three either side of it,
which gives p. 235's own example (mouse and horse months, bird and hare
days). **Built** (`ZHAG_NAG`): the date's animal against the month's, the
tiger month the 11th. The Paṇchen Mön'drowa's list by year that follows
(«བྱི་ལོ་དཔྱིད་འབྲིང་ཚེས་བདུན་ནག། གླང་ལོ་དཔྱིད་ཐའི་ཚེས་ལྔ་ནག། …», read on the scan) is
built as another view, the year the Chinese reckoning's, which the model
almanac begins with the 11th month. The results are those of the black
hours (p. 236: a grave site, building, feasts, consecration, enthronement,
teaching, a new land, a bride and all important works bad; fierce work
strikes; a swastika and the sun and moon at the door).

## After p. 235

The earth lords of the hour (*dus kyi lha*, *g.yu mdzod sngon mo*, *dus
tshod bla mkhyen*, *sa bdag khang brtsegs*, *dus tshod sa rgyal* and the
black hours) follow and belong to the times within the day (ROADMAP T2.20);
Rāhu's own reckoning (pp. 236–239) is [rahu.md](rahu.md).
