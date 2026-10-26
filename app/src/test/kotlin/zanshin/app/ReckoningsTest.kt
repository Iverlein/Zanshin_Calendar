/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import zanshin.core.tibetan.BirthChild
import zanshin.core.tibetan.BirthOmen
import zanshin.core.tibetan.Element
import zanshin.core.tibetan.Gender
import zanshin.core.tibetan.Kin
import zanshin.core.tibetan.KinObstacle
import zanshin.core.tibetan.KinReckoning
import zanshin.core.tibetan.KinRole
import zanshin.core.tibetan.Sign
import zanshin.core.tibetan.TibetanCalendar
import zanshin.core.tibetan.Trigram
import zanshin.core.tibetan.YearReckoning
import java.time.LocalDate

/** SPEC §10.11: the Reckonings section's list, and the year of age it steps by Tibetan year. */
class ReckoningsTest {
    private val person = Person("", LocalDate.of(1976, 5, 20), Gender.FEMALE)

    @Test
    fun `the list needs a person`() {
        assertEquals(emptyList<Reckoning>(), Reckonings.built(null))
        assertEquals(listOf(Reckoning.BIRTH, Reckoning.MATCH, Reckoning.YEAR_OF_AGE, Reckoning.DEAD, Reckoning.THIRTY, Reckoning.CIRCLES, Reckoning.MOMENT, Reckoning.ILLNESS), Reckonings.built(person))
    }

    /**
     * The reckoning for the dead (ROADMAP R2n) from a relative entered as dead: Kun dga' rig 'dzin's dead man of the
     * fire horse year, died on 27 October 1995 (the 4th of the 9th month) at 14:30, the sheep hour; the same at 04:30
     * on the 28th falls on the 27th's Tibetan day; one without a date of death is not reckoned.
     */
    @Test
    fun `the dead are reckoned from their relatives' dates`() {
        val father = Relative(1, null, Relation.FATHER, birth = Moment(1906), died = true, death = Moment.of(LocalDate.of(1995, 10, 27), java.time.LocalTime.of(14, 30)))
        val sister = Relative(2, null, Relation.SIBLING, gender = Gender.FEMALE, birth = Moment(1950), died = true, death = Moment(1990))
        val dead = Reckonings.dead(person.copy(relatives = Relatives(listOf(father, sister))))
        val r = dead[0].second!!
        assertEquals(zanshin.core.tibetan.Animal.SHEEP, r.year.hour!!.sign.animal)
        assertEquals(listOf("BLACK", "WHITE", "SPECKLED", "BLACK"), zanshin.core.tibetan.Force.entries.map { r.deciding(it).name })
        assertNull(dead[1].second)
        val early = father.copy(death = Moment.of(LocalDate.of(1995, 10, 28), java.time.LocalTime.of(4, 30)))
        val e = Reckonings.dead(person.copy(relatives = Relatives(listOf(early))))[0].second!!
        assertEquals(r.date, e.date)
    }

    /** The moment's day turns at dawn: 04:30 belongs to the day before, 05:00 starts the new one at minute 0. */
    @Test
    fun `the moment turns at dawn`() {
        val zone = java.time.ZoneId.of("Asia/Tokyo")
        val before = Reckonings.moment(java.time.ZonedDateTime.of(2026, 10, 22, 4, 30, 0, 0, zone))
        val after = Reckonings.moment(java.time.ZonedDateTime.of(2026, 10, 22, 5, 0, 0, 0, zone))
        assertEquals(TibetanCalendar.of(LocalDate.of(2026, 10, 21)), before.first)
        assertEquals(1410, before.second)
        assertEquals(TibetanCalendar.of(LocalDate.of(2026, 10, 22)), after.first)
        assertEquals(0, after.second)
    }

    @Test
    fun `the year of age starts in the birth year`() {
        val born = Reckonings.birthYear(person)
        assertEquals(1976, born)
        assertNull(Reckonings.yearOfAge(person, born - 1, null))
        assertEquals(1, Reckonings.yearOfAge(person, born, null)!!.age)
        assertEquals(51, Reckonings.yearOfAge(person, 2026, null)!!.age)
    }

    @Test
    fun `a year stepped to reckons as the year's balloon does from a day in it`() {
        // Every tenth day over four years, which takes in each new year's eve and day within ten days.
        val born = TibetanCalendar.of(person.birth)
        var date = LocalDate.of(2025, 1, 1)
        while (date < LocalDate.of(2029, 1, 1)) {
            val day = TibetanCalendar.of(date)
            val fromDay = YearReckoning(Sign(born.yearElement, born.yearAnimal), born.year, Sign(day.yearElement, day.yearAnimal), day.year, person.gender, null)
            assertEquals(fromDay, Reckonings.yearOfAge(person, day.year, null), "$date")
            date = date.plusDays(10)
        }
    }

