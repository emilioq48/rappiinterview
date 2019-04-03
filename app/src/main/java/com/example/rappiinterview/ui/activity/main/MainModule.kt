package com.example.rappiinterview.ui.activity.main

import android.app.Activity
import com.example.rappiinterview.domain.repository.interfaces.MoviesRepository
import com.example.rappiinterview.infrastructure.networking.interfaces.MoviesManager
import com.example.rappiinterview.ui.adapter.MoviesAdapter
import com.example.rappiinterview.ui.util.di.ActivityScoped
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class MainModule {

    @Binds
    @ActivityScoped
    abstract fun provideView(mainActivity: MainActivity): MainContract.View

    @Binds
    @ActivityScoped
    abstract fun provideActivity(activity: MainActivity): Activity

    /**
     * The method annotated with `@Provides` needs an instance, so we are making it static
     *
     * Reference: https://google.github.io/dagger/faq.html#why-cant-binds-and-instance-provides-methods-go-in-the-same-module
     */
    @Module
    companion object {
        @JvmStatic
        @Provides
        @ActivityScoped
        fun providePresenter(
            mainView: MainContract.View,
            moviesManager: MoviesManager,
            moviesRepository: MoviesRepository
        ): MainContract.Presenter {
            return MainPresenter(mainView, moviesManager, moviesRepository)
        }

        @JvmStatic
        @Provides
        @ActivityScoped
        fun provideMovieClickListener(activity: MainActivity): MoviesAdapter.MovieClickListener = activity

    }
}