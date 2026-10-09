/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import zanshin.core.texts.Catalog
import zanshin.core.texts.Texts
import zanshin.core.tibetan.EarthLordCourse.BAR_KHYI
import zanshin.core.tibetan.EarthLordCourse.CLASS_TIMES
import zanshin.core.tibetan.EarthLordCourse.DBUL_PO
import zanshin.core.tibetan.EarthLordCourse.DRA_CHEN
import zanshin.core.tibetan.EarthLordCourse.GNAM_KHYI
import zanshin.core.tibetan.EarthLordCourse.GNAM_SBYOR
import zanshin.core.tibetan.EarthLordCourse.GNAM_SGO
import zanshin.core.tibetan.EarthLordCourse.GNYAN
import zanshin.core.tibetan.EarthLordCourse.GZA_BDUN
import zanshin.core.tibetan.EarthLordCourse.GZA_RGOD
import zanshin.core.tibetan.EarthLordCourse.HAL_KHYI
import zanshin.core.tibetan.EarthLordCourse.KA_KHYUNG
import zanshin.core.tibetan.EarthLordCourse.KI_KANG
import zanshin.core.tibetan.EarthLordCourse.KLU
import zanshin.core.tibetan.EarthLordCourse.NAG_CHUNG
import zanshin.core.tibetan.EarthLordCourse.NGAM_SHING
import zanshin.core.tibetan.EarthLordCourse.PHUNG_ZOR
import zanshin.core.tibetan.EarthLordCourse.PI_LING
import zanshin.core.tibetan.EarthLordCourse.SA_BDAG_BZLOG
import zanshin.core.tibetan.EarthLordCourse.SDE_BRGYAD
import zanshin.core.tibetan.EarthLordCourse.ZIN_PHUNG

/**
 * The earth lords that move by date (WB vol. 2, pp. 226–235; docs/sources/earth-lord-courses.md):
 * vectors from the text's own lists, read on the scan, and from the day boxes of WB's model almanac
 * (vol. 1, pp. 154–155, 156 and 170–171, img. 164–166 and 180–181), which write the courses on the
 * dates of Hor months 11 (the first of spring), 12 and 9 (the middle of winter).
 */
class EarthLordCoursesTest {
    private fun on(month: Int, date: Int, animal: Animal = Animal.MOUSE) = EarthLordCourses.of(month, date, animal)
    private fun has(month: Int, date: Int, course: EarthLordCourse, event: CourseEvent = CourseEvent.MOVES) =
        on(month, date).any { it.course == course && it.event == event }

    @Test
    fun `the model almanac's first month of spring`() {
        val month = 11
        assertTrue(has(month, 1, DBUL_PO), "dbul on the 1st")
        assertTrue(has(month, 11, DBUL_PO), "dbul on the 11th")
        for (course in listOf(BAR_KHYI, NAG_CHUNG, GNAM_SBYOR, HAL_KHYI)) assertTrue(has(month, 8, course), "$course on the 8th")
        assertTrue(has(month, 10, KLU, CourseEvent.TURNS_BACK), "klu bzlog on the 10th")
        assertTrue(has(month, 14, SDE_BRGYAD, CourseEvent.STRIKES), "thebs at the hare hour on the 14th")
        assertTrue(has(month, 14, KLU, CourseEvent.STRIKES), "klu thebs on the 14th")
        assertTrue(has(month, 15, KI_KANG), "ki kang on the 15th")
        assertTrue(has(month, 15, SDE_BRGYAD, CourseEvent.STRIKES), "thebs on the 15th")
        assertTrue(has(month, 17, SDE_BRGYAD, CourseEvent.TURNS_BACK), "bzlog on the 17th")
        assertTrue(has(month, 19, SDE_BRGYAD, CourseEvent.TURNS_BACK), "bzlog on the 19th")
        assertTrue(has(month, 27, KA_KHYUNG), "ki kang on the 27th")
        // The sky doors in the boxes: mgron, tshong, bu, dmag, gnyen, bag, dur, spyi, mkhar, shid.
        val doors = mapOf(
            1 to "mgron po", 2 to "tshong", 3 to "bu chung", 4 to "dmag", 5 to "gnyen", 7 to "bag ma", 8 to "dur", 10 to "spyi",
            11 to "mgron po", 13 to "bu chung", 14 to "dmag", 15 to "gnyen", 16 to "mkhar", 17 to "bag ma", 19 to "shid", 20 to "spyi",
            21 to "mgron po", 22 to "tshong", 23 to "bu chung", 24 to "dmag", 25 to "gnyen",
        )
        doors.forEach { (date, door) -> assertEquals(door, on(month, date).single { it.course == GNAM_SGO }.variant, "door of the $date") }
    }

