package com.manubla.cinemapp.data.repository.movies

import com.manubla.cinemapp.data.dao.MoviesPageDao
import com.manubla.cinemapp.data.helper.networking.NetworkingManager
import com.manubla.cinemapp.data.service.MovieService

@Suppress("UNUSED_PARAMETER")
open class MoviesDataStoreFactory(
    var service: MovieService,
    var dao: MoviesPageDao,
    var networkingManager: NetworkingManager
) {

    val moviesDataStoreFactory: MoviesDataStore
        get() = createDataSourceFactory()

    private fun createDataSourceFactory() =
        if (networkingManager.isNetworkOnline())
            MoviesDataStoreImplCloud(service)
        else
            MoviesDataStoreImplDatabase(dao)


}