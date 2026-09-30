/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.iverlein.zanshin.R
import zanshin.core.kyureki.DayMark
import zanshin.core.kyureki.Tone
import zanshin.core.texts.ActivityNote
import zanshin.core.texts.DaySummary
import zanshin.core.texts.SummaryEntry

private val termStyle get() = body.copy(fontFamily = Mincho, fontWeight = FontWeight.Bold, fontSize = 15.sp)

/** The day's summary line: how many activities are named good, to avoid, or both; opens [DaySummarySheet]. */
@Composable
fun BriefRow(summary: DaySummary, onOpen: () -> Unit) {
    val briefLabel = stringResource(R.string.kyu_day_in_brief)
    Row(
        Modifier.fillMaxWidth().heightIn(min = 44.dp).clickable(role = Role.Button, onClickLabel = briefLabel, onClick = onOpen),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        val disputed = summary.activities.count { it.disputed }
        Text(stringResource(R.string.kyu_in_brief), style = body.copy(fontSize = 14.sp, fontWeight = FontWeight.SemiBold))
        Text(
            stringResource(R.string.kyu_brief_counts, summary.good.size, summary.avoid.size) +
                if (disputed > 0) stringResource(R.string.kyu_brief_disputed, disputed) else "",
            style = body.copy(fontSize = 14.sp, color = Palette.muted),
            modifier = Modifier.weight(1f),
        )
        Icon(Icons.ChevronRight, contentDescription = null, tint = Palette.faint, modifier = Modifier.size(18.dp))
    }
}

/** The breakdown behind the day's summary line (ROADMAP R3): a listing, never a verdict. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DaySummarySheet(s: DaySummary, onDismiss: () -> Unit) {
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
                stringResource(R.string.brief_note),
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
            Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Column(Modifier.weight(1f)) {
                    Text(n.activity.english, style = body.copy(fontSize = 16.sp))
                    if (n.disputed) {
                        Text(
                            stringResource(if (good) R.string.brief_also_avoid else R.string.brief_also_good),
                            style = body.copy(fontSize = 12.sp, color = Palette.mixed),
                        )
                    }
                }
                Terms(if (good) n.good else n.avoid, small = true)
            }
        }
    }
}

/** Annotation names, each translated on tap. */
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun Terms(entries: List<SummaryEntry>, small: Boolean = false) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
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
