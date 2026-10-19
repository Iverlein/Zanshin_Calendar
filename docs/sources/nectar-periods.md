# Jupiter's nectar periods

Sigla and the rules for quoting are in [README.md](README.md).

KP §10 (img. 81–82, read on the scan 2026-10-05; the book's second list of
contents gives the images as 81–82 too): two tables, by day and by night,
of the *bdud rtsi thun mtshams*, "the nectar periods". Many activity boxes
name them among the good times ([kp-activities.md](kp-activities.md):
boxes 17, 19, 26, 34, 49, 55, 60).

## The tables

Each table has a row for each weekday (ཉི་མ ༡, ཟླ་བ ༢, མིག་དམར ༣, ལྷག་པ ༤,
ཕུར་བུ ༥, པ་སངས ༦, སྤེན་པ ༠: the planets numbered as the weekdays, Saturn
0) and twelve columns, the halves (སྔ་ཆ, ཕྱི་ཆ) of six named double hours:

- by day (ཉི་མོའི་ཁྲམ): ནངས, ཉི་ཤར, ཉི་དྲོས, ཉི་ཕྱེད, ཕྱེད་ཡོལ, ཉི་མྱུར;
- by night (མཚན་མོའི་ཁྲ་མ): ཉི་ནུབ, ས་སྲོས, སྲོད་འཁོར, ནམ་ཕྱེད, ཕྱེད་ཡོལ, ཐོ་རེངས.

Each cell holds the number of the planet that rules that half. Jupiter's
cells are written བདུད་རྩི (nectar) instead of ༥, and Saturn's carry a word
of their own, not read here. The headings name the rule: «ཕྱེད་བདག་ཕུར་བུའི་
བདུད་རྩི་ཐུན་མཚམས་ཉིན་མོ་དྲུག་སྐོར་ཞེས་པ་གཟའ་རྣམས་དྲུག་ཏུ་སྐོར་བ», the
lord of the half being Jupiter makes the nectar period; by day the planets
go round "in sixes", by night (img. 82) «ལྔར་སྐོར་བ», "in fives".

Read on the scan, every row bears this out. A row starts with the
weekday's own planet in the first half of dawn, and each next half's
planet is the sixth counted from the one before (five on, mod 7, in the
weekday order); by night the first half of sunset is again the weekday's
planet, and each next half the fifth counted (four on). Sunday by day runs
1, 6, 4, 2, Saturn, nectar, 3, 1, 6, 4, 2, Saturn; by night 1, nectar, 2,
6, 3, Saturn, 4, 1, nectar, 2, 6, 3. The nectar cells, by column:

| Weekday | By day | By night |
| --- | --- | --- |
| Sunday | 6 | 2, 9 |
| Monday | 3, 10 | 7 |
| Tuesday | 7 | 5, 12 |
| Wednesday | 4, 11 | 3, 10 |
| Thursday | 1, 8 | 1, 8 |
| Friday | 5, 12 | 6 |
| Saturday | 2, 9 | 4, 11 |

`NectarPeriodsTest` checks the rule against this table.

## The clock

The twelve named double hours run in order from dawn, so with midnight
(ནམ་ཕྱེད) on the mouse hour and noon (ཉི་ཕྱེད) on the horse hour they are
the twelve hours the app already draws from the hare hour at 05:00 (SPEC
§10.3, after Berzin): ནངས the hare hour, ཉི་ཤར the dragon, … ཐོ་རེངས the
tiger. Each half is then one clock hour. The app gives the day's nectar
hours in the Almanac section; the halves of Saturn, which the heading ties
to the rising of *rigs ldan* (the poison time of the medicine box), are not
built.

## The tables written out

(O10, 2026-10-13.) The two tables are BDRC's files I4CZ655990083.jpg and
…0084.jpg, the 83rd and 84th of the volume's images: the image numbers
above count two fewer, as for KP's other boxes (img. 81–82 hold the
combination period's table, [combination-period.md](combination-period.md)).
Read on the scan cell by cell at 1.6× and the doubtful cells at 3×, each
row checked against the rule. «བདུད་རྩི» is printed in Jupiter's cells;
[S] marks Saturn's cells, each a short word in a small hand that was not
read; the long title over each table is read only as far as quoted above.

By day (ཉི་མོའི་ཁྲམ), img. 83:

