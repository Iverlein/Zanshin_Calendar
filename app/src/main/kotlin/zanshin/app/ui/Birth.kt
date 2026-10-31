/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.iverlein.zanshin.R
import zanshin.app.BirthReading
import zanshin.app.Labels
import zanshin.app.LocalLabels
import zanshin.app.Person
import zanshin.app.Reckonings
import zanshin.app.Relation
import zanshin.core.kyureki.Tone
import zanshin.core.texts.Catalog
import zanshin.core.texts.Texts
import zanshin.core.texts.gloss
import zanshin.core.tibetan.Animal
import zanshin.core.tibetan.BirthChild
import zanshin.core.tibetan.BirthWhole
import zanshin.core.tibetan.BirthReckoning
import zanshin.core.tibetan.ClanYear
import zanshin.core.tibetan.Forces
import zanshin.core.tibetan.PowerYear
import zanshin.core.tibetan.DpalByur
import zanshin.core.tibetan.Force
import zanshin.core.tibetan.Ewts
import zanshin.core.tibetan.LineGroup
import zanshin.core.tibetan.Gender
import zanshin.core.tibetan.GroupCut
import zanshin.core.tibetan.Hundred
import zanshin.core.tibetan.HundredKin
import zanshin.core.tibetan.UnchangingLuck
import zanshin.core.tibetan.SME_BA_COLOURS
import zanshin.core.tibetan.BirthShare
import zanshin.core.tibetan.smeBaElement
import zanshin.core.tibetan.Element
import zanshin.core.tibetan.RaisingDay
import zanshin.core.tibetan.RaisingMonth
import zanshin.core.tibetan.PebbleColour
import zanshin.core.tibetan.PreciousPebble
import zanshin.core.tibetan.Sign
import zanshin.core.tibetan.TibetanCalendar
import zanshin.core.tibetan.Trigram
import zanshin.core.tibetan.YearOfLife

/**
 * The reckoning of [person]'s birth [b] (SPEC §5.9.2, §10.11, ROADMAP R2): the natal trigram and what it makes them
 * against their life force, then the readings of the parents' trigrams that hold. Each row opens its reading through
 * [onOpen].
 */
@Composable
fun BirthReckoningView(person: Person, b: BirthReading, onOpen: (Annotation) -> Unit) {
    val labels = LocalLabels.current
    val muted = body.copy(fontSize = 13.sp, color = Palette.muted, lineHeight = 19.sp)
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text(
            stringResource(R.string.birth_subtitle, personLabel(person), signName(b.birth, labels), gloss(b.vitality, "inText")),
            style = body.copy(fontSize = 14.sp, color = Palette.muted, lineHeight = 20.sp),
        )
        val h = person.gender?.let { BirthReckoning.hundred(b.birth, b.birthYear, Forces.signs(TibetanCalendar.of(person.birth)).month, it, b.mother.birthYear!!, b.father?.birth) }
        TogetherSection(h, onOpen)
        Section(stringResource(R.string.birth_section_natal))
        AnnotationRow(natalTrigramAnnotation(b.birthYear, b.natal, b.mother, labels), onOpen)
        AnnotationRow(childAnnotation(b, labels), onOpen)
        val forces = Forces.of(b.birth.element, b.birth.animal)
        val trigramElement = YearOfLife.trigramElement(b.natal)
        listOf(BirthShare.BODY, BirthShare.DESTINY, BirthShare.LUCK).forEach { share ->
            val force = share.force!!
            val element = forces[force]
            AnnotationRow(
                shareAnnotation(
                    share, BirthReckoning.kind(element, trigramElement), trigramElement,
                    labels.string(R.string.birth_share_subtitle, gloss(force).replaceFirstChar(Char::uppercase), gloss(element, "inText"), Ewts.named(b.natal.english, b.natal.wylie), gloss(trigramElement, "inText")),
                ),
                onOpen,
            )
        }

        DpalByurSection(b, onOpen)
        LineSection(person, b, onOpen)
        PowerSection(person, onOpen)
        ClanSection(person, onOpen)

        Section(stringResource(R.string.birth_section_parents))
        if (person.gender == null) Text(stringResource(R.string.birth_gender_needed), style = muted)
        if (b.fatherTrigram == null) Text(stringResource(R.string.birth_father_needed), style = muted)
        if (b.omens.isEmpty()) Text(stringResource(R.string.birth_parents_none), style = muted)
        b.omens.forEach { o ->
            AnnotationRow(
                Annotation(
                    title = o.english.replaceFirstChar(Char::uppercase),
                    english = "",
                    tone = Tone.BAD,
                    reading = Texts.birthOmen(o),
                    titleIsKanji = false,
                    details = parentsDetails(b, person.gender, labels),
                ),
                onOpen,
            )
        }
        HundredSection(person, b, h, onOpen)
        Text(stringResource(R.string.birth_note), style = muted)
    }
}

