package com.diegomedina.notesapp.data.repository.notes

import com.diegomedina.notesapp.data.model.Note
import com.diegomedina.notesapp.data.service.NoteService

class CloudNotesDataStore(private var noteService: NoteService) : NotesDataStore {

    override suspend fun getNotes(): List<Note> {
        return noteService.getNotes()
    }

}