package com.manubla.cinemapp.data.repository.configuration

import com.manubla.cinemapp.data.service.ConfigurationService

@Suppress("UNUSED_PARAMETER")
open class ConfigurationDataStoreFactory(
    var service: ConfigurationService
) {

    val configurationDataStoreFactory: ConfigurationDataStore
        get() = createDataStoreFactory()


    private fun createDataStoreFactory() =
        ConfigurationDataStoreImplCloud(service)


}