# PJ_springBoot_RabbitMQ
Projeto de microserviços com Spring Boot RabbitMQ e AWS

### Dependências Spring
- Spring Data JPA   //SQL
- Spring Web //WEB
- PostgreeSQL Driver //SQL
- Lombok //Developer Tools
- WebSocket //Messaging
- Spring for RabbitMQ //Messaging

### Imagens docker

#### FrontEnd

``docker run -d -p 80:80 --name proposta-web-container matheuspieropan/proposta-web``

#### PostgreSQL

``docker run --name postgres-container -d -e POSTGRES_PASSWORD=123 -e POSTGRES_DB=propostadb -p 5433:5432 postgres``

- Onde:
  - postgres-container = nome da instancia docker
  - POSTGRES_DB = nome do BD
  - POSTGRES_PASSWORD = senha do BD
  - ** POSTGRES_USER = nome do usuário (no projeto não foi utilizado, o padrão postgres foi utilizado)