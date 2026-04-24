package com.example.gestaopacientes.features.login.data.repository

import com.example.gestaopacientes.features.login.data.dto.LoginRequest
import com.example.gestaopacientes.features.login.data.remote.AuthApi

class AuthRepositoryImpl(private val authApi: AuthApi): AuthRepository {
    override suspend fun login(email: String, password: String): String {
        val request = LoginRequest(email, password)
        val response = authApi.login(request);
        return response.data.token;
    }
}