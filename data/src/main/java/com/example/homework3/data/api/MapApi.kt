package com.example.homework3.data.api

import com.example.homework3.data.model.CountryDTO
import retrofit2.http.GET

internal interface MapApi {
    @GET("all")
    suspend fun getCountries(): List<CountryDTO>
}