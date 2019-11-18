package com.manubla.cinemapp.presentation.view.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.manubla.cinemapp.R
import com.manubla.cinemapp.data.service.response.ConfigurationResponse
import com.manubla.cinemapp.data.service.response.MoviesPageResponse
import com.manubla.cinemapp.presentation.view.favorites.FavoritesFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val moviesPage = intent.getParcelableExtra<MoviesPageResponse>(MoviesPageKey)
        val config = intent.getParcelableExtra<ConfigurationResponse?>(ConfigurationKey)

        val homeFragment = HomeFragment().apply {
            arguments = Bundle().apply {
                putParcelable(MoviesPageKey, moviesPage)
                putParcelable(ConfigurationKey, config)
            }
        }
        val favoritesFragment = FavoritesFragment()

        showFragment(HomeFragment(), HomeFragmentTag)
        bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            removeActiveFragment()
            when (menuItem.itemId) {
                R.id.home -> showFragment(homeFragment, HomeFragmentTag)
                R.id.favorites -> showFragment(favoritesFragment, FavoritesFragmentTag)
            }
            true
        }
    }

    private fun removeActiveFragment() {
        listOf(HomeFragmentTag, FavoritesFragmentTag).forEach { tag ->
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

        private const val HomeFragmentTag = "HomeFragmentTag"
        private const val FavoritesFragmentTag = "FavoritesFragmentTag"
    }
}
