package com.example.homework3.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity (
    @PrimaryKey
    val id: Long,
    val login: String,
    @ColumnInfo
    val avatarUrl: String
        )