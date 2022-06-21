package com.example.homework3.data.repository

import com.example.homework3.data.api.MapApi
import com.example.homework3.data.mapper.toDomainModule
import com.example.homework3.domain.model.Country
import com.example.homework3.domain.repository.CountryRemoteRepository

internal class CountryRemoteRepositoryImpl(private val mapApi: MapApi): CountryRemoteRepository {
    override suspend fun getCountries(): Result<List<Country>> {
        return runCatching {
            mapApi.getCountries()
        }.map { it.toDomainModule() }
    }
}