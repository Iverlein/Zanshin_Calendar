/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.wheel

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.math.hypot
import kotlin.math.pow

/** The prayer wheel's turning, its layout and its colours (SPEC §10.12). */
class WheelTest {
    private fun run(motion: WheelMotion, seconds: Double) = repeat((seconds * 60).toInt()) { motion.step(1.0 / 60) }

    @Test
    fun `it turns by itself at the slow turn`() {
        val motion = WheelMotion()
        run(motion, 3.0)
        assertEquals(WheelMotion.REST * 3, motion.angle, 1e-3)
    }

    @Test
    fun `a flick speeds it up to the most, and it eases back`() {
        val motion = WheelMotion()
        motion.touch(1000.0)
        assertEquals(WheelMotion.MAX, motion.speed, 1e-9)
        run(motion, 60.0)
        assertEquals(WheelMotion.REST, motion.speed, 1e-3)
    }

    @Test
    fun `a finger either way raises the speed and never lowers it or turns it back`() {
        val motion = WheelMotion()
        motion.touch(-5.0)
        assertEquals(5.0, motion.speed, 1e-9, "against the turning counts as with it")
        motion.touch(-1.0)
        assertEquals(5.0, motion.speed, 1e-9, "a slower finger takes nothing away")
        motion.touch(-1000.0)
        assertEquals(WheelMotion.MAX, motion.speed, 1e-9)
        val before = motion.angle
        motion.step(0.05)
        assertTrue(motion.angle > before)
    }

    @Test
    fun `each tap adds, up to the most`() {
        val motion = WheelMotion()
        motion.nudge()
        assertEquals(WheelMotion.REST + WheelMotion.NUDGE, motion.speed, 1e-9)
        motion.nudge()
        assertEquals(WheelMotion.REST + 2 * WheelMotion.NUDGE, motion.speed, 1e-9)
        repeat(20) { motion.nudge() }
        assertEquals(WheelMotion.MAX, motion.speed, 1e-9)
        run(motion, 60.0)
        assertEquals(WheelMotion.REST, motion.speed, 1e-3)
    }

    @Test
    fun `a slow finger with it changes nothing`() {
        val motion = WheelMotion()
        motion.touch(5.0)
        motion.touch(1.0)
        assertEquals(5.0, motion.speed, 1e-9)
    }

    private fun rest(finger: WheelFinger, motion: WheelMotion, seconds: Double) = repeat((seconds * 60).toInt()) {
        finger.step(1.0 / 60)
        motion.step(1.0 / 60)
    }

    @Test
    fun `a finger resting holds the wheel, and letting go leaves it at the slow turn`() {
        val motion = WheelMotion()
        motion.touch(WheelMotion.MAX)
        val finger = WheelFinger(motion, 10f)
        finger.down(100f, 100f)
        rest(finger, motion, 0.5)
        assertTrue(motion.held)
        assertEquals(0.0, motion.speed, 0.0)
        val stood = motion.angle
        finger.move(103f, 102f, 50.0)
        rest(finger, motion, 2.0)
        assertEquals(stood, motion.angle, 0.0, "a finger's tremble does not free it")
        finger.up()
        assertFalse(motion.held)
        assertEquals(WheelMotion.REST, motion.speed, 0.0)
        motion.step(0.05)
        assertTrue(motion.angle > stood)
    }

    @Test
    fun `a quick tap quickens a little and does not hold`() {
        val motion = WheelMotion()
        val finger = WheelFinger(motion, 10f)
        finger.down(100f, 100f)
        rest(finger, motion, 0.1)
        finger.up()
        assertFalse(motion.held)
        assertTrue(motion.speed > WheelMotion.NUDGE)
        assertTrue(motion.speed < WheelMotion.MAX / 4)
    }

    @Test
    fun `a swipe speeds it either way, also out of a hold`() {
        for (rate in listOf(9.0, -9.0)) {
            val motion = WheelMotion()
            val finger = WheelFinger(motion, 10f)
            finger.down(100f, 100f)
            rest(finger, motion, 0.5)
            assertTrue(motion.held)
            finger.move(200f, 100f, rate)
            assertFalse(motion.held)
            assertEquals(9.0, motion.speed, 1e-9)
            rest(finger, motion, 1.0)
            assertFalse(motion.held, "a finger stopped after a swipe does not hold")
            finger.up()
            assertTrue(motion.speed > WheelMotion.REST)
        }
    }

