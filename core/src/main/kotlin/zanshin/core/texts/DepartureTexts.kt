/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.texts

import zanshin.core.tibetan.DepartureFinding
import zanshin.core.tibetan.DeparturePart

/**
 * The readings of the bride's departure (ROADMAP P3, SPEC §5.9.3; WB vol. 1, ch. 23, pp. 365–377,
 * docs/sources/marriage.md): one reading per finding, its paragraph `reading.Departure.<PART>.<case>`, its *Remedy* row
 * beside it or shared by its part (`reading.Departure.<PART>.remedy`), its *How it falls* the engine's line. An earth
 * lord the departure names reads what ch. 23 says of it (`reading.Departure.EARTH_LORDS.<lord>`), with the lord's own
 * reading's rows after it.
 */
object DepartureTexts {
    fun reading(f: DepartureFinding): Reading {
        val key = f.note ?: f.reading
        val shared = f.remedy?.takeIf { it != "${f.reading}.remedy" }?.removeSuffix(".remedy")
        val also = listOfNotNull(f.reading.takeIf { f.note != null }, shared)
        return Reading(
            source = Sources.WHITE_BERYL_DEPARTURE,
            key = key,
            falls = listOfNotNull(f.falls),
            alsoRows = also,
        )
    }

    /** The part's name, `DeparturePart.<PART>`. */
    fun part(p: DeparturePart): String = gloss(p)
}
