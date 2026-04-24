package com.example.gestaopacientes.core.network

import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://192.168.1.106:8080/"

    private val client = OkHttpClient.Builder()
        .addInterceptor ( HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        } ).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    fun <T> create(service: Class<T>) : T {
        return retrofit.create(service)
    }
}