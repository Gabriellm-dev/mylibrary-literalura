# MyLibrary-Literalura

## Descrição

O MyLibrary-Literalura é um aplicativo Spring Boot que permite buscar e armazenar livros e autores. Ele se conecta a uma API pública para obter informações sobre livros e autores e armazena essas informações em um banco de dados PostgreSQL.

## Funcionalidades

- Buscar livros por título através de uma API pública.
- Listar todos os livros armazenados.
- Listar livros por idioma.
- Listar todos os autores armazenados.
- Listar autores vivos em um determinado ano.

## Pré-requisitos

- Java 20
- Maven
- PostgreSQL
- pgAdmin (opcional, para administração do PostgreSQL)

## Configuração

### Configuração do Banco de Dados

1. Instale o PostgreSQL e crie um banco de dados chamado `mylibrary`.
2. No `pgAdmin`, conecte-se ao seu servidor PostgreSQL e crie o banco de dados se ele ainda não existir.
3. Crie um usuário e senha para acessar o banco de dados.

### Configuração do Projeto

1. Clone este repositório:
   ```bash
   git clone https://github.com/Gabriellm-dev/mylibrary-literalura.git
   cd mylibrary-literalura
   ```

2. Configure as credenciais do banco de dados no arquivo `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/mylibrary
   spring.datasource.username=seu-usuario
   spring.datasource.password=sua-senha
   spring.jpa.hibernate.ddl-auto=update
   ```

3. Compile e execute o projeto:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

## Uso

Ao iniciar a aplicação, você verá um menu com as seguintes opções:

1. Buscar livro por título
2. Listar todos os livros
3. Listar livros por idioma
4. Listar todos os autores
5. Listar autores vivos em determinado ano
0. Sair

Escolha uma opção digitando o número correspondente e seguindo as instruções na tela.

## Licença

Este projeto está licenciado sob a MIT License. Veja o arquivo LICENSE para mais detalhes.
