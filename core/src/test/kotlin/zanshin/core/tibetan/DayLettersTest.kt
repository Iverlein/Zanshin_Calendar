/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import zanshin.core.time.julianDayNumber
import java.text.Normalizer
import java.time.LocalDate

/**
 * The letters of the day (WB vol. 1; docs/sources/day-letters.md) against what WB prints: the model
 * almanac's day boxes (pp. 154–156, the 11th and 12th months, read on the 1996 scans and the Zhol
 * print), the links' table (pp. 150–153) row by row, and the hundred feet's wheel (p. 97).
 */
class DayLettersTest {

    private fun kalacakra(month: Int, date: Int) = DayLetters.kalacakra(month, date).let { "${it.vowel.iast} ${it.syllable.iast} ${it.element} ${it.sense}" }
    private fun nfd(s: String) = Normalizer.normalize(s, Normalizer.Form.NFD)
    private fun svara(date: Int) = DayLetters.svarodaya(date).let { "${it.vowel.iast} ${it.consonant.iast} ${it.element} ${it.sense}" }

    @Test
    fun `the 11th month's Kalacakra line is the model almanac's`() {
        // Each box's top line (img. 164–165; WBZ img. 173–174): date, vowel, syllable, element, sense.
        val printed = mapOf(
            1 to "a kṣa SPACE SOUND", 2 to "i śi WIND TOUCH", 3 to "ṛ ṣṛ FIRE TASTE", 4 to "u hu WATER FORM", 5 to "ḷ sḷ EARTH SMELL",
            6 to "a kṣa SPACE SOUND", 7 to "e śe WIND TOUCH", 8 to "ar ṣar FIRE TASTE", 9 to "o ho WATER FORM", 10 to "al sal EARTH SMELL",
            11 to "ha kṣha SPACE SOUND", 12 to "ya śya WIND TOUCH", 13 to "ra ṣra FIRE TASTE", 14 to "va hva WATER FORM", 15 to "la sla EARTH SMELL",
            17 to "vā śvā WATER FORM", 18 to "rā ṣrā FIRE TASTE", 19 to "yā hyā WIND TOUCH", 20 to "hā shā SPACE SOUND",
            22 to "au śau WATER FORM", 23 to "ār ṣār FIRE TASTE", 24 to "ai hai WIND TOUCH", 25 to "ā sā SPACE SOUND",
            27 to "ū śū WATER FORM", 28 to "ṝ ṣṝ FIRE TASTE", 29 to "ī hī WIND TOUCH", 30 to "ā sā SPACE SOUND",
        )
        for ((date, line) in printed) assertEquals(line, kalacakra(11, date), "11th month, date $date")
        assertEquals(ZodiacSign.SAGITTARIUS, DayLetters.sign(11))
    }

    @Test
    fun `the 12th month takes the ka group in order, as the model almanac writes it`() {
        // img. 166: «ཨ ཀ», «ཨི ཁི», «རྀ གྲྀ», «ཨུ གྷུ», «ལྀ ངླྀ», and the 11th «ཧ ཀྷ».
        val printed = listOf("ཀ", "ཁི", "གྲྀ", "གྷུ", "ངླྀ")
        for ((i, s) in printed.withIndex()) assertEquals(nfd(s), nfd(DayLetters.kalacakra(12, i + 1).syllable.tibetan), "12th month, date ${i + 1}")
        assertEquals(nfd("ཀྷ"), nfd(DayLetters.kalacakra(12, 11).syllable.tibetan))
        assertEquals("ཀྵ", DayLetters.kalacakra(11, 1).syllable.tibetan)
        assertEquals("ཥར", DayLetters.kalacakra(11, 8).syllable.tibetan)
        assertEquals("ཨཱལ", DayLetters.kalacakra(11, 21).vowel.tibetan)
        assertEquals("རྀ", DayLetters.kalacakra(11, 3).vowel.tibetan)
    }

    @Test
    fun `every sign-month has its group, forward in one and back in the next`() {
        // p. 16: Capricorn the ka group, Aquarius back; … Scorpio sa ha ṣa śa kṣa, Sagittarius back.
        assertEquals(listOf("k", "kh", "g", "gh", "ṅ"), (1..5).map { DayLetters.kalacakra(12, it).syllable.iast.dropLast(DayLetters.kalacakra(12, it).vowel.iast.length) })
        assertEquals(listOf("ṅ", "gh", "g", "kh", "k"), (1..5).map { DayLetters.kalacakra(1, it).syllable.iast.dropLast(DayLetters.kalacakra(1, it).vowel.iast.length) })
        assertEquals("ca", DayLetters.kalacakra(2, 1).syllable.iast)
        assertEquals("ña", DayLetters.kalacakra(3, 1).syllable.iast)
        assertEquals("ṭa", DayLetters.kalacakra(4, 1).syllable.iast)
        assertEquals("pa", DayLetters.kalacakra(6, 1).syllable.iast)
        assertEquals("ta", DayLetters.kalacakra(8, 1).syllable.iast)
        assertEquals("sa", DayLetters.kalacakra(10, 1).syllable.iast)
    }

