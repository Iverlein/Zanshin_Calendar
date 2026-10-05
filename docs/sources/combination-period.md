# The combination period

Sigla and the rules for quoting are in [README.md](README.md).

The *dus sbyor* or *tatkāla dus sbyor*, which the *kun phan me long* and
the White Beryl hold above every factor of the day ([weighing.md](weighing.md)),
is the sign rising at the moment: «དུས་སྦྱོར་གཟའ་སྐར་བྱེད་སྦྱོར་བཞི། །དུས་སྦྱོར་རེ་རེའི་ཁྱིམ་ལ་ཚང་།
།དེ་ཕྱིར་ཁྱིམ་གྱི་འབྲས་བུ་བཤད།» (WB vol. 2, p. 371): the works of planet,
mansion, karaṇa and yoga are complete in the house of each *dus sbyor*,
so the results of the houses are given. It is the same "rising sign" the
activity boxes name among good and bad times ([kp-activities.md](kp-activities.md),
"not calculated"). Read 2026-10-05.

## The sign of each hour: KP §9

KP img. 79–80, read on the scan: a table headed «ཁྱིམ་བྱུར་ཟླ» (the month by
its house) with the month and its sign in the first column and the twelve
double hours as the others, from daybreak: ནམ་ལངས, ཉི་ཤར, ཉི་དྲོས, ཉི་ཕྱེད,
ཕྱེད་ཡོལ, ཉི་མྱུར, ཉི་ནུབ, ས་སྲོས, སྲོད་འཁོར, ནམ་ཕྱེད, ཕྱེད་ཡོལ, ཐོ་རེངས. The
rows run ༣ ལུག (the 3rd month, Aries) … ༡༢ ཆུ་སྲིན, ༡ བུམ་པ, ༢ ཉ (the 2nd month,
Pisces); each row starts at daybreak with the month's sign and goes on one
sign an hour (the 5th month: Gemini, Cancer, Leo … Taurus). These are the
twelve hours the app draws from the hare hour at 05:00
([nectar-periods.md](nectar-periods.md): daybreak, ནངས, is the hare hour),
so the sign of hour *h* (0 at 05:00) in month *m* is the month's sign plus
*h*. `CombinationPeriodTest` checks the rows.

## What each period means: WB pp. 371–376

WB vol. 2, pp. 371–376 (img. 379–384), read with Yigdzin-1 on the scans
and BDRC's etext as witness; the list boundaries of Scorpio and Capricorn
checked on the scan. For each sign rising «ཁྱིམ་དང་ཏཏྐཱལ་ལ», a list of works
that are good (closed by བཟང), one that are bad (closed by ངན or སྤང), what
the period brings, its verdict, its own name, what an eclipse in it harms
(གཟས་བཟུང), and readings for a birth, a death, an illness and a prisoner,
which the app does not use.

| Sign | Name of the period | Verdict |
| --- | --- | --- |
| Aries | པྲ་ཏི་ཏ | སྤང, avoid |
| Taurus | ཀཱ་ཤཱ | བསྒྲུབ, accomplish |
| Gemini | ཀ་ཊི་ཙཱ་ཤ (etext ཀ་ཊི་དྷོ་ཤ) | བཟང, good |
| Cancer | སྤལ་བ (etext སྦལ་བ) | སྤང, avoid |
| Leo | སིདྷ | བསྒྲུབ, accomplish |
| Virgo | ཏཱ་ཊ་གུ (etext ཏཱ་ཊ་གྲུ) | བསྒྲུབ, accomplish |
| Libra | ཀཱ་ཀཱ་ཙཉྩ | སྤང, avoid |
| Scorpio | ཊུ་ན | སྤང, avoid |
| Sagittarius | དྷ་ནུ | བསྒྲུབ, accomplish |
| Capricorn | མོ་ན | སྤང, avoid |
| Aquarius | གྷ་ཊ | བསྒྲུབ, accomplish |
| Pisces | དྲྀ་ཏ | བསྒྲུབ, accomplish |

The works of each list are in `Texts.DUS_SBYOR`, in the catalog's
wordings; a few words with no wording are left out (Aries' བཟེ, Cancer's
boats and bridges and «the four empowerments», Pisces' opening storehouses).
Two readings are the app's:

- **Scorpio**: the good list ends «དྲག་ལས་མཚོན་ཆ་ཁྲག་ལས་བཟང» on p. 373, and
  the bad list begins with the temples on the same line (the etext garbles
  the page break).
- **Capricorn** has one list, closed «ཞི་རྒྱས་དབང་གི་ལས་ཕལ་ཆེར། །འདུམས་དང་བ་དན་
  འཛུགས་པ་ངན»: no word of it is good, every other section marks its good
  list with བཟང, and the verdict is "avoid", so the app takes the whole list
  as bad.

After the twelve, the chapter sorts the signs by sex, class and element and
repeats the weighing of the day ([weighing.md](weighing.md)), ending with
the *dus sbyor* above all.

## In the app

The hours panel (SPEC §10.3) opens for everyone: its inner ring is the
combination period, each hour coloured by its verdict, with dots on the
nectar periods; tapping an hour shows its sign's reading with the lists.
The day in brief does not weigh it: it is a time within the day, as the
text itself says, and the day's verdict stays the day's.
