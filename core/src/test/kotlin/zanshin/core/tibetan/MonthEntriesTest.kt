/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import zanshin.core.tibetan.MonthEntries.MonthPart
import zanshin.core.tibetan.MonthEntries.Rise
import zanshin.core.tibetan.MonthEntries.SeasonSign
import zanshin.core.vectors
import java.time.LocalDate

/**
 * The month heading's entries (WB vol. 1, p. 176; docs/sources/month-entries.md) against what the
 * texts print: the month's days against Henning's calendars, the weekday's rise against the verse
 * and the model almanac's three month lines, the black months against pp. 183 and 212, the
 * seasonal signs against ch. 16's table, the comet against its count.
 */
class MonthEntriesTest {

    @Test
    fun `a month is long when its days reach thirty, as Henning's calendars count them`() {
        val rows = vectors("henning-phugpa.tsv")
        val months = rows.groupBy { Triple(it[1].toInt(), it[2].toInt(), it[3] == "1") }.entries.toList()
        var checked = 0
        // The first and last month of the file may be cut off.
        for ((key, days) in months.drop(1).dropLast(1)) {
            val (year, month, leap) = key
            val n = Phugpa.trueMonthCount(year, month, leap)
            assertEquals(days.size, MonthEntries.days(n, Phugpa.A2_HENNING), "$key")
            assertEquals(days.size >= 30, MonthEntries.isLong(days.size), "$key")
            checked++
        }
        assertTrue(checked > 60)
        assertTrue(months.drop(1).dropLast(1).any { it.value.size == 29 } && months.any { it.value.size == 30 })
    }

    @Test
    fun `the rough rise follows the month's animal as the verse gives it`() {
        // p. 176: «སྟག་རྟ་ཁྱི་གསུམ་སྤེན་དར་ཚེས། །བྱི་འབྲུག་སྤྲེལ་གསུམ་མིག་དར་ཚེས། །ཕག་ལུག་ཡོས་གསུམ་ཟླ་དར་ཚེས། །བྱ་གླང་སྦྲུལ་གསུམ་ཕུར་དར་ཚེས»
        val verse = mapOf(
            Weekday.SATURDAY to setOf(Animal.TIGER, Animal.HORSE, Animal.DOG),
            Weekday.TUESDAY to setOf(Animal.MOUSE, Animal.DRAGON, Animal.MONKEY),
            Weekday.MONDAY to setOf(Animal.PIG, Animal.SHEEP, Animal.RABBIT),
            Weekday.THURSDAY to setOf(Animal.BIRD, Animal.OX, Animal.SNAKE),
        )
        for ((planet, animals) in verse) for (a in animals) assertEquals(planet, MonthEntries.roughRise(a), "$a")
    }

    /** The weekdays by the almanac's numbers: 0 Saturday, 1 Sunday … 6 Friday. */
    private fun planets(vararg numbers: Int) = numbers.map { Weekday.entries[it] }.toSet()

    private fun groups(lord: Weekday) = Rise.entries.associateWith { r -> Weekday.entries.filter { MonthEntries.rise(lord, it) == r }.toSet() }

    @Test
    fun `the fine rise gives the model almanac's month lines`() {
        // Vol. 1, img. 164, the 11th month: «དར། ༠༦ སྐྱོད། ༢༤ ཞུད། ༡༣ གུད། ༥», a Saturday lord (earth).
        assertEquals(
            mapOf(Rise.RISES to planets(0, 6), Rise.MOVES to planets(2, 4), Rise.WANES to planets(1, 3), Rise.DECLINES to planets(5)),
            groups(Weekday.SATURDAY),
        )
        // Img. 172, the 4th month: «དར། ༢༤༥ གུད། ༠ སྐྱོད། ༡༣ ཞུད། ༦», a lord of water.
        for (lord in listOf(Weekday.MONDAY, Weekday.WEDNESDAY)) {
            assertEquals(
                mapOf(Rise.RISES to planets(2, 4, 5), Rise.DECLINES to planets(0), Rise.MOVES to planets(1, 3), Rise.WANES to planets(6)),
                groups(lord),
            )
        }
        // Img. 169, the 2nd month: «དར། ༡༣ གུད། ༦ སྐྱོད། ༠ ཞུད། ༢༤», a Thursday lord (wood), the print leaving out Jupiter's own 5.
        val thursday = groups(Weekday.THURSDAY)
        assertEquals(planets(1, 3), thursday.getValue(Rise.RISES) - Weekday.THURSDAY)
        assertEquals(planets(6), thursday[Rise.DECLINES])
        assertEquals(planets(0), thursday[Rise.MOVES])
        assertEquals(planets(2, 4), thursday[Rise.WANES])
    }

