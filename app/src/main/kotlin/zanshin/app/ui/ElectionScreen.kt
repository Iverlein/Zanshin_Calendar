/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import android.util.LruCache
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.iverlein.zanshin.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import zanshin.app.LocalLabels
import zanshin.core.kyureki.Tone
import zanshin.core.texts.Activity
import zanshin.core.texts.ActivityFamily
import zanshin.core.texts.Election
import zanshin.core.texts.ElectionDay
import zanshin.core.texts.ElectionMonth
import zanshin.core.texts.ElectionSpan
import zanshin.core.texts.PeriodRun
import zanshin.core.texts.VerdictBy
import zanshin.core.texts.family
import java.time.LocalDate

/** The spans offered, in Tibetan months from the shown day's (ROADMAP E2). */
private val SPANS = listOf(1 to R.string.election_span_1, 3 to R.string.election_span_3, Election.MAX_MONTHS to R.string.election_span_12)

/** Spans weighed once and kept, per start, length and birth date: a year is some 380 day summaries (ROADMAP E1). */
private object Spans {
    private val cache = LruCache<Triple<LocalDate, Int, LocalDate?>, ElectionSpan>(4)

    fun of(from: LocalDate, months: Int, birth: LocalDate?): ElectionSpan =
        cache[Triple(from, months, birth)] ?: ElectionSpan.of(from, months, birth).also { cache.put(Triple(from, months, birth), it) }
}

/**
 * The election (SPEC §10.5, ROADMAP E2): [work] picked from the families of the works some list
 * names, or by search; then the span from [from], the shown day, its days as a grid per Tibetan
 * month with the work's hours in each (E3), and under them the good days, best first, each opening
 * its workings and from there its day page through [onOpenDay]. With [birth] set the person's days
 * of WB's p. 338 are not offered (E6).
 */
@Composable
fun ElectionScreen(
    from: LocalDate,
    birth: LocalDate?,
    work: Activity?,
    onWork: (Activity?) -> Unit,
    onOpenDay: (LocalDate) -> Unit,
    onBack: () -> Unit,
) {
    var months by rememberSaveable { mutableStateOf(1) }
    Column(
        Modifier
            .fillMaxSize()
            .background(Palette.ink)
            .windowInsetsPadding(WindowInsets.safeDrawing),
    ) {
        Row(Modifier.fillMaxWidth().height(64.dp).padding(start = 6.dp, top = 8.dp), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) { Icon(Icons.ChevronLeft, contentDescription = stringResource(R.string.back), tint = Palette.text) }
            Text(stringResource(R.string.election_title), style = body.copy(fontSize = 18.sp, fontWeight = FontWeight.SemiBold))
        }
        if (work == null) {
            WorkPicker(onWork)
        } else {
            Result(from, birth, work, months, { months = it }, onChange = { onWork(null) }, onOpenDay)
        }
    }
}

/** The works by family, each family opening its works, and a search over their names in the app's language. */
@Composable
private fun WorkPicker(onWork: (Activity) -> Unit) {
    var query by rememberSaveable { mutableStateOf("") }
    var open by rememberSaveable { mutableStateOf<String?>(null) }
    val locale = LocalLabels.current.locale
    val matches = remember(query, locale) {
        val q = query.trim().lowercase(locale)
        if (q.isEmpty()) emptyList() else Election.WORKS.values.flatten().filter { q in it.english.lowercase(locale) }.sortedBy { it.english.lowercase(locale) }
    }
    LazyColumn(Modifier.fillMaxSize(), contentPadding = androidx.compose.foundation.layout.PaddingValues(start = 24.dp, end = 24.dp, bottom = 32.dp)) {
        item {
            Column(Modifier.padding(bottom = 12.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(stringResource(R.string.election_pick), style = body.copy(fontSize = 16.sp, color = Palette.muted))
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .background(Palette.surface, RoundedCornerShape(12.dp))
                        .border(1.dp, Palette.line, RoundedCornerShape(12.dp))
                        .padding(horizontal = 14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    Icon(Icons.Search, contentDescription = null, tint = Palette.faint, modifier = Modifier.size(18.dp))
                    BasicTextField(
                        value = query,
                        onValueChange = { query = it },
                        singleLine = true,
                        textStyle = body.copy(fontSize = 16.sp),
                        cursorBrush = SolidColor(Palette.text),
                        modifier = Modifier.weight(1f),
                        decorationBox = { inner ->
                            if (query.isEmpty()) Text(stringResource(R.string.election_search), style = body.copy(fontSize = 16.sp, color = Palette.faint))
                            inner()
                        },
                    )
                }
            }
        }
        if (query.isNotBlank()) {
            if (matches.isEmpty()) item { Text(stringResource(R.string.election_no_match), style = body.copy(color = Palette.faint)) }
            items(matches) { WorkRow(it, onWork) }
        } else {
            for ((family, works) in Election.WORKS) {
                item(key = "family ${family.name}") {
                    FamilyRow(family, works.size, open == family.name) { open = if (open == family.name) null else family.name }
                }
                if (open == family.name) items(works, key = { "work ${it.name}" }) { WorkRow(it, onWork, indent = true) }
            }
        }
    }
}

@Composable
private fun FamilyRow(family: ActivityFamily, count: Int, open: Boolean, onToggle: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .heightIn(min = 52.dp)
            .clickable(role = Role.Button, onClick = onToggle),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        CueIcon(CueGlyphs.FAMILY.getValue(family), Palette.text, 24.dp)
        Text(family.english.replaceFirstChar { it.uppercase() }, style = body.copy(fontSize = 16.sp), modifier = Modifier.weight(1f))
        Text("$count", style = body.copy(fontSize = 13.sp, color = Palette.faint))
        Icon(if (open) Icons.ChevronDown else Icons.ChevronRight, contentDescription = null, tint = Palette.faint, modifier = Modifier.size(16.dp))
    }
}

