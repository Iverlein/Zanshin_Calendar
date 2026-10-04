/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zanshin.app.CalendarKind
import zanshin.app.DayInfo
import zanshin.app.LocalLabels
import io.github.iverlein.zanshin.R
import zanshin.core.kyureki.DayMark
import zanshin.core.time.SUPPORTED_RANGE
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.ZoneId
import java.time.format.TextStyle

/**
 * Month grid of Gregorian days; under each, the day number in the calendar
 * being viewed, with a dot on holidays and festivals and, in the 旧暦 view,
 * the almanac's mark for 受死日 and 天赦日. Limited to 1900–2100.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerSheet(
    selected: LocalDate,
    today: LocalDate,
    calendar: CalendarKind,
    zone: ZoneId,
    onPick: (LocalDate) -> Unit,
    onDismiss: () -> Unit,
) {
    val labels = LocalLabels.current
    val accent = if (calendar == CalendarKind.TIBETAN) Palette.saffron else Palette.vermilion
    var month by remember { mutableStateOf(YearMonth.from(selected)) }
    val firstMonth = YearMonth.from(SUPPORTED_RANGE.start)
    val lastMonth = YearMonth.from(SUPPORTED_RANGE.endInclusive)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        containerColor = Palette.surface,
        scrimColor = Palette.scrim,
    ) {
        Column(
            Modifier.padding(start = 16.dp, end = 16.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                IconButton(onClick = { month = month.minusMonths(1) }, enabled = month > firstMonth) {
                    Icon(Icons.ChevronLeft, contentDescription = stringResource(R.string.picker_previous_month), tint = if (month > firstMonth) Palette.text else Palette.off)
                }
                Text(month.atDay(1).format(labels.monthTitle), style = body.copy(fontSize = 17.sp, fontWeight = FontWeight.SemiBold))
                IconButton(onClick = { month = month.plusMonths(1) }, enabled = month < lastMonth) {
                    Icon(Icons.ChevronRight, contentDescription = stringResource(R.string.picker_next_month), tint = if (month < lastMonth) Palette.text else Palette.off)
                }
            }
            Row(Modifier.fillMaxWidth()) {
                for (name in DayOfWeek.entries.map { it.getDisplayName(TextStyle.SHORT, labels.locale) }) {
                    Text(name, style = body.copy(fontSize = 12.sp, color = Palette.faint, textAlign = TextAlign.Center), modifier = Modifier.weight(1f))
                }
            }

            val lead = month.atDay(1).dayOfWeek.value - 1
            val cells: List<LocalDate?> = List(lead) { null } + (1..month.lengthOfMonth()).map { month.atDay(it) }
            for (week in cells.chunked(7)) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    for (i in 0 until 7) {
                        val date = week.getOrNull(i)
                        Box(Modifier.weight(1f)) {
                            if (date != null) DayCell(date, date == selected, date == today, calendar, zone, accent) { onPick(date) }
                        }
                    }
                }
            }

            if (calendar == CalendarKind.KYUREKI) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    for (mark in DayMark.entries) {
                        DayMarkGlyph(mark, Palette.faint, 7.dp)
                        GlossText(
                            mark.senjitsu.kanji,
                            mark.senjitsu.english,
                            body.copy(fontFamily = Mincho, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Palette.faint),
                            reading = mark.senjitsu.reading,
                            preferAbove = true,
                        )
                        Spacer(Modifier.width(10.dp))
                    }
                }
            }

            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    stringResource(if (calendar == CalendarKind.TIBETAN) R.string.picker_small_figures_tibetan else R.string.picker_small_figures_kyureki),
                    style = body.copy(fontSize = 12.sp, color = Palette.faint),
                )
                Text(
                    stringResource(R.string.today),
                    style = body.copy(fontSize = 14.sp, fontWeight = FontWeight.SemiBold),
                    modifier = Modifier
                        .heightIn(min = 44.dp)
                        .border(1.dp, Palette.lineStrong, RoundedCornerShape(22.dp))
                        .clickable(role = Role.Button) { onPick(today) }
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                )
            }
        }
    }
}

@Composable
private fun DayCell(
    date: LocalDate,
    selected: Boolean,
    today: Boolean,
    calendar: CalendarKind,
    zone: ZoneId,
    accent: Color,
    onClick: () -> Unit,
) {
    val info = remember(date) { DayInfo.of(date, zone) }
    val sub = when (calendar) {
        CalendarKind.TIBETAN -> "${info.tibetan.day}" + if (info.tibetan.holiday != null) " •" else ""
        CalendarKind.KYUREKI -> {
            val k = info.kyureki
            (if (k.day == 1) "${k.month}/1" else "${k.day}") + if (k.festival != null || k.gregorianFestival != null) " •" else ""
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(if (selected) accent else Color.Transparent, RoundedCornerShape(12.dp))
            .border(1.dp, if (today && !selected) accent else Color.Transparent, RoundedCornerShape(12.dp))
            .clickable(role = Role.Button, onClickLabel = date.toString(), onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val fg = if (selected) Palette.ink else Palette.text
        Text("${date.dayOfMonth}", style = body.copy(fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = fg))
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(3.dp)) {
            Text(sub, style = body.copy(fontSize = 11.sp, color = fg.copy(alpha = 0.8f)))
            val mark = if (calendar == CalendarKind.KYUREKI) info.rekichu.mark else null
            if (mark != null) {
                DayMarkGlyph(
                    mark,
                    fg.copy(alpha = 0.8f),
                    6.dp,
                    Modifier.semantics { contentDescription = "${mark.senjitsu.kanji}, ${mark.senjitsu.english}" },
                )
            }
        }
    }
}
