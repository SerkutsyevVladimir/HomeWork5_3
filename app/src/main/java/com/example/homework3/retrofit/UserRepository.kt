package com.example.homework3.retrofit

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class UserRepository (private val githubApi: GithubApi) {

    suspend fun getUsers(lastId: Int) = withContext(Dispatchers.IO){
       // delay(10000)
        githubApi.getUsers(lastId)
    }

    suspend fun getUserDetails (username: String) = withContext(Dispatchers.IO){
        githubApi.getUserDetails(username)
    }
}