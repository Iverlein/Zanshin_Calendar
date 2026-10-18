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
import zanshin.core.texts.fallLines
import java.time.LocalDate
import java.util.Locale

/**
 * The *How it falls* line of a course (SPEC §8.1, rule 4; ROADMAP X7): the lesser black day (*nyi ma
 * nag chung*, WB vol. 2, p. 226; docs/sources/earth-lord-courses.md) names the date and the
 * season-month it fell on, and every other course the line of its table that holds the shown day.
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

    /** The course [course] on [date] of Hor month [month], a day of [animal], doing [event] if given. */
    private fun course(course: EarthLordCourse, month: Int, date: Int, animal: Animal = Animal.MOUSE, event: CourseEvent? = null): CourseDay =
        EarthLordCourses.of(month, date, animal, Animal.MOUSE).first { it.course == course && (event == null || it.event == event) }

    @Test
    fun `every course but the black months and Viṣṭi's classes names its line, and the day is on it`() {
        for (month in 1..12) for (date in 1..30) for (animal in Animal.entries) for (year in Animal.entries) {
            for (day in EarthLordCourses.of(month, date, animal, year)) {
                val fall = EarthLordCourses.fall(day)
                val none = day.course in setOf(EarthLordCourse.ZLA_NAG, EarthLordCourse.KI_KANG_ZLA_NAG) || day.variant in setOf("gza", "srin po")
                assertEquals(none, fall == null, "$day")
                when (fall) {
                    is CourseFall -> assertEquals(date, fall.dates[fall.monthOfSeason], "$day")
                    is MonthFall -> assertTrue(date in fall.dates && fall.date == date, "$day")
                    is AnimalFall -> assertTrue(animal in fall.animals && fall.animal == animal, "$day")
                    is EveryMonthFall -> assertTrue(date in fall.dates && fall.dates.all { (it - date) % 10 == 0 }, "$day")
                    // The hours' lines (HourFallTest), never a course's.
                    is HourFall -> throw AssertionError("$day")
                    null -> {}
                }
            }
        }
    }

    @Test
    fun `each form of the line`() {
        inLocale(Locale.ENGLISH) {
            // One date and hour in each season-month: Piling Parma on the 8th of the last month of spring (Hor 1).
            assertEquals(
                "the 28th, 18th and 8th of the spring months; today the 8th of the last month of spring, at the snake hour",
                fallLine(EarthLordCourses.fall(course(EarthLordCourse.PI_LING, 1, 8))!!),
            )
            // The month's own dates: Kikang in the dragon month on the 18th and 28th.
            assertEquals("the 18th and 28th of the last month of spring; today the 18th", fallLine(EarthLordCourses.fall(course(EarthLordCourse.KI_KANG, 1, 18))!!))
            // One date this month, with the way it goes: Ngamshing's 10th of the first month of spring.
            assertEquals(
                "the 10th of the first month of spring, which is today, from east to west",
                fallLine(EarthLordCourses.fall(course(EarthLordCourse.NGAM_SHING, 11, 10))!!),
            )
            // Most of a month: the eight classes strike the whole last month of winter but the 27th.
            assertEquals(
                "every date of the last month of winter but the 27th; today the 15th",
                fallLine(EarthLordCourses.fall(course(EarthLordCourse.SDE_BRGYAD, 10, 15))!!),
            )
            // Runs of dates: their strikes in the middle month of autumn.
            assertEquals(
                "the 10th, 17th to 22nd and 24th to 30th of the middle month of autumn; today the 18th",
                fallLine(EarthLordCourses.fall(course(EarthLordCourse.SDE_BRGYAD, 6, 18))!!),
            )
            // Every date: the nyen strike the whole middle month of summer.
            assertEquals(
                "every date of the middle month of summer; today the 10th",
                fallLine(EarthLordCourses.fall(course(EarthLordCourse.GNYAN, 3, 10, event = CourseEvent.STRIKES))!!),
            )
            // By the date's animal, with the time of day: the black sky dog on the dragon and sheep days.
            assertEquals(
                "the dragon and sheep days of the first month of spring; today a day of the sheep, in the warmth of the morning",
                fallLine(EarthLordCourses.fall(course(EarthLordCourse.GNAM_KHYI, 11, 5, Animal.SHEEP))!!),
            )
            // A season's days: Zinpung on the tiger and monkey days of spring, its first month's hour.
            assertEquals(
                "the tiger and monkey days of the spring months; today a day of the monkey, when the birds go to roost",
                fallLine(EarthLordCourses.fall(course(EarthLordCourse.ZIN_PHUNG, 11, 5, Animal.MONKEY))!!),
            )
            // The same dates every month: the sky door of the 2s.
            assertEquals("the 2nd, 12th and 22nd of every month; today the 12th", fallLine(EarthLordCourses.fall(course(EarthLordCourse.GNAM_SGO, 4, 12))!!))
        }
        inLocale(Locale.forLanguageTag("ru")) {
            assertEquals(
                "28-е, 18-е и 8-е весенних месяцев; сегодня 8-е последнего месяца весны, в час змеи",
                fallLine(EarthLordCourses.fall(course(EarthLordCourse.PI_LING, 1, 8))!!),
            )
            assertEquals(
                "каждое число последнего месяца зимы, но не 27-е; сегодня 15-е",
                fallLine(EarthLordCourses.fall(course(EarthLordCourse.SDE_BRGYAD, 10, 15))!!),
            )
            assertEquals(
                "дни дракона и овцы первого месяца весны; сегодня день овцы, в утреннем тепле",
                fallLine(EarthLordCourses.fall(course(EarthLordCourse.GNAM_KHYI, 11, 5, Animal.SHEEP))!!),
            )
            assertEquals("10-е первого месяца весны — это сегодня, с востока на запад", fallLine(EarthLordCourses.fall(course(EarthLordCourse.NGAM_SHING, 11, 10))!!))
        }
    }

    @Test
    fun `a course doing two things has a line for each, under what it does`() {
        inLocale(Locale.ENGLISH) {
            // The nyen move on the 10th of the middle month of summer and strike all that month.
            val days = EarthLordCourses.of(3, 10, Animal.MOUSE).filter { it.course == EarthLordCourse.GNYAN }
            assertEquals(2, days.size)
            assertEquals(
                "moving: the 7th, 10th and 27th of the summer months; today the 10th of the middle month of summer\n" +
                    "striking: every date of the middle month of summer; today the 10th",
                Texts.earthLord(days).rows.single { it.first == ReadingRow.FALLS }.second,
            )
            assertEquals(fallLine(EarthLordCourses.fall(days[0])!!), fallLines(listOf(EarthLordCourses.fall(days[0])!!)))
        }
    }

    @Test
    fun `the other readings' rows`() {
        inLocale(Locale.ENGLISH) {
            // The day animal's earth lord: its remedy, the earth lord and the hearth god, and the witness.
            assertEquals(listOf(ReadingRow.REMEDY, ReadingRow.SPIRITS, ReadingRow.DEATH), Texts.EARTH_LORD.getValue(Animal.MOUSE).rows.map { it.first })
            // The black month's line is its third's, Kikang's its year's.
            val black = Texts.earthLord(CourseDay(EarthLordCourse.ZLA_NAG, CourseEvent.MOVES, 2, "LOWER", 21))
            assertEquals("the lower third, the 21st to the 30th, in a last month of its season", black.rows.single { it.first == ReadingRow.FALLS }.second)
            val kiKang = Texts.earthLord(CourseDay(EarthLordCourse.KI_KANG_ZLA_NAG, CourseEvent.MOVES, 2, "OX", 22))
            assertEquals(listOf(ReadingRow.REMEDY, ReadingRow.FALLS), kiKang.rows.map { it.first })
            // The sky door's remedy is its door's.
            assertTrue(Texts.earthLord(course(EarthLordCourse.GNAM_SGO, 4, 12)).rows.first().second.startsWith("press figures under an effigy"))
            // Rāhu's course through the day, once, on both his courses by date.
            val through = Catalog.text("reading.Rahu.through_day")
            assertEquals(through, Texts.RAHU.getValue(8).rows.single { it.first == ReadingRow.THROUGH_DAY }.second)
            assertEquals(through, Texts.RAHU_GENERAL.getValue(2).rows.single { it.first == ReadingRow.THROUGH_DAY }.second)
            // The great black day, Rāhu among the earth lords and his course by month name their lines.
            assertEquals(
                "the 7th, 14th and 21st of the spring months; today the 7th of the first month of spring",
                Texts.GREAT_BLACK_DAY.getValue(0).rows.single { it.first == ReadingRow.FALLS }.second,
            )
            assertEquals("the 11th and 28th of the first month of spring; today the 28th", Texts.rahuSeason(11, 28)!!.rows.single { it.first == ReadingRow.FALLS }.second)
            assertNull(Texts.rahuSeason(11, 27))
            assertEquals(
                "the 6th, 9th, 11th and 13th of the first month of spring; today the 9th",
                Texts.RAHU_MONTH.getValue(11 to 9).rows.single { it.first == ReadingRow.FALLS }.second,
            )
        }
    }
}
