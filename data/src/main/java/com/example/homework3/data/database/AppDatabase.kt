package com.example.homework3.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.homework3.data.model.FavoriteUserEntity
import com.example.homework3.data.model.UserEntity

@Database(entities = [FavoriteUserEntity::class, UserEntity::class], version = 5, exportSchema = false)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun githubDao(): GithubDao

    abstract fun githubCashedDao(): GithubCashedUsersDao
}