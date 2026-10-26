/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import java.time.LocalDate
import zanshin.core.texts.gloss
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
import zanshin.core.tibetan.DepartureScope.DAY
import zanshin.core.tibetan.DepartureScope.HOUR
import zanshin.core.tibetan.DepartureScope.MONTH
import zanshin.core.tibetan.DepartureScope.YEAR
import zanshin.core.tibetan.DepartureTone.BAD
import zanshin.core.tibetan.DepartureTone.GOOD
import zanshin.core.tibetan.DepartureTone.MIDDLING
import zanshin.core.time.julianDayNumber
import zanshin.core.time.localDateOfJulianDayNumber

/*
 * The bride's departure, the reckoning for setting out on the road (ROADMAP P3; WB vol. 1, ch. 23, the second
 * examination, pp. 365–377, and the remedies, pp. 377–378): docs/sources/marriage.md quotes each rule as read on
 * the scan, "The departure" and "What the departure asks, in our own words". The sky dog of brides is read in
 * docs/sources/year-month-earth-lords.md, "The sky dog, male and female" (vol. 2, pp. 196–198).
 */

/**
 * The seventeen parts of the departure in the order of WB's body (p. 365's list puts the hour ninth; the body
 * treats the date's animal 8th, the planet 9th, the mansion 10th, the earth lords 11th, the hour 12th and the
 * direction 13th, marriage.md, *Its seventeen parts*).
 */
enum class DeparturePart {
    KHAM_YU, TWENTY_BRIDES, TRIGRAM, MEWA, COINCIDENCES, YEAR, MONTH, DATE, PLANET, MANSION, EARTH_LORDS, HOUR, DIRECTION,
    SKY_DOG, FIVE_PEOPLE, RECEIVING, PLACE;

    val english: String get() = gloss(this)
}

/** A finding's tone, as WB words it: good, middling, or bad (to be avoided, or bad for someone). */
enum class DepartureTone { GOOD, MIDDLING, BAD }

/**
 * What a finding changes with: the year (the bride's age, the year's sign; the Chinese reckoning's year for the
 * sky dog), the month, the day, the hour given, or the direction of travel given (or, with none given, the
 * directions an earth lord bars).
 */
enum class DepartureScope {
    YEAR, MONTH, DAY, HOUR, DIRECTION;

    val english: String get() = gloss(this)
}

/**
 * The *How it falls* line of a departure finding (SPEC §8.1, rule 4): the catalog's `DepartureFall.<rule>`, its
 * {0}, {1} … filled with [terms] in turn as an [HourFall]'s are (an [Animal] as a line names it, another engine
 * term by its name in running text, a list as "a, b and c", a [SeasonMonth] as the season-month's name, a number
 * as it stands); [at] the time of day where the source gives one (`CourseAt.*`).
 */
data class DepartureFall(val rule: String, val terms: List<Any>, override val at: String? = null, override val label: String? = null) : Fall

/** A season-month of the Chinese reckoning as a *How it falls* line names it, 0 the first month of spring (`CourseFall.month.<n>`). */
data class SeasonMonth(val season: Int)

/**
 * One finding of the departure: [part] the part of the seventeen it belongs to, [case] what holds within it,
 * [tone] as WB gives it, [reading] its catalog key (`reading.Departure.<PART>.<case>`, or an earth lord's own
 * reading), [remedy] the key of its remedy where the source gives one, [falls] its *How it falls* line. [weighed]
 * false for what stays out of the verdict: the earth lords (D4), the five people and receiving the bride, which
 * name people and not the day. [note] the key of what ch. 23 itself says of an earth lord it names.
 */
data class DepartureFinding(
    val part: DeparturePart,
    val case: String,
    val tone: DepartureTone,
    val scope: DepartureScope,
    val reading: String,
    val remedy: String?,
    val falls: Fall?,
    val weighed: Boolean = true,
    val note: String? = null,
)

/**
 * A spouse as the departure needs them: the Tibetan year of birth ([TibetanDay.year]), and where known the birth
 * date's Tibetan day [born] (the birth month, day animal, weekday and mansion) and the mother's birth year
 * [motherBirthYear] (the natal trigram, [KinReckoning.natalTrigram]).
 */
data class Spouse(val birthYear: Int, val born: TibetanDay? = null, val motherBirthYear: Int? = null) {
    init {
        require(born == null || born.year == birthYear) { "the birth date is in Tibetan year ${born?.year}, not $birthYear" }
    }

    val sign: Sign get() = YearOfLife.yearSign(birthYear)
    val forces: YearForces get() = sign.forces
    val natalTrigram: Trigram? get() = motherBirthYear?.let { KinReckoning.natalTrigram(it, birthYear) }

    companion object {
        /** A spouse born on [date]. */
        fun born(date: LocalDate, motherBirthYear: Int? = null): Spouse = TibetanCalendar.of(date).let { Spouse(it.year, it, motherBirthYear) }
    }
}

/** The couple: the groom, who takes the bride, and the bride, who goes. */
data class Couple(val groom: Spouse, val bride: Spouse)

/**
 * The departure reckoned for one day: [findings] in the order of [DeparturePart] and, within a part, of WB's
 * text; [noRemedy] the meeting p. 377 says cannot be remedied.
 */
data class DepartureDay(val date: LocalDate, val day: TibetanDay, val findings: List<DepartureFinding>, val noRemedy: Boolean) {
    /** The day's verdict over every weighed finding ([Departure.verdict]). */
    val verdict: DepartureTone get() = Departure.verdict(findings)

    /**
     * The verdict over the weighed findings that change within the year, the month's and the day's: what tells the
     * days of one month apart, while [yearFindings] stand over all of them.
     */
    val dayVerdict: DepartureTone get() = Departure.verdict(findings.filter { it.scope == MONTH || it.scope == DAY })

    /** The weighed findings that hold for the whole year, the same on every day of it. */
    val yearFindings: List<DepartureFinding> get() = findings.filter { it.weighed && it.scope == YEAR }
}

