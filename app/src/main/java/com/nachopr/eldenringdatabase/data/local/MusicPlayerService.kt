package com.nachopr.eldenringdatabase.data.local

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.nachopr.eldenringdatabase.R
import com.nachopr.eldenringdatabase.common.NOTIFICATION_CHANNEL_ID
import com.nachopr.eldenringdatabase.common.STARTFOREGROUND_ACTION
import com.nachopr.eldenringdatabase.common.STOPFOREGROUND_ACTION

class MusicPlayerService : Service() {

    private val NOTIFICATION_ID = 1

    private lateinit var mediaPlayer: MediaPlayer
    private var isPlaying = false

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(this, R.raw.main_theme_song)
        mediaPlayer.isLooping = true
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        if(intent.action == STARTFOREGROUND_ACTION) {
            startForeground(NOTIFICATION_ID, createNotification())
            playMusic()
            isPlaying = true
        } else if(intent.action == STOPFOREGROUND_ACTION) {
            stopMusic()
            isPlaying = false
            stopSelf()
        }
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        if(isPlaying) {
            stopMusic()
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    private fun createNotification(): Notification {
        val stopIntent = Intent(this, MusicPlayerService::class.java)
        stopIntent.action = STOPFOREGROUND_ACTION
        val pendingStopIntent = PendingIntent.getService(this, 0, stopIntent, 0)

        val builder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setContentTitle("Music Player")
            .setContentText("Playing Music")
            .setSmallIcon(R.drawable.ic_notifications_black_24dp)
            .addAction(R.drawable.ic_dashboard_black_24dp, "Stop", pendingStopIntent)

        return builder.build()
    }

    private fun playMusic() {
        if(!mediaPlayer.isPlaying) {
            mediaPlayer.start()
        }
    }

    private fun stopMusic() {
        if(mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            mediaPlayer.seekTo(0)
        }
    }
}