package com.manubla.cinemapp.data.dao

import androidx.room.*
import com.manubla.cinemapp.data.model.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM ${Movie.TABLE_NAME}")
    suspend fun getAll(): List<Movie>

    @Query("SELECT * FROM ${Movie.TABLE_NAME} LIMIT :limit, :rows")
    suspend fun getAllWithLimit(limit: Int, rows: Int): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<Movie>)

    @Delete
    suspend fun delete(movie: Movie)

}