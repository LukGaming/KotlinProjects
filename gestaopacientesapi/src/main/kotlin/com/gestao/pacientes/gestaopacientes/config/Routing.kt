package com.gestao.pacientes.gestaopacientes.config

import com.gestao.pacientes.gestaopacientes.domain.services.AuthService
import com.gestao.pacientes.gestaopacientes.presentation.routes.authRoutes
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*
import com.gestao.pacientes.gestaopacientes.presentation.routes.healthRoutes
import com.gestao.pacientes.gestaopacientes.presentation.routes.pacientesRoutes

fun Application.configureRouting(authService: AuthService) {
    routing {
        // Rotas de saúde
        healthRoutes()

        // Rota pública de autenticação
        authRoutes(authService)

        // Rotas protegidas por JWT
        authenticate("auth-jwt") {
            pacientesRoutes()
        }
    }
}
