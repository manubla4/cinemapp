package com.manubla.cinemapp.presentation.view.splash

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manubla.cinemapp.data.repository.configuration.ConfigurationSourceRepository
import com.manubla.cinemapp.data.repository.movies.MoviesSourceRepository
import com.manubla.cinemapp.data.service.response.ConfigurationResponse
import com.manubla.cinemapp.data.service.response.MoviesPageResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SplashViewModel(private val movieRepository: MoviesSourceRepository,
                      private val configRepository: ConfigurationSourceRepository) : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    val data: LiveData<List<Parcelable?>>
        get() = localData

    private val localData = MutableLiveData<List<Parcelable?>>()

    fun loadData() {
        launch(Dispatchers.IO) {
            val moviesPage = fetchMovies()
            if (moviesPage.fromCloud) {
                movieRepository.storeMovies(moviesPage.results)
                val configuration = fetchConfiguration()
                localData.postValue(listOf(moviesPage, configuration))
            }
            else
                localData.postValue(listOf(moviesPage))
        }
    }

    private suspend fun fetchMovies(): MoviesPageResponse = try {
            movieRepository.getMoviesPage(1)
        } catch (error: Exception) {
            MoviesPageResponse(1, listOf(), false)
        }

    private suspend fun fetchConfiguration(): ConfigurationResponse? = try {
            configRepository.getConfiguration()
        } catch (error: Exception) {
            null
        }

}
