package com.manubla.cinemapp.mocks

import com.manubla.cinemapp.data.dao.NoteDao
import com.manubla.cinemapp.data.model.Note

class NoteDaoMock : NoteDao {

    override suspend fun getAll(): List<Note> {
        return listOf()
    }

    override suspend fun insertAll(vararg notes: Note) {

    }

    override suspend fun delete(note: Note) {

    }

}