    @Test
    fun `the lord is the weekday of the day in which the 1st ends`() {
        // 1st of the 1st month of 2026: Wednesday 18 February (Henning, pl_2026).
        assertEquals(Weekday.WEDNESDAY, MonthEntries.lord(Phugpa.trueMonthCount(2026, 1)))
    }

    @Test
    fun `the black months are the year's four-slayers up and down`() {
        // Vol. 1, p. 183: «བྱི་རྟ་གནམ་ཤར་བྱ་ཡོས་ནག །གླང་ལུག་ཤར་ཚེ་ཁྱི་འབྲུག་ནག །སྟག་སྤྲེལ་ཤར་ན་ཕག་སྦྲུལ་ནག»
        assertEquals(setOf(Animal.BIRD, Animal.RABBIT), MonthEntries.blackMonths(Animal.MOUSE))
        assertEquals(setOf(Animal.BIRD, Animal.RABBIT), MonthEntries.blackMonths(Animal.HORSE))
        assertEquals(setOf(Animal.DOG, Animal.DRAGON), MonthEntries.blackMonths(Animal.OX))
        assertEquals(setOf(Animal.DOG, Animal.DRAGON), MonthEntries.blackMonths(Animal.SHEEP))
        assertEquals(setOf(Animal.PIG, Animal.SNAKE), MonthEntries.blackMonths(Animal.TIGER))
        assertEquals(setOf(Animal.PIG, Animal.SNAKE), MonthEntries.blackMonths(Animal.MONKEY))
        // «ར་བཞིའི་སྟོད་ནག་བྲིང་བཞིའི་སྒང་། །ཐ་བཞིའི་སྨད་ནག»: the tiger month is the first of spring.
        assertEquals(MonthPart.UPPER, MonthEntries.blackPart(Animal.TIGER))
        assertEquals(MonthPart.MIDDLE, MonthEntries.blackPart(Animal.RABBIT))
        assertEquals(MonthPart.LOWER, MonthEntries.blackPart(Animal.DRAGON))
        assertEquals(MonthPart.UPPER, MonthEntries.blackPart(Animal.PIG))
        // A black month's two months always share their third.
        for (year in Animal.entries) assertEquals(1, MonthEntries.blackMonths(year).map(MonthEntries::blackPart).toSet().size)
    }

    @Test
    fun `the black month's third is a course of its days`() {
        // A horse year's (2026) hare month, the 12th, is black in its middle: 11–20, counted in the Chinese year
        // from the 11th month, so the 12th month of 2025 (a snake year) belongs to the horse year.
        val inside = EarthLordCourses.of(12, 15, Animal.MOUSE, Animal.HORSE)
        assertTrue(inside.any { it.course == EarthLordCourse.ZLA_NAG && it.variant == "MIDDLE" })
        assertTrue(EarthLordCourses.of(12, 21, Animal.MOUSE, Animal.HORSE).none { it.course == EarthLordCourse.ZLA_NAG })
        // Its bird month, the 6th, the same third.
        assertTrue(EarthLordCourses.of(6, 11, Animal.MOUSE, Animal.HORSE).any { it.course == EarthLordCourse.ZLA_NAG })
        assertTrue(EarthLordCourses.of(5, 15, Animal.MOUSE, Animal.HORSE).none { it.course == EarthLordCourse.ZLA_NAG })
        // On the calendar: 1 February 2026 is the 14th of the 12th month of 2025 (Henning).
        val day = TibetanCalendar.of(LocalDate.of(2026, 2, 1))
        assertEquals(12, day.month)
        assertEquals(MonthPart.MIDDLE, MonthEntries.blackMonth(day))
    }

    @Test
    fun `the ki kang black months are the year lines of p 212`() {
        // A tiger year: the 8th of the first months of summer and winter (season-months 3 and 9, Hor 2 and 8), at dawn.
        val tiger = MonthEntries.KI_KANG.getValue(Animal.TIGER)
        assertEquals(setOf(2, 8), tiger.seasons.map { SeasonReckoning.CHINESE.month(it) }.toSet())
        assertEquals(8 to MonthEntries.DayTime.DAWN, tiger.date to tiger.time)
        // A sheep year: the 22nd of the last months of autumn and spring (Hor 7 and 1), at dusk.
        val sheep = MonthEntries.KI_KANG.getValue(Animal.SHEEP)
        assertEquals(setOf(7, 1), sheep.seasons.map { SeasonReckoning.CHINESE.month(it) }.toSet())
        assertEquals(22 to MonthEntries.DayTime.DUSK, sheep.date to sheep.time)
        // The six years of the summer and winter and the six of autumn and spring.
        for ((year, k) in MonthEntries.KI_KANG) {
            val summer = year in setOf(Animal.TIGER, Animal.RABBIT, Animal.DRAGON, Animal.MONKEY, Animal.BIRD, Animal.DOG)
            assertEquals(if (summer) setOf(3, 9) else setOf(6, 0), k.seasons.map { it - it % 3 }.toSet(), "$year")
        }
        assertTrue(EarthLordCourses.of(2, 8, Animal.MOUSE, Animal.TIGER).any { it.course == EarthLordCourse.KI_KANG_ZLA_NAG })
        assertTrue(EarthLordCourses.of(2, 9, Animal.MOUSE, Animal.TIGER).none { it.course == EarthLordCourse.KI_KANG_ZLA_NAG })
    }

