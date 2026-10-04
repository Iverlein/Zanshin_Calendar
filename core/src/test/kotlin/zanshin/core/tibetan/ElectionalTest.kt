/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import zanshin.core.texts.Activity
import zanshin.core.texts.DaySummary
import zanshin.core.texts.Texts

/** SPEC §5.10: Henning's activity lists, his doubled mansions read on the print, and the rules for what stays doubtful. */
class ElectionalTest {
    private fun list(wording: String) = Electional.ACTIVITIES.single { it.wording == wording }

    @Test
    fun `thirteen activities, each mansion list without duplicates or overlap`() {
        assertEquals(13, Electional.ACTIVITIES.size)
        for (a in Electional.ACTIVITIES) {
            assertEquals(a.good.mansions.distinct(), a.good.mansions, a.wording)
            assertTrue(a.good.mansions.intersect(a.bad.mansions.toSet()).isEmpty(), a.wording)
            assertTrue(a.good.weekdays.intersect(a.bad.weekdays).isEmpty(), a.wording)
            assertTrue(a.good.dates.intersect(a.bad.dates).isEmpty(), a.wording)
            assertTrue(a.good.animals.intersect(a.bad.animals).isEmpty(), a.wording)
        }
        assertEquals(Mansion.entries.toSet(), Electional.MANSION_ACTIVITIES.keys)
    }

    @Test
    fun `a mansion named in two places for one activity is left out`() {
        // Marriage: Svātī good only in parentheses.
        val marriage = list("marriage")
        assertFalse(Mansion.SVATI in marriage.good.mansions)
        assertTrue(Mansion.PUSHYA in marriage.good.mansions)
        // Offerings: Uttarabhādrapadā stands in the good and the bad half of the print.
        val offerings = list("offerings_to_deities")
        assertFalse(Mansion.UTTARABHADRAPADA in offerings.good.mansions || Mansion.UTTARABHADRAPADA in offerings.bad.mansions)
        assertTrue(Mansion.MRIGASHIRAS in offerings.good.mansions)
        // Controlling: Mṛgaśiras is both "merely acceptable" and bad.
        assertFalse(Mansion.MRIGASHIRAS in list("controlling_activity").bad.mansions)
    }

    /** Henning's doubled chu smad, read on the print he translated (docs/sources/mansions.md, Box by box). */
    @Test
    fun `the doubled mansions follow the kun phan me long`() {
        fun good(w: String) = list(w).good.mansions
        fun bad(w: String) = list(w).bad.mansions
        val ua = Mansion.UTTARASHADHA
        val ub = Mansion.UTTARABHADRAPADA
        assertTrue(ua in good("marriage") && ub in bad("marriage"))
        assertTrue(ua in good("taking_a_new_home") && ub in bad("taking_a_new_home"))
        assertTrue(ub in good("setting_out_on_journeys") && ua in bad("setting_out_on_journeys"))
        assertTrue(Mansion.SHATABHISHAJ in good("setting_out_on_journeys"))
        assertTrue(ub in good("making_weapons") && ua in bad("making_weapons"))
        assertTrue(ub in good("funerals") && ua !in good("funerals") && ua !in bad("funerals"))
        assertTrue(ub in good("destructive_activity") && ua !in good("destructive_activity"))
        assertTrue(listOf(ua, ub, Mansion.PURVAPHALGUNI, Mansion.SHATABHISHAJ).all { it in good("controlling_activity") })
        assertTrue(ua in bad("increasing_activity") && ub in bad("increasing_activity"))
        val offerings = list("offerings_to_deities")
        assertTrue(listOf(ua, Mansion.PURVAPHALGUNI, Mansion.DHANISHTHA).all { it in offerings.good.mansions })
        assertTrue(Mansion.CITRA in offerings.bad.mansions)
    }

    @Test
    fun `rules the source states as ranges`() {
        assertEquals(11, list("pacifying_activity").bad.mansions.size)
        val health = list("health_and_wealth")
        assertTrue(5 in health.good.dates && 6 !in health.good.dates && 16 in health.bad.dates)
        assertTrue(list("setting_out_on_journeys").good.weekdays.isEmpty())
    }

    @Test
    fun `a mansion's reading keeps both lists where they disagree`() {
        // Rohiṇī: the list of mansions names marriage; the marriage list names Rohiṇī bad.
        val rohini = Texts.MANSION.getValue(Mansion.ROHINI)
        assertTrue("marriage" in rohini.goodKeys && "marriage" in rohini.avoidKeys)
    }

    @Test
    fun `the Tibetan day in brief`() {
        // 2026-09-30: Wednesday, 19th, Aśvinī, fire sheep, trigram li.
        val day = TibetanCalendar.of(java.time.LocalDate.of(2026, 9, 30))
        assertEquals(listOf(Weekday.WEDNESDAY, 19, Mansion.ASHVINI, Animal.SHEEP, Trigram.LI), listOf(day.weekday, day.day, day.mansion, day.dayAnimal, day.trigram))
        val s = DaySummary.of(day)
        val marriage = s.activities.single { it.activity == Activity.WEDDING }
        // Marriage lists Wednesday, Aśvinī, the 19th and the li trigram as bad, and the sheep, the 60-day animal,
        // as good; the lists count the lunar date's animal, here the tiger, which marriage does not name
        // (open question 9). The weekday decides, the death combination agrees below the mansion (SPEC §5.12).
        assertEquals(listOf("Wednesday", "Aśvinī", "Death combination", "day 19", "Li"), marriage.avoid.map { it.kanji })
        assertTrue(marriage.good.isEmpty())
        assertFalse(marriage.disputed)
    }
}
