/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.wheel

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Shader
import android.graphics.Typeface
import androidx.core.content.res.ResourcesCompat
import io.github.iverlein.zanshin.R
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sin

/**
 * Draws the wheel on a plain [Canvas], for the screen and the wallpaper alike (SPEC §10.12). Each piece of the mantra
 * is drawn once into a bitmap of its own; a drum bends each over its curve as a mesh, a ring stands each whole. No
 * OpenGL: the canvas needs nothing a phone may lack.
 */
class WheelPainter(context: Context) {
    private val typeface: Typeface = ResourcesCompat.getFont(context, R.font.noto_serif_tibetan) ?: Typeface.SERIF
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG)
    private val shade = Paint()
    private val verts = FloatArray((COLUMNS + 1) * 2 * 2)

    private class Piece(val bitmap: Bitmap, val advance: Float, val start: Float)

    private var built: WheelLook? = null
    private var builtWidth = 0
    private var builtHeight = 0
    private var pieces: List<Piece> = emptyList()
    private var round = 0f
    private var margin = 0f
    private var fit = WheelFit(1, 0f, 0f)

    /** The wheel's turning for a finger moving at (vx, vy) pixels a second. */
    fun rate(velocityX: Float, velocityY: Float): Double = WheelMotion.fingerRate(velocityX, velocityY, fit.radius)

    /** Draws the wheel filling [width] by [height] at [angle], radians turned. */
    fun draw(canvas: Canvas, width: Int, height: Int, angle: Double, look: WheelLook) {
        canvas.drawColor(look.ground)
        if (width <= 0 || height <= 0) return
        if (look != built || width != builtWidth || height != builtHeight) build(width, height, look)
        if (pieces.isEmpty()) return
        when (look.form) {
            WheelForm.DRUM -> drum(canvas, width, height, angle)
            WheelForm.RING -> ring(canvas, width, height, angle)
        }
    }

    /** Lets the bitmaps go; the next [draw] makes them again. */
    fun release() {
        pieces.forEach { it.bitmap.recycle() }
        pieces = emptyList()
        built = null
    }

    private fun build(width: Int, height: Int, look: WheelLook) {
        release()
        val clusters = wheelClusters(look.text)
        val text = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            typeface = this@WheelPainter.typeface
            textSize = MEASURE
            color = look.script
        }
        // The script's extent, measured at a large size and scaled down, the whole line alike so that all stand on one base.
        val bounds = Rect()
        var top = 0
        var bottom = 0
        var advance = 0f
        var widest = 0f
        for (cluster in clusters) {
            text.getTextBounds(cluster, 0, cluster.length, bounds)
            top = min(top, bounds.top)
            bottom = max(bottom, bounds.bottom)
            val width1 = text.measureText(cluster)
            advance += width1
            widest = max(widest, width1)
        }
        if (advance <= 0f) return
        val tall = (bottom - top) / MEASURE
        fit = wheelFit(look.form, width.toFloat(), height.toFloat(), advance / MEASURE, tall, widest / MEASURE)
        text.textSize = fit.em
        margin = fit.em * MARGIN
        val bitmapHeight = wholePixels(tall * fit.em) + 2
        val baseline = -top / MEASURE * fit.em + 1
        var start = 0f
        pieces = clusters.map { cluster ->
            val width1 = text.measureText(cluster)
            val bitmap = Bitmap.createBitmap(ceil(width1 + 2 * margin).toInt().coerceAtLeast(1), bitmapHeight, Bitmap.Config.ARGB_8888)
            Canvas(bitmap).drawText(cluster, margin, baseline, text)
            Piece(bitmap, width1, start).also { start += width1 }
        }
        round = start
        if (look.form == WheelForm.DRUM) {
            val cx = width / 2f
            val clear = look.ground and 0x00FFFFFF
            val deep = (look.ground and 0x00FFFFFF) or (0xD0 shl 24)
            shade.shader = LinearGradient(
                cx - fit.radius, 0f, cx + fit.radius, 0f,
                intArrayOf(deep, clear, clear, deep),
                floatArrayOf(0f, 0.28f, 0.72f, 1f),
                Shader.TileMode.CLAMP,
            )
        }
        built = look
        builtWidth = width
        builtHeight = height
    }

    private fun drum(canvas: Canvas, width: Int, height: Int, angle: Double) {
        val cx = width / 2f
        val cy = height / 2f
        val radius = fit.radius
        for (turn in 0 until fit.repeats) {
            for (piece in pieces) {
                val first = (turn * round + piece.start - margin) / radius - angle
                val span = (piece.advance + 2 * margin) / radius
                // Brought to the front's half turn: the middle of the piece between −π and π.
                val middle = wrap(first + span / 2)
                if (abs(middle) - span / 2 >= PI / 2) continue
                val from = middle - span / 2
                val half = piece.bitmap.height / 2f
                for (j in 0..COLUMNS) {
                    val theta = (from + span * j / COLUMNS).coerceIn(-PI / 2, PI / 2)
                    val x = cx + radius * sin(theta).toFloat()
                    // The script's height falls a little towards the edges, as the far side of a round thing does.
                    val rise = half * (1 - BOW * (1 - cos(theta).toFloat()))
                    verts[j * 2] = x
                    verts[j * 2 + 1] = cy - rise
                    verts[(COLUMNS + 1 + j) * 2] = x
                    verts[(COLUMNS + 1 + j) * 2 + 1] = cy + rise
                }
                canvas.drawBitmapMesh(piece.bitmap, COLUMNS, 1, verts, 0, null, 0, paint)
            }
        }
        canvas.drawRect(cx - radius, 0f, cx + radius, height.toFloat(), shade)
    }

    private fun ring(canvas: Canvas, width: Int, height: Int, angle: Double) {
        val cx = width / 2f
        val cy = height / 2f
        val radius = fit.radius
        val whole = round * fit.repeats
        for (turn in 0 until fit.repeats) {
            for (piece in pieces) {
                // Each stands upright at the foot of the ring: from there the line reads anticlockwise as the ring turns
                // clockwise, so that the syllables pass any one point in their order, as on the drum's front.
                val along = (turn * round + piece.start + piece.advance / 2) / whole * 360f
                canvas.save()
                canvas.rotate((Math.toDegrees(angle) - along).toFloat(), cx, cy)
                canvas.drawBitmap(piece.bitmap, cx - piece.bitmap.width / 2f, cy + radius - piece.bitmap.height / 2f, paint)
                canvas.restore()
            }
        }
    }

    private fun wrap(theta: Double): Double {
        val t = (theta + PI) % (2 * PI)
        return (if (t < 0) t + 2 * PI else t) - PI
    }

    private companion object {
        const val MEASURE = 200f
        const val COLUMNS = 16
        const val MARGIN = 0.08f
        const val BOW = 0.1f
    }
}
