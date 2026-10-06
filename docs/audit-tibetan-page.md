# Audit of the Tibetan page, 2026-10-06

Three questions: how the day's verdict is reached and whether the sources
support it; whether the activity lists are complete, consistent and free of
duplicates; and which lines of the page repeat each other.

**Basis.** The code at a560b81 plus the uncommitted working tree (a
comment-only change in `Texts.kt`), SPEC §5.10–5.13 and §10.3, and
`docs/sources/{weighing,combinations,kp-activities,mansion-verses}.md`. The
numbers come from running `DaySummary.of(TibetanDay)` over every day from
2000-01-01 to 2049-12-31 (18,263 days) with a throwaway test, which is not
part of the repository. The examples are 2026 dates.

## Status, 2026-10-06 (later)

Built from this audit (SPEC §5.12, §10.3; `DaySummary.of(TibetanDay)`):

- **One weighing for the day and its works.** Where the named combination
  and the element pair agree, the combination is the result of the day,
  and of a work its element pair names, and what any other factor says
  against it is outweighed (WB p. 333). Where they disagree, and on works
  the combination does not name, the side more voices take, then the
  strongest. Days whose lists run against their tone: 3,892 before, 2,243
  now. A first version let the combination's tone decide every work (409
  such days), which emptied the avoid list on every lucky-combination day;
  withdrawn the same evening.
- **V1** the special-day step is gone; *dmigs bsal* is read as the
  combination period (open question 13); the special days are one voice in
  their rank. **V2** the count is KP's seven: the mansion, the *nyi ma*
  and Rāhu have no tone of their own, the trigram is not counted.
  **V3** a tie goes to the strongest of the seven; the weighted reading
  is open question 12. **V4** the grades are read in the verse's order;
  the three *gso thub* pairs are lucky by their own verses (`PairGrade`).
  **V5** the brief adds a note on festival and observance days. **V6** the
  summary line names what decided the tone. **V7** the texts are rewritten
  in English and Russian. Tone now: combination 53 %, count 40 %,
  strongest 7 %.