| | ནངས་སྔ་ཆ | ནངས་ཕྱི་ཆ | ཉི་ཤར་སྔ་ཆ | ཉི་ཤར་ཕྱི་ཆ | ཉི་དྲོས་སྔ་ཆ | ཉི་དྲོས་ཕྱི་ཆ | ཉི་ཕྱེད་སྔ་ཆ | ཉི་ཕྱེད་ཕྱི་ཆ | ཕྱེད་ཡོལ་སྔ་ཆ | ཕྱེད་ཡོལ་ཕྱི་ཆ | ཉི་མྱུར་སྔ་ཆ | ཉི་མྱུར་ཕྱི་ཆ |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| ཉི་མ་༡ | ༡ | ༦ | ༤ | ༢ | [S] | བདུད་རྩི | ༣ | ༡ | ༦ | ༤ | ༢ | [S] |
| ཟླ་བ་༢ | ༢ | [S] | བདུད་རྩི | ༣ | ༡ | ༦ | ༤ | ༢ | [S] | བདུད་རྩི | ༣ | ༡ |
| མིག་དམར་༣ | ༣ | ༡ | ༦ | ༤ | ༢ | [S] | བདུད་རྩི | ༣ | ༡ | ༦ | ༤ | ༢ |
| ལྷག་པ་༤ | ༤ | ༢ | [S] | བདུད་རྩི | ༣ | ༡ | ༦ | ༤ | ༢ | [S] | བདུད་རྩི | ༣ |
| ཕུར་བུ་༥ | བདུད་རྩི | ༣ | ༡ | ༦ | ༤ | ༢ | [S] | བདུད་རྩི | ༣ | ༡ | ༦ | ༤ |
| པ་སངས་༦ | ༦ | ༤ | ༢ | [S] | བདུད་རྩི | ༣ | ༡ | ༦ | ༤ | ༢ | [S] | བདུད་རྩི |
| སྤེན་པ་༠ | [S] | བདུད་རྩི | ༣ | ༡ | ༦ | ༤ | ༢ | [S] | བདུད་རྩི | ༣ | ༡ | ༦ |

By night (མཚན་མོའི་ཁྲ་མ), img. 84:

| | ཉི་ནུབ་སྔ་ཆ | ཉི་ནུབ་ཕྱི་ཆ | ས་སྲོས་སྔ་ཆ | ས་སྲོས་ཕྱི་ཆ | སྲོད་འཁོར་སྔ་ཆ | སྲོད་འཁོར་ཕྱི་ཆ | ནམ་ཕྱེད་སྔ་ཆ | ནམ་ཕྱེད་ཕྱི་ཆ | ཕྱེད་ཡོལ་སྔ་ཆ | ཕྱེད་ཡོལ་ཕྱི་ཆ | ཐོ་རེངས་སྔ་ཆ | ཐོ་རེངས་ཕྱི་ཆ |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| ཉི་མ་༡ | ༡ | བདུད་རྩི | ༢ | ༦ | ༣ | [S] | ༤ | ༡ | བདུད་རྩི | ༢ | ༦ | ༣ |
| ཟླ་བ་༢ | ༢ | ༦ | ༣ | [S] | ༤ | ༡ | བདུད་རྩི | ༢ | ༦ | ༣ | [S] | ༤ |
| མིག་དམར་༣ | ༣ | [S] | ༤ | ༡ | བདུད་རྩི | ༥ | ༦ | ༣ | [S] | ༤ | ༡ | བདུད་རྩི |
| ལྷག་པ་༤ | ༤ | ༡ | བདུད་རྩི | ༢ | ༦ | ༣ | [S] | ༤ | ༡ | བདུད་རྩི | ༢ | ༥ |
| ཕུར་བུ་༥ | བདུད་རྩི | ༢ | ༦ | ༣ | [S] | ༤ | ༡ | བདུད་རྩི | ༢ | ༦ | ༣ | [S] |
| པ་སངས་༦ | ༦ | ༣ | [S] | ༤ | ༡ | བདུད་རྩི | ༢ | ༦ | ༣ | [S] | ༤ | ༡ |
| སྤེན་པ་༠ | [S] | ༤ | ༡ | བདུད་རྩི | ༢ | ༦ | ༣ | [S] | ༤ | ༡ | བདུད་རྩི | ༢ |

Two cells of the night table break the rule: Tuesday's sixth half
(སྲོད་འཁོར་ཕྱི་ཆ), where the count gives ༢, and Wednesday's last (ཐོ་རེངས་ཕྱི་ཆ), where
it gives ༦, both print ༥, Jupiter's number, as a plain digit and not as
«བདུད་རྩི». The nectar cells of the table above are those of the rule; the two
༥ are not counted among them.

### Open

- Open question 144 ([open-questions.md](open-questions.md)), answered 2026-10-19 (below).

## The two ༥ against KP2, 2026-10-19 (open question 144)

The second print (KP2, BDRC I3CN12074) carves the night table on
img. 307 (2750 px, read at 1.5–2×; the day table is img. 306), headed
«མཚན་མོའི་ཁྲམ» as in KP. In Tuesday's row (མིག་དམར་༣) the cell after the
nectar of སྲོད་འཁོར་སྔ་ཆ, and in Wednesday's row (ལྷག་པ་༤) the last cell, KP2
has the shapes its own ༢ and ༦ take everywhere else in the table (Sunday's
row, «༡ བདུད་རྩི ༢ ༦ ༣ …», read beside them): ༢ for Tuesday and ༦ for
Wednesday, as the count by fives gives. So the two plain ༥ of KP (img. 84)
are slips of that print's carver, not halves ruled by Jupiter; the
table above stands as the rule makes it.
