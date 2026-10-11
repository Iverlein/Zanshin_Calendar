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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.iverlein.zanshin.R
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import java.time.YearMonth
import java.time.ZoneId
import java.time.format.TextStyle
import zanshin.app.CalendarKind
import zanshin.app.DayInfo
import zanshin.app.LocalLabels
import zanshin.core.kyureki.DayMark
import zanshin.core.time.SUPPORTED_RANGE

/** What the picker shows: the days of a month, the years, or the twelve months of a year. */
private enum class PickerView { DAYS, YEARS, MONTHS }

/**
 * Month grid of Gregorian days; under each, the day number in the calendar
 * being viewed, with a dot on holidays and festivals and, in the 旧暦 view,
 * the almanac's mark for 受死日 and 天赦日. Limited to 1900–2100. A tap on
 * the month's title opens the years, then the twelve months of the chosen
 * year, then the days, Material's pattern (ROADMAP U9); the chevrons stay.
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
    var view by remember { mutableStateOf(PickerView.DAYS) }
    // The year whose months are shown, chosen in the years' grid.
    var year by remember { mutableIntStateOf(month.year) }
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
            // In the days the chevrons move a month, in the months a year; the years' grid scrolls instead.
            val canBack = when (view) {
                PickerView.DAYS -> month > firstMonth
                PickerView.MONTHS -> year > firstMonth.year
                PickerView.YEARS -> false
            }
            val canForward = when (view) {
                PickerView.DAYS -> month < lastMonth
                PickerView.MONTHS -> year < lastMonth.year
                PickerView.YEARS -> false
            }
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                IconButton(
                    onClick = { if (view == PickerView.MONTHS) year -= 1 else month = month.minusMonths(1) },
                    enabled = canBack,
                    modifier = if (view == PickerView.YEARS) Modifier.alpha(0f).clearAndSetSemantics {} else Modifier,
                ) {
                    Icon(
                        Icons.ChevronLeft,
                        contentDescription = stringResource(if (view == PickerView.MONTHS) R.string.picker_previous_year else R.string.picker_previous_month),
                        tint = if (canBack) Palette.text else Palette.off,
                    )
                }
                val title = if (view == PickerView.DAYS) month.atDay(1).format(labels.monthTitle) else "$year"
                val clickLabel = stringResource(if (view == PickerView.YEARS) R.string.picker_show_days else R.string.picker_choose_year)
                Row(
                    Modifier
                        .heightIn(min = 44.dp)
                        .clip(RoundedCornerShape(22.dp))
                        .clickable(role = Role.Button, onClickLabel = clickLabel) {
                            if (view == PickerView.YEARS) {
                                view = PickerView.DAYS
                            } else {
                                year = month.year
                                view = PickerView.YEARS
                            }
                        }
                        .padding(start = 12.dp, end = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(2.dp),
                ) {
                    Text(title, style = body.copy(fontSize = 17.sp, fontWeight = FontWeight.SemiBold))
                    Icon(
                        Icons.ChevronDown,
                        contentDescription = null,
                        tint = if (view == PickerView.DAYS) Palette.muted else accent,
                        modifier = Modifier.size(18.dp).rotate(if (view == PickerView.YEARS) 180f else 0f),
                    )
                }
                IconButton(
                    onClick = { if (view == PickerView.MONTHS) year += 1 else month = month.plusMonths(1) },
                    enabled = canForward,
                    modifier = if (view == PickerView.YEARS) Modifier.alpha(0f).clearAndSetSemantics {} else Modifier,
                ) {
                    Icon(
                        Icons.ChevronRight,
                        contentDescription = stringResource(if (view == PickerView.MONTHS) R.string.picker_next_year else R.string.picker_next_month),
                        tint = if (canForward) Palette.text else Palette.off,
                    )
                }
            }

            when (view) {
                PickerView.YEARS -> YearGrid(
                    years = firstMonth.year..lastMonth.year,
                    shown = month.year,
                    thisYear = today.year,
                    accent = accent,
                ) {
                    year = it
                    view = PickerView.MONTHS
                }
                PickerView.MONTHS -> MonthGrid(
                    year = year,
                    shown = month,
                    thisMonth = YearMonth.from(today),
                    range = firstMonth..lastMonth,
                    accent = accent,
                ) {
                    month = it
                    view = PickerView.DAYS
                }
                PickerView.DAYS -> DayGrid(month, selected, today, calendar, zone, accent, onPick)
            }

            if (view == PickerView.DAYS && calendar == CalendarKind.KYUREKI) {
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
                    if (view == PickerView.DAYS) {
                        stringResource(if (calendar == CalendarKind.TIBETAN) R.string.picker_small_figures_tibetan else R.string.picker_small_figures_kyureki)
                    } else {
                        ""
                    },
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

/** The height of the years' and months' grids, about that of a month's days, so the sheet keeps its size. */
private val JUMP_HEIGHT = 340.dp

