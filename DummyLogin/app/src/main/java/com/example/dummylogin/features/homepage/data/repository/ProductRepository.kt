package com.example.dummylogin.features.homepage.data.repository

import com.example.dummylogin.features.homepage.domain.Product

interface ProductRepository {
    suspend fun getProduts(): Result<List<Product>>

    suspend fun getProductById(id: Int): Result<Product>
}