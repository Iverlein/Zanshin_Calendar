# The burning dates (*bsreg tshes*)

Sigla and the rules for quoting are in [README.md](README.md). Read on
2026-10-08 for ROADMAP T2.12: BDRC's etext of the 1996 edition to find the
passages (every «བསྲེག་ཚེས» and «སྲེག་ཚེས» in both volumes), then each passage
on the scans (vol. 1 I1KG12906, vol. 2 I1KG12907, enlarged 2.5–5×). In
vol. 1 the printed page is the image number minus 10, in vol. 2 minus 8.
Built as `BurningDate` (SPEC §5.11, §5.12).

## The rule: WB vol. 1, p. 177 (img. 187)

In WB's account of what a Phugpa almanac writes for each day, right after
the special days of weekday and mansion («… འགྲུབ་སྦྱོར་འཆི་སྦྱོར་སྲེག་སྦྱོར་དང་། །བདུད་ཉི་བདུད་རྒྱལ་འཇིག་པའི་ཉི། །མི་མཐུན་ཉི་མ་ཞེས་སུ་གྲགས།»),
and before the twelve links:

> «ཚེས་[…]གཟའ་འཛོམ་བསྲེག་ཚེས་ནི། །ཉི་མ་བཅུ་གཉིས་ཉི་ཤུ་བདུན། །ཟླ་བ་བཅུ་གཅིག་ཉི་ཤུ་དྲུག །མིག་དམར་བཅུ་དང་ཉི་ཤུ་ལྔ། །ལྷག་པ་གསུམ་དང་བཅོ་བརྒྱད་དེ། །ཕུར་བུ་དྲུག་དང་ཉི་ཤུ་གཅིག །པ་སངས་གཉིས་དང་བཅུ་བདུན་ཏེ། །སྤེན་པ་བདུན་དང་ཉི་ཤུ་གཉིས། །འདི་དག་འཛོམ་ཚེ་བསྲེག་ཚེས་འདྲི། །དེ་ཡང་དཔེར་ན་ཚེས་བཅུ་ལ། །རེས་གཟའ་ཟླ་བའི་ཚེས་ལོངས་དེ། །ཉིན་ཚད་མ་ལོངས་[ཞུང]་བ་ན། །བཅུ་གཅིག་ཚང་བའི་བརྡ་ཆད་དུ། །ཐ་མར་ཞབས་ཀྱུ་ལྡན་པར་འདྲི།»

- The syllable after «ཚེས» in the first line is faint on the scan; the
  etext has «བྱུང». Either way the line says the burning date is where
  date and planet meet.
- «[ཞུང]»: the print looks like ཞུང, the etext has «ཉུང», "less", which the
  sense needs.

**What it says.** The burning date is a weekday meeting a date: Sunday the
12th and 27th, Monday the 11th and 26th, Tuesday the 10th and 25th,
Wednesday the 3rd and 18th, Thursday the 6th and 21st, Friday the 2nd and
17th, Saturday the 7th and 22nd; where they meet, the almanac writes
*bsreg tshes*. Then a case: on the 10th, when the weekday is Monday and the
10th's span (*tshes longs*) falls short of the length of daylight, so that
the 11th begins while it is still day, the almanac writes it too, with a
*zhabs kyu* at the end as the sign that the 11th has come.

**Built and not built.** The app counts the day's own date with the day's
own weekday (a doubled date, falling on two weekdays, burns on one of them
at most; no date burns on two weekdays). The marked case is built since
2026-10-09 with the times within the day (ROADMAP T2.20,
`DayTimes.burningFrom`): where the date before a weekday's burning date
ends before nightfall, that burning date begins in daylight and is shown
on the day with its time and the hook. Nightfall is WB's own day length,
30 chu tshod at the equinoxes and 1;10 more or less each sign-month
(vol. 1, ch. 15, pp. 180–182; almanac-page.md), counted from daybreak,
as for the second mansion. Shown, not weighed. Over 2000–2049 it marks
603 days; 21 September 2026, a
Monday the 10th whose date ends at 14:50, is WB's own example.

## The reading: WB vol. 2, p. 351 (img. 359)

After the eleven karaṇas, before the twelve links, the dates again in
number words, then the reading (read on the scan):

> «ཉི་མའི་ཚེས་ལ་ཉི་མ་ནུབ། །ཟླ་བས་དྲག་པོའི་གཙུག་རྒྱན་ཕྲོགས། །མིག་དམར་སྡང་མིག་ཕྱོགས་བཅུར་བལྟ། །མེ་ཡིས་གཟའ་ཡི་ལག་པ་བསྲེག །རོ་བྲོ་ཕུར་བུས་བརྐུས་ནས་ཟོས། །འཁྲིག་པ་པ་སངས་ཀྱིས་སྤྱད། །རི་ལ་སྤེན་པ་ངན་པ་སྐྱེས། །ཕྱོགས་གཉིས་མཚུངས་ཏེ་བསྲེག་ཚེས་ཡིན། །གཏར་སྲེག་དང་ནི་དགེ་བའི་ལས། །མི་འགྲུབ་མྱུར་དུ་སྤང་བར་བྱ། །རོ་བསྲེག་རྒྱུན་སྲེག་བྱ་བ་ངན། །འོན་ཀྱང་དྲག་པོའི་ལས་ལ་ཤིས། །དེ་དུས་གང་བྱས་འབྲས་བུ་མེད། །དཔེར་ན་དར་བསྲེགས་ཐལ་བ་བཞིན། །སྐྱོབ་ན་རེས་གཟའི་ཁ་དོག་དང་། །མཐུན་པའི་དར་ཆེན་འཕྱུར་བ་དང་། །མར་དཀར་གསུར་བཏང་དེ་ཡིས་ཐུབ།»

