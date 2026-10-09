/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.bell

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioFocusRequest
import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack
import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executors

/**
 * Rings a [BellSound] on the alarm stream, so that a sitting's bells sound
 * with the ringer silenced, and ducks other sound while it rings. The
 * samples are rendered off the main thread and kept for the next ring.
 */
object BellPlayer {
    private val renderer = Executors.newSingleThreadExecutor()
    private val main = Handler(Looper.getMainLooper())
    private val rendered = HashMap<Pair<BellSound, Int>, ShortArray>()
    private val attributes = AudioAttributes.Builder()
        .setUsage(AudioAttributes.USAGE_ALARM)
        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
        .build()

    /** Renders [sound] ahead of its first ring, so that the ring is not late. */
    fun prepare(sound: BellSound, strikes: Int = 1) {
        renderer.execute { samples(sound, strikes) }
    }

    /** Rings [sound] [strikes] times at [volume]; [onDone] runs on the main thread once it has faded. */
    fun ring(context: Context, sound: BellSound, strikes: Int, volume: Float, onDone: () -> Unit = {}) {
        val audio = context.applicationContext.getSystemService(AudioManager::class.java)
        renderer.execute {
            val data = samples(sound, strikes)
            main.post { play(audio, data, volume, onDone) }
        }
    }

    private fun samples(sound: BellSound, strikes: Int): ShortArray =
        synchronized(rendered) { rendered[sound to strikes] } ?: sound.render(strikes).also { synchronized(rendered) { rendered[sound to strikes] = it } }

    private fun play(audio: AudioManager, data: ShortArray, volume: Float, onDone: () -> Unit) {
        val track = runCatching {
            AudioTrack.Builder()
                .setAudioAttributes(attributes)
                .setAudioFormat(
                    AudioFormat.Builder()
                        .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                        .setSampleRate(BellSound.SAMPLE_RATE)
                        .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                        .build(),
                )
                .setTransferMode(AudioTrack.MODE_STATIC)
                .setBufferSizeInBytes(data.size * 2)
                .build()
        }.getOrNull()
        if (track == null) {
            onDone()
            return
        }
        val focus = AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK).setAudioAttributes(attributes).build()
        audio.requestAudioFocus(focus)
        track.write(data, 0, data.size)
        track.setVolume(volume)
        track.play()
        val millis = data.size * 1000L / BellSound.SAMPLE_RATE
        main.postDelayed({
            runCatching { track.stop() }
            track.release()
            audio.abandonAudioFocusRequest(focus)
            onDone()
        }, millis + 150)
    }
}
