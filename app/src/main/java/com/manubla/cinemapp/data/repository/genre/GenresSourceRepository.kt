package com.manubla.cinemapp.data.repository.genre

import com.manubla.cinemapp.data.model.Genre
import com.manubla.cinemapp.data.service.response.GenreResponse

interface GenresSourceRepository {
    suspend fun fetchGenres(): GenreResponse
    suspend fun storeGenres(genres: List<Genre>)
}