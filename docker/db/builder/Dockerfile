FROM ubuntu:latest

RUN apt-get update && apt-get install -y postgresql-client

COPY ./sql/grant_user.sql ./

CMD psql -h db -U service -d task_management -f grant_user.sql
