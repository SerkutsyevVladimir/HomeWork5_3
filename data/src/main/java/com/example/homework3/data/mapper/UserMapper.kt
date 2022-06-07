package com.example.homework3.data.mapper

import com.example.homework3.data.model.UserDTO
import com.example.homework3.data.model.UserEntity
import com.example.homework3.domain.model.User

fun List<UserDTO>.toDomainModels():List<User>{
    return map { it.toDomainModel() }
}

fun UserDTO.toDomainModel() : User {
    return User (
        id = id,
        login = login,
        avatarUrl = avatarUrl
            )
}

//fun List<UserEntity>.toDomainModels():List<User>{
//    return map { it.toDomainModel() }
//}

fun UserEntity.toDomainModel(): User {
    return User (
        id = id,
        login = login,
        avatarUrl = avatarUrl
    )
}