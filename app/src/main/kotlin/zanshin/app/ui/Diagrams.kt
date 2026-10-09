/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.iverlein.zanshin.R
import zanshin.app.LocalLabels
import zanshin.core.kyureki.Choku
import zanshin.core.kyureki.KyuSei
import zanshin.core.kyureki.Rokuyo
import zanshin.core.kyureki.Shuku
import zanshin.core.kyureki.SolarTerm
import zanshin.core.kyureki.Tone
import zanshin.core.texts.Activity
import zanshin.core.texts.Catalog
import zanshin.core.texts.DaySummary
import zanshin.core.texts.DayTime
import zanshin.core.texts.Texts
import zanshin.core.tibetan.Direction
import zanshin.core.tibetan.Element
import zanshin.core.tibetan.ElementPair
import zanshin.core.tibetan.Ewts
import zanshin.core.tibetan.GreatCombination
import zanshin.core.tibetan.IndianElement
import zanshin.core.tibetan.Karana
import zanshin.core.tibetan.LunarDayClass
import zanshin.core.tibetan.Mansion
import zanshin.core.tibetan.RahuMove
import zanshin.core.tibetan.TibetanCalendar
import zanshin.core.tibetan.TibetanDay
import zanshin.core.tibetan.Trigram
import zanshin.core.tibetan.Weekday
import zanshin.core.tibetan.Yoga
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.hypot
import kotlin.math.sin
import java.time.LocalDate

// Diagrams of the visual cues (SPEC §10.4). Bearings are degrees clockwise
// from the top. Kanji drawn inside a diagram are explained by its caption,
// which follows the cell last tapped (today's at first).

private fun polar(c: Offset, r: Float, bearing: Float): Offset {
    val a = bearing * PI.toFloat() / 180f
    return Offset(c.x + r * sin(a), c.y - r * cos(a))
}

/** Bearing of [p] seen from [c], 0..360. */
private fun bearingOf(c: Offset, p: Offset): Float {
    val b = atan2(p.x - c.x, c.y - p.y) * 180f / PI.toFloat()
    return (b + 360f) % 360f
}

private fun ring(c: Offset, r: Float) = Rect(c.x - r, c.y - r, c.x + r, c.y + r)

/** A ring sector between radii [r0] and [r1] and bearings [a0] to [a1]. */
private fun sector(c: Offset, r0: Float, r1: Float, a0: Float, a1: Float) = Path().apply {
    val p = polar(c, r1, a0)
    moveTo(p.x, p.y)
    arcTo(ring(c, r1), a0 - 90f, a1 - a0, false)
    val q = polar(c, r0, a1)
    lineTo(q.x, q.y)
    arcTo(ring(c, r0), a1 - 90f, a0 - a1, false)
    close()
}

private fun DrawScope.arcStroke(c: Offset, r: Float, a0: Float, a1: Float, color: Color, width: Float, cap: StrokeCap = StrokeCap.Butt) {
    drawArc(color, a0 - 90f, a1 - a0, false, topLeft = Offset(c.x - r, c.y - r), size = Size(2 * r, 2 * r), style = Stroke(width, cap = cap))
}

private fun DrawScope.label(measurer: TextMeasurer, text: String, at: Offset, style: TextStyle) =
    label(measurer, AnnotatedString(text), at, style)

/** A label centred on [at], wrapped within [maxWidth] px when given (a named Tibetan term in a ring's centre). */
private fun DrawScope.label(measurer: TextMeasurer, text: AnnotatedString, at: Offset, style: TextStyle, maxWidth: Int? = null) {
    val layout = measurer.measure(text, style, constraints = maxWidth?.let { Constraints(maxWidth = it) } ?: Constraints())
    drawText(layout, topLeft = Offset(at.x - layout.size.width / 2f, at.y - layout.size.height / 2f))
}

private val kanjiStyle get() = TextStyle(fontFamily = Mincho, fontWeight = FontWeight.Bold)
private val captionStyle get() = body.copy(fontSize = 14.sp, color = Palette.muted, textAlign = TextAlign.Center)

@Composable
private fun Caption(text: String) {
    Text(withTibetan(text), style = captionStyle, modifier = Modifier.fillMaxWidth().padding(top = 8.dp))
}

// ---------------------------------------------------------------- 六曜

private val ROKUYO_CYCLE = listOf(Rokuyo.SENSHO, Rokuyo.TOMOBIKI, Rokuyo.SENBU, Rokuyo.BUTSUMETSU, Rokuyo.TAIAN, Rokuyo.SHAKKO)

/** Hours drawn for each time a rokuyō names; noon is drawn last, over the halves it cuts into. */
private val DAY_TIME_HOURS = listOf(
    DayTime.MORNING to (6f to 12f),
    DayTime.AFTERNOON to (12f to 18f),
    DayTime.EVENING to (12f to 18f),
    DayTime.NOON to (11f to 13f),
)

/**
 * The day as an arc, morning on the left, noon at the top, evening on the
 * right, with the times the rokuyō's reading names good and to avoid in
 * their tone colours ([times] from `rokuyoTimes`). Nothing is drawn for a
 * rokuyō whose reading names no time.
 */
@Composable
fun DayArc(times: Pair<Set<DayTime>, Set<DayTime>>, width: Dp, modifier: Modifier = Modifier) {
    val description = stringResource(R.string.desc_day_arc)
    Canvas(modifier.size(width, width * 0.56f).semantics { contentDescription = description }) {
        val stroke = size.width * 0.065f
        val r = size.width / 2 - stroke
        val c = Offset(size.width / 2, size.height - stroke / 2)
        fun bearing(h: Float) = (h - 12f) * 15f
        arcStroke(c, r, -90f, 90f, Palette.lineStrong, stroke, StrokeCap.Round)
        for ((time, hours) in DAY_TIME_HOURS) {
            val color = when (time) {
                in times.second -> Palette.bad.copy(alpha = 0.6f)
                in times.first -> Palette.good
                else -> continue
            }
            arcStroke(c, r, bearing(hours.first), bearing(hours.second), color, stroke)
        }
        val tick = polar(c, r - stroke * 1.3f, 0f)
        drawLine(Palette.faint, tick, Offset(tick.x, tick.y + stroke), 1.dp.toPx(), StrokeCap.Round)
        drawLine(Palette.off, Offset(0f, c.y), Offset(size.width, c.y), 1.dp.toPx(), StrokeCap.Round)
    }
}

