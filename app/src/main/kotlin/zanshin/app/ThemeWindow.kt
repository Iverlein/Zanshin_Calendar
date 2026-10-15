/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app

import android.content.res.Configuration
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.toArgb
import io.github.iverlein.zanshin.R
import zanshin.app.ui.AppTheme
import zanshin.app.ui.Palette
import zanshin.app.ui.colors

/**
 * The window's side of a theme (SPEC §10.1, ROADMAP A1): the style the window is drawn in before Compose
 * (`themes.xml`, so that a light theme's start shows no dark frame), the system bars' icons, and the splash of the
 * next launch. System's style resolves by the phone's dark setting through `values-night`.
 */
object ThemeWindow {
    fun style(theme: AppTheme): Int = when (theme) {
        AppTheme.SYSTEM -> R.style.Theme_Zanshin_System
        AppTheme.INK -> R.style.Theme_Zanshin
        AppTheme.PAPER -> R.style.Theme_Zanshin_Paper
        AppTheme.NIGHT -> R.style.Theme_Zanshin_Night
        AppTheme.MAROON -> R.style.Theme_Zanshin_Maroon
        AppTheme.SEPIA -> R.style.Theme_Zanshin_Sepia
    }

    private fun ComponentActivity.systemDark(): Boolean =
        resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES

    /** Before `super.onCreate`: the window's style, so that the first frame is the theme's page. */
    fun ComponentActivity.prepare(theme: AppTheme) {
        setTheme(style(theme))
        apply(theme)
    }

    /**
     * Draws [theme] at once: the palette every screen reads, the bars' icons dark on a light page, the window behind
     * Compose, and from Android 13 the splash of the next launch (on Android 12 the splash stays the manifest's Ink).
     */
    fun ComponentActivity.apply(theme: AppTheme) {
        val colors = theme.colors(systemDark())
        Palette.colors = colors
        val transparent = android.graphics.Color.TRANSPARENT
        val bars = if (colors.light) SystemBarStyle.light(transparent, transparent) else SystemBarStyle.dark(transparent)
        enableEdgeToEdge(statusBarStyle = bars, navigationBarStyle = bars)
        window.setBackgroundDrawable(ColorDrawable(colors.ink.toArgb()))
        if (Build.VERSION.SDK_INT >= 33) splashScreen.setSplashScreenTheme(style(theme))
    }
}
