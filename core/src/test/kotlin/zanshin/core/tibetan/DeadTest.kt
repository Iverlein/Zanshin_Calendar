/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import java.time.LocalDate

/** The reckoning for the dead's first examination (ROADMAP R2n; docs/sources/reckonings.md, RD16). */
class DeadTest {
    /**
     * Kun dga' rig 'dzin's sheet for the dead (KD vol. 3, p. 729; docs/sources/verdicts.md, RD28): a man of the fire
     * horse year, past ninety, dead in a wood pig year on the 4th of the 9th month at the sheep hour, his deciding
     * pebbles life black, body white, destiny white, luck black. The sheet fights four places, the year, month, day
     * and hour of death; on those four the colours and counts here give its pebbles. WB's roots (pp. 45–46; the notes,
     * p. 500), eight of them here, give the same but destiny speckled, three places white and three black.
     */
    @Test
    fun `the time of death on Kun dga' rig 'dzin's sheet`() {
        var d = LocalDate.of(1995, 10, 15)
        while (TibetanCalendar.of(d).let { !(it.month == 9 && it.day == 4) }) d = d.plusDays(1)
        val day = TibetanCalendar.of(d)
        val hour = Forces.hours(Forces.signs(day).date).first { it.sign.animal == Animal.SHEEP }
        val r = DeadReckoning.of(1906, Gender.MALE, day, hour)!!
        assertEquals(90, r.year.age)
        val four = setOf(DeadRoot.DEATH_YEAR, DeadRoot.MONTH, DeadRoot.DATE, DeadRoot.HOUR)
        fun onFour(f: Force): PebbleColour = r.pebbles.filter { it.force == f && it.root in four }.map { it.pebbles }
            .let { ps -> ps.count { it.white > 0 && it.black == 0 } to ps.count { it.black > 0 && it.white == 0 } }
            .let { (w, b) -> if (w > b) PebbleColour.WHITE else if (b > w) PebbleColour.BLACK else PebbleColour.SPECKLED }
        assertEquals(listOf(PebbleColour.BLACK, PebbleColour.WHITE, PebbleColour.WHITE, PebbleColour.BLACK), Force.entries.map(::onFour))
        assertEquals(listOf(PebbleColour.BLACK, PebbleColour.WHITE, PebbleColour.SPECKLED, PebbleColour.BLACK), Force.entries.map(r::deciding))
        assertEquals(Pebbles(3, 3), r.places(Force.DESTINY))
        assertEquals(8, r.pebbles.count { it.force == Force.LUCK })
        assertNull(r.whole)
        // Without the hour of death the seven other roots are reckoned.
        assertEquals(7, DeadReckoning.of(1906, Gender.MALE, day, null)!!.pebbles.count { it.force == Force.LUCK })
        assertNull(DeadReckoning.of(1996, Gender.MALE, day, hour))
    }
}
