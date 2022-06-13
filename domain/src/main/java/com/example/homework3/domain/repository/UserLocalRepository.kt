package com.example.homework3.domain.repository

import com.example.homework3.domain.model.FavoriteUser
import com.example.homework3.domain.model.Item

interface UserLocalRepository {

    suspend fun getAll(): Result<List<Item.User>>

    suspend fun insertAll(user: List<Item.User>)

    suspend fun insertFavorite(username: FavoriteUser)

    suspend fun getAllFavorites() : Result<List<FavoriteUser>>

    suspend fun deleteFavoriteUser(user: FavoriteUser)
}