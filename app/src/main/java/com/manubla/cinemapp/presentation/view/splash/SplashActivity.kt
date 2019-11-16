package com.manubla.cinemapp.presentation.view.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.manubla.cinemapp.R
import com.manubla.cinemapp.data.service.response.MoviesPageResponse
import com.manubla.cinemapp.presentation.view.home.HomeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {
    private val splashViewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        splashViewModel.moviesPage.observe(this, Observer(this::moviesPageChanged))
        splashViewModel.fetchData()
    }


    private fun moviesPageChanged(moviesPage: MoviesPageResponse) {
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra(HomeActivity.MoviesPageKey, moviesPage)
        startActivity(intent)
        finish()
    }

}
