package com.example.dummylogin.features.homepage.data.models

import com.example.dummylogin.features.homepage.domain.Product

data class ProductDto(
    val id: Int,
    val title: String,
    val price: Double,
    val rating: Double,
    val thumbnail: String,
    val description: String
) {
    fun toDomain(): Product {
        return Product(
            id = id,
            title = title,
            price = price,
            rating = rating,
            thumbnail = thumbnail,
            description = description
        )
    }
}
