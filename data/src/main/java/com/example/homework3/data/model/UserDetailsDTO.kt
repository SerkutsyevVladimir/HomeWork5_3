package com.example.homework3.data.model

import com.google.gson.annotations.SerializedName

data class UserDetailsDTO (
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val followers: Int,
    val following: Int
        )