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
import zanshin.core.tibetan.BeerGiven
import zanshin.core.tibetan.EatenCount
import zanshin.core.tibetan.Element
import zanshin.core.tibetan.ObservedSigns
import zanshin.core.tibetan.GrainMeasure
import zanshin.core.tibetan.KinGroup
import zanshin.core.tibetan.LifeVerdict
import zanshin.core.tibetan.MomentKin
import zanshin.app.Relation
import zanshin.app.Relative
import zanshin.core.tibetan.MomentSigns
import zanshin.core.tibetan.SickLife
import zanshin.core.tibetan.Gender
import zanshin.core.tibetan.Kinship
import zanshin.core.tibetan.MomentGates
import zanshin.core.tibetan.gender
import zanshin.core.tibetan.kinPlaces
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
        MomentTogether(day, minutes, person, trigram, onOpen)
        Section(stringResource(R.string.moment_time_section))
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
        person?.let { LifeParts(it, mewa, onOpen) }
        AskerPlaces(day, minutes, person, onPeople, onOpen)
        ThreeMewa(day, onOpen)
        ObservedSignsSection(onOpen)
        Text(stringResource(R.string.moment_note), style = body.copy(fontSize = 13.sp, color = Palette.muted, lineHeight = 19.sp))
    }
}

/**
 * The moment together (ROADMAP R5; docs/sources/verdicts.md, RD58): with the person chosen and a moment by day, the
 * fighting enemies' verdict (vol. 2, p. 172), which closes the chapter, and the wealth's, the one place whose verdict
 * WB lays (p. 168: the body and the power both rising on the moment and the moment cutting neither), as the sections
 * below reckon them; then the Remedy rows of the time of day, the hour's element and the trigram gathered into one.
 * Ch. 30 turns no verdict into a rite, and none is added.
 */
@Composable
private fun MomentTogether(day: TibetanDay, minutes: Int, person: Person?, trigram: zanshin.core.tibetan.Trigram, onOpen: (Annotation) -> Unit) {
    val muted = body.copy(fontSize = 13.sp, color = Palette.muted, lineHeight = 19.sp)
    Section(stringResource(R.string.match_section_together))
    val moment = MomentPlaces.sign(Forces.signs(day).date.element, minutes)
    when {
        person == null -> Text(stringResource(R.string.moment_together_needed), style = muted)
        moment == null -> Text(stringResource(R.string.moment_together_night), style = muted)
        else -> {
            val born = TibetanCalendar.of(person.birth).let { Sign(it.yearElement, it.yearAnimal) }
            FightRow(born, TibetanCalendar.of(person.birth).year, day.year, moment, onOpen)
            WealthRow(born.forces, moment, onOpen)
        }
    }
    val period = MomentReckoning.period(minutes)
    val readings = listOfNotNull(
        period?.let { Texts.momentPeriod(it) },
        period?.let { Texts.momentElement(MomentReckoning.hourElement(Forces.signs(day).date.element, it)) },
        Texts.momentTrigram(trigram),
    )
    val remedies = gatheredRemedies(readings)
    if (remedies.isNotEmpty()) MomentRow(stringResource(R.string.together_remedies), Texts.momentTogether(remedies), onOpen)
}

/** A row opening [reading], titled [title] with [english] beneath (ROADMAP R2z). */
@Composable
private fun MomentRow(title: String, reading: zanshin.core.texts.Reading, onOpen: (Annotation) -> Unit, english: String = "", tone: Tone = Tone.NEUTRAL, parts: List<Annotation> = emptyList()) {
    AnnotationRow(Annotation(title = title, english = english, tone = tone, reading = reading, titleIsKanji = false, parts = parts), onOpen)
}

/** The moment's mewa as a place and a shape, with its foods, and the mewa falling in each kin's place on the figure (pp. 162–166; the notes, p. 535). */
@Composable
private fun MewaPlaces(mewa: Int, onOpen: (Annotation) -> Unit) {
    val labels = LocalLabels.current
    fun colour(n: Int) = Catalog.text("Colour.${SME_BA_COLOURS[n - 1]}")
    MomentRow(labels.string(R.string.moment_mewa_place_title, mewa, colour(mewa)), Texts.momentMewaPlace(mewa), onOpen)
    Section(stringResource(R.string.moment_kin_section))
    MomentPlaces.kinPlaces(mewa).forEach { (place, n) ->
        MomentRow(labels.string(R.string.moment_kin_place_title, place.english.replaceFirstChar(Char::uppercase), n, colour(n)), Texts.momentForefathers(n), onOpen, english = place.direction.english)
    }
}

