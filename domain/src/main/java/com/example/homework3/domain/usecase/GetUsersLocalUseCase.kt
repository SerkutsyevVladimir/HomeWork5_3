package com.example.homework3.domain.usecase

import com.example.homework3.domain.model.Item
import com.example.homework3.domain.repository.UserLocalRepository

class GetUsersLocalUseCase(
    private val userLocalRepository: UserLocalRepository
) {
    suspend operator fun invoke(): Result<List<Item.User>> {
        return userLocalRepository.getAll()
    }
}