/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import zanshin.core.tibetan.Animal.BIRD
import zanshin.core.tibetan.Animal.DOG
import zanshin.core.tibetan.Animal.DRAGON
import zanshin.core.tibetan.Animal.HORSE
import zanshin.core.tibetan.Animal.MONKEY
import zanshin.core.tibetan.Animal.MOUSE
import zanshin.core.tibetan.Animal.OX
import zanshin.core.tibetan.Animal.PIG
import zanshin.core.tibetan.Animal.RABBIT
import zanshin.core.tibetan.Animal.SHEEP
import zanshin.core.tibetan.Animal.SNAKE
import zanshin.core.tibetan.Animal.TIGER

/**
 * The earth lords that move by date (*tshes la rgyu ba'i sa bdag*), the White Beryl's chapter 31,
 * vol. 2, pp. 226–235 (docs/sources/earth-lord-courses.md): each moves, strikes or turns back on
 * dates of the month, which WB's almanac writes on the day (vol. 1, pp. 173, 178: the Chinese
 * reckoning's earth lords, the strikes and turnings of the nāgas and the eight classes, the sky
 * doors and *gnyan pa*). [wylie] is the name WB heads the course with.
 *
 * The first of them, the great black day (*nyi ma nag chen*), is [GreatBlackDay]; the course of
 * Rāhu among them (section 27) is [RahuBySeason], weighed with Rāhu's other courses.
 */
enum class EarthLordCourse(val wylie: String) {
    /** The black months, before the courses (p. 212), and that year's *ki kang* black month: [MonthEntries]. */
    ZLA_NAG("zla nag"),
    KI_KANG_ZLA_NAG("ki kang zla nag"),
    /** Only the "one person's view" of the great black day, whose own course is [GreatBlackDay]. */
    NAG_CHEN("nyi ma nag chen"),
    NAG_CHUNG("nyi ma nag chung"),
    PI_LING("pi ling 'phar ma"),
    ZIN_PHUNG("zin phung"),
    PHUNG_ZOR("phung po zor thogs"),
    KI_KANG("ki kang"),
    HAL_KHYI("hal khyi nag po"),
    GNAM_KHYI("gnam khyi nag po"),
    GNAM_SBYOR("gnam sbyor"),
    GZA_RGOD("gza' rgod"),
    DBUL_PO("dbul po lag stong"),
    GZA_BDUN("gza' bdun"),
    NGAM_SHING("ngam shing"),
    BAR_KHYI("bar khyi"),
    KA_KHYUNG("ka khyung ki kang"),
    DRA_CHEN("dra chen"),
    SDE_BRGYAD("sde brgyad"),
    SA_BDAG_BZLOG("sa bdag bzlog"),
    SPUG_STON("spug ston"),
    GNYAN("gnyan"),
    KLU("klu"),
    CLASS_TIMES("thebs dus"),
    GNAM_SGO("gnam sgo"),
    ZHAG_NAG("zhag nag"),
}

/**
 * What a course does on its date: the earth lord moves (seeks food), strikes (*thebs*) or turns back
 * (*bzlog*); *zin phung* on its spring days of the mouse, horse, bird and hare sits in the middle.
 */
enum class CourseEvent { MOVES, STRIKES, TURNS_BACK, SITS }

/**
 * One course on one day: [season] is the Chinese reckoning's season-month, 0 the first month of
 * spring (Hor month 11, the tiger month) to 11 the last of winter; [variant] says which of a course's
 * readings holds, where it has several (the sky door's kind, *zin phung*'s quarter, the class whose
 * time it is); [date], the lunar date it fell on, as it stands, and [animal], the date's animal
 * ([EarthLordCourses.of] sets both).
 */
data class CourseDay(
    val course: EarthLordCourse,
    val event: CourseEvent,
    val season: Int,
    val variant: String? = null,
    val date: Int? = null,
    val animal: Animal? = null,
)

/**
 * How a course fell on a day, the line of its reading's *How it falls* row (SPEC §8.1, rule 4): the
 * line of the course's table that holds the shown day, never the table. [season] is the season-month,
 * 0 the first month of spring to 11 the last of winter. [at] is the catalog key of what the line adds,
 * the hour, the time of day or the way the course goes (`CourseAt.*`), where the source gives one;
 * [label] the key of what the course does on the line (`CourseFall.event.*`), named where one reading shows
 * two lines, the nyen moving and striking on one date.
 */
sealed interface Fall {
    val at: String?
    val label: String?
}

/** A course with one date in each season-month: [dates] the season's three in turn, [date] the one of [season]. */
data class CourseFall(
    val season: Int,
    val date: Int,
    val dates: List<Int>,
    override val at: String? = null,
    override val label: String? = null,
) : Fall {
    /** The season, 0 spring to 3 winter. */
    val quarter: Int get() = season / 3

    /** The month within the season, 0 the first, 1 the middle, 2 the last. */
    val monthOfSeason: Int get() = season % 3
}

