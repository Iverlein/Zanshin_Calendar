/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.iverlein.zanshin.R
import zanshin.app.LocalLabels
import zanshin.app.Person
import zanshin.core.kyureki.Tone
import zanshin.core.texts.Catalog
import zanshin.core.texts.Texts
import zanshin.core.tibetan.AskerPlace
import zanshin.core.tibetan.DaySmeBa
import zanshin.core.tibetan.Ewts
import zanshin.core.tibetan.Forces
import zanshin.core.texts.gloss
import zanshin.core.tibetan.Kinship
import zanshin.core.tibetan.MomentPlaces
import zanshin.core.tibetan.MomentReckoning
import zanshin.core.tibetan.PebbleColour
import zanshin.core.tibetan.SME_BA_COLOURS
import zanshin.core.tibetan.Sign
import zanshin.core.tibetan.SquareFate
import zanshin.core.tibetan.SquareGuard
import zanshin.core.tibetan.TibetanCalendar
import zanshin.core.tibetan.TibetanDay
import zanshin.core.tibetan.YearOfLife
import zanshin.core.time.localDateOfJulianDayNumber

/**
 * The reckoning of the moment (SPEC §5.9.5, ROADMAP R2o, R2z) for [moment], the Tibetan day and the minutes since its
 * dawn: the moment's trigram and mewa, each opening its reading; the mewa's place and the forefathers'; the asker's
 * places for [person], by day; the three mewa joined; and a note on what is reckoned.
 */
@Composable
fun MomentReckoningView(moment: Pair<TibetanDay, Int>, person: Person?, onPeople: () -> Unit, onOpen: (Annotation) -> Unit) {
    val labels = LocalLabels.current
    val (day, minutes) = moment
    val animal = day.monthNames.animal
    val trigram = MomentReckoning.trigram(animal, day.day, minutes)
    val mewa = MomentReckoning.mewa(animal, day.day, minutes)
    val colour = Catalog.text("Colour.${SME_BA_COLOURS[mewa - 1]}")
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text(
            labels.string(R.string.moment_subtitle, day.weekday.english.replaceFirstChar(Char::uppercase), labels.ordinal(day.day), labels.ordinal(day.monthNames.number), "%d:%02d".format(minutes / 60, minutes % 60)),
            style = body.copy(fontSize = 14.sp, color = Palette.muted, lineHeight = 20.sp),
        )
        val period = MomentReckoning.period(minutes)
        if (period == null) {
            Text(stringResource(R.string.moment_period_night), style = body.copy(fontSize = 13.sp, color = Palette.muted, lineHeight = 19.sp))
        } else {
            AnnotationRow(
                Annotation(
                    title = labels.string(R.string.moment_period_title, period.english),
                    english = "",
                    tone = Tone.NEUTRAL,
                    reading = Texts.momentPeriod(period),
                    titleIsKanji = false,
                ),
                onOpen,
            )
            val element = MomentReckoning.hourElement(Forces.signs(day).date.element, period)
            AnnotationRow(
                Annotation(
                    title = labels.string(R.string.moment_element_title, gloss(element, "inText")),
                    english = "",
                    tone = Tone.NEUTRAL,
                    reading = Texts.momentElement(element),
                    titleIsKanji = false,
                ),
                onOpen,
            )
            // The moment's element is the hour's power (D24): the asker's wealth, illness and harming spirit by it.
            AnnotationRow(
                Annotation(
                    title = labels.string(R.string.moment_wealth_title, gloss(element, "inText")),
                    english = "",
                    tone = Tone.NEUTRAL,
                    reading = Texts.momentWealth(element),
                    titleIsKanji = false,
                ),
                onOpen,
            )
        }
        AnnotationRow(
            Annotation(
                title = labels.string(R.string.moment_trigram_title, Ewts.named(trigram.english, trigram.wylie)),
                english = "",
                tone = Tone.NEUTRAL,
                reading = Texts.momentTrigram(trigram),
                titleIsKanji = false,
            ),
            onOpen,
        )
        AnnotationRow(
            Annotation(
                title = labels.string(R.string.moment_mewa_title, mewa, colour),
                english = "",
                tone = Tone.NEUTRAL,
                reading = Texts.momentMewa(mewa),
                titleIsKanji = false,
            ),
            onOpen,
        )
        AnnotationRow(
            Annotation(
                title = labels.string(R.string.moment_spirit_title, mewa, colour),
                english = "",
                tone = Tone.NEUTRAL,
                reading = Texts.momentSpirit(mewa),
                titleIsKanji = false,
            ),
            onOpen,
        )
        MewaPlaces(mewa, onOpen)
        AskerPlaces(day, minutes, person, onPeople, onOpen)
        ThreeMewa(day, onOpen)
        Text(stringResource(R.string.moment_note), style = body.copy(fontSize = 13.sp, color = Palette.muted, lineHeight = 19.sp))
    }
}

