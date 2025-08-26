

```markdown
# 🏠 Sistema de Organização de Tarefas Domésticas

[![Java](https://img.shields.io/badge/Java-17+-blue)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.0-green)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.9.0-orange)](https://maven.apache.org/)

Backend em **Java** utilizando **Spring Boot**, **Spring Data JPA** e API REST em **JSON** para gerenciamento de tarefas domésticas.



## ⚡ Funcionalidades

- CRUD de tarefas domésticas  
- Listagem de tarefas por prioridade  
- Marcação de tarefas como concluídas  
- Listagem de tarefas concluídas por responsável  
- Atualização de responsável das tarefas  
- Filtragem de tarefas por status (Concluída ou Pendente)  
- Gerenciamento de usuários  
- Persistência de dados em banco relacional (H2/PostgreSQL)



## 🛠 Tecnologias

- **Java 21**  
- **Spring Boot 3**  
- **Spring Data JPA / Hibernate**  
- **Spring Web (REST API)**  
- **H2 / PostgreSQL**  
- **Maven**  
- **JUnit 5**  
- **Lombok**  
- **JSON** para comunicação de dados


## 📂 Estrutura do Projeto



src/
├─ main/
│  ├─ java/com/exemplo/tarefas/
│  │   ├─ controller/    # Endpoints REST
│  │   ├─ service/       # Lógica de negócio
│  │   ├─ repository/    # Repositórios JPA
│  │   ├─ model/         # Entidades do banco
│  │   └─ dto/           # Objetos de transferência de dados
│  └─ resources/
│      ├─ application.properties # Configurações
│      └─ data.sql / schema.sql   # Scripts opcionais de banco
└─ test/
└─ java/                    # Testes unitários e de integração

```
