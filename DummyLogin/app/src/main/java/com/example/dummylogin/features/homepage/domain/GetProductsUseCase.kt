package com.example.dummylogin.features.homepage.domain

import com.example.dummylogin.features.homepage.data.repository.ProductRepository
import kotlinx.coroutines.delay

class GetProductsUseCase(private val repository: ProductRepository) {
    suspend operator fun invoke(): Result<List<Product>> {
        return repository.getProduts()
    }
}