package com.example.homework3.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.homework3.model.GithubFavoriteUser

@Database(entities = [GithubFavoriteUser::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun githubDao(): GithubDao
}