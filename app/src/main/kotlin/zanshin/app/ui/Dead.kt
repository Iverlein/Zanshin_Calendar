/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.iverlein.zanshin.R
import zanshin.app.LocalLabels
import zanshin.app.Person
import zanshin.app.Relative
import zanshin.core.kyureki.Tone
import zanshin.core.texts.Catalog
import zanshin.core.texts.Texts
import zanshin.core.texts.gloss
import zanshin.core.tibetan.DeadReckoning
import zanshin.core.tibetan.DeadTime
import zanshin.core.tibetan.Ewts
import zanshin.core.tibetan.SME_BA_COLOURS
import zanshin.core.tibetan.DeadWhole
import zanshin.core.tibetan.Force
import zanshin.core.tibetan.PebbleColour

/**
 * The reckoning for the dead (SPEC §5.9.4, ROADMAP R2n): for each of [person]'s relatives who has died, [dead] with
 * its first examination where it can be reckoned, whether the time of death had come: the whole, then each aspect's
 * deciding pebble with its reading and the roots it was counted from; or what it needs.
 */
@Composable
fun DeadReckoningView(person: Person, dead: List<Pair<Relative, DeadReckoning?>>, onPeople: () -> Unit, onOpen: (Annotation) -> Unit) {
    val labels = LocalLabels.current
    val muted = body.copy(fontSize = 13.sp, color = Palette.muted, lineHeight = 19.sp)
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        if (dead.isEmpty()) {
            Text(stringResource(R.string.dead_none), style = body.copy(fontSize = 15.sp, lineHeight = 21.sp, color = Palette.muted))
            ScreenLinkRow(stringResource(R.string.menu_people), stringResource(R.string.health_choose_person), onPeople, Icons.Person)
        }
        dead.forEach { (relative, r) ->
            val name = listOf(relativeLabel(person.relatives, relative, labels), relative.name).filter { it.isNotBlank() }.joinToString(" · ")
            Section(labels.string(R.string.dead_section, name, relative.birth?.encode() ?: "–", relative.death?.encode()?.replace("T", " ") ?: "–"))
            if (r == null) {
                Text(stringResource(R.string.dead_needed_one), style = muted)
                return@forEach
            }
            DeadTogether(r, onOpen)
            Text(stringResource(R.string.dead_time_head), style = muted)
            Force.entries.forEach { force ->
                val colour = r.deciding(force)
                val places = r.places(force)
                val aspect = gloss(force).replaceFirstChar(Char::uppercase)
                if (colour == PebbleColour.SPECKLED) {
                    Text(labels.string(R.string.dead_speckled, aspect), style = muted)
                } else {
                    AnnotationRow(
                        Annotation(
                            title = stringResource(
                                R.string.birth_share_title, aspect,
                                stringResource(if (colour == PebbleColour.WHITE) R.string.birth_precious_white else R.string.birth_precious_black),
                            ),
                            english = "",
                            tone = Tone.NEUTRAL,
                            reading = Texts.deadPebble(force, colour),
                            subtitle = labels.string(R.string.dead_places, places.white, places.black),
                            titleIsKanji = false,
                            details = r.pebbles.filter { it.force == force }.map { it.root.english.replaceFirstChar(Char::uppercase) to it.pebbles.toString() },
                        ),
                        onOpen,
                    )
                }
            }
            val mewa = r.mewaObstacles
            AnnotationRow(
                Annotation(
                    title = stringResource(R.string.dead_mewa_title),
                    english = "",
                    tone = Tone.NEUTRAL,
                    reading = Texts.deadMewa(mewa.isNotEmpty()),
                    subtitle = mewa.joinToString(", ") { it.english }.ifEmpty { null },
                    titleIsKanji = false,
                ),
                onOpen,
            )
            val course = r.course.map { it.english } + listOfNotNull(r.badStages.takeIf { it.isNotEmpty() }?.let { fs -> labels.string(R.string.dead_course_stage, fs.joinToString(", ") { gloss(it) }) })
            if (course.isNotEmpty()) AnnotationRow(
                Annotation(
                    title = stringResource(R.string.dead_course_title),
                    english = "",
                    tone = Tone.NEUTRAL,
                    reading = Texts.deadCourse(),
                    subtitle = course.joinToString(", "),
                    titleIsKanji = false,
                ),
                onOpen,
            )
            CauseRows(r, onOpen)
            DemonRows(r, onOpen)
            RebirthRows(r, onOpen)
            LaterRows(r, onOpen)
            // The thirty's points read for the dead (ROADMAP R2v): the dead's year and age at death.
            Text(stringResource(R.string.dead_thirty_head), style = muted)
            zanshin.core.tibetan.Thirty.forTheDead(r.year.birth, r.year.age).forEach { ThirtyRow(it, onOpen) }
        }
        if (dead.isNotEmpty()) Text(stringResource(R.string.dead_note), style = muted)
    }
}

