package com.manubla.cinemapp.presentation.view.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.manubla.cinemapp.R
import com.manubla.cinemapp.data.model.Genre
import com.manubla.cinemapp.data.model.Movie
import com.manubla.cinemapp.data.service.response.MoviesPageResponse
import com.manubla.cinemapp.databinding.FragmentDetailBinding
import com.manubla.cinemapp.presentation.helper.gone
import com.manubla.cinemapp.presentation.helper.visible
import com.manubla.cinemapp.presentation.util.showLongErrorMessage
import com.manubla.cinemapp.presentation.view.home.HomeAdapter
import com.manubla.cinemapp.presentation.view.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {
    private val detailViewModel: DetailViewModel by viewModel()
    private var movie: Movie? = null
    private lateinit var binding: FragmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movie = arguments?.getParcelable<Movie?>(DetailActivity.MovieKey)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_detail,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.movie = movie
        binding.genres = listOf<Genre>()

    }

}
