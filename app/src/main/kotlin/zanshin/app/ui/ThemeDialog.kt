/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.iverlein.zanshin.R

/** A theme's name and the line under it in the dialog and the menu. */
fun AppTheme.label(): Int = when (this) {
    AppTheme.SYSTEM -> R.string.theme_system
    AppTheme.INK -> R.string.theme_ink
    AppTheme.PAPER -> R.string.theme_paper
    AppTheme.NIGHT -> R.string.theme_night
    AppTheme.MAROON -> R.string.theme_maroon
    AppTheme.SEPIA -> R.string.theme_sepia
}

private fun AppTheme.note(): Int = when (this) {
    AppTheme.SYSTEM -> R.string.theme_system_note
    AppTheme.INK -> R.string.theme_ink_note
    AppTheme.PAPER -> R.string.theme_paper_note
    AppTheme.NIGHT -> R.string.theme_night_note
    AppTheme.MAROON -> R.string.theme_maroon_note
    AppTheme.SEPIA -> R.string.theme_sepia_note
}

/**
 * The colour themes (SPEC §10.5, ROADMAP A1), each shown as a swatch of its page, its text and the three tone dots;
 * a tap applies the theme at once and the dialog stays, so the themes can be compared on it.
 */
@Composable
fun ThemeDialog(current: AppTheme, onChoose: (AppTheme) -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Palette.surface,
        title = { Text(stringResource(R.string.menu_theme), style = body.copy(fontSize = 20.sp, fontWeight = FontWeight.SemiBold)) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                for (theme in AppTheme.entries) ThemeRow(theme, theme == current) { onChoose(theme) }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) { Text(stringResource(R.string.theme_done), color = Palette.saffron) }
        },
    )
}

@Composable
private fun ThemeRow(theme: AppTheme, selected: Boolean, onClick: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
            .clickable(role = Role.RadioButton, onClick = onClick)
            .padding(horizontal = 4.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        ThemeSwatch(theme)
        Column(Modifier.weight(1f)) {
            Text(stringResource(theme.label()), style = body.copy(fontSize = 16.sp))
            Text(stringResource(theme.note()), style = body.copy(fontSize = 13.sp, color = Palette.muted))
        }
        if (selected) {
            Icon(Icons.Check, contentDescription = stringResource(R.string.selected), tint = Palette.saffron, modifier = Modifier.size(20.dp))
        } else {
            Spacer(Modifier.width(20.dp))
        }
    }
}

/** The theme's page with a line of its text and its good, mixed and bad dots; System split into Ink and Paper. */
@Composable
private fun ThemeSwatch(theme: AppTheme) {
    Canvas(Modifier.size(width = 56.dp, height = 40.dp)) {
        val corner = CornerRadius(8.dp.toPx())
        val box = Path().apply { addRoundRect(androidx.compose.ui.geometry.RoundRect(0f, 0f, size.width, size.height, corner)) }
        if (theme == AppTheme.SYSTEM) {
            clipPath(box) {
                swatch(InkColors, 0f, size.width / 2)
                swatch(PaperColors, size.width / 2, size.width)
            }
        } else {
            clipPath(box) { swatch(theme.colors(systemDark = true), 0f, size.width) }
        }
        drawRoundRect(Palette.lineStrong, cornerRadius = corner, style = Stroke(1.dp.toPx()))
    }
}

private fun DrawScope.swatch(c: ZanshinColors, from: Float, to: Float) {
    drawRect(c.ink, Offset(from, 0f), Size(to - from, size.height))
    val pad = 7.dp.toPx()
    val left = from + pad
    val width = to - from - 2 * pad
    drawRoundRect(c.text, Offset(left, pad + 2.dp.toPx()), Size(width, 3.dp.toPx()), CornerRadius(1.5.dp.toPx()))
    drawRoundRect(c.muted, Offset(left, pad + 9.dp.toPx()), Size(width * 0.7f, 3.dp.toPx()), CornerRadius(1.5.dp.toPx()))
    val r = 3.dp.toPx()
    val y = size.height - pad - r
    val step = (width - 2 * r) / 2
    listOf(c.good, c.mixed, c.bad).forEachIndexed { i, tone -> drawCircle(tone, r, Offset(left + r + i * step, y)) }
}
