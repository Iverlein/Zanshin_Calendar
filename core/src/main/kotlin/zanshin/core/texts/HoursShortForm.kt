/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.texts

/**
 * The combination periods in short form (SPEC §10.3, ROADMAP U11): clock hours alone, two figures
 * each, runs joined by a middle dot, "05–09 · 11–13 · 17–19 · 23–01". The hours row gives the hours
 * against the day's tone so, on one line; the morning notice (ROADMAP N1) and the widget (W1) use the
 * same form. Every two-hour period begins on the hour, so the minutes it leaves out are always zero;
 * the full times stay in the brief's "By the hour" and in what a screen reader hears.
 */
object HoursShortForm {
    /** Between two runs. */
    const val SEPARATOR = " · "

    /** The clock hour, 0–23, at which two-hour [period] begins, counted from the hare hour at 05:00. */
    fun startHour(period: Int): Int = (5 + period * 2) % 24

    /** "23–01": the run's first and last clock hours, past midnight as the clock reads them. */
    fun span(run: PeriodRun): String = "${two(startHour(run.first))}–${two(startHour(run.first + run.count))}"

    /** "05–09 · 11–13 · 17–19 · 23–01", or "" when there are no [runs]. */
    fun of(runs: List<PeriodRun>): String = runs.joinToString(SEPARATOR) { span(it) }

    private fun two(hour: Int) = hour.toString().padStart(2, '0')
}
