# Sistema Financeiro - Gerenciamento de Débito e Crédito

Este projeto é um **sistema financeiro backend** desenvolvido em **Spring Boot**. Ele permite o registro e gerenciamento de **débito e crédito** através de CRUDs para **lançamentos financeiros**, **pessoas** (clientes/fornecedores) e **categorias**.

---

## Funcionalidades

### CRUD de Lançamentos
- **Registrar lançamentos** de receitas e despesas.
- Associar lançamentos a uma categoria e a uma pessoa.
- **Campos principais**:
  - Descrição
  - Valor
  - Datas de vencimento e pagamento
  - Tipo (Receita ou Despesa)

### CRUD de Pessoas
- Gerenciar informações de pessoas, como:
  - Nome
  - Endereço (logradouro, número, bairro, CEP, cidade, estado)
  - Estado ativo/inativo

### CRUD de Categorias
- Criar e gerenciar categorias financeiras, como:
  - Alimentação
  - Transporte
  - Saúde

---

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3**
- **JPA/Hibernate**
- **Banco de Dados MySQL**
- **Maven**

---

## Estrutura do Projeto

### Pacotes

- **`controller`**: Controladores REST que gerenciam as requisições para cada entidade.
- **`model`**: Representação das entidades (Categoria, Pessoa, Lancamento, TipoLancamento).
- **`repository`**: Interfaces que facilitam o acesso ao banco de dados usando Spring Data JPA.

### Banco de Dados

#### Tabelas

- **`categoria`**
- **`pessoa`**
- **`lancamento`**

#### Relacionamentos

- Cada lançamento está associado a uma pessoa e a uma categoria.
- O modelo relacional é gerenciado com **chaves estrangeiras**.

---

## Endpoints REST

### Categorias

| Método | URL            | Descrição                         |
|--------|----------------|-------------------------------------|
| `GET`  | `/categorias`  | Listar todas as categorias         |
| `POST` | `/categorias`  | Criar uma nova categoria           |
| `GET`  | `/categorias/{id}` | Buscar categoria por ID          |

### Lançamentos

| Método | URL            | Descrição                         |
|--------|----------------|-------------------------------------|
| `GET`  | `/lancamentos` | Listar todos os lançamentos        |
| `POST` | `/lancamentos` | Criar um novo lançamento           |
| `GET`  | `/lancamentos/{id}` | Buscar lançamento por ID         |

### Pessoas

| Método | URL           | Descrição                         |
|--------|---------------|-------------------------------------|
| `GET`  | `/pessoas`    | Listar todas as pessoas            |
| `POST` | `/pessoas`    | Criar uma nova pessoa              |
| `GET`  | `/pessoas/{id}` | Buscar pessoa por ID              |

---

## Configuração

### Requisitos

- **Java 17**
- **Maven**
- **MySQL**

### Banco de Dados

Crie o banco de dados conforme as tabelas abaixo:

```sql
CREATE TABLE categoria (
   codigo BIGINT PRIMARY KEY AUTO_INCREMENT,
   nome VARCHAR(50) NOT NULL
);

CREATE TABLE pessoa (
   codigo BIGINT PRIMARY KEY AUTO_INCREMENT,
   nome VARCHAR(50) NOT NULL,
   logradouro VARCHAR(30),
   numero VARCHAR(30),
   bairro VARCHAR(30),
   cep VARCHAR(30),
   cidade VARCHAR(30),
   estado VARCHAR(30),
   ativo BOOLEAN NOT NULL
);

CREATE TABLE lancamento (
   codigo BIGINT PRIMARY KEY AUTO_INCREMENT,
   descricao VARCHAR(50) NOT NULL,
   data_vencimento DATE NOT NULL,
   data_pagamento DATE,
   valor DECIMAL(10,2) NOT NULL,
   tipo VARCHAR(20) NOT NULL,
   codigo_categoria BIGINT NOT NULL,
   codigo_pessoa BIGINT NOT NULL,
   FOREIGN KEY (codigo_categoria) REFERENCES categoria(codigo),
   FOREIGN KEY (codigo_pessoa) REFERENCES pessoa(codigo)
);
