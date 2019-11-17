package com.manubla.cinemapp.data.repository.movies

import android.content.Context
import com.bumptech.glide.Glide
import com.manubla.cinemapp.data.model.Movie
import com.manubla.cinemapp.data.service.MovieService
import com.manubla.cinemapp.data.service.response.ImagesConfigurationResponse
import com.manubla.cinemapp.data.service.response.MoviesPageResponse
import java.io.File

class MoviesDataStoreImplCloud(private var movieService: MovieService,
                               private val context: Context) : MoviesDataStore {

    private val defaultPopularitySort = "popularity.desc"
    private val defaultPosterSize = "w185"
    private val defaultLanguage = "en-US"

    override suspend fun getMoviesPage(page: Int): MoviesPageResponse {
        val page = movieService.getMoviesPage(
            defaultLanguage,
            defaultPopularitySort,
            includeAdult = false,
            includeVideo = false,
            page = page
        )
        page.fromCloud = true
        return page
    }

    override suspend fun getMovie(movieId: Int): Movie? {
        TODO("not implemented")
    }

    suspend fun getRemoteImage(posterPath: String?, imageConfig: ImagesConfigurationResponse): String {
        var avgPosterSize: String = try {
            imageConfig.posterSizes[imageConfig.posterSizes.size / 2]
        } catch (e: Exception) {
            defaultPosterSize
        }
        val url = imageConfig.secureBaseUrl + avgPosterSize + posterPath
        val file: File = Glide.with(context).asFile().load(url).submit().get()
        return file.absolutePath
    }

}