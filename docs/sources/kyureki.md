# The kyūreki gaps

The three 旧暦 questions of [PLAN.md](PLAN.md) task 7, with what was found on
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
