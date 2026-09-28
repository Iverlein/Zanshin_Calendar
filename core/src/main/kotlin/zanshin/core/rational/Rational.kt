/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.rational

import kotlin.math.abs

/**
 * Exact rational number over Long, always in lowest terms with a positive
 * denominator. Every multiplication and addition is overflow-checked: the
 * Tibetan arithmetic stays far inside Long range for 1900–2100, and an
 * overflow is a bug to surface, not a value to wrap.
 */
class Rational private constructor(val num: Long, val den: Long) : Comparable<Rational> {

    operator fun plus(o: Rational): Rational {
        val g = gcd(den, o.den)
        val a = Math.multiplyExact(num, o.den / g)
        val b = Math.multiplyExact(o.num, den / g)
        return of(Math.addExact(a, b), Math.multiplyExact(den / g, o.den))
    }

    operator fun minus(o: Rational): Rational = this + (-o)

    operator fun unaryMinus(): Rational = Rational(-num, den)

    operator fun times(o: Rational): Rational {
        val g1 = gcd(abs(num), o.den)
        val g2 = gcd(abs(o.num), den)
        return of(
            Math.multiplyExact(num / g1, o.num / g2),
            Math.multiplyExact(den / g2, o.den / g1),
        )
    }

    operator fun div(o: Rational): Rational {
        require(o.num != 0L) { "division by zero" }
        return this * of(o.den, o.num)
    }

    operator fun plus(n: Long): Rational = this + of(n)
    operator fun minus(n: Long): Rational = this - of(n)
    operator fun times(n: Long): Rational = this * of(n)
    operator fun div(n: Long): Rational = this / of(n)

    /** Largest integer ≤ this. */
    fun floor(): Long = Math.floorDiv(num, den)

    /** this − floor(this), in [0, 1): "modulo 1" for angles in revolutions. */
    fun frac(): Rational = of(Math.floorMod(num, den), den)

    fun toDouble(): Double = num.toDouble() / den.toDouble()

    override fun compareTo(other: Rational): Int =
        Math.multiplyExact(num, other.den).compareTo(Math.multiplyExact(other.num, den))

    override fun equals(other: Any?): Boolean = other is Rational && num == other.num && den == other.den

    override fun hashCode(): Int = 31 * num.hashCode() + den.hashCode()

    override fun toString(): String = if (den == 1L) "$num" else "$num/$den"

    companion object {
        val ZERO = Rational(0, 1)
        val ONE = Rational(1, 1)

        fun of(num: Long, den: Long = 1): Rational {
            require(den != 0L) { "zero denominator" }
            var n = num
            var d = den
            if (d < 0) {
                n = Math.negateExact(n)
                d = Math.negateExact(d)
            }
            val g = gcd(abs(n), d)
            return Rational(n / g, d / g)
        }

        private tailrec fun gcd(a: Long, b: Long): Long = if (b == 0L) (if (a == 0L) 1 else a) else gcd(b, a % b)
    }
}

fun Long.toRational(): Rational = Rational.of(this)

fun Int.toRational(): Rational = Rational.of(this.toLong())
