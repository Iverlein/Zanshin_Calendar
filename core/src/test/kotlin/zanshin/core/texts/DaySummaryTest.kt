/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.texts

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import zanshin.core.kyureki.KyuSei
import zanshin.core.kyureki.Kyureki
import zanshin.core.kyureki.Rekichu
import zanshin.core.kyureki.Rokuyo
import zanshin.core.kyureki.Tone
import zanshin.core.tibetan.TibetanCalendar
import java.time.LocalDate

/** ROADMAP R3: the summary lists only what the readings state, and keeps both sides of a disagreement. */
class DaySummaryTest {
    @Test
    fun `every listed activity is named by the annotations it cites`() {
        var date = LocalDate.of(2026, 1, 1)
        var disputedDays = 0
        while (date.year == 2026) {
            val rk = Rekichu.of(date)
            val s = DaySummary.of(Kyureki.of(date), rk, KyuSei.IPPAKU)
            assertEquals(rk.mark, s.mark, "$date")
            for (n in s.activities) {
                assertTrue(n.good.isNotEmpty() || n.avoid.isNotEmpty(), "$date ${n.activity}")
                n.good.forEach { assertTrue(n.activity in Activities.of(it.reading!!.goodKeys), "$date ${n.activity} ${it.kanji}") }
                n.avoid.forEach { assertTrue(n.activity in Activities.of(it.reading!!.avoidKeys), "$date ${n.activity} ${it.kanji}") }
                assertEquals(n.good.isNotEmpty() && n.avoid.isNotEmpty(), n.disputed)
            }
            val named = s.byTone.values.flatten().flatMap { e -> e.reading?.let { Activities.of(it.goodKeys) + Activities.of(it.avoidKeys) }.orEmpty() }.toSet()
            assertEquals(named, s.activities.map { it.activity }.toSet(), "$date: activities left out")
            val aside = s.setAside.values.flatten()
            assertEquals(rk.setAside.size, aside.size, "$date")
            assertTrue(aside.none { it in s.byTone.values.flatten() }, "$date: a set-aside annotation still counted")
            if (s.activities.any { it.disputed }) disputedDays++
            date = date.plusDays(1)
        }
        // Annotations disagree often enough that hiding either side would change the picture.
        assertTrue(disputedDays > 0)
    }

    @Test
    fun `every family is drawn for some activity`() {
        assertEquals(ActivityFamily.entries.toSet(), Activity.entries.map { it.family }.toSet())
    }

    @Test
    fun `the summary line shows the families of 23 October 2026`() {
        val date = LocalDate.of(2026, 10, 23)
        val s = DaySummary.of(Kyureki.of(date), Rekichu.of(date))
        // 先負, 成, 牛宿, 一粒万倍日, 大明日, 母倉日, 三隣亡, 大犯土: 11 named good, 12 to avoid, building both ways.
        assertEquals(11, s.good.size)
        assertEquals(12, s.avoid.size)
        assertEquals(
            listOf(ActivityFamily.EVERYTHING, ActivityFamily.WEDDING, ActivityFamily.JOURNEY, ActivityFamily.MOVING_HOUSE,
                ActivityFamily.BUILDING, ActivityFamily.TRADE, ActivityFamily.BEGINNING, ActivityFamily.CONDUCT),
            s.goodFamilies,
        )
        assertEquals(
            listOf(ActivityFamily.BUILDING, ActivityFamily.EARTH, ActivityFamily.WELL, ActivityFamily.FIELD, ActivityFamily.AGREEMENT, ActivityFamily.CONDUCT),
            s.avoidFamilies,
        )
        assertEquals(setOf(ActivityFamily.BUILDING), s.disputedFamilies)
    }

    @Test
    fun `the black day of 5 February 2026 counts no other lower-band note`() {
        val date = LocalDate.of(2026, 2, 5)
        val s = DaySummary.of(Kyureki.of(date), Rekichu.of(date))
        assertEquals(listOf("大明日", "天恩日", "復日"), s.setAside.entries.single { it.key.kanji == "受死日" }.value.map { it.kanji })
        val counted = s.byTone.values.flatten().map { it.kanji }
        assertTrue("受死日" in counted)
        assertTrue(listOf("大明日", "天恩日", "復日").none { it in counted }, "$counted")
    }

    @Test
    fun `the Tibetan day of 1 November 2026, weighed`() {
        // Sunday with Ārdrā: no special day; the raven and fire with water, both unlucky, decide (SPEC §5.12).
        val s = DaySummary.of(TibetanCalendar.of(LocalDate.of(2026, 11, 1)))
        assertEquals(DayVerdict(Tone.BAD, VerdictBy.COMBINATION), s.verdict)
        assertEquals(listOf("Raven", "Fire – Water", "Viṣṭi", "Khon"), s.byTone.getValue(Tone.BAD).map { it.kanji })
        assertEquals(setOf(Tone.BAD), s.byTone.keys)
        // Funerals: no combination names them, so Sunday decides, and the weaker factors that agree stand beside it.
        // The day animal is the lunar date's (open question 9), which the funeral list does not name.
        assertEquals(listOf("Sunday", "Ārdrā", "Khon"), s.activities.single { it.activity == Activity.FUNERALS }.avoid.map { it.kanji })
        // Destroying: the element pair, the death combination, names it first.
        assertEquals(listOf("Fire – Water", "Ārdrā", "day 22", "Viṣṭi"), s.activities.single { it.activity == Activity.DESTROYING }.good.map { it.kanji })
    }

    @Test
    fun `where the combinations disagree, the special days decide`() {
        // 18 January 2026: Sunday with Mūla is grub (lucky) but fire with water (unlucky);
        // the demon king and a day of accomplishment, both lucky, settle it (SPEC §5.12).
        val s = DaySummary.of(TibetanCalendar.of(LocalDate.of(2026, 1, 18)))
        assertEquals(DayVerdict(Tone.GOOD, VerdictBy.COMBINATION_DAY), s.verdict)
        assertEquals(listOf("Accomplishment", "Demon king", "Day of accomplishment"), s.byTone.getValue(Tone.GOOD).take(3).map { it.kanji })
    }

    @Test
    fun `every Tibetan activity is decided by the strongest factor that names it`() {
        val rank = DayFactor.entries.map { it.english }
        var date = LocalDate.of(2026, 1, 1)
        while (date.year == 2026) {
            val s = DaySummary.of(TibetanCalendar.of(date))
            assertTrue(s.disputedFamilies.isEmpty(), "$date")
            for (n in s.activities) {
                val side = n.good + n.avoid
                assertTrue(n.good.isEmpty() || n.avoid.isEmpty(), "$date ${n.activity}")
                assertEquals(side.sortedBy { rank.indexOf(it.english) }, side, "$date ${n.activity}: rank order")
            }
            val verdict = s.verdict!!
            assertTrue(s.byTone.keys.all { it == verdict.tone }, "$date: only the day's tone is listed")
            date = date.plusDays(1)
        }
    }

    @Test
    fun `the rokuyo times come from their readings`() {
        assertEquals(setOf(DayTime.AFTERNOON) to setOf(DayTime.MORNING), rokuyoTimes(Rokuyo.SENBU))
        assertEquals(setOf(DayTime.MORNING, DayTime.EVENING) to setOf(DayTime.NOON), rokuyoTimes(Rokuyo.TOMOBIKI))
        assertEquals(setOf(DayTime.NOON) to emptySet<DayTime>(), rokuyoTimes(Rokuyo.SHAKKO))
        assertEquals(emptySet<DayTime>() to emptySet<DayTime>(), rokuyoTimes(Rokuyo.TAIAN))
    }
}
