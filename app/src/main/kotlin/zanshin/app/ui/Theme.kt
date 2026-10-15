/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.em
import io.github.iverlein.zanshin.R
import zanshin.core.tibetan.Ewts

/**
 * A theme's colours (SPEC §10.1, ROADMAP A1): the roles every screen, sheet and diagram draws with. [ink] is the
 * page; [onAccent] the text and thumbs set on a saffron or vermilion fill, the page's colour on every theme, as
 * each accent is chosen to stand against its page. [light] picks the printed colours' set for a light page.
 */
@Immutable
data class ZanshinColors(
    val light: Boolean,
    val ink: Color,
    val surface: Color,
    val raised: Color,
    val selected: Color,
    val line: Color,
    val lineStrong: Color,
    val text: Color,
    val muted: Color,
    val faint: Color,
    val off: Color,
    val saffron: Color,
    val vermilion: Color,
    // Annotation tones; they differ in lightness as well as hue.
    val good: Color,
    val bad: Color,
    val mixed: Color,
    val scrim: Color = Color(0x99000000),
    val onAccent: Color = ink,
)

/** Ink, the dark palette of the basic design, the default for every install (D5). */
val InkColors = ZanshinColors(
    light = false,
    ink = Color(0xFF111311),
    surface = Color(0xFF1A1D1A),
    raised = Color(0xFF232723),
    selected = Color(0xFF262B26),
    line = Color(0xFF2C302C),
    lineStrong = Color(0xFF3A3F3A),
    text = Color(0xFFECE8DF),
    muted = Color(0xFFA6A99F),
    faint = Color(0xFF80847C),
    off = Color(0xFF4A4F49),
    saffron = Color(0xFFE3A94F),
    vermilion = Color(0xFFEC8468),
    good = Color(0xFF9CCB98),
    bad = Color(0xFFE8826F),
    mixed = Color(0xFFD9B45C),
)

/** Night: Ink on a black page, for OLED screens; the sheets and cells a step above black. */
val NightColors = InkColors.copy(
    ink = Color(0xFF000000),
    surface = Color(0xFF0D0F0D),
    raised = Color(0xFF181B18),
    selected = Color(0xFF1C201C),
    line = Color(0xFF242824),
    lineStrong = Color(0xFF333833),
    onAccent = Color(0xFF000000),
)

/**
 * Paper: a warm off-white page with dark ink. The accents and tones are darkened until each stands 4.5:1 as text
 * on the page, the sheets and the raised cells; the tones keep their order apart in lightness, good darkest.
 */
val PaperColors = ZanshinColors(
    light = true,
    ink = Color(0xFFF5F1E8),
    surface = Color(0xFFECE7DB),
    raised = Color(0xFFE3DDCF),
    selected = Color(0xFFDCD5C5),
    line = Color(0xFFD6CFC0),
    lineStrong = Color(0xFFBDB5A4),
    text = Color(0xFF1F1D19),
    muted = Color(0xFF57534B),
    faint = Color(0xFF6F6B62),
    off = Color(0xFFB4AD9E),
    saffron = Color(0xFF8A5300),
    vermilion = Color(0xFFA8361C),
    good = Color(0xFF1A4F23),
    bad = Color(0xFFAD3019),
    mixed = Color(0xFF6A4C00),
    scrim = Color(0x66000000),
)

/**
 * Maroon: the robe's maroon with saffron. Vermilion and saffron sit beside maroon in hue, so the theme carries an
 * accent pair and a tone set of its own (ROADMAP A1): saffron a brighter gold, the 旧暦 accent and the bad tone
 * turned towards orange and lightened, each apart from the page in hue as well as lightness.
 */
val MaroonColors = ZanshinColors(
    light = false,
    ink = Color(0xFF3A1117),
    surface = Color(0xFF46161D),
    raised = Color(0xFF521C24),
    selected = Color(0xFF5A2029),
    line = Color(0xFF61262F),
    lineStrong = Color(0xFF77343E),
    text = Color(0xFFF6EADB),
    muted = Color(0xFFD4BBAE),
    faint = Color(0xFFB8998D),
    off = Color(0xFF7A4A50),
    saffron = Color(0xFFF4BA4A),
    vermilion = Color(0xFFFFAC80),
    good = Color(0xFFB0E0A6),
    bad = Color(0xFFFFA07C),
    mixed = Color(0xFFEAC66A),
)

/**
 * Sepia: a parchment page for the evening, less blue in it than Paper, with brown ink; the accents and tones
 * darkened to 4.5:1 on its deeper sheets.
 */
val SepiaColors = ZanshinColors(
    light = true,
    ink = Color(0xFFEFE3C8),
    surface = Color(0xFFE6D8BA),
    raised = Color(0xFFDCCCAB),
    selected = Color(0xFFD4C3A0),
    line = Color(0xFFD2C29F),
    lineStrong = Color(0xFFB5A27E),
    text = Color(0xFF2A1F12),
    muted = Color(0xFF5A4A35),
    faint = Color(0xFF6C5C45),
    off = Color(0xFFB09F7E),
    saffron = Color(0xFF7E4C00),
    vermilion = Color(0xFF9C3418),
    good = Color(0xFF2A4A14),
    bad = Color(0xFF9E2A14),
    mixed = Color(0xFF5E4500),
    scrim = Color(0x66000000),
)

