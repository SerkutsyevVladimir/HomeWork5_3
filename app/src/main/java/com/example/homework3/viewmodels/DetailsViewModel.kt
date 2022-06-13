package com.example.homework3.viewmodels

import androidx.lifecycle.ViewModel
import com.example.homework3.domain.model.FavoriteUser
import com.example.homework3.domain.model.UserDetails
import com.example.homework3.domain.usecase.GetUserDetailsUseCase
import com.example.homework3.domain.usecase.InsertFavoriteUserUseCase
import com.example.homework3.model.GithubFavoriteUser
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.*

class DetailsViewModel(
   // private val username: String,
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
    private val insertFavoriteUserUseCase: InsertFavoriteUserUseCase
) : ViewModel() {

    private val scope = CoroutineScope(Dispatchers.IO)

    fun getUserDetails(username: String) : Flow<UserDetails> = flow {
        emit(getUserDetailsUseCase(username).getOrThrow()) }

    fun insertAll (username: FavoriteUser) {
        scope.launch { insertFavoriteUserUseCase(username)}
    }

//    private val loadDetails = flow {
//        emit(userRepository.getUserDetails(username))
//    }
//
//    val getDetails = loadDetails.shareIn(
//        scope = viewModelScope,
//        started = SharingStarted.Eagerly,
//        replay = 1
//    )

}