/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
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
import zanshin.core.kyureki.Kanshi
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
import zanshin.core.texts.toneOf

/**
 * The 旧暦 view of one day with its almanac annotations (SPEC §10.4). [birthStar]
 * adds the personal 九星気学 row when the owner has switched it on.
 */
@Composable
fun KyurekiPage(info: DayInfo, birthStar: KyuSei? = null, modifier: Modifier = Modifier) {
    val day = info.kyureki
    val rk = info.rekichu
    val accent = Palette.vermilion
    var monthOpen by remember(day.date) { mutableStateOf(false) }
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
                if (namesTimes) Centered { DayArc(rokuyoTimes, 220.dp) }
                RokuyoStrip(day.rokuyo)
            }
        },
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 28.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp),
    ) {
        val festival = day.festival
        if (festival != null) {
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
                    Text(
                        "${day.day}",
                        style = TextStyle(fontFamily = Mincho, fontWeight = FontWeight.Medium, fontSize = 120.sp, letterSpacing = (-4).sp, color = Palette.text),
                        modifier = Modifier.capBox(120.sp),
                    )
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
            MoonGlyph(info.moonElongation, 64.dp, labels.moonPhase(info.moonElongation))
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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Palette.surface, RoundedCornerShape(14.dp))
                .border(1.dp, Palette.line, RoundedCornerShape(14.dp))
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TermRing(day.currentTerm, 48.dp)
            Spacer(Modifier.width(14.dp))
            Row(Modifier.weight(1f), verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                GlossText(day.currentTerm.kanji, day.currentTerm.english, kanjiStyle.copy(fontSize = 26.sp), reading = day.currentTerm.romaji)
                Text(day.currentTerm.romaji, style = body, modifier = Modifier.padding(bottom = 3.dp))
            }
            if (day.termBeginning != null) {
                Text(
                    stringResource(R.string.kyu_begins_today),
                    style = body.copy(fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Palette.ink),
                    modifier = Modifier.background(accent, RoundedCornerShape(12.dp)).padding(horizontal = 10.dp, vertical = 4.dp),
                )
            } else {
                Text(stringResource(R.string.kyu_until, day.nextTermStart.minusDays(1).format(labels.shortDate)), style = body.copy(fontSize = 14.sp, color = Palette.muted))
            }
        }

        Spacer(Modifier.fillMaxWidth().height(1.dp).background(Palette.line))

        SectionTitle(stringResource(R.string.section_choku))
        Column {
            AnnotationRow(chokuAnnotation(rk.choku, labels)) { sheet = it }
        }

        SectionTitle(stringResource(R.string.section_shuku))
        Column {
            AnnotationRow(shukuAnnotation(rk.shuku, labels)) { sheet = it }
        }

        SenjitsuSection(stringResource(R.string.section_kagedan), rk.senjitsu.filter { it.band == Band.KAGEDAN }) { sheet = it }
        SenjitsuSection(stringResource(R.string.section_senjitsu), rk.senjitsu.filter { it.band == Band.SENJITSU }) { sheet = it }
        SenjitsuSection(stringResource(R.string.section_ennichi), rk.senjitsu.filter { it.band == Band.ENNICHI }) { sheet = it }

        if (rk.zassetsu.isNotEmpty()) {
            SectionTitle(stringResource(R.string.section_zassetsu))
            Column {
                rk.zassetsu.forEach { z ->
                    AnnotationRow(Annotation(z.kanji, "${z.reading} — ${z.english}", Tone.NEUTRAL, Texts.ZASSETSU[z])) { sheet = it }
                }
            }
        }

        SectionTitle(stringResource(R.string.section_kanshi))
        Column {
            val yearKanshi = Kanshi.ofYear(day.year)
            val branch = Kanshi(rk.setsuBranch)
            FactRow(stringResource(R.string.row_day), rk.dayKanshi.kanji, "${rk.dayKanshi.reading} — ${rk.dayKanshi.english}", tibetan = false, kanji = true, lead = { KanshiGlyphs(rk.dayKanshi) })
            FactRow(stringResource(R.string.row_year), yearKanshi.kanji, "${yearKanshi.reading} — ${yearKanshi.english}", tibetan = false, kanji = true, lead = { KanshiGlyphs(yearKanshi) })
            FactRow(
                stringResource(R.string.row_solar_month),
                "${branch.kanji.drop(1)}月",
                stringResource(R.string.kyu_solar_month, Catalog.text("Branch.${branch.branch}"), rk.setsuStart.format(labels.shortDate)),
                tibetan = false,
                kanji = true,
                lead = { KanshiGlyphs(branch, element = false) },
            )
        }

        SectionTitle(stringResource(R.string.section_stars))
        val dayStar = Annotation(rk.dayStar.kanji, stringResource(R.string.kyu_day_star, rk.dayStar.english), Tone.NEUTRAL, Texts.KYUSEI[rk.dayStar], diagram = { StarBoardDetail(rk.dayStar, 288.dp) })
        Row(
            Modifier.fillMaxWidth().clickable(role = Role.Button) { sheet = dayStar },
            horizontalArrangement = Arrangement.spacedBy(18.dp),
        ) {
            StarBoard(rk.dayStar, rk.monthStar, rk.yearStar, 120.dp)
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                StarLine(stringResource(R.string.row_day_star), null, rk.dayStar)
                StarLine(stringResource(R.string.row_month_star), "月", rk.monthStar)
                StarLine(stringResource(R.string.row_year_star), "年", rk.yearStar)
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
                diagram = { Compass(rk.ehou.bearing, 160.dp, ehouLine) },
            ),
        ) { sheet = it }
    }

    sheet?.let { ReadingSheet(it) { sheet = null } }
    if (summaryOpen) DaySummarySheet(summary) { summaryOpen = false }
}

private fun chokuAnnotation(c: Choku, labels: Labels) =
    Annotation(c.kanji, labels.string(R.string.kyu_choku_gloss, c.reading, c.english), c.tone, Texts.CHOKU[c], diagram = { ChokuDial(c, 240.dp) })

private fun shukuAnnotation(s: Shuku, labels: Labels): Annotation {
    val reading = Texts.SHUKU[s]
    return Annotation("${s.kanji}宿", labels.string(R.string.kyu_shuku_gloss, s.reading, s.english), toneOf(reading), reading, diagram = { ShukuRing(s, 280.dp) })
}

private fun senjitsuAnnotation(s: Senjitsu) = Annotation(s.kanji, "${s.reading} — ${s.english}", s.tone, Texts.SENJITSU[s])

@Composable
private fun SenjitsuSection(title: String, days: List<Senjitsu>, onOpen: (Annotation) -> Unit) {
    if (days.isEmpty()) return
    SectionTitle(title)
    Column {
        days.forEach { s ->
            AnnotationRow(senjitsuAnnotation(s), onOpen)
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
private fun StarLine(label: String, mark: String?, star: KyuSei) {
    Column {
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
