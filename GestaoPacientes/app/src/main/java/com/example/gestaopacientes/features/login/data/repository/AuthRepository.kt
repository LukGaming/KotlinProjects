package com.example.gestaopacientes.features.login.data.repository

interface AuthRepository {
    suspend fun login(email: String, password: String) : String
}