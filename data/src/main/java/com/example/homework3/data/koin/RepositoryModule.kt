package com.example.homework3.data.koin

import com.example.homework3.data.repository.UserLocalRepositoryImpl
import com.example.homework3.data.repository.UserRemoteRepositoryImpl
import com.example.homework3.domain.repository.UserLocalRepository
import com.example.homework3.domain.repository.UserRemoteRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val repositoryModule = module {
    singleOf(::UserRemoteRepositoryImpl) {
        bind<UserRemoteRepository>()
        named("remote")
    }
    singleOf(::UserLocalRepositoryImpl) {
        bind<UserLocalRepository>()
        named("local")
    }
}