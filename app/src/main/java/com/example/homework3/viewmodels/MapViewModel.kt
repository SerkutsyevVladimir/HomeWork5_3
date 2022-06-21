package com.example.homework3.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework3.domain.model.Country
import com.example.homework3.domain.model.FavoriteUser
import com.example.homework3.domain.usecase.GetCountriesUseCase
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

class MapViewModel(
    private val getCountriesUseCase: GetCountriesUseCase
) : ViewModel() {
    private val loadStateFlow = MutableSharedFlow<Country>(
        replay = 1, extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val dataFlow = flow {
        emit(getCountriesUseCase())
    }.shareIn(
        scope = viewModelScope,
        replay = 1,
        started = SharingStarted.Eagerly
    )
}