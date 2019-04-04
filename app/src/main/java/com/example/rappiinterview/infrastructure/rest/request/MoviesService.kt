package com.example.rappiinterview.infrastructure.rest.request

import com.example.rappiinterview.infrastructure.networking.services.responses.MoviesServiceResponse
import com.example.rappiinterview.infrastructure.networking.services.responses.SelectedMoviesServiceResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {
    companion object {
        /**
         * I use the API_VERSION_NUMBER as a constant because it's suppose not to change so often
         */
        private const val API_VERSION_NUMBER = 3
        const val BASE_URL = "https://api.themoviedb.org/$API_VERSION_NUMBER/"
        const val BASE_MOVIE_URL = "https://image.tmdb.org/t/p/w500/"
    }

    @GET("list/{list_id}")
    fun getMovies(
        @Path("list_id") listID: Int,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): Single<Response<MoviesServiceResponse>>

    @GET("movie/popular/")
    fun getPopularMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): Single<Response<SelectedMoviesServiceResponse>>

    @GET("movie/top_rated/")
    fun getTopRatedMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): Single<Response<SelectedMoviesServiceResponse>>

    @GET("movie/upcoming/")
    fun getUpcomingMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): Single<Response<SelectedMoviesServiceResponse>>

    @GET("search/movie/")
    fun getOnlineSearch(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("include_adult") includeAdult: Boolean
    ): Single<Response<SelectedMoviesServiceResponse>>
}