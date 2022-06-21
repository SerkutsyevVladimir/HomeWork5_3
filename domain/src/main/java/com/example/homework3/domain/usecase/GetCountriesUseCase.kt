package com.example.homework3.domain.usecase

import com.example.homework3.domain.model.Country
import com.example.homework3.domain.repository.CountryRemoteRepository

class GetCountriesUseCase(
    private val countryRemoteRepository: CountryRemoteRepository
) {
    suspend operator fun invoke(): Result<List<Country>> {
        return countryRemoteRepository.getCountries()
    }
}