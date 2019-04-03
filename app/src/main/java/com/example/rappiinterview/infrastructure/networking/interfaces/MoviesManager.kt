package com.example.rappiinterview.infrastructure.networking.interfaces

import com.example.rappiinterview.infrastructure.networking.services.responses.MoviesServiceResponse
import com.example.rappiinterview.infrastructure.networking.services.responses.TopRatedMoviesServiceResponse
import io.reactivex.Single
import retrofit2.Response

interface MoviesManager{
    fun getMovies(listID: Int, page: Int, apiKey: String): Single<Response<MoviesServiceResponse>>
    fun getTopRatedMovies(page: Int, apiKey: String): Single<Response<TopRatedMoviesServiceResponse>>
    fun getUpcomingMovies(page: Int, apiKey: String): Single<Response<TopRatedMoviesServiceResponse>>
    fun getPopularMovies(page: Int, apiKey: String): Single<Response<TopRatedMoviesServiceResponse>>
}