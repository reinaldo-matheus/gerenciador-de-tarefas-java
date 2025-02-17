# Gerenciador de Tarefas em Java

Este projeto consiste em um sistema de gerenciamento de tarefas desenvolvido em Java, utilizando o framework JSf, JPA/Hibernate para persistência de dados e PostgreSQL como banco de dados.

## Funcionalidades Implementadas

A. **Persistência de Dados**
   - Configuração do JPA/Hibernate
   - Integração com PostgreSQL
   - Implementação do padrão DAO para acesso aos dados

B. **Modelo de Dados**
   - Entidade Task com atributos básicos (id, título, descrição, status)
   - Mapeamento JPA para criação automática das tabelas

C. **Camada de Acesso a Dados**
   - Interface TaskDAO definindo operações CRUD
   - Implementação TaskDAOImpl com gerenciamento de transações
   - Utilização de EntityManager para operações no banco

## Requisitos do Sistema

- Java 11 ou superior
- PostgreSQL 12 ou superior
- Maven 3.6 ou superior
- IDE Java (recomendado: IntelliJ IDEA ou Eclipse)

## Configuração do Ambiente Local

1. **Banco de Dados**
   ```sql
   CREATE DATABASE task_manager;
   ```

2. **Configuração do persistence.xml**
   - Localizado em: `src/main/resources/META-INF/persistence.xml`

3. **Compilação do Projeto**
   ```bash
   mvn clean install
   ```

4. **Execução**
   - Importe o projeto na sua IDE
   - Execute a classe principal ou deploy no servidor Tomcat

## Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── taskmanager/
│   │           ├── bean/
│   │           │   └── TaskBean.java
│   │           ├── dao/
│   │           │   ├── TaskDAO.java
│   │           │   └── TaskDAOImpl.java
│   │           ├── model/
│   │           │   └── Task.java
│   │           └── util/
│   │               └── JPAUtil.java
│   └── resources/
│       └── META-INF/
│           └── persistence.xml
└── webapp/
    └── WEB-INF/
```


