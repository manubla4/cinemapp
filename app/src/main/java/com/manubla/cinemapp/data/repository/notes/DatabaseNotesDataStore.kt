package com.manubla.cinemapp.data.repository.notes

import com.manubla.cinemapp.data.dao.NoteDao
import com.manubla.cinemapp.data.model.Note

class DatabaseNotesDataStore(private val noteDao: NoteDao) : NotesDataStore {

    override suspend fun getNotes(): List<Note> {
        return noteDao.getAll()
    }
}