/**
 * The bride's departure (ROADMAP P3, block 30; WB vol. 1, ch. 23, pp. 365–378, docs/sources/marriage.md): for a
 * couple and a day of departure, optionally its hour and the direction of travel, every check of the seventeen
 * parts that the inputs make calculable, each as [DepartureFinding]s.
 *
 * **The verdict (a stated choice).** WB gives no weighing among the seventeen parts: each bad finding has its own
 * remedy (marriage.md, *Searched and not found*, "No weighing"). The app's rule: a day is BAD for the departure if any
 * weighed finding is BAD, GOOD if at least one is GOOD and none BAD, MIDDLING otherwise ([verdict]). The earth lords
 * (part 11) are reported, BAD where they bar a bride, and not weighed (D4, ROADMAP T2.26); nor are the five people
 * and receiving the bride, which name people. Most findings hold for the whole year (the bride's age, her trigram,
 * mewa and progressed sign, the year's sign), so over all findings nearly every year is bad for nearly every bride;
 * [DepartureDay.dayVerdict] gives the verdict of the month's and the day's findings alone, which tells the days of a
 * month apart, with [DepartureDay.yearFindings] shown over them. The meeting of Anurādhā, Tuesday and a fire year,
 * "the bride of a hundred slain deer", has no remedy (p. 377): [DepartureDay.noRemedy].
 *
 * **Other stated choices.** Ages are the Tibetan count ([YearOfLife.age]) in the departure's Tibetan year. The
 * trigram, mewa and progressed sign the bride "goes on" are those of her year of age ([YearOfLife.currentTrigram],
 * [YearOfLife.currentSmeBa], [YearOfLife.logMen], a woman's). *Kham yu*: Mipham's first count (vol. 2, p. 527),
 * the years of age from one's own year over the twelve year stations, own year the 1st; his sheep-born bride of 24
 * lands on the horse, so the bride's count runs forward through the animals and the groom's, "the other way",
 * backward. "Own", "mother", "son", "friend" and "enemy" trigrams are by the trigram's element ([YearOfLife.trigramElement])
 * against her life force, as p. 368's tiger and hare (mother *kham*, son *li*, own *zin*, enemy *dwa*); an aspect's
 * "enemy" trigram is the one whose element overcomes the aspect's. "Cuts" (nos. 10–17 of the twenty) is one aspect's
 * element overcoming the same aspect of the year's sign. The four firsts, middles and lasts («ར་བཞི», «འབྲིང་བཞི», «ཐ་ཆུང་བཞི»)
 * are the first, middle and last months of the seasons and the animals of those months (tiger, snake, monkey, pig
 * the firsts), as the date's mewa (WB vol. 1, p. 178) and ch. 31 (open question 31) use them; read so, p. 372's
 * good ages agree with its table of bad ages, read as the first of each triad they do not (the mouse would die at 18
 * and be good at 18). The executioner's and grave's days and hours are by the life force (the notes, vol. 2, p. 531:
 * "the executioner is the year that is the life force's enemy, the grave the year of entering the grave"); the four
 * executioners are the animals three either way, the upper the one counted up. The general mewa of a direction is
 * the year's square ([YearOfLife.smeBaAt] with the year's mewa in the middle); a direction's trigram is the square's
 * with the five-yellow in the middle (the notes, vol. 2, p. 524; [MatchReckoning.mewaTrigram]). An animal's place
 * is the direction WB gives its twelfth (tiger and hare east, dragon south-east …, `Animal.<A>.place`). The sky dog's
 * year is the Chinese reckoning's ([EarthLordCourses.chineseYear]) and its months that reckoning's seasons; the
 * coupling date goes by the year of departure, not the bride's birth year, since ch. 31 (vol. 2, p. 198) avoids
 * "any work" and a corpse's road at the coupling. The sky dog (part 14) is not weighed either: ch. 31 counts the
 * sky dog, male and female, among the year's earth lords (vol. 2, p. 174), which D4 keeps out of the weighing; its
 * findings are reported with their remedy, as the earth lords' are.
 *
 * **Left out**, needing what the app does not have or what the source does not fix: of the twenty brides, nos. 1–3
 * (the bride's mother's marriage year), no. 18's first way (the year the groom's father set up house), no. 19's first
 * way (the parents' birth mewa) and no. 9's direction where man and woman fight; the trigram's "other learned views"
 * and the four *sri* (p. 369); the coincidences' *rgya ma* (p. 370); the twelve bad *klung* (p. 371); the second
 * *kham yu* count, the mansions counted from Kṛttikā and Pūrvaphalgunī (p. 365); the planet's power and mother
 * planets (named, no tone); the mansions «ཤ་མ», «ཁྲག་སྐམ», wood's «རྒྱལ་སྨད» and earth's «བྱ་གཞུག» (not identified), and the
 * accomplishing and northern mansions (not listed); *chos gnyan*, Viṣṇu, the Rāhus' faces, *sribs lha*, the seven mewa,
 * the gold and turquoise treasuries and the bride's *ko long* (not built or not identified); the hour by the clan
 * element and by the hour's own element (the app has neither), the sky dog of the hour at dusk and its facings by the
 * times of day (no hour animal is named), its body by the old and new reckonings; the mewa's gods met with the mewa
 * by the general rule (only the nine meetings WB names are read); where she is placed (part 17, no reckoning).
 *
 * **The catalog keys** the texts need (each `reading.Departure.<PART>.<case>`; `.remedy` where marked R):
 * - KHAM_YU: `GROOM.<ANIMAL>`, `BRIDE.<ANIMAL>` (24, the stations of the two cycles, p. 365).
 * - TWENTY_BRIDES (pp. 365–368): `GREAT_OMEN` R, `MIDDLING_OMEN` R, `SMALL_OMEN` R, `ILL_SPEECH` R, `SORROW` R,
 *   `SCATTERED_WARES` R, `RAZOR` R, `LADLE` R, `FIREBRAND` R, `EARTH_SACK` R, `BROOM` R, `IRON_HOOK` R, `CROW` R,
 *   `MARKET_WOOD` R, `HUSBAND_DEVOURER` R, `BLACK_LINE` R, `SEPARATION` R.
 * - TRIGRAM (pp. 368–369): `MOTHER`, `SON`, `OWN`, `ENEMY.<FORCE>` R (four), `GROUP`, `SAME.<TRIGRAM>` R (eight), `LI_PIT`.
 * - MEWA (pp. 369–370): `1` … `9` (R for 1, 2, 3, 4, 5, 7, 9), `GOD.<DIRECTION>` (nine; R for EAST and NORTH),
 *   `MEETING.<DIRECTION>.<n>` (the nine meetings: SOUTH_WEST.1, CENTRE.2, EAST.3, CENTRE.4, SOUTH_EAST.6, SOUTH_EAST.7,
 *   WEST.8, NORTH_WEST.5, NORTH_EAST.9), `OBSTACLE.<SmeBaObstacle>` R (HOUSE, BED, LAND, ROYAL_GATE).
 * - COINCIDENCES (p. 370): `NINE_RED_LI` R, `LOG_MEN_LI` R.
 * - YEAR (pp. 370–372): `OWN_YEAR`, `SEVENTH`, `WHITE_YEAR` R, `NINE_TOMB`, `LOG_MEN_HARSH` R,
 *   `LOG_MEN.<LogMenPlace>` (R for all but GAIN), `SECTOR.<FORCE>` R, `FLOURISH.BRIDE_BODY`, `FLOURISH.GROOM_VITALITY`,
 *   `FLOURISH.GROOM_DESTINY`, `DIES` R, `NO_SON`, `OTHER_AGE`, `AGE_GOOD`, `AGE_MIDDLING`, `AGE.17` … `AGE.32` R.
 * - MONTH (pp. 372–373): `COUNT.1` … `COUNT.7` (R for all but 2), `BIRTH_MONTH` R, `BIRTH_SEVENTH` R, `GRAVE_MONTH` R,
 *   `RUIN_MONTH` R, `LINE_CUT_MONTH` R.
 * - DATE (p. 373): `FATHER_BROTHERS`, `MOTHER`, `BON_PO`, `SUBORDINATE`, `FEAR`, `HOUSE`, `IN_LAWS`, `DEAD`, `SKY_DOOR`,
 *   `SKY_DOOR_MONTH`, `OWN_DAY`, `SEVENTH_DAY`, `EXECUTIONER_DAY`, `GRAVE_DAY`, `SNAKE_DAY` R.
 * - PLANET (p. 373): `<WEEKDAY>` (seven; R `reading.Departure.PLANET.remedy` for the bad), `ENEMY_WEEKDAY`, `BIRTH_WEEKDAY`.
 * - MANSION (p. 373): `<MANSION>` for KRITTIKA, ANURADHA, REVATI, DHANISHTHA, MAGHA, ROHINI and the element's good ones
 *   (UTTARAPHALGUNI, SVATI, VISHAKHA, PURVAPHALGUNI, UTTARASHADHA, PURVABHADRAPADA, UTTARABHADRAPADA, under
 *   `GOOD.<MANSION>`), `BIRTH_MANSION`, `<CombinationDay>` for BDUD_NYI, CHI_SBYOR, MI_PHROD_NYI, MI_MTHUN_NYI, JIG_NYI,
 *   `BURNING`; R `reading.Departure.MANSION.remedy` (the bad mansions) and `reading.Departure.MANSION.COMBINATION.remedy`
 *   (the dog's head, for the combination days and the burning date).
 * - EARTH_LORDS (pp. 373–375): the lords' own readings (`reading.EarthLord.<COURSE>`, `reading.GreatBlackDay`,
 *   `reading.YearLord.<LORD>`, `reading.MonthLord.<LORD>`) and remedies (their `.remedy`, where the catalog has one),
 *   with a note each, `reading.Departure.EARTH_LORDS.<EarthLord|GreatBlackDay|YearLord|MonthLord>[.<NAME>]`, what ch. 23
 *   says of it: EarthLord.NAG_CHUNG, ZHAG_NAG, DBUL_PO, KI_KANG, PI_LING, ZIN_PHUNG, HAL_KHYI, GNAM_KHYI, ZLA_NAG,
 *   KI_KANG_ZLA_NAG; GreatBlackDay; YearLord.TESE, HANGPHEN, SEBYI, HAL_KHYI, KARSHAM, NB_BLACK_ONE, SME_1, SKY_DOG;
 *   MonthLord.HAL_KHYI, TENMA, FIVE_GODS, FOUR_QUEENS, SKY_DOG.
 * - HOUR (pp. 374–375; the notes, vol. 2, p. 531): `MOTHER`, `FRIEND`, `SEVENTH`, `FOUR_EXECUTIONER`, `GRAVE`.
 * - DIRECTION (pp. 374–375): `<HiddenWheel>` for GNAM_SMAN, SROG_TSHO, DPAL_BSKYED, DRE_LNGA, BDUD_GCOD, LUS_CHAD, GNOD_PA
 *   (R for the bad; DRE_LNGA, BDUD_GCOD and GNOD_PA by her trigram, `.remedy.<TRIGRAM>`, LUS_CHAD one general
 *   `.remedy`), `MEWA.<n>` (nine).
 * - SKY_DOG (pp. 375–376; vol. 2, pp. 196–198): `MALE` R, `FEMALE` R, `LI`, `LURKING`, `WATCH`, `FOOD_HOUR`, `YEAR`,
 *   `MONTH`, `DAY`, `BLACK_DAY`, `PLANET`, `COUPLING` (no remedy given), `FOOD` R; R `reading.Departure.SKY_DOG.remedy` for
 *   the others.
 * - FIVE_PEOPLE (p. 376): `LAMA`, `HORSE_LEADER`, `WELCOMER`, `ESCORT`, `SNA_BON`. RECEIVING (p. 376): `<TRIGRAM>` (eight:
 *   on *li* a horse, *khen* a sheep, *dwa* a goat, *khon* a dog, *kham* a pig, *gin* an ox, *zin* a donkey, *zon* a mule).
 * - Terms: `DeparturePart.<PART>`, `DepartureScope.<SCOPE>`; the *How it falls* lines `DepartureFall.<rule>` of [FALL_RULES];
 * one new time of day, `CourseAt.GREAT_WARMTH` («དྲོས་ཆེན», the sky dog's food in the middle autumn month).
 */
