/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.ui.res.stringResource
import io.github.iverlein.zanshin.R
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zanshin.core.texts.Texts
import zanshin.core.tibetan.SpecialDay
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

/*
 * The practice days of the month on the Tibetan page (SPEC §10.3): the date stands in an aureole, as a buddha's
 * image does, on a lotus seat, with an emblem at its crown, and the day's name under it opens a balloon with what
 * is practised. Drawn for this project (MPL-2.0), in the interface's two accents.
 */

/** The aureole's kind: rays for the buddhas, rainbow bands for the guru and the ḍākinīs, flames for the protectors. */
private enum class Aureole { RAYS, RAINBOW, FLAMES }

/** What stands at the aureole's crown. */
private enum class Crown { NONE, JEWEL, FULL_MOON, NEW_MOON }

private class Ornament(val aureole: Aureole, val crown: Crown, val protector: Boolean, val seat: Boolean)

private fun ornamentOf(day: SpecialDay): Ornament = when (day) {
    SpecialDay.EIGHTH -> Ornament(Aureole.RAYS, Crown.JEWEL, protector = false, seat = true)
    SpecialDay.TENTH -> Ornament(Aureole.RAINBOW, Crown.JEWEL, protector = false, seat = true)
    SpecialDay.FULL_MOON -> Ornament(Aureole.RAYS, Crown.FULL_MOON, protector = false, seat = true)
    SpecialDay.TWENTY_FIFTH -> Ornament(Aureole.RAINBOW, Crown.JEWEL, protector = true, seat = true)
    SpecialDay.TWENTY_NINTH -> Ornament(Aureole.FLAMES, Crown.JEWEL, protector = true, seat = true)
    SpecialDay.NEW_MOON -> Ornament(Aureole.RAYS, Crown.NEW_MOON, protector = false, seat = true)
    SpecialDay.NINTH, SpecialDay.TWENTY_FOURTH -> Ornament(Aureole.FLAMES, Crown.NONE, protector = true, seat = false)
}

/** The accent of a practice day: saffron for the buddhas and the guru, vermilion for the ḍākinīs and the protectors. */
fun practiceColor(day: SpecialDay): Color = if (ornamentOf(day).protector) Palette.vermilion else Palette.saffron

private val SIDE = 20.dp
private val TOP = 34.dp
private val SEAT = 30.dp
private val SMALL_BOTTOM = 14.dp

/** [content], the day's numeral, in the ornament of [day]: the aureole around it, the seat under it, the crown above. */
@Composable
fun PracticeOrnament(day: SpecialDay, content: @Composable () -> Unit) {
    val o = ornamentOf(day)
    val color = practiceColor(day)
    val bottom = if (o.seat) SEAT else SMALL_BOTTOM
    Box(
        Modifier
            .drawBehind { drawOrnament(o, color, SIDE.toPx(), TOP.toPx(), bottom.toPx()) }
            .padding(start = SIDE, top = TOP, end = SIDE, bottom = bottom),
    ) { content() }
}

