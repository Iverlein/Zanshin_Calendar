# The kyūreki gaps

The three 旧暦 questions set on 2026-10-03 (the 2033 leap month, the
personal 三箇の悪日, the Gregorian-dated festivals), with what was found on
2026-10-03. Facts only, in our own words (SPEC §8); the Japanese is quoted
where the finding rests on its wording.

**In the app since 2026-10-03:** `Kyureki.kt` cites the 2033 statement (SPEC
§7.1); the three personal bad days cite the 簠簋内伝 scan and koyomi8
(`Rekichu.personalDays`, SPEC §7.5, and their readings); O-Bon shows on
15 August, labelled by the Gregorian date, from Japanese Wikipedia お盆
(SPEC §7.3).

## The 2033 leap month (SPEC §7.1 rule 5)

**Settled.** The 一般社団法人日本カレンダー暦文化振興協会 (暦文協) published
its position on 28 August 2015 (平成27年8月28日), at its fifth general
meeting: https://www.rekibunkyo.or.jp/year2033problem.html, with the
slides `files/pdf/20150828_2033mondaikenkailight_t.pdf` and seven
supporting tables (`files/pdf/year2033problem/01.pdf` … `07.pdf`: overview,
the perpetual calendars already published, the classes of solution, a
matrix of thirteen rules).

- Its conclusion (1): of the candidate leap months it **recommends 閏11月**
  («複数ある閏月候補の内、閏11月を推奨する»), for three reasons: the
  traditional calendar puts weight on the winter solstice, so the month
  holding it should be the 11th; nearly every perpetual calendar already
  in print has 閏11月, so none needs correcting; every serious
  intercalation rule it examined gives 閏11月, so the recommendation does
  not depend on choosing one.
- Its conclusion (2): the general intercalation rule stays open
  («置閏ルールについては検討を継続する»). Choosing 閏11月 in 2033 does not
  fix the rule; the next cases where the rules part are 2147 and 2223.
- Its table of new moons puts the leap month at the lunation that begins on
  22 December 2033: 11月 from 11.22 (holding 小雪 and 冬至), 閏11月 from
  12.22, 12月 from 1.20.

The app agrees: `./gradlew :cli:run --args="--sui 2033"` prints 11月 from
2033-11-22, **閏11月 from 2033-12-22**, 12月 from 2034-01-20. The constant
`RESOLUTION_2033_SUI` in `Kyureki.kt` cites this source, as do SPEC §7.1
and §8.3.

