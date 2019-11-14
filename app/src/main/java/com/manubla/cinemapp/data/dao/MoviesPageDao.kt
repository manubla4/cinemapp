package com.manubla.cinemapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.manubla.cinemapp.data.model.MoviesPage

@Dao
interface MoviesPageDao {

    @Query("SELECT * FROM moviespage")
    suspend fun getAll(): MoviesPage

    @Insert
    suspend fun insertAll(vararg moviesPages: MoviesPage)

    @Delete
    suspend fun delete(moviesPage: MoviesPage)

//    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
//    suspend fun loadAllByIds(userIds: IntArray): List<User>
//
//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    suspend fun findByName(first: String, last: String): User
}