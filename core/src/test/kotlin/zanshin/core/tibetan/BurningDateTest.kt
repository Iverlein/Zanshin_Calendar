/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import java.time.LocalDate
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import zanshin.core.texts.Activities
import zanshin.core.texts.Activity
import zanshin.core.texts.DaySummary
import zanshin.core.texts.Texts
import zanshin.core.vectors

/** The White Beryl's burning dates (docs/sources/burning-dates.md). */
class BurningDateTest {
    @Test
    fun `the burning dates as the White Beryl's verse gives them`() {
        val rows = vectors("white-beryl-burning-dates.tsv")
        assertEquals(7, rows.size)
        for ((weekday, dates) in rows) {
            val w = Weekday.entries.single { it.english.equals(weekday, ignoreCase = true) }
            assertEquals(dates.split(' ').map { it.toInt() }.toSet(), BurningDate.dates(w), weekday)
        }
    }

    @Test
    fun `the number words of the second verse name the same dates in each half`() {
        // Vol. 2, p. 351, img. 359, read on the scan: «ཉི་མའི་ཚེས་ལ་ཉི་མ» the Sun's date the sun (12),
        // «ཟླ་བས་དྲག་པོ» Rudra (11), «མིག་དམར … ཕྱོགས་བཅུ» the ten directions, «མེ» fire (3), «རོ» the tastes (6),
        // «འཁྲིག་པ» the pair (2), «རི» the mountains (7); «ཕྱོགས་གཉིས་མཚུངས» both halves alike.
        val words = mapOf(
            Weekday.SUNDAY to 12, Weekday.MONDAY to 11, Weekday.TUESDAY to 10, Weekday.WEDNESDAY to 3,
            Weekday.THURSDAY to 6, Weekday.FRIDAY to 2, Weekday.SATURDAY to 7,
        )
        for ((w, n) in words) assertEquals(setOf(n, n + 15), BurningDate.dates(w), "$w")
        // Fourteen dates, none twice: every date of the month burns on one weekday at most.
        assertEquals(14, Weekday.entries.flatMap { BurningDate.dates(it) }.toSet().size)
    }

    @Test
    fun `a day burns by its own weekday and date, a doubled date on one of its days at most`() {
        var first = LocalDate.of(2000, 1, 1)
        var doubled = 0
        while (first.year < 2050) {
            val day = TibetanCalendar.of(first)
            assertEquals(day.day in BurningDate.dates(day.weekday), day.burningDate, "$first")
            if (day.repetition == Repetition.SECOND_OF_TWO && day.burningDate) {
                assertTrue(TibetanCalendar.of(first.minusDays(1)).let { it.day == day.day && !it.burningDate })
                doubled++
            }
            first = first.plusDays(1)
        }
        // A doubled date falls on two weekdays, so it burns on at most one of them.
        assertTrue(doubled > 0)
    }

    @Test
    fun `the burning date speaks with the special days, against virtuous work and for fierce work`() {
        val virtue = Activities.of(listOf("virtuous_work")).single()
        assertEquals(Activity.VIRTUE, virtue)
        var day = LocalDate.of(2026, 1, 1)
        var seen = 0
        while (day.year < 2027) {
            val t = TibetanCalendar.of(day)
            if (t.burningDate && t.combinationDays.isEmpty() && t.gtsugLagDays.isEmpty()) {
                val s = DaySummary.of(t)
                val burning = s.activities.single { it.activity == Activity.BLOODLETTING }
                assertTrue(burning.avoid.any { it.reading == Texts.BURNING_DATE } || burning.outweighed.any { it.reading == Texts.BURNING_DATE }, "$day")
                seen++
            }
            day = day.plusDays(1)
        }
        assertTrue(seen > 0)
    }
}
