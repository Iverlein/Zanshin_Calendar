/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.stringResource
import zanshin.app.DayInfo
import zanshin.app.Labels
import zanshin.app.LocalLabels
import io.github.iverlein.zanshin.R
import zanshin.core.kyureki.Band
import zanshin.core.kyureki.Choku
import zanshin.core.kyureki.DayMark
import zanshin.core.kyureki.Kigaku
import zanshin.core.kyureki.KyuSei
import zanshin.core.kyureki.Kyureki
import zanshin.core.kyureki.KyurekiDay
import zanshin.core.kyureki.Rekichu
import zanshin.core.kyureki.Kanshi
import zanshin.core.kyureki.RekichuDay
import zanshin.core.kyureki.Senjitsu
import zanshin.core.kyureki.Shuku
import zanshin.core.kyureki.Tone
import zanshin.core.texts.Catalog
import zanshin.core.texts.Reading
import zanshin.core.texts.DaySummary
import zanshin.core.texts.Texts
import zanshin.core.texts.rokuyoTone
import zanshin.core.texts.rokuyoTimes
import zanshin.core.tibetan.Animal
import zanshin.core.texts.Activity
import zanshin.core.texts.KyurekiElection
import zanshin.core.texts.toneOf

/**
 * The 旧暦 view of one day with its almanac annotations (SPEC §10.4). [birthStar]
 * adds the personal 九星気学 row when the owner has switched it on. A work in the
 * breakdown or a reading's lists opens its 旧暦 election through [onElect] (ROADMAP E5).
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun KyurekiPage(
    info: DayInfo,
    birthStar: KyuSei? = null,
    modifier: Modifier = Modifier,
    /** Shows the first-run line under the brief (ROADMAP U18); a tap on it calls this. */
    onFirstRun: (() -> Unit)? = null,
    onElect: (Activity) -> Unit = {},
) {
    val day = info.kyureki
    val rk = info.rekichu
    val accent = Palette.vermilion
    var monthOpen by remember(day.date) { mutableStateOf(false) }
    var dateOpen by remember(day.date) { mutableStateOf(false) }
    var sheet by remember(day.date) { mutableStateOf<Annotation?>(null) }
    var summaryOpen by remember(day.date) { mutableStateOf(false) }
    val summary = remember(day.date, birthStar) { DaySummary.of(day, rk, birthStar) }
    val kanjiStyle = TextStyle(fontFamily = Mincho, fontWeight = FontWeight.Bold, color = Palette.text)
    val labels = LocalLabels.current
    val monthLabel = labels.month(day.month, day.leapMonth)
    val rokuyoTimes = rokuyoTimes(day.rokuyo)
    val namesTimes = rokuyoTimes.first.isNotEmpty() || rokuyoTimes.second.isNotEmpty()
    val rokuyo = Annotation(
        day.rokuyo.kanji,
        "${day.rokuyo.romaji} — ${day.rokuyo.english}",
        rokuyoTone(day.rokuyo),
        Texts.ROKUYO[day.rokuyo],
        diagram = {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                if (namesTimes) Centered { DiagramFrame { DayArc(rokuyoTimes, 220.dp) } }
                RokuyoStrip(day.rokuyo)
            }
        },
    )

    val lunarMonth = remember(day.date) { Kyureki.monthOf(day.date) }
    val moonAge = remember(day.date) { Kyureki.moonAge(day.date) }
    val dateDescription = stringResource(R.string.desc_kyu_date, day.day)
    val dateRows = listOf(
        BalloonRow(stringResource(R.string.row_moon_age), stringResource(R.string.kyu_moon_age, String.format(labels.locale, "%.1f", moonAge))),
        BalloonRow(stringResource(R.string.row_phase), labels.moonPhase(info.moonElongation)),
        BalloonRow(stringResource(R.string.row_month_length), stringResource(if (lunarMonth.days == 30) R.string.kyu_month_long else R.string.kyu_month_short)),
        BalloonRow(stringResource(R.string.row_gregorian), day.date.format(labels.shortDateYear)),
    )
    val term = termAnnotation(day, rk, labels)

    PageColumns(modifier, top = {
        for (festival in listOfNotNull(day.festival, day.gregorianFestival)) {
            val a = Annotation(festival.kanji, "${festival.romaji} — ${festival.english}", Tone.GOOD, Texts.JAPANESE_FESTIVAL[festival.kanji])
            Row(
                Modifier.clickable(role = Role.Button) { sheet = a },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(14.dp),
            ) {
                FestivalGlyph(festival.kanji, accent, 56.dp)
                Column {
                    Text(festival.kanji, style = kanjiStyle.copy(fontSize = 30.sp, color = accent))
                    Text("${festival.romaji} — ${festival.english}", style = body.copy(fontSize = 14.sp, color = Palette.muted))
                }
            }
        }

        Row(verticalAlignment = Alignment.Top) {
            Column(Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(stringResource(R.string.kyu_day), style = body.copy(fontSize = 14.sp, color = Palette.muted))
                    // The date and the moon open one balloon of facts (ROADMAP K6): the moon's age, as the almanac
                    // prints it beside the date, its phase, the month's length and the Gregorian date.
                    Box {
                        Text(
                            "${day.day}",
                            style = TextStyle(fontFamily = Mincho, fontWeight = FontWeight.Medium, fontSize = 120.sp, letterSpacing = (-4).sp, color = Palette.text),
                            modifier = Modifier
                                .clickable(role = Role.Button, onClickLabel = stringResource(R.string.action_meaning)) { dateOpen = !dateOpen }
                                .semantics { contentDescription = dateDescription }
                                .capBox(120.sp),
                        )
                        if (dateOpen) Balloon(dateRows) { dateOpen = false }
                    }
                }
                BalloonText(
                    text = monthLabel,
                    style = body.copy(fontSize = 22.sp, fontWeight = FontWeight.Medium),
                    open = monthOpen,
                    onToggle = { monthOpen = !monthOpen },
                    description = stringResource(R.string.kyu_month_description, monthLabel, day.day),
                    rows = listOf(
                        BalloonRow(stringResource(R.string.row_traditional_name), "${day.monthName} · ${day.monthNameRomaji}"),
                        BalloonRow(stringResource(R.string.row_meaning), day.monthNameEnglish),
                        BalloonRow(stringResource(R.string.row_year), "${day.year}"),
                    ),
                )
            }
            Box(Modifier.clickable(role = Role.Button, onClickLabel = stringResource(R.string.action_meaning)) { dateOpen = !dateOpen }) {
                MoonGlyph(info.moonElongation, 64.dp, labels.moonPhase(info.moonElongation))
            }
        }

        Row(
            Modifier.clickable(role = Role.Button) { sheet = rokuyo },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(day.rokuyo.kanji, style = kanjiStyle.copy(fontSize = 72.sp, color = accent).tight(1.0f))
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(day.rokuyo.romaji, style = body.copy(fontSize = 20.sp, fontWeight = FontWeight.SemiBold))
                Text(stringResource(R.string.kyu_rokuyo_line, day.rokuyo.english), style = body.copy(fontSize = 13.sp, color = Palette.muted))
                if (namesTimes) DayArc(rokuyoTimes, 76.dp, Modifier.padding(top = 4.dp))
            }
        }

        rk.mark?.let { mark ->
            Row(
                Modifier.clickable(role = Role.Button) { sheet = senjitsuAnnotation(mark.senjitsu) },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                DayMarkGlyph(mark, Palette.text, 12.dp)
                Text(mark.senjitsu.kanji, style = kanjiStyle.copy(fontSize = 20.sp))
                Text(
                    when (mark) {
                        DayMark.BLACK -> stringResource(R.string.kyu_black_day)
                        DayMark.PARDON -> stringResource(R.string.kyu_pardon_day)
                    },
                    style = body.copy(fontSize = 14.sp, color = Palette.muted),
                )
            }
        }

        BriefRow(summary) { summaryOpen = true }
        onFirstRun?.let { FirstRunLine(stringResource(R.string.first_run_kyureki), it) }

        // The term's box opens its sheet: the reading, how it was worked out and the whole ring (ROADMAP K6).
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .clickable(role = Role.Button, onClickLabel = stringResource(R.string.action_meaning)) { sheet = term }
                .background(Palette.surface, RoundedCornerShape(14.dp))
                .border(1.dp, Palette.line, RoundedCornerShape(14.dp))
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TermRing(day.currentTerm, 48.dp)
            Spacer(Modifier.width(14.dp))
            // The term and its end on one line; at a large font the end goes under the term (ROADMAP A2).
            FlowRow(
                Modifier.weight(1f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalArrangement = Arrangement.spacedBy(6.dp),
                itemVerticalAlignment = Alignment.CenterVertically,
            ) {
                Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(day.currentTerm.kanji, style = kanjiStyle.copy(fontSize = 26.sp))
                    Text(day.currentTerm.romaji, style = body, modifier = Modifier.padding(bottom = 3.dp))
                }
                if (day.termBeginning != null) {
                    Text(
                        stringResource(R.string.kyu_begins_today),
                        style = body.copy(fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Palette.onAccent),
                        modifier = Modifier.background(accent, RoundedCornerShape(12.dp)).padding(horizontal = 10.dp, vertical = 4.dp),
                    )
                } else {
                    Text(stringResource(R.string.kyu_until, day.nextTermStart.minusDays(1).format(labels.shortDate)), style = body.copy(fontSize = 14.sp, color = Palette.muted))
                }
            }
        }

    }) {
        SectionTitle(stringResource(R.string.section_choku))
        Column {
            AnnotationRow(chokuAnnotation(rk.choku, labels)) { sheet = it }
        }

        SectionTitle(stringResource(R.string.section_shuku))
        Column {
            AnnotationRow(shukuAnnotation(rk.shuku, rk, labels)) { sheet = it }
        }

        SenjitsuSection(stringResource(R.string.section_kagedan), rk, Band.KAGEDAN, labels) { sheet = it }
        SenjitsuSection(stringResource(R.string.section_senjitsu), rk, Band.SENJITSU, labels) { sheet = it }
        SenjitsuSection(stringResource(R.string.section_ennichi), rk, Band.ENNICHI, labels) { sheet = it }

        if (rk.zassetsu.isNotEmpty()) {
            SectionTitle(stringResource(R.string.section_zassetsu))
            Column {
                rk.zassetsu.forEach { z ->
                    AnnotationRow(Annotation(z.kanji, "${z.reading} — ${z.english}", Tone.NEUTRAL, Texts.ZASSETSU[z])) { sheet = it }
                }
            }
        }

        SectionTitle(stringResource(R.string.section_kanshi))
        FactColumn(listOf(R.string.row_day, R.string.row_year, R.string.row_solar_month).map { stringResource(it) }) {
            val yearKanshi = Kanshi.ofYear(day.year)
            val branch = Kanshi(rk.setsuBranch)
            // Each 干支 row opens a balloon of what its glyphs say (ROADMAP K6): the stem's element and polarity, the
            // branch's animal, the pair's place in the sixty; the solar month's span and what is counted by it.
            FactRow(
                stringResource(R.string.row_day), rk.dayKanshi.kanji, "${rk.dayKanshi.reading} — ${rk.dayKanshi.english}",
                tibetan = false, kanji = true, lead = { KanshiGlyphs(rk.dayKanshi) }, balloon = kanshiRows(rk.dayKanshi, labels),
            )
            FactRow(
                stringResource(R.string.row_year), yearKanshi.kanji, "${yearKanshi.reading} — ${yearKanshi.english}",
                tibetan = false, kanji = true, lead = { KanshiGlyphs(yearKanshi) }, balloon = kanshiRows(yearKanshi, labels),
            )
            val setsuEnd = remember(rk.setsuStart) { Rekichu.setsuOf(rk.setsuStart.plusDays(40)).second.minusDays(1) }
            FactRow(
                stringResource(R.string.row_solar_month),
                "${branch.kanji.drop(1)}月",
                stringResource(R.string.kyu_solar_month, Catalog.text("Branch.${branch.branch}"), rk.setsuStart.format(labels.shortDate)),
                tibetan = false,
                kanji = true,
                lead = { KanshiGlyphs(branch, element = false) },
                balloon = listOf(
                    branchRow(branch.branch, labels),
                    BalloonRow(stringResource(R.string.row_from), rk.setsuStart.format(labels.shortDate)),
                    BalloonRow(stringResource(R.string.row_until), setsuEnd.format(labels.shortDate)),
                    BalloonRow(stringResource(R.string.row_counts), stringResource(R.string.kyu_solar_month_counts)),
                ),
            )
        }

        SectionTitle(stringResource(R.string.section_stars))
        fun star(s: KyuSei, gloss: String) = Annotation(s.kanji, gloss, Tone.NEUTRAL, Texts.KYUSEI[s], diagram = { DiagramFrame { StarBoardDetail(s, 288.dp) } })
        val dayStar = star(rk.dayStar, stringResource(R.string.kyu_day_star, rk.dayStar.english))
        // The month and year stars open their own sheets, the board set to that star (ROADMAP K6).
        val monthStar = star(rk.monthStar, stringResource(R.string.kyu_month_star, rk.monthStar.english))
        val yearStar = star(rk.yearStar, stringResource(R.string.kyu_year_star, rk.yearStar.english))
        Row(
            Modifier.fillMaxWidth().clickable(role = Role.Button) { sheet = dayStar },
            horizontalArrangement = Arrangement.spacedBy(18.dp),
        ) {
            StarBoard(rk.dayStar, rk.monthStar, rk.yearStar, 120.dp)
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                StarLine(stringResource(R.string.row_day_star), null, rk.dayStar)
                StarLine(stringResource(R.string.row_month_star), "月", rk.monthStar) { sheet = monthStar }
                StarLine(stringResource(R.string.row_year_star), "年", rk.yearStar) { sheet = yearStar }
            }
        }
        if (birthStar != null) {
            val a = Kigaku.affinity(birthStar, rk.dayStar)
            AnnotationRow(
                Annotation(
                    a.relation.kanji,
                    "${a.relation.reading} — ${a.relation.english}",
                    a.relation.tone,
                    Texts.KIGAKU[a.relation],
                    subtitle = stringResource(R.string.kyu_birth_star, birthStar.english, a.cycleEnglish),
                ),
            ) { sheet = it }
        }
        val ehouLine = stringResource(R.string.kyu_ehou, rk.ehou.english)
        AnnotationRow(
            Annotation(
                "恵方",
                ehouLine,
                Tone.GOOD,
                Texts.EHOU[rk.ehou],
                subtitle = "${rk.ehou.kanji} — ${rk.ehou.english}",
                lead = { Compass(rk.ehou.bearing, 56.dp, ehouLine) },
                diagram = { DiagramFrame { Compass(rk.ehou.bearing, 160.dp, ehouLine) } },
            ),
        ) { sheet = it }
    }

    sheet?.let { ReadingSheet(it, onElect = onElect, offered = KyurekiElection.OFFERED) { sheet = null } }
    if (summaryOpen) DaySummarySheet(summary, onElect = onElect) { summaryOpen = false }
}

