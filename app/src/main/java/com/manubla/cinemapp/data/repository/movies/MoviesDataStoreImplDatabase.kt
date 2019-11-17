package com.manubla.cinemapp.data.repository.movies

import com.manubla.cinemapp.data.dao.MovieDao
import com.manubla.cinemapp.data.model.Genre
import com.manubla.cinemapp.data.model.Movie
import com.manubla.cinemapp.data.model.MovieGenre
import com.manubla.cinemapp.data.service.response.MoviesPageResponse

class MoviesDataStoreImplDatabase(private val movieDao: MovieDao) : MoviesDataStore {
    private val pageRows = 20

    override suspend fun getMoviesPage(page: Int): MoviesPageResponse {
        val limit = (page - 1) * pageRows
        val results = movieDao.getAllWithLimit(limit, pageRows)
        return MoviesPageResponse(page, results, false)
    }

    override suspend fun getMovie(movieId: Int): Movie? {
        return movieDao.get(movieId)
    }

    suspend fun getMovieGenres(movie: Movie): List<Genre> {
        return movieDao.getGenresForMovie(movie.id)
    }

    suspend fun storeMovies(movies: List<Movie>) {
        movieDao.insertAll(movies)
    }

    suspend fun storeMovieGenre(movieGenre: MovieGenre) {
        movieDao.insertMovieGenre(movieGenre)
    }

    suspend fun updateMovieImagePath(movieId: Int, path: String) {
        movieDao.updateMovieImagePath(movieId, path)
    }

}