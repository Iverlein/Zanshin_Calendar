/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.astro

import java.time.Instant
import kotlin.math.PI
import kotlin.math.asin
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.roundToLong
import kotlin.math.sin

/**
 * Sun and moon positions after Jean Meeus, "Astronomical Algorithms",
 * 2nd ed. (1998). Chapter numbers refer to that book.
 *
 * Time scales: `jd` is a Julian date in UT, `jde` a Julian ephemeris date in
 * TT; they differ by ΔT.
 */
object Astro {
    private const val J2000 = 2451545.0
    private const val SYNODIC_MONTH = 29.530588861
    private const val DEG = PI / 180.0
    private const val ARCSEC = DEG / 3600.0

    fun jdOf(instant: Instant): Double = instant.epochSecond / 86400.0 + instant.nano / 86400e9 + 2440587.5

    fun instantOfJd(jd: Double): Instant {
        val millis = ((jd - 2440587.5) * 86400_000.0).roundToLong()
        return Instant.ofEpochMilli(millis)
    }

    fun jdeOf(instant: Instant): Double {
        val jd = jdOf(instant)
        return jd + deltaTSeconds(jd) / 86400.0
    }

    fun instantOfJde(jde: Double): Instant = instantOfJd(jde - deltaTSeconds(jde) / 86400.0)

    /**
     * ΔT = TT − UT in seconds: the polynomial expressions of Espenak & Meeus,
     * NASA Five Millennium Canon of Solar Eclipses,
     * https://eclipse.gsfc.nasa.gov/SEhelp/deltatpoly2004.html
     */
    fun deltaTSeconds(jd: Double): Double {
        val y = 2000.0 + (jd - J2000) / 365.25
        return when {
            y < 1860 -> -20 + 32 * ((y - 1820) / 100).pow(2) // outside the supported range; coarse
            y < 1900 -> {
                val t = y - 1860
                7.62 + 0.5737 * t - 0.251754 * t * t + 0.01680668 * t.pow(3) -
                    0.0004473624 * t.pow(4) + t.pow(5) / 233174
            }
            y < 1920 -> {
                val t = y - 1900
                -2.79 + 1.494119 * t - 0.0598939 * t * t + 0.0061966 * t.pow(3) - 0.000197 * t.pow(4)
            }
            y < 1941 -> {
                val t = y - 1920
                21.20 + 0.84493 * t - 0.076100 * t * t + 0.0020936 * t.pow(3)
            }
            y < 1961 -> {
                val t = y - 1950
                29.07 + 0.407 * t - t * t / 233 + t.pow(3) / 2547
            }
            y < 1986 -> {
                val t = y - 1975
                45.45 + 1.067 * t - t * t / 260 - t.pow(3) / 718
            }
            y < 2005 -> {
                val t = y - 2000
                63.86 + 0.3345 * t - 0.060374 * t * t + 0.0017275 * t.pow(3) +
                    0.000651814 * t.pow(4) + 0.00002373599 * t.pow(5)
            }
            y < 2050 -> {
                val t = y - 2000
                62.92 + 0.32217 * t + 0.005589 * t * t
            }
            y < 2150 -> -20 + 32 * ((y - 1820) / 100).pow(2) - 0.5628 * (2150 - y)
            else -> -20 + 32 * ((y - 1820) / 100).pow(2)
        }
    }

    /** Nutation in longitude and obliquity, radians (ch. 22, low precision, ±0.5″). */
    private fun nutation(jde: Double): Pair<Double, Double> {
        val t = (jde - J2000) / 36525.0
        val omega = (125.04452 - 1934.136261 * t) * DEG
        val l = (280.4665 + 36000.7698 * t) * DEG
        val lp = (218.3165 + 481267.8813 * t) * DEG
        val dPsi = -17.20 * sin(omega) - 1.32 * sin(2 * l) - 0.23 * sin(2 * lp) + 0.21 * sin(2 * omega)
        val dEps = 9.20 * cos(omega) + 0.57 * cos(2 * l) + 0.10 * cos(2 * lp) - 0.09 * cos(2 * omega)
        return dPsi * ARCSEC to dEps * ARCSEC
    }

    /** True obliquity of the ecliptic, radians (22.2 plus nutation). */
    private fun trueObliquity(jde: Double, dEps: Double): Double {
        val t = (jde - J2000) / 36525.0
        val eps0 = 84381.448 - 46.8150 * t - 0.00059 * t * t + 0.001813 * t * t * t
        return eps0 * ARCSEC + dEps
    }

