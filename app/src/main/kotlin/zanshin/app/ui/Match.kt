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
import zanshin.app.MatchReading
import zanshin.core.kyureki.Tone
import zanshin.core.texts.Catalog
import zanshin.core.texts.Texts
import zanshin.core.texts.gloss
import zanshin.core.tibetan.Animal
import zanshin.core.tibetan.BadYear
import zanshin.core.tibetan.BadYearPebble
import zanshin.core.tibetan.Force
import zanshin.core.tibetan.SiblingsWin
import zanshin.core.tibetan.SpouseKind
import zanshin.core.tibetan.Element
import zanshin.core.tibetan.Forces
import zanshin.core.tibetan.MatchMeeting
import zanshin.core.tibetan.MatchWhole
import zanshin.core.tibetan.MatchMewa
import zanshin.core.tibetan.MatchReckoning
import zanshin.core.tibetan.MatchParent
import zanshin.core.tibetan.MewaPathYear
import zanshin.core.tibetan.NiSha
import zanshin.core.tibetan.ParentWay
import zanshin.core.tibetan.SME_BA_COLOURS
import zanshin.core.tibetan.YearSexes
import zanshin.core.tibetan.matchStageGrade
import zanshin.core.tibetan.YearOfLife

/**
 * The marriage match (SPEC §5.9.3, ROADMAP R3): the single match of the four aspects, the wife of karma where all four
 * meet as mother and son, the two destinies set together and the *ni sha* reckoning. Each row opens its reading.
 */
