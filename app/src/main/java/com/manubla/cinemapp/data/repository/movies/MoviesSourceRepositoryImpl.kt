package com.manubla.cinemapp.data.repository.movies

import com.manubla.cinemapp.data.model.Movie
import com.manubla.cinemapp.data.service.response.MoviesPageResponse

class MoviesSourceRepositoryImpl(var factory: MoviesDataStoreFactory) : MoviesSourceRepository {

    override suspend fun getMoviesPage(page: Int): MoviesPageResponse {
        return factory.moviesDataStoreFactory.getMoviesPage(page)
    }

    override suspend fun storeMovies(movies: List<Movie>) {
        factory.moviesDataStoreDatabase.storeMovies(movies)
    }

}