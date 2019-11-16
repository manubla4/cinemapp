package com.manubla.cinemapp.data.repository.movies

import com.manubla.cinemapp.data.dao.MovieDao
import com.manubla.cinemapp.data.model.Movie
import com.manubla.cinemapp.data.service.response.MoviesPageResponse

class MoviesDataStoreImplDatabase(private val movieDao: MovieDao) : MoviesDataStore {
    private val pageRows = 20

    override suspend fun getMoviesPage(page: Int): MoviesPageResponse {
        val limit = (page - 1) * pageRows
        val results = movieDao.getAllWithLimit(limit, pageRows)
        return MoviesPageResponse(page, results, false)
    }

    suspend fun storeMovies(movies: List<Movie>) {
        movieDao.insertAll(movies)
    }

}