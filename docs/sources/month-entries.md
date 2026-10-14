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

O10 (2026-10-14): the passages quoted here by their first lines or with
gaps are written out whole, each from Yigdzin-1 with BDRC's etext as
witness (its bracketed numbers taken where Yigdzin-1 drops them), read
line by line on the Gser thang reprint of the same typesetting (གསེར་ཐང, gser thang; I3CN5028, image = page − 1 at pp. 185–189, page + 7 at pp. 31–65), the doubtful words also on BDRC's scan; the
table of pp. 188–189 cell by cell. Vol. 1, p. 176, the month heading, is
written out whole in [almanac-page.md](almanac-page.md) (O10c); the
quotations of it here are its lines.

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
11th to the 20th (vol. 2, p. 484, img. 492, read on the scan, BDRC's and the Gser thang
reprint's, I3CN5029 img. 490: «ཟླ་བའི་སྒང་ཞེས་བཅུ་གཅིག་ནས་ཉི་ཤུ་བར་རོ», where Yigdzin-1
reads «སྣང»), so the upper
third is the 1st to the 10th and the lower the 21st to the 30th.

**Which year and which seasons.** WB counts the black months among the
earth lords (vol. 2, pp. 174, 199: «རྣམ་གྲངས་རེ་བཞི་ཟླ་ནག་དང་། །བུམ་སྟོང་…»), and ch. 31
counts its seasons and the black days by the Chinese reckoning, the
year beginning with the 11th month, the tiger month the first of spring
([earth-lord-courses.md](earth-lord-courses.md), *The black days*); the
notes say «ཟླ་ནག་ནི་གནམ་ལོ་ལ་ལྟོས་པ་སྤྱི་ལྟར། གཤེད་བཞི་ནི་མཚམས་བཞིའི་ཟླ་བ་ཐ་ཆུང་ལ་ཞུགས་ན་ཞེས་སོ།» (p. 486,
img. 494, l. 25–26, Yigdzin-1 and the etext agreeing, not read on the
scan): the black months by the sky year, as in general; and the
four-slayer, "when it falls in the last month of the four seasons". A year's two black months are six
apart, so they always share their third.

**Built** as the course `ZLA_NAG` of the earth lords' row, on the days
of the black third, shown and not weighed as the other earth lords
(SPEC §5.13), with the year's two months and this one's third in the
month balloon. `MonthEntriesTest` checks p. 183's three pairs.

### Kikang's black month (*ki kang zla nag*)

The same page, vol. 2, p. 212 (img. 220), goes on, read on the scan
(again on the Gser thang reprint, I3CN5029, img. 218, in O10):

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

The hare's and bird's «ཉི་ཤར» (sunrise) was read on the scan in T2.21; the
etext and Yigdzin-1 have «ཉི་མྱུར». O10 (2026-10-14) read both lines again
on the Gser thang reprint at 4–7×: in both the word after «ཉི» is one
stacked letter with a subscript and «-ར» (མྱུར or the like), not «ཤར»; on
BDRC's 703-px scan it cannot be told. So «ཉི་ཤར» is doubtful; «ཉི་མྱུར», "the
sun quick" (not settled as a time of day), may be the print's. The remedy is "told under the black days" (vol. 2, p. 235).
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

pp. 187–188 (img. 197–198), the whole of the two signs, read on the scan (O10: Gser thang img. 186–187; the etext reads «ཡན་ལག་བརྒྱུད་པའི», the scan «བརྒྱད་པའི»):

> དྲང་སྲོང་རི་ཏི་ཞེས་པ་ནི། གོ་ལའི་གདུགས་ཀྱི་ཤམ་བུའི་ཚུལ། །ཟླ་དྲུག་མཚན་འཆར་ཟླ་དྲུག་ཉིན། །འཆར་བ་རྒྱུ་སྐར་སྤྱི་འགྲེ་བཞིན། །ཉིན་མོར་ཞུགས་ནས་ཐོ་རེངས་ལ། །ཐོག་མར་དོན་པ་དེ་ཉིད་ཀྱང་། །གདུགས་རི་མཐོ་བ་ལ་སོགས་ནས། །སྲིབས་རིའི་ལྡེབས་ནས་མཐོང་བའི་བར། །ཟླ་བ་ཕྱེད་གཉིས་ལྷག་བཅས་འགོར། །དེ་ཕྱིར་མིག་མཐོང་འཆར་བ་ཡི། །ཚད་བཟུང་ངེས་པར་དཀའ་ནའང་། །ཞག་བདུན་ཡན་ལག་བརྒྱད་པའི་ཆུར། །འགྱུར་བར་དྲང་སྲོང་དམོད་པ་དང་། །སྐར་མ་དེ་ཡི་གཞལ་ཡས་ཁང་། །ཀེ་ཏ་ཀ་ལས་གྲུབ་པ་སོགས། །སྔོན་གྱི་མཁས་པ་ཕལ་ཆེར་གྱིས། །འཁྲུལ་པ་མེད་པར་ཚད་འཛིན་ཞིང་། །དེ་ཡང་རབ་བརྒྱད་ཐོག་མ་རུ། །བྱེད་པར་དྲག་པོ་རྐང་བྲལ་ལ། །རི་ཏི་འཆར་བར་ཕུག་པ་བ། །མཁས་དབང་ལྷུན་གྲུབ་རྒྱ་མཚོས་མཛད། །དེ་ཡང་བྱེད་པར་མ་གཏོགས་པ། །གྲུབ་པར་གཏན་འབེབས་མ་མཛད་ཅིང་། །དེང་སང་བར་དུ་ཕུག་ལུགས་པ། །ཀུན་གྱིས་བྱེད་པའི་ཉི་དག་ལ། །དྲག་པོ་ཤར་བར་མཛད་ནའང་། །དེ་ཡང་རབ་བརྒྱད་ད་ལྟའི་བར། །བྱེད་པའི་སྐྱོན་སོགས་ཞག་གི་གྲངས། །གསུམ་གྱིས་སྔ་བར་སོང་བ་སོགས། །འཁྲུལ་པའི་ཆ་མང་གྱུར་པ་ན། །མཁས་དབང་ལྷུན་གྲུབ་རྒྱ་མཚོ་ལ། །འཁྲུལ་པ་ནམ་ཡང་མི་འབྱུང་བས། །རབ་བརྒྱད་དྲག་པོ་ཤར་བའི་དུས། །ཡན་ལག་བརྒྱད་ལྡན་ཞག་བདུན་ལ། །འགྱུར་བར་སོམ་ཉིའི་དྲ་བ་བཅད། །རྣམ་དག་གྲུབ་པའི་ཉི་བར་གྱི། །སྐར་ཕྱོགས་མེ་མཚོ་མཁའ་མེ་སྲང་། །ཤར་ཚེ་རི་ཏིའི་དུས་འབྱུང་ངོ་། །ཕག་ཞག་རྒྱུ་མཚན་མཛངས་བླུན་ལས། །རི་རབ་ཤར་དུ་དཔག་བསམ་ཤིང་། །བྱང་ཤར་ཡོངས་འདུ་ནུབ་ཙནྡན། །ལྷོ་ཕྱོགས་ཛམྦུ་བྲྀཀྵ་གནས། །ཞེས་དང་དུར་ཕག་བཟའ་མི་གཉིས། །འཛེག་ལ་ཡར་མར་གསུམ་གསུམ་དང་། །རྩེ་ལ་ཞག་གཅིག་བདུན་གྱི་བར། །ཆར་བབ་དུག་ཆར་འབྲུ་བཅུད་ཉམས། །བཞོན་མར་ཕྱིར་གཏོང་ཆུ་ལས་དང་། །སྨན་དཔྱད་འཛེམ་པ་ལ་སོགས་པར། །མཁས་པ་ཕལ་ཆེར་ཚད་བཟུང་བ། །བྱེད་ [p. 188, img. 198] པར་མ་གཏོགས་གྲུབ་པ་ལ། །ཚད་ཀྱི་སྐར་མ་མེད་པའི་གཤིས། །བྱེད་པའི་སྐྱོན་སོགས་དུས་འཕྱུགས་པ། །ཚད་ལྡན་མཁས་པའི་དུས་ཀྱི་ཚེ། །བྱེད་པའི་ཕག་ཞག་བརྩིས་པ་དེ། །འཁྲུལ་བྲལ་རྣམ་དག་གྲུབ་རྩིས་དང་། །བསྟུན་ནས་འཕྱུགས་མེད་ཉི་བར་ལ། །མདའ་དང་དུས་མཚོ་མཁའ་མེ་སྲང་། [a sign in the margin, not transcribed] །ཤར་ཚེ་ཕག་གི་ཞག་པོ་བདུན། །འདྲེན་པར་ཐེ་ཚོམ་སོམ་ཉི་བཅད། །

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
pig). The rule after it (p. 189, img. 199, read on the scan, whole with the chapter's closing verse; Gser thang img. 188): «དེ་ནས་རི་ཏི་ཕག་ཞག་གི། །ཚད་ནི་ནམ་འབྱུང་བསྟན་པའི་ཆེད། །རབ་བྱུང་འདས་ལོར་དུས་མིག(༢༦)བྱིན། །མདའ་རོས(༦༥)བགོས་པའི་ལྷག་མ་ཡིས། །བྱ་རའི་རེའུ་མིག་བཟུང་བ་ལ། །སྟེང་མ་རི་ཏི་འོག་ཕག་ཞག །འབྱུང་བའི་གོང་མ་ཚེས་གྲངས་དང་། །བར་པ་འདོད་ཆ་འོག་ལྔ་ཆ། །དེ་ལྟར་བཟུང་བའི་ཚེས་གྲངས་དེའི། །རྣམ་དག་གྲུབ་པའི་ཉི་བར་ལ། །འདོད་ཆ་ལྔ་ཆ་གྲངས་མཐུན་བྱིན། །སོ་སོའི་ཚད་དེ་འཕྱུགས་མེད་འཆར། །འདོད་ཆ་ལྔ་ཆ་གང་ཤར་ལྟར། །སྔ་ཕྱིའི་ཚེས་གྲངས་གང་ཟིན་བརྟག །དེ་ལྟར་རི་ཏི་ཕག་ཞག་དང་། །ཉི་ལྡོག་དུ་མཇུག་གཙུག་གསེར་བཅས། །ཐོར་བུའི་དཀའ་གནད་གསར་བཟོས་འགའ། །ཕྱོགས་སུ་བསྡེབས་ཏེ་སྨྲས་པའོ། །»:
the column is the rab byung's elapsed years plus 26 over 65; then the
date, or the one before or after it, by the parts. The parts grow by one
a year and the date by 11, by 12 when the part comes round to nought, so
the table is its first column and that step: the sage 27 with 5, the
pig 19 with 4. Read on the scan: the first block's dates (sage 27, 8, 19,
30, 11, 22, 3, 14, 26, 7, 18, 29, 10, 21, 2; pig 19, 30, 11, 22, 3, 14,
25, 6, 17, 29, 10, 21, 2, 13, 24) and parts, and the blocks' first and
last cells (columns 16, 34, 35, 52). The Yigdzin-1 reading of the table
is noise. O10 (2026-10-14) read every cell on the Gser thang scan (img. 187–188),
the doubtful ones on BDRC's too: of the 455 cells (dates, desired and fifth
parts, column numbers), all but four are the step rule's. The four, the same in both
scans: column 12, the sage's date «༢༧» where the step gives 29; column 15,
the sage's date «༨» where it gives 2 (the 2 read before here is the rule's,
not the print's); column 18, the sage's part «༧» where it gives 9; column
25, the sage's date «༡༣» where it gives 23. Each is a digit of like shape
(༧ for ༩, ༨ for ༢, ༡ for ༢); the pig's rows hold no slip. Every fifth part
is «༡» for the sage and «༤» for the pig. The table, as printed (dates, then
desired parts, over the columns):