/**
 * The solar term's sheet (ROADMAP K6): its reading, how it was worked out this year, and the whole ring with
 * today's place in the term.
 */
private fun termAnnotation(day: KyurekiDay, rk: RekichuDay, labels: Labels): Annotation {
    val t = day.currentTerm
    val days = (day.nextTermStart.toEpochDay() - day.currentTermStart.toEpochDay()).toInt()
    val today = (day.date.toEpochDay() - day.currentTermStart.toEpochDay()).toInt() + 1
    // A 節 opens its solar month; a 中 falls in the one its 節 opened.
    val monthStart = if (t.setsu) day.currentTermStart else rk.setsuStart
    val next = t.next
    return Annotation(
        t.kanji,
        "${t.romaji} — ${t.english}",
        Tone.NEUTRAL,
        Texts.SOLAR_TERM[t],
        details = listOf(
            labels.string(R.string.detail_term_kind) to labels.string(if (t.setsu) R.string.kyu_term_setsu else R.string.kyu_term_chu),
            labels.string(R.string.detail_sun_longitude) to "${t.longitude}°",
            labels.string(R.string.detail_term_began) to day.currentTermStart.format(labels.shortDateYear),
            labels.string(R.string.detail_term_ends) to day.nextTermStart.minusDays(1).format(labels.shortDateYear),
            labels.string(R.string.detail_term_today) to labels.string(R.string.kyu_term_day, today, days),
            labels.string(R.string.row_solar_month) to "${Kanshi.branchKanji(t.branch)}月 · " +
                labels.string(R.string.kyu_solar_month, Catalog.text("Branch.${t.branch}"), monthStart.format(labels.shortDate)),
            labels.string(R.string.detail_next_term) to labels.string(R.string.kyu_next_term, "${next.kanji} ${next.romaji} — ${next.english}", day.nextTermStart.format(labels.shortDate)),
        ),
        diagram = { DiagramFrame { TermRingDetail(t, today, days, 280.dp) } },
    )
}

