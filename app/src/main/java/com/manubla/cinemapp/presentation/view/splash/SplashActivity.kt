package com.manubla.cinemapp.presentation.view.splash

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.manubla.cinemapp.R
import com.manubla.cinemapp.data.service.response.ConfigurationResponse
import com.manubla.cinemapp.data.service.response.MoviesPageResponse
import com.manubla.cinemapp.presentation.view.home.HomeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {
    private val splashViewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        splashViewModel.data.observe(this, Observer(this::dataChanged))
        splashViewModel.loadData()
    }


    private fun dataChanged(data: List<Parcelable?>) {
        val intent = Intent(this, HomeActivity::class.java)
        for (element in data) {
            if (element is MoviesPageResponse)
                intent.putExtra(HomeActivity.MoviesPageKey, element)
            else if (element is ConfigurationResponse?)
                intent.putExtra(HomeActivity.ConfigurationKey, element)
        }
        startActivity(intent)
        finish()
    }

}
