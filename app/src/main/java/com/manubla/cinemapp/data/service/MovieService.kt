package com.manubla.cinemapp.data.service

import com.manubla.cinemapp.data.model.MoviesPage
import retrofit2.http.GET

interface MovieService {

    @GET("notes") //TODO
    suspend fun getMoviesPage(page: Int): MoviesPage
}
