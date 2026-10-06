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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.iverlein.zanshin.R
import zanshin.core.kyureki.DayMark
import zanshin.core.kyureki.Tone
import zanshin.core.texts.ActivityFamily
import zanshin.core.texts.ActivityNote
import zanshin.core.texts.DaySummary
import zanshin.core.texts.VerdictBy
import zanshin.core.texts.SummaryEntry
import zanshin.core.texts.family

private val termStyle get() = body.copy(fontFamily = Mincho, fontWeight = FontWeight.Bold, fontSize = 15.sp)

/**
 * The day's summary line (SPEC §10.4): the glyphs of the activity families
 * the annotations name good and to avoid, a family in the mixed colour when
 * one of its activities is named both ways; opens [DaySummarySheet]. Screen
 * readers get the counts.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BriefRow(summary: DaySummary, onOpen: () -> Unit) {
    val briefLabel = stringResource(R.string.kyu_day_in_brief)
    val disputed = summary.activities.count { it.disputed }
    val dayLabel = summary.verdict?.let {
        stringResource(if (it.tone == Tone.GOOD) R.string.brief_day_good else R.string.brief_day_bad) + " · " + stringResource(byShort(it.by))
    }
    val spoken = stringResource(R.string.kyu_in_brief) + ": " + (dayLabel?.let { "$it, " } ?: "") +
        stringResource(R.string.kyu_brief_counts, summary.good.size, summary.avoid.size) +
        if (disputed > 0) stringResource(R.string.kyu_brief_disputed, disputed) else ""
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
                    Box(Modifier.size(8.dp).background(toneColor(v.tone), CircleShape))
                    Text(dayLabel!!, style = body.copy(fontSize = 14.sp, color = Palette.muted))
                }
            }
            FamilyLine(stringResource(R.string.brief_good), summary.goodFamilies, summary.disputedFamilies, Palette.good)
            FamilyLine(stringResource(R.string.brief_avoid), summary.avoidFamilies, summary.disputedFamilies, Palette.bad)
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

/** The breakdown behind the day's summary line (ROADMAP R3): a listing on the 旧暦 page, the weighed day on the Tibetan one (SPEC §5.12). */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DaySummarySheet(s: DaySummary, festival: Boolean = false, onDismiss: () -> Unit) {
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
                            stringResource(
                                when (v.by) {
                                    VerdictBy.COMBINATION -> R.string.brief_day_combination
                                    VerdictBy.STRONGEST -> R.string.brief_day_strongest
                                },
                            ),
                            style = body.copy(fontSize = 14.sp, color = Palette.muted),
                        )
                    }
                }
            }

            if (s.verdict != null && festival) {
                Text(stringResource(R.string.brief_festival_note), style = body.copy(fontSize = 14.sp, color = Palette.muted))
            }

            ActivityBlock(stringResource(R.string.good_for), s.good, Palette.good, good = true)
            ActivityBlock(stringResource(R.string.avoid), s.avoid, Palette.bad, good = false)

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
                    if (s.personal.isNotEmpty()) Terms(s.personal)
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
                stringResource(if (s.verdict != null) R.string.brief_note_tibetan else R.string.brief_note),
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

@Composable
private fun ActivityBlock(title: String, notes: List<ActivityNote>, color: Color, good: Boolean) {
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
                    Column(Modifier.widthIn(max = labelMax)) {
                        Text(n.activity.english, style = body.copy(fontSize = 16.sp))
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

/** What decided the Tibetan day's tone, in a few words for the summary line. */
private fun byShort(by: VerdictBy): Int = when (by) {
    VerdictBy.COMBINATION -> R.string.brief_by_combination
    VerdictBy.STRONGEST -> R.string.brief_by_strongest
}

/** Annotation names, each translated on tap. */
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun Terms(entries: List<SummaryEntry>, small: Boolean = false, modifier: Modifier = Modifier, end: Boolean = false) {
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
