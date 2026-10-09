/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableIntStateOf
import zanshin.app.ui.ZanshinApp
import zanshin.app.ui.ZanshinTheme
import zanshin.core.texts.Catalog

class MainActivity : ComponentActivity() {
    /** Counts the requests to show the meditation screen, from the timer's notification. */
    private val meditationRequests = mutableIntStateOf(0)

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(AppLanguage.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(android.graphics.Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(android.graphics.Color.TRANSPARENT),
        )
        super.onCreate(savedInstanceState)
        val settings = Settings(this)
        val cities = Cities(applicationContext)
        val labels = Labels(resources)
        Catalog.locale = labels.locale
        if (savedInstanceState == null) take(intent)
        setContent {
            CompositionLocalProvider(LocalLabels provides labels) {
                ZanshinTheme {
                    ZanshinApp(settings, cities, meditationRequests.intValue)
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        take(intent)
    }

    private fun take(intent: Intent?) {
        if (intent?.getStringExtra(EXTRA_SCREEN) == SCREEN_MEDITATION) meditationRequests.intValue++
    }

    companion object {
        const val EXTRA_SCREEN = "screen"
        const val SCREEN_MEDITATION = "meditation"
    }
}
