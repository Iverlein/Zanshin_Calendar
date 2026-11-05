/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import java.time.LocalDate

/** A pebble's colour in the household round: white, black or speckled from a deciding pebble, any but speckled from a mewa. */
enum class RoundColour { WHITE, BLACK, BLUE, GREEN, YELLOW, RED, SPECKLED }

/** One of the household, with their year of age and, for two born in one year, their birth date if known. */
data class HouseholdMember(val year: YearReckoning, val born: LocalDate? = null, val tag: Int? = null)

/** A pebble of the round: whose it is and of which aspect, the [force] null for the member's mewa. */
data class RoundPebble(val member: HouseholdMember, val force: Force?, val colour: RoundColour)

/** A reading the round gives: the [family] and [key] of the catalog, and whether it is good, null for a mixed lot. */
data class RoundRead(val family: String, val key: String, val good: Boolean?)

/**
 * The household round (རྒྱ་ནག་རྡེལ་སྐོར, rgya nag rdel skor; WB vol. 1, pp. 385–387, the notes in vol. 2, pp. 490–491;
 * SPEC §5.9.10): the plain row of every member's pebbles taken in a zigzag and folded into the four places. [fold] is
 * the fold's leftover and [refold] the refold's two, when the count leaves them.
 */
data class HouseholdRound(
    val members: List<HouseholdMember>,
    val plain: List<RoundPebble>,
    val god: List<RoundPebble>,
    val people: List<RoundPebble>,
    val house: List<RoundPebble>,
    val enemy: List<RoundPebble>,
    val fold: RoundPebble?,
    val refold: List<RoundPebble>,
) {
    /** What each row gives by the colour of its best, middle and last pebbles, the first three. */
    private fun colours(row: List<RoundPebble>) = row.take(3).map { it.colour }.toSet()

    val godReads: List<RoundRead> = colours(god).let { c ->
        listOfNotNull(
            read("HouseholdGod", RoundColour.BLACK, c), read("HouseholdGod", RoundColour.RED, c),
            RoundRead("HouseholdGod", "BLUE_RED", false).takeIf { RoundColour.BLUE in c && RoundColour.RED in c },
            read("HouseholdGod", RoundColour.GREEN, c), read("HouseholdGod", RoundColour.YELLOW, c), read("HouseholdGod", RoundColour.WHITE, c),
        )
    }

    val peopleReads: List<RoundRead> = colours(people).let { c ->
        listOf(RoundColour.WHITE, RoundColour.BLACK, RoundColour.BLUE, RoundColour.GREEN, RoundColour.RED, RoundColour.YELLOW).mapNotNull { read("HouseholdPeople", it, c) }
    }

    /** The tails of the first two rows (p. 386): green has no line. */
    val tailReads: List<RoundRead> = listOf(god.last(), people.last()).map { it.colour }.filter { it != RoundColour.GREEN && it != RoundColour.SPECKLED }.distinct()
        .map { RoundRead("HouseholdTail", it.name, it == RoundColour.WHITE) }

    val houseReads: List<RoundRead> = colours(house).let { c ->
        listOfNotNull(
            read("HouseholdHouse", RoundColour.WHITE, c), read("HouseholdHouse", RoundColour.BLACK, c),
            RoundRead("HouseholdHouse", "BLUE_GREEN", false).takeIf { RoundColour.BLUE in c || RoundColour.GREEN in c },
            read("HouseholdHouse", RoundColour.YELLOW, c), read("HouseholdHouse", RoundColour.RED, c),
        )
    }

    /** The leftovers' verdicts (p. 386): the refold's two by their white and black, all three when the fold has one too. */
    val leftoverReads: List<RoundRead> = buildList {
        val colours = refold.map { it.colour }
        val (whites, blacks) = colours.count { it == RoundColour.WHITE } to colours.count { it == RoundColour.BLACK }
        if (colours.size == 2) when {
            whites == 2 -> add(RoundRead("HouseholdLeftover", "TWO_WHITE", true))
            whites == 1 && blacks == 1 -> add(RoundRead("HouseholdLeftover", "MIXED", null))
            blacks == 2 -> add(RoundRead("HouseholdLeftover", "TWO_BLACK", false))
            blacks == 1 -> add(RoundRead("HouseholdLeftover", "ONE_BLACK", false))
        }
        val all = colours + listOfNotNull(fold?.colour)
        if (all.size == 3) {
            if (RoundColour.WHITE in all && RoundColour.BLACK in all && all.all { it == RoundColour.WHITE || it == RoundColour.BLACK }) add(RoundRead("HouseholdLeftover", "BONE", false))
            if (all.toSet() == setOf(RoundColour.BLUE, RoundColour.RED, RoundColour.YELLOW)) add(RoundRead("HouseholdLeftover", "WATER", false))
        }
        if (RoundColour.GREEN in all) add(RoundRead("HouseholdLeftover", "GREEN", false))
    }

    /** The enemy's place (pp. 386–387): white raises the enemy's head and at the top is a lawsuit, every other colour brings it low. */
    val enemyReads: List<RoundRead> = buildList {
        val c = colours(enemy)
        if (RoundColour.WHITE in c) add(RoundRead("HouseholdEnemy", "HIGH", false)) else if (c.any { it != RoundColour.SPECKLED }) add(RoundRead("HouseholdEnemy", "LOW", true))
        if (enemy.first().colour == RoundColour.WHITE) add(RoundRead("HouseholdEnemy", "TOP", false))
    }

    /** The first of the god's place read with the two remainders (p. 387), when the count leaves both. */
    val omenReads: List<RoundRead> = buildList {
        val folded = fold?.colour
        if (folded != null && refold.isNotEmpty()) {
            val first = god.first().colour
            if (first == RoundColour.WHITE && folded == RoundColour.WHITE && refold.all { it.colour == RoundColour.WHITE }) add(RoundRead("HouseholdOmen", "GODS_ILLNESS", false))
            if (first == RoundColour.BLACK && folded == RoundColour.WHITE && refold.all { it.colour == RoundColour.BLACK }) add(RoundRead("HouseholdOmen", "DEMONS", false))
        }
    }

    /** Every reading of the round, in the order of its places. */
    val reads: List<RoundRead> get() = godReads + peopleReads + tailReads + houseReads + leftoverReads + enemyReads + omenReads

    /** Of the whole round's white and black (p. 415, the household's general pebbles), whether black are the more. */
    val blackTheMore: Boolean get() = plain.count { it.colour == RoundColour.BLACK } > plain.count { it.colour == RoundColour.WHITE }

    private fun read(family: String, colour: RoundColour, shown: Set<RoundColour>): RoundRead? =
        RoundRead(family, colour.name, colour == RoundColour.WHITE).takeIf { colour in shown }

    companion object {
        /** The notes (p. 490): not fewer than four; three would give rows too short for three places and a tail. */
        const val FEWEST = 4

        /** Each column from the top, the life force's and the body's rows exchanged as the oral instruction (the notes, p. 490). */
        val COLUMN: List<Force?> = listOf(Force.BODY, Force.VITALITY, Force.DESTINY, Force.LUCK, null)

        /** The round's order (the notes, p. 490): men first, then women, each eldest to youngest, the earlier birth first in one year. */
        fun ordered(members: List<HouseholdMember>): List<HouseholdMember> =
            members.sortedWith(compareBy({ it.year.gender != Gender.MALE }, { it.year.birthYear }, { it.born ?: LocalDate.MAX }))

        /** What a member brings: the deciding pebble of each aspect of their year of age, and a pebble of their mewa's colour. */
        fun column(m: HouseholdMember): List<RoundPebble> = COLUMN.map { f ->
            RoundPebble(m, f, if (f == null) mewaColour(m.year.currentSmeBa) else deciding(m.year.predictive(f)!!))
        }

        private fun deciding(c: PebbleColour) = when (c) {
            PebbleColour.WHITE -> RoundColour.WHITE
            PebbleColour.BLACK -> RoundColour.BLACK
            PebbleColour.SPECKLED -> RoundColour.SPECKLED
        }

        private fun mewaColour(n: Int) = RoundColour.valueOf(SME_BA_COLOURS[n - 1].uppercase())

        /**
         * The plain row: the columns end to end, taken alternately from the two ends, the front first. The rule is worked out
         * from the notes' laying of five, which it reproduces cell by cell (claims/household-round-zigzag.md).
         */
        fun <T> zigzag(line: List<T>): List<T> = buildList {
            var (front, back) = 0 to line.lastIndex
            while (front <= back) {
                add(line[front++])
                if (front <= back) add(line[back--])
            }
        }

        /**
         * The round of [members], every one of them reckoned whole (a gender and an hour); null below [FEWEST]. The row
         * folded in half and in half again as the notes do (p. 491; claims/household-round-fold.md): the fold's leftover
         * the middle pebble of an odd row, the refold's the middle column of an odd fold.
         */
        fun of(members: List<HouseholdMember>): HouseholdRound? {
            if (members.size < FEWEST || members.any { !it.year.complete }) return null
            val order = ordered(members)
            val plain = zigzag(order.flatMap(::column))
            val half = plain.size / 2
            val fold = plain.getOrNull(half).takeIf { plain.size % 2 == 1 }
            val top = plain.take(half)
            val bottom = plain.takeLast(half).reversed()
            val quarter = half / 2
            return HouseholdRound(
                order, plain,
                god = top.take(quarter),
                people = bottom.take(quarter),
                house = bottom.takeLast(quarter).reversed(),
                enemy = top.takeLast(quarter).reversed(),
                fold = fold,
                refold = if (half % 2 == 1) listOf(top[quarter], bottom[quarter]) else emptyList(),
            )
        }
    }
}
