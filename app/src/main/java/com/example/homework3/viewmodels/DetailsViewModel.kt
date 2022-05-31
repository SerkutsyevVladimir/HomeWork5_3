package com.example.homework3.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework3.database.GithubCashedUsersDao
import com.example.homework3.database.GithubDao
import com.example.homework3.model.GithubFavoriteUser
import com.example.homework3.retrofit.GithubUserDetails
import com.example.homework3.retrofit.UserRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import java.util.*

class DetailsViewModel(
   // private val username: String,
    private val userRepository: UserRepository,
    private val githubDao: GithubDao
) : ViewModel() {

    private val scope = CoroutineScope(Dispatchers.IO)

    fun getUserDetails(username: String) : Flow<GithubUserDetails> = flow {
        emit(userRepository.getUserDetails(username)) }

    fun insertAll (username: GithubFavoriteUser) {
        scope.launch { githubDao.insertAll(username)}
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