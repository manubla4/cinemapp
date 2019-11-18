package com.manubla.cinemapp.presentation.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.manubla.cinemapp.data.model.Movie
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private val adapter = HomeAdapter()
    private val homeViewModel: HomeViewModel by viewModel()

    private var currentRating = 0
    private var currentPage = 1
    private var loading = false


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
                                adapter.addItem(Any())  //Progress item
                                homeViewModel.loadData(currentPage)
                            }
                        }
                    }
                }
            })
        }

        ratingBar.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (currentRating == ratingBar.rating.toInt()) {
                    ratingBar.rating = 1f
                    currentRating = 0
                } else
                    currentRating = ratingBar.rating.toInt()
            }
            false
        }

        loading = true
        swipeLayout.isRefreshing = true
        homeViewModel.loadData(currentPage)


        swipeLayout.setOnRefreshListener {
            adapter.movies = arrayListOf()
            currentPage = 1
            homeViewModel.loadData(currentPage)
        }
    }


    private fun dataChanged(data: List<Movie>) {
        swipeLayout.isRefreshing = false
        if (currentPage > 1)
            adapter.removeProgressItem()
        adapter.addMovieItems(data)
        if (currentPage == 1)
            recyclerView.scheduleLayoutAnimation()
        currentPage++
        loading = false
    }
}
