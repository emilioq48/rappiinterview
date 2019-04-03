package com.example.rappiinterview.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rappiinterview.R
import com.example.rappiinterview.infrastructure.manager.interfaces.ImageManager
import com.example.rappiinterview.infrastructure.rest.request.MoviesService.Companion.BASE_MOVIE_URL
import com.example.rappiinterview.domain.model.Item
import com.example.rappiinterview.ui.util.di.ActivityScoped
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_movie.*
import javax.inject.Inject

// I just let the Serie viewtype's logic commented as I couln't get a response from any service
// getting me series so I just used movies
@ActivityScoped
class MoviesAdapter @Inject constructor(
    private val listener: MovieClickListener,
    private val imageManager: ImageManager
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = ArrayList<Any>()

    companion object {
        const val TYPE_MOVIE = 1
        const val TYPE_SERIE = 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_MOVIE -> MovieViewHolder(inflater.inflate(R.layout.item_movie, parent, false))
//            TYPE_SERIE -> SerieViewHolder(inflater.inflate(R.layout.item_serie, parent, false))
            else -> throw RuntimeException("There is no type that matches the type $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            items[position] is Item -> TYPE_MOVIE
//            items[position] is Phone -> TYPE_SERIE
            else -> throw RuntimeException("There is no ViewHolder that matches the type")
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieViewHolder -> holder.bind(items[position] as Item, listener, imageManager)
//            is SerieViewHolder -> holder.bind(items[position] as Serie)
        }
    }

    fun setItems(movies: List<Any>?) {
        if (movies != null) {
            items.clear()
            items.addAll(movies)
            notifyDataSetChanged()
        }
    }

    class MovieViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(movie: Item, listener: MovieClickListener, imageManager: ImageManager) {
            movieTitle.text = movie.title
            imageManager.loadImage(
                containerView.context,
                BASE_MOVIE_URL + movie.poster_path,
                R.drawable.movie_place_holder,
                movieImage
            )
            movieAverageVotes.text = movie.vote_average.toString()
            movieVoteCount.text = movie.vote_count.toString()
            movieContainer.setOnLongClickListener {
                listener.onMovieLongClicked(movie)
                true
            }
            movieContainer.setOnClickListener {
                //Do what needed here with the click listener, not needed for this exercise
                listener.onMovieClicked(movie)
            }
        }
    }

//    class SerieViewHolder(
//        override val containerView: View
//    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
//        fun bind(serie: Serie) {
//            //fill the view with content
//        }
//    }

    interface MovieClickListener {
        fun onMovieClicked(movie: Item)
        fun onMovieLongClicked(movie: Item)
    }
}