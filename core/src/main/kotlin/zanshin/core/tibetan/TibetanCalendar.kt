/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import zanshin.core.rational.Rational
import zanshin.core.time.amod
import zanshin.core.time.julianDayNumber
import zanshin.core.time.localDateOfJulianDayNumber
import zanshin.core.time.mod
import java.time.LocalDate

enum class Element { WOOD, FIRE, EARTH, IRON, WATER }

enum class Animal { MOUSE, OX, TIGER, RABBIT, DRAGON, SNAKE, HORSE, SHEEP, MONKEY, BIRD, DOG, PIG }

enum class Gender { MALE, FEMALE }

/** Days of the week with their Tibetan names and planets, Janson Table 5. */
enum class Weekday(val english: String, val wylie: String, val planet: String) {
    SATURDAY("Saturday", "spen ma", "Saturn"),
    SUNDAY("Sunday", "nyi ma", "Sun"),
    MONDAY("Monday", "zla ba", "Moon"),
    TUESDAY("Tuesday", "mig dmar", "Mars"),
    WEDNESDAY("Wednesday", "lhag pa", "Mercury"),
    THURSDAY("Thursday", "phur bu", "Jupiter"),
    FRIDAY("Friday", "pa sangs", "Venus"),
}

/** Month names, Phugpa system, Janson Table 4. */
data class MonthNames(val wylie: String, val sanskrit: String, val animal: Animal, val season: String)

enum class Repetition { NONE, FIRST_OF_TWO, SECOND_OF_TWO }

data class Holiday(val festival: TibetanFestival, val movedFromDay: Int? = null) {
    val name: String get() = festival.title
}

data class TibetanDay(
    val jd: Long,
    val year: Int,
    val month: Int,
    val leapMonth: Boolean,
    val day: Int,
    val repetition: Repetition,
    /** The lunar day skipped just before this day, if any. */
    val omittedBefore: Int?,
    val weekday: Weekday,
    val dayElement: Element,
    val dayGender: Gender,
    val dayAnimal: Animal,
    val yearElement: Element,
    val yearGender: Gender,
    val yearAnimal: Animal,
    val royalYear: Int,
    val rabjungCycle: Int,
    val rabjungYear: Int,
    val monthNames: MonthNames,
    val holiday: Holiday?,
    /** Lunar mansion of the moon at daybreak (Janson (10.3)). */
    val mansion: Mansion,
    /** Combination of the weekday's and the mansion's element. */
    val elementPair: ElementPair,
    val yoga: Yoga,
    /** Karaṇa in effect at daybreak. */
    val karana: Karana,
    /** Animal, trigram and number of the lunar day (Janson E.3). */
    val lunarDayAnimal: Animal,
    val trigram: Trigram,
    val smeBa: Int,
    /** Monthly observance on this lunar day, if any; none in leap months' days are excluded. */
    val specialDay: SpecialDay?,
)

object TibetanCalendar {

    private val MONTH_NAMES = listOf(
        MonthNames("mchu", "Māgha", Animal.DRAGON, "early spring"),
        MonthNames("dbo", "Phālguna", Animal.SNAKE, "mid spring"),
        MonthNames("nag pa", "Caitra", Animal.HORSE, "late spring"),
        MonthNames("sa ga", "Vaiśākha", Animal.SHEEP, "early summer"),
        MonthNames("snron", "Jyeṣṭha", Animal.MONKEY, "mid summer"),
        MonthNames("chu stod", "Āṣāḍha", Animal.BIRD, "late summer"),
        MonthNames("gro bzhin", "Śrāvaṇa", Animal.DOG, "early autumn"),
        MonthNames("khrums", "Bhādrapada", Animal.PIG, "mid autumn"),
        MonthNames("tha skar", "Āśvina", Animal.MOUSE, "late autumn"),
        MonthNames("smin drug", "Kārttika", Animal.OX, "early winter"),
        MonthNames("mgo", "Mārgaśīrṣa", Animal.TIGER, "mid winter"),
        MonthNames("rgyal", "Pauṣa", Animal.RABBIT, "late winter"),
    )

    fun of(date: LocalDate, a2: Rational = Phugpa.A2_ALMANAC): TibetanDay = of(date.julianDayNumber(), a2)

