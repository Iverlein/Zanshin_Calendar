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
import android.media.AudioAttributes
import android.media.AudioManager
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
        setExact(alarms, at, pendingIntent(context, at))
    }

    private fun pendingIntent(context: Context, at: Long?): PendingIntent {
        val intent = Intent(context, RingReceiver::class.java)
        if (at != null) intent.putExtra(EXTRA_AT, at)
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
    }

    /** Why the bell keeps quiet now, if it does: a sitting, a call or another app's sound, or a silenced phone. */
    private fun quiet(context: Context, settings: BellSettings): Boolean {
        if (MeditationSession.state.value != null) return true
        val audio = context.getSystemService(AudioManager::class.java)
        if (settings.muteWithPlayback && playing(audio)) return true
        if (!settings.muteWithPhone) return false
        val filter = context.getSystemService(NotificationManager::class.java).currentInterruptionFilter
        return audio.ringerMode != AudioManager.RINGER_MODE_NORMAL ||
            (filter != NotificationManager.INTERRUPTION_FILTER_ALL && filter != NotificationManager.INTERRUPTION_FILTER_UNKNOWN)
    }

    /** A call, or something playing longer than a notification: music, a video, a game, directions, a voice chat, an alarm. */
    private fun playing(audio: AudioManager): Boolean =
        audio.mode in CALL_MODES || audio.isMusicActive ||
            audio.activePlaybackConfigurations.any { it.audioAttributes.usage !in BRIEF_USAGES }

    /** A call ringing, answered, or held by a calling app. */
    private val CALL_MODES = setOf(AudioManager.MODE_RINGTONE, AudioManager.MODE_IN_CALL, AudioManager.MODE_IN_COMMUNICATION)

    /** Sounds too short to keep the bell quiet: notifications, key clicks and the screen reader. */
    @Suppress("DEPRECATION")
    private val BRIEF_USAGES = setOf(
        AudioAttributes.USAGE_NOTIFICATION,
        AudioAttributes.USAGE_NOTIFICATION_EVENT,
        AudioAttributes.USAGE_NOTIFICATION_COMMUNICATION_INSTANT,
        AudioAttributes.USAGE_NOTIFICATION_COMMUNICATION_DELAYED,
        AudioAttributes.USAGE_NOTIFICATION_COMMUNICATION_REQUEST,
        AudioAttributes.USAGE_ASSISTANCE_SONIFICATION,
        AudioAttributes.USAGE_ASSISTANCE_ACCESSIBILITY,
    )

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
            // An exact alarm may start a foreground service from the background; should Android refuse,
            // the bell rings from the broadcast, whose wake lock holds until finish().
            if (BellRingService.start(context)) return
            val result = goAsync()
            BellPlayer.ring(context, settings.sound, 1, settings.volume) { result.finish() }
        }
    }

    /** Sets the bell's alarm and the morning notice's again after a reboot, an update or a change of the clock or zone. */
    class RescheduleReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            when (intent.action) {
                Intent.ACTION_BOOT_COMPLETED, Intent.ACTION_MY_PACKAGE_REPLACED,
                Intent.ACTION_TIME_CHANGED, Intent.ACTION_TIMEZONE_CHANGED,
                AlarmManager.ACTION_SCHEDULE_EXACT_ALARM_PERMISSION_STATE_CHANGED,
                -> {
                    schedule(context)
                    MorningNotice.schedule(context)
                }
            }
        }
    }
}
