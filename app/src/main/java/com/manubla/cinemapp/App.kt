package com.manubla.cinemapp

import android.app.Activity
import android.app.Application
import android.content.SharedPreferences
import android.os.Bundle
import com.jakewharton.threetenabp.AndroidThreeTen
import com.manubla.cinemapp.inject.databaseModule
import com.manubla.cinemapp.inject.loginModule
import com.manubla.cinemapp.inject.networkModule
import com.manubla.cinemapp.inject.moviesModule
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import java.lang.ref.WeakReference

class App : Application() {
    private val sharedPreferences: SharedPreferences by inject()

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(moviesModule, loginModule, networkModule, databaseModule))
        }

        // Initializing LocalDate backport
        AndroidThreeTen.init(this)

        listenActivityCallbacks()
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

//        fun goToLoginScreen() {
//            currentActivity.get()?.let {
//                val intent = Intent(it, AuthActivity::class.java)
//                it.startActivity(intent)
//                it.finish()
//            }
//        }
    }
}