The 国立天文台 takes no side: it explains the problem and the three
proposals on its 暦Wiki
(https://eco.mtk.nao.ac.jp/koyomi/wiki/C2C0B1A2C2C0CDDBCEF12F2033C7AFCCE4C2EA.html)
and, as a 2024 interview in 週刊BCN with a member of its joint research
reports, says that no public body will decide which proposal is adopted
(«公的機関がどの案を採用するか決定することはない»). The 2015 statement
also says public bodies do not touch the question
(«公的機関（国立天文台など）は問題解決にノータッチ»).

## The personal reading of 三箇の悪日 (SPEC §7.5)

**Sourced**, to the 簠簋内伝 and to こよみのページ (koyomi8.com, the site the app
already uses for its 暦注 vectors), *暦注の説明（その３）・下段について*,
https://koyomi8.com/sub/rekicyuu_doc03.html, says the three bad days were
originally meant to be watched only by those born in the matching year,
though today they are mostly treated as bad for everyone:
«本来は生まれ年に該当する三箇の悪日だけ注意すれば良いということだったらしいが、現在は生まれ年とは無関係の悪日とされることが多い。»
Its table pairs each birth-year branch with one solar month (寅年 → 正月 …
丑年 → 十二月, i.e. the 節月 whose branch is the birth year's) and gives the
three day branches:

| 生年 | 寅 | 卯 | 辰 | 巳 | 午 | 未 | 申 | 酉 | 戌 | 亥 | 子 | 丑 |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| 節月 | 正 | 二 | 三 | 四 | 五 | 六 | 七 | 八 | 九 | 十 | 十一 | 十二 |
| 大禍日 | 亥 | 午 | 丑 | 申 | 卯 | 戌 | 巳 | 子 | 未 | 寅 | 酉 | 辰 |
| 狼藉日 | 子 | 卯 | 午 | 酉 | 子 | 卯 | 午 | 酉 | 子 | 卯 | 午 | 酉 |
| 滅門日 | 巳 | 子 | 未 | 寅 | 酉 | 辰 | 亥 | 午 | 丑 | 申 | 卯 | 戌 |

All twelve rows match `Rekichu.personalDays` (its strings run from 子: 酉午卯,
丑: 辰酉戌, 寅: 亥子巳 …), and the rule "only in the 節月 of the birth-year
branch" is the code's. Its companion page on 狼藉日
(https://koyomi8.com/doc/mlwa/200803280.html) says the same: originally fixed
per person by birth year and 節月 («寅年生まれの人は正月（節月）の子の日が狼藉日»),
later simplified to apply to everyone.

**The classical source, checked 2026-10-03.** The 簠簋内伝
(『三國相傳陰陽輨轄簠簋内傳金烏玉兎集』), 巻上, §十七 三箇悪日, in the
printing of 田中太右衛門 (1919), NDL Digital Collections pid 1911335,
image 20 (https://dl.ndl.go.jp/pid/1911335/1/20), read on the scan: a
table headed 正節切 ("by the solar months"), one column for each month,
each column labelled with the birth year it applies to (寅人, 卯人, 辰人 …,
"a person of the 寅 year …"), and the three rows 大禍日 亥午丑申卯戌巳子未寅酉辰,
狼藉日 子卯午酉子卯午酉子卯午酉, 滅門日 巳子未寅酉辰亥午丑申卯戌. That is
koyomi8's table and the app's, and the birth-year reading is printed in
the text, not koyomi8's guess, so the app's reading states it without
koyomi8's hedge «らしい». Two older printings, read on the NDL's scans on 2026-10-04:
the 寛政12 (1800) printing (pid 1901824, img. 20,
https://dl.ndl.go.jp/pid/1901824/1/20) has the same table, headed 正節切,
with the small labels 寅人 … 丑人 over the columns and the same three rows;
the 寛永9 (1632) edition (pid 2533009, img. 26,
https://dl.ndl.go.jp/pid/2533009/1/26) has the same three rows, marked 正
for the solar months, but no birth-year labels on the columns. Its top
margin carries a handwritten note giving 狼藉日 by groups of birth years
(寅午戌年人 …), a reader's addition, not the print. So the birth-year
reading is printed at least from 1800; the oldest printing seen gives the
table by solar month alone.

The text adds that the three days stand for the three gods of poverty,
hunger and obstruction and the three poisons, and so are used for nothing
(«右今三箇日取貧窮飢渇障导三神貪欲瞋恚愚癡三毒故萬事不用»).

## Gregorian-dated festivals (SPEC §7.3)

**Facts found; the list is a design choice.** The SPEC promises festivals
"that most of Japan now keeps by the Gregorian calendar (for example O-Bon
on August 15)"; the code has only the kyūreki-dated ones.

- **O-Bon** splits three ways since the 1873 calendar reform: 新暦 7月15日
  (Tokyo and parts of Kantō, Tōhoku, Hokuriku), 月遅れ 8月15日 (nearly the
  whole country, and the national holiday period), and 旧暦 7月15日
  (Okinawa, Amami). Sources: Wikipedia ja *お盆* (on 月遅れ: «ほぼ全国的に多くの地域»);
  imidas 「お盆」 (谷村鯛夢, 集英社, 2007-08-10); All About and others repeat it.
  So "most of Japan" is 8月13–16日, centred on 15.
- **七夕** has the same three forms: 7月7日, 月遅れ 8月7日 (e.g. 仙台七夕まつり),
  and the 国立天文台's 伝統的七夕 (the 7th day counted from the new moon on
  or just before 処暑), which the app's kyūreki 7/7 already approximates.
- The other kyūreki festivals the app shows (sekku, 十五夜, 十三夜, 旧正月)
  are kept today on the Gregorian date of the same number (3月3日, 5月5日 …)
  or, for 十五夜 and 十三夜, still by the lunar date. For the sekku the
  国立天文台's 暦Wiki 「節句」 says so (read 2026-10-04 in a browser): the
  old seasons follow the lunisolar date, «現在のように太陽暦の同じ日付に
  もとづく季節とは平均1か月ほどのズレがあります», hence the 月遅れ and the
  伝統的七夕 (https://eco.mtk.nao.ac.jp/koyomi/wiki/C0E1B6E7.html, the page
  the app already cites for the sekku). The app shows only the kyūreki
  forms, so no Gregorian-day form needs a further source.

What the SPEC needs is a decision on which of these the page shows (only
月遅れ O-Bon, as its example says, or every 月遅れ and 新暦 form); the
facts above support O-Bon on 8月15日 labelled 月遅れ.

## 歳下食, a second witness (SPEC §7.5)

**Checked 2026-10-04.** 歳下食 is the one 暦注 the koyomi8 vectors do not
cover: koyomi8 does not compute it, and its 下段 page does not mention it
(`Koyomi8Test`). The app's table (one sexagenary day for each year branch,
`Rekichu.SAIGEJIKI`) came from Japanese Wikipedia 暦注下段 alone. A second
source, found through the NDL catalogue's full text: 開運館 編『独占易学全書』
(又間精華堂, 大阪 1901), 「歳下食日」, p. 54, NDL pid 760758, img. 29
(https://dl.ndl.go.jp/pid/760758/1/29), public domain, read on the scan.
It gives the day of each year in kana: 子年 丁丑, 丑年 庚寅, 寅年 丁卯, 卯年
壬辰, 辰年 丁巳, 巳年 丙午, 午年 丁未, 未年 庚申, 申年 丁酉, 酉年 丙戌, 戌年 辛亥,
亥年 庚子. All twelve agree with Wikipedia and the code. It explains the day
as the one in sixty on which the evil star 天狗星 comes down to the human
world to eat, an old tale kept by custom, and adds a condition Wikipedia
lacks: «此内十干十二支相生して余の悪日にあたらざる日は障なし», a day whose
stem and branch generate one another and which falls on no other bad day
does no harm. The reading now says so.

Neither source says where the year begins for this annotation; the app
takes 立春, as for every other year-keyed 暦注 (SPEC §7.5).

## Weighing the annotations (SPEC §7.5, §10.4)

**Checked 2026-10-04**, by analogy with the Tibetan page's weighing
([weighing.md](weighing.md)): do the 暦注 rank, weigh or cancel one another?
No source ranks one kind above another. A few rules act inside the lower
band, and the app follows those.

**Used.** Japanese Wikipedia 暦注下段 (after 岡田・阿久根 1993):

- Its opening: «下段の暦注は本来、受死日（●）と十死日（十し）は他のものと重複して
  記載されず». The two days stood alone in the lower band; modern almanacs
  often print others beside them. The app sets the day's other 下段
  annotations aside on either day. 受死日 and 十死日 never share a day: in
  every solar month their branches differ (koyomi8 その３ gives both tables).
- 受死日: «この日には他の暦注は一切見る必要がない», already in its reading.
- 十死日: «受死日（黒日）の次に凶日とされ、全てのことに凶とされる。ただし、
  受死日と違い、葬式も差し支えありとしている»; koyomi8 その３: «葬式にも凶».
  Funerals are affected too. The reading had this backwards ("funerals are
  not affected") and now says "funerals included".
- 歳下食: «歳下食は軽い凶日とされ、他の暦注に吉日があれば、歳下食は忌む必要が
  ない。ただし、他の凶日と重なると、より重くなる». Which days count as 吉日 and
  凶日 is not said. The app takes the good and bad days of the lower band
  and the 選日, not the 十二直's stations (nine of twelve are good, so the
  rule would lift almost every 歳下食) and not the 縁日. Where a good and a
  bad day both fall on it, the bad one decides: the 1901 table's condition
  (above) also requires no other bad day.
- 重日, 復日: they double what is done, «吉事には吉で、凶事には凶». Their tone
  was already mixed.

In 2026–2027, 歳下食 falls on a 大明日 every time (both are fixed by the
sexagenary day), so it is set aside unless a bad day joins it: 2026-06-02 is
lifted by 大明日, while on 2026-04-03 血忌日 makes it heavier. 2026-02-05 is
a black day carrying 大明日, 天恩日 and 復日 (`RekichuTest`).

**Found, not used:**

- koyomi8 その２ on 一粒万倍日: «他の吉日と重なれば効果は倍増、凶日と重なると
  効果半減と云われる». It is given as hearsay («と云われる»), and Wikipedia
  一粒万倍日 calls the day itself without a source text.
- Japanese Wikipedia 十二直: «昭和初期までは十二直が暦注中で最重視されていた».
  This describes what people watched, not a ranking; its source is a personal
  homepage.
- The 宿曜経's days of weekday and mansion, 甘露日, 金剛峯日 and 羅刹日, are
  the Japanese kin of the Tibetan combinations of weekday and mansion. They
  count the 27 mansions by the lunar date. In the 28-mansion cycle that the
  almanac uses from the Jōkyō calendar (NAOJ 暦Wiki 二十八宿: «暦注としては
  貞享暦以降二十七宿に代わって採用»), every mansion falls on a fixed weekday
  (角 Thursday, 鬼 Friday …), as NAOJ notes («曜日は7日なので二十八宿では
  組み合わせが限られます»). The tables would make every 畢 and 尾 day a 甘露日
  and every 翼, 参 and 柳 day a 羅刹日: a property of the mansion, not a
  combination. The tables are known only from modern 宿曜 sites; whether the
  具注暦 printed these days is not checked.
- 欽定協紀辨方書 (1739), 卷10 (Wikisource, 四庫全書本), sets out a full
  weighing in six grades, from «上吉足勝凶，從宜不從忌» to «最下凶叠大凶，遇德仍
  諸事皆忌». It turns on the 建除 stations and the virtue spirits (天德, 月德),
  and it rejects the older rule that any baleful spirit overrides a good one
  («舊本凡吉神遇凶煞皆從忌而不從宜»). It is the Qing system; nothing found
  says the Japanese almanac used it, and the 仮名暦 prints few of its spirits.

## The expert's review of the page (2026-10-12)

The Russian dossier of 2026-09-30 (the 旧暦 page as built, planned and
questioned, with its seventeen questions and the whole glossary; built by
`tools/pdf/`, gitignored) went to a Japanologist, a historian of Japan and
of onmyōdō. Her annotated copy came back on 2026-10-12, red handwriting in
the margins, written from memory («на коленке») with the caveat that
nearly every point still needs checking against the sources, and the
Russian terms against the scholarly literature. What she wrote, question
by question in the dossier's numbering; the facts are hers and not yet
checked here. What follows from it is ROADMAP K1–K5.

### The seventeen questions

1. **The 2033 leap month.** Follow the NAOJ; where it has published
   nothing, the calendar society. Check. — The NAOJ takes no side
   (above), so the app's 閏11月 is the society's recommendation, as she asks.
2. **三箇の悪日, personal or general.** "Needs searching." — Found since
   the dossier (above): the birth-year reading is printed in the 簠簋内伝
   from 1800 at least, by solar month alone in the 1632 edition. But see
   6: she would not take the 簠簋内伝 as a source.
3. **The year boundary of the year's 干支.** Needs checking; in China it
   is usually given from 旧正月.
4. **二十八宿, 二十七宿 and the red writing.** The question was not
   clear to her; «朱書き» has a "?" beside it. The 二十七宿 were never
   applied in Japan: the twenty-seven mansions are Indian. — This
   contradicts the NAOJ's 暦Wiki 二十八宿, quoted under *Weighing the
   annotations* above («暦注としては貞享暦以降二十七宿に代わって採用»: the
   28 were adopted as 暦注 from the Jōkyō calendar in place of the 27),
   which has the 27 in the almanac before 1685, as the 宿曜道's. To be
   settled on the sources (ROADMAP K4).
5. **Day marks.** Needs searching.
6. **Precedence among the annotations.** As far as she knows, no such
   rule existed. The 簠簋内伝 (*Hoki naiden*) is better not taken as a
   source; the 暦林問答集 (*Rekirin mondōshū*) can be relied on.
   Needs checking. — The app ranks nothing (SPEC §7.5), as she says; the
   choice of texts is ROADMAP K3.
7. **The bands' composition.** To be checked against the sources.
8. **The omitted annotations** (神吉日, 凶会日, 五墓日, 時下食). Definitely
   needs searching.
9. **The day's 九星.** The question was not clear to her.
10. **社日 at an equal distance.** Needs searching.
11. **九星気学 and onmyōdō.** «本命星 ≠ 九星! These are somewhat different
    things.» Needs checking. — The app's reading says the 本命星 is the
    birth year's star (SPEC §7.5, after Japanese Wikipedia 九星); what she
    separates is to be asked (ROADMAP K4).
12. **The month names.** «одной этимологии» underlined: which one?
    Translations of the month names, the established ones included, are
    to be found in the scholarly works. To check.
13. **六曜.** "Not Shinto" marked correct. The six-day cycle has been
    popular since the 19th century. (A line under it is struck out.)
14. **The Russian terms.** Some of the terms have established
    translations. — Which, she did not say; ROADMAP K1.
15. **天一天上 and the direction gods.** The wording on 日遊神 keeping the
    house and on 恵方 as 歳徳神's direction marked «верно»; the directions
    of 天一神 are to be checked. Counter-question: which other direction
    gods does the app compute? — Answered below.
16. **The tones** (八専間日, 犯土間日 neutral; 重日, 復日, 庚申 mixed;
    一粒万倍日 with its caveat). Needs searching.
17. **庚申待 and kaidan.** The 庚申 reading is correct, but it is not
    kaidan.

### Marginal notes on the rest

- 節気 (§II): "solar or lunar nodes?" — Solar: the 15° points of solar
  longitude (SPEC §7.1); the dossier said so, the page should too.
- 暦注 (§II's heading «Пометки альманаха»): «календарные примечания».
- 仏滅 («ничего не выйдет»): «смерть Будды».
- 二十八宿: there are established translations among the sinologists.
- 暦注下段, and the same for 選日 and 縁日: these terms are not found in
  Russian; a check is needed («нужна сверка»).
- 彼岸の中日 struck out: it is usually called simply 秋分 or 春分
  (*shūbun*, *shunbun*). 社日: "?". (Applied 2026-10-14 with the owner's
  word: 彼岸の中日 dropped from the page, the three names below changed,
  人日 and 社日 kept until K1; ROADMAP K2.)
- 旧正月: «лунный Новый год». 人日の節句: "?". 七夕: «Танабата», the gloss
  «вечер седьмого дня» struck out. 重陽の節句: «двойной девятки», not
  «двойного ян».
- The month names and the 二十四節気: to be corrected later («исправить
  потом», the second word read with some doubt), by the literature.
- Ticks of agreement: no whole-day colouring by luck, no weighing and no
  verdict (§III, *Not done on purpose*); the 九星気学 page «without free
  readings»; «ого» beside the Japanese interface as a first Weblate
  language.

### Her questions, and the answers

1. **Which other direction gods are computed or planned?** None: the app
   computes 天一神's sixteen days in heaven (天一天上) and 歳徳神's direction
   (恵方), nothing else — no 八将神, no 金神, no 日遊神's course, no
   方違え. The modern almanac's first page carries the year's 方位神; whether
   they belong on this page is the owner's (ROADMAP K5).
2. **Are the 中気 in the rules, since some annotations are counted by
   them?** Yes: the month takes its number from the 中気 it holds (SPEC
   §7.1), and the 二十四節気 shown are all 24 points, 節 and 中 alike. No
   annotation is computed from a 中気 other than through the month
   number (不成就日 by month and day) or the longitude (入梅, 半夏生, 土用);
   which annotations she means is to be asked.
3. **Does the app reproduce the almanac as printed in Japan today, or
   reconstruct an older one?** The owner (2026-10-12): as with the Tibetan
   page, not a reproduction of the modern almanac; an almanac on the old
   rules and readings, useful to a present-day practitioner of onmyōdō,
   Shinto or Shingon, ideally to all three (SPEC §7). The rules today come
   from modern digests (Japanese Wikipedia after 岡田・阿久根 1993, koyomi8,
   Todan), and the one old text read is the 簠簋内伝; which old texts set
   the standard is ROADMAP K3.
4. **What to work on first, before pricing?** The owner's choice (ROADMAP
   K, *Owner decisions*): the dossier mixes term translations, calendar
   rules, rare annotations and direction gods, and she asks for the
   emphasis.

### Her second answer (2026-10-14): the frame is the printed almanac

Told that the page is an almanac on the old rules for a present-day
practitioner, she answered that she had assumed the app would rest on the
traditional almanacs still printed in Japan, and that a reconstruction
has no bound: the calendar annotations that ever existed are «неприлично
много», many archaic ones (she names 天一, the 天一天上) are not used
today, and some are reckoned otherwise today than in the fourteenth
century (she names 歳徳神). A present-day Shinto practitioner takes the
calendar handed out at the shrines; an onmyōdō practitioner the same,
the tradition having reached today «в полуживом состоянии»; of Shingon
she does not know («там вообще отдельная песня»). The owner (2026-10-14):
the Shingon reader's reckoning is much nearer the Tibetan page's than an
onmyōji's or a Shinto priest's, so the Tibetan page serves that reader at
least; the remaining points are investigated here, not commissioned
(ROADMAP D10).

**What the shrines hand out, checked the same day.** Two unrelated
things carry the name.

- **神宮暦**, issued by the 神宮司庁 of Ise and distributed by the 神社本庁
  through every shrine with the 神宮大麻, is the heir of the official
  本暦 of 1883–1945. It holds the day list, the weekdays, the national and
  shrine festivals, the 二十四節気 and 雑節, the 干支, the moon's age, the
  旧暦 date, sun, moon and tide times and farming notes, in a 大暦 and a
  小暦; the 六曜 come as a separate sheet. Every luck annotation is left
  out on purpose since 1883 — Ise calls it the only «正暦» that
  «迷信的記述を排除し、科学的情報のみを記述», and Japanese Wikipedia says
  the 暦注 of luck and the 六曜 are «本暦からは現在も一切排除». Paper only,
  from the local shrine; no online edition.
  (https://www.isejingu.or.jp/about/jingutaima/,
  https://ja.wikipedia.org/wiki/神宮暦,
  https://fukushima-jinjacho.or.jp/monoshiri/pc/section14.html.)
- **The commercial almanacs** sold at shrines and shops, above all the
  神宮館高島暦 (神宮館 with the 高島易断所本部; unrelated to Ise, as
  Wikipedia notes) and its 家庭暦 and 運勢暦 siblings, are what "the
  traditional almanac printed today" means. The 令和8年 edition's day
  column carries 干支, 九星, 六輝, 中段 (the 十二直), 二十八宿 and 下段, and
  its front matter the 方位吉凶図, the 八将神 and 金神, a 二十八宿吉凶表
  and the 下段's explanation (the publisher's and Kinokuniya's listings:
  https://jingukan.co.jp/fortune-home/,
  https://www.kinokuniya.co.jp/f/dsg-01-9784867711965). Paper and a paid
  e-book, about 2,420 yen; not freely readable online. Its table of
  contents does not name 天一天上 or 歳徳神; whether its day column prints
  them is K4's.

**Online.** No shrine publishes its calendar as a page or a PDF. The
calendar sites reproduce the commercial almanacs' set: こよみのページ
(koyomi8.com, the app's vector source), rekichu.com, and
takao-hospital.net/koyomi, which says it is «高島暦にもとづく». On
2026-10-14 the three agreed (赤口; 閉; 軫; 九紫; 神吉日 and 大明日;
一粒万倍日 and 八専), so koyomi8 stands for the printed almanac well
enough for the rules; rekichu.com shows no 天一天上 that day. The NDL's
digital collections hold the pre-war 略本暦 (e.g. 明治13年, pid 2535376,
open on the internet) and the 大小暦 albums
(https://www.ndl.go.jp/koyomi/collection/index.html): the official line,
not the luck annotations. shrineheritager.com, which the owner asked
about, is a shrine-pilgrimage blog (実践和學 Cultural Japan heritage:
延喜式内社 by prefecture, explainers on 新嘗祭, 祈年祭, 例祭, 紙垂, 狛犬),
with nothing on calendars; its festival pages may help K2's names.

**What follows** (the owner, 2026-10-14: adjust the plan): the frame of
the 旧暦 page is the set of annotations the almanac printed today carries
— what the app already shows — and the old texts are read for the
readings and the corrections, not for annotations the almanac has
dropped (SPEC §7; ROADMAP K3, D8). Her two examples become K4 claims: whether
天一天上 is printed today, and how today's 歳徳神 differs from the old
reckoning. A paper 高島暦 is the reference to check the page against
(ROADMAP D12).

## K3-R: the 暦林問答集 (ROADMAP K3)

What the 暦林問答集 says of each annotation the app shows on the 旧暦 page,
and only those: an annotation the printed almanac of today has dropped is
not revived from it. With it, its three bad days against the 簠簋内伝's
table (*The personal reading of 三箇の悪日* above), whether it ranks the
annotations (question 6), and what it says of 歳徳神's reckoning (K4).
The edition, author and date come from the NDL catalogue, never from
memory; every passage with its pid and image number.

### The work, its author and the scans (read 2026-10-15)

**The text read.** 『暦林問答集』, two books (上・下), in the woodblock
『群書類従』, 巻第五百六, 雑部六十二, «撿校保己一集» (the compiler 塙保己一,
1746–1821, by the catalogue): NDL Digital Collections **pid 14488380**
(https://dl.ndl.go.jp/pid/14488380), 58 images, catalogued as 「暦林問答集
2巻」, 賀茂在方, «[18--]», series 「群書類從 ; 卷506. 雜部 ; 62」, call number
特2-131, 新城文庫, public domain (PDM), open on the internet. Cited below
as **GR**, with its image numbers (the printed folio numbers in the
margin run from 一 on img. 3 to 五十五 on img. 56). Every passage quoted
below is read on this scan.

**The witnesses.**

- A manuscript, 『暦林問答集』, 賀茂在方, «写», one fascicle, call number
  ちニ-5 (cover label 「暦林問答 全」, a 東京図書館 label): **pid 2533201**
  (https://dl.ndl.go.jp/pid/2533201), 53 colour images, PDM, open. Cited
  as **MS**. It has the preface (img. 3), the afterword dated in the same
  甲午 year and signed 在方 (img. 50), and a copyist's note after it:
  «正本書云 今考暦林之新書斷知累家之餘慶貴賤之謹慎子孫之鄭重者乎»,
  signed by a 大外史清原真人 (the signature line not read with certainty).
- A second copy of the same 群書類従 blocks: 群書類従 第636-637冊（巻505-506）,
  **pid 2559255** (https://dl.ndl.go.jp/pid/2559255), 114 images, PDM,
  open (the 暦林問答集 in its second half; not read).
- Typeset printings, for searching only: 『新校群書類従』 第22巻 (内外書籍,
  昭和7 = 1932), pid 1181028, its table of contents «曆林問答集 上/257»
  (送信サービス, for registered users only); 『羣書類従』 第28輯 (続群書類従完成会,
  1933, «曆林問答集…賀茂在方/688»; 1954, pid 2980902; 1959, pid
  2934516), NDL premises only. None was used.
- Not on the internet at the NDL: the separate woodblock printing of 文化8
  (1811), 2 vols, «奥付の出版者: 京都 林伊兵衛, 大坂 淺井吉兵衛, 江戸
  前川六左衛門», whose 見返し reads «賀茂保憲先師撰» (catalogue record
  000007481577, call number VF7-N74, from 渡辺敏夫's books; no pid), and a
  manuscript catalogued 「暦林或問集 2巻」, 賀茂在方, «写», 新城文庫 (no
  pid). Other libraries' records (国書データベース, CiNii) list more copies;
  not searched.
- Modern studies found in the catalogue, not read (copyrighted, and not
  needed for the text): 中村璋八「暦林問答集本文とその校訂」 and his
  『日本陰陽道書の研究』 (汲古書院 1985, ch. 6 「暦林問答集について」);
  馬場真理子「暦の「正理」—『暦林問答集』における暦注の解説を中心に—」
  (2017); 細井浩志 on the 古谷本 manuscript (2021, 2024) and 『古谷本『暦林問答集』
  : 写真と翻刻』 (活水女子大学, 2025).

**Author and date, from the text itself.** The preface (GR img. 2–3)
ends:

> 貞觀之初大春日真野麻呂又天德之末吾祖司暦博士賀茂保憲博考大成而所獻之暦天数不違所傳莫不規模然歳〻時〻愚師野巫之僻説多起正理錯乱矣故今剪截煩浮之辞採機要之説號曰暦林問答廣来喆補其闕焉
> 應永甲午孟春日　正儀大夫司暦博士賀茂在方書

"At the beginning of Jōgan, Ōkasuga no Manomaro, and at the end of
Tentoku my ancestor the calendar doctor Kamo no Yasunori, studied widely
and completed [the calendar]; the calendars they presented did not stray
from the heavens' numbers, and what they handed down was a model to all.
But year after year, season after season, the twisted teachings of
foolish masters and country shamans have multiplied, and the true
principle has fallen into disorder. So now I cut away the verbose and
idle words, gather the essential teachings, and call it 'Questions and
Answers from the Forest of the Calendar', and ask later sages to fill
its gaps. Written on a day of the first month of spring of the 甲午 year
of Ōei, by the calendar doctor Kamo no Arikata, 正儀大夫."

So the work is 賀茂在方's (the catalogue: 賀茂, 在方, −1444), written
against the "twisted teachings" of unlicensed diviners; its 甲午 year of
應永 is 1414 by the sexagenary count (a reader's pencil note in GR's
margin, img. 56, writes «応永二十一甲午 1414»; the print gives only the
cyclic year). The 1811 printing's «賀茂保憲先師撰» names the ancestor the
preface names, not the writer. The afterword (GR img. 56, MS img. 50, signed «在方誌»,
dated «竜集甲午孟春日») gives the plan: «分上下巻象乎二儀上二十四段者節氣之定法下四十段者五行之成數»
— two books after the two powers, the first of 24 sections after the
fixed number of the solar terms, the second of 40 after the
"completed number" of the five agents; and its range: «始自天地之根元終至宿曜之吉凶粗舉八八問»,
from the root of heaven and earth to the luck of the mansions and
planets, 64 questions in all. A closing note (img. 57) says a grandson
copied «祖父在方卿» 's book at the request of the 持明院殿, and the 群書類従
editors add in small print «右暦林問答以印本及五行大義校合了»: collated
with a printed edition and the 五行大義.

**What it is.** Each section is one question, «或問…何也», and an answer,
«答曰», that quotes the older Chinese and Japanese authorities
(『暦例』, 『新撰陰陽書』, 『五行書』, 『尚書暦』, 『郝震堪餘八會經』, 『金匱經』 …)
and adds the writer's view («今按»).

**Its sections** (tables of contents, GR img. 3–4 for 上, img. 36–37 for 下;
the section headings in the text agree):

| 上 | | 下 | |
| --- | --- | --- | --- |
| 1 天地 | img. 4 | 25 土用事 | img. 38 |
| 2 五行 | img. 7 | 26 弦望 | img. 39 |
| 3 日 | img. 8 | 27 社日 | img. 40 |
| 4 月 | img. 8 | 28 三伏 | img. 40 |
| 5 星 | img. 9 | 29 臘 | img. 41 |
| 6 大歳 | img. 11 | 30 沒滅 | img. 41 |
| 7 歳徳 | img. 12 | 31 大歳位前對後 | img. 41–42 |
| 8 大將軍 | img. 13 | 32 天恩 | img. 42 |
| 9 大陰 | img. 13 | 33 天赦 | img. 42–43 |
| 10 歳刑 | img. 14 | 34 母倉 | img. 43 |
| 11 歳破 | img. 15 | 35 歸忌 | img. 43 |
| 12 歳殺 | img. 16 | 36 血忌 | img. 43 |
| 13 黄旛 | img. 16 | 37 厭對 | img. 43–44 |
| 14 豹尾 | img. 16 | 38 無翹 | img. 44 |
| 15 歳名 | img. 16 | 39 九坎 | img. 44 |
| 16 歳次 | img. 17 | 40 重 | img. 44–45 |
| 17 二十四氣七十二候 | img. 18–27 | 41 復 | img. 45 |
| 18 六十四卦 | img. 27–28 | 42 往亡 | img. 45–46 |
| 19 閏月 | img. 29 | 43 日遊 | img. 46 |
| 20 日月蝕 | img. 30 | 44 凶會 | img. 46–47 |
| 21 納音 | img. 31 | 45 八竜七鳥九虎六蛇 | img. 47 |
| 22 十二直 | img. 32–34 | 46 五墓 | img. 47–48 |
| 23 十干十二支 | img. 34 | 47 伐 | img. 48 |
| 24 月建 | img. 34–35 | 48 遠行 | img. 48 |
| | | 49 忌夜行 | img. 48 |
| | | 50 天間 | img. 48 |
| | | 51 歳下食 | img. 48–49 |
| | | 52 下食時 | img. 49 |
| | | 53 天一天上 | img. 49–50 |
| | | 54 大將軍行 | img. 50 |
| | | 55 土公土府 | img. 50–51 |
| | | 56 伏竜 | img. 51 |
| | | 57 十干吉凶 | img. 51–52 |
| | | 58 十二支吉凶 | img. 52–53 |
| | | 59 二十八宿吉凶 | img. 53–54 |
| | | 60 七曜吉凶 | img. 54 |
| | | 61 滅門太禍狼籍 | img. 54–55 |
| | | 62 羅刹 | img. 55 |
| | | 63 甘露 | img. 55 |
| | | 64 金剛峯 | img. 55–56 |

(The 下 list numbers both 沒滅 and 大歳位前對後 «第三十»; the text's
own headings run 沒滅 30, «歳位歳前歳對歳後» 31. The headings in the
text differ from the list in places: «天一» 53, «大將軍遊行» 54, «土公遊行土府所在»
55, «十干» 57, «十二支» 58, «滅門大過狼籍» 61. The 上 book ends on img. 35,
«暦林問答集上終», and 下 begins on img. 38, «暦林問答集下».)

**Coverage at a glance.** Of the annotation families the app shows, the
work has sections on the 十二直, 歳徳 (the 恵方), 土用, 社日, 天恩日, 天赦日,
母倉日, 帰忌日, 血忌日, 重日, 復日, 往亡日, 歳下食, 天一天上, the
二十八宿's luck and the three bad days (滅門・太禍・狼籍), and the year's
direction gods of the modern almanac's first page (大歳, 大將軍, 大陰, 歳刑,
歳破, 歳殺, 黄旛, 豹尾: ROADMAP K5). It has no section on the 六曜 (the
expert: popular only since the 19th century), the day's 九星, 大明日,
節徳日, 鬼宿日 as a 下段 day, 受死日, 十死日, 天火日, 地火日, the 選日 of
the modern almanac (一粒万倍日, 不成就日, 三隣亡, 十方暮, 八専, 犯土), the
縁日, or the 雑節 other than 土用 and 社日; whether any of these is
named inside another section is said under each group below.

### The 中段: the 十二直 (§22, GR img. 32–34)

> 釋十二直第二十二
> 或問十二直何也　答曰新撰陰陽書又郝震堪餘八會經等兩説皆以同而上配于星辰下主万物以配於人事故吉凶尤大不可不用捨也
> 建者斗柄所指之名也能建生万物故曰建尚書暦曰安社稷冠帶立柱納財及奴婢出行吉也
> 除者斗柄前辰也又名戸曹折衝万物除去百凶故曰除書云療病祠祀合薬針刺吉
> 滿者天之倉曹財貨所也奄覆凶咎滿益万物故曰滿書云造屋舎移徙嫁娶内財及奴婢裁衣出行造車塗竈吉也
> 平者天上會曹平分万物也又名帝路故曰平書云造屋舎移徙嫁娶内財及奴婢裁衣吉
> 定者斗柄前之四辰也又名大忌能定諸客故曰定書云造屋舎移徙嫁娶安宅内財及奴婢裁衣買馬牛起土祠祀吉
> 執者斗柄前五辰也能執斷万物故曰執昏云張設羅網射獵收捕盜賊吉
> 破者斗柄之相衝破所也又名天之游激故曰破昏云收鬼捕邪治病舉軍攻擊所向吉也
> 危者斗魁之前險也不可昇高故曰危昏云張設羅網魚捕射獵伐樹木吉
> 成者斗柄當相對又名天之主記為事必成故曰成書云祠祀入宮造屋舎移徙嫁娶出行裁衣吉
> 收者謂收斂万物又名天倉故曰收昏云造屋舎入室入宮移徙嫁娶治壁垣張設羅網捕縛田獵下種種樹吉
> 開者斗柄居前也天之使者開險通後故曰開昏曰造屋舎起土移徙嫁娶内財及奴婢立門戸祠祀出行入學加冠拜官治病吉
> 閉者名嘆星閉塞不通故曰閉尤禁邪悪之日也修隄防塞穴吉
> 郝震堪餘八會經云建者主足除者主尻滿者主腹平者主背定者主胸執者主手破者主口危者主鼻成者主眉收者主髪開者主耳閉者主目十二直釋大槩如此

(GR img. 32, left page, to img. 34, right page; the line breaks are
ours, one station to a line. «昏» is printed for the citations after 執;
it reads as a short form of 暦, the 尚書暦 cited at 建 — a doubt. «内財»
is 納財, as the 建 line writes it.)

"The 新撰陰陽書 and the 郝震堪餘八會經 agree: above they are matched to the
stars, below they govern the ten thousand things and are matched to
human affairs, so their good and bad fortune is especially great and
they must be heeded in choosing what to do and what to leave."

| Station | What it is | Good for (暦林問答集) | The app's lists (Todan) |
| --- | --- | --- | --- |
| 建 | where the Dipper's handle points; it establishes and gives life | state rites (安社稷), capping (冠帶), raising pillars, taking in wealth and servants, setting out | good: everything; avoid: moving earth, boarding ships |
| 除 | the station before the handle; clears away a hundred evils | treating illness, rites, compounding medicine, acupuncture | clearing out, throwing away |
| 滿 | heaven's granary; covers misfortune and fills all things | building, moving house, marriage, taking in wealth and servants, cutting clothes, setting out, building carts, plastering the hearth | shrine rites, building, moving, weddings, opening a shop, sowing, moving earth |
| 平 | heaven's assembly that shares out evenly; the emperor's road | building, moving house, marriage, wealth and servants, cutting clothes | consultations, negotiations |
| 定 | the fourth station before the handle; "great taboo", settles all guests | building, moving house, marriage, settling a dwelling, wealth and servants, cutting clothes, buying horses and oxen, moving earth, rites | sowing, weddings, digging wells, fixing decisions |
| 執 | the fifth before the handle; seizes and cuts off | spreading nets, hunting, arresting thieves | harvesting, buying, acquiring |
| 破 | the station opposite the handle, where it breaks; heaven's 游激 | catching demons and the possessing spirit, treating illness, raising armies to attack | avoid: promises, consultations, agreements |
| 危 | the danger before the Dipper's bowl; "do not climb high" | spreading nets, fishing, hunting, felling trees | avoid: journeys, sea travel |
| 成 | heaven's recorder; "what is done is completed" | rites, entering the palace, building, moving house, marriage, setting out, cutting clothes | money talks, opening a shop, announcements, raising pillars |
| 收 | gathers in all things; heaven's granary | building, entering a house or the palace, moving house, marriage, mending walls, nets, arrests, hunting, sowing, planting trees | storing, collecting |
| 開 | heaven's messenger opens the dangerous way | building, moving earth, moving house, marriage, wealth and servants, setting up gates, rites, setting out, starting school, capping, taking office, treating illness | starting school, opening a business, beginnings |
| 閉 | "the sighing star", blocked; the day that forbids evil | repairing dykes, stopping holes | avoid: opening a shop, beginnings |

It names nothing to avoid except on 危 ("do not climb high") and, by its
name, 閉; every station has its good works, 破 among them. The body
parts (建 the feet, 除 the buttocks, 滿 the belly, 平 the back, 定 the
chest, 執 the hands, 破 the mouth, 危 the nose, 成 the eyebrows, 收 the
hair, 開 the ears, 閉 the eyes) are the 八會經's.

**What the app needs.** The stations' rule (建 on the day whose branch is
the month's, the month by the Dipper's handle) agrees with §24 月建
(img. 34–35): «月建者北斗之所指柄之辰也觀斗柄之指於寅方定歳首故以寅月今為正月»,
the month is named by the branch the Dipper's handle points to, and the
寅 month is the first, «自尓至今無改». The good lists differ from the
app's modern lists for most stations: the old text gives 滿, 平, 定, 成,
收 and 開 all as good for building, moving house and marriage, and gives
破 good works (exorcism, healing, war) where the app lists only what to
avoid. 建's "good for everything" and "avoid moving earth" are not in
it. The 十二直 are called "especially great in good and bad fortune",
a statement of weight, not a rule of precedence (below, *Ranking*).

### 歳徳神 and the 恵方 (§7, GR img. 12–13; MS img. 12)

> 釋歳徳第七
> 或問歳徳者何也　答曰五行書云凡陰陽用事遇徳為善故歳徳方一年間有徳方也皆十干徳也但五為陽徳〔甲丙戊庚壬〕五為陰徳〔乙丁巳辛癸〕其甲歳徳者在東宮甲方丙歳徳者在南宮丙方戊歳徳者在中宮戊方〔中宮者在丙方〕庚歳徳者在西宮庚方壬歳徳者在北宮壬方此五干歳徳各為陽徳故在其方次乙歳徳者在西宮庚方丁歳徳者在北宮壬方巳歳徳者在東宮甲方辛歳徳者在南宮丙方癸歳徳者在中宮戊方其乙丁巳辛癸者為陰干故自無徳配合陽干而成徳是以乙[巳]為甲妻相合故巳歳徳在甲辛為丙妻相合故辛歳徳在丙乙為庚妻相合故乙歳徳在庚癸為戊妻相合故癸歳徳在戊孔子曰禀氣於陽定形於陰〔云云〕其戊巳者火生土之故戊属丙方巳属丁方又号中宮戊巳土也五行木也言宮者舎也故称之五行皆畏於相尅故以木妹庚庚金以火妹妻於壬水以土妹妻於甲木以金妹妻於丙火以水妹妻於戊土是五行皆畏於相尅故各配合而以生万物也

(GR img. 12, left half of the right page, to img. 13, right page;
〔〕 small double-line notes; GR prints 巳 throughout for the stem 己;
at «是以乙為甲妻» a reader has circled 乙 and written 巳 beside it,
which the sense needs: 己 is 甲's wife. MS img. 12 has the same text
with 己 written correctly, «五行本也» for GR's «五行木也», and
«故以木妹妻庚金» where GR drops 妻.)

"The 五行書 says: whenever yin and yang are put to use, meeting virtue
is good; so the 歳徳 direction is the direction that holds virtue for
the year. All are the virtues of the ten stems. Five are yang virtues
(甲丙戊庚壬), five yin (乙丁己辛癸). A 甲 year's 歳徳 is in the eastern
palace, the 甲 direction; 丙's in the southern palace, the 丙 direction;
戊's in the central palace, the 戊 direction [note: the central palace
is in the 丙 direction]; 庚's in the western palace, the 庚 direction;
壬's in the northern palace, the 壬 direction. These five stems' 歳徳
are yang virtues and so sit in their own directions. Next: 乙's 歳徳 is
in the western palace, the 庚 direction; 丁's in the northern palace,
the 壬 direction; 己's in the eastern palace, the 甲 direction; 辛's in
the southern palace, the 丙 direction; 癸's in the central palace, the
戊 direction. 乙丁己辛癸 are yin stems and have no virtue of their own;
they take it by marriage to a yang stem. 己 is 甲's wife, so 己's 歳徳 is
at 甲; 辛 is 丙's wife, so at 丙; 乙 is 庚's wife, so at 庚; 癸 is 戊's
wife, so at 戊. … 戊 and 己 [belong to the centre] because fire
generates earth: 戊 belongs to the 丙 direction, 己 to the 丁 direction,
and they are called the central palace, earth. … The five agents all
fear the one that overcomes them, so each gives its younger sister in
marriage to it [wood's to 庚 metal, fire's to 壬 water, earth's to 甲
wood, metal's to 丙 fire, water's to 戊 earth], and by these matches the
ten thousand things are born."

**What the app needs.**

- The 歳徳 by the year's stem, in the 暦林問答集: 甲・己 → 甲 (east),
  乙・庚 → 庚 (west), 丙・辛 → 丙 (south), 丁・壬 → 壬 (north), 戊・癸 →
  戊, "the central palace", which its note places in the 丙 direction.
  The app's rule (`Rekichu.ehouOf`: 甲己 east-northeast 75°, 乙庚
  west-southwest 255°, 丙辛 and 戊癸 south-southeast 165°, 丁壬
  north-northwest 345°) gives the same direction for every stem, 戊 and
  癸 included, by way of the note. What the old text adds: the 戊・癸
  direction is the centre, reckoned to 丙 by a note, not a fifth
  compass point of its own; and the 歳徳 is the yang stem's virtue, the
  yin stem borrowing its husband's.
- The year is the stem's year; the section does not say where the year
  begins (立春 or the new year), so the app's 立春 stays the modern
  digests' (SPEC §7.5).
- Its reading: what is done facing the 歳徳 meets virtue and is good
  («凡陰陽用事遇徳為善»). The section names no works and no "facing"
  custom (the 恵方参り, the 恵方巻) and no image of a goddess; the app's
  reading ("whatever is done facing this direction is lucky") states
  no more than the old text.
- **K4, "reckoned otherwise today than in the fourteenth century".**
  For the direction itself the 暦林問答集 (1414) and the app's modern rule
  agree stem by stem; the only difference found is that 戊・癸 is the
  centre, put at 丙 by a note. What she meant (another rule for 戊・癸,
  the goddess, the year's beginning, the 恵方's customs) is not
  settled by this text; it is a question for her (*Open*).
- Beside 歳徳, §6 大歳 (img. 11–12) calls the 大歳's direction «一年之君有福最吉方也»,
  the lord of the year, the luckiest direction, against which nothing
  bad may be done; it and §8–§14 (大將軍, 大陰, 歳刑, 歳破, 歳殺, 黄旛,
  豹尾, img. 13–16) are the year's direction gods of K5, not read here.

### The 下段 days (§32–§42, §51; GR img. 42–46, 48–49; MS img. 35–36)

Each passage as printed in GR; 巳 is GR's form of the stem 己 throughout,
〔〕 a small double-line note, line breaks ours.

**天恩 (§32, GR img. 42, MS img. 35).**

> 或問天恩者何也　答曰暦例云天恩者天之位有四禁之道常開一也其自甲子至癸亥六十日也四分一是十五日也其甲配于子巳配于卯酉甲在十干之始子在十二支之始故自甲子至戊辰五日之間為天恩之日又巳為十干之主卯酉為天地之緯故自巳卯至癸未五日之間為天恩日又自巳酉至癸丑五日之間為天恩日合十五日皆為天恩日如此支干偶助而成育万物故名天恩日尤好日也

"Heaven's grace: of heaven's four forbidden ways, one is always open.
Of the sixty days, a quarter, fifteen. 甲 heads the stems and 子 the
branches, so 甲子 to 戊辰; 己 is the stems' lord and 卯 and 酉 heaven's
and earth's weft, so 己卯 to 癸未 and 己酉 to 癸丑: fifteen days in all.
Stem and branch help one another and rear the ten thousand things; a
very good day." The fifteen are the app's (`TENON`: 1–5, 16–20, 46–50).
It names no works; the app's "not to be used for sad occasions" is not
in it.

**天赦 (§33, GR img. 42–43, MS img. 36).**

> 或問天赦日者何也　答曰通鑑云天赦者天之生養万物宥其罪日也故曰天赦百神上天之日也無所禁忌春戊寅夏甲午秋戊申冬甲子此皆天赦日也夫甲戊者為陽干之位子午者為天地經寅申者為陰陽之主是以戊寅甲午戊申甲子者於四時而名天赦日舉百事吉也

"The day heaven, which bears and rears all things, pardons their
wrongs; the day the hundred gods rise to heaven; nothing is forbidden.
Spring 戊寅, summer 甲午, autumn 戊申, winter 甲子 … every undertaking
is lucky." The app's four days and its reading (the gods rise, the
wrongs forgiven, good for everything) agree. (MS img. 36 writes «春戊刀»
for 戊寅: see *For K6-R's questions* on 刀.)

**母倉 (§34, GR img. 43, MS img. 36).**

> 或問母倉者何也　答曰暦例云母倉者五行所生之母辰也春之木王之時亥子母倉日水生木故也夏者火王之時寅卯母倉日火生土故也秋金王之時丑未母倉日土生金故也冬者水王之時申酉母倉日金生水故也五行皆以母之位為母倉日也是舉百事吉也

GR as printed; MS img. 36 reads at the summer clause
«夏火王之時刀卯母倉日木生火故也四季土王之時巳午母倉日火生土故也», so GR
has dropped a line by eye-skip (from the first «…生» to the second) and
lost the earth months. "The 母倉 is the branch of the agent that bears
the ruling one: spring (wood) 亥子, water bearing wood; summer (fire)
寅卯; the four earth seasons 巳午 (MS); autumn (metal) 丑未; winter
(water) 申酉. Every undertaking is lucky." Against the app (`BOSO`:
寅卯 months 子亥, 巳午 寅卯, 辰未戌丑 巳午, 申酉 丑辰未戌, 亥子 申酉): the
same but autumn, where the old text has 丑未 only and the app adds 辰戌.
Its reading has nothing of "as a mother her child" or of marriage
especially; only "good for every undertaking".

**帰忌 (§35, GR img. 43, MS img. 36).**

> 或問歸忌者何也　答曰暦例云歸忌者天棓星之精也此星上衛紫宮下防門闕凡有四名一曰歸忌二曰皈化三曰天小女四曰皈来主丑寅子日自天降地而来居人家門防禦皈家来故遠行皈家移徙嫁娶加冠入國皆不吉也

"The spirit of the star 天棓, which guards the Purple Palace above and
the gate-towers below; four names. It rules the 丑, 寅 and 子 days, comes
down and stays at people's gates and keeps the homecomer out: long
journeys, coming home, moving house, marriage, capping and entering a
province are all unlucky." The three branches are the app's (`KIKO`),
which the text does not tie to months; the app's avoid list
(journeys, coming home, moving, bringing in a bride) is the old one,
with capping and entering a province besides; lending money is not in it.

**血忌 (§36, GR img. 43, MS img. 36).**

> 或問血忌者何也　答曰郝震堪輿經云血忌者天精也云梗河之精也有三名一曰殺忌二曰日忌三曰血忌從丑始至于子終来往十二辰運而復始主斂伐故刑戮及針灸出血等犯用之凶也

"The spirit of 梗河; three names; it starts from 丑 and ends at 子,
running the twelve branches round; it rules gathering in and cutting
down, so executions, acupuncture and moxa, letting blood are unlucky."
The app's table starts at 丑 in the 寅 month; the text gives the start
only. The app's avoid list (acupuncture, bloodshed, hunting) has
hunting for which the text has executions.

**往亡 (§42, GR img. 45–46).**

> 或問徃亡者何也　答曰新撰陰陽書云徃亡者天之斂鬼也暦例云徃者去也亡者無也有三名一曰徃亡二曰天門三曰天從行十二節而一歳有十有二日今按自立春七日自驚蟄十四日自清明二十一日合四十二日春徃亡也自立夏八日自芒種十六日自小暑二十四日合四十八日夏徃亡也自立秋九日自白露十八日自寒露二十七日合五十四日秋徃亡也自立冬十日自大雪二十日自小寒三十日合六十日冬徃亡也四時合二百有四日也是丁歳之数不足故失陰陽之数仍云窮日其日不可遠行又出軍憂死不還尤凶也

"Heaven's gatherer of ghosts … it goes with the twelve 節, twelve days a
year. By my count: the 7th day from 立春, the 14th from 驚蟄, the 21st
from 清明 … the 30th from 小寒 … so it is called the exhausted day: no
long journey; an army that sets out fears death and does not return;
very bad." The twelve counts are the app's (`OMO_DAY`), counted from
the 節 itself as day 1 or day 0 the text does not say (a question). The
app's "the day armies did not march" is the old text's; its avoid list
(journeys, taking office, moving, weddings) goes beyond it (journeys
and war only). «丁歳» is printed; the sense is unclear (a doubt).

**重 (§40, GR img. 44–45).**

> 或問重者何也　答曰暦例云檢巳當純乾檢亥當純坤陰陽是重也又云巳亥天地之木也今按巳四月建主乾卦重陽也亥十月建主坤卦重陰也以重陽重陰故為重日舉百事必重疊也更不可為凶事又雖吉事随事可用之矣

"巳 is pure 乾 and 亥 pure 坤 … so 巳 and 亥 days are 重日: whatever is
done is doubled; never do an ill deed on it; even a good one, use it
according to the matter." (Here 巳 is the branch; «天地之木» is printed,
perhaps for 本, a doubt.) The app's rule (巳 and 亥 days) and its "good
for good things, bad for bad" agree; weddings are not named.

**復 (§41, GR img. 45–46).**

> 或問復日者何也　答曰暦例云復者寅卯月者木王故正月甲日復日又相對庚日復日也二月乙日復日又相對辛日復日也三月土王故戊巳日復日土者依無相對之位連日復日也巳午月者火王故四月丙日復日也又相對壬日復日也五月丁日復日也又相對癸日復日也六月土王故戊巳日連日復日也申酉月者金王故七月庚日復日又相對甲日復日也八月辛日復日又相對乙日復日也九月土王故戊巳日連日復日也亥子月者水王故十月壬日復日又相對丙日復日也十一月癸日復日又相対丁日復日也十二月土王故戊巳日連日復日也吉凶皆同重日也

The months' stems and their opposites, earth months 戊己: the app's
`FUKU_STEMS` cell by cell; "good and bad alike as on 重日".

**歳下食 (§51, GR img. 48–49).**

> 或問歳下食者何也　答曰尚書暦云歳下食者有天狗星其精也是以云天狗出食日又号深悪神日六十日一出食一歳六食也但輕凶也支干吉并者用之無咎也

"The spirit of the star 天狗: the day it comes out to eat, also called
the day of the deep-evil god; once in sixty days, six times a year. But
it is a light bad day: where stem and branch are both lucky, use it
without blame." This is the 1901 guide's condition (above, *歳下食, a
second witness*) in a text of 1414, and the app's "light bad day",
from a classical source. It gives no table of the days.

**Not in the work.** No section, and no mention found in the sections
read, of 大明日, 節徳日, 鬼宿日, 受死日, 十死日, 天火日, 地火日 (§47 伐 and
§45 八竜七鳥九虎六蛇 are other days). §44 凶會 and §46 五墓 exist (img.
46–48) but the app leaves both out (SPEC §7.5) and nothing is revived.

### The 選日 and the 縁日 (§53 天一, §43 日遊, §58 十二支; GR img. 46, 49–50, 52–53)

**天一天上 (§53, GR img. 49–50).**

> 或問天一者何也　答曰春秋命暦云天一者地星之灵也太一者人星之灵也尤為尊星倶在天上紫微宮門外左曰天一右曰太一天一主戰鬪知吉凶太一主風雨水旱兵革飢疫灾害而遊行九宮陰陽書云天一者巳酉日從天来居東北維六日化人頭蛇身乙卯日移居正東五日化人頭魚身庚申日移居東南維六日化人頭鷹身丙寅日移居正南五日化人頭鶏身辛未日移居西南維六日化人頭半身丁丑日移居正西五日化人頭馬身壬午日移居西北維六日化人頭竜身戊子日移居正北五日化人頭亀身從癸巳日上天十六日間招搖大微星大紫房等宮遊行而從巳酉日降地運行八方而角六日方五都四十四日運終焉其天一遊行方角百事犯向之大凶戰鬪向之弩弓折産乳向之死傷尤大凶東北維艮方正東卯方也餘倣之

(«人頭半身» at 辛未 and «人頭竜身» at 壬午 are read with doubt: the
character before 身 in each is small and unclear on the scan.)

"天一 is the spirit of the earth star … 天一 rules battle and knows good
and bad fortune. The 陰陽書: on 己酉 天一 comes down from heaven to the
north-east corner for six days, in the shape of a man's head and a
snake's body; on 乙卯 it moves to due east for five days; 庚申 south-east,
six; 丙寅 due south, five; 辛未 south-west, six; 丁丑 due west, five; 壬午
north-west, six; 戊子 due north, five; from 癸巳 it goes up to heaven for
sixteen days and wanders the palaces of 招搖, 大微, 大紫 and the 房; from
己酉 it comes down again and goes round the eight directions, six days
in a corner and five in a cardinal point, 44 days in all. Whatever is
done facing the direction where 天一 wanders is very bad: in battle the
crossbow breaks, in childbirth there is death and harm."

- The sixteen days in heaven, 癸巳 to 戊申, are the app's (sexagenary
  30–45). The 44-day course on earth is K4's "天一神's course": 己酉 NE 6,
  乙卯 E 5, 庚申 SE 6, 丙寅 S 5, 辛未 SW 6, 丁丑 W 5, 壬午 NW 6, 戊子 N 5.
  (6 + 5) × 4 = 44 and 44 + 16 = 60.
- The app's reading ("no direction is blocked and any journey is fine")
  is the consequence the old text implies but does not state: it names
  only the harm of facing 天一's direction on the 44 days.

**日遊 (§43, GR img. 46).**

> 或問日遊者何也　答曰訪諸文日遊之説多矣日遊所在尤有忌諱夫日遊者天一火神也日之精氣下主宮舎内外而遊八方主日精之故名日遊也自癸巳日至巳酉日十七日在屋内又戊巳日居屋舎内餘日皆運轉八方今暦所載者只在屋舎内耳八方遊行者皆略之此日勿掃屋舎内又婦人産期之時避母屋移庇間無咎也

"日遊 is 天一's fire god … From 癸巳 to 己酉, seventeen days, it is
inside the house, and on 戊 and 己 days too; on the other days it goes
round the eight directions. The almanac of today prints only its days
in the house and leaves out its wanderings. On these days do not sweep
inside the house; a woman near childbirth moves from the main house to
the side room, and no harm follows."

- So the old text puts 日遊 in the house 癸巳–己酉, the 天一天上 days and
  one more, and also on every 戊 and 己 day. The app's reading says
  "Nichiyū-jin stays in the house instead: keep it clean"; the old text
  says the opposite of the second half: **do not sweep the house** on
  these days. A correction for the reading (K3/D8).
- «今暦所載者只在屋舎内耳»: already in 1414 the almanac printed only the
  house days, not the course.

**The 縁日 days in §58 十二支吉凶 (GR img. 52–53).** The section lists,
branch by branch, what is not done on each day, with small notes from
the 尚書暦, 群忌 and 八會經. Read in outline only (the small notes not
all read with certainty): 寅日 «不鮮祭 … 不解除» (no sacrifices, no
purification) — no word of weddings, which the app's 寅の日 reading
avoids; 巳日 «不造屋舎 … 不出行 … 不吊人» (no building, no journeys, no
condolence visits). The app's 縁日 readings (Bishamonten, Benzaiten,
Daikokuten, 庚申待) have no counterpart in it; §57 十干吉凶 has 庚日 «不出金錢»
and nothing of 庚申 or 甲子 as festival days.

**The modern 選日** — 一粒万倍日, 不成就日, 三隣亡, 十方暮, 八専, 大犯土
and 小犯土 — have no section and are not named in the sections read.
(§55 土公土府, img. 50–51, is the earth god's course, not the 犯土.)

### The 雑節: 土用 and 社日 (§25, §27; GR img. 38–40, MS img. 32–34)

**土用 (§25, GR img. 38–39; MS img. 32–33).**

> 或問土用事何也　答曰暦例云土用事暦筭定之不可起土其五行皆配於四方故主十干十二支以成四時春木用事刁十八日甲十八日乙十八日卯十八日合七十二日也木一氣之数木王時也辰十八日土用事土王時也夏火用事巳十八日丙十八日丁十八日午十八日合七十二日火一氣之数火王時也未十八日土用事土王時也秋金用事申十八日庚十八日辛十八日酉十八日合七十二日金一氣之数金王時也戌十八日土用事土王之時也冬水用事亥十八日壬十八日子十八日癸十八日合七十二日水一氣之数也水王時也丑十八日土用事土王之時也土為五行之王故為王於四季四時土用数合七十二日為土王之位是五行之数合三百六十日一歳之数也但春秋冬之三土用者皆為散王之位未能相生春土畏木故春土用者不専秋土用者土羨老故無威冬土用者居水與木間而不専故為王之位夏獨正為土王之位戊属於丙巳属於丁其土見生於火故以夏土用正為土王之時是以生金於西也春木用事無伐木夏火用時無縱火秋金用時無禁金石冬水用時無澤池堰四季時無犯土是五行妙用大槩如此

(«刁» stands where the count needs 寅; MS has «土羨老», read with doubt
in both; MS reads «不專故為散王之位» where GR drops 散; for «禁» and
«澤» see *For K6-R's questions*.)

"The 土用 is fixed by the calendar's reckoning: **do not move earth**.
… Each agent has four eighteen-day spans, 72 days; the eighteen days of
辰, 未, 戌 and 丑 are 土用, earth's rule, 72 days in all, and the five
agents' 72 days make the year's 360. But the 土用 of spring, autumn and
winter are earth's scattered rule …; only summer's is earth's true
rule, since fire bears earth. In wood's season do not fell trees, in
fire's do not set fires, in metal's do not burn metal and stone, in
water's do not breach ponds and weirs, in the four 土用 do not
disturb the earth."

- The app's rule (18 or so days before each 立) and reading (the days
  "five-element theory gives to earth") agree; the old text adds the one
  avoidance the app's reading lacks, **no earthworks during 土用**
  («不可起土», «四季時無犯土»), and that the summer 土用 is earth's true one.
- It says nothing of the Ox days (土用の丑).

**社日 (§27, GR img. 40–41; MS img. 33–34).**

> 或問社者何也　答曰尚書暦云社者歳之春秋可祀之社事土地之主也稷五穀之長也二月八月之中氣也二月為春社八月為秋社百穀實而稼穡成報德而祀之故春社者近於春分戊日秋社近於秋分戊日也各命民祭於土地將攘悪氣其戊土也故取其日祭之也

"The 社 is the lord of the land, 稷 the chief of the five grains;
spring and autumn are when they are worshipped, at the 中氣 of the
second and eighth months … so the spring 社 is the 戊 day near 春分, the
autumn 社 the 戊 day near 秋分; the people are made to sacrifice to the
earth to drive off bad 氣. 戊 is earth, so its day is taken." The app's
rule (the 戊 day nearest the equinox) is this; the old text gives no
rule for a tie (question 10 stays open), and its reading supplies what
the app's lacks: an offering to the god of the land for the harvest,
with 戊 chosen because it is earth.

### The 二十八宿 (§59, GR img. 53–54)

> 或問二十八宿有吉凶乎　答曰二十八宿〔并七曜〕者天之元氣万物之精也故吉凶甚明矣今大槩釋之宿曜經云牛宿為吉祥宿每日午時直事是以午時為吉祥又云畢翼斗壁為安重宿此等宿直日造宮殿伽藍館宇寺舎種蒔婚姻造家具入道場入壇受灌頂等宜觜角房奎為和善宿此宿直日學伎藝習真言受灌頂求婚姻舉錢對君王參將相冠帶出行服藥等吉參柳心尾為悪害之宿此宿直日圍城斫營徵兵略賊交陳破敵宜鬼軫胃婁為急速之宿此宿直日進路出行入學受業服藥入道場受灌頂吉星張箕室為猛悪之宿此宿直日射獵祭天祈神求兵威吉井亢女虛危五宿為輕躁之宿此宿直日學乘象馬種蒔服藥吉昴氐為剛柔之宿此宿直日造家具送葬鑽火入宅王者盟會吉也

The 宿曜經's seven classes, with 牛 apart as the auspicious mansion that
rules every day at the horse hour:

| Class | Mansions | Good for |
| --- | --- | --- |
| 安重 (steady) | 畢 翼 斗 壁 | building palaces, temples and houses, sowing, marriage, household goods, entering the practice hall or the altar, receiving 灌頂 |
| 和善 (gentle) | 觜 角 房 奎 | learning arts, mantras, 灌頂, seeking marriage, lending, audiences, capping, setting out, medicine |
| 悪害 (harmful) | 參 柳 心 尾 | sieges, camps, levies, raids, battle |
| 急速 (swift) | 鬼 軫 胃 婁 | the road, setting out, school, medicine, the practice hall, 灌頂 |
| 猛悪 (fierce) | 星 張 箕 室 | hunting, sacrifice to heaven, prayers, military power |
| 輕躁 (light) | 井 亢 女 虛 危 | learning to ride, sowing, medicine |
| 剛柔 (mixed) | 昴 氐 | household goods, funerals, kindling fire, moving in, covenants |

**What the app needs.** These are the 宿曜経's classes, not the lists the
app shows (Todan's, after the modern almanac): 參 柳 心 尾 are harmful
here (the app: 心 good for shrine rites, 尾 for medicine and weddings),
氐 good for funerals, 室 fierce (the app: 室 good for weddings). The
writer himself holds the 宿曜経's days in low esteem (below, *Ranking*),
so the old text gives no reading of the 二十八宿 to put first; the
app's lists are the later almanac's. Its 28 count 牛, which the 27 of the
宿曜経 lack, as a class of its own (on K4's 27/28 question: the 1414 text
speaks of twenty-eight, with 牛 apart). Of the 七曜 (§60) the app shows
nothing on this page.

### The three bad days, and ranking (§61–§64, GR img. 54–56; MS img. 46)

**§61 滅門大過狼籍 (GR img. 54–55; MS img. 46, the same words).**

> 或問滅門大禍狼藉者何也　答曰宿曜經云一月之内三箇之凶日一切不可舉行慎而能慎若犯滅門日者家門亡若犯大禍日口舌發不休息若犯狼藉日所作不宜万事散失凶也

"The 宿曜經 says: three bad days in each month; nothing at all is to be
undertaken; be as careful as can be. Who offends the 滅門 day, his house
perishes; the 大禍 day, quarrels break out without rest; the 狼藉 day,
nothing done is fitting and everything scatters and is lost."

**§64 金剛峯, with the writer's judgement (GR img. 55–56; MS img. 46).**

> 或問金剛峯者何也　答曰宿曜經云宜作一切降伏法此名金剛峯日誦真言作護摩猛利事吉今按二十八宿七曜滅門大禍狼藉甘露金剛峰吉凶皆一經之説也是弃於四時生殺之理不取五行日辰之妙也於是玄女云不知五行之相尅日辰之吉凶者如盲馳於曠野云云所詮滅門大禍狼藉羅刹等四箇日灌頂受戒習經出家修道入寺供佛立寺受真言護摩此三寶類皆以可忌之餘無咎又甘露金剛峰等兩箇日雖有吉文不并支干之吉者勿用之

"… By my judgement: the good and bad of the 二十八宿 and 七曜, of 滅門,
大禍, 狼藉, 甘露 and 金剛峯, are all the teaching of one sutra. It casts
away the principle of the four seasons' birth and killing and does not
take the subtlety of the five agents' days. So 玄女 says: who does not
know how the five agents overcome one another, and the good and bad of
days, is like a blind man galloping across a wilderness. In sum: on
the four days 滅門, 大禍, 狼藉 and 羅刹, 灌頂, taking the precepts,
studying sutras, leaving home, the path, entering a temple, offerings
to the Buddha, founding a temple, receiving mantras and the 護摩 — all
matters of the Three Jewels — are to be avoided; **for the rest there is
no blame**. And the two days 甘露 and 金剛峯, though written as good, are
not to be used unless stem and branch are lucky as well."

**Against the 簠簋内伝's table** (*The personal reading of 三箇の悪日*
above):

- The 暦林問答集 gives **no table**: no branches, no solar months, and no
  birth year. It knows the three days only as the 宿曜經's "three bad
  days in each month", with the harm each brings.
- Its writer limits them to Buddhist matters and calls them harmless for
  everything else. The 簠簋内伝 (1632, 1800, 1919 printings) says they are
  used for nothing («萬事不用») and, from 1800, binds each column to a
  birth year.
- So the 1414 text cannot confirm the personal reading; it is a third
  view of the same days: a monthly sutra rule, of weight only for the
  Three Jewels. Which to show is D8's.

**Ranking (question 6).** The work has no rule of precedence between the
almanac's annotations of the kind asked for (which band overrides
which). It has two weighing statements:

1. §64: the 宿曜經's days (the 二十八宿 and 七曜, 滅門・大禍・狼藉, 羅刹,
   甘露, 金剛峯) stand below the reckoning by stems, branches and the five
   agents: their bad days bind only Buddhist matters, and their good
   days count only where stem and branch are good too.
2. §51: 歳下食 is a light bad day, set aside where stem and branch are
   lucky together.

And one of weight without precedence: the 十二直 "especially great in
good and bad fortune" (§22). The expert's "no such rule existed" holds
for the almanac's bands; the 1414 writer does subordinate one school,
the 宿曜道's, to the 陰陽道's five-agent reckoning.

### For K6-R

The 暦林問答集's passages on the 節気, not read here (K6-R's):