/** A row opening [reading], titled [title] with [english] beneath (ROADMAP R2z). */
@Composable
private fun MomentRow(title: String, reading: zanshin.core.texts.Reading, onOpen: (Annotation) -> Unit, english: String = "", tone: Tone = Tone.NEUTRAL, parts: List<Annotation> = emptyList()) {
    AnnotationRow(Annotation(title = title, english = english, tone = tone, reading = reading, titleIsKanji = false, parts = parts), onOpen)
}

/** The moment's mewa as a place and a shape, with its foods, and the mewa falling in the forefathers' place (pp. 162–166). */
@Composable
private fun MewaPlaces(mewa: Int, onOpen: (Annotation) -> Unit) {
    val labels = LocalLabels.current
    fun colour(n: Int) = Catalog.text("Colour.${SME_BA_COLOURS[n - 1]}")
    MomentRow(labels.string(R.string.moment_mewa_place_title, mewa, colour(mewa)), Texts.momentMewaPlace(mewa), onOpen)
    val forefathers = MomentPlaces.forefathers(mewa)
    MomentRow(labels.string(R.string.moment_forefathers_title, forefathers, colour(forefathers)), Texts.momentForefathers(forefathers), onOpen)
}

/**
 * The asker's house, field and grave, fought by the moment's aspects, and the people of one's own place (pp. 166–168;
 * the notes, p. 536): only by day, when the hour has a power, and for a chosen person.
 */
@Composable
private fun AskerPlaces(day: TibetanDay, minutes: Int, person: Person?, onPeople: () -> Unit, onOpen: (Annotation) -> Unit) {
    val labels = LocalLabels.current
    val muted = body.copy(fontSize = 13.sp, color = Palette.muted, lineHeight = 19.sp)
    Section(stringResource(R.string.moment_places_section))
    val moment = MomentPlaces.sign(Forces.signs(day).date.element, minutes)
    if (moment == null) {
        Text(stringResource(R.string.moment_places_night), style = muted)
        return
    }
    fun name(s: Sign) = labels.string(R.string.element_animal, gloss(s.element), gloss(s.animal))
    if (person == null) {
        Text(stringResource(R.string.moment_places_needed), style = muted)
        ScreenLinkRow(stringResource(R.string.menu_people), stringResource(R.string.health_choose_person), onPeople, Icons.Person)
        return
    }
    val born = TibetanCalendar.of(person.birth).let { Sign(it.yearElement, it.yearAnimal) }
    Text(labels.string(R.string.moment_places_subtitle, name(moment), name(born)), style = body.copy(fontSize = 14.sp, color = Palette.muted, lineHeight = 20.sp))
    AskerPlace.entries.forEach { place ->
        val m = MomentPlaces.meet(place, born.forces, moment)
        val english = labels.string(R.string.moment_place_meet, gloss(m.moment, "inText"), gloss(m.asker, "inText"), gloss(m.kinship))
        val reading = Texts.askerPlace(m)
        if (reading == null) {
            Text(labels.string(R.string.moment_place_none, place.english.replaceFirstChar(Char::uppercase), english), style = muted)
        } else {
            MomentRow(place.english.replaceFirstChar(Char::uppercase), reading, onOpen, english = english, tone = placeTone(m.kinship))
        }
        when (place) {
            AskerPlace.FIELD -> {
                val stage = MomentPlaces.stage(born.forces.destiny, moment)
                MomentRow(labels.string(R.string.moment_field_there_title, stage.english), Texts.momentFieldThere(stage.good), onOpen, tone = if (stage.good) Tone.GOOD else Tone.BAD)
            }
            AskerPlace.GRAVE -> if (MomentPlaces.graveDeclining(born.forces, moment)) {
                MomentRow(labels.string(R.string.moment_grave_decline_title, MomentPlaces.stage(born.forces.luck, moment).english), Texts.momentGraveDecline(), onOpen, tone = Tone.BAD)
            }
            AskerPlace.HOUSE -> {}
        }
    }
    val stage = MomentPlaces.kinStage(moment)
    MomentRow(labels.string(R.string.moment_kin_title, stage.english), Texts.momentKin(stage.good), onOpen, tone = if (stage.good) Tone.GOOD else Tone.BAD)
    Text(
        MomentPlaces.ownPlace(moment).joinToString("\n") { (role, direction) -> labels.string(R.string.moment_own_place, role.english.replaceFirstChar(Char::uppercase), direction.english) },
        style = muted,
    )
}

