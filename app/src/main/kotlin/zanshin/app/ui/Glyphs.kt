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
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/**
 * Line glyphs for festivals, drawn for this project (MPL-2.0) on a 24-unit
 * grid, in the stroke style of the interface icons.
 */
private class Glyph(val path: String, val dots: List<Offset> = emptyList(), val description: String)

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

private val LOTUS = "M12 5c2 2.5 2.5 5.5 0 9c-2.5-3.5-2-6.5 0-9zM11 14c-3-.5-6-2.5-7-6c3 .2 5.5 2 7 6zM13 14c3-.5 6-2.5 7-6c-3 .2-5.5 2-7 6zM5 17h14M7 20h10"
private val LAMP = "M12 3c1.8 2.2 2 4 0 6c-2-2-1.8-3.8 0-6zM6 11h12c0 3-2.7 5-6 5s-6-2-6-5zM12 16v3M8 21h8"

private val GLYPHS: Map<String, Glyph> = mapOf(
    "LOSAR" to Glyph(LOTUS, description = "lotus"),
    "CHOTRUL_DUCHEN" to Glyph(LAMP, description = "butter lamp"),
    "KALACAKRA" to Glyph(
        circle(12f, 12f, 9f) + "M7 7h10v10H7zM11 7V5.5h2V7M11 17v1.5h2V17M7 11H5.5v2H7M17 11h1.5v2H17" + circle(12f, 12f, 1.5f),
        description = "mandala",
    ),
    "BIRTH" to Glyph("M12 4c2.5 3 2.5 6 0 9c-2.5-3-2.5-6 0-9zM12 13v7M12 17c-2-1-3.5-1-5 0M12 17c2-1 3.5-1 5 0", description = "lotus bud"),
    "SAGA_DAWA_DUCHEN" to Glyph("M12 20c-5-3-8-7-7-11c.7-2.6 3.3-3.6 5.3-2.3L12 8l1.7-1.3c2-1.3 4.6-.3 5.3 2.3c1 4-2 8-7 11zM12 8v14", description = "bodhi leaf"),
    "ZAMLING_CHISANG" to Glyph("M7 16h10l-1.5 4h-7zM10 13c-1.5-1.5 1.5-3 0-4.5s1.5-3 0-4.5M14 13c-1.5-1.5 1.5-3 0-4.5s1.5-3 0-4.5", description = "smoke offering"),
    "CHOKHOR_DUCHEN" to Glyph(circle(12f, 12f, 8f) + circle(12f, 12f, 2.5f) + rays(8, 2.5f, 8f), description = "wheel of Dharma"),
    "ENTRY_INTO_WOMB" to Glyph(circle(12f, 12f, 8f) + circle(12f, 12f, 2f), description = "seed within a circle"),
    "LHABAB_DUCHEN" to Glyph(
        "M5 3v18M9 3v18M10.5 3v18M13.5 3v18M15 3v18M19 3v18M5 7h4M5 12h4M5 17h4M10.5 7h3M10.5 12h3M10.5 17h3M15 7h4M15 12h4M15 17h4",
        description = "triple ladder",
    ),
    "GADEN_NGAMCHO" to Glyph(
        "M8 5c1.3 1.6 1.4 3 0 4.4c-1.4-1.4-1.3-2.8 0-4.4zM16 5c1.3 1.6 1.4 3 0 4.4c-1.4-1.4-1.3-2.8 0-4.4z" +
            "M4.5 11h7c0 2-1.6 3.4-3.5 3.4S4.5 13 4.5 11zM12.5 11h7c0 2-1.6 3.4-3.5 3.4S12.5 13 12.5 11zM8 14.4v3.6M16 14.4v3.6M5 20h14",
        description = "butter lamps",
    ),
    "SANGPO_CHUZOM" to Glyph(
        "",
        dots = listOf(
            Offset(12f, 5f), Offset(9.5f, 9f), Offset(14.5f, 9f), Offset(7f, 13f), Offset(12f, 13f), Offset(17f, 13f),
            Offset(4.5f, 17f), Offset(9.5f, 17f), Offset(14.5f, 17f), Offset(19.5f, 17f),
        ),
        description = "ten dots",
    ),
    "PROTECTORS" to Glyph("M12 3c3 4 6 6 6 10a6 6 0 0 1-12 0c0-2 1-3.5 2-4.5c0 2 1 3 2 3c-1-3 0-6 2-8.5z", description = "flame"),

    "旧正月" to Glyph("M8 20V9l2-2v13M11 20V6l2-2v16M14 20V8l2-2v14M6 20h12M7 16h10", description = "kadomatsu"),
    "人日の節句" to Glyph(
        "M12 21V9M12 13c-3 0-5-2-5-5c3 0 5 2 5 5zM12 11c3 0 5-2 5-5c-3 0-5 2-5 5zM12 17c-2.5 0-4-1.5-4-4c2.5 0 4 1.5 4 4zM12 16c2.5 0 4-1.5 4-4c-2.5 0-4 1.5-4 4z",
        description = "herb sprig",
    ),
    "上巳の節句" to Glyph(ring(5, 4.5f, 3f) + circle(12f, 12f, 1.2f), description = "peach blossom"),
    "端午の節句" to Glyph("M5 3v18M5 5h11l3 2.5l-3 2.5H5M5 12h9l2.5 2l-2.5 2H5M8.5 7.5h.01M8 14h.01", description = "carp streamers"),
    "七夕" to Glyph(
        "M6 4l.9 2.1L9 7l-2.1.9L6 10l-.9-2.1L3 7l2.1-.9zM18 13l.9 2.1L21 16l-2.1.9L18 19l-.9-2.1L15 16l2.1-.9zM3 21c7-3 12-9 18-18",
        description = "two stars across the river",
    ),
    "十五夜" to Glyph(
        circle(16.5f, 7f, 4.5f) + "M4 22c0-4-.3-8-1.5-11M8 22c0-5 .4-9 2-12.5M12 22c.4-3 1.4-6 3.2-8.5" +
            "M2.5 11c.6-.8 1.6-1 2.4-.6M10 9.5c.8-.6 1.9-.6 2.6 0M15.2 13.5c.9-.3 1.8 0 2.3.7",
        description = "full moon and susuki grass",
    ),
    "重陽の節句" to Glyph(circle(12f, 12f, 2f) + rays(12, 3.5f, 8.5f) + ring(12, 8.5f, 0.6f, 0.0), description = "chrysanthemum"),
    "十三夜" to Glyph(circle(12f, 12f, 8f) + "M12 4a5 8 0 0 1 0 16", description = "gibbous moon"),
)

/** The glyph of a festival, by its key (a Tibetan festival's enum name or a Japanese festival's kanji). */
@Composable
fun FestivalGlyph(key: String, color: Color, size: Dp) {
    val glyph = GLYPHS[key]
    if (glyph == null) {
        GlyphRing(color, size)
        return
    }
    val path = PathParser().parsePathString(glyph.path.ifEmpty { "M0 0" }).toPath()
    Canvas(Modifier.size(size).semantics { contentDescription = glyph.description }) {
        val k = this.size.minDimension / 24f
        scale(k, k, pivot = Offset.Zero) {
            drawPath(path, color, style = Stroke(width = 1.4f, cap = StrokeCap.Round, join = StrokeJoin.Round))
            glyph.dots.forEach { drawCircle(color, radius = 1.1f, center = it) }
        }
    }
}
