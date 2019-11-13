package com.manubla.cinemapp.data.controller

import android.content.SharedPreferences
import com.manubla.cinemapp.data.service.AuthService
import com.manubla.cinemapp.data.service.MovieService
import com.manubla.cinemapp.data.service.request.LoginRequest
import com.manubla.cinemapp.presentation.view.accessTokenKey

class MovieController(
    private val movieService: MovieService
) {
    suspend fun getMovies(): Boolean {
        movieService.getMovies()
        return true
    }

}