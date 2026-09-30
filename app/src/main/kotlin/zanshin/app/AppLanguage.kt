/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app

import android.app.Activity
import android.app.LocaleManager
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.LocaleList
import java.util.Locale

/**
 * The app's own language, apart from the phone's (SPEC §10.5). Android 13 and
 * later keep it themselves (per-app language, also in Settings › Apps);
 * before that it is stored in [Settings] and applied by [wrap] when the
 * activity starts. Null: follow the phone, English when it is not offered.
 */
object AppLanguage {
    /** The languages with a translation, as BCP 47 tags; the list in res/xml/locales_config.xml. */
    val TAGS = listOf("en", "ru")

    /** A language's name in that language, as a language list shows it. */
    fun nativeName(tag: String): String = Locale.forLanguageTag(tag).let { it.getDisplayName(it) }.replaceFirstChar { it.titlecase() }

    fun current(context: Context): String? =
        if (Build.VERSION.SDK_INT >= 33) {
            context.getSystemService(LocaleManager::class.java).applicationLocales.takeIf { !it.isEmpty }?.get(0)?.language
        } else {
            Settings(context).language
        }

    /** Sets the language and shows the activity in it. */
    fun set(activity: Activity, tag: String?) {
        if (Build.VERSION.SDK_INT >= 33) {
            // The system recreates the activity in the new language.
            activity.getSystemService(LocaleManager::class.java).applicationLocales =
                if (tag == null) LocaleList.getEmptyLocaleList() else LocaleList.forLanguageTags(tag)
        } else {
            Settings(activity).language = tag
            activity.recreate()
        }
    }

    /** The activity's base context in the stored language, before Android 13. */
    fun wrap(base: Context): Context {
        if (Build.VERSION.SDK_INT >= 33) return base
        val tag = Settings(base).language ?: return base
        val config = Configuration(base.resources.configuration)
        config.setLocales(LocaleList.forLanguageTags(tag))
        return base.createConfigurationContext(config)
    }
}
