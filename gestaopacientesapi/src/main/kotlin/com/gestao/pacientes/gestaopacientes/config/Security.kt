package com.gestao.pacientes.gestaopacientes.config

import com.gestao.pacientes.gestaopacientes.domain.services.AuthService
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*

fun Application.configureSecurity(): AuthService {
    val authService = AuthService(environment.config)

    install(Authentication) {
        jwt("auth-jwt") {
            realm = authService.realm
            verifier(authService.jwtVerifier)

            validate { credential ->
                val email = credential.payload.getClaim("email").asString()
                if (email.isNullOrBlank()) null else JWTPrincipal(credential.payload)
            }

            challenge { _, _ ->
                call.respond(
                    io.ktor.http.HttpStatusCode.Unauthorized,
                    mapOf("message" to "Token JWT inválido ou ausente")
                )
            }
        }
    }

    return authService
}
