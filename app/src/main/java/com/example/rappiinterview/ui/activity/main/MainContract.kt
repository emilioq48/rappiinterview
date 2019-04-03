package com.example.rappiinterview.ui.activity.main

import com.example.rappiinterview.infrastructure.networking.services.responses.Item

interface MainContract {
    interface View {
        fun showError(errorMessage: String)
        fun getDefaultErrorMessage(): String
        fun updateItems(items: List<Item>?)
        fun hideProgress()
        fun showProgress()
    }

    interface Presenter {
        fun refreshMovies()
        fun onStop()
        fun onMovieClicked(movie: Item)
        fun onTopRatedMoviesButtonClicked()
        fun onUpcomingMoviesButtonClicked()
        fun onPopularMoviesButtonClicked()
    }
}