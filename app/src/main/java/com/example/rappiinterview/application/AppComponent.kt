package com.example.rappiinterview.application

import com.bumptech.glide.annotation.GlideModule
import com.example.rappiinterview.infrastructure.manager.di.ManagersModule
import com.example.rappiinterview.infrastructure.networking.NetModule
import com.example.rappiinterview.ui.util.di.GlideApp
import com.example.rappiinterview.ui.util.di.RappiInterviewGlideModule
import com.example.rappiinterview.ui.util.di.UtilsModule
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
        NetModule::class,
        AppModule::class,
        ManagersModule::class,
        UtilsModule::class]
)
interface AppComponent : AndroidInjector<RappiInterviewApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<RappiInterviewApplication>()
}