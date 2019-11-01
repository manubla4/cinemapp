package com.diegomedina.notesapp.data.repository.notes

import com.diegomedina.notesapp.data.dao.NoteDao
import com.diegomedina.notesapp.data.helper.networking.NetworkingManager
import com.diegomedina.notesapp.data.service.NoteService

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