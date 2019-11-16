package com.manubla.cinemapp.presentation.view.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manubla.cinemapp.data.repository.movies.MoviesSourceRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SplashViewModel(private val repository: MoviesSourceRepository) : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    val loadingSuccess: LiveData<Boolean>
        get() = localLoadingSuccess

    private val localLoadingSuccess = MutableLiveData<Boolean>()

    fun loadMovies() {
        launch(Dispatchers.IO) {
            try {
                repository.getMoviesPage()
                localLoadingSuccess.postValue(true)
            } catch (error: Exception) {
                localLoadingSuccess.postValue(false)
            }
        }
    }
}
