/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.kyureki

import zanshin.core.astro.Astro
import zanshin.core.texts.Catalog
import zanshin.core.texts.gloss
import zanshin.core.time.julianDayNumber
import zanshin.core.time.mod
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneOffset
import java.util.concurrent.ConcurrentHashMap

enum class Rokuyo(val kanji: String, val romaji: String) {
    TAIAN("大安", "Taian"),
    SHAKKO("赤口", "Shakkō"),
    SENSHO("先勝", "Senshō"),
    TOMOBIKI("友引", "Tomobiki"),
    SENBU("先負", "Senbu"),
    BUTSUMETSU("仏滅", "Butsumetsu");

    val english: String get() = gloss(this)
}

/** The 24 solar terms in Japanese almanac order, from 立春 at 315°. */
enum class SolarTerm(val longitude: Int, val kanji: String, val romaji: String) {
    RISSHUN(315, "立春", "Risshun"), USUI(330, "雨水", "Usui"),
    KEICHITSU(345, "啓蟄", "Keichitsu"), SHUNBUN(0, "春分", "Shunbun"),
    SEIMEI(15, "清明", "Seimei"), KOKUU(30, "穀雨", "Kokuu"),
    RIKKA(45, "立夏", "Rikka"), SHOMAN(60, "小満", "Shōman"),
    BOSHU(75, "芒種", "Bōshu"), GESHI(90, "夏至", "Geshi"),
    SHOSHO(105, "小暑", "Shōsho"), TAISHO(120, "大暑", "Taisho"),
    RISSHU(135, "立秋", "Risshū"), SHOSHO_HEAT(150, "処暑", "Shosho"),
    HAKURO(165, "白露", "Hakuro"), SHUBUN(180, "秋分", "Shūbun"),
    KANRO(195, "寒露", "Kanro"), SOKO(210, "霜降", "Sōkō"),
    RITTO(225, "立冬", "Rittō"), SHOSETSU(240, "小雪", "Shōsetsu"),
    TAISETSU(255, "大雪", "Taisetsu"), TOJI(270, "冬至", "Tōji"),
    SHOKAN(285, "小寒", "Shōkan"), DAIKAN(300, "大寒", "Daikan");

    val english: String get() = gloss(this)


    companion object {
        fun atLongitude(deg: Int): SolarTerm = entries.first { it.longitude == deg }
    }
}

data class Festival(val kanji: String, val romaji: String) {
    val english: String get() = Catalog.text("Festival.$kanji")
}

data class KyurekiDay(
    val date: LocalDate,
    /** Kyūreki year, numbered by the Gregorian year in which its first month begins. */
    val year: Int,
    val month: Int,
    val leapMonth: Boolean,
    val day: Int,
    /** Traditional month name, e.g. 葉月. */
    val monthName: String,
    val monthNameRomaji: String,
    val rokuyo: Rokuyo,
    val dayKanshi: String,
    val yearKanshi: String,
    /** The solar term whose instant falls on this JST day, if any. */
    val termBeginning: SolarTerm?,
    val currentTerm: SolarTerm,
    val currentTermStart: LocalDate,
    val nextTermStart: LocalDate,
    val festival: Festival?,
) {
    /** English of the traditional month name, from the catalog. */
    val monthNameEnglish: String get() = Catalog.text("KyurekiMonth.$month")
}

/**
 * The Japanese lunisolar calendar under the Tenpō rules (SPEC §7), computed
 * in JST: months begin on the JST day of new moon and are numbered by the
 * major term (中気) they contain; a month without one is a leap month
 * numbered after the month before it.
 */
object Kyureki {
    val JST: ZoneOffset = ZoneOffset.ofHours(9)

    private val STEMS = "甲乙丙丁戊己庚辛壬癸"
    private val BRANCHES = "子丑寅卯辰巳午未申酉戌亥"

