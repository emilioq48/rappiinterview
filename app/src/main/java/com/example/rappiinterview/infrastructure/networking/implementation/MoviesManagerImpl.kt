package com.example.rappiinterview.infrastructure.networking.implementation

import com.example.rappiinterview.infrastructure.networking.interfaces.MoviesManager
import com.example.rappiinterview.infrastructure.networking.services.interfaces.MoviesService
import com.example.rappiinterview.infrastructure.networking.services.responses.MoviesServiceResponse
import com.example.rappiinterview.infrastructure.networking.services.responses.SelectedMoviesServiceResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class MoviesManagerImpl @Inject constructor(private val moviesService: MoviesService) : MoviesManager {

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
}