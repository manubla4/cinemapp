package com.manubla.cinemapp.data.model

import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity
data class MoviesPage(
    @PrimaryKey val page: Int,
    @ColumnInfo(name = "total_results") @SerializedName("total_results") val totalResults: Int,
    @ColumnInfo(name = "total_pages") @SerializedName("total_pages")  val totalPages: Int,
    @Relation(parentColumn = "id", entityColumn = "userId") val results: List<Movie>
)
