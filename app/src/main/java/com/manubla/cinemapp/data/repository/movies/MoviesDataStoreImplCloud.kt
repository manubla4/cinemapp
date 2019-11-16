package com.manubla.cinemapp.data.repository.movies

import com.manubla.cinemapp.data.service.MovieService
import com.manubla.cinemapp.data.service.response.MoviesPageResponse

class MoviesDataStoreImplCloud(private var movieService: MovieService) : MoviesDataStore {

    private val popularityDescendant = "popularity.desc"
    private val language = "en-US"

    override suspend fun getMoviesPage(page: Int): MoviesPageResponse {
        return movieService.getMoviesPage(
            language,
            popularityDescendant,
            includeAdult = false,
            includeVideo = false,
            page = page
        )
    }

}