/**
 * The dead together (ROADMAP R5; docs/sources/verdicts.md, RD58): the whole of the first examination (pp. 56–57), many
 * black the life spent, many white an untimely death, where three or four deciding pebbles agree; then the Remedy rows
 * of every reading shown below for this dead, the rites and the provisions, gathered into one. The time of death's
 * general verdict over its twenty roots (the notes, p. 503) is not laid: most of the twenty are not reckoned.
 */
@Composable
private fun DeadTogether(r: DeadReckoning, onOpen: (Annotation) -> Unit) {
    val muted = body.copy(fontSize = 13.sp, color = Palette.muted, lineHeight = 19.sp)
    Text(stringResource(R.string.match_section_together), style = muted)
    val w = r.whole
    if (w == null) {
        Text(stringResource(R.string.dead_whole_none), style = muted)
    } else {
        AnnotationRow(
            Annotation(
                title = stringResource(R.string.birth_share_title, stringResource(R.string.dead_whole_title), w.english),
                english = "",
                tone = if (w == DeadWhole.SPENT) Tone.NEUTRAL else Tone.BAD,
                reading = Texts.deadWhole(w),
                titleIsKanji = false,
            ),
            onOpen,
        )
    }
    val remedies = gatheredRemedies(shownReadings(r))
    if (remedies.isNotEmpty()) {
        AnnotationRow(
            Annotation(title = stringResource(R.string.together_remedies), english = "", tone = Tone.NEUTRAL, reading = Texts.deadTogether(remedies), titleIsKanji = false),
            onOpen,
        )
    }
}

/**
 * The readings the rows below show for the dead [r], in their order: the deciding pebbles, the mewa and the course, the
 * second, third, fifth and sixth examinations and the later ones; the thirty's points keep their own.
 */
private fun shownReadings(r: DeadReckoning): List<zanshin.core.texts.Reading> = buildList {
    Force.entries.forEach { f -> r.deciding(f).takeIf { it != PebbleColour.SPECKLED }?.let { add(Texts.deadPebble(f, it)) } }
    add(Texts.deadMewa(r.mewaObstacles.isNotEmpty()))
    if (r.course.isNotEmpty() || r.badStages.isNotEmpty()) add(Texts.deadCourse())
    Force.entries.forEach { f -> r.deciding(f).takeIf { it != PebbleColour.SPECKLED }?.let { add(Texts.deadCause(f, it)) } }
    r.cuts.filter { it.read }.forEach { add(Texts.deadCut(it)) }
    add(Texts.deadCauseTrigram(r.trigram)); add(Texts.deadCauseMewa(r.mewa))
    r.times.forEach { (_, animal) -> add(Texts.deadCauseTime(animal)) }
    add(Texts.deadDemonYear(r.year.birth.animal))
    r.demonDay?.let { add(Texts.deadDemonDay(it)) }
    r.weekday?.let { add(Texts.deadDemonWeekday(it)) }
    r.mansion?.let { add(Texts.deadDemonMansion(it)) }
    add(Texts.deadDemonTrigram(r.trigram)); add(Texts.deadDemonMewa(r.mewa))
    r.year.hour?.let { add(Texts.deadDemonHour(it.sign.animal)) }
    add(Texts.deadRebirthDay(r.date.animal))
    r.year.hour?.let { add(Texts.deadRebirthHour(DeadReckoning.rebirthByHour(it.sign.animal))) }
    add(Texts.deadRebirthMewa(r.mewa)); add(Texts.deadVirtue(r.year.present.animal))
    add(Texts.deadProvisions(r.trigram))
    r.nineEye?.let { add(Texts.deadNineEye(it)) }
    if (r.graveDay) add(Texts.deadGraveDay())
    add(Texts.deadRitesTrigram(r.trigram)); add(Texts.deadRitesMewa(r.mewa))
    r.weekday?.let { add(Texts.deadRitesPlanet(it)) }
    r.lunarDate?.let { add(Texts.deadRitesDate(it)) }
    add(Texts.deadGraveProvisions(r.trigram))
}

