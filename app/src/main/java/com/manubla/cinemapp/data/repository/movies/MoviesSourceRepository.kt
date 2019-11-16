package com.manubla.cinemapp.data.repository.movies

import com.manubla.cinemapp.data.model.Movie
import com.manubla.cinemapp.data.service.response.MoviesPageResponse

interface MoviesSourceRepository {
    suspend fun getMoviesPage(page: Int): MoviesPageResponse
    suspend fun storeMovies(movies: List<Movie>)
}