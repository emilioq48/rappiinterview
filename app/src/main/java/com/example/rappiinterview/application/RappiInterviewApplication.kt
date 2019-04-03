package com.example.rappiinterview.application

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.realm.Realm
import io.realm.RealmConfiguration




class RappiInterviewApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        Realm.init(this)
        val config = RealmConfiguration.Builder().name("moviesdb.realm").build()
        Realm.setDefaultConfiguration(config)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}