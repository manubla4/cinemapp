package com.manubla.cinemapp.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.manubla.cinemapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val intent = when {
//            retrofitController.accessToken != null -> Intent(this, HomeActivity::class.java)
//            else -> Intent(this, AuthActivity::class.java)
//        }
//
//        startActivity(intent)
//        finish()
    }
}