    @Test
    fun `the model almanac's hare month has ki kang on the 2nd and 3rd`() {
        assertTrue(has(12, 2, KI_KANG))
        assertTrue(has(12, 3, KI_KANG))
        assertTrue(has(12, 2, DBUL_PO))
    }

    @Test
    fun `the model almanac's middle month of winter`() {
        val month = 9
        for (date in listOf(2, 6, 16, 20, 26)) assertTrue(has(month, date, KLU, CourseEvent.TURNS_BACK), "klu bzlog on the $date")
        assertFalse(has(month, 3, KLU, CourseEvent.TURNS_BACK), "no klu bzlog on the 3rd")
        for (date in listOf(7, 17)) assertTrue(has(month, date, KLU, CourseEvent.STRIKES), "klu thebs on the $date")
        for (date in listOf(6, 16, 26)) assertTrue(on(month, date).any { it.course == CLASS_TIMES && it.variant == "btsan" }, "btsan on the $date")
        for (date in listOf(9, 19, 29)) assertTrue(on(month, date).any { it.course == CLASS_TIMES && it.variant == "gnod sbyin" }, "gnod sbyin on the $date")
        for (date in listOf(22, 24, 25)) assertTrue(has(month, date, SDE_BRGYAD, CourseEvent.STRIKES), "thebs on the $date")
    }

    @Test
    fun `the text's own lists`() {
        // Pi ling: the 28th of the first month of spring (the 11th), the 9th of the last of winter (the 10th).
        assertTrue(has(11, 28, PI_LING)); assertTrue(has(10, 9, PI_LING)); assertFalse(has(10, 28, PI_LING))
        // Phung po zor thogs: the horse month (the 3rd) on the 23rd, the pig month (the 8th) on the 1st.
        assertTrue(has(3, 23, PHUNG_ZOR)); assertTrue(has(8, 1, PHUNG_ZOR))
        // Hal khyi: the dog month (the 7th) on the 30th, the ox month (the 10th) on the 3rd.
        assertTrue(has(7, 30, HAL_KHYI)); assertTrue(has(10, 3, HAL_KHYI))
        // Gnam sbyor: the last of summer (the 4th) on the 26th; gza' rgod: the monkey month (the 5th) on the 9th and 22nd.
        assertTrue(has(4, 26, GNAM_SBYOR)); assertTrue(has(5, 9, GZA_RGOD)); assertTrue(has(5, 22, GZA_RGOD))
        // Dbul po: the dog month on the 22nd and 9th; gza' bdun: the last of winter on the 29th.
        assertTrue(has(7, 22, DBUL_PO)); assertTrue(has(7, 9, DBUL_PO)); assertTrue(has(10, 29, GZA_BDUN))
        // Ngam shing: none in the first month of summer (the 2nd); the middle of winter (the 9th) on the 30th.
        assertTrue((1..30).none { has(2, it, NGAM_SHING) }); assertTrue(has(9, 30, NGAM_SHING))
        // Dra chen: the bird month (the 6th) on the 1st and 21st; nothing in the tiger month.
        assertTrue(has(6, 1, DRA_CHEN)); assertTrue(has(6, 21, DRA_CHEN)); assertTrue((1..30).none { has(11, it, DRA_CHEN) })
        // Nag chung: the winter's 4th, 18th and 16th (Hor 8, 9, 10).
        assertTrue(has(8, 4, NAG_CHUNG)); assertTrue(has(9, 18, NAG_CHUNG)); assertTrue(has(10, 16, NAG_CHUNG))
        // The earth lords turn back on the 30th of the last month of spring (the 1st).
        assertTrue(has(1, 30, SA_BDAG_BZLOG, CourseEvent.TURNS_BACK))
        // The gnyan move on the 27th of the last of summer (the 4th); in Spug ston's view they strike all of it.
        assertTrue(has(4, 27, GNYAN)); assertTrue((1..30).all { has(4, it, GNYAN, CourseEvent.STRIKES) })
        // In the last month of winter the eight classes turn back on the 27th and strike every other day; the nāgas sleep.
        assertTrue(has(10, 27, SDE_BRGYAD, CourseEvent.TURNS_BACK)); assertFalse(has(10, 27, SDE_BRGYAD, CourseEvent.STRIKES))
        assertTrue(has(10, 29, SDE_BRGYAD, CourseEvent.STRIKES)); assertTrue((1..30).none { d -> on(10, d).any { it.course == KLU } })
    }

