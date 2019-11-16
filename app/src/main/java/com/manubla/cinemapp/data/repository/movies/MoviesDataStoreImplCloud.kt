package com.manubla.cinemapp.data.repository.movies

import com.manubla.cinemapp.data.service.MovieService
import com.manubla.cinemapp.data.service.response.PageResponse

class MoviesDataStoreImplCloud(private var movieService: MovieService) : MoviesDataStore {

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