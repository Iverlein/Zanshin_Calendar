/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

/** The small reckonings of ch. 27 (ROADMAP R6; WB vol. 2, pp. 16–17 and 20; docs/sources/corpus/wb/ch27.md). */
class SmallReckoningsTest {
    private val woodMonkey = Sign(Element.WOOD, Animal.MONKEY)

    /** p. 17's starts: the rat of the same element, the tiger of the power's friend, the dragon of its mother, the horse of the same. */
    @Test
    fun `the four starting points of a wood person`() {
        val starts = StartingPoint.entries.associateWith { SmallReckonings.start(it, woodMonkey) }
        assertEquals(Sign(Element.WOOD, Animal.MOUSE), starts[StartingPoint.SPIRITUAL_FRIEND])
        assertEquals(Sign(Element.EARTH, Animal.TIGER), starts[StartingPoint.MINISTERS])
        assertEquals(Sign(Element.WATER, Animal.DRAGON), starts[StartingPoint.SOLDIERS])
        assertEquals(Sign(Element.WOOD, Animal.HORSE), starts[StartingPoint.WEAPONS])
    }

    /** «ལོ་གྲངས་ཐུར་སྦུབ»: down through the sixty by the years of age, the start the first; the 13th year twelve back. */
    @Test
    fun `the count runs down, the start the first`() {
        val first = SmallReckonings.reckon(StartingPoint.SPIRITUAL_FRIEND, woodMonkey, 1)
        assertEquals(first.start, first.reached)
        val thirteenth = SmallReckonings.reckon(StartingPoint.SPIRITUAL_FRIEND, woodMonkey, 13)
        assertEquals(Sign(Element.WATER, Animal.MOUSE), thirteenth.reached)
    }

    /**
     * The checks counted from the year reached, inclusively: from the wood rat the 18th upward is the iron snake, the
     * 10th upward the water bird, the 18th downward the fire sheep, the 10th downward the wood hare. Like fights like:
     * the iron snake's fire life force overcomes the wood monkey's iron, cut inward.
     */
    @Test
    fun `the four checks and the cut`() {
        val r = SmallReckonings.reckon(StartingPoint.SPIRITUAL_FRIEND, woodMonkey, 1)
        assertEquals(Sign(Element.IRON, Animal.SNAKE), r.checks[StartingCheck.ROPE])
        assertEquals(Sign(Element.WATER, Animal.BIRD), r.checks[StartingCheck.SOLD])
        assertEquals(Sign(Element.FIRE, Animal.SHEEP), r.checks[StartingCheck.DAGGER])
        assertEquals(Sign(Element.WOOD, Animal.RABBIT), r.checks[StartingCheck.BOUGHT])
        assertTrue(StartingCheck.ROPE in r.cut)
        // Each cut is the check year's aspect overcoming the person's own, and nothing else.
        StartingCheck.entries.forEach { c ->
            val own = woodMonkey.forces[c.force]
            val other = r.checks.getValue(c).forces[c.force]
            assertEquals(other.overcomes == own, c in r.cut, "$c")
        }
    }

    /** p. 16: the triads of the sky year and whose la wanders; the snake's and the horse's both north as printed. */
    @Test
    fun `the la's wandering`() {
        assertEquals(LaWander.MOUSE, SmallReckonings.laWander(Animal.MOUSE, Animal.MONKEY))
        assertEquals(LaWander.SNAKE, SmallReckonings.laWander(Animal.SNAKE, Animal.OX))
        assertEquals(LaWander.HORSE, SmallReckonings.laWander(Animal.HORSE, Animal.DOG))
        assertEquals(LaWander.PIG, SmallReckonings.laWander(Animal.PIG, Animal.RABBIT))
        assertNull(SmallReckonings.laWander(Animal.MOUSE, Animal.HORSE))
        assertEquals(Direction.NORTH, LaWander.HORSE.direction)
        // Each sky year has one wandering la among the twelve.
        Animal.entries.forEach { risen -> assertEquals(1, Animal.entries.count { SmallReckonings.laWander(it, risen) != null }) }
    }

    /** pp. 16–17: the tiger declines in the rat's triad, the monkey in the bird's, the bird in the hare's; the horse's triad none. */
    @Test
    fun `the la in decline`() {
        assertEquals(LaDecline.TIGER, SmallReckonings.laDecline(Animal.TIGER, Animal.DRAGON))
        assertEquals(LaDecline.MONKEY, SmallReckonings.laDecline(Animal.MONKEY, Animal.SNAKE))
        assertEquals(LaDecline.BIRD, SmallReckonings.laDecline(Animal.BIRD, Animal.PIG))
        assertNull(SmallReckonings.laDecline(Animal.TIGER, Animal.HORSE))
        Animal.entries.forEach { assertNull(SmallReckonings.laDecline(it, Animal.TIGER)) }
    }

    /** p. 16: the grave of the element entered, and gentle or rough by sex; water not named. */
    @Test
    fun `the grave entered`() {
        assertEquals(Element.WOOD, SmallReckonings.graveEntered(Element.WOOD, Trigram.KHON))
        assertEquals(Element.WATER, SmallReckonings.graveEntered(Element.WATER, Trigram.ZON))
        assertNull(SmallReckonings.graveEntered(Element.FIRE, Trigram.ZON))
        assertEquals(true, SmallReckonings.graveGentle(Element.WOOD, Gender.MALE))
        assertEquals(false, SmallReckonings.graveGentle(Element.FIRE, Gender.MALE))
        assertEquals(true, SmallReckonings.graveGentle(Element.IRON, Gender.FEMALE))
        assertEquals(false, SmallReckonings.graveGentle(Element.EARTH, Gender.FEMALE))
        assertNull(SmallReckonings.graveGentle(Element.WATER, Gender.MALE))
    }

    /** p. 20: born on li, the year's trigram li, in a horse or snake year; born on khon, khon, in a sheep year. */
    @Test
    fun `the end of the womb`() {
        assertEquals(WombEnd.LI, SmallReckonings.wombEnd(Trigram.LI, Trigram.LI, Animal.SNAKE))
        assertEquals(WombEnd.KHON, SmallReckonings.wombEnd(Trigram.KHON, Trigram.KHON, Animal.SHEEP))
        assertNull(SmallReckonings.wombEnd(Trigram.LI, Trigram.KHON, Animal.HORSE))
        assertNull(SmallReckonings.wombEnd(Trigram.KHON, Trigram.KHON, Animal.HORSE))
        assertFalse(SmallReckonings.birthPower(Element.WATER))
        assertTrue(SmallReckonings.birthPower(Element.IRON))
    }
}
