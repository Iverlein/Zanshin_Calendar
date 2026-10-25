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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import zanshin.app.Reckonings
import zanshin.core.kyureki.Tone
import zanshin.core.texts.Catalog
import zanshin.core.texts.Texts
import zanshin.core.texts.gloss
import zanshin.core.tibetan.BasicSign
import zanshin.core.tibetan.Care
import zanshin.core.tibetan.DecisivePebble
import zanshin.core.tibetan.Element
import zanshin.core.tibetan.Ewts
import zanshin.core.tibetan.Force
import zanshin.core.tibetan.PebbleColour
import zanshin.core.tibetan.Forces
import zanshin.core.tibetan.HarshYear
import zanshin.core.tibetan.HourSign
import zanshin.core.tibetan.Kin
import zanshin.core.tibetan.KinReckoning
import zanshin.core.tibetan.KinRole
import zanshin.core.tibetan.Kinship
import zanshin.core.tibetan.LogMenPlace
import zanshin.core.tibetan.ObstacleGrade
import zanshin.core.tibetan.Pebbles
import zanshin.core.tibetan.SME_BA_COLOURS
import zanshin.core.tibetan.Sign
import zanshin.core.tibetan.TibetanCalendar
import zanshin.core.tibetan.YearOfLife
import zanshin.core.tibetan.gender
import zanshin.core.tibetan.YearReckoning
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

/**
 * The reckoning of the year of age [r] for [person] (SPEC §5.9.1, §10.11): the signs of the year; whose aspect
 * matters most, each aspect's predictive pebble with the stage of its element's energy, and the twenty-four pebbles
 * they are counted from; then the year's obstacles, as rows of the Reckonings screen's year of age. Each row opens
 * its reading through [onOpen].
 */
