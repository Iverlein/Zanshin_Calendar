/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import zanshin.core.rational.Rational
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
import zanshin.core.time.mod

/**
 * What the White Beryl's full almanac writes at the head of each month (vol. 1, p. 176;
 * docs/sources/month-entries.md): whether the month is long or short, the black months, the weekday
 * that rises in it, and the seasonal signs, each by WB's own rule.
 */
object MonthEntries {

    /** The days of the month of count [n]: 30 dates, less the omitted, more the doubled. */
    fun days(n: Long, a2: Rational = Phugpa.A2_ALMANAC): Int = (Phugpa.lastJd(n, a2) - Phugpa.firstJd(n, a2) + 1).toInt()

    /**
     * Long or short (p. 176, «ཆད་ལྷག་ཇི་ཙམ་ཡོད་ཀྱང་རུང་། །ཚེས་ཞག་མཁའ་མེ་ལོངས་པ་ན། །ཆེ་དང་དེ་ཚུན་ཆུང་བ་སྟེ»): however many
     * dates are omitted or doubled, a month whose days reach thirty is long, one with fewer short.
     */
    fun isLong(days: Int): Boolean = days >= 30

    // The weekday's rise (gza' dar gud).

    /** What a planet does in the month by the fine reckoning: rises, wanes, declines or moves (p. 176). */
    enum class Rise(val wylie: String) { RISES("dar"), WANES("zhud"), DECLINES("gud"), MOVES("skyod") }

    /**
     * The planet that rises in the month by the rough reckoning, "as the *Gtsug lag spor thang* means it"
     * (p. 176, «སྟག་རྟ་ཁྱི་གསུམ་སྤེན་དར་ཚེས། །བྱི་འབྲུག་སྤྲེལ་གསུམ་མིག་དར་ཚེས། །ཕག་ལུག་ཡོས་གསུམ་ཟླ་དར་ཚེས། །བྱ་གླང་སྦྲུལ་གསུམ་ཕུར་དར་ཚེས»): Saturn in the
     * months of the tiger, horse and dog, Mars in the mouse, dragon and monkey, the Moon in the pig,
     * sheep and hare, Jupiter in the bird, ox and snake. The month's animal is [MonthNames.animal].
     */
    fun roughRise(monthAnimal: Animal): Weekday = when (monthAnimal) {
        TIGER, HORSE, DOG -> Weekday.SATURDAY
        MOUSE, DRAGON, MONKEY -> Weekday.TUESDAY
        PIG, SHEEP, RABBIT -> Weekday.MONDAY
        BIRD, OX, SNAKE -> Weekday.THURSDAY
    }

    /**
     * The month's lord (p. 176, «ཞིབ་པར་ཟླ་བའི་བདག །རྣམ་དག་གྲུབ་པའི་ཚེས་གཅིག་གི། །རེས་གཟའ»): the true weekday of its first date,
     * the weekday of the day in which the 1st ends.
     */
    fun lord(n: Long, a2: Rational = Phugpa.A2_ALMANAC): Weekday = Weekday.entries[mod(Phugpa.endJd(n, 1, a2) + 2, 7L).toInt()]

    /**
     * What [planet] does in a month whose lord is [lord], by the fine reckoning (p. 176, «རེས་གཟའི་ཁམས་ནས་བརྩི་བ་ལ། །གང་ཆེ་དེ་དར་བུ་ཡང་དར། །མ་ཞུད་དགྲ་གུད་གྲོགས་སྐྱོད་པར»):
     * counted from the element of the lord, the planets of its element rise and its son's rise too, its
     * mother's wane, its enemy's decline and its friend's move. The elements are the Chinese
     * reckoning's ([fiveElement]).
     */
    fun rise(lord: Weekday, planet: Weekday): Rise = when (Forces.kinship(lord.fiveElement, planet.fiveElement)) {
        Kinship.IDENTITY, Kinship.SON -> Rise.RISES
        Kinship.MOTHER -> Rise.WANES
        Kinship.ENEMY -> Rise.DECLINES
        Kinship.FRIEND -> Rise.MOVES
    }

    // The black months (zla nag).

    /** The third of a month that is black, by its dates. */
    enum class MonthPart(val dates: IntRange) { UPPER(1..10), MIDDLE(11..20), LOWER(21..30) }

