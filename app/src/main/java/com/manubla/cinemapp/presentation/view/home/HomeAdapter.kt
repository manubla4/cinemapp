package com.manubla.cinemapp.presentation.view.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.manubla.cinemapp.R
import com.manubla.cinemapp.data.model.Movie
import com.manubla.cinemapp.databinding.ItemMovieBinding

class HomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var movies = arrayListOf<Any>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun addItems(items: List<Movie>) {
        movies.addAll(items)
        notifyDataSetChanged()
    }

    fun addItem(item: Any) {
        movies.add(item)
        notifyDataSetChanged()
    }

    fun removeLastItem() {
        movies.removeAt(movies.size - 1)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == MOVIE_TYPE) {
            MovieViewHolder(ItemMovieBinding.inflate(inflater))
        } else {
            ProgressViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_progress, parent, false)
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (movies[position] is Movie)
            return MOVIE_TYPE
        return PROGRESS_TYPE
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MovieViewHolder)
            holder.bind(movies[position] as Movie)
    }

    inner class MovieViewHolder(val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie?) {
            binding.movie = item
            binding.executePendingBindings()
        }
    }

    inner class ProgressViewHolder(view: View) : RecyclerView.ViewHolder(view)

    companion object {
        private const val PROGRESS_TYPE = 1
        private const val MOVIE_TYPE = 2
    }
}