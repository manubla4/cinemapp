package com.manubla.cinemapp.presentation.view.home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.manubla.cinemapp.R
import com.manubla.cinemapp.data.service.response.MoviesPageResponse

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val moviesPage = intent.getParcelableExtra<MoviesPageResponse>(MoviesPageKey)

//        showFragment(NotesFragment(), NotesFragmentTag)
//        bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
//            removeActiveFragment()
//
//            when (menuItem.itemId) {
//                R.id.notes -> showFragment(NotesFragment(), NotesFragmentTag)
//                R.id.profile -> showFragment(ProfileFragment(), ProfileFragmentTag)
//            }
//
//            true
//        }
    }

    private fun removeActiveFragment() {
        listOf(NotesFragmentTag, ProfileFragmentTag).forEach { tag ->
            val fragment = supportFragmentManager.findFragmentByTag(tag)
            fragment?.let {
                supportFragmentManager
                    .beginTransaction()
                    .remove(it)
                    .commit()
            }
        }
    }

    private fun showFragment(fragment: Fragment, tag: String) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, fragment, tag)
            .commit()
    }

    companion object {
        const val MoviesPageKey = "MoviesPageKey"
        const val ConfigurationKey = "ConfigurationKey"

        private const val NotesFragmentTag = "NotesFragmentTag"
        private const val ProfileFragmentTag = "ProfileFragmentTag"
    }
}