object Departure {
    /** The *How it falls* rules the engine names, each a catalog line `DepartureFall.<rule>`, with its terms in order. */
    val FALL_RULES: Map<String, String> = linkedMapOf(
        "KHAM_YU" to "own year's animal, age, the station's animal",
        "OWN_YEAR" to "the year's animal (her own)",
        "SEVENTH_YEAR" to "her animal, the year's animal",
        "NINE_EYE" to "age",
        "GRAVE_EYE" to "her trigram of age, her life force's element",
        "TRIGRAM_KIN" to "her trigram of age, its element, the aspect (Force), her aspect's element",
        "SORROW" to "the year's animal, her trigram of age",
        "YEAR_CUT" to "the aspect (Force), her element, the year's element",
        "FIRE_EIGHT_ZON" to "the year's element, the year's animal",
        "HUSBAND_TRIGRAM" to "her trigram of age (the groom's natal trigram)",
        "OWN_MEWA" to "her mewa",
        "SEPARATION" to "her trigram of age, the time (DepartureScope), the time's animal",
        "SEPARATION_LOG_MEN" to "her progressed sign's animal, the time (DepartureScope), the time's animal",
        "GROUP_TRIGRAM" to "her animal, her trigram of age",
        "TRIGRAM" to "her trigram of age",
        "MEWA" to "her mewa of the year, age",
        "GOD" to "the place (Direction), her birth mewa",
        "MEWA_GOD" to "the place (Direction), her mewa of the year",
        "SMALL_OBSTACLE" to "the obstacle (SmeBaObstacle), her mewa of the year",
        "NINE_RED_LI" to "her mewa of the year, her trigram of age",
        "LOG_MEN" to "her progressed sign's element, its animal",
        "WHITE_YEAR" to "her animal, the year's animal",
        "SECTOR" to "the aspect (Force), its element, the stage (Sector), the year's animal",
        "AGE" to "her animal, age",
        "MONTH_COUNT" to "her animal, the month's animal, the count",
        "MONTH_ANIMAL" to "her animal (or birth month's), the month's animal",
        "DATE" to "the date",
        "SKY_DOOR" to "the date, the month's animal",
        "DAY_ANIMAL" to "the day's animal, her own (year's or birth day's) animal",
        "WEEKDAY" to "the weekday",
        "ENEMY_WEEKDAY" to "the weekday, its element, her life force's element",
        "MANSION" to "the mansion",
        "ELEMENT_MANSION" to "the mansion, her life force's element",
        "COMBINATION" to "the combination day (CombinationDay), the weekday, the mansion",
        "BURNING" to "the weekday, the date",
        "TOWARD" to "the directions the lord bars",
        "HOUR_ANIMAL" to "the hour's animal, her animal",
        "HOUR_ELEMENT" to "the hour's animal, its life force's element, her life force's element",
        "DIRECTION_TRIGRAM" to "the direction, its trigram, her trigram of age",
        "DIRECTION_MEWA" to "the direction, its mewa, the year's mewa",
        "SKY_DOG_TRIGRAM" to "her trigram of age, age",
        "SKY_DOG_OWN" to "her animal, the last of her triad, the time (DepartureScope)",
        "SKY_DOG_TIME" to "the time (DepartureScope), its animal",
        "SKY_DOG_MONTH" to "the season-month (SeasonMonth)",
        "COUPLING" to "the year's animal, the season-month (SeasonMonth), the date",
        "SKY_DOG_FOOD" to "the season-month (SeasonMonth), its animals, the day's animal",
        "PEOPLE_LAMA" to "her triad's animals, the element that bears her life force",
        "PEOPLE_BEARER" to "the aspect (Force), the element that bears it",
        "PEOPLE_ESCORT" to "the elements that bear her life force, destiny and luck",
        "PEOPLE_SNA_BON" to "the element that overcomes her year's, her seventh animal",
        "RECEIVING" to "her trigram of age",
    )

    /** The verdict over [findings] (the stated choice, above): BAD if a weighed one is BAD, GOOD if one is GOOD and none BAD, MIDDLING otherwise. */
    fun verdict(findings: List<DepartureFinding>): DepartureTone {
        val weighed = findings.filter { it.weighed }
        return when {
            weighed.any { it.tone == BAD } -> BAD
            weighed.any { it.tone == GOOD } -> GOOD
            else -> MIDDLING
        }
    }

    /** The departure on [date] for [couple], at the hour of [hour] where given, travelling toward [direction] where given. */
    fun of(couple: Couple, date: LocalDate, hour: Animal? = null, direction: Direction? = null): DepartureDay =
        of(couple, TibetanCalendar.of(date), hour, direction)

    /** The departure on [day]. */
    fun of(couple: Couple, day: TibetanDay, hour: Animal? = null, direction: Direction? = null): DepartureDay {
        val date = localDateOfJulianDayNumber(day.jd)
        val c = Context(couple, day, date, hour, direction?.takeIf { it != Direction.CENTRE })
        if (c.age < 1) return DepartureDay(date, day, emptyList(), false)
        val findings = buildList {
            khamYu(c)
            twentyBrides(c)
            trigram(c)
            mewa(c)
            coincidences(c)
            year(c)
            month(c)
            date(c)
            planet(c)
            mansion(c)
            earthLords(c)
            hour(c)
            direction(c)
            skyDog(c)
            people(c)
        }
        return DepartureDay(date, day, findings, noRemedy(day))
    }

    /**
     * Each civil day from [from] to [to] (inclusive) for [couple], one Tibetan day each (a doubled date twice, a
     * skipped one not at all): the election's hook (SPEC §5.14, ROADMAP E2), so that "Choose a day" can show the
     * couple's departure beside the day's own side for the bride's works ([zanshin.core.texts.Election.BRIDE_WORKS]).
     */
    fun days(couple: Couple, from: LocalDate, to: LocalDate, hour: Animal? = null, direction: Direction? = null): List<DepartureDay> {
        val days = mutableListOf<DepartureDay>()
        var jd = from.julianDayNumber()
        val last = to.julianDayNumber()
        while (jd <= last) {
            days += of(couple, TibetanCalendar.of(jd), hour, direction)
            jd++
        }
        return days
    }

    /**
     * "Anurādhā, Tuesday and a fire year: the bride of a hundred slain deer; if these meet, they cannot be remedied"
     * (p. 377, «སྐར་མ་ལྷ་མཚམས་གཟའ་མིག་དམར། །གནམ་ལོ་མེ་ཤར»): the day's mansion, weekday and the Tibetan year's element.
     */
    fun noRemedy(day: TibetanDay): Boolean = day.mansion == Mansion.ANURADHA && day.weekday == Weekday.TUESDAY && day.yearElement == Element.FIRE

    // ---- The tables. ----

    /** The man's single cycle (p. 365): the station of each year animal and its verdict. */
    val GROOM_STATIONS: Map<Animal, DepartureTone> = mapOf(
        MOUSE to MIDDLING, OX to GOOD, TIGER to MIDDLING, RABBIT to MIDDLING, DRAGON to GOOD, SNAKE to BAD,
        HORSE to GOOD, SHEEP to BAD, MONKEY to GOOD, BIRD to MIDDLING, DOG to MIDDLING, PIG to GOOD,
    )

