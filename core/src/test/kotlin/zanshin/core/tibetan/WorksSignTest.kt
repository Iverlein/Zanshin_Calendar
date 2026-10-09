/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import zanshin.core.texts.Activities
import zanshin.core.texts.Texts
import zanshin.core.tibetan.ZodiacSign.*

/** The works' own rising signs, read on the scans of WB's chapter 34 (vol. 2, pp. 385–428; docs/sources/white-beryl-ch34.md). */
class WorksSignTest {
    private fun list(wording: String) = Electional.ACTIVITIES.single { it.wording == wording }

    @Test
    fun `the chapter's lists as printed`() {
        // 12, p. 393: «དུས་སྦྱོར་ཆུ་སྲིན་བུ་མོ་གཞུ། །ཉ་བཟང་གཞན་རྣམས་ངན་པ་ཡིན།» — the others bad, as stated.
        val journeys = list("setting_out_on_journeys")
        assertEquals(setOf(CAPRICORN, VIRGO, SAGITTARIUS, PISCES), journeys.good.signs)
        assertEquals(setOf(ARIES, TAURUS, GEMINI, CANCER, LEO, LIBRA, SCORPIO, AQUARIUS), journeys.bad.signs)
        // 2, p. 385: «འཁྲིག་གཞུ་བུམ་ཉ་བཟང་། །སྲང་དང་སྡིག་པ་གཉིས་ལ་ངན།།»
        assertEquals(setOf(GEMINI, SAGITTARIUS, AQUARIUS, PISCES), list("naming").good.signs)
        assertEquals(setOf(LIBRA, SCORPIO), list("naming").bad.signs)
        // 9, p. 390: «དུས་སྦྱོར་སྲང་གཞུ་བུམ་ཉ་བཟང་།» — Libra, with ra under sa.
        assertEquals(setOf(LIBRA, SAGITTARIUS, AQUARIUS, PISCES), list("manuring_and_breaking_in_oxen").good.signs)
        // 36, p. 405: «སེང་གེའི་དུས་སྦྱོར་བཟང་བ་སྟེ། །གཞུ་ཉ་བུམ་པ་རུང་བ་ཙམ།» — the acceptable ones on neither side.
        assertEquals(setOf(LEO), list("enthronement").good.signs)
        assertEquals(setOf(CAPRICORN, SCORPIO), list("enthronement").bad.signs)
        // 65, p. 428: «…སེང་གླང་འབྲིང་ཡིན་གཞན་རྣམས་ངན།» — the middling ones on neither side, the others bad.
        assertEquals(setOf(ARIES, LIBRA, SCORPIO, CAPRICORN, PISCES), list("increasing_activity").bad.signs)
        // 25–28, p. 401: taming, treating and saddling take horse racing's signs.
        for (w in listOf("treating_horses_mules_and_donkeys", "saddling", "breaking_in_horses")) {
            assertEquals(list("feeding_up_horses").good.signs, list(w).good.signs, w)
            assertEquals(list("feeding_up_horses").bad.signs, list(w).bad.signs, w)
        }
        // 44, p. 417: every sign named for a kind of rite.
        assertEquals(emptySet<ZodiacSign>(), list("averting_rites").good.signs + list("averting_rites").bad.signs)
    }

    @Test
    fun `no sign on both sides`() {
        for (a in Electional.ACTIVITIES) assertEquals(emptySet<ZodiacSign>(), a.good.signs intersect a.bad.signs, a.wording)
        assertEquals(55, Electional.ACTIVITIES.count { it.good.signs.isNotEmpty() || it.bad.signs.isNotEmpty() })
    }

    @Test
    fun `each hour lists the works whose own sign it is`() {
        // Leo rises: good for enthronement (36, p. 405), bad for journeys (12, p. 393) and for springs and wells (22, p. 399).
        val leo = Texts.WORKS_SIGN.getValue(LEO)
        assertTrue("enthronement" in leo.goodKeys)
        assertTrue("setting_out_on_journeys" in leo.avoidKeys)
        assertTrue("digging_ponds_canals_and_wells" in leo.avoidKeys)
        for (sign in ZodiacSign.entries) {
            val r = Texts.WORKS_SIGN.getValue(sign)
            assertEquals(emptySet<String>(), r.goodKeys.toSet() intersect r.avoidKeys.toSet(), "$sign")
        }
    }

    @Test
    fun `the particular case stands above the period's general reading`() {
        // Aries, a period to avoid, names fire offerings good (p. 371), and so does the chapter (45, p. 418): it stays.
        assertTrue("fire_offerings" in Texts.period(ARIES).second.goodKeys)
        // Taurus names sowing good (p. 371) and the chapter agrees (23, p. 400); Scorpio names consecration good (p. 373),
        // the chapter names Scorpio good for it too (48, p. 419).
        assertTrue("consecration" in Texts.period(SCORPIO).second.goodKeys)
        // Wherever the chapter names an act on one side, the period no longer names it on the other.
        for (sign in ZodiacSign.entries) {
            val own = Texts.WORKS_SIGN.getValue(sign)
            val period = Texts.period(sign).second
            assertEquals(emptySet<Any>(), Activities.of(period.goodKeys) intersect Activities.of(own.avoidKeys), "$sign")
            assertEquals(emptySet<Any>(), Activities.of(period.avoidKeys) intersect Activities.of(own.goodKeys), "$sign")
        }
        // The one case on the twelve: Gemini's period names empowerment, practice and study good (p. 372), the
        // chapter names Gemini bad for ordination, teaching, study and empowerment (47, p. 418).
        val set = ZodiacSign.entries.associateWith { sign ->
            Texts.DUS_SBYOR.getValue(sign).second.let { it.goodKeys + it.avoidKeys } - Texts.period(sign).second.let { (it.goodKeys + it.avoidKeys).toSet() }
        }.filterValues { it.isNotEmpty() }
        assertEquals(mapOf(GEMINI to listOf("empowerment", "dharma_practice", "study")), set)
    }
}
