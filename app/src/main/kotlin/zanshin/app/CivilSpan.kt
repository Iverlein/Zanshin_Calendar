/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app

import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * The two ends of a lunar month's civil span in the election's heading (ROADMAP U14): "11 Oct" and
 * "9 Nov" by [short], the year added to the end by [withYear] only where the span leaves [year], the
 * shown day's ("10 Dec" and "8 Jan 2027", "8 Jan" and "6 Feb 2027"), as a span of twelve months runs
 * into the next one.
 */
fun civilSpan(first: LocalDate, last: LocalDate, year: Int, short: DateTimeFormatter, withYear: DateTimeFormatter): Pair<String, String> =
    first.format(short) to last.format(if (first.year == year && last.year == year) short else withYear)
