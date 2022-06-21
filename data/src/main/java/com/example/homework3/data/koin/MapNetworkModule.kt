package com.example.homework3.data.koin

import com.example.homework3.data.api.MapApi
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

internal val mapNetworkModule = module {
    single(qualifier("map")) {
        Retrofit.Builder()
            .baseUrl("https://restcountries.com/v3.1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>(qualifier("map")).create<MapApi>()
    }
}