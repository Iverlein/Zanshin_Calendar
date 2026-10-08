/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.iverlein.zanshin.R

private val SECTIONS = listOf(
    R.string.about_tibetan_title to R.string.about_tibetan,
    R.string.about_kyureki_title to R.string.about_kyureki,
    R.string.about_rekichu_title to R.string.about_rekichu,
    R.string.about_astronomy_title to R.string.about_astronomy,
    R.string.about_readings_title to R.string.about_readings,
    R.string.about_tibetan_script_title to R.string.about_tibetan_script,
    R.string.about_glyphs_title to R.string.about_glyphs,
    R.string.about_cities_title to R.string.about_cities,
    R.string.about_fonts_title to R.string.about_fonts,
    R.string.about_licence_title to R.string.about_licence,
)

@Composable
fun AboutScreen(onBack: () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Palette.ink)
            .windowInsetsPadding(WindowInsets.safeDrawing),
    ) {
        Row(Modifier.fillMaxWidth().height(64.dp).padding(start = 6.dp, top = 8.dp), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) { Icon(Icons.ChevronLeft, contentDescription = stringResource(R.string.back), tint = Palette.text) }
            Text(stringResource(R.string.about_title), style = body.copy(fontSize = 18.sp, fontWeight = FontWeight.SemiBold))
        }
        Column(
            Modifier.verticalScroll(rememberScrollState()).padding(horizontal = 24.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            for ((titleId, textId) in SECTIONS) {
                val title = stringResource(titleId)
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(title, style = body.copy(fontSize = 16.sp, fontWeight = FontWeight.SemiBold, fontFamily = if (title == "旧暦") Mincho else Figtree))
                    Text(withTibetan(stringResource(textId)), style = body.copy(color = Palette.muted, lineHeight = 22.sp))
                }
            }
        }
    }
}