/** The six rokuyō in turn, today's marked; tapping one names it below. */
@Composable
fun RokuyoStrip(today: Rokuyo) {
    var selected by remember(today) { mutableIntStateOf(ROKUYO_CYCLE.indexOf(today)) }
    Column {
        Text(stringResource(R.string.rokuyo_cycle), style = body.copy(fontSize = 13.sp, color = Palette.muted), modifier = Modifier.padding(bottom = 8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            ROKUYO_CYCLE.forEachIndexed { i, r ->
                val on = r == today
                Box(
                    Modifier
                        .weight(1f)
                        .height(44.dp)
                        .background(if (i == selected) Palette.raised else Palette.surface, RoundedCornerShape(8.dp))
                        .border(if (on) 1.5.dp else 1.dp, if (on) Palette.vermilion else Palette.lineStrong, RoundedCornerShape(8.dp))
                        .clickable(role = Role.Button) { selected = i },
                    contentAlignment = Alignment.Center,
                ) {
                    Text(r.kanji, style = kanjiStyle.copy(fontSize = 15.sp, color = if (on) Palette.vermilion else Palette.muted))
                }
            }
        }
        val r = ROKUYO_CYCLE[selected]
        Caption("${r.kanji} ${r.romaji} — ${r.english}")
    }
}

// ---------------------------------------------------------------- solar terms

/** Solar longitude to the ring's bearing: 冬至 at the top, the year running clockwise. */
private fun termBearing(longitude: Float) = ((longitude - 270f) % 360f + 360f) % 360f

/**
 * The 24 solar terms as a ring, 冬至 at the top: the current term filled,
 * the four 土用 (the 18° before each 立) and the two 彼岸 (about three days
 * either side of each equinox) as inner arcs.
 */
@Composable
fun TermRing(current: SolarTerm, size: Dp) {
    val description = stringResource(R.string.desc_term_ring, current.kanji)
    Canvas(Modifier.size(size).semantics { contentDescription = description }) {
        val c = center
        val r = this.size.minDimension / 2 - 4.dp.toPx()
        val w = r * 0.14f
        drawCircle(Palette.lineStrong, r, c, style = Stroke(1.dp.toPx()))
        val a = termBearing(current.longitude.toFloat())
        drawPath(sector(c, r - w, r + w * 0.25f, a, a + 15f), Palette.vermilion)
        for (k in 0 until 24) {
            val major = k % 3 == 0
            val p0 = polar(c, r - (if (major) w else w * 0.5f), k * 15f)
            val p1 = polar(c, r + (if (major) w * 0.6f else w * 0.3f), k * 15f)
            drawLine(if (major) Palette.muted else Palette.faint, p0, p1, (if (major) 1.4f else 1f).dp.toPx(), StrokeCap.Round)
        }
        val inner = r - w * 1.9f
        for (start in listOf(297f, 27f, 117f, 207f)) {
            val b = termBearing(start)
            arcStroke(c, inner, b, b + 18f, Palette.saffron, w * 0.55f, StrokeCap.Round)
        }
        for (equinox in listOf(0f, 180f)) {
            val b = termBearing(equinox)
            arcStroke(c, inner, b - 3f, b + 3f, Palette.good, w * 0.55f, StrokeCap.Round)
        }
    }
}

// ---------------------------------------------------------------- 恵方

/** A compass rose of the 24 directions, north at the top, with the year's lucky bearing as a needle. */
@Composable
fun Compass(bearing: Int, size: Dp, description: String) {
    Canvas(Modifier.size(size).semantics { contentDescription = description }) {
        val c = center
        val r = this.size.minDimension / 2 - 2.dp.toPx()
        drawCircle(Palette.lineStrong, r, c, style = Stroke(1.dp.toPx()))
        for (k in 0 until 24) {
            val major = k % 6 == 0
            drawLine(
                if (major) Palette.muted else Palette.faint,
                polar(c, r - (if (major) r * 0.16f else r * 0.07f), k * 15f),
                polar(c, r, k * 15f),
                (if (major) 1.4f else 1f).dp.toPx(),
                StrokeCap.Round,
            )
        }
        val b = bearing.toFloat()
        val tip = polar(c, r * 0.86f, b)
        val tail = polar(c, r * 0.35f, b + 180f)
        val left = polar(c, r * 0.1f, b - 90f)
        val right = polar(c, r * 0.1f, b + 90f)
        val needle = Path().apply {
            moveTo(tip.x, tip.y); lineTo(left.x, left.y); lineTo(tail.x, tail.y); lineTo(right.x, right.y); close()
        }
        drawPath(needle, Palette.vermilion.copy(alpha = 0.18f))
        drawPath(needle, Palette.vermilion, style = Stroke(1.4.dp.toPx()))
        drawCircle(Palette.text, 1.8.dp.toPx(), c)
    }
}

// ---------------------------------------------------------------- Rāhu

/** The bearing of one of the eight directions, clockwise from north. */
private fun bearingOf(d: Direction): Float = d.ordinal * 45f

/**
 * Rāhu's course on a date ([move], `RahuCourse.of`) as a compass, north at
 * the top as the 恵方's: an arrow from the direction it comes from to the one
 * it goes to; on the 14th arrows into the middle (from the sky into the
 * lake), on the 30th out to every direction. [caption] says which course
 * and where; the large one shows it below and adds the eight directions'
 * ticks and the four cardinal letters, the small one is a mark for the
 * almanac row.
 */
@Composable
fun RahuCompass(move: RahuMove, size: Dp, caption: String, small: Boolean = false) {
    val measurer = rememberTextMeasurer()
    val letters = stringResource(R.string.compass_letters).split(' ')
    val description = stringResource(R.string.desc_rahu_compass, caption)
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Canvas(Modifier.size(size).semantics { contentDescription = description }) {
            val c = center
            val r = compassFrame(measurer, letters, small)
            val stroke = (if (small) 1.4f else 2f).dp.toPx()
            val head = (if (small) 3.5f else 9f).dp.toPx()
            val reach = if (small) r * 0.72f else r * 0.62f
            fun arrow(from: Offset, to: Offset) = arrow(Palette.vermilion, from, to, stroke, head)
            when (move) {
                is RahuMove.Across -> {
                    val from = polar(c, reach, bearingOf(move.from))
                    arrow(from, polar(c, reach, bearingOf(move.to)))
                    drawCircle(Palette.vermilion, stroke * 1.4f, from)
                }
                RahuMove.IntoTheLake -> {
                    for (k in 0 until 8 step 2) arrow(polar(c, reach, k * 45f), polar(c, reach * 0.4f, k * 45f))
                    drawCircle(Palette.vermilion.copy(alpha = 0.25f), reach * 0.28f, c)
                }
                RahuMove.Everywhere -> {
                    for (k in 0 until 8) arrow(polar(c, reach * 0.2f, k * 45f), polar(c, reach, k * 45f))
                }
            }
            if (!small && move is RahuMove.Across) drawCircle(Palette.text, 1.8.dp.toPx(), c)
        }
        if (!small) Caption(caption)
    }
}

/**
 * Where the *bla mkhyen* dwells today ([direction], the day's seven-red), as
 * a compass like Rāhu's, north at the top: an arrow from the middle out to
 * its direction, in saffron to keep it apart from Rāhu's, or a ring in the
 * middle when the day's sme ba is the 7 itself (SPEC §5.11).
 */
