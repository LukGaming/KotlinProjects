package com.example.dummylogin.features.homepage.data.remote

import com.example.dummylogin.features.homepage.data.models.ProductResponse
import retrofit2.http.GET

interface ProductApi {
    @GET("products")
    suspend fun getProducts(): ProductResponse
}