/** The themes offered in Settings (SPEC §10.5), by the owner's names (D5); System follows the phone's dark setting. */
enum class AppTheme { SYSTEM, INK, PAPER, NIGHT, MAROON, SEPIA }

/** The colours [theme] draws with, [systemDark] deciding System between Ink and Paper. */
fun AppTheme.colors(systemDark: Boolean): ZanshinColors = when (this) {
    AppTheme.SYSTEM -> if (systemDark) InkColors else PaperColors
    AppTheme.INK -> InkColors
    AppTheme.PAPER -> PaperColors
    AppTheme.NIGHT -> NightColors
    AppTheme.MAROON -> MaroonColors
    AppTheme.SEPIA -> SepiaColors
}

/**
 * The palette of the theme drawn now: every screen, sheet and Canvas reads its roles here, so the call sites keep
 * their names. A snapshot state rather than a CompositionLocal: a theme is one for the whole app, and the reads
 * inside draw lambdas and plain functions ([toneColor], the printed colours) are tracked all the same, so a
 * change of theme recomposes and redraws everything that read the old one.
 */
object Palette {
    var colors: ZanshinColors by mutableStateOf(InkColors)

    val light get() = colors.light
    val ink get() = colors.ink
    val surface get() = colors.surface
    val raised get() = colors.raised
    val selected get() = colors.selected
    val line get() = colors.line
    val lineStrong get() = colors.lineStrong
    val text get() = colors.text
    val muted get() = colors.muted
    val faint get() = colors.faint
    val off get() = colors.off
    val saffron get() = colors.saffron
    val vermilion get() = colors.vermilion
    val scrim get() = colors.scrim
    val onAccent get() = colors.onAccent

    val good get() = colors.good
    val bad get() = colors.bad
    val mixed get() = colors.mixed
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

/** Tibetan script: Noto Serif Tibetan, one weight, the Tibetan block (tools/subset_fonts.py). */
val TibetanSerif = FontFamily(Font(R.font.noto_serif_tibetan, FontWeight.Medium))

private val TIBETAN_RUN = Regex("[\\u0F00-\\u0FFF]+")

/**
 * [text] with its runs of Tibetan script set in [TibetanSerif], a little
 * larger as [tibetanStyle] sets it: the titles that readings, notes and
 * sources give as "English (Tibetan, Wylie)" (SPEC §8).
 */
fun withTibetan(text: String): AnnotatedString = buildAnnotatedString {
    var from = 0
    for (m in TIBETAN_RUN.findAll(text)) {
        append(text.substring(from, m.range.first))
        withStyle(SpanStyle(fontFamily = TibetanSerif, fontSize = 1.15.em)) { append(m.value) }
        from = m.range.last + 1
    }
    append(text.substring(from))
}

/**
 * A Tibetan term as SPEC §8.1 names it: [name], then its script and Wylie in
 * brackets, the brackets in [bracket] where the name is set large (a festival
 * headline, the month).
 */
fun namedTerm(name: String, wylie: String, bracket: SpanStyle? = null): AnnotatedString = buildAnnotatedString {
    append(name)
    val rest = withTibetan(Ewts.named(name, wylie).removePrefix(name))
    if (bracket == null) append(rest) else withStyle(bracket) { append(rest) }
}

/** Large numerals and every kanji: a subset of Shippori Mincho (tools/subset_fonts.py). */
val Mincho = FontFamily(
    Font(R.font.shippori_medium, FontWeight.Medium),
    Font(R.font.shippori_bold, FontWeight.Bold),
)

/** The Material scheme built from the theme drawn now, `lightColorScheme` for a light one. */
@Composable
fun ZanshinTheme(content: @Composable () -> Unit) {
    val c = Palette.colors
    val scheme = if (c.light) {
        lightColorScheme(
            background = c.ink, surface = c.surface, surfaceContainerLowest = c.ink, surfaceContainerLow = c.surface,
            surfaceContainer = c.surface, surfaceContainerHigh = c.raised, surfaceContainerHighest = c.selected,
            surfaceVariant = c.raised, onBackground = c.text, onSurface = c.text, onSurfaceVariant = c.muted,
            primary = c.saffron, onPrimary = c.onAccent, secondary = c.saffron, onSecondary = c.onAccent,
            outline = c.lineStrong, outlineVariant = c.line, scrim = c.scrim,
        )
    } else {
        darkColorScheme(
            background = c.ink, surface = c.surface, surfaceContainerLow = c.surface, surfaceContainer = c.surface,
            surfaceContainerHigh = c.raised, onBackground = c.text, onSurface = c.text, onSurfaceVariant = c.muted,
            primary = c.saffron, outline = c.lineStrong, outlineVariant = c.line, scrim = c.scrim,
        )
    }
    MaterialTheme(colorScheme = scheme, content = content)
}
