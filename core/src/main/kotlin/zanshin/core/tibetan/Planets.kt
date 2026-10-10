/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * The arithmetic follows Edward Henning's Tibetan calendar software (TCG 1.06,
 * t3.c and t2.c, http://www.kalacakra.org/calendar/os_tib.htm), published
 * under this licence, which is kept with it:
 *
 *   Copyright (c) 2009-2013 Edward Henning
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in all
 *   copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *   SOFTWARE.
 */

package zanshin.core.tibetan

/** The five planets (*gza' lnga*) of the true reckoning. */
enum class Planet { MARS, JUPITER, SATURN, MERCURY, VENUS }

/**
 * The five planets' and Rāhu's places by the Phugpa true reckoning (*grub rtsis*), as Edward Henning's
 * software reckons them for his generalised Phugpa epoch (*Kalacakra and the Tibetan Calendar*,
 * pp. 57–96, cited as KTC in its source; docs/sources/day-letters.md). Each place is in mansions,
 * chu tshod, chu srang, breaths and the planet's own fractions, digit by digit as the texts work,
 * so that the app's places are Henning's to the last unit (`PlanetsTest`).
 *
 * The planets go by the general day (*spyi zhag*), the count of days since the epoch: from a
 * planet's day in its cycle (*sgos zhag*) its mean place (*dal bar*), corrected to the slow place
 * (*dal dag*), then by the mean sun (*drag po'i rkang 'dzin*) to its apparent place (*myur dag*);
 * Mercury and Venus the other way, from the sun. Rāhu goes back round the mansions in 230 months.
 */
object Planets {

    /** A place: six digits, of radices 27, 60, 60, 6, [f4] and [f5], the mansion first. */
    private class Place(val d: LongArray, val f4: Long, val f5: Long) {
        val mansion: Int get() = d[0].toInt()
    }

    private fun mul(a: LongArray, x: Long, f4: Long, f5: Long): LongArray {
        val b = LongArray(6) { a[it] * x }
        b[4] += b[5] / f5; b[5] %= f5
        b[3] += b[4] / f4; b[4] %= f4
        b[2] += b[3] / 6; b[3] %= 6
        b[1] += b[2] / 60; b[2] %= 60
        b[0] += b[1] / 60; b[1] %= 60
        return b
    }

    private fun div(a: LongArray, x: Long, f4: Long, f5: Long): LongArray {
        val b = a.copyOf()
        val radix = longArrayOf(60, 60, 6, f4, f5)
        for (i in 0 until 5) {
            val r = b[i] % x
            b[i] /= x
            b[i + 1] += r * radix[i]
        }
        b[5] /= x
        return b
    }

    private fun sub6(a: LongArray, c: LongArray, f4: Long, f5: Long): LongArray {
        val x = a.copyOf()
        val out = LongArray(6)
        val radix = longArrayOf(27, 60, 60, 6, f4, f5)
        for (i in 5 downTo 1) {
            out[i] = x[i] - c[i]
            if (out[i] < 0) { out[i] += radix[i]; x[i - 1] -= 1 }
        }
        out[0] = x[0] - c[0]
        if (out[0] < 0) out[0] += 27
        return out
    }

    private fun add6(a: LongArray, c: LongArray, f4: Long, f5: Long): LongArray {
        val out = LongArray(6)
        var r = a[5] + c[5]; out[5] = r % f5
        r = a[4] + c[4] + r / f5; out[4] = r % f4
        r = a[3] + c[3] + r / f4; out[3] = r % 6
        r = a[2] + c[2] + r / 6; out[2] = r % 60
        r = a[1] + c[1] + r / 60; out[1] = r % 60
        r = a[0] + c[0] + r / 60; out[0] = r % 27
        return out
    }

    /** The five-digit forms (the last digit of radix [f4], none after it), as Henning's sub_gen and add_gen. */
    private fun sub5(a: LongArray, c: LongArray, f4: Long): LongArray = sub6(a.copyOf().also { it[5] = 0 }, c.copyOf().also { it[5] = 0 }, f4, 1).also { it[5] = a[5] }
    private fun add5(a: LongArray, c: LongArray, f4: Long): LongArray = add6(a.copyOf().also { it[5] = 0 }, c.copyOf().also { it[5] = 0 }, f4, 1).also { it[5] = a[5] }

