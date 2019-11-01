package com.diegomedina.notesapp.mocks

import com.diegomedina.notesapp.data.dao.NoteDao
import com.diegomedina.notesapp.data.helper.networking.NetworkingManager
import com.diegomedina.notesapp.data.repository.notes.NotesDataStoreFactory
import com.diegomedina.notesapp.data.service.NoteService

class NotesDataStoreFactoryMock(
    service: NoteService,
    dao: NoteDao,
    networkingManager: NetworkingManager
) : NotesDataStoreFactory(service, dao, networkingManager) {


}