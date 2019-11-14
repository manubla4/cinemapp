package com.manubla.cinemapp.data.repository.movies

import com.manubla.cinemapp.data.model.MoviesPage

interface MoviesDataStore {

    suspend fun getMoviesPage(): MoviesPage
}