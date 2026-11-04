/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.wheel

import android.content.Context
import android.content.SharedPreferences
import zanshin.app.bell.BellSettings

/** The prayer wheel's settings (SPEC §10.12), one set for the screen and the wallpaper; stored on the device only. */
class WheelSettings(context: Context) {
    val prefs: SharedPreferences = context.getSharedPreferences("zanshin", Context.MODE_PRIVATE)

    var mantra: WheelMantra
        get() = enumOf(KEY_MANTRA, WheelMantra.MANI)
        set(value) = prefs.edit().putString(KEY_MANTRA, value.name).apply()

    /** The user's own line, for [WheelMantra.OWN]. */
    var ownLine: String
        get() = prefs.getString(KEY_OWN, null).orEmpty()
        set(value) = prefs.edit().putString(KEY_OWN, BellSettings.cleanMessage(value)).apply()

    var form: WheelForm
        get() = enumOf(KEY_FORM, WheelForm.DRUM)
        set(value) = prefs.edit().putString(KEY_FORM, value.name).apply()

    var script: WheelScript
        get() = enumOf(KEY_SCRIPT, WheelScript.GOLD)
        set(value) = prefs.edit().putString(KEY_SCRIPT, value.name).apply()

    var ground: WheelGround
        get() = enumOf(KEY_GROUND, WheelGround.BLACK)
        set(value) = prefs.edit().putString(KEY_GROUND, value.name).apply()

    /** What the wheel shows now; an own line left empty shows the first mantra. */
    val look: WheelLook
        get() {
            val text = mantra.text ?: ownLine.ifEmpty { WheelMantra.MANI.text.orEmpty() }
            return WheelLook(text, form, script.argb, ground.argb)
        }

    private inline fun <reified T : Enum<T>> enumOf(key: String, default: T): T =
        prefs.getString(key, null)?.let { name -> enumValues<T>().firstOrNull { it.name == name } } ?: default

    companion object {
        private const val KEY_MANTRA = "wheel_mantra"
        private const val KEY_OWN = "wheel_own"
        private const val KEY_FORM = "wheel_form"
        private const val KEY_SCRIPT = "wheel_script"
        private const val KEY_GROUND = "wheel_ground"

        /** Whether a preference key is one of the wheel's, for the wallpaper to draw it anew. */
        fun isWheelKey(key: String?): Boolean = key?.startsWith("wheel_") == true
    }
}
