package com.example.rappiinterview.infrastructure.manager.interfaces

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import androidx.annotation.DrawableRes
import io.reactivex.Single

interface GlideImageManager {
    fun loadImage(context: Context, url: String?, @DrawableRes placeholder: Int, imageView: ImageView)
    fun loadBitmap(context: Context, url: String?): Single<Bitmap>
}