/** "Trigram of death · lake (དྭ, dwa)": the dead's descended trigram at death (ROADMAP D18). */
@Composable
private fun trigramTitle(r: DeadReckoning): String =
    stringResource(R.string.birth_share_title, stringResource(R.string.dead_trigram_title), Ewts.named(r.trigram.english, r.trigram.wylie))

/** "Mewa of death · 8 white": the dead's descended mewa at death (ROADMAP D18). */
@Composable
private fun mewaTitle(r: DeadReckoning): String = stringResource(
    R.string.birth_share_title, stringResource(R.string.dead_mewa_of_death),
    "${r.mewa} " + Catalog.text("Colour.${SME_BA_COLOURS[r.mewa - 1]}"),
)

/**
 * The second examination (SPEC §5.9.4, ROADMAP R2p): the reason the death met, by each aspect's deciding pebble
 * white or black, by the elements' cut met most often among the pebbles, and by the animal of the year, month, day and
 * hour of death, the nearest in time last; the inner reason by the trigram and the middle one by the mewa at death (D18).
 */
@Composable
private fun CauseRows(r: DeadReckoning, onOpen: (Annotation) -> Unit) {
    val labels = LocalLabels.current
    Text(stringResource(R.string.dead_cause_head), style = body.copy(fontSize = 13.sp, color = Palette.muted, lineHeight = 19.sp))
    Force.entries.forEach { force ->
        val colour = r.deciding(force).takeIf { it != PebbleColour.SPECKLED } ?: return@forEach
        AnnotationRow(
            Annotation(
                title = stringResource(
                    R.string.birth_share_title, labels.string(R.string.dead_cause_title, gloss(force)),
                    stringResource(if (colour == PebbleColour.WHITE) R.string.birth_precious_white else R.string.birth_precious_black),
                ),
                english = "",
                tone = Tone.NEUTRAL,
                reading = Texts.deadCause(force, colour),
                titleIsKanji = false,
            ),
            onOpen,
        )
    }
    r.cuts.filter { it.read }.forEach { cut ->
        AnnotationRow(
            Annotation(
                title = stringResource(R.string.birth_share_title, stringResource(R.string.dead_cut_title), cut.english),
                english = "",
                tone = Tone.NEUTRAL,
                reading = Texts.deadCut(cut),
                titleIsKanji = false,
            ),
            onOpen,
        )
    }
    for ((title, reading) in listOf(trigramTitle(r) to Texts.deadCauseTrigram(r.trigram), mewaTitle(r) to Texts.deadCauseMewa(r.mewa))) {
        AnnotationRow(Annotation(title = title, english = "", tone = Tone.NEUTRAL, reading = reading, titleIsKanji = false), onOpen)
    }
    r.times.forEach { (time, animal) ->
        AnnotationRow(
            Annotation(
                title = labels.string(R.string.dead_time_title, time.english.replaceFirstChar(Char::uppercase), gloss(animal).lowercase()),
                english = "",
                tone = Tone.NEUTRAL,
                reading = Texts.deadCauseTime(animal),
                subtitle = labels.string(R.string.dead_time_subtitle, gloss(DeadReckoning.cutMonth(animal), "of")),
                titleIsKanji = false,
            ),
            onOpen,
        )
    }
}

/**
 * The third examination (SPEC §5.9.4, ROADMAP R2q): which spirit carried the dead off and its rite, by the dead's year
 * animal, the day's animal where p. 63 names it, the weekday, the mansion and the hour of death where it is known. By
 * the dead's trigram and mewa at death too (D18).
 */
@Composable
private fun DemonRows(r: DeadReckoning, onOpen: (Annotation) -> Unit) {
    val labels = LocalLabels.current
    Text(stringResource(R.string.dead_demon_head), style = body.copy(fontSize = 13.sp, color = Palette.muted, lineHeight = 19.sp))
    @Composable
    fun row(title: String, reading: zanshin.core.texts.Reading) = AnnotationRow(
        Annotation(title = title, english = "", tone = Tone.NEUTRAL, reading = reading, titleIsKanji = false),
        onOpen,
    )
    val born = r.year.birth.animal
    row(labels.string(R.string.dead_demon_year, gloss(born).lowercase()), Texts.deadDemonYear(born))
    r.demonDay?.let { row(labels.string(R.string.dead_time_title, DeadTime.DAY.english.replaceFirstChar(Char::uppercase), gloss(it).lowercase()), Texts.deadDemonDay(it)) }
    r.weekday?.let { row(labels.string(R.string.dead_demon_weekday, gloss(it, "inText")), Texts.deadDemonWeekday(it)) }
    r.mansion?.let { row(labels.string(R.string.dead_demon_mansion, gloss(it, "sanskrit")), Texts.deadDemonMansion(it)) }
    row(trigramTitle(r), Texts.deadDemonTrigram(r.trigram))
    row(mewaTitle(r), Texts.deadDemonMewa(r.mewa))
    r.year.hour?.let { h -> row(labels.string(R.string.dead_time_title, DeadTime.HOUR.english.replaceFirstChar(Char::uppercase), gloss(h.sign.animal).lowercase()), Texts.deadDemonHour(h.sign.animal)) }
}