    /**
     * The black months of the year of [year] (vol. 2, p. 212, «རྣམ་དག་ཕུག་པ་གོང་མའི་ལུགས། །གནམ་ལོ་རྒྱལ་པོ་དབུས་བཞག་པའི། །ཡར་མར་བཞི་གཤེད་ཟླ་བ་ནག»;
     * vol. 1, p. 183, «བྱི་རྟ་གནམ་ཤར་བྱ་ཡོས་ནག»): the months of the animals that are the year's four-slayers up and down,
     * three either side of it; the year of the mouse or horse makes the bird and hare months black.
     * The year is the Chinese reckoning's ([EarthLordCourses.chineseYear]), as in the chapter's black
     * days.
     */
    fun blackMonths(year: Animal): Set<Animal> = setOf(Animal.entries[(year.ordinal + 3) % 12], Animal.entries[(year.ordinal + 9) % 12])

    /**
     * The black third of a month of [monthAnimal] (p. 212, «དེ་ཡང་ར་བ་བཞི་ཡི་སྟོད། །འབྲིང་སྒང་ཐ་ཆུང་ཟླ་སྨད་ནག»): the upper in the four
     * first months of the seasons, the middle (*sgang*) in the four middle ones, the lower in the four
     * last; WB's notes count the month's *sgang* from the 11th to the 20th (vol. 2, p. 484). The seasons
     * are the Chinese reckoning's, the tiger month the first of spring.
     */
    fun blackPart(monthAnimal: Animal): MonthPart = MonthPart.entries[Math.floorMod(monthAnimal.ordinal - TIGER.ordinal, 3)]

    /** The black third of [day]'s month if the month is black in its year, else null; the days in it are [EarthLordCourse.ZLA_NAG]'s. */
    fun blackMonth(day: TibetanDay): MonthPart? =
        day.monthNames.animal.takeIf { it in blackMonths(EarthLordCourses.chineseYear(day)) }?.let(::blackPart)

    /** The time of day a *ki kang* black month names, if any. */
    enum class DayTime(val wylie: String) { DAWN("tho rengs"), SUNRISE("nyi shar"), DUSK("bya nyal") }

    /**
     * One year's *ki kang* black month (p. 212, «ཁྱད་པར་ཞག་དུས་སྤང་བྱ་བ། …འདི་ལ་ཀི་ཀང་ཟླ་ནག་ཅེས། །ཤིན་ཏུ་གཉན་པས་གཟབ་གལ་ཆེ»): in
     * [seasons], two season-months of the Chinese reckoning (0 the first of spring, the 11th month), on
     * [date], at [time] where it names one, the work the year's line names is ruinous.
     */
    data class KiKang(val year: Animal, val seasons: Set<Int>, val date: Int, val time: DayTime?)

    /**
     * The twelve *ki kang* black months, read on the scan (img. 220): the summer's and winter's first,
     * middle and last months in the years of the tiger, hare and dragon and of the monkey, bird and dog,
     * the autumn's and spring's in the others; the dates 8, 18, the full moon, the 30th, the 15th, the
     * 22nd and so on as each line gives them.
     */
    val KI_KANG: Map<Animal, KiKang> = listOf(
        KiKang(TIGER, setOf(3, 9), 8, DayTime.DAWN),
        KiKang(RABBIT, setOf(4, 10), 18, DayTime.SUNRISE),
        KiKang(DRAGON, setOf(5, 11), 15, null),
        KiKang(SNAKE, setOf(6, 0), 30, null),
        KiKang(HORSE, setOf(7, 1), 15, null),
        KiKang(SHEEP, setOf(8, 2), 22, DayTime.DUSK),
        KiKang(MONKEY, setOf(3, 9), 8, DayTime.DAWN),
        KiKang(BIRD, setOf(4, 10), 18, DayTime.SUNRISE),
        KiKang(DOG, setOf(5, 11), 8, null),
        KiKang(PIG, setOf(6, 0), 30, null),
        KiKang(MOUSE, setOf(7, 1), 15, null),
        KiKang(OX, setOf(8, 2), 22, DayTime.DUSK),
    ).associateBy { it.year }

    /** The year's *ki kang* black month if it falls in [day]'s month, else null; its day is [EarthLordCourse.KI_KANG_ZLA_NAG]'s. */
    fun kiKang(day: TibetanDay): KiKang? = KI_KANG.getValue(EarthLordCourses.chineseYear(day))
        .takeIf { SeasonReckoning.CHINESE.season(day.month) in it.seasons }

    // The seasonal signs (vol. 1, ch. 16).

