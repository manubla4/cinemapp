package com.manubla.cinemapp.presentation.util

import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.manubla.cinemapp.R
import com.manubla.cinemapp.data.service.response.GenreResponse
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.io.File


@BindingAdapter("bindMovieDate")
fun bindMovieDate(textView: TextView, date: ZonedDateTime) {
     textView.text = DateTimeFormatter.ofPattern("yyyy").format(date)
}

@BindingAdapter("bindMovieRate")
fun bindMovieRate(textView: TextView, rate: Double) {
     textView.text = rate.toString()
}

@BindingAdapter("bindMoviePoster")
fun bindMoviePoster(imageView: ImageView, path: String) {

    Glide.with(imageView.context)
        .load(path)
        .listener(object : RequestListener<Drawable> {

            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?,
                 dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                return false
            }

            override fun onLoadFailed(e: GlideException?, model: Any?,
                    target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                return false
            }

        })
        .into(imageView)


}

@BindingAdapter("bindMovieGenres")
fun bindMovieGenres(textView: TextView, genreResponse: GenreResponse?) {
    var result = ""
    genreResponse?.apply {
        for (genre in genres) {
            result = result.plus(genre.name)
            if (genres.indexOf(genre)
                != (genres.size - 1)) { //No comma for last item
                result = result.plus(", ")
            }
        }
    }
    textView.text = result
}

@BindingAdapter("bindMovieFavorite")
fun bindMovieFavorite(imageView: ImageView, date: ZonedDateTime?) {
    if (date == null)
        imageView.setImageDrawable(imageView.context.getDrawable(R.drawable.ic_favorite_border_40dp))
    else
        imageView.setImageDrawable(imageView.context.getDrawable(R.drawable.ic_favorite_filled_40dp))
}