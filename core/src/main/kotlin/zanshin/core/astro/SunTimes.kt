/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.astro

import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import kotlin.math.PI
import kotlin.math.acos
import kotlin.math.asin
import kotlin.math.cos
import kotlin.math.sin

/** A place on Earth: degrees, north and east positive, and the zone its clock follows. */
data class Place(val latitude: Double, val longitude: Double, val zone: ZoneId)

/**
 * Sunrise, sunset and true solar noon for one civil date. [sunrise] and
 * [sunset] are null when the sun does not cross the horizon that day
 * (polar day or night).
 */
data class SunDay(
    val sunrise: ZonedDateTime?,
    val sunset: ZonedDateTime?,
    val transit: ZonedDateTime,
    val altitudeAtTransitDeg: Double,
)

object SunTimes {
    private const val DEG = PI / 180.0

    /** Standard altitude of the sun's upper limb at rise and set: −0°50′ (Meeus ch. 15). */
    private const val H0 = -0.8333 * DEG

    /** Sidereal degrees per solar day, used to turn hour angles into time. */
    private const val SIDEREAL_RATE = 360.98564736629

    fun of(date: LocalDate, place: Place): SunDay {
        val lat = place.latitude * DEG
        val lon = place.longitude * DEG
        val noonGuess = ZonedDateTime.of(date, LocalTime.NOON, place.zone).toInstant()

        var transit = Astro.jdOf(noonGuess)
        repeat(4) { transit -= hourAngle(transit, lon) / DEG / SIDEREAL_RATE }

        val (_, decAtTransit) = Astro.sunEquatorial(transit + Astro.deltaTSeconds(transit) / 86400.0)
        val altitude = asin(sin(lat) * sin(decAtTransit) + cos(lat) * cos(decAtTransit))

        return SunDay(
            sunrise = crossing(transit, lat, lon, rising = true)?.let { zoned(it, place.zone) },
            sunset = crossing(transit, lat, lon, rising = false)?.let { zoned(it, place.zone) },
            transit = zoned(transit, place.zone),
            altitudeAtTransitDeg = altitude / DEG,
        )
    }

    /** Local hour angle of the sun at UT Julian date [jd], radians in (−π, π]. */
    private fun hourAngle(jd: Double, lon: Double): Double {
        val (ra, _) = Astro.sunEquatorial(jd + Astro.deltaTSeconds(jd) / 86400.0)
        var h = Astro.apparentSiderealTime(jd) + lon - ra
        h = Astro.normalizeRad(h)
        return if (h > PI) h - 2 * PI else h
    }

    /** Rise or set nearest the given transit, iterated on the sun's changing declination. */
    private fun crossing(transit: Double, lat: Double, lon: Double, rising: Boolean): Double? {
        var jd = transit
        repeat(5) {
            val (_, dec) = Astro.sunEquatorial(jd + Astro.deltaTSeconds(jd) / 86400.0)
            val cosH0 = (sin(H0) - sin(lat) * sin(dec)) / (cos(lat) * cos(dec))
            if (cosH0 < -1.0 || cosH0 > 1.0) return null
            val target = if (rising) -acos(cosH0) else acos(cosH0)
            jd += (target - hourAngle(jd, lon)) / DEG / SIDEREAL_RATE
        }
        return jd
    }

    private fun zoned(jd: Double, zone: ZoneId): ZonedDateTime = Instant.ofEpochMilli(
        Astro.instantOfJd(jd).toEpochMilli(),
    ).atZone(zone)
}