/** The month's days, a week to a row, from Monday. */
@Composable
private fun DayGrid(
    month: YearMonth,
    selected: LocalDate,
    today: LocalDate,
    calendar: CalendarKind,
    zone: ZoneId,
    accent: Color,
    onPick: (LocalDate) -> Unit,
) {
    val labels = LocalLabels.current
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
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
    }
}

/** The years of the range, four to a row, scrolled to the year shown; this year outlined, the shown one filled. */
@Composable
private fun YearGrid(years: IntRange, shown: Int, thisYear: Int, accent: Color, onPick: (Int) -> Unit) {
    val columns = 4
    // Open with the shown year in the grid's third row, as Material does.
    val state = rememberLazyGridState(initialFirstVisibleItemIndex = maxOf(0, ((shown - years.first) / columns - 2) * columns))
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        state = state,
        modifier = Modifier.fillMaxWidth().height(JUMP_HEIGHT),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(years.count(), key = { years.first + it }) { i ->
            val y = years.first + i
            JumpCell("$y", y == shown, y == thisYear, true, accent) { onPick(y) }
        }
    }
}

/** The twelve months of [year], three to a row; this month outlined, the shown one filled, those out of range greyed. */
@Composable
private fun MonthGrid(year: Int, shown: YearMonth, thisMonth: YearMonth, range: ClosedRange<YearMonth>, accent: Color, onPick: (YearMonth) -> Unit) {
    val labels = LocalLabels.current
    Column(Modifier.fillMaxWidth().height(JUMP_HEIGHT), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        for (row in Month.entries.chunked(3)) {
            Row(Modifier.fillMaxWidth().weight(1f), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                for (m in row) {
                    val ym = YearMonth.of(year, m)
                    val name = m.getDisplayName(TextStyle.FULL_STANDALONE, labels.locale).replaceFirstChar { it.titlecase(labels.locale) }
                    Box(Modifier.weight(1f).fillMaxHeight(), contentAlignment = Alignment.Center) {
                        JumpCell(name, ym == shown, ym == thisMonth, ym in range, accent) { onPick(ym) }
                    }
                }
            }
        }
    }
}

/** A year or a month to jump to: filled in the accent when shown, outlined when it holds today. */
@Composable
private fun JumpCell(text: String, selected: Boolean, current: Boolean, enabled: Boolean, accent: Color, onClick: () -> Unit) {
    val fg = when {
        selected -> Palette.ink
        !enabled -> Palette.off
        current -> accent
        else -> Palette.text
    }
    Box(
        Modifier
            .fillMaxWidth()
            .height(44.dp)
            .clip(RoundedCornerShape(22.dp))
            .background(if (selected) accent else Color.Transparent)
            .border(1.dp, if (current && !selected) accent else Color.Transparent, RoundedCornerShape(22.dp))
            .clickable(enabled = enabled, role = Role.Button, onClick = onClick)
            .semantics { this.selected = selected },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text,
            style = body.copy(fontSize = 15.sp, fontWeight = if (selected || current) FontWeight.SemiBold else FontWeight.Normal, color = fg, fontFeatureSettings = "tnum"),
            maxLines = 1,
        )
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