    private fun meanPlace(day: Long, cycle: Long, f4: Long): LongArray = div(longArrayOf(day * 27, 0, 0, 0, 0, 0), cycle, f4, 1)

    // The slow equation (KTC 59): the place less the planet's factor, its sixth of a half-circle, the table.
    private fun slow(mean: LongArray, factor: LongArray, bye: IntArray, dom: IntArray, f4: Long): LongArray {
        val a = sub5(mean, factor, f4)
        var test = a[0] * 60 + a[1]
        val half = test >= 13 * 60 + 30
        if (half) test -= 13 * 60 + 30
        val rem = test % 135
        var quo = (test / 135).toInt()
        if (quo == 0) quo = 6
        var l = longArrayOf(0, rem, mean[2], mean[3], mean[4], 0)
        l = div(mul(l, bye[quo - 1].toLong(), f4, 1), 135, f4, 1)
        val b = longArrayOf(0, dom[quo - 1].toLong(), 0, 0, 0, 0)
        val c = if (quo in 3..5) sub5(b, l, f4) else add5(b, l, f4)
        return if (!half) sub5(mean, c, f4) else add5(mean, c, f4)
    }

    /** Turns a place's fourth digit from radix [from] into [to], the rest into a fifth digit of radix [from]. */
    private fun rescale(a: LongArray, to: Long, from: Long): LongArray {
        val b = a.copyOf()
        val x = b[4] * to
        b[4] = x / from
        b[5] = x % from
        return b
    }

    private class Fast(val bye1: IntArray, val dom1: IntArray, val bye2: IntArray, val dom2: IntArray, val sub1: IntRange, val sub2: IntRange)

    // The fast equation (KTC 65–86): the difference of the two places, its half-circle, the tables of fourteen.
    private fun fast(from: LongArray, base: LongArray, toward: LongArray, t: Fast, f4: Long, f5: Long): LongArray {
        var a = sub6(from, toward, f4, f5)
        val half = a[0] * 60 + a[1] >= 13 * 60 + 30
        if (half) a = sub5(a, longArrayOf(13, 30, 0, 0, 0, 0), f5).also { it[5] = a[5] }
        var quo = a[0].toInt()
        var rem = a[1]
        val c: LongArray
        if (!half) {
            val twice = quo == 13
            if (quo == 0) quo = 14
            var l = longArrayOf(0, a[1], a[2], a[3], a[4], a[5])
            l = mul(l, t.bye1[quo - 1].toLong(), f4, f5)
            if (twice) l = mul(l, 2, f4, f5)
            l = div(l, 60, f4, f5)
            val dom = t.dom1[quo - 1].toLong()
            val b = longArrayOf(dom / 60, dom % 60, 0, 0, 0, 0)
            c = if (quo in t.sub1) sub6(b, l, f4, f5) else add6(b, l, f4, f5)
        } else {
            val twice = a[0] == 0L && a[1] < 30
            if (a[1] >= 30) { quo += 1; rem = a[1] - 30 } else if (a[0] != 0L) rem = a[1] + 30
            if (quo == 0) quo = 14
            var l = longArrayOf(0, rem, a[2], a[3], a[4], a[5])
            l = mul(l, t.bye2[quo - 1].toLong(), f4, f5)
            if (twice) l = mul(l, 2, f4, f5)
            l = div(l, 60, f4, f5)
            val dom = t.dom2[quo - 1].toLong()
            val b = longArrayOf(dom / 60, dom % 60, 0, 0, 0, 0)
            c = if (quo in t.sub2) sub6(b, l, f4, f5) else add6(b, l, f4, f5)
        }
        return if (half) sub6(base, c, f4, f5) else add6(base, c, f4, f5)
    }

    private fun ints(vararg x: Int) = x

