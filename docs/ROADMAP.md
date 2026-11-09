# Zanshin Calendar — Roadmap

Planned work that is not yet built. [SPEC.md](SPEC.md) describes what the app
does; this file describes what it is meant to do next. When an item is built,
its settled design moves into the SPEC and the entry here is removed.

The rules of the SPEC apply to everything below: no text without a published
source (§8), nothing the app cannot calculate, no `INTERNET` permission (§2).

## By effort

The items below, easiest first. Sizes: **S** a sitting, one or two files;
**M** a few days, new code with its tests; **L** a week or more, touching
most screens or needing a generator in `tools/`; **XL** several weeks,
mostly research or design before any code. The rank counts the work of
building an item, not its prerequisites; "Needs" gives those, so the build
order can differ from the rank: it is under *Blocks*. An item with no use on its own is ranked
with the one that gives it a use. "Later" is not ranked.

| # | Item | Size | Why | Needs |
| --- | --- | --- | --- | --- |
| 1 | T3 Element colours — built 2026-10-09 | S | The day sign moved to the Lunar day section, its element in colour | — |
| 2 | L4 Hosted Weblate | S | The repository is ready (docs/weblate.md); the project, its three components and the review rules are set up on Weblate with the owner's account; the translating itself is outside the code and open-ended | Not before 2026-12-28 (three months of development, for the Libre plan) |
| 3 | L3 Russian | S | Translated and passing `CatalogTest`, with the store listing and the language switch; what is left is the owner's read-through on the phone and the fixes it brings | X1–X10, which reword every reading, so the owner reads them once |
| 4 | T2 The Tibetan page's gaps | L | The page's readings are built, the hour of KP's rule 2 too (T2.4, built 2026-10-10): a work plan of nine items below, T2.1–T2.9 built; then WB above all (T2.10–T2.18): WB's seasons (built), its almanac page (built; it added T2.20 the times within the day (built), T2.21 the month's own entries (built), T2.22 the *dbyangs 'char* entries and twelve links (built), T2.23 the five planets and the *byed rtsis* (built: the eclipses; the rest left out by the owner's rule)), WB's day animals (T2.24, built 2026-10-10), the burning dates (built), the eight classes' and nāgas' strikes, 11/6 (built), hair by date (built), the la's place (built), Russian terms (built) | — |
| 5 | E Election: the best day for a work — built 2026-10-09 | L | E1–E6 built (SPEC §5.13, §5.14, §7.6, §10.8): the day's weighing read across days for one work, best first, with the hours of each month, the works' own rising signs of WB ch. 34 first; on the 旧暦 page the annotations' days for it, unranked (E5) | — |
| 6 | T4 Element calculation, with the T5 settings — built 2026-10-09 | XL | The year of age (SPEC §5.9.1): natal and yearly mewa, trigram and progressed sign by gender, the 24 decisive pebbles with the predictive ones, the sectors, the harsh years, nine-multiples and the mewa's obstacles, all with WB's readings; the gender on each person. Left: what needs the mother's year, a spouse or the dead, and the rules WB leaves unclear (below). Mo is not planned | — |
| 7 | M1 Meditation timer and bell — built 2026-10-09 | M | A screen of its own from the menu (SPEC §10.9): the timer in a foreground service, the mindfulness bell by exact alarms, fixed or random, the bells synthesised | — |
| 8 | U The Tibetan page's UX | M | Five small items, all built: the deciding factor named (U2), the combination as one row (U1), the page in weighed / yours / also-today sections (U3), the brief grouped by voices (U4), the hours on the In brief row (U5); no side or tone changes | — |
| 9 | T2.27 WB's days of the twelve links — built 2026-10-15 | S | WB's own reading of each link's day (vol. 2, pp. 351–356), found unread on 2026-10-11: works, directions, illness, remedy; it joins the link's lists | — |
| 10 | A1 Light theme and colour themes — built 2026-10-15: System, Ink, Paper and Night (block 33), Maroon and Sepia (block 38) | L | The palette is one dark `Palette` object, read about 510 times in 20 files, 29 of them in Canvas code; it becomes a theme chosen in Settings, with a light theme and dark and light alternatives, every tone and printed colour kept legible on each; the checks of the review of 2026-10-11 in its section | — (D5 settled 2026-10-11); U8–U18 and X2 first, so the screenshots are retaken once |
| 11 | T2.26 The year's and the month's earth lords — built 2026-10-26 | L | WB's almanac writes them at the head of the year and of each month, and ch. 31's pp. 175–221 are unread; Year and Month tabs, outside the weighing with their remedies (D4, the owner, 2026-10-26) | — |
| 12 | H1–H3 The health screen, and the menu's groups collapsible — H1 and H3 built 2026-10-15, H2 built 2026-10-26 (H2a 2026-10-22) | L | A screen of its own from the menu, with Tibetan medicine beside WB (Dr. Nida Chenagtsang, trusted by the owner): the element's mansions by the life force (left by T2.19), the la's place, vitality and body, the day's illness readings, then WB's illness reckoning; with it the menu's groups collapsible, closed by default, each remembering its last state (H3, S), since the screen is the first of several that lengthen the menu | T2.27; R1 for H2 |
| 13 | P1–P3 Relatives, the obstacles that need them, the bride's departure — P1 built 2026-10-15 | L | The readings T4 left for want of other people's dates, and WB ch. 23's bride's departure, on relatives added to each saved person | P1 first |
| 14 | R The reckonings, a section of their own — R1 built 2026-10-15 | XL | WB's pebble divinations, chapters 20–30, in a menu section as Meditation has; the section is open with the year of age in it (R1), the reckonings one by one follow (R2…) | R1 first; P1 for those that need relatives |
| 15 | U8–U18 The UX review of 2026-10-11 | M | Eleven small items, each S, none changing a side, a tone or a reading: the sky line as one line, the date picker's jump, the brief's rows in the 旧暦 brief's form, the hours in short form, dots that read, second lines that state the fact, the election's civil dates, the calendar toggle, the two briefs alike, the bell's time picker, a first-run line; all eleven built 2026-10-11, blocks 34 to 37 | — |
| 16 | N1 The morning notice — built 2026-10-16 | S | One notification a day with the brief's tone, reason and hours, on the bell's alarms (SPEC §10.9); SPEC §3 moves it into scope | U11, for the hours' short form |
| 17 | A2 Large font and landscape — built 2026-10-16 | M | The page at font scale 1.5 and 2.0, in landscape and on a tablet: labels at 12 sp least, label columns measured, two columns or a capped width, diagrams that scale | U8–U18 and X2, so the rows and the sheets' label column it measures are final |
| 18 | W1 The widget — built 2026-10-16 | M | A widget in two sizes with the day's tone, its reason and the hours, plain RemoteViews rather than Glance; SPEC §10.6 written | A1 (the theme), U8–U18 (the header and rows), U11 (the hours' short form) |
| 19 | X1–X10 The readings' wording | L | Every reading of the Tibetan page's sheets in one shape: the ruling first in a paragraph of 20–60 words, the rest as labelled rows in a fixed order, *How it falls* today's line only, boilerplate once, one vocabulary, the verse's headword as an image; the catalog carries rows and `CatalogTest` holds the shape (X1–X2, block 42, built 2026-10-11; X10 and the year sheet, blocks 49 and 57, built 2026-10-16; X5, block 44, built 2026-10-16; X6, block 45, 2026-10-17; X7, X8 and X9, blocks 46 to 48, and X3–X4, block 43, 2026-10-18, the last); 459 readings, family by family, the Russian with each; no side, tone or list changes | — for X1–X2; X1–X2 for the rest; L3's read-through (block 15) waits on all of it, A1 and A2 on X2's sheet |
| 20 | K1–K6 The 旧暦 page after the expert's review of 2026-10-12 (K3 and K5's year sheet built 2026-10-31) | M | The Russian terms by the scholarly literature (K1), the festivals' names she corrected (K2, built 2026-10-14), the old texts behind the readings (K3, her advice: 暦林問答集 over 簠簋内伝), the claims to settle on the sources (K4, with 天一天上 and 歳徳神 as printed today), the direction gods (K5), every row opening as on the Tibetan page (K6, planned 2026-10-14: the solar term with its reading, details and full ring, 2026-10-16, the 干支 rows, the month and year stars, the date and moon); the purpose and the frame are settled (SPEC §7: the printed almanac's set, read by the old texts; her second answer, 2026-10-14) | D8 and D9 settled 2026-10-30 but for their open points (the 十二直's lists, the terms' voice; 金神 and the daily directions, read in RD93–RD94); a paper 高島暦 (D12) |
| 21 | T2.28 Corrections from the readings | S | Five entries the O readings found otherwise on the scan than the app has them: KP's table, ch. 34's marriage and learning, the 9th hair date, Tuesday's saddling, the horse day's witness; three lunar dates wait on the owner (D11) | — (D11 for the lunar dates) |
| 22 | U19 Pinned works — built 2026-10-21 | S | The works one cares about first in the Works section, left by U6 as a later item | — |
| 23 | R4 Combining the readings | M | Each reckoning's sections joined by WB's own ladder (the verdict of each aspect, the sets, whose aspect and which part of life, the rule for the whole) into a conclusion read for action, with its remedies; planned 2026-10-20, the readings first | RD18–RD28; block 31 |
| 24 | [C1](#c1) The knowledge pool in layers | M | The structure of docs/sources/STRUCTURE.md, decided 2026-10-22 and indexed by `tools/sources/kb.py`, applied to the files: the topic files split into corpus files with passage ids, claims and question files, README's index and State column generated from their frontmatter, `SourcesTest` covering the layers; done file by file as each is next touched, and this item closes the remainder | — |
| 25 | R5 The Together everywhere WB sums up — built 2026-10-31 (block 60); the dead's twenty roots and q428's differences left | M | The moment, each of the dead and the illness get a Together atop, by WB's own summing rules, as the birth, the match and the year of age have one (R4); planned 2026-10-27 | RD58; R4 |
| 25 | [R9](#r9) The household round — built 2026-11-05 (block 58) | M | The year of age read for a whole house: each member's deciding pebbles and mewa zigzagged, folded into the god's, people's, house's and enemy's places | Question 258 |
| 26 | R6–R8 Ch. 27's small reckonings: by the year, the rounds by age, and those of the asking time — R6, R7 and R8 built 2026-10-31 (blocks 61–63) | M | What WB gathers at the end of the circles' chapter and the app left out: the four starting points of p. 17, the la's wandering and decline, the grave entered, the end of the womb, the birth by the year's power (R6); the rounds by age of pp. 18–19 (R7); the month for one gone abroad and the reckonings of the asking time, pp. 13–14 and 19 (R8). The meadow's ornament (p. 22) stays out: what the five of a group are is open (question 216), and KD's worked sheet, recomputed, fits its first group only (claims/kd-meadow-sheet-not-reproduced.md) | R6 first |

## Blocks

The build order, by what each item waits on rather than by its size. A
block is built in one go and is done when each of its items meets its own
"Done when" below. **"Build the next block"** means the lowest-numbered
block not marked built; a built block keeps its number, marked built with
its date, so each prompt stays valid. Each block links to its items;
"Readings" names the readings it waits on (below) and "Builds" the blocks
it needs; a new item names its block the same way. A block blocked from outside (14)
moves up as soon as it is unblocked.

| # | Block | Readings | Builds | Prompt |
| --- | --- | --- | --- | --- |
| <a id="block-1"></a>1 | [U1](#u1) + [U3](#u3) — built 2026-10-09 | — | — | Build block 1: U1 and U3, one combination row and the Almanac in weighed, yours and also-today sections. |
| <a id="block-2"></a>2 | [E6](#e6), the "For you" half — built 2026-10-09 | — | — | Build block 2: E6's "For you" half, every work to avoid on the person's enemy weekday and death mansion. |
| <a id="block-3"></a>3 | [U4](#u4) + [U5](#u5) — built 2026-10-09 | — | [1](#block-1) | Build block 3: U4 and U5, the brief grouped by voices and the hours on the In brief row. |
| <a id="block-4"></a>4 | [T3](#t3) Element colours — built 2026-10-09 | — | [1](#block-1); the day element's place is the owner's choice | Build block 4: T3, a place for the day's element on the page and its colour. |
| <a id="block-5"></a>5 | [T2.11](#t2-11) WB's almanac page — built 2026-10-09 | — | — | Build block 5: T2.11, read WB pp. 171–178 on the scan and inventory every entry against the app. |
| <a id="block-6"></a>6 | [T2.15](#t2-15) Hair by date — built 2026-10-09 | — | [5](#block-5) | Build block 6: T2.15, read WB p. 404's washing results and settle the haircut sheet against FPMT. |
| <a id="block-7"></a>7 | [T2.14](#t2-14) The 11th month's 6th — built 2026-10-09 | — | [5](#block-5) | Build block 7: T2.14, read WB p. 226's nine bad days, put them on WB's day and remove Rabten's festival. |
| <a id="block-8"></a>8 | [T2.13](#t2-13) The eight classes and the nāgas — built 2026-10-09 | — | [5](#block-5) | Build block 8: T2.13, read WB pp. 226–235 and build the eight classes' and nāgas' strikes and turnings. |
| <a id="block-9"></a>9 | [E1](#e1) + [E2](#e2) + [E3](#e3), with [E6](#e6)'s election half — built 2026-10-09 | — | [2](#block-2) | Build block 9: the election, E1 engine, E2 screen and E3 hours, with E6's election half. |
| <a id="block-10"></a>10 | [E5](#e5) The 旧暦 election — built 2026-10-09 | — | [9](#block-9) | Build block 10: E5, the 旧暦 election on E2's screen, unranked. |
| <a id="block-11"></a>11 | [E4](#e4) The works' own rising signs — built 2026-10-09 | — | [9](#block-9) | Build block 11: E4, read WB ch. 34's rising signs for each work and build them as the work's own hours. |
| <a id="block-12"></a>12 | [M1](#m1) Meditation timer and bell — built 2026-10-09 | — | — | Build block 12: M1, the meditation timer and the periodic bell. |
| <a id="block-13"></a>13 | [T4](#t4) + [T5](#t5) Element calculation and gender — built 2026-10-09 | — | — | Build block 13: T4 with T5, the element calculation and the gender setting. |
| <a id="block-14"></a>14 | [T2.4](#t2-4)'s reading half + [T2.9](#t2-9) — built 2026-10-10 ([T2.9](#t2-9) 2026-10-09) | — | — (the lent scans are read with the owner's account, `tools/sources/archive_lend.py`) | Build block 14: T2.4's reading half and T2.9, from the borrowed scans. |
| <a id="block-15"></a>15 | [L3](#l3) Russian read-through — the project's read-through built 2026-10-10; the owner's on the phone open | — | The blocks before it that change wording, [42](#block-42)–[51](#block-51) above all | Build block 15: L3, fix what my Russian read-through on the phone found. |
| <a id="block-16"></a>16 | [L4](#l4) Hosted Weblate | — | [15](#block-15); not before 2026-12-28 | Build block 16: L4, set up Hosted Weblate as docs/weblate.md says. |
| <a id="block-17"></a>17 | [T2.20](#t2-20) The times within the day — built 2026-10-09 | — | — | Build block 17: T2.20, read WB ch. 15 on the sun's terms, then build the second mansion, the skipped yoga, Viṣṭi's span and the sun's terms. |
| <a id="block-18"></a>18 | [T2.21](#t2-21) The month's own entries — built 2026-10-10 | — | — | Build block 18: T2.21, read WB's black months, the month's length, the weekday's rise by month, eclipses and seasonal signs, and build what is calculable. |
| <a id="block-19"></a>19 | [T2.22](#t2-22) The *dbyangs 'char* entries and the twelve links — built 2026-10-10 | — | [17](#block-17) | Build block 19: T2.22, read the *dbyangs 'char*'s rules and the twelve links' and write them on the day as WB's almanac does. |
| <a id="block-20"></a>20 | [T2.23](#t2-23) The five planets and the *byed rtsis* — built 2026-10-10, re-scoped by the owner's rule and accepted | — | — | Build block 20: T2.23, the five planets by Janson and the *byed rtsis* by WB, in the month's heading. |
| <a id="block-21"></a>21 | [U6](#u6) The page in the order of strength — built 2026-10-09 | — | [3](#block-3) | Build block 21: U6, the hours above the brief, the works' verdicts apart from the Almanac, only the deciding voice's dot solid. |
| <a id="block-22"></a>22 | [T2.24](#t2-24) The day animals by WB — built 2026-10-10 | — | — | Build block 22: T2.24, read WB's results of the twelve day animals on the scan and join them to the day animal's lists. |
| <a id="block-23"></a>23 | [T2.25](#t2-25) The Lunar day section by use — built 2026-10-10, released as 2.4.1 | — | — | Build block 23: T2.25, keep in the Lunar day section only what can be used or weighed, the hundred feet in Your day. |
| <a id="block-24"></a>24 | [T2.27](#t2-27) WB's days of the twelve links — built 2026-10-15 | [RD1](#rd1) | [42](#block-42), the shape its readings are written in | Build block 24: T2.27, join WB's days of the twelve links (RD1) to the day's link. |
| <a id="block-25"></a>25 | [T2.26](#t2-26) The year's and the month's earth lords — built 2026-10-26 | [RD2](#rd2), [RD3](#rd3), [RD4](#rd4), [RD5](#rd5), [RD6](#rd6) | [42](#block-42), the shape its readings are written in ([D4](#t2-26) settled 2026-10-26) | Build block 25: T2.26, the Year and Month tabs from RD2–RD6, outside the weighing with their remedies as D4 says. |
| <a id="block-26"></a>26 | [H1](#h1) The health screen + [H3](#h3) The menu's groups collapsible — built 2026-10-15 ([D14](#h1) decided the same day) | [RD1](#rd1), [RD7](#rd7), [RD8](#rd8), [RD9](#rd9), [RD10](#rd10) | [42](#block-42), the shape its readings are written in | Build block 26: H1, the Health screen from RD1 and RD7–RD10, with the element's mansions, and H3, the menu's groups collapsible, closed by default, each remembering its last state. |
| <a id="block-27"></a>27 | [P1](#p1) Relatives — built 2026-10-15 | [RD13](#rd13), [RD14](#rd14), [RD15](#rd15), [RD16](#rd16) | — | Build block 27: P1, the relatives of each saved person. |
| <a id="block-28"></a>28 | [R1](#r) The Reckonings section — built 2026-10-15 | [RD11](#rd11) | — | Build block 28: R1, open the Reckonings section from RD11's inventory, with the year of age moved into it. |
| <a id="block-29"></a>29 | [P2](#p2) The natal trigram and the obstacles — built 2026-10-15 | [RD13](#rd13), [RD14](#rd14) | [27](#block-27), [28](#block-28) | Build block 29: P2, the natal trigram and the spouses' and the dead's obstacles. |
| <a id="block-30"></a>30 | [P3](#p3) The bride's departure — built 2026-10-26 | [RD15](#rd15), [RD3](#rd3) | [25](#block-25), [27](#block-27) | Build block 30: P3, the bride's departure for a couple, from RD15. |
| <a id="block-31"></a>31 | [R2](#r)… The reckonings one by one — the birth's parts R2a–R2l (the year's nature, body, mewa, aspects, glory and misfortune, line of births, rise and decline, raising, the precious pebbles) and the marriage match's points R3a–R3m built 2026-10-19; the release build checked over them the same day; R3n–R3q (the hidden and the deeper hidden, the siblings' number, the couple's bad-year pebble, the kinds of wife) built 2026-10-22; R2m the hundred pebbles, R2n the dead's time of death and R2o the moment's trigram and mewa built 2026-10-22; R2z the moment built whole 2026-10-27 | [RD12](#rd12), [RD13](#rd13), [RD15](#rd15), [RD16](#rd16), [RD17](#rd17), each for its reckoning | [28](#block-28); [27](#block-27) for those that need relatives | Build block 31: the next reckoning of R1's list. |
| <a id="block-32"></a>32 | [H2](#h2) The illness reckoning — built: H2a, the elements' illnesses, 2026-10-22; H2b–H2g, the reckoning itself, 2026-10-26 (the moment of falling ill, the five pebbles, the nineteen, the year, the household, the board of the fifteen starting points), with stated choices where the texts are silent (SPEC §5.9.8); left out: the nine classes (229), the masters' reckonings off the board, the verdict on death, the pillow-guard (359), the rites for aspects in decline | [RD12](#rd12), [RD8](#rd8), [RD22](#rd22), [RD41](#rd41), [RD52](#rd52) | [26](#block-26), [28](#block-28) | Build block 32: H2, the illness reckoning on the Health screen, from RD12. |
| <a id="block-33"></a>33 | [A1](#a1) Light theme and colour themes, the first four — built 2026-10-15 | — | [34](#block-34), [42](#block-42), so the screenshots are retaken once | Build block 33: A1, the palette as a theme chosen in Settings: System, Ink (the default), Paper and Night, with the review's checks, the light screen first. |
| <a id="block-34"></a>34 | [U8](#u8) + [U12](#u12) + [U13](#u13) + [U15](#u15) + [U16](#u16) + [U18](#u18) — the day page — built 2026-10-11 | — | — | Build block 34: the day page's review items, the sky line as one line, dots that read, second lines that state the fact, the calendar toggle, the two briefs alike and the first-run line. |
| <a id="block-35"></a>35 | [U9](#u9) + [U17](#u17) — the pickers — built 2026-10-11 | — | — | Build block 35: U9 and U17, the date picker's year and month jump and the bell's time picker. |
| <a id="block-36"></a>36 | [U10](#u10) + [U11](#u11) — the brief's rows and the hours in short form — built 2026-10-11 | — | — | Build block 36: U10 and U11, the Tibetan brief's rows in the 旧暦 brief's form and the hours row in short form. |
| <a id="block-37"></a>37 | [U14](#u14) The election's civil dates — built 2026-10-11 | — | — | Build block 37: U14, the civil day in every cell of the election's grid and the voices line only where a voice stands beside the deciding one. |
| <a id="block-38"></a>38 | [A1](#a1) Maroon and Sepia — built 2026-10-15 | — | [33](#block-33) | Build block 38: A1's Maroon and Sepia, Maroon with its own accent pair and tone set. |
| <a id="block-39"></a>39 | [N1](#n1) The morning notice — built 2026-10-16 | — | [36](#block-36) | Build block 39: N1, the morning notice on the bell's alarms, with the hours in U11's short form. |
| <a id="block-40"></a>40 | [A2](#a2) Large font and landscape — built 2026-10-16 | — | [34](#block-34), [36](#block-36), [42](#block-42) (the sheets' label column) | Build block 40: A2, the page at font scale 1.5 and 2.0, in landscape and on a tablet. |
| <a id="block-41"></a>41 | [W1](#w1) The widget — built 2026-10-16 | — | [33](#block-33), [34](#block-34), [36](#block-36) | Build block 41: W1, the widget in two sizes, in the app's theme. |
| <a id="block-42"></a>42 | [X1](#x1) + [X2](#x2) The shape of a reading, and rows in the catalog — built 2026-10-11, with the lesser black day's example and rule 2's 20-word floor; the examples read by the owner 2026-10-11 | — | — | Build block 42: X1 and X2, the shape rule in SPEC §8.1 with its three examples, the catalog's rows on the sheet and `CatalogTest` holding the shape. |
| <a id="block-43"></a>43 | [X3](#x3) + [X4](#x4) The lunar dates, the weekdays and the goddesses' days — built 2026-10-18, dates 1, 3 and 6 as built until D11 | — | [42](#block-42), [50](#block-50) (D11 for dates 1, 3 and 6) | Build block 43: X3 and X4, the 30 lunar dates, the 7 weekdays and the 8 goddesses' days in the shape, with their Russian. |
| <a id="block-44"></a>44 | [X5](#x5) The combination and the special days — built 2026-10-16 | — | [42](#block-42) | Build block 44: X5, the named combinations, the element pairs, the special days, the burning date and the Vajra Treatise's days in the shape, with their Russian. |
| <a id="block-45"></a>45 | [X6](#x6) The yogas, the karaṇa and the mansions — built 2026-10-17 | — | [42](#block-42) | Build block 45: X6, the yogas, the karaṇa and the mansions in the shape, with their Russian. |
| <a id="block-46"></a>46 | [X7](#x7) The earth lords, Rāhu and the month's entries — built 2026-10-18 | — | [42](#block-42), [50](#block-50) | Build block 46: X7, the earth lords, Rāhu's courses and the month's entries in the shape, each *How it falls* row the shown day's line, with their Russian. |
| <a id="block-47"></a>47 | [X8](#x8) Your day — built 2026-10-18 | — | [42](#block-42) | Build block 47: X8, Your day's readings in the shape, the app's mechanics left to the notes, with their Russian. |
| <a id="block-48"></a>48 | [X9](#x9) The hours panel — built 2026-10-18 | — | [42](#block-42) | Build block 48: X9, the hours panel's readings in the shape, with their Russian. |
| <a id="block-49"></a>49 | [X10](#x10) The rest — built 2026-10-16 | — | [42](#block-42) | Built ahead of 43–48 at the owner's word: the astrologer spirit, the date's mewa and the haircut in the shape, X10's allowance line deleted. (The year sheet went with Y1, block 57.) |
| <a id="block-50"></a>50 | [T2.28](#t2-28) Corrections from the readings — built 2026-10-15 but for D11's three lunar dates and D13, which wait on the owner; the year of age's predictive pebble (RD18, D16) built 2026-10-22; the match's entries built with block 31 (R3n–R3q); point 10's month and day and the orphaning month built 2026-11-05; the moment's fighting mewa (RD145) waits on the owner | [O1](#o1), [O2](#o2), [O3](#o3), [O7](#o7), [O8](#o8) | — (D11 for the three lunar dates) | Build block 50: T2.28, the corrections the O readings found: KP's table, ch. 34's marriage and learning, the 9th hair date, Tuesday's saddling, the horse day's witness; the lunar dates 1, 3 and 6 once D11 is settled. |
| <a id="block-51"></a>51 | [K1](#k1) The 旧暦 terms by the literature (K2 built 2026-10-14) — built 2026-10-19 but for D15, the owner's choices | K1's reading (kyureki.md) | — (D15 for the months, the mansions and 重陽) | Build block 51: K1, the Russian terms by the literature. |
| <a id="block-52"></a>52 | [K4](#k4)… The 旧暦 claims one by one — 二十七宿, the red writing and the 六曜 settled 2026-10-19 | K4's, each for its claim | — (D12 for 天一天上 and the 選日/下段 check) | Build block 52: the next K4 claim whose reading is in kyureki.md. |
| <a id="block-53"></a>53 | [K3](#k3) The old texts behind the 旧暦 readings, in order — D8 settled 2026-10-30, built 2026-10-31 but for D8's open points | K3-R, the 暦林問答集 (kyureki.md) | — (D8's open points: the 十二直's lists, the solar terms' voice) | Build block 53: K3 as D8 settled it: the 暦林問答集 first in SPEC §8.1, §64's ranking in §7.5 and the election, 日遊's wording, the 三箇の悪日 kept from the 簠簋内伝. |
| <a id="block-54"></a>54 | [K5](#k5) The direction gods — D9 settled 2026-10-30: a year sheet, built 2026-10-31; 金神 and the daily 天一 row decided and built the same day | [RD92](#rd92); [RD93](#rd93), [RD94](#rd94) for 金神 and the daily rows | [53](#block-53) (D9's open points: 金神, the daily directions) | Build block 54: K5, the year sheet of the 八将神 from the 恵方 row, from RD92; 金神 and the daily directions as the owner decides on RD93 and RD94. |
| <a id="block-56"></a>56 | [K6](#k6) Every 旧暦 row opens — built 2026-10-18 | K6-R, the 24 terms' readings (kyureki.md) | — | Build block 56: K6, a sheet for the solar term from K6-R with its reading, details and the full ring, balloons on the 干支 rows and on the date and moon, the month and year stars opening their own sheets. |
| <a id="block-55"></a>55 | [U19](#u19) Pinned works — built 2026-10-21 | — | — | Build block 55: U19, pinned works in the Works section, with a chooser and a preference. |
| <a id="block-57"></a>57 | [Y1](#y1) The year of age read for action — built 2026-10-16 | WB vol. 1, ch. 25, pp. 440–442, the twelve stages' spirits and rites (read in the block, health.md) | — | Built: Y1, the year sheet in the shape with its remedies as rows, what matters most, the stages named as the energy's. |
| <a id="block-58"></a>58 | [R4](#r) Combining the readings: each reckoning's verdicts, its sets read whole, what to do — the match's Together built 2026-10-22; open questions 184–185 answered 2026-10-22 (the hundred's board from KD vol. 3, pp. 716–718; no count of a child's conditions in any text) and the birth's Together built the same day, its twenty-one children not named; the year of age's Together built 2026-10-25 by D17 (the four classes gathered, its obstacles graded by Mipham's meetings); the household round built 2026-11-05 as [R9](#r9) | [RD18](#rd18), [RD19](#rd19), [RD20](#rd20), [RD21](#rd21), [RD23](#rd23), [RD24](#rd24), [RD25](#rd25), [RD26](#rd26), [RD27](#rd27), [RD28](#rd28), [RD29](#rd29); [RD22](#rd22) with H2 | [31](#block-31) | Build block 58: R4, a Together part atop each reckoning built, from RD18–RD28: the verdicts, the sets, whose aspect and which part of life, the rule for the whole and the remedies gathered. |
| <a id="block-59"></a>59 | [A3](#a3) Glyphs of their own for the works their family draws badly — built 2026-10-25 | — | — | Built: A3, `CueGlyphs.WORK`, 53 works with glyphs of their own in the breakdown and the election. |
| <a id="block-60"></a>60 | [R5](#r) The Together everywhere WB sums up: the moment, the dead, the illness — built 2026-10-31; the dead's general verdict over its twenty roots and a class for q428's differences left | [RD58](#rd58) | [58](#block-58) | Build block 60: R5, a Together section atop the moment, atop each of the dead and under the illness's first row, by WB's summing rules as RD58 reads them, the remedies below gathered; the thirty and the circles keep none. |
| <a id="block-61"></a>61 | [R6](#r6) Ch. 27's small reckonings by the year — built 2026-10-31 | — (read: RD38, RD51, RD89, RD90) | [28](#block-28) | Build block 61: R6, the small reckonings of ch. 27 pp. 16–17 and 20 in a view of their own, stepped by year: the four starting points of p. 17, the la's wandering and decline, the grave entered, the end of the womb, the birth by the year's power. |
| <a id="block-62"></a>62 | [R7](#r7) The rounds by age of pp. 18–19 — built 2026-10-31 | — (read: RD38, RD51, RD90) | [61](#block-61) | Build block 62: R7, the rounds by age of ch. 27 pp. 18–19 in the small reckonings, the man's or the woman's: their cuts and the signs as printed. |
| <a id="block-63"></a>63 | [R8](#r8) The reckonings of the time of asking — built 2026-10-31 | — (read: RD38, RD51) | [62](#block-62) | Build block 63: R8, Brahmā's month for one gone abroad and the signs of the time of asking (ch. 27 pp. 13–14, 19) on the moment's view. |
| <a id="block-64"></a>64 | [M2](#m2) The prayer wheel: a screen and a live wallpaper — built 2026-11-04 | — (the mantras: claims/wheel-mantras.md) | [M1](#m1) | Build block 64: M2, the prayer wheel on a screen of its own and as a live wallpaper. |
| <a id="block-65"></a>65 | [M2](#m2) Counter-clockwise for a line of one's own — built 2026-11-05 | — | [64](#block-64) | Build block 65: a switch under "Your own", off by default, turning the wheel counter-clockwise, with the owner's caution beside it. |
| <a id="block-72"></a>72 | [R2](#r) The clan taken out of the app — done 2026-11-06 | — | — | Done: the person's clan, the special pebble and what cuts the father removed; only Tibetans have a clan (the owner). |
| <a id="block-73"></a>73 | [S2](#s2) Read a dream: the watch of the night, the images, the dreamer ill | [RD207](#rd207)–[RD212](#rd212), [RD215](#rd215), [RD216](#rd216) ([RD214](#rd214) beside) | — | Build block 73: S2, the dream reading in the Reckonings: the night and its watch (chips or a clock time), the images as a grouped, searchable multi-select with WB first, "The dreamer is ill" as a checkbox that brings the Four Tantras' readings into the rows, each image's verdict as a row and the watch's fruit date opening its day; no net verdict. |
| <a id="block-71"></a>71 | [R2](#r) Who does not handle the corpse — built 2026-11-06 | — (read: RD33) | [31](#block-31) | Built: p. 93's offices by the mewa and the trigram of death. |
| <a id="block-70"></a>70 | [R2](#r) The death's eye, in part — built 2026-11-06 | — (read: RD33) | [31](#block-31) | Built: p. 94's eight sending places by the trigram of death, and the living of the dead's year animal and its seventh named. |
| <a id="block-69"></a>69 | [R9](#r9) The mewa's copper vessel — built 2026-11-06 | — (read: RD30) | [58](#block-58) | Built: pp. 54–55's readings for a birth mewa half the household or more share, a section of the household round. |
| <a id="block-68"></a>68 | [R2](#r) Who is not made an old one — built 2026-11-06 | — (read: RD33) | [66](#block-66) | Built: the old born and dead on the two-black, p. 82, a row in place of the kind. |
| <a id="block-67"></a>67 | [K5](#k5) The 大将軍's outings as a daily row — built 2026-11-06 | — (read: RD92, RD172) | [54](#block-54) | Built: the 大将軍's day by the 暦林問答集 §54 under 天一神's row, its five-day outings and its own place. |
| <a id="block-66"></a>66 | [R2](#r) The old dead's kinds, and what cuts the father by the clan (taken out the same day, block [72](#block-72)) — built 2026-11-06 | — (read: RD33, RD164, RD174) | [31](#block-31) | Built: the nine rounds of the old by sex and year of age, the ages they leave read by WB's other round (q309), and in The clan the birth's month and year that overcome the clan's element (p. 349, q566). |

## Readings

Reading the sources is apart from building (the owner, 2026-10-11): a
reading needs no code, no emulator and no build before it, only the
readings in its "Needs", so readings run ahead of the blocks, in any
session. A reading is done when its topic file holds it: the passages in
Tibetan script as read on the scan, with edition, page and image number,
the English beside them, and everything else it learned
([sources/PLAN.md](sources/PLAN.md), *The knowledge pool*). It is marked
"— done YYYY-MM-DD" after its name, keeps its number, and its topic file
names it; `SourcesTest` checks both. **"Do the next reading"** means the
lowest-numbered reading not done whose needs are done. Page references
are the 1996 edition's, from BDRC's etext (IE0OPI51524892); the readings
check them on the scans.

| # | Reading | Source and pages | Needs | For | Topic file | Prompt |
| --- | --- | --- | --- | --- | --- | --- |
| <a id="rd1"></a>RD1 | WB's days of the twelve links, and which day they mean — done 2026-10-11 | WB vol. 2, pp. 351–356 (img. 359–364); against vol. 1, p. 177's count; p. 356's links on the hours | — | [24](#block-24), [26](#block-26) | day-letters.md | Read RD1: WB vol. 2 pp. 351–356 on the scan, each link's day with its works, directions, birth, loss, illness and remedy, and whether its day is the almanac's link. |
| <a id="rd2"></a>RD2 | What the almanac writes of the year's and month's earth lords — done 2026-10-11 | WB vol. 1, pp. 174–176 (img. 184–186), the year's front matter and the month's head; the model almanac's month heads, pp. 154–171 (img. 164–181) | — | [25](#block-25) | almanac-page.md, month-entries.md | Read RD2: WB vol. 1 pp. 174–176 and the model almanac's month heads, for how the year's and the month's earth lords are written. |
| <a id="rd3"></a>RD3 | WB's earth lords of the year — done 2026-10-11 | WB vol. 2, ch. 31, pp. 175–198 (img. 183–206): the kings and ministers, the year's *hal khyi* and *sa rgyal*, the black years, *nag mo dgu skor*, the sme ba's gods, the palace gods, the sky dog of brides (p. 198) | — | [25](#block-25), [30](#block-30) | year-month-earth-lords.md | Read RD3: WB vol. 2 pp. 175–198, each earth lord of the year with its course, place, works and remedy. |
| <a id="rd4"></a>RD4 | WB's earth lords of the month — done 2026-10-11 | WB vol. 2, ch. 31, pp. 199–221 (img. 207–229): the bad directions, *bum stong*, the month's *hal khyi*, *lto 'phye*, the month's *nag mo dgu skor*, the scattered earth lords | — | [25](#block-25) | year-month-earth-lords.md | Read RD4: WB vol. 2 pp. 199–221, each earth lord of the month with its course, place, works and remedy. |
| <a id="rd5"></a>RD5 | KP's boxes on the Chinese almanac's days — done 2026-10-12 (boxes 56–64 machine-read only; KP2 past box 54 and KP's second part, img. 87–118, unread) | KP and KP2, every box naming *se byi*, *hang phan*, *zla klung*, *se shar*, the *se*, *pi ling*, *tsang kun*, *dbul*, *nyi nag*, *gnam stong* ([kp-activities.md](sources/kp-activities.md), "not calculated") | [RD3](#rd3), [RD4](#rd4) | [25](#block-25) | kp-activities.md | Read RD5: KP's boxes for the earth lords RD3 and RD4 identify, their works on the scan. |
| <a id="rd6"></a>RD6 | Any weight the texts give the earth lords — done 2026-10-12 | WB's notes (vol. 2, pp. 474–545), KD's readers of WB, KP's prose (img. 13–14): the earth lords against the day's factors | [RD3](#rd3), [RD4](#rd4) | [25](#block-25) ([D4](#t2-26)) | weighing.md | Read RD6: search WB's notes and its readers for a rank of the earth lords, for the owner's D4. |
| <a id="rd7"></a>RD7 | Dr. Nida Chenagtsang's works — done 2026-10-12 (from the catalogues; KL's la chapter and moxa dates read on the scan; GC and RS only by their contents and records, the English books unread) | Catalogues (library and publishers' records): titles, editions, his name in Tibetan as printed; the books themselves | — | [26](#block-26), [32](#block-32) | health.md (new) | Read RD7: identify Dr. Nida Chenagtsang's published works from catalogues and list them with what each covers. |
| <a id="rd8"></a>RD8 | What his works cite — done 2026-10-12 (KL's whole etext and its quotation pages on the scan; GC and RS by BDRC search snippets only; the classical lines located in machine text for RD9) | His works (RD7), for the classical passages they rest on: the la, the life force, health and illness by date, month, hour and age | [RD7](#rd7) | [26](#block-26), [32](#block-32) | health.md (new) | Read RD8: list every classical passage Dr. Chenagtsang's works cite for the la, the life force and illness by time. |
| <a id="rd9"></a>RD9 | The classical texts he cites — done 2026-10-12 (on the scans: the Four Tantras in the Serthang and Drathang prints, the Final Tantra's la pulse found, Mipham's treatise, Deumar's table on lent scans; KL's monthly course is Deumar's Tsuglag Rigje column with Mipham's syllables; questions 108–111) | The *rgyud bzhi*, its commentaries and the calendar-and-medicine texts RD8 names, on their scans (BDRC) | [RD8](#rd8) | [26](#block-26) | health.md (new) | Read RD9: the passages RD8 lists, on their scans. |
| <a id="rd10"></a>RD10 | WB on health — done 2026-10-12 (every quoted passage read on the scan; ch. 20's narrative pages and the Kālacakra la list past the 5th date outlined from the machine reading; KL's tables found in WB but its six moxa dates and syllables) | WB vol. 1, ch. 20 (pp. 218–251) and 21 (pp. 252–258) on the la and the life force; vol. 2, ch. 33's illness lines (dates pp. 297–304, weekdays pp. 308–312, day animals pp. 356–359); vol. 1, pp. 403–405 (bloodletting and moxa by the year of age's mewa); pp. 330, 338 on the enemy and grave mansions | [RD1](#rd1) | [26](#block-26) | health.md (new) | Read RD10: WB's passages on the la, the life force and illness, on the scan. |
| <a id="rd11"></a>RD11 | The reckonings' inventory — done 2026-10-11 | WB vol. 1, ch. 20–26 (pp. 218 to the volume's end); vol. 2, ch. 27–30 (pp. 3–173); the reckoning for the dead (ch. 31, the notes pp. 474–545): each chapter's outline and count of pebbles | — | [27](#block-27), [28](#block-28) and every reckoning | reckonings.md | Read RD11: the outline of each reckoning chapter, what it reckons, from whose dates, what is a casting, and its size. |
| <a id="rd12"></a>RD12 | WB's illness reckoning — done 2026-10-12 (quoted: pp. 417–421, 438–439; the rest outline only, machine-read; pp. 428–429 missing from BDRC's scan, read in WBZ by machine) | WB vol. 1, ch. 25, pp. 417–452 (img. 427–462): fifteen starting points; the elements' illnesses, pp. 438–439 | [RD11](#rd11); [RD8](#rd8) beside | [32](#block-32) | health.md (new) | Read RD12: WB ch. 25 on the scan. |
| <a id="rd13"></a>RD13 | The birth reckoning and the natal trigram — done 2026-10-12 (the natal trigram, the pebbles of pp. 259–262 and the readings of pp. 271–272, 294, 326, 347 read on the scan, with the *Moonbeams* f. 9a on Dharmaśrī's open print; the rest of ch. 22 and the notes past p. 476 outlined from Yigdzin-1 and the etext) | WB vol. 1, ch. 22, pp. 259–350 (img. 269–360): the natal trigram from the mother's age first, the *Moonbeams* f. 12a (KD vol. 3, lent scans) beside; then the rest | [RD11](#rd11) | [27](#block-27), [29](#block-29), [31](#block-31) | year-of-life.md | Read RD13: WB ch. 22, the natal trigram first. |
| <a id="rd14"></a>RD14 | The obstacles that need other people — done 2026-10-12 (pp. 380, 385–390, 396–400, 407–409 and ff. 180b–182a = pp. 286–288 read on the scan where quoted; the parents' nine-multiples, pp. 412–413, and the notes' pp. 493–494 from Yigdzin-1 and the etext) | WB vol. 1, ch. 24: the secret obstacle, the spouses' shared trigram and obstacles, the ancestors' tombs and *dur keg*, the household readings (p. 408) and pebbles (pp. 385–387); the natal trigram's readings (WB ff. 180b–182a, found in the 1996 edition) | [RD11](#rd11) | [27](#block-27), [29](#block-29) | year-of-life.md | Read RD14: WB ch. 24's obstacles that need the mother, spouse or the dead, on the scan. |
| <a id="rd15"></a>RD15 | The marriage chapter — done 2026-10-12 (the departure and the remedies, pp. 365–378, read on the scan; the match, pp. 351–364, Yigdzin-1 alone, no MITRA; the notes' section 6 past p. 525 and ch. 30, p. 152, from the etext) | WB vol. 1, ch. 23, pp. 351–379 (img. 361–389): the match, the departure (from p. 365, seventeen parts), the remedies | [RD11](#rd11); [RD3](#rd3) for the sky dog of brides | [27](#block-27), [30](#block-30), [31](#block-31) | marriage.md (new) | Read RD15: WB ch. 23 on the scan, the departure first. |
| <a id="rd16"></a>RD16 | The reckoning for the dead — done 2026-10-12 (the sizes, pp. 44–51, the list of examinations and the notes' inputs, pp. 499–500, 509, read on the scan; the fifteen examinations' results, pp. 51–143, and the notes past p. 500 outlined from Yigdzin-1 and the etext, their readings and rites not read on the scan) | WB's *gshin rtsis*, the copper vessel (*zangs kyam*): vol. 2, ch. 29, pp. 44–143 (img. 52–151), the sizes pp. 44–51 first, and the notes' section 4, pp. 499–522 (img. 507–530); ch. 31 holds none (RD11): what it needs of the dead and the living | [RD11](#rd11) | [27](#block-27), [31](#block-31) | reckonings.md | Read RD16: WB's reckoning for the dead, what it asks and what it gives. |
| <a id="rd17"></a>RD17 | The other reckonings — done 2026-10-12 (each chapter's size, inputs and first reading on the scan, with the notes' pp. 532, 535, 538; the rest of ch. 26, 27 and 30 and all of ch. 28 but its sum outlined from Yigdzin-1 and the etext) | WB vol. 1, ch. 26 (from p. 453); vol. 2, ch. 27 (pp. 3–25), 28 (pp. 26–43), 30 (pp. 144–173), each in full, with the notes' sections 7 and 8 (pp. 532–541; ch. 29 is RD16's, RD11) | [RD11](#rd11) | [31](#block-31) | reckonings.md | Read RD17: the next reckoning chapter RD11 sorts as calculable. |
| <a id="rd18"></a>RD18 | The verdict pebble, chapter by chapter — done 2026-10-20: every «ཁ་དམར» and «བཏང་གཅོད» of ch. 21–30 and the notes' rules read on the scan; each place counts once by its colour, a tie lays a speckled pebble (question 167 answered); the verdict is laid and spoken, no red mark; Dorje's chart 6.2 follows the notes with a son's meeting black; the year of age's count for a build ([T2.28](#t2-28)); questions 171–174 | WB vol. 1, p. 258 (img. 268), pp. 260–262 (img. 270–272), pp. 380–385 (img. 390–395), p. 407 (img. 417); vol. 2, pp. 44–51 (img. 52–59), p. 172 (img. 180); every other «ཁ་དམར» and «བཏང་གཅོད» of ch. 22–30 that verdicts.md lists; the notes, p. 490 (img. 498), on the scan; Gyurme Dorje's charts 6.2 and 8.1 beside | [RD11](#rd11) | [58](#block-58) | verdicts.md (new) | Read RD18: every rule WB and its notes give for laying a verdict pebble, on the scan: what is counted, what a tie lays, the red mark; settle open question 167. |
| <a id="rd19"></a>RD19 | The birth read whole: its sets, its twenty-one children, its close — done 2026-10-20: ch. 22 pp. 328–342 and 349 read on the Gser thang reprint and the 1996 scan; the hundred's verdicts, the general verdict by where the pebbles lie (p. 331), the twenty sets (pp. 334–335) and p. 338's read whole, the luck ruling the rest (p. 335); the twenty-one children each named by several conditions, none by a date alone, no count; p. 349's close; questions 175–185 | WB vol. 1, ch. 22, pp. 328–342 (img. 338–352): the hundred pebbles' readings, the sets of p. 338, the secret *yang tig*'s twenty-one children; p. 349 (img. 359), the rule for the whole; the notes' section 1 on them (vol. 2, pp. 474–489, img. 482–497), on the scan | [RD13](#rd13); [RD18](#rd18) | [58](#block-58) | year-of-life.md, verdicts.md | Read RD19: WB ch. 22 pp. 328–342 and 349 on the scan, the sets read whole, the twenty-one children with which of their conditions are dates, and the chapter's rule for the whole. |
| <a id="rd20"></a>RD20 | The year of age gathered: the four classes and the household — done 2026-10-20: p. 385's four classes are the four aspects, their verdicts gathered and the household's round built on each member's four; the notes' count (p. 490) read on the scan, a mother meeting's three white as one; the notes' worked example (p. 489) and household fold (pp. 490–491) cell by cell; every obstacle fought against the aspects (pp. 492–495); questions 168–170 | WB vol. 1, ch. 24, p. 385 (img. 395), «སྡེ་བཞིའི་ཁ་དམར་བསྡུས», and the household's verdicts, pp. 385–387, 407–408 (img. 395–397, 417–418); the notes' section 2 (vol. 2, pp. 489–495, img. 497–503), on the scan | [RD14](#rd14); [RD18](#rd18) | [58](#block-58) | year-of-life.md, verdicts.md | Read RD20: how ch. 24 and the notes gather the year of age's verdicts, for one person and for a household. |
| <a id="rd21"></a>RD21 | The marriage match concluded — done 2026-10-20: the kind of spouse is the third point's, aspect by aspect (mother or son karma, friend kin, enemy enmity; earth or water meeting itself karma, fire, iron or wood kin, by Sangye Gyatso's reply 113 and the notes p. 524), graded best, middling and last; the whole judged "in general", no rule for a mixed match; the bad years one deciding pebble for the couple (p. 524); point 7 summed and read by parity (p. 525, question 166 answered); a bad karma reckoning overrules a good match (p. 364; notes p. 526); p. 355's «ཆུང་མར» on the Zhol print; Sangye Gyatso's reply 117 not read; for a build ([T2.28](#t2-28)); questions 208–211 | WB vol. 1, ch. 23, p. 351 (img. 361), the spouse of karma, of kin or of enmity and «སྐར་འབྲས་སྤྱི་སྡོམ»; p. 359 (img. 369), point 7; p. 364 (img. 374), point 13; the notes' section 6, vol. 2, pp. 523–526 (img. 531–534), on the scan | [RD15](#rd15); [RD18](#rd18) | [58](#block-58) | marriage.md, verdicts.md | Read RD21: the match's conclusion on the scan: how the fifteen points make the spouse of karma, kin or enmity and the general sum, the notes' "in general" for the bad years and the aspects, point 7's count (open question 166). |
| <a id="rd22"></a>RD22 | The illness identified: the element that is most — done 2026-10-21: two counts: the board of the fifteen starting points and the sixteenth against the four aspects, three groups of five (man, woman, children), its white against black in nine classes from an illness of karma to one of spirits, each with its rites (p. 425; notes pp. 495–496), the seven great reckonings read off it; and apart from it the illness named by five element pebbles of the time of falling ill, the year's, month's, day's and hour's life force and the trigram that is most (pp. 435–436), shares by p. 438 (half and half both, a third and two thirds spirits and conditions, else the element most); the notes count seven, «two wood, two fire, three iron», iron's the illness (p. 497 on the scan; the etext's elision corrected); no rule for four trigrams of four elements; the rites by the black plank-tree and deciding pebble, the four slayers chief (notes p. 498), sized to land, person and earlier rites (p. 451); question 181 narrowed; questions 226–232 | WB vol. 1, ch. 25, p. 435 (img. 445) and the close, pp. 451–452 (img. 461–462); the notes' section 3, vol. 2, pp. 495–499 (img. 503–507), p. 497 the shares, on the scan | [RD12](#rd12); [RD18](#rd18) | [32](#block-32), [58](#block-58) | health.md, verdicts.md | Read RD22: how ch. 25 and the notes join the fifteen starting points into one illness, its shares and its rite. |
| <a id="rd23"></a>RD23 | The verdicts of the dead, the thirty, the circles and the moment — done 2026-10-21: no verdict over a whole reckoning in ch. 26, 27, 29 or 30; the dead's thirteenth point lays one verdict over the five aspects' and six earlier findings, more white white, more black black, a tie a pair, and sizes the rites great, middling or small (p. 50), each black root remedied, its 202 identifying pebbles remedies in thirteen classes each with its verdict (notes p. 517); the funeral share by two deciding pebbles (notes p. 500); a white, speckled and black rite for each trigram, full or short by the household's means (pp. 104–105, 114); ch. 26's grave site read whole (p. 468) and a group of three by its greater share (notes p. 533); ch. 27's meadow's ornament 360 and Sprugtön's 75 with five verdicts by aspect and remedies by aspect (pp. 22–23); ch. 30 lays the moment's verdicts on the figure of a man lying down, no rite (pp. 163, 172); question 174 narrowed; questions 212–221 | WB vol. 2, ch. 29, p. 50 (img. 58), the thirteenth point; vol. 1, ch. 26, p. 471 (img. 481); vol. 2, ch. 27, pp. 22–23 (img. 30–31), the aspects' domains and remedies; ch. 30, p. 172 (img. 180); the notes' sections 4 and 7, on the scan | [RD16](#rd16), [RD17](#rd17); [RD18](#rd18) | [58](#block-58) | reckonings.md, verdicts.md | Read RD23: how ch. 26, 27, 29 and 30 gather their verdicts and turn them into remedies, on the scan. |
| <a id="rd24"></a>RD24 | Whose aspect, which part of life, and the reckonings with each other — done 2026-10-20: one rule for whose aspect in ch. 20 and 24 (a man in his prime the life force, the young the body, women the power, the noble and learned the luck; pp. 242, 415–416), ch. 26's board adding the minister, boys and girls (p. 472); the parts of life by the birth's year, month, day and hour (p. 261) and by ch. 26's board (p. 471, «ཡར་གསུམ» on WBZ); no reckoning ranked over another as a whole: the birth the unchanging ground, the year of age the changing part, both on the present month, day and hour (p. 335; notes p. 484), the birth "cannot deceive" (p. 349) and outweighs a good match (notes p. 526); question 181 narrowed; questions 203–207 | WB vol. 1, p. 242 (img. 252), p. 261 (img. 271), p. 471 (img. 481), pp. 415–416 (img. 425–426); ch. 20–24 and the notes searched for a rule that reads the birth with the year of age and with the month, day and hour, on the scan | [RD18](#rd18) | [58](#block-58) | verdicts.md | Read RD24: WB's rules for whose aspect matters, which part of life a sign rules, and whether one reckoning stands over another. |
| <a id="rd25"></a>RD25 | Ch. 20's classes of texts and the deciding texts — done 2026-10-21: the five classes are classes of tantra, the six summaries six fields of reckoning (pp. 242–243), the *Clear Lamp*'s six summaries six reckonings (p. 248), none the deciding; WB names four deciding texts, «བཏང་གཅོད» the trigram's fifth tantra and the *Black Deciding* (p. 245), the verdict text *Bsam ka bi ho* (p. 246), the *Lamp of the Magic of Deciding*, third of the twenty commentaries (p. 248), none in BDRC's catalogue; the root in five topics, the Mentsikhang's commentary in twenty and the *Little Red Tiger-Head* found in the catalogue, not read (BDRC's image server down); the *Clear Lamp*'s rounds five with the secret key and the hidden finger-pointing apart (p. 249, RD27 corrected); questions 222 and 223 narrowed; questions 240–242 | WB vol. 1, pp. 242–250 (img. 252–260), the five classes and six summaries, «བཏང་གཅོད» among them (p. 245) and the *Lamp of the Magic of Deciding* (p. 248), on the scan; BDRC's catalogue for the titles | [RD11](#rd11) | [58](#block-58) | verdicts.md | Read RD25: ch. 20 on the classes of the reckoning's texts, and whether a deciding text survives. |
| <a id="rd26"></a>RD26 | Mipham's notes on condensing — done 2026-10-20: a set's deciding pebble is the majority of its five (p. 475's table cell by cell), a pebble with no fight white or black by its reading, "half" read middling (p. 488); the count is a frame and a long count "mere elaboration" to be condensed for practice (pp. 474, 513, 523); no section closes on a single verdict for a whole reckoning; the conclusion is a written sheet of chart, results and rites, or a reckoning memorandum of the decisive points for the patron (p. 539, for RD28); questions 197–202 | WB vol. 2, pp. 474–476 (img. 482–484), p. 490 (img. 498), p. 513 (img. 521) and the close of each of the notes' eight sections, on the scan | [RD18](#rd18) | [58](#block-58) | verdicts.md | Read RD26: what the notes say about condensing a reckoning to its essence and laying its verdicts. |
| <a id="rd27"></a>RD27 | Dharmaśrī's *Moonbeams*: how the abridgement orders and closes a reckoning — done 2026-10-21: a general part (the nine means, the stages, nine-multiples and tombs) and six reckonings, each in one order: the size, the base, the root pebbles and those fought against them, the stages, the verdict counted white and black, the harsh pebbles, the other masters' ways; no chapter reads its pebbles, each sends the results to other works (the *Clear Lamp*'s commentary in twenty and others) and closes on remedy, the dead's twelfth section itself the rite; the year of age gathered into five general totals by domain (f. 24a), as WB ch. 27; WB pp. 248–249 name it the "condensed instructions in six rounds"; KD's copy differs in headings and words only; question 190 narrowed; questions 222–225 | DZ, BDRC I2533 img. 5–64 (open scans), whole; KD vol. 3's copy where it differs (lent scans) | [RD24](#rd24) | [58](#block-58) | verdicts.md | Read RD27: the *Moonbeams* for the order it gives a reckoning and how it closes it, against WB. |
| <a id="rd28"></a>RD28 | Written reckonings: the practitioners' form — done 2026-10-21; its two unfetched scans read 2026-10-22 as [RD29](#rd29): Kun dga' rig 'dzin's *Sheets of Some Needed Examples* (KD vol. 3, pp. 713–729, lent scans) set one sheet of charts per reckoning, inputs, then the pebbles aspect by aspect, then a last row of deciding or verdict pebbles, the verdict laid with a count (p. 724); no sheet closes on written words but the dead's, which reads the four deciding pebbles in WB's lines (vol. 2, p. 56) and then names the results, rites, the hour of carrying out, the burial and the rebirth to draw out (p. 729), as Mipham's p. 539; Lhamo Tsultrim's manual closes each reckoning on the results taken into practice (etext); no sheet or manual gives one verdict for a whole reckoning; questions 243–246 | BDRC's catalogue and full-text search (bdrc_search.py) for written birth and year reckonings and manuals for writing them («རྩིས་ཡིག», «སྐྱེས་རྩིས», «ལོ་རྩིས», «ཀེག་རྩིས»); those found, on their scans: their parts and how they close. A witness below WB: followed only where WB is silent and the manual rests on it | [RD26](#rd26), [RD27](#rd27) | [58](#block-58) | verdicts.md | Read RD28: find written reckonings and their manuals, and record the form a conclusion takes in them. |
| <a id="rd29"></a>RD29 | Written reckonings on the open scans — done 2026-10-22: neither is one person's reckoning; the LTWA manuscript is a reference sheet for any death (look-up cells, then the four deciding pebbles each read black, white and speckled, one rule over the four by colour, burial, rebirth, rites), the Mentsikhang print eight digests of WB (a life reckoning with blanks for one child, *The Beautiful Rosary* of the 136 pebbles closing on WB's general verdict by where the pebbles lie, p. 331, an obstacle book closing on remedies, *The Precious Key* for the dead, almanac results, elections, a star primer, the *Moonbeams*); both close on results with rites, as KD's dead sheet and Mipham's p. 539, never one verdict for a whole reckoning; the manuscript's speckled readings and its rule over the four depart from WB; cursive prose machine-read only; questions 253–257 | The LTWA manuscript *A Written Reckoning of the Dead, Set Out Briefly on a Sheet* (BDRC I1LT0800, img. 359–401; MW1LT0800_O1LT0800_D13407) and the Lhasa Mentsikhang's 1987 blockprint of life reckonings and reckoning books (I1KG12897, 656 images, no etext; MW1KG12790), on the scan; both failed to load on 2026-10-21 | [RD28](#rd28) | [58](#block-58) | verdicts.md | Read RD29: the two written reckonings RD28 found and could not fetch, for their parts and how they close, against KD's sheets and WB. |
| <a id="rd30"></a>RD30 | The dead's living household and the cause of death — done 2026-10-23: the living household read on the reprint: the paternal kin by its element against how many members' years fall on each animal, the birth mewa, the twelve la pebbles (twelve places set by the clan element, a white pebble under each living member's year and a black on its seventh, three verdicts), the household's years together, the mewa's copper vessel colour by colour, the six lamps (the notes: a struck lamp black, else white); the second examination gives reasons only, no rites: the four deciding pebbles, the element cuts, the la cut in the month seventh from the death's animal, the trigram of death and the mewa; the notes pp. 499–503 with the wood clan's chart; no MITRA, agy's model no longer offered; questions 267–274 | WB vol. 2, ch. 29, pp. 51–55 (img. 59–63), the living household: the kin's element and years, the mewa pebbles, the twelve la pebbles in three verdicts, the mewa's copper vessel, the six lamps; pp. 57–58 (img. 65–66), the second examination, cause and reason; the notes' section 4 where it explains them, pp. 499–503 (img. 507–511); on the scan | [RD16](#rd16); [RD23](#rd23) | [31](#block-31) | corpus/wb/ch29.md | Read RD30: the dead's living household and the second examination on the scan, each reading with its remedy, in the shape a build needs (by which pebble, which input). |
| <a id="rd31"></a>RD31 | The dead's third examination: which demon carried it off — done 2026-10-23: the third examination read on the reprint, seven tables: by the dead's year animal (12: whom the eating demon leads, where it hides, to whom the executioner goes, the rite, when it returns), the trigram of death (8, with four heart-crushings others hold), the mewa (9), the day's animal (6 days only, each the month the life was cut), the weekday by its planet's state (dar, gud, zhud, gyod), the mansion (28, Abhijit with them) and the double hour (12); the notes (p. 503): the trigram and mewa of the year of death, the hiding by the dead's birth year; questions 282–287 | WB vol. 2, ch. 29, pp. 58–68 (img. 66–76): by the dead's year animal, the trigram of death, the mewa, the day's animal, the weekday, the mansion and the hour of death; the eating demon's object, the executioner's next year, the rites and skulls; the notes' section 4 where it explains them; on the scan | [RD16](#rd16) | [31](#block-31) | corpus/wb/ch29-demons.md | Read RD31: ch. 29's third examination on the scan, every table entry by its input (year animal, trigram, mewa, day, weekday, mansion, hour) with what it names and its rite. |
| <a id="rd32"></a>RD32 | The dead's examinations 4–8: funeral share, rebirth, virtue, the corpse, the graves — done 2026-10-23: examinations 4–8 read on the reprint: the eighteen funeral shares and the thirteen ways, the share by the power's and element's deciding pebbles (both white large, mixed middling, both black none and a secret rite), held in the enemy month of the dead's life force and never in the living's seventh, four-executioner or grave-entering months (the notes, p. 500); the rebirth by its deciding pebble on twelve roots (white a god, speckled a human, black the lower realms), by the mewa of death with the image to make, the aspects, the day animal, the hour, the stage and the posture; the twelve ways of virtue and a year table WB calls without certain source; the thirteen kinds of corpse; the five graves with what each refuses and when each is good; Yigdzin-1 drops the dog day (p. 71); no MITRA; questions 275–281 | WB vol. 2, ch. 29, pp. 68–75 (img. 76–83): the funeral share and the element's pebble (4), rebirth by the mewa, the aspects, the day, the hour and the posture (5), help by virtue by the year of death (6), the kinds of corpse (7), the five graves (8); the notes, pp. 500, 503–510 (img. 508, 511–518) where they explain them; on the scan | [RD16](#rd16); [RD23](#rd23) | [31](#block-31) | corpus/wb/ch29-funeral.md | Read RD32: ch. 29's examinations 4–8 on the scan, each by its deciding pebble or input, with its readings and remedies. |
| <a id="rd33"></a>RD33 | The dead's examinations 9–11: provisions, repetition, the death's eye — done 2026-10-23: examinations 9–11 read on the reprint, 9 passages: the provisions by the trigram of death, the nine old men and nine old women by age at death, the Clear Lamp's kinds and who is not made an old one, the small grave, the graves by rank and craft; the eight great strikes each with its criteria and remedy (belly, heart, the nine-eye at 9 to 81, the grave days 6, 16, 26, illness-death-grave by aspect, kung kung, iron-four fire-eight, planets and mansions, wrong deeds), the copper vessel's sounding (100 pebbles, 25 places), the handlers by rise and decline, the posture and the omens; the death's eye and the joined hands (p. 94); the notes' pp. 504–512 machine-read (the sounding's board 25 × 4, the 13 postures, the eye's 13 roots); Yigdzin-1's dropped lines restored; questions 307–316 | WB vol. 2, ch. 29, pp. 75–94 (img. 83–102): the provisions by trigram and the nine kinds of the old (9), the eight great strikes and their small strikes, the copper vessel's sounding, the omens (10), the death's eye (11); the notes where they explain them; on the scan | [RD16](#rd16) | [31](#block-31) | corpus/wb/ch29-provisions.md | Read RD33: ch. 29's examinations 9–11 on the scan, each strike and eye by its input with its reading and remedy. |
| <a id="rd34"></a>RD34 | The dead's sending of the remains and the funeral assembly — done 2026-10-23: sending the remains: an ordinary body after four days, the eminent after seven, by ten numbered roads (the sky's, the year's, month's, day's and hour's earth lords, the mewa, the trigram, the living's and the dead's door, the bearer; p. 96 on the scan), the year's 17 and the month's 18 earth lords named and the day's seats by its animal (as p. 224), the 26 places not allowed even if clean, each with its harm (p. 104); no Rāhu in the twelfth's own text; pp. 97–103 (the mewa's and trigram's roads, the doors, the hours, the bearer, where each corpse is set down) machine-read; the funeral feast (pp. 126–128, scan): whether to hold it, the beer by mansion, the day and seats, a peg on each mewa, the works by mewa; the notes' p. 521: the forty-five palace pebbles a scale of fathoms east of the patron's threshold; the «eighty» of p. 126 is a work, «ཁྲོམ་རྩིས་ལེའུ་བརྒྱད་ཅུ»; questions 288–294 | WB vol. 2, ch. 29, pp. 94–104 (img. 102–112), the twelfth examination: the day after four days, the sky's bird path, the earth's rat path, the year's, month's, day's and hour's earth lords, Rāhu, the places not allowed; pp. 126–128 (img. 134–136), the fourteenth: the feast's day, the forty-five pebbles of the palace; the notes, p. 521 (img. 529); on the scan, against year-month-earth-lords.md | [RD16](#rd16) | [31](#block-31) | corpus/wb/ch29-remains.md | Read RD34: ch. 29's twelfth and fourteenth examinations on the scan, the days and lords that decide the sending of the remains and the feast, each list entry by entry. |
| <a id="rd35"></a>RD35 | The dead's thirteenth examination: cutting off the run of deaths — done 2026-10-23: the thirteenth examination read through on the reprint against Yigdzin-1, its 19 passages machine state (no MITRA; O20): one rite per trigram of death (an ordinary remedy with another trigram's seal on a stone, a craftsman's slip, the grave's mouth broken by two animals, and a white, a speckled and a black part given together, not chosen by a verdict's colour), cutting the run of deaths for 9 (li) to 16 (zon) years; tables by the dead's year with the trigram, by mewa, by the thirty dates, by planet and Rāhu, by mansion, the five clans' strikes; the rules: kung kung graves on the 6th, 16th, 26th, the black dog's mouth and Brahmā's horn only when nothing else cuts, substitutes for barred graves, the nine-ring white protection, the rites sized by the household's means (p. 114); Mipham's thirteen remedy classes (p. 517); question 213 narrowed (no line ties a part to a colour), 221's «སྐབ» confirmed and «སྒབ» also printed; questions 295–300 | WB vol. 2, ch. 29, pp. 104–126 (img. 112–134): the rites against repeating deaths, by trigram (white, speckled, black, full or short), by mewa, by date, planet and mansion; the notes' section 4 where it classes them (p. 517, img. 525); on the scan, the mantras named, not copied beyond need | [RD16](#rd16); [RD23](#rd23) | [31](#block-31) | corpus/wb/ch29-rites.md | Read RD35: ch. 29's thirteenth examination on the scan, each rite by the pebble or input that calls for it, as a remedy row would name it. |
| <a id="rd36"></a>RD36 | The dead's fifteenth examination: the grave — done 2026-10-23: the fifteenth reckons nothing of its own: its 58 pebbles are rites chosen by an input, the dead's trigram picking one of eight grave-provision blocks of eleven entries each (pp. 129–132, on the reprint), the grave kind of the eighth examination one of five chants, a child's death the small grave, a fortunate old death the calling back of fortune; searching for the ground is a rite, the elements' gods each shielding a group of kin (p. 135); the colophon's sources (pp. 142–143); pp. 133–141 machine-read; questions 260–266 | WB vol. 2, ch. 29, pp. 128–143 (img. 136–151): the grave provisions, searching for the ground from Mañjuśrī's turtle, the five graves, the joined hands of the living and the dead, calling back the fortune; the colophon, p. 143; on the scan | [RD16](#rd16) | [31](#block-31) | corpus/wb/ch29-grave.md | Read RD36: ch. 29's fifteenth examination on the scan, what is reckoned and from what input, and the colophon's sources. |
| <a id="rd37"></a>RD37 | The thirty starting points on the scan — done 2026-10-23: the thirty starting points read on the scan whole, 33 passages: the size and the eight fighting pebbles (pp. 453–454), each point with its emblem (the ninth the parrot, not an ape), its kind of person and its eight checks read white and black with the rite for black, the general board and the colophon; the count by the year's sex (a male year down, a female up, as many places as the years), the role picking whose year (the notes, p. 532), the checks grouped upper 1, 3, 5, lower 2, 4, 6, middle 7, 8 (the notes); 36 claims, one table per point; Yigdzin-1 dropped a line of p. 456; question 259 narrowed (which pebble answers which check, by the notes' grouping, an inference), its element left open; questions 301–306 | WB vol. 1, ch. 26, pp. 453–473 (img. 463–483), the thirty points one by one with their eight checks, emblems, kinds and readings; the notes' section 7, vol. 2, pp. 532–535 (img. 540–543); on the scan; questions 215 and 259 | [RD17](#rd17); [RD23](#rd23) | [31](#block-31) | corpus/wb/ch26.md | Read RD37: ch. 26 on the scan, each of the thirty points with its role, its inputs, its eight checks and their readings, so that a build can reckon them from a birth year and age. |
| <a id="rd38"></a>RD38 | The thirteen circles and the small reckonings on the scan — done 2026-10-23: ch. 27 whole on the Gser thang reprint, 28 passages all on the scan, with MITRA over img. 12–33: the size and the colour rule (the son black, «བུ་དགྲ་ཤིང་ལྕགས་མེ་གསུམ་པོ། །ནང་ཕྲད་ནག་པོ», p. 3), each of the thirteen circles with its emblem, kind of person, starting animal, five pebbles and white and black readings with remedies, every small reckoning (the one gone abroad, the secret reckonings of heart, bones and flesh, the la's wandering, the chiefs', ministers', soldiers' and weapons', the banner, the wheel, the trade of gods and demons, the womb's end, the golden turtle's year classes), the meadow's ornament, Sprugtön's obstacle reckoning, the reckonings in four, the colophon; the notes' pp. 533, 535–538; questions 218 («རྐང་གྲངས»), 219 («རུས་མིག») and 220 («དར་གུད་སྐོར») answered on the reprint; question 259 answered for ch. 27, the element the power's (the notes, p. 533; p. 10); questions 363–369 | WB vol. 2, ch. 27, pp. 3–25 (img. 11–33): the thirteen circles' starting animals and five checks each, the small reckonings, the meadow's ornament and Sprugtön's sixteen limbs (pp. 22–23); the notes, pp. 535–538 (img. 543–546); on the scan; questions 216–220, 225 and 259 | [RD17](#rd17); [RD23](#rd23) | [31](#block-31) | corpus/wb/ch27.md | Read RD38: ch. 27 on the scan, each circle and small reckoning with its starting point, inputs, checks and readings. |
| <a id="rd39"></a>RD39 | The small signs on the scan — done 2026-10-23: the small signs, 22 passages (12 on the scan by eye on the Gser thang reprint, 10 machine): the year from three spring dates by where the moon falls on a hand of stars, 23 places (the notes, p. 537: the hand is the mansion lha mtshams, so calculable), the six full moons by the Pleiades, the seasonal marks counted from the winter solstice, the solstice's day, the sky-bird's thirty days (solar days, the notes), snow-rain and frost on set dates, the leaking-roof rite, the twelve black small feet (year, month, time, link, work, outcome), building's doors and black years, months and days, the 360 starting points (six aspects × sixty years, the notes); the signs of the body and the day's link machine-read, only the day of ignorance entry by entry; questions 321–332 | WB vol. 2, ch. 28, pp. 26–43 (img. 34–51): the signs of the sky, sneezes, twitches and sounds, the days to look, the links on the day's times (pp. 37–38) and the black small feet by year, month and hour; on the scan, the calculable parts first | [RD17](#rd17) | [31](#block-31) | corpus/wb/ch28.md | Read RD39: ch. 28 on the scan, its calculable parts entry by entry (the days and times, the black small feet), the observed signs outlined with their readings. |
| <a id="rd40"></a>RD40 | The moment's preliminaries, the asker's year and the small reckonings — done 2026-10-23: the moment's preliminaries (pp. 144–155, machine), its entry (p. 156) and its small reckonings (pp. 162–173) on the Gser thang reprint, with the notes' pp. 538–539: the asker is the one reckoned (p. 166), his four aspects fought against the moment's for house, field, grave, wealth, beer, the sick, trade and the casting's grain, the moment's year mewa against the day's set in the nine squares (p. 165), the thirteen gates' sky year the asker's own counted by his years, male down, female up (the notes, p. 536), the moment standing where ch. 27 puts the person's year (p. 172; the notes, p. 537); the thirteen gates, the ogre's and demon's counts and the sixty fixed signs, the eight places, wealth, the sick and the demon by element, the three mewa joined and the nine eyes, the mewa as spirits and as kin; the notes' thirty 'bri wang, gold and hand reckonings; question 101's «ལུ་བཞིན» confirmed on the scan; questions 341–353 | WB vol. 2, ch. 30, pp. 144–156 (img. 152–164), the preliminaries and the asker's year; pp. 163–173 (img. 171–181), the small examinations of the ground and of pebbles, the figure of a man lying down; the notes' section 8, pp. 538–541 (img. 546–549); on the scan | [RD17](#rd17); [RD23](#rd23) | [31](#block-31) | corpus/wb/ch30.md | Read RD40: the parts of ch. 30 not yet read on the scan, how the asker's year enters the moment and each small reckoning with its inputs and readings. |
| <a id="rd41"></a>RD41 | The illness reckoning on the scan — done 2026-10-23: the illness reckoning's pages on the Gser thang reprint (image = page − 5; it has pp. 428–429, which BDRC's 1996 scan lacks), 32 passages (16 by eye, 16 machine with every disagreement of the two prints settled) and the notes' pp. 495–499: the seven great reckonings and the four plank-trees read standing, broken, speckled, mostly black or white, with rites (pp. 422–425); the spirit by the count of black pebbles, 1 the land btsan to 15 Indra (p. 425, RD12's split settled); p. 426's table the power's element against the sky year's pair of animals (the notes, p. 496); the illnesses, spirits and ransoms by trigram, day animal and mewa of onset (pp. 427–435); the twelve stages, the divination, the trigram ransoms, the eight mothers (pp. 440–445); the last verdict on death, the five people, the nineteen pebbles for the sick and infants, the life force and body against the sky year, month, day and hour (pp. 445–447, by eye); question 195 read («བསྔོ་མ་བཏུབ»), 231 narrowed; questions 358–362 | WB vol. 1, ch. 25, pp. 422–437 (img. 432–447) and 440–450 (img. 450–460), the fifteen starting points, their pebbles, classes and labels (pp. 425, 435, 438); the notes' section 3, vol. 2, pp. 495–499 (img. 503–507); on the scan; questions 195, 226, 228–231 | [RD12](#rd12), [RD22](#rd22) | [32](#block-32) | corpus/wb/ch25.md | Read RD41: the illness reckoning's pages not yet read on the scan, each starting point with its inputs and readings, so that H2 can be built. |
| <a id="rd42"></a>RD42 | The marriage match's open points — done 2026-10-23: the match's open points on the scan (WB vol. 1, pp. 355, 358, 360, 362 on the Gser thang reprint and the Zhol and Taikhang blocks; the notes' pp. 478, 524–526 with Mipham's own print): question 233 answered, the blocks carve the turtle's left side 7 and left leg 6 (the 1996 setting 6 and 5), the body cut's pairs those of the notes' drawing and of the built wheel; question 235 answered, «དགྲ་དུར» two relations, enemy and grave (the notes' gloss, p. 478), so point 10 tests enemy, grave, seventh and four-executioner between the months, days and hours; question 210 mostly answered, the karma reckoning over the match is each spouse's own birth reckoning (the notes' section on ch. 22, pp. 474–489, «ལས་རྩིས»); question 211 in part, Mipham's print «ཤིང་དུར», WB p. 362 joining zin with sheep, dwa with ox, li with dog, kham with dragon; «སྐྱུག» or «སྒྱུག» open; p. 355 «བག་ཕོ»; questions 354–357 | WB vol. 1, ch. 23, p. 355 (img. 365), p. 358 (img. 368), p. 360 (img. 370); the notes, vol. 2, pp. 524–526 (img. 532–534): a match of mixed kinds, the karma reckoning over the match, «གནས་སྐྱུག», «ཤིད་དུར», the turtle's left side, «དགྲ་དུར» in point 10; on the scan and the Gser thang reprint; questions 209–211, 233, 235 | [RD15](#rd15), [RD21](#rd21) | [58](#block-58) | corpus/wb/ch23.md | Read RD42: the match's open points on the scan, for the karma reckoning over the match and point 10's relations. |
| <a id="rd43"></a>RD43 | The household round and the son meeting in the hundred — done 2026-10-23: the household round on the scan (WB vol. 1, pp. 385–387, 407–408 on the Gser thang reprint, image = page − 5; the notes' pp. 475, 490–491; DZ ff. 16a–16b, 22b–23a): the zigzag, all members' columns end to end taken alternately from the two ends, reproduces the notes' five and holds for any count (worked out, not stated); the fold and refold give four rows of ⌊5N/4⌋ with 5N's remainder by four left over, so not fewer than four members; each member brings its four deciding pebbles of the year of age and a pebble of its mewa's colour, the Moonbeams' plain meetings being the shortcut the notes reject; question 197 answered: p. 475's son black is the birth hundred's own rule, as the Moonbeams' «མ་གྲོགས་དཀར་ལ་དགྲ་བུ་ནག»; question 258 narrowed; questions 317–320 | WB vol. 1, ch. 24, pp. 385–387 (img. 395–397), 407–408 (img. 417–418); the notes, vol. 2, pp. 475 (img. 483), 489–491 (img. 497–499); DZ (BDRC I2533, open scans) on the household and the son meeting; on the scan; questions 197 and 258 | [RD20](#rd20), [RD26](#rd26) | [58](#block-58) | corpus/wb/ch24.md | Read RD43: how the household round runs for a household other than five and how a son meeting is laid in the birth's hundred, on the scan, against DZ. |
| <a id="rd44"></a>RD44 | The thirty and the circles in the manuals: whose year, element and count — done 2026-10-23: the Moonbeams' ch. 2 whole on the scan (DZ f. 13a–15b), LT pp. 8, 65–67 on the scan, UT pp. 4–5 from the etext, and a new source, Zhechen Gyaltsab's Garland of White Utpalas (ZG, pp. 303–304, on its open scan), the one worked circle BDRC's full-text search found: question 302 answered by the manual, the Moonbeams joining WB's reading n to WB's pair n (the Chinese pairs for 1–15, the Tibetan for 16–30, but 28 and 29 exchanged), its pairs the sixty years in order seen from a wood person as example; its eight pebbles counted from the pair's own year (4th, 7th, 11th up and down, the pair's two years for the luck), not WB's way; the circles worked by ZG for a wood-monkey king, from the iron bird (wood's enemy) upward by the years lived, man or woman alike, the reached year's aspects and mewa fought by the person's, the tenth circle naming the power's element; question 301 in part (UT counts years inclusively), 259 and 306 inferences, 368 narrowed (the body mewa, the starting year's mewa in the person's own sequence); a lead: the regent's replies 137–140 in Removing the Tarnish (RD47); questions 377–379 | DZ (*Moonbeams*, BDRC I2533, open scans) ch. 2, f. 13a–15b (img. 029–034), on the scan; LT (I1KG21886, etext IE0OPI9C5B1B35) and UT (etext IE0OPI6328F058) wherever they lay the thirty starting points or the thirteen circles, etext first, the scans for what is quoted; BDRC's full-text search (bdrc_search.py) for a worked thirty or circle; questions 259 (ch. 26's element), 301–303, 306, 363, 368 | [RD37](#rd37), [RD38](#rd38) | [31](#block-31) | corpus/dz/dz.md | Read RD44: how the manuals below WB lay the thirty and the circles (whose year and element, the count, which pebble answers which check, the circles' mewa), for the questions that stop their build; a manual is followed only where WB is silent. |
| <a id="rd45"></a>RD45 | The dead, the sick and the moment in the manuals: the inputs WB leaves open — done 2026-10-23: the Moonbeams' ch. 6–7 (DZ f. 24a–30a) on the scan, LT on the scan where quoted, UT from the etext: question 358 answered, the nineteen pebbles for the sick or the newborn over year, month, day and hour with date, weekday and mansion (DZ; LT names them with WB's heading and counts an infant's day by day to a month), «ཟ་མ» standing where the hour stands (an inference); question 267 answered, LT gives the twelve la years for all five clans, the great cut-off the element's grave year and the small its seventh, UT agreeing for three; a planet's state by its rising triad of animals (LT, UT), read so for the weekday in the dead's reckoning (UT), for question 282; questions 260, 272 and 283 narrowed (the living's twenty pebbles hold the descended and the birth mewa; UT matches the dead's life force with the descended trigram); question 270 by DZ's own board (rows by line, columns by age and sex), question 344 by analogy with the plank-tree's heads; questions 371–376 | DZ ch. 6–7, f. 24a–30a (img. 051–063), on the scan; LT and UT where they lay the reckoning for the dead, the illness reckoning and the moment, etext first, the scans for what is quoted; questions 260, 267, 269, 270, 272, 282, 283, 308, 311, 343, 344, 358–361 | [RD30](#rd30), [RD31](#rd31), [RD33](#rd33), [RD40](#rd40), [RD41](#rd41) | [31](#block-31), [32](#block-32) | corpus/dz/dz.md | Read RD45: which trigram, mewa and year the manuals take where WB's dead, illness and moment reckonings leave it open, and how they lay the la board and the planet's state, for the questions that stop their build. |
| <a id="rd46"></a>RD46 | WB's doubtful words on the Zhol and Taikhang blocks — done 2026-10-23: the doubtful words on the Lhasa blocks, the Zhol print and the Taikhang reproduction, by eye (21 passages, a page-to-folio concordance for vol. 2, pp. 3–175 and vol. 1, pp. 355, 386, 453–472): the blocks correct the 1996 setting at p. 98 «བཀྲ་རོ» (question 336), p. 128 «སྒོ་དབྱེ» (263) and p. 4 «ཀླུང་གསོ་འཛུགས» (369); they carve as printed the black small feet's «ཀ་ཤི», «དཔྱིད་ཐ», «རྡེལ་འགོ་དྲོས», «རེག» (328), the iron dog (357), «རྨང་པོར» (293), «བསྒྲུབ» (370) and the 360's fifth and sixth (331); they settle «ཕྱི་རྩིས» (331), «གཡང་རྗེན» (286), «ཇེ་ངན་ཇེ་ཆུང» (294), «སྒབ» (333), «ཕ་གུ» (338), «དྭའུ», «ཁྱོ», «ཡོ», «སྡེར» (274), the thirteenth gate's horse and bird as WB's own (343), dwa's food in seven syllables (262), «བནྡེ་སྔགས་ཕ» and «ལྔ་འདྲ» (305); 284, 274 in part; 356 and 317 not legible; questions 380–381 | WBZ (the Zhol print, I1KG1710, 3752 px) and the Taikhang reproduction (I5877–I5878, 8280 px), on the scan, for the words of WB vol. 2, pp. 26–173 and vol. 1, pp. 355, 386, 453–472 that the 1996 setting and the Gser thang reprint leave in doubt; questions 262, 263, 274, 284, 286, 293, 294, 296, 317, 328, 331, 333, 336, 338, 343, 356, 357, 369, 370 | [RD30](#rd30), [RD31](#rd31), [RD39](#rd39), [RD40](#rd40), [RD42](#rd42) | [31](#block-31), [58](#block-58) | corpus/wbz/wbz.md | Read RD46: each doubtful word of the questions listed on the Lhasa blocks, the folio and line found for each page of the 1996 edition, and what the blocks carve. |
| <a id="rd47"></a>RD47 | The regent's replies on the thirty and the circles — done 2026-10-23: the regent's questions and replies 137–142 (YS, BDRC MW8226, I5875 img. 100–101, 494–499) on the scan with MITRA as witness: the thirty and the circles are two reckonings, the common identification by counting from the iron bird rejected (137); the subjects' point by the lord's year, or the asker's own for his own wish, the horses' by whoever asks, owner, groom or buyer, "in the power of whoever asks" (137–138, question 303 in part); the horse reading names the board's forefathers, last part of life and middle demons, and all speckled is good, the board read by its base (138, question 306 supported); the circles counted upward by the years lived for man and woman alike, male down and female up rejected (139); the servant's fine start hidden in an older text as four nails of fixed years (140); the small reckonings and Sprugtön's kept apart from the essentials (141–142); YS's work id, scans and etext confirmed; questions 382–384 | YS (*Removing the Tarnish*, BDRC I5875, open scans), replies 137–140 on the thirty starting points, the circles' count direction, the subjects' year and the servant's start (near etext pp. 100–101 and 494–498 of RD44's search; the images found from reply 113 at img. 469), on the scan; any other reply on ch. 26–27 found in its outline or etext; questions 259, 301, 303, 306, 363, 368, 377 | [RD44](#rd44) | [31](#block-31) | corpus/ys/thirty-circles.md | Read RD47: what the author of WB answers about the thirty and the circles, whose element and year, the count, the order of the checks, the role's year, on the scan. |
| <a id="rd48"></a>RD48 | The regent's replies on the dead, the sick, the moment and the match — done 2026-10-23: the regent's questions and replies 101–136 and 143–160 (YS vol. 1, I5875 img. 90–108, 450–546; vol. 2, I5876 img. 5–22) by Yigdzin-1 in outline, five read on the scan: reply 111, a match barred when both lives are utterly bad, each life gathered into one verdict pebble (question 355 by analogy); reply 133, the illness's five pebbles the trigram elements of the year, month, day and hour of falling ill and their commonest (question 226 narrowed); reply 148, the dead's outer reason laid as rough pebbles, enemy rising, inner and son meetings black, mother and friend white; reply 152, li or kham as the trigram fallen to, the descended trigram (questions 260 and 283 lean so); reply 160, the thirteen gates end on the horse (question 343), the sixty signs the gates' small reckonings (344 narrowed); no reply on the la board's places, the planet's state, the nine-eye's age, p. 426's sky year, mixed kinds or the clans' cut-off; YS's volumes registered; reply 157 on the moment's trigram by year left for the scan (RD49); questions 385–389 | YS (BDRC I5875), every reply on WB's ch. 22–25 and 28–30 and on the notes' matters (the dead's trigram and mewa, the la board, the planet's state, the illness's sky year, the moment's asker and sixty signs, the match's mixed kinds and karma reckoning), found by its outline and etext, read on the scan; questions 209, 260, 270, 272, 282, 283, 308, 343, 344, 354, 355, 361, 375 | [RD45](#rd45) | [31](#block-31), [32](#block-32), [58](#block-58) | corpus/ys/other-reckonings.md | Read RD48: what the author of WB answers about the dead, the sick, the moment and the match where his text leaves the input open, on the scan. |
| <a id="rd49"></a>RD49 | The regent's reply 157: the moment's trigram by year — done 2026-10-25: reply 157 read on the scan (YS vol. 2, img. 16–17): WB's start of the moment's trigram is the Phugpa practice, li for the tiger, horse and dog triad, as the notes (p. 535) have it, so R2o's start is confirmed and WB's source named (Duhar Nagpo's *Clear Lamp* and the *Speckled Sheet*); nothing on the asker's year; the *Vaiḍūrya* line puts the triad on the year or the month, which is question 390, and R2o stands as built | YS vol. 2 (BDRC I5876), question and reply 157, img. 16–17, on the scan; set beside WB vol. 2, pp. 161–162 and the notes, p. 535 (the moment's trigram and mewa, built as R2o) | [RD48](#rd48) | [31](#block-31) | corpus/ys/other-reckonings.md | Read RD49: what the regent's reply 157 says of the moment's trigram by the year, on the scan, and whether it bears on the built R2o or on the asker's year. |
| <a id="rd50"></a>RD50 | The thirty's count, 241st pebble and check order, on the scan — done 2026-10-25: q301 (the nominal age through the sixty, an inference; UT p. 4), q304 (241 reads so in three prints, the *Moonbeams* has 240, no 241st named), q306 (the pebbles are named up and down in the check order, contents do not all fit), q259 (the year's own element, from wording) narrowed, q377 confirmed as one text's exchange, in five claims in claims/thirty-*.md | WB vol. 1, ch. 26, pp. 453–454 (img. 463–464 of the 1996 scan; Gser thang reprint I3CN5029, image = page − 5) and the notes, vol. 2, pp. 532–533; the *Moonbeams*' ch. 2 (DZ img. 032–033) for the exchange of readings 28 and 29; UT pp. 4–5 for the count | RD49 | [31](#block-31) | corpus/wb/ch26.md | Read RD50: on the scan, how the thirty's count of years runs (inclusive or full years, through the sixty or the twelve; q301), what the 241st pebble is (q304), whether the check order is the order of the eight fighting pebbles (q306), whose element starts the thirty (q259) and whether readings 28 and 29 are exchanged in the *Moonbeams* (q377). |
| <a id="rd51"></a>RD51 | The thirteen circles' small reckonings: misprints and unclear words, on the scan — done 2026-10-25: ch. 27's small reckonings read on the scan: the la's wandering as printed in three prints (q363), the turtle's wood sheep «ཤིང་ལུག་པ» and the count of sixty (q364), the «བརྗེ» set as the monk's (q365), «འུར་ཞོགས» (q366) and the thirty's doubtful words in context (q305); three claims | WB vol. 2, ch. 27, pp. 9, 16, 17, 20, 23–25 (Gser thang reprint I3CN5029 img. 22 for p. 16, image = page + 6) and the notes, pp. 533, 535–538; the doubtful words of the thirty (vol. 1, pp. 455–467, q305) | —; RD50 beside | [31](#block-31) | corpus/wb/ch27.md | Read RD51: on the scan and the reprint, the la's wandering of the snake and horse, the rat and pig (q363), the golden turtle's wood sheep and how a year becomes an element (q364), for whom the four reckonings of «བརྗེ» are (q365), the doubtful words of p. 9, p. 17 (q366) and the thirty's doubtful words and rites (q305). |
| <a id="rd52"></a>RD52 | The illness reckoning's people and animals; the Vaiḍūrya line of the moment's trigram — done 2026-10-25: the illness reckoning's day animals are those of the day of falling ill (q360, inferred, four texts agree), the five people by the life force's element (reply 135, q359 narrowed), the moment's trigram by the month and turning date by date (q390 narrowed, the Vaiḍūrya's «ལོ་ལ» a doubt); KD vol. 3 pp. 693, 701 added from the etext; three claims | WB vol. 1, ch. 25, pp. 428–429, 445 (Gser thang reprint I3CN5029, image = page − 5) and the notes, pp. 495–499; for q390 the *Speckled Elements*' *Vaiḍūrya* volume as quoted in YS vol. 2 img. 17 and any print of it in the pool or on BDRC | RD49 | [31](#block-31), [32](#block-32) | corpus/wb/ch25.md, corpus/ys/other-reckonings.md | Read RD52: whether the day's animal or the birth's heads pp. 428–429 (q360), by which relation the pillow-guard is chosen and what «མཐུན་གསུམ་རང་དང་ལྔ» counts (q359), and whether the *Vaiḍūrya*'s tiger, horse and dog triad of the moment's trigram is of the year or the month and what the trigram does on later dates (q390); where no print can be found, write the search down as a dead end. |
| <a id="rd53"></a>RD53 | The illness identified by its most numerous element: the fifth pebble, the seven elements, the thirds, the nine classes and p. 425's labels — done 2026-10-25: the nine classes named by the difference of white and black among the fifteen (q229 narrowed), p. 425's labels graded by a colour of a colour and «གདགས་སྲིབས་གོ་ལོག» glossed (q230), «ཉ་བཞི» read «ཉ» on the scan (q231), «བླངས་ལན་པའི» as printed (q232); q226–q228 left to the work/q-illness reading; three claims | WB vol. 1, ch. 25, pp. 425, 435–438 (Gser thang reprint I3CN5029, image = page − 5), the notes, vol. 2, pp. 496–497; the regent's reply 133 (YS vol. 1, I5875 img. 491–494) | RD52 | [32](#block-32) | corpus/wb/ch25.md | Read RD53: on the scan, the fifth pebble's trigram and the tiger, horse and dog counted on li (q226), the notes' seven elements (q227), a third and two thirds (q228), the nine classes as counts of the fifteen (q229), p. 425's labels (q230), «རྙོངས» and «ཉ་བཞི» (q231) and «བླངས་ལན་པའི» (q232). |
| <a id="rd54"></a>RD54 | The circles' and the thirty's verdicts: the meadow's ornament, Sprugtön's limbs, the five totals, «རྡེལ་ཁྲ» — done 2026-10-25: the circles' verdicts one under each column in Mipham and KD's sheet (q216), Sprugtön's sixteen limbs beside the five verdicts (q217), the Moonbeams' five totals beside WB's domains (q225), «རུས་མིག» (q219), «རྡེལ་ཁྲ» as the mixed board (q215), and KD p. 726's household round of seven read as quarter-runs of eight with no speckled pebble (q398); six claims | WB vol. 2, ch. 27, pp. 22–23 (Gser thang reprint I3CN5029 img. 28–29), vol. 1 p. 468 (I3CN5028 img. 463), the notes, vol. 2, pp. 533, 535–538; DZ f. 24a; KD vol. 3 pp. 716–723 (lent scans) | RD51 | [31](#block-31) | corpus/wb/ch27.md | Read RD54: on the scan, the meadow's ornament's five verdicts beside KD's worked sheet (q216), whether Sprugtön's sixteen limbs count into the five verdicts (q217), whether the *Moonbeams*' five general totals are WB's five verdicts by domain (q225), «རུས་མིག» or «དུས་མིག» on the other prints (q219) and «རྡེལ་ཁྲ» in the twenty-fifth starting point (q215). |
| <a id="rd55"></a>RD55 | «བསྔོ་རུ་མ་བཏུབ» and «སྔོ་བགྲང»: the plank-tree's offering line — done 2026-10-25: «བསྔོ་རུ་མ་བཏུབ» spelled alike on every print and glossed by no text (q195, "cannot be assigned" an inference), «སྔོ་བགྲང» glossed by the notes' next sentence as a speckled pebble counted nowhere (q196); two claims | WB vol. 1, pp. 419, 423–425 and the notes, vol. 2, pp. 495–496 (Gser thang reprint I3CN5029, image = page − 5, and I5877/WBZ for the other blocks), the regent's replies on the plank-tree (YS I5875) | RD52 | [32](#block-32) | corpus/wb/ch25.md | Read RD55: what «བསྔོ་རུ་མ་བཏུབ» (q195) and «སྔོ་བགྲང» (q196) say, on every print and in the manuals that gloss the plank-tree; where no gloss is found, write the search down as a dead end. |
| <a id="rd56"></a>RD56 | The pages of WB ch. 31 and ch. 34 no reading has opened — done 2026-10-25: the 22 pages no reading had opened in ch. 31 (pp. 175–176, 224–225, 236–237, 240: the fixed earth lords, the day animal's lord, the earth kings' times, Rāhu's course, the closing note) and ch. 34 (pp. 378–384, 387, 407–411, 415–416: the list of works, birth by the hour and mansion, the digging rite, the marriage wheels, examining the sick) written out in machine state (Yigdzin-1 and MITRA, set right by eye where they disagreed; not scan-checked syllable by syllable); no contradiction with earth-lords.md, year-month-earth-lords.md or white-beryl-ch34.md; 22 claims | WB vol. 2, ch. 31 pp. 175–176, 224–225, 236–237, 240 and ch. 34 pp. 378–384, 387, 407–411, 415–416 (1996 scan img. = page + 8; Gser thang reprint I3CN5029, image = page + 6); `kb.py coverage wb` lists them | — | [25](#block-25) | corpus/wb/ch31-gaps.md (new) | Read RD56: on the scan, each of these pages, passage by passage with its Tibetan and English; say what each adds to the earth lords of ch. 31 and the works of ch. 34, and which of the topic files (year-month-earth-lords.md, white-beryl-ch34.md) it corrects. |
| <a id="rd57"></a>RD57 | Ch. 27's residual doubts on the Taikhang block and in single crops — done 2026-10-25: the Taikhang block (I5878) and the Zhol print read for ch. 27's doubts: the la's wandering carved as the other prints (snake, horse north, pig south), the turtle's wood verse «ལྷག་མ་བཅུ་པོ» ten with པ, p. 9 l. 6 «མག་རྒུད» on the Taikhang, p. 17 l. 9 «རྔམས» likely, p. 17 l. 26 a box-shaped བ or འ never ཞ (agy blocked); the Taikhang and Zhol differ in the machine's reading on three lines | WB vol. 2, ch. 27 pp. 9, 16, 17, 20 (the la's wandering, the golden turtle's verse, the doubtful letters) on the Taikhang reproduction (I5877, the block's own page numbers) and the Zhol print (WBZ I1KG1710), with agy for single crops; the 1996 scan img. = page + 8 | RD51, RD54 | [31](#block-31) | corpus/wb/ch27.md | Read RD57: on the Taikhang block and the Zhol scan, whether the la's wandering of the snake, horse, rat and pig is carved as the reprint has it (q363), whether the turtle's wood verse reads «བཅུ་པོ» or nine (q364), and the doubtful letters of p. 17 ll. 9 and 26 and p. 9 (q366); where a block is not served, write the search down as a dead end. |
| <a id="rd58"></a>RD58 | The summing rules left for the moment, the dead and the illness — done 2026-10-27: the notes' «ཡར་གསུམ་སོགས» are p. 172's own three-up, three-down, eight-up and eight-down counts, whose landed years the four aspects fight (the larger colour decides; whose aspects fight stays open, q); a place's verdict is laid where the cutting and the rise and decline agree (p. 168 on wealth), no white-black count per place; p. 503's twenty roots counted 3+4+2+5+2+1+3 with «བླ་བར»/«ཟླ་བར» unsettled on the print; the illness's nine classes named by the difference of white and black among the fifteen | WB vol. 2, ch. 30, p. 172 (img. 180), the fighting enemies, with the notes, p. 537 (img. 545, «ཡར་གསུམ་སོགས་ལ་སྲོག་ལུས་དབང་ཀླུང་བཞིས་འཐབ། དུས་ཀྱིས་བརྩི་བྱའི་ལོ་ཚབ་བྱའོ»: "the upper three" of ch. 26's board, vol. 1, pp. 471–472, or "three up" as claims/moment-asker-year.md has it), and ch. 27's like reckoning it names; pp. 166 and 168, how a place's cutting and its rise and decline make its verdict (question 423); the notes, p. 503 (img. 511), the time of death's twenty roots, «བླ་བར» or «ཟླ་བར» in the fifth group; WB vol. 1, p. 425 and the notes, pp. 495–496, the illness's nine classes (question 229) | [RD23](#rd23), [RD40](#rd40) | [60](#block-60) | verdicts.md | Read RD58: on the scan, which years the moment's fighting enemies count, which of their aspects fight which of the asker's and how the count is laid; how p. 166's three make a place's verdict; which of the dead's twenty roots of p. 503 the dead's view already reckons; and the illness's nine classes from fifteen pebbles. |
| <a id="rd59"></a>RD59 | The moment's preliminaries on the scan: the dreams, the asker's arrival, gestures and words, the omens of the road — done 2026-10-27: pp. 144–146 read by eye on the Gser thang reprint, 13 readings corrected (the verb «སྨྲ» throughout p. 145, «རྨི་ལྟས» at the head of the dreams, «རང་ལ་སླང»), five stacks unsettled and ten words not understood, all in the passages' notes | WB vol. 2, ch. 30, pp. 144–146 (Gser thang reprint I3CN5029, image = page + 6; the 1996 scan img. = page + 8), today machine-read in corpus/wb/ch30.md (wb.2:p144, wb.2:p144-146) | — | [31](#block-31) | corpus/wb/ch30.md | Read RD59: on the scan, line by line, the dreams before the reckoning, the asker's arrival, what he carries, his gestures and words, and the omens of the road (pp. 144–146); bring the passages to the scan state, every doubtful word read by eye or marked unsettled. |
| <a id="rd60"></a>RD60 | The moment's preliminaries on the scan: the place and time, the oath, the seat, the implements and their signs — done 2026-10-27: pp. 146–150 read by eye on the reprint, 11 corrections (9 clear: «རུལ་པ», «ཡོ་མེད», «ཕག་ཤ», «དྭངས», «དར་སྐྱུད» among them; p. 146's «ཀ་ཤི» a misprint for «རྒ་ཤི»), the old English's rat day and the flour's hole not on the scan | WB vol. 2, ch. 30, pp. 146–150 (Gser thang reprint I3CN5029, image = page + 6), today machine-read in corpus/wb/ch30.md (wb.2:p146-150) | — | [31](#block-31) | corpus/wb/ch30.md | Read RD60: on the scan, line by line, the place and time of reckoning, the conduct and oath, the seat, and the signs of the one swearing, the cloth, the grain, the meat, the arrow, the mirror, the beer, the butter, the flour and the smoke (pp. 146–150); bring the passage to the scan state. |
| <a id="rd61"></a>RD61 | The moment's voices and callers, and the asker's posture — done 2026-10-27: pp. 150–151 read by eye, about twenty corrections; p. 170's posture words read as printed («འདྲུད», «འཇག», «ཁོང་འབྲས», «གྱེར་པོ»; the senses of «འཇག» and «གྱེར་པོ» open), the hair's earth word «བནྡེ» («བཛེ» withdrawn), the marks line naming neither wood nor fire (q346 open) | WB vol. 2, ch. 30, pp. 150–151 (Gser thang reprint I3CN5029, image = page + 6) and p. 170, the posture lines («སྤྱོད་ལམ» to «འབྲས་བུ་འཆད»), whose «འདྲུད», «འཇག», «ཁོང་འབྲས», «གྱེར་པོ» the corpus leaves doubtful | — | [31](#block-31) | corpus/wb/ch30.md | Read RD61: on the scan, the voices heard and the callers who come during the reckoning (pp. 150–151; the invocation that follows only as far as it names signs), and the posture lines of p. 170 with their doubtful words; bring both to the scan state. |
| <a id="rd62"></a>RD62 | The thirteen circles and the small reckonings on the scan: the la's wandering, the golden turtle, the monk's set and the unread words — done 2026-10-28: ch. 27's doubtful letters re-read by eye at 5–6× on the reprint (pp. 9, 16, 17, 20, 23), none settled by the eye: «འུར་ཞོགས» confirmed against ཞུར, «ལྷོར» of the rat verse read by sense, the wood verse's printed ten against our count of nine, the monk's set by place only; no new question | WB vol. 2, ch. 27, pp. 3–25 (Gser thang reprint I3CN5029, image = page + 6; the 1996 scan img. = page + 8), machine-read and read by eye in RD38 and RD51 (questions 216, 217, 225, 259, 363–366) | — | [31](#block-31) | corpus/wb/ch27.md | Read RD62: on the scan, again and by the questions open on ch. 27 (216, 217, 225, 259, 363, 364, 365, 366): the directions of the la's wandering, the golden turtle's wood sheep, the set of «བརྗེ», the words of pp. 9 and 17; settle what the scan, the other prints and the sources allow, mark the rest unsettled. |
| <a id="rd63"></a>RD63 | The earth lords' comings, the dead's rites and the black times on the scan (ch. 31) — done 2026-10-28: ch. 31 on the reprint: q402 answered («བཞི་བརྒྱ་དོན་བཞི» is 474, the sum of the notes), the hal khyi's five pairs hold 22 days each (q36 in part), the sun's «ཀླུང» not «གླང» on the scan (q35's ox reading falls), q29 and q28 narrowed, q404 on p. 224 (the lines certain, two words unsettled), q405 unsettled | WB vol. 2, ch. 31, pp. 174–240 (Gser thang reprint I3CN5029, image = page + 6), questions 28, 29, 35, 36, 402, 404, 405 | — | [25](#block-25) | corpus/wb/ch31-gaps.md | Read RD63: on the scan the passages of ch. 31 that the open questions 28, 29, 35, 36, 402 (p. 175–177, the count of the comings), 404 (p. 225) and 405 (p. 236) stand on; settle what the scan and the published sources allow, mark the rest unsettled. |
| <a id="rd64"></a>RD64 | The list of works, the measure of the earth figure and the closing verses of ch. 34 on the scan — done 2026-10-28: ch. 34 on the reprint and two published witnesses: q403 answered (the digging figure ten mtho long, «གླུ་བརྒྱད» the eight nāgas, «ཁྲུ་གང» a cubit's depth), q411 answered (yes, «གདོང་ཐུག» the lord's seat), q119 narrowed, q406 partly (gifts, dike, nails; «སྦ་ཕྱར» open), q408 unsettled; two new questions | WB vol. 2, ch. 34, pp. 378–428 (Gser thang reprint I3CN5029, image = page + 6), questions 119, 403, 406, 408, 411 | — | [25](#block-25) | corpus/wb/ch34.md | Read RD64: on the scan the passages of ch. 34 that questions 119, 403 (p. 387), 406 (pp. 378–384, the list of works), 408 (p. 428) and 411 (p. 427) stand on; settle what the scan and the published sources allow, mark the rest unsettled. |
| <a id="rd65"></a>RD65 | Ch. 31's four open passages on other witnesses: the black hal khyi's mothers and sons, the sun's klung, the day's twelve for the dead and the black times' door figure — done 2026-10-28: ch. 31's four open passages on the Zhol and Taikhang prints, the Bhutan collection and BDRC's search: q405 answered («སྤྲ་ཁྱིམ» is the herdsman's tent), q404 narrowed (the threshold is the house's; «སྦྲང་མ་ཕུད» and «ཡོ་ནས» unsettled), q432 narrowed (the sun's klung probably the day's klung-horse, an inference), q433 unsettled on every print; three new questions | WB vol. 2, ch. 31, pp. 185–186, 224–225, 236–237 (Gser thang reprint I3CN5029, image = page + 6), questions 404, 405, 432, 433; the other prints (I5877/WBZ), the notes (vol. 2, pp. 474–545) and published manuals that gloss them | — | [25](#block-25) | corpus/wb/ch31-gaps.md | Read RD65: for questions 404 (p. 225), 405 (p. 236), 432 (p. 186) and 433 (p. 185), the same lines on every print and in the notes and the published manuals; settle what they allow, mark the rest unsettled, write each fruitless search down as a dead end. |
| <a id="rd66"></a>RD66 | Ch. 34's list of works, the mtho and the closing verse on other witnesses — done 2026-10-28: ch. 34's list of works and the digging figure on the prints, the notes and the manuals: q430 answered (work 24 is gifts, not a dowry), q431 answered (one mtho is a tenth of the plot, from ch. 32 p. 281), q406 answered for works 24, 49 and 50 and work 17 most likely «སྦྲ་ཕྱར», q408 not ranked by any text read; two new questions | WB vol. 2, ch. 34, pp. 378–384, 387, 428 (Gser thang reprint I3CN5029, image = page + 6), questions 406, 408, 430, 431; the other prints, the notes and published manuals on the list of works and the digging figure | — | [25](#block-25) | corpus/wb/ch34.md | Read RD66: for questions 406 («སྦ་ཕྱར», «ཤྭ་རགས», «སེན»), 430 («སྐྱས» and the dowry), 431 (the length of one mtho) and 408 («ཁྱད་པར་དུ»), the lines on every print, in the notes and in the published manuals; settle what they allow, mark the rest unsettled, write each fruitless search down as a dead end. |
| <a id="rd67"></a>RD67 | Ch. 27's unread words on other witnesses: the la's wandering, the golden turtle, the monk's set — done 2026-10-28: ch. 27's unread words searched on the Sakya, Zhol and Taikhang-copy etexts, BDRC's phrase search and Zhechen Gyaltsab's volume: q363, q364, q366 unchanged on six prints, q365's «བརྗེའི» is also ch. 30's word (p. 155) and still unglossed, q216 and q225 gain ZG's five general totals (zg:p300-301); no question settled; one new question | WB vol. 2, ch. 27, pp. 3–25 (Gser thang reprint I3CN5029, image = page + 6), questions 216, 217, 225, 259, 363, 364, 365, 366, left open by the eye in RD62; the other prints, the notes and published manuals on the thirteen circles | RD62 | [31](#block-31) | corpus/wb/ch27.md | Read RD67: for the questions open on ch. 27 (216, 217, 225, 259, 363–366), the words on every print other than the reprint, in the notes and in the published manuals; settle what they allow, mark the rest unsettled, write each fruitless search down as a dead end. |
| <a id="rd68"></a>RD68 | Ch. 34's hair date, the work 17 stack and the digging figure's finger-breadths — done 2026-10-28: ch. 34 on the Taikhang print and Steinert's dictionaries: work 17 is the sewing of tents and covers («སྦྲ» a yak-hair tent, «ཕྱར་བ» a covering sheet, «ཕྱ་ཐེར» unglossed, q437 narrowed), one mtho is a span of twelve finger-breadths in the dictionaries so p. 387's six are half (q438 open against ch. 32's tenth of the plot), the 9th hair date's result judged by no source (q436 unchanged); no new question | WB vol. 2, ch. 34, pp. 378, 387 (Gser thang reprint I3CN5029, image = page + 6), questions 119, 436, 437, 438; the other prints, the notes and published manuals | — | [25](#block-25) | corpus/wb/ch34.md | Read RD68: for questions 436 (p. 378, «ན་ཆུང་མོད་འགྱུར»), 437 (p. 378, «སྦྲ་ཕྱར», «ཕྱ་ཐེར»), 438 (p. 387, the finger-breadths of one mtho) and 119, the lines on the scan, on every other print, in the notes and in the published manuals; settle what they allow, mark the rest unsettled, write each fruitless search down as a dead end. |
| <a id="rd69"></a>RD69 | Ch. 31's Langging list and the hal khyi counts that stay open — done 2026-10-28: ch. 31's open passages on the scan and the etexts: q434 answered (Langging's dragon year pairs with the dog «ཁྱི»), q28 narrowed and RD63's reading corrected (the word after «དུར་གྱི» is «སྟེང», on top of, not «རྟིང»), q35 and q36 (q432, q433) unchanged with one digest echo recorded, q29 unchanged; two new questions | WB vol. 2, ch. 31, pp. 174–240 (Gser thang reprint I3CN5029, image = page + 6), questions 28, 29, 35, 36, 434; the notes (vol. 2, pp. 474–545), the other prints and published manuals | — | [25](#block-25) | corpus/wb/ch31-gaps.md | Read RD69: for questions 434 (p. 186, the dragon year on «ཁྱི» or «བྱི»), 28, 29, 35 and 36, the lines on the scan at high magnification, on every other print and in the notes; settle what they allow, mark the rest unsettled, write each fruitless search down as a dead end. |
| <a id="rd70"></a>RD70 | Ch. 27's general totals in Zhechen Gyaltsab and the open stacks — done 2026-10-28: ZG's sums on the scan and in the Moonbeams: q435 answered («ཉེར་ཐམ» is twenty exactly, each year 120, the three 360), q216 and q225 narrowed (the sixty verdicts gather evenly into five totals, twelve each, if each group's five belong one to each aspect; the Moonbeams add 120 pebbles each for days and hours, 600 in all); q217, q259 untouched | WB vol. 2, ch. 27, pp. 3–25 (Gser thang reprint I3CN5029, image = page + 6), questions 216, 217, 225, 259, 435; Zhechen Gyaltsab zg:p300-301 (scan img. 344-345) | RD67 | [31](#block-31) | corpus/wb/ch27.md | Read RD70: for q435 («ཉེར་ཐམ» in ZG's sums, zg:p300-301) and the totals it bears on in q216 and q225, on the ZG scan and in the published manuals; settle what they allow, mark the rest unsettled, write each fruitless search down as a dead end. |
| <a id="rd71"></a>RD71 | Ch. 27's black bone and the circles' mewa pebble on other witnesses — done 2026-10-28: ch. 27 on the notes, ZG and the manuals: q367 answered («རུས་ནག» is the north's black turtle, the tenth circle's nails the four-direction set), q368 not settled (ZG's table sets the years in triads each with its own mewa sequence, an inference), q259 narrowed a little for ch. 26 by two manuals' «wood rat from the iron tiger», q217 nothing; no new question | WB vol. 2, ch. 27, pp. 3–25 (Gser thang reprint I3CN5029, image = page + 6), questions 217, 259, 367, 368; the notes (vol. 2, p. 533), the other prints and published manuals | RD70 | [31](#block-31) | corpus/wb/ch27.md | Read RD71: for questions 367 («རུས་ནག», p. 10), 368 (the two mewas of the mewa pebble, notes p. 533), 217 and 259, the lines in the notes and in the published manuals that gloss them; settle what they allow, mark the rest unsettled, write each fruitless search down as a dead end. |
| <a id="rd72"></a>RD72 | Ch. 31's Wangkhyi day-time, the «དུར» of the light-clearer and the sun's klung in the commentaries — done 2026-10-28: ch. 31's day-time and «དུར» on the scan, the notes and Steinert's dictionaries: q442 answered («ཉི་དུས» is the short form of «ཉི་མ་དུས་ཚོད», the day and the hour; q29 narrowed with it), «དུར» one of the four slayer animals (ox, sheep, dog, dragon; q443 and q28 narrowed, two candidate counts), q35 narrowed (the sun's klung probably an animal, the klung-horse year), q36 not advanced (the year charts hold names only), q439 untouched; two new questions | WB vol. 2, ch. 31, pp. 185–186, 210, 219 (Gser thang reprint I3CN5029, image = page + 6), questions 28, 29, 35, 36, 439, 442, 443; the notes (vol. 2, pp. 474–545), the commentary etexts and published manuals | RD69 | [25](#block-25) | corpus/wb/ch31-gaps.md | Read RD72: for questions 442 («ཉི་དུས», p. 210), 443 (the «དུར» of p. 219), 439, 28, 29, 35 and 36, the glosses in the commentary etexts, the dictionaries (Steinert's clone) and the other books of the year charts; settle what they allow, mark the rest unsettled, write each fruitless search down as a dead end. |
| <a id="rd73"></a>RD73 | Ch. 34's mtho against ch. 32 p. 281 and the works of 24, 49, 50 on the scan — done 2026-10-28: ch. 32 p. 281 and ch. 34 p. 387 agree on four mtho to the navel at twelve finger-breadths a mtho (q438 narrowed: whether «རང་མཐོ» is the digger's span stays open), work 24 reads «སྐྱས» on the reprint, work 49 has «ཤྭ» with subjoined wa, work 50's head letter a blot, q408 narrowed («ཁྱད་པར་དུ» singles out the particular case and carries no rank); no new question | WB vol. 2, ch. 32 p. 281 and ch. 34, pp. 378–384, 387, 428 (Gser thang reprint I3CN5029, image = page + 6), questions 408, 436, 437, 438 | RD68 | [25](#block-25) | corpus/wb/ch34.md | Read RD73: for q438 the count of ch. 32 p. 281 and ch. 34's example side by side on the scan with the mtho a span of twelve finger-breadths (claims/wb-ch34-mtho-is-a-span.md); the lines of works 24, 49 and 50 on the reprint at high magnification; q408 «ཁྱད་པར་དུ»; settle what they allow, mark the rest unsettled, write each fruitless search down as a dead end. |
| <a id="rd74"></a>RD74 | Ch. 31's «ཡོ་ནས» of the rat day and the herdsman's tent in the dictionaries and the Bhutan tables — done 2026-10-28: the rat day's remedy on WB's own table (vol. 1 p. 133) and in the dictionaries: «ཁ་ཡོ་ནས་འདྲེན» is WB's own wording, the verb «འཚག» is «to strain» (the corpse reading withdrawn: the beer of the dead is not strained at the threshold), «ཐེམ་བུ» the house threshold, «ཡོ» «crooked» in the dictionaries, «from the jar's mouth tilted» a guess (q439 narrowed, q404 narrowed); q432 and q433 not advanced; two new questions | WB vol. 2, ch. 31, pp. 224–225 (Gser thang reprint I3CN5029, image = page + 6), questions 404, 439, 432, 433; the Bhutan table of the twelve days (MW26041, printed p. 561), Steinert's dictionaries and the commentary etexts | RD65 | [25](#block-25) | corpus/wb/ch31-gaps.md | Read RD74: for q439 («ཡོ» with or without «ཁ», whose «འདྲེན») and q404, the Bhutan table of the twelve days on the scan, the dictionaries and the etexts; for q432 and q433 the dictionaries; settle what they allow, mark the rest unsettled, write each fruitless search down as a dead end. |
| <a id="rd75"></a>RD75 | Ch. 27's unread words in the dictionaries: «བརྗེ», «མག་སྐུད», «ཚད་ཟླ», «ན་ཟླ་སྟོན་མོ» — done 2026-10-28: ch. 27's unread words in Steinert's dictionaries and ZG: q365 narrowed (ZG names the «བརྗེ» set the spiritual friend's, not the monk's; what «བརྗེ» spells stays open), q366 three words of four read («ན་ཟླ་སྟོན་མོ» the age-mates' feast, «ཚད» a misprint for «ཚང» by ZG, «འུར་ཞོགས» an alarm; p. 17 ll. 9 and 26 still open), q364 པ confirmed as the person-suffix; q363 and q368 nothing; no new question | WB vol. 2, ch. 27, pp. 9, 17, 23 (Gser thang reprint I3CN5029, image = page + 6), questions 363, 364, 365, 366, 368; Steinert's 64 dictionaries (clone, as in RD68) and the typeset notes | RD71 | [31](#block-31) | corpus/wb/ch27.md | Read RD75: for the words of q365 («བརྗེ»), q366 and q364 («ཤིང་ལུག་པ»), the Steinert dictionaries searched as RD68 did (locate.py dict), and the notes on the circles; settle what they allow, mark the rest unsettled, write each fruitless search down as a dead end. |
| <a id="rd76"></a>RD76 | Ch. 31's Wangkhyi day and hour, the light-clearer's «དུར» and the mothers and sons on the other prints and the almanac cells — done 2026-10-28: ch. 31's Wangkhyi day-time and the light-clearer's «དུར་གྱི་སྟེང» on the Zhol, Taikhang and Sakya prints and the almanac cell: all carve the 1996 print's words, no print separates day from hour (q445 open; the almanac cell «འབྲུག་གྱི་ཉི་དུས། ནུབ་བལྟ།» writes an animal and a direction in one cell), «སྟེང» confirmed on Zhol and Taikhang, no print names the grave's animal by month (q444 open), the Taikhang year charts have «ཧལ་ཁྱི། བུ་མོ།» and no mothers and sons (q36 open); one new question | WB vol. 2, ch. 31, pp. 185, 210, 219 (Gser thang reprint I3CN5029, image = page + 6) and vol. 1, pp. 116–121 and the almanac cells «འབྲུག་གྱི་ཉི་དུས། ནུབ་བལྟ།» (vol. 1 etext pp. 148–150), questions 36, 444, 445; the Zhol, Taikhang and Sakya Centre prints | RD72 | [25](#block-25) | corpus/wb/ch31-gaps.md | Read RD76: for q445 and q444 the same lines on the Zhol, Taikhang and Sakya prints and the almanac cells of vol. 1 on the scan, and for q36 the mothers' and sons' seats where a print has a chart; settle what they allow, mark the rest unsettled, write each fruitless search down as a dead end. |
| <a id="rd77"></a>RD77 | Ch. 27's three open letters on the Taikhang and Zhol scans: p. 17 ll. 9 and 26, p. 9 l. 6 — done 2026-10-28: ch. 27's three open letters on the Taikhang scan: p. 17 ll. 9 and 26 and p. 9 l. 6 are fused ink told by neither head nor foot, nothing settled; q407: no difference between the Taikhang and Zhol impressions on these three lines (the pair «ཆུ་མིག» / «ཆུ་མག» not rechecked); no new question | WB vol. 2, ch. 27, pp. 9 and 17 (Taikhang I5878, Zhol WBZ I1KG1710; the reprint I3CN5029, image = page + 6), question 366, 407 | RD75 | [31](#block-31) | corpus/wb/ch27.md | Read RD77: on the Taikhang and Zhol scans at high magnification, p. 17 l. 26 (བ or འ before «ཞོགས», now that «འུར་ཞོགས» is an alarm), p. 17 l. 9 («རྔམས» or «རྔ་མས») and p. 9 l. 6 («རྒུད» or «སྐུད» on the block, q407); settle what the scan allows, mark the rest unsettled, write each fruitless search down as a dead end. |
| <a id="rd78"></a>RD78 | WB vol. 1 p. 133, the table of the twelve days: the other cells and the la-knower lines — done 2026-10-28: WB vol. 1 p. 133's two tables of twelve cells (machine state): the days' seats, harms, remedy acts, hearth god's side and «གཉན»-kind last names, and the times' la-knowers with animals; no cell glosses «ཁ་ཡོ» and «སྦྲང་མ» is in none, so q446 and q447 are untouched; RD74's passage misplaces the rat cell (third from the right in the lower row of the upper table); three new questions | WB vol. 1, p. 133 (Gser thang I3CN5028 img. 140; 1996 print I1KG12906 img. 143) and the Bhutan copy (I1KG11905 img. 571, printed p. 561), the cells beyond the rat day RD74 read, questions 404, 439, 446, 447 | RD74 | [25](#block-25) | corpus/wb/ch31-gaps.md | Read RD78: the twelve-day table of vol. 1 p. 133 cell by cell on the scan, the 1996 print and the Bhutan copy, and the cells that bear on the rat day's «ཁ་ཡོ་ནས་འདྲེན» (q446) and «སྦྲང་མ» (q447) beside p. 224; settle what the scan allows, mark the rest unsettled, write each fruitless search down as a dead end. |
| <a id="rd79"></a>RD79 | Ch. 33's pages that no reading has opened: pp. 313–329 — done 2026-10-28: ch. 33 pp. 313–329 read as passages (32, state machine): the 28 mansion verses carried into the corpus with their settled Tibetan, p. 313's planets' directions for war and journey (Sun, Venus, Mars, Rāhu, Saturn, Moon, Mercury, Jupiter round the directions from the east) and the weekday's rising dates by animal triad are new, the seven classes of the mansions with the numerals read on the scan; five new questions | WB vol. 2, ch. 33, pp. 313–329 (Gser thang reprint I3CN5029, image = page + 6; the 1996 scan img. = page + 8), pages `kb.py coverage wb` lists as unread | — | [25](#block-25) | corpus/wb/ch33.md | Read RD79: pp. 313–329 of ch. 33 on the scan, line by line, as the other chapters' unread pages were read (Yigdzin-1 for the whole run, the eye for what bears on the app); record the passages, mark every doubtful word, write down what bears on works, directions or days. |
| <a id="rd80"></a>RD80 | Ch. 33's pages that no reading has opened: pp. 303–307, 334–336, 341–347, 349–350 — done 2026-10-28: ch. 33 pp. 303–307, 334–336, 341–347 and 349–350 read by machine only (Yigdzin-1 on the reprint, nine passages, state machine): the life-spirit sites by date and hour, the vowels' five stages and four meetings, remedies by planet and letter, the planets' and mansions' elements, the named day-combinations, the yogas' results and the eleven karaṇas' works; the tables of pp. 341–346 not transcribed, no eye pass; five new questions | WB vol. 2, ch. 33, pp. 303–307, 334–336, 341–347, 349–350 (Gser thang reprint I3CN5029, image = page + 6; the 1996 scan img. = page + 8), pages `kb.py coverage wb` lists as unread | — | [25](#block-25) | corpus/wb/ch33.md | Read RD80: those pages of ch. 33 on the scan, line by line, as the other chapters' unread pages were read (Yigdzin-1 for the run, the eye for what bears on the app); record the passages, mark every doubtful word, write down what bears on works, directions or days. RD79 reads pp. 313–329 in the same file: keep to your own section. |
| <a id="rd81"></a>RD81 | Ch. 32's pages that no reading has opened: pp. 276–280, 282–294 — done 2026-10-28: ch. 32 pp. 276–280 and 282–294 read by machine only (Yigdzin-1 one pass, 12 passages, English a summary): the graveyard's layout by paces, the earth-deposit vases, foundation stones and Phurba pegs, the signs of dug ground, the outer door table (places 1–8 per side as bad, middling or good), rooms by direction, the new-hearth rite and the earth-lord and Golden Tortoise long-life rite; digging at Lto-'phye's ninth part or back harms the household, the tail's lines doubtful; no scan reading, the numerals of the door table unchecked; three new questions | WB vol. 2, ch. 32, pp. 276–280 and 282–294 (Gser thang reprint I3CN5029, image = page + 6; the 1996 scan img. = page + 8), pages `kb.py coverage wb` lists as unread (p. 281, the stake count of the digging figure, is read) | — | [25](#block-25) | corpus/wb/ch32.md | Read RD81: those pages of ch. 32 on the scan, line by line, as the other chapters' unread pages were read (Yigdzin-1 for the run, the eye for what bears on the app); record the passages, mark every doubtful word, write down what bears on works, directions or days. |
| <a id="rd82"></a>RD82 | Ch. 33's pages that no reading has opened: pp. 360–363, 366–368, 370 — done 2026-10-28: ch. 33 pp. 360–363, 366–368 and 370 read by machine only (Yigdzin-1 one pass, four passages, English partly interpretive): the works on the twelve animal days and double-hours, the date's earth lords after the Black Moon with works spoiled and remedies, the nine mewa's works, riders and skulls of the eight trigrams and the sky doors, the box-binding invocations; not to be built on before the scan is read; four new questions | WB vol. 2, ch. 33, pp. 360–363, 366–368 and 370 (Gser thang reprint I3CN5029, image = page + 6; the 1996 scan img. = page + 8), pages `kb.py coverage wb` lists as unread | — | [25](#block-25) | corpus/wb/ch33.md | Read RD82: those pages of ch. 33 on the scan, line by line (Yigdzin-1 for the run, the eye for what bears on the app); record the passages, mark every doubtful word, write down what bears on works, directions or days. |
| <a id="rd83"></a>RD83 | Ch. 35's first pages, pp. 429–437, no reading has opened — done 2026-10-28: ch. 35 pp. 429–437 read by machine only (Yigdzin-1 one pass, nine passages): a verse account of the fields of knowledge — the eighteen, the five great fields, grammar with its history and the dates to beware of for it (p. 432), the five minor fields, logic, the crafts from the Buddha's body proportions; no calendar content, star reckoning (p. 433) refers back to ch. 1; pp. 438–440 not read, scan reading needed (row RD84) | WB vol. 2, ch. 35, pp. 429–437 (Gser thang reprint I3CN5029, image = page + 6; the 1996 scan img. = page + 8), pages `kb.py coverage wb` lists as unread | — | [25](#block-25) | corpus/wb/ch35.md | Read RD83: the first pages of ch. 35 on the scan, line by line (Yigdzin-1 for the run, the eye for what bears on the app); a new corpus file `corpus/wb/ch35.md` with the frontmatter of STRUCTURE.md; record the passages, mark every doubtful word, say what the chapter is about. |
| <a id="rd84"></a>RD84 | Ch. 35's remaining pages, pp. 438–454, and a scan reading of pp. 429–437 — done 2026-10-31: every line of pp. 429–454 read on the scan (the line ends of pp. 447, 451, 454 on the 1996 print), all 26 passages marked scan; no calendar rule but p. 432's days to beware of for grammar; Padmākara at p. 443, Khyung nag Shākya dar the Phugpa at p. 451 (q476, q478 answered; q503–q505) | WB vol. 2, ch. 35, pp. 438–454 (Gser thang reprint I3CN5029, image = page + 6; the 1996 scan img. = page + 8), unread; pp. 429–437 are machine passages (RD83) | RD83 | [25](#block-25) | corpus/wb/ch35.md | Machine pass done 2026-10-28, eye pass open (Yigdzin-1 and MITRA over img. 435–460, 26 passages marked witnessed, no calendar rule in pp. 438–454, three questions; the scan read line by line is what stays; before: only Yigdzin-1 ran, img. 444–460 cached under docs/sources/wb/yigdzin/, nothing recorded; the pages look like the crafts, medicine and translators' lineages, no calendar rule seen by keyword). Read RD84: pp. 438–454 of ch. 35 on the scan, line by line, and pp. 429–437 against the scan (Yigdzin-1, MITRA, the eye); the crafts, medicine and other fields after logic; say whether any calendar content stands in them. |
| <a id="rd85"></a>RD85 | Ch. 33 p. 313 on the scan: the planets' directions and the weekday's rising dates — done 2026-10-28: ch. 33 p. 313 lines 1–11 by eye on the reprint (img. 319): «མ་རྒྱབ་གྲོགས་མདུན་གཏད་པ་གཅེས», «ཉི་མ་ཤར་དགེ་ཆུ་བདག་ངན» (the lord of water is in the print, not a slip) and «བྱ་གླང་སྦྲུལ་གསུམ་ཕུང་དར་ཚེས» confirmed as the machine read them; the senses (q449, q450, q451) stay open, the page has no gloss; the 1996 scan not re-read | WB vol. 2, ch. 33, p. 313 (Gser thang reprint I3CN5029 img. 319; the 1996 scan img. 321), questions 449, 450, 451 | RD79 | [25](#block-25) | corpus/wb/ch33.md | Read RD85: p. 313 line by line by eye on both scans, the lines 1–11 (the direction rule, the lord of water, «ཕུང», the triads of rising dates); settle what the scan allows, mark the rest unsettled, write each fruitless search down as a dead end. |
| <a id="rd86"></a>RD86 | Ch. 32 p. 287 on the scan: the outer door table — done 2026-10-28: ch. 32 p. 287's door table by eye on the reprint (img. 293, lines 5–8): the scan prints ordinal words, not digits, and all four sides match the machine's ranking word for word (q462 narrowed); the east line's opening «དང་» (place 1 or a conjunction) and the direction of counting stay unsettled; the 1996 scan and the rest of the page not read; one new question | WB vol. 2, ch. 32, p. 287 (Gser thang reprint I3CN5029 img. 293; the 1996 scan img. 295), question 462 | RD81 | [25](#block-25) | corpus/wb/ch32.md | Read RD86: p. 287 line by line by eye, the numerals and the direction of counting of the door table; settle what the scan allows, mark the rest unsettled, write each fruitless search down as a dead end. |
| <a id="rd87"></a>RD87 | Ch. 31 pp. 185–186 on the scan: the black hal khyi's mothers and sons, and the sun's klung in Langging's list — done 2026-10-28: ch. 31 pp. 185–186 by eye on the reprint (img. 191–192): no line gives a seat, direction or order to the black hal khyi's five mothers and sons (q433 stays open); «ཉི་མའི་ཀླུང» stands again with no gloss (q432 open); the print numbers the hal khyi 16, a bracket 17–25, then Dkar-sham 26, Sbal-te 27, Langging 28, Tsang-kun 29 (the pool's «no. 19» for Langging is its own order); one new question | WB vol. 2, ch. 31, pp. 185–186 (Gser thang reprint I3CN5029, image = page + 6; the 1996 scan img. = page + 8), questions 432, 433 | RD63 | [25](#block-25) | corpus/wb/ch31-gaps.md | Read RD87: pp. 185–186 line by line by eye, the black hal khyi's five mothers and sons with their seats and order, and «ཉི་མའི་ཀླུང» in Langging's list; settle what the scan allows, mark the rest unsettled, write each fruitless search down as a dead end. |
| <a id="rd88"></a>RD88 | Ch. 34 pp. 378 and 428 on the scan: the 9th hair date's result, work 17's first syllable, the closing verse's «ཁྱད་པར་དུ» — done 2026-10-28: ch. 34 pp. 378 and 428 by eye on the reprint: the closing verse's tail and colophon written out (p428c, machine), «ཁ་དམར་སྨྲ» and «དག་པའི་ཆོས་ཉིད» settled against MITRA, «ཁྱད་པར་དུ» clear with no weight sign (q408 open), the 9th hair date's result judged by nothing on the page (q436 open), work 17's stack undecidable at the print's size (q437 open); KP's contents list «སྦྲ་དང་ཕྱ་ཐེར་འཚེམ་པ» makes the two named things; «གུད་སྦས» the profound part set apart; three new questions | WB vol. 2, ch. 34, pp. 378 and 428 (Gser thang reprint I3CN5029, image = page + 6; the 1996 scan img. = page + 8), questions 408, 436, 437 | RD79 | [25](#block-25) | corpus/wb/ch34.md | Read RD88: p. 378 and p. 428 line by line by eye; the subjoined ra of work 17, «ཕྱར» and «ཕྱ་ཐེར», the result of the 9th hair date, the closing verse; settle what the scan allows, mark the rest unsettled, write each fruitless search down as a dead end. |
| <a id="rd89"></a>RD89 | Ch. 27 pp. 16, 20 and 23–25 on the scan: the la's wandering, the golden turtle's wood sheep, the four reckonings of «བརྗེ» — done 2026-10-28: ch. 27 pp. 16, 20 and 23–25 by eye on both scans: the la's directions settled as letters (rat south, snake north, horse north, pig south; q363 keeps the sense), p. 20 l. 24 «གཞན་རྣམས་བསྡེབས་པ་དགྲ་གཤེད་མེད» (q364), «བརྗེ» the same stack twice and the black verse a syllable short (q365 narrowed), «སྤ་འཁར», «ལྕུག་ཕྲན» settled; two new questions | WB vol. 2, ch. 27, pp. 16, 20 and 23–25, questions 363, 364, 365 (Gser thang reprint I3CN5029, image = page + 6; the 1996 scan img. = page + 8) | RD79 | [25](#block-25) | corpus/wb/ch27.md | Read RD89: the three passages line by line by eye; settle what the scan allows, mark the rest unsettled, write each fruitless search down as a dead end. |
| <a id="rd90"></a>RD90 | Ch. 27 pp. 3, 9 and 17 on the scan: whose element starts the circles, the four seasonal names — done 2026-10-28: ch. 27 pp. 3, 9 and 17 by eye on both scans: «ལྕོང་མོའི་རྔ་མས» (two syllables), «འུར་ཞོགས», «ཚད་ཟླ་མག་སྐུད» and «ཇི་ནོང» settled as letters; the bare «ཁམས» of the circles against «དབང་ཁམས» of p. 17 stays open (q259); three new questions | WB vol. 2, ch. 27, pp. 3, 9 and 17, questions 259, 366 (Gser thang reprint I3CN5029, image = page + 6; the 1996 scan img. = page + 8) | RD79 | [25](#block-25) | corpus/wb/ch27.md | Read RD90: the three pages line by line by eye, the circles' first element and the four names of q366; settle what the scan allows, mark the rest unsettled, write each fruitless search down as a dead end. |
| <a id="rd91"></a>RD91 | Ch. 32 pp. 282–285 and 291–294 on the scan: the ninth part of Lto-'phye, «བུད་མེད་བུས་པ» and «ཁྱི་སྤྱང་བྲང་ཤུལ» — done 2026-10-28: ch. 32 pp. 282 l. 17–19, 283 l. 7–8 and 294 l. 6–8 by eye on both scans: the tail line reads «མཇུག་མ་གླང་ཆེན་རྟ་མ་ཧེ» (ha, not nya: elephants, horses, water buffaloes and cattle die), «བུད་མེད་བུས་པ» (women and small children) and «ཁྱི་སྤྱང་བྲང་ཤུལ» (earth from the old lair of dogs and wolves put at the lintel) clear; the rest of the pages not read by eye; two new questions | WB vol. 2, ch. 32, pp. 282–285 and 291–294, questions 463, 464 (Gser thang reprint I3CN5029, image = page + 6; the 1996 scan img. = page + 8) | RD81 | [25](#block-25) | corpus/wb/ch32.md | Read RD91: the lines holding the words of q463 and q464 by eye; settle what the scan allows, mark the rest unsettled, write each fruitless search down as a dead end. |
| <a id="rd92"></a>RD92 | The 暦林問答集's year direction gods: the 八将神 for the year sheet — done 2026-10-31: every god by the year's branch, none by the stem; 大歳 the year's branch, 大陰 two behind, 歳破 opposite (graded by where it falls), 豹尾 opposite the 黄幡, 歳刑 a whole quarter by the triad, 大將軍 three years in each 仲 with five-day outings and no anchor year, 歳殺 among 丑未辰戌 with no rule, 黄幡 the 大歳's grave; the modern tables of 大將軍 and 歳殺 are the digests' alone (q484–q491) | 『暦林問答集』 (賀茂在方, 1414), §6 大歳, §8 大將軍, §9 大陰, §10 歳刑, §11 歳破, §12 歳殺, §13 黄旛, §14 豹尾 and §15 歳名 (群書類從 print, NDL pid 14488380, img. 11–16; the manuscript ちニ-5, pid 2533201, as witness); against the modern digests (koyomi8, the NAOJ) and the 高島暦's 方位吉凶図 as its listings describe it | — | [54](#block-54) | kyureki.md | Read RD92: the 暦林問答集's §6 and §8–§15 by eye on the NDL scan (no model), each god's direction by the year's stem or branch, what is avoided and what is allowed towards it, any time limit (the 大將軍's three-year stay, the 遊行) and its ruling, with the manuscript where the print is doubtful; then each direction against the modern digests' reckoning, a difference stated, not settled. |
| <a id="rd93"></a>RD93 | 金神: which old text gives it, and how the printed almanac reckons it — done 2026-10-31: the 簠簋内伝 (巻一 §5–§9) is the only old text found, with two year tables by the stem (§5, and §6 «此説暦出»), the 遊行 by the 寅 days and by the seasons and the 間日, «尤可厭» and no works; the 暦林問答集 never names it, and the court diaries record the 陰陽道 not using it (1114, 1173); today's table blends §5 and §6; 金神 is the owner's to decide (D9; q497–q502) | The NDL catalogue (`ndl.py`) for old works on 金神 (the year's 金神 directions, its 遊行 and 七殺), read on the scan of the oldest found; the 暦林問答集 searched for 金神 inside another section; the 簠簋内伝 (NDL pid 1911335) checked among the candidates; the modern digests for today's reckoning | — | [54](#block-54) | kyureki.md | Read RD93: find the old text that gives 金神 (edition, author and date from the catalogue, never from memory) and read its passage by eye on the scan (no model): the directions by the year's stem, the 遊行 and what is avoided; whether the 暦林問答集 names it anywhere; the modern reckoning against it. If the 簠簋内伝 is the only old source, say so: the owner decides (D9). |
| <a id="rd94"></a>RD94 | The daily directions: 天一神's and 日遊神's — done 2026-10-31: the 暦林問答集 avoids every work facing 天一's direction on its 44 days (battle, childbirth above all) and has no 方違え; the 方違え (a night elsewhere) only in texts 古事類苑 quotes; 日遊神 outside the house has no Japanese course (the 協紀辨方書 makes it 天一's); Meiji almanac-books turn §43's «勿掃» into sweeping; whether the printed almanac shows either daily waits on D12 (q492–q496) | 『暦林問答集』 §53 天一 (GR img. 49–50, the course already read in K3-R) and its 日遊 passage, for what is avoided towards each and any 方違え; the modern digests (koyomi8, the NAOJ) on whether the printed almanac shows the daily 天一神 direction and 日遊神 outside the house; any old text giving 日遊神's course on the days it is out of the house | — | [54](#block-54) | kyureki.md | Read RD94: by eye on the NDL scans (no model), what the 暦林問答集 says is avoided towards 天一神 and how a 方違え is made, and where 日遊神 is on the days it is not in the house; whether today's printed almanac carries either daily (the digests; the paper 高島暦, D12, settles it); 天一天上 itself stays K4's. |
| <a id="rd95"></a>RD95 | The dead's open questions, pp. 44–75: the preliminary, the la board, the first four examinations, the funeral shares — done 2026-11-01: in two parts: counts recomputed (65), 66, 200, 277, 279 (a puppy), 280 (a little song), 284 answered; 212, 268, 269, 271, 273–276, 278, 285, 287 narrowed; 281 a dead end; not done: the fourteenth's sums, the tenth's identifying 1,952 against 1,826 on the scan, the preliminary's 39 and 17, the Zhol-print pass; the claim dead-la-pebbles-layout is contradicted by WB p. 45 (RD103) and is to be corrected | WB vol. 2, pp. 44–75 (images 52–83), by eye on the page images, every page already read by eye (no model) where a stack decides, the Gser thang reprint (I3CN5029, image = page + 6) beside; the notes, pp. 500–504, 513, 522 | — | [31](#block-31), [60](#block-60) | corpus/wb/ch29.md, corpus/wb/ch29-demons.md, corpus/wb/ch29-funeral.md, verdicts.md | Read RD95 (read in part 2026-11-01: 65, 66, 200, 277, 284 answered or narrowed, 273, 278, 287 in part, see its section in verdicts.md; LEFT: 212, 265, 268–272, 274–276, 279–283, 285, 429, the by-eye pass over the doubtful stacks (274, 279, 280), the tenth's and fourteenth's sums line by line, the preliminary's 39 and 17, «འཚོལ་བ»/«འཆོལ་བ» on the Zhol print, and BDRC's full-text search; the dictionary clone is at ~/ai/tibetan-ocr/scratch/rd97b/tibetan-dictionary): open questions 65, 66, 200, 212, 265, 268–285, 287 and 429, each on its page: the counts recomputed from the passages (993 against 1,008; the 87; 1,874 against 1,828), the doubtful stacks by eye on both prints, the unexplained words and rites in Steinert's dictionaries (`locate.py`) and BDRC's full-text search (`bdrc_search.py`); each question answered, narrowed or its search written as a dead end. |
| <a id="rd96"></a>RD96 | The dead's open questions, pp. 75–97: the provisions, the old kinds, the nine-eye, the sounding, the grave days — done 2026-11-01: q307, 308, 314, 339, 340 answered (the sounding counted over the 25 pebbles; LT's rounds; the two accounts of the last months' 26th/16th; the note's «ཁོ་ན» = «ཁོན»; the name-endings); 288, 309, 310, 312, 313, 315, 316 narrowed; q311, 289 dead ends; the Zhol scan letters (310, 311) not read | WB vol. 2, pp. 75–97 (images 83–105), by eye on the page images, every page already read by eye (no model) where a stack decides, the Gser thang reprint beside; the notes, pp. 504–512 | — | [31](#block-31) | corpus/wb/ch29-provisions.md, corpus/wb/ch29-remains.md | Read RD96: open questions 288, 289, 307–316, 339 and 340, each on its page: over which pebbles the sounding counts, the ages and rounds of the nine-eye and the old kinds, the grave days' two accounts, the eight places for khen, gin and zon against the notes; the doubtful words by eye on both prints and in the dictionaries and BDRC's full-text search; each answered, narrowed or a dead end. |
| <a id="rd97"></a>RD97 | The dead's open questions, pp. 97–143: the doors and the bearer, the thirteenth examination's rites, the grave provisions — done 2026-11-01: in two parts: the run of deaths one word «ཤི་སྒབ» (175, 221, 333), «ཁ་སྨྲང» an account, «བཙད་པོ» the old «བཙན་པོ» (291, 292), «རྨང་པོ» childless (293), «སྤོ» = Uttaraphalgunī (284, 296, 380), the gzhob sel / gung phur / sha ru rites (299), the omen words (264), the colophon's leads (266), the notes' 14 rites (213, 214, 297); NOT reached: 260–262 (the p. 130 stack), 300 (p. 114 clans), the ur mnan syllables, the sgab stacks at 3× | WB vol. 2, pp. 97–143 (images 105–151), by eye on the page images, every page already read by eye (no model) where a stack decides, the Gser thang reprint and WBZ (images 777–826) beside; WB vol. 2, pp. 50, 65, 114, 166 for «ཁ་སྨྲང» and the Phalgunī mansions; the notes, pp. 517, 522 | — | [31](#block-31) | corpus/wb/ch29-rites.md, corpus/wb/ch29-grave.md, corpus/wb/ch29-remains.md, verdicts.md | Read RD97 (read in part 2026-11-01: 174, 175, 221, 333, 295, 338, 370, 335, 337, 349 have a first check, see its section in ch29-remains.md and verdicts.md; LEFT: 213, 214, 260–262, 264, 266, 284, 291–293, 296–300, 334, 380, and a 3× ka/ga crop of the stack at p. 106 l. 4 and the sgab stacks; the dictionary clone and BDRC search were not run): open questions 174, 175, 213, 214, 221, 260–262, 264, 266, 291–293, 295–300, 333–335, 337, 338, 349, 370 and 380, each on its page. «སྐབ» or «སྒབ» (175, 221, 333) and «ཁ་སྨྲང» (174, 349) are answered once for every page they stand on, as are the Phalgunī mansions «སྤོ», «སྤོ་སྨད» (296, 380, with 284 of RD95 if it is not done); the rites and objects in the dictionaries and BDRC's full-text search; each answered, narrowed or a dead end. |
| <a id="rd98"></a>RD98 | WB ch. 27's open questions: the thirteen circles' verdicts, the la's wandering, the four reckonings of «བརྗེ», the words — done 2026-11-01: the leaves' verdicts are told by the five aspects (216, 217 narrowed), «ཚད་ཟླ» a slip for «ཚང་ཟླ», «གོས་ལེགས» for «གྲོས་ལེགས», the mewa of the notes one pebble (368), «ས་ཡོས» not shown a slip by the elements' rule (506); the Zhol, Taikhang and dictionary comparisons not repeated | WB vol. 2, pp. 3–25 (images 11–33), by eye on the page images, every page already read by eye (no model), WBZ and the Taikhang reproduction beside for pp. 9 and 17; the notes, pp. 532–533; the *Moonbeams* (DZ f. 24a) | — | [31](#block-31), [25](#block-25) | corpus/wb/ch27.md, verdicts.md | Read RD98: open questions 216, 217, 225, 363–366, 368, 407, 474, 475, 479–481 and 506, each on its page: whether the five verdicts go by aspect or by year, the sixteen limbs' place, how the circles' two mewas combine, whether the Taikhang and Zhol prints are two blockings; the words in the dictionaries and BDRC's full-text search; each answered, narrowed or a dead end. |
| <a id="rd99"></a>RD99 | WB ch. 28's open questions: the small signs' seasons, the hand of stars, the black small feet, the doors of sky and earth — done 2026-11-01: q321 (the chapter counts by the Chinese reckoning, spring = Hor 11, 12, 1), 323 (the Pleiades), 324 (end of autumn, the black kite) answered; 325 in part (the bird's days 40, «སོ་གཉིས» the 32nd day), 326–328, 330–332 narrowed; q322 (the figure's labels), 329 left open; the ས/སེ and ཕྱི/སྤྱི stacks not settled on the scan | WB vol. 2, pp. 26–43 (images 34–51), by eye on the page images, every page already read by eye (no model), WBZ (images 680, 686) beside; the notes, p. 537 | — | [31](#block-31) | corpus/wb/ch28.md | Read RD99: open questions 321–332, each on its page: the seasons against the Phugpa months, the parts of p. 28's figure and p. 29's sky-bird, the six full moons, the fifty water-hours, whether a black small foot needs year, month and hour together, the 360's fifth and sixth years; the words in the dictionaries and BDRC's full-text search; each answered, narrowed or a dead end. |
| <a id="rd100"></a>RD100 | WB ch. 26's open questions: the thirty's count through the years, its eight fighting pebbles, its words — done 2026-11-01: q204 «མི་ཚན» a slip for «མི་ཆེན», 203 «ཁོན» likely «བོན» (not proved), 391 te khyim the earth lord, 305 the twelve female spirits; 215, 259, 301, 303, 304, 306 narrowed or unmoved; a new witness for the thirty, the Garland of Stars (KD vol. 3 pp. 517–546, 644), read from the etext only, the lent scan not opened | WB vol. 1, pp. 453–473 (images 463–483), by eye on the page images, every page already read by eye (no model), WBZ (images 605–627) beside; the notes, pp. 532–533; WB vol. 2, p. 3 for the circles' element | — | [31](#block-31) | corpus/wb/ch26.md, corpus/wb/notes-7.md, verdicts.md | Read RD100: open questions 203, 204, 215, 259, 301, 303–306 and 391, each on its page: whose element starts the thirty and the thirteen circles, how the count runs through the years, whose year serves the son and the bride, the 241st pebble, whether the check order is the fighting pebbles' order, «རྡེལ་ཁྲ» on p. 468; the words in the dictionaries and BDRC's full-text search; each answered, narrowed or a dead end. |
| <a id="rd101"></a>RD101 | The birth's and the match's open questions: the words of pp. 330–349, the match of mixed kinds, the karma reckoning over the match — done 2026-11-01: q176 («དར་གུད་རྟ་ལ་བསྐྱོན» on the Zhol print, «ཏ» a slip), 180, 207, 236, 396 answered; 177, 178, 181, 183, 205, 208, 392, 393 narrowed; 209/354 a dead end for a name; the Taikhang reproduction not read (no GPU slot came), the Zhol letters rest on the eye and etexts | WB vol. 1, pp. 329–375 (images 339–385), by eye on the page images, every page already read by eye (no model), the Gser thang reprint beside; the notes, pp. 478, 483, 488, 524–526; UT pp. 153–155; YS reply 111 (images 467–468) | — | [58](#block-58) | year-of-life.md, verdicts.md, corpus/wb/notes-6.md, corpus/ut/ch4-karma.md | Read RD101: open questions 176–178, 180, 181, 183, 205, 207–210, 236, 354, 355, 392, 393 and 396, each on its page: the words of the birth read whole, the 54 of the birth wind, which verdict stands when the birth and the year of age disagree, a name for a match of mixed kinds, which findings of the karma reckoning make an aspect bad and whether a middling one bars the match, the white enemy pebble; Mipham decides where WB is silent; each answered, narrowed or a dead end. |
| <a id="rd102"></a>RD102 | The year of age's open questions: the obstacles' words, the household's general pebbles and rows — done 2026-11-01: q90 (the forefathers' tomb: their own trigram, mewa and progressed sign at death), 168 (the general pebbles are the household's taken together), 206 («ལོ་རྟ» = the years) answered as far as the sources go; 81, 91, 169, 173, 319, 381 narrowed; 258 nothing new; doubts left on the scan: p. 381 the stack after ཁ་, p. 405 the line head, p. 386 «མ་ཏྲི»/«མ་ཏྲཾ» | WB vol. 1, pp. 380–416 (images 390–426), by eye on the page images, every page already read by eye (no model), WBZ (images 506) beside; the notes, pp. 489–495 | — | [58](#block-58), [31](#block-31) | corpus/wb/ch24.md, corpus/wb/notes-2.md, year-of-life.md | Read RD102: open questions 81, 90, 91, 168, 169, 173, 206, 258, 319 and 381, each on its page: the seven-red's two readings, the forefathers' tomb and the secret obstacle's «མ་སྤར», the household's general pebbles and the wealth's places among its rows, the other verdicts «ཅི་རིགས», «ཁ་སྦྲང» and «ངོ་ཐེག», «ལོ་རྟ»; the words in the dictionaries and BDRC's full-text search; each answered, narrowed or a dead end. |
| <a id="rd103"></a>RD103 | The *Moonbeams* and LT on the open questions of the dead, the la board, the thirty and the household — done 2026-11-01: q371 answered (the la board's count is WB vol. 2 p. 45; «a half» = white and black as a pair), q416 the nose rite, LT's five-clan table is WB vol. 1 p. 261 (375 a conflict with the notes' corner rule), 374 «ལྔ་མཐུན»; 188, 190, 223, 224, 372, 376–378, 397 narrowed; 373 and 379 not read, 318 unmoved; the claim dead-la-pebbles-layout is contradicted by WB p. 45 and is to be corrected by RD95's second part | The *Moonbeams* (DZ, BDRC I2533, img. 029–061), on the scan; LT pp. 9–11, 20–28; against WB vol. 1, pp. 453–454 and the notes, pp. 499–500, 532–533 | — | [31](#block-31), [58](#block-58) | corpus/dz/dz.md, corpus/lt/lt.md, verdicts.md | Read RD103: open questions 188, 190, 223, 224, 318, 371–379, 397 and 416 in the *Moonbeams* and LT, each on its folio or page: the half place and the half verdict, the general and particular verdict, the works the *Moonbeams* sends its results to (identified from a catalogue, never from memory), the la board's count, the burial reckoning's roots, the readings 28 and 29, the son text's trigram pebble; each answered, narrowed or a dead end. |
| <a id="rd104"></a>RD104 | The *Removing the Tarnish*'s replies on the open questions — done 2026-11-01: the questions of the 208 and replies 137, 140, 148, 152, 156 read on Yigdzin-1: q382, 383, 384, 385, 386, 387, 390, 399, 400 narrowed (reply 156 cites the same work as «རྣམ་འདེན་མཆོག» with no author; «དཀར་ནག་བསྔོ་མེད» short for WB's «བསྔོ་རུ་མ་བཏུབ»; «ཡི་བམ» = «ལི་ཁམ»); NOT read: replies 1–100 and 161–208 (170: q463, q482; 174, 177–180: ch. 32; 190–195: ch. 34, q408, q409, q472, q473; 70/71 Dus 'khor dbyangs 'char) — the next reading's | YS (the replies, img. 099, 467–468, 491–514), on the scan; YS vol. 2 img. 016–017; the list of its 208 questions searched for every other open question on WB | — | [31](#block-31), [32](#block-32) | corpus/ys/other-reckonings.md, corpus/ys/thirty-circles.md | Read RD104: open questions 382–388, 390, 399 and 400, each on its reply; then the list of YS's 208 questions read whole against the open questions of WB and the notes, every reply that touches one read and quoted, its question narrowed; each answered, narrowed or a dead end. |
| <a id="rd105"></a>RD105 | The notes' doubtful stacks on Mipham's own print, and the notes' last pages — done 2026-11-01: on Mipham's own print and the Chengdu typeset: q199 («སོ་སོའི་རང་རྡེལ»), 182 («འཛོམས»), 201 («དྲུང་མཁས་པ་རཏྣ») answered as to the print; 198, 179, 101, 353 (the worked example names phung, klung, kyis), 352 («གྱོ་ཁང»), 351, 320, 356 narrowed; 202 not shown to exist; not reached: the typeset's p. 526 and p. 538, the eye reading of I2029 img. 744 «ཀླུང»/«ཀྱཱིས» | Mipham's own print of the notes (BDRC I2029, img. 621–750), on the scan; WB vol. 2, pp. 475, 484, 488, 490–491, 513, 526, 534–541 (img. 483–549) | — | [58](#block-58), [31](#block-31) | corpus/wb/notes-1.md, corpus/wb/notes-2.md, corpus/wb/notes-6.md, corpus/wb/notes-8.md | Read RD105: open questions 101, 179, 182, 198, 199, 201, 202, 211, 317, 320, 351–353 and 356: each doubtful or blotted stack of the 1996 print read on Mipham's own print (p. 475's first row and marks, «ཟླ་བའི་སྣང», «འཛོམས», «གནས་སྐྱུག», «ཤིད་དུར», the secretary Ratna), then the hand reckoning's «ལུ», the thirty 'bri wang, «རྒྱ་ཁང», the box over the fold and the collection to be written separately; each answered, narrowed or a dead end. |
| <a id="rd106"></a>RD106 | The practitioners' manuscripts: the dead's speckled deciding pebble, the numbered cells, the life reckoning's blanks — done 2026-11-01: by the etext only: q253 narrowed (WB's first examination reads the dead's four pebbles black and white only; a speckled one arises from the tie rule of p. 46), 254 narrowed (the manuscript's colour rule is its own), 255 cells 1–9 likely the nine mewa's harms, 256 the blanks explained by RD29's colophon, 257 a dead end (water bird 1873 or 1933); no scan, no model | BDRC I1LT0800 (img. 370–383) and I1KG12897 (img. 004–384), on the scan; WB vol. 2, pp. 55–56 | — | — | verdicts.md | Read RD106: open questions 253–257 on the manuscripts' images: where a speckled deciding pebble of the dead is read, the repetition by the four together, the keys of the numbered cells, the blanks Khyenrab Norbu left, Prajñāmaṇi and the water bird year (from a catalogue, never from memory); each answered, narrowed or a dead end. |
| <a id="rd107"></a>RD107 | The direction gods' open questions in the old texts — done 2026-11-01: the direction gods in the old Japanese texts, read on NDL images: the 簠簋内伝 prints the whole year table (single branches for 大將軍, 歳殺, 黄幡, 歳刑; Q484–487), 金神七殺方 is today's table and koyomi8's 戊癸 row is a slip (497, 498), the 大將軍's year turns at the eve of 立春 (491), 日遊在内 on every 戊 and 己 day (492), 天一's start days 5 and 6 apart (494); 499 no text says which 遊行 wins, 493 and the 日遊's outer course dead ends | The 暦林問答集 (群書類從 print, NDL pid 14488380), the 簠簋内伝 (NDL pid 1911335), the 拾芥抄 and the 古事類苑's 方術部, by eye on NDL's page images (no model); the modern digests (koyomi8, the NAOJ) | — | [54](#block-54) | kyureki.md | Read RD107: open questions 484–487, 489, 491–495 and 497–501, each in the old text that speaks to it: the 大將軍's 仲 by the year, the 歳殺's branch, the 黄幡's grave, the 歳刑's span, the 歳破 and livestock, where the 八将神's year begins, 日遊 in the house, 天一's five-day count, the 拾芥抄's line, which 金神 table the Edo almanac followed and its 遊行; each answered, narrowed or a dead end. The paper 高島暦's questions (488, 490, 496, 502) are not this reading's. |
| <a id="rd108"></a>RD108 | The lent scans: KD vol. 3 and the Sakya volume on the eclipse clause, the sheets, the supplement; KD's title page and the Tibet University interview — done 2026-11-01: on the lent scans (loans returned): q116–118 (the Seven Special Qualities restates the Mercury and Venus clause as one mansion, 'smaller than above', no 135), 243 (the verdict's count is the white-minus-black sum of the five cells above, 39 of 40 columns fit), 244 (the Spor thang is a tradition's name, no single text), 398 (the leftovers 17, 18, 19 named on the sheet), 415 (the sky dog told once under each of seven ways), 249 (KD's Chinese title 藏传历算学大全, a compilers' group, no person) answered; q250 a dead end (the interview is in no volume archive.org carries); the Sakya print is stream-only | KD vol. 3, pp. 641–642, 718–726 and its title page and colophon; the Sakya volume with the *Seven Special Qualities*; MW1KG25692 — all lent on archive.org, read with `archive_lend.py` under the owner's login | — | [58](#block-58), [30](#block-30) | month-entries.md, corpus/kd/v3-sheets.md, corpus/kd/v3-supplement.md, verdicts.md | Read RD108: open questions 116–118 (the *Seven Special Qualities*' restatement of WB's Mercury and Venus clause), 243, 244, 398 (the sheets' counts, the *Spor thang*, the sheet of seven), 415 (the supplement's sky dog), 249 and 250 (KD's Chinese title, the interview), one loan at a time; each answered, narrowed or a dead end; the loans returned. |
| <a id="rd109"></a>RD109 | WB ch. 31's open questions: the moving earth lords' places, Wangkhyi's day and hour, the rat day's rite — done 2026-11-01: q460 (the last name of each cell on vol. 1 p. 133 is the day's earth lord; ox «གཉན་ལྗང», hare «རུས་སྦལ», monkey «གཟིག་མཇུག»), 461 answered; 459, 290, 28, 29, 443, 444, 36, 433, 148 narrowed (etext and dictionaries only, no scan, no model); NOT reached and still open: 32, 35, 432, 123, 125, 404, 439–441, 445–448, 470 | WB vol. 2, pp. 185–238 (images 193–246), by eye on the page images, every page already read by eye (no model), the Gser thang reprint and WBZ beside; WB vol. 1, p. 133 (images 143); KP images 78; the model almanac and MTA for which view the almanacs follow | — | [25](#block-25) | year-month-earth-lords.md, earth-lords.md, corpus/wb/ch31-gaps.md, rahu.md | Read RD109: open questions 28, 29, 32, 35, 36, 123, 125, 148, 290, 404, 432, 433, 439–441, 443–448, 459–461 and 470, each on its page; the questions that repeat one another (28, 443, 444; 29, 445, 448; 35, 432; 36, 433, 470; 439, 446; 440, 447) answered once for each set: the «དུར» of the first months, Wangkhyi's animal day and hour, the sun's ox, the hal khyi's mothers and sons, the rat day's «ཡོ» and «སྦྲང་མ», KP's square; each answered, narrowed or a dead end. |
| <a id="rd110"></a>RD110 | WB ch. 30's open questions: the moment's dawn and direction, the thirteen gates, the asker's small examinations — done 2026-11-01: in two parts, on the machine text: q348 settled, 341, 345, 346, 422 (reading «ལྟས་ངན་བབས»), 342, 344, 420, 421, 426 narrowed, the sixty signs are starting points counted by the asker's years lived, the thirteen gates in no text but WB; NOT reached: 238, 239 (the notes p. 535: dawn), 347, 349, 350, 423–425, 427; the gates pp. 170–172 and the notes pp. 535–538 not read by eye | WB vol. 2, pp. 144–173 (images 152–181), by eye on the page images, every page already read by eye (no model); the notes, pp. 535–538 | — | [60](#block-60), [31](#block-31) | corpus/wb/ch30.md, corpus/wb/notes-8.md, reckonings.md | Read RD110 (read in part 2026-11-01, see its section in corpus/wb/notes-8.md: q348 settled, 341, 345, 346, 422 narrowed, UT pp. 224–226 the parallel of the «ཡར་གསུམ» counts; LEFT: 238, 239, 342–344, 347, 349, 350, 420, 421, 423–427, the thirteen gates pp. 170–172 and the notes pp. 535–538 by eye, BDRC full-text search for the gates, the sixty pairs and the 360 starting points; the dictionary clone is at ~/ai/tibetan-ocr/scratch/rd97b/tibetan-dictionary): open questions 238, 239, 341, 342, 344–348, 350 and 420–427, each on its page: the moment's dawn and its direction within the day, which mewa fights the nine squares, earth in its own place, how the thirteen gates are counted and the asker's examinations combined, whose aspects fight the landed year, the sixty pairs and the 360 starting points; the words by eye and in the dictionaries; each answered, narrowed or a dead end. |
| <a id="rd111"></a>RD111 | WB ch. 25's open questions: the illness classes, the fifth pebble's trigram, the shares, the ransoms — done 2026-11-01: by dictionary and BDRC search only, no scan: the five people are chosen by the life force's relations (359, 394 in part; two 20th-century medical manuals gloss WB p. 445), «མྱུར་སྨད» the late afternoon (362), 195, 232, 401 narrowed, 395 a dead end (the app's 05:00 stands); 226–231, 360, 361, 428 searched again with nothing new and still need the page images | WB vol. 1, pp. 417–452 (images 427–462), by eye on the page images, every page already read by eye (no model), the Gser thang reprint beside; the notes, pp. 495–497 | — | [32](#block-32), [60](#block-60) | corpus/wb/ch25.md, corpus/wb/notes-3.md, verdicts.md | Read RD111: open questions 195, 226–232, 359–362, 394, 395, 401 and 428, each on its page: «བསྔོ་རུ་མ་བཏུབ», the fifth pebble's trigram, the notes' seven, a third and two thirds, the nine classes as counts and the differences of one, two and seven, p. 425's labels, the sky year of p. 426, the time of sending the ransoms, the day of falling ill at night; each answered, narrowed or a dead end. |
| <a id="rd112"></a>RD112 | WB ch. 33's open questions: the planets' points, the Khadbon letters, the day-combinations' digits, the Black Moon's earth lords — done 2026-11-01: in two parts: first part p. 313 Jupiter and west, p. 305's meetings by name, the yogas' count, Sesame-pounding; second part q454 (Kalacakra seed-letter sites differ from the Khadbon sites), 465, 466 (p. 359's hours against animals), 467 (the sky doors by the n-th date of each decade), 468 (p. 370's invocation) answered from the etext and scan crops, 456 narrowed (Saturn is 0 in the 1996 print's bracketed numbers); not done: the cell-by-cell scan reading of pp. 335–342 and pp. 361–363, the comparison with ch. 21 and Kalacakra's own text | WB vol. 2, pp. 303–370 (img. 311–378), on the scan by eye, the Gser thang reprint beside | — | [25](#block-25) | corpus/wb/ch33.md, white-beryl-ch33.md | Read RD112 (read in part 2026-11-01: 449, 450, 451, 452, 453, 455, 457, 458 answered or narrowed, see ch33.md's RD112 section; LEFT: 454 seed letters against the Khadbon list, 456 digits of pp. 335–342, 465 and 466 earth lords after the Black Moon and hours against animals, 467 sky doors, riders and skulls, 468 p. 370 invocations; BDRC full-text search not run; img. 368–371 missing from the I1KG12907 cache; new question: which Sanskrit yogas are WB's good «self-born three, five, seven and two, eight, six» of p. 349): open questions 449–458 and 465–468, each on its page: «ཕུང» and «ཆུ་བདག» of p. 313, the mansion verses' ransom noun, the seed letters against the Khadbon list, p. 305's four meetings, the digits of pp. 335–342, the yogas' verdict and Sesame-pounding's missing line, the order of the date's earth lords and of the hours, the sky doors, the invocations' names; each answered, narrowed or a dead end. |
| <a id="rd113"></a>RD113 | WB ch. 34 and 35's open questions: work 17's stack, the digging figure, the closing verse, the sciences' words — done 2026-11-01: by dictionaries and etexts, BDRC search not run: q472 («ཨ་འཐས» clinging to things as solid) and 504 (p. 448 reads «གུད་སྦས») answered, 477 («ཐོར་ཚུགས» the topknot) in part, 437/471 (tent and awning), 438 (mtho = 12 finger-breadths), 408, 473, 503 (ཏ་ལ in two of three witnesses), 505 narrowed or open; the scans of pp. 378, 428, 440, 450, 453 not read again | WB vol. 2, pp. 378–453 (img. 386–461), on the scan by eye, the Gser thang reprint and KP's contents list beside | — | [25](#block-25) | corpus/wb/ch34.md, corpus/wb/ch35.md | Read RD113: open questions 408, 437, 438, 471–473, 477 and 503–505, each on its page (437 and 471 are one): work 17's subjoined ra and «ཕྱར», «ཕྱ་ཐེར», the mtho at the navel, the closing verse's «ཁྱད་པར་དུ», «ཨ་འཐས» and the «དམར་ནག» kinds, «ཟོར་ཚུགས», «ཏ་ལ», «གུད», the «མཱུ་ལ་ཏནྟྲ» (from a catalogue); each answered, narrowed or a dead end. |
| <a id="rd114"></a>RD114 | WB ch. 32's open questions and the ground rite's first stroke — done 2026-11-01: q419 («འཇུག་ཕོད» = viṣṭi), 418 (Padma dkar po gives WB p. 281's square, the serpent walks one cell per house-day; the day's mansion plays no part), 469 (the east line's «དང་» is the first place cut short) answered; 482, 483 («ཡ་ཐེམ་སྦས» in three etexts), 463 confirmed, 462 and 464 unchanged; scans of pp. 282, 283, 294 not read again; Padma dkar po (BDRC MW10736 vol. 13) is not yet in sources.yaml | WB vol. 2, pp. 282–294 (img. 290–302), on the scan by eye; BSB pp. 435–436; Sādhuputra's rite (SDP pp. 238–241) | — | [25](#block-25) | corpus/wb/ch32.md, corpus/bsb/bsb.md, corpus/sdp/sdp.md | Read RD114: open questions 418, 419, 462–464, 469, 482 and 483, each on its page: the outer door table's numerals and the side its places are counted from, Lto-'phye's ninth part, «བུད་མེད་བུས་པ» and «ཁྱི་སྤྱང་བྲང་ཤུལ», the serpent's place for the first stroke by mansion or season, «འཇུག་ཕོད»; each answered, narrowed or a dead end. |
| <a id="rd115"></a>RD115 | WB ch. 20–21's open questions: the older system's 440, the circuits WB rejects, the luck's mewa, the la; the *Deathless Elixir*'s half stages — done 2026-11-01: q21 (no subset of the seven counts sums to 440; 442 without the 20 is the verse's head count), 73/139 (p. 257 lists the rejected circuits and gives one trigram per year), 242 («གསུམ» the number three), 193 narrowed, 194 a doubt (the scan too small); NOT reached: 80 (the two la, vol. 1 p. 253 and vol. 2 p. 330) and 234 (the luck's mewa, p. 255 and the Moonbeams) | WB vol. 1, pp. 248–258 (img. 258–268), on the scan by eye; WB vol. 2, p. 330; Mipham's *Deathless Elixir* (I1KG8550, img. 798–809), on the scan; the *Moonbeams* f. 8b–9a | — | — | year-of-life.md, reckonings.md, health.md, verdicts.md | Read RD115: open questions 21, 73, 80, 139, 193, 194, 234 and 242, each on its page: the older system's sum, the mother's trigram by a year or by the leap at ten, the circuits rejected on p. 257, the luck's mewa, the two la, the third topic split in four, the best of the six bad stages laid half and the faint letters of img. 809; Mipham decides where WB is silent; each answered, narrowed or a dead end. |
| <a id="rd116"></a>RD116 | Catalogue questions: the *Clear Lamp*'s commentary, the *Little Red Tiger-Head*, the deciding pebble's spelling, the sheets' date — done 2026-11-01: catalogue, search snippets and etext only: q222 answered (the Mentsikhang treasury's book 6 «rtsa 'grel nyi shu» is the commentary in twenty of WB's list), 252 answered as to the text («ལྔ་པ་འཕྲོད» is Mkhyen rab nor bu's digest, printed 1980), 240, 241, 247 («བཏང་གཅོད» the older spelling), 251 narrowed, 248 open; no page image read | BDRC's catalogue and full-text search, the Mentsikhang catalogue (MW4CZ354964), MW1GS66241; no page images unless a find needs one | — | — | verdicts.md | Read RD116: open questions 222, 240, 241, 247, 248, 251 and 252 in the catalogues: whether the *Clear Lamp*'s commentary in twenty survives and is the Mentsikhang's, what the *Little Red Tiger-Head* holds, which spelling of the deciding pebble is older and whose «སྟངས་གཅོད» is, when the sheets were drawn, «ལྔ་པ་འཕྲོད»; titles and dates from the catalogue, never from memory; each answered, narrowed or a dead end. |
| <a id="rd117"></a>RD117 | The reckoning for the dead's totals: the pebbles unaccounted for, the fourth examination's 87, the planets-and-mansions round's count, the white verdict's rites — done 2026-11-01: q65, q66, q200, q212 narrowed, none answered: the foot of p. 45 is the first examination's own count (87 / 338 / 425); nine of fifteen parts close from the verses, the tenth, twelfth and fourteenth do not; the 57 sit in the opening line's totals 1,008 / 9,363; «བསྒྲིལ་བས» is a sum of the list before it, so the fourth's 87 is not one; 1,874 and 1,828 each stand on their prints; «ཆེ་ཆུང་འབྲིང» is an unusual order, the white-small pairing is only the LTWA manuscript's; five claims filed | WB vol. 2, pp. 44–51 (img. 52–59) and the notes p. 513, 522, on the scan by eye | — | — | verdicts.md | Read RD117: open questions 65, 66, 200 and 212 on their pages: whether the twelve la pebbles with the six lamps and the leftover pebble are inside the totals 1,008 and 9,363, which pebbles make up the 87, WB's 1,874 against the notes' 1,828, and the order «གཏོ་རིམ་ཆེ་ཆུང་འབྲིང»; each counted pebble by pebble on the scan; each answered, narrowed or a dead end. |
| <a id="rd118"></a>RD118 | WB vol. 2 ch. 31 and the notes on the views of the almanacs and the earth lords against the results — done 2026-11-01: q32 narrowed (WB names a main view only for the belly-crawler; the Bhutan digest's remedies follow WB's lords whole, its month wheels are unread cursive), q41 unchanged (the notes' earth-lord and result heads stand side by side); etext and existing machine text only, no new scan reading | WB vol. 2, ch. 31 and the notes pp. 534–535 (img. 542–543), on the scan by eye | — | — | earth-lords.md | Read RD118: open questions 32 and 41: which view the almanacs follow where WB gives a course and then others, and whether the tradition sets the earth lords against the results; each answered, narrowed or a dead end. |
| <a id="rd119"></a>RD119 | The words of WB vol. 1 ch. 22: «སྐབ» or «སྒབ», «རག་མི་ལ», «དུས་ཚོད་ཚང་གི་རྩིས» — done 2026-11-01: q175 narrowed to «སྒབ» on the Zhol print (stack ས over ག; the 1996 machine reading agrees), q221 narrowed (the first phrase under a red smudge on the Zhol block), q178 and q181 narrowed (no variant on the Zhol print, still unglossed); the Taikhang lines of pp. 338, 349 not read | WB vol. 1, pp. 331, 338, 349 (img. 341, 348, 359); WB vol. 2, p. 104 (img. 112), on the scan by eye | — | — | year-of-life.md | Read RD119: open questions 175, 221, 178 and 181 on their pages, with the Zhol print where the Taikhang reproduction is not read; each answered, narrowed or a dead end. |
| <a id="rd120"></a>RD120 | The earth lords' seats: the closing remedy's «མི་ཧྲེང» and the day's *lo*; the 9th hair date — done 2026-11-01: q123 («མི་ཧྲེང་མདུང་ཐོགས» a lone man with a spear, the third screen, clear on the reprint), q125 narrowed (the reprint reads «ལོ་སྟེང» and «བྱི་ལམ»; the day's year-sign stands), q119 narrowed (the 9th is a bad hair date whatever the result's tone; ch. 33 side decides); BDRC search not run, its Chromium missing | WB vol. 2, pp. 224–225 (img. 232–233); WB vol. 1 on the hair dates, on the scan by eye | — | — | earth-lords.md | Read RD120: open questions 123, 125 and 119 on their pages: what the man *hreng* of the seats' closing remedy is, what the *lo* (ལོ) of the day is on p. 225, and whether the 9th hair date is a gain or a warning; each answered, narrowed or a dead end. |
| <a id="rd121"></a>RD121 | The year of age's five verdicts and sixteen limbs: the meadow's ornament, Sprugtön, the *Moonbeams*' five general totals — done 2026-11-01: q216 narrowed (the five verdicts of a group are likely one per aspect, in the order life force, body, power, luck, mewa), q217 narrowed (the limbs have no pebble of their own; 50+16+5+4 = 75), q225 narrowed (the Moonbeams' five totals gather the meadow's verdicts, not the household round); WB pp. 22–23 read line by line on the scan | WB vol. 2, pp. 22–23 (img. 30–31), on the scan by eye; the *Moonbeams* (DZ) f. 24a on the etext | — | — | verdicts.md | Read RD121: open questions 216, 217 and 225 on their pages: whether the meadow's ornament's five verdicts of each twenty-five are WB's, whether Sprugtön's sixteen limbs' pebbles are counted as WB's, and whether the *Moonbeams*' gathering of the year of age's five general totals is the same as WB p. 22; each answered, narrowed or a dead end. |
| <a id="rd122"></a>RD122 | The raising of the child and the marriage match: «དཱ་ཟླ», Revatī among p. 348's mansions, the match's hidden aspects — done 2026-11-01: q162 narrowed (the Zhol print reads «དྭ་ཟླ» with a subscript wa in p. 348's three lines and again on p. 349, a fixed term of the chapter; judged from Yigdzin-1 and the glyph's shape, no 6× crop), q163 narrowed (Revatī stays in both lines of p. 348; «ནག་པས» against «ནག་པ་ས» a doubt), q161 narrowed (the hidden's verse is the same on the blocks; «ཡར»/«ལྔ་སྨེ» doubtful at 2×); Zhol print by eye at 2–3×, Yigdzin-1 over img. 453–457, 464–468 | WB vol. 1, pp. 348, 357–358, on the scan by eye (Zhol print first) | — | — | year-of-life.md, marriage.md | Read RD122: open questions 161, 162 and 163 on their pages: which mewa stands for each hidden aspect of the match, what «དཱ་ཟླ» is in raising the child, and whether Revatī stands in p. 348's mansions; each answered, narrowed or a dead end. |
| <a id="rd123"></a>RD123 | The year of age's son place: a place with both colours, a son meeting, a pair or speckled — done 2026-11-01: q171, q186, q187, q192 narrowed, none closed: the notes' p. 490 counts only pure places and names no son place; the Clear Mirror and the Bhutan collection read the son's meeting speckled, the Moonbeams a half, one late digest a pair; WB's son line differs between prints (1996 and Taikhang «དཀར་ན་གཟུང་དུ་བོར», the Zhol's machine readings «དཀར་ནག་ཟུང་དུ», a doubt); Dorje's chart not re-read (no loan) | WB vol. 1, pp. 380–385 and the notes vol. 2, pp. 489–491, on the scan by eye (Zhol print first); Gyurme Dorje's chart | — | — | verdicts.md | Read RD123: open questions 171, 186, 187 and 192 on their pages: what a place with both colours counts as in the year of age, how a son's meeting is read against Dorje's chart, «བུ་ཕྲད་དཀར་ན་གཟུང་དུ་བོར», and whether the son's place is a pair or speckled; each answered, narrowed or a dead end. |
| <a id="rd124"></a>RD124 | KP's third square for Rāhu, ch. 34's «ཉེར་གཅིག་ཉེར་ལྔའི་བར», «བསྔོ་རུ་མ་བཏུབ» — done 2026-11-01: q148 narrowed (KP's third chart is the Bhutan reset's square, six of eight cells agree by eye; it is none of WB's p. 238 courses; KP's digits unread), q156 narrowed (the Zhol line reads as the 1996 print; the run reading stays likelier, not settled by the text), q195 unmoved (the Zhol print spells «བསྔོ་རུ» and «བསྔོ་མ» as the others; p. 425 has no «ཐང་ཤིང་» contrary to the claim plank-tree-bsngo-ru); by eye on crops, no model run | KP img. 78 (§7); WB vol. 2, p. 395 (img. 403); WB vol. 1, pp. 419, 423–425, on the scan by eye (Zhol print first) | — | — | kun-phan-me-long.md, verdicts.md | Read RD124: open questions 148, 156 and 195 on their pages: what the third chart beside KP's two compasses of boxes for Rāhu is, how work 14's good dates «ཉེར་གཅིག་ཉེར་ལྔའི་བར» run, and what «བསྔོ་རུ་མ་བཏུབ» says; each answered, narrowed or a dead end. |
| <a id="rd125"></a>RD125 | The trigram's three rites and fourteen or fifteen remedies; the in-laws' «གནས་སྐྱུག» and «ཤིད་དུར» — done 2026-11-01: q214 narrowed (the notes' p. 517 sentence is a sum: 8 identifying plus six counted for the trigram found = 14, WB's own number; «བཅོ་ལྔའོ» is the same sentence's other count, no fifteenth item named), q213 narrowed (the white, black and speckled rites are three items each by itself; nothing says the verdict's colour picks one), q211 narrowed (the root letter of the stack is untold on every available print; the 1996 etexts read «གནས་སྒྱུག», «ཤིད་དུར» likelier «ཤིང་དུར»); BDRC search not run, its Chromium missing | WB vol. 2, pp. 104–105; the notes vol. 2, pp. 517, 526 (img. 525, 534), on the scan by eye (Mipham's own print where the notes' stack is doubtful) | — | — | verdicts.md, marriage.md | Read RD125: open questions 211, 213 and 214 on their pages: whether the in-laws' words read «གནས་སྐྱུག» or «གནས་སྒྱུག», «ཤིད་དུར» or «ཤིང་དུར», whether the white, speckled and black trigram have three rites, and whether the notes count fourteen or fifteen remedies; each answered, narrowed or a dead end. |
| <a id="rd126"></a>RD126 | The year of age's obstacles: the seven-red's two readings, the secret obstacle's «མ་སྤར», «ཁ་སྦྲང» and «ངོ་ཐེག» — done 2026-11-01: q173 narrowed (the Zhol block reads «ཁ་སྨྲང» clearly, an account, not «ཁ་སྦྲང»; «ངོ་ཐོག» or «ངོ་ཐེག» unsettled at 4×), q91 narrowed (the Zhol block has «མ་སྤར་ལི» for the son and «མ་སྤར་ཁམ» for the daughter, no gloss of «མ»), q81 narrowed (p. 403 closes «གཏར་སྲེག་འཛེམ»; p. 405's head «ནག་སྐྱེས་བདུན་དམར་བབས་པ་ན» is on the block; no rule between the two lists); Zhol print by eye on 2–4× crops, Yigdzin-1 over img. 498–540 | WB vol. 1, pp. 381, 390, 403, 405, on the scan by eye (Zhol print first) | — | — | year-of-life.md | Read RD126: open questions 81, 91 and 173 on their pages: when a person born under the two-black mewa reads the seven-red in either of its two ways, what «མ་སྤར» says in the secret obstacle, and what «ཁ་སྦྲང» and «ངོ་ཐེག» are; each answered, narrowed or a dead end. |
| <a id="rd127"></a>RD127 | The *Moonbeams*' half place, its «དཀར་གསུམ་དཀར་གཉིས་སོགས་སུ་བོར», the reading of a half verdict, the general and the particular verdict — done 2026-11-01: q188 narrowed (a half is one value on the scale of six; the verse gives no rule for how it counts), q189 narrowed (the plainer reading is a grade of the scale, undecided), q190 a dead end for the Moonbeams (f. 22b only refers elsewhere), q224 narrowed (general, particular and a third, special; none defined); DZ ff. 21b–22b, 26b–27a by eye at half size, no model run | The *Moonbeams* (DZ) ff. 21b–22b and 26b–27a (img. 046–047 and the folios after), on the scan by eye and the etext | — | — | verdicts.md | Read RD127: open questions 188, 189, 190 and 224 on their folios: what the half place of the *Moonbeams*' year-of-age verdict is, what «དཀར་གསུམ་དཀར་གཉིས་སོགས་སུ་བོར» does, whether a half verdict has a reading of its own (f. 22b), and what the general and the particular verdict are (ff. 26b–27a); each answered, narrowed or a dead end. |
| <a id="rd128"></a>RD128 | The birth read whole, the words left open: «སྐབ» or «སྒབ», the horse in «དར་གུད་ཏ་ལ», «བེ་ལུག», «རག་མི་ལ» and «དུས་ཚོད་ཚང་གི་རྩིས» — done 2026-11-03: q175 narrowed (the 1996 stack on p. 331 is p. 330's «སྒབ» shape at 20×, the scan cannot tell ཀ from ག; the Taikhang spur may be ཀ, unsettled), q176 answered («ཏ» is a slip of the 1996 typesetting for «རྟ»), q177 narrowed (the 1996 «བེ» is clear, the Zhol «བུ» a variant; the Taikhang by Yigdzin-1 reads «བེ་ལུགས་རུ»; no gloss), q178 a dead end for the gloss («རག་མི་ལ» clear, no variant), q181 narrowed (the line break is the typesetter's; the meaning as RD119); 1996 scans by eye at 5–20×, Taikhang by Yigdzin-1 and by eye on pp. 331 and 334, one agy crop | WB vol. 1, ch. 22, pp. 331, 334, 338, 349 (img. 341, 344, 348, 359), on the scan by eye (Zhol print first) | — | — | year-of-life.md | Read RD128: open questions 175, 176, 177, 178 and 181 on their pages: the spelling of p. 331's «བོང་བུ་སྐབ», whether «ཏ» on p. 334 is the horse, what «བེ་ལུག» and «རག་མི་ལ» are, and which reckoning p. 349's «དུས་ཚོད་ཚང་གི་རྩིས» names; each answered, narrowed or a dead end. |
| <a id="rd129"></a>RD129 | Ch. 26's board and the harsh years: «ཁོན་སར», «མི་ཚན», «ཀླུང་གི་དུར་ཕུགས», «ལོ་རྟ» and «སྣང་པར་བཤད» — done 2026-11-03: q205 narrowed (the Zhol blocks read «ཀླུངས་ཀྱི་དུར་ཕུགས»; the «དུར་ལུགས» variant came from the Zhol etext's OCR, not a print), q203 and q204 answered for the Zhol print (it reads «བོན» and «མི་ཆེན» where the 1996 print and the Gser thang have «ཁོན» and «མི་ཚན»), q206 narrowed («ལོ་རྟ» is one word across a line break; «སྣང་པར་བཤད» reads «སྣད་པར་བཤད» on the Zhol, ང or ད not told by eye); Zhol print img. 435, 513, 628 by eye at 3–7×, Yigdzin-1 and MITRA | WB vol. 1, ch. 26, p. 472 (img. 482); pp. 335 (img. 345) and 391 (img. 401), on the scan by eye (Zhol print first) | — | — | year-of-life.md | Read RD129: open questions 203, 204, 205 and 206 on their pages: what stands between the learned man and the minister on ch. 26's board, what «མི་ཚན» is, the luck's tomb's «ཕུགས», and the harsh years' «ལོ་རྟ» and «སྣང་པར་བཤད»; each answered, narrowed or a dead end. |
| <a id="rd130"></a>RD130 | The notes' worked table and the hand reckoning: the marks and first row of p. 475, the learned secretary Ratna, the collection of deep points, the hand reckoning's «ལུ» — done 2026-11-03: q101 narrowed (p. 538 reads «ལཱུ་བཞིན» with a long-vowel sign on both prints, not «ལུ»; no gloss), q198 narrowed (one mark with a cross; a filled dot on the reprint, an open circle on Mipham's print; which is white is not stated), q199 answered (the reprint's blot; Mipham's print reads «སོ་སོའི་རང་རྡེལ»), q201 narrowed («རཏྣ» on Mipham's print, a blot on the reprint), q202 a dead end for the work (a promised separate writing on matters the notes leave out); Gser thang reprint, 1996 print and Mipham's p. 475 table by eye, Yigdzin-1 over four images | WB vol. 2, the notes, pp. 475 (img. 483) and 538 (img. 546), on the scan by eye | — | — | verdicts.md | Read RD130: open questions 101, 198, 199, 201 and 202 on their pages: the two marks and the first row of p. 475's table, who the learned secretary Ratna is, what the collection of deep points is, and the hand reckoning's «ལུ»; each answered, narrowed or a dead end. |
| <a id="rd131"></a>RD131 | The fever's words and the third and two thirds: «རྙོངས» and «ཉ་བཞི» on p. 435, the third and two thirds of p. 438, «སྐབས་གསུམ་བཞིར་བགོས» on p. 249 — done 2026-11-03: q228 narrowed (both Lhasa blocks carve «གསུམ་ཆ་སུམ་གཉིས» where the 1996 print has «སུམ་ཆ»; in the notes' example «སུམ་ཆ» is a rough share), q231 unsettled («ཉ་བཞིན» has a wa-zur foot under ཉ or ཞ, agreeing with the 1996 etext's «ཞྭ»; «རྙོངས» is «སྙོངས» to Yigdzin-1), q242 narrowed (no «པ» after «གསུམ» on either block; which three topics is told by none); Zhol and Taikhang pp. 435, 438, 249 by Yigdzin-1 and by eye, the notes p. 497 on the typeset reprint | WB vol. 1, pp. 435 (img. 445), 438 (img. 448), 249 (img. 259); the notes, vol. 2, p. 497, on the scan by eye (Zhol print first) | — | — | health.md | Read RD131: open questions 228, 231 and 242 on their pages: how p. 438's "a third and two thirds" count, what the fever's «རྙོངས» and «ཉ་བཞི» are on p. 435, and whether the third topic of the hiding of the root was to be divided in three or four; each answered, narrowed or a dead end. |
| <a id="rd132"></a>RD132 | The hand reckoning's «ལཱུ» on Mipham's own print, and «ཕ་ཡི་དྭ་ཟླས་མ་འཆི» in the notes — done 2026-11-03: q580 answered as to the print (the word is on img. 744, not 743, and again in the colophon on img. 745; Yigdzin-1 reads «ལཱུ», ཱུ against ླུ not told on the 1-bit bitmap; no gloss), q236 answered on a second print (the 1996 print has the same line break; the negative before «འཆི» is «མི», so «མ» is the mother); Mipham's print img. 743–745 and the 1996 print img. 496 by eye, Yigdzin-1 over four images | Mipham's own print (I2029) img. 743 (p. 538) and the notes, vol. 2, p. 488 (img. 496) on the scan by eye | — | — | verdicts.md | Read RD132: open questions 580 and 236: whether Mipham's own print writes «ལཱུ་བཞིན» or «ལུ་བཞིན» on the hand reckoning, and what «ཕ་ཡི་དྭ་ཟླས་མ་འཆི། བུ་ཡིས་གཉིས་ཀ་འཆི» says on p. 488; each answered, narrowed or a dead end. |
| <a id="rd133"></a>RD133 | The marriage match: «སྐར་འབྲས་སྤྱི་སྡོམ», a match of mixed kinds, the karma reckoning over it, «གནས་སྐྱུག» or «གནས་སྒྱུག» and «ཤིད་དུར» or «ཤིང་དུར» — done 2026-11-03: q577 answered (the root verse of p. 362 stands at the end of I5877 img. 490 and the head of img. 491), q356 and q211's first part narrowed (the Taikhang stack after «གནས» leans to «སྐྱུག», against the typeset etext's «སྒྱུག»), q208, q209 and q210 no new witness, «ཤིད་དུར»/«ཤིང་དུར» untouched; Taikhang img. 487–491 by Yigdzin-1 and the verse leaf by eye at 6–8× | WB vol. 1, ch. 23, pp. 351 (img. 361), 355; the notes, vol. 2, pp. 524, 526 (img. 532, 534), on the scan by eye (Zhol print first) | — | — | marriage.md | Read RD133: open questions 208, 209, 210 and 211 on their pages: what «སྐར་འབྲས་སྤྱི་སྡོམ» names, how the four aspects fall when the kinds are mixed, whether the notes' «ལས་རྩིས» is the karma reckoning over the match, and the spellings «གནས་སྐྱུག»/«གནས་སྒྱུག» and «ཤིད་དུར»/«ཤིང་དུར»; each answered, narrowed or a dead end. |
| <a id="rd134"></a>RD134 | The Taikhang reproduction beside the Zhol: p. 331's «སྒབ»/«སྐབ», p. 334's «བེ་ལུགས»/«བེ་ལུག་ས», p. 391's «སྣང»/«སྣད», p. 472's «མི་ཆེན»/«མི་ཚན» and «བོན»/«ཁོན» — done 2026-11-03: q582 narrowed (the stack on p. 331 keeps a lower-left spur on the Taikhang, ཀ or ག not told), q583 narrowed (by eye the Taikhang has one syllable «བེ་ལུགས་རུ», no tsheg between ག and ས), q584 narrowed (Yigdzin-1 reads «སྣང་བར་བཤད» on the Taikhang, with the 1996 print and against the Zhol machines' «སྣད་པར»; ང or ད not told by eye), q585 narrowed (Yigdzin-1 gives «མི་ཆེན» and «བོན» on the Taikhang as on the Zhol, so they are the Lhasa blocks' reading, not an editor's mending of one copy); Taikhang img. 446, 450, 530, 645 by Yigdzin-1 and by eye at 4–10× | WB vol. 1 on the Taikhang reproduction (I5877; image = Zhol image + 17): pp. 331 (img. 446), 334 (img. 450), 391 (img. 530), 472 (img. 645), on the scan by eye at 4–8× | — | — | year-of-life.md | Read RD134: open questions 582, 583, 584 and 585 on the Taikhang's own pages, by eye line by line: the stack on p. 331, the syllable after «ལུག» on p. 334, the letter after སྣ on p. 391, and the two names on ch. 26's board on p. 472; each answered, narrowed or a dead end. |
| <a id="rd135"></a>RD135 | The dead's reckoning, the day and its food: Dwa's food on p. 130, the thirteen who may not be offered food, the dog day's former and next life — done 2026-11-03: q279 answered by eye on the Taikhang (the former life is «ཁྱི་གུ», a puppy; the next-life line is carved alike on the bird and dog days), q262 narrowed (both prints carry a subjoined ya under the fifth stack of «བྱ་ཤ་ཇུ་རྩེ་[སྐྱུར/སྒྱུར]་རྩིབ་གཡོན»; ཀ or ག undecided, «ཇུ་རྩེ» unexplained), q276 narrowed (the Zhol names only the one who choked; no sum of the verse's lists gives thirteen); Zhol img. 724–729 and 815–820 by Yigdzin-1 and by eye, Taikhang img. 110 and 199 by eye; the 1996 images not machine-read (no slot free) | WB vol. 2, ch. 29, pp. 68–72 (img. 76–80) and 129–132 (img. 137–140), on the scan by eye (Zhol print first) | — | — | reckonings.md | Read RD135: open questions 262, 276 and 279 on their pages (question files q0262, q0276, q0279): what each asks, each answered, narrowed or a dead end. |
| <a id="rd136"></a>RD136 | The dead's reckoning, the la board and the death day: the upper, lower and middle pebbles, «སྐྱུར», «ལྷོག་པ» and «ཁྲ་བོ་བློ་ཐུབ», how the death day's planet's state is found, the months of the day's-animal entries — done 2026-11-03: q270 narrowed (WB p. 52 states the pebbles' meaning: upper empty the male line, lower empty the female line's «རྨང», cut at the waist the kin split in two or three; which of the twelve places are upper, lower or middle is unsaid), q273 narrowed («ཁྲ་བོ་བློ་ཐུབ» is WB's name for the mewa's copper vessel, «ལྷོག་པ» an illness, «སྐྱུར» likelier sour), q282 narrowed (WB has two rules for a planet's state, ch. 29 names neither; Mercury has no gyod and Jupiter no gud on all four prints), q285 narrowed (the month is that of the life's cutting, not the opposite animal's; no rule found); Zhol img. 686–718, Taikhang img. 79–102 by Yigdzin-1, MITRA on the reprint images, BDRC full-text search | WB vol. 2, ch. 29, pp. 51–58 (img. 59–66) and 63–65 (img. 71–73), on the scan by eye (Zhol print first) | — | — | reckonings.md | Read RD136: open questions 270, 273, 282 and 285 on their pages (question files q0270, q0273, q0282, q0285): what each asks, each answered, narrowed or a dead end. |
| <a id="rd137"></a>RD137 | The dead's reckoning, the grid and the pegs: the five parts of each direction on the grid of the sun and moon, the two pegs on the nine-red and none on the eight-white, the mansions «སྦ་སྨད», «ལག་སོར» and «སྤོ» on p. 122 — done 2026-11-03: q289 narrowed (p. 96 carries no figure for the grid, the wording is the same in all prints; «ཤིང» may be the verse's linking particle), q292 narrowed (both pegs stand on the nine-red in the Zhol print, the reprint and the 1996 print, so a slip would predate all three), q296 narrowed (the Zhol machine reading gives «སྦོ་སྨད» for the snake and «སྦོལ» for the dragon, stacks not separated; identification stays on the dictionary claims); reprint img. 102, 128, 133 by eye, Zhol img. 764–776, 803–813 by Yigdzin-1 | WB vol. 2, ch. 29, pp. 96–97 (img. 104–105), 122 (img. 130) and 126–128 (img. 134–136), on the scan by eye (Zhol print first) | — | — | reckonings.md | Read RD137: open questions 289, 292 and 296 on their pages (question files q0289, q0292, q0296): what each asks, each answered, narrowed or a dead end. |
| <a id="rd138"></a>RD138 | The thirty and thirteen circles and the check order: whose element starts them, whose year serves for the son and bride and the points the notes pass over, whether the check order is the order of the eight fighting pebbles — done 2026-11-03: q259 narrowed (WB p. 387 names the start's element «བརྩི་བྱའི་ལོ་ཁམས», the year-element of the one reckoned, which matches the notes' «ལོ་རྟ་དང་ཁམས་མཐུན» for the thirty; still an inference for ch. 26; the bare «བརྩི་བྱའི་ཁམས» in the illness reckoning takes a different element in each examination), q303 and q306 no change (nothing says whose year serves the thirteenth point; no sentence joins the board's eight results to the eight pebbles by number); Zhol img. 602–607 by Yigdzin-1, the notes on the reprint img. 538–539 and p. 387 by eye | WB vol. 1, ch. 26, pp. 453–458 (img. 463–468) and vol. 2, p. 3 (img. 11) and the notes pp. 532–533 (img. 540–541), on the scan by eye (Zhol print first), the etext for searching; no model run unless a slot is free | — | — | year-of-life.md | Read RD138: open questions 259, 303 and 306 on their pages (question files q0259, q0303, q0306): what each asks, each answered, narrowed or a dead end. |
| <a id="rd139"></a>RD139 | Ch. 28's seasons and hours: the seasons against the Phugpa months, the fifty water-hours and the fourth system, the doors of sky and earth and the black days — done 2026-11-03: q321 confirmed (the three spring dates read alike in the notes and the Zhol verse of p. 26; RD99's answer stands), q327 narrowed (the Zhol print has «ལོངས་པའི་ཉིན» where the 1996 edition and the reprint have «ལངས», the sun reaching the fiftieth water-hour; the fourth system still not found), q330 narrowed (on the reprint «མ» in «སྤར་ཁ་མ» and «བུ» in «ཁོན་དང་བུ» are clear, so the reading-slip explanation is not shown by the scan); Zhol img. 662, 678, 685 and reprint img. 048, 543 by eye, no model run | WB vol. 2, ch. 28, pp. 26 (img. 34), 37 (img. 45), 42 (img. 50) and the notes p. 537 (img. 545), on the scan by eye (Zhol print first), the etext for searching | — | — | almanac-page.md | Read RD139: open questions 321, 327 and 330 on their pages (question files q0321, q0327, q0330): what each asks, each answered, narrowed or a dead end. |
| <a id="rd140"></a>RD140 | The dead's reckoning, the corpse and the gates: «རྫ་མོ» in the sounding, the eight places that open death, «སྤྲ་སྒོ» and «རྨེ་བདུན» in the corpse's facing, the thirteen gates' fifth and sixth, the marks' line of p. 170, «ཁ་སྨྲང» on p. 166 — done 2026-11-03: q349 answered («ཁ་སྨྲང་བཏགས» read on p. 166 itself: an account attached to the element of the pebble's place), q311 narrowed (the reprint's letter in the ninth place is «ཛ་མོ», the 1996 etext «རྫ་མོ», Yigdzin-1 «རྔ་མོ»; no being named), q315 narrowed (the five lines of p. 90 place all twelve animals; khen, gin and zon share an element with dwa, khon and zin, pairs inferred), q337 narrowed («རྨེ་བདུན» is the seven stars of the Great Bear, not the mewa; «སྤྲ་སྒོ» unexplained), q342 narrowed (gate 5's «ལྔ» counts five years up; in gates 6, 10, 11, 13 it follows an animal, unexplained), q346 narrowed (no hour is lost; the earth hour's hair is «བནྡེ» in Yigdzin-1 and the etext, a blot on the reprint); Gser thang reprint img. 096–177 by eye against Yigdzin-1 and the 1996 etext, Zhol pages located through its etext | WB vol. 2, ch. 29, pp. 86–100 (img. 94–108), 166–171 (img. 174–179) and the notes pp. 504–512, on the scan by eye (Zhol print first), the etext for searching | — | — | reckonings.md | Read RD140: open questions 311, 315, 337, 342, 346 and 349 on their pages (question files q0311, q0315, q0337, q0342, q0346, q0349): what each asks, each answered, narrowed or a dead end. |
| <a id="rd141"></a>RD141 | The tail of the year and the thirteenth's rites: «ནམ་མཇུག» and the bird «འོལ་བ» in ch. 28, «ཤི་སྐབ» or «ཤི་སྒབ» throughout the thirteenth, the rites gzhob sel, gung phur, sha ri bhyo, sha ru, ma tram and ur mnan — done 2026-11-03: a thin reading: q324 already answered by RD99 (nothing added), q333 held with doubt (the Taikhang stack at the two places of p. 104 looks like ག, the heading blotted; five places not placed), q299 the ur mnan wheel's first row of spoke seeds legible on the Taikhang (img. 172), seven of eight ཧྲཱི-type; no question answered; Taikhang img. 160 and 172 by eye, corpus and Zhol etext, no model run | WB vol. 2, ch. 28, pp. 27–30 (img. 35–38) and ch. 29, pp. 104–114 (img. 112–122), on the scan by eye (Zhol print first), the etext for searching | — | — | almanac-page.md | Read RD141: open questions 299, 324 and 333 on their pages (question files q0299, q0324, q0333): what each asks, each answered, narrowed or a dead end. |
| <a id="rd142"></a>RD142 | The dead's la and the year's names: the la's wandering of the snake and horse, the rat and pig, «ཚད་ཟླ་མག་སྐུད», «ན་ཟླ་སྟོན་མོ», «ཞུར/འུར་ཞོགས» and «ལྕོང་མོའི་ཛ་མས» — done 2026-11-03: q363 narrowed (counted by each animal's own place on the compass the rat and pig lie north and the snake and horse south, and all four la go to the opposite half, so the verse needs no misprint; a likely reason, not stated), q366 answered in meaning (the age-mates' feast, «ཚད་ཟླ» for «ཚང་ཟླ» by Zhechen Gyaltsab's verse, «འུར་ཞོགས» a dictionary headword, «ལྕོང་མོའི་རྔ་མས» the tadpole's tail; two letters, p. 9 l. 6 and p. 17 l. 26, unsettled); Zhol img. 638 and 649 at 3–9×, the 1996 etext | WB vol. 2, pp. 9 (img. 17), 16 (img. 24) and 17 (img. 25), on the scan by eye (Zhol print first), the etext for searching | — | — | almanac-page.md | Read RD142: open questions 363 and 366 on their pages (question files q0363, q0366): what each asks, each answered, narrowed or a dead end. |
| <a id="rd143"></a>RD143 | The notes on the karma reckoning and the gold reckoning: «རྒྱ་ཁང» or «གྱོ་ཁང», which findings make an aspect bad, «སྲོག་མ་མ་གུད་པ» in the five doors of bad luck, a white enemy pebble — done 2026-11-03: q352 narrowed (the stack is «གྱོ» on the reprint and the 1996 print, three prints with Mipham's; Yigdzin-1's «རྒྱ» is its slip; what «གྱོ་ཁང» is stays unexplained), q393 narrowed (the doubled «མ་མ» stands on three prints, so it is Mipham's spelling, not an editor's dittography), q355 and q396 no new evidence (the notes name no colour test; the line is the same on three prints); reprint img. 484, 489, 532, 544 and the 1996 print by eye and Yigdzin-1, Mipham's print img. 640–641 and 744 | WB vol. 2, the notes, pp. 478 (img. 486), 483 (img. 491), 526 (img. 534) and 538 (img. 546), on the scan by eye (the Gser thang reprint I3CN5029 and Mipham's own print I2029 beside), the etext for searching | — | — | verdicts.md | Read RD143: open questions 352, 355, 393 and 396 on their pages (question files q0352, q0355, q0393, q0396): what each asks, each answered, narrowed or a dead end. |
| <a id="rd144"></a>RD144 | The *Moonbeams*' la board and plank-tree: the root text's count for the la board (the *Moonbeams*' or LT's), the plank-tree's fifteenth and sixteenth heads «ལྔ་ཐུན» and «དྲུག་འབྲས», the *Moonbeams*' readings 28 and 29 exchanged — done 2026-11-03: q371 answered (the notes' root text for the la board is WB vol. 2, p. 45 with 12 places; the Moonbeams follows it; LT copies WB vol. 1, p. 260 word for word), q374 narrowed («ལྔ་ཐུན» and «དྲུག་འབྲས» confirmed on img. 054 and read as the fifth and sixth places below the sky year per Mipham's water-snake example; whether «མར» is earlier or later years open), q377 narrowed (the exchange of readings 28 and 29 reconfirmed; LT copies WB's pair verses; nothing decides slip or teaching); Moonbeams img. 033, 054, 057 and WB vol. 1 and 2 pages by eye, no model run | The *Moonbeams* (DZ) img. 029–034 and 052–057, LT pp. 26–28, WB vol. 1 pp. 453–454 and vol. 2 pp. 499–500, on the scan by eye and the etext | — | — | verdicts.md | Read RD144: open questions 371, 374 and 377 on their pages (question files q0371, q0374, q0377): what each asks, each answered, narrowed or a dead end. |
| <a id="rd145"></a>RD145 | The moment's examinations: which mewa fights the nine squares of the three mewa joined, where earth stands in one's own place, how the thirteen gates are counted, how the small examinations of the asker are combined, and two rites of the day's twelve for the dead — done 2026-11-03: q423 answered as to the text (wealth, beer, food, the sick one's life and the organs agree with the readings built), q420 narrowed (the fighter of the nine squares is the power-mewa four down from the moment's mewa per Mipham's notes, not the year's mewa; may change the build, T2.28), q421 narrowed (earth is named only among the results, no person seated on it), q422 partly settled («རྒྱ་མའི་ཐག་པ» is a balance image, p. 171 reads «ལྟས་ངན་བབས», the «ལྔ་ནས» of gates 6, 10–13 open), q404 no new sense (the lines are on p. 224; first syllable «སྦྲང»); reprint img. 171–177 and 230–231 by Yigdzin-1 and the 1996 etext, named lines by eye | WB vol. 2, ch. 31, pp. 165–171 (img. 173–179) and ch. 32, pp. 224–225 (img. 232–233), on the scan by eye (the Gser thang reprint I3CN5029, image = page + 6, beside the Zhol print), the etext for searching | — | — | reckonings.md | Read RD145: open questions 404, 420, 421, 422 and 423 on their pages (question files q0404, q0420, q0421, q0422, q0423): what each asks, each answered, narrowed or a dead end. |
| <a id="rd146"></a>RD146 | Words of the works and rites: the departure's «ཁྱད་པར་དམིགས་ཀྱིས་བསལ་བ», KP's «འགྲོ་བདེ་གཉིས» and the screening of a bad planet-mansion meeting, the nose rite «སྣ་ཚོགས་བྱ» or «སྣ་ཆོག་བྱ», «འཇུག་ཕོད» in Sādhuputra's ground rite — done 2026-11-03: q416 answered (the nose rite «སྣ་ཆོག་བྱ» is meant; WB p. 377 has the line in full, DZ's scan really has «ཚོགས», a variant), q409 narrowed («ཁྱད་པར་དམིགས་ཀྱིས་བསལ་བ» is the commentators' idiom for "singled out in particular"; the departure is seventeen checks WB ranks none of; no text says such a lord leads over the general good), q413 narrowed (the clause is KP's own; two readings of the grammar; «འགྲོ་བདེ་གཉིས» not found), q419 narrowed («འཇུག་ཕོད» confirmed by BDRC's other Tengyur copy, the last of the Sarvadurgati's time-gods; the viṣṭi identification stays the dictionary's alone); Yigdzin-1 over Zhol, KP, DZ and SDP images, by eye on WB pp. 375 and 377 and DZ img. 045, BDRC full-text search | WB vol. 1 pp. 373–375 and vol. 2 p. 376; KP img. 14; the *Moonbeams* (DZ) img. 045; Sādhuputra (SDP) pp. 238–241, on the scan by eye and the etext (cached scans only; no archive.org loan) | — | — | weighing.md | Read RD146: open questions 409, 413, 416 and 419 on their pages (question files q0409, q0413, q0416, q0419): what each asks, each answered, narrowed or a dead end. |
| <a id="rd147"></a>RD147 | The *Vaiḍūrya* replies (YS): the old text of the servants' fine start, «ཡི་བམ» and «འབྱུང་ཆུང» in reply 152, the line on the moment's trigram (year or month), «བཙས་པ་སྲོག་རླུང» in question 134 — done 2026-11-03: a partial reading, no question answered: q399 «བཙས་པ་སྲོག་རླུང» shows no break on the Taikhang img. 99 (one word, the life-wind), q390 the first letter of «སྤར་ཁ» still not told from «སྦར་ཁ», q383 the reply names only «རྩ་བ» (RD104), q386 not re-read letter by letter (RD104 stands); the letter-by-letter reading of img. 497–498 and 513–514 at 3× was not made; Taikhang I5875 img. 99, 497–498, 513–514 and I5876 img. 16–17 at 1×, no model run | YS img. 497–498, 513–514, 099 and vol. 2 img. 016–017, with WB vol. 2 p. 497, on the scan by eye (cached scans only) and the etext | — | — | reckonings.md | Read RD147: open questions 383, 386, 390 and 399 on their pages (question files q0383, q0386, q0390, q0399): what each asks, each answered, narrowed or a dead end. |
| <a id="rd148"></a>RD148 | The *Vaiḍūrya* replies at 3×: the old text of the servants' fine start (reply near img. 497–498) and «ཡི་བམ» and «འབྱུང་ཆུང» in reply 152 (img. 513–514), letter by letter, as RD147 could not — done 2026-11-03: a partial reading: q386 narrowed as to the letters (reply 152 reads «ཡི་བམ» on img. 514 l. 3, ya with gigu and ba over ma, not «ལི་ཁམ»; the sense rests on the sentence repeating the question's «ལི་ཁམ»), q383 unchanged (the old text is named only as «རྩ་བ»); img. 497, the rest of img. 498 and img. 513 not read letter by letter; hand-cut crops at 1–3×, no model run | YS (Taikhang I5875) img. 497–498 and 513–514, cut by hand in line crops at 3× or more (the two-tier pages defeat `scans.py line`), and the etext | — | — | reckonings.md | Read RD148: open questions 383 and 386 (question files q0383, q0386), letter by letter at 3×; each answered, narrowed or a dead end. |
| <a id="rd149"></a>RD149 | The Taikhang's pressing-down wheel and run of deaths: the eight seed syllables on the first spoke row (img. 172) and the five places of «ཤི་སྒབ» or «ཤི་སྐབ» not yet placed — done 2026-11-03: a partial reading: q593 narrowed (on the Taikhang img. 172 the eight seeds are one syllable repeated, seven ཧྲཱི-like, the first without the dot; the fifth has no subjoined ར and is most like ཧཱ, a doubt), q594 unchanged (no stack of the five places read by eye; Yigdzin-1 reads «ཤི་སྒབ» everywhere, which is no evidence; places located on img. 161, 162, 165, 166); Taikhang img. 172 at 3–8×, Yigdzin-1 over img. 160–166 | The Taikhang (I5878) img. 172 (folio 162) and the leaves of pp. 105, 106 and 108 (Zhol image minus 618), in crops at 4–8× | — | — | almanac-page.md | Read RD149: open questions 593 and 594 (question files q0593, q0594); each answered, narrowed or a dead end. |
| <a id="rd150"></a>RD150 | The five stacks of «ཤི་སྒབ» or «ཤི་སྐབ» on the Taikhang, by eye — done 2026-11-06: q594 leans to ག at all six places, not settled | The Taikhang (I5878) img. 161 (p. 105 l. 15), 162 (p. 106 ll. 2 and 15), 165 (p. 108 l. 11) and 166 (p. 108 ll. 24 and 26), the ས-stack in crops at 4–8× | — | — | almanac-page.md | Read RD150: open question 594 (question file q0594) at those five places, by eye; answered, narrowed or a dead end. |
| <a id="rd151"></a>RD151 | The Vaiḍūrya replies: «ཡི་བམ» in reply 152 and the first syllable of «[?]ཁམ་མི་དགོས་ཞེ་ན» in reply 140 — done 2026-11-06: q603 and q604 narrowed, ya against la undecidable in this hand | YS (Taikhang I5875) img. 514 l. 3 and img. 498 l. 2, in hand-cut crops at 3–8×, beside the Zhol print and the etext | — | — | reckonings.md | Read RD151: open questions 603 and 604 (question files q0603, q0604) letter by letter; each answered, narrowed or a dead end. Read once 2026-11-06 (by eye, 1.6–6×): both narrowed, neither answered; ya against la is undecidable in this hand, so a certain ya and la from one hand, or a second print, is needed. |
| <a id="rd152"></a>RD152 | The Vaiḍūrya reply 140's first syllable, found from the clause «དེ་ལྟར་བསལ་ན་དཀའ་བ་མེད་པར་རྟོགས་པས» — done 2026-11-03: q604 narrowed (img. 498 has six full-width cursive lines, not two tiers, so RD104's line numbering does not carry over; the clause «དེ་ལྟར་བསལ་ན་དཀའ་བ་མེད་པར་རྟོགས་པས [?]ཁམ་མི་དགོས་ཞེ་ན» is found at x about 4,700–5,500, y about 480–580; the syllable before «ཁམ» is one stack with a flat ra-like head and a long right descender, ba, ga or da not told at 6×); hand-cut crops, no model run | YS (Taikhang I5875) img. 498, hand-cut crops at 3–8× (two tiers of lines; find the clause at low zoom first) | — | — | reckonings.md | Read RD152: open question 604 (question file q0604): the first syllable of «[?]ཁམ་མི་དགོས་ཞེ་ན» in reply 140; answered, narrowed or a dead end. |
| <a id="rd153"></a>RD153 | The other five stacks of «ཤི་སྒབ» or «ཤི་སྐབ» on the Taikhang, found by the words around them — done 2026-11-06: q594 three places ག with doubt, img. 166's right-hand place a possible ཀ (differs from RD150's plain box), img. 165 not located; a sharper print is wanted | The Taikhang (I5878) img. 162 (two places), 165 and 166 (two places), the ས-stack in crops at 4–8×; RD150's section in almanac-page.md lists the surrounding words and approximate positions (the scan has 5 lines, Yigdzin-1's files 6) | — | — | almanac-page.md | Read RD153: open question 594 (question file q0594) at the five places RD150 did not read; answered, narrowed or a dead end. |
| <a id="rd154"></a>RD154 | The la board's «རྨང» and the copper vessel's blue mewa line: «རྡེལ་སྨད་སྟོངས་ན་མོ་བརྒྱུད་རྨང» on p. 52, «སྤོས་གྲི་རྒོད» and «གླང་ཐབས» on p. 55 — done 2026-11-03: q596 narrowed («སྤོས་གྲི» stands in WB's own verse in a list of harms to the body, illness, nailing, stabbing, burning, so a harm beside the illness, not a rite; «གླང་ཐབས» has 811 hits, unsettled), q595 open (the phrase «མོ་བརྒྱུད་རྨང» occurs only in WB's Gser thang etext; «རྨང» as foundation is an inference); BDRC full-text search only, the Dge ldan commentary and the scans not read | WB vol. 2, ch. 29, pp. 52 and 55 (img. 60 and 63), Mipham's note p. 502 and the Dge ldan *Rdel 'grem dpag bsam ljon shing* (MW21984_22CC1A) etext, by BDRC full-text search and the etexts first, the scan by eye after | — | — | reckonings.md | Read RD154: open questions 595 and 596 (question files q0595, q0596): what each asks, each answered, narrowed or a dead end. |
| <a id="rd155"></a>RD155 | The order of the thirteenth and fourteenth pairs on the Zhol blocks' p. 453: the friend's horse and sheep, the enemy's bird and monkey — done 2026-11-06: q602 answered, no print of the three keeps the Moonbeams' order (q651 asks for others) | WB vol. 1 on the Zhol print (I1KG1710) img. 602–603 (p. 453), in hand-cut crops at 3–8×, the Taikhang (I5877, Zhol image + 17) as second witness | — | — | verdicts.md | Read RD155: open question 602 (question file q0602): in which order the Zhol blocks list the thirteenth and fourteenth pairs, against the 1996 print and the *Moonbeams*; answered, narrowed or a dead end. |
| <a id="rd156"></a>RD156 | Ch. 32 on the scan: the stretch of WB vol. 2 pp. 241–296 that only the etext (34 pages) and the machine reading (19) cover, 2 pages by eye (`kb.py coverage WB`, 2026-11-03) — done 2026-11-05: ch. 32 pp. 276–294 line by line on the reprint at 1.15× and p. 241 l. 1, the thirteen machine passages raised to scan (the Zhol print only at the questioned lines; pp. 290–292 hand-typed and to be spot-checked, RD170): lines Yigdzin-1 lost on pp. 277, 286, 290–292 restored, p. 281's first half added, rmang through pp. 283–284, the p. 280 table a woodcut of Lto-'phye, summaries corrected (water mill and bellows, not loom and spinning); q463, q464, q483, q549 and q608 answered, q469 narrowed; five claims, three corrected; pp. 242–275 and 295–296 not reached, RD170 | WB vol. 2, ch. 32, the thirteen passages of `corpus/wb/ch32.md` still marked machine (pp. 276–294, img. 282–300 of the reprint) and p. 241 l. 1, on the scans by eye (Zhol print first, reprint beside), then pp. 242–275 where only the etext holds them | — | — | corpus/wb/ch32.md | Read RD156: every machine passage of `corpus/wb/ch32.md` checked line by line against the scan and its `*Read:*` line raised to scan where every syllable is confirmed, the English corrected; the doubtful syllables recorded; q463, q464, q469 and q608 answered, narrowed or a dead end. |
| <a id="rd157"></a>RD157 | Ch. 33 on the scan, RD80's pages: pp. 303–307, 334–336, 341–347, 349–350 — done 2026-11-05: ch. 33 pp. 303–307, 334–336, 341–347, 349–350 on the scan, all nine passages raised to scan with the English in full: the way of p. 303 is the Phug «ཁུ་དབོན», uncle and nephew, not «ཁ་དབོན»; q455, q456 and q458 settled (the 294 cells of pp. 341–342 read on both prints and set against the verses, one difference, Saturn's Pūrvabhādrapadā ༡༤ in the verse, ༢༤ in the table); q457 corrected (the eighteenth yoga is dpa' bo), q454 narrowed; five claims, four corrected | WB vol. 2, ch. 33, the machine passages of `corpus/wb/ch33.md` for those pages (img. = page + 6 on the reprint), on the scans by eye (Zhol print first, reprint beside) | — | — | corpus/wb/ch33.md | Read RD157: every machine passage of those pages checked line by line against the scan and its `*Read:*` line raised to scan where every syllable is confirmed, the English corrected from summary to full; the doubtful syllables recorded; the open questions on those pages answered, narrowed or a dead end. |
| <a id="rd158"></a>RD158 | Ch. 33's last pages and ch. 34 on the scan: pp. 360–363, 366–370, 378–384, 387, 407–411, 415–416, 428 — read in part 2026-11-05, stopped at the owner's word: pp. 369–370 raised (wb.2:p369, p370), the text under pp. 360–361 found to be pp. 358–359 and moved, the lords after the Black Moon in order, q468 answered, q465 narrowed; left: wb.2:p358-359, p360-361 (three words, the picture labels), p362-363, p366-368, p369b, all of ch. 34 and the Zhol print for every page — second pass 2026-11-06 (Yigdzin-1 and MITRA over the ch. 34 reprint pages, compared by script): p428c raised to scan, four readings corrected (p. 379 «༦༥», p. 380 «ལྷག་པར(༤)», p. 387 «བརྐོའོ། །དཔེར»); q472 answered; the other ch. 34 passages and all of ch. 33's pp. 358–369 still machine, MITRA not run on ch. 33; flags with neither reader agreeing (101 syllables) unread | WB vol. 2, the machine passages of `corpus/wb/ch33.md` (pp. 360–370) and `corpus/wb/ch34.md`, on the scans by eye (Zhol print first, reprint beside) | — | — | corpus/wb/ch34.md | Read RD158: every machine passage of those pages checked line by line against the scan and its `*Read:*` line raised to scan where every syllable is confirmed, the English corrected from summary to full; q472 and q473 and the other open questions on those pages answered, narrowed or a dead end. |
| <a id="rd159"></a>RD159 | Ch. 33 on the scan, the mansions' results: pp. 313–330 — pp. 313–319 read 2026-11-05 on the reprint at 1.4×: seven passages raised, five held for a named word, «རྗོན» for «རྔོན» on both prints, q449–q451 closed, q452 narrowed; second part the same day, stopped at the owner's word: pp. 319–330 read on the reprint at 1.5×, four passages raised, nine held for the one stack «བསྐྱུར»/«བསྒྱུར» (q645), six kept for a second look at img. 325–328, p. 329's note read whole, q453 answered; left: those passages, wb.2:p329-330's last two lines and the Zhol print for the whole stretch — 2026-11-06 third part: only a census of q645's closing stack (Yigdzin-1 reads «བསྐྱུར» 14 times and «བསྒྱུར» 11 times at 25 places of the reprint, inconsistent for the same formula; no evidence); no passage raised; a next run crops each ransom ending by eye from a whole-page view of the Zhol print at 5× — fourth part 2026-11-06 raised nothing: the stack of q645 is a blot on the reprint and too small on the Zhol folio; the row waits on a cleaner print of pp. 313–330 | WB vol. 2, ch. 33, the machine passages of `corpus/wb/ch33.md` for pp. 313–330 (img. 319–336 of the reprint), on the scans by eye (Zhol print first, reprint beside); the topic files' scan readings of those pages (`kb.py passage`) first, so that a line already read is taken over, not read again | — | — | corpus/wb/ch33.md | Read RD159: every machine passage of pp. 313–330 checked against the scan and its `*Read:*` line raised to scan where every syllable is confirmed; q449, q450, q451, q452, q453 and the other open questions on those pages answered, narrowed or a dead end. |
| <a id="rd160"></a>RD160 | The prayer wheel's direction: which way a wheel turns, and why, in published Tibetan sources — done 2026-11-05: the wheel's direction found in print: Glag bla Sönam Chödrup (printed pp. 442–443, by eye) has a wheel turned with the way its text is laid out read forward and turned against it read from the tail, a fault and no benefit, and names the Dharma circuit and the Bon circuit side by side; Milarepa's songs do the Dharma circuit from the right and refuse the reversed one; three benefits texts forbid turning wrongly without naming a side; counter-clockwise belongs to named rites and classes (mother tantras, Cakrasaṃvara and fierce works), and no passage gives one rule for all mantras; Ladner and the text it translates not reached | BDRC etexts by full-text search (`bdrc_search.py`, `locate.py`) for the texts on the benefits of the mani wheel and their rules of turning; Ladner, *The Wheel of Great Compassion*, and the Tibetan text it translates, where a copy can be reached; no scan needs the card until a passage is found, then its scan by eye | — | — | prayer-wheel.md | Read RD160: find a published Tibetan passage on the direction a prayer wheel turns and on any wheel or mantra turned the other way (M2, ROADMAP *Direction*; block 65's caution): the passage in script from the scan with its English, or a dead end naming what was searched; no Bon text is a source, and the Bon counter-clockwise custom is recorded only if a Buddhist text names it. |
| <a id="rd161"></a>RD161 | Ch. 29's unexplained words, by the other texts: a word hunt — done 2026-11-05: ch. 29's unexplained words hunted outside WB, with a new witness, a prose digest of WB's dead reckoning in the Bhutan collection (I1KG11905, from about printed p. 479): q277, q284, q291, q293, q298, q310, q320, q334 and q338 answered (spud pa = bellows, rmang po = childless, pha gu = pha khu, «འཆོལ་བ», «ལྔ་ལ་རྩུབ»), q275, q278, q287 and q312 answered in the main, q288, q290 and q296 narrowed | BDRC full-text search (`bdrc_search.py`), the etexts (`locate.py`) and Steinert's 64 dictionaries, for the words of q275, q277, q278, q284, q287, q288, q290, q291, q293, q296, q298, q310, q312, q320, q334 and q338; each find checked on its scan by eye; the earlier readings' dead ends in PLAN.md read first, so no search is repeated | — | — | word-hunts.md | Read RD161: each question's word sought outside WB (parallels in the Mdzod, the *Moonbeams*, the manuals, the death rites, medical works; no Bon text); each answered, narrowed or a dead end, with the search recorded. |
| <a id="rd162"></a>RD162 | The words of ch. 31–34 left open, by the other texts: a word hunt — done 2026-11-06 on etext only (SY's and the Drigung scans are not served): q440, q447 answered; q439, q446, q436 narrowed; q432 not advanced | BDRC full-text search (`bdrc_search.py`), the etexts (`locate.py`) and Steinert's 64 dictionaries, for the words of q439, q440, q446, q447, q432 and q436; each find checked on its scan by eye; the earlier readings' dead ends in PLAN.md read first | — | — | word-hunts.md | Read RD162: each question's word sought outside WB (parallels in the manuals, ritual and medical works; no Bon text); each answered, narrowed or a dead end, with the search recorded. |
| <a id="rd163"></a>RD163 | The direction gods' last questions in the old texts: 歳殺, 黄幡, 歳刑, 歳破, 天一, 金神 — done 2026-11-05: the direction gods' last questions on the NDL's scans: the eight gods stand at the head of every 具注暦, all twelve branches read 756–1422, every cell as the 簠簋内伝's table, so the 歳殺 and 黄幡 tables no longer rest on the digests alone; the 協紀辨方書 gives the 歳煞 triad rule and the 歳刑's quarter and branch as one rule; q485–q487, q494/q534, q495/q531, q498, q500 (for one almanac) and q501 answered, q489/q533, q499, q530, q532 narrowed, q493 a dead end; the paper 高島暦 of today not reached | The NDL scans and the 古事類苑 already fetched for RD92–RD94 (`kyureki.md`), by eye, and the printed almanacs where the catalogue holds them | — | [54](#block-54) | kyureki.md | Read RD163: q485, q486, q487, q489, q493, q494, q495, q498, q499, q500, q501, q530, q531, q532, q533 and q534, what each asks (question files q0485…); each answered, narrowed or a dead end; a question that repeats another named so. |
| <a id="rd164"></a>RD164 | The dead's reckoning, what no row holds: the seals, the road omens, the colophon, the strikes, the kinds of old, the tenth and twelfth examinations — done 2026-11-05: the dead's reckoning by the Bhutan digest, stopped early at the owner's word: the digest's colophon (img. 600) makes it a pupil's work of the Medical and Astrological Institute, gathered from WB and filled out from the star reckoning, date unread, so it ranks as a possibly modern reader of WB, and it has no pebble counts, la board, kinds of old or blades; q300, q309 and q512 answered from WB and the primer, q261 in part, q264, q271, q297, q313, q316, q510 narrowed, q266, q281, q567–q569 dead ends in the digest; q297's count over Zhol img. 782–806 not made | WB vol. 2, ch. 29 and the notes pp. 500–522, the etext for searching; the scan by eye only where a letter is in doubt | — | [31](#block-31) | reckonings.md | Read RD164: q261, q264, q266, q271, q281, q297, q300, q309, q313, q316, q510, q512, q567, q568 and q569, what each asks (question files q0261…); each answered, narrowed or a dead end; a question that repeats another named so. |
| <a id="rd165"></a>RD165 | Ch. 28 and ch. 30, what no row holds: the full moons, the sky-bird, the small foot, ordination's trigrams, the hand reckoning, the marks — done 2026-11-06 without a model run (no slot): q556 and q323 answered; q353, q350, q335, q345 narrowed; q325, q326, q332, q598, q599 no new text (q332 waits on a scan of p. 39 img. 47); q347 and q528 doubts; q329 and q597 dead ends | WB vol. 2, pp. 27–40 and 163–172 and the notes pp. 535–538, the etext for searching; the scan by eye only where a letter is in doubt | — | [31](#block-31), [60](#block-60) | reckonings.md | Read RD165: q323, q325, q326, q329, q332, q335, q345, q347, q350, q353, q528, q556, q597, q598 and q599, what each asks (question files q0323…); each answered, narrowed or a dead end; a question that repeats another named so. |
| <a id="rd166"></a>RD166 | The *Moonbeams* and the replies, what no row holds: the burial's eleven roots, «ར་བར», the eight pebbles, the replies' words, the half — done 2026-11-06, no model, mostly etext: q573 answered; q600 and q379 in part; q372, q376, q378, q387, q391, q394, q397, q601 narrowed (etext-only ones to be checked on a scan); q373, q384 dead ends | DZ on the scan and YS, the etexts for searching; by eye only where a letter is in doubt | — | [31](#block-31), [58](#block-58) | reckonings.md | Read RD166: q372, q373, q376, q378, q379, q384, q387, q388, q391, q392, q394, q395, q397, q400, q401, q521, q573, q574, q575, q600 and q601, what each asks (question files q0372…); each answered, narrowed or a dead end; a question that repeats another named so. |
| <a id="rd167"></a>RD167 | The notes, what no row holds: the fighting years, the 360 starting points, the nine classes' differences, the doors of bad luck, the worked table — done 2026-11-06 but for the typeset check: q426, q425, q428, q429 narrowed, q522 and q586 half answered, q424, q427, q555, q557, q581, q589 open | WB vol. 2, the notes pp. 484–538, on Mipham's own print where it differs; the etext for searching | — | [60](#block-60), [58](#block-58) | corpus/wb/notes-8.md | Read RD167: q424, q425, q426, q427, q428, q429, q522, q555, q557, q581, q586 and q589, what each asks (question files q0424…); each answered, narrowed or a dead end; a question that repeats another named so. |
| <a id="rd168"></a>RD168 | The catalogue's questions: which print, which copy, which author — done 2026-11-06, no model: q546, q607 answered; q526, q537, q554 narrowed; q524, q527, q535, q536 nothing found (scans or catalogues not served) | BDRC's catalogue and full-text search, no scan unless a passage is found | — | — | word-hunts.md | Read RD168: q524, q526, q527, q535, q536, q537, q546, q554 and q607, what each asks (question files q0524…); each answered, narrowed or a dead end; a question that repeats another named so. |
| <a id="rd169"></a>RD169 | Ch. 31–32's words and counts, what no row holds: p. 249's topics, p. 257's count, the older system's sum, the outer door, the lintel — done 2026-11-07 (q483, q541, q543 answered; q540, q542, q548 narrowed; q539, q547, q559, q560 dead ends) — read 2026-11-06 in part, no model: q539, q543, q548, q542 narrowed; q540, q541, q547 unchanged; p. 257 and p. 436 not read on a scan, the ch. 12 colophon not found, the root book MW2PD17476_B43210 not opened (next); second run 2026-11-06: q541 settled to the two syllables «ནང་སྐོར», q543 very likely the red-black wheels (both colophons on the scan), the root book unreadable by eye; third run 2026-11-07: q540 narrowed (p. 257 lists the variant among «དགག་བྱའི་གནས», places to be refuted), q548's «དོ» read clearly on the reprint (sense unsaid), vol. 1 p. 257 is ch. 21 (corpus/wb/ch21.md); the row is done but for q540's sense and q547 | WB vol. 2, pp. 249–294, on the scan by eye (Zhol print first) | — | [25](#block-25) | corpus/wb/ch32.md | Read RD169: q483, q539, q540, q541, q542, q543, q547, q548, q549, q559 and q560, what each asks (question files q0483…); each answered, narrowed or a dead end; a question that repeats another named so. |
| <a id="rd170"></a>RD170 | Ch. 32 on the scan, what RD156 left: pp. 242–275 and 295–296, the woodcut's edge labels on p. 280, a check of pp. 290–292 at 3× — read 2026-11-06 in part, no model (no slot): p. 242's list of thirty-seven grounds (etext), p. 291's stanza heads (first four read, «བུ་ཕོ་དྭ» at 5×), p. 280's east-edge labels tentatively; pp. 243–275 and 295–296 not read on the scan, the row stays open for a run with a free slot; second run 2026-11-06: Yigdzin-1 read the reprint's pp. 243–275 and 295–296, aligned with the 1996 etext (about 99 in 100 syllables agree), 35 passages written as machine state with a gist; no page read syllable by syllable on the scan, p. 280's west edge and caption and the last four stanza heads of pp. 290–292 unchecked; q547's dead end stands on the machine text too (the only direction word, p. 273's river and sun and moon turning right, is about ground) | WB vol. 2, ch. 32, pp. 242–275 and 295–296 where only the etext holds them, on the scan by eye (Zhol print first); p. 280's four edges on Zhol img. 1020 in crops; pp. 290–292 (reprint img. 296–298) at 3× | — | — | corpus/wb/ch32.md | Read RD170: the etext-only pages and the woodcut's labels on the scan; pp. 290–292, typed by hand in RD156 and read at 1.15×, checked against the print at 3×; q547 and the open questions on those pages answered, narrowed or a dead end. |
| <a id="rd171"></a>RD171 | The wheel texts' and the Kun-gsal-dpe's words, what no row holds: the Longchenpa quotation, the wheel with rims, the fathers' and mothers' wheels, the parasol, the palm turned into a road (etext only, no card) — done 2026-11-06, etext and BDRC search only: q545, q616 answered; q544, q613, q614, q615, q617, q618 narrowed (the Kangyur/Tengyur catalogues not searched for the Nges 'byed bla ma) | BDRC full-text search (`bdrc_search.py`), the etexts (`locate.py`) and Steinert's 64 dictionaries; the prayer-wheel texts already in `sources/prayer-wheel.md` (Glag bla, Gangtok print, 'Jam dbyangs don yod, the Karma Chagme catalogue entry); KP's etext for q544 | — | — | word-hunts.md | Read RD171: q544, q545, q613, q614, q615, q616, q617, q618, what each asks (question files q0280…, the older ones in `open-questions.md`); each word or text sought outside the one place it stands (parallels in the wheel manuals and the benefits texts; no Bon text as a source for the sense); each answered, narrowed or a dead end; a question that repeats another named so. |
| <a id="rd172"></a>RD172 | The Japanese almanac's last questions, what no row holds: 大將軍's width and outings, the paper 高島暦, the 簠簋内伝's copies and date, the 假名暦略註's writer, the 歳破's livestock (NDL pages and catalogues, no card) — done 2026-11-06 on NDL pages by eye, no model: q490, q628 answered (for the 1937 book and the NDL's note); q488, q623, q624, q627, q629 narrowed; q625, q626 dead ends; today's paper 高島暦 not seen | The NDL digital collection and its catalogue, the 古事類苑 and the 協紀辨方書 already fetched for RD92–RD94 and RD163 (`kyureki.md`), read in the browser; the printed 高島暦 as far as a library record or a shop page shows it; no GPU reading | — | — | kyureki.md | Read RD172: q488, q490, q623, q624, q625, q626, q627, q628, q629, what each asks (question files q0280…, the older ones in `open-questions.md`); each answer from a dated, citable print or record; a question the digitised pages do not settle is a dead end named so; each answered, narrowed or a dead end; a question that repeats another named so. |
| <a id="rd173"></a>RD173 | The catalogue's questions, the second round: Dr. Chenagtsang's books, the Serthang edition, the *Spor thang*, the Chinese title of KD, the Bhutan digest's writer, the Drigung verse work, the Clear Lamp's root, the sheets' date (BDRC records and full-text search, no card) — done 2026-11-06: q655 answered (writer and source; the date narrowed); q654, q642, q656, q244, q249, q62, q86 narrowed; q538 needs the owner or KD's title leaf | BDRC's catalogue records and full-text search (`bdrc_search.py`, `locate.py`), publishers' pages for q62; the etexts of the Drigung verse work (MW3KG218) and the Clear Lamp (I3CN5028) for q654–q656 | — | — | word-hunts.md | Read RD173: q62, q86, q244, q249, q538, q642, q654, q655, q656, what each asks (question files q0280…, the older ones in `open-questions.md`); which print, which copy, which author, which date; the record's own wording quoted; a question that needs the owner's interview (q538) is marked so, not guessed; each answered, narrowed or a dead end; a question that repeats another named so. |
| <a id="rd174"></a>RD174 | Ch. 24 and ch. 26 on the birth and the year of age, what no row holds: the speckled predictive pebble, the fierce planets' meeting, the birth against the year of age, Revatī, the match's results, the clan element's months (etext and commentaries, no card) — done 2026-11-06, etext only: q566, q551 answered; q514, q523, q565 (= q237), q579 narrowed; q180, q172, q207 confirmed as before | WB vol. 1, pp. 335, 340, 348–351, 381–385 in the etexts; Mipham's notes (vol. 2, p. 489 and after), the *Moonbeams*, LT and the other manuals in the etexts (`locate.py`); BDRC full-text search for Sanskrit-yoga names (q551) | — | — | year-of-life.md | Read RD174: q172, q180, q207, q237, q514, q523, q551, q565, q566, q579, what each asks (question files q0280…, the older ones in `open-questions.md`); each question put to the manuals and commentaries that treat the same passage; which rule stands and by whose word; a question no source touches is a dead end named so; each answered, narrowed or a dead end; a question that repeats another named so. |
| <a id="rd175"></a>RD175 | Vol. 1's birth and illness pages on the Zhol and Taikhang scans: letters in doubt on pp. 331–435 (needs the card) — done 2026-11-08, ended: a fifth run cut the lines again and found nothing the earlier four had not; what is left depends on the one heavily inked Zhol impression and stays open as q519, q587, q588 — read 2026-11-06 in part, by eye and Yigdzin-1: q562, q518, q520 (= q183) answered; q572, q519, q606, q578 narrowed; q587, q588 left (the Zhol blocks are heavily inked; a better impression is needed); the Gser thang reprint and Sakya prints not consulted | WB vol. 1, pp. 331, 334, 342, 377, 380–381, 435 on the Zhol print (I1KG1710 img. 326, 435, 498–499), the Taikhang (I5877 about img. 446–462, 515) and the Gser thang reprint, by eye in crops (Yigdzin-1 first, then the eye) | — | — | corpus/wb/ch24.md | Read RD175: q183, q518, q519, q520, q562, q572, q578, q587, q588, q606, what each asks (question files q0280…, the older ones in `open-questions.md`); each doubtful syllable read on every print that has it, the crops kept; a blot that stays a blot is named a dead end with the image number; each answered, narrowed or a dead end; a question that repeats another named so. |
| <a id="rd176"></a>RD176 | Ch. 33's mansion verses on the scan, the letters left in doubt: «རྗོན», Bharaṇī's and Revatī's minor stars, Viśākhā's clan, Jyeṣṭhā's «ཆུ་ཀ་ལེན», Aśvinī's numeral, and the Zhol's wavy marks on p. 517 (needs the card) — read 2026-11-06 in part, by eye, no model: q631, q646, q647, q649, q650 narrowed (none confirmed on both prints, no `*Read:*` raised); q609, q630, q639, q648 not reached (q648: Zhol img. 1076 line 2 has «རུས་མཚན» located); second part: q646 answered, q609, q648, q649 narrowed, q639 and q630 dead ends (BDRC and SY etext found for the first time) | WB vol. 2, pp. 305, 314, 317, 322–323, 328–329 on the Zhol print (WBZ img. 1066 and its neighbours) and the Gser thang reprint at 3×–5× by eye; the notes' p. 517 on the Zhol print (img. 782–806, mark counts) | — | — | corpus/wb/ch33.md | Read RD176: q609, q630, q631, q639, q646, q647, q648, q649, q650, what each asks (question files q0280…, the older ones in `open-questions.md`); each syllable or numeral read on both prints and the 1996 etext, the crops kept; the senses taken from the mansion-verse lists the pool already holds; each answered, narrowed or a dead end; a question that repeats another named so. |
| <a id="rd177"></a>RD177 | The meanings left open in ch. 33 and the notes: the departure's singled-out lords, the Mara days' images, the six fang-holders, «ཆུབ», the illness examinations' elements, the secret obstacle, the hand reckoning (etext and commentaries, no card) — done 2026-11-06 on etext only: q605, q610, q611 answered; q592, q513, q632, q640, q576 narrowed; q652, q653, q612 dead ends or repeats; q571 not worked (scan checks wanted: UT p. 234, 302–304, KD vol. 3 pp. 276–278 and 752, the notes' pp. 480 and 531, WB p. 390) | WB vol. 1, pp. 317, 375, 390, 395, 417–419 and vol. 2, pp. 305–306, 335–336, 341–342, 501, 517, 538–539 in the etexts; Mipham's notes, the *Moonbeams*, LT, UT and the Bhutan digest in the etexts (`locate.py`), Steinert's dictionaries | — | — | corpus/wb/ch33.md | Read RD177: q513, q571, q576, q592, q605, q610, q611, q612, q632, q640, q652, q653, what each asks (question files q0280…, the older ones in `open-questions.md`); each question put to the commentaries on the same passage; the rule found with its page; a question no commentary treats a dead end named so; each answered, narrowed or a dead end; a question that repeats another named so. |
| <a id="rd178"></a>RD178 | Ch. 27's first leaves on the scan, the letters in doubt: the black pebble's verse, the minister's set, the feat of the body, the nine-eye's sky years, the stack «བརྗེ» or «བནྡེ», the executioners' door, p. 448's praise (needs the card) — second run 2026-11-07, by eye on the Zhol with Taikhang and reprint beside: q475 «གྲོས» a slip of the 1996 setting (probable), q474/q507 the stack reads «བརྗེ» not «བནྡེ» (8×), q506/q508 «ས་ཡོས» plain sa, «མེ་སྟག» e or i unsettled, q504 «ཧྲུལ» or «སྦས» unsettled, q480 stays open; q477, q481, q509, q511 not reached (Zhol img. for p. 440 and pp. 53–54 not located); begun 2026-11-06: Yigdzin-1 over Zhol img. 629–645 (pp. 3–22), q480 narrowed slightly, nothing read by eye; q474, q475, q477, q481, q504, q506–q509, q511 not reached; Zhol img. 646–664 machine-read in scratch if the job finished; next: search them for «མི་སྟག» and «ས་ཡོས» (q506, q508) | WB vol. 2, pp. 3, 9, 16, 23–25, 53–54, 440, 448 on the Zhol print (img. 11–60 and 446–454), the Taikhang and the Gser thang reprint, by eye in crops at 4×–6× | — | — | corpus/wb/ch27.md | Read RD178: q474, q475, q477, q480, q481, q504, q506, q507, q508, q509, q511, what each asks (question files q0280…, the older ones in `open-questions.md`); each stack or syllable read on all three prints and set against Zhechen Gyaltsab's verse where it has one, the crops kept; a blot that stays a blot is named a dead end; each answered, narrowed or a dead end; a question that repeats another named so. |
| <a id="rd179"></a>RD179 | Ch. 27's reckonings, what no row holds: the element of the one reckoned, the meadow's ornament columns, Sprugtön's limbs, UT's chase, the supplement's sky dog, KL's moxa dates, the true place of Mercury and Venus, the Black Moon's arrival (etext and commentaries, no card) — done 2026-11-06, etext only: q550, q415 answered; q479, q529, q564, q563, q79, q117, q118 narrowed (q663–q667 filed) | WB vol. 2, pp. 3, 17, 22–23, 144, 359–364, 537 and KD vol. 3, pp. 641–642 in the etexts; UT pp. 224–226, KL p. 256, Blo gros dpal's and WB's solar rules (p. 65) in the etexts (`locate.py`) | — | — | corpus/wb/ch27.md | Read RD179: q79, q117, q118, q415, q479, q529, q550, q563, q564, what each asks (question files q0280…, the older ones in `open-questions.md`); each question put to the texts that treat the same reckoning; the rule or the figure found with its page; a question no source touches a dead end named so; each answered, narrowed or a dead end; a question that repeats another named so. |
| <a id="rd180"></a>RD180 | Vol. 2's early leaves on the Zhol and Taikhang scans: the dog day, p. 37's «ལོངས་པའི་ཉིན», the smudge on p. 104, the goat's bladder, the snake's second name, the old one, the provisions' seals (needs the card) — begun 2026-11-06, mostly etext: q561, q590, q591, q634, q635, q638 narrowed; q633 not answered; q280, q636, q637, q641 not worked; second pass: q280, q641, q636 answered; q637, q591, q633, q638 narrowed on the 1996 scans (the Zhol and Gser thang scans for the seal blocks and img. 782–783 not read) | WB vol. 2, pp. 37, 70–72, 80, 104, 106, 122, 129–132, 92 on the Zhol print (img. 45, 728–729, 778 and the provisions' leaves), the Taikhang (I5878 about img. 160), the Gser thang reprint and the search-only copy I1KG81102, by eye | — | — | corpus/wb/ch29.md | Read RD180: q280, q561, q590, q591, q633, q634, q635, q636, q637, q638, q641, what each asks (question files q0280…, the older ones in `open-questions.md`); each leaf or syllable read on every print that has it; whether a leaf is missing in the Zhol print answered from the img. numbers; blots named dead ends; each answered, narrowed or a dead end; a question that repeats another named so. |
| <a id="rd181"></a>RD181 | The manuscripts, the sheets and the Bhutan collection on the lent scans: the numbered cells, the blanks, *The Precious Key*'s teacher, KD's pebbles in the seven members' round, KP's square, the hand-drawn month wheels (needs the card and the lent scans; q558 needs a reader of the cursive) | I1LT0800 img. 370–383, I1KG12897 img. 004, 048, 147, 160, 227, 383–384, KD vol. 3 pp. 723–726 on the lent scan, KP img. 78, the Bhutan collection pp. 436–446 (img. 441–452), by eye in crops | — | — | verdicts.md | Read RD181: q254, q255, q256, q257, q525, q552, q553, q558, q570, what each asks (question files q0280…, the older ones in `open-questions.md`); each cell, blank or colour word read at the print's own size and set against WB's examinations; what the lent scan's size forbids named so; each answered, narrowed or a dead end; a question that repeats another named so. |
| <a id="rd182"></a>RD182 | Vol. 1's tables and the hal khyi on the scan: the la-knower's animals, the hearth god's side, the light-clearer's grave, the five mothers and sons, the tent at the door, Wangkhyi's «ཉི་མ་དུས་ཚོད», the ransoms' time (needs the card) — done 2026-11-06, mostly etext: q362 answered; q448, q515, q461, q470, q517 narrowed; q441 dead end with a lead; q459, q516 a lead only; scans of pp. 210, 219, 224, 51 and the lower table of p. 133 not read | WB vol. 1, pp. 133, 431–433, 443–444 and vol. 2, pp. 185, 210, 224, 236 on the Zhol print and the Gser thang reprint, by eye in crops; the tables' cells read one at a time (img. numbers from `wb/README.md`) | — | — | corpus/wb/ch28.md | Read RD182: q362, q441, q448, q459, q461, q470, q515, q516, q517, what each asks (question files q0280…, the older ones in `open-questions.md`); each cell of the tables read on the scan, the crops kept; a gloss for q441, q448 and q362 sought in the manuals; dead ends named; each answered, narrowed or a dead end; a question that repeats another named so. |
| <a id="rd183"></a>RD183 | Ch. 32's last words on the scan: the byan tshang, the Sa dga' ma, the two physicians and the nine mothers, the vase's bone, the woodcut's hand-written labels, the sky doors, the ko long and the bstan ma (needs the card; with RD170's pages) — done 2026-11-06 in part, by eye: q643, q644 answered; q619, q620, q621, q622, q466, q467, q657 narrowed (q657: Taikhang I5878 img. 402 at 8280 px awaits a GPU reader or agy); pp. 294, 361, 366–367, 370 not read on the scan; second part: q467 answered (all skulls read on the scan), q657 narrowed to the east edge's structure, p. 294's lotus line and p. 361's second and third stanzas read; p. 366, p. 370, q466's hour works not read | WB vol. 2, pp. 276, 278, 280, 287–288, 294, 360–361, 366–368, 370 on the scan (Zhol img. 282–300, 1020 and the reprint), by eye in crops at 3×–5× | — | — | corpus/wb/ch32.md | Read RD183: q466, q467, q619, q620, q621, q622, q643, q644, q657, what each asks (question files q0280…, the older ones in `open-questions.md`); each label or word read and set against the pages around it and the commentaries; q657 shares its crops with RD170's p. 280; dead ends named; each answered, narrowed or a dead end; a question that repeats another named so. |
| <a id="rd184"></a>RD184 | The sixty years' results (WB ch. 18) | WB vol. 1, ch. 18, pp. 198–212 (img. 208–222), on the scan | — | — | corpus/wb/ch18.md (new) | Read RD184: WB vol. 1 ch. 18 on the scan, the results of the sixty years from the *Dus bstan me long* and the other texts it names: each passage in Tibetan as read, with the English, the sources it cites, and what it says year by year. |
| <a id="rd185"></a>RD185 | The three kinds of day and carrying the reckoning forward (WB ch. 17 and 19) — done 2026-11-08 (ch. 17 and 19 read in part 2026-11-07, a second look 2026-11-08 on the reprint; q687–q690 filed, the small stacked figures wait on a larger scan) | WB vol. 1, ch. 17, pp. 190–197 (img. 200–207), and ch. 19, pp. 213–217 (img. 223–227), on the scan | — | — | corpus/wb/ch17.md (new) | Read RD185: WB vol. 1 ch. 17 (the three kinds of day, *zhag gsum*) and ch. 19 (carrying the reckoning forward, the wheel of the three days) on the scan, passage by passage. |
| <a id="rd186"></a>RD186 | The solstice wheel and the like (WB ch. 16) — done 2026-11-08 (ch. 16 pp. 184–189 read 2026-11-07, q676–q680 filed; p. 189 and the first two blocks of the 65-year table thin) | WB vol. 1, ch. 16, pp. 184–189 (img. 194–199), on the scan | — | — | corpus/wb/ch16.md | Read RD186: WB vol. 1 ch. 16 on the scan, passage by passage, the solstice wheel and what it names. |
| <a id="rd187"></a>RD187 | The chronology of the doctrine, the byed rtsis and grub rtsis, Rāhu and the Kar myang reckoning (WB ch. 3–5) — done 2026-11-07 in part: 15 passages machine-read (Yigdzin-1 and BDRC's etext, a sample by eye on the reprint), the ch. 3 figures corrected by the chain and the number words; no MITRA; a line-by-line reading on the Gser thang reprint (I3CN5028, img. 29–43) is open | WB vol. 1, ch. 3–5, pp. 22–36 (img. 32–46), on the scan | — | — | corpus/wb/ch03.md (new) | Read RD187: WB vol. 1 ch. 3, 4 and 5 on the scan, passage by passage: what each says, in Tibetan as read with the English, the sources it names. |
| <a id="rd188"></a>RD188 | The five planets and the dus sbyor (WB ch. 6–8) — done 2026-11-07 in part: 17 passages machine-read with Yigdzin-1 and MITRA, figures checked on the scan on p. 37 only; the tables of pp. 44–47 and 53–54 not read cell by cell | WB vol. 1, ch. 6–8, pp. 37–55 (img. 47–65), on the scan | — | — | corpus/wb/ch06.md (new) | Read RD188: WB vol. 1 ch. 6, 7 and 8 on the scan, passage by passage: the planets, the days of the planets, the exact reckoning (tshes spyod) and the dus sbyor. |
| <a id="rd189"></a>RD189 | Making new tables and keeping the old (WB ch. 10) — done 2026-11-07 in part: 9 passages machine-read with Yigdzin-1 and MITRA, the long figure strings of img. 86–87 unreadable at the 1996 scan's size; a re-read on the Gser thang reprint (I3CN5028, img. 65–73) would settle the figures | WB vol. 1, ch. 10, pp. 70–78 (img. 80–88), on the scan | — | — | corpus/wb/ch10.md (new) | Read RD189: WB vol. 1 ch. 10 on the scan, passage by passage. |
| <a id="rd190"></a>RD190 | The sixty years' year lists on the scan (WB ch. 18, pp. 206–211) — done 2026-11-07: pp. 206–211 on the Gser thang reprint with Yigdzin-1 and MITRA, flags read at 2×, the rest once at 0.6×; the Sūtra of the Secret's 60 entries and the Little Tiger-Ear's entries 1–44 entry by entry (the last 16 are on p. 212, RD194); the key is on p. 210; the reprint binds pp. 207 and 208 in the other order | WB vol. 1, ch. 18, pp. 206–211 (img. 216–221 of the 1996 print; the Gser thang reprint img. 205–210), on the scan; MITRA beforehand; the key of p. 209 first | — | — | corpus/wb/ch18.md | Read RD190: the year lists of pp. 206–211 (the Sūtra of the Secret and the Chinese Little Tiger-Ear) on the scan, entry by entry, each year with its result, the key of p. 209 settled first; raise the passages to scan. |
| <a id="rd191"></a>RD191 | The remaining pages of WB ch. 11 and 12 (pp. 86–103) — done 2026-11-08 (every page of the range now on the scan in kb.py coverage; the figures still in doubt are named in the topic file's notes and questions) — read in part 2026-11-07: 18 passages (pp. 86–103), 3 on the scan (pp. 96–98, O10e's), 15 machine (Yigdzin-1 over the reprint, crops re-read where lines dropped); no MITRA; ch. 11's code-numbers not checked digit by digit; the wheel drawings described, not transcribed | WB vol. 1, ch. 11 pp. 86–90 (img. 96–100) and ch. 12 pp. 91–103 (img. 101–113), on the scan; the gaps `kb.py coverage` lists | — | — | corpus/wb/ch12.md (new) | Read RD191: WB vol. 1 ch. 11 (from p. 86) and ch. 12 on the scan, passage by passage, the pages no earlier reading has read; the earlier readings of ch. 12 (day-letters.md, personal-mansions.md) are read first and not repeated. |
| <a id="rd192"></a>RD192 | WB ch. 13's unread pages, first half (pp. 104–132) — done 2026-11-08 (every page of the range now on the scan in kb.py coverage; the figures still in doubt are named in the topic file's notes and questions) — read in part 2026-11-07: pp. 104–109 (years 1–30 of the table of the sixty years, the sme ba rule checked on all rows), pp. 122–125 (twelve strips) and pp. 126, 128 (nine flying squares) on the scan by eye; pp. 127 and 129–132 described, machine; columns 12–14 of the table unread; the reprint's images are page + 7 (img. 111–116 and 129–139), the row's numbers fit the 1996 print | WB vol. 1, ch. 13, pp. 104–109 and 122–132 (img. 114–119 and 132–142), on the scan | — | — | corpus/wb/ch13.md (new) | Read RD192: WB vol. 1 ch. 13 on the scan, the pages `kb.py coverage` lists as unread in this range, passage by passage, the lists of names with their sources. |
| <a id="rd193"></a>RD193 | WB ch. 13's unread pages, second half (pp. 134–172) — done 2026-11-08 (every page of the range now on the scan in kb.py coverage; the figures still in doubt are named in the topic file's notes and questions) — read in part 2026-11-07: pp. 134–135 only (the eight yoginīs, Viṣṭi, the bum stong), machine state; pp. 136–172 (charts, woodcuts, grids, the model almanac's tables) unread, neither reader reads them: to be read cell by cell by eye | WB vol. 1, ch. 13, pp. 134–136, 141–163 and 165–172 (img. 144–146, 151–173, 175–182), on the scan | — | — | corpus/wb/ch13.md | Read RD193: WB vol. 1 ch. 13 on the scan, the pages `kb.py coverage` lists as unread in this range, passage by passage. |
| <a id="rd194"></a>RD194 | The rest of the Little Tiger-Ear year list on the scan (WB ch. 18, p. 212) — done 2026-11-07: p. 212 on the Gser thang reprint (Yigdzin-1, MITRA as witness, the deciding words at 3×); the Little Tiger-Ear list complete in 60 entries | WB vol. 1, ch. 18, p. 212 (img. 222 of the 1996 print; the Gser thang reprint img. 211), on the scan; MITRA beforehand | — | — | corpus/wb/ch18.md | Read RD194: p. 212 on the scan, the last 16 entries of the Little Tiger-Ear list (fire monkey to water pig) entry by entry, the nine sme ba entering the centre, and the closing note; raise the passages to scan. |
| <a id="rd195"></a>RD195 | WB ch. 13's earth-lord charts and woodcuts by eye (pp. 136–147) — read in part 2026-11-07: the layout of the 24 charts and their woodcuts described by eye, the cells unread (the reprint's face is not legible at any enlargement); needs another print | WB vol. 1, ch. 13, pp. 136–147 (Gser thang reprint I3CN5028 img. 143–154; 1996 print img. 146–157), by eye in crops; neither machine reader reads these pages | — | — | corpus/wb/ch13.md | Read RD195: the charts and woodcuts of pp. 136–147 by eye on the sharp reprint, box by box: what each chart is, its labels and entries in Tibetan as read with the English, what it is read against; unread cells named as unread, no guessing. |
| <a id="rd196"></a>RD196 | WB ch. 13's tables of the model almanac by eye (pp. 154–171) — read in part 2026-11-07: the model almanac's layout and a box's anatomy; boxes 1–5 and 11 of p. 154 and 1–5 of p. 165 digit by digit; pp. 155–164, 166 box by box and pp. 167–171 (the 1996 print only) unread | WB vol. 1, ch. 13, pp. 154–171 (Gser thang reprint img. 161–178), by eye in crops, the pages `kb.py coverage` lists as unread | — | — | corpus/wb/ch13.md | Read RD196: the model almanac's tables of pp. 154–171 by eye on the sharp reprint, the pages no earlier reading has read (RD2 read the month heads); each table's shape, its labels and a sample of cells digit by digit, with the rule for the rest; unread cells named as unread. |
| <a id="rd197"></a>RD197 | WB ch. 3's dated verses line by line on the reprint (pp. 22–28) — done 2026-11-07 in part: the dated figures of pp. 22–28 read by eye at 3–4× against the chain of reductions; q691–q694 answered (three figures of the print itself part from its chain: 2587, 1048, 278); the verse not compared syllable by syllable, all passages stay machine | WB vol. 1, ch. 3, pp. 22–28 (Gser thang reprint I3CN5028 img. 29–35, image = page + 7), by eye in crops beside RD187's machine passages | — | — | corpus/wb/ch03.md | Read RD197: the dated verses of pp. 22–28 line by line on the sharp reprint against RD187's passages, every figure digit by digit with the chain of reductions as the check; answer q691–q694 where the print decides; raise passages to scan where every syllable is confirmed. |
| <a id="rd198"></a>RD198 | The remaining gaps of WB ch. 1, 2, 9, 14 and 15 — done 2026-11-07, machine state: the gap pages of ch. 1, 2, 9, 14 and 15 (pp. 4–5, 14, 20–21, 56–57, 67–69, 179, 183) read with Yigdzin-1 on both prints (93–99% agreement), the reprint's reading quoted; no page read syllable by syllable on the scan, no MITRA | WB vol. 1, the pages `kb.py coverage` lists as unread or gaps in ch. 1 (pp. 4–5), ch. 2 (pp. 14, 20–21), ch. 9 (pp. 56–57, 67–69), ch. 14 (p. 179) and ch. 15 (p. 183), on the scan | — | — | corpus/wb/ch09.md | Read RD198: the gap pages of ch. 1, 2, 9, 14 and 15 on the scan, passage by passage; ch. 1 and 2 are new in the corpus: a topic file section for each in the files corpus/wb/ch01.md and corpus/wb/ch02.md, ch. 14 and 15 in ch14.md and ch15.md. |
| <a id="rd199"></a>RD199 | WB ch. 13's chart cells (pp. 136–147) on the 1996 print and the Zhol print — read in part 2026-11-07: the layout of the 24 charts on the 1996 and Zhol prints by eye (the Zhol has the twelve strip charts on img. 151–153 in cycle order from the rat), the cell text is BDRC's etext reading, not confirmed by eye; needs a sharper print (Taikhang) | WB vol. 1, ch. 13, pp. 136–147: the 1996 print I1KG12906 img. 146–157 and the Zhol print (I1KG1710, find the images of vol. 1 by the page numbers), by eye in crops; the reprint's face is not legible (RD195) | — | — | corpus/wb/ch13.md | Read RD199: the 24 charts of pp. 136–147 on the 1996 print and the Zhol print, cell by cell where legible: each chart's labels and entries in Tibetan as read with the English; cells not legible named as unread; no guessing. |
| <a id="rd200"></a>RD200 | The model almanac's month heads, pp. 155–171, line by line — read in part 2026-11-07: the 12th and 1st month heads (pp. 155, 157); the heads of the 2nd–11th months continue in RD201 and RD202 | WB vol. 1, ch. 13, the seven-line heads of the twelve months on pp. 155, 157, 158, 160, 162, 163, 165, 166–167, 168, 170 and 171 (Gser thang reprint I3CN5028 img. 162–173 for pp. 155–166; the 1996 print I1KG12906 img. 177–181 for pp. 167–171), by eye in crops, against RD2's reading (almanac-page.md, month-entries.md) | — | — | corpus/wb/ch13.md | Read RD200: the seven-line head of each month of the model almanac, line by line, in Tibetan as read with the English, set against RD2's reading of the same heads; what differs is named; the boxes themselves are not this reading's. |
| <a id="rd201"></a>RD201 | The model almanac's month heads, the 2nd to 6th months — read in part 2026-11-07: the 2nd head by eye at 3× (scan), the 3rd–6th heads machine (Yigdzin-1 on head crops against the scan at 1×); festivals RD2's table omits recorded; stacked figures unread | WB vol. 1, ch. 13, the seven-line heads of the 2nd–6th months on pp. 158, 160, 162, 163 and 165 (Gser thang reprint I3CN5028 img. 165, 167, 169, 170, 172), by eye in crops at 3× and Yigdzin-1 on head crops, against RD2's reading; the layout and method of RD200's section | — | — | corpus/wb/ch13.md | Read RD201: the heads of the 2nd–6th months line by line in Tibetan as read with the English, against RD2; stacked figures digit by digit at 3× or named unread. |
| <a id="rd202"></a>RD202 | The model almanac's month heads, the 7th to 11th months — read in part 2026-11-07: the 7th–10th heads machine text with a few lines by eye (the machine's «གྱོད» is «སྐྱོད» in the print), the 11th head's lines 1–4 by eye; a second pass at 3× is open | WB vol. 1, ch. 13, the seven-line heads of the 7th–11th months on pp. 166–167, 168, 170, 171 (the reprint I3CN5028 img. 173 for p. 166; the 1996 print I1KG12906 img. 177–181 for pp. 167–171) and p. 154 (img. 161), by eye in crops at 3×, against RD2's reading; the layout and method of RD200's section | — | — | corpus/wb/ch13.md | Read RD202: the heads of the 7th–11th months line by line in Tibetan as read with the English, against RD2; stacked figures digit by digit at 3× or named unread. |
| <a id="rd203"></a>RD203 | The rest of WB ch. 1, 2 and 9 (pp. 3, 6–13, 15–19, 58–66) — read in part 2026-11-07: ch. 2 pp. 15–19 and ch. 9 pp. 58–59 entered (machine; the Sanskritised names unchecked at 3×); ch. 9 pp. 60–66 (Yigdzin-1 text in the reprint's img. 67–73 not entered) and ch. 1 pp. 3, 6–13 not read | WB vol. 1, ch. 1 pp. 3 and 6–13, ch. 2 pp. 15–19, ch. 9 pp. 58–66 (Gser thang reprint I3CN5028, image = page + 7), on the scan, the pages `kb.py coverage` lists as machine or etext-only | — | — | corpus/wb/ch02.md | Read RD203: the pages named, passage by passage, Yigdzin-1 on the reprint against the 1996 etext, the deciding words and figures by eye at 3×; passages in the files of RD198 (ch01.md, ch02.md, ch09.md). |
| <a id="rd204"></a>RD204 | WB ch. 4 and 5 on the scan: pp. 29–36 raised from machine (RD187 wrote them as machine passages; the number words were the only check) | WB vol. 1, ch. 4 pp. 29–33 and ch. 5 pp. 34–36 (Gser thang reprint I3CN5028, image = page + 7), the passages of corpus/wb/ch03.md listed as machine by `kb.py coverage`, on the scan | — | — | corpus/wb/ch03.md | Read RD204: the machine passages of ch. 4 and 5 (pp. 29–36), line by line on the reprint at 2–3× beside the Yigdzin-1 text and the etext; every syllable and figure confirmed raises a passage to scan; each doubt is named. |
| <a id="rd205"></a>RD205 | WB ch. 6 and 7 on the scan: pp. 37–50 raised from machine (RD188 read them with MITRA, figures not checked) | WB vol. 1, ch. 6 pp. 37–41 and ch. 7 pp. 42–50 (Gser thang reprint I3CN5028, image = page + 7), the passages of corpus/wb/ch06.md and ch07.md that `kb.py coverage` lists as machine, on the scan | — | — | corpus/wb/ch06.md, corpus/wb/ch07.md | Read RD205: the machine passages of ch. 6 and 7 (pp. 37–50), the figures digit by digit on the reprint at 3×, the words against MITRA; passages confirmed throughout are raised to scan; doubts named. |
| <a id="rd206"></a>RD206 | WB ch. 8 and 10 on the scan: pp. 51–55 and 70–78 raised from machine (the tables of pp. 53–54 not read cell by cell) | WB vol. 1, ch. 8 pp. 51–55 and ch. 10 pp. 70–78 (Gser thang reprint I3CN5028, image = page + 7), the passages of corpus/wb/ch08.md and ch10.md that `kb.py coverage` lists as machine, on the scan | — | — | corpus/wb/ch08.md, corpus/wb/ch10.md | Read RD206: the machine passages of ch. 8 and 10, the tables of pp. 53–54 cell by cell at 3× or named unread, the prose line by line; passages confirmed throughout are raised to scan; doubts named. |
| <a id="rd207"></a>RD207 | The Svapnohana on the scan: Vibhūticandra's rule by the watch of the night, his catalogue of dream images and the remedy; Advayavajra's examination beside it — done 2026-11-09: the Svapnohana (svo:p268–271) and Advayavajra's examination (svi:p261–262) as passages with Tibetan and English, in part etext and machine only (the scan proof is RD218); the five watches and their spans (first watch within a year, second eight months, third three months, fourth half a month, dawn ten days; no calendar unit named), the images with verdicts, the remedies; no tie to a lunar day, weekday or month in either text, SVI names only three parts of the night | SVO: Derge Tengyur vol. 28 (I1344), pp. 268 l. 4 – 271 l. 4, etext VE23703_1344 (read whole in the etext 2026-11-09, q784); SVI: vol. 49 (I1365), pp. 261–262, etext VE23703_1365 | — | [S1](#s1) | corpus/svo/svo.md, corpus/svi/svi.md | Read RD207: the Svapnohana line by line on the Derge scan against its etext, every image with its verdict as a passage, the watches and their spans as a claim, the remedy as a claim; then Advayavajra's text the same way; name every place either text ties a dream to a time other than the watch of the night. |
| <a id="rd208"></a>RD208 | The Lama Gongdü's signs of good and bad dreams — done 2026-11-09: the Lama Gongdü's dream signs at their true pages (Rin chen gter mdzod vol. 42 pp. 961–971, I1KG57 img. 980–990; the row's pp. 799–810 were wrong) in eight passages (seven on the scan, the rite of pp. 961–966 machine) and eight claims: the three stages, the kind of dream decided by the part of the night (dusk habit, midnight afflictions, only the dawn dream an omen), seven kinds, about 30 good and 50 bad signs, signs that reverse, the three yogins; the lunar day governs only the rite (waxing half), no sign's verdict changes by hour or lunar day | LGD: Rin chen gter mdzod vol. 42, pp. 961–971 (6 ff.; the first plan said 799–810, wrong), etext VE1KG14_042, scans I1KG57 img. 980–990 | — | [S1](#s1) | corpus/lgd/lgd.md | Read RD208: the six folios on the scan against the etext, the good and the bad signs each a passage with the English beside it, the stages (rim pa) as a claim; note whether a sign depends on the time of night, the lunar day or the practice in hand. |
| <a id="rd209"></a>RD209 | The short dream manuals: Karma chags med's magic mirror, the method under Sakya Paṇḍita's name, Dam tshig rdo rje's instruction and method, the eighteen signs of the latter times, the ACIP method, the 'Bri gung extended method — done 2026-11-09: partly, machine or etext state: the short manuals KCM, SKP, DTD, RST, RRT and RBG (the last as a witness from the Derge etext, its own pages not reached) with 16 claims; time rules: DTD examines the last watch of a four-part night, RST three thirds with dawn alone examined, KCM a rite on the tenth day of the waxing or waning moon with 'the proper time' of the night; SKP's list has no time rule or remedy, its second text repeats the RST verses; scan proof, RBG's own pages and the KCM time phrase remain | KCM pp. 679–682 (dbu med); SKP pp. 40–55; DTD vol. 10 pp. 219–220 and the NLM manuscripts (pp. 155–158); RBG vol. 3 pp. 203–205 (etext IE0OPIBC148677); RST (etext only); RRT vol. 41 pp. 186–195 (etext IE3KG218) | — | [S1](#s1) | corpus/kcm/kcm.md, corpus/skp/skp.md, corpus/dtd/dtd.md, corpus/rbg/rbg.md, corpus/rst/rst.md, corpus/rrt/rrt.md | Read RD209: each manual whole, on its scan where there is one and in the etext where there is none (state honest), its images and verdicts as passages, its method (what is asked, when the dream counts, what is done with a bad one) as claims; a table at the end of which manual gives which time rule. |
| <a id="rd210"></a>RD210 | Dream divination through a deity: White Tārā, the goddess Śramaṇā, the Great Compassionate One and Mtsho rgyal — done 2026-11-09: partly, machine state only (Yigdzin-1, no scan proof): Śramaṇā (SRM printed pp. 386–389: a good time of the waxing half, no lunar day, any night of the rite counts, the answer graded prophecy > voice > dream, success in 3, 5 or 7 nights), White Tārā (TDR pp. 343–344: four mirrors A NU TA RA, no lunar day) and the Great Compassionate One (GNC pp. 557–558); GNC pp. 559–561 and the Mtsho rgyal text (img. 567–569) unread, left to RD217; registry pages corrected | TDR pp. 343–344; SRM vol. 6, ff. 51v–53r (etext IE0OPIAB2E5954); GNC vol. 1, ff. 61r–61v and the Mtsho rgyal text | — | [S1](#s1) | corpus/tdr/tdr.md, corpus/srm/srm.md, corpus/gnc/gnc.md | Read RD210: the rite before sleep, the dream asked for, and how the dream is read, as passages and claims; whether the dawn dream alone counts, and whether the lunar day of the rite is prescribed. |
| <a id="rd211"></a>RD211 | The Four Tantras' dream passages on the scan: ch. 7's distant signs of death, the nāga dreams, the fevers' dreams, the pregnancy dream, the poison dream — done 2026-11-09: seven Four Tantras passages on the Gser thang scan I3CN5038 (ch. 2 pregnancy, ch. 7 distant death signs, ch. 15, 23, 27 fevers and pestilence, ch. 81 spirit illness, ch. 91 the flavour) with one claim per illness (claims/st-dream-*.md); the print's pages run backwards from img. 199 to 261; remedies of the fever chapters not read | ST etext pp. 34, 44, 202, 225, 260, 362–363, 507 (image = etext page) on the scan I3CN5038; GT as witness | — | [S1](#s1) | corpus/st/st.md | Read RD211: each passage on the Gser thang scan line by line against the etext (q784 quotes them from the etext), the dream named with what it marks and what is done, as passages and one claim per illness; the chapter and its printed page for each. |
| <a id="rd212"></a>RD212 | Klong rdol bla ma's omen methods, the folk collection and the article on his dream method — done 2026-11-09: partly: KDL (pp. 235–241 on I5556, MITRA and flags settled by eye) holds Garga's omens, no dream and no watch of the night, only parts of the day for a sound; KBS (the article, pp. 68–78) carries Klong rdol's own dream rule: the night in three thirds, dusk the imprint of the last three days, midnight the display of spirits, only the dawn third examined, a dream that leaves the dreamer glad is good; nine claims; the MDP dream part (pp. 134–137) is not reachable (etext 403, scan restricted), RD221 | KDL pp. 235–240; MDP, the dream part (etext IE0OPIE0503306); KBS pp. 66–80 (facts only) | — | [S1](#s1) | corpus/kdl/kdl.md, corpus/mdp/mdp.md, corpus/kbs/kbs.md | Read RD212: Klong rdol's methods whole on the scan, the dream texts of the folk collection in the etext with their sources named where the collection names them, the article for the facts it adds in our own words; a claim for each time rule found. |
| <a id="rd213"></a>RD213 | Japan: the dream book beside the 大雑書 in the Ebara reprint, and the 初夢 in the old texts — done 2026-11-09: partly: the Ebara volume is in no online form, so its own 大雑書 and 夢占吉凶秘伝書 were not read (the NDL record gives the order of the five texts); stand-in read: the 万代大雑書 part 15 (夢はんじ, 132 articles, no date; the one calendar rule is ゆ六 夢を見て語らざる日, a month-by-branch table of days a dream is not told) and the 初夢 night's history (節分 night, then New Year's Eve, then 2 January; 宝船) from the 日本大百科全書 | EB9: the 大雑書 (its contents, any 夢合 section) and the 夢占吉凶秘伝書 (its order, its rules, any date); the 初夢 night in 『山家集』 and the Edo sources named by 日本大百科全書 *夢占* | the book, not online | [S3](#s3) | corpus/eb9/eb9.md | Read RD213: the two texts' tables of contents and every dream rule that names a date, a day of the cycle or a season, quoted in the original with the English beside it; the 初夢 night's history as claims with their sources. |
| <a id="rd214"></a>RD214 | Scheuermann's study of the Svapnohana | SCH pp. 161–180, facts only; access past Brill's human check in the owner's browser, or a library copy | RD207; access | [S1](#s1) | corpus/sch/sch.md | Read RD214 (blocked 2026-11-09: the chapter needs the owner's PDF, PLAN.md Dead ends):  what the chapter settles on the text's author, date, Sanskrit original and parallels, and on how Tibetan readers used it, in our own words; where it corrects RD207's reading, a claim of type conflict. |
| <a id="rd215"></a>RD215 | The watches of the night (ཐུན, thun): how the Tibetan sources divide the night into four, and dawn, in clock terms — done 2026-11-09: no Tibetan source read fixes the watches of the night by the clock (claims/night-watches.md): WB vol. 1 p. 101 and vol. 2 p. 403 count four watches by day and four by night (eight in all, the night's fifth beginning at sunset, equality not stated); WB vol. 1 p. 17 names a three-watch night (sunset to dawn, six times of the night, dawn the last); the Svapnohana four watches plus dawn-breaking; Mipham's notes name none; fallback: sunset to sunrise cut in four, unchecked against published studies | WB vol. 2, ch. 30, p. 162 (the twelve of night, read on the scan by RD17) and the notes, vol. 2, p. 535; vol. 1's divisions of the day where `locate.py` finds «ཐུན་བཞི», «མཚན་ཐུན», «ནམ་ཐུན», «སྲོད་ཐུན», «ཐོ་རངས»; Mipham's notes; SVO p. 268 for the text's own usage; the Indian yāma as the Sanskrit background, from the published studies only | — ; RD207 | [S2](#s2) | corpus/wb/ch30.md, corpus/svo/svo.md | Read RD215: find where WB, Mipham or the manuals divide the night into watches and name their bounds (sunset, midnight, cockcrow, dawn, or fixed double hours), each passage on the scan with the English beside it; a claim (claims/night-watches.md) giving the four watches' spans and dawn's place in clock terms, or stating that no Tibetan source fixes them and the Indian quarters of the night from sunset to sunrise are the fallback; name every text that uses a three-watch night instead. |
| <a id="rd216"></a>RD216 | The dream images gathered into one catalogue: every image read with each text's verdict, WB first, the medical ones marked — done 2026-11-09: the dream images gathered into one catalogue: 85 image claims (claims/dream-*.md) naming the texts that read each with verdict, passage id and state, WB ch. 30 first, the Four Tantras marked for a sick dreamer and above WB ch. 25, the index claims/dream-catalogue.md ending in the table of images by text (tone G/B/X/N, state S/M/E, ? where a word is unsettled) for the picker; dream-remedies (each text's remedy once); dream-unsettled-words (13 images not to offer yet); eight images read in opposite ways, kept, none chosen; KBS and RST are one witness, not two; no text sums images | The passages of RD59 (WB ch. 30, p. 144), RD207 (SVO, SVI), RD208 (LGD), RD209 (the manuals), RD211 (ST, the dreamer ill), RD212 (KDL, MDP); WB ch. 25's dreams of the sick (corpus/wb/ch25.md) | RD207, RD208, RD209, RD211; RD212, RD214 | [S2](#s2) | corpus/wb/ch30.md, corpus/svo/svo.md | Read RD216: from the read passages, one claim per dream image (claims/dream-*.md, indexed by claims/dream-catalogue.md) naming the texts that read it and each text's verdict with its passage id, WB's ch. 30 verdict first, the Four Tantras' rows marked as readings for a sick dreamer (and ST above WB ch. 25 where both read one), a bad dream's remedy per text once, and the images whose verdict rests on an unsettled word listed apart; a table at the end of the images by text, so the app's picker can be built from it without reading the corpus again. |
| <a id="rd217"></a>RD217 | The rest of the Great Compassionate One's dream text and the Mtsho rgyal text, and the scan proof of RD210 — done 2026-11-09: partly, machine state: the Great Compassionate One's dream text to its end (gnc.1:p559–561) is a clarifying rite only: a wheel drawn at the pillow with vulture flesh, mantra water drunk, recited until sleep; it reads no dream signs and prescribes no lunar day, hour or count of nights (the 14th night of the Pleiades month is the colophon's date of revelation); a new text, a mo divination, begins at the end of p. 561; the scan proof of TDR, SRM and GNC pp. 557–558 not done, RD222 | GNC vol. 1, printed pp. 559–561 (I1662 img. 567–569, image = page + 8; the Mtsho rgyal text MW21578_A09994 ff. 61v–63v); TDR I3856 img. 347–348 and SRM I3JW3517 img. 392–395 for the proof | — | [S1](#s1) | corpus/gnc/gnc.md, corpus/tdr/tdr.md, corpus/srm/srm.md | Read RD217: img. 567–569 (Yigdzin-1, then by eye), how the Mtsho rgyal text reads its dream and whether it prescribes a lunar day; then the passages of RD210 (tdr, srm, gnc) from machine to scan, flag by flag. |
| <a id="rd218"></a>RD218 | The Svapnohana and Advayavajra's examination, scan proof of RD207 | SVO I1344 img. 268–271 and SVI I1365 img. 261–262 (image = printed page; 2000-px strips, enlarge 3× or more) | — | [S1](#s1) | corpus/svo/svo.md, corpus/svi/svi.md | Read RD218 (run 2026-11-09 by machine only, passages now machine; waits for a higher-resolution scan, PLAN.md Dead ends):  Yigdzin-1 over I1344 img. 268–269 and I1365 img. 261–262, then every syllable of the four passages on the scan, the doubts of RD207 settled or left (gang la/gang ba, འོ་མ་འམ, the Gāyatrī spelling, «བླ་མོ», «འགྲོན་བུ», «བགྲོད་མིན»); bring pp. 268–269 and svi to the scan state. |
| <a id="rd219"></a>RD219 | The short dream manuals on the scan: KCM, SKP and DTD, the time phrase of KCM, RBG and RRT | KCM pp. 679–682 (img 689–692), SKP pp. 40–55, DTD vol. 10 pp. 219–220 and the NLM manuscript pp. 155–158, RRT scan I3KG732, RBG vol. 3 pp. 203–205 (restricted, see PLAN.md Dead ends) | — | [S1](#s1) | corpus/kcm/kcm.md, corpus/skp/skp.md, corpus/dtd/dtd.md, corpus/rrt/rrt.md, corpus/rbg/rbg.md | Read RD219 (run 2026-11-09: DTD's time rule read on the scan, the rest machine or etext, KCM's time phrase unread: a cursive ligature; RBG unreachable, PLAN.md Dead ends):  MITRA as witness and the disagreement sheets over the Yigdzin-1 readings of RD209, then the passages on the scan, the time phrase «བཻ་རང་གི་དུས་ཚོད» of KCM first; RRT against its scan; RBG if a route to its pages is found. |
| <a id="rd220"></a>RD220 | The Lama Gongdü's rite and its machine proof — done 2026-11-09: the rite (lgd:p961–966) on the scan: Yigdzin-1 and MITRA agree on every syllable of img. 981–985 (99–100 %), the eye pass over all six pages and the mantra lines match, so the passage is scan on that two-reader agreement; the page-opening Om on pp. 961–963 and 965 is a mark of the print, not text; the six-glyph seed string of p. 962 stays unsettled (q807: Yigdzin-1 and MITRA share only ཀྱ, ཊ, ཀ) | LGD vol. 42 pp. 961–966 (I1KG57 img. 980–985), the rite of RD208 read by Yigdzin-1 and the etext only; MITRA as witness | — | [S1](#s1) | corpus/lgd/lgd.md | Read RD220: MITRA over img. 980–985 and the disagreement sheets, the rite passage (lgd:p961-966) read syllable by syllable on the scan, the seed syllables after «Om» in the first line of p. 962. |
| <a id="rd221"></a>RD221 | Klong rdol's own dream text and the folk collection's dream part — done 2026-11-09: Klong rdol's dream text is the ACIP etext RST (MW1ALS06558E, creator Klong rdol bla ma Ngag dbang blo bzang, colophon naming him; the dream verses pp. 3–8 follow Ma gcig's Chöd collection, so he is compiler or last writer); the etext is the whole collection (dream verses, Garga's omens, men's and women's examinations), KDL is the same collection minus the dream part; rst:p8-13, p13-17, p17-20 added (etext); KBS and the etext agree in sense and differ in four places; q808 answered; MDP's dream part unreachable | Klong rdol bla ma's text on examining the omens of dreams (rmi lam gyi ltas la brtag pa byed tshul) in his gsung 'bum, to be located in BDRC; MDP printed pp. 134–137 (I4992, restricted; see PLAN.md Dead ends) | — | [S1](#s1) | corpus/kbs/kbs.md, corpus/mdp/mdp.md | Read RD221: locate Klong rdol's own dream text and read it whole, the three thirds of the night in his own words; MDP's dream part if a route to its pages is found. |
| <a id="rd222"></a>RD222 | The deity dream rites on the scan: TDR, SRM and GNC pp. 557–561 | TDR I3856 img. 347–348 (image = page + 4), SRM I3JW3517 img. 392–395 (image = page + 6; the etext is garbled), GNC I1662 img. 565–569 (image = page + 8); manuscript hands, 2173×460 and 4320×896 | — | [S1](#s1) | corpus/tdr/tdr.md, corpus/srm/srm.md, corpus/gnc/gnc.md | Read RD222 (run 2026-11-09: both machines over all 11 images, no passage moved to scan; needs line-by-line crops from the line boxes, PLAN.md Dead ends):  Yigdzin-1 then MITRA over these images (a slot, nothing else queued), the disagreement sheets, then each passage of RD210 and RD217 syllable by syllable on the scan with crops at explicit coordinates (scans.py line fails on these pages); bring tdr, srm and gnc from machine to scan. |
| <a id="rd227"></a>RD227 | The Four Tantras' dream words left unsettled, on the scan: ch. 2 «ཡང»/«དག» (p. 21), ch. 81 the nāga-illness dreams (pp. 350–351), ch. 91 the thread's mantra and «བ་ར» (p. 493) — done 2026-11-09: the Four Tantras' dream places on the Gser thang scan: ch. 81 reads ཉ་སྦྲུལ (fish, snakes), not ཉ་ལྕོལ, and its creature names are settled by the dictionaries (the row struck from dream-unsettled-words); ch. 91's mantra read letter by letter and བ་ར is cattle and goats (row struck); ch. 2 ལུས་ཡང་རྨི་ལམ་དག་ན read clearly in both prints, only དག (plural or clear) left open; ལྷས་ཚོར་དུས་ཏེ, ལྡེར་བཟོ་འགུལ and ཕྱུ unsettled, no verdict rests on them | ST etext pp. 21, 350–351, 493 (image = etext page) on the Gser thang scan I3CN5038, with the Chag-po-ri/Derge print as witness where BDRC serves one; claims/dream-unsettled-words.md | RD211 | [S1](#s1) | corpus/st/st.md | Read RD227: each of the three places on the scan line by line, crops at explicit coordinates, every doubtful syllable read by eye and set against the etext and against a second print of the Four Tantras if one is served; settle the creature names of ch. 81 or mark which stay unsettled; update the passages and the image claims (dream-sick-naga-illness-dreams, dream-sick-women-lead-away, dream-pregnant-man-seen) and strike a word from dream-unsettled-words.md only when settled. |
| <a id="rd228"></a>RD228 | Other witnesses of the Svapnohana and of the Lama Gongdü's signs, for the words the Derge scan will not give: «བགྲོད་མིན», «འགྲོན་བུ», «བླ་མོ», «བཅུ་ཡི་བར», «ག་ཡ་ཏྲི» (SVO); «རམ་རུག་སྦང་གཡེར» (LGD pp. 968–969) — done 2026-11-09: partly: the Svapnohana read in the Peking and Narthang prints and the Dpe bsdur ma etext: «བཅུ་ཡི་བར», «འགྲོན་བུ» and the Gāyatrī's spelling «ག་ཡ་ཏྲི» settled, «བགྲོད་མིན» the same in all witnesses with its sense open, and «བླ་མོ» a conflict (Peking and Narthang read «ལ་མོ»); the Lama Gongdü's goods of gossip compared across five prints, which disagree (conflict claim lgd-goods-of-gossip-prints); two further Narthang prints, the Zhe chen LGD print, SVI in Peking and the scan check of the Kazi and Sbas-yul readings left to RD231 | SVO in the Peking, Narthang and Co ne Tengyur and the Dpe bsdur ma etext (BDRC, rKTs, the Tengyur parallels of the Derge text), SVI likewise; LGD in the other prints of the Rin chen gter mdzod and the Sanskrit if a parallel is published | RD207, RD208 | [S1](#s1) | corpus/svo/svo.md, corpus/lgd/lgd.md | Read RD228: locate a second print or an etext of the Svapnohana (and of Advayavajra's examination) and of the Lama Gongdü's dream folios that the Derge scan's 8-pixel letters do not need; read the doubtful words there, name the print and its page; where two prints agree against the etext a claim, where they disagree a claim of type conflict; the doubts that stay go to PLAN.md Dead ends. |
| <a id="rd229"></a>RD229 | What the Four Tantras do with the dreams of fever and pestilence: the remedies of ch. 15, 23 and 27 beside the dream lines, and the commentary on ch. 2 «རྨི་ལམ་དག་ན» — done 2026-11-09: partly, machine state: none of the Four Tantras' ch. 15, 23 or 27 gives a remedy for the dream itself, the dream marks the unripe stage and the whole fever is ripened (four manu, three fruits with sle tres, five sle tres decoctions; light warm food; no purge or emetic then; the pestilence cham a fast and a powder of the three fruits, sle tres, tig ta, ma nu, hong len); two commentaries (Byang pa MW27496, Rin spungs pa MW27494, etext) gloss ch. 2's དག as again and again, so a frequency marker not 'clear', and ལུས་ཡང་ as a light body, settling what RD227 left open; ch. 7's grades of remedy by kind of person; bjp and rsp registered; MITRA and img. 256 not reached | ST etext pp. 202, 218–225, 260 (ST img. = etext page, printed pp. 241, 218, 183) on the Gser thang scan I3CN5038, the remedies that follow each dream line; a commentary on the Explanatory Tantra for ch. 2 and ch. 7 (Sde srid's Blue Beryl is not a Four Tantras commentary; look in the BDRC catalogue for Zur khar, Lho brag, Mkhyen rab nor bu on the Explanatory Tantra) | RD211, RD227 | [S1](#s1) | corpus/st/st.md | Read RD229: the remedies that the fever and pestilence chapters give beside their dream lines, on the scan line by line, as passages; the claims st-dream-* gain what is done for the sick dreamer; for ch. 2 «དག» and ch. 7's distant signs of death the commentary if one is served in etext or scan, named with print and page; dead ends to PLAN.md. |
| <a id="rd230"></a>RD230 | WB ch. 25's four dream lines and ch. 30's dream omen (p. 144) on the scan, the verdicts word by word — done 2026-11-09: WB ch. 25's four dream lines (vol. 1 pp. 428, 430, 431) and ch. 30 p. 144 read by eye on the Gser thang reprint: all read exactly as the machine text had them, no correction, the sick-dreamer rows moved from machine to scan; the fourth riding animal «རྟ[?]» stays an ink-filled glyph; Mipham's notes gloss none of the lines; Taikhang and Zhol prints and MITRA not reached | WB vol. 2 ch. 25 (the ox-day rough dreams, the five-yellow mewa's dreams of za zi, the dragon-year skull remedy, the pig-year ritualist bitten in the sick person's dream) and ch. 30 p. 144 on the Gser thang reprint (I3CN5029, image = page + 6) and the Zhol/Taikhang prints where served | RD59, RD216 | [S2](#s2) | corpus/wb/ch25.md, corpus/wb/ch30.md | Read RD230: each dream line on the scan at crops, every syllable by eye, the verdict of each image against dream-catalogue.md; where the machine reading differs a correction to the passage and to the image claim; Mipham's notes (vol. 2 pp. 474–541) on these lines if they gloss them. |
| <a id="rd231"></a>RD231 | The «ལ་མོ»/«བླ་མོ» of the Svapnohana in the two further Narthang prints (a first attempt on 2026-11-09 stopped before reading any of them) — done 2026-11-09: the «ལ་མོ»/«བླ་མོ» line of the Svapnohana read by eye in the two further Narthang prints (W22704 vol. 28 img. 288, the gser bris ma img. 389): both read «རྟ་ལ་མོ» with a plain la, so four prints (Peking, three Narthang) read «ལ་མོ» against the Derge etext and the Dpe bsdur ma base text «བླ་མོ»; the sense of the word stays open (q825); the other doubtful words read by machine only in these prints and agree with Peking; printed numbers 284 and 387 recorded | SVO: Narthang W22704 vol. 28 (I3279 pp. 286–289), the gser bris ma W23702 (I1PD31365 pp. 386–391); volume ids by purl.bdrc.io/resource/W….json (PLAN.md Dead ends, RD228); the scans are fetched in the shared cache | RD228 | [S1](#s1) | corpus/svo/svo.md | Read RD231: only the passage of the run «རྟ་བླ་མོ / རྟ་ལ་མོ … གཟུགས་པ… ཆུ་སྲིན་» (svo:p271b names the line) in each print, by enlargement and by eye, then every other doubtful word of RD228 in them; a claim of agreement or conflict per print; stop with the passage read, not the whole text. |
| <a id="rd232"></a>RD232 | The Lama Gongdü's goods of gossip in the Zhe chen, Palri, Kazi and Sbas-yul prints (RD228 left five readings in conflict; the first attempt of 2026-11-09 stopped at a machine reading of the Zhe chen) — done 2026-11-09: partly: the Lama Gongdü's goods of gossip read by eye in the Palri (img. 542 line 1, «རམ་རུག་སྤང་གཡེང»), Kazi (img. 559, «རམ་རུག་སྤང་གཡེར», moderate confidence) and Zhe chen (img. 310 line 6, «སྦང་གཡེར», second syllable «རུག» or «རྒྱུག» unsettled) prints: the first syllables agree, the third differs (སྤང/སྦང) and the fourth splits between ང (Palri) and ར (Kazi, Zhe chen); Sbas-yul's cursive «རྟ་ཇུག་སྦང་ཕྱེར» not settled; the Palri print gives no gloss of the goods; Palri img. 541 and 543 not read | LGD: Zhe chen W2PD17514 vol. 33 (I4PD2250 img. 303–314, hand script 2750×474, `scans.py crop` with pixel boxes, `line` fails), Palri W01CT0060 vol. 7 (I01CT0067 img. 534–545, clean typeset, line 1 of img. 542 already confirmed «རམ་རུག་སྤང་གཡེང་ལ་སོགས་མི་ཁའི་རྫས»), Kazi I6132 img. 559, Sbas-yul I1LT3208 img. 148 | RD228 | [S1](#s1) | corpus/lgd/lgd.md | Read RD232: Palri img. 541–543 whole by eye (clean print), the list of the goods of gossip as passages; then the same words in the Zhe chen, Kazi and Sbas-yul scans by crops at explicit coordinates; update lgd-goods-of-gossip-prints; what the goods are called in the Palri print is the answer to RD228's second question if the text names them. |
| <a id="rd233"></a>RD233 | The Svapnohana whole on the Peking print, whose letters are about 40 px (the Derge strips of RD218 are 8 px): the catalogue of images and verdicts, the remedy and the watches — done 2026-11-09: the Svapnohana whole on the Peking print (img. 315–318, every line by eye at 1.25×, key words at 2×) as passage svo:p271d, scan state: the order and wording follow the Derge etext throughout, the doubtful words read on Peking agree with RD228 («རྟ་ལ་མོ», «ག་ཡ་ཏྲི» with ཏྲི, «བཅུ་ཡི་བར»), but the Peking has no line for the fourth watch («ཐུན་བཞི་ཟླ་བ་ཕྱེད་བར་དུ», half a month) after the second and third (conflict claim svo-peking-fourth-watch-line; moderate confidence, 2× crop), so the half-month span stands on the Derge text alone until the Narthang prints and the Dpe bsdur ma notes are checked; read at low size, a one-syllable variant in an unremarkable word could have passed | SVO: Peking W1KG13126 vol. 28 (I1KG13197 img. 315–318, red print), and the Narthang I3279 img. 286–289 as witness; the Derge etext and the passages svo:p268-271 of RD207; routes and volume ids in PLAN.md Dead ends (RD228) | RD218, RD228 | [S1](#s1) | corpus/svo/svo.md | Read RD233: the text line by line on the Peking print by eye on enlargements (crops at pixel boxes), against the Derge etext of RD207; every image with its verdict, the watches and their spans, the remedy; bring the passages svo:p268–271 from etext/machine to scan state on the Peking print, name every difference from the Derge etext as a variant, and the doubts that stay as doubts. |
| <a id="rd234"></a>RD234 | Advayavajra's examination of dreams (SVI) on a print readable at size: the Peking print or another — done 2026-11-09: Advayavajra's examination of dreams found in the Peking print (BDRC W1KG13126, vol. 46 pp. 309–312, I1KG13215 img. 309–312, open scans 5370×1400, letters about 40 px; the row's vol. 49 was wrong) and read by eye as passage svi:p309-312: it follows the Derge text in every place read; the Sanskrit title reads «སྭ་པ་ནེ་ཀྵ»; the places RD218 left to the machines read «དང་བ་ཡིས», «ཁྲི་མོན», «ནོར་ཕྲོགས», «དེད་དཔོན», «རྐྱལ»; Amoghasiddhi's sign reads «བྱ་ཤིང་ཏེ…ཕོ་ཉར་གཏོང», not «བྱ་ཤར་ཏེ་རུ»; Narthang (W2KG5015 vol. 46 pp. 289–291) and W22704 vol. 46 pp. 291–293 located, the Dpe bsdur ma etext not compared | SVI: the Derge scan (I1365 pp. 261–262) is 8 px; look for the work in the Peking (W1KG13126 vol. 49 or the Dpe bsdur ma vol. 25 pp. 415–418, etext only), Narthang and Co ne through purl.bdrc.io/resource/W….json | RD218, RD228 | [S1](#s1) | corpus/svi/svi.md | Read RD234: locate the work in the Peking or Narthang print (volume ids by instanceHasVolume, as RD228 did); read its passage line by line by eye against the passage svi:p261-262 of RD207, the Dpe bsdur ma etext beside it with its variant notes; the three parts of the night, the images with verdicts, the Amoghasiddhi sign «བྱ་ཤར་ཏེ་རུ»; variants as claims; dead ends to PLAN.md. |
| <a id="rd235"></a>RD235 | The Svapnohana's fourth-watch line («ཐུན་བཞི་ཟླ་བ་ཕྱེད་བར་དུ», half a month) in the Narthang prints and the Dpe bsdur ma notes: absent in Peking (RD233), present in the Derge etext — done 2026-11-09: the Svapnohana's fourth-watch line «ཐུན་བཞི་ཟླ་བ་ཕྱེད་བར་དུ» is present in both Narthang scans (printed p. 282, one print tradition) and in the gser bris ma, and in the Dpe bsdur ma text, whose notes (p. 1368, placed by order only) record it as omitted by Peking alone; the omission is Peking's, not a Derge addition, so the span of half a month stands; the Co ne print not found; the first segment's spelling «བརྒྱད་པ་ལ»/«བརྒྱད་བར་ལ» not settled at 30–40 px | SVO: Narthang W22704 vol. 28 (I3279 img. 286–288), the gser bris ma W23702 (I1PD31365 img. 386–389), the Dpe bsdur ma etext W1PD95844 vol. 14 pp. 1364–1368 with its variant notes, Peking img. 315 line 2 (I1KG13197) for the comparison; claims/svo-peking-fourth-watch-line.md | RD233 | [S1](#s1) | corpus/svo/svo.md | Read RD235: the lines of the second and third watches and what follows them in each print by eye, and the Dpe bsdur ma notes at that place (which prints omit the line); the claim of conflict names the prints on each side; claims/night-watches.md and the RD215 spans are corrected if the line is a Derge addition. |
| <a id="rd236"></a>RD236 | Advayavajra's examination in the Narthang prints and the Dpe bsdur ma notes, beside the Peking reading of RD234 — done 2026-11-09: Advayavajra's examination in the Narthang manuscript (W2KG5015 vol. 46, I2KG212330 img. 289–291, 30 px hand) by eye and the Dpe bsdur ma notes: Narthang agrees with Peking on the Sanskrit title «སྭ་པ་ནེ་ཀྵ», «ཛ་ཏ་པ» and «རྐྱལ»; reads «བཏབ་ལ་ཉལ» where Derge has «བརྟགས་ལ» and Peking «བརྟགས་པ་ལ»; has «སྦྲུལ» as the Derge etext where Peking read «སྤྲུལ»; W22704 is a coarse copy of the same images, not a second witness; the Dpe bsdur ma notes (p. 418, markers lost) give Peking and Narthang together at most places and, at Amoghasiddhi's line, Peking «ཤིང་ཏེའུ» and Narthang «ཤང་ཏེའུ»; the svi registry entry gained its prints block; the middle syllable of Narthang's Amoghasiddhi line not settled | SVI: Narthang W2KG5015 vol. 46 pp. 289–291 (MW2KG5015_1746), W22704 vol. 46 pp. 291–293 (MW22704_1746), the Dpe bsdur ma etext W1PD95844 vol. 25 pp. 415–418 with variant notes; Peking W1KG13126 vol. 46 (I1KG13215 img. 309–312) as base; routes in PLAN.md Dead ends (RD234) | RD234 | [S1](#s1) | corpus/svi/svi.md | Read RD236: the places RD234 left unsettled (Amoghasiddhi's «བྱ་ཤིང་ཏེ…», «བརྟགས་པ་ལ་ཉལ», «ཛ་ཏ་པ/ཀ», «སྤྲུལ»/«སྦྲུལ», «རྐྱལ») in the Narthang prints by eye, and the Dpe bsdur ma notes at those places; claims of agreement or conflict per print; the svi registry entry gains the Peking and Narthang image groups. |
| <a id="rd237"></a>RD237 | Other prints of the dream texts whose cursive hand the scans of RD219 and RD222 would not give: KCM's time phrase «བཻ་རང་གི་དུས་ཚོད» (pp. 679–682), SRM (Śramaṇā, vol. 6 ff. 51v–53r), TDR (White Tārā, pp. 343–344), GNC (pp. 557–561) — done 2026-11-09: partly: KCM's time phrase found in the etext of the Rin chen gter mdzod (VE1KG14_037; a second etext IE0OPIC2BFA6FE): «ཐོ་རེངས་ཀྱི་དུས», the time of dawn, not a named watch and not «བཻ་རང་གི་དུས་ཚོད» (Yigdzin-1) or «ཕམ་རང་གི་དུས་ཚེ» (MITRA) (etext of a second print, not scan; q819 and q801 answerable with that reserve); SRM's second print W24769 vol. 6 (I6320 img. 275–279, clear dbu can woodblock) read by Yigdzin-1 and three crops by eye: «གྲུ་བཞི» settles the grub/gru doubt as gru, «བཞག» not gzhag; the other flagged readings machine only; TDR and GNC not started, KCM's scan page in the Rin chen gter mdzod not found | the same works in other editions or typeset reprints found as RD228 found them: BDRC work-level search (bdrc_search.py), the abstract work's workHasInstance, then purl.bdrc.io/resource/MW….json for content locations; the Dalai Lama I's gsung 'bum in a typeset edition for SRM; Karma chags med's gsung 'bum for KCM | RD219, RD222 | [S1](#s1) | corpus/kcm/kcm.md, corpus/srm/srm.md, corpus/tdr/tdr.md, corpus/gnc/gnc.md | Read RD237: for each of the four texts, find a second print or edition whose letters can be read by eye, read the flagged places there (KCM's time phrase first, then SRM p. 394 line 2 and the grub/gru doubt, TDR, GNC), name print and page; where the edition agrees with the machine reading of RD210/RD217 the passage moves to scan state on that edition; dead ends to PLAN.md. |
| <a id="rd238"></a>RD238 | The Lama Gongdü's dream folios whole on the Palri Parkhang print, a clean typeset page: the signs of good and bad dreams against RD208's passages — done 2026-11-09: the Lama Gongdü's dream folios whole on the Palri Parkhang print (I01CT0067 img. 533–545, printed pp. 525–537; the end by eye on half-page crops, the rite machine only): the whole text of lgd:p961–971 stands in the same order with the same seven kinds, items and verdicts and the same time rules (dusk habit, midnight afflictions, dawn omen), nothing added or missing; the red whirlwind «རླུང་དམར་འཚུབས་མས་དེད་པ» occurs once in Palri against twice in the Rin chen gter mdzod, whose first occurrence looks like a doubling; wording variants listed in claims/lgd-palri-print-against-rd208.md; the goods-of-gossip line read in its context (the run of riding and being led off, before the lone wanderer) says only that they are examples of goods of gossip the dreamer uses, without naming them; passage state machine | LGD: Palri Parkhang W01CT0060 vol. 7 (I01CT0067 img. 534–545; img. 542 line 1 already read in RD232), with RD208's passages lgd:p961–971 (Rin chen gter mdzod vol. 42, I1KG57) and the Shechen etext as witnesses | RD208, RD232 | [S1](#s1) | corpus/lgd/lgd.md | Read RD238: the dream folios line by line on the Palri print (crops at pixel boxes, autocontrast), the lists of good and bad signs against RD208's passages and claims (dream-catalogue.md); every difference of wording or verdict as a variant claim; whether the Palri text has the rite and the time rules as the Rin chen gter mdzod does; the goods of gossip passage whole with its context. |
| <a id="rd239"></a>RD239 | What RD237 left of the deity dream rites: SRM on its legible second print, TDR and GNC in other prints, and KCM's page in the Rin chen gter mdzod scan — done 2026-11-09: partly: SRM's flagged places read by eye on the legible second print (W24769 vol. 6, I6320 img. 276–278): «འཛེམ» with the e sign, «འོག», the goddess's name «ཤྲ་མ་ཎ», «སྤྲས་ཏེ» with ra-btags, «བསྟན་ཏེ» (moderate), «དེས་མ་གྲུབ་ན» (des, not ngos); the print agrees with Yigdzin-1 except «རྔ་ཡབ་གི་མེ་ལོང་» (moderate), passages stay machine for the Gangtok print; dro'i kha/dro zhig, 'dod/'ong and «ཡོང་ངོ»/«ཡོད་དོ» unsettled; TDR, GNC and KCM's scan page not reached | SRM: W24769 vol. 6 (I6320 img. 275–279, ff. 39r–41r); TDR (White Tārā, pp. 343–344) and GNC (pp. 557–561, the Mtsho rgyal text) in other editions found by BDRC work-level search and workHasInstance; KCM: Rin chen gter mdzod vol. 37 (I1KG52, etext VE1KG14_037) to find the scan page of the phrase «ཐོ་རེངས་ཀྱི་དུས» | RD237 | [S1](#s1) | corpus/srm/srm.md, corpus/tdr/tdr.md, corpus/gnc/gnc.md, corpus/kcm/kcm.md | Read RD239: SRM's passages of RD210 against the clear W24769 print by eye, every flagged place; second prints for TDR and GNC as RD237 did for SRM; KCM's phrase on the scan if the page can be located; dead ends to PLAN.md. |
| <a id="rd240"></a>RD240 | Second prints of the White Tārā dream rite (TDR pp. 343–344) and the Great Compassionate One's dream text with the Mtsho rgyal text (GNC pp. 557–561) — done 2026-11-09: partly, machine and etext state: GNC has a second instance, the dbu med manuscript W1ER167 vol. 1 (I1ER1055, scans 5704×1181, image = printed page + 3, a headless calligraphic hand), read by Yigdzin-1 over img. 729–735 which follows the Paro machine reading closely (differences «རྨི་ལམ་བརྟགས་པ», «བདག་གི་ཁྱོད་ལ», «པད་འདབ་ཉི་ཤུ་པའི་»), no syllable settled by eye; TDR has a second witness in an etext of the 'Brong rtse be'u bum chen mo (VEIE0OPI0BD8769F_I1PD407, printed pp. 313–316) which reads the north mirror «srog chags» (living beings) where the Delhi machine had «སྤོག་ཆཊ», and differs at «སེང་གེའི་ཉལ་སྟབས», «གཉིད་ལོག», «ཚར་མང་དུ» (many times, not seven) and the lion-faced dakini's section heading; the tdr summary's 'life-force' wants correcting; nothing moved to scan | TDR and GNC in other editions found by BDRC work-level search (bdrc_search.py), the abstract work's workHasInstance, purl.bdrc.io/resource/MW….json for content locations, as RD237 found SRM's W24769; RD239 did not start them | RD237, RD239 | [S1](#s1) | corpus/tdr/tdr.md, corpus/gnc/gnc.md | Read RD240: locate a legible second print of each, read the passages of RD210 and RD217 by eye there (four mirrors A NU TA RA; the wheel at the pillow with vulture flesh), name print and page; dead ends to PLAN.md. |
| <a id="rd241"></a>RD241 | The White Tārā dream rite on the scan of the other print (W1PD2, I1PD407, printed pp. 313–316) and the Great Compassionate One's text on the manuscript crops (I1ER1055 img. 730–731) — done 2026-11-09: not reached: the TDR scan of W1PD2 (I1PD407, printed pp. 313–316) is restricted (the manifest lists only img. 3–22 and 373–391), so TDR stays on the Delhi machine reading and the etext of RD240; GNC's headless manuscript hand (I1ER1055 img. 731 lines 3–5) does not resolve at 1.2×, no passage changed state | TDR: W1PD2 vol. of the 'Brong rtse be'u bum chen mo, scan I1PD407 (volume ids by purl.bdrc.io/resource/W1PD2.json), etext VEIE0OPI0BD8769F_I1PD407 as guide; GNC: W1ER167 vol. 1, I1ER1055 img. 729–735 (image = page + 3), the four-mirror lines and the vulture-flesh sentence at 1.5–2× | RD240 | [S1](#s1) | corpus/tdr/tdr.md, corpus/gnc/gnc.md | Read RD241: fetch the TDR scan pages, read the mirrors, the lion posture, the count of recitations and the north mirror's «srog chags» by eye; GNC's flagged places by crops; passages to scan state where read; dead ends to PLAN.md. |
| <a id="rd242"></a>RD242 | The Svapnohana on the Peking print at 2× line by line: img. 315 and 316, the catalogue of images and verdicts, the second pass RD233 did not make (it read at 1.25×) — done 2026-11-09: only in part: Peking img. 315 line 2 at 2×: the fourth-watch line is absent again (as RD233 and RD235), the third watch's line ends «བར་དུ», the first segment looks like «བརྒྱད་པ་ལ» (པ, weak) against the Derge etext's «བརྒྱད་བར་ལ»; no other difference in what was read, svo:p271d unchanged; the other seven lines of img. 315 and all of img. 316 not read at 2× | SVO: Peking W1KG13126 vol. 28, I1KG13197 img. 315–316, contrast-stretched, 2× crops; passage svo:p271d | RD233 | [S1](#s1) | corpus/svo/svo.md | Read RD242: img. 315 and 316 line by line at 2× against passage svo:p271d and the Derge etext, a variant claim for every one-syllable difference found, the passage corrected where the first pass was wrong; the fourth-watch line of img. 315 line 2 re-checked once more at 2.5×. |
| <a id="rd243"></a>RD243 | The Svapnohana on the Peking print at 2×, img. 315 lines 1 and 3–8 (RD242 read line 2 only) — done 2026-11-09: only in part: Peking img. 315 line 1 (title through «དེ་ཡི་བརྟག་པ་བཤད་པར་…», x 480–3350) read at 2×, no variant from the Derge etext; the Sanskrit title's stack still not told apart; lines 3–8 not read at 2× | SVO: Peking I1KG13197 img. 315, passage svo:p271d; lines found from a 1/3-size overview (line 2 at y about 400–520, pitch about 111 px) | RD242 | [S1](#s1) | corpus/svo/svo.md | Read RD243: lines 1 and 3–8 of img. 315 at 2× against svo:p271d and the Derge etext; a variant claim for every one-syllable difference; the passage corrected where the first pass was wrong. |
| <a id="rd244"></a>RD244 | The Svapnohana on the Peking print at 2×, img. 316 (8 lines, offset −45 px) — done 2026-11-09: only in part: Peking img. 316 lines 1 and 2 at 2× (the merit of the flesh eaten, foot, hand, head, into the entrails): the verse shapes agree with the Derge etext, no differing syllable seen, a shape check not syllable by syllable; lines 3–8 not read at 2× | SVO: Peking I1KG13197 img. 316, passage svo:p271d | RD242 | [S1](#s1) | corpus/svo/svo.md | Read RD244: img. 316 line by line at 2× against svo:p271d and the Derge etext; variant claims; the passage corrected where wrong. |
| <a id="rd245"></a>RD245 | The Svapnohana on the Peking print at 2×, img. 317 and 318 (8 and 5 lines; the places of RD233 left unsettled: «བློ» line 8 of 317, the end of 318 line 4) — done 2026-11-09: only in part: Peking img. 317 line 8 (the syllable between «འབྲས་བུ་ལྡན་ན» and «མེད» is one stack with a vowel sign, not told from «བློ» or anything else) and img. 318 lines 4–5 at their joint (the end of line 4 cut at the frame, line 5 begins «པ་» then stacks that look one syllable longer than «པ་རྫོགས་སོ», unresolved at 2×); no variant claimed, svo:p271d unchanged; img. 317 lines 1–7 and img. 318 lines 1–3 not read at 2× | SVO: Peking I1KG13197 img. 317–318, passage svo:p271d | RD242 | [S1](#s1) | corpus/svo/svo.md | Read RD245: img. 317 and 318 line by line at 2× against svo:p271d and the Derge etext; variant claims; the passage corrected where wrong; the doubtful syllables of RD233 settled or kept as doubts. |
| <a id="rd246"></a>RD246 | The Svapnohana on the Peking print at 2×, img. 316 lines 3–8 (what RD243–RD245 left) — done 2026-11-09: Peking img. 316 lines 3–8 transcribed at 2× (42–45 syllables a line, each break between verse lines): one difference from the Derge etext, line 8 «དེ་ཡི་དཔལ་ནི་ཐམས་ཅད་འབྱུང་» where Derge has «དེ་ཡིས་དཔལ…» (no ས after «ཡི» at 4×, agrees with Yigdzin-1's reading in RD218, sense unchanged); weak spots: «གང་ལ»/«གང་བ», «འོ་མ»/«ཨོ་མ», shad and tsheg spacing, and «དཀར» of the white-garment woman read at 2× only; most syllables identified by shape with the expected Derge word, so a one-stroke difference could have passed | SVO: Peking I1KG13197 img. 316 (offset −45 px, lines 3–8 at y about 522–1000), passage svo:p271d; lines from a 1/3-size overview, pitch about 111 px | RD243, RD244, RD245 | [S1](#s1) | corpus/svo/svo.md | Read RD246: img. 316 lines 3–8 syllable by syllable at 2× against svo:p271d and the Derge etext of svo:p268-271, every syllable written down and compared, not a shape check; a variant claim for every difference; the passage corrected where the first pass was wrong. |
| <a id="rd247"></a>RD247 | The Svapnohana on the Peking print at 2×, img. 317 lines 1–7 (what RD243–RD245 left) — done 2026-11-09: Peking img. 317 lines 1–7 transcribed and compared at 2×: the only difference from the Derge etext is again «རྟ་ལ་མོ» (plain la) for «རྟ་བླ་མོ»; doubts kept: la/ba of «གང་ལ་» and ཁ/བ of «འཁྱུད» in line 1, the joint of lines 4–5 (the Derge «བྲམ་ཟེར་ཕྱུགས»: whether a separate ར and a «དང་» stand), «ཡིན་ན» and «ཞགས་པས» read as the etext at low confidence; every 'no difference' is moderate confidence at this size | SVO: Peking I1KG13197 img. 317, passage svo:p271d; lines from a 1/3-size overview, pitch about 111 px | RD243, RD244, RD245 | [S1](#s1) | corpus/svo/svo.md | Read RD247: img. 317 lines 1–7 syllable by syllable at 2× against svo:p271d and the Derge etext of svo:p268-271, every syllable written down and compared, not a shape check; a variant claim for every difference; the passage corrected where the first pass was wrong. |
| <a id="rd248"></a>RD248 | The Svapnohana on the Peking print at 2×, img. 315 lines 3–8 and the rest of line 1 (x 3350–4900) (what RD243–RD245 left) — done 2026-11-09: only in part, weaker than asked: Peking img. 315 lines 3–8 transcribed at 2× with syllable counts and shapes agreeing with the Derge etext, no variant seen, but wherever a glyph was blurred the etext's syllable was written, so this is a shape check; Peking's line breaks differ from Derge's; doubts: the last glyph of line 8 («ཟན» or «ཟས»), «རྣམ་པར» ས, the first stacks of «བཞོན་པ» and «ཁང་བཟངས», «དང་བ» བ against པ; the rest of line 1 (x 3350–4900) not read | SVO: Peking I1KG13197 img. 315 (line 2 at y about 400–520), passage svo:p271d; lines from a 1/3-size overview, pitch about 111 px | RD243, RD244, RD245 | [S1](#s1) | corpus/svo/svo.md | Read RD248: img. 315 lines 3–8 and the rest of line 1 (x 3350–4900) syllable by syllable at 2× against svo:p271d and the Derge etext of svo:p268-271, every syllable written down and compared, not a shape check; a variant claim for every difference; the passage corrected where the first pass was wrong. |
| <a id="rd249"></a>RD249 | The Svapnohana on the Peking print: img. 318 lines 1–3 and the end of img. 315 line 1 (x 3350–4900) at 2×, the last lines RD243–RD248 did not reach — done 2026-11-09: only in part, a shape check: Peking img. 315 line 1 from x 3350 to the frame reads «…རྨི་ལམ་སྣ་ཚོགས་ལས་བྱུང་བ། །དེ་ཡི་བརྟག་པ་བཤད་པར་» as the Derge etext; img. 318 lines 1–3 (about 40 syllables each, centres y about 325, 436, 547) fit the etext's run, written by shape against the etext not syllable by syllable, line breaks not fixed; no difference found; with RD243–RD248 the Peking text of img. 315–318 now has a first full pass at 2× and a shape check, the doubtful stacks listed in svo:p271d | SVO: Peking I1KG13197 img. 315 and 318 (offset −5 px), passage svo:p271d | RD243, RD245 | [S1](#s1) | corpus/svo/svo.md | Read RD249: write down every syllable of img. 318 lines 1–3 and img. 315 line 1 from x 3350, compare with the Derge etext of svo:p268-271; a variant sentence for every difference. |
| <a id="rd250"></a>RD250 | The doubtful stacks of the Peking Svapnohana at 4×: img. 315 line 8 last glyph («ཟན»/«ཟས»), «བཞོན་པ», «ཁང་བཟངས», «དང་བ», «རྣམ་པར» (RD248); img. 317 line 1 la/ba and ཁ/བ, the joint of lines 4–5 (RD247); img. 316 «གང་ལ»/«གང་བ», «འོ་མ»/«ཨོ་མ» (RD246) — done 2026-11-09: only in part: two of the doubtful Peking places at 4×: img. 315 line 8's last glyph («ཟན» or «ཟས») not told apart, low confidence in ཟན; the joint of img. 317 lines 4–5 reads «བྲམ་ཟེ། ཕྱུགས» with no separate ར where the Derge etext has «བྲམ་ཟེར་ཕྱུགས» (a weak variant, about 55 %, a ར fused into the stack's tail cannot be excluded); the other seven places (img. 315 «བཞོན་པ», «ཁང་བཟངས», «དང་བ», «རྣམ་པར»; img. 317 line 1 la/ba and ཁ/བ; img. 316 «གང་ལ»/«གང་བ» and «འོ་མ»/«ཨོ་མ») not reached | SVO: Peking I1KG13197 img. 315–317, line centres in the RD246–RD248 notes of svo:p271d | RD246, RD247, RD248 | [S1](#s1) | corpus/svo/svo.md | Read RD250: each listed place at 4× or more, contrast-stretched, write down what the stack is; settle each doubt or keep it with the reason; a variant sentence in svo:p271d for each difference found. |
| <a id="rd251"></a>RD251 | Doubtful stacks of the Peking Svapnohana at 4× (what RD250 left): img. 315 «བཞོན་པ» (line 3), «ཁང་བཟངས» (lines 3 and 7), «དང་བ» (line 6), «རྣམ་པར» (line 5) — done 2026-11-09: only in part: the doubtful stacks of Peking img. 315 at 3–5×: «བཞོན་པ» (line 3) fits the etext in shape, «ཁང་བཟངས» (line 3) fits with low-medium confidence, «དང་བ» (line 6) reads བ rather than པ, weakly; line 7 «ཁང་བཟངས» and line 5 «རྣམ་པར» (རྣམ or རྣམས) not settled; no difference from the Derge etext found; the print is too blurred at 3–5× to tell these stacks from their neighbours | SVO: Peking I1KG13197 img. 315; line centres in the RD246–RD248 notes of svo:p271d | RD250 | [S1](#s1) | corpus/svo/svo.md | Read RD251: each listed place at 4× or more, contrast-stretched, write down what the stack is; settle or keep with the reason; a variant sentence in svo:p271d for each difference. |
| <a id="rd252"></a>RD252 | Doubtful stacks of the Peking Svapnohana at 4× (what RD250 left): img. 317 line 1 la/ba of «གང་ལ་» and ཁ/བ of «འཁྱུད»; img. 316 «གང་ལ»/«གང་བ» (line 3 area) and «འོ་མ»/«ཨོ་མ» (line 6) — done 2026-11-09: only in part: Peking img. 317 line 1 at 4–6×: both «གང་ལ་» read ལ (plain, medium-high confidence) as the Derge etext, ཁ against བ in «འཁྱུད» kept as a doubt (looks more like ཁྱ than བྱ, low-medium); img. 316 line 5 «གང་ལ་རྣམ་པར…», line 6 «གང་ལ་ཏིལ་མར» and «འོ་མ»/«ཨོ་མ» not settled; no difference from the Derge etext found | SVO: Peking I1KG13197 img. 316–317; line centres in the RD246–RD248 notes of svo:p271d | RD250 | [S1](#s1) | corpus/svo/svo.md | Read RD252: each listed place at 4× or more, contrast-stretched, write down what the stack is; settle or keep with the reason; a variant sentence in svo:p271d for each difference. |
| <a id="rd253"></a>RD253 | SRM on the legible second print, what RD239 left: «བརྐྱང་འདུག»/«ཞལ་འཛུམ» (dro'i kha / dro zhig), «འདོད་དོན» ('dod/'ong), «ཡོང་ངོ»/«ཡོད་དོ», «རྔ་ཡབ་གི་མེ་ལོང་», and the rest of the passages of RD210 on W24769 — done 2026-11-09: SRM read whole on the legible second print (W24769 vol. 6, I6320 img. 275–279, title to colophon) as passage srm.6:i275-279, state scan with listed doubts: «མེ་ལོང་» is a clause of its own after a shad (not «ཡབ་གི་མེ་ལོང་» as RD239), «དབང་ཕྱུག», «ཆིངས», «ཞལ་འཛུམ», «འདོད་དོན» ('dod don, not 'ong), «བསྟན་ཏེ», «ཡང་ཡང་བསྐྱར་ན་ཕྱིས་གསལ་པོ་ཡོང་ངོ» (yong ngo), «དེས་མ་གྲུབ་ན», «གྲིབ་ལ་འཛེམ», «ཤ་མ་ཎའི» in the colophon without ra-btags; the dro'i kha / dro zhig place not found on this print (probably Gangtok-only); the Gangtok passage stays machine; confirmed mostly by matching shapes to Yigdzin-1, a stricter reader may downgrade a few syllables | SRM: W24769 vol. 6 (I6320 img. 275–279, six rows a page at pitch about 125 px, y about 20, 145, 270, 395, 520, 645; scans.py crop with pixel boxes), passages srm in corpus/srm/srm.md | — | [S1](#s1) | corpus/srm/srm.md | Read RD253: each of the listed places and then each passage of RD210 on the clear print syllable by syllable, written down and compared with the Gangtok machine reading; the passages move to scan state on W24769 where read; doubts kept with the reason. |
| <a id="rd254"></a>RD254 | The Lama Gongdü's goods of gossip, the last prints: the second and fourth syllables in the Zhe chen (img. 310 line 6: «རུག» or «རྒྱུག»), Kazi (img. 559) and Sbas-yul (img. 148) at 3–5×, and the Shechen vol. 37 page images — done 2026-11-09: the goods of gossip phrase in the last prints, by eye with both machines as witnesses: Zhe chen syllable 1 «རམ», syllable 2 no ཡ-btags and no ག above the head so «རྒྱུག» is not seen but «རུག», «དྲུག» and «ཟུག» are not told apart (MITRA reads «དྲུག»), syllable 3 «སྦང», syllable 4 «གཡེར»; Kazi «རམ་རུག་སྤང་གཡེར» confirmed; Sbas-yul's hand goes with the Ngodrup and Sherab Drimay print («ཏུག», «སྦང», «ཁྱེར»/«ཕྱེར»), first syllable unsettled; the Shechen etext's second place (text «མཁའ་འགྲོ་ལྔ་སྐོར», etext p. 36) names «ཞབས་རམ་རུག» and «སྦང་མ» as two of the goods of gossip filled into a speckled woollen bag to turn back a debt-child, «རམ་རུག» and «སྦང» there separate words, no print saying what they are; Palri img. 541 and 543 (printed pp. 533 and 535) read whole, agreeing with RD238 (machines agree syllable for syllable, line openings and endings checked by eye) | LGD: Zhe chen I4PD2250 img. 310 (x 1750–2750, y 305–395), Kazi I6132 img. 559, Sbas-yul I1LT3208 img. 148 line 3, Shechen VE1KG14_037 pages (the second place naming «ཞབས་རམ་རུག» and «སྦང་མ»), Palri I01CT0067 img. 541–543 whole | — | [S1](#s1) | corpus/lgd/lgd.md | Read RD254: the four syllables of the phrase in each print at 3–5×, written down; the Shechen etext's second place and its page image; Palri img. 541 and 543 whole as passages; claims/lgd-goods-of-gossip-prints.md corrected; what the goods are named in any print. |
| <a id="rd255"></a>RD255 | The Four Tantras' commentaries on the dream passages: Khro ru Mkhan po Tshe rnam (MW29701) on ch. 2, 7, 81 and 91, the Drikung Dka' mchan (MW1KG24729), Byang pa and Rin spungs pa on ch. 81 and 91, in the etext — done 2026-11-09: the Four Tantras' dream passages through their commentaries, etext only: Khro ru Mkhan po Tshe rnam (modern, ktr), 'Bri gung Chos kyi grags pa's Dka' mchan (bdk, printed page = etext − 27), Byang pa and Rin spungs pa on ch. 7 (their volumes end with the Explanatory Tantra, so nothing on ch. 81 or 91), Sde srid's Man ngag lhan thabs in two prints (lth, lhq), Blo bzang dbang rgyal's annotated form (mbl) and a Bhutan print (mnb); ch. 2 དག read as 'many' by every commentary that glosses it (q823 answered); ch. 7: all four quote the bad-dream verses and give one rule, the sick who constantly dream them die, the healthy are freed by the rite of service (རིམ་གྲོ), merit and meditation; ch. 81 sorted by kind of spirit; q820: no witness reads ལུས་ (0 hits), nobody defines ལྷས་, Khro ru's gloss ལྷས་ཚོར་བའི་དུས comes from Blo bzang dbang rgyal, two later prints read ངུས་ for དུས་; q821: བཟོ in the Serthang scan and most others, སོ only in the Bhutan print (OCR) and Drathang (machine); q824 answered: the thread and milk-and-dung bracketed in Serthang are the commentaries' own glosses; q822 partly: མོ plus ཕྱུ/ཕྱྭ/ཕྱ is the divination that identifies the spirit, ཕྱུ undefined; new sigla ktr, bdk, lth, lhq, mbl, mnb | ST commentaries: BDRC etexts MW29701 (pp. 175, 320–322 seen in part), MW1KG24729 (p. 61), MW27496, MW27494; claims st-dream-* | — | [S1](#s1) | corpus/st/st.md, corpus/bjp/bjp.md, corpus/rsp/rsp.md | Read RD255: what each commentary says on the dream lines of ch. 2, 7 (distant signs of death), 81 (the nāga dreams and the creature names) and 91, as passages with the Tibetan and English, the commentator named and dated; where a commentary settles ལྷས་ཚོར་དུས་ཏེ, ལྡེར་བཟོ་འགུལ or ཕྱུ (q820–q824) a claim; modern authors marked. |
| <a id="rd256"></a>RD256 | The Japanese side: the Ebara reprint's 大雑書 and 夢占吉凶秘伝書 (q786) and the 初夢 in old texts, found in any online form (NDL Digital, Waseda, Kokubungaku, Aozora), and the number of the 山家集 first-dream poem — done 2026-11-09: partly: the Ebara reprint's dream book identified (eb9:p2: the 墨屋吉兵衛 Kyoto printing of 享和1 (1801) of the 安永3 (1774) 新版ゆめあはせ, running titles いろは引夢うらなひ and 夢占吉凶秘傳書, held in the 潁原文庫; the 国文研 copy of its 嘉永5 (1852) printing has 10 open IIIF images, not yet read through; the 大雑書 not online); two Edo prints read through NDL OCR (machine state): 夢合延寿袋大成 (1814, ymk): the 初夢 once as Fuji the best first dream with no night named, season rules (scarecrow good in spring, summer and autumn, best mid-autumn; a colt by season), the real dream shows within three days; 永代大雑書 (1856, ezs): no 初夢, a dream before midnight shows far off and one after midnight near, Fuji hawk aubergine the first three lucky dreams, the 庚申 and 節分 articles name no dream; the 山家集 first-dream poem is entry 00001 of the spring book in the 日文研 waka database (the 新編国歌大観 number not seen); no Edo text read names a date or day of the cycle for the first dream; the sigla YMK and EZS added | EB9, the 万代大雑書 (RD223 read part 15), 山家集 (RD224), a search of the National Diet Library digital collections, Waseda's 古典籍総合データベース and the Edo almanac and 大雑書 prints for 夢合, 初夢, 宝船 | — | [S1](#s1) | corpus/eb9/eb9.md | Read RD256: locate in a headed browser (rule 7) any online copy of an Edo 大雑書 or 夢占 text, read its dream and 初夢 articles for rules naming a date, a day of the cycle or a season, quoted in the original with the English beside; the 山家集 poem's number; dead ends to PLAN.md. |
| <a id="rd257"></a>RD257 | Scheuermann's study of the Svapnohana and other modern studies of the Tibetan dream manuals by another route than Brill's captcha: open-access copies, library copies, the author's own repository — done 2026-11-09: partly: no open copy of Scheuermann's chapter (Brill, Google Books, OAPEN, Unpaywall, OpenAlex, Semantic Scholar, CORE, DOAB, HAL, Internet Archive all closed or blocked), so the chapter text stays unread; its published abstract (second-hand through an excerpt of Brill's front matter) says it treats the Svapnohana (Toh 1749) as one of two short stand-alone Tengyur dream texts, attributes authorship with the translation to Vibhūticandra, gives the sign-meaning associations and relates them to meditation-work dream images, and stands firmly in an Indian dream-omen tradition, not specifically Buddhist; read in full: Stearns 1996 (JIABS 19.1, Vibhūticandra born in the later 12th century, in Tibet from 1204, the Svapnohana no. 23 of the appendix), Wayman 1967 (older Indian watch rules: the Atharva-Veda three watches, the Kālaprakāśikā four quarters; the Aṅgavijjā adds the moon's phase; the Svapnohana's spans match the Kālaprakāśikā in kind, not in numbers), the Tohoku entry 1749 (translator only); authorship conflict kept as a claim (translator only: the Derge colophon, Tohoku, 84000, Bu ston «རང་འགྱུར»; author as well: Peking/Otani 2621, Cordier, Bose, New Catalogus Catalogorum, Scheuermann), the colophon outranking catalogues; two modern Tibetan books quote the watch verse (Padma rdo rje, Lanzhou 2002, MW25126; Bya mdo Klu byams rgyal, Beijing 2012, MW8LS18598, glossing dawn as tho rengs); no contradiction with RD207's reading | SCH (Scheuermann, Brill, DOI 10.1163/9789004410688_009); also Schuh, Cornu, Mayer, Kapstein on dream divination; routes: DOAB, OAPEN (Anubis), Semantic Scholar, the author's repository, Google Books snippets, Internet Archive | — | [S1](#s1) | corpus/sch/sch.md | Read RD257: find an open copy of the chapter or, failing that, of other modern studies of Tibetan dream divination; read for author, date, Sanskrit original and parallels of the Svapnohana and how Tibetan readers used it, in our own words; any conflict with RD207's reading as a claim of type conflict; dead ends to PLAN.md. |
| <a id="rd258"></a>RD258 | KCM's time phrase on a scan: the page of «ཐོ་རེངས་ཀྱི་དུས» in the Rin chen gter mdzod vol. 37 (I1KG52), found by offset from the etext — done 2026-11-09: KCM's time phrase on a scan in a third print: the Rin chen gter mdzod scans (I1KG52, 803 images, ending at etext offset about 752k; I1KG53 not in the etext's order) do not carry KCM, but the second etext's volume (VEIE0OPIC2BFA6FE_I1KG81132, a bka' 'bum volume, gleg bam 20, scans of 592 images served, image = etext page, printed folio = image − 8) holds it at img. 480–482 (folio 474): by eye on img. 482 line 3 end the phrase reads «ཐོ་རེང་གི་དུས་མངོན་སུམ་དུ་ལུང་སྟོན་པའི་ཚིག», dawn with «དུས» alone and no «ཚོད», agreeing with the Rin chen gter mdzod etext (ཐོ་རེངས་ཀྱི་དུས) and RD226's ligature before «རང་»; q819 and q801 can be answered 'dawn' with the reserve that the Tashijong syllable is a ligature; the title and first part (img. 480–481) Yigdzin-1 only, passage state scan for the phrase and page only | KCM: Rin chen gter mdzod vol. 37, scans I1KG52 (810 images, image = printed page + 17 at img. 640, 700, 730; img. 640, 700, 730, 760, 780, 800 in the scan cache), etext VE1KG14_037 (KCM at about 762k characters of 1.17M); the second etext IE0OPIC2BFA6FE | — | [S1](#s1) | corpus/kcm/kcm.md | Read RD258: find the page of the KCM text by the etext's [pN] marks and Yigdzin-1 over probe images (a binary search by the title and the closing paragraph), read the time phrase by eye on the scan and the title; if the manifest does not serve the page, say so as a dead end and try the other volume; the passage moves to scan state. |
| <a id="rd259"></a>RD259 | The Svapnohana in the Co ne print and other open copies, and the unnamed manuals' other routes: MDP's dream part and RBG — done 2026-11-09: partly: the Co ne Tengyur is served with scans (BDRC W1GS66030, 209 volumes, volume n = image group I1GS(66031+n), vol. 1 = I1GS66032; bitonal half-leaves about 1940–2055 px wide, letters about 25–30 px; open on archive.org as bdrc-W1GS66030, CC BY); the Svapnohana not yet found inside it (it should lie in vol. 63's last part, I1GS66094, or vol. 65's first part, I1GS66096; sampled at strides, the text only about 8 images long), so q833's Co ne part stays open; MDP's dream part is open on archive.org (bdrc-W21880, CC BY, 216 TIFFs 1504×2175, printed p. 134 = img. 142, pp. 134–137 = img. 142–145), read by Yigdzin-1 with the first page by eye: not a catalogue of dream images but a dream-incubation rite to see one's next birth (waxing 1st, a mandala of nine heaps, five offerings and a torma, a mantra recited 110,000 times and 108 at bedtime; signs told by colour only: white god, green human, yellow asura, blue animal, red hungry ghost, black hell; a lineage line to Bu ston and a monk's note), recorded as mdp:p134-137, superseding the 'unreachable' notes of RD212 and RD221; no higher-resolution Peking (archive.org bdrc-W1KG13126 original zip 401; Otani online has no page images; Adarsha's Tengyur is Derge only) | SVO: the Co ne Tengyur (BDRC search of the abstract work WA0RT4282 instances; Adarsha, the Asian Classics Input Project, rKTs); MDP (MW21880, pp. 134–137) and RBG (vol. 3 pp. 203–205) in other prints or in the Dpe bsdur ma Karma Kagyu texts; the Digital Otani Peking images | — | [S1](#s1) | corpus/svo/svo.md, corpus/mdp/mdp.md, corpus/rbg/rbg.md | Read RD259: find a Co ne copy of the Svapnohana and read the fourth-watch line and «ལ་མོ» there; find any other open print of the Peking Svapnohana at higher resolution than I1KG13197 (the Otani Peking Tripitaka images); other routes to MDP's dream part and RBG; every search written down as a dead end or a find. |
| <a id="rd260"></a>RD260 | WB ch. 30 p. 144's fourth riding animal «རྟ[?]» on the Taikhang and Zhol prints, and ch. 25's four dream lines on Taikhang I5877 img. 587–590 — done 2026-11-09: WB's dream lines on the Taikhang and Zhol prints: the fourth riding animal of ch. 30 p. 144 is «རྟ» (horse) on both (Taikhang vol. 2 I5878 img. 222, printed folio 212 = image − 10, a crisp hooked ra over ta, good; Zhol I1KG1710 img. 840, a hooked head over ta in a worn but open cut, moderate; Yigdzin-1 also reads «རྟ» on both, «ཧ» on the reprint), so the reprint's ink-filled glyph is the same word (q828 answered); ch. 25's four dream lines read by eye on the Taikhang vol. 1 (I5877 img. 583, 586, 587, 588, printed 573, 576, 577, 578 = image − 10) exactly as the reprint and the 1996 edition, no variant, the «རྨི» stack open on the Taikhang confirming the reprint's ink-filled stack; q827 not narrowed (the prints give only the same words, no gloss); q348 «རྨི་ལྟས» against «ཕྱི་ལྟས» not enlarged; the Zhol print not opened for ch. 25 | WB vol. 2 p. 144 on the Taikhang (I5877; offset page + 159 guessed, first check printed numbers) and the Zhol print; vol. 1 pp. 428, 430, 431 on Taikhang I5877 img. 587–590 | — | [S1](#s1) | corpus/wb/ch30.md, corpus/wb/ch25.md | Read RD260: find p. 144 on the Taikhang and Zhol prints by their printed numbers, read the animal after «མ་ཧེ» at 4–6× on each print; the four ch. 25 dream lines on the Taikhang likewise; claims of agreement or conflict per print; the RD230 questions answered or narrowed. |
| <a id="rd261"></a>RD261 | The 夢占吉凶秘傳書 itself: the 国文研 copy of the 嘉永5 (1852) printing, 10 open IIIF images, read through for every rule naming a date, a day of the cycle or a season — done 2026-11-09: the 夢占吉凶秘傳書 (the 嘉永5 (1852) printing, 国文研 copy, 10 open images) read by eye at crops: iroha-ordered two-tier entries with pictures, no preface and no table of omens; the one calendar rule is the table of days a dream is not told (image 9 left page, twelve columns 正月–十二月 with day branches in kana), which repeats the ゆ六 table of the 万代大雑書 in every column matched (二月 酉 申/巳; 四月 = 五月; 六月 子 丑/亥; 七月 子 丑/辰; 八月 = 九月; 十月 辰/巳; 十二月 午 未/亥), the other cells agreeing in count and outline but not decoded glyph by glyph; one season mention (image 4, under ま: seen in the autumn, good); a time to fulfilment of two or three days (image 9 right page under す), as in ymk; the closing paragraph a bad-dream remedy naming no day or season; the colophon matches the 1801 one with the year changed; a claim of absence over the leaves seen: no 初夢, 元日, 節分 or 庚申 article, no season-by-season rules; many cursive columns only skimmed, picture captions not read; q786 answered in part (the Ebara 大雑書 itself still unread); passages in machine state | EB9: the 新版ゆめあはせ / 夢占吉凶秘傳書 (CiNii BB15658308, 国書データベース 200013909); the 国文研 copy (IIIF manifest from the 国書データベース record), small cursive, iroha-ordered entries with pictures; the Waseda copy (bunko30_e0277) if the site is up again | — | [S3](#s3) | corpus/eb9/eb9.md | Read RD261: fetch the ten images, read every leaf (the front matter and any preface or table of the days first, then the entries), quote in the original with the English beside every rule that names a date, a day of the cycle, a season or the 初夢; if the book has none, say so as a claim with the leaves read; dead ends to PLAN.md. |
| <a id="rd262"></a>RD262 | The two modern Tibetan books that quote the Svapnohana's watch verse: Padma rdo rje (Lanzhou 2002, MW25126) and Bya mdo Klu byams rgyal (Beijing 2012, MW8LS18598) — done 2026-11-09: the two modern Tibetan books that quote the Svapnohana, search-only on BDRC (the etext chunks answer 403), assembled by about 200 chained exact-string searches, etext state: Padma rdo rje (MW25126) quotes the whole Svapnohana (about pp. 17–26 by machine page markers; the watch lines match Derge except རྒྱལ་བོ for རྒྱལ་པོ; no source named, no gloss of the watches reached; his p. 16 gives a modern account of a dream's length); Bya mdo Klu byams rgyal (MW8LS18598) quotes the verse twice, introducing it as from the Conqueror's Tengyur (ch. 6, 'Explanation of dreams in Tibetan medicine', pp. 110/111) and glossing: a dream arising at tho rengs stag gi dus (dawn, the tiger's time) surely gives its fruit, a modern reader's clock reading marked lower in rank than the texts; a second quotation with the same gloss leads on to the Upadeśa Tantra's leprosy chapter; two further lines 'from the tantra itself' («mtshan stod rmi lam brjed nges 'bras bu med / tho rengs mthong dang gsal na 'bras bu 'byin») are found by search in the Four Tantras tradition's etexts but the tantra's own chapter was not opened; neither book gives a clock span for the watches or names a lunar day, weekday or season for a dream; strings with tshes grangs, dbyar dgun, dus bzhi, gza' skar and rmi lam give 0 hits on all of BDRC (gza' skar rmi lam 2 hits, neither in the two books) | BDRC etexts of MW25126 and MW8LS18598 found by the exact-string search of RD257; claims/svo-modern-tibetan-quotations.md | — | [S1](#s1) | corpus/svo/svo.md | Read RD262: each quotation with its page and context in the etext, the Tibetan and the English beside, the author's own gloss of the watches (tho rengs and the tiger's time in the second) marked as a modern reader's reading; whether either book names a clock span for the watches, or a lunar day or season; the books' own sources named where they name them. |
| <a id="rd263"></a>RD263 | The Indian frame of the watch rule, from published translations: the Atharva-Veda Pariśiṣṭa's Svapnādhyāya, the Kālaprakāśikā, the Aṅgavijjā, and the Brhat Saṃhitā or other Indian dream-omen chapters, for any tie of a dream to a lunar day, a weekday or a month — done 2026-11-09: the Indian frame of the watch rule from published texts on the Internet Archive (OCR text layers, machine state): the Atharva-Veda Pariśiṣṭa 68 (Svapnādhyāya) 58–59 gives five times, first watch a year, second eight months, third half of that, at the cows' release a month, at dawn at once, the same five spans as the Svapnohana's, so Wayman's six months for the second watch is wrong (six months is the Kālaprakāśikā's number: a year, six months, a month, twelve days before dawn, a day before sunrise) and the Svapnohana's year and eight months are the appendix's; the claim svo-indian-frame-of-watch-rule is corrected; the Aṅgavijjā ch. 42 dates the dream (night and day, evening, midnight, afternoon, the fortnights and seasons by their signs) but gives no span to fruit and no tithi, weekday or month; the Bṛhatsaṃhitā and Yogayātrā have no dream chapter; no text read ties the time of a dream's fruit to a lunar day, weekday or month; sigla apv, kps, agv, bsm, yyt registered | Wayman 1967 (Internet Archive scan, read in RD257) as guide; the published editions and translations found on the Internet Archive or Wikisource (Atharvaveda Pariśiṣṭa 'Svapnādhyāya'; Varāhamihira's Bṛhatsaṃhitā ch. on dreams; Aṅgavijjā; Kālaprakāśikā) | — | [S1](#s1) | corpus/svo/svo.md | Read RD263: locate a published text or translation of each (facts only, our own words, no copyrighted wording); read the passages naming the time of a dream's fruit and any tie to a lunar day, weekday, month or season; register each as a source with a siglum if read; the claim svo-indian-frame-of-watch-rule extended with the passages; dead ends to PLAN.md. |
| <a id="rd264"></a>RD264 | The Four Tantras' dream words on the other prints' scans: «ལྷས་ཚོར་དུས་ཏེ» / «ངུས་ཏེ» and «ལྡེར་བཟོ་འགུལ» / «སོ» in the Bhutan print (MW4CZ16923, I1KG13565, open) and the 2005 Man ngag lhan thabs scan, and the Serthang pages of ch. 7's bad-dream list for «མི་བཟང་བ»/«མི་ཟད་པ» — done 2026-11-09: the Four Tantras' dream words on the other prints' scans: «ལྷས་ཚོར་དུས་ཏེ» reads དུས with ལྷས as ལྷ plus ས on all three scans (Serthang, Bhutan I1KG13565 img. 267, the 2005 Man ngag lhan thabs leaf 362), the ངུས of the Bhutan and 2005 etexts is the etext's OCR fault, no scan or etext has ལུས (q820, q844 answered); «ལྡེར་བཟོ་འགུལ»: བཟོ on the Serthang and 2005 scans, སོ on the Bhutan scan (moderate) and the Drathang machine reading, the variants splitting by tradition (q821 narrowed); the sister clause on the Bhutan scan «རང་ཚོར་དུས་ཡིན» not ངང་; ཕྱུ in ch. 91 corrected to ཕྱྭ (the Serthang glyph at 10×, img. 507, has the wa-zur; the Bhutan scan img. 418 and the Drathang print agree on «མོ་ཕྱྭ་རིམ་གྲོ»), RD227's ཕྱུ was wrong, its sense not looked up; the Serthang scan has no image of the page with ch. 7's bad-dream verse (img. 44 and 46 are the same page twice), the Drathang print has it on img. 42 reading «རྨི་ལམ་མི་བཟང་བ» by eye, so མི་ཟད་པ is Rin spungs pa's own variant (q843 narrowed); mnb now scan with passage mnb:p418, gt:i42 new | ST: the Bhutan print scan I1KG13565 (ch. 81 near the Instruction Tantra), the 2005 Lhan thabs on archive.org, the Serthang scan I3CN5038 for ch. 7's verses (etext p. 44, img. 362–363 neighbourhood: RD211 read the ch. 7 distant-death-signs passage), claims/st-lhas-tshor-lder-bzo.md | RD255 | [S1](#s1) | corpus/st/st.md | Read RD264: fetch the scan pages, read the words at 4–6× by eye and write down each syllable; claims of agreement or conflict per print; q820, q821 and RD255's ch. 7 question answered or narrowed; dead ends to PLAN.md. |
| <a id="rd265"></a>RD265 | More Indian dream-omen texts for any tie of a dream to a lunar day, weekday, month or season: Negelein's German Svapnacintāmaṇi (1912), Kohlbrugge 1938 on the Atharva-Veda Pariśiṣṭa omens, the Aṅgavijjā chs. 43–44, the Kālaprakāśikā's Sanskrit verse, and the Matsya-, Agni- and Garuḍa-purāṇa dream chapters — done 2026-11-09: more Indian dream-omen texts from OCR text layers (machine state): the Svapnohana's series (a year, eight months, three months, half a month, ten days at dawn) is the Brahmavaivartapurāṇa's (Kṛṣṇajanmakhaṇḍa 73.5–6, bvp:p662-665) whole, which Negelein prints as his 'b 5–6', and the Svapnohana's 'seen without doubt' on the fourth watch echoes the Matsya's paśyato nātra saṃśayaḥ, correcting RD263's 'follows the Kālaprakāśikā in kind'; the Agni 229 and Matsya 242 have six months for the second watch and differ only in the fourth watch; Varāhamihira's Bṛhadyātrā 16 (Pingree 1972) is a dream chapter after all (year, half-year, quarter-year, a month at the last part of the night, at once at the cows' release; verse 10 ties the dream's humour to the parts of the night and the rains, autumn and spring); the Svapnacintāmaṇi's verses give year, half-year, three months, month, the last 48 minutes within ten days, sunrise at once; the Kālaprakāśikā's Sanskrit found (ch. 42 vv. 66–69, Nṛsiṃhasūri named, the fourth span garbled); the Aṅgavijjā ch. 42's time list recovered whole (dark things mean night: evening, midnight, break of day; bright things day: forenoon, midday, afternoon; the fortnight, season and lunar-month rules in ch. 59); chs. 43–44 are travel; the Garuḍapurāṇa has no dream chapter; no text ties a dream or its fruit to a tithi, weekday, nakṣatra or month, though other omens are so tied (the halo by the day of the half-month, the nirghāta on the 1st, 8th and 14th lunar day); Yogayātrā 2.23–24: hearing the tithi, nakṣatra and weekday read ends bad dreams; sigla neg, bya, kpd, adb, mtp, agp, kho, gpu, bvp registered; agv:p120-121 is the same page as agv:p190-191 (OCR slip) | The Internet Archive and GRETIL/Sanskrit Documents editions found as RD263 found them; Negelein 1912 (public domain), Kohlbrugge 1938 (id kohlbrugge-atharvaveda-parisista-uber-omina), Puṇyavijaya 1957 (Aṅgavijjā), the Purāṇas on Wikisource or Sanskrit Documents | RD263 | [S1](#s1) | corpus/svo/svo.md | Read RD265: for each text that can be had, the passage naming the time of a dream or its fruit and any tie to a tithi, weekday, month, nakṣatra or season, facts in our own words with the original briefly quoted; the claim indian-dream-time-atharva-kalaprakasika extended; sigla registered; dead ends to PLAN.md. |
| <a id="rd266"></a>RD266 | The Svapnohana in the Co ne Tengyur: find the text in vol. 63 (I1GS66094) last part or vol. 65 (I1GS66096) first part and read the fourth-watch line and «ལ་མོ»/«བླ་མོ» — done 2026-11-09: not found: the Svapnohana is not in the Co ne Tengyur's vol. 63 last part (I1GS66094, 605 images: Yigdzin-1 over img. 400–422 every image and 424–604 every fourth, all Nāmasaṃgīti commentary prose, no hit for «རྨི་ལམ་བརྟག་པ», «ཐུན་གསུམ», «ཐུན་གཉིས», and img. 604 still commentary) nor at the start of vol. 65 (I1GS66096: img. 1–5 the scanner's title page and blanks, img. 6 opens the Nāmasiddhi-vimalā), so the Co ne does not keep the Derge neighbours at these two places; q833's Co ne part stays open; SVI not looked for; nothing verified by eye | SVO: Co ne W1GS66030, volume n = image group I1GS(66031+n), images served by iiif.bdrc.io, bitonal half-leaves about 2000 px wide; Derge puts the work (vol. 28 p. 268 here, vol. 49 p. 261 for SVI) after the Nāmasaṃgīti commentaries, which fill Co ne vols. 63–65 | RD259 | [S1](#s1) | corpus/svo/svo.md | Read RD266: OCR vol. 63 img. 400–555 and vol. 65 img. 4–120 image by image with Yigdzin-1 (`hf_read.py yigdzin`, until [ -f out.txt ] for waits) searching «རྨི་ལམ་བརྟག་པ», then the Svapnohana's page by eye: the fourth-watch line, «ལ་མོ»/«བླ་མོ», «བགྲོད་མིན», «འགྲོན་བུ», «བཅུ་ཡི་བར», «ག་ཡ་ཏྲི»; likewise Advayavajra's examination (SVI) in the Co ne; q833 answered. |
| <a id="rd267"></a>RD267 | The Four Tantras' own rule of the dream by night: the lines «མཚན་སྟོད་རྨི་ལམ་བརྗེད་ངེས་འབྲས་བུ་མེད། ཐོ་རངས་མཐོང་དང་གསལ་ན་འབྲས་བུ་འབྱིན།» (RD262: found by search in the Four Tantras tradition's etexts), their chapter and commentary — done 2026-11-09: the Four Tantras' own rule of the dream by night found: the lines «མཚན་སྟོད་རྨི་ལམ་བརྗེད་ངས་འབྲས་བུ་མེད། ཐོ་རངས་མཐོང་དང་གསལ་ན་འབྲས་བུ་འབྱིན།» are Explanatory Tantra ch. 7 (lus kyi 'jig ltas), in the dream verses after the six kinds and before the good dreams (etexts: Yu thog I1KG25029 pp. 44–45, Grwa thang I1PD464 pp. 68–69, Peking-type I4839 pp. 49–50; quoted by Byang pa, Rin spungs pa, Khro ru, 'Bri gung and the 'Bum nag gsal sgron); the Buddhist etexts read «བརྗེད་ངས» (forgetfulness), «བརྗེད་ངེས» is the Bon/modern variant, ཐོ་རངས the usual spelling; the Serthang scan I3CN5038 lacks printed pp. 31–32 (foot numbers img. 42 = p. 29, 43 = 30, 45 = 33, 44 = 34, 46 = 34 again), so the leaf is not on it and the Serthang etext has none of the lines; the Drathang print GT img. 42 has them (gt:i42, machine, the first half of the lowest line by eye); commentaries on «མཚན་སྟོད»: Byang pa quoting Zla zer: the first part unclear and forgotten, a dream up to the half of the first part has its fruit far and small; the 'Bum nag gsal sgron: dreams from midnight upward are forgotten, from midnight on they give fruit; Rin spungs pa: heavy sleep presses after it, dawn is when the wind-mind enters the central channel; Khro ru: the first part is dusk and midnight alike, dawn close with no sleep after gives fruit; 'Bri gung: forgotten = delusion; the ST rule is two-way (the first part no fruit, only dawn counts), agreeing with LGD/RST that only dawn counts but naming no middle part and giving forgetting as its reason; not the Svapnohana's first-watch rule (which gives the early dream a long fruit), and ST gives no spans; only the Zla zer gradation far/small is a measure; claims st-dream-night-first-part-dawn, st-serthang-scan-pp31-32-missing, night-watches-st-rule-against-thirds-and-svapnohana | ST: BDRC full-text search for the two lines in the Gso rig 'bum bzhi, the Bdud rtsi'i chu rgyun, the 'Bum nag gsal sgron, the Gso bya lus kyi rnam bshad (which names «བཤད་རྒྱུད་ལས»); the Serthang scan I3CN5038 once the chapter is located; the commentaries ktr, bdk, bjp, rsp | RD262, RD255 | [S1](#s1) | corpus/st/st.md | Read RD267: locate the two lines in the Explanatory Tantra (chapter, printed page, scan image) by search and probe images, read them on the scan by eye, quote with the English beside; what the commentaries (bjp, rsp, ktr, bdk and the others found) say of «མཚན་སྟོད»; whether the rule is the first-watch/dawn rule of the Svapnohana and the three-thirds rule of LGD/RST; a claim in night-watches. |
| <a id="rd268"></a>RD268 | The Explanatory Tantra ch. 7's dream verses whole on the Drathang print (the Serthang scan lacks the page): the list of bad dreams the Four Tantras give as distant signs of death, against the commentaries' quotations of the list (bjp, rsp, ktr, bdk) — done 2026-11-09: the Explanatory Tantra ch. 7's dream verse whole on the Drathang print (I4CZ327131 img. 42 from the end of its first line to img. 43 line 2, 31 verse lines, from the cat, monkey and tiger to the good dreams), by eye at 3.4–10× against Yigdzin-1, MITRA and the four commentaries' lists, state machine since single syllables at 35 px could not all be verified: the bad images (each marks that the lord of death has the sick), the cause (the channel of mind yid 'jug bu ga blocked by the illness), the rule (the sick who dream them constantly die; for the healthy it is uncertain and the rite of service rim gro frees them), the six kinds, the dawn dream and the good dreams; settled by eye: མགོ་ཆག (head broken), གཤིན་པོ་དག (the dead dance), ཆང་འཐུང (beer), མི་བཟང་བ, ཁྱེར with no prefix འ; variants against the commentaries that touch the sense: the head broken (ཆག) on Drathang, Drikung and Khro ru but cut (བཅད) in Byang pa and Rin spungs pa; beer with the dead on Drathang, Khro ru, Drikung and Rin spungs pa but water and fiends (ཆུ, སྲིན་པོ) in Byang pa; a lotus drawn out (ཕྱུང) against arisen (བྱུང); limbs or skin of the legs; Rin spungs pa's མི་ཟད་པ is not on this print; the claim dream-sick-any-dream-death-sign corrected (the Four Tantras read a dream by its image list too, not only by the closing lines of ch. 7), st-dream-death-signs likewise, new claims gt-dream-death-images, gt-dream-death-rule, gt-dream-verse-variants | ST ch. 7: Drathang I4CZ327131 img. 38–48 (ch. 7 near img. 42), the commentary lists bjp:p177-181, rsp:p208-211, ktr:p303-307, bdk:p53-54 and p57; passage st:p44 and claims/st-dream-death-signs.md | RD264, RD255 | [S2](#s2) | corpus/st/st.md, corpus/gt/gt.md | Read RD268: the verses of ch. 7 that name dreams (img. 41–44 of the Drathang print, by eye at 3–5×, each syllable written down) as passages with the Tibetan and English, every dream image named with what it marks; compared with the lists the four commentaries quote (one-syllable variants as claims); the image claims of the dreamer-ill rows (dream-sick-*) corrected where the list differs; dead ends to PLAN.md. |
| <a id="rd269"></a>RD269 | The Indian dream-time verses on the page images, where RD265 read only OCR: Brahmavaivarta Kṛṣṇajanmakhaṇḍa 73.5–6 (Jivananda 1888), Negelein's Introduction pp. XIX–XX (his letters a–e), Kālaprakāśikā ch. 42 v. 66 (p. 189 of the Lawley Press scan), Bṛhadyātrā 16.10 and 16.30 (leaf 28 of Pingree's edition), Aṅgavijjā ch. 41–42 time lists, Matsya 242 and Agni 229 — done 2026-11-09: the Indian dream-time verses read on the page images (archive.org page images, scan state): Negelein's 'b' is the Svapnādhyāya of the Brahmavaivarttapurāṇa (Introduction p. XIX, whole letter list a–w read; his b 5–6 is BVP 73.5–6; his 's 2f' is really 'i 2f', Leipzig 1178; f = Leipzig 1180 'Matsya Svapnādhyāya' with both eight and six months; h = Agni 229; s = Matsya 241.2ff) (q852 answered); Kālaprakāśikā 42.66–67 (Devanagari p. 189, leaf n476): the four spans are a year, half-year, three months, a month, dawn 12 days, the same day at the cows' release (RD265's 'half a month' and the table's 'a month' for the third were wrong), v. 69 as printed says after seeing something bad one does not sleep on, left unmended; Bṛhadyātrā 16.10 gives three lists in one order (last/middle/first part of the night; rains/autumn/Mādhava; wind/bile/phlegm), the pairs are by position and ours, 16.30 'varṣapādāt' confirmed, vv. 4–32 clean; Aṅgavijjā ch. 42's time list whole on pp. 190–191 (puvvaṇha = forenoon for the bright beginning) while ch. 41's (p. 185) reads avaraṇha (afternoon), the apparatus silent so the original is not settled; Matsya 242 on pp. 493–494 of the Ānandāśrama print (fourth watch 'māsamātreṇa paśyato nātra saṃśayaḥ', second 6 months); Agni 229 on pp. 281–282, vv. 15–18a in a bracket absent from MSS ka and ṅa; BVP 73.5–7 on p. 661; also the Adbhutasāgara p. 514 (Parāśara: year, half-year, three months, month) and the Aṅgavijjā ch. 59 = the 27th paḍala pp. 260–261 (season and month lists); no older Indian text with the Brahmavaivarta/Svapnohana series found | The Internet Archive items named in corpus/{bvp,neg,kpd,bya,agv,mtp,agp}: page images fetched at full size (the archive.org page image URL of the item's leaf, `…/page/n<k>.jpg` or the BookReader) and read with the image viewer; Devanagari and Roman print is clean | RD265 | [S1](#s1) | corpus/svo/svo.md | Read RD269: each verse on the page image by eye, the OCR doubts of RD265 settled (the fourth span of the Kālaprakāśikā; Negelein's 'b'; the Bṛhadyātrā's seasons and humours; the Aṅgavijjā's bright beginning); passages from machine to scan state; the claim indian-dream-spans-by-watch-nine-texts corrected; dead ends to PLAN.md. |
| <a id="rd270"></a>RD270 | The Explanatory Tantra ch. 7's dream page on a print the pool can read: another scan of the Serthang print (the pool's I3CN5038 lacks it), the Chag-po-ri, Derge, Zhol or Lhasa prints of the Four Tantras on BDRC and archive.org, to settle the Drathang line's unsettled syllables — done 2026-11-09: the Four Tantras' ch. 7 dream page on three Chag-po-ri scans of the Lokesh Chandra Collection (MW3CN1709 / I3CN1711 img. 40, the Zungchu monastery coloured print; MW3CN1694 / I3CN1696 img. 44; MW3CN1700 / I3CN1702 img. 36, faint), about 55 px a syllable against 35 on the Drathang, by eye at 5–12×: I3CN1711 reads ལེན་པ (the slanting vowel stroke over ལ plain), རྟ་ཕག clear, རྔ་མོ moderate to good (not Khro ru's ཇ་མོ), འདེད (moderate), བྲོ་བའི (the closed box བ, moderate); I3CN1696 by eye རྟ་ཕག and རྔ་མོ, Yigdzin-1 ལེན་པ, འདེད, བྲོ་བའི; line 17 by eye on I3CN1696 is མི་བཟད་པ (moderate) which could split the prints against the Drathang's བཟང་བ, not settled on I3CN1711 (Yigdzin-1 reads བཟད་པ); I3CN1702 mostly not read; they agree with Byang pa, Khro ru and the Drikung on ལེན་པ and with Khro ru on བྲོ་བའི, against Byang pa's འདོད and བྲོ་པའི and the Drathang's machine ལན་པ; no change of sense; the Bhutan scan has no ch. 7 (it is the Instruction Tantra by content); the Serthang page still missing | ST ch. 7: BDRC work-level search for the Four Tantras (Gso rig rgyud bzhi) prints with open scans (MW3CN5036 and the other instances; the abstract work's workHasInstance), the lines «ལན་པ»/«ལེན་པ», «བྲོ་པའི»/«བྲོ་བའི», «འདེད»/«འདོད», «རྟ་ཕག», «རྔ་མོ» of gt:i42-43 | RD268 | [S2](#s2) | corpus/st/st.md, corpus/gt/gt.md | Read RD270: find the ch. 7 dream verse page in any other open print, read the five unsettled places there by eye and write each syllable down; a claim of agreement or conflict per print; dead ends to PLAN.md. |
| <a id="rd271"></a>RD271 | The unexplained words of the dream passages looked up in the Tibetan dictionaries: ཕྱྭ, ལྷས, ལྡེར, བྲོ་པའི་ཁྲལ, བག་མ་ལན་པ, རྔ་མོ, རྨེ་ཅན, རམ་རུག, སྦང, མི་ཁ, རྟ་བླ, གཏད་དུ་ཚུད་པ — done 2026-11-09: the unexplained words looked up in Steinert's 64 public dictionaries (by Wylie headword, a sparse clone; senses in our own words, nothing quoted), BDRC exact-string searches and a medical dictionary etext: ལྡེར་བཟོ a clay statue ('the temple's clay statues move', ལྡེར་སོ a known spelling); ལྷས the agent form lha yis, 'the time felt by the god' (moderate; the idiom lhas tshor against rang tshor); ཕྱྭ omen, lots, fortune, and 'mo phywa' a stock compound so ch. 91 has divination-and-omen; རྟ་བླ a centipede-like many-legged worm in creature lists beside the scorpion and spider (and rta bla mo in three Svapnohana etexts: the line names a centipede and a snake, narrowing q800, q814, q825); གཏད་དུ་ཚུད་པ 'within the aim of a sent harm', not a pledge (moderate); བྲོ་བའི་ཁྲལ་འདེད་བག་མ་ལེན་པ in nearly every witness (བྲོ་པ and ལན་པ minority spellings of the Drathang), a levy demanded, which bro stays open, ལན་པ attested as a spelling of ལེན་པ; རྔ་མོ the camel; རྨེ་ཅན a man with a black birthmark on the face (rje can a variant; the sense 'base-born butcher' not excluded); རམ་རུག no headword, in the slot of ram bu / ram pa (a plant) in rite lists, exact plant weak; སྦང་གཡེར the beer's mash (སྦང་མ) plus the Sichuan pepper (གཡེར་མ) each written short, confirmed by lists «ram bu sbang ma g.yer ma … mi kha'i rdzas»; མི་ཁའི་རྫས the substances of a rite against gossip, three words a short list not a name; q821, q822, q851, q845, q846, q857, q858, q859 answered; q804, q816, q826, q840, q841, q860 narrowed (water and fiends occur only in Byang pa's etexts, beer 7 hits, the dead 16); thirteen new claims; dream-goods-of-gossip stays unsettled for the picker | The Tibetan-Tibetan dictionaries with etexts on BDRC (Brda dkrol gser gyi me long, Bod rgya tshig mdzod chen mo, Dag yig), Jäschke, Das, Tshig mdzod chen mo; Steinert's dictionaries if reachable online; the open questions q821, q822, q840, q841, q860 and the RD268 and RD255 question files | RD268, RD255 | [S1](#s1) | corpus/st/st.md, corpus/lgd/lgd.md | Read RD271: for each word, each dictionary's headword and sense (our own words, dictionary named), whether the sense fits the passage; a claim per word; questions answered or narrowed; dead ends to PLAN.md. |
| <a id="rd272"></a>RD272 | The older Indian source of the Svapnohana's series (a year, eight months, three months, half a month, ten days at dawn): the Śabdakalpadruma's article svapna (its Vāgbhaṭa Svapnādhyāya and the Brahmavaivarta Gaṇeśakhaṇḍa 33), the Śaunaka-kārikā, the Bṛhaspati Svapnādhyāya of Negelein's manuscript i, Modak 1993's lists of Svapnādhyāya texts | Internet Archive page images and text layers of the Śabdakalpadruma (Raja Radhakanta Deva, 1886–1893, the article svapna), Negelein 1912 pp. XIX–XXI and 17–19, the manuscript catalogues named there; claims/indian-dream-spans-by-watch-nine-texts.md | RD265, RD269 | [S1](#s1) | corpus/svo/svo.md | Read RD272: the Śabdakalpadruma article svapna on the page images (the verses it quotes with their sources named), Negelein's list of manuscripts and which carry the five-span series; the earliest text carrying it, with dates as far as published; whether a Sanskrit original of the Svapnohana is named anywhere; facts in our own words; dead ends to PLAN.md. |
| <a id="rd273"></a>RD273 | The Svapnohana in the Co ne Tengyur by the Co ne's own catalogue: its number and volume, then the page | SVO: Co ne W1GS66030 (volume n = image group I1GS(66031+n)); the Co ne catalogue (the Tohoku-style concordance of the Co ne Tengyur: Hakuju Ui et al. Tohoku catalogue lists Derge; the Co ne numbers in Cordier, Lalou, the Library of Congress microfilm catalogue, the BDRC outline of W1GS66030), end of vol. 64 (I1GS66095, img. 545 on) and the end of vol. 65 and vol. 66 | RD259, RD266 | [S1](#s1) | corpus/svo/svo.md | Read RD273: find the Co ne volume and position of the Svapnohana from a catalogue or the BDRC outline (the outline of W1GS66030 gives titles per volume), then the images (one at a time, 0.5 s pause), the fourth-watch line, «ལ་མོ»/«བླ་མོ», «བགྲོད་མིན», «འགྲོན་བུ», «བཅུ་ཡི་བར», «ག་ཡ་ཏྲི» by eye; Advayavajra's examination likewise; q833 answered; dead ends to PLAN.md. |
| <a id="rd274"></a>RD274 | The dream catalogue brought in line with the readings of RD227–RD273: the image claims (claims/dream-*.md), the index claims/dream-catalogue.md, dream-unsettled-words and dream-remedies, for the picker of S2 | The claims written by RD216 and the new claims of RD227–RD273: gt-dream-death-images, gt-dream-death-rule, the st-dream-* claims, svo-rta-bla-mo-is-the-centipede, lgd-* and gt-* word claims, night-watches*, indian-dream-spans-by-watch-nine-texts, st-dream-night-first-part-dawn; the passages they cite | RD227–RD273 | [S2](#s2) | corpus/svo/svo.md | Read RD274: each image claim read against the new findings (the Four Tantras' ch. 7 image list as a dreamer-ill source above WB ch. 25; the centipede and the snake; the camel; clay statues; goods of gossip as rite substances), the verdicts and states corrected, an image added for every image of gt-dream-death-images that has no claim, the unsettled list shortened where words are settled; a table in the catalogue of watch rules by text (Svapnohana, Atharva-Veda appendix, Brahmavaivarta, Kālaprakāśikā; ST ch. 7; LGD/RST thirds; DTD fourth) for S2's night chips; every change with its reading named. |

### Left open in built work

Readings that no block waits on: each would settle or confirm a reading
already built, and is done when it is, or when the search for it is
written down as a dead end (sources/PLAN.md).

| # | What is open | Where | Built in |
| --- | --- | --- | --- |
| <a id="o1"></a>O1 | KP's links left out: box 53's (which half), box 55's numbers, box 50 (KP2 only), "skye" alone (boxes 5, 7, 20), boxes 48 and 60 — done 2026-10-12 (read on the scan in KP and KP2; the table's changes, and box 53's Tiger, Dragon and Ox days and *zon* moved to the good, are in kp-activities.md, *What the table should now carry*, for a build ([T2.28](#t2-28), block [50](#block-50)); questions 103–105) | [kp-activities.md](sources/kp-activities.md) | [T2.25](#t2-25) |
| <a id="o2"></a>O2 | Ch. 34: three entries whose verse reads two ways; whether «བཅོ་བརྒྱད་དགུ» names the 9th for marriage; which mansion «བྱ་གཞུག» is — done 2026-10-12 (read on the scan: the 18th and the 9th for marriage, the 1st and 6th, not the 16th, for learning, Friday middling, *bya gzhug* Dhaniṣṭhā in WB's usage; marriage's 9th added and learning's 16th dropped are in white-beryl-ch34.md's O2 section, for a build ([T2.28](#t2-28), block [50](#block-50)); questions 106–107) | [white-beryl-ch34.md](sources/white-beryl-ch34.md), *Left out* | T2.6 |
| <a id="o3"></a>O3 | The hair dates' 9th, «མོད་འགྱུར» — done 2026-10-12 (read on the scan in five prints, all «མོད»: "young women become many", by the dictionaries and WB's own «X མོད་འགྱུར»; SN and two Drigung lists give meeting young women; for a build ([T2.28](#t2-28), block [50](#block-50)), the catalog's `reading.HairDate.9`, now "Youthfulness", in every language; question 119) | [hair-dates.md](sources/hair-dates.md) | [T2.15](#t2-15) |
| <a id="o4"></a>O4 | Open question 18: the places WB's Mercury and Venus clause compares at an eclipse (vol. 1, p. 64); searched without result 2026-10-10 — done 2026-10-12 (the *Seven Special Qualities* restates the clause: the true sun and Mercury or Venus in one mansion, no 135, a smaller eclipse; mean places ruled out by Blo gros dpal; 135 sixtieths is one sign, WB p. 34; which place and which sun stay open, questions 116–118; the restatement's lines wait for the lent scans of KD and the Sakya volume) | [month-entries.md](sources/month-entries.md), *p. 64's two exceptions* | [T2.23](#t2-23) |
| <a id="o5"></a>O5 | Open question 1: the yoga ranking's «རང་སྐྱེས» line; no reading found; not planned | [open-questions.md](sources/open-questions.md) | — |
| <a id="o6"></a>O6 | WB's passages on the *sri*, from the etext only (the household reckoning, vol. 1 pp. 305–306, and p. 301's *rgan sri*, read on the scan 2026-10-11; p. 301's *dar sri*, pp. 244, 382, 387, 418, 425–427, 458–462 and vol. 2 left) — done 2026-10-12 (from the etext, not checked on the scan: the *dar sri* of the debt, the *chung sri* in the womb and the small grave, the *'phung sri*'s forms, the *dur sri*'s castle, the death-mouth's sri pressed down in skulls, seven new kinds in the table; in sri.md, *WB's other passages on the sri (O6)*; questions 112–115) | [sri.md](sources/sri.md) | the *sri*'s name |
| <a id="o7"></a>O7 | The weekdays' verses, by machine with corrections, not by eye syllable by syllable — done 2026-10-13 (pp. 308–312 read by eye on the scan, with WBZ img. 1058–1064 and the Gser thang reprint for the doubtful stacks; all seven verses and the closing verse quoted in full with their illness, theft, remedy and war lines; for a build ([T2.28](#t2-28), block [50](#block-50)), Tuesday's good list gains "saddling horses and mules" («སྒ་རྒྱག», the same stack as Wednesday's «རྟ་དྲེལ་སྒ་རྒྱག»; the vowel of rgyag cannot be told) in weekdays.md, *O7: what the reading by eye changes*; Wednesday's «དུར་?ོ» stays out, unsettled; questions 132–138) | [weekdays.md](sources/weekdays.md) | T2 |
| <a id="o8"></a>O8 | The date animal's earth lords, by machine with witnesses, not by eye syllable by syllable — done 2026-10-12 (pp. 223–226 read by eye on the scan, with the Gser thang reprint at 1905 px and WBZ for the doubtful stacks; every seat, hearth god and witness stands; for a build ([T2.28](#t2-28), block [50](#block-50)), the horse day's witness adds "on the journey with the corpse no horse, the funeral door and the horse's back avoided" in earth-lords.md, *O8: what the reading by eye changes*; the chief earth lord's forms and remedy and the other remedies quoted in full; questions 120–125) | [earth-lords.md](sources/earth-lords.md) | T2 |
| <a id="o9"></a>O9 | The four times of Māra's manual — done 2026-10-13: three manuals read on the scan (Rin chen gter mdzod vol. 54 p. 235, Sgrub thabs kun btus vol. 8 p. 698, Gdams ngag mdzod vol. 2 pp. 121–122): the four are midnight, noon and the rays of the rising and setting sun, moments not spans; the Red Tārā manual makes them the breaks between sessions; questions 126–131 | [weighing.md](sources/weighing.md) | [T2.4](#t2-4) |
| <a id="o10"></a>O10 | Topic files that give a passage in English only, or only its first lines — done 2026-10-14: the Tibetan of every passage read written out, checked on the scan (the owner, 2026-10-11; [sources/PLAN.md](sources/PLAN.md), *The knowledge pool*), file by file — begun 2026-10-13 in four parts, stopped at the usage budget: **done** yogas, lunar-dates, nectar-periods, karanas, lunar-day-signs (the goddesses), mansions, mansion-verses (all 28 verses whole), kun-phan-me-long, almanac-page (WB vol. 1 pp. 173–182 whole), year-of-life, reckonings (its outlines stay outlines); great-black-day needs nothing; resumed 2026-10-14 in three parts e–g: **done** (O10e, 2026-10-14) combinations (the table pp. 148–149 typed), rahu (KP's chart img. 78, its numeral square open, question 148), combination-period (Capricorn's good list, for a build in [T2.28](#t2-28)), burning-dates, day-letters, personal-mansions, hair-dates, day-animals; **done** (O10g, 2026-10-14) marriage (the match pp. 351–364 whole, the notes pp. 523–525), month-entries (the 65-year table whole; Kikang's time doubtful, D13 in [T2.28](#t2-28)), health, year-month-earth-lords; **done** (O10f, 2026-10-14) kp-activities (every box's Tibetan, the differences for a build in [T2.28](#t2-28)), white-beryl-ch33 (an inventory, nothing to write out), earth-lord-courses, earth-lords, tibetastromed, white-beryl-ch34 (works 51–65 machine reading), sri; questions 139–157; for the owner ([T2.28](#t2-28), D11): lunar date 6's "no further children" rests on a «མེད» the scan does not print, date 1 reads «ལམ་ཞུགས་རྟེན», date 3 «གང་བསྟུད» (questions 141–143) | [sources/README.md](sources/README.md)'s index | all |
| <a id="o11"></a>O11 | Open question 159: whether sel ba's long reading puts naming, harmony, glory and the company of friends among what goes badly (its lines 1–3 close on «ངན»); left out of the reading until it is read — done 2026-10-18 (question 159 answered: NM sums sel ba «…བཟང་གཞན་ངན», the first three lines go badly, the next two well, as the grammar reads; «དགྲ་ལས་མིང་འདོགས» a bad name given by enemies); the reading's lines 1–3 wait on a build | [yogas.md](sources/yogas.md), [open-questions.md](sources/open-questions.md) | [45](#block-45) |
| <a id="o12"></a>O12 | Open question 160: the birth's key pebbles by sign (WB vol. 1, p. 260, read on the Gser thang reprint): how many black the tiger starts with, and whether a tie (the dog, the ox) gives a precious pebble white, black or neither; the four precious pebbles wait on it — answered as far as the sources go 2026-10-19 (WB's next lines count from one: the tiger one black, the snake four; a tie gives a half, speckled pebble, WB vol. 2, p. 46, «ཕྱེད་མ་ཁྲ་བོར་བོར») — done 2026-10-19, as above; the four precious pebbles wait on a build | [year-of-life.md](sources/year-of-life.md), [open-questions.md](sources/open-questions.md) | [31](#block-31) |
| <a id="o13"></a>O13 | WB ch. 33's results of the date's trigram and sme ba (vol. 2, pp. 365–368) — done 2026-10-19 (read on the scan the same day): works good and bad for each of the eight and the nine, and their remedies; not built. Whether the day's page should weigh or show them beside the boxes, which already weigh the date's trigram and sme ba, is the owner's to decide | [lunar-day-signs.md](sources/lunar-day-signs.md) | — |
| <a id="o14"></a>O14 | WB ch. 35's times to beware for learning (vol. 2, p. 432) — done 2026-10-19 (read on the scan the same day): the 1st for all sciences, the 8th, 14th, 16th, 23rd, 29th, 15th and 30th for grammar (Pāṇini's adds the 13th and 28th, the Sārasvata's the 12th and 27th); ch. 34's work 52 and KP box 54 refer to them. Whether the composing-and-learning list takes grammar's dates is the owner's to decide | [white-beryl-ch34.md](sources/white-beryl-ch34.md) | — |
| <a id="o15"></a>O15 | WB ch. 34's *sme ba* of the date, compared with the boxes — done 2026-10-19: where the box names the same or WB decides against a box's numeral it is built (medicine's four-green; forts' three-blue for the box's 9; the two-black of boxes 44 and 45, read 3 before). The chapter also names, with no box numeral beside them: the five-yellow good for consecration (work 48) and bad for opening the soil (work 9), the two-black and three-blue bad for marriage (work 39), the nine-red good for life and wealth (work 61), the seven-red bad for hunting (work 20). Not joined, since the chapter's *sme ba* were left out when it was built; whether to join them is the owner's to decide | [white-beryl-ch34.md](sources/white-beryl-ch34.md), [kp-activities.md](sources/kp-activities.md) | — |
| <a id="o16"></a>O16 | Open questions 161–166, from block 31's builds of 2026-10-19: the match's hidden (which mewa per aspect, the body-cut wheel), «དཱ་ཟླ» in raising the child, Revatī among p. 348's bad birth mansions, the moment's counting order, the match's point 10 relations, point 7's parity; each waits as its question says — done 2026-10-21: 161, 162, 164 and 165 answered (the aspects' mewa from the birth mewa, the body cut's four pairs, «དྭ་ཟླ» the orphaning month, the moment's count by the month's triad and season, four-executioner and grave), 163 as far as the sources go (Revatī named twice on p. 348), 166 on 2026-10-20; the changes for a build in [T2.28](#t2-28); questions 233–239 | [open-questions.md](sources/open-questions.md), [marriage.md](sources/marriage.md), [year-of-life.md](sources/year-of-life.md), [reckonings.md](sources/reckonings.md) | [31](#block-31) |
| <a id="o17"></a>O17 | D16's worked examples — done 2026-10-20: every cell of both follows from WB's rules and the *Moonbeams*' sector ranks; the notes' table (p. 489, on the scan) fits a son meeting counted white, left out or one of each, never black (its body row would tie); Dorje's chart 6.2 fits black only; no count fits both; neither turns on the breath-taking or the womb; questions 186–187. The row asked: the notes' table (WB vol. 2, p. 489, img. 497) and Gyurme Dorje's chart 6.2 (p. 228), each cell traced to the meeting it records by WB's rules (vol. 1, pp. 380–384) and the notes' (p. 490), and each predictive pebble recomputed with a mixed place (a son's meeting; the breath-taking and womb stages) counted black, white, speckled, as one of each, or not at all; which counts give the printed verdicts, read on the scan where a cell decides | [verdicts.md](sources/verdicts.md), *D16: the worked examples (O17)* | [T2.28](#t2-28) |
| <a id="o18"></a>O18 | D16 in Dharmaśrī's *Moonbeams* — done 2026-10-20: read on DZ's scan (f. 21b–22a, img. 046–047), a son's meeting and the breath-taking and womb stages are each a half (ཕྱེད་མ), one pebble on a six-step scale from three white to two black; the verdict verse counts "how many or few the white and black" and lays "three white, two white and so on", with no tie rule; the tie «མཉམ་ན་ཕྱེད་མ» is the dead's (f. 28b); no reading of a half for the year of age; Dorje renders the half "one white and one black" and gives no reading of a mixed predictive pebble; questions 188–190. The row asked: the year of age's verdict (f. 28b, «དཀར་གསུམ་དཀར་གཉིས་…སོགས་སུ་བོར», KD vol. 3 etext p. 527) and the tie («མཉམ་ན་ཕྱེད་མ», etext p. 534), on DZ's open scans (BDRC I2533 img. 5–64): how a mixed place counts and what a speckled or half verdict foretells; Gyurme Dorje's text around chart 6.2 (pp. 226–230) beside | [verdicts.md](sources/verdicts.md), *D16: the Moonbeams (O18)* | [T2.28](#t2-28) |
| <a id="o19"></a>O19 | D16 in WB, the notes and other manuals — done 2026-10-20: WB lays a son meeting as a pair (the Zhol print «དཀར་ནག་ཟུང»), and its pp. 381–384 readings follow the six meeting counts, "white and black, two" the pair, named "the ordinary verdict" (p. 384); the predictive readings white and black only. WB's illness chapter reads each aspect's speckled verdict (p. 422), undecided, with rites. Mipham's notes (p. 495, on the scan) lay a son meeting as one speckled pebble, uncounted for either colour, "roughly" with the bad, "somewhat weak". The *Good Vase* reads a tie by the "white and black, two" reading, the Drukpa *Mirror* prints those readings as the speckled verdict's, Zhechen Gyaltsab lays the son speckled; questions 191–196 (the row's "notes' pp. 496, 507" are KD vol. 3's). The row asked: a reading for a speckled or half predictive pebble of the year of age (WB vol. 1, pp. 380–385, img. 390–395; the notes' section 2, vol. 2, pp. 489–495), how a mixed place counts wherever the notes lay verdicts (pp. 496, 500–503, 507, img. 504, 508–511, 515), and BDRC's full-text search for other obstacle-reckoning manuals' rule (bdrc_search.py: «ཁ་དམར་རྡེལ», «ཕྱེད་མ», «ཁྲ་བོ» with «ཀེག་རྩིས», «བུ་འཕྲད»), on the scan | [verdicts.md](sources/verdicts.md), *D16: WB, the notes and the manuals (O19)* | [T2.28](#t2-28) |
| <a id="o20"></a>O20 | The dead's pages read on 2026-10-23 without MITRA and not syllable by syllable on the scan: WB vol. 2, pp. 97–103 (RD34, the mewa's and trigram's roads, the doors, the hours, the bearer), pp. 104–126 (RD35, the thirteenth examination, all 19 passages machine state) and pp. 133–141 (RD36); MITRA over the 1996 images, `disagree.py` and a pass on the Gser thang reprint (I3CN5029, image = page + 6) would bring them to the scan state — done 2026-10-23: pp. 97–117 and 133–142 and the notes' pp. 504–512, 519–520 read on the Gser thang reprint line by line and set to the scan state, with a line Yigdzin-1 dropped on p. 99 restored, the mantras of pp. 111–113 written out (the wheels' seed syllables blotted), «སྒྱེད་པོ» the hearth-stones, the mewas 5 and 8 both north-east (question 295), the snowcock of p. 136; pp. 118–126 left machine-read (O21); questions 333–340 | [corpus/wb/ch29-remains.md](sources/corpus/wb/ch29-remains.md), [corpus/wb/ch29-rites.md](sources/corpus/wb/ch29-rites.md), [corpus/wb/ch29-grave.md](sources/corpus/wb/ch29-grave.md) | — |
| <a id="o21"></a>O21 | WB vol. 2, pp. 118–126 of the dead's thirteenth examination (the rites by date, planet, mansion and clan, the white protection), left machine-read by O20 when its readers hit the usage limit: a line-by-line pass on the Gser thang reprint (I3CN5029 img. 124–132) would bring the seven passages to the scan state — done 2026-10-23: read the same day by O20's last parts (f5d0f8d): pp. 118–126 line by line on the reprint, every passage of ch29-rites now on the scan; question 370 | [corpus/wb/ch29-rites.md](sources/corpus/wb/ch29-rites.md) | — |

## 旧暦 page

### <a id="k"></a>K. The expert's review of 2026-10-12

The Russian dossier of 2026-09-30 on the 旧暦 page (built by `tools/pdf/`,
gitignored: the page as built and planned, seventeen questions, the whole
glossary) came back from a Japanologist on 2026-10-12, annotated by hand
from memory; her answers, notes and counter-questions are in
[sources/kyureki.md](sources/kyureki.md), *The expert's review*. Two
things it settled at once: the page's purpose (SPEC §7: an almanac on the
old rules and readings for a present-day practitioner of onmyōdō, Shinto
or Shingon, not the modern printed almanac; the owner, 2026-10-12), and
that the page's refusals stand (no whole-day colour, no weighing, no
verdict, no free readings: her ticks). Nearly everything else she marked
"needs checking in the sources", so the items below are mostly readings.
They are kyūreki readings, kept apart from the *Readings* table above,
whose test expects Tibetan script in a done reading's topic file; a K
reading is done when [sources/kyureki.md](sources/kyureki.md) holds it
with edition, page and image number, as the Tibetan ones are. Nothing in
the app changes until its item is built.

Her second answer (2026-10-14, [sources/kyureki.md](sources/kyureki.md),
*Her second answer*) set the frame: a reconstruction of "the old rules"
has no bound, since the annotations that ever existed are far more than
any living practice and some are reckoned otherwise today, so the page's
frame is the set of annotations the traditional almanac printed today
carries (the 神宮館高島暦 and its kind), which is what a present-day
practitioner reads; the old texts give the readings within it (SPEC §7).
The shrines' own 神宮暦 carries no luck annotation at all, and no shrine
calendar is online; the app's set already matches the printed one, so
what is left on the page is the terms (K1, K2), the claims (K4), the
direction gods (K5) and a check against a paper almanac (D12).

#### <a id="k1"></a>K1 The Russian terms by the scholarly literature — M, open-ended

- **Gap.** The glossary's Russian renderings were made for the app. She
  marks as having established translations, or none: the month names
  («исправить потом») and the 二十四節気 («тоже»), the 二十八宿 (the
  sinologists' established names), 暦注下段, 選日 and 縁日 and their days
  (not found in Russian at all, «нужна сверка»), 暦注 itself
  («календарные примечания», not «пометки альманаха»), 仏滅 («смерть
  Будды»), and the dossier's band names of question 14 («двенадцать
  станций», «лунная стоянка», «нижняя полоса», «избранные дни», «дни
  божеств», «сезонные вехи», «принятие смерти», «нисходящий пожиратель
  года», «Тэнъити на небесах»), of which «some have established
  translations», unnamed.
- **Work.** A reading first: the established Russian names from
  published Japanology and Sinology (dictionaries and monographs on the
  Japanese calendar, the Chinese mansions, the 節気), each term with the
  work it comes from, into a table in kyureki.md; a term with no
  attested name keeps the app's and says so. Then
  `texts_ru.properties` (`Festival.*`, the 旧暦 readings' names), the
  interface strings that name a band (`about_rekichu`), the dossier's
  glossary (`tools/pdf/`), and the store listing where it names a term.
  The expert may be asked to name the works (her offer: the terms need
  checking «в т.ч. по научным работам на русском языке»).
- **Read 2026-10-15** ([sources/kyureki.md](sources/kyureki.md), *K1*):
  the table of attested names, from БЯРС (via WaRoDai, CC BY-NC-ND: the
  fact of a name only), Вяткин's *Ши цзи* vol. IV (the one full set of the
  28 mansions), Еремеев and Агеев 2012 (the 24 terms) and a dozen articles.
  For the build: 神無月 is printed «месяц без богов», not the app's
  «месяц богов»; 閏月 is «вставной месяц» (the year alone «високосный»);
  穀雨, 小満, 処暑, 芒種 have attested names other than the app's; thirteen
  mansions have Вяткин's names where the app's are found nowhere (房, 箕,
  危, 室, 奎, 婁, 昴, 畢, 觜, 参, 鬼, 張, 翼); 人日 only as «дзиндзицу»,
  重陽 as «праздник хризантем» (the expert's «двойной девятки» not in print);
  no Russian name at all for 暦注, 下段, 中段/十二直, 選日, 雑節, 社日 and
  the named 下段 days, which keep the app's. Which of several names in use
  to take (the months «N-я луна» or «N-й месяц»; Вяткин's mansions) is the
  owner's word, with the section's five questions for the expert.
- **Built 2026-10-19** (block 51) but for D15, the owner's choices:
  where the table names a term clearly, the app takes the name.
  `KyurekiMonth.10` «месяц без богов»; `month_leap` «Вставной N-й месяц»,
  with «вставной месяц» in `about_kyureki` and the Russian store listing;
  `SolarTerm.KOKUU` «дожди для злаков» (БЯРС, Агеев), `SHOMAN` «малая
  полнота» (БЯРС, Еремеев), `SHOSHO_HEAT` «окончание жары» (БЯРС),
  `SHOSETSU` and `TAISETSU` «малые снега», «большие снега» (Еремеев,
  Агеев; the pair kept alike), with the 大雪 reading's «малым снегам»;
  `section_ennichi` «縁日 · Храмовые праздники»; `Festival.人日の節句`
  «Дзиндзицу», as 七夕 is «Танабата»; the 仏滅 reading's literal sense
  «кончина Будды». Kept: 芒種 «колошение» (Агеев's short form), 啓蟄 and
  清明 (each attested), 半夏生 (the dictionary has the transcription alone,
  for another plant), the twelve month names (none translated in print),
  暦注 and the bands (no work names them). The English «month of the
  gods» for 神無月 reads 無 as の and is left. Checked: `:core:test`,
  `:app:testDebugUnitTest`, the emulator in Russian (the term sheets of
  小満, 小雪 and 処暑, the 10th month's balloon on 25 November 2026, the
  leap 6th month on 20 August 2025, 人日 on 23 February 2026, the 縁日
  band), the website's Russian page.
- **Block.** [51](#block-51), from the table. [15](#block-15)
  (L3) waits on it for the 旧暦 page.
- **Done when.** The table in kyureki.md; `CatalogTest`; the owner's
  read-through of the Russian (L3, block 15), which waits on this for the
  旧暦 page.

#### <a id="k2"></a>K2 The festivals' names — built 2026-10-14

- **Her corrections.** 七夕: «Танабата» (the gloss «вечер седьмого дня»
  struck out); 重陽の節句: «праздник двойной девятки», not «двойного ян»;
  旧正月: «лунный Новый год»; 彼岸の中日: struck out, the day is simply
  called 秋分 or 春分. Marked "?" without a proposal: 人日の節句 («праздник
  дня людей»), 社日 («день божества земли»).
- **Built** (the owner's word, 2026-10-14): `Festival.七夕` «Танабата»,
  `Festival.重陽の節句` «праздник двойной девятки», `Festival.旧正月`
  «лунный Новый год» in `texts_ru.properties` («лунный» taken as the
  Russian usage, though the calendar is lunisolar); the 雑節 entry
  彼岸の中日 dropped in both languages (`Zassetsu.HIGAN_CHUNICHI` gone;
  the equinox day is now a 彼岸 day like its neighbours, and the 節気 row
  shows the equinox), so nothing repeats on the page. 人日の節句 and 社日
  keep their names until K1's table of attested terms. The dossier's
  glossary is built from the catalog, so it follows.
- **Block.** Done; [51](#block-51) is K1's alone.

#### <a id="k3"></a>K3 The old texts behind the readings — a reading, then D8; built 2026-10-31 (SPEC §7.5, §7.6, §8.1)

- **Gap.** The frame is settled (SPEC §7, her second answer of
  2026-10-14): the printed almanac's set of annotations, which the app
  already shows. What the old texts are for is the readings and the
  corrections of the modern digests (Japanese Wikipedia after 岡田・阿久根
  1993, koyomi8, Todan's こよみ博物館, the NAOJ), which are all the rules rest
  on today; the one old text read on its scan is the 簠簋内伝 (三箇の悪日).
  The Tibetan page has the White Beryl above all (SPEC §8.1); the 旧暦
  page names no such text. Her advice: the 簠簋内伝 is better not taken as
  a source, the 暦林問答集 can be relied on.
- **Work.** A reading, K3-R: the 暦林問答集, identified in the NDL catalogue
  when the reading starts (edition, author, date never from memory) and
  read on its scan: what it says of each annotation the app shows (and
  only those: an annotation the printed almanac has dropped is not
  revived from it), its three bad days against the 簠簋内伝's table, and
  whether it ranks the annotations (none expected: question 6). Then the
  owner decides the order among the texts — the 暦林問答集 first, the
  簠簋内伝 as a witness or a named other view, the modern digests where
  the old texts are silent — and SPEC §8.1 gets the 旧暦 rule beside
  WB's. Until then the 簠簋内伝 stays the 三箇の悪日's source, named as its
  own.
- **K3-R read 2026-10-15** ([sources/kyureki.md](sources/kyureki.md),
  *K3-R*): 賀茂在方's 『暦林問答集』 (preface 應永甲午, 1414; 64 questions), the
  群書類從 woodblock (NDL pid 14488380) with the manuscript ちニ-5 (pid
  2533201) as witness. What it gives for the annotations the app shows:
  the 十二直's old good-lists (wider than the app's modern ones; 破 has
  good works); 歳徳 by the year's stem, the modern directions; 天恩, 天赦,
  帰忌, 往亡, 重, 復, 母倉 agreeing with the app's tables but 母倉's autumn
  (丑未 only, the app 丑辰未戌); 歳下食 light, used where stem and branch
  are good; 天一's whole course; 日遊 in the house 癸巳–己酉 and on 戊/己
  days, **"do not sweep the house"**, against the app's reading «keep it
  clean» (`reading.Senjitsu.TENICHI_TENJO`, both languages): a correction
  for this block; 土用 "do not move earth"; 社日 the 戊 day near the
  equinox with no tie rule; the 二十八宿 only as the 宿曜経's seven classes;
  the 三箇悪日 as the 宿曜経's monthly days with **no table and no birth
  year**, binding Buddhist matters only. Its one precedence statement (§64):
  the 宿曜経's days bind only Buddhist matters, their good days count only
  where stem and branch are good. Silent on the 六曜, the day's 九星 and
  most 選日. D8 now has what it needs; the year's direction gods (§6,
  §8–14, img. 11–16) are located for K5, not read.
- **D8 settled 2026-10-30** (*Owner decisions* below): the 暦林問答集
  first, the personal 三箇の悪日 kept from the 簠簋内伝, §64's ranking
  applied, the printed almanac's days with the old text's wording. **To
  build**: SPEC §8.1 the 旧暦 rule beside WB's (the 暦林問答集 first, the
  digests where it is silent, the 簠簋内伝 for the 三箇の悪日 alone);
  §7.5 and §7.6 the §64 ranking, the 二十八宿's (and 甘露's, 金剛峯's, if
  shown) good side counting only on a day whose stem-and-branch
  annotations are good, with what counts as "stem and branch good"
  stated as a choice; `reading.Senjitsu.TENICHI_TENJO`'s 日遊 line «do not
  sweep the house» in both languages; each reading the 暦林問答集 covers
  checked against it and its source line naming it. The 十二直's lists
  and the solar terms' voice wait on D8's two open points.
- **Block.** [53](#block-53), built 2026-10-31.
- **Built 2026-10-31** (block 53). SPEC §8.1 has the 旧暦 page's order of
  texts. `Sources.RYAKURIN` is the source of every reading the 暦林問答集
  covers, the digest named after it: 天赦日, 天恩日, 母倉日 (now every
  work good, «舉百事吉»), 帰忌日 (lending money dropped), 血忌日 (hunting
  dropped), 往亡日 (journeys and an army's setting out only), 重日 and
  復日 (funerals, not weddings), 歳下食, 天一天上 (the new work «sweeping
  the house», `Activity.SWEEPING_HOUSE` in place of «an unclean house»),
  土用, 社日 and the 恵方, each reworded from the old text in both
  languages; the 三箇の悪日 take the 簠簋内伝 as their source and name every
  work to avoid, 大禍日 too. §64: `RekichuDay.goodByStemAndBranch` (a good
  lower-band or 選日 annotation other than 歳下食 and no bad one, the rule
  歳下食 already used, stated as a choice) and `DaySummary.held`, the
  二十八宿 whose good list does not count today; the breakdown's "Good side
  not counted" block, the mansion row's subtitle and detail. About names
  the 暦林問答集 and the 簠簋内伝. `KyurekiElectionTest`'s vector redone
  (8/24 and 8/30 now good, 婁宿 and 胃宿 held). Left: D8's open points,
  the 十二直's lists and the solar terms' voice, and the 二十八宿's lists,
  the later almanac's.

#### <a id="k4"></a>K4 Claims to settle on the sources — S each

Each a reading into kyureki.md, each closing when found or written down
as a dead end (sources/PLAN.md).

- **二十七宿 in Japan.** She: never applied in Japan, the 27 are Indian.
  The NAOJ's 暦Wiki 二十八宿: the 28 were adopted as 暦注 from the Jōkyō
  calendar in place of the 27 (quoted in kyureki.md, *Weighing*), so the
  27 were in the almanac before 1685. Settle which, and whether the page
  says anything of it (question 4); with it the claim of SPEC §10.4 that
  the 具注暦's red writing marks the 二十八宿 and 七曜, which she queried.
- **天一神's course.** The sixteen days in heaven are computed (癸巳–戊申);
  his directions on the other forty-four days are not. Check the course
  against a source (question 15); whether the directions are shown is
  K5's. Found 2026-10-15: the 暦林問答集 §53 (GR img. 49–50)
  gives the whole course (己酉 north-east 6 days, 乙卯 east 5, … 戊子 north
  5, then 16 days in heaven from 癸巳), kyureki.md *K3-R*.
- **天一天上 as printed today.** She (2026-10-14): 天一 is among the
  archaic annotations no longer used. koyomi8 lists 天一天上 among the
  選日 of the present almanac; rekichu.com printed none on 2026-10-14; the
  高島暦's table of contents does not name it. Settle on a paper 高島暦
  (D12) whether its day column prints 天一天上; if not, the row leaves the
  frame (SPEC §7) and stays only as a sourced reading behind the 干支, or
  goes.
- **歳徳神 as reckoned today.** She (2026-10-14): reckoned otherwise today
  than in the fourteenth century. The 暦林問答集 (1414) gives the modern directions by
  the stem, 戊・癸 to the centre placed at 丙 (kyureki.md *K3-R*); which
  difference she meant is asked there. The app takes the 恵方 from the year's
  stem as the modern almanacs do (SPEC §7.5). Find what the old reckoning
  was (K3-R may say; else ask her for the text) and whether the 恵方 row
  should name the modern rule as modern.
- **本命星 ≠ 九星.** The 九星気学 reading says the 本命星 is the birth year's
  star (SPEC §7.5). Ask her what she separates (the 本命星 from the 月命星;
  気学's stars from the almanac's day star) and fix the reading if it
  conflates them (question 11).
- **六曜 since the 19th century**, not Shinto: once sourced, the 六曜
  reading may say so (question 13).
- **Her "needs searching"**, each a reading when a source turns up:
  day marks other than 黒日 and 万よし (question 5), 神吉日 and 凶会日
  tables (question 8), the 社日 tie (question 10), the tones of the
  間日, 重日, 復日, 庚申 and the 一粒万倍日 caveat (question 16), the year
  boundary of the year's 干支 (question 3: China counts from 旧正月, as
  the app does). Question 2 is answered since the dossier (kyureki.md,
  *The personal reading of 三箇の悪日*), subject to K3's view of the 簠簋内伝.

- **Built 2026-10-19** (block 52, the three claims a reading closed;
  kyureki.md, *K4: three claims settled*): **二十七宿**, NAOJ's 暦Wiki has
  them the almanac's day mansions from the 宿曜経 until the Jōkyō
  calendar, so the expert's "never applied in Japan" does not hold for
  the almanac; the page shows the 28 and says nothing of the 27, so
  nothing changes. **The red writing**, SPEC §10.4 now says what a
  source shows: the weekday in red in the upper band of the 具注暦 of 1210,
  密 on Sundays (作花一志), and no longer the 二十八宿. **六曜**, a family
  note `reading.Rokuyo` in both languages: the present six first found in
  1747, one annotation among many in the Edo period, weightier after the
  luck annotations were struck in 1873 (Japanese Wikipedia 六曜, an own
  summary; SPEC §7). Checked: `:core:test`, `:app:testDebugUnitTest`, the
  六曜 sheet on the emulator in English and Russian. Left: the 本命星
  (question 11, the expert's), 天一天上 and 歳徳神 (D12, the paper
  almanac), "her needs searching".
- **Block.** [52](#block-52), a claim at a time as its reading closes
  (the 本命星 reading, the 六曜 line, SPEC §10.4's red writing), as
  [31](#block-31) builds the reckonings one by one.

#### <a id="k5"></a>K5 The direction gods — the year sheet built 2026-10-31 (SPEC §7.5, §10.4); 金神 and the daily directions the owner's decision

- **Her counter-question.** Which direction gods beyond 天一神 and 歳徳神
  does the app compute or plan? None: 天一天上 and 恵方 are all it has, no
  八将神 (太歳, 大将軍, 太陰, 歳刑, 歳破, 歳殺, 黄幡, 豹尾), no 金神, no
  日遊神's course, no 方違え. She notes that the modern almanac reckons
  them by the year and puts them on its first page.
- **Work, if wanted.** A year's direction sheet from the 恵方 row: the
  year's 方位神 by the year's stem and branch, each with its direction and
  what is avoided towards it, from an old text (K3's) — usable by a
  practitioner for 方忌み, so within the rule of 2026-10-10 that nothing
  is built that cannot be used, and within the frame of 2026-10-14: the
  printed almanac opens with the year's 方位吉凶図 and explains the 八将神
  and 金神 (the 高島暦's table of contents), so the year sheet is the one
  part of the printed set the page lacks. Daily 方位 (天一神's course, 日遊神) only
  if K4 finds a source.
- **D9 settled 2026-10-30** but for 金神 and the daily directions: the
  year sheet. Three readings before the build, into kyureki.md:
  [RD92](#rd92) the 暦林問答集's eight gods (§6, §8–§14), the sheet's
  text; [RD93](#rd93) 金神's old source, if any; [RD94](#rd94) 天一神's
  and 日遊神's daily directions as an old text and the printed almanac
  give them. The owner decides 金神 and the daily rows on their reports.
- **Block.** [54](#block-54), after [53](#block-53) (the old text's
  order) and RD92; 金神 and the daily rows join it when RD93 and RD94
  are reported and decided.
- **Built 2026-10-31** (block 54): `YearGods` (core) gives the eight by
  the year's branch from 立春, the 暦林問答集's rules where it has them and
  the printed almanac's table (Japanese Wikipedia「八将神」) where it gives
  only the range, the 歳刑 as the almanac's one branch, the 大将軍's
  quarter and the 歳破's weight; `YearGodsTest` holds the twelve years
  against the table. `Texts.YEAR_GOD`, eight readings in the shape from
  the 暦林問答集 in both languages, the star as *Also called*. The 恵方 row
  opens the year's directions: the compass of the twelve branches with
  the gods' marks, the quarter's arc and the 恵方's needle, then the
  恵方 and the eight rows (SPEC §10.4). Font subset rebuilt (the gods'
  kanji, and 78 characters missing since earlier blocks). Checked on the
  emulator in English and Russian, at font scale 2.0 and in landscape.
- **The 大将軍's outings decided and built 2026-11-06** (the owner: a
  daily row, by the 暦林問答集; block 67): `Daishogun`, §54's five-day
  outings with MS's 丙子, its own place from `YearGods` on the other days,
  under 天一神's row. The 1937 book's six days set aside. Font subset
  rebuilt for 宮. Checked: `DaishogunTest`, `CatalogTest`, the emulator in
  English and Russian on an outing, inside the house and at home.
- **D9's open points decided 2026-10-31** (the owner, on RD93 and RD94):
  "show" 金神 from the 簠簋内伝, and "add" the daily 天一 row. **Built the
  same day**: `YearGods.konjin`, §5's four directions by the year's stem,
  the sheet's last row with 金 on the compass, its reading with the
  wanderings (§7, §8) and the free days (§9) as rows, nothing reckoned by
  the day; `Tenichi`, §53's course, a row under the 恵方's with the day's
  direction and the stay's first and last day, 天上 on the sixteen days
  (SPEC §7.5, §10.4; `YearGodsTest`, `TenichiTest`; new rows *Wanders*
  and *Free days*). No 日遊神 row: no Japanese course outside the house
  (question 493). Checked on the emulator in English and Russian, at
  font scale 2.0 and in landscape; fonts rebuilt (680 characters).

#### <a id="k6"></a>K6 Every 旧暦 row opens — planned 2026-10-14, built 2026-10-18 (SPEC §10.4)

- **Gap.** The Tibetan page gives every line a reading, or at least a
  balloon of facts, on tap (SPEC §10.3); the owner (2026-10-14): the 旧暦
  page must do the same. Inventoried on the code of 2026-10-14
  (`KyurekiPage.kt`): the festival, the month line, the 六曜, the black
  and pardon days, the brief, the 中段, 二十八宿, 下段, 選日, 縁日 and 雑節
  rows, the day star, the 気学 relation, the 恵方 and the sky line all
  open. These do not:
  1. **The solar term row** (the framed box under the day's number: the
     small ring, `TermRing` at 48 dp, and «秋分 · until 8 Oct»): not
     tappable; its kanji gives the English only. No `reading.SolarTerm.*`
     exists in the catalog. The owner (2026-10-16): no reading, no
     details and no full ring, where a Tibetan row opens to all three —
     the reading, the details of how the entry was worked out
     (`Annotation.details`) and, for a cycle, the whole ring at sheet size
     (the mansion, yoga and karana rings at 280 dp).
  2. **The 干支 rows** — the day's, the year's and the solar month's:
     `FactRow`s with no `onClick` and no balloon; the glyphs give the
     element and animal, nothing says what they are.
  3. **The month star and the year star**: inside the day star's row,
     whose sheet is the day star's alone; `Texts.KYUSEI` has the readings,
     but the two stars cannot be opened.
  4. **The day number and the moon glyph**: the glyph's phase name goes
     to screen readers only; the number opens nothing. The printed
     almanac prints the moon's age (月齢) beside the 旧暦 date; the app
     computes the elongation and shows no number.
  The hours (十二時辰 with 時下食, SPEC §3) and a year sheet (K5) are
  not rows and stay where they are planned.
- **Work.** In the order of the page: (1) the whole framed box opens a
  sheet for the term, with the three parts a Tibetan sheet has.
  *The reading*: what the term marks and what hangs on it — the 節月 it
  opens with the 三箇の悪日, 土用 before each 立, 彼岸 and 社日 at the
  equinoxes — 24 texts in both languages from a sourced reading, K6-R,
  into kyureki.md (the NAOJ 暦Wiki and koyomi8 for the astronomy, the old
  texts of K3 for the readings; no 七十二候, dropped by the owner on
  2026-10-10). *The details*, computed, as label and value: 節 or 中, the
  sun's longitude, the day it began and the day it ends this year, the
  節月 it opens or falls in, the next term and its date. *The full ring*:
  `TermRing` drawn at sheet size in a `DiagramFrame`, the 24 terms named
  round it in kanji, the 節 and 中 told apart, the present term marked
  and today's place within it, the 土用 and equinox arcs as on the small
  ring; its own content description naming the term. (2) A balloon on each 干支 row: the stem's element and
  polarity, the branch's animal, the pair's place in the sixty (day and
  year); the solar month's 節 start and end and that the personal bad
  days are counted by it. Facts the catalog already names, no new
  reading. (3) The month and year stars each open their own 九星 sheet
  with the board set to that star, the subtitle saying month or year.
  (4) A balloon on the date and moon: the moon's age in days, the phase
  name, the month's length (大 or 小) and the Gregorian date; facts only.
  Nothing new is weighed and no verdict changes (SPEC §7.5).
- **K6-R read 2026-10-15** ([sources/kyureki.md](sources/kyureki.md),
  *K6-R*): each of the 24 with 節 or 中, longitude, JST dates (the app's
  engine, 2000–2099), what it marks (NAOJ, koyomi8), the gloss of the
  『こよみ便覧』 (1787, NDL pid 2536637) and of the 『暦林問答集』 (群書類從
  print, pid 14488380, with its direction and element), the 節月 it opens
  with that month's personal bad days, 往亡日 and 天赦日, and what hangs on
  it (土用, 彼岸, 社日, 八十八夜, 入梅, 半夏生, 節分, the year at 立春). The
  社日 tie (her question 10): the app's nearer-to-noon rule is the NAOJ's,
  from the 明治14 almanac on. Which old voice the readings follow (KB or
  RM) is D8's; four doubtful readings of the print wait on the manuscript
  (pid 2533201).
- **Block.** [56](#block-56), from K6-R; [53](#block-53) may
  revise the term readings' wording, not their presence.
- **Built 2026-10-18** (block 56). `Texts.SOLAR_TERM`, 24 readings in the
  shape of SPEC §8.1 in both languages, each with its month's name as
  *Also called*: what the term marks after the こよみ便覧's gloss (KB) and
  the NAOJ, the month it opens or names, what hangs on it. KB speaks, RM
  does not: its directions and elements wait on D8 with the old texts'
  order (block 53 may reword). `CatalogTest` holds the family to the
  shape as item K6, with no allowance. The sheet's details and
  `TermRingDetail` as SPEC §10.4 says; `Kyureki.moonAge` (noon JST,
  tested against the NAOJ's new moons of 2026–2027) and
  `Kyureki.Month.days` for the date's balloon; the 干支 balloons through
  `FactRow`'s rows for a kanji term; the month and year stars their own
  sheets. About & sources names the こよみ便覧, the NAOJ's 二十四節気 and
  koyomi8. Checked on the emulator in English and Russian on 18 Oct 2026
  (寒露, a 節) and 25 Oct (霜降, a 中): every new row and sheet tapped and
  dumped, at font scale 2.0 (the date balloon's labels shortened so that
  its values do not break letter by letter) and in landscape. The kanji
  shown are all in the font subset; no rebuild. The review dossier (`tools/pdf/`, kept out of git) has the 24 terms'
  readings and *Also called* rows in its glossary, 節 or 中 beside each,
  and its feature notes describe the term's sheet, the date and moon's
  balloon, the 干支 balloons and the stars' sheets; rebuilt as
  `kyureki-review-2026-10-25.ru.pdf`.
- **Done when.** Every row of the page opens on the emulator in both
  languages (a tour that taps each row and dumps the sheet or balloon),
  the term's sheet with its reading, details and full ring, legible at
  the largest font (A2); `CatalogTest` for the 24 readings in the shape of SPEC §8.1; SPEC §10.4;
  the dossier's glossary.

#### Owner decisions

- **D8 The sources' order for the 旧暦 page** (K3) — settled
  2026-10-30 but for two points (the owner, on K3-R): the frame stays the
  printed almanac's set (2026-10-14, SPEC §7), and within it:
  1. **The 暦林問答集 speaks first**; the modern digests (koyomi8, the
     NAOJ, Japanese Wikipedia) only where it is silent: the 六曜, the
     day's 九星, most 選日. One voice per reading, no *Another view*
     (2026-10-16).
  2. **The personal 三箇の悪日 stay as they are**: the 簠簋内伝's table by
     the birth-year branch and the solar month, read "everything to
     avoid", its source named as its own. The 暦林問答集's monthly days,
     binding only the Three Jewels' matters (§61, §64), are not shown.
  3. **§64's ranking applies**: the 宿曜経's annotations (the 二十八宿,
     the 七曜, 甘露, 金剛峯) stand below the stem-and-branch reckoning;
     their good days count only where stem and branch are good too.
     SPEC §7.5's "no published rule ranks one kind above another" and
     §7.6's election change with it. The 三箇の悪日, by point 2, are the
     one exception: the 簠簋内伝's reckoning, not the 宿曜経's monthly days.
  4. **Days as the printed almanac reckons them, wording from the old
     text**: 母倉 keeps 丑辰未戌 in autumn (the 暦林問答集 has 丑未); 日遊's
     reading becomes the 暦林問答集's "do not sweep the house" in place of
     «keep it clean».
  Open: whether the 十二直's good-lists follow the 暦林問答集's wider ones
  (破 with good works; they feed the election) or the modern ones the
  app has; and for the solar terms (K6) whether KB (1787) or RM (1414)
  speaks first, and whether RM's direction for each term belongs in a
  reading.
- **D9 The direction gods** (K5) — settled 2026-10-30 but for two points
  (the owner): **a year sheet**, opened from the 恵方 row, of the year's
  八将神 by its stem and branch, each with its direction and what is
  avoided towards it, as the printed almanac's 方位吉凶図. Not settled
  until their readings report: **金神** (RD93; the 暦林問答集's contents
  do not name it) and **the daily directions**, 天一神's and 日遊神's
  (RD94). The year sheet is built (block 54, 2026-10-31); both
  readings reported the same day, their questions under K5's *Built*.
- **D10 What to ask the expert to work on first**, her question before
  pricing — settled 2026-10-14: nothing is commissioned; the remaining
  points (K1, K2, K4, K5) are investigated here, on the sources and the
  paper almanac (D12). The answers to her four questions stay drafted in
  kyureki.md, *Her questions, and the answers*, and her second answer
  narrowed the field: the rare annotations drop out unless the printed
  almanac carries them.
- **D12 The reference almanac**: a paper 神宮館高島暦 令和8年 (about
  2,420 yen; no free online edition, and the shrines' 神宮暦 carries no
  annotation) to check the page's set and its days against — the two K4
  claims of 2026-10-14 and the 選日 and 下段 rows wait on it — open, the
  owner's purchase.
- **D15 The Russian names K1 leaves to choose** (block 51) — open: the
  numbered months «N-я луна» (the translators of old texts) or «N-й
  месяц» (БЯРС, Садокова; the app now); Вяткин's names for the thirteen
  mansions the app names otherwise («Дом» for 房, «Корзина» for 箕,
  «Кровля» for 危, «Дом» again for 室, «Скипетр», «Оковы», «Утиное гнездо»,
  «Вилы», «Морда», «Заслуги», «Духи», «Натянутый лук», «Крыло»), his set
  being the only one read and naming 房 and 室 alike; 重陽の節句 «праздник
  двойной девятки» (the expert, K2) or «праздник хризантем» (three works
  in print).

## Tibetan page

### T2. The Tibetan page's gaps: work plan

The page's readings are built (SPEC §5.8–5.13, the texts in
[sources/](sources/README.md)) and all fifteen questions for
a reader are answered ([open-questions.md](sources/open-questions.md)). Reviewed on
2026-10-06, what is left are places where the page contradicts itself,
shows a factor it does not weigh, or rests on a thin or machine-read
source. Numbers are over 2000–2049 (18,263 days) on the code of that day.
How to read the sources is in [sources/PLAN.md](sources/PLAN.md); every
reading task here ends with its facts in a topic file, with edition, page
and image number.

Each item is done when its code has tests, SPEC is changed with it, both
pages and the sheets it touches are checked on the emulator in English and
Russian, and the release build is checked before the next tag (SPEC §12).

#### T2.1 The haircut row, weighed — built 2026-10-06 (SPEC §10.3)

#### T2.2 Rāhu's compass — built 2026-10-06 (SPEC §10.7)

#### T2.3 The person's own days — built 2026-10-06 (SPEC §5.12, §10.3)

The brief's "For you" block, shown and not weighed; WB pp. 337–338 and
345–346 read into [personal-mansions.md](sources/personal-mansions.md). WB
calls a person's own days "of particular importance" but ranks them
against nothing, so the weighing stays as it is. Open question 14,
answered the same evening and checked on the scans of four works: WB's
tables by element are by the clan's element, which the app does not
have, or, "applied the same way", by the birth year's life force, which
it has; the birth weekday is the weekday of birth, and the birth mansion
the mansion the moon passes through on the date of birth (Phug pa Lhun
grub rgya mtsho). All three follow from the birth date. The mansion of
conception has no definition in any source found and is not to be built.
Showing the rest in "For you" is the owner's decision.

#### <a id="t2-4"></a>T2.4 The hours above the day — S built 2026-10-07 (SPEC §10.3), then M, built 2026-10-10

- **Gap.** The texts hold the combination period above every factor of
  the day (KP rule 5, WB vol. 2, p. 376), and KP's rule 2 puts the hour
  (*dus tshod*), "a sharp weapon", above the day's animal sign. The hours
  panel shows the combination period and the nectar periods, but the
  brief, which gives the day's verdict on each work, does not say that an
  hour can overrule it. The hour of rule 2 is not built (SPEC §5.13).
- **Work, S, built 2026-10-07.** The brief gets a "By the hour" block: today's hours whose
  combination period is to be accomplished or avoided, and the nectar
  hours, each opening the hours panel, with one sentence that within its
  hour the combination period outweighs the day's weighing.
- **Work, M (reading).** What makes an hour good or bad against the *nyi
  ma* in rule 2: KP img. 13–14 with WB p. 376 ([weighing.md](sources/weighing.md)),
  then the table it refers to. Built on the hours panel if it is
  calculable.
- **Built 2026-10-10** (SPEC §5.13, §10.3; [weighing.md](sources/weighing.md),
  *The hour of rule 2*). Rule 2's "sharp weapon" closes WB's passage on
  the twelve hours of the Chinese reckoning (vol. 2, p. 359), read on the
  scan with two readers: what each hour is good for, the hour of one's
  own year, and the four rough times «ཕྱེད་གཉིས་ཟེར་གཉིས», in which only averting,
  killing and fierce work succeeds. WB does not name the four; a ritual
  manual of the *Rin chen gter mdzod* glosses the same pair as midnight,
  noon and the rays of the rising and setting sun, so the app takes them
  as the sun's moments at the user's place. On the hours panel each hour
  shows its works, a rough time falling in it, the own year's hour with a
  birth date, and the works it turns against the day animal: those the
  day animal alone decided and the hour's own readings (with the black
  hour) name the other way. `ChineseHoursTest` keeps 5 January 2026
  (consultations to avoid in the rough times and black hours of a horse
  day) and 29 November 2026 (fierce rites good in the ox and tiger hours);
  checked on the emulator in both languages. Found on the way: WB's own
  results of the twelve day animals (pp. 356–359), T2.24.

#### T2.5 The mansion's own lists — built 2026-10-07 (SPEC §5.10, §5.12)

WB's 28 mansion verses (pp. 313–328) are read: every good and avoid list
on the scan, Kṛttikā's «དྲ་གྱོན» (new clothes, where the 1996 print sets
«དྲ་གྱོད») from the Zhol print ([mansion-verses.md](sources/mansion-verses.md)).
Each day mansion's reading now joins its verse's lists, first, to
Henning's list and KP's boxes: 313 new wordings and two new activities
(Bon rites, pitching tents). Henning's "installing a deity" is *rab
gnas*, consecration: his nine mansions for it are all ones whose verse
names consecration good, none whose verse avoids it, while the verses'
temples and images go their own way (Hasta avoids them). *Bcud len*,
which no verse names, is its own act, taking elixirs, no longer the
Japanese pages' taking medicine. The *dar gud* (pp.
329–330) is read and not built: WB says that reckoning lacks a
scriptural source. Measured again on 2000–2049: the tones do not change
(the mansion has none); the days' works grow from 1,530,335 to
1,627,039 (101,811 added; 5,107 gone, silent where a verse contradicts
the mansion's other lists or moved with "installing a deity"), 11,245
change side, and the mansion decides 288,365
instead of 172,008; days against their tone 2,087 to 2,140.
`DaySummaryTest` keeps 1 November 2026 (Ārdrā: killing good) and 28
January 2026 (Kṛttikā: war and raids to avoid).

#### T2.6 WB chapter 34, the works one by one — built 2026-10-08 (SPEC §5.10)

Inventoried, compared and read on the scans
([white-beryl-ch34.md](sources/white-beryl-ch34.md)): the chapter's 65
works are KP's boxes in the same order, so the boxes are its digest. It
is joined to each box (what it names plainly good or bad is added, and
decides where the box has the other side), and five works without a box
are lists of their own (shows, hunting and theft, taming horses,
averting rites, sorcery). Its entries stand at their factors' ranks, as
the works' particular cases; the chapter says nothing of a rank of its
own. Measured on 2000–2049: no day's tone changes, 23,616 works are
added, 9,741 change side, days against their tone 2,140 to 2,085.
`ElectionalTest` keeps the decisions (the offerings' khrums, servants'
4th and 14th, war's animals, rain's mansions). Left open: three entries
whose verse reads two ways, and the name «བྱ་གཞུག» (white-beryl-ch34.md,
*Left out*).

#### T2.7 The day's tone against its lists — built 2026-10-07 (SPEC §5.12, §10.3, §10.7)

The owner chose the counts beside the In brief row ("good 61", "avoid 21",
one above the other). Measured again on the code of that day: 2,087 days
of 18,263 in 2000–2049 run against their tone, and the combination
decides the tone on 9,672; `DaySummaryTest` keeps both.

#### T2.8 Rows that repeat — built 2026-10-07 (SPEC §10.3)

The owner chose to fold the five components into the Almanac rows: the
weekday's, mansion's, karaṇa's and yoga's sheets give the Tibetan name in
script (tap for Wylie and phonetics), the section is gone, and the day
line keeps its facts.

#### <a id="t2-9"></a>T2.9 Answers that rest on unseen scans — built 2026-10-09

- **Gap.** Question 11 (the day's sme ba runs up from the first wood-mouse
  day after the winter solstice) rests on BS and MK, and the reading of
  *dmigs bsal* in part on KD; all three were read in BDRC's etext only,
  their scans being lent on archive.org (`bdrc-W25151` and others,
  [sources/README.md](sources/README.md)). Of question 1, WB's 18th bad
  yoga and its line with «རང་སྐྱེས» have no reading in any source found; the
  app does not use the ranking.
- **Work.** Borrowed with the owner's archive.org account: check the BS
  and MK passages and KD vol. 1, pp. 498–499 on the page, and record them
  as read. Question 1's remainder waits for a reader of the tradition.
- **Built 2026-10-09.** Read on the scans lent on archive.org under the
  owner's login (`tools/sources/archive_lend.py`): BS vol. 3, pp. 49–50,
  and MK vol. 10, p. 306, as quoted, BS adding the hours' sme ba and that
  the count comes from Chinese writings ([earth-lords.md](sources/earth-lords.md));
  KD vol. 1, pp. 498–499, syllable for syllable as quoted
  ([weighing.md](sources/weighing.md)). On the way, KD vol. 2, pp. 223–224
  and 315, question 15's witnesses, show the figures above their number
  words: 14 over *yid*, 6 over *dus* and *ro*, 30 over *mkha' me*. Nothing
  in the app changes.

#### WB above all: T2.10–T2.18

The owner's rule (2026-10-08): **the White Beryl counts above every other
source.** Where Rabten, FPMT, Henning's lists or a calendar disagree with
it, WB decides; where WB is silent they may stand, marked as theirs. The
items below come from comparing a Russian WB-based calendar with the app
over 2026 ([tibetastromed.md](sources/tibetastromed.md)) and from WB's
etext, searched for each thing that calendar shows; every WB passage they
name is found in the etext and **not yet read on the scan**, so each item
starts with that reading (PLAN.md). The comparison found nothing that
moves the app's arithmetic: its dates, month animals, trigram, sme ba and
mansions are WB's and Henning's, and the calendar's mansions are wrong on
38 days of 2026.

The Bon *snang srid me long* (SN in [sources/README.md](sources/README.md)),
where the calendar takes most of what WB lacks, is a **witness to WB
only** (the owner, 2026-10-08): its etext is searchable, so each item reads
it beside WB (the burning dates for T2.12, the nāgas and eight classes for
T2.13, the hair-washing results for T2.15, the people's and horses' la
places for T2.16) to confirm a reading or raise a question, but nothing
that only SN gives is built or cited, and no Bon text is a `Source`.

#### T2.10 WB's months and seasons — built 2026-10-08 (SPEC §5.13, §10.3)

WB's model almanac gives each Hor month a Kālacakra season and a Chinese
one (vol. 1, pp. 154–171, read on the scan:
[tibetastromed.md](sources/tibetastromed.md), *WB's months and
seasons*). Its chapter 31, where every season-keyed passage the app uses
or plans stands, counts by the Chinese reckoning, as two of its own
passages show (pp. 206, 228): spring is months 11, 12 and 1, autumn 5–7.
`Texts.RAHU_MONTH` moved from months 1, 2 and 7–9 to 11, 12 and 5–7
(`SeasonReckoning`, `SeasonReckoningTest`); the month balloon shows both
seasons, each labelled. T2.13 and T2.14 take their months from the same
answer.

#### <a id="t2-11"></a>T2.11 WB's almanac page — built 2026-10-09 (SPEC §5.8, §8.3)

WB's list of what an almanac writes for each day is in chapter 14 (vol. 1,
pp. 177–178, img. 187–188; the month's heading p. 176; the same list in
short at the close of chapter 13, p. 173), read on the scan into
[almanac-page.md](sources/almanac-page.md), each entry against the app.
Built already: the date, the five-fold cycle, the weekday, the mansion,
the element pair, Rāhu, the named combination, the yoga, the karaṇa, the
special days, the burning dates, the date's animal, trigram and sme ba,
the date animal's earth lords. Planned: the strikes and turnings, the sky
doors (*gnam sgo*) and *gnyan pa* (T2.13: the sky doors are dated on
p. 235, in its pages), the times within the day (T2.20), the month's own
entries (T2.21), the five planets and the *byed rtsis* (T2.23), the *dbyangs 'char*'s vowels, stages and "hundred feet"
and the twelve links (T2.22: WB writes them on the day line though it
reads them only against a name or a birth). *snag tsha* is no
entry: it is the ink the entries are written in (p. 178). The special
days' verse (p. 177) names seven kinds on every weekday as the table of
p. 341 does, Tuesday's *bdud nyi* Āśleṣā and the *sreg sbyor* the table's
*gtan spang*; the first date's trigram and sme ba (p. 178) are the app's
on every first date of 2000–2049, with the Chinese reckoning's seasons.
`AlmanacPageTest` checks both. The calendar's "lucky days" and "days of
good and evil" are not in WB's etext and are not built.

#### T2.12 The burning dates (*bsreg tshes*) — built 2026-10-08 (SPEC §5.11, §5.12)

- **Gap.** WB's burning dates, a weekday meeting one of two dates (vol. 1,
  p. 177, img. 187: Sunday the 12th and 27th, Monday the 11th and 26th …
  Saturday the 7th and 22nd), are named among the things to avoid after
  the karaṇas (vol. 2, p. 351: bloodletting, moxibustion and virtuous work
  do not succeed) and in chapter 34 (pp. 404, 414, 426). The app did not
  have them.
- **Built.** Read on the scans into [burning-dates.md](sources/burning-dates.md);
  `BurningDate` by the day's weekday and its own date, with WB's reading,
  lists and remedy, a member of the special days' voice (WB's almanac
  writes it with them, p. 177), in the special-days row and its sheet in
  English and Russian; `BurningDateTest` against WB's verse (the vector)
  and its number words. Over 2000–2049: 1,226 burning days, the day's tone
  changed on 11, days whose lists run against their tone 2,082 (2,085
  without). The calendar's 2026 days have the same pairs.
- **Left.** *rgyun sreg* (p. 351) is unidentified. WB's marked case, the
  burning date that begins before nightfall on the day before (p. 177),
  is built with T2.20 (2026-10-09), nightfall by WB's day length.

#### <a id="t2-13"></a>T2.13 The earth lords that move by date — built 2026-10-09 (SPEC §5.13)

- **Gap.** WB vol. 2, pp. 226–235 run through the earth lords that move by
  date, which WB's almanac writes on the day (vol. 1, pp. 173, 178): the
  strikes (*thebs*) and turnings (*bzlog*) of the eight classes and the
  nāgas, the sky doors (*gnam sgo*), *gnyan pa*, and the earth lords of the
  Chinese reckoning. None was built but the great black day (T2.14).
- **Built.** Read on the scan into
  [earth-lord-courses.md](sources/earth-lord-courses.md), section by
  section, with Yigdzin-1, MITRA and the etext, and checked against the day
  boxes of WB's model almanac (vol. 1, pp. 154–171: Hor months 11, 12 and 9),
  which settle «X གསུམ» as the Xth, X+10th and X+20th and show the almanac
  writing the sky doors by the general reckoning. `EarthLordCourses`: the
  small black day, *pi ling 'phar ma*, *zin phung*, *phung po zor thogs*,
  *ki kang*, *hal khyi*, *gnam khyi*, *gnam sbyor*, *gza' rgod*, *dbul po*,
  *gza' bdun*, *ngam shing*, *bar khyi*, *ka khyung ki kang*, *dra chen*,
  the eight classes' and the nāgas' strikes and turnings, the earth lords'
  turning, the *gnyan*'s moving times with their strikes and turnings, the
  classes' own times and the sky doors, each with WB's results (p. 364 and
  pp. 368–369 for the strikes and the doors) in English and Russian;
  `EarthLordCoursesTest`. One row, "The earth lords' courses", in "Also
  today" after the great black day, shown and not weighed: WB's order of
  strength (p. 376) does not rank the earth lords, and no weight is
  invented. The exception is section 27, a course of Rāhu: `RahuBySeason`,
  a third reading in the Rāhu row, weighed in Rāhu's tier (15 days of
  2000–2049 change in `DaySummaryTest`'s count, 2,082 to 2,067).
- **Other views.** The second and third courses WB reports after its own
  (*ki kang*'s, *gnam khyi*'s, *phung po zor thogs*'s by season, *gza'
  bdun*'s reversed, *dbul po*'s and *nag chung*'s variants), what "some say"
  of the eight classes' and the nāgas' strikes, and Spug ston's view of the
  earth lords (dates of success, vanishing and keeping wealth) are built too,
  each marked as another view. **Removed 2026-10-11** (the owner: the earth
  lords show one entry each and no other view at all): the other views are
  no longer built or shown, in the courses or in the readings, and stay in
  `docs/sources/earth-lord-courses.md`; an earth lord that does several
  things on a date (the *gnyan* move and strike, several classes' times) is
  one entry with one reading. Spug ston's view is a course of its own and
  stays.
- **Read again 2026-10-09:** the Zhol print settles *zin phung*'s twelve
  hours («ཉི་རྩེའི་རྡོལ་འགོ», where the 1996 edition has རྡེལ) and Spug ston's last
  summer («གསུམ་དྲུག་ཉེར་ཡལ»: the 3rd, 26th and 28th), and «བིདྡྷི» is Viṣṭi (WB's
  karaṇa table, p. 343), so the planets' and *srin po*'s times are Viṣṭi's
  eight dates; all three built.
- **Read last:** Spug ston's direction for each month (*yas lam*) is the way
  for the ransom offering, as WB uses *yas* elsewhere, and is in the
  reading; *zin phung*'s two autumn works, confirmed on the Zhol print, are
  given word for word. The black days of p. 235 are built too, by the black
  months' rule of vol. 1, p. 183 (the four-slayer), with the Paṇchen's list
  by year as another view (removed 2026-10-11, above). Nothing of pp. 226–235 is left unbuilt. The sky doors' remedies (pp. 368–369) are summarised in
  each door's reading; *zin phung*'s spring days in the middle are their own
  event ("in the middle"). The black days
  (p. 235) go with the black months (T2.21); the earth lords of the hour
  that follow (pp. 235–236) with the times within the day (T2.20).

#### <a id="t2-14"></a>T2.14 The 11th month's 6th: the nine bad — built 2026-10-09 (SPEC §5.7, §5.11)

- **Gap.** The app showed 11/6 as Sangpo Chuzom, the Ten Good Omens, after
  Rabten. WB has no ten good omens; it names «ངན་པ་དགུ་འཛོམ», the meeting of
  the nine bad, on the 7th of the first spring month (vol. 2, p. 226),
  which by T2.10's months is 11/7.
- **Decision.** The owner decided on 2026-10-09: WB's nine bad on its
  day, Rabten's festival removed (WB above all).
- **Built.** Read on the scan into [great-black-day.md](sources/great-black-day.md):
  the nine bad is the first date of the great black day (*nyi ma nag
  chen*), the first of chapter 31's earth lords that move by date, with
  one date in every month (7, 14, 21 / 8, 16, 24 / 9, 18, 27 / 10, 20, 30
  by season-month). `GreatBlackDay` with WB's results and remedies, a row
  in "Also today" (titled the meeting of the nine bad on 11/7), unlucky
  and not weighed, like the *bla mkhyen*; English and Russian;
  `GreatBlackDayTest`. Rabten's festival, its reading, glyph and strings
  removed. Checked on the emulator in both languages on 11/7 (2026-12-16), 11/6 and 12/14 (2027-01-21).
- **Left.** WB's "one person's tradition" (the 24th in three last
  months, the 9th in the first of winter) is not built; «དགེ་བཤེས་ཆེ་འདོན»
  in the list of works is not identified. The small black day, the next
  verses on p. 226, is T2.13's.

#### <a id="t2-15"></a>T2.15 Hair: cutting and washing by date — built 2026-10-09 (SPEC §5.8, §10.3)

- **Gap.** The haircut sheet showed FPMT's results by date; WB has no
  list of haircut results by date, and its chapter 34 work 34 gives
  "bathing and washing the hair, with the dates' results" (p. 404), which
  a WB-based calendar shows close to FPMT's haircut list.
- **Built.** Read on the scan ([hair-dates.md](sources/hair-dates.md)):
  WB's thirty hair dates are for washing the hair, "differing a little"
  from chapter 33's date readings. FPMT's list is of the same family (the
  same result on 17 dates, the same side on 25) but not WB's. The lunar
  date's sheet gains a "Washing the hair" detail with WB's result
  (`Texts.HAIR_DATE`, English and Russian); the haircut sheet keeps
  FPMT's day, weighed as before, marked as not WB's. KP box 37's worn bad
  line takes WB's bad dates, which leave the 27th on neither side
  (`ElectionalTest`). SN's table agrees and is witness only.

#### T2.16 The la's place: the sides and the animals — built 2026-10-08 (SPEC §5.11)

- **Gap.** WB gives the left and right of men and women for the Kālacakra
  list (p. 303), and beside each Phugpa date the place of "horses and the
  like" (pp. 303–304). The app gave the person's place only, and called
  the *bla* "the soul".
- **Built.** Read on the scans ([lunar-dates.md](sources/lunar-dates.md),
  *Where the la resides*): the sides open the Kālacakra list and the
  Phugpa list names none, so the app keeps the place without a side; the
  thirty readings give the place in horses and other livestock beside the
  person's, two of its words (dates 3 and 5) marked unidentified. *bla* is
  now "the la (bla), the life-spirit", «ла (bla), жизненный дух», after
  Berzin's glossary, in the readings, the la mansion (*bla skar*) and
  Gaṇḍa's long reading (WB's «བླ་ཚེ»). `CatalogTest` checks every date in
  both languages.

#### T2.17 Russian terms by WB's words — built 2026-10-08 (SPEC §8.2)

- **Gap.** The catalog gave one Russian word to different WB terms:
  «свершение» for the yoga Siddhi, the element pair *dngos grub* and the
  named combination *grub*, «юность» for *lang tsho* and *gzhon*; and
  several to one: *'phel* was «рост», «возрастание», «приумножение» and
  «продвижение», the burning «жжение», «сочетание жжения» and «сжигающая
  дата», *mi 'phrod* «недостаток» beside «несовместимость». The calendar
  reads *rtag myos* as a tiger (*stag*).
- **Built.** *dngos grub* достижение (yoga and pair), *grub* свершение;
  *lang tsho* юность, *gzhon* юноша; *'phel* and the pair's *'phel 'gyur*
  возрастание, приумножение left to the increasing rites (*rgyas*); *mi
  'phrod* несовместимость. WB spells the burning *sreg* and *bsreg* alike
  (p. 177 «སྲེག་སྦྱོར», ch. 34 «སྲེག་ཚེས», [burning-dates.md](sources/burning-dates.md)) and
  names both the earth–fire pair and *gtan spang* a burning combination,
  so all three are сожжение: «сожжение», «сочетание сожжения», «дата
  сожжения». «нектар», «ваджра» and «радость» each stand for one WB word
  (*bdud rtsi*, *rdo rje*, *dga' ba*) in two reckonings and stay. *rtag
  myos* is WB's on the table's heading (vol. 1, p. 149, img. 159, its ར
  beside «བརྟན»'s) and in its short reading (vol. 2, p. 333): «вечно
  ликующий» stays ([combinations.md](sources/combinations.md)).
  `CatalogTest` fails when two WB terms share a Russian name or one WB
  word loses its Russian one.
- **English, the same rule** (the owner, 2026-10-08: follow WB). English
  had the same two collisions, *'khon 'dzin* and *mi mthun* both
  "discord", and its pair names followed the Sanskrit while their
  readings named the Tibetan. Now *dngos grub* attainment, *gzhon* young
  one, *'khon 'dzin* (Vaidhṛti) enmity; *'phel* and *'phel 'gyur* growth
  ("increasing" stays the increasing rites'); *phun tshogs* perfection,
  *mi 'phrod* incompatibility, *mi mthun* discord in the reading too,
  *dga' ba* joy for the yoga Harṣaṇa as for Nandā. `CatalogTest` checks
  both languages. The Russian read-through (L3) sees the new words.

#### T2.18 Election by activity — built as E1–E3, 2026-10-09 (SPEC §5.14)

The calendar's "choice of time" lists a month's good and bad days for one
of 25 works, ranked. The app weighs every work on every day already
(SPEC §5.12); the election reads that weighing across days, so the WB
voices still to come (T2.11, T2.13) change its results without changing
it. It is item E below, built 2026-10-09.

#### T2.19 The birth weekday, the birth mansion and the element's weekdays — built 2026-10-08 (SPEC §5.8, §5.12, §10.3)

- **Gap.** WB p. 338 names the birth weekday, one's own weekday (the *bla*
  weekday, one with it: p. 346, p. 312) and the mother's, friend's and
  enemy weekdays of one's element, and the birth mansion; the app builds
  p. 330's days by the birth animal only. Open question 14 settled what
  each is: the element is the birth year's life force ("applied the same
  way", the tables pp. 345–346), the birth weekday the weekday of birth
  (p. 379), the birth mansion the mansion of the birth date (Phug pa Lhun
  grub rgya mtsho). The mansion of conception stays unbuilt (no source
  defines it).
- **Work.** Compute them from the birth date; show each in "For you" with
  p. 338's works (the birth and own weekday: contests, trade, pleading a
  case, races, archery; the mother's and friend's good; the enemy's every
  work avoided, E6; the birth mansion: offerings, serving the lama,
  giving, a new house, planting trees). Where one of them and a p. 330
  day fall on the same weekday, one row. Not weighed (T2.3; what WB says
  of a person's days against the day is under E, *The person's days and
  the hour against the day*).
- **Done when.** Vectors for the test birth date (1976-06-01);
  `CatalogTest`; SPEC §5.8, §10.3; Russian.
- **Built.** `ownDays` (core `tibetan/`): the birth weekday, the
  weekday's place among the five of the life force's element
  (`elementWeekday`, the weekdays' elements of the *nag rtsis*, vol. 1,
  p. 257) and the birth mansion; readings `Texts.OWN_DAY` with p. 338's
  works, the mother's and friend's "anything" (p. 330), the child's
  middling (no dot), the enemy's "every work" to avoid. Every weekday
  holds one of the five, so with a birth date every day has a row. The
  roles of the weekday, with the birth animal's personal day, are one
  Almanac row, "Your weekday" with each one's dot, its sheet giving each
  reading in turn, and one entry in the brief's "For you" (`sharedTone`);
  the birth mansion its own row. `OwnDayTest` checks the table of p. 346
  cell by cell, that each animal's anti day (*gshed gza'*, p. 330) is an
  enemy weekday of its life force, and the test birth date (a Tuesday,
  Fire Dragon, life force earth, birth mansion Ārdrā, 12 returns in
  2026); `DaySummaryTest`, that nothing is weighed and one weekday is one
  entry. Checked on the emulator in English (8 October 2026: "Anti day ·
  Enemy weekday") and Russian (1 November 2026: «День удачи · День недели
  матери» and the birth mansion), and with no birth date (no row).
- **Left.** The element's mansions by the life force (p. 330, tables
  p. 345: own, mother, friend, child, enemy quarters and the grave
  mansions), which would put a second row on every day: built on the
  Health screen (H1, 2026-10-15); the mansion of
  conception (no source defines it); E6's exception for the enemy weekday
  and the death mansion.

#### <a id="t2-20"></a>T2.20 The times within the day — M, built 2026-10-09

- **Gap.** WB's almanac writes what changes within a day (vol. 1, p. 177,
  [almanac-page.md](sources/almanac-page.md), entries 5, 7–9, 18): the
  second mansion when the moon enters one in daytime, the mansion's change
  "rough, in *chu tshod*"; a skipped yoga with the one before; where Viṣṭi
  begins and ends (the later half of the 4th, 11th, 18th and 25th, the
  earlier half of the 8th, 15th, 22nd and 29th); the sun's terms (*dbugs
  thob*, *khyim 'pho*, *sgang*) in the date they fall in. The app gives the
  mansion, yoga and karaṇa at daybreak only (SPEC §5.8) and shows no sun's
  term.
- **Rule (the owner, 2026-10-09).** Everything as the sources give it, WB
  first: where WB gives a rule it is built, where it gives no weight none
  is invented. So the second mansion and the skipped yoga are written
  beside the daybreak ones, not weighed (WB only says to write both), and
  the sun's terms are reckoned as WB's almanac reckons them, not by the
  astronomical sun (the day's sme ba keeps its own sourced count, SPEC
  §5.11).
- **Work.** Read WB ch. 15 (vol. 1, p. 180 ff., img. 190 ff.) on the scan
  for how *dbugs thob* and *khyim 'pho* are reckoned: its opening weighs
  the present custom against an earlier one and seems to leave both
  standing (etext, «…ད་ལྟའི་ཕྱག་རྒྱུན་དང་། །སྔོན་མ་གཉིས་པོ་གང་བདེ་ཡིན»); write what it
  settles into almanac-page.md. Then, from Janson's arithmetic (the moon,
  the true sun, the end of each lunar day): the end of the day's mansion
  and yoga, the half lunar days' bounds, and the sun's terms by WB's rule.
  Rows: a second mansion and a skipped yoga each beside the daybreak one,
  with its time; Viṣṭi's span on the karaṇa row and the hours panel; the
  sun's term in the Lunar day section.
  The earth lords of the hour (WB vol. 2, pp. 235–236: *g.yu mdzod sngon
  mo*, the hour's *bla mkhyen*, *sa bdag khang brtsegs*, *dus tshod sa
  rgyal* with *pi ling 'phar ma*'s hours, the black hours), found by T2.13
  ([earth-lord-courses.md](sources/earth-lord-courses.md)), are times within
  the day too: read and build them on the hours panel.
- **Done when.** Vectors against Henning's calendars where they give the
  times, and against WB's rule for the sun's terms; SPEC §5.8, §10.3;
  checked on the emulator in both languages.
- **Built** (SPEC §5.8, §5.13, §10.3; [almanac-page.md](sources/almanac-page.md),
  *The times within the day*; [earth-lord-courses.md](sources/earth-lord-courses.md),
  *the earth lords of the hour*). WB ch. 15 read on the scans: it leaves
  the present custom (the true sun) and the earlier (the mean sun) both
  standing; the app keeps the measures it lists, 36 terms read from number
  words, each kind stepping by 2;15. `DayTimes`: the second mansion by
  daylight, the skipped yoga, Viṣṭi's span, the sun's terms;
  `EarthLordCourses.blackHour`; readings `SUN_TERM`, `HOUR_EARTH_LORDS`,
  `BLACK_HOUR`. Checked: `DayTimesTest` against Henning's figures, now in
  henning-phugpa.tsv (moon, sun, yoga sum and the date's end to the chu
  srang on all 2,245 days; the mansion changes, skipped yogas and over 400
  Viṣṭi spans worked by WB's rules on Henning's printed figures; a *dbugs
  thob* worked by hand); on the emulator in English and Russian, 7, 9, 14
  and 27 October 2026 (the 8th month's *sgang* then at 01:44, Uttaraphalgunī
  from 16:30, Viṣṭi 08:33–21:16, the skipped Vajra 09:01–04:06) and the
  hours panel (the black hare hour of a mouse date, the earth lords, the
  Viṣṭi arc). Then the hour's *bla mkhyen* on its triad's *klung rta* and
  the hour's *sa rgyal* on the four-slayer in front, by WB vol. 1,
  pp. 254 and 235 (the triads' *klung rta* and the animals' places, the
  four-slayers counted up and down); checked in `DayTimesTest` (each
  *klung rta*'s element is the year's luck of §5.9) and on the emulator in
  both languages (the hare hour: the snake's upper south, the horse's
  lower south; the ox hour: the pig's upper north, the dragon's
  south-east). Last, the hour's hidden earth lord on its animal's place
  (vol. 2, p. 221), the hour's sky dog over the twelve places by the
  year's rule (p. 197), and the *sa rgyal* the other way, *pi ling 'phar
  ma*'s hour of each season-month (p. 236, the late summer's dog hour
  from the Zhol print), as another view (removed 2026-10-11 with every
  earth lord's other view, SPEC §5.13); and the sun's terms by WB's step,
  the excess's chu tshod times 14 (*yid*) and its chu srang divided by 6
  (*ro*), in whole chu tshod (open question 15, answered from Ngag dbang
  bzang po's two restatements of the line in the *Kun 'dus chen mo*),
  which puts the 8th month's *sgang* of 2026 at 01:00 on 8 October. Checked in
  `DayTimesTest` and on the emulator in both languages (the tiger hour of
  9 October 2026: the earth king the other way on the hare's place, the
  garuda, the sky dog's head on the tiger's place). And the burning date
  begun in daylight (entry 11), which the dates' ends now give: shown on
  the day before with its time and WB's hook (1 October 2026, the 21st
  from 12:22, checked in both languages). Daytime, for it and the second
  mansion, is WB's own day length by the true sun (ch. 15's values at
  each *sgang*, p. 182's 1;10 a sign-month), not the place's sunset.
  Built in full.

#### <a id="t2-22"></a>T2.22 The *dbyangs 'char* entries and the twelve links — built 2026-10-10 (SPEC §5.11, §10.3)

- **Gap.** WB's almanac writes on each day the vowels and consonants of
  the Kālacakra and the *dbyangs 'char* with their elements and sense
  objects, the date's stage (child, youth, adult, old, ripe), the
  "hundred feet" table over each mansion, and the twelve links counted
  from the month's *sgang* (vol. 1, pp. 177–178, entries 3, 4, 12, 17 of
  [almanac-page.md](sources/almanac-page.md)). None is built. WB attaches
  no reading of the day to them here: the stages are read against a
  person's name (vol. 2, p. 305), a link as a birth sign (vol. 2, p. 384).
- **Rule.** As T2.20: written as WB writes them, with no reading or weight
  it does not give.
- **Work.** Read their rules on the scan: the *dbyangs 'char*'s in WB's
  second preliminary chapter, to which p. 177 refers («སྔོན་འགྲོ་ལེའུ་གཉིས་པ»),
  and the *gtan le* of ch. 13; the twelve links' rule (vol. 1, p. 19,
  etext) with p. 177's table for months of 28 to 32 days from *sgang* to
  *sgang* (it needs T2.20's *sgang*). Build what is calculable as rows of
  the Lunar day section, each balloon saying what WB says of it.
- **Done when.** Vectors from WB's model almanac (vol. 1, pp. 154–171,
  whose day rows carry these entries); SPEC §5.8, §10.3; both languages.
- **Built** ([day-letters.md](sources/day-letters.md); `DayLetters`,
  `DayLettersTest`). Read on the scans: ch. 2's rules (pp. 16–19), the
  wheel of the mansions (ch. 12, p. 97), the links' table (pp. 150–153,
  with WBZ img. 171–172) and the model almanac's day boxes, which are
  WB's *gtan le*, the fixed entries of each date. The Kālacakra's vowel,
  syllable, element and sense object; the *dbyangs 'char*'s, its halves
  reversed (the long vowels and ka–da on the 16th–30th); the stage with
  the five-fold cycle; all checked against 27 boxes of the 11th month and
  the 12th's. The twelve links by the table from the day of each *sgang*,
  all twelve rows read cell by cell, three in the test, the 10th month's one odd cell kept as
  both prints have it, with 28–32 days by p. 177's rule; one stretch of
  33 days in 2000–2049 (2009) has no link. The hundred feet: the moon's
  quarter and its syllable. Four rows of the Lunar day section, each
  opening its reading. Checked on the emulator in English and Russian
  (10 October 2026: nā, o da, ripe, ignorance on day 4 of 31,
  Uttaraphalgunī's 3rd quarter pa). With the foot, the moon's mansion's
  face-on and right and left fangs across the wheel of the hundred feet,
  which WB draws and names cell by cell (vol. 1, pp. 96–97), reckoned as
  p. 98's worked fang of Kṛttikā lays them out (on 10 October 2026,
  Uttaraphalgunī: right ma, ḍa, Punarvasu; left pa, Libra, Scorpio, bha;
  Uttarāṣāḍhā face-on), and the fangs of the sun, the five planets and
  Rāhu over their mansions: WB's «ལྔ་སྒྲ་ཉི་མ», the five planets, Rāhu and the
  sun (open question 17, answered). The planets' and Rāhu's places by
  Edward Henning's software for the generalised Phugpa epoch, ported to
  `Planets` digit by digit and checked against his program on every day
  of 2000–2047 and 1,000 months of Rāhu ([day-letters.md](sources/day-letters.md)).
  Built in full.

#### <a id="t2-21"></a>T2.21 The month's own entries — built 2026-10-10 (SPEC §5.11, §5.13, §10.3)

- **Gap.** WB's month heading (vol. 1, p. 176) writes the black months
  (*zla nag*), whether the month is long or short, the weekday that rises in the month (*gza' dar gud*:
  roughly Saturday in the tiger, horse and dog months, Mars in the mouse,
  dragon and monkey, the Moon in the pig, sheep and hare, Jupiter in the
  bird, ox and snake; finely by the element of the month's first
  weekday), eclipses, and seasonal signs (the Ṛṣi's seven days, the
  poisoned waters, a comet). None is built.
- **Work.** Read on the scan: the black months (vol. 2, p. 212, *ki kang
  zla nag*, and vol. 1, pp. 183, 340, 343; the black days that follow their rule,
  vol. 2, p. 235, are built by T2.13), the weekday's rise against its
  works (vol. 2, p. 312: «ཕྱུགས་གཟའ་དར་ཡང་ཕྱུགས་མི་སྐྱེལ»), WB's eclipse
  reckoning (vol. 1, p. 62 ff.), and where the seasonal signs are dated.
  Build each that is calculable in the month balloon; the weekday's rise,
  if it changes a weekday's works, as part of the weekday's voice.
- **Done when.** Each entry built with its vector or written off with the
  reason in [almanac-page.md](sources/almanac-page.md); SPEC §5.11 and
  §10.3.
- **Built** ([month-entries.md](sources/month-entries.md); `MonthEntries`,
  `MonthEntriesTest`). Read on the scans: p. 176's heading, p. 183's and
  p. 212's black months with p. 359's results, ch. 16's signs and their
  table, the model almanac's month lines. The month's days, long or
  short, checked against every whole month of Henning's calendars. The
  weekday's rise both ways, the fine one against the model almanac's three
  month lines (the verse's friend «སྐྱོད», moves, not the etext's «གུད»);
  p. 312 lifts no avoidance for a rising planet, so it changes no
  weighing. The black months and Kikang's black months (p. 212's twelve
  year lines) as two earth lords' courses on their days, shown and not
  weighed, the month's thirds by WB's notes (the *sgang* the 11th–20th).
  The sage's and the pig's seven days by the true reckoning's mean sun at
  10;43,30 and 5;46,30, against WB's own 65-year table for 1990–2100 (the
  table's date, or the next where its part is 12, as p. 189 allows): in
  2026 the pig's from 16 July, the sage's from 21 September. The comet by
  p. 186's count, the years since 1027 as the *Kun 'dus chen mo*
  restates the line. All in the month balloon, the seven days as rows of
  "Also today" too. **Written off: the eclipses** (WB ch. 9), moved to
  T2.23: Rāhu's epoch reads 1687 by the number word and 1987 by the
  editors' bracket, and KD's later constants stand a month from WB's
  (open question 16, since answered: 1687, by Henning's reckoning); the
  solar rule needs the *byed rtsis*; and as read
  the lunar rule finds 13 of 23 real lunar eclipses of 2015–2030 with 14
  false ones. Checked on the emulator in English and Russian.

#### <a id="t2-23"></a>T2.23 The five planets and the *byed rtsis* — built 2026-10-10 (SPEC §5.11, §10.3)

- **Gap.** WB's full almanac writes, at each month's head, the five
  planets' slow and fast motion and their meetings, and the omitted and
  doubled days of both reckonings, the *byed rtsis* beside the *grub
  rtsis* (vol. 1, p. 176, [almanac-page.md](sources/almanac-page.md)); the
  middle almanac may write the *grub rtsis* alone (p. 178). The app
  reckons the sun and the moon by the *grub rtsis* only (SPEC §5).
- **Rule.** As T2.20: what WB's full almanac writes is built, with no
  weight WB does not give.
- **Work.** The planets by Janson's arithmetic (*Tibetan Calendar
  Mathematics*, the planets' chapters), checked against Henning's
  calendars; the *byed rtsis* from WB's own rules (vol. 1, the chapters
  on it, to be found and read on the scan). Then the month balloon gives
  what the full almanac's heading gives.
- **Done when.** Vectors against Henning for the planets and against WB's
  worked examples for the *byed rtsis*; SPEC §5; both languages.
- **And the eclipses** (moved from T2.21, 2026-10-10;
  [month-entries.md](sources/month-entries.md)): WB ch. 9 (vol. 1,
  pp. 56–70), on Rāhu's head and tail by p. 34, its ten sections from
  whether the moon or sun is seized to the results by month. Rāhu's
  epoch and constant are settled (open question 16, answered 2026-10-10:
  1687 and 209, Henning's reckoning, built in `Planets`); they wait on
  the *byed rtsis*, which the solar rule uses, and on the lunar rule's
  misses against the sky. Done when a worked example or almanac agrees.
- **The planets' places are built** (T2.22, `Planets`, after Henning's
  software, checked against it to the last unit): what is left here is
  the heading's slow and fast motion and the meetings, the *byed rtsis*
  and the eclipses. Rāhu's place is built, and is WB's own (question 16).
- **The owner's rule (2026-10-10).** What can neither be used nor change
  the weights or the verdict is not shown, however WB writes it: "info
  for the sake of info is useless". It holds for every block after this
  one; the entries already built under the rule above stay until the
  owner says otherwise.
- **Built** ([month-entries.md](sources/month-entries.md); `Eclipses`,
  `ByedRtsis`, `EclipsesTest`, `ByedRtsisTest`). Read on the scans: the
  heading's line (p. 176), ch. 4's *byed rtsis* (pp. 31–32) with its
  *grub rtsis* (p. 29, whose 2·M + 15 is Janson's index carried to 1687),
  ch. 5's Kar myang reckoning (p. 36), ch. 6's planets' four motions
  (p. 40), ch. 9's eclipse rules (pp. 59, 62, 64–65). **The eclipses are
  built:** ch. 9 reckons them by the abridged tantra's sun, the *byed
  rtsis*, and so built every full moon (79) and new moon (33) WB's rule
  marks in 2000–2049 has a real eclipse (Meeus, ch. 54,
  `tools/eclipse_vectors.py`); with the true reckoning's sun the rule
  found 27 of 72 umbral eclipses and marked 31 full moons with none,
  which is why T2.21 wrote them off. They are shown on their day in the
  Almanac, neutral, with WB's multiplied merit (p. 62); they name no
  works. The *byed rtsis* is built only as their reckoning. **Left out by
  the owner's rule**, each read and reckonable: the *byed rtsis*'s
  omitted and doubled dates and its five places of each date, both
  reckonings' month figures, the planets' slow and fast motion at the
  full and new moon, Rāhu's figure, the Kar myang Lord's meeting.
  **Not built:** the eclipse's time, size, length and results (ch. 9,
  sections 2–8 and 10), which name no works, and p. 64's two exceptions
  for the sun (Mercury or Venus with it; the sun in Cancer, and the
  first day of the sun's crooked motion, its entry into Capricorn),
  which WB's readers make "hard to seize" rather than "not seized" and
  the sky contradicts as read (open question 18, answered 2026-10-10
  as far as the sources go). Checked on the emulator in English and
  Russian.

#### <a id="t2-24"></a>T2.24 The day animals by WB — built 2026-10-10 (SPEC §5.10)

- **Gap.** The day animal (the *nyi ma*, the lunar date's animal) weighs
  by KP's lists only (SPEC §5.10). WB gives the twelve day animals'
  results of its own just before the hours of rule 2 (vol. 2, pp. 356–359,
  img. 364–367: from the tiger, works good and bad and remedies, then the
  opposed pairs, «སྟག་སྤྲེལ་གཉིས་ལ་འདུན་ཁྲོམ་སྤང»); they are not read.
- **Rule.** WB above all (T2.10–T2.18): where WB names a work for a day
  animal, it joins the day animal's lists first, as the mansion verses
  joined the mansion's (T2.5).
- **Work.** Read pp. 356–359 on the scan with two readers, write them into
  a sources file with the lists by eye, and join them to the day animal's
  voice; measure the change on 2000–2049 as T2.5 did.
- **Done when.** `DaySummaryTest` keeps a day each list decides; SPEC
  §5.10; both languages.
- **Built** ([day-animals.md](sources/day-animals.md); `DayAnimalVerses`).
  Read on the 1996 scans (img. 364–367) with Yigdzin-1 and MITRA and the
  etext as third witness, every list word and every disagreement by eye:
  Yigdzin-1 dropped the dragon's line beside its woodcut, MITRA the whole
  monkey verse; the pig's «དགའ་སྟོན», celebrations, read at 5×. Each verse
  names works to avoid and good, lawsuits by direction, then the earth
  lord's seat and remedy, which are ch. 31's twelve seats (already built)
  and are not built again; five of the six opposed pairs join both
  animals' avoid lists, the bird's and hare's «གཏད་སྟོན» is left in doubt
  with some twenty words of the verses. The verse's lists join the day
  animal's first; WB above all, so the activity lists lose each entry
  that names a work the other way from the verse (a hare day's marriage,
  a pig day's suppressing the *sri*; a joined entry goes whole, the
  dragon's and dog's "hunting and theft"). 32 new wordings, in both
  languages. Measured on 2000–2049: tones unchanged, the days' works from
  1,650,091 to 1,681,946 (31,983 added, 128 gone, 518 changing side), the
  day animal deciding 49,784 instead of 17,797, days against their tone
  2,067 to 2,018. `DaySummaryTest` keeps 4 October 2026 (seeking friends,
  by the horse's verse alone), 3 October (felling trees avoided on a snake
  day), 7 October (feasts on a dog day, by the lists alone) and 6 December
  2026 (suppressing the *sri*, good by the pig's verse against the lists);
  the October election's wedding order, the hare's and horse's hours and
  1 November's counts moved with it. The day animal's sheet now says its
  lists are the day's in general, not only for the dead. Checked on the
  emulator in English and Russian; store shot 1 retaken (its count).

#### <a id="t2-25"></a>T2.25 The Lunar day section by use — built 2026-10-10 (SPEC §5.11, §5.12, §10.3)

The owner asked which Lunar day rows serve a reader; by the rule that
nothing is shown that can neither be used nor change a weight or
verdict, and the owner's decisions of 2026-10-10:

- **The date's mewa** opens a reading, and the *kun phan me long*'s boxes
  that name its numbers (left out in 2026-10 as "not calculated") were
  read on KP and KP2: sixteen works. **The twelve links** likewise, 22
  works; "skye" alone (birth or the sense sources) left out, boxes 50, 53
  and 55 not settled on the scans. Both weighed with the trigram as one
  voice, last ([kp-activities.md](sources/kp-activities.md)).
- **The hundred feet** moved to Your day: the fangs on the birth mansion
  and the six holders by WB vol. 1, pp. 97–98, read on the Zhol print;
  the spear and great spear (p. 99) in Also today; shown, not weighed
  (no rank in p. 376, the owner's choice). p. 98's rule for the day's
  date, sign and letters is not built: 64% of days, and it does not say
  whose letters. The five harms of p. 97 are read and not built.
- **Out:** the Kālacakra's and *dbyangs 'char*'s letters (WB reads them
  against a name; building p. 305 with a name vowel declined by the
  owner), the moon's foot and wheel cells, and the sun's terms row.
- **Short rows** (the owner: "do not bloat the screen"): the *bla mkhyen*
  row gives the direction alone, the earth lords' row the courses' names.
- **Checked:** `ElectionalTest` pins every sme ba and link entry to the
  scan reading; `HundredFeetTest` the holders' count with Abhijit and
  2000–2049's strikes on the test birth mansion (Ārdrā) and spears;
  `DaySummaryTest`'s days against their tone 2,018 to 2,006. Emulator in
  English and Russian (10 and 19 October 2026).

#### <a id="t2-26"></a>T2.26 The year's and the month's earth lords — planned 2026-10-11, built 2026-10-26 (SPEC §5.13, §10.3)

- **Gap.** WB's almanac writes, at the head of each month, the month's
  wheel and the charts of its scattered earth lords (vol. 1, p. 176,
  img. 186; [almanac-page.md](sources/almanac-page.md), *The month's
  heading*), and the year's earth lords' charts in the year's front
  matter (p. 176). Chapter 31 counts 231 earth lords of the year and 124
  of the month (vol. 2, p. 174, img. 182): the year's from p. 175 (the
  kings and ministers, the year's *hal khyi* and *sa rgyal*, the black
  years, the nine black ones, *nag mo dgu skor*, p. 191, the sme ba's
  nine gods, p. 192, the forty-five palace gods, p. 195, the sky dog male
  and female); the month's from p. 199 (img. 207: sixty-four bad
  directions, the black months, *bum stong*, the month's *hal khyi*,
  *lto 'phye*, the month's *nag mo dgu skor*, the scattered earth lords,
  *ba dan khra bo*, *snang sel*, *'gram 'phye*, *'gog ku*, the hidden
  twelve, *lag rdum* …) to the date animal's earth lords (p. 222). Most
  stay in a direction for the year or the month, with the works to avoid
  there (a bride, funerals, building, digging) and their remedies. Built
  so far: the black months and Kikang's (T2.21) and the *bla mkhyen*'s
  yearly avoidances (vol. 2, p. 180). The list above is the etext's; none
  of it is read on the scan. **How the almanac writes them** (RD2,
  2026-10-11): the year's front matter draws a wheel of the year and
  the next with the sme ba, the directions to avoid and the clean ones,
  the nine black ones and the date's and hour's charts
  ([almanac-page.md](sources/almanac-page.md)); the month head the
  month's wheel and the scattered earth lords' charts, or fewer. All
  the charts are drawn in vol. 1, ch. 13 (pp. 116–147,
  [wb-contents.md](sources/wb-contents.md)). The model almanac draws
  none: each month head names *bum stong*, the five gods (*lha lnga*)
  and the nine (*lha dgu*) with their directions, twelve months that
  vol. 2, pp. 211–213 and vol. 1, p. 135 confirm, the vectors for this
  block, and a dated entry or two with works (the water demons' king,
  the earth lords' king), whose rule is not found
  ([month-entries.md](sources/month-entries.md)). *bum stong* by the
  month's animal and by the sun's sign part within the month; which
  the app follows is to settle in the build.
- **KP names some of them.** The *kun phan me long*'s boxes name works
  for *se byi*, *hang phan*, *zla klung*, *se shar*, the *se*, *pi ling*,
  *tsang kun*, *dbul*, *nyi nag* and *gnam stong*, left out as "not
  calculated" ([kp-activities.md](sources/kp-activities.md)); once they
  are calculated those entries join the boxes' lists.
- **D4, settled** (the owner, 2026-10-26, on the search below): the
  earth lords stay outside the weighing; each is shown with the works it
  bars in its place, direction or time and with its remedy (screening
  and mending, *sgrib bcos*), and changes neither the day's tone nor any
  work's verdict. This holds for all of them: the year's and month's,
  and the date's already built, which are shown and not weighed today
  (SPEC §5.13). A work barred only in one direction weighs for neither
  side of the day, as a work qualified by a direction does today (SPEC
  §5.10). It replaces *Counted* (the owner, 2026-10-11: the works an
  earth lord names counted in the weighing, one that names no works
  shown with a grey dot). D4 asked, before the build, since WB's order of strength (vol. 2, p. 376) places
  no earth lord: their rank (last, with the trigram, the date's mewa and
  the link, as the boxes' entries are, SPEC §5.12 and T2.25, or another);
  whether the date's earth lords already built (T2.13's courses, the
  great black day, the black months and Kikang's, shown and not weighed
  today) are counted by the same rule; and how a work avoided in one
  direction counts, as a work qualified by a direction weighs for neither
  side today (SPEC §5.10).
  **Searched for D4, 2026-10-26** ([weighing.md](sources/weighing.md),
  *What the three searches give D4*): no text ranks the earth lords, so
  each of the three is still the owner's choice. The texts searched were
  WB itself, the regent's replies, the manuals, the ground rites, the
  Men-Tsee-Khang almanac and modern works. What they agree on:
  - KP and SY close the weighing of the day's results and then send the
    earth lords to screening and remedy "as the occasion comes".
  - An earth lord bars its place, direction or time whatever the day's
    good, and a good day serves its rite without lifting the bar.
  - The date's earth lords stand among the day's results.
  - A work avoided in one direction is met by place or facing, and is
    never counted against the day.

  The inference closest to the texts is a model with no rank: the
  year's and month's lords stay out of the weighing and bar their works
  in their place with the remedy beside them. D4 takes it. Questions
  408–419 follow from the search; none of them holds the build.
- **Work.** Read ch. 31, pp. 175–221, on the scans (PLAN.md) into a topic
  file: each earth lord with its course (by the year's or month's animal,
  the season or the year's mewa), its place, works and remedy, and what
  reckons it; build what is calculable. A sheet with two tabs, **Year**
  and **Month**, opened from the year's and the month's balloon at its
  own tab, since the balloons are too small for these: each tab the
  earth lords of the shown year or month on a compass, as Rāhu's and the
  *bla mkhyen*'s, each opening its reading and works; the month balloon's
  long entries move into its tab where the owner says. The day page shows
  the year's and month's earth lords beside the works they bar, with
  their remedies, not in the works' verdicts (D4).
- **Block.** [25](#block-25); reads [RD2](#rd2), [RD3](#rd3), [RD4](#rd4), [RD5](#rd5), [RD6](#rd6); waits on [42](#block-42) (built) for the shape its readings are written in ([X1](#x1)).
  [30](#block-30) (P3) waits on it.
- **Built 2026-10-26.** `YearMonthEarthLords` (core) places 44 of the
  year's lords by the year the last winter solstice opens, each on its own
  entry day (question 34), and 55 of the month's by the month's animal;
  `LordTexts` gives each a reading in the shape, written from RD3 and RD4
  by five writers in parallel (the works it bars as wordings, *Remedy*,
  *How it falls* by the engine), not weighed (D4). The app's sheet has a
  Year and a Month tab with a compass of the lords on the twelve animals'
  places and the eight directions; the year's and the month's balloons
  open it at their tab, a row in Also today opens it, and the day in
  brief lists the day's works the lords bar and where. Choices stated in
  SPEC §5.13 for the owner to overrule (the *se ba bla mkhyen*'s outer
  place only; "following" as the year behind; *bum stong* by the month's
  animal). Not built: the lords whose place the text does not fix, the
  palace gods, Gogku, the bride's sky dogs (P3) and the month's lords that
  fall on dates (SPEC §5.13, *Left out*); the KP boxes' *se byi*, *hang
  phan* and the like stay "not calculated", since D4 keeps the lords out
  of the weighing the boxes feed. Checked: `YearMonthEarthLordsTest` (the
  model almanac's twelve heads for the empty vase, the five gods and the
  nine; the move at the solstice; the black years by the year's sme ba),
  `CatalogTest` and `TextsTest` over every lord in a sixty and every
  month, `:core:test` and `:app:testDebugUnitTest` in full; the emulator
  in English and Russian (both tabs, readings, the brief's block, the
  balloon action), at font scale 2.0 and in landscape; the release build
  for raw keys.
- **Done when.** Vectors from the model almanac's month heads (vol. 1,
  pp. 154–171) where it draws them; `DaySummaryTest`'s counts updated;
  `CatalogTest`; SPEC §5.12, §5.13, §10.3; both tabs and the page checked
  on the emulator in English and Russian; the release build checked.

#### <a id="t2-27"></a>T2.27 WB's days of the twelve links — built 2026-10-15 (SPEC §5.11, §10.3)

- **Gap.** T2.22 counts the day's link by vol. 1's table, and T2.25 weighs
  it by the *kun phan me long*'s boxes. WB's own readings of the links'
  days (vol. 2, pp. 351–356, img. 359–364;
  [white-beryl-ch33.md](sources/white-beryl-ch33.md)) are not built: for
  each link's day, from ignorance, the works good and to avoid, washing
  and new clothes, the four directions for one's aims, a birth on it, a
  loss, the course of an illness and a remedy; p. 356 sets the links on
  the hours. Found in the etext on 2026-10-11 and read on the scan the
  same day (RD1, [day-letters.md](sources/day-letters.md), *WB's days of
  the twelve links*): the day is the link the almanac writes by vol. 1's
  table, so these join the link already counted; p. 356 gives only the
  hours, no works, and its last line is open question 19.
- **Work.** Join their works to the link's lists as ch. 34 joins the
  boxes (WB's plain word over the box's, T2.6), so that they weigh where
  the link weighs; the directions, the birth, the loss and the remedy in
  the link's sheet, the illness to H1. p. 356's hours carry no works, so
  they add at most the hour's link to the hours panel, and only once
  question 19 settles the last two.
- **Block.** [24](#block-24); reads [RD1](#rd1), which [26](#block-26) (H1)
  reads too for the links' illness lines; builds on [42](#block-42) for
  the shape its readings are written in ([X1](#x1)).
- **Done when.** A test pins each link's lists to the scan reading;
  `CatalogTest`; `DaySummaryTest`'s counts; SPEC §5.11; both languages.
- **Built.** `LinkVerses` holds each verse's works good and to avoid,
  with washing the head, washing the body and new clothes, and
  `Texts.TWELVE_LINK` joins them ahead of KP's boxes as the day animal's
  verses are joined (one `verseFirst` for both); fourteen new wordings.
  Each link has its reading in the shape of §8.1: the ruling with the
  woodcut's figure, *Setting out*, *Remedy* and *A birth today*, in
  English and Russian; the old paragraph on the table's count is gone
  (the count stands under the sheet's title, the rule in SPEC §5.11 and
  day-letters.md), so X10's allowance fell from 180 to 174.
  `LinkVersesTest` pins each verse's washing and clothes sides and a work
  of each verse to the scan reading, and the join. Over 2000–2049 the
  days whose lists run against their tone go from 2006 to 2047; October
  2026's haircut and wedding orders move, the same days; 4 October's
  friends gain feeling beside the horse, and 28 January's war loses the
  Zon trigram, the sense sources naming taking a town good. Left out, as
  the Work says or as the shape requires: the loss (no row holds it), the
  illness (H1, built 2026-10-15 as the link's Illness row), p. 356's hours (question 19), and the unidentified words
  (day-letters.md, *What the app takes from them*). Checked on the
  emulator in both languages (contact's day, 2026-10-15).

#### <a id="t2-28">></a>T2.28 Corrections from the readings — planned 2026-10-13, built 2026-10-15 but for D11 and D13; the year of age's predictive pebble (RD18, D16) added 2026-10-20 (SPEC §5.9.1, §5.10, §5.13)

- **Gap.** Readings done since the built items they check (the *Left
  open in built work* rows) found entries that the scan reads otherwise
  than the app; each topic file says what changes, and none is built:
  - **Ch. 33's mansion verses** ([RD159](#rd159), `corpus/wb/ch33.md`) — checked 2026-11-06: Anurādhā's good list gained rites for long life («ཚེ་སྒྲུབ», the machine's «ཚེ་སྣུབ»); Jyeṣṭhā's «ཆུ་ཀ་ལེན» stays drawing water channels, the sense RD176 gives it (q649); «རྗོན» (q630) is a dead end and the list stands.
  - **KP's activity table** ([O1](#o1)): the links and sme ba of boxes
    5, 7, 20, 48, 50, 53, 55, 59 and 60 as
    [kp-activities.md](sources/kp-activities.md), *What the table should
    now carry*, lists them, box 53 split into its two halves, and box 53's
    Tiger, Dragon and Ox days and the trigram *zon* moved from the bad to
    the good of both halves.
  - **Ch. 34's works** ([O2](#o2), [white-beryl-ch34.md](sources/white-beryl-ch34.md),
    *Ch. 34's entries that read two ways*): marriage has the 9th, 14th,
    18th and 22nd bad (the 9th added); learning writing and astrology has
    the 1st and 6th bad, not the 16th.
  - **The 9th hair date** ([O3](#o3), [hair-dates.md](sources/hair-dates.md),
    *The 9th's «མོད་འགྱུར»*): `reading.HairDate.9`, now "Youthfulness",
    says that young women become many, in every language.
  - **Tuesday** ([O7](#o7), [weekdays.md](sources/weekdays.md), *O7: what
    the reading by eye changes*): the good list gains saddling horses and
    mules, after horse racing, as the verse orders it, and with it the
    election's weekday list for saddling.
  - **The horse day's witness** ([O8](#o8), [earth-lords.md](sources/earth-lords.md),
    *Change for a build*): `reading.EarthLord.HORSE`'s witness sentence
    adds that on the journey with the corpse no horse is used, and the
    funeral door and the horse's back are avoided, the Russian to match.
  - **Capricorn's combination period** ([O10](#o10),
    [combination-period.md](sources/combination-period.md), *What each
    period means*): WB p. 374 gives Capricorn a good list beside
    its woodcut, which the etext dropped: ordination, sowing, ponds,
    canals and wells, taking on servants, water work, travel, freeing the
    bound, taking a maidservant, work with four-footed animals, setting
    out on the road, lawsuits, seeking lasting things, washing the hair,
    suppressing the sri, theft, planting flowers and trees, killing.
    `Texts.DUS_SBYOR`'s Capricorn gains it as its good list; its avoid
    list (from «རབ་གནས་ལྷ་ཁང») and the verdict "avoid" stay. KP's
    sign-by-hour table is BDRC file img. 81–82, not 79–80, wherever a
    source cites it.
  - **The 65-year table's test comment** ([O10](#o10),
    [month-entries.md](sources/month-entries.md)): the print has ༢༧ in
    column 12 and ༨ in column 15 where the step rule gives 29 and 2 (and
    ༡༣ in column 25, the desired part ༧ in column 18), like-shaped digit
    slips on both scans. `MonthEntriesTest`'s values are the rule's and
    stay; its comment, "read on the scan", says the print's slips instead.
  - **KP's boxes on the scan** ([O10](#o10),
    [kp-activities.md](sources/kp-activities.md), every bullet *The scan
    (O10f) reads otherwise*): box 18 names no khrums in its bad half
    (khrums smad good, the trigram *khon* bad); box 21's good dates 1, 6,
    14, 16, 25, 26 and bad 11, 22, 24 (question 153; its «བྱི» the mouse
    road, not a Mouse day); box 22's 14th on both sides; boxes 28 (Wednesday,
    Thursday), 30 (Friday) and 54 (Sun, Moon) bracketed, neither side; box
    35's bad khrums is khrums smad; box 37's bad half adds the Dog and Tiger
    days and li and khen; box 48's good mansion sa ga, lag bad; box 55's
    mansion lists as the file gives them, KP and KP2 alike; box 56's gro
    bzhin neither and the Tiger day bad; box 33 adds the link 'du byed, box
    49 snron, box 57 sme ba 5, all good.
  - **Ch. 34 beside KP's boxes** ([O10](#o10),
    [white-beryl-ch34.md](sources/white-beryl-ch34.md), *The chapter's lists
    whole (O10f)*): where a box is unread or reads otherwise, WB's work
    gives it: work 3 the Mouse day good; work 14 box 16's three month dates
    (11th month 27th, 12th 25th, 1st 23rd, bad), the bad run 16–19 and the
    good dates (question 156); work 29 box 32's «མི་གཡང་བཟང»; work 30 box
    31's khrums smad; work 31 box 33's second date the 22nd; work 32's good
    sme ba 4 for box 34's 9; work 36 box 39's dates; work 38 bra nye good
    only for taking a widow into service; work 46 box 44's dates; work 49
    KP2 box 51's dates. Which reading the app takes where WB and KP differ
    follows WB above all (SPEC §8).
  - **The eight classes' first spring month** ([O10](#o10),
    [earth-lord-courses.md](sources/earth-lord-courses.md), section 28):
    p. 232 reads «སྦྲུལ་ཐེབས་བཅོ་ལྔའི་ཚེས་ལའང་ཐེབས»; `reading.EarthLord.SDE_BRGYAD`'s
    "and on the 15th too" gains "at the snake hour", the Russian to match.
    No date changes.
  - **The year of age's predictive pebble** — built 2026-10-22 ([RD18](#rd18),
    [verdicts.md](sources/verdicts.md), *What is counted* and *What a tie
    lays*): `YearOfLife.predictive()` counts every pebble and lays none on
    a tie (SPEC §5.9.1, "even, none"). The notes (vol. 2, p. 490), Mipham's
    reading of p. 381, count each place once by its colour, a mother's
    three white as one white, an enemy's two black as one black, and lay a
    speckled pebble on a tie; so does every tie rule of WB and its notes.
    The predictive pebble becomes white, black or speckled by places;
    SPEC §5.9.1's "even, none" and "Gyurme Dorje's chart 6.2 lays its
    predictive pebbles otherwise" are rewritten (chart 6.2 is the notes'
    rule). A place with both colours counts as speckled; a speckled
    pebble reads WB's "white and black, two" reading with Mipham's note
    (D16, decided 2026-10-20). The catalog's Russian follows.
  - **The marriage match concluded** — built 2026-10-22 as R3o–R3q but
    for R4's part ([RD21](#rd21),
    [verdicts.md](sources/verdicts.md), *The marriage match concluded*;
    block [31](#block-31) left point 7 and the bad years' whole unbuilt): point 7, the
    siblings' number (p. 359; the notes, p. 525), is now read: each
    spouse's life force, body, power, luck and birth mewa counted by
    element (wood 1, earth 2, water 3, fire 4, iron 5) and summed; the
    larger sum wins, equal is good, the man defeated takes Mañjuśrī's
    wheel; the two sums together even agree, odd disagree. The bad years
    (pp. 353–354), shown per spouse, gain the notes' one deciding pebble
    for the couple: black if both are born in them, speckled if one,
    white if neither, a woman's bad year above all (p. 524). Each aspect
    of the single match can name its kind, a spouse of karma, kin or
    enmity, the inner meetings of earth and water karma and of fire, iron
    and wood kin (p. 355; Sangye Gyatso's reply 113; the notes, p. 524);
    the whole stays "in general", no sum of kinds. For R4 (block
    [58](#block-58)): a bad birth reckoning of either spouse overrules a
    good match (p. 364; the notes, p. 526).
  - **Block 31's open points read** — the hidden and the aspects' mewas
    built 2026-10-22 as R3n; point 10's relations and `RaisingMonth`'s
    orphaning month built 2026-11-05, the moment as R2z ([O16](#o16), open questions
    161–165; [marriage.md](sources/marriage.md),
    [year-of-life.md](sources/year-of-life.md),
    [reckonings.md](sources/reckonings.md), *Open questions 161–165
    (O16)*): the match's hidden (point 4, R3c) and the four aspects'
    mewas (R3) can be built: the body's mewa is the birth mewa, the life
    force's "four up" the birth mewa less three, the power's "four down"
    plus three, counted round 1–9; the luck's the mewa of the triad's
    wind-horse year in the same sixty, by WB p. 255 its birth mewa (the
    *Moonbeams* its life-force mewa; question 234); the body-cut wheel
    holds li–khon, zon–dwa, gin–zin, kham–khen (the notes, p. 524). Point
    10's relations (R3c): four-executioner three places either way
    (p. 235), grave the tomb of the life-force element (p. 258), taken
    between the spouses' months, days and hours, and with them the
    enemy, «དགྲ་དུར» being enemy and grave (question 235, answered by
    [RD42](#rd42), 2026-10-23: the notes' gloss of the same compound, p. 478). Raising the child (R2k): «དྭ་ཟླ» is the orphaning month, so
    `RaisingMonth`'s "heavy for the father / mother / the child" may read
    as the month that orphans the child of the father, the mother, and
    the child's own (question 236 on the notes' line); the mansion stays
    unbuilt, Revatī named both very bad and accomplishing (question
    237). The moment (ch. 30, not built): the date's trigram starts by
    the month's triad (li, zin, dwa, kham) and runs forward, its mewa
    1, 4, 7 by the month's place in its season and up one a date; the
    day's and night's lengths divided by eight and nine from dawn
    (questions 238, 239).
  - **The moment's fighting mewa and earth's own place** ([RD145](#rd145), 2026-11-03,
    [reckonings.md](sources/reckonings.md), *The moment's examinations*): the
    mewa that fights the nine squares (p. 165) is the power-mewa, which Mipham's
    notes put four down from the moment's mewa («དུས་ཀྱི་དབང་སྨེ», 5 yellow
    gives 8 white), not the year's mewa itself, where `Moment.squares` takes the
    year's; and the own-place lines name earth only among the results, so the
    built centre has no line behind it. Undecided until the owner rules.
- **D16, decided 2026-10-20 (the owner: Mipham's word above every source but WB, SPEC §8; the speckled pebble's reading after [O17](#o17)–[O19](#o19))** ([RD18](#rd18), open questions 170–172,
  [verdicts.md](sources/verdicts.md)): what a place with both colours
  counts as (a son's meeting, one white and one black; the breath-taking
  and womb stages) no text read says. Gyurme Dorje's chart 6.2 counts a
  son's meeting black; the notes on the dead (p. 502) put a mixed year
  with the speckled. And a speckled predictive pebble has no reading of
  its own in pp. 384–385; the decisive pebbles' "white and black, two"
  (pp. 381–384) may be the one meant. Whether the app counts a mixed place
  as Dorje does, leaves it out of the count, or waits for a reader of the
  tradition, and whether a speckled pebble shows the "white and black,
  two" reading or none, is the owner's; until then the count stays as
  built.
  O17 (2026-10-20, [verdicts.md](sources/verdicts.md), *D16: the worked
  examples*): the notes' own table (vol. 2, p. 489) rules out a son
  meeting counted black, which Gyurme Dorje's chart 6.2 needs; no single
  count fits both (question 186).
  O18 (2026-10-20, *D16: the Moonbeams*): the *Moonbeams* lays a son's
  meeting, and the breath-taking and womb stages, as a half (ཕྱེད་མ), one
  pebble apart from both colours, which fits the notes' table counted as
  left out or as one of each, not Dorje's chart; how a half counts and
  what a half verdict foretells it does not say (questions 188–190).
  **Decided** (the owner, 2026-10-20): Mipham's notes decide where WB is
  terse, so Gyurme Dorje's count (a son's meeting black) is not followed.
  A place with both colours, a son's meeting and the breath-taking and
  womb stages, counts as speckled: Mipham puts a mixed year "with the
  speckled" (vol. 2, p. 502, read on the scan by RD18) and counts each
  place by its colour (p. 490), and a speckled place adds to neither side;
  his own table (p. 489) comes out so in every row (O17), and the
  *Moonbeams*' half agrees. The build counts white and black places only
  and lays a speckled pebble on a tie. **Open**: what a speckled
  predictive pebble foretells (questions 172, 190); until a reading is
  found, how the app shows it is the owner's, once O19 reports.
  O19 (2026-10-20, *D16: WB, the notes and the manuals*): Mipham (vol. 2,
  p. 495) lays a son meeting as one speckled pebble counted for neither
  colour, which the decision above already follows. For the reading: WB's
  pp. 381–384 "white and black, two" readings, which WB calls the ordinary
  verdict, are the speckled verdict's in the *Good Vase* and the Drukpa
  *Mirror*; WB's illness chapter reads a speckled verdict as undecided,
  with rites; Mipham calls the speckled "somewhat weak", leaning to the
  bad (questions 172, 190, 191).
  **Decided** (the owner, 2026-10-20): a speckled predictive pebble reads
  WB's "white and black, two" reading for that aspect (vol. 1, pp. 381–384,
  the ordinary verdict, with its rites), and beside it Mipham's note that
  a speckled pebble, counted for neither colour, is "somewhat weak" if it
  comes, roughly with the bad (vol. 2, p. 495, said of the illness
  reckoning's pebbles; named so). Questions 172, 190 and 191 stay open
  for a reader of the tradition; the build does not wait on them.
- **D17, decided 2026-10-25 (the owner: by Mipham, as D16): the year of age's obstacles read through the aspect they meet** ([RD20](#rd20),
  [verdicts.md](sources/verdicts.md), *The year of age gathered*; R4, block
  [58](#block-58)): Mipham's notes (vol. 2, pp. 492–495) read each obstacle
  of the year through the person's aspects: a harsh year is harsh or not
  "by fighting that year against one's own year's vitality and the rest";
  a tomb year met as the own tomb itself or the slayer's tomb as enemy is
  a great danger, as mother, friend or son a small one; the undertakers are
  bad when they meet an aspect as enemy; the *dza zor* without cutting the
  vitality seems to be no fault; the nine-multiples' tomb years bad as
  oneself or enemy, mild otherwise. The app shows every obstacle WB lists
  for the year, each with its reading. Whether the year of age's Together
  grades or leaves out an obstacle by the notes' meeting, and for the harsh
  years which meeting makes one harsh (the notes name the fight but not
  its count), is the owner's; the household round waits besides on open
  question 258 (its zigzag and fold for a household other than five).
  **Decided** (the owner, 2026-10-25): the notes decide, as in D16. The
  year of age's Together grades each obstacle by the meeting the notes
  give it, the vitality first: a tomb year met as itself or as enemy a
  great danger, as mother, friend or son a small one (p. 493); the
  nine-multiples' tomb years bad as itself or enemy, mild otherwise
  (p. 494); the undertakers bad where they meet an aspect as enemy, and
  the *dza zor* a fault only where it cuts the vitality (p. 494). The
  harsh years are graded by the same fight: the notes gloss the triad's
  "in decline" as "meeting the enemy" and have the thirteenth examined
  for whether the current year cuts the body and the power (p. 492), so
  a harsh year is harsh where the current year meets the aspect as enemy,
  not otherwise. An obstacle the notes clear (no fault, no enemy meeting)
  is left out of the Together, as outweighed factors are left out of the
  brief; the sections below still show every obstacle WB lists, each
  with its reading. No count the notes do not make: the obstacles are
  graded, not summed. The household round still waits on question 258.
- **D18, decided 2026-10-25 (the owner): the dead's trigram and mewa are the descended ones at death** (open questions 260, 272;
  [claims/dead-trigram-fallen-to.md](sources/claims/dead-trigram-fallen-to.md)):
  where ch. 29 says "died on li" or "the trigram and mewa of death" without
  saying whose, the build takes the dead's trigram and mewa of the year of
  age at the year of death, as the regent's reply 152 reads the tenth
  examination and as the first examination's roots already do (the notes,
  p. 500). The questions stay open for a reader of the tradition.
- **D19, decided 2026-10-25 (the owner): the circles count the years inclusively** (open question 301;
  [claims/circles-zg-worked.md](sources/claims/circles-zg-worked.md)):
  "as many years as lived" from a circle's starting year is the app's age,
  the starting year itself the first, so the year reached is the start
  plus the age less one, as Zhechen Gyaltsab's worked example and UT's
  count of years fit.
- **D20, decided 2026-10-25 (the owner): the circles are built without their mewa pebble** (open question 368):
  the four settled pebbles of each circle, life force, body, power and
  luck, are built with their readings; the mewa pebble waits until how
  its two mewas are joined is read.
- **D21–D23, decided 2026-10-25 (the owner): the thirty starting points as the circles** (open questions 259, 301, 306;
  [claims/thirty-circles-whose-element.md](sources/claims/thirty-circles-whose-element.md),
  [claims/thirty-count-years.md](sources/claims/thirty-count-years.md),
  [claims/thirty-board-groups.md](sources/claims/thirty-board-groups.md)):
  D21, each point's pair of years is taken in the element related to the
  person's power, the birth year's own element, as the notes gloss the
  same phrase for the circles; D22, the count from the pair's year runs
  inclusively, the pair's year the first, down from its male year for a
  male birth year and up from its female year for a female one; D23, each
  point's check n is the eight fighting pebbles' pebble n of p. 453, as
  the notes' grouping of the checks (1, 3, 5; 2, 4, 6; 7, 8) fits.
- **D24, decided 2026-10-25 (the owner): the moment's element is the present hour's power** ([claims/moment-asker-year.md](sources/claims/moment-asker-year.md),
  [claims/moment-eight-places.md](sources/claims/moment-eight-places.md)):
  the *bya ra* of ch. 30's later reckonings (pp. 166–170), which the notes
  gloss as the moment's power, is the element of the present two-hour
  sign, the one the hour's element readings already reckon.
- **D13, the owner's word first** ([O10](#o10), open question 150,
  [month-entries.md](sources/month-entries.md), *Kikang's black month*):
  `reading.EarthLord.KI_KANG_ZLA_NAG.RABBIT` and `.BIRD` say "at sunrise"
  on «ཉི་ཤར»; the etext, Yigdzin-1 and the Gser thang reprint at 4–7× read
  «ཉི་མྱུར», whose time of day is not settled. Whether the two readings
  drop the time until a reader of the tradition answers, or keep "at
  sunrise", is the owner's; until then they stay as built.
- **D11, the owner's word first** ([O10](#o10), open questions 141–143,
  [lunar-dates.md](sources/lunar-dates.md)): three lunar dates as built
  rest on readings the scan does not bear out. Date 6's "No more children
  follow one born today" rests on a «མེད» the scan does not print
  («སྐྱེས་བསྟུད», read on dates 1, 11 and 21 as "children follow one
  another"); date 3's "Whoever is born today" is «གང་བསྟུད», "whichever
  boy or girl follows"; date 1 reads «ལམ་ཞུགས་རྟེན», where «རྟེན» may be
  a work of its own, making supports. Whether the app follows the scan
  now, in the plain sense of its words, or keeps the line out until a
  reader of the tradition answers, is the owner's; until then the three
  stay as built.
- **Work.** The lists in `Texts` and the table's boxes; the two readings
  in the catalog with their Russian; D11's three dates once it is
  settled. No reading is needed: all of them are done.
  - **Ch. 27's words** ([RD75](#rd75), [RD67](#rd67); `corpus/wb/ch27.md`): «ན་ཟླ་སྟོན་མོ» is the age-mates' feast and «འུར་ཞོགས» an alarm (not a raid, if the letter is འ; q366), «ཚད་ཟླ» is «ཚང་ཟླ» in ZG; the «བརྗེ» set is the spiritual friend's in ZG (q365); wherever the app renders these, the wording changes. Waits on the owner for q366's letters.
  - **Ch. 34's mtho** ([RD73](#rd73); `corpus/wb/ch34.md`): ch. 32 and ch. 34 agree on four mtho to the navel at twelve finger-breadths each, so a build needs no plot length; work 24 reads «སྐྱས» (gifts); q438's «རང་མཐོ» stays open.
  - **Ch. 33 p. 313's planets** ([RD79](#rd79); `corpus/wb/ch33.md`, wb.2:p313): a direction rule for war and journey (the eight planets round the directions from the east) and the weekday's rising dates by animal triad (tiger, horse, dog Saturn's; mouse, dragon, monkey Mars's; bird, ox, snake «ཕུང»'s; pig, sheep, hare the Moon's), new to the app; machine state only, so a build waits on a reading by eye and on q449–q453 (the owner decides whether the app takes them).
- **Rule from here on.** A reading that finds a change for a build adds
  it to this item's list, with its row and topic file, and the item
  keeps a block until every entry is built; the readings' launcher does
  it while bookkeeping (sources/PROTOCOL.md).
- **Block.** [50](#block-50); reads [O1](#o1), [O2](#o2), [O3](#o3),
  [O7](#o7), [O8](#o8), [O10](#o10); D11 for the three lunar dates, D13
  for Kikang's time. [43](#block-43)
  (X3, X4) and [46](#block-46) (X7) reword these readings and build on
  it.
- **Done when.** A test pins each changed list to its topic file;
  `DaySummaryTest`'s and the election's counts updated; `CatalogTest`;
  SPEC §5.10 where it describes the boxes; Tuesday, the 9th hair date and
  a horse day on the emulator in both languages.
- **Built** 2026-11-05 (block 50): point 10's relations and the
  orphaning month. The two births' month and day animals read for enemy,
  grave, small grave, seventh and four-slayer, all mutual, with the rite
  against parting and the Ratnakūṭa (`MatchReckoning.timeMeetings`, family
  `MatchTime`, SPEC §5.9.3); the hours wait, the app not asking a person's
  birth hour. 46 of the 144 ordered pairs of animals agree. `RaisingMonth`
  names the father's, the mother's and the child's own orphaning month: the
  father's by which the mother dies, the mother's bad for the father, the
  child's by which both die (the notes, pp. 488–489; the mother's on
  p. 489 is stated, the other two the reading of
  claims/orphaning-month-kills-the-other-parent.md, question 236).
  Checked: `BirthTest` (the four-slayers, the notes' mouse and dragon, the
  small grave, a free pair, every relation mutual), `CatalogTest`,
  `:app:testDebugUnitTest`, the emulator in English and Russian (a couple
  of 1976 and 1978, agreeing months and days on a small grave, both
  sheets; a man of 1984 in the mother's orphaning month), the release build
  in both.
- **Built** 2026-10-22 (block 50): the year of age's predictive pebble
  by places (`YearReckoning.places`, `predictive` now white, black or
  speckled), a speckled pebble reading WB's one white and one black of its
  aspect with its rites and the notes' "somewhat weak" (four new readings,
  English and Russian); the grid's count and each predictive row now by
  places. Checked: `YearOfLifeTest` (chart 6.2 recounted: vitality
  speckled 3:3, body and destiny white, luck black), `CatalogTest`,
  `:app:testDebugUnitTest`, the emulator in English and Russian (a fire
  dragon man, 2026 and 2030, the speckled destiny's sheet). No release
  build: no enum key added.
- **Built** 2026-10-15 (block 50), every entry above but D11's and D13's:
  - The print's boxes (`Electional.PRINT`) as O1's table and O10f's
    bullets read them: the links of boxes 5, 7, 20, 50, 53 and 59, the
    sme ba of boxes 48, 50, 55 and 57, box 53's good column, and the
    scan's readings of boxes 18, 21, 22, 28, 30, 33, 35, 37, 48, 49, 54,
    55 and 56. Box 5's bracketed craving is middling by WB's work 4
    (question 103 answered); box 21's 11th, 22nd and 24th are bad for
    breaking in oxen only, which WB's work 9 qualifies too, so they count
    for neither side whichever way question 153 is answered.
  - Chapter 34 beside the boxes (`WhiteBerylWorks`): marriage's 9th,
    learning's 1st and 6th and the link formation, work 3's Mouse day,
    work 9's dates (the 19th for the box's 14th; its 29th, built as plain
    bad, is qualified and goes), work 14's 19th for the box's 20th and its
    three dates of one month (Hor 11/27, 12/25, 1/23, `Factors.monthDates`,
    joined to the date's lists in that month by `Texts.electionalDate`),
    work 32's sme ba four for the box's nine, work 38's bra nye
    qualified. What the chapter reads otherwise or calls middling is
    taken from both sides (`WhiteBerylWorks.NEITHER`). The chapter's
    notes add, plainly named: the link feeling for servants (work 38),
    ageing and death for bathing (34), contact for fire offerings (45,
    where the box qualifies it), the trigram li for bloodletting (33),
    the 14th bad for sowing (23). Works 30, 31, 36, 46 and 49 were
    already joined (T2.6); work 29's «མི་གཡང་བཟང» qualifies lag and
    gro bzhin, which box 32 had on neither side already. Work 14's good
    run is the 21st to the 25th since 2026-10-18 (question 156: WB's own
    usage reads the run, KP the 21st and 25th alone; the owner chose the
    run), and a month's own date outweighs the plain date on the other
    side (the 12th month's 25th, the 1st month's 23rd, bad).
  - Tuesday's good list has saddling after horse racing; the 9th hair
    date reads "Young women become many"; the horse day's witness has
    the journey with the corpse, in its *A death today* row (X7); the
    eight classes strike on the 15th "at the snake hour"; Capricorn's
    period has its good list, with three new wordings (freeing the bound,
    taking a maidservant, work with four-footed animals); KP's
    sign-by-hour table is cited as img. 81–82; `MonthEntriesTest` names
    the print's four slips. All in English and Russian.
  - Tests: `ElectionalTest` (the boxes read again; the chapter where the
    boxes are unread or read otherwise; a date of one month), the counts
    (days against the tone 1946 → 1945, after the clothes split; 28 lists name a link), the
    Kṛttikā day of 2026-01-28 (the Zon trigram and the sense sources now
    name war good, outweighed); `TextsTest` checks the combination
    periods' and the month dates' wordings too. `CatalogTest`'s
    allowances unchanged.

#### Order

1. T2.1, T2.2 and T2.3 are built.
2. T2.4, the display half: built 2026-10-07.
3. T2.7 and T2.8 built 2026-10-07.
4. T2.5 built 2026-10-07; T2.6 built 2026-10-08.
5. The reading half of T2.4 built 2026-10-10; T2.9 built 2026-10-09.
6. WB above all: T2.10 built 2026-10-08; then
   T2.11 (built 2026-10-09; it added T2.20 and T2.21), then the small ones T2.16 (built 2026-10-08), T2.17 (built 2026-10-08) and T2.15 (built 2026-10-09),
   then T2.12 (built 2026-10-08), T2.14 (built 2026-10-09) and T2.13 (built 2026-10-09);
   then T2.20 (built 2026-10-09), T2.21 (built 2026-10-10) and T2.22 (built 2026-10-10), T2.24 (found and built 2026-10-10); T2.23 built 2026-10-10; T2.25 built 2026-10-10.
7. T2.19 built 2026-10-08; with it E6's "For you" half has its enemy weekday.
8. Found 2026-10-11, checking the page against WB's own almanac and
   chapters: T2.27 (built 2026-10-15), then T2.26 (D4 settled and built 2026-10-26).
9. Found 2026-10-12 and 2026-10-13 by the O readings: T2.28, before the
   X blocks that reword the same readings; built 2026-10-15 but for D11 and D13.

#### Not planned

- The day by the clan's element (*rus chen*, WB ch. 31, p. 225): it needs
  a Tibetan patrilineal clan, which the app's readers do not have, and WB
  confines it to reckoning for the dead.
- Whatever needs a clan, of anyone (the owner, 2026-11-06: only Tibetans
  have one, and 2026-10-15): the death's eye's thirteen root pebbles and
  verdict, the years counted together on the same pages (cut-off, power,
  mother, friend), the clans' strikes of p. 114, and the special pebble
  and what cuts the father (R2b, R2aa), built and taken out in block
  [72](#block-72). No decision waits here; a reckoning that asks the clan
  goes without it, and the app asks no one's clan.
- KP box 47 (averting rites, every entry a kind of rite) and KP's charts
  (SPEC §5.10).
- Rāhu's course by the hour on the hours panel: the text names times of
  day, not clock hours (SPEC §5.13).
- WB's yoga ranking verse (question 1): the yoga's dot comes from its
  other verses (SPEC §5.11).
- From the calendar compared in [tibetastromed.md](sources/tibetastromed.md),
  what WB does not support: its "old style" (the month animals from the
  tiger; WB's model almanac gives the 1st month the dragon), its
  "protectors of the teaching" (no such course in WB), the house god's
  10-day cycle (WB's *khyim lha* moves by season, p. 204), the sky
  medicine by the day's trigram (WB's *gnam sman* is by one's own year,
  vol. 1, p. 401: T4 material), its weekday groups by birth year, and one
  column only of the special days' table (WB gives both).
- Anything resting on Bon texts alone (the owner, 2026-10-08): SN's six-day
  cycle for setting out, its eight classes by date, its six gods and six
  black days (*gdags kyi lha drug dang nag drug*), Bon festivals, and
  Khyung sprul's *dpyad gsum dag rtsis*, a reformed calendar arithmetic of
  its own (the calendar's «Че сум так ци»).

### <a id="t3"></a>T3. Element colours — built 2026-10-09 (SPEC §10.3)

The sme ba shows in the colour of its box (SPEC §10.3), the four aspects
with the pebbles of the year, month, day and hours are built (SPEC §5.9),
and so are the personal mansions (SPEC §5.8). What is left waits on design:

- **Element colours:** wood green, fire red, earth yellow, iron white, water
  black or blue (Berzin 4; first cited here as Berzin 3). The day's element appears only inside the day
  line, so it first needs a place of its own on the page.
- Whole days stay uncoloured, as on the 旧暦 page (SPEC §10.4). The element
  pair already marks four of its ten pairs as inauspicious.
- **Built.** The day's element is weighed nowhere: the element pair is
  the weekday's and the mansion's, vitality and body count the lunar
  date's element. The owner chose (2026-10-09) to move the whole day line
  down: weekday and planet were already the Almanac's weekday row, and
  the day sign ("Earth Bird") is now the first row of the Lunar day
  section, its element glyph in colour, its balloon giving element,
  animal and gender. The colours are not Berzin 3's, which names none,
  but Berzin 4's: the sme ba numbers' colours with their elements (iron
  white, water black, "black is equivalent to blue", wood green, earth
  yellow, fire red). Water is drawn blue, and wood and fire lighter than
  the printed boxes, so that a thin glyph reads on the dark page. Only
  the day sign's glyph is coloured; the year line and the combination's
  element glyphs stay muted.

### <a id="t4"></a>T4. Divination — built 2026-10-09 (SPEC §5.9.1, §10.3)

- **Built: the element calculation (*'byung rtsis*) of the year of age**,
  from WB vol. 1, chapter 21 (pp. 255–258) and chapter 24 (pp. 380–416),
  read with Yigdzin-1 and MITRA and the rules on the scan, with the
  *Moonbeams* (KD vol. 3) where WB is silent
  ([sources/year-of-life.md](sources/year-of-life.md)): age in the Tibetan
  count; the natal mewa (the 九星 year count, confirmed by WB's 1687 and
  Gyurme Dorje's 180-year captions); the mewa of the year of age (WB's
  circuit by the birth year's gender, every cell of Dorje's Table 2.11);
  the trigram of the year of age and the progressed sign by the person's
  gender; the 24 decisive pebbles of the six basic signs, the hour of
  reckoning being the present one, with the predictive pebble of each
  aspect by WB's count (chart 6.2's 24 cells as the vector); the twelve
  sectors of growth and decline with their pebble ranks; the harsh years,
  the nine-multiples and the mewa's four small obstacles; every result
  with WB's reading, in English and Russian. Shown in a year sheet from
  the year's balloon. Checked on the emulator in both languages (a man of
  1976, a woman of 1990 in her own-animal 37th year, a person without a
  gender) and in a release build.
- **Left, with why** (each in the sources file; the owner accepted these as outside block 13 on 2026-10-09, and chose not to ask for the mother's birth year):
  - the natal trigram (*skyes spar*): the mother's trigram in the year of
    the birth, which needs the mother's birth year, a further personal
    datum for the owner to decide on; with it the secret obstacle (*gsang
    keg*) and the natal trigram readings (WB ff. 180b–182a) would follow;
  - the obstacles of spouses (*bza' shug*), of the parents' and ancestors'
    deaths (*dur keg*), and the household readings: they need the other
    people's signs;
  - planned since 2026-10-11, the two above on relatives added to each
    saved person (P1, P2), and the year of age moved to the Reckonings
    section (R1);
  - the progressed sign's four counted signs (lifeline, peg, sky and earth
    extension, p. 387): WB does not say which aspect of the counted sign
    meets the subject's, and Dorje's chart 6.3 reads otherwise;
  - built since (2026-10-09, the owner's word): the rest of chapter 24's
    obstacle years, pp. 409–416: the combined nine-multiple, the
    nine-multiple meeting the tomb, the trigram's and the mewa's
    nine-multiples, the four tomb years and the black undertakers, the
    progressed sign on a tomb, the tomb sign, the mewa's doors and the
    aspects in decline, each with its reading; checked on the emulator
    (a woman of 1990 in 2021, her own great tomb and a black undertaker; a
    man of 1976 in 2020, his 45th, the fourth combined nine-multiple);
  - not built, with why: the clan's nine-multiple (no clan); the mewa's
    nine-multiple readings by kind of person, which WB gives as what
    "some hold"; the month's tomb days, which are days with no reading;
    the coloured and household pebbles (pp. 385–387, 408); the Chinese
    pebble divination after p. 416, a casting, not a reckoning.
- **Mo** (Mipham's dice: two throws of the ARAPACANA die, 36 outcomes; other
  systems use three dice or a mala) has no calculation beyond a random draw.
  Every reading is a text, and the English ones are copyrighted
  translations (Goldberg, *Mo*, Snow Lion 1990). Berzin notes it is done in
  a meditational context after a retreat. Not planned.

### <a id="t5"></a>T5. Personal settings — built 2026-10-09 (SPEC §10.5)

The gender (not set, male or female) belongs to each saved person, asked
for in the person's dialog under the birth date and stored with them on
the device; a person saved before reads as not set, and while it is not
set the readings that need it are hidden, with a note that says so. Only
the year of age reads it (its trigram and progressed sign).

### <a id="m1"></a>M1. Meditation timer and periodic bell — built 2026-10-09 (SPEC §10.5, §10.9)

Mind Bell's functions on a screen of its own, opened from the menu's new
Practice group, written for this app rather than forked: Mind Bell is
Java of the Google Code era, and its one sound file names no source.

- **Timer:** a foreground service (`specialUse`) with a partial wake
  lock rings a sitting's bells by the elapsed-realtime clock, with the
  screen off; its notification counts down and carries Stop.
- **Mindfulness bell:** fixed or random intervals within active hours on
  chosen weekdays (`BellPlan`, `BellTest`), one exact alarm at a time set
  again after each ring, a reboot, an update and a clock or zone change;
  quiet during a sitting and, by default, a call, another app's playback
  (2026-10-11, owner's request: its own switch, checked on the emulator
  with YouTube Music playing and a call ringing and answered) and a
  silenced phone.
- **Bell notice** (2026-10-11, owner's request): each bell rings in a
  short foreground service, as Android 17's AudioHardening muted the
  alarm-fired bell in the background; its heads-up notification shows
  the bell's message (by default ཨོཾ་མ་ཎི་པདྨེ་ཧཱུྃ, the owner's choice)
  and goes with the strike. Checked on the emulator by the real exact
  alarm: playback no longer muted, the notice up over the home screen
  and removed 8.3 s later; the message cleared and set again by Default.
- **Sound:** three bells synthesised on the device (`BellSound`), no
  sound file to licence; alarm stream, other sound ducked.
- **Sessions and presets** (2026-10-09, owner's request): a warm-up and
  periods of any length, minutes and seconds, the wood block (also
  synthesised) between periods, in place of the fixed choices and the
  bell within a sitting; plans saved as named presets. On the emulator a
  7 s warm-up and periods of 20 s and 15 s rang the bowl at 7 s, the
  wood block twice at 27 s and the bowl three times at 42 s, the
  notification following the periods; a preset replaced by name and
  set again by a tap.
- Checked on the emulator in English and Russian: a one-minute sitting
  rang on time with the screen off and stopped its service; the bell rang
  at 14:15:00 exact and set 14:30, rang from a cold process, stayed
  quiet on silent, and switching it off cleared the alarm.
- **Keep the screen on** (2026-10-11, owner's request): a switch under
  the session's settings; from Begin the screen stays on, past the last
  bell and Stop, until the meditation screen is left. Checked on the
  emulator by the window's `KEEP_SCREEN_ON` flag in `dumpsys window`:
  off at rest, on through an 8 s sitting and after its last bell, on
  after Stop, off once the back arrow left the screen and when it was
  opened again; never on with the switch off; the switch stored on tap,
  shown in English and Russian.

### <a id="m2"></a>M2. The prayer wheel — built 2026-11-04 (SPEC §10.5, §10.12)

The owner's request of 2026-11-04, after an old live wallpaper no longer
offered on current Android: a screen in the Practice group and a live
wallpaper, one painter for both. Its forms and colours (the owner's
answers the same day): both a drum and a ring, the colours to choose;
several mantras, not one; a slow turn by itself. The owner first chose
that a finger against it should only brake it, and reversed that the same
day, once it stood on the phone: a tap on the home screen or a page swipe
stopped the wheel, "ideologically and logically wrong". With that, a mode
with only the wheel on the screen, and the option of keeping the screen on.
Then, tried on the phone again, the final shape: tap and hold holds the
wheel, release lets it rotate slowly, the home button and a tap accelerate
it a bit, a swipe left or right a lot.

- **Checked on the emulator** (Android 17): both forms and every mantra
  in English and Russian, an own line, font scale 2.0 and landscape in both,
  a turn of the phone keeping the screen; the wallpaper set through
  Android's page, its speed logged at rest (no speed-up), under a flick
  (3.6 rad/s) and a page swipe (the most, 12.5 rad/s), no frames while an
  app covers it, a change of colours reaching it at once. `WheelTest`
  holds the turning, the layout and the colours' contrast.
- **Tap, swipe, hold and only the wheel (built 2026-11-04), checked on
  the emulator:** the speed logged on the screen and the wallpaper alike:
  a tap to 0.3 turns a second, swipes left and right to over 1.1, a hold of
  2 s at 0 for its length, also from a fast turn, and the slow turn (0.033)
  on release; the home button to 0.3, from an app and on the home screen.
  Only the wheel: no bars and no words on the
  screen, upright and on its side, back returning to the settings; the
  screen's flag set only with the option on, and gone after back, also
  after a turn of the phone and a trip to the home screen. That last case
  is why the window's flag is used: `view.keepScreenOn` stayed set after it.
- **Direction:** read 2026-11-05 ([RD160](#rd160), `sources/prayer-wheel.md`).
  Glag bla Sönam Chödrup: a wheel turned with the way its text is laid out
  reads it forward; turned against it, it reads from the tail, a fault and
  no benefit. Milarepa's songs do the Dharma circuit from the right.
  Counter-clockwise belongs to named rites and classes (the mother tantras,
  Cakrasaṃvara and fierce works) and no passage gives one rule for all
  mantras, which fits block 65's caution; the app's text is unchanged.
  Ladner's *The Wheel of Great Compassion* (Wisdom, 2000) could not be
  reached.
- **Left open:** an ornate wheel, a drum with its rim and body drawn, as
  a third form (the owner: "maybe in future").
- **Counter-clockwise for a line of one's own (the owner, 2026-11-04),
  built 2026-11-05 (block [65](#block-65)):** a few special mantras must rotate counter-clockwise. They
  are usually restricted to practitioners who have received the proper
  teachings and the empowerment (དབང, dbang), so none is offered in the
  list: the seven all turn clockwise (the owner), and the option stands
  only with "Your own", off by default. Beside it a comment, in the owner's
  words: reversing the rotation is "an act that the practitioner must
  clearly understand, it is not deliberate". The direction of a mantra
  circle is usually given orally during the empowerment, when the lama
  (བླ་མ, bla ma) describes the sadhana's visualization, and the wheel must
  match that strictly: so the app never proposes a direction for a line,
  and the comment sends the practitioner to what was given. That comment is the owner's
  caution, the only text on the screen about what the turning means (§10.12
  otherwise keeps it free of any, §8). Its English, settled by the owner:
  "The direction of a mantra is the one given by your lama with the
  practice. Reverse it only if that is what you received." The Russian,
  shown to the owner at the build: «Направление мантры — то, которое дал
  ваш лама вместе с практикой. Меняйте его, только если получили именно
  такое.» Checked: `WheelTest` (a reversed look draws the angle the other
  way, a plain one the same), the emulator in English and Russian (the
  switch and caution only under "Your own"; the drum's front moving about
  25 px right to left with it off and 32 px left to right with it on,
  measured between two frames). The wallpaper draws through the same painter
  and was not run with it on.

## UX

### U. The Tibetan day page: weight made visible

A review of the page against SPEC §5.12 (2026-10-08; the emulator, Kyoto,
8 October 2026, the test birth date). The weighed readings stand in the
Phugpa order of strength, as §5.12 sets it, but the page does not show
what weighs what: rows that are not weighed stand inside the weighed run,
an unweighed personal row opens the list, one voice is two rows with
opposite dots, and nothing names the factor that decided the day. The
brief's lists run long and say the same voices on line after line. No
item changes a side or a tone; each changes SPEC §5.12 (*Order on the
page*) and §10.3 with the code. U2 is built, U1 and U3 built together (block 1), U4 and U5 (block 3).

#### <a id="u1"></a>U1 One row for the combination — built 2026-10-09 (SPEC §5.12, §10.3)

- **Gap.** The named combination (*'phrod chen*) and the element pair are
  one voice (§5.12), yet two rows with their own dots: on 8 October 2026
  *Pestle* is red and Wind–Fire green, so the combination has no tone, and
  the page did not say so. Rāhu's courses and the special days, each one
  voice, were already one row each.
- **Built.** One "Combination" row («Сочетание»): both parts' dots side by
  side, the two elements' glyphs, the subtitle naming both and whether
  they agree ("Pestle · Wind–Fire: the parts disagree, no tone", "Friend ·
  Earth–Earth: both lucky", "Raven · Fire–Water: both unlucky"), the
  "decides" mark once, on this row. Its sheet says it is one voice and
  when it decides (WB vol. 2, p. 333), then gives each reading in turn
  with the combination table and the element grid, as the special days'
  sheet does.

#### U2 The deciding factor named — built 2026-10-08 (SPEC §5.12, §10.3)

- **Gap.** The brief's tone said "the strongest factor that takes a side
  decides" without naming it (Thursday on 8 October 2026), while the
  haircut row named its own ("avoid · by Thursday"). Nothing in the
  Almanac linked a row to the brief's verdict.
- **Built.** The weighing keeps its deciding voice (`DayVerdict.factor`,
  `.deciding`). The In brief row says "a lucky day · by Thursday" («решает:
  Четверг»), or "by the combination"; the brief's sheet names it with its
  kind ("Thursday (weekday)"); that row in the Almanac, both rows where
  the combination decides, carries a "decides" mark outlined in its tone,
  and its sheet says so under the gloss. Over 2000–2049: the combination
  9,672 days, the weekday 7,708, the special days 568, the date 292, the
  karaṇa 23, the yoga none (`DaySummaryTest`). Checked on the emulator
  in English and Russian on a day of each kind, lucky and unlucky, with
  and without a birth date. U1 will put the mark on its one combination
  row.

#### <a id="u3"></a>U3 Three sections: weighed, yours, also today — built 2026-10-09 (SPEC §5.12, §10.3)

- **Gap.** The *bla mkhyen* and the nectar periods stood between Rāhu and
  the weekday, inside the run of weighed voices, though neither is
  weighed. The personal day and personal mansions opened the Almanac, so
  an unweighed row was the first verdict on the page (8 October 2026:
  "Hostile day" in red above a lucky day), while vitality and body sat
  apart in "Your day". The nectar periods were shown three times: a row,
  the brief's "By the hour" and the dots on the hours dial.
- **Built.** **Almanac**: the monthly observance, the weighed voices in
  rank (the combination, Rāhu, weekday, mansion, special days, date,
  karaṇa, yoga), the haircut. **Your day** (with a birth date): "Your
  weekday" (T2.19's roles), the personal and birth mansions, then
  vitality and body; E6's every-work days will join it. **Also today**
  («Также сегодня»): the *bla mkhyen*. The nectar row is gone, and with
  it the small nectar dial; the brief and the hours dial keep the
  periods, and the hours panel's nectar row now gives the Tibetan term
  (*bdud rtsi thun mtshams*) the Almanac row held. "Lunar day" is as it
  was.

U1 and U3 were checked on the emulator (Kyoto) in English and Russian:
lucky by the combination (2 October 2026), lucky with the parts
disagreeing (8 October), unlucky by the combination (1 November), the
monthly observance first (10 and 18 October), personal mansions in Your
day (4, 6, 7 October), each with and without a birth date.

#### <a id="u4"></a>U4 The brief grouped by the voices that carry each work — built 2026-10-09 (SPEC §5.12, §10.3)

- **Gap.** On 8 October 2026 the brief lists 65 good works, one per line,
  and from the 25th on the same "Thursday" or "Thursday · Maghā" repeats
  on each.
- **Work.** Works grouped by the set of voices standing on their side,
  each group headed by that set, its works as one wrapped run of glyphs
  and names; groups in the order of their weight (§5.12), as the works
  are now. Nothing outweighed is shown, as now; a work still opens its
  workings.
- **Built.** `byVoices` groups the good and the avoid list by their
  standing entries, in the works' order, so groups stand by weight and
  each work keeps its place (`DaySummaryTest`, every day of 2026). The
  sheet heads each group with its voices, each tapped for its kind, and
  runs its works as glyph and name; a work's balloon gives its side and
  each voice with its kind. On 2 October 2026 "Friday · Rohiṇī" carries
  fourteen of the 79 good works as one run. The 旧暦
  brief keeps one row per work, with both sides.

#### <a id="u5"></a>U5 The hours on the In brief row — built 2026-10-09 (SPEC §10.3)

- **Gap.** The combination period is the one factor a text puts above the
  day (WB p. 376, T2.4; under E, *The person's days and the hour against
  the day*), but the In brief row gives only the day's tone; its hours
  are one tap away, in the sheet's "By the hour". On an unlucky day the
  hours to be accomplished are what a reader can use.
- **Work.** The row carries the hours that run against the day's tone:
  on an unlucky day those to be accomplished ("unlucky day ·
  09:00–13:00 good"), on a lucky day those to be avoided; a tap on them
  opens the hours panel at that hour. Nothing is weighed anew.
- **Built.** `DaySummary.hoursAgainst`: the runs of `DayHours` against
  the verdict, none on the person's enemy weekday or death mansion (WB
  places no hour above it; E, *The person's days and the hour against
  the day*). The row's second line: "good hours" («благоприятные часы»)
  or "hours to avoid" («неблагоприятные часы») in the side's colour, then
  the times, each opening the hours panel at its hour; screen readers
  get the times in the row's text and an action for each.

U4 and U5 were checked on the emulator (Kyoto) in English and Russian:
lucky by the combination (2 October 2026: hours to avoid 07:00–11:00,
13:00–15:00, 19:00–21:00, 01:00–03:00), unlucky by the combination
(1 November: good hours 09:00–11:00, 13:00–17:00, 19:00–23:00,
01:00–05:00), each with and without a birth date; the enemy weekday
(8 October, with the test birth date) shows no hours. A time on the row
opened the panel at its hour (07:00 the Dragon hour, 13:00 the Sheep
hour).

#### <a id="u6"></a>U6 The page in the order of strength — built 2026-10-09 (SPEC §5.12, §10.3)

- **Gap** (the owner, 2026-10-09: neither the page nor the sheets gave a
  determined impression of what is allowed and what is prohibited, and
  the reading felt "adaptive and soft", guided by the line of dots). On
  3 October 2026 the Almanac read as a scoreboard: nine rows, nine
  coloured dots, two red, red, grey, red, yellow, green, green, red, with
  a "decides" chip on the first and nothing to say the other eight did
  not count for the day's tone. The haircut, the one row whose dot is a
  verdict on a work, stood last among rows whose dots are the voices'
  tones. The hour, which outranks everything (WB vol. 2, p. 376), was a
  clock icon on the Almanac's heading and a line of times under In brief,
  and the brief's "By the hour" came after 93 works. The brief's headline
  explained the mechanism ("its named combination and its element pair
  agree, and outweigh every other factor") and then listed 42 good works
  on an unlucky day without a word that this is genuine.
- **Built.** The page in the order of strength: a **Hours** row above the
  brief, a strip of the twelve periods coloured by their verdict with the
  present moment marked, the present hour with its sign and verdict, and
  U5's hours against the tone, each opening the panel (`HoursRow`,
  `rememberCurrentHour` shared with the panel); the In brief row keeps
  the tone, glyphs and counts; a **Works** section with the haircut row
  between the brief and the Almanac; the Almanac's heading names what
  decides ("by the combination"), and only that voice's dot is solid,
  the others' outlines, "not deciding today" to a screen reader, with a
  note in their sheets that their lists still decide the works they name
  that no stronger factor names (`Annotation.outline`, `ToneDot`); the
  brief's headline adds the rule in a sentence ("Each work goes to the
  strongest factor that names it, so 42 works are still good today.
  Within its hour the combination period outweighs the day.", plurals in
  both languages, nothing on the person's enemy weekday or death
  mansion), and "By the hour" moves before the works. The clock icon on
  the Almanac's heading is gone. No side or tone changes. Pinned works in
  the Works section, each with its deciding factor, are a later item: a
  chooser and a preference (U19, built 2026-10-21).

U6 was checked on the emulator (Kyoto, clock set so the Bird hour is the
present one) in English and Russian: unlucky by the combination
(3 October 2026, with the yoga's and the haircut's sheets), lucky by
Friday on the death mansion with the test birth date (9 October: no hours
against the tone, no rule sentence), lucky by Monday with the monthly
observance (5 October, Russian).

Each is done when SPEC has it, `TranslationsTest` passes, and screenshots
on the emulator of a lucky and an unlucky day, with and without a birth
date, in English and Russian, show it.

### U7. Readings without their sources — built 2026-10-09 (SPEC §8.1, §10.5, §11)

- **Gap** (the owner, 2026-10-09): every reading sheet, on both pages,
  ended in its sources' titles, publishers, URLs and licence, four to
  ten lines on a phone, and the readings themselves were full of them:
  "Of the ten, the White Beryl (…) counts it among the three good", "an
  earth lord of the White Beryl", "vol. 2, p. 338", a detail row "In the
  White Beryl" on some twenty sheets. The reader of the day needs its
  readings; whoever needs the attributions finds them in the repository.
- **Built.** The sheets end with the reading, its lists and its
  workings. No reading, note, wording or interface string names a
  source, a page or what "the text" says (about 130 catalog entries and
  20 interface strings rewritten in English and Russian): the reading
  states the fact, and a view other than the main one is "another
  view", named only where the view is the row itself (Pukton, the Vajra
  Treatise, the Paṇchen Mön'drowa). The detail row gives the term's
  Tibetan name as "Tibetan", and the White Beryl's own name for a yoga or
  karaṇa as "Also called" where it differs from the almanacs'; the
  haircut sheet's "not in the White Beryl" row is gone; Catuṣpada's
  edition note is dropped. The sources stay in the catalog (`Texts.kt`,
  its comments, `TextsTest`) and in About & sources, which gains a
  Documentation section pointing to the repository, with a link.
  `CatalogTest` and `TranslationsTest` fail on a citation outside About.
  Store shot 3 retaken in both languages, its caption no longer about
  sources.

Checked on the emulator in English and Russian: the Saga Dawa Düchen
sheet (store shot 3), the karaṇa and yoga sheets, an earth lord's, the
hours sheet and About & sources.

### U8–U18. The second review — planned 2026-10-11

A review of the app against SPEC §10 on the emulator (the build of
c90cd5c, 11 October 2026, Kyoto, the test person born 1976-06-01, English,
font scale 1.0 and 1.3): both pages, the brief, the hours panel, the
combination sheet, the year sheet, the date picker, the menu, the people
dialog, the election with and without a work, the meditation screen with
the bell on, Location and About. The order of strength U1–U6 built holds,
the glyphs, dots and sheets read as one language, every drawn thing has
its description, and the page reflows at 1.3 without clipping. What is
left is chrome and form: eleven items, each a sitting, none changing a
side, a tone or a reading. **No reading:** nothing below touches a
source, so no item of this section stands in the Readings table; a
first-run line and a notice are interface text (SPEC §8 does not apply).
The blocks are [34](#block-34) to [37](#block-37), by screen; A2, N1 and
W1 build on them (below).

#### <a id="u8"></a>U8 The sky line as one line — built 2026-10-11 (SPEC §10.2, §10.5)

- **Gap.** The sky line is pinned under both pages at every scroll
  position: three times with their icons on one line, the place on a
  second, about 110 dp with its padding, a tenth of the screen, for facts
  read once a day (SPEC §10.2, §10.5).
- **Work.** One line of 44 dp: sunrise, noon with its altitude, sunset,
  each with its icon; the place leaves the line for a balloon on a tap of
  the line, with "Change location" as the balloon's action; the "set a
  location" prompt keeps the line while no place is set. SPEC §10.2 and
  §10.5.
- **Block.** [34](#block-34); no reading; waits on no build. Every store
  shot shows the line, so [33](#block-33) (A1) waits on this block and
  retakes them once.
- **Done when.** Both pages on the emulator in English and Russian at
  font scale 1.0 and 1.3 show one line; the balloon's action opens the
  location screen; SPEC §10.2 and §10.5; store shots 1, 2 and 4 retaken
  in both languages.
- **Built** 2026-10-11 as planned, the line 44 dp; its balloon gives the
  place alone, with "Change location →", which opens the location screen.
  Checked on the emulator in English and Russian at font scale 1.0 and
  1.3 (11 October 2026, Kyoto); store shots retaken.

#### <a id="u9"></a>U9 The date picker's jump — built 2026-10-11 (SPEC §10.2)

- **Gap.** The picker moves one month per tap of a chevron; 2030 from
  2026 is 48 taps, and the range runs 1900–2100 (SPEC §10.2).
- **Work.** A tap on the month title opens the years as a scrolling grid,
  this year marked, then the twelve months of the chosen year, then the
  days; the chevrons stay. The Material date picker's pattern, drawn in
  the app's style with its accent. SPEC §10.2.
- **Block.** [35](#block-35); no reading; waits on no build.
- **Done when.** 1900 and 2100 reached in three taps each on the
  emulator; the small figures (Tibetan day, 旧暦 day) and the day marks
  right after a jump; both languages; SPEC §10.2.
- **Built** 2026-10-11 as planned: the title carries a small chevron; the
  years run in four columns, opened with the year shown in the third row,
  the shown year filled in the calendar's accent and this year outlined;
  the months three to a row, marked the same way; in the months the
  chevrons move a year, in the years they are hidden and the title goes
  back to the days. Checked on the emulator in English and Russian, on
  both pages: January 1900, December 2100, March 2030 and August 1955
  each reached in three taps (title, year, month) with a scroll of the
  years between, and every day's small figure and 受死日/天赦日 mark of
  those four months equal to the engines' (`TibetanCalendar`, `Kyureki`,
  `Rekichu`) for the same dates, 496 cells in all.

#### <a id="u10"></a>U10 The brief's work rows in the 旧暦 brief's form — built 2026-10-11 (SPEC §10.3)

- **Gap.** In the Tibetan brief sheet each work stands under its voices,
  the voices in 16 sp semi-bold white and the work in regular: the eye
  lands on "Sunday · Hasta · Kham", not on "making things". The 旧暦
  brief lists the work first, its annotations right-aligned and muted,
  and reads at a glance. The two differ for no reason in the sources.
- **Work.** The Tibetan brief takes the 旧暦 brief's row: the family
  glyph, the work in the row's weight, its voices after it right-aligned
  in muted 13 sp, each voice still a tap-term with its kind; the work's
  balloon and "Choose a day for it" unchanged; the "For you" and "By the
  hour" blocks unchanged. Nothing weighed anew (`DaySummary` untouched).
  SPEC §10.3.
- **Block.** [36](#block-36); no reading; waits on no build.
- **Done when.** The brief sheet on a lucky and an unlucky day, with and
  without a birth date, in English and Russian; the enemy-weekday sheet
  unchanged; SPEC §10.3.
- **Built** 2026-10-11 as planned: both briefs draw their works with one
  row (`WorkBlock`), the 旧暦's as before, the Tibetan's with every work
  opening its balloon. Checked on the emulator (Kyoto) on 11 October 2026,
  lucky, and 28 March 2026, unlucky, with and without the test person
  born 1976-06-01, in English and Russian: the rows, a work's balloon
  with its voices' kinds and "Choose a day for it", a voice's kind on a
  tap, "By the hour" and "For you" as they were; on 8 October 2026, the
  person's enemy weekday, the sheet keeps its line, no good list and
  WB's words. No store shot shows the brief sheet.

#### <a id="u11"></a>U11 The hours in short form — built 2026-10-11 (SPEC §10.3)

- **Gap.** The hours row paints the hours to avoid on the strip and lists
  them again as text under the present hour, "05:00–09:00 11:00–13:00
  17:00–19:00 23:00–01:00", two or three lines, four at font scale 1.3.
- **Work.** One line in short form, "avoid 05–09 · 11–13 · 17–19 · 23–01"
  ("good …" on an unlucky day; the label the In brief line's word, under
  the row's "Hours" heading), the full times kept in the brief sheet's "By the hour"; the
  runs stay taps that open the panel at their hour; screen readers keep
  the full times. The same short form serves the morning notice
  ([N1](#n1)) and the widget ([W1](#w1)). SPEC §10.3.
- **Block.** [36](#block-36); no reading; waits on no build.
- **Done when.** The row on a day with four runs (11 October 2026,
  Kyoto) at font scale 1.0 and 1.3 is one line in English and Russian;
  a tap on a run opens the panel at its hour; `HoursRow`'s description
  gives the full times; SPEC §10.3.
- **Built** 2026-10-11, the label the In brief line's word ("avoid
  05–09 · 11–13 · 17–19 · 23–01", "good …" on an unlucky day): "hours to
  avoid", and the Russian «неблагоприятные часы» still more, did not fit
  at 1.3 beside four runs, and the row already stands under "Hours". The
  formatter is `HoursShortForm` in `core/texts`, tested with runs across
  midnight and the two days of the review (`HoursShortFormTest`); the
  "hours to avoid" and "good hours" strings stay for N1's notice, which
  has no heading over it. A day with five runs (2026–2027 have them, e.g.
  28 March 2026) is set smaller to keep one line. Checked on the emulator
  on 11 October 2026 in Kyoto at font scale 1.0 and 1.3, English and
  Russian, one line each, and 28 March 2026 at 1.3 (five runs, one
  line); a tap on 11–13 opened the panel at the Horse hour; the row's
  description keeps every run's full times. Store shot 1, the Tibetan
  day, shows the row.
- **The wording settled** (the owner, 2026-10-11: "avoid" stays, and the
  Work above says it): the first wording, "hours to avoid" / "good hours" (Russian
  «неблагоприятные часы» / «благоприятные часы») cannot keep the "Done
  when"'s one line at a readable size, so the row keeps "avoid" / "good".
  With the literal labels and the row's shrink-to-fit, measured on the
  emulator (1080 px wide, 859 px for the line): 11 October 2026 in Kyoto
  (a lucky day, "hours to avoid", four runs) fits at full size at font
  scale 1.0 in English (692 px) and Russian (852 px), but at 1.3 the
  English needs 917 px (set at 0.92, the label 11.9 sp) and the Russian
  1129 px (0.75, 9.7 sp); 28 March 2026 (an unlucky day, "good hours",
  five runs) fits in English at 1.0 (794 px), but the Russian at 1.0
  needs 960 px (0.88, the label 11.4 sp, under the 12 sp floor at scale
  1.0), and at 1.3 the English 1055 px (0.80, 10.4 sp) and the Russian
  1275 px (0.66, 8.6 sp). The first case to fail is 28 March 2026 in
  Russian at font scale 1.0.

#### <a id="u12"></a>U12 Dots that read — built 2026-10-11 (SPEC §10.3)

- **Gap.** The Almanac's outline dots (U6) are 8 dp with a 1.5 dp ring;
  on the ink page the mixed outline beside "Sunday" hardly shows, so the
  distinction U6 built, the deciding voice alone solid, is lost at a
  glance.
- **Work.** Dots of 10 dp with a 2 dp ring, or the outline dots filled
  at low alpha inside the ring; the same `ToneDot` everywhere it is drawn
  (the Almanac, Your day, the hours panel, the election's cells, the
  year sheet); `PaletteTest` holds the ring to 3:1 on ink, surface and
  raised, and on every theme once A1 is built. SPEC §10.3.
- **Block.** [34](#block-34); no reading; waits on no build.
- **Done when.** The Almanac on 11 October 2026 and the election's grid
  on the emulator in English and Russian, the ring told from the solid
  dot at a glance; the Russian labels still fit beside the wider dots;
  SPEC §10.3.
- **Built** 2026-10-11: `ToneDot` 10 dp, its ring a fifth of its size,
  drawn wherever a tone is: the Almanac, Your day, the brief's sheet, the
  hours panel's rows, the election's list and grid (8 dp in the grid's
  cells, the person's days to avoid as rings) and the lunar dates' grid.
  `PaletteTest` holds every tone 3:1 on ink, surface and raised (the
  neutral faint, the weakest, 3.98:1 on raised). Checked on 11 October
  2026 and the election for marriage talks, English and Russian.

#### <a id="u13"></a>U13 Second lines that state the fact — built 2026-10-11 (SPEC §10.3)

- **Gap.** Some rows' second lines describe their sheet rather than state
  the fact: Rāhu's course says "date 1: where Rāhu moves, and what the
  day is good for" (two lines at font scale 1.3) while its description
  already carries "south-west → north-east"; the accomplishment
  combination says "special day of weekday and mansion: Sunday + Hasta".
  Against the short-rows rule (the owner, 2026-10-10; SPEC §10.3).
- **Work.** Rāhu's row's second line the course itself, "south-west →
  north-east", on the 14th "from the sky into the lake" and on the 30th
  "every direction", as its compass draws them; the special days' row
  "Sunday + Hasta" alone; then a pass over every row's second line on
  both pages for a line that names its sheet instead of its fact. The
  kind-and-gloss lines ("lunar mansion: the hand", "karaṇa: unpleasant")
  stay: the gloss is the fact. SPEC §10.3.
- **Block.** [34](#block-34); no reading; waits on no build.
- **Done when.** The rows of both pages listed with their second lines
  before and after, in the commit; SPEC §10.3's wording; `CatalogTest`
  and `TranslationsTest`; both languages on the emulator.
- **Built** 2026-10-11. The pass found four rows that named their sheet,
  all on the Tibetan page: Rāhu's course by date (detailed and general),
  the special day, the Vajra Treatise's special day and the burning date;
  the commit lists them before and after. Every other row's second line,
  on both pages, is a kind and gloss or a fact and stays.

#### <a id="u14"></a>U14 The election's civil dates and voices line — built 2026-10-11 (SPEC §10.8)

- **Gap.** The election's grid shows the lunar date in each cell and the
  civil date only on a tap, while a day is chosen in civil terms; the
  best-days list says "by Friday" and under it "Friday" on consecutive
  lines when the deciding voice is the only one (SPEC §10.8).
- **Work.** The civil day small under the lunar date in every cell, the
  date picker's figures reversed (a doubled lunar date's two cells each
  with their own civil day); the month heading carries the civil span
  ("9th month · 11 Oct – 9 Nov"); in the best-days list the voices line
  only when a voice stands beside the deciding one. The 旧暦 election's
  grid the same. SPEC §10.8.
- **Block.** [37](#block-37); no reading; waits on no build.
- **Done when.** The election for marriage talks from 11 October 2026,
  this month and twelve months, and the 旧暦 election, on the emulator in
  English and Russian; a doubled and a skipped date checked in the grid;
  SPEC §10.8.
- **Built** 2026-10-11. `WorkCell.civil` puts the civil day under the
  lunar date in the election's grids only (the haircut sheet's keeps its
  own); the heading's span is `civilSpan`, the year at the end only where
  the span leaves the shown day's ("12th month · 8 Jan – 6 Feb 2027");
  the voices line is `ElectionDay.beside`, what stands beside the deciding
  voice, the combination's entries left out where it decided. Checked:
  `ElectionTest` (the 16th by Friday alone, the 28th with two special days
  beside, a year of every work never repeating the decider) and
  `CivilSpanTest` (both languages' patterns); on the emulator, marriage
  talks from 11 October 2026 over this month and twelve months, Tibetan
  and 旧暦, in English and Russian: the 9th month's doubled 9 on the 19th
  and 20th, its skipped 17 between the 27th and the 28th, each cell's tap
  naming its own civil date; the twelfth month's doubled 24 (31 Jan,
  1 Feb 2027) and skipped 21; "by Friday" with nothing under it, the 28th
  with "Day of harmony · Day of union"; the grid at font scale 1.3.

#### <a id="u15"></a>U15 The calendar toggle in the header — built 2026-10-11 (SPEC §10.2)

- **Gap.** The header's pill names the calendar shown, "Tibetan", with a
  swap icon; read as a button it says either "this is Tibetan" or "go to
  Tibetan" (SPEC §10.2 settled a tap on the name switching directly).
- **Work.** A two-segment toggle in the pill's place, "Tibetan | 旧暦",
  the current segment filled in its accent, the other outlined; a tap on
  the other switches; the same width as the pill in English and Russian;
  screen readers get the two as tabs with the selected state. The menu's
  two calendar rows stay. SPEC §10.2.
- **Block.** [34](#block-34); no reading; waits on no build.
- **Done when.** Both languages on the emulator; store shots 1–7 retaken
  in both languages (every one shows the header); SPEC §10.2.
- **Built** 2026-10-11, each segment 44 dp high to the touch and drawn
  36 dp, its padding cut to 7 dp so that the toggle is the pill's width,
  117 dp in Russian at font scale 1.0. In Russian with "Сегодня" and the
  person ring shown the date beside it wraps to two lines at 1.0 and
  clips at 1.3, as it all but did beside the pill: A2's short date
  pattern is for that.

#### <a id="u16"></a>U16 The two briefs alike — built 2026-10-11 (SPEC §10.4, §10.7)

- **Gap.** The 旧暦's In brief row writes "good" and "avoid" beside its
  glyphs and shows every family, up to three lines on a busy day; the
  Tibetan row shows eight glyphs at most, a rule between the sides and
  the counts at the right, with no word: colour and position alone
  separate its sides, where the 旧暦 row names them (SPEC §10.4, §10.7).
- **Work.** The same labels on both rows, "good" and "avoid" in their
  tones before each side's glyphs, and the same cap on the 旧暦 row: four
  families a side at most in the order the row now uses, a side with
  fewer leaving its places to the other, the counts at the right as the
  Tibetan row gives them; the breakdown keeps every family. No weight
  enters the 旧暦: the cap is a count, not a rank. SPEC §10.4, §10.7.
- **Block.** [34](#block-34); no reading; waits on no build.
- **Done when.** Both rows on the emulator in English and Russian at font
  scale 1.0 and 1.3, the labels and eight glyphs within 411 dp; store
  shots 1 and 2 retaken; SPEC §10.4 and §10.7.
- **Built** 2026-10-11 with one change to the plan: the word and count
  stand over each side's glyphs ("good 62" over the good side, "avoid 35"
  over the other) rather than before the glyphs with the counts at the
  row's end, since words before the glyphs and counts after them leave
  fewer than eight places at font scale 1.3 in Russian ("избегать 35");
  the rule between the sides went, the words parting them. The 旧暦's
  cap is `DaySummary.familyRow`, tested over 2026. Checked on both pages
  in English and Russian at 1.0 and 1.3.

#### <a id="u17"></a>U17 The bell's time picker — built 2026-10-11 (SPEC §10.9)

- **Gap.** The bell's From and Until move by half hours on − and +; from
  09:00 to 05:00 is eight taps (SPEC §10.9).
- **Work.** A tap on the time opens the system's time picker (Material
  `TimePicker`) in the app's colours, the result rounded to the half
  hour; the steppers stay for small moves. SPEC §10.9.
- **Block.** [35](#block-35); no reading; waits on no build.
- **Done when.** The two pickers on the emulator in English and Russian;
  a time set by the picker rounds to the half hour and the switch's
  subtitle names the next bell from it; `BellTest`; SPEC §10.9.
- **Built** 2026-10-11: the time between − and + is an outlined button
  that opens the Material clock in a dialog ("The bell rings from",
  "The bell rings until"), 24-hour as the app's clock, with the line "Set
  to the nearest half hour"; a quarter past or to rounds up, from 23:45
  it stays 23:30, and an end set past the other moves that one half an
  hour away (`app/.../bell/BellHours.kt`). `BellTest` covers the rounding,
  the pushed ends (every pair still a valid `BellPlan`) and the subtitle
  from a picked From, today's and another day's wording. Checked on the
  emulator in English and Russian: From 05:10 → 05:00, Until 23:50 →
  23:30, From 23:55 → 23:00 with Until 23:30, Until 00:05 → 00:00–00:30
  and the subtitle "Next bell at 23:00", "Следующий удар: понедельник,
  00:00" as each set it.

#### <a id="u18"></a>U18 A first-run line — built 2026-10-11 (SPEC §10.2)

- **Gap.** Nothing introduces the page: a first-run reader meets
  "Kiṃstughna", outline dots and "decides" without a word; the dots and
  the chip are explained only inside the brief sheet.
- **Work.** One dismissable line under the In brief row on the first
  three opens of each page: on the Tibetan page "Tap any row for its
  reading; the solid dot decides the day", on the 旧暦 page "Tap any row
  for its reading; every kanji shows its English on tap"; gone after a
  tap on it or the third open, a counter per page in `Settings.kt`.
  Interface text in `strings.xml`, no reading. SPEC §10.2.
- **Block.** [34](#block-34); no reading; waits on no build.
- **Done when.** A fresh install on the emulator shows the line three
  times on each page and not a fourth, and not after a tap on it; both
  languages; `TranslationsTest`; SPEC §10.2.
- **Built** 2026-10-11: an open is counted once a launch when the page is
  first shown, kept across turning the phone; `first_run_tibetan` and
  `first_run_kyureki` in the preferences, and `tools/emulator/emu.py`
  writes them as 3 so that tours and store shots do not show the line.
  Checked from a fresh counter: three opens of each page show it, the
  fourth not, a tap puts it away; English and Russian.

#### <a id="u19"></a>U19 Pinned works — planned 2026-10-13, built 2026-10-21 (SPEC §10.3)

- **Gap.** U6 left it as a later item (2026-10-09): the works a reader
  cares about are found by scrolling the Works section, each time.
- **Work.** A chooser from the Works section's heading to pin works; the
  pinned ones first in the section, each with its verdict and deciding
  factor, as now; a preference, kept across days, none pinned on a fresh
  install, so the store shots stay as they are. The design is the
  owner's to confirm before the build.
- **Block.** [55](#block-55); no reading.
- **Done when.** Pinning, unpinning and the order on the emulator in
  both languages; a fresh install with none pinned; SPEC §10.3.
- **Built 2026-10-21** (block 55). The owner's design of that day: each
  pinned work in the brief's own row, not the haircut's (glyph in the
  side's colour, the work, the deciding voices; a balloon with "Choose a
  day for it"); a work no reading names stays, grey, "Not named today";
  good first, then to avoid, then the unnamed, re-sorted every day, each
  in the families' order; the chooser from a pin on the heading, the
  election's works by family and search, the haircut not offered.
  `DaySummary.pinned` gives the rows, on the person's day of every work to
  avoid every work to avoid; the brief's work row is `WorkLine`, shared;
  the preference `pinned_works`. Checked: `DaySummaryTest` (every day of
  2026, the sides' order and the families' within each, the notes the
  brief's own; the enemy weekday of 8 October 2026), `:core:test`,
  `:app:testDebugUnitTest`; the emulator in English and Russian (a fresh
  install with the pin and no rows; ticking by family and by search,
  "hair" finding only washing the hair; good, to avoid by the hour and
  unnamed in that order; the pins kept over a restart; a row's balloon
  and its election; unpinning; Thursday 22 October 2026, the test birth
  date's enemy weekday, every pinned work to avoid by it; font scale 2.0
  and landscape), the release build (the pins read, both languages, no
  raw keys). Store shots 1 and 8 retaken for the heading's pin, and 3 with
  them, whose reading had grown since; the rest unchanged.

#### <a id="u20"></a>U20 The haircut in the hour — built 2026-10-15 (SPEC §10.3)

- **Gap.** The Works section's haircut row gave the day's weighing, the
  weekday's side (KP box 52a), though within its hour the combination
  period outweighs every factor of the day (WB vol. 2, p. 376), and ten of
  the twelve signs name haircuts (WB ch. 34, p. 420, as the periods'
  own readings): on any day the row held for two hours at most. The owner
  (2026-10-15): the row must hold in the moment, not in the day.
- **Built.** `DaySummary.inHours(work, month)`: each hour's side, the
  period's where it names the work one way (`Election.hours`, the work's
  own hours first), the day's elsewhere and on the person's day of every
  work to avoid. On today's page the row's dot is the hour's side and its
  subtitle says until when and what decides ("good until 23:00 · by
  Pisces rising"), and it moves on with the clock; the sheet's note says
  which sign rises and whether its period names haircuts, its rows give the
  good hours and the hours to avoid, and FPMT's day for the date is
  measured against the hour's decider. Another day's row is the day's, its
  sheet with the hours. `DaySummaryTest` holds every hour of 2026 against
  ch. 34's signs, 15 October 2026 hour by hour, and the test birth date's
  enemy weekday with no hour above it. A sheet with parts now shows its
  own rows too (`ReadingSheet`); no other such sheet has any. Checked on
  the emulator in Kyoto on 15 October 2026 at 06:00 ("avoid until 15:00 ·
  by Libra rising"), 10:00 (Sagittarius: "avoid until 15:00 · by
  Thursday") and 16:00 ("good until 23:00 · by Pisces rising"), in English
  and Russian, on the 16th (the day's row, "good · by Friday") and with
  the test birth date (the day's row, no hours). The store shots keep the
  row below the fold; unchanged.

#### <a id="u21"></a>U21 The day's works in the hour — built 2026-10-16 (SPEC §10.3, §5.13)

- **Gap.** U20 gave the haircut row its side in the present hour, but
  the day in brief still listed every other work by the day's weighing,
  though the same rule holds for each: 75 of the 139 works are named by
  the combination period of some sign (setting out, building and the
  sacred supports by all twelve, ten more beside haircuts by ten), so in
  most hours the brief's lists said "good" of works the hour forbids.
  The owner (2026-10-16): the works change their side right in today's
  list, marked "by the hour", with a word on it at the top.
- **Built.** `DaySummary.atHour(month, hour)`: every work the period of
  the sign rising names one way (`Election.periodSide`, which
  `Election.hours` and so the haircut row now read too) takes its side,
  the period its one voice and first on its side, a work the day names
  nowhere added; the rest and the day's tone as the day weighed them,
  and nothing on the person's day of every work to avoid. On today's
  page the In brief row and its sheet take these lists and move on with
  the clock (the owner's choices of 2026-10-16: both the row and the
  sheet; the hour replacing the outweighed voices; the sentence with
  the sign and the counts): each such work reads "by the hour · Pisces",
  its balloon "Pisces rising, until 17:00", and the rule's sentence under
  the tone says "Now, until 17:00, Pisces rises, and its combination
  period outweighs the day: it decides 46 works, 37 good and 9 to avoid,
  marked “by the hour” below". Another day's brief is the day's.
  `DaySummaryTest` holds every hour of 2026: each work's side and mark in
  the hour's lists equal `inHours`'s, the period's works first, the
  counts, the day's tone unchanged, and the test birth date's enemy
  weekday unchanged in every hour. Checked on the emulator in Kyoto on
  16 October 2026 at 16:58 (Pisces, 37 good and 9 to avoid by the hour)
  and as the clock passed 17:00 (the lists moved to Aries by
  themselves), in English and Russian, and on 17 October (the day's
  lists, the old sentence). The morning notice keeps the day's counts.

### <a id="x"></a>X1–X10. The readings' wording — planned 2026-10-11

An analysis of every reading the Tibetan page's sheets open (the build of
25ec842: 459 readings of the catalog's Tibetan families, the year sheet's
beside them). Sound in substance, weak in shape. Each reading is one
paragraph in its source's order, so the sentence the reader opened the
sheet for comes in the middle or at the end: a lunar date gives three
names before any meaning, a weekday opens with caste, colour and element,
the astrologer spirit's reading explains its mechanism twice around its
"Today". Facts the day cannot use stand in the main text at full length:
the la's seat in the body and in livestock closes all 30 lunar dates in
the same 30 words, the hearth god's sentence stands in all 12 earth-lord
days, the mansions end in "Planet: Sun. Foods: meat and honey", and the
earth lords' date tables are written out as prose, the eight classes' in
529 words, the sky dog's in 435, the nāgas' in 391. The weight is upside
down: the combination, the strongest voice, gets fourteen words of verse
tag ("Fear of illness."), the unweighed earth lords the longest texts.
Yoga readings open with the verse's headword as if it were the ruling
("Death in prison.", "Meeting a woman friend."). The ruling words vary
(good, auspicious, succeed, turn out well, virtuous; bad, avoided,
shunned, given up at once). The app's own mechanics sit inside the
source's text ("so with a birth date set the day in brief gives you no
list of good works on it", "the works below"). Prose and lists disagree
without a word of explanation (date 1 says "good for pacifying rites,
crafts, games and all white work", its Good-for list says setting out,
lawsuits, breaking ground, war not east). Editor's notes stand as text
("not identified", "the two rendered word for word", "which the print
spells 'yellow rope'"). And lists of named spirits with their brackets
cannot be scanned in a sentence, though the same words read well in a
row. Counts: 54 readings over 100 words, 9 over 200; "spring counted
from the 11th month" 23 times; the la's sentence 30 times.

**The shape** every reading takes, set as a rule once ([X1](#x1)),
carried by the catalog and the sheet ([X2](#x2)), then applied family by
family ([X3](#x3)–[X10](#x10)), the heaviest voices and the longest
texts first:

1. **The ruling first.** The paragraph says what this is today and which
   way it cuts, in one to three sentences of 20 to 60 words, and never
   repeats a list the sheet shows under it.
2. **Facts as rows.** Everything else is a labelled row in one fixed
   order, so a reader who wants the ruling stops after the paragraph and
   a reader who wants the rest finds each thing in its place.
3. **How it falls, today only.** The row gives the line that applies to
   the shown day; the full table stays in `docs/sources/`, which About
   points to.
4. **Boilerplate once.** A sentence that is the same in every reading of
   a family is a row's label or the family's note, not a sentence in
   each.
5. **One vocabulary.** The lists' words in prose too; a verse's headword
   marked as an image, never as the opening ruling.
6. **Effort by weight.** The combination's readings grow to the shape,
   the earth lords' shrink to it.

**Decisions (the owner, 2026-10-11):**

- **D6 — the omens stay.** Births, deaths, livestock, the planet's caste
  and colour, the mansion's foods: the source's content, kept, as the
  last rows of the sheet and never in the paragraph.
- **D7 — the title does not count.** SPEC §8.1's bracket rule stays:
  a word is bracketed at its first mention in each text, and the
  reading, each row and the sheet's title are each a text of their own,
  so "earth lord (ས་བདག, sa bdag)" stays in a reading whose title carries
  it. Uniform where possible: the same word is bracketed the same way
  everywhere, one English name and one Wylie per word across the catalog
  and the interface, the few words the texts themselves name two ways
  (the la weekday, *bla gza'*, which is both the birth animal's luck day
  and the element's own weekday) listed as the exceptions.

**No reading:** nothing below reads a source anew; every fact stays the
fact the catalog has, moved and reworded, so no item stands in the
Readings table. A sentence a rewrite cannot place (an editor's note, an
unidentified word) goes to its topic file in `docs/sources/`, and to the
*Left open* table if it is a question.

**Russian follows each item:** the catalog's keys are the same in both
languages (SPEC §8.2), so each family's rewrite carries its Russian,
checked against the source as the project's read-through did, and
[L3](#l3)'s read-through on the phone ([15](#block-15)) waits on all of
X, so that the owner reads the wording once. The blocks are
[42](#block-42) to [49](#block-49); [24](#block-24), [25](#block-25) and
[26](#block-26) write their new readings in the shape from the start;
[33](#block-33) and [40](#block-40) build on the sheet's new rows.

#### <a id="x1"></a>X1 The shape rule — built 2026-10-11, the examples read by the owner the same day (SPEC §8.1, §10.3)

- **Gap.** SPEC §8.1 says what a reading may not contain, a source or an
  unnamed Tibetan word, and nothing about its order, its length or its
  words; 459 readings in as many shapes.
- **Work.** SPEC §8.1 gains *The shape of a reading*, the six points
  above in full: the paragraph's content and bounds; the rows and their
  order, with these labels: *Also called* · *Strong when* · *Setting out*
  · *Rank* · *Remedy* · *Spirits that harm* · *Illness* · *Rites* · *La today* ·
  *A birth today* · *A death today* · *Livestock* · *How it falls* ·
  *Another view*, the Good-for and Avoid lists between the paragraph and
  the rows as now, the engine's workings rows (how it was counted) last;
  the vocabulary, "goes well" and "goes badly" for a work in prose,
  "Good for" and "Avoid" the lists, "Remedy:" in the imperative, "its
  image is …" for a verse's headword; nothing about the app in a
  reading, the app's rules being notes in `strings.xml`
  (`brief_outweighed_note`, `brief_avoid_all_note`) under the gloss;
  boilerplate once, as a family note (`reading.<Family>` filled with
  {0}) or a row's label. D6 and D7 above as the rule's words. SPEC §10.3
  describes the sheet in the new order. Three readings rewritten to the
  rule stand in the spec as its examples: lunar date 1, Sunday and the
  lesser black day.
- **Block.** [42](#block-42); no reading; waits on no build.
- **Written 2026-10-11:** SPEC §8.1, *The shape of a reading*, thirteen
  rules with the three examples, and the interim form for new readings
  until X2 (each row a sentence after the paragraph, opening with its
  label); AGENTS.md's bullet and the catalog's header point to it. Left
  for block 42: SPEC §10.3's sheet in the new order, with X2.
- **Built** 2026-10-11 with X2: SPEC §10.3's *The sheet* gives the new
  order; the interim form gave way to real rows. Lunar date 1 and Sunday
  stand in the catalog in the examples' shape, English and Russian, the
  facts moved and no reading added; the lesser black day followed the same
  day, with the engine's matched date ([X7](#x7), *Started*).
  The la row names the la in its value, "the la, the life-spirit (བླ,
  bla), sits in the big toe; in horses and other livestock, …", as rule 11
  asks of each row, and keeps the source's horses; the example in SPEC
  §8.1 says so. **Read and accepted by the owner 2026-10-11**, the
  lesser black day's Remedy row put in the imperative ("set dough figures
  …; or burn white butter …", the Russian in the infinitive); the points
  shown for that reading (a third, the lunar date's paragraph of 17 words under rule 2's 20,
  mended the same day: "The 1st of the month is a virtuous day, …", 23
  words, the Russian 20): the row labels are short names with no bracket, as the Lunar day
  section's are ("La today"); and [X6](#x6) names rows (*Planet*,
  *Foods*, *Avoid its start*) that X1's fourteen do not have.
- **Done when.** SPEC §10.3 says it, with X2 (done), and the owner has
  read the examples (done 2026-10-11).

#### <a id="x2"></a>X2 Rows in the catalog and on the sheet — built 2026-10-11 (SPEC §8.1, §8.2, §10.3)

- **Gap.** `Reading` holds one summary key and two lists; the sheet's
  rows (`Annotation.details`, `DetailsBlock`) are built in the UI from
  engine values and carry no text from the catalog, so every fact of a
  reading has to be a sentence of its paragraph.
- **Work.** `Reading` gains rows: `reading.<key>.<row>` in the catalog,
  `<row>` one of X1's labels' keys (`also_called`, `strong`,
  `setting_out`, `rank`, `remedy`, `spirits`, `illness`, `rites`, `la`, `birth`,
  `death`, `livestock`, `falls`, `other_view`), the label a catalog term
  of its own so that it translates; a row takes {0} as the summary does,
  for the today-line of *How it falls*. `ReadingBody` shows them after
  the lists in X1's order, the workings rows last, each row's label in
  the details block's column. `CatalogTest` holds the shape: every row
  key one of the list; the Russian has every row the English has; no
  paragraph over 60 words; no reading containing "the lists", "below",
  "the day in brief", "not identified", "word for word" or "the print";
  and D7's uniformity, one script-and-Wylie bracket per English name
  and one English name per Wylie across the catalog, the interface and
  the listing, with the listed exceptions. SPEC §8.2.
- **Block.** [42](#block-42) with X1; no reading; waits on no build.
- **Done when.** A reading with rows on the emulator in both languages
  at font scale 1.0 and 1.3, the label column holding the longest label
  in each; the tests above, which fail on the catalog as it stands and
  pass family by family as X3–X10 are built, so each item's "Done when"
  is its family removed from the test's allowance.
- **Built** 2026-10-11: `ReadingRow`, the fourteen rows in X1's order,
  each label the catalog term `ReadingRow.<NAME>` in English and Russian;
  `Reading.rows`, read from `<reading>.<row>` beside the entry's own text
  (`Reading.rowKey`: under a family's note, the entry its {0} takes) and
  filled as the summary is. `ReadingBody` shows them after the lists and
  before the workings in one label column, measured to hold the longest
  of the fourteen labels on one line in the shown language and font scale
  (at least 112 dp, at most half the sheet). `CatalogTest`: *a
  reading's rows are the shape's rows*, *the Russian has every row the
  English has*, and *readings keep the shape* (no paragraph over 60
  words, rule 8's and 9's words in English and Russian, D7's uniformity
  across the catalog, the interface and the listing, *bla gza'* the
  exception); the 旧暦 page's readings, not in this review, stay outside
  it. The allowance, by item, counted in both languages together on the
  catalog of 2026-10-11: [X3](#x3) 36, [X4](#x4) 28, [X5](#x5) 4,
  [X6](#x6) 6, [X7](#x7) 109, [X8](#x8) 15, [X9](#x9) 25, [X10](#x10)
  120; each block deletes its item's line, and a count can only fall.
  Later the same day (block 42's review) the test gained rule 2's floor,
  no paragraph under 20 words, measured as the sheet shows it (a family's
  note filled with the entry's text), and the paragraphs already under it
  joined their items' counts, the one time the counts rose: [X3](#x3) 96
  (the 30 hair dates' one-line results, both languages), [X5](#x5) 95 (the
  named combinations, four combination days, the special days and the
  festivals), [X6](#x6) 40 (16 yogas and Viṣṭi), [X8](#x8) 27, [X10](#x10)
  180 (the 30 haircuts); [X4](#x4) and [X9](#x9) have none under 20. The
  lesser black day in the shape took [X7](#x7) from 109 to 107.
  Four list wordings mended for D7 on the way, no reading touched: the
  thread-cross's and the ransom's brackets moved before "rites", the
  *hom* named fire offerings as the weekdays name it, and the Russian
  flood dikes one word (заслоны от паводков). Checked: the tests, which
  fail with every line at 0; lunar date 1 and Sunday on 11 October 2026
  (a Sunday and the 1st) on the emulator in English and Russian at font
  scale 1.0 and 1.3; the release build for raw keys.

#### <a id="x3"></a>X3 The lunar dates — built 2026-10-18

- **Gap.** Thirty readings of 60 to 90 words: the class's three names
  first, the ruling after the colon, then births, deaths, livestock,
  and the la's 30-word sentence; the empty days' remedy inside the
  ruling; "Also to avoid: flood dikes" repeating the Avoid list; the
  29th's print note.
- **Work.** Paragraph: the date and its class with the ruling ("The 1st
  of the month is a virtuous day, the day of joy (དགའ་བ, dga' ba):
  pacifying rites, crafts, games and all white work go well", 20 words at
  least), the perilous-day sentence kept in it since it
  changes the ruling. Rows: *Also called* (Nandā), *Remedy* (the empty
  and full days'), *La today* (the seat in the body and in livestock,
  with the treatment line), *A birth today*, *A death today*,
  *Livestock*; the flood dikes left to the list; the print note to
  [lunar-dates.md](sources/lunar-dates.md). The hair date and the
  washing result stay the details they are.
- **Block.** [43](#block-43); no reading; builds on [42](#block-42) and
  [50](#block-50) (the 9th hair date; dates 1, 3 and 6 once D11 is settled).
- **Done when.** The 30 readings and their Russian; dates 1, 4, 8 and
  15 on the emulator in both languages; `CatalogTest` without the
  family's allowance.
- **Built** 2026-10-18 (block 43). Each date's paragraph is its class's
  ruling, as date 1's example: joy and victorious as the five-fold verse
  words them, the excellent day "above all, seeking friends and sowing
  seed go well" (the verse's «ཁྱད་པར»), the empty and the full day "not a
  virtuous one: in general …" (its «སྤྱིར་ན»); the four perilous dates add
  "It is a perilous day, when …" and the illness sentence. Rows: *Also
  called* (the class's Sanskrit name), *Remedy* (the empty and full
  days'), *La today*, *A birth today*, *A death today*, *Livestock*, the
  H1 *Illness* row as it was. Moved out of the paragraphs: the flood
  dikes (dates 22 and 27, already in their Avoid lists), the 29th's
  lightning and hail (under fierce rites, its print note in
  [lunar-dates.md](sources/lunar-dates.md)), "not identified" (dates 3
  and 5, in the topic file's la table); the 15th's six rites (དྲུག་ལས,
  drug las) joined its Good-for list as a wording that weighs no work
  (`Activities`: "six_rites" to none). Dates 1, 3 and 6 keep their birth
  lines as built until D11. The hair dates' results are no longer
  readings: their family note "Washing the hair today: {0}." was shown
  nowhere, the result being the lunar date sheet's *Washing the hair*
  detail, so the results are `HairDate.<n>` texts read by
  `Texts.hairDate` and their 60 short paragraphs left the count.

#### <a id="x4"></a>X4 The weekdays and the goddesses' days — built 2026-10-18

- **Gap.** A weekday's 100 words open with the planet's caste, colour
  and element and end with births and deaths; the one prohibition that
  rules the day ("hold no funeral on it: it is the door of heaven")
  stands sixth. A trigram's day packs the spirits and their brackets,
  the illness and the rites into one sentence each.
- **Work.** Weekday: paragraph the planet, setting out, the planet's own
  prohibition with its reason, and the closing verse's days of no
  virtuous work; rows *Strong when*, the planet's line as *Also called*
  ("the la weekday (བླ་གཟའ, bla gza') of kings; royal caste; peaceful;
  pale red; fire"), *A birth today*, *A death today*. Goddess's day:
  paragraph the goddess and what an illness that comes on it is traced
  to; rows *Spirits that harm*, *Illness*, *Rites*.
- **Block.** [43](#block-43) with X3; builds on [42](#block-42) and
  [50](#block-50) (Tuesday's saddling).
- **Done when.** The 7 and the 8 with their Russian; Sunday and the
  fire trigram's day on the emulator in both languages; `CatalogTest`
  without the family's allowance.
- **Built** 2026-10-18 (block 43). Weekdays as Sunday's example: "<the
  planet>'s day", setting out, the planet's own "even when strong"
  prohibition with its reason (none for Mars, whose «གྲི་མི་འདུལ» is not
  settled), and on Tuesday and Saturday the closing verse's days without
  virtuous work; Tuesday's name Auspicious moved into *Also called* with
  caste, colour and element; what goes well when the planet is strong
  (Wednesday's increasing rites, Friday's rites of power, Saturday's
  killing) closes *Strong when*; Thursday's funeral feast stands in *A
  death today*. Wednesday's "some forbid burial" and Friday's "some
  forbid fire offerings" left the readings, as views the verse gives as
  some people's (rules 6 and 9; [weekdays.md](sources/weekdays.md)). The
  goddesses' days: "The day of the <element> trigram is the day of
  <goddess>, … of the eight goddesses counted through the month", and
  what an illness is traced to; rows *Spirits that harm*, *Illness*,
  *Rites*. The sky trigram's honorific «ཆབ་གཏོར» is named as the water
  torma (ཆུ་གཏོར, chu gtor), D7's one name per Wylie
  ([lunar-day-signs.md](sources/lunar-day-signs.md)). `CatalogTest`'s
  allowance is empty: every reading holds the shape.
- **Checked** (X3 and X4). `:core:test` and `:app:testDebugUnitTest`; on
  the emulator in English and Russian, Kyoto: 14 October 2026 (the 4th),
  18 October (the 8th, a Sunday), 26 October (the 15th, the six rites in
  its Good-for list), 10 November (the 1st) and 24 October (the fire
  trigram's day), each sheet read to its last row. No store shot shows
  these sheets; no release build (no enum key changed).

#### <a id="x5"></a>X5 The combination and the special days — built 2026-10-16

- **Gap.** The strongest voice has the thinnest text: the 28 named
  combinations are verse tags of 10 to 20 words ("Fear of illness.",
  "Understand it as pounding: … heads are broken"), the element pairs
  close with "Of the ten, it is among the three that sustain (གསོ་ཐུབ, gso
  thub), which come after the three good" nine times over, the paired
  and the avoid-entirely combinations each describe the other in a
  sentence that cannot be followed, the burning date's reading is the
  table of all seven weekdays' dates, and the Vajra Treatise's days
  repeat their preamble five times.
- **Work.** Named combination: the paragraph says what the day brings
  and which way it cuts, the tag's image marked as an image where it is
  one. Element pair: the rank as a row ("*Rank* · one of the three
  good"). Special days: the cross-reference between the paired and the
  avoid-entirely combinations said once, plainly, in each; the demon
  days' seven works as a row. Burning date: the paragraph the ruling,
  *How it falls* today's pair ("Sunday and the 12th") and *Remedy* its
  own. Vajra Treatise: the preamble as the family note. The five special
  dates and the eleven festivals checked for the shape, their length
  already right.
- **Block.** [44](#block-44); no reading; builds on [42](#block-42).
- **Done when.** The 28, 10, 15, 5 and 11 and the burning date with
  their Russian; the combination sheet on a day where the parts agree
  and one where they disagree, on the emulator in both languages;
  `CatalogTest` without the families' allowance.
- **Built** (2026-10-16). *Named combinations*: each opens on its
  ruling, "A lucky combination: wealth is found", and grows from its
  verse (pp. 331–332, combinations.md) to 25–45 words, an image marked
  as one ("its image is the pestle that pounds, the demon's son");
  phrases whose meaning the verse does not give stay in the topic file;
  the lord of death's "enemies and thieves" rested on Yigdzin-1's «རྐུན»,
  which the scan reads «ཀུན», and is gone. The combination is not named
  in its own paragraph: the title carries it, and "growth" in a bracket
  would name both *'phel ba* and the fire pair's *'phel 'gyur*.
  *Element pairs*: the verse's close (p. 333), that the pair makes every
  work good or bad over the weekday and the mansion, is the family's
  note; the grade is a *Rank* row; the works the lists give are gone
  from the paragraph. *Special days*: WB p. 337's "the planet's and the
  mansion's own results are the main thing" is the note; the paired
  combination and the day to avoid entirely each say once that on every
  weekday but Thursday the other falls with it; the demon days' seven
  names are an *Also called* row; the accomplishment combination's
  nectar name and the day to avoid entirely's burning name are rows.
  *Vajra Treatise*: the note says it is that text's reckoning; the main
  table's reading of the kind comes first, then its own line where it
  has one (approach and accomplishment, an ill omen), its rows the main
  table's. *Burning date*: the ruling, *Remedy* and *How it falls* with
  the shown day's pair ("today Saturday and the 7th", `Texts.burningDate`;
  `Weekday.<DAY>.inText` for the Russian «суббота»), the seven weekdays'
  table left to burning-dates.md. *Observances*: Rabten's front matter
  (special for any wholesome action with the full and new moon and the
  eclipse days, the eight precepts, the tantra's feast offerings on the
  10th and 25th, Sojong); the 10th's line naming its prayer is gone,
  as readings name no source (U7). *Festivals* (festivals.md): WB's month heading
  (vol. 1, p. 176), the great occasions (དུས་ཆེན, dus chen) the almanac
  writes at the head of their month, is the note, WB added as a source;
  Rabten's Dharma festivals say that every action on them is strongly
  multiplied, as his calendar does of them; Henning's three (Kālacakra,
  the birth, the entry into the womb) give their date. X5's allowance
  line is deleted; the Russian catalog header no longer lists the
  retired *Another view*.
- **Checked.** `:core:test`, `:app:testDebugUnitTest`; every paragraph
  20–60 words as the sheet shows it, the note filled, in both languages;
  on the emulator in English and Russian, the combination sheet on
  2026-10-16 (movement and earth–earth, agreeing) and 2026-10-17 (pestle
  and earth–earth, disagreeing), the special days' sheets on 2026-10-17
  (day of union and the burning date, Saturday and the 7th), 2026-10-19
  (day of growth and the Vajra Treatise's demon day) and 2026-10-22 (day
  of union and the day to avoid entirely). No store shot shows these
  sheets.

#### <a id="x6"></a>X6 The yogas, the karaṇa and the mansions — built 2026-10-17

- **Gap.** A yoga opens with its verse's headword as the ruling ("Death
  in prison.", "Middling: leprosy breaks out.") and ends with the hours
  to avoid at its start in a sentence; a mansion's paragraph lists the
  verse's works the lists repeat, then its nature, planet and foods, and
  three mansions carry a note on the list of classes.
- **Work.** Yoga: the ruling first ("Avoid it for its whole length: …"),
  the headword as "its image is death in prison" after; the avoided
  start as a row (*Avoid its start* · "the first three chutsö (ཆུ་ཚོད, chu
  tshod), 1 h 12 min"). Mansion: the paragraph its nature and what it
  suits; rows *Planet*, *Foods*, and the class note as *Another view*;
  the family's note on verse and lists kept as the one place that
  explains the two lists. The karaṇa's one reading checked.
- **Block.** [45](#block-45); no reading; builds on [42](#block-42).
- **Done when.** The 27, 28 and 1 with their Russian; Parigha and Hasta
  on the emulator in both languages; `CatalogTest` without the
  families' allowance.
- **Built** (2026-10-17). *Yogas*: each opens on its tone, "A lucky
  yoga: …", "A mixed yoga: …", the three of the avoidance verse's whole
  length "An unlucky yoga, avoided for its whole length: …", and grows
  from the long reading (yogas.md, *The readings in the shape*) to 20–40
  words, a headword that is an image marked as one ("its image is death
  in prison", rdo rje's "thunderbolt of the sky"); the six avoided at
  their start have it as an *Avoid its start* row ("the first nine
  chutsö (ཆུ་ཚོད, chu tshod), 3 h 36 min"). Sel ba's "naming, harmony and
  glory" left the reading: in the long reading they stand in the lines
  that close on «ངན» (question 159); las bzang's «སྨོན་འཇུག» is aspiration
  and engagement, no longer setting out. *Mansions*: no family note; the
  paragraph is the mansion's place among WB's seven classes with
  Henning's nature and kind of work ("Hasta is one of the four unstable
  and changing mansions, light and swift in nature: trade, love, the
  fine arts and the like go well"), WB's class alone for Śatabhiṣaj,
  Uttarabhādrapadā and Revatī (no other view, SPEC §8.1 rule 6; Henning's
  lines in mansions.md), Bharaṇī with Henning's line alone; *Planet* and
  *Foods* rows. How the lists are joined, which the note told in the app's
  words, is the sheet's note under the gloss (`tib_mansion_lists_note`,
  a new `Annotation.note`). *Karaṇa*: Viṣṭi's ruling, with its common
  remedy (karanas.md) as a *Remedy* row; the halves' figures wait on
  question 145. *Electional*: the family note and its four texts, which
  no sheet showed and which said the app does not count the rising sign
  (untrue since E4), are gone; the lists keep a key with no text, as the
  karaṇas' lists-only readings do. `ReadingRow` gains AVOID_START, PLANET
  and FOODS (SPEC §8.1 rule 3, §10.3); X6's allowance line is deleted.
- **Checked.** `:core:test`, `:app:testDebugUnitTest`; on the emulator in
  English and Russian, Viṣṭi and Śūla on 2026-10-22, Vyāghāta and Revatī
  on 2026-10-26, Parigha on 2026-10-31, Hasta and Vaidhṛti on
  2026-11-07, the rows in their place and the label column holding
  "Avoid its start" and «Избегать начала». No store shot shows these
  sheets.

#### <a id="x7"></a>X7 The earth lords, Rāhu and the month's entries — built 2026-10-18

- **Gap.** The longest texts of the page, unweighed: 98 earth-lord
  readings of up to 529 words, each a calendar in prose ("in spring the
  8th, 7th and 4th of the three months in turn, in summer the 7th, 7th
  and 26th …"), "spring counted from the 11th month" 23 times, the
  hearth god's sentence in all 12 animal days; Rāhu's hourly course in
  two readings; the great black day's table before its ruling; the
  black month's 263 words; the eclipses' arithmetic inside the reading.
- **Work.** Paragraph: the earth lord and its ruling on the shown day
  ("Avoid works for the dead, a bride, consecration, feasts and funeral
  rites, and above all sending wealth out of the house"). Rows: *How it
  falls* with today's line only ("the 8th, 18th and 28th of the spring
  months; today the 8th of the first month of spring"), which needs the
  engine to hand each course the date and season it matched
  (`EarthLordCourse`, `MonthEntries`; most readings take {0} already);
  *Remedy*; no *Another view* (the other courses are not shown since
  2026-10-11, SPEC §5.13); the
  tables to [earth-lords.md](sources/earth-lords.md); the animal days'
  hearth god and Serdzö Serpo as one row; Rāhu's hourly course once, in
  the family note; the eclipses' limits as a row. The great black day,
  the black month, Kikang's, the sage's and the pig's days and the comet
  the same.
- **Block.** [46](#block-46); no reading; builds on [42](#block-42) and [50](#block-50) (the horse day's witness).
  Block 50 put the horse day's witness, which O8 lengthened past the
  paragraph's 60 words, into its *A death today* row
  (`reading.EarthLord.HORSE.death`); the other eleven animal days still
  carry their witness in the paragraph and move to the same row here.
- **Started** 2026-10-11 (block 42's review): the lesser black day, SPEC
  §8.1's third example, stands in the shape in English and Russian, its
  ruling paragraph, *Remedy* and *How it falls* (its *Another view* row
  and the other view's reading `.other` removed the same day with every
  earth lord's other view, SPEC §5.13). The engine names the line: `CourseDay.date`, the date that fell,
  and `EarthLordCourses.fall`, a `CourseFall` (the season-month, the date,
  the season's three dates in turn), which `Reading.today` carries into
  the rows' {0}, written from the catalog's `CourseFall.*` and
  `DateOrdinal.*`; the other courses that give a date to each
  season-month can take it as they are brought to the shape.
  `CourseFallTest`: each season, the winter's 4th, 18th and 16th, no
  other view's 9th, 19th or 29th, the reading's rows, and
  2000–2049 day by day (a skipped date has no course, a doubled one the
  same line on both days). Allowance 109 → 107, then 92 with the other views' texts
  removed. Checked on the emulator on
  28 October 2026 (the 18th of Hor month 9, the course's) and 29 October
  (the 19th, the other view's) in English and Russian at font scale 1.0
  and 1.3, and in the release build for raw keys.
- **Done when.** The 98, 61 and the month's entries with their Russian;
  no reading over 60 words; the lesser black day (done), the eight classes and
  the sky door on the emulator in both languages, each *How it falls*
  row naming the shown day's line; `EarthLordCoursesTest` unchanged;
  `CatalogTest` without the families' allowance.
- **Built** (2026-10-18). *The engine names every line*: `EarthLordCourses.fall`
  gives each course a `Fall`, the line of its table that holds the shown
  day, in four forms (SPEC §8.2): `CourseFall`, the season's three dates
  (the lesser black day's, Piling Parma's, Pungpo Zortok's, Halkyi
  Nakpo's, Namjor's, Zadün's and Barkyi's, the nyen's moving); `MonthFall`,
  the month's own dates, one ("…, which is today"), several, ranges ("the
  10th, 17th to 22nd and 24th to 30th") or all but a few ("every date of
  the last month of winter but the 27th"); `AnimalFall`, the day animals
  of the month or the season (Zinpung, the sky dog, the black day); and
  `EveryMonthFall`, the sky doors'. Each carries the hour, time of day or
  way the source gives for its month (`CourseAt.*`), and `CourseDay`
  carries the date's animal. `Reading.today` gave way to `Reading.falls`:
  the *How it falls* row needs no catalog entry, and a course doing two
  things on a date (the nyen moving and turning back) has a line for each,
  under what it does. The great black day, Rāhu among the earth lords
  (`Texts.rahuSeason`, which the page now calls) and Rāhu's course by
  month take the same lines; the black months' lines are rows of their
  third's or year's key (`Reading.alsoRows`), the eclipses', the seasonal
  signs' and the comet's the rule that marks them. Only the planets' and
  ogres' times name no line: their paragraph names Viṣṭi's dates.
  *Readings*: every course's paragraph is its ruling for the day, 20–60
  words as the sheet shows it in both languages, every day with several
  entries included (the classes' times reworded to fit three at once);
  the remedies are *Remedy* rows in the imperative, each sky door's its
  own (`alsoRows`); births, deaths and illnesses are rows; the tables, the
  hours and directions of the eight classes, the nāgas' deeds, the ransom
  ways, the other courses and "some say" are left to
  earth-lord-courses.md, which lists them. The twelve animal days: the
  paragraph where the earth lord sits and whom it harms, then *Remedy*,
  *Spirits that harm* (Serdzö Serpo and the hearth god, one row as the
  item asked) and *A death today* (the witness, as the horse day's since
  block 50); the family note says the reckoning is for the dead, without
  the app's "the lists". The great black day: the event inside the
  ruling, "the mute one bursts" dropped (SPEC §8.1, rule 7;
  great-black-day.md). Rāhu: the course through the day once, as a new
  row, *Through the day* (`ReadingRow.THROUGH_DAY`, SPEC §8.1 rule 3), of
  Rāhu's family note, which the general course now shares
  (`reading.RahuGeneral` deleted); in the note itself, as the item put
  it, the 70-word course would have broken the 60-word paragraph. The
  detailed course's paragraph names only works its lists do not. Rāhu's
  course by month and among the earth lords: the month in the line, the
  paragraph the form or the ruling (`reading.RahuSeason.<0–10>` deleted).
  The eclipses' limits, the seasonal signs' mean-sun points and the
  comet's count are *How it falls* rows; the eclipses' "weighs nothing in
  the day's verdict" left the reading (rule 8). Named one way, as D7
  asks: the black sky dog, the ogre for *srin po*, the rabjung, the
  Collected Dhāraṇīs as *gzungs bsdus*, and in Russian «демон» and «белое
  подношение дыма»; that settled one break each of X4's and X9's counts
  (24 → 23, 22 → 21). X7's allowance line is deleted.
- **Checked.** `:core:test` (`CourseFallTest`: every course but the black
  months and Viṣṭi's classes names its line on every month, date, day
  animal and year, the day always on it; each form in English and
  Russian; the nyen's two lines; the rows of the animal days, the black
  months, the sky door, Rāhu and the great black day;
  `EarthLordCoursesTest` unchanged), `:app:testDebugUnitTest`; every
  rendered X7 sheet 20–60 words in both languages. On the emulator in
  English and Russian, the earth lords' sheet on 2026-10-30 (the eight
  classes turning back, Pukton, the nāgas, the general sky door, the black
  day) with the great black day, and on 2026-11-12 (Halkyi Nakpo at the
  hare hour, the eight classes striking "every date … but the 27th", the
  nyen's two lines, the sky door, the sky dog), Rāhu's *Through the day*
  and the dog day's earth lord. No store shot shows these sheets.

#### <a id="x8"></a>X8 Your day — built 2026-10-18

- **Gap.** "One of your six personal mansions, by the animal of your
  birth year" opens all six; two readings end in the app's mechanics
  ("so with a birth date set the day in brief gives you no list of good
  works on it"); the hundred feet's 229 words explain the wheel before
  the day's result; the kinship note describes the app's screens.
- **Work.** The family sentence as the family note; the app's
  mechanics left to `brief_avoid_all_note`; the hundred feet's paragraph
  the day's result, the wheel and the six places as rows; the own days'
  and the kinship's app sentences out. The personal days, the own days,
  the personal mansions, the kinship, the hundred feet, the spear and
  the great spear.
- **Block.** [47](#block-47); no reading; builds on [42](#block-42).
- **Done when.** The 29 with their Russian; Your day on the enemy
  weekday and on a hundred-feet day on the emulator in both languages;
  `CatalogTest` without the families' allowance.
- **Built** (2026-10-18). Every reading opens on the shown day ("Today is
  your …", "Today the moon is in your …") and says which way it cuts in
  rule 6's words, 20–60 words as the sheet shows it in both languages.
  *Personal days*: Rabten's 15-word sentences grown from WB vol. 2,
  p. 330, whose la, life-force and slayer weekdays (*bla*, *srog*, *gshed
  gza'*) are Rabten's luck, life and anti days cell for cell
  (personal-mansions.md): the la weekday's activities at its strong time,
  the life-force weekday virtuous, the slayer weekday avoided, and
  forceful rites quick when one's slayer weekday is strong and an
  enemy's la weekday weak; WB is now each one's `also` source. *Personal
  mansions*: the family sentence is the note (`reading.PersonalMansion`,
  "It is one of the six personal mansions of your birth year's animal");
  the la, power and slayer mansions' births are *A birth today* rows,
  worded for the child whose own mansion it is; "the Sakya manual calls
  it" left the reading (U7); the death mansion bracketed *'chi ba'i skar
  ma* as the brief's note brackets it (D7). *Own days*: each weekday named
  with its bracket (*skyes*, *rang*, *ma*, *grogs*, *bu*, *dgra gza'*), "those
  listed here" and the brief's mechanics out (left to
  `brief_avoid_all_note`), the own weekday's other names an *Also called*
  row; the birth mansion's "which the app does not make" became the
  sheet's note `tib_birth_mansion_note`. *Kinship*: the 118-word note,
  which described the app's screens, became the pebble sheets' note
  `pebble_note` (strings.xml) and a family note of eight words ("The
  other aspects read as the vitality does"); best, second and worst are
  *Rank* rows. *Hundred feet*: the day's result first, the note naming the
  malefics and benefics, the wheel as the note's *How it falls* row (the
  rule that marks it, as X7's eclipses), the six places' results on the
  sheet's holder rows ("Mars: epidemics and grave illness",
  `Holder.<NAME>.struck`); the benefics' result says "one or two", as the
  engine counts it. *Spear and great spear*: the note gives the ruling and
  the day's omens, then each body's line for a bride
  (`reading.Spear.<BODY>`, `reading.GreatSpear.<BODY>`, joined by
  `Texts.spear`); *Setting out* and *A birth today* rows. No side, tone or
  list changed. X8's allowance line is deleted.
- **Checked.** `:core:test` and `:app:testDebugUnitTest`; at most three
  bodies make a spear on any day of 1950–2100 (all four malefics would be
  63 words, three at most 56). On the emulator, English and Russian, the
  test person (1976-06-01): 18 Oct 2026 (Your weekday: luck day and mother
  weekday; hundred feet, aims fail; spear of Venus), 5 Nov (anti day and
  enemy weekday; slayer mansion), 7 Nov (hundred feet with Conception
  struck by Mars), 14 Nov (spear of Mercury and Venus), 1 Nov (birth
  mansion with its note; great spear of Saturn), 12 Nov (power mansion),
  and the Health screen's vitality pebbles with `pebble_note`. No store
  shot shows these sheets.
- **Release build** (2026-10-18), signed with the debug key: no INTERNET
  permission; it showed the hundred feet's holders raw
  ("HundredFeet$Holder.ADHANA", and the new ".struck" after it). R8 drops
  the InnerClasses attribute, so a nested enum's `simpleName` is
  `Outer$Inner`; `glossKey` now takes the name after the last `$`, which
  mends the eleven nested enums (the holders, the month entries' rise,
  parts, times and seasonal signs, the rough times, the sun's terms, the
  eclipses' bodies and ends), raw in every release since they were built.
  Checked on the emulator: the hundred feet, spear and Your day sheets and
  the whole Tibetan page on 18 October and 14 November 2026 and 15 January
  2027, English and Russian, without a raw key.

#### <a id="x9"></a>X9 The hours panel — built 2026-10-18

- **Gap.** The sky dog of the hour is 435 words, its twelve places and
  what each forbids; the earth lords of the hour 221; the astrologer
  spirit's five texts in both its day's and its hour's reading; the
  earth king's other way a reading of its own (removed 2026-10-11); the combination period's
  thirteen readings already in shape.
- **Work.** The sky dog: the paragraph this hour's place and its ruling,
  the twelve places a row each or the table in
  [earth-lords.md](sources/earth-lords.md); the earth lords of the hour
  the same; the five texts once, in the day's reading, the hour's
  pointing to it in a sentence; the black hour, the rough times, the own
  year's hour, the hidden earth lords and the nectar periods to the
  shape.
- **Block.** [48](#block-48); no reading; builds on [42](#block-42).
- **Done when.** The panel's readings with their Russian; an hour with
  the sky dog, the black hour and a nectar period on the emulator in
  both languages; `CatalogTest` without the families' allowance.
- **Built** (2026-10-18). Every reading of the panel is 20–60 words as
  the sheet shows it, in both languages, and opens on what it is in the
  shown hour and which way it cuts; rule 6's words throughout (the
  combination periods "to be avoided" and "to be accomplished", "all keep
  away from it", "thrive"; the hours' works "go well" for "auspicious",
  "succeed", "turns out"). *How it falls* rows are the shown hour's line,
  built by a new `HourFall` (rule 4): the earth lords and the hidden lord
  on the hour's own place, the astrologer spirit and the earth king on
  theirs ("in the hare hour, on the place of the snake, the upper south"),
  the sky dog's head and tail, the date's two black hours, the rough
  time's four with this one and its clock time, the own year's hour, and
  the nectar periods' rule with the weekday's clock hours. The earth
  lords' 195 words: who sits on the place, what goes badly, whom they
  take offence at; Yudzö Ngönmo's form an *Also called* row, the two
  remedies one *Remedy* row. The astrologer spirit's 151: on the luck of
  the hour's three animals, its offence; the triads' table left to
  earth-lord-courses.md and the five texts to the day's reading, which the
  hour's *Remedy* points to. The earth king's 117: on the four-slayer
  three animals on, what harming it does; the mouse hour's example left to
  the topic file, the empowerment and the feast offering a *Remedy* row
  (*tshogs* named as the special days name it, D7). The hidden lords'
  106: the hour's lord named with its bracket in the note, which no longer
  lists all twelve; the general remedies a row of the family. The sky
  dog's 407: lying round the places, the head's and the tail's works,
  above all a bride, its offence; the thread-cross a *Remedy* row; the
  other ten parts and the old reckoning to earth-lord-courses.md, as a
  table with the etext's Tibetan. The black hour's 103: what it is and
  that fierce work strikes home, the swastika a *Remedy* row. The nectar
  periods' 96: what this clock hour is, the rule the line, "the works
  below" and the clock gone. The rough time's clock and the hour's reach
  over the day's other factors left the readings for two notes,
  `hours_rough_note` and `hours_over_day_note` (rule 8). *phyed yol* named
  one way, "past the half", in the sheep and the ox hours (D7). X9's
  allowance line is deleted; X3 and X4 keep theirs.
- **Checked.** `:core:test` (`HourFallTest`: each line in English and
  Russian, the remedies as rows, the five texts only in the day's
  astrologer spirit) and `:app:testDebugUnitTest`. On the emulator in
  English and Russian, Kyoto, 18 October 2026 (a bird date, a Sunday), the
  test person (1976-06-01): the horse hour with the black hour, the noon
  rough time, the hour against the day's animal sign, what the hour is
  for, the earth lords, the astrologer spirit, the earth king, the hidden
  lord Jilam and the sky dog; the bird hour's nectar period and sunset;
  the dragon hour as the own year's. The release build, signed with the
  debug key: no INTERNET permission, the same sheets word for word as the
  debug build in both languages, no raw key. No store shot shows these
  sheets.

#### <a id="x10"></a>X10 The rest — built 2026-10-16 (the year sheet went with Y1)

- **Gap.** The astrologer spirit's day reading (190 words) explains its
  count before and after its "Today"; the date's mewa gives the table's
  rule before the day's number (the twelve links' 175-word rule went with
  T2.27, block 24, 2026-10-15, each link now in the shape). The year
  sheet was brought to the shape by [Y1](#y1) (block 57, 2026-10-16).
- **Work.** The astrologer spirit and the mewa to the shape,
  the count as *How it falls*, uniform where possible with the hours'
  and the day's readings. The festivals and
  the special dates were checked in X5.
- **Block.** [49](#block-49); no reading; builds on [42](#block-42). It
  was to come last, as the lightest; the owner had it built on
  2026-10-16 with Y1, ahead of 43–48.
- **Built** (2026-10-16). The astrologer spirit's 161-word reading is a
  45-word paragraph (where it dwells today, works towards it go badly,
  whom it takes offence at) with *Also called* (Sewa Lakhyen) and
  *Remedy* (the torma and the five texts) as rows; the day's mewa count
  from a wood-mouse day is left to earth-lords.md, the direction and the
  square stay in the sheet's diagrams. The date's mewa reading drops
  "the works below" and the month's count (almanac-page.md). The thirty
  haircut results stay short, as the page's rows show them, under two
  notes, a good date's and one to avoid (`reading.Haircut.GOOD`,
  `.AVOID`), which open on the result and whether the cut goes well or
  badly. The links were in the shape since block 24. X10's allowance
  line is deleted; X3–X9 keep theirs.
- **Checked.** `:core:test`, `:app:testDebugUnitTest`; on the emulator,
  2026-10-16 (the 6th, "A lawsuit"), the haircut, astrologer spirit and
  date's mewa sheets in English and Russian; the store shots retaken
  and compared with the committed ones, none changed (shot 3 shows none
  of these sheets); the listing's line on the readings read against the
  sheets, now naming the remedy too.
- **Done when.** The readings with their Russian; the astrologer spirit's
  row and the link's on the emulator in both languages;
  `CatalogTest` with no allowance left; store shot 3 retaken in both
  languages, since it shows a reading opened; the listing's "every
  reading explained" line read against a sheet.

## Election

### E. The best day for a work

The user picks a work and a span; the app lists the days the weighing
makes good for it, best first, each with the factor that decides it, and
the hours within them. E1–E6 are built (2026-10-09): the design, the
order of the good days and what in it is sourced are SPEC §5.14, the
screen §10.8. Each day is weighed exactly as its page weighs it (§5.12),
so every voice built later (T2.13, T2.20–T2.23) reaches the election by
itself. The 旧暦 page has its own, unranked (E5, SPEC §7.6).

#### The person's days and the hour against the day

What WB says on whether a person's own days or an hour outweigh the day
(asked by the owner 2026-10-08; [personal-mansions.md](sources/personal-mansions.md),
*Works on one's own days*; [weighing.md](sources/weighing.md)):

- **A person's bad days are absolute.** On the enemy weekday of one's
  element "every work is to be avoided" (*las kun spang*); on the death
  mansion, the *gshed skar* (p. 330), "anything is bad" (p. 338). The
  line before them: where weekday, date and mansion are good, "something
  else can arise: from white bronze and good iron fly hot, burning
  sparks"; by its place, a good day can still go wrong for one person
  (the verse does not draw the link).
- **A person's good days only name works.** The birth and own weekday are
  good for contests, trade, pleading a case, races; the birth mansion for
  offerings, giving, a new house. Nothing says one lifts a bad day.
- **WB ranks a person's days against nothing**, neither the combination
  nor any single factor (open question 14). Tshul khrims rgyal mtshan's
  gloss, that they lead as the *dmigs bsal* over the general ("over a
  treatise's general rule the particular is strong"), is not WB's and is
  not built.
- **The hour:** only the combination period is placed above the day: the
  powers of all the factors are complete in it, "held highest of all"
  (p. 376). KP's rule 2, the hour above the *nyi ma*, sets the hour
  against the weakest of the seven only, not the day. D1 keeps both out
  of the day's place.

So a person's bad day takes the day away from that person (E6, D3); a
person's good day and a good hour add nothing to a day's place.

#### <a id="e1"></a>E1 The engine — built 2026-10-09 (SPEC §5.14)

`Election`, `ElectionSpan` and `ElectionDay` in `core/.../texts/Election.kt`;
`ActivityNote.decider` names the voice that decided each work, and
`DaySummary.combinationTone` the combination's tone where it decides the
day. `ElectionTest` holds the sides against `sideOf` on 2000–2049 for all
137 works and the order of the good days, and keeps October 2026 ranked
for haircuts, weddings and setting out. The witness, the tibetastromed.ru
election of October 2026, read again on 2026-10-09: haircuts 7 of 7,
weddings 11 of 12, setting out 11 of 14, each difference a WB reading
([tibetastromed.md](sources/tibetastromed.md), *The election, October
2026*). The CLI prints one (`--elect WORK [date] --months N --all`).

#### <a id="e2"></a>E2 The screen — built 2026-10-09 (SPEC §10.8)

"Choose a day" in the menu, "Choose a day for it" in a work's balloon in
the brief, and a reading's wordings opening their works; the families or
a search; the three spans; a grid per Tibetan month (`WorkGrid`, which
the haircut sheet now uses too) and the best days, each opening its
workings and its day page.

#### <a id="e3"></a>E3 The hours of the chosen days — built 2026-10-09 (SPEC §5.14)

The combination periods good for the work and those to avoid it, once
per Tibetan month (`Election.hours`), and on each good day its nectar
periods where their reading names the work. Hours do not move a day's
place (D1).

#### <a id="e4"></a>E4 The works' own rising signs — built 2026-10-09 (SPEC §5.10, §5.13, §5.14, §10.3)

- **Gap.** WB's chapter 34 gives most works their rising signs (naming,
  clothes, the new home, the hearth, shows, sewing, storehouses,
  banners …: [white-beryl-ch34.md](sources/white-beryl-ch34.md),
  *Inventory*), and KP's boxes carry them; [kp-activities.md](sources/kp-activities.md)
  lists them as not calculated. Since the combination period was built
  the app has the sign rising in each hour, so they are calculable.
- **Work.** Read each work's rising signs on the scans (I1KG12907, img.
  386–436), KP's boxes as witness, into white-beryl-ch34.md; build them as
  the work's own hours, which stand above the sign's general reading for
  that work as its particular case (WB p. 376: «དམིགས་བསལ་བྱུང་ན་དེ་ཉིད་གཙོ»).
  E3 and the hours panel show them.
- **Done when.** A vector from the text; SPEC §5.13. The same lists'
  twelve links (not calculated) and *sme ba* (calculated, §5.11, but
  given no rank by the texts) stay out.
- **Built 2026-10-09.** All the
  chapter's lists read on the scans (white-beryl-ch34.md, *Rising
  signs*: 55 works; Yigdzin-1 as witness, KP's boxes not read box by box);
  `WhiteBerylWorks.SIGNS` into `Factors.signs`; `Texts.WORKS_SIGN` (the
  works of each sign) and `Texts.period` (the period's reading without
  what the works' own hours decide the other way); the hours panel's
  row "The works' own hours"; `WorksSignTest`; SPEC §5.10, §5.13, §10.3.
  E3's hours (`Election.hours`) take the work's own signs first, then
  `Texts.period`; `ElectionTest` checks journeys against p. 393.

#### <a id="e5"></a>E5 The 旧暦 page — built 2026-10-09 (SPEC §7.6, §10.8)

- **Gap.** No source ranks one kind of 暦注 above another (SPEC §7.5,
  [kyureki.md](sources/kyureki.md)); the page lists both sides unweighed,
  only the lower band's own rules setting some aside.
- **Work.** The same screen on the 旧暦 page: the days on which some
  annotation names the work good and none, after the lower band's rules,
  names it to avoid, and the disputed days apart, in date order, with no
  "best" and a sentence that the almanac gives no order. The Qing
  協紀辨方書's six grades (kyureki.md) stay out: nothing ties them to the
  Japanese almanac.
- **Built 2026-10-09.** `KyurekiElection`, `KyurekiSpan` and
  `KyurekiElectionDay` in `core/.../texts/KyurekiElection.kt`, reading each
  day's `DaySummary` of the 旧暦 page; an annotation naming everything names
  the work. The election screen takes the calendar shown, and the 旧暦
  page's breakdown and readings open it. `KyurekiElectionTest`: October
  2026's weddings as the vector, 2020–2039 against each day's breakdown,
  the 三箇の悪日 for 1 June 1976. The CLI prints one
  (`--elect WORK [date] --kyureki`). Checked on the emulator in English and
  Russian.

#### Owner decisions — settled 2026-10-08

- **D1 An hour against the day: no.** KP rule 5 holds the combination
  period above everything, so by the letter a day to avoid becomes good
  in an hour whose sign names the work good. But every day of a month
  has every sign (E3), so offering such days would offer every day the
  combination's verdict forbids. The owner decided: the day's place is
  the day's weighing, and the hours say when within it; a day to avoid
  is not offered, not even "only at these hours".
- **D2 The 旧暦 election: yes.** E5 goes ahead, unranked, as the page
  itself is.

#### The election's order

E1, E2 and E3 built together (2026-10-09); E4 after its reading, the
same day; E5, on E2's screen, the same day. Each is done when its code has tests, SPEC has it (§5.14,
§10.8), it is checked on the emulator in English and Russian, and the
release build is checked before the tag (R8: the picker names works
through the catalog by their enum's class name). The store listing and
the website name the election only once it is released.

#### <a id="e6"></a>E6 The days WB avoids every work on, for you — built 2026-10-09 (SPEC §5.12, §5.14, §10.3, §10.8)

- **Gap.** "For you" lists the person's days with their dots and weighs
  none (T2.3), so the enemy weekday and the death mansion read as one
  unlucky row among others, though WB avoids every work on them (p. 338;
  *The person's days and the hour against the day*, above). The death
  mansion is already among the six personal mansions; the enemy weekday
  by element is T2.19's (built 2026-10-08, `OwnDay.ENEMY_WEEKDAY`).
- **D3 — settled 2026-10-08: it needs a birth date and follows WB.** With
  no birth date set, nothing changes: the day's tone and lists are the
  weighing's (§5.12), the same for every reader. With a birth date set,
  on the person's enemy weekday and death mansion WB's words hold for
  that person: every work is to be avoided (p. 338).
- **Work.** On those days, with a birth date: "For you" says so in WB's
  words, naming the day (enemy weekday of one's element, death mansion);
  the In brief row says "every work to avoid, for you" beside the day's
  tone; the brief shows no good list, which has no power for the person
  (as outweighed voices are not shown, §5.12), and its avoid list stays;
  the election (E1–E2) does not offer the day for any work and says why
  on tap. The combination period is not placed against a person's days
  by any text, so "By the hour" stays as it is (the good list hidden and
  the hours kept: both agreed by the owner, 2026-10-08). SPEC §5.12
  states the exception: the person's days stay unweighed except these
  two, which WB makes absolute.
- **Done when.** A vector of such days for the test birth date
  (1976-06-01); `DaySummaryTest` unchanged with no birth date; the reading
  cites p. 338; SPEC §5.12, §10.3, §5.14; Russian. The "For you" and brief
  half needs no election; the election half ships with E1–E2.
- **Built, the "For you" half (2026-10-09).** `DaySummary.avoidAll` holds
  the enemy weekday and the slayer mansion (the death mansion) as entries;
  `good` is empty on them, `sideOf` and the activities stay the weighing's,
  so the Almanac's haircut row and the hours do not change. The In brief
  row says "every work to avoid, for you" and gives only the avoid count;
  the sheet names the day under the tone and quotes p. 338 in "For you";
  both readings cite p. 338 (EN, RU). Vector: October 2026 for 1 June 1976
  = Thursdays 1, 8, 15, 22, 29 and Pūrvaphalgunī on the 9th (gre in
  Henning's list too).
- **Built, the election half (2026-10-09).** With a birth date set the
  election does not offer those days for any work (`ElectionDay.offered`);
  the grid draws them as a red ring and a tap says "every work to avoid,
  for you", naming the day; their side stays the weighing's. Vector: the
  same six October days (`ElectionTest`).

## Health

Health gets a screen of its own, opened from the menu, since it may draw
on Tibetan medicine (*gso ba rig pa*) beside WB (the owner, 2026-10-11).
It is the first of several screens that lengthen the menu (Reckonings,
R1, and the people's relatives, P1, follow), so its block also makes the
menu's groups collapsible (H3).

- **Sources.** The owner trusts Dr. Nida Chenagtsang's teaching strongly
  (2026-10-11), and his works are the guide to the sources, not the
  source (the owner, 2026-10-11): they are identified from a catalogue
  when the block starts (titles, editions, his name as the books print it
  in Tibetan), never from memory, and read for the classical texts they
  rest on and cite (the *rgyud bzhi* and its commentaries, the
  calendar-and-medicine texts). Those texts are read on their scans, as
  WB is (sources/PLAN.md), and the app's readings and `Source`s are
  theirs: public domain, so the app's texts stay free of any licence
  (SPEC §8.1). Where a text he names cannot be found or read, the gap is
  written into the sources file, not filled from his wording. Where he
  and WB differ on one point, both go into the sources file and the owner
  decides.
- **Asking him.** If the reading goes deep and a question stays open, the
  owner may write to Dr. Chenagtsang personally to ask for help (he is
  open to such requests, the owner, 2026-10-11): such questions are
  collected in the sources file, each with what was read and where it
  stops, ready for that letter. Nothing is sent without the owner.
- **The grey dot** (the owner, 2026-10-11): what a source gives and WB
  does not reckon, no works or no weight, is shown on its tab with a
  grey dot: it has some influence, and WB's weighing does not count it.

### <a id="h1"></a>H1. The health screen — built 2026-10-15 (SPEC §5.8, §8.3, §10.3, §10.5, §10.10)

The Health screen from the menu and from Your day, on the shown day, in
four tabs: Today (falling ill today, by the date's round of eight with
the perilous days, the weekday, the link and the goddess of the day; the
la's place by the date and its course within the day), You (the day's
mansion as a quarter of the life force's element, the grave mansions,
the la's element, the vitality and body moved from Your day, the year
of age on the seven-red), Treatment (the medical works as the person's
day page gives them) and Medicine (the Four Tantras' humours and
seasons with the grey dot), and a line that the readings are the
tradition's, not medical advice. The illness lines are Illness rows of
the date's, weekday's and link's readings, so the day page's sheets
show them too. What WB has and what the readings found is in
[sources/health.md](sources/health.md) (RD7–RD10, RD12).

- **Settled from the text.** The enemy and grave mansions are not
  absolute as the enemy weekday is (E6): p. 338 names the weekday only,
  and neither mansion has a reading for the living's day (RD10); the
  enemy quarter is shown with no dot, the grave mansions as reckoned for
  the dead. The tables' 21 among earth's four is Abhijit (vol. 1,
  p. 232), so earth's fourth and three grave mansions fall on no day.
- **Checked.** `HealthTest` (the five tables of pp. 345–346 cell by
  cell, the la's element, the round of eight, the test birth date
  1976-06-01, life force earth, la fire, over 15–24 October 2026: child
  mansions Anurādhā to Śravaṇa, friend Dhaniṣṭhā to Pūrvabhādrapadā);
  `CatalogTest` with the new families held to the shape, no allowance
  (H1), X4's allowance down to 24 with the dremo named one way;
  `TranslationsTest`. On the emulator in English and Russian
  (15 October 2026: Anurādhā a child mansion, the 5th's illness and the
  hollow of the knee, the enemy weekday's day with no good work under
  Treatment; 18 October, the 8th, a perilous day; 15 June 2027 with a
  person born 1977-06-01, the seven-red and Citrā an own mansion).
- **Left.** The six moxa dates and the syllables of Dr. Chenagtsang's
  tables, which are not WB's (RD8, RD9); the la pulse, read at the pulse
  and not by date; which Tibetan months the Four Tantras' seasons are,
  which no source read gives, so the Medicine tab names no season as
  today's; WB's illness reckoning (H2).
- **D14, decided 2026-10-15** (RD8, RD9, open questions 58 and 84,
  [sources/health.md](sources/health.md), *KL's tables in WB* and *The
  classical texts he cites*): the la's place by date follows WB's Phugpa
  list (p. 303) alone. Dr. Chenagtsang's course differs on four dates
  (the 4th the knee, WB the waist; the 10th the shoulder blade, WB the
  top of the shoulder; the 15th the crown and whole body, WB the shoulder
  blade; the 29th the instep, WB the shin). His course cites no source:
  it is the Tsuglag Rigje column of Deumar's *Precious Garland*, thirty
  of thirty dates, with the crown and sole of Mipham's *Treatise on
  Passion* on the 15th and 30th. The Four Tantras, which he cites for
  the humours, conduct by season and the la channel, give no course by
  date. The owner's word: had the difference come from the Four
  Tantras it would be followed; since it does not, WB counts higher.

### <a id="h2"></a>H2. The illness reckoning — planned 2026-10-11, built 2026-10-26 (SPEC §5.9.8, §10.10, §10.11)

- **Gap.** WB's illness reckoning (*nad rtsis*, vol. 1, ch. 25,
  pp. 417–452, img. 427–462): its fifteen starting points, and the
  elements' illnesses, where each enters the body and its signs
  (pp. 438–439). A pebble reckoning for the sick, unread.
- **Work.** Read it on the scans with R1's inventory; build its pebbles
  with R's, on a tab of the Health screen, listed in the Reckonings
  section too; Dr. Chenagtsang's works read beside it.
- **Block.** [32](#block-32); reads [RD12](#rd12), [RD8](#rd8) beside; waits on
  [26](#block-26) (the screen) and [28](#block-28) (R1's pebbles).
- **Done when.** Vectors where Dorje or WB give a worked example;
  `CatalogTest`; SPEC; both languages.
- **H2a The elements' illnesses — built 2026-10-22** (block 32, SPEC
  §10.10): pp. 435 and 438–439, read on the scan (RD12, RD22): each
  element's humour, outer field, organ, door and signs of entry, the
  ground its sick avoid, and the signs of its time of onset as an
  Illness row, five rows on the Health screen's Medicine tab with the
  grey dot; family `ElementIllness` (item H2), English and Russian.
  Checked: `CatalogTest`, `:app:testDebugUnitTest`, the emulator in
  English and Russian (the section, the wood and iron sheets). No
  release build: no enum key added. Not built, and why: the board of the
  fifteen starting points counts from "the sky-year's earth" (གནམ་ལོའི་ས),
  which ch. 25 does not define, and pp. 421–452 are read in outline only;
  the illness identified by five pebbles waits on open questions 226–228
  (the fifth pebble's trigram, the notes' seven, the shares) and has no
  worked example; the time of onset's element on open question 48.

- **H2b–H2g The illness reckoning — built 2026-10-26** (block 32,
  SPEC §5.9.8, §10.10, §10.11): the Health screen's fifth tab, Illness,
  and the same view in the Reckonings, for an illness that came on at a
  time set on a clock (the present time to begin with) on the shown day;
  `IllnessReckoning`, core `tibetan/Illness.kt`. H2b, the moment of
  falling ill: its trigram (illness, spirits and house, what it repays,
  its ransom and the rite of fleeing; pp. 427–428, 443–448), the day
  animal (pp. 428–429), the mewa (pp. 431–433), the planet of the
  weekday (p. 447), and the kind of spirit by the birth mewa against the
  moment's (pp. 433–435). H2c, the five pebbles (pp. 435–436): the life
  force of the year, month, day and hour of falling ill and the element
  most among their trigrams; the element most opens its H2a illness, and
  seventeen splits of two elements open their own. H2d, the sixteen
  fought pebbles of the nineteen (pp. 445–447). H2e, the progressed
  sign's reach, the power by the sky year's pair (thirty readings) and
  the ransom of the birth year (pp. 426–431). H2f, the nurse, physician
  and messenger (p. 445) and the hearth-keeper's stage at the hour of
  asking (pp. 440–442). H2g, the board of the fifteen starting points:
  sixty pebbles, the four plank-trees and their verdicts, the spirit by
  the body's black, the pebble door (pp. 417–425). Families
  `IllnessTrigram`, `IllnessTrigramCause`, `IllnessTrigramRansom`,
  `IllnessFlee`, `IllnessDay`, `IllnessMewa`, `IllnessSpirit`,
  `IllnessPlanet`, `IllnessSplit`, `IllnessPebble`, `IllnessReach`,
  `IllnessYear`, `IllnessRansom`, `IllnessStage`, `IllnessHelper`,
  `IllnessPoint`, `IllnessDoor`, `IllnessTree`, `IllnessBlack` (item
  H2), English and Russian, written from the corpus passages; words
  printed doubtful or not identified are left out of them.
- **Stated choices** (SPEC §5.9.8, for the owner to overrule): the
  trigram and mewa of falling ill are the moment's, as R2o finds them;
  the year's trigram for the fifth pebble by the month's triads on the
  year's animal (question 390), the month's the one its first date
  begins with; four pebbles only where the four trigrams give no single
  most element; the sky year is the year of falling ill (question 361);
  the twelve stages are the chosen person's power at the present hour;
  on the board, "the reckoned's element" is the power (as D21), "the
  sky-year's earth" the sky year's place (the *Moonbeams*' heads count
  from the sky year's thirteenth and fifth), "down" forward through the
  sixty (point 1 counted backward would land on the birth year every
  time), the count inclusive (D19), the son a speckled pebble, point 9
  from the mother's hare, and the five verdicts graded all white, mostly
  white, even, mostly black, all black.
- **Checked.** `IllnessTest`: WB's worked example of the twelve stages
  (p. 442: a wood rat woman at sunrise on decline); a day worked by hand
  (15 October 2026, 06:45: the four times' signs, the trigrams li, kham,
  li, khon, the pebbles fire, water, fire, wood, fire); the sixteen
  pebbles and the board for a fire dragon born 1976 in 2026 (points 1,
  2 and 10, the door); the spirit's kind, the helpers, the progressed
  sign. `CatalogTest` (no allowance), `:app:testDebugUnitTest`
  (`ReckoningsTest` with the new entry), `kb.py check`. On the emulator in
  English and Russian (26 October 2026, a man born 1976-06-01): every row
  of the tab, the sheets of the trigram, the spirit's kind, the illness
  by five pebbles, a fought pebble, the hearth-keeper, a point, a
  plank-tree, the spirit by the body's black and a blocked door; the
  Reckonings' row and view; the clock, 04:14 falling in the 14th, the
  Tibetan day before. No release build: the new enums are top-level,
  kept by `-keepnames enum zanshin.core.**`.
- **Left out, and why.** The nine classes of the fifteen (question 229;
  built with R5, block 60, 2026-10-31, but for the differences of
  question 428);
  the masters' reckonings read off the board (pp. 423–425: the golden
  bridges, the turquoise juniper, the wish-tree, the houses of glory and
  misfortune, the death- and life-eye, the boundary), whose rules name
  rows and pairs the text does not fix; the verdict on death (p. 445),
  whose conditions name no time or count; the pillow-guard (question
  359); the rites for the aspects in decline and in enemy rise
  (pp. 444, 448–449), which no pebble says are met; the mansion of
  falling ill (p. 447), its mansions' names read by machine only; the
  time of onset's element (question 48).

### <a id="h3"></a>H3. The menu's groups collapsible — built 2026-10-15 (SPEC §10.5)

Each group's label is a header row with a turning chevron that opens and
closes its rows; every group closed on a fresh install, each keeping its
own last state in the preferences; About & sources outside the groups.
Checked on the emulator: a fresh install opens the menu with the three
groups closed and About & sources shown; the Calendar group opened stays
open after the app is stopped and started, the other two closed; both
languages; `TranslationsTest`. Each header is a button with its state
("open", "closed") for screen readers; that TalkBack speaks it was not
heard on the emulator, where injected taps and keys did not move
TalkBack's focus into the drawer. Store shot 5 (the menu) opens the
groups it shows (`tools/emulator/store_shots.py`).

## People

### <a id="p1"></a>P1. Relatives of a saved person — planned 2026-10-11, built 2026-10-15 (SPEC §10.5)

- **Gap.** Several of WB's readings need other people's dates: the natal
  trigram the mother's year, the spouses' and the dead's obstacles their
  signs (T4, left), the bride's departure both partners' years (P3), the
  marriage and the dead's reckonings (R). People (SPEC §10.5) holds up to
  ten unrelated persons, each a name, a birth date and a gender.
- **Work.** For each saved person, the relatives the reckonings ask for
  (the owner, 2026-10-11): mother and father, spouse, and the dead whose
  obstacles WB reckons (parents, ancestors), each one of the saved people
  or added for this person alone, with what the readings need, settled
  from P2's, P3's and R's reading (a birth year at least, a birth date and
  gender where asked, the year of death for the dead). Stored on the
  device only; the `people` preference grows a relatives field, an older
  line reading as none. Whether relatives count toward the ten is the
  owner's choice.
- **Block.** [27](#block-27); reads [RD13](#rd13), [RD14](#rd14), [RD15](#rd15), [RD16](#rd16) for what each
  asks of the relatives; waits on no build. [29](#block-29) (P2),
  [30](#block-30) (P3) and [31](#block-31) (R2…) wait on it.
- **Done when.** A test of the stored format, older lines included; the
  dialogs checked on the emulator in both languages; SPEC §10.5.
- **What the readings ask of the relatives** (RD13–RD16, gathered for the
  build): the **mother**'s birth year and whether she is living (the
  natal trigram, WB vol. 1 p. 261; the secret obstacle, p. 390; the births
  on the trigrams, pp. 286–288); the **father**'s birth year, and his
  year of death (the father's luck pebble, for a son; the progressed sign
  on another's year, p. 388; the tomb obstacle, p. 390); **both
  parents**' birth years and clans (the nine-multiples of birth luck,
  pp. 412–413); a **spouse**'s birth year and gender, and for the
  marriage reckonings the whole birth (the shared trigram, p. 390; the
  departure, ch. 23, which also asks the bride's mother's marriage year
  and the year the groom's father set up house); **the dead**: the year
  of death for the tomb obstacle, the birth with its hour and the death
  with its hour for the reckoning for the dead (ch. 29, notes p. 509);
  **the household**: each member's birth year, gender, order of birth
  and station (the household's pebbles, trigrams and mewa, pp. 385–387,
  396–400, 407–409, and the reckoning for the dead's living rows).
- **Decided by the owner** (2026-10-15): each of the ten saved people is
  a user of the app and holds their own tree of relatives, which does not
  count toward the ten; every field the readings ask for is kept but the
  clan, since only some families have one.
- **Built 2026-10-15** (block 27, SPEC §10.5): `Relatives` in the app, a
  tree on each `Person` (mother, father, spouse, child, sibling, of the
  person or of any relative, and non-kin members of the household under
  the person), each with a name, a gender, a birth and a death as a year,
  a day or a day and hour, and the household; the `relatives` preference
  beside `people`; the person's dialog's Relatives row, the tree dialog
  and the relative's dialog. The two marriage years of ch. 23 are not
  asked yet: P3 adds them with the departure, the format taking new
  fields at the end of a line. Order of birth and station are not asked:
  P2 is to read them from the dates and the relations. No reading uses the relatives yet, so the
  README and the store listing name them with P2, when the first does.
  Checked: `RelativesTest` (11 tests: every field's round trip person by
  person, older lists, malformed lines and lost branches, the walk, the
  branch deleted, the relations open, the labels in English and Russian
  order, a day before the new year in the year before, a person removed);
  the emulator in English and Russian (a mother with her years, her
  father under her, a household member, the save, a restart, the mother
  deleted with her father; a day and hour from the pickers); the release
  build (a tree entered, read back after a restart, no raw keys). Found
  and fixed in the build: the keyboard came back over the person's
  dialog when the relatives closed, covering Save.

### <a id="p2"></a>P2. The natal trigram and the spouses' and the dead's obstacles — planned 2026-10-11, built 2026-10-15 (SPEC §5.9.1)

- **Gap.** Left by T4 (block 13) for want of other people's signs
  ([year-of-life.md](sources/year-of-life.md)): the natal trigram (*skyes
  spar*), the mother's trigram in the year of the birth (Dorje p. 108,
  from the *Moonbeams* f. 12a; WB's ch. 22, vol. 1, pp. 259–350, unread),
  with the secret obstacle (*gsang keg*) and the natal trigram's readings;
  the spouses' shared trigram and their obstacles (*bza' shug*); the
  ancestors' tombs and the parents' and ancestors' deaths (*dur keg*);
  the household readings (p. 408) and pebbles (pp. 385–387).
- **Work.** Read ch. 22's natal trigram and ch. 24's passages on the
  scans; build each in the year of age (R1's section), from P1's
  relatives.
- **Block.** [29](#block-29); reads [RD13](#rd13), [RD14](#rd14); waits on [27](#block-27)
  (P1) and [28](#block-28) (R1).
- **Done when.** Vectors from Dorje's charts where they give them;
  `CatalogTest`; SPEC §5.9.1; both languages.
- **Built 2026-10-15** (block 29, SPEC §5.9.1): `Kin.kt` in the core
  (`Kin`, `KinReckoning`, `KinObstacle`) and `Reckonings.kin` from the
  person's tree. The natal trigram, a row among the year's signs reading
  WB's births on the eight trigrams (pp. 286–288), or a note asking for the
  mother's birth year; the obstacles with a relative, among the year's: the
  progressed sign on the wife's, husband's, father's or son's birth year
  and on a parent's death year (p. 388), the spouses' obstacle and the
  bridge's end (p. 390, the notes p. 492), the secret obstacle's first
  clause (p. 390), both spouses on the two-black (p. 409), each row naming
  the relative. Two families in the shape with no allowance (`NatalTrigram`,
  `KinObstacle`, item P2 in `CatalogTest`); the harsh years' note lost its
  sentence about the app (X10 173). Dorje gives no worked natal trigram
  (his mother's 34th year at li fits neither count, year-of-life.md), so
  the vectors are the Moonbeams' count and WB's circuits worked by hand,
  anchored on chart 6.2's man (`KinTest`, 5 tests; `ReckoningsTest`, the
  tree read as kin). Left out, each on its open question: the tomb
  obstacle (90), the secret obstacle's li and kham (91), the white year
  (92), the household's pebbles, piled trigrams and gathered mewa (96,
  158); the dead's obstacle, which WB rejects; the births by the natal
  trigram against the life force and the parents' trigrams at the birth
  (pp. 294, 326) go with the birth reckoning (R2…). Checked on the emulator
  in English and Russian (a man of 1976 with a mother of 1950, a wife of
  1988, a father dead in 1988 and a brother in the house: the lake trigram
  natal, four obstacles, their sheets; no relatives: the note); the
  sheets' gloss line no longer reads " · unlucky" for an entry without a
  gloss of its own; store shot 7 retaken in both languages with a mother
  entered; the release build checked.

### <a id="p3"></a>P3. The bride's departure — planned 2026-10-11, built 2026-10-26 (SPEC §5.9.3)

- **Gap.** WB's chapter 23 (vol. 1, pp. 351–379, img. 361–389, the
  marriage and road reckonings) has three parts: whether two people
  match, the reckoning for setting out on the road (*lam du 'jug pa'i
  rtsis*, from p. 365, img. 375), and the remedies. The second has
  seventeen parts by the etext: *kham yu* (counted for the man from
  Kṛttikā down, for the woman from Pūrvaphalgunī up, each by their own
  years), *byur thog*, the trigram, the sme ba, *'dom sbrags*, the year,
  the month, the date's animal, the hour, the weekday, the mansion, the
  earth lords, the direction, the sky dog's own reckoning and receiving
  the bride (*sna len*), among others. Not read on the scan. The app's
  election for a wedding (E1) weighs the day alone.
- **Work.** Read the second part on the scans, with the first, which
  frames it, into a topic file; with a spouse set (P1), the departure's
  verdict for the couple on the day page and in the election for the
  bride's works ("Choose a day", E2), its earth lords outside the
  weighing with their remedies, as D4 settled (T2.26). The first part, the match, is a reckoning
  for R.
- **Block.** [30](#block-30); reads [RD15](#rd15), with [RD3](#rd3)'s sky dog of
  brides; waits on [27](#block-27) (P1) and [25](#block-25) (T2.26, for
  its earth lords and the sky dog).
- **Built 2026-10-26.** `Departure` (core) reckons every check the couple's
  births make calculable (SPEC §5.9.3), by an engine and four writers of its
  readings in parallel; a row in Your day and a grid on the election for a
  wedding. Stated choices for the owner: the verdict by the month's and the
  day's findings, any bad one making the day bad; the sky dog not weighed, as
  D4 keeps the earth lords out (ch. 31 counts it among them). Checked:
  `DepartureTest` (Mipham's worked *kham yu*, the bad ages, the coupling dates,
  the notes' hours, the directions, a month of a couple's days worked by hand),
  `CatalogTest` over every case for 42 couples over twelve years,
  `:core:test` and `:app:testDebugUnitTest`; the row and its sheet on the
  emulator in English and Russian, and the election's grid for a wedding
  (a test couple whose bride's birth month makes the whole 9th month bad, as
  the month's check gives it). Doubts
  for a reader (the engine's report): Kṛttikā in p. 377's no-remedy meeting,
  the coupling year, the sky dog's food days in ch. 23 against ch. 31, «ར་བ་བཞི»,
  the ages 17–32, «ཕྱྭ་ལོན» among the directions.
- **Done when.** A vector of a couple's good and bad days for the
  departure; `ElectionTest`; SPEC §5.14, §10.8; both languages.

## Reckonings

### <a id="r"></a>R. The pebble divinations, a section of their own — planned 2026-10-11, R1 built 2026-10-15 (SPEC §10.11)

- **Why.** The owner (2026-10-11): WB's element reckoning (*'byung
  rtsis*) is a large body of its own and gets its own group in the menu,
  as Meditation has (SPEC §10.5, §10.9).
- **What WB holds** (chapters by the 1996 contents, printed pages): vol. 1,
  ch. 20, the framework of the element reckoning (pp. 218–251); ch. 21,
  the opening of the Chinese reckoning (pp. 252–258); ch. 22, the
  reckoning of lives, the birth (*tshe rabs las rtsis*, pp. 259–350);
  ch. 23, marriage and the road (pp. 351–379; the road is P3); ch. 24,
  obstacles (*keg rtsis*) and the Chinese pebbles (pp. 380–416; the year
  of age built, T4); ch. 25, illness (pp. 417–452; H2); ch. 26, the thirty
  starting points with their pebbles (from p. 453 to the volume's end);
  vol. 2, ch. 27, the thirteen Chinese *dpyad lding* and small reckonings
  (pp. 3–25); ch. 28, minor signs (pp. 26–43); ch. 29, *zangs kyam*, the
  reckoning for the dead (*gshin rtsis*, pp. 44–143, with the notes'
  section 4, pp. 499–522; not ch. 31, RD11); ch. 30, the reckoning of the
  moment, from dreams and the caller's signs, with small earth and pebble
  examinations (pp. 144–173). The notes (vol. 2, pp. 474–541) explain them
  in eight sections, mapped to the chapters in
  [reckonings.md](sources/reckonings.md) (RD11).
- **R1 The section and the inventory.** Read each chapter's outline on the
  scans (each opens with its starting points and its count of pebbles)
  into `docs/sources/reckonings.md`: what each reckons, from whose dates
  (the person's, a relative's, the moment's), what is a casting (a throw
  of chance, not built, as Mo is not, T4), and its size; the items after
  R1 are cut from it. A "Reckonings" group in the menu with one screen
  listing, for the person chosen, the reckonings built; the year of age
  moves into it from the year's balloon, which keeps a link to it (T2.26's
  Year tab holds the year's earth lords). Pebbles drawn as the charts
  draw them (○ ×). **Built 2026-10-15** (block 28, SPEC §10.5, §10.11):
  the inventory is RD11's and RD17's, read before the build; the menu's
  Reckonings group (closed on a fresh install, `menu_reckonings`) with
  the Element reckoning row; its list with the year of age, the only
  reckoning built; the year of age a view of its own, stepped by year
  from the birth year, no longer a sheet over the page, and the year's
  balloon opening it directly, Back returning to the page; the pebbles
  already ○ ×. With it, the people dialogs open over Health and the
  Reckonings (Health's way to People did nothing before). Checked:
  `ReckoningsTest` (the year stepped to reckons as the balloon's day
  does, 2025–2028), the emulator in English and Russian (menu, list,
  year before and after, Back both ways, the balloon, no one chosen and
  the dialog over both screens), the release build on both pages and in
  the sheets; store shots 5 and 7 retaken in both languages.
- **R2… One item per reckoning**, in R1's order, likely: the birth
  (ch. 22, with P2), the marriage match (ch. 23's first part, with P1),
  the reckoning for the dead (with P1), the thirty starting points
  (ch. 26), the *dpyad lding* (ch. 27), the moment (ch. 30, from the clock
  time of the question, if R1 finds it calculable). The illness reckoning
  (ch. 25) is built with H2 and listed here too.
- **R2a The birth, its first part — built 2026-10-19** (block 31, SPEC
  §5.9.2, §10.11): a "The birth" reckoning in the list, first, from the
  mother's birth year: the natal trigram; the five children by its
  element against the life force (p. 294), WB's worked count of the horse
  and snake years and of the five demons as `BirthTest`'s vectors; the
  readings of the parents' trigrams at the birth (p. 326) that the years,
  the child's sex and the father's trigram decide, nine of them. The
  readings in the shape (families `BirthChild`, `BirthOmen`, item R2 in
  `CatalogTest`), English and Russian. Left for the next part: the
  pebbles of the birth (pp. 259–262: the colours of p. 260 are checked on
  the scan only in part, and the readings past p. 262 not at all), the
  sixty years, the mewa of the birth, the line of births; the clan,
  asked once, for the special pebble. Checked: `:core:test`
  (`BirthTest`, `CatalogTest`), `:app:testDebugUnitTest`
  (`ReckoningsTest`: the birth from the parents' years), the emulator in
  English and Russian (the list, the view for a son with both parents, a
  daughter, no mother, no gender and no father; both sheets), the release
  build (the list, the view and both sheets, no raw keys). The store
  shots do not show the list: unchanged.
- **R2b The birth's special pebble — built 2026-10-19, taken out 2026-11-06** (block 31, then block [72](#block-72); the owner: only Tibetans have a clan): the clan, asked by its element in the person
  dialog and stored with the person; p. 261's table of the seven years
  for each clan; the end reckoning's seven readings (pp. 284–286, read
  that day on the Gser thang reprint at 2048 px, year-of-life.md) in the
  shape, English and Russian, family `ClanYear` (item R2). The four
  precious pebbles wait on open question 160 (the colour rule of p. 260).
  Checked: `BirthTest` (the table against the tomb rule and the elements'
  kinship), `PeopleTest` (the clan stored with and without a gender),
  `ReckoningsTest`, `CatalogTest`; the emulator in English and Russian
  (the dialog's chooser saving `JI`; the clan's row for keg, kungs and
  shang, with and without the mother's year; no clan), the release build.
- **R2c The year's own destiny — built 2026-10-19** (block 31, SPEC
  §5.9.2, §10.11): the birth year's kind by destiny against life force
  (pp. 261, 275–280, the five defining lines read on the scan), with the
  five general readings in the shape (family `PowerYear`, item R2),
  English and Russian, shown under "The birth year" whether or not the
  mother's year is known; with them the twenty-five particular readings,
  by the year's element, whose side of the family the kind falls on
  (`PowerYearOf`), the same day, and the four readings of the luck
  triads that follow them (pp. 280–281, `LuckTriad`), and the condensed
  readings of the first, middle and last years (p. 283, `YearPlace`).
  The cutting and tearing among one's own four aspects followed the same
  day (pp. 281–282, ten pairs, `BirthCut`), with its group cuts (p. 282,
  `GroupCut`), and the full readings of the first, middle and last
  (pp. 283–284, `YearPlaceFull`, its lines compared with the scan). The sixty years followed as R2d. Left besides: the pebble of the time; the four precious
  pebbles (open question 160). Checked: `BirthTest` (WB's particular years, twelve
  of each kind), `CatalogTest`, the emulator in English (a fire dragon,
  *khong nong*) and Russian (a wood tiger, *kha yan*, no mother), the
  release build.
- **R2d The birth year's nature — built 2026-10-19** (block 31, SPEC
  §5.9.2, §10.11): the twelve years in brief and the sixty in full
  (pp. 263–271), read on the 1996 print against the Gser thang reprint
  (both through Yigdzin-1, every difference read on the scan;
  year-of-life.md): "The year animal's nature" and "The sixty years'
  reading" first under "The birth year", families `YearNature` and
  `SixtyYear` (item R2) in the shape, English and Russian; the span of
  life, the obstacles and the children in the paragraph, the illnesses
  and spirits as rows. Left out as the topic file lists: the line's
  past, unidentified words, the deaths by one's own blade. Checked:
  `:core:test` (`CatalogTest` over all 72), `:app:testDebugUnitTest`,
  the emulator in English (a fire dragon, 1976) and Russian (a water
  snake, 2013): both rows and both sheets. No release build: no enum
  and no key by class name was added. The five bodies followed as
  R2e; left besides: the pebble of the time, the four precious
  pebbles (open question 160).
- **R2e The body — built 2026-10-19** (block 31, SPEC §5.9.2, §10.11):
  the five bodies in brief and the thirty paired-year elements
  (pp. 271–275), read as R2d (a line of the water horse the 1996 machine
  reading drops, restored from the reprint): "The year's body" and "Its
  pair among the thirty" after the sixty years, families `BodyNature`
  and `KhaSrog` (item R2), English and Russian; the mother's trigram at
  the birth in the paragraph where WB names one. Checked: `BirthTest`
  (the thirty bodies as WB names them are `Forces`' body elements of all
  sixty years), `CatalogTest`, `:app:testDebugUnitTest`, the emulator in
  English (1976, earth body, fire dragon and snake) and Russian (2013,
  water body, water dragon and snake). The births on each mewa followed
  as R2f.
- **R2f The natal mewa — built 2026-10-19** (block 31, SPEC §5.9.2,
  §10.11): the births under each of the nine mewa (pp. 288–294), read as
  R2d (the 1996 machine reading drops the five-yellow's «ལྔ»): "The natal
  mewa" after the thirty, family `BirthMewa` (item R2), English and
  Russian; the image to make for a better rebirth as the remedy. The
  trigrams' births before them (pp. 286–288) were already the natal
  trigram's readings. Checked: `CatalogTest`, `:app:testDebugUnitTest`,
  the emulator in English (1976, six-white) and Russian (2013,
  five-yellow). The five by the other aspects followed as R2g.
- **R2g The five by the other aspects — built 2026-10-19** (block 31,
  SPEC §5.9.2, §10.11): p. 294–295's same five for the body, destiny and
  the luck against the natal trigram, and for the mewa against the life
  force, read as R2d; family `BirthAspect` (item R2), English and
  Russian, a demon's reading by its element. Checked: `BirthTest`
  (p. 294's examples), `CatalogTest`, `:app:testDebugUnitTest`, the
  emulator in English and Russian (a fire dragon of 1976 with a mother
  of 1950: lake trigram, the body the children's glory, destiny a spirit,
  the luck a demon of iron; the mewa the children's glory). Glory and
  misfortune followed as R2h.
- **R2h Glory and misfortune — built 2026-10-19** (block 31, SPEC
  §5.9.2, §10.11): *dpal byur* (pp. 295–297) by the notes' rule (vol. 2,
  p. 476), read as R2d; family `DpalByur` (item R2), English and Russian,
  in a section of its own after the natal trigram, the father's two with
  his year. Checked: `BirthTest` (the notes' rule), `CatalogTest`,
  `:app:testDebugUnitTest`, the emulator in English and Russian (a fire
  dragon of 1976, mother 1950, father 1948: life taken, misfortune, life
  taken, own foot). The line of births followed as R2i.
- **R2i The line of births — built 2026-10-19** (block 31, SPEC §5.9.2,
  §10.11): the life force's downward count (pp. 297–299, the notes'
  rule), read as R2d, with the section's summary the 1996 machine reading
  drops; family `BirthLine` (item R2), English and Russian, in a section
  of its own for the father and the grandfather. Checked: `BirthTest`
  (WB's list, eight groups), `CatalogTest`, `:app:testDebugUnitTest`,
  the emulator in English (1976, father 1948 torn, grandfather 1918
  staying) and Russian (1986, father 1960 crowded). Left: the upward and
  across counts and the other aspects (24 pebbles), the instructions on
  crowding and staying from p. 299 on, the gods born with one (p. 305).
- **R2j The four of rise and decline — built 2026-10-19** (block 31,
  SPEC §5.9.2, §10.11): the 48 readings of the twelve stages for each
  aspect (pp. 331–333, read as R2d), counted onto the birth's year,
  month and day by the notes (vol. 2, p. 475, found by the parallel
  open-questions run); family `RiseDecline` (item R2), English and
  Russian. Checked: `CatalogTest`, `:app:testDebugUnitTest`, the
  emulator in English and Russian (a fire dragon of 1 June 1976: tomb,
  body complete, illness). Left: the luck's, which needs the hour of
  birth; the hundred pebbles' own readings (pp. 328–331).
- **R2k Raising the child — built 2026-10-19** (block 31, SPEC §5.9.2,
  §10.11): the birth month and the birth day (pp. 347–348, Yigdzin-1,
  each rule checked by WB's own example), families `RaisingMonth` and
  `RaisingDay` (item R2), English and Russian. Checked: `BirthTest`,
  `CatalogTest`, `:app:testDebugUnitTest`, the emulator in English (a
  wood mouse born 10 May 1984: the mother's month, a hard day) and
  Russian. Left: the year, the planet and the mansion (the passage's
  four accomplishing mansions disagree with Revatī's own verse), the
  hour.
- **R2l The four precious pebbles — built 2026-10-19** (block 31, SPEC
  §5.9.2, §10.11): with open question 160 answered the same day (WB's
  rule for an even count, vol. 2, p. 46: a speckled half), the king's,
  minister's and soldier's pebbles of the birth's year, month and day
  (p. 262, read on the reprint), family `PreciousPebble` (item R2),
  English and Russian. Checked: `BirthTest` (the key-pebble table),
  `CatalogTest`, `:app:testDebugUnitTest`, the emulator in English and
  Russian (a fire dragon: black, white, white) and a dog year's speckled
  line. Left: the weapon's, which needs the hour.
- **R2m The hundred pebbles — built 2026-10-22** (block 31, SPEC §5.9.2,
  §10.11): open question 184 answered first, the board found in Kun dga'
  rig 'dzin's worked sheet (KD vol. 3, pp. 716–718, read on the lent
  scan; [sources/claims/birth-hundred-board.md](sources/claims/birth-hundred-board.md)):
  the five unchanging lucks (p. 261) against the four aspects' own,
  mother, son, friend and enemy, each cell by the notes' colour rule
  (vol. 2, p. 475), the twenty deciding pebbles with WB's forty readings
  (pp. 329–331) and its four remedies; `Hundred`, family `HundredPebble`
  (item R2), English and Russian; the board drawn ○ × as the sheet lays
  it. Checked: `BirthTest` (the sheet's hundred cells and twenty deciding
  pebbles, every one), `CatalogTest`, `:app:testDebugUnitTest`, the
  emulator in English and Russian (a son of the wood mouse year 1984 born
  in the water monkey month, mother of 1958, father of 1955: the sheet's
  own birth, the board and the rows as drawn; a reading sheet). Not
  built: the general verdict by the board's pattern (p. 331), which R4
  takes up; the pebble of the time.
- **The release build** was checked over R2d–R3c on 2026-10-19 (the
  enums they add key the catalog): the birth and match views in English
  with a mother, father, grandfather and spouse entered, no raw keys;
  and again over R3d–R3l the same day: the whole match view in English
  and Russian with both spouses' mothers and the man's father entered,
  53 rows, no raw keys, three rows opened.
- **R3a The marriage match, its first points — built 2026-10-19**
  (block 31, SPEC §5.9.3, §10.11): "The marriage match" in the
  Reckonings list, from the person's gender and a spouse's birth year;
  the single match of the four aspects with the wife of karma, the two
  destinies together and *ni sha* (pp. 355–357, 359, read on the scan
  in RD15/O10, marriage.md), families `MatchAspect`, `MatchDestiny`,
  `MatchNiSha` (item R3), English and Russian. The naming check now
  takes a Wylie that begins with a capital retroflex as written (ཎི་ཤ,
  Ni sha). Checked: `BirthTest` (the single's meetings, *ni sha*),
  `CatalogTest`, `ReckoningsTest` (the man and the woman told apart
  whichever the person is), the emulator in English (a fire dragon man
  and an earth sheep woman: all four agree, not reversed) and Russian (a
  woman of 1979 and a man of 1983: reversed). The years followed as R3b.
- **R3b The marriage match's years — built 2026-10-19** (block 31,
  SPEC §5.9.3, §10.11): point 1's kinds not joined and point 2's named
  bad years (pp. 351–354, read on the scan in RD15/O10), families
  `BadYear` and `MatchSameKind` (item R3), English and Russian, under
  "The years" in the match. Checked: `BirthTest` (the lists, a fire
  horse's three, two fire dragon and dog spouses of one kind),
  `CatalogTest`, `:app:testDebugUnitTest`, the emulator in English (a
  fire dragon and an earth sheep: four bad years) and Russian (a fire
  horse and a fire snake: both *kha yan*). Left: the year animals'
  pairs (several lists that disagree), the
  hidden and the deeper hidden.
- **R3c The same planet of birth — built 2026-10-19** (block 31, SPEC
  §5.9.3, §10.11): point 10's spouses born on one weekday (p. 360),
  family `MatchPlanet` (item R3), English and Russian, from both birth
  dates (the spouse's date entered, not only the year). Checked:
  `CatalogTest`, `:app:testDebugUnitTest`, the emulator in English and
  Russian (both born on a Tuesday). Not built from point 4 (the hidden):
  its wheels are read, but which mewa stands for each aspect
  («ཡར་བཞི་སྲོག་ལ་མར་བཞི་དབང་། །གང་སྐྱེས་ལུས་སྨེ་མཐུན་གསུམ་ཀླུང་») is not settled, and the body-cut wheel
  names body parts rather than pairs (marriage.md).
- **R3d The same animal and the same element — built 2026-10-19** (block
  31, SPEC §5.9.3, §10.11): point 1's spouses who meet within one year
  animal and within one element by the sexes of their years (p. 353),
  families `MatchSameAnimal` and `MatchSameElement` (item R3), English and
  Russian. Checked: `BirthTest`, `CatalogTest`, `:app:testDebugUnitTest`,
  the emulator in English (fire dragon and fire snake) and Russian (fire
  and earth dragon). Not built: the monkey's line and water female's and
  wood male's (their words not settled), and the pairs of different
  animals (several lists that disagree, marriage.md).
- **R3e The pairs of year animals — built 2026-10-19** (block 31, SPEC
  §5.9.3, §10.11): point 1's pairs of different year animals (pp.
  352–353), nine named lists (`PairList`) and forty-four pairs read one
  by one (`PairLine`), families `MatchPairList` and `MatchPairLine` (item
  R3), English and Russian; pp. 351–353 read in the Gser thang reprint
  (I3CN5028 img. 346–348) beside the 1996 print. Checked: `BirthTest`,
  `CatalogTest`, `:app:testDebugUnitTest`, the emulator in English (fire
  dragon and iron monkey) and Russian (fire dragon and earth sheep). Not
  built (marriage.md): the first line's triads beyond the mouse, dragon
  and monkey, the "line ends" list and the list before the parting
  pairs, whose pairing or words are not settled, the seven-corners and
  the four executioners of the mouse and pig.
- **R3f The birth mewas — built 2026-10-19** (block 31, SPEC §5.9.3,
  §10.11): point 12's meetings of the two birth mewas (p. 363), family
  `MatchMewa` (item R3), English and Russian, in a section of its own.
  Checked: `BirthTest`, `CatalogTest`, `:app:testDebugUnitTest`, the
  emulator in English (mewas 6 and 2, mother and son) and Russian (6 and
  5); the Russian mewa subtitles, here and in the birth reckoning, now
  read «6-я мева, белый цвет». Not built: the four aspects' mewas (open
  question 161) and the white and black shares, which need them.
- **R3g The natal trigrams — built 2026-10-19** (block 31, SPEC §5.9.3,
  §10.11): point 11's meetings of the two natal trigrams (pp. 361–362),
  each from the mother's birth year (the spouse's mother entered under
  the spouse), `MatchTrigramKind`, family `MatchTrigram` (item R3),
  English and Russian. Checked: `BirthTest` (every pair in one kind but
  ཁམ་ཟོན), `CatalogTest`, `:app:testDebugUnitTest`, the emulator in English
  (lake and sky, sustaining life) and Russian (lake and wind, the body
  cut) and without the spouse's mother. Not built: the bad pairs' rites,
  the graves, the four born on their own trigram, the eight
  hand-wavers, the woman's lines and the children by trigram pair.
- **R3h The children by trigram and the hand-wavers — built 2026-10-19**
  (block 31, SPEC §5.9.3, §10.11): point 11's children for eleven pairs
  of natal trigrams (p. 363) and the eight hand-wavers (p. 362), families
  `MatchTrigramChildren` and `MatchHandWaver` (item R3), English and
  Russian. Checked: `BirthTest`, `CatalogTest`, `:app:testDebugUnitTest`,
  the emulator in English and Russian (lake and earth: eight or nine
  children; an iron-monkey woman on earth, a hand-waver). Not built: the
  bad pairs' rites, the graves, the four born on their own trigram, the
  woman's lines.
- **R3i Rise and decline — built 2026-10-19** (block 31, SPEC §5.9.3,
  §10.11): point 13's root rise and decline, each life force on the
  other's year (p. 364), with the twelve stages' results and grades
  (p. 363), family `MatchRiseDecline` (item R3), English and Russian.
  Checked: `BirthTest`, `CatalogTest`, `:app:testDebugUnitTest`, the
  emulator in English and Russian (fire dragon and iron monkey: birth
  stage and the body complete). Not built: the aspects' rise and decline,
  the other way, the throne doors.
- **R3j The bad trigram pairs' rites — built 2026-10-19** (block 31, SPEC
  §5.9.3): the sixteen bad pairs' rites (pp. 361–362) as Remedy rows of
  `MatchTrigram`, English and Russian. Checked: `CatalogTest`, the
  emulator in English and Russian (lake and wind). Left out of the rites,
  the words not settled: marriage.md.
- **R3k From the parents' years — built 2026-10-19** (block 31, SPEC
  §5.9.3, §10.11): points 8–9's outer and secret rise and decline from
  each spouse's father and mother (pp. 359–360, the notes p. 524), family
  `MatchParentStage` (item R3), English and Russian. Checked: `BirthTest`
  (the notes' tiger and hare), `CatalogTest`, `:app:testDebugUnitTest`,
  the emulator in English and Russian (an iron-tiger father and mother on
  a fire dragon). Not built: the clans, the inner way, the grandfathers
  and the uncles.
- **R3l The bad years by mewa path — built 2026-10-19** (block 31, SPEC
  §5.9.3): point 2's years of five and of two elements in each mewa path
  (p. 354), family `MewaPathYear` (item R3), English and Russian; the
  five-element list matches the five forces' count in all three paths.
  Checked: `BirthTest`, `CatalogTest`, `:app:testDebugUnitTest`, the
  emulator in English and Russian (a water-sheep woman of the lower
  path).
- **R3m Three births on a trigram — built 2026-10-19** (block 31, SPEC
  §5.9.3): p. 362's wood tiger or hare woman on earth, iron bird or
  monkey husband on mountain, and sky woman with wind man, family
  `MatchTrigramLine` with their rites (item R3), English and Russian;
  p. 362 read again in the Gser thang reprint (I3CN5028 img. 357).
  Checked: `BirthTest`, `CatalogTest`, `:app:testDebugUnitTest`, the
  emulator in English and Russian (sky and wind). Not built: the four on
  their own trigram (both prints «མོ་རྟ»), the other three lines, the graves.
- **R3n The hidden and the deeper hidden — built 2026-10-22** (block 31,
  SPEC §5.9.3, §10.11): points 4 and 5 (pp. 357–359) with O16's answer to
  open question 161: each aspect's mewa by ch. 21's rule (p. 255, the
  luck's by WB's own fire hare, open question 234 for the *Moonbeams*'
  other way), set on the nine wheels as the notes draw them (p. 524;
  `HiddenWheel`, sustaining life with its water–wind pair the natal
  trigrams' list leaves out), the woman's mewas drawn for the deeper
  hidden; families `MatchHidden` and `MatchDeepHidden` (item R3), English
  and Russian, the bad wheels' rites as Remedy rows where WB's words are
  read (the five demons', the demon's cut's and the body cut's obscure
  rite names left out, marriage.md). Checked: `BirthTest` (WB's fire hare
  and the *Moonbeams*' wood rat, every pair of trigrams on one wheel, a
  worked couple), `CatalogTest`, `:app:testDebugUnitTest`, the emulator in
  English (a fire dragon man and an earth sheep woman) and Russian (a
  water pig man and an earth sheep woman, the person a woman), the
  release build. Not built: the hidden's progressed signs fighting.
- **R3o The siblings' number — built 2026-10-22** (block 31, SPEC
  §5.9.3): point 7 (p. 359; the notes, p. 525; RD21, open question 166),
  each spouse's five counts summed, the larger wins and equal is good,
  the sums together even agree; family `MatchSiblings` (item R3), English
  and Russian. Checked as R3n (14 against 15, disagreeing; 18 against 15).
- **R3p The couple's bad-year pebble — built 2026-10-22** (block 31, SPEC
  §5.9.3): the notes' one deciding pebble over the bad years (pp.
  523–524; RD21), from the bad-year and mewa-path rows each spouse has;
  family `BadYearPebble` (item R3). Checked as R3n (both: black).
- **R3q The kinds of wife — built 2026-10-22** (block 31, SPEC §5.9.3):
  each aspect's kind, of karma, kin or enmity (p. 355; *Removing the
  Tarnish*, reply 113; the notes, p. 524; RD21), one row per kind with
  its aspects where the wife of karma does not hold; family `SpouseKind`
  (item R3). Checked as R3n. The whole "in general" and the karma
  reckoning over the match are R4's ([58](#block-58)).
- **R2n The dead's time of death — built 2026-10-22** (SPEC §5.9.4,
  §10.11): the first of ch. 29's fifteen examinations, its four deciding
  pebbles read on the scan (vol. 2, pp. 55–57, the Gser thang reprint;
  [sources/corpus/wb/ch29.md](sources/corpus/wb/ch29.md)), counted from
  eight of WB's ten roots (pp. 45–46; the notes, p. 500: the planet and
  mansion, fought by element, left out, WB's weekday elements being the
  Indian four), for each relative entered as dead with a birth year, a
  gender and a whole date of death, the hour of death where its time is
  known; the whole of pp. 56–57 on the four. `DeadReckoning`, families
  `DeadPebble` and `DeadWhole` (item R2), English and Russian; a "The
  dead" view in the Reckonings list. Checked: `DeadTest` (KD vol. 3,
  p. 729's dead man: his four drawn deciding pebbles from the sheet's
  four places; on WB's eight, destiny speckled), `ReckoningsTest` (the
  relative's dates, the 04:30 death on the day before), `CatalogTest`,
  the emulator in English and Russian (the sheet's dead man as a father,
  a sister without a full date of death), the release build. Parts 6
  and 7 (the mewa's small obstacles and the course of the years, p. 56)
  added the same day, families `DeadMewa` and `DeadCourse`. Not built:
  the element's pebble and funeral share, part 5 (the trigram's test),
  the other fourteen examinations, the living household.
- **R2p The reason the death met — built 2026-10-25** (SPEC §5.9.4,
  §10.11): ch. 29's second examination (vol. 2, pp. 57–58, read on the
  scan; [sources/corpus/wb/ch29.md](sources/corpus/wb/ch29.md)) as far as
  it is settled: the outer reason by each aspect's deciding pebble white
  or black (the notes, p. 503; the regent's reply 148), the elements' cut
  met most often among the roots' fights where a pebble is black, and the
  demon's cut of the la in the seventh month of the year's, month's,
  day's and hour's animal. `DeadReckoning.cuts`, `times`, `cutMonth`,
  `DeadCut`, `DeadTime`, families `DeadCause`, `DeadCut`, `DeadCauseTime`
  (item R2), English and Russian. Checked: `DeadTest` (the seventh months;
  KD's dead man of the fire horse: the year pig, the hour sheep, three
  times without the hour; the cuts the most-met fights), `CatalogTest`,
  `:app:testDebugUnitTest`, the emulator in English and Russian (a father
  of 1906 dead on 1995-10-27 at 14:00: three reasons, fire cut by water,
  the four times), the release build. Not built: the reasons by the trigram and the
  mewa (open questions 260, 272), two waters within («ཁང་ནག་བཤུས» not
  settled).
- **R2q Which spirit carried the dead off — built 2026-10-25** (SPEC
  §5.9.4, §10.11): ch. 29's third examination (vol. 2, pp. 58–68, read on
  the scan; [sources/corpus/wb/ch29-demons.md](sources/corpus/wb/ch29-demons.md))
  by the dead's year animal, the day's animal (the six p. 63 names), the
  weekday's general entry and the hour of death, each rite a Remedy row;
  `DeadReckoning.weekday`, `demonDay`, families `DeadDemonYear`,
  `DeadDemonDay`, `DeadDemonWeekday`, `DeadDemonHour` (item R2), English
  and Russian. Checked: `DeadTest` (the weekday and the named day on KD's
  dead man), `CatalogTest`, `:app:testDebugUnitTest`, the emulator in
  English and Russian (the same father: born in the horse year, a Friday,
  Anurādhā, the sheep hour), the release build. Not built: by the
  trigram and the mewa (open question 260), by the weekday's planet's
  state (how it is found not said). **R2r, the mansion of death, built the
  same day**: the 27 mansions' entries (pp. 65–66; Abhijit's not reached,
  the calendar counting 27), `DeadReckoning.mansion`, family
  `DeadDemonMansion`, English and Russian; checked as R2q.
- **R2s Where the dead is reborn, and help by virtue — built 2026-10-25**
  (SPEC §5.9.4): the fifth examination by the day's animal and the hour
  of death, the sixth by the year of death (vol. 2, pp. 70–72, read on
  the scan; [sources/corpus/wb/ch29-funeral.md](sources/corpus/wb/ch29-funeral.md)),
  `DeadReckoning.rebirthByHour`, `DeadRebirth`, families
  `DeadRebirthDay`, `DeadRebirthHour`, `DeadVirtue` (item R2), English and
  Russian. Checked: `DeadTest` (two hours to each realm), `CatalogTest`,
  `:app:testDebugUnitTest`, the emulator in English and Russian (the sheep
  hour, a hungry ghost; the pig year's texts), the release build. Not built: the rebirth's deciding pebble (the
  element's pebble, also the fourth examination's funeral share, is not
  yet reckoned), by the mewa (open question 272), by the aspects and the
  stage (whose not said).
- **R2t The dead's trigram and mewa — built 2026-10-25** (SPEC §5.9.4,
  D18): the second examination's inner reason by the trigram and middle
  reason by the mewa (p. 58), the third's spirit by the trigram and by the
  mewa (pp. 60–63), the fifth's rebirth by the mewa with the image that
  turns it (p. 71), each on the dead's trigram and mewa of the year of age
  at the year of death; `DeadReckoning.trigram`, `mewa`, families
  `DeadCauseTrigram`, `DeadCauseMewa`, `DeadDemonTrigram`,
  `DeadDemonMewa`, `DeadRebirthMewa` (item R2), English and Russian; the
  words the text leaves unread («སྐྱུར», «ལྷོག», the bracketed ones) left in
  the topic files. Checked: `DeadTest`, `CatalogTest`,
  `:app:testDebugUnitTest`, the emulator in English and Russian (the 1906
  father: earth trigram, three blue). No release build: no enum added.
- **R3c Building a house this year — built 2026-10-25** (SPEC §5.9.1):
  ch. 28's doors of sky and earth (vol. 2, p. 42) for the year and the
  owner's progressed sign, mewa and trigram, a section of the year of
  age; `Building`, family `Building` (item R2), English and Russian.
  Checked: `YearOfLifeTest`, `CatalogTest`, `:app:testDebugUnitTest`, the
  emulator in English and Russian (2024, a wood dragon year: the year on
  the sky door, the mewa on the earth door). Ch. 28's other
  parts read observed signs (weather, sneezes, birds, the moon on the
  hand) or wait on readings (the 360 starting points' starts).
- **R2y The golden turtle — built 2026-10-25** (SPEC §5.9.6): ch. 27's
  classes of years (pp. 20–22), the birth year's class with its pebbles,
  reading and rites atop the circles; `TurtleClass`, family `TurtleClass`
  (item R2), English and Russian. Checked: `CirclesTest` (the fourteen
  classes' counts over the sixty years equal the text's, god 2 … ogre 9,
  the four earth executioners' years the turtle's four), `CatalogTest`,
  `:app:testDebugUnitTest`, the emulator in English and Russian (a fire
  dragon: an asura year), the release build (no raw keys). The household's
  agreement followed the same day: a parent or child of the same class
  among the five and the executioner pairs, with each living relative
  (families `TurtleKin`, `TurtleEnemy`); not built: the relatives'
  agreement among themselves.
- **R2aa The old dead's kinds and what cuts the father by the clan —
  built 2026-11-06, the clan's cut taken out the same day (block [72](#block-72))** (block 66; SPEC §5.9.4): `DeadOldKind`, the
  kinds of pp. 77–79 by sex and year of age; `DeadReckoning.oldRound` the
  ages they leave, read by p. 79's other round (q309), its three cases
  listed since neither companion nor son is recorded; `ClanCut`, a birth
  in a month or year of the element that overcomes the clan's (p. 349,
  Mipham p. 489, p. 300; q566). Families `DeadOldKind`, `DeadOldRound`,
  `ClanCut`, English and Russian. Stated choices for the owner: a woman of
  70–90 but 72 keeps the general unguarded old one where q309 gives the
  other round; the kinds' English names are literal renderings of the
  Wylie; month and year read apart, each cutting the father. Checked:
  `DeadTest`, `BirthTest`, `CatalogTest`, the emulator in English and
  Russian, the release build (no raw keys). Not built: the clans' strikes
  of p. 114 (relatives carry no clan, the owner, 2026-10-15), the kinds tied to a condition (*srid sman*,
  *mche ser ma*). The departure's gold and turquoise treasuries and the
  bride's *ko long* stay out: q605 and q643 name them, but no text gives a
  day or seat they fall on (claim departure-singled-out-lords-by-time).
- **R2ae Who does not handle the corpse — built 2026-11-06** (block 71):
  p. 93, families `DeadHandlerMewa`, `DeadHandlerTrigram`. Which "black
  pebble" the trigram's lines mean is not said, so their condition stands in
  the reading. Not built: the rounds of rise and decline (WB: names only; the
  true count is the commentary's), the birth trigram's test (the mother's
  age).
- **R2ad The death's eye, in part — built 2026-11-06** (block 70): p. 94's
  eight places, `eyeLooks`, `Reckonings.deathEye`, families `DeadEye`,
  `DeadEyeLooks`. Not built: its thirteen root pebbles and verdict (the
  clan), the four executioners upward and five in accord downward and the
  same trigram seat (the verse fixes no count or year), the joined hands,
  the venomous year roots.
- **R2ac The mewa's copper vessel — built 2026-11-06** (block 69; SPEC
  §5.9.10): `HouseholdRound.vesselReads`, family `MewaVessel`, in the
  household round, its remedies in the Together's. Stated choices: "many" is
  half of the members or more, and the household stands for WB's paternal
  kin. Words unsettled on the scan are left out (the corpse's kind in the
  yellow line, the blue's ox rites and *lhog pa*). Not built: the same pages'
  years counted together (cut-off, power, mother, friend), which need the
  clan.
- **R2ab Who is not made an old one — built 2026-11-06** (block 68): p. 82's
  two-black born and dead, `notMadeOld`, family `DeadOldNotMade`. A two-black
  birth year falls on the four named animals only, so the verse's animals
  add nothing to the mewa. Not built: the line's *kham* and *gin* (the birth
  trigram goes by the mother's age), the way of dying (not recorded).
- **R2x The dead's provisions, strikes and rites — built 2026-10-25**
  (SPEC §5.9.4, D18): the ninth and fifteenth examinations' provisions by
  the trigram of death, the tenth's nine-eye and grave-day strikes, the
  thirteenth's rites by trigram, mewa, planet and lunar date;
  `DeadReckoning.lunarDate`, `monthNumber`, `nineEye`, `graveDay`, eight
  families (item R2), English and Russian, the texts written by a
  subagent from the claims. Checked: `DeadTest` (the grave day by the
  Chinese seasons, no nine-eye at 90), `CatalogTest`,
  `:app:testDebugUnitTest`, the emulator in English and Russian (the 1906
  father: earth trigram, mewa 3, Venus's day, the 4th). No release build:
  no enum added. Not built: the mansions' portents (gaps in their
  table; the old dead's kinds built 2026-11-06, block 66), the strikes that need the
  household, the funeral share and feast, the remains'
  roads and earth lords.
- **R2w The moment's later reckonings — built 2026-10-25** (SPEC §5.9.5,
  D24): wealth, illness and harming spirit by the moment's element, the
  hour's (pp. 168–170), and the moment's mewa as a spirit claiming its
  share (pp. 164–165); families `MomentWealth`, `MomentSpirit` (item R2),
  English and Russian. Checked: `CatalogTest`, `:app:testDebugUnitTest`,
  the emulator in English and Russian with the clock set to day (water
  hour, nine red). No release build: no enum added. The house, field and
  grave, the three mewa joined and the mewa's places followed as R2z.
- **R2z The moment, built whole — 2026-10-27** (SPEC §5.9.5): every part
  of ch. 30 that can be reckoned from the moment and the person chosen as
  the asker (vol. 2, pp. 162–172, read on the scan, with Mipham's notes,
  pp. 535–536, whose worked examples are `MomentTest`'s vectors: the iron
  snake moment's aspects and own place, the three mewa with the day's 4
  green and the year's 8 white, 5 yellow's 7 red at the forefathers' and 2
  black at the father's, the third gate's start from a wood rat and a wood
  ox; and WB's wood moment's houses and sick bed). Built in two passes the
  same day (6c1a8f7, then this): the moment's mewa as a place, a shape and
  food; the kin's places on the figure; the moment's sign, the hour's
  animal with the hour's power, fighting the asker like with like for the
  house, the field and the grave, with whether there is a field, the
  grave's luck and its back; kin and enemy at one's own place and the six
  people's directions; the small examinations (wealth there, the demon,
  beer there, given, drunk and its taste, the neighbouring houses, the
  sick one's life, where the illness came from, bed, food eaten and how
  often, the organs where it sits, hair, trade, disputes); the three mewa
  joined with each square's kin, gods or demons and eye at the door; the
  thirteen gates and the thirty-three counts after them. `MomentPlaces`,
  `MomentSigns`, `MomentGates`; 30 families (`MomentMewaPlace` …
  `MomentDispute`, item R2), English and Russian, written by eight writers
  in parallel. Stated choices for the owner to overrule (questions 420,
  421, 422, 423): the year's mewa fights the squares around the day's of
  the sixty-day count; earth in the centre of one's own place; the gates'
  «ལྔ་ནས» read as no count, inclusive counts and the birth year's sex as
  D22; the small examinations' combinations. Corrected in the corpus: p.
  166's third house reading is the life force generating the moment, as
  the scan reads. Checked: `MomentTest`
  (14, the notes' and WB's examples), `CatalogTest` and all core tests
  (345), `:app:testDebugUnitTest` (72), the emulator in English and
  Russian (Lhasa in the monkey and bird hours against a fire dragon man:
  every section, every new family's sheet opened, by night the line
  instead of the asker's parts), the release build in both languages (no
  raw keys). Not on the phone (not connected). A third pass the same day added the
  parts of life on the figure for the person and the relatives entered
  (the notes' fire father and grandfather as `MomentTest`'s vector), the
  forefathers' year, the casting's grain and the marks for iron and water
  (`MomentKin`; families `MomentLifePart`, `MomentForefathersYear`,
  `MomentGrain`, `MomentGrainVessel`, `MomentMarks`), checked in core and
  app tests and on the emulator with a father, grandfather, mother,
  maternal uncle and daughter entered; the corpus's "freckle" on p. 170
  corrected to harelip («ཤོ་རེ», the dictionaries and WB ch. 23). Then, the
  owner's word the same day ("read and build them"): RD59–RD61 read pp.
  144–151 and p. 170's posture on the scan, and the observed signs were
  built as "Signs to watch", 242 signs in fifteen groups written by six
  writers in parallel (`ObservedSigns`, family `MomentSign`), with the
  fighting enemies of p. 172 from RD58 (`MomentGates.fight`, `MomentFight`;
  stated choices on questions 424, 425); checked in core and app tests and
  on the emulator in both languages. **Closed by the owner, 2026-10-27**:
  the moment is complete as built; the words and signs the print leaves
  unsettled (the marks for wood and fire, q346; «འཇག», «གྱེར་པོ», «སྐེས» and
  the words the readers marked not understood) stay in the passages' notes
  and questions until a reader of the tradition settles them, and are not
  part of R2z; the stated choices of questions 420–425 kept as built.
- **R2v The thirty starting points — built 2026-10-25** (SPEC §5.9.7,
  D21–D23): ch. 26 (vol. 1, pp. 453–473, RD37 on the scan; RD44, RD47),
  each point from its pair in the element related to the power, counted
  down from the male year or up from the female by the birth year's sex,
  inclusively, eight pebbles as the point's eight checks, the board read
  whole; `Thirty`, `ThirtyPoint`, `ThirtyPebble`, `ThirtyBoard`,
  families `ThirtyCheck` and `ThirtyBoard` (item R2), English and Russian;
  "The thirty starting points" in the Reckonings list. Points 21 and
  23–25 (a dead person's year) not shown in the person's view. Checked:
  `ThirtyTest` (the pairs of p. 453, the count by the birth year's sex
  both ways, the eight places, the board's groups and patterns),
  `CatalogTest` (480 check readings, 170 remedies, the board's twelve,
  written by a subagent from the claims, the unsettled words left out),
  `:app:testDebugUnitTest`, the emulator in English and Russian (a fire
  dragon in his 51st year: the lords' point from the fire mouse to the
  fire dog, upper ○×○, lower ×××, middle ××), the release build (no raw
  keys, the board's titles included). The four points for the dead
  followed the same day in the dead's view (`Thirty.forTheDead`, the
  dead's year and age at death), checked on the emulator in English and
  Russian (the 1906 father: the great deer, the red corpse, the pale
  wooden box, the nine stacked skulls).
- **R2u The thirteen circles — built 2026-10-25** (SPEC §5.9.6, D19,
  D20): ch. 27's circles (vol. 2, pp. 3–21, RD38 on the scan; RD44, RD47
  for whose element, the count and the servant's start), from the birth
  year's power, counted upward inclusively, four pebbles each by p. 3's
  colours; `Circles`, `Circle`, `CircleReading`, family `CirclePebble`
  (item R2), English and Russian; "The thirteen circles" in the
  Reckonings list. Checked: `CirclesTest` (Zhechen Gyaltsab's wood-monkey
  king from the iron bird, life force meeting itself, body the son, power
  the enemy; the four nails equal to reply 140's fixed years seen from
  wood), `CatalogTest`, `:app:testDebugUnitTest`, the emulator in English
  and Russian (a fire dragon in his 51st year: 29 white and 23 black pebbles, the
  king's from the water bird to the water pig; the sheet with each
  pebble's reading and remedy), the release build (no raw keys). The
  readings leave out what the claims mark unsettled or leave unnamed (the
  nine-eye condition, «…» quoted words, Wylie-only titles without their
  script), and name the sri spirits and other terms anew where the
  catalog had none (the grave spirits, the spirits of the prime, of
  enmity, of losses, of small children, the mother's, the horse spirits).
  Not built: the mewa pebble (D20), the small reckonings, the meadow's
  ornament, Sprugtön's.
- **R2o The moment's trigram and mewa — built 2026-10-22** (SPEC §5.9.5,
  §10.11): ch. 30's clock part, the date's trigram and mewa by the notes'
  months (O16, question 164), run through day and night by WB's rough
  spans (p. 162), with the readings by the moment's trigram and its mewa
  as person, wealth and spirit read on the scan (vol. 2, pp. 161–162,
  [sources/corpus/wb/ch30.md](sources/corpus/wb/ch30.md));
  `MomentReckoning`, families `MomentTrigram` and `MomentMewa` (item R2),
  English and Russian; a "The moment" view in the Reckonings list.
  Checked: `MomentTest` (the months' starts follow from one a date; the
  day's spans), `ReckoningsTest` (the day turning at dawn),
  `CatalogTest`, the emulator in English and Russian (22:30 at Kyoto on
  the 11th of the 9th month: khon and 9, by hand), the release build. The readings by the
  time of day (pp. 156–157, read on the scan) added the same day, family
  `MomentPeriod`. The readings by the
  hour's element (pp. 157–161) added the same day, family
  `MomentElement`. Not built: the asker's year, the small reckonings.
- **R4 Combining the readings — planned 2026-10-20** (block
  [58](#block-58); [sources/verdicts.md](sources/verdicts.md)). The
  owner (2026-10-20): the reckonings show each section with its pebbles
  and reading, one by one; they must also be combined and general
  conclusions drawn, for the birth, the marriage match and the others.
  WB gives no single score for a reckoning, but a ladder every reckoning
  climbs part of: the colour of a pebble; the verdict (ཁ་དམར, kha dmar)
  of an aspect, counted, the more winning, a tie speckled; the verdicts
  read as a set (all four alike; the birth's four mothers, sons and
  friends; the dead's thirteenth point over the twelve); the set placed
  in a life (whose aspect matters most, which part of life a sign rules,
  each aspect's domain); and the whole: the birth by "the good results
  greater and more" (vol. 1, p. 349) and its twenty-one kinds of child
  (pp. 338–342), the match by the spouse of karma, kin or enmity and the
  notes' "good and bad in general" (vol. 2, p. 524), the illness by the
  element that is most (p. 435), each with its remedies. Most of it is
  found in the etext only: RD18–RD28 read it on the scan. **To build**,
  cut to what the readings confirm: a *Together* part atop each
  reckoning, with each aspect's verdict, the set readings that hold,
  whose aspect and which part of life, WB's rule for the whole with its
  reasons named, and the remedies gathered as one *Remedy* row (Y1); no
  number WB does not count; the sections below unchanged. First the
  birth (RD19) and the match (RD21), then the year of age (RD20), the
  others as they are built (H2 takes the illness's, RD22). **Done when**
  as R2… above. **The match's Together built 2026-10-22**
  (block 58, SPEC §5.9.3): a "Together" section atop the match, its kinds
  of wife read whole (all four of karma, kin or enmity, or mixed, weighed
  in general, no count), the bad karma reckoning's overruling stated, and
  the Remedy rows of every bad or mixed point below gathered into one;
  family `MatchTogether` (item R3), English and Russian. Checked:
  `BirthTest`, `CatalogTest`, `:app:testDebugUnitTest`, the emulator in
  English (a fire dragon man and an earth sheep woman, all of karma, the
  gathered remedies) and Russian (a water pig man and an earth sheep
  woman, mixed), the release build (no raw keys). **The birth's Together built
  2026-10-22** (block 58, SPEC §5.9.2), after open questions 184 and 185
  ([claims/birth-hundred-board.md](sources/claims/birth-hundred-board.md),
  [claims/birth-children-no-count.md](sources/claims/birth-children-no-count.md))
  and R2m: a "Together" section atop the birth, the hundred's twenty
  deciding pebbles weighed by p. 349's rule (good, bad or even by the
  good pebbles' number) with the bad pebbles' and bad sets' remedies
  gathered, the sets of p. 338 that hold (the four mothers, many white
  or black, the four sons and friends, own against enemy for the life
  force, destiny and luck; rising read as the pebble white by the notes'
  gloss of the five doors, p. 484, and Lha mo tshul khrims's
  restatement), and how the board is read whole (p. 331) as a reading
  beside the drawn board; families `BirthTogether`, `BirthSet`,
  `BirthBoard` (item R2), English and Russian. Checked: `BirthTest` (the
  sheet's birth: mothers two and two, many black, sons mixed, friends
  black, 8 good of 20, weighs bad), `CatalogTest`, `:app:testDebugUnitTest`,
  the emulator in English and Russian (the sheet's birth: the section,
  the gathered remedies, the board's reading). Not built: the twenty-one
  children (each by conditions together, several the mother's obstacle
  reckoning at the birth or observations; no text names one on part of
  them), the board's patterns counted,
  the karma reckoning reckoned over the match (open question 210).
  **The year of age's Together built 2026-10-25** (block 58, SPEC
  §5.9.1, D17): a "Together" section atop the year, the four classes'
  verdicts gathered (p. 385) and read by the aspect that matters most,
  with the Remedy rows of every black or speckled pebble, bad stage and
  standing obstacle gathered into one; then each obstacle graded by the
  notes' meeting with the four aspects (vol. 2, pp. 492–494): the harsh
  years and the undertaker where the year meets an aspect as enemy, the
  tombs a great or small danger by the vitality's meeting, the
  nine-multiples' tomb years bad or mild against the year's destiny; the
  cleared ones and WB's two glories left out. `YearReckoning.graded`,
  `together`, `standing`, families `YearTogether` and `YearGrade` (item
  R2), English and Russian. Checked: `YearOfLifeTest` (a fire dragon of
  1976: the water dog's seventh harsh by the destiny's enemy, the fire
  dog's own great tomb a great danger, the iron mouse's combined
  nine-multiple once and mild; across 1977–2070 a harsh year cleared
  exactly where no aspect meets an enemy), `CatalogTest`,
  `:app:testDebugUnitTest`, the emulator in English and Russian (a fire
  dragon man in 2020: white together, the progressed sign's seventh harsh,
  its tomb a great danger, the combined nine-multiple mild; the gathered
  remedies; 2026 with none graded), the release build (no raw keys). Not
  built: the *dza zor*, the la's own six.
- **R5 The Together everywhere WB sums up — planned 2026-10-27** (block
  [60](#block-60); [sources/verdicts.md](sources/verdicts.md)). The owner
  (2026-10-27): wherever WB gives a summary, the reckoning shows it at
  the top, as the birth, the match and the year of age already do (R4).
  WB sums no whole reckoning (RD23); what it does give, reckoning by
  reckoning, beyond the three built:
  - **The moment** (SPEC §5.9.5; built whole in R2z, with no summary).
    The chapter closes on one count, the moment's own: "the fighting
    enemies are reckoned taking the time of reckoning as the year of the
    one reckoned: the life force, body, power and luck fight; count white
    and black and lay the verdict" (vol. 2, p. 172, on the scan); the
    notes (p. 537) lay it as ch. 26's and 27's like reckonings, the
    moment in the place of the asker's year. And each of the eight
    places has three, the cutting, the rise and decline and the verdict
    (p. 166), "the verdict laid by the two together" (p. 168; question
    423). **To build**: the fighting enemies, a section of their own
    with their pebbles, as RD58 reads them; a *Together* atop the moment
    with their verdict, white, black or a pair by the count (p. 50's
    rule, RD18), read by the aspects' domains (vol. 2, p. 22) and, if
    RD58 finds ch. 26's board in it, by its groups and parts of life
    (vol. 1, p. 471); each place's verdict where both its cutting and its
    rise and decline are reckoned; the Remedy rows below gathered (the
    time of day's, the hour's element's, the trigram's). Ch. 30 turns no
    verdict into a rite (RD23): none is added to the verdicts.
  - **The dead** (SPEC §5.9.4). The whole (pp. 56–57) stands among each
    dead's rows: it moves into a *Together* atop each dead, with the
    time of death's one general verdict over its twenty roots (the
    notes, p. 503), counted white and black, where RD58 finds the twenty
    reckoned, and the rites and provisions below gathered into one
    Remedy row. Each of the others joins it when its roots are built: the
    thirteenth point's verdict over its eleven roots, which sizes the
    rites (p. 50; question 212), needs the element's pebble and the
    trigram's test; the rebirth over twelve (the notes, p. 501) and the
    funeral share (p. 500) the element's pebble.
  - **The illness** (SPEC §5.9.8, §10.10). WB names the illness by the
    element that is most (p. 435), the spirit by the body's black
    pebbles (p. 425), each plank-tree's verdict (pp. 422–423) and the
    class by the white against the black of the fifteen (p. 425).
    **To build**: a *Together* under "Fell ill at": the illness named,
    the harmful spirit, the four plank-trees' verdicts and the blocked
    doors in one place, the remedies below gathered; the nine classes
    when RD58 settles question 229.
  - **None**: the thirty starting points and the thirteen circles, which
    WB never sums over all thirty or all thirteen (RD23): each point and
    circle keeps its own board and pebbles. The meadow's ornament and
    Sprugtön's reckoning bring their five verdicts by aspect (p. 22) when
    they are built. Left with R4: the birth's twenty-one children.
  - **Done when** as R2… above, with each *Together*'s rule cited in the
    SPEC section of its reckoning, no number WB does not count, the
    sections below unchanged.
  - **Built 2026-10-31** (block 60, SPEC §5.9.4, §5.9.5, §5.9.8, §10.10).
    *The moment*: a Together under the subtitle with, for the person
    chosen by day, the fighting enemies' verdict (p. 172) and the wealth's
    (p. 168), the same rows as below, and one Remedy row gathering the time
    of day's, the hour's element's and the trigram's (family
    `MomentTogether`); no other place's verdict, WB laying none by count
    (question 427), and no rite added. *The dead*: the whole (pp. 56–57)
    moved into a Together atop each dead, a line where neither colour has
    three of the four, and one Remedy row gathering every reading's rites
    and provisions shown for that dead (family `DeadTogether`). *The
    illness*: a Together under "Fell ill at" with the illness named
    (p. 435), the spirit by the body's black (p. 425), the four
    plank-trees in one row (pp. 422–423), the blocked doors (p. 419) and
    the nine classes (p. 425; the notes, p. 496), counted on the body's
    fifteen (a stated choice) by the difference of white and black, a
    speckled pebble in neither, each class with its rites; one Remedy row
    gathering the view's (a starting point mostly white left out)
    (families `IllnessClass`, `IllnessTogether`; `IllnessClass.of`,
    `IllnessBoard.bodyClass`). Checked: `IllnessTest` (the nine classes
    by the notes' examples and the even and unread differences),
    `CatalogTest` (the four families in the shape, item R5),
    `:app:testDebugUnitTest`, the emulator in English and Russian (a fire
    dragon man of 1976 at 18:43 on 2026-10-31: 18 white and 6 black, the
    wealth not there, the trigram's rite gathered; a father who died
    2001-07-10, an untimely death, and one of 2001-05-10 and a mother of
    2019-11-02 on neither whole, their rites gathered; the illness at
    that hour with its four plank-trees, two blocked doors and a fourth
    more white of the body's eight white and four black; a birth of 1965
    with six and four, no class; no one chosen, the line asking for the
    sick person). **Left**: the dead's general verdict over its twenty
    roots (the notes, p. 503), most of which the view does not reckon
    (the nine-eyes, the five grave-eyes by aspect, the clan element), and
    with it the thirteenth point's and the rebirth's verdicts; a class for
    a difference of one, two, or seven and more (question 428), which
    about half the birth years reckoned in 2026 fall on; the meadow's
    ornament and Sprugtön's reckoning, not built.
  - **The rites listed 2026-10-31** (the owner, on the illness's long
    Remedy row: "keep it, list the rites, sort them from most popular and
    general to more peculiar and specific"). Every Together's Remedy row,
    R4's and R5's, lists its rites one to a line, each once, ranked by how
    many remedy rows of the catalog name what they name (`Rites`, SPEC
    §5.9.1's *Together*; `RitesTest`, every remedy row of both languages
    split into whole rites). One Russian row mended to carry its
    pronouns as the English does («уложите её в постель и отошлите её»).
    Checked on the emulator: the illness of a 1976 fire dragon man, 74
    rites in English and 73 in Russian, thread-crosses and tormas first,
    the belt for a fisherman last.
- <a id="r6"></a>**R6 Ch. 27's small reckonings by the year — planned and built 2026-10-31** (block
  [61](#block-61), SPEC §5.9.9; [sources/corpus/wb/ch27.md](sources/corpus/wb/ch27.md)).
  The goal of 2026-10-31 ("implement 3 upgrade blocks, one at a time,
  with investigation and reading if needed") found no block left that
  waits on nothing; ch. 27's small reckonings are read on the scan (RD38,
  RD51, RD89, RD90) and named in the colophon, and SPEC §5.9.6 listed
  them as not calculated. Built: `SmallReckonings` (core), the four
  starting points of p. 17 (`StartingPoint`, `StartingCheck`), the la's
  wandering and decline (`LaWander`, `LaDecline`), the grave entered
  with its gentle or rough by sex, the end of the womb (`WombEnd`), the
  birth by the year's power; six families (`StartingCut`, `LaWander`,
  `LaDecline`, `GraveEntered`, `WombEnd`, `BirthPower`, item R6) in
  English and Russian; "The small reckonings" in the Reckonings list,
  stepped by year. Stated choices: the count down inclusive as the
  circles' up (D19); the checks counted inclusively and fought like with
  like; the bare «ཁམས» the power's (question 479). Left out: the black
  nine-eye (question 506), the household's trigrams (pp. 14–15, 17), the
  bones' reckoning (a casting), the rounds of pp. 17–19 (R7).
  Checked: `SmallReckoningsTest` (the starts of a wood person, the count
  down, the four checks from the wood rat and their cuts, the la's
  triads, the grave and its sex, the womb's end), `CatalogTest` (six
  families, item R6), `:app:testDebugUnitTest`, the emulator in English
  and Russian (a man of 1990, an iron horse, in 2026: the la wanders
  north, six checks cut, the support peg's sheet; in 2027 none), at font
  scale 2.0 and in landscape.
- <a id="r7"></a>**R7 The rounds by age of pp. 18–19 — planned and built 2026-10-31** (block
  [62](#block-62), SPEC §5.9.9). The wheel's full depth, the gods' and
  demons' trade and the peacock with crossed necks by their cuts; the
  yungdrung, the yellow lord, the cock's black trail and the
  rhinoceros, outer, inner and the woman's, by the signs printed;
  `Round`, `Rounds` (core), families `RoundCut`, `RoundSign` (item R7),
  English and Russian; a section "The rounds by age" in the small
  reckonings. Stated choices: the man's or the woman's round by the
  person's sex; the woman's peacock counted down "as above". Left out:
  the Chinese lord's banner and the ogre's round (their terms unread),
  the element enemies left "to intelligence".
  Checked: `SmallReckoningsTest` (the wheel's starts, the woman's count
  up, every check's cut over sixty years, the swastika's sign only for a
  water life force and destiny, the black trail's), `CatalogTest` (two
  families, item R7), `:app:testDebugUnitTest`, the emulator in English
  (a man of 1990: the black trail on the wood ox, its sheet) and Russian
  (women of 1985–1994: the wheel's life force cut by the earth mouse,
  its sheet; the peacock, the black trail, the rhinoceros).
- <a id="r8"></a>**R8 The reckonings of the time of asking — planned and built 2026-10-31** (block
  [63](#block-63), SPEC §5.9.9). Brahmā's month for one gone abroad
  (pp. 13–14, read on the scan by RD38), each aspect against the month
  of asking with the month's element, and the signs of p. 19 (the
  demons' door, the linked letters, the yellow light of li, the hour's
  rhinoceros, the goat horn) on the moment's view, for the person
  chosen; `Asking` (core), families `Abroad`, `AskingSign` (item R8),
  English and Russian. Stated choice: «སྟོངས», empty, is the son. Left
  out: the red and black star, the inner goat horn, the casting's hour.
  It replaces the meadow's ornament, first planned as R8: KD's sheet
  does not settle it (claims/kd-meadow-sheet-not-reproduced.md).
  Checked: `SmallReckoningsTest` (the wood monkey's aspects against a
  fire and a wood month, the fire meeting within by aspect; the demons'
  door, the hour's rhinoceros and both goat horns, the linked letters,
  the yellow light), `CatalogTest` (two families and the signs' names,
  item R8), `:app:testDebugUnitTest`, the emulator in English (a man of
  1990 by night: the earth mouse month, his life force empty, only the
  demons' door reckoned) and Russian (women of 1958 and 1970 at 17:30 in
  Kyoto: the hour's rhinoceros on the wood bird hour). The release
  build, signed with the debug key, over blocks 61–63 in both
  languages: the small reckonings, the rounds and the moment's new
  section, no raw catalog keys.
- <a id="r9"></a>**R9 The household round — planned and built 2026-11-05** (block
  [58](#block-58), SPEC §5.9.10). RD43, RD54 and the claims *household-round-…*
  left of question 258 only the speckled pebble: the zigzag runs for any
  count, the fold by the notes' way, four rows and their leftovers. A view
  of its own after the year of age, stepped by year: `HouseholdRound`
  (core), `Reckonings.household`, `HouseholdRoundView`, seven families of
  places and `HouseholdTogether` (item R9), English and Russian. Stated
  choices: a speckled pebble stays speckled with no line (question 318);
  the enemy's place read by its first three. Left out: the wealth's places
  (question 319), the verdicts when many share a mewa (pp. 407–408, how
  many is many: question 158), the household's trigrams, the three
  unsettled words of the rites. Checked: `HouseholdTest` (the notes' zigzag
  and fold of five, the seven of the claim, four and six, the order, a
  member's column), `CatalogTest`, `ReckoningsTest`, the emulator in
  English and Russian (six of a test house, its Together and rows; seven
  with both leftovers; three with the needed line and two not counted),
  font scale 2.0 and landscape in Russian, the release build in both, no
  raw keys.
- **Blocks.** R1 is [28](#block-28), reading [RD11](#rd11) and waiting on no
  build; R2… is [31](#block-31), each reckoning reading its own
  ([RD12](#rd12), [RD13](#rd13), [RD15](#rd15), [RD16](#rd16), [RD17](#rd17)) and waiting on 28 and, for what needs
  relatives, on [27](#block-27) (P1). [29](#block-29) (P2) and [32](#block-32) (H2) wait
  on R1. R4 is [58](#block-58), reading [RD18](#rd18)–[RD28](#rd28) and
  waiting on [31](#block-31). R5 is [60](#block-60), reading
  [RD58](#rd58) and waiting on 58.
- **Done when** (each item): its readings in the catalog with WB as their
  source; vectors from Dorje's charts where they exist; `CatalogTest`;
  SPEC; both languages on the emulator; the release build checked.

### <a id="y1"></a>Y1. The year of age read for action — built 2026-10-16 (SPEC §5.9.1, §8.1, §10.11)

- **Gap** (the owner, 2026-10-16). The year sheet's readings gave the
  verdict and little else: "Vitality · Death" opened on "Death:
  prosperity lost. The worst: two black pebbles", the nine-multiples and
  the progressed sign's meetings named no remedy, and where WB gives one
  it stood last, after a long omen. A Western reader takes "death" as a
  verdict on the person. The owner's ask: the points to weigh and the
  practices that counterbalance, faithful to the sources.
- **Decided** (the owner, 2026-10-16). No "another view" anywhere, in
  any reading: to a Western reader an alternative is uncertainty, not a
  call to act (SPEC §8.1, the row removed from `ReadingRow`). One
  *Remedy* row for what the person does and the rites alike: the reader
  is a ngakpa, practitioner and ritualist at once. The stages are named
  as the energy's, "energy: death stage", never a bare "death". WB's own
  framing goes in (pp. 415–416, "treat the black pebbles in earnest"),
  and the screen runs as WB weighs it, the predictive pebble first.
- **Built.** What matters most for the person (ch. 20, p. 242; pp.
  415–416; `Care`), first under the pebbles; each aspect's predictive
  pebble with its stage under it, then the grid. The ten year families
  rewritten in the shape (ruling first, images after, remedies,
  spirits and illnesses as rows), in English and Russian, and taken out
  of X10's allowance as Y1 with none. Shared remedies once for their
  group (`Reading.alsoRows`): the own year's and the seventh's general
  rites, the tombs', and the nine-multiples' cure, which p. 414 gives for
  every kind, so the single nine-multiples now have it. A bad stage's
  remedy: the aspect's decline (p. 409), then the stage's own rite from
  the illness reckoning's twelve stages, where p. 409 sends it (ch. 25,
  pp. 440–442, read in this block: Yigdzin-1, every rite line and the
  bad stages' spirits on the scan; health.md). WB's Bonpo rites there are
  left out of the rows.
- **Checked.** `:core:test`, `:app:testDebugUnitTest`; on the emulator a
  man born in a wood tiger year in 2026, whose vitality and destiny stand
  in wood's death stage: the order, the marker and the death stage's
  sheet; store shot 7 retaken in both languages (only it changed).
- **Not built.** Where WB names no remedy (one white and one black on
  the destiny, the progressed sign on one's own sign, its seventh or the
  enemy of the vitality, the black undertakers, the progressed sign on
  a tomb), none is written; the relatives' obstacles (P2) are in the
  shape already and name none either.

## Dreams

### <a id="s"></a>S. Dreams and their reading — asked 2026-11-09 (question q784)

- **Why.** The owner asked (2026-11-09) whether written sources on
  reading dreams exist and whether any ties them to the calendar.
  [q784](sources/questions/q0784.md) answers: they exist on both sides,
  and the one time rule found is the watch of the night the dream came
  in; no source found reads a dream by the lunar day, the weekday, the
  month or the year. In Japan a dream meets the calendar once, in the
  first dream of the year (初夢). The texts found are registered in
  [sources.yaml](sources/sources.yaml) (SVO, SVI, LGD, KCM, RST, RRT,
  DTD, RBG, SKP, KDL, MDP, KBS, TDR, SRM, GNC, SCH, EB9), all rank 3,
  below WB's own dream omens (ch. 30) and the Four Tantras (ST).
- **What the pool holds** (q784): WB's dream omens before the asking or
  the reckoning (ch. 30, p. 144; built, the family `MomentSign.DREAM`),
  dreams as symptoms of the sick by birth animal and sme ba (ch. 25),
  in the sixty years (ch. 18), the turtle classes (ch. 27) and the
  years of life, before the grave ground (ch. 32), and the clan's token
  animal found from dreams (the digest, p. 474); the Four Tantras'
  Explanatory Tantra ch. 7, dreams the distant and uncertain signs of
  death, and its dreams of nāga illness, fever, pregnancy and poison
  (ST, etext); the Svapnohana whole in the etext.

#### <a id="s1"></a>S1 The dream manuals read — planned 2026-11-09

- **Work.** RD207–RD212 and RD214 read the registered texts: the
  Svapnohana and Advayavajra's examination on the Derge scan, the Lama
  Gongdü's signs, the short manuals, the deity rites, the Four Tantras'
  passages on the scan, Klong rdol bla ma's methods and the folk
  collection, and Scheuermann's study for its facts. Each passage in
  Tibetan with the English beside it; each method's rule on *when* a
  dream counts (the watch of the night, dawn, the lunar day of the rite)
  as a claim; a table at the end of which text gives which time rule.
  Then, for S2, [RD215](#rd215) settles the watches of the night in clock
  terms and [RD216](#rd216) gathers every read image into one catalogue
  with each text's verdict.
- **What to settle.** Whether any manual names a date, weekday, mansion
  or month (none did in q784's searches); the variants of the
  watch-of-the-night spans; what a bad dream's remedy is in each
  tradition; and where the manuals and WB's ch. 30 overlap, WB stands
  (`.agents/rules/sources.md`).
- **Check.** Every topic file holds its reading; `kb.py check` and
  `SourcesTest` clean; the readings marked done in the table.
- **Read 2026-11-09** (RD207–RD213, RD215–RD217, RD219–RD225; most in
  part, see their rows): no text read ties a dream to a lunar day,
  weekday or month; the watch of the night is the one time rule, and
  only the Svapnohana gives spans for it (a year, eight months, three
  months, half a month, ten days). The Lama Gongdü, Klong rdol's text
  (RST, KBS, one witness) and SVI instead cut the night in three (dusk habit, midnight spirits, dawn the only
  omen); DTD examines the last of four watches. No Tibetan source
  bounds the watches by the clock (`claims/night-watches.md`). The
  catalogue is `claims/dream-catalogue.md`: 85 images, 13 not to be
  offered yet (`dream-unsettled-words`), eight read in opposite ways.
  Still unread: Scheuermann (RD214, the PDF), the Ebara 大雑書 (q786),
  the folk collection's dream part (RD221) and RBG; the scan proof of
  most passages (RD218, RD219, RD222).

#### <a id="s2"></a>S2 A dream reading in the app — planned 2026-11-09

- **Gap.** The app shows WB's dream omens as observed signs of the
  moment's reckoning (SPEC §5.9.5) and nothing else on dreams. No text
  ties a dream to the day (q784), so the reading is not an election,
  which searches days for a work, and no row of the day page follows
  from the sources: it is a reckoning with inputs, like the illness
  reckoning (H2), whose input is what was dreamt and when, and whose
  output is each image's verdict. Free text is not an option: the app
  is offline and has no language model, and the catalogue is finite.
- **Design** (the owner, 2026-11-09, after the sources of q784):
  - **The screen.** "Read a dream" in the Reckonings list (§10.11) and
    a link from the moment screen's "Dreams before the reckoning"
    sheet; a full screen with a back arrow, Tibetan page only (Japan
    has no dream reading, only the dated 初夢 of S3).
  - **When.** The night: by default the night before the shown day,
    since the Tibetan day turns at 05:00 and last night belongs to
    today (SPEC §3). The watch of the night (ཐུན, thun), the one time
    rule any source gives (the Svapnohana, SVO): a chip row, first,
    second, third, fourth and dawn (སྐྱ་རེངས, skya rengs); or a clock
    time typed as the illness reckoning takes one, mapped onto the
    watches by [RD215](#rd215)'s spans (its fallback: the night from
    sunset to sunrise in four equal parts, `SunTimes`, dawn the last
    part's end). The watch gives the span within which the dream bears
    fruit (a year, eight months, three months, half a month, ten days)
    and the app turns it into a date: "dreamt in the third watch: bears
    fruit within three months, by 9 Feb 2027", the date opening its
    day.
  - **The dreamer is ill.** A checkbox among the inputs, not a section
    (the owner, 2026-11-09): when set, the Four Tantras' dream readings
    (ST, RD211) join the images' rows in their place in the catalogue,
    each saying what the dream marks for a sick person ("marks an
    illness sent by the nāgas", "a distant and uncertain sign of
    death, turned back by merit, recitation and the yidam") with its
    remedy; unset, the medical images are not offered. The Four
    Tantras stand above WB on medicine (SPEC §8.1), so where WB ch. 25
    and ST both read a sick person's dream, ST's row is the one shown.
  - **What.** The dream images as a grouped, searchable multi-select,
    the election's work picker generalised (§10.8): groups by source
    text in rank order, WB's good and bad dreams (ch. 30, the
    `MomentSign.DREAM` rows reused, never copied), the Svapnohana's
    catalogue, the Lama Gongdü's signs and the short manuals as S1
    reads them, the Four Tantras' when the dreamer is ill; each image a
    toggle, several per dream the normal case; a search over the
    images' names in the app's language. An image two texts both read
    is one entry, with each text's verdict as a row, WB first
    ([RD216](#rd216)'s catalogue).
  - **The reading.** The watch's line first; then one row per chosen
    image, its verdict and tone dot, under its text's heading, in the
    shape of SPEC §8.1 (English and Russian, `CatalogTest`); one Remedy
    row for a bad dream (WB: incense smoke and washing, then striving
    for good omens again; SVO: a fire offering of red sandalwood and
    reading the text in the morning), each once. **No net verdict**: no
    source sums images, so the screen never calls the dream good or bad
    as a whole; each image speaks for itself (the rule of the brief,
    which never shows outweighed factors, applied the other way: nothing
    is weighed).
  - **Core.** `DreamReading` in `core/tibetan/Dream.kt`: the watch from
    a clock time and the night's sun times, the fruit date from the
    watch, the catalogue as a family per text (`DreamImage` entries
    keyed by text and image, verdict tone, medical flag) with their
    catalog entries; the CLI prints a dream read for a date, a watch and
    a list of images.
- **Stated choices**, for the owner to overrule: equal quarters of the
  night from sunset to sunrise (RD215 found no span of WB's own; the
  fallback stays, marked as such on the screen); the fruit date shown
  for the Svapnohana's watches only, since no other text gives spans,
  and a dream in the three-part night's dusk or midnight shown with
  those texts' words (habit, spirits' display) beside the image rows; the
  fruit date counted in civil months and days from the morning after the
  dream; an image whose verdict rests on an unsettled word is not offered
  (as the moment's signs, §5.9.5); the deity rites' asked-for dreams
  (TDR, SRM, GNC, RD210) are not an input until their texts are read and
  the owner wants them, then as a "Dreamt after a rite" chip.
- **Block.** [73](#block-73); reads [RD207](#rd207)–[RD212](#rd212),
  [RD215](#rd215), [RD216](#rd216) ([RD214](#rd214) beside, access
  permitting); waits on no block (the moment screen and the work picker
  are built).
- **Done when.** `DreamTest`: the watches of a night in Lhasa in
  summer and winter from `SunTimes`, a clock time at the night's
  quarters and at dawn, the five fruit dates; a catalogue entry per read
  image; `CatalogTest` with no allowance; `:app:testDebugUnitTest`
  (`ReckoningsTest` with the new entry); `kb.py check`; on the emulator
  in English and Russian: the picker, the search, a dream of three
  images in the second watch, the same with "The dreamer is ill" set,
  the fruit date opening its day; the release-build check (new enums);
  SPEC §5.9.11 (new), §10.11 and §8.2, this item, the README, both
  listings, a screenshot of the reading, the website and the GitHub
  description (*Docs on every feature merge*).

#### <a id="s3"></a>S3 The first dream of the year on the 旧暦 page — planned 2026-11-09

- **Gap.** 初夢, the first dream of the year, is a dated observance: the
  dream of the night of 2 January today, earlier of the night of 節分
  before 立春 and then of New Year's night, with the 宝船 picture under
  the pillow (q784). SPEC §7.3 lists the sekku, 十五夜, 十三夜, 旧正月
  and O-Bon, and nothing of this kind.
- **Work.** RD213 reads the sources (EB9 and the texts 日本大百科全書
  names) for the night's history and any rule the dream book ties to a
  date. Then, on the owner's word, a festival line on 2 January
  ("初夢の夜", labelled Gregorian as O-Bon is), its reading the history
  in a paragraph and the 宝船 custom as a row, in both languages;
  SPEC §7.3 and the listing updated with it (*Docs on every feature
  merge*).
- **Check.** The line on 2 January of two years in `KyurekiElectionTest`'s
  neighbour or a festival test; the reading in the shape of SPEC §8.1.

## Appearance

### <a id="a1"></a>A1. Light theme and colour themes — planned 2026-10-11, built 2026-10-15: System, Ink, Paper and Night (block 33), Maroon and Sepia (block 38) (SPEC §10.1, §10.3, §10.5, §10.7)

- **Gap.** The app is dark only (SPEC §10.1, "in 1.0"): `Palette` in
  `ui/Theme.kt` is one object of fixed colours, read directly about 510
  times in 20 files, 29 of them inside Canvas drawing (`Diagrams.kt`,
  `CueGlyphs.kt`, `HoursRow.kt`, …); `themes.xml` paints the window ink
  before Compose and sets light status-bar icons off. In daylight, and
  for readers who find light text on black hard to read, there is no
  other choice.
- **Work.**
  - **The palette as a theme.** `Palette`'s roles stay (ink, surface,
    raised, selected, line, lineStrong, text, muted, faint, off,
    saffron, vermilion, scrim; the tones good, bad, mixed) and become a
    `ZanshinColors` value per theme, given through a CompositionLocal,
    so the call sites keep their names; the Canvas code reads its
    colours before it draws. `ZanshinTheme` builds the Material scheme
    from it, `lightColorScheme` for the light ones.
  - **Colours that are not roles.** The sme ba's printed colours and
    the nine stars' (`smeBaColour`, `starColour`) keep their hues on
    every theme, the white box and the black box outlined where they
    meet a page of their own lightness; the element colours of T3, now
    lightened for the dark page, get a darker set for the light themes,
    each element told apart from the others and from the tones.
  - **The themes** (D5, settled): **System**, Ink or Paper by the phone's
    dark setting; **Ink**, today's palette; **Paper**, the light theme,
    a warm off-white page with dark ink; and the pre-built
    alternatives, two dark and one light: **Night**, black for OLED
    screens; **Maroon**, the robe's maroon with saffron; **Sepia**, a
    low-blue parchment page for the evening.
  - **The setting.** Settings (§10.5) gains **Theme**, a dialog with
    each theme as a small swatch of its page, text and the three tone
    dots; a tap applies it at once. Stored in `Settings.kt` with the
    other preferences; the language dialog's pattern.
  - **Everything follows the theme.** The window before Compose draws
    (the stored theme set in `onCreate`, so the start shows no flash
    of the other theme), the status and navigation bar icons, sheets,
    balloons, dialogs, the date picker, the election and meditation
    screens and the hours panel. The bell's notification stays the
    system's. The widget, when built (§10.6), takes the app's theme.
  - **Not planned:** Material You's colours taken from the wallpaper:
    the tones and printed colours are chosen against a known page, and
    a wallpaper's page can undo their contrast.
- **Checks of the review of 2026-10-11**, each part of the work above:
  - **Tones that colour text need 4.5:1.** The tones colour not only
    dots, strips and rings but text at 13–14 sp: "good 62", "hours to
    avoid", "Good for", "Avoid", "Decides the day's tone in the brief
    today". 3:1 is the floor for marks; text needs 4.5:1. Either every
    tone meets 4.5:1 on each page of its theme, or each theme carries a
    mark tone and a text tone and `toneColor` takes which; `PaletteTest`
    checks both.
  - **Maroon needs its own accents.** Vermilion and saffron sit beside
    maroon in hue; on a maroon page the 旧暦 accent and the bad tone lose
    their separation from the page. Maroon carries an accent pair and a
    tone set of its own, checked like the rest. Night is Ink with the
    page black, a variant. So the themes come in two blocks:
    [33](#block-33) builds System, Ink, Paper and Night; [38](#block-38)
    builds Maroon and Sepia on it, since each theme multiplies the
    two-language, every-screen check.
  - **Every colour a token first.** The hour dial draws its segment gaps
    and its centre in `ink`, which on Paper is a dark line on a light
    page; the switches, the picker's selected cell, the sheets' tone band
    and the chips name palette members directly. The inventory is every
    `Palette.` use outside `Theme.kt`; each is given a role before any
    theme is drawn, a mechanical pass, not a design one.
  - **The window chrome.** Beside `themes.xml` and the status bar icons:
    the navigation bar's colour, `windowLightStatusBar` toggled at run
    time through `WindowCompat.getInsetsController`, the launcher's
    splash background, and a Paper variant of `themes.xml` chosen in
    `onCreate`, so that the first frame does not flash dark.
  - **A light screen first.** The sme ba boxes, the nine-star board, the
    hour dial and the combination table were tuned to the dark page. The
    first step is Paper on the emulator across every diagram, before the
    dialog is built: the glyphs' 1.4-unit strokes may be too thin on
    white before the contrast test says so.
  - **Dots.** [U12](#u12)'s dots and rings hold their contrast on every
    theme; block 33 builds on [34](#block-34) for that and so that the
    store shots are retaken once, with U8's sky line and U15's toggle.
- **D5 — settled 2026-10-11 (the owner):** the six themes above, by
  those names, and Ink the default for every install, so that no one's
  app changes colour on the update. The review's alternative, recorded
  and not taken: System for new installs, an install told apart by a
  stored calendar preference, Ink for updates.
- **Block.** [33](#block-33) for System, Ink, Paper and Night, building
  on [34](#block-34) and on [42](#block-42) (the sheet's rows, [X2](#x2),
  which store shot 3 shows); [38](#block-38) for Maroon and Sepia,
  building on 33; no reading.
- **Done when** (each block, for its themes). `PaletteTest` holds every
  theme to its contrast: text and muted at 4.5:1 at least on ink,
  surface and raised, faint and the mark tones at 3:1, the tones
  wherever they colour text at 4.5:1, U12's rings at 3:1, and good, bad
  and mixed apart in lightness as well as hue (as the dark palette's
  comment requires, for colour-blind readers); no `Palette.` use left
  outside the theme; the strings in both languages (`TranslationsTest`);
  every theme on the emulator, in English and Russian, across both
  pages, a sheet, the date picker, the election, meditation and About
  screens, with no colour left from another theme and no flash at the
  start; a release build checked on one light theme; SPEC §10.1, §10.3
  (T3's colours, the sme ba), §10.5 and §10.7; the listing in both
  languages names the themes, and `store_shots.py` takes one Paper shot
  in each language beside the seven in Ink.

- **Built 2026-10-15** (block 33: System, Ink, Paper and Night). `ZanshinColors`
  holds the roles, with `light` and a new `onAccent` (text and switch thumbs on
  a saffron or vermilion fill, the page's colour on all four, since each
  accent stands 4.5:1 against its page); `InkColors` is today's palette
  unchanged, `NightColors` Ink on black, `PaperColors` the light page.
  **Deviation from the plan:** `Palette` holds the theme drawn now as a
  snapshot state, not a CompositionLocal: a theme is one for the whole app,
  and the 510 reads include draw lambdas and plain functions (`toneColor`,
  `elementColour`), which a snapshot read tracks and a CompositionLocal
  cannot reach, so every call site kept its name and nothing was hoisted;
  the frozen top-level `body` style became a getter. The inventory found
  ten foreground uses of `ink` (text on an accent chip, the date picker's
  selected cell, the Health tabs, the location Save, the switches' thumbs,
  the time picker's dial), now `onAccent`; the dial's gaps and centre in
  `ink` are the page's colour by role, right on every theme. Each theme
  carries one tone set, not a mark set and a text set: Paper's tones
  (good #1A4F23, mixed #6A4C00, bad #AD3019) stand 4.8:1 or more as text
  on the raised cells and 5 L* apart. The printed colours keep their hues;
  `needsRim` rims the box of the page's own lightness (the black sme ba
  box on a dark page, the white ones and the white stars' dots on a light
  one); the elements take a darker set on a light page. The window: a
  style per theme in `themes.xml` (System's by `values-night`), set before
  `super.onCreate` with the bars' icons and the window background
  (`ThemeWindow.kt`), and the next launch's splash through
  `setSplashScreenTheme` on Android 13 and later; Android 12's splash stays
  the manifest's Ink. Settings gains **Theme**, a dialog of swatches that
  applies on a tap and stays open until Done (so the themes can be
  compared), stored as `theme`. `PaletteTest` holds all three palettes to
  the list under *Done when*, the tones' lightness floor at 2 L* (Ink's
  own closest pair, good and mixed, is 2.4 apart), and fails on any colour
  value written outside the theme and the printed colours. Checked: the
  app's unit tests; every theme on the emulator (API 37) in English and
  Russian across both pages, the hours panel, the combination, haircut,
  astrologer-spirit and mewa sheets, the year balloon, the menu, the theme
  dialog, the date picker, the election and a work's list, Health, the
  Reckonings, meditation and About; System with the phone set dark and
  light, and switched while open; a cold start on Paper recorded frame by
  frame with no dark frame; the release build on Paper. Store shot 8 is
  new, shot 1's day on Paper, in both languages; shots 1–7 are unchanged
  in Ink and were not retaken. The listing and README name the themes.
- **Built 2026-10-15** (block 38: Maroon and Sepia). `MaroonColors` is a
  dark page of the robe's maroon (#3A1117) with cream text and an accent
  pair and tone set of its own, as the review asked: saffron a brighter
  gold (#F4BA4A), vermilion turned towards orange and lightened (#FFAC80),
  the bad tone likewise (#FFA07C), good #B0E0A6 and mixed #EAC66A, each
  4.5:1 as text on the raised cells. `SepiaColors` is a parchment page
  (#EFE3C8, its blue channel 200 against Paper's 232) with brown ink and
  Paper's tones darkened to hold 4.5:1 on its deeper sheets. Both pass
  every check of *Done when*, and `PaletteTest` adds one for Maroon: the 旧暦
  accent, the bad tone and saffron at least 25° of CIELAB hue from the
  page (they are 37°, 30° and 64°; Ink's bad tone would be 19°). The rims
  became a rule: `needsRim` rims a printed box or star dot that stands
  under 1.5:1 against the sheet, which gives exactly block 33's rims on
  Ink, Night and Paper and on Sepia rims the yellow box (5) and the yellow
  star's dot as well, which a list keyed on light or dark would have
  missed (1.33:1 and 1.36:1). The element colours' two sets hold 3:1 on
  both new themes unchanged. Each theme has its window style (Maroon's
  on Ink's, Sepia's on Paper's) and its name and line in the dialog in
  both languages. Checked: the app's unit tests; both themes on the
  emulator in English and Russian across both pages, the combination and
  rokuyō sheets, the date picker, the theme dialog, the election, Health,
  the element reckoning, meditation and About (the year balloon in
  English), with no colour left from another theme; the nine-star
  board's and the sme ba square's rims on Sepia; a cold start on each,
  recorded frame by frame, the splash and first frame in the theme's own
  page; the release build on Sepia, both pages and the rokuyō and
  twelve-station sheets, no raw keys. No store shot was retaken:
  Ink and Paper draw as before. The listing and README name the six
  themes.

### <a id="a2"></a>A2. Large font and landscape — planned 2026-10-11, built 2026-10-16 (SPEC §10.1)

- **Gap.** The page was laid out at font scale 1.0 on a 411 dp portrait
  phone. At 1.3 it holds (the review of 2026-10-11): the month heading
  and the hours list wrap, nothing clips. But the strip's hour labels
  are 10 sp, the Lunar day label column is a fixed 120 dp, the header's
  date meets the calendar toggle at larger scales (in Russian with
  "Сегодня" and the person ring it clips at 1.3, found building U15), and nothing has been
  looked at at 1.5 or 2.0, in landscape or on a tablet, where the pager's
  page is 800 dp wide and the sheets' diagrams stay 280 dp.
- **Work.** Labels at 12 sp at least; label columns by the widest label
  measured, not fixed; the header's date in a short pattern where the
  long one does not fit; in landscape and above 600 dp the page in two
  columns, the headline and hours left and the sections right, or a
  capped width centred; diagrams that scale with the sheet's width up to
  360 dp; the dial and the boards keep their tap targets. SPEC §10.1
  gains a line on scale and width.
- **Block.** [40](#block-40); no reading; builds on [34](#block-34),
  [36](#block-36) and [42](#block-42) (the sheets' label column,
  [X2](#x2)), so that the rows it measures are final.
- **Done when.** The emulator at font scale 1.0, 1.3, 1.5 and 2.0 and in
  landscape, and a tablet AVD at 800 dp: both pages, the brief, the hours
  panel, the election and meditation screens, no clipping or overlap, in
  English and Russian; SPEC §10.1.
- **Built** 2026-10-16 (`app/.../ui/Fit.kt`). The day pages stand in two
  columns from 600 dp (`PageColumns`): headline, hours and brief left,
  the sections right, each column scrolling on its own, at most 1040 dp
  together; the screens of their own are capped at 640 dp and centred
  (`ReadingWidth`). The header lays out its controls first and gives the
  date what is left, the whole date, then without its year
  (`pattern_header_date_no_year`, new string), then `d MMM`; where even
  that has no room (Russian at 2.0 with the person ring) the date takes
  a line of its own under the menu, whole. The sky line moves the noon's
  altitude into its balloon ("Sun at noon", new string) when the three
  times would not fit with it, and its "Set a location" wraps under a
  dotted line per line. The Lunar day and 干支 label columns are measured
  (`FactColumn`: the widest label, 120 dp to 40 % of the row, never under
  the longest word; at 45 % "Вода-Свинья" broke inside its last word at
  1.5), and a fact's value breaks by the language's hyphenation, never at
  a bare letter. The hours strip's labels are 12 sp, its height their
  measured height; the short form fits by measuring again, as Android
  scales small text more than large (at 2.0 it was cut at "23–C"). The
  rings and compasses of the sheets (the mansion, karaṇa and yoga rings,
  Rāhu's and the bla mkhyen's compasses, the 十二直 dial, the 宿 ring, the
  nine-star board, the 恵方 compass, the rokuyō arc) are framed
  (`DiagramFrame`): they grow by the font scale, or by the room on a
  tablet, to 360 dp or the sheet's width, strokes and labels together,
  their labels never past the font scale and their captions at it; at
  2.0 the mansion ring's name had run over its numbers. The hours' dial
  is drawn up to 360 dp with its names at its own scale (`DrawnFor`), at
  2.0 its animal names had overlapped the rings. The combination table
  keeps its figures' size on a phone (`rememberDrawingMeasurer`). Text
  under 12 sp raised to 12 (the pair grid, the axes, the haircut grid's
  civil days, the date picker's small figures); grid cells grow taller
  rather than clip (`atLeastAspect`, least heights); title rows of 64 dp
  grow; the Health tabs scroll sideways at their names' width where four
  equal segments do not fit ("Сего…" at 2.0); the date picker's Today
  keeps its word whole ("С е г о д н я" down the side at 2.0); the 旧暦
  term card puts "until 22 Oct" under the term where the line is short
  ("Kanr/o" at 2.0). Checked with `tools/emulator/scales.py` (new) at
  1.0, 1.3, 1.5 and 2.0, in landscape and at 800 dp upright (wm size
  1600x2560, density 320, on `zanshin-test`: no second AVD), in English
  and Russian: both pages top to bottom, the hours panel, the brief, a
  mansion's sheet, the date picker, the election for weddings, the
  meditation timer, Health and the 旧暦's mansion ring; at 1.0 nothing
  moves but the strip's labels and the dial's 20 dp. The release build
  checked at 2.0 and in landscape (names, no raw keys). Store shots 1–5
  and 8 retaken in both languages (the strip's labels).

### <a id="a3"></a>A3. Glyphs of their own for the works — built 2026-10-25 (SPEC §10.7)

- **Gap.** Every work was drawn by its family's glyph, 180 works on 38
  glyphs: the breakdown showed the throne-less roof frame before
  "climbing high" and "pitching tents", the feast cup before "games" and
  "horse racing", the horseshoe before "raising dogs" and "dairy work",
  the meditating figure before "haste" and "careless words", the book
  before "astrology and divination" and "learning music or dance", the
  gourd before "surgery" and "visiting the sick", the mala before
  "mantras" and "prostrations".
- **Work.** A glyph per work where its family's fits badly, drawn in the
  same style; the families stay what the In brief row counts and shows.
- **Block.** [59](#block-59); no reading.
- **Built** 2026-10-25 (`CueGlyphs.WORK`, `CueGlyphs.of(Activity)`): 53
  works with 47 drawings, listed in SPEC §10.7; the dog's and the ox's
  heads, the wind and the smoke offering reused from the animals, the
  elements and the festivals. The breakdown, the election's work list
  (the search results and the rows outside a family) and the election's
  heading draw a work by its own glyph. `CueGlyphsTest` holds that each
  parses and differs from its family's; `tools/glyph_sheet.py` (new)
  renders every work glyph beside its family's, large and at 22 px.
  Checked on the emulator: a Tibetan day's breakdown and the election's
  picker and result.

## Widget and notices

Two surfaces outside the app's screens, both reading the day as the page
does (`DaySummary`, `DayInfo`), nothing weighed anew, no reading. SPEC §3
lists notifications under Later and §10.6 deferred the widget; each item
changed that when built.

### <a id="n1"></a>N1. The morning notice — built 2026-10-16 (SPEC §3, §10.5, §10.9)

- **Gap.** A calendar read once a morning is opened for its tone, reason
  and hours. The bell built exact alarms, a foreground ring service, the
  boot, update and clock receivers and the notification permission (SPEC
  §10.9); a daily notice reuses all of it. The brief's wording is the
  notice's text.
- **Work.** A switch in Settings, "Morning notice", off by default, with
  a time (06:00 at first; the bell's From pattern, or [U17](#u17)'s
  picker once built); at the time one silent notification for the
  calendar last viewed: on the Tibetan page the day's tone and reason
  ("a lucky day · by the combination"), the counts and the hours against
  the tone in [U11](#u11)'s short form, or "every work to avoid, for
  you" on the person's enemy weekday or death mansion; on the 旧暦 page
  the rokuyō and the day mark. A tap opens the day. One exact alarm set
  again after each notice, a reboot, an update and a clock change
  (`BellPlan`'s pattern), the ring service not used: a notice makes no
  sound. SPEC §3 moves the notice into scope, §10.5 gains the switch,
  §10.9 says the alarms are shared.
- **Block.** [39](#block-39); no reading; builds on [36](#block-36) for
  the hours' short form.
- **Done when.** On the emulator with the clock set, the notice at its
  time in English and Russian, after a reboot, on a day with no hours
  against the tone, on the enemy weekday with the test person, and on
  the 旧暦; the switch's subtitle names the next notice; `BellTest` for
  the alarm's plan; the listing in both languages; SPEC §3, §10.5 and
  §10.9.
- **Built** 2026-10-16: "Morning notice" in the menu's Settings group,
  its subtitle "Next notice on Saturday at 06:00", and while on a "Time
  of the notice" row opening the bell's time picker without its
  half-hour rounding (`BellTimeDialog` with no note); switching it on
  asks for the notification permission on Android 13 and later. The
  notice (`app/.../bell/MorningNotice.kt`) is on a channel of low
  importance, silent and without heads-up, with the civil date as its
  subtext: on the Tibetan page the title "A lucky day · by the
  combination", then "good 79 · avoid 14" and "hours to avoid 05–09 ·
  11–13 · 17–19 · 23–01" (the strings U11 kept for it), or on the
  person's enemy weekday or death mansion "Every work to avoid, for
  you" and "avoid 20" with no hours; on the 旧暦 page the title "大安
  Taian — great peace", the day mark ("受死日 black day — the worst of
  all") and the counts. A tap opens today's page of that calendar from
  any screen. `NoticePlan` gives the time (`BellTest`: today or
  tomorrow, a time the clock skips, an hour that repeats); the bell's
  `RescheduleReceiver` sets both alarms again, and a launch sets the
  notice's again, since a force stop clears an app's alarms. A notice
  whose day is past, after a clock change, is dropped. Every month's
  combination periods hold both tones (checked for months 1–12), so a
  day with no hours against the tone is only the person's avoid-all
  day; the enemy weekday stood for both checks. Checked on the emulator
  with the clock set: 2026-10-16 (English, by the combination),
  2026-10-22 (the test person's enemy weekday), 2026-10-17 in Russian
  («Неблагоприятный день · решает: Суббота», good hours), 2026-10-19
  on the 旧暦 in Russian and English (black day), the tap from the
  Meditation screen with the Tibetan page chosen opening the 旧暦's
  today, the notice after a reboot, and the release build's notice
  (names, no raw keys). No store shot changes: shot 5 leaves the
  Settings group closed.

### <a id="w2"></a>W2. The wide widget's free space — asked and built 2026-10-25 (SPEC §10.6)

- **Why.** The owner (2026-10-25): the wide widget leaves free space above
  and below on an upright home screen; asked what goes there, the owner
  chose the sun's times above and the person's own day below.
- **Built 2026-10-25**: a tall variant of the wide size from 195 dp of
  height (`DayWidget.TALL_HEIGHT`), with `widget_sun` and
  `widget_yours_row` in `widget_wide.xml`, the padding cut to 4 dp. Checked:
  `:app:testDebugUnitTest`; on the emulator in English and Russian, the
  Tibetan page and the 旧暦 (sun line only), at font scale 1.0 and 1.15
  (the first try cut the Your day line at 1.15; the padding and margins
  were cut until it fit); store shots retaken in both languages (9 shows
  the tall widget, 7 the year of age's Together).

### <a id="w1"></a>W1. The widget — planned 2026-10-11, built 2026-10-16 (SPEC §10.6)

- **Gap.** SPEC §10.6 defers the widget's sizes and content. For a
  calendar read once a morning the home screen is the main surface, and
  the day's tone, reason and hours are the facts read.
- **Work.** A Glance widget in two sizes, for the calendar last viewed:
  small, the civil date, the calendar's day and the tone with its reason;
  wide, with [U11](#u11)'s hours strip and the present hour, and on the
  旧暦 the rokuyō and the day mark. A tap opens the app on today, as SPEC
  §4 has it: the Tibetan day turns at 05:00, the 旧暦's at midnight. Updated
  at midnight and at each two-hour period's boundary by an alarm (the
  bell's pattern), and on a change of calendar, person or place. The
  app's theme ([A1](#a1)), as A1 says. SPEC §10.6 written, §3's scope
  line, the listing and a store shot of its own in `store_shots.py`.
- **Block.** [41](#block-41); no reading; builds on [33](#block-33) (the
  theme), [34](#block-34) (the header and dots as they will stay) and
  [36](#block-36) (the hours' short form).
- **Done when.** Both sizes on the emulator's launcher in English and
  Russian, in Ink and Paper; the change at a period's boundary and at
  midnight seen with the clock set; the widget after a reboot; the
  listing names it in both languages; SPEC §3 and §10.6; README's feature
  list.
- **Built 2026-10-16** (block 41, SPEC §10.6): `app/.../widget/DayWidget.kt`
  (`WidgetPlan`, `DayWidget`, the providers `Small` and `Wide`, the
  alarm's `TickReceiver`), `widget_small.xml` and `widget_wide.xml`, each
  its own preview in the launcher's list (16 October 2026 in Kyoto at
  13:00). **Not Glance**: Glance 1.2.0 brings WorkManager, DataStore and
  protobuf, and WorkManager's manifest adds permissions (network state, a
  boot receiver of its own) for updates the app's exact alarms already
  keep; plain `RemoteViews` need no library and no permission more.
  Leaving the app draws it anew, the bell's receiver after a reboot, an
  update and a change of clock or zone. Found in the build: the launcher
  applies an update to the views it holds, so every row's visibility is
  set on each update (a row hidden for the 旧暦 stayed hidden on the
  Tibetan page); a tint with alpha shows the white shape under it, so the
  strip's 80 % tones are laid over the page in code; the text follows the
  font scale up to 1.15 only, and a compact variant of each size serves a
  landscape home screen's cells (193 × 87 dp and 193 × 180 dp on the
  emulator, against about 178 × 202 and 375 × 201 dp upright). The hours
  against the day take the hours row's short words ("avoid", not "hours
  to avoid"), which fit the wide size in Russian. Checked: `WidgetTest`
  (the next change by the place's odd hours and the phone's midnight, a
  skipped and a repeated hour, the present period); on the emulator both
  sizes in English and Russian, Ink, Paper and System (switching with the
  phone's dark setting without an update), the change at 23:00 Kyoto and
  at midnight with the clock set and at 05:00 on the Tibetan page, the
  widget redrawn after a reboot with its alarm set again, no alarm left
  once both were removed, a tap opening today's page, a 旧暦 black day,
  the person's avoid-all day, font scale 1.5 and 2.0 and a landscape home
  screen; the merged manifest's permissions unchanged; store shot 9 in
  both languages (`emu.widgets()` places both from the launcher's list);
  the release build.

## Localisation

English stays the source language. The order: Russian, translated by the
project; then Japanese; then German, French, Spanish, Portuguese and
Chinese (Simplified and Traditional, served separately), all through Hosted
Weblate.

The text is out of the code: interface text in `strings.xml`, the engines'
and readings' text in the catalog, dates and ordinals by the app's language
(SPEC §8.2, §10.1). A language is added as files, not code.

### <a id="l3"></a>L3. Russian, by the project

Built: the interface strings, the catalog and the ru-RU store listing, with
the language switch in the menu (SPEC §8.2, §10.5).

- Done when the owner has read the app in Russian on the phone and the
  fixes that brings are in; after [X1](#x1)–[X10](#x10) ([42](#block-42)–[49](#block-49)),
  which reword every reading, so that the wording is read once.
- **The project's read-through, built 2026-10-10** (the owner asked for
  it ahead of their own): every string of `values-ru/strings.xml` and
  `texts_ru.properties` read against the English, and every row and sheet
  of both pages, the hours, the year of life, the menu's screens and the
  date picker walked in Russian on the emulator. Fixed: the Sanskrit names
  of the months, mansions, yogas, karaṇas and lunar-date classes showed in
  IAST on Russian screens (SPEC §8.1 wants a Russian transcription): they
  now come from the catalog (`.sanskrit`, SPEC §8.2), Бхадрапада,
  Уттарапхалгуни, Чатушпада, Шукла; one Russian word per term (лунное
  созвездие on the Tibetan side, владыки земли, Вишти, «семь красных»,
  мытьё головы); the plural "так что 1 дела … стоит избегать"; "Ваше
  «жизненная сила»" in the year's pebbles; accusatives after a nominative
  in the black hour's list; infinitives in noun lists; capitals after
  semicolons; «то же стихия»; Всходы пинеллии for 半夏生; the 旧暦 election note's «Пометка, называющая всё, хорошим…». In both
  languages "setting up a sel" named Wylie alone: now "a clearing rite
  (སེལ, sel)". What is left is the owner's own read on the phone.

### <a id="l4"></a>L4. Hosted Weblate

For Japanese first, then German, French, Spanish, Portuguese, Simplified
Chinese and Traditional Chinese.

The repository is ready: partial translations fall back to English string
by string, tests guard keys, placeholders, `locale_tag` and the
completeness of offered languages, and [weblate.md](weblate.md) gives every
setting of the project and its three components. What is left is on
Weblate and GitHub, with the owner's accounts:

- Push `main`; create the project on Hosted Weblate (Libre plan) and its
  components as in weblate.md; approve the imported Russian strings; add
  the GitHub webhook.
- Not before 2026-12-28: the Libre plan wants three months of active
  development, and development began on 2026-09-28 (weblate.md,
  *Eligibility*). The README then names Weblate.
- Split the catalog: the Wikipedia-adapted readings into a CC BY-SA
  component of their own (weblate.md, *The catalog's licence*).
- Fill the glossary with the kanji and Tibetan terms and their English.
- Japanese goes right after Russian, as the first Weblate language,
  translated there by the owner together with invited friends; the owner
  and those of them who read Japanese well are its reviewers. Its
  readings of the 旧暦 page can be written from the Japanese sources
  directly, but Todan's copyrighted wording still may not be copied;
  Wikipedia's CC BY-SA wording may, with attribution.
- The app itself gains no network access: translations arrive in the source
  and ship with a release.

## The knowledge pool

#### <a id="c1"></a>C1 The knowledge pool in layers — planned 2026-10-22

- **Gap.** `docs/sources/` is 42 topic files and 450 000 words that mix
  the corpus (the Tibetan with its page and image) with what is drawn from
  it; a claim cites a file, not a passage; the questions are one numbered
  file; a source's coverage is prose in README's *State* column. The pool
  will grow to several medicine and tantra sources and thousands of pages
  (the owner, 2026-10-22), and that shape does not bear it.
- **Work.** The layers of [docs/sources/STRUCTURE.md](sources/STRUCTURE.md):
  the registry `sources.yaml`, a corpus file per source chapter with
  passage ids and reading states, claims citing passage ids, a file per
  question, and the index `tools/sources/kb.py` that reads both the old
  and the new shape (built 2026-10-22: the registry, the note, the index
  with `check`, `coverage`, `passage`, `cites`, `questions`, `next`,
  `search`, `orphans`, `graph`; `schedule.py stubs` and `SourcesTest`
  accept a topic file in a subdirectory; the same evening `pool.py`, the
  write side — `stub`, `passage`, `claim`, `question`, `answer`, `anchor`,
  `split`, `index` — and the `sources` skill as the method, the `readings`
  skill planning with `kb.py next` and numbering questions as files). What remains: every new reading
  written in the layers; each old topic file split when next touched
  (first step allowed: anchor its passages with ids); then README's Files
  table and *State* column generated from the frontmatter, `SourcesTest`
  covering the layers, `open-questions.md` emptied into `questions/`.
- **Check.** `kb.py check` clean with no warnings; `kb.py coverage wb`
  agrees with `wb-contents.md`; a claim's `cites:` resolve to passages
  with a state; `SourcesTest` passes.

## Later

- **Fortune-telling page** (九星気学 in full): 本命星, 月命星, the year, month
  and day boards, lucky directions and the 五黄殺, 暗剣殺, 本命殺 and 的殺
  directions. All of these can be calculated from the boards; the readings need
  sources. Only calculated positions, never free interpretation.
- Kyūreki: 神吉日, 凶会日, 五墓日, 時下食 (SPEC §3).
- Tibetan: Tsurphu version (SPEC §3).
- Notifications beyond the morning notice ([N1](#n1)).

**Dropped (the owner, 2026-10-10)**: what can neither be used nor change a
weight or a verdict is not planned (T2.23). So 七十二候, seasonal names
naming no works, and the moon's rise and set, which nothing weighs; the
Rishi-star bathing week was a stale entry, built as the sage's seven days
(T2.21).
