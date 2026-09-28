/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app

import android.util.LruCache
import zanshin.core.astro.Astro
import zanshin.core.kyureki.Kyureki
import zanshin.core.kyureki.KyurekiDay
import zanshin.core.kyureki.Rekichu
import zanshin.core.kyureki.RekichuDay
import zanshin.core.tibetan.PersonalDay
import zanshin.core.tibetan.personalDay
import zanshin.core.tibetan.TibetanCalendar
import zanshin.core.tibetan.TibetanDay
import zanshin.core.time.SUPPORTED_RANGE
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Locale

/** Everything the two calendar views show for one civil date. */
class DayInfo private constructor(
    val date: LocalDate,
    val tibetan: TibetanDay,
    val kyureki: KyurekiDay,
    /** Moon's elongation from the sun at 21:00 local time, degrees. */
    val moonElongation: Double,
    val rekichu: RekichuDay,
    /** Luck, life or anti day for the owner's birth year, if a birth date is set. */
    val personalDay: PersonalDay?,
) {
    companion object {
        private val cache = LruCache<Pair<LocalDate, LocalDate?>, DayInfo>(256)

        fun of(date: LocalDate, zone: ZoneId, birth: LocalDate? = null): DayInfo = cache[date to birth] ?: run {
            val tibetan = TibetanCalendar.of(date)
            DayInfo(
                date = date,
                tibetan = tibetan,
                kyureki = Kyureki.of(date),
                moonElongation = Astro.moonElongationDeg(date.atTime(LocalTime.of(21, 0)).atZone(zone).toInstant()),
                rekichu = Rekichu.of(date, birth),
                personalDay = birth?.let { personalDay(TibetanCalendar.of(it).yearAnimal, tibetan.weekday) },
            )
        }.also { cache.put(date to birth, it) }
    }
}

/** Pager pages are days of the supported range, page 0 = 1900-01-01. */
object Days {
    val first: LocalDate = SUPPORTED_RANGE.start
    val count: Int = (ChronoUnit.DAYS.between(SUPPORTED_RANGE.start, SUPPORTED_RANGE.endInclusive) + 1).toInt()

    fun dateOf(page: Int): LocalDate = first.plusDays(page.toLong())

    fun pageOf(date: LocalDate): Int =
        ChronoUnit.DAYS.between(first, date.coerceIn(SUPPORTED_RANGE.start, SUPPORTED_RANGE.endInclusive)).toInt()
}

object Labels {
    val headerDate: DateTimeFormatter = DateTimeFormatter.ofPattern("EEE d MMM yyyy", Locale.ENGLISH)
    val shortDate: DateTimeFormatter = DateTimeFormatter.ofPattern("d MMM", Locale.ENGLISH)
    val monthTitle: DateTimeFormatter = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH)
    val clock: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm", Locale.ENGLISH)

    fun ordinal(n: Int): String {
        val suffix = if (n % 100 in 11..13) "th" else when (n % 10) {
            1 -> "st"
            2 -> "nd"
            3 -> "rd"
            else -> "th"
        }
        return "$n$suffix"
    }

    fun month(number: Int, leap: Boolean): String = "${if (leap) "Leap " else ""}${ordinal(number)} month"

    fun enum(e: Enum<*>): String = e.name.lowercase().replaceFirstChar { it.uppercase() }

    fun moonPhase(elongation: Double): String = when {
        elongation < 12 || elongation > 348 -> "New moon"
        elongation < 84 -> "Waxing crescent"
        elongation < 96 -> "First quarter"
        elongation < 168 -> "Waxing gibbous"
        elongation < 192 -> "Full moon"
        elongation < 264 -> "Waning gibbous"
        elongation < 276 -> "Last quarter"
        else -> "Waning crescent"
    }
}