- §17 二十四氣七十二候, the twenty-four terms with their months, stems
  and branches and the seventy-two pentads: GR img. 18–27 (from img. 18
  left to img. 27); MS img. 17–25.
- §24 月建, the month named by the Dipper's handle, the 寅 month first
  (the 節月's basis): GR img. 34–35 (quoted above under the 十二直).
- §25 土用 and §27 社日 (above), §28 三伏 (GR img. 40–41, the 庚 days after
  夏至 and 立秋), §42 往亡 (above: its days counted from the twelve 節).

### For K6-R's questions

Read on the manuscript (MS, pid 2533201) for the doubtful places K6-R
found in GR:

1. 小寒 «配于亥» (GR img. 26): MS img. 24, left page, reads
   «小寒十二月節配于癸», and its gloss «故癸主十一月以配於水»: **癸** in both
   places. (MS writes 十一月 in the gloss, as read; whether that is
   the copyist's slip was not settled.)
2. 秋分's «故酉主地分» (GR img. 24): MS img. 22, left page,
   «故酉主秋分以配于金»: **秋分**. MS also has «雷潜入地下伏» where GR has
   «地中伏».
3. «又小暑者極熱之物也» (GR img. 22): MS img. 20, left page,
   «又小暑者極熱之初也»: **初**, "the beginning of the great heat".
4. «又大大暑月半極熱之中為大» (GR img. 23): MS img. 21, right page,
   «又大暑者月半極熱之中為大»: **one 大**, and 者 after 暑.
5. The glyph like 刀 in the 土用 passage (GR img. 38): MS img. 32, left
   page, writes the same glyph, «春木用事刀十八日甲十八日»; and MS img. 36
   writes it again where the sense must be 寅, in 天赦 «春戊刀» (戊寅) and
   母倉 «刀卯» (寅卯). So it is this tradition's short form of **寅**, not a
   slip of the print.
6. «秋金用時無禁金石» / «冬水用時無澤池堰» (GR img. 39): MS img. 33, right
   page, «秋金用時无焚金石», «冬水用時无決池堰»: **焚** (do not burn metal
   and stone) and **決** (do not breach ponds and weirs).

### Open

- Question: the expert (2026-10-14) said 歳徳神 is reckoned otherwise
  today than in the fourteenth century. In the 暦林問答集 (1414) the 歳徳's
  direction by the year's stem is the modern one, 戊・癸 going to the
  centre, which a note places at 丙. Which difference did she mean: the
  centre for 戊・癸, the year's beginning, the goddess 歳徳神 herself, or
  the customs of the 恵方?
- Question: the 暦林問答集 gives the three bad days (滅門・大禍・狼藉) as
  the 宿曜經's monthly days, with no table and no birth year, and its
  writer limits them to Buddhist matters. Is the 簠簋内伝's table by
  solar month and birth year the 宿曜道's own reckoning, or the 簠簋内伝's?
  And is a reading that says "bad for everything" or one that says "bad
  for the Three Jewels' matters" the one an onmyōdō practitioner would
  follow?
- Question: §59's twenty-eight mansions are the 宿曜經's seven classes,
  with 牛 apart. Did the 具注暦 of the 15th century print the 二十八宿 with
  these classes, and from where do the modern almanac's lists (the
  app's) come?
- Question: 往亡 (§42) counts "the 7th day from 立春" and so on: is the
  節's own day the first day (the app's count) or day zero?
- Question: §43 says that on 日遊's days in the house one must not sweep
  it; the modern readings say to keep the house clean. Which do the
  present-day almanacs print?
- Question: in §53 the shapes of 天一 at 辛未 and 壬午 («人頭半身»,
  «人頭竜身» as read) are unclear on GR; what are they?
- Question: «土羨老» in §25 (GR and MS alike) and «丁歳之数» in §42: what is
  meant?

### Searched and not found

- A section on the 六曜, the day's 九星, 大明日, 節徳日, 鬼宿日, 受死日, 十死日,
  天火日, 地火日, 一粒万倍日, 不成就日, 三隣亡, 十方暮, 八専, 犯土 or the 縁日's
  festivals: none in either table of contents (GR img. 3–4, 36–37), and
  none of these words was met in the sections read for this reading
  (§6–§7, §22, §24, §25, §27, §32–§36, §40–§43, §51, §53, §58–§59,
  §61–§64, the preface and afterword). The other sections (§1–§5, §8–§21,
  §23, §26, §28–§31, §37–§39, §44–§50, §52, §54–§57, §60) were looked at,
  not read word by word.
- A table of the three bad days, or any tie of them to the birth year:
  none in §61 or §64 (GR img. 54–56, MS img. 46).
- The separate 文化8 (1811) woodblock printing on the internet: not
  digitized by the NDL (record 000007481577, no pid); 国書データベース,
  CiNii and other libraries' copies not searched.
- A full-text API for the NDL scans: the NDL Lab's fulltext API answers
  "This PID is not allowed" for pid 14488380 (2026-10-15); the
  Digital Collections' own full-text search was used only to list the
  copies.

## K6-R: the twenty-four solar terms (ROADMAP K6)

A reading for each of the 二十四節気 the page's term row will open: what
the term marks, whether it is a 節 or a 中, its solar longitude, the 節月
it opens and what hangs on that (the 三箇の悪日, 土用 before each 立,
彼岸 and 社日 at the equinoxes), from the NAOJ's 暦Wiki and koyomi8 for
the astronomy and from the old texts for the readings; no 七十二候 (the
owner, 2026-10-10). The facts in our own words, the Japanese quoted where
a fact rests on its wording.

**Read 2026-10-15.** What was read, and where:

| Siglum | Work | Edition | Access |
| --- | --- | --- | --- |
| **NAOJ-24** | 国立天文台 暦計算室, こよみ用語解説「二十四節気」 | web page, read in the browser 2026-10-15 | https://eco.mtk.nao.ac.jp/koyomi/faq/24sekki.html (the table of the 24 with month, longitude and a one-line gloss; the 雑節 table) |
| **NAOJ-W** | 国立天文台 暦計算室, 暦Wiki: 「季節/二十四節気とは？」, 「節月」, 「季節/二十四節気の定め方」, 「二十四節気/歴史」, 「季節/雑節とは？」 | web pages, read in the browser 2026-10-15 | https://eco.mtk.nao.ac.jp/koyomi/wiki/B5A8C0E12FC6F3BDBDBBCDC0E1B5A4A4C8A4CFA1A9.html, …/C0E1B7EE.html, …/B5A8C0E12FC6F3BDBDBBCDC0E1B5A4A4CEC4EAA4E1CAFD.html, …/C6F3BDBDBBCDC0E1B5A42FCEF2BBCB.html, …/B5A8C0E12FBBA8C0E1A4C8A4CFA1A9.html |
| **K8** | こよみのページ (koyomi8.com), 「二十四節気・解説」 | web page, read in the browser 2026-10-15 | https://koyomi8.com/24doc.html (each term with month, longitude, "頃" date, the 暦便覧 line and a modern note; the articles under 解説記事 on the same page) |
| **KB** | 『こよみ便覧』, 太玄斎 校訂 (the catalogue's creator line) | 江戸, 蔦屋重三郎[ほか1名], 天明7 [1787]; NDL 特1-2401, public domain (PDM) | NDL Digital Collections pid 2536637, 15 images; the 24 terms on image 7 (both pages), the 雑節 on images 9 and 10; https://dl.ndl.go.jp/pid/2536637/1/7 |
| **RM** | 『暦林問答集』 2巻, 賀茂在方 (preface signed «正儀大夫司暦博士賀茂在方書», dated «應永甲午孟春日», 應永21 = 1414; image 3) | 『群書類從』 卷506, 雜部62, printed [18--]; NDL 特2-131, public domain (PDM) | NDL Digital Collections pid 14488380, 58 images; 釋二十四氣七十二候第十七 on images 18–27, 釋月建第二十四 on image 34, 釋土用事第二十五 on images 38–39, 釋社第二十七 on image 40; contents 上 on images 3–4, 下 on images 36–37. A manuscript copy, pid 2533201 (写, 53 images), was not read |

K3-R (the 暦林問答集 for the annotations) had nothing on its branch when
this was read, so RM's 節気 passages were found here, in its contents,
and read on the scan. The app's own rules are cited from
`core/.../kyureki/` (`Kyureki.kt`, `Rekichu.kt`) as they stood at
0baf42d, and the dates below were computed with the app's engine
(`Kyureki.termsIn`, JST) for 2000–2099, the NAOJ's own dates being the
engine's test vectors.

### What every term shares

- **What a term is.** A mark of the season fixed by the Sun's apparent
  ecliptic longitude: each term is the moment the Sun reaches a multiple
  of 15°, 春分 at 0°, 秋分 at 180° (NAOJ-24). The name stands both for that
  moment, and its day, and for the fifteen-odd days that follow it up to
  the next term (NAOJ-W 二十四節気とは？; K8, *二十四節気の期間*). The year
  is split into four seasons of six terms, 節 and 中 alternating
  (NAOJ-24). Not lunar: the expert's marginal "solar or lunar nodes?"
  (above) is answered by every source.
- **節 and 中.** Counting from 立春, the odd terms (立春, 啓蟄, 清明 …,
  among them the four 立) are the 節 or 節気; the even ones (雨水, 春分 …,
  among them the solstices and equinoxes) the 中 or 中気. The 節 serve the
  solar months (節月), the 中 the intercalation (NAOJ-W 二十四節気とは？).
  Each is named by its month: 立春 is 正月節, 雨水 正月中, and so on to 大寒,
  十二月中 (NAOJ-24; RM writes the same, «立春正月節», image 18).
- **The 中 names the lunar month.** A lunar month takes the number of the
  中 it holds: the month holding 二月中 (春分) is the 2nd month (K8,
  *二十四節気と旧暦の関係*; SPEC §7.1). The 2033 problem turns on this
  (above, *The 2033 leap month*).
- **The 節 opens the solar month.** A 節月 runs from the day holding a 節
  to the day before the next: 立春 opens 正月, 啓蟄 二月, and so on; the
  day of entry is 節入り; a 節月 has 29 to 32 days under 定気 and never a
  leap month (NAOJ-W 節月). Many 暦注 are counted by the 節月 (節切り)
  rather than by the lunar month (月切り) (NAOJ-W 節月). RM gives the
  month's branch the same root: the Dipper's handle «隨二十四氣運轉、而指柄
  於十二辰», turning with the twenty-four 氣 it points to the twelve
  branches, and the month it points to 寅 is the first (image 34, left
  page, «釋月建第二十四»).
- **How they are reckoned.** By angle (定気法: the Sun's apparent
  longitude in steps of 15°) since the 天保暦, the reckoning of the
  modern 旧暦 and of the government's 暦要項; before it, to the 寛政暦,
  by time (平気法 or 恒気法: the year cut into 24 equal parts, about
  15.22 days each). Under 定気 a term lasts 14 to 16 days, shorter near
  perihelion in early January; the two reckonings part by about two days
  at the equinoxes (NAOJ-W 二十四節気の定め方; K8, *二十四節気の求め方*).
  KB (1787, under 平気) closes its list: five days to a 候, three 候,
  fifteen days, to one 氣 (image 7, left page, last column, below).
- **Names.** The names and order go back to the 淮南子 and are fixed
  from the 後漢四分暦; Japan and China differ only in the 2nd month's 節,
  啓蟄 in Japan and 驚蟄 in China, 啓 having been avoided as the
  Han emperor 景帝's personal name. Japan's 具注暦 of the 宣明暦 era wrote
  驚蟄 too; the 貞享暦 has 啓蟄, and the 仮名暦 prints the names from the
  享保14 (1729) almanac on, before which it wrote only "○月せつ" and
  "○月中" (NAOJ-W 二十四節気/歴史). RM (1414) writes 驚蟄 (image 19).
- **The four 立 and the two 至 and 分.** 立春, 立夏, 立秋, 立冬 (四立) each
  open a season, midway between a solstice and an equinox; 冬至, 夏至,
  春分, 秋分 are the 二至二分; the eight together are the 八節 (NAOJ-W
  二十四節気とは？; K8, *二至二分・四立・八節*). The almanac's seasons begin
  at the 立, so 立春 and 立秋 fall just after the cold and heat of 小寒・大寒
  and 小暑・大暑; the names come from northern China and need not fit
  Japan's climate (NAOJ-W 二十四節気とは？).
- **The directions (RM).** RM ties each term to one of the twenty-four
  directions («配于艮», «配于寅» …): the four 立 to the corner trigrams
  艮 (立春, north-east), 巽 (立夏), 坤 (立秋), 乾 (立冬); the four 至・分
  and the other 中 to the twelve branches (雨水 寅, 春分 卯, 穀雨 辰 … 大寒
  丑); the other 節 to the stems (啓蟄 甲, 清明 乙, 芒種 丙, 小暑 丁, 白露
  庚, 寒露 辛, 大雪 壬, 小寒 癸), and most to an element («以配於土» …).
  Its glosses of the stems and branches it takes from 鄭玄 and the 三礼義宗,
  of the 候 from the 月令正義; its own closing words say so and ask a
  later reader to correct its errors (image 27, left page, below).

### What the app hangs on the terms (code at 0baf42d)

- **Every 節** starts a 節月 (`Rekichu.setsuOf`): the 十二直 restart (建
  on the day whose branch is the month's; on the 節入り day the
  previous day's station repeats, `chokuOf`), the month star (月の九星)
  changes (`monthStar`), and every 節切り 選日 and 下段 day takes the new
  month's row: 天赦日 (by the 節月's season), 一粒万倍日, 母倉日, 月徳日,
  受死日, 十死日, 帰忌日, 血忌日, 天火日, 地火日, 三隣亡, 復日, and 往亡日,
  which falls on a fixed day counted from the 節入り (the 7th of 寅月,
  14th of 卯月, 21st of 辰月, 8th of 巳月, 16th of 午月, 24th of 未月, 9th
  of 申月, 18th of 酉月, 27th of 戌月, 10th of 亥月, 20th of 子月, 30th of
  丑月: `OMO_DAY`). The personal 三箇の悪日 fall only in the 節月 whose
  branch is the birth year's (above, *The personal reading of 三箇の悪日*;
  `personalDays`).
- **立春** also starts the year for every year-keyed annotation: the
  year's 干支, the year star, the 恵方, 歳下食, and the birth year of the
  personal days (`setsuYearOf`; SPEC §7.5).
- **The 中** set the lunar month's number and so the 不成就日 (by lunar
  month and day) and the festivals (SPEC §7.1, §7.3).
