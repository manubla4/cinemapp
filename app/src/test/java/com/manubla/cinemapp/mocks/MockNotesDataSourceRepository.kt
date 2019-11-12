package com.manubla.cinemapp.mocks

import com.manubla.cinemapp.data.model.Note
import com.manubla.cinemapp.data.repository.NotesSourceRepository

class MockNotesDataSourceRepository : NotesSourceRepository {
    override suspend fun getNotes(): List<Note> {
        return listOf()
    }
}