package com.manubla.cinemapp.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.ZonedDateTime

@Parcelize
@Entity(tableName = Movie.TABLE_NAME)
data class Movie (
    @PrimaryKey val id: Int,
    val overview: String,
    @Ignore @SerializedName("genre_ids") val genreIds: List<Int>?,
    @ColumnInfo(name = "favorite_date") val favoriteDate: ZonedDateTime?,
    @ColumnInfo(name = "original_title") @SerializedName("original_title") val originalTitle: String,
    @ColumnInfo(name = "vote_average") @SerializedName("vote_average") val voteAverage: Double,
    @ColumnInfo(name = "release_date") @SerializedName("release_date") val releaseDate: ZonedDateTime,
    @ColumnInfo(name = "poster_path") @SerializedName("poster_path") val posterPath: String?
): Parcelable {
    companion object {
        const val TABLE_NAME = "movies"
    }
}