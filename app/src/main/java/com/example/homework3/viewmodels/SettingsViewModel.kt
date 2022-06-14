package com.example.homework3.viewmodels

import androidx.lifecycle.ViewModel
import com.example.homework3.domain.service.NightModeService

class SettingsViewModel(private val preferencesService: NightModeService) : ViewModel() {
    var selectedAppMode by preferencesService::nightMode
}