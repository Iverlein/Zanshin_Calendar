/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.time

import java.time.LocalDate

/** Julian day number of the epoch day 1970-01-01. */
private const val JD_OF_EPOCH_DAY_0 = 2440588L

/** Earliest and latest civil dates the app supports (SPEC §5.7). */
val SUPPORTED_RANGE: ClosedRange<LocalDate> = LocalDate.of(1900, 1, 1)..LocalDate.of(2100, 12, 31)

/**
 * Julian day number — an integer naming a whole day (Janson §2), not the
 * real-valued Julian date of an instant.
 */
fun LocalDate.julianDayNumber(): Long = toEpochDay() + JD_OF_EPOCH_DAY_0

fun localDateOfJulianDayNumber(jd: Long): LocalDate = LocalDate.ofEpochDay(jd - JD_OF_EPOCH_DAY_0)

/** m mod n in 0 until n, for any sign of m. */
fun mod(m: Long, n: Long): Long = Math.floorMod(m, n)

fun mod(m: Int, n: Int): Int = Math.floorMod(m, n)

/** m amod n in 1..n (Janson §2). */
fun amod(m: Long, n: Long): Long = 1 + Math.floorMod(m - 1, n)

fun amod(m: Int, n: Int): Int = 1 + Math.floorMod(m - 1, n)
