/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import zanshin.core.time.julianDayNumber
import zanshin.core.vectors
import java.time.LocalDate
import kotlin.math.abs
import kotlin.math.floor

/**
 * The times within the day (SPEC §5.8), against the figures of Edward
 * Henning's calendars (henning-phugpa.tsv: the true weekday, the moon at
 * daybreak, the true sun and the yoga's sum of each day) and against the
 * White Beryl's rules worked by hand from them (docs/sources/almanac-page.md).
 */
class DayTimesTest {
    private val rows by lazy { vectors("henning-phugpa.tsv").filter { it.size > 18 } }

    /** "4;20,10" → 20 + 10/60: the chu tshod part of a figure. */
    private fun chuTshod(f: String) = f.substringAfter(';').split(',').let { it[0].toDouble() + it[1].toDouble() / 60 }

    /** "22;28,5" → 22 + 28/60 + 5/3600: a figure in mansions. */
    private fun mansions(f: String) = f.substringBefore(';').toDouble() + chuTshod(f) / 60

    private fun day(date: String) = TibetanCalendar.of(LocalDate.parse(date), Phugpa.A2_HENNING)

    /** The difference of two places on the 27-mansion circle, in chu srang. */
    private fun srang(a: Double, b: Double) = abs(((a - b) % 27 + 40.5) % 27 - 13.5) * 3600

    @Test
    fun `the day's figures are Henning's to the chu srang`() {
        assertEquals(2245, rows.size)
        for (r in rows) {
            val t = day(r[0])
            val where = "on ${r[0]}"
            assertTrue(srang(t.moon.toDouble() * 27, mansions(r[16])) <= 1.0, "moon $where")
            assertTrue(srang(t.sun.toDouble() * 27, mansions(r[17])) <= 1.0, "sun $where")
            assertTrue(srang((t.moon + t.sun).frac().toDouble() * 27, mansions(r[18])) <= 2.0, "yoga $where")
            if (t.repetition != Repetition.FIRST_OF_TWO && t.omittedBefore == null) {
                val end = Phugpa.trueDate(t.monthCount, t.day, Phugpa.A2_HENNING).frac().toDouble() * 60
                assertTrue(abs(end - chuTshod(r[15])) * 60 <= 1.5, "date's end $where")
            }
        }
    }

    /**
     * WB's rough rule (vol. 1, p. 178), worked on Henning's printed figures: the moon's motion
     * from one daybreak to the next, and the mansion's change where it brings the moon to the
     * next mansion's start. Ours, from our own figures, agree within a tenth of a chu tshod.
     */
    @Test
    fun `mansion changes follow WB's rule on Henning's figures`() {
        var seconds = 0
        for ((r, s) in rows.zipWithNext()) {
            if (LocalDate.parse(s[0]) != LocalDate.parse(r[0]).plusDays(1)) continue
            val a = mansions(r[16])
            var b = mansions(s[16])
            while (b <= a) b += 27
            val expected = ((floor(a).toInt() + 1)..floor(b).toInt()).map { (it - a) / (b - a) * 60 }.filter { it < 60 }
            val ours = DayTimes.mansions(day(r[0]), day(s[0]))
            // A change within a chu srang of the next daybreak goes either way with the figures' rounding.
            if ((expected + ours.map { it.at }).any { it > 59.95 }) continue
            assertEquals(expected.size, ours.size, "changes on ${r[0]}")
            expected.zip(ours).forEach { (e, o) -> assertTrue(abs(e - o.at) < 0.1, "time on ${r[0]}: $e vs ${o.at}") }
            if (ours.size == 2) seconds++
        }
        // A fast moon can enter two mansions in one day.
        assertTrue(seconds > 0)
    }

