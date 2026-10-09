/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.texts

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import zanshin.core.kyureki.Kyureki
import zanshin.core.kyureki.Rekichu
import zanshin.core.kyureki.Tone
import java.time.LocalDate

/** ROADMAP E5, SPEC §7.6: the 旧暦 election lists each day's own annotations for a work, unranked. */
class KyurekiElectionTest {
    @Test
    fun `on 2020–2039 every day's sides are its breakdown's, and the lists hold the good and the disputed days in date order`() {
        var from = LocalDate.of(2020, 1, 1)
        val named = mutableSetOf<Activity>()
        while (from.year < 2040) {
            // With a birth date, so that the personal 三箇の悪日 and 大禍日's own works are read too: a 子 year,
            // since for a 辰 year 大禍日 falls on the 辰 month's 丑 days, its 十死日, which sets it aside (SPEC §7.5).
            val span = KyurekiSpan.of(from, Election.MAX_MONTHS, LocalDate.of(1984, 6, 1))
            for (works in KyurekiElection.WORKS.values) for (work in works) {
                val election = span.election(work)
                for (e in election.days) {
                    // The work's own row of the breakdown, and the row of everything, which names it too.
                    val rows = e.summary.activities.filter { it.activity == work || it.activity == Activity.EVERYTHING }
                    assertEquals(rows.flatMap { it.good }.toSet(), e.good.toSet(), "${e.date} $work")
                    assertEquals(rows.flatMap { it.avoid }.toSet(), e.avoid.toSet(), "${e.date} $work")
                    // Nothing the lower band's rules set aside speaks for the work.
                    val aside = e.summary.setAside.values.flatten().toSet()
                    assertTrue((e.good + e.avoid).none { it in aside }, "${e.date} $work")
                }
                assertEquals(election.days.filter { it.good.isNotEmpty() && it.avoid.isEmpty() }, election.good, "$from $work")
                assertEquals(election.days.filter { it.good.isNotEmpty() && it.avoid.isNotEmpty() }, election.disputed, "$from $work")
                (election.good.zipWithNext() + election.disputed.zipWithNext()).forEach { (a, b) -> assertTrue(a.date < b.date) }
            }
            for ((_, s) in span.days) named += s.activities.map { it.activity }
            // The spans follow one another without a gap, a day per civil day, each its 旧暦 date.
            assertEquals(from, span.days.first().first.date)
            span.days.zipWithNext().forEach { (a, b) -> assertEquals(a.first.date.plusDays(1), b.first.date) }
            from = span.days.last().first.date.plusDays(1)
        }
        // The works offered are every work some day's annotations name, and no other.
        assertEquals(named - Activity.EVERYTHING, KyurekiElection.WORKS.values.flatten().toSet())
    }

    @Test
    fun `a span is the shown day's month and the next ones, whole`() {
        val span = KyurekiSpan.of(LocalDate.of(2026, 10, 1), 3)
        val months = span.days.map { (d, _) -> Triple(d.year, d.month, d.leapMonth) }.distinct()
        assertEquals(listOf(Triple(2026, 8, false), Triple(2026, 9, false), Triple(2026, 10, false)), months)
        assertEquals(Kyureki.monthOf(LocalDate.of(2026, 12, 1)).end.minusDays(1), span.days.last().first.date)
    }

    /**
     * Weddings from 1 October 2026 to the end of the 8th month: each annotation by the rules of SPEC
     * §7.5, checked in `RekichuTest`; 天赦日 on 戊申 in autumn (1 Oct), 不成就日 on the 8th month's 26th,
     * the rokuyō by (month + day) mod 6, 大安 on 8/22 and 8/28.
     */
    @Test
    fun `weddings in the 8th month of 2026`() {
        val e = KyurekiSpan.of(LocalDate.of(2026, 10, 1), 1).election(Activity.WEDDING)
        fun days(list: List<KyurekiElectionDay>) = list.map { it.day.day }
        assertEquals("21± 22+ 23+ 24± 25. 26± 27- 28+ 29+ 30±", e.days.joinToString(" ") { d ->
            "${d.day.day}${when (d.side) { Tone.GOOD -> "+"; Tone.BAD -> "-"; Tone.MIXED -> "±"; else -> "." }}"
        })
        assertEquals(listOf(22, 23, 28, 29), days(e.good))
        assertEquals(listOf(21, 24, 26, 30), days(e.disputed))
        val first = e.days.first()
        assertEquals(listOf("天赦日"), first.good.map { it.kanji })
        assertEquals(listOf("仏滅"), first.avoid.map { it.kanji })
        assertEquals(listOf("大安", "婁宿", "建"), e.days[1].good.map { it.kanji })
        assertTrue("不成就日" in e.days[5].avoid.map { it.kanji })
    }

    /** For 1 June 1976 (辰 year), the 三箇の悪日 of the 辰 month name every work to avoid, so no such day is good for any. */
    @Test
    fun `the person's 三箇の悪日 name every work to avoid`() {
        val birth = LocalDate.of(1976, 6, 1)
        val span = KyurekiSpan.of(LocalDate.of(2027, 3, 8), 3, birth)
        val bad = setOf("大禍日", "狼藉日", "滅門日")
        val marked = span.days.filter { (_, s) -> s.personal.any { it.kanji in bad } }
        assertTrue(marked.isNotEmpty())
        for (works in KyurekiElection.WORKS.values) for (work in works) {
            val election = span.election(work)
            for (d in election.days.filter { it.personal.any { p -> p.kanji in bad } }) {
                assertTrue(d.side == Tone.BAD || d.side == Tone.MIXED, "${d.date} $work")
                assertTrue(d !in election.good)
            }
        }
        // Without the birth date they are not there.
        assertTrue(KyurekiSpan.of(LocalDate.of(2027, 3, 8), 3).days.none { (d, _) -> Rekichu.of(d.date).senjitsu.any { it.kanji in bad } })
    }
}
