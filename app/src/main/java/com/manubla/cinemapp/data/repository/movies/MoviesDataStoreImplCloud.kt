package com.manubla.cinemapp.data.repository.movies

import com.manubla.cinemapp.data.service.MovieService
import com.manubla.cinemapp.data.service.response.MoviesPageResponse

class MoviesDataStoreImplCloud(private var movieService: MovieService) : MoviesDataStore {

    private val popularityDescendant = "popularity.desc"

    override suspend fun getMoviesPage(page: Int): MoviesPageResponse {
        return movieService.getMoviesPage(
            popularityDescendant,
            includeAdult = false,
            includeVideo = false,
            page = page
        )
    }

}