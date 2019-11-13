package com.manubla.cinemapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MoviesPage(
    @PrimaryKey val page: Int,
    @ColumnInfo(name = "total_results") val totalResults: Int,
    @ColumnInfo(name = "total_pages") val totalPages: Int,
    @Embedded val results: List<Movie>
)
