package com.manubla.cinemapp.mocks

import com.manubla.cinemapp.data.model.Note
import com.manubla.cinemapp.data.service.NoteService

open class NotesServiceMock : NoteService {

    override suspend fun getNotes(): List<Note> {
        return listOf()
    }
}