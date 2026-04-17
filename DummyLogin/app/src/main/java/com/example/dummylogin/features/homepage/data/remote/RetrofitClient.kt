package com.example.dummylogin.features.homepage.data.remote

object RetrofitClient {
    private const val BASE_URL = "https://dummyjson.com/"

    val api: ProductApi by lazy {
        retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
            .build()
            .create(ProductApi::class.java)
    }
}