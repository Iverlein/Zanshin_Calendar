/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import zanshin.core.vectors

class ForcesTest {
    private fun element(name: String) = Element.valueOf(name.uppercase())

    /** Year n of the sexagenary cycle, 1 = wood mouse. */
    private fun forcesOfYear(n: Int) = Forces.of(Element.entries[(n - 1) % 10 / 2], Animal.entries[(n - 1) % 12])

    /** The White Beryl's sixty-year charts, as printed in Gyurme Dorje's edition (2001), pp. 70–85. */
    @Test
    fun `aspects of all sixty years match the charts`() {
        val rows = vectors("gyurme-dorje-forces.tsv")
        assertEquals(60, rows.size)
        val kinshipOf = mapOf(
            "kha-yan" to Kinship.IDENTITY, "khong-nong" to Kinship.MOTHER, "se-zhig" to Kinship.SON,
            "kha-ral" to Kinship.FRIEND, "'dun-khur" to Kinship.ENEMY,
        )
        for (r in rows) {
            val n = r[0].toInt()
            val f = forcesOfYear(n)
            for ((i, force) in Force.entries.withIndex()) {
                val printed = r[i + 1]
                if (printed.isNotEmpty()) assertEquals(element(printed), f[force], "year $n ${force.english}")
            }
            // The chart's own row: how the destiny element stands to the vitality element.
            kinshipOf[r.getOrElse(5) { "" }]?.let { assertEquals(it, Forces.kinship(f.vitality, f.destiny), "year $n") }
        }
    }

    /**
     * Chart 6.2 of the same edition (p. 228; Moonbeams f. 28a/b): a subject born
     * in a fire dragon year, read in an earth tiger year. Each column sets one
     * element per aspect against the subject's; 0 is a white pebble, X a black one.
     */
    @Test
    fun `pebbles match the obstacle-year chart`() {
        val subject = Forces.of(Element.FIRE, Animal.DRAGON)
        assertEquals(YearForces(Element.EARTH, Element.EARTH, Element.FIRE, Element.WOOD), subject)
        val present = Forces.of(Element.EARTH, Animal.TIGER)
        assertEquals(YearForces(Element.WOOD, Element.EARTH, Element.EARTH, Element.IRON), present)
        val logMen = Forces.of(Element.IRON, Animal.MOUSE)
        assertEquals(YearForces(Element.WATER, Element.EARTH, Element.IRON, Element.WOOD), logMen)

        fun marks(p: Pebbles) = "0".repeat(p.white) + "X".repeat(p.black)
        assertEquals(listOf("XX", "0", "0X", "XX"), Forces.contrast(subject, present).map { marks(it.pebbles) })
        assertEquals(listOf("00", "0", "00", "X"), Forces.contrast(subject, logMen).map { marks(it.pebbles) })

        // Trigram zin (wood), numeric square red 7 (fire), hour of a water snake: vitality fire, the rest water.
        val columns = listOf(
            listOf("wood", "wood", "wood", "wood") to listOf("XX", "XX", "000", "X"),
            listOf("fire", "fire", "fire", "fire") to listOf("000", "000", "X", "0X"),
            listOf("fire", "water", "water", "water") to listOf("000", "00", "XX", "000"),
        )
        for ((elements, expected) in columns) {
            val got = Force.entries.mapIndexed { i, force ->
                marks(Forces.pebbles(Forces.kinship(subject[force], element(elements[i])), subject[force]))
            }
            assertEquals(expected, got, "column $elements")
        }
    }

    /** Table 2.5 of the same edition (p. 91): the destiny element of each month by the year's. */
    @Test
    fun `month elements match the table`() {
        val animals = listOf(Animal.TIGER, Animal.RABBIT, Animal.DRAGON, Animal.SNAKE, Animal.HORSE, Animal.SHEEP, Animal.MONKEY, Animal.BIRD, Animal.DOG, Animal.PIG, Animal.MOUSE, Animal.OX)
        val table = mapOf(
            Element.WOOD to "fire fire earth earth iron iron water water wood wood fire fire",
            Element.FIRE to "earth earth iron iron water water wood wood fire fire earth earth",
            Element.EARTH to "iron iron water water wood wood fire fire earth earth iron iron",
            Element.IRON to "water water wood wood fire fire earth earth iron iron water water",
            Element.WATER to "wood wood fire fire earth earth iron iron water water wood wood",
        )
        for ((year, row) in table) {
            row.split(' ').forEachIndexed { i, e -> assertEquals(element(e), Forces.monthElement(year, animals[i]), "$year ${animals[i]}") }
        }
    }

