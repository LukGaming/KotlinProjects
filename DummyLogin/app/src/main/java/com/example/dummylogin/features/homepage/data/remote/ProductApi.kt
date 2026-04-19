package com.example.dummylogin.features.homepage.data.remote

import com.example.dummylogin.features.homepage.data.models.ProductDto
import com.example.dummylogin.features.homepage.data.models.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {
    @GET("products")
    suspend fun getProducts(): ProductResponse

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): ProductDto
}