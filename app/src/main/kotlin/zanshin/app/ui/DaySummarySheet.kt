/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.iverlein.zanshin.R
import zanshin.core.kyureki.DayMark
import zanshin.core.kyureki.Tone
import zanshin.core.texts.Activity
import zanshin.core.texts.ActivityFamily
import zanshin.core.texts.ActivityNote
import zanshin.core.texts.DayHours
import zanshin.core.texts.DaySummary
import zanshin.core.texts.DayVerdict
import zanshin.core.texts.KyurekiElection
import zanshin.core.texts.VerdictBy
import zanshin.core.texts.SummaryEntry
import zanshin.core.texts.Texts
import zanshin.core.texts.byVoices
import zanshin.core.texts.family
import zanshin.core.tibetan.OwnDay

private val termStyle get() = body.copy(fontFamily = Mincho, fontWeight = FontWeight.Bold, fontSize = 15.sp)

/**
 * The day's summary line (SPEC §10.4, §10.7). On the 旧暦 page the glyphs of
 * the activity families the annotations name good and to avoid, a family in
 * the mixed colour when one of its activities is named both ways; on the
 * Tibetan page one row, the families of the heaviest works good and to avoid
 * ([DaySummary.row]), followed by how many works the day names good and to
 * avoid, since the row cannot show the proportion. Under the Tibetan day's
 * tone, the combination periods that run against it ([DaySummary.hoursAgainst],
 * ROADMAP U5), each time calling [onHour] with its two-hour period. Opens
 * [DaySummarySheet]. Screen readers get the counts, the hours, with an action
 * for each, and on the Tibetan page the works the row stands for.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BriefRow(summary: DaySummary, onHour: (Int) -> Unit = {}, onOpen: () -> Unit) {
    val briefLabel = stringResource(R.string.kyu_day_in_brief)
    val disputed = summary.activities.count { it.disputed }
    val dayLabel = summary.verdict?.let {
        stringResource(if (it.tone == Tone.GOOD) R.string.brief_day_good else R.string.brief_day_bad) + " · " + byShort(it)
    }
    val row = remember(summary) { summary.row() }
    val avoidAll = if (summary.avoidAll.isNotEmpty()) stringResource(R.string.brief_avoid_all) else null
    val hours = summary.hoursAgainst.map { clockSpan(5 * 60 + it.first * 120, it.count * 120) to it.first }
    val hoursLabel = summary.verdict?.let { stringResource(if (it.tone == Tone.GOOD) R.string.brief_row_hours_bad else R.string.brief_row_hours_good) }
    val openHour = stringResource(R.string.hours_open)
    val spoken = stringResource(R.string.kyu_in_brief) + ": " + (dayLabel?.let { "$it, " } ?: "") +
        (if (hours.isNotEmpty()) "$hoursLabel ${hours.joinToString { it.first }}, " else "") + (avoidAll?.let { "$it, " } ?: "") +
        (if (avoidAll != null) stringResource(R.string.brief_row_avoid, summary.avoid.size) else stringResource(R.string.kyu_brief_counts, summary.good.size, summary.avoid.size)) +
        (if (disputed > 0) stringResource(R.string.kyu_brief_disputed, disputed) else "") +
        when {
            summary.verdict == null || row.first.size + row.second.size == 0 -> ""
            avoidAll != null -> stringResource(R.string.brief_row_spoken_avoid, row.second.joinToString { it.activity.english })
            else -> stringResource(R.string.brief_row_spoken, row.first.joinToString { it.activity.english }, row.second.joinToString { it.activity.english })
        }
    Row(
        Modifier
            .fillMaxWidth()
            .heightIn(min = 44.dp)
            .clickable(role = Role.Button, onClickLabel = briefLabel, onClick = onOpen)
            .clearAndSetSemantics {
                contentDescription = spoken
                customActions = hours.map { (time, hour) -> CustomAccessibilityAction("$openHour, $time") { onHour(hour); true } }
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(stringResource(R.string.kyu_in_brief), style = body.copy(fontSize = 14.sp, fontWeight = FontWeight.SemiBold))
                summary.verdict?.let { v ->
                    Box(Modifier.size(8.dp).background(toneColor(v.tone), CircleShape))
                    Text(dayLabel!!, style = body.copy(fontSize = 14.sp, color = Palette.muted))
                }
            }
            // The hours against the day's tone: on an unlucky day those to be accomplished, on a lucky day those to avoid.
            if (hours.isNotEmpty()) {
                val against = if (summary.verdict!!.tone == Tone.GOOD) Tone.BAD else Tone.GOOD
                FlowRow(
                    verticalArrangement = Arrangement.Center,
                    itemVerticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Text(hoursLabel!!, style = body.copy(fontSize = 13.sp, color = toneColor(against)))
                    for ((time, hour) in hours) {
                        Text(
                            time,
                            style = body.copy(fontSize = 14.sp),
                            modifier = Modifier.clickable(role = Role.Button, onClickLabel = openHour) { onHour(hour) }.padding(vertical = 4.dp),
                        )
                    }
                }
            }
            // The person's enemy weekday or death mansion: every work to avoid, beside the day's tone (ROADMAP E6).
            avoidAll?.let { Text(it, style = body.copy(fontSize = 14.sp, color = Palette.bad)) }
            if (summary.verdict != null) {
                // The glyphs show the heaviest works only; the counts say how the day's lists divide (ROADMAP T2.7).
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Box(Modifier.weight(1f)) { WeighedLine(summary) }
                    // One above the other, so that they take half the width and leave it to the glyphs.
                    Column(horizontalAlignment = Alignment.End) {
                        val small = body.copy(fontSize = 12.sp, lineHeight = 14.sp)
                        if (avoidAll == null) {
                            Text(stringResource(R.string.brief_row_good, summary.good.size), style = small.copy(color = Palette.good), maxLines = 1)
                        }
                        Text(stringResource(R.string.brief_row_avoid, summary.avoid.size), style = small.copy(color = Palette.bad), maxLines = 1)
                    }
                }
            } else {
                FamilyLine(stringResource(R.string.brief_good), summary.goodFamilies, summary.disputedFamilies, Palette.good)
                FamilyLine(stringResource(R.string.brief_avoid), summary.avoidFamilies, summary.disputedFamilies, Palette.bad)
            }
        }
        Icon(Icons.ChevronRight, contentDescription = null, tint = Palette.faint, modifier = Modifier.size(18.dp))
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FamilyLine(label: String, families: List<ActivityFamily>, disputed: Set<ActivityFamily>, color: Color) {
    if (families.isEmpty()) return
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(label, style = body.copy(fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = color), modifier = Modifier.width(62.dp))
        FlowRow(horizontalArrangement = Arrangement.spacedBy(9.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            for (f in families) CueIcon(CueGlyphs.FAMILY.getValue(f), if (f in disputed) Palette.mixed else color, 22.dp)
        }
    }
}

/**
 * The Tibetan line: one row, however many works the day names (SPEC §10.7). As
 * many places as fit, eight at most; the heaviest good works' families, a rule,
 * then the heaviest to avoid.
 */