/** A course with its own dates in each season-month: [dates] those of [season], [date] the one that fell. */
data class MonthFall(
    val season: Int,
    val date: Int,
    val dates: Set<Int>,
    override val at: String? = null,
    override val label: String? = null,
) : Fall

/**
 * A course by the date's animal: [animals] are its days in [season], or in the whole of [season]'s
 * season where [wholeSeason]; [animal] the shown day's.
 */
data class AnimalFall(
    val season: Int,
    val animal: Animal,
    val animals: Set<Animal>,
    val wholeSeason: Boolean = false,
    override val at: String? = null,
    override val label: String? = null,
) : Fall

/** A course on the same [dates] of every month (the sky doors by the date's last figure), [date] the one that fell. */
data class EveryMonthFall(val date: Int, val dates: Set<Int>, override val at: String? = null, override val label: String? = null) : Fall

/**
 * The courses of WB vol. 2, pp. 226–235, read on the scan (docs/sources/earth-lord-courses.md).
 * Chapter 31 counts by the Chinese reckoning ([SeasonReckoning.CHINESE]): its first month of spring
 * is the 11th, the tiger month, so a course "of the tiger month" and one "of the first month of
 * spring" fall in the same Hor month, and the season-months 0–11 are also the animal months from
 * the tiger. A course keyed by "the day" (*nyi ma*) takes the date's animal, as the chapter's earth
 * lords do (docs/sources/lunar-day-signs.md, question 9). Keyed by the date as it stands, like
 * [GreatBlackDay]: a skipped date has none, a doubled one has it on both days, a leap month as its
 * month. Where WB gives a course and then "another way" (*lugs gcig*) or what "some say", the first
 * course is built and the others are in the topic file.
 */
object EarthLordCourses {
    /** The season-months of each animal month from the tiger: tiger 0, hare 1 … ox 11. */
    private fun months(vararg animals: Animal): Set<Int> = animals.map { (it.ordinal - TIGER.ordinal + 12) % 12 }.toSet()

    /** Dates by season-month, 0–11. */
    private fun bySeason(vararg dates: Int): List<Set<Int>> = dates.map { setOf(it) }

    /** Three dates ten days apart, "the three Xs" in order, the season's three months: X, X + 10, X + 20. */
    private fun threes(vararg first: Int): List<Set<Int>> = first.flatMap { x -> listOf(setOf(x), setOf(x + 10), setOf(x + 20)) }

    private val ALL = (1..30).toSet()

    /** The *nyi ma nag chung*, *bya ma gung rgyal* (section 2): the three 8s in spring, 5s in summer, 1s in autumn; 4, 18, 16 in winter. */
    val NAG_CHUNG: List<Set<Int>> = threes(8, 5, 1).take(9) + bySeason(4, 18, 16)

    /** The *pi ling 'phar ma*: one date and hour in each season-month. */
    val PI_LING: List<Set<Int>> = bySeason(28, 18, 8, 25, 15, 5, 21, 11, 1, 29, 19, 9)
    val PI_LING_HOURS: List<Animal> = listOf(PIG, MONKEY, SNAKE, PIG, MONKEY, SNAKE, DOG, SHEEP, DRAGON, BIRD, HORSE, RABBIT)

    /** *Zin phung* (section 14): the days it moves on in each season, and the spring days it sits in the middle. */
    val ZIN_PHUNG_DAYS: List<Set<Animal>> = listOf(setOf(TIGER, MONKEY), setOf(OX, SHEEP), setOf(DOG, DRAGON), setOf(PIG, SNAKE))
    val ZIN_PHUNG_MIDDLE: Set<Animal> = setOf(MOUSE, HORSE, BIRD, RABBIT)

    /** *Zin phung*'s time of day, month by month from the first month of spring (`CourseAt.*`). */
    val ZIN_PHUNG_TIMES: List<String> = listOf(
        "ROOST", "MIDNIGHT", "DAWN", "LATE_AFTERNOON", "FIRST_SUN", "MORNING_WARMTH", "NIGHTFALL", "SUNRISE", "MORNING", "NOON", "MIDNIGHT", "EVENING",
    )

    /** *Phung po zor thogs* (section 15), by animal month from the tiger. */
    val PHUNG_ZOR: List<Set<Int>> = bySeason(8, 8, 9, 3, 23, 9, 7, 17, 8, 1, 16, 9)