/**
 * The cutting on the figure (p. 163; the notes, pp. 535–536): for the person chosen and each relative entered with a
 * birth year whose group the figure reads, the father and paternal grandfather, the father's brothers, the mother and
 * maternal grandmother, the mother's brothers and the children, their life force against the mewa in each of their
 * three places, the moment's mewa [mewa] in the centre.
 */
@Composable
private fun LifeParts(person: Person, mewa: Int, onOpen: (Annotation) -> Unit) {
    val labels = LocalLabels.current
    fun colour(n: Int) = Catalog.text("Colour.${SME_BA_COLOURS[n - 1]}")
    val tree = person.relatives
    val all = tree.list
    val father = all.firstOrNull { it.of == null && it.relation == Relation.FATHER }
    val mother = all.firstOrNull { it.of == null && it.relation == Relation.MOTHER }
    val kin: List<Pair<KinGroup, Relative>> = listOfNotNull(father?.let { KinGroup.FATHERS to it }) +
        all.filter { father != null && it.of == father.id && it.relation == Relation.FATHER }.map { KinGroup.FATHERS to it } +
        all.filter { father != null && it.of == father.id && it.relation == Relation.SIBLING && it.gender == Gender.MALE }.map { KinGroup.FATHERS_BROTHERS to it } +
        listOfNotNull(mother?.let { KinGroup.MOTHERS to it }) +
        all.filter { mother != null && it.of == mother.id && it.relation == Relation.MOTHER }.map { KinGroup.MOTHERS to it } +
        all.filter { mother != null && it.of == mother.id && it.relation == Relation.SIBLING && it.gender == Gender.MALE }.map { KinGroup.MATERNAL_UNCLES to it } +
        all.filter { it.of == null && it.relation == Relation.CHILD }.map { KinGroup.CHILDREN to it }
    val partTitles = listOf(R.string.moment_life_early, R.string.moment_life_middle, R.string.moment_life_late)
    Section(stringResource(R.string.moment_life_section))
    val own = TibetanCalendar.of(person.birth).let { Sign(it.yearElement, it.yearAnimal) }
    val rows = listOf(Triple(KinGroup.ONESELF, personLabel(person), own)) +
        kin.mapNotNull { (g, r) -> r.birth?.let { b -> Triple(g, labels.string(R.string.moment_life_kin, relativeLabel(tree, r, labels), r.name), YearOfLife.yearSign(b.tibetanYear)) } }
    rows.forEach { (group, title, sign) ->
        val parts = MomentKin.lifeParts(group, sign.forces.vitality, mewa)
        AnnotationRow(
            Annotation(
                title = title.trimEnd(' ', '·'),
                english = parts.joinToString(" · ") { "${it.mewa} ${colour(it.mewa)}" },
                tone = Tone.NEUTRAL, reading = null, titleIsKanji = false,
                parts = parts.map { p ->
                    Annotation(
                        title = labels.string(partTitles[p.part.ordinal], p.mewa, colour(p.mewa)),
                        english = "", titleIsKanji = false,
                        tone = when (p.verdict) { LifeVerdict.GOOD -> Tone.GOOD; LifeVerdict.RISE_FALL -> Tone.MIXED; LifeVerdict.BAD -> Tone.BAD },
                        reading = Texts.momentLifePart(p.part, p.verdict),
                    )
                },
            ),
            onOpen,
        )
    }
    if (kin.none { it.second.birth != null }) {
        Text(stringResource(R.string.moment_life_needed), style = body.copy(fontSize = 13.sp, color = Palette.muted, lineHeight = 19.sp))
    }
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
            AskerPlace.GRAVE -> {
                if (MomentPlaces.graveDeclining(born.forces, moment)) {
                    MomentRow(labels.string(R.string.moment_grave_decline_title, MomentPlaces.stage(born.forces.luck, moment).english), Texts.momentGraveDecline(), onOpen, tone = Tone.BAD)
                }
                // The forefathers' year (p. 168): the paternal grandfather's, else the father's, as entered.
                val forefather = person.relatives.list.let { all ->
                    val father = all.firstOrNull { it.of == null && it.relation == Relation.FATHER }
                    all.firstOrNull { father != null && it.of == father.id && it.relation == Relation.FATHER && it.birth != null } ?: father?.takeIf { it.birth != null }
                }
                forefather?.birth?.let { YearOfLife.yearSign(it.tibetanYear) }?.let { y ->
                    MomentKin.forefathersYear(y, moment)?.let { fault ->
                        MomentRow(labels.string(R.string.moment_forefathers_year_title, name(y)), Texts.momentForefathersYear(fault), onOpen, tone = if (fault) Tone.BAD else Tone.GOOD)
                    }
                }
                // WB gives the grave's back for the four elements of the directions; earth has none.
                if (moment.element != Element.EARTH) {
                    MomentRow(labels.string(R.string.moment_grave_back_title, MomentPlaces.direction(moment.element).english), Texts.momentGraveBack(), onOpen)
                }
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
    SmallExaminations(born, moment, onOpen)
    Gates(born, TibetanCalendar.of(person.birth).year, day.year, moment, onOpen)
}

/**
 * The moment's small examinations of the asker (pp. 168–170; the notes, p. 536): wealth, the demon, beer, the
 * neighbouring houses, the asker as the sick one, what was eaten, where the illness is, hair, trade and disputes.
 */
@Composable
private fun SmallExaminations(born: Sign, moment: Sign, onOpen: (Annotation) -> Unit) {
    val labels = LocalLabels.current
    val muted = body.copy(fontSize = 13.sp, color = Palette.muted, lineHeight = 19.sp)
    val a = born.forces
    fun tone(good: Boolean) = if (good) Tone.GOOD else Tone.BAD
    fun up(e: Element) = MomentPlaces.stage(e, moment)
    Section(stringResource(R.string.moment_small_section))
    WealthRow(a, moment, onOpen)
    val demon = MomentSigns.demonMeet(a, moment)
    MomentRow(labels.string(R.string.moment_demon_title, gloss(demon)), Texts.momentDemonMeet(demon), onOpen,
        english = labels.string(R.string.moment_place_meet, gloss(moment.element, "inText"), gloss(a.body, "inText"), gloss(demon)), tone = placeTone(demon))
    val beer = MomentSigns.beerThere(a, moment)
    val given = MomentSigns.beerGiven(a, moment)
    val drunk = MomentSigns.beerDrunk(born, moment)
    fun part(title: String, t: Tone, r: zanshin.core.texts.Reading) = Annotation(title = title, english = "", tone = t, reading = r, titleIsKanji = false)
    AnnotationRow(
        Annotation(
            title = stringResource(R.string.moment_beer_title),
            english = labels.string(R.string.moment_body_power, up(a.body).english, up(a.destiny).english),
            tone = Tone.NEUTRAL, reading = null, titleIsKanji = false,
            parts = listOf(
                part(stringResource(if (beer) R.string.moment_beer_there else R.string.moment_beer_none), tone(beer), Texts.momentBeer(beer)),
                part(stringResource(R.string.moment_beer_given), when (given) { BeerGiven.GIVEN, BeerGiven.OWN_SIDE -> Tone.GOOD; BeerGiven.HALF -> Tone.MIXED; BeerGiven.NOT_GIVEN -> Tone.BAD }, Texts.momentBeerGiven(given)),
                part(stringResource(R.string.moment_beer_drunk), Tone.NEUTRAL, Texts.momentBeerDrunk(drunk)),
                part(stringResource(R.string.moment_beer_taste), Tone.NEUTRAL, Texts.momentBeerTaste(moment.element)),
            ),
        ),
        onOpen,
    )
    MomentRow(stringResource(R.string.moment_houses_title), Texts.momentHouses(), onOpen)
    Text(
        MomentSigns.houses(moment).joinToString("\n") { (side, e) -> labels.string(R.string.moment_own_place, side.english.replaceFirstChar(Char::uppercase), Catalog.text("Element.${e.name}.house")) },
        style = muted,
    )
    Section(stringResource(R.string.moment_sick_section))
    val life = MomentSigns.sickLife(a, moment)
    MomentRow(labels.string(R.string.moment_sick_life_title, up(a.vitality).english), Texts.momentSickLife(life), onOpen,
        tone = when (life) { SickLife.LIVES -> Tone.GOOD; SickLife.HARD -> Tone.MIXED; SickLife.DECLINE -> Tone.BAD })
    MomentRow(stringResource(R.string.moment_sick_cause_title), Texts.momentSickCause(moment.element), onOpen, english = gloss(moment.element, "inText"))
    MomentRow(stringResource(R.string.moment_sick_bed_title), Texts.momentSickBed(), onOpen)
    val bed = mapOf(Kinship.MOTHER to R.string.moment_bed_clothing, Kinship.SON to R.string.moment_bed_pillow, Kinship.ENEMY to R.string.moment_bed_mat, Kinship.FRIEND to R.string.moment_bed_food)
    Text(
        MomentSigns.sickBed(moment).joinToString("\n") { (k, e) -> labels.string(R.string.moment_own_place, labels.string(bed.getValue(k)), Catalog.text("Element.${e.name}.colour")) },
        style = muted,
    )
    MomentRow(stringResource(R.string.moment_eaten_title), Texts.momentEaten(moment.element), onOpen, english = gloss(moment.element, "inText"))
    MomentSigns.eaten(born, moment)?.let { c ->
        MomentRow(stringResource(when (c) { EatenCount.TWICE -> R.string.moment_eaten_twice; EatenCount.ONCE -> R.string.moment_eaten_once; EatenCount.NONE -> R.string.moment_eaten_none }), Texts.momentEatenCount(c), onOpen)
    }
    val seat = MomentSigns.illnessSeat(moment)
    MomentRow(
        labels.string(R.string.moment_seat_title, if (seat.isEmpty()) stringResource(R.string.moment_seat_none) else seat.joinToString { it.english }),
        Texts.momentIllnessSeat(), onOpen,
    )
    Section(stringResource(R.string.moment_more_section))
    Texts.momentHair(moment.element)?.let { MomentRow(stringResource(R.string.moment_hair_title), it, onOpen, english = gloss(moment.element, "inText")) }
    Texts.momentMarks(moment.element)?.let { MomentRow(stringResource(R.string.moment_marks_title), it, onOpen, english = gloss(moment.element, "inText")) }
    val grain = MomentKin.grain(moment)
    MomentRow(labels.string(R.string.moment_grain_title, MomentPlaces.stage(moment.forces.vitality, moment).english), Texts.momentGrain(grain), onOpen,
        tone = when (grain) { GrainMeasure.FULL -> Tone.GOOD; GrainMeasure.MIDDLING -> Tone.MIXED; GrainMeasure.LITTLE -> Tone.BAD })
    MomentRow(labels.string(R.string.moment_grain_from_title, MomentKin.grainFrom(moment).joinToString(" · ") { it.english }), Texts.momentGrainVessel(), onOpen,
        english = Catalog.text("Element.${moment.element.name}.colour"))
    val trade = MomentSigns.trade(a, moment)
    MomentRow(labels.string(R.string.moment_trade_title, up(a.destiny).english), Texts.momentTrade(trade), onOpen, tone = tone(trade))
    val dispute = MomentSigns.dispute(a, moment)
    MomentRow(labels.string(R.string.moment_dispute_title, up(a.luck).english), Texts.momentDispute(dispute), onOpen, tone = tone(dispute))
}

/**
 * The thirteen gates and the counts after them (pp. 170–172; the notes, p. 536): the asker's years counted from each
 * gate's year, a man down and a woman up by the birth year's sex, as the thirty count (D22); a row for each gate, its
 * sheet each year fought against the moment; then the counts after the gates that fall.
 */
@Composable
private fun Gates(born: Sign, birthYear: Int, year: Int, moment: Sign, onOpen: (Annotation) -> Unit) {
    val labels = LocalLabels.current
    fun name(s: Sign) = labels.string(R.string.element_animal, gloss(s.element), gloss(s.animal))
    val age = YearOfLife.age(birthYear, year)
    val male = born.animal.gender == Gender.MALE
    Section(stringResource(R.string.moment_gates_section))
    Text(
        labels.string(R.string.moment_gates_subtitle, labels.ordinal(age), stringResource(if (male) R.string.moment_gates_down else R.string.moment_gates_up)),
        style = body.copy(fontSize = 14.sp, color = Palette.muted, lineHeight = 20.sp),
    )
    MomentGates.gates(born, age, male, moment).forEach { g ->
        val parts = g.years.map { y ->
            val title = when {
                y.offset > 0 -> labels.string(R.string.moment_gate_up, y.offset + 1, name(y.year))
                y.offset < 0 -> labels.string(R.string.moment_gate_down, -y.offset + 1, name(y.year))
                else -> labels.string(R.string.moment_gate_reached, name(y.year))
            }
            Annotation(title = title, english = "", tone = if (y.cuts) Tone.BAD else Tone.GOOD, reading = Texts.momentGate(g.gate, y.offset, y.cuts), titleIsKanji = false)
        }
        AnnotationRow(
            Annotation(
                title = labels.string(R.string.moment_gate_title, g.gate.ordinal + 1, g.gate.english.replaceFirstChar(Char::uppercase)),
                english = labels.string(R.string.moment_gate_counted, name(g.start), name(g.reached)),
                tone = Tone.NEUTRAL,
                reading = null,
                titleIsKanji = false,
                parts = parts,
            ),
            onOpen,
        )
    }
    Section(stringResource(R.string.moment_sixty_section))
    val falling = MomentGates.sixty(born, age, male, moment)
    if (falling.isEmpty()) {
        Text(stringResource(R.string.moment_sixty_none), style = body.copy(fontSize = 13.sp, color = Palette.muted, lineHeight = 19.sp))
    }
    falling.forEach { r ->
        MomentRow(labels.string(R.string.moment_sixty_title, name(r.start)), Texts.momentSixty(r.count), onOpen, english = labels.string(R.string.moment_sixty_reached, name(r.reached)), tone = Tone.BAD)
    }
    FightRow(born, birthYear, year, moment, onOpen)
}

/** Whether the wealth is there (p. 168), the asker's [a] body and power on the [moment]. */
@Composable
private fun WealthRow(a: zanshin.core.tibetan.YearForces, moment: Sign, onOpen: (Annotation) -> Unit) {
    val labels = LocalLabels.current
    val wealth = MomentSigns.wealthPresent(a, moment)
    MomentRow(stringResource(if (wealth) R.string.moment_wealth_present else R.string.moment_wealth_absent), Texts.momentWealthThere(wealth), onOpen,
        english = labels.string(R.string.moment_body_power, MomentPlaces.stage(a.body, moment).english, MomentPlaces.stage(a.destiny, moment).english),
        tone = if (wealth) Tone.GOOD else Tone.BAD)
}

/** The fighting enemies (p. 172; the notes, p. 537): their pebbles and verdict for one born in [born] in the Tibetan year [birthYear], in [year]. */
@Composable
private fun FightRow(born: Sign, birthYear: Int, year: Int, moment: Sign, onOpen: (Annotation) -> Unit) {
    val labels = LocalLabels.current
    val (pebbles, verdict) = MomentGates.fight(born, YearOfLife.age(birthYear, year), born.animal.gender == Gender.MALE, moment)
    MomentRow(
        labels.string(R.string.moment_fight_title, pebbles.count { it == PebbleColour.WHITE }, pebbles.count { it == PebbleColour.BLACK }),
        Texts.momentFight(verdict), onOpen,
        english = pebbles.chunked(4).joinToString(" ") { c -> c.joinToString("") { if (it == PebbleColour.WHITE) "○" else "×" } },
        tone = when (verdict) { PebbleColour.WHITE -> Tone.GOOD; PebbleColour.BLACK -> Tone.BAD; PebbleColour.SPECKLED -> Tone.MIXED },
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
        val eye = Annotation(title = stringResource(R.string.moment_square_eye), english = "", tone = Tone.NEUTRAL, reading = Texts.momentEye(s.mewa), titleIsKanji = false)
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
                parts = listOfNotNull(fate, guard, eye),
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

/**
 * The signs WB lists for the moment that are met, not reckoned (pp. 144–151, 170; ROADMAP R2z): the dreams, the
 * asker's arrival, gestures and posture, the road's omens, the place, time and oath, the implements and the smoke, the
 * voices and callers; a row for each group, its sheet each sign with its reading.
 */
@Composable
private fun ObservedSignsSection(onOpen: (Annotation) -> Unit) {
    val labels = LocalLabels.current
    Section(stringResource(R.string.moment_signs_section))
    Text(stringResource(R.string.moment_signs_note), style = body.copy(fontSize = 13.sp, color = Palette.muted, lineHeight = 19.sp))
    ObservedSigns.GROUPS.forEach { (group, signs) ->
        AnnotationRow(
            Annotation(
                title = Catalog.text("MomentSign.$group"),
                english = labels.string(R.string.moment_signs_count, signs.size),
                tone = Tone.NEUTRAL,
                reading = null,
                titleIsKanji = false,
                parts = signs.map { (key, tone) ->
                    Annotation(title = Catalog.text("MomentSign.$group.$key"), english = "", tone = tone, reading = Texts.momentSign(group, key), titleIsKanji = false)
                },
            ),
            onOpen,
        )
    }
}
