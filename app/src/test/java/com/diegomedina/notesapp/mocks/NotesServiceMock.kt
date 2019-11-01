package com.diegomedina.notesapp.mocks

import com.diegomedina.notesapp.data.model.Note
import com.diegomedina.notesapp.data.service.NoteService

open class NotesServiceMock : NoteService {

    override suspend fun getNotes(): List<Note> {
        return listOf()
    }
}