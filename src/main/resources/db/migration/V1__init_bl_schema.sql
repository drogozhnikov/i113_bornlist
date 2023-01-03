create schema if not exists bornlist;

SET search_path TO bornlist;

DROP TABLE IF EXISTS units;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS binds;

CREATE TABLE IF NOT EXISTS units
(
    id          SERIAL PRIMARY KEY,
    first_name   VARCHAR(100) not null,
    middle_name  VARCHAR(100),
    last_name    VARCHAR(100) unique not null,
    phone_number VARCHAR(100) unique,
    telegram    VARCHAR(100),
    date        date not null,
    description text      not null
);

CREATE TABLE IF NOT EXISTS users
(
    id         SERIAL PRIMARY KEY,
    user_name   VARCHAR(100) not null unique,
    telegram_id VARCHAR(100) not null unique
);

CREATE TABLE IF NOT EXISTS binds
(
    id     SERIAL PRIMARY KEY,
    user_id int not null,
    unit_id int not null
);

ALTER TABLE binds
    ADD FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE binds
    ADD FOREIGN KEY (unit_id) REFERENCES units (id);



