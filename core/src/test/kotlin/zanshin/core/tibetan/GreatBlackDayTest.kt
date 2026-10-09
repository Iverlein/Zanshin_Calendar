/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import java.time.LocalDate
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import zanshin.core.texts.Texts

/** The White Beryl's great black day (docs/sources/great-black-day.md). */
class GreatBlackDayTest {
    @Test
    fun `the dates as the White Beryl lists them by season-month`() {
        // Vol. 2, p. 226, img. 234, read on the scan: «དཔྱིད་རའི་ཚེས་བདུན་མནོལ་ཤོར་དུས། …དཔྱིད་འབྲིང་བཅུ་བཞི་སྐྱེས་པའི་དུས། །
        // དཔྱིད་ཐའི་ཉེར་གཅིག་རྡོལ་བའི་དུས། །དབྱར་རའི་ཚེས་བརྒྱད་སྟག་སྲིང་མནོལ། །དབྱར་འབྲིང་བཅུ་དྲུག་ཁྲག་གྲོད་སྐྱེས། །དབྱར་ཐའི་ཉེར་བཞི་གྲོད་པ་རྡོལ། །
        // སྟོན་རའི་དགུ་ལ་གོང་སྲིང་མནོལ། །སྟོན་འབྲིང་བཅོ་བརྒྱད་ལྕགས་གྲོད་སྐྱེས། །ཐ་མའི་ཉེར་བདུན་གྲོད་པ་རྡོལ། །དགུན་རའི་ཚེས་བཅུར་བམ་སྲིང་མནོལ། །
        // འབྲིང་པོའི་ཉི་ཤུར་ཆུ་གྲོད་སྐྱེས། །ཐ་ཆུང་གནམ་གང་ལྐུགས་པ་རྡོལ།» (གནམ་གང, the new moon, is the 30th).
        val read = listOf(7, 14, 21, 8, 16, 24, 9, 18, 27, 10, 20, 30)
        assertEquals(read, GreatBlackDay.DATES)
        // Season-months of the Chinese reckoning: the first month of spring is the 11th (SeasonReckoningTest).
        val byMonth = mapOf(11 to 7, 12 to 14, 1 to 21, 2 to 8, 3 to 16, 4 to 24, 5 to 9, 6 to 18, 7 to 27, 8 to 10, 9 to 20, 10 to 30)
        for ((month, date) in byMonth) {
            for (d in 1..30) {
                assertEquals(if (d == date) SeasonReckoning.CHINESE.season(month) else null, GreatBlackDay.of(month, d), "$month/$d")
            }
        }
    }

    @Test
    fun `the meeting of the nine bad is the 11th month's 7th, and the 6th holds nothing`() {
        // «དཔྱིད་རའི་ཚེས་བདུན་ … འདི་ལ་ངན་པ་དགུ་འཛོམ་ཞེས»: the 7th of the first month of spring.
        assertEquals(GreatBlackDay.NINE_BAD, GreatBlackDay.of(11, 7))
        assertNull(GreatBlackDay.of(11, 6))
        assertEquals("reading.GreatBlackDay.0", Texts.GREAT_BLACK_DAY.getValue(GreatBlackDay.NINE_BAD).arg)
        // Rabten's Ten Good Omens on 11/6 is gone: no festival falls in the 11th month.
        assertEquals(emptyList<TibetanFestival>(), TibetanFestival.entries.filter { it.month == 11 })
    }

    @Test
    fun `every year from 2000 to 2049 has the course on the days its dates stand`() {
        var date = LocalDate.of(2000, 1, 1)
        var nineBad = 0
        while (date.year < 2050) {
            val day = TibetanCalendar.of(date)
            val season = GreatBlackDay.of(day.month, day.day)
            assertEquals(day.day == GreatBlackDay.DATES[SeasonReckoning.CHINESE.season(day.month)], season != null, "$date")
            if (season == GreatBlackDay.NINE_BAD && !day.leapMonth && day.repetition != Repetition.SECOND_OF_TWO) nineBad++
            assertEquals(null, day.holiday?.takeIf { day.month == 11 && day.day == 6 && !day.leapMonth }, "$date")
            date = date.plusDays(1)
        }
        // About one meeting a year; a skipped 7th of the 11th month has none.
        assertEquals(true, nineBad in 45..51, "$nineBad")
    }
}