/**
 * The hundred pebbles (pp. 261, 329–331): the board as Kun dga' rig 'dzin's sheet lays it (KD vol. 3, p. 718), the
 * five unchanging lucks top to bottom against the four aspects' own, mother, son, friend and enemy, the deciding
 * pebbles below; then each deciding pebble with its reading. A son's needs his father's year.
 */
@Composable
private fun HundredSection(person: Person, b: BirthReading, h: Hundred?, onOpen: (Annotation) -> Unit) {
    val labels = LocalLabels.current
    val muted = body.copy(fontSize = 13.sp, color = Palette.muted, lineHeight = 19.sp)
    Section(stringResource(R.string.birth_section_hundred))
    val gender = person.gender
    val month = Forces.signs(TibetanCalendar.of(person.birth)).month
    if (h == null || gender == null) {
        Text(stringResource(R.string.birth_hundred_needed), style = muted)
        return
    }
    HundredBoard(h)
    val motherAge = YearOfLife.age(b.mother.birthYear!!, b.birthYear)
    val natalMewa = YearOfLife.yearSmeBa(b.birthYear)
    val rows = listOf(
        stringResource(if (gender == Gender.FEMALE) R.string.birth_hundred_mother_year else R.string.birth_hundred_father_year) to
            signName(if (gender == Gender.FEMALE) b.mother.birth!! else b.father!!.birth!!, labels),
        UnchangingLuck.MOTHER_LOG_MEN.english to signName(YearOfLife.logMen(b.mother.birth!!.element, Gender.FEMALE, motherAge), labels),
        UnchangingLuck.TRIGRAM.english to trigramName(b.natal),
        UnchangingLuck.MEWA.english to "$natalMewa ${Catalog.text("Colour.${SME_BA_COLOURS[natalMewa - 1]}")}",
        UnchangingLuck.MONTH.english to signName(month, labels),
    )
    rows.forEachIndexed { i, (luck, value) -> Text(labels.string(R.string.birth_hundred_row, i + 1, luck, value), style = muted) }
    Text(stringResource(R.string.birth_hundred_columns), style = muted)
    Force.entries.forEach { force ->
        HundredKin.entries.forEach { kin ->
            val colour = h.deciding(force, kin)
            val white = UnchangingLuck.entries.count { h.cell(it, force, kin) == PebbleColour.WHITE }
            AnnotationRow(
                Annotation(
                    title = stringResource(
                        R.string.birth_share_title, "${gloss(force).replaceFirstChar(Char::uppercase)} · ${kin.english}",
                        stringResource(if (colour == PebbleColour.WHITE) R.string.birth_precious_white else R.string.birth_precious_black),
                    ),
                    english = "",
                    tone = if (h.good(force, kin)) Tone.GOOD else Tone.BAD,
                    reading = Texts.hundredPebble(force, kin, colour),
                    subtitle = labels.string(R.string.birth_hundred_subtitle, gloss(h.column(force, kin), "inText"), white, 5 - white),
                    titleIsKanji = false,
                ),
                onOpen,
            )
        }
    }
}

/**
 * The birth together (ROADMAP R4; docs/sources/verdicts.md, RD19): the hundred's twenty deciding pebbles weighed by
 * force and number (p. 349), with the remedies of the bad ones and of the sets gathered; the sets of p. 338 that hold;
 * how the board is read whole (p. 331). Without the hundred, a line saying what it needs.
 */
