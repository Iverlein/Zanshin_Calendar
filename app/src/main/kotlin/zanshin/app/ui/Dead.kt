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
import zanshin.core.texts.Texts
import zanshin.core.texts.gloss
import zanshin.core.tibetan.DeadReckoning
import zanshin.core.tibetan.DeadTime
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
            r.whole?.let { w ->
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
        }
        if (dead.isNotEmpty()) Text(stringResource(R.string.dead_note), style = muted)
    }
}

/**
 * The second examination (SPEC §5.9.4, ROADMAP R2p): the reason the death met, by each aspect's deciding pebble
 * white or black, by the elements' cut met most often among the pebbles, and by the animal of the year, month, day and
 * hour of death, the nearest in time last. The reasons by the trigram and the mewa wait on which of them is meant.
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
 * the trigram and the mewa it is not yet reckoned.
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
    r.mansion?.let { row(labels.string(R.string.dead_demon_mansion, it.english), Texts.deadDemonMansion(it)) }
    r.year.hour?.let { h -> row(labels.string(R.string.dead_time_title, DeadTime.HOUR.english.replaceFirstChar(Char::uppercase), gloss(h.sign.animal).lowercase()), Texts.deadDemonHour(h.sign.animal)) }
}

