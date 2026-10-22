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
import zanshin.core.kyureki.Tone
import zanshin.core.texts.Catalog
import zanshin.core.texts.Texts
import zanshin.core.tibetan.Ewts
import zanshin.core.tibetan.MomentReckoning
import zanshin.core.tibetan.SME_BA_COLOURS
import zanshin.core.tibetan.TibetanDay

/**
 * The reckoning of the moment (SPEC §5.9.5, ROADMAP R2o) for [moment], the Tibetan day and the minutes since its dawn:
 * the moment's trigram and mewa, each opening its reading, and a note on what is reckoned.
 */
@Composable
fun MomentReckoningView(moment: Pair<TibetanDay, Int>, onOpen: (Annotation) -> Unit) {
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
        Text(stringResource(R.string.moment_note), style = body.copy(fontSize = 13.sp, color = Palette.muted, lineHeight = 19.sp))
    }
}
