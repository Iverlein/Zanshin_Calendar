# White Beryl: the month's own entries

Sigla, numbering and quoting rules are in [README.md](README.md). Read on
2026-10-10 for ROADMAP T2.21 (block 18): WB vol. 1 pp. 176, 183, 186–189
and the model almanac's month headings (img. 164–181), vol. 2 pp. 212, 312
and 359, with Yigdzin-1 on the scans and BDRC's etext as witness, every
line quoted below read on the scan at 2–6×; the eclipse chapter (vol. 1,
ch. 9, pp. 56–70, img. 66–82) and Rāhu's reckoning (pp. 34–35) with
Yigdzin-1 and the etext, the rules' lines by eye. In vol. 1 the printed
page is the image number − 10, in vol. 2 − 8. Built as `MonthEntries` and
two courses of `EarthLordCourses` (SPEC §5.11, §5.13, §10.3);
`MonthEntriesTest` checks each entry against what the texts print.

The month heading (p. 176, [almanac-page.md](almanac-page.md)) names five
entries the app did not have: whether the month is long or short, the
black months, the weekday's rise, the eclipses and the seasonal signs.
Four are built; the eclipses are not (below).

## Long or short

p. 176 (img. 186): «ཆད་ལྷག་ཇི་ཙམ་ཡོད་ཀྱང་རུང་། །ཚེས་ཞག་མཁའ་མེ་ལོངས་པ་ན། །ཆེ་དང་དེ་ཚུན་ཆུང་བ་སྟེ། །ཆེ་ཆུང་དབྱེ་བ»
However many dates are omitted or doubled, a month whose days (*tshes
zhag*) reach thirty (*mkha' me*) is long, one with fewer is short. A
Phugpa month has 29 or 30 days. The model almanac's headings leave it as
a blank to fill: «སྟག་གི་ཟླ་བ་ཆེ་ཆུང་བལྟ» (img. 164), «ལུག་གི་ཟླ་བ་ཆེ་ཆུང་བལྟ» (img. 172).
**Built** (`MonthEntries.days`, `isLong`), checked against every whole
month of Henning's calendars in henning-phugpa.tsv.

## The weekday's rise (*gza' dar gud*)

p. 176 (img. 186), read on the scan:

> ཁྱིམ་རྣམས་ཐོག་མར་སླེབས་དེ་ཡི། །གྲུབ་པའི་རེས་གཟའ་ཟླ་བ་དེའི། །བདག་ཡིན་དར་གུད་རགས་པ་ནི། །གཙུག་ལག་སྤོར་ཐང་དགོངས་དོན་ལྟར། །སྟག་རྟ་ཁྱི་གསུམ་སྤེན་དར་ཚེས། །བྱི་འབྲུག་སྤྲེལ་གསུམ་མིག་དར་ཚེས། །ཕག་ལུག་ཡོས་གསུམ་ཟླ་དར་ཚེས། །བྱ་གླང་སྦྲུལ་གསུམ་ཕུར་དར་ཚེས། །ཞེས་དང་ཞིབ་པར་ཟླ་བའི་བདག །རྣམ་དག་གྲུབ་པའི་ཚེས་གཅིག་གི། །རེས་གཟའི་ཁམས་ནས་བརྩི་བ་ལ། །གང་ཆེ་དེ་དར་བུ་ཡང་དར། །མ་ཞུད་དགྲ་གུད་གྲོགས་སྐྱོད་པར། །སྤྱིར་བཏང་རགས་པར་བཞེད་པ་ལྟར། །མ་ནོར་གཉིས་ཀ་སོ་སོར་བཀོད།

- **Roughly**, "as the *Gtsug lag spor thang* means it": Saturn rises in
  the months of the tiger, horse and dog, Mars in the mouse, dragon and
  monkey, the Moon in the pig, sheep and hare, Jupiter in the bird, ox
  and snake. The month's animal is the app's (the 11th month the tiger,
  as the trigram verse of p. 178 has it).
- **Finely**, the month's lord is the weekday of the true reckoning's
  1st; counted from the lord's element, the planets of its element rise
  and its son's rise too, its mother's wane (*zhud*), its enemy's decline
  (*gud*) and its friend's move (*skyod*). The etext reads the last
  «གྲོགས་ཀྱང་གུད»; the scan has «གྲོགས་སྐྱོད་པར», and the model almanac
  writes *skyod* too. The elements are those of the Chinese reckoning
  (vol. 1, p. 257, `Weekday.fiveElement`), the relations those of the
  elemental divination (`Forces.kinship`).
- WB says to write both.

**The model almanac's month lines** (vol. 1, the headings of the 11th,
2nd and 4th months, img. 164, 169, 172), each word with the weekday
numbers (0 Saturday, 1 Sunday … 6 Friday) under an arc, read at 5–7×:

| Month | Printed | Lord it fits |
| --- | --- | --- |
| 11th (img. 164) | «དར། ༠༦ སྐྱོད། ༢༤ ཞུད། ༡༣ གུད། ༥» | Saturday (earth): earth and its son iron rise, its friend water moves, its mother fire wanes, its enemy wood declines |
| 2nd (img. 168–169) | «དར། ༡༣ གུད། ༦ སྐྱོད། ༠ ཞུད། ༢༤» | Thursday (wood): fire, the son, rises; iron declines, earth moves, water wanes; Jupiter's own ༥ is not printed with the rising |
| 4th (img. 172) | «དར། ༢༤༥ གུད། ༠ སྐྱོད། ༡༣ ཞུད། ༦» | Monday or Wednesday (water) |