- **The dates in number words**, a second witness to p. 177: the Sun's
  date «ཉི་མ» (12), the Moon's «དྲག་པོ» (Rudra, 11), Mars's «ཕྱོགས་བཅུ» (the ten
  directions), Mercury's «མེ» (fire, 3), Jupiter's «རོ» (the six tastes),
  Venus's «འཁྲིག་པ» (the pair, 2), Saturn's «རི» (the seven mountains); «ཕྱོགས་གཉིས་མཚུངས», the
  two halves of the month alike (+15). Each image also pictures the planet
  harmed (the Moon robbed of Rudra's crown jewel, fire burning the
  planet's hand …). `BurningDateTest` checks both witnesses.
- **The results**: bloodletting and moxibustion («གཏར་སྲེག») and virtuous
  work do not succeed and are given up at once; burning a corpse («རོ་བསྲེག»)
  and «རྒྱུན་སྲེག» are bad (the second, literally "continuous burning", is
  not identified and not in the app's lists); fierce work is auspicious.
  Whatever is done bears no fruit, like burnt silk turned to ash.
- **The remedy**: hoisting a large flag in the colour of the day's planet
  and a burnt offering (*gsur*) of white butter overcome it.

## Chapter 34 (read on the scans)

The chapter names the burning date among the times to avoid for four
works ([white-beryl-ch34.md](white-beryl-ch34.md)):

| Work | Page (img.) | Line |
| --- | --- | --- |
| 33, bloodletting and moxa | 404 (412) | «བདུན་དམར་འཆར་དུས་སྲེག་ཚེས་དང་། །འཆི་སྦྱོར་ས་ཡི་ཕུང་བྱེད་རྒྱུ། …» |
| 41, funeral works | 414 (422) | «འཆི་སྦྱོར་སྲེག་སྦྱོར་ཕུང་གི་ཉི། །སྲེག་ཚེས་མི་མཐུན་ཚེས་གཉན་དང་། …» |
| 42, supports, temples, stupas | 414 (422) | «འཆི་སྦྱོར་སྲེག་ཚེས་ལོ་ཟླ་ཚེས། །ནག་དང་ …» |
| 61, life and wealth | 426 (434) | «འཆི་སྦྱོར་སྲེག་ཚེས་དབུལ་ལག་འཆར། …» |

Its closing verse (p. 428, etext) lists *sreg tshes* among the things to
know for any work, with no side.

## As built

`Texts.BURNING_DATE`: avoid bloodletting and moxibustion, virtuous work,
cremation (p. 351), funerals, setting up supports and temples, and
accomplishing health and wealth (ch. 34); good for fierce rites (p. 351).
Its tone is bad. In the weighing it is a member of the special days'
voice, where WB's almanac writes it (p. 177): the special days speak as
one, and not at all when they disagree (SPEC §5.12).

Over 2000–2049 (measured 2026-10-08): 1,226 burning days of 18,263; 729
of them have a lucky tone without it, and the burning date changes the
day's tone on 11. On 721 a special day of weekday and mansion
falls too, and where that one is lucky the voice is silent. The works it
moves most: bloodletting to avoid (91 days, 46 of them from good), health
and wealth (56), funerals (37), virtuous work (24), and fierce rites to
good (6). Days whose lists run more than two to one against their tone:
2,082 (2,085 without it).

## SN, a witness (read 2026-10-08)

The Bon *snang srid me long* (SN, a witness to WB only: nothing that only
it gives is built) has the burning dates in a table, p. 221 (img. 249,
read on the scan), headed «ཁ་བྱང་། དཔལ་ཉི། སྲེག་ཚེས།», the weekday by number
(༡ Sunday … ༦ Friday, ༠ Saturday), «གཟའ། ཚེས་གྲངས།» at its foot; the
etext loses the numbers. Its *sreg tshes* column agrees with WB on five
weekdays (Tuesday 10/25, Wednesday 3/18, Thursday 6/21, Friday 2/17,
Saturday 7/22) and prints Sunday ༡༢ ༢༤ and Monday ༡༠ ༢༦: each of those two
cells breaks the fifteen-day step that every other row keeps and that
WB's p. 351 states («ཕྱོགས་གཉིས་མཚུངས»). WB counts; SN's two cells are noted,
not built. Its verse on the special days, p. 210 (etext), has
«སྲེག་སྦྱོར་སྲེག་ཚེས་གཏར་སྲེག་སྤང་», bloodletting and moxibustion avoided on
both, as WB. Its works 12 (planting trees and flowers, p. 229) and 16
(bloodletting and moxa, p. 233; etext) avoid the burning date; WB's
chapter 34 does not name it for planting, so the app does not either.

## The calendar compared

tibetastromed.ru's "burning day" ([tibetastromed.md](tibetastromed.md))
has the same fourteen pairs on every day of 2026. Its reading, "all
undertakings fail", is broader than WB's.
