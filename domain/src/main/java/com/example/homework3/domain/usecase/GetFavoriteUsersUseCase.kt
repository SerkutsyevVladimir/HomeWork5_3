package com.example.homework3.domain.usecase

import com.example.homework3.domain.model.FavoriteUser
import com.example.homework3.domain.repository.UserLocalRepository

class GetFavoriteUsersUseCase(
    private val userLocalRepository: UserLocalRepository
) {
    suspend operator fun invoke(): Result<List<FavoriteUser>> {
        return userLocalRepository.getAllFavorites()
    }
}