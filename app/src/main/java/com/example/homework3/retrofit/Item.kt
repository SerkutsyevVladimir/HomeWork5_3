package com.example.homework3.retrofit


import com.google.gson.annotations.SerializedName

sealed class Item {

    data class GithubUser (
        val id: Long,
        val login: String,
        @SerializedName("avatar_url")
        val avatarUrl: String
    ) : Item()

    object Loading : Item()
}