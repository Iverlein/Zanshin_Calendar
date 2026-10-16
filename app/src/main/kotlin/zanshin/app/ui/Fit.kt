/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.material3.Text
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

/*
 * The page at any font scale and width (SPEC §10.1, ROADMAP A2): two columns from 600 dp, label columns as wide as
 * their widest label, and diagrams that grow with the room and the font scale, their labels with them.
 */

/** The width from which a day page stands in two columns: a phone in landscape, a tablet either way. */
val TWO_COLUMNS = 600.dp

/** Both columns together at most this wide, centred: lines past it are too long to read. */
private val PAGE_MAX = 1040.dp

/**
 * A day page: on a phone held upright one column, [top] (the headline, the hours and the brief) over a rule and
 * [rest] (the sections); from [TWO_COLUMNS] the two side by side, each scrolling on its own, so that the headline
 * stays in sight while the sections are read.
 */
@Composable
fun PageColumns(modifier: Modifier = Modifier, top: @Composable ColumnScope.() -> Unit, rest: @Composable ColumnScope.() -> Unit) {
    val spacing = Arrangement.spacedBy(18.dp)
    BoxWithConstraints(modifier.fillMaxSize()) {
        if (maxWidth < TWO_COLUMNS) {
            Column(
                Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 28.dp, vertical = 20.dp),
                verticalArrangement = spacing,
            ) {
                top()
                Spacer(Modifier.fillMaxWidth().height(1.dp).background(Palette.line))
                rest()
            }
        } else {
            Row(Modifier.fillMaxHeight().widthIn(max = PAGE_MAX).align(Alignment.TopCenter)) {
                Column(
                    Modifier
                        .weight(0.45f)
                        .fillMaxHeight()
                        .verticalScroll(rememberScrollState())
                        .padding(start = 28.dp, end = 20.dp, top = 20.dp, bottom = 20.dp),
                    verticalArrangement = spacing,
                    content = top,
                )
                Box(Modifier.padding(vertical = 20.dp).fillMaxHeight().width(1.dp).background(Palette.line))
                Column(
                    Modifier
                        .weight(0.55f)
                        .fillMaxHeight()
                        .verticalScroll(rememberScrollState())
                        .padding(start = 20.dp, end = 28.dp, top = 20.dp, bottom = 20.dp),
                    verticalArrangement = spacing,
                    content = rest,
                )
            }
        }
    }
}

/**
 * The first of [choices] that fits on one line, the last one cut if none does: the header's date, whole on a
 * phone at font scale 1.0, without its year or its weekday at larger ones. A screen reader hears the first.
 */
@Composable
fun FittedText(choices: List<String>, style: TextStyle, modifier: Modifier = Modifier) {
    val measurer = rememberTextMeasurer()
    BoxWithConstraints(modifier) {
        val max = constraints.maxWidth
        val text = remember(choices, style, max) {
            choices.firstOrNull { measurer.measure(it, style, maxLines = 1, softWrap = false).size.width <= max } ?: choices.last()
        }
        Text(
            text,
            style = style,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.semantics { contentDescription = choices.first() },
        )
    }
}

/** A screen of its own (the election, meditation, health…) at most this wide, centred. */
private val SCREEN_MAX = 640.dp

/**
 * A screen of its own at most 640 dp wide and centred on the app's ground: from a phone in landscape on, its rows
 * would stretch across the whole width, the label at one edge and its value at the other (ROADMAP A2).
 */
@Composable
fun ReadingWidth(content: @Composable () -> Unit) {
    Box(Modifier.fillMaxSize().background(Palette.ink), contentAlignment = Alignment.TopCenter) {
        Box(Modifier.widthIn(max = SCREEN_MAX)) { content() }
    }
}

/**
 * Text under a dotted line, as [dottedUnderline] draws it, but a line under each of its lines: a call to act that
 * wraps at a large font instead of being cut ("Set a location for sunrise and sunset" at font scale 2.0).
 */
@Composable
fun UnderlinedText(text: String, style: TextStyle, modifier: Modifier = Modifier) {
    var layout by remember { mutableStateOf<TextLayoutResult?>(null) }
    val colour = Palette.faint
    Text(
        text,
        style = style,
        onTextLayout = { layout = it },
        modifier = modifier.drawBehind {
            val l = layout ?: return@drawBehind
            for (i in 0 until l.lineCount) {
                val y = l.getLineBottom(i) + 3.dp.toPx()
                drawLine(
                    color = colour,
                    start = Offset(l.getLineLeft(i), y),
                    end = Offset(l.getLineRight(i), y),
                    strokeWidth = 1.2.dp.toPx(),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(2.dp.toPx(), 3.dp.toPx())),
                )
            }
        },
    )
}

/** The width of the label column of the [FactRow]s inside a [FactColumn]. */
val LocalFactLabelWidth = compositionLocalOf { 120.dp }

val factLabelStyle: TextStyle get() = body.copy(fontSize = 14.sp, color = Palette.muted)

/**
 * [FactRow]s whose labels share one column, as wide as the widest of [labels] on one line, 120 dp at least and at
 * most 40 % of the row; past that a label wraps between words, the column then as wide as its longest word, so that
 * no word is broken ("Солнечн-ый" at font scale 2.0). The cap leaves the value its room: at 45 % "Вода-Свинья" broke
 * inside its last word at font scale 1.5.
 */
