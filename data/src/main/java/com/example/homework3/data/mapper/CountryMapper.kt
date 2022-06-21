package com.example.homework3.data.mapper

import com.example.homework3.data.model.CountryDTO
import com.example.homework3.domain.model.Country


internal fun List<CountryDTO>.toDomainModule(): List<Country>{
    return map {
        it.toDomainModule()
    }
}

internal fun CountryDTO.toDomainModule(): Country{
       return Country(
           name = name.name,
           latitude = infoLatLng.latLng[0],
           longitude = infoLatLng.latLng[1]
       )

}