    /**
     * The seasonal signs WB's month heading writes and ch. 16 dates by the mean sun of the true
     * reckoning (*rnam dag grub pa'i nyi bar*), with [mansion], [chuTshod] and [chuSrang] its measure:
     * the Ṛṣi's seven days, when the waters gain the eight qualities (p. 187, «རྣམ་དག་གྲུབ་པའི་ཉི་བར་གྱི། །སྐར་ཕྱོགས་མེ་མཚོ་མཁའ་མེ་སྲང་། །ཤར་ཚེ་རི་ཏིའི་དུས་འབྱུང་ངོ», 10;43,30),
     * and the pig's seven days, when the pig of the charnel ground climbs the tree and the rain turns
     * to poison (p. 188, «འཕྱུགས་མེད་ཉི་བར་ལ། །མདའ་དང་དུས་མཚོ་མཁའ་མེ་སྲང་། །ཤར་ཚེ་ཕག་གི་ཞག་པོ་བདུན», 5;46,30).
     */
    enum class SeasonSign(val wylie: String, val mansion: Int, val chuTshod: Int, val chuSrang: Int) {
        RISHI("ri Shi'i zhag bdun", 10, 43, 30),
        PIG("phag zhag", 5, 46, 30);

        /** The measure in revolutions of the sun's course. */
        val measure: Rational get() = Rational.of(mansion * 3600L + chuTshod * 60L + chuSrang, 27L * 3600)
    }

    /** The mean sun of the true reckoning at the end of lunar date [d] of month count [n], in revolutions (Janson (7.6)–(7.8)). */
    fun meanSun(n: Long, d: Int): Rational = Phugpa.meanSun(n, d)

    /**
     * The date of month count [n] in which the mean sun reaches [sign]'s measure, if it does in that
     * month: the first date at whose end it stands at or past it («སྔ་ཕྱིའི་ཚེས་གྲངས་གང་ཟིན་བརྟག», p. 189).
     */
    fun signDate(n: Long, sign: SeasonSign): Int? {
        val m = sign.measure
        fun past(d: Int): Boolean {
            val s0 = meanSun(n, d - 1)
            val s1 = meanSun(n, d)
            return if (s1 >= s0) m > s0 && m <= s1 else m > s0 || m <= s1
        }
        return (1..30).firstOrNull(::past)
    }

    /**
     * Where [day] stands in [sign]'s seven days, 1–7, if it does: the days are counted from the calendar
     * day in which the sign's date ends.
     */
    fun signDay(day: TibetanDay, sign: SeasonSign, a2: Rational = Phugpa.A2_ALMANAC): Int? {
        for (n in day.monthCount - 1..day.monthCount) {
            val d = signDate(n, sign) ?: continue
            val k = (day.jd - Phugpa.endJd(n, d, a2) + 1).toInt()
            if (k in 1..7) return k
        }
        return null
    }

    /**
     * The months WB's count marks for a long-tailed comet (*du ba mjug ring*, vol. 1, p. 186, «རབ་བྱུང་ལ་སོགས་འདས་པའི་ལོ། །ཉི་མས་བསྒྱུར་ལ་ནག་པ་སོགས། །འདས་ཟླ་བསྲེས་ལ་གནས་གཉིས་བཞག །འོག་མ་མིག་བསྒྱུར་གཟུགས་མེས་བརྒྱན། །དབང་རོས་བགོས་ཐོབ་ཟླ་བ་དག །ཟླ་བ་དག་པར་རྩེ་མོ་བྱིན། །དབང་རིས་བགོས་ལྷག་ཐིག་ཤར་ན། །མཇུག་རིང་དུ་ཕོད་བཅས་པ་མཐོང»):
     * the years elapsed since the rab byung began (1027, as the *Kun 'dus chen mo* restates the line,
     * «རབ་རྒྱུན་ནས་བཟུང་འདས་པའི་ལོ», vol. 2, p. 145) times twelve, with the months elapsed since *nag pa*, the 3rd
     * month, where the Kālacakra's year begins; that, with its double and 31 divided by 65 added, is the
     * corrected month count; with 3 added it leaves nothing over 75 in a comet's month. A leap month
     * counts one less than its regular month.
     */
    fun cometMonth(year: Int, month: Int, leap: Boolean): Boolean {
        val (kalacakraYear, elapsed) = if (month >= 3) year to month - 3 else year - 1 to month + 9
        val m = 12L * (kalacakraYear - 1027) + elapsed
        val corrected = m + Math.floorDiv(2 * m + 31, 65L) - if (leap) 1 else 0
        return Math.floorMod(corrected + 3, 75L) == 0L
    }
}
