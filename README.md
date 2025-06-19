
# CondominoSpringBoot

Projeto simples de sistema de gestão de condomínio desenvolvido com Spring Boot.

---

## 🚀 Tecnologias usadas

- Java 21
- Spring Boot 3.5.0
- Spring Data JPA
- Banco H2 em memória
- Swagger (OpenAPI) para documentação da API

---

## 📋 Funcionalidades principais

- Cadastro e gerenciamento de condomínios e moradores
- Banco de dados em memória com H2
- Documentação da API via Swagger UI
- Console H2 para acessar o banco

---

## 🛠️ Como rodar o projeto

1. Clone este repositório:
   ```bash
   git clone https://github.com/anacarolinaalves/condominoSpringBoot.git
   ```

2. Entre na pasta do projeto:
   ```bash
   cd condominoSpringBoot
   ```

3. Compile e rode a aplicação com Maven:
   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```
   *Obs:* No Windows, use `mvnw.cmd` em vez de `./mvnw`.

---

## 📌 Acessando a aplicação

- API disponível em:  
  `http://localhost:8080`

- Documentação Swagger UI:  
  `http://localhost:8080/swagger-ui.html`

- Console do banco H2:  
  `http://localhost:8080/h2-console`  
  Login:  
  - JDBC URL: `jdbc:h2:mem:testdb`  
  - Usuário: `sa`  
  - Senha: (deixe em branco)

---

## 📫 Contato

- Ana Carolina Alves  
- LinkedIn: https://www.linkedin.com/in/ana-carolina-alves-/
- Email: anacarolina.dev@outlook.com

---

![Thanks](https://media.giphy.com/media/hvRJCLFzcasrR4ia7z/giphy.gif)