    /** *Ki kang* (section 16), its first course, by groups of animal months. */
    val KI_KANG: Map<Set<Int>, Set<Int>> = mapOf(
        months(TIGER, HORSE, DOG) to setOf(15, 28),
        months(PIG, SHEEP, RABBIT) to setOf(2, 3),
        months(MOUSE, DRAGON, MONKEY) to setOf(18, 28),
        months(BIRD, OX, SNAKE) to setOf(16, 26),
    )

    /** *Hal khyi nag po* (section 17), seeking food: one date and hour in each animal month from the tiger. */
    val HAL_KHYI: List<Set<Int>> = bySeason(8, 16, 24, 9, 18, 27, 10, 20, 30, 11, 22, 3)
    val HAL_KHYI_HOURS: List<Animal> = listOf(BIRD, HORSE, TIGER, OX, PIG, BIRD, SNAKE, DRAGON, TIGER, BIRD, MONKEY, RABBIT)

    /** *Gnam khyi nag po* (section 18), its first course: the days of each season-month it seeks food on. */
    val GNAM_KHYI: List<Set<Animal>> = listOf(
        setOf(DRAGON, SHEEP), setOf(SNAKE, MONKEY), setOf(HORSE, BIRD),
        setOf(DOG, SHEEP), setOf(PIG, SNAKE), setOf(MOUSE, HORSE, MONKEY),
        setOf(DRAGON, SHEEP), setOf(TIGER, SHEEP, PIG), setOf(MOUSE, RABBIT, MONKEY),
        setOf(OX, TIGER, DRAGON), setOf(TIGER, DRAGON, DOG), setOf(PIG, RABBIT, MOUSE),
    )
    val GNAM_KHYI_TIMES: List<String> = listOf(
        "MORNING_WARMTH", "NOON", "DAYBREAK", "LATE_AFTERNOON", "SUNRISE", "DUSK", "DUSK", "MORNING_WARMTH", "MIDNIGHT", "LATE_AFTERNOON", "SUNSET", "DUSK",
    )

    /** *Gnam sbyor* (section 19), by season-month. */
    val GNAM_SBYOR: List<Set<Int>> = bySeason(8, 7, 4, 7, 7, 26, 19, 19, 7, 16, 19, 24)

    /** *Gza' rgod* (section 20), in six animal months. */
    val GZA_RGOD: Map<Set<Int>, Set<Int>> = mapOf(
        months(DRAGON, SHEEP) to setOf(18),
        months(PIG, SNAKE) to setOf(16, 22),
        months(HORSE) to setOf(13),
        months(MONKEY) to setOf(9, 22),
    )

    /** *Dbul po lag stong* (section 21), by animal month from the tiger. */
    val DBUL_PO: List<Set<Int>> = listOf(
        setOf(1, 11), setOf(2, 21), setOf(3, 8), setOf(4, 8), setOf(5, 11), setOf(6, 22),
        setOf(7, 19), setOf(8, 10), setOf(22, 9), setOf(1), setOf(1, 16), setOf(9, 29),
    )

    /** *Gza' bdun* (section 22), its first course, and *bar khyi* (section 24): the three 8s, 5s, 1s and 9s of the seasons. */
    val GZA_BDUN: List<Set<Int>> = threes(8, 5, 1, 9)

    /** *Ngam shing* (section 23): eight season-months, one date each. */
    val NGAM_SHING: Map<Int, Int> = mapOf(0 to 10, 1 to 20, 4 to 30, 5 to 12, 6 to 10, 7 to 20, 10 to 30, 11 to 12)

    /** The way *ngam shing* goes in each of its season-months (`CourseAt.*`). */
    val NGAM_SHING_WAYS: Map<Int, String> = mapOf(
        0 to "EAST_WEST", 1 to "EAST_WEST", 4 to "SOUTH_NORTH", 5 to "EAST_WEST", 6 to "WEST_EAST", 7 to "EAST_WEST", 10 to "SOUTH_NORTH", 11 to "EAST_WEST",
    )

    /** *Ka khyung ki kang* (section 25), by groups of animal months. */
    val KA_KHYUNG: Map<Set<Int>, Set<Int>> = mapOf(
        months(TIGER, HORSE, DOG) to setOf(11, 27),
        months(PIG, SHEEP, RABBIT) to setOf(24),
        months(MOUSE, DRAGON, MONKEY) to setOf(28),
        months(BIRD, OX, SNAKE) to setOf(26),
    )

    /** *Dra chen* (section 26), in five animal months. */
    val DRA_CHEN: Map<Set<Int>, Set<Int>> = mapOf(
        months(SNAKE) to setOf(25), months(SHEEP) to setOf(15), months(BIRD) to setOf(1, 21), months(DOG) to setOf(21), months(PIG) to setOf(9),
    )