    /** The woman's hidden cycle (p. 365). */
    val BRIDE_STATIONS: Map<Animal, DepartureTone> = mapOf(
        MOUSE to BAD, OX to MIDDLING, TIGER to BAD, RABBIT to MIDDLING, DRAGON to BAD, SNAKE to GOOD,
        HORSE to BAD, SHEEP to MIDDLING, MONKEY to BAD, BIRD to GOOD, DOG to BAD, PIG to MIDDLING,
    )

    /**
     * The *kham yu* station of one born in a year of [own] in the year of age [age] (Mipham's count, vol. 2, p. 527):
     * the years from one's own year over the twelve year stations, own year the 1st; the bride forward through the
     * animals, the groom the other way.
     */
    fun khamYuStation(own: Animal, age: Int, groom: Boolean): Animal =
        Animal.entries[Math.floorMod(own.ordinal + if (groom) -(age - 1) else age - 1, 12)]

    /** The bride's bad ages by her year animal (p. 371): she dies, no son, other bad ages. */
    val BAD_AGES: Map<Animal, Triple<Set<Int>, Set<Int>, Set<Int>>> = mapOf(
        MOUSE to Triple(setOf(18, 27), setOf(15), setOf(26)),
        OX to Triple(setOf(16, 18), setOf(14), emptySet()),
        TIGER to Triple(setOf(17, 22), setOf(11), setOf(26, 36)),
        RABBIT to Triple(setOf(12, 16), setOf(13), setOf(29, 30)),
        DRAGON to Triple(setOf(17, 19), setOf(11), setOf(22, 28)),
        SNAKE to Triple(setOf(12, 24), setOf(20), setOf(28)),
        HORSE to Triple(setOf(17, 29), setOf(15), setOf(22)),
        SHEEP to Triple(setOf(14, 16), setOf(12), setOf(22, 26, 36)),
        MONKEY to Triple(setOf(15, 21), setOf(16), setOf(14, 25, 22)),
        BIRD to Triple(setOf(14, 22), setOf(17), setOf(12, 13)),
        DOG to Triple(setOf(17, 21), setOf(20), emptySet()),
        PIG to Triple(setOf(13, 23), setOf(16), setOf(14, 32)),
    )

    /** The good and middling ages of p. 372 by the bride's group of four (0 the firsts, 1 the middles, 2 the lasts). */
    val GROUP_AGES: List<Pair<Set<Int>, Set<Int>>> = listOf(setOf(18) to setOf(27), emptySet<Int>() to setOf(25, 31), setOf(24) to setOf(15))

    /** The group of four of an animal, or of the month of that animal: 0 the firsts (tiger, snake, monkey, pig), 1 the middles, 2 the lasts. */
    fun group(a: Animal): Int = Math.floorMod(a.ordinal - TIGER.ordinal, 3)

    /** The Chinese white years, *lo dkar ha sha* (p. 355): the pairs of animals; the departure's white year is the bride's pair. */
    val WHITE_YEARS: List<Pair<Animal, Animal>> = listOf(MOUSE to RABBIT, OX to TIGER, DRAGON to SNAKE, PIG to DOG, HORSE to BIRD, MONKEY to SHEEP)

    /** The white year of a bride born in a year of [a]. */
    fun whiteYear(a: Animal): Animal = WHITE_YEARS.first { it.first == a || it.second == a }.let { if (it.first == a) it.second else it.first }

    /** The triads' grave months (p. 372): pig, sheep and hare on the sheep month, and so on. */
    fun graveMonth(a: Animal): Animal = when (a) {
        PIG, SHEEP, RABBIT -> SHEEP
        TIGER, HORSE, DOG -> DOG
        BIRD, OX, SNAKE -> OX
        MOUSE, DRAGON, MONKEY -> DRAGON
    }

    /** The triad of [a], in WB's order (tiger, horse, dog; pig, sheep, hare; mouse, dragon, monkey; bird, ox, snake). */
    fun triad(a: Animal): List<Animal> = when (a) {
        TIGER, HORSE, DOG -> listOf(TIGER, HORSE, DOG)
        PIG, SHEEP, RABBIT -> listOf(PIG, SHEEP, RABBIT)
        MOUSE, DRAGON, MONKEY -> listOf(MOUSE, DRAGON, MONKEY)
        BIRD, OX, SNAKE -> listOf(BIRD, OX, SNAKE)
    }

    /** The dates of p. 373 and whom each harms. */
    val BAD_DATES: Map<Int, String> = mapOf(
        1 to "FATHER_BROTHERS", 3 to "MOTHER", 4 to "MOTHER", 5 to "BON_PO", 6 to "BON_PO", 8 to "SUBORDINATE", 9 to "SUBORDINATE",
        18 to "FEAR", 19 to "FEAR", 21 to "HOUSE", 22 to "HOUSE", 28 to "IN_LAWS", 30 to "DEAD",
    )

    /** The bride's sky door of a month of the group [g] (p. 373): the 27th in the firsts, the 17th in the middles, the 7th in the lasts. */
    fun skyDoorDate(g: Int): Int = listOf(27, 17, 7)[g]

    /** The planets of p. 373. */
    val WEEKDAYS: Map<Weekday, DepartureTone> = mapOf(
        Weekday.SATURDAY to BAD, Weekday.WEDNESDAY to BAD, Weekday.MONDAY to MIDDLING, Weekday.FRIDAY to GOOD,
        Weekday.THURSDAY to GOOD, Weekday.SUNDAY to BAD, Weekday.TUESDAY to BAD,
    )

    /** The mansions bad to send on (p. 373). */
    val BAD_MANSIONS: Set<Mansion> = setOf(Mansion.KRITTIKA, Mansion.ANURADHA, Mansion.REVATI, Mansion.DHANISHTHA, Mansion.MAGHA, Mansion.ROHINI)

    /** The mansions good by the bride's element (p. 373), as far as they are identified. */
    val ELEMENT_MANSIONS: Map<Element, Set<Mansion>> = mapOf(
        Element.FIRE to setOf(Mansion.UTTARAPHALGUNI, Mansion.SVATI),
        Element.EARTH to setOf(Mansion.VISHAKHA),
        Element.IRON to setOf(Mansion.VISHAKHA, Mansion.PURVAPHALGUNI, Mansion.UTTARASHADHA),
        Element.WATER to setOf(Mansion.PURVABHADRAPADA, Mansion.UTTARABHADRAPADA),
    )

    /** The combination days of p. 373: the demon day and *'chi*, *mi 'phrod*, *mi mthun*, *'jig*. */
    val BAD_COMBINATIONS: List<CombinationDay> =
        listOf(CombinationDay.BDUD_NYI, CombinationDay.CHI_SBYOR, CombinationDay.MI_PHROD_NYI, CombinationDay.MI_MTHUN_NYI, CombinationDay.JIG_NYI)

    /** The date's earth lords the departure names (p. 373, and the hal dog and the day's sky dog, pp. 375–376). */
    val DATE_LORDS: List<EarthLordCourse> = listOf(
        EarthLordCourse.NAG_CHUNG, EarthLordCourse.ZHAG_NAG, EarthLordCourse.DBUL_PO, EarthLordCourse.KI_KANG, EarthLordCourse.PI_LING,
        EarthLordCourse.ZIN_PHUNG, EarthLordCourse.HAL_KHYI, EarthLordCourse.GNAM_KHYI, EarthLordCourse.ZLA_NAG, EarthLordCourse.KI_KANG_ZLA_NAG,
    )

    /** The year's earth lords the departure names (pp. 374–375; the sky dog, p. 375). */
    val YEAR_LORDS: List<YearLord> = listOf(
        YearLord.TESE, YearLord.HANGPHEN, YearLord.SEBYI, YearLord.HAL_KHYI, YearLord.KARSHAM, YearLord.NB_BLACK_ONE, YearLord.SME_1, YearLord.SKY_DOG,
    )

    /** The month's earth lords the departure names (pp. 374–375). */
    val MONTH_LORDS: List<MonthLord> = listOf(MonthLord.HAL_KHYI, MonthLord.TENMA, MonthLord.FIVE_GODS, MonthLord.FOUR_QUEENS, MonthLord.SKY_DOG)

    /** The direction of an animal's twelfth (WB vol. 1, p. 254, `Animal.<A>.place`). */
    fun direction(a: Animal): Direction = when (a) {
        TIGER, RABBIT -> Direction.EAST
        DRAGON -> Direction.SOUTH_EAST
        SNAKE, HORSE -> Direction.SOUTH
        SHEEP -> Direction.SOUTH_WEST
        MONKEY, BIRD -> Direction.WEST
        DOG -> Direction.NORTH_WEST
        PIG, MOUSE -> Direction.NORTH
        OX -> Direction.NORTH_EAST
    }

