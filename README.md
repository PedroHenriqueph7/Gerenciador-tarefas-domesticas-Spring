


 Sistema de Organização de Tarefas Domésticas

[![Java](https://img.shields.io/badge/Java-21+-blue)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.5-green)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-orange)](https://maven.apache.org/)

Backend em **Java** utilizando **Spring Boot**, **Spring Data JPA** e API REST em **JSON** para gerenciamento de tarefas domésticas.



## Funcionalidades

- CRUD de tarefas domésticas  
- Listagem de tarefas por prioridade  
- Marcação de tarefas como concluídas  
- Listagem de tarefas concluídas por responsável  
- Atualização de responsável das tarefas  
- Filtragem de tarefas por status (Concluída ou Pendente)  
- Gerenciamento de usuários  
- Persistência de dados em banco relacional (H2/PostgreSQL)



## Tecnologias

- Java 21
- Spring Boot 3
- Spring Data JPA / Hibernate
- Spring Web (REST API)  
- H2 / PostgreSQL
- Maven
- JUnit 5
- Lombok
- JSON


## 📂 Estrutura do Projeto
```markdown
src/
├─ main/
│  ├─ java/com/exemplo/tarefas/
│  │   ├─ controller/    # Endpoints REST
|  |   |─ dtos           # Objetos de transferência de dados
│  │   ├─ service/       # Lógica de negócio
│  │   ├─ repository/    # Repositórios JPA
│  │   ├─ model/         # Entidades do banco
|  |   |  └─ enums/      # Enumeradores
|  |   ├─ exceptions/    # Definição de exceptions(erros)
│  │   └─ infra/         # Configuração de captura de erros na aplicação     
│  └─ resources/
│      ├─ application.properties # Configurações
│      └─ import.sql   # Scripts opcionais de banco
└─ test/
└─ java/    # Testes unitários

```
