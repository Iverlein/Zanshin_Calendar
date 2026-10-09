/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package zanshin.app.bell

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.PowerManager
import android.os.SystemClock
import androidx.core.app.NotificationCompat
import androidx.core.app.ServiceCompat
import io.github.iverlein.zanshin.R
import zanshin.app.AppLanguage
import zanshin.app.MainActivity

/**
 * The meditation timer (SPEC §10.9): a foreground service that holds the
 * CPU awake for one [Sitting] and rings its bells with the screen off,
 * its notification counting down with a Stop button.
 */
class MeditationService : Service() {
    private val handler = Handler(Looper.getMainLooper())
    private var wakeLock: PowerManager.WakeLock? = null
    private lateinit var settings: BellSettings

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(AppLanguage.wrap(base))
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action == ACTION_STOP) {
            finish()
            return START_NOT_STICKY
        }
        if (MeditationSession.state.value != null) return START_NOT_STICKY
        settings = BellSettings(this)
        val sitting = Sitting(SystemClock.elapsedRealtime(), settings.session)
        channel()
        ServiceCompat.startForeground(
            this,
            NOTIFICATION_ID,
            notification(sitting),
            if (Build.VERSION.SDK_INT >= 34) ServiceInfo.FOREGROUND_SERVICE_TYPE_SPECIAL_USE else 0,
        )
        val total = sitting.endsAt - sitting.startedAt + (settings.sound.seconds(sitting.plan.endStrikes) * 1000).toLong() + 30_000
        wakeLock = getSystemService(PowerManager::class.java)
            .newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "zanshin:sitting")
            .apply { acquire(total) }
        MeditationSession.state.value = sitting
        BellPlayer.prepare(settings.sound)
        BellPlayer.prepare(sitting.plan.between, sitting.plan.betweenStrikes)
        BellPlayer.prepare(settings.sound, sitting.plan.endStrikes)
        val bells = sitting.bells
        for ((i, bell) in bells.withIndex()) {
            val last = i == bells.lastIndex
            handler.postAtTime({ ring(sitting, bell, last) }, TOKEN, uptimeAt(sitting.startedAt + bell.offset))
        }
        return START_NOT_STICKY
    }

    /** The uptime clock's reading at an elapsed-realtime instant; the wake lock keeps the two in step. */
    private fun uptimeAt(elapsed: Long): Long = SystemClock.uptimeMillis() + (elapsed - SystemClock.elapsedRealtime())

    private fun ring(sitting: Sitting, bell: Sitting.Bell, last: Boolean) {
        val sound = if (bell.between) sitting.plan.between else settings.sound
        BellPlayer.ring(this, sound, bell.strikes, settings.volume) { if (last) finish() }
        // Each bell begins a period or ends the sitting: the countdown moves on to it.
        val at = maxOf(SystemClock.elapsedRealtime(), sitting.startedAt + bell.offset)
        getSystemService(NotificationManager::class.java).notify(NOTIFICATION_ID, notification(sitting, at))
    }

    private fun finish() {
        handler.removeCallbacksAndMessages(TOKEN)
        MeditationSession.state.value = null
        wakeLock?.takeIf { it.isHeld }?.release()
        wakeLock = null
        ServiceCompat.stopForeground(this, ServiceCompat.STOP_FOREGROUND_REMOVE)
        stopSelf()
    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(TOKEN)
        MeditationSession.state.value = null
        wakeLock?.takeIf { it.isHeld }?.release()
        super.onDestroy()
    }

    private fun channel() {
        val channel = NotificationChannel(CHANNEL, getString(R.string.meditation_channel), NotificationManager.IMPORTANCE_LOW)
        channel.setShowBadge(false)
        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
    }

    /** The notification as it stands at [now], an elapsed-realtime instant. */
    private fun notification(sitting: Sitting, now: Long = SystemClock.elapsedRealtime()): Notification {
        val phase = sitting.phase(now)
        val open = PendingIntent.getActivity(
            this,
            0,
            Intent(this, MainActivity::class.java)
                .putExtra(MainActivity.EXTRA_SCREEN, MainActivity.SCREEN_MEDITATION)
                .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP),
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT,
        )
        val stop = PendingIntent.getService(
            this,
            1,
            Intent(this, MeditationService::class.java).setAction(ACTION_STOP),
            PendingIntent.FLAG_IMMUTABLE,
        )
        val periods = sitting.plan.periods.size
        val text = when {
            phase == Sitting.Phase.WARM_UP -> getString(R.string.meditation_warm_up)
            phase == Sitting.Phase.ENDED -> getString(R.string.meditation_ended)
            periods > 1 -> getString(R.string.meditation_period_of, sitting.period(now) + 1, periods)
            else -> getString(R.string.meditation_sitting)
        }
        return NotificationCompat.Builder(this, CHANNEL)
            .setSmallIcon(R.drawable.ic_bell)
            .setContentTitle(getString(R.string.meditation_title))
            .setContentText(text)
            .setContentIntent(open)
            .addAction(0, getString(R.string.meditation_stop), stop)
            .setOngoing(true)
            .setSilent(true)
            .setCategory(NotificationCompat.CATEGORY_STOPWATCH)
            .setForegroundServiceBehavior(NotificationCompat.FOREGROUND_SERVICE_IMMEDIATE)
            .apply {
                if (phase != Sitting.Phase.ENDED) {
                    setUsesChronometer(true)
                    setChronometerCountDown(true)
                    setWhen(System.currentTimeMillis() + sitting.remaining(now))
                    setShowWhen(true)
                }
            }
            .build()
    }

    companion object {
        private const val CHANNEL = "meditation"
        private const val NOTIFICATION_ID = 1
        private const val ACTION_STOP = "zanshin.app.bell.STOP"
        private val TOKEN = Any()

        fun start(context: Context) {
            context.startForegroundService(Intent(context, MeditationService::class.java))
        }

        fun stop(context: Context) {
            context.startService(Intent(context, MeditationService::class.java).setAction(ACTION_STOP))
        }
    }
}