- **The solstices** turn the day star: 陽遁 from the 甲子 nearest 冬至, 陰遁
  from the 甲子 nearest 夏至 (`dayStar`).
- **The 雑節** (`Rekichu.zassetsu`, all from NAOJ-W 雑節とは？): 節分 the
  day before 立春; 八十八夜, 二百十日 and 二百二十日 the 88th, 210th and
  220th day counting 立春 as the first; 彼岸 the equinox and three days
  either side; 社日 the 戊 day nearest each equinox; 入梅 at 80°, 半夏生
  at 100°; 土用 from 297°, 27°, 117° and 207° to the day before 立春,
  立夏, 立秋 and 立冬.

### The old texts' passages

#### こよみ便覧 (KB), image 7: «年中廿四氣»

Read on the scan, image 7 (right page: the heading and 立春 to 清明; left
page: 穀雨 to 大寒 and the closing line), crops at full size. The text is
a woodblock in running hand with kana: the term and its month in large
kanji, the gloss in cursive kana with small furigana (not given here).
The kana are written below in modern form, as K8 transcribes the same
1787 print (K8, *暦便覧について*: «天明七年（1787）に出版», readable at the
NDL); each line was set against the scan, and where the scan does not
bear K8's reading out, or was not resolved, a bracket says so.

