package com.example.rappiinterview.ui.util

import android.annotation.SuppressLint
import android.app.Activity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.rappiinterview.R
import com.example.rappiinterview.infrastructure.manager.interfaces.GlideImageManager
import com.example.rappiinterview.infrastructure.networking.services.interfaces.MoviesService.Companion.BASE_MOVIE_URL
import com.example.rappiinterview.infrastructure.networking.services.responses.Item

class PopUpsUtils(private val imageManager: GlideImageManager) {
    fun showAlertDialog(activity: Activity, description: String) {
        val alertDialog = AlertDialog.Builder(activity).create()
        val view = activity.layoutInflater.inflate(R.layout.popup_general_error, null)
        alertDialog.setView(view)
        view.findViewById<TextView>(R.id.description).text = description
        view.findViewById<TextView>(R.id.title).text = activity.getString(R.string.app_name)
        val okButton = view.findViewById<Button>(R.id.buttonOK)
        okButton.setOnClickListener {
            alertDialog.dismiss()
        }
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    @SuppressLint("StringFormatMatches")
    fun showMovieDetailPopUp(activity: Activity, movie: Item) {
        val alertDialog = AlertDialog.Builder(activity).create()
        val view = activity.layoutInflater.inflate(R.layout.popup_movie_details, null)
        alertDialog.setView(view)

        val genres = movie.genre_ids.toString()
        val formattedGenres = genres.subSequence(
            genres.indexOf("["),
            genres.indexOf("]").inc()
        )
        view.findViewById<TextView>(R.id.details).text =
            activity.getString(
                R.string.popup_movie_details_format,
                movie.adult,
                formattedGenres,
                movie.id,
                movie.media_type,
                movie.original_language,
                movie.original_title,
                movie.overview,
                movie.popularity,
                movie.release_date,
                movie.title,
                movie.video,
                movie.vote_average,
                movie.vote_count,
                movie.category
            )
        val poster = view.findViewById<ImageView>(R.id.poster)
        val backdrop = view.findViewById<ImageView>(R.id.backdrop)
        val posterPath = view.findViewById<TextView>(R.id.posterPath)
        val backdropPath = view.findViewById<TextView>(R.id.backdropPath)
        imageManager.loadImage(activity, BASE_MOVIE_URL + movie.poster_path, R.drawable.movie_place_holder, poster)
        imageManager.loadImage(activity, BASE_MOVIE_URL + movie.backdrop_path, R.drawable.movie_place_holder, backdrop)
        posterPath.text = movie.poster_path
        backdropPath.text = movie.backdrop_path
        alertDialog.setCancelable(true)
        alertDialog.show()
    }
}