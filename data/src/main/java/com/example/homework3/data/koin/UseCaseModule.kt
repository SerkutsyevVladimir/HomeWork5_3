package com.example.homework3.data.koin

import com.example.homework3.domain.usecase.*
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModule = module {
    factoryOf(::GetUsersUseCase)
    factoryOf(::GetUsersLocalUseCase)
    factoryOf(::InsertUsersLocalUseCase)
    factoryOf(::GetUserDetailsUseCase)
    factoryOf(::GetFavoriteUsersUseCase)
    factoryOf(::InsertFavoriteUserUseCase)
    factoryOf(::DeleteFavoriteUserUseCase)
}