    /** The trigram of a direction: the square's with the five-yellow in the middle (the notes, vol. 2, p. 524). */
    fun directionTrigram(d: Direction): Trigram = MatchReckoning.mewaTrigram(YearOfLife.smeBaAt(d, 5), man = true)

    /** The tone of Barma's god of each place (p. 370). */
    val GODS: Map<Direction, DepartureTone> = mapOf(
        Direction.CENTRE to BAD, Direction.EAST to BAD, Direction.SOUTH to BAD, Direction.WEST to GOOD, Direction.NORTH to BAD,
        Direction.NORTH_EAST to GOOD, Direction.SOUTH_EAST to GOOD, Direction.SOUTH_WEST to BAD, Direction.NORTH_WEST to GOOD,
    )

    /** The nine meetings of a god with a mewa that p. 370 reads (the god's place, the mewa) and their tones. */
    val MEETINGS: Map<Pair<Direction, Int>, DepartureTone> = mapOf(
        (Direction.SOUTH_WEST to 1) to BAD, (Direction.CENTRE to 2) to BAD, (Direction.EAST to 3) to BAD, (Direction.CENTRE to 4) to BAD,
        (Direction.SOUTH_EAST to 6) to GOOD, (Direction.SOUTH_EAST to 7) to MIDDLING, (Direction.WEST to 8) to GOOD,
        (Direction.NORTH_WEST to 5) to MIDDLING, (Direction.NORTH_EAST to 9) to GOOD,
    )

    /** The mewa sent on (p. 369): the three whites and nine-red good in general, but the one-white and nine-red each with a harm. */
    fun mewaTone(m: Int): DepartureTone = when (m) {
        6, 8 -> GOOD
        1, 9 -> MIDDLING
        else -> BAD
    }

    /** The general mewa of a direction (p. 375): six and eight good, five, three and seven middling, the rest bad. */
    fun directionMewaTone(m: Int): DepartureTone = when (m) {
        6, 8 -> GOOD
        3, 5, 7 -> MIDDLING
        else -> BAD
    }

    /** The coupling of the sky dogs by the year's animal (p. 376): the season-month and the date. */
    val COUPLING: Map<Animal, Pair<Int, Int>> = mapOf(
        MOUSE to (1 to 1), OX to (2 to 4), TIGER to (3 to 7), RABBIT to (4 to 9), DRAGON to (5 to 26), SNAKE to (6 to 19),
        HORSE to (7 to 9), SHEEP to (8 to 7), MONKEY to (9 to 16), BIRD to (10 to 19), DOG to (11 to 24), PIG to (0 to 8),
    )

    /** The sky dog's search for food (p. 376) by season-month: its days, and its time of day (`CourseAt.*`) where read. */
    val FOOD: List<Pair<Set<Animal>, String?>> = listOf(
        setOf(DRAGON, SHEEP) to "DAWN",
        setOf(MONKEY, SNAKE) to "MIDNIGHT",
        setOf(TIGER) to "DAWN",
        setOf(SHEEP, MONKEY, RABBIT) to "DAYBREAK",
        setOf(SHEEP, DRAGON, PIG) to "SUNRISE",
        setOf(BIRD, MONKEY, HORSE) to "SUNRISE",
        setOf(SNAKE, HORSE, SHEEP) to "MORNING_WARMTH",
        setOf(SHEEP, PIG, DRAGON) to "GREAT_WARMTH",
        setOf(MOUSE, RABBIT, MONKEY) to "NOON",
        setOf(OX, TIGER, DRAGON) to null,
        setOf(OX, SNAKE, TIGER) to "SUNSET",
        setOf(TIGER, RABBIT, MOUSE, PIG) to "DUSK",
    )


    // ---- The parts. ----

    private class Context(val couple: Couple, val day: TibetanDay, val date: LocalDate, val hour: Animal?, val direction: Direction?) {
        val bride = couple.bride
        val groom = couple.groom
        val her: YearForces = bride.forces
        val animal: Animal = bride.sign.animal
        val present: Sign = YearOfLife.yearSign(day.year)
        val r = YearReckoning(bride.sign, bride.birthYear, present, day.year, Gender.FEMALE, null)
        val g = YearReckoning(groom.sign, groom.birthYear, present, day.year, Gender.MALE, null)
        val age: Int = r.age
        val trigram: Trigram = r.trigram!!
        val mewa: Int = r.currentSmeBa
        val logMen: Sign = r.logMen!!
        val monthAnimal: Animal = day.monthNames.animal
        val dayAnimal: Animal = day.lunarDayAnimal
        val season: Int = SeasonReckoning.CHINESE.season(day.month)
        val chineseYear: Animal = EarthLordCourses.chineseYear(day)
    }

    private fun MutableList<DepartureFinding>.add(
        part: DeparturePart, case: String, tone: DepartureTone, scope: DepartureScope, rule: String, terms: List<Any>,
        remedy: Boolean = false, remedyKey: String? = null, at: String? = null, weighed: Boolean = true,
    ) {
        val reading = "reading.Departure.${part.name}.$case"
        add(DepartureFinding(part, case, tone, scope, reading, remedyKey ?: if (remedy) "$reading.remedy" else null, DepartureFall(rule, terms, at), weighed && part != DeparturePart.SKY_DOG))
    }

    /** The life force's element of an animal: its direction's (tiger and hare wood …). */
    private fun life(a: Animal): Element = Forces.of(Element.WOOD, a).vitality

    private fun step(from: Animal, to: Animal): Int = Math.floorMod(to.ordinal - from.ordinal, 12)

    private fun plus(a: Animal, n: Int): Animal = Animal.entries[Math.floorMod(a.ordinal + n, 12)]

    /** Part 1, *kham yu* (p. 365): each spouse's station by age. */
    private fun MutableList<DepartureFinding>.khamYu(c: Context) {
        val groomAge = YearOfLife.age(c.groom.birthYear, c.day.year)
        if (groomAge >= 1) {
            val s = khamYuStation(c.groom.sign.animal, groomAge, groom = true)
            add(DeparturePart.KHAM_YU, "GROOM.${s.name}", GROOM_STATIONS.getValue(s), YEAR, "KHAM_YU", listOf(c.groom.sign.animal, groomAge, s))
        }
        val s = khamYuStation(c.animal, c.age, groom = false)
        add(DeparturePart.KHAM_YU, "BRIDE.${s.name}", BRIDE_STATIONS.getValue(s), YEAR, "KHAM_YU", listOf(c.animal, c.age, s))
    }

