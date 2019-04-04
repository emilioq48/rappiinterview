package com.example.rappiinterview.ui.activity.main

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import com.bitvale.lavafab.Child
import com.example.rappiinterview.R
import com.example.rappiinterview.domain.model.Item
import com.example.rappiinterview.ui.adapter.MoviesAdapter
import com.example.rappiinterview.ui.extensions.hideSoftKeyboard
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
        initUI()
    }

    private fun initUI() {
        initListeners()
        with(lavaFAB) {
            setLavaBackgroundResColor(android.R.color.white)
            setChildIcon(Child.LEFT, R.drawable.ic_popular)
            setChildIcon(Child.LEFT_TOP, R.drawable.ic_top_rated)
            setChildIcon(Child.TOP, R.drawable.ic_upcoming_movies)

            setParentOnClickListener { lavaFAB.trigger() }
            setChildOnClickListener(Child.LEFT) { presenter.onPopularFABClicked() }
            setChildOnClickListener(Child.LEFT_TOP) { presenter.onTopRatedFABClicked() }
            setChildOnClickListener(Child.TOP) { presenter.onUpcomingFABClicked() }

            enableShadow()
            setParentIcon(com.example.rappiinterview.R.drawable.ic_filter)
        }
    }

    private fun initListeners() {
        popularMoviesButton.setOnClickListener { presenter.onPopularMoviesButtonClicked() }
        topRatedMoviesButton.setOnClickListener { presenter.onTopRatedMoviesButtonClicked() }
        upcomingMoviesButton.setOnClickListener { presenter.onUpcomingMoviesButtonClicked() }
        swypeToRefreshLayout.setOnRefreshListener { presenter.refreshMovies() }
        searchImage.setOnClickListener { presenter.onSearchIconClicked(searchText.text.toString()) }
        searchText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                presenter.onSearchIconClicked(searchText.text.toString())
            }
            true
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

    override fun onMovieLongClicked(movie: Item) {
        presenter.onMovieLongClicked(movie)
    }

    override fun onStop() {
        presenter.onStop()
        super.onStop()
    }

    override fun showProgress() {
        swypeToRefreshLayout.isRefreshing = true
    }

    override fun showOnMovieClickedMessage(movie: Item) {
        Toast.makeText(this, getString(R.string.i_love_rappi), Toast.LENGTH_SHORT).show()
    }

    override fun hideProgress() {
        swypeToRefreshLayout.isRefreshing = false
    }

    override fun showMovieDetail(movie: Item) {
        popUpsUtils.showMovieDetailPopUp(this, movie)
    }

    override fun showNoItemsFound() {
        nothingFoundGroup.visibility = View.VISIBLE
        moviesRV.visibility = View.GONE
    }

    override fun hideNoItemsFound() {
        nothingFoundGroup.visibility = View.GONE
        moviesRV.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun hideKeyboard() {
        hideSoftKeyboard()
    }
}
