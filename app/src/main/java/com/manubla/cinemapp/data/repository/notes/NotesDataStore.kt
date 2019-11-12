package com.manubla.cinemapp.data.repository.notes

import com.manubla.cinemapp.data.model.Note

interface NotesDataStore {

    suspend fun getNotes(): List<Note>
}