package com.manubla.cinemapp.presentation.view.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.manubla.cinemapp.R

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, LoginFragment(), LoginFragmentTag)
            .commit()
    }

    companion object {
        private const val LoginFragmentTag = "LoginFragmentTag"
    }
}
