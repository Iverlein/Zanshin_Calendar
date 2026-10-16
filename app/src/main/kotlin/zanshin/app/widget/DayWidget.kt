/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.widget

import android.app.AlarmManager
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.util.SizeF
import android.util.TypedValue
import android.view.View
import android.widget.RemoteViews
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.toArgb
import io.github.iverlein.zanshin.R
import zanshin.app.AppLanguage
import zanshin.app.CalendarKind
import zanshin.app.DayInfo
import zanshin.app.Days
import zanshin.app.Labels
import zanshin.app.MainActivity
import zanshin.app.Settings
import zanshin.app.ui.AppTheme
import zanshin.app.ui.InkColors
import zanshin.app.ui.PaperColors
import zanshin.app.ui.ZanshinColors
import zanshin.app.ui.colors
import zanshin.core.kyureki.DayMark
import zanshin.core.kyureki.Tone
import zanshin.core.texts.Catalog
import zanshin.core.texts.DaySummary
import zanshin.core.texts.HoursShortForm
import zanshin.core.texts.Texts
import zanshin.core.texts.VerdictBy
import zanshin.core.tibetan.risingSign
import java.time.Duration
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import kotlin.concurrent.thread

/** When the widget's day can change (SPEC §10.6), and which two-hour period is running. */
object WidgetPlan {
    /**
     * The first moment after [after] at which the widget shows something else: the next two-hour period's start on
     * the clock of [place], the zone whose hours the Tibetan page shows (each period begins on an odd hour, the
     * first at 05:00, when the Tibetan day turns, SPEC §4), or the next midnight on the phone's own clock [civil],
     * when the 旧暦 day and the civil date turn, whichever comes first. An hour the clock skips is passed over.
     */
    fun next(after: Instant, place: ZoneId, civil: ZoneId): Instant {
        var hour = after.atZone(place).truncatedTo(ChronoUnit.HOURS).plusHours(1)
        while (hour.hour % 2 == 0) hour = hour.plusHours(1)
        val midnight = after.atZone(civil).toLocalDate().plusDays(1).atStartOfDay(civil)
        return minOf(hour.toInstant(), midnight.toInstant())
    }

    /** The two-hour period of the Tibetan day [date] running at [now] in [zone], counted from 05:00; null outside the day. */
    fun period(date: LocalDate, zone: ZoneId, now: Instant): Int? {
        val since = Duration.between(date.atTime(Days.DAWN).atZone(zone).toInstant(), now).toMinutes()
        return if (since in 0 until 24 * 60) (since / 120).toInt() else null
    }
}

/**
 * The home-screen widget (SPEC §10.6, ROADMAP W1), in two sizes, for the calendar last viewed and in the app's
 * theme, reading the day as the page does ([DayInfo], [DaySummary]) with nothing weighed anew. Plain RemoteViews:
 * the app's alarms already keep its times, so the widget needs no library of its own and no permission more.
 * Updated by one alarm at [WidgetPlan.next], not waking the phone (a widget is seen only with the screen on, and a
 * missed alarm fires as the phone wakes); when the app is left, so that a change of calendar, person, place, theme
 * or language shows at once; and by the bell's [zanshin.app.bell.MindfulnessBell.RescheduleReceiver] after a reboot,
 * an update and a change of the clock or zone. A tap opens the app on the widget's calendar at today.
 */
object DayWidget {
    private const val REQUEST_OPEN = 4
    private const val REQUEST_TICK = 5

    /** Every widget of both sizes, drawn now, and the alarm for the next change; none placed, the alarm is cleared. */
    fun update(context: Context) {
        val manager = AppWidgetManager.getInstance(context)
        val small = manager.getAppWidgetIds(ComponentName(context, Small::class.java))
        val wide = manager.getAppWidgetIds(ComponentName(context, Wide::class.java))
        val alarms = context.getSystemService(AlarmManager::class.java)
        alarms.cancel(tick(context))
        if (small.isEmpty() && wide.isEmpty()) return
        val now = Instant.now()
        val localized = AppLanguage.wrap(context)
        if (small.isNotEmpty()) manager.updateAppWidget(small, sized(localized, wide = false, now))
        if (wide.isNotEmpty()) manager.updateAppWidget(wide, sized(localized, wide = true, now))
        val zone = Settings(context).place?.place?.zone ?: ZoneId.systemDefault()
        val at = WidgetPlan.next(now, zone, ZoneId.systemDefault()).toEpochMilli()
        // Not waking: the next look at the home screen wakes the phone, and the alarm fires then.
        if (Build.VERSION.SDK_INT < 31 || alarms.canScheduleExactAlarms()) {
            alarms.setExact(AlarmManager.RTC, at, tick(context))
        } else {
            alarms.set(AlarmManager.RTC, at, tick(context))
        }
    }

