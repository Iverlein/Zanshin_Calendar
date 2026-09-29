/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app

import android.content.Context
import zanshin.core.astro.Place
import java.time.LocalDate
import java.time.ZoneId
import java.util.Locale
import kotlin.math.abs

enum class CalendarKind { TIBETAN, KYUREKI }

/** Where the sky line is computed for, as the owner chose it (SPEC §10.5). */
data class SavedPlace(val label: String, val place: Place)

/** Small persistent settings; stored on the device only. */
class Settings(context: Context) {
    private val prefs = context.getSharedPreferences("zanshin", Context.MODE_PRIVATE)

    var calendar: CalendarKind
        get() = prefs.getString(KEY_CALENDAR, null)
            ?.let { runCatching { CalendarKind.valueOf(it) }.getOrNull() }
            ?: CalendarKind.TIBETAN
        set(value) = prefs.edit().putString(KEY_CALENDAR, value.name).apply()

    var place: SavedPlace?
        get() {
            if (!prefs.contains(KEY_LAT)) return null
            val zone = runCatching { ZoneId.of(prefs.getString(KEY_ZONE, null)) }.getOrElse { ZoneId.systemDefault() }
            return SavedPlace(
                label = prefs.getString(KEY_LABEL, null) ?: "",
                place = Place(
                    java.lang.Double.longBitsToDouble(prefs.getLong(KEY_LAT, 0)),
                    java.lang.Double.longBitsToDouble(prefs.getLong(KEY_LON, 0)),
                    zone,
                ),
            )
        }
        set(value) {
            prefs.edit().apply {
                if (value == null) {
                    remove(KEY_LAT); remove(KEY_LON); remove(KEY_ZONE); remove(KEY_LABEL)
                } else {
                    putLong(KEY_LAT, java.lang.Double.doubleToRawLongBits(value.place.latitude))
                    putLong(KEY_LON, java.lang.Double.doubleToRawLongBits(value.place.longitude))
                    putString(KEY_ZONE, value.place.zone.id)
                    putString(KEY_LABEL, value.label)
                }
            }.apply()
        }

    /** Birth date, for the personal days of both calendars; kept on the device only. */
    var birthDate: LocalDate?
        get() = if (prefs.contains(KEY_BIRTH)) LocalDate.ofEpochDay(prefs.getLong(KEY_BIRTH, 0)) else null
        set(value) = prefs.edit().apply {
            if (value == null) remove(KEY_BIRTH) else putLong(KEY_BIRTH, value.toEpochDay())
        }.apply()

    /** The personal 九星気学 row of the 旧暦 view (ROADMAP R2); off by default. */
    var kigaku: Boolean
        get() = prefs.getBoolean(KEY_KIGAKU, false)
        set(value) = prefs.edit().putBoolean(KEY_KIGAKU, value).apply()

    private companion object {
        const val KEY_BIRTH = "birth"
        const val KEY_KIGAKU = "kigaku"
        const val KEY_CALENDAR = "calendar"
        const val KEY_LAT = "lat"
        const val KEY_LON = "lon"
        const val KEY_ZONE = "zone"
        const val KEY_LABEL = "label"
    }
}

fun formatCoordinates(latitude: Double, longitude: Double): String = String.format(
    Locale.ROOT,
    "%.2f°%s %.2f°%s",
    abs(latitude), if (latitude >= 0) "N" else "S",
    abs(longitude), if (longitude >= 0) "E" else "W",
)
