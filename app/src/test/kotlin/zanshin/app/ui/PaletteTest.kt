/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import zanshin.core.kyureki.Tone
import zanshin.core.tibetan.Element
import java.io.File
import kotlin.math.abs
import kotlin.math.cbrt

/**
 * The themes' contrast (ROADMAP U12, A1), after WCAG 2.1: text 4.5:1 (1.4.3), a graphical object 3:1 (1.4.11), each
 * against every background it is drawn on, the page, the sheets and the raised and selected cells. Each theme is
 * drawn by setting [Palette.colors], as the app does, so [toneColor] and the printed colours are read as drawn.
 */
class PaletteTest {
    private val themes = mapOf("Ink" to InkColors, "Night" to NightColors, "Paper" to PaperColors)

    private fun contrast(a: Color, b: Color): Double {
        val (light, dark) = listOf(a.luminance(), b.luminance()).sortedDescending()
        return (light + 0.05) / (dark + 0.05)
    }

    /** CIELAB lightness L*, from the relative luminance. */
    private fun lightness(c: Color): Double {
        val y = c.luminance().toDouble()
        return if (y > 216.0 / 24389) 116 * cbrt(y) - 16 else y * 24389 / 27
    }

    private fun ZanshinColors.backgrounds() = mapOf("ink" to ink, "surface" to surface, "raised" to raised)

    private fun forEachTheme(check: (String, ZanshinColors) -> Unit) {
        for ((name, c) in themes) {
            Palette.colors = c
            check(name, c)
        }
    }

    private fun atLeast(ratio: Double, fg: Color, bg: Color, what: String) {
        val c = contrast(fg, bg)
        assertTrue(c >= ratio, "$what: %.2f:1, under %.1f:1".format(c, ratio))
    }

    @AfterEach
    fun ink() {
        Palette.colors = InkColors
    }

    @Test
    fun `text and muted text stand 4_5 to 1 on the page, the sheets and the raised cells`() = forEachTheme { name, c ->
        for ((bg, color) in c.backgrounds()) {
            atLeast(4.5, c.text, color, "$name text on $bg")
            atLeast(4.5, c.muted, color, "$name muted on $bg")
        }
    }

    @Test
    fun `faint text and every tone's dot stand 3 to 1, a ring as a dot`() = forEachTheme { name, c ->
        for ((bg, color) in c.backgrounds() + ("selected" to c.selected)) {
            atLeast(3.0, c.faint, color, "$name faint on $bg")
            for (tone in Tone.entries) atLeast(3.0, toneColor(tone), color, "$name $tone on $bg")
        }
    }

    @Test
    fun `the tones and the accents stand 4_5 to 1 where they colour text`() = forEachTheme { name, c ->
        for ((bg, color) in c.backgrounds()) {
            for (tone in listOf(Tone.GOOD, Tone.BAD, Tone.MIXED)) atLeast(4.5, toneColor(tone), color, "$name $tone text on $bg")
            atLeast(4.5, c.saffron, color, "$name saffron on $bg")
            atLeast(4.5, c.vermilion, color, "$name vermilion on $bg")
        }
    }

    @Test
    fun `text set on an accent fill stands 4_5 to 1`() = forEachTheme { name, c ->
        atLeast(4.5, c.onAccent, c.saffron, "$name on saffron")
        atLeast(4.5, c.onAccent, c.vermilion, "$name on vermilion")
    }

    /**
     * Good, bad and mixed are told apart by lightness as well as hue, for readers who do not see red against green:
     * each pair at least 2 L* apart, the floor Ink's own closest pair sets (good and mixed, 2.4).
     */
    @Test
    fun `the three tones differ in lightness`() = forEachTheme { name, c ->
        val tones = listOf("good" to c.good, "bad" to c.bad, "mixed" to c.mixed)
        for (i in tones.indices) for (j in i + 1 until tones.size) {
            val d = abs(lightness(tones[i].second) - lightness(tones[j].second))
            assertTrue(d >= 2.0, "$name ${tones[i].first} and ${tones[j].first}: %.1f L* apart".format(d))
        }
    }

    @Test
    fun `the element colours stand 3 to 1 on the page and the sheets of every theme`() = forEachTheme { name, c ->
        for (e in Element.entries) for ((bg, color) in c.backgrounds()) atLeast(3.0, elementColour(e), color, "$name $e on $bg")
    }

    @Test
    fun `the printed boxes of the page's own lightness get a rim`() {
        Palette.colors = InkColors
        assertEquals(listOf(2), (1..9).filter(::needsRim))
        Palette.colors = PaperColors
        assertEquals(listOf(1, 6, 8), (1..9).filter(::needsRim))
    }

    @Test
    fun `System is Ink on a dark phone and Paper on a light one`() {
        assertEquals(InkColors, AppTheme.SYSTEM.colors(systemDark = true))
        assertEquals(PaperColors, AppTheme.SYSTEM.colors(systemDark = false))
        assertEquals(NightColors, AppTheme.NIGHT.colors(systemDark = false))
    }

    /**
     * Every colour a token first (ROADMAP A1): a colour value is written only in the theme and in the printed
     * colours of the stars, the sme ba and the elements, which keep their hues on every theme; every other colour
     * is a role of [Palette]. The icons' vectors carry a placeholder stroke, which the tint replaces wherever one
     * is drawn (`Icons.stroke`).
     */
    @Test
    fun `no colour is written outside the theme and the printed colours`() {
        val printed = setOf("starColour", "smeBaColour", "elementColour", "stroke")
        val strays = mutableListOf<String>()
        File("src/main/kotlin").walkTopDown().filter { it.extension == "kt" && it.name != "Theme.kt" }.forEach { file ->
            var function = ""
            file.readLines().forEachIndexed { i, line ->
                Regex("""^\s*(?:private )?fun (\w+)""").find(line)?.let { function = it.groupValues[1] }
                if (Regex("""Color\(0x|Color\.(White|Black|Gray|Red|Green|Blue|Yellow)\b""").containsMatchIn(line) && function !in printed) {
                    strays += "${file.name}:${i + 1}: ${line.trim()}"
                }
            }
        }
        assertTrue(strays.isEmpty(), strays.joinToString("\n"))
    }
}