@Composable
fun FactColumn(labels: List<String>, modifier: Modifier = Modifier, verticalArrangement: Arrangement.Vertical = Arrangement.Top, content: @Composable ColumnScope.() -> Unit) {
    val measurer = rememberTextMeasurer()
    val density = LocalDensity.current
    val style = factLabelStyle
    BoxWithConstraints(modifier) {
        val (line, word) = remember(labels, density, style) {
            // A label naming a Tibetan word puts its bracket on a line of its own (FactRow).
            val lines = labels.flatMap { it.split(" (").let { p -> if (p.size > 1) listOf(p[0], "(" + p.drop(1).joinToString(" (")) else p } }
            fun width(text: String) = with(density) { measurer.measure(text, style, maxLines = 1, softWrap = false).size.width.toDp() }
            lines.maxOf(::width) to lines.flatMap { it.split(' ') }.maxOf(::width)
        }
        val pad = 8.dp
        val width = maxOf(120.dp, minOf(line + pad, maxWidth * 0.4f), minOf(word + pad, maxWidth * 0.5f))
        CompositionLocalProvider(LocalFactLabelWidth provides width) {
            Column(verticalArrangement = verticalArrangement, content = content)
        }
    }
}

/**
 * At least [ratio] wide for its height, taller when its text needs it: a grid cell that holds its shape at font
 * scale 1.0 and does not clip its figures at 2.0.
 */
fun Modifier.atLeastAspect(ratio: Float): Modifier = layout { measurable, constraints ->
    val least = if (constraints.hasBoundedWidth) (constraints.maxWidth / ratio).roundToInt().coerceAtMost(constraints.maxHeight) else 0
    val placeable = measurable.measure(constraints.copy(minHeight = maxOf(constraints.minHeight, least)))
    layout(placeable.width, placeable.height) { placeable.place(0, 0) }
}

/**
 * Measures text for a drawing that fills the width it is given, such as the combination table's 27 columns: its
 * labels grow with the room past a phone's, not with the font scale, which would set them over each other.
 */
@Composable
fun rememberDrawingMeasurer(): TextMeasurer {
    val user = LocalDensity.current
    val room = minOf(LocalConfiguration.current.screenWidthDp.dp, 640.dp) - 48.dp
    val scale = minOf(user.fontScale, maxOf(1f, room / PHONE_ROOM))
    val resolver = LocalFontFamilyResolver.current
    val direction = LocalLayoutDirection.current
    return remember(user.density, scale, resolver, direction) { TextMeasurer(resolver, Density(user.density, scale), direction) }
}

/** The density the reader set, inside a [DiagramFrame] that draws at its own; text read as text uses it. */
val LocalUserDensity = compositionLocalOf<Density?> { null }

/** The room a phone's sheet gives a diagram at 411 dp: a diagram does not grow there at font scale 1.0. */
private val PHONE_ROOM = 363.dp

/** No diagram grows past this side. */
private val DIAGRAM_MAX = 360.dp

/**
 * A diagram in a reading sheet, grown with the room and the font scale (ROADMAP A2): drawn at its own size on a
 * phone at font scale 1.0, it grows by the font scale, or by the room on a tablet, until its side is 360 dp or the
 * room's width. Everything in it grows together, strokes and cells with the side, so that labels keep their places
 * on the rings; its labels grow with the diagram and never past the reader's font scale, its caption is read at the
 * reader's ([AtUserScale]). For drawings of a fixed size: the tables and grids, laid out by their text, are not
 * framed.
 */
@Composable
fun DiagramFrame(content: @Composable () -> Unit) {
    val user = LocalDensity.current
    SubcomposeLayout { constraints ->
        val room = constraints.maxWidth
        val loose = constraints.copy(minWidth = 0, minHeight = 0)
        // The drawing's own width, as drawn on a phone at font scale 1.0: the least width it can take, since its
        // caption fills whatever width it is given.
        val natural = subcompose("natural") {
            CompositionLocalProvider(LocalDensity provides Density(user.density, 1f)) { content() }
        }.maxOfOrNull { it.minIntrinsicWidth(Constraints.Infinity) } ?: 0
        val wide = room > PHONE_ROOM.roundToPx() * 1.3f
        val target = minOf(room, DIAGRAM_MAX.roundToPx()).toFloat()
        val grow = if (natural <= 0 || natural >= room) 1f else maxOf(1f, minOf(target / natural, if (wide) Float.MAX_VALUE else user.fontScale))
        val placeables = if (grow == 1f) {
            subcompose("plain") { content() }.map { it.measure(loose) }
        } else {
            val scaled = Density(user.density * grow, minOf(user.fontScale, grow) / grow)
            subcompose("scaled") {
                CompositionLocalProvider(LocalDensity provides scaled, LocalUserDensity provides user) { content() }
            }.map { it.measure(loose) }
        }
        val width = placeables.maxOfOrNull { it.width } ?: 0
        val height = placeables.maxOfOrNull { it.height } ?: 0
        layout(width, height) { placeables.forEach { it.place(0, 0) } }
    }
}

/**
 * A drawing that fills the width it is given up to 360 dp, laid out for [design] dp at font scale 1.0, such as the
 * hours' dial: its text grows with its side past [design], never past the reader's font scale.
 */
@Composable
fun DrawnFor(design: Dp, content: @Composable () -> Unit) {
    val user = LocalDensity.current
    BoxWithConstraints(Modifier.fillMaxWidth()) {
        val side = minOf(maxWidth, DIAGRAM_MAX)
        val scale = minOf(user.fontScale, maxOf(1f, side / design))
        CompositionLocalProvider(LocalDensity provides Density(user.density, scale), LocalUserDensity provides user) { content() }
    }
}

/** Text that is read, not a label on a drawing: inside a [DiagramFrame] it keeps the reader's font scale. */
@Composable
fun AtUserScale(content: @Composable () -> Unit) {
    val user = LocalUserDensity.current
    if (user == null) content() else CompositionLocalProvider(LocalDensity provides user, LocalUserDensity provides null) { content() }
}
