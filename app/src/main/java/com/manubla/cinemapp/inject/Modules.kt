package com.manubla.cinemapp.inject

import android.content.Context
import androidx.room.Room
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.manubla.cinemapp.BuildConfig
import com.manubla.cinemapp.data.helper.adapter.ZonedDateTimeAdapter
import com.manubla.cinemapp.data.helper.networking.NetworkingManager
import com.manubla.cinemapp.data.repository.movies.MoviesDataStoreFactory
import com.manubla.cinemapp.data.repository.movies.MoviesSourceRepository
import com.manubla.cinemapp.data.repository.movies.MoviesSourceRepositoryImpl
import com.manubla.cinemapp.data.repository.reviews.ReviewsDataStoreFactory
import com.manubla.cinemapp.data.repository.reviews.ReviewsSourceRepository
import com.manubla.cinemapp.data.repository.reviews.ReviewsSourceRepositoryImpl
import com.manubla.cinemapp.data.service.MovieService
import com.manubla.cinemapp.data.service.ReviewService
import com.manubla.cinemapp.data.source.AppDatabase
import com.manubla.cinemapp.presentation.view.splash.SplashViewModel
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.threeten.bp.ZonedDateTime
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

var networkModule = module {
    single { NetworkingManager(get()) }
    single<Converter.Factory> {
        GsonConverterFactory.create(
            GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(ZonedDateTime::class.java, ZonedDateTimeAdapter())
                .create()
        )
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(ChuckInterceptor(get()))
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .addInterceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader("Authorization",
                        "Bearer " + BuildConfig.BEARER_TOKEN)
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(get())
        .client(get())
        .build()
    }

    single<MovieService> {
        get<Retrofit>().create(MovieService::class.java)
    }
    single<ReviewService> {
        get<Retrofit>().create(ReviewService::class.java)
    }
}

var databaseModule = module {
    single {
        Room.databaseBuilder<AppDatabase>(
            get<Context>().applicationContext,
            AppDatabase::class.java,
            BuildConfig.DATABASE_NAME
        )
        .fallbackToDestructiveMigration()
        .build()
    }
    single { get<AppDatabase>().movieDao() }
    single { get<AppDatabase>().reviewDao() }
    single { get<AppDatabase>().genreDao() }
    single { get<AppDatabase>().movieGenreDao() }
}

var moviesModule = module {
    single { MoviesDataStoreFactory(get(), get(), get()) }
    single<MoviesSourceRepository> {
        MoviesSourceRepositoryImpl(get())
    }
    viewModel { SplashViewModel(get()) }
}

var reviewsModule = module {
    single { ReviewsDataStoreFactory(get(), get(), get()) }
    single<ReviewsSourceRepository> {
        ReviewsSourceRepositoryImpl(get())
    }
}