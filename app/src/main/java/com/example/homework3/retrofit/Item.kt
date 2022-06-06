package com.example.homework3.retrofit

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

sealed class Item {

    @Entity
    data class GithubUser (
        @PrimaryKey
        val id: Long,
        val login: String,
        @ColumnInfo
        @SerializedName("avatar_url")
        val avatarUrl: String
    ) : Item()

    object Loading : Item()
}