    private val MARS = Fast(
        ints(23, 23, 23, 21, 21, 18, 15, 11, 3, 11, 38, 80, 53, 24), ints(24, 47, 70, 93, 114, 135, 153, 168, 179, 182, 171, 133, 53, 0),
        ints(80, 38, 11, 3, 11, 15, 18, 21, 21, 23, 23, 23, 24, 53), ints(53, 133, 171, 182, 179, 168, 153, 135, 114, 93, 70, 47, 24, 0),
        10..13, 4..13,
    )
    private val JUPITER = Fast(
        ints(10, 9, 8, 6, 6, 2, 1, 3, 6, 9, 11, 16, 7, 10), ints(10, 20, 29, 37, 43, 49, 51, 52, 49, 43, 34, 23, 7, 0),
        ints(16, 11, 9, 6, 3, 1, 2, 6, 6, 8, 9, 10, 10, 7), ints(7, 23, 34, 43, 49, 52, 51, 49, 43, 37, 29, 20, 10, 0),
        8..13, 6..13,
    )
    private val SATURN = Fast(
        ints(5, 5, 4, 4, 2, 2, 0, 2, 4, 5, 6, 8, 3, 6), ints(6, 11, 16, 20, 24, 26, 28, 28, 26, 22, 17, 11, 3, 0),
        ints(8, 6, 5, 4, 2, 0, 2, 2, 4, 4, 5, 5, 6, 3), ints(3, 11, 17, 22, 26, 28, 28, 26, 24, 20, 16, 11, 6, 0),
        8..13, 6..13,
    )
    private val MERCURY = Fast(
        ints(16, 15, 14, 13, 11, 7, 5, 0, 4, 11, 20, 28, 34, 16), ints(16, 32, 47, 61, 74, 85, 92, 97, 97, 93, 82, 62, 34, 0),
        ints(28, 20, 11, 4, 0, 5, 7, 11, 13, 14, 15, 16, 16, 34), ints(34, 62, 82, 93, 97, 97, 92, 85, 74, 61, 47, 32, 16, 0),
        9..13, 5..13,
    )
    private val VENUS = Fast(
        ints(25, 25, 24, 24, 22, 22, 18, 15, 8, 6, 30, 99, 73, 25), ints(25, 50, 75, 99, 123, 145, 167, 185, 200, 208, 202, 172, 73, 0),
        ints(99, 30, 6, 8, 15, 18, 22, 22, 24, 24, 25, 25, 25, 73), ints(73, 172, 202, 208, 200, 185, 167, 145, 123, 99, 75, 50, 25, 0),
        10..13, 4..13,
    )

    /** The generalised Phugpa epoch's general day: the Julian day number less this. */
    const val EPOCH_JD: Long = 1355847
    private const val SUN_FRAC = 149209L

    /** The apparent places (*myur dag*) of the five planets on the civil day of Julian day number [jd]. */
    fun places(jd: Long): Map<Planet, Int> = exact(jd).mapValues { it.value.mansion }

