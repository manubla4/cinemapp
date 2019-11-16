package com.manubla.cinemapp.data.repository.reviews

import com.manubla.cinemapp.data.service.MovieService
import com.manubla.cinemapp.data.service.response.PageResponse

class ReviewsDataStoreImplCloud(private var movieService: MovieService) : ReviewsDataStore {

    private val popularityDescendant = "popularity.desc"

    override suspend fun getMoviesPage(page: Int): PageResponse {
        return movieService.getMoviesPage(
            popularityDescendant,
            includeAdult = false,
            includeVideo = false,
            page = page
        )
    }

}