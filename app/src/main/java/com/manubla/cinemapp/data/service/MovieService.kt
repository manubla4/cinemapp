package com.manubla.cinemapp.data.service

import com.manubla.cinemapp.data.service.response.MoviesPageResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("discover/movie")
    suspend fun getMoviesPage(@Query("sort_by") sortBy: String,
                              @Query("include_adult") includeAdult: Boolean,
                              @Query("include_video") includeVideo: Boolean,
                              @Query("page") page: Int): MoviesPageResponse
}
