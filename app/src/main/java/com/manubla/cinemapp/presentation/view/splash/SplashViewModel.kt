package com.manubla.cinemapp.presentation.view.splash

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manubla.cinemapp.data.model.Genre
import com.manubla.cinemapp.data.model.Movie
import com.manubla.cinemapp.data.repository.configuration.ConfigurationSourceRepository
import com.manubla.cinemapp.data.repository.genre.GenresSourceRepository
import com.manubla.cinemapp.data.repository.movies.MoviesSourceRepository
import com.manubla.cinemapp.data.service.response.ConfigurationResponse
import com.manubla.cinemapp.data.service.response.GenreResponse
import com.manubla.cinemapp.data.service.response.ImagesConfigurationResponse
import com.manubla.cinemapp.data.service.response.MoviesPageResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import kotlin.coroutines.CoroutineContext

class SplashViewModel(private val moviesRepository: MoviesSourceRepository,
                      private val genresRepository: GenresSourceRepository,
                      private val configRepository: ConfigurationSourceRepository) : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    val data: LiveData<List<Parcelable?>>
        get() = localData

    private val localData = MutableLiveData<List<Parcelable?>>()

    fun loadData() {
        launch(Dispatchers.IO) {
            val moviesPage = getMoviesPage()
            val genreResponse = getRemoteGenres()
            val configResponse = getRemoteConfiguration()

            if (moviesPage.fromCloud)
                storeMovies(moviesPage.results)

            genreResponse?.let {
                storeGenres(it.genres)
            }

            for (movie in moviesPage.results) {
                storeMovieGenres(movie)
                configResponse?.let {
                    getRemoteImage(movie, it.images)
                }
            }
            localData.postValue(listOf(moviesPage, configResponse))
        }
    }

    private suspend fun storeGenres(genres: List<Genre>) {
        try {
            genresRepository.storeGenres(genres)
        } catch (ignored: Exception) {
        }
    }

    private suspend fun storeMovieGenres(movie: Movie) {
        try {
            moviesRepository.storeMovieGenres(movie)
        } catch (ignored: Exception) {
        }
    }

    private suspend fun storeMovies(movies: List<Movie>) {
        try {
            moviesRepository.storeMovies(movies)
        } catch (ignored: Exception) {
        }
    }

    private suspend fun getMoviesPage(): MoviesPageResponse = try {
            moviesRepository.getMoviesPage(1)
        } catch (ignored: Exception) {
            MoviesPageResponse(1, listOf(), false)
        }

    private suspend fun getRemoteGenres(): GenreResponse? = try {
            genresRepository.getRemoteGenres()
        } catch (ignored: Exception) {
            null
        }

    private suspend fun getRemoteConfiguration(): ConfigurationResponse? = try {
        configRepository.getRemoteConfiguration()
    } catch (ignored: Exception) {
        null
    }

    private suspend fun getRemoteImage(movie: Movie, 
                                   imageConfig: ImagesConfigurationResponse) {
        try {
            val localMovie = moviesRepository.getLocalMovie(movie.id)
            if (localMovie != null && movie.posterPath != null) {
                if (!localMovie.posterLocalPath.isNullOrEmpty()) {
                    val file = File(localMovie.posterLocalPath)
                    if(file.exists())
                        file.delete()
                }
                val imageLocalPath = moviesRepository.getRemoteImage(movie.posterPath, imageConfig)
                movie.posterLocalPath = imageLocalPath
                moviesRepository.updateMovieImagePath(movie.id, imageLocalPath)
            }
        }
        catch (ignored: Exception) {}
    }

}