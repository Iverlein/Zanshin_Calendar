/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.kyureki

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import zanshin.core.tibetan.Direction

/** ROADMAP K5, SPEC §7.5: the 大将軍's outings, the 暦林問答集 §54 with MS's 丙子 (RD92). */
class DaishogunTest {
    private fun day(kanji: String) = Kanshi((0 until 60).first { Kanshi(it).kanji == kanji })

    @Test
    fun `the five outings as §54 gives them`() {
        val outings = listOf(
            Triple("甲子", "戊辰", DaishogunOuting.U), Triple("丙子", "庚辰", DaishogunOuting.UMA), Triple("戊子", "壬辰", DaishogunOuting.CHUGU),
            Triple("庚子", "甲辰", DaishogunOuting.TORI), Triple("壬子", "丙辰", DaishogunOuting.NE),
        )
        for ((first, last, place) in outings) {
            assertEquals(place, Daishogun.outing(day(first)), first)
            assertEquals(place, Daishogun.outing(day(last)), last)
            assertEquals(day(first) to day(last), place.stay, first)
        }
        assertEquals(25, (0 until 60).count { Daishogun.outing(Kanshi(it)) != null })
    }

    @Test
    fun `on each 巳 day it is back in its own place`() {
        listOf("己巳", "辛巳", "癸巳", "乙巳", "丁巳").forEach { assertNull(Daishogun.outing(day(it)), it) }
        assertNull(Daishogun.outing(day("乙亥")))
    }

    @Test
    fun `an outing frees its own direction unless it goes there`() {
        assertTrue(Daishogun.frees(DaishogunOuting.UMA, 3))
        assertTrue(Daishogun.frees(DaishogunOuting.CHUGU, 3))
        assertFalse(Daishogun.frees(DaishogunOuting.U, 3))
        assertEquals(Direction.WEST, Daishogun.direction(YearGods.branchOf(HasshoGod.DAISHOGUN, 11)))
    }
}