@Composable
fun BlaMkhyenCompass(direction: Direction, size: Dp, caption: String, small: Boolean = false) {
    val measurer = rememberTextMeasurer()
    val letters = stringResource(R.string.compass_letters).split(' ')
    val description = stringResource(R.string.desc_bla_mkhyen_compass, caption)
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Canvas(Modifier.size(size).semantics { contentDescription = description }) {
            val c = center
            val r = compassFrame(measurer, letters, small)
            val stroke = (if (small) 1.4f else 2f).dp.toPx()
            val head = (if (small) 3.5f else 9f).dp.toPx()
            val reach = if (small) r * 0.72f else r * 0.62f
            if (direction == Direction.CENTRE) {
                drawCircle(Palette.saffron.copy(alpha = 0.25f), reach * 0.3f, c)
                drawCircle(Palette.saffron, reach * 0.3f, c, style = Stroke(stroke))
            } else {
                arrow(Palette.saffron, polar(c, if (small) 0f else r * 0.08f, bearingOf(direction)), polar(c, reach, bearingOf(direction)), stroke, head)
                drawCircle(if (small) Palette.saffron else Palette.text, (if (small) 1.4f else 1.8f).dp.toPx(), c)
            }
        }
        if (!small) Caption(caption)
    }
}

/** The compass's ring, with the eight directions' ticks and the four letters when large; returns its radius. */
private fun DrawScope.compassFrame(measurer: TextMeasurer, letters: List<String>, small: Boolean): Float {
    val c = center
    val r = size.minDimension / 2 - (if (small) 0.75f else 2f).dp.toPx()
    drawCircle(Palette.lineStrong, r, c, style = Stroke(1.dp.toPx()))
    if (!small) {
        for (k in 0 until 8) {
            drawLine(
                if (k % 2 == 0) Palette.muted else Palette.faint,
                polar(c, r - (if (k % 2 == 0) r * 0.08f else r * 0.05f), k * 45f),
                polar(c, r, k * 45f),
                (if (k % 2 == 0) 1.4f else 1f).dp.toPx(),
                StrokeCap.Round,
            )
        }
        letters.take(4).forEachIndexed { i, l ->
            label(measurer, l, polar(c, r * 0.8f, i * 90f), body.copy(fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Palette.muted))
        }
    }
    return r
}

/** A line from [from] to [to] with a filled head at [to]. */
private fun DrawScope.arrow(colour: Color, from: Offset, to: Offset, stroke: Float, head: Float) {
    drawLine(colour, from, to, stroke, StrokeCap.Round)
    val back = bearingOf(to, from)
    val tip = Path().apply {
        val l = polar(to, head, back - 28f)
        val rr = polar(to, head, back + 28f)
        moveTo(to.x, to.y); lineTo(l.x, l.y); lineTo(rr.x, rr.y); close()
    }
    drawPath(tip, colour)
}

// ---------------------------------------------------------------- boards of nine

/**
 * The Lo Shu square, south at the top, as both calendars draw it: the 九星
 * on the fixed board (後天定位盤, Japanese Wikipedia 九星) and the Tibetan
 * sme ba (Berzin, Details of Tibetan Astrology 4: 9 at the top, south, and
 * 1 at the bottom, north). Read row by row from the top left (southeast).
 */
val LO_SHU = listOf(4, 9, 2, 3, 5, 7, 8, 1, 6)

/** The trigram of each number's box (Berzin 4; Japanese Wikipedia 九星); the centre has none. */
private val LO_SHU_TRIGRAM = mapOf(
    1 to Trigram.KHAM, 2 to Trigram.KHON, 3 to Trigram.ZIN, 4 to Trigram.ZON,
    6 to Trigram.KHEN, 7 to Trigram.DWA, 8 to Trigram.GIN, 9 to Trigram.LI,
)

private val LO_SHU_DIRECTION = listOf("南東", "南", "南西", "東", "中央", "西", "北東", "北", "北西")

/** The printed colours of the nine stars, after their readings (white, black, blue, green, yellow, white, red, white, purple). */
private fun starColour(n: Int): Color = when (n) {
    1, 6, 8 -> Color(0xFFE9E6DC)
    2 -> Color(0xFF4A4A46)
    3 -> Color(0xFF5FA7A0)
    4 -> Color(0xFF7FB46A)
    5 -> Color(0xFFD9B94A)
    7 -> Color(0xFFD9705A)
    else -> Color(0xFFA07AC0)
}

/** Lines of a trigram from the top down, true for a whole line. */
private fun trigramLines(t: Trigram): List<Boolean> = when (t) {
    Trigram.KHEN -> listOf(true, true, true)
    Trigram.DWA -> listOf(false, true, true)
    Trigram.LI -> listOf(true, false, true)
    Trigram.ZIN -> listOf(false, false, true)
    Trigram.ZON -> listOf(true, true, false)
    Trigram.KHAM -> listOf(false, true, false)
    Trigram.GIN -> listOf(true, false, false)
    Trigram.KHON -> listOf(false, false, false)
}

/** A trigram drawn as its three lines, not set in type. */
@Composable
fun TrigramBars(t: Trigram, size: Dp, color: Color = Palette.text, description: String? = null) {
    val semantics = if (description != null) Modifier.semantics { contentDescription = description } else Modifier
    Canvas(Modifier.size(size).then(semantics)) {
        val w = this.size.width * 0.72f
        val x0 = (this.size.width - w) / 2
        val gap = this.size.height * 0.24f
        val y0 = this.size.height / 2 - gap
        val sw = this.size.height * 0.09f
        trigramLines(t).forEachIndexed { i, whole ->
            val y = y0 + i * gap
            if (whole) {
                drawLine(color, Offset(x0, y), Offset(x0 + w, y), sw, StrokeCap.Round)
            } else {
                drawLine(color, Offset(x0, y), Offset(x0 + w * 0.38f, y), sw, StrokeCap.Round)
                drawLine(color, Offset(x0 + w * 0.62f, y), Offset(x0 + w, y), sw, StrokeCap.Round)
            }
        }
    }
}

/**
 * The nine stars on the fixed board, [day] marked, [month] and [year] with
 * a small 月 and 年. The page's version: names only.
 */
