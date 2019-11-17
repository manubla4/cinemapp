package com.manubla.cinemapp.data.repository.genre

import com.manubla.cinemapp.data.model.Genre
import com.manubla.cinemapp.data.repository.genres.GenresDataStoreFactory
import com.manubla.cinemapp.data.service.response.GenreResponse

class GenresSourceRepositoryImpl(var factory: GenresDataStoreFactory) :
    GenresSourceRepository {

    override suspend fun fetchGenres(): GenreResponse {
        return factory.genresDataStoreCloud.fetchGenres()
    }

    override suspend fun storeGenres(genres: List<Genre>) {
        factory.genresDataStoreDatabase.storeGenres(genres)
    }

}