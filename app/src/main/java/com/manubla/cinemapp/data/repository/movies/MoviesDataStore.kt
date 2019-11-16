package com.manubla.cinemapp.data.repository.movies

import com.manubla.cinemapp.data.service.response.PageResponse

interface MoviesDataStore {
    suspend fun getMoviesPage(page: Int): PageResponse
}