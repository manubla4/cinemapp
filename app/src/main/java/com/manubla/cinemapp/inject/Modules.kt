package com.manubla.cinemapp.inject

import android.content.Context
import android.preference.PreferenceManager
import androidx.room.Room
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.manubla.cinemapp.BuildConfig
import com.manubla.cinemapp.data.controller.AuthController
import com.manubla.cinemapp.data.helper.adapter.ZonedDateTimeAdapter
import com.manubla.cinemapp.data.helper.networking.NetworkingManager
import com.manubla.cinemapp.data.repository.MoviesSourceRepository
import com.manubla.cinemapp.data.repository.MoviesSourceRepositoryImpl
import com.manubla.cinemapp.data.repository.movies.MoviesDataStoreFactory
import com.manubla.cinemapp.data.service.AuthService
import com.manubla.cinemapp.data.service.MovieService
import com.manubla.cinemapp.data.source.AppDatabase
import com.manubla.cinemapp.presentation.view.auth.LoginViewModel
import com.manubla.cinemapp.presentation.view.splash.SplashViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.threeten.bp.ZonedDateTime
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

var networkModule = module {
    single { NetworkingManager(get()) }
    single<GsonConverterFactory> {
        GsonConverterFactory.create(
            GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(ZonedDateTime::class.java, ZonedDateTimeAdapter())
                .create()
        )
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .addInterceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader("Authorization", BuildConfig.ACCESS_TOKEN)
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

    single<MovieService> { get<Retrofit>().create(MovieService::class.java) }
    single<AuthService> { get<Retrofit>().create(AuthService::class.java) }
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
}

var moviesModule = module {
    single { MoviesDataStoreFactory(get(), get(), get()) }
    single<MoviesSourceRepository> { MoviesSourceRepositoryImpl(get()) }

    viewModel { SplashViewModel(get()) }
}

var loginModule = module {
    single { AuthController(get(), get()) }
    single { PreferenceManager.getDefaultSharedPreferences(get()) }

    viewModel { LoginViewModel(get()) }
}
