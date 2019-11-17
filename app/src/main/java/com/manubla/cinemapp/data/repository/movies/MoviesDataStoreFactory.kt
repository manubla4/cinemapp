package com.manubla.cinemapp.data.repository.movies

import android.content.Context
import com.manubla.cinemapp.data.dao.MovieDao
import com.manubla.cinemapp.data.helper.networking.NetworkingManager
import com.manubla.cinemapp.data.service.MovieService

@Suppress("UNUSED_PARAMETER")
open class MoviesDataStoreFactory(
    var context: Context,
    var service: MovieService,
    var dao: MovieDao,
    private var networkingManager: NetworkingManager
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

    private fun createDataStoreCloud() = MoviesDataStoreImplCloud(service, context)
    private fun createDataStoreDatabase() = MoviesDataStoreImplDatabase(dao)

}