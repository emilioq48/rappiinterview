package com.example.rappiinterview.infrastructure.networking

import android.app.Application
import com.example.rappiinterview.infrastructure.networking.implementation.MoviesManagerImpl
import com.example.rappiinterview.infrastructure.networking.implementation.RetrofitManagerImpl
import com.example.rappiinterview.infrastructure.networking.interfaces.MoviesManager
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
    fun provideRetrofitManager(
        application: Application,
        gSon: Gson
    ): RetrofitManager = RetrofitManagerImpl(application, gSon)

    @Provides
    fun providesMoviesService(retrofitManager: RetrofitManager): MoviesService {
        return retrofitManager.getRetrofit(MoviesService.BASE_URL).create(MoviesService::class.java)
    }
}