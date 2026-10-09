/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.cli

import zanshin.core.astro.Astro
import zanshin.core.astro.Place
import zanshin.core.astro.SunTimes
import zanshin.core.kyureki.Kyureki
import zanshin.core.kyureki.Tone
import zanshin.core.texts.Activity
import zanshin.core.texts.ElectionSpan
import zanshin.core.texts.KyurekiSpan
import zanshin.core.texts.VerdictBy
import zanshin.core.tibetan.Repetition
import zanshin.core.tibetan.TibetanCalendar
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

private val HOURS = DateTimeFormatter.ofPattern("HH:mm")

private const val USAGE = """usage:
  zanshin [YYYY-MM-DD] [--lat L --lon L --tz ZONE]   one day in both calendars
  zanshin --sui YEAR                                 kyūreki months from month 11 of YEAR
  zanshin --tibetan-year YEAR                        Tibetan months of YEAR
  zanshin --elect WORK [YYYY-MM-DD] [--months N]     the best days for a work (an Activity name,
          [--birth YYYY-MM-DD] [--all]                 e.g. HAIRCUTS), from the date for N Tibetan months;
                                                     --all gives every day with its deciding voice;
                                                     --kyureki: the 旧暦 election, N 旧暦 months, unranked
The place defaults to Kyoto."""

fun main(args: Array<String>) {
    val options = args.toList()
    when {
        "--help" in options || "-h" in options -> println(USAGE)
        "--sui" in options -> printSui(options.valueAfter("--sui")!!.toInt())
        "--tibetan-year" in options -> printTibetanYear(options.valueAfter("--tibetan-year")!!.toInt())
        "--elect" in options -> printElection(options)
        else -> printDay(options)
    }
}

private fun List<String>.valueAfter(flag: String): String? = indexOf(flag).takeIf { it >= 0 }?.let { getOrNull(it + 1) }

