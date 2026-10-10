/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app

import android.content.res.Resources
import android.icu.text.MessageFormat
import android.icu.util.ULocale
import android.util.LruCache
import io.github.iverlein.zanshin.R
import androidx.compose.runtime.staticCompositionLocalOf
import zanshin.core.astro.Astro
import zanshin.core.kyureki.Kyureki
import zanshin.core.kyureki.KyurekiDay
import zanshin.core.kyureki.Rekichu
import zanshin.core.kyureki.RekichuDay
import zanshin.core.tibetan.DaySigns
import zanshin.core.tibetan.Element
import zanshin.core.tibetan.Forces
import zanshin.core.tibetan.Mansion
import zanshin.core.tibetan.OwnDay
import zanshin.core.tibetan.ownDays
import zanshin.core.tibetan.ownElement
import zanshin.core.tibetan.PersonalDay
import zanshin.core.tibetan.Sign
import zanshin.core.tibetan.PersonalMansion
import zanshin.core.tibetan.personalDay
import zanshin.core.tibetan.personalMansions
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
    /** Which of the owner's personal mansions the day's mansion is, if a birth date is set: none, one, or two. */
    val personalMansions: List<PersonalMansion>,
    /** Which of the owner's own days by the birth date this is (WB p. 338), if a birth date is set: the weekday's roles, then the birth mansion. */
    val ownDays: List<OwnDay>,
    /** The element of the owner's own weekdays, the birth year's life force, if a birth date is set. */
    val ownElement: Element?,
    /** The sign of the birth year, if a birth date is set: its aspects are set against the day's, month's and year's. */
    val birthSign: Sign?,
    /** The birth date's mansion, if a birth date is set: the fangs of the hundred feet are read against it. */
    val birthMansion: Mansion?,
    /** The year, month and lunar-date signs of the elemental divination for this day. */
    val signs: DaySigns,
) {
    companion object {
        private val cache = LruCache<Pair<LocalDate, LocalDate?>, DayInfo>(256)

        fun of(date: LocalDate, zone: ZoneId, birth: LocalDate? = null): DayInfo = cache[date to birth] ?: run {
            val tibetan = TibetanCalendar.of(date)
            val born = birth?.let { TibetanCalendar.of(it) }
            DayInfo(
                date = date,
                tibetan = tibetan,
                kyureki = Kyureki.of(date),
                moonElongation = Astro.moonElongationDeg(date.atTime(LocalTime.of(21, 0)).atZone(zone).toInstant()),
                rekichu = Rekichu.of(date, birth),
                personalDay = born?.let { personalDay(it.yearAnimal, tibetan.weekday) },
                personalMansions = born?.let { personalMansions(it.yearAnimal, tibetan.mansion) }.orEmpty(),
                ownDays = born?.let { ownDays(it, tibetan) }.orEmpty(),
                ownElement = born?.let { ownElement(it) },
                birthSign = born?.let { Sign(it.yearElement, it.yearAnimal) },
                birthMansion = born?.mansion,
                signs = Forces.signs(tibetan),
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

/**
 * Dates, ordinals and phrases in the app's language: the language its resources
 * resolved to (`locale_tag` in strings.xml), so dates never switch language
 * while the text around them stays English (SPEC §10.1).
 */
class Labels(private val res: Resources) {
    val locale: Locale = Locale.forLanguageTag(res.getString(R.string.locale_tag))
    val headerDate: DateTimeFormatter = DateTimeFormatter.ofPattern(res.getString(R.string.pattern_header_date), locale)
    val shortDate: DateTimeFormatter = DateTimeFormatter.ofPattern(res.getString(R.string.pattern_short_date), locale)
    val monthTitle: DateTimeFormatter = DateTimeFormatter.ofPattern(res.getString(R.string.pattern_month_title), locale)
    val clock: DateTimeFormatter = DateTimeFormatter.ofPattern(res.getString(R.string.pattern_clock), locale)
    private val ordinals = MessageFormat("{0,ordinal}", ULocale.forLocale(locale))

    fun string(id: Int, vararg args: Any): String = res.getString(id, *args)

    fun plural(id: Int, n: Int): String = res.getQuantityString(id, n, n)

    /** 1st, 2nd, 3rd… as the language writes them. */
    fun ordinal(n: Int): String = ordinals.format(arrayOf(n))

    fun month(number: Int, leap: Boolean): String = string(if (leap) R.string.month_leap else R.string.month, ordinal(number))

    fun moonPhase(elongation: Double): String = string(
        when {
            elongation < 12 || elongation > 348 -> R.string.moon_new
            elongation < 84 -> R.string.moon_waxing_crescent
            elongation < 96 -> R.string.moon_first_quarter
            elongation < 168 -> R.string.moon_waxing_gibbous
            elongation < 192 -> R.string.moon_full
            elongation < 264 -> R.string.moon_waning_gibbous
            elongation < 276 -> R.string.moon_last_quarter
            else -> R.string.moon_waning_crescent
        },
    )
}

val LocalLabels = staticCompositionLocalOf<Labels> { error("Labels not provided") }
