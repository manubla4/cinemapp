package com.manubla.cinemapp.data.service.response

import android.os.Parcelable
import com.manubla.cinemapp.data.model.Movie
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PageResponse (
    val page: Int,
    val results: List<Movie>,
    val fromCloud: Boolean = true
): Parcelable