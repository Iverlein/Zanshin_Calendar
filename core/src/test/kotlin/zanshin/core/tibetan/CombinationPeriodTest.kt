/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import zanshin.core.kyureki.Tone
import zanshin.core.texts.Texts

/** The combination period: KP's table of rising signs (§9, img. 79–80) and WB's readings (vol. 2, pp. 371–376). */
class CombinationPeriodTest {
    @Test
    fun `the rising sign of each hour follows KP's table`() {
        // Row by row as printed: the month, then its sign at daybreak, the first hour.
        val daybreak = mapOf(
            3 to ZodiacSign.ARIES, 4 to ZodiacSign.TAURUS, 5 to ZodiacSign.GEMINI, 6 to ZodiacSign.CANCER,
            7 to ZodiacSign.LEO, 8 to ZodiacSign.VIRGO, 9 to ZodiacSign.LIBRA, 10 to ZodiacSign.SCORPIO,
            11 to ZodiacSign.SAGITTARIUS, 12 to ZodiacSign.CAPRICORN, 1 to ZodiacSign.AQUARIUS, 2 to ZodiacSign.PISCES,
        )
        for ((month, sign) in daybreak) assertEquals(sign, risingSign(month, 0), "month $month")
        // The 5th month's row (img. 79): Gemini at daybreak, then Cancer, Leo … Taurus in the last hour, before dawn.
        assertEquals(
            listOf("GEMINI", "CANCER", "LEO", "VIRGO", "LIBRA", "SCORPIO", "SAGITTARIUS", "CAPRICORN", "AQUARIUS", "PISCES", "ARIES", "TAURUS"),
            (0 until 12).map { risingSign(5, it).name },
        )
    }

    @Test
    fun `WB's verdicts on the twelve periods`() {
        val avoid = setOf(ZodiacSign.ARIES, ZodiacSign.CANCER, ZodiacSign.LIBRA, ZodiacSign.SCORPIO, ZodiacSign.CAPRICORN)
        for (sign in ZodiacSign.entries) {
            val (tone, reading) = Texts.DUS_SBYOR.getValue(sign)
            assertEquals(if (sign in avoid) Tone.BAD else Tone.GOOD, tone, "$sign")
            assertEquals(emptySet<String>(), reading.goodKeys.toSet() intersect reading.avoidKeys.toSet(), "$sign")
        }
    }
}
