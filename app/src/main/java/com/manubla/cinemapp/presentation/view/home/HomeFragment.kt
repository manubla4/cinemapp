package com.manubla.cinemapp.presentation.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.manubla.cinemapp.R
import com.manubla.cinemapp.data.service.response.ConfigurationResponse
import com.manubla.cinemapp.data.service.response.MoviesPageResponse
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private val adapter = HomeAdapter()
    private val homeViewModel: HomeViewModel by viewModel()
    private var moviesPage: MoviesPageResponse? = null
    private var config: ConfigurationResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            moviesPage = it.getParcelable(HomeActivity.MoviesPageKey)
            config = it.getParcelable(HomeActivity.ConfigurationKey)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = this@HomeFragment.adapter
        }
        moviesPage?.results?.let {
            adapter.movies = it
        }

//        swipeLayout.setOnRefreshListener {
////            TODO
//        }

    }
}