    /** Part 2, the twenty brides of misfortune (pp. 365–368), those the births, ages and the day make calculable. */
    private fun MutableList<DepartureFinding>.twentyBrides(c: Context) {
        val p = DeparturePart.TWENTY_BRIDES
        val year = c.present
        val te = YearOfLife.trigramElement(c.trigram)
        // 4, 5: her own obstacle, the seven-corner.
        if (year.animal == c.animal) add(p, "GREAT_OMEN", BAD, YEAR, "OWN_YEAR", listOf(year.animal), remedy = true)
        if (step(c.animal, year.animal) == 6) add(p, "MIDDLING_OMEN", BAD, YEAR, "SEVENTH_YEAR", listOf(c.animal, year.animal), remedy = true)
        // 6: the nine-eye or the grave-eye.
        if (c.r.nineMultiple) add(p, "SMALL_OMEN", BAD, YEAR, "NINE_EYE", listOf(c.age), remedy = true)
        if (c.trigram == YearOfLife.tombTrigram(c.her.vitality)) add(p, "SMALL_OMEN", BAD, YEAR, "GRAVE_EYE", listOf(c.trigram, c.her.vitality), remedy = true)
        // 7, 8, 9: going on her enemy, own or friend trigram.
        val kin = Forces.kinship(c.her.vitality, te)
        if (kin == Kinship.ENEMY) add(p, "ILL_SPEECH", BAD, YEAR, "TRIGRAM_KIN", listOf(c.trigram, te, Force.VITALITY, c.her.vitality), remedy = true)
        if (kin == Kinship.IDENTITY && year.animal in triad(c.animal)) add(p, "SORROW", BAD, YEAR, "SORROW", listOf(year.animal, c.trigram), remedy = true)
        if (kin == Kinship.FRIEND) add(p, "SCATTERED_WARES", BAD, YEAR, "TRIGRAM_KIN", listOf(c.trigram, te, Force.VITALITY, c.her.vitality), remedy = true)
        // 10–17: her aspects and the year's cutting each other.
        val y = year.forces
        fun cut(force: Force, hers: String, years: String) {
            when (Forces.kinship(c.her[force], y[force])) {
                Kinship.FRIEND -> add(p, hers, BAD, YEAR, "YEAR_CUT", listOf(force, c.her[force], y[force]), remedy = true)
                Kinship.ENEMY -> add(p, years, BAD, YEAR, "YEAR_CUT", listOf(force, c.her[force], y[force]), remedy = true)
                else -> {}
            }
        }
        cut(Force.VITALITY, "RAZOR", "LADLE")
        cut(Force.BODY, "FIREBRAND", "EARTH_SACK")
        cut(Force.DESTINY, "IRON_HOOK", "BROOM")
        cut(Force.LUCK, "CROW", "MARKET_WOOD")
        // 18: her progressed trigram on the husband's birth trigram, or zon in a fire-eight year.
        if (c.groom.natalTrigram == c.trigram) add(p, "HUSBAND_DEVOURER", BAD, YEAR, "HUSBAND_TRIGRAM", listOf(c.trigram), remedy = true)
        if (c.trigram == Trigram.ZON && year.element == Element.FIRE && year.animal.ordinal in TIGER.ordinal..BIRD.ordinal) {
            add(p, "HUSBAND_DEVOURER", BAD, YEAR, "FIRE_EIGHT_ZON", listOf(year.element, year.animal), remedy = true)
        }
        // 19: sent on her own birth mewa.
        if (c.mewa == c.r.natalSmeBa) add(p, "BLACK_LINE", BAD, YEAR, "OWN_MEWA", listOf(c.mewa), remedy = true)
        // 20: li or kham, or the progressed sign bird or monkey, with a year, month, date or hour of bird or monkey.
        val sign: Pair<String, Any>? = when {
            c.trigram == Trigram.LI || c.trigram == Trigram.KHAM -> "SEPARATION" to c.trigram
            c.logMen.animal == BIRD || c.logMen.animal == MONKEY -> "SEPARATION_LOG_MEN" to c.logMen.animal
            else -> null
        }
        if (sign != null) {
            val time = listOfNotNull(
                year.animal.let { YEAR to it }, MONTH to c.monthAnimal, DAY to c.dayAnimal, c.hour?.let { HOUR to it },
            ).firstOrNull { it.second == BIRD || it.second == MONKEY }
            if (time != null) add(p, "SEPARATION", BAD, time.first, sign.first, listOf(sign.second, time.first, time.second), remedy = true)
        }
    }

    /** Part 3, the trigram (pp. 368–369): the trigram of her year of age against her aspects. */
    private fun MutableList<DepartureFinding>.trigram(c: Context) {
        val p = DeparturePart.TRIGRAM
        val te = YearOfLife.trigramElement(c.trigram)
        when (Forces.kinship(c.her.vitality, te)) {
            Kinship.MOTHER -> add(p, "MOTHER", GOOD, YEAR, "TRIGRAM_KIN", listOf(c.trigram, te, Force.VITALITY, c.her.vitality))
            Kinship.SON -> add(p, "SON", GOOD, YEAR, "TRIGRAM_KIN", listOf(c.trigram, te, Force.VITALITY, c.her.vitality))
            Kinship.IDENTITY -> add(p, "OWN", GOOD, YEAR, "TRIGRAM_KIN", listOf(c.trigram, te, Force.VITALITY, c.her.vitality))
            else -> {}
        }
        for (f in MatchReckoning.ASPECTS) {
            if (Forces.kinship(c.her[f], te) == Kinship.ENEMY) add(p, "ENEMY.${f.name}", BAD, YEAR, "TRIGRAM_KIN", listOf(c.trigram, te, f, c.her[f]), remedy = true)
        }
        val group = when (c.animal) {
            OX, TIGER, RABBIT -> Trigram.GIN
            DRAGON, SNAKE, HORSE -> Trigram.ZON
            SHEEP, MONKEY, BIRD -> Trigram.KHON
            PIG, MOUSE -> Trigram.KHEN
            DOG -> null
        }
        if (group == c.trigram) add(p, "GROUP", BAD, YEAR, "GROUP_TRIGRAM", listOf(c.animal, c.trigram))
        if (c.bride.natalTrigram == c.trigram) add(p, "SAME.${c.trigram.name}", BAD, YEAR, "TRIGRAM", listOf(c.trigram), remedy = true)
        if (c.trigram == Trigram.LI) add(p, "LI_PIT", BAD, YEAR, "TRIGRAM", listOf(c.trigram))
    }

    /** Part 4, the mewa (pp. 369–370): the mewa of her year of age, Barma's god of its place, and their meeting. */
    private fun MutableList<DepartureFinding>.mewa(c: Context) {
        val p = DeparturePart.MEWA
        val tone = mewaTone(c.mewa)
        add(p, "${c.mewa}", tone, YEAR, "MEWA", listOf(c.mewa, c.age), remedy = c.mewa != 6 && c.mewa != 8)
        val place = Direction.entries.first { YearOfLife.smeBaAt(it, c.r.natalSmeBa) == c.mewa }
        add(p, "GOD.${place.name}", GODS.getValue(place), YEAR, "GOD", listOf(place, c.r.natalSmeBa), remedy = place == Direction.EAST || place == Direction.NORTH)
        MEETINGS[place to c.mewa]?.let { add(p, "MEETING.${place.name}.${c.mewa}", it, YEAR, "MEWA_GOD", listOf(place, c.mewa)) }
        if (tone == BAD) {
            for (o in c.r.smeBaObstacles.filter { it in setOf(SmeBaObstacle.HOUSE, SmeBaObstacle.BED, SmeBaObstacle.LAND, SmeBaObstacle.ROYAL_GATE) }) {
                add(p, "OBSTACLE.${o.name}", BAD, YEAR, "SMALL_OBSTACLE", listOf(o, c.mewa), remedy = true)
            }
        }
    }

    /** Part 5, the coincidences (p. 370). */
    private fun MutableList<DepartureFinding>.coincidences(c: Context) {
        val p = DeparturePart.COINCIDENCES
        if (c.mewa == 9 && c.trigram == Trigram.LI) add(p, "NINE_RED_LI", BAD, YEAR, "NINE_RED_LI", listOf(c.mewa, c.trigram), remedy = true)
        if ((c.logMen.animal == BIRD || c.logMen.animal == MONKEY) && c.trigram == Trigram.LI) {
            add(p, "LOG_MEN_LI", BAD, YEAR, "LOG_MEN", listOf(c.logMen.element, c.logMen.animal), remedy = true)
        }
    }

    /** Part 6, the year (pp. 370–372). */
    private fun MutableList<DepartureFinding>.year(c: Context) {
        val p = DeparturePart.YEAR
        val year = c.present
        if (year.animal == c.animal) add(p, "OWN_YEAR", BAD, YEAR, "OWN_YEAR", listOf(year.animal))
        if (step(c.animal, year.animal) == 6) add(p, "SEVENTH", BAD, YEAR, "SEVENTH_YEAR", listOf(c.animal, year.animal))
        if (year.animal == whiteYear(c.animal)) add(p, "WHITE_YEAR", BAD, YEAR, "WHITE_YEAR", listOf(c.animal, year.animal), remedy = true)
        if (YearOfLife.nineMeetsTomb(c.animal, Gender.FEMALE, c.age)) add(p, "NINE_TOMB", BAD, YEAR, "NINE_EYE", listOf(c.age))
        // The progressed sign on iron's four and fire's eight, on the doors and the ruins (p. 371).
        val lm = c.logMen
        val harsh = lm.element == Element.IRON && lm.animal in setOf(DOG, PIG, MOUSE, OX) ||
            lm.element == Element.FIRE && lm.animal.ordinal in TIGER.ordinal..BIRD.ordinal
        if (harsh) add(p, "LOG_MEN_HARSH", BAD, YEAR, "LOG_MEN", listOf(lm.element, lm.animal), remedy = true)
        c.r.logMenPlace?.let { place ->
            val tone = if (place == LogMenPlace.GAIN) GOOD else BAD
            add(p, "LOG_MEN.${place.name}", tone, YEAR, "LOG_MEN", listOf(lm.element, lm.animal), remedy = tone == BAD)
        }
        // Life, body, power and wind-horse on illness, death or the grave; the man's life and power and the woman's body flourishing.
        for (s in c.r.sectors) {
            if (s.sector == Sector.ILLNESS || s.sector == Sector.DEATH || s.sector == Sector.ENTERING_THE_TOMB) {
                add(p, "SECTOR.${s.force.name}", BAD, YEAR, "SECTOR", listOf(s.force, s.element, s.sector, year.animal), remedy = true)
            }
        }
        c.r.sectors.first { it.force == Force.BODY }.takeIf { it.sector.good }?.let {
            add(p, "FLOURISH.BRIDE_BODY", GOOD, YEAR, "SECTOR", listOf(it.force, it.element, it.sector, year.animal))
        }
        if (YearOfLife.age(c.groom.birthYear, c.day.year) >= 1) {
            for (f in listOf(Force.VITALITY, Force.DESTINY)) {
                c.g.sectors.first { it.force == f }.takeIf { it.sector.good }?.let {
                    add(p, "FLOURISH.GROOM_${f.name}", GOOD, YEAR, "SECTOR", listOf(it.force, it.element, it.sector, year.animal))
                }
            }
        }
        // Her bad ages by her year (p. 371), the good ages of her group (p. 372), and the ages 17 to 32 by number.
        val (dies, noSon, other) = BAD_AGES.getValue(c.animal)
        if (c.age in dies) add(p, "DIES", BAD, YEAR, "AGE", listOf(c.animal, c.age), remedy = true)
        if (c.age in noSon) add(p, "NO_SON", BAD, YEAR, "AGE", listOf(c.animal, c.age))
        if (c.age in other) add(p, "OTHER_AGE", BAD, YEAR, "AGE", listOf(c.animal, c.age))
        val (good, middling) = GROUP_AGES[group(c.animal)]
        if (c.age in good) add(p, "AGE_GOOD", GOOD, YEAR, "AGE", listOf(c.animal, c.age))
        if (c.age in middling) add(p, "AGE_MIDDLING", MIDDLING, YEAR, "AGE", listOf(c.animal, c.age))
        if (c.age in 17..32) add(p, "AGE.${c.age}", BAD, YEAR, "AGE", listOf(c.animal, c.age), remedy = true)
    }

