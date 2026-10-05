/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.tibetan

import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.temporal.ChronoUnit
import kotlin.math.floor
import zanshin.core.astro.Astro
import zanshin.core.texts.gloss
import zanshin.core.time.amod
import zanshin.core.time.julianDayNumber

/** The eight directions and the middle, as the sme ba square places them. */
enum class Direction {
    NORTH, NORTH_EAST, EAST, SOUTH_EAST, SOUTH, SOUTH_WEST, WEST, NORTH_WEST, CENTRE;

    val english: String get() = gloss(this)
}

/**
 * The day's sme ba of the sixty-day count, as the White Beryl's *bla mkhyen*
 * of the day needs it (SPEC §5.11; docs/sources/earth-lords.md, question 11).
 *
 * The White Beryl starts it at the one-white on the winter solstice (vol. 2,
 * p. 192); Blo bzang sbyin pa's *Tsi na'i rtsis la 'jug pa* and a text in the
 * *Mdo khams stod* collection say on which day it takes hold and which way
 * it runs. A wood-mouse day has the sme ba of the stretch between mid-month
 * terms (*sgang*) it falls in: from the winter solstice to the first *sgang*
 * the one-white, to the third the seven-red, to the summer solstice the
 * four-green, counting forward; from the summer solstice to the seventh the
 * nine-red, to the ninth the three-blue, to the winter solstice the
 * six-white, counting backward. Every other day counts on from the last
 * wood-mouse day.
 *
 * The *sgang* are the sun's longitudes 270°, 330°, 30°, 90°, 150° and 210°,
 * dated in Lhasa's mean solar time: the White Beryl reckons the solstice
 * "for this land of ours" (vol. 1, p. 195).
 */
data class DaySmeBa(
    /** 1–9. */
    val number: Int,
    /** The wood-mouse day the count runs from, on or before the day. */
    val woodMouse: LocalDate,
    /** The wood-mouse day's own sme ba, which its stretch gives. */
    val woodMouseNumber: Int,
    /** True after the winter solstice, counting up; false after the summer solstice, counting down. */
    val forward: Boolean,
) {
    /** Where the seven-red stands when the day's sme ba is in the middle: the *bla mkhyen*'s direction (vol. 2, p. 224). */
    val sevenRed: Direction get() = directionOf(7, number)

    companion object {
        private val LHASA = ZoneOffset.ofHoursMinutesSeconds(6, 4, 24)

        /** The wood-mouse day's sme ba in each sixty-degree stretch from the winter solstice. */
        private val STRETCH = listOf(1, 7, 4, 9, 3, 6)

        /** The square with 5 in the middle, south at the top, read row by row (SE S SW / E C W / NE N NW). */
        private val BASE = listOf(4, 9, 2, 3, 5, 7, 8, 1, 6)
        private val PLACES = listOf(
            Direction.SOUTH_EAST, Direction.SOUTH, Direction.SOUTH_WEST,
            Direction.EAST, Direction.CENTRE, Direction.WEST,
            Direction.NORTH_EAST, Direction.NORTH, Direction.NORTH_WEST,
        )

        /** Where [n] stands in the square whose middle holds [centre]. */
        fun directionOf(n: Int, centre: Int): Direction = PLACES[BASE.indexOf(amod(n - centre + 5, 9))]

        fun of(date: LocalDate): DaySmeBa {
            // The wood-mouse days are the Julian day numbers 11 mod 60 (TibetanCalendar's dayElement, dayAnimal).
            val back = Math.floorMod(date.julianDayNumber() - 11, 60L)
            val woodMouse = date.minusDays(back)
            val endOfDay = woodMouse.plusDays(1).atTime(LocalTime.MIDNIGHT).toInstant(LHASA)
            val lambda = Astro.sunApparentLongitudeDeg(Astro.jdeOf(endOfDay))
            val stretch = floor((lambda - 270.0).mod(360.0) / 60.0).toInt()
            val start = STRETCH[stretch]
            val forward = stretch < 3
            val n = ChronoUnit.DAYS.between(woodMouse, date).toInt()
            return DaySmeBa(if (forward) amod(start + n, 9) else amod(start - n, 9), woodMouse, start, forward)
        }
    }
}
