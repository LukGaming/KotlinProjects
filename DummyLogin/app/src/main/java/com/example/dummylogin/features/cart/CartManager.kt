package com.example.dummylogin.features.cart

import com.example.dummylogin.features.homepage.domain.Product

object CartManager {
    private val items = mutableListOf<CartItem>()

    fun addProduct(product: Product){
        val existing = items.find { it.productId == product.id }
        if(existing != null){
            existing.quantity++
        } else {
            items.add(
                CartItem(
                    productId = product.id,
                    title = product.title,
                    price = product.price,
                    quantity = 1
                )
            )
        }
    }

    fun getItems(): List<CartItem> = items

    fun clear(){
        items.clear()
    }

    fun getTotal(): Double{
        return items.sumOf { it.price * it.quantity }
    }
}