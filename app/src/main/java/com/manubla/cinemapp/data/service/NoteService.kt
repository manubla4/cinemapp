package com.manubla.cinemapp.data.service

import com.manubla.cinemapp.data.model.MoviesPage
import com.manubla.cinemapp.data.model.Note
import retrofit2.http.GET

interface MovieService {

    @GET("notes")
    suspend fun getMovies(): MoviesPage
}
