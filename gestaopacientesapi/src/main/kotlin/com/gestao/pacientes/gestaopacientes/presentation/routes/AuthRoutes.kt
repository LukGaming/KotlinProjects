package com.gestao.pacientes.gestaopacientes.presentation.routes

import com.gestao.pacientes.gestaopacientes.domain.models.ApiResponse
import com.gestao.pacientes.gestaopacientes.domain.models.LoginRequest
import com.gestao.pacientes.gestaopacientes.domain.models.LoginResponse
import com.gestao.pacientes.gestaopacientes.domain.services.AuthService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.authRoutes(authService: AuthService) {
    post("/login") {
        val request = try {
            call.receive<LoginRequest>()
        } catch (e: Exception) {
            call.respond(
                HttpStatusCode.BadRequest,
                ApiResponse<Unit>(success = false, message = "Payload de login inválido")
            )
            return@post
        }

        if (!authService.validateCredentials(request.email, request.senha)) {
            call.respond(
                HttpStatusCode.Unauthorized,
                ApiResponse<Unit>(success = false, message = "Email ou senha inválidos")
            )
            return@post
        }

        val token = authService.generateToken(request.email)
        call.respond(
            HttpStatusCode.OK,
            ApiResponse(
                success = true,
                message = "Login realizado com sucesso",
                data = LoginResponse(token = token)
            )
        )
    }
}
