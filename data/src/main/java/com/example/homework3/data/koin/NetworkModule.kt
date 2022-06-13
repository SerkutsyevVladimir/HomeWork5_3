package com.example.homework3.data.koin

import com.example.homework3.data.api.GithubApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

internal val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    single {
        get<Retrofit>().create<GithubApi>()
    }
}