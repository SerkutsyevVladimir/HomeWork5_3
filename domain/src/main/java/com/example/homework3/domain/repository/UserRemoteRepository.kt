package com.example.homework3.domain.repository

import com.example.homework3.domain.model.Item
import com.example.homework3.domain.model.UserDetails

interface UserRemoteRepository {

    suspend fun getUsers(lastId: Int): Result<List<Item.User>>

    suspend fun getUserDetails(username: String): Result<UserDetails>

}