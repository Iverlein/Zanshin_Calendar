/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.kyureki

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import zanshin.core.tibetan.TibetanCalendar
import zanshin.core.time.SUPPORTED_RANGE
import java.time.LocalDate

class KyurekiTest {

    /** SPEC §7.3: O-Bon on its Gregorian date, 月遅れ, whatever the kyūreki date. */
    @Test
    fun `O-Bon by the Gregorian date`() {
        for (year in listOf(2026, 2033)) {
            val day = Kyureki.of(LocalDate.of(year, 8, 15))
            assertEquals("お盆", day.gregorianFestival?.kanji)
            assertTrue(day.gregorianFestival!!.gregorian)
        }
        assertEquals(null, Kyureki.of(LocalDate.of(2026, 8, 14)).gregorianFestival)
    }

    /** ROADMAP T6.5: the deities' days kept by the new calendar, Inari's 初午 the first 午 day of February. */
    @Test
    fun `the deities' Gregorian days`() {
        assertEquals("五大力尊仁王会", Kyureki.of(LocalDate.of(2027, 2, 23)).gregorianFestival?.kanji)
        assertEquals("四万六千日", Kyureki.of(LocalDate.of(2027, 7, 10)).gregorianFestival?.kanji)
        assertEquals("地蔵盆", Kyureki.of(LocalDate.of(2027, 8, 24)).gregorianFestival?.kanji)
        // 初午: 12 February 2024, 6 February 2025, 1 February 2026.
        for (date in listOf(LocalDate.of(2024, 2, 12), LocalDate.of(2025, 2, 6), LocalDate.of(2026, 2, 1))) {
            assertEquals("初午", Kyureki.of(date).gregorianFestival?.kanji, "$date")
        }
        for (year in 1901..2100) {
            val days = (0L..27L).map { LocalDate.of(year, 2, 1).plusDays(it) }.filter { Kyureki.of(it).gregorianFestival?.kanji == "初午" }
            assertEquals(1, days.size, "$year")
            assertEquals('午', Kyureki.of(days.single()).dayKanshi[1], "$year")
        }
    }

    /** ROADMAP T6.5: 初不動 1/28, 夏越の祓 the last day of the (last) sixth month, 十夜 10/6–15, Daikoku's day in month 11. */
    @Test
    fun `the deities' kyureki days`() {
        for (year in 1901..2099) {
            val months = Kyureki.sui(year - 1)
            fun daysOf(kanji: String) = months.flatMap { m -> (0 until m.days).map { m.start.plusDays(it.toLong()) } }
                .map(Kyureki::of).filter { it.festival?.kanji == kanji }
            daysOf("初不動").single().run { assertEquals(listOf(1, 28, 0), listOf(month, day, if (leapMonth) 1 else 0), "$year") }
            daysOf("夏越の祓").single().run {
                val m = months.last { it.number == 6 }
                assertEquals(m.end.minusDays(1), date, "$year")
            }
            assertEquals((6..15).toList(), daysOf("十夜").map { it.day }, "$year")
            // Month 11 of this sui (it opens the sui): one Daikoku day, the 甲子 or, without one, the first 子.
            val m11 = Kyureki.sui(year).first { it.number == 11 && !it.leap }
            val inMonth = (0 until m11.days).map { Kyureki.of(m11.start.plusDays(it.toLong())) }
            val daikoku = inMonth.filter { it.festival?.kanji in setOf("甲子祭", "子祭") }
            assertEquals(1, daikoku.size, "$year")
            val kinoene = inMonth.firstOrNull { it.dayKanshi == "甲子" }
            if (kinoene != null) assertEquals("甲子祭", daikoku.single().festival?.kanji)
            else daikoku.single().run {
                assertEquals("子祭", festival?.kanji)
                assertEquals(inMonth.first { it.dayKanshi[1] == '子' }, this)
            }
        }
    }

    @Test
    fun `autumn 2026`() {
        // 十五夜 2026 is 25 September; month 9 begins 11 October (new moon 10 Oct 15:50 UTC = 11 Oct JST).
        Kyureki.of(LocalDate.of(2026, 9, 25)).run {
            assertEquals(listOf(8, 15), listOf(month, day))
            assertEquals("十五夜", festival?.kanji)
        }
        Kyureki.of(LocalDate.of(2026, 10, 11)).run {
            assertEquals(listOf(9, 1), listOf(month, day))
            assertEquals(Rokuyo.SENBU, rokuyo)
        }
        Kyureki.of(LocalDate.of(2026, 9, 23)).run {
            assertEquals(SolarTerm.SHUBUN, termBeginning)
        }
    }

    @Test
    fun `first day of month 1 is always 先勝`() {
        for (year in 1901..2100) {
            val first = Kyureki.sui(year - 1).first { it.number == 1 && !it.leap }
            assertEquals(Rokuyo.SENSHO, Kyureki.of(first.start).rokuyo, "$year")
        }
    }

    @Test
    fun `month structure holds for every sui 1899-2100`() {
        for (year in 1899..2100) {
            val months = Kyureki.sui(year)
            assertTrue(months.size in 12..13, "$year: ${months.size} months")
            assertEquals(11, months.first().number)
            assertTrue(months.first().start.monthValue in 11..12, "$year month 11 starts ${months.first().start}")
            assertEquals(months.size == 13, months.any { it.leap }, "$year leap")
            for (m in months) {
                val length = m.end.toEpochDay() - m.start.toEpochDay()
                assertTrue(length in 29..30, "$year ${m.number}: $length days")
            }
            months.zipWithNext().forEach { (a, b) -> assertEquals(a.end, b.start) }
        }
    }

    @Test
    fun `leap months of the 2030s`() {
        val leaps = (2030..2036).flatMap { y -> Kyureki.sui(y).filter { it.leap }.map { "${it.year}/${it.number}" } }
        println("leap months 2030–2036 (sui years): $leaps")
        assertTrue("2033/11" in leaps, "2033 resolution: 閏11月, got $leaps")
    }

    @Test
    fun `day kanshi agrees with the Tibetan day cycles every day 1900-2100`() {
        val elementOfStem = "甲乙丙丁戊己庚辛壬癸".mapIndexed { i, c -> c to i / 2 }.toMap()
        val animalOfBranch = "子丑寅卯辰巳午未申酉戌亥".mapIndexed { i, c -> c to i }.toMap()
        var date = SUPPORTED_RANGE.start
        while (date <= SUPPORTED_RANGE.endInclusive) {
            val k = Kyureki.of(date)
            val t = TibetanCalendar.of(date)
            assertEquals(elementOfStem[k.dayKanshi[0]], t.dayElement.ordinal, "$date element")
            assertEquals(animalOfBranch[k.dayKanshi[1]], t.dayAnimal.ordinal, "$date animal")
            date = date.plusDays(1)
        }
    }
}