    private fun series(terms: Array<DoubleArray?>, tau: Double): Double {
        var sum = 0.0
        var power = 1.0
        for (coefficients in terms) {
            if (coefficients != null) {
                var s = 0.0
                var i = 0
                while (i < coefficients.size) {
                    s += coefficients[i] * cos(coefficients[i + 1] + coefficients[i + 2] * tau)
                    i += 3
                }
                sum += s * power
            }
            power *= tau
        }
        return sum
    }

    /** Apparent geocentric longitude of the sun, radians in [0, 2π) (ch. 32, 25). */
    fun sunApparentLongitudeRad(jde: Double): Double {
        val tau = (jde - J2000) / 365250.0
        val l = series(Vsop87Earth.L, tau)
        val r = series(Vsop87Earth.R, tau)
        val (dPsi, _) = nutation(jde)
        val lambda = l + PI - 0.09033 * ARCSEC + dPsi - 20.4898 * ARCSEC / r
        return normalizeRad(lambda)
    }

    fun sunApparentLongitudeDeg(jde: Double): Double = sunApparentLongitudeRad(jde) / DEG

    /** Apparent right ascension and declination of the sun, radians. */
    fun sunEquatorial(jde: Double): Pair<Double, Double> {
        val lambda = sunApparentLongitudeRad(jde)
        val (_, dEps) = nutation(jde)
        val eps = trueObliquity(jde, dEps)
        val ra = atan2(cos(eps) * sin(lambda), cos(lambda))
        val dec = asin(sin(eps) * sin(lambda))
        return normalizeRad(ra) to dec
    }

    /** Greenwich apparent sidereal time at UT Julian date [jd], radians (12.4 plus equation of the equinoxes). */
    fun apparentSiderealTime(jd: Double): Double {
        val t = (jd - J2000) / 36525.0
        val theta0 = 280.46061837 + 360.98564736629 * (jd - J2000) + 0.000387933 * t * t - t * t * t / 38710000.0
        val jde = jd + deltaTSeconds(jd) / 86400.0
        val (dPsi, dEps) = nutation(jde)
        val eps = trueObliquity(jde, dEps)
        return normalizeRad(theta0 * DEG + dPsi * cos(eps))
    }

    /** JDE of the new moon with lunation number [k] (k = 0 near 2000-01-06), ch. 49. */
    fun newMoonJde(k: Long): Double {
        val kd = k.toDouble()
        val t = kd / 1236.85
        var jde = 2451550.09766 + SYNODIC_MONTH * kd +
            (0.00015437 + (-0.00000015 + 0.00000000073 * t) * t) * t * t
        val e = 1.0 + (-0.002516 - 0.0000074 * t) * t
        val m = (2.5534 + 29.1053567 * kd + (-0.0000014 - 0.00000011 * t) * t * t) * DEG
        val mp = (201.5643 + 385.81693528 * kd + (0.0107582 + (0.00001238 - 0.000000058 * t) * t) * t * t) * DEG
        val f = (160.7108 + 390.67050284 * kd + (-0.0016118 + (-0.00000227 + 0.000000011 * t) * t) * t * t) * DEG
        val omega = (124.7746 - 1.56375588 * kd + (0.0020672 + 0.00000215 * t) * t * t) * DEG

        jde += -0.40720 * sin(mp) + 0.17241 * e * sin(m) +
            0.01608 * sin(2 * mp) + 0.01039 * sin(2 * f) +
            0.00739 * e * sin(mp - m) - 0.00514 * e * sin(mp + m) +
            0.00208 * e * e * sin(2 * m) - 0.00111 * sin(mp - 2 * f) -
            0.00057 * sin(mp + 2 * f) + 0.00056 * e * sin(2 * mp + m) -
            0.00042 * sin(3 * mp) + 0.00042 * e * sin(m + 2 * f) +
            0.00038 * e * sin(m - 2 * f) - 0.00024 * e * sin(2 * mp - m) -
            0.00017 * sin(omega) - 0.00007 * sin(mp + 2 * m) +
            0.00004 * sin(2 * mp - 2 * f) + 0.00004 * sin(3 * m) +
            0.00003 * sin(mp + m - 2 * f) + 0.00003 * sin(2 * mp + 2 * f) -
            0.00003 * sin(mp + m + 2 * f) + 0.00003 * sin(mp - m + 2 * f) -
            0.00002 * sin(mp - m - 2 * f) - 0.00002 * sin(3 * mp + m) +
            0.00002 * sin(4 * mp)

        val planetary = doubleArrayOf(
            299.77 + 0.107408 * kd - 0.009173 * t * t, 0.000325,
            251.88 + 0.016321 * kd, 0.000165,
            251.83 + 26.651886 * kd, 0.000164,
            349.42 + 36.412478 * kd, 0.000126,
            84.66 + 18.206239 * kd, 0.000110,
            141.74 + 53.303771 * kd, 0.000062,
            207.14 + 2.453732 * kd, 0.000060,
            154.84 + 7.306860 * kd, 0.000056,
            34.52 + 27.261239 * kd, 0.000047,
            207.19 + 0.121824 * kd, 0.000042,
            291.34 + 1.844379 * kd, 0.000040,
            161.72 + 24.198154 * kd, 0.000037,
            239.56 + 25.513099 * kd, 0.000035,
            331.55 + 3.592518 * kd, 0.000023,
        )
        var i = 0
        while (i < planetary.size) {
            jde += planetary[i + 1] * sin(planetary[i] * DEG)
            i += 2
        }
        return jde
    }

