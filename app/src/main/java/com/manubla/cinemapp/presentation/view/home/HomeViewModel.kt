package com.manubla.cinemapp.presentation.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manubla.cinemapp.data.model.Movie
import com.manubla.cinemapp.data.repository.configuration.ConfigurationSourceRepository
import com.manubla.cinemapp.data.repository.movies.MoviesSourceRepository
import com.manubla.cinemapp.data.service.response.ConfigurationResponse
import com.manubla.cinemapp.data.service.response.ImagesConfigurationResponse
import com.manubla.cinemapp.data.service.response.MoviesPageResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import kotlin.coroutines.CoroutineContext

class HomeViewModel(private val moviesRepository: MoviesSourceRepository,
                      private val configRepository: ConfigurationSourceRepository) : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    val data: LiveData<List<Movie>>
        get() = localData

    private val localData = MutableLiveData<List<Movie>>()
    private var config: ConfigurationResponse? = null

    fun loadData(page: Int) {
        launch(Dispatchers.IO) {
            val moviesPage = getMoviesPage(page)

            if (moviesPage.fromCloud) {
                if (config == null)
                    config = getRemoteConfiguration()

                storeMovies(moviesPage.results)

                for (movie in moviesPage.results) {
                    storeMovieGenres(movie)
                    config?.let {
                        getRemoteImage(movie, it.images)
                    }
                }
            }
            localData.postValue(moviesPage.results)
        }
    }


    private suspend fun storeMovieGenres(movie: Movie) {
        try {
            moviesRepository.storeMovieGenres(movie)
        } catch (error: Exception) {
        }
    }

    private suspend fun storeMovies(movies: List<Movie>) {
        try {
            moviesRepository.storeMovies(movies)
        } catch (error: Exception) {
        }
    }

    private suspend fun getMoviesPage(page: Int): MoviesPageResponse = try {
        moviesRepository.getMoviesPage(page)
    } catch (error: Exception) {
        MoviesPageResponse(page, listOf(), false)
    }


    private suspend fun getRemoteConfiguration(): ConfigurationResponse? = try {
        configRepository.getRemoteConfiguration()
    } catch (error: Exception) {
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