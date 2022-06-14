package com.example.homework3.data.api

import com.example.homework3.data.model.UserDTO
import com.example.homework3.data.model.UserDetailsDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface GithubApi {
    @GET("users")
    suspend fun getUsers(
        @Query("since") since: Int
    ): List<UserDTO>

    @GET("users/{username}")
    suspend fun getUserDetails(
        @Path("username") username: String
    ): UserDetailsDTO
}