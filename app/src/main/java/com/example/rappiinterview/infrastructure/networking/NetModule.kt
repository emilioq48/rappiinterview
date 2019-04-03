package com.example.rappiinterview.infrastructure.networking

import com.example.rappiinterview.infrastructure.networking.implementation.RetrofitManagerImpl
import com.example.rappiinterview.infrastructure.networking.interfaces.RetrofitManager
import com.example.rappiinterview.infrastructure.networking.services.interfaces.MoviesService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetModule {

    @Provides
    @Singleton
    fun provideRetrofitManager(gSon: Gson): RetrofitManager = RetrofitManagerImpl(gSon)

    @Provides
    fun providesMoviesService(retrofitManager: RetrofitManager): MoviesService =
        retrofitManager.getRetrofit(MoviesService.BASE_URL).create(MoviesService::class.java)
}