/** The tone of a place's meeting: the mother and the friend cut outward good, the enemy and the son bad, an element meeting itself mixed. */
private fun placeTone(k: Kinship): Tone = when (k) {
    Kinship.MOTHER, Kinship.FRIEND -> Tone.GOOD
    Kinship.ENEMY, Kinship.SON -> Tone.BAD
    Kinship.IDENTITY -> Tone.MIXED
}

/**
 * The three mewa joined (p. 165; the notes, p. 536): the day's mewa in the centre of the nine squares, fought by the
 * year's; a row for each square, its pebble as the notes lay it, its sheet the fate of the square's kin and the
 * guardians its meeting shows.
 */
@Composable
private fun ThreeMewa(day: TibetanDay, onOpen: (Annotation) -> Unit) {
    val labels = LocalLabels.current
    fun colour(n: Int) = Catalog.text("Colour.${SME_BA_COLOURS[n - 1]}")
    val dayMewa = remember(day.jd) { DaySmeBa.of(localDateOfJulianDayNumber(day.jd)).number }
    val yearMewa = YearOfLife.yearSmeBa(day.year)
    Section(stringResource(R.string.moment_squares_section))
    Text(
        labels.string(R.string.moment_squares_subtitle, yearMewa, colour(yearMewa), dayMewa, colour(dayMewa)),
        style = body.copy(fontSize = 14.sp, color = Palette.muted, lineHeight = 20.sp),
    )
    MomentPlaces.squares(dayMewa, yearMewa).forEach { s ->
        val guardTone = when (s.guard) {
            SquareGuard.GODS -> Tone.GOOD
            SquareGuard.DEMONS -> Tone.BAD
            SquareGuard.NEITHER -> Tone.NEUTRAL
        }
        val guard = Annotation(title = stringResource(R.string.moment_square_guard), english = "", tone = guardTone, reading = Texts.momentGuard(s.guard), titleIsKanji = false)
        val fate = s.fate?.let {
            Annotation(title = stringResource(R.string.moment_square_fate), english = "", tone = if (it == SquareFate.CUT) Tone.BAD else Tone.GOOD, reading = Texts.momentSquare(s.square, it), titleIsKanji = false)
        }
        AnnotationRow(
            Annotation(
                title = labels.string(R.string.moment_square_title, s.square.direction.english.replaceFirstChar(Char::uppercase), s.mewa, colour(s.mewa)),
                english = labels.string(R.string.moment_square_meet, gloss(s.kinship), pebble(s.colour)),
                tone = Tone.NEUTRAL,
                reading = null,
                titleIsKanji = false,
                parts = listOfNotNull(fate, guard),
            ),
            onOpen,
        )
    }
}

@Composable
private fun pebble(c: PebbleColour): String = stringResource(
    when (c) {
        PebbleColour.WHITE -> R.string.year_colour_white
        PebbleColour.BLACK -> R.string.year_colour_black
        PebbleColour.SPECKLED -> R.string.year_colour_speckled
    },
)
