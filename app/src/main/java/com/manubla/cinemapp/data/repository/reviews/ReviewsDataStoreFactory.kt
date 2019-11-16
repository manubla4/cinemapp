package com.manubla.cinemapp.data.repository.reviews

import com.manubla.cinemapp.data.dao.MovieDao
import com.manubla.cinemapp.data.helper.networking.NetworkingManager
import com.manubla.cinemapp.data.service.MovieService

@Suppress("UNUSED_PARAMETER")
open class ReviewsDataStoreFactory(
    var service: MovieService,
    var dao: MovieDao,
    var networkingManager: NetworkingManager
) {

    val moviesDataStoreFactory: ReviewsDataStore
        get() = createDataStoreFactory()
    val moviesDataStoreCloud: ReviewsDataStoreImplCloud
        get() = createDataStoreCloud()
    val moviesDataStoreDatabase: ReviewsDataStoreImplDatabase
        get() = createDataStoreDatabase()


    private fun createDataStoreFactory() =
        if (networkingManager.isNetworkOnline())
            createDataStoreCloud()
        else
            createDataStoreDatabase()

    private fun createDataStoreCloud() = ReviewsDataStoreImplCloud(service)
    private fun createDataStoreDatabase() = ReviewsDataStoreImplDatabase(dao)

}