@Composable
private fun TogetherSection(h: Hundred?, onOpen: (Annotation) -> Unit) {
    val labels = LocalLabels.current
    Section(stringResource(R.string.match_section_together))
    if (h == null) {
        Text(stringResource(R.string.birth_together_needed), style = body.copy(fontSize = 13.sp, color = Palette.muted, lineHeight = 19.sp))
        return
    }
    val sets = BirthReckoning.sets(h)
    val bad = Force.entries.flatMap { f -> HundredKin.entries.filter { !h.good(f, it) }.map { Texts.hundredPebble(f, it, h.deciding(f, it)) } } +
        sets.filter { it.good == false }.map { Texts.birthSet(it) }
    val remedies = gatheredRemedies(bad)
    val whole = BirthReckoning.whole(h)
    val good = BirthReckoning.goodCount(h)
    AnnotationRow(
        Annotation(
            title = stringResource(R.string.birth_share_title, stringResource(R.string.birth_together_title), whole.english),
            english = "",
            tone = when (whole) {
                BirthWhole.GOOD -> Tone.GOOD
                BirthWhole.EVEN -> Tone.MIXED
                BirthWhole.BAD -> Tone.BAD
            },
            reading = Texts.birthTogether(whole, remedies),
            subtitle = labels.string(R.string.birth_together_subtitle, good, 20 - good),
            titleIsKanji = false,
        ),
        onOpen,
    )
    sets.forEach { set ->
        AnnotationRow(
            Annotation(
                title = set.english.replaceFirstChar(Char::uppercase),
                english = "",
                tone = when (set.good) {
                    true -> Tone.GOOD
                    false -> Tone.BAD
                    null -> Tone.MIXED
                },
                reading = Texts.birthSet(set),
                titleIsKanji = false,
            ),
            onOpen,
        )
    }
    AnnotationRow(
        Annotation(title = stringResource(R.string.birth_board_title), english = "", tone = Tone.NEUTRAL, reading = Texts.birthBoard(), titleIsKanji = false),
        onOpen,
    )
}

