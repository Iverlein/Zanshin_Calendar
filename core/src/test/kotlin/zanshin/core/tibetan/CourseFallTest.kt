/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import zanshin.core.texts.Catalog
import zanshin.core.texts.ReadingRow
import zanshin.core.texts.Texts
import zanshin.core.texts.fallLine
import java.time.LocalDate
import java.util.Locale

/**
 * The *How it falls* line of a course by season-month (SPEC §8.1, rule 4; ROADMAP X7): the lesser
 * black day (*nyi ma nag chung*, WB vol. 2, p. 226; docs/sources/earth-lord-courses.md) names the
 * date and the season-month it fell on.
 */
class CourseFallTest {
    private fun nagChung(month: Int, date: Int): CourseDay? =
        EarthLordCourses.of(month, date, Animal.MOUSE).singleOrNull { it.course == EarthLordCourse.NAG_CHUNG }

    private fun <T> inLocale(locale: Locale, block: () -> T): T {
        val before = Catalog.locale
        Catalog.locale = locale
        try {
            return block()
        } finally {
            Catalog.locale = before
        }
    }

    private fun line(day: CourseDay?, locale: Locale = Locale.ENGLISH) = inLocale(locale) { fallLine(EarthLordCourses.fall(day!!)!!) }

    @Test
    fun `each season's three dates in turn, today the one of its month`() {
        // Hor 11, 12 and 1 are the first, middle and last months of spring.
        assertEquals(CourseFall(0, 8, listOf(8, 18, 28)), EarthLordCourses.fall(nagChung(11, 8)!!))
        assertEquals("the 8th, 18th and 28th of the spring months; today the 8th of the first month of spring", line(nagChung(11, 8)))
        assertEquals("the 8th, 18th and 28th of the spring months; today the 18th of the middle month of spring", line(nagChung(12, 18)))
        assertEquals("the 8th, 18th and 28th of the spring months; today the 28th of the last month of spring", line(nagChung(1, 28)))
        assertEquals("the 5th, 15th and 25th of the summer months; today the 15th of the middle month of summer", line(nagChung(3, 15)))
        assertEquals("the 1st, 11th and 21st of the autumn months; today the 21st of the last month of autumn", line(nagChung(7, 21)))
        assertEquals("8-е, 18-е и 28-е весенних месяцев; сегодня 8-е первого месяца весны", line(nagChung(11, 8), Locale.forLanguageTag("ru")))
        // The course falls on no other date of a month: the 18th of the first month of spring is not its.
        assertNull(nagChung(11, 18))
    }

    @Test
    fun `the winter's 4th, 18th and 16th`() {
        assertEquals(CourseFall(9, 4, listOf(4, 18, 16)), EarthLordCourses.fall(nagChung(8, 4)!!))
        assertEquals("the 4th, 18th and 16th of the winter months; today the 4th of the first month of winter", line(nagChung(8, 4)))
        assertEquals("the 4th, 18th and 16th of the winter months; today the 18th of the middle month of winter", line(nagChung(9, 18)))
        assertEquals("the 4th, 18th and 16th of the winter months; today the 16th of the last month of winter", line(nagChung(10, 16)))
        assertEquals(
            "4-е, 18-е и 16-е зимних месяцев; сегодня 16-е последнего месяца зимы",
            line(nagChung(10, 16), Locale.forLanguageTag("ru")),
        )
    }

    @Test
    fun `no other view`() {
        // The winter 9th, 19th and 29th of another view are not shown: only the course's own 4th, 18th and 16th.
        assertNull(nagChung(8, 9)); assertNull(nagChung(9, 19)); assertNull(nagChung(10, 29))
    }

    @Test
    fun `the readings' rows`() {
        inLocale(Locale.ENGLISH) {
            val own = Texts.earthLord(nagChung(11, 8)!!)
            assertEquals(listOf(ReadingRow.REMEDY, ReadingRow.FALLS), own.rows.map { it.first })
            assertEquals("the 8th, 18th and 28th of the spring months; today the 8th of the first month of spring", own.rows[1].second)
            assertTrue(own.summary.startsWith("The lesser black day, Nyima Nakchung"), own.summary)
        }
        // A course day made without its date, as the page's black month makes one, has no line.
        assertNull(EarthLordCourses.fall(CourseDay(EarthLordCourse.NAG_CHUNG, CourseEvent.MOVES, 0)))
    }

    @Test
    fun `a skipped date has none, a doubled one the same line on both days`() {
        var skipped = 0
        var doubled = 0
        var date = LocalDate.of(2000, 1, 1)
        while (date.year < 2050) {
            val day = TibetanCalendar.of(date)
            val season = SeasonReckoning.CHINESE.season(day.month)
            val courseDate = EarthLordCourses.NAG_CHUNG[season].single()
            val fall = EarthLordCourses.of(day).singleOrNull { it.course == EarthLordCourse.NAG_CHUNG }?.let { EarthLordCourses.fall(it) }
            assertEquals(day.day == courseDate, fall != null, "$date")
            if (fall != null) assertEquals(CourseFall(season, courseDate, EarthLordCourses.NAG_CHUNG.slice(season / 3 * 3..season / 3 * 3 + 2).map { it.single() }), fall)
            if (fall != null && day.repetition == Repetition.SECOND_OF_TWO) {
                val first = TibetanCalendar.of(date.minusDays(1))
                assertEquals(fall, EarthLordCourses.of(first).single { it.course == EarthLordCourse.NAG_CHUNG }.let { EarthLordCourses.fall(it) })
                doubled++
            }
            if (day.omittedBefore == courseDate) {
                assertTrue(TibetanCalendar.monthOf(day).none { d -> EarthLordCourses.of(d).any { it.course == EarthLordCourse.NAG_CHUNG } }, "$date")
                skipped++
            }
            date = date.plusDays(1)
        }
        assertTrue(skipped > 0 && doubled > 0, "skipped $skipped, doubled $doubled")
    }
}
