/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.time.LocalDate

/**
 * The day's sme ba of the sixty-day count and the *bla mkhyen*'s direction
 * (docs/sources/earth-lords.md, question 11): Blo bzang sbyin pa's stretches
 * between the *sgang*, worked by hand for 2025–2026, and the White Beryl's
 * example (vol. 2, p. 224): the one-white on the first wood-mouse day, the
 * *bla mkhyen* in the south-west.
 */
class DaySmeBaTest {
    private fun d(s: String) = LocalDate.parse(s)

    @Test
    fun `the count's wood-mouse days are the calendar's`() {
        var date = d("2026-01-01")
        while (date.year < 2027) {
            val day = TibetanCalendar.of(date)
            val woodMouse = day.dayElement == Element.WOOD && day.dayAnimal == Animal.MOUSE
            assertEquals(woodMouse, DaySmeBa.of(date).woodMouse == date, "$date")
            date = date.plusDays(1)
        }
    }

    @Test
    fun `the winter solstice of 2025 falls on a wood-mouse day, which takes the one-white`() {
        // Solstice 2025-12-21 15:03 UT, 21:07 in Lhasa mean time; the day is a wood-mouse day.
        val s = DaySmeBa.of(d("2025-12-21"))
        assertEquals(d("2025-12-21"), s.woodMouse)
        assertEquals(1, s.number)
        assertTrue(s.forward)
        assertEquals(Direction.SOUTH_WEST, s.sevenRed, "WB p. 224's example")
        assertEquals(2, DaySmeBa.of(d("2025-12-22")).number)
    }

    @Test
    fun `before it the count runs down from the wood-mouse day just before the ninth sgang`() {
        // 2025-10-22 is a wood-mouse day; the sun reaches 210° only on 23 October, so it is the three-blue.
        val s = DaySmeBa.of(d("2025-10-22"))
        assertEquals(3, s.number)
        assertFalse(s.forward)
        assertEquals(2, DaySmeBa.of(d("2025-10-23")).number)
        assertEquals(7, DaySmeBa.of(d("2025-12-20")).number)
    }

    @Test
    fun `the first spring stretches give the seven-red and the four-green, counting up`() {
        assertEquals(7, DaySmeBa.of(d("2026-02-19")).number) // after the first sgang, 330° on 18 February
        assertEquals(4, DaySmeBa.of(d("2026-04-20")).number) // after the third, 30° on 20 April
        assertEquals(5, DaySmeBa.of(d("2026-04-21")).number) // the wood-ox, five-yellow
        assertEquals(4, DaySmeBa.of(d("2026-06-19")).number) // still before the summer solstice of 21 June
    }

    @Test
    fun `after the summer solstice the wood-mouse day takes the nine-red and the count runs down`() {
        val s = DaySmeBa.of(d("2026-08-18"))
        assertEquals(9, s.number)
        assertFalse(s.forward)
        assertEquals(8, DaySmeBa.of(d("2026-08-19")).number)
        assertEquals(3, DaySmeBa.of(d("2026-10-17")).number) // after the seventh sgang, 150° on 23 August
    }

    @Test
    fun `the seven-red's place in the square`() {
        assertEquals(Direction.WEST, DaySmeBa.directionOf(7, 5))
        assertEquals(Direction.SOUTH_WEST, DaySmeBa.directionOf(7, 1))
        assertEquals(Direction.CENTRE, DaySmeBa.directionOf(7, 7))
        assertEquals(Direction.NORTH, DaySmeBa.directionOf(1, 5))
        // Each centre puts the seven-red in a different place.
        assertEquals(9, (1..9).map { DaySmeBa.directionOf(7, it) }.toSet().size)
    }
}