@Composable
fun StarBoard(day: KyuSei, month: KyuSei, year: KyuSei, size: Dp) {
    val description = stringResource(R.string.desc_nine_board)
    val measurer = rememberTextMeasurer()
    Canvas(Modifier.size(size).semantics { contentDescription = description }) {
        val cell = this.size.width / 3
        LO_SHU.forEachIndexed { i, n ->
            val star = KyuSei.of(n)
            val x = (i % 3) * cell
            val y = (i / 3) * cell
            val on = star == day
            val inset = 1.5.dp.toPx()
            drawRoundRect(if (on) Palette.raised else Palette.surface, Offset(x + inset, y + inset), Size(cell - 2 * inset, cell - 2 * inset), androidx.compose.ui.geometry.CornerRadius(6.dp.toPx()))
            drawRoundRect(
                if (on) Palette.vermilion else Palette.lineStrong,
                Offset(x + inset, y + inset),
                Size(cell - 2 * inset, cell - 2 * inset),
                androidx.compose.ui.geometry.CornerRadius(6.dp.toPx()),
                style = Stroke((if (on) 1.5f else 1f).dp.toPx()),
            )
            drawCircle(starColour(n), 2.6.dp.toPx(), Offset(x + 8.dp.toPx(), y + 8.dp.toPx()))
            label(measurer, star.kanji.take(2), Offset(x + cell / 2, y + cell / 2), kanjiStyle.copy(fontSize = (cell * 0.24f).toSp(), color = if (on) Palette.text else Palette.muted))
            val marks = listOfNotNull("月".takeIf { star == month }, "年".takeIf { star == year })
            marks.forEachIndexed { j, m ->
                val mc = Offset(x + cell - 9.dp.toPx() - j * 14.dp.toPx(), y + cell - 9.dp.toPx())
                drawCircle(Palette.saffron, 6.dp.toPx(), mc, style = Stroke(1.dp.toPx()))
                label(measurer, m, mc, kanjiStyle.copy(fontSize = 8.sp, color = Palette.saffron))
            }
        }
    }
}

/**
 * The fixed board in the 九星 reading: each box with its star, colour,
 * trigram and direction; tapping a box gives its star's reading below.
 */
@Composable
fun StarBoardDetail(day: KyuSei, size: Dp) {
    var selected by remember(day) { mutableIntStateOf(day.ordinal + 1) }
    val description = stringResource(R.string.desc_nine_board)
    val measurer = rememberTextMeasurer()
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Canvas(
            Modifier
                .size(size)
                .semantics { contentDescription = description }
                .pointerInput(Unit) {
                    detectTapGestures { p ->
                        val col = (p.x / (this.size.width / 3f)).toInt().coerceIn(0, 2)
                        val row = (p.y / (this.size.height / 3f)).toInt().coerceIn(0, 2)
                        selected = LO_SHU[row * 3 + col]
                    }
                },
        ) {
            val cell = this.size.width / 3
            LO_SHU.forEachIndexed { i, n ->
                val x = (i % 3) * cell
                val y = (i / 3) * cell
                val on = n == day.ordinal + 1
                val inset = 2.dp.toPx()
                val corner = androidx.compose.ui.geometry.CornerRadius(8.dp.toPx())
                drawRoundRect(if (n == selected) Palette.raised else Palette.surface, Offset(x + inset, y + inset), Size(cell - 2 * inset, cell - 2 * inset), corner)
                drawRoundRect(
                    if (on) Palette.vermilion else Palette.lineStrong,
                    Offset(x + inset, y + inset),
                    Size(cell - 2 * inset, cell - 2 * inset),
                    corner,
                    style = Stroke((if (on) 1.6f else 1f).dp.toPx()),
                )
                drawCircle(starColour(n), 3.2.dp.toPx(), Offset(x + 11.dp.toPx(), y + 11.dp.toPx()))
                label(measurer, LO_SHU_DIRECTION[i], Offset(x + cell - 16.dp.toPx(), y + 11.dp.toPx()), kanjiStyle.copy(fontSize = 8.sp, color = Palette.faint))
                label(measurer, KyuSei.of(n).kanji.take(2), Offset(x + cell / 2, y + cell * 0.45f), kanjiStyle.copy(fontSize = (cell * 0.2f).toSp(), color = if (on) Palette.text else Palette.muted))
                LO_SHU_TRIGRAM[n]?.let { t ->
                    val w = cell * 0.2f
                    val x0 = x + cell / 2 - w / 2
                    val gap = 3.2.dp.toPx()
                    val y0 = y + cell - 20.dp.toPx()
                    trigramLines(t).forEachIndexed { k, whole ->
                        val yy = y0 + k * gap
                        if (whole) {
                            drawLine(Palette.faint, Offset(x0, yy), Offset(x0 + w, yy), 1.4.dp.toPx(), StrokeCap.Round)
                        } else {
                            drawLine(Palette.faint, Offset(x0, yy), Offset(x0 + w * 0.38f, yy), 1.4.dp.toPx(), StrokeCap.Round)
                            drawLine(Palette.faint, Offset(x0 + w * 0.62f, yy), Offset(x0 + w, yy), 1.4.dp.toPx(), StrokeCap.Round)
                        }
                    }
                }
            }
        }
        val star = KyuSei.of(selected)
        Caption("${star.kanji} ${star.reading} — ${star.english}\n${Catalog.text("reading.KyuSei.${star.name}")}")
    }
}

/** The sme ba colours as printed (Berzin 4), by number; black gets a rim on the dark page. */
fun smeBaColour(n: Int): Color = when (n) {
    1, 6, 8 -> Color(0xFFF2EFE8)
    2 -> Color(0xFF000000)
    3 -> Color(0xFF2E4A8C)
    4 -> Color(0xFF3F8A4E)
    5 -> Color(0xFFE1B93A)
    else -> Color(0xFFB3322A)
}

/**
 * An element's colour (ROADMAP T3): the hues of the sme ba boxes that stand for it (Berzin 4: iron
 * white, water black or blue, wood green, earth yellow, fire red), lightened where a thin glyph in
 * the printed shade would vanish on the dark page; water takes the blue, as black would.
 */
fun elementColour(e: Element): Color = when (e) {
    Element.WOOD -> Color(0xFF5FAE6C)
    Element.FIRE -> Color(0xFFD9553F)
    Element.EARTH -> smeBaColour(5)
    Element.IRON -> smeBaColour(1)
    Element.WATER -> Color(0xFF6A8FE0)
}

/** The nine numbers (sme ba) in the square, each in its printed colour, [today] marked. */
@Composable
fun SmeBaSquare(today: Int, size: Dp, description: String) {
    Canvas(Modifier.size(size).semantics { contentDescription = description }) {
        val cell = this.size.width / 3
        LO_SHU.forEachIndexed { i, n ->
            val x = (i % 3) * cell
            val y = (i / 3) * cell
            val on = n == today
            val inset = 0.6.dp.toPx()
            drawRect(if (on) Palette.raised else Palette.surface, Offset(x + inset, y + inset), Size(cell - 2 * inset, cell - 2 * inset))
            val s = cell * 0.46f
            drawRect(smeBaColour(n), Offset(x + (cell - s) / 2, y + (cell - s) / 2), Size(s, s))
            if (n == 2) drawRect(Palette.faint, Offset(x + (cell - s) / 2, y + (cell - s) / 2), Size(s, s), style = Stroke(0.6.dp.toPx()))
            if (on) drawRect(Palette.saffron, Offset(x + inset, y + inset), Size(cell - 2 * inset, cell - 2 * inset), style = Stroke(1.4.dp.toPx()))
        }
    }
}

/**
 * The square moved so that [centre] stands in the middle, south at the top,
 * with the place of [mark] outlined, an arrow from the middle into it, and
 * the four directions round it: the day's sme ba and its seven-red, the working
 * behind the *bla mkhyen*'s compass (SPEC §5.11).
 */
