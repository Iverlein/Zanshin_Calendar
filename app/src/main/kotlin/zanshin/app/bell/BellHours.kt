/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.bell

import java.time.LocalDate
import java.time.LocalTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

/**
 * The mindfulness bell's active hours as the screen sets them (SPEC §10.9):
 * by half hours, From before Until, from 00:00 to 23:30 within one day. The
 * steppers move them a half hour; a time chosen in the time picker
 * (ROADMAP U17) is rounded to the nearest half hour, and when it passes the
 * other end, that end moves to stay half an hour away.
 */
object BellHours {
    const val STEP_MINUTES = 30L
    val FIRST: LocalTime = LocalTime.MIDNIGHT
    val LAST: LocalTime = LocalTime.of(23, 30)

    /** [t] to the nearest half hour, a quarter past or to rounding up; from 23:45 the day's last, 23:30. */
    fun round(t: LocalTime): LocalTime {
        val minutes = t.hour * 60 + t.minute
        val rounded = (minutes + 15) / 30 * 30
        return if (rounded >= 24 * 60) LAST else LocalTime.of(rounded / 60, rounded % 60)
    }

    /** From set to [picked], rounded; Until moved after it when it no longer is. */
    fun withFrom(picked: LocalTime, to: LocalTime): Pair<LocalTime, LocalTime> {
        val from = minOf(round(picked), LAST.minusMinutes(STEP_MINUTES))
        return from to (if (to > from) to else from.plusMinutes(STEP_MINUTES))
    }

    /** Until set to [picked], rounded; From moved before it when it no longer is. */
    fun withTo(from: LocalTime, picked: LocalTime): Pair<LocalTime, LocalTime> {
        val to = maxOf(round(picked), FIRST.plusMinutes(STEP_MINUTES))
        return (if (from < to) from else to.minusMinutes(STEP_MINUTES)) to to
    }

    /**
     * The switch's subtitle for the bell at [next]: [today] its time alone
     * ("Next bell at 05:00"), another day its weekday and time ("Next bell
     * on Monday at 05:00"), from the screen's two wordings.
     */
    fun nextLine(
        next: ZonedDateTime,
        today: LocalDate,
        clock: DateTimeFormatter,
        locale: Locale,
        todayLine: (time: String) -> String,
        dayLine: (weekday: String, time: String) -> String,
    ): String {
        val time = next.format(clock)
        return if (next.toLocalDate() == today) {
            todayLine(time)
        } else {
            dayLine(next.dayOfWeek.getDisplayName(TextStyle.FULL_STANDALONE, locale), time)
        }
    }
}
