package com.manubla.cinemapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.ZonedDateTime

@Entity
data class Movie(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "original_title") val originalTitle: String,
    @ColumnInfo(name = "vote_average") val voteAverage: Double,
    @ColumnInfo(name = "release_date") val releaseDate: ZonedDateTime,
    @ColumnInfo(name = "poster_path") val posterPath: String
)
