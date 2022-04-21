package com.example.homework3.retrofit

import com.google.gson.annotations.SerializedName

data class GithubUserDetails (
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val followers: Int,
    val following: Int
)