package com.manubla.cinemapp.presentation.view.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manubla.cinemapp.data.repository.movies.MoviesSourceRepository
import com.manubla.cinemapp.data.service.response.MoviesPageResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SplashViewModel(private val repository: MoviesSourceRepository) : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    val moviesPage: LiveData<MoviesPageResponse>
        get() = localMoviesPage

    private val localMoviesPage = MutableLiveData<MoviesPageResponse>()

    fun fetchData() {
        launch(Dispatchers.IO) {
            try {
                val moviesPage = repository.getMoviesPage(1)
                repository.storeMovies(moviesPage.results)
                localMoviesPage.postValue(moviesPage)
            } catch (error: Exception) {
                val moviesPage = MoviesPageResponse(1, listOf(), false)
                localMoviesPage.postValue(moviesPage)
            }
        }
    }
}
