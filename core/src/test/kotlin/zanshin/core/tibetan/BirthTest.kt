/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

/** The birth reckoning's parts built (ROADMAP R2; docs/sources/year-of-life.md, RD13). */
class BirthTest {
    private fun vitality(a: Animal) = Forces.of(Element.WOOD, a).vitality

    /** P. 294's own count, read on the scan: the horse and snake years on each trigram, and the five demons. */
    @Test
    fun `the five children follow WB's worked count`() {
        for (a in listOf(Animal.HORSE, Animal.SNAKE)) {
            val fire = vitality(a)
            assertEquals(Element.FIRE, fire)
            assertEquals(BirthChild.PARENTS_GOD, BirthReckoning.child(fire, Trigram.ZIN))
            for (corner in listOf(Trigram.KHON, Trigram.KHEN, Trigram.GIN, Trigram.ZON)) assertEquals(BirthChild.CHILDRENS_GLORY, BirthReckoning.child(fire, corner))
            assertEquals(BirthChild.LIFE, BirthReckoning.child(fire, Trigram.LI))
            assertEquals(BirthChild.SPIRIT, BirthReckoning.child(fire, Trigram.DWA))
            assertEquals(BirthChild.DEMON, BirthReckoning.child(fire, Trigram.KHAM))
        }
        // The wood tiger and hare on dwa die at fifteen, iron bird and monkey on li at twelve, fire horse and snake on
        // kham at nine, water mouse and pig on the four corners at six, the earth animals on zin at three.
        val demons = listOf(
            listOf(Animal.TIGER, Animal.RABBIT) to (Trigram.DWA to 15),
            listOf(Animal.BIRD, Animal.MONKEY) to (Trigram.LI to 12),
            listOf(Animal.HORSE, Animal.SNAKE) to (Trigram.KHAM to 9),
            listOf(Animal.MOUSE, Animal.PIG) to (Trigram.GIN to 6),
            listOf(Animal.OX, Animal.DRAGON, Animal.SHEEP, Animal.DOG) to (Trigram.ZIN to 3),
        )
        for ((animals, fall) in demons) for (a in animals) {
            assertEquals(BirthChild.DEMON, BirthReckoning.child(vitality(a), fall.first), "$a")
            assertEquals(fall.second, BirthReckoning.demonAge(vitality(a)), "$a")
        }
    }

    /** Each trigram's element against each life force gives each of the five once per element. */
    @Test
    fun `every life force has one trigram element of each kind`() {
        for (v in Element.entries) {
            val kinds = Trigram.entries.map { YearOfLife.trigramElement(it) }.distinct().map { BirthReckoning.child(v, Trigram.entries.first { t -> YearOfLife.trigramElement(t) == it }) }
            assertEquals(BirthChild.entries.toSet(), kinds.toSet(), "$v")
        }
    }

    /** A man's trigram runs li, khon, dwa, khen, kham, gin, zin, zon from his first year (WB vol. 1, p. 257). */
    @Test
    fun `the father's trigram is his of the year of the birth`() {
        assertEquals(Trigram.LI, BirthReckoning.fatherTrigram(1950, 1950))
        assertEquals(Trigram.ZIN, BirthReckoning.fatherTrigram(1950, 1956))
        assertEquals(Trigram.GIN, BirthReckoning.fatherTrigram(1950, 1955))
        assertEquals(Trigram.KHEN, BirthReckoning.fatherTrigram(1950, 1953))
        assertNull(BirthReckoning.fatherTrigram(1980, 1976))
    }