@Composable
fun MovedSmeBaSquare(centre: Int, mark: Int, size: Dp, description: String, caption: String? = null) {
    val measurer = rememberTextMeasurer()
    val names = listOf(R.string.dir_south, R.string.dir_east, R.string.dir_west, R.string.dir_north).map { stringResource(it) }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Canvas(Modifier.size(size).semantics { contentDescription = description }) {
            val margin = 18.dp.toPx()
            val cell = (this.size.width - 2 * margin) / 3
            LO_SHU.forEachIndexed { i, base ->
                val n = (base + centre - 5 + 9 - 1) % 9 + 1
                val x = margin + (i % 3) * cell
                val y = margin + (i / 3) * cell
                val inset = 1.dp.toPx()
                drawRect(Palette.surface, Offset(x + inset, y + inset), Size(cell - 2 * inset, cell - 2 * inset))
                val s = cell * 0.34f
                drawRect(smeBaColour(n), Offset(x + (cell - s) / 2, y + cell * 0.14f), Size(s, s))
                if (n == 2) drawRect(Palette.faint, Offset(x + (cell - s) / 2, y + cell * 0.14f), Size(s, s), style = Stroke(0.6.dp.toPx()))
                label(measurer, "$n", Offset(x + cell / 2, y + cell * 0.74f), TextStyle().copy(fontSize = 13.sp, color = Palette.text))
                if (i == 4) drawRect(Palette.lineStrong, Offset(x + inset, y + inset), Size(cell - 2 * inset, cell - 2 * inset), style = Stroke(1.dp.toPx()))
            }
            // [mark]'s cell outlined, and a short arrow across the border from
            // the middle into it, clear of the swatches and the numbers; when
            // [mark] is the middle, the middle is outlined alone.
            val to = LO_SHU.indices.first { (LO_SHU[it] + centre - 5 + 9 - 1) % 9 + 1 == mark }
            val inset = 1.dp.toPx()
            drawRect(Palette.saffron, Offset(margin + (to % 3) * cell + inset, margin + (to / 3) * cell + inset), Size(cell - 2 * inset, cell - 2 * inset), style = Stroke(2.dp.toPx()))
            if (to != 4) {
                val dx = (to % 3 - 1).toFloat()
                val dy = (to / 3 - 1).toFloat()
                val border = Offset(margin + (1.5f + dx / 2) * cell, margin + (1.5f + dy / 2) * cell)
                val unit = Offset(dx, dy) * (cell / kotlin.math.hypot(dx, dy))
                // A corner leaves more room than an edge.
                val (back, ahead) = if (dx != 0f && dy != 0f) 0.24f to 0.08f else 0.12f to 0.03f
                arrow(Palette.saffron, border - unit * back, border + unit * ahead, 2.dp.toPx(), 6.dp.toPx())
            }
            val mid = this.size.width / 2
            val style = TextStyle().copy(fontSize = 10.sp, color = Palette.muted)
            label(measurer, names[0], Offset(mid, margin / 2), style)
            label(measurer, names[3], Offset(mid, this.size.height - margin / 2), style)
            label(measurer, names[1].take(1).uppercase(), Offset(margin / 2, mid), style)
            label(measurer, names[2].take(1).uppercase(), Offset(this.size.width - margin / 2, mid), style)
        }
        caption?.let { Caption(it) }
    }
}

// ---------------------------------------------------------------- 十二直 and 二十八宿

/** The twelve stations as a dial, 建 at the top, each with its tone; tapping one names it below. */
@Composable
fun ChokuDial(today: Choku, size: Dp) {
    var selected by remember(today) { mutableIntStateOf(today.ordinal) }
    val description = stringResource(R.string.desc_choku_dial)
    val measurer = rememberTextMeasurer()
    val toneWords = Tone.entries.associateWith { toneLabel(it) }
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Canvas(
            Modifier
                .size(size)
                .semantics { contentDescription = description }
                .pointerInput(Unit) {
                    detectTapGestures { p ->
                        val c = Offset(this.size.width / 2f, this.size.height / 2f)
                        selected = (((bearingOf(c, p) + 15f) / 30f).toInt()) % 12
                    }
                },
        ) {
            val c = center
            val r1 = this.size.minDimension / 2 - 2.dp.toPx()
            val r0 = r1 * 0.62f
            Choku.entries.forEachIndexed { i, k ->
                val on = k == today
                val path = sector(c, r0, r1, i * 30f - 14f, i * 30f + 14f)
                drawPath(path, if (i == selected) Palette.raised else Palette.surface)
                drawPath(path, if (on) Palette.vermilion else Palette.lineStrong, style = Stroke((if (on) 1.6f else 1f).dp.toPx()))
                label(measurer, k.kanji, polar(c, (r0 + r1) / 2, i * 30f), kanjiStyle.copy(fontSize = 15.sp, color = if (on) Palette.vermilion else if (k.tone == Tone.BAD) Palette.muted else Palette.text))
                drawCircle(toneColor(k.tone), 2.6.dp.toPx(), polar(c, r0 - 8.dp.toPx(), i * 30f))
            }
        }
        val k = Choku.entries[selected]
        Caption("${k.kanji} ${k.reading} — ${k.english} · ${toneWords.getValue(k.tone)}")
    }
}

private val QUADRANT = listOf("東", "北", "西", "南")

/**
 * The 28 lodges in their four quadrants of seven, north at the top, running
 * counterclockwise from 角 in the east as in the sky; tapping one names it below.
 */
@Composable
fun ShukuRing(today: Shuku, size: Dp) {
    var selected by remember(today) { mutableIntStateOf(today.ordinal) }
    val description = stringResource(R.string.desc_shuku_ring)
    val quadrantNames = listOf(R.string.dir_east, R.string.dir_north, R.string.dir_west, R.string.dir_south).map { stringResource(it) }
    val measurer = rememberTextMeasurer()
    val step = 360f / 28f
    fun bearing(n: Int) = 135f - (n + 0.5f) * step
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Canvas(
            Modifier
                .size(size)
                .semantics { contentDescription = description }
                .pointerInput(Unit) {
                    detectTapGestures { p ->
                        val c = Offset(this.size.width / 2f, this.size.height / 2f)
                        val b = bearingOf(c, p)
                        selected = (((135f - b) / step).let { ((it % 28f) + 28f) % 28f }).toInt()
                    }
                },
        ) {
            val c = center
            val r1 = this.size.minDimension / 2 - 2.dp.toPx()
            val r0 = r1 * 0.74f
            Shuku.entries.forEachIndexed { n, s ->
                val on = s == today
                val a = bearing(n)
                val path = sector(c, r0, r1, a - step / 2 + 0.8f, a + step / 2 - 0.8f)
                drawPath(path, if (n == selected) Palette.raised else Palette.surface)
                drawPath(path, if (on) Palette.vermilion else Palette.lineStrong, style = Stroke((if (on) 1.6f else 0.9f).dp.toPx()))
                label(measurer, s.kanji, polar(c, (r0 + r1) / 2, a), kanjiStyle.copy(fontSize = 11.sp, color = if (on) Palette.vermilion else Palette.muted))
            }
            QUADRANT.forEachIndexed { q, d ->
                label(measurer, d, polar(c, r0 - 18.dp.toPx(), 90f - 90f * q), kanjiStyle.copy(fontSize = 12.sp, color = Palette.faint))
            }
        }
        val s = Shuku.entries[selected]
        Caption("${s.kanji}宿 ${s.reading} — ${s.english} · ${quadrantNames[selected / 7]}")
    }
}

