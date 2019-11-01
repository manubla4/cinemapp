package com.diegomedina.notesapp

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import com.diegomedina.notesapp.data.controller.RetrofitController
import com.diegomedina.notesapp.inject.databaseModule
import com.diegomedina.notesapp.inject.loginModule
import com.diegomedina.notesapp.inject.networkModule
import com.diegomedina.notesapp.inject.notesModule
import com.diegomedina.notesapp.presentation.view.accessTokenKey
import com.diegomedina.notesapp.presentation.view.auth.AuthActivity
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import java.lang.ref.WeakReference

class App : Application() {
    private val retrofitController: RetrofitController by inject()
    private val sharedPreferences: SharedPreferences by inject()

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(notesModule, loginModule, networkModule, databaseModule))
        }

        // Initializing LocalDate backport
        AndroidThreeTen.init(this)

        listenActivityCallbacks()

        retrofitController.accessToken = sharedPreferences.getString(accessTokenKey, null)
    }

    private fun listenActivityCallbacks() {
        registerActivityLifecycleCallbacks(Lifecycle())
    }

    inner class Lifecycle : ActivityLifecycleCallbacks {
        override fun onActivityPaused(activity: Activity?) {
        }

        override fun onActivityResumed(activity: Activity?) {
            activity?.let {
                currentActivity = WeakReference(it)
            }
        }

        override fun onActivityStarted(activity: Activity?) {
            activity?.let {
                currentActivity = WeakReference(it)
            }
        }

        override fun onActivityDestroyed(activity: Activity?) {
            if (activity == currentActivity.get()) {
                currentActivity.clear()
            }
        }

        override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
        }

        override fun onActivityStopped(activity: Activity?) {
        }

        override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        }
    }

    companion object {
        var currentActivity = WeakReference<Activity>(null)

        fun goToLoginScreen() {
            currentActivity.get()?.let {
                val intent = Intent(it, AuthActivity::class.java)
                it.startActivity(intent)
                it.finish()
            }
        }
    }
}
