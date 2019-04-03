package com.example.rappiinterview.ui.util.di

import com.example.rappiinterview.infrastructure.util.MovieCategoryUtils
import com.example.rappiinterview.infrastructure.manager.interfaces.ImageManager
import com.example.rappiinterview.ui.util.PopUpsUtils
import dagger.Module
import dagger.Provides

@Module
class UtilsModule {

    @Provides
    fun providesPopUpsUtils(imageManager: ImageManager): PopUpsUtils = PopUpsUtils(imageManager)

    @Provides
    fun provideMovieCategoryUtils() = MovieCategoryUtils()
}