package com.example.homework3.koin

import com.example.homework3.retrofit.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    single {
        UserRepository(get())
    }
}