package com.manubla.cinemapp.data.repository.genre

import android.accounts.NetworkErrorException
import com.manubla.cinemapp.data.model.Genre
import com.manubla.cinemapp.data.repository.genres.GenresDataStoreFactory
import com.manubla.cinemapp.data.service.response.GenreResponse

class GenresSourceRepositoryImpl(var factory: GenresDataStoreFactory) :
    GenresSourceRepository {

    override suspend fun fetchGenres(): GenreResponse? {
        factory.genresDataStoreCloud?.let {
            return it.fetchGenres()
        }
        throw NetworkErrorException()
    }

    override suspend fun storeGenres(genres: List<Genre>) {
        factory.genresDataStoreDatabase.storeGenres(genres)
    }

}