@Composable
fun YearOfAge(person: Person, r: YearReckoning, onOpen: (Annotation) -> Unit) {
    val labels = LocalLabels.current
    fun name(s: Sign) = labels.string(R.string.element_animal, gloss(s.element), gloss(s.animal))

    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text(
            stringResource(R.string.year_subtitle, personLabel(person), labels.ordinal(r.age), name(r.present), name(r.birth)),
            style = body.copy(fontSize = 14.sp, color = Palette.muted, lineHeight = 20.sp),
        )

        val kin = Reckonings.kin(person)
        YearTogetherSection(r, person, kin, labels, onOpen)
        Section(stringResource(R.string.year_section_signs))
        AnnotationRow(mewaAnnotation(r, labels), onOpen)
        if (r.gender == null) {
            Text(stringResource(R.string.year_gender_needed), style = body.copy(fontSize = 13.sp, color = Palette.muted, lineHeight = 19.sp))
        } else {
            AnnotationRow(trigramAnnotation(r, labels), onOpen)
            AnnotationRow(logMenAnnotation(r, labels), onOpen)
        }
        val mother = kin.firstOrNull { it.role == KinRole.MOTHER && it.birthYear != null }
        val natal = mother?.let { KinReckoning.natalTrigram(it.birthYear!!, r.birthYear) }
        if (natal == null) {
            Text(stringResource(R.string.year_natal_needed), style = body.copy(fontSize = 13.sp, color = Palette.muted, lineHeight = 19.sp))
        } else {
            AnnotationRow(natalTrigramAnnotation(r.birthYear, natal, mother, labels), onOpen)
        }

        // The pebbles as the White Beryl weighs them: whose aspect matters most, then each aspect's predictive pebble,
        // the year's verdict on it, with the stage its element's energy stands in; the six signs' pebbles under them.
        Section(stringResource(R.string.year_section_pebbles))
        val care = Care.of(r.gender, r.age)
        AnnotationRow(careAnnotation(care, labels), onOpen)
        Force.entries.forEach { force ->
            AnnotationRow(predictiveAnnotation(r, force, care, labels), onOpen)
            r.sectors.firstOrNull { it.force == force }?.let { s ->
                AnnotationRow(
                    Annotation(
                        title = labels.string(R.string.year_sector_title, s.force.english.replaceFirstChar(Char::uppercase), s.sector.english),
                        english = "",
                        tone = if (s.sector.good) Tone.GOOD else Tone.BAD,
                        reading = Texts.yearSector(s.sector, s.force),
                        subtitle = labels.string(R.string.year_sector_subtitle, gloss(s.element), gloss(YearOfLife.breathTaking(s.element)), gloss(r.present.animal)),
                        titleIsKanji = false,
                        tibetan = s.sector.wylie to s.sector.english,
                        details = listOf(labels.string(R.string.year_detail_pebbles) to YearOfLife.sectorPebbles(s.sector).toString()),
                    ),
                    onOpen,
                )
            }
        }
        if (r.complete) {
            val all = Force.entries.map { r.predictive(it) }.distinct()
            if (all.size == 1 && all[0] != PebbleColour.SPECKLED) {
                val white = all[0] == PebbleColour.WHITE
                AnnotationRow(
                    Annotation(
                        title = stringResource(if (white) R.string.year_all_white else R.string.year_all_black),
                        english = "",
                        tone = if (white) Tone.MIXED else Tone.BAD,
                        reading = Texts.yearPredictive(null, all[0]!!),
                        titleIsKanji = false,
                    ),
                    onOpen,
                )
            }
        }
        PebbleGrid(r, labels, onOpen)
        r.hour?.let { h ->
            Text(
                stringResource(R.string.year_hour_note, hourName(h, labels), hourSpan(h), name(h.sign)),
                style = body.copy(fontSize = 13.sp, color = Palette.muted, lineHeight = 19.sp),
            )
        }

        // Building a house this year (ROADMAP R3c; WB vol. 2, ch. 28, p. 42): the year and the owner's signs on the doors.
        val building = zanshin.core.tibetan.Building.of(r)
        if (building.isNotEmpty()) {
            Section(stringResource(R.string.year_section_building))
            building.forEach { (sign, door) ->
                AnnotationRow(
                    Annotation(
                        title = labels.string(R.string.birth_share_title, sign.english.replaceFirstChar(Char::uppercase), door.english),
                        english = "", tone = Tone.BAD, reading = Texts.building(sign, door), titleIsKanji = false,
                    ),
                    onOpen,
                )
            }
        }

        Section(stringResource(R.string.year_section_obstacles))
        val obstacles = obstacleAnnotations(r, labels) + kinObstacleAnnotations(r, person, kin, labels)
        if (obstacles.isEmpty()) {
            Text(stringResource(R.string.year_no_obstacles), style = body.copy(fontSize = 14.sp, color = Palette.muted, lineHeight = 20.sp))
        } else {
            obstacles.forEach { AnnotationRow(it, onOpen) }
        }
    }
}

/**
 * The year together (ROADMAP R4, D17; docs/sources/verdicts.md, RD20): the four classes' verdicts gathered, read by
 * the aspect that matters most (WB vol. 1, p. 385), with the remedies of every black or speckled pebble, bad stage
 * and obstacle the notes do not clear gathered; then each obstacle the notes grade by its meeting with the four
 * aspects (vol. 2, pp. 492–494), the cleared ones left out.
 */
