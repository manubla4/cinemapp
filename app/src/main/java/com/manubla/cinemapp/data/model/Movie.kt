package com.manubla.cinemapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.threeten.bp.ZonedDateTime

@Entity
data class Movie(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "original_title") @SerializedName("original_title") val originalTitle: String,
    @ColumnInfo(name = "vote_average") @SerializedName("vote_average") val voteAverage: Double,
    @ColumnInfo(name = "release_date") @SerializedName("release_date") val releaseDate: ZonedDateTime,
    @ColumnInfo(name = "poster_path") @SerializedName("poster_path") val posterPath: String
)