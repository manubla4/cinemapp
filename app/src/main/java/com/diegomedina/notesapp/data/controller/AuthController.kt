package com.diegomedina.notesapp.data.controller

import android.content.SharedPreferences
import com.diegomedina.notesapp.data.service.AuthService
import com.diegomedina.notesapp.data.service.request.LoginRequest
import com.diegomedina.notesapp.presentation.view.accessTokenKey

class AuthController(
    private val sharedPreferences: SharedPreferences,
    private val authService: AuthService,
    private val retrofitController: RetrofitController
) {
    suspend fun login(email: String, password: String) {
        val request = LoginRequest(email, password)
        val response = authService.login(request)

        with(response.authToken) {
            retrofitController.accessToken = this
            sharedPreferences.edit()
                .putString(accessTokenKey, this)
                .apply()
        }
    }

    suspend fun logout() {
        authService.logout()
        retrofitController.accessToken = null
    }
}
