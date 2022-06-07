package com.example.homework3.data.koin

import com.example.homework3.data.repository.UserRepositoryImpl
import com.example.homework3.domain.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<UserRepository> {
        UserRepositoryImpl(get())
    }
}