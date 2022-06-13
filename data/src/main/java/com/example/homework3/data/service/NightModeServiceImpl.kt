package com.example.homework3.data.service

import android.content.Context
import com.example.homework3.domain.model.settings.NightMode
import com.example.homework3.domain.service.NightModeService

internal class NightModeServiceImpl(context: Context) : NightModeService {

    private val sharedPrefs = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)


    override var nightMode: NightMode by enumPref(KEY_NIGHT_MODE, NightMode.SYSTEM)


    private inline fun <reified E : Enum<E>> enumPref(key: String, defaultValue: E) =
        PrefsDelegate(
            sharedPrefs,
            getValue = { getString(key, null)?.let(::enumValueOf) ?: defaultValue },
            setValue = { putString(key, it.name) }
        )


    companion object {
        private const val PREFS_NAME = "Github_Preferences"

        private const val KEY_NIGHT_MODE = "night_mode"
    }

}