package com.manubla.cinemapp.presentation.view.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manubla.cinemapp.data.repository.movies.MoviesSourceRepository
import com.manubla.cinemapp.data.service.response.GenreResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.threeten.bp.ZonedDateTime
import kotlin.coroutines.CoroutineContext

class DetailViewModel(private val moviesRepository: MoviesSourceRepository) : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    val genres: LiveData<GenreResponse>
        get() = localGenres
    val favoriteDate: LiveData<ZonedDateTime?>
        get() = localFavoriteDate

    private val localFavoriteDate = MutableLiveData<ZonedDateTime?>()
    private val localGenres = MutableLiveData<GenreResponse>()

    fun loadGenres(movieId: Int) {
        launch(Dispatchers.IO) {
            val genresResponse = getGenresForMovie(movieId)
            localGenres.postValue(genresResponse)
        }
    }

    fun storeFavorite(movieId: Int, state: Boolean) {
        launch(Dispatchers.IO) {
            val date: ZonedDateTime? =
                if (state) ZonedDateTime.now()
                else null
            storeFavorite(movieId, date)
            localFavoriteDate.postValue(date)
        }
    }

    fun loadFavorite(movieId: Int) {
        launch(Dispatchers.IO) {
            val date = getFavoriteDate(movieId)
            localFavoriteDate.postValue(date)
        }
    }


    private suspend fun getFavoriteDate(movieId: Int): ZonedDateTime? = try {
        moviesRepository.getLocalFavoriteDate(movieId)
    } catch (error: Exception) {
        null
    }

    private suspend fun getGenresForMovie(movieId: Int): GenreResponse = try {
            moviesRepository.getLocalGenresForMovie(movieId)
        } catch (error: Exception) {
            GenreResponse(listOf())
        }


    private suspend fun storeFavorite(movieId: Int, date: ZonedDateTime?) {
        try {
            moviesRepository.updateMovieFavorite(movieId, date)
        } catch (ignored: Exception) {
        }
    }
}