/**
 * The fifth and sixth examinations (SPEC §5.9.4, ROADMAP R2s): the rebirth by the day's animal of death and, where
 * the hour is known, by the hour; then help by virtue by the year of death, its texts and works a Remedy row.
 */
@Composable
private fun RebirthRows(r: DeadReckoning, onOpen: (Annotation) -> Unit) {
    val labels = LocalLabels.current
    Text(stringResource(R.string.dead_rebirth_head), style = body.copy(fontSize = 13.sp, color = Palette.muted, lineHeight = 19.sp))
    @Composable
    fun row(title: String, reading: zanshin.core.texts.Reading) = AnnotationRow(
        Annotation(title = title, english = "", tone = Tone.NEUTRAL, reading = reading, titleIsKanji = false),
        onOpen,
    )
    row(labels.string(R.string.dead_time_title, DeadTime.DAY.english.replaceFirstChar(Char::uppercase), gloss(r.date.animal).lowercase()), Texts.deadRebirthDay(r.date.animal))
    r.year.hour?.let { h ->
        val realm = DeadReckoning.rebirthByHour(h.sign.animal)
        row(labels.string(R.string.birth_share_title, labels.string(R.string.dead_time_title, DeadTime.HOUR.english.replaceFirstChar(Char::uppercase), gloss(h.sign.animal).lowercase()), realm.english), Texts.deadRebirthHour(realm))
    }
    row(mewaTitle(r), Texts.deadRebirthMewa(r.mewa))
    val year = r.year.present.animal
    row(labels.string(R.string.dead_virtue_title, gloss(year).lowercase()), Texts.deadVirtue(year))
}

/**
 * The later examinations (SPEC §5.9.4, ROADMAP R2x): the ninth's provisions by the trigram of death; the tenth's
 * nine-eye and grave-day strikes where they hold; the thirteenth's rites by the trigram, the mewa, the weekday's
 * planet and the lunar date; the fifteenth's grave provisions by the trigram. Each rite is a Remedy row.
 */
@Composable
private fun LaterRows(r: DeadReckoning, onOpen: (Annotation) -> Unit) {
    val labels = LocalLabels.current
    Text(stringResource(R.string.dead_later_head), style = body.copy(fontSize = 13.sp, color = Palette.muted, lineHeight = 19.sp))
    @Composable
    fun row(title: String, reading: zanshin.core.texts.Reading, tone: Tone = Tone.NEUTRAL) = AnnotationRow(
        Annotation(title = title, english = "", tone = tone, reading = reading, titleIsKanji = false),
        onOpen,
    )
    val trigram = Ewts.named(r.trigram.english, r.trigram.wylie)
    row(labels.string(R.string.dead_provisions_title, trigram), Texts.deadProvisions(r.trigram))
    r.nineEye?.let { row(labels.string(R.string.dead_nine_eye_title, labels.ordinal(it)), Texts.deadNineEye(it), Tone.BAD) }
    if (r.graveDay) row(stringResource(R.string.dead_grave_day_title), Texts.deadGraveDay(), Tone.BAD)
    row(labels.string(R.string.dead_rites_trigram_title, trigram), Texts.deadRitesTrigram(r.trigram))
    row(labels.string(R.string.dead_rites_mewa_title, r.mewa), Texts.deadRitesMewa(r.mewa))
    r.weekday?.let { row(labels.string(R.string.dead_rites_planet_title, gloss(it, "planet")), Texts.deadRitesPlanet(it)) }
    r.lunarDate?.let { row(labels.string(R.string.dead_rites_date_title, labels.ordinal(it)), Texts.deadRitesDate(it)) }
    row(labels.string(R.string.dead_grave_provisions_title, trigram), Texts.deadGraveProvisions(r.trigram))
}

