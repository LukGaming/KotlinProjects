package com.gestao.pacientes.gestaopacientes

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.defaultheaders.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import org.slf4j.event.Level
import com.gestao.pacientes.gestaopacientes.config.configureRouting
import com.gestao.pacientes.gestaopacientes.config.configureLogging

fun main() {
    embeddedServer(
        Netty,
        port = 8080,
        host = "0.0.0.0",
        module = Application::configureServer
    ).start(wait = true)
}

fun Application.configureServer() {
    // Configurar headers padrão
    install(DefaultHeaders) {
        header("X-Engine", "Ktor")
    }

    // Configurar logging de requisições
    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }

    // Configurar serialização JSON
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            ignoreUnknownKeys = true
        })
    }

    // Log da inicialização
    configureLogging()

    // Configurar rotas
    configureRouting()
}
