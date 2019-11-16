package com.manubla.cinemapp.data.dao

import androidx.room.*
import com.manubla.cinemapp.data.model.Review

@Dao
interface ReviewDao {

    @Query("SELECT * FROM ${Review.TABLE_NAME}")
    suspend fun getAll(): List<Review>

    @Query("SELECT * FROM ${Review.TABLE_NAME} WHERE movie_id=:movieId")
    suspend fun getAllForMovie(movieId: Int): List<Review>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(reviews: List<Review>)

    @Delete
    suspend fun delete(review: Review)

}