/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import zanshin.core.texts.gloss

/*
 * The thirty starting points (སྐོར་འགོ, skor 'go; ROADMAP R2v; WB vol. 1, ch. 26, pp. 453–473; docs/sources/corpus/wb/ch26.md,
 * RD37, RD44, RD47): fifteen Chinese and fifteen Tibetan, each from a pair of year animals in an element related to the
 * person's power (D21), counted from the pair's male year down for a male birth year and from its female year up for a
 * female one, as many places as the years of age, the pair's year the first (D22); from the year reached eight
 * fighting pebbles, each a place up or down fought against one aspect (p. 453), which are the point's eight checks in
 * order (D23).
 */

/** The thirty points in WB's order, the Chinese fifteen first. */
enum class ThirtyPoint {
    LORDS, SPIRITUAL_FRIENDS, MANTRINS, BON_PRIESTS, ELDERS, YOUNG_MEN, WIVES, SMALL_CHILDREN, GIRLS, SUBJECTS,
    HORSES, LIVESTOCK, COUPLE, BRIDE, HOUSE, GROUND, ARMOUR_VESSEL, LA_TREE, HELPING_GOD, HARMING_SPIRIT,
    OLD, SICK, DEAD, BURNING, GRAVE_SITE, RITES, MEN_LIFE_FORCE, CHILDREN_BODY, WOMEN_POWER, GREAT_MEN_LUCK;

    val english: String get() = gloss(this)

    /** The pair's male animal: the Chinese from the rat, the Tibetan from the horse, two animals a point (p. 453). */
    val animal: Animal get() = Animal.entries[(if (ordinal < 15) 2 * ordinal else 6 + 2 * (ordinal - 15)) % 12]

    /** The element's relation to the person's power: own, son, friend, enemy, mother, and again (p. 453). */
    val relation: Kinship get() = RELATIONS[ordinal % 15 % 5]

    /** Points 21 and 23–25 reckon a dead person's year and age (the notes, vol. 2, pp. 532–533), not the asker's own. */
    val forTheDead: Boolean get() = this in setOf(OLD, DEAD, BURNING, GRAVE_SITE)

    private companion object {
        val RELATIONS = listOf(Kinship.IDENTITY, Kinship.SON, Kinship.FRIEND, Kinship.ENEMY, Kinship.MOTHER)
    }
}

/** One fighting pebble (p. 453): its place counted from the year reached, [step] years up or down, and the aspect it fights. */
enum class ThirtyPebble(val step: Int, val force: Force) {
    FOURTH_UP(3, Force.VITALITY), FOURTH_DOWN(-3, Force.BODY), ELEVENTH_UP(10, Force.DESTINY), ELEVENTH_DOWN(-10, Force.LUCK),
    SEVENTH_UP(6, Force.VITALITY), SEVENTH_DOWN(-6, Force.BODY), REACHED_POWER(0, Force.DESTINY), REACHED_LUCK(0, Force.LUCK),
}

/** A point reckoned for one person: its starting year, the year reached, and the eight pebbles in the order of its checks. */
data class ThirtyReading(val point: ThirtyPoint, val start: Sign, val reached: Sign, val pebbles: List<PebbleColour>) {
    /** The upper three (checks 1, 3, 5), the lower three (2, 4, 6) and the middle two (7, 8) of the board (p. 471; the notes, p. 533). */
    val upper: List<PebbleColour> get() = listOf(pebbles[0], pebbles[2], pebbles[4])
    val lower: List<PebbleColour> get() = listOf(pebbles[1], pebbles[3], pebbles[5])
    val middle: List<PebbleColour> get() = listOf(pebbles[6], pebbles[7])
}

/** The board read whole (WB vol. 1, pp. 471–472): each group by its greater share, and the patterns the commentary names. */
enum class ThirtyBoard {
    UPPER_WHITE, UPPER_BLACK, LOWER_WHITE, LOWER_BLACK, MIDDLE_WHITE, MIDDLE_BLACK,
    UPPER_WHITE_LOWER_BLACK, UPPER_BLACK_LOWER_WHITE, OUTER_BLACK_MIDDLE_WHITE, OUTER_WHITE_MIDDLE_BLACK, ALL_WHITE, ALL_BLACK;

    val english: String get() = gloss(this)
}

object Thirty {
    /**
     * How [r]'s board reads whole (pp. 471–472; the notes, p. 533: within a group of three the greater share decides,
     * two white and one black good): all white or all black alone; otherwise each group that decides, the middle two
     * only when alike, then the patterns that hold.
     */
    fun board(r: ThirtyReading): List<ThirtyBoard> {
        if (r.pebbles.all { it == PebbleColour.WHITE }) return listOf(ThirtyBoard.ALL_WHITE)
        if (r.pebbles.all { it == PebbleColour.BLACK }) return listOf(ThirtyBoard.ALL_BLACK)
        fun share(g: List<PebbleColour>) = if (g.count { it == PebbleColour.WHITE } * 2 > g.size) PebbleColour.WHITE else PebbleColour.BLACK
        val upper = share(r.upper)
        val lower = share(r.lower)
        val middle = r.middle.distinct().singleOrNull()
        return buildList {
            add(if (upper == PebbleColour.WHITE) ThirtyBoard.UPPER_WHITE else ThirtyBoard.UPPER_BLACK)
            add(if (lower == PebbleColour.WHITE) ThirtyBoard.LOWER_WHITE else ThirtyBoard.LOWER_BLACK)
            middle?.let { add(if (it == PebbleColour.WHITE) ThirtyBoard.MIDDLE_WHITE else ThirtyBoard.MIDDLE_BLACK) }
            when {
                upper == PebbleColour.WHITE && lower == PebbleColour.BLACK -> add(ThirtyBoard.UPPER_WHITE_LOWER_BLACK)
                upper == PebbleColour.BLACK && lower == PebbleColour.WHITE -> add(ThirtyBoard.UPPER_BLACK_LOWER_WHITE)
                upper == PebbleColour.BLACK && middle == PebbleColour.WHITE -> add(ThirtyBoard.OUTER_BLACK_MIDDLE_WHITE)
                upper == PebbleColour.WHITE && middle == PebbleColour.BLACK -> add(ThirtyBoard.OUTER_WHITE_MIDDLE_BLACK)
            }
        }
    }

    /** Where [point] starts for one born in [birth]: the pair's male year for a male birth year, its female year for a female one. */
    fun start(point: ThirtyPoint, birth: Sign): Sign {
        val element = Circles.element(birth.forces.destiny, point.relation)
        val male = birth.animal.gender == Gender.MALE
        return Sign(element, if (male) point.animal else Animal.entries[point.animal.ordinal + 1])
    }

    /** [point] for one born in [birth] in the [age]th year of age: the count down from a male year, up from a female, inclusively (D22). */
    fun reckon(point: ThirtyPoint, birth: Sign, age: Int): ThirtyReading {
        val start = start(point, birth)
        val down = birth.animal.gender == Gender.MALE
        val at = YearOfLife.sexagenary(start) + (if (down) -(age - 1) else age - 1)
        val reached = YearOfLife.signOf(at)
        val own = birth.forces
        val pebbles = ThirtyPebble.entries.map { p ->
            Circles.colour(own[p.force], YearOfLife.signOf(at + p.step).forces[p.force])
        }
        return ThirtyReading(point, start, reached, pebbles)
    }

    /** The points the asker reads for themself: all but those that reckon a dead person's year. */
    fun all(birth: Sign, age: Int): List<ThirtyReading> = ThirtyPoint.entries.filter { !it.forTheDead }.map { reckon(it, birth, age) }
}
