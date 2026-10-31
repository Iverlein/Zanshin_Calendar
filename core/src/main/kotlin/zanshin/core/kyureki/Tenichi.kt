/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.kyureki

import zanshin.core.tibetan.Direction

/**
 * 天一神's place on earth, in the order of its course (the 暦林問答集 §53, docs/sources/kyureki.md, RD94): the corner
 * by its trigram, the side by its branch, as §53 names them, and the days it stays there.
 */
enum class TenichiPlace(val kanji: String, val direction: Direction, /** Degrees from north, clockwise. */ val bearing: Int, val days: Int) {
    USHITORA("艮", Direction.NORTH_EAST, 45, 6),
    U("卯", Direction.EAST, 90, 5),
    TATSUMI("巽", Direction.SOUTH_EAST, 135, 6),
    UMA("午", Direction.SOUTH, 180, 5),
    HITSUJISARU("坤", Direction.SOUTH_WEST, 225, 6),
    TORI("酉", Direction.WEST, 270, 5),
    INUI("乾", Direction.NORTH_WEST, 315, 6),
    NE("子", Direction.NORTH, 0, 5),
}

/**
 * 天一神's course (ROADMAP K5, D9; the owner's choice of 2026-10-31): from 己酉 it comes down to the north-east for six
 * days, then five due east, six south-east, five due south, six south-west, five due west, six north-west and five
 * due north, forty-four days on earth; from 癸巳 to 戊申 it is in heaven, the sixteen days of 天一天上.
 */
object Tenichi {
    /** Where 天一神 is on the day [day]; null while it is in heaven. */
    fun of(day: Kanshi): TenichiPlace? {
        var n = Math.floorMod(day.index - 45, 60) // 己酉 = 45
        for (p in TenichiPlace.entries) {
            if (n < p.days) return p
            n -= p.days
        }
        return null
    }

    /** The first and last day of the stay [day] falls in, on earth or in heaven: 己酉 to 甲寅 in the north-east, 癸巳 to 戊申 above. */
    fun stay(day: Kanshi): Pair<Kanshi, Kanshi> {
        val n = Math.floorMod(day.index - 45, 60)
        var start = 0
        for (p in TenichiPlace.entries) {
            if (n < start + p.days) return Kanshi(Math.floorMod(45 + start, 60)) to Kanshi(Math.floorMod(45 + start + p.days - 1, 60))
            start += p.days
        }
        return Kanshi(Math.floorMod(45 + start, 60)) to Kanshi(Math.floorMod(45 + 59, 60))
    }
}
