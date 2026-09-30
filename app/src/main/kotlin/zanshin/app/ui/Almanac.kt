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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zanshin.core.kyureki.DayMark
import zanshin.core.kyureki.Tone
import zanshin.core.texts.Reading

fun toneColor(tone: Tone): Color = when (tone) {
    Tone.GOOD -> Palette.good
    Tone.BAD -> Palette.bad
    Tone.MIXED -> Palette.mixed
    Tone.NEUTRAL -> Palette.faint
}

fun toneLabel(tone: Tone): String = when (tone) {
    Tone.GOOD -> "lucky"
    Tone.BAD -> "unlucky"
    Tone.MIXED -> "mixed"
    Tone.NEUTRAL -> "neutral"
}

/**
 * The almanac's own mark for the day, drawn rather than set in type: a black
 * dot for 受死日, a ring for 天赦日. [color] is the text colour, so the dot
 * reads on both themes.
 */
@Composable
fun DayMarkGlyph(mark: DayMark, color: Color, size: Dp, modifier: Modifier = Modifier) {
    val fill = when (mark) {
        DayMark.BLACK -> Modifier.background(color, CircleShape)
        DayMark.PARDON -> Modifier.border(size / 5, color, CircleShape)
    }
    Box(modifier.size(size).then(fill))
}

/**
 * A Tibetan word or kanji. Tapping it shows its English (and reading) in a
 * balloon; there is no underline — every such term is tappable (SPEC §10.1).
 */
@Composable
fun GlossText(
    text: String,
    english: String,
    style: TextStyle,
    reading: String? = null,
    preferAbove: Boolean = false,
    modifier: Modifier = Modifier,
    /** Replaces the reading and English rows, for terms with more to say (Tibetan: Wylie, phonetics, English). */
    rows: List<BalloonRow>? = null,
    /** What a screen reader says for [text]. */
    spoken: String = text,
) {
    var open by remember { mutableStateOf(false) }
    Box(modifier) {
        Text(
            text = text,
            style = style,
            modifier = Modifier
                .clickable(role = Role.Button, onClickLabel = "English") { open = !open }
                .semantics { contentDescription = "$spoken, $english" },
        )
        if (open) {
            Balloon(
                rows = rows ?: listOfNotNull(
                    reading?.let { BalloonRow("Reading", it) },
                    BalloonRow("English", english),
                ),
                preferAbove = preferAbove,
                onDismiss = { open = false },
            )
        }
    }
}

@Composable
fun SectionTitle(text: String) {
    Text(
        text.uppercase(),
        style = body.copy(fontSize = 12.sp, fontWeight = FontWeight.SemiBold, letterSpacing = 1.2.sp, color = Palette.faint),
        modifier = Modifier.padding(top = 8.dp),
    )
}

/** What an annotation row opens: the term, its gloss and its sourced reading. */
data class Annotation(
    val title: String,
    val english: String,
    val tone: Tone,
    val reading: Reading?,
    val subtitle: String? = null,
    val titleIsKanji: Boolean = true,
    /** What a screen reader says for [title], when the title holds marks such as pebbles. */
    val spokenTitle: String? = null,
)

/** One annotation in a list: tone dot, term, English; opens its reading. */
@Composable
fun AnnotationRow(a: Annotation, onOpen: (Annotation) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .clickable(role = Role.Button, onClickLabel = "Meaning") { onOpen(a) }
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Box(
            Modifier
                .size(8.dp)
                .background(toneColor(a.tone), CircleShape)
                .semantics { contentDescription = toneLabel(a.tone) },
        )
        Column(Modifier.weight(1f)) {
            Text(
                a.title,
                style = if (a.titleIsKanji) {
                    body.copy(fontFamily = Mincho, fontWeight = FontWeight.Bold, fontSize = 17.sp)
                } else {
                    body.copy(fontSize = 16.sp, fontWeight = FontWeight.Medium)
                },
                modifier = a.spokenTitle?.let { spoken -> Modifier.semantics { contentDescription = spoken } } ?: Modifier,
            )
            Text(a.subtitle ?: a.english, style = body.copy(fontSize = 13.sp, color = Palette.muted))
        }
    }
}

/** The reading of an annotation, as a bottom sheet with its source and licence. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReadingSheet(a: Annotation, onDismiss: () -> Unit) {
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
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Box(Modifier.size(10.dp).background(toneColor(a.tone), CircleShape))
                Text(
                    a.title,
                    style = if (a.titleIsKanji) {
                        body.copy(fontFamily = Mincho, fontWeight = FontWeight.Bold, fontSize = 28.sp)
                    } else {
                        body.copy(fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
                    },
                )
            }
            Text("${a.english} · ${toneLabel(a.tone)}", style = body.copy(color = Palette.muted))
            val r = a.reading
            if (r == null) {
                Text("No published reading for this entry.", style = body.copy(color = Palette.faint))
                return@Column
            }
            if (r.summary.isNotBlank()) Text(r.summary, style = body.copy(fontSize = 16.sp, lineHeight = 23.sp))
            if (r.good.isNotEmpty()) ListBlock("Good for", r.good, Palette.good)
            if (r.avoid.isNotEmpty()) ListBlock("Avoid", r.avoid, Palette.bad)
            Spacer(Modifier.heightIn(min = 4.dp))
            SelectionContainer {
                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Text(if (r.also.isEmpty()) "Source" else "Sources", style = body.copy(fontSize = 12.sp, color = Palette.faint))
                    for (s in listOf(r.source) + r.also) {
                        Text("${s.title} — ${s.publisher}", style = body.copy(fontSize = 13.sp, color = Palette.muted))
                        Text(s.url, style = body.copy(fontSize = 12.sp, color = Palette.faint))
                    }
                    Text(r.license.label, style = body.copy(fontSize = 12.sp, color = Palette.faint))
                }
            }
        }
    }
}

@Composable
private fun ListBlock(title: String, items: List<String>, color: Color) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(title, style = body.copy(fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = color))
        Text(items.joinToString(" · "), style = body.copy(lineHeight = 21.sp))
    }
}
