package com.gestao.pacientes.gestaopacientes.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Paciente(
    val id: Long,
    val nome: String,
    val email: String,
    val telefone: String,
    val dataNascimento: String
)

@Serializable
data class CreatePacienteRequest(
    val nome: String,
    val email: String,
    val telefone: String,
    val dataNascimento: String
)

@Serializable
data class ApiResponse<T>(
    val success: Boolean,
    val message: String,
    val data: T? = null
)
