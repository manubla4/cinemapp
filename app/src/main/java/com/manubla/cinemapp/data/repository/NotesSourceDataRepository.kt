package com.manubla.cinemapp.data.repository

import com.manubla.cinemapp.data.model.Note
import com.manubla.cinemapp.data.repository.notes.NotesDataStoreFactory

class NotesSourceDataRepository(var factory: NotesDataStoreFactory) : NotesSourceRepository {

    override suspend fun getNotes(): List<Note> {
        return factory.notesDataStoreFactory.getNotes()
    }

}