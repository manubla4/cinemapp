package com.diegomedina.notesapp.data.repository

import com.diegomedina.notesapp.data.model.Note

interface NotesSourceRepository {

    suspend fun getNotes(): List<Note>
}