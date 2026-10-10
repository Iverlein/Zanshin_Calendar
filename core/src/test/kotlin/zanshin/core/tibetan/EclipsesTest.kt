/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import zanshin.core.time.julianDayNumber
import zanshin.core.vectors
import java.time.LocalDate
import kotlin.math.abs

/** WB's eclipses (vol. 1, ch. 9) against the sky. */
class EclipsesTest {

    private fun month(date: LocalDate): Long = TibetanCalendar.of(date).monthCount

    @Test
    fun theEclipsesOf2025And2026() {
        // Total lunar eclipses of 14 March and 7 September 2025 and 3 March 2026, the partial of 28 August 2026,
        // and the total solar eclipse of 12 August 2026.
        assertEquals(Eclipses.End.TAIL, Eclipses.moon(month(LocalDate.of(2025, 3, 14)))!!.end)
        assertEquals(Eclipses.End.HEAD, Eclipses.moon(month(LocalDate.of(2025, 9, 7)))!!.end)
        assertNotNull(Eclipses.moon(month(LocalDate.of(2026, 3, 3))))
        assertNotNull(Eclipses.moon(month(LocalDate.of(2026, 8, 27))))
        assertNotNull(Eclipses.sun(month(LocalDate.of(2026, 8, 12))))
        // Full moons between them with no eclipse.
        assertNull(Eclipses.moon(month(LocalDate.of(2025, 6, 11))))
        assertNull(Eclipses.moon(month(LocalDate.of(2026, 6, 29))))
    }

    @Test
    fun everyEclipseTheRuleMarksIsInTheSky() {
        val rows = vectors("meeus-eclipses.tsv")
        val real = rows.groupBy({ it[0] }, { LocalDate.parse(it[1]).julianDayNumber() })
        val umbral = rows.filter { it[0] == "moon" && it[2].toDouble() > 0.1 }.map { LocalDate.parse(it[1]).julianDayNumber() }
        val first = Phugpa.approximateCount(LocalDate.of(2000, 1, 20).julianDayNumber())
        val last = Phugpa.approximateCount(LocalDate.of(2049, 12, 1).julianDayNumber())
        val moons = (first..last).filter { Eclipses.moon(it) != null }.map { Phugpa.endJd(it, 15) }
        val suns = (first..last).filter { Eclipses.sun(it) != null }.map { Phugpa.endJd(it, 30) }
        fun near(jd: Long, list: List<Long>) = list.any { abs(it - jd) <= 1 }
        assertEquals(emptyList<Long>(), moons.filterNot { near(it, real.getValue("moon")) })
        assertEquals(emptyList<Long>(), suns.filterNot { near(it, real.getValue("sun")) })
        assertEquals(79, moons.size)
        assertEquals(33, suns.size)
        // Of the umbral eclipses deeper than a tenth, only 2001's and 2019's July ones are missed.
        assertEquals(2, umbral.count { !near(it, moons) })
        assertTrue(umbral.size > 60)
    }
}
