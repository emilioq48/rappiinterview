package com.example.rappiinterview.infrastructure.manager.implementation

import android.content.Context
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.example.rappiinterview.infrastructure.manager.interfaces.ImageManager
import com.example.rappiinterview.ui.util.di.GlideApp

class ImageManagerImpl : ImageManager {

    override fun loadImage(context: Context, url: String?, @DrawableRes placeholder: Int, imageView: ImageView) {
        GlideApp.with(context)
            .load(url)
            .placeholder(placeholder)
            .into(imageView)
    }
}