package com.manubla.cinemapp.data.repository.movies

import com.manubla.cinemapp.data.dao.MovieDao
import com.manubla.cinemapp.data.helper.networking.NetworkingManager
import com.manubla.cinemapp.data.service.MovieService

@Suppress("UNUSED_PARAMETER")
open class MoviesDataStoreFactory(
    var service: MovieService,
    var dao: MovieDao,
    var networkingManager: NetworkingManager
) {

    val moviesDataStoreFactory: MoviesDataStore
        get() = createDataStoreFactory()
    val moviesDataStoreCloud: MoviesDataStoreImplCloud
        get() = createDataStoreCloud()
    val moviesDataStoreDatabase: MoviesDataStoreImplDatabase
        get() = createDataStoreDatabase()


    private fun createDataStoreFactory() =
        if (networkingManager.isNetworkOnline())
            createDataStoreCloud()
        else
            createDataStoreDatabase()

    private fun createDataStoreCloud() = MoviesDataStoreImplCloud(service)
    private fun createDataStoreDatabase() = MoviesDataStoreImplDatabase(dao)

}