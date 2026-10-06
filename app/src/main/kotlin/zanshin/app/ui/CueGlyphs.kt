/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import zanshin.core.kyureki.Gogyo
import zanshin.core.texts.ActivityFamily
import zanshin.core.tibetan.Animal
import zanshin.core.tibetan.Element
import zanshin.core.tibetan.IndianElement

/** A line glyph on a 24-unit grid, in the stroke style of the festival glyphs. */
class CueGlyph(val path: String, val dots: List<Offset> = emptyList())

/**
 * The glyphs of the visual cues (SPEC §10.4), drawn for this project and
 * licensed with it (MPL-2.0): one per activity family, the twelve animals as
 * heads, the five elements and the Indian wind. The four actions are drawn as
 * the hearths of their fire offerings: round for pacifying, square for
 * increasing, semicircular for power, triangular for fierce rites (Gyurme
 * Dorje, *Tibetan Elemental Divination Paintings*, 2001, glossary: burnt
 * offerings).
 */
object CueGlyphs {
    val FAMILY: Map<ActivityFamily, CueGlyph> = mapOf(
        ActivityFamily.EVERYTHING to CueGlyph("M 2.5 13.5 A 11 11 0 0 1 21.5 13.5 M 9.4 17.5 A 3 3 0 0 1 14.6 17.5 M 9.4 17.5 L 2.5 13.5 M 10.5 16.4 L 6.5 9.5 M 12 16 L 12 8 M 13.5 16.4 L 17.5 9.5 M 14.6 17.5 L 21.5 13.5", dots = listOf(Offset(12f, 20f))),
        ActivityFamily.CELEBRATION to CueGlyph("M 4 13 Q 12 20 20 13 M 10 16.3 V 19 M 14 16.3 V 19 M 8 19 H 16 M 10 9 Q 12 6.5 11 4 M 14 9 Q 16 6.5 15 4"),
        ActivityFamily.WEDDING to CueGlyph("M 12 5 A 6 6 0 1 0 12 15 A 6 6 0 0 0 12 5 M 12 5 A 6 6 0 1 1 12 15 A 6 6 0 0 1 12 5 M 11 14.5 L 10 19 M 13 14.5 L 14 19"),
        ActivityFamily.JOURNEY to CueGlyph("M 4 9 L 12 4 L 20 9 Q 12 12 4 9 M 12 5 L 12 10.5 M 10.5 5.5 L 8 10.1 M 13.5 5.5 L 16 10.1 M 5 21 L 19 13"),
        ActivityFamily.SEA to CueGlyph("M3 17q3-3 6 0t6 0t6 0M3 20q3-3 6 0t6 0t6 0M7 14l2 2h6l2-2zM12 14V4q7 4 0 9"),
        ActivityFamily.MOVING_HOUSE to CueGlyph("M3 11l6-6l6 6M5 11v9h8v-3M13 11v2M9 15h11M17 12l3 3l-3 3"),
        ActivityFamily.BUILDING to CueGlyph("M3 8l9-5l9 5M5 12h14M8 12v9M16 12v9M12 3v9"),
        ActivityFamily.EARTH to CueGlyph("M 3 19 H 21 M 7 19 A 5 4 0 0 1 17 19 M 6 6 L 10 14 M 8 15 L 12 13 L 12 18 Z M 5 6.5 L 7 5.5 M 5 6.5 Q 4.5 3 7 5.5"),
        ActivityFamily.WELL to CueGlyph("M8 5v14M16 5v14M5 8h14M5 16h14M9.5 12a2.5 2.5 0 1 0 5 0a2.5 2.5 0 1 0 -5 0M9.5 12q2.5-3 5 0"),
        ActivityFamily.FIELD to CueGlyph("M4.5 20.5h15M12 20.5V11c0-3.6 2.2-6 5-6c1.9 0 3 1.4 3 3.2M9.5 20.5c-.4-3.6-1.8-6.5-4.5-8.6M14.5 20.5c.3-2.5 1.2-4.6 2.8-6.3", dots = listOf(Offset(13.2f, 7.0f), Offset(15.1f, 4.3f), Offset(17.8f, 3.6f), Offset(20.3f, 4.6f), Offset(21.2f, 6.6f))),
        ActivityFamily.LIVESTOCK to CueGlyph("M8.5 20.5H6C3 15 4 3.5 12 3.5S21 15 18 20.5H15.5L15 19C17 14.5 16 7.5 12 7.5S7 14.5 9 19Z", dots = listOf(Offset(6.4f, 10.0f), Offset(6.2f, 14.5f), Offset(17.6f, 10.0f), Offset(17.8f, 14.5f))),
        ActivityFamily.TRADE to CueGlyph("M4 12a8 8 0 1 0 16 0a8 8 0 1 0 -16 0M9 9h6v6h-6z"),
        ActivityFamily.AGREEMENT to CueGlyph("M5 4h14v16H5zM8 8h8M8 12h8M13 16a2 2 0 1 0 4 0a2 2 0 1 0 -4 0"),
        ActivityFamily.DISPUTE to CueGlyph("M2.5 12H9.5M6.5 9L9.5 12L6.5 15M21.5 12H14.5M17.5 9L14.5 12L17.5 15M12 4.5V7.5M12 16.5V19.5"),
        ActivityFamily.AUTHORITY to CueGlyph("M7 13V5.5Q12 2 17 5.5V13M5 13H19M6 13V20M18 13V20M6 16.5H18M3.5 20.5H20.5"),
        ActivityFamily.BEGINNING to CueGlyph("M6 4v16M18 4v16M6 4h12M6 5l8 2v10l-8 2"),
        ActivityFamily.LEARNING to CueGlyph("M12 5Q7 3 4 7v11Q7 16 12 18M12 5Q17 3 20 7v11Q17 16 12 18M12 5v13"),
        ActivityFamily.MEDICINE to CueGlyph("M10 4h4M10 4C9 4 7 7 9 10c1 2-4 3-4 7c0 3 4 4 7 4M14 4c1 0 3 3 1 6c-1 2 4 3 4 7c0 3-4 4-7 4M8 10.5h8"),
        ActivityFamily.FUNERAL to CueGlyph("M7 18h10M5 20h14M9 18V6q3-2 6 0v12M12 18v-4M12 13q2-2 0-4"),
        ActivityFamily.SHRINE to CueGlyph("M3 5q9 2 18 0M4 9h16M6 20l1-14M18 20l-1-14M12 6v3"),
        ActivityFamily.PRAYER to CueGlyph("M 12 16 L 10.5 20.5 M 12 16 L 13.5 20.5", dots = listOf(Offset(12f, 3f), Offset(15.5f, 4.1f), Offset(17.7f, 7.1f), Offset(17.7f, 10.9f), Offset(15.5f, 13.9f), Offset(12f, 15f), Offset(8.5f, 13.9f), Offset(6.3f, 10.9f), Offset(6.3f, 7.1f), Offset(8.5f, 4.1f))),
        ActivityFamily.VOWS to CueGlyph("M4 11H20M4.5 11C4.5 16.5 8 19.5 12 19.5C16 19.5 19.5 16.5 19.5 11M6.5 11C6.5 7.5 17.5 7.5 17.5 11M12 7.8V5.5M9.5 19.2L9 21H15L14.5 19.2"),
        ActivityFamily.OFFERING to CueGlyph("M5.5 10H18.5Q18 15 12 15Q6 15 5.5 10ZM12 15V18.5M8 20.5Q12 17.5 16 20.5ZM12 8C10.4 6.6 10.6 4.8 12 2.5C13.4 4.8 13.6 6.6 12 8Z"),
        ActivityFamily.SACRED to CueGlyph("M7 18h10v3H7zM8 18c0-8 8-8 8 0M12 12V4M10 10h4M10 8h4M10 6h4"),
        ActivityFamily.RITE to CueGlyph("M10.5 12a1.5 1.5 0 1 0 3 0a1.5 1.5 0 1 0 -3 0M9 9.5H15M9 14.5H15M12 9.5V3M9.5 9.5C7.5 7.5 9 4.5 12 3M14.5 9.5C16.5 7.5 15 4.5 12 3M12 14.5V21M9.5 14.5C7.5 16.5 9 19.5 12 21M14.5 14.5C16.5 16.5 15 19.5 12 21"),
        ActivityFamily.PACIFYING to CueGlyph("M4 13a8 8 0 1 0 16 0a8 8 0 1 0 -16 0M12 17c-1.6-1.5-1.6-3.6 0-6c1.6 2.4 1.6 4.5 0 6z"),
        ActivityFamily.INCREASING to CueGlyph("M4.5 5.5h15v15h-15zM12 17c-1.6-1.5-1.6-3.6 0-6c1.6 2.4 1.6 4.5 0 6z"),
        ActivityFamily.CONTROLLING to CueGlyph("M3.5 19.5A8.5 8.5 0 0 1 20.5 19.5ZM12 18c-1.6-1.5-1.6-3.6 0-6c1.6 2.4 1.6 4.5 0 6z"),
        ActivityFamily.DESTROYING to CueGlyph("M12 3.5L21 19.5H3ZM12 18c-1.4-1.3-1.4-3.2 0-5.3c1.4 2.1 1.4 4 0 5.3z"),
        ActivityFamily.CLOTHES to CueGlyph("M12 21H6v-11H3V5l7-2M12 21h6v-11h3V5l-7-2M14 3L8 10M10 3l1.5 1.8M6 13h12M6 16h12"),
        ActivityFamily.HAIRCUT to CueGlyph("M7 4l7 11M13 17a2.5 2.5 0 1 0 5 0a2.5 2.5 0 1 0 -5 0M17 4l-7 11M6 17a2.5 2.5 0 1 0 5 0a2.5 2.5 0 1 0 -5 0", dots = listOf(Offset(12f, 11.3f))),
        ActivityFamily.BATHING to CueGlyph("M9.5 9C6 11 6 18 9 20H15C18 18 18 11 14.5 9ZM10 9L10.5 5H13.5L14 9M9.5 5H14.5M7.5 12.5L3.5 8.5M16.5 11.5C20 11.5 20 16.5 17 17", dots = listOf(Offset(3.5f, 11.5f), Offset(4.5f, 14.5f))),
        ActivityFamily.NAME to CueGlyph("M8 6h8v14H8zM12 6V3M10 10h4M10 14h4"),
        ActivityFamily.HOUSEHOLD to CueGlyph("M12 3v10M12 13q4 3 4 8H8q0-5 4-8M10 17v4M14 17v4"),
        ActivityFamily.KIN to CueGlyph("M5.5 6a2.5 2.5 0 1 0 5 0a2.5 2.5 0 1 0 -5 0M3.5 20.5V14C3.5 11 5.5 9.5 8 9.5S12.5 11 12.5 14V20.5M14.5 11a2 2 0 1 0 4 0a2 2 0 1 0 -4 0M13.5 20.5V16.5C13.5 14.8 14.8 14 16.5 14S19.5 14.8 19.5 16.5V20.5M2.5 20.5H21.5"),
        ActivityFamily.BLADE to CueGlyph("M5 19l2 2l4-4l-2-2zM8 14l4 4M10 16L19 7M9 15Q13 9 19 7"),
        ActivityFamily.FIRE to CueGlyph("M7 19l10-4M7 15l10 4M12 15q-3-6 0-12q3 6 0 12M8 16q-3-4 0-8q2 4 0 8M16 16q3-4 0-8q-2 4 0 8"),
        ActivityFamily.CONDUCT to CueGlyph("M10 5a2 2 0 1 0 4 0a2 2 0 1 0 -4 0M12 7v9M12 8l-5 5l5 2l5-2l-5-5M6 17q6-2 12 0q-6 4-12 0"),
    )