/** A 干支's balloon: its stem with element and polarity, its branch with the animal, its place in the sixty. */
private fun kanshiRows(k: Kanshi, labels: Labels) = listOf(
    BalloonRow(labels.string(R.string.row_stem), "${k.stemKanji} ${k.stemReading} · ${Catalog.text("Stem.${k.stem}")}"),
    branchRow(k.branch, labels),
    BalloonRow(labels.string(R.string.row_in_sixty), labels.string(R.string.kyu_in_sixty, labels.ordinal(k.index + 1))),
)

private fun branchRow(branch: Int, labels: Labels) =
    BalloonRow(labels.string(R.string.row_branch), "${Kanshi.branchKanji(branch)} ${Kanshi.branchReading(branch)} · ${Catalog.text("Branch.$branch")}")

private fun chokuAnnotation(c: Choku, labels: Labels) =
    Annotation(c.kanji, labels.string(R.string.kyu_choku_gloss, c.reading, c.english), c.tone, Texts.CHOKU[c], diagram = { DiagramFrame { ChokuDial(c, 240.dp) } })

/**
 * The day's mansion; its good list does not count where stem and branch are not good (SPEC §7.5, the
 * 暦林問答集 §64), and the row says so.
 */
private fun shukuAnnotation(s: Shuku, rk: RekichuDay, labels: Labels): Annotation {
    val reading = Texts.SHUKU[s]
    val gloss = labels.string(R.string.kyu_shuku_gloss, s.reading, s.english)
    val held = !rk.goodByStemAndBranch && reading?.goodKeys?.isNotEmpty() == true
    return Annotation(
        "${s.kanji}宿", gloss, toneOf(reading), reading,
        subtitle = if (held) labels.string(R.string.kyu_shuku_held, gloss) else null,
        details = if (held) listOf(labels.string(R.string.detail_shuku_held) to labels.string(R.string.detail_shuku_held_why)) else emptyList(),
        diagram = { DiagramFrame { ShukuRing(s, 280.dp) } },
    )
}