    /** The eight classes' general strikes and turnings (section 28), by season-month: the dates they strike and turn back on. */
    val SDE_BRGYAD_STRIKES: List<Set<Int>> = listOf(
        setOf(14, 15), setOf(16), setOf(22), setOf(8), setOf(15), setOf(9),
        setOf(15, 23), setOf(10) + (17..30).toSet() - 23, setOf(8, 28, 30), (13..16).toSet(), setOf(17) + (22..30).toSet(), ALL - 27,
    )
    val SDE_BRGYAD_TURNINGS: List<Set<Int>> = listOf(
        setOf(17, 19), setOf(1, 8, 11, 21, 28), setOf(9, 30), setOf(2, 5), setOf(6, 16, 18), setOf(11),
        setOf(25), setOf(3, 13, 23), setOf(1, 2), setOf(8, 9, 18, 26), setOf(20), setOf(27),
    )

    /** The earth lords' turning (section 29), by season-month. */
    val SA_BDAG_BZLOG: List<Set<Int>> = listOf(
        setOf(17, 19), setOf(1, 8), setOf(30), setOf(2, 5), setOf(3), setOf(11),
        setOf(25), setOf(3), setOf(2), setOf(18), setOf(27), setOf(14),
    )

    /** The *gnyan*'s moving times (section 30), by season-month. */
    val GNYAN_MOVES: List<Set<Int>> = bySeason(8, 6, 4, 7, 10, 27, 9, 30, 27, 19, 10, 3)

    /** The *gnyan*'s strikes and turnings as Spug ston has them, the course WB gives after section 30. */
    val GNYAN_STRIKES: List<Set<Int>> = listOf(
        setOf(7), setOf(13), setOf(21), setOf(3, 23), ALL, ALL, setOf(9), setOf(23), setOf(7), setOf(1), setOf(25), emptySet(),
    )
    val GNYAN_TURNINGS: List<Set<Int>> = listOf(
        setOf(19), setOf(15), setOf(18), setOf(16), emptySet(), emptySet(), setOf(18), setOf(30), setOf(1), setOf(7), setOf(30), ALL - 15,
    )

    /** The nāgas' strikes and turnings (section 31), by season-month; in the last month of winter they sleep. */
    val KLU_STRIKES: List<Set<Int>> = listOf(
        setOf(14), setOf(10), setOf(25), setOf(8, 15), setOf(20, 22), setOf(5, 20, 25),
        setOf(9, 19), setOf(15), setOf(1, 11, 21, 22), setOf(8, 18), setOf(7, 17), emptySet(),
    )
    val KLU_TURNINGS: List<Set<Int>> = listOf(
        setOf(5, 10, 15), setOf(8, 18, 20, 22, 28), ALL - setOf(2, 8, 25), setOf(20, 25), setOf(8, 15), setOf(11, 13, 15),
        setOf(5, 6, 10), setOf(3, 6, 9, 13, 16), setOf(9, 10, 19), setOf(9, 10, 19, 26), setOf(2, 6, 16, 26, 20), emptySet(),
    )

    /**
     * The classes' own times (sections 32–37): in the first months of the four seasons the nāgas
     * on the three 7s, the *rgyal po* on the three 8s and the *lam mo* on the 22nd; in the middle
     * months the *btsan* on the three 6s and the *gnod sbyin* on the three 9s; in the last months the
     * *bdud* on the 29th and the *gshin rje* on the 13th; the planets and the *srin po* (38–39) "on
     * the dates of the great time *biddhi*", Viṣṭi's (WB's karaṇa table, p. 343, writes «བིདྡྷི» in its
     * place), the eight dates whose half it holds (vol. 1, p. 177): the 4th, 8th, 11th, 15th, 18th,
     * 22nd, 25th and 29th of every month ([VISHTI_DATES]).
     */
    val VISHTI_DATES: Set<Int> = setOf(4, 8, 11, 15, 18, 22, 25, 29)
    val CLASS_TIMES: List<Map<String, Set<Int>>> = listOf(
        mapOf("klu" to setOf(7, 17, 27), "rgyal po" to setOf(8, 18, 28), "lam mo" to setOf(22)),
        mapOf("btsan" to setOf(6, 16, 26), "gnod sbyin" to setOf(9, 19, 29)),
        mapOf("bdud" to setOf(29), "gshin rje" to setOf(13)),
    )

