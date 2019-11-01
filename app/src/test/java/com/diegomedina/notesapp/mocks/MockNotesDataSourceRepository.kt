package com.diegomedina.notesapp.mocks

import com.diegomedina.notesapp.data.model.Note
import com.diegomedina.notesapp.data.repository.NotesSourceRepository

class MockNotesDataSourceRepository : NotesSourceRepository {
    override suspend fun getNotes(): List<Note> {
        return listOf()
    }
}