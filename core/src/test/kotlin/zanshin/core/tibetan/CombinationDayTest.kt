/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

/**
 * The White Beryl's special days of weekday and mansion: the table (vol. 2,
 * p. 341, img. 349, read on the scan) against the pairs its verses name
 * (pp. 335–336; docs/sources/combinations.md).
 */
class CombinationDayTest {
    private val week = listOf(Weekday.SUNDAY, Weekday.MONDAY, Weekday.TUESDAY, Weekday.WEDNESDAY, Weekday.THURSDAY, Weekday.FRIDAY, Weekday.SATURDAY)

    private fun assertDays(day: CombinationDay, vararg mansions: Mansion) =
        week.zip(mansions).forEach { (w, m) -> assertTrue(m in day.mansions(w), "$day $w ${m.wylie}") }

    @Test
    fun `the accomplishment combination as its verse names it`() {
        // me bzhi, gro bzhin, tshangs dbyig (= dbyu gu), lag sor (= lha mtshams), rgyal, nam gru, snar ma.
        assertDays(CombinationDay.GRUB_SBYOR, Mansion.HASTA, Mansion.SHRAVANA, Mansion.ASHVINI, Mansion.ANURADHA, Mansion.PUSHYA, Mansion.REVATI, Mansion.ROHINI)
    }

    @Test
    fun `the death combination as its verse pictures it`() {
        // The Sun burns the fingers (lag sor), the Moon overpowers smin drug, Mars is bound by sgrog (mon gru),
        // Mercury's hand is broken by the staff (dbyug), Jupiter strikes the head (mgo), Venus the herdsman
        // (snar ma), Saturn is burnt by me bzhi.
        assertDays(CombinationDay.CHI_SBYOR, Mansion.ANURADHA, Mansion.KRITTIKA, Mansion.SHATABHISHAJ, Mansion.ASHVINI, Mansion.MRIGASHIRAS, Mansion.ROHINI, Mansion.HASTA)
    }

    @Test
    fun `the paired combination and the day to avoid entirely share all but Thursday`() {
        for (w in week) {
            val same = CombinationDay.ZUNG_SBYOR.mansions(w) == CombinationDay.GTAN_SPANG.mansions(w)
            assertEquals(w != Weekday.THURSDAY, same, "$w")
        }
    }

    @Test
    fun `Monday's demon king falls on Abhijit, which never comes`() {
        assertTrue(CombinationDay.BDUD_RGYAL.mansions(Weekday.MONDAY).isEmpty())
    }
}
