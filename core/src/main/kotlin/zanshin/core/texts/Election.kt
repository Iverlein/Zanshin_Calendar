/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.texts

import zanshin.core.kyureki.Tone
import zanshin.core.tibetan.TibetanCalendar
import zanshin.core.tibetan.TibetanDay
import zanshin.core.tibetan.nectarHours
import zanshin.core.tibetan.ownDays
import zanshin.core.tibetan.personalDay
import zanshin.core.tibetan.personalMansions
import zanshin.core.tibetan.risingSign
import zanshin.core.time.SUPPORTED_RANGE
import zanshin.core.time.julianDayNumber
import zanshin.core.time.localDateOfJulianDayNumber
import java.time.LocalDate

/**
 * One civil day of an election (SPEC §5.14): the side the day's own weighing gives [work] (SPEC
 * §5.12), exactly as the day page gives it, with what decided it.
 */
class ElectionDay(val date: LocalDate, val day: TibetanDay, val summary: DaySummary, val work: Activity) {
    private val note: ActivityNote? = summary.activities.firstOrNull { it.activity == work }

    /** GOOD or BAD as [DaySummary.sideOf] gives it; null where no voice names the work, a blank day, never "neutral, so fine". */
    val side: Tone? = summary.sideOf(work)?.first

    /** The voices standing on [side], strongest first. */
    val standing: List<SummaryEntry> = summary.sideOf(work)?.second.orEmpty()

    /** The voice that decided [side]: the combination where its element pair names the work, otherwise the strongest naming it. */
    val decider: DayFactor? get() = note?.decider
    val by: VerdictBy? get() = note?.by

    /** The sum of the standing voices' weights ([DaySummary.weight]): the app's convention, the last order but the date's. */
    val weight: Int get() = note?.weight ?: 0

    /**
     * The person's days that take the day away from them for every work (WB vol. 2, p. 338; ROADMAP
     * E6): with a birth date set, the day is not offered, whatever [side] is.
     */
    val avoidAll: List<SummaryEntry> get() = summary.avoidAll

    /** The person's own days on it, marked and not weighed (SPEC §5.12). */
    val personal: List<SummaryEntry> get() = summary.personal

    /** Offered among the best days: good for the work, and not one of the person's days of [avoidAll]. */
    val offered: Boolean get() = side == Tone.GOOD && avoidAll.isEmpty()

    /**
     * Jupiter's nectar periods of the day, clock hours from 05:00 ([nectarHours]), where their reading
     * names the work good; none otherwise. Times within the day: they do not move its place.
     */
    val nectar: List<Int> get() = if (work in NECTAR_WORKS) nectarHours(day.weekday) else emptyList()

    private companion object {
        val NECTAR_WORKS: Set<Activity> = Activities.of(Texts.NECTAR_PERIODS.goodKeys)
    }
}

/**
 * One Tibetan month of an election's span, [days] those of the span in it, and the work's hours in
 * it (ROADMAP E3): the combination periods follow the month and the hour only (KP §9), so every
 * day of the month has the same, and they are given once. [good] holds the periods whose sign's
 * reading (WB vol. 2, pp. 371–376) names the work good, [avoid] those that name it to avoid, each as
 * runs of consecutive two-hour periods from the hare hour at 05:00. They choose the hour, never the
 * day (D1).
 */
class ElectionMonth(val days: List<ElectionDay>, val good: List<PeriodRun>, val avoid: List<PeriodRun>) {
    val first: TibetanDay get() = days.first().day
}

/**
 * The best days for one work (SPEC §5.14, ROADMAP E): each day of the span weighed exactly as its
 * page weighs it (SPEC §5.12), so that the election and the day page can never differ, then the
 * good days ordered by [ORDER]. The days to avoid are kept in [days] for the grid and given no list.
 */
class Election(val work: Activity, val months: List<ElectionMonth>) {
    val days: List<ElectionDay> get() = months.flatMap { it.days }

    /** The days offered for the work, best first. */
    val best: List<ElectionDay> by lazy { days.filter { it.offered }.sortedWith(ORDER) }

