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
