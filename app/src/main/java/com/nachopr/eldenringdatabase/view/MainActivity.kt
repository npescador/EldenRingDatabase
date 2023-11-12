package com.nachopr.eldenringdatabase.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nachopr.eldenringdatabase.R
import com.nachopr.eldenringdatabase.common.STARTFOREGROUND_ACTION
import com.nachopr.eldenringdatabase.common.STOPFOREGROUND_ACTION
import com.nachopr.eldenringdatabase.data.local.MusicPlayerService
import com.nachopr.eldenringdatabase.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        startMusicService()

        val navView: BottomNavigationView = binding.navView
        navView.itemIconTintList = null;
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_talismans,
                R.id.navigation_weapons
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.selectedItemId = R.id.navigation_home


        supportActionBar?.hide()
    }

    override fun onResume() {
        startMusicService()
        super.onResume()
    }

    override fun onPause() {
        stopMusicService()
        super.onPause()
    }

    fun stopMusicService() {
        val serviceIntent = Intent(this, MusicPlayerService::class.java)
        serviceIntent.action = STOPFOREGROUND_ACTION
        startService(serviceIntent)
    }

    fun startMusicService() {
        val serviceIntent = Intent(this, MusicPlayerService::class.java)
        serviceIntent.action = STARTFOREGROUND_ACTION
        startService(serviceIntent)
    }

    override fun onStop() {
        stopMusicService()
        super.onStop()
    }

    override fun onDestroy() {
        stopMusicService()
        super.onDestroy()
    }
}