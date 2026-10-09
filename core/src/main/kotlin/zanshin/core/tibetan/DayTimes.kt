/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import zanshin.core.rational.Rational
import kotlin.math.floor
import kotlin.math.roundToInt

/**
 * What the White Beryl's almanac writes within a day (vol. 1, pp. 177–178 and
 * ch. 15, pp. 180–182; docs/sources/almanac-page.md, entries 5, 7–9, 18), as
 * times in *chu tshod* after daybreak: 60 to the day, so that one is 24
 * minutes and daybreak is the hare hour's 05:00 (SPEC §10.3). A time below 0
 * falls before the day's daybreak, one of 60 or more after the next.
 *
 * WB reckons them "roughly, in *chu tshod*" from the almanac's own figures,
 * not from the sky: the moon at daybreak and the yoga's sum from one day's
 * row to the next, the lunar dates' ends and the true sun at them.
 */
object DayTimes {

    /** Something that begins at [at] chu tshod after daybreak, and for a skipped yoga ends at [until]. */
    data class Change<T>(val what: T, val at: Double, val until: Double? = null)

    /** A stretch from [start] to [end] chu tshod after daybreak; either may lie outside the day. */
    data class Span(val start: Double, val end: Double)

    /**
     * The mansions the moon enters during [day], from the almanac's moon at
     * daybreak on [day] and on [next], the following calendar day (p. 178):
     * the day's motion is the one figure taken from the next, and the moon
     * enters a mansion when the motion's share of the day brings it to the
     * mansion's start. WB writes a second mansion only when it comes in
     * daytime («ཉིན་མོ་སྐར་མ་གཉིས། །འདུག་ན་གཉིས་ཀ་འདྲི», p. 177), within WB's day length ([dayLength]).
     */
    fun mansions(day: TibetanDay, next: TibetanDay): List<Change<Mansion>> =
        crossings(day.moon, next.moon).map { (k, t) -> Change(Mansion.entries[k], t) }

    /**
     * The yogas that begin during [day], by the yoga's sum (the moon at
     * daybreak with the true sun) from [day] to [next], as for the mansions.
     * A yoga that begins and ends before the next daybreak is skipped from
     * the almanac's rows, and WB writes it with the day's own (p. 177,
     * «སྦྱོར་བ་མཆོངས་ན་སྔོན་མར་གཉིས»): it carries its end as [Change.until].
     */
    fun yogas(day: TibetanDay, next: TibetanDay): List<Change<Yoga>> {
        val starts = crossings(day.moon + day.sun, next.moon + next.sun)
        return starts.mapIndexed { i, (k, t) -> Change(Yoga.entries[k], t, starts.getOrNull(i + 1)?.second) }
    }

    /** The yogas of [day] that no daybreak shows: begun and ended within the day. */
    fun skippedYogas(day: TibetanDay, next: TibetanDay): List<Change<Yoga>> = yogas(day, next).filter { it.until != null }

    /** Boundaries k of 27 crossed between [from] and [to] (revolutions, a day apart), with the time of each. */
    private fun crossings(from: Rational, to: Rational): List<Pair<Int, Double>> {
        val a = from.frac().toDouble() * 27
        var b = to.frac().toDouble() * 27
        while (b <= a) b += 27
        return ((floor(a).toInt() + 1)..floor(b).toInt())
            .map { k -> k % 27 to (k - a) / (b - a) * 60 }
            .filter { (_, t) -> t < 60 }
    }

    /** The lunar dates on which Viṣṭi holds the later half, and those on which it holds the earlier. */
    val VISTI_LATER = setOf(4, 11, 18, 25)
    val VISTI_EARLIER = setOf(8, 15, 22, 29)