Heading (right page): «年中廿四氣 十二節 十二中 晝夜の刻數ハ暦面にて見べし»,
"the twenty-four 氣 of the year, twelve 節 and twelve 中; the hours of
day and night are to be seen on the almanac's face".

| Term | As printed (month) | The gloss, read on the scan | English |
| --- | --- | --- | --- |
| 立春 | 正月節 | はるの氣たつをもつてなり | spring's 氣 rises |
| 雨水 | 正月中 | 陽氣地上にはつし、雪氷とけて雨水となればなり | the yang 氣 comes out above ground; snow and ice melt and become rain-water |
| 啓蟄 | 二月節 | 陽氣地中にうごき、ちゞまる虫、あなをひらき出ればなり | the yang 氣 stirs in the earth; the curled-up insects open their holes and come out |
| 春分 | 二月中 | 日天の中を行て、晝夜とうぶんの時なり | the Sun goes through the middle of the sky: day and night are equal |
| 清明 | 三月節 | 万物発して清浄明潔なれば、此芽は何の草としるゝなり | all things come forth pure and bright, so that each bud shows which plant it is |
| 穀雨 | 三月中 | 春雨ふりて百穀を生化すればなり | spring rain falls and the hundred grains are brought to life |
| 立夏 | 四月節 | なつのたつがゆへなり | summer rises |
| 小満 | 四月中 | 万物盈満すれば草木枝葉しげる (盈満 with the furigana えいまん; 満 in a cursive form) | all things fill out, and plants grow thick in branch and leaf |
| 芒種 | 五月節 | 芒ある穀るい稼種する時なり (K8: 穀類) | the time to sow the awned grains |
| 夏至 | 五月中 | 陽熱至極し、又日の長きのいたりなるをもつてなり | the yang heat reaches its peak, and the day is at its longest |
| 小暑 | 六月節 | 大暑来れるまへなれ[…] (the ending not resolved; K8: 大暑来れる前なれば也) | it comes before the great heat |
| 大暑 | 六月中 | 暑氣いたりつまり[…]時節なれ[…] (the middle and ending not resolved; the print shows 時節, which K8's 暑気いたりつまりたるゆえんなれば也 lacks) | the heat comes to its extreme |
| 立秋 | 七月節 | はじめて秋の氣立つがゆへなれ[ば]なり | autumn's 氣 rises for the first time |
| 処暑 | 七月中 | 陽氣とゞまりて、はじめて退きやまんとすればなり | the yang 氣 stops and begins to withdraw |
| 白露 | 八月節 | 陰氣やうやく重りて、露こゞりて白色となればなり | the yin 氣 gathers; dew congeals and turns white |
| 秋分 | 八月中 | 陰陽の中分なれ[ば]なり (K8: 中分となれば也; no と seen) | yin and yang are halved |
| 寒露 | 九月節 | 陰寒の氣に合つて、露むすびこらんとすればなり | meeting the cold yin 氣, dew forms and is about to freeze |
| 霜降 | 九月中 | つゆが陰氣にむすばれて、霜となりてふるゆへなり | dew, bound by the yin 氣, becomes frost and falls |
| 立冬 | 十月節 | 冬の氣立そめて、いよいよ冷ゆれ[ば]なり | winter's 氣 begins to rise and it grows ever colder |
| 小雪 | 十月中 | ひゆるがゆへに、雨も雪となりてくだるがゆへなり | it is cold, so rain too falls as snow |
| 大雪 | 十一月節 | 雪いよいよふりかさねる折からなれ[ば]なり | snow now falls on snow |
| 冬至 | 十一月中 | 日南のかぎりを行て、日みじかきのいたりなれ[ば]なり | the Sun goes at its southern limit, and the day is at its shortest |
| 小寒 | 十二月節 | 冬至より一陽起るがゆへに、陰氣にさからふゆへ益冷る也 | since one yang rose at the winter solstice and goes against the yin 氣, it grows colder still |
| 大寒 | 十二月中 | ひゆることのいたりてはなはだしきときなればなり | the cold is at its extreme |

Closing line (left page, last column): «又[五]日を一候とし、三候十五日を以
一氣といふなり», "five days make one 候; three 候, fifteen days, make
one 氣" (the first numeral is written small and in a cursive form; 五 by
the sense).

#### 暦林問答集 (RM), 釋二十四氣七十二候第十七, images 18–27

The section runs term by term: the term with its month and direction,
then its three 候, each with a gloss; the gloss of the term itself (why
the name, why the direction) sits between them, mostly after the first
候, introduced by «又». Only the terms' own sentences are given here, and
the 候 only where a 雑節 the app shows is one of them (半夏生) or where a
候's gloss explains a term's name (白露); the 候 are not used (no
七十二候, the owner, 2026-10-10). Read on the scan at full size, kanbun
as printed with our punctuation.

- **立春** (image 18, left page, to image 19, right page): «立春正月節、
  配于艮。東風解氷。…又立春陽氣已發、雖在上陰氣猶厚在下、而陽氣尚微。故艮主
  立春、在東北之維、以配於土也。» "立春, the 節 of the 1st month, is
  set at 艮. … The yang 氣 has come out at 立春, but though it is above,
  the yin 氣 is still thick below and the yang still weak; so 艮, in the
  north-east corner, governs 立春, and it is set with earth."
- **雨水** (image 19, right page): «雨水正月中、配于寅。獺祭魚。…又雨水
  者、雪散為雨水也。又寅木、故主正月中也。» "雨水, the 中 of the 1st
  month, at 寅. … 雨水: snow breaks up and becomes rain-water. 寅 is
  wood, so it governs the 1st month's 中."
- **驚蟄** (啓蟄; image 19, right page, to left page): «驚蟄二月節、配
  于甲。桃始華。…又驚蟄者、伏蟄之虫大驚而走出也。甲者万物咸解孚甲自出。故
  主二月節、以配於木也。» "驚蟄, the 2nd month's 節, at 甲. … 驚蟄: the
  insects lying hidden are startled and run out. 甲: all things split
  their husks and come out; so it governs the 2nd month's 節, set with
  wood."
- **春分** (image 19, left page): «春分二月中、配于卯。玄鳥至。…又春分
  是為陰陽之交會、而此節之大者。故卯主正東、以配於二月中。» "春分, the
  2nd month's 中, at 卯. … 春分 is the meeting of yin and yang, the great
  one of this season; so 卯, due east, is set with the 2nd month's 中."
  Its 候 «玄鳥至» adds that the swallow's coming was the time to worship
  the god of matchmaking and pray for children («此鳥至之時祀媒神而祈子孫
  也»).
- **清明** (image 20, right page): «清明三月節、配于乙。桐始華。…又清明
  者、謂生物天氣清浄明潔、万物盛大而可觀、故云清明。又乙軋也、万物奮軋而出
  也。故乙主三月節、以配于木也。» "清明, the 3rd month's 節, at 乙. …
  清明: the 氣 of heaven over living things is pure and bright, and all
  things are grown and fair to see; hence the name. 乙 is 軋, to push
  through: all things force their way out. So 乙 governs the 3rd month's
  節, set with wood."
- **穀雨** (image 20, left page): «穀雨三月中、配于辰。萍始生。…穀雨者、
  是時日在昴宿、昴西方之宿金也。金生水之、故甘雨降、生万物、故云穀雨。三礼
  義宗云、辰物盡震動而長、故主三月中、以配于土。» "穀雨, the 3rd month's
  中, at 辰. … 穀雨: at this time the Sun is in the lodge 昴, a western
  lodge, metal; metal gives birth to water, so sweet rain falls and
  brings all things to life; hence the name. The 三礼義宗: at 辰 all
  things shake and grow, so it governs the 3rd month's 中, set with
  earth."
- **立夏** (image 20, left page, to image 21, right page): «立夏四月節、
  配于巽。螻蟈鳴。…是時陽氣已盛在上、陰氣微弱在下、宜顯。故巽主立夏、在東南
  維、以配于木。» "立夏, the 4th month's 節, at 巽. … Now the yang 氣 is
  strong above and the yin weak below: it is time to show forth. So 巽,
  the south-east corner, governs 立夏, set with wood."
- **小満** (image 21, right page): «小滿四月中、配于巳。苦菜秀。此時候物
  咸秀生也。又小得盈滿、故云小滿。三礼義宗曰、巳起也、物至此時皆畢起、故巳
  主四月、以配于火也。» "小満, the 4th month's 中, at 巳. … At this
  time things all flower. They are filled a little, hence 小満. The 三礼
  義宗: 巳 is to rise; by now all things have risen, so 巳 governs the
  4th month, set with fire."
- **芒種** (image 21, left page): «芒種五月節、配于丙。螳蜋生。…丙者炳
  也。物之生長各執其柄、万物強大而炳然著見也。故丙主五月節、以配於火。又芒
  種者、有芒之穀可稼種者也。» "芒種, the 5th month's 節, at 丙. … 丙 is
  炳, bright: all things grow strong and stand out clearly. So 丙 governs
  the 5th month's 節, set with fire. 芒種: the awned grains may be
  sown."
- **夏至** (image 22, right page): «夏至五月中、配于午。鹿角解。…此時候應
  陰解角也。大陽始屈、陰氣始升、陰陽相向之候也。…此時陰氣動於黄泉之下。又
  午盛陽之位、而居南方、故主夏至、以配于火。» "夏至, the 5th month's 中,
  at 午. … The deer sheds its antlers in answer to the yin: the great
  yang begins to bend and the yin 氣 to rise; yin and yang face each
  other. … The yin 氣 stirs below the Yellow Springs. 午 is the seat of
  full yang and lies south, so it governs 夏至, set with fire." Its
  third 候, «次半夏生。此時候無文。蓋半夏是藥草也» (image 22, left page),
  "next, the 半夏 grows; no text for this 候; the 半夏 is a medicinal
  herb", is the 半夏生 the app shows as a 雑節.
- **小暑** (image 22, left page): «小暑六月節、配于丁。温風始至。此亦無
  文。今按、南方名暑門、生景風、盛熱之時也、故温風至矣。又小暑者、極熱之物
  也。鄭玄云、丁亭也、物生長時應而止也。故丁主小暑、以配于火。» "小暑, the
  6th month's 節, at 丁. … The south is called the gate of heat; it
  sends the 景 wind; it is the time of great heat, so the warm wind
  comes. 小暑 is 'the thing of extreme heat' (so printed: «極熱之物也»).
  鄭玄: 丁 is 亭, to halt: growing things halt at their season. So 丁
  governs 小暑, set with fire."
- **大暑** (image 22, left page, to image 23, right page): «大暑六月中、
  配于未。腐草化為螢。…又大、大暑月半極熱之中為大。又未、未者味也、物向成皆
  有氣味。故未主六月中、以配于土。» (printed «又大大暑…», the second 大
  read as the start of the next phrase) "大暑, the 6th month's 中, at 未.
  … 'Great': in the month of heat, its middle, the height of the heat,
  is the great one. 未 is 味, taste: things nearing ripeness all have
  their flavour. So 未 governs the 6th month's 中, set with earth."
- **立秋** (image 23, right page, to left page): «立秋七月節、配于坤。
  涼風至。此時候無文。今按之、涼風者秋風也、陰氣淒涼、收成万物。鄭玄云、坤
  純陰之象、能養万物、莫過於地。陰動于午、至未始著。故坤主立秋、在西南之維、
  以配于土。次白露者、陰氣漸重、露濃色白之謂。» "立秋, the 7th month's 節,
  at 坤. The cool wind comes: … the cool wind is the autumn wind; the
  yin 氣 is chill and gathers in all things. 鄭玄: 坤 is the image of pure
  yin; nothing nourishes all things more than the earth. The yin stirs
  at 午 and shows at 未. So 坤, the south-west corner, governs 立秋, set
  with earth. Next, the white dew: the yin 氣 grows heavy, the dew thick
  and white." (The last sentence is the 候 白露降, and glosses the name of
  the next term.)
- **処暑** (image 23, left page): «處暑七月中、配于申。鷹乃祭鳥。…» "処暑,
  the 7th month's 中, at 申." Its 候 follow; no gloss of the name or of
  申.
- **白露** (image 24, right page): «白露八月節、配于庚。鴻雁来。…鄭玄云、
  庚更也、万物代改。故庚主八月節、在西方、以配於金也。» "白露, the 8th
  month's 節, at 庚. … 鄭玄: 庚 is 更, change: all things take their
  turn. So 庚 governs the 8th month's 節, in the west, set with metal."
- **秋分** (image 24, right page, to left page): «秋分八月中、配于酉。雷
  乃收聲。雷是陽氣、主於動、今地中潛伏焉。今按之、雷生於震、震木也。中秋金王
  之時也、木畏金、故雷潛入地中伏乎。三礼義宗云、酉老也、万物老極成熟。故酉
  主地分、以配于金。» (so printed: «地分») "秋分, the 8th month's 中, at
  酉. Thunder withdraws its voice: thunder is yang and moves; now it
  lies hidden in the earth. Thunder is born of 震, which is wood; mid-
  autumn is when metal reigns, and wood fears metal, so thunder hides in
  the earth. The 三礼義宗: 酉 is old: all things are old and fully ripe.
  So 酉 governs [the 秋分?], set with metal."
- **寒露** (image 24, left page, to image 25, right page): «寒露九月節、
  配于辛。鴻雁来賓。…鄭玄云、辛新也、万物皆秀實新成。故辛主九月節、以配於
  金。» "寒露, the 9th month's 節, at 辛. … 鄭玄: 辛 is 新, new: all
  things flower, fruit and are newly made. So 辛 governs the 9th month's
  節, set with metal." No gloss of the name.
- **霜降** (image 25, right page): «霜降九月中、配于戌。豺乃祭獸。…三礼義
  宗云、戌滅也、殺也、此時物衰滅。故戌主九月中、以配於土。» "霜降, the
  9th month's 中, at 戌. … The 三礼義宗: 戌 is to perish, to kill: now
  things wane and perish. So 戌 governs the 9th month's 中, set with
  earth." No gloss of the name.
- **立冬** (image 25, right page, to left page): «立冬十月節、配于乾。水
  始氷。此時候無文。按之、陽氣漸沈、陰氣已乗乎。鄭玄云、乾純陽之象、生物之
  首也、陽氣之本也、故先子之位、以堅剛也。故乾主立冬、在西北之維、以配于
  金。» "立冬, the 10th month's 節, at 乾. Water begins to freeze: … the
  yang 氣 sinks and the yin already rides over it. 鄭玄: 乾 is the image
  of pure yang, the head of living things, the root of the yang 氣; it
  stands before 子 and is firm. So 乾, the north-west corner, governs
  立冬, set with metal."
- **小雪** (image 25, left page): «小雪十月中、配于亥。虹藏不見。…小雪者、
  霜露凝結而為雪、十月猶小、故云小雪。三礼義宗云、亥閡也、言陰氣劾殺万物。故
  亥主十月中、以配於水也。» "小雪, the 10th month's 中, at 亥. … 小雪:
  frost and dew congeal into snow; in the 10th month it is still little,
  hence 'small snow'. The 三礼義宗: 亥 is to bar: the yin 氣 bears down
  on all things. So 亥 governs the 10th month's 中, set with water."
- **大雪** (image 26, right page): «大雪十一月節、以配于壬。鶡旦不鳴。…大
  雪者、小雪之相對也。鄭玄云、壬任也、閉藏万物懷任於下。故壬主十一月節、以
  配於水。» "大雪, the 11th month's 節, at 壬. … 大雪 is the counterpart
  of 小雪. 鄭玄: 壬 is 任, to bear: all things shut away are carried in
  the womb below. So 壬 governs the 11th month's 節, set with water."
- **冬至** (image 26, right page, to left page): «冬至十一月中、配于子。
  蚯蚓結。…易通卦驗云、冬至陽氣動於黄泉之下。子雖大陰之位、陽氣在内而動其
  下、故居水之位。假令水外陰内明、象懷陽也。故子主冬至、以配于水。» "冬至,
  the 11th month's 中, at 子. … The 易通卦驗: at 冬至 the yang 氣 stirs
  below the Yellow Springs. Though 子 is the seat of great yin, yang is
  within and moves beneath it, so it holds the place of water: water,
  dark outside and bright within, is the image of yin carrying yang. So
  子 governs 冬至, set with water."
- **小寒** (image 26, left page, to image 27, right page): «小寒十二月節、
  配于亥。雁北鄕。…小寒者、極寒之時、月初為小、月半為大寒。鄭玄云、癸揆也、
  陰任於陽而萌芽於物也。故癸主十二月、以配於水。» (so printed: «配于亥»;
  its own gloss names 癸, the 12th month's stem, and the series of
  directions asks 癸) "小寒, the 12th month's 節, at 亥 [癸]. … 小寒:
  the time of extreme cold; the month's beginning is the small, its
  middle the great cold. 鄭玄: 癸 is 揆, to measure: the yin bears the
  yang, and things put out shoots. So 癸 governs the 12th month, set
  with water."
- **大寒** (image 27, right page): «大寒十二月中、配于丑。雞始乳。此時候
  無文。大寒者、相對小寒、月半寒為大。三礼義宗云、丑紐也、結也、繫也、言居
  于十二支終始之際、以紐結為名。故主十二月中、以配于土也。» "大寒, the 12th
  month's 中, at 丑. … 大寒 is the counterpart of 小寒: in the middle
  of the month the cold is great. The 三礼義宗: 丑 is a knot, a tie, a
  bond: it sits where the twelve branches end and begin, and is named
  for the knot. So it governs the 12th month's 中, set with earth."
- **Closing** (image 27, left page): «右七十二候者、五日一候、十五日三候一
  氣也。一月三十日六候二氣、一歳十二月二十四氣七十二候也。皆知草木萌芽鳥獸
  變化耳。以月令正義之説載之、十干十二支以尓雅淮南子等之説註之。定可多誤乎、
  後見之人可改之。» "The seventy-two 候 above: five days to a 候,
  fifteen days, three 候, to a 氣; a month of thirty days has six 候 and
  two 氣; a year of twelve months, twenty-four 氣 and seventy-two 候. All
  are for knowing the sprouting of plants and the changes of birds and
  beasts. They are set down after the 月令正義; the stems and branches are
  glossed after the 爾雅, the 淮南子 and the like. There must be many
  errors; let later readers correct them."

