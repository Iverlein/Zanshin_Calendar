/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.bell

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import io.github.iverlein.zanshin.R
import zanshin.app.AppLanguage
import zanshin.app.CalendarKind
import zanshin.app.DayInfo
import zanshin.app.Days
import zanshin.app.Labels
import zanshin.app.MainActivity
import zanshin.app.Settings
import zanshin.core.kyureki.DayMark
import zanshin.core.kyureki.Tone
import zanshin.core.texts.Catalog
import zanshin.core.texts.DaySummary
import zanshin.core.texts.HoursShortForm
import zanshin.core.texts.VerdictBy
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

/** When the morning notice comes (SPEC §10.9): at its time, by the phone's clock, every day. */
object NoticePlan {
    /** [time] on the first day it falls strictly after [after], in its zone; a time the clock skips comes as late as the gap. */
    fun next(time: LocalTime, after: ZonedDateTime): ZonedDateTime {
        val today = after.toLocalDate().atTime(time).atZone(after.zone)
        return if (today.isAfter(after)) today else after.toLocalDate().plusDays(1).atTime(time).atZone(after.zone)
    }
}

/**
 * The morning notice (SPEC §10.5, §10.9, ROADMAP N1): at its time one silent notification with the
 * day in brief of the calendar last viewed, the brief's own words. One exact alarm, set again after
 * each notice and, by the bell's [MindfulnessBell.RescheduleReceiver], after a reboot, an update and
 * a change of the clock or zone; the bell's ring service is not used, since the notice makes no sound.
 */
object MorningNotice {
    private const val EXTRA_AT = "at"
    private const val CHANNEL = "notice"
    private const val NOTIFICATION_ID = 3

    /** Sets the alarm for the next notice, or clears it when the notice is off. */
    fun schedule(context: Context) {
        val settings = Settings(context)
        val alarms = context.getSystemService(AlarmManager::class.java)
        alarms.cancel(pendingIntent(context, null))
        val next = if (settings.notice) NoticePlan.next(settings.noticeTime, ZonedDateTime.now()) else null
        settings.nextNotice = next?.toInstant()?.toEpochMilli() ?: 0
        if (next == null) return
        val at = next.toInstant().toEpochMilli()
        setExact(alarms, at, pendingIntent(context, at))
    }

    private fun pendingIntent(context: Context, at: Long?): PendingIntent {
        val intent = Intent(context, ShowReceiver::class.java)
        if (at != null) intent.putExtra(EXTRA_AT, at)
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
    }

    /** The notice's title and lines for today on [calendar], in the app's language. */
    fun text(context: Context, calendar: CalendarKind, now: Instant = Instant.now()): Notice {
        val settings = Settings(context)
        val labels = Labels(context.resources)
        Catalog.locale = labels.locale
        val zone = settings.place?.place?.zone ?: ZoneId.systemDefault()
        val date = Days.today(calendar, zone, now)
        val info = DayInfo.of(date, zone, settings.people.current?.birth)
        val subtitle = date.format(labels.headerDate)
        fun capital(s: String) = s.replaceFirstChar { it.titlecase(labels.locale) }
        return when (calendar) {
            CalendarKind.TIBETAN -> {
                val summary = DaySummary.of(info.tibetan, info.personalDay, info.personalMansions, info.ownDays)
                val verdict = summary.verdict!!
                val by = when (verdict.by) {
                    VerdictBy.COMBINATION -> labels.string(R.string.brief_by_combination)
                    VerdictBy.STRONGEST -> labels.string(R.string.brief_by_factor, verdict.deciding.joinToString { it.kanji })
                }
                val tone = labels.string(if (verdict.tone == Tone.GOOD) R.string.brief_day_good else R.string.brief_day_bad)
                val lines = buildList {
                    if (summary.avoidAll.isNotEmpty()) {
                        add(labels.string(R.string.brief_avoid_all_title))
                        add(labels.string(R.string.brief_row_avoid, summary.avoid.size))
                    } else {
                        add(counts(labels, summary))
                    }
                    // The hours against the day's tone in short form (ROADMAP U11); none on the person's avoidAll day.
                    val against = summary.hoursAgainst
                    if (against.isNotEmpty()) {
                        val label = labels.string(if (verdict.tone == Tone.GOOD) R.string.brief_row_hours_bad else R.string.brief_row_hours_good)
                        add(label + " " + HoursShortForm.of(against))
                    }
                }
                Notice(capital(tone) + " · " + by, lines, subtitle)
            }
            CalendarKind.KYUREKI -> {
                val day = info.kyureki
                val summary = DaySummary.of(day, info.rekichu)
                val lines = buildList {
                    info.rekichu.mark?.let { mark ->
                        add(mark.senjitsu.kanji + " " + labels.string(if (mark == DayMark.BLACK) R.string.kyu_black_day else R.string.kyu_pardon_day))
                    }
                    add(counts(labels, summary))
                }
                Notice("${day.rokuyo.kanji} ${day.rokuyo.romaji} — ${day.rokuyo.english}", lines, subtitle)
            }
        }
    }