    /** Instants of new moon in [from, to). */
    fun newMoonsBetween(from: Instant, to: Instant): List<Instant> {
        val jdeFrom = jdeOf(from)
        val jdeTo = jdeOf(to)
        var k = floor((jdeFrom - 2451550.09766) / SYNODIC_MONTH).toLong() - 1
        val result = mutableListOf<Instant>()
        while (true) {
            val jde = newMoonJde(k)
            if (jde >= jdeTo) break
            if (jde >= jdeFrom) result += instantOfJde(jde)
            k++
        }
        return result
    }

    /** The last new moon at or before [instant] and the first after it. */
    fun lunationAround(instant: Instant): Pair<Instant, Instant> {
        val jde = jdeOf(instant)
        var k = floor((jde - 2451550.09766) / SYNODIC_MONTH).toLong() + 1
        while (newMoonJde(k) > jde) k--
        return instantOfJde(newMoonJde(k)) to instantOfJde(newMoonJde(k + 1))
    }

    /**
     * Approximate elongation of the moon from the sun in degrees, [0, 360),
     * interpolated linearly across the lunation. Good to a few degrees:
     * enough for a phase glyph, not for eclipses.
     */
    fun moonElongationDeg(instant: Instant): Double {
        val (previous, next) = lunationAround(instant)
        val fraction = (instant.toEpochMilli() - previous.toEpochMilli()).toDouble() /
            (next.toEpochMilli() - previous.toEpochMilli()).toDouble()
        return fraction * 360.0
    }

    /** Instant when the sun's apparent longitude equals [targetDeg], starting near [guess]. */
    fun solarLongitudeInstant(targetDeg: Double, guess: Instant): Instant {
        var jde = jdeOf(guess)
        repeat(12) {
            val delta = normalizeDegSigned(targetDeg - sunApparentLongitudeDeg(jde))
            jde += delta * 365.2422 / 360.0
            if (kotlin.math.abs(delta) < 1e-8) return instantOfJde(jde)
        }
        return instantOfJde(jde)
    }

    /**
     * Every instant in [from, to) at which the sun's longitude crosses a
     * multiple of [stepDeg], paired with that longitude in whole degrees.
     */
    fun solarLongitudeCrossings(from: Instant, to: Instant, stepDeg: Int = 15): List<Pair<Int, Instant>> {
        val result = mutableListOf<Pair<Int, Instant>>()
        var cursor = from
        var lambda = sunApparentLongitudeDeg(jdeOf(cursor))
        var target = (floor(lambda / stepDeg).toInt() + 1) * stepDeg
        while (true) {
            val daysAhead = normalizeDeg(target - lambda) * 365.2422 / 360.0
            val guess = cursor.plusSeconds((daysAhead * 86400).toLong())
            val instant = solarLongitudeInstant((target % 360).toDouble(), guess)
            if (!instant.isBefore(to)) break
            if (!instant.isBefore(from)) result += (target % 360) to instant
            cursor = instant
            lambda = target.toDouble()
            target += stepDeg
        }
        return result
    }

    internal fun normalizeRad(x: Double): Double {
        val r = x % (2 * PI)
        return if (r < 0) r + 2 * PI else r
    }

    private fun normalizeDeg(x: Double): Double {
        val r = x % 360.0
        return if (r < 0) r + 360.0 else r
    }

    private fun normalizeDegSigned(x: Double): Double {
        val r = normalizeDeg(x)
        return if (r > 180.0) r - 360.0 else r
    }
}
