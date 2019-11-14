package com.manubla.cinemapp.data.repository

import com.manubla.cinemapp.data.model.MoviesPage
import com.manubla.cinemapp.data.repository.movies.MoviesDataStoreFactory

class MoviesSourceRepositoryImpl(var factory: MoviesDataStoreFactory) : MoviesSourceRepository {

    override suspend fun getMoviesPage(): MoviesPage {
        return factory.moviesDataStoreFactory.getMoviesPage()
    }

}