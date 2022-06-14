package com.example.homework3.domain.model

sealed class Item {

    data class User(
        val id: Long,
        val login: String,
        val avatarUrl: String
    ) : Item()

    object Loading : Item()
}