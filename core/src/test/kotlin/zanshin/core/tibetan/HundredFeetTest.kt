/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import java.time.LocalDate

/** The fangs of the hundred feet on a birth mansion and on the moon's (WB vol. 1, pp. 97–99; docs/sources/day-letters.md). */
class HundredFeetTest {

    @Test
    fun `the six holders count round the wheel with Abhijit`() {
        // From Kṛttikā: the 10th Uttaraphalgunī, the 16th Jyeṣṭhā, the 18th Pūrvāṣāḍhā, the 19th Uttarāṣāḍhā, the 23rd
        // Śatabhiṣaj and the 25th Uttarabhādrapadā, Abhijit after Śravaṇa being the 21st.
        val from = HundredFeet.Holder.entries.associateWith { HundredFeet.holder(Mansion.KRITTIKA, it) }
        assertEquals(
            listOf(
                Mansion.UTTARAPHALGUNI, Mansion.JYESHTHA, Mansion.PURVASHADHA, Mansion.UTTARASHADHA,
                Mansion.SHATABHISHAJ, Mansion.UTTARABHADRAPADA,
            ),
            from.values.toList(),
        )
        // From Ārdrā the 18th is Abhijit, which no body's fang can be on as a day's mansion.
        assertNull(HundredFeet.holder(Mansion.ARDRA, HundredFeet.Holder.SAMUDAYA))
    }

    @Test
    fun `the birth mansion's strikes and the spear over fifty years`() {
        // The test birth date, 1 June 1976 (Ārdrā): the fangs on its mansion in 2000–2049, and the days of the spear.
        val birth = TibetanCalendar.of(LocalDate.of(1976, 6, 1)).mansion
        val results = mutableMapOf<HundredFeet.Result, Int>()
        var spear = 0
        var great = 0
        var date = LocalDate.of(2000, 1, 1)
        while (date.year < 2050) {
            val day = TibetanCalendar.of(date)
            HundredFeet.of(day, birth)?.result?.let { results.merge(it, 1, Int::plus) }
            HundredFeet.spear(day)?.let { if (it.great) great++ else spear++ }
            date = date.plusDays(1)
        }
        // A malefic's fang on it on about one day in eight, each of the slow malefics staying for weeks; all
        // three of birth, work and the Destroyer held on 34 days.
        assertEquals(
            mapOf(
                HundredFeet.Result.DEATH to 34, HundredFeet.Result.ILLNESS to 134, HundredFeet.Result.AIMS_FAIL to 2429,
                HundredFeet.Result.EPIDEMIC to 2, HundredFeet.Result.WANT to 1659,
            ),
            results.toMap(),
        )
        assertEquals(1428 to 2446, spear to great)
    }
}