    @Test
    fun `the date's animal`() {
        // Zin phung: spring tiger days move, spring mouse days sit in the middle; gnam khyi: early spring dragon days.
        assertEquals("0", on(11, 5, Animal.TIGER).single { it.course == ZIN_PHUNG }.variant)
        assertEquals(CourseEvent.SITS to "middle", on(12, 5, Animal.MOUSE).single { it.course == ZIN_PHUNG }.let { it.event to it.variant })
        assertTrue(on(5, 5, Animal.TIGER).none { it.course == ZIN_PHUNG })
        assertTrue(on(11, 5, Animal.DRAGON).any { it.course == GNAM_KHYI })
        assertTrue(on(12, 5, Animal.DRAGON).none { it.course == GNAM_KHYI })
    }

    @Test
    fun `the other views WB reports, and Spug ston's`() {
        fun other(month: Int, date: Int, course: EarthLordCourse, animal: Animal = Animal.MOUSE) =
            EarthLordCourses.of(month, date, animal).filter { it.course == course && it.otherView }.map { it.variant }
        // Gza' bdun reversed: the first month of spring the 28th; dbul po's view: its 6th and 16th.
        assertEquals(listOf(EarthLordCourses.OTHER), other(11, 28, GZA_BDUN)); assertTrue(other(11, 8, GZA_BDUN).isEmpty())
        assertEquals(listOf(EarthLordCourses.OTHER), other(11, 16, DBUL_PO)); assertTrue(other(12, 16, DBUL_PO).isEmpty())
        // Ki kang's second (the tiger month's 17th) and third (the hare month's 22nd); nag chung's winter 9s.
        assertEquals(listOf(EarthLordCourses.OTHER, EarthLordCourses.THIRD), other(11, 17, KI_KANG)); assertEquals(listOf(EarthLordCourses.THIRD), other(12, 22, KI_KANG))
        assertEquals(listOf(EarthLordCourses.OTHER), other(9, 19, NAG_CHUNG)); assertEquals(listOf(EarthLordCourses.OTHER), other(8, 9, NAG_CHUNG))
        // Phung po zor thogs by season: the middle month of autumn (the 6th) on the 17th; gnam khyi's other: early spring sheep days.
        assertEquals(listOf(EarthLordCourses.OTHER), other(6, 17, PHUNG_ZOR)); assertEquals(listOf(EarthLordCourses.OTHER), other(11, 5, GNAM_KHYI, Animal.SHEEP))
        // What some say: the eight classes from the 8th to the 30th of the last autumn month (the 7th), the nāgas on the 15th of the last of summer (the 4th).
        assertEquals(listOf(EarthLordCourses.OTHER), other(7, 12, SDE_BRGYAD)); assertTrue(other(7, 28, SDE_BRGYAD).isEmpty())
        assertEquals(listOf(EarthLordCourses.OTHER), other(4, 15, KLU))
        // The great black day's other view: the last month of spring (the 1st) on the 24th, the first of winter (the 8th) on the 9th.
        assertEquals(listOf(EarthLordCourses.OTHER), other(1, 24, EarthLordCourse.NAG_CHEN)); assertEquals(listOf(EarthLordCourses.OTHER), other(8, 9, EarthLordCourse.NAG_CHEN))
        // Spug ston: the first month of spring succeeds on the 5th and 30th, vanishes on the 8th, gives no wealth on the 1st and 6th.
        fun spug(month: Int, date: Int) = on(month, date).filter { it.course == EarthLordCourse.SPUG_STON }.map { it.variant }
        assertEquals(listOf("grub"), spug(11, 5)); assertEquals(listOf("yal"), spug(11, 8)); assertEquals(listOf("nor"), spug(11, 6))
        // The last month of summer (the 4th), read on the Zhol print: success on the 3rd, vanishing on the 26th, no wealth on the 28th.
        assertEquals(listOf("nor"), spug(4, 28)); assertEquals(listOf("grub"), spug(4, 3)); assertEquals(listOf("yal"), spug(4, 26))
        // The planets and the srin po on Viṣṭi's dates, every month.
        for (date in listOf(4, 8, 11, 15, 18, 22, 25, 29)) assertEquals(setOf("gza", "srin po"), on(7, date).filter { it.course == EarthLordCourse.CLASS_TIMES && it.variant in setOf("gza", "srin po") }.map { it.variant }.toSet())
    }

