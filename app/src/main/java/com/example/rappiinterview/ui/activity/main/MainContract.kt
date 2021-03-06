package com.example.rappiinterview.ui.activity.main

import com.example.rappiinterview.domain.model.Item

interface MainContract {
    interface View {
        fun showError(errorMessage: String)
        fun getDefaultErrorMessage(): String
        fun updateItems(items: List<Item>?)
        fun hideProgress()
        fun showProgress()
        fun showOnMovieClickedMessage(movie: Item)
        fun showMovieDetail(movie: Item)
        fun showNoItemsFound()
        fun hideNoItemsFound()
        fun hideKeyboard()
    }

    interface Presenter {
        fun refreshMovies()
        fun onStop()
        fun onMovieClicked(movie: Item)
        fun onTopRatedMoviesButtonClicked()
        fun onUpcomingMoviesButtonClicked()
        fun onPopularMoviesButtonClicked()
        fun onDestroy()
        fun onPopularFABClicked()
        fun onTopRatedFABClicked()
        fun onUpcomingFABClicked()
        fun onMovieLongClicked(movie: Item)
        fun onSearchIconClicked(query: String)
    }
}