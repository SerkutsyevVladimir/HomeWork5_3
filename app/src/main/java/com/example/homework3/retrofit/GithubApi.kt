package com.example.homework3.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {
    @GET("users")
    suspend fun getUsers(
        @Query("since") since: Int
    ): List<Item.GithubUser>

    @GET("users/{username}")
    suspend fun getUserDetails(
        @Path("username") username : String
    ): GithubUserDetails
}