/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId

/** SPEC §4: today's page, the Tibetan day from 05:00 at the place, the 旧暦 day from midnight. */
class TodayTest {
    private val kyoto = ZoneId.of("Asia/Tokyo")
    private fun at(zone: ZoneId, day: Int, hour: Int, minute: Int = 0) = LocalDateTime.of(2026, 10, day, hour, minute).atZone(zone).toInstant()

    @Test
    fun `the Tibetan day runs from daybreak at 5 to daybreak at the place`() {
        assertEquals(LocalDate.of(2026, 10, 14), Days.today(CalendarKind.TIBETAN, kyoto, at(kyoto, 14, 22, 30)))
        assertEquals(LocalDate.of(2026, 10, 14), Days.today(CalendarKind.TIBETAN, kyoto, at(kyoto, 15, 0, 9)))
        assertEquals(LocalDate.of(2026, 10, 14), Days.today(CalendarKind.TIBETAN, kyoto, at(kyoto, 15, 4, 59)))
        assertEquals(LocalDate.of(2026, 10, 15), Days.today(CalendarKind.TIBETAN, kyoto, at(kyoto, 15, 5, 0)))
        // 05:30 in Kyoto is 04:30 at Lhasa, before its daybreak: the zone is the place's.
        assertEquals(LocalDate.of(2026, 10, 14), Days.today(CalendarKind.TIBETAN, ZoneId.of("Asia/Shanghai"), at(kyoto, 15, 5, 30)))
    }

    @Test
    fun `the 旧暦 day turns at midnight in the phone's zone`() {
        val phone = ZoneId.systemDefault()
        assertEquals(LocalDate.of(2026, 10, 15), Days.today(CalendarKind.KYUREKI, kyoto, at(phone, 15, 0, 9)))
        assertEquals(LocalDate.of(2026, 10, 14), Days.today(CalendarKind.TIBETAN, phone, at(phone, 15, 0, 9)))
        assertEquals(LocalDate.of(2026, 10, 14), Days.today(CalendarKind.KYUREKI, kyoto, at(phone, 14, 23, 59)))
    }
}