    @Test
    fun `the black days`() {
        // WB's example: in the mouse and horse months (Hor 9 and 3) the bird and hare days are black.
        for (month in listOf(9, 3)) for (animal in Animal.entries) {
            val black = EarthLordCourses.of(month, 5, animal).any { it.course == EarthLordCourse.ZHAG_NAG && !it.otherView }
            assertEquals(animal == Animal.BIRD || animal == Animal.RABBIT, black, "$animal day in month $month")
        }
        // The tiger month (the 11th): pig and snake days, as the black months' rule has it for the tiger.
        assertTrue(EarthLordCourses.of(11, 5, Animal.PIG).any { it.course == EarthLordCourse.ZHAG_NAG })
        // The Paṇchen's by year: the pig year's 8th of the first month of spring, the mouse year's 7th of the middle.
        assertTrue(EarthLordCourses.of(11, 8, Animal.OX, Animal.PIG).any { it.course == EarthLordCourse.ZHAG_NAG && it.otherView })
        assertTrue(EarthLordCourses.of(12, 7, Animal.OX, Animal.MOUSE).any { it.course == EarthLordCourse.ZHAG_NAG && it.otherView })
        assertTrue(EarthLordCourses.of(12, 7, Animal.OX, Animal.OX).none { it.course == EarthLordCourse.ZHAG_NAG && it.otherView })
    }

    @Test
    fun `rahu by season`() {
        assertEquals(0, RahuBySeason.of(11, 11)); assertEquals(0, RahuBySeason.of(11, 28))
        assertEquals(4, RahuBySeason.of(3, 13)); assertNull(RahuBySeason.of(10, 2))
    }

    @Test
    fun `every course, event and sky door has its name`() {
        for (suffix in listOf("", "_ru")) {
            val catalog = Catalog.entries(suffix)
            val keys = EarthLordCourse.entries.map { "EarthLordCourse.${it.name}" } + CourseEvent.entries.map { "CourseEvent.${it.name}" } +
                EarthLordCourses.GNAM_SGO.map { "SkyDoor.${it.replace(' ', '_')}" } + "CourseDay.otherView"
            keys.forEach { assertTrue(it in catalog, "$it missing in texts$suffix") }
        }
    }

    @Test
    fun `every course day has its reading's texts`() {
        val english = Catalog.entries("")
        val russian = Catalog.entries("_ru")
        for (month in 1..12) for (date in 1..30) for (animal in Animal.entries) for (d in EarthLordCourses.of(month, date, animal)) {
            val r = Texts.earthLord(d)
            for (key in listOfNotNull(r.key, r.arg) + (r.goodKeys + r.avoidKeys).map { "wording.$it" }) {
                assertTrue(key in english, "$d: $key missing in English")
                assertTrue(key in russian, "$d: $key missing in Russian")
            }
            assertEquals(r.arg != null, english.getValue(r.key).contains("{0}"), "$d: {0} and the arg go together")
        }
    }
}
