package com.gestao.pacientes.gestaopacientes.config

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.gestao.pacientes.gestaopacientes.presentation.routes.healthRoutes
import com.gestao.pacientes.gestaopacientes.presentation.routes.pacientesRoutes

fun Application.configureRouting() {
    routing {
        // Rotas de saúde
        healthRoutes()
        
        // Rotas de pacientes
        pacientesRoutes()
    }
}
