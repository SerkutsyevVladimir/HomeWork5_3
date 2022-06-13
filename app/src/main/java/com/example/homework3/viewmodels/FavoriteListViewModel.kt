package com.example.homework3.viewmodels

import androidx.lifecycle.ViewModel
import com.example.homework3.domain.model.FavoriteUser
import com.example.homework3.domain.usecase.GetFavoriteUsersUseCase
import com.example.homework3.model.GithubFavoriteUser
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

class FavoriteListViewModel(
    private val getFavoriteUsersUseCase: GetFavoriteUsersUseCase
) : ViewModel() {
    private val loadStateFlow = MutableSharedFlow<GithubFavoriteUser>(
        replay = 1, extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST
    )


    fun getAll(): Flow<List<FavoriteUser>> {
        return loadStateFlow.map { getFavoriteUsersUseCase().getOrDefault(emptyList()) }
            .onStart {
                emit(getFavoriteUsersUseCase.invoke().getOrDefault(emptyList()))
            }
    }

}