    /**
     * Spug ston's view of the earth lords (p. 233), by season-month: the dates of success (*grub*,
     * when the earth lords' remedies help), of vanishing (*yal*, when they turn back on oneself) and of
     * not giving out wealth. The last month of summer's «གསུམ་དྲུག་ཉེར་ཡལ» (the Zhol print; the 1996 edition
     * has ཉར) is read as the autumn lines are, success, vanishing and wealth in turn: the 3rd, and the
     * 26th inverted for the metre. Each month's direction for sending the ransom offering («ཡས་ལམ»; WB
     * uses *yas* for the ransom sent to the spirits, «གཉན་རྣམས་ཡས་ཀྱིས་བཀར») is in the reading.
     */
    val SPUG_STON: List<Map<String, Set<Int>>> = listOf(
        mapOf("grub" to setOf(5, 30), "yal" to setOf(8), "nor" to setOf(1, 6)),
        mapOf("grub" to setOf(8), "yal" to setOf(6), "nor" to setOf(12)),
        mapOf("grub" to setOf(18), "yal" to setOf(6), "nor" to setOf(8)),
        mapOf("grub" to setOf(1), "yal" to setOf(6), "nor" to setOf(8)),
        mapOf("grub" to setOf(19), "yal" to setOf(26), "nor" to setOf(16)),
        mapOf("grub" to setOf(3), "yal" to setOf(26), "nor" to setOf(28)),
        mapOf("grub" to setOf(19), "yal" to setOf(29), "nor" to setOf(9)),
        mapOf("grub" to setOf(9), "yal" to setOf(12), "nor" to setOf(10)),
        mapOf("grub" to setOf(7), "yal" to setOf(4), "nor" to setOf(27)),
        mapOf("grub" to setOf(27), "yal" to setOf(9), "nor" to setOf(20)),
        mapOf("grub" to setOf(30), "yal" to setOf(2), "nor" to setOf(12, 20)),
        mapOf("grub" to setOf(19), "yal" to setOf(6), "nor" to setOf(30)),
    )

    /** The ten sky doors, one for each date's last figure, 1 the guests' … 10 the general one (vol. 2, pp. 234–235). */
    val GNAM_SGO = listOf("mgron po", "tshong", "bu chung", "dmag", "gnyen", "mkhar", "bag ma", "dur", "shid", "spyi")

    /** The sky door of [date]. */
    fun skyDoor(date: Int): String = GNAM_SGO[(date - 1) % 10]

    /**
     * The black days (p. 235): "as the black months", which are the four-slayer of the animal that
     * rises (vol. 1, p. 183: «བྱི་རྟ་གནམ་ཤར་བྱ་ཡོས་ནག། གླང་ལུག་ཤར་ཚེ་ཁྱི་འབྲུག་ནག། སྟག་སྤྲེལ་ཤར་ན་ཕག་སྦྲུལ་ནག།», the
     * animals three either side), here meeting the month with the day: in the mouse and horse months
     * the bird and hare days are black. The day is the date's animal; the month's animal counts from
     * the tiger, the 11th month.
     */
    fun blackDay(season: Int, dayAnimal: Animal): Boolean {
        val month = (TIGER.ordinal + season) % 12
        return dayAnimal.ordinal == (month + 3) % 12 || dayAnimal.ordinal == (month + 9) % 12
    }

    /**
     * The black hours (p. 236), "known together with the above": as the black days meet the month with
     * the date, they meet the date with the hour, «བྱི་རྟའི་ཉི་མ་ལ། །བྱ་ཡོས་གཉིས་ཀྱི་དུས་ཚོད་ནག»: on a day of the mouse or
     * the horse, the bird and hare hours are black. The day is the date's animal.
     */
    fun blackHour(dateAnimal: Animal, hourAnimal: Animal): Boolean =
        hourAnimal.ordinal == (dateAnimal.ordinal + 3) % 12 || hourAnimal.ordinal == (dateAnimal.ordinal + 9) % 12

    /**
     * The *klung rta* of each animal's triad, as WB names it (vol. 1, p. 254: «སྟག་རྟ་ཁྱི་གསུམ་ཀླུང་སྤྲེལ་ལྕགས། །ཕག་ལུག་ཡོས་གསུམ་ཀླུང་སྦྲུལ་མེ། །བྱི་འབྲུག་སྤྲེལ་གསུམ་ཀླུང་སྟག་ཤིང་། །བྱ་གླང་སྦྲུལ་གསུམ་ཀླུང་ཕག་ཆུ»):
     * the tiger, horse and dog's the iron monkey, the pig, sheep and hare's the fire snake, the mouse,
     * dragon and monkey's the wood tiger, the bird, ox and snake's the water pig.
     */
    fun klungRta(animal: Animal): Animal = when (animal) {
        TIGER, HORSE, DOG -> MONKEY
        PIG, SHEEP, RABBIT -> SNAKE
        MOUSE, DRAGON, MONKEY -> TIGER
        BIRD, OX, SNAKE -> PIG
    }

    /**
     * The hour's *bla mkhyen* (vol. 2, p. 235): on the *klung rta* of the hour's triad, as the year's on
     * the year's («དུས་ཚོད་བླ་མཁྱེན་དུས་ལོ་ཡི། །མཐུན་གསུམ་ཀླུང་རྟའི་སྟེང་ན་གནས»).
     */
    fun hourBlaMkhyen(hourAnimal: Animal): Animal = klungRta(hourAnimal)