Each line fits exactly one element of the lord, which checks the rule as
read; the lords match no single year near WB's (1657 and 1701 give the
11th, 2nd and 4th months these lords, 1684 does not), so the model
almanac reads as a pattern, not a dated year. `MonthEntriesTest`.

**Against the works.** WB's closing verse of the weekdays (vol. 2,
p. 312, [weekdays.md](weekdays.md)) says what each planet forbids "even
when it rises" (*dar yang*): the rise lifts none of a weekday's
avoidances, and WB gives no weight to it. So the rise is written in the
month balloon and changes no weighing.

## The black months (*zla nag*)

**The rule**, vol. 1, p. 183 (img. 193), at the close of ch. 15:
«གཞན་ཡང་ཟླ་བ་ནག་པོ་ནི། །གང་ཤར་ཟླ་བའི་བཞི་གཤེད་ནག །བྱི་རྟ་གནམ་ཤར་བྱ་ཡོས་ནག །གླང་ལུག་ཤར་ཚེ་ཁྱི་འབྲུག་ནག །སྟག་སྤྲེལ་ཤར་ན་ཕག་སྦྲུལ་ནག །དེ་ལྟར་འགྲེ་ལ་ཤེས་པར་བྱ། །ར་བཞིའི་སྟོད་ནག་བྲིང་བཞིའི་སྒང་། །ཐ་བཞིའི་སྨད་ནག་ཤེས་པར་བྱ།»
and the month heading p. 176: «གནམ་ལོ་ནས་བགྲངས་བཞི་གཤེད་ཀྱི། །ཟླ་ནག་ར་སྒང་ཐ་ཆུང་གི། །སྟོད་སྨད་འབྱུང་ཚུལ་བཅས་པ་བཀོད།»

**With the works**, vol. 2, p. 212 (img. 220), in ch. 31 among the earth
lords, read on the scan:

> ཟླ་ནག་འདོད་པ་མང་ན་ཡང་། །རྣམ་དག་ཕུག་པ་གོང་མའི་ལུགས། །གནམ་ལོ་རྒྱལ་པོ་དབུས་བཞག་པའི། །ཡར་མར་བཞི་གཤེད་ཟླ་བ་ནག །དེ་ཡང་ར་བ་བཞི་ཡི་སྟོད། །འབྲིང་སྒང་ཐ་ཆུང་ཟླ་སྨད་ནག །གསོན་གཤིན་བྱ་བ་ཐམས་ཅད་དང་། །ཁྱད་པར་ཤིད་མཁར་དགེ་བའི་ལས། །ཚོང་དུར་ས་ལས་ནོར་ཕྱིར་གཏོང་། །བག་སྟོན་རབ་བྱུང་རབ་ཏུ་གནས། །ཆེ་འདོན་ལ་སོགས་འཛེམ་པ་དང་། །མཐུ་གཏད་མནན་བསྲེག་དགྲ་ལིང་འདྲི། །དྲག་ལས་དབྱེ་སེལ་སྦྱོར་བཤིག་བཟང་། །བཅོས་ཐབས་མར་གྱི་ཟླ་བ་གསུམ། །སྒྲ་ཁྱིམ་ཁང་པའི་གོང་ཆུར་བཞག །བདེན་པ་བདར་ཞིང་འདི་ལྟར་བརྗོད། །ཟླ་ནག་མ་ཡིན་དཀར་པ་ཡིན། །ལས་དང་བྱ་བ་ཤིས་པར་ཤོག །ཅེས་བརྗོད་ཟན་གྱི་ཟླ་ནག་གསུམ། །མི་ཁའི་རྫས་བཅས་ལམ་མདོར་བསྐྱལ། །ཟླ་ནག་ལོག་པར་བལྟ་བ་དང་། །མི་མཐུན་ཕྱོགས་རིགས་ངན་དགུའི་ཚོགས། །བསྟན་གནོད་དགྲ་བགེགས་སྟེང་དུ་བསྒྱུར། །ཞེས་དང་འོད་ཟེར་ཅན་མ་སྒྲོག

Of the many views, the pure Phugpa one: with the year's king in the
middle, the months of its four-slayers up and down are black. Of the four
first months the upper part, of the middle the middle (*sgang*), of the
last the lower. All works for the living and the dead are avoided, above
all funeral rites, forts, virtuous work, trade, burial, earthworks,
sending wealth out, weddings, ordination, consecration, *che 'don*;
curses, pressing down, burning, drawing the enemy's *ling ga*, fierce
work, separating and breaking up unions are good. Remedies: three dough
black months carried to a crossroads with the substances against gossip
after the words "this is no black month but a white one", and Mārīcī's
prayer.

**The results** in ch. 33's earth lords of the date, vol. 2, p. 359
(img. 367), read on the scan: «ཟླ་ནག་མཁར་ལས་ཐོག་བརྩེགས་འབུབས། །རབ་གནས་རབ་བྱུང་རྟེན་བཞེངས་དང་། །བག་མ་ནོར་གཏོང་གསོན་གཤིན་འཛེམ། །མཐུ་གཏད་མནན་སེལ་དགྲ་ལིང་བྲི། །དྲག་ལས་བཟང་ཞིང་སྒྲིབ་པ་ན། །ཁ་ཚར་མ་ཉམས་དར་རས་དཀར། །འཕྱུར་ཞིང་མར་གསུར་གཏོང་བ་དང་། །ཕྱོགས་བཞིར་ས་དམར་གཏོར་ལ་ནི། །གཡག་ལུག་མགོ་བོ་དཀར་པོ་ལ། །དར་དཀར་བརྒྱན་པས་ཟླ་ནག་སྒྲིབ། །ཆུ་གཏོར་མང་གཏོང་གཟུངས་འདུས་ཀྱི། །གསུང་སྒྲོག་དེ་ཡི་ཉེས་པ་ཞི།»
adds building storeys and roofing, raising images, and a remedy of white
silk, a white *gsur*, red earth, a white-headed yak or sheep and the
*gzungs 'dus*.

