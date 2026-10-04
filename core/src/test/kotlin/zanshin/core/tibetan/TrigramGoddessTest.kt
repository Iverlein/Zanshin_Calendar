/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate

/**
 * The White Beryl's eight goddesses of the date (vol. 1, pp. 449–450, img.
 * 459–460; docs/sources/lunar-day-signs.md): its deeper count, by the date and
 * the month's animal, names the same day as the date's trigram (Janson E.10).
 */
class TrigramGoddessTest {
    /** The goddesses in the order the text counts them from the 1st. */
    private val order = listOf(
        "'od 'bar ma", "bstan ma", "dkar gsal ma", "mdangs ldan ma",
        "char 'bebs ma", "g.yo med ma", "'od 'chang ma", "skyob byed ma",
    )

    /** Who has the 1st, by the month's animal (img. 460). */
    private fun first(month: Animal): String = when (month) {
        Animal.TIGER, Animal.HORSE, Animal.DOG -> "'od 'bar ma"
        Animal.PIG, Animal.SHEEP, Animal.RABBIT -> "'od 'chang ma"
        Animal.BIRD, Animal.OX, Animal.SNAKE -> "dkar gsal ma"
        Animal.MOUSE, Animal.DRAGON, Animal.MONKEY -> "char 'bebs ma"
    }

    @Test
    fun `the goddess of every date is the goddess of its trigram`() {
        var date = LocalDate.of(2026, 1, 1)
        while (date.year < 2028) {
            val day = TibetanCalendar.of(date)
            val goddess = order[(order.indexOf(first(day.monthNames.animal)) + day.day - 1) % 8]
            assertEquals(goddess, day.trigram.goddess, "$date, month ${day.month} (${day.monthNames.animal}), date ${day.day}")
            date = date.plusDays(1)
        }
    }

    @Test
    fun `the trigrams follow the goddesses' order`() {
        assertEquals(order, Trigram.entries.map { it.goddess })
    }
}
