package com.gestao.pacientes.gestaopacientes.config

import io.ktor.server.application.*
import org.slf4j.LoggerFactory

private val logger = LoggerFactory.getLogger("ApplicationLogger")

fun Application.configureLogging() {
    logger.info("====================================")
    logger.info("Gestão de Pacientes API iniciada")
    logger.info("Ambiente: ${environment.config.propertyOrNull("ktor.application.environment")?.getString() ?: "local"}")
    logger.info("Servidor rodando em: http://localhost:8080")
    logger.info("====================================")
}
