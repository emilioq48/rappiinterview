package com.example.rappiinterview.ui.activity.main

import android.annotation.SuppressLint
import android.util.Log
import com.example.rappiinterview.domain.repository.MovieCategory.*
import com.example.rappiinterview.domain.repository.MovieCategoryUtils
import com.example.rappiinterview.domain.repository.interfaces.MoviesRepository
import com.example.rappiinterview.infrastructure.networking.RestConstants.API_KEY_V3
import com.example.rappiinterview.infrastructure.networking.interfaces.MoviesManager
import com.example.rappiinterview.infrastructure.networking.services.responses.Item
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class MainPresenter(
    private val view: MainContract.View,
    private val moviesManager: MoviesManager,
    private val repository: MoviesRepository,
    private val movieCategoryUtils: MovieCategoryUtils
) : MainContract.Presenter {

    companion object {
        private const val TAG = "MainPresenter"
    }

    private var compositeDisposable = CompositeDisposable()

    @SuppressLint("CheckResult")
    override fun refreshMovies() {
        moviesManager.getMovies(1, 1, API_KEY_V3)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                view.showProgress()
            }
            .doAfterTerminate {
                view.hideProgress()
            }
            .subscribe({ response ->
                if (response.isSuccessful) {
                    repository.saveMovies(response.body()?.items)
                } else {
                    handleErrorCase(response.code())
                }
                updateItems(repository.getMovies())
            }, {
                updateItems(repository.getMovies())
                view.showError(it.message ?: view.getDefaultErrorMessage())
                Log.d(TAG, it.message)
            }).addTo(compositeDisposable)
    }

    private fun handleErrorCase(errorCode: Int) {
        when (errorCode) {
            // Here you could show a proper PopUp error for each error case
            401 -> view.showError(view.getDefaultErrorMessage())
            404 -> view.showError(view.getDefaultErrorMessage())
            500 -> view.showError(view.getDefaultErrorMessage())
            else -> view.showError(view.getDefaultErrorMessage())
        }
    }

    override fun onMovieClicked(movie: Item) {
        view.showOnMovieClickedMessage(movie)
    }

    override fun onMovieLongClicked(movie: Item) {
        view.showMovieDetail(movie)
    }

    override fun onPopularMoviesButtonClicked() {
        moviesManager.getPopularMovies(1, API_KEY_V3)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                view.showProgress()
            }
            .doAfterTerminate {
                view.hideProgress()
            }
            .subscribe({ response ->
                if (response.isSuccessful) {
                    repository.saveMoviesWithCategory(
                        response.body()?.results,
                        movieCategoryUtils.getCategory(POPULAR)
                    )
                    updateItems(repository.getMoviesWithCategory(POPULAR))
                } else {
                    updateItems(repository.getMoviesWithCategory(POPULAR))
                    handleErrorCase(response.code())
                }
            }, {
                updateItems(repository.getMoviesWithCategory(POPULAR))
                view.showError(it.message ?: view.getDefaultErrorMessage())
                Log.d(TAG, it.message)
            }).addTo(compositeDisposable)
    }

    override fun onTopRatedMoviesButtonClicked() {
        moviesManager.getTopRatedMovies(1, API_KEY_V3)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                view.showProgress()
            }
            .doAfterTerminate {
                view.hideProgress()
            }
            .subscribe({ response ->
                if (response.isSuccessful) {
                    repository.saveMoviesWithCategory(
                        response.body()?.results,
                        movieCategoryUtils.getCategory(TOP_RATED)
                    )
                    updateItems(repository.getMoviesWithCategory(TOP_RATED))
                } else {
                    updateItems(repository.getMoviesWithCategory(TOP_RATED))
                    handleErrorCase(response.code())
                }
            }, {
                updateItems(repository.getMoviesWithCategory(TOP_RATED))
                view.showError(it.message ?: view.getDefaultErrorMessage())
                Log.d(TAG, it.message)
            }).addTo(compositeDisposable)
    }

    override fun onUpcomingMoviesButtonClicked() {
        moviesManager.getUpcomingMovies(1, API_KEY_V3)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                view.showProgress()
            }
            .doAfterTerminate {
                view.hideProgress()
            }
            .subscribe({ response ->
                if (response.isSuccessful) {
                    repository.saveMoviesWithCategory(
                        response.body()?.results,
                        movieCategoryUtils.getCategory(UPCOMING)
                    )
                    updateItems(repository.getMoviesWithCategory(UPCOMING))
                } else {
                    updateItems(repository.getMoviesWithCategory(UPCOMING))
                    handleErrorCase(response.code())
                }
            }, {
                updateItems(repository.getMoviesWithCategory(UPCOMING))
                view.showError(it.message ?: view.getDefaultErrorMessage())
                Log.d(TAG, it.message)
            }).addTo(compositeDisposable)
    }

    private fun updateItems(items: List<Item>?) {
        if (items.isNullOrEmpty()) {
            view.showNoItemsFound()
            return
        }
        view.hideNoItemsFound()
        view.updateItems(items)
    }

    override fun onDestroy() {
        repository.close()
    }

    override fun onPopularFABClicked() {
        updateItems(repository.getMoviesWithCategory(POPULAR))
    }

    override fun onTopRatedFABClicked() {
        updateItems(repository.getMoviesWithCategory(TOP_RATED))
    }

    override fun onUpcomingFABClicked() {
        updateItems(repository.getMoviesWithCategory(UPCOMING))
    }

    override fun onStop() {
        compositeDisposable.clear()
    }

    override fun onSearchIconClicked(query: String) {
        if (query.isBlank()) {
            return
        }
        moviesManager.getOnlineSearch(1, API_KEY_V3, query)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                view.showProgress()
            }
            .doAfterTerminate {
                view.hideProgress()
                view.hideKeyboard()
            }
            .subscribe({ response ->
                if (response.isSuccessful) {
                    updateItems(response.body()?.results)
                } else {
                    handleErrorCase(response.code())
                }
            }, {
                view.showError(it.message ?: view.getDefaultErrorMessage())
                Log.d(TAG, it.message)
            }).addTo(compositeDisposable)
    }
}