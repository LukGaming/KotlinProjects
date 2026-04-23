package com.gestao.pacientes.gestaopacientes.presentation.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.gestao.pacientes.gestaopacientes.domain.models.ApiResponse
import org.slf4j.LoggerFactory

private val logger = LoggerFactory.getLogger("HealthRoutes")

fun Route.healthRoutes() {
    route("/health") {
        get {
            logger.info("Health check solicitado")
            call.respond(ApiResponse<Unit>(
                success = true,
                message = "Servidor está operacional"
            ))
        }

        get("/status") {
            logger.info("Status check solicitado")
            val status = mapOf(
                "status" to "UP",
                "timestamp" to System.currentTimeMillis(),
                "service" to "Gestão de Pacientes API"
            )
            call.respond(status)
        }
    }
}
