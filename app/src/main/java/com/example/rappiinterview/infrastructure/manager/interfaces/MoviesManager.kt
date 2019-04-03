package com.example.rappiinterview.infrastructure.manager.interfaces

import com.example.rappiinterview.infrastructure.networking.services.responses.MoviesServiceResponse
import com.example.rappiinterview.infrastructure.networking.services.responses.SelectedMoviesServiceResponse
import io.reactivex.Single
import retrofit2.Response

interface MoviesManager{
    fun getMovies(listID: Int, page: Int, apiKey: String): Single<Response<MoviesServiceResponse>>
    fun getTopRatedMovies(page: Int, apiKey: String): Single<Response<SelectedMoviesServiceResponse>>
    fun getUpcomingMovies(page: Int, apiKey: String): Single<Response<SelectedMoviesServiceResponse>>
    fun getPopularMovies(page: Int, apiKey: String): Single<Response<SelectedMoviesServiceResponse>>
    fun getOnlineSearch(page: Int, apiKey: String, query: String): Single<Response<SelectedMoviesServiceResponse>>
}