    /**
     * The hour's *sa rgyal* (vol. 2, p. 236): on the four-slayer in front of the hour
     * («དུས་ཚོད་ས་རྒྱལ་མདུན་གྱི་ནི། །བཞི་གཤེད་སྟེང་དུ་གནས»), the one ahead in the hours' own course: WB counts an
     * animal's four-slayers up and down (vol. 1, p. 235: the mouse's upward one the hare, «ཡར་གྱི་བཞི་གཤེད་ཡོས་བུ», its
     * downward one the bird), and the one in front is the upward, three animals on, as the *'Bras rtsis rab
     * gsal nor bu'i me long* works it: «བྱི་དུས་ཡོས་ཐོག», in the mouse hour on the hare (Sa skya *gsung rab* vol. 7, p. 52).
     */
    fun hourSaRgyal(hourAnimal: Animal): Animal = Animal.entries[(hourAnimal.ordinal + 3) % 12]

    /**
     * The hidden earth lords (*gab pa'i sa bdag*, vol. 2, p. 221), one to each animal, which sit on that
     * animal's place in its year, month, day and hour («བྱི་བའི་ལོ་ཟླ་ཞག་དུས་ལ། །བྱི་བའི་སྟེང་ན་ས་བདག་ནི། །གཉན་ཁྲ་གནས»), the hour's
     * "as the month's" (p. 236): their Wylie names, by the animal.
     */
    val HIDDEN_LORDS: Map<Animal, String> = mapOf(
        MOUSE to "gnyan khra", OX to "gnyan ljang", TIGER to "bya khyung", RABBIT to "rus sbal ser po",
        DRAGON to "ba dan ser po", SNAKE to "tsang kun", HORSE to "byi lam", SHEEP to "be sna lag chen",
        MONKEY to "gzig mjug", BIRD to "he thon", DOG to "byi dur", PIG to "phyug po",
    )

    /**
     * The black sky dog of the hour (vol. 2, p. 236, «དུས་ཚོད་གནམ་ཁྱི་ལོར་བཤད»), as the year's by the new Chinese
     * reckoning (p. 197), which holds for year, month, day and hour alike: its head on the time's own
     * animal, its tail on the seventh, the twelve parts of its body in order clockwise («རིམ་པར་ཆོས་སྐོར»), on through
     * the animals, as a Gyalrong rtsis collection lays the old reckoning's out (head on the monkey, mouth on
     * the bird, «ནམ» on the dog …; BDRC MW1KG16672). The part
     * that lies on [place] in the hour of [hourAnimal], 1 the head … 12 the neck.
     */
    fun gnamKhyiPart(hourAnimal: Animal, place: Animal): Int = Math.floorMod(place.ordinal - hourAnimal.ordinal, 12) + 1

    private fun in3(map: Map<Set<Int>, Set<Int>>, season: Int, date: Int) = map.any { (m, d) -> season in m && date in d }

