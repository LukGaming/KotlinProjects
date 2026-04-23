package com.gestao.pacientes.gestaopacientes.data

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory

object UsersTable : Table("users") {
    val id = long("id").autoIncrement()
    val name = varchar("name", length = 120)
    val email = varchar("email", length = 150).uniqueIndex()

    override val primaryKey = PrimaryKey(id)
}

object PatientsTable : Table("patients") {
    val id = long("id").autoIncrement()
    val userId = long("user_id").references(UsersTable.id)
    val fullName = varchar("full_name", length = 150)
    val birthDate = varchar("birth_date", length = 10)

    override val primaryKey = PrimaryKey(id)
}

object DocumentsTable : Table("documents") {
    val id = long("id").autoIncrement()
    val patientId = long("patient_id").references(PatientsTable.id)
    val fileName = varchar("file_name", length = 255)
    val contentType = varchar("content_type", length = 120)
    val createdAt = long("created_at")

    override val primaryKey = PrimaryKey(id)
}

object DatabaseFactory {
    private val logger = LoggerFactory.getLogger(DatabaseFactory::class.java)

    fun init() {
        val jdbcUrl = "jdbc:sqlite:file:memdb1?mode=memory&cache=shared"

        logger.info("Inicializando banco SQLite em memoria com Exposed")
        logger.info("URL de conexao: {}", jdbcUrl)

        Database.connect(
            url = jdbcUrl,
            driver = "org.sqlite.JDBC"
        )

        transaction {
            SchemaUtils.create(UsersTable, PatientsTable, DocumentsTable)
        }

        logger.info("Banco inicializado com sucesso")
        logger.info("Tabelas criadas: users, patients, documents")
    }
}
