# Gestão de Pacientes API - Backend Kotlin + Ktor

## 📋 Visão Geral
Projeto backend completo em Kotlin utilizando Ktor framework com arquitetura em camadas.

## 🏗️ Arquitetura

### Estrutura de Pastas
```
src/main/kotlin/com/gestao/pacientes/gestaopacientes/
├── presentation/       # Camada de Apresentação (HTTP Routes)
│   └── routes/        # Definição de rotas da API
├── domain/            # Camada de Domínio (Modelos de Negócio)
│   └── models/        # Entidades e DTOs
├── data/              # Camada de Dados
│   └── repository/    # Acesso aos dados
└── config/            # Configurações da aplicação
```

### Camadas

- **Presentation**: Rotas HTTP, controllers
- **Domain**: Modelos de dados, lógica de negócio
- **Data**: Repositórios, persistência de dados

## 🚀 Como Executar

### Requisitos
- JDK 17+
- Gradle 7.5+

### Iniciar o Servidor
```bash
./gradlew run
```

O servidor iniciará em `http://localhost:8080`

## 📡 Endpoints da API

### Health Check
- **GET** `/health` - Verifica se o servidor está operacional
- **GET** `/health/status` - Retorna status detalhado

### Pacientes (CRUD)
- **GET** `/api/pacientes` - Listar todos os pacientes
- **GET** `/api/pacientes/{id}` - Obter paciente por ID
- **POST** `/api/pacientes` - Criar novo paciente
- **PUT** `/api/pacientes/{id}` - Atualizar paciente
- **DELETE** `/api/pacientes/{id}` - Deletar paciente

### Exemplos de Requisição

#### Criar Paciente
```bash
curl -X POST http://localhost:8080/api/pacientes \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "João Silva",
    "email": "joao@example.com",
    "telefone": "11987654321",
    "dataNascimento": "1990-05-15"
  }'
```

#### Listar Pacientes
```bash
curl http://localhost:8080/api/pacientes
```

#### Obter Paciente
```bash
curl http://localhost:8080/api/pacientes/1
```

#### Atualizar Paciente
```bash
curl -X PUT http://localhost:8080/api/pacientes/1 \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "João Silva Atualizado",
    "email": "joao.novo@example.com",
    "telefone": "11987654321",
    "dataNascimento": "1990-05-15"
  }'
```

#### Deletar Paciente
```bash
curl -X DELETE http://localhost:8080/api/pacientes/1
```

## 📦 Dependências Principais

- **Ktor Server**: Framework web
- **Kotlinx Serialization**: Serialização JSON
- **Logback**: Logging
- **SLF4J**: Interface de logging

## 🔧 Configurações

- **Porta**: 8080
- **Host**: 0.0.0.0
- **Serialização**: JSON com Kotlinx Serialization
- **Logs**: INFO level, com CallLogging habilitado

## 📝 Arquivos de Configuração

- `application.properties`: Propriedades básicas do servidor
- `application.yml`: Configurações do Ktor
- `logback.xml`: Configurações de logging
- `build.gradle.kts`: Dependências e build configuration

## 🧪 Testando a API

Use qualquer cliente HTTP como:
- **curl** (terminal)
- **Postman**
- **Insomnia**
- **Thunder Client** (VS Code extension)

## 📚 Recursos Adicionais

- [Documentação Ktor](https://ktor.io/docs/home.html)
- [Kotlin Documentation](https://kotlinlang.org/docs/home.html)
- [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization)