private fun DrawScope.drawOrnament(o: Ornament, color: Color, side: Float, top: Float, bottom: Float) {
    val dp = density
    // The numeral's box, and the aureole round it: a superellipse, which hugs a wide numeral's corners.
    val cx = size.width / 2
    val cy = (top + size.height - bottom) / 2
    val hw = size.width / 2 - side
    val hh = (size.height - bottom - top) / 2
    val a = hw * 1.17f + 13 * dp
    val bb = hh * 1.2f + 14 * dp
    val weight = if (o.seat) 1f else 0.6f
    val rim = Rim(cx, cy)

    drawPath(rim.path(a, bb), color.copy(alpha = 0.06f * weight))
    when (o.aureole) {
        Aureole.RAYS -> {
            // Fine rays from near the numeral to the rim, long and short in turn.
            val n = 84
            for (i in 0 until n) {
                val th = 2 * PI * i / n
                val inner = if (i % 2 == 0) 0.80f else 0.88f
                drawLine(color.copy(alpha = 0.28f), rim.at(th, a * inner, bb * inner), rim.at(th, a - 7 * dp, bb - 7 * dp), 0.8f * dp, StrokeCap.Round)
            }
            drawPath(rim.path(a - 5 * dp, bb - 5 * dp), color.copy(alpha = 0.55f), style = Stroke(0.8f * dp))
            drawPath(rim.path(a, bb), color.copy(alpha = 0.85f), style = Stroke(1.5f * dp))
            rim.pearls(this, color, a + 4.5f * dp, bb + 4.5f * dp, 44, 1.5f * dp, 0.75f)
        }
        Aureole.RAINBOW -> {
            // Three bands, as the rainbow light of the guru's and the ḍākinīs' aureoles, and pearls on the rim.
            val bands = listOf(Palette.saffron to 0.55f, Palette.vermilion to 0.45f, Palette.saffron to 0.25f)
            bands.forEachIndexed { i, (c, alpha) ->
                val inset = (2.5f + i * 4.5f) * dp
                drawPath(rim.path(a - inset, bb - inset), c.copy(alpha = alpha), style = Stroke(4f * dp))
            }
            drawPath(rim.path(a, bb), color.copy(alpha = 0.9f), style = Stroke(1.2f * dp))
            rim.pearls(this, color, a + 4.5f * dp, bb + 4.5f * dp, 40, 1.8f * dp, 0.85f)
        }
        Aureole.FLAMES -> {
            // A wreath of flames licking outwards and a little clockwise, round a plain rim.
            val n = if (o.seat) 30 else 24
            val tall = (if (o.seat) 14f else 9f) * dp
            for (i in 0 until n) {
                val th = 2 * PI * (i + 0.5) / n
                rim.flame(this, color.copy(alpha = if (o.seat) 0.5f else 0.38f), a, bb, th, PI / n * 0.9, if (i % 2 == 0) tall else tall * 0.65f)
            }
            drawPath(rim.path(a, bb), color.copy(alpha = 0.9f * weight + 0.05f), style = Stroke(1.3f * dp))
            drawPath(rim.path(a - 4 * dp, bb - 4 * dp), color.copy(alpha = 0.4f * weight), style = Stroke(0.8f * dp))
        }
    }

    if (o.seat) lotusSeat(color, cx, cy + hh + 2 * dp, a * 1.02f, size.height - 1.5f * dp)

    val crownY = cy - bb - 9 * dp
    when (o.crown) {
        Crown.NONE -> Unit
        Crown.JEWEL -> jewel(color, cx, crownY)
        Crown.FULL_MOON -> {
            drawCircle(color.copy(alpha = 0.18f), 9 * dp, Offset(cx, crownY))
            drawCircle(color.copy(alpha = 0.85f), 6 * dp, Offset(cx, crownY))
        }
        Crown.NEW_MOON -> {
            drawCircle(Palette.text.copy(alpha = 0.08f), 6 * dp, Offset(cx, crownY))
            drawCircle(color.copy(alpha = 0.85f), 6 * dp, Offset(cx, crownY), style = Stroke(1.3f * dp))
        }
    }
}

/** The aureole's outline, a superellipse of exponent 2.6 centred on ([cx], [cy]), with what is drawn along it. */
private class Rim(val cx: Float, val cy: Float) {
    private fun pow(v: Double) = Math.signum(v) * Math.pow(Math.abs(v), 2.0 / 2.6)

    /** The point of the outline of half-axes [a], [b] at parameter [th]. */
    fun at(th: Double, a: Float, b: Float) = Offset(cx + a * pow(cos(th)).toFloat(), cy + b * pow(sin(th)).toFloat())

    fun path(a: Float, b: Float, steps: Int = 120) = Path().apply {
        for (i in 0..steps) {
            val p = at(2 * PI * i / steps, a, b)
            if (i == 0) moveTo(p.x, p.y) else lineTo(p.x, p.y)
        }
        close()
    }

    fun pearls(scope: DrawScope, color: Color, a: Float, b: Float, n: Int, r: Float, alpha: Float) {
        for (i in 0 until n) scope.drawCircle(color.copy(alpha = alpha), r, at(2 * PI * i / n, a, b))
    }

    /** One flame on the outline at [th], [half] its half-width in parameter, [tall] its height. */
    fun flame(scope: DrawScope, color: Color, a: Float, b: Float, th: Double, half: Double, tall: Float) {
        val left = at(th - half, a, b)
        val right = at(th + half, a, b)
        val base = at(th, a, b)
        // The outward normal, from the tangent between the flame's two feet, turned a little so that the flames swirl.
        val ang = atan2((right.y - left.y).toDouble(), (right.x - left.x).toDouble()) - PI / 2 + 0.35
        val nx = cos(ang).toFloat()
        val ny = sin(ang).toFloat()
        val tip = Offset(base.x + nx * tall, base.y + ny * tall)
        val path = Path().apply {
            moveTo(left.x, left.y)
            quadraticTo(left.x + nx * tall * 0.75f, left.y + ny * tall * 0.75f, tip.x, tip.y)
            quadraticTo(right.x + nx * tall * 0.35f, right.y + ny * tall * 0.35f, right.x, right.y)
            close()
        }
        scope.drawPath(path, color)
    }
}

