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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
import zanshin.core.texts.HourNow
import zanshin.core.texts.HoursShortForm
import zanshin.core.texts.KyurekiElection
import zanshin.core.texts.VerdictBy
import zanshin.core.texts.SummaryEntry
import zanshin.core.texts.Texts
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
fun BriefRow(summary: DaySummary, onOpen: () -> Unit) {
    val briefLabel = stringResource(R.string.kyu_day_in_brief)
    val disputed = summary.activities.count { it.disputed }
    val dayLabel = summary.verdict?.let {
        stringResource(if (it.tone == Tone.GOOD) R.string.brief_day_good else R.string.brief_day_bad) + " · " + byShort(it)
    }
    val row = remember(summary) { summary.row() }
    val avoidAll = if (summary.avoidAll.isNotEmpty()) stringResource(R.string.brief_avoid_all) else null
    val spoken = stringResource(R.string.kyu_in_brief) + ": " + (dayLabel?.let { "$it, " } ?: "") + (avoidAll?.let { "$it, " } ?: "") +
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
            .clearAndSetSemantics { contentDescription = spoken },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(stringResource(R.string.kyu_in_brief), style = body.copy(fontSize = 14.sp, fontWeight = FontWeight.SemiBold))
                summary.verdict?.let { v ->
                    ToneDot(v.tone)
                    Text(dayLabel!!, style = body.copy(fontSize = 14.sp, color = Palette.muted))
                }
            }
            // The person's enemy weekday or death mansion: every work to avoid, beside the day's tone (ROADMAP E6).
            avoidAll?.let { Text(it, style = body.copy(fontSize = 14.sp, color = Palette.bad)) }
            // Both pages alike (ROADMAP U16): each side under its word and count, eight glyphs at most, a side with
            // fewer leaving its places to the other; the Tibetan line by the works' weights, the 旧暦's by family order.
            SidesLine(
                goodCount = if (avoidAll == null) summary.good.size else null,
                avoidCount = summary.avoid.size,
            ) { slots ->
                if (summary.verdict != null) {
                    val (good, avoid) = summary.row(slots)
                    good.map { it.activity.family to Palette.good } to avoid.map { it.activity.family to Palette.bad }
                } else {
                    val disputed = summary.disputedFamilies
                    fun colour(f: ActivityFamily, side: Color) = if (f in disputed) Palette.mixed else side
                    val (good, avoid) = summary.familyRow(slots)
                    good.map { it to colour(it, Palette.good) } to avoid.map { it to colour(it, Palette.bad) }
                }
            }
        }
        Icon(Icons.ChevronRight, contentDescription = null, tint = Palette.faint, modifier = Modifier.size(18.dp))
    }
}

/**
 * The In brief line of both pages (SPEC §10.7, ROADMAP U16): the families named good, then those to avoid, each
 * side under its word and its count in its tone ("good 53", "avoid 36"; no good side on the person's enemy
 * weekday). [sides] gives each side's glyphs and colours for the places that fit, eight at most.
 */
@Composable
private fun SidesLine(
    goodCount: Int?,
    avoidCount: Int,
    sides: (slots: Int) -> Pair<List<Pair<ActivityFamily, Color>>, List<Pair<ActivityFamily, Color>>>,
) {
    BoxWithConstraints(Modifier.fillMaxWidth()) {
        val fit = ((maxWidth - SIDE_GAP + GLYPH_GAP) / (GLYPH + GLYPH_GAP)).toInt()
        val (good, avoid) = sides(minOf(DaySummary.ROW_SLOTS, fit))
        Row(horizontalArrangement = Arrangement.spacedBy(SIDE_GAP)) {
            if (goodCount != null && (good.isNotEmpty() || goodCount > 0)) Side(stringResource(R.string.brief_row_good, goodCount), Palette.good, good)
            if (avoid.isNotEmpty() || avoidCount > 0) Side(stringResource(R.string.brief_row_avoid, avoidCount), Palette.bad, avoid)
        }
    }
}

/** One side of the In brief line: its word and count, its glyphs under them. */
@Composable
private fun Side(label: String, tone: Color, glyphs: List<Pair<ActivityFamily, Color>>) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(label, style = body.copy(fontSize = 12.sp, lineHeight = 14.sp, fontWeight = FontWeight.SemiBold, color = tone), maxLines = 1)
        Row(horizontalArrangement = Arrangement.spacedBy(GLYPH_GAP)) {
            for ((family, colour) in glyphs) CueIcon(CueGlyphs.FAMILY.getValue(family), colour, GLYPH)
        }
    }
}

