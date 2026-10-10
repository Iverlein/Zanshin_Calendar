/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import zanshin.core.vectors

/**
 * The five planets and Rāhu against Edward Henning's software (TCG 1.06), its source compiled
 * unchanged: every place to the last unit, for each day of 2000–2047 and 1,000 months of Rāhu
 * (henning-tcg-planets.tsv, henning-tcg-rahu.tsv; docs/sources/day-letters.md). A few rows stand
 * here too, so the test runs without the files.
 */
class PlanetsTest {

    @Test
    fun `the planets of 10 to 12 October 2026 are Henning's`() {
        assertEquals(
            mapOf(Planet.MARS to "7;19,28,3,130", Planet.JUPITER to "9;7,45,5,243", Planet.SATURN to "25;13,21,4,3534", Planet.MERCURY to "14;6,14,3,3114", Planet.VENUS to "13;52,56,1,305"),
            Planets.printed(2461324),
        )
        assertEquals("22;25,34,4,23", Planets.printed(2451545).getValue(Planet.MARS))
        assertEquals(mapOf(Planet.MARS to 7, Planet.JUPITER to 9, Planet.SATURN to 25, Planet.MERCURY to 14, Planet.VENUS to 13), Planets.places(2461324))
    }

    @Test
    fun `every day's planets are Henning's to the last unit`() {
        var n = 0
        for (row in vectors("henning-tcg-planets.tsv")) {
            val p = Planets.printed(row[0].toLong())
            assertEquals(row.drop(1).take(5), Planet.entries.map { p.getValue(it) }, "JD ${row[0]}")
            n++
        }
        assertTrue(n == 0 || n > 17000)
    }

    @Test
    fun `Rahu's head is Henning's on the 15th and the 30th`() {
        // WB's own constant (vol. 1, p. 34): the 3rd month of 1687, the 12th rab byung's first, is Rāhu's 209th;
        // carried to the 3rd month of 1987 it is the 10th (the 1996 editors' bracket, the 17th rab byung, would put 209 there).
        assertEquals(209, Planets.rahuMonth(Phugpa.trueMonthCount(1687, 3)))
        assertEquals(10, Planets.rahuMonth(Phugpa.trueMonthCount(1987, 3)))
        // His example month: 37239, the leap 11th of 2010.
        assertEquals(37239L, Phugpa.trueMonthCount(2010, 11, true) + Planets.MONTH_OFFSET)
        assertEquals("18;29,20,5,5", Planets.printedRahu(37239 - Planets.MONTH_OFFSET, 15))
        assertEquals("18;25,49,3,9", Planets.printedRahu(37239 - Planets.MONTH_OFFSET, 30))
        for (row in vectors("henning-tcg-rahu.tsv")) {
            val n = row[0].toLong() - Planets.MONTH_OFFSET
            assertEquals(row[1], Planets.printedRahu(n, 15), "month ${row[0]}")
            assertEquals(row[2], Planets.printedRahu(n, 30), "month ${row[0]}")
        }
    }
}