/** The lotus seat: petals rising from a base line at [baseY] to the numeral's foot at [topY], [half] wide on each side. */
private fun DrawScope.lotusSeat(color: Color, cx: Float, topY: Float, half: Float, baseY: Float) {
    val dp = density
    val h = baseY - topY
    // The moon disc the seat bears, a flat ellipse at the petals' tips.
    drawOval(color.copy(alpha = 0.10f), Offset(cx - half * 0.86f, topY - 3 * dp), Size(half * 1.72f, 6 * dp))
    drawOval(color.copy(alpha = 0.6f), Offset(cx - half * 0.86f, topY - 3 * dp), Size(half * 1.72f, 6 * dp), style = Stroke(0.9f * dp))
    // Petals: the middle one upright, the others leaning out and lower; a back row shows between them.
    val n = 3
    val w = half / (n + 0.5f)
    for (row in 0..1) {
        val back = row == 0
        val shift = if (back) w / 2 else 0f
        for (k in -n..n) {
            if (back && k == n) continue
            val x = cx + k * w + shift
            val lean = (x - cx) / half
            val rise = h * (1f - 0.45f * lean * lean) * (if (back) 0.82f else 1f)
            val tipX = x + lean * w * 0.5f
            val tipY = baseY - rise
            val path = Path().apply {
                moveTo(x - w * 0.5f, baseY)
                cubicTo(x - w * 0.62f, baseY - rise * 0.55f, tipX - w * 0.18f, tipY + rise * 0.18f, tipX, tipY)
                cubicTo(tipX + w * 0.18f, tipY + rise * 0.18f, x + w * 0.62f, baseY - rise * 0.55f, x + w * 0.5f, baseY)
                close()
            }
            if (!back) drawPath(path, Palette.raised)
            drawPath(path, color.copy(alpha = if (back) 0.10f else 0.22f))
            drawPath(path, color.copy(alpha = if (back) 0.45f else 0.85f), style = Stroke(0.9f * dp))
            // The petal's midrib.
            if (!back) drawLine(color.copy(alpha = 0.4f), Offset(x, baseY - 2 * dp), Offset(x + (tipX - x) * 0.6f, baseY - rise * 0.6f), 0.7f * dp, StrokeCap.Round)
        }
    }
    drawLine(color.copy(alpha = 0.8f), Offset(cx - half, baseY), Offset(cx + half, baseY), 1.2f * dp, StrokeCap.Round)
}

/** The wish-fulfilling jewel at the crown: a drop with its flame. */
private fun DrawScope.jewel(color: Color, cx: Float, cy: Float) {
    val dp = density
    val path = Path().apply {
        moveTo(cx, cy - 8 * dp)
        cubicTo(cx + 2 * dp, cy - 4 * dp, cx + 6 * dp, cy - 1 * dp, cx + 5 * dp, cy + 3 * dp)
        cubicTo(cx + 4 * dp, cy + 7 * dp, cx - 4 * dp, cy + 7 * dp, cx - 5 * dp, cy + 3 * dp)
        cubicTo(cx - 6 * dp, cy - 1 * dp, cx - 2 * dp, cy - 4 * dp, cx, cy - 8 * dp)
        close()
    }
    drawPath(path, color.copy(alpha = 0.25f))
    drawPath(path, color.copy(alpha = 0.9f), style = Stroke(1.2f * dp))
    drawCircle(color.copy(alpha = 0.9f), 1.6f * dp, Offset(cx, cy + 2.5f * dp))
}

/**
 * The practice day's name under the numeral, opening a balloon with the day's reading and its rows (the practice,
 * when it is strong); a skipped date's day says where it comes from.
 */
@Composable
fun PracticeLine(day: SpecialDay, movedFrom: Int?, open: Boolean, onToggle: () -> Unit) {
    val reading = Texts.SPECIAL_DAY.getValue(day)
    Column {
        BalloonText(
            text = withTibetan(day.english),
            style = body.copy(fontSize = 19.sp, fontWeight = FontWeight.Medium, color = practiceColor(day), lineHeight = 25.sp),
            open = open,
            onToggle = onToggle,
            lead = reading.summary,
            rows = reading.rows.map { (row, text) -> BalloonRow(row.label, text, stacked = true) },
        )
        movedFrom?.let { Text(stringResource(R.string.tib_moved_from_day, it), style = body.copy(fontSize = 14.sp, color = Palette.muted)) }
    }
}
