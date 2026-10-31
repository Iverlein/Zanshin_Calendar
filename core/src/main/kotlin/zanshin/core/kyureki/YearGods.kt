/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.kyureki

import zanshin.core.texts.gloss
import java.time.LocalDate

/**
 * 八将神, the year's eight direction gods of the printed almanac's 方位吉凶図 (SPEC §7.5, ROADMAP K5, D9), in the
 * almanac's order. The 暦林問答集 gives each its place by the year's branch and none by the stem (§6, §8–§14,
 * docs/sources/kyureki.md, RD92).
 */
enum class HasshoGod(override val kanji: String, override val reading: String) : Term {
    TAISAI("太歳神", "taisaijin"),
    DAISHOGUN("大将軍", "daishōgun"),
    TAION("太陰神", "taionjin"),
    SAIKYO("歳刑神", "saikyōjin"),
    SAIHA("歳破神", "saihajin"),
    SAISETSU("歳殺神", "saisetsujin"),
    OBAN("黄幡神", "ōbanjin"),
    HYOBI("豹尾神", "hyōbijin");

    override val english: String get() = gloss(this)
}

/** How heavily 歳破 strikes by the branch it falls in (the 暦林問答集 §11): none in a season's first, light in its middle, heavy in its last. */
enum class SaihaWeight { NONE, LIGHT, HEAVY }

/**
 * One god's place in a year: [branch] its direction, 0 = 子 due north, clockwise by 30°.
 * The 大将軍 bars the whole quarter about it (the 暦林問答集 §8: in 卯, 寅甲乙辰 with it), the others their branch.
 */
data class YearGod(val god: HasshoGod, val branch: Int) {
    val bearing: Int get() = branch * 30

    /** The width of the direction it bars, in degrees about [bearing]. */
    val arc: Int get() = if (god == HasshoGod.DAISHOGUN) 90 else 30

    /** 歳破's weight in this year; null for the other gods. */
    val weight: SaihaWeight?
        get() = if (god != HasshoGod.SAIHA) null else when (branch % 3) {
            2 -> SaihaWeight.NONE // 寅巳申亥
            0 -> SaihaWeight.LIGHT // 子卯午酉
            else -> SaihaWeight.HEAVY // 丑辰未戌
        }
}

/**
 * The eight gods' places by the year's branch. The 暦林問答集 gives the rule for 太歳 (the year's branch, §6), 太陰
 * (two behind, §9), 歳破 (opposite, §11) and 豹尾 (opposite the 黄幡, §14). Where it gives the god's range and no
 * rule, the printed almanac's table as Japanese Wikipedia「八将神」gives it stands (ROADMAP D8, the digests where
 * the old text is silent): the 大将軍 three years in each 仲 (§8; the years from the digest), the 歳殺 among
 * 丑未辰戌 (§12), the 黄幡 at the grave of the year's triad (§13 with §10 and §46 read together, as the digest
 * has it). The 歳刑 is the almanac's one branch, each inside the quarter of the year's triad that §10 bars
 * (the earth years' branch is §10's own). The year runs from 立春, as the 恵方's (a stated choice, question 491).
 */
object YearGods {
    /** 歳刑 by the year's branch, 子 to 亥: 卯 戌 巳 子 辰 申 午 丑 寅 酉 未 亥. */
    private val SAIKYO = intArrayOf(3, 10, 5, 0, 4, 8, 6, 1, 2, 9, 7, 11)

    /** The grave of the year's triad, by the branch mod 4 (申子辰 water → 辰, 巳酉丑 metal → 丑, 寅午戌 fire → 戌, 亥卯未 wood → 未). */
    private val GRAVE = intArrayOf(4, 1, 10, 7)

    /** 歳殺 by the triad: 申子辰 → 未, 巳酉丑 → 辰, 寅午戌 → 丑, 亥卯未 → 戌. */
    private val SAISETSU = intArrayOf(7, 4, 1, 10)

    fun branchOf(god: HasshoGod, year: Int): Int = Math.floorMod(
        when (god) {
            HasshoGod.TAISAI -> year
            // 亥子丑 → 酉, 寅卯辰 → 子, 巳午未 → 卯, 申酉戌 → 午.
            HasshoGod.DAISHOGUN -> 9 + 3 * (Math.floorMod(year + 1, 12) / 3)
            HasshoGod.TAION -> year - 2
            HasshoGod.SAIKYO -> SAIKYO[year]
            HasshoGod.SAIHA -> year + 6
            HasshoGod.SAISETSU -> SAISETSU[year % 4]
            HasshoGod.OBAN -> GRAVE[year % 4]
            HasshoGod.HYOBI -> GRAVE[year % 4] + 6
        },
        12,
    )

    /** The eight in the almanac's order for the year whose branch is [year] (0 = 子). */
    fun of(year: Int): List<YearGod> = HasshoGod.entries.map { YearGod(it, branchOf(it, year)) }

    /** The branch of the year [date] falls in, reckoned from 立春. */
    fun yearBranch(date: LocalDate): Int = Math.floorMod(Rekichu.setsuYearOf(date) - 4, 12)

    fun of(date: LocalDate): List<YearGod> = of(yearBranch(date))

    /**
     * 金神's four directions by the year's stem (0 = 甲), as the 簠簋内伝 gives them (巻一 §5, docs/sources/kyureki.md,
     * RD93; the owner's choice of 2026-10-31, D9): 甲己 午未申酉, 乙庚 辰巳戌亥, 丙辛 子丑寅卯, 丁壬 寅卯戌亥, 戊癸 子丑申酉.
     * The 暦林問答集 does not name it.
     */
    private val KONJIN = arrayOf(intArrayOf(6, 7, 8, 9), intArrayOf(4, 5, 10, 11), intArrayOf(0, 1, 2, 3), intArrayOf(2, 3, 10, 11), intArrayOf(0, 1, 8, 9))

    fun konjin(yearStem: Int): List<Int> = KONJIN[Math.floorMod(yearStem, 5)].toList()

    /** 金神's directions in the year [date] falls in, from 立春. */
    fun konjin(date: LocalDate): List<Int> = konjin(Math.floorMod(Rekichu.setsuYearOf(date) - 4, 10))
}
