/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupPositionProvider
import androidx.compose.ui.window.PopupProperties
import io.github.iverlein.zanshin.R
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos

val body = TextStyle(fontFamily = Figtree, fontSize = 15.sp, color = Palette.text)

/**
 * Display text in Mincho with the line box trimmed to [ratio] of the font
 * size: Shippori's line metrics are sized for kanji and leave a large empty
 * band above Latin numerals.
 */
fun TextStyle.tight(ratio: Float = 0.9f): TextStyle = copy(
    lineHeight = fontSize * ratio,
    lineHeightStyle = LineHeightStyle(LineHeightStyle.Alignment.Center, LineHeightStyle.Trim.Both),
)

/**
 * Crops a text's box to its cap height: from [capRatio] × [fontSize] above
 * the first baseline down to the baseline. For large Mincho numerals, whose
 * font box reserves room for kanji above and descenders below.
 */
fun Modifier.capBox(fontSize: TextUnit, capRatio: Float = 0.72f): Modifier = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)
    val baseline = placeable[FirstBaseline]
    if (baseline == AlignmentLine.Unspecified) {
        return@layout layout(placeable.width, placeable.height) { placeable.place(0, 0) }
    }
    val top = (baseline - (fontSize.toPx() * capRatio).toInt()).coerceAtLeast(0)
    layout(placeable.width, baseline - top) { placeable.place(0, -top) }
}

/** A thin dotted line under text that opens a balloon: the design's cue for "tap for details". */
fun Modifier.dottedUnderline(color: Color = Palette.faint, offset: Dp = 3.dp): Modifier = drawBehind {
    val y = size.height + offset.toPx()
    drawLine(
        color = color,
        start = Offset(0f, y),
        end = Offset(size.width, y),
        strokeWidth = 1.2.dp.toPx(),
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(2.dp.toPx(), 3.dp.toPx())),
    )
}

/**
 * Places a balloon below its anchor, or above when the space below is too
 * small; clamped to the window with a 16 dp gutter.
 */
private class BalloonPosition(private val gapPx: Int, private val gutterPx: Int, private val preferAbove: Boolean) :
    PopupPositionProvider {
    override fun calculatePosition(
        anchorBounds: IntRect,
        windowSize: IntSize,
        layoutDirection: LayoutDirection,
        popupContentSize: IntSize,
    ): IntOffset {
        val below = anchorBounds.bottom + gapPx
        val above = anchorBounds.top - gapPx - popupContentSize.height
        val fitsBelow = below + popupContentSize.height <= windowSize.height - gutterPx
        val fitsAbove = above >= gutterPx
        val y = when {
            preferAbove && fitsAbove -> above
            fitsBelow -> below
            fitsAbove -> above
            else -> below
        }
        val x = anchorBounds.left.coerceIn(gutterPx, (windowSize.width - gutterPx - popupContentSize.width).coerceAtLeast(gutterPx))
        return IntOffset(x, y)
    }
}

/** One label–value pair in a balloon. */
data class BalloonRow(val label: String, val value: String, val valueStyle: TextStyle? = null)

@Composable
fun Balloon(rows: List<BalloonRow>, preferAbove: Boolean = false, onDismiss: () -> Unit) {
    val density = androidx.compose.ui.platform.LocalDensity.current
    val provider = with(density) { BalloonPosition(10.dp.roundToPx(), 16.dp.roundToPx(), preferAbove) }
    Popup(popupPositionProvider = provider, onDismissRequest = onDismiss, properties = PopupProperties(focusable = true)) {
        Column(
            modifier = Modifier
                .widthIn(min = 220.dp, max = 320.dp)
                .shadow(16.dp, RoundedCornerShape(14.dp))
                .background(Palette.raised, RoundedCornerShape(14.dp))
                .border(1.dp, Palette.lineStrong, RoundedCornerShape(14.dp))
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            for (row in rows) {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text(row.label, style = body.copy(fontSize = 14.sp, color = Palette.muted))
                    Box(Modifier.weight(1f), contentAlignment = Alignment.CenterEnd) {
                        Text(row.value, style = row.valueStyle ?: body.copy(fontSize = 14.sp))
                    }
                }
            }
        }
    }
}

