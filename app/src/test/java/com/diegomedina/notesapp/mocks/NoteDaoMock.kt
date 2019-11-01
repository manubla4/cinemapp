package com.diegomedina.notesapp.mocks

import com.diegomedina.notesapp.data.dao.NoteDao
import com.diegomedina.notesapp.data.model.Note

class NoteDaoMock : NoteDao {

    override suspend fun getAll(): List<Note> {
        return listOf()
    }

    override suspend fun insertAll(vararg notes: Note) {

    }

    override suspend fun delete(note: Note) {

    }

}