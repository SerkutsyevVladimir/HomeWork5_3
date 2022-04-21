package com.example.homework3.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitService  {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val githubApi = retrofit.create<GithubApi>()

    fun provideGithubApi(): GithubApi{
        return githubApi
    }

}