@Composable
private fun WeighedLine(summary: DaySummary) {
    BoxWithConstraints {
        val fit = ((maxWidth - 1.dp) / (22.dp + 9.dp)).toInt()
        val (good, avoid) = remember(summary, fit) { summary.row(minOf(DaySummary.ROW_SLOTS, fit)) }
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(9.dp)) {
            for (n in good) CueIcon(CueGlyphs.FAMILY.getValue(n.activity.family), Palette.good, 22.dp)
            if (good.isNotEmpty() && avoid.isNotEmpty()) Box(Modifier.width(1.dp).height(16.dp).background(Palette.faint))
            for (n in avoid) CueIcon(CueGlyphs.FAMILY.getValue(n.activity.family), Palette.bad, 22.dp)
        }
    }
}

/**
 * The breakdown behind the day's summary line (ROADMAP R3): a listing on the 旧暦 page, the weighed
 * day on the Tibetan one (SPEC §5.12), with the hours above it; a time there calls [onHour] with its
 * two-hour period, counted from 05:00. A work's workings offer its election through [onElect], the
 * Tibetan one on the Tibetan page (ROADMAP E2), the 旧暦's on the 旧暦 page (E5).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DaySummarySheet(
    s: DaySummary,
    festival: Boolean = false,
    onHour: (Int) -> Unit = {},
    onElect: ((Activity) -> Unit)? = null,
    onDismiss: () -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        containerColor = Palette.surface,
        scrimColor = Palette.scrim,
    ) {
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .padding(start = 24.dp, end = 24.dp, bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp),
        ) {
            Text(stringResource(R.string.brief_title), style = body.copy(fontSize = 22.sp, fontWeight = FontWeight.SemiBold))

            s.mark?.let { mark ->
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    DayMarkGlyph(mark, Palette.text, 12.dp)
                    GlossText(mark.senjitsu.kanji, mark.senjitsu.english, termStyle.copy(fontSize = 18.sp), reading = mark.senjitsu.reading)
                    Text(
                        when (mark) {
                            DayMark.BLACK -> stringResource(R.string.brief_black_day)
                            DayMark.PARDON -> stringResource(R.string.brief_pardon_day)
                        },
                        style = body.copy(fontSize = 14.sp, color = Palette.muted),
                    )
                }
            }

            s.verdict?.let { v ->
                Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Box(Modifier.padding(top = 8.dp).size(10.dp).background(toneColor(v.tone), CircleShape))
                    Column {
                        Text(
                            stringResource(if (v.tone == Tone.GOOD) R.string.brief_day_good else R.string.brief_day_bad),
                            style = body.copy(fontSize = 18.sp),
                        )
                        Text(
                            when (v.by) {
                                VerdictBy.COMBINATION -> stringResource(R.string.brief_day_combination)
                                VerdictBy.STRONGEST -> stringResource(R.string.brief_day_strongest, "${v.deciding.joinToString { it.kanji }} (${v.factor.english})")
                            },
                            style = body.copy(fontSize = 14.sp, color = Palette.muted),
                        )
                    }
                }
            }

            if (s.avoidAll.isNotEmpty()) {
                Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Box(Modifier.padding(top = 8.dp).size(10.dp).background(Palette.bad, CircleShape))
                    Column {
                        Text(stringResource(R.string.brief_avoid_all_title), style = body.copy(fontSize = 18.sp))
                        Text(s.avoidAll.joinToString(" · ") { "${it.kanji}, ${it.english}" }, style = body.copy(fontSize = 14.sp, color = Palette.muted))
                    }
                }
            }

            if (s.verdict != null && festival) {
                Text(stringResource(R.string.brief_festival_note), style = body.copy(fontSize = 14.sp, color = Palette.muted))
            }

            if (s.verdict != null) {
                VoiceBlock(stringResource(R.string.good_for), s.good, Palette.good, good = true, onElect)
                VoiceBlock(stringResource(R.string.avoid), s.avoid, Palette.bad, good = false, onElect)
            } else {
                ActivityBlock(stringResource(R.string.good_for), s.good, Palette.good, good = true, onElect)
                ActivityBlock(stringResource(R.string.avoid), s.avoid, Palette.bad, good = false, onElect)
            }

            s.hours?.let { HoursBlock(it, onHour) }

            Block(stringResource(R.string.brief_by_tone)) {
                for (tone in listOf(Tone.GOOD, Tone.MIXED, Tone.BAD)) {
                    val entries = s.byTone[tone].orEmpty()
                    if (entries.isEmpty()) continue
                    Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Box(Modifier.padding(top = 7.dp).size(8.dp).background(toneColor(tone), CircleShape))
                        Terms(entries)
                    }
                }
            }

            if (s.setAside.isNotEmpty()) {
                Block(stringResource(R.string.brief_set_aside)) {
                    for ((by, entries) in s.setAside) {
                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Box(Modifier.size(8.dp).background(toneColor(by.tone), CircleShape))
                                GlossText(by.kanji, by.english, termStyle)
                            }
                            Text(
                                stringResource(if (by.tone == Tone.GOOD) R.string.brief_set_aside_lifted else R.string.brief_set_aside_alone),
                                style = body.copy(fontSize = 14.sp, color = Palette.muted),
                            )
                            Terms(entries, small = true)
                        }
                    }
                }
            }

            if (s.personal.isNotEmpty() || s.affinity != null) {
                Block(stringResource(R.string.brief_for_you)) {
                    if (s.verdict != null) {
                        // The Tibetan day's own weekday and mansions: shown with their tones, not weighed (SPEC §5.12).
                        for (e in s.personal) {
                            Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                                Box(Modifier.padding(top = 7.dp).size(8.dp).background(toneColor(e.tone), CircleShape))
                                Column {
                                    Text(e.kanji, style = body.copy(fontSize = 16.sp))
                                    Text(e.english, style = body.copy(fontSize = 14.sp, color = Palette.muted))
                                }
                            }
                        }
                        val note = body.copy(fontSize = 14.sp, lineHeight = 20.sp, color = Palette.muted)
                        if (s.avoidAll.isNotEmpty()) {
                            // WB's words on the two days it makes absolute (vol. 2, p. 338), then why the good list is gone.
                            for (e in s.avoidAll) {
                                val words = if (e.reading == Texts.OWN_DAY[OwnDay.ENEMY_WEEKDAY]) R.string.brief_avoid_all_enemy else R.string.brief_avoid_all_death
                                Text(withTibetan(stringResource(words)), style = note.copy(color = Palette.text))
                            }
                            Text(withTibetan(stringResource(R.string.brief_avoid_all_note)), style = note)
                        } else {
                            Text(withTibetan(stringResource(R.string.brief_personal_not_weighed)), style = note)
                        }
                    } else if (s.personal.isNotEmpty()) {
                        Terms(s.personal)
                    }
                    s.affinity?.let { a ->
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            Box(Modifier.size(8.dp).background(toneColor(a.relation.tone), CircleShape))
                            GlossText(a.relation.kanji, a.relation.english, termStyle, reading = a.relation.reading)
                            Text(stringResource(R.string.brief_birth_star, a.cycleEnglish), style = body.copy(fontSize = 14.sp, color = Palette.muted))
                        }
                    }
                }
            }

            Text(
                withTibetan(stringResource(if (s.verdict != null) R.string.brief_note_tibetan else R.string.brief_note)),
                style = body.copy(fontSize = 12.sp, color = Palette.faint, lineHeight = 17.sp),
            )
        }
    }
}

@Composable
private fun Block(title: String, content: @Composable () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        SectionTitle(title)
        content()
    }
}

/**
 * The 旧暦 day's works on one side, each with the annotations naming it; a work the 旧暦 election is
 * offered for opens a balloon with them and "Choose a day for it" through [onElect] (ROADMAP E5).
 */