// ---------------------------------------------------------------- Tibetan

/**
 * The 27 lunar mansions as a ring from Aśvinī at the top, [today] marked.
 * The large one names the mansion tapped in its centre; the small one is a
 * mark for the almanac row.
 */
@Composable
fun MansionRing(today: Mansion, size: Dp, small: Boolean = false) {
    var selected by remember(today) { mutableIntStateOf(today.ordinal) }
    val description = stringResource(R.string.desc_mansion_ring, today.sanskrit)
    val measurer = rememberTextMeasurer()
    val step = 360f / 27f
    val sel = Mansion.entries[selected]
    val named = remember(sel) { namedTerm(sel.english, sel.wylie) }
    Canvas(
        Modifier
            .size(size)
            .semantics { contentDescription = description }
            .then(
                if (small) {
                    Modifier
                } else {
                    Modifier.pointerInput(Unit) {
                        detectTapGestures { p ->
                            val c = Offset(this.size.width / 2f, this.size.height / 2f)
                            if (hypot(p.x - c.x, p.y - c.y) > this.size.width * 0.3f) selected = (bearingOf(c, p) / step).toInt() % 27
                        }
                    }
                },
            ),
    ) {
        val c = center
        val r1 = this.size.minDimension / 2 - (if (small) 0.5f else 2f).dp.toPx()
        val r0 = if (small) r1 * 0.55f else r1 * 0.8f
        Mansion.entries.forEachIndexed { i, m ->
            val on = m == today
            val path = sector(c, r0, r1, i * step + (if (small) 1.5f else 0.7f), (i + 1) * step - (if (small) 1.5f else 0.7f))
            if (small) {
                if (on) drawPath(sector(c, r0 * 0.7f, r1, i * step - 2f, (i + 1) * step + 2f), Palette.saffron) else drawPath(path, Palette.off)
            } else {
                drawPath(path, if (i == selected) Palette.raised else Palette.surface)
                drawPath(path, if (on) Palette.saffron else Palette.lineStrong, style = Stroke((if (on) 1.6f else 0.8f).dp.toPx()))
                label(measurer, "${i + 1}", polar(c, (r0 + r1) / 2, (i + 0.5f) * step), body.copy(fontSize = 10.sp, fontWeight = FontWeight.SemiBold, color = if (on) Palette.saffron else Palette.faint))
            }
        }
        if (!small) {
            label(measurer, sel.sanskrit, Offset(c.x, c.y - r1 * 0.3f), body.copy(fontSize = 19.sp, fontWeight = FontWeight.SemiBold))
            label(measurer, named, c, body.copy(fontSize = 13.sp, lineHeight = 17.sp, textAlign = TextAlign.Center), maxWidth = (r0 * 1.5f).toInt())
            label(measurer, sel.element.english, Offset(c.x, c.y + r1 * 0.3f), body.copy(fontSize = 12.sp, color = Palette.faint))
        }
    }
}

private val INDIAN = listOf(IndianElement.WIND, IndianElement.FIRE, IndianElement.EARTH, IndianElement.WATER)

/**
 * The ten pairs of the weekday's and the mansion's elements as a table,
 * weekday down, mansion across, each pair in its tone; [weekday] and
 * [mansion] mark today's.
 */
@Composable
fun ElementPairGrid(weekday: IndianElement, mansion: IndianElement) {
    val description = stringResource(R.string.desc_pair_grid)
    Column(Modifier.semantics { contentDescription = description }, verticalArrangement = Arrangement.spacedBy(5.dp)) {
        @Composable
        fun head(e: IndianElement) = Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CueIcon(CueGlyphs.of(e), Palette.muted, 20.dp)
            Text(e.english, style = body.copy(fontSize = 11.sp, color = Palette.muted))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            Box(Modifier.width(44.dp))
            INDIAN.forEach { Box(Modifier.weight(1f), contentAlignment = Alignment.Center) { head(it) } }
        }
        INDIAN.forEach { row ->
            Row(horizontalArrangement = Arrangement.spacedBy(5.dp), verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.width(44.dp), contentAlignment = Alignment.Center) { head(row) }
                INDIAN.forEach { col ->
                    val pair = ElementPair.of(row, col)
                    val on = row == weekday && col == mansion
                    Box(
                        Modifier
                            .weight(1f)
                            .height(48.dp)
                            .background(if (on) Palette.raised else Palette.surface, RoundedCornerShape(8.dp))
                            .border(if (on) 1.6.dp else 1.dp, if (on) Palette.saffron else Palette.lineStrong, RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            pair.english,
                            style = body.copy(
                                fontSize = 11.sp,
                                lineHeight = 13.sp,
                                color = if (pair.auspicious) Palette.good else Palette.bad,
                                textAlign = TextAlign.Center,
                                hyphens = Hyphens.Auto,
                                lineBreak = LineBreak.Paragraph,
                            ),
                            modifier = Modifier.padding(horizontal = 3.dp),
                        )
                    }
                }
            }
        }
        Text(stringResource(R.string.pair_grid_axes), style = body.copy(fontSize = 11.sp, color = Palette.faint))
    }
}

/**
 * The days of [today]'s Tibetan month, one cell per civil day (a doubled date
 * twice, a skipped one not at all), each with the side the weighing gives
 * haircuts on it (SPEC §5.12), [today] marked; tapping a day names its civil
 * date, side and deciding factor below. [date] is [today]'s civil date.
 */
@Composable
fun HaircutGrid(today: TibetanDay, date: LocalDate) {
    val labels = LocalLabels.current
    val days = remember(today.jd, labels.locale) {
        TibetanCalendar.monthOf(today).map { it to DaySummary.of(it).sideOf(Activity.HAIRCUTS) }
    }
    val good = stringResource(R.string.brief_good)
    val avoid = stringResource(R.string.brief_avoid)
    val neutral = toneLabel(Tone.NEUTRAL)
    WorkGrid(
        heading = stringResource(R.string.haircut_days),
        cells = days.map { (d, side) -> WorkCell(d.day, side?.first ?: Tone.NEUTRAL, marked = d.jd == today.jd) },
        key = today.jd,
        initial = days.indexOfFirst { it.first.jd == today.jd },
    ) { k ->
        val (d, side) = days[k]
        val verdict = side?.let { (tone, standing) ->
            stringResource(R.string.haircut_by, if (tone == Tone.GOOD) good else avoid, standing.first().kanji)
        } ?: neutral
        stringResource(R.string.haircut_cell, d.day, date.plusDays(d.jd - today.jd).format(labels.shortDate), verdict)
    }
}