**The month's thirds.** WB's notes count the month's *sgang* from the
11th to the 20th (vol. 2, p. 484, etext: «ཟླ་བའི་སྒང་ཞེས་བཅུ་གཅིག་ནས་ཉི་ཤུ་བར་རོ»), so the upper
third is the 1st to the 10th and the lower the 21st to the 30th.

**Which year and which seasons.** WB counts the black months among the
earth lords (vol. 2, pp. 174, 199: «རྣམ་གྲངས་རེ་བཞི་ཟླ་ནག་དང་། །བུམ་སྟོང་…»), and ch. 31
counts its seasons and the black days by the Chinese reckoning, the
year beginning with the 11th month, the tiger month the first of spring
([earth-lord-courses.md](earth-lord-courses.md), *The black days*); the
notes say «ཟླ་ནག་ནི་གནམ་ལོ་ལ་ལྟོས་པ་སྤྱི་ལྟར» (p. 486). A year's two black months are six
apart, so they always share their third.

**Built** as the course `ZLA_NAG` of the earth lords' row, on the days
of the black third, shown and not weighed as the other earth lords
(SPEC §5.13), with the year's two months and this one's third in the
month balloon. `MonthEntriesTest` checks p. 183's three pairs.

### Kikang's black month (*ki kang zla nag*)

The same page goes on, read on the scan:

> ར་བཞིའི་སྟོད་དང་འབྲིང་བཞིའི་སྒང་། །ཐ་ཆུང་བཞི་ཡི་ཟླ་སྨད་ནག །ཁྱད་པར་ཞག་དུས་སྤང་བྱ་བ། །སྟག་ལོའི་དབྱར་དགུན་ར་བ་ཡི། །ཚེས་བརྒྱད་ཐོ་རེངས་ཤིད་བྱས་ན། །རོ་བདག་ཤི་ཁ་བྱེ་བས་འཛེམ། །ཡོས་ལོའི་དབྱར་དགུན་འབྲིང་པོ་ཡི། །བཅོ་བརྒྱད་ཉི་ཤར་ཤིད་བྱས་འཕུང་། །འབྲུག་ལོའི་དབྱར་དགུན་ཐ་ཆུང་གི །ཉ་ལ་ཤིད་ཀྱི་བྱ་བ་འཛེམ། །སྦྲུལ་ལོའི་སྟོན་དཔྱིད་ར་བ་ཡི། །གནམ་གང་ལ་ནི་ཤིད་ངན་ནོ། །རྟ་ལོའི་སྟོན་དཔྱིད་འབྲིང་པོའི་ཉར། །མཁར་ལས་རྩིས་མཁན་དགེ་བཤེས་དང་། །ཁྱིམ་བདག་ལ་ནི་ངན་པའོ། །ལུག་ལོའི་སྟོན་དཔྱིད་ཐ་ཆུང་གི །ཉི་ཤུ་གཉིས་ཀྱི་བྱ་ཉལ་ལ། །ཚོང་བྱས་ནོར་ལ་གོད་ཁ་འོང་། །སྤྲེལ་ལོའི་དབྱར་དགུན་ར་བའི་བརྒྱད། །ཐོ་རེངས་དུར་བཏབ་མི་ཚང་འཕུང་། །བྱ་ལོའི་དབྱར་དགུན་འབྲིང་པོ་ཡི། །བཅོ་བརྒྱད་ཉི་ཤར་དགེ་ལས་ངན། །ཁྱི་ལོའི་དབྱར་དགུན་ཐ་ཆུང་གི །བརྒྱད་ལ་ས་རྡོ་འདམ་ལས་ངན། །ཕག་ལོའི་སྟོན་དཔྱིད་ར་བ་ཡི། །གནམ་གང་ཤིང་དང་རོ་འདོན་ངན། །བྱི་ལོའི་སྟོན་དཔྱིད་འབྲིང་པོའི་ཉར། །ཤིད་ཀྱི་བྱ་བ་ངན་པའོ། །གླང་ལོའི་སྟོན་དཔྱིད་ཐ་ཆུང་གི །ཉེར་གཉིས་བྱ་ཉལ་ཙམ་ལ་ནི། །དུས་དེར་ནོར་ཕྱིར་མི་བཏང་ངོ་། །བཏང་ན་གཡང་ཉམས་དབུལ་པོར་འགྲོ། །འདི་ལ་ཀི་ཀང་ཟླ་ནག་ཅེས། །ཤིན་ཏུ་གཉན་པས་གཟབ་གལ་ཆེ། །སྒྲིབ་ཐབས་ཉི་ནག་སྐབས་སུའང་འཆད།

