/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import zanshin.core.texts.Texts

/**
 * The White Beryl's model almanac (vol. 1, pp. 154–171, img. 164–181), read on the scan: each
 * Hor month's line names its Kālacakra season («དུས་འཁོར་བའི་…»), its Chinese-reckoning season
 * («རྒྱ་རྩིས་…») and its animal.
 */
class SeasonReckoningTest {
    private val spring = 0
    private val summer = 3
    private val autumn = 6
    private val winter = 9
    private val early = 0
    private val mid = 1
    private val late = 2

    // Hor month to (Kālacakra season, Chinese season, the month's animal), as the lines read.
    private val read = mapOf(
        11 to Triple(winter + mid, spring + early, Animal.TIGER),
        12 to Triple(winter + late, spring + mid, Animal.RABBIT),
        1 to Triple(spring + early, spring + late, Animal.DRAGON),
        2 to Triple(spring + mid, summer + early, Animal.SNAKE),
        3 to Triple(spring + late, summer + mid, Animal.HORSE),
        4 to Triple(summer + early, summer + late, Animal.SHEEP),
        5 to Triple(summer + mid, autumn + early, Animal.MONKEY),
        6 to Triple(summer + late, autumn + mid, Animal.BIRD),
        7 to Triple(autumn + early, autumn + late, Animal.DOG),
        8 to Triple(autumn + mid, winter + early, Animal.PIG),
        9 to Triple(autumn + late, winter + mid, Animal.MOUSE),
        10 to Triple(winter + early, winter + late, Animal.OX),
    )

    @Test
    fun `each month's two seasons and its animal as WB's model almanac gives them`() {
        for ((month, line) in read) {
            val (kalacakra, chinese, animal) = line
            assertEquals(kalacakra, SeasonReckoning.KALACAKRA.season(month), "month $month, Kālacakra")
            assertEquals(chinese, SeasonReckoning.CHINESE.season(month), "month $month, Chinese reckoning")
            assertEquals(month, SeasonReckoning.KALACAKRA.month(kalacakra))
            assertEquals(month, SeasonReckoning.CHINESE.month(chinese))
            assertEquals(animal, TibetanCalendar.monthNames(month).animal, "month $month")
        }
    }

    @Test
    fun `Rāhu's course by month falls in the Chinese reckoning's spring and autumn`() {
        // WB vol. 2, p. 238 (img. 246–247), in chapter 31, which counts by the Chinese seasons:
        // the first month of spring the 11th, the middle the 12th, the three of autumn the 5th to 7th.
        val rahu = Texts.RAHU_MONTH
        fun dates(month: Int) = rahu.keys.filter { it.first == month }.map { it.second }.toSet()
        assertEquals(setOf(6, 9, 11, 13), dates(11))
        assertEquals(setOf(9, 11, 13, 16, 19), dates(12))
        for (month in 5..7) assertEquals(setOf(4, 8, 11, 15, 22, 25, 29), dates(month), "month $month")
        assertEquals(setOf(5, 6, 7, 11, 12), rahu.keys.map { it.first }.toSet())
        assertEquals("reading.RahuMonth.earlySpring.6", rahu.getValue(11 to 6).arg)
        assertEquals("reading.RahuMonth.autumn.15", rahu.getValue(5 to 15).arg)
    }
}