    @Test
    fun `a skipped yoga is written with its times on the day before`() {
        var skipped = 0
        for ((r, s) in rows.zipWithNext()) {
            if (LocalDate.parse(s[0]) != LocalDate.parse(r[0]).plusDays(1)) continue
            val a = floor(mansions(r[18])).toInt()
            val b = floor(mansions(s[18])).toInt()
            val gap = (b - a + 27) % 27
            val ours = DayTimes.skippedYogas(day(r[0]), day(s[0]))
            assertEquals(if (gap == 2) 1 else 0, ours.size, "skipped yoga on ${r[0]}")
            if (gap == 2) {
                skipped++
                val y = ours.single()
                assertEquals(Yoga.entries[(a + 1) % 27], y.what)
                assertTrue(y.at > 0 && y.until!! in y.at..60.0, "times on ${r[0]}")
            }
        }
        assertTrue(skipped > 50, "$skipped skipped yogas")
    }

    /**
     * WB p. 177: Viṣṭi's span is half the date, the date being (60 − the end of the date before) + its own
     * end; the later half ends with the date, the earlier begins with it, often in the calendar day before.
     */
    @Test
    fun `Visti holds half the date as WB reckons it on Henning's figures`() {
        var checked = 0
        for ((r, s) in rows.zipWithNext()) {
            val d = s[4].toInt()
            if (d !in DayTimes.VISTI_LATER && d !in DayTimes.VISTI_EARLIER) continue
            if (r[4].toInt() != d - 1 || LocalDate.parse(s[0]) != LocalDate.parse(r[0]).plusDays(1)) continue
            if (s[15].contains(";60,") || r[15].contains(";60,")) continue
            val dayBefore = LocalDate.parse(r[0]).julianDayNumber()
            val before = dayBefore + chuTshod(r[15]) / 60
            val own = dayBefore + 1 + chuTshod(s[15]) / 60
            val half = (own - before) / 2
            val expected = if (d in DayTimes.VISTI_LATER) own - half to own else before to before + half
            val t = day(s[0])
            val (a, b) = DayTimes.vistiOf(t.monthCount, d, Phugpa.A2_HENNING)
            assertTrue(abs(a - expected.first) * 60 < 0.05 && abs(b - expected.second) * 60 < 0.05, "Viṣṭi of ${s[0]}: $a–$b vs $expected")
            // Each calendar day it touches shows it.
            for (jd in floor(a).toLong()..floor(b - 1e-9).toLong()) {
                val span = DayTimes.visti(TibetanCalendar.of(jd, Phugpa.A2_HENNING), Phugpa.A2_HENNING)
                assertTrue(span != null && abs(span.start - (a - jd) * 60) < 1e-6, "Viṣṭi of ${s[0]} on $jd")
            }
            checked++
        }
        assertTrue(checked > 400, "$checked Viṣṭi dates")
    }

    @Test
    fun `Visti falls on its dates and on no other day`() {
        val start = LocalDate.of(2026, 1, 1).julianDayNumber()
        for (jd in start until start + 400) {
            val t = TibetanCalendar.of(jd)
            val span = DayTimes.visti(t) ?: continue
            assertTrue(span.end > 0 && span.start < 60)
            // The earlier half can fall wholly into the calendar day before, through a skipped date (WB: «སྔ་ཆས་ཚེས་སྔ་མར། །ཤས་ཆེར་ཕྱིན»).
            val dates = setOf(t.day, t.omittedBefore, t.day + 1, t.day + 2).filterNotNull()
            assertTrue(dates.any { it in DayTimes.VISTI_LATER || it in DayTimes.VISTI_EARLIER }, "Viṣṭi on ${t.day} ($jd)")
        }
        // 2026-02-21, the 4th of the 1st month: the later half, ending with the date at 13;50 (Henning 0;13,50).
        val span = DayTimes.visti(TibetanCalendar.of(LocalDate.of(2026, 2, 21)))!!
        assertTrue(abs(span.end - (13 + 50.0 / 60)) < 0.05, "$span")
    }

    /** WB's measures as read: each kind steps by 2;15, the signs from Aries at 0;0. */
    @Test
    fun `the sun's measures step by a twelfth of the course`() {
        for (kind in DayTimes.SunTermKind.entries) {
            val arcs = DayTimes.SUN_TERMS.filter { it.kind == kind }.sortedBy { it.month ?: it.sign!!.ordinal }.map { it.arc }
            assertEquals(12, arcs.size)
            arcs.zipWithNext().forEach { (a, b) -> assertEquals(135, Math.floorMod(b - a, 1620), "$kind") }
        }
        assertEquals(0, DayTimes.SUN_TERMS.single { it.sign == ZodiacSign.ARIES }.arc)
        assertEquals(36, DayTimes.SUN_TERMS.single { it.kind == DayTimes.SunTermKind.SGANG && it.month == 3 }.arc)
    }

