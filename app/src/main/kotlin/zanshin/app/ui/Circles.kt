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
import zanshin.core.tibetan.CircleReading
import zanshin.core.tibetan.Force
import zanshin.core.tibetan.PebbleColour
import zanshin.core.tibetan.Sign

/**
 * The thirteen circles (SPEC §5.9.6, ROADMAP R2u) of [person] in their [age]th year of age, born in [birth]: a row for
 * each circle, its four pebbles drawn as the charts draw them (○ ×) with the starting year and the year reached; its
 * sheet gives each pebble's reading in turn, a black one's remedy as its row. A note on what is reckoned closes it.
 */
@Composable
fun CirclesView(person: Person, birth: Sign, age: Int, circles: List<CircleReading>, onOpen: (Annotation) -> Unit) {
    val labels = LocalLabels.current
    val muted = body.copy(fontSize = 13.sp, color = Palette.muted, lineHeight = 19.sp)
    fun name(s: Sign) = labels.string(R.string.element_animal, gloss(s.element), gloss(s.animal))
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text(
            labels.string(R.string.circles_subtitle, personLabel(person), labels.ordinal(age), name(birth), gloss(birth.forces.destiny, "inText")),
            style = body.copy(fontSize = 14.sp, color = Palette.muted, lineHeight = 20.sp),
        )
        circles.forEach { c ->
            val tone = { p: PebbleColour -> if (p == PebbleColour.WHITE) Tone.GOOD else Tone.BAD }
            val parts = Force.entries.map { f ->
                val p = c.pebbles.getValue(f)
                Annotation(
                    title = labels.string(R.string.birth_share_title, f.english.replaceFirstChar(Char::uppercase), stringResource(if (p == PebbleColour.WHITE) R.string.birth_precious_white else R.string.birth_precious_black)),
                    english = "",
                    tone = tone(p),
                    reading = Texts.circlePebble(c.circle, f, p),
                    titleIsKanji = false,
                )
            }
            AnnotationRow(
                Annotation(
                    title = c.circle.english.replaceFirstChar(Char::uppercase),
                    english = labels.string(R.string.circles_counted, name(c.start), name(c.reached)),
                    tone = sharedTone(parts.map { it.tone }),
                    reading = null,
                    subtitle = Force.entries.joinToString(" · ") { f -> "${gloss(f)} " + if (c.pebbles.getValue(f) == PebbleColour.WHITE) "○" else "×" },
                    titleIsKanji = false,
                    parts = parts,
                ),
                onOpen,
            )
        }
        Text(stringResource(R.string.circles_note), style = muted)
    }
}