    /** [update] off the main thread, for the activity and the receivers; a receiver passes its [done]. */
    fun updateLater(context: Context, done: () -> Unit = {}) {
        val app = context.applicationContext
        thread(name = "widget") {
            try {
                update(app)
            } finally {
                done()
            }
        }
    }

    private fun tick(context: Context): PendingIntent = PendingIntent.getBroadcast(
        context,
        REQUEST_TICK,
        Intent(context, TickReceiver::class.java),
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT,
    )

    private fun open(context: Context, calendar: CalendarKind): PendingIntent = PendingIntent.getActivity(
        context,
        REQUEST_OPEN,
        Intent(context, MainActivity::class.java)
            .putExtra(MainActivity.EXTRA_SCREEN, MainActivity.SCREEN_TODAY)
            .putExtra(MainActivity.EXTRA_CALENDAR, calendar.name)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP),
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT,
    )

    /**
     * One size's views, full and, from Android 12, compact for a cell the full one does not fit, which the launcher
     * picks by the widget's size as it stands: a landscape home screen's cells are far shorter and narrower than a
     * portrait one's. Before Android 12 the full one stands at every size.
     */
    private fun sized(context: Context, wide: Boolean, now: Instant): RemoteViews {
        val full = views(context, wide, now, compact = false)
        if (Build.VERSION.SDK_INT < 31) return full
        val compact = views(context, wide, now, compact = true)
        return if (wide) {
            RemoteViews(mapOf(SizeF(250f, 150f) to full, SizeF(110f, 80f) to compact))
        } else {
            RemoteViews(mapOf(SizeF(110f, 105f) to full, SizeF(110f, 50f) to compact))
        }
    }

    /**
     * One size's views for today on the calendar last viewed, in the app's language [context] and theme; [compact]
     * leaves out what the cell has no room for: on the small size the date and calendar's line, the reason and the
     * padding's height, the 旧暦's counts on one line; on the wide size the reason beside the tone, set on a line of its own, the clock hours under
     * the strip and the present period's line, the mark under the strip still showing it.
     */
    private fun views(context: Context, wide: Boolean, now: Instant, compact: Boolean): RemoteViews {
        val settings = Settings(context)
        val labels = Labels(context.resources)
        Catalog.locale = labels.locale
        val calendar = settings.calendar
        val zone = settings.place?.place?.zone ?: ZoneId.systemDefault()
        val date = Days.today(calendar, zone, now)
        val info = DayInfo.of(date, zone, settings.people.current?.birth)
        val v = RemoteViews(context.packageName, if (wide) R.layout.widget_wide else R.layout.widget_small)
        val paint = Paint(v, settings.theme, context)
        val spoken = mutableListOf<String>()

        paint.tint(R.id.widget_page) { it.ink }
        // The header's date without its year, "Fri 16 Oct", which the small size has room for beside the calendar.
        val dateText = date.format(labels.headerDates[1])
        v.setTextViewText(R.id.widget_date, dateText)
        paint.text(R.id.widget_date) { it.muted }
        val tibetan = calendar == CalendarKind.TIBETAN
        val calendarName = if (tibetan) labels.string(R.string.calendar_tibetan) else "旧暦"
        v.setTextViewText(R.id.widget_calendar, calendarName)
        paint.text(R.id.widget_calendar) { if (tibetan) it.saffron else it.vermilion }
        spoken += calendarName
        spoken += dateText
        paint.text(R.id.widget_day) { it.text }
        paint.text(R.id.widget_month) { it.text }
        // Each calendar's rows shown and the other's hidden, every time: the launcher applies an update to the views it
        // already holds, so a row hidden for one calendar stays hidden for the other unless it is shown again.
        val shown = { b: Boolean -> if (b) View.VISIBLE else View.GONE }
        v.setViewVisibility(R.id.widget_tone_row, shown(tibetan))
        if (wide) {
            v.setViewVisibility(R.id.widget_reason, shown(tibetan && !compact))
            v.setViewVisibility(R.id.widget_reason_separator, shown(tibetan && !compact))
            v.setViewVisibility(R.id.widget_reason_line, shown(tibetan && compact))
            v.setViewVisibility(R.id.widget_hours, shown(tibetan))
            v.setViewVisibility(R.id.widget_kyureki, shown(!tibetan))
            v.setViewVisibility(R.id.widget_clocks, shown(!compact))
            v.setViewVisibility(R.id.widget_now_row, shown(!compact))
        } else {
            v.setViewVisibility(R.id.widget_top, shown(!compact))
            v.setViewVisibility(R.id.widget_reason, shown(tibetan && !compact))
            v.setViewVisibility(R.id.widget_counts, shown(!tibetan && !compact))
            v.setViewVisibility(R.id.widget_counts_line, shown(!tibetan && compact))
            v.setInt(R.id.widget_avoid_all, "setMaxLines", if (compact) 1 else 2)
            // The layout's own padding, its height cut to 4 dp in the compact variant.
            val dp = context.resources.displayMetrics.density
            val vertical = ((if (compact) 4 else 10) * dp).toInt()
            v.setViewPadding(R.id.widget_content, (16 * dp).toInt(), vertical, (14 * dp).toInt(), vertical)
        }

        when (calendar) {
            CalendarKind.TIBETAN -> {
                val day = info.tibetan
                val month = labels.month(day.month, day.leapMonth)
                v.setTextViewText(R.id.widget_day, "${day.day}")
                v.setTextViewText(R.id.widget_month, month)
                spoken += "${day.day}, $month"
                val summary = DaySummary.of(day, info.personalDay, info.personalMansions, info.ownDays)
                val verdict = summary.verdict!!
                val tone = labels.string(if (verdict.tone == Tone.GOOD) R.string.brief_day_good else R.string.brief_day_bad)
                val by = when (verdict.by) {
                    VerdictBy.COMBINATION -> labels.string(R.string.brief_by_combination)
                    VerdictBy.STRONGEST -> labels.string(R.string.brief_by_factor, verdict.deciding.joinToString { it.kanji })
                }
                v.setTextViewText(R.id.widget_tone, tone)
                v.setTextViewText(R.id.widget_reason, by)
                paint.tint(R.id.widget_dot) { toneColor(it, verdict.tone) }
                paint.text(R.id.widget_tone) { it.text }
                paint.text(R.id.widget_reason) { it.muted }
                if (wide) {
                    paint.text(R.id.widget_reason_separator) { it.muted }
                    v.setTextViewText(R.id.widget_reason_line, by)
                    paint.text(R.id.widget_reason_line) { it.muted }
                }
                spoken += "$tone, $by"
                // The person's enemy weekday or death mansion: every work to avoid, beside the day's tone (ROADMAP E6).
                val avoidAll = summary.avoidAll.isNotEmpty()
                v.setViewVisibility(R.id.widget_avoid_all, if (avoidAll) View.VISIBLE else View.GONE)
                if (avoidAll) {
                    val text = labels.string(R.string.brief_avoid_all)
                    v.setTextViewText(R.id.widget_avoid_all, text)
                    paint.text(R.id.widget_avoid_all) { it.bad }
                    spoken += text
                }
                if (wide) spoken += hours(v, paint, labels, summary, day.month, WidgetPlan.period(date, zone, now))
            }
            CalendarKind.KYUREKI -> {
                val day = info.kyureki
                val month = labels.month(day.month, day.leapMonth)
                v.setTextViewText(R.id.widget_day, "${day.day}")
                v.setTextViewText(R.id.widget_month, month)
                spoken += "${day.day}, $month"
                // The 旧暦 has no tone (SPEC §7.5): the In brief line's counts stand where the Tibetan day's tone does.
                v.setViewVisibility(R.id.widget_avoid_all, View.GONE)
                val summary = DaySummary.of(day, info.rekichu)
                val good = labels.string(R.string.brief_row_good, summary.good.size)
                val avoid = labels.string(R.string.brief_row_avoid, summary.avoid.size)
                v.setTextViewText(R.id.widget_good_count, good)
                v.setTextViewText(R.id.widget_avoid_count, avoid)
                paint.text(R.id.widget_good_count) { it.good }
                paint.text(R.id.widget_avoid_count) { it.bad }
                if (!wide) {
                    v.setTextViewText(R.id.widget_good_line, good)
                    v.setTextViewText(R.id.widget_avoid_line, avoid)
                    paint.text(R.id.widget_good_line) { it.good }
                    paint.text(R.id.widget_avoid_line) { it.bad }
                }
                if (wide) {
                    val rokuyo = "${day.rokuyo.kanji} ${day.rokuyo.romaji} — ${day.rokuyo.english}"
                    v.setTextViewText(R.id.widget_rokuyo, rokuyo)
                    paint.text(R.id.widget_rokuyo) { it.text }
                    spoken += rokuyo
                    val mark = info.rekichu.mark
                    v.setViewVisibility(R.id.widget_mark, if (mark != null) View.VISIBLE else View.GONE)
                    if (mark != null) {
                        val text = mark.senjitsu.kanji + " " + labels.string(if (mark == DayMark.BLACK) R.string.kyu_black_day else R.string.kyu_pardon_day)
                        v.setTextViewText(R.id.widget_mark, text)
                        paint.text(R.id.widget_mark) { if (mark == DayMark.BLACK) it.bad else it.good }
                        spoken += text
                    }
                }
                spoken += "$good, $avoid"
            }
        }
        // The phone's font scale up to [MAX_FONT_SCALE]: the cells do not grow with the font, and past it the lines
        // that matter most are cut short; the page holds every line at any scale (SPEC §10.1).
        val scale = minOf(context.resources.configuration.fontScale, MAX_FONT_SCALE)
        for ((id, size) in if (wide) WIDE_SIZES else SMALL_SIZES) v.setTextViewTextSize(id, TypedValue.COMPLEX_UNIT_DIP, size * scale)
        v.setOnClickPendingIntent(R.id.widget_root, open(context, calendar))
        v.setContentDescription(R.id.widget_root, spoken.joinToString(". "))
        return v
    }

    /**
     * The wide widget's hours (ROADMAP W1, U11): the twelve periods from 05:00 coloured by their combination
     * period's tone, as the page's strip has them, the present one [current] marked under its cell; the present
     * period's clock hours, rising sign and verdict; the hours against the day's tone in short form, as the hours
     * row gives them. Returns what a screen reader hears of them.
     */
    private fun hours(v: RemoteViews, paint: Paint, labels: Labels, summary: DaySummary, month: Int, current: Int?): List<String> {
        val signs = (0 until 12).map { risingSign(month, it) }
        val tones = signs.map { Texts.DUS_SBYOR.getValue(it).first }
        for (i in 0 until 12) {
            // The page's 80 % tone, laid over the page here: a tint with alpha would show the white shape under it.
            paint.tint(CELLS[i]) { toneColor(it, tones[i]).copy(alpha = 0.8f).compositeOver(it.ink) }
            v.setViewVisibility(MARKS[i], if (i == current) View.VISIBLE else View.INVISIBLE)
            if (i == current) paint.tint(MARKS[i]) { it.saffron }
        }
        for (id in CLOCKS) paint.text(id) { it.faint }
        val spoken = mutableListOf<String>()
        if (current != null) {
            val span = HoursShortForm.startHour(current).two() + "–" + HoursShortForm.startHour(current + 1).two()
            val verdict = labels.string(if (tones[current] == Tone.GOOD) R.string.hours_period_good else R.string.hours_period_bad)
            val now = labels.string(R.string.widget_now, span, signs[current].english, verdict)
            v.setTextViewText(R.id.widget_now, now)
            paint.text(R.id.widget_now) { it.muted }
            paint.tint(R.id.widget_now_dot) { toneColor(it, tones[current]) }
            spoken += now
        }
        // On an unlucky day the hours to be accomplished, on a lucky day those to avoid; none on the person's avoidAll day.
        val against = summary.hoursAgainst
        v.setViewVisibility(R.id.widget_against_row, if (against.isEmpty()) View.GONE else View.VISIBLE)
        if (against.isNotEmpty()) {
            val good = summary.verdict!!.tone == Tone.BAD
            // Under the strip, the hours row's own short words, as on the page: "avoid 05–09 · 11–13".
            val label = labels.string(if (good) R.string.brief_good else R.string.brief_avoid)
            val spans = HoursShortForm.of(against)
            v.setTextViewText(R.id.widget_against_label, label)
            v.setTextViewText(R.id.widget_against, spans)
            paint.text(R.id.widget_against_label) { if (good) it.good else it.bad }
            paint.text(R.id.widget_against) { it.text }
            spoken += "$label $spans"
        }
        return spoken
    }

    private fun Int.two() = toString().padStart(2, '0')

    private fun toneColor(c: ZanshinColors, tone: Tone): Color = when (tone) {
        Tone.GOOD -> c.good
        Tone.BAD -> c.bad
        Tone.MIXED -> c.mixed
        Tone.NEUTRAL -> c.faint
    }

    /** The largest font scale the widget's text follows (ROADMAP W1): the wide size's lines fit a 4 × 2 cell at it in Russian, the longer of the two. */
    private const val MAX_FONT_SCALE = 1.15f

    /** Each text's size before the font scale, in sp as the layouts set it; the day's numeral is set in dp there. */
    private val SMALL_SIZES = listOf(
        R.id.widget_date to 12f, R.id.widget_calendar to 12f, R.id.widget_month to 14f, R.id.widget_tone to 13f,
        R.id.widget_reason to 13f, R.id.widget_avoid_all to 13f, R.id.widget_good_count to 13f, R.id.widget_avoid_count to 13f,
        R.id.widget_good_line to 13f, R.id.widget_avoid_line to 13f,
    )
    private val WIDE_SIZES = listOf(
        R.id.widget_month to 15f, R.id.widget_calendar to 12f, R.id.widget_date to 12f, R.id.widget_tone to 13f,
        R.id.widget_reason_separator to 13f, R.id.widget_reason to 13f, R.id.widget_reason_line to 13f, R.id.widget_avoid_all to 13f,
        R.id.widget_clock_0 to 11f, R.id.widget_clock_1 to 11f, R.id.widget_clock_2 to 11f, R.id.widget_clock_3 to 11f,
        R.id.widget_now to 12f, R.id.widget_against_label to 12f, R.id.widget_against to 12f,
        R.id.widget_rokuyo to 14f, R.id.widget_mark to 13f, R.id.widget_good_count to 13f, R.id.widget_avoid_count to 13f,
    )

    private val CELLS = intArrayOf(
        R.id.widget_cell_0, R.id.widget_cell_1, R.id.widget_cell_2, R.id.widget_cell_3, R.id.widget_cell_4, R.id.widget_cell_5,
        R.id.widget_cell_6, R.id.widget_cell_7, R.id.widget_cell_8, R.id.widget_cell_9, R.id.widget_cell_10, R.id.widget_cell_11,
    )
    private val MARKS = intArrayOf(
        R.id.widget_mark_0, R.id.widget_mark_1, R.id.widget_mark_2, R.id.widget_mark_3, R.id.widget_mark_4, R.id.widget_mark_5,
        R.id.widget_mark_6, R.id.widget_mark_7, R.id.widget_mark_8, R.id.widget_mark_9, R.id.widget_mark_10, R.id.widget_mark_11,
    )
    private val CLOCKS = intArrayOf(R.id.widget_clock_0, R.id.widget_clock_1, R.id.widget_clock_2, R.id.widget_clock_3)

    /**
     * Colours on the views by the app's theme (ROADMAP A1). System follows the phone's dark setting: from Android 12
     * each colour is given for both, Paper by day and Ink by night, and the launcher switches them as the phone
     * does; before it the setting at the update stands until the next.
     */
    private class Paint(private val views: RemoteViews, theme: AppTheme, context: Context) {
        private val both = theme == AppTheme.SYSTEM && Build.VERSION.SDK_INT >= 31
        private val day: ZanshinColors = if (both) PaperColors else theme.colors(
            context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES,
        )

        fun text(id: Int, role: (ZanshinColors) -> Color) = set(id, "setTextColor", role)

        fun tint(id: Int, role: (ZanshinColors) -> Color) = set(id, "setColorFilter", role)

        private fun set(id: Int, method: String, role: (ZanshinColors) -> Color) {
            if (both && Build.VERSION.SDK_INT >= 31) {
                views.setColorInt(id, method, role(day).toArgb(), role(InkColors).toArgb())
            } else {
                views.setInt(id, method, role(day).toArgb())
            }
        }
    }

    /** The small widget's provider: the launcher's add, resize and the system's update after a boot. */
    class Small : Provider()

    /** The wide widget's provider. */
    class Wide : Provider()

    /** Both sizes are drawn together, whichever the launcher asks for. */
    open class Provider : AppWidgetProvider() {
        override fun onUpdate(context: Context, manager: AppWidgetManager, ids: IntArray) {
            val result = goAsync()
            updateLater(context) { result.finish() }
        }

        override fun onDisabled(context: Context) {
            val result = goAsync()
            updateLater(context) { result.finish() }
        }
    }

    /** The alarm at the next change, and the phone's language changed. */
    class TickReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val result = goAsync()
            updateLater(context) { result.finish() }
        }
    }
}