private fun printDay(options: List<String>) {
    val date = options.firstOrNull { !it.startsWith("--") && it.matches(Regex("""\d{4}-\d{2}-\d{2}""")) }
        ?.let(LocalDate::parse) ?: LocalDate.now()
    val zone = ZoneId.of(options.valueAfter("--tz") ?: "Asia/Tokyo")
    val place = Place(
        options.valueAfter("--lat")?.toDouble() ?: 35.0116,
        options.valueAfter("--lon")?.toDouble() ?: 135.7681,
        zone,
    )

    val t = TibetanCalendar.of(date)
    val k = Kyureki.of(date)
    val sun = SunTimes.of(date, place)
    val evening = ZonedDateTime.of(date, LocalTime.of(21, 0), zone).toInstant()

    println(date.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy")))
    println()
    println("Tibetan (Phugpa)")
    println("  day ${t.day} of the ${if (t.leapMonth) "leap " else ""}${ordinal(t.month)} month · ${t.monthNames.wylie}")
    when (t.repetition) {
        Repetition.FIRST_OF_TWO -> println("  first of two days numbered ${t.day}")
        Repetition.SECOND_OF_TWO -> println("  second of two days numbered ${t.day}")
        Repetition.NONE -> Unit
    }
    t.omittedBefore?.let { println("  day $it is omitted") }
    t.holiday?.let { println("  holiday: ${it.name}${it.movedFromDay?.let { d -> " (moved from day $d)" } ?: ""}") }
    println("  ${t.yearElement.label()} ${t.yearAnimal.label()} year · royal year ${t.royalYear} · rabjung ${t.rabjungCycle}/${t.rabjungYear}")
    println("  ${t.weekday.english} · ${t.weekday.planet} · ${t.dayElement.label()} ${t.dayAnimal.label()} · ${t.dayGender.name.lowercase()}")
    println()
    println("旧暦 (Tenpō)")
    println("  day ${k.day} of the ${if (k.leapMonth) "leap " else ""}${ordinal(k.month)} month · ${k.monthName} ${k.monthNameRomaji} · year ${k.year}")
    println("  rokuyō ${k.rokuyo.kanji} ${k.rokuyo.romaji}")
    println("  term ${k.currentTerm.kanji} ${k.currentTerm.romaji} since ${k.currentTermStart}, next ${k.nextTermStart}" +
        if (k.termBeginning != null) " · begins today" else "")
    println("  kanshi day ${k.dayKanshi} · year ${k.yearKanshi}")
    k.festival?.let { println("  festival: ${it.kanji} ${it.romaji}") }
    k.gregorianFestival?.let { println("  festival (Gregorian date): ${it.kanji} ${it.romaji}") }
    println("  moon elongation at 21:00: ${"%.1f".format(Astro.moonElongationDeg(evening))}°")
    println()
    println("Sky at ${place.latitude}, ${place.longitude} (${place.zone})")
    println("  sunrise ${sun.sunrise?.format(HOURS) ?: "—"} · noon ${sun.transit.format(HOURS)} " +
        "(${"%.1f".format(sun.altitudeAtTransitDeg)}°) · sunset ${sun.sunset?.format(HOURS) ?: "—"}")
}

private fun printElection(options: List<String>) {
    val work = Activity.valueOf(options.valueAfter("--elect")!!)
    val from = options.firstOrNull { it.matches(Regex("""\d{4}-\d{2}-\d{2}""")) && it != options.valueAfter("--birth") }
        ?.let(LocalDate::parse) ?: LocalDate.now()
    if ("--kyureki" in options) return printKyurekiElection(work, from, options)
    val span = ElectionSpan.of(from, options.valueAfter("--months")?.toInt() ?: 1, options.valueAfter("--birth")?.let(LocalDate::parse))
    val election = span.election(work)
    fun clock(hour: Int, count: Int) = "%02d:00–%02d:00".format((5 + 2 * hour) % 24, (5 + 2 * (hour + count)) % 24)
    println("${work.english}, ${span.days.first().date} – ${span.days.last().date}")
    for (m in election.months) {
        val d = m.first
        println()
        println("${if (d.leapMonth) "leap " else ""}${ordinal(d.month)} month")
        println("  days: " + m.days.joinToString(" ") { e ->
            "${e.day.day}${when { e.avoidAll.isNotEmpty() -> "x"; e.side == Tone.GOOD -> "+"; e.side == Tone.BAD -> "-"; else -> "." }}"
        })
        if (m.good.isNotEmpty()) println("  hours good: " + m.good.joinToString { clock(it.first, it.count) })
        if (m.avoid.isNotEmpty()) println("  hours to avoid: " + m.avoid.joinToString { clock(it.first, it.count) })
    }
    if ("--all" in options) {
        println()
        println("Every day")
        for (e in election.days) {
            val side = when (e.side) { Tone.GOOD -> "good"; Tone.BAD -> "avoid"; else -> "—" }
            println("  ${e.date}  ${ordinal(e.day.month)}/${e.day.day}  ${e.day.weekday.english}  $side" +
                (e.decider?.let { " by ${it.english}: " + e.standing.joinToString { s -> "${s.kanji} (${s.english})" } } ?: "") +
                (if (e.avoidAll.isNotEmpty()) " · every work to avoid, for you: " + e.avoidAll.joinToString { it.kanji } else ""))
        }
    }
    println()
    println("Best first")
    for (e in election.best) {
        val by = if (e.by == VerdictBy.COMBINATION) "by the combination" else "by ${e.standing.first().kanji} (${e.decider!!.english})"
        val combination = e.summary.combinationTone?.let { if (it == Tone.GOOD) "lucky" else "unlucky" } ?: "no tone"
        println("  ${e.date}  ${ordinal(e.day.month)}/${e.day.day}  $by · combination $combination · weight ${e.weight}" +
            (if (e.nectar.isNotEmpty()) " · nectar ${e.nectar.joinToString { "%02d:00".format((5 + it) % 24) }}" else ""))
    }
}

/** The 旧暦 election (ROADMAP E5): each day's annotations naming the work, the good days in date order, the disputed apart. */
private fun printKyurekiElection(work: Activity, from: LocalDate, options: List<String>) {
    val span = KyurekiSpan.of(from, options.valueAfter("--months")?.toInt() ?: 1, options.valueAfter("--birth")?.let(LocalDate::parse))
    val election = span.election(work)
    fun names(e: List<zanshin.core.texts.SummaryEntry>) = e.joinToString(" ") { it.kanji }
    println("${work.english}, ${span.days.first().first.date} – ${span.days.last().first.date} (旧暦)")
    for (m in election.months) {
        val d = m.first
        println()
        println("${if (d.leapMonth) "leap " else ""}${ordinal(d.month)} month")
        println("  days: " + m.days.joinToString(" ") { e ->
            "${e.day.day}${when (e.side) { Tone.GOOD -> "+"; Tone.BAD -> "-"; Tone.MIXED -> "±"; else -> "." }}"
        })
    }
    if ("--all" in options) {
        println()
        println("Every day")
        for (e in election.days) {
            println("  ${e.date}  ${ordinal(e.day.month)}/${e.day.day}  ${e.day.rokuyo.kanji}" +
                (if (e.good.isNotEmpty()) "  good: ${names(e.good)}" else "") + (if (e.avoid.isNotEmpty()) "  avoid: ${names(e.avoid)}" else ""))
        }
    }
    println()
    println("Named good, in date order")
    for (e in election.good) println("  ${e.date}  ${ordinal(e.day.month)}/${e.day.day}  ${names(e.good)}")
    println()
    println("Disputed")
    for (e in election.disputed) println("  ${e.date}  ${ordinal(e.day.month)}/${e.day.day}  good: ${names(e.good)} · avoid: ${names(e.avoid)}")
}

private fun printSui(year: Int) {
    for (m in Kyureki.sui(year)) {
        println("${m.start}  ${if (m.leap) "閏" else " "}${m.number.toString().padStart(2)}月  year ${m.year}  (${m.end.toEpochDay() - m.start.toEpochDay()} days)")
    }
}

private fun printTibetanYear(year: Int) {
    println("Losar ${TibetanCalendar.losar(year)}")
    for (m in zanshin.core.tibetan.Phugpa.monthsOf(year)) {
        val first = zanshin.core.time.localDateOfJulianDayNumber(zanshin.core.tibetan.Phugpa.firstJd(m.count))
        val last = zanshin.core.time.localDateOfJulianDayNumber(zanshin.core.tibetan.Phugpa.lastJd(m.count))
        println("${if (m.leap) "leap " else "     "}${m.number.toString().padStart(2)}  $first – $last")
    }
}

private fun Enum<*>.label(): String = name.lowercase().replaceFirstChar { it.uppercase() }

private fun ordinal(n: Int): String {
    val suffix = if (n % 100 in 11..13) "th" else when (n % 10) { 1 -> "st"; 2 -> "nd"; 3 -> "rd"; else -> "th" }
    return "$n$suffix"
}
