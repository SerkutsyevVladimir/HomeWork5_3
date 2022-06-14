package com.example.homework3.data.mapper

import com.example.homework3.data.model.FavoriteUserEntity
import com.example.homework3.data.model.UserDTO
import com.example.homework3.domain.model.FavoriteUser
import com.example.homework3.domain.model.Item

internal fun FavoriteUserEntity.toDomainModel(): FavoriteUser {
    return FavoriteUser(
        id = id,
        username = githubUsername
    )
}

internal fun FavoriteUser.toFavoriteUserEntity(): FavoriteUserEntity {
    return FavoriteUserEntity(
        id = id,
        githubUsername = username
    )
}

internal fun List<FavoriteUserEntity>.toDomainModels(): List<FavoriteUser> {
    return map { it.toDomainModel() }
}