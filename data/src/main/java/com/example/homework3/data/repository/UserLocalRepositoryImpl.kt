package com.example.homework3.data.repository

import com.example.homework3.data.database.GithubCashedUsersDao
import com.example.homework3.data.database.GithubDao
import com.example.homework3.data.mapper.toDomainModel
import com.example.homework3.data.mapper.toFavoriteUserEntity
import com.example.homework3.data.mapper.toUserEntity
import com.example.homework3.domain.model.FavoriteUser
import com.example.homework3.domain.model.Item
import com.example.homework3.domain.repository.UserLocalRepository
import com.example.homework3.domain.repository.UserRemoteRepository
import kotlinx.coroutines.launch

internal class UserLocalRepositoryImpl(
    private val cashedUsersDao: GithubCashedUsersDao,
    private val githubDao: GithubDao
) : UserLocalRepository {

    override suspend fun getAll(): Result<List<Item.User>> {
        return runCatching {
            cashedUsersDao.getAll()
        }.map { userEntities -> userEntities.map { it.toDomainModel() } }
    }

    override suspend fun insertAll(user: List<Item.User>) {
        cashedUsersDao.insertAll(user.map { it.toUserEntity() })
    }

    override suspend fun insertFavorite(username: FavoriteUser) {
        githubDao.insertAll(username.toFavoriteUserEntity())
    }

    override suspend fun getAllFavorites(): Result<List<FavoriteUser>> {
        return runCatching {
            githubDao.getAll()
        }.map { favoriteUserEntities -> favoriteUserEntities.map { it.toDomainModel() } }
    }

    override suspend fun deleteFavoriteUser(user: FavoriteUser) {
        githubDao.delete(user.toFavoriteUserEntity())
    }
}