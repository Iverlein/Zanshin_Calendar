/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.kyureki

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate

/** ROADMAP K5, SPEC §7.5: the eight gods by the year's branch, against the printed almanac's table. */
class YearGodsTest {
    /**
     * Japanese Wikipedia「八将神」's table for the years 子 to 亥 (read 2026-10-31, docs/sources/kyureki.md, RD92), which
     * agrees with the 暦林問答集 wherever it gives a rule: 太歳 the year, 太陰 two behind, 歳破 opposite, 豹尾 opposite
     * the 黄幡, the 大将軍 in the 仲 and the 歳殺 and 黄幡 in the 季.
     */
    @Test
    fun `the eight gods of each year as the almanac tabulates them`() {
        val table = mapOf(
            HasshoGod.TAISAI to "子丑寅卯辰巳午未申酉戌亥",
            HasshoGod.DAISHOGUN to "酉酉子子子卯卯卯午午午酉",
            HasshoGod.TAION to "戌亥子丑寅卯辰巳午未申酉",
            HasshoGod.SAIKYO to "卯戌巳子辰申午丑寅酉未亥",
            HasshoGod.SAIHA to "午未申酉戌亥子丑寅卯辰巳",
            HasshoGod.SAISETSU to "未辰丑戌未辰丑戌未辰丑戌",
            HasshoGod.OBAN to "辰丑戌未辰丑戌未辰丑戌未",
            HasshoGod.HYOBI to "戌未辰丑戌未辰丑戌未辰丑",
        )
        for ((god, row) in table) {
            assertEquals(row, (0 until 12).joinToString("") { BRANCHES[YearGods.branchOf(god, it)].toString() }, "$god")
        }
    }

    @Test
    fun `the year turns at 立春 and 歳破 is weighed by where it falls`() {
        // 2026 is 丙午 from 立春, 4 February; the day before is still 乙巳.
        assertEquals(6, YearGods.yearBranch(LocalDate.of(2026, 2, 4)))
        assertEquals(5, YearGods.yearBranch(LocalDate.of(2026, 2, 3)))
        val gods = YearGods.of(LocalDate.of(2026, 10, 31)).associateBy { it.god }
        // A 午 year: 歳破 in 子, a middle branch, so light; 大将軍 in 卯 bars its quarter, 寅 to 辰.
        assertEquals(0, gods.getValue(HasshoGod.SAIHA).branch)
        assertEquals(SaihaWeight.LIGHT, gods.getValue(HasshoGod.SAIHA).weight)
        assertEquals(90, gods.getValue(HasshoGod.DAISHOGUN).bearing)
        assertEquals(90, gods.getValue(HasshoGod.DAISHOGUN).arc)
        assertEquals(SaihaWeight.NONE, YearGods.of(5).first { it.god == HasshoGod.SAIHA }.weight)
        assertEquals(SaihaWeight.HEAVY, YearGods.of(4).first { it.god == HasshoGod.SAIHA }.weight)
    }
}
