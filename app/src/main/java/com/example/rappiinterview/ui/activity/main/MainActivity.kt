package com.example.rappiinterview.ui.activity.main

import android.os.Bundle
import com.example.rappiinterview.R
import dagger.android.DaggerActivity
import javax.inject.Inject

class MainActivity : DaggerActivity(), MainContract.View {

    @Inject lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.init()
    }
}