    /**
     * Viṣṭi's span that falls in [day], if any (p. 177): the later half of
     * the 4th, 11th, 18th and 25th, the earlier half of the 8th, 15th, 22nd
     * and 29th. The date's length is what the date before leaves of the day
     * after its end, with the date's own end; half of it is Viṣṭi's span,
     * from the date's start in the earlier half, up to its end in the later
     * («ཚེས་ལོངས་སྔོན་མས་མཁའ་རོ་ལ། །ཕྲི་ལྷག་ནམ་ལངས་གོང་དུ་འདས། །ལྷག་དང་ད་ལྟར་ལོངས་སྤྱོད་གཉིས། །བསྲེས་ལ་ཟུང་གིས་ཕྱེ་བའི་ནོར། །བིཥྚི་རྒྱུ་བའི་ཡུན་ཚད་ཡིན»).
     */
    fun visti(day: TibetanDay, a2: Rational = Phugpa.A2_ALMANAC): Span? {
        for (n in day.monthCount - 1..day.monthCount + 1) for (d in VISTI_LATER + VISTI_EARLIER) {
            val (a, b) = vistiOf(n, d, a2)
            val span = Span((a - day.jd) * 60, (b - day.jd) * 60)
            if (span.end > 0 && span.start < 60) return span
        }
        return null
    }

    /** Viṣṭi's span on date [d] of month count [n], from and to, on the dawn-based day scale. */
    fun vistiOf(n: Long, d: Int, a2: Rational = Phugpa.A2_ALMANAC): Pair<Double, Double> {
        val start = end(n, d - 1, a2)
        val stop = end(n, d, a2)
        val mid = (start + stop) / 2
        return if (d in VISTI_LATER) mid to stop else start to mid
    }

    /** When [day]'s own lunar date ends, in chu tshod after daybreak; none on the first of two equal dates, which no date ends. */
    fun dateEnd(day: TibetanDay, a2: Rational = Phugpa.A2_ALMANAC): Double? =
        if (day.repetition == Repetition.FIRST_OF_TWO) null else (end(day.monthCount, day.day, a2) - day.jd) * 60

    /**
     * The burning date that begins in daylight (vol. 1, p. 177, entry 11, «དེ་ཡང་དཔེར་ན་ཚེས་བཅུ་ལ། །རེས་གཟའ་ཟླ་བའི་ཚེས་ལོངས་དེ། །ཉིན་ཚད་མ་ལོངས་ཉུང་བ་ན། །བཅུ་གཅིག་ཚང་བའི་བརྡ་ཆད་དུ། །ཐ་མར་ཞབས་ཀྱུ་ལྡན་པར་འདྲི»):
     * on a Monday the 10th whose date ends before nightfall, the 11th, Monday's burning date, comes in the
     * day, and WB writes it with a hook. On a day whose weekday burns on the date after its own, the time
     * that date begins, when it is before [nightfall] (chu tshod after daybreak); otherwise none.
     */
    fun burningFrom(day: TibetanDay, nightfall: Double, a2: Rational = Phugpa.A2_ALMANAC): Double? {
        if (day.burningDate || !BurningDate.of(day.weekday, day.day % 30 + 1)) return null
        return dateEnd(day, a2)?.takeIf { it < nightfall }
    }

    /** The end of lunar date [d] of month count [n] on the dawn-based day scale; date 0 is the month before's 30th. */
    private fun end(n: Long, d: Int, a2: Rational = Phugpa.A2_ALMANAC): Double =
        (if (d == 0) Phugpa.trueDate(n - 1, 30, a2) else Phugpa.trueDate(n, d, a2)).toDouble()

    /** The true sun at the end of lunar date [d] of month count [n], in revolutions. */
    private fun sun(n: Long, d: Int): Double =
        (if (d == 0) Phugpa.trueSun(n - 1, 30) else Phugpa.trueSun(n, d)).toDouble()

    /** The sun's three kinds of term (p. 177, «དབུགས་ཐོབ་ཁྱིམ་འཕོ་དང་། །སྒང་ཚད»). */
    enum class SunTermKind(val wylie: String) { DBUGS_THOB("dbugs thob"), KHYIM_PHO("khyim 'pho"), SGANG("sgang") }

