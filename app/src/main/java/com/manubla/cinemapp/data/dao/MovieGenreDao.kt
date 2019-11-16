package com.manubla.cinemapp.data.dao

import androidx.room.*
import com.manubla.cinemapp.data.model.Genre
import com.manubla.cinemapp.data.model.Movie
import com.manubla.cinemapp.data.model.MovieGenre

@Dao
interface MovieGenreDao {

    @Query("SELECT * FROM ${Genre.TABLE_NAME} INNER JOIN ${MovieGenre.TABLE_NAME} ON " +
            "${Genre.TABLE_NAME}.id=${MovieGenre.TABLE_NAME}.genre_id WHERE ${MovieGenre.TABLE_NAME}.movie_id=:movieId")
    suspend fun getGenresForMovie(movieId: Int): List<Genre>

    @Query("SELECT * FROM ${Movie.TABLE_NAME} INNER JOIN ${MovieGenre.TABLE_NAME} ON " +
            "${Movie.TABLE_NAME}.id=${MovieGenre.TABLE_NAME}.movie_id WHERE ${MovieGenre.TABLE_NAME}.genre_id=:genreId")
    suspend fun getMoviesForGenre(genreId: Int): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movieGenres: List<MovieGenre>)

    @Delete
    suspend fun delete(movieGenre: MovieGenre)
}