    /** P. 326's readings, read on the scan, each where it holds and where it does not. */
    @Test
    fun `the parents' trigrams at the birth`() {
        fun omens(natal: Trigram, animal: Animal, gender: Gender?, mother: Animal = Animal.TIGER, father: Trigram? = null) =
            BirthReckoning.omens(natal, animal, gender, mother, father)
        assertEquals(listOf(BirthOmen.FATHER_DIES), omens(Trigram.DWA, Animal.HORSE, Gender.MALE, father = Trigram.ZIN))
        assertEquals(emptyList<BirthOmen>(), omens(Trigram.DWA, Animal.HORSE, Gender.FEMALE, father = Trigram.ZIN))
        assertEquals(listOf(BirthOmen.MOTHER_DIES), omens(Trigram.ZIN, Animal.HORSE, Gender.FEMALE, father = Trigram.GIN))
        // On khen: a son of a dog or dragon mother; a child of a dog or dragon year, either sex; a daughter of a dog
        // mother is not, and on khen every daughter counts hearthstones.
        assertEquals(listOf(BirthOmen.SKY_BAD_FOR_FATHER), omens(Trigram.KHEN, Animal.HORSE, Gender.MALE, mother = Animal.DOG))
        assertEquals(listOf(BirthOmen.SKY_BAD_FOR_FATHER), omens(Trigram.KHEN, Animal.DRAGON, null))
        assertEquals(listOf(BirthOmen.HEARTHS), omens(Trigram.KHEN, Animal.HORSE, Gender.FEMALE, mother = Animal.DOG))
        assertEquals(listOf(BirthOmen.FATHER_SKY_SON_FIRE), omens(Trigram.LI, Animal.HORSE, Gender.MALE, father = Trigram.KHEN))
        // On khon: a daughter of an ox or sheep mother, or an ox or sheep daughter, who also counts hearthstones there.
        assertEquals(listOf(BirthOmen.EARTH_BAD_FOR_MOTHER), omens(Trigram.KHON, Animal.HORSE, Gender.FEMALE, mother = Animal.SHEEP))
        assertEquals(listOf(BirthOmen.EARTH_BAD_FOR_MOTHER, BirthOmen.HEARTHS), omens(Trigram.KHON, Animal.OX, Gender.FEMALE))
        assertEquals(emptyList<BirthOmen>(), omens(Trigram.KHON, Animal.OX, Gender.MALE, mother = Animal.SHEEP))
        // On zon: any child of a dog or dragon mother; a son of a dog or dragon year; a dog daughter counts hearthstones.
        assertEquals(listOf(BirthOmen.WIND_NO_SISTER), omens(Trigram.ZON, Animal.HORSE, null, mother = Animal.DRAGON))
        assertEquals(listOf(BirthOmen.WIND_NO_SISTER), omens(Trigram.ZON, Animal.DOG, Gender.MALE))
        assertEquals(listOf(BirthOmen.HEARTHS), omens(Trigram.ZON, Animal.DOG, Gender.FEMALE))
        // On gin: any child of an ox or sheep mother; an ox or sheep daughter, bad for her brothers.
        assertEquals(listOf(BirthOmen.MOUNTAIN_NO_SISTER), omens(Trigram.GIN, Animal.HORSE, Gender.MALE, mother = Animal.OX))
        assertEquals(listOf(BirthOmen.MOUNTAIN_BAD_FOR_BROTHERS, BirthOmen.HEARTHS), omens(Trigram.GIN, Animal.SHEEP, Gender.FEMALE))
        // The hearthstones by the daughter's year.
        assertEquals(listOf(BirthOmen.HEARTHS), omens(Trigram.KHAM, Animal.PIG, Gender.FEMALE))
        assertEquals(listOf(BirthOmen.HEARTHS), omens(Trigram.ZIN, Animal.BIRD, Gender.FEMALE))
        assertEquals(emptyList<BirthOmen>(), omens(Trigram.KHAM, Animal.BIRD, Gender.FEMALE))
        assertEquals(emptyList<BirthOmen>(), omens(Trigram.LI, Animal.HORSE, Gender.FEMALE))
    }

    /** P. 261's table, read on the scan: every clan's seven kinds of year cover the twelve animals once. */
    @Test
    fun `the special pebble follows the clan's table`() {
        for (c in Clan.entries) {
            val kinds = Animal.entries.groupBy { BirthReckoning.clanYear(c, it) }
            assertEquals(ClanYear.entries.toSet(), kinds.keys, "$c")
            assertEquals(1, kinds.getValue(ClanYear.GREAT_PENALTY).size)
            assertEquals(1, kinds.getValue(ClanYear.SMALL_PENALTY).size)
            // The great penalty is the element's tomb animal, the small its seventh; water's are earth's.
            val tomb = YearOfLife.tomb(YearOfLife.tombKey(c.element))
            assertEquals(listOf(tomb), kinds.getValue(ClanYear.GREAT_PENALTY), "$c")
            assertEquals(listOf(Animal.entries[(tomb.ordinal + 6) % 12]), kinds.getValue(ClanYear.SMALL_PENALTY), "$c")
            // The rest by the life force of the year against the clan's element.
            for (y in listOf(ClanYear.POWER, ClanYear.MOTHER, ClanYear.SON, ClanYear.FRIEND, ClanYear.ENEMY)) for (a in kinds.getValue(y)) {
                val kin = Forces.kinship(c.element, vitality(a))
                val expected = mapOf(Kinship.IDENTITY to ClanYear.POWER, Kinship.MOTHER to ClanYear.MOTHER, Kinship.SON to ClanYear.SON, Kinship.FRIEND to ClanYear.FRIEND, Kinship.ENEMY to ClanYear.ENEMY)
                assertEquals(expected.getValue(kin), y, "$c $a")
            }
        }
        // Rows of the table as printed.
        assertEquals(ClanYear.GREAT_PENALTY, BirthReckoning.clanYear(Clan.KEG, Animal.SHEEP))
        assertEquals(ClanYear.FRIEND, BirthReckoning.clanYear(Clan.KEG, Animal.DRAGON))
        assertEquals(ClanYear.MOTHER, BirthReckoning.clanYear(Clan.SHANG, Animal.DOG))
        assertEquals(ClanYear.ENEMY, BirthReckoning.clanYear(Clan.U, Animal.SHEEP))
        assertEquals(ClanYear.SMALL_PENALTY, BirthReckoning.clanYear(Clan.JI, Animal.DRAGON))
    }

