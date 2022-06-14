package com.example.homework3.data.koin

import org.koin.dsl.module

val dataModule = module {
    includes(
        databaseModule,
        networkModule,
        repositoryModule,
        useCaseModule,
        serviceModule
    )
}