    private val MONTH_NAMES = listOf(
        "睦月" to "Mutsuki", "如月" to "Kisaragi", "弥生" to "Yayoi", "卯月" to "Uzuki",
        "皐月" to "Satsuki", "水無月" to "Minazuki", "文月" to "Fumizuki", "葉月" to "Hazuki",
        "長月" to "Nagatsuki", "神無月" to "Kannazuki", "霜月" to "Shimotsuki", "師走" to "Shiwasu",
    )

    private val FESTIVALS = mapOf(
        (1 to 1) to Festival("旧正月", "Kyū-shōgatsu"),
        (1 to 7) to Festival("人日の節句", "Jinjitsu"),
        (3 to 3) to Festival("上巳の節句", "Jōshi"),
        (5 to 5) to Festival("端午の節句", "Tango"),
        (7 to 7) to Festival("七夕", "Tanabata"),
        (8 to 15) to Festival("十五夜", "Jūgoya"),
        (9 to 9) to Festival("重陽の節句", "Chōyō"),
        (9 to 13) to Festival("十三夜", "Jūsanya"),
    )

    /** Major terms that fix a month number regardless of leap months (Tenpō rule 4). */
    private val FIXED = mapOf(0 to 2, 90 to 5, 180 to 8, 270 to 11)

    /**
     * SPEC §7.1 rule 5, the 2033 problem: in the sui that opens in month 11
     * of 2033 the rules contradict each other. Resolution: the leap month is
     * the one right after month 11 (閏11月), as recommended by the Japan
     * calendar society. To be confirmed from its published source (S3).
     */
    private const val RESOLUTION_2033_SUI = 2033
    private const val RESOLUTION_2033_LEAP_INDEX = 1

    data class Month(val start: LocalDate, val end: LocalDate, val number: Int, val leap: Boolean, val year: Int)

    private val suiCache = ConcurrentHashMap<Int, List<Month>>()
    private val termCache = ConcurrentHashMap<Int, List<Pair<SolarTerm, LocalDate>>>()

    fun of(date: LocalDate): KyurekiDay {
        val month = monthOf(date)
        val day = (date.toEpochDay() - month.start.toEpochDay()).toInt() + 1
        val jd = date.julianDayNumber()
        val terms = termsAround(date)
        val current = terms.last { !it.second.isAfter(date) }
        val next = terms.first { it.second.isAfter(date) }
        val (name, romaji) = MONTH_NAMES[month.number - 1]
        return KyurekiDay(
            date = date,
            year = month.year,
            month = month.number,
            leapMonth = month.leap,
            day = day,
            monthName = name,
            monthNameRomaji = romaji,
            rokuyo = Rokuyo.entries[(month.number + day) % 6],
            dayKanshi = kanshi(mod(jd + 49, 60L).toInt()),
            yearKanshi = kanshi(mod(month.year - 4, 60)),
            termBeginning = current.first.takeIf { current.second == date },
            currentTerm = current.first,
            currentTermStart = current.second,
            nextTermStart = next.second,
            festival = if (month.leap) null else FESTIVALS[month.number to day],
        )
    }

    fun monthOf(date: LocalDate): Month {
        for (year in listOf(date.year, date.year - 1)) {
            sui(year).firstOrNull { !date.isBefore(it.start) && date.isBefore(it.end) }?.let { return it }
        }
        error("no kyūreki month for $date")
    }

    /**
     * The months from month 11 of [year] (the month containing 冬至 in
     * December of [year]) up to, not including, month 11 of [year] + 1.
     */
    fun sui(year: Int): List<Month> = suiCache.getOrPut(year) { computeSui(year) }

