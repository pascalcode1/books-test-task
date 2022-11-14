REST API, Spring boot, PostgreSQL, Jdbc Template, Stream API, Swagger, Liquibase

Запуск PostgreSQL в Docker:

`docker run --name postgres-books -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=postgres -p 5432:5432 postgres`

Приложение имеет миграции liquibase. При необходимоти можно отключить выставив параметр `spring.liquibase.enabled` в `false`

Также подключен [Swagger UI](http://localhost:8080/swagger-ui/)