    /**
     * ROADMAP P2: the obstacles read the mother, father, spouses and children entered under the person, and anyone
     * of the household further out; a year alone counts as the Tibetan year that begins in it, a day as the
     * Tibetan year it falls in.
     */
    @Test
    fun `relatives as the obstacles read them`() {
        val tree = Relatives()
            .put(Relative(0, null, Relation.MOTHER, birth = Moment(1950)))
            .put(Relative(0, null, Relation.SPOUSE, gender = Gender.MALE, birth = Moment.of(LocalDate.of(1976, 2, 1))))
            .put(Relative(0, null, Relation.FATHER, died = true, death = Moment(1988)))
            .put(Relative(0, 1, Relation.MOTHER, birth = Moment(1925)))
            .put(Relative(0, null, Relation.SIBLING, gender = Gender.FEMALE, birth = Moment(1980), household = true))
        val kin = Reckonings.kin(person.copy(relatives = tree))
        assertEquals(
            listOf(
                Kin(KinRole.MOTHER, 1950, Gender.FEMALE, true, null, false, 1),
                // 1 February 1976 falls before Losar, in the Tibetan year that began in 1975.
                Kin(KinRole.SPOUSE, 1975, Gender.MALE, true, null, false, 2),
                Kin(KinRole.FATHER, null, Gender.MALE, false, 1988, false, 3),
                Kin(KinRole.OTHER, 1980, Gender.FEMALE, true, null, true, 5),
            ),
            kin,
        )
        // The mother's 27th year at the birth: the lake trigram; the woman's own trigram reaches it in her 3rd, 11th … year.
        val r = Reckonings.yearOfAge(person.copy(relatives = tree), 2026, null)!!
        assertEquals(KinReckoning.natalTrigram(1950, 1976), KinReckoning.natalTrigram(r, kin))
        assertEquals(listOf(KinObstacle.SECRET to 1), KinReckoning.obstacles(r, kin).map { it.obstacle to it.kin.tag })
    }

    /**
     * ROADMAP R2: the birth is reckoned from the mother's year, the father's where known. A fire dragon of 1976 born
     * in the mother's 27th year stands on dwa, iron, the son of the dragon's earth life force: the children's glory.
     */
    @Test
    fun `the birth is reckoned from the parents' years`() {
        assertNull(Reckonings.birth(person))
        val mother = Relatives().put(Relative(0, null, Relation.MOTHER, birth = Moment(1950)))
        val b = Reckonings.birth(person.copy(relatives = mother))!!
        assertEquals(Trigram.DWA, b.natal)
        assertEquals(Element.EARTH, b.vitality)
        assertEquals(BirthChild.CHILDRENS_GLORY, b.child)
        assertNull(b.fatherTrigram)
        assertEquals(emptyList<BirthOmen>(), b.omens)
        // A son of the same birth whose father, born in 1954, stands in his 23rd year on zin: the father soon dies.
        val parents = mother.put(Relative(0, null, Relation.FATHER, birth = Moment(1954)))
        val son = Reckonings.birth(person.copy(gender = Gender.MALE, relatives = parents))!!
        assertEquals(Trigram.ZIN, son.fatherTrigram)
        assertEquals(listOf(BirthOmen.FATHER_DIES), son.omens)
        assertEquals(emptyList<BirthOmen>(), Reckonings.birth(person.copy(relatives = parents))!!.omens)
    }

    /** The special pebble needs only the clan: a fire dragon of a keg (wood) clan is born in its friend year. */
    @Test
    fun `the special pebble from the clan`() {
        assertNull(Reckonings.clanYear(person))
        assertEquals(zanshin.core.tibetan.ClanYear.FRIEND, Reckonings.clanYear(person.copy(clan = zanshin.core.tibetan.Clan.KEG)))
        assertEquals(zanshin.core.tibetan.ClanYear.GREAT_PENALTY, Reckonings.clanYear(person.copy(clan = zanshin.core.tibetan.Clan.KUNGS)))
    }

    /** The match needs the person's gender and a spouse's year; the man and the woman are told apart whichever the person is. */
    @Test
    fun `the marriage match from the spouse's year`() {
        assertNull(Reckonings.match(person))
        val wife = Relatives().put(Relative(0, null, Relation.SPOUSE, birth = Moment(1979)))
        assertNull(Reckonings.match(person.copy(gender = null, relatives = wife)))
        val m = Reckonings.match(person.copy(gender = Gender.MALE, relatives = wife))!!
        assertEquals(zanshin.core.tibetan.Animal.DRAGON, m.man.animal)
        assertEquals(zanshin.core.tibetan.Animal.SHEEP, m.woman.animal)
        val husband = Relatives().put(Relative(0, null, Relation.SPOUSE, birth = Moment(1979)))
        val w = Reckonings.match(person.copy(gender = Gender.FEMALE, relatives = husband))!!
        assertEquals(zanshin.core.tibetan.Animal.SHEEP, w.man.animal)
        assertEquals(zanshin.core.tibetan.Animal.DRAGON, w.woman.animal)
    }
}
