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
import zanshin.app.DayInfo
import zanshin.app.Labels
import zanshin.core.kyureki.Band
import zanshin.core.kyureki.Choku
import zanshin.core.kyureki.DayMark
import zanshin.core.kyureki.Kigaku
import zanshin.core.kyureki.KyuSei
import zanshin.core.kyureki.Kanshi
import zanshin.core.kyureki.Senjitsu
import zanshin.core.kyureki.Shuku
import zanshin.core.kyureki.Tone
import zanshin.core.texts.Reading
import zanshin.core.texts.DaySummary
import zanshin.core.texts.Texts
import zanshin.core.texts.rokuyoTone
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
    val monthLabel = Labels.month(day.month, day.leapMonth)
    val rokuyo = Annotation(day.rokuyo.kanji, "${day.rokuyo.romaji} — ${day.rokuyo.english}", rokuyoTone(day.rokuyo), Texts.ROKUYO[day.rokuyo])

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
                    Text("day", style = body.copy(fontSize = 14.sp, color = Palette.muted))
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
                    description = "$monthLabel, day ${day.day}",
                    rows = listOf(
                        BalloonRow("Traditional name", "${day.monthName} · ${day.monthNameRomaji}"),
                        BalloonRow("Meaning", day.monthNameEnglish),
                        BalloonRow("Year", "${day.year}"),
                    ),
                )
            }
            MoonGlyph(info.moonElongation, 64.dp, Labels.moonPhase(info.moonElongation))
        }

        Row(
            Modifier.clickable(role = Role.Button) { sheet = rokuyo },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(day.rokuyo.kanji, style = kanjiStyle.copy(fontSize = 72.sp, color = accent).tight(1.0f))
            Column {
                Text(day.rokuyo.romaji, style = body.copy(fontSize = 20.sp, fontWeight = FontWeight.SemiBold))
                Text("${day.rokuyo.english} · rokuyō", style = body.copy(fontSize = 13.sp, color = Palette.muted))
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
                        DayMark.BLACK -> "black day — the worst of all"
                        DayMark.PARDON -> "heaven's pardon — good for all"
                    },
                    style = body.copy(fontSize = 14.sp, color = Palette.muted),
                )
            }
        }

        Row(
            Modifier.fillMaxWidth().heightIn(min = 44.dp).clickable(role = Role.Button, onClickLabel = "Day in brief") { summaryOpen = true },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            val disputed = summary.activities.count { it.disputed }
            Text("In brief", style = body.copy(fontSize = 14.sp, fontWeight = FontWeight.SemiBold))
            Text(
                "good for ${summary.good.size} · avoid ${summary.avoid.size}" + if (disputed > 0) " · $disputed disputed" else "",
                style = body.copy(fontSize = 14.sp, color = Palette.muted),
                modifier = Modifier.weight(1f),
            )
            Icon(Icons.ChevronRight, contentDescription = null, tint = Palette.faint, modifier = Modifier.size(18.dp))
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Palette.surface, RoundedCornerShape(14.dp))
                .border(1.dp, Palette.line, RoundedCornerShape(14.dp))
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                GlossText(day.currentTerm.kanji, day.currentTerm.english, kanjiStyle.copy(fontSize = 26.sp), reading = day.currentTerm.romaji)
                Text(day.currentTerm.romaji, style = body, modifier = Modifier.padding(bottom = 3.dp))
            }
            if (day.termBeginning != null) {
                Text(
                    "begins today",
                    style = body.copy(fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Palette.ink),
                    modifier = Modifier.background(accent, RoundedCornerShape(12.dp)).padding(horizontal = 10.dp, vertical = 4.dp),
                )
            } else {
                Text("until ${day.nextTermStart.minusDays(1).format(Labels.shortDate)}", style = body.copy(fontSize = 14.sp, color = Palette.muted))
            }
        }

        Spacer(Modifier.fillMaxWidth().height(1.dp).background(Palette.line))

        SectionTitle("中段 · Twelve stations")
        Column {
            AnnotationRow(chokuAnnotation(rk.choku)) { sheet = it }
        }

        SectionTitle("二十八宿 · Lunar lodge")
        Column {
            AnnotationRow(shukuAnnotation(rk.shuku)) { sheet = it }
        }

        SenjitsuSection("下段 · Lower band", rk.senjitsu.filter { it.band == Band.KAGEDAN }) { sheet = it }
        SenjitsuSection("選日 · Selected days", rk.senjitsu.filter { it.band == Band.SENJITSU }) { sheet = it }
        SenjitsuSection("縁日 · Deity days", rk.senjitsu.filter { it.band == Band.ENNICHI }) { sheet = it }

        if (rk.zassetsu.isNotEmpty()) {
            SectionTitle("雑節 · Seasonal markers")
            Column {
                rk.zassetsu.forEach { z ->
                    AnnotationRow(Annotation(z.kanji, "${z.reading} — ${z.english}", Tone.NEUTRAL, Texts.ZASSETSU[z])) { sheet = it }
                }
            }
        }

        SectionTitle("干支 · Cycles")
        Column {
            FactRow("Day", rk.dayKanshi.kanji, "${rk.dayKanshi.reading} — ${rk.dayKanshi.english}", tibetan = false, kanji = true)
            val yearKanshi = Kanshi.ofYear(day.year)
            FactRow("Year", yearKanshi.kanji, "${yearKanshi.reading} — ${yearKanshi.english}", tibetan = false, kanji = true)
            val branch = Kanshi(rk.setsuBranch)
            FactRow(
                "Solar month",
                "${branch.kanji.drop(1)}月",
                "month of the ${branch.english.substringAfterLast(' ')}, since ${rk.setsuStart.format(Labels.shortDate)}",
                tibetan = false,
                kanji = true,
            )
            FactRow("Month star", rk.monthStar.kanji, "${rk.monthStar.reading} — ${rk.monthStar.english}", tibetan = false, kanji = true)
            FactRow("Year star", rk.yearStar.kanji, "${rk.yearStar.reading} — ${rk.yearStar.english}", tibetan = false, kanji = true)
        }
        AnnotationRow(Annotation(rk.dayStar.kanji, "day star: ${rk.dayStar.english}", Tone.NEUTRAL, Texts.KYUSEI[rk.dayStar])) { sheet = it }
        if (birthStar != null) {
            val a = Kigaku.affinity(birthStar, rk.dayStar)
            AnnotationRow(
                Annotation(
                    a.relation.kanji,
                    "${a.relation.reading} — ${a.relation.english}",
                    a.relation.tone,
                    Texts.KIGAKU[a.relation],
                    subtitle = "your birth star ${birthStar.english} · ${a.cycleEnglish}",
                ),
            ) { sheet = it }
        }
        AnnotationRow(Annotation("恵方", "lucky direction of the year: ${rk.ehou.english}", Tone.GOOD, Texts.EHOU[rk.ehou], subtitle = "${rk.ehou.kanji} — ${rk.ehou.english}")) { sheet = it }
    }

    sheet?.let { ReadingSheet(it) { sheet = null } }
    if (summaryOpen) DaySummarySheet(summary) { summaryOpen = false }
}

private fun chokuAnnotation(c: Choku) = Annotation(c.kanji, "${c.reading} — ${c.english} · twelve stations", c.tone, Texts.CHOKU[c])

private fun shukuAnnotation(s: Shuku): Annotation {
    val reading = Texts.SHUKU[s]
    return Annotation("${s.kanji}宿", "${s.reading} — ${s.english} · 28 lodges", toneOf(reading), reading)
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
