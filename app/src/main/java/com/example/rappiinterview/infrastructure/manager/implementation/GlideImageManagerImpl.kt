package com.example.rappiinterview.infrastructure.manager.implementation

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.rappiinterview.R
import com.example.rappiinterview.infrastructure.manager.interfaces.GlideImageManager
import com.example.rappiinterview.ui.util.di.GlideApp
import io.reactivex.Single
import io.reactivex.SingleOnSubscribe
import io.reactivex.schedulers.Schedulers

class GlideImageManagerImpl : GlideImageManager {

    override fun loadImage(context: Context, url: String?, @DrawableRes placeholder: Int, imageView: ImageView) {
        GlideApp.with(context)
            .load(url)
            .placeholder(placeholder)
            .into(imageView)
    }

    override fun loadBitmap(context: Context, url: String?): Single<Bitmap> {
        return Single.create(SingleOnSubscribe<Bitmap> { emitter ->
            GlideApp.with(context)
                .asBitmap()
                .load(url)
                .listener(object : RequestListener<Bitmap> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        // If the load fails return the placeholder as Bitmap
                        emitter.onSuccess(
                            BitmapFactory.decodeResource(context.resources,
                                R.drawable.movie_place_holder))
                        return true
                    }

                    override fun onResourceReady(
                        resource: Bitmap?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        emitter.onSuccess(resource
                            ?: BitmapFactory.decodeResource(context.resources,
                                R.drawable.movie_place_holder))
                        return true
                    }
                })
                .submit()
        }).subscribeOn(Schedulers.io())
    }
}