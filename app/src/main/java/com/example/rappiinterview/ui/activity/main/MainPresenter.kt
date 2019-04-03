package com.example.rappiinterview.ui.activity.main

import android.annotation.SuppressLint
import android.util.Log
import com.example.rappiinterview.domain.repository.interfaces.MoviesRepository
import com.example.rappiinterview.infrastructure.networking.RestConstants
import com.example.rappiinterview.infrastructure.networking.interfaces.MoviesManager
import com.example.rappiinterview.infrastructure.networking.services.responses.Item
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class MainPresenter(
    private val view: MainContract.View,
    private val moviesManager: MoviesManager,
    private val repository: MoviesRepository
) : MainContract.Presenter {

    companion object {
        private const val TAG = "MainPresenter"
    }

    private var compositeDisposable = CompositeDisposable()

    @SuppressLint("CheckResult")
    override fun refreshMovies() {
        moviesManager.getMovies(1, 1, RestConstants.API_KEY_v3)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                view.showProgress()
            }
            .doAfterTerminate {
                view.hideProgress()
            }
            .subscribe({ response ->
                if (response.isSuccessful) {
                    repository.clearRepository()
                    repository.saveMovies(response.body()?.items)
                } else {
                    handleErrorCase(response.code())
                }
                view.updateItems(repository.getMovies())
            }, {
                view.updateItems(repository.getMovies())
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

    override fun onPopularMoviesButtonClicked() {
        moviesManager.getPopularMovies(1, RestConstants.API_KEY_v3)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                view.showProgress()
            }
            .doAfterTerminate {
                view.hideProgress()
            }
            .subscribe({ response ->
                if (response.isSuccessful) {
                    repository.clearRepository()
                    repository.saveMovies(response.body()?.results)
                    view.updateItems(repository.getMovies())
                } else {
                    view.updateItems(repository.getMovies())
                    handleErrorCase(response.code())
                }
            }, {
                view.updateItems(repository.getMovies())
                view.showError(it.message ?: view.getDefaultErrorMessage())
                Log.d(TAG, it.message)
            }).addTo(compositeDisposable)
    }

    override fun onTopRatedMoviesButtonClicked() {
        moviesManager.getTopRatedMovies(1, RestConstants.API_KEY_v3)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                view.showProgress()
            }
            .doAfterTerminate {
                view.hideProgress()
            }
            .subscribe({ response ->
                if (response.isSuccessful) {
                    repository.clearRepository()
                    repository.saveMovies(response.body()?.results)
                    view.updateItems(repository.getMovies())
                } else {
                    view.updateItems(repository.getMovies())
                    handleErrorCase(response.code())
                }
            }, {
                view.updateItems(repository.getMovies())
                view.showError(it.message ?: view.getDefaultErrorMessage())
                Log.d(TAG, it.message)
            }).addTo(compositeDisposable)
    }

    override fun onUpcomingMoviesButtonClicked() {
        moviesManager.getUpcomingMovies(1, RestConstants.API_KEY_v3)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                view.showProgress()
            }
            .doAfterTerminate {
                view.hideProgress()
            }
            .subscribe({ response ->
                if (response.isSuccessful) {
                    repository.clearRepository()
                    repository.saveMovies(response.body()?.results)
                    view.updateItems(repository.getMovies())
                } else {
                    view.updateItems(repository.getMovies())
                    handleErrorCase(response.code())
                }
            }, {
                view.updateItems(repository.getMovies())
                view.showError(it.message ?: view.getDefaultErrorMessage())
                Log.d(TAG, it.message)
            }).addTo(compositeDisposable)
    }

    override fun onStop() {
        compositeDisposable.clear()
    }
}