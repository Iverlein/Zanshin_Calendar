/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import zanshin.core.texts.Catalog
import zanshin.core.texts.Reading
import zanshin.core.texts.ReadingRow
import zanshin.core.texts.Texts
import java.util.Locale

/**
 * The hours panel's readings in the shape (SPEC §8.1; ROADMAP X9): each *How it falls* row the shown hour's
 * or the shown day's line, never the table (rule 4), and the remedies rows (rule 3), in English and Russian.
 */
class HourFallTest {
    private val russian = Locale.forLanguageTag("ru")

    private fun <T> inLocale(locale: Locale, block: () -> T): T {
        val before = Catalog.locale
        Catalog.locale = locale
        try {
            return block()
        } finally {
            Catalog.locale = before
        }
    }

    private fun falls(r: Reading, locale: Locale = Locale.ENGLISH) = inLocale(locale) { r.rows.single { it.first == ReadingRow.FALLS }.second }

    @Test
    fun `the earth lords of the hour on its place`() {
        assertEquals("on the place of the hour's own animal; in the hare hour, the lower east", falls(Texts.HOUR_EARTH_LORDS.getValue(Animal.RABBIT)))
        assertEquals("на месте собственного животного часа; в час зайца — нижний восток", falls(Texts.HOUR_EARTH_LORDS.getValue(Animal.RABBIT), russian))
        assertEquals("on the place of the hour's own animal; in the mouse hour, the lower north", falls(Texts.HOUR_HIDDEN.getValue(Animal.MOUSE)))
        // The astrologer spirit on the luck of the hour's triad, the earth king three animals on (WB vol. 2, pp. 235–236).
        assertEquals("in the hare hour, on the place of the snake, the upper south", falls(Texts.HOUR_BLA_MKHYEN.getValue(Animal.RABBIT)))
        assertEquals("in the mouse hour, on the place of the tiger, the upper east", falls(Texts.HOUR_BLA_MKHYEN.getValue(Animal.MOUSE)))
        assertEquals("in the mouse hour, on the place of the hare, the lower east", falls(Texts.HOUR_SA_RGYAL.getValue(Animal.MOUSE)))
        assertEquals("в час мыши — на месте зайца, нижний восток", falls(Texts.HOUR_SA_RGYAL.getValue(Animal.MOUSE), russian))
    }

    @Test
    fun `the sky dog's head and tail`() {
        assertEquals(
            "in the hare hour, the head on its own place, the lower east, and the tail on the place of the bird, the lower west",
            falls(Texts.HOUR_GNAM_KHYI.getValue(Animal.RABBIT)),
        )
        assertEquals(
            "в час зайца голова — на его собственном месте, нижний восток, а хвост — на месте птицы, нижний запад",
            falls(Texts.HOUR_GNAM_KHYI.getValue(Animal.RABBIT), russian),
        )
        for (a in Animal.entries) assertEquals(7, EarthLordCourses.gnamKhyiPart(a, Animal.entries[(a.ordinal + 6) % 12]))
    }

    @Test
    fun `the day's and the person's lines`() {
        assertEquals(
            "the hours of the four-slayers of the date's animal, three animals either side of it; today a mouse day, its hare and bird hours",
            falls(Texts.blackHour(Animal.MOUSE)),
        )
        assertEquals(
            "часы «четвёртых убийц» животного даты, через три животных по обе стороны от него; сегодня день коня, часы зайца и птицы",
            falls(Texts.blackHour(Animal.HORSE), russian),
        )
        assertEquals(
            "the sun's rays rising, noon, the sun's rays gathering and midnight; this one noon, at 11:52",
            falls(Texts.roughTime(ChineseHours.RoughTime.NOON, "11:52")),
        )
        assertEquals("the hour of the birth year's animal; yours the dragon hour", falls(Texts.ownYearHour(Animal.DRAGON)))
        // Sunday's nectar periods: the day's sixth half, the night's second and ninth (docs/sources/nectar-periods.md).
        assertEquals(
            "the halves whose lord is Jupiter, counted from the weekday's planet in sixes by day and in fives by night; " +
                "on Sunday, 10:00–11:00, 18:00–19:00 and 01:00–02:00",
            falls(Texts.nectarPeriods(Weekday.SUNDAY)),
        )
        assertEquals(
            "половины, владыка которых — Юпитер, при счёте от планеты дня недели шестёрками днём и пятёрками ночью; " +
                "воскресенье — 10:00–11:00, 18:00–19:00 и 01:00–02:00",
            falls(Texts.nectarPeriods(Weekday.SUNDAY), russian),
        )
    }

    @Test
    fun `the remedies are rows, the five texts once`() {
        inLocale(Locale.ENGLISH) {
            assertEquals(listOf(ReadingRow.ALSO_CALLED, ReadingRow.REMEDY, ReadingRow.FALLS), Texts.HOUR_EARTH_LORDS.getValue(Animal.TIGER).rows.map { it.first })
            for (r in listOf(Texts.HOUR_BLA_MKHYEN, Texts.HOUR_SA_RGYAL, Texts.HOUR_HIDDEN, Texts.HOUR_GNAM_KHYI).map { it.getValue(Animal.TIGER) } + Texts.blackHour(Animal.MOUSE)) {
                assertEquals(listOf(ReadingRow.REMEDY, ReadingRow.FALLS), r.rows.map { it.first }, r.key)
            }
            // The hour's astrologer spirit points to the day's for its five texts, which stand only there.
            val hour = Texts.HOUR_BLA_MKHYEN.getValue(Animal.TIGER).rows.first().second
            assertEquals(false, "Collected" in hour)
            assertEquals(true, "Collected Dhāraṇīs" in Texts.BLA_MKHYEN.getValue(Direction.NORTH).rows.single { it.first == ReadingRow.REMEDY }.second)
            // The hidden lord's name, with its bracket, fills the family's note.
            assertEquals(true, Texts.HOUR_HIDDEN.getValue(Animal.MOUSE).summary.contains(", Nyentra (གཉན་ཁྲ, gnyan khra), sits on its place."))
        }
    }
}
