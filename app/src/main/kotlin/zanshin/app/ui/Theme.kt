/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import io.github.iverlein.zanshin.R

/** The palette of the basic design (SPEC §10), dark only. */
object Palette {
    val ink = Color(0xFF111311)
    val surface = Color(0xFF1A1D1A)
    val raised = Color(0xFF232723)
    val selected = Color(0xFF262B26)
    val line = Color(0xFF2C302C)
    val lineStrong = Color(0xFF3A3F3A)
    val text = Color(0xFFECE8DF)
    val muted = Color(0xFFA6A99F)
    val faint = Color(0xFF80847C)
    val off = Color(0xFF4A4F49)
    val saffron = Color(0xFFE3A94F)
    val vermilion = Color(0xFFEC8468)
    val scrim = Color(0x99000000)

    // Annotation tones; they differ in lightness as well as hue.
    val good = Color(0xFF9CCB98)
    val bad = Color(0xFFE8826F)
    val mixed = Color(0xFFD9B45C)
}

@OptIn(ExperimentalTextApi::class)
private fun figtree(weight: FontWeight) = Font(
    R.font.figtree,
    weight = weight,
    variationSettings = FontVariation.Settings(FontVariation.weight(weight.weight)),
)

/** Body and interface text. */
val Figtree = FontFamily(
    figtree(FontWeight.Normal),
    figtree(FontWeight.Medium),
    figtree(FontWeight.SemiBold),
)

/** Large numerals and every kanji: a subset of Shippori Mincho (tools/subset_fonts.py). */
val Mincho = FontFamily(
    Font(R.font.shippori_medium, FontWeight.Medium),
    Font(R.font.shippori_bold, FontWeight.Bold),
)

@Composable
fun ZanshinTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = darkColorScheme(
            background = Palette.ink,
            surface = Palette.surface,
            surfaceContainerLow = Palette.surface,
            surfaceContainer = Palette.surface,
            surfaceContainerHigh = Palette.raised,
            onBackground = Palette.text,
            onSurface = Palette.text,
            onSurfaceVariant = Palette.muted,
            primary = Palette.saffron,
            outline = Palette.lineStrong,
            outlineVariant = Palette.line,
            scrim = Palette.scrim,
        ),
        content = content,
    )
}
