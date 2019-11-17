package com.manubla.cinemapp.data.repository.configuration

import com.manubla.cinemapp.data.service.response.ConfigurationResponse

class ConfigurationSourceRepositoryImpl(var factory: ConfigurationDataStoreFactory):
    ConfigurationSourceRepository {

    override suspend fun fetchConfiguration(): ConfigurationResponse {
        return factory.configurationDataStoreFactory.fetchConfiguration()
    }

}