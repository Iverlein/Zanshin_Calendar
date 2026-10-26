/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.iverlein.zanshin.R
import zanshin.app.DayInfo
import zanshin.app.Labels
import zanshin.app.LocalLabels
import zanshin.app.Person
import zanshin.core.kyureki.Tone
import zanshin.core.texts.ActivityFamily
import zanshin.core.texts.Catalog
import zanshin.core.texts.DaySummary
import zanshin.core.texts.ReadingRow
import zanshin.core.texts.Texts
import zanshin.core.texts.family
import zanshin.core.texts.gloss
import zanshin.core.tibetan.DayLetters
import zanshin.core.tibetan.ElementQuarter
import zanshin.core.tibetan.Ewts
import zanshin.core.tibetan.Element
import zanshin.core.tibetan.Force
import zanshin.core.tibetan.Health
import zanshin.core.tibetan.Humour
import zanshin.core.tibetan.LaHour
import zanshin.core.tibetan.MedicalSeason
import zanshin.core.tibetan.Sign
import zanshin.core.tibetan.TibetanCalendar
import zanshin.core.tibetan.YearReckoning
import java.time.LocalDate
import java.time.ZoneId

/**
 * Tabs as one row of segments, the chosen one filled (the Health screen's, the earth lords' sheet); screen readers get them as tabs. Where they do not
 * fit as equal segments (a large font, ROADMAP A2), each is as wide as its name and the row scrolls sideways.
 */
@Composable
fun SegmentTabs(names: List<String>, selectedIndex: Int, onSelect: (Int) -> Unit) {
    val style = body.copy(fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
    val measurer = rememberTextMeasurer()
    val density = LocalDensity.current
    BoxWithConstraints(Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        val gap = 6.dp
        val widest = remember(names, density) { with(density) { names.maxOf { measurer.measure(it, style, maxLines = 1).size.width }.toDp() } }
        val equal = (widest + 24.dp) * names.size + gap * (names.size - 1) <= maxWidth
        Row(
            Modifier
                .fillMaxWidth()
                .then(if (equal) Modifier else Modifier.horizontalScroll(rememberScrollState()))
                .heightIn(min = 44.dp)
                .height(IntrinsicSize.Min)
                .selectableGroup(),
            horizontalArrangement = Arrangement.spacedBy(gap),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            names.forEachIndexed { i, _ ->
                val selected = i == selectedIndex
                val shape = RoundedCornerShape(18.dp)
                Box(
                    Modifier
                        .then(if (equal) Modifier.weight(1f) else Modifier)
                        .fillMaxHeight()
                        .selectable(selected = selected, role = Role.Tab) { onSelect(i) },
                    contentAlignment = Alignment.Center,
                ) {
                    Box(
                        Modifier
                            .then(if (equal) Modifier.fillMaxWidth() else Modifier)
                            .heightIn(min = 34.dp)
                            .then(if (selected) Modifier.background(Palette.saffron, shape) else Modifier.border(1.dp, Palette.line, shape)),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            names[i],
                            style = style.copy(color = if (selected) Palette.onAccent else Palette.muted),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(horizontal = if (equal) 4.dp else 14.dp, vertical = 4.dp),
                        )
                    }
                }
            }
        }
    }
}