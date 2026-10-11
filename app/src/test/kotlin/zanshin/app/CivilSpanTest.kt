/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.w3c.dom.Element
import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.xml.parsers.DocumentBuilderFactory

/** ROADMAP U14: the civil span in the heading of an election's month, in each language's own patterns. */
class CivilSpanTest {
    private fun string(values: String, name: String): String {
        val doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(File("src/main/res/$values/strings.xml"))
        val nodes = doc.getElementsByTagName("string")
        return (0 until nodes.length).map { nodes.item(it) as Element }.first { it.getAttribute("name") == name }.textContent
    }

    /** The heading as [values]' strings.xml writes it, for a month from [first] to [last] with the shown day in [year]. */
    private fun heading(values: String, month: String, first: LocalDate, last: LocalDate, year: Int): String {
        val locale = Locale.forLanguageTag(string(values, "locale_tag"))
        val short = DateTimeFormatter.ofPattern(string(values, "pattern_short_date"), locale)
        val withYear = DateTimeFormatter.ofPattern(string(values, "pattern_short_date_year"), locale)
        val (start, end) = civilSpan(first, last, year, short, withYear)
        return String.format(locale, string(values, "election_month_span"), month, start, end)
    }

    @Test
    fun `the heading carries the civil span, the year only where the span leaves the shown day's`() {
        val oct11 = LocalDate.of(2026, 10, 11)
        val nov9 = LocalDate.of(2026, 11, 9)
        assertEquals("9th month · 11 Oct – 9 Nov", heading("values", "9th month", oct11, nov9, 2026))
        // The Tibetan months of a twelve-month span from that day: one crossing the year, one wholly in the next.
        assertEquals("11th month · 9 Dec – 7 Jan 2027", heading("values", "11th month", LocalDate.of(2026, 12, 9), LocalDate.of(2027, 1, 7), 2026))
        assertEquals("12th month · 8 Jan – 6 Feb 2027", heading("values", "12th month", LocalDate.of(2027, 1, 8), LocalDate.of(2027, 2, 6), 2026))
        // Russian: the month in the genitive, abbreviated, as the date picker and the header write it.
        assertEquals("9-й месяц · 11 окт. – 9 нояб.", heading("values-ru", "9-й месяц", oct11, nov9, 2026))
        assertEquals("11-й месяц · 9 дек. – 7 янв. 2027", heading("values-ru", "11-й месяц", LocalDate.of(2026, 12, 9), LocalDate.of(2027, 1, 7), 2026))
    }
}
