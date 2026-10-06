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
import zanshin.core.tibetan.CombinationDay
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
        // 先負, 成, 牛宿, 一粒万倍日, 大明日, 母倉日, 三隣亡, 大犯土: 11 named good, 12 to avoid, building both ways;
        // the disputes 牛宿 avoids are drawn apart from agreements.
        assertEquals(11, s.good.size)
        assertEquals(12, s.avoid.size)
        assertEquals(
            listOf(ActivityFamily.EVERYTHING, ActivityFamily.WEDDING, ActivityFamily.JOURNEY, ActivityFamily.MOVING_HOUSE,
                ActivityFamily.BUILDING, ActivityFamily.TRADE, ActivityFamily.BEGINNING, ActivityFamily.CONDUCT),
            s.goodFamilies,
        )
        assertEquals(
            listOf(ActivityFamily.BUILDING, ActivityFamily.EARTH, ActivityFamily.WELL, ActivityFamily.FIELD, ActivityFamily.DISPUTE, ActivityFamily.CONDUCT),
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
        // Sunday with Ārdrā: the raven and fire with water, both unlucky, are the day's result (WB p. 333, SPEC §5.12).
        val s = DaySummary.of(TibetanCalendar.of(LocalDate.of(2026, 11, 1)))
        assertEquals(DayVerdict(Tone.BAD, VerdictBy.COMBINATION), s.verdict)
        // The trigram has no tone of its own and is not among the factors of the day's tone.
        assertEquals(listOf("Raven", "Fire – Water", "Viṣṭi"), s.byTone.getValue(Tone.BAD).map { it.kanji })
        assertEquals(setOf(Tone.BAD), s.byTone.keys)
        // Funerals: every factor that names them avoids them; the trigram's list stands beside them.
        assertEquals(listOf("Sunday", "Ārdrā", "Khon"), s.activities.single { it.activity == Activity.FUNERALS }.avoid.map { it.kanji })
        // Destroying: the element pair, the death combination, names it good itself; Rāhu's autumn course
        // (the 22nd of the 9th month: fierce work good, WB p. 239) and the others that agree stand beside it.
        assertEquals(listOf("Fire – Water", "Rāhu", "Ārdrā", "day 22", "Viṣṭi"), s.activities.single { it.activity == Activity.DESTROYING }.good.map { it.kanji })
        // The bad combination makes the day unlucky, but does not decide works it does not name: bathing, which
        // Sunday, Ārdrā and the 22nd name good, stays good.
        assertEquals(listOf("Sunday", "Ārdrā", "day 22"), s.activities.single { it.activity == Activity.BATHING }.good.map { it.kanji })
        // Study: Rāhu, Sunday and Ārdrā name it good, the pig to avoid; Rāhu, the strongest, decides. The 22nd names
        // it both ways (learning writing, astrology and crafts good; study to avoid) and says nothing.
        val study = s.activities.single { it.activity == Activity.STUDY }
        assertEquals(listOf("Rāhu", "Sunday", "Ārdrā"), study.good.map { it.kanji })
        assertEquals(listOf("Pig"), study.outweighed.map { it.kanji })
    }

    @Test
    fun `where the combinations disagree, the strongest factor decides`() {
        // 28 January 2026: Wednesday with Kṛttikā is grub (lucky) but fire with water (unlucky). Wednesday, the special
        // days (the demon king and a day of accomplishment, one voice), Gara and Śubha are lucky; the 10th is not.
        // Wednesday, the strongest with a tone, decides (WB vol. 2, p. 376: by strength, not by number).
        val s = DaySummary.of(TibetanCalendar.of(LocalDate.of(2026, 1, 28)))
        assertEquals(DayVerdict(Tone.GOOD, VerdictBy.STRONGEST), s.verdict)
        assertEquals(listOf("Wednesday", "Demon king", "Day of accomplishment", "Gara", "Śubha"), s.byTone.getValue(Tone.GOOD).map { it.kanji })
        // An activity goes the same way: building, which Wednesday names good, is avoided by Kṛttikā, the 10th and the
        // snake; three weaker factors do not outweigh the planet.
        val building = s.activities.single { it.activity == Activity.BUILDING }
        assertEquals(listOf("Wednesday"), building.good.map { it.kanji })
        assertEquals(listOf("Kṛttikā", "day 10", "Snake"), building.outweighed.map { it.kanji })
    }

    @Test
    fun `special days that disagree say nothing`() {
        // 18 January 2026: Sunday with Mūla, grub but fire with water; the demon king and a day of accomplishment
        // are lucky, the Rdo rje gtsug lag makes it a day of discord too, so the special days take no side.
        // Sunday is mixed and the 30th unlucky; Catuṣpada and Dhruva are lucky, but the date is stronger than both.
        val day = TibetanCalendar.of(LocalDate.of(2026, 1, 18))
        assertEquals(listOf(CombinationDay.MI_MTHUN_NYI), day.gtsugLagDays)
        val s = DaySummary.of(day)
        assertEquals(DayVerdict(Tone.BAD, VerdictBy.STRONGEST), s.verdict)
        assertEquals(listOf("day 30"), s.byTone.getValue(Tone.BAD).map { it.kanji })
    }

    @Test
    fun `as many on each side, the strongest still decides`() {
        // 19 May 2026: Tuesday with Rohiṇī, rtag myos (lucky) but earth with fire (unlucky). Tuesday and Atigaṇḍa are
        // unlucky, the 3rd and Taitila lucky: Tuesday, the strongest of them, decides.
        val s = DaySummary.of(TibetanCalendar.of(LocalDate.of(2026, 5, 19)))
        assertEquals(DayVerdict(Tone.BAD, VerdictBy.STRONGEST), s.verdict)
        assertEquals(listOf("Tuesday", "Atigaṇḍa"), s.byTone.getValue(Tone.BAD).map { it.kanji })
        // Two that disagree on a work: the planet leads the mansion (the kun phan me long's rule 2).
        val pillars = s.activities.single { it.activity == Activity.CONSECRATION }
        assertEquals(listOf("Tuesday"), pillars.avoid.map { it.kanji })
        assertEquals(listOf("Rohiṇī"), pillars.outweighed.map { it.kanji })
    }

    @Test
    fun `every Tibetan activity is weighed as the day is`() {
        val rank = DayFactor.entries.map { it.english }
        var date = LocalDate.of(2026, 1, 1)
        while (date.year == 2026) {
            val day = TibetanCalendar.of(date)
            val s = DaySummary.of(day)
            val verdict = s.verdict!!
            assertTrue(s.disputedFamilies.isEmpty(), "$date")
            assertEquals(setOf(verdict.tone), s.byTone.keys, "$date: only the day's tone is listed")
            val pair = Texts.ELEMENT_PAIR.getValue(day.elementPair)
            for (n in s.activities) {
                val side = n.good + n.avoid
                assertTrue(n.good.isEmpty() || n.avoid.isEmpty(), "$date ${n.activity}")
                assertEquals(side.sortedBy { rank.indexOf(it.english) }, side, "$date ${n.activity}: rank order")
                // A work the element pair names goes its way, whatever the others say.
                if (n.activity in Activities.of(pair.goodKeys)) {
                    assertTrue(n.good.any { it.english == DayFactor.ELEMENT_PAIR.english }, "$date ${n.activity}: the pair names it")
                }
            }
            date = date.plusDays(1)
        }
    }

    @Test
    fun `the Tibetan line of 1 November 2026 shows the heaviest works`() {
        val s = DaySummary.of(TibetanCalendar.of(LocalDate.of(2026, 11, 1)))
        val (good, avoid) = s.row()
        // Destroying: the element pair (10), Rāhu (9), Ārdrā (7), the 22nd (5) and Viṣṭi (4).
        assertEquals(35, good.first().weight)
        assertEquals(
            listOf(ActivityFamily.DESTROYING, ActivityFamily.LEARNING, ActivityFamily.BUILDING, ActivityFamily.EARTH),
            good.map { it.activity.family },
        )
        assertEquals(
            listOf(ActivityFamily.WEDDING, ActivityFamily.SACRED, ActivityFamily.DISPUTE, ActivityFamily.LIVESTOCK),
            avoid.map { it.activity.family },
        )
    }

    @Test
    fun `the Tibetan line shows each family once, heaviest first`() {
        var date = LocalDate.of(2026, 1, 1)
        while (date.year == 2026) {
            val s = DaySummary.of(TibetanCalendar.of(date))
            val (good, avoid) = s.row()
            val families = (good + avoid).map { it.activity.family }
            assertEquals(families.distinct(), families, "$date: a family twice")
            assertTrue(families.size <= DaySummary.ROW_SLOTS, "$date")
            assertTrue(good.all { it.good.isNotEmpty() } && avoid.all { it.avoid.isNotEmpty() }, "$date: a work on the wrong side")
            for (side in listOf(good, avoid)) assertEquals(side.sortedByDescending { it.weight }, side, "$date: heaviest first")
            // Half the places each, unless a side has fewer families to show.
            val goodFamilies = s.good.map { it.activity.family }.toSet()
            val avoidFamilies = s.avoid.map { it.activity.family }.toSet()
            if (goodFamilies.size >= 8 && avoidFamilies.size >= 8) assertEquals(4 to 4, good.size to avoid.size, "$date")
            // A family left out of a side weighs no more there than the lightest shown on it.
            for ((side, notes) in listOf(good to s.good, avoid to s.avoid)) {
                if (side.size < 4) continue
                val shown = families.toSet()
                val left = notes.filter { it.activity.family !in shown }.maxOfOrNull { it.weight } ?: continue
                assertTrue(left <= side.last().weight, "$date: ${side.last().activity} shown before a heavier work")
            }
            date = date.plusDays(1)
        }
    }

    @Test
    fun `haircuts are weighed on every day, so the haircut row has a side`() {
        // The Almanac's Haircut row shows this side and the factor that decides it (ROADMAP T2.1).
        var date = LocalDate.of(2000, 1, 1)
        while (date.year < 2050) {
            val s = DaySummary.of(TibetanCalendar.of(date))
            val n = s.activities.singleOrNull { it.activity == Activity.HAIRCUTS }
            assertTrue(n != null && (n.good.isEmpty() != n.avoid.isEmpty()), "$date: haircuts not weighed")
            assertEquals(if (n!!.good.isNotEmpty()) Tone.GOOD to n.good else Tone.BAD to n.avoid, s.sideOf(Activity.HAIRCUTS), "$date")
            date = date.plusDays(1)
        }
        // 1 January 2026, the 13th, a Thursday: FPMT's day is good, Thursday's lists avoid haircuts and decide.
        val (tone, standing) = DaySummary.of(TibetanCalendar.of(LocalDate.of(2026, 1, 1))).sideOf(Activity.HAIRCUTS)!!
        assertEquals(Tone.BAD, tone)
        assertEquals("Thursday", standing.first().kanji)
        assertTrue(13 in Texts.HAIRCUT_GOOD)
    }

    @Test
    fun `the rokuyo times come from their readings`() {
        assertEquals(setOf(DayTime.AFTERNOON) to setOf(DayTime.MORNING), rokuyoTimes(Rokuyo.SENBU))
        assertEquals(setOf(DayTime.MORNING, DayTime.EVENING) to setOf(DayTime.NOON), rokuyoTimes(Rokuyo.TOMOBIKI))
        assertEquals(setOf(DayTime.NOON) to emptySet<DayTime>(), rokuyoTimes(Rokuyo.SHAKKO))
        assertEquals(emptySet<DayTime>() to emptySet<DayTime>(), rokuyoTimes(Rokuyo.TAIAN))
    }
}
