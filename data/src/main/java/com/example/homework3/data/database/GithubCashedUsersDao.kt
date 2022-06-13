package com.example.homework3.data.database

import androidx.room.*
import com.example.homework3.data.model.UserEntity


@Dao
internal interface GithubCashedUsersDao {
    @Query("SELECT * FROM userentity")
    suspend fun getAll(): List<UserEntity>

//    @Query("SELECT * FROM userentity WHERE id IN (:usersIds)")
//    suspend fun loadAllByIds(usersIds: IntArray): List<UserEntity>
//
//    @Query("SELECT * FROM userentity WHERE login LIKE :userName LIMIT 1")
//    suspend fun findByName(userName: String): UserEntity
//
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(user: List<UserEntity>)
//
//    @Delete
//    suspend fun delete(user: UserEntity)
}