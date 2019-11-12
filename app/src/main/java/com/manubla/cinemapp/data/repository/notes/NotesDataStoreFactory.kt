package com.manubla.cinemapp.data.repository.notes

import com.manubla.cinemapp.data.dao.NoteDao
import com.manubla.cinemapp.data.helper.networking.NetworkingManager
import com.manubla.cinemapp.data.service.NoteService

@Suppress("UNUSED_PARAMETER")
open class NotesDataStoreFactory(
    var service: NoteService,
    var dao: NoteDao,
    var networkingManager: NetworkingManager
) {

    open var notesDataStoreFactory: NotesDataStore
        get() {
            return createDataSourceFactory()
        }
        set(value) {}

    private fun createDataSourceFactory() = if (networkingManager.isNetworkOnline()) {
        CloudNotesDataStore(service)
    } else {
        DatabaseNotesDataStore(dao)
    }
}