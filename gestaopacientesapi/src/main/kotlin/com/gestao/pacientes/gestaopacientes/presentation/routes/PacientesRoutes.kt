package com.gestao.pacientes.gestaopacientes.presentation.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.gestao.pacientes.gestaopacientes.data.repository.PacienteRepository
import com.gestao.pacientes.gestaopacientes.domain.models.ApiResponse
import com.gestao.pacientes.gestaopacientes.domain.models.CreatePacienteRequest
import org.slf4j.LoggerFactory

private val logger = LoggerFactory.getLogger("PacientesRoutes")
private val pacienteRepository = PacienteRepository()

fun Route.pacientesRoutes() {
    route("/api/pacientes") {
        // GET /api/pacientes - Listar todos os pacientes
        get {
            logger.info("Listando todos os pacientes")
            val pacientes = pacienteRepository.findAll()
            call.respond(ApiResponse(
                success = true,
                message = "Pacientes recuperados com sucesso",
                data = pacientes
            ))
        }

        // GET /api/pacientes/{id} - Obter paciente por ID
        get("/{id}") {
            val id = call.parameters["id"]?.toLongOrNull()
            if (id == null) {
                logger.warn("ID inválido fornecido")
                call.respond(HttpStatusCode.BadRequest, ApiResponse<Unit>(
                    success = false,
                    message = "ID inválido"
                ))
                return@get
            }

            val paciente = pacienteRepository.findById(id)
            if (paciente == null) {
                logger.warn("Paciente com ID: $id não encontrado")
                call.respond(HttpStatusCode.NotFound, ApiResponse<Unit>(
                    success = false,
                    message = "Paciente não encontrado"
                ))
                return@get
            }

            logger.info("Paciente encontrado: ${paciente.nome}")
            call.respond(ApiResponse(
                success = true,
                message = "Paciente recuperado com sucesso",
                data = paciente
            ))
        }

        // POST /api/pacientes - Criar novo paciente
        post {
            try {
                val request = call.receive<CreatePacienteRequest>()
                
                if (request.nome.isBlank() || request.email.isBlank()) {
                    logger.warn("Dados inválidos fornecidos para criação de paciente")
                    call.respond(HttpStatusCode.BadRequest, ApiResponse<Unit>(
                        success = false,
                        message = "Nome e email são obrigatórios"
                    ))
                    return@post
                }

                val novoPaciente = com.gestao.pacientes.gestaopacientes.domain.models.Paciente(
                    id = 0,
                    nome = request.nome,
                    email = request.email,
                    telefone = request.telefone,
                    dataNascimento = request.dataNascimento
                )

                val pacienteCriado = pacienteRepository.save(novoPaciente)
                
                call.respond(HttpStatusCode.Created, ApiResponse(
                    success = true,
                    message = "Paciente criado com sucesso",
                    data = pacienteCriado
                ))
            } catch (e: Exception) {
                logger.error("Erro ao criar paciente: ${e.message}", e)
                call.respond(HttpStatusCode.BadRequest, ApiResponse<Unit>(
                    success = false,
                    message = "Erro ao criar paciente: ${e.message}"
                ))
            }
        }

        // PUT /api/pacientes/{id} - Atualizar paciente
        put("/{id}") {
            val id = call.parameters["id"]?.toLongOrNull()
            if (id == null) {
                logger.warn("ID inválido fornecido para atualização")
                call.respond(HttpStatusCode.BadRequest, ApiResponse<Unit>(
                    success = false,
                    message = "ID inválido"
                ))
                return@put
            }

            try {
                val request = call.receive<CreatePacienteRequest>()
                
                val novoPaciente = com.gestao.pacientes.gestaopacientes.domain.models.Paciente(
                    id = id,
                    nome = request.nome,
                    email = request.email,
                    telefone = request.telefone,
                    dataNascimento = request.dataNascimento
                )

                val pacienteAtualizado = pacienteRepository.update(id, novoPaciente)
                
                if (pacienteAtualizado == null) {
                    call.respond(HttpStatusCode.NotFound, ApiResponse<Unit>(
                        success = false,
                        message = "Paciente não encontrado"
                    ))
                    return@put
                }

                call.respond(ApiResponse(
                    success = true,
                    message = "Paciente atualizado com sucesso",
                    data = pacienteAtualizado
                ))
            } catch (e: Exception) {
                logger.error("Erro ao atualizar paciente: ${e.message}", e)
                call.respond(HttpStatusCode.BadRequest, ApiResponse<Unit>(
                    success = false,
                    message = "Erro ao atualizar paciente: ${e.message}"
                ))
            }
        }

        // DELETE /api/pacientes/{id} - Deletar paciente
        delete("/{id}") {
            val id = call.parameters["id"]?.toLongOrNull()
            if (id == null) {
                logger.warn("ID inválido fornecido para deleção")
                call.respond(HttpStatusCode.BadRequest, ApiResponse<Unit>(
                    success = false,
                    message = "ID inválido"
                ))
                return@delete
            }

            if (pacienteRepository.delete(id)) {
                call.respond(ApiResponse<Unit>(
                    success = true,
                    message = "Paciente deletado com sucesso"
                ))
            } else {
                call.respond(HttpStatusCode.NotFound, ApiResponse<Unit>(
                    success = false,
                    message = "Paciente não encontrado"
                ))
            }
        }
    }
}
