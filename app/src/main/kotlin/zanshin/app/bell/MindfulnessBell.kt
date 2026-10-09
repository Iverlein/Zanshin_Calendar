/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.bell

import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.os.Build
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

/**
 * The mindfulness bell through the day (SPEC §10.9): one exact alarm at a
 * time, set for the next bell of the [BellPlan]; each ring sets the next.
 */
object MindfulnessBell {
    private const val EXTRA_AT = "at"

    /** A bell more than this late (the phone was off, or asleep past its window) is skipped. */
    private const val STALE_MILLIS = 10 * 60 * 1000L

    /** Sets the alarm for the next bell, or clears it when the bell is off. */
    fun schedule(context: Context) {
        val settings = BellSettings(context)
        val alarms = context.getSystemService(AlarmManager::class.java)
        val pending = pendingIntent(context, null)
        alarms.cancel(pending)
        val next = if (settings.periodic) settings.plan.next(ZonedDateTime.now()) else null
        settings.nextBell = next?.toInstant()?.toEpochMilli() ?: 0
        if (next == null) return
        val at = next.toInstant().toEpochMilli()
        val intent = pendingIntent(context, at)
        if (Build.VERSION.SDK_INT < 31 || alarms.canScheduleExactAlarms()) {
            alarms.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, at, intent)
        } else {
            alarms.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, at, intent)
        }
    }

    private fun pendingIntent(context: Context, at: Long?): PendingIntent {
        val intent = Intent(context, RingReceiver::class.java)
        if (at != null) intent.putExtra(EXTRA_AT, at)
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
    }

    /** Why the bell keeps quiet now, if it does: a sitting, a call, or a silenced phone. */
    private fun quiet(context: Context, settings: BellSettings): Boolean {
        if (MeditationSession.state.value != null) return true
        val audio = context.getSystemService(AudioManager::class.java)
        if (audio.mode == AudioManager.MODE_IN_CALL || audio.mode == AudioManager.MODE_IN_COMMUNICATION) return true
        if (!settings.muteWithPhone) return false
        val filter = context.getSystemService(NotificationManager::class.java).currentInterruptionFilter
        return audio.ringerMode != AudioManager.RINGER_MODE_NORMAL ||
            (filter != NotificationManager.INTERRUPTION_FILTER_ALL && filter != NotificationManager.INTERRUPTION_FILTER_UNKNOWN)
    }

    /** Rings the bell the alarm was set for, then sets the next. */
    class RingReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val settings = BellSettings(context)
            val at = intent.getLongExtra(EXTRA_AT, 0)
            schedule(context)
            val now = System.currentTimeMillis()
            val due = settings.periodic && at > 0 && now - at < STALE_MILLIS &&
                settings.plan.isActive(ZonedDateTime.ofInstant(Instant.ofEpochMilli(at), ZoneId.systemDefault()))
            if (!due || quiet(context, settings)) return
            // The alarm's wake lock holds until finish(); a strike is shorter than a broadcast may take.
            val result = goAsync()
            BellPlayer.ring(context, settings.sound, 1, settings.volume) { result.finish() }
        }
    }

    /** Sets the alarm again after a reboot, an update or a change of the clock or zone. */
    class RescheduleReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            when (intent.action) {
                Intent.ACTION_BOOT_COMPLETED, Intent.ACTION_MY_PACKAGE_REPLACED,
                Intent.ACTION_TIME_CHANGED, Intent.ACTION_TIMEZONE_CHANGED,
                AlarmManager.ACTION_SCHEDULE_EXACT_ALARM_PERMISSION_STATE_CHANGED,
                -> schedule(context)
            }
        }
    }
}
