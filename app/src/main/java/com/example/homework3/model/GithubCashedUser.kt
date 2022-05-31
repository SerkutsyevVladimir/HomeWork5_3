package com.example.homework3.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GithubCashedUser(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "github_username")
    val githubUsername: String?,
    @ColumnInfo(name = "following")
    val following: Int,
    @ColumnInfo(name = "followers")
    val followers: Int,
    @ColumnInfo(name = "image_url")
    var image_url: String
)
