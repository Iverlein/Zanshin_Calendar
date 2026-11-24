/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import io.github.iverlein.zanshin.R
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/**
 * Line glyphs for the 旧暦 festivals, drawn for this project (MPL-2.0) on a 24-unit
 * grid, in the stroke style of the interface icons, until their painted frames come (ROADMAP T6.3); the Tibetan
 * festivals stand in painted frames (PracticeDay.kt, T6.2).
 */
private class Glyph(val path: String, val dots: List<Offset> = emptyList(), val description: Int)

private fun circle(cx: Float, cy: Float, r: Float) =
    "M${cx - r} ${cy}a$r $r 0 1 0 ${2 * r} 0a$r $r 0 1 0 ${-2 * r} 0"

private fun ring(count: Int, radius: Float, petal: Float, start: Double = -PI / 2) = (0 until count).joinToString("") { i ->
    val a = start + 2 * PI * i / count
    circle((12 + radius * cos(a)).toFloat(), (12 + radius * sin(a)).toFloat(), petal)
}

private fun rays(count: Int, inner: Float, outer: Float) = (0 until count).joinToString("") { i ->
    val a = 2 * PI * i / count
    "M${12 + inner * cos(a)} ${12 + inner * sin(a)}L${12 + outer * cos(a)} ${12 + outer * sin(a)}"
}

private val GLYPHS: Map<String, Glyph> = mapOf(
    "旧正月" to Glyph("M8 20V9l2-2v13M11 20V6l2-2v16M14 20V8l2-2v14M6 20h12M7 16h10", description = R.string.glyph_kadomatsu),
    "人日の節句" to Glyph(
        "M12 21V9M12 13c-3 0-5-2-5-5c3 0 5 2 5 5zM12 11c3 0 5-2 5-5c-3 0-5 2-5 5zM12 17c-2.5 0-4-1.5-4-4c2.5 0 4 1.5 4 4zM12 16c2.5 0 4-1.5 4-4c-2.5 0-4 1.5-4 4z",
        description = R.string.glyph_herb_sprig,
    ),
    "上巳の節句" to Glyph(ring(5, 4.5f, 3f) + circle(12f, 12f, 1.2f), description = R.string.glyph_peach_blossom),
    "端午の節句" to Glyph("M5 3v18M5 5h11l3 2.5l-3 2.5H5M5 12h9l2.5 2l-2.5 2H5M8.5 7.5h.01M8 14h.01", description = R.string.glyph_carp_streamers),
    "七夕" to Glyph(
        "M6 4l.9 2.1L9 7l-2.1.9L6 10l-.9-2.1L3 7l2.1-.9zM18 13l.9 2.1L21 16l-2.1.9L18 19l-.9-2.1L15 16l2.1-.9zM3 21c7-3 12-9 18-18",
        description = R.string.glyph_two_stars_across_the_river,
    ),
    "十五夜" to Glyph(
        circle(16.5f, 7f, 4.5f) + "M4 22c0-4-.3-8-1.5-11M8 22c0-5 .4-9 2-12.5M12 22c.4-3 1.4-6 3.2-8.5" +
            "M2.5 11c.6-.8 1.6-1 2.4-.6M10 9.5c.8-.6 1.9-.6 2.6 0M15.2 13.5c.9-.3 1.8 0 2.3.7",
        description = R.string.glyph_full_moon_and_susuki_grass,
    ),
    "重陽の節句" to Glyph(circle(12f, 12f, 2f) + rays(12, 3.5f, 8.5f) + ring(12, 8.5f, 0.6f, 0.0), description = R.string.glyph_chrysanthemum),
    "十三夜" to Glyph(circle(12f, 12f, 8f) + "M12 4a5 8 0 0 1 0 16", description = R.string.glyph_gibbous_moon),
)

/** The glyph of a 旧暦 festival, by its kanji. */
@Composable
fun FestivalGlyph(key: String, color: Color, size: Dp) {
    val glyph = GLYPHS[key]
    if (glyph == null) {
        GlyphRing(color, size)
        return
    }
    val path = PathParser().parsePathString(glyph.path.ifEmpty { "M0 0" }).toPath()
    val description = stringResource(glyph.description)
    Canvas(Modifier.size(size).semantics { contentDescription = description }) {
        val k = this.size.minDimension / 24f
        scale(k, k, pivot = Offset.Zero) {
            drawPath(path, color, style = Stroke(width = 1.4f, cap = StrokeCap.Round, join = StrokeJoin.Round))
            glyph.dots.forEach { drawCircle(color, radius = 1.1f, center = it) }
        }
    }
}
