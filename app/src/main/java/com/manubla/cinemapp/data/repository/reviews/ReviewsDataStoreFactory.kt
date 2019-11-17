package com.manubla.cinemapp.data.repository.reviews

import com.manubla.cinemapp.data.dao.ReviewDao
import com.manubla.cinemapp.data.helper.networking.NetworkingManager
import com.manubla.cinemapp.data.repository.genre.ReviewsDataStore
import com.manubla.cinemapp.data.repository.genre.ReviewsDataStoreImplCloud
import com.manubla.cinemapp.data.repository.genre.ReviewsDataStoreImplDatabase
import com.manubla.cinemapp.data.service.ReviewService

@Suppress("UNUSED_PARAMETER")
open class ReviewsDataStoreFactory(
    var service: ReviewService,
    var dao: ReviewDao,
    var networkingManager: NetworkingManager
) {

    val reviewsDataStoreFactory: ReviewsDataStore
        get() = createDataStoreFactory()
    val reviewsDataStoreCloud: ReviewsDataStoreImplCloud
        get() = createDataStoreCloud()
    val reviewsDataStoreDatabase: ReviewsDataStoreImplDatabase
        get() = createDataStoreDatabase()


    private fun createDataStoreFactory() =
        if (networkingManager.isNetworkOnline())
            createDataStoreCloud()
        else
            createDataStoreDatabase()

    private fun createDataStoreCloud() = ReviewsDataStoreImplCloud(service)
    private fun createDataStoreDatabase() = ReviewsDataStoreImplDatabase(dao)

}