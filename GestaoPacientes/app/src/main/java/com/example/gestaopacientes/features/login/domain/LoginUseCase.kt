package com.example.gestaopacientes.features.login.domain

import com.example.gestaopacientes.features.login.data.repository.AuthRepository

class LoginUseCase(private val repository: AuthRepository) {
    suspend fun execute(email: String, password: String): String{
        return repository.login(email, password)
    }
}