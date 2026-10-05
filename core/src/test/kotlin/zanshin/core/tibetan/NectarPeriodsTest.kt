/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** The kun phan me long's tables of Jupiter's nectar periods (§10, img. 81–82), read on the scan. */
class NectarPeriodsTest {
    @Test
    fun `the nectar cells of both tables, weekday by weekday`() {
        // The columns of each table in which the row writes བདུད་རྩི, counted from 1: twelve half-periods
        // by day from the first half of dawn, twelve by night from the first half of sunset.
        val read = mapOf(
            Weekday.SUNDAY to (listOf(6) to listOf(2, 9)),
            Weekday.MONDAY to (listOf(3, 10) to listOf(7)),
            Weekday.TUESDAY to (listOf(7) to listOf(5, 12)),
            Weekday.WEDNESDAY to (listOf(4, 11) to listOf(3, 10)),
            Weekday.THURSDAY to (listOf(1, 8) to listOf(1, 8)),
            Weekday.FRIDAY to (listOf(5, 12) to listOf(6)),
            Weekday.SATURDAY to (listOf(2, 9) to listOf(4, 11)),
        )
        for ((weekday, cells) in read) {
            val (day, night) = cells
            assertEquals(day.map { it - 1 } + night.map { it + 11 }, nectarHours(weekday), "$weekday")
        }
    }
}
