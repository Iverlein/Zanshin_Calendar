/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan


/**
 * The *byed rtsis*, the reckoning that follows the Kālacakra's abridged tantra, as the White Beryl
 * gives it from the 12th rab byung (vol. 1, ch. 4, pp. 31–32, img. 41–42; docs/sources/month-entries.md):
 * «བསྡུས་པའི་རྒྱུད་ཀྱི་རྗེས་འབྲངས་པའི། །བྱེད་པའི་རྩིས་ནི་བསྟན་པ་ལ། །རབ་བྱུང་ཉི་མའི་ཐོག་མ་ཡི། །མེ་ཡོས་ལ་སོགས་འདས་པའི་ལོ». The app uses it for one thing, as
 * WB's chapter 9 does: its true sun decides whether Rāhu seizes the moon or the sun ([Eclipses]).
 *
 * The months are counted from *nag pa*, the 3rd month, of the fire hare year 1687; the corrected
 * month (*zla dag*) is the months elapsed with their double and 19 divided by 65 added
 * («འོག་མ་མིག་བསྒྱུར་བུག་གཟུགས་བསྣན། །མདའ་རོས་ཐོབ་པ་ཟླ་དག་གོ»). The month's weekday moves 1;31,50 and starts 0;2,10 back, its
 * anomaly 2;1 (of 28 and 126) from 9;79 back, its sun 2;10,58,2,10 from 26;54,24,3,9 back; each date
 * adds 0;59,3,4,0 to the weekday and 0;4,21,5,9 to the sun, a whole step to the anomaly. The moon's
 * and the sun's equations are the true reckoning's steps (5,5,5,4,3,2,1 over fourteen, of 126;
 * 6,4,1 over six, of 135, from the sun less 6;45). The weekday and the sun are counted here in
 * units of a thirteenth of a breath, 280 800 to the day or the mansion, as the verses carry them
 * («འདོད་པས་བསྒྱུར་བ་ཆ་ཤས་ཏེ»): chu tshod, chu srang, breaths and thirteenths.
 *
 * A month's moon is the same in both reckonings, so the *byed rtsis*'s corrected month is the
 * app's ([Phugpa], Janson's E806) less [COUNT_OFFSET]: the 3rd month of 1687 is the true
 * reckoning's 10 898th and the *byed rtsis*'s 0th.
 */
object ByedRtsis {

    /** The app's month count of the 3rd month of 1687, the *byed rtsis*'s first. */
    const val COUNT_OFFSET: Long = 10898

    /** Units to a day, a mansion: 60 chu tshod of 60 chu srang of 6 breaths of 13. */
    const val UNIT: Long = 60L * 60 * 6 * 13
    private const val CHU_TSHOD: Long = UNIT / 60
    private const val CHU_SRANG: Long = CHU_TSHOD / 60
    private const val WEEK: Long = 7 * UNIT
    private const val CIRCLE: Long = 27 * UNIT

    private const val MONTH_WEEKDAY: Long = (1 * 3600 + 31 * 60 + 50) * CHU_SRANG
    private const val EPOCH_WEEKDAY: Long = (2 * 60 + 10) * CHU_SRANG
    private const val DATE_WEEKDAY: Long = (59 * 60 + 3) * CHU_SRANG + 4 * 13
    private const val MONTH_SUN: Long = ((2 * 3600 + 10 * 60 + 58) * 6 + 2) * 13L + 10
    private const val EPOCH_SUN: Long = ((26 * 3600 + 54 * 60 + 24) * 6 + 3) * 13L + 9
    private const val DATE_SUN: Long = ((4 * 60 + 21) * 6 + 5) * 13L + 9
    private const val MONTH_ANOMALY: Long = 2 * 126 + 1
    private const val EPOCH_ANOMALY: Long = 9 * 126 + 79

    private val MOON_STEPS = intArrayOf(5, 5, 5, 4, 3, 2, 1, 1, 2, 3, 4, 5, 5, 5)
    private val MOON_SUMS = intArrayOf(0, 5, 10, 15, 19, 22, 24, 25, 24, 22, 19, 15, 10, 5, 0)
    private val SUN_STEPS = intArrayOf(6, 4, 1, 1, 4, 6)
    private val SUN_SUMS = intArrayOf(0, 6, 10, 11, 10, 6, 0)

