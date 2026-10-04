/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * The White Beryl's table of the 28 combinations (vol. 1, pp. 148–149, img.
 * 158–159, read on the scan): for each weekday, the mansion of each
 * combination, by WB's numbers (0 tha skar … 21 gro bzhin, then 21 again
 * for byi bzhin, Abhijit, which WB's verses put after gro bzhin, then 22 mon
 * gre … 26 nam gru).
 */
class GreatCombinationTest {
    /** The first six columns of the table (kun dga' … bya rog), as printed. */
    private val printed = mapOf(
        Weekday.SUNDAY to listOf(0, 1, 2, 3, 4, 5),
        Weekday.MONDAY to listOf(4, 5, 6, 7, 8, 9),
        Weekday.TUESDAY to listOf(8, 9, 10, 11, 12, 13),
        Weekday.WEDNESDAY to listOf(12, 13, 14, 15, 16, 17),
        Weekday.THURSDAY to listOf(16, 17, 18, 19, 20, 21),
        Weekday.FRIDAY to listOf(20, 21, 21, 22, 23, 24),
        Weekday.SATURDAY to listOf(23, 24, 25, 26, 0, 1),
    )

    @Test
    fun `the first six combinations of each weekday fall on the printed mansions`() {
        for ((w, numbers) in printed) {
            numbers.forEachIndexed { i, n ->
                // Friday's second 21 is Abhijit, which is never the day's mansion.
                val abhijit = w == Weekday.FRIDAY && i == 2
                if (!abhijit) {
                    val mansion = Mansion.entries[n]
                    assertEquals(GreatCombination.entries[i], GreatCombination.of(w, mansion), "$w ${mansion.wylie}")
                }
            }
        }
    }

    @Test
    fun `Sunday's last seven run from Sravana to Revati`() {
        // Printed 21 21 22 … 26: gtun shing on gro bzhin, glang po on byi bzhin (Abhijit), rtag myos on mon gre … 'phel on nam gru.
        assertEquals(GreatCombination.GTUN_SHING, GreatCombination.of(Weekday.SUNDAY, Mansion.SHRAVANA))
        assertEquals(GreatCombination.RTAG_MYOS, GreatCombination.of(Weekday.SUNDAY, Mansion.DHANISHTHA))
        assertEquals(GreatCombination.PHEL, GreatCombination.of(Weekday.SUNDAY, Mansion.REVATI))
        assertEquals(GreatCombination.DOD, GreatCombination.of(Weekday.SUNDAY, Mansion.CITRA))
    }

    @Test
    fun `every weekday has each combination but the one on Abhijit`() {
        for (w in Weekday.entries) {
            assertEquals(27, Mansion.entries.map { GreatCombination.of(w, it) }.distinct().size, "$w")
        }
    }
}
