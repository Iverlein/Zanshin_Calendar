/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import zanshin.core.texts.gloss
import zanshin.core.time.amod

/*
 * The signs a person carries through the years of life, from the elemental
 * divination ('byung rtsis) of the White Beryl's chapter 21 (vol. 1, 1996,
 * pp. 255–258, img. 265–268): the sme ba of the birth year and of each year
 * of age, the trigram of each year of age, and the twelve sectors of growth
 * and decline; and the progressed sign (log men) of chapter 24.
 * docs/sources/year-of-life.md quotes each rule.
 */

/** The element of a sme ba: the three whites iron, black and blue water, green wood, the reds fire, yellow earth (WB vol. 1, p. 255). */
fun smeBaElement(n: Int): Element = when (n) {
    1, 6, 8 -> Element.IRON
    2, 3 -> Element.WATER
    4 -> Element.WOOD
    5 -> Element.EARTH
    7, 9 -> Element.FIRE
    else -> throw IllegalArgumentException("no sme ba $n")
}

/** Male years are those of the mouse, tiger, dragon, horse, monkey and dog. */
val Animal.gender: Gender get() = if (ordinal % 2 == 0) Gender.MALE else Gender.FEMALE

/**
 * The twelve sectors of growth and decline (*dar gud bcu gnyis*, WB vol. 1,
 * p. 258), in their order; the six from the body complete to flourishing are
 * good, the six from decline to the breath-taking bad.
 */
enum class Sector(val wylie: String, val good: Boolean) {
    TAKING_BREATH("dbugs len", false),
    IN_THE_WOMB("mngal gnas", false),
    BODY_COMPLETE("lus rdzogs", true),
    BIRTH("btsas", true),
    BATHING("khrus byed", true),
    DRESSING("gos gyon", true),
    WORKING("las byed", true),
    FLOURISHING("dar ba", true),
    DECLINE("gud pa", false),
    ILLNESS("na ba", false),
    DEATH("shi ba", false),
    ENTERING_THE_TOMB("dur zhugs", false);

    val english: String get() = gloss(this)
}

/** One of the four aspects of the birth year in its sector for the present year. */
data class AspectSector(val force: Force, val element: Element, val sector: Sector)

object YearOfLife {
    /**
     * The age in the Tibetan count: one in the year of birth, one more at
     * each new year (Losar). [birthYear] and [year] are the Tibetan years'
     * numbers ([TibetanDay.year]).
     */
    fun age(birthYear: Int, year: Int): Int = year - birthYear + 1

    /**
     * The sme ba of a year, the natal sme ba of those born in it: one less
     * each year, round the nine; "the present rabjung's fire hare", 1687, has
     * the seven-red (WB vol. 1, p. 255), the count of the 九星 year star.
     */
    fun yearSmeBa(year: Int): Int = amod(7 - (year - 1687), 9)

    /** The path of the yearly sme ba from the middle, by the birth year's gender (WB vol. 1, p. 256). */
    private val MALE_PATH = listOf(
        Direction.CENTRE, Direction.EAST, Direction.NORTH_EAST, Direction.NORTH, Direction.NORTH_WEST,
        Direction.WEST, Direction.SOUTH_WEST, Direction.SOUTH, Direction.SOUTH_EAST,
    )
    private val FEMALE_PATH = listOf(
        Direction.CENTRE, Direction.EAST, Direction.SOUTH_EAST, Direction.SOUTH, Direction.SOUTH_WEST,
        Direction.WEST, Direction.NORTH_WEST, Direction.NORTH, Direction.NORTH_EAST,
    )

    /** The square with 5 in the middle, as [DaySmeBa] places it. */
    private val SQUARE = mapOf(
        Direction.SOUTH_EAST to 4, Direction.SOUTH to 9, Direction.SOUTH_WEST to 2,
        Direction.EAST to 3, Direction.CENTRE to 5, Direction.WEST to 7,
        Direction.NORTH_EAST to 8, Direction.NORTH to 1, Direction.NORTH_WEST to 6,
    )