    @Test
    fun `each term falls once a year and in WB's order`() {
        val start = LocalDate.of(2026, 3, 1).julianDayNumber()
        val terms = (start until start + 366).flatMap { DayTimes.sunTerms(TibetanCalendar.of(it)).map { c -> c.what } }
        assertEquals(DayTimes.SUN_TERMS.toSet(), terms.toSet())
        assertTrue(terms.size in 36..37, "${terms.size} terms")
        val order = DayTimes.SUN_TERMS.map { it.arc }.sorted()
        terms.zipWithNext().forEach { (a, b) ->
            assertEquals(order[(order.indexOf(a.arc) + 1) % 36], b.arc, "after $a")
        }
    }

    /**
     * WB p. 182 worked on Henning's 2026 figures: the sun ends the 1st month's 1st date at 21;54,15
     * (Henning) and the 2nd at 21;58,49, beyond the 1st month's dbugs thob at 21;58 by 0;0,49; that
     * excess times 14 is 11;26 chu tshod, taken from the 2nd's end at 19;22 on 19 February: 7;56 after
     * daybreak, 08:10 by hand; 08:09 from the unrounded figures.
     */
    @Test
    fun `a dbugs thob worked by hand`() {
        val t = TibetanCalendar.of(LocalDate.of(2026, 2, 19), Phugpa.A2_HENNING)
        val term = DayTimes.sunTerms(t, Phugpa.A2_HENNING).single()
        assertEquals(DayTimes.SunTermKind.DBUGS_THOB, term.what.kind)
        assertEquals(1, term.what.month)
        val expected = (19 + 22.0 / 60) - (49.0 / 60) * 14
        assertTrue(abs(term.at - expected) < 0.1, "${term.at} vs $expected")
        assertEquals(8 * 60 + 9, DayTimes.clockMinute(term.at), "${term.at}")
    }

    @Test
    fun `the black hours are the four-slayer of the date's animal`() {
        val black = Forces.HOUR_ANIMALS.filter { EarthLordCourses.blackHour(Animal.MOUSE, it) }
        assertEquals(setOf(Animal.RABBIT, Animal.BIRD), black.toSet())
        assertEquals(setOf(Animal.RABBIT, Animal.BIRD), Forces.HOUR_ANIMALS.filter { EarthLordCourses.blackHour(Animal.HORSE, it) }.toSet())
        assertFalse(EarthLordCourses.blackHour(Animal.MOUSE, Animal.MOUSE))
        for (a in Animal.entries) assertEquals(2, Forces.HOUR_ANIMALS.count { EarthLordCourses.blackHour(a, it) })
    }

    /** WB vol. 1, p. 254: each triad's klung rta; vol. 2, p. 235: the hour's bla mkhyen on its triad's. */
    @Test
    fun `the hour's bla mkhyen sits on its triad's klung rta`() {
        val triads = listOf(
            listOf(Animal.TIGER, Animal.HORSE, Animal.DOG) to Animal.MONKEY,
            listOf(Animal.PIG, Animal.SHEEP, Animal.RABBIT) to Animal.SNAKE,
            listOf(Animal.MOUSE, Animal.DRAGON, Animal.MONKEY) to Animal.TIGER,
            listOf(Animal.BIRD, Animal.OX, Animal.SNAKE) to Animal.PIG,
        )
        for ((triad, klung) in triads) for (a in triad) assertEquals(klung, EarthLordCourses.hourBlaMkhyen(a), "$a")
        // The klung rta's element is the luck of the year's four aspects (SPEC §5.9), its animal the element's first.
        for (a in Animal.entries) {
            val luck = Forces.of(Element.WOOD, a).luck
            assertEquals(luck, Forces.of(Element.WOOD, EarthLordCourses.klungRta(a)).vitality, "$a")
        }
    }

