package com.manubla.cinemapp.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = Genre.TABLE_NAME)
data class Genre(
    @PrimaryKey val id: Int,
    val name: String
): Parcelable {
    companion object {
        const val TABLE_NAME = "genres"
    }
}