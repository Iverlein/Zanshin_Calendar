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
import zanshin.app.BirthReading
import zanshin.app.Labels
import zanshin.app.LocalLabels
import zanshin.app.Person
import zanshin.app.Reckonings
import zanshin.core.kyureki.Tone
import zanshin.core.texts.Texts
import zanshin.core.texts.gloss
import zanshin.core.tibetan.Animal
import zanshin.core.tibetan.BirthChild
import zanshin.core.tibetan.BirthReckoning
import zanshin.core.tibetan.ClanYear
import zanshin.core.tibetan.Forces
import zanshin.core.tibetan.PowerYear
import zanshin.core.tibetan.Ewts
import zanshin.core.tibetan.Gender
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
        Section(stringResource(R.string.birth_section_natal))
        AnnotationRow(natalTrigramAnnotation(b.birthYear, b.natal, b.mother, labels), onOpen)
        AnnotationRow(childAnnotation(b, labels), onOpen)

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
        Text(stringResource(R.string.birth_note), style = muted)
    }
}

/** The year's own destiny (pp. 261, 275–280): the birth year's destiny against its life force; it needs no one else. */
@Composable
fun PowerSection(person: Person, onOpen: (Annotation) -> Unit) {
    val labels = LocalLabels.current
    Section(stringResource(R.string.birth_section_year))
    val born = TibetanCalendar.of(person.birth)
    val sign = Sign(born.yearElement, born.yearAnimal)
    val y = BirthReckoning.powerYear(sign)
    val forces = Forces.of(sign.element, sign.animal)
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
    BirthReckoning.cuts(sign).forEach { (a, c) ->
        AnnotationRow(
            Annotation(
                title = labels.string(R.string.birth_cut_title, a.english.replaceFirstChar(Char::uppercase), c.english),
                english = "",
                tone = Tone.BAD,
                reading = Texts.birthCut(a, c),
                subtitle = labels.string(R.string.birth_cut_subtitle, gloss(forces[a], "inText"), gloss(forces[c], "inText")),
                titleIsKanji = false,
            ),
            onOpen,
        )
    }
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

private fun signName(s: Sign, labels: Labels) = labels.string(R.string.element_animal, gloss(s.element), gloss(s.animal))

private fun trigramName(t: Trigram) = Ewts.named(t.english, t.wylie)

/** The one born, by the natal trigram's element against the life force (p. 294). */
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