/** Text that opens a balloon when tapped, marked by a dotted underline. */
@Composable
fun BalloonText(
    text: String,
    style: TextStyle,
    open: Boolean,
    onToggle: () -> Unit,
    rows: List<BalloonRow>,
    preferAbove: Boolean = false,
    description: String? = null,
    underline: Boolean = true,
) = BalloonText(AnnotatedString(text), style, open, onToggle, rows, preferAbove, description, underline)

/** As above, for a line that mixes scripts (a Tibetan month name after its number). */
@Composable
fun BalloonText(
    text: AnnotatedString,
    style: TextStyle,
    open: Boolean,
    onToggle: () -> Unit,
    rows: List<BalloonRow>,
    preferAbove: Boolean = false,
    description: String? = null,
    /** Lines that are terms (Tibetan or kanji) carry no underline: every term is tappable. */
    underline: Boolean = true,
) {
    Box {
        Text(
            text = text,
            style = style,
            modifier = Modifier
                .heightIn(min = 44.dp)
                .clickable(role = Role.Button, onClick = onToggle)
                .padding(vertical = 8.dp)
                .let { if (underline) it.dottedUnderline() else it }
                .let { m -> if (description != null) m.semantics { contentDescription = description } else m },
        )
        if (open) Balloon(rows, preferAbove, onDismiss = onToggle)
    }
}

/** Placeholder for a festival or holiday glyph; the artwork is chosen later (SPEC §10.6). */
@Composable
fun GlyphRing(color: Color, size: Dp) {
    Canvas(Modifier.size(size)) {
        val stroke = 1.5.dp.toPx()
        drawCircle(
            color = color,
            radius = this.size.minDimension / 2 - stroke,
            style = Stroke(width = stroke, pathEffect = PathEffect.dashPathEffect(floatArrayOf(5.dp.toPx(), 4.dp.toPx()))),
        )
    }
}

/**
 * The moon as seen at [elongation] degrees from the sun: the lit limb on
 * the right while waxing, on the left while waning.
 */
@Composable
fun MoonGlyph(elongation: Double, size: Dp, description: String) {
    Canvas(Modifier.size(size).semantics { contentDescription = description }) {
        val r = this.size.minDimension / 2 - 1.dp.toPx()
        val cx = this.size.width / 2
        val cy = this.size.height / 2
        drawCircle(Palette.raised, r)
        drawCircle(Palette.lineStrong, r, style = Stroke(1.dp.toPx()))

        val e = ((elongation % 360) + 360) % 360
        val waxing = e < 180
        val crescent = e < 90 || e > 270
        val rx = (abs(cos(e * PI / 180)) * r).toFloat()
        // Lit limb: a half circle on the lit side. Terminator: a half ellipse
        // bulging towards the lit side for a crescent, away from it past quarter.
        val limbSign = if (waxing) 1f else -1f
        val terminatorSign = if (crescent) limbSign else -limbSign
        val path = Path().apply {
            val steps = 48
            for (i in 0..steps) {
                val t = -PI / 2 + PI * i / steps
                val x = cx + limbSign * r * cos(t).toFloat()
                val y = cy + r * kotlin.math.sin(t).toFloat()
                if (i == 0) moveTo(x, y) else lineTo(x, y)
            }
            for (i in 0..steps) {
                val t = PI / 2 - PI * i / steps
                val x = cx + terminatorSign * rx * cos(t).toFloat()
                val y = cy + r * kotlin.math.sin(t).toFloat()
                lineTo(x, y)
            }
            close()
        }
        drawPath(path, Palette.text)
    }
}

/** A disabled row where a sourced text will open once sourcing is done (SPEC §8). */
@Composable
fun PendingTextRow(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .background(Palette.surface, RoundedCornerShape(12.dp))
            .border(1.dp, Palette.line, RoundedCornerShape(12.dp))
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(title, style = body.copy(fontSize = 14.sp, color = Palette.muted))
        Text(stringResource(R.string.text_pending_source), style = body.copy(fontSize = 12.sp, color = Palette.faint))
    }
}

@Composable
fun Chip(text: String) {
    Text(
        text = text,
        style = body.copy(fontSize = 13.sp, fontWeight = FontWeight.Medium),
        modifier = Modifier
            .background(Palette.raised, RoundedCornerShape(14.dp))
            .border(1.dp, Palette.lineStrong, RoundedCornerShape(14.dp))
            .padding(horizontal = 12.dp, vertical = 6.dp),
    )
}
