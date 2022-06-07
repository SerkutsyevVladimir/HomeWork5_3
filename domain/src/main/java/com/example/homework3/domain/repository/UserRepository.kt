package com.example.homework3.domain.repository

import com.example.homework3.domain.model.User
import com.example.homework3.domain.model.UserDetails

interface UserRepository {

    suspend fun getUsers(lastId: Int): Result<List<User>>

    suspend fun getUserDetails(username: String): Result<UserDetails>

}