/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.wheel

import zanshin.app.bell.BellSettings
import kotlin.math.PI
import kotlin.math.ceil
import kotlin.math.exp
import kotlin.math.floor
import kotlin.math.hypot
import kotlin.math.max
import kotlin.math.min

/** How the wheel stands (SPEC §10.12): a drum seen from the side, or a ring seen from above. */
enum class WheelForm { DRUM, RING }

/** The colours offered for the script: each stands 4.5:1 on every [WheelGround], so no choice hides the mantra. */
enum class WheelScript(val argb: Int) {
    GOLD(0xFFE3A94F.toInt()),
    WHITE(0xFFF2EEE4.toInt()),
    RED(0xFFEC8468.toInt()),
    GREEN(0xFF9CCB98.toInt()),
    BLUE(0xFF8DB4E8.toInt()),
    TURQUOISE(0xFF5FC9BC.toInt()),
}

/** The colours offered for the ground behind the script. */
enum class WheelGround(val argb: Int) {
    BLACK(0xFF000000.toInt()),
    INK(0xFF111311.toInt()),
    MAROON(0xFF3A1117.toInt()),
    INDIGO(0xFF101B3A.toInt()),
    FOREST(0xFF0F2A1E.toInt()),
    UMBER(0xFF2A1A0E.toInt()),
}

/**
 * The mantras offered (SPEC §10.12), each as printed in the work the claim wheel-mantras names for it, in script alone;
 * [OWN] is the user's own line. Their names are the interface's (`wheel_mantra_*`).
 */
enum class WheelMantra(val text: String?) {
    MANI(BellSettings.MANTRA),
    VAJRA_GURU("ཨོཾ་ཨཱཿཧཱུྃ་བཛྲ་གུ་རུ་པདྨ་སིདྡྷི་ཧཱུྃ"),
    TARA("ཨོཾ་ཏཱ་རེ་ཏུཏྟཱ་རེ་ཏུ་རེ་སྭཱ་ཧཱ"),
    MANJUSHRI("ཨོཾ་ཨ་ར་པ་ཙ་ན་དྷཱིཿ"),
    VAJRAPANI("ཨོཾ་བཛྲ་པཱ་ཎི་ཧཱུྃ"),
    MEDICINE("ཏདྱ་ཐཱ། ཨོཾ་བྷཻ་ཥ་ཛྱེ་བྷཻ་ཥ་ཛྱེ། མ་ཧཱ་བྷཻ་ཥ་ཛྱེ། བྷཻ་ཥ་ཛྱེ་རཱ་ཛཱ་ཡ། ས་མུངྒ་ཏེ་སྭཱ་ཧཱ"),
    SHAKYAMUNI("ཏདྱ་ཐཱ། ཨོཾ་མུ་ནེ་མུ་ནེ་མ་ཧཱ་མུ་ན་ཡེ་སྭཱ་ཧཱ"),
    OWN(null),
}

/** What the painter needs: the words, the form and the two colours. */
data class WheelLook(val text: String, val form: WheelForm, val script: Int, val ground: Int)

/**
 * The turning (SPEC §10.12): [angle] runs on at [speed], which eases back to the slow turn. The wheel never runs
 * backwards: a finger with it speeds it, a finger against it only brakes it, and from a stop it takes up the slow
 * turn again.
 */
class WheelMotion {
    var angle = 0.0
        private set
    var speed = REST
        private set

    /** Moves the wheel on by [seconds]; a long gap, the screen having been away, counts as one short step. */
    fun step(seconds: Double) {
        val dt = seconds.coerceIn(0.0, MAX_STEP)
        speed = REST + (speed - REST) * exp(-dt / EASE)
        angle = (angle + speed * dt) % TURN
    }

    /** A finger moving at [rate] radians a second, positive in the wheel's own direction. */
    fun touch(rate: Double) {
        when {
            rate > speed -> speed = min(rate, MAX)
            rate < 0 -> speed = max(0.0, speed + rate * BRAKE)
        }
    }

    companion object {
        const val TURN = 2 * PI

        /** One turn in thirty seconds. */
        const val REST = TURN / 30

        /** Two turns a second, above which the script is only a blur. */
        const val MAX = TURN * 2

        /** The time constant of the ease back to the slow turn, seconds. */
        const val EASE = 5.0
        private const val MAX_STEP = 0.1
        private const val BRAKE = 0.25

        /** A drum's turning for a finger's velocity along x: its front runs from right to left. */
        fun drumRate(velocityX: Float, radius: Float): Double = if (radius > 0) -velocityX.toDouble() / radius else 0.0

        /** A ring's turning for a finger at (x, y) from its centre, in screen axes, moving at (vx, vy); clockwise positive. */
        fun ringRate(x: Float, y: Float, velocityX: Float, velocityY: Float): Double {
            val r2 = x.toDouble() * x + y.toDouble() * y
            return if (r2 > 1) (x.toDouble() * velocityY - y.toDouble() * velocityX) / r2 else 0.0
        }
    }
}

/**
 * The words as the wheel lays them out: cut after each tsheg, shad or space, so that a stack stays whole, and closed
 * by a tsheg or a space so that the end meets the beginning as the mantra comes round again.
 */
fun wheelClusters(text: String): List<String> {
    val line = text.trim()
    if (line.isEmpty()) return emptyList()
    val out = mutableListOf<String>()
    val cluster = StringBuilder()
    for (c in line) {
        cluster.append(c)
        if (c in BREAKS) {
            if (cluster.isNotBlank()) out += cluster.toString()
            cluster.clear()
        }
    }
    if (cluster.isNotEmpty()) out += cluster.toString() + if (cluster.last() in 'ༀ'..'࿿') TSHEG else ' '
    return out
}

private const val TSHEG = '་'
private const val BREAKS = "་།༎ "

/** The size of the script and how many times the mantra goes round. */
data class WheelFit(val repeats: Int, val em: Float, val radius: Float)

/**
 * The fit in a [width] by [height] canvas, for a mantra [advance] ems long and [tall] ems high, its widest piece [widest]
 * ems. A drum's mantra closes on its circumference, going round more times where one round would stand taller than the
 * canvas; a ring's goes round as often as keeps its script clear of the centre and each straight piece on the curve, the
 * corners of the widest within the canvas, and [WheelFit.radius] is the middle of the script.
 */
fun wheelFit(form: WheelForm, width: Float, height: Float, advance: Float, tall: Float, widest: Float): WheelFit = when (form) {
    WheelForm.DRUM -> {
        val radius = width / 2 * 0.94f
        val least = ceil(TURN_F * radius * tall / (advance * height * 0.8f)).toInt()
        val repeats = max(1, least)
        WheelFit(repeats, TURN_F * radius / (repeats * advance), radius)
    }
    WheelForm.RING -> {
        val outer = min(width, height) / 2 * 0.94f
        val repeats = max(1, ceil(max(RING_CLEARANCE * tall, widest) * TURN_F / advance).toInt())
        val round = repeats * advance / TURN_F
        val em = outer / hypot(round + tall / 2, widest / 2)
        WheelFit(repeats, em, round * em)
    }
}

private const val TURN_F = (2 * PI).toFloat()

/** A ring's middle stands at least this many times the script's height from the centre. */
private const val RING_CLEARANCE = 1.1f

/** A size in whole pixels, at least one. */
internal fun wholePixels(value: Float): Int = max(1, floor(value).toInt())
