package com.example.homework3.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.homework3.model.GithubFavoriteUser
import com.example.homework3.retrofit.Item

@Database(entities = [GithubFavoriteUser::class, Item.GithubUser::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun githubDao(): GithubDao

    abstract fun githubCashedDao(): GithubCashedUsersDao
}