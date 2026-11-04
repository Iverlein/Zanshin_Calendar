/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.wheel

import android.content.SharedPreferences
import android.graphics.Canvas
import android.os.SystemClock
import android.service.wallpaper.WallpaperService
import android.view.Choreographer
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.VelocityTracker

/**
 * The prayer wheel as a live wallpaper (SPEC §10.12): the screen's wheel on the home screen, with its settings. It draws
 * only while seen, thirty frames a second at the slow turn; a finger on the home screen and the turning of its pages
 * speed it.
 */
class WheelWallpaper : WallpaperService() {
    override fun onCreateEngine(): Engine = WheelEngine()

    private inner class WheelEngine : Engine(), Choreographer.FrameCallback, SharedPreferences.OnSharedPreferenceChangeListener {
        private val settings = WheelSettings(this@WheelWallpaper)
        private val painter = WheelPainter(this@WheelWallpaper)
        private val motion = WheelMotion()
        private var look = settings.look
        private var width = 0
        private var height = 0
        private var seen = false
        private var hardware = true
        private var lastFrame = 0L
        private var lastDrawn = 0L
        private var tracker: VelocityTracker? = null
        private var lastOffset = Float.NaN
        private var lastOffsetAt = 0L

        override fun onCreate(surfaceHolder: SurfaceHolder) {
            super.onCreate(surfaceHolder)
            setTouchEventsEnabled(true)
            settings.prefs.registerOnSharedPreferenceChangeListener(this)
        }

        override fun onDestroy() {
            stop()
            settings.prefs.unregisterOnSharedPreferenceChangeListener(this)
            tracker?.recycle()
            tracker = null
            painter.release()
            super.onDestroy()
        }

        override fun onSharedPreferenceChanged(prefs: SharedPreferences?, key: String?) {
            if (WheelSettings.isWheelKey(key)) look = settings.look
        }

        override fun onSurfaceChanged(holder: SurfaceHolder, format: Int, w: Int, h: Int) {
            super.onSurfaceChanged(holder, format, w, h)
            width = w
            height = h
            if (seen) draw()
        }

        override fun onSurfaceDestroyed(holder: SurfaceHolder) {
            stop()
            seen = false
            super.onSurfaceDestroyed(holder)
        }

        override fun onVisibilityChanged(visible: Boolean) {
            seen = visible
            if (visible) start() else stop()
        }

        override fun onTouchEvent(event: MotionEvent) {
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    tracker?.recycle()
                    tracker = VelocityTracker.obtain().also { it.addMovement(event) }
                }
                MotionEvent.ACTION_MOVE -> tracker?.let {
                    it.addMovement(event)
                    it.computeCurrentVelocity(1000)
                    motion.touch(painter.rate(event.x, event.y, it.xVelocity, it.yVelocity))
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    tracker?.recycle()
                    tracker = null
                }
            }
        }

        override fun onOffsetsChanged(xOffset: Float, yOffset: Float, xStep: Float, yStep: Float, xPixels: Int, yPixels: Int) {
            val now = SystemClock.uptimeMillis()
            val moved = xOffset - lastOffset
            val gap = (now - lastOffsetAt) / 1000.0
            if (moved != 0f && !moved.isNaN() && gap in 0.001..0.25) motion.touch(moved / gap * WheelMotion.TURN)
            lastOffset = xOffset
            lastOffsetAt = now
        }

        override fun doFrame(frameTimeNanos: Long) {
            if (!seen) return
            val now = SystemClock.uptimeMillis()
            if (lastFrame != 0L) motion.step((now - lastFrame) / 1000.0)
            lastFrame = now
            if (motion.speed > WheelMotion.REST * 4 || now - lastDrawn >= SLOW_FRAME_MILLIS) {
                lastDrawn = now
                draw()
            }
            Choreographer.getInstance().postFrameCallback(this)
        }

        private fun start() {
            lastFrame = 0L
            Choreographer.getInstance().removeFrameCallback(this)
            Choreographer.getInstance().postFrameCallback(this)
        }

        private fun stop() {
            Choreographer.getInstance().removeFrameCallback(this)
        }

        private fun draw() {
            val holder = surfaceHolder
            val canvas: Canvas = lock(holder) ?: return
            try {
                painter.draw(canvas, width, height, motion.angle, look)
            } finally {
                holder.unlockCanvasAndPost(canvas)
            }
        }

        /** The surface's canvas: the hardware's, or from the first refusal on the plain one. */
        private fun lock(holder: SurfaceHolder): Canvas? {
            if (hardware) {
                try {
                    return holder.lockHardwareCanvas()
                } catch (_: RuntimeException) {
                    hardware = false
                }
            }
            return try {
                holder.lockCanvas()
            } catch (_: RuntimeException) {
                null
            }
        }
    }

    private companion object {
        const val SLOW_FRAME_MILLIS = 30L
    }
}
