package com.example.rappiinterview.application

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Defines all the classes that need to be provided in the scope of the app.
 *
 * Define here all objects that are shared throughout the app, like SharedPreferences, navigators or
 * others. If some of those objects are singletons, they should be annotated with `@Singleton`.
 */
@Module
class AppModule {

    @Provides
    fun providesContext(application: RappiInterviewApplication): Context = application.applicationContext

    @Provides
    fun providesApplication(application: RappiInterviewApplication): Application = application
}