@Composable
private fun WorkRow(work: Activity, onWork: (Activity) -> Unit, indent: Boolean = false) {
    Row(
        Modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .clickable(role = Role.Button) { onWork(work) }
            .padding(start = if (indent) 38.dp else 0.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        if (!indent) CueIcon(CueGlyphs.FAMILY.getValue(work.family), Palette.muted, 20.dp)
        Text(withTibetan(work.english.replaceFirstChar { it.uppercase() }), style = body.copy(fontSize = 15.sp))
    }
}

/** The election for [work]: the span's chips, each Tibetan month's grid and hours, then the best days. */
@Composable
private fun Result(
    from: LocalDate,
    birth: LocalDate?,
    work: Activity,
    months: Int,
    onMonths: (Int) -> Unit,
    onChange: () -> Unit,
    onOpenDay: (LocalDate) -> Unit,
) {
    // Weighed off the main thread; a year takes a moment on a phone.
    val election by produceState<Election?>(null, from, months, birth, work) {
        value = null
        value = withContext(Dispatchers.Default) { Spans.of(from, months, birth).election(work) }
    }
    LazyColumn(
        Modifier.fillMaxSize(),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(start = 24.dp, end = 24.dp, bottom = 32.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp),
    ) {
        item {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                CueIcon(CueGlyphs.FAMILY.getValue(work.family), Palette.saffron, 26.dp)
                Text(
                    withTibetan(work.english.replaceFirstChar { it.uppercase() }),
                    style = body.copy(fontSize = 20.sp, fontWeight = FontWeight.SemiBold),
                    modifier = Modifier.weight(1f),
                )
                Text(
                    stringResource(R.string.election_change),
                    style = body.copy(fontSize = 14.sp, color = Palette.muted),
                    modifier = Modifier.heightIn(min = 44.dp).clickable(role = Role.Button, onClick = onChange).padding(horizontal = 4.dp, vertical = 12.dp),
                )
            }
        }
        item { SpanChips(months, onMonths) }
        val e = election
        if (e == null) {
            item { Text(stringResource(R.string.election_weighing), style = body.copy(color = Palette.faint)) }
            return@LazyColumn
        }
        items(e.months, key = { "month ${it.first.jd}" }) { MonthBlock(it, from) }
        if (birth != null && e.days.any { it.avoidAll.isNotEmpty() }) {
            item { Text(withTibetan(stringResource(R.string.election_for_you_note)), style = note) }
        }
        item { SectionTitle(stringResource(R.string.election_best)) }
        if (e.best.isEmpty()) item { Text(stringResource(R.string.election_none), style = body.copy(color = Palette.muted)) }
        items(e.best, key = { "day ${it.day.jd}" }) { BestDay(it, onOpenDay) }
        item { Text(withTibetan(stringResource(R.string.election_order_note)), style = note) }
        item { Text(withTibetan(stringResource(R.string.election_hours_note)), style = note) }
    }
}

private val note get() = body.copy(fontSize = 13.sp, lineHeight = 19.sp, color = Palette.faint)

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SpanChips(months: Int, onMonths: (Int) -> Unit) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        for ((n, label) in SPANS) {
            val on = n == months
            Text(
                stringResource(label),
                style = body.copy(fontSize = 14.sp, fontWeight = if (on) FontWeight.SemiBold else FontWeight.Normal, color = if (on) Palette.saffron else Palette.muted),
                modifier = Modifier
                    .heightIn(min = 40.dp)
                    .border(1.dp, if (on) Palette.saffron else Palette.lineStrong, RoundedCornerShape(20.dp))
                    .clickable(role = Role.RadioButton) { onMonths(n) }
                    .padding(horizontal = 14.dp, vertical = 10.dp),
            )
        }
    }
}

