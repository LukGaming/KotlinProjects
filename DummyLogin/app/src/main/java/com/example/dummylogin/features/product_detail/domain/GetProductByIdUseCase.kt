package com.example.dummylogin.features.product_detail.domain

import com.example.dummylogin.features.homepage.data.repository.ProductRepository
import com.example.dummylogin.features.homepage.domain.Product

class GetProductByIdUseCase(private val repository: ProductRepository) {
    suspend operator fun invoke(id: Int): Result<Product> {
        return repository.getProductById(id)
    }
}