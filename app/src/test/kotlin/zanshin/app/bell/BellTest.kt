/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.bell

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import kotlin.math.abs
import kotlin.random.Random

/** The mindfulness bell's times, a sitting's bells and the synthesised strikes (SPEC §10.9). */
class BellTest {
    private val kyoto = ZoneId.of("Asia/Tokyo")
    private val weekdays = DayOfWeek.entries.toSet() - DayOfWeek.SATURDAY - DayOfWeek.SUNDAY
    private val plan = BellPlan(30, false, LocalTime.of(9, 0), LocalTime.of(21, 0), weekdays)

    /** 9 October 2026 is a Friday. */
    private fun at(day: Int, h: Int, m: Int, zone: ZoneId = kyoto): ZonedDateTime = LocalDateTime.of(2026, 10, day, h, m).atZone(zone)

    @Test
    fun `fixed bells fall on the interval from the start of the hours, both ends included`() {
        assertEquals(at(9, 9, 0), plan.next(at(9, 7, 12)))
        assertEquals(at(9, 9, 30), plan.next(at(9, 9, 0)))
        assertEquals(at(9, 13, 30), plan.next(at(9, 13, 1)))
        assertEquals(at(9, 21, 0), plan.next(at(9, 20, 45)))
    }

    @Test
    fun `after the last bell of Friday the next is Monday's first`() {
        assertEquals(at(12, 9, 0), plan.next(at(9, 21, 0)))
        assertEquals(at(12, 9, 0), plan.next(at(10, 12, 0)))
    }

    @Test
    fun `an interval that does not divide the hours stops before their end`() {
        val p = plan.copy(intervalMinutes = 90, to = LocalTime.of(12, 0))
        assertEquals(at(9, 12, 0), p.next(at(9, 10, 31)))
        assertEquals(at(12, 9, 0), p.next(at(9, 12, 0)))
        val q = plan.copy(intervalMinutes = 45, to = LocalTime.of(11, 0))
        assertEquals(at(9, 10, 30), q.next(at(9, 9, 46)))
        assertEquals(at(12, 9, 0), q.next(at(9, 10, 30)))
    }

    @Test
    fun `no day chosen, no bell`() {
        assertNull(plan.copy(days = emptySet()).next(at(9, 10, 0)))
    }

    @Test
    fun `random bells stay within the hours and half an interval either way`() {
        val p = plan.copy(random = true, intervalMinutes = 60)
        val random = Random(7)
        var t = at(9, 6, 0)
        repeat(2000) {
            val next = p.next(t, random)!!
            assertTrue(next.isAfter(t))
            assertTrue(p.isActive(next), "$next outside the hours")
            val gap = java.time.Duration.between(t, next).toMinutes()
            // Within the hours, half to one and a half intervals; from outside them, within one interval of their start.
            if (next.toLocalDate() == t.toLocalDate() && p.isActive(t)) {
                assertTrue(gap in 30..90, "$t → $next")
            } else {
                assertTrue(next.toLocalTime() <= LocalTime.of(10, 0), "$t → $next")
            }
            t = next
        }
    }

    @Test
    fun `a bell in the spring-forward gap rings at the moved clock time`() {
        val berlin = ZoneId.of("Europe/Berlin")
        // 29 March 2026, a Sunday: clocks go from 2:00 to 3:00.
        val p = BellPlan(30, false, LocalTime.of(1, 0), LocalTime.of(4, 0), setOf(DayOfWeek.SUNDAY))
        val before = LocalDateTime.of(2026, 3, 29, 1, 45).atZone(berlin)
        val next = p.next(before)!!
        assertEquals(LocalDateTime.of(2026, 3, 29, 3, 0), next.toLocalDateTime())
        assertTrue(next.isAfter(before))
    }

    @Test
    fun `a sitting's bells, the start, the intervals inside it, the end's strikes`() {
        val s = Sitting(startedAt = 1000, prepareMillis = 10_000, sitMillis = 20 * 60_000, intervalMillis = 5 * 60_000, endStrikes = 3)
        assertEquals(
            listOf(Sitting.Bell(10_000, 1), Sitting.Bell(310_000, 1), Sitting.Bell(610_000, 1), Sitting.Bell(910_000, 1), Sitting.Bell(1_210_000, 3)),
            s.bells,
        )
        assertEquals(listOf(Sitting.Bell(0, 1), Sitting.Bell(60_000, 1)), Sitting(0, 0, 60_000, 0, 1).bells)
        assertEquals(Sitting.Phase.PREPARING, s.phase(1000))
        assertEquals(10_000, s.remaining(1000))
        assertEquals(Sitting.Phase.SITTING, s.phase(11_000))
        assertEquals(20 * 60_000L, s.remaining(11_000))
        assertEquals(Sitting.Phase.ENDED, s.phase(s.endsAt))
    }

    @Test
    fun `a strike peaks below full scale and fades to silence`() {
        for (sound in BellSound.entries) {
            val one = sound.render()
            assertEquals((BellSound.STRIKE_SECONDS * BellSound.SAMPLE_RATE).toInt(), one.size)
            val peak = one.maxOf { abs(it.toInt()) }
            assertTrue(peak in 29_000..29_500, "$sound peak $peak")
            assertTrue(abs(one.first().toInt()) < 100, "$sound starts with a click")
            assertTrue(one.takeLast(10).all { abs(it.toInt()) < 50 }, "$sound ends with a click")
            val three = sound.render(3)
            assertEquals(((2 * BellSound.STRIKE_GAP + BellSound.STRIKE_SECONDS) * BellSound.SAMPLE_RATE).toInt(), three.size)
        }
    }
}