    /** The *byed rtsis*'s corrected month of the app's month count [n]. */
    fun count(n: Long): Long = n - COUNT_OFFSET

    /** The corrected month of month [number] of [year] by the verse's own count from *nag pa* 1687. */
    fun countOf(year: Int, number: Int): Long {
        val m = 12L * (year - 1687) + number - 3
        return m + Math.floorDiv(2 * m + 19, 65L)
    }

    /** The month's figures (*dhru ba*): weekday and sun in [UNIT]s, the anomaly in 126ths of its 28 steps. */
    data class Dhruva(val weekday: Long, val anomaly: Long, val sun: Long)

    /** The figures of the app's month count [n], at the start of its 1st date (p. 31, «སྟེང་འོག་རིལ་ཆའི་དྷྲུ་བའོ»). */
    fun dhruva(n: Long): Dhruva {
        val b = count(n)
        return Dhruva(
            weekday = Math.floorMod(b * MONTH_WEEKDAY - EPOCH_WEEKDAY, WEEK),
            anomaly = Math.floorMod(b * MONTH_ANOMALY - EPOCH_ANOMALY, 28L * 126),
            sun = Math.floorMod(b * MONTH_SUN - EPOCH_SUN, CIRCLE),
        )
    }

    /** One date reckoned: the true weekday and sun at its end, in [UNIT]s, the weekday from Saturday's daybreak. */
    data class Date(val weekday: Long, val sun: Long)

    /** Date [d] of the app's month count [n] (pp. 31–32). */
    fun date(n: Long, d: Int): Date {
        val m = dhruva(n)
        val meanWeekday = m.weekday + d * DATE_WEEKDAY
        val meanSun = m.sun + d * DATE_SUN

        // The moon (p. 31, «རིལ་པོར་ཚེས་བསྲེས་ཡིད་ཀྱིས་བགོ»): the anomaly's whole steps with the date, by fourteen; the odd ones subtract.
        val step = (m.anomaly / 126 + d).toInt()
        val part = m.anomaly % 126
        val r = step % 14
        val partial = part * MOON_STEPS[r] * CHU_TSHOD / 126
        val moon = MOON_SUMS[r] * CHU_TSHOD + if (r < 7) partial else -partial
        val moonEqu = if ((step / 14) % 2 == 0) moon else -moon

        // The sun (p. 32, «ཉི་བར་གཅིག་ལ་སྐར་དྲུག་དང་། །ཞེ་ལྔས་སྦྱངས་ལ»): less 6;45, a half-circle dropped makes it add.
        var a = Math.floorMod(meanSun - (6 * UNIT + 45 * CHU_TSHOD), CIRCLE)
        val half = a >= 13 * UNIT + 30 * CHU_TSHOD
        if (half) a -= 13 * UNIT + 30 * CHU_TSHOD
        val k = (a / (135 * CHU_TSHOD)).toInt()
        val sunPart = (a % (135 * CHU_TSHOD)) * SUN_STEPS[k] / (135 * CHU_TSHOD)
        val sun = SUN_SUMS[k] * CHU_TSHOD + if (k < 3) sunPart else -sunPart
        val sunEqu = if (half) sun else -sun

        return Date(Math.floorMod(meanWeekday + moonEqu + sunEqu, WEEK), Math.floorMod(meanSun + sunEqu, CIRCLE))
    }

    /** A weekday or sun figure in [UNIT]s as the texts write it, "w;ct,cs,b,f", for the tests. */
    internal fun figure(units: Long): String {
        val f = units % 13
        val b = units / 13 % 6
        val cs = units / 78 % 60
        val ct = units / CHU_TSHOD % 60
        return "${units / UNIT};$ct,$cs,$b,$f"
    }

    /** An anomaly in 126ths as the texts write it, "ril;cha", for the tests. */
    internal fun anomalyFigure(units: Long): String = "${units / 126};${units % 126}"
}
