package com.example.homework3.domain.usecase

import com.example.homework3.domain.model.User
import com.example.homework3.domain.model.UserDetails
import com.example.homework3.domain.repository.UserRepository

class GetUserDetailsUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(username: String): Result<UserDetails> {
        return userRepository.getUserDetails(username)
    }
}