package com.manubla.cinemapp.data.repository.movies

import com.manubla.cinemapp.data.dao.MoviesPageDao
import com.manubla.cinemapp.data.model.MoviesPage

class MoviesDataStoreImplDatabase(private val moviesPageDao: MoviesPageDao) : MoviesDataStore {

    override suspend fun getMoviesPage(page: Int): MoviesPage {
        return moviesPageDao.get(page)
    }
}