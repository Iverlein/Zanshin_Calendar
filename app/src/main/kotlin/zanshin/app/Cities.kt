/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app

import android.content.Context
import java.text.Normalizer
import java.time.ZoneId
import zanshin.core.astro.Place

data class City(val name: String, val country: String, val latitude: Double, val longitude: Double, val zone: String) {
    fun toSavedPlace(): SavedPlace = SavedPlace(
        label = "$name · ${formatCoordinates(latitude, longitude)}",
        place = Place(latitude, longitude, runCatching { ZoneId.of(zone) }.getOrElse { ZoneId.systemDefault() }),
    )
}

/**
 * Offline city search over the bundled GeoNames list (assets/cities.tsv,
 * largest cities first). Loaded once, on first search.
 */
class Cities(private val context: Context) {
    private class Entry(val city: City, val keys: List<String>)

    private val entries: List<Entry> by lazy {
        context.assets.open("cities.tsv").bufferedReader().useLines { lines ->
            lines.filter { !it.startsWith("#") }.mapNotNull { line ->
                val f = line.split('\t')
                if (f.size < 6) return@mapNotNull null
                val city = City(f[0], f[2], f[3].toDouble(), f[4].toDouble(), f[5])
                Entry(city, listOf(fold(f[0]), fold(f[1])).distinct())
            }.toList()
        }
    }

    /** Loads the list if it is not loaded yet; call off the main thread. */
    fun preload() {
        entries.size
    }

    /**
     * Cities whose name, or any word of it, starts with [query]; at most
     * [limit]. Exact names first, then names starting with the query, then
     * word matches; each group largest city first.
     */
    fun search(query: String, limit: Int = 20): List<City> {
        val q = fold(query.trim())
        if (q.isEmpty()) return emptyList()
        return entries.asSequence()
            .mapNotNull { e ->
                val rank = e.keys.minOf { key ->
                    when {
                        key == q -> 0
                        key.startsWith(q) -> 1
                        key.contains(" $q") || key.contains("-$q") -> 2
                        else -> 3
                    }
                }
                if (rank < 3) rank to e.city else null
            }
            .sortedBy { it.first }
            .take(limit)
            .map { it.second }
            .toList()
    }

    private fun fold(s: String): String {
        if (s.all { it.code < 0x80 }) return s.lowercase()
        return Normalizer.normalize(s, Normalizer.Form.NFD).replace(MARKS, "").lowercase()
    }

    private companion object {
        val MARKS = Regex("\\p{Mn}+")
    }
}