/**
 * One day in a [WorkGrid]: its lunar [date], the [tone] the weighing gives the work on it (NEUTRAL
 * where no factor names it, blank), [marked] for the shown day, and [forYou] where the person's own
 * day takes it away for every work (ROADMAP E6), drawn as a ring.
 */
data class WorkCell(val date: Int, val tone: Tone, val marked: Boolean = false, val forYou: Boolean = false)

/**
 * Days as a grid of six to a row, each with the dot of the side the weighing gives one work on it
 * (SPEC §5.12): the haircut sheet's month and the election's (SPEC §10.3, ROADMAP E2). Tapping a day
 * selects it and shows [caption] of it below; [key] resets the selection to [initial] (none for -1).
 * The shown day is outlined in [accent], the calendar's colour.
 */
@Composable
fun WorkGrid(
    heading: String?,
    cells: List<WorkCell>,
    key: Any,
    initial: Int,
    accent: Color = Palette.saffron,
    caption: @Composable (Int) -> String,
) {
    var selected by remember(key) { mutableIntStateOf(initial) }
    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
        heading?.let { Text(it, style = body.copy(fontSize = 13.sp, color = Palette.muted)) }
        for (row in cells.indices.chunked(6)) {
            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                for (i in 0 until 6) {
                    val k = row.getOrNull(i)
                    if (k == null) {
                        Box(Modifier.weight(1f))
                        continue
                    }
                    val c = cells[k]
                    Column(
                        Modifier
                            .weight(1f)
                            .aspectRatio(1.1f)
                            .background(if (k == selected) Palette.raised else Palette.surface, RoundedCornerShape(8.dp))
                            .border(if (c.marked) 1.6.dp else 1.dp, if (c.marked) accent else Palette.lineStrong, RoundedCornerShape(8.dp))
                            .clickable(role = Role.Button) { selected = k },
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text("${c.date}", style = body.copy(fontSize = 14.sp, color = if (c.marked) Palette.text else Palette.muted))
                        val dot = Modifier.padding(top = 3.dp).size(6.dp)
                        if (c.forYou) {
                            Box(dot.border(1.4.dp, Palette.bad, CircleShape))
                        } else {
                            Box(dot.background(toneColor(c.tone), CircleShape))
                        }
                    }
                }
            }
        }
        if (selected in cells.indices) Caption(caption(selected))
    }
}

/** The weekdays from Sunday, as the White Beryl counts the named combinations. */
private val WEEK = listOf(Weekday.SUNDAY, Weekday.MONDAY, Weekday.TUESDAY, Weekday.WEDNESDAY, Weekday.THURSDAY, Weekday.FRIDAY, Weekday.SATURDAY)

/**
 * The named combinations of weekday and mansion as a table, weekday down
 * from Sunday, the 27 mansions across from Aśvinī, each cell in its
 * combination's tone; today's marked. Tapping a cell names it below.
 */
@Composable
fun CombinationTable(weekday: Weekday, mansion: Mansion) {
    var selected by remember(weekday, mansion) { mutableStateOf(weekday to mansion) }
    val description = stringResource(R.string.desc_combination_table)
    val measurer = rememberTextMeasurer()
    val names = WEEK.map { it.english.take(2) }
    Column {
        Canvas(
            Modifier
                .fillMaxWidth()
                .aspectRatio(2.1f)
                .semantics { contentDescription = description }
                .pointerInput(Unit) {
                    detectTapGestures { p ->
                        val left = this.size.width * 0.08f
                        val top = this.size.height * 0.12f
                        val col = ((p.x - left) / ((this.size.width - left) / 27f)).toInt()
                        val row = ((p.y - top) / ((this.size.height - top) / 7f)).toInt()
                        if (col in 0 until 27 && row in 0 until 7) selected = WEEK[row] to Mansion.entries[col]
                    }
                },
        ) {
            val left = size.width * 0.08f
            val top = size.height * 0.12f
            val cw = (size.width - left) / 27f
            val ch = (size.height - top) / 7f
            val pad = 0.9.dp.toPx()
            val numberStyle = body.copy(fontSize = 9.sp, color = Palette.faint)
            for (m in listOf(1, 7, 14, 21, 27)) label(measurer, "$m", Offset(left + (m - 0.5f) * cw, top / 2), numberStyle)
            WEEK.forEachIndexed { r, w ->
                label(measurer, names[r], Offset(left / 2, top + (r + 0.5f) * ch), body.copy(fontSize = 10.sp, color = if (w == weekday) Palette.text else Palette.muted))
                Mansion.entries.forEachIndexed { c, m ->
                    val g = GreatCombination.of(w, m)
                    val on = w == weekday && m == mansion
                    val tl = Offset(left + c * cw + pad, top + r * ch + pad)
                    val sz = Size(cw - 2 * pad, ch - 2 * pad)
                    val alpha = if (on || (w to m) == selected) 1f else if (w == weekday || m == mansion) 0.6f else 0.35f
                    drawRect((if (g.lucky) Palette.good else Palette.bad).copy(alpha = alpha), tl, sz)
                    if (on) drawRect(Palette.saffron, Offset(tl.x - pad, tl.y - pad), Size(sz.width + 2 * pad, sz.height + 2 * pad), style = Stroke(1.6.dp.toPx()))
                }
            }
        }
        Text(stringResource(R.string.combination_table_axes), style = body.copy(fontSize = 11.sp, color = Palette.faint), modifier = Modifier.padding(top = 4.dp))
        val (w, m) = selected
        val g = GreatCombination.of(w, m)
        Caption("${w.english} · ${m.sanskrit}: ${Ewts.named(g.english, g.wylie)} · ${toneLabel(if (g.lucky) Tone.GOOD else Tone.BAD)}")
    }
}

/**
 * The lunar date's class: five marks for Nandā … Pūrṇā, today's filled in
 * its tone. A mark for the almanac row.
 */
@Composable
fun LunarDateStrip(date: Int) {
    val today = LunarDayClass.of(date)
    Canvas(Modifier.size(40.dp, 12.dp)) {
        val w = (size.width - 4 * 2.dp.toPx()) / 5f
        LunarDayClass.entries.forEachIndexed { i, k ->
            val tl = Offset(i * (w + 2.dp.toPx()), size.height * 0.2f)
            val sz = Size(w, size.height * 0.6f)
            if (k == today) {
                drawRect(toneColor(Texts.lunarDateTone(date)), Offset(tl.x, 0f), Size(w, size.height))
            } else {
                drawRect(Palette.off, tl, sz)
            }
        }
    }
}

/**
 * The thirty lunar dates in the columns of their five classes, each with
 * its tone, [today] marked; tapping a date names it below.
 */
