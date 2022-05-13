package com.example.homework3.database

import androidx.room.*
import com.example.homework3.model.GithubFavoriteUser

@Dao
interface GithubDao {
    @Query("SELECT * FROM githubfavoriteuser")
    //suspend fun getAll(): List<GithubFavoriteUser>
    fun getAll(): List<GithubFavoriteUser>

    @Query("SELECT * FROM githubfavoriteuser WHERE id IN (:countriesIds)")
    //fun loadAllByIds(countriesIds: IntArray): List<GithubFavoriteUser>
    fun loadAllByIds(countriesIds: IntArray): List<GithubFavoriteUser>

    @Query("SELECT * FROM githubfavoriteuser WHERE github_username LIKE :countryName LIMIT 1")
    //fun findByName(countryName: String): GithubFavoriteUser
    fun findByName(countryName: String): GithubFavoriteUser

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    //fun insertAll(country: GithubFavoriteUser)
    fun insertAll(country: GithubFavoriteUser)

    @Delete
    //suspend fun delete(country: GithubFavoriteUser)
    fun delete(country: GithubFavoriteUser)
}