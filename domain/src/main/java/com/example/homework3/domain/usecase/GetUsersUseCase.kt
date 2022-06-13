package com.example.homework3.domain.usecase

import com.example.homework3.domain.model.Item
import com.example.homework3.domain.repository.UserLocalRepository
import com.example.homework3.domain.repository.UserRemoteRepository

class GetUsersUseCase(
    private val userRemoteRepository: UserRemoteRepository
    ) {
        suspend operator fun invoke(lastId: Int): Result<List<Item.User>> {
        return userRemoteRepository.getUsers(lastId)
    }
}