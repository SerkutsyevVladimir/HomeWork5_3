package com.example.homework3.data.repository

import com.example.homework3.data.api.GithubApi
import com.example.homework3.data.mapper.toDomainModel
import com.example.homework3.data.mapper.toDomainModels
import com.example.homework3.domain.model.Item
import com.example.homework3.domain.model.UserDetails
import com.example.homework3.domain.repository.UserRemoteRepository

internal class UserRemoteRepositoryImpl(private val githubApi: GithubApi):
    UserRemoteRepository
{
    override suspend fun getUsers(lastId: Int): Result<List<Item.User>> {
        return runCatching {
            githubApi.getUsers(lastId)
        }.map { it.toDomainModels() }
    }

    override suspend fun getUserDetails(username: String): Result<UserDetails> {
        return runCatching {
            githubApi.getUserDetails(username)
        }.map { it.toDomainModel() }
    }
}