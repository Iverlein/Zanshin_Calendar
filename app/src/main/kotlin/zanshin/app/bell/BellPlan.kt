/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.bell

import java.time.DayOfWeek
import java.time.Duration
import java.time.LocalTime
import java.time.ZonedDateTime
import kotlin.random.Random

/**
 * When the mindfulness bell rings (SPEC §10.9): every [intervalMinutes]
 * from [from] to [to], both included, on [days]. Fixed bells fall on the
 * interval counted from [from] (9:00, 9:30, 10:00 …); [random] bells come
 * after half to one and a half intervals, the first of a day within one
 * interval of [from].
 */
data class BellPlan(
    val intervalMinutes: Int,
    val random: Boolean,
    val from: LocalTime,
    val to: LocalTime,
    val days: Set<DayOfWeek>,
) {
    init {
        require(intervalMinutes > 0)
        require(from < to) { "the active hours must not cross midnight" }
    }

    /** The first bell strictly after [after], in its zone; null when no day is chosen. */
    fun next(after: ZonedDateTime, random: Random = Random.Default): ZonedDateTime? {
        if (days.isEmpty()) return null
        return if (this.random) nextRandom(after, random) else nextFixed(after)
    }

    private fun nextFixed(after: ZonedDateTime): ZonedDateTime? {
        val zone = after.zone
        for (d in 0L..7L) {
            val date = after.toLocalDate().plusDays(d)
            if (date.dayOfWeek !in days) continue
            var t = from
            while (true) {
                val at = date.atTime(t).atZone(zone)
                if (at.isAfter(after)) return at
                val next = t.plusMinutes(intervalMinutes.toLong())
                if (next <= t || next > to) break
                t = next
            }
        }
        return null
    }

    private fun nextRandom(after: ZonedDateTime, random: Random): ZonedDateTime? {
        val interval = intervalMinutes * 60L
        val candidate = after.plusSeconds(random.nextLong(interval / 2, interval * 3 / 2 + 1))
        if (isActive(candidate)) return candidate
        val start = nextWindowStart(after) ?: return null
        val span = minOf(interval, Duration.between(from, to).seconds)
        return start.plusSeconds(random.nextLong(0, span + 1))
    }

    /** Whether [at] falls in the active hours of a chosen day. */
    fun isActive(at: ZonedDateTime): Boolean {
        val t = at.toLocalTime()
        return at.dayOfWeek in days && t >= from && t <= to
    }

    private fun nextWindowStart(after: ZonedDateTime): ZonedDateTime? {
        for (d in 0L..7L) {
            val date = after.toLocalDate().plusDays(d)
            if (date.dayOfWeek !in days) continue
            val start = date.atTime(from).atZone(after.zone)
            if (start.isAfter(after)) return start
        }
        return null
    }
}
