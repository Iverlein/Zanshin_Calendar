/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.iverlein.zanshin.R
import zanshin.app.Labels
import zanshin.app.LocalLabels
import zanshin.app.Person
import zanshin.core.kyureki.Tone
import zanshin.core.texts.Catalog
import zanshin.core.texts.Texts
import zanshin.core.texts.gloss
import zanshin.core.tibetan.BasicSign
import zanshin.core.tibetan.DecisivePebble
import zanshin.core.tibetan.Ewts
import zanshin.core.tibetan.Force
import zanshin.core.tibetan.Forces
import zanshin.core.tibetan.HarshYear
import zanshin.core.tibetan.HourSign
import zanshin.core.tibetan.Kinship
import zanshin.core.tibetan.LogMenPlace
import zanshin.core.tibetan.Pebbles
import zanshin.core.tibetan.SME_BA_COLOURS
import zanshin.core.tibetan.Sign
import zanshin.core.tibetan.TibetanCalendar
import zanshin.core.tibetan.TibetanDay
import zanshin.core.tibetan.YearOfLife
import zanshin.core.tibetan.gender
import zanshin.core.tibetan.YearReckoning
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

/**
 * The reckoning of the year of age for [person] in the Tibetan year of [day]
 * (SPEC §5.9, §10.3): the signs of the year, the twenty-four pebbles with the
 * predictive pebble of each aspect, the sectors of growth and decline and the
 * year's obstacles. The hour of reckoning is the present two-hour period at
 * [zone]. Each row opens its reading through [onOpen].
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YearSheet(person: Person, day: TibetanDay, zone: ZoneId, onOpen: (Annotation) -> Unit, onDismiss: () -> Unit) {
    val labels = LocalLabels.current
    val r = remember(person, day.year) {
        val born = TibetanCalendar.of(person.birth)
        YearReckoning(Sign(born.yearElement, born.yearAnimal), born.year, Sign(day.yearElement, day.yearAnimal), day.year, person.gender, hourOfReckoning(zone))
    }
    fun name(s: Sign) = labels.string(R.string.element_animal, gloss(s.element), gloss(s.animal))

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        containerColor = Palette.surface,
        scrimColor = Palette.scrim,
    ) {
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .padding(start = 24.dp, end = 24.dp, bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            Text(stringResource(R.string.year_title), style = body.copy(fontSize = 22.sp, fontWeight = FontWeight.SemiBold))
            Text(
                stringResource(R.string.year_subtitle, personLabel(person), labels.ordinal(r.age), name(r.present), name(r.birth)),
                style = body.copy(fontSize = 14.sp, color = Palette.muted, lineHeight = 20.sp),
            )

            Section(stringResource(R.string.year_section_signs))
            AnnotationRow(mewaAnnotation(r, labels), onOpen)
            if (r.gender == null) {
                Text(stringResource(R.string.year_gender_needed), style = body.copy(fontSize = 13.sp, color = Palette.muted, lineHeight = 19.sp))
            } else {
                AnnotationRow(trigramAnnotation(r, labels), onOpen)
                AnnotationRow(logMenAnnotation(r, labels), onOpen)
            }

            Section(stringResource(R.string.year_section_pebbles))
            PebbleGrid(r, labels, onOpen)
            Force.entries.forEach { AnnotationRow(predictiveAnnotation(r, it, labels), onOpen) }
            if (r.complete) {
                val all = Force.entries.map { r.predictive(it) }.distinct()
                if (all.size == 1 && all[0] != null) {
                    val white = all[0]!!
                    AnnotationRow(
                        Annotation(
                            title = stringResource(if (white) R.string.year_all_white else R.string.year_all_black),
                            english = "",
                            tone = if (white) Tone.MIXED else Tone.BAD,
                            reading = Texts.yearPredictive(null, white),
                            titleIsKanji = false,
                        ),
                        onOpen,
                    )
                }
            }
            r.hour?.let { h ->
                Text(
                    stringResource(R.string.year_hour_note, hourName(h, labels), hourSpan(h), name(h.sign)),
                    style = body.copy(fontSize = 13.sp, color = Palette.muted, lineHeight = 19.sp),
                )
            }

            Section(stringResource(R.string.year_section_sectors))
            r.sectors.forEach { s ->
                AnnotationRow(
                    Annotation(
                        title = labels.string(R.string.year_sector_title, s.force.english.replaceFirstChar(Char::uppercase), s.sector.english),
                        english = "",
                        tone = if (s.sector.good) Tone.GOOD else Tone.BAD,
                        reading = Texts.yearSector(s.sector),
                        subtitle = labels.string(R.string.year_sector_subtitle, gloss(s.element), gloss(YearOfLife.breathTaking(s.element)), gloss(r.present.animal)),
                        titleIsKanji = false,
                        tibetan = s.sector.wylie to s.sector.english,
                        details = listOf(labels.string(R.string.year_detail_pebbles) to YearOfLife.sectorPebbles(s.sector).toString()),
                    ),
                    onOpen,
                )
            }

            Section(stringResource(R.string.year_section_obstacles))
            val obstacles = obstacleAnnotations(r, labels)
            if (obstacles.isEmpty()) {
                Text(stringResource(R.string.year_no_obstacles), style = body.copy(fontSize = 14.sp, color = Palette.muted, lineHeight = 20.sp))
            } else {
                obstacles.forEach { AnnotationRow(it, onOpen) }
            }
        }
    }
}

/** The present two-hour period, from the hare hour at 05:00, of the Tibetan day it falls in. */
private fun hourOfReckoning(zone: ZoneId): HourSign {
    val now = ZonedDateTime.now(zone)
    val dayStart = if (now.toLocalTime() < LocalTime.of(5, 0)) now.toLocalDate().minusDays(1) else now.toLocalDate()
    val minutes = java.time.Duration.between(dayStart.atTime(5, 0).atZone(zone), now).toMinutes().toInt()
    val signs = Forces.signs(TibetanCalendar.of(dayStart))
    return Forces.hours(signs.date)[(minutes / 120).coerceIn(0, 11)]
}

