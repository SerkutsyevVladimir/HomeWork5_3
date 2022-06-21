package com.example.homework3.viewmodels

import androidx.lifecycle.ViewModel
import com.example.homework3.domain.model.Item
import com.example.homework3.domain.usecase.GetUsersLocalUseCase
import com.example.homework3.domain.usecase.GetUsersUseCase
import com.example.homework3.domain.usecase.InsertUsersLocalUseCase
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

class ListViewModel(
    private val getUsersUseCase: GetUsersUseCase,
    private val insertUsersLocalUseCase: InsertUsersLocalUseCase,
    private val getUsersLocalUseCase: GetUsersLocalUseCase
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

    fun getData(): Flow<List<Item.User>> {
        return loadStateFlow
            .filter { !isLoading }
            .onEach {
                if (it == LoadState.REFRESH) {
                    lastId = 0
                }
                isLoading = true
            }
            .map {
                getUsersUseCase(lastId).getOrDefault(emptyList())
            }
            .onEach {
                insertUsersLocalUseCase(it)
                lastId = it.last().id.toInt()
                isLoading = false
            }
            .runningReduce { accumulator, value -> accumulator + value }
            .onStart {
                emit(getUsersLocalUseCase().getOrDefault(emptyList()))
            }
    }

}

enum class LoadState {
    LOAD_MORE, REFRESH
}