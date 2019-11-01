package com.diegomedina.notesapp.data.repository

import com.diegomedina.notesapp.data.model.Note
import com.diegomedina.notesapp.data.repository.notes.NotesDataStoreFactory

class NotesSourceDataRepository(var factory: NotesDataStoreFactory) : NotesSourceRepository {

    override suspend fun getNotes(): List<Note> {
        return factory.notesDataStoreFactory.getNotes()
    }

}