- **Questions 12 and 13 answered (2026-10-06,
  [sources/weighing.md](sources/weighing.md)).** KP's rules digest WB
  vol. 2, p. 376. WB sets the weights aside as the Kashmiri paṇḍita's
  (V3 stands: no sum). Its *dmigs bsal* is the particular case (a reading
  that names the work; a person's own weekday and mansion), not the
  combination period, and *phyogs sdebs* the general grouping of factors
  as good or bad, not a count: V1's reading of *dmigs bsal* and V2's
  count both lose their source. Read with WB, a work goes to the strongest
  voice that names it and the tone to the strongest voice that has one,
  after the combination. Measured on 2000–2049 (18,263 days; the count
  decides 7,314): the strongest voice alone changes the tone of 1,533 days
  and 85,247 of 1,530,335 work decisions (5.6 %), touching some work on
  17,719 days. Built the same day at the owner's word: the count is gone,
  `VerdictBy.SIDES` with it.
- **A2/D4** FPMT's hair-cutting day is one of the date's lists, weighed
  with them; the row and the brief agree on 62 % of days (was 50 %). Where
  they still differ, the weighing has put a stronger voice above FPMT.
- **Splits**: `JUDGING_CASES` (box 59), `DOWRY` (box 23); the
  directional war wordings count for neither side; the crafts wording maps
  to study, astrology and making things. `HORSES` is kept as one.
- **D1** the festival is not repeated in the Almanac. **D5** several
  special days are one row. **D6** Rāhu's courses are one row. **D7, D8**
  the two animals and the two sme ba are labelled apart.
- Outweighed factors and works are not shown on the page (the owner's
  rule, 2026-10-06).

Not done: **A1** (the mansion verses must be read by eye first),
**D2/D3** (the five components and the day line still repeat Almanac
rows), and the `installing_a_deity` and `taking_elixirs` mappings.

---

## 1. The day's verdict

### What the code does

`DaySummary.of(TibetanDay)` (`core/.../texts/DaySummary.kt`) decides the
tone in four steps. Only the third step counts good factors against bad
ones:

| Step | Rule | Days | Share |
| --- | --- | ---: | ---: |
| `COMBINATION` | named combination and element pair agree | 9,672 | 53.0 % |
| `COMBINATION_DAY` | they disagree; all of the day's special days have one tone | 3,660 | 20.0 % |
| `SIDES` | otherwise the side with more toned factors | 4,147 | 22.7 % |
| `STRONGEST` | the count is a tie: the first toned factor | 784 | 4.3 % |

So **the verdict is a raw count on about a quarter of days**, and on the
other three quarters the combinations or the special days decide it.

### Where the code departs from the sources, or from itself

**V1. The special-day step is not well supported, and it contradicts the
app's own ranking.** The step reads the *dmigs bsal* of KP rule 3
(«འཕྲོད་ཉིད་གཙོ་བ་དང་། དམིགས་བསལ་མེད་ན་ཕྱོགས་སྡེབས་གཙོ») as WB's special days of
weekday and mansion. The same KP passage uses *dmigs bsal* again a few
lines later, and there it names the combination period:
«…ཏཏྐཱལ་དུས་སྦྱོར་གཅིག་ལ་ཚང་བའི་དམིགས་བསལ་གཙོ» (rule 5). WB p. 337 says the
special days "matter somewhat" and that "the individual results of planet
and mansion are the main thing". SPEC §5.12 therefore puts the special days
*below* the weekday and the mansion for activities, yet the verdict lets
them overrule everything except the combinations. The comment on
`Texts.COMBINATION_DAY` goes further and calls them "above the
combinations", which matches neither the SPEC nor the code. Dropping the
step would change the verdict on 1,321 of the 3,660 days it decides (7 % of
all days).
*Proposal:* read *dmigs bsal* as the combination period, which a whole day
does not have, so a day with split combinations goes straight to the
count. In that count the special days stand in their own rank, as they
already do for activities. Fix the `COMBINATION_DAY` comment either way.

**V2. The count does not count KP's seven.** KP rule 3's *phyogs sdebs*
weighs the factors ranked in rule 4: Rāhu, weekday, mansion, date, karaṇa,
yoga and *nyi ma*. The code counts something else:

- **The mansion never votes.** Its tone is `toneOf(its lists)`, and every
  one of the 27 mansions has lists on both sides, so all 27 come out mixed.
  The mansion's Almanac dot is therefore always the mixed colour and tells
  the reader nothing. The *nyi ma* is the same: all 12 animals are mixed.
- **The trigram votes**, although `weighing.md` says it is not among the
  seven and its Almanac row carries no dot. Two of its eight values have a
  tone (one lucky, one unlucky), and that vote changes 165 verdicts.
- **Each special day votes separately.** A day can have up to six (see D5),
  which outvotes the weekday. 873 of the counted days have two or more.
- Rāhu takes no side. SPEC states this as the app's choice; it is
  consistent, but KP ranks Rāhu first.

The result is that "most of the day's factors" in practice means the
weekday's dot, the date's dot, the karaṇa, the yoga and the special days.
Counting KP's seven alone (with the combinations, which cancel out) changes
254 verdicts.
*Proposal:* count the seven. Take the mansion's tone from a source rather
than from its lists: either WB's seven classes (pp. 328–329), if they carry
good and bad, or WB's mansion verses (see A1). If neither works, state that
the mansion and the *nyi ma* have no tone of their own. Leave the trigram
out of the count. If the special days are counted at all, give them one vote
as a group: their shared tone, or none when they are split.

**V3. A tie always goes to the named combination.** `STRONGEST` takes
`ranked.first { toned }`. The named combination is first in that list and
always has a tone, so it decides every tie: 463 lucky and 321 unlucky days.
But a tie can only arise after the two combinations have disagreed. KP's
"the stronger" comes in the same sentence as the ranking of the seven, so
the stronger factor should be one of the seven, which in practice means the
weekday (or the date when the weekday is Sunday, since the mansion has no
tone). There is a second possibility. «ནུས་སྟོབས་ཀྱི་ཁྱད་པར་བརྩིས་ནས» ("having
counted the difference in strength") repeats the heading of rule 1,
«ནུས་སྟོབས་ཆེ་ཆུང་གི་ཁྱད་པར་བརྩི་ལུགས», whose weights are the date 1, the
planet 4 and the mansion 8. That suggests a weighted sum. Applied whenever
the combinations disagree, such a sum changes 2,805 of those 8,591 days.
*Proposal:* break a tie with the first toned factor among the seven. Add
the weighted reading to `open-questions.md` as question 12, since it needs
a reader of KP.

**V4. The element pairs are flattened from four grades to two.** WB p. 333
grades the ten pairs: «བཟང་གསུམ་གསོ་ཐུབ་གསུམ། །ངན་གསུམ་ཐ་ཆད་གཅིག», three good,
three that can be restored, three bad and one worst. `ElementPair.auspicious`
makes six lucky and four unlucky, so the three restorable pairs count as
lucky. `combinations.md` does not record which three pairs are the
restorable ones. Because the `COMBINATION` step decides 53 % of days on the
pair's tone, this choice carries weight.
*Proposal:* record from the verse's order which pairs are restorable. Then
decide whether they agree with a lucky named combination, as now, or leave
the decision to the next step (they would become mixed). The second is the
more cautious reading of *gso thub*.

**V5. Festival days often get "an unlucky day".** 252 of 600 festival days
(42 %) and 1,360 of 3,080 monthly observances receive an unlucky verdict.
Examples: Chotrul Düchen 2026-03-03 (Tuesday, Maghā, *dus dbyig*) and
Kālacakra 2026-05-01. The texts give no rule placing these days in the
weighing, so the verdict itself is not wrong. But the headline announces a
festival while the In brief line calls the day unlucky, and the page does
not say how the two relate.
*Proposal:* on a festival or observance day, the brief says that its tone
concerns the day's works and not the festival's merit. This is a change of
wording only.

**V6. The tone and the activity lists can point opposite ways.** On 3,892
days (21 %) a lucky verdict comes with more than twice as many activities
to avoid as to do, or an unlucky one the other way round. Example:
2026-05-19 is lucky, through the named combination on a tie, with 25 good
and 52 avoid. The sources allow this, because the tone is the
combination's. The summary line, however, shows a lucky dot above a long
row of things to avoid. The brief also lists 40–75 activities a day.
*Proposal:* the line names what decided the tone ("lucky by the
combination"), as the sheet already does.

**V7. Some interface texts describe a rule the code does not follow.**
`brief_note_tibetan` leaves out Rāhu. `brief_day_strongest` says "the
strongest decides", but the decider is always the named combination.
`brief_day_sides` says "most of the day's factors", but the mansion and the
*nyi ma* never count. Rewrite these texts once V1–V3 are settled, in
English and Russian.

---

## 2. Activities

### Coverage

- Every one of the 254 wordings in the Tibetan readings maps to one of 126
  activities; `TextsTest` enforces this. 63 KP lists are built. Box 47,
  the averting-rite boxes and the charts are not built, as SPEC §5.10
  records.
- **A1. The mansion has the thinnest lists of the ranked factors.** The
  weekday, date, karaṇa and yoga each carry WB chapter 33's own lists. The
  mansion, KP's third-strongest factor, carries only Henning's mansion list
  and the KP boxes. WB's 28 mansion verses (pp. 313–328) each have a long
  good list and an avoid list. They are transcribed in
  `docs/sources/mansion-verses.md`, but `PLAN.md` item 3 holds them back
  until they are read by eye.
  *Proposal:* read the verses by eye and build them into `Texts.MANSION`.
  That also supplies a sourced basis for V2's mansion tone.
- **A2. The haircut is decided twice, by different texts.** KP box 52a
  (cutting hair and nails) names all seven weekdays, so the weekday decides
  haircuts on every single day. The Almanac's Haircut row follows FPMT's
  sutra, by date. The two disagree on 9,217 of 18,263 days (50 %). Example:
  on 2026-01-01 (the 13th, a Thursday) the row says good and the brief says
  avoid, by the weekday. See D4.

### Merges that create false disagreements

`Ranked` makes a factor silent on any activity its own lists name both
ways. Where two distinct acts share one `Activity`, that rule hides real
information:

| Activity | Merged wordings | Factors silenced | Proposal |
| --- | --- | --- | --- |
| `DISPUTES` | box 58 ཁ་མཆུ་རྩོད་པ (taking part in disputes) + box 59 ཞལ་ལྕེ་གཅོད་པ (judging cases) + WB "quarrels", "feuds" | Sunday, Friday, date 8, Mṛgaśiras, Ārdrā, Puṣya, Svāti | `judging_disputes` → a new `JUDGING_CASES` (AGREEMENT family) |
| `GIVING_OUT` | box 23 སྐྱས (gifts and dowries) + "sending out wealth", "sending out livestock", "giving anything out" | Wednesday, Thursday, Uttaraphalgunī, Mūla, Pūrvabhādrapadā, Revatī | `giving_gifts_and_dowries` → a new `DOWRY` |
| `HORSES` | box 28 feeding up horses + box 29 treating horses, mules and donkeys | Svāti, Śatabhiṣaj | split, or accept as minor |
| `WAR` | "war", plus WB's "war not to the east", "not to the south", "to the south or west" | Saturday; on the dates, "good for war" appears without its direction | treat the directional wordings as qualified (SPEC §5.10: counts for neither side); the reading keeps them |

Two compound wordings are mapped inconsistently. `learning_writing_and_astrology`
maps to STUDY + LEARNING_ASTROLOGY. `learning_writing_astrology_and_crafts`
maps to LEARNING_ASTROLOGY + LEARNING_ARTS: it drops "writing", and sends
"crafts" to *music or dance*, although `crafts` elsewhere maps to
MAKING_THINGS. *Proposal:* map it to STUDY + LEARNING_ASTROLOGY + MAKING_THINGS.

Some factors are silent because two sources genuinely disagree, which is
how SPEC means the rule to work. Example: on dates 2, 3, 5–8 and 14, WB's
date verse and KP box 49 disagree on consecration. These are listed here so
they are not mistaken for mapping errors.

### Duplicates that could be merged

Few activities are true duplicates. The 126 are mostly distinct Tibetan
terms, and the 27 families already group them for the glyphs. Candidates:

- `installing_a_deity` (Henning's mansion list) → SACRED_SUPPORTS. If its
  Tibetan is *rab gnas*, it belongs with CONSECRATION. Check it against
  the mansion verses (A1).
- `taking_elixirs` (*bcud len*, rejuvenation) → TAKING_MEDICINE, a
  Japanese activity ("taking medicine"). MEDICAL_TREATMENT or a separate
  entry fits better.
- VIRTUE / DHARMA_PRACTICE / GOOD_FORTUNE render *dge ba'i las*, *chos
  spyod* and *bkra shis*. They are different words in the source; keep them
  apart.
- The two calendars share 43 activities. Near-pairs across them, such as
  MEMORIAL_SERVICES and FUNERALS, or SHRINE_RITES and OFFERINGS, never meet
  on one page, so merging them gains nothing.

---

## 3. Lines on the page that repeat

| # | Repeated | Where | Proposal |
| --- | --- | --- | --- |
| D1 | The festival | headline (name, glyph, tap opens the reading) and the first Almanac row, with the same reading | drop the Almanac row on a holiday; the headline already opens it |
| D2 | Weekday and planet | day line ("Tuesday · Mars · Fire Horse"), Almanac row (Tuesday / planet Mars), Five components row (gza' … — Tuesday · Mars · fire) | one place each: the day line for the fact, the Almanac row for the reading |
| D3 | Mansion, karaṇa, yoga | Almanac row and Five components row each | fold the Tibetan term (script, tap for Wylie and phonetics) into the Almanac row's subtitle and drop the Five components section, which has only four rows anyway |
| D4 | The date, and the haircut | headline number, "Lunar date N" row, Haircut row (FPMT by date); the brief also names haircuts every day (A2) | weigh FPMT's day as the date's haircut entry, so the row and the brief agree; or move the haircut into the date's reading and keep KP's box for the brief |
| D5 | Special days | up to six rows on one day (2026-01-02: *zung sbyor*, *chub nyi*, *'chi sbyor*, *mi 'phrod*, *gtan spang*, *gtsug lag 'jig nyi*); opposite pairs together: *mthun nyi* + *mi mthun nyi* on 6 weekday–mansion cells (2026-03-21), *zung sbyor* + *chub nyi* + *gtan spang* on 6 (WB itself says the last two take *zung sbyor*'s mansions except on Thursday). 48 of the 189 cells carry two or more special days, 27 of them with both tones | one "Special days" row, its dots side by side, opening a sheet with each; the three that share mansions shown as one entry with WB's note |
| D6 | Rāhu | two rows titled "Rāhu's course" on the dates of the monthly course (the detailed course and the month's form) | one row; the month's form inside the reading |
| D7 | Two different "Animal"s | the day line's balloon gives the 60-day cycle's animal (`dayAnimal`); the Lunar day section gives the date's animal (`lunarDayAnimal`, the *nyi ma* that the weighing uses). Same label, usually different values | label them "day sign" and "date's animal (*nyi ma*)" |
| D8 | Two different sme ba | "Number" in the Lunar day section is the date's sme ba; the *bla mkhyen* subtitle "the day's sme ba N" is the 60-day count from the solstice | label them "date's sme ba" and "day's sme ba (from the solstice)" |
| D9 | "weekday + mansion" | subtitle of the named combination, the element pair and every special-day row | resolved by D5 |

The factors listed in the In brief sheet repeat Almanac rows by design: it
says which of them carried the verdict. That is not a defect.

SPEC §10.3 itself specifies D1 (headline and Almanac row) and D3 (the five
components after the Almanac), so those fixes need a SPEC change. They fit
the owner's rule that nothing repeats on the day page.

---

## Order of work

1. **Verdict rule** (V1–V3): done 2026-10-06; questions 12 and 13 answered
   from WB's verse, the count replaced by the strongest voice. Then SPEC §5.12,
   `DaySummary`, `DaySummaryTest` with vectors, and the strings (V7).
2. **The haircut** (A2/D4): a contradiction visible on every other day.
3. **Activity splits**: DISPUTES/JUDGING_CASES, GIVING_OUT/DOWRY, the
   directional WAR wordings, the crafts mapping.
4. **Page duplicates** D1–D3, D5–D8, with SPEC §10.3.
5. **Element pair grades** (V4) and the festival wording (V5–V6).
6. **WB mansion verses as lists** (A1): the largest item, and the one that
   gives the mansion a sourced voice in the weighing.