    /** Part 7, the month (pp. 372–373). */
    private fun MutableList<DepartureFinding>.month(c: Context) {
        val p = DeparturePart.MONTH
        val m = c.monthAnimal
        val n = (if (c.animal.gender == Gender.MALE) step(c.animal, m) else step(m, c.animal)) + 1
        when (n) {
            1, 3, 4, 5, 6, 7 -> add(p, "COUNT.$n", BAD, MONTH, "MONTH_COUNT", listOf(c.animal, m, n), remedy = true)
            2 -> add(p, "COUNT.2", GOOD, MONTH, "MONTH_COUNT", listOf(c.animal, m, n))
        }
        c.bride.born?.monthNames?.animal?.let { b ->
            if (m == b) add(p, "BIRTH_MONTH", BAD, MONTH, "MONTH_ANIMAL", listOf(b, m), remedy = true)
            if (step(b, m) == 6) add(p, "BIRTH_SEVENTH", BAD, MONTH, "MONTH_ANIMAL", listOf(b, m), remedy = true)
        }
        if (m == graveMonth(c.animal)) add(p, "GRAVE_MONTH", BAD, MONTH, "MONTH_ANIMAL", listOf(c.animal, m), remedy = true)
        if (m == plus(c.animal, 3)) add(p, "RUIN_MONTH", BAD, MONTH, "MONTH_ANIMAL", listOf(c.animal, m), remedy = true)
        if (m == plus(c.animal, -3)) add(p, "LINE_CUT_MONTH", BAD, MONTH, "MONTH_ANIMAL", listOf(c.animal, m), remedy = true)
    }

    /** Part 8, the date and the date's animal (p. 373). */
    private fun MutableList<DepartureFinding>.date(c: Context) {
        val p = DeparturePart.DATE
        val d = c.day.day
        BAD_DATES[d]?.let { add(p, it, BAD, DAY, "DATE", listOf(d)) }
        if (d % 10 == 7) {
            val own = d == skyDoorDate(group(c.monthAnimal))
            add(p, if (own) "SKY_DOOR_MONTH" else "SKY_DOOR", BAD, DAY, "SKY_DOOR", listOf(d, c.monthAnimal))
        }
        val a = c.dayAnimal
        c.bride.born?.lunarDayAnimal?.let { b ->
            if (a == b) add(p, "OWN_DAY", BAD, DAY, "DAY_ANIMAL", listOf(a, b))
            if (step(b, a) == 6) add(p, "SEVENTH_DAY", BAD, DAY, "DAY_ANIMAL", listOf(a, b))
        }
        if (Forces.kinship(c.her.vitality, life(a)) == Kinship.ENEMY) add(p, "EXECUTIONER_DAY", BAD, DAY, "DAY_ANIMAL", listOf(a, c.animal))
        if (a == YearOfLife.tomb(c.her.vitality)) add(p, "GRAVE_DAY", BAD, DAY, "DAY_ANIMAL", listOf(a, c.animal))
        if (a == SNAKE) add(p, "SNAKE_DAY", BAD, DAY, "DAY_ANIMAL", listOf(a, c.animal), remedy = true)
    }

    /** Part 9, the planet (p. 373). */
    private fun MutableList<DepartureFinding>.planet(c: Context) {
        val p = DeparturePart.PLANET
        val w = c.day.weekday
        val remedy = "reading.Departure.PLANET.remedy"
        val tone = WEEKDAYS.getValue(w)
        add(p, w.name, tone, DAY, "WEEKDAY", listOf(w), remedyKey = remedy.takeIf { tone == BAD })
        if (elementWeekday(c.her.vitality, w) == OwnDay.ENEMY_WEEKDAY) add(p, "ENEMY_WEEKDAY", BAD, DAY, "ENEMY_WEEKDAY", listOf(w, w.fiveElement, c.her.vitality), remedyKey = remedy)
        if (c.bride.born?.weekday == w) add(p, "BIRTH_WEEKDAY", BAD, DAY, "WEEKDAY", listOf(w), remedyKey = remedy)
    }

    /** Part 10, the mansion (p. 373), with the combination days it names. */
    private fun MutableList<DepartureFinding>.mansion(c: Context) {
        val p = DeparturePart.MANSION
        val m = c.day.mansion
        val remedy = "reading.Departure.MANSION.remedy"
        val combination = "reading.Departure.MANSION.COMBINATION.remedy"
        if (m in BAD_MANSIONS) add(p, m.name, BAD, DAY, "MANSION", listOf(m), remedyKey = remedy)
        if (c.bride.born?.mansion == m) add(p, "BIRTH_MANSION", BAD, DAY, "MANSION", listOf(m), remedyKey = remedy)
        if (m in ELEMENT_MANSIONS[c.her.vitality].orEmpty()) add(p, "GOOD.${m.name}", GOOD, DAY, "ELEMENT_MANSION", listOf(m, c.her.vitality))
        for (k in c.day.combinationDays.filter { it in BAD_COMBINATIONS }) {
            add(p, k.name, BAD, DAY, "COMBINATION", listOf(k, c.day.weekday, m), remedyKey = combination)
        }
        if (c.day.burningDate) add(p, "BURNING", BAD, DAY, "BURNING", listOf(c.day.weekday, c.day.day), remedyKey = combination)
    }

    /**
     * Part 11, the earth lords (pp. 373–375): those of the date the departure names, and those of the year and the
     * month it names, toward the direction of travel or, with none given, toward each place they bar. Not weighed (D4).
     */
    private fun MutableList<DepartureFinding>.earthLords(c: Context) {
        val p = DeparturePart.EARTH_LORDS
        fun lord(reading: String, noteName: String, scope: DepartureScope, falls: Fall?, case: String) {
            add(DepartureFinding(p, case, BAD, scope, reading, "$reading.remedy", falls, weighed = false, note = "reading.Departure.EARTH_LORDS.$noteName"))
        }
        if (GreatBlackDay.of(c.day.month, c.day.day) != null) lord("reading.GreatBlackDay", "GreatBlackDay", DAY, null, "GreatBlackDay")
        EarthLordCourses.of(c.day).filter { it.course in DATE_LORDS }.distinctBy { it.course }.forEach {
            lord("reading.EarthLord.${it.course.name}", "EarthLord.${it.course.name}", DAY, EarthLordCourses.fall(it), "EarthLord.${it.course.name}")
        }
        fun directions(seats: List<LordSeat>): List<Direction> = seats.mapNotNull {
            when (val place = it.place) {
                is LordPlace.OnAnimal -> direction(place.animal)
                is LordPlace.InDirection -> place.direction.takeIf { d -> d != Direction.CENTRE }
                else -> null
            }
        }.distinct()
        fun toward(kind: String, name: String, seats: List<LordSeat>) {
            val ds = directions(seats)
            val shown = if (c.direction == null) ds else ds.filter { it == c.direction }
            if (shown.isNotEmpty()) lord("reading.$kind.$name", "$kind.$name", DepartureScope.DIRECTION, DepartureFall("TOWARD", listOf(shown)), "$kind.$name")
        }
        YearMonthEarthLords.year(c.date).filter { it.lord in YEAR_LORDS }.sortedBy { YEAR_LORDS.indexOf(it.lord) }.forEach { toward("YearLord", it.lord.name, it.seats) }
        YearMonthEarthLords.month(c.monthAnimal).filter { it.lord in MONTH_LORDS }.sortedBy { MONTH_LORDS.indexOf(it.lord) }.forEach { toward("MonthLord", it.lord.name, it.seats) }
    }

