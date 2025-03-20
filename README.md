# Credito

## Visão Geral

A aplicação "Credito" é um serviço desenvolvido em Java utilizando o framework Spring Boot. O objetivo principal desta aplicação é gerenciar informações de crédito, incluindo dados como número de crédito, número de NFSe, data de constituição, valor do ISSQN, tipo de crédito, entre outros.

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.4.3**
- **JPA (Java Persistence API)**
- **PostgreSQL**
- **Kafka**
- **Flyway**
- **Lombok**

## Configuração do Ambiente

### Pré-requisitos

- **Java 21**
- **Maven**
- **PostgreSQL**
- **Redis**

### Subindo a Infraestrutura basica
Com o docker instanciado, execute o comando abaixo na raiz do projeto para baixar as dependencias:
```sh
docker-compose up -d
```
### Iniciando o projeto
```sh
mvn spring-boot:run
```
Nesse momento as migrations serão executadas, populando a base de dados.

### Criando topico kafka
```sh
kafka-topics.sh --create --topic credit-events --bootstrap-server kafka:9092 --partitions 1 --replication-factor 1
```
Nesta etapa todo o processo de infraestrutura ja está completo e pronto para receber as requisições, seja via postman ou
via aplicação.

### Realizando consultas via Postman ou terminal

- **Listando todos os creditos**
```sh
curl --location 'localhost:8090/v1/api/creditos/7891011'
```
- **Consultando um credito**
```sh
curl --location 'localhost:8090/v1/api/creditos/123456/credito'
```
### Composição de testes
A aplicação é composta por:
- **Testes Unitarios**
- **Testes Integrados**
- **Teste Container**