| Year | Months | Date, time | Bad |
| --- | --- | --- | --- |
| tiger | first of summer and winter | 8th, dawn | funeral rites |
| hare | middle of summer and winter | 18th, sunrise | funeral rites |
| dragon | last of summer and winter | full moon | funeral rites |
| snake | first of autumn and spring | 30th | funeral rites |
| horse | middle of autumn and spring | full moon | building a fort |
| sheep | last of autumn and spring | 22nd, *bya nyal* (dusk) | trade |
| monkey | first of summer and winter | 8th («བརྒྱད»; the etext's «བརྒྱུད» is a misprint), dawn | burial |
| bird | middle of summer and winter | 18th, sunrise | virtuous work |
| dog | last of summer and winter | 8th | earth, stone and mud work |
| pig | first of autumn and spring | 30th | wood, carrying out a corpse |
| mouse | middle of autumn and spring | full moon | funeral rites |
| ox | last of autumn and spring | 22nd, *bya nyal* | sending wealth out |

The hare's and bird's «ཉི་ཤར» (sunrise) is on the scan; the etext has
«ཉི་མྱུར». The remedy is "told under the black days" (vol. 2, p. 235).
**Built** as the course `KI_KANG_ZLA_NAG`, the year and seasons the
Chinese reckoning's, on its date, with the date, time and work in the
month balloon when it falls in the month.

## The seasonal signs

p. 176's list, read on the scan: «རི་ཥི་ཞག་བདུན་ཆུ་ཀླུང་རྣམས། །ཡན་ལག་བརྒྱད་ལྡན་འགྱུར་བ་དང་། །དུར་ཕག་ཤིང་ལ་འཛེགས་པའི་འབྲས། །ཞག་བདུན་ཆུ་རྣམས་དུག་འགྱུར་ཚུལ། །དུ་བ་མཇུག་རིང་འཆར་བ་ན། །འདི་ལ་འཆར་ཞེས་ཚེས་ལ་སོགས།»
the Ṛṣi's seven days when the rivers gain their eight qualities; the
fruit of the charnel ground's pig climbing the tree, seven days when the
waters turn to poison; and when a long-tailed comet rises, "it rises on
this date". The pig climbing the tree and the poisoned waters are one
sign. WB dates all three in ch. 16 (vol. 1, pp. 184–189, img. 194–199).

### The Ṛṣi's and the pig's seven days

pp. 187–188 (img. 197–198), read on the scan:

> …ཡན་ལག་བརྒྱད་ལྡན་ཞག་བདུན་ལ། །འགྱུར་བར་སོམ་ཉིའི་དྲ་བ་བཅད། །རྣམ་དག་གྲུབ་པའི་ཉི་བར་གྱི། །སྐར་ཕྱོགས་མེ་མཚོ་མཁའ་མེ་སྲང་། །ཤར་ཚེ་རི་ཏིའི་དུས་འབྱུང་ངོ་། །ཕག་ཞག་རྒྱུ་མཚན་མཛངས་བླུན་ལས། །རི་རབ་ཤར་དུ་དཔག་བསམ་ཤིང་། …ཞེས་དང་དུར་ཕག་བཟའ་མི་གཉིས། །འཛེག་ལ་ཡར་མར་གསུམ་གསུམ་དང་། །རྩེ་ལ་ཞག་གཅིག་བདུན་གྱི་བར། །ཆར་བབ་དུག་ཆར་འབྲུ་བཅུད་ཉམས། །བཞོན་མར་ཕྱིར་གཏོང་ཆུ་ལས་དང་། །སྨན་དཔྱད་འཛེམ་པ་ལ་སོགས་པར། །མཁས་པ་ཕལ་ཆེར་ཚད་བཟུང་བ། །བྱེད་པར་མ་གཏོགས་གྲུབ་པ་ལ། །ཚད་ཀྱི་སྐར་མ་མེད་པའི་གཤིས། …འཁྲུལ་བྲལ་རྣམ་དག་གྲུབ་རྩིས་དང་། །བསྟུན་ནས་འཕྱུགས་མེད་ཉི་བར་ལ། །མདའ་དང་དུས་མཚོ་མཁའ་མེ་སྲང་། །ཤར་ཚེ་ཕག་གི་ཞག་པོ་བདུན།

The sage's star (*drang srong ri ti*) rises six months by night and six
by day; its first rising at dawn, hard to fix by the eye, brings seven
days in which the waters gain the eight qualities. Phug pa Lhun grub rgya
mtsho fixed it in the *byed rtsis*; WB fixes it in the true reckoning:
when the mean sun (*nyi bar*) reaches 10 mansions, 43 chu tshod, 30 chu
srang (*skar phyogs*, *me mtsho*, *mkha' me*). The pig's seven days come
from the story in the *Mdzangs blun* of the trees around Meru: the
charnel ground's pig and its fellow climb three days up, a day at the
top and three down; for seven days the rain is poison and the grain's
essence fails; sending out milk and butter, work with water and medical
treatment are avoided. Most scholars fixed it in the *byed rtsis* only;
WB in the true reckoning, when the mean sun reaches 5;46,30 (*mda'*,
*dus mtsho*, *mkha' me*).

**The table** (pp. 188–189, img. 198–199): for each of 65 years
(*bya ra ba* 1–65) each sign's date with its "desired part" (*'dod cha*,
in thirteenths) and fifth part (*lnga cha*, 1 for the sage, 4 for the
pig). The rule after it (p. 189, read on the scan): «རབ་བྱུང་འདས་ལོར་དུས་མིག་བྱིན། །མདའ་རོས་བགོས་པའི་ལྷག་མ་ཡིས། །བྱ་རའི་རེའུ་མིག་བཟུང་བ་ལ། …དེ་ལྟར་བཟུང་བའི་ཚེས་གྲངས་དེའི། །རྣམ་དག་གྲུབ་པའི་ཉི་བར་ལ། །འདོད་ཆ་ལྔ་ཆ་གྲངས་མཐུན་བྱིན། །སོ་སོའི་ཚད་དེ་འཕྱུགས་མེད་འཆར། །འདོད་ཆ་ལྔ་ཆ་གང་ཤར་ལྟར། །སྔ་ཕྱིའི་ཚེས་གྲངས་གང་ཟིན་བརྟག»:
the column is the rab byung's elapsed years plus 26 over 65; then the
date, or the one before or after it, by the parts. The parts grow by one
a year and the date by 11, by 12 when the part comes round to nought, so
the table is its first column and that step: the sage 27 with 5, the
pig 19 with 4. Read on the scan: the first block's dates (sage 27, 8, 19,
30, 11, 22, 3, 14, 26, 7, 18, 29, 10, 21, 2; pig 19, 30, 11, 22, 3, 14,
25, 6, 17, 29, 10, 21, 2, 13, 24) and parts, and the blocks' first and
last cells (columns 16, 34, 35, 52). The Yigdzin-1 reading of the table
is noise.

**Built** (`MonthEntries.signDate`, `signDay`): the sign falls on the
date at whose end the mean sun of Janson's arithmetic (the *grub rtsis*
mean sun) has reached the measure, and its seven days are counted from
the calendar day of that date. Against WB's table: for every year from
1990 to 2100 the engine's date is the table's, or the next one where the
part is 12, which is p. 189's "the date before or after" (the engine's
crossing lies 0.908 of a date before the table's date and parts, the
same in every column); column 1 is 2014, since the true reckoning
repeats in 65 years (804 months). 2026: the pig's days from the 2nd of
the 6th month (16 July), the sage's from the 10th of the 8th
(21 September). The column rule's "elapsed years plus 26" gives
column 13 for 2026 from no epoch tried (1027, 1687, 1987); it is not
needed, since the engine reckons the measure directly. Not used: KD's
rougher dating, the sage from the 8th month's *dbugs thob* (vol. 2,
p. 223, etext p. 243).

