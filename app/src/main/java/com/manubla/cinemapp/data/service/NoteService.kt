package com.manubla.cinemapp.data.service

import com.manubla.cinemapp.data.model.Note
import retrofit2.http.GET

interface NoteService {
    @GET("notes")
    suspend fun getNotes(): List<Note>
}