private val GLYPH = 22.dp
private val GLYPH_GAP = 9.dp
private val SIDE_GAP = 20.dp

/**
 * The breakdown behind the day's summary line (ROADMAP R3): a listing on the 旧暦 page, the weighed
 * day on the Tibetan one (SPEC §5.12), with the hours above it; a time there calls [onHour] with its
 * two-hour period, counted from 05:00. A work's workings offer its election through [onElect], the
 * Tibetan one on the Tibetan page (ROADMAP E2), the 旧暦's on the 旧暦 page (E5). On today's Tibetan
 * page [s] is the lists of the present hour ([DaySummary.atHour]) and [day] the day's own weighing,
 * whose count against the tone the rule's sentence gives.
 */
@Composable
fun DaySummarySheet(
    s: DaySummary,
    day: DaySummary = s,
    festival: Boolean = false,
    onHour: (Int) -> Unit = {},
    onElect: ((Activity) -> Unit)? = null,
    onDismiss: () -> Unit,
) {
    Sheet(onDismiss) {
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
                    ToneDot(v.tone, modifier = Modifier.padding(top = 8.dp))
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
                        // The rule in a sentence (ROADMAP U6): a work goes to the strongest factor naming it, and the hour
                        // outweighs the day; on the person's enemy weekday or death mansion neither holds (SPEC §5.12).
                        // On today's page the hour is the present one, which the lists below follow (SPEC §10.3).
                        if (s.avoidAll.isEmpty()) {
                            val against = if (v.tone == Tone.GOOD) day.avoid.size else day.good.size
                            val rule = if (v.tone == Tone.GOOD) R.plurals.brief_rule_lucky else R.plurals.brief_rule_unlucky
                            val hour = s.now?.takeIf { it.good + it.avoid > 0 }?.let { now ->
                                val works = now.good + now.avoid
                                pluralStringResource(R.plurals.brief_rule_now, works, untilOf(now.hour), now.sign.english, works, now.good, now.avoid)
                            } ?: stringResource(R.string.brief_rule_hour)
                            Text(
                                (if (against > 0) pluralStringResource(rule, against, against) + " " else "") + hour,
                                style = body.copy(fontSize = 14.sp, lineHeight = 20.sp, color = Palette.muted),
                                modifier = Modifier.padding(top = 6.dp),
                            )
                        }
                    }
                }
            }

            if (s.avoidAll.isNotEmpty()) {
                Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    ToneDot(Tone.BAD, modifier = Modifier.padding(top = 8.dp))
                    Column {
                        Text(stringResource(R.string.brief_avoid_all_title), style = body.copy(fontSize = 18.sp))
                        Text(s.avoidAll.joinToString(" · ") { "${it.kanji}, ${it.english}" }, style = body.copy(fontSize = 14.sp, color = Palette.muted))
                    }
                }
            }

            if (s.verdict != null && festival) {
                Text(stringResource(R.string.brief_festival_note), style = body.copy(fontSize = 14.sp, color = Palette.muted))
            }

            // The hours before the works (ROADMAP U6): the one factor the texts put above the day.
            s.hours?.let { HoursBlock(it, onHour) }

            WorkBlock(stringResource(R.string.good_for), s.good, Palette.good, good = true, tibetan = s.verdict != null, s.now, onElect)
            WorkBlock(stringResource(R.string.avoid), s.avoid, Palette.bad, good = false, tibetan = s.verdict != null, s.now, onElect)

            Block(stringResource(R.string.brief_by_tone)) {
                for (tone in listOf(Tone.GOOD, Tone.MIXED, Tone.BAD)) {
                    val entries = s.byTone[tone].orEmpty()
                    if (entries.isEmpty()) continue
                    Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        ToneDot(tone, modifier = Modifier.padding(top = 6.dp))
                        Terms(entries)
                    }
                }
            }

            if (s.setAside.isNotEmpty()) {
                Block(stringResource(R.string.brief_set_aside)) {
                    for ((by, entries) in s.setAside) {
                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                ToneDot(by.tone)
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
                                ToneDot(e.tone, modifier = Modifier.padding(top = 6.dp))
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
                            ToneDot(a.relation.tone)
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
 * The day's works on one side, one row each (SPEC §10.3, §10.4): the family's glyph, the work, and the
 * annotations naming it right-aligned and muted, each tapped for its English or, on the Tibetan page,
 * for its kind ("weekday"). The Tibetan brief takes the 旧暦's row (ROADMAP U10): the work first, its
 * voices after it; until then each group of works stood under its voices, which took the eye. A work
 * opens a balloon with its side and its annotations and "Choose a day for it" through [onElect]:
 * every Tibetan work opens it, its election from the Tibetan page (ROADMAP E2); a 旧暦 work only when
 * the 旧暦 election is offered for it (E5).
 */
@Composable
private fun WorkBlock(title: String, notes: List<ActivityNote>, color: Color, good: Boolean, tibetan: Boolean, now: HourNow?, onElect: ((Activity) -> Unit)?) {
    if (notes.isEmpty()) return
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(title, style = body.copy(fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = color))
        for (n in notes) WorkLine(n.activity, n, good, color, tibetan, now, onElect)
    }
}

/**
 * One work in the brief's form, as [WorkBlock] describes it, on [good]'s side in [color]. [n] is null for a
 * pinned work no voice names today (ROADMAP U19): its glyph in [color], "Not named today" in place of its
 * voices, its balloon the way to its election only. The side is spoken after the work, which on the Works
 * section has no side's heading over it. [title] is the work's name as the row shows it: capitalised on the Works
 * section, where it stands among rows that are.
 */
@Composable
internal fun WorkLine(
    activity: Activity,
    n: ActivityNote?,
    good: Boolean,
    color: Color,
    tibetan: Boolean,
    now: HourNow?,
    onElect: ((Activity) -> Unit)?,
    title: String = activity.english,
) {
    val until = now?.let { untilOf(it.hour) }
    val unnamed = stringResource(R.string.works_unnamed)
    val side = if (n == null) unnamed else stringResource(if (good) R.string.good_for else R.string.avoid)
    val choose = stringResource(R.string.election_choose)
    // A work the present hour decides has the hour as its one voice (SPEC §10.3): the day's are outweighed.
    val period = n?.period
    val voices = when {
        n == null -> emptyList()
        period != null -> {
            val tone = (n.good + n.avoid).first().tone
            listOf(SummaryEntry(stringResource(R.string.brief_by_the_hour, period.english), stringResource(R.string.brief_period_kind), tone, null, latin = true))
        }
        good -> n.good
        else -> n.avoid
    }
    BoxWithConstraints {
        // The label takes its own width up to 55% of the row and wraps by words past
        // that; the annotations fill the rest, right-aligned. Without the cap a wide
        // set of annotations squeezed the label into a one-letter column.
        val labelMax = maxWidth * 0.55f
        Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            CueIcon(CueGlyphs.of(activity), if (n?.disputed == true) Palette.mixed else color, 22.dp)
            val elect = if (tibetan) onElect else onElect?.takeIf { activity in KyurekiElection.OFFERED }
            val opens = tibetan || elect != null
            var open by remember { mutableStateOf(false) }
            Column(
                Modifier
                    .widthIn(max = labelMax)
                    .then(if (opens) Modifier.clickable(role = Role.Button) { open = !open } else Modifier)
                    .semantics { stateDescription = side },
            ) {
                Text(title, style = body.copy(fontSize = 16.sp))
                if (open && opens) {
                    Balloon(
                        listOf(BalloonRow(side, activity.english)) + (
                            period?.let { sign -> listOf(BalloonRow(stringResource(R.string.brief_period_kind), stringResource(R.string.brief_period_until, sign.english, until.orEmpty()))) }
                                ?: voices.map { BalloonRow(it.english, it.kanji) }
                            ),
                        preferAbove = true,
                        actions = listOfNotNull(elect?.let { BalloonAction(choose) { it(activity) } }),
                        onDismiss = { open = false },
                    )
                }
                if (n?.disputed == true) {
                    Text(
                        stringResource(if (good) R.string.brief_also_avoid else R.string.brief_also_good),
                        style = body.copy(fontSize = 12.sp, color = Palette.mixed),
                    )
                }
            }
            if (n == null) {
                Text(unnamed, style = body.copy(fontSize = 13.sp, color = Palette.faint, textAlign = TextAlign.End), modifier = Modifier.weight(1f).clearAndSetSemantics {})
            } else {
                Terms(voices, small = true, modifier = Modifier.weight(1f), end = true)
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
            Box(Modifier.padding(top = 6.dp).size(10.dp).background(dot, CircleShape))
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

/** The clock time two-hour period [hour], counted from 05:00, ends at ("23:00"). */
private fun untilOf(hour: Int): String = "%02d:00".format(HoursShortForm.startHour(hour + 1))

/** What decided the Tibetan day's tone, in a few words for the summary line and the Almanac's heading: the combination, or the factor by name (ROADMAP U2, U6). */
@Composable
internal fun byShort(v: DayVerdict): String = when (v.by) {
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
