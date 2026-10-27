/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** The moment's trigram and mewa (ROADMAP R2o; docs/sources/reckonings.md, question 164). */
class MomentTest {
    /**
     * O16's check: counted one a date, a month of thirty ends where the next month's group begins, round the year: a
     * tiger month starting on li ends on gin and the hare month starts on zin; a first month starting on 1 ends on 3
     * and the middle month starts on 4.
     */
    @Test
    fun `the months' starts follow from one a date`() {
        val year = listOf(Animal.TIGER, Animal.RABBIT, Animal.DRAGON, Animal.SNAKE, Animal.HORSE, Animal.SHEEP, Animal.MONKEY, Animal.BIRD, Animal.DOG, Animal.PIG, Animal.MOUSE, Animal.OX)
        assertEquals(Trigram.GIN, MomentReckoning.dateTrigram(Animal.TIGER, 30))
        year.zipWithNext().forEach { (a, b) ->
            assertEquals(MomentReckoning.monthTrigram(b), Trigram.entries[(MomentReckoning.dateTrigram(a, 30).ordinal + 1) % 8], "$a $b")
            assertEquals(MomentReckoning.monthMewa(b), MomentReckoning.dateMewa(a, 30) % 9 + 1, "$a $b")
        }
    }

    /** P. 162's rough spans: eight trigrams in the twelve hours of day, nine mewa, and the night starting again on the date's. */
    @Test
    fun `the day's spans`() {
        assertEquals(Trigram.LI, MomentReckoning.trigram(Animal.TIGER, 1, 0))
        assertEquals(Trigram.KHON, MomentReckoning.trigram(Animal.TIGER, 1, 90))
        assertEquals(Trigram.ZON, MomentReckoning.trigram(Animal.TIGER, 1, 719))
        assertEquals(Trigram.LI, MomentReckoning.trigram(Animal.TIGER, 1, 720))
        assertEquals(1, MomentReckoning.mewa(Animal.TIGER, 1, 79))
        assertEquals(2, MomentReckoning.mewa(Animal.TIGER, 1, 80))
        assertEquals(9, MomentReckoning.mewa(Animal.TIGER, 1, 719))
        assertEquals(1, MomentReckoning.mewa(Animal.TIGER, 1, 1439 - 640))
    }

    /** P. 156's seven headings on the hours from the hare: dawn at 05:00, sunset in the bird's hour, none after 19:00. */
    @Test
    fun `the periods of the day`() {
        assertEquals(MomentPeriod.DAWN, MomentReckoning.period(0))
        assertEquals(MomentPeriod.NOON, MomentReckoning.period(6 * 60 + 30))
        assertEquals(MomentPeriod.SUNSET, MomentReckoning.period(12 * 60 + 59))
        assertEquals(null, MomentReckoning.period(14 * 60))
    }

    /** The hour's power follows on by sons from the day's: a wood day's dawn is fire, its sunrise earth, its late afternoon fire again. */
    @Test
    fun `the hour's element`() {
        assertEquals(Element.FIRE, MomentReckoning.hourElement(Element.WOOD, MomentPeriod.DAWN))
        assertEquals(Element.EARTH, MomentReckoning.hourElement(Element.WOOD, MomentPeriod.SUNRISE))
        assertEquals(Element.FIRE, MomentReckoning.hourElement(Element.WOOD, MomentPeriod.LATE))
    }

    /**
     * The notes' moment of an iron snake (vol. 2, p. 536): a wood day's snake hour, the sun's warmth, has the power
     * iron; its life force fire, body iron, luck water; oneself in the west, the paternal kin in the south, the maternal
     * uncle in the west, the maid in the east, the lord in the north, the enemy in the south; iron on the snake falls on
     * birth, a good stage, so friends and no enemy.
     */
    @Test
    fun `the notes' iron snake`() {
        val moment = MomentPlaces.sign(Element.WOOD, 4 * 60 + 10)!!
        assertEquals(Sign(Element.IRON, Animal.SNAKE), moment)
        assertEquals(YearForces(Element.FIRE, Element.IRON, Element.IRON, Element.WATER), moment.forces)
        assertEquals(
            listOf(
                OwnPlaceRole.ASKER to Direction.WEST, OwnPlaceRole.PATERNAL_KIN to Direction.SOUTH, OwnPlaceRole.MATERNAL_UNCLE to Direction.WEST,
                OwnPlaceRole.MAID to Direction.EAST, OwnPlaceRole.LORD to Direction.NORTH, OwnPlaceRole.ENEMY to Direction.SOUTH,
            ),
            MomentPlaces.ownPlace(moment),
        )
        assertEquals(Sector.BIRTH, MomentPlaces.kinStage(moment))
        assertEquals(null, MomentPlaces.sign(Element.WOOD, 14 * 60))
    }