private fun senjitsuAnnotation(s: Senjitsu) = Annotation(s.kanji, "${s.reading} — ${s.english}", s.tone, Texts.SENJITSU[s])

/**
 * An annotation of the lower band or the selected days, with the band's own
 * rules (SPEC §7.5): set aside by 受死日, 十死日 or a good day, or 歳下食
 * made heavier by another bad day; the row says so and the sheet names which.
 */
private fun senjitsuAnnotation(s: Senjitsu, rk: RekichuDay, labels: Labels): Annotation {
    fun name(o: Senjitsu) = "${o.kanji} — ${o.english}"
    val gloss = "${s.reading} — ${s.english}"
    val by = rk.setAside[s]
    val heavier = if (s == Senjitsu.SAIGEJIKI) rk.heavierWith else emptyList()
    return Annotation(
        s.kanji,
        gloss,
        s.tone,
        Texts.SENJITSU[s],
        subtitle = when {
            by != null -> labels.string(R.string.kyu_set_aside, gloss)
            heavier.isNotEmpty() -> labels.string(R.string.kyu_heavier, gloss)
            else -> null
        },
        details = buildList {
            by?.let { add(labels.string(R.string.detail_set_aside_by) to name(it)) }
            if (heavier.isNotEmpty()) add(labels.string(R.string.detail_heavier_with) to heavier.joinToString(", ") { name(it) })
        },
    )
}

