package com.manubla.cinemapp.data.repository.movies

import com.manubla.cinemapp.data.model.Movie
import com.manubla.cinemapp.data.service.response.ImagesConfigurationResponse
import com.manubla.cinemapp.data.service.response.MoviesPageResponse

interface MoviesSourceRepository {
    suspend fun getMoviesPage(page: Int): MoviesPageResponse
    suspend fun getLocalMovie(movieId: Int): Movie?
    suspend fun getRemoteImage(posterPath: String?, imageConfig: ImagesConfigurationResponse): String
    suspend fun storeMovies(movies: List<Movie>)
    suspend fun storeMovieGenres(movie: Movie)
    suspend fun updateMovieImagePath(movieId: Int, path: String)
}