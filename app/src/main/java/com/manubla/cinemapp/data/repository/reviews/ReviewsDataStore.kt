package com.manubla.cinemapp.data.repository.reviews

import com.manubla.cinemapp.data.service.response.PageResponse

interface ReviewsDataStore {
    suspend fun getMoviesPage(page: Int): PageResponse
}