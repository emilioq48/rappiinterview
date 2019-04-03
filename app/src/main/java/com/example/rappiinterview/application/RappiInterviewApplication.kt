package com.example.rappiinterview.application

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.realm.Realm
import io.realm.RealmConfiguration

class RappiInterviewApplication : DaggerApplication() {

    companion object {
        private const val DATABASE_NAME = "moviesdb.realm"
    }

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val config = RealmConfiguration.Builder().name(DATABASE_NAME).build()
        Realm.setDefaultConfiguration(config)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}