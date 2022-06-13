package com.example.homework3.domain.usecase

import com.example.homework3.domain.model.FavoriteUser
import com.example.homework3.domain.repository.UserLocalRepository

class DeleteFavoriteUserUseCase(
    private val userLocalRepository: UserLocalRepository
) {
    suspend operator fun invoke(user: FavoriteUser) {
        userLocalRepository.deleteFavoriteUser(user)
    }
}