package com.manubla.cinemapp.mocks

import com.manubla.cinemapp.data.dao.NoteDao
import com.manubla.cinemapp.data.helper.networking.NetworkingManager
import com.manubla.cinemapp.data.repository.movies.MoviesDataStoreFactory
import com.manubla.cinemapp.data.service.NoteService

class MoviesDataStoreFactoryMock(
    service: NoteService,
    dao: NoteDao,
    networkingManager: NetworkingManager
) : MoviesDataStoreFactory(service, dao, networkingManager) {


}