/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.texts

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import zanshin.core.kyureki.Tone
import zanshin.core.tibetan.TibetanCalendar
import zanshin.core.tibetan.nectarHours
import zanshin.core.tibetan.risingSign
import java.time.LocalDate

/** ROADMAP E1–E3, SPEC §5.14: the election reads each day's own weighing and only orders the days. */
class ElectionTest {
    @Test
    fun `on 2000–2049 every work's side is the day page's, and the key's order holds`() {
        var from = LocalDate.of(2000, 1, 1)
        var days = 0
        val named = mutableSetOf<Activity>()
        while (from.year < 2050) {
            val span = ElectionSpan.of(from, Election.MAX_MONTHS)
            for (works in Election.WORKS.values) for (work in works) {
                val election = span.election(work)
                for (e in election.days) {
                    val side = e.summary.sideOf(work)
                    assertEquals(side?.first, e.side, "${e.date} $work")
                    assertEquals(side?.second.orEmpty(), e.standing, "${e.date} $work")
                    if (e.side != null) {
                        // The strongest standing voice decides; the combination's two parts are one voice.
                        val first = DayFactor.entries.first { it.english == e.standing.first().english }
                        assertEquals(first.rank, e.decider!!.rank, "${e.date} $work")
                        assertEquals(if (e.decider!!.rank == 0) VerdictBy.COMBINATION else VerdictBy.STRONGEST, e.by)
                    }
                }
                // Every good day, and only those; no day decided by a weaker voice before one decided by a stronger.
                assertEquals(election.days.filter { it.side == Tone.GOOD }.toSet(), election.best.toSet(), "$from $work")
                election.best.zipWithNext().forEach { (a, b) ->
                    assertTrue(a.decider!!.rank <= b.decider!!.rank, "$from $work: ${a.date} before ${b.date}")
                    if (a.decider!!.rank == b.decider!!.rank) {
                        val lucky = { e: ElectionDay -> e.summary.combinationTone == Tone.GOOD }
                        assertTrue(lucky(a) || !lucky(b), "$from $work: ${a.date} before ${b.date}")
                        if (lucky(a) == lucky(b)) {
                            assertTrue(a.weight >= b.weight, "$from $work: ${a.date} before ${b.date}")
                            if (a.weight == b.weight) assertTrue(a.date < b.date)
                        }
                    }
                }
            }
            for (d in span.days) named += d.summary.activities.map { it.activity }
            // The spans follow one another without a gap.
            assertEquals(from, span.days.first().date)
            days += span.days.size
            from = span.days.last().date.plusDays(1)
        }
        assertTrue(days >= 18_263)
        // The works offered are every work some day's weighing names, and no other.
        assertEquals(named - Activity.EVERYTHING, Election.WORKS.values.flatten().toSet())
    }

    @Test
    fun `a span is whole days from the shown one to the end of its last Tibetan month`() {
        val from = LocalDate.of(2026, 10, 9)
        val one = ElectionSpan.of(from, 1)
        assertEquals(from, one.days.first().date)
        val month = TibetanCalendar.monthOf(TibetanCalendar.of(from))
        assertEquals(month.last().jd, one.days.last().day.jd)
        one.days.zipWithNext().forEach { (a, b) -> assertEquals(a.date.plusDays(1), b.date) }

        val twelve = ElectionSpan.of(from, 12)
        val months = twelve.election(Activity.HAIRCUTS).months
        assertEquals(12, months.size)
        assertEquals(months.map { it.days.size }.sum(), twelve.days.size)
        // The 2100 limit of the date picker.
        val end = ElectionSpan.of(LocalDate.of(2100, 12, 20), 12)
        assertEquals(LocalDate.of(2100, 12, 31), end.days.last().date)
    }

    @Test
    fun `October 2026, ranked`() {
        // The app's own weighing (SPEC §5.12) read across the days: a regression vector, its differences from
        // the tibetastromed.ru election named in docs/sources/tibetastromed.md.
        val span = ElectionSpan.of(LocalDate.of(2026, 10, 1), 2)
        fun best(work: Activity) = span.election(work).best.filter { it.date.monthValue == 10 }.map { it.date.dayOfMonth }
        fun avoid(work: Activity) = span.election(work).days.filter { it.date.monthValue == 10 && it.side == Tone.BAD }.map { it.date.dayOfMonth }
        assertEquals(listOf(26, 2, 16, 23, 30, 14, 12, 7, 19, 21, 28, 5, 9), best(Activity.HAIRCUTS))
        assertEquals(listOf(26, 19, 7, 14, 16, 2, 23, 30, 1, 5, 12, 29, 8, 9, 15, 22), best(Activity.WEDDING))
        assertEquals(listOf(28, 23, 2, 30, 16, 15, 22, 21, 7, 9, 29, 1, 8, 14, 25, 11), best(Activity.JOURNEY))
        // The site's days to avoid are ours, but for the wedding on the 7th (its element pair) and setting out on the 11th.
        assertTrue(avoid(Activity.HAIRCUTS).containsAll(listOf(10, 17, 20)))
        assertTrue(avoid(Activity.WEDDING).containsAll(listOf(4, 11, 17, 18, 24, 25, 27, 28, 31)))
        assertTrue(avoid(Activity.JOURNEY).containsAll(listOf(3, 4, 10, 17, 18, 24, 31, 5, 19)))

        val first = span.election(Activity.WEDDING).best.first()
        assertEquals(LocalDate.of(2026, 10, 26), first.date)
        assertEquals(VerdictBy.COMBINATION, first.by)
        assertEquals(Tone.GOOD, first.summary.combinationTone)
    }

