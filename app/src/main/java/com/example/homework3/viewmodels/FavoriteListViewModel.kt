package com.example.homework3.viewmodels

import androidx.lifecycle.ViewModel
import com.example.homework3.database.GithubDao
import com.example.homework3.model.GithubFavoriteUser
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

class FavoriteListViewModel(
    private val githubDao: GithubDao
) : ViewModel() {
    private val loadStateFlow = MutableSharedFlow<GithubFavoriteUser>(
        replay = 1, extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST
    )


    fun getAll(): Flow<List<GithubFavoriteUser>> {
        return loadStateFlow.map { githubDao.getAll() }
            .onStart {
                emit(githubDao.getAll())
            }
    }

}