    companion object {
        /**
         * The order of the good days. The texts' order of strength as far as it goes: the days on
         * which the combination names the work good (WB vol. 2, p. 333), then by the strongest voice
         * naming it good, in the Phugpa order (KP rules 2–4, WB vol. 2, p. 376); within one rank a
         * day whose combination is lucky before one whose combination is unlucky or has no tone (WB
         * p. 333 makes it the day's result, so it orders days already on one side, and decides no
         * work it does not name). The app's convention only where the texts stop: then the sum of
         * the standing voices' weights, then the earlier day.
         */
        val ORDER: Comparator<ElectionDay> = compareBy<ElectionDay> { it.decider!!.rank }
            .thenBy { if (it.summary.combinationTone == Tone.GOOD) 0 else 1 }
            .thenByDescending { it.weight }
            .thenBy { it.day.jd }

        /** The longest span offered, in Tibetan months. */
        const val MAX_MONTHS = 12

        /**
         * The works an election is offered for: those some list of the Tibetan day's voices names
         * (SPEC §5.12), grouped by family in the summary line's order, each family's works in their
         * own order. "Everything" is left out: it is a list's word for the whole day, not a work
         * one chooses.
         */
        val WORKS: Map<ActivityFamily, List<Activity>> by lazy {
            val named = tibetanLists().flatMap { Activities.of(it.goodKeys) + Activities.of(it.avoidKeys) }.toSet() - Activity.EVERYTHING
            Activity.entries.filter { it in named }.groupBy { it.family }.toSortedMap().toMap()
        }

        /** The works of [WORKS], whatever their family. */
        val OFFERED: Set<Activity> by lazy { WORKS.values.flatten().toSet() }

        /** Every reading whose lists the Tibetan day's voices weigh, as [DaySummary.of] builds them. */
        internal fun tibetanLists(): List<Reading> =
            Texts.GREAT_COMBINATION.values + Texts.ELEMENT_PAIR.values + Texts.RAHU.values + Texts.RAHU_MONTH.values +
                Texts.WEEKDAY.values + Texts.ELECTIONAL_WEEKDAY.values + Texts.MANSION.values +
                Texts.COMBINATION_DAY.values + Texts.GTSUG_LAG_DAY.values + Texts.BURNING_DATE +
                Texts.LUNAR_DATE + Texts.ELECTIONAL_DATE.values + Texts.HAIRCUT_LIST +
                Texts.KARANA.values + Texts.YOGA.values + Texts.ELECTIONAL_ANIMAL.values + Texts.ELECTIONAL_TRIGRAM.values

        /**
         * The combination periods of Tibetan month [month] that name [work] on [side], as runs of
         * consecutive two-hour periods counted from the hare hour at 05:00: the work's own hours
         * first ([Texts.WORKS_SIGN], WB's chapter 34), its particular case, then the sign's general
         * reading without what they decide the other way ([Texts.period]) (SPEC §5.13, ROADMAP E4).
         */
        fun hours(month: Int, work: Activity, side: Tone): List<PeriodRun> {
            val runs = mutableListOf<PeriodRun>()
            for (hour in 0 until 12) {
                val sign = risingSign(month, hour)
                val reading = Texts.period(sign).second
                val own = Texts.WORKS_SIGN.getValue(sign)
                val good = work in Activities.of(reading.goodKeys + own.goodKeys)
                val avoid = work in Activities.of(reading.avoidKeys + own.avoidKeys)
                // A sign whose reading names the work both ways says nothing on it, as a voice's lists do (SPEC §5.12).
                val named = if (side == Tone.GOOD) good && !avoid else avoid && !good
                if (!named) continue
                val last = runs.lastOrNull()
                if (last != null && last.first + last.count == hour) {
                    runs[runs.lastIndex] = last.copy(count = last.count + 1, signs = last.signs + sign)
                } else {
                    runs += PeriodRun(hour, 1, side, listOf(sign))
                }
            }
            return runs
        }
    }
}

/** A day of a span and its weighing, computed once and read for every work. */
class SpanDay(val date: LocalDate, val day: TibetanDay, val summary: DaySummary)

/**
 * The days an election runs over: from [from], the shown day, to the end of the [months]th Tibetan
 * month counting [from]'s own as the first, up to twelve (ROADMAP E1), and never past the end of
 * 2100, the date picker's range. Each day weighed once, with the person's days where a birth date
 * is set, so that every work's election reads the same weighing.
 */
class ElectionSpan private constructor(val from: LocalDate, val months: Int, val birth: LocalDate?, val days: List<SpanDay>) {
    /** The election for [work] over the span. */
    fun election(work: Activity): Election {
        val months = days.groupBy { Triple(it.day.year, it.day.month, it.day.leapMonth) }.values.map { month ->
            val number = month.first().day.month
            ElectionMonth(
                month.map { ElectionDay(it.date, it.day, it.summary, work) },
                Election.hours(number, work, Tone.GOOD),
                Election.hours(number, work, Tone.BAD),
            )
        }
        return Election(work, months)
    }

    companion object {
        fun of(from: LocalDate, months: Int, birth: LocalDate? = null): ElectionSpan {
            require(months in 1..Election.MAX_MONTHS) { "a span runs one to ${Election.MAX_MONTHS} Tibetan months" }
            val start = from.coerceIn(SUPPORTED_RANGE.start, SUPPORTED_RANGE.endInclusive)
            val born = birth?.let { TibetanCalendar.of(it) }
            val days = mutableListOf<SpanDay>()
            var jd = start.julianDayNumber()
            val last = SUPPORTED_RANGE.endInclusive.julianDayNumber()
            var seen = 0
            var month: Triple<Int, Int, Boolean>? = null
            while (jd <= last) {
                val day = TibetanCalendar.of(jd)
                val key = Triple(day.year, day.month, day.leapMonth)
                if (key != month) {
                    if (++seen > months) break
                    month = key
                }
                val summary = DaySummary.of(
                    day,
                    personalDay = born?.let { personalDay(it.yearAnimal, day.weekday) },
                    personalMansions = born?.let { personalMansions(it.yearAnimal, day.mansion) }.orEmpty(),
                    ownDays = born?.let { ownDays(it, day) }.orEmpty(),
                )
                days += SpanDay(localDateOfJulianDayNumber(jd), day, summary)
                jd++
            }
            return ElectionSpan(start, months, birth, days)
        }
    }
}
