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
 * (pp. 335–336; docs/sources/combinations.md), and the *Rdo rje gtsug
 * lag*'s reckoning it quotes after them (p. 337, its table p. 342).
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

    @Test
    fun `the Rdo rje gtsug lag's day of accomplishment as WB's verse names it`() {
        // p. 337, img. 345, and its table p. 342, img. 350, read on the scan: khrums stod, smad, nam gru,
        // chu smad on Sunday; gro bzhin and snar ma on Monday; … gro bzhin alone on Saturday.
        val m = Mansion.entries
        assertEquals(
            listOf(
                setOf(m[24], m[25], m[26], m[20]), setOf(m[21], m[3]), setOf(m[25], m[26], m[2]), setOf(m[2], m[23]),
                setOf(m[6], m[7]), setOf(m[21], m[0]), setOf(m[21]),
            ),
            week.map { CombinationDay.GRUB_NYI.gtsugLagMansions(it) },
        )
    }

    @Test
    fun `the Rdo rje gtsug lag's other names are WB's mansions`() {
        // 'jig nyi: bre (19) on Sunday is chu stod, sgrog (23) on Monday is mon gru.
        assertTrue(Mansion.PURVASHADHA in CombinationDay.JIG_NYI.gtsugLagMansions(Weekday.SUNDAY))
        assertTrue(Mansion.SHATABHISHAJ in CombinationDay.JIG_NYI.gtsugLagMansions(Weekday.MONDAY))
        // mi mthun: byi bzhin on Monday, which the editors number 21 like gro bzhin, is Abhijit and never comes.
        assertEquals(setOf(Mansion.VISHAKHA, Mansion.UTTARABHADRAPADA), CombinationDay.MI_MTHUN_NYI.gtsugLagMansions(Weekday.MONDAY))
    }

    @Test
    fun `the Rdo rje gtsug lag reckons five kinds, and adds only what WB's own table lacks`() {
        val reckoned = CombinationDay.entries.filter { c -> week.any { c.gtsugLagMansions(it).isNotEmpty() } }
        assertEquals(
            listOf(CombinationDay.GRUB_NYI, CombinationDay.BDUD_NYI, CombinationDay.MI_PHROD_NYI, CombinationDay.MI_MTHUN_NYI, CombinationDay.JIG_NYI),
            reckoned,
        )
        // Tuesday with Uttarabhādrapadā is a day of accomplishment in both tables: WB's own counts, nothing is added;
        // with Revatī it is one by the Rdo rje gtsug lag only.
        assertTrue(CombinationDay.GRUB_NYI in CombinationDay.of(Weekday.TUESDAY, Mansion.UTTARABHADRAPADA))
        assertTrue(CombinationDay.ofGtsugLag(Weekday.TUESDAY, Mansion.UTTARABHADRAPADA).isEmpty())
        assertEquals(listOf(CombinationDay.GRUB_NYI), CombinationDay.ofGtsugLag(Weekday.TUESDAY, Mansion.REVATI))
        for (w in week) for (mansion in Mansion.entries) {
            CombinationDay.ofGtsugLag(w, mansion).forEach { assertTrue(it !in CombinationDay.of(w, mansion), "$w $mansion $it") }
        }
    }
}
