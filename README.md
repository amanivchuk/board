# board
Bulletin Board backend

Создание базы данных Postgres в Docker:
  docker run --name postgres-board-db -e POSTGRES_DB=board -e POSTGRES_PASSWORD=root -d -p 5432:5432 postgres

Swagger:
  http://localhost:8082/swagger-ui.html#
