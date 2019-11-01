package com.diegomedina.notesapp.presentation.view.home.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.diegomedina.notesapp.R
import com.diegomedina.notesapp.data.controller.AuthController
import com.diegomedina.notesapp.presentation.view.auth.AuthActivity
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject
import kotlin.coroutines.CoroutineContext

class ProfileFragment : Fragment(), CoroutineScope {
    private val authController: AuthController by inject()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_profile, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logOut.setOnClickListener { logout() }
    }

    private fun logout() {
        launch(Dispatchers.IO) {
            try {
                authController.logout()
                withContext(Dispatchers.Main) {
                    activity?.let {
                        it.startActivity(Intent(it, AuthActivity::class.java))
                        it.finish()
                    }
                }
            } catch (error: Exception) {

            }
        }
    }
}
