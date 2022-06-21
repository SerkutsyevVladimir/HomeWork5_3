package com.example.homework3.domain.repository

import com.example.homework3.domain.model.Country

interface CountryRemoteRepository {
    suspend fun getCountries(): Result<List<Country>>
}