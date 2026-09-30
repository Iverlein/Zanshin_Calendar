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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val SECTIONS = listOf(
    "Tibetan calendar" to "Phugpa version, computed with the arithmetic of Svante Janson, “Tibetan Calendar " +
        "Mathematics” (2007, revised 2014 and 2022), epoch 806, with the moon's daily anomaly of 1/28 used by the " +
        "Men-Tsee-Khang almanac. Lunar mansion, yoga, karaṇa and the lunar-day cycles follow Janson §10 and " +
        "Appendix E. Checked against Janson's table of Losar dates for 1927–2046 and, day by day, against Edward " +
        "Henning's computed Phugpa calendars (kalacakra.org).",
    "旧暦" to "Tenpō rules in Japan Standard Time: months begin on the day of new moon and are numbered by the " +
        "major solar term they contain. For 2033 the leap month is 閏11月, the recommended resolution of the " +
        "2033 problem. Solar terms, new moons and 雑節 are checked against the National Astronomical Observatory " +
        "of Japan (暦要項).",
    "暦注" to "The almanac annotations (十二直, 二十八宿, 九星, 選日 and 暦注下段) follow the rules tabulated in " +
        "Japanese Wikipedia after 岡田芳朗・阿久根末忠『現代こよみ読み解き事典』 (1993), and are checked day by day " +
        "against こよみのページ (koyomi8.com). 神吉日 and 凶会日 are left out: their full rules are not recoverable.",
    "Sun and moon" to "Jean Meeus, “Astronomical Algorithms” (2nd ed., 1998): new moons from chapter 49, the " +
        "sun from the VSOP87D series of Bretagnon and Francou, ΔT from the polynomials of Espenak and Meeus " +
        "(NASA). Everything is computed on this phone; the app has no internet access.",
    "Readings" to "Every reading names its source. Japanese: こよみ博物館 of the almanac publisher Todan (六曜, " +
        "十二直, 二十八宿); Japanese Wikipedia (選日, 暦注下段, 九星, 九星気学, 歳徳神, 庚申待), whose adapted text is " +
        "licensed CC BY-SA 4.0; NAOJ 暦Wiki (雑節, 節句, 十三夜); the dictionaries on Kotobank. Tibetan: " +
        "Lama Zopa Rinpoche's translation of the hair-cutting days (FPMT, 2008); Edition Rabten's Tibetan " +
        "calendar; Edward Henning's symbolic details of the calendar; and Jigme Lingpa's prayer on the tenth " +
        "day (Lotsawa House); the colours of the nine numbers from Alexander Berzin's Details of Tibetan " +
        "Astrology (Study Buddhism); the four aspects of each year and the yearly pebbles from the White Beryl " +
        "of Desi Sangye Gyatso with Lochen Dharmashri's Moonbeams. Readings not adapted from Wikipedia are the app's own English summaries of the " +
        "cited sources, under the app's licence.",
    "Tibetan" to "Tibetan terms are written in Tibetan script from their Wylie spelling, checked against the " +
        "White Beryl of Desi Sangye Gyatso; their pronunciation follows the THL Simplified Phonetic " +
        "Transcription of Standard Tibetan (David Germano and Nicolas Tournadre, 2003).",
    "Glyphs" to "Festival glyphs are drawn for this app and share its licence.",
    "Cities" to "City list from GeoNames (geonames.org), licensed under Creative Commons Attribution 4.0.",
    "Fonts" to "Figtree, © 2022 The Figtree Project Authors; Shippori Mincho, © 2021 The Shippori Mincho " +
        "Project Authors; and Noto Serif Tibetan, © 2022 The Noto Project Authors; all under the SIL Open Font " +
        "License 1.1.",
    "Licence" to "Zanshin Calendar is free software under the Mozilla Public License 2.0.",
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
            IconButton(onClick = onBack) { Icon(Icons.ChevronLeft, contentDescription = "Back", tint = Palette.text) }
            Text("About & sources", style = body.copy(fontSize = 18.sp, fontWeight = FontWeight.SemiBold))
        }
        Column(
            Modifier.verticalScroll(rememberScrollState()).padding(horizontal = 24.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            for ((title, text) in SECTIONS) {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(title, style = body.copy(fontSize = 16.sp, fontWeight = FontWeight.SemiBold, fontFamily = if (title == "旧暦") Mincho else Figtree))
                    Text(text, style = body.copy(color = Palette.muted, lineHeight = 22.sp))
                }
            }
        }
    }
}
