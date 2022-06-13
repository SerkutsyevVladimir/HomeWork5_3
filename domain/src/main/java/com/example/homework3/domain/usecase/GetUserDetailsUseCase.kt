package com.example.homework3.domain.usecase

import com.example.homework3.domain.model.UserDetails
import com.example.homework3.domain.repository.UserRemoteRepository

class GetUserDetailsUseCase(private val userRepository: UserRemoteRepository) {
    suspend operator fun invoke(username: String): Result<UserDetails> {
        return userRepository.getUserDetails(username)
    }
}