package com.manubla.cinemapp.data.repository

import com.manubla.cinemapp.data.model.Note

interface NotesSourceRepository {

    suspend fun getNotes(): List<Note>
}