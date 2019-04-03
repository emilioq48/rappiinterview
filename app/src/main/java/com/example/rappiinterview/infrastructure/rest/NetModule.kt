package com.example.rappiinterview.infrastructure.rest

import com.example.rappiinterview.infrastructure.manager.implementation.RetrofitManagerImpl
import com.example.rappiinterview.infrastructure.manager.interfaces.RetrofitManager
import com.example.rappiinterview.infrastructure.rest.request.MoviesService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetModule {

    @Provides
    @Singleton
    fun provideRetrofitManager(gSon: Gson): RetrofitManager =
        RetrofitManagerImpl(gSon)

    @Provides
    fun providesMoviesService(retrofitManager: RetrofitManager): MoviesService =
        retrofitManager.getRetrofit(MoviesService.BASE_URL).create(
            MoviesService::class.java)

    @Provides
    fun providesGson(): Gson = GsonBuilder().create()
}