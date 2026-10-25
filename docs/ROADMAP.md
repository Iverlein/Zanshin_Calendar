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
| 11 | T2.26 The year's and the month's earth lords | L | WB's almanac writes them at the head of the year and of each month, and ch. 31's pp. 175–221 are unread; Year and Month tabs, their works counted (the owner, 2026-10-11) | D4, how they are counted |
| 12 | H1–H3 The health screen, and the menu's groups collapsible — H1 and H3 built 2026-10-15, H2 open | L | A screen of its own from the menu, with Tibetan medicine beside WB (Dr. Nida Chenagtsang, trusted by the owner): the element's mansions by the life force (left by T2.19), the la's place, vitality and body, the day's illness readings, then WB's illness reckoning; with it the menu's groups collapsible, closed by default, each remembering its last state (H3, S), since the screen is the first of several that lengthen the menu | T2.27; R1 for H2 |
| 13 | P1–P3 Relatives, the obstacles that need them, the bride's departure — P1 built 2026-10-15 | L | The readings T4 left for want of other people's dates, and WB ch. 23's bride's departure, on relatives added to each saved person | P1 first |
| 14 | R The reckonings, a section of their own — R1 built 2026-10-15 | XL | WB's pebble divinations, chapters 20–30, in a menu section as Meditation has; the section is open with the year of age in it (R1), the reckonings one by one follow (R2…) | R1 first; P1 for those that need relatives |
| 15 | U8–U18 The UX review of 2026-10-11 | M | Eleven small items, each S, none changing a side, a tone or a reading: the sky line as one line, the date picker's jump, the brief's rows in the 旧暦 brief's form, the hours in short form, dots that read, second lines that state the fact, the election's civil dates, the calendar toggle, the two briefs alike, the bell's time picker, a first-run line; all eleven built 2026-10-11, blocks 34 to 37 | — |
| 16 | N1 The morning notice — built 2026-10-16 | S | One notification a day with the brief's tone, reason and hours, on the bell's alarms (SPEC §10.9); SPEC §3 moves it into scope | U11, for the hours' short form |
| 17 | A2 Large font and landscape — built 2026-10-16 | M | The page at font scale 1.5 and 2.0, in landscape and on a tablet: labels at 12 sp least, label columns measured, two columns or a capped width, diagrams that scale | U8–U18 and X2, so the rows and the sheets' label column it measures are final |
| 18 | W1 The widget — built 2026-10-16 | M | A widget in two sizes with the day's tone, its reason and the hours, plain RemoteViews rather than Glance; SPEC §10.6 written | A1 (the theme), U8–U18 (the header and rows), U11 (the hours' short form) |
| 19 | X1–X10 The readings' wording | L | Every reading of the Tibetan page's sheets in one shape: the ruling first in a paragraph of 20–60 words, the rest as labelled rows in a fixed order, *How it falls* today's line only, boilerplate once, one vocabulary, the verse's headword as an image; the catalog carries rows and `CatalogTest` holds the shape (X1–X2, block 42, built 2026-10-11; X10 and the year sheet, blocks 49 and 57, built 2026-10-16; X5, block 44, built 2026-10-16; X6, block 45, 2026-10-17; X7, X8 and X9, blocks 46 to 48, and X3–X4, block 43, 2026-10-18, the last); 459 readings, family by family, the Russian with each; no side, tone or list changes | — for X1–X2; X1–X2 for the rest; L3's read-through (block 15) waits on all of it, A1 and A2 on X2's sheet |
| 20 | K1–K6 The 旧暦 page after the expert's review of 2026-10-12 | M | The Russian terms by the scholarly literature (K1), the festivals' names she corrected (K2, built 2026-10-14), the old texts behind the readings (K3, her advice: 暦林問答集 over 簠簋内伝), the claims to settle on the sources (K4, with 天一天上 and 歳徳神 as printed today), the direction gods (K5), every row opening as on the Tibetan page (K6, planned 2026-10-14: the solar term with its reading, details and full ring, 2026-10-16, the 干支 rows, the month and year stars, the date and moon); the purpose and the frame are settled (SPEC §7: the printed almanac's set, read by the old texts; her second answer, 2026-10-14) | D9; a paper 高島暦 (D12) |
| 21 | T2.28 Corrections from the readings | S | Five entries the O readings found otherwise on the scan than the app has them: KP's table, ch. 34's marriage and learning, the 9th hair date, Tuesday's saddling, the horse day's witness; three lunar dates wait on the owner (D11) | — (D11 for the lunar dates) |
| 22 | U19 Pinned works — built 2026-10-21 | S | The works one cares about first in the Works section, left by U6 as a later item | — |
| 23 | R4 Combining the readings | M | Each reckoning's sections joined by WB's own ladder (the verdict of each aspect, the sets, whose aspect and which part of life, the rule for the whole) into a conclusion read for action, with its remedies; planned 2026-10-20, the readings first | RD18–RD28; block 31 |
| 24 | [C1](#c1) The knowledge pool in layers | M | The structure of docs/sources/STRUCTURE.md, decided 2026-10-22 and indexed by `tools/sources/kb.py`, applied to the files: the topic files split into corpus files with passage ids, claims and question files, README's index and State column generated from their frontmatter, `SourcesTest` covering the layers; done file by file as each is next touched, and this item closes the remainder | — |

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
| <a id="block-25"></a>25 | [T2.26](#t2-26) The year's and the month's earth lords | [RD2](#rd2), [RD3](#rd3), [RD4](#rd4), [RD5](#rd5), [RD6](#rd6) | [42](#block-42), the shape its readings are written in ([D4](#t2-26), the owner's choice of how they are counted) | Build block 25: T2.26, the Year and Month tabs from RD2–RD6, their works counted as D4 says. |
| <a id="block-26"></a>26 | [H1](#h1) The health screen + [H3](#h3) The menu's groups collapsible — built 2026-10-15 ([D14](#h1) decided the same day) | [RD1](#rd1), [RD7](#rd7), [RD8](#rd8), [RD9](#rd9), [RD10](#rd10) | [42](#block-42), the shape its readings are written in | Build block 26: H1, the Health screen from RD1 and RD7–RD10, with the element's mansions, and H3, the menu's groups collapsible, closed by default, each remembering its last state. |
| <a id="block-27"></a>27 | [P1](#p1) Relatives — built 2026-10-15 | [RD13](#rd13), [RD14](#rd14), [RD15](#rd15), [RD16](#rd16) | — | Build block 27: P1, the relatives of each saved person. |
| <a id="block-28"></a>28 | [R1](#r) The Reckonings section — built 2026-10-15 | [RD11](#rd11) | — | Build block 28: R1, open the Reckonings section from RD11's inventory, with the year of age moved into it. |
| <a id="block-29"></a>29 | [P2](#p2) The natal trigram and the obstacles — built 2026-10-15 | [RD13](#rd13), [RD14](#rd14) | [27](#block-27), [28](#block-28) | Build block 29: P2, the natal trigram and the spouses' and the dead's obstacles. |
| <a id="block-30"></a>30 | [P3](#p3) The bride's departure | [RD15](#rd15), [RD3](#rd3) | [25](#block-25), [27](#block-27) | Build block 30: P3, the bride's departure for a couple, from RD15. |
| <a id="block-31"></a>31 | [R2](#r)… The reckonings one by one — the birth's parts R2a–R2l (the year's nature, body, mewa, aspects, glory and misfortune, line of births, rise and decline, raising, the precious pebbles) and the marriage match's points R3a–R3m built 2026-10-19; the release build checked over them the same day; R3n–R3q (the hidden and the deeper hidden, the siblings' number, the couple's bad-year pebble, the kinds of wife) built 2026-10-22; R2m the hundred pebbles, R2n the dead's time of death and R2o the moment's trigram and mewa built 2026-10-22 | [RD12](#rd12), [RD13](#rd13), [RD15](#rd15), [RD16](#rd16), [RD17](#rd17), each for its reckoning | [28](#block-28); [27](#block-27) for those that need relatives | Build block 31: the next reckoning of R1's list. |
| <a id="block-32"></a>32 | [H2](#h2) The illness reckoning — H2a, the elements' illnesses, built 2026-10-22; the reckoning itself waits: the board's counts start from "the sky-year's earth" (གནམ་ལོའི་ས), which ch. 25 does not define, most of pp. 421–452 are read only in outline (RD12), and the element count's fifth pebble and shares are open questions 226–228 with no worked example | [RD12](#rd12), [RD8](#rd8) | [26](#block-26), [28](#block-28) | Build block 32: H2, the illness reckoning on the Health screen, from RD12. |
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
| <a id="block-50"></a>50 | [T2.28](#t2-28) Corrections from the readings — built 2026-10-15 but for D11's three lunar dates and D13, which wait on the owner; the year of age's predictive pebble (RD18, D16) built 2026-10-22; the match's entries built with block 31 (R3n–R3q) but point 10, the orphaning month and the moment | [O1](#o1), [O2](#o2), [O3](#o3), [O7](#o7), [O8](#o8) | — (D11 for the three lunar dates) | Build block 50: T2.28, the corrections the O readings found: KP's table, ch. 34's marriage and learning, the 9th hair date, Tuesday's saddling, the horse day's witness; the lunar dates 1, 3 and 6 once D11 is settled. |
| <a id="block-51"></a>51 | [K1](#k1) The 旧暦 terms by the literature (K2 built 2026-10-14) — built 2026-10-19 but for D15, the owner's choices | K1's reading (kyureki.md) | — (D15 for the months, the mansions and 重陽) | Build block 51: K1, the Russian terms by the literature. |
| <a id="block-52"></a>52 | [K4](#k4)… The 旧暦 claims one by one — 二十七宿, the red writing and the 六曜 settled 2026-10-19 | K4's, each for its claim | — (D12 for 天一天上 and the 選日/下段 check) | Build block 52: the next K4 claim whose reading is in kyureki.md. |
| <a id="block-53"></a>53 | [K3](#k3) The old texts behind the 旧暦 readings, in order | K3-R, the 暦林問答集 (kyureki.md) | — (D8) | Build block 53: K3, the old texts' order for the 旧暦 readings as D8 settles it, in SPEC §7.5 and §8.1 and on the 三箇の悪日. |
| <a id="block-54"></a>54 | [K5](#k5) The direction gods | K3-R; K4's 天一神 course for the daily directions | [53](#block-53) (D9) | Build block 54: K5, the year's direction gods as D9 settles it. |
| <a id="block-56"></a>56 | [K6](#k6) Every 旧暦 row opens — built 2026-10-18 | K6-R, the 24 terms' readings (kyureki.md) | — | Build block 56: K6, a sheet for the solar term from K6-R with its reading, details and the full ring, balloons on the 干支 rows and on the date and moon, the month and year stars opening their own sheets. |
| <a id="block-55"></a>55 | [U19](#u19) Pinned works — built 2026-10-21 | — | — | Build block 55: U19, pinned works in the Works section, with a chooser and a preference. |
| <a id="block-57"></a>57 | [Y1](#y1) The year of age read for action — built 2026-10-16 | WB vol. 1, ch. 25, pp. 440–442, the twelve stages' spirits and rites (read in the block, health.md) | — | Built: Y1, the year sheet in the shape with its remedies as rows, what matters most, the stages named as the energy's. |
| <a id="block-58"></a>58 | [R4](#r) Combining the readings: each reckoning's verdicts, its sets read whole, what to do — the match's Together built 2026-10-22; open questions 184–185 answered 2026-10-22 (the hundred's board from KD vol. 3, pp. 716–718; no count of a child's conditions in any text) and the birth's Together built the same day, its twenty-one children not named; the year of age's Together buildable since D17 (2026-10-25, its obstacles graded by Mipham's meetings), its household round waiting on question 258 | [RD18](#rd18), [RD19](#rd19), [RD20](#rd20), [RD21](#rd21), [RD23](#rd23), [RD24](#rd24), [RD25](#rd25), [RD26](#rd26), [RD27](#rd27), [RD28](#rd28), [RD29](#rd29); [RD22](#rd22) with H2 | [31](#block-31) | Build block 58: R4, a Together part atop each reckoning built, from RD18–RD28: the verdicts, the sets, whose aspect and which part of life, the rule for the whole and the remedies gathered. |

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
| <a id="rd51"></a>RD51 | The thirteen circles' small reckonings: misprints and unclear words, on the scan | WB vol. 2, ch. 27, pp. 9, 16, 17, 20, 23–25 (Gser thang reprint I3CN5029 img. 22 for p. 16, image = page + 6) and the notes, pp. 533, 535–538; the doubtful words of the thirty (vol. 1, pp. 455–467, q305) | —; RD50 beside | [31](#block-31) | corpus/wb/ch27.md | Read RD51: on the scan and the reprint, the la's wandering of the snake and horse, the rat and pig (q363), the golden turtle's wood sheep and how a year becomes an element (q364), for whom the four reckonings of «བརྗེ» are (q365), the doubtful words of p. 9, p. 17 (q366) and the thirty's doubtful words and rites (q305). |
| <a id="rd52"></a>RD52 | The illness reckoning's people and animals; the Vaiḍūrya line of the moment's trigram | WB vol. 1, ch. 25, pp. 428–429, 445 (Gser thang reprint I3CN5029, image = page − 5) and the notes, pp. 495–499; for q390 the *Speckled Elements*' *Vaiḍūrya* volume as quoted in YS vol. 2 img. 17 and any print of it in the pool or on BDRC | RD49 | [31](#block-31), [32](#block-32) | corpus/wb/ch25.md, corpus/ys/other-reckonings.md | Read RD52: whether the day's animal or the birth's heads pp. 428–429 (q360), by which relation the pillow-guard is chosen and what «མཐུན་གསུམ་རང་དང་ལྔ» counts (q359), and whether the *Vaiḍūrya*'s tiger, horse and dog triad of the moment's trigram is of the year or the month and what the trigram does on later dates (q390); where no print can be found, write the search down as a dead end. |

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
| <a id="o13"></a>O13 | WB ch. 33's results of the date's trigram and sme ba (vol. 2, pp. 365–368), read on the scan 2026-10-19: works good and bad for each of the eight and the nine, and their remedies; not built. Whether the day's page should weigh or show them beside the boxes, which already weigh the date's trigram and sme ba, is the owner's to decide | [lunar-day-signs.md](sources/lunar-day-signs.md) | — |
| <a id="o14"></a>O14 | WB ch. 35's times to beware for learning (vol. 2, p. 432), read on the scan 2026-10-19: the 1st for all sciences, the 8th, 14th, 16th, 23rd, 29th, 15th and 30th for grammar (Pāṇini's adds the 13th and 28th, the Sārasvata's the 12th and 27th); ch. 34's work 52 and KP box 54 refer to them. Whether the composing-and-learning list takes grammar's dates is the owner's to decide | [white-beryl-ch34.md](sources/white-beryl-ch34.md) | — |
| <a id="o15"></a>O15 | WB ch. 34's *sme ba* of the date, compared with the boxes 2026-10-19: where the box names the same or WB decides against a box's numeral it is built (medicine's four-green; forts' three-blue for the box's 9; the two-black of boxes 44 and 45, read 3 before). The chapter also names, with no box numeral beside them: the five-yellow good for consecration (work 48) and bad for opening the soil (work 9), the two-black and three-blue bad for marriage (work 39), the nine-red good for life and wealth (work 61), the seven-red bad for hunting (work 20). Not joined, since the chapter's *sme ba* were left out when it was built; whether to join them is the owner's to decide | [white-beryl-ch34.md](sources/white-beryl-ch34.md), [kp-activities.md](sources/kp-activities.md) | — |
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

#### <a id="k3"></a>K3 The old texts behind the readings — a reading, then D8

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
- **Block.** [53](#block-53), after K3-R and D8.
- **Done when.** The reading in kyureki.md; D8 settled; SPEC §7.5 and §8.1.

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

#### <a id="k5"></a>K5 The direction gods — the owner's decision

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
  if K4 finds a source. Not planned until D9.
- **Block.** [54](#block-54), after D9 and [53](#block-53) (the old
  text the year sheet is read from).

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

- **D8 The sources' order for the 旧暦 page** (K3): the frame is
  settled (2026-10-14: the printed almanac's set, SPEC §7); which old
  text speaks first among the readings, and what the 簠簋内伝 is — open,
  after the 暦林問答集 is read.
- **D9 The direction gods** (K5): on the page, a year sheet, or not at
  all — open; the year sheet is now the part of the printed set the page
  lacks.
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

#### <a id="t2-26"></a>T2.26 The year's and the month's earth lords — planned 2026-10-11

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
- **Counted** (the owner, 2026-10-11): the works an earth lord names are
  counted in the weighing, not only shown. One that names no works, or
  whose works WB gives no weight, is shown on its tab with a grey dot: it
  has some influence, and WB's weighing does not count it. **D4**, the owner's choice
  before the build, since WB's order of strength (vol. 2, p. 376) places
  no earth lord: their rank (last, with the trigram, the date's mewa and
  the link, as the boxes' entries are, SPEC §5.12 and T2.25, or another);
  whether the date's earth lords already built (T2.13's courses, the
  great black day, the black months and Kikang's, shown and not weighed
  today) are counted by the same rule; and how a work avoided in one
  direction counts, as a work qualified by a direction weighs for neither
  side today (SPEC §5.10).
- **Work.** Read ch. 31, pp. 175–221, on the scans (PLAN.md) into a topic
  file: each earth lord with its course (by the year's or month's animal,
  the season or the year's mewa), its place, works and remedy, and what
  reckons it; build what is calculable. A sheet with two tabs, **Year**
  and **Month**, opened from the year's and the month's balloon at its
  own tab, since the balloons are too small for these: each tab the
  earth lords of the shown year or month on a compass, as Rāhu's and the
  *bla mkhyen*'s, each opening its reading and works; the month balloon's
  long entries move into its tab where the owner says. The day page shows
  the year's and month's earth lords through the works they decide.
- **Block.** [25](#block-25); reads [RD2](#rd2), [RD3](#rd3), [RD4](#rd4), [RD5](#rd5), [RD6](#rd6); waits on D4, and on [42](#block-42) for the shape its readings are written in ([X1](#x1)).
  [30](#block-30) (P3) waits on it.
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
    built 2026-10-22 as R3n; point 10's relations, `RaisingMonth`'s
    orphaning month and the moment not yet ([O16](#o16), open questions
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
   chapters: T2.27 (built 2026-10-15), then T2.26 once D4 is settled.
9. Found 2026-10-12 and 2026-10-13 by the O readings: T2.28, before the
   X blocks that reword the same readings; built 2026-10-15 but for D11 and D13.

#### Not planned

- The day by the clan's element (*rus chen*, WB ch. 31, p. 225): it needs
  a Tibetan patrilineal clan, which the app's readers do not have, and WB
  confines it to reckoning for the dead.
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

### <a id="h2"></a>H2. The illness reckoning — planned 2026-10-11

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

### <a id="p3"></a>P3. The bride's departure — planned 2026-10-11

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
  bride's works ("Choose a day", E2), its works counted as D4 settles
  for the earth lords (T2.26). The first part, the match, is a reckoning
  for R.
- **Block.** [30](#block-30); reads [RD15](#rd15), with [RD3](#rd3)'s sky dog of
  brides; waits on [27](#block-27) (P1) and [25](#block-25) (T2.26, for
  its earth lords and the sky dog).
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
- **R2b The birth's special pebble — built 2026-10-19** (block 31, SPEC
  §5.9.2, §10.5, §10.11): the clan, asked by its element in the person
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
  them), the board's patterns counted, the year of age's beyond Y1's (its obstacles graded by the notes' meeting, D17 decided 2026-10-25, buildable; the household round waits on open question 258),
  the karma reckoning reckoned over the match (open question 210).
- **Blocks.** R1 is [28](#block-28), reading [RD11](#rd11) and waiting on no
  build; R2… is [31](#block-31), each reckoning reading its own
  ([RD12](#rd12), [RD13](#rd13), [RD15](#rd15), [RD16](#rd16), [RD17](#rd17)) and waiting on 28 and, for what needs
  relatives, on [27](#block-27) (P1). [29](#block-29) (P2) and [32](#block-32) (H2) wait
  on R1. R4 is [58](#block-58), reading [RD18](#rd18)–[RD28](#rd28) and
  waiting on [31](#block-31).
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
