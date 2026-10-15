/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app

import android.content.Context
import zanshin.app.ui.AppTheme
import zanshin.core.astro.Place
import java.time.LocalDate
import java.time.ZoneId
import java.util.Locale
import kotlin.math.abs

enum class CalendarKind { TIBETAN, KYUREKI }

/** The menu's groups, each opened and closed on its own (SPEC §10.5); About & sources stands outside them. */
enum class MenuGroup { CALENDAR, RECKONINGS, PRACTICE, SETTINGS }

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

    /**
     * The saved people and the one chosen, for the personal days of both
     * calendars; kept on the device only. A single birth date saved before
     * there were people reads as one unnamed person, chosen. Each person's
     * relatives are kept apart, by the person's place in the list, so that
     * a list saved before them reads as people without relatives.
     */
    var people: People
        get() {
            val saved = prefs.getString(KEY_PEOPLE, null)
            if (saved != null) {
                val list = People.decode(saved)
                val trees = Relatives.decode(prefs.getString(KEY_RELATIVES, null).orEmpty(), list.size)
                return People.of(list.zip(trees) { p, r -> p.copy(relatives = r) }, prefs.getInt(KEY_PERSON, -1).takeIf { it >= 0 })
            }
            if (!prefs.contains(KEY_BIRTH)) return People()
            return People(listOf(Person("", LocalDate.ofEpochDay(prefs.getLong(KEY_BIRTH, 0)))), 0)
        }
        set(value) = prefs.edit()
            .putString(KEY_PEOPLE, People.encode(value.list))
            .putString(KEY_RELATIVES, Relatives.encode(value.list))
            .putInt(KEY_PERSON, value.active ?: -1)
            .remove(KEY_BIRTH)
            .apply()

    /** The personal 九星気学 row of the 旧暦 view (ROADMAP R2); off by default. */
    var kigaku: Boolean
        get() = prefs.getBoolean(KEY_KIGAKU, false)
        set(value) = prefs.edit().putBoolean(KEY_KIGAKU, value).apply()

    /**
     * How many times each page has been opened with its first-run line (SPEC §10.2, ROADMAP U18): the line shows
     * while this is under [FIRST_RUN_OPENS]; a tap on the line sets it there at once.
     */
    fun firstRunOpens(kind: CalendarKind): Int = prefs.getInt(firstRunKey(kind), 0)

    fun setFirstRunOpens(kind: CalendarKind, opens: Int) = prefs.edit().putInt(firstRunKey(kind), opens).apply()

    private fun firstRunKey(kind: CalendarKind) = "first_run_" + kind.name.lowercase(Locale.ROOT)

    /**
     * Whether the menu's group [group] is open (SPEC §10.5, ROADMAP H3): every group closed on a fresh install, each
     * keeping the state it was last left in, apart from the others.
     */
    fun menuGroupOpen(group: MenuGroup): Boolean = prefs.getBoolean(menuGroupKey(group), false)

    fun setMenuGroupOpen(group: MenuGroup, open: Boolean) = prefs.edit().putBoolean(menuGroupKey(group), open).apply()

    private fun menuGroupKey(group: MenuGroup) = "menu_" + group.name.lowercase(Locale.ROOT)

    /** The colour theme (SPEC §10.5, ROADMAP A1): Ink on every install until another is chosen (D5). */
    var theme: AppTheme
        get() = prefs.getString(KEY_THEME, null)
            ?.let { runCatching { AppTheme.valueOf(it) }.getOrNull() }
            ?: AppTheme.INK
        // Committed at once: the launch reads it back before the first frame, and the splash with it.
        set(value) {
            prefs.edit().putString(KEY_THEME, value.name).commit()
        }

    /** The app's language before Android 13 (see [AppLanguage]); null follows the phone. */
    var language: String?
        get() = prefs.getString(KEY_LANGUAGE, null)
        set(value) {
            // Committed at once: the activity is recreated right after and reads it back.
            prefs.edit().apply { if (value == null) remove(KEY_LANGUAGE) else putString(KEY_LANGUAGE, value) }.commit()
        }

    companion object {
        /** The opens of each page that show its first-run line. */
        const val FIRST_RUN_OPENS = 3
        private const val KEY_LANGUAGE = "language"
        private const val KEY_THEME = "theme"
        private const val KEY_BIRTH = "birth"
        private const val KEY_PEOPLE = "people"
        private const val KEY_PERSON = "person"
        private const val KEY_RELATIVES = "relatives"
        private const val KEY_KIGAKU = "kigaku"
        private const val KEY_CALENDAR = "calendar"
        private const val KEY_LAT = "lat"
        private const val KEY_LON = "lon"
        private const val KEY_ZONE = "zone"
        private const val KEY_LABEL = "label"
    }
}

fun formatCoordinates(latitude: Double, longitude: Double): String = String.format(
    Locale.ROOT,
    "%.2f°%s %.2f°%s",
    abs(latitude), if (latitude >= 0) "N" else "S",
    abs(longitude), if (longitude >= 0) "E" else "W",
)
