package com.manubla.cinemapp.data.repository.reviews

import com.manubla.cinemapp.data.model.Movie
import com.manubla.cinemapp.data.service.response.PageResponse

interface ReviewsSourceRepository {
    suspend fun getMoviesPage(page: Int): PageResponse
    suspend fun storeMovies(movies: List<Movie>)
}