@Composable
fun LunarDateGrid(today: Int) {
    var selected by remember(today) { mutableIntStateOf(today) }
    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
        Text(stringResource(R.string.desc_lunar_dates), style = body.copy(fontSize = 13.sp, color = Palette.muted))
        Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            LunarDayClass.entries.forEach { k ->
                Text(
                    k.sanskrit,
                    style = body.copy(fontSize = 12.sp, color = if (k == LunarDayClass.of(today)) Palette.text else Palette.muted, textAlign = TextAlign.Center),
                    modifier = Modifier.weight(1f),
                )
            }
        }
        for (row in 0 until 6) {
            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                for (col in 0 until 5) {
                    val d = row * 5 + col + 1
                    val on = d == today
                    Column(
                        Modifier
                            .weight(1f)
                            .height(40.dp)
                            .background(if (d == selected) Palette.raised else Palette.surface, RoundedCornerShape(8.dp))
                            .border(if (on) 1.6.dp else 1.dp, if (on) Palette.saffron else Palette.lineStrong, RoundedCornerShape(8.dp))
                            .clickable(role = Role.Button) { selected = d },
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text("$d", style = body.copy(fontSize = 14.sp, color = if (on) Palette.text else Palette.muted))
                        Box(Modifier.padding(top = 3.dp).size(6.dp).background(toneColor(Texts.lunarDateTone(d)), CircleShape))
                    }
                }
            }
        }
        val k = LunarDayClass.of(selected)
        Caption(stringResource(R.string.lunar_date_cell, selected, k.sanskrit, k.english, toneLabel(Texts.lunarDateTone(selected))))
    }
}

/** The karaṇas in the order of a month's half-days: Kiṃstughna first, the seven moving ones, the last three fixed. */
private val KARANA_RING = listOf(Karana.KIMSTUGHNA) + Karana.entries.take(7) + listOf(Karana.SHAKUNI, Karana.CATUSHPADA, Karana.NAGA)

/**
 * A ring of [n] cells from the top, [today] marked, each with a tone dot;
 * the large one names the cell tapped in its centre through [centre], the
 * small one is a mark for a row. [arc] draws a line inside the cells it spans.
 * The centre's note may take two lines.
 */
@Composable
private fun CellRing(
    n: Int,
    today: Int,
    size: Dp,
    small: Boolean,
    description: String,
    tone: (Int) -> Tone,
    arc: IntRange? = null,
    centre: @Composable (Int) -> Triple<String, AnnotatedString, String>,
) {
    var selected by remember(today) { mutableIntStateOf(today) }
    val measurer = rememberTextMeasurer()
    val step = 360f / n
    val (name, named, note) = centre(selected)
    Canvas(
        Modifier
            .size(size)
            .semantics { contentDescription = description }
            .then(
                if (small) {
                    Modifier
                } else {
                    Modifier.pointerInput(n) {
                        detectTapGestures { p ->
                            val c = Offset(this.size.width / 2f, this.size.height / 2f)
                            if (hypot(p.x - c.x, p.y - c.y) > this.size.width * 0.3f) selected = (bearingOf(c, p) / step).toInt() % n
                        }
                    }
                },
            ),
    ) {
        val c = center
        val r1 = this.size.minDimension / 2 - (if (small) 0.5f else 2f).dp.toPx()
        val r0 = if (small) r1 * 0.55f else r1 * 0.8f
        val gap = if (small) 1.5f else 0.7f
        for (i in 0 until n) {
            val on = i == today
            val path = sector(c, r0, r1, i * step + gap, (i + 1) * step - gap)
            if (small) {
                if (on) drawPath(sector(c, r0 * 0.7f, r1, i * step - 2f, (i + 1) * step + 2f), Palette.saffron) else drawPath(path, Palette.off)
            } else {
                drawPath(path, if (i == selected) Palette.raised else Palette.surface)
                drawPath(path, if (on) Palette.saffron else Palette.lineStrong, style = Stroke((if (on) 1.6f else 0.8f).dp.toPx()))
                label(measurer, "${i + 1}", polar(c, r0 + (r1 - r0) * 0.38f, (i + 0.5f) * step), body.copy(fontSize = 10.sp, fontWeight = FontWeight.SemiBold, color = if (on) Palette.saffron else Palette.faint))
                drawCircle(toneColor(tone(i)), 2.4.dp.toPx(), polar(c, r0 + (r1 - r0) * 0.75f, (i + 0.5f) * step))
            }
        }
        if (!small) {
            arc?.let { arcStroke(c, r0 - 5.dp.toPx(), it.first * step + 2f, (it.last + 1) * step - 2f, Palette.lineStrong, 1.5.dp.toPx(), StrokeCap.Round) }
            label(measurer, name, Offset(c.x, c.y - r1 * 0.3f), body.copy(fontSize = 19.sp, fontWeight = FontWeight.SemiBold))
            label(measurer, named, c, body.copy(fontSize = 13.sp, lineHeight = 17.sp, textAlign = TextAlign.Center), maxWidth = (r0 * 1.5f).toInt())
            label(measurer, note, Offset(c.x, c.y + r1 * 0.32f), body.copy(fontSize = 12.sp, lineHeight = 15.sp, color = Palette.faint, textAlign = TextAlign.Center))
        }
    }
}

/**
 * The eleven karaṇas in the order a month runs through them: Kiṃstughna on
 * the first half-day, the seven moving ones eight times over (the line inside
 * them), the last three fixed; [today] marked.
 */
@Composable
fun KaranaRing(today: Karana, size: Dp, small: Boolean = false) {
    val moving = stringResource(R.string.karana_moving)
    val fixed = stringResource(R.string.karana_fixed)
    CellRing(
        n = 11,
        today = KARANA_RING.indexOf(today),
        size = size,
        small = small,
        description = stringResource(R.string.desc_karana_ring, today.sanskrit),
        tone = { Texts.KARANA_TONE.getValue(KARANA_RING[it]) },
        arc = 1..7,
    ) { i ->
        val k = KARANA_RING[i]
        Triple(k.sanskrit, namedTerm(k.english, k.wylie), if (i in 1..7) moving else fixed)
    }
}

/** The 27 yogas as a ring from Viṣkambha at the top, each with its tone, [today] marked. */
@Composable
fun YogaRing(today: Yoga, size: Dp, small: Boolean = false) {
    CellRing(
        n = 27,
        today = today.ordinal,
        size = size,
        small = small,
        description = stringResource(R.string.desc_yoga_ring, today.sanskrit),
        tone = { Texts.YOGA_TONE.getValue(Yoga.entries[it]) },
    ) { i ->
        val y = Yoga.entries[i]
        Triple(y.sanskrit, namedTerm(y.english, y.wylie), toneLabel(Texts.YOGA_TONE.getValue(y)))
    }
}

/** A diagram centred in the sheet's width. */
@Composable
fun Centered(content: @Composable () -> Unit) {
    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) { content() }
}
