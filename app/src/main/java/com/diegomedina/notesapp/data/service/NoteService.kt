package com.diegomedina.notesapp.data.service

import com.diegomedina.notesapp.data.model.Note
import retrofit2.http.GET

interface NoteService {
    @GET("notes")
    suspend fun getNotes(): List<Note>
}