    /** "good 53 · avoid 36", as the In brief line counts them. */
    private fun counts(labels: Labels, summary: DaySummary): String =
        labels.string(R.string.brief_row_good, summary.good.size) + HoursShortForm.SEPARATOR + labels.string(R.string.brief_row_avoid, summary.avoid.size)

    /** Posts today's notice for the calendar last viewed; a tap opens that calendar on today. */
    fun show(context: Context) {
        val calendar = Settings(context).calendar
        val notice = text(context, calendar)
        val manager = context.getSystemService(NotificationManager::class.java)
        // Low importance: in the shade and the status bar, no sound and no heads-up.
        val channel = NotificationChannel(CHANNEL, context.getString(R.string.notice_title), NotificationManager.IMPORTANCE_LOW)
        channel.setShowBadge(false)
        manager.createNotificationChannel(channel)
        val open = PendingIntent.getActivity(
            context,
            3,
            Intent(context, MainActivity::class.java)
                .putExtra(MainActivity.EXTRA_SCREEN, MainActivity.SCREEN_TODAY)
                .putExtra(MainActivity.EXTRA_CALENDAR, calendar.name)
                .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP),
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT,
        )
        val notification = NotificationCompat.Builder(context, CHANNEL)
            .setSmallIcon(R.drawable.ic_notice)
            .setContentTitle(notice.title)
            .setContentText(notice.lines.firstOrNull())
            .setSubText(notice.subtitle)
            .setStyle(NotificationCompat.BigTextStyle().bigText(notice.lines.joinToString("\n")))
            .setContentIntent(open)
            .setAutoCancel(true)
            .setSilent(true)
            .setCategory(NotificationCompat.CATEGORY_REMINDER)
            .build()
        runCatching { manager.notify(NOTIFICATION_ID, notification) }
    }

    /** Shows the notice the alarm was set for, then sets the next. */
    class ShowReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val at = intent.getLongExtra(EXTRA_AT, 0)
            schedule(context)
            // A notice is for its own day: one whose day is past, after a change of the clock, is dropped.
            val due = Settings(context).notice && at > 0 &&
                Instant.ofEpochMilli(at).atZone(ZoneId.systemDefault()).toLocalDate() == LocalDate.now()
            if (due) show(AppLanguage.wrap(context))
        }
    }
}

/** The morning notice's [title], the day's tone and reason or the 六曜, its [lines] and the civil date as [subtitle]. */
data class Notice(val title: String, val lines: List<String>, val subtitle: String)

/** An exact alarm at [at] where Android allows one, an inexact one otherwise. */
internal fun setExact(alarms: AlarmManager, at: Long, intent: PendingIntent) {
    if (Build.VERSION.SDK_INT < 31 || alarms.canScheduleExactAlarms()) {
        alarms.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, at, intent)
    } else {
        alarms.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, at, intent)
    }
}
