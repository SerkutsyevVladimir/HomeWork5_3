package com.example.homework3.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {
    @GET("users")
    fun getUsers(
        @Query("since") since: Int
    ): Call<List<Item.GithubUser>>

    @GET("users/{username}")
    fun getUserDetails(
        @Path("username") username : String
    ): Call<GithubUserDetails>
}