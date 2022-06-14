package com.example.homework3.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.homework3.databinding.FragmentDetailsBinding
import com.example.homework3.databinding.FragmentSettingsBinding
import com.example.homework3.domain.model.settings.NightMode
import com.example.homework3.domain.service.NightModeService
import com.example.homework3.domain.usecase.GetUsersUseCase
import com.example.homework3.viewmodels.ListViewModel
import com.example.homework3.viewmodels.SettingsViewModel
import org.koin.android.ext.android.inject

class SettingsFragment: Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = requireNotNull(_binding) { "View was destroyed" }

    private val nightModeService by inject<NightModeService> ()

    private val viewModel by viewModels<SettingsViewModel> {
        object : ViewModelProvider.Factory {
            @Suppress
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return SettingsViewModel(
                    nightModeService
                ) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentSettingsBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            when (viewModel.selectedAppMode) {
                NightMode.LIGHT -> buttonLightMode
                NightMode.DARK -> buttonDarkMode
                NightMode.SYSTEM -> buttonSystemMode
            }.isChecked = true


            radioGroup.setOnCheckedChangeListener { _, checkedId ->
                val (preferencesMode, systemMode) = when(checkedId){
                    buttonLightMode.id -> NightMode.LIGHT to AppCompatDelegate.MODE_NIGHT_NO
                    buttonDarkMode.id -> NightMode.DARK to AppCompatDelegate.MODE_NIGHT_YES
                    buttonSystemMode.id -> NightMode.SYSTEM to AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                    else -> error("Unexpected button $checkedId")
                }

                viewModel.selectedAppMode = preferencesMode
                AppCompatDelegate.setDefaultNightMode(systemMode)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}