@Composable
private fun SenjitsuSection(title: String, rk: RekichuDay, band: Band, labels: Labels, onOpen: (Annotation) -> Unit) {
    val days = rk.senjitsu.filter { it.band == band }
    if (days.isEmpty()) return
    SectionTitle(title)
    Column {
        days.forEach { s ->
            AnnotationRow(senjitsuAnnotation(s, rk, labels), onOpen)
        }
    }
}

/** The element of a 干支's stem and the animal of its branch, as glyphs; the text beside them names both. */
@Composable
private fun KanshiGlyphs(k: Kanshi, element: Boolean = true) {
    if (element) CueIcon(CueGlyphs.of(k.element), Palette.muted, 20.dp)
    CueIcon(CueGlyphs.ANIMAL.getValue(Animal.entries[k.branch]), Palette.muted, 20.dp)
}

@Composable
private fun StarLine(label: String, mark: String?, star: KyuSei, onClick: (() -> Unit)? = null) {
    Column(if (onClick == null) Modifier else Modifier.clickable(role = Role.Button, onClickLabel = stringResource(R.string.action_meaning), onClick = onClick)) {
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(label, style = body.copy(fontSize = 13.sp, color = Palette.muted))
            if (mark != null) Text(mark, style = TextStyle(fontFamily = Mincho, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Palette.saffron))
        }
        Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(star.kanji.take(2), style = TextStyle(fontFamily = Mincho, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Palette.text))
            Text(star.english, style = body.copy(fontSize = 13.sp, color = Palette.muted), modifier = Modifier.padding(bottom = 2.dp))
        }
    }
}
