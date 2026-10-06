/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import zanshin.core.tibetan.Direction.EAST
import zanshin.core.tibetan.Direction.NORTH
import zanshin.core.tibetan.Direction.NORTH_EAST
import zanshin.core.tibetan.Direction.NORTH_WEST
import zanshin.core.tibetan.Direction.SOUTH
import zanshin.core.tibetan.Direction.SOUTH_EAST
import zanshin.core.tibetan.Direction.SOUTH_WEST
import zanshin.core.tibetan.Direction.WEST

/** Where Rāhu moves on a lunar date (SPEC §5.13). */
sealed interface RahuMove {
    /** From one direction to another. */
    data class Across(val from: Direction, val to: Direction) : RahuMove

    /** The 14th's general course: from the sky into the lake (the *kun phan me long*: into the ocean). */
    data object IntoTheLake : RahuMove

    /** The 30th's general course: from the meeting place of the gods through above, below and every direction. */
    data object Everywhere : RahuMove
}

/**
 * Rāhu's course by lunar date, the directions of its readings (`Texts.RAHU`,
 * `Texts.RAHU_GENERAL`; docs/sources/rahu.md).
 */
object RahuCourse {
    /**
     * The general *nu ru* course of all thirty dates, the White Beryl vol. 2,
     * pp. 236–237, confirmed date by date by the *kun phan me long*'s chart
     * (§7, img. 78), which settles the 15th and the 18th as south-west to
     * north-east.
     */
    val GENERAL: Map<Int, RahuMove> = mapOf(
        1 to across(SOUTH_WEST, NORTH_EAST),
        2 to across(EAST, WEST),
        3 to across(SOUTH_WEST, NORTH_EAST),
        4 to across(WEST, EAST),
        5 to across(EAST, WEST),
        6 to across(NORTH, SOUTH),
        7 to across(NORTH, SOUTH),
        8 to across(SOUTH_EAST, NORTH_WEST),
        9 to across(NORTH_EAST, SOUTH_WEST),
        10 to across(SOUTH, NORTH),
        11 to across(NORTH, SOUTH),
        12 to across(SOUTH, NORTH),
        13 to across(NORTH, SOUTH),
        14 to RahuMove.IntoTheLake,
        15 to across(SOUTH_WEST, NORTH_EAST),
        16 to across(NORTH_EAST, SOUTH_WEST),
        17 to across(WEST, EAST),
        18 to across(SOUTH_WEST, NORTH_EAST),
        19 to across(SOUTH, NORTH),
        20 to across(SOUTH_EAST, NORTH_WEST),
        21 to across(EAST, WEST),
        22 to across(NORTH_WEST, SOUTH_EAST),
        23 to across(NORTH_WEST, SOUTH_EAST),
        24 to across(NORTH, SOUTH),
        25 to across(SOUTH, NORTH),
        26 to across(EAST, WEST),
        27 to across(SOUTH_WEST, NORTH_EAST),
        28 to across(NORTH, SOUTH),
        29 to across(NORTH_EAST, SOUTH_WEST),
        30 to RahuMove.Everywhere,
    )

    /**
     * The detailed course's move on the eight dates on which Rāhu enters a
     * direction and moves on to another (vol. 2, pp. 237–238). On its other
     * eight dates it only turns back, naming no course.
     */
    val DETAILED: Map<Int, RahuMove.Across> = mapOf(
        4 to across(WEST, EAST),
        8 to across(SOUTH_EAST, NORTH_WEST),
        12 to across(NORTH, SOUTH),
        15 to across(SOUTH_WEST, NORTH_EAST),
        18 to across(EAST, WEST),
        22 to across(NORTH_WEST, SOUTH_EAST),
        25 to across(SOUTH, NORTH),
        29 to across(NORTH_EAST, SOUTH_WEST),
    )

    /**
     * The move the date's Rāhu row shows: the detailed course's where it names
     * one, which the White Beryl calls the more exact, else the general course.
     */
    fun of(date: Int): RahuMove = DETAILED[date] ?: GENERAL.getValue(date)

    /** True when [of] gives the general course for [date]. */
    fun isGeneral(date: Int): Boolean = date !in DETAILED

    private fun across(from: Direction, to: Direction) = RahuMove.Across(from, to)
}