@Composable
fun MatchReckoningView(m: MatchReading, onOpen: (Annotation) -> Unit) {
    val labels = LocalLabels.current
    val muted = body.copy(fontSize = 13.sp, color = Palette.muted, lineHeight = 19.sp)
    val man = Forces.of(m.man.element, m.man.animal)
    val woman = Forces.of(m.woman.element, m.woman.animal)
    val items = mutableListOf<Any>()
    fun add(a: Annotation) { items += a }
    fun section(title: String) { items += MatchHead(title) }
    fun note(text: String) { items += MatchNote(text) }
    section(stringResource(R.string.match_section_years))
    MatchReckoning.sameKind(m.man, m.woman)?.let { k ->
        add(
            Annotation(
                title = stringResource(R.string.match_same_kind_title, k.english),
                english = "",
                tone = Tone.BAD,
                reading = Texts.matchSameKind(k),
                titleIsKanji = false,
            ),
        )
    }
    val sameAnimal = MatchReckoning.sameAnimal(m.man, m.woman)
    sameAnimal?.let { a ->
        add(
            Annotation(
                title = stringResource(R.string.match_same_animal_title, gloss(a)),
                english = "",
                tone = when (a) {
                    Animal.PIG -> Tone.GOOD
                    Animal.DRAGON, Animal.SNAKE, Animal.SHEEP -> Tone.MIXED
                    else -> Tone.BAD
                },
                reading = Texts.matchSameAnimal(a),
                titleIsKanji = false,
            ),
        )
    }
    val sameElement = MatchReckoning.sameElement(m.man, m.woman)?.let { s -> Texts.matchSameElement(m.man.element, s)?.let { s to it } }
    sameElement?.let { (s, reading) ->
        add(
            Annotation(
                title = stringResource(R.string.match_same_element_title, gloss(m.man.element)),
                english = "",
                tone = sameElementTone(m.man.element, s),
                reading = reading,
                subtitle = gloss(s),
                titleIsKanji = false,
            ),
        )
    }
    val pairLists = MatchReckoning.pairLists(m.man.animal, m.woman.animal)
    val pairLines = MatchReckoning.pairLines(m.man.animal, m.woman.animal)
    pairLists.forEach { l ->
        add(
            Annotation(
                title = stringResource(R.string.match_pair_list_title, gloss(l)),
                english = "",
                tone = if (l.good) Tone.GOOD else Tone.BAD,
                reading = Texts.matchPairList(l),
                titleIsKanji = false,
            ),
        )
    }
    pairLines.forEach { l ->
        add(
            Annotation(
                title = stringResource(R.string.match_pair_line_title, gloss(m.man.animal), gloss(m.woman.animal)),
                english = "",
                tone = when (l.good) { true -> Tone.GOOD; false -> Tone.BAD; null -> Tone.MIXED },
                reading = Texts.matchPairLine(l),
                subtitle = stringResource(if (l.ordered) R.string.match_pair_line_takes else R.string.match_pair_line_either),
                titleIsKanji = false,
            ),
        )
    }
    val bad = MatchReckoning.badYears(m.man, YearOfLife.yearSmeBa(m.manYear)).filter { it != BadYear.BLACK_FANGS }.map { R.string.match_man_year to (it to m.man) } +
        MatchReckoning.badYears(m.woman, YearOfLife.yearSmeBa(m.womanYear)).map { R.string.match_woman_year to (it to m.woman) }
    bad.forEach { (whose, pair) ->
        val (y, sign) = pair
        add(
            Annotation(
                title = stringResource(R.string.birth_share_title, stringResource(whose), y.english),
                english = "",
                tone = Tone.BAD,
                reading = Texts.badYear(y),
                subtitle = signName(sign, labels),
                titleIsKanji = false,
            ),
        )
    }
    val pathNames = listOf(R.string.match_path_first, R.string.match_path_middle, R.string.match_path_lower)
    val pathYears = MatchReckoning.mewaPathYears(m.manYear).filter { it != MewaPathYear.FIVE_ELEMENTS }.map { Triple(R.string.match_man_year, it, m.manYear) } +
        MatchReckoning.mewaPathYears(m.womanYear).map { Triple(R.string.match_woman_year, it, m.womanYear) }
    pathYears.forEach { (whose, y, year) ->
        add(
            Annotation(
                title = stringResource(R.string.birth_share_title, stringResource(whose), gloss(y)),
                english = "",
                tone = Tone.BAD,
                reading = Texts.mewaPathYear(y),
                subtitle = labels.string(R.string.birth_share_title, signName(YearOfLife.yearSign(year), labels), stringResource(pathNames[MatchReckoning.mewaPath(year)])),
                titleIsKanji = false,
            ),
        )
    }
    val pebble = MatchReckoning.badYearPebble(
        man = bad.any { it.first == R.string.match_man_year } || pathYears.any { it.first == R.string.match_man_year },
        woman = bad.any { it.first == R.string.match_woman_year } || pathYears.any { it.first == R.string.match_woman_year },
    )
    add(
        Annotation(
            title = stringResource(R.string.match_pebble_title, gloss(pebble)),
            english = "",
            tone = when (pebble) {
                BadYearPebble.BLACK, BadYearPebble.SPECKLED_WOMAN -> Tone.BAD
                BadYearPebble.SPECKLED_MAN -> Tone.MIXED
                BadYearPebble.WHITE -> Tone.GOOD
            },
            reading = Texts.badYearPebble(pebble),
            titleIsKanji = false,
        ),
    )
    if (bad.isEmpty() && pathYears.isEmpty() && MatchReckoning.sameKind(m.man, m.woman) == null && sameAnimal == null && sameElement == null && pairLists.isEmpty() && pairLines.isEmpty()) note(stringResource(R.string.match_years_none))
    section(stringResource(R.string.match_section_single))
    MatchReckoning.aspects(man, woman).forEach { (force, meeting) ->
        add(
            Annotation(
                title = stringResource(R.string.birth_share_title, gloss(force).replaceFirstChar(Char::uppercase), meeting.english),
                english = "",
                tone = when (meeting) {
                    MatchMeeting.AGREE -> Tone.GOOD
                    MatchMeeting.MIDDLING -> Tone.MIXED
                    MatchMeeting.DISAGREE -> Tone.BAD
                },
                reading = Texts.matchAspect(force, meeting),
                subtitle = labels.string(R.string.match_aspect_subtitle, gloss(man[force], "inText"), gloss(woman[force], "inText")),
                titleIsKanji = false,
            ),
        )
    }
    if (MatchReckoning.karma(man, woman)) {
        add(
            Annotation(title = stringResource(R.string.match_karma_title), english = "", tone = Tone.GOOD, reading = Texts.matchKarma(), titleIsKanji = false),
        )
    }
    if (!MatchReckoning.karma(man, woman)) {
        MatchReckoning.kinds(man, woman).groupBy({ it.second }, { it.first }).toSortedMap().forEach { (kind, forces) ->
            add(
                Annotation(
                    title = stringResource(R.string.birth_share_title, gloss(kind).replaceFirstChar(Char::uppercase), forces.joinToString(", ") { gloss(it) }),
                    english = "",
                    tone = when (kind) {
                        SpouseKind.KARMA -> Tone.GOOD
                        SpouseKind.KIN -> Tone.MIXED
                        SpouseKind.ENMITY -> Tone.BAD
                    },
                    reading = Texts.spouseKind(kind),
                    titleIsKanji = false,
                ),
            )
        }
    }
    val mewaName = { n: Int -> labels.string(R.string.match_mewa_name, n, Catalog.text("Colour.${SME_BA_COLOURS[n - 1]}")) }
    listOf(false to R.string.match_section_hidden, true to R.string.match_section_deep_hidden).forEach { (deeper, section) ->
        section(stringResource(section))
        MatchReckoning.hidden(m.manYear, m.womanYear, deeper).forEach { (force, mewas, wheel) ->
            add(
                Annotation(
                    title = stringResource(R.string.birth_share_title, gloss(force).replaceFirstChar(Char::uppercase), gloss(wheel)),
                    english = "",
                    tone = if (wheel.good) Tone.GOOD else Tone.BAD,
                    reading = Texts.matchHidden(wheel, deeper),
                    subtitle = labels.string(R.string.match_aspect_subtitle, mewaName(mewas.first), mewaName(mewas.second)),
                    titleIsKanji = false,
                ),
            )
        }
    }
    section(stringResource(R.string.match_section_destiny))
    add(
        Annotation(
            title = stringResource(R.string.match_destiny_title),
            english = "",
            tone = Tone.NEUTRAL,
            reading = Texts.matchDestiny(MatchReckoning.destinyPair(man.destiny, woman.destiny)),
            subtitle = labels.string(R.string.match_aspect_subtitle, gloss(man.destiny, "inText"), gloss(woman.destiny, "inText")),
            titleIsKanji = false,
        ),
    )
    m.weekdays?.let { (a, b) -> MatchReckoning.samePlanet(a, b) }?.let { p ->
        add(
            Annotation(
                title = stringResource(R.string.match_planet_title, gloss(m.weekdays!!.first, "inText")),
                english = "",
                tone = Tone.BAD,
                reading = Texts.matchPlanet(p),
                titleIsKanji = false,
            ),
        )
    }
    val niSha = MatchReckoning.niSha(m.manYear, m.man.animal, m.womanYear, m.woman.animal)
    if (niSha != null) {
        add(
            Annotation(
                title = stringResource(if (niSha == NiSha.GOOD) R.string.match_nisha_good else R.string.match_nisha_reversed),
                english = "",
                tone = if (niSha == NiSha.GOOD) Tone.GOOD else Tone.BAD,
                reading = Texts.matchNiSha(niSha),
                titleIsKanji = false,
            ),
        )
    }
    section(stringResource(R.string.match_section_siblings))
    val (manSiblings, womanSiblings) = MatchReckoning.siblings(m.manYear) to MatchReckoning.siblings(m.womanYear)
    val win = MatchReckoning.siblingsWin(manSiblings, womanSiblings)
    add(
        Annotation(
            title = stringResource(R.string.match_siblings_title, gloss(win)),
            english = "",
            tone = when (win) { SiblingsWin.EQUAL -> Tone.GOOD; SiblingsWin.MAN -> Tone.NEUTRAL; SiblingsWin.WOMAN -> Tone.BAD },
            reading = Texts.matchSiblings(win),
            subtitle = labels.string(R.string.match_siblings_subtitle, manSiblings, womanSiblings),
            titleIsKanji = false,
        ),
    )
    val agree = MatchReckoning.siblingsAgree(manSiblings, womanSiblings)
    add(
        Annotation(
            title = stringResource(R.string.match_siblings_title, stringResource(if (agree) R.string.match_siblings_agree else R.string.match_siblings_disagree)),
            english = "",
            tone = if (agree) Tone.GOOD else Tone.BAD,
            reading = Texts.matchSiblingsAgree(agree),
            subtitle = labels.string(R.string.match_siblings_together, manSiblings + womanSiblings),
            titleIsKanji = false,
        ),
    )
    section(stringResource(R.string.match_section_rise))
    listOf(
        Triple(R.string.match_rise_man, man.vitality, m.woman),
        Triple(R.string.match_rise_woman, woman.vitality, m.man),
    ).forEach { (whose, vitality, other) ->
        val stage = MatchReckoning.riseDecline(vitality, other.animal)
        add(
            Annotation(
                title = stringResource(R.string.birth_share_title, stringResource(whose), stage.english),
                english = "",
                tone = when (matchStageGrade(stage)) { true -> Tone.GOOD; false -> Tone.BAD; null -> Tone.MIXED },
                reading = Texts.matchRiseDecline(stage),
                subtitle = labels.string(R.string.match_rise_subtitle, gloss(vitality, "inText"), signName(other, labels)),
                titleIsKanji = false,
            ),
        )
    }
    section(stringResource(R.string.match_section_parents))
    val parentRows = listOf(
        Triple(R.string.match_man_father, m.manParents.first to MatchParent.FATHER, m.man),
        Triple(R.string.match_man_mother, m.manParents.second to MatchParent.MOTHER, m.man),
        Triple(R.string.match_woman_father, m.womanParents.first to MatchParent.FATHER, m.woman),
        Triple(R.string.match_woman_mother, m.womanParents.second to MatchParent.MOTHER, m.woman),
    ).filter { it.second.first != null }
    parentRows.forEach { (whose, parent, own) ->
        val (sign, which) = parent
        ParentWay.entries.forEach { way ->
            val stage = MatchReckoning.parentStage(way, sign!!, own.animal)
            add(
                Annotation(
                    title = labels.string(
                        R.string.match_parent_title, stringResource(whose),
                        stringResource(if (way == ParentWay.OUTER) R.string.match_way_outer else R.string.match_way_secret), stage.english,
                    ),
                    english = "",
                    tone = if (stage.good) Tone.GOOD else Tone.BAD,
                    reading = Texts.matchParentStage(which, stage.good),
                    subtitle = labels.string(R.string.match_rise_subtitle, signName(sign, labels), signName(own, labels)),
                    titleIsKanji = false,
                ),
            )
        }
    }
    if (parentRows.isEmpty()) note(stringResource(R.string.match_parents_needs))
    val manMewa = YearOfLife.yearSmeBa(m.manYear)
    val womanMewa = YearOfLife.yearSmeBa(m.womanYear)
    section(stringResource(R.string.match_section_mewa))
    MatchReckoning.mewaMeetings(manMewa, womanMewa).forEach { meeting ->
        add(
            Annotation(
                title = stringResource(R.string.match_mewa_title, gloss(meeting)),
                english = "",
                tone = if (meeting == MatchMewa.MOTHER_SON) Tone.GOOD else Tone.BAD,
                reading = Texts.matchMewa(meeting),
                subtitle = labels.string(R.string.match_aspect_subtitle, mewaName(manMewa), mewaName(womanMewa)),
                titleIsKanji = false,
            ),
        )
    }
    if (MatchReckoning.mewaMeetings(manMewa, womanMewa).isEmpty()) {
        note(labels.string(R.string.match_mewa_none, mewaName(manMewa), mewaName(womanMewa)))
    }
    section(stringResource(R.string.match_section_trigram))
    val (manTrigram, womanTrigram) = m.manTrigram to m.womanTrigram
    if (manTrigram == null || womanTrigram == null) {
        note(stringResource(R.string.match_trigram_needs))
    } else {
        val subtitle = labels.string(R.string.match_aspect_subtitle, gloss(manTrigram), gloss(womanTrigram))
        val kind = MatchReckoning.trigramKind(manTrigram, womanTrigram)
        val reading = Texts.matchTrigram(manTrigram, womanTrigram)
        if (kind == null || reading == null) {
            note(subtitle)
        } else {
            add(
                Annotation(
                    title = stringResource(R.string.match_trigram_title, gloss(kind)),
                    english = "",
                    tone = if (kind.good) Tone.GOOD else Tone.BAD,
                    reading = reading,
                    subtitle = subtitle,
                    titleIsKanji = false,
                ),
            )
        }
        Texts.matchTrigramChildren(manTrigram, womanTrigram)?.let { r ->
            add(
                Annotation(title = stringResource(R.string.match_trigram_children), english = "", tone = Tone.NEUTRAL, reading = r, subtitle = subtitle, titleIsKanji = false))
        }
        MatchReckoning.trigramLines(m.man, manTrigram, m.woman, womanTrigram).forEach { l ->
            add(
                Annotation(
                    title = stringResource(R.string.match_trigram_title, gloss(l)),
                    english = "",
                    tone = Tone.BAD,
                    reading = Texts.matchTrigramLine(l),
                    subtitle = subtitle,
                    titleIsKanji = false,
                ),
            )
        }
        listOf(Triple(R.string.match_man_year, m.man, manTrigram), Triple(R.string.match_woman_year, m.woman, womanTrigram)).forEach { (whose, sign, natal) ->
            if (MatchReckoning.handWaver(sign.animal, natal)) {
                add(
                    Annotation(
                        title = stringResource(R.string.birth_share_title, stringResource(whose), stringResource(R.string.match_hand_waver)),
                        english = "",
                        tone = Tone.BAD,
                        reading = Texts.matchHandWaver(natal),
                        subtitle = labels.string(R.string.match_hand_waver_subtitle, signName(sign, labels), gloss(natal)),
                        titleIsKanji = false,
                    ),
                )
            }
        }
    }
    val kinds = MatchReckoning.kinds(man, woman)
    val remedies = items.filterIsInstance<Annotation>().filter { it.tone == Tone.BAD || it.tone == Tone.MIXED }
        .mapNotNull { it.reading }.flatMap { it.rowKeys }.distinct().filter { Catalog.textOrNull("$it.remedy") != null }
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text(
            labels.string(R.string.match_subtitle, signName(m.man, labels), signName(m.woman, labels)),
            style = body.copy(fontSize = 14.sp, color = Palette.muted, lineHeight = 20.sp),
        )
        Section(stringResource(R.string.match_section_together))
        val whole = MatchReckoning.together(kinds)
        AnnotationRow(
            Annotation(
                title = stringResource(R.string.birth_share_title, stringResource(R.string.match_together_title), gloss(whole)),
                english = "",
                tone = when (whole) {
                    MatchWhole.KARMA -> Tone.GOOD
                    MatchWhole.KIN, MatchWhole.MIXED -> Tone.MIXED
                    MatchWhole.ENMITY -> Tone.BAD
                },
                reading = Texts.matchTogether(whole, remedies),
                subtitle = kinds.groupBy({ it.second }, { it.first }).toSortedMap().entries
                    .joinToString(" · ") { (k, fs) -> labels.string(R.string.birth_share_title, gloss(k), fs.joinToString(", ") { gloss(it) }) },
                titleIsKanji = false,
            ),
            onOpen,
        )
        items.forEach {
            when (it) {
                is MatchHead -> Section(it.title)
                is MatchNote -> Text(it.text, style = muted)
                is Annotation -> AnnotationRow(it, onOpen)
            }
        }
        Text(stringResource(R.string.match_note), style = muted)
    }
}

private class MatchHead(val title: String)

private class MatchNote(val text: String)

/** The tone of one element's lot by the sexes of the two years (p. 353). */
private fun sameElementTone(e: Element, s: YearSexes): Tone = when {
    s == YearSexes.MIXED && (e == Element.WATER || e == Element.EARTH) -> Tone.GOOD
    e == Element.FIRE && s == YearSexes.FEMALE_FEMALE -> Tone.GOOD
    e == Element.EARTH && s == YearSexes.FEMALE_FEMALE -> Tone.NEUTRAL
    s == YearSexes.MIXED || (e == Element.EARTH && s == YearSexes.MALE_MALE) -> Tone.MIXED
    else -> Tone.BAD
}
