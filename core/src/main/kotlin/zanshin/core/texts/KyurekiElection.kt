/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.texts

import zanshin.core.kyureki.Kyureki
import zanshin.core.kyureki.KyurekiDay
import zanshin.core.kyureki.Rekichu
import zanshin.core.kyureki.Tone
import zanshin.core.time.SUPPORTED_RANGE
import java.time.LocalDate

/**
 * One civil day of a 旧暦 election (SPEC §7.6, ROADMAP E5): the annotations of the day's brief
 * (SPEC §7.5) that name [work] good and those that name it to avoid, after the lower band's own
 * rules have set some aside, exactly as the day's breakdown lists them. An annotation whose list
 * names everything names the work too: the almanac's 万事 is every work.
 */
class KyurekiElectionDay(val date: LocalDate, val day: KyurekiDay, val summary: DaySummary, val work: Activity) {
    /** The annotations naming the work good, in the almanac's order. */
    val good: List<SummaryEntry> = naming(summary, work, good = true)

    /** The annotations naming the work to avoid. */
    val avoid: List<SummaryEntry> = naming(summary, work, good = false)

    /** GOOD where only the good side names the work, BAD where only the avoid side does, MIXED where both do; null where none names it. */
    val side: Tone? = when {
        good.isNotEmpty() && avoid.isNotEmpty() -> Tone.MIXED
        good.isNotEmpty() -> Tone.GOOD
        avoid.isNotEmpty() -> Tone.BAD
        else -> null
    }

    /** The person's 三箇の悪日 on it, with a birth date set: they name every work to avoid. */
    val personal: List<SummaryEntry> get() = summary.personal
}

/** The annotations of [summary] that name [work] on one side, its own lists first, then those naming everything. */
private fun naming(summary: DaySummary, work: Activity, good: Boolean): List<SummaryEntry> =
    summary.activities.filter { it.activity == work || it.activity == Activity.EVERYTHING }
        .sortedBy { if (it.activity == work) 0 else 1 }
        .flatMap { if (good) it.good else it.avoid }
        .distinct()

/** One 旧暦 month of an election's span, [days] those of the span in it. */
class KyurekiElectionMonth(val days: List<KyurekiElectionDay>) {
    val first: KyurekiDay get() = days.first().day
}

/**
 * The 旧暦 election for one work (SPEC §7.6, ROADMAP E5): the days some annotation names the
 * work good on and none names it to avoid, in date order, and the disputed days apart. Unranked:
 * no published rule ranks one kind of annotation above another (SPEC §7.5), so the almanac gives no
 * "best" day. The days to avoid are kept in [days] for the grid and given no list.
 */
class KyurekiElection(val work: Activity, val months: List<KyurekiElectionMonth>) {
    val days: List<KyurekiElectionDay> get() = months.flatMap { it.days }

    /** The days named good for the work and to avoid by none, in date order. */
    val good: List<KyurekiElectionDay> by lazy { days.filter { it.side == Tone.GOOD } }

    /** The days named both ways, in date order. */
    val disputed: List<KyurekiElectionDay> by lazy { days.filter { it.side == Tone.MIXED } }

    companion object {
        /**
         * The works a 旧暦 election is offered for: those some list of the day's brief names, the
         * rokuyō's, the 十二直's, the 二十八宿's and the 選日's, 暦注下段's and 縁日's (SPEC §7.5),
         * grouped by family in the summary line's order. "Everything" is left out, as in the
         * Tibetan election: a list's word for the whole day, not a work one chooses.
         */
        val WORKS: Map<ActivityFamily, List<Activity>> by lazy {
            val lists = Texts.ROKUYO.values + Texts.CHOKU.values + Texts.SHUKU.values + Texts.SENJITSU.values
            val named = lists.flatMap { Activities.of(it.goodKeys) + Activities.of(it.avoidKeys) }.toSet() - Activity.EVERYTHING
            Activity.entries.filter { it in named }.groupBy { it.family }.toSortedMap().toMap()
        }

        /** The works of [WORKS], whatever their family. */
        val OFFERED: Set<Activity> by lazy { WORKS.values.flatten().toSet() }
    }
}

/**
 * The days a 旧暦 election runs over: from [from], the shown day, to the end of the [months]th 旧暦
 * month counting [from]'s own as the first, up to [Election.MAX_MONTHS], and never past the end of
 * 2100. Each day's brief built once, with the person's 三箇の悪日 where a birth date is set, so that
 * every work's election reads the same listing.
 */
class KyurekiSpan private constructor(val from: LocalDate, val months: Int, val birth: LocalDate?, val days: List<Pair<KyurekiDay, DaySummary>>) {
    /** The election for [work] over the span. */
    fun election(work: Activity): KyurekiElection {
        val months = days.groupBy { (d, _) -> Triple(d.year, d.month, d.leapMonth) }.values.map { month ->
            KyurekiElectionMonth(month.map { (d, s) -> KyurekiElectionDay(d.date, d, s, work) })
        }
        return KyurekiElection(work, months)
    }

    companion object {
        fun of(from: LocalDate, months: Int, birth: LocalDate? = null): KyurekiSpan {
            require(months in 1..Election.MAX_MONTHS) { "a span runs one to ${Election.MAX_MONTHS} months" }
            val start = from.coerceIn(SUPPORTED_RANGE.start, SUPPORTED_RANGE.endInclusive)
            val days = mutableListOf<Pair<KyurekiDay, DaySummary>>()
            var date = start
            var seen = 0
            var month: Triple<Int, Int, Boolean>? = null
            while (date <= SUPPORTED_RANGE.endInclusive) {
                val day = Kyureki.of(date)
                val key = Triple(day.year, day.month, day.leapMonth)
                if (key != month) {
                    if (++seen > months) break
                    month = key
                }
                days += day to DaySummary.of(day, Rekichu.of(date, birth))
                date = date.plusDays(1)
            }
            return KyurekiSpan(start, months, birth, days)
        }
    }
}