| Column | 1–15 | 16–34 | 35–52 | 53–65 |
| --- | --- | --- | --- | --- |
| Sage, date | ༢༧ ༨ ༡༩ ༣༠ ༡༡ ༢༢ ༣ ༡༤ ༢༦ ༧ ༡༨ ༢༧ ༡༠ ༢༡ ༨ | ༡༣ ༢༤ ༥ ༡༦ ༢༧ ༨ ༢༠ ༡ ༡༢ ༡༣ ༤ ༡༥ ༢༦ ༧ ༡༨ ༢༩ ༡༠ ༢༡ ༢ | ༡༤ ༢༥ ༦ ༡༧ ༢༨ ༩ ༢༠ ༡ ༡༢ ༢༣ ༤ ༡༥ ༢༦ ༨ ༡༩ ༣༠ ༡༡ ༢༢ | ༣ ༡༤ ༢༥ ༦ ༡༧ ༢༨ ༩ ༢༠ ༢ ༡༣ ༢༤ ༥ ༡༦ |
| Sage, desired part | ༥ ༦ ༧ ༨ ༩ ༡༠ ༡༡ ༡༢ ༠ ༡ ༢ ༣ ༤ ༥ ༦ | ༧ ༨ ༧ ༡༠ ༡༡ ༡༢ ༠ ༡ ༢ ༣ ༤ ༥ ༦ ༧ ༨ ༩ ༡༠ ༡༡ ༡༢ | ༠ ༡ ༢ ༣ ༤ ༥ ༦ ༧ ༨ ༩ ༡༠ ༡༡ ༡༢ ༠ ༡ ༢ ༣ ༤ | ༥ ༦ ༧ ༨ ༩ ༡༠ ༡༡ ༡༢ ༠ ༡ ༢ ༣ ༤ |
| Pig, date | ༡༩ ༣༠ ༡༡ ༢༢ ༣ ༡༤ ༢༥ ༦ ༡༧ ༢༩ ༡༠ ༢༡ ༢ ༡༣ ༢༤ | ༥ ༡༦ ༢༧ ༨ ༡༩ ༣༠ ༡༡ ༢༣ ༤ ༡༥ ༢༦ ༧ ༡༨ ༢༩ ༡༠ ༢༡ ༢ ༡༣ ༢༤ | ༥ ༡༧ ༢༨ ༩ ༢༠ ༡ ༡༢ ༢༣ ༤ ༡༥ ༢༦ ༧ ༡༨ ༢༩ ༡༡ ༢༢ ༣ ༡༤ | ༢༥ ༦ ༡༧ ༢༨ ༩ ༢༠ ༡ ༡༢ ༢༣ ༥ ༡༦ ༢༧ ༨ |
| Pig, desired part | ༤ ༥ ༦ ༧ ༨ ༩ ༡༠ ༡༡ ༡༢ ༠ ༡ ༢ ༣ ༤ ༥ | ༦ ༧ ༨ ༩ ༡༠ ༡༡ ༡༢ ༠ ༡ ༢ ༣ ༤ ༥ ༦ ༧ ༨ ༩ ༡༠ ༡༡ | ༡༢ ༠ ༡ ༢ ༣ ༤ ༥ ༦ ༧ ༨ ༩ ༡༠ ༡༡ ༡༢ ༠ ༡ ༢ ༣ | ༤ ༥ ༦ ༧ ༨ ༩ ༡༠ ༡༡ ༡༢ ༠ ༡ ༢ ༣ |

The print's row heads: «རི་ཏིའི་ཚེས་གྲངས», «འདོད་ཆ», «ལྔ་ཆ», «བྱ་ར་བ», «ཕག་ཞག་ཚེས་གྲངས», «འདོད་ཆ», «ལྔ་ཆ».

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

p. 186 (img. 196), read on the scan, the whole section from its head (O10: Gser thang img. 185):

> མཇུག་རིང་རི་མོར་གཏན་འབེབས་ནི། །བསྡུས་རྒྱུད་བྱེད་པའི་ཟླ་དག་དེར། །བུག་གཟུགས(༡༩)བྱིན་ལ་དབང་རིས(༧༥)བགོས། །ནོར་མཉམ་རིམ་མིན་མི་མཉམ་རིམ། །ལྷག་དེ་མཁའ་མེས(༣༠)བསྒྱུར་བ་ལ། །གང་བརྩིའི་ཚེས་ཀྱི་གྲངས་དེ་བསྲེས། །མཁའ་ཀླུ་ཕྱོགས(༡༠༨༠)ཀྱིས་བགོས་པའི་ནོར། །སྣོན་འབྲིའི་མཚན་མ་བྱེད་པ་དང་། །དེ་ལྷག་གནས་གཉིས་སྟེང་མ་མིག(༢)། །འོག་མ་མེས(༣)བསྒྱུར་རོ(༦)མཁའ་རོས(༦༠)། །བསྒྲིལ་བས་རྐང་འཛིན་ལོངས་སྤྱོད་དག །དལ་དག་བྱེད་པའི་ཉི་དག་བསྒྲུབས། །མཁའ་ཀླུ་ཕྱོགས(༡༠༨༠)རྙེད་གཟུགས(༡)ཤར་རིམ། །ཐིག་ཤར་རིམ་མིན་ཉི་དག་ལ། །རིམ་བྱིན་རིམ་མིན་ཕྲི་བ་དང་། །ཉི་རྐང་དུས་ཆུ་ཟླ་བ་དེ། །སྔ་རྐང་རིམ་ཤར་ཕྱི་རྐང་ལྷོ། །རིམ་མིན་སྔ་རྐང་འཁྱོག་པོའི་འགྲོས། །ཕྱི་རྐང་འབྱུང་འགྲོས་བྱང་དུ་བྱེད། །སྐར་མ་གང་གནས་ལོངས་སྤྱོད་རྟོགས། །བཤོལ་ཡོད་ལོ་ཡིན་ཟླ་དག་སྟེང་། །གཟུགས་ཕྲི་བྱས་པས་ཚེགས་མེད་འཆར། །འདི་ནི་དུ་བ་མཐོང་བ་རུ། །འཆར་དང་མི་འཆར་ཆེ་ཆུང་སོགས། །ལྟས་ཀྱི་བཟང་ངན་སྟོན་པའི་ཆེད། །ངེས་པར་རྟོགས་པ་དཀའ་ནའང་། །རི་མོའི་ལམ་ནས་འདྲེན་པའི་ལུགས། །མང་ཡང་འདི་ཉིད་ངེས་པ་སྟེ། །རབ་བྱུང་ལ་སོགས་འདས་པའི་ལོ། །ཉི་མས(༡༢)བསྒྱུར་ལ་ནག་པ་སོགས། །འདས་ཟླ་བསྲེས་ལ་གནས་གཉིས་བཞག །འོག་མ་མིག(༢)བསྒྱུར་གཟུགས་མེས(༣༡)བརྒྱན། །དབང་རོས(༦༥)བགོས་ཐོབ་ཟླ་བ་དག །ཟླ་བ་དག་པར་རྩེ་མོ(༣)བྱིན། །དབང་རིས(༧༥)བགོས་ལྷག་ཐིག་ཤར་ན། །མཇུག་རིང་དུ་ཕོད་བཅས་པ་མཐོང་། །ཐོབ་ནོར་མ་བྱུང་ནམ་སྨད་དང་། །ཆ་བྱུང་ནམ་སྟོད་མཐོང་བར་བཤད། །སྐབས་འགར་རི་མོར་མ་ཤར་ཡང་། །ངན་ལྟས་ཡིན་གྱིས་མཐོང་བར་སྲིད། །དེ་ཡང་ནམ་སྟོད་ནམ་སྨད་དེ། །རྙེད་ནོར་འདི་ཡིས་མི་བསླུ་རྟོགས། །ཐོག་མའི་ཟླ་བ་སྐར་མ་དང་། །བར་དུ་དུ་ཕོད་རྗེས་སྐར་མ། །ཟླ་གསུམ་རྫོགས་ནས་མི་ཡལ་བ། །མི་སྲིད་བློ་གྲོས་ལྡན་པ་རྣམས། །དེ་ཡི་དུ་བ་ཕོད་ཆེན་གྱི། །མིང་འདོགས་ལ་སོགས་ཞིབ་ཆ་རྣམས། །ཁོ་བོས་སྔོན་སྤེལ་རྩིས་གཞིར་གསལ།།

The section opens with the comet's place by the abridged tantra's
reckoning (the corrected month +19, over 75; the remainder ×30 with the
date, over 1080; then the slow and true sun, and the planet's motion in
its four kinds), and says that whether the smoky tail rises and how large
is hard to know for certain, and of the many ways drawn from the reckoning
this one is sure. The elapsed years ×12 (*nyi ma*) with the months elapsed since *nag pa*,
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
principal.

**p. 64's two exceptions** (img. 74; open question 18, answered as far
as the sources go): «ཉི་མ་ལྷོ་བགྲོད་ཀརྐ་ཊའི་ཁྱིམ། །འཁྱོག་འགྲོས་དང་པོའི་ཉིན་ཞག་ནི། །གནམ་སྟོང་འགྲིག་ན་མི་འཛིན་བཞེད། །གཞན་ཡང་ལྷག་པ་པ་སངས་གཉིས། །གང་རུང་ཅིག་དང་སྐར་མ་གཅིག །ཆུ་ཚོད་འབྱུང་མེ་ཟླ་ཚུན་དུ། །ལྷན་ཅིག་གནས་ཚེ་བཟུང་མི་སྲིད».
The crooked motion is the sun's (OS pp. 1302–1303, JL; README), counted
on the sun's own equation (p. 32): its first day is the mean sun's
entry into 20;15, Capricorn, as the sun turns north, while Cancer is
where it turns south; the line names the two turnings. Both readers
make these days hard to seize (JL «འཛིན་པ་དཀའ»; OS «གཞི་ནས་མི་འཛིན་ངེས་བཅད་དཀའ»), not
days with none, WB's own word being «བཞེད», "is held". For the second,
the *Seven Special Qualities* compares the true sun with Mercury or Venus
in one mansion and makes the part seized smaller; which place of the
planet, and why 135 chu tshod, no source found says (O4, below).
Applied, each reading removes real eclipses: the crooked
motion's first day 2010-01-15; the sun in Cancer 2008-08-01,
2009-07-22, 2026-08-12, 2027-08-02, 2045-08-12; Mercury or Venus by the
true reckoning nearly all. The app applies neither.

