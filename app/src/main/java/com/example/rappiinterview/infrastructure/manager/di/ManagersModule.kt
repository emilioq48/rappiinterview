package com.example.rappiinterview.infrastructure.manager.di

import com.example.rappiinterview.infrastructure.manager.implementation.GlideImageManagerImpl
import com.example.rappiinterview.domain.repository.implementation.MoviesRepositoryImpl
import com.example.rappiinterview.infrastructure.manager.interfaces.GlideImageManager
import com.example.rappiinterview.domain.repository.interfaces.MoviesRepository
import com.example.rappiinterview.infrastructure.networking.implementation.MoviesManagerImpl
import com.example.rappiinterview.infrastructure.networking.interfaces.MoviesManager
import com.example.rappiinterview.infrastructure.networking.services.interfaces.MoviesService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ManagersModule {

    @Provides
    fun providesMoviesManager(moviesService: MoviesService): MoviesManager =
        MoviesManagerImpl(moviesService)

    @Provides
    @Singleton
    fun providesImageManager(): GlideImageManager = GlideImageManagerImpl()

    @Provides
    fun providesMoviesRepository() : MoviesRepository =
        MoviesRepositoryImpl()
}