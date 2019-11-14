package com.manubla.cinemapp.data.repository.movies

import com.manubla.cinemapp.data.model.MoviesPage
import com.manubla.cinemapp.data.service.MovieService

class MoviesDataStoreImplCloud(private var movieService: MovieService) : MoviesDataStore {

    override suspend fun getMoviesPage(page: Int): MoviesPage {
        return movieService.getMoviesPage(page)
        //TODO here we store results on the database
    }

}