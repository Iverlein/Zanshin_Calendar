/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app

import zanshin.core.tibetan.Gender
import zanshin.core.tibetan.Weekday
import zanshin.core.tibetan.BirthChild
import zanshin.core.tibetan.BirthOmen
import zanshin.core.tibetan.BirthReckoning
import zanshin.core.tibetan.ClanYear
import zanshin.core.tibetan.Element
import zanshin.core.tibetan.Forces
import zanshin.core.tibetan.HourSign
import zanshin.core.tibetan.Kin
import zanshin.core.tibetan.KinReckoning
import zanshin.core.tibetan.KinRole
import zanshin.core.tibetan.Sign
import zanshin.core.tibetan.TibetanCalendar
import zanshin.core.tibetan.DeadReckoning
import java.time.LocalTime
import zanshin.core.tibetan.Trigram
import zanshin.core.tibetan.YearOfLife
import zanshin.core.tibetan.YearReckoning

/**
 * The element reckonings built, as the Reckonings screen lists them (SPEC §10.11, ROADMAP R1), in the order of
 * docs/sources/reckonings.md's inventory. Each one built after R1 (R2…) is added here.
 */
enum class Reckoning { BIRTH, MATCH, YEAR_OF_AGE, DEAD, CIRCLES, MOMENT }

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

/**
 * The marriage match (ROADMAP R3) of [person] with their spouse [spouseName]: the man's and the woman's Tibetan birth
 * years and signs, whichever of the two the person is.
 */
data class MatchReading(
    val spouseName: String,
    val manYear: Int,
    val man: Sign,
    val womanYear: Int,
    val woman: Sign,
    /** The weekdays of the two births, where both dates are known. */
    val weekdays: Pair<Weekday, Weekday>? = null,
    /** The man's and the woman's natal trigrams, each where their mother's birth year is entered. */
    val manTrigram: Trigram? = null,
    val womanTrigram: Trigram? = null,
    /** The man's and the woman's parents' year signs, each where entered: father, mother. */
    val manParents: Pair<Sign?, Sign?> = null to null,
    val womanParents: Pair<Sign?, Sign?> = null to null,
)

/** What the Reckonings section reckons for a person, apart from the screen that shows it. */
object Reckonings {
    /** The reckonings the section lists for [person]: all of them need a birth date, so none without one. */
    fun built(person: Person?): List<Reckoning> = if (person == null) emptyList() else Reckoning.entries

    /** The sign of the Tibetan year [person] was born in. */
    fun birthSign(person: Person): Sign = TibetanCalendar.of(person.birth).let { Sign(it.yearElement, it.yearAnimal) }

    /** The Tibetan year of today's date. */
    fun presentYear(): Int = TibetanCalendar.of(java.time.LocalDate.now()).year

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
     * The marriage match of [person] with the first spouse entered under them whose birth year is known (ROADMAP
     * R3); null without one, or without the person's gender and the spouse's (the spouse taken as the other sex
     * where theirs is not set).
     */
    fun match(person: Person): MatchReading? {
        val gender = person.gender ?: return null
        val spouse = person.relatives.list.firstOrNull { it.of == null && it.relation == Relation.SPOUSE && it.birth != null } ?: return null
        val spouseGender = spouse.sex ?: if (gender == Gender.MALE) Gender.FEMALE else Gender.MALE
        if (spouseGender == gender) return null
        val own = TibetanCalendar.of(person.birth).let { it.year to Sign(it.yearElement, it.yearAnimal) }
        val theirs = spouse.birth!!.tibetanYear.let { it to YearOfLife.yearSign(it) }
        val (man, woman) = if (gender == Gender.MALE) own to theirs else theirs to own
        val weekdays = spouse.birth!!.date?.let { TibetanCalendar.of(person.birth).weekday to TibetanCalendar.of(it).weekday }
        val parent = { of: Int?, relation: Relation -> person.relatives.list.firstOrNull { it.of == of && it.relation == relation && it.birth != null } }
        val mother = { of: Int? -> parent(of, Relation.MOTHER) }
        val signs = { of: Int? -> listOf(Relation.FATHER, Relation.MOTHER).map { r -> parent(of, r)?.birth?.tibetanYear?.let { YearOfLife.yearSign(it) } }.let { it[0] to it[1] } }
        val (manParents, womanParents) = if (gender == Gender.MALE) signs(null) to signs(spouse.id) else signs(spouse.id) to signs(null)
        val ownTrigram = mother(null)?.let { KinReckoning.natalTrigram(it.birth!!.tibetanYear, own.first) }
        val theirTrigram = mother(spouse.id)?.let { KinReckoning.natalTrigram(it.birth!!.tibetanYear, theirs.first) }
        val (manTrigram, womanTrigram) = if (gender == Gender.MALE) ownTrigram to theirTrigram else theirTrigram to ownTrigram
        return MatchReading(spouse.name, man.first, man.second, woman.first, woman.second, weekdays, manTrigram, womanTrigram, manParents, womanParents)
    }

    /**
     * The reckoning for the dead (ROADMAP R2n) of each relative of [person] who has died with a birth year, a gender
     * (a parent's is the relation's) and a whole date of death; the hour of death where its time is known, from the
     * hare hour at 05:00, the Tibetan day turning at dawn.
     */
    fun dead(person: Person): List<Pair<Relative, DeadReckoning?>> = person.relatives.list.filter { it.died }.map { r ->
        val date = r.death?.date
        val gender = r.sex
        val birth = r.birth
        r to if (date == null || gender == null || birth == null) null else {
            val time = r.death.time
            val dayDate = if (time != null && time < LocalTime.of(5, 0)) date.minusDays(1) else date
            val day = TibetanCalendar.of(dayDate)
            val hour = time?.let {
                val minutes = Math.floorMod(it.hour * 60 + it.minute - 300, 1440)
                Forces.hours(Forces.signs(day).date)[(minutes / 120).coerceIn(0, 11)]
            }
            DeadReckoning.of(birth.tibetanYear, gender, day, hour)
        }
    }

    /**
     * The moment at [now] (ROADMAP R2o): the Tibetan day it falls in, the day turning at dawn, 05:00, and the minutes
     * since that dawn; the moment's trigram and mewa follow from them ([MomentReckoning]).
     */
    fun moment(now: java.time.ZonedDateTime): Pair<zanshin.core.tibetan.TibetanDay, Int> {
        val dayStart = if (now.toLocalTime() < LocalTime.of(5, 0)) now.toLocalDate().minusDays(1) else now.toLocalDate()
        val minutes = java.time.Duration.between(dayStart.atTime(5, 0).atZone(now.zone), now).toMinutes().toInt().coerceIn(0, 1439)
        return TibetanCalendar.of(dayStart) to minutes
    }

    /** How [person]'s birth year stands to their clan, the special pebble (ROADMAP R2); null while the clan is not set. */
    fun clanYear(person: Person): ClanYear? = person.clan?.let { BirthReckoning.clanYear(it, TibetanCalendar.of(person.birth).yearAnimal) }

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
