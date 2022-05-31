package com.example.homework3.viewmodels

import androidx.lifecycle.ViewModel
import com.example.homework3.database.GithubCashedUsersDao
import com.example.homework3.retrofit.Item
import com.example.homework3.retrofit.UserRepository
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

class ListViewModel(
    private val userRepository: UserRepository,
    private val cashedUsersDao: GithubCashedUsersDao
) : ViewModel() {

    private val loadStateFlow = MutableSharedFlow<LoadState>(
        replay = 1, extraBufferCapacity = 0, onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    private var isLoading = false
    private var lastId = 0

    fun onLoadMore() {
        loadStateFlow.tryEmit(LoadState.LOAD_MORE)
    }

    fun onRefresh() {
        loadStateFlow.tryEmit(LoadState.REFRESH)
    }

    fun getData(): Flow<List<Item.GithubUser>> {
        return loadStateFlow
            .filter { !isLoading }
            .onEach {
                if (it == LoadState.REFRESH) {
                    lastId = 0
                }
                isLoading = true
            }
            .map {
                userRepository.getUsers(lastId)
            }
            .onEach {
                cashedUsersDao.insertAll(it)
                lastId = it.last().id.toInt()
                isLoading = false
            }
            .runningReduce { accumulator, value -> accumulator + value }
            .onStart {
                emit(cashedUsersDao.getAll())
            }
    }


}

enum class LoadState {
    LOAD_MORE, REFRESH
}