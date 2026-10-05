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

    @Test
    fun `Rāhu's course by month falls on the dates WB gives`() {
        val rahu = zanshin.core.texts.Texts.RAHU_MONTH
        assertEquals(setOf(6, 9, 11, 13), rahu.keys.filter { it.first == 1 }.map { it.second }.toSet())
        assertEquals(setOf(9, 11, 13, 16, 19), rahu.keys.filter { it.first == 2 }.map { it.second }.toSet())
        for (month in 7..9) assertEquals(setOf(4, 8, 11, 15, 22, 25, 29), rahu.keys.filter { it.first == month }.map { it.second }.toSet())
        assertEquals(setOf(1, 2, 7, 8, 9), rahu.keys.map { it.first }.toSet())
    }
}
