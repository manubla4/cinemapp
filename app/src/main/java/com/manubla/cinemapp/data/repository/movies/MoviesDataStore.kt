package com.manubla.cinemapp.data.repository.movies

import com.manubla.cinemapp.data.service.response.MoviesPageResponse

interface MoviesDataStore {
    suspend fun getMoviesPage(page: Int): MoviesPageResponse
}