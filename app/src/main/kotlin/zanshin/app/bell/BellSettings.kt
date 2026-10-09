/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.bell

import android.content.Context
import java.time.DayOfWeek
import java.time.LocalTime

/** The meditation timer's and the mindfulness bell's settings (SPEC §10.9); stored on the device only. */
class BellSettings(context: Context) {
    private val prefs = context.getSharedPreferences("zanshin", Context.MODE_PRIVATE)

    var sound: BellSound
        get() = prefs.getString(KEY_SOUND, null)?.let { runCatching { BellSound.valueOf(it) }.getOrNull() } ?: BellSound.BOWL
        set(value) = prefs.edit().putString(KEY_SOUND, value.name).apply()

    /** The bell's loudness on the alarm stream, 0 to 1. */
    var volume: Float
        get() = prefs.getFloat(KEY_VOLUME, 0.7f)
        set(value) = prefs.edit().putFloat(KEY_VOLUME, value.coerceIn(0f, 1f)).apply()

    var sitMinutes: Int
        get() = prefs.getInt(KEY_SIT, 20)
        set(value) = prefs.edit().putInt(KEY_SIT, value).apply()

    var prepareSeconds: Int
        get() = prefs.getInt(KEY_PREPARE, 10)
        set(value) = prefs.edit().putInt(KEY_PREPARE, value).apply()

    /** Minutes between the bells within a sitting; 0: none. */
    var sitIntervalMinutes: Int
        get() = prefs.getInt(KEY_SIT_INTERVAL, 0)
        set(value) = prefs.edit().putInt(KEY_SIT_INTERVAL, value).apply()

    var endStrikes: Int
        get() = prefs.getInt(KEY_END_STRIKES, 3)
        set(value) = prefs.edit().putInt(KEY_END_STRIKES, value).apply()

    var periodic: Boolean
        get() = prefs.getBoolean(KEY_PERIODIC, false)
        set(value) = prefs.edit().putBoolean(KEY_PERIODIC, value).apply()

    var periodicMinutes: Int
        get() = prefs.getInt(KEY_PERIODIC_MINUTES, 60)
        set(value) = prefs.edit().putInt(KEY_PERIODIC_MINUTES, value).apply()

    var periodicRandom: Boolean
        get() = prefs.getBoolean(KEY_PERIODIC_RANDOM, false)
        set(value) = prefs.edit().putBoolean(KEY_PERIODIC_RANDOM, value).apply()

    var from: LocalTime
        get() = LocalTime.ofSecondOfDay(prefs.getInt(KEY_FROM, 9 * 3600).toLong())
        set(value) = prefs.edit().putInt(KEY_FROM, value.toSecondOfDay()).apply()

    var to: LocalTime
        get() = LocalTime.ofSecondOfDay(prefs.getInt(KEY_TO, 21 * 3600).toLong())
        set(value) = prefs.edit().putInt(KEY_TO, value.toSecondOfDay()).apply()

    var days: Set<DayOfWeek>
        get() = prefs.getInt(KEY_DAYS, 0x7f).let { bits -> DayOfWeek.entries.filter { bits and (1 shl it.ordinal) != 0 }.toSet() }
        set(value) = prefs.edit().putInt(KEY_DAYS, value.fold(0) { bits, d -> bits or (1 shl d.ordinal) }).apply()

    /** Whether the bell keeps quiet while the phone is on silent, vibrate or do not disturb. */
    var muteWithPhone: Boolean
        get() = prefs.getBoolean(KEY_MUTE, true)
        set(value) = prefs.edit().putBoolean(KEY_MUTE, value).apply()

    /** The next periodic bell, epoch milliseconds, as last scheduled; 0 when none. */
    var nextBell: Long
        get() = prefs.getLong(KEY_NEXT, 0)
        set(value) = prefs.edit().putLong(KEY_NEXT, value).apply()

    val plan: BellPlan get() = BellPlan(periodicMinutes, periodicRandom, from, to, days)

    private companion object {
        const val KEY_SOUND = "bell_sound"
        const val KEY_VOLUME = "bell_volume"
        const val KEY_SIT = "sit_minutes"
        const val KEY_PREPARE = "sit_prepare"
        const val KEY_SIT_INTERVAL = "sit_interval"
        const val KEY_END_STRIKES = "sit_end_strikes"
        const val KEY_PERIODIC = "bell_periodic"
        const val KEY_PERIODIC_MINUTES = "bell_minutes"
        const val KEY_PERIODIC_RANDOM = "bell_random"
        const val KEY_FROM = "bell_from"
        const val KEY_TO = "bell_to"
        const val KEY_DAYS = "bell_days"
        const val KEY_MUTE = "bell_mute"
        const val KEY_NEXT = "bell_next"
    }
}
