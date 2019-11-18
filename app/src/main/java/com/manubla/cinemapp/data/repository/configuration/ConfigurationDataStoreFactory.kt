package com.manubla.cinemapp.data.repository.configuration

import android.accounts.NetworkErrorException
import com.manubla.cinemapp.data.helper.networking.NetworkingManager
import com.manubla.cinemapp.data.service.ConfigurationService

@Suppress("UNUSED_PARAMETER")
open class ConfigurationDataStoreFactory(
    var service: ConfigurationService,
    var networkingManager: NetworkingManager
) {

    val configurationDataStoreFactory: ConfigurationDataStore
        get() = createDataStoreFactory()


    private fun createDataStoreFactory() =
        if (networkingManager.isNetworkOnline())
            ConfigurationDataStoreImplCloud(service)
        else
            throw NetworkErrorException()

}