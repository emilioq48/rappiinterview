package com.example.rappiinterview.infrastructure.networking.implementation

import android.app.Application
import android.util.Log
import com.example.rappiinterview.infrastructure.networking.RestConstants
import com.example.rappiinterview.infrastructure.networking.interfaces.RetrofitManager
import com.google.gson.Gson
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

class RetrofitManagerImpl(
    private val mApplication: Application,
    private val mGson: Gson
) : RetrofitManager {
    private var mCustomRetrofit: Retrofit? = null
    private var mCustomCachedRetrofit: Retrofit? = null
    private var mRetrofit: Retrofit? = null
    private var mCustomOkHttpClient: OkHttpClient? = null
    private var mCustomCachedOkHttpClient: OkHttpClient? = null
    private var mDefaultOkHttpClient: OkHttpClient? = null
    private var mCache: Cache? = null

    companion object {
        const val TAG = "RetrofitManager"
    }

    /**
     * Returns a Custom Retrofit instance.
     */
    // set your desired log level
    override fun getCustomRetrofit(url: String): Retrofit {
        if (mCustomRetrofit == null) {
            val logging = HttpLoggingInterceptor()
            logging.level = Level.BODY

            val httpClient = OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(provideHeaderInterceptor())
//                .cache(provideCache())
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)

            mCustomOkHttpClient = httpClient.build()

            mCustomRetrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .client(mCustomOkHttpClient!!)
                .build()
        }

        return mCustomRetrofit!!
    }

    /**
     * Returns a Clean Retrofit instance.
     */
    // set your desired log level
    override fun getRetrofit(url: String): Retrofit {
        if (mRetrofit == null) {
            val logging = HttpLoggingInterceptor()
            logging.level = Level.BODY

            val httpClient = OkHttpClient.Builder()
                .addInterceptor(logging)
//                .cache(provideCache())

            mDefaultOkHttpClient = httpClient.build()

            mRetrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mDefaultOkHttpClient!!)
                .build()
        }
        return mRetrofit!!
    }

    private fun provideCache(): Cache? {
        if (mCache == null) {
            try {
                mCache = Cache(
                    File(mApplication.cacheDir, "http-cache"),
                    (10 * 1024 * 1024).toLong()
                ) // 10 MB
            } catch (e: Exception) {
                Log.e(TAG, "Could not create Cache!")
            }
        }
        return mCache
    }

    private fun provideHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()

            val requestBuilder = request.newBuilder()
                .header(RestConstants.HEADER_AUTH, "")

            request = requestBuilder.build()

            chain.proceed(request)
        }
    }
}