    /** The notes' three mewa joined (p. 536): the day's 4 green in the centre, the year's 8 white meets it as a friend, the east's 2 black as a son, the north-east's 7 red as an enemy. */
    @Test
    fun `the notes' three mewa`() {
        val squares = MomentPlaces.squares(dayMewa = 4, yearMewa = 8).associateBy { it.square }
        assertEquals(4, squares.getValue(MomentSquare.CENTRE).mewa)
        assertEquals(Kinship.FRIEND, squares.getValue(MomentSquare.CENTRE).kinship)
        assertEquals(2, squares.getValue(MomentSquare.EAST).mewa)
        assertEquals(Kinship.SON, squares.getValue(MomentSquare.EAST).kinship)
        assertEquals(7, squares.getValue(MomentSquare.NORTH_EAST).mewa)
        assertEquals(Kinship.ENEMY, squares.getValue(MomentSquare.NORTH_EAST).kinship)
        assertEquals(SquareFate.CUT, squares.getValue(MomentSquare.NORTH_EAST).fate)
        assertEquals(PebbleColour.BLACK, squares.getValue(MomentSquare.CENTRE).colour)
        assertEquals(SquareGuard.DEMONS, squares.getValue(MomentSquare.CENTRE).guard)
        assertEquals(null, squares.getValue(MomentSquare.CENTRE).fate)
        assertEquals((1..9).toSet(), squares.values.map { it.mewa }.toSet())
    }

    /** The notes' figure (p. 535): with 5 yellow in the centre, 7 red falls in the forefathers' place, the crown to the west. */
    @Test
    fun `the forefathers' place`() {
        assertEquals(7, MomentPlaces.forefathers(5))
        assertEquals(DaySmeBa.directionOf(MomentPlaces.forefathers(1), 1), Direction.WEST)
    }

    /** The asker's places fight like with like; the grave has WB's readings only (pp. 167–168). */
    @Test
    fun `the asker's places`() {
        val asker = Forces.of(Element.FIRE, Animal.HORSE)
        val moment = Sign(Element.IRON, Animal.SNAKE)
        val house = MomentPlaces.meet(AskerPlace.HOUSE, asker, moment)
        assertEquals(Element.FIRE to Element.FIRE, house.asker to house.moment)
        assertEquals(Kinship.IDENTITY, house.kinship)
        val field = MomentPlaces.meet(AskerPlace.FIELD, asker, moment)
        assertEquals(Kinship.FRIEND, field.kinship)
        assertEquals("SAME.WOOD", MomentPlaces.graveKey(AskerMeet(AskerPlace.GRAVE, Element.WOOD, Element.WOOD)))
        assertEquals(null, MomentPlaces.graveKey(AskerMeet(AskerPlace.GRAVE, Element.EARTH, Element.EARTH)))
        assertEquals("FIRE_WATER", MomentPlaces.graveKey(AskerMeet(AskerPlace.GRAVE, Element.FIRE, Element.WATER)))
        assertEquals("FIRE_IRON", MomentPlaces.graveKey(AskerMeet(AskerPlace.GRAVE, Element.FIRE, Element.IRON)))
        assertEquals(null, MomentPlaces.graveKey(AskerMeet(AskerPlace.GRAVE, Element.WOOD, Element.EARTH)))
        assertEquals("KIN", MomentPlaces.graveKey(AskerMeet(AskerPlace.GRAVE, Element.WOOD, Element.WATER)))
    }

    /** The notes' example of the third gate (p. 536): a wood rat counts from the fire male tiger, a wood ox from the fire female hare. */
    @Test
    fun `the third gate's start`() {
        assertEquals(Sign(Element.FIRE, Animal.TIGER), MomentGates.start(MomentGate.FIRE_OX, Sign(Element.WOOD, Animal.MOUSE), male = true))
        assertEquals(Sign(Element.FIRE, Animal.RABBIT), MomentGates.start(MomentGate.FIRE_OX, Sign(Element.WOOD, Animal.OX), male = false))
    }