    /**
     * The line [day] fell on (SPEC §8.1, rule 4; ROADMAP X7): for a course with one date in each
     * season-month the season's three in turn, for one with its own dates this month's, for one by
     * the date's animal the month's or the season's animals, for the sky doors the same dates of
     * every month. Null for a [CourseDay] made without its date, for one whose date or animal is
     * not the course's, for the black months, whose line is their reading's (`reading.EarthLord.ZLA_NAG.<part>.falls`,
     * `KI_KANG_ZLA_NAG.<year>.falls`), and for the planets' and ogres' times, whose reading names Viṣṭi's dates.
     */
    fun fall(day: CourseDay): Fall? {
        val date = day.date ?: return null
        val s = day.season
        val event = day.event.takeIf { day.course in setOf(EarthLordCourse.GNYAN, EarthLordCourse.SDE_BRGYAD, EarthLordCourse.KLU) }
            ?.let { "CourseFall.event.${it.name}" }
        fun season(table: List<Set<Int>>, at: String? = null): Fall? {
            if (date !in table[s]) return null
            val first = s / 3 * 3
            return CourseFall(s, date, (first..first + 2).map { table[it].single() }, at, event)
        }
        fun month(dates: Set<Int>, at: String? = null): Fall? = MonthFall(s, date, dates, at, event).takeIf { date in dates }
        fun in3(map: Map<Set<Int>, Set<Int>>) = month(map.filterKeys { s in it }.values.flatten().toSet())
        fun animals(set: Set<Animal>, whole: Boolean, at: String? = null): Fall? =
            day.animal?.takeIf { it in set }?.let { AnimalFall(s, it, set, whole, at) }
        return when (day.course) {
            EarthLordCourse.NAG_CHUNG -> season(NAG_CHUNG)
            EarthLordCourse.PI_LING -> season(PI_LING, "CourseAt.hour.${PI_LING_HOURS[s].name}")
            EarthLordCourse.ZIN_PHUNG -> if (day.event == CourseEvent.SITS) {
                animals(ZIN_PHUNG_MIDDLE, true)
            } else {
                animals(ZIN_PHUNG_DAYS[s / 3], true, "CourseAt.${ZIN_PHUNG_TIMES[s]}")
            }
            EarthLordCourse.PHUNG_ZOR -> season(PHUNG_ZOR)
            EarthLordCourse.KI_KANG -> in3(KI_KANG)
            EarthLordCourse.HAL_KHYI -> season(HAL_KHYI, "CourseAt.hour.${HAL_KHYI_HOURS[s].name}")
            EarthLordCourse.GNAM_KHYI -> animals(GNAM_KHYI[s], false, "CourseAt.${GNAM_KHYI_TIMES[s]}")
            EarthLordCourse.GNAM_SBYOR -> season(GNAM_SBYOR)
            EarthLordCourse.GZA_RGOD -> in3(GZA_RGOD)
            EarthLordCourse.DBUL_PO -> month(DBUL_PO[s])
            EarthLordCourse.GZA_BDUN, EarthLordCourse.BAR_KHYI -> season(GZA_BDUN)
            EarthLordCourse.NGAM_SHING -> NGAM_SHING[s]?.let { month(setOf(it), "CourseAt.${NGAM_SHING_WAYS.getValue(s)}") }
            EarthLordCourse.KA_KHYUNG -> in3(KA_KHYUNG)
            EarthLordCourse.DRA_CHEN -> in3(DRA_CHEN)
            EarthLordCourse.SDE_BRGYAD -> month(if (day.event == CourseEvent.STRIKES) SDE_BRGYAD_STRIKES[s] else SDE_BRGYAD_TURNINGS[s])
            EarthLordCourse.SA_BDAG_BZLOG -> month(SA_BDAG_BZLOG[s])
            EarthLordCourse.SPUG_STON -> month(SPUG_STON[s].getValue(day.variant!!))
            EarthLordCourse.GNYAN -> when (day.event) {
                CourseEvent.MOVES -> season(GNYAN_MOVES)
                CourseEvent.STRIKES -> month(GNYAN_STRIKES[s])
                else -> month(GNYAN_TURNINGS[s])
            }
            EarthLordCourse.KLU -> month(if (day.event == CourseEvent.STRIKES) KLU_STRIKES[s] else KLU_TURNINGS[s])
            EarthLordCourse.CLASS_TIMES -> CLASS_TIMES[s % 3][day.variant]?.let { month(it) }
            EarthLordCourse.GNAM_SGO -> EveryMonthFall(date, (1..30).filter { (it - 1) % 10 == (date - 1) % 10 }.toSet())
            EarthLordCourse.ZHAG_NAG -> {
                val month = (TIGER.ordinal + s) % 12
                animals(setOf(Animal.entries[(month + 3) % 12], Animal.entries[(month + 9) % 12]), false)
            }
            EarthLordCourse.ZLA_NAG, EarthLordCourse.KI_KANG_ZLA_NAG, EarthLordCourse.NAG_CHEN -> null
        }
    }

