package com.example.homework3.data.database

import androidx.room.*
import com.example.homework3.data.model.UserEntity


@Dao
internal interface GithubCashedUsersDao {
    @Query("SELECT * FROM userentity")
    suspend fun getAll(): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(user: List<UserEntity>)
}