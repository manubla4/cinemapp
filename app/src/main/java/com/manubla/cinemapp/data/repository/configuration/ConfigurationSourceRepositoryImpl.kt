package com.manubla.cinemapp.data.repository.configuration

import android.accounts.NetworkErrorException
import com.manubla.cinemapp.data.service.response.ConfigurationResponse

class ConfigurationSourceRepositoryImpl(var factory: ConfigurationDataStoreFactory):
    ConfigurationSourceRepository {

    override suspend fun fetchConfiguration(): ConfigurationResponse {
        factory.configurationDataStoreFactory?.let {
            return it.fetchConfiguration()
        }
        throw NetworkErrorException()
    }

}