    /** The count runs through the sixty, the start the first year: a man down, a woman up; the king's gate down for all. */
    @Test
    fun `the gates' count`() {
        val start = Sign(Element.WOOD, Animal.MOUSE)
        assertEquals(start, MomentGates.counted(start, 1, down = true))
        assertEquals(Sign(Element.WATER, Animal.PIG), MomentGates.counted(start, 2, down = true))
        assertEquals(Sign(Element.WOOD, Animal.OX), MomentGates.counted(start, 2, down = false))
        val moment = Sign(Element.IRON, Animal.SNAKE)
        val gates = MomentGates.gates(Sign(Element.FIRE, Animal.DRAGON), 51, male = false, moment)
        val king = gates.first { it.gate == MomentGate.KING }
        assertEquals(Sign(Element.FIRE, Animal.MOUSE), king.start)
        assertEquals(MomentGates.counted(king.start, 51, down = true), king.reached)
        assertEquals(2, king.years.size)
        gates.flatMap { it.years }.forEach { assertEquals(it.year.element == Element.FIRE, it.cuts) }
    }

    /** A door falls by its landing, not by a cut (p. 172): from the iron dragon, eight up landing on a dog or a dragon. */
    @Test
    fun `the doors`() {
        val moment = Sign(Element.IRON, Animal.SNAKE)
        (1..80).forEach { age ->
            val door = MomentGates.sixty(Sign(Element.FIRE, Animal.DRAGON), age, male = true, moment).any { it.count == SixtyCount.SKY_DOOR }
            val reached = MomentGates.counted(Sign(Element.IRON, Animal.DRAGON), age, down = true)
            assertEquals(YearOfLife.signOf(YearOfLife.sexagenary(reached) + 7).animal in setOf(Animal.DOG, Animal.DRAGON), door, "$age")
        }
    }

    /** The kin's places on the figure (the notes, p. 535): with 5 yellow in the centre, 7 red at the forefathers', 2 black at the father's. */
    @Test
    fun `the kin's places`() {
        val places = MomentPlaces.kinPlaces(5).toMap()
        assertEquals(5, places.getValue(KinPlace.HOUSEHOLD))
        assertEquals(7, places.getValue(KinPlace.FOREFATHERS))
        assertEquals(2, places.getValue(KinPlace.FATHER))
    }

    /**
     * WB's wood moment (p. 169): to the right a white house (iron, the enemy), behind a blue one (water, the mother), in
     * front a red one (fire, the son); and the sick one at a wood moment (p. 170): a red pillow (fire, the son), a white
     * mat (iron, the enemy), fried pastry eaten (earth, the friend).
     */
    @Test
    fun `WB's wood moment`() {
        val houses = MomentSigns.houses(Sign(Element.WOOD, Animal.TIGER)).toMap()
        assertEquals(Element.WOOD, houses.getValue(HouseSide.CENTRE))
        assertEquals(Element.IRON, houses.getValue(HouseSide.RIGHT))
        assertEquals(Element.EARTH, houses.getValue(HouseSide.LEFT))
        assertEquals(Element.WATER, houses.getValue(HouseSide.BEHIND))
        assertEquals(Element.FIRE, houses.getValue(HouseSide.FRONT))
        val bed = MomentSigns.sickBed(Sign(Element.WOOD, Animal.TIGER)).toMap()
        assertEquals(Element.FIRE, bed.getValue(Kinship.SON))
        assertEquals(Element.IRON, bed.getValue(Kinship.ENEMY))
        assertEquals(Element.EARTH, bed.getValue(Kinship.FRIEND))
    }

    /** The small examinations follow the stages: the sick one's life, food eaten and the organs (p. 170). */
    @Test
    fun `the small examinations`() {
        val moment = Sign(Element.IRON, Animal.SNAKE)
        Element.entries.forEach { e ->
            val f = YearForces(e, e, e, e)
            val stage = MomentPlaces.stage(e, moment)
            val life = MomentSigns.sickLife(f, moment)
            assertEquals(stage.good, life == SickLife.LIVES, "$e")
            assertEquals(stage == Sector.TAKING_BREATH || stage == Sector.IN_THE_WOMB, life == SickLife.HARD, "$e")
        }
        assertEquals(Organ.entries.filter { !MomentPlaces.stage(it.element, moment).good }, MomentSigns.illnessSeat(moment))
        assertEquals(BeerDrunk.NOT_FINISHED, MomentSigns.beerDrunk(Sign(Element.FIRE, Animal.SHEEP), moment))
        assertEquals(BeerDrunk.HALF, MomentSigns.beerDrunk(Sign(Element.FIRE, Animal.DRAGON), moment))
        assertEquals(BeerDrunk.FINISHED, MomentSigns.beerDrunk(Sign(Element.FIRE, Animal.DRAGON), Sign(Element.IRON, Animal.MONKEY)))
    }
}
