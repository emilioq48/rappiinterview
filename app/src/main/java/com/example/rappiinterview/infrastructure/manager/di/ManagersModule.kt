package com.example.rappiinterview.infrastructure.manager.di

import com.example.rappiinterview.infrastructure.util.MovieCategoryUtils
import com.example.rappiinterview.infrastructure.repository.implementation.MoviesRepositoryImpl
import com.example.rappiinterview.infrastructure.repository.interfaces.MoviesRepository
import com.example.rappiinterview.infrastructure.manager.implementation.ImageManagerImpl
import com.example.rappiinterview.infrastructure.manager.interfaces.ImageManager
import com.example.rappiinterview.infrastructure.manager.implementation.MoviesManagerImpl
import com.example.rappiinterview.infrastructure.manager.interfaces.MoviesManager
import com.example.rappiinterview.infrastructure.rest.request.MoviesService
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
    fun providesImageManager(): ImageManager = ImageManagerImpl()

    @Provides
    fun providesMoviesRepository(movieCategoryUtils: MovieCategoryUtils): MoviesRepository =
        MoviesRepositoryImpl(movieCategoryUtils)
}