#### The 雑節 the terms carry: RM and KB

- **RM, 釋土用事第二十五** (image 38, left page, to image 39, left
  page; the first section of 下, «暦林問答集下»): «或問土用事何也。答曰、
  暦例云、土用事筭定之、不可起土。其五行皆配於四方、故主十干十二支、以成四
  時。春木用事、[寅]十八日、甲十八日、乙十八日、卯十八日、合七十二日也、木一
  氣之数、木王時也。辰十八日、土用事、土王時也。夏火用事、巳十八日、丙十八日、
  午十八日、丁十八日、合七十二日、火一氣之数、火王時也。未十八日、土用事、土
  王時也。秋金用事、申十八日、庚十八日、酉十八日、辛十八日、合七十二日、金一
  氣之数、金王時也。戌十八日、土用事、土王之時也。冬水用事、亥十八日、壬十
  八日、子十八日、癸十八日、合七十二日、水一氣之数也、水王時也。丑十八日、土
  用事、土王之時也。土為五行之王、故為王於四季四時。土用数合七十二日、為土王
  之位。是五行之数合三百六十日、一歳之数也。…春木用事無伐木、夏火用時無縦
  火、秋金用時無禁金石、冬水用時無澤池堰、四季時無犯土。是五行妙用大槩如
  此。» ([寅]: printed with a glyph like 刀, image 38; «禁» so read; the
  passage between, on why the four 土用 are weak but summer's, image 39,
  right page, is left out) "What is 土用? The 暦例 says: once 土用 is
  reckoned, the earth must not be dug. The five phases are set at the
  four quarters, govern the stems and branches, and make the four
  seasons. Spring is wood's reign: eighteen days each of 寅, 甲, 乙 and 卯,
  seventy-two days, wood's share; then eighteen days of 辰, earth's
  reign, 土用. Summer, fire: 巳, 丙, 午, 丁, seventy-two days; then 未,
  土用. Autumn, metal: 申, 庚, 酉, 辛; then 戌, 土用. Winter, water: 亥,
  壬, 子, 癸; then 丑, 土用. Earth is king of the five and reigns in the
  last month of each season; its four spans make seventy-two days; the
  five shares make 360 days, a year. … In wood's reign fell no trees; in
  fire's set no fires; in metal's [work no metal or stone]; in water's
  [dam no ponds]; in the four seasons' ends do not disturb the earth."
- **RM, 釋社第二十七** (image 40, right page, to left page; the
  contents call it «釋社日»): «或問社者何也。答曰、尚書暦云、社者歳之春秋
  可祀之。社事土地之主也、稷五穀之長也。二月八月之中氣也。二月為春社、八月為
  秋社。百穀實而稼穡成、報德而祀之。故春社者近於春分戊日、秋社近於秋分戊日
  也。各命民祭於土地、將攘惡氣。其戊土也、故取其日祭之也。» "What is 社?
  The 尚書暦: 社 is worshipped in the spring and autumn of the year. 社
  serves the lord of the land; 稷 is the chief of the five grains. It
  belongs to the 中氣 of the 2nd and 8th months: the 2nd month's is the
  spring 社, the 8th's the autumn 社, when the grains have ripened and
  the harvest is in, and their virtue is repaid with worship. So the
  spring 社 is on the 戊 day near 春分 and the autumn 社 on the 戊 day near
  秋分. The people are bidden to worship the land and drive off bad 氣. 戊
  is earth, so that day is taken for the rite." (RM says "near", not
  which of two equidistant 戊 days.)
- **RM, 釋月建第二十四** (image 34, left page): «或問月建者何也。答曰、
  月建者、北斗之所指柄之辰也。觀斗柄之指於寅方、定歳首、故以寅月今為正月。…
  北斗居天中、隨二十四氣運轉、而指柄於十二辰。» "The month's 'establish-
  ment' is the branch the Dipper's handle points to. Seeing the handle
  point to 寅, the head of the year was fixed, so the 寅 month is now the
  first. … The Dipper sits in the middle of heaven and, turning with the
  twenty-four 氣, points its handle to the twelve branches." It goes on
  to the 夏, 殷 and 周 calendars' first months (寅, 丑, 子) and the 秦's
  (亥).
- **KB, image 9** (right page, last columns; left page): the 雑節 under
  the 選日, in running hand, read in part:
  - 社日: «此日ハ土地の神をまつり、春ハ二月、秋ハ八月の中氣より[…]春の
    社日ハ春分にちかきつちのえの日[…]秋の社日ハ秋分にちかきつちのえの日な
    り» "on this day the god of the land is worshipped, in spring in the
    2nd month and in autumn in the 8th …: the spring 社日 is the 戊 day
    nearest 春分, the autumn one the 戊 day nearest 秋分". The rest of the
    entry not resolved.
  - ひがん: «ひがんといふハ仏家に用ゆる日なり» "彼岸 is a day the Buddhists
    use"; it goes on with a legend of the heavens and the underworld's
    officials gathering on «二月八月七日の間» (the seven days in the 2nd
    and 8th months), not resolved further.
  - 八十八や: «八十八やハ正月の節立春の初日より八十八日にあたる[…]» "the
    88th night falls on the 88th day from the first day of 立春, the 1st
    month's 節"; then, partly read, it is the farmers' time to sow.
  - 入梅: «入梅ハ五月の節の後はじめての壬の日也» "入梅 is the first 壬 day
    after the 5th month's 節 (芒種)"; the rest (on mould and the rainy
    season) not resolved.
- **KB, image 10** (right page): 土用, after a passage on the four
  phases' 72 days each («二百八十八日») and earth's 72 making 360: «三
  月六月九月十二月の節に入て十三日目より土用なり» "土用 begins on the 13th
  day after the entry into the 節 of the 3rd, 6th, 9th and 12th months",
  that is after 清明, 小暑, 寒露 and 小寒. Under 平気 that is the 節 plus
  a thirtieth of the year, about 12 days; the NAOJ notes that the 天保暦
  counted 土用 the same way, adding a thirtieth of the year to its 定気
  小寒, 清明, 小暑 and 寒露, until the 明治2 almanac set 土用 by the Sun's longitude
  (NAOJ-W 雑節とは？, *土用*). 二百十日 follows on the same page, not
  read.

### The terms, one by one

Each entry: the term, 節 or 中 with its month, the longitude (NAOJ-24),
the JST dates in 2000–2099 (the app's engine; the commonest first, with
its count of the hundred years), what it marks (NAOJ-24 and K8 in our
words), the two old glosses (KB, RM, above), the month it opens or names,
and what hangs on it in the app's frame. The three personal bad days are
given for the 節月 a 節 opens, from the table above (*The personal reading
of 三箇の悪日*), for a person born in a year of that month's branch;
往亡日 and 天赦日 from `Rekichu.kt` (*What the app hangs on the terms*).

1. **立春 (risshun)**, 正月節, 315°; 4 Feb (66), 3 Feb (34).
   - Marks: the start of spring. The cold is past its peak and the first
     signs of spring show (NAOJ-24); spring runs from this day to the day
     before 立夏 (K8). It lies midway between 冬至 and 春分 (NAOJ-W).
   - KB: spring's 氣 rises. RM: set at 艮, the north-east corner, with
     earth; the yang 氣 has come out but is still weak under the thick
     yin.
   - Opens 寅月, the 1st solar month (正月).
   - Hangs on it: the year's turn for every year-keyed annotation (the
     year's 干支, the year star, the 恵方, 歳下食, the birth year); 節分 is
     the day before; winter 土用 (from 297°, about 17 Jan) ends the day
     before; 八十八夜, 二百十日 and 二百二十日 count it as day 1. The
     season's 寒 ends with 節分 (K8, under 小寒), and the greeting changes
     from 寒中見舞い to 余寒見舞い (NAOJ-W 二十四節気とは？). When 立春 falls
     within the 12th lunar month it is 年内立春, a theme of poetry (NAOJ-W
     節月). In 寅月: for a person of a 寅 year 大禍日 亥, 狼藉日 子, 滅門日
     巳; 往亡日 the 7th day; 天赦日 on 戊寅.
2. **雨水 (usui)**, 正月中, 330°; 18 Feb (52), 19 Feb (48).
   - Marks: the weather warms; snow and ice melt into water, and snow
     turns to rain (NAOJ-24); the first spring storm (春一番) blows
     (K8).
   - KB: the yang 氣 comes out above ground; snow and ice melt into
     rain-water. RM: set at 寅, wood; snow breaks up into rain-water.
   - Names the 1st lunar month: the month that holds 雨水 is 正月.
   - Hangs on it: the month's number, and with it 旧正月 (1/1) and
     人日の節句 (1/7).
3. **啓蟄 (keichitsu)**, 二月節, 345°; 5 Mar (79), 6 Mar (18), 4 Mar
   (3).
   - Marks: insects that wintered underground crawl out (NAOJ-24); China
     calls it 驚蟄 (NAOJ-24, NAOJ-W 二十四節気/歴史).
   - KB: the yang 氣 stirs in the earth and the curled-up insects open
     their holes. RM (as 驚蟄): set at 甲, wood; the hidden insects are
     startled and run out; 甲, all things split their husks.
   - Opens 卯月, the 2nd solar month.
   - Hangs on it: in 卯月, for a person of a 卯 year 大禍日 午, 狼藉日 卯,
     滅門日 子; 往亡日 the 14th day; 天赦日 on 戊寅.
4. **春分 (shunbun)**, 二月中, 0°; 20 Mar (78), 21 Mar (20), 19 Mar
   (2).
   - Marks: the Sun rises due east and sets due west, and day and night
     are about equal (NAOJ-24). The national holiday 春分の日 is the
     equinox day (NAOJ-W 二十四節気とは？).
   - KB: the Sun goes through the middle of the sky; day and night are
     equal. RM: set at 卯, due east; the meeting of yin and yang, the
     great one of its season.
   - Names the 2nd lunar month.
   - Hangs on it: spring 彼岸, the equinox day and three days either
     side, the first day 彼岸の入り, the last 彼岸明け (NAOJ-W 雑節とは？);
     the spring 社日, the 戊 day nearest the equinox (NAOJ-W; the tie
     below). The middle day of 彼岸 has been the equinox only since the
     天保暦's angle reckoning: to the 貞享暦 彼岸 began two days after
     the (平気) equinox; the 宝暦暦 put its middle day two days before
     春分 and two days after 秋分, so that it fell on equal day and night
     (NAOJ-W 雑節とは？, *彼岸*). Since 2026-10-14 the page shows
     the equinox in this row and no 彼岸の中日 (K2, above).
5. **清明 (seimei)**, 三月節, 15°; 4 Apr (66), 5 Apr (34).
   - Marks: everything is lively and looks pure and clear (NAOJ-24); the
     word is short for 清浄明潔 (K8).
   - KB: all things come forth pure and bright, so that each bud shows
     which plant it is. RM: set at 乙, wood; the 氣 of heaven is pure and
     bright and all things are grown and fair to see; 乙 is to push
     through.
   - Opens 辰月, the 3rd solar month.
   - Hangs on it: in 辰月, for a person of a 辰 year 大禍日 丑, 狼藉日 午,
     滅門日 未; 往亡日 the 21st day; 天赦日 on 戊寅. Under the 天保暦 the
     spring 土用 was counted from 清明 (below, *土用*).
6. **穀雨 (kokuu)**, 三月中, 30°; 20 Apr (63), 19 Apr (37).
   - Marks: spring rain falls and moistens the grain (NAOJ-24).
   - KB: spring rain falls and brings the hundred grains to life. RM:
     set at 辰, earth; the Sun is in 昴, a western lodge, metal; metal
     gives birth to water, so sweet rain falls.
   - Names the 3rd lunar month, and with it 上巳の節句 (3/3).
   - Hangs on it: the spring 土用 begins at 27°, about three days before
     穀雨 (about 17 Apr), and runs to the day before 立夏; 八十八夜 (about
     2 May) falls in 穀雨's span (NAOJ-W 雑節とは？).
7. **立夏 (rikka)**, 四月節, 45°; 5 May (81), 6 May (12), 4 May (7).
   - Marks: the start of summer; summer runs to the day before 立秋 (K8).
   - KB: summer rises. RM: set at 巽, the south-east corner, wood; the
     yang is strong above and the yin weak below.
   - Opens 巳月, the 4th solar month.
   - Hangs on it: the spring 土用 ends the day before. In 巳月, for a
     person of a 巳 year 大禍日 申, 狼藉日 酉, 滅門日 寅; 往亡日 the 8th
     day; 天赦日 on 甲午.
8. **小満 (shōman)**, 四月中, 60°; 21 May (59), 20 May (41).
   - Marks: all things grow and begin to fill heaven and earth (NAOJ-24).
   - KB: all things fill out, and plants grow thick. RM: set at 巳,
     fire; things are filled a little, hence 'small fullness'.
   - Names the 4th lunar month.
   - Hangs on it: nothing but the month's number.
9. **芒種 (bōshu)**, 五月節, 75°; 5 Jun (76), 6 Jun (22), 4 Jun (2).
   - Marks: the time to plant the awned grains such as rice (NAOJ-24);
     sowing now comes much earlier (K8).
   - KB: the time to sow the awned grains. RM: set at 丙, fire; the
     awned grains may be sown; 丙 is bright, all things stand out.
   - Opens 午月, the 5th solar month.
   - Hangs on it: 入梅 (80°, about 11 Jun) falls in its span; until the
     明治9 (1876) almanac 入梅 was the first 壬 day after 芒種 (from the
     元文5 almanac 芒種 itself when it fell on a 壬 day), as KB has it too
     (NAOJ-W 雑節とは？, *入梅*; KB, below). In 午月, for a person of a 午
     year 大禍日 卯, 狼藉日 子, 滅門日 酉; 往亡日 the 16th day; 天赦日 on
     甲午.
10. **夏至 (geshi)**, 五月中, 90°; 21 Jun (80), 20 Jun (15), 22 Jun (5).
    - Marks: the longest day of the year (NAOJ-24); in most of Japan it
      falls in the rainy season (K8).
    - KB: the yang heat is at its peak and the day at its longest. RM:
      set at 午, fire; the great yang begins to bend and the yin to
      rise; yin and yang face each other.
    - Names the 5th lunar month, and with it 端午の節句 (5/5).
    - Hangs on it: the day star turns to 陰遁 at the 甲子 nearest 夏至
      (`dayStar`); 半夏生 (100°, about 2 Jul) is its third 候 (RM: the 半夏
      is a medicinal herb), the one 候 the 仮名暦 printed, and marks the
      end of rice planting (NAOJ-W 雑節とは？; NAOJ-24).
11. **小暑 (shōsho)**, 六月節, 105°; 7 Jul (64), 6 Jul (36).
    - Marks: the heat sets in, and the rainy season ends about now
      (NAOJ-24).
    - KB: it comes before the great heat. RM: set at 丁, fire; the warm
      wind comes from the south, the gate of heat; 丁 is to halt.
    - Opens 未月, the 6th solar month.
    - Hangs on it: in 未月, for a person of a 未 year 大禍日 戌, 狼藉日 卯,
      滅門日 辰; 往亡日 the 24th day; 天赦日 on 甲午. 暑中見舞い are sent
      from here to the day before 立秋 (NAOJ-W 二十四節気とは？, by its
      contrast of 暑中 and 残暑). Under the 天保暦 the summer 土用 was
      counted from 小暑 (below, *土用*).
12. **大暑 (taisho)**, 六月中, 120°; 22 Jul (64), 23 Jul (36).
    - Marks: the summer heat at its height (NAOJ-24); the summer 土用
      (K8).
    - KB: the heat comes to its extreme. RM: set at 未, earth; in the
      month of heat its middle is the great one; 未 is taste, things near
      ripeness.
    - Names the 6th lunar month.
    - Hangs on it: the summer 土用 begins at 117°, about three days
      before 大暑 (about 19 Jul), and runs to the day before 立秋; its 丑
      days are 土用の丑の日 (`Rekichu.zassetsu`; NAOJ-W 雑節とは？).
13. **立秋 (risshū)**, 七月節, 135°; 7 Aug (81), 8 Aug (12), 6 Aug (7).
    - Marks: the start of autumn; autumn runs to the day before 立冬
      (K8). It falls in the hottest weeks, but from here the heat only
      wanes; the greeting changes from 暑中見舞い to 残暑見舞い (K8; NAOJ-W
      二十四節気とは？).
    - KB: autumn's 氣 rises for the first time. RM: set at 坤, the
      south-west corner, earth; the cool wind is the autumn wind; the
      yin stirs at 午 and shows at 未.
    - Opens 申月, the 7th solar month.
    - Hangs on it: the summer 土用 ends the day before. In 申月, for a
      person of a 申 year 大禍日 巳, 狼藉日 午, 滅門日 亥; 往亡日 the 9th
      day; 天赦日 on 戊申.
14. **処暑 (shosho)**, 七月中, 150°; 23 Aug (66), 22 Aug (34).
    - Marks: the heat abates (NAOJ-24); the name means the heat stops
      (K8); the typhoon season (K8).
    - KB: the yang 氣 stops and begins to withdraw. RM: set at 申; no
      gloss of the name.
    - Names the 7th lunar month, and with it 七夕 (7/7); the NAOJ's
      伝統的七夕 is counted from the new moon on or before 処暑 (above,
      *Gregorian-dated festivals*).
    - Hangs on it: 二百十日 (about 1 Sep) falls in its span; the 210th
      to 220th days are the rice's flowering and the storms' season
      (NAOJ-W 雑節とは？).
15. **白露 (hakuro)**, 八月節, 165°; 7 Sep (77), 8 Sep (20), 6 Sep (3).
    - Marks: white dew settles on the grass (NAOJ-24).
    - KB: the yin 氣 gathers; dew congeals and turns white. RM: set at
      庚, metal; 庚 is change, all things take their turn; the name is
      glossed under 立秋's 候: the yin grows heavy, the dew thick and
      white.
    - Opens 酉月, the 8th solar month.
    - Hangs on it: 二百二十日 (about 11 Sep) falls in its span. In 酉月,
      for a person of a 酉 year 大禍日 子, 狼藉日 酉, 滅門日 午; 往亡日 the
      18th day; 天赦日 on 戊申.
16. **秋分 (shūbun)**, 八月中, 180°; 23 Sep (58), 22 Sep (42).
    - Marks: the middle day of the autumn 彼岸; day and night about equal
      (NAOJ-24); the national holiday 秋分の日 (NAOJ-W 二十四節気とは？).
    - KB: yin and yang are halved. RM: set at 酉, metal; thunder hides in
      the earth, for wood fears metal; 酉 is old, all things fully ripe.
    - Names the 8th lunar month, and with it 十五夜 (8/15).
    - Hangs on it: the autumn 彼岸 (the equinox and three days either
      side) and the autumn 社日 (the 戊 day nearest; the tie below).
17. **寒露 (kanro)**, 九月節, 195°; 8 Oct (79), 7 Oct (18), 9 Oct (3).
    - Marks: autumn deepens and cold dew forms on the wild grasses
      (NAOJ-24).
    - KB: meeting the cold yin 氣, dew forms and is about to freeze. RM:
      set at 辛, metal; 辛 is new; no gloss of the name.
    - Opens 戌月, the 9th solar month.
    - Hangs on it: in 戌月, for a person of a 戌 year 大禍日 未, 狼藉日 子,
      滅門日 丑; 往亡日 the 27th day; 天赦日 on 戊申. Under the 天保暦 the
      autumn 土用 was counted from 寒露 (above, KB image 10).
18. **霜降 (sōkō)**, 九月中, 210°; 23 Oct (83), 22 Oct (10), 24 Oct (7).
    - Marks: frost begins to fall (NAOJ-24).
    - KB: dew, bound by the yin 氣, becomes frost and falls. RM: set at
      戌, earth; 戌 is to perish; no gloss of the name.
    - Names the 9th lunar month, and with it 重陽の節句 (9/9) and 十三夜
      (9/13).
    - Hangs on it: the autumn 土用 begins at 207°, about three days
      before 霜降 (about 20 Oct), and runs to the day before 立冬.
19. **立冬 (rittō)**, 十月節, 225°; 7 Nov (84), 6 Nov (8), 8 Nov (8).
    - Marks: the start of winter; winter runs to the day before 立春
      (K8).
    - KB: winter's 氣 begins to rise, and it grows ever colder. RM: set
      at 乾, the north-west corner, metal; the yang sinks and the yin
      rides over it.
    - Opens 亥月, the 10th solar month.
    - Hangs on it: the autumn 土用 ends the day before. In 亥月, for a
      person of a 亥 year 大禍日 寅, 狼藉日 卯, 滅門日 申; 往亡日 the 10th
      day; 天赦日 on 甲子.
