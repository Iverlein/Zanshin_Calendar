/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.bell

import kotlinx.coroutines.flow.MutableStateFlow

/**
 * One sitting of the meditation timer (SPEC §10.9), its [plan] begun at
 * [startedAt], milliseconds of the elapsed-realtime clock.
 */
data class Sitting(val startedAt: Long, val plan: SessionPlan) {
    enum class Phase { WARM_UP, SITTING, ENDED }

    /** A bell: when, from [startedAt], how many strikes, and whether it is the turn between two periods. */
    data class Bell(val offset: Long, val strikes: Int, val between: Boolean = false)

    private val warmUp: Long get() = plan.warmUpSeconds * 1000L

    /** Where each period ends, from [startedAt]. */
    private val ends: List<Long> get() = plan.periods.runningFold(warmUp) { at, s -> at + s * 1000L }.drop(1)

    val endsAt: Long get() = startedAt + ends.last()

    val bells: List<Bell>
        get() = buildList {
            add(Bell(warmUp, 1))
            for (end in ends.dropLast(1)) add(Bell(end, plan.betweenStrikes, between = true))
            add(Bell(ends.last(), plan.endStrikes))
        }

    fun phase(now: Long): Phase = when {
        now < startedAt + warmUp -> Phase.WARM_UP
        now < endsAt -> Phase.SITTING
        else -> Phase.ENDED
    }

    /** The period [now] falls in, from 0; the last after the end. */
    fun period(now: Long): Int = ends.indexOfFirst { now < startedAt + it }.let { if (it < 0) ends.lastIndex else it }

    /** Milliseconds left of the warm-up or of the period [now] is in. */
    fun remaining(now: Long): Long = when (phase(now)) {
        Phase.WARM_UP -> startedAt + warmUp - now
        Phase.SITTING -> startedAt + ends[period(now)] - now
        Phase.ENDED -> 0
    }

    /** The length of the warm-up or of the period [now] is in, milliseconds. */
    fun length(now: Long): Long = if (phase(now) == Phase.WARM_UP) warmUp else plan.periods[period(now)] * 1000L
}

/** The sitting under way, null when none; set by [MeditationService], watched by the screen and the periodic bell. */
object MeditationSession {
    val state = MutableStateFlow<Sitting?>(null)
}
