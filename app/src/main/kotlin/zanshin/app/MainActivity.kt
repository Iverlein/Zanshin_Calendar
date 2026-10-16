/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import zanshin.app.ThemeWindow.prepare
import zanshin.app.bell.MorningNotice
import zanshin.app.ui.ZanshinApp
import zanshin.app.ui.ZanshinTheme
import zanshin.app.widget.DayWidget
import zanshin.core.texts.Catalog

class MainActivity : ComponentActivity() {
    /** Counts the requests to show the meditation screen, from the timer's notification. */
    private val meditationRequests = mutableIntStateOf(0)

    /** The last request to show today, from the morning notice: a count, to tell one tap from the next, and its calendar. */
    private val todayRequest = mutableStateOf<TodayRequest?>(null)

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(AppLanguage.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val settings = Settings(this)
        // The stored theme before the first frame (ROADMAP A1): the window's style, the bars and the palette.
        prepare(settings.theme)
        super.onCreate(savedInstanceState)
        val cities = Cities(applicationContext)
        val labels = Labels(resources)
        Catalog.locale = labels.locale
        if (savedInstanceState == null) take(intent)
        // The notice's alarm again, in case it was lost with a force stop, which clears an app's alarms.
        if (settings.notice) MorningNotice.schedule(this)
        setContent {
            CompositionLocalProvider(LocalLabels provides labels) {
                ZanshinTheme {
                    ZanshinApp(settings, cities, meditationRequests.intValue, todayRequest.value)
                }
            }
        }
    }

    /** The widget drawn anew as the app is left (SPEC §10.6): the calendar, person, place, theme or language may have changed. */
    override fun onStop() {
        super.onStop()
        DayWidget.updateLater(this)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        take(intent)
    }

    private fun take(intent: Intent?) {
        when (intent?.getStringExtra(EXTRA_SCREEN)) {
            SCREEN_MEDITATION -> meditationRequests.intValue++
            SCREEN_TODAY -> {
                val calendar = intent.getStringExtra(EXTRA_CALENDAR)?.let { runCatching { CalendarKind.valueOf(it) }.getOrNull() }
                todayRequest.value = TodayRequest((todayRequest.value?.count ?: 0) + 1, calendar ?: Settings(this).calendar)
            }
        }
    }

    companion object {
        const val EXTRA_SCREEN = "screen"
        const val SCREEN_MEDITATION = "meditation"
        const val SCREEN_TODAY = "today"
        const val EXTRA_CALENDAR = "calendar"
    }
}

/** A request to show today's page of [calendar], the [count]th since the activity started. */
data class TodayRequest(val count: Int, val calendar: CalendarKind)