    private fun computeSui(year: Int): List<Month> {
        val ws1 = jstDate(winterSolstice(year))
        val ws2 = jstDate(winterSolstice(year + 1))
        val newMoons = Astro.newMoonsBetween(jstMidnight(ws1.minusDays(40)), jstMidnight(ws2.plusDays(40)))
            .map(::jstDate)
        val start1 = newMoons.last { !it.isAfter(ws1) }
        val start2 = newMoons.last { !it.isAfter(ws2) }
        val starts = newMoons.filter { !it.isBefore(start1) && !it.isAfter(start2) }
        val majors = Astro.solarLongitudeCrossings(jstMidnight(start1), jstMidnight(start2), stepDeg = 30)
            .map { (deg, instant) -> deg to jstDate(instant) }

        val spans = starts.zipWithNext()
        val majorsPerMonth = spans.map { (from, to) ->
            majors.filter { !it.second.isBefore(from) && it.second.isBefore(to) }.map { it.first }
        }

        val leapIndex: Int? = when {
            spans.size == 12 -> null
            year == RESOLUTION_2033_SUI -> RESOLUTION_2033_LEAP_INDEX
            else -> {
                val candidates = majorsPerMonth.indices.filter { it > 0 && majorsPerMonth[it].isEmpty() }
                candidates.firstOrNull { satisfiesFixedTerms(number(spans.size, it), majorsPerMonth) }
                    ?: candidates.first()
            }
        }
        val numbers = number(spans.size, leapIndex)
        return spans.mapIndexed { i, (from, to) ->
            val (n, leap) = numbers[i]
            Month(from, to, n, leap, if (n >= 11) year else year + 1)
        }
    }

    /** Month numbers for a sui of [size] months, starting at 11, with a leap month at [leapIndex]. */
    private fun number(size: Int, leapIndex: Int?): List<Pair<Int, Boolean>> {
        val result = mutableListOf<Pair<Int, Boolean>>()
        var n = 11
        for (i in 0 until size) {
            if (i > 0) {
                if (i == leapIndex) {
                    result += n to true
                    continue
                }
                n = n % 12 + 1
            }
            result += n to false
        }
        return result
    }

    private fun satisfiesFixedTerms(numbers: List<Pair<Int, Boolean>>, majors: List<List<Int>>): Boolean =
        majors.indices.all { i ->
            majors[i].all { deg -> FIXED[deg]?.let { it == numbers[i].first && !numbers[i].second } ?: true }
        }

    private fun termsAround(date: LocalDate): List<Pair<SolarTerm, LocalDate>> {
        val here = termsOfYear(date.year)
        return termsOfYear(date.year - 1).takeLast(2) + here + termsOfYear(date.year + 1).take(2)
    }

    private fun termsOfYear(year: Int): List<Pair<SolarTerm, LocalDate>> = termCache.getOrPut(year) {
        Astro.solarLongitudeCrossings(
            jstMidnight(LocalDate.of(year, 1, 1)),
            jstMidnight(LocalDate.of(year + 1, 1, 1)),
            stepDeg = 15,
        ).map { (deg, instant) -> SolarTerm.atLongitude(deg) to jstDate(instant) }
    }

    /** The instant in Gregorian [year] when the sun's apparent longitude is [deg]. */
    fun solarLongitudeIn(year: Int, deg: Double): Instant {
        val start = jstMidnight(LocalDate.of(year, 1, 1))
        // The sun is at 280° about 1 January; aim at the crossing within the year.
        val daysAhead = ((deg - 280.0).mod(360.0)) * 365.2422 / 360.0
        return Astro.solarLongitudeInstant(deg, start.plusSeconds((daysAhead * 86400).toLong()))
    }

    /** JST date of an instant. */
    fun jstDateOf(instant: Instant): LocalDate = jstDate(instant)

    /** The 24 solar terms whose JST dates fall in Gregorian [year], in order. */
    fun termsIn(year: Int): List<Pair<SolarTerm, LocalDate>> = termsOfYear(year)

    private fun winterSolstice(year: Int): Instant =
        Astro.solarLongitudeInstant(270.0, jstMidnight(LocalDate.of(year, 12, 21)))

    private fun kanshi(index: Int): String = "${STEMS[index % 10]}${BRANCHES[index % 12]}"

    private fun jstDate(instant: Instant): LocalDate = instant.atOffset(JST).toLocalDate()

    private fun jstMidnight(date: LocalDate): Instant = date.atTime(LocalTime.MIDNIGHT).toInstant(JST)
}