    /**
     * The courses on [date] of Hor month [month], whose date's animal is [dayAnimal], in WB's order;
     * [chineseYear], the animal of the Chinese reckoning's year, for the black months and ki kang's. Only
     * WB's own course of each earth lord: what it reports after a course as "another way" (*lugs gcig*),
     * "one person's view" or what "some say" stays in the topic file and is not shown (the owner,
     * 2026-10-11). A course may do several things on one date (the *gnyan* move and strike, several
     * classes have their time): the page shows them in one entry ([zanshin.core.texts.Texts.earthLord]).
     */
    fun of(month: Int, date: Int, dayAnimal: Animal, chineseYear: Animal? = null): List<CourseDay> {
        val s = SeasonReckoning.CHINESE.season(month)
        return buildList {
            fun moves(course: EarthLordCourse, on: Boolean, variant: String? = null) {
                if (on) add(CourseDay(course, CourseEvent.MOVES, s, variant))
            }
            if (chineseYear != null) {
                val monthAnimal = TibetanCalendar.monthNames(month).animal
                val part = MonthEntries.blackPart(monthAnimal)
                moves(EarthLordCourse.ZLA_NAG, monthAnimal in MonthEntries.blackMonths(chineseYear) && date in part.dates, part.name)
                val kiKang = MonthEntries.KI_KANG.getValue(chineseYear)
                moves(EarthLordCourse.KI_KANG_ZLA_NAG, s in kiKang.seasons && date == kiKang.date, chineseYear.name)
            }
            fun strikes(course: EarthLordCourse, strikes: List<Set<Int>>, turnings: List<Set<Int>>) {
                if (date in strikes[s]) add(CourseDay(course, CourseEvent.STRIKES, s))
                if (date in turnings[s]) add(CourseDay(course, CourseEvent.TURNS_BACK, s))
            }
            moves(EarthLordCourse.NAG_CHUNG, date in NAG_CHUNG[s])
            moves(EarthLordCourse.PI_LING, date in PI_LING[s])
            moves(EarthLordCourse.ZIN_PHUNG, dayAnimal in ZIN_PHUNG_DAYS[s / 3], "${s / 3}")
            if (s / 3 == 0 && dayAnimal in ZIN_PHUNG_MIDDLE) add(CourseDay(EarthLordCourse.ZIN_PHUNG, CourseEvent.SITS, s, "middle"))
            moves(EarthLordCourse.PHUNG_ZOR, date in PHUNG_ZOR[s])
            moves(EarthLordCourse.KI_KANG, in3(KI_KANG, s, date))
            moves(EarthLordCourse.HAL_KHYI, date in HAL_KHYI[s])
            moves(EarthLordCourse.GNAM_KHYI, dayAnimal in GNAM_KHYI[s])
            moves(EarthLordCourse.GNAM_SBYOR, date in GNAM_SBYOR[s])
            moves(EarthLordCourse.GZA_RGOD, in3(GZA_RGOD, s, date))
            moves(EarthLordCourse.DBUL_PO, date in DBUL_PO[s])
            moves(EarthLordCourse.GZA_BDUN, date in GZA_BDUN[s])
            moves(EarthLordCourse.NGAM_SHING, NGAM_SHING[s] == date)
            moves(EarthLordCourse.BAR_KHYI, date in GZA_BDUN[s])
            moves(EarthLordCourse.KA_KHYUNG, in3(KA_KHYUNG, s, date))
            moves(EarthLordCourse.DRA_CHEN, in3(DRA_CHEN, s, date))
            strikes(EarthLordCourse.SDE_BRGYAD, SDE_BRGYAD_STRIKES, SDE_BRGYAD_TURNINGS)
            if (date in SA_BDAG_BZLOG[s]) add(CourseDay(EarthLordCourse.SA_BDAG_BZLOG, CourseEvent.TURNS_BACK, s))
            SPUG_STON[s].forEach { (kind, dates) -> moves(EarthLordCourse.SPUG_STON, date in dates, kind) }
            moves(EarthLordCourse.GNYAN, date in GNYAN_MOVES[s])
            strikes(EarthLordCourse.GNYAN, GNYAN_STRIKES, GNYAN_TURNINGS)
            strikes(EarthLordCourse.KLU, KLU_STRIKES, KLU_TURNINGS)
            CLASS_TIMES[s % 3].forEach { (cls, dates) -> moves(EarthLordCourse.CLASS_TIMES, date in dates, cls) }
            moves(EarthLordCourse.CLASS_TIMES, date in VISHTI_DATES, "gza")
            moves(EarthLordCourse.CLASS_TIMES, date in VISHTI_DATES, "srin po")
            add(CourseDay(EarthLordCourse.GNAM_SGO, CourseEvent.MOVES, s, skyDoor(date)))
            moves(EarthLordCourse.ZHAG_NAG, blackDay(s, dayAnimal))
        }.map { it.copy(date = date, animal = dayAnimal) }
    }

    /** The Chinese reckoning's year of [day]: the Tibetan year's animal, the next from the 11th month on. */
    fun chineseYear(day: TibetanDay): Animal = if (day.month >= 11) Animal.entries[(day.yearAnimal.ordinal + 1) % 12] else day.yearAnimal

    fun of(day: TibetanDay): List<CourseDay> = of(day.month, day.day, day.lunarDayAnimal, chineseYear(day))
}

/**
 * Rāhu's course by season-month among the earth lords (section 27, WB vol. 2, p. 232): the dates
 * it seeks food on, none in the last month of winter. It is Rāhu, so it stands with his other
 * courses and is weighed in his tier (SPEC §5.13).
 */
object RahuBySeason {
    val DATES: List<Set<Int>> = listOf(
        setOf(11, 28), setOf(2), setOf(18), setOf(16, 22), setOf(1, 13), setOf(2),
        setOf(28), setOf(16), setOf(1), setOf(2), setOf(18), emptySet(),
    )

    /** The season-month (0–11) whose date Rāhu seeks food on, [date] of Hor month [month], or null. */
    fun of(month: Int, date: Int): Int? = SeasonReckoning.CHINESE.season(month).takeIf { date in DATES[it] }
}