@Composable
private fun YearTogetherSection(r: YearReckoning, person: Person, kin: List<Kin>, labels: Labels, onOpen: (Annotation) -> Unit) {
    val muted = body.copy(fontSize = 13.sp, color = Palette.muted, lineHeight = 19.sp)
    Section(stringResource(R.string.match_section_together))
    val chief = Care.of(r.gender, r.age).force
    if (!r.complete || chief == null) {
        Text(stringResource(R.string.year_together_needed), style = muted)
    } else {
        val colours = Force.entries.associateWith { r.predictive(it)!! }
        val bad = buildList {
            (listOf(chief) + Force.entries.filter { it != chief }).forEach { f ->
                colours.getValue(f).takeIf { it != PebbleColour.WHITE }?.let { add(Texts.yearPredictive(f, it)) }
            }
            if (colours.values.all { it == PebbleColour.BLACK }) add(Texts.yearPredictive(null, PebbleColour.BLACK))
            r.sectors.filter { !it.sector.good }.forEach { add(Texts.yearSector(it.sector, it.force)) }
            r.standing.forEach { add(Texts.harsh(it)) }
            if (r.nineMultiple) add(Texts.nineMultiple(r.gender!!, r.age))
            r.smeBaObstacles.forEach { add(Texts.mewaObstacle(it)) }
            KinReckoning.obstacles(r, kin).forEach { add(Texts.kinObstacle(it.obstacle)) }
        }
        val remedies = bad.flatMap { it.rowKeys }.distinct().filter { Catalog.textOrNull("$it.remedy") != null }
        val first = colours.getValue(chief)
        AnnotationRow(
            Annotation(
                title = labels.string(R.string.birth_share_title, labels.string(R.string.year_together_title), colourWord(first, labels)),
                english = "",
                tone = when (first) {
                    PebbleColour.WHITE -> Tone.GOOD
                    PebbleColour.BLACK -> Tone.BAD
                    PebbleColour.SPECKLED -> Tone.MIXED
                },
                reading = Texts.yearTogether(first, remedies),
                subtitle = Force.entries.joinToString(" · ") { labels.string(R.string.year_predictive_title, it.english.replaceFirstChar(Char::uppercase), colourWord(colours.getValue(it), labels)) },
                titleIsKanji = false,
            ),
            onOpen,
        )
    }
    val graded = r.together
    graded.forEach { g ->
        val also = g.met.filter { it != Force.VITALITY }
        AnnotationRow(
            Annotation(
                title = labels.string(R.string.birth_share_title, g.harsh.year.english.replaceFirstChar(Char::uppercase), gloss(g.grade, g.kind.name)),
                english = "",
                tone = if (g.grade == ObstacleGrade.GRAVE) Tone.BAD else Tone.MIXED,
                reading = Texts.yearGrade(g),
                subtitle = when (val v = g.vitality) {
                    null -> labels.string(R.string.year_grade_enemy, g.met.joinToString(", ") { it.english })
                    else -> listOfNotNull(
                        labels.string(R.string.year_grade_vitality, v.english),
                        also.takeIf { it.isNotEmpty() }?.let { fs -> labels.string(R.string.year_grade_also, fs.joinToString(", ") { it.english }) },
                    ).joinToString(" · ")
                },
                titleIsKanji = false,
            ),
            onOpen,
        )
    }
    if (graded.isEmpty() && r.harsh.isNotEmpty() && r.standing.isEmpty()) Text(stringResource(R.string.year_together_clear), style = muted)
}

private fun colourWord(c: PebbleColour, labels: Labels): String = labels.string(
    when (c) {
        PebbleColour.WHITE -> R.string.year_colour_white
        PebbleColour.BLACK -> R.string.year_colour_black
        PebbleColour.SPECKLED -> R.string.year_colour_speckled
    },
)

/** The present two-hour period, from the hare hour at 05:00, of the Tibetan day it falls in: the hour of reckoning. */
internal fun hourOfReckoning(zone: ZoneId): HourSign {
    val now = ZonedDateTime.now(zone)
    val dayStart = if (now.toLocalTime() < LocalTime.of(5, 0)) now.toLocalDate().minusDays(1) else now.toLocalDate()
    val minutes = java.time.Duration.between(dayStart.atTime(5, 0).atZone(zone), now).toMinutes().toInt()
    val signs = Forces.signs(TibetanCalendar.of(dayStart))
    return Forces.hours(signs.date)[(minutes / 120).coerceIn(0, 11)]
}

