package com.example.rappiinterview.ui.util.di

import com.example.rappiinterview.ui.util.PopUpsUtils
import dagger.Module
import dagger.Provides

@Module
class UtilsModule {

    @Provides
    fun providesPopUpsUtils(): PopUpsUtils = PopUpsUtils()
}