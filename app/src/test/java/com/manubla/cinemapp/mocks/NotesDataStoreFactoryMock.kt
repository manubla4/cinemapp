package com.manubla.cinemapp.mocks

import com.manubla.cinemapp.data.dao.NoteDao
import com.manubla.cinemapp.data.helper.networking.NetworkingManager
import com.manubla.cinemapp.data.repository.notes.NotesDataStoreFactory
import com.manubla.cinemapp.data.service.NoteService

class NotesDataStoreFactoryMock(
    service: NoteService,
    dao: NoteDao,
    networkingManager: NetworkingManager
) : NotesDataStoreFactory(service, dao, networkingManager) {


}