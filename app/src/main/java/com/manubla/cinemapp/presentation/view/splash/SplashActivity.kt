package com.manubla.cinemapp.presentation.view.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.manubla.cinemapp.R
import com.manubla.cinemapp.presentation.view.home.HomeActivity

class SplashActivity : AppCompatActivity() {
    private val splashViewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        splashViewModel.loadingSuccess.observe(this, Observer(this::loadingSuccessChanged))
        splashViewModel.loadMovies()
    }


    private fun loadingSuccessChanged(success: Boolean) {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

}