    /** Heads, so that each fills the frame and reads at 20 dp. */
    val ANIMAL: Map<Animal, CueGlyph> = mapOf(
        Animal.MOUSE to CueGlyph("M 12 20 C 9 20 7 17 7 14 C 7 11 9 8 12 8 C 15 8 17 11 17 14 C 17 17 15 20 12 20 Z M 8.5 9.5 A 3.5 3.5 0 1 0 5 14 M 15.5 9.5 A 3.5 3.5 0 1 1 19 14 M 3 16 L 7.5 17 M 3 18 L 8 18.5 M 21 16 L 16.5 17 M 21 18 L 16 18.5", dots = listOf(Offset(10f, 13f), Offset(14f, 13f))),
        Animal.OX to CueGlyph("M 8 10 Q 12 8 16 10 L 15 17 Q 12 20 9 17 Z M 8 10 C 3 9 3 5 5 4 M 16 10 C 21 9 21 5 19 4 M 7 13 L 4 14 L 7 15 M 17 13 L 20 14 L 17 15 M 10 17 Q 12 18 14 17", dots = listOf(Offset(10f, 13f), Offset(14f, 13f))),
        Animal.TIGER to CueGlyph("M 5 13 A 7 7 0 1 0 19 13 A 7 7 0 1 0 5 13 M 7.5 8 A 2.5 2.5 0 1 1 10.5 6.2 M 13.5 6.2 A 2.5 2.5 0 1 1 16.5 8 M 10 7 H 14 M 11 9 H 13 M 9 11 H 15 M 12 7 V 11 M 10 16 Q 12 18 14 16", dots = listOf(Offset(9f, 14f), Offset(15f, 14f))),
        Animal.RABBIT to CueGlyph("M 8 16 A 4 4 0 1 0 16 16 A 4 4 0 1 0 8 16 M 9.5 13 C 9 6 10 3 11 3 C 12 3 12 8 11.5 12 M 14.5 13 C 15 6 14 3 13 3 C 12 3 12 8 12.5 12 M 11.5 17 L 12 18 L 12.5 17", dots = listOf(Offset(10f, 15f), Offset(14f, 15f))),
        Animal.DRAGON to CueGlyph("M 4 10 C 4 6 8 5.5 12 6.5 Q 16 7.5 18.5 8.5 C 20 9 20 11 18.5 11 C 17 11 17 9.5 18 9.5 M 18.5 11 C 18 16 14 20 7 20 M 17 13.5 C 14 15.5 9 15.5 5 13.5 M 10 6 Q 8 3 4 3 M 7.5 3.8 Q 6 1.5 4 1.5", dots = listOf(Offset(12f, 9f))),
        Animal.SNAKE to CueGlyph("M 5 17 C 5 21.5 19 21.5 19 17 C 19 13 8 13 8 17 C 8 20.5 15 20.5 15 17 C 15 13 12 12 12 9 M 12 9 C 10 9 9 6 12 5 C 15 6 14 9 12 9 M 12 5 V 3.5 L 11 2.5 M 12 3.5 L 13 2.5", dots = listOf(Offset(11f, 7f), Offset(13f, 7f))),
        Animal.HORSE to CueGlyph("M 10 21 L 10 16 L 5 16 C 3 16 3 13 5 12 L 11 6 C 13 4 15 5 15 7 L 16 21 M 12 6 L 11 3 L 14 6 M 4 15 L 7 15 M 15.5 10 L 18 11 M 15.5 14 L 18 15 M 16 18 L 18.5 19", dots = listOf(Offset(10.5f, 10f))),
        Animal.SHEEP to CueGlyph("M 10 7 A 3.5 3.5 0 1 0 3 7 A 3.5 3.5 0 0 0 6.5 10.5 A 2 2 0 0 0 8.5 8.5 M 14 7 A 3.5 3.5 0 1 1 21 7 A 3.5 3.5 0 0 1 17.5 10.5 A 2 2 0 0 1 15.5 8.5 M 11 7 C 9 8 9 12 10 16 C 11 19 13 19 14 16 C 15 12 15 8 13 7 M 9 10 L 4 12 L 8 13 M 15 10 L 20 12 L 16 13 M 11 17 L 12 18 L 13 17", dots = listOf(Offset(10.5f, 13f), Offset(13.5f, 13f))),
        Animal.MONKEY to CueGlyph("M 5 12 A 7 7 0 1 0 19 12 A 7 7 0 1 0 5 12 M 12 17 C 7 17 5 10 8 7 C 9.5 5.5 11 6 12 8.5 C 13 6 14.5 5.5 16 7 C 19 10 17 17 12 17 Z M 5.5 9 A 3 3 0 1 0 5.5 15 M 18.5 9 A 3 3 0 1 1 18.5 15 M 10 17.5 Q 12 18.5 14 17.5", dots = listOf(Offset(9.5f, 10.5f), Offset(14.5f, 10.5f))),
        Animal.BIRD to CueGlyph("M8.5 21c0-4.2.6-7.4 2.2-9.6c-1.2-2.8.2-5.6 3.2-5.9c2.5-.2 4.1 1.5 4.3 3.6l2.8 1.2l-2.8 1.1c-.3 1.4-1.2 2.4-2.6 2.6c.1 1.6-.6 2.8-1.9 2.8c-1.1 0-1.6-1-1.3-2.2M14.6 13.5c.6 2.4.8 4.8.8 7.5M10.6 6.6c-1-1.7.2-3.4 1.8-2.8c.3-1.8 2.6-2.2 3.4-.5c1.5-.6 2.9.8 2.1 2.4", dots = listOf(Offset(15.0f, 8.6f))),
        Animal.DOG to CueGlyph("M 10 19.5 Q 7 17 6 11 L 5 4 L 10 7 Q 12 7.5 14 7 L 19 4 L 18 11 Q 17 17 14 19.5 Q 12 21 10 19.5 Z M 6 6 L 8 9 M 18 6 L 16 9 M 11 16 Q 12 15 13 16 L 12 17.5 Z M 12 17.5 V 19 M 10 19.5 Q 12 19 14 19.5", dots = listOf(Offset(9f, 13f), Offset(15f, 13f))),
        Animal.PIG to CueGlyph("M 12 19 C 5 19 4 13 4 11 C 4 7 8 5 12 5 C 16 5 20 7 20 11 C 20 13 19 19 12 19 Z M 6 7 L 3 4 L 8 5 M 18 7 L 21 4 L 16 5 M 8 14 A 4 3 0 1 0 16 14 A 4 3 0 1 0 8 14", dots = listOf(Offset(10.5f, 14f), Offset(13.5f, 14f), Offset(9f, 10f), Offset(15f, 10f))),
    )

    private val ELEMENTS: Map<String, CueGlyph> = mapOf(
        "wood" to CueGlyph("M 12 21 v -14 M 12 13 l -5 -5 M 12 10 l 4 -4 M 12 17 l -4 -4 M 12 16 l 5 -5"),
        "fire" to CueGlyph("M 12 20 C 10 14 10 10 12 4 C 14 10 14 14 12 20 Z M 7 20 C 5 16 6 12 8 9 C 9 13 9 16 7 20 Z M 17 20 C 19 16 18 12 16 9 C 15 13 15 16 17 20 Z"),
        "earth" to CueGlyph("M 4 20 h 16 M 4 16 h 16 M 6 12 h 12 M 8 8 h 8 M 10 4 h 4"),
        "metal" to CueGlyph("M 8 18 V 10 C 8 5 16 5 16 10 V 18 Z M 6 18 h 12 M 10 6 v -2 C 10 2.5 14 2.5 14 4 v 2 M 8 14 h 8 M 12 18 v 2"),
        "water" to CueGlyph("M 4 18 Q 8 14 12 18 T 20 18 M 4 12 Q 8 8 12 12 T 20 12 M 4 6 Q 8 2.5 12 6 T 20 6"),
        "wind" to CueGlyph("M4 8.5h10.5c1.7 0 3-1.2 3-2.8S16.2 3 14.7 3c-1.2 0-2.2.8-2.5 1.8M4 12.5h14c1.8 0 3.2 1.3 3.2 3s-1.4 3-3.1 3c-1.3 0-2.3-.8-2.7-1.9M4 16.5h7"),
    )

    fun of(e: Gogyo): CueGlyph = ELEMENTS.getValue(e.name.lowercase())

    /** The Tibetan elements; iron is drawn as metal. */
    fun of(e: Element): CueGlyph = ELEMENTS.getValue(if (e == Element.IRON) "metal" else e.name.lowercase())

    fun of(e: IndianElement): CueGlyph = ELEMENTS.getValue(e.name.lowercase())
}

/** A cue glyph at [size]; [description] is what a screen reader says, null when the text beside it says it. */
@Composable
fun CueIcon(glyph: CueGlyph, color: Color, size: Dp, description: String? = null, modifier: Modifier = Modifier) {
    val path = remember(glyph) { PathParser().parsePathString(glyph.path).toPath() }
    val semantics = if (description != null) Modifier.semantics { contentDescription = description } else Modifier
    Canvas(modifier.size(size).then(semantics)) {
        val k = this.size.minDimension / 24f
        scale(k, k, pivot = Offset.Zero) {
            drawPath(path, color, style = Stroke(width = 1.4f, cap = StrokeCap.Round, join = StrokeJoin.Round))
            glyph.dots.forEach { drawCircle(color, radius = 1.1f, center = it) }
        }
    }
}
