/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import zanshin.core.vectors
import java.time.LocalDate

/**
 * Every day of six years of Edward Henning's computed Phugpa calendars
 * (henning-phugpa.tsv). Henning uses the exact a₂ = 3781/105840 (Janson
 * (7.24)), so these checks run with it; the almanac value differs on single
 * days only, which TibetanCalendarTest pins separately.
 */
class HenningPhugpaTest {
    private val rows = vectors("henning-phugpa.tsv")

    /** Henning writes gro bzhin as "gro zhin". */
    private fun mansionName(m: Mansion) = if (m == Mansion.SHRAVANA) "gro zhin" else m.wylie

    /** Henning writes til brdung, the White Beryl's spelling, as "til rdung". */
    private fun karanaName(k: Karana) = if (k == Karana.TAITILA) "til rdung" else k.wylie

    private fun label(e: Enum<*>) = e.name.lowercase().replaceFirstChar { it.uppercase() }

    private val festivalByText = mapOf(
        "Demonstration of Miracles" to TibetanFestival.LOSAR,
        "Revelation of the Kalacakra" to TibetanFestival.KALACAKRA,
        "Birth of the Buddha" to TibetanFestival.BIRTH,
        "Enlightenment and Parinirvana" to TibetanFestival.SAGA_DAWA_DUCHEN,
        "Turning of the Wheel" to TibetanFestival.CHOKHOR_DUCHEN,
        "entry into the womb" to TibetanFestival.ENTRY_INTO_WOMB,
        "Descent of the Buddha" to TibetanFestival.LHABAB_DUCHEN,
    )

    @Test
    fun `dates, mansions, yogas and karanas match every day`() {
        assertEquals(2245, rows.size)
        for (r in rows) {
            val date = LocalDate.parse(r[0])
            val t = TibetanCalendar.of(date, Phugpa.A2_HENNING)
            val where = "on $date"
            assertEquals(listOf(r[1].toInt(), r[2].toInt(), r[3] == "1", r[4].toInt()), listOf(t.year, t.month, t.leapMonth, t.day), where)
            assertEquals(r[5], t.weekday.english.take(3), where)
            assertEquals(r[6], mansionName(t.mansion), where)
            assertEquals(r[7], "${t.weekday.element.english}-${t.mansion.element.english}", where)
            // The almanac omits these entries on the first of two equal dates.
            assertEquals(r[8].isEmpty(), t.repetition == Repetition.FIRST_OF_TWO, "repetition $where")
            if (r[8].isNotEmpty()) {
                assertEquals(r[8], t.yoga.wylie, "yoga $where")
                assertEquals(r[9], karanaName(t.karana), "karana $where")
                assertEquals(r[10], label(t.lunarDayAnimal), "lunar-day animal $where")
                assertEquals(r[11], t.trigram.wylie, "trigram $where")
                assertEquals(r[12].toInt(), t.smeBa, "number $where")
            }
        }
    }

    @Test
    fun `festivals Henning lists fall on the same days`() {
        for (r in rows) {
            if (r[3] == "1") continue // Henning marks festivals in leap months too; SPEC §5.7 does not
            val date = LocalDate.parse(r[0])
            val t = TibetanCalendar.of(date, Phugpa.A2_HENNING)
            val expected = festivalByText.entries.firstOrNull { r.getOrElse(14) { "" }.contains(it.key) }?.value
            val ours = t.holiday?.festival?.takeIf { it in festivalByText.values }
            assertEquals(expected, ours, "festival on $date")
        }
    }
}
