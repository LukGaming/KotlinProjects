package com.example.dummylogin.features.homepage.data.repository

import com.example.dummylogin.features.homepage.data.models.ProductDto
import com.example.dummylogin.features.homepage.data.remote.ProductApi
import com.example.dummylogin.features.homepage.domain.Product

class ProductRepositoryImpl(private val api: ProductApi): ProductRepository {
    override suspend fun getProduts(): Result<List<Product>> {
        return try {
        val response = api.getProducts();
            val products = response.products.map { dto: ProductDto ->
                dto.toDomain()
            }

            Result.success(products)
        } catch (e: Exception){
            Result.failure(e)
        }
    }

}