    /**
     * Chart 8.1 (p. 296; White Beryl ff. 295b–299a): a subject born in a fire
     * dragon year, on the 15th of the 3rd month of an earth tiger year. The 3rd
     * month is a wood horse month, the 15th a wood dragon day; the chart sets
     * the subject's vitality and body against theirs.
     */
    @Test
    fun `month and day signs and pebbles match the health chart`() {
        val month = Sign(Forces.monthElement(Element.EARTH, Animal.HORSE), Animal.HORSE)
        assertEquals(Sign(Element.WOOD, Animal.HORSE), month)
        val day = Sign(Forces.dateElement(month.element, 15), Animal.DRAGON)
        assertEquals(Sign(Element.WOOD, Animal.DRAGON), day)
        val subject = Forces.of(Element.FIRE, Animal.DRAGON)
        fun marks(own: Element, other: Element) = Forces.pebbles(Forces.kinship(own, other), own).let { "0".repeat(it.white) + "X".repeat(it.black) }
        assertEquals("000", marks(subject.vitality, month.forces.vitality))
        assertEquals("0X", marks(subject.body, month.forces.body))
        assertEquals("0", marks(subject.vitality, day.forces.vitality))
        assertEquals("000", marks(subject.body, day.forces.body))
        // The hour, earth bird: vitality iron (0X), body earth (0). The bird hour of a wood day is its friend, earth.
        val hour = Forces.hours(day).single { it.sign.animal == Animal.BIRD }
        assertEquals(Sign(Element.EARTH, Animal.BIRD), hour.sign)
        assertEquals(17 * 60, hour.startMinute)
        assertEquals("0X", marks(subject.vitality, hour.sign.forces.vitality))
        assertEquals("0", marks(subject.body, hour.sign.forces.body))
    }

    /** Table 2.7 (p. 91): the hours' destiny elements for each daily one, and the hours' order and clock times. */
    @Test
    fun `hour elements follow the day's by the hour's animal`() {
        // "The first two-hour period, known as daybreak or the hour of the hare, will have the destiny element
        // wood if the destiny element of the day is water."
        assertEquals(Element.WOOD, Forces.hourElement(Element.WATER, Animal.RABBIT))
        // The table's first rows, for a wood day: hare fire, dragon earth, snake iron.
        val wood = Forces.hours(Sign(Element.WOOD, Animal.DRAGON))
        assertEquals(listOf(Element.FIRE, Element.EARTH, Element.IRON), wood.take(3).map { it.sign.element })
        assertEquals(Animal.RABBIT, wood.first().sign.animal)
        assertEquals(5 * 60, wood.first().startMinute)
        assertEquals(Animal.MOUSE, wood[9].sign.animal)
        assertEquals(23 * 60, wood[9].startMinute)
        assertEquals(Animal.TIGER, wood.last().sign.animal)
        assertEquals(3 * 60, wood.last().startMinute)
        // Every element's hours: the rule of p. 90, cell by cell of Table 2.7.
        val table = mapOf(
            Element.WOOD to "fire earth iron water wood fire earth iron water wood fire earth",
            Element.FIRE to "earth iron water wood fire earth iron water wood fire earth iron",
            Element.EARTH to "iron water wood fire earth iron water wood fire earth iron water",
            Element.IRON to "water wood fire earth iron water wood fire earth iron water wood",
            Element.WATER to "wood fire earth iron water wood fire earth iron water wood fire",
        )
        for ((day, row) in table) {
            assertEquals(row, Forces.hours(Sign(day, Animal.DRAGON)).joinToString(" ") { it.sign.element.name.lowercase() }, "$day")
        }
    }

    @Test
    fun `lunar dates run through the elements from the son of the month's`() {
        // In a fire month the 1st is earth, the 2nd iron (p. 90).
        assertEquals(Element.EARTH, Forces.dateElement(Element.FIRE, 1))
        assertEquals(Element.IRON, Forces.dateElement(Element.FIRE, 2))
        assertEquals(Element.FIRE, Forces.dateElement(Element.FIRE, 5))
        assertEquals(Element.EARTH, Forces.dateElement(Element.FIRE, 6))
    }

    /** The app's month animal and lunar-date animal give chart 8.1's signs for the day it describes. */
    @Test
    fun `signs of a calendar day`() {
        // 1998 was an earth tiger year; its 3rd month's 15th fell in spring 1998.
        val day = (0L..200L).map { TibetanCalendar.of(java.time.LocalDate.of(1998, 3, 1).plusDays(it)) }
            .first { it.month == 3 && !it.leapMonth && it.day == 15 }
        assertEquals(DaySigns(Sign(Element.EARTH, Animal.TIGER), Sign(Element.WOOD, Animal.HORSE), Sign(Element.WOOD, Animal.DRAGON)), Forces.signs(day))
    }

    @Test
    fun `kinship is what the other element is to one's own`() {
        // For a wood person: water years are mother years, fire son, earth friend, iron enemy (p. 64).
        assertEquals(Kinship.MOTHER, Forces.kinship(Element.WOOD, Element.WATER))
        assertEquals(Kinship.SON, Forces.kinship(Element.WOOD, Element.FIRE))
        assertEquals(Kinship.FRIEND, Forces.kinship(Element.WOOD, Element.EARTH))
        assertEquals(Kinship.ENEMY, Forces.kinship(Element.WOOD, Element.IRON))
        assertEquals(Kinship.IDENTITY, Forces.kinship(Element.WOOD, Element.WOOD))
    }
}
