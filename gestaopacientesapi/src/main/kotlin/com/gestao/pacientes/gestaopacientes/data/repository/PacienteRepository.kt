package com.gestao.pacientes.gestaopacientes.data.repository

import com.gestao.pacientes.gestaopacientes.domain.models.Paciente
import org.slf4j.LoggerFactory
import java.util.concurrent.atomic.AtomicLong

class PacienteRepository {
    companion object {
        private val logger = LoggerFactory.getLogger(PacienteRepository::class.java)
        private val pacientes = mutableMapOf<Long, Paciente>()
        private val idCounter = AtomicLong(1)

        init {
            // Dados de exemplo
            pacientes[1L] = Paciente(1L, "João Silva", "joao@example.com", "11987654321", "1990-05-15")
            pacientes[2L] = Paciente(2L, "Maria Santos", "maria@example.com", "11987654322", "1985-03-20")
            idCounter.set(3L)
        }
    }

    fun findAll(): List<Paciente> {
        logger.info("Buscando todos os pacientes")
        return pacientes.values.toList()
    }

    fun findById(id: Long): Paciente? {
        logger.info("Buscando paciente com ID: $id")
        return pacientes[id]
    }

    fun save(paciente: Paciente): Paciente {
        val novoId = idCounter.getAndIncrement()
        val novoPaciente = paciente.copy(id = novoId)
        pacientes[novoId] = novoPaciente
        logger.info("Paciente criado com ID: $novoId - ${novoPaciente.nome}")
        return novoPaciente
    }

    fun update(id: Long, paciente: Paciente): Paciente? {
        if (pacientes.containsKey(id)) {
            val pacienteAtualizado = paciente.copy(id = id)
            pacientes[id] = pacienteAtualizado
            logger.info("Paciente atualizado com ID: $id")
            return pacienteAtualizado
        }
        logger.warn("Paciente com ID: $id não encontrado")
        return null
    }

    fun delete(id: Long): Boolean {
        val deleted = pacientes.remove(id) != null
        if (deleted) {
            logger.info("Paciente deletado com ID: $id")
        } else {
            logger.warn("Tentativa de deletar paciente com ID: $id que não existe")
        }
        return deleted
    }
}
