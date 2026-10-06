/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import java.util.Locale
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import zanshin.core.texts.Catalog
import zanshin.core.texts.Texts
import zanshin.core.vectors

/** Rāhu's course by date, the directions its compass draws (docs/sources/rahu.md). */
class RahuCourseTest {
    private fun english(key: String) = Catalog.text(key, Locale.ENGLISH)
    private fun name(d: Direction) = english("Direction.${d.name}")

    @Test
    fun `the general course of every date, as the White Beryl gives it`() {
        val rows = vectors("white-beryl-rahu.tsv")
        assertEquals((1..30).toList(), rows.map { it[0].toInt() })
        for ((date, from, to) in rows) {
            val expected = when (from) {
                "sky" -> RahuMove.IntoTheLake
                "everywhere" -> RahuMove.Everywhere
                else -> RahuMove.Across(Direction.entries.single { name(it) == from }, Direction.entries.single { name(it) == to })
            }
            assertEquals(expected, RahuCourse.GENERAL[date.toInt()], "date $date")
        }
    }

    @Test
    fun `the detailed course differs from the general only on the 12th and the 18th`() {
        val differ = RahuCourse.DETAILED.filter { (date, move) -> RahuCourse.GENERAL[date] != move }.keys
        assertEquals(setOf(12, 18), differ)
        // Its other dates only turn back: they name no course of their own.
        assertEquals(Texts.RAHU.keys, RahuCourse.DETAILED.keys + setOf(1, 6, 11, 14, 17, 21, 24, 27))
    }

    @Test
    fun `the compass agrees with the reading it stands beside`() {
        fun word(d: Direction) = "(?<![-\\w])${Regex.escape(name(d))}(?![-\\w])"
        for (date in 1..30) {
            val move = RahuCourse.of(date) as? RahuMove.Across ?: continue
            // On a turning-back date the row's reading is the detailed one, which names no course.
            if (RahuCourse.isGeneral(date) && date in Texts.RAHU) continue
            val reading = english(if (RahuCourse.isGeneral(date)) "reading.RahuGeneral.$date" else "reading.Rahu.$date")
            assertTrue(Regex(word(move.from)).containsMatchIn(reading), "date $date comes from ${move.from}: $reading")
            assertTrue(Regex("to (the )?${word(move.to)}").containsMatchIn(reading), "date $date goes to ${move.to}: $reading")
        }
    }
}
