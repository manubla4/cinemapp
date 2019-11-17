package com.manubla.cinemapp.data.repository.configuration

import android.accounts.NetworkErrorException
import com.manubla.cinemapp.data.service.response.ConfigurationResponse

class ConfigurationSourceRepositoryImpl(var factory: ConfigurationDataStoreFactory):
    ConfigurationSourceRepository {

    override suspend fun getConfiguration(): ConfigurationResponse {
        factory.configurationDataStoreFactory?.let {
            return it.getConfiguration()
        }
        throw NetworkErrorException()
    }

}