    /**
     * A term of the sun: [month] is the Hor month whose *dbugs thob* or
     * *sgang* it is, [sign] the sign a *khyim 'pho* enters; [mansion] and
     * [chuTshod] give the true sun's place, WB's "measure" of the term.
     */
    data class SunTerm(val kind: SunTermKind, val mansion: Int, val chuTshod: Int, val month: Int? = null, val sign: ZodiacSign? = null) {
        /** The measure in chu tshod of the sun's course, 27 × 60 to the round. */
        val arc: Int get() = mansion * 60 + chuTshod
    }

    /**
     * The measures of the twelve months' *sgang* and *dbugs thob* and of the
     * twelve signs' entries, as WB's ch. 15 lists them in mansions and chu
     * tshod of the true sun (vol. 1, pp. 180–182, number words read on the
     * scan, docs/sources/almanac-page.md), in its order from the 3rd month's
     * *sgang*. Each kind steps by 2;15, a twelfth of the course.
     */
    val SUN_TERMS: List<SunTerm> = listOf(
        SunTerm(SunTermKind.SGANG, 0, 36, month = 3), SunTerm(SunTermKind.DBUGS_THOB, 26, 28, month = 3),
        SunTerm(SunTermKind.KHYIM_PHO, 2, 15, sign = ZodiacSign.TAURUS),
        SunTerm(SunTermKind.SGANG, 2, 51, month = 4), SunTerm(SunTermKind.DBUGS_THOB, 1, 43, month = 4),
        SunTerm(SunTermKind.KHYIM_PHO, 4, 30, sign = ZodiacSign.GEMINI),
        SunTerm(SunTermKind.SGANG, 5, 6, month = 5), SunTerm(SunTermKind.DBUGS_THOB, 3, 58, month = 5),
        SunTerm(SunTermKind.KHYIM_PHO, 6, 45, sign = ZodiacSign.CANCER),
        SunTerm(SunTermKind.SGANG, 7, 21, month = 6), SunTerm(SunTermKind.DBUGS_THOB, 6, 13, month = 6),
        SunTerm(SunTermKind.KHYIM_PHO, 9, 0, sign = ZodiacSign.LEO),
        SunTerm(SunTermKind.SGANG, 9, 36, month = 7), SunTerm(SunTermKind.DBUGS_THOB, 8, 28, month = 7),
        SunTerm(SunTermKind.KHYIM_PHO, 11, 15, sign = ZodiacSign.VIRGO),
        SunTerm(SunTermKind.SGANG, 11, 51, month = 8), SunTerm(SunTermKind.DBUGS_THOB, 10, 43, month = 8),
        SunTerm(SunTermKind.KHYIM_PHO, 13, 30, sign = ZodiacSign.LIBRA),
        SunTerm(SunTermKind.SGANG, 14, 6, month = 9), SunTerm(SunTermKind.DBUGS_THOB, 12, 58, month = 9),
        SunTerm(SunTermKind.KHYIM_PHO, 15, 45, sign = ZodiacSign.SCORPIO),
        SunTerm(SunTermKind.SGANG, 16, 21, month = 10), SunTerm(SunTermKind.DBUGS_THOB, 15, 13, month = 10),
        SunTerm(SunTermKind.KHYIM_PHO, 18, 0, sign = ZodiacSign.SAGITTARIUS),
        SunTerm(SunTermKind.SGANG, 18, 36, month = 11), SunTerm(SunTermKind.DBUGS_THOB, 17, 28, month = 11),
        SunTerm(SunTermKind.KHYIM_PHO, 20, 15, sign = ZodiacSign.CAPRICORN),
        SunTerm(SunTermKind.SGANG, 20, 51, month = 12), SunTerm(SunTermKind.DBUGS_THOB, 19, 43, month = 12),
        SunTerm(SunTermKind.KHYIM_PHO, 22, 30, sign = ZodiacSign.AQUARIUS),
        SunTerm(SunTermKind.SGANG, 23, 6, month = 1), SunTerm(SunTermKind.DBUGS_THOB, 21, 58, month = 1),
        SunTerm(SunTermKind.KHYIM_PHO, 24, 45, sign = ZodiacSign.PISCES),
        SunTerm(SunTermKind.DBUGS_THOB, 24, 13, month = 2), SunTerm(SunTermKind.SGANG, 25, 21, month = 2),
        SunTerm(SunTermKind.KHYIM_PHO, 0, 0, sign = ZodiacSign.ARIES),
    )

