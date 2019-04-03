package com.example.rappiinterview.infrastructure.networking.interfaces

import com.example.rappiinterview.infrastructure.networking.RestConstants
import retrofit2.Retrofit

interface RetrofitManager {
    fun getCustomRetrofit(url: String = RestConstants.BASE_URL): Retrofit
    fun getRetrofit(url: String = RestConstants.BASE_URL): Retrofit
}