### Mercury and Venus at an eclipse (O4)

Read 2026-10-12 for open question 18. WB vol. 1, p. 64 (img. 74) and
p. 34 (img. 44) with Yigdzin-1 and MITRA as witness, the lines quoted
below read on the scan at 4–8×; the other texts from BDRC's etexts
only (KD's and the Sakya volume's scans are lent on archive.org and
were not read), so their Tibetan is the machine reading.

**WB p. 64 (img. 74)**, lines 26–28, with the 1996 editors' bracket:
«གཞན་ཡང་ལྷག་པ་པ་སངས་གཉིས། །གང་རུང་ཅིག་དང་སྐར་མ་གཅིག །ཆུ་ཚོད་འབྱུང་མེ་ཟླ(༡༣༥)ཚུན་དུ། །ལྷན་ཅིག་གནས་ཚེ་བཟུང་མི་སྲིད། །དང་པོ་འཛིན་ནམ་མི་འཛིན་ལ། །འདི་ལ་གྲུབ་རྩིས་ལེ་ཚན་དང་། །ཅུང་ཟད་བསྟུན་ཙམ་དགོས་པའི་[ཕྱིར།]».
"Further, when either of the two, Mercury (ལྷག་པ, lhag pa) or Venus
(པ་སངས, pa sangs), stays together [with the sun] in one mansion (སྐར་མ,
skar ma), within 135 sixtieths (ཆུ་ཚོད, chu tshod), it cannot be
seized. For the first part, whether it is seized or not, the chapter
on the true reckoning (གྲུབ་རྩིས, grub rtsis) and some agreement with it
are needed." On the scan the bracket is (༡༣༥), as the number word reads
(Yigdzin-1 ༡༢༥, MITRA lost it, the 1996 etext lost the word's end and
the bracket), and «བསྟུན» is printed where KD's reprint and the Gser
thang (གསེར་ཐང, gser thang) edition's etexts have «བསྟན». The partner
of Mercury or Venus is not named; the section is the sun's.

