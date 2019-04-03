package com.example.rappiinterview.infrastructure.manager.interfaces

import android.content.Context
import android.widget.ImageView
import androidx.annotation.DrawableRes

interface ImageManager {
    fun loadImage(context: Context, url: String?, @DrawableRes placeholder: Int, imageView: ImageView)
}