/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** The signs of the years of life (WB vol. 1, pp. 255–258; docs/sources/year-of-life.md). */
class YearOfLifeTest {
    @Test
    fun `the year's sme ba, from WB's fire hare and Gyurme Dorje's sixty-year charts`() {
        // WB vol. 1, p. 255: "the present rabjung's fire hare" (1687) has the seven-red.
        assertEquals(7, YearOfLife.yearSmeBa(1687))
        // Gyurme Dorje (2001), the captions of the 180-year charts, pp. 70–85 and 101: year n of the
        // three cycles from the wood mouse of 1864 and its natal sme ba.
        val printed = mapOf(1 to 1, 4 to 7, 12 to 8, 61 to 4, 72 to 2, 113 to 6, 121 to 7, 173 to 9)
        for ((n, sme) in printed) assertEquals(sme, YearOfLife.yearSmeBa(1864 + n - 1), "year $n")
        // Every year one less, round the nine.
        for (y in 1900..2100) assertEquals(Math.floorMod(YearOfLife.yearSmeBa(y) - 2, 9) + 1, YearOfLife.yearSmeBa(y + 1))
    }

    /**
     * Gyurme Dorje, Table 2.11 (p. 103): the sme ba of the ages 1 to 9 (and
     * every ninth year on) for each natal sme ba, in male and female years.
     */
    @Test
    fun `the sme ba of each year of age matches Table 2_11`() {
        val table = mapOf(
            9 to ("973512648" to "978462153"), 8 to ("862491537" to "867351942"),
            7 to ("751389426" to "756249831"), 6 to ("649278315" to "645138729"),
            5 to ("538167294" to "534927618"), 4 to ("427956183" to "423816597"),
            3 to ("316845972" to "312795486"), 2 to ("295734861" to "291684375"),
            1 to ("184623759" to "189573264"),
        )
        for ((natal, rows) in table) for ((gender, row) in listOf(Gender.MALE to rows.first, Gender.FEMALE to rows.second)) {
            for (age in 1..90) {
                val expected = row[(age - 1) % 9].digitToInt()
                assertEquals(expected, YearOfLife.currentSmeBa(natal, gender, age), "natal $natal $gender age $age")
            }
        }
    }

    @Test
    fun `the trigram of each year of age`() {
        assertEquals(Trigram.LI, YearOfLife.currentTrigram(Gender.MALE, 1))
        assertEquals(Trigram.KHON, YearOfLife.currentTrigram(Gender.MALE, 2))
        assertEquals(Trigram.LI, YearOfLife.currentTrigram(Gender.MALE, 9))
        assertEquals(Trigram.KHAM, YearOfLife.currentTrigram(Gender.FEMALE, 1))
        assertEquals(Trigram.KHEN, YearOfLife.currentTrigram(Gender.FEMALE, 2))
        assertEquals(Trigram.GIN, YearOfLife.currentTrigram(Gender.FEMALE, 8))
        assertEquals(Trigram.KHAM, YearOfLife.currentTrigram(Gender.FEMALE, 9))
    }

    /**
     * Gyurme Dorje, chart 6.2 (p. 228): a man born in a fire dragon year
     * (1976), read in the earth tiger year 1998, his 23rd: current trigram
     * Zin, current sme ba red seven.
     */
    @Test
    fun `chart 6_2's current trigram and sme ba`() {
        val age = YearOfLife.age(1976, 1998)
        assertEquals(23, age)
        assertEquals(Trigram.ZIN, YearOfLife.currentTrigram(Gender.MALE, age))
        assertEquals(7, YearOfLife.currentSmeBa(YearOfLife.yearSmeBa(1976), Animal.DRAGON.gender, age))
    }

    @Test
    fun `the sectors run from each element's breath-taking and end in its tomb`() {
        // WB vol. 1, p. 258: the tombs of wood the sheep, of fire the dog, of iron the ox, of earth and water the dragon.
        val tombs = mapOf(Element.WOOD to Animal.SHEEP, Element.FIRE to Animal.DOG, Element.IRON to Animal.OX, Element.EARTH to Animal.DRAGON, Element.WATER to Animal.DRAGON)
        for ((element, tomb) in tombs) assertEquals(Sector.ENTERING_THE_TOMB, YearOfLife.sector(element, tomb), "$element")
        assertEquals(Sector.TAKING_BREATH, YearOfLife.sector(Element.FIRE, Animal.PIG))
        assertEquals(Sector.BIRTH, YearOfLife.sector(Element.FIRE, Animal.TIGER))
        assertEquals(6, Sector.entries.count { it.good })
    }

