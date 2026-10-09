/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.bell

import kotlinx.coroutines.flow.MutableStateFlow

/**
 * One sitting of the meditation timer (SPEC §10.9), its times in
 * milliseconds of the elapsed-realtime clock: a quiet preparation, one
 * strike to begin, one strike every [intervalMillis] within the sitting
 * (none when 0), [endStrikes] strikes at its end.
 */
data class Sitting(
    val startedAt: Long,
    val prepareMillis: Long,
    val sitMillis: Long,
    val intervalMillis: Long,
    val endStrikes: Int,
) {
    enum class Phase { PREPARING, SITTING, ENDED }

    /** A bell: when, from [startedAt], and how many strikes. */
    data class Bell(val offset: Long, val strikes: Int)

    val endsAt: Long get() = startedAt + prepareMillis + sitMillis

    val bells: List<Bell>
        get() = buildList {
            add(Bell(prepareMillis, 1))
            if (intervalMillis > 0) {
                var t = prepareMillis + intervalMillis
                while (t < prepareMillis + sitMillis) {
                    add(Bell(t, 1))
                    t += intervalMillis
                }
            }
            add(Bell(prepareMillis + sitMillis, endStrikes))
        }

    fun phase(now: Long): Phase = when {
        now < startedAt + prepareMillis -> Phase.PREPARING
        now < endsAt -> Phase.SITTING
        else -> Phase.ENDED
    }

    /** Milliseconds left of the phase [now] is in: the preparation, or the sitting. */
    fun remaining(now: Long): Long = when (phase(now)) {
        Phase.PREPARING -> startedAt + prepareMillis - now
        Phase.SITTING -> endsAt - now
        Phase.ENDED -> 0
    }
}

/** The sitting under way, null when none; set by [MeditationService], watched by the screen and the periodic bell. */
object MeditationSession {
    val state = MutableStateFlow<Sitting?>(null)
}
