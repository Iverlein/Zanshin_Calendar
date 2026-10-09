/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.bell

import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.exp
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sin

/**
 * The bells, synthesised on the device rather than recorded (SPEC §10.9):
 * no sound file whose licence F-Droid would have to check. Each strike is
 * a sum of inharmonic partials, each split in two close modes whose beating
 * gives the bowl its waver, decaying exponentially after a short attack.
 */
enum class BellSound(
    /** The fundamental, Hz. */
    val pitch: Double,
    /** Partials as (ratio to [pitch], amplitude, decay time constant in seconds). */
    val partials: List<Triple<Double, Double, Double>>,
    /** Hz between the two modes of a partial. */
    val beat: Double,
) {
    /** A large bowl: low, long, wavering. */
    BOWL(
        196.0,
        listOf(Triple(1.0, 1.0, 4.5), Triple(2.76, 0.55, 2.6), Triple(5.12, 0.28, 1.5), Triple(8.15, 0.12, 0.9), Triple(11.7, 0.05, 0.6)),
        1.1,
    ),

    /** A small bowl: higher and clearer. */
    SMALL_BOWL(
        587.0,
        listOf(Triple(1.0, 1.0, 3.2), Triple(2.71, 0.4, 1.8), Triple(5.05, 0.16, 0.9), Triple(7.95, 0.06, 0.5)),
        2.2,
    ),

    /** A bell: a bright strike over a hum an octave down. */
    BELL(
        440.0,
        listOf(
            Triple(0.5, 0.45, 3.4), Triple(1.0, 0.8, 2.4), Triple(1.19, 0.5, 1.6), Triple(1.5, 0.3, 1.2),
            Triple(2.0, 0.6, 1.1), Triple(2.52, 0.25, 0.7), Triple(3.0, 0.15, 0.5),
        ),
        0.7,
    ),
    ;

    companion object {
        const val SAMPLE_RATE = 44100

        /** Seconds from one strike to the next when a bell is struck more than once. */
        const val STRIKE_GAP = 5.0

        /** A strike's length, kept under the ten seconds a broadcast may take (the periodic bell rings from one). */
        const val STRIKE_SECONDS = 8.0
    }

    /**
     * [strikes] strikes [STRIKE_GAP] apart, as 16-bit mono samples at
     * [SAMPLE_RATE], peaking at nine tenths of full scale and fading to
     * silence at the end.
     */
    fun render(strikes: Int = 1): ShortArray {
        require(strikes >= 1)
        val strikeFrames = (STRIKE_SECONDS * SAMPLE_RATE).toInt()
        val gapFrames = (STRIKE_GAP * SAMPLE_RATE).toInt()
        val total = gapFrames * (strikes - 1) + strikeFrames
        val mix = DoubleArray(total)
        val one = strike(strikeFrames)
        for (s in 0 until strikes) {
            val at = s * gapFrames
            for (i in one.indices) mix[at + i] += one[i]
        }
        val peak = mix.maxOf { abs(it) }.takeIf { it > 0 } ?: 1.0
        val scale = 0.9 * Short.MAX_VALUE / peak
        return ShortArray(total) { (mix[it] * scale).toInt().toShort() }
    }

    private fun strike(frames: Int): DoubleArray {
        val out = DoubleArray(frames)
        val attack = 0.004 * SAMPLE_RATE
        val fade = 0.6 * SAMPLE_RATE
        for ((ratio, amplitude, decay) in partials) {
            val f = pitch * ratio
            // The two modes of a partial, the beat apart; higher partials beat faster.
            for (mode in listOf(f - beat * ratio / 2, f + beat * ratio / 2)) {
                val step = 2 * PI * mode / SAMPLE_RATE
                val fall = exp(-1.0 / (decay * SAMPLE_RATE))
                var envelope = amplitude / 2
                for (i in 0 until frames) {
                    out[i] += envelope * sin(step * i)
                    envelope *= fall
                }
            }
        }
        for (i in 0 until frames) {
            val rise = min(1.0, i / attack)
            val tail = min(1.0, (frames - 1 - i) / fade)
            out[i] *= rise * max(0.0, tail)
        }
        return out
    }
}