### The comet

p. 186 (img. 196), read on the scan:

> རབ་བྱུང་ལ་སོགས་འདས་པའི་ལོ། །ཉི་མས་བསྒྱུར་ལ་ནག་པ་སོགས། །འདས་ཟླ་བསྲེས་ལ་གནས་གཉིས་བཞག །འོག་མ་མིག་བསྒྱུར་གཟུགས་མེས་བརྒྱན། །དབང་རོས་བགོས་ཐོབ་ཟླ་བ་དག །ཟླ་བ་དག་པར་རྩེ་མོ་བྱིན། །དབང་རིས་བགོས་ལྷག་ཐིག་ཤར་ན། །མཇུག་རིང་དུ་ཕོད་བཅས་པ་མཐོང་། །ཐོབ་ནོར་མ་བྱུང་ནམ་སྨད་དང་། །ཆ་བྱུང་ནམ་སྟོད་མཐོང་བར་བཤད། །སྐབས་འགར་རི་མོར་མ་ཤར་ཡང་། །ངན་ལྟས་ཡིན་གྱིས་མཐོང་བར་སྲིད། …ཐོག་མའི་ཟླ་བ་སྐར་མ་དང་། །བར་དུ་དུ་ཕོད་རྗེས་སྐར་མ། །ཟླ་གསུམ་རྫོགས་ནས་མི་ཡལ་བ། །མི་སྲིད

The elapsed years ×12 (*nyi ma*) with the months elapsed since *nag pa*,
in two places; the lower ×2 (*mig*) + 31 (*gzugs me*), over 65 (*dbang
ro*), the quotient added: the corrected month count; +3 (*rtse mo*), over
75 (*dbang ris*): no remainder, a comet with its smoky tail is seen,
before dawn or in the evening by the quotient (not built: which quotient
is meant is not clear). It may also show as an omen when the count does
not mark it, first as a star, then with its tail, then as a star, and
does not fade before three months are out.

**The epoch.** "The years elapsed of the rab byung and the like" could be
those of the present 60-year cycle or those since the rab byung began.
KD restates WB's count among other comet counts (Bu ston's, Rga lo's and
Rngog's on the corrected months with *bzang po* added; WB's with *yon
tan*, 3), and words the years «རབ་རྒྱུན་ནས་བཟུང་འདས་པའི་ལོ» (vol. 2, p. 145, etext
p. 165): **since the rab byung began, 1027**. Its months count from *nag
pa*, the Kālacakra's 3rd month, so the 1st and 2nd months close the
year before. **Built** (`MonthEntries.cometMonth`), in the month
balloon; 2000–2049 it marks the 3rd month of 2003 and 2009, the 4th of
2015, the 5th of 2021, the 6th of 2027 and 2033, the 7th of 2039 and the
8th of 2045. A count, not the sky: it marks no month of the comets of
1680 or 1682.

## The eclipses: built by the *byed rtsis* (T2.23)

