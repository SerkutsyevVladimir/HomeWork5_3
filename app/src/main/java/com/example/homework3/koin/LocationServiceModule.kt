package com.example.homework3.koin

import com.example.homework3.map.LocationService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val locationServiceModule = module {
    singleOf(::LocationService)
}