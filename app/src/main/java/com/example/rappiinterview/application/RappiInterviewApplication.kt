package com.example.rappiinterview.application

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class RappiInterviewApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}