p. 176: «ཉ་སྟོང་ཟླ་ཉི་སྒྲ་གཅན་གྱིས། །སྒྲིབ་ཚེ་དུས་ཚོད་ཆ་ཡུན་དང་། །བཟང་ངན་ལ་སོགས་སྙིང་པོ་འདྲི།» WB's
reckoning is ch. 9 (vol. 1, pp. 56–70, img. 66–80), in ten sections:
whether the moon or sun is seized, the time, the side it is seized from,
the size, the length, the side left, the colour, the side it is released
from, the merit multiplied, and the results by month, weekday and
mansion (p. 63). It rests on Rāhu's head (*gdong*) and tail (*dus me*) by
the true reckoning, ch. 5, p. 34 (img. 44), read on the scan:
«རབ་བྱུང་ཉི་མའི(༡༧)ཐོག་མ་ཡི། །ཟླ་བ་རྣམ་པར་དག་པ་ལ། །བུ་ག་ནམ་མཁའ་འཁྲིག་པ(༢༠༩)བྱིན། །མཁའ་མེས(༣༠)བསྒྱུར་ལ་གང་བརྩི་ཡི། །ཚེས་གྲངས་བསྲེས་ལ…»:
the corrected month count from the start of the rab byung *nyi ma*, plus
209, ×30, with the date, over Rāhu's 230 months; his course subtracted
from 27 mansions is the head, half a circle on the tail. The lunar rule
(p. 59, img. 69): the moon of the full moon is the true sun plus 13;30;
taken from Rāhu's nearer end, the moon is seized within 57 chu tshod after
the head, 50 before the head or the tail, 45 after the tail; a head
eclipse beyond 58 or a tail one beyond 55 is not seen.

T2.21 left them unbuilt, for three reasons; T2.23 answered them (below):

1. **The epoch was in doubt; settled 2026-10-10** (open question 16):
   Henning's Phugpa Rāhu puts the 3rd month of 1687 at WB's 209, so the
   number word stands. *Nyi ma* is the number word for 12: the
   12th rab byung begins in 1687, WB's own time; the 1996 editors'
   bracket reads 17 (1987). With 1987 Rāhu is four mansions from any
   real eclipse. With 1687, WB's 209 carried to 1987 is 10, while KD's
   restatements from the 17th rab byung's fire hare add *drag po*, 11
   (vol. 2, etext p. 324), and *sor mo* (vol. 4, etext p. 375) to the
   corrected months, and *rtsa* in another (vol. 2, etext p. 458): a
   month apart from WB's, or counting the months otherwise. No worked
   eclipse in WB or KD has been found; Henning's reckoning settled it
   (open question 16, answered). The app's Rāhu (`Planets`) is that one.