    fun of(jd: Long, a2: Rational = Phugpa.A2_ALMANAC): TibetanDay {
        val month = monthContaining(jd, a2)
        val n = month.count
        val day = (1..30).first { Phugpa.endJd(n, it, a2) >= jd }
        val endsToday = Phugpa.endJd(n, day, a2) == jd
        val repetition = when {
            !endsToday -> Repetition.FIRST_OF_TWO
            Phugpa.endJd(n, day - 1, a2) < jd - 1 -> Repetition.SECOND_OF_TWO
            else -> Repetition.NONE
        }
        val omitted = if (repetition != Repetition.SECOND_OF_TWO &&
            Phugpa.endJd(n, day - 1, a2) == Phugpa.endJd(n, day - 2, a2)
        ) amod(day - 1, 30) else null

        val moon = if (repetition == Repetition.FIRST_OF_TWO) {
            // Janson §10: for the first of two equal dates the moon is taken
            // one mansion before its position at the end of the lunar day.
            (Phugpa.trueSun(n, day) + Rational.of(day.toLong(), 30) - Rational.of(1, 27)).frac()
        } else {
            Phugpa.moonAtDaybreak(n, day, a2)
        }
        val sun = Phugpa.trueSun(n, day)
        val mansion = Mansion.entries[(moon * 27).floor().toInt()]
        val weekday = Weekday.entries[mod(jd + 2, 7L).toInt()]
        val monthAnimal = amod(month.number + 4, 12)

        val y = month.year
        val yz = amod(y - 3, 10)
        val rabjungOffset = y - 1026
        return TibetanDay(
            jd = jd,
            year = y,
            month = month.number,
            leapMonth = month.leap,
            day = day,
            repetition = repetition,
            omittedBefore = omitted,
            weekday = weekday,
            dayElement = Element.entries[(amod(jd, 10L).toInt() + 1) / 2 - 1],
            dayGender = if (jd % 2 != 0L) Gender.MALE else Gender.FEMALE,
            dayAnimal = Animal.entries[amod(jd + 2, 12L).toInt() - 1],
            yearElement = Element.entries[(yz + 1) / 2 - 1],
            yearGender = if (yz % 2 == 1) Gender.MALE else Gender.FEMALE,
            yearAnimal = Animal.entries[amod(y - 3, 12) - 1],
            royalYear = y + 127,
            rabjungCycle = Math.floorDiv(rabjungOffset + 59, 60),
            rabjungYear = amod(rabjungOffset, 60),
            monthNames = MONTH_NAMES[month.number - 1],
            holiday = holidayOn(jd, month, day, repetition, a2),
            mansion = mansion,
            elementPair = ElementPair.of(weekday.element, mansion.element),
            yoga = Yoga.entries[((moon + sun).frac() * 27).floor().toInt()],
            karana = Karana.ofHalfDay(((moon - sun).frac() * 60).floor().toInt() + 1),
            lunarDayAnimal = Animal.entries[amod(day + 6 * month.number + 8, 12) - 1],
            trigram = Trigram.entries[amod(day + 6 * monthAnimal + 6, 8) - 1],
            smeBa = amod(day + 3 * monthAnimal, 9),
            specialDay = SpecialDay.entries.firstOrNull { it.day == day },
        )
    }

    /** First day of Tibetan year [year] (Janson §8, "Tibetan New Year"). */
    fun losar(year: Int, a2: Rational = Phugpa.A2_ALMANAC): LocalDate =
        localDateOfJulianDayNumber(Phugpa.lastJd(Phugpa.trueMonthCount(year - 1, 12), a2) + 1)

    private fun monthContaining(jd: Long, a2: Rational): Phugpa.Month {
        val estimate = Phugpa.approximateCount(jd)
        val n = (estimate - 1..estimate + 2).first { jd in Phugpa.firstJd(it, a2)..Phugpa.lastJd(it, a2) }
        val gregorianYear = localDateOfJulianDayNumber(jd).year
        return (gregorianYear - 1..gregorianYear).asSequence()
            .flatMap { Phugpa.monthsOf(it) }
            .first { it.count == n }
    }

    private fun holidayOn(jd: Long, month: Phugpa.Month, day: Int, repetition: Repetition, a2: Rational): Holiday? {
        if (jd == losar(month.year, a2).julianDayNumber()) return Holiday(TibetanFestival.LOSAR)
        if (month.leap) return null
        val n = month.count
        fun festivalOn(d: Int) = TibetanFestival.entries.firstOrNull {
            it != TibetanFestival.LOSAR && it.month == month.number && it.day == d
        }
        if (repetition != Repetition.SECOND_OF_TWO) festivalOn(day)?.let { return Holiday(it) }
        // A holiday whose lunar day is skipped falls on the preceding day: the
        // calendar day in which both lunar days end (Janson §11).
        val next = day + 1
        if (next <= 30 && Phugpa.endJd(n, next, a2) == Phugpa.endJd(n, day, a2) && Phugpa.endJd(n, day, a2) == jd) {
            festivalOn(next)?.let { return Holiday(it, movedFromDay = next) }
        }
        return null
    }
}
