package com.gestao.pacientes.gestaopacientes.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val email: String,
    val senha: String
)

@Serializable
data class LoginResponse(
    val token: String,
    val tokenType: String = "Bearer"
)
