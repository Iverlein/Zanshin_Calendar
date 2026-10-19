/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app

import zanshin.core.tibetan.BirthChild
import zanshin.core.tibetan.BirthOmen
import zanshin.core.tibetan.BirthReckoning
import zanshin.core.tibetan.Element
import zanshin.core.tibetan.Forces
import zanshin.core.tibetan.HourSign
import zanshin.core.tibetan.Kin
import zanshin.core.tibetan.KinReckoning
import zanshin.core.tibetan.KinRole
import zanshin.core.tibetan.Sign
import zanshin.core.tibetan.TibetanCalendar
import zanshin.core.tibetan.Trigram
import zanshin.core.tibetan.YearOfLife
import zanshin.core.tibetan.YearReckoning

/**
 * The element reckonings built, as the Reckonings screen lists them (SPEC §10.11, ROADMAP R1), in the order of
 * docs/sources/reckonings.md's inventory. Each one built after R1 (R2…) is added here.
 */
enum class Reckoning { BIRTH, YEAR_OF_AGE }

/**
 * The reckoning of [person]'s birth as far as it is built (ROADMAP R2): the natal trigram, their [mother]'s trigram in
 * the year of the birth; what the trigram makes them against their life force [vitality]; and the readings of the
 * parents' trigrams that hold, with the [father]'s trigram where his birth year is known.
 */
data class BirthReading(
    val birthYear: Int,
    val birth: Sign,
    val vitality: Element,
    val mother: Kin,
    val natal: Trigram,
    val child: BirthChild,
    val father: Kin?,
    val fatherTrigram: Trigram?,
    val omens: List<BirthOmen>,
)

/** What the Reckonings section reckons for a person, apart from the screen that shows it. */
object Reckonings {
    /** The reckonings the section lists for [person]: all of them need a birth date, so none without one. */
    fun built(person: Person?): List<Reckoning> = if (person == null) emptyList() else Reckoning.entries

    /** The Tibetan year [person] was born in, which is their first year of age. */
    fun birthYear(person: Person): Int = TibetanCalendar.of(person.birth).year

    /**
     * The year of age of [person] in the Tibetan year [year] (SPEC §5.9.1), reckoned at [hour], the hour of
     * reckoning; null before their first year. The year's sign is the sixty-year cycle's for [year], so a year
     * stepped to on the screen reckons as the year's balloon does from any day in it.
     */
    fun yearOfAge(person: Person, year: Int, hour: HourSign?): YearReckoning? {
        val born = TibetanCalendar.of(person.birth)
        if (YearOfLife.age(born.year, year) < 1) return null
        return YearReckoning(Sign(born.yearElement, born.yearAnimal), born.year, YearOfLife.yearSign(year), year, person.gender, hour)
    }

    /** The reckoning of [person]'s birth (ROADMAP R2); null without their mother's birth year, from which the natal trigram follows. */
    fun birth(person: Person): BirthReading? {
        val born = TibetanCalendar.of(person.birth)
        val birth = Sign(born.yearElement, born.yearAnimal)
        val kin = kin(person)
        val mother = kin.firstOrNull { it.role == KinRole.MOTHER && it.birthYear != null } ?: return null
        val natal = KinReckoning.natalTrigram(mother.birthYear!!, born.year) ?: return null
        val vitality = Forces.of(birth.element, birth.animal).vitality
        val father = kin.firstOrNull { it.role == KinRole.FATHER && it.birthYear != null }
        val fatherTrigram = father?.let { BirthReckoning.fatherTrigram(it.birthYear!!, born.year) }
        return BirthReading(
            born.year, birth, vitality, mother, natal, BirthReckoning.child(vitality, natal), father, fatherTrigram,
            BirthReckoning.omens(natal, birth.animal, person.gender, mother.birth!!.animal, fatherTrigram),
        )
    }

    /**
     * [person]'s relatives as the year of age's obstacles read them (ROADMAP P2): the mother, father, spouses and
     * children entered under the person, and anyone of the household further out in the tree; each tagged with
     * their id in the tree.
     */
    fun kin(person: Person): List<Kin> = person.relatives.list.mapNotNull { r ->
        val role = when {
            r.of == null && r.relation == Relation.MOTHER -> KinRole.MOTHER
            r.of == null && r.relation == Relation.FATHER -> KinRole.FATHER
            r.of == null && r.relation == Relation.SPOUSE -> KinRole.SPOUSE
            r.of == null && r.relation == Relation.CHILD -> KinRole.CHILD
            r.household -> KinRole.OTHER
            else -> return@mapNotNull null
        }
        Kin(role, r.birth?.tibetanYear, r.sex, !r.died, r.death?.tibetanYear, r.household, r.id)
    }
}
