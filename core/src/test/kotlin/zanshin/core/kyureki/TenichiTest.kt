/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.kyureki

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

/** ROADMAP K5, SPEC §7.5: 天一神's course, the 暦林問答集 §53 and the 江次第抄 as the 古事類苑 prints it (RD94). */
class TenichiTest {
    private fun day(kanji: String) = Kanshi((0 until 60).first { Kanshi(it).kanji == kanji })

    @Test
    fun `the course from 己酉 and the sixteen days in heaven`() {
        // The 江次第抄: 己酉 to 甲寅 艮, 乙卯 to 己未 卯, 庚申 to 乙丑 巽, 丙寅 to 庚午 午, 辛未 to 丙子 坤, 丁丑 to 辛巳 酉,
        // 壬午 to 丁亥 乾, 戊子 to 壬辰 子, 癸巳 to 戊申 in heaven.
        val stays = listOf(
            Triple("己酉", "甲寅", TenichiPlace.USHITORA), Triple("乙卯", "己未", TenichiPlace.U),
            Triple("庚申", "乙丑", TenichiPlace.TATSUMI), Triple("丙寅", "庚午", TenichiPlace.UMA),
            Triple("辛未", "丙子", TenichiPlace.HITSUJISARU), Triple("丁丑", "辛巳", TenichiPlace.TORI),
            Triple("壬午", "丁亥", TenichiPlace.INUI), Triple("戊子", "壬辰", TenichiPlace.NE),
        )
        for ((first, last, place) in stays) {
            val a = day(first)
            val b = day(last)
            assertEquals(place, Tenichi.of(a), first)
            assertEquals(place, Tenichi.of(b), last)
            assertEquals(a to b, Tenichi.stay(b), last)
        }
        assertEquals(44, TenichiPlace.entries.sumOf { it.days })
        assertNull(Tenichi.of(day("癸巳")))
        assertNull(Tenichi.of(day("戊申")))
        assertEquals(day("癸巳") to day("戊申"), Tenichi.stay(day("丁未")))
    }

    @Test
    fun `heaven is the almanac's 天一天上`() {
        for (i in 0 until 60) {
            val k = Kanshi(i)
            // Rekichu marks 天一天上 on the sexagenary days 30 to 45, 1-based.
            assertEquals(i + 1 in 30..45, Tenichi.of(k) == null, k.kanji)
        }
    }
}
