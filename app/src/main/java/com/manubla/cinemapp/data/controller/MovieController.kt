package com.manubla.cinemapp.data.controller

import com.manubla.cinemapp.data.service.MovieService

class MovieController(
    private val movieService: MovieService
) {
    suspend fun getMovies(): Boolean {
        movieService.getMovies()
        return true
    }

}