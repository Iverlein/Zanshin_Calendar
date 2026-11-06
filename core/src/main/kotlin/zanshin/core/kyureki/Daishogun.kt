/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.kyureki

import zanshin.core.tibetan.Direction

/**
 * The 大将軍's five outings in the sixty days (the 暦林問答集 §54, docs/sources/kyureki.md, RD92): [first] the index of
 * the day it goes out, [kanji] where it goes as §54 names it, its [branch] (0 = 子; null for 中宮, inside the house).
 * GR's «從中子日» is a misprint: MS has 丙子, and 丙子 to 庚辰 is the five days the others have.
 */
enum class DaishogunOuting(val first: Int, val kanji: String, val direction: Direction, val branch: Int?) {
    U(0, "卯", Direction.EAST, 3),
    UMA(12, "午", Direction.SOUTH, 6),
    CHUGU(24, "中宮", Direction.CENTRE, null),
    TORI(36, "酉", Direction.WEST, 9),
    NE(48, "子", Direction.NORTH, 0),
    ;

    /** Degrees from north, clockwise; null inside the house. */
    val bearing: Int? get() = branch?.let { it * 30 }

    /** The first and last day out: 甲子 to 戊辰 for 卯. */
    val stay: Pair<Kanshi, Kanshi> get() = Kanshi(first) to Kanshi(first + DAYS - 1)

    companion object {
        const val DAYS = 5
    }
}

/**
 * The 大将軍's day by day (ROADMAP K5, the owner's choice of 2026-11-06, the 暦林問答集's five days over the 1937 book's
 * six): out from the 子 day to the 辰 day, back in its own place on each 巳 day and until the next outing. While it is
 * out, its own direction may be built towards, but the direction it has gone to is greatly bad. Where an outing goes to
 * the direction it already holds, §54 does not say (question 490); that direction stays barred.
 */
object Daishogun {
    /** Where the 大将軍 has gone on the day [day]; null while it is in its own place. */
    fun outing(day: Kanshi): DaishogunOuting? = DaishogunOuting.entries.firstOrNull { day.index - it.first in 0 until DaishogunOuting.DAYS }

    /** Whether the outing [o] frees the own direction [own], the branch the 大将軍 holds this year. */
    fun frees(o: DaishogunOuting, own: Int): Boolean = o.branch != own

    /** The compass word for a branch of the four 仲 the 大将軍 stands in. */
    fun direction(branch: Int): Direction = when (branch) {
        0 -> Direction.NORTH
        3 -> Direction.EAST
        6 -> Direction.SOUTH
        9 -> Direction.WEST
        else -> error("the 大将軍 stands in the four 仲 only: $branch")
    }
}