20. **小雪 (shōsetsu)**, 十月中, 240°; 22 Nov (80), 21 Nov (16), 23 Nov
    (4).
    - Marks: it grows cold and rain turns to snow (NAOJ-24).
    - KB: it is cold, so rain falls as snow. RM: set at 亥, water; frost
      and dew congeal into snow, still little in the 10th month, hence
      'small snow'.
    - Names the 10th lunar month.
    - Hangs on it: nothing but the month's number. (In 2033 the month
      from 22 Nov holds both 小雪 and 冬至, which is how the leap month
      question arises: above, *The 2033 leap month*.)
21. **大雪 (taisetsu)**, 十一月節, 255°; 7 Dec (71), 6 Dec (29).
    - Marks: snow piles up (NAOJ-24).
    - KB: snow now falls on snow. RM: set at 壬, water; the counterpart
      of 小雪; 壬 is to bear, all things held in the womb below.
    - Opens 子月, the 11th solar month.
    - Hangs on it: in 子月, for a person of a 子 year 大禍日 酉, 狼藉日 午,
      滅門日 卯; 往亡日 the 20th day; 天赦日 on 甲子.
22. **冬至 (tōji)**, 十一月中, 270°; 21 Dec (54), 22 Dec (46).
    - Marks: the shortest day of the year (NAOJ-24); once taken as the
      year's starting point, as the days lengthen from it; the customs
      of 冬至南瓜 and 柚湯 (K8). It is the base point of the 24, and the
      calendar was set right by observing it (NAOJ-W 二十四節気とは？).
    - KB: the Sun goes at its southern limit and the day is at its
      shortest. RM: set at 子, water; the yang stirs below the Yellow
      Springs; 子, though great yin, holds the yang within.
    - Names the 11th lunar month: the month holding 冬至 is the 11th,
      the weight the 暦文協 gave the solstice in 2033 (above).
    - Hangs on it: the day star turns to 陽遁 at the 甲子 nearest 冬至
      (`dayStar`).
23. **小寒 (shōkan)**, 十二月節, 285°; 5 Jan (77), 6 Jan (21), 4 Jan (2).
    - Marks: 寒の入り, the entry into the cold, which grows sharper
      (NAOJ-24); 寒 runs from this day to 節分 (K8); the greeting is
      寒中見舞い (NAOJ-W 二十四節気とは？).
    - KB: one yang rose at 冬至, and going against the yin 氣 it grows
      colder still. RM: set at 癸 (printed 亥), water; the month's
      beginning is the small cold, its middle the great.
    - Opens 丑月, the 12th solar month.
    - Hangs on it: in 丑月, for a person of an 丑 year 大禍日 辰, 狼藉日 酉,
      滅門日 戌; 往亡日 the 30th day; 天赦日 on 甲子. Under the 天保暦 the
      winter 土用 was counted from 小寒 (above, KB image 10).
24. **大寒 (daikan)**, 十二月中, 300°; 20 Jan (80), 19 Jan (15), 21 Jan
    (5).
    - Marks: the cold at its extreme (NAOJ-24); from here it warms, and
      spring is near (K8).
    - KB: the cold is at its extreme. RM: set at 丑, earth; the
      counterpart of 小寒, the great cold of mid-month; 丑 is the knot
      where the branches end and begin.
    - Names the 12th lunar month.
    - Hangs on it: the winter 土用 begins at 297°, about three days
      before 大寒 (about 17 Jan), and runs to 節分, the day before 立春.

**社日's tie.** When both 戊 days lie five days from the equinox, the
NAOJ records that the 宣明暦 era was confused, that the 貞享暦 to the
明治7 almanac took the earlier one, and that from the 明治14 almanac the
earlier is taken when the equinox falls before noon and the later when
after (NAOJ-W 雑節とは？, *社日*, citing a 国立公文書館 document). The app
takes the 戊 day whose noon is nearer the equinox instant (`shanichi`),
which gives the 明治14 rule. RM says only "near" (above). This answers
the expert's question 10 as far as the sources go.

**For the readings.** Every term has a printed gloss in KB and a
direction in RM. RM glosses the names of 雨水, 驚蟄, 清明, 穀雨, 小満, 芒種,
小暑, 大暑, 白露 (under 立秋's 候), 小雪, 大雪, 小寒 and 大寒; the four 立,
the solstices and the equinoxes only by the state of yin and yang; 処暑,
寒露 and 霜降 not at all. Both are public
domain and may be quoted whole; NAOJ and koyomi8 give the facts. Which
of the two old voices speaks first is the owner's (D8), and so is
whether RM's directions and elements belong in a reading at all within
the printed almanac's frame (SPEC §7).

### Open

The five questions on RM's print (小寒, 秋分, 小暑, 大暑, the 土用 passage)
are answered from the manuscript, pid 2533201, in K3-R's *For K6-R's
questions* (2026-10-15).

- Question: RM's 群書類從 print sets 小寒 «配于亥» (image 26, left page)
  while its own gloss says «故癸主十二月» and the series of directions
  asks 癸: is 亥 a misprint, and what does the manuscript (pid 2533201)
  read?
- Question: RM prints «故酉主地分» in 秋分's gloss (image 24, left page):
  is 地分 an error for 秋分?
- Question: RM prints «又小暑者、極熱之物也» (image 22, left page): is 物 an
  error, for 初 or the like ("the beginning of the great heat")?
- Question: RM prints «又大大暑月半極熱之中為大» (image 23, right page):
  is the doubled 大 a dittography, or «大» glossed and then «大暑» named?
- Question: RM's 土用 passage prints spring's first eighteen days with a
  glyph like 刀 where 寅 is expected (image 38, left page), and reads
  «秋金用時無禁金石» and «冬水用時無澤池堰»: what are the right readings of
  the two prohibitions?
- Question: KB's cursive endings, read by koyomi8 as «なれば也», were not
  resolved on the scan in 小暑, 大暑, 立秋, 秋分, 立冬, 大雪 and 冬至; and
  大暑's line shows «時節», which koyomi8's «暑気いたりつまりたるゆえんなれば
  也» lacks. What does the print read?
- Question: KB's 社日 and ひがん entries (image 9, right page) and its
  八十八夜, 入梅 and 二百十日 entries (images 9–10) beyond their first
  sentences: what do they say?
- Question: for the terms' readings, is KB (1787, the Edo almanac's own
  kana glosses, still quoted by koyomi8) or RM (1414, the 賀茂 house's
  kanbun) the voice to follow, the other as *Another view* (D8)?
- Question: RM's direction for each term (艮 for 立春, 寅 for 雨水 …): is
  it read by anyone today, in the printed almanac's 方位 or in 方違え, or
  is it a learned gloss only?

### Searched and not found

- K3-R's list of the 暦林問答集's 節気 passages: work/k3r had no commits
  on 2026-10-15 when this was read; the passages were found here.
- The NAOJ 暦Wiki page 「二十四節気」 at …/C6F3BDBDBBCDC0E1B5A4.html
  answers 404 (2026-10-15, browser); the page is 「季節/二十四節気とは？」.
- koyomi8.com/sub/24sekki.html answers 404 (2026-10-15, browser); the
  page is koyomi8.com/24doc.html.
- RM has no section on 節分, 彼岸, 八十八夜, 入梅, 半夏生 or 二百十日: its
  contents 上 (images 3–4) and 下 (images 36–37) list none; 土用 is its
  第二十五, 社日 its 第二十七, and 半夏生 occurs only as a 候 of 夏至.
- A classical Chinese gloss of each term's name on a scan: the NDL
  catalogue (`ndl.py`, 2026-10-15) has 月令七十二候集解 (元, 呉澄) only in
  modern reprints (叢書集成初編 and others) with no digitised copy among
  the hits; RM's glosses, which quote 鄭玄, the 三礼義宗 and the 易通卦驗,
  stand in for it.

## K4: three claims settled, 2026-10-19 (ROADMAP K4)

Read in the headed browser (the Playwright MCP) and, for the PDF, its
text layer; each quoted as printed.

**二十七宿 in Japan (question 4).** NAOJ 暦Wiki 二十八宿
(https://eco.mtk.nao.ac.jp/koyomi/wiki/C6F3BDBDC8ACBDC9.html, last
modified 2017-08-30), its section 二十七宿: «二十七宿はインドの占星術(とくにナクシャトラ)から宿曜経すくようきょうに組み込まれたもので、日の吉凶を占うものとして唐の時代に伝わってきました。二十八宿と比較すると、牛宿がありません。» and «朔日の宿はあらかじめ下表のように配当されており、朔日の後は (牛宿を除いて) 毎日順番に配当されます。» … «貞享暦以降は二十八宿が用いられています。»
The twenty-seven came from India through the 宿曜経 in the Tang, to
tell the luck of days; they lack 牛; each month's 1st has a fixed mansion
(正月 室, 二月 奎 … 十二月 虚) and the days run on from it; from the Jōkyō
calendar the twenty-eight are used. So the 27 were the almanac's day
mansions before 1685, against the expert's "never applied in Japan"; the
暦林問答集 of 1414 (K3-R, *What the app needs*) speaks of twenty-eight
with 牛 apart, the class the 宿曜経 lacks. The app shows the 28 of the
modern almanac and says nothing of the 27: nothing to change.

**The red writing of the 具注暦 (SPEC §10.4).** The spec's claim that the
red writing marks the 二十八宿 and the 七曜 had no source. 作花一志 (京都情報大学院大学), «曜日の起源と具注暦» (PDF, 2009,
https://www.kcg.ac.jp/kcg/sakka/kagakushi/youbi.pdf, linked from Japanese
Wikipedia 曜日), fig. 1: «承元四年八月（1210年9月）の具注暦の一部。上段に曜日が朱書されている。（京都大学宇宙物理学教室保管）» and the text: «日曜日に「密」という字が書かれているのはソグド語のミールに由来するものといわれる。»
In the 具注暦 of 1210 the weekday is written in red in the upper band,
密 on Sundays. Nothing read says the 二十八宿 were written in red: the
spec now says the weekday only. Japanese Wikipedia 具注暦 (read the same
day) says nothing of red writing.

