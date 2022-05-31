package com.example.homework3.database

import androidx.room.*
import com.example.homework3.model.GithubCashedUser
import com.example.homework3.retrofit.Item

@Dao
interface GithubCashedUsersDao {
    @Query("SELECT * FROM githubuser")
    suspend fun getAll(): List<Item.GithubUser>

    @Query("SELECT * FROM githubuser WHERE id IN (:usersIds)")
    suspend fun loadAllByIds(usersIds: IntArray): List<Item.GithubUser>

    @Query("SELECT * FROM githubuser WHERE login LIKE :userName LIMIT 1")
    suspend fun findByName(userName: String): Item.GithubUser

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(user: List<Item.GithubUser>)

    @Delete
    suspend fun delete(user: Item.GithubUser)
}