    /** The number standing at [place] in the square with [centre] in the middle. */
    fun smeBaAt(place: Direction, centre: Int): Int = amod(SQUARE.getValue(place) - 5 + centre, 9)

    /**
     * The sme ba of the year of age [age] (*babs sme*): the natal sme ba is put
     * in the middle and the count goes out from the middle to the east, one
     * sme ba a year; "whether the person is male or female", in a male year
     * on to the north-east and back to the middle from the south-east, in a
     * female year on to the south-east and back from the north-east (WB vol. 1,
     * p. 256). [birthYearGender] is the gender of the birth year, not of the person.
     */
    fun currentSmeBa(natal: Int, birthYearGender: Gender, age: Int): Int {
        val path = if (birthYearGender == Gender.MALE) MALE_PATH else FEMALE_PATH
        return smeBaAt(path[Math.floorMod(age - 1, 9)], natal)
    }

    /**
     * The trigram of the year of age [age] (*babs spar*): "a man from Li
     * towards Khon, a woman from Kham towards Khen; one trigram for each
     * year" (WB vol. 1, p. 257). [Trigram]'s order is Li's towards Khon.
     */
    fun currentTrigram(gender: Gender, age: Int): Trigram {
        val step = Math.floorMod(age - 1, 8)
        return when (gender) {
            Gender.MALE -> Trigram.entries[step]
            Gender.FEMALE -> Trigram.entries[Math.floorMod(Trigram.KHAM.ordinal - step, 8)]
        }
    }

    /**
     * Where an element's breath-taking sector lies: earth and water at the
     * snake, fire at the pig, iron at the tiger, wood at the monkey; the
     * sectors then go on with the animals (WB vol. 1, p. 258).
     */
    fun breathTaking(element: Element): Animal = when (element) {
        Element.EARTH, Element.WATER -> Animal.SNAKE
        Element.FIRE -> Animal.PIG
        Element.IRON -> Animal.TIGER
        Element.WOOD -> Animal.MONKEY
    }

    /** The sector of [element] that the year, month, day or hour of [animal] falls in. */
    fun sector(element: Element, animal: Animal): Sector =
        Sector.entries[Math.floorMod(animal.ordinal - breathTaking(element).ordinal, 12)]

    /**
     * The pebbles of a sector (the Moonbeams, KD vol. 3, pp. 496 and 507):
     * flourishing and working the best of the good, three white; bathing and
     * dressing the middle, two; body complete and birth the least, one; the
     * breath-taking and the womb the best of the bad, one white and one
     * black; decline and illness the middle, one black; death and the tomb
     * the worst, two black.
     */
    fun sectorPebbles(sector: Sector): Pebbles = when (sector) {
        Sector.FLOURISHING, Sector.WORKING -> Pebbles(3, 0)
        Sector.BATHING, Sector.DRESSING -> Pebbles(2, 0)
        Sector.BODY_COMPLETE, Sector.BIRTH -> Pebbles(1, 0)
        Sector.TAKING_BREATH, Sector.IN_THE_WOMB -> Pebbles(1, 1)
        Sector.DECLINE, Sector.ILLNESS -> Pebbles(0, 1)
        Sector.DEATH, Sector.ENTERING_THE_TOMB -> Pebbles(0, 2)
    }

    /**
     * A trigram's element: Li fire, Khon earth, Dwa iron, Kham water, Zin
     * wood (WB vol. 1, p. 256); Khen the sky, Gin the mountain and Zon the
     * wind, which "are also made earth, so that they come to the five
     * elements" (the Moonbeams, KD vol. 3, p. 493).
     */
    fun trigramElement(t: Trigram): Element = when (t) {
        Trigram.LI -> Element.FIRE
        Trigram.DWA -> Element.IRON
        Trigram.KHAM -> Element.WATER
        Trigram.ZIN -> Element.WOOD
        Trigram.KHON, Trigram.KHEN, Trigram.GIN, Trigram.ZON -> Element.EARTH
    }

