/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.iverlein.zanshin.R
import java.time.LocalDate
import zanshin.app.LocalLabels
import zanshin.core.texts.Activities
import zanshin.core.texts.Activity
import zanshin.core.texts.Catalog
import zanshin.core.texts.LordTexts
import zanshin.core.texts.Reading
import zanshin.core.texts.gloss
import zanshin.core.texts.toneOf
import zanshin.core.tibetan.Ewts
import zanshin.core.tibetan.LordPlace
import zanshin.core.tibetan.LordSeat
import zanshin.core.tibetan.MonthStanding
import zanshin.core.tibetan.TibetanDay
import zanshin.core.tibetan.YearLord
import zanshin.core.tibetan.YearMonthEarthLords
import zanshin.core.tibetan.YearStanding

/** The earth lords' sheet's tabs (ROADMAP T2.26): the year's lords and the month's. */
enum class LordsTab { YEAR, MONTH }

/** One lord as the sheet and the brief show it: its row, its reading and its seats. */
class LordEntry(val annotation: Annotation, val reading: Reading, val seats: List<LordSeat>)

/** The year's lords where they stand on [date], as rows. */
@Composable
fun yearLordEntries(date: LocalDate): List<LordEntry> {
    val tibetan = stringResource(R.string.row_tibetan)
    val labels = LocalLabels.current
    val standing = remember(date) { YearMonthEarthLords.year(date) }
    return remember(standing, labels.locale, tibetan) { standing.map { lordEntry(it, tibetan) } }
}

/** The month's lords of [day]'s month, as rows. */
@Composable
fun monthLordEntries(day: TibetanDay): List<LordEntry> {
    val tibetan = stringResource(R.string.row_tibetan)
    val labels = LocalLabels.current
    val standing = remember(day.monthNames.animal) { YearMonthEarthLords.month(day) }
    return remember(standing, labels.locale, tibetan) { standing.map { lordEntry(it, tibetan) } }
}

private fun lordEntry(s: YearStanding, tibetan: String): LordEntry {
    val r = LordTexts.year(s)
    val title = gloss(s.lord)
    return LordEntry(
        Annotation(title, LordTexts.place(s.seats, "YearLord.${s.lord.name}"), toneOf(r), r, titleIsKanji = false, details = listOf(tibetan to Ewts.named(title, s.lord.wylie))),
        r,
        s.seats,
    )
}

private fun lordEntry(s: MonthStanding, tibetan: String): LordEntry {
    val r = LordTexts.month(s)
    val title = gloss(s.lord)
    return LordEntry(
        Annotation(title, LordTexts.place(s.seats, "MonthLord.${s.lord.name}"), toneOf(r), r, titleIsKanji = false, details = listOf(tibetan to Ewts.named(title, s.lord.wylie))),
        r,
        s.seats,
    )
}

/**
 * The works the year's and the month's lords bar, each with the places it is barred in (ROADMAP T2.26, D4): what the
 * brief shows beside the day's works. A lord with no place on the compass (the black years) bars its works everywhere.
 */
fun barredWorks(lords: List<LordEntry>): Map<Activity, List<String>> {
    val out = linkedMapOf<Activity, MutableList<LordPlace>>()
    for (l in lords) {
        val places = l.seats.map { it.place }.distinct()
        for (w in l.reading.avoidKeys) for (a in Activities.BY_WORDING[w].orEmpty()) {
            val list = out.getOrPut(a) { mutableListOf() }
            places.forEach { if (it !in list) list += it }
        }
    }
    // Round the compass from the north, then the places in the house and the lines; each by its short name.
    fun bearing(p: LordPlace) = when (p) {
        is LordPlace.OnAnimal -> p.animal.ordinal * 30f
        is LordPlace.InDirection -> p.direction.ordinal * 45f
        else -> 1000f
    }
    fun short(p: LordPlace) = (p as? LordPlace.OnAnimal)?.let { Catalog.text("Animal.${it.animal.name}.place") } ?: LordTexts.place(p)
    return out.mapValues { (_, ps) -> ps.sortedBy(::bearing).map(::short).distinct() }
}

/**
 * The earth lords of the year and the month (ROADMAP T2.26, SPEC §10.3): a tab for each, a compass of how many lords
 * hold each place, and every lord as a row opening its reading. Opened from the year's or the month's balloon at its
 * own tab, and from Also today.
 */
@Composable
fun EarthLordsSheet(date: LocalDate, day: TibetanDay, start: LordsTab, onElect: ((Activity) -> Unit)?, onDismiss: () -> Unit) {
    var tab by remember { mutableStateOf(start) }
    var open by remember { mutableStateOf<Annotation?>(null) }
    val year = yearLordEntries(date)
    val month = monthLordEntries(day)
    Sheet(onDismiss) {
        Column(
            Modifier.verticalScroll(rememberScrollState()).padding(bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            Text(stringResource(R.string.earth_lords_title), style = body.copy(fontSize = 22.sp, fontWeight = FontWeight.SemiBold), modifier = Modifier.padding(horizontal = 24.dp))
            SegmentTabs(listOf(stringResource(R.string.earth_lords_tab_year), stringResource(R.string.earth_lords_tab_month)), tab.ordinal) { tab = LordsTab.entries[it] }
            Column(Modifier.padding(horizontal = 24.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                val entries = if (tab == LordsTab.YEAR) year else month
                val head = when (tab) {
                    LordsTab.YEAR -> stringResource(R.string.earth_lords_year_head, Catalog.text("Animal.${YearMonthEarthLords.yearAnimal(YearMonthEarthLords.lordYear(YearLord.TESE, date).first).name}.of"))
                    LordsTab.MONTH -> stringResource(R.string.earth_lords_month_head, Catalog.text("Animal.${day.monthNames.animal.name}.of"))
                }
                Text(withTibetan(head), style = body.copy(fontSize = 16.sp))
                Text(withTibetan(stringResource(R.string.earth_lords_note)), style = body.copy(fontSize = 14.sp, lineHeight = 20.sp, color = Palette.muted))
                val seats = entries.flatMap { it.seats }
                val animals = seats.mapNotNull { (it.place as? LordPlace.OnAnimal)?.animal }.groupingBy { it }.eachCount()
                val directions = seats.mapNotNull { (it.place as? LordPlace.InDirection)?.direction }.groupingBy { it }.eachCount()
                val elsewhere = seats.count { it.place !is LordPlace.OnAnimal && it.place !is LordPlace.InDirection }
                DiagramFrame {
                    LordsCompass(
                        animals, directions, 240.dp,
                        stringResource(R.string.earth_lords_compass_caption, animals.values.sum() + directions.values.sum()).let {
                            if (elsewhere > 0) stringResource(R.string.earth_lords_compass_elsewhere, it, elsewhere) else it
                        },
                    )
                }
                Column { entries.forEach { e -> AnnotationRow(e.annotation) { open = it } } }
            }
        }
    }
    open?.let { ReadingSheet(it, onElect = onElect) { open = null } }
}
