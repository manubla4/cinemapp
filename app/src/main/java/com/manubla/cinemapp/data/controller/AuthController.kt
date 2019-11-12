package com.manubla.cinemapp.data.controller

import android.content.SharedPreferences
import com.manubla.cinemapp.data.service.AuthService
import com.manubla.cinemapp.data.service.request.LoginRequest
import com.manubla.cinemapp.presentation.view.accessTokenKey

class AuthController(
    private val sharedPreferences: SharedPreferences,
    private val authService: AuthService
) {
    suspend fun login(email: String, password: String) {
        val request = LoginRequest(email, password)
        val response = authService.login(request)

        with(response.authToken) {
            sharedPreferences.edit()
                .putString(accessTokenKey, this)
                .apply()
        }
    }

    suspend fun logout() {
        authService.logout()
    }
}