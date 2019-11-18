package com.manubla.cinemapp.presentation.util

import android.graphics.BitmapFactory
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
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
    val imgFile = File(path)
    if (imgFile.exists()) {
        val bitmap = BitmapFactory.decodeFile(path)
        imageView.setImageBitmap(bitmap)
    }
}