package com.example.dummylogin.features.auth.data.model

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val id: Int,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val image: String
)