/** One Tibetan month: its heading, its days as a grid (red where to avoid, a ring for the person's days), and the work's hours. */
@Composable
private fun MonthBlock(m: ElectionMonth, from: LocalDate) {
    val labels = LocalLabels.current
    val first = m.first
    val month = labels.month(first.month, first.leapMonth)
    val description = stringResource(R.string.desc_election_grid, month, m.days.first().work.english)
    val good = stringResource(R.string.brief_good)
    val avoid = stringResource(R.string.brief_avoid)
    val blank = stringResource(R.string.election_blank)
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(month.replaceFirstChar { it.uppercase() }, style = body.copy(fontSize = 15.sp, fontWeight = FontWeight.SemiBold))
        Box(Modifier.semantics { contentDescription = description }) {
            WorkGrid(
                heading = null,
                cells = m.days.map { WorkCell(it.day.day, it.side ?: Tone.NEUTRAL, marked = it.date == from, forYou = it.avoidAll.isNotEmpty()) },
                key = first.jd,
                initial = -1,
            ) { k ->
                val d = m.days[k]
                val verdict = when {
                    d.avoidAll.isNotEmpty() -> stringResource(R.string.election_cell_for_you, d.avoidAll.joinToString { it.kanji })
                    d.side == null -> blank
                    else -> stringResource(R.string.election_side_by, if (d.side == Tone.GOOD) good else avoid, deciding(d))
                }
                stringResource(R.string.election_cell, d.date.format(labels.headerDate), d.day.day, verdict)
            }
        }
        Hours(stringResource(R.string.election_hours_good), Tone.GOOD, m.good)
        Hours(stringResource(R.string.election_hours_avoid), Tone.BAD, m.avoid)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun Hours(label: String, tone: Tone, runs: List<PeriodRun>) {
    if (runs.isEmpty()) return
    Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        Box(Modifier.padding(top = 7.dp).size(8.dp).background(toneColor(tone), CircleShape))
        Column {
            Text(label, style = body.copy(fontSize = 13.sp, color = Palette.muted))
            FlowRow(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                for (r in runs) Text(clockSpan(5 * 60 + r.first * 120, r.count * 120), style = body.copy(fontSize = 15.sp))
            }
        }
    }
}

/** What decided a day for the work, in a few words: "by the combination", or the factor by name ("by Monday"). */
@Composable
private fun deciding(d: ElectionDay): String =
    if (d.by == VerdictBy.COMBINATION) stringResource(R.string.brief_by_combination) else stringResource(R.string.brief_by_factor, d.standing.first().kanji)

/**
 * A good day: its civil and Tibetan dates, what decides it and the voices standing, the person's
 * own days marked; it opens its workings, the voices with their kinds, and from there its day page.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun BestDay(d: ElectionDay, onOpenDay: (LocalDate) -> Unit) {
    val labels = LocalLabels.current
    var open by remember { mutableStateOf(false) }
    val by = deciding(d)
    val combination = d.summary.combinationTone
    Box {
        Row(
            Modifier
                .fillMaxWidth()
                .clickable(role = Role.Button) { open = !open }
                .padding(vertical = 6.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Box(Modifier.padding(top = 7.dp).size(8.dp).background(Palette.good, CircleShape))
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically) {
                    // A span runs into the next year: the date with its weekday and year, as the header gives it.
                    Text(d.date.format(labels.headerDate), style = body.copy(fontSize = 16.sp, fontWeight = FontWeight.SemiBold))
                    Text(
                        stringResource(R.string.election_tibetan_date, labels.month(d.day.month, d.day.leapMonth), d.day.day),
                        style = body.copy(fontSize = 13.sp, color = Palette.muted),
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text(by, style = body.copy(fontSize = 14.sp))
                    // The combination's tone orders days of one rank (ROADMAP E, the order's third step).
                    combination?.let { Box(Modifier.size(6.dp).background(toneColor(it), CircleShape)) }
                }
                Text(withTibetan(d.standing.joinToString(" · ") { it.kanji }), style = body.copy(fontSize = 13.sp, color = Palette.muted))
                if (d.personal.isNotEmpty()) {
                    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), itemVerticalAlignment = Alignment.CenterVertically) {
                        Text(stringResource(R.string.brief_for_you), style = body.copy(fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Palette.saffron))
                        for (p in d.personal) {
                            Box(Modifier.size(6.dp).background(toneColor(p.tone), CircleShape))
                            Text(withTibetan(p.kanji), style = body.copy(fontSize = 12.sp, color = Palette.muted))
                        }
                    }
                }
                if (d.nectar.isNotEmpty()) {
                    Text(
                        stringResource(R.string.election_nectar, d.nectar.joinToString { clockSpan(5 * 60 + it * 60, 60) }),
                        style = body.copy(fontSize = 12.sp, color = Palette.saffron),
                    )
                }
            }
        }
        if (open) {
            Balloon(
                listOf(BalloonRow(stringResource(R.string.good_for), d.work.english)) + d.standing.map { BalloonRow(it.english, it.kanji) },
                actions = listOf(BalloonAction(stringResource(R.string.election_open_day)) { onOpenDay(d.date) }),
                onDismiss = { open = false },
            )
        }
    }
}
