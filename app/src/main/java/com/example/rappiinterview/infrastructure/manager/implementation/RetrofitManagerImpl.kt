package com.example.rappiinterview.infrastructure.manager.implementation

import com.example.rappiinterview.infrastructure.manager.interfaces.RetrofitManager
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitManagerImpl(
    private val mGson: Gson
) : RetrofitManager {
    private var mRetrofit: Retrofit? = null
    private lateinit var mDefaultOkHttpClient: OkHttpClient

    companion object {
        private const val TAG = "RetrofitManager"
    }

    override fun getRetrofit(url: String): Retrofit {
        if (mRetrofit == null) {
            val logging = HttpLoggingInterceptor()
            logging.level = Level.BODY

            val httpClient = OkHttpClient.Builder()
                .addInterceptor(logging)

            mDefaultOkHttpClient = httpClient.build()

            mRetrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mDefaultOkHttpClient)
                .build()
        }
        return mRetrofit!!
    }
}
