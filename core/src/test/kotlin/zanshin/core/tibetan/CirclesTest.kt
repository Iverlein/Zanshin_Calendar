/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** The thirteen circles (ROADMAP R2u; WB vol. 2, ch. 27; docs/sources/claims/circles-*.md). */
class CirclesTest {
    private val woodMonkey = Sign(Element.WOOD, Animal.MONKEY)

    /**
     * Zhechen Gyaltsab's worked circle (*Garland of White Utpalas*, pp. 303–304; claims/circles-zg-worked.md): a
     * wood-monkey king starts from the iron bird, iron being the enemy of his wood power; met against the starting year
     * itself, his life force meets itself (iron and iron), his body the son, his power the enemy.
     */
    @Test
    fun `Zhechen Gyaltsab's wood-monkey king`() {
        val king = Circles.reckon(Circle.KING, woodMonkey, 1)
        assertEquals(Sign(Element.IRON, Animal.BIRD), king.start)
        assertEquals(king.start, king.reached)
        val own = woodMonkey.forces
        val there = king.reached.forces
        assertEquals(Kinship.IDENTITY, Forces.kinship(own.vitality, there.vitality))
        assertEquals(Kinship.SON, Forces.kinship(own.body, there.body))
        assertEquals(Kinship.ENEMY, Forces.kinship(own.destiny, there.destiny))
        // Iron meeting iron is black by ch. 27's rule, the son black, the enemy black.
        assertEquals(PebbleColour.BLACK, king.pebbles[Force.VITALITY])
        assertEquals(PebbleColour.BLACK, king.pebbles[Force.BODY])
        assertEquals(PebbleColour.BLACK, king.pebbles[Force.DESTINY])
    }

    /** The count runs upward through the sixty, the starting year the first (D19): the 13th year of age is twelve years on. */
    @Test
    fun `the count is inclusive and upward`() {
        val r = Circles.reckon(Circle.KING, woodMonkey, 13)
        assertEquals(YearOfLife.sexagenary(r.start) + 12, YearOfLife.sexagenary(r.reached).let { if (it < YearOfLife.sexagenary(r.start)) it + 60 else it })
        assertEquals(Animal.BIRD, r.reached.animal)
    }

    /** Each circle's start (pp. 3–21): its animal, in the element its relation names against the power; wood's enemy iron, friend earth, mother water. */
    @Test
    fun `the starting years of a wood person`() {
        val starts = Circle.entries.filter { it != Circle.SERVANTS }.associateWith { Circles.start(it, woodMonkey) }
        assertEquals(Sign(Element.IRON, Animal.BIRD), starts[Circle.KING])
        assertEquals(Sign(Element.IRON, Animal.RABBIT), starts[Circle.SPIRITUAL_FRIENDS])
        assertEquals(Sign(Element.EARTH, Animal.SNAKE), starts[Circle.MANTRINS])
        assertEquals(Sign(Element.WATER, Animal.DRAGON), starts[Circle.BON_PRIESTS])
        assertEquals(Sign(Element.WATER, Animal.MOUSE), starts[Circle.WOMEN])
        assertEquals(Sign(Element.EARTH, Animal.PIG), starts[Circle.STORED_GOODS])
    }

    /**
     * The servant's four nails (reply 140): seen from wood, the earth male tiger, the water male dragon, the wood female
     * pig and the fire female bird, the older text's fixed years.
     */
    @Test
    fun `the servant's four nails seen from wood`() {
        assertEquals(Sign(Element.EARTH, Animal.TIGER), Circles.servantStart(Sign(Element.WOOD, Animal.HORSE)))
        assertEquals(Sign(Element.WATER, Animal.DRAGON), Circles.servantStart(woodMonkey))
        assertEquals(Sign(Element.WOOD, Animal.PIG), Circles.servantStart(Sign(Element.WOOD, Animal.SHEEP)))
        assertEquals(Sign(Element.FIRE, Animal.BIRD), Circles.servantStart(Sign(Element.WOOD, Animal.OX)))
    }

    /** Ch. 27's colours (p. 3): the son black, earth and water by themselves white, wood, iron and fire black. */
    @Test
    fun `the rule of colour`() {
        assertEquals(PebbleColour.BLACK, Circles.colour(Element.WOOD, Element.FIRE))
        assertEquals(PebbleColour.WHITE, Circles.colour(Element.WOOD, Element.WATER))
        assertEquals(PebbleColour.WHITE, Circles.colour(Element.WOOD, Element.EARTH))
        assertEquals(PebbleColour.BLACK, Circles.colour(Element.WOOD, Element.IRON))
        assertEquals(PebbleColour.WHITE, Circles.colour(Element.EARTH, Element.EARTH))
        assertEquals(PebbleColour.BLACK, Circles.colour(Element.FIRE, Element.FIRE))
    }

    /**
     * The golden turtle's classes (p. 20; claims/ch27-turtle-wood-sheep-and-element.md): over the sixty years the
     * counts of each class come to the text's own, god 2, man 1, cow 1, asura 2, animal 1, hungry ghost 1, hell 2,
     * planet 8, re 'jam 9, garuḍa 4, klu 8, turtle 4, earth lord 8, srin po 9.
     */
    @Test
    fun `the golden turtle's classes of the sixty years`() {
        val counts = (0 until 60).map { TurtleClass.of(YearOfLife.signOf(it)) }.groupingBy { it }.eachCount()
        val expected = mapOf(
            TurtleClass.GOD to 2, TurtleClass.MAN to 1, TurtleClass.COW to 1, TurtleClass.ASURA to 2, TurtleClass.ANIMAL to 1,
            TurtleClass.HUNGRY_GHOST to 1, TurtleClass.HELL to 2, TurtleClass.PLANET to 8, TurtleClass.RE_JAM to 9, TurtleClass.GARUDA to 4,
            TurtleClass.KLU to 8, TurtleClass.TURTLE to 4, TurtleClass.EARTH_LORD to 8, TurtleClass.SRIN_PO to 9,
        )
        assertEquals(expected, counts)
        assertEquals(true, TurtleClass.enemies(TurtleClass.KLU, TurtleClass.GARUDA))
        assertEquals(false, TurtleClass.enemies(TurtleClass.COW, TurtleClass.TURTLE))
        assertEquals(TurtleClass.GARUDA to TurtleClass.KLU, TurtleClass.enemyPair(TurtleClass.KLU, TurtleClass.GARUDA))
        assertEquals(TurtleClass.KLU, TurtleClass.sameWithChild(TurtleClass.KLU, TurtleClass.KLU))
        assertEquals(null, TurtleClass.sameWithChild(TurtleClass.GOD, TurtleClass.GOD))
    }
}

