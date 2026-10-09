/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import java.time.LocalDate
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

/**
 * What the White Beryl's almanac writes for each day (vol. 1, ch. 14, pp. 177–178, img. 187–188,
 * read on the scan; docs/sources/almanac-page.md), against the app: the special days in the
 * verse's words, a second witness to the table of vol. 2, p. 341, and the first date's trigram
 * and sme ba by the month.
 */
class AlmanacPageTest {
    private val week = listOf(Weekday.SUNDAY, Weekday.MONDAY, Weekday.TUESDAY, Weekday.WEDNESDAY, Weekday.THURSDAY, Weekday.FRIDAY, Weekday.SATURDAY)

    @Test
    fun `the special days as the almanac verse names them`() {
        // «… འགྲུབ་སྦྱོར་འཆི་སྦྱོར་སྲེག་སྦྱོར་དང་། །བདུད་ཉི་བདུད་རྒྱལ་འཇིག་པའི་ཉི། །མི་མཐུན་ཉི་མ་ཞེས་སུ་གྲགས།»: seven
        // kinds in this order, seven mansions to each weekday. The burning combination (*sreg sbyor*) is the
        // table's «བསྲེག་སྦྱོར་དང་གཏན་སྤང». dbyu gu is tha skar, bre chu stod, mon dre mon gre, the bare khrums
        // khrums stod; Monday's demon king is byi bzhin, Abhijit (null), which never is the day's mansion.
        val kinds = listOf(
            CombinationDay.GRUB_SBYOR, CombinationDay.CHI_SBYOR, CombinationDay.GTAN_SPANG, CombinationDay.BDUD_NYI,
            CombinationDay.BDUD_RGYAL, CombinationDay.JIG_NYI, CombinationDay.MI_MTHUN_NYI,
        )
        val m = Mansion.entries
        val verse = listOf(
            // ཉི་མ་མེ་བཞི་ལྷ་མཚམས་མཆུ། །སྨིན་དྲུག་སྣུབས་དང་ས་ག་སྒྲོག
            listOf(m[12], m[16], m[9], m[2], m[18], m[15], m[23]),
            // ཟླ་བ་གྲོ་བཞིན་སྨིན་ས་ག །དབོ་དང་བྱི་བཞིན་སྣྲོན་ཁྲུམས་སྨད།
            listOf(m[21], m[2], m[15], m[11], null, m[17], m[25]),
            // མིག་དམར་དབྱུ་གུ་མོན་གྲུ་ལག །སྐག་དང་ཁྲུམས་སྨད་ནམ་གྲུ་ཁྲུམས།
            listOf(m[0], m[23], m[5], m[8], m[25], m[26], m[24]),
            // ལྷག་པ་ལྷ་མཚམས་དབྱུ་གུ་སྣུབས། །བྲེ་སྨིན་མེ་བཞི་བྲ་ཉེ་དང་།
            listOf(m[16], m[0], m[18], m[19], m[2], m[12], m[1]),
            // ཕུར་བུ་རྒྱལ་མགོ་མོན་དྲེ་མགོ། །ནབས་སོ་སྣར་མ་ལྷ་མཚམས་ཏེ།
            listOf(m[7], m[4], m[22], m[4], m[6], m[3], m[16]),
            // དཀར་པོ་ནམ་གྲུ་སྣར་མ་སྣར། །རྒྱལ་གྲེ་ནབས་སོ་མཆུ་དང་ནི།
            listOf(m[26], m[3], m[3], m[7], m[10], m[6], m[9]),
            // སྤེན་སྣར་མེ་བཞི་ཆུ་སྟོད་དང་། །མོན་གྲུ་ས་རི་བྲ་ཉེ་དང་། །ནམ་གྲུ་
            listOf(m[3], m[12], m[19], m[23], m[14], m[1], m[26]),
        )
        for ((w, mansions) in week.zip(verse)) for ((kind, mansion) in kinds.zip(mansions)) {
            if (mansion == null) assertTrue(kind.mansions(w).isEmpty(), "$w $kind") else assertTrue(mansion in kind.mansions(w), "$w $kind ${mansion.wylie}")
        }
        // The Tuesday demon day is skag, Āśleṣā, as in the table; a calendar after WB has Maghā (tibetastromed.md).
        assertEquals(setOf(Mansion.ASHLESHA), CombinationDay.BDUD_NYI.mansions(Weekday.TUESDAY))
    }

    @Test
    fun `the first date's trigram by the month's animal and its sme ba by the season`() {
        // «སྟག་རྟ་ཁྱི་གསུམ་ལི་ཡིས་ཚེས། །ཕག་ལུག་ཡོས་གསུམ་ཟིན་གྱིས་ཚེས། །བྱི་འབྲུག་སྤྲེལ་གསུམ་ཁམ་གྱིས་ཚེས། །བྱ་གླང་སྦྲུལ་གསུམ་དྭ་ཡིས་ཚེས།
        // །སྨེ་བ་ར་བཞིར་གཅིག་དཀར་ཚེས། །འབྲིང་བཞི་བཞི་ལྗང་མཐའ་བཞི་བདུན། །དེ་ནས་རིམ་བཞིན་ཤེས་པར་བྱ།»: the four first months
        // of a season begin on the white 1, the four middle on the green 4, the four last on 7; the seasons
        // are the Chinese reckoning's, as in WB's chapters on the elemental reckoning (SeasonReckoning).
        val trigram = mapOf(
            Animal.TIGER to Trigram.LI, Animal.HORSE to Trigram.LI, Animal.DOG to Trigram.LI,
            Animal.PIG to Trigram.ZIN, Animal.SHEEP to Trigram.ZIN, Animal.RABBIT to Trigram.ZIN,
            Animal.MOUSE to Trigram.KHAM, Animal.DRAGON to Trigram.KHAM, Animal.MONKEY to Trigram.KHAM,
            Animal.BIRD to Trigram.DWA, Animal.OX to Trigram.DWA, Animal.SNAKE to Trigram.DWA,
        )
        val smeBa = listOf(1, 4, 7)
        assertEquals("white", SME_BA_COLOURS[0])
        assertEquals("green", SME_BA_COLOURS[3])
        var date = LocalDate.of(2000, 1, 1)
        var firsts = 0
        while (date.year < 2050) {
            val day = TibetanCalendar.of(date)
            if (day.day == 1) {
                assertEquals(trigram.getValue(day.monthNames.animal), day.trigram, "$date")
                assertEquals(smeBa[SeasonReckoning.CHINESE.season(day.month) % 3], day.smeBa, "$date")
                firsts++
            }
            date = date.plusDays(1)
        }
        // Each month that keeps its first date (one is now and then skipped) is checked.
        assertTrue(firsts > 590, "$firsts")
    }
}
