package com.example.rappiinterview.infrastructure.manager.interfaces

import com.example.rappiinterview.infrastructure.rest.RestConstants
import retrofit2.Retrofit

interface RetrofitManager {
    fun getRetrofit(url: String = RestConstants.BASE_URL): Retrofit
}