    @Test
    fun `the hours are the month's, by the sign's reading of the work`() {
        val month = TibetanCalendar.of(LocalDate.of(2026, 10, 20)).month
        for (work in listOf(Activity.HAIRCUTS, Activity.WEDDING, Activity.JOURNEY)) {
            val good = Election.hours(month, work, Tone.GOOD)
            val avoid = Election.hours(month, work, Tone.BAD)
            for (hour in 0 until 12) {
                val reading = Texts.DUS_SBYOR.getValue(risingSign(month, hour)).second
                val g = work in Activities.of(reading.goodKeys) && work !in Activities.of(reading.avoidKeys)
                val a = work in Activities.of(reading.avoidKeys) && work !in Activities.of(reading.goodKeys)
                assertEquals(g, good.any { hour in it.first until it.first + it.count }, "$work $hour")
                assertEquals(a, avoid.any { hour in it.first until it.first + it.count }, "$work $hour")
            }
            // Runs are separate: two runs on one side never touch.
            for (runs in listOf(good, avoid)) runs.zipWithNext().forEach { (x, y) -> assertTrue(x.first + x.count < y.first) }
        }
        // Every day of a month has the same hours: they are given once per month.
        val election = ElectionSpan.of(LocalDate.of(2026, 10, 1), 2).election(Activity.HAIRCUTS)
        assertEquals(2, election.months.size)
        election.months.forEach { m -> assertEquals(Election.hours(m.first.month, Activity.HAIRCUTS, Tone.GOOD), m.good) }
        assertTrue(election.months.all { it.good.isNotEmpty() && it.avoid.isNotEmpty() })
    }

    @Test
    fun `the nectar periods only where their reading names the work`() {
        val span = ElectionSpan.of(LocalDate.of(2026, 10, 1), 1)
        val named = Activities.of(Texts.NECTAR_PERIODS.goodKeys)
        assertTrue(named.isNotEmpty())
        for (work in Election.WORKS.values.flatten()) {
            for (e in span.election(work).days) {
                assertEquals(if (work in named) nectarHours(e.day.weekday) else emptyList<Int>(), e.nectar, "$work")
            }
        }
    }

    @Test
    fun `the days WB avoids every work on are not offered, for the test birth date`() {
        // ROADMAP E6: October 2026 for 1 June 1976 (earth, Dragon): Thursdays 1, 8, 15, 22, 29 and Pūrvaphalgunī on the 9th.
        val birth = LocalDate.of(1976, 6, 1)
        val span = ElectionSpan.of(LocalDate.of(2026, 10, 1), 2, birth)
        val plain = ElectionSpan.of(LocalDate.of(2026, 10, 1), 2)
        val taken = span.days.filter { it.date.monthValue == 10 && it.summary.avoidAll.isNotEmpty() }.map { it.date.dayOfMonth }
        assertEquals(listOf(1, 8, 9, 15, 22, 29), taken)
        for (work in Election.WORKS.values.flatten()) {
            val mine = span.election(work)
            val everyone = plain.election(work)
            // The side stays the weighing's, the same for every reader; only the offer changes.
            assertEquals(everyone.days.map { it.side }, mine.days.map { it.side }, "$work")
            assertTrue(mine.best.none { it.avoidAll.isNotEmpty() }, "$work")
            assertEquals(everyone.best.filter { it.date.dayOfMonth !in taken || it.date.monthValue != 10 }.map { it.date }.filter { d ->
                mine.days.first { it.date == d }.avoidAll.isEmpty()
            }, mine.best.map { it.date }, "$work")
        }
        // Weddings on Thursday the 1st and the 8th are good for everyone and not offered to this person.
        val wedding = span.election(Activity.WEDDING)
        assertTrue(wedding.days.first { it.date.dayOfMonth == 1 }.let { it.side == Tone.GOOD && !it.offered })
    }
}
