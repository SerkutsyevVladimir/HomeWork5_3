package com.example.homework3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.example.homework3.domain.model.settings.NightMode
import com.example.homework3.domain.service.NightModeService
import com.example.homework3.fragments.ListFragment
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val nightModeService by inject<NightModeService>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppCompatDelegate.setDefaultNightMode(
            when (nightModeService.nightMode) {
                NightMode.LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
                NightMode.DARK -> AppCompatDelegate.MODE_NIGHT_YES
                NightMode.SYSTEM -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            }
        )
    }
}