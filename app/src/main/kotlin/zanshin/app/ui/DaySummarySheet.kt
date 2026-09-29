/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zanshin.core.kyureki.DayMark
import zanshin.core.kyureki.Tone
import zanshin.core.texts.ActivityNote
import zanshin.core.texts.DaySummary
import zanshin.core.texts.SummaryEntry

private val termStyle get() = body.copy(fontFamily = Mincho, fontWeight = FontWeight.Bold, fontSize = 15.sp)

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
            Text("The day in brief", style = body.copy(fontSize = 22.sp, fontWeight = FontWeight.SemiBold))

            s.mark?.let { mark ->
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    DayMarkGlyph(mark, Palette.text, 12.dp)
                    GlossText(mark.senjitsu.kanji, mark.senjitsu.english, termStyle.copy(fontSize = 18.sp), reading = mark.senjitsu.reading)
                    Text(
                        when (mark) {
                            DayMark.BLACK -> "the almanac's black day: no other annotation need be read"
                            DayMark.PARDON -> "the almanac notes it good for all"
                        },
                        style = body.copy(fontSize = 14.sp, color = Palette.muted),
                    )
                }
            }

            ActivityBlock("Good for", s.good, Palette.good, good = true)
            ActivityBlock("Avoid", s.avoid, Palette.bad, good = false)

            Block("By tone") {
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
                Block("For you") {
                    if (s.personal.isNotEmpty()) Terms(s.personal)
                    s.affinity?.let { a ->
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            Box(Modifier.size(8.dp).background(toneColor(a.relation.tone), CircleShape))
                            GlossText(a.relation.kanji, a.relation.english, termStyle, reading = a.relation.reading)
                            Text("birth star and day star: ${a.cycleEnglish}", style = body.copy(fontSize = 14.sp, color = Palette.muted))
                        }
                    }
                }
            }

            Text(
                "Listed as the sources give them. No published rule says which annotation outranks another, " +
                    "so none is weighed against the others; where they disagree, both are shown.",
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
                            if (good) "also named to avoid" else "also named good",
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
                if (small) termStyle.copy(fontSize = 13.sp, color = Palette.muted) else termStyle,
                preferAbove = true,
            )
        }
    }
}
