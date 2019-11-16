package com.manubla.cinemapp.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = Review.TABLE_NAME,
        foreignKeys = [ForeignKey(entity = Movie::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("movie_id"),
            onDelete = CASCADE)]
)
data class Review(
    @PrimaryKey val id: String,
    val content: String,
    val author: String,
    @ColumnInfo(name = "movie_id") val movieId: Int
): Parcelable {
    companion object {
        const val TABLE_NAME = "reviews"
    }
}