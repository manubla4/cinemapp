package com.diegomedina.notesapp.data.repository.notes

import com.diegomedina.notesapp.data.model.Note

interface NotesDataStore {

    suspend fun getNotes(): List<Note>
}