    /**
     * Gyurme Dorje, chart 6.2 (p. 229): the man born in the fire dragon year,
     * in the earth tiger year, at a water snake hour. Every cell of the six
     * basic columns, 0 a white pebble and X a black one.
     */
    @Test
    fun `chart 6_2's twenty-four decisive pebbles`() {
        val r = YearReckoning(Sign(Element.FIRE, Animal.DRAGON), 1976, Sign(Element.EARTH, Animal.TIGER), 1998, Gender.MALE, HourSign(Sign(Element.WATER, Animal.SNAKE), 9 * 60))
        assertEquals(Sign(Element.IRON, Animal.MOUSE), r.logMen)
        assertEquals(Trigram.ZIN, r.trigram)
        assertEquals(7, r.currentSmeBa)
        val expected = mapOf(
            BasicSign.PRESENT_YEAR to "XX 0 0X XX",
            BasicSign.LOG_MEN to "00 0 00 X",
            BasicSign.TRIGRAM to "XX XX 000 X",
            BasicSign.SME_BA to "000 000 X 0X",
            BasicSign.SECTOR to "X X 0 000",
            BasicSign.HOUR to "000 00 XX 000",
        )
        fun marks(p: Pebbles) = "0".repeat(p.white) + "X".repeat(p.black)
        for ((basic, row) in expected) {
            assertEquals(row, r.pebbles.filter { it.basic == basic }.joinToString(" ") { marks(it.pebbles) }, "$basic")
        }
        assertEquals(listOf(Sector.ILLNESS, Sector.ILLNESS, Sector.BIRTH, Sector.WORKING), r.sectors.map { it.sector })
        assertEquals(Pebbles(8, 5), r.tally(Force.VITALITY))
        assertEquals(true, r.predictive(Force.VITALITY))
    }

    @Test
    fun `the progressed sign of a woman counts back from the monkey of the mother`() {
        // A woman of a fire year: her first year the wood monkey, her second the water sheep.
        assertEquals(Sign(Element.WOOD, Animal.MONKEY), YearOfLife.logMen(Element.FIRE, Gender.FEMALE, 1))
        assertEquals(Sign(Element.WATER, Animal.SHEEP), YearOfLife.logMen(Element.FIRE, Gender.FEMALE, 2))
        // A man of a fire year: from the earth tiger, then the earth hare.
        assertEquals(Sign(Element.EARTH, Animal.TIGER), YearOfLife.logMen(Element.FIRE, Gender.MALE, 1))
        assertEquals(Sign(Element.EARTH, Animal.RABBIT), YearOfLife.logMen(Element.FIRE, Gender.MALE, 2))
        assertEquals(LogMenPlace.SKY_DOOR, YearOfLife.logMenPlace(Animal.DOG, Gender.MALE))
        assertEquals(null, YearOfLife.logMenPlace(Animal.DOG, Gender.FEMALE))
    }

    @Test
    fun `harsh years by age`() {
        val birth = Sign(Element.FIRE, Animal.DRAGON)
        fun at(year: Int) = YearReckoning(birth, 1976, YearOfLife.yearSign(year), year, null, null).harsh.map { it.year to it.kinship }
        // The 13th year, 1988, an earth dragon: one's own animal, its element the son of fire (WB vol. 1, p. 388).
        assertEquals(listOf(HarshYear.OWN_YEAR to Kinship.SON), at(1988))
        // The 7th, 1982, a water dog: the seventh, water the enemy of fire.
        assertEquals(listOf(HarshYear.SEVENTH to Kinship.ENEMY), at(1982))
        assertEquals(listOf(HarshYear.TRIAD to null), at(1980))
        assertEquals(listOf(HarshYear.FOURTH_UP to null), at(1985))
        assertEquals(listOf(HarshYear.FOURTH_DOWN to null), at(1979))
        // The year of birth is one's own animal, but the harsh own year starts at the 13th.
        assertEquals(emptyList<Pair<HarshYear, Kinship?>>(), at(1976))
    }

    @Test
    fun `the small obstacles of the sme ba`() {
        // Ages 1, 10, 19 … stand in the middle, on the natal sme ba; in 1985, the 10th year of a man
        // born in 1976, the year's own sme ba is the six-white too.
        val r = YearReckoning(Sign(Element.FIRE, Animal.DRAGON), 1976, YearOfLife.yearSign(1985), 1985, null, null)
        assertEquals(10, r.age)
        assertEquals(listOf(6, 6, 6), listOf(r.natalSmeBa, r.currentSmeBa, r.yearSmeBa))
        assertEquals(listOf(SmeBaObstacle.HOUSE, SmeBaObstacle.BED), r.smeBaObstacles)
        // The 11th year, 1986: east of the middle, the four-green, wood: no obstacle (6 is iron, which overcomes wood).
        val next = YearReckoning(Sign(Element.FIRE, Animal.DRAGON), 1976, YearOfLife.yearSign(1986), 1986, null, null)
        assertEquals(4, next.currentSmeBa)
        assertEquals(emptyList<SmeBaObstacle>(), next.smeBaObstacles)
    }
}