    /** Pp. 275–280's particular readings name the years of each kind; the tiger and hare years run through all five. */
    @Test
    fun `the year's destiny against its life force`() {
        fun kind(e: Element, a: Animal) = BirthReckoning.powerYear(Sign(e, a))
        assertEquals(PowerYear.KHA_YAN, kind(Element.WOOD, Animal.TIGER))
        assertEquals(PowerYear.KHA_YAN, kind(Element.FIRE, Animal.HORSE))
        assertEquals(PowerYear.KHA_YAN, kind(Element.EARTH, Animal.DOG))
        assertEquals(PowerYear.KHONG_NONG, kind(Element.WATER, Animal.RABBIT))
        assertEquals(PowerYear.KHONG_NONG, kind(Element.IRON, Animal.PIG))
        assertEquals(PowerYear.SE_ZHIG, kind(Element.FIRE, Animal.TIGER))
        assertEquals(PowerYear.SE_ZHIG, kind(Element.WATER, Animal.MONKEY))
        assertEquals(PowerYear.DUN_KHUR, kind(Element.IRON, Animal.RABBIT))
        assertEquals(PowerYear.DUN_KHUR, kind(Element.WATER, Animal.SNAKE))
        assertEquals(PowerYear.KHA_RAL, kind(Element.EARTH, Animal.TIGER))
        assertEquals(PowerYear.KHA_RAL, kind(Element.WATER, Animal.DRAGON))
        // Each of the five falls on twelve of the sixty years.
        val counts = (0 until 60).groupingBy { BirthReckoning.powerYear(YearOfLife.signOf(it)) }.eachCount()
        assertEquals(PowerYear.entries.associateWith { 12 }, counts)
    }

    /** P. 283: four first, four middle and four last years. */
    @Test
    fun `the first, middle and last years`() {
        val places = Animal.entries.groupBy { BirthReckoning.yearPlace(it) }
        assertEquals(listOf(Animal.TIGER, Animal.SNAKE, Animal.MONKEY, Animal.PIG), places.getValue(YearPlace.FIRST))
        assertEquals(listOf(Animal.MOUSE, Animal.RABBIT, Animal.HORSE, Animal.BIRD), places.getValue(YearPlace.MIDDLE))
        assertEquals(listOf(Animal.OX, Animal.DRAGON, Animal.SHEEP, Animal.DOG), places.getValue(YearPlace.LAST))
    }

    /** Pp. 281–282: a pair is read where the cutter's element overcomes the other's; the fire dragon's destiny cuts its luck. */
    @Test
    fun `the cutting and tearing among one's own aspects`() {
        // Fire dragon: life force earth, body iron? destiny fire, luck wood.
        val f = Forces.of(Element.FIRE, Animal.DRAGON)
        val cuts = BirthReckoning.cuts(Sign(Element.FIRE, Animal.DRAGON))
        for ((a, b) in cuts) assertEquals(f[b], f[a].overcomes, "$a $b")
        for ((a, b) in BirthReckoning.CUTS - cuts.toSet()) assert(f[a].overcomes != f[b]) { "$a $b" }
        // Over the sixty years every pair WB reads falls somewhere.
        val seen = (0 until 60).flatMap { BirthReckoning.cuts(YearOfLife.signOf(it)) }.toSet()
        assertEquals(BirthReckoning.CUTS.toSet(), seen)
    }
}