**六曜 since the 19th century (question 13).** Japanese Wikipedia 六曜
(https://ja.wikipedia.org/wiki/六曜, read 2026-10-19), *歴史*: «現在と同じ六曜の初見は、神田茂の研究によれば延享4年（1747年）編と思われる『万暦両面鑑』である» ; «江戸時代末期の『安政雑書万暦大成』で現在と同様の六曜の吉凶解釈が出揃うが、江戸時代には六曜は数ある暦注の一つにすぎなかった。» ; *近代以降*: the 1872 notice that from 1873 the
略暦 carry no 中下段 annotations, and «政府が発行する官暦となった神宮暦も…吉凶の暦注は一切排除されるはずであったが、六曜と旧暦を略本暦に附すという形で存続した。…暦注追放を経て六曜はかえって重視されるようになったともいわれている»
The present six are first found in an almanac of 1747 (by 神田茂's
study); their present readings are complete only in the late Edo
安政雑書万暦大成; in the Edo period they were one annotation among many;
after the luck annotations were struck in 1873 they stayed beside the
official almanac and came to weigh more. This bears out the expert's
"popular since the 19th century"; the six's family note
(`reading.Rokuyo`) says it, with Wikipedia beside Todan as its source.
The page has no 六曜 entry in NAOJ's 暦Wiki (the 暦注 page lists none).

## K1: the Russian terms in the scholarly literature (ROADMAP K1)

**Built 2026-10-19** (block 51): the clear names below are in the app
(ROADMAP K1, *Built*); the numbered months, Вяткин's thirteen mansions
and 重陽の節句 wait on the owner (D15).

The established Russian names, from published Japanology and Sinology,
for the terms the expert marked (the month names, the 二十四節気, the
二十八宿, 暦注 and its bands, 下段, 選日, 縁日 and their days, 仏滅, the
dossier's band names of question 14, 人日の節句 and 社日 of K2): each
term with the work, page and year it comes from, in a table; a term with
no attested name keeps the app's and says so.

**Read 2026-10-15**, each work on its own page (the browser) or in its
PDF; only the words a name rests on are quoted. "App" is the Russian in
`texts_ru.properties` and `values-ru/strings.xml` on main 0baf42d.

### The works read

| Siglum | Work | Where read |
| --- | --- | --- |
| **БЯРС** | *Большой японско-русский словарь*, ed. Н. И. Конрад, «Советская энциклопедия», 1970, as digitized and revised by the WaRoDai project (its *О проекте*: the БЯРС word list digitized 2006–2008, revised since, about 96 000 entries) | https://warodai.ru/lookup/, each entry with its card number 〔…〕; licence CC BY-NC-ND 3.0, so its wording is never copied into the app, only the fact of a name |
| **Вяткин** | Сыма Цянь, *Исторические записки (Ши цзи)*, т. IV, transl., introduction and commentary Р. В. Вяткин, М., 1986 (Памятники письменности Востока, XXXII, 4) | the electronic copy at coollib.com (book 523632), which keeps the printed page numbers as [nn]; ch. 25 pp. 100–103, ch. 27 pp. 116–123, the introduction pp. 32–36, the *Аннотированный указатель астрономических терминов, названий звезд и созвездий* (no page marks in the copy) |
| **Еремеев** | В. Е. Еремеев, «Календарь», in *Духовная культура Китая*, т. 5, М.: Вост. лит., 2009, pp. 147–153 | synologia.ru, article *Календарь* (its printed source line names the volume and pages) |
| **Агеев 2012** | Н. Ю. Агеев, «О шести древних китайских календарях», *Общество и государство в Китае*, т. 42, вып. 3, 2012, pp. 225–237 | cyberleninka.ru, PDF; table 5 on pp. 232–233 |
| **Агеев 2003** | Н. Ю. Агеев, «24 сезона китайского календаря и их осмысление в ханьской традиции», IX Всероссийская конференция «Философии Восточно-Азиатского региона и современная цивилизация», М.: ИДВ РАН, 2003 | synologia.ru (no page numbers) |
| **Бреславец** | Т. И. Бреславец, «Адъективные формы старописьменного японского языка в поэтической речи», *Известия Восточного института*, 2002 | cyberleninka.ru, PDF p. 112 |
| **Степанова** | А. С. Степанова, «Гравюры „Ниппон“ и их прототипы в творчестве Кавахара Кэйга: праздники, обряды», *Вестник СПбГУ*, сер. 13, 2014, вып. 2 | cyberleninka.ru, PDF pp. 124, 128 |
| **Васютин** | С. А. Васютин, А. М. Мкртчян, «Повседневная жизнь городской семьи в эпоху сегуната Токугава…», *Вестник КемГУ*, 2008, № 2 | cyberleninka.ru, PDF p. 37 |
| **Садокова** | А. Р. Садокова, «Жанры японской народной календарно-обрядовой поэзии», 1989, № 3, from p. 109 | the archive of *Этнографическое обозрение*, eo.iea.ras.ru, PDF p. 111 |

### The month names (睦月 … 師走) and the numbered months

No Russian work found translates the twelve old names as a set. The
dictionary gives each as "the Nth month by the lunar calendar" and leaves
the name in transcription; one name has a translation in print.

| Term | App now | Attested | Note |
| --- | --- | --- | --- |
| 睦月 | месяц привязанности | БЯРС 〔006-12-65〕 «первый месяц по лунному календарю» | no translation of the name found |
| 如月 | месяц многослойных одежд | БЯРС 〔000-05-68〕 «второй месяц [по лунному календарю]» | none found |
| 弥生 | месяц роста | БЯРС 〔003-58-03〕 «третий месяц по лунному календарю» | none found |
| 卯月 | месяц цветов дейции | БЯРС 〔004-42-60〕 «четвёртый месяц …» | none found |
| 皐月 | месяц посадки риса | БЯРС 〔007-29-96〕 «пятый месяц …»; its synonym 早苗月 〔006-05-45〕 is only cross-referred | none found |
| 水無月 | месяц воды | БЯРС 〔008-96-79〕 «шестой месяц …» | none found |
| 文月 | месяц писем | БЯРС 〔005-13-29〕 «седьмой месяц …» | none found |
| 葉月 | месяц листьев | БЯРС 〔001-76-87〕 «восьмой месяц …» | none found |
| 長月 | месяц долгих ночей | БЯРС 〔007-19-66〕 «„долгая луна“ (название 9-го месяца …)» | the one name the dictionary translates |
| 神無月 | месяц богов | БЯРС 〔004-47-27〕 «десятый месяц …»; Бреславец p. 112, in a translation of 古今集 314: «Каннадзуки — В „месяц без богов“» | the printed reading is "without gods"; the app's «месяц богов» drops the 無 |
| 霜月 | месяц инея | БЯРС 〔003-49-95〕 «кн. одиннадцатый месяц …» | none found |
| 師走 | месяц, когда бегут учителя | БЯРС 〔006-62-31〕 «двенадцатый месяц …; поэт. последний месяц года» | none found |
| the numbered month (正月, 二月 …) | «N-й месяц» (`month`) | «N-я луна»: Вяткин ch. 25, pp. 100–103 («связано с десятой луной», «с первой луной»); Степанова p. 128 («в пятый день пятой луны»); Васютин p. 37 («третий день третьей луны»). «N-й месяц»: Садокова p. 111 («1-й месяц», «5-й или 6-й месяц»); БЯРС «N-й месяц по лунному календарю» | both in use; «луна» is the usage of the translators of old texts, «месяц» of the dictionary and the ethnographer |
| 閏月 | «Високосный N-й месяц» (`month_leap`) | Еремеев: «вставной месяц» (жунь юэ), the thirteen-month year «високосный (жунь суй)»; Агеев 2012 p. 225: «вставные месяцы (жуньюэ)» | the sinologists call the month «вставной» and only the year «високосный» |

The expert's question 12 («одной этимологии», which one?) is not
answered by any of these: none gives an etymology but 長月 and 神無月's
literal sense.

### The twenty-four solar terms (二十四節気)

The set name: «двадцать четыре сезона сельскохозяйственного года (по
старому яп. и кит. календарю)» (БЯРС 〔005-13-50〕, under 二十四気; 節気
alone «сезон», 〔007-52-80〕); «24 „сезона“, называемых цзе ци» (Еремеев,
table 2); «двадцать четыре сезона (эршисы ци)» (Агеев 2003); «24
солнечных сезона» (Агеев 2012, p. 226). The 節 and 中: «12
разделителей-цзеци и 12 середин-чжунци» (Агеев 2012, p. 227), «чжун ци
(„средние пневмы“)» (Еремеев). The app's «сезон» agrees with all of them.

| Term | App now | БЯРС (card) | Еремеев (table 2) | Агеев 2012 (table 5, pp. 232–233) | Note |
| --- | --- | --- | --- | --- | --- |
| 立春 | начало весны | «начало весны» 〔005-01-22〕 | Начало весны | Становление весны | app attested |
| 雨水 | дождевая вода | дождевая вода, "name of the second season of spring" 〔003-86-64〕 | Дождевая вода | Дождевая вода | app attested |
| 啓蟄 | пробуждение насекомых | «пробуждение личинок» 〔006-23-39〕 | Пробуждение личинок | Пробуждение насекомых | both in use; app = Агеев |
| 春分 | весеннее равноденствие | «весеннее равноденствие» 〔003-50-22〕 | Весеннее равноденствие | Весеннее равноденствие | app attested |
| 清明 | чистый свет | «ясный свет» 〔008-67-64〕 | Чистый свет | Чистота и ясность | app = Еремеев |
| 穀雨 | хлебный дождь | «дожди [для] злаков» 〔002-42-37〕 | Хлебные дожди | Дожди для злаков | app's singular not attested; «хлебные дожди» or «дожди для злаков» |
| 立夏 | начало лета | «начало лета» 〔006-27-89〕 | Начало лета | Становление лета | app attested |
| 小満 | малое наполнение зерна | «малая полнота» 〔006-47-04〕 | Малая полнота | Малое насыщение | app's not attested; «малая полнота» in two works |
| 芒種 | колошение | «полный колос» 〔003-35-67〕 | Колосящиеся всходы | Колошение хлебов | three names; app's is the short form of Агеев's |
| 夏至 | летнее солнцестояние | летнее солнцестояние 〔000-88-82〕 | Летнее солнцестояние | Летнее солнцестояние | app attested |
| 小暑 | малая жара | «малая жара» 〔001-41-26〕 | Малая жара | Малая жара | app attested |
| 大暑 | большая жара | большая (сильная) жара 〔001-01-64〕 | Большая жара | Большая жара | app attested |
| 立秋 | начало осени | «начало осени» 〔009-52-25〕 | Начало осени | Становление осени | app attested |
| 処暑 | конец жары | «окончание жары» 〔009-53-56〕 | Последняя жара | Прекращение жары | three names, the app's a fourth |
| 白露 | белые росы | поэт. белая роса 〔008-79-06〕 | Белые росы | Белые росы | app attested |
| 秋分 | осеннее равноденствие | «осеннее равноденствие» 〔001-34-57〕 | Осеннее равноденствие | Осеннее равноденствие | app attested |
| 寒露 | холодные росы | «холодная роса» 〔001-84-34〕 | Холодные росы | Холодные росы | app attested |
| 霜降 | выпадение инея | «выпадение инея» 〔008-98-88〕 | Появление инея | Выпадение инея | app attested |
| 立冬 | начало зимы | «начало зимы» 〔009-01-71〕 | Начало зимы | Становление зимы | app attested |
| 小雪 | малый снег | «малый снег» 〔006-87-22〕 | Малые снега | Малые снега | both in use |
| 大雪 | большой снег | кн. большой снегопад 〔004-68-76〕 | Большие снега | Большие снега | «большие снега» in two works |
| 冬至 | зимнее солнцестояние | зимнее солнцестояние 〔005-58-91〕 | Зимнее солнцестояние | Зимнее солнцестояние | app attested |
| 小寒 | малые холода | «малые холода» 〔004-68-60〕 | Малые холода | Малые холода | app attested |
| 大寒 | большие холода | сильные холода 〔007-65-80〕 | Большие холода | Большие холода | app attested |

Агеев 2012 calls the four 立 «становление», БЯРС and Еремеев «начало»;
Агеев 2012 p. 231 and Еремеев both begin the series at 立春. The
dictionary's dates are Gregorian approximations and are not used.

### The twenty-eight lunar mansions (二十八宿)

The expert: "there are established translations among the sinologists".
The one full set read is Вяткин's, in three places of the same volume:
the introduction (pp. 34–36, the mansions of the four palaces), the
translation of ch. 27 (pp. 116–123) and the annotated index of
astronomical terms. The set name there is «28 сю», «зодиакальные
созвездия» and, quoting Pannekoek, «лунные станции» (introduction,
pp. 32–33); elsewhere «28 созвездий (или „28 стоянок“, эрши басю)» (П. С.
Чирич, «Изображение планет в ранней китайской живописной традиции…»,
*Вестник культурологии*, 2025, № 2 (113), p. 10, after Кобзев 2009,
p. 97), «семи „стоянок Луны“ (созвездий на эклиптике)» (Н. А. Сомкина,
«Сышэнь — духи-стражи сторон света», *Вестник СПбГУ. Востоковедение и
африканистика*, 2025, т. 17, вып. 2, p. 344), and «двадцать восемь
частей небесной сферы вдоль эклиптики» (БЯРС 〔003-52-85〕). The app's
«лунная стоянка» / «28 стоянок» is attested.

| Term | App now | Вяткин: introduction | Вяткин: ch. 27 | Вяткин: index | Note |
| --- | --- | --- | --- | --- | --- |
| 角 | Рог | Цзюэ «Рог» | «Рог», p. 117 | «Рог», или «Угол» | app attested |
| 亢 | Шея | Ган «Шея» | «Шея», p. 117 | — | app attested |
| 氐 | Основание | Ди «Основа» | «Основа», p. 117 | — | Вяткин «Основа» |
| 房 | Горница | Фан (untranslated) | «Дом», p. 116 | «Дом» | Вяткин «Дом»; «Горница» not found |
| 心 | Сердце | Синь (untranslated) | «Сердце», p. 116; «Душа» in ch. 25, p. 102 | «Сердце», или «Душа» | app attested |
| 尾 | Хвост | Вэй «Хвост» | «Хвост», p. 117 | — | app attested |
| 箕 | Веялка | Цзи «Корзина» | «Корзина», p. 117 | «Корзина» | Вяткин «Корзина» |
| 斗 | Ковш | Доу «Ковш» | «Ковш», p. 122 | «Ковш» | app attested |
| 牛 | Бык | Ню «Бык» | Цянь-ню «Воловод», p. 122 | — | app attested |
| 女 | Дева | Нюй «Дева» | Унюй «Служанка», p. 121 | Унюй-сю «Служанка» (also Сюйнюй, Нюй) | app attested; the full name is «Служанка» |
| 虚 | Пустота | Сюй «Пустота» | «Пустота», p. 120 | «Пустота» | app attested |
| 危 | Крыша | Вэй «Кровля» | «Кровля», p. 120 | — | Вяткин «Кровля» |
| 室 | Стан | Ши «Дом» | Ин-ши «Построенный дом», p. 121 | Ин-ши-сю «Построенный дом» | Вяткин «Дом»; «Стан» not found |
| 壁 | Стена | Би «Стена» | Дун-би «Восточная стена», p. 122 | «Восточная стена», или «Стена» | app attested |
| 奎 | Ноги | Куй «Скипетр» | «Скипетр», p. 119 | «Скипетр» | Вяткин «Скипетр»; «Ноги» not found |
| 婁 | Узы | Лоу «Оковы» | «Оковы», p. 119 | — | Вяткин «Оковы» |
| 胃 | Желудок | Вэй «Желудок» | «Желудок», p. 119 | — | app attested |
| 昴 | Волосатая голова | Мао «Утиное гнездо» | «Утиное гнездо», p. 123 | «Утиное гнездо» (also Лю-сю «Накопление») | Вяткин «Утиное гнездо» |
| 畢 | Сеть | Би «Вилы» | «Вилы», p. 119 | — | Вяткин «Вилы» |
| 觜 | Клюв черепахи | Цзуй «Морда» | Цзуй-хуэй «Морда», p. 119 | Цзуйхуэй-сю «Морда» | Вяткин «Морда» |
| 参 | Три звезды | Шэнь «Звезда заслуг» | «Заслуги», p. 119 | Шэнь-сю «Заслуги» | Вяткин «Заслуги» |
| 井 | Колодец | Цзин «Колодец» | «Колодец», p. 118 | «Колодец» (also «Восточный колодец») | app attested |
| 鬼 | Дух | Гуй «Духи» | Юй-гуй «Духи людей», p. 118 | — | Вяткин «Духи» |
| 柳 | Ива | Лю «Ива» | «Изгиб», p. 118 | «Изгиб», или «Ива» | app attested |
| 星 | Звезда | Цисин «Семизвездие» | Ци-син «Семизвездие», p. 118 | «Звезда», или «Семизвездие» | app attested; «Семизвездие» the fuller |
| 張 | Растянутая сеть | Чжан «Лук» | «Натянутый лук», p. 118 | «Лук», или «Натянутый лук» | Вяткин «Натянутый лук» |
| 翼 | Крылья | И «Крыло» | «Крыло», p. 118 | — | Вяткин singular «Крыло» |
| 軫 | Колесница | Чжэнь «Повозка» | «Колесница», p. 118 | «Повозка», или «Колесница» | app attested |

Of the 28, fifteen of the app's names stand in Вяткин (角 亢 心 尾 斗 牛 女
虚 壁 胃 井 柳 星 軫, and 氐 as «Основа» beside the app's «Основание»);
房, 箕, 危, 室, 奎, 婁, 昴, 畢, 觜, 参, 鬼, 張, 翼 have other names there.
The app's «Горница», «Веялка», «Стан», «Ноги», «Узы», «Волосатая голова»,
«Сеть», «Клюв черепахи», «Три звезды», «Растянутая сеть» were found in
no Russian work read. Only one sinologist's full set was read; whether
the expert means his or another (Кобзев's, Еремеев's in *Духовная
культура Китая*) is a question below. No Russian Japanology was found
that names the mansions as the Japanese almanac prints them.

### 暦注 and its bands

No Russian work read names 暦注, 暦注下段, 中段, 十二直, 選日 or 雑節 as
calendar terms; БЯРС has no entry for 暦注, 十二直, 選日 or 雑節, and gives
中段 and 下段 only in their everyday senses (〔000-23-30〕 «площадка
лестницы», 〔002-73-04〕 «нижняя ступенька; нижняя полка»). 縁日 is the
exception.

| Term | App now | Attested | Note |
| --- | --- | --- | --- |
| 暦注 | «Пометки альманаха» (`about_rekichu`) | none found | the expert's «календарные примечания» is her proposal, not found in print |
| 暦注下段 / 下段 | «нижняя полоса» (`section_kagedan`) | none found as a calendar term | keeps the app's |
| 中段 / 十二直 | «Двенадцать станций» (`section_choku`, `desc_choku_dial`) | none found; the twelve (建 … 閉) as day names found only on fortune-telling sites, not read | keeps the app's |
| 選日 | «Избранные дни» (`section_senjitsu`) | none found | keeps the app's |
| 縁日 | «Дни божеств» (`section_ennichi`) | БЯРС 〔005-63-42〕 «буд. [храмовый] праздник»; А. Ю. Синицын, А. И. Габитова, «О традиционном японском календарном празднике „День Косин“…», *Этнография*, 2023, № 4 (22), p. 237: «календарных храмовых праздников» | the dictionary's word is «храмовый праздник» |
| 雑節 | «Сезонные вехи» (`section_zassetsu`) | none found | keeps the app's |
| 吉日 / 凶日 (the tones) | хороший / плохой день | БЯРС 〔001-65-21〕 «счастливый (благоприятный) день», 〔007-40-18〕 «чёрный (несчастливый) день» | in passing |

The 雑節 one by one, where the dictionary has them (none is in any other
work read but 節分 and 彼岸):

| Term | App now | Attested | Note |
| --- | --- | --- | --- |
| 節分 | Разделение сезонов | БЯРС 〔005-50-51〕 «смена времён года; канун весны»; Степанова p. 124: «дословно переводится как „разделение сезонов“» | app attested |
| 彼岸 | Хиган, неделя равноденствия | БЯРС 〔007-37-14〕 «неделя равноденствия; буд. хига́н» | app attested |
| 彼岸入り / 彼岸明け | Начало хиган / Конец хиган | БЯРС 〔008-09-21〕 «начало недели равноденствия», 〔006-77-49〕 «конец недели равноденствия» | close |
| 社日 | День божества земли | none found (no БЯРС entry; nothing in sinology read) | keeps the app's (K2) |
| 八十八夜 | Восемьдесят восьмая ночь | БЯРС 〔009-03-81〕 «восемьдесят восьмой день (букв. ночь) с начала весны» | the dictionary counts it a day |
| 入梅 | Начало сезона дождей | БЯРС 〔007-77-09〕 «день начала сезона дождей» | app attested |
| 半夏生 | Всходы пинеллии | БЯРС 〔008-36-03〕 «хангэсё (название народного праздника …)»; its sense 2 is the plant «(тж. 三白花) заурурус, Saururus chinensis» | transcription only; the dictionary's plant is not the app's pinellia (半夏) |
| 土用 | Доё, сезон земли | БЯРС 〔008-90-41〕 «доё: последние 18–20 дней каждого времени года» | the transcription is the dictionary's |
| 二百十日 | Двести десятый день | БЯРС 〔008-53-00〕 «двести десятый день от начала весны» | app attested |

### 六曜

| Term | App now | Attested | Note |
| --- | --- | --- | --- |
| 六曜 (the cycle) | рокуё | БЯРС, in each day's entry: «название дня шестидневки по лунному календарю»; М. Н. Корнилов, abstract of F. Coulmas, *Japanische Zeiten* (2000), *Афро-азиатский мир: проблемы цивилизационного анализа*, 2004, p. 127: «шестидневные циклы (рокуё)» | «шестидневка» is the dictionary's word |
| 先勝 | выигрывает первый | БЯРС 〔000-15-26〕 сэнсё: «день, благоприятный для дел»; Корнилов p. 127 «сэнкати» | transcription; no translation of the name in scholarship |
| 友引 | ничья между друзьями | БЯРС 〔004-49-73〕 томобики: «злосчастный день …» | as above |
| 先負 | проигрывает первый | БЯРС 〔005-93-51〕 сэмбу: «день, неблагоприятный для дел» | as above |
| 仏滅 | ничего не выйдет | БЯРС 〔006-80-22〕 буцумэцу: «1) кончина Будды; 2) см. ぶつめつにち», 〔002-29-93〕 «буд. несчастливый день»; Корнилов p. 127 «буцумэцу» | the literal sense in print is «кончина Будды»; the expert's «смерть Будды» only in the popular source below |
| 大安 | великий покой | БЯРС 〔001-97-95〕 тайан: «уст. счастливый (удачный) день»; Корнилов p. 127 «тайан» | as above |
| 赤口 | красный рот | БЯРС 〔004-91-69〕 сякку (赤口日): «несчастливый день» | as above |

A popular witness, not scholarship: Nippon.com, «Рокуё: счастливые и
несчастливые дни японского календаря» (27.10.2025,
https://www.nippon.com/ru/guide-to-japan/cs00041/), glosses the six as
«победа первому», «привлечь друзей», «поражение первому», «смерть
Будды», «Великий мир», «красный рот». Its companion page *Японский
календарь* (25.02.2026, https://www.nippon.com/ru/japan-glances/jg00122/)
glosses the month names in sentences («Месяц цветения дейции», «Месяц
воды», «„Месяц без богов“», «„Учителя бегают“») and the 節気 much as the
table above; it is the only Russian text found that glosses every month.

### 人日の節句 and the other festivals

| Term | App now | Attested | Note |
| --- | --- | --- | --- |
| 人日の節句 | праздник дня людей | БЯРС 〔004-88-77〕 «дзи́ндзицу (один из пяти традиционных праздников госэ́кку, проводится в седьмой день первого месяца …)»; Э. В. Молодякова, «Ритуал в синтоистской традиции», *Ежегодник Япония*, 2008 (read in the HTML text, the PDF's font not extractable): «пяти сезонных праздников госэкку — дзиндзицу, хинамацури, танго-но сэкку, танабата, теесики» | attested only in transcription, «дзиндзицу»; no translation found |
| 五節句 | — | БЯРС 〔006-69-67〕 «пять традиционных праздников»; Молодякова «пяти сезонных праздников госэкку» | for the set |
| 社日 | день божества земли | none found | keeps the app's |
| 重陽の節句 | праздник двойной девятки | БЯРС 〔008-53-71〕 «праздник хризантем»; Васютин p. 37 «девятый день девятой луны (праздник хризантем)»; Н. Н. Изотова, «Напиток богов…», *Вестник ТГУ. Культурология и искусствоведение*, 2020, № 39, p. 27 «праздник, посвященный хризантемам» | the expert's «двойной девятки» (K2) not found in print; «праздник хризантем» in three |
| 七夕 | Танабата | БЯРС 〔007-09-76〕 «„праздник Ткачихи“»; Васютин p. 37 «праздник влюблённых» | the transcription is the expert's (K2) |
| 端午 / 上巳 | праздник первого дня Лошади / Змеи | БЯРС 〔007-35-26〕 «„праздник мальчиков“», 〔006-84-82〕 «праздник девочек»; Степанова p. 128 «Танго-но-сэкку»; Васютин p. 37 «праздник мальчиков», «праздник кукол или праздник девочек» | in passing: the app's literal names not found |
| 十三夜 | тринадцатая ночь, поздняя луна | БЯРС 〔001-69-98〕 «„тринадцатая ночь“» | app attested |
| 旧暦 | Кюрэки | БЯРС 〔000-53-93〕 «старый (лунный) календарь» | in passing |
| 庚申 | Обезьяна Металла ян (косин) | Синицын, Габитова pp. 237, 242: «День Косин», «Год Косин»; БЯРС 〔002-02-30〕 «„год обезьяны“ (по 60-летнему циклу)» | in passing |

### The dossier's band names of question 14

| Name in the dossier | For | Attested | Note |
| --- | --- | --- | --- |
| «двенадцать станций» | 十二直 | none found | keeps the app's |
| «лунная стоянка» | 二十八宿 | «28 стоянок» (Чирич p. 10), «стоянок Луны» (Сомкина p. 344); «лунные станции» (Вяткин pp. 32–33, after Pannekoek); «28 сю», «зодиакальные созвездия» (Вяткин) | attested |
| «нижняя полоса» | 下段 | none found | keeps the app's |
| «избранные дни» | 選日 | none found | keeps the app's |
| «дни божеств» | 縁日 | «храмовый праздник» (БЯРС), «календарные храмовые праздники» (Синицын, Габитова p. 237) | another name in print |
| «сезонные вехи» | 雑節 | none found | keeps the app's |
| «принятие смерти» | 受死日 | none found (no БЯРС entry) | keeps the app's |
| «нисходящий пожиратель года» | 歳下食 | none found (no БЯРС entry) | keeps the app's |
| «Тэнъити на небесах» | 天一天上 | none found (no БЯРС entry for 天一天上 or 天一) | keeps the app's |

So of the expert's "some have established translations", the read works
bear out «стоянка» for the mansions, «сезон» for the 節気, «кончина
Будды» for 仏滅's literal sense, «месяц без богов» for 神無月 and
«храмовый праздник» for 縁日; for the bands of the lower almanac (下段,
中段, 選日, 雑節) and its named days she was right that nothing is found in
Russian.

### Open

- Question: the expert wrote that the twenty-eight mansions have
  established translations among the sinologists. Вяткин's set (Ши цзи,
  vol. IV, 1986) is the one read; is it the one she means, or another
  (Кобзев's tables, Еремеев's or another article of *Духовная культура
  Китая*)?
- Question: is «календарные примечания» for 暦注 attested in a
  published work she can name, or her own proposal?
- Question: for the numbered months, does Japanology prefer «N-я луна»
  (the translators of the chronicles and of Хэйан prose) or «N-й месяц»
  (БЯРС, Садокова) for the 旧暦 page?
- Question: the twelve old month names are translated as a set in no
  work found; does a Russian Japanologist's commentary (to 古今集, 枕草子
  or 日本書紀) translate them, and which etymology did «одной
  этимологии» mean?
- Question: Н. И. Фельдман, «Японский календарь» (*Народы Азии и
  Африки*, 1970, № 4) and В. В. Цыбульский, *Лунно-солнечный календарь
  стран Восточной Азии* (М., 1987), cited in Еремеев's bibliography, may
  name the 暦注 and the 六曜 in Russian; where can they be read?

### Searched and not found

- cyberleninka.ru (its search API, 2026-10-15): «гутюрэки» (0),
  «кюрэки» (0), «дзюнитёку» (0), «28 сю» (0), «шэ жи» (0), «сюкуё»
  (only Трубникова 2023, a sūtra's title), «буцумэцу» (only Корнилов
  2004), «оммёдо календарь», «календарные приметы Японии», «коёми»,
  «сэкку», «госэкку», «дзиндзицу», «муцуки», «каннадзуки», «цзянь чу»,
  «жи шу», «тун шу альманах», «хуанли»: no Russian name for 暦注, its
  bands, 十二直, 選日, 雑節, 社日 or the named days of 下段.
- synologia.ru: the articles «Эр ши ба сю», «Сю», «Цзе ци», «Эр ши сы
  ци», «Шэ», «Шэ цзи», «Цзянь чу», «Жэнь жи», «Тянь и», «Тай и», «Тай
  суй», «Шэнь ша», «Тун шу», «Хуан ли», «Двадцать восемь созвездий»
  are "not yet written"; «Календарь» (Еремеев) and «Гань чжи» (Кравцова)
  exist.
- warodai.ru (БЯРС): no entry for 暦注, 六曜, 十二直, 選日, 雑節, 社日,
  受死日, 歳下食, 天一天上, 八専, 三隣亡, 一粒万倍日, 不成就日, 天赦日,
  母倉, 大明, 往亡, 帰忌, 血忌, 大禍日, 狼藉日, 滅門日, 三箇の悪日, 角宿,
  鬼宿, 亢宿; 中段, 下段, 十死 («смертельная опасность») and 十方暮 («небо,
  со всех сторон обложенное тучами») only in senses outside the
  calendar.
- abirus.ru's copy of Вяткин's index did not load (timeout,
  2026-10-15); the same volume was read at coollib.com.
- The 1989 volume *Календарные обычаи и обряды народов Восточной Азии.
  Годовой цикл* (М., 1989), Фельдман 1970 and Цыбульский 1987: no full
  text found online (WebSearch, 2026-10-15).
- Воркина, Кодама, Саркисова, «Японские календарные системы и их
  влияние на приметы и традиции народа» (*Современная наука… Серия:
  Познание*, 2019, № 12, pp. 5–8; on the 六曜): only the RUDN
  repository's record, no text.
- WebSearch in Russian for the 十二直 (建除), 社日 and 暦注 terms: only
  fortune-telling and travel sites, not read.
