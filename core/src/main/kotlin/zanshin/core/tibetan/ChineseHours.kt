/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import zanshin.core.astro.Place
import zanshin.core.astro.SunTimes
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZonedDateTime

/**
 * The twelve hours of the Chinese reckoning as the White Beryl reads them after the day animals'
 * results (vol. 2, p. 359; docs/sources/weighing.md, *The hour of rule 2*): what each hour is good
 * for, the hour of one's own year, and the four rough times when the four demons move, «ཕྱེད་གཉིས་ཟེར་གཉིས»,
 * in which only averting, killing and fierce work succeeds; «དུས་ཚོད་མཚོན་ཆ་རྣོ་བ་ཡིན», the hour is a
 * sharp weapon. The texts' readings are in [zanshin.core.texts.Texts.HOUR_WORKS] and the two after it.
 */
object ChineseHours {

    /**
     * The four rough times, «ཕྱེད་གཉིས་ཟེར་གཉིས», the two midpoints and the two rays: midnight and noon,
     * the rays of the sun rising in the morning and gathering in the evening, as a ritual manual of the
     * Rin chen gter mdzod glosses «གུང་གཉིས་དང་ཟེར་གཉིས» (vol. 54; docs/sources/weighing.md). Moments,
     * not double hours: the app shows each in the hour it falls in.
     */
    enum class RoughTime(val wylie: String) {
        SUNRISE("nyi ma 'char ka'i zer"),
        NOON("nyin gung"),
        SUNSET("nyi ma sdud pa'i zer"),
        MIDNIGHT("nam gung"),
    }

    data class Moment(val kind: RoughTime, val at: ZonedDateTime)

    /**
     * The rough times of the Tibetan day of [date] at [place], in order: the day runs from 05:00 on
     * its date to 05:00 the next morning (SPEC §10.3), so a sunrise before 05:00 belongs to the day
     * before. Noon is the sun's transit, midnight twelve hours after it; at a place where the sun
     * does not rise or set that day those moments are missing.
     */
    fun roughTimes(date: LocalDate, place: Place): List<Moment> {
        val start = date.atTime(LocalTime.of(5, 0)).atZone(place.zone)
        val end = start.plusDays(1)
        val today = SunTimes.of(date, place)
        val tomorrow = SunTimes.of(date.plusDays(1), place)
        return listOfNotNull(
            today.sunrise?.let { Moment(RoughTime.SUNRISE, it) },
            tomorrow.sunrise?.let { Moment(RoughTime.SUNRISE, it) },
            Moment(RoughTime.NOON, today.transit),
            today.sunset?.let { Moment(RoughTime.SUNSET, it) },
            Moment(RoughTime.MIDNIGHT, today.transit.plusHours(12)),
        ).filter { !it.at.isBefore(start) && it.at.isBefore(end) }.sortedBy { it.at }
    }

    /** The double hour from 05:00, 0 to 11, that [at] falls in on the Tibetan day of [date]. */
    fun hourOf(date: LocalDate, at: ZonedDateTime): Int {
        val minutes = Duration.between(date.atTime(LocalTime.of(5, 0)).atZone(at.zone), at).toMinutes()
        return (minutes / 120).toInt().coerceIn(0, 11)
    }

    /** «རང་ཉིད་ལོ་ཡི་དུས་ཤར་ཚེ»: the hour of one's own year, the hour of the birth year's animal. */
    fun ownYearHour(birthYear: Animal, hour: Animal): Boolean = birthYear == hour
}