    @Test
    fun `a finger lost to the system never leaves the wheel held`() {
        val motion = WheelMotion()
        val finger = WheelFinger(motion, 10f)
        finger.down(0f, 0f)
        rest(finger, motion, 0.5)
        finger.up()
        finger.up()
        assertFalse(motion.held)
        assertEquals(WheelMotion.REST, motion.speed, 0.0)
    }

    @Test
    fun `a gap in the frames counts as one short step`() {
        val motion = WheelMotion()
        motion.step(3600.0)
        assertTrue(motion.angle < 0.1)
    }

    @Test
    fun `a finger counts in every direction`() {
        for ((x, y) in listOf(100f to 0f, -100f to 0f, 0f to 100f, 0f to -100f)) {
            assertEquals(2.0, WheelMotion.fingerRate(x, y, 50f), 1e-9)
        }
        assertEquals(10.0, WheelMotion.fingerRate(300f, -400f, 50f), 1e-9)
        assertEquals(0.0, WheelMotion.fingerRate(100f, 100f, 0f), 0.0, "before the first drawing there is no radius")
    }

    @Test
    fun `the mantra is cut after each tsheg, and its stacks stay whole`() {
        assertEquals(listOf("ཨོཾ་", "མ་", "ཎི་", "པདྨེ་", "ཧཱུྃ་"), wheelClusters("ཨོཾ་མ་ཎི་པདྨེ་ཧཱུྃ"))
        assertEquals(listOf("om ", "mani "), wheelClusters("  om mani "))
        assertEquals(listOf("ཐཱ།", "ཨོཾ་"), wheelClusters("ཐཱ། ཨོཾ"), "the space after a shad is no piece of its own")
        assertEquals(emptyList<String>(), wheelClusters("   "))
    }

    @Test
    fun `every mantra offered is in script, and only the own line has none`() {
        for (mantra in WheelMantra.entries) {
            if (mantra == WheelMantra.OWN) {
                assertEquals(null, mantra.text)
            } else {
                assertTrue(mantra.text!!.all { it == ' ' || it in 'ༀ'..'࿿' }, mantra.name)
            }
        }
        assertEquals(WheelMantra.entries.size, WheelMantra.entries.map { it.text }.toSet().size)
    }

    @Test
    fun `a drum fills the width and stays within the height`() {
        for ((advance, tall) in listOf(4f to 1.3f, 2.5f to 1.3f, 16f to 1.3f)) {
            for ((w, h) in listOf(1080f to 1000f, 1080f to 400f, 400f to 1000f)) {
                val fit = wheelFit(WheelForm.DRUM, w, h, advance, tall, 1f)
                assertTrue(fit.radius * 2 <= w)
                assertTrue(fit.em * tall <= h * 0.8f + 1, "$advance $tall in $w by $h")
                assertEquals(fit.repeats * advance * fit.em, (2 * Math.PI * fit.radius).toFloat(), 1f)
            }
        }
    }

    @Test
    fun `a ring stays within the canvas and clear of its centre`() {
        for ((advance, tall, widest) in listOf(Triple(4f, 1.3f, 1.1f), Triple(2.5f, 1.3f, 0.8f), Triple(16f, 1.3f, 1.2f), Triple(4.7f, 0.55f, 2.2f))) {
            val fit = wheelFit(WheelForm.RING, 1080f, 900f, advance, tall, widest)
            assertTrue(hypot(fit.radius + fit.em * tall / 2, fit.em * widest / 2) <= 450f, "a corner of the widest piece")
            assertTrue(fit.radius - fit.em * tall / 2 > 0)
            assertTrue(fit.em * widest <= fit.radius + 1, "a piece no wider than the ring's radius")
        }
    }

    @Test
    fun `every script stands on every ground`() {
        for (script in WheelScript.entries) {
            for (ground in WheelGround.entries) {
                val ratio = contrast(script.argb, ground.argb)
                assertTrue(ratio >= 4.5, "$script on $ground: $ratio")
            }
        }
    }

    private fun contrast(a: Int, b: Int): Double {
        val la = luminance(a)
        val lb = luminance(b)
        return (maxOf(la, lb) + 0.05) / (minOf(la, lb) + 0.05)
    }

    private fun luminance(argb: Int): Double {
        fun channel(shift: Int): Double {
            val c = (argb shr shift and 0xFF) / 255.0
            return if (c <= 0.03928) c / 12.92 else ((c + 0.055) / 1.055).pow(2.4)
        }
        return 0.2126 * channel(16) + 0.7152 * channel(8) + 0.0722 * channel(0)
    }
}
