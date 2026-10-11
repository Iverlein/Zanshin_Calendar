/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.texts

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import zanshin.core.kyureki.Tone
import zanshin.core.tibetan.TibetanCalendar
import java.time.LocalDate

/** ROADMAP U11: the hours against the day's tone in short form, as the hours row, the notice and the widget give them. */
class HoursShortFormTest {
    private fun run(first: Int, count: Int) = PeriodRun(first, count, Tone.BAD, emptyList())

    @Test
    fun `a run gives its first and last clock hours`() {
        assertEquals("05–07", HoursShortForm.span(run(0, 1)))
        assertEquals("05–09", HoursShortForm.span(run(0, 2)))
        assertEquals("11–13", HoursShortForm.span(run(3, 1)))
    }

    @Test
    fun `a run across midnight reads as the clock does`() {
        assertEquals("23–01", HoursShortForm.span(run(9, 1)))
        assertEquals("21–03", HoursShortForm.span(run(8, 3)))
        // The last period of the day ends at the next daybreak.
        assertEquals("03–05", HoursShortForm.span(run(11, 1)))
        assertEquals("19–05", HoursShortForm.span(run(7, 5)))
    }

    @Test
    fun `runs are joined on one line`() {
        assertEquals("", HoursShortForm.of(emptyList()))
        assertEquals("05–09 · 11–13 · 17–19 · 23–01", HoursShortForm.of(listOf(run(0, 2), run(3, 1), run(6, 1), run(9, 1))))
    }

    @Test
    fun `the days of the review give their hours against the tone`() {
        // 11 October 2026, a lucky day: four runs to avoid (ROADMAP U11).
        val lucky = DaySummary.of(TibetanCalendar.of(LocalDate.of(2026, 10, 11)))
        assertEquals(Tone.GOOD, lucky.verdict!!.tone)
        assertEquals("05–09 · 11–13 · 17–19 · 23–01", HoursShortForm.of(lucky.hoursAgainst))
        // 1 November 2026, unlucky: the hours to be accomplished.
        val unlucky = DaySummary.of(TibetanCalendar.of(LocalDate.of(2026, 11, 1)))
        assertEquals("09–11 · 13–17 · 19–23 · 01–05", HoursShortForm.of(unlucky.hoursAgainst))
    }
}
