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
import zanshin.app.Reckonings
import zanshin.core.kyureki.Tone
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.Modifier
import zanshin.core.texts.Texts
import zanshin.core.texts.gloss
import zanshin.core.tibetan.Ewts
import zanshin.core.tibetan.Gender
import zanshin.core.tibetan.Sign
import zanshin.core.tibetan.SmallReckonings
import zanshin.core.tibetan.Round
import zanshin.core.tibetan.Rounds
import zanshin.core.tibetan.StartingCheck
import zanshin.core.tibetan.YearOfLife

/**
 * Ch. 27's small reckonings (SPEC §5.9.9, ROADMAP R6) of [person] in the Tibetan year [year]: the four starting points
 * of p. 17 with their checks, what the sky year does to the la, the grave entered, the end of the womb, and the
 * reading of the birth by the year's power. A note on what is reckoned and what is left out closes it.
 */
@Composable
fun SmallReckoningsView(person: Person, year: Int, onOpen: (Annotation) -> Unit) {
    val labels = LocalLabels.current
    val muted = body.copy(fontSize = 13.sp, color = Palette.muted, lineHeight = 19.sp)
    fun name(s: Sign) = labels.string(R.string.element_animal, gloss(s.element), gloss(s.animal))
    val birth = Reckonings.birthSign(person)
    val age = YearOfLife.age(Reckonings.birthYear(person), year)
    val risen = YearOfLife.yearSign(year)
    val gender = person.gender
    val trigramOfYear = gender?.let { YearOfLife.currentTrigram(it, age) }
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text(
            labels.string(R.string.circles_subtitle, personLabel(person), labels.ordinal(age), name(birth), gloss(birth.forces.destiny, "inText")),
            style = body.copy(fontSize = 14.sp, color = Palette.muted, lineHeight = 20.sp),
        )

        // The four starting points of p. 17, each check a part: a cut inward with its reading, else not cut.
        SectionTitle(stringResource(R.string.small_section_points))
        SmallReckonings.startingPoints(birth, age).forEach { r ->
            val marks = labels.string(R.string.small_counted, name(r.start), name(r.reached)) + " · " +
                StartingCheck.entries.joinToString(" ") { if (it in r.cut) "×" else "○" }
            val details = StartingCheck.entries.map { c ->
                c.english.replaceFirstChar(Char::uppercase) to labels.string(
                    R.string.small_check_detail,
                    labels.string(R.string.small_check_year, name(r.checks.getValue(c)), gloss(c.force)),
                    stringResource(if (c in r.cut) R.string.small_cut else R.string.small_not_cut),
                )
            }
            if (r.cut.isEmpty()) {
                // No check cuts inward: nothing to read, the row only states it.
                QuietRow(r.point.english.replaceFirstChar(Char::uppercase), marks + " · " + stringResource(R.string.small_none_cut))
            } else {
                val parts = StartingCheck.entries.filter { it in r.cut }.map { c ->
                    Annotation(
                        title = labels.string(R.string.small_check_title, c.english.replaceFirstChar(Char::uppercase), stringResource(R.string.small_cut)),
                        english = labels.string(R.string.small_check_year, name(r.checks.getValue(c)), gloss(c.force)),
                        tone = Tone.BAD,
                        reading = Texts.startingCut(r.point, c),
                        titleIsKanji = false,
                    )
                }
                AnnotationRow(
                    Annotation(
                        title = r.point.english.replaceFirstChar(Char::uppercase),
                        english = labels.string(R.string.small_counted, name(r.start), name(r.reached)),
                        tone = Tone.BAD,
                        reading = null,
                        subtitle = marks,
                        titleIsKanji = false,
                        details = details,
                        parts = parts,
                    ),
                    onOpen,
                )
            }
        }

        // The rounds by age of pp. 18–19, the man's or the woman's: each check that cuts, or the sign the year reached shows.
        if (gender != null) {
            SectionTitle(stringResource(R.string.small_section_rounds))
            Rounds.all(birth, gender, age).forEach { r ->
                val counted = labels.string(if (r.round.down) R.string.small_counted else R.string.small_counted_up, name(r.start), name(r.reached))
                val marks = if (r.round.checks.isEmpty()) counted else counted + " · " + r.round.checks.joinToString(" ") { if (it in r.cut) "×" else "○" }
                val title = r.round.english.replaceFirstChar(Char::uppercase)
                when {
                    r.cut.isNotEmpty() -> AnnotationRow(
                        Annotation(
                            title = title, english = counted, tone = Tone.BAD, reading = null, subtitle = marks, titleIsKanji = false,
                            parts = r.cut.distinctBy { it.key }.map { c ->
                                Annotation(
                                    title = labels.string(R.string.small_check_title, gloss(c.own).replaceFirstChar(Char::uppercase), stringResource(R.string.small_cut)),
                                    english = labels.string(R.string.small_check_year, name(YearOfLife.signOf(YearOfLife.sexagenary(r.reached) + c.step)), gloss(c.own)),
                                    tone = Tone.BAD, reading = Texts.roundCut(c.key), titleIsKanji = false,
                                )
                            },
                        ),
                        onOpen,
                    )
                    r.sign != null -> AnnotationRow(
                        Annotation(title = title, english = counted, tone = Tone.BAD, reading = Texts.roundSign(r.sign!!), titleIsKanji = false),
                        onOpen,
                    )
                    else -> QuietRow(title, marks + " · " + stringResource(if (r.round.checks.isEmpty()) R.string.small_round_none else R.string.small_none_cut))
                }
                if (r.round == Round.RHINO_MALE && trigramOfYear != null && Rounds.rhinoInner(trigramOfYear)) {
                    AnnotationRow(
                        Annotation(
                            title = stringResource(R.string.small_rhino_inner_title),
                            english = labels.string(R.string.small_rhino_inner_line, Ewts.named(trigramOfYear.english, trigramOfYear.wylie)),
                            tone = Tone.BAD, reading = Texts.roundSign("RHINO.INNER"), titleIsKanji = false,
                        ),
                        onOpen,
                    )
                }
            }
        }

        // What the sky year does to this person: the la, the grave, the womb (pp. 16–17, 20).
        SectionTitle(labels.string(R.string.small_section_year, name(risen)))
        val trigram = trigramOfYear
        val natal = Reckonings.birth(person)?.natal
        val wander = SmallReckonings.laWander(birth.animal, risen.animal)
        val decline = SmallReckonings.laDecline(birth.animal, risen.animal)
        val grave = trigram?.let { SmallReckonings.graveEntered(birth.forces.vitality, it) }
        val womb = if (natal != null && trigram != null) SmallReckonings.wombEnd(natal, trigram, risen.animal) else null
        wander?.let {
            AnnotationRow(
                Annotation(
                    title = labels.string(R.string.small_la_wanders, gloss(it.direction)),
                    english = labels.string(R.string.small_born_in, gloss(birth.animal), name(risen)),
                    tone = Tone.BAD, reading = Texts.laWander(it), titleIsKanji = false,
                ),
                onOpen,
            )
        }
        decline?.let {
            AnnotationRow(
                Annotation(
                    title = stringResource(R.string.small_la_declines),
                    english = labels.string(R.string.small_born_in, gloss(birth.animal), name(risen)),
                    tone = Tone.BAD, reading = Texts.laDecline(it), titleIsKanji = false,
                ),
                onOpen,
            )
        }
        if (grave != null && trigram != null) {
            val gentle = gender?.let { SmallReckonings.graveGentle(grave, it) }
            AnnotationRow(
                Annotation(
                    title = labels.string(R.string.small_grave_title, gloss(grave)),
                    english = labels.string(R.string.small_grave_line, Ewts.named(trigram.english, trigram.wylie), gloss(birth.forces.vitality, "inText")),
                    tone = if (gentle == true) Tone.MIXED else Tone.BAD,
                    reading = Texts.graveEntered(grave, gender), titleIsKanji = false,
                ),
                onOpen,
            )
        }
        womb?.let {
            AnnotationRow(
                Annotation(
                    title = stringResource(R.string.small_womb_title),
                    english = labels.string(R.string.small_womb_line, Ewts.named(it.trigram.english, it.trigram.wylie), name(risen)),
                    tone = Tone.BAD, reading = Texts.wombEnd(it), titleIsKanji = false,
                ),
                onOpen,
            )
        }
        if (wander == null && decline == null && grave == null && womb == null) Text(stringResource(R.string.small_year_none), style = muted)
        if (gender == null) Text(stringResource(R.string.small_gender_needed), style = muted)
        else if (natal == null) Text(stringResource(R.string.small_mother_needed), style = muted)

        // The birth by the sky year's power (p. 17): the person's own birth, by their birth year's element and sex.
        if (gender != null && SmallReckonings.birthPower(birth.element)) {
            SectionTitle(stringResource(R.string.small_section_birth))
            AnnotationRow(
                Annotation(
                    title = labels.string(R.string.small_birth_title, stringResource(if (gender == Gender.MALE) R.string.small_son else R.string.small_daughter), gloss(birth.element, "inText")),
                    english = name(birth),
                    tone = Tone.NEUTRAL, reading = Texts.birthPower(birth.element, gender), titleIsKanji = false,
                ),
                onOpen,
            )
        }
        Text(stringResource(R.string.small_note), style = muted)
    }
}

/** A row with nothing to read: a good dot, the title and a line, not tappable. */
@Composable
private fun QuietRow(title: String, line: String) {
    Row(Modifier.padding(vertical = 6.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        ToneDot(Tone.GOOD, false)
        Column(Modifier.weight(1f)) {
            Text(title, style = body.copy(fontSize = 16.sp, fontWeight = FontWeight.Medium))
            Text(line, style = body.copy(fontSize = 13.sp, lineHeight = 18.sp, color = Palette.muted))
        }
    }
}
