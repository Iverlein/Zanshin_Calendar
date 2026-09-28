/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.core.rational

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class RationalTest {
    @Test
    fun arithmetic() {
        val a = Rational.of(167025, 5656)
        assertEquals(Rational.of(11135, 11312), a / 30)
        assertEquals(Rational.of(1, 2), Rational.of(1, 3) + Rational.of(1, 6))
        assertEquals(Rational.of(-7, 2).floor(), -4L)
        assertEquals(Rational.of(1, 2), Rational.of(-7, 2).frac())
        assertEquals(Rational.of(2, 3), Rational.of(-4, -6))
    }

    @Test
    fun `overflow is an error, not a wrap`() {
        val big = Rational.of(Long.MAX_VALUE / 2, 1)
        assertThrows(ArithmeticException::class.java) { big * 4 }
    }
}
