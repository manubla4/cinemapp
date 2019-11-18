package com.manubla.cinemapp.presentation.view.home

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manubla.cinemapp.data.repository.configuration.ConfigurationSourceRepository
import com.manubla.cinemapp.data.repository.movies.MoviesSourceRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class HomeViewModel(private val moviesRepository: MoviesSourceRepository,
                      private val configRepository: ConfigurationSourceRepository) : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    val data: LiveData<List<Parcelable?>>
        get() = localData

    private val localData = MutableLiveData<List<Parcelable?>>()

//    fun loadData() {
//        launch(Dispatchers.IO) {
//            val moviesPage = getMoviesPage()
//            val genreResponse = getRemoteGenres()
//            val configResponse = getRemoteConfiguration()
//
//            if (moviesPage.fromCloud)
//                storeMovies(moviesPage.results)
//
//            genreResponse?.let {
//                storeGenres(it.genres)
//            }
//
//            for (movie in moviesPage.results) {
//                storeMovieGenres(movie)
//                configResponse?.let {
//                    getRemoteImage(movie, it.images)
//                }
//            }
//            localData.postValue(listOf(moviesPage, configResponse))
//        }
//    }
//
//    private suspend fun storeGenres(genres: List<Genre>) {
//        try {
//            genresRepository.storeGenres(genres)
//        } catch (error: Exception) {
//            Log.d("error","error")
//        }
//    }
//
//    private suspend fun storeMovieGenres(movie: Movie) {
//        try {
//            moviesRepository.storeMovieGenres(movie)
//        } catch (error: Exception) {
//            Log.d("error","error")
//        }
//    }
//
//    private suspend fun storeMovies(movies: List<Movie>) {
//        try {
//            moviesRepository.storeMovies(movies)
//        } catch (error: Exception) {
//            Log.d("error","error")
//        }
//    }
//
//    private suspend fun getMoviesPage(): MoviesPageResponse = try {
//        moviesRepository.getMoviesPage(1)
//    } catch (error: Exception) {
//        MoviesPageResponse(1, listOf(), false)
//    }
//
//    private suspend fun getRemoteGenres(): GenreResponse? = try {
//        genresRepository.getRemoteGenres()
//    } catch (error: Exception) {
//        null
//    }
//
//    private suspend fun getRemoteConfiguration(): ConfigurationResponse? = try {
//        configRepository.getRemoteConfiguration()
//    } catch (error: Exception) {
//        null
//    }
//
//    private suspend fun getRemoteImage(movie: Movie,
//                                       imageConfig: ImagesConfigurationResponse) {
//        try {
//            val localMovie = moviesRepository.getLocalMovie(movie.id)
//            if (localMovie != null && movie.posterPath != null) {
//                val imageLocalPath = moviesRepository.getRemoteImage(movie.posterPath, imageConfig)
//                if (!localMovie.posterLocalPath.isNullOrEmpty()) {
//                    val file = File(localMovie.posterLocalPath)
//                    if(file.exists())
//                        file.delete()
//                }
//                movie.posterLocalPath = imageLocalPath
//                moviesRepository.updateMovieImagePath(movie.id, imageLocalPath)
//            }
//        }
//        catch (ignored: Exception) {}
//    }

}