/** The hundred's board: a block of five columns for each aspect, a row for each unchanging luck, the deciding row below a rule. */
@Composable
private fun HundredBoard(h: Hundred) {
    val pebble = { c: PebbleColour -> if (c == PebbleColour.WHITE) "○" else "×" }
    val cell = body.copy(fontSize = 12.sp, lineHeight = 16.sp)
    Column(verticalArrangement = Arrangement.spacedBy(1.dp)) {
        Row(Modifier.fillMaxWidth()) {
            Box(Modifier.weight(1f))
            Force.entries.forEach {
                Text(gloss(it), style = body.copy(fontSize = 11.sp, color = Palette.muted, lineHeight = 14.sp), textAlign = TextAlign.Center, maxLines = 1, modifier = Modifier.weight(5f))
            }
        }
        UnchangingLuck.entries.forEachIndexed { i, luck ->
            Row(Modifier.fillMaxWidth()) {
                Text("${i + 1}", style = cell.copy(color = Palette.muted), modifier = Modifier.weight(1f))
                Force.entries.forEach { force ->
                    HundredKin.entries.forEach { kin -> Text(pebble(h.cell(luck, force, kin)), style = cell, textAlign = TextAlign.Center, modifier = Modifier.weight(1f)) }
                }
            }
        }
        HorizontalDivider(color = Palette.faint)
        Row(Modifier.fillMaxWidth()) {
            Box(Modifier.weight(1f))
            Force.entries.forEach { force ->
                HundredKin.entries.forEach { kin ->
                    Text(pebble(h.deciding(force, kin)), style = cell.copy(color = if (h.good(force, kin)) Palette.good else Palette.bad), textAlign = TextAlign.Center, modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

/**
 * The birth year read on its own, needing no one else: the twelve animals' natures and the sixty years (pp. 263–271),
 * the body and its pair of years among the thirty (pp. 271–275), the natal mewa (pp. 288–294), then the year's own destiny, its destiny against its life force (pp. 261, 275–280).
 */
@Composable
fun PowerSection(person: Person, onOpen: (Annotation) -> Unit) {
    val labels = LocalLabels.current
    Section(stringResource(R.string.birth_section_year))
    val born = TibetanCalendar.of(person.birth)
    val sign = Sign(born.yearElement, born.yearAnimal)
    val y = BirthReckoning.powerYear(sign)
    val natalMewa = YearOfLife.yearSmeBa(born.year)
    val forces = Forces.of(sign.element, sign.animal)
    AnnotationRow(
        Annotation(
            title = stringResource(R.string.birth_nature_title),
            english = "",
            tone = Tone.NEUTRAL,
            reading = Texts.yearNature(sign.animal),
            subtitle = gloss(sign.animal),
            titleIsKanji = false,
        ),
        onOpen,
    )
    AnnotationRow(
        Annotation(
            title = stringResource(R.string.birth_sixty_title),
            english = "",
            tone = Tone.NEUTRAL,
            reading = Texts.sixtyYear(sign),
            subtitle = signName(sign, labels),
            titleIsKanji = false,
        ),
        onOpen,
    )
    AnnotationRow(
        Annotation(
            title = stringResource(R.string.birth_body_title),
            english = "",
            tone = Tone.NEUTRAL,
            reading = Texts.bodyNature(forces.body),
            subtitle = labels.string(R.string.birth_body_subtitle, gloss(forces.body, "inText")),
            titleIsKanji = false,
        ),
        onOpen,
    )
    val first = BirthReckoning.pairFirst(sign)
    AnnotationRow(
        Annotation(
            title = stringResource(R.string.birth_pair_title),
            english = "",
            tone = Tone.NEUTRAL,
            reading = Texts.khaSrog(sign),
            subtitle = labels.string(R.string.birth_pair_subtitle, signName(first, labels), signName(Sign(first.element, Animal.entries[first.animal.ordinal + 1]), labels)),
            titleIsKanji = false,
        ),
        onOpen,
    )
    AnnotationRow(
        Annotation(
            title = stringResource(R.string.birth_mewa_title),
            english = "",
            tone = Tone.NEUTRAL,
            reading = Texts.birthMewa(natalMewa),
            subtitle = labels.string(R.string.year_mewa_subtitle, natalMewa, Catalog.text("Colour.${SME_BA_COLOURS[natalMewa - 1]}")),
            titleIsKanji = false,
        ),
        onOpen,
    )
    val mewaElement = smeBaElement(natalMewa)
    AnnotationRow(
        shareAnnotation(
            BirthShare.MEWA, BirthReckoning.kind(forces.vitality, mewaElement), mewaElement,
            labels.string(R.string.birth_share_mewa_subtitle, natalMewa, Catalog.text("Colour.${SME_BA_COLOURS[natalMewa - 1]}"), gloss(mewaElement, "inText"), gloss(forces.vitality, "inText")),
        ),
        onOpen,
    )
    BirthReckoning.riseDecline(born).forEach { (force, sector) ->
        AnnotationRow(
            Annotation(
                title = stringResource(R.string.birth_share_title, gloss(force).replaceFirstChar(Char::uppercase), sector.english),
                english = "",
                tone = if (sector.good) Tone.GOOD else Tone.BAD,
                reading = Texts.riseDecline(force, sector),
                subtitle = labels.string(
                    when (force) {
                        Force.VITALITY -> R.string.birth_rise_year
                        Force.BODY -> R.string.birth_rise_month
                        else -> R.string.birth_rise_day
                    },
                    gloss(forces[force], "inText"),
                ),
                titleIsKanji = false,
            ),
            onOpen,
        )
    }
    Section(stringResource(R.string.birth_section_precious))
    listOf(PreciousPebble.YEAR to sign.animal, PreciousPebble.MONTH to born.monthNames.animal, PreciousPebble.DAY to born.dayAnimal).forEach { (p, animal) ->
        val colour = BirthReckoning.preciousColour(animal)
        val reading = Texts.preciousPebble(p, colour)
        if (reading == null) {
            Text(labels.string(R.string.birth_precious_speckled, p.english.replaceFirstChar(Char::uppercase), gloss(animal)), style = body.copy(fontSize = 13.sp, color = Palette.muted, lineHeight = 19.sp))
        } else {
            AnnotationRow(
                Annotation(
                    title = stringResource(R.string.birth_share_title, p.english.replaceFirstChar(Char::uppercase), stringResource(if (colour == PebbleColour.WHITE) R.string.birth_precious_white else R.string.birth_precious_black)),
                    english = "",
                    tone = if (colour == PebbleColour.WHITE) Tone.GOOD else Tone.BAD,
                    reading = reading,
                    subtitle = gloss(animal),
                    titleIsKanji = false,
                ),
                onOpen,
            )
        }
    }
    val month = BirthReckoning.raisingMonth(sign.animal, born.monthNames.animal)
    val day = BirthReckoning.raisingDay(sign.animal, born.dayAnimal)
    if (month != null || day != null) Section(stringResource(R.string.birth_section_raising))
    month?.let {
        AnnotationRow(
            Annotation(
                title = stringResource(R.string.birth_share_title, stringResource(R.string.birth_raising_month), it.english),
                english = "",
                tone = if (it == RaisingMonth.GOOD) Tone.GOOD else Tone.BAD,
                reading = Texts.raisingMonth(it),
                subtitle = labels.string(R.string.birth_raising_month_subtitle, gloss(born.monthNames.animal)),
                titleIsKanji = false,
            ),
            onOpen,
        )
    }
    day?.let {
        AnnotationRow(
            Annotation(
                title = stringResource(R.string.birth_share_title, stringResource(R.string.birth_raising_day), it.english),
                english = "",
                tone = if (it == RaisingDay.EASY) Tone.GOOD else Tone.BAD,
                reading = Texts.raisingDay(it),
                subtitle = labels.string(R.string.birth_raising_day_subtitle, gloss(born.dayAnimal)),
                titleIsKanji = false,
            ),
            onOpen,
        )
    }
    AnnotationRow(
        Annotation(
            title = y.english.replaceFirstChar(Char::uppercase),
            english = "",
            tone = when (y) {
                PowerYear.KHA_YAN, PowerYear.KHA_RAL -> Tone.MIXED
                PowerYear.KHONG_NONG, PowerYear.SE_ZHIG -> Tone.GOOD
                PowerYear.DUN_KHUR -> Tone.BAD
            },
            reading = Texts.powerYear(y),
            subtitle = labels.string(R.string.birth_year_subtitle, gloss(forces.vitality, "inText"), gloss(forces.destiny, "inText")),
            titleIsKanji = false,
            tibetan = y.wylie to y.english,
            details = listOf(
                labels.string(R.string.birth_detail_child) to signName(sign, labels),
                labels.string(R.string.birth_detail_vitality) to gloss(forces.vitality, "inText"),
                labels.string(R.string.birth_detail_destiny) to gloss(forces.destiny, "inText"),
            ),
        ),
        onOpen,
    )
    AnnotationRow(
        Annotation(
            title = stringResource(R.string.birth_year_side_title),
            english = "",
            tone = Tone.NEUTRAL,
            reading = Texts.powerYearOf(y, forces.destiny),
            subtitle = labels.string(R.string.birth_year_side_subtitle, Ewts.named(y.english, y.wylie), gloss(forces.destiny, "inText")),
            titleIsKanji = false,
        ),
        onOpen,
    )
    val place = BirthReckoning.yearPlace(sign.animal)
    AnnotationRow(
        Annotation(
            title = place.english.replaceFirstChar(Char::uppercase),
            english = "",
            tone = Tone.NEUTRAL,
            reading = Texts.yearPlace(place),
            subtitle = labels.string(R.string.birth_place_subtitle, gloss(sign.animal)),
            titleIsKanji = false,
            tibetan = place.wylie to place.english,
        ),
        onOpen,
    )
    AnnotationRow(
        Annotation(
            title = stringResource(R.string.birth_place_full_title),
            english = "",
            tone = Tone.NEUTRAL,
            reading = Texts.yearPlaceFull(place),
            subtitle = place.english,
            titleIsKanji = false,
        ),
        onOpen,
    )
    val triad = Animal.entries.filter { Forces.of(sign.element, it).luck == forces.luck }
    AnnotationRow(
        Annotation(
            title = stringResource(R.string.birth_triad_title),
            english = "",
            tone = Tone.NEUTRAL,
            reading = Texts.luckTriad(forces.luck),
            subtitle = labels.string(R.string.birth_triad_subtitle, triad.joinToString(", ") { gloss(it) }, gloss(forces.luck, "inText")),
            titleIsKanji = false,
        ),
        onOpen,
    )
    val cuts = BirthReckoning.cuts(sign)
    val groups = BirthReckoning.groupCuts(sign)
    if (cuts.isNotEmpty() || groups.isNotEmpty()) Section(stringResource(R.string.birth_section_cuts))
    cuts.forEach { (a, c) ->
        AnnotationRow(
            Annotation(
                title = Catalog.text("BirthCut.${a.name}_${c.name}").replaceFirstChar(Char::uppercase),
                english = "",
                tone = Tone.BAD,
                reading = Texts.birthCut(a, c),
                subtitle = labels.string(R.string.birth_cut_subtitle, gloss(forces[a], "inText"), gloss(forces[c], "inText")),
                titleIsKanji = false,
            ),
            onOpen,
        )
    }
    groups.forEach { g ->
        AnnotationRow(
            Annotation(
                title = g.english.replaceFirstChar(Char::uppercase),
                english = "",
                tone = if (g.nourish) Tone.GOOD else Tone.BAD,
                reading = Texts.groupCut(g),
                titleIsKanji = false,
            ),
            onOpen,
        )
    }
}

/** The special pebble (pp. 261, 284–286): the birth year against the clan's element, or what it needs. */
@Composable
fun ClanSection(person: Person, onOpen: (Annotation) -> Unit) {
    val labels = LocalLabels.current
    Section(stringResource(R.string.birth_section_clan))
    val y = Reckonings.clanYear(person)
    val clan = person.clan
    if (y == null || clan == null) {
        Text(stringResource(R.string.birth_clan_needed), style = body.copy(fontSize = 13.sp, color = Palette.muted, lineHeight = 19.sp))
        return
    }
    val born = TibetanCalendar.of(person.birth)
    AnnotationRow(
        Annotation(
            title = y.english.replaceFirstChar(Char::uppercase),
            english = "",
            tone = when (y) {
                ClanYear.GREAT_PENALTY, ClanYear.POWER, ClanYear.SON -> Tone.GOOD
                ClanYear.SMALL_PENALTY, ClanYear.MOTHER -> Tone.MIXED
                ClanYear.FRIEND, ClanYear.ENEMY -> Tone.BAD
            },
            reading = Texts.clanYear(y),
            subtitle = labels.string(R.string.birth_clan_subtitle, Ewts.named(clan.english, clan.wylie), gloss(clan.element, "inText"), gloss(born.yearAnimal)),
            titleIsKanji = false,
            details = listOf(
                labels.string(R.string.birth_detail_clan) to "${Ewts.named(clan.english, clan.wylie)} · ${gloss(clan.element, "inText")}",
                labels.string(R.string.birth_detail_child) to signName(Sign(born.yearElement, born.yearAnimal), labels),
            ),
        ),
        onOpen,
    )
}

internal fun signName(s: Sign, labels: Labels) = labels.string(R.string.element_animal, gloss(s.element), gloss(s.animal))

private fun trigramName(t: Trigram) = Ewts.named(t.english, t.wylie)

/** The one born, by the natal trigram's element against the life force (p. 294). */
/**
 * Glory and misfortune (pp. 295–297): one's life force and luck against the father's year's, body and destiny
 * against the mother's; the father's two only when his year is known.
 */
@Composable
private fun DpalByurSection(b: BirthReading, onOpen: (Annotation) -> Unit) {
    val labels = LocalLabels.current
    Section(stringResource(R.string.birth_section_dpal_byur))
    val own = Forces.of(b.birth.element, b.birth.animal)
    val mother = b.mother.birth!!.let { Forces.of(it.element, it.animal) }
    val father = b.father?.birth?.let { Forces.of(it.element, it.animal) }
    listOf(Force.VITALITY, Force.BODY, Force.DESTINY, Force.LUCK).forEach { force ->
        val byFather = BirthReckoning.dpalByurParentIsFather(force)
        val parent = (if (byFather) father else mother) ?: return@forEach
        val result = BirthReckoning.dpalByur(own[force], parent[force])
        AnnotationRow(
            Annotation(
                title = stringResource(R.string.birth_share_title, gloss(force).replaceFirstChar(Char::uppercase), result.english),
                english = "",
                tone = when (result) {
                    DpalByur.GLORY, DpalByur.LIFE_TAKEN -> Tone.GOOD
                    DpalByur.OWN_FOOT -> Tone.MIXED
                    DpalByur.MISFORTUNE, DpalByur.LUSTRE_LOST -> Tone.BAD
                },
                reading = Texts.dpalByur(force, result, own[force]),
                subtitle = labels.string(
                    if (byFather) R.string.birth_dpal_byur_father else R.string.birth_dpal_byur_mother,
                    gloss(own[force], "inText"), gloss(parent[force], "inText"),
                ),
                titleIsKanji = false,
            ),
            onOpen,
        )
    }
    if (father == null) Text(stringResource(R.string.birth_dpal_byur_father_needed), style = body.copy(fontSize = 13.sp, color = Palette.muted, lineHeight = 19.sp))
}

/**
 * The line of births (pp. 297–299): the father's and the grandfather's life force counted down onto one's birth
 * year, each with its year; the grandfather is the father entered under the father.
 */
@Composable
private fun LineSection(person: Person, b: BirthReading, onOpen: (Annotation) -> Unit) {
    val labels = LocalLabels.current
    val muted = body.copy(fontSize = 13.sp, color = Palette.muted, lineHeight = 19.sp)
    Section(stringResource(R.string.birth_section_line))
    val father = person.relatives.list.firstOrNull { it.of == null && it.relation == Relation.FATHER && it.birth != null }
    val grandfather = father?.let { f -> person.relatives.list.firstOrNull { it.of == f.id && it.relation == Relation.FATHER && it.birth != null } }
    listOf(R.string.birth_line_father to father, R.string.birth_line_grandfather to grandfather).forEach { (whose, r) ->
        val sign = r?.birth?.let { YearOfLife.yearSign(it.tibetanYear) } ?: return@forEach
        val stage = BirthReckoning.lineStage(sign.animal, b.birth.animal)
        val subtitle = labels.string(R.string.birth_line_subtitle, signName(sign, labels), gloss(Forces.of(sign.element, sign.animal).vitality, "inText"))
        if (stage == null) {
            Text(labels.string(R.string.birth_line_clear, stringResource(whose), signName(sign, labels)), style = muted)
        } else {
            AnnotationRow(
                Annotation(
                    title = stringResource(R.string.birth_share_title, stringResource(whose), stage.english),
                    english = "",
                    tone = Tone.BAD,
                    reading = Texts.birthLine(stage, LineGroup.of(sign.animal)),
                    subtitle = subtitle,
                    titleIsKanji = false,
                ),
                onOpen,
            )
        }
    }
    if (father == null) Text(stringResource(R.string.birth_line_needed), style = muted)
}

/** One of the five that the natal trigram, or the natal mewa, of element [against] makes the share [share] (p. 294). */
@Composable
private fun shareAnnotation(share: BirthShare, kind: BirthChild, against: Element, subtitle: String): Annotation = Annotation(
    title = stringResource(
        R.string.birth_share_title,
        share.force?.let { gloss(it).replaceFirstChar(Char::uppercase) } ?: stringResource(R.string.birth_share_mewa),
        kind.english,
    ),
    english = "",
    tone = when (kind) {
        BirthChild.PARENTS_GOD, BirthChild.CHILDRENS_GLORY -> Tone.GOOD
        BirthChild.LIFE -> Tone.MIXED
        BirthChild.SPIRIT, BirthChild.DEMON -> Tone.BAD
    },
    reading = Texts.birthAspect(share, kind, against),
    subtitle = subtitle,
    titleIsKanji = false,
)

private fun childAnnotation(b: BirthReading, labels: Labels): Annotation {
    val element = YearOfLife.trigramElement(b.natal)
    return Annotation(
        title = b.child.english.replaceFirstChar(Char::uppercase),
        english = "",
        tone = when (b.child) {
            BirthChild.PARENTS_GOD, BirthChild.CHILDRENS_GLORY -> Tone.GOOD
            BirthChild.LIFE -> Tone.MIXED
            BirthChild.SPIRIT, BirthChild.DEMON -> Tone.BAD
        },
        reading = Texts.birthChild(b.child, b.vitality),
        subtitle = labels.string(R.string.birth_child_subtitle, gloss(b.vitality, "inText"), Ewts.named(b.natal.english, b.natal.wylie), gloss(element, "inText")),
        titleIsKanji = false,
        tibetan = b.child.wylie to b.child.english,
        details = listOfNotNull(
            labels.string(R.string.birth_detail_vitality) to gloss(b.vitality, "inText"),
            labels.string(R.string.birth_detail_trigram_element) to "${trigramName(b.natal)} · ${gloss(element, "inText")}",
            if (b.child == BirthChild.DEMON) labels.string(R.string.birth_detail_demon_age) to "${BirthReckoning.demonAge(b.vitality)}" else null,
        ),
    )
}

/** What the readings of the parents' trigrams were read from: both trigrams, both years and the child's sex. */
private fun parentsDetails(b: BirthReading, gender: Gender?, labels: Labels): List<Pair<String, String>> = listOfNotNull(
    labels.string(R.string.birth_detail_mother) to "${trigramName(b.natal)} · ${signName(b.mother.birth!!, labels)}",
    b.fatherTrigram?.let { labels.string(R.string.birth_detail_father) to "${trigramName(it)} · ${signName(b.father!!.birth!!, labels)}" },
    labels.string(R.string.birth_detail_child) to listOfNotNull(signName(b.birth, labels), gender?.let { gloss(it, "inText") }).joinToString(" · "),
)
