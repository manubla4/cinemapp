package com.manubla.cinemapp.data.repository

import com.manubla.cinemapp.data.model.MoviesPage

interface MoviesSourceRepository {

    suspend fun getMoviesPage(): MoviesPage
}