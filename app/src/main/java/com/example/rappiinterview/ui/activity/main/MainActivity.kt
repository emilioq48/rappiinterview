package com.example.rappiinterview.ui.activity.main

import android.os.Bundle
import com.example.rappiinterview.R
import com.example.rappiinterview.infrastructure.networking.services.responses.Item
import com.example.rappiinterview.ui.adapter.MoviesAdapter
import com.example.rappiinterview.ui.util.PopUpsUtils
import dagger.android.DaggerActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerActivity(), MainContract.View, MoviesAdapter.MovieClickListener {

    @Inject
    lateinit var presenter: MainContract.Presenter
    @Inject
    lateinit var popUpsUtils: PopUpsUtils
    @Inject
    lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.refreshMovies()
        moviesRV.adapter = moviesAdapter
        initListeners()
    }

    private fun initListeners() {
        popularMoviesButton.setOnClickListener {
            presenter.onPopularMoviesButtonClicked()
        }
        topRatedMoviesButton.setOnClickListener {
            presenter.onTopRatedMoviesButtonClicked()
        }
        upcomingMoviesButton.setOnClickListener {
            presenter.onUpcomingMoviesButtonClicked()
        }
        swypeToRefreshLayout.setOnRefreshListener {
            presenter.refreshMovies()
        }
    }

    override fun getDefaultErrorMessage(): String = getString(R.string.default_error_message)

    override fun showError(errorMessage: String) {
        popUpsUtils.showAlertDialog(this, errorMessage)
    }

    override fun updateItems(items: List<Item>?) {
        moviesAdapter.setItems(items)
        hideProgress()
    }

    override fun onMovieClicked(movie: Item) {
        presenter.onMovieClicked(movie)
    }

    override fun onStop() {
        presenter.onStop()
        super.onStop()
    }

    override fun showProgress() {
        swypeToRefreshLayout.isRefreshing = true
    }

    override fun hideProgress() {
        swypeToRefreshLayout.isRefreshing = false
    }
}
