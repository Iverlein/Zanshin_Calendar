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
import zanshin.core.tibetan.Element
import zanshin.core.tibetan.Forces
import zanshin.core.tibetan.MatchMeeting
import zanshin.core.tibetan.MatchMewa
import zanshin.core.tibetan.MatchReckoning
import zanshin.core.tibetan.NiSha
import zanshin.core.tibetan.SME_BA_COLOURS
import zanshin.core.tibetan.YearSexes
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
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text(
            labels.string(R.string.match_subtitle, signName(m.man, labels), signName(m.woman, labels)),
            style = body.copy(fontSize = 14.sp, color = Palette.muted, lineHeight = 20.sp),
        )
        Section(stringResource(R.string.match_section_years))
        MatchReckoning.sameKind(m.man, m.woman)?.let { k ->
            AnnotationRow(
                Annotation(
                    title = stringResource(R.string.match_same_kind_title, k.english),
                    english = "",
                    tone = Tone.BAD,
                    reading = Texts.matchSameKind(k),
                    titleIsKanji = false,
                ),
                onOpen,
            )
        }
        val sameAnimal = MatchReckoning.sameAnimal(m.man, m.woman)
        sameAnimal?.let { a ->
            AnnotationRow(
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
                onOpen,
            )
        }
        val sameElement = MatchReckoning.sameElement(m.man, m.woman)?.let { s -> Texts.matchSameElement(m.man.element, s)?.let { s to it } }
        sameElement?.let { (s, reading) ->
            AnnotationRow(
                Annotation(
                    title = stringResource(R.string.match_same_element_title, gloss(m.man.element)),
                    english = "",
                    tone = sameElementTone(m.man.element, s),
                    reading = reading,
                    subtitle = gloss(s),
                    titleIsKanji = false,
                ),
                onOpen,
            )
        }
        val pairLists = MatchReckoning.pairLists(m.man.animal, m.woman.animal)
        val pairLines = MatchReckoning.pairLines(m.man.animal, m.woman.animal)
        pairLists.forEach { l ->
            AnnotationRow(
                Annotation(
                    title = stringResource(R.string.match_pair_list_title, gloss(l)),
                    english = "",
                    tone = if (l.good) Tone.GOOD else Tone.BAD,
                    reading = Texts.matchPairList(l),
                    titleIsKanji = false,
                ),
                onOpen,
            )
        }
        pairLines.forEach { l ->
            AnnotationRow(
                Annotation(
                    title = stringResource(R.string.match_pair_line_title, gloss(m.man.animal), gloss(m.woman.animal)),
                    english = "",
                    tone = when (l.good) { true -> Tone.GOOD; false -> Tone.BAD; null -> Tone.MIXED },
                    reading = Texts.matchPairLine(l),
                    subtitle = stringResource(if (l.ordered) R.string.match_pair_line_takes else R.string.match_pair_line_either),
                    titleIsKanji = false,
                ),
                onOpen,
            )
        }
        val bad = MatchReckoning.badYears(m.man, YearOfLife.yearSmeBa(m.manYear)).filter { it != BadYear.BLACK_FANGS }.map { R.string.match_man_year to (it to m.man) } +
            MatchReckoning.badYears(m.woman, YearOfLife.yearSmeBa(m.womanYear)).map { R.string.match_woman_year to (it to m.woman) }
        bad.forEach { (whose, pair) ->
            val (y, sign) = pair
            AnnotationRow(
                Annotation(
                    title = stringResource(R.string.birth_share_title, stringResource(whose), y.english),
                    english = "",
                    tone = Tone.BAD,
                    reading = Texts.badYear(y),
                    subtitle = signName(sign, labels),
                    titleIsKanji = false,
                ),
                onOpen,
            )
        }
        if (bad.isEmpty() && MatchReckoning.sameKind(m.man, m.woman) == null && sameAnimal == null && sameElement == null && pairLists.isEmpty() && pairLines.isEmpty()) Text(stringResource(R.string.match_years_none), style = muted)
        Section(stringResource(R.string.match_section_single))
        MatchReckoning.aspects(man, woman).forEach { (force, meeting) ->
            AnnotationRow(
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
                onOpen,
            )
        }
        if (MatchReckoning.karma(man, woman)) {
            AnnotationRow(
                Annotation(title = stringResource(R.string.match_karma_title), english = "", tone = Tone.GOOD, reading = Texts.matchKarma(), titleIsKanji = false),
                onOpen,
            )
        }
        Section(stringResource(R.string.match_section_destiny))
        AnnotationRow(
            Annotation(
                title = stringResource(R.string.match_destiny_title),
                english = "",
                tone = Tone.NEUTRAL,
                reading = Texts.matchDestiny(MatchReckoning.destinyPair(man.destiny, woman.destiny)),
                subtitle = labels.string(R.string.match_aspect_subtitle, gloss(man.destiny, "inText"), gloss(woman.destiny, "inText")),
                titleIsKanji = false,
            ),
            onOpen,
        )
        m.weekdays?.let { (a, b) -> MatchReckoning.samePlanet(a, b) }?.let { p ->
            AnnotationRow(
                Annotation(
                    title = stringResource(R.string.match_planet_title, gloss(m.weekdays!!.first, "inText")),
                    english = "",
                    tone = Tone.BAD,
                    reading = Texts.matchPlanet(p),
                    titleIsKanji = false,
                ),
                onOpen,
            )
        }
        val niSha = MatchReckoning.niSha(m.manYear, m.man.animal, m.womanYear, m.woman.animal)
        if (niSha != null) {
            AnnotationRow(
                Annotation(
                    title = stringResource(if (niSha == NiSha.GOOD) R.string.match_nisha_good else R.string.match_nisha_reversed),
                    english = "",
                    tone = if (niSha == NiSha.GOOD) Tone.GOOD else Tone.BAD,
                    reading = Texts.matchNiSha(niSha),
                    titleIsKanji = false,
                ),
                onOpen,
            )
        }
        val manMewa = YearOfLife.yearSmeBa(m.manYear)
        val womanMewa = YearOfLife.yearSmeBa(m.womanYear)
        val mewaName = { n: Int -> labels.string(R.string.match_mewa_name, n, Catalog.text("Colour.${SME_BA_COLOURS[n - 1]}")) }
        Section(stringResource(R.string.match_section_mewa))
        MatchReckoning.mewaMeetings(manMewa, womanMewa).forEach { meeting ->
            AnnotationRow(
                Annotation(
                    title = stringResource(R.string.match_mewa_title, gloss(meeting)),
                    english = "",
                    tone = if (meeting == MatchMewa.MOTHER_SON) Tone.GOOD else Tone.BAD,
                    reading = Texts.matchMewa(meeting),
                    subtitle = labels.string(R.string.match_aspect_subtitle, mewaName(manMewa), mewaName(womanMewa)),
                    titleIsKanji = false,
                ),
                onOpen,
            )
        }
        if (MatchReckoning.mewaMeetings(manMewa, womanMewa).isEmpty()) {
            Text(labels.string(R.string.match_mewa_none, mewaName(manMewa), mewaName(womanMewa)), style = muted)
        }
        Section(stringResource(R.string.match_section_trigram))
        val (manTrigram, womanTrigram) = m.manTrigram to m.womanTrigram
        if (manTrigram == null || womanTrigram == null) {
            Text(stringResource(R.string.match_trigram_needs), style = muted)
        } else {
            val subtitle = labels.string(R.string.match_aspect_subtitle, gloss(manTrigram), gloss(womanTrigram))
            val kind = MatchReckoning.trigramKind(manTrigram, womanTrigram)
            val reading = Texts.matchTrigram(manTrigram, womanTrigram)
            if (kind == null || reading == null) {
                Text(subtitle, style = muted)
            } else {
                AnnotationRow(
                    Annotation(
                        title = stringResource(R.string.match_trigram_title, gloss(kind)),
                        english = "",
                        tone = if (kind.good) Tone.GOOD else Tone.BAD,
                        reading = reading,
                        subtitle = subtitle,
                        titleIsKanji = false,
                    ),
                    onOpen,
                )
            }
            Texts.matchTrigramChildren(manTrigram, womanTrigram)?.let { r ->
                AnnotationRow(
                    Annotation(title = stringResource(R.string.match_trigram_children), english = "", tone = Tone.NEUTRAL, reading = r, subtitle = subtitle, titleIsKanji = false),
                    onOpen,
                )
            }
            listOf(Triple(R.string.match_man_year, m.man, manTrigram), Triple(R.string.match_woman_year, m.woman, womanTrigram)).forEach { (whose, sign, natal) ->
                if (MatchReckoning.handWaver(sign.animal, natal)) {
                    AnnotationRow(
                        Annotation(
                            title = stringResource(R.string.birth_share_title, stringResource(whose), stringResource(R.string.match_hand_waver)),
                            english = "",
                            tone = Tone.BAD,
                            reading = Texts.matchHandWaver(natal),
                            subtitle = labels.string(R.string.match_hand_waver_subtitle, signName(sign, labels), gloss(natal)),
                            titleIsKanji = false,
                        ),
                        onOpen,
                    )
                }
            }
        }
        Text(stringResource(R.string.match_note), style = muted)
    }
}

/** The tone of one element's lot by the sexes of the two years (p. 353). */
private fun sameElementTone(e: Element, s: YearSexes): Tone = when {
    s == YearSexes.MIXED && (e == Element.WATER || e == Element.EARTH) -> Tone.GOOD
    e == Element.FIRE && s == YearSexes.FEMALE_FEMALE -> Tone.GOOD
    e == Element.EARTH && s == YearSexes.FEMALE_FEMALE -> Tone.NEUTRAL
    s == YearSexes.MIXED || (e == Element.EARTH && s == YearSexes.MALE_MALE) -> Tone.MIXED
    else -> Tone.BAD
}
