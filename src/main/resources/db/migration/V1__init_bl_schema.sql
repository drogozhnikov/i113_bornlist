create schema if not exists bornlist;

SET search_path TO bornlist;

DROP TABLE IF EXISTS units;
DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users
(
    id        SERIAL PRIMARY KEY,
    user_name VARCHAR(100) not null unique
);

CREATE TABLE IF NOT EXISTS units
(
    id          SERIAL PRIMARY KEY,
    user_id     INTEGER      not null,
    first_name  VARCHAR(100) not null,
    last_name   VARCHAR(100) not null,
    notify      boolean      NOT NULL DEFAULT FALSE,
    date        date         not null,
    description text
);


ALTER TABLE units
    ADD FOREIGN KEY (user_id) REFERENCES users (id);




