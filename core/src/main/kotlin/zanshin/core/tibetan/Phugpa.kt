/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import zanshin.core.rational.Rational
import zanshin.core.rational.Rational.Companion.of
import zanshin.core.time.mod

/**
 * The Phugpa arithmetic of Svante Janson, "Tibetan Calendar Mathematics"
 * (2007, rev. 2014, 2022), epoch E806. Equation numbers refer to that paper.
 */
object Phugpa {
    private val M1 = of(167025, 5656)                 // (7.2) mean month
    private val M2 = of(11135, 11312)                 // (7.3) = M1 / 30
    private val M0 = of(2015501L * 5656 + 4783, 5656) // (7.4)
    private val S1 = of(65, 804)                      // (7.6)
    private val S2 = of(13, 4824)                     // (7.7) = S1 / 30
    private val S0 = of(743, 804)                     // (7.8)
    private val A1 = of(253, 3528)                    // (7.12)
    private val A0 = of(475, 3528)                    // (7.14)
    private val QUARTER = of(1, 4)                    // (7.19)

    /**
     * a₂, the moon's anomaly per lunar day. 1/28 (7.13) is what the
     * Men-Tsee-Khang almanac uses; Henning's exact 3781/105840 (7.24) moves
     * about one date a decade. SPEC §5.5 fixes the almanac value.
     */
    val A2_ALMANAC: Rational = of(1, 28)
    val A2_HENNING: Rational = of(3781, 105840)

    private const val EPOCH_YEAR = 806
    private const val EPOCH_MONTH = 3
    private const val BETA_STAR = 61                   // (5.4)

    private val MOON_TAB = intArrayOf(0, 5, 10, 15, 19, 22, 24, 25) // (7.18)
    private val SUN_TAB = intArrayOf(0, 6, 10, 11)                  // (7.21)

    /** A calendar month named by year, number and leap flag, with its true month count. */
    data class Month(val year: Int, val number: Int, val leap: Boolean, val count: Long)

    /**
     * True month count n of month [number] of [year] (5.2)–(5.9). For the leap
     * month, pass [leap] = true; it exists only when [hasLeap] says so.
     */
    fun trueMonthCount(year: Int, number: Int, leap: Boolean = false): Long {
        val mStar = 12L * (year - EPOCH_YEAR) + number - EPOCH_MONTH
        val ix = intercalationIndex(year, number)
        val base = Math.floorDiv(67 * mStar + BETA_STAR, 65L)
        val isLeapMonth = leap && ix >= 48 && ix <= 49
        require(!leap || isLeapMonth) { "no leap month $number in $year" }
        return if (ix >= 48 && !isLeapMonth) base + 1 else base
    }

    /** Intercalation index ix (5.7). */
    fun intercalationIndex(year: Int, number: Int): Int {
        val mStar = 12L * (year - EPOCH_YEAR) + number - EPOCH_MONTH
        return mod(2 * mStar + BETA_STAR, 65L).toInt()
    }

    /** Whether month [number] of [year] is preceded by a leap month of the same number (5.8). */
    fun hasLeap(year: Int, number: Int): Boolean = intercalationIndex(year, number) in 48..49

    /** Months of Tibetan year [year] in order, the leap month (if any) before its regular month. */
    fun monthsOf(year: Int): List<Month> = buildList {
        for (m in 1..12) {
            if (hasLeap(year, m)) add(Month(year, m, true, trueMonthCount(year, m, leap = true)))
            add(Month(year, m, false, trueMonthCount(year, m)))
        }
    }

    /**
     * true_date(d, n) (7.22): the instant, in days on the JD scale shifted to
     * begin at dawn, when lunar day [d] of true month count [n] ends.
     */
    fun trueDate(n: Long, d: Int, a2: Rational = A2_ALMANAC): Rational {
        val meanDate = M1 * n + M2 * d.toLong() + M0
        val meanSun = S1 * n + S2 * d.toLong() + S0
        val anomalyMoon = A1 * n + a2 * d.toLong() + A0
        val moonEqu = interpolate(anomalyMoon.frac() * 28, 28, ::moonTab)
        val sunEqu = interpolate((meanSun - QUARTER).frac() * 12, 12, ::sunTab)
        return meanDate + moonEqu / 60 - sunEqu / 60
    }

    /** Mean longitude of the sun at the end of lunar day [d], in revolutions, from (7.6)–(7.8): the *nyi ma bar pa*. */
    fun meanSun(n: Long, d: Int): Rational = (S1 * n + S2 * d.toLong() + S0).frac()

    /** True longitude of the sun at the end of lunar day [d], in revolutions (7.23). */
    fun trueSun(n: Long, d: Int): Rational {
        val meanSun = S1 * n + S2 * d.toLong() + S0
        val sunEqu = interpolate((meanSun - QUARTER).frac() * 12, 12, ::sunTab)
        return (meanSun - sunEqu / (27L * 60)).frac()
    }

    /**
     * Longitude of the moon at the start of the calendar day in which lunar
     * day [d] ends, in revolutions: (10.1) corrected back to daybreak by (10.2).
     */
    fun moonAtDaybreak(n: Long, d: Int, a2: Rational = A2_ALMANAC): Rational {
        val moonLunarDay = trueSun(n, d) + Rational.of(d.toLong(), 30)
        return (moonLunarDay - trueDate(n, d, a2).frac() / 27).frac()
    }

    /**
     * JD of the calendar day in which lunar day [d] of month count [n] ends
     * (8.1). Day 0 or below counts back into the preceding months, so
     * endJd(n, 0) is the end of day 30 of month n − 1.
     */
    fun endJd(n: Long, d: Int, a2: Rational = A2_ALMANAC): Long {
        var month = n
        var day = d
        while (day <= 0) {
            month -= 1
            day += 30
        }
        return trueDate(month, day, a2).floor()
    }

    /** JD of the first calendar day of month count [n]. */
    fun firstJd(n: Long, a2: Rational = A2_ALMANAC): Long = endJd(n - 1, 30, a2) + 1

    /** JD of the last calendar day of month count [n]. */
    fun lastJd(n: Long, a2: Rational = A2_ALMANAC): Long = endJd(n, 30, a2)

    /** Rough month count for [jd] from the mean motion, good to ±1. */
    fun approximateCount(jd: Long): Long = Math.floorDiv(
        Math.multiplyExact(jd - 2015501, 5656L) - 4783,
        167025L,
    )

    private fun moonTab(i: Int): Int {
        val k = mod(i, 28)
        return when {
            k <= 7 -> MOON_TAB[k]
            k <= 14 -> MOON_TAB[14 - k]
            k <= 21 -> -MOON_TAB[k - 14]
            else -> -MOON_TAB[28 - k]
        }
    }

    private fun sunTab(i: Int): Int {
        val k = mod(i, 12)
        return when {
            k <= 3 -> SUN_TAB[k]
            k <= 6 -> SUN_TAB[6 - k]
            k <= 9 -> -SUN_TAB[k - 6]
            else -> -SUN_TAB[12 - k]
        }
    }

    /** Linear interpolation of a periodic integer table at rational argument [x]. */
    private fun interpolate(x: Rational, period: Int, table: (Int) -> Int): Rational {
        val i = x.floor().toInt()
        val f = x - i.toLong()
        val lo = table(mod(i, period)).toLong()
        val hi = table(mod(i + 1, period)).toLong()
        return f * (hi - lo) + lo
    }
}
