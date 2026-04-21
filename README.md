<h1 align="center">  Kanban System </h1>

<p align="center">
  <img loading="lazy"
       src="https://img.shields.io/badge/status-finalizado-brightgreen?style=for-the-badge"/>
  <img loading="lazy"
       src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white"/>
  <img loading="lazy"
       src="https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"/>
  <img loading="lazy"
       src="https://img.shields.io/badge/MySQL-00758F?style=for-the-badge&logo=mysql&logoColor=white"/>
  <img loading="lazy"
       src="https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white"/>
</p>

Projeto desenvolvido com o objetivo de aprofundar conhecimentos em desenvolvimento back-end com **Spring Boot**, explorando autenticação, regras de negócio e organização em camadas através da construção de um sistema Kanban completo.

---

## 📌 Sobre o projeto

O sistema consiste em um Kanban funcional onde usuários podem criar e gerenciar projetos e tarefas.

Cada projeto possui um quadro Kanban dividido em:

*  To Do
*  Doing
*  Done

Além disso, o sistema possui controle de acesso baseado em permissões:

*  Usuário comum: gerencia apenas seus próprios dados
*  Administrador: possui acesso total ao sistema

---

##  Preview do Sistema

<img width="1348" height="587" alt="image" src="https://github.com/user-attachments/assets/0c7a25e2-4b3b-4984-97d7-9417883ed46f" />

---

## Funcionalidades

- Sistema de login e autenticação
- Controle de acesso com Spring Security
- CRUD completo de usuários
- CRUD de projetos
- CRUD de tarefas
- Organização de tarefas em Kanban (To Do / Doing / Done)

---

##  Abrir e Rodar o Projeto

- Realize o clone do repositório:

  ```bash
    git clone https://github.com/DaviAlme1da/Spring-Kanban.git

- Configure o banco de dados no application.properties:

  ```bash
    spring.datasource.url=jdbc:mysql://localhost:3306/kanban_db
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
  
    spring.jpa.hibernate.ddl-auto=update
  
- Execute o projeto:

    ```bash
    mvn spring-boot:run

---

## Conhecimentos Aplicados

Durante o desenvolvimento deste projeto, foram praticados:

- Desenvolvimento com Spring Boot
- Autenticação e autorização com Spring Security
- Modelagem de dados com JPA / Hibernate
- Relacionamentos entre entidades
- Arquitetura em camadas (Controller, Service, Repository)
- Uso de DTOs e Mappers
- Tratamento global de exceções

---

## Tecnologias e Ferramentas Utilizadas

- Java
- Spring Boot
- Spring Security
- Thymeleaf
- JPA / Hibernate
- MySQL
- HTML5
- CSS3

---

##  Autor

<p>
Desenvolvido por <strong>Davi Lucas de Almeida</strong>.<br>
Estudante de Análise e Desenvolvimento de Sistemas (ADS).
</p>

