package com.example.homework3.domain.usecase

import com.example.homework3.domain.model.FavoriteUser
import com.example.homework3.domain.repository.UserLocalRepository

class InsertFavoriteUserUseCase(
    private val userLocalRepository: UserLocalRepository
) {
    suspend operator fun invoke(username: FavoriteUser) {
        userLocalRepository.insertFavorite(username)
    }
}