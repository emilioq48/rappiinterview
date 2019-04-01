package com.example.rappiinterview.application

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * Main component of the app, created and persisted in the Application class.
 *
 * Whenever a new module is created, it should be added to the list of modules.
 * [AndroidInjectionModule] is the module from Dagger.Android that helps with the
 * generation and location of subcomponents.
 */
@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        ActivityBindingModule::class,
        AppModule::class]
)
interface AppComponent : AndroidInjector<RappiInterviewApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<RappiInterviewApplication>()
}