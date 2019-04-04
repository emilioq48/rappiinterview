package com.example.rappiinterview.infrastructure.manager.implementation

import com.example.rappiinterview.infrastructure.manager.interfaces.MoviesManager
import com.example.rappiinterview.infrastructure.networking.services.responses.MoviesServiceResponse
import com.example.rappiinterview.infrastructure.networking.services.responses.SelectedMoviesServiceResponse
import com.example.rappiinterview.infrastructure.rest.request.MoviesService
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class MoviesManagerImpl @Inject constructor(private val moviesService: MoviesService) :
    MoviesManager {

    override fun getMovies(listID: Int, page: Int, apiKey: String): Single<Response<MoviesServiceResponse>> =
        moviesService.getMovies(listID, page, apiKey)
            .subscribeOn(Schedulers.io())

    override fun getPopularMovies(page: Int, apiKey: String): Single<Response<SelectedMoviesServiceResponse>> =
        moviesService.getPopularMovies(page, apiKey)
            .subscribeOn(Schedulers.io())

    override fun getTopRatedMovies(page: Int, apiKey: String): Single<Response<SelectedMoviesServiceResponse>> =
        moviesService.getTopRatedMovies(page, apiKey)
            .subscribeOn(Schedulers.io())

    override fun getUpcomingMovies(page: Int, apiKey: String): Single<Response<SelectedMoviesServiceResponse>> =
        moviesService.getUpcomingMovies(page, apiKey)
            .subscribeOn(Schedulers.io())

    override fun getOnlineSearch(
        page: Int,
        apiKey: String,
        query: String
    ): Single<Response<SelectedMoviesServiceResponse>> =
        moviesService.getOnlineSearch(page, apiKey, query, false)
            .subscribeOn(Schedulers.io())
}