@Composable
private fun Section(title: String) {
    Text(title.uppercase(), style = body.copy(fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Palette.muted, letterSpacing = 1.sp), modifier = Modifier.padding(top = 8.dp))
}

/**
 * The twenty-four pebbles: a row for each basic sign, a column for each
 * aspect, as chart 6.2 of Gyurme Dorje's edition lays them out; the last
 * row counts each aspect's white and black. Each cell opens its reading.
 */
@Composable
private fun PebbleGrid(r: YearReckoning, labels: Labels, onOpen: (Annotation) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        Row(Modifier.fillMaxWidth()) {
            Box(Modifier.weight(1.3f))
            Force.entries.forEach {
                Text(it.english, style = body.copy(fontSize = 11.sp, color = Palette.muted, lineHeight = 14.sp, hyphens = Hyphens.Auto), textAlign = TextAlign.Center, modifier = Modifier.weight(1f))
            }
        }
        BasicSign.entries.forEach { basic ->
            val cells = r.pebbles.filter { it.basic == basic }
            Row(Modifier.fillMaxWidth().heightIn(min = 44.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(basicLabel(r, basic, labels), style = body.copy(fontSize = 12.sp, lineHeight = 16.sp, hyphens = Hyphens.Auto), modifier = Modifier.weight(1.3f))
                Force.entries.forEach { force ->
                    val p = cells.firstOrNull { it.force == force }
                    Box(
                        Modifier
                            .weight(1f)
                            .heightIn(min = 44.dp)
                            .let { m -> if (p == null) m else m.clickable(role = Role.Button) { onOpen(cellAnnotation(r, p, labels)) } }
                            .semantics { contentDescription = p?.let { spokenPebbles(it.pebbles, labels) } ?: "" },
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(p?.pebbles?.toString() ?: "–", style = body.copy(fontSize = 16.sp, color = p?.let { toneColour(pebbleTone(it.pebbles)) } ?: Palette.faint))
                    }
                }
            }
        }
        Row(Modifier.fillMaxWidth().padding(top = 4.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(stringResource(R.string.year_tally), style = body.copy(fontSize = 12.sp, color = Palette.muted), modifier = Modifier.weight(1.3f))
            Force.entries.forEach {
                val t = r.tally(it)
                Text(stringResource(R.string.year_tally_value, t.white, t.black), style = body.copy(fontSize = 13.sp, color = Palette.muted), textAlign = TextAlign.Center, modifier = Modifier.weight(1f))
            }
        }
    }
}

private fun toneColour(t: Tone) = when (t) {
    Tone.GOOD -> Palette.good
    Tone.BAD -> Palette.bad
    else -> Palette.text
}

private fun spokenPebbles(p: Pebbles, labels: Labels): String = when {
    p.black == 0 -> labels.plural(R.plurals.pebbles_white, p.white)
    p.white == 0 -> labels.plural(R.plurals.pebbles_black, p.black)
    else -> labels.string(R.string.pebbles_mixed, p.white, p.black)
}

/** A basic sign as its row names it: "Present year · Fire Horse". */
private fun basicLabel(r: YearReckoning, basic: BasicSign, labels: Labels): String {
    fun name(s: Sign) = labels.string(R.string.element_animal, gloss(s.element), gloss(s.animal))
    val value = when (basic) {
        BasicSign.PRESENT_YEAR -> name(r.present)
        BasicSign.LOG_MEN -> r.logMen?.let(::name)
        BasicSign.TRIGRAM -> r.trigram?.english
        BasicSign.SME_BA -> "${r.currentSmeBa}"
        BasicSign.SECTOR -> null
        BasicSign.HOUR -> r.hour?.let { name(it.sign) }
    }
    val label = basic.english.replaceFirstChar(Char::uppercase)
    return if (value == null) label else "$label · $value"
}

private fun cellAnnotation(r: YearReckoning, p: DecisivePebble, labels: Labels): Annotation {
    val aspect = p.force.english.replaceFirstChar(Char::uppercase)
    val details = buildList {
        add(labels.string(R.string.detail_yours) to labels.string(R.string.year_detail_yours, gloss(p.own), p.force.english))
        val other = p.other
        if (other != null) {
            add(p.basic.english.replaceFirstChar(Char::uppercase) to gloss(other))
            add(labels.string(R.string.detail_relation) to "${p.kinship!!.english} · ${p.pebbles}")
        } else {
            add(p.basic.english.replaceFirstChar(Char::uppercase) to "${p.sector!!.english} · ${p.pebbles}")
        }
    }
    return Annotation(
        title = labels.string(R.string.pebble_row_title, aspect, p.pebbles.toString()),
        english = p.kinship?.english ?: p.sector!!.english,
        tone = pebbleTone(p.pebbles),
        reading = Texts.yearPebble(p.force, p.pebbles),
        subtitle = labels.string(R.string.year_cell_subtitle, p.basic.english, p.force.english),
        titleIsKanji = false,
        spokenTitle = "$aspect, ${spokenPebbles(p.pebbles, labels)}",
        details = details,
    )
}

private fun predictiveAnnotation(r: YearReckoning, force: Force, labels: Labels): Annotation {
    val t = r.tally(force)
    val side = r.predictive(force)
    val aspect = force.english.replaceFirstChar(Char::uppercase)
    val value = when {
        !r.complete -> labels.string(R.string.year_predictive_incomplete)
        side == null -> labels.string(R.string.year_predictive_even)
        side -> labels.string(R.string.year_predictive_white)
        else -> labels.string(R.string.year_predictive_black)
    }
    return Annotation(
        title = labels.string(R.string.year_predictive_title, aspect, value),
        english = "",
        tone = when (side) {
            true -> Tone.GOOD
            false -> Tone.BAD
            null -> Tone.NEUTRAL
        },
        reading = side?.let { Texts.yearPredictive(force, it) },
        subtitle = labels.string(R.string.year_tally_value, t.white, t.black),
        titleIsKanji = false,
    )
}

private fun mewaAnnotation(r: YearReckoning, labels: Labels): Annotation = Annotation(
    title = labels.string(R.string.year_mewa_title, r.currentSmeBa, Catalog.text("Colour.${SME_BA_COLOURS[r.currentSmeBa - 1]}")),
    english = "",
    tone = Tone.NEUTRAL,
    reading = Texts.yearMewa(r.natalSmeBa, r.currentSmeBa),
    subtitle = labels.string(R.string.year_mewa_subtitle, r.natalSmeBa, Catalog.text("Colour.${SME_BA_COLOURS[r.natalSmeBa - 1]}")),
    titleIsKanji = false,
    details = listOf(
        labels.string(R.string.year_detail_natal_mewa) to "${r.natalSmeBa}",
        labels.string(R.string.year_detail_year_mewa) to "${r.yearSmeBa}",
        labels.string(R.string.year_detail_birth_year) to labels.string(R.string.element_animal, gloss(r.birth.element), gloss(r.birth.animal)) + " · " + gloss(r.birth.animal.gender, "inText"),
        labels.string(R.string.year_detail_pebbles) to "${gloss(zanshin.core.tibetan.smeBaElement(r.currentSmeBa))} · " +
            Force.entries.joinToString(" ") { f -> r.pebbles.first { it.basic == BasicSign.SME_BA && it.force == f }.pebbles.toString() },
    ),
)

private fun trigramAnnotation(r: YearReckoning, labels: Labels): Annotation {
    val t = r.trigram!!
    return Annotation(
        title = labels.string(R.string.year_trigram_title, Ewts.named(t.english, t.wylie)),
        english = "",
        tone = Tone.NEUTRAL,
        reading = Texts.yearTrigram(t),
        subtitle = labels.string(R.string.year_trigram_subtitle, gloss(YearOfLife.trigramElement(t), "inText")),
        titleIsKanji = false,
    )
}

private fun logMenAnnotation(r: YearReckoning, labels: Labels): Annotation {
    val s = r.logMen!!
    val place = r.logMenPlace
    return Annotation(
        title = labels.string(R.string.year_log_men_title, labels.string(R.string.element_animal, gloss(s.element), gloss(s.animal))),
        english = "",
        tone = when (place) {
            null -> Tone.NEUTRAL
            LogMenPlace.GAIN -> Tone.GOOD
            else -> Tone.BAD
        },
        reading = Texts.logMen(place),
        subtitle = place?.let { Ewts.named(it.english, it.wylie) },
        titleIsKanji = false,
    )
}

private fun obstacleAnnotations(r: YearReckoning, labels: Labels): List<Annotation> = buildList {
    r.harsh.forEach { h ->
        val good = h.kinship == Kinship.MOTHER && h.year in setOf(HarshYear.OWN_YEAR, HarshYear.SEVENTH)
        add(
            Annotation(
                title = h.year.english.replaceFirstChar(Char::uppercase),
                english = "",
                tone = if (good) Tone.MIXED else Tone.BAD,
                reading = Texts.harsh(h),
                subtitle = h.kinship?.let { labels.string(R.string.year_harsh_subtitle, it.english) },
                titleIsKanji = false,
            ),
        )
    }
    if (r.nineMultiple) {
        add(Annotation(labels.string(R.string.year_nine_title, labels.ordinal(r.age)), "", Tone.BAD, Texts.nineMultiple(r.gender!!, r.age), titleIsKanji = false))
    }
    r.smeBaObstacles.forEach { o ->
        add(Annotation(o.english.replaceFirstChar(Char::uppercase), "", Tone.BAD, Texts.mewaObstacle(o), subtitle = Ewts.named(o.english, o.wylie), titleIsKanji = false))
    }
}