    /**
     * The multiplier of WB's rule for the terms (p. 182, «གང་མང་བའི། །ཆུ་ཚོད་ཡིད་བསྒྱུར་ཆུ་སྲང་རོས། །བགོས་པ་སྟེང་བྱིན»): the
     * chu tshod of the sun's excess over a measure, multiplied (*bsgyur*) by *yid*, the number word for
     * 14 (*Tshig mdzod chen mo*), the chu srang carried up into chu tshod, are the chu tshod of time
     * taken from the date's end. A day of the sun's course is so 60/14 = 4;17,8 chu tshod (open
     * question 15).
     */
    const val MULTIPLIER: Double = 14.0

    /**
     * WB's length of the day (*nyin tshad*) in chu tshod when the true sun stands at [sunArc] chu tshod
     * of its course (27 × 60 to the round): 30 at the middle terms of the 2nd and 8th months, the
     * equinoxes, longer or shorter by 1;10 each sign-month (vol. 1, p. 182, «ཁྱིམ་ཟླ་རེར། །ཆུ་ཚོད་རེ་དང་ཆུ་སྲང་ཕྱོགས། །འཕེལ་འགྲིབ»)
     * to 33;30 at the 5th month's, the summer solstice, and 26;30 at the 11th's; the day lengths ch. 15
     * lists at each middle term (31;10 at the 3rd month's … 27;40 at the 12th's, 28;50 at the 1st's).
     */
    fun dayLength(sunArc: Double): Double {
        val x = Math.floorMod((sunArc - (25 * 60 + 21)).let { Math.round(it * 1000) }, 1620L * 1000) / 1000.0
        val t = when {
            x <= 405 -> x / 405
            x <= 1215 -> (810 - x) / 405
            else -> (x - 1620) / 405
        }
        return 30 + 3.5 * t
    }

    /** WB's daytime of [day]: its length by the true sun at the end of the day's date. */
    fun dayLength(day: TibetanDay): Double = dayLength(day.sun.toDouble() * 1620)

    /**
     * The sun's terms that fall in [day] (p. 182): a term falls in the lunar date at whose end the true
     * sun has reached its measure; if the sun stands exactly on it, at the date's end, and if beyond,
     * the excess times [MULTIPLIER] is taken, in chu tshod of time, from the date's end
     * («ཚད་བཞིན་མ་ཤར་མང་བ་ཡི། …ཚེས་ཀྱི་ཆུ་ཚོད་ཕྲི་བ་དེའི། །ལྷག་མ་ཟད་ཚེ་འཕོ»), which can carry it into the day
     * before («གོང་མའི་ཞག་གི་ནམ་ལངས་ནས»).
     */
    fun sunTerms(day: TibetanDay, a2: Rational = Phugpa.A2_ALMANAC): List<Change<SunTerm>> = buildList {
        for (n in day.monthCount - 1..day.monthCount + 1) for (d in 1..30) {
            val t1 = end(n, d, a2)
            if (t1 < day.jd || t1 >= day.jd + 3) continue
            val s0 = sun(n, d - 1) * 1620
            var s1 = sun(n, d) * 1620
            if (s1 < s0) s1 += 1620
            for (term in SUN_TERMS) {
                val m = if (term.arc <= s0) term.arc + 1620.0 else term.arc.toDouble()
                if (m > s1) continue
                val at = t1 - (s1 - m) * MULTIPLIER / 60
                if (floor(at).toLong() == day.jd) add(Change(term, (at - day.jd) * 60))
            }
        }
    }.sortedBy { it.at }

    /** The clock minute of the day, 0–1439, of a time [chuTshod] after daybreak at 05:00. */
    fun clockMinute(chuTshod: Double): Int = Math.floorMod((5 * 60 + chuTshod * 24).roundToInt(), 24 * 60)
}
