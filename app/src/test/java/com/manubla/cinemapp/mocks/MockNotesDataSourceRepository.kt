package com.manubla.cinemapp.mocks

import com.manubla.cinemapp.data.model.Note
import com.manubla.cinemapp.data.repository.MoviesSourceRepository

class MockNotesDataSourceRepository : MoviesSourceRepository {
    override suspend fun getNotes(): List<Note> {
        return listOf()
    }
}