    /**
     * Ch. 16's table of the two signs for 65 years (vol. 1, pp. 188–189, img. 198–199): each sign's date
     * and its "desired part" (*'dod cha*, thirteenths), which grows by one a year, the date by 11, or 12
     * when the part comes round to nought. Built from the first column, Ṛṣi 27 with 5, the pig 19 with 4,
     * and checked against cells read on the scan.
     */
    private fun table(date: Int, part: Int): List<Pair<Int, Int>> {
        val out = mutableListOf(date to part)
        repeat(64) {
            val (d, a) = out.last()
            val next = (a + 1) % 13
            out += ((d - 1 + 11 + (if (next == 0) 1 else 0)) % 30 + 1) to next
        }
        return out
    }

    private val rishiTable = table(27, 5)
    private val pigTable = table(19, 4)

    @Test
    fun `the table rebuilt from its first column has the cells read on the scan`() {
        // Columns 1–15 (img. 198, the first block), read on the scan.
        assertEquals(listOf(27, 8, 19, 30, 11, 22, 3, 14, 26, 7, 18, 29, 10, 21, 2), rishiTable.take(15).map { it.first })
        assertEquals(listOf(5, 6, 7, 8, 9, 10, 11, 12, 0, 1, 2, 3, 4, 5, 6), rishiTable.take(15).map { it.second })
        assertEquals(listOf(19, 30, 11, 22, 3, 14, 25, 6, 17, 29, 10, 21, 2, 13, 24), pigTable.take(15).map { it.first })
        // The blocks' first and last cells: columns 16 and 34, 35 and 52.
        assertEquals(listOf(13, 2, 14, 22), listOf(16, 34, 35, 52).map { rishiTable[it - 1].first })
    }

    /** The calendar year whose signs stand in the table's column 1 (the 65 years repeat exactly: 804 months to 65 years). */
    private val firstColumnYear = 2014

    private fun signDateIn(year: Int, sign: SeasonSign): Int =
        Phugpa.monthsOf(year).firstNotNullOf { MonthEntries.signDate(it.count, sign) }

    @Test
    fun `each year's sign falls on the table's date, or the next when the part is twelve`() {
        // P. 189: add the parts to the date's mean sun, «སྔ་ཕྱིའི་ཚེས་གྲངས་གང་ཟིན་བརྟག»: the table's date, or the one after.
        for (year in 1990..2100) {
            val column = Math.floorMod(year - firstColumnYear, 65)
            for ((sign, t) in listOf(SeasonSign.RISHI to rishiTable, SeasonSign.PIG to pigTable)) {
                val (date, part) = t[column]
                val expected = if (part == 12) date % 30 + 1 else date
                assertEquals(expected, signDateIn(year, sign), "$sign $year")
            }
        }
    }

    @Test
    fun `the signs fall in late summer, the pig two months before the sage`() {
        val y2026 = Phugpa.monthsOf(2026)
        val rishi = y2026.first { MonthEntries.signDate(it.count, SeasonSign.RISHI) != null }
        val pig = y2026.first { MonthEntries.signDate(it.count, SeasonSign.PIG) != null }
        assertEquals(8 to 10, rishi.number to MonthEntries.signDate(rishi.count, SeasonSign.RISHI))
        assertEquals(6 to 2, pig.number to MonthEntries.signDate(pig.count, SeasonSign.PIG))
        // 21 September 2026 is the 10th of the 8th month, the first of the sage's seven days.
        assertEquals(1, MonthEntries.signDay(TibetanCalendar.of(LocalDate.of(2026, 9, 21)), SeasonSign.RISHI))
        assertEquals(7, MonthEntries.signDay(TibetanCalendar.of(LocalDate.of(2026, 9, 27)), SeasonSign.RISHI))
        assertEquals(null, MonthEntries.signDay(TibetanCalendar.of(LocalDate.of(2026, 9, 28)), SeasonSign.RISHI))
    }

    @Test
    fun `the comet's count marks a month in about 75`() {
        val marked = (2000..2049).flatMap { y -> (1..12).filter { m -> MonthEntries.cometMonth(y, m, false) }.map { y to it } }
        assertEquals(listOf(2003 to 3, 2009 to 3, 2015 to 4, 2021 to 5, 2027 to 6, 2033 to 6, 2039 to 7, 2045 to 8), marked)
        assertFalse(MonthEntries.cometMonth(2026, 10, false))
    }
}
