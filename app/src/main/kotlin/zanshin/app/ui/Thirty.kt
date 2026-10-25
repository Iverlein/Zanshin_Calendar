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
import zanshin.core.kyureki.Tone
import zanshin.core.texts.Texts
import zanshin.core.texts.gloss
import zanshin.core.texts.sharedTone
import zanshin.core.tibetan.PebbleColour
import zanshin.core.tibetan.Sign
import zanshin.core.tibetan.Thirty
import zanshin.core.tibetan.ThirtyBoard
import zanshin.core.tibetan.ThirtyReading

/**
 * The thirty starting points (SPEC §5.9.7, ROADMAP R2v) of [person] in their [age]th year of age, born in [birth]: a
 * row for each point the asker reads for themself, its eight pebbles drawn ○ × in the order of its checks, the upper,
 * lower and middle groups set apart; its sheet gives each check's reading in turn and then the board read whole.
 */
@Composable
fun ThirtyView(person: Person, birth: Sign, age: Int, points: List<ThirtyReading>, onOpen: (Annotation) -> Unit) {
    val labels = LocalLabels.current
    fun name(s: Sign) = labels.string(R.string.element_animal, gloss(s.element), gloss(s.animal))
    fun drawn(p: List<PebbleColour>) = p.joinToString("") { if (it == PebbleColour.WHITE) "○" else "×" }
    val tone = { p: PebbleColour -> if (p == PebbleColour.WHITE) Tone.GOOD else Tone.BAD }
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text(
            labels.string(R.string.circles_subtitle, personLabel(person), labels.ordinal(age), name(birth), gloss(birth.forces.destiny, "inText")),
            style = body.copy(fontSize = 14.sp, color = Palette.muted, lineHeight = 20.sp),
        )
        points.forEach { r ->
            val checks = r.pebbles.mapIndexed { i, p ->
                Annotation(
                    title = labels.string(
                        R.string.birth_share_title, labels.string(R.string.thirty_check, i + 1),
                        stringResource(if (p == PebbleColour.WHITE) R.string.birth_precious_white else R.string.birth_precious_black),
                    ),
                    english = "",
                    tone = tone(p),
                    reading = Texts.thirtyCheck(r.point, i + 1, p),
                    titleIsKanji = false,
                )
            }
            val board = Thirty.board(r).map { b ->
                Annotation(
                    title = b.english.replaceFirstChar(Char::uppercase),
                    english = "",
                    tone = boardTone(b),
                    reading = Texts.thirtyBoard(b),
                    titleIsKanji = false,
                )
            }
            AnnotationRow(
                Annotation(
                    title = r.point.english.replaceFirstChar(Char::uppercase),
                    english = labels.string(R.string.circles_counted, name(r.start), name(r.reached)),
                    tone = sharedTone(checks.map { it.tone }),
                    reading = null,
                    subtitle = labels.string(R.string.thirty_drawn, drawn(r.upper), drawn(r.lower), drawn(r.middle)),
                    titleIsKanji = false,
                    parts = checks + board,
                ),
                onOpen,
            )
        }
        Text(stringResource(R.string.thirty_note), style = body.copy(fontSize = 13.sp, color = Palette.muted, lineHeight = 19.sp))
    }
}

/**
 * The tone of a board's reading (WB vol. 1, pp. 471–472): good the white groups, oneself risen with the forebears in
 * decline, and the middle white with no spirits; mixed the forebears good and oneself weak; bad the rest, all white
 * too, which brings an unexpected fault.
 */
private fun boardTone(b: ThirtyBoard): Tone = when (b) {
    ThirtyBoard.UPPER_WHITE, ThirtyBoard.LOWER_WHITE, ThirtyBoard.MIDDLE_WHITE, ThirtyBoard.UPPER_BLACK_LOWER_WHITE,
    ThirtyBoard.OUTER_BLACK_MIDDLE_WHITE -> Tone.GOOD
    ThirtyBoard.UPPER_WHITE_LOWER_BLACK -> Tone.MIXED
    else -> Tone.BAD
}
