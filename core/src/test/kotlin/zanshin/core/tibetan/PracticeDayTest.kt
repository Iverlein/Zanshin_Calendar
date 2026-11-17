/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import java.time.LocalDate
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import zanshin.core.time.julianDayNumber

/** The practice days of the month and where a doubled or skipped date keeps them (SPEC §5.8). */
class PracticeDayTest {
    private val days: List<TibetanDay> by lazy {
        val from = LocalDate.of(2020, 1, 1).julianDayNumber()
        val to = LocalDate.of(2040, 1, 1).julianDayNumber()
        (from until to).map { TibetanCalendar.of(it) }
    }

    @Test
    fun `the eight days and the two small ones`() {
        assertEquals(listOf(8, 9, 10, 15, 24, 25, 29, 30), SpecialDay.entries.map { it.day })
        assertEquals(setOf(SpecialDay.NINTH, SpecialDay.TWENTY_FOURTH), SpecialDay.entries.filter { it.minor }.toSet())
    }

    @Test
    fun `a day keeps its own date's practice day, a doubled date on its first day only`() {
        for (d in days) {
            val own = SpecialDay.of(d.day)
            when {
                d.repetition == Repetition.SECOND_OF_TWO -> assertNull(d.specialDay, "${d.jd}")
                d.specialDayMovedFrom == null -> assertEquals(own, d.specialDay, "${d.jd}")
                else -> assertEquals(d.day + 1, d.specialDayMovedFrom, "${d.jd}")
            }
        }
    }

    @Test
    fun `a skipped date's day is kept on the day it ends in, and every major day falls once a month`() {
        val moved = days.filter { it.specialDayMovedFrom != null }
        assertTrue(moved.isNotEmpty())
        for (d in moved) {
            val next = TibetanCalendar.of(d.jd + 1)
            // The date after the day's own is skipped: tomorrow is two dates on.
            assertEquals(d.day + 2, if (next.day == 1) 31 else next.day, "${d.jd}")
            assertEquals(SpecialDay.of(d.day + 1), d.specialDay)
        }
        // Every month has each major day once, but the 29th when the new moon is skipped into its day.
        for ((_, month) in days.groupBy { it.monthCount to it.leapMonth }) {
            if (month.first().day > 1 || month.last().day < 29) continue // cut by the range
            for (s in SpecialDay.entries.filter { !it.minor }) {
                val count = month.count { it.specialDay == s }
                val lost = s == SpecialDay.TWENTY_NINTH && month.any { it.specialDay == SpecialDay.NEW_MOON && it.specialDayMovedFrom == 30 }
                assertEquals(if (lost) 0 else 1, count, "$s in month ${month.first().jd}")
            }
        }
    }
}