2. **The solar rule needs the *byed rtsis*** (pp. 65–67, img. 75–77: the
   *byed rtsis* governs where the two reckonings part, and the sun in the
   east's seven mansions), which T2.23 builds.
3. **Checked against the sky**, the lunar rule as read with the 1687
   epoch finds 13 of the 23 lunar eclipses of 2015–2030 and marks 14 full
   moons with none (2025's two total eclipses fall 62 and 59 chu tshod
   from Rāhu's ends; 2028's come a month late); 80 in 2000–2049. Built
   now, it would put eclipses on nights without one.

The eclipses moved to T2.23 with the *byed rtsis* (ROADMAP).

### T2.23: the heading's last entries, read 2026-10-10

Vol. 1 img. 39–80 and 186–187 read with Yigdzin-1 (`hf_read.py`), every
line quoted here and every number word read on the scan at 3×; the
bracketed numerals of the 1996 edition, and the reader's copies of them,
are often wrong (༢༡ for *zla me*, 31), so the words decide.

**p. 176 (img. 186)**, the heading's rest: «བྱེད་གྲུབ་གཉིས་ཀའི་ཆད་ལྷག་དང་། །དབང་ཕྱུག་མཇལ་ཕྲད་རི་མོ་རྣམས། །བཞེངས་བཞུགས་ཁ་བྱང་འབྲས་བུ་འདྲི། །ཉ་སྟོང་ཟླ་ཉི་སྒྲ་གཅན་གྱིས། །སྒྲིབ་ཚེ་དུས་ཚོད་ཆ་ཡུན་དང་། །བཟང་ངན་ལ་སོགས་སྙིང་པོ་འདྲི།
…དེ་ནས་ཞག་གསུམ་གང་ཡིན་ཀྱང་། །ཉ་སྟོང་གཟའ་ལྔའི་དལ་མྱུར་དང་། །ཟླ་བ་དེ་ཡི་སྒེར་དྷུ་ཝ། །ཡོད་ན་བཀོད་པར་བྱ་བ་དང་། །བྱེད་གྲུབ་སོ་སོའི་དྷུ་ཝ་དང་། །ཉ་སྟོང་སྒྲ་གཅན་རི་མོ་བཀོད། །དེ་ནས་སོ་སོའི་ཚེས་ཁོངས་ལ། །རྣམ་པར་དག་པའི་གྲུབ་རྩིས་དང་། །བྱེད་རྩིས་གཉིས་ཀ་གནས་ལྔའམ། །མ་འགྲུབ་གནས་གསུམ་མི་ནོར་བའི། །མཚན་མ་དང་བཅས་བཀོད་པ་དང་»:
both reckonings' omitted and doubled dates; the Lord's meeting drawn,
standing or sitting, with its label and result; at the full and new moon
the eclipse, its time, part and length and its good or ill; the five
planets' slow and fast at the full and new moon; the month's own figures
(*dhru ba*) and each reckoning's; Rāhu's figure at the full and new moon;
and in each date both reckonings in five places, or three.

**The *byed rtsis*** (ch. 4, p. 31, img. 41): «བསྡུས་པའི་རྒྱུད་ཀྱི་རྗེས་འབྲངས་པའི། །བྱེད་པའི་རྩིས་ནི་བསྟན་པ་ལ། །རབ་བྱུང་ཉི་མའི་ཐོག་མ་ཡི། །མེ་ཡོས་ལ་སོགས་འདས་པའི་ལོ། །ཉི་མས་བསྒྱུར་ལ་ནག་པ་སོགས། །འདས་ཟླ་བསྲེས་ལ་གནས་གཉིས་བཞག །འོག་མ་མིག་བསྒྱུར་བུག་གཟུགས་བསྣན། །མདའ་རོས་ཐོབ་པ་ཟླ་དག་གོ»:
the years since the fire hare of 1687 times twelve with the months since
*nag pa*; the lower copy doubled with 19, over 65, added: the corrected
month. «བསེ་རུ་ཟླ་མེ་མཁའ་འབྱུང་བསྒྱུར། །སྟེང་ནས་ནམ་མཁའ་ཆུ་ཚོད་མིག །ཕྱོགས་ཀྱིས་ཕྲི་ལ་མཁའ་རོ་གཉིས། །ཐུབ་པས་དོར་ལྷག་གཟའ་དྷྲུའོ»:
the weekday 1;31,50 a month, 0;2,10 taken off. «ཟླ་དག་གནས་གཉིས་སྟེང་མ་མིག །འོག་མ་གཟུགས་ཀྱིས་བསྒྱུར་བ་ལ། །རིལ་བུག་ཆ་ཤས་རྩ་རི་ཕྲི། །དུས་ཁྱིམ་ཀླུ་ལག་དོར་བའི་ལྷག»:
the anomaly 2;1, 9;79 taken off, by 126 and 28. «ཟླ་དག་གནས་ལྔ་སྟེང་རིམ་བཞིན། །མིག་ཕྱོགས་ཀླུ་དབང་ལག་ཕྱོགས་བསྒྱུར། །སྐར་གནས་དུས་མིག་ཆུ་ཚོད་དུ། །མཚོ་དབང་ཆུ་མིག་ཆུ་སྲང་ཡིན། །དབུགས་མེ་ཆ་ཤས་བུ་གས་ཕྲི། །འདོད་རོ་མཁའ་རོ་ཐིག་རོ་དང་། །འཁོར་ལོས་དོར་ལྷག་ཉི་དྷྲུའོ»:
the sun 2;10,58,2,10, 26;54,24,3,9 taken off, by 13, 6, 60, 60 and 27.
Each date «གཟའ་དྷྲུར་ཆུ་ཚོད་རྩ་དབང་དང་། །སྲང་ཡོན་དབུགས་ཆུ་ཆ་ཐིག་དང་། །ཉི་དྷྲུར་དབྱུག་མཚོ་ཟླ་མིག་སྲང་། །དབུགས་དབང་ཆ་ཤས་བུ་ག»: 0;59,3,4,0
and 0;4,21,5,9. The moon's steps 5, 5, 5, 4, 3, 2, 1 over fourteen of
126 («ལྔ་གསུམ་བཞི་དང་གསུམ་གཉིས་གཅིག། །ལྡོག་པ་དང་བཅས་བཅུ་བཞིའི་གནས»), the odd fourteens subtracting; the sun
less 6;45, steps 6, 4, 1 over six of 135 (p. 32). These are the true
reckoning's equations, so the two differ only in their mean motions.
The *grub rtsis* of the same chapter (p. 29) doubles with 15: Janson's
intercalation index carried from 806 to 1687 is 15, so both count from
the same month, the true reckoning's 10 898th. WB adds the *Zhal lung*'s
correction (p. 32, «གཟའ་གནས་དུས་དང་ཆུ་ཚོད་དུ། །རོ་མཚོ་སྲང་ལ་དུས་རྒྱ་མཚོ། །དབུགས་འཁྲིག»: 6;46,46,2 off
the weekday, 0;28,46,2,4 off the sun) and keeps the plain way, since the
error grows (p. 32); the weekday's correction, 0;13,13,4 forward, is the
true reckoning's lead at the epoch to a tenth of a chu tshod
(`ByedRtsisTest`); the sun's (28.8 chu tshod) does not meet its lead
(35.8). No worked example is in the chapter.

**The lunar rule** (ch. 9, p. 59, img. 69): «དང་པོ་འཛིན་དང་མི་འཛིན་ནི། །བསྡུས་པའི་རྒྱུད་ཀྱི་ཉི་དག་ལ། །འདོད་པ་མཁའ་མེ་བྱིན་པ་དེ། །ཚེས་འཁྱུད་ཟླ་སྐར་ཞེས་གྲགས»: the true
sun *of the abridged tantra*, the *byed rtsis*, with 13;30. Then «ཟླ་བར་གདོང་སྦྱངས་ལྷག་མ་རུ། །རི་དབང་མན་ཆད་འཛིན་པ་དེ། །གདོང་ལ་ཟླ་བས་སྦྱངས་པ་དང་། །དུས་མེ་ཟླ་བས་སྦྱངས་པ་ལ། །མཁའ་འབྱུང་མན་ཤར་འཛིན་པར་ངེས། །ཟླ་བ་དུས་མེས་སྦྱངས་པའི་ཚེ། །འབྱུང་མཚོ་མན་ཆད་འཛིན་པ་ཡིན»:
the moon past the head by 57 or less, before the head or the tail by
50, past the tail by 45 (the instrumental is the subtrahend: «ཟླ་བ་དུས་མེས་སྦྱངས» is the
moon less the tail). The T2.21 trial used the true reckoning's sun.

**The solar rule** (p. 65, img. 75): «གདོང་གིས་ཉི་སྦྱངས་སྐར་གནས་སྟོངས། །གོ་མ་ལོག་པའི་རི་མོ་སྟེ། །ཆུ་ཚོད་མིག་དབང་བར་དུ་འཛིན། །ཉི་མས་གདོང་སྦྱངས་གདོང་པ་ཐུང་། །གོ་ལྡོག་ཅེས་གྲགས་ཆུ་ཚོད་མདའ། །ལྷག་ན་འཛིན་པ་མ་ཡིན་ནོ། །དུས་མེས་ཉི་སྦྱངས་མཇུག་མ་རིང་། །དེ་ཡང་གོ་ལྡོག་ཅེས་གྲགས་པས། །སྐར་ཐིག་ཆུ་ཚོད་རི་ཙམ་འཛིན། །ཉི་མས་དུས་མེ་སྦྱངས་པ་ན། །མ་ལྡོག་མཁའ་ཆུའི་ནང་ཚུན་འཛིན»:
the sun past the head by 52, before it by 5, past the tail by 0;7, before
it by 40; and «བྱེད་གྲུབ་ཁྱད་རྣམས་གོང་སྨྲས་ཀྱི། །ཁྱད་པར་ཙམ་ལས་བྱེད་རང་གཙོ», the *byed rtsis* is the
principal. p. 64's two exceptions are open question 18.

**Against the sky**, 2000–2049 (Meeus, ch. 54; `EclipsesTest`): every one
of the 79 full moons the lunar rule marks has a lunar eclipse (65 of the
72 umbral ones, 14 penumbral); it misses five of umbral magnitude under
0.1, 2001-07-05 (0.49) and 2019-07-17 (0.65). Every one of the 33 new
moons the solar rule marks has a solar eclipse; those it misses on the
northern side are partial ones of gamma near 1. The 2025 eclipses fall
5 and 11 chu tshod from Rāhu's ends.

**The merit** (p. 62, img. 72): «དགུ་པ་དགེ་བའི་འགྱུར་ཁྱད་ནི། །དུས་བཟང་དཀྱུས་རྣམས་བརྒྱ་འགྱུར་ལ། །ཟླ་འཛིན་བྱེ་བ་བདུན་འགྱུར་དང་། །ཉི་འཛིན་བྱེ་བ་འབུམ་འགྱུར་དུ»: a
hundredfold on the ordinary good occasions, seven *bye ba* (seventy
million) at a lunar eclipse, a hundred thousand *bye ba* (a million
million) at a solar one. Elsewhere WB names eclipses as an omen in
divination (vol. 2, p. 29) and in a birth (p. 484); no work list names
them.

**Read and left out** (the owner, 2026-10-10: what neither serves nor
changes the weights or the verdict is not shown):

- The planets' motions (ch. 6, p. 40, img. 50): «ལུགས་ལྡོག་ལུགས་འབྱུང་རི་མོ་འཆར། །རིམ་པའི་སྔ་རྐང་མྱུར་འགྲོས་ཤར། །ཕྱི་རྐང་དལ་འགྲོས་ལྷོ་རུ་བྱེད། །རིམ་མིན་སྔ་རྐང་འཁྱོག་འགྲོས་ནུབ། །ཕྱི་རྐང་འབྱུང་བའི་འགྲོས་ཏེ་བྱང»: by the
  fast equation's table, read in its order the first seven steps are
  the fast motion (east) and the rest the slow (south); reversed, the
  crooked (west) and the emerging (north). Reckoned with Henning's
  routine (the first step is his 14th), each planet runs through the
  four in turn; the crooked follows the opposition.
- The Lord's meeting (ch. 5, p. 36, img. 46): «ཀར་མྱང་རྩིས་ལ་བུག་མཁའ་དང་། །མི་བདག་ལོ་ལ་རབ་ཉི་ནས། །གང་བརྩིའི་བར་གྱི་འདས་ལོ་བསྲེས། །དེ་ནི་ཤཱ་ཀའི་ལོ་ཞེས་གྲགས» (1609 with the years since 1687,
  the Śaka year), «ཡང་ནི་ཤཱ་ཀའི་འདས་ལོ་དེ། །དབང་ཕྱུག་སྦྱངས་ལ་ལྷག་མ་དེ། །མཚོ་ཡིས་དོར་ལྷག་གཟུགས་ཤར་ན། །རྫིང་བུར་འཇུག་ཅེས་ཐན་པ་ཆེ། །གཉིས་པ་ཡན་ལག་འཇུག་ཅེས་པ། …མེ་ཤར་ཀུན་འཇུག་ཅེས་ཟེར་ཏེ། …ཐིག་ལ་ཞོད་ཅེས་ཆུ་བོ་རྒྱས། །དབང་ཕྱུག་མཇལ་ཕྲད་ཅེས་བགྱི་བ»: the Śaka year
  less 11, by four, gives the year's rain. *zhod* in *la zhod* is not in
  the dictionaries searched.
- The two reckonings' month figures and omitted and doubled dates, and
  each date's five places, as above; Rāhu's figure.
- The eclipse's time, part, length, side and results (ch. 9, sections
  2–8 and 10, pp. 59–69): no works.
