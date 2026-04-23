package com.gestao.pacientes.gestaopacientes.domain.services

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.config.*
import java.util.Date

class AuthService(config: ApplicationConfig) {
    private val issuer = config.propertyOrNull("jwt.issuer")?.getString() ?: "gestao-pacientes-api"
    private val audience = config.propertyOrNull("jwt.audience")?.getString() ?: "gestao-pacientes-clients"
    val realm = config.propertyOrNull("jwt.realm")?.getString() ?: "GestaoPacientesRealm"
    private val secret = config.propertyOrNull("jwt.secret")?.getString() ?: "dev-secret-change-me"
    private val expirationSeconds = config.propertyOrNull("jwt.expiresInSeconds")?.getString()?.toLongOrNull() ?: 3600L

    private val loginEmail = config.propertyOrNull("auth.login.email")?.getString() ?: "admin@gestao.local"
    private val loginSenha = config.propertyOrNull("auth.login.senha")?.getString() ?: "123456"

    private val algorithm = Algorithm.HMAC256(secret)

    val jwtVerifier: JWTVerifier = JWT.require(algorithm)
        .withIssuer(issuer)
        .withAudience(audience)
        .build()

    fun validateCredentials(email: String, senha: String): Boolean {
        return email == loginEmail && senha == loginSenha
    }

    fun generateToken(email: String): String {
        val now = System.currentTimeMillis()
        return JWT.create()
            .withIssuer(issuer)
            .withAudience(audience)
            .withClaim("email", email)
            .withIssuedAt(Date(now))
            .withExpiresAt(Date(now + expirationSeconds * 1000))
            .sign(algorithm)
    }
}
