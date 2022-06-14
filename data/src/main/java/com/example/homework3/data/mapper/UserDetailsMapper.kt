package com.example.homework3.data.mapper

import com.example.homework3.data.model.UserDetailsDTO
import com.example.homework3.domain.model.UserDetails

internal fun UserDetailsDTO.toDomainModel(): UserDetails {
    return UserDetails(
        avatarUrl = avatarUrl,
        followers = followers,
        following = following
    )
}