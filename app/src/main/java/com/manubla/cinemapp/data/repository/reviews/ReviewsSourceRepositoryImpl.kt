package com.manubla.cinemapp.data.repository.reviews

import com.manubla.cinemapp.data.model.Movie
import com.manubla.cinemapp.data.service.response.PageResponse

class ReviewsSourceRepositoryImpl(var factory: ReviewsDataStoreFactory) :
    ReviewsSourceRepository {

    override suspend fun getMoviesPage(page: Int): PageResponse {
        return factory.moviesDataStoreFactory.getMoviesPage(page)
    }

    override suspend fun storeMovies(movies: List<Movie>) {
        factory.moviesDataStoreDatabase.storeMovies(movies)
    }

}