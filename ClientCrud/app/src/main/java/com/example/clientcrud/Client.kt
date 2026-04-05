package com.example.clientcrud

import java.io.Serializable

data class Client(
    val name: String,
    val age: Int
) : Serializable;