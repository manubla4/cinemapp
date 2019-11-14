package com.manubla.cinemapp.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.manubla.cinemapp.data.dao.MovieDao
import com.manubla.cinemapp.data.dao.MoviesPageDao
import com.manubla.cinemapp.data.helper.converter.Converters
import com.manubla.cinemapp.data.model.Movie
import com.manubla.cinemapp.data.model.MoviesPage

@Database(entities = [MoviesPage::class, Movie::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun moviesPageDao(): MoviesPageDao
}