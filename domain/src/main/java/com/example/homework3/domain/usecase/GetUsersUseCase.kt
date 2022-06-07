package com.example.homework3.domain.usecase

import com.example.homework3.domain.model.User
import com.example.homework3.domain.repository.UserRepository

class GetUsersUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(lastId: Int): Result<List<User>> {
        return userRepository.getUsers(lastId)
    }
}