/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import zanshin.core.texts.gloss
import zanshin.core.tibetan.DayLetters.Body

/**
 * What the fangs of the hundred feet do (WB vol. 1, ch. 12, pp. 97–99, read on the Zhol print, WBZ
 * img. 133–136; docs/sources/day-letters.md): against a person's birth mansion and the six holders
 * counted from it, and, as the spear and the great spear, against the moon's mansion of the day. WB
 * gives them results but no place in the order of strength (vol. 2, p. 376), so the app shows them and
 * weighs none (SPEC §5.11).
 */
object HundredFeet {

    /**
     * The four malefics (*drag gza' bzhi*, p. 95): the sun, which p. 97 calls «སྡིག་གཟའ་ཉི་མ» and counts with
     * Mars and Saturn on the malefics' right, and Rāhu, whose face-on p. 98 names with Mars's as the worst. The
     * other four are the benefics (*zhi gza'*, *bsod nams gza'*).
     */
    val MALEFICS: Set<Body> = setOf(Body.SUN, Body.MARS, Body.SATURN, Body.RAHU)

    /** The mansions of the wheel in their order round it (p. 97), Abhijit (null) after Śravaṇa. */
    private val RING: List<Mansion?> by lazy {
        val w = DayLetters.WHEEL
        buildList {
            for (j in 1..7) add(w[0][j])
            for (i in 1..7) add(w[i][8])
            for (j in 7 downTo 1) add(w[8][j])
            for (i in 7 downTo 1) add(w[i][0])
        }.map { (it as DayLetters.Cell.Star).mansion }
    }

    /**
     * The places counted from the birth mansion round the wheel (p. 98, «གཞན་ཡང་གནོད་པ་དྲུག་འཛིན་བརྟག»), each with what a
     * malefic's fang on it brings: the 10th, the place of work («གཡས་སྐོར་བཅུ་པ་ལས་ཀྱི་སར»), the 16th *rnam dgyes*,
     * the 18th *rgya can*, the 19th *a na Na*, the 23rd *bi na sha ga* and the 25th *yid can*.
     */
    enum class Holder(val count: Int, val wylie: String) {
        WORK(10, "las kyi sa"),
        SANGHATIKA(16, "rnam dgyes"),
        SAMUDAYA(18, "rgya can"),
        ADHANA(19, "a na Na"),
        VINASHAKA(23, "bi na sha ga"),
        MANASA(25, "yid can");

        val english: String get() = gloss(this)
    }

    /** The mansion of [holder] for one born in [birth]: Abhijit, which no day has, is null. */
    fun holder(birth: Mansion, holder: Holder): Mansion? = RING[(RING.indexOf(birth) + holder.count - 1) % RING.size]

    /** The mansion each body's right fang reaches (p. 98: Kṛttikā's is Bharaṇī); the left runs through the inner rings to the face-on. */
    private fun struck(day: TibetanDay): List<Pair<Body, Mansion?>> =
        DayLetters.bodies(day).map { (body, m) ->
            body to DayLetters.fangs(m).right.filterIsInstance<DayLetters.Cell.Star>().single().mansion
        }

    /**
     * The fangs on a person's day ([of]): [malefics] and [benefics] whose fang is on the birth mansion
     * (p. 98, «སྐྱེས་མིང་སྐར་མར་དྲག་གཟའི་མཆེ། །གཅིག་ཟུག་དོན་ཉམས་གཉིས་ཀྱིས་ནད། །འབྱུང་ལ་གསུམ་གྱིས་འཆི་བ་དང་། །ཞི་གཟའ་གཅིག་གཉིས་ཟས་སྐོམ་མེད། །གསུམ་བྱུང་རིམས་དང་ནོར་རྣམས་ཉམས།»),
     * the malefics on each of the six [holders], and [deadly] where malefics hold the birth mansion, the place of
     * work and *vināśaka* at once («ཁྱད་པར་སྐྱེས་ས་ལས་ཀྱི་ས། །བི་ན་ཤ་གར་དྲག་པོའི་གཟའ། །གསུམ་འཛོམ་སྐྱེས་བུ་འཆི་བར་བྱེད།»).
     */
    data class Strikes(
        val birth: Mansion,
        val malefics: List<Body>,
        val benefics: List<Body>,
        val holders: Map<Holder, List<Body>>,
    ) {
        val deadly: Boolean get() = malefics.isNotEmpty() && Holder.WORK in holders && Holder.VINASHAKA in holders

        /** What the fangs on the birth mansion bring: the malefics' by their number, else the benefics'. */
        val result: Result? get() = when {
            deadly -> Result.DEATH
            malefics.size >= 3 -> Result.DEATH
            malefics.size == 2 -> Result.ILLNESS
            malefics.size == 1 -> Result.AIMS_FAIL
            benefics.size >= 3 -> Result.EPIDEMIC
            benefics.isNotEmpty() -> Result.WANT
            else -> null
        }
    }

    /** The results of p. 98 for the birth mansion, worst first. */
    enum class Result { DEATH, ILLNESS, AIMS_FAIL, EPIDEMIC, WANT }

    /**
     * The fangs on [day] for one born in [birth]'s mansion, or null when none is on the birth mansion and the
     * three of [Strikes.deadly] are not held: the six holders alone are struck on most days, often for weeks, so
     * they are given with the birth mansion's strikes and do not stand alone (SPEC §10.3).
     */
    fun of(day: TibetanDay, birth: Mansion): Strikes? {
        val struck = struck(day)
        val onBirth = struck.filter { it.second == birth }.map { it.first }
        val holders = Holder.entries.associateWith { h ->
            val m = holder(birth, h)
            struck.filter { (b, s) -> b in MALEFICS && m != null && s == m }.map { it.first }
        }.filterValues { it.isNotEmpty() }
        val strikes = Strikes(birth, onBirth.filter { it in MALEFICS }, onBirth.filter { it !in MALEFICS }, holders)
        return strikes.takeIf { it.result != null }
    }

    /**
     * The spear and the great spear (p. 98–99, «དེ་ལྟར་གདོང་ཚུགས་གནོད་པ་དེ། །ཟླ་སྐར་ལ་ཤར་དུས་སུ་ནི། །བསོད་ནམས་གཟའ་ཡིས་མདུང་སྦྱོར་དང་། །དྲག་གཟའ་མདུང་ཆེན་སྦྱོར་བ་སྟེ། …དེ་སོགས་མདུང་དང་མདུང་ཆེན་དག །བྱ་བ་ཀུན་ལ་སྤང་བ་གནད།»):
     * a body's face-on on the moon's mansion of the day, a benefic's the spear, a malefic's the great spear; both
     * to be avoided for every work, a bride above all. [bodies] are those whose face-on it is.
     */
    data class Spear(val great: Boolean, val bodies: List<Body>)

    /** The spear of [day], the great spear where any malefic's face-on is on the moon's mansion; null on most days. */
    fun spear(day: TibetanDay): Spear? {
        val on = DayLetters.bodies(day).filter { (body, m) ->
            body != Body.MOON && DayLetters.fangs(m).faceOn == DayLetters.Cell.Star(day.mansion)
        }.map { it.first }
        if (on.isEmpty()) return null
        val malefic = on.filter { it in MALEFICS }
        return if (malefic.isNotEmpty()) Spear(true, malefic) else Spear(false, on)
    }
}
