package com.example.homework3.data.mapper

import com.example.homework3.data.model.UserDTO
import com.example.homework3.data.model.UserEntity
import com.example.homework3.domain.model.Item

internal fun List<UserDTO>.toDomainModels():List<Item.User>{
    return map { it.toDomainModel() }
}

internal fun UserDTO.toDomainModel() : Item.User {
    return Item.User (
        id = id,
        login = login,
        avatarUrl = avatarUrl
            )
}

//fun List<UserEntity>.toDomainModels():List<User>{
//    return map { it.toDomainModel() }
//}

internal fun UserEntity.toDomainModel(): Item.User {
    return Item.User (
        id = id,
        login = login,
        avatarUrl = avatarUrl
    )
}

internal fun Item.User.toUserEntity() : UserEntity {
    return UserEntity(
        id = id,
        login = login,
        avatarUrl = avatarUrl
    )
}