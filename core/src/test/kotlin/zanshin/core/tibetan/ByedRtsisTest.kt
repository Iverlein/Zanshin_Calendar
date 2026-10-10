/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.math.abs

/** The *byed rtsis* as WB vol. 1, ch. 4 gives it (pp. 31–32; docs/sources/byed-rtsis.md). */
class ByedRtsisTest {

    private val epoch = Phugpa.trueMonthCount(1687, 3)

    @Test
    fun theEpochIsTheThirdMonthOf1687() {
        assertEquals(ByedRtsis.COUNT_OFFSET, epoch)
        assertEquals(0L, ByedRtsis.count(epoch))
        assertEquals(0L, ByedRtsis.countOf(1687, 3))
        // A month's moon is the same in both reckonings away from the leap months.
        assertEquals(ByedRtsis.count(Phugpa.trueMonthCount(2026, 9)), ByedRtsis.countOf(2026, 9))
    }

    @Test
    fun theMonthFiguresAreTheVersesOwn() {
        // At the epoch each figure is its constant taken from nothing: 0;2,10 back, 9;79 back, 26;54,24,3,9 back.
        val d0 = ByedRtsis.dhruva(epoch)
        assertEquals("6;57,50,0,0", ByedRtsis.figure(d0.weekday))
        assertEquals("18;47", ByedRtsis.anomalyFigure(d0.anomaly))
        assertEquals("0;5,35,2,4", ByedRtsis.figure(d0.sun))
        // Each month adds 1;31,50, 2;1 and 2;10,58,2,10 (p. 31, «བསེ་རུ་ཟླ་མེ་མཁའ་འབྱུང་བསྒྱུར», «མིག་ཕྱོགས་ཀླུ་དབང་ལག་ཕྱོགས་བསྒྱུར»).
        val d1 = ByedRtsis.dhruva(epoch + 1)
        assertEquals("1;31,50,0,0", ByedRtsis.figure(Math.floorMod(d1.weekday - d0.weekday, 7 * ByedRtsis.UNIT)))
        assertEquals("2;1", ByedRtsis.anomalyFigure(d1.anomaly - d0.anomaly))
        assertEquals("2;10,58,2,10", ByedRtsis.figure(Math.floorMod(d1.sun - d0.sun, 27 * ByedRtsis.UNIT)))
    }

    @Test
    fun wbsOwnCorrectionMeasuresItsLagBehindTheTrueReckoning() {
        // The Zhal lung's correction (p. 32) takes 6;46,46,2 from the weekday, that is adds 0;13,13,4:
        // the true reckoning's mean weekday at the epoch stands that far ahead, to a tenth of a chu tshod.
        val trueWeekday = Phugpa.meanDate(epoch, 0).let { (it.frac() + Math.floorMod(it.floor() + 2, 7L)).toDouble() }
        val byed = ByedRtsis.dhruva(epoch).weekday.toDouble() / ByedRtsis.UNIT
        val lag = (trueWeekday - byed + 7) % 7 * 60
        val correction = (7 * 3600.0 * 6 - ((6 * 60 + 46) * 60 + 46) * 6 - 2) / (3600.0 * 6) * 60
        assertTrue(abs(lag - correction) < 0.2) { "lag $lag, correction $correction" }
    }

    @Test
    fun itsDatesEndAThirdOfADayBeforeTheTrueReckonings() {
        // Its month is 29;31,50, a little shorter than the true reckoning's: from 0;13 behind at the epoch it is
        // about 0;20 behind in 2026, the difference of the two reckonings' weekdays at each date's end, as here.
        for (n in Phugpa.trueMonthCount(2026, 1)..Phugpa.trueMonthCount(2026, 12)) for (d in 1..30) {
            val t = Phugpa.trueDate(n, d)
            val trueWeekday = (t.frac() + Math.floorMod(t.floor() + 2, 7L)).toDouble()
            val lag = (trueWeekday - ByedRtsis.date(n, d).weekday.toDouble() / ByedRtsis.UNIT + 7) % 7
            assertTrue(lag in 0.1..0.6) { "month $n, date $d: $lag" }
        }
    }
}