    /**
     * Part 12, the hour (p. 374, with the notes, vol. 2, p. 531): an hour whose animal's life force is the mother or the
     * friend of hers is good, unless it is her seventh, a four-executioner or her grave; only with an hour given.
     */
    private fun MutableList<DepartureFinding>.hour(c: Context) {
        val h = c.hour ?: return
        val p = DeparturePart.HOUR
        val tomb = YearOfLife.tomb(c.her.vitality)
        when {
            step(c.animal, h) == 6 -> add(p, "SEVENTH", BAD, HOUR, "HOUR_ANIMAL", listOf(h, c.animal))
            step(c.animal, h) == 3 || step(c.animal, h) == 9 -> add(p, "FOUR_EXECUTIONER", BAD, HOUR, "HOUR_ANIMAL", listOf(h, c.animal))
            h == tomb || step(tomb, h) == 6 -> add(p, "GRAVE", BAD, HOUR, "HOUR_ANIMAL", listOf(h, c.animal))
            else -> when (Forces.kinship(c.her.vitality, life(h))) {
                Kinship.MOTHER -> add(p, "MOTHER", GOOD, HOUR, "HOUR_ELEMENT", listOf(h, life(h), c.her.vitality))
                Kinship.FRIEND -> add(p, "FRIEND", GOOD, HOUR, "HOUR_ELEMENT", listOf(h, life(h), c.her.vitality))
                else -> {}
            }
        }
    }

    /** Part 13, the direction (pp. 374–375): its trigram met with hers, and its general mewa; only with a direction given. */
    private fun MutableList<DepartureFinding>.direction(c: Context) {
        val d = c.direction ?: return
        val p = DeparturePart.DIRECTION
        val t = directionTrigram(d)
        val wheel = MatchReckoning.wheel(c.trigram, t)
        val tone = when (wheel) {
            HiddenWheel.GNAM_SMAN, HiddenWheel.SROG_TSHO, HiddenWheel.DPAL_BSKYED -> GOOD
            HiddenWheel.DRE_LNGA, HiddenWheel.BDUD_GCOD, HiddenWheel.LUS_CHAD, HiddenWheel.GNOD_PA -> BAD
            HiddenWheel.PHYWA_LON, HiddenWheel.PHYWA_MNYAM -> null
        }
        if (tone != null) {
            val reading = "reading.Departure.DIRECTION.${wheel.name}"
            val remedy = when (wheel) {
                HiddenWheel.DRE_LNGA, HiddenWheel.BDUD_GCOD, HiddenWheel.GNOD_PA -> "$reading.${c.trigram.name}.remedy"
                HiddenWheel.LUS_CHAD -> "$reading.remedy"
                else -> null
            }
            add(p, wheel.name, tone, DepartureScope.DIRECTION, "DIRECTION_TRIGRAM", listOf(d, t, c.trigram), remedyKey = remedy)
        }
        val yearMewa = YearOfLife.yearSmeBa(c.day.year)
        val m = YearOfLife.smeBaAt(d, yearMewa)
        add(p, "MEWA.$m", directionMewaTone(m), DepartureScope.DIRECTION, "DIRECTION_MEWA", listOf(d, m, yearMewa))
    }

    /**
     * Part 14, the sky dog's own reckoning (pp. 375–376, with vol. 2, pp. 196–198): the male on *khen* and the female on
     * *zon* (her trigram of age), the dog in her own triad's last year, the seven dogs, the coupling and the search for food.
     */
    private fun MutableList<DepartureFinding>.skyDog(c: Context) {
        val p = DeparturePart.SKY_DOG
        val remedy = "reading.Departure.SKY_DOG.remedy"
        when (c.trigram) {
            Trigram.KHEN -> add(p, "MALE", BAD, YEAR, "SKY_DOG_TRIGRAM", listOf(c.trigram, c.age), remedy = true)
            Trigram.ZON -> add(p, "FEMALE", BAD, YEAR, "SKY_DOG_TRIGRAM", listOf(c.trigram, c.age), remedy = true)
            Trigram.LI -> add(p, "LI", BAD, YEAR, "SKY_DOG_TRIGRAM", listOf(c.trigram, c.age), remedyKey = remedy)
            else -> {}
        }
        val last = triad(c.animal).last()
        if (c.chineseYear == last) {
            if (c.monthAnimal == last) add(p, "LURKING", BAD, MONTH, "SKY_DOG_OWN", listOf(c.animal, last, MONTH), remedyKey = remedy)
            if (c.dayAnimal == last) add(p, "WATCH", BAD, DAY, "SKY_DOG_OWN", listOf(c.animal, last, DAY), remedyKey = remedy)
            if (c.hour == last) add(p, "FOOD_HOUR", BAD, HOUR, "SKY_DOG_OWN", listOf(c.animal, last, HOUR), remedyKey = remedy)
        }
        val dogs = setOf(TIGER, HORSE, DOG)
        if (c.chineseYear in dogs) add(p, "YEAR", BAD, YEAR, "SKY_DOG_TIME", listOf(YEAR, c.chineseYear), remedyKey = remedy)
        if (c.season == 6) add(p, "MONTH", BAD, MONTH, "SKY_DOG_MONTH", listOf(SeasonMonth(c.season)), remedyKey = remedy)
        if (c.dayAnimal in dogs) add(p, "DAY", BAD, DAY, "SKY_DOG_TIME", listOf(DAY, c.dayAnimal), remedyKey = remedy)
        if (EarthLordCourses.blackDay(c.season, c.dayAnimal)) add(p, "BLACK_DAY", BAD, DAY, "SKY_DOG_TIME", listOf(DAY, c.dayAnimal), remedyKey = remedy)
        if (c.day.weekday == Weekday.WEDNESDAY) add(p, "PLANET", BAD, DAY, "WEEKDAY", listOf(c.day.weekday), remedyKey = remedy)
        COUPLING.getValue(c.chineseYear).let { (s, d) ->
            if (c.season == s && c.day.day == d) add(p, "COUPLING", BAD, DAY, "COUPLING", listOf(c.chineseYear, SeasonMonth(s), d))
        }
        val (animals, at) = FOOD[c.season]
        if (c.dayAnimal in animals) {
            add(p, "FOOD", BAD, DAY, "SKY_DOG_FOOD", listOf(SeasonMonth(c.season), Animal.entries.filter { it in animals }, c.dayAnimal), remedy = true, at = at?.let { "CourseAt.$it" })
        }
    }

    /**
     * Parts 15 and 16, the five people and receiving the bride (p. 376): whom the roles want, by her birth year, and
     * what receives her by the trigram she goes on. They name people, not the day: not weighed.
     */
    private fun MutableList<DepartureFinding>.people(c: Context) {
        val p = DeparturePart.FIVE_PEOPLE
        fun mother(e: Element): Element = Element.entries.first { it.feeds == e }
        fun person(case: String, rule: String, terms: List<Any>) = add(p, case, GOOD, YEAR, rule, terms, weighed = false)
        person("LAMA", "PEOPLE_LAMA", listOf(triad(c.animal), mother(c.her.vitality)))
        person("HORSE_LEADER", "PEOPLE_BEARER", listOf(Force.BODY, mother(c.her.body)))
        person("WELCOMER", "PEOPLE_BEARER", listOf(Force.DESTINY, mother(c.her.destiny)))
        person("ESCORT", "PEOPLE_ESCORT", listOf(listOf(mother(c.her.vitality), mother(c.her.destiny), mother(c.her.luck)).distinct()))
        person("SNA_BON", "PEOPLE_SNA_BON", listOf(Element.entries.first { it.overcomes == c.bride.sign.element }, plus(c.animal, 6)))
        add(DeparturePart.RECEIVING, c.trigram.name, GOOD, YEAR, "RECEIVING", listOf(c.trigram), weighed = false)
    }
}