    /** The place of year [n] in the sixty, 0 = wood mouse. */
    fun sexagenary(s: Sign): Int = (0 until 60).first { it % 12 == s.animal.ordinal && it / 2 % 5 == s.element.ordinal }

    /** The sign of the Tibetan year [year] ([TibetanDay.year]); 1984 is a wood mouse. */
    fun yearSign(year: Int): Sign = signOf(year - 1984)

    fun signOf(n: Int): Sign = Math.floorMod(n, 60).let { Sign(Element.entries[it / 2 % 5], Animal.entries[it % 12]) }

    /**
     * The progressed sign (*log men*) of the year of age [age]: "whatever the
     * element of the subject's year, a man from the tiger of its son, counting
     * the years of age down; a woman from the monkey of its mother, counting
     * up" (WB vol. 1, p. 387). A man's count runs forward through the sixty,
     * as Gyurme Dorje's chart 6.2 shows (fire dragon, 23rd year: iron mouse),
     * a woman's backward.
     */
    fun logMen(birthElement: Element, gender: Gender, age: Int): Sign = when (gender) {
        Gender.MALE -> signOf(sexagenary(Sign(birthElement.feeds, Animal.TIGER)) + (age - 1))
        Gender.FEMALE -> signOf(sexagenary(Sign(Element.entries.first { it.feeds == birthElement }, Animal.MONKEY)) - (age - 1))
    }

    /** WB vol. 1, p. 387: the dog a man's sky door and the pig his earth door, the dragon and the snake a woman's; ox and sheep the ruins, bird and monkey the separations, mouse and horse the lineage-cuttings, tiger and hare the gains. */
    fun logMenPlace(animal: Animal, gender: Gender): LogMenPlace? = when (animal) {
        Animal.DOG -> if (gender == Gender.MALE) LogMenPlace.SKY_DOOR else null
        Animal.PIG -> if (gender == Gender.MALE) LogMenPlace.EARTH_DOOR else null
        Animal.DRAGON -> if (gender == Gender.FEMALE) LogMenPlace.SKY_DOOR else null
        Animal.SNAKE -> if (gender == Gender.FEMALE) LogMenPlace.EARTH_DOOR else null
        Animal.OX, Animal.SHEEP -> LogMenPlace.RUIN
        Animal.BIRD, Animal.MONKEY -> LogMenPlace.SEPARATION
        Animal.MOUSE, Animal.HORSE -> LogMenPlace.LINEAGE_CUT
        Animal.TIGER, Animal.RABBIT -> LogMenPlace.GAIN
    }

    /** Each of the four aspects of the birth year in its sector for the year of [yearAnimal]: "where it reaches the year is the year's" (p. 258). */
    fun sectors(birth: YearForces, yearAnimal: Animal): List<AspectSector> =
        Force.entries.map { AspectSector(it, birth[it], sector(birth[it], yearAnimal)) }
}

/** The six unchanging basic pebbles of a year's reckoning (WB vol. 1, p. 380), each set against the four aspects. */
enum class BasicSign {
    PRESENT_YEAR, LOG_MEN, TRIGRAM, SME_BA, SECTOR, HOUR;

    val english: String get() = gloss(this)
}

/** One of the twenty-four decisive pebbles: an aspect of the birth year against one basic sign. */
data class DecisivePebble(
    val basic: BasicSign,
    val force: Force,
    val own: Element,
    /** The basic sign's element for this aspect; null for the sector, whose pebbles go by its rank. */
    val other: Element?,
    val sector: Sector?,
    val pebbles: Pebbles,
) {
    val kinship: Kinship? get() = other?.let { Forces.kinship(own, it) }
}