@Composable
private fun ActivityBlock(title: String, notes: List<ActivityNote>, color: Color, good: Boolean, onElect: ((Activity) -> Unit)?) {
    val side = stringResource(if (good) R.string.good_for else R.string.avoid)
    val choose = stringResource(R.string.election_choose)
    if (notes.isEmpty()) return
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(title, style = body.copy(fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = color))
        for (n in notes) {
            BoxWithConstraints {
                // The label takes its own width up to 55% of the row and wraps by words past
                // that; the annotations fill the rest, right-aligned. Without the cap a wide
                // set of annotations squeezed the label into a one-letter column.
                val labelMax = maxWidth * 0.55f
                Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    CueIcon(CueGlyphs.FAMILY.getValue(n.activity.family), if (n.disputed) Palette.mixed else color, 22.dp)
                    val elect = onElect?.takeIf { n.activity in KyurekiElection.OFFERED }
                    var open by remember { mutableStateOf(false) }
                    Column(Modifier.widthIn(max = labelMax).then(if (elect != null) Modifier.clickable(role = Role.Button) { open = !open } else Modifier)) {
                        Text(n.activity.english, style = body.copy(fontSize = 16.sp))
                        if (open && elect != null) {
                            Balloon(
                                listOf(BalloonRow(side, n.activity.english)) + (if (good) n.good else n.avoid).map { BalloonRow(it.english, it.kanji) },
                                preferAbove = true,
                                actions = listOf(BalloonAction(choose) { elect(n.activity) }),
                                onDismiss = { open = false },
                            )
                        }
                        if (n.disputed) {
                            Text(
                                stringResource(if (good) R.string.brief_also_avoid else R.string.brief_also_good),
                                style = body.copy(fontSize = 12.sp, color = Palette.mixed),
                            )
                        }
                    }
                    Terms(if (good) n.good else n.avoid, small = true, modifier = Modifier.weight(1f), end = true)
                }
            }
        }
    }
}

