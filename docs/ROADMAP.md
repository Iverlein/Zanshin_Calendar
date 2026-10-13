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
| 9 | T2.27 WB's days of the twelve links | S | WB's own reading of each link's day (vol. 2, pp. 351–356), found unread on 2026-10-11: works, directions, illness, remedy; it joins the link's lists | — |
| 10 | A1 Light theme and colour themes | L | The palette is one dark `Palette` object, read about 510 times in 20 files, 29 of them in Canvas code; it becomes a theme chosen in Settings, with a light theme and dark and light alternatives, every tone and printed colour kept legible on each; the checks of the review of 2026-10-11 in its section | — (D5 settled 2026-10-11); U8–U18 and X2 first, so the screenshots are retaken once |
| 11 | T2.26 The year's and the month's earth lords | L | WB's almanac writes them at the head of the year and of each month, and ch. 31's pp. 175–221 are unread; Year and Month tabs, their works counted (the owner, 2026-10-11) | D4, how they are counted |
| 12 | H1–H3 The health screen, and the menu's groups collapsible | L | A screen of its own from the menu, with Tibetan medicine beside WB (Dr. Nida Chenagtsang, trusted by the owner): the element's mansions by the life force (left by T2.19), the la's place, vitality and body, the day's illness readings, then WB's illness reckoning; with it the menu's groups collapsible, closed by default, each remembering its last state (H3, S), since the screen is the first of several that lengthen the menu | T2.27; R1 for H2 |
| 13 | P1–P3 Relatives, the obstacles that need them, the bride's departure | L | The readings T4 left for want of other people's dates, and WB ch. 23's bride's departure, on relatives added to each saved person | P1 first |
| 14 | R The reckonings, a section of their own | XL | WB's pebble divinations, chapters 20–30, in a menu section as Meditation has; the year of age moves there | R1 first; P1 for those that need relatives |
| 15 | U8–U18 The UX review of 2026-10-11 | M | Eleven small items, each S, none changing a side, a tone or a reading: the sky line as one line, the date picker's jump, the brief's rows in the 旧暦 brief's form, the hours in short form, dots that read, second lines that state the fact, the election's civil dates, the calendar toggle, the two briefs alike, the bell's time picker, a first-run line; all eleven built 2026-10-11, blocks 34 to 37 | — |
| 16 | N1 The morning notice | S | One notification a day with the brief's tone, reason and hours, on the bell's alarms (SPEC §10.9); SPEC §3 moves it into scope | U11, for the hours' short form |
| 17 | A2 Large font and landscape | M | The page at font scale 1.5 and 2.0, in landscape and on a tablet: labels at 12 sp least, label columns measured, two columns or a capped width, diagrams that scale | U8–U18 and X2, so the rows and the sheets' label column it measures are final |
| 18 | W1 The widget | M | A Glance widget in two sizes with the day's tone, its reason and the hours; SPEC §10.6 written | A1 (the theme), U8–U18 (the header and rows), U11 (the hours' short form) |
| 19 | X1–X10 The readings' wording | L | Every reading of the Tibetan page's sheets in one shape: the ruling first in a paragraph of 20–60 words, the rest as labelled rows in a fixed order, *How it falls* today's line only, boilerplate once, one vocabulary, the verse's headword as an image; the catalog carries rows and `CatalogTest` holds the shape (X1–X2, block 42, built 2026-10-11); 459 readings, family by family, the Russian with each; no side, tone or list changes | — for X1–X2; X1–X2 for the rest; L3's read-through (block 15) waits on all of it, A1 and A2 on X2's sheet |
| 20 | K1–K5 The 旧暦 page after the expert's review of 2026-10-12 | M | The Russian terms by the scholarly literature (K1), the festivals' names she corrected (K2), which old texts set the page's standard (K3, her advice: 暦林問答集 over 簠簋内伝), five claims to settle on the sources (K4), the direction gods (K5); the purpose is settled (SPEC §7) | the owner's word on K2; D8–D10 |

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
| <a id="block-15"></a>15 | [L3](#l3) Russian read-through — the project's read-through built 2026-10-10; the owner's on the phone open | — | The blocks before it that change wording, [42](#block-42)–[49](#block-49) above all | Build block 15: L3, fix what my Russian read-through on the phone found. |
| <a id="block-16"></a>16 | [L4](#l4) Hosted Weblate | — | [15](#block-15); not before 2026-12-28 | Build block 16: L4, set up Hosted Weblate as docs/weblate.md says. |
| <a id="block-17"></a>17 | [T2.20](#t2-20) The times within the day — built 2026-10-09 | — | — | Build block 17: T2.20, read WB ch. 15 on the sun's terms, then build the second mansion, the skipped yoga, Viṣṭi's span and the sun's terms. |
| <a id="block-18"></a>18 | [T2.21](#t2-21) The month's own entries — built 2026-10-10 | — | — | Build block 18: T2.21, read WB's black months, the month's length, the weekday's rise by month, eclipses and seasonal signs, and build what is calculable. |
| <a id="block-19"></a>19 | [T2.22](#t2-22) The *dbyangs 'char* entries and the twelve links — built 2026-10-10 | — | [17](#block-17) | Build block 19: T2.22, read the *dbyangs 'char*'s rules and the twelve links' and write them on the day as WB's almanac does. |
| <a id="block-20"></a>20 | [T2.23](#t2-23) The five planets and the *byed rtsis* — built 2026-10-10, re-scoped by the owner's rule and accepted | — | — | Build block 20: T2.23, the five planets by Janson and the *byed rtsis* by WB, in the month's heading. |
| <a id="block-21"></a>21 | [U6](#u6) The page in the order of strength — built 2026-10-09 | — | [3](#block-3) | Build block 21: U6, the hours above the brief, the works' verdicts apart from the Almanac, only the deciding voice's dot solid. |
| <a id="block-22"></a>22 | [T2.24](#t2-24) The day animals by WB — built 2026-10-10 | — | — | Build block 22: T2.24, read WB's results of the twelve day animals on the scan and join them to the day animal's lists. |
| <a id="block-23"></a>23 | [T2.25](#t2-25) The Lunar day section by use — built 2026-10-10, released as 2.4.1 | — | — | Build block 23: T2.25, keep in the Lunar day section only what can be used or weighed, the hundred feet in Your day. |
| <a id="block-24"></a>24 | [T2.27](#t2-27) WB's days of the twelve links | [RD1](#rd1) | [42](#block-42), the shape its readings are written in | Build block 24: T2.27, join WB's days of the twelve links (RD1) to the day's link. |
| <a id="block-25"></a>25 | [T2.26](#t2-26) The year's and the month's earth lords | [RD2](#rd2), [RD3](#rd3), [RD4](#rd4), [RD5](#rd5), [RD6](#rd6) | [42](#block-42), the shape its readings are written in ([D4](#t2-26), the owner's choice of how they are counted) | Build block 25: T2.26, the Year and Month tabs from RD2–RD6, their works counted as D4 says. |
| <a id="block-26"></a>26 | [H1](#h1) The health screen + [H3](#h3) The menu's groups collapsible | [RD1](#rd1), [RD7](#rd7), [RD8](#rd8), [RD9](#rd9), [RD10](#rd10) | [42](#block-42), the shape its readings are written in | Build block 26: H1, the Health screen from RD1 and RD7–RD10, with the element's mansions, and H3, the menu's groups collapsible, closed by default, each remembering its last state. |
| <a id="block-27"></a>27 | [P1](#p1) Relatives | [RD13](#rd13), [RD14](#rd14), [RD15](#rd15), [RD16](#rd16) | — | Build block 27: P1, the relatives of each saved person. |
| <a id="block-28"></a>28 | [R1](#r) The Reckonings section | [RD11](#rd11) | — | Build block 28: R1, open the Reckonings section from RD11's inventory, with the year of age moved into it. |
| <a id="block-29"></a>29 | [P2](#p2) The natal trigram and the obstacles | [RD13](#rd13), [RD14](#rd14) | [27](#block-27), [28](#block-28) | Build block 29: P2, the natal trigram and the spouses' and the dead's obstacles. |
| <a id="block-30"></a>30 | [P3](#p3) The bride's departure | [RD15](#rd15), [RD3](#rd3) | [25](#block-25), [27](#block-27) | Build block 30: P3, the bride's departure for a couple, from RD15. |
| <a id="block-31"></a>31 | [R2](#r)… The reckonings one by one | [RD12](#rd12), [RD13](#rd13), [RD15](#rd15), [RD16](#rd16), [RD17](#rd17), each for its reckoning | [28](#block-28); [27](#block-27) for those that need relatives | Build block 31: the next reckoning of R1's list. |
| <a id="block-32"></a>32 | [H2](#h2) The illness reckoning | [RD12](#rd12), [RD8](#rd8) | [26](#block-26), [28](#block-28) | Build block 32: H2, the illness reckoning on the Health screen, from RD12. |
| <a id="block-33"></a>33 | [A1](#a1) Light theme and colour themes, the first four | — | [34](#block-34), [42](#block-42), so the screenshots are retaken once | Build block 33: A1, the palette as a theme chosen in Settings: System, Ink (the default), Paper and Night, with the review's checks, the light screen first. |
| <a id="block-34"></a>34 | [U8](#u8) + [U12](#u12) + [U13](#u13) + [U15](#u15) + [U16](#u16) + [U18](#u18) — the day page — built 2026-10-11 | — | — | Build block 34: the day page's review items, the sky line as one line, dots that read, second lines that state the fact, the calendar toggle, the two briefs alike and the first-run line. |
| <a id="block-35"></a>35 | [U9](#u9) + [U17](#u17) — the pickers — built 2026-10-11 | — | — | Build block 35: U9 and U17, the date picker's year and month jump and the bell's time picker. |
| <a id="block-36"></a>36 | [U10](#u10) + [U11](#u11) — the brief's rows and the hours in short form — built 2026-10-11 | — | — | Build block 36: U10 and U11, the Tibetan brief's rows in the 旧暦 brief's form and the hours row in short form. |
| <a id="block-37"></a>37 | [U14](#u14) The election's civil dates — built 2026-10-11 | — | — | Build block 37: U14, the civil day in every cell of the election's grid and the voices line only where a voice stands beside the deciding one. |
| <a id="block-38"></a>38 | [A1](#a1) Maroon and Sepia | — | [33](#block-33) | Build block 38: A1's Maroon and Sepia, Maroon with its own accent pair and tone set. |
| <a id="block-39"></a>39 | [N1](#n1) The morning notice | — | [36](#block-36) | Build block 39: N1, the morning notice on the bell's alarms, with the hours in U11's short form. |
| <a id="block-40"></a>40 | [A2](#a2) Large font and landscape | — | [34](#block-34), [36](#block-36), [42](#block-42) (the sheets' label column) | Build block 40: A2, the page at font scale 1.5 and 2.0, in landscape and on a tablet. |
| <a id="block-41"></a>41 | [W1](#w1) The widget | — | [33](#block-33), [34](#block-34), [36](#block-36) | Build block 41: W1, the Glance widget in two sizes, in the app's theme. |
| <a id="block-42"></a>42 | [X1](#x1) + [X2](#x2) The shape of a reading, and rows in the catalog — built 2026-10-11, with the lesser black day's example and rule 2's 20-word floor; the examples read by the owner 2026-10-11 | — | — | Build block 42: X1 and X2, the shape rule in SPEC §8.1 with its three examples, the catalog's rows on the sheet and `CatalogTest` holding the shape. |
| <a id="block-43"></a>43 | [X3](#x3) + [X4](#x4) The lunar dates, the weekdays and the goddesses' days | — | [42](#block-42) | Build block 43: X3 and X4, the 30 lunar dates, the 7 weekdays and the 8 goddesses' days in the shape, with their Russian. |
| <a id="block-44"></a>44 | [X5](#x5) The combination and the special days | — | [42](#block-42) | Build block 44: X5, the named combinations, the element pairs, the special days, the burning date and the Vajra Treatise's days in the shape, with their Russian. |
| <a id="block-45"></a>45 | [X6](#x6) The yogas, the karaṇa and the mansions | — | [42](#block-42) | Build block 45: X6, the yogas, the karaṇa and the mansions in the shape, with their Russian. |
| <a id="block-46"></a>46 | [X7](#x7) The earth lords, Rāhu and the month's entries | — | [42](#block-42) | Build block 46: X7, the earth lords, Rāhu's courses and the month's entries in the shape, each *How it falls* row the shown day's line, with their Russian. |
| <a id="block-47"></a>47 | [X8](#x8) Your day | — | [42](#block-42) | Build block 47: X8, Your day's readings in the shape, the app's mechanics left to the notes, with their Russian. |
| <a id="block-48"></a>48 | [X9](#x9) The hours panel | — | [42](#block-42) | Build block 48: X9, the hours panel's readings in the shape, with their Russian. |
| <a id="block-49"></a>49 | [X10](#x10) The rest, and the year sheet | — | [42](#block-42); after [43](#block-43)–[48](#block-48) | Build block 49: X10, the astrologer spirit, the links, the mewa and the year sheet in the shape, `CatalogTest` with no allowance left, store shot 3 retaken. |

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

### Left open in built work

Readings that no block waits on: each would settle or confirm a reading
already built, and is done when it is, or when the search for it is
written down as a dead end (sources/PLAN.md).

| # | What is open | Where | Built in |
| --- | --- | --- | --- |
| <a id="o1"></a>O1 | KP's links left out: box 53's (which half), box 55's numbers, box 50 (KP2 only), "skye" alone (boxes 5, 7, 20), boxes 48 and 60 — done 2026-10-12 (read on the scan in KP and KP2; the table's changes, and box 53's Tiger, Dragon and Ox days and *zon* moved to the good, are in kp-activities.md, *What the table should now carry*, for a build; questions 103–105) | [kp-activities.md](sources/kp-activities.md) | [T2.25](#t2-25) |
| <a id="o2"></a>O2 | Ch. 34: three entries whose verse reads two ways; whether «བཅོ་བརྒྱད་དགུ» names the 9th for marriage; which mansion «བྱ་གཞུག» is — done 2026-10-12 (read on the scan: the 18th and the 9th for marriage, the 1st and 6th, not the 16th, for learning, Friday middling, *bya gzhug* Dhaniṣṭhā in WB's usage; marriage's 9th added and learning's 16th dropped are in white-beryl-ch34.md's O2 section, for a build; questions 106–107) | [white-beryl-ch34.md](sources/white-beryl-ch34.md), *Left out* | T2.6 |
| <a id="o3"></a>O3 | The hair dates' 9th, «མོད་འགྱུར» — done 2026-10-12 (read on the scan in five prints, all «མོད»: "young women become many", by the dictionaries and WB's own «X མོད་འགྱུར»; SN and two Drigung lists give meeting young women; for a build, the catalog's `reading.HairDate.9`, now "Youthfulness", in every language; question 119) | [hair-dates.md](sources/hair-dates.md) | [T2.15](#t2-15) |
| <a id="o4"></a>O4 | Open question 18: the places WB's Mercury and Venus clause compares at an eclipse (vol. 1, p. 64); searched without result 2026-10-10 — done 2026-10-12 (the *Seven Special Qualities* restates the clause: the true sun and Mercury or Venus in one mansion, no 135, a smaller eclipse; mean places ruled out by Blo gros dpal; 135 sixtieths is one sign, WB p. 34; which place and which sun stay open, questions 116–118; the restatement's lines wait for the lent scans of KD and the Sakya volume) | [month-entries.md](sources/month-entries.md), *p. 64's two exceptions* | [T2.23](#t2-23) |
| <a id="o5"></a>O5 | Open question 1: the yoga ranking's «རང་སྐྱེས» line; no reading found; not planned | [open-questions.md](sources/open-questions.md) | — |
| <a id="o6"></a>O6 | WB's passages on the *sri*, from the etext only (the household reckoning, vol. 1 pp. 305–306, and p. 301's *rgan sri*, read on the scan 2026-10-11; p. 301's *dar sri*, pp. 244, 382, 387, 418, 425–427, 458–462 and vol. 2 left) — done 2026-10-12 (from the etext, not checked on the scan: the *dar sri* of the debt, the *chung sri* in the womb and the small grave, the *'phung sri*'s forms, the *dur sri*'s castle, the death-mouth's sri pressed down in skulls, seven new kinds in the table; in sri.md, *WB's other passages on the sri (O6)*; questions 112–115) | [sri.md](sources/sri.md) | the *sri*'s name |
| <a id="o7"></a>O7 | The weekdays' verses, by machine with corrections, not by eye syllable by syllable — done 2026-10-13 (pp. 308–312 read by eye on the scan, with WBZ img. 1058–1064 and the Gser thang reprint for the doubtful stacks; all seven verses and the closing verse quoted in full with their illness, theft, remedy and war lines; for a build, Tuesday's good list gains "saddling horses and mules" («སྒ་རྒྱག», the same stack as Wednesday's «རྟ་དྲེལ་སྒ་རྒྱག»; the vowel of rgyag cannot be told) in weekdays.md, *O7: what the reading by eye changes*; Wednesday's «དུར་?ོ» stays out, unsettled; questions 132–138) | [weekdays.md](sources/weekdays.md) | T2 |
| <a id="o8"></a>O8 | The date animal's earth lords, by machine with witnesses, not by eye syllable by syllable — done 2026-10-12 (pp. 223–226 read by eye on the scan, with the Gser thang reprint at 1905 px and WBZ for the doubtful stacks; every seat, hearth god and witness stands; for a build, the horse day's witness adds "on the journey with the corpse no horse, the funeral door and the horse's back avoided" in earth-lords.md, *O8: what the reading by eye changes*; the chief earth lord's forms and remedy and the other remedies quoted in full; questions 120–125) | [earth-lords.md](sources/earth-lords.md) | T2 |
| <a id="o9"></a>O9 | The four times of Māra's manual — done 2026-10-13: three manuals read on the scan (Rin chen gter mdzod vol. 54 p. 235, Sgrub thabs kun btus vol. 8 p. 698, Gdams ngag mdzod vol. 2 pp. 121–122): the four are midnight, noon and the rays of the rising and setting sun, moments not spans; the Red Tārā manual makes them the breaks between sessions; questions 126–131 | [weighing.md](sources/weighing.md) | [T2.4](#t2-4) |
| <a id="o10"></a>O10 | Topic files that give a passage in English only, or only its first lines: the Tibetan of every passage read written out, checked on the scan (the owner, 2026-10-11; [sources/PLAN.md](sources/PLAN.md), *The knowledge pool*), file by file | [sources/README.md](sources/README.md)'s index | all |

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
- **Done when.** The table in kyureki.md; `CatalogTest`; the owner's
  read-through of the Russian (L3, block 15), which waits on this for the
  旧暦 page.

#### <a id="k2"></a>K2 The festivals' names — S, waits for the owner's word

- **Her corrections.** 七夕: «Танабата» (the gloss «вечер седьмого дня»
  struck out); 重陽の節句: «праздник двойной девятки», not «двойного ян»;
  旧正月: «лунный Новый год»; 彼岸の中日: struck out, the day is simply
  called 秋分 or 春分. Marked "?" without a proposal: 人日の節句 («праздник
  дня людей»), 社日 («день божества земли»).
- **Work.** `Festival.七夕`, `Festival.重陽の節句`, `Festival.旧正月` in
  `texts_ru.properties`; the 雑節 row 彼岸の中日 either dropped from the
  page (the 節気 row already shows the equinox that day) or renamed; 人日
  and 社日 asked back. Each is a user-facing text, so the owner's word
  first; «лунный» is a usage, not a
  description (the calendar is lunisolar), to be weighed with K1.
- **Done when.** `CatalogTest`; both languages on the emulator; the
  dossier's glossary.

#### <a id="k3"></a>K3 Which old texts set the page's standard — decision, then readings

- **Gap.** The purpose (SPEC §7) wants the old rules and readings; the
  rules today come from modern digests (Japanese Wikipedia after 岡田・
  阿久根 1993, koyomi8, Todan's こよみ博物館, the NAOJ), and the one old text
  read on its scan is the 簠簋内伝 (三箇の悪日). The Tibetan page has the
  White Beryl above all (SPEC §8.1); the 旧暦 page names no such text.
  Her advice: the 簠簋内伝 is better not taken as a source, the 暦林問答集
  can be relied on.
- **Work.** A reading, K3-R: the 暦林問答集, identified in the NDL catalogue
  when the reading starts (edition, author, date never from memory) and
  read on its scan: what it says of each
  annotation the app shows, its three bad days against the 簠簋内伝's
  table, and whether it ranks the annotations (none expected: question
  6). Then the owner decides the order — the 暦林問答集 first, the 簠簋内伝
  as a witness or a named other view, the modern digests where the old
  texts are silent — and SPEC §8.1 gets the 旧暦 rule beside WB's. Until
  then the 簠簋内伝 stays the 三箇の悪日's source, named as its own.
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
  K5's.
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
  is built that cannot be used. Daily 方位 (天一神's course, 日遊神) only
  if K4 finds a source. Not planned until D9.

#### Owner decisions

- **D8 The sources' order for the 旧暦 page** (K3): which text ranks
  first, and what the 簠簋内伝 is — open, after the 暦林問答集 is read.
- **D9 The direction gods** (K5): on the page, a year sheet, or not at
  all — open.
- **D10 What to ask the expert to work on first**, her question before
  pricing: the terms (K1, K2), the calendar rules (K4's claims), the rare
  annotations (question 8) or the direction gods (K5) — open; the answers
  to her four questions are drafted in kyureki.md, *Her questions, and
  the answers*.

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
  mansions), which would put a second row on every day: planned on the
  Health screen (H1, 2026-10-11); the mansion of
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

#### <a id="t2-27"></a>T2.27 WB's days of the twelve links — planned 2026-10-11

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
   chapters: T2.27, then T2.26 once D4 is settled.

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
  chooser and a preference.

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

#### <a id="x3"></a>X3 The lunar dates

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
- **Block.** [43](#block-43); no reading; builds on [42](#block-42).
- **Done when.** The 30 readings and their Russian; dates 1, 4, 8 and
  15 on the emulator in both languages; `CatalogTest` without the
  family's allowance.

#### <a id="x4"></a>X4 The weekdays and the goddesses' days

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
- **Block.** [43](#block-43) with X3; builds on [42](#block-42).
- **Done when.** The 7 and the 8 with their Russian; Sunday and the
  fire trigram's day on the emulator in both languages; `CatalogTest`
  without the family's allowance.

#### <a id="x5"></a>X5 The combination and the special days

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

#### <a id="x6"></a>X6 The yogas, the karaṇa and the mansions

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

#### <a id="x7"></a>X7 The earth lords, Rāhu and the month's entries

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
- **Block.** [46](#block-46); no reading; builds on [42](#block-42).
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

#### <a id="x8"></a>X8 Your day

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

#### <a id="x9"></a>X9 The hours panel

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

#### <a id="x10"></a>X10 The rest, and the year sheet

- **Gap.** The astrologer spirit's day reading (190 words) explains its
  count before and after its "Today"; the twelve links (175) and the
  date's mewa give the table's rule before the day's link; the year
  sheet's readings are verses (the pebbles, the nine-multiples, the
  harsh years, the sectors) with their remedies inside them.
- **Work.** The astrologer spirit, the links and the mewa to the shape,
  the count as *How it falls*; the year sheet's verses stay verses (D6),
  each with its ruling line first and its remedy as a row, uniform where
  possible, with the hours' and the day's readings. The festivals and
  the special dates were checked in X5.
- **Block.** [49](#block-49); no reading; builds on [42](#block-42) and
  comes last, as the lightest.
- **Done when.** The readings with their Russian; the astrologer spirit's
  row, the link's and the year sheet on the emulator in both languages;
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

### <a id="h1"></a>H1. The health screen — planned 2026-10-11

- **What WB has.** Read on the scan where marked; the rest found in the
  etext:
  - the element's mansions by the life force (vol. 2, p. 330, tables
    pp. 345–346, read: [personal-mansions.md](sources/personal-mansions.md),
    *By element*): the own, mother, friend, child and enemy quarters and
    the great and small grave mansions; the same table's weekdays are
    built (T2.19);
  - the la's place in the body by date (vol. 2, pp. 303–305, read and
    built, T2.16, in the lunar date's sheet), and the hair dates (p. 404,
    built, T2.15);
  - the vitality and body of the date and the hour against the birth
    year, the divination of health (built, SPEC §5.9: Your day and the
    hours panel);
  - the illness parts of the date's, weekday's, link's and day animal's
    readings (vol. 2, pp. 297–312, 351–359; the links' with
    [T2.27](#t2-27)), and the trigram's goddesses (vol. 1, pp. 449–450,
    built);
  - bloodletting and moxa in the readings of the year of age's mewa
    (vol. 1, pp. 403–405), to be checked against the year sheet (T4);
  - the medical works the weighing already decides (taking and starting
    medicine, treatment, surgery, bloodletting, making medicine:
    `Activities.kt`).
- **Work.** First a reading pass, WB (vol. 1, ch. 20, 21 and 25; vol. 2,
  ch. 33) and Dr. Chenagtsang's works, for the movements of the la and
  the life force (*srog*) through the body and for health and illness by
  date, month, hour and age, into `docs/sources/health.md`, with what is
  calculable and whose it is. Then the Health screen from the menu, in
  tabs by subject as the reading sorts them: with a birth date, the day's
  mansion as one of the element's quarters for the life force with
  p. 330's reading, vitality and body (moved from Your day) and the la's
  place; for everyone, the day's illness readings and the medical works'
  sides as the weighing gives them; what the medical sources add, with
  the grey dot where WB does not reckon it. Your day links to the screen.
  Whether the enemy and grave mansions are absolute as the enemy weekday
  is (E6) is settled from the text: p. 338 names the weekday only.
- **Block.** [26](#block-26); reads [RD1](#rd1), [RD7](#rd7), [RD8](#rd8), [RD9](#rd9), [RD10](#rd10); builds on
  [42](#block-42) for the shape its readings are written in ([X1](#x1)).
- **Done when.** Vectors for the test birth date (1976-06-01, life force
  earth); `CatalogTest`; SPEC §5.8, §8.3, §10.3, §10.5; both languages
  on the emulator; one line on the screen that its readings are the
  tradition's, not medical advice.

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

### <a id="h3"></a>H3. The menu's groups collapsible — planned 2026-10-11

- **Gap.** The menu (SPEC §10.5) is one column in three groups, Calendar,
  Practice and Settings, with About & sources at the foot, and today it
  fits a phone screen. The Health screen (H1) adds a row, Reckonings (R1)
  a group with a screen for each reckoning built, the themes (A1) a
  settings row: the column scrolls, and the row wanted is below the fold.
  The owner (2026-10-11): the groups collapsible, collapsed by default,
  each remembering the state it was last left in.
- **Work.** Each group's small-capital label becomes a header row that
  opens and closes its group, with a chevron that turns to show the
  state; the rows fold with an animation of the height; About & sources
  stays alone at the foot, always shown, outside any group. On a fresh
  install every group is closed; the state is kept per group in the
  preferences (`Settings.kt`, one key per group, as the first-run
  counters are), so a group opened stays open across the menu's closing
  and the app's restart, and one closed stays closed, each apart from the
  others. A new group (R1) starts closed as well. The page's calendar
  toggle (U15) switches the calendar, so a closed Calendar group hides
  nothing needed daily. Screen readers get each header as a button with
  its expanded state. No reading: the headers are the groups' present
  labels, the only new interface text the headers' descriptions.
  SPEC §10.5.
- **Block.** [26](#block-26), with H1, the first item that lengthens the
  menu; no reading; waits on no build.
- **Done when.** A fresh install on the emulator opens the menu with every
  group closed and About & sources shown; a group opened stays open
  across the menu's closing and a restart, one closed stays closed, each
  apart from the others; TalkBack reads each header with its state; both
  languages; `TranslationsTest`; SPEC §10.5; store shot 5 (the menu)
  retaken in both languages, its caption in `screenshots.toml` saying
  the groups fold, and `tools/emulator/store_shots.py` opening the
  groups the shot shows.

## People

### <a id="p1"></a>P1. Relatives of a saved person — planned 2026-10-11

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

### <a id="p2"></a>P2. The natal trigram and the spouses' and the dead's obstacles — planned 2026-10-11

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

### <a id="r"></a>R. The pebble divinations, a section of their own — planned 2026-10-11

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
  draw them (○ ×).
- **R2… One item per reckoning**, in R1's order, likely: the birth
  (ch. 22, with P2), the marriage match (ch. 23's first part, with P1),
  the reckoning for the dead (with P1), the thirty starting points
  (ch. 26), the *dpyad lding* (ch. 27), the moment (ch. 30, from the clock
  time of the question, if R1 finds it calculable). The illness reckoning
  (ch. 25) is built with H2 and listed here too.
- **Blocks.** R1 is [28](#block-28), reading [RD11](#rd11) and waiting on no
  build; R2… is [31](#block-31), each reckoning reading its own
  ([RD12](#rd12), [RD13](#rd13), [RD15](#rd15), [RD16](#rd16), [RD17](#rd17)) and waiting on 28 and, for what needs
  relatives, on [27](#block-27) (P1). [29](#block-29) (P2) and [32](#block-32) (H2) wait
  on R1.
- **Done when** (each item): its readings in the catalog with WB as their
  source; vectors from Dorje's charts where they exist; `CatalogTest`;
  SPEC; both languages on the emulator; the release build checked.

## Appearance

### <a id="a1"></a>A1. Light theme and colour themes — planned 2026-10-11

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

### <a id="a2"></a>A2. Large font and landscape — planned 2026-10-11

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

## Widget and notices

Two surfaces outside the app's screens, both reading the day as the page
does (`DaySummary`, `DayInfo`), nothing weighed anew, no reading. SPEC §3
lists notifications under Later and §10.6 defers the widget; each item
changes that when built.

### <a id="n1"></a>N1. The morning notice — planned 2026-10-11

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

### <a id="w1"></a>W1. The widget — planned 2026-10-11

- **Gap.** SPEC §10.6 defers the widget's sizes and content. For a
  calendar read once a morning the home screen is the main surface, and
  the day's tone, reason and hours are the facts read.
- **Work.** A Glance widget in two sizes, for the calendar last viewed:
  small, the civil date, the calendar's day and the tone with its reason;
  wide, with [U11](#u11)'s hours strip and the present hour, and on the
  旧暦 the rokuyō and the day mark. A tap opens the app on today. Updated
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