    private fun exact(jd: Long): Map<Planet, Place> {
        val sz = jd - EPOCH_JD
        // The mean sun of the planets' reckoning, drag po'i rkang 'dzin (KTC 63).
        val drag = Math.floorMod(sz * 18382 + 6663418, 6714405L)
        val sun = div(longArrayOf(27 * drag, 0, 0, 0, 0, 0), 6714405, SUN_FRAC, 1)

        fun outer(add: Long, cycle: Long, frac: Long, factor: Long, bye: IntArray, dom: IntArray, t: Fast): Place {
            val mean = meanPlace(Math.floorMod(sz + add, cycle), cycle, frac)
            val slowPlace = slow(mean, longArrayOf(factor / 60, factor % 60, 0, 0, 0, 0), bye, dom, frac)
            val s = rescale(sun, frac, SUN_FRAC)
            return Place(fast(s, slowPlace, slowPlace, t, frac, SUN_FRAC), frac, SUN_FRAC)
        }
        fun inner(gz: Long, cycle: Long, frac: Long, factor: Long, bye: IntArray, dom: IntArray, t: Fast): Place {
            val mean = meanPlace(gz, cycle, frac)
            val slowSun = rescale(slow(sun, longArrayOf(factor / 60, factor % 60, 0, 0, 0, 0), bye, dom, SUN_FRAC), frac, SUN_FRAC)
            return Place(fast(mean, slowSun, slowSun, t, frac, SUN_FRAC), frac, SUN_FRAC)
        }
        return mapOf(
            Planet.MARS to outer(4, 687, 229, 9 * 60 + 30, ints(18, 7, 7, 18, 25, 25), ints(25, 43, 50, 43, 25, 0), MARS),
            Planet.JUPITER to outer(511, 4332, 361, 12 * 60, ints(9, 3, 3, 9, 11, 11), ints(11, 20, 23, 20, 11, 0), JUPITER),
            Planet.SATURN to outer(2995, 10766, 5383, 18 * 60, ints(15, 6, 6, 15, 22, 22), ints(22, 37, 43, 37, 22, 0), SATURN),
            Planet.MERCURY to inner(Math.floorMod(sz * 100 + 2080, 8797L), 8797, 8797, 16 * 60 + 30, ints(7, 3, 3, 7, 10, 10), ints(10, 17, 20, 17, 10, 0), MERCURY),
            Planet.VENUS to inner(Math.floorMod(sz * 10 + 277, 2247L), 2247, 749, 6 * 60, ints(4, 1, 1, 4, 5, 5), ints(5, 9, 10, 9, 5, 0), VENUS),
        )
    }

    /** Henning's true month count (*zla dag*) of the generalised epoch less the app's ([Phugpa], Janson's E806): month 37239 is the 11th, leap, of 2010. */
    const val MONTH_OFFSET: Long = 22338
    private const val RAHU_PART = 93L

    /**
     * Rāhu's head (*sgra gcan gdong*) on lunar date [date] of the month of count [monthCount] (KTC 96):
     * it goes back round the 27 mansions in 230 months, 0;0,14,0,12 a date (the last digit of radix 23),
     * from the whole circle, as Henning reckons it for the 15th and the 30th; the app for every date.
     */
    /**
     * Rāhu's month in his 230 (KTC 96): WB's own count gives the same, the true month count from the
     * 3rd month of 1687, the start of the 12th rab byung, with 209 added (vol. 1, p. 34,
     * «རབ་བྱུང་ཉི་མའི་ཐོག་མ་ཡི། །ཟླ་བ་རྣམ་པར་དག་པ་ལ། །བུ་ག་ནམ་མཁའ་འཁྲིག་པ་བྱིན», open question 16).
     */
    fun rahuMonth(monthCount: Long): Int = Math.floorMod(monthCount + MONTH_OFFSET + RAHU_PART, 230L).toInt()

    fun rahu(monthCount: Long, date: Int): Int = (rahuPlace(monthCount, date)[0] % 27).toInt()

    private fun rahuPlace(monthCount: Long, date: Int): LongArray {
        val t = rahuMonth(monthCount).toLong() * 30 + date
        val p = longArrayOf(0, 0, 14 * t, 0, 12 * t, 0)
        p[3] += p[4] / 23; p[4] %= 23
        p[2] += p[3] / 6; p[3] %= 6
        p[1] += p[2] / 60; p[2] %= 60
        p[0] += p[1] / 60; p[1] %= 60
        p[0] %= 27
        // From the whole circle, 27;0, which Henning prints as it stands when nothing is taken.
        return sub5(longArrayOf(27, 0, 0, 0, 0, 0), p, 23)
    }

    /** Rāhu's head as Henning prints it, "m;cs,cr,b,f", for the test. */
    internal fun printedRahu(monthCount: Long, date: Int): String = rahuPlace(monthCount, date).let { "${it[0]};${it[1]},${it[2]},${it[3]},${it[4]}" }

    /** The places of [jd] as Henning's software prints them, "m;cs,cr,b,f", for the test. */
    internal fun printed(jd: Long): Map<Planet, String> = exact(jd).mapValues { (_, p) -> p.d.take(5).let { "${it[0]};${it[1]},${it[2]},${it[3]},${it[4]}" } }
}
