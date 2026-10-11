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
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.ServiceCompat
import io.github.iverlein.zanshin.R
import zanshin.app.AppLanguage
import zanshin.app.MainActivity

/**
 * One strike of the mindfulness bell (SPEC §10.9), rung in the foreground:
 * Android mutes an app's sound in the background, so the bell rings inside
 * a service whose notification, the bell's own message, shows while it
 * sounds and goes with it.
 */
class BellRingService : Service() {
    private var ringing = false

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(AppLanguage.wrap(base))
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val settings = BellSettings(this)
        channel()
        // Should Android refuse the foreground now, the bell still rings, without its notice.
        runCatching {
            ServiceCompat.startForeground(
                this,
                NOTIFICATION_ID,
                notification(settings.message),
                if (Build.VERSION.SDK_INT >= 34) ServiceInfo.FOREGROUND_SERVICE_TYPE_SPECIAL_USE else 0,
            )
        }
        if (!ringing) {
            ringing = true
            BellPlayer.ring(this, settings.sound, 1, settings.volume) {
                ServiceCompat.stopForeground(this, ServiceCompat.STOP_FOREGROUND_REMOVE)
                stopSelf()
            }
        }
        return START_NOT_STICKY
    }

    private fun channel() {
        // High importance for a heads-up notice; the bell is the sound, so the channel has none of its own.
        val channel = NotificationChannel(CHANNEL, getString(R.string.bell_title), NotificationManager.IMPORTANCE_HIGH)
        channel.setSound(null, null)
        channel.enableVibration(false)
        channel.setShowBadge(false)
        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
    }

    private fun notification(message: String): Notification {
        val open = PendingIntent.getActivity(
            this,
            2,
            Intent(this, MainActivity::class.java)
                .putExtra(MainActivity.EXTRA_SCREEN, MainActivity.SCREEN_MEDITATION)
                .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP),
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT,
        )
        return NotificationCompat.Builder(this, CHANNEL)
            .setSmallIcon(R.drawable.ic_bell)
            .setContentTitle(message.ifEmpty { getString(R.string.bell_title) })
            .setContentIntent(open)
            // Not marked silent, which would keep it from showing over the screen: the channel has no sound to make.
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_REMINDER)
            // Without this Android may hold a service's notification back for ten seconds, past the bell.
            .setForegroundServiceBehavior(NotificationCompat.FOREGROUND_SERVICE_IMMEDIATE)
            .setTimeoutAfter(TIMEOUT_MILLIS)
            .build()
    }

    companion object {
        private const val CHANNEL = "bell"
        private const val NOTIFICATION_ID = 2

        /** Longer than any one strike; the notice goes when the strike has faded, this only in case it does not. */
        private const val TIMEOUT_MILLIS = 30_000L

        /** Rings the bell in the foreground; false when Android does not allow it now. */
        fun start(context: Context): Boolean = runCatching {
            context.startForegroundService(Intent(context, BellRingService::class.java))
        }.isSuccess
    }
}
