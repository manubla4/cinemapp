package com.manubla.cinemapp.data.repository.movies

import com.bumptech.glide.Glide
import com.manubla.cinemapp.data.model.Movie
import com.manubla.cinemapp.data.model.MovieGenre
import com.manubla.cinemapp.data.service.response.ImagesConfigurationResponse
import com.manubla.cinemapp.data.service.response.MoviesPageResponse

class MoviesSourceRepositoryImpl(var factory: MoviesDataStoreFactory) : MoviesSourceRepository {

    override suspend fun getLocalMovie(movieId: Int): Movie? {
        return factory.moviesDataStoreDatabase.getMovie(movieId)
    }

    override suspend fun getMoviesPage(page: Int): MoviesPageResponse {
        return factory.moviesDataStoreFactory.getMoviesPage(page)
    }

    override suspend fun storeMovies(movies: List<Movie>) {
        factory.moviesDataStoreDatabase.storeMovies(movies)
    }

    override suspend fun storeMovieGenres(movie: Movie) {
        movie.genreIds?.let {
            for (genreId in it) {
                val movieGenre = MovieGenre(movie.id, genreId)
                try {
                    factory.moviesDataStoreDatabase.storeMovieGenre(movieGenre)
                }
                catch (ignored: Exception) {
                }
            }
        }
    }

    override suspend fun getRemoteImage(posterPath: String?, imageConfig: ImagesConfigurationResponse): String {
        return factory.moviesDataStoreCloud.getRemoteImage(posterPath, imageConfig)
    }

    override suspend fun updateMovieImagePath(movieId: Int, path: String) {
        factory.moviesDataStoreDatabase.updateMovieImagePath(movieId, path)
    }

}