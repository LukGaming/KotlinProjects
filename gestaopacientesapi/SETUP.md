# Projeto Gestão de Pacientes API

## Estrutura Final do Projeto

```
gestaopacientesapi/
├── src/
│   ├── main/
│   │   ├── kotlin/com/gestao/pacientes/gestaopacientes/
│   │   │   ├── GestaopacientesApplication.kt          # Entrada da aplicação
│   │   │   ├── presentation/                           # Camada de Apresentação
│   │   │   │   └── routes/
│   │   │   │       ├── HealthRoutes.kt                 # Rotas de saúde
│   │   │   │       └── PacientesRoutes.kt              # CRUD de Pacientes
│   │   │   ├── domain/                                 # Camada de Domínio
│   │   │   │   └── models/
│   │   │   │       └── Paciente.kt                    # Modelos e DTOs
│   │   │   ├── data/                                   # Camada de Dados
│   │   │   │   └── repository/
│   │   │   │       └── PacienteRepository.kt           # Acesso aos dados
│   │   │   └── config/                                 # Configurações
│   │   │       ├── Logging.kt                          # Configuração de logs
│   │   │       └── Routing.kt                          # Configuração de rotas
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── application.yml
│   │       └── logback.xml
│   └── test/
│       └── kotlin/com/gestao/pacientes/gestaopacientes/
│           └── ApplicationTest.kt                      # Testes de exemplo
├── build.gradle.kts                                   # Configuração Gradle
├── settings.gradle                                     # Configuração de módulos
├── gradlew / gradlew.bat                              # Gradle wrapper
└── README_PROJETO.md                                  # Documentação detalhada
```

## Início Rápido

### Executar o Servidor
```bash
cd c:\Users\Paulo\Desktop\KotlinProjects\gestaopacientesapi
./gradlew run
```

Servidor iniciará em: **http://localhost:8080**

### Testar a API
```bash
# Health check
curl http://localhost:8080/health

# Listar pacientes
curl http://localhost:8080/api/pacientes

# Criar paciente
curl -X POST http://localhost:8080/api/pacientes \
  -H "Content-Type: application/json" \
  -d '{"nome":"João","email":"joao@test.com","telefone":"11987654321","dataNascimento":"1990-05-15"}'
```

## Recursos Implementados

✅ **Ktor Framework** - Web server moderno e eficiente  
✅ **Arquitetura em Camadas** - Presentation, Domain, Data  
✅ **Serialização JSON** - kotlinx.serialization  
✅ **Logging** - SLF4J + Logback com CallLogging  
✅ **CRUD Completo** - Para Pacientes  
✅ **Respostas Padronizadas** - ApiResponse genérico  
✅ **Health Checks** - Endpoints de status  
✅ **Testes** - Exemplo de teste com Ktor  

## Endpoints Disponíveis

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/health` | Status do servidor |
| GET | `/health/status` | Detalhes de status |
| GET | `/api/pacientes` | Listar todos |
| GET | `/api/pacientes/{id}` | Obter por ID |
| POST | `/api/pacientes` | Criar novo |
| PUT | `/api/pacientes/{id}` | Atualizar |
| DELETE | `/api/pacientes/{id}` | Deletar |

## Tecnologias

- **Kotlin** 1.9.22
- **Ktor** 2.3.7
- **Java** 17+
- **Gradle** 7.5+
- **Logback** 1.4.11

Veja [README_PROJETO.md](README_PROJETO.md) para documentação completa.
