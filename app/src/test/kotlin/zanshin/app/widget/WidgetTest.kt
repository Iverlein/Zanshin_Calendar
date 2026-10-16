/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.widget

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId

/** When the widget is drawn anew, and which period it marks (SPEC §10.6). */
class WidgetTest {
    private val kyoto = ZoneId.of("Asia/Tokyo")
    private val lhasa = ZoneId.of("Asia/Shanghai")
    private val berlin = ZoneId.of("Europe/Berlin")

    private fun at(day: Int, h: Int, m: Int, zone: ZoneId = kyoto, month: Int = 10): Instant =
        LocalDateTime.of(2026, month, day, h, m).atZone(zone).toInstant()

    @Test
    fun `the widget changes at each two-hour period's start, on the odd hours from five`() {
        assertEquals(at(16, 5, 0), WidgetPlan.next(at(16, 4, 59), kyoto, kyoto))
        assertEquals(at(16, 7, 0), WidgetPlan.next(at(16, 5, 0), kyoto, kyoto))
        assertEquals(at(16, 11, 0), WidgetPlan.next(at(16, 10, 0), kyoto, kyoto))
        assertEquals(at(16, 11, 0), WidgetPlan.next(at(16, 9, 0), kyoto, kyoto))
        assertEquals(at(17, 3, 0), WidgetPlan.next(at(17, 1, 30), kyoto, kyoto))
    }

    @Test
    fun `and at midnight on the phone's clock, when the 旧暦 day and the civil date turn`() {
        assertEquals(at(17, 0, 0), WidgetPlan.next(at(16, 23, 30), kyoto, kyoto))
        assertEquals(at(17, 1, 0), WidgetPlan.next(at(17, 0, 0), kyoto, kyoto))
    }

    @Test
    fun `the periods are the place's, midnight the phone's`() {
        // Lhasa's clock runs an hour behind Kyoto's: its odd hours are Kyoto's even ones.
        assertEquals(at(16, 12, 0), WidgetPlan.next(at(16, 10, 30), lhasa, kyoto))
        // A phone in Berlin showing Lhasa's hours: Berlin's midnight comes at 06:00 in Lhasa, after its 05:00.
        assertEquals(at(16, 5, 0, lhasa), WidgetPlan.next(at(16, 4, 10, lhasa), lhasa, berlin))
        assertEquals(at(16, 0, 0, berlin), WidgetPlan.next(at(16, 5, 0, lhasa), lhasa, berlin))
        assertEquals(at(17, 0, 0, berlin), WidgetPlan.next(at(16, 23, 10, berlin), lhasa, berlin))
    }

    @Test
    fun `an hour the clock skips is passed over, and an hour it repeats comes once more`() {
        // 29 March 2026: Berlin's 02:00 to 03:00 is skipped, so the period after 01:00 starts at 03:00.
        assertEquals(at(29, 3, 0, berlin, month = 3), WidgetPlan.next(at(29, 1, 30, berlin, month = 3), berlin, berlin))
        // 25 October 2026: 02:00 to 03:00 comes twice; the odd hour 03:00 is the next start after either 02:30.
        val first = LocalDateTime.of(2026, 10, 25, 2, 30).atZone(berlin).withEarlierOffsetAtOverlap().toInstant()
        assertEquals(at(25, 3, 0, berlin), WidgetPlan.next(first, berlin, berlin))
    }

    @Test
    fun `the present period counts from five in the morning of the Tibetan day, and none outside it`() {
        val day = LocalDate.of(2026, 10, 16)
        assertEquals(0, WidgetPlan.period(day, kyoto, at(16, 5, 0)))
        assertEquals(2, WidgetPlan.period(day, kyoto, at(16, 10, 59)))
        assertEquals(9, WidgetPlan.period(day, kyoto, at(17, 0, 30)))
        assertEquals(11, WidgetPlan.period(day, kyoto, at(17, 4, 59)))
        assertNull(WidgetPlan.period(day, kyoto, at(17, 5, 0)))
        assertNull(WidgetPlan.period(day, kyoto, at(16, 4, 59)))
    }
}
