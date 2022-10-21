CREATE TABLE IF NOT EXISTS users
(
    id         bigserial primary key,
    username   varchar(36) not null,
    password   varchar(80) not null
)