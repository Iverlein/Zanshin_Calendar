/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.github.iverlein.zanshin.R
import zanshin.core.texts.DepartureTexts
import zanshin.core.kyureki.Tone
import zanshin.core.texts.gloss
import zanshin.core.tibetan.Departure
import zanshin.core.tibetan.DepartureDay
import zanshin.core.tibetan.DepartureFinding
import zanshin.core.tibetan.DepartureScope
import zanshin.core.tibetan.DepartureTone

/** A finding's tone as the page's dots show it. */
fun tone(t: DepartureTone): Tone = when (t) {
    DepartureTone.GOOD -> Tone.GOOD
    DepartureTone.MIDDLING -> Tone.MIXED
    DepartureTone.BAD -> Tone.BAD
}

/**
 * The bride's going on one day for the couple (ROADMAP P3, SPEC §5.9.3): one row whose dot is the day's verdict over
 * the month's and the day's findings, its second line that verdict and the year's; its sheet every finding in turn,
 * the day's and the month's first, then the year's, then those shown and not weighed.
 */
@Composable
fun departureAnnotation(d: DepartureDay): Annotation {
    val notWeighed = stringResource(R.string.departure_not_weighed)
    fun part(f: DepartureFinding) = Annotation(
        DepartureTexts.part(f.part),
        gloss(f.scope),
        tone(f.tone),
        DepartureTexts.reading(f),
        titleIsKanji = false,
        note = if (f.weighed) null else notWeighed,
    )
    val order = d.findings.sortedBy {
        when {
            !it.weighed -> 3
            it.scope == DepartureScope.YEAR -> 2
            else -> 1
        }
    }
    return Annotation(
        stringResource(R.string.departure_title),
        stringResource(R.string.departure_note),
        tone(d.dayVerdict),
        null,
        subtitle = stringResource(R.string.departure_subtitle, toneLabel(tone(d.dayVerdict)), toneLabel(tone(Departure.verdict(d.yearFindings)))),
        titleIsKanji = false,
        parts = order.map(::part),
    )
}