**The *Seven Special Qualities* restates it.** Sde gzhung Chos 'phel
(སྡེ་གཞུང་ཆོས་འཕེལ, sde gzhung chos 'phel), *The Sun's Rays*, a
practice of reckoning with seven special qualities (ཁྱད་ཆོས་བདུན་ལྡན་གྱི་རྩིས་ཀྱི་ལག་ལེན་ཟབ་དོན་གསལ་བར་བྱེད་པའི་ལེགས་བཤད་ཉི་མའི་འོད་ཟེར, khyad chos bdun ldan gyi rtsis kyi lag len zab don gsal bar byed pa'i legs bshad nyi ma'i 'od zer):
in the Sakya (ས་སྐྱ, sa skya) volume that holds NM, its epoch the fire ox of the
16th sixty-year cycle (རབ་བྱུང, rab byung), 1937, printed pp. 166–167 (etext IE0OPI8BBA1CDB, volume I5721,
pp. 196–197; BDRC MW29978_C79345, the author there the Sde gzhung incarnation of
Li thang (ལི་ཐང་སྡེ་གཞུང་སྤྲུལ་སྐུ, li thang sde gzhung sprul sku)), and in KD vol. 2 with the epoch moved to the fire hare of
the 17th (1987) by Kun dga' rig 'dzin (ཀུན་དགའ་རིག་འཛིན, kun dga' rig 'dzin)
and Phur bu don grub (ཕུར་བུ་དོན་གྲུབ, phur bu don grub), printed p. 441
(etext p. 461; printed = etext − 20 by the page footers). A modern
work: quoted no further than the facts. Its solar eclipse opens with
«སྐྱོན་བྲལ་གྲུབ་པའི་གནམ་སྟོང་གི །ཉི་དག་དང་ནི་གདོང་མཇུག་གཉིས» (KD p. 440), the true sun (ཉི་དག, nyi dag) of
the faultless true reckoning's new moon against Rāhu's (སྒྲ་གཅན, sgra
gcan) head and tail, and after the side and the hiding comes
«ཉི་མ་རིལ་འཛིན་ཤར་ན་ཡང་། །ཟླ་བ་བཞིན་དུ་སྒྲིབ་མི་ནུས། །ཉི་དག་དང་ནི་ལྷག་སངས་གཉིས། །གང་རུང་སྐར་མ་གཅིག་གྱུར་ན། །འཛིན་ཆ་གོང་ལས་ཆུང་བར་འགྱུར།»:
even when a total seizing of the sun comes out, the sun is not covered
as the moon is; if the true sun and either Mercury or Venus come into
one mansion, the part seized is smaller than reckoned above. So:

- the sun's place compared is the true sun, the same that the text's
  solar rule takes (its opening holds the true reckoning's planets and
  the abridged reckoning's (བྱེད་རྩིས, byed rtsis) sun the right ones, «གྲུབ་པའི་གཟའ་དང་བྱེད་པ་ཡི། །ཉི་མ་དག་ཅེས་མཁས་རྣམས་བཞེད», KD p. 424,
  and corrects the true reckoning's sun so that it may serve);
- the planet's place is not named beyond the planet;
- the condition is one mansion, and the 135 is gone;
- the result is a smaller part, not no eclipse, as in JL («འཛིན་པ་དཀའ») and OS
  (above).

**Not the mean places.** Jo nang lo tsā ba Blo gros dpal (ཇོ་ནང་ལོ་ཙཱ་བ་བློ་གྲོས་དཔལ, jo nang lo tsA ba blo gros dpal),
*The Intent Made Clear, Gladdening the Learned* (མཁས་པ་རྣམས་དགའ་བར་བྱེད་པའི་དགོངས་པ་རབ་ཏུ་གསལ་བ, mkhas pa rnams dga' bar byed pa'i dgongs pa rab tu gsal ba),
on the five planets' mean motions (the Jonang series, ཇོ་ནང་དཔེ་ཚོགས, jo nang dpe tshogs, general vol. 16,
printed p. 197; etext VEIE0OPIA6E6FF51_I1PD95842, p. 223):
«ཞི་གཟའ་ལྷག་པ་དང་པ་སངས་གཉིས་ཀྱི་རྒྱུ་སྐར་འཁོར་བའི་རྟག་པའི་ལོངས་སྤྱོད་ནི། ཉི་མ་དང་མཚུངས་པ་ཡིན་ཏེ། ཞག་གསུམ་གྱི་དཀྱིལ་འཁོར་ཉི་མ་དང་མཚུངས་པ་ཡིན་པའི་ཕྱིར་ལོགས་སུ་བཤད་མི་དགོས།»:
the mean motion (རྟག་པའི་ལོངས་སྤྱོད, rtag pa'i longs spyod) of the gentle
planets (ཞི་གཟའ, zhi gza'), Mercury and Venus, round the mansions is the
sun's, their cycle of days being the sun's, so it is not given apart.
Their mean place is the mean sun's at every new moon; a clause that
compared mean places would hold always. What it compares must be a
place moved by the planet's own fast equation (མྱུར་བ, myur ba), its true
place: this step is ours, no text read draws it.

**135 sixtieths is a sign.** WB p. 34 (img. 44), on Rāhu, read on the
scan: «ཁྱིམ་གནས་ཇི་ལྟར་བལྟ་བའི་ཕྱིར། །སྐར་གནས་མཁའ་རོས(༦༠)བསྒྱུར་བ་ལ། །ཆུ་ཚོད་བསྲེས་ལ་འབྱུང་མེ་ཟླས(༡༣༥)། །ཐོབ་པ་འདས་པའི་ཁྱིམ་ཡིན་ཞིང་།»:
to see where he stands among the signs (ཁྱིམ, khyim), the mansions times
60 with the sixtieths, divided by 135, give the signs passed. 135 is one
sign, two mansions and 15. The annotated *Abridged Kālacakra Tantra*
(བསྡུས་པའི་རྒྱུད་གོ་སླའི་མཆན, bsdus pa'i rgyud go sla'i mchan; BDRC
MW1ALS05001I, from the ACIP database, etext VE1ALS05001I p. 23, the
annotator not named) gives the planets' motion in a month:
«ཟླ་བ་གཅིག་གི་ལོངས་སྤྱོད་མིག་དམར་གྱི་ཆུ་ཚོད་དགུ་བཅུ། །ལྷག་པ་དང་པ་སངས་གཉིས་ཀྱི་བརྒྱ་སུམ་ཅུ་སོ་ལྔ་རེ་རེ། ཕུར་བུའི་བཅུ་གཅིག །སྤེན་པའི་བཞི»,
Mars 90 sixtieths, Mercury and Venus 135 each, Jupiter 11, Saturn 4.
So 135 is one sign and the month's mean motion of Mercury and Venus,
the sun's. Neither text ties the number to the eclipse, and WB's "one
mansion" and "within one sign" do not sit together as one condition.

What this leaves: the second exception compares Mercury or Venus with
the sun, the *Seven Special Qualities* says with the true sun and in one
mansion, and reads it as a smaller eclipse rather than none, as JL and
OS read the whole list. Which sun WB means here (its solar rule makes
the abridged reckoning principal, p. 65) and which place of the planet,
and what the 135 adds, stay open.

#### Open

- Open question 150 ([open-questions.md](open-questions.md)): Kikang's «ཉི་ཤར» or «ཉི་མྱུར».
- Open question 116 ([open-questions.md](open-questions.md)).
- Open question 117 ([open-questions.md](open-questions.md)).
- Open question 118 ([open-questions.md](open-questions.md)).
#### Searched and not found

- BDRC full-text search, 2026-10-12: «ཆུ་ཚོད་འབྱུང་མེ་ཟླ་ཚུན», «ལྷན་ཅིག་གནས་ཚེ་བཟུང», «ལྷག་པ་པ་སངས་གཉིས»
  (WB's prints and reprints, KD, the Gser thang edition, a copy in verse
  in the works of Bstan 'dzin chos kyi rgyal mtshan (བསྟན་འཛིན་ཆོས་ཀྱི་རྒྱལ་མཚན, bstan 'dzin chos kyi rgyal mtshan),
  BDRC MW3JT13322, search only, «ལྷག་ཅིག» for «ལྷན་ཅིག», no gloss; otherwise
  Mercury and Venus in other contexts); «ལྷག་སངས་གཉིས་གང་རུང», «ལྷག་པའམ་པ་སངས»,
  «ལྷག་སངས་དང་ཉི་མ», «ལྷག་སངས་སྐར་མ་གཅིག», «ཞི་གཟའ་ཉི་མ་དང», «སྐར་མ་གཅིག་ཏུ་འཛོམས», «ཉི་མ་དང་སྐར་མ་གཅིག་ཏུ» (none);
  «ཉི་འཛིན་མི་འབྱུང», «ཉི་མ་དང་ལྷག་སངས», «ཞི་གཟའ་གཉིས་ཉི་མ», «ཞི་གཉིས་ཉི་མ», «ཉི་མ་དང་ལྷག་པ», «ཉི་མ་དང་ལྷན་ཅིག་གནས»,
  «ཉི་མ་དང་སྐར་མ་གཅིག» (eclipses without the planets, or the planets'
  reckoning without the eclipse); «སྐར་མ་གཅིག་གྱུར་ན» and «འཛིན་ཆ་གོང་ལས་ཆུང» (the *Seven Special
  Qualities* only).
- Etexts searched for Mercury, Venus or the gentle planets beside a
  solar eclipse: KD's five volumes (IE0OPI2F246A26), the Sakya
  *rtsis gzhung*'s two volumes (IE0OPI8BBA1CDB), JL (IE0OPI436D66B7),
  Bu ston (བུ་སྟོན, bu ston), *Making the Learned Glad* (རྩིས་ཀྱི་བསྟན་བཅོས་མཁས་པ་དགའ་བྱེད, rtsis kyi bstan bcos mkhas pa dga' byed;
  IE0OPID3FD8407), Kongtrul (ཀོང་སྤྲུལ, kong sprul), *The Treasury
  Pervading All Knowledge* (ཤེས་བྱ་ཀུན་ཁྱབ, shes bya kun khyab), three volumes (IE0OPIAC60CE45), the
  annotated *Abridged Kālacakra Tantra* (VE1ALS05001I) and Blo gros
  dpal's volume (VEIE0OPIA6E6FF51_I1PD95842): only the *Seven Special
  Qualities*.
- WB's author's *Removing the Tarnish from the Beryl* (བཻ་ཌཱུརྱ་གཡའ་སེལ, bai DUrya g.ya' sel): BDRC's search for «བཻ་ཌཱུརྱ་གཡའ་སེལ» finds only modern
  essays naming it, no etext of it.

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

### The eclipse and planet passages whole (O10)

The passages quoted above in parts, written out whole on 2026-10-14 from
Yigdzin-1 (img. 41–42, 44, 46, 69, 72, 74–75) with the etext's bracketed
numbers, every line read on the Gser thang reprint (I3CN5028, img. 38–39,
41, 43, 66, 69–72; image = page + 7). Bracketed numbers stacked two to a
box in the print, and the numbers in the margins, are not transcribed and
are marked.

*The* byed rtsis, ch. 4, p. 31, l. 10, to p. 32, l. 16 (img. 41–42; the
chapter's colophon follows):

> བསྡུས་པའི་རྒྱུད་ཀྱི་རྗེས་འབྲངས་པའི། །བྱེད་པའི་རྩིས་ནི་བསྟན་པ་ལ། །རབ་བྱུང་ཉི་མའི་ཐོག་མ་ཡི། །མེ་ཡོས་ལ་སོགས་འདས་པའི་ལོ། །ཉི་མས(༡༢)བསྒྱུར་ལ་ནག་པ་སོགས། །འདས་ཟླ་བསྲེས་ལ་གནས་གཉིས་བཞག །འོག་མ་མིག(༢)བསྒྱུར་བུག་གཟུགས(༡༩)བསྣན། །མདའ་རོས(༦༥)ཐོབ་པ་ཟླ་དག་གོ། །དེ་ཉིད་འཕར་ན་ལུགས་འདིར་བཤོལ། །འདིར་ཡང་བསྒྲུབ་ལུགས་མི་འདྲ་བ། །ཡོད་ཀྱང་ད་ལམ་དཀྱུས་ལྟར་དུ། །ཟླ་དག་གནས་གསུམ་སྟེང་རིམ་བཞིན། །བསེ་རུ(༡)ཟླ་མེ(༣༡)མཁའ་འབྱུང(༥༠)བསྒྱུར། །སྟེང་ནས་ནམ་མཁའ(༠)ཆུ་ཚོད་མིག(༢)། །ཕྱོགས(༡༠)ཀྱིས་ཕྲི་ལ་མཁའ་རོ(༦༠)གཉིས(༦༠)། །ཐུབ་པས(༧)དོར་ལྷག་གཟའ་དྷྲུའོ། །ཟླ་དག་གནས་གཉིས་སྟེང་མ་མིག(༢)། །འོག་མ་གཟུགས(༡)ཀྱིས་བསྒྱུར་བ་ལ། །རིལ་བུག(༩)ཆ་ཤས་རྩ་རི(༧༩)ཕྲི། །དུས་ཁྱིམ(༡༢༦)ཀླུ་ལག(༢༨)དོར་བའི་ལྷག །སྟེང་འོག་རིལ་ཆའི་དྷྲུ་བའོ། །ཟླ་དག་གནས་ལྔ་སྟེང་རིམ་བཞིན། །མིག(༢)ཕྱོགས(༡༠)ཀླུ་དབང(༥༨)ལག(༢)ཕྱོགས(༡༠)བསྒྱུར། །སྐར་གནས་དུས་མིག(༢༦)ཆུ་ཚོད་དུ། །མཚོ་དབང(༥༤)ཆུ་མིག(༢༤)ཆུ་སྲང་ཡིན། །དབུགས་མེ(༣)ཆ་ཤས་བུ་གས(༩)ཕྲི། །འདོད(༡༣)རོ(༦)མཁའ་རོ(༦༠)ཐིག་རོ(༦༠)དང་། །འཁོར་ལོས(༢༧)དོར་ལྷག་ཉི་དྷྲུའོ། །ཀུན་ལ་སྦྱང་བར་མ་ལྡངས་ན། །སོ་སོའི་འཁོར་ལོ་བསྣན་པར་བྱ། །སྔ་དྷྲུར་རང་རང་སྦྱར་བྱེད་བྱིན། །རིམ་བཞིན་ཕྱི་མའི་དྷྲུ་བ་འཆར། །དེ་ནས་ཉིན་རྩིས་བྱ་ར་ལ། །གཟའ་དྷྲུར་ཆུ་ཚོད་རྩ་དབང(༥༩)དང་། །སྲང་ཡོན(༣)དབུགས་ཆུ(༤)ཆ་ཐིག(༠)དང་། །ཉི་དྷྲུར་དབྱུག་མཚོ(༤)ཟླ་མིག(༢༡)སྲང་། །དབུགས་དབང(༥)ཆ་ཤས་བུ་ག(༩)རྣམས། །ཚེས་ཀྱིས་བསྒྱུར་རམ་རྟག་ལོངས་དག །སོ་སོར་བྱིན་པས་གཟའ་ཉི་གཉིས། །བར་པ་འགྲུབ་པོ་རིལ་ཆའི་དྷྲུར། །རིལ་པོར་ཚེས་བསྲེས་ཡིད(༡༤)ཀྱིས་བགོ། །ཐོབ་ནོར་ཟླ(༡)ཡོན(༣)མི་མཉམ་ཞིང་། །མཁའ(༠)ལག(༢)རིག་བྱེད(༤)མཉམ་པའོ། །ལྷག་མས་ཟླ་བའི་རྐང་པ་བཟུང་། །ལྔ་གསུམ(༥༥༥)བཞི(༤)དང་གསུམ(༣)གཉིས(༢)གཅིག(༡)། །ལྡོག་པ་དང་བཅས་བཅུ་བཞིའི(༡༤)གནས། །སྔ་ཕྱི་ཟོས་དང་མ་ཟོས་སྡོམ། །རྐང་བཟུང་སྔོན་མས་ཆ་ཤས་བསྒྱུར། །མ་ཟིན་རྐང་པ་དང་པོས་བསྒྱུར། །དུས་ཁྱིམ(༡༢༦)གྱིས་ཐོབ་ཡང་རྒྱུ་སྟེ། །ལྷག་མ་ཐིག་རོ(༦༠)དུས(༦)དང་ནི། །འདོད་པས(༡༣)བསྒྱུར་བ་ཆ་ཤས་ཏེ། །ལྷག་མེད་འདོད་ན་མ་ནུའོ(༡༤)། །རང་གི་ཡང་རྒྱུ་སྔ་རྐང་བསྣན། ། [p. 32, img. 42] ཕྱི་རྐང་རིམ་ཕྲི་གཟའ་བར་ལ། །ནོར་མཉམ་བསྣན་ལ་མི་མཉམ་ཕྲི། །ཉི་བར་གཅིག་ལ་སྐར་དྲུག(༦)དང་། །ཞེ་ལྔས(༤༥)སྦྱངས་ལ་མ་ལྡངས་ན། །འཁོར་ལོ(༢༧)བསྣན་ལ་འཁོར་བྱེད([two numbers stacked, not read])དོར། །ལྷག་མའི་སྐར་མ་ཐིག་རོས(༦༠)བསྒྱུར། །ཆུ་ཚོད་བཅུག་ལ་འབྱུང་མེ་ཟླས(༡༣༥)། །བགོས་ཐོབ་ནོར་གྱིས་ཉི་རྐང་བཟུང་། །རོ(༦)མཚོ(༤)བསེ་རུ(༡)སྔ་རྐང་སྟེ། །ལྡོག་པ་དང་བཅས་དུས(༦)ཀྱི་གནས། །སྔ་ཕྱི་ཟོས་དང་མ་ཟོས་སྡོམ། །རྐང་བཟུང་གདོང་མས་ལྷག་རྣམས་བསྒྱུར། །མ་ཟིན་རྐང་པ་དང་པོས་བསྒྱུར། །འབྱུང་མེ་ཟླས(༡༣༥)ཐོབ་ཡང་དག་རྒྱུ། །ལྷག་མ་ཐིག་རོ(༦༠)དུས(༦)འདོད་པ(༡༣)། །ལྷག་མེད་འདོད་ན་མཁའ་རི་མིག(༢༧༠)། །བསྒྱུར་ཐོབ(རང་རང་གི་ཆ་གོ་བའི་ཤུགས་ཀྱིས་འཇུག)སྔ་རྐང་བསྣན་པ་དང་། །ཕྱི་རྐང་ཕྲི་བའི་ལོངས་སྤྱོད་དེ། །གཟའ་ཉི་བར་པ་གཉིས་ཀ་ལ། །འཁོར་ཕྱེད་དོར་བསྣན་མ་དོར་ཕྲི། །དེ་ཡིས་གཟའ་དང་ཉི་མ་དག །ཉི་དག་གནས་གཉིས་གཅིག་ལ་ནི། །ཆུ་ཚོད་མཚོ་དབང(༥༤)ཚེས་བསྒྱུར་བྱིན། །གཟའ་ཡི་ལོངས་སྤྱོད་མན་གྱིས་སྦྱངས། །རེ་གྲོགས་ཟླ་བའི་སྐར་མ་ཡིན། །ཉི་ཟླ་གཉིས་བསྲེས་སྦྱོར་བ་འགྲུབ། །བྱེད་པ་ཚེས་གྲངས་མིག(༢)བསྒྱུར་ལ། །གཟུགས(༡)ཕྲི་ཐུབ་པས(༧)དོར་བ་དེ། །བསྡུས་རྒྱུད་ཡན་ལག་ལྔ་ག་ཚང་། །དེ་ཡང་བྱེད་པའི་སྐྱོན་བཏོན་པའི། །ཞལ་ལུང་ལུགས་བཞིན་རྣམ་དག་ཏུ། །འདོད་ན་གཟའ་ཉི་གཉིས་པོ་ཡི། །ཟླ་བའི་བསྒྱུར་གཞི་སོ་སོ་ལ། །མ་ནོར་བསྒྱུར་བར་དབྲི་བྱེད་དེ། །གཟའ་གནས་དུས(༦)དང་ཆུ་ཚོད་དུ། །རོ་མཚོ(༤༦)སྲང་ལ་དུས་རྒྱ་མཚོ(༤༦)། །དབུགས་འཁྲིག(༢)ཉི་མར་སྟོང་པ(༠)དང་། །ཆུ་ཚོད་ཀླུ་ལག(༢༨)སྲང་དུས་མཚོ(༤༦)། །དབུགས་ཟུང(༢)འདོད་ཆ་རིག་བྱེད(༤)རྣམས། །ཕྲི་ལ་གཞན་མ་གཞུང་ལྟར་བྱས། །རབ་ཉིའི་ཐོག་མའི་བྱེད་པ་ཡི། །རྩིས་འཕྲོ་སྐྱོན་བཏོན་ཡིན་ནའང་། །རིམ་བཞིན་སྐྱོན་ཆེར་འགྲོ་བ་སོགས། །ཞལ་ལུང་མ་བུར་གསུངས་པའི་ཕྱིར། །དཀྱུས་བཞིན་བྱ་ལས་ཧ་ཅང་གི །དགོས་དོན་ཆེ་བའི་གནད་མ་མཆིས། །

*Rāhu's head and tail by the true reckoning*, ch. 5, p. 34, l. 9–23
(img. 44):

> རབ་བྱུང་ཉི་མའི(༡༧)ཐོག་མ་ཡི། །ཟླ་བ་རྣམ་པར་དག་པ་ལ། །བུ་ག་ནམ་མཁའ་འཁྲིག་པ(༢༠༩)བྱིན། །མཁའ་མེས(༣༠)བསྒྱུར་ལ་གང་བརྩི་ཡི། །ཚེས་གྲངས་བསྲེས་ལ་གནས་ལྔ་བཞག །སྟེང་ནས་རིམ་བཞིན་ནམ་མཁའ(༠)དང་། །སྟོང་པ(༠)མ་ནུ(༡༤)དབུགས་ཐིག་ལེ(༠)། །ཆ་ཤས་ཉི་མས(༡༢)བསྒྱུར་བྱས་ལ། [numbers in the margin, not transcribed] །མེ་མིག(༢༣)རོ་དང(༦)སྲང་མཁའ་རོ(༦༠)། །ཐིག་མཚམས(༦༠)འཁོར་ལོས(༣)དོར་བའི་ལྷག །བསོད་ནམས་ལྡན་པའི་བུལ་བ་དག། །དེ་ཡིས་འཁོར་ལོར(༢༧)ཕྲི་བ་ཡི། །གདོང་གནས་སྐར་དང་ལོངས་སྤྱོད་ཤར། །དེ་ལ་འཁོར་ཕྱེད([two numbers stacked, not read])སྟེར་རམ་དམན། །མཇུག་གམ་དུས་མེ་རྣམ་པར་དག །ཡང་ན་ཟླ་བ་དག་པ་ལ། །བུ་ག་ཡོན་ཏན་མཚོ(༤༣༩)ལྡན་པ། །མིག(༢)བསྒྱུར་ཉ་གཟུགས(༡)སྟོང་མིག(༢)བྱིན། །མཁའ་དུས་ཆུ་བོས(༤༦༠)ཐོབ་པ་དོར། །ལྷག་མ་སྐར་མས(༢༧)བསྒྱུར་བ་ལ། །མཁའ་དུས་ཆུ་བོས(༤༦༠)བགོད་པ་སོགས། །བྱས་ཀྱང་བུལ་བ་གོང་བཞིན་འཆར། །ཁྱིམ་གནས་ཇི་ལྟར་བལྟ་བའི་ཕྱིར། །སྐར་གནས་མཁའ་རོས(༦༠)བསྒྱུར་བ་ལ། །ཆུ་ཚོད་བསྲེས་ལ་འབྱུང་མེ་ཟླས(༡༣༥)། །ཐོབ་པ་འདས་པའི་ཁྱིམ་ཡིན་ཞིང་། །ལྷག་རྣམས་དབང་རི་མདའ(༥༧༥)ཡིས་བསྒྱུར། །འབྱུང་མེ་ཟླས(༡༣༥)ཐོབ་འདས་པའི་ཞག །གདོང་མཇུག་གཉིས་ནི་མ་འོངས་ཞག །ཐོབ་པའི་ལྷག་དེ་མཁའ་རོས(༦༠)བསྒྱུར། །འབྱུང་མེ་ཟླས(༡༣༥)ཐོབ་ཆུ་ཚོད་ལ། །ཡང་ལྷག་མཁའ་རོས(༦༠)བསྒྱུར་ཐོབ་སྲང་། །སླར་ལྷག་དུས(༦)བསྒྱུར་ཐོབ་པ་དབུགས། །དེ་ལྷག་མེ་མིག(༢༣)གིས་བསྒྱུར་བར། །འབྱུང་མེ་ཟླས(༡༣༥)ཐོབ་ཆ་ཤས་ཏེ། །གདོང་མཇུག་དབང་རི་འབྱུང(༥༧༥)བར་སྦྱངས། །ལྷག་དེ་ཁྱིམ་དེར་འདས་ཞག་ཡིན། །

*The Lord's meeting, with the Kar myang reckoning of the rain*, ch. 5,
p. 36, l. 3–18 (img. 46; the chapter's colophon follows):

> ནཻ་པ་ལ་ཡི་མཁས་བཞེད་པའི། །ཀར་མྱང་རྩིས་ལ་བུག་མཁའ་དང་། །མི་བདག(༡༦༠༩)ལོ་ལ་རབ་ཉི་ནས། །གང་བརྩིའི་བར་གྱི་འདས་ལོ་བསྲེས། །དེ་ནི་ཤཱ་ཀའི་ལོ་ཞེས་གྲགས། །དེ་ཉིད་མེས(༣)བསྒྱུར་དྲང་སྲོང(༧)བགོ། །ཐོབ་ནོར་ལོགས་བཞག་ལྷག་མ་དེ། །ཆར་གྱི་དོན་དུ་མིག(༢)གིས་བསྒྱུར། །འབྱུང་བ(༥)བྱིན་ལ་ཆར་འབྲས་ཡིན། །སྔོན་གྱི་ཐོབ་ནོར་མེ(༣)ཡིས་བསྒྱུར། །རིས(༧)བགོད་ལ་སོགས་ལན་བརྒྱད་བྱ། །འབྲུ་རྩྭ་བསིལ་དྲོད་རླུང་འཕེལ་ཟད། །འཁྲུག་རྣམས་རིམ་པ་བཞིན་དུ་ཤེས། །ཉུང་མཐའ་འབྱུང་བ(༥)མང་མཐའ་ལ། །རི་ཟླ(༡༧)འབྲིང་པོ་ཉི་མ(༡༢)ཡིན། །སོ་སོར་མང་ཉུང་འབྲས་བུ་བྱིན། །ཡང་ནི་ཤཱ་ཀའི་འདས་ལོ་དེ། །དབང་ཕྱུག(༡༡)སྦྱངས་ལ་ལྷག་མ་དེ། །མཚོ(༤)ཡིས་དོར་ལྷག་གཟུགས(༡)ཤར་ན། །རྫིང་བུར་འཇུག་ཅེས་ཐན་པ་ཆེ། །གཉིས་པ(༢)ཡན་ལག་འཇུག་ཅེས་པ། །ཡུལ་གྲུ་ལ་ལར་ཆར་འབབ་ཅིང་། །ལ་ལར་མི་འབབ་འབྲུ་ཚོགས་ཉམས། །མེ(༣)ཤར་ཀུན་འཇུག་ཅེས་ཟེར་ཏེ། །ཡུལ་ཀུན་ཁྱབ་པར་སྦྲང་ཆར་འབབ། །ཐིག(༠)ལ་ཞོད་ཅེས་ཆུ་བོ་རྒྱས། །དབང་ཕྱུག་མཇལ་ཕྲད་ཅེས་བགྱི་བ། །བྱེད་པ་ལོ་བདག་གཟའ་དབྱུག་གཉིས། །གནས་ནི་བཅུ་གཉིས་བཀོད་བྱས་ཏེ། །གཟའ་ལ་ཟླ་བ(༡)རི་དབང(༥༧)དབྱུག །ལུག་ཁྱིམ་དོན་དུ་བྱིན་པར་བྱ། །ཆུ(༤)དང་མེ་དབང(༥༣)གླང་ལ་བྱིན། །རི་བོང(༡)རི་གཟུགས(༡༧)འཁྲིག་པ་སྟེ། །རྒྱ་མཚོ(༤)མེ་མདའ(༥༣)ཀརྐ་ཊ། །ཟླ་བ(༡)མིག་བགྲོད(༢༢)སེང་གེ་ལ། །ཆུ་གཏེར(༤)རྒྱལ་བ(༢༤)བུ་མོ་སྟེ། །དུས(༦)དང་གཟུགས་དབང(༥༡)སྲང་གི་དོན། །ཟླ་བ(༡)རི་མཚོ(༤༧)སྡིག་པ་ལ། །མེ(༣)དང་རྒྱལ་པོ(༡༦)གཞུ་ལ་སྟེ། །མཚོ(༤)དང་གཟའ་ཡོན(༣༧)ཆུ་སྲིན་ཡིན། །དུས(༦)དང་རྒྱ་མཚོ(༤)བུམ་པ་ལ། །ནམ་མཁའ(༠)མིག་འབྱུང(༥༢)ཉ་ལ་བསྲེ། །སོ་སོར་མ་ནོར་བསྣན་པ་ལ། །མཁའ་རོ(༦༠)ཐུབ་པས(༧)བསྒྲིལ་བའི་ལྷག །གང་ཤར་ཁྱིམ་གཟའ་བཅུ་གཉིས་ཡིན། །

*The lunar eclipse, first section: seized or not*, ch. 9, p. 59, l. 6–13
(img. 69):

> དང་པོ་འཛིན་དང་མི་འཛིན་ནི། །བསྡུས་པའི་རྒྱུད་ཀྱི་ཉི་དག་ལ། །འདོད་པ(༡༣)མཁའ་མེ(༣༠)བྱིན་པ་དེ། །ཚེས་འཁྱུད་ཟླ་སྐར་ཞེས་གྲགས་དང་། །སྒྲ་གཅན་གདོང་མཇུག་གང་ཉེ་གཉིས། །སྐར་མ་གཅིག་ལ་གནས་པའམ། །གཅིག་ཁྱད་འདུག་ཀྱང་བརྟག་པར་བྱ། །ཉུང་གིས་མང་སྦྱངས་ལྷག་མ་དེ། །སྒྲ་གཅན་ཟླ་བའི་བར་ཁྱད་ཡིན། །ཟླ་བར་གདོང་སྦྱངས་ལྷག་མ་རུ། །རི་དབང(༥༧)མན་ཆད་འཛིན་པ་དེ། །གདོང་ལ་ཟླ་བས་སྦྱངས་པ་དང་། །དུས་མེ་ཟླ་བས་སྦྱངས་པ་ལ། །མཁའ་འབྱུང(༥༠)མན་ཤར་འཛིན་པར་ངེས། །ཟླ་བ་དུས་མེས་སྦྱངས་པའི་ཚེ། །འབྱུང་མཚོ(༤༥)མན་ཆད་འཛིན་པ་ཡིན། །སྤྱིར་ནི་ཆུ་ཚོད་ཉུང་བཞེད་ཀྱང་། །གཉིས་ཀ་སྟོངས་ན་འཛིན་མི་འགྱུར། །ཟླ་འཛིན་ཆུ་ཚོད་མང་བཞེད་ཀྱང་། །གདོང་འཛིན་ཀླུ་དབང(༥༨)ལྷག་པ་དང་། །མཇུག་ལ་འབྱུང་མདའ(༥༥)བསྒྲལ་བ་ན། །མཐོང་བར་གྱུར་པ་མ་ཡིན་ནོ། །

*The merit multiplied*, ch. 9, p. 62, l. 25, to p. 63, l. 2 (img. 72–73):

> དགུ་པ་དགེ་བའི་འགྱུར་ཁྱད་ནི། །དུས་བཟང་དཀྱུས་རྣམས་བརྒྱ་འགྱུར་ལ། །ཟླ་འཛིན་བྱེ་བ་བདུན་འགྱུར་དང་། །ཉི་འཛིན་བྱེ་བ་འབུམ་འགྱུར་དུ། །རྒྱལ་བས་གསུང་རབ་མཐའ་ཀླས་པར། །རྒྱས་པར་གསུངས་བཞིན་དོན་མཐུན་ཅིང་། །ཁྱད་པར་ཉི་ཟླའི་མདོ་ལ་སོགས། །དྲང་དོན་གསུང་རབ་རྣམས་ནས་གསུངས། །ངེས་དོན་བཅོམ་ལྡན་དུས་འཁོར་ལོར། །ཉི་འཛིན་རོ་རྐྱང་རླུང་གཉིས་དང་། །ཟླ་འཛིན་རྐྱང་མ་དྷཱུ་ཏཱི་རུ། །ཚུད་དུས་བསྐྱེད་རྫོགས་བདག་འཇུག་སོགས། །རྣམ་ [p. 63, img. 73] དཀར་འཕེལ་འགྱུར་འགྱུར་ཁྱད་ཀྱང་། །དེ་དབང་ཡིན་པ་དོན་དམ་གསུངས། །དེ་མཚུངས་སྐབས་འདིར་སྡིག་ལས་ཀྱང་། །དེ་བཞིན་འཕེལ་བར་བཀའ་བསྟན་བཅོས། །ཁུངས་མ་རྣམས་ནས་བཤད་པ་ཡིན།།

*The solar eclipse, first section*, ch. 9, p. 64, l. 21, to p. 65, l. 19
(img. 74–75):

> རྟ་བདུན་དབང་པོར་སྒྲ་གཅན་ཉིད། །འཇུག་ཚུལ་རི་མོ་ཅུང་དཀའ་ཞིང་། །ལུས་འཕགས་གླིང་དང་བ་ལང་སྤྱོད། །ཉིན་མཚན་མཉམ་ཚེ་ལྷོ་གླིང་ལ། །མི་བསླུ་གུང་ལ་འཛིན་ན་བསླུ། །དེ་ཡི་རྒྱུ་མཚན་བགྲོད་པ་གཉིས། །ཡོད་པས་རྟ་བདུན་སྟོབས་ལྡན་ཟུང་། །གོ་ལའི་ལམ་པོ་འཇོལ་བ་ཡིན། །ལྷོ་གླིང་སྟོན་དཔྱིད་ཉིན་མཚན་མཉམ། །གུང་ལ་འཛིན་པའི་རི་མོ་ཤར། །ཐེ་ཚོམ་མེད་མོད་ལྔ་དགོངས་བསླུ། །ཤར་ནུབ་གཉིས་ལ་བགྲོད་གཉིས་ཡོད། །མིག་སྨན་མདོག་ཅན་རྟ་བདུན་གཉིས། །མཉམ་གཉིས་ལམ་དུ་འགྲིག་པ་ཡིན། །སུཀྲར་མཐོ་དམའ་སྣ་ཚོགས་ཡོད། །སྒྲ་གཅན་རྟག་ཏུ་མཐོ་དམའ་མེད། །ཉི་མ་ལྷོ་བགྲོད་ཀརྐ་ཊའི་ཁྱིམ། །འཁྱོག་འགྲོས་དང་པོའི་ཉིན་ཞག་ནི། །གནམ་སྟོང་འགྲིག་ན་མི་འཛིན་བཞེད། །གཞན་ཡང་ལྷག་པ་པ་སངས་གཉིས། །གང་རུང་ཅིག་དང་སྐར་མ་གཅིག །ཆུ་ཚོད་འབྱུང་མེ་ཟླ(༡༣༥)ཚུན་དུ། །ལྷན་ཅིག་གནས་ཚེ་བཟུང་མི་སྲིད། །དང་པོ་འཛིན་ནམ་མི་འཛིན་ལ། །འདི་ལ་གྲུབ་རྩིས་ལེ་ཚན་དང་། །ཅུང་ཟད་བསྟུན་ཙམ་དགོས་པའི་ [p. 65, img. 75] ཕྱིར། །གྲུབ་རྩིས་བྱང་བགྲོད་སྐར་དུས(༦)དང་། །ཆུ་ཚོད་ཞེ་ལྔས(༤༥)སྦྱངས་པ་དང་། །ལྷོ་བགྲོད་འབྱུང་བ(༥)མེ་མིག(༢༣)དོར། །ཞལ་ལུང་སྐྱེས་ཁྱིམ་མདའ(༥)དུས(༦)སྦྱངས། །གཉིས་དང་བསྡུས་པའི་རྒྱུད་དང་བཅས། །གསུམ་གའི་ཉི་མ་དག་པ་དང་། །སྒྲ་གཅན་གདོང་མཇུག་གང་ཉེ་གཉིས། །སྐར་མ་གཅིག་ལ་བབས་པ་འམ། །གཅིག་ཁྱད་འདུག་ཀྱང་ཉུང་གིས་མང་། །སྦྱངས་པའི་ལྷག་མ་བརྟག་གཞི་སྟེ། །གདོང་གིས་ཉི་སྦྱངས་སྐར་གནས་སྟོངས། །གོ་མ་ལོག་པའི་རི་མོ་སྟེ། །ཆུ་ཚོད་མིག་དབང(༥༢)བར་དུ་འཛིན། །ཉི་མས་གདོང་སྦྱངས་གདོང་པ་ཐུང་། །གོ་ལྡོག་ཅེས་གྲགས་ཆུ་ཚོད་མདའ(༥)། །ལྷག་ན་འཛིན་པ་མ་ཡིན་ནོ། །དུས་མེས་ཉི་སྦྱངས་མཇུག་མ་རིང་། །དེ་ཡང་གོ་ལྡོག་ཅེས་གྲགས་པས། །སྐར་ཐིག(༠)ཆུ་ཚོད་རི(༧)ཙམ་འཛིན།། ཉི་མས་དུས་མེ་སྦྱངས་པ་ན། །མ་ལྡོག་མཁའ་ཆུའི(༤༠)ནང་ཚུན་འཛིན། །ཁྱད་པར་བསྡུས་རྒྱུད་ཉི་འཛིན་མེད། །གྲུབ་རྩིས་ལེ་ཚན་འཛིན་འགྱུར་ཡང་། །ཀུན་ལ་མི་འཛིན་ནུབ་སྐར་སྤྱོད། །བརྟག་དཔྱད་གཞན་རྣམས་ཀུན་མཐུན་ན། །ཆ་ཆུང་འཛིན་པར་སྲིད་པ་ཙམ། །བྱེད་རྩིས་འཛིན་ལ་གྲུབ་རྩིས་ནས། །སྐར་མ་སྟོངས་ཙམ་མི་འཛིན་ཀྱང་། །འཁྲུལ་མེད་བསྡུས་རྒྱུད་དགོངས་དོན་གཙོ། །ཁྱད་པར་ཤར་སྐར་བདུན་སྟེང་ལ། །ཉི་མ་གནས་ན་གྲུབ་རྩིས་ཀྱི། །སྐར་གནས་ལུས་ཀྱང་བྱེད་པ་གཙོ། །བྱེད་པའི་ཆུ་སྲང་མན་མ་གཏོགས། །སྟོངས་ན་ཉི་འཛིན་མི་འབྱུང་ངོ་། །ཉི་རྐང་གཟུགས(༡)ལ་སྤྱོད་པའི་ཚེ། །མི་བསླུ་འཛིན་པའི་རི་མོ་སྟེ། །མཚོ(༤)ལ་སྣ་ཚོགས་འོང་བ་དང་། །དུས(༦)ལ་འཛིན་པར་དཀའ་བ་ཡིན། །ཁྱད་པར་བྱེད་པ་གོ་ལྡོག་ན། །འཛིན་མིན་མ་ལྡོག་བརྟག་དཔྱད་ཀུན། །མཐུན་ན་འཛིན་པར་སྲིད་པ་ཡིན། །གོ་ལྡོག་འཛིན་དང་མི་འཛིན་སོགས། །གོང་གི་སོ་སོའི་སྦྱངས་ལྷག་གཙོ། །བྱེད་གྲུབ་ཁྱད་རྣམས་གོང་སྨྲས་ཀྱི། །ཁྱད་པར་ཙམ་ལས་བྱེད་རང་གཙོ། །དབྱར་དགུན་ཉི་ལྡོག་ཉེར་དགུ་དང་། །གནམ་སྟོང་ཚེས་གཅིག་ཡོད་པ་ཡི། །སྟོང་དེར་ཉི་འཛིན་མི་འབྱུང་མོད། །ཉི་མ་འཁྲིག་གཞུའི་ཁྱིམ་ན་གནས། །གཡོ་བས་འཛིན་དཀའ་ཆ་རེས་ཆུང་། །ཀརྐ་ཊ་དང་ཆུ་སྲིན་ལ། །ཆ་ཕྱེད་ཙམ་གྱིས་ཆུང་བར་སྣང་། །ཏནྟྲ་ལ་ཡིན་ཀྱང་ཅུང་ཟད་ཆུང་། །བྱེད་རྩིས་ཆད་ཀྱང་ཚེས་གཅིག་འཛིན། །གྲུབ་པའི་གནམ་སྟོང་དུས་ཉེའི་ཚེས། །ཆད་ཚེ་འཛིན་པར་སྲིད་པ་མིན།།

In the solar section Yigdzin-1's «སླུ་རླར» is the print's «སུཀྲར» (to Venus),
«ཀཊའི» is «ཀརྐ་ཊའི», and p. 65's «རི་ཙུན» is «རི(༧)ཙམ», as the quotation above
has it.

## The month's earth lords as the almanac writes them (RD2)

Read 2026-10-11 for ROADMAP RD2: vol. 1, p. 176 (img. 186), and the
twelve month heads of the model almanac (pp. 154–171, img. 164–181),
with Yigdzin-1 on 3× crops of each head and every line quoted here read
on the scan; the courses on vol. 1, p. 135 (img. 145), and vol. 2,
pp. 211–213 (img. 219–221), read the same way, Yigdzin-1 on a 3× crop
below the woodcut of img. 219, where it reads nothing on the whole page.
The year's front matter is in [almanac-page.md](almanac-page.md), *The
year's earth lords in the front matter*.

**What p. 176 asks.** «དེ་ནས་རང་རང་ཟླ་བའི་འགོར། །ཟླ་བའི་འཁོར་ལོ་དེ་ཡང་ནི། །སིལ་མའི་ས་བདག་ཁྲ་མ་རྣམས། །མི་ནོར་མཚན་མས་འདྲི་བའམ། །མ་ཕྱུར་རུང་ཙམ་དེ་རྗེས་སུ། །ཟླ་བཤད་…»
At the head of each month, the month's wheel and the charts of the
scattered earth lords (*sil ma'i sa bdag*), drawn without error with
their marks, «འམ», or «མ་ཕྱུར་རུང་ཙམ», and then the month's description.
*phyur* is not settled; the year's front matter has the same turn, «མ་ཕྱུར་གྱུར་ཀྱང་འགལ་མ་མེད», "even
if *ma phyur*, no fault", so the charts may be left out or cut down, and
the model almanac draws none.

**What the model almanac writes.** No chart: each month head names the
month's earth lords in its run of entries, after the sun's sign and the
work the month favours, always in the same order, «ཟླ་བདག་ལྟོས། བུམ་སྟོང་…» and,
near the end, *lha lnga* and *lha dgu* with their directions. All
twelve, on the scan:

| Hor month (animal) | img. | the head's sign | month's lord | *bum stong* | *lha lnga*, *lha dgu* |
| --- | --- | --- | --- | --- | --- |
| 11 (tiger) | 164 | གཞུ, Sagittarius | «ཟླ་བདག་ལྟོས» | «བུམ་སྟོང་ཤར», east | «ལྷ་ལྔ་ཤར། དགུ་ནུབ», five east, nine west |
| 12 (hare) | 165 | ཆུ་སྲིན, Capricorn | «ཟླ་བདག་བལྟ» | «བུམ་སྟོང་ལྷོ», south | «ལྔ་དགུ་ལྷོ», both south |
| 1 (dragon) | 167 | བུམ་པ, Aquarius | «ཟླ་བདག་ལྟོས» | «བུམ་སྟོང་ནུབ», west | «ལྔ་ནུབ། དགུ་ཤར» |
| 2 (snake) | 168 | ཉ, Pisces | «ཟླ་བདག་བལྟ» | «བུམ་སྟོང་བྱང་», north | «ལྔ་དགུ་བྱང་» |
| 3 (horse) | 170 | ལུག, Aries | «ཟླ་བདག་ལྟོས། འཇམ་རྩུབ་ཀྱི་འབྲས་བུ་བརྗོད» | «བུམ་སྟོང་ཤར» | «ལྔ་ཤར་དགུ་ནུབ» |
| 4 (sheep) | 172 | གླང, Taurus | «ཟླ་བདག་ལྟོས» | «བུམ་སྟོང་ལྷོ» | «ལྔ་དགུ་ལྷོ» |
| 5 (monkey) | 173 | འཁྲིག་པ, Gemini | «ཟླ་བདག་ལྟོས» | «བུམ་སྟོང་ནུབ» | «ལྔ་ནུབ། དགུ་ཤར» |
| 6 (bird) | 175 | ཀརྐ་ཊ, Cancer | «ཟླ་བདག་བལྟ» | «བུམ་སྟོང་བྱང་» | «ལྔ་དགུ་བྱང་» |
| 7 (dog) | 176–177 | སེང་གེ, Leo | «ཟླ་བདག་ལྟོས» | «བུམ་སྟོང་ཤར» | «ལྔ་ཤར། དགུ་ནུབ» |
| 8 (pig) | 178 | བུ་མོ, Virgo | «ཟླ་བདག་ལྟོས» | «བུམ་སྟོང་ལྷོ» | «ལྔ་དགུ་ལྷོ» |
| 9 (mouse) | 180 | སྲང, Libra | «ཟླ་བདག་ལྟོས» | «བུམ་སྟོང་ནུབ» | «ལྷ་ལྔ་ནུབ། དགུ་ཤར» |
| 10 (ox) | 181 | སྡིག་པ, Scorpio | «ཟླ་བདག་ལྟོས» | «བུམ་སྟོང་བྱང་» | «ལྔ་དགུ་བྱང་» |

The full form «ལྷ་ལྔ་» stands on img. 164 and 180 (read at 8×); the
others shorten it to «ལྔ་».

- **The month's lord** («ཟླ་བདག») is no earth lord: it is p. 176's lord of
  the month, the weekday of the true reckoning's 1st, from which the
  weekday's rise is counted (*The weekday's rise*, above). The 3rd month
  adds "tell the results, mild and harsh".
- ***bum stong***, the empty vase. Vol. 2, p. 212–213 (img. 220–221), by
  the month's animal, read on the scan (the lines at «[…]», where the five
  gods sit in the vase, are written out whole in
  [year-month-earth-lords.md](year-month-earth-lords.md), *The empty vase*;
  the head reads «ད་ནི» on BDRC's scan and the Gser thang reprint's, where
  the quotation had «དེ་ནི»):
  «ད་ནི་བུམ་སྟོང་ཞེས་བྱ་བ། །གཙུག་ལག་འབྱུང་བའི་རྩིས་ལུགས་ལས། །སྟག་རྟ་ཁྱི་གསུམ་བུམ་སྟོང་ཤར། །ཕག་ལུག་ཡོས་ལ་ལྷོ་ཡི་ཕྱོགས། །བྱི་འབྲུག་སྤྲེལ་ཟླར་ནུབ་ཀྱི་ཕྱོགས། །བྱ་གླང་སྦྲུལ་ཟླར་བྱང་ཕྱོགས་གནས། །དེ་ཡང་བུམ་སྟོང་རྒྱུ་མཚན་ནི། །བུམ་པ་དེ་ལ་ལྷ་ལྔ་གནས། […] ལས་དང་བྱ་བ་ཕལ་ཆེར་ངན། །སྒྲིབ་ན་འཇམ་དཔལ་སྐུ་བསྟན་ཅིང་། །སྣང་བརྒྱད་བཏོན་པས་ཉེས་པ་ཞི།»
  In the months of the tiger, horse and dog it is in the east, of the
  pig, sheep and hare in the south, of the mouse, dragon and monkey in
  the west, of the bird, ox and snake in the north; the vase holds the
  five gods; most works are bad (in its direction); the remedy is
  Mañjuśrī's image shown and the *snang brgyad* recited. Vol. 1, p. 135
  (img. 145), in ch. 13's charts, by the sun's sign:
  «ཤར་ནས་རིམ་པར་ཁྱིམ་བཅུ་གཉིས། །བུམ་པ་བཞི་ལ་རིམ་པ་རུ། །བཀོད་པས་ཉི་མ་གང་གནས་པ། །བུམ་སྟོང་གནས་པའི་ས་ཕྱོགས་ཡིན། །ཚེས་གྲངས་སོ་སོའི་ཨངྒི་དག །བཀོད་པའི་ཕྱོགས་ལ་སྡེ་བརྒྱད་ཐེབས།»:
  the twelve signs set in turn on four vases from the east (Aries east,
  Taurus south, Gemini west, Cancer north, Leo east …); the direction
  where the sun is, is *bum stong*'s; and on the direction where each
  date's number is set, the eight classes strike (built, T2.13). Vol. 2
  gives the same second way on p. 213, «ལེགས་ལྡན་གཙུག་ན་ཟླ་བའི་ལུགས», Aries, Leo and
  Sagittarius in the east. With the head's sign, both ways give the
  same twelve directions, and the model almanac's twelve fit both. They
  part within a month: the sun enters the head's sign during it (ch. 15,
  [almanac-page.md](almanac-page.md), *The sun's terms*: the 11th month's
  sign entry falls between its *dbugs thob* and its *sgang*), so by
  p. 135 *bum stong* would move at the sign entry, where the model
  almanac writes one direction for the month. Which the app follows is
  for T2.26.
- ***lha lnga* and *lha dgu***, the five gods and the nine. Vol. 2,
  p. 211 (img. 219), read on the scan (the editors' bracketed numbers
  left out; the gaps closed in O10 from the Gser thang reprint, I3CN5029,
  img. 217; what follows the last «…» is in
  [year-month-earth-lords.md](year-month-earth-lords.md), 51–64):
  «རིན་ཆེན་གསལ་བའི་སྒྲོན་མེ་ལས། །ཟླ་འཁོར་ས་བདག་ཆེན་པོ་དང་། །གྲོལ་བུའི་ལྷ་ལྔ་ཞེས་བྱ་བ། །ཤར་ཕྱོགས་རྩེ་ལུང་འབྲུག་སྔོན་མགོ །ལྷོ་ཕྱོགས་ཆུ་ཚགས་བྱ་དམར་མགོ །ནུབ་ཕྱོགས་འབེ་འུ་སྟག་དཀར་མགོ །བྱང་ཕྱོགས་ཧན་བུ་སྦལ་ནག་མགོ །དབུས་མཚམས་ས་བདག་སྦལ་མགོ་སེར། །གཏོར་ཤེས་ལ་སོགས་འགའ་ཞིག་ཏུ། །དབུས་ཕྱོགས་ས་ཡི་ལྷ་མོ་ནི། །ན་ཆུང་གཞོན་མའི་གཟུགས་ཅན་ལ། །པདྨའི་སྟེང་ན་རྨ་བྱ་བརྩིགས། །བདུད་རྩིའི་བུམ་པ་བསྣམས་ཏེ་བཞུགས། །ཞེས་ཀྱང་བཤད་མོད་སོ་སོ་མིན། །གཡོན་སྐོར་སྟག་རྟ་ཁྱི་ཟླ་ལ། །ཤར་འཚོག་ཕག་ལུག་ཡོས་ཟླ་ལྷོར། །བྱི་འབྲུག་སྤྲེལ་ནུབ་བྱ་གླང་སྦྲུལ། །བྱང་གི་ཕྱོགས་སུ་ལྷ་ལྔ་འཚོག །ལས་ཐབས་རོ་དང་ཆུང་དུར་དང་། །མཁར་ལས་ཁྲོམ་ཤིད་ལ་སོགས་པ། །ཕྱི་ནང་བྱ་བ་ལྟད་སྟོན་འཇལ། །དམག་ཇག་མཚོན་སྟོན་སྐྱས་བག་འཛེམ། …»
  and «ཡང་ནི་ལྷ་དགུ་ཞེས་བྱ་བ། །ཞག་ལྷ་དུས་ལྷ་ཁྱིམ་ནང་ལྷ། །ཐབ་ལྷ་ཤིད་ལྷ་སེ་ཡི་ལྷ། །རོ་ལས་ལྷ་དང་སྤྱི་ཡི་ལྷ། །གཏད་ལྷ་དགུ་པོ་འཚོག་པ་ནི། །སྟག་རྟ་ཁྱི་ཡི་ཟླ་བར་ནུབ། །ཕག་ལུག་ཡོས་ཀྱི་ཟླ་བར་ལྷོ། །བྱི་འབྲུག་སྤྲེལ་གསུམ་ཟླ་བར་ཤར། །བྱ་གླང་སྦྲུལ་ཟླར་བྱང་གི་ཕྱོགས། །འཚོག་སར་རོ་གཏོང་དུར་འདེབས་དང་། །མཁར་ལས་ཁྲོམ་ཤིད་ལ་སོགས་པ། །ཕྱི་ནང་བྱ་བ་ལྟད་སྟོན་འཇལ། །དམག་སོགས་ལས་ཀུན་འཛེམ་པར་བྱ།»
  From the *Rin chen gsal ba'i sgron me*: the great earth lord of the
  month's wheel, called the five gods of *grol bu*, each with an
  animal's head (blue
  dragon east, red bird south, white tiger west, black frog north,
  yellow frog in the centre), gather, counted to the left, in the east in
  the months of the tiger, horse and dog, the south in those of the pig,
  sheep and hare, the west in those of the mouse, dragon and monkey, the
  north in those of the bird, ox and snake. The nine gods (of the day,
  the time, the house, the hearth, funeral rites, the *se*, corpses, the
  general and the *gtad*) gather in the west, south, east and north for
  the same groups: opposite the five on the east–west line, with them on
  the north–south. Where they gather, building, markets, funeral rites,
  visits, shows and gatherings, and, for the five, war, theft, weapons,
  moving house and a bride; for the nine, sending out a corpse, burial
  and all works, war first, are avoided. The remedy for both, «ལྔ་དགུ་གཉིས་ཀ་སྒྲིབ་འདོད་ན། །སྤྱང་ཀིའི་ཐོད་པ་ཕྱོགས་དེར་བསྟན།» (a wolf's
  skull shown in that direction, a ransom with a dough figure, pp. 211–212),
  is for RD4. The model almanac's twelve months are the rule's: the
  heads write "five and nine south" or "north" where the two meet, and
  the two directions apart where they do not.

So the model almanac gives a vector for each course, twelve months each,
that two rules of WB confirm. The month's other earth lords of ch. 31
(the 64 bad directions, the month's *hal khyi*, *lto 'phye*, the
month's *nag mo dgu skor*, the scattered ones) are not in the heads;
the charts p. 176 asks for are drawn in ch. 13 ([wb-contents.md](wb-contents.md), ch. 13).

**The heads' dated entries.** Each head also gives a date of the month
with works to avoid, in the same run, read on the scan. None is built.
Their rule is not in the head, and the etext of both volumes has none of
these phrases outside the model almanac (searched 2026-10-11:
«ཆུ་བདུད་རྒྱལ་པོ», «དགྲར་ལྡང», «རྗེ་རིགས», «ཁ་རུལ», «ཟོར་འཕེན་ཟླ», «ཁྱི་གསར་མི་གསོ»):

| Hor month | img. | Printed | Read |
| --- | --- | --- | --- |
| 11 | 164 | «བདུན་ལ་དུས་ཚོད་ངན་པ་དགུ་འཛོམ། ཁ་སྨྲས་གླེང་གཞིའི་ཟས་མི་ཟ། རྒྱལ་བའི་བཀའ་གསུང་མི་སྒྲོག» | on the 7th nine bad times meet: no feasting that gives rise to talk, no proclaiming the Victor's word |
| 12 | 165 | «བཅུ་བཞི་ལ་དམག་མི་དྲང་། ཡུལ་དང་ས་ཞིང་མི་འཚོང་།» | on the 14th no leading an army, no selling land or fields |
| 1 | 167 | «ཉེར་གཅིག་ལ་ཆུ་བདུད་རྒྱལ་པོ་དགྲར་ལྡང་། འབྱུང་བ་ཆུའི་ལས་མི་བྱེད། ཉེར་ལྔར་ཀླུ་ཐེབས། གཉིས་དང་བརྒྱད་མ་གཏོགས་ཟླ་བ་རིལ་པོར་ཀླུ་བཟློག» | on the 21st the king of the water demons rises as an enemy: no work with water; the nāgas strike on the 25th and are turned the whole month but the 2nd and 8th |
| 2 | 168 | «བརྒྱད་ལ་མེ་ལྷ་ཨགྣི་གདན་འདིང་བའི་དུས། རོ་བསྲེག་པ་དང་ནད་པའི་སེལ་སྒོ་འབྱེད་སར་མི་འགྲོ། བརྒྱད་ལ་རྗེ་རིགས། བཅོ་ལྔ་ལ་རྒྱལ་རིགས་དང་དམངས་རིགས་ལྡང་།» | the 8th is when Agni's seat is spread: no going to a cremation or where a sick person's purification is opened; the lords' caste rises on the 8th, the royal and common castes on the 15th |
| 3 | 170 | «བཅུ་དྲུག་ལ་བྱང་དུ་རྟ་མི་རྒྱུག །ལྷོར་བཀའ་མི་སྒྲོག ཤར་དུ་དོན་མི་གཉེར། ནུབ་ཏུ་གཡུ་མི་ཉོ།» | on the 16th no racing horses to the north, no proclaiming orders to the south, no business to the east, no buying turquoise to the west |
| 4 | 172 | «ཉེར་བཞི་ལ་རིན་པོ་ཆེའི་ལས་མི་བྱེད། བྱང་དུ་རྟ་མི་རྒྱུག །དགུ་མ་གཏོགས་ཟླ་བ་རིལ་པོར་སྡེ་བརྒྱད་བཟློག་པའི་ཐེབས་མེད་པས་སྔགས་པ་ཁ་རུལ་གྱི་ཟླ་བའང་ཟེར།» | on the 24th no work with jewels, no racing horses to the north; the whole month but the 9th has no strike for turning the eight classes back, so it is called the mantrin's rotten-mouthed month |
| 5 | 173 | «དགུ་ལ་དམག་མི་དྲང་། ཡུལ་དང་ས་ཞིང་མི་འཚོང་།» | on the 9th as the 12th month's 14th |
| 6 | 175 | «བཅོ་བརྒྱད་ལ་གཤིན་ལས་ཀྱི་ཡོས་མི་བརྗོད། རྟ་བཤའ་བ་དང་། བཤན་པ་བོན་པོའི་ཁྱིམ་དུ་མི་འགྲོ།» | on the 18th the funeral rites' *yos* is not spoken (sense not settled); no slaughtering horses, no going to a butcher's or a Bonpo's house |
| 7 | 176–177 | «ཉེར་བདུན་ལ་ཁྱི་གསར་མི་གསོ། ཡོ་ལང་མི་བྱེད།» | on the 27th no taking in a new dog, no *yo lang* |
| 8 | 178 | «བཅུ་ལ་ས་བདག་རྒྱལ་པོ་དགྲར་ལྡང་། རྒྱལ་པོ་ལ་བསྙེན་བཀུར་མི་བྱ། གདན་མི་འདྲེན། བརྒྱད་ལ་དམངས་རིགས། བཅོ་བརྒྱད་ལ་རྒྱལ་རིགས། ཉེར་བརྒྱད་ལ་རྗེ་རིགས་རྣམས་ཉལ།» | on the 10th the king of the earth lords rises as an enemy: no serving a king, no inviting a guest; the common caste lies down on the 8th, the royal on the 18th, the lords' on the 28th |
| 9 | 180 | «ཉི་ཤུ་ལ་ཆུ་བདུད་རྒྱལ་པོ་དགྲར་ལྡང་། འབྱུང་བ་ཆུའི་ལས་མི་བྱེད། ཆུ་ཆེན་མི་བརྒལ། ནོར་ཕྱུགས་ཕྱིར་མི་གཏོང་།» | on the 20th the king of the water demons rises as an enemy: no work with water, no crossing a great river, no sending out wealth or livestock |
| 10 | 181 | «གནམ་གང་ལ་དགའ་གཞས་རྩེད་མོ་མི་བྱེད། ཉེར་བདུན་ལ་སྡེ་བརྒྱད་བཟློག་པ་ལས་ཟླ་བ་རིལ་པོར་ཐེབས་ཆེ་བས་སྔགས་པ་ཟོར་འཕེན་ཟླ་བ་ཟེར།» | on the 30th no songs of joy or games; turning the eight classes back on the 27th, the strike is great the whole month, so it is called the mantrin's month of casting the *zor* |

The 11th head also has, at the winter solstice, «ས་བདག་སྐྱས་བྱེད་བོད་ན་དགུ་ཡང་འདྲེན»
(read at 3×; the etext's «སྐྱེས» is a misprint): the earth lords move
house, and "in Tibet the nine too are drawn"; which nine is open
question 20.