/**
 * The Tibetan day's works on one side, grouped by the voices standing on it (ROADMAP U4): each group
 * headed by its voices, each tapped for its kind, and its works as one wrapped run of glyphs and
 * names; a work opens its workings, the voices that carry it with their kinds, and from there its
 * election through [onElect].
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun VoiceBlock(title: String, notes: List<ActivityNote>, color: Color, good: Boolean, onElect: ((Activity) -> Unit)?) {
    if (notes.isEmpty()) return
    val groups = remember(notes, good) { notes.byVoices(good) }
    val side = stringResource(if (good) R.string.good_for else R.string.avoid)
    val choose = stringResource(R.string.election_choose)
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text(title, style = body.copy(fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = color))
        for ((voices, works) in groups) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                FlowRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    voices.forEachIndexed { i, e ->
                        GlossText(e.kanji, e.english, body.copy(fontSize = 14.sp, fontWeight = FontWeight.SemiBold), preferAbove = true)
                        if (i < voices.lastIndex) Text("·", style = body.copy(fontSize = 14.sp, color = Palette.faint))
                    }
                }
                // Centred on each line: a name with Tibetan script stands taller than its neighbours.
                FlowRow(horizontalArrangement = Arrangement.spacedBy(14.dp), itemVerticalAlignment = Alignment.CenterVertically) {
                    for (n in works) {
                        var open by remember { mutableStateOf(false) }
                        Box {
                            Row(
                                Modifier
                                    .clickable(role = Role.Button) { open = !open }
                                    .padding(vertical = 5.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                            ) {
                                CueIcon(CueGlyphs.FAMILY.getValue(n.activity.family), color, 18.dp)
                                Text(n.activity.english, style = body.copy(fontSize = 15.sp))
                            }
                            if (open) {
                                Balloon(
                                    listOf(BalloonRow(side, n.activity.english)) + voices.map { BalloonRow(it.english, it.kanji) },
                                    preferAbove = true,
                                    actions = listOfNotNull(onElect?.let { elect -> BalloonAction(choose) { elect(n.activity) } }),
                                    onDismiss = { open = false },
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

/**
 * The Tibetan day's hours (SPEC §10.3, §5.13): the clock times of its combination periods to be
 * accomplished and to be avoided, and its nectar periods, each time opening its hour; then why
 * they stand above the day's weighing.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun HoursBlock(hours: DayHours, onHour: (Int) -> Unit) {
    // A list of clock times under a dot and a label; each time opens the two-hour period it starts in.
    @Composable
    fun Times(dot: Color, label: String, times: List<Pair<String, Int>>) {
        Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Box(Modifier.padding(top = 7.dp).size(8.dp).background(dot, CircleShape))
            Column {
                Text(label, style = body.copy(fontSize = 14.sp, color = Palette.muted))
                FlowRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    val open = stringResource(R.string.hours_open)
                    for ((time, hour) in times) {
                        Text(
                            time,
                            style = body.copy(fontSize = 16.sp),
                            modifier = Modifier
                                .clickable(role = Role.Button, onClickLabel = open) { onHour(hour) }
                                .padding(vertical = 6.dp),
                        )
                    }
                }
            }
        }
    }
    Block(stringResource(R.string.brief_by_hour)) {
        for (tone in listOf(Tone.GOOD, Tone.BAD)) {
            Times(
                toneColor(tone),
                stringResource(if (tone == Tone.GOOD) R.string.brief_hours_good else R.string.brief_hours_bad),
                hours.periods.filter { it.tone == tone }.map { clockSpan(5 * 60 + it.first * 120, it.count * 120) to it.first },
            )
        }
        Times(Palette.saffron, stringResource(R.string.tib_nectar_title), hours.nectar.map { clockSpan(5 * 60 + it * 60, 60) to it / 2 })
        Text(withTibetan(stringResource(R.string.brief_hours_note)), style = body.copy(fontSize = 14.sp, lineHeight = 20.sp, color = Palette.muted))
    }
}

/** What decided the Tibetan day's tone, in a few words for the summary line: the combination, or the factor by name (ROADMAP U2). */
@Composable
private fun byShort(v: DayVerdict): String = when (v.by) {
    VerdictBy.COMBINATION -> stringResource(R.string.brief_by_combination)
    VerdictBy.STRONGEST -> stringResource(R.string.brief_by_factor, v.deciding.joinToString { it.kanji })
}

/** Annotation names, each translated on tap. */
@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun Terms(entries: List<SummaryEntry>, small: Boolean = false, modifier: Modifier = Modifier, end: Boolean = false) {
    FlowRow(
        modifier,
        horizontalArrangement = if (end) Arrangement.spacedBy(12.dp, Alignment.End) else Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        for (e in entries) {
            GlossText(
                e.kanji,
                e.english,
                when {
                    e.latin && small -> body.copy(fontSize = 13.sp, color = Palette.muted)
                    e.latin -> body.copy(fontSize = 16.sp)
                    small -> termStyle.copy(fontSize = 13.sp, color = Palette.muted)
                    else -> termStyle
                },
                preferAbove = true,
            )
        }
    }
}
