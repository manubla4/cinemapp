package com.manubla.cinemapp.data.repository.notes

import com.manubla.cinemapp.data.model.Note
import com.manubla.cinemapp.data.service.NoteService

class CloudNotesDataStore(private var noteService: NoteService) : NotesDataStore {

    override suspend fun getNotes(): List<Note> {
        return noteService.getNotes()
    }

}