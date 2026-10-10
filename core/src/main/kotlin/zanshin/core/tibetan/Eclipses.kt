/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

/**
 * Whether Rāhu seizes the moon at the full moon or the sun at the new moon, by the White Beryl's
 * chapter 9 (vol. 1, pp. 59 and 65, img. 69 and 75; docs/sources/month-entries.md), which the full
 * almanac's month heading writes (p. 176, «ཉ་སྟོང་ཟླ་ཉི་སྒྲ་གཅན་གྱིས། །སྒྲིབ་ཚེ»).
 *
 * The sun is the abridged tantra's, the [ByedRtsis] («བསྡུས་པའི་རྒྱུད་ཀྱི་ཉི་དག་ལ། །འདོད་པ་མཁའ་མེ་བྱིན་པ་དེ། །ཚེས་འཁྱུད་ཟླ་སྐར»; for the sun, «བྱེད་གྲུབ་ཁྱད་རྣམས་གོང་སྨྲས་ཀྱི། །ཁྱད་པར་ཙམ་ལས་བྱེད་རང་གཙོ»):
 * at the end of the 15th, with half a circle added, it is the moon of the full moon; at the end of
 * the 30th it is the sun of the new moon. Rāhu is the true reckoning's ([Planets.rahu]), WB's own
 * (open question 16). The gap from Rāhu's nearer end decides, in chu tshod, each way its own limit.
 * Checked against the sky, 2000–2049: of the 79 full moons it marks, every one has a lunar eclipse,
 * and of the 33 new moons a solar one (`EclipsesTest`).
 */
object Eclipses {

    /** The body seized. */
    enum class Body { MOON, SUN }

    /** Rāhu's end that seizes: the head (*gdong*) or the tail (*dus me*). */
    enum class End(val wylie: String) { HEAD("gdong"), TAIL("dus me") }

    /** An eclipse WB's rule finds: the body, the end, and the gap from it in chu tshod. */
    data class Eclipse(val body: Body, val end: End, val gap: Int, val date: Int)

    private const val UNIT = ByedRtsis.UNIT
    private const val CIRCLE = 27 * UNIT
    private const val CHU_TSHOD = UNIT / 60

    /** Rāhu's head on date [d] of month count [n], in [ByedRtsis.UNIT]s: back round the circle in 6900 dates. */
    internal fun head(n: Long, d: Int): Long {
        val t = Planets.rahuMonth(n).toLong() * 30 + d
        return Math.floorMod(-(t * CIRCLE / 6900), CIRCLE)
    }

    /**
     * The four limits, each the most the gap may be: the body past the head, the head past the body, the
     * body past the tail, the tail past the body.
     */
    private class Limits(val pastHead: Int, val beforeHead: Int, val pastTail: Int, val beforeTail: Int)

    /**
     * The moon (p. 59, «ཟླ་བར་གདོང་སྦྱངས་ལྷག་མ་རུ། །རི་དབང་མན་ཆད་འཛིན་པ་དེ། །གདོང་ལ་ཟླ་བས་སྦྱངས་པ་དང་། །དུས་མེ་ཟླ་བས་སྦྱངས་པ་ལ། །མཁའ་འབྱུང་མན་ཤར་འཛིན་པར་ངེས། །ཟླ་བ་དུས་མེས་སྦྱངས་པའི་ཚེ། །འབྱུང་མཚོ་མན་ཆད་འཛིན་པ་ཡིན»):
     * 57 past the head, 50 before the head or the tail, 45 past the tail.
     */
    private val MOON = Limits(57, 50, 45, 50)

    /**
     * The sun (p. 65, «གདོང་གིས་ཉི་སྦྱངས་…ཆུ་ཚོད་མིག་དབང་བར་དུ་འཛིན། །ཉི་མས་གདོང་སྦྱངས་…ཆུ་ཚོད་མདའ། …དུས་མེས་ཉི་སྦྱངས་…སྐར་ཐིག་ཆུ་ཚོད་རི་ཙམ་འཛིན། །ཉི་མས་དུས་མེ་སྦྱངས་པ་ན། །མ་ལྡོག་མཁའ་ཆུའི་ནང་ཚུན་འཛིན»):
     * 52 past the head, 5 before it ("turned back", *go ldog*), 7 past the tail, 40 before it.
     */
    private val SUN = Limits(52, 5, 7, 40)

    private fun seized(body: Body, place: Long, n: Long, d: Int): Eclipse? {
        val limits = if (body == Body.MOON) MOON else SUN
        val head = head(n, d)
        val tail = Math.floorMod(head + CIRCLE / 2, CIRCLE)
        fun gap(a: Long, b: Long): Long = Math.floorMod(a - b, CIRCLE)
        val ways = listOf(
            Triple(End.HEAD, gap(place, head), limits.pastHead),
            Triple(End.HEAD, gap(head, place), limits.beforeHead),
            Triple(End.TAIL, gap(place, tail), limits.pastTail),
            Triple(End.TAIL, gap(tail, place), limits.beforeTail),
        )
        return ways.filter { it.second <= it.third * CHU_TSHOD }.minByOrNull { it.second }
            ?.let { Eclipse(body, it.first, (it.second / CHU_TSHOD).toInt(), d) }
    }

    /** The moon at the full moon of month count [n], if Rāhu seizes it. */
    fun moon(n: Long): Eclipse? =
        seized(Body.MOON, Math.floorMod(ByedRtsis.date(n, 15).sun + CIRCLE / 2, CIRCLE), n, 15)

    /** The sun at the new moon of month count [n], if Rāhu seizes it. */
    fun sun(n: Long): Eclipse? = seized(Body.SUN, ByedRtsis.date(n, 30).sun, n, 30)

    /**
     * The eclipse of [day], if any: the moon's on the day the 15th ends, the sun's on the day the 30th ends.
     * WB counts the day by its virtue alone (p. 62, «དགུ་པ་དགེ་བའི་འགྱུར་ཁྱད་ནི། །དུས་བཟང་དཀྱུས་རྣམས་བརྒྱ་འགྱུར་ལ། །ཟླ་འཛིན་བྱེ་བ་བདུན་འགྱུར་དང་། །ཉི་འཛིན་བྱེ་བ་འབུམ་འགྱུར་དུ»): what is done
     * on it is multiplied seventy million times at a lunar eclipse and a million million times at a solar
     * one, against a hundred on the ordinary good days. It names no works, so the day is shown and not weighed.
     */
    fun of(day: TibetanDay): Eclipse? = when {
        Phugpa.endJd(day.monthCount, 15) == day.jd -> moon(day.monthCount)
        Phugpa.endJd(day.monthCount, 30) == day.jd -> sun(day.monthCount)
        else -> null
    }
}
