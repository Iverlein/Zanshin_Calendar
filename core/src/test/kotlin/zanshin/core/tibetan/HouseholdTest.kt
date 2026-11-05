/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.time.LocalDate

class HouseholdTest {
    private val year = 2026
    private val hour = Forces.hours(Forces.signs(TibetanCalendar.of(LocalDate.of(2026, 11, 5))).date)[3]

    private fun member(birthYear: Int, gender: Gender, born: LocalDate? = null) = HouseholdMember(
        YearReckoning(YearOfLife.yearSign(birthYear), birthYear, YearOfLife.yearSign(year), year, gender, hour), born,
    )

    /** The notes' laying of five (vol. 2, p. 490): the first man's body 1, the last woman's mewa 2, the middle member's 21–25. */
    @Test
    fun `the zigzag reproduces the notes' numbers for five members`() {
        val cells = (0 until 25).toList()
        val order = HouseholdRound.zigzag(cells)
        assertEquals(listOf(0, 24, 1, 23, 2, 22, 3, 21, 4, 20), order.take(10))
        // The middle member's column (cells 10–14) taken from both its ends: body 21, mewa 22, life force 23, luck 24, power 25.
        assertEquals(listOf(10, 14, 11, 13, 12), order.takeLast(5))
    }

    /** The notes' fold of five (p. 491): rows 1–6, 25–20, 14–19, 12–7, the 13th over. */
    @Test
    fun `the fold of five lays the notes' four rows`() {
        val r = HouseholdRound.of(List(5) { member(1950 + it, Gender.MALE) })!!
        fun numbers(row: List<RoundPebble>) = row.map { r.plain.indexOf(it) + 1 }
        assertEquals((1..6).toList(), numbers(r.god))
        assertEquals((25 downTo 20).toList(), numbers(r.people))
        assertEquals((14..19).toList(), numbers(r.house))
        assertEquals((12 downTo 7).toList(), numbers(r.enemy))
        assertEquals(13, r.plain.indexOf(r.fold) + 1)
        assertTrue(r.refold.isEmpty())
    }

    /** Seven members by the folds (claims/household-round-fold.md): the 18th the fold's, the 9th and 27th the refold's, the enemy's row 17–10. */
    @Test
    fun `seven members leave both leftovers`() {
        val r = HouseholdRound.of(List(7) { member(1950 + it, Gender.FEMALE) })!!
        assertEquals(18, r.plain.indexOf(r.fold) + 1)
        assertEquals(listOf(9, 27), r.refold.map { r.plain.indexOf(it) + 1 })
        assertEquals((17 downTo 10).toList(), r.enemy.map { r.plain.indexOf(it) + 1 })
    }

    @Test
    fun `the leftovers follow the count`() {
        assertNull(HouseholdRound.of(List(3) { member(1950 + it, Gender.MALE) }))
        val four = HouseholdRound.of(List(4) { member(1950 + it, Gender.MALE) })!!
        assertEquals(5, four.god.size)
        assertNull(four.fold)
        assertTrue(four.refold.isEmpty())
        val six = HouseholdRound.of(List(6) { member(1950 + it, Gender.MALE) })!!
        assertEquals(7, six.god.size)
        assertNull(six.fold)
        assertEquals(2, six.refold.size)
    }

    @Test
    fun `men come first and each by age, the earlier birth first in one year`() {
        val a = member(1980, Gender.FEMALE)
        val b = member(1990, Gender.MALE, LocalDate.of(1990, 8, 1))
        val c = member(1990, Gender.MALE, LocalDate.of(1990, 5, 1))
        val d = member(1970, Gender.MALE)
        assertEquals(listOf(d, c, b, a), HouseholdRound.ordered(listOf(a, b, c, d)))
    }

    @Test
    fun `a member brings body, vitality, power, luck and the mewa's colour`() {
        val m = member(1976, Gender.MALE)
        val column = HouseholdRound.column(m)
        assertEquals(listOf(Force.BODY, Force.VITALITY, Force.DESTINY, Force.LUCK, null), column.map { it.force })
        assertEquals(SME_BA_COLOURS[m.year.currentSmeBa - 1].uppercase(), column.last().colour.name)
    }

    @Test
    fun `the round reads the places by colour`() {
        val r = HouseholdRound.of(listOf(1950, 1955, 1980, 1984, 2010).map { member(it, Gender.FEMALE) })!!
        val colours = r.god.take(3).map { it.colour }
        if (RoundColour.WHITE in colours) assertTrue(r.godReads.any { it.key == "WHITE" })
        assertEquals(r.plain.count { it.colour == RoundColour.BLACK } > r.plain.count { it.colour == RoundColour.WHITE }, r.blackTheMore)
        assertTrue(r.reads.all { it.family.startsWith("Household") })
    }
}
