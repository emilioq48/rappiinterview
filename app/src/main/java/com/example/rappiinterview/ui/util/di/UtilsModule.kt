package com.example.rappiinterview.ui.util.di

import com.example.rappiinterview.domain.repository.MovieCategoryUtils
import com.example.rappiinterview.infrastructure.manager.interfaces.GlideImageManager
import com.example.rappiinterview.ui.util.PopUpsUtils
import dagger.Module
import dagger.Provides

@Module
class UtilsModule {

    @Provides
    fun providesPopUpsUtils(imageManager: GlideImageManager): PopUpsUtils = PopUpsUtils(imageManager)

    @Provides
    fun provideMovieCategoryUtils() = MovieCategoryUtils()
}