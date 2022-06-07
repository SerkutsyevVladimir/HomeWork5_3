package com.example.homework3.data.database

import androidx.room.*
import com.example.homework3.data.model.FavoriteUserEntity


@Dao
interface GithubDao {
    @Query("SELECT * FROM favoriteuserentity")
    suspend fun getAll(): List<FavoriteUserEntity>

    @Query("SELECT * FROM favoriteuserentity WHERE id IN (:countriesIds)")
    suspend fun loadAllByIds(countriesIds: IntArray): List<FavoriteUserEntity>

    @Query("SELECT * FROM favoriteuserentity WHERE github_username LIKE :countryName LIMIT 1")
    suspend fun findByName(countryName: String): FavoriteUserEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(country: FavoriteUserEntity)

    @Delete
    suspend fun delete(country: FavoriteUserEntity)
}