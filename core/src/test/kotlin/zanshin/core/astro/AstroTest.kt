/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.astro

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import zanshin.core.vectors
import java.time.Duration
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import kotlin.math.abs

class AstroTest {

    /** SPEC §6: new moons and solar terms within 60 s. */
    private val tolerance = Duration.ofSeconds(60)

    @Test
    fun `new moons agree with the PyEphem cross-check over 1900-2100`() {
        val expected = vectors("crosscheck-new-moons.tsv").map { Instant.parse(it[0]) }
        val actual = Astro.newMoonsBetween(Instant.parse("1900-01-01T00:00:00Z"), Instant.parse("2101-01-01T00:00:00Z"))
        assertEquals(expected.size, actual.size)
        val worst = expected.zip(actual).maxOf { (e, a) -> abs(Duration.between(e, a).seconds) }
        println("new moons: worst difference ${worst}s over ${expected.size}")
        assertTrue(worst <= tolerance.seconds, "worst difference ${worst}s")
    }

    @Test
    fun `solar terms agree with the PyEphem cross-check over 1900-2100`() {
        val expected = vectors("crosscheck-solar-terms.tsv").map { it[0].toInt() to Instant.parse(it[1]) }
        val actual = Astro.solarLongitudeCrossings(
            Instant.parse("1900-01-01T00:00:00Z"),
            Instant.parse("2101-01-01T00:00:00Z"),
            stepDeg = 15,
        )
        assertEquals(expected.size, actual.size)
        var worst = 0L
        for ((e, a) in expected.zip(actual)) {
            assertEquals(e.first, a.first)
            worst = maxOf(worst, abs(Duration.between(e.second, a.second).seconds))
        }
        println("solar terms: worst difference ${worst}s over ${expected.size}")
        assertTrue(worst <= tolerance.seconds, "worst difference ${worst}s")
    }

    @Test
    fun `sun times for Kyoto match PyEphem within a minute`() {
        // PyEphem 4.2.1 defaults, 35.0116N 135.7681E, Asia/Tokyo, 2026-09-28:
        // rise 05:48:46, transit 11:47:42, set 17:46:05, 53.01°.
        val sun = SunTimes.of(LocalDate.of(2026, 9, 28), Place(35.0116, 135.7681, ZoneId.of("Asia/Tokyo")))
        fun minutes(t: java.time.ZonedDateTime?) = t!!.hour * 60 + t.minute + t.second / 60.0
        assertEquals(5 * 60 + 48 + 46 / 60.0, minutes(sun.sunrise), 1.0)
        assertEquals(11 * 60 + 47 + 42 / 60.0, minutes(sun.transit), 1.0)
        assertEquals(17 * 60 + 46 + 5 / 60.0, minutes(sun.sunset), 1.0)
        assertEquals(53.01, sun.altitudeAtTransitDeg, 0.1)
    }

    @Test
    fun `polar night has no sunrise`() {
        val sun = SunTimes.of(LocalDate.of(2026, 12, 21), Place(78.22, 15.65, ZoneId.of("Arctic/Longyearbyen")))
        assertEquals(null, sun.sunrise)
        assertEquals(null, sun.sunset)
    }
}
