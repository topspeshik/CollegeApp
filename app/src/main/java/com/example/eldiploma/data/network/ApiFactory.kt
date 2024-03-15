package com.example.eldiploma.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {
    private const val BASE_URL = "http://158.160.37.138/api/v1/"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor{ chain->
            val originalRequest = chain.request()
            val request = originalRequest.newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("X-Api-Key", "c8497eae2175fd4fb5b25e371ec9a6b0")
                .build()
            chain.proceed(request)
        }.build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    val apiService = retrofit.create(ApiService::class.java)
}