/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** The thirty starting points (ROADMAP R2v; WB vol. 1, ch. 26; docs/sources/claims/thirty-*.md; D21–D23). */
class ThirtyTest {
    /** The pairs of p. 453 (claims/thirty-starting-pairs.md): the Chinese from the rat, the Tibetan from the horse, own, son, friend, enemy, mother. */
    @Test
    fun `the thirty pairs`() {
        assertEquals(Animal.MOUSE to Kinship.IDENTITY, ThirtyPoint.LORDS.animal to ThirtyPoint.LORDS.relation)
        assertEquals(Animal.MONKEY to Kinship.MOTHER, ThirtyPoint.ELDERS.animal to ThirtyPoint.ELDERS.relation)
        assertEquals(Animal.DRAGON to Kinship.MOTHER, ThirtyPoint.HOUSE.animal to ThirtyPoint.HOUSE.relation)
        assertEquals(Animal.HORSE to Kinship.IDENTITY, ThirtyPoint.GROUND.animal to ThirtyPoint.GROUND.relation)
        assertEquals(Animal.MOUSE to Kinship.ENEMY, ThirtyPoint.HELPING_GOD.animal to ThirtyPoint.HELPING_GOD.relation)
        assertEquals(Animal.HORSE to Kinship.FRIEND, ThirtyPoint.CHILDREN_BODY.animal to ThirtyPoint.CHILDREN_BODY.relation)
        assertEquals(Animal.DOG to Kinship.MOTHER, ThirtyPoint.GREAT_MEN_LUCK.animal to ThirtyPoint.GREAT_MEN_LUCK.relation)
    }

    /** A male birth year starts from the pair's male year and counts down, a female one from the female year up, inclusively (D22). */
    @Test
    fun `the count by the birth year's sex`() {
        val woodMouse = Sign(Element.WOOD, Animal.MOUSE)
        val m = Thirty.reckon(ThirtyPoint.LORDS, woodMouse, 1)
        assertEquals(Sign(Element.WOOD, Animal.MOUSE), m.start)
        assertEquals(m.start, m.reached)
        assertEquals(Sign(Element.WATER, Animal.PIG), Thirty.reckon(ThirtyPoint.LORDS, woodMouse, 2).reached)
        val woodOx = Sign(Element.WOOD, Animal.OX)
        assertEquals(Sign(Element.WOOD, Animal.OX), Thirty.start(ThirtyPoint.LORDS, woodOx))
        assertEquals(Sign(Element.FIRE, Animal.TIGER), Thirty.reckon(ThirtyPoint.LORDS, woodOx, 2).reached)
    }

    /** The eight pebbles (p. 453): for a wood-mouse's first year from the wood mouse itself, the life force fought at the fourth up, the wood rabbit. */
    @Test
    fun `the eight fighting pebbles`() {
        val woodMouse = Sign(Element.WOOD, Animal.MOUSE)
        val r = Thirty.reckon(ThirtyPoint.LORDS, woodMouse, 1)
        val own = woodMouse.forces
        val fourthUp = YearOfLife.signOf(YearOfLife.sexagenary(woodMouse) + 3)
        assertEquals(Circles.colour(own.vitality, fourthUp.forces.vitality), r.pebbles[0])
        // The year reached itself, its power and luck, the seventh and eighth: the wood mouse against itself, wood own, black.
        assertEquals(PebbleColour.BLACK, r.pebbles[6])
        assertEquals(8, r.pebbles.size)
    }

    /** The board read whole (pp. 471–472): all white alone, and groups by their greater share. */
    @Test
    fun `the board`() {
        val w = PebbleColour.WHITE
        val b = PebbleColour.BLACK
        fun board(vararg p: PebbleColour) = Thirty.board(ThirtyReading(ThirtyPoint.LORDS, Sign(Element.WOOD, Animal.MOUSE), Sign(Element.WOOD, Animal.MOUSE), p.toList()))
        assertEquals(listOf(ThirtyBoard.ALL_WHITE), board(w, w, w, w, w, w, w, w))
        assertEquals(listOf(ThirtyBoard.UPPER_WHITE, ThirtyBoard.LOWER_BLACK, ThirtyBoard.UPPER_WHITE_LOWER_BLACK), board(w, b, w, b, b, b, w, b))
        assertEquals(listOf(ThirtyBoard.UPPER_BLACK, ThirtyBoard.LOWER_BLACK, ThirtyBoard.MIDDLE_WHITE, ThirtyBoard.OUTER_BLACK_MIDDLE_WHITE), board(b, b, b, b, w, b, w, w))
    }
}
