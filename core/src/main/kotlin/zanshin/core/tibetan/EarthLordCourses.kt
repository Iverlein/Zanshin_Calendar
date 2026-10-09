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
 * time it is).
 */
data class CourseDay(val course: EarthLordCourse, val event: CourseEvent, val season: Int, val variant: String? = null) {
    /** A date of "another view" that WB reports after its own course, or of what "some say" ([EarthLordCourses.OTHER]). */
    val otherView: Boolean get() = variant == EarthLordCourses.OTHER || variant == EarthLordCourses.THIRD
}

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
    /** The variant of a date that only another view of a course gives, the second and the third. */
    const val OTHER = "other"
    const val THIRD = "third"

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

    /** *Zin phung* (section 14): the days it moves on in each season, and the spring days it sits in the middle. */
    val ZIN_PHUNG_DAYS: List<Set<Animal>> = listOf(setOf(TIGER, MONKEY), setOf(OX, SHEEP), setOf(DOG, DRAGON), setOf(PIG, SNAKE))
    val ZIN_PHUNG_MIDDLE: Set<Animal> = setOf(MOUSE, HORSE, BIRD, RABBIT)

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

    /** *Gnam khyi nag po* (section 18), its first course: the days of each season-month it seeks food on. */
    val GNAM_KHYI: List<Set<Animal>> = listOf(
        setOf(DRAGON, SHEEP), setOf(SNAKE, MONKEY), setOf(HORSE, BIRD),
        setOf(DOG, SHEEP), setOf(PIG, SNAKE), setOf(MOUSE, HORSE, MONKEY),
        setOf(DRAGON, SHEEP), setOf(TIGER, SHEEP, PIG), setOf(MOUSE, RABBIT, MONKEY),
        setOf(OX, TIGER, DRAGON), setOf(TIGER, DRAGON, DOG), setOf(PIG, RABBIT, MOUSE),
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
     * The other views WB reports after a course, "another way" (*lugs gcig*), "one person's view" or
     * what "some say" (docs/sources/earth-lord-courses.md): shown beside the course as such, by
     * season-month. *Dbul po*'s view differs from WB's own in four months only, the others "the same".
     */
    /** The great black day in one person's view (p. 226): the 24th of the last months of spring, autumn and winter, the 9th of the first of winter. */
    val NAG_CHEN_OTHER: Map<Int, Set<Int>> = mapOf(2 to setOf(24), 8 to setOf(24), 11 to setOf(24), 9 to setOf(9))
    val NAG_CHUNG_OTHER: List<Set<Int>> = List(9) { emptySet<Int>() } + threes(9).let { listOf(it[0], it[1], it[2]) }
    val PHUNG_ZOR_OTHER: List<Set<Int>> = bySeason(18, 8, 9, 3, 13, 9, 7, 17, 28, 1, 16, 9)
    val KI_KANG_OTHER: Map<Set<Int>, Set<Int>> = mapOf(
        months(TIGER, HORSE, DOG) to setOf(17, 28),
        months(PIG, SHEEP, RABBIT) to setOf(2, 24),
        months(MOUSE, DRAGON, MONKEY) to setOf(8, 28),
        months(BIRD, OX, SNAKE) to setOf(16, 26),
    )
    /** *Ki kang*'s third course, "a discordant view", given for two groups of months only. */
    val KI_KANG_THIRD: Map<Set<Int>, Set<Int>> = mapOf(months(TIGER, HORSE, DOG) to setOf(17, 27), months(PIG, SHEEP, RABBIT) to setOf(2, 22))
    val GNAM_KHYI_OTHER: List<Set<Animal>> = listOf(
        setOf(SHEEP), setOf(SNAKE, MONKEY), setOf(TIGER, BIRD),
        setOf(RABBIT, MONKEY), setOf(SHEEP, PIG), setOf(BIRD, MONKEY, HORSE),
        setOf(SNAKE, SHEEP), setOf(DRAGON, HORSE, PIG), setOf(RABBIT, MONKEY),
        setOf(DOG, OX, DRAGON, TIGER), setOf(OX, TIGER, DOG, SNAKE), setOf(TIGER, RABBIT, PIG, MOUSE),
    )
    val GZA_BDUN_OTHER: List<Set<Int>> = bySeason(28, 18, 8, 27, 17, 7, 21, 11, 1, 29, 19, 9)
    val DBUL_PO_OTHER: Map<Int, Set<Int>> = mapOf(0 to setOf(6, 16), 8 to setOf(9, 21), 9 to setOf(19), 10 to setOf(10))
    /** What "some say" of the eight classes' and the nāgas' strikes: dates the text adds to a month's own. */
    val SDE_BRGYAD_STRIKES_OTHER: Map<Int, Set<Int>> = mapOf(8 to (8..30).toSet() - setOf(8, 28, 30))
    val KLU_STRIKES_OTHER: Map<Int, Set<Int>> = mapOf(5 to setOf(15), 7 to setOf(5), 8 to setOf(23), 9 to setOf(15), 10 to setOf(15, 21))

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
     * The hour's *sa rgyal* the other way (vol. 2, p. 236, «ཡང་ནི་འདི་ལྟར་བཤད་ཀྱང་ཡོད»), the course of *pi ling
     * 'phar ma*: one hour and one place for each season-month of the Chinese reckoning, from the snake hour
     * on the upper south in the first of spring to the dragon hour on the bird's place in the last of
     * winter. The places are given as the animal whose place WB names (vol. 1, p. 254). The late summer's
     * dog hour reads «ཁྱི» in the Zhol print where the 1996 edition's scan is unclear.
     */
    val SA_RGYAL_OTHER: List<Pair<Animal, Animal>> = listOf(
        SNAKE to SNAKE, HORSE to MONKEY, SHEEP to PIG,
        MONKEY to SNAKE, BIRD to PIG, DOG to PIG,
        PIG to DRAGON, MOUSE to SHEEP, OX to DOG,
        TIGER to RABBIT, RABBIT to HORSE, DRAGON to BIRD,
    )

    /** The place of the hour's *sa rgyal* the other way in [season] (0 the first of spring), if [hourAnimal] is its hour. */
    fun hourSaRgyalOther(season: Int, hourAnimal: Animal): Animal? =
        SA_RGYAL_OTHER[season].takeIf { it.first == hourAnimal }?.second

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

    /**
     * The Paṇchen Mön'drowa's black days by year (p. 235), another view: the season-month and date
     * of each year's black day. The year is the Chinese reckoning's, which begins with the 11th month
     * (the model almanac, vol. 1, p. 154: «ནག་རྩིས་ལོ་འགོ … ཧོར་ཟླ་བཅུ་གཅིག་པ»).
     */
    val ZHAG_NAG_BY_YEAR: Map<Animal, Pair<Int, Int>> = mapOf(
        MOUSE to (1 to 7), OX to (2 to 5), TIGER to (3 to 7), RABBIT to (10 to 7), DRAGON to (11 to 26), SNAKE to (6 to 9),
        HORSE to (7 to 19), SHEEP to (8 to 7), MONKEY to (9 to 16), BIRD to (1 to 10), DOG to (5 to 21), PIG to (0 to 8),
    )

    private fun in3(map: Map<Set<Int>, Set<Int>>, season: Int, date: Int) = map.any { (m, d) -> season in m && date in d }

    /**
     * The courses on [date] of Hor month [month], whose date's animal is [dayAnimal], in WB's order;
     * [chineseYear], the animal of the Chinese reckoning's year, for the Paṇchen's black days.
     */
    fun of(month: Int, date: Int, dayAnimal: Animal, chineseYear: Animal? = null): List<CourseDay> {
        val s = SeasonReckoning.CHINESE.season(month)
        return buildList {
            fun moves(course: EarthLordCourse, on: Boolean, variant: String? = null) {
                if (on) add(CourseDay(course, CourseEvent.MOVES, s, variant))
            }
            fun strikes(course: EarthLordCourse, strikes: List<Set<Int>>, turnings: List<Set<Int>>) {
                if (date in strikes[s]) add(CourseDay(course, CourseEvent.STRIKES, s))
                if (date in turnings[s]) add(CourseDay(course, CourseEvent.TURNS_BACK, s))
            }
            moves(EarthLordCourse.NAG_CHEN, date in NAG_CHEN_OTHER[s].orEmpty(), OTHER)
            moves(EarthLordCourse.NAG_CHUNG, date in NAG_CHUNG[s])
            moves(EarthLordCourse.NAG_CHUNG, date in NAG_CHUNG_OTHER[s], OTHER)
            moves(EarthLordCourse.PI_LING, date in PI_LING[s])
            moves(EarthLordCourse.ZIN_PHUNG, dayAnimal in ZIN_PHUNG_DAYS[s / 3], "${s / 3}")
            if (s / 3 == 0 && dayAnimal in ZIN_PHUNG_MIDDLE) add(CourseDay(EarthLordCourse.ZIN_PHUNG, CourseEvent.SITS, s, "middle"))
            moves(EarthLordCourse.PHUNG_ZOR, date in PHUNG_ZOR[s])
            moves(EarthLordCourse.PHUNG_ZOR, date in PHUNG_ZOR_OTHER[s], OTHER)
            moves(EarthLordCourse.KI_KANG, in3(KI_KANG, s, date))
            moves(EarthLordCourse.KI_KANG, in3(KI_KANG_OTHER, s, date), OTHER)
            moves(EarthLordCourse.KI_KANG, in3(KI_KANG_THIRD, s, date), THIRD)
            moves(EarthLordCourse.HAL_KHYI, date in HAL_KHYI[s])
            moves(EarthLordCourse.GNAM_KHYI, dayAnimal in GNAM_KHYI[s])
            moves(EarthLordCourse.GNAM_KHYI, dayAnimal in GNAM_KHYI_OTHER[s], OTHER)
            moves(EarthLordCourse.GNAM_SBYOR, date in GNAM_SBYOR[s])
            moves(EarthLordCourse.GZA_RGOD, in3(GZA_RGOD, s, date))
            moves(EarthLordCourse.DBUL_PO, date in DBUL_PO[s])
            moves(EarthLordCourse.DBUL_PO, date in DBUL_PO_OTHER[s].orEmpty(), OTHER)
            moves(EarthLordCourse.GZA_BDUN, date in GZA_BDUN[s])
            moves(EarthLordCourse.GZA_BDUN, date in GZA_BDUN_OTHER[s], OTHER)
            moves(EarthLordCourse.NGAM_SHING, NGAM_SHING[s] == date)
            moves(EarthLordCourse.BAR_KHYI, date in GZA_BDUN[s])
            moves(EarthLordCourse.KA_KHYUNG, in3(KA_KHYUNG, s, date))
            moves(EarthLordCourse.DRA_CHEN, in3(DRA_CHEN, s, date))
            strikes(EarthLordCourse.SDE_BRGYAD, SDE_BRGYAD_STRIKES, SDE_BRGYAD_TURNINGS)
            if (date in SDE_BRGYAD_STRIKES_OTHER[s].orEmpty()) add(CourseDay(EarthLordCourse.SDE_BRGYAD, CourseEvent.STRIKES, s, OTHER))
            if (date in SA_BDAG_BZLOG[s]) add(CourseDay(EarthLordCourse.SA_BDAG_BZLOG, CourseEvent.TURNS_BACK, s))
            SPUG_STON[s].forEach { (kind, dates) -> moves(EarthLordCourse.SPUG_STON, date in dates, kind) }
            moves(EarthLordCourse.GNYAN, date in GNYAN_MOVES[s])
            strikes(EarthLordCourse.GNYAN, GNYAN_STRIKES, GNYAN_TURNINGS)
            strikes(EarthLordCourse.KLU, KLU_STRIKES, KLU_TURNINGS)
            if (date in KLU_STRIKES_OTHER[s].orEmpty()) add(CourseDay(EarthLordCourse.KLU, CourseEvent.STRIKES, s, OTHER))
            CLASS_TIMES[s % 3].forEach { (cls, dates) -> moves(EarthLordCourse.CLASS_TIMES, date in dates, cls) }
            moves(EarthLordCourse.CLASS_TIMES, date in VISHTI_DATES, "gza")
            moves(EarthLordCourse.CLASS_TIMES, date in VISHTI_DATES, "srin po")
            add(CourseDay(EarthLordCourse.GNAM_SGO, CourseEvent.MOVES, s, skyDoor(date)))
            moves(EarthLordCourse.ZHAG_NAG, blackDay(s, dayAnimal))
            moves(EarthLordCourse.ZHAG_NAG, chineseYear != null && ZHAG_NAG_BY_YEAR[chineseYear] == (s to date), OTHER)
        }
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
