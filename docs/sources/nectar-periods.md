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