/** The harsh years and the progressed sign's hard places (WB vol. 1, pp. 387–391), each read on its own. */
enum class HarshYear {
    /** The year of one's own animal (*rang keg*), from the 13th. */
    OWN_YEAR,
    /** The seventh from one's own animal (*bdun zur*, *dgra gshed*). */
    SEVENTH,
    /** The other two animals of one's triad (*mthun gsum*), sharing its luck. */
    TRIAD,
    /** The fourth animal counted up, backward from one's own (*yar bzhi*): illness. */
    FOURTH_UP,
    /** The fourth animal counted down, forward from one's own (*mar bzhi*): death. */
    FOURTH_DOWN,
    /** The progressed sign come to one's own birth sign. */
    LOG_MEN_OWN,
    /** The progressed sign on the seventh from one's own birth sign. */
    LOG_MEN_SEVENTH,
    /** The progressed sign's element the enemy of one's vitality (*bdud gcod*). */
    LOG_MEN_ENEMY;

    val english: String get() = gloss(this)
}

/** A harsh year that holds, with how the present year's element stands to the birth year's where the reading goes by it. */
data class Harsh(val year: HarshYear, val kinship: Kinship? = null)

/**
 * What the progressed sign's animal brings (WB vol. 1, p. 387): the sky door
 * and the earth door, which differ for men and women, the five ruins, the
 * five separations, the five lineage-cuttings and the five gains.
 */
enum class LogMenPlace(val wylie: String) {
    SKY_DOOR("gnam sgo"),
    EARTH_DOOR("sa sgo"),
    RUIN("'phung gyod"),
    SEPARATION("bye bral"),
    LINEAGE_CUT("rus gcod"),
    GAIN("'byor pa");

    val english: String get() = gloss(this)
}

/** The four small obstacles of the sme ba (*sme ba'i keg phran bzhi*, WB vol. 1, pp. 408–409). */
enum class SmeBaObstacle(val wylie: String) {
    /** The year's sme ba falls on that of the present year. */
    HOUSE("khang keg"),
    /** It falls on one's natal sme ba. */
    BED("mal keg"),
    /** It falls on the two-black. */
    LAND("yul keg"),
    /** It is the enemy of the natal sme ba, or the two are fire and iron. */
    ROYAL_GATE("rgyal sgo 'gags pa");

    val english: String get() = gloss(this)
}

/**
 * One person's year: their age in the Tibetan count and every sign the
 * elemental divination reckons for it. [gender] null leaves out the
 * progressed sign and the trigram, which go by it; [hour] null leaves out
 * the hour of reckoning. The predictive pebbles need all six basic signs.
 */
