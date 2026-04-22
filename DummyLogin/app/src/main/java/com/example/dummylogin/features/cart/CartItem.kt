package com.example.dummylogin.features.cart

data class CartItem(
    val productId: Int,
    val title: String,
    val price: Double,
    val thumbnail: String,
    var quantity: Int
)