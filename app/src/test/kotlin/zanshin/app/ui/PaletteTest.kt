/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import zanshin.core.kyureki.Tone

/**
 * ROADMAP U12: a tone's dot, solid or a 2 dp ring, is told at a glance only if its colour stands 3:1 against
 * every background it is drawn on (WCAG 2.1's 1.4.11 for graphical objects): the page, the sheets and the
 * balloons and selected cells.
 */
class PaletteTest {
    private fun contrast(a: Color, b: Color): Double {
        val (light, dark) = listOf(a.luminance(), b.luminance()).sortedDescending()
        return (light + 0.05) / (dark + 0.05)
    }

    @Test
    fun `every tone's dot stands 3 to 1 on ink, surface and raised`() {
        val backgrounds = mapOf("ink" to Palette.ink, "surface" to Palette.surface, "raised" to Palette.raised)
        for (tone in Tone.entries) {
            for ((name, bg) in backgrounds) {
                val c = contrast(toneColor(tone), bg)
                assertTrue(c >= 3.0, "$tone on $name: %.2f:1".format(c))
            }
        }
    }
}