@Composable
internal fun Section(title: String) {
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
                val t = r.places(it)
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

private fun predictiveAnnotation(r: YearReckoning, force: Force, care: Care, labels: Labels): Annotation {
    val t = r.places(force)
    val side = r.predictive(force)
    val aspect = force.english.replaceFirstChar(Char::uppercase)
    val value = when (side) {
        null -> labels.string(R.string.year_predictive_incomplete)
        PebbleColour.WHITE -> labels.string(R.string.year_predictive_white)
        PebbleColour.BLACK -> labels.string(R.string.year_predictive_black)
        PebbleColour.SPECKLED -> labels.string(R.string.year_predictive_speckled)
    }
    return Annotation(
        title = labels.string(R.string.year_predictive_title, aspect, value),
        english = "",
        tone = when (side) {
            PebbleColour.WHITE -> Tone.GOOD
            PebbleColour.BLACK -> Tone.BAD
            PebbleColour.SPECKLED -> Tone.MIXED
            null -> Tone.NEUTRAL
        },
        reading = side?.let { Texts.yearPredictive(force, it) },
        subtitle = labels.string(R.string.year_places_value, t.white, t.black) +
            if (care.force == force) " · " + labels.string(R.string.year_matters_most) else "",
        titleIsKanji = false,
    )
}

/** Whose aspect matters most for the person (WB vol. 1, p. 242; pp. 415–416), and how the black pebbles are met. */
private fun careAnnotation(care: Care, labels: Labels): Annotation = Annotation(
    title = care.force?.let { labels.string(R.string.year_care_title, it.english) } ?: labels.string(R.string.year_care_general),
    english = "",
    tone = Tone.NEUTRAL,
    reading = Texts.yearCare(care),
    titleIsKanji = false,
)

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
        // WB reads the own year and the seventh good at their mother year, and the progressed sign on a tomb "slightly bad".
        val mixed = (h.kinship == Kinship.MOTHER && h.year in setOf(HarshYear.OWN_YEAR, HarshYear.SEVENTH)) || h.year == HarshYear.LOG_MEN_TOMB
        add(
            Annotation(
                title = h.year.english.replaceFirstChar(Char::uppercase),
                english = "",
                tone = if (mixed) Tone.MIXED else Tone.BAD,
                reading = Texts.harsh(h),
                subtitle = when (val kin = h.kinship) {
                    null -> h.element?.let { e ->
                        labels.string(
                            R.string.year_combined_subtitle,
                            if (e == Element.EARTH) labels.string(R.string.year_earth_and_water) else gloss(e, "inText"),
                            labels.ordinal(h.nth!!),
                        )
                    }
                    else -> labels.string(R.string.year_harsh_subtitle, kin.english)
                },
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

/** The natal trigram [t] of one born in the Tibetan year [birthYear] to [mother] (ROADMAP P2). */
internal fun natalTrigramAnnotation(birthYear: Int, t: zanshin.core.tibetan.Trigram, mother: Kin, labels: Labels): Annotation {
    val born = mother.birth!!
    return Annotation(
        title = labels.string(R.string.year_natal_trigram_title, Ewts.named(t.english, t.wylie)),
        english = "",
        tone = Tone.NEUTRAL,
        reading = Texts.natalTrigram(t),
        subtitle = labels.string(R.string.year_natal_trigram_subtitle, labels.ordinal(YearOfLife.age(mother.birthYear!!, birthYear))),
        titleIsKanji = false,
        details = listOf(
            labels.string(R.string.year_detail_mother_born) to "${mother.birthYear} · " + labels.string(R.string.element_animal, gloss(born.element), gloss(born.animal)),
        ),
    )
}

/** The obstacles that come with a relative (ROADMAP P2), each naming the relative it comes with. */
private fun kinObstacleAnnotations(r: YearReckoning, person: Person, kin: List<Kin>, labels: Labels): List<Annotation> =
    KinReckoning.obstacles(r, kin).map { h ->
        val relative = person.relatives.byId(h.kin.tag)
        val who = relative?.let { listOfNotNull(relativeLabel(person.relatives, it, labels), it.name.takeIf(String::isNotBlank)).joinToString(" · ") }
        Annotation(
            title = h.obstacle.english.replaceFirstChar(Char::uppercase),
            english = "",
            tone = Tone.BAD,
            reading = Texts.kinObstacle(h.obstacle),
            subtitle = listOfNotNull(h.obstacle.wylie?.let { Ewts.named(h.obstacle.english, it) }, who).joinToString(" · ").ifEmpty { null },
            titleIsKanji = false,
        )
    }