    /** WB vol. 2, p. 236, with the upward four-slayer of vol. 1, p. 235 (the mouse's the hare). */
    @Test
    fun `the hour's sa rgyal sits on the four-slayer in front`() {
        assertEquals(Animal.RABBIT, EarthLordCourses.hourSaRgyal(Animal.MOUSE))
        assertEquals(Animal.HORSE, EarthLordCourses.hourSaRgyal(Animal.RABBIT))
        assertEquals(Animal.MOUSE, EarthLordCourses.hourSaRgyal(Animal.BIRD))
        for (a in Animal.entries) assertTrue(EarthLordCourses.blackHour(a, EarthLordCourses.hourSaRgyal(a)), "a four-slayer of $a")
    }

    @Test
    fun `every animal has its place`() {
        for (a in Animal.entries) assertTrue(zanshin.core.texts.gloss(a, "place").isNotBlank())
        assertEquals("upper east", zanshin.core.texts.gloss(Animal.TIGER, "place"))
    }

    /** WB vol. 2, p. 236: the other way, Piling Parma's hours, one a month from the snake. */
    @Test
    fun `the earth king's other way runs one hour a month`() {
        val hours = EarthLordCourses.SA_RGYAL_OTHER.map { it.first }
        assertEquals((0 until 12).map { Animal.entries[(Animal.SNAKE.ordinal + it) % 12] }, hours)
        assertEquals(Animal.SNAKE, EarthLordCourses.hourSaRgyalOther(0, Animal.SNAKE))
        assertEquals(null, EarthLordCourses.hourSaRgyalOther(0, Animal.HORSE))
        assertEquals(Animal.PIG, EarthLordCourses.hourSaRgyalOther(5, Animal.DOG))
    }

    /** WB vol. 2, p. 197: the sky dog's head on the hour's animal, its tail on the seventh. */
    @Test
    fun `the hour's sky dog lies head on its own animal`() {
        for (h in Animal.entries) {
            assertEquals(1, EarthLordCourses.gnamKhyiPart(h, h))
            assertEquals(7, EarthLordCourses.gnamKhyiPart(h, Animal.entries[(h.ordinal + 6) % 12]))
            assertEquals((1..12).toSet(), Animal.entries.map { EarthLordCourses.gnamKhyiPart(h, it) }.toSet())
        }
        assertEquals(12, EarthLordCourses.HIDDEN_LORDS.size)
    }

    /** WB ch. 15's day lengths at the middle terms, and p. 182's 1;10 a sign-month. */
    @Test
    fun `WB's day length at the middle terms`() {
        fun at(month: Int) = DayTimes.dayLength(DayTimes.SUN_TERMS.single { it.kind == DayTimes.SunTermKind.SGANG && it.month == month }.arc.toDouble())
        val expected = mapOf(2 to 30.0, 3 to 31 + 10 / 60.0, 4 to 32 + 20 / 60.0, 5 to 33.5, 8 to 30.0, 11 to 26.5, 12 to 27 + 40 / 60.0, 1 to 28 + 50 / 60.0)
        for ((m, d) in expected) assertTrue(abs(at(m) - d) < 1e-9, "month $m: ${at(m)}")
        assertEquals(14.0, DayTimes.MULTIPLIER)
    }

    /** WB vol. 1, p. 177, entry 11: the date before a burning date, ending in daylight, marks it. */
    @Test
    fun `a burning date can begin in daylight`() {
        val start = LocalDate.of(2000, 1, 1).julianDayNumber()
        var marked = 0
        for (jd in start until start + 50 * 365) {
            val t = TibetanCalendar.of(jd)
            val from = DayTimes.burningFrom(t, DayTimes.dayLength(t)) ?: continue
            marked++
            assertTrue(BurningDate.of(t.weekday, t.day % 30 + 1) && !t.burningDate, "on $jd")
            assertTrue(from in 0.0..DayTimes.dayLength(t) && abs(from - DayTimes.dateEnd(t)!!) < 1e-9)
            assertEquals(null, DayTimes.burningFrom(t, from - 0.01), "not after nightfall")
        }
        assertTrue(marked > 100, "$marked marked burning dates")
    }
}
