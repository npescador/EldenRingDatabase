package com.nachopr.eldenringdatabase

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.nachopr.eldenringdatabase.di.baseModule
import com.nachopr.eldenringdatabase.di.talismanModule
import com.nachopr.eldenringdatabase.common.NOTIFICATION_CHANNEL_ID
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class EldenRingApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@EldenRingApplication)
            modules(listOf(baseModule, talismanModule)).allowOverride(true)
        }

        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                "Music Player1",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(
                NotificationManager::class.java
            )
            manager.createNotificationChannel(channel)
        }
    }
}