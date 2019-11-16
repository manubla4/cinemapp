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
    @PrimaryKey var id: Int,
    var title: String,
    var overview: String,
    @Ignore @SerializedName("genre_ids") var genreIds: List<Int>?,
    @ColumnInfo(name = "favorite_date") var favoriteDate: ZonedDateTime?,
    @ColumnInfo(name = "vote_average") @SerializedName("vote_average") var voteAverage: Double,
    @ColumnInfo(name = "release_date") @SerializedName("release_date") var releaseDate: ZonedDateTime,
    @ColumnInfo(name = "poster_path") @SerializedName("poster_path") var posterPath: String?
): Parcelable {

    constructor(id: Int,
                overview: String,
                title: String,
                voteAverage: Double,
                releaseDate: ZonedDateTime,
                posterPath: String?)
            : this(id, title, overview, null, null,
                    voteAverage, releaseDate, posterPath)

    companion object {
        const val TABLE_NAME = "movies"
    }
}