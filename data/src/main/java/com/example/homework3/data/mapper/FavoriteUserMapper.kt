package com.example.homework3.data.mapper

import com.example.homework3.data.model.FavoriteUserEntity
import com.example.homework3.domain.model.FavoriteUser

fun FavoriteUserEntity.toDomainModel(): FavoriteUser{
    return FavoriteUser(
        id = id,
        username = githubUsername
    )
}