data class YearReckoning(
    val birth: Sign,
    val birthYear: Int,
    val present: Sign,
    val year: Int,
    val gender: Gender?,
    val hour: HourSign?,
) {
    val age: Int = YearOfLife.age(birthYear, year)
    val forces: YearForces = birth.forces
    val natalSmeBa: Int = YearOfLife.yearSmeBa(birthYear)
    val yearSmeBa: Int = YearOfLife.yearSmeBa(year)
    val currentSmeBa: Int = YearOfLife.currentSmeBa(natalSmeBa, birth.animal.gender, age)
    val trigram: Trigram? = gender?.let { YearOfLife.currentTrigram(it, age) }
    val logMen: Sign? = gender?.let { YearOfLife.logMen(birth.element, it, age) }
    val sectors: List<AspectSector> = YearOfLife.sectors(forces, present.animal)

    /** The decisive pebbles, by basic sign in the order of the reckoning and by aspect within each. */
    val pebbles: List<DecisivePebble> = buildList {
        fun sign(basic: BasicSign, s: YearForces) = Force.entries.forEach { f ->
            add(DecisivePebble(basic, f, forces[f], s[f], null, Forces.pebbles(Forces.kinship(forces[f], s[f]), forces[f])))
        }
        fun element(basic: BasicSign, e: Element) = Force.entries.forEach { f ->
            add(DecisivePebble(basic, f, forces[f], e, null, Forces.pebbles(Forces.kinship(forces[f], e), forces[f])))
        }
        sign(BasicSign.PRESENT_YEAR, present.forces)
        logMen?.let { sign(BasicSign.LOG_MEN, it.forces) }
        trigram?.let { element(BasicSign.TRIGRAM, YearOfLife.trigramElement(it)) }
        element(BasicSign.SME_BA, smeBaElement(currentSmeBa))
        sectors.forEach { add(DecisivePebble(BasicSign.SECTOR, it.force, it.element, null, it.sector, YearOfLife.sectorPebbles(it.sector))) }
        hour?.let { sign(BasicSign.HOUR, it.sign.forces) }
    }

    /** True once all six basic signs are reckoned, so that the predictive pebbles can be laid. */
    val complete: Boolean get() = gender != null && hour != null

    /** The white and the black pebbles laid for [force]. */
    fun tally(force: Force): Pebbles = pebbles.filter { it.force == force }
        .fold(Pebbles(0, 0)) { a, p -> Pebbles(a.white + p.pebbles.white, a.black + p.pebbles.black) }

    /**
     * Where the predictive pebble of [force] goes: on whichever are more, the
     * white (true) or the black (false), "counting the white and black that
     * are there, not the threes and twos" (WB vol. 1, p. 381); null when they
     * are even or the reckoning is not [complete].
     */
    fun predictive(force: Force): Boolean? {
        if (!complete) return null
        val t = tally(force)
        return when {
            t.white > t.black -> true
            t.black > t.white -> false
            else -> null
        }
    }

    /** The harsh years that hold this year, in the order WB gives them. */
    val harsh: List<Harsh> = buildList {
        val step = Math.floorMod(present.animal.ordinal - birth.animal.ordinal, 12)
        val kin = Forces.kinship(birth.element, present.element)
        if (step == 0 && age >= 13) add(Harsh(HarshYear.OWN_YEAR, kin))
        if (step == 6) add(Harsh(HarshYear.SEVENTH, kin))
        if (step == 4 || step == 8) add(Harsh(HarshYear.TRIAD))
        if (step == 9) add(Harsh(HarshYear.FOURTH_UP))
        if (step == 3) add(Harsh(HarshYear.FOURTH_DOWN))
        logMen?.let { lm ->
            if (lm == birth) add(Harsh(HarshYear.LOG_MEN_OWN))
            if (Math.floorMod(lm.animal.ordinal - birth.animal.ordinal, 12) == 6) add(Harsh(HarshYear.LOG_MEN_SEVENTH))
            if (Forces.kinship(forces.vitality, lm.element) == Kinship.ENEMY) add(Harsh(HarshYear.LOG_MEN_ENEMY))
        }
    }

    /** Where the progressed sign's animal stands among the doors and the fives; null for a man's dragon or snake, a woman's dog or pig. */
    val logMenPlace: LogMenPlace? = logMen?.let { YearOfLife.logMenPlace(it.animal, gender!!) }

    /** A year of the nine-multiples (*dgu mig*): the 9th, 18th … 81st (WB vol. 1, pp. 410–411); needs the gender. */
    val nineMultiple: Boolean get() = gender != null && age % 9 == 0 && age in 9..81

    /** The sme ba's small obstacles that hold this year. */
    val smeBaObstacles: List<SmeBaObstacle> = buildList {
        if (currentSmeBa == yearSmeBa) add(SmeBaObstacle.HOUSE)
        if (currentSmeBa == natalSmeBa) add(SmeBaObstacle.BED)
        if (currentSmeBa == 2) add(SmeBaObstacle.LAND)
        val (c, n) = smeBaElement(currentSmeBa) to smeBaElement(natalSmeBa)
        if (c.overcomes == n || setOf(c, n) == setOf(Element.FIRE, Element.IRON)) add(SmeBaObstacle.ROYAL_GATE)
    }
}
