/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import zanshin.core.time.SUPPORTED_RANGE
import zanshin.core.time.julianDayNumber
import zanshin.core.vectors
import java.time.LocalDate

class TibetanCalendarTest {

    private fun label(e: Enum<*>) = e.name.lowercase().replaceFirstChar { it.uppercase() }

    @Test
    fun `Losar and year names match Janson Table 1 for 1927-2046`() {
        val rows = vectors("tibetan-losar.tsv")
        assertEquals(120, rows.size)
        for ((year, losar, name) in rows) {
            val y = year.toInt()
            assertEquals(LocalDate.parse(losar), TibetanCalendar.losar(y), "Losar $y")
            val day = TibetanCalendar.of(LocalDate.parse(losar))
            assertEquals(y, day.year, "year of Losar $y")
            assertEquals(name, "${label(day.yearElement)}–${label(day.yearAnimal)}", "year name $y")
            assertEquals("Losar", day.holiday?.name, "Losar holiday $y")
        }
    }

    @Test
    fun `dated examples from Janson's title page`() {
        // 2007-12-31: day 23, month 11, Fire–Pig. Janson calls it a Sunday, but it
        // was a Monday and his own (9.1) gives Monday, so the weekday is not asserted.
        TibetanCalendar.of(LocalDate.of(2007, 12, 31)).run {
            assertEquals(listOf(2007, 11, 23), listOf(year, month, day))
            assertEquals(Element.FIRE to Animal.PIG, yearElement to yearAnimal)
        }
        TibetanCalendar.of(LocalDate.of(2014, 1, 8)).run {
            assertEquals(listOf(2013, 11, 8), listOf(year, month, day))
            assertEquals(Weekday.WEDNESDAY, weekday)
            assertEquals(Element.WATER to Animal.SNAKE, yearElement to yearAnimal)
        }
        TibetanCalendar.of(LocalDate.of(2022, 2, 13)).run {
            assertEquals(listOf(2021, 12, 12), listOf(year, month, day))
            assertEquals(Weekday.SUNDAY, weekday)
            assertEquals(Element.IRON to Animal.OX, yearElement to yearAnimal)
        }
    }

    @Test
    fun `leap month 1 opens 2000 and Losar falls on it`() {
        // Janson §8 and footnote 28: Losar 2000 was Sunday 6 February, first day of leap month 1.
        val day = TibetanCalendar.of(LocalDate.of(2000, 2, 6))
        assertEquals(listOf(2000, 1, 1), listOf(day.year, day.month, day.day))
        assertTrue(day.leapMonth)
        assertEquals("Losar", day.holiday?.name)
    }

    @Test
    fun `a2 choice changes exactly the dates Janson lists`() {
        // Remark 14: JD 2451951, 2453866 and 2460999 differ between a2 = 1/28 and 3781/105840.
        for (jd in listOf(2451951L, 2453866L, 2460999L)) {
            val almanac = TibetanCalendar.of(jd, Phugpa.A2_ALMANAC)
            val henning = TibetanCalendar.of(jd, Phugpa.A2_HENNING)
            assertNotEquals(almanac.day to almanac.repetition, henning.day to henning.repetition, "JD $jd")
        }
    }

    @Test
    fun `day numbering is continuous over 1900-2100`() {
        var date = SUPPORTED_RANGE.start
        var previous = TibetanCalendar.of(date)
        var repeated = 0
        var skipped = 0
        while (date < SUPPORTED_RANGE.endInclusive) {
            date = date.plusDays(1)
            val day = TibetanCalendar.of(date)
            val sameMonth = day.year == previous.year && day.month == previous.month && day.leapMonth == previous.leapMonth
            when {
                sameMonth && day.day == previous.day -> {
                    repeated++
                    assertEquals(Repetition.FIRST_OF_TWO, previous.repetition, "$date")
                    assertEquals(Repetition.SECOND_OF_TWO, day.repetition, "$date")
                }
                sameMonth && day.day == previous.day + 1 -> assertEquals(null, day.omittedBefore, "$date")
                sameMonth && day.day == previous.day + 2 -> {
                    skipped++
                    assertEquals(day.day - 1, day.omittedBefore, "$date")
                }
                !sameMonth -> {
                    assertTrue(day.day in 1..2, "$date starts a month on day ${day.day}")
                    assertTrue(previous.day in 29..30, "$date: previous month ends on day ${previous.day}")
                    if (day.day == 2) assertEquals(1, day.omittedBefore, "$date")
                }
                else -> throw AssertionError("$date: ${previous.day} → ${day.day}")
            }
            previous = day
        }
        assertTrue(repeated > 0 && skipped > 0)
    }

    @Test
    fun `cycles of the day match the Chinese sexagenary day`() {
        // Janson E.4: element, gender and animal equal those of the Chinese calendar day.
        // 2000-01-01 is 戊午: Earth, male, Horse.
        val day = TibetanCalendar.of(LocalDate.of(2000, 1, 1))
        assertEquals(2451545L, LocalDate.of(2000, 1, 1).julianDayNumber())
        assertEquals(Triple(Element.EARTH, Gender.MALE, Animal.HORSE), Triple(day.dayElement, day.dayGender, day.dayAnimal))
    }
}