    @Test
    fun `the dbyangs 'char line is the model almanac's, the same in every month`() {
        // Each box's oval (img. 164–166; WBZ img. 173–174): the 1st «ཨ དྷ ས དྲི» to the 30th «ཨོ ད ནམ་མཁའ སྒྲ», long ā ī ū from the 16th.
        val printed = mapOf(
            1 to "a dha EARTH SMELL", 2 to "i na WATER TASTE", 3 to "u pa FIRE FORM", 4 to "e pha WIND TOUCH", 5 to "o ba SPACE SOUND",
            6 to "a bha EARTH SMELL", 7 to "i ma WATER TASTE", 8 to "u ya FIRE FORM", 9 to "e ra WIND TOUCH", 10 to "o la SPACE SOUND",
            11 to "a va EARTH SMELL", 12 to "i śa WATER TASTE", 13 to "u ṣa FIRE FORM", 14 to "e sa WIND TOUCH", 15 to "o ha SPACE SOUND",
            17 to "ī kha WATER TASTE", 18 to "ū ga FIRE FORM", 19 to "e gha WIND TOUCH", 20 to "o ca SPACE SOUND",
            22 to "ī ja WATER TASTE", 23 to "ū jha FIRE FORM", 24 to "e ṭa WIND TOUCH", 25 to "o ṭha SPACE SOUND",
            28 to "ū ta FIRE FORM", 29 to "e tha WIND TOUCH", 30 to "o da SPACE SOUND",
        )
        for ((date, line) in printed) assertEquals(line, svara(date), "date $date")
        assertEquals(nfd("ཛྷ"), nfd(DayLetters.svarodaya(23).consonant.tibetan))
        // WBZ img. 174: the 21st «ཨཱ ཚ», the 22nd «ཨཱི ཛ», the 23rd «ཨཱུ ཛྷ».
        assertEquals(listOf("ཨཱ", "ཨཱི", "ཨཱུ").map(::nfd), (21..23).map { nfd(DayLetters.svarodaya(it).vowel.tibetan) })
        assertEquals("ā cha EARTH SMELL", svara(21))
    }

    @Test
    fun `the stage goes with the five-fold cycle, as the boxes write it`() {
        // The word under each oval (img. 164): byis, gzhon, lang, rgan, smin, then again.
        val printed = listOf(LifeStage.CHILD, LifeStage.YOUTH, LifeStage.PRIME, LifeStage.OLD, LifeStage.RIPE)
        for (date in 1..30) assertEquals(printed[(date - 1) % 5], DayLetters.stage(date), "date $date")
    }

    @Test
    fun `the links' table is WB's, row by row`() {
        val l = Link.entries
        fun row(vararg n: Int) = n.map { l[it - 1] }
        // pp. 150–151 (5th month, Gemini), 152–153 (11th, Sagittarius), and the 10th with its one odd cell.
        val fifth = row(7, 8, 9, 10, 11, 12, 1, 2, 3, 4, 5, 6, 9, 10, 11, 7, 6, 5, 4, 3, 2, 1, 12, 11, 10, 9, 8, 5, 4, 3, 12, 2)
        val eleventh = row(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 3, 4, 5, 1, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 11, 10, 9, 6, 8)
        val tenth = row(12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 2, 3, 4, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 12, 9, 8, 5, 7)
        assertEquals(fifth, (1..32).map { DayLetters.linkAt(5, it) })
        assertEquals(eleventh, (1..32).map { DayLetters.linkAt(11, it) })
        assertEquals(tenth, (1..32).map { DayLetters.linkAt(10, it) })
        // Each row's first link: the model almanac heads the 11th month with ignorance, the 12th with formation.
        assertEquals(Link.IGNORANCE, DayLetters.linkAt(11, 1))
        assertEquals(Link.FORMATION, DayLetters.linkAt(12, 1))
        for (m in 1..12) assertEquals(l[Math.floorMod(m + 1, 12)], DayLetters.linkAt(m, 1))
    }

    @Test
    fun `the rule of p 177 drops and adds columns by the days`() {
        assertEquals(28, DayLetters.columns(28)!!.size)
        assertTrue(15 !in DayLetters.columns(28)!! && 30 !in DayLetters.columns(28)!!)
        assertEquals((1..29).toList(), DayLetters.columns(29))
        assertEquals(32, DayLetters.columns(32)!!.last())
        assertEquals(null, DayLetters.columns(33))
    }

    @Test
    fun `every day of 2000 to 2049 has its link, the sgang's day the row's first`() {
        val start = LocalDate.of(2000, 1, 1).julianDayNumber()
        val end = LocalDate.of(2050, 1, 1).julianDayNumber()
        val lengths = mutableSetOf<Int>()
        val outside = mutableListOf<Long>()
        var jd = start
        while (jd < end) {
            val t = TibetanCalendar.of(jd)
            val d = DayLetters.link(t)
            if (d == null) outside += jd else {
                lengths += d.days
                assertTrue(d.day in 1..d.days)
                if (d.day == 1) assertEquals(DayLetters.linkAt(d.month, 1), d.link)
            }
            jd += 1
        }
        assertEquals(setOf(29, 30, 31, 32), lengths)
        // The one stretch beyond the table: 33 days from the 5th month's sgang of 2009.
        assertEquals(33, outside.size)
        assertEquals(LocalDate.of(2009, 7, 4).julianDayNumber(), outside.first())
    }

    @Test
    fun `the moon's foot is its quarter's syllable on the wheel`() {
        // p. 97: Kṛttikā a i u e; Bharaṇī li lu le lo.
        val start = LocalDate.of(2026, 1, 1).julianDayNumber()
        val seen = mutableSetOf<Pair<Mansion, Int>>()
        for (jd in start until start + 730) {
            val t = TibetanCalendar.of(jd)
            val f = DayLetters.foot(t)
            assertEquals(t.mansion, f.mansion)
            seen += f.mansion to f.quarter
            if (t.mansion == Mansion.KRITTIKA) assertEquals(listOf("a", "i", "u", "e")[f.quarter - 1], f.syllable.iast)
            if (t.mansion == Mansion.BHARANI) assertEquals(listOf("li", "lu", "le", "lo")[f.quarter - 1], f.syllable.iast)
        }
        assertTrue(seen.size > 100, "${seen.size} of the 108 feet")
    }
}
