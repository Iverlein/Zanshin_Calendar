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
        }
        if (dead.isNotEmpty()) Text(stringResource(R.string.dead_note), style = muted)
    }
}
