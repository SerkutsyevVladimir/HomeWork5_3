package com.example.homework3.domain.usecase

import com.example.homework3.domain.model.Item
import com.example.homework3.domain.repository.UserLocalRepository

class InsertUsersLocalUseCase(
    private val userLocalRepository: UserLocalRepository
) {
    suspend operator fun invoke(user: List<Item.User>) {
        userLocalRepository.insertAll(user)
    }
}