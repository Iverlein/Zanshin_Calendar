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
import zanshin.core.texts.Activity
import zanshin.core.texts.ActivityFamily
import zanshin.core.texts.family
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

    private val DHARMA_WHEEL = CueGlyph("M4 12a8 8 0 1 0 16 0a8 8 0 1 0 -16 0M9.5 12a2.5 2.5 0 1 0 5 0a2.5 2.5 0 1 0 -5 0M14.5 12H20M13.8 13.8L17.7 17.7M12 14.5V20M10.2 13.8L6.3 17.7M9.5 12H4M10.2 10.2L6.3 6.3M12 9.5V4M13.8 10.2L17.7 6.3")
    private val TALK = CueGlyph("M3 4.5h11v7H8.5l-3 2.5v-2.5H3zM16.5 9.5H21v7h-2.5V19l-3-2.5H10V14")
    private val STARS = CueGlyph("M13 3.5a8.5 8.5 0 1 0 7.5 12.5a7 7 0 0 1 -7.5 -12.5zM17.5 4l.7 1.8l1.8 .7l-1.8 .7l-.7 1.8l-.7 -1.8l-1.8 -.7l1.8 -.7z", dots = listOf(Offset(20.5f, 11f)))
    private val HAMMER = CueGlyph("M6.5 4h7.5c2 0 4 1 5 3h-12.5zM10 7v13a1.5 1.5 0 0 0 3 0V7")
    private val GIFT = CueGlyph("M4 8.5h16v4H4zM5.5 12.5h13v8h-13zM12 8.5v12M12 8.5C10 4.5 6.5 4.5 6.5 6.7S10 8.5 12 8.5C14 8.5 17.5 9 17.5 6.7S14 4.5 12 8.5")
    private const val HAND = "M2.5 14h3.5l3.5-1.5h4.5a1.5 1.5 0 0 1 0 3H11M14 15.5l5-2.8a1.5 1.5 0 0 1 2 2.1L15 19.5H6l-3.5 1"

    /**
     * Works whose family glyph draws them badly, each with its own: the breakdown and the election show a
     * work by this glyph where it has one, by its family's otherwise. The In brief row keeps the families.
     */
    val WORK: Map<Activity, CueGlyph> = mapOf(
        Activity.BETROTHAL to GIFT,
        Activity.DOWRY to GIFT,
        Activity.HORSE_RACING to CueGlyph("M 10 21 L 10 16 L 5 16 C 3 16 3 13 5 12 L 11 6 C 13 4 15 5 15 7 L 16 21 M 12 6 L 11 3 L 14 6 M 4 15 L 7 15 M18.5 9h3.5M19 13h2.5M18.5 17h3.5", dots = listOf(Offset(10.5f, 10f))),
        Activity.SPECTACLES to CueGlyph("M4.5 3.5h15v7c0 5.5-3.4 10-7.5 10s-7.5-4.5-7.5-10zM7 9q1.5-1.5 3 0M14 9q1.5-1.5 3 0M8.5 14.5q3.5 3 7 0"),
        Activity.GAMES to CueGlyph("M7 4.5h10a2.5 2.5 0 0 1 2.5 2.5v10a2.5 2.5 0 0 1 -2.5 2.5H7a2.5 2.5 0 0 1 -2.5 -2.5V7A2.5 2.5 0 0 1 7 4.5z", dots = listOf(Offset(8.7f, 8.7f), Offset(15.3f, 8.7f), Offset(12f, 12f), Offset(8.7f, 15.3f), Offset(15.3f, 15.3f))),
        Activity.GOOD_FORTUNE to CueGlyph("M12 5L19 12L12 19L5 12ZM14.33 7.33L7.33 14.33M16.67 9.67L9.67 16.67M9.67 7.33L16.67 14.33M7.33 9.67L14.33 16.67M10.83 6.17C9 4.5 10 2.5 12 2.5S15 4.5 13.17 6.17M17.83 10.83C19.5 9 21.5 10 21.5 12S19.5 15 17.83 13.17M13.17 17.83C15 19.5 14 21.5 12 21.5S9 19.5 10.83 17.83M6.17 13.17C4.5 15 2.5 14 2.5 12S4.5 9 6.17 10.83"),
        Activity.MAKING_THINGS to HAMMER,
        Activity.DRIVING_NAILS to HAMMER,
        Activity.CLIMBING_HIGH to CueGlyph("M6 21L10 3M14 21L18 3M6.9 17h8M7.8 13h8M8.7 9h8M9.6 5h8"),
        Activity.STONE_WALLS to CueGlyph("M3 6h18v13.5H3zM3 10.5h18M3 15h18M9 6v4.5M15 6v4.5M6 10.5V15M12 10.5V15M18 10.5V15M9 15v4.5M15 15v4.5"),
        Activity.PITCHING_TENTS to CueGlyph("M5 19.5L12 7l7 12.5M2.5 19.5h19M12 7V4M10 19.5l2-5l2 5M12 7L3 13M12 7l9 6"),
        Activity.SEWING_TENTS to CueGlyph("M4 20L17 7M17 7l2.5-2.5a1.2 1.2 0 0 1 1.7 1.7L18.5 8.7L17 7zM19 6.5c-3 1-4 4-7 5s-6 0-7.5-2M3 22h2.5M8 22h2.5M13 22h2.5M18 22h2.5"),
        Activity.HEARTH to CueGlyph("M3.5 20.5h17M5 20.5V12h14v8.5M9.5 20.5v-3a2.5 2.5 0 0 1 5 0v3M7.5 12c0-2.2 1.8-3.5 4.5-3.5s4.5 1.3 4.5 3.5M12 6.5c-1-1 1-2 0-3"),
        Activity.DAMS to CueGlyph("M11 5h3l6.5 15.5H11zM2.5 20.5h19M3 9q2-1.5 4 0t4 0M3 13q2-1.5 4 0t4 0M3 17q2-1.5 4 0t4 0"),
        Activity.FELLING_TREES to CueGlyph("M16.5 20.5v-4M16.5 4L12 16.5h9zM3.5 21L8 5M7.7 6L11.5 6.6Q11.3 9.3 10.4 11.6L6.6 10"),
        Activity.CUTTING_GRASS to CueGlyph("M8.5 17C6 10 10 4.5 18.5 5C13 6.5 10 10.5 10.5 16.5M8.5 17L5 21M14 21c0-3 1-5 2.5-6.5M17.5 21c0-2 .5-4 2-5M20.5 21c0-2-.5-3-1-4"),
        Activity.OPENING_RICE_BALES to CueGlyph("M6 7h12M6 17h12M6 7a2.5 5 0 0 0 0 10M18 7a2.5 5 0 1 0 0 10a2.5 5 0 1 0 0 -10M9.5 7q-1 5 0 10M13.5 7q-1 5 0 10"),
        Activity.RAISING_DOGS to ANIMAL.getValue(Animal.DOG),
        Activity.CATTLE_WORK to ANIMAL.getValue(Animal.OX),
        Activity.DAIRY to CueGlyph("M8 7.5h8v13H8zM8 11h8M8 17h8M12 7.5V2.5M10 2.5h4M10.5 5h3"),
        Activity.GIVING_OUT to CueGlyph(HAND + "M14.5 4.5a3 3 0 1 0 0 6a3 3 0 1 0 0 -6"),
        Activity.HELPING_OTHERS to CueGlyph(HAND + "M14.5 10.5s-4-2.4-4-5a2 2 0 0 1 4 -.6a2 2 0 0 1 4 .6c0 2.6-4 5-4 5z"),
        Activity.MOVING_GOODS to CueGlyph("M3 5.5h12v9.5H3zM15 11h3.5l2.5 2.5v1.5h-6M3 9.5h12M6.5 17.5a2 2 0 1 0 0 .01M17.5 17.5a2 2 0 1 0 0 .01"),
        Activity.CONSULTATIONS to TALK,
        Activity.NEGOTIATIONS to TALK,
        Activity.ANNOUNCEMENTS to CueGlyph("M3 9.5v5h3l11 5.5V4L6 9.5zM7 14.5l1.5 6h3L10 15.5"),
        Activity.DIVINATION to STARS,
        Activity.LEARNING_ASTROLOGY to STARS,
        Activity.LEARNING_ARTS to CueGlyph("M9 18V6l10-2v12M7 16a2 2 0 1 0 0 4a2 2 0 1 0 0 -4M17 14a2 2 0 1 0 0 4a2 2 0 1 0 0 -4M9 9.5l10-2"),
        Activity.TEACHING_DHARMA to DHARMA_WHEEL,
        Activity.HEARING_DHARMA to DHARMA_WHEEL,
        Activity.ACUPUNCTURE to CueGlyph("M2.5 18.5h19M7 18.5V9M12 18.5V6M17 18.5V9", dots = listOf(Offset(7f, 7.6f), Offset(12f, 4.6f), Offset(17f, 7.6f))),
        Activity.SURGERY to CueGlyph("M3 19.5l8-8l1.5 1.5l-8 8zM11 11.5L17 5.5c2-1.5 4-.5 3 1.5c-1.5 3-4 4.5-7.5 6"),
        Activity.BLOODLETTING to CueGlyph("M9 20.5a4.5 4.5 0 0 1 -4.5 -4.5c0 -3 4.5 -8.5 4.5 -8.5s4.5 5.5 4.5 8.5a4.5 4.5 0 0 1 -4.5 4.5zM20.5 3.5L16 8M16 8l-2.8 .4l-.4 2.8l2.8 -.4z"),
        Activity.VISITING_THE_SICK to CueGlyph("M3 6.5v14M3 16h18v4.5M3 12.5h6v3.5M10.5 11.5H19a2 2 0 0 1 2 2V16M6 7.5a2 2 0 1 0 0 4a2 2 0 1 0 0 -4"),
        Activity.MAKING_MEDICINE to CueGlyph("M4 11h16c0 4.5-3.6 7.5-8 7.5S4 15.5 4 11zM8.5 20.5h7M12 18.5v2M12.5 11l5.5 -7.5"),
        Activity.PROSTRATIONS to CueGlyph("M12 3.5c-1.8 1.5-3 4-3 7.5v3L5.5 18.5L8 21l4-4l4 4l2.5-2.5L15 14v-3c0-3.5-1.2-6-3-7.5zM12 3.5V17"),
        Activity.AMULETS to CueGlyph("M8 3.5c0 3 1.5 5 4 5.5M16 3.5c0 3-1.5 5-4 5.5M6.5 9h11v11.5h-11zM10 18v-4.5l2-2.5l2 2.5V18z"),
        Activity.RECITATION to CueGlyph("M2.5 8h19v8.5h-19zM5 11h14M5 13.5h10M2.5 19h19M2.5 5.5h19"),
        Activity.MANTRAS to CueGlyph("M7 6h9v8H7zM8.5 6l1-1.8h4l1 1.8M11.5 4.2V2.5M7 8.5h9M7 11.5h9M11.5 14v7.5M16 9.5l3.5 2", dots = listOf(Offset(20f, 12.8f))),
        Activity.SMOKE_OFFERINGS to CueGlyph("M7 16h10l-1.5 4h-7zM10 13c-1.5-1.5 1.5-3 0-4.5s1.5-3 0-4.5M14 13c-1.5-1.5 1.5-3 0-4.5s1.5-3 0-4.5"),
        Activity.BANNERS to CueGlyph("M12 2.5v19M8 7h8v8.5l-2 1.5l-2 -1.5l-2 1.5l-2 -1.5zM8 7a4 2.5 0 0 1 8 0M8 11h8"),
        Activity.FIRE_OFFERINGS to CueGlyph("M4 14.5h16c-.8 3.6-4 6-8 6s-7.2-2.4-8-6zM12 13c-2.6-2.4-2.4-5.5 0-9c2.4 3.5 2.6 6.6 0 9zM8 13c-1.3-1.4-1.1-3 0-4.5M16 13c1.3-1.4 1.1-3 0-4.5"),
        Activity.RAINMAKING to CueGlyph("M7 14a4 4 0 0 1 -.5 -8a5.5 5.5 0 0 1 10.5 1a3.5 3.5 0 0 1 .5 7zM8.5 17l-1 3.5M12.5 17l-1 3.5M16.5 17l-1 3.5"),
        Activity.EMPOWERMENT to CueGlyph("M9 8.5h6M10 8.5c-3.5 1-4.5 4.5-4 7.5c.5 2.5 2.5 4 6 4s5.5-1.5 6-4c.5-3-.5-6.5-4-7.5M10.5 8.5V6.5h3v2M12 6.5c-1.2-1-1.6-2.6 0-4c1.6 1.4 1.2 3 0 4M8 20.5h8"),
        Activity.ORNAMENTS to CueGlyph("M4 3.5c0 7 3.5 10.5 8 10.5s8-3.5 8-10.5M12 14l-2.5 3l2.5 4l2.5 -4z"),
        Activity.PERFUME to CueGlyph("M12 20.5V10M6.5 20.5h11M9 20.5l1-3h4l1 3M12 10c-2-2 2-3 0-5.5c-1-1.2 0-2 1-2"),
        Activity.BREWING to CueGlyph("M7 4h10c1.5 5.3 1.5 10.7 0 16H7c-1.5-5.3-1.5-10.7 0-16zM5.9 9h12.2M5.9 15h12.2"),
        Activity.HUNTING to CueGlyph("M6 3.5c7.5 1 13.5 7 14.5 14.5M6 3.5L20.5 18M4 20l11-11M15 9h-3.5M15 9v3.5M4 20l-.5-2.5M4 20l2.5.5"),
        Activity.POISON to CueGlyph("M10 3h4M10.5 3v4.5c-3 1-4.5 3.5-4.5 6.5c0 4 2.7 6.5 6 6.5s6-2.5 6-6.5c0-3-1.5-5.5-4.5-6.5V3M10 12l4 4M14 12l-4 4"),
        Activity.HASTE to CueGlyph("M15 7a7 7 0 1 0 0 14a7 7 0 1 0 0 -14M15 10.5V14l2.5 1.5M13 3.5h4M15 3.5V7M2.5 10h4M3.5 14h3M2.5 18h4.5"),
        Activity.CARELESS_WORDS to CueGlyph("M3.5 4.5h17v11h-9l-4.5 4v-4H3.5zM13 6.5l-2.5 3.5h3.5l-2.5 3.5"),
        Activity.HEAVY_EATING to CueGlyph("M3.5 12.5h17c0 4.5-3.8 7.5-8.5 7.5s-8.5-3-8.5-7.5zM6 12.5c0-3 2.6-5 6-5s6 2 6 5M14.5 9L20.5 3M16.5 10l5-5"),
        Activity.LASTING_WORK to CueGlyph("M2.5 19.5h19M3.5 19.5L10 7.5l4 6l2-3l4.5 9M8.2 10.8l1.8 1.5l1.6-1.4"),
        Activity.MOVING_WORK to ELEMENTS.getValue("wind"),
    )

    /** The glyph a work is drawn with: its own where it has one, its family's otherwise. */
    fun of(a: Activity): CueGlyph = WORK[a] ?: FAMILY.getValue(a.family)
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
