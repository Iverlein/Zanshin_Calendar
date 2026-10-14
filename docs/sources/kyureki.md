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
  (*shūbun*, *shunbun*). 社日: "?".
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
