package com.manubla.cinemapp.data.repository.movies

import com.manubla.cinemapp.data.model.Movie
import com.manubla.cinemapp.data.service.response.PageResponse

interface MoviesSourceRepository {
    suspend fun getMoviesPage(page: Int): PageResponse
    suspend fun storeMovies(movies: List<Movie>)
}