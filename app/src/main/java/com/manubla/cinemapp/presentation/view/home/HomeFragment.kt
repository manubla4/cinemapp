package com.manubla.cinemapp.presentation.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.manubla.cinemapp.data.model.Movie
import com.manubla.cinemapp.data.service.response.ConfigurationResponse
import com.manubla.cinemapp.data.service.response.MoviesPageResponse
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private val adapter = HomeAdapter()
    private val homeViewModel: HomeViewModel by viewModel()

    private var moviesPage: MoviesPageResponse? = null
    private var config: ConfigurationResponse? = null
    private var currentPage = 1
    private var loading = false

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
    ): View = inflater.inflate(com.manubla.cinemapp.R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.data.observe(this, Observer(this::dataChanged))
        val layoutManager = GridLayoutManager(activity, 2)
        recyclerView.let {
            it.layoutManager = layoutManager
            it.adapter = adapter
            it.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (dy > 0) {
                        val visibleItemCount = layoutManager.childCount
                        val totalItemCount = layoutManager.itemCount
                        val pastVisiblesItems = layoutManager.findFirstVisibleItemPosition()

                        if (!loading) {
                            if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                                loading = true
                                adapter.addItem(Any())
                                homeViewModel.loadData(currentPage)
                            }
                        }
                    }
                }
            })
        }
        loading = true
        adapter.addItem(Any())
        homeViewModel.loadData(currentPage)

//        moviesPage?.results?.let {
//            adapter.movies = it
//        }

//        swipeLayout.setOnRefreshListener {
////            TODO
//        }

    }

    private fun dataChanged(data: List<Movie>) {
        adapter.removeLastItem()
        adapter.addItems(data)
        currentPage++
        loading = false
    }
}
