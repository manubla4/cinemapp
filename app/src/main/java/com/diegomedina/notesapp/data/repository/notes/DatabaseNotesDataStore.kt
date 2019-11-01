package com.diegomedina.notesapp.data.repository.notes

import com.diegomedina.notesapp.data.dao.NoteDao
import com.diegomedina.notesapp.data.model.Note

class DatabaseNotesDataStore(private val noteDao: NoteDao) : NotesDataStore {